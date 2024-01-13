package models.reports;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.calculo.Calc_AjustesEpOdo;
import models.calculo.Calc_BodegaEmpresa;
import models.calculo.Calc_Precio;
import models.calculo.Inventarios;
import models.calculo.ModCalc_GuiasPer;
import models.calculo.ModCalc_InvInicial;
import models.calculo.ModeloCalculo;
import models.tables.BodegaEmpresa;
import models.tables.Equipo;
import models.tables.ListaPrecioServicio;
import models.tables.Moneda;
import models.tables.TasasCambio;
import models.tables.VentaServicio;
import models.utilities.Fechas;




public class ReportGerenciales {
	public Long anioMes;
	public String categoria;
	public Double precioCompra;
	public Double precioReposicion;
	public Double precioReal;
	public Double precioLista;
	public Double ventaParcial;
	public Long id_grupo;
	

	public ReportGerenciales(Long anioMes, String categoria, Double precioCompra, Double precioReposicion,
			Double precioReal, Double precioLista, Double ventaParcial, Long id_grupo) {
		super();
		this.anioMes = anioMes;
		this.categoria = categoria;
		this.precioCompra = precioCompra;
		this.precioReposicion = precioReposicion;
		this.precioReal = precioReal;
		this.precioLista = precioLista;
		this.ventaParcial = ventaParcial;
		this.id_grupo = id_grupo;
	}

	public ReportGerenciales() {
		super();
	}
	
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble4 = new DecimalFormat("#,##0.0000");
	static SimpleDateFormat myformatfecha = new SimpleDateFormat("dd-MM-yyyy");
	static DecimalFormat myformatMonedaOrigen = new DecimalFormat("#,##0");
	
	
	@SuppressWarnings("static-access")
	public static List<String> graficoCategoriasAnio(Connection con, String db, Long id_grupo, Map<String,String> mapeoDiccionario, String esPorSucursal, String id_sucursal) {
		
		List<String> series = new ArrayList<String>();
		
		List<Long> listIdBodegaEmpresa = ModCalc_InvInicial.listIdBodegaEmpresa(con, db, "");
		Map<Long,Calc_BodegaEmpresa> mapBodegaEmpresa = Calc_BodegaEmpresa.mapAllBodegasVigentes(con, db);
		Map<String,Calc_Precio> mapPrecios = Calc_Precio.mapPrecios(con, db, listIdBodegaEmpresa);
		Map<Long,Calc_Precio> mapMaestroPrecios = Calc_Precio.mapMaestroPrecios(con, db);
		Map<Long,Calc_Precio> mapPreciosCompra = Calc_Precio.mapPreciosCompra(con, db);
		Map<Long,BodegaEmpresa> mapBodegas = BodegaEmpresa.mapAll(con, db);
		Map<String,TasasCambio> mapAllTasas = TasasCambio.mapTasasporAllFecha(con, db, mapeoDiccionario.get("pais"));
		
		Map<String,ListaPrecioServicio> mapPreciosOdo = ListaPrecioServicio.mapAllListaPrecioServicio(con, db);
		Long ultimoAnioMes = ReportGerenciales.ultimoAnioMes(con, db, id_grupo);
		
		List<List<String>> listIniFinMes2Anios = ReportGerenciales.graficoMeses2Anios();
		Long inicioMes = Long.parseLong(listIniFinMes2Anios.get(0).get(2));
		
		
		/*aux.add(desdeSql);
			aux.add(hastaSql);
			aux.add(strAnioMes); // 2 anioMes
			aux.add(strAnioMes.substring(4,6)+"-"+strAnioMes.substring(0,4)); // 3 mes-Anio
			aux.add(strAnioMes.substring(0,4)); // 4 Anio*/
		
		//AQUI DEBO ELIMINAR DEL LA TABLA HISTORICA reportGerencial POR ANIO COMPLETO SI DESEO ACTUALIZAR TODO o aumentar los 4 meses de acualizacion. 
		//AQUI resto 4 meses para grabar en historico.
		Long AAMM_menos4 = ReportGerenciales.actualAnioMes(-4);
		
		Fechas hoy = Fechas.hoy();
		
		String[] aux1 =  hoy.getFechaStrAAMMDD().split("-");
		Long yearActual = Long.parseLong(aux1[0]);
		Long yearAnterior = yearActual - (long)1;
		
		Map<Long,Long> mapDec = Moneda.numeroDecimal(con, db);
		
		Map<Long,Long> mapIdEquipoVsIdGrupo = Equipo.mapIdEquipoVsIdGrupo(con, db);
		
		
		
		//revisa y llena historico a partir del ultimo ingreso
		for(int i=0; i<listIniFinMes2Anios.size(); i++){
			
			Long compara = Long.parseLong(listIniFinMes2Anios.get(i).get(2));
			
			if(ultimoAnioMes<compara && AAMM_menos4>compara) {
				
				String desdeAAMMDD = Fechas.obtenerFechaDesdeStrAAMMDD(listIniFinMes2Anios.get(i).get(0)).getFechaStrAAMMDD();
				String hastaAAMMDD = Fechas.obtenerFechaDesdeStrAAMMDD(listIniFinMes2Anios.get(i).get(1)).getFechaStrAAMMDD();
				
				TasasCambio tasasCambio = mapAllTasas.get(hastaAAMMDD);
				Calendar auxHastaAAMMDD = Fechas.obtenerFechaDesdeStrAAMMDD(hastaAAMMDD).fechaCal;
				Calendar auxHoyAAMMDD = hoy.getFechaCal();
				if(auxHoyAAMMDD.before(auxHastaAAMMDD)) {
					hoy = hoy.addDias(auxHoyAAMMDD, -2);
					tasasCambio = mapAllTasas.get(hoy.getFechaStrAAMMDD());
				}
				
				Map<Long,Double> tasas = new HashMap<Long,Double>();  // id_moneda, valor tasa
				if(tasasCambio!=null) {
					tasas.put((long)1, (double)1);
					tasas.put((long)2, Double.parseDouble(tasasCambio.getClpUsd().replaceAll(",", "")));
					tasas.put((long)3, Double.parseDouble(tasasCambio.getClpEur().replaceAll(",", "")));
					tasas.put((long)4, Double.parseDouble(tasasCambio.getClpUf().replaceAll(",", "")));
				}else {
					tasas.put((long)1, (double)1);
					tasas.put((long)2, (double)1);
					tasas.put((long)3, (double)1);
					tasas.put((long)4, (double)1);
				}
				
				
				List<String> datos = ReportGerenciales.resumenTotalMesAnio(con, db, desdeAAMMDD, hastaAAMMDD, tasas, listIdBodegaEmpresa, mapBodegaEmpresa, 
						mapPrecios, mapMaestroPrecios, mapPreciosCompra, mapBodegas, id_grupo,mapDec,mapPreciosOdo, mapIdEquipoVsIdGrupo, esPorSucursal, id_sucursal);
				
				ReportGerenciales.actualizaTablaReportGerencial(con, db, datos, listIniFinMes2Anios.get(i).get(2), id_grupo);
				
			}
		}
		
		
		//trae historico desde tabla reportGerencial
		List<ReportGerenciales> historia = ReportGerenciales.datosHistoria(con, db, inicioMes, AAMM_menos4, id_grupo);
		
		String categoriasVentaAnterior = "[";
		String categoriasVentaActual = "[";
		
		String ventaParcialAnterior =  "[";
		String ventaParcialActual =  "[";
		String ventaAcumAnterior =  "[";
		String ventaAcumActual =  "[";
		
		Double sumVtaAnterior = (double) 0;
		Double sumVtaActual = (double) 0;
		
		String categoriasColocacion = "[";
		String colocVtaPReal = "[";
		String colocVtaPLista = "[";
		String colocPVenta = "[";
		String colocPCompra = "[";
		
		String anioAnterior = listIniFinMes2Anios.get(0).get(4);
		
		Long AAMM_menos11 = ReportGerenciales.actualAnioMes(-11);
		
	
		
		for(int i=0; i<historia.size(); i++){
			
			if(historia.get(i).categoria.substring(3, 7).equals(anioAnterior)) {
				categoriasVentaAnterior += "'"+historia.get(i).categoria + "',";
				ventaParcialAnterior += historia.get(i).ventaParcial.longValue() + ",";
				sumVtaAnterior += historia.get(i).ventaParcial;
				ventaAcumAnterior += sumVtaAnterior.longValue() + ",";
			}else {
				categoriasVentaActual += "'"+historia.get(i).categoria + "',";
				ventaParcialActual += historia.get(i).ventaParcial.longValue() + ",";
				sumVtaActual += historia.get(i).ventaParcial;
				ventaAcumActual += sumVtaActual.longValue() + ",";
			}
			
			if( historia.get(i).anioMes>=AAMM_menos11 && historia.get(i).anioMes<AAMM_menos4 ) {
				categoriasColocacion += "'"+historia.get(i).categoria+"',";
				colocVtaPReal += historia.get(i).precioReal.longValue() + ",";
				colocVtaPLista+= historia.get(i).precioLista.longValue() + ",";
				colocPVenta += historia.get(i).precioReposicion.longValue() + ",";
				colocPCompra += historia.get(i).precioCompra.longValue() + ",";
			}
		}
		
		
		//obtiene los ultimos x meses mas proyeccion mes actual
		
		Long menos0 = ReportGerenciales.actualAnioMes(0);
		
		
		
		for(int i=0;i<listIniFinMes2Anios.size();i++){
			
			Long compara = Long.parseLong(listIniFinMes2Anios.get(i).get(2));
			
			if(AAMM_menos4 <= compara &&  compara <= menos0) {
				
				String desdeAAMMDD = Fechas.obtenerFechaDesdeStrAAMMDD(listIniFinMes2Anios.get(i).get(0)).getFechaStrAAMMDD();
				String hastaAAMMDD = Fechas.obtenerFechaDesdeStrAAMMDD(listIniFinMes2Anios.get(i).get(1)).getFechaStrAAMMDD();
				
				TasasCambio tasasCambio = mapAllTasas.get(hastaAAMMDD);
				Calendar auxHastaAAMMDD = Fechas.obtenerFechaDesdeStrAAMMDD(hastaAAMMDD).fechaCal;
				Calendar auxHoyAAMMDD = hoy.getFechaCal();
				if(auxHoyAAMMDD.before(auxHastaAAMMDD)) {
					hoy = hoy.addDias(auxHoyAAMMDD, -2);
					tasasCambio = mapAllTasas.get(hoy.getFechaStrAAMMDD());
				}
				
				
				Map<Long,Double> tasas = new HashMap<Long,Double>();  // id_moneda, valor tasa
				if(tasasCambio!=null) {
					tasas.put((long)1, (double)1);
					tasas.put((long)2, Double.parseDouble(tasasCambio.getClpUsd().replaceAll(",", "")));
					tasas.put((long)3, Double.parseDouble(tasasCambio.getClpEur().replaceAll(",", "")));
					tasas.put((long)4, Double.parseDouble(tasasCambio.getClpUf().replaceAll(",", "")));
				}else {
					tasas.put((long)1, (double)1);
					tasas.put((long)2, (double)1);
					tasas.put((long)3, (double)1);
					tasas.put((long)4, (double)1);
				}
				
				List<String> datos = ReportGerenciales.resumenTotalMesAnio(con, db, desdeAAMMDD, hastaAAMMDD, tasas, listIdBodegaEmpresa, mapBodegaEmpresa, 
						mapPrecios, mapMaestroPrecios, mapPreciosCompra, mapBodegas, id_grupo, mapDec, mapPreciosOdo, mapIdEquipoVsIdGrupo, esPorSucursal, id_sucursal);
				
				String[] aux = listIniFinMes2Anios.get(i).get(3).split("-");

				if(aux[1].equals(yearAnterior.toString())) {
					
					categoriasVentaAnterior = categoriasVentaAnterior +"'"+listIniFinMes2Anios.get(i).get(3) + "',";
					ventaParcialAnterior = ventaParcialAnterior + datos.get(4) + ",";
					sumVtaAnterior = sumVtaAnterior + Double.parseDouble(datos.get(4));
					ventaAcumAnterior = ventaAcumAnterior + sumVtaAnterior.longValue() + ",";
					
				}else {
					
					categoriasVentaActual = categoriasVentaActual +"'"+listIniFinMes2Anios.get(i).get(3) + "',";
					ventaParcialActual = ventaParcialActual + datos.get(4) + ",";
					sumVtaActual = sumVtaActual + Double.parseDouble(datos.get(4));
					ventaAcumActual = ventaAcumActual + sumVtaActual.longValue() + ",";
					
				}
				
				
				categoriasColocacion = categoriasColocacion +"'"+listIniFinMes2Anios.get(i).get(3) + "',";
				colocVtaPReal = colocVtaPReal + datos.get(4) + ",";
				colocVtaPLista = colocVtaPLista + datos.get(5) + ",";
				colocPVenta = colocPVenta + datos.get(6) + ",";
				colocPCompra = colocPCompra + datos.get(7) + ",";
			}
			
			if(compara > menos0) {
				categoriasVentaActual = categoriasVentaActual +"'"+listIniFinMes2Anios.get(i).get(3) + "',";
				ventaParcialActual = ventaParcialActual + "0,";
				ventaAcumActual = ventaAcumActual + " ,";
			}
		}
		
		
//		aqui debo quitar la ultima coma a categoriasVentaAnterior y los otros y cerrar con ]
		
		
		if(categoriasVentaAnterior.length() > 1) {
			categoriasVentaAnterior = categoriasVentaAnterior.substring(0,categoriasVentaAnterior.length()-1)+"]";
			ventaParcialAnterior = ventaParcialAnterior.substring(0,ventaParcialAnterior.length()-1)+"]";
			ventaAcumAnterior = ventaAcumAnterior.substring(0,ventaAcumAnterior.length()-1)+"]";
		}else {
			categoriasVentaAnterior += "]";
			ventaParcialAnterior += "]";
			ventaAcumAnterior += "]";
		}
		
		if(categoriasVentaActual.length() > 1) {
			categoriasVentaActual = categoriasVentaActual.substring(0,categoriasVentaActual.length()-1)+"]";
			ventaParcialActual = ventaParcialActual.substring(0,ventaParcialActual.length()-1)+"]";
			ventaAcumActual = ventaAcumActual.substring(0,ventaAcumActual.length()-1)+"]";
		}else {
			categoriasVentaActual += "]";
			ventaParcialActual += "]";
			ventaAcumActual += "]";
		}
		
		
		if(categoriasColocacion.length() > 1) {
			categoriasColocacion = categoriasColocacion.substring(0,categoriasColocacion.length()-1)+"]";
		}else {
			categoriasColocacion += "]";
		}
		
		if(colocVtaPReal.length() > 1) {
			colocVtaPReal = colocVtaPReal.substring(0,colocVtaPReal.length()-1)+"]";
		}else {
			colocVtaPReal += "]";
		}
		
		if(colocVtaPLista.length() > 1) {
			colocVtaPLista = colocVtaPLista.substring(0,colocVtaPLista.length()-1)+"]";
		}else {
			colocVtaPLista += "]";
		}
		
		if(colocPVenta.length() > 1) {
			colocPVenta = colocPVenta.substring(0,colocPVenta.length()-1)+"]";
		}else {
			colocPVenta += "]";
		}
		
		if(colocPCompra.length() > 1) {
			colocPCompra = colocPCompra.substring(0,colocPCompra.length()-1)+"]";
		}else {
			colocPCompra += "]";
		}
	
		series.add(categoriasVentaAnterior);	// 0 
		series.add(ventaParcialAnterior);		// 1
		series.add(ventaAcumAnterior);			// 2
		series.add(categoriasVentaActual);		// 3
		series.add(ventaParcialActual);			// 4
		series.add(ventaAcumActual);			// 5
		series.add(categoriasColocacion);		// 6
		series.add(colocVtaPReal);				// 7
		series.add(colocVtaPLista);				// 8
		series.add(colocPVenta);				// 9
		series.add(colocPCompra);				// 10
		
		return (series);
	}
	
	@SuppressWarnings("static-access")
	public static List<String> graficoCategoriasAnioPorGrupo(Connection con, String db, Long id_grupo, Map<String,String> mapeoDiccionario, String esPorSucursal, String id_sucursal) {
		
		List<String> series = new ArrayList<String>();
		
		List<Long> listIdBodegaEmpresa = ModCalc_InvInicial.listIdBodegaEmpresa(con, db, "");
		Map<Long,Calc_BodegaEmpresa> mapBodegaEmpresa = Calc_BodegaEmpresa.mapAllBodegasVigentes(con, db);
		Map<String,Calc_Precio> mapPrecios = Calc_Precio.mapPrecios(con, db, listIdBodegaEmpresa);
		Map<Long,Calc_Precio> mapMaestroPrecios = Calc_Precio.mapMaestroPrecios(con, db);
		Map<Long,Calc_Precio> mapPreciosCompra = Calc_Precio.mapPreciosCompra(con, db);
		Map<Long,BodegaEmpresa> mapBodegas = BodegaEmpresa.mapAll(con, db);
		Map<String,TasasCambio> mapAllTasas = TasasCambio.mapTasasporAllFecha(con, db, mapeoDiccionario.get("pais"));
		
		Map<String,ListaPrecioServicio> mapPreciosOdo = ListaPrecioServicio.mapAllListaPrecioServicio(con, db);
		Long ultimoAnioMes = ReportGerenciales.ultimoAnioMes(con, db, id_grupo);
		
		List<List<String>> listIniFinMes2Anios = ReportGerenciales.graficoMeses2Anios();
		Long inicioMes = Long.parseLong(listIniFinMes2Anios.get(0).get(2));
		
		
		/*aux.add(desdeSql);
			aux.add(hastaSql);
			aux.add(strAnioMes); // 2 anioMes
			aux.add(strAnioMes.substring(4,6)+"-"+strAnioMes.substring(0,4)); // 3 mes-Anio
			aux.add(strAnioMes.substring(0,4)); // 4 Anio*/
		
		//AQUI DEBO ELIMINAR DEL LA TABLA HISTORICA reportGerencial POR ANIO COMPLETO SI DESEO ACTUALIZAR TODO o aumentar los 4 meses de acualizacion. 
		//AQUI resto 4 meses para grabar en historico.
		Long AAMM_menos4 = ReportGerenciales.actualAnioMes(-4);
		
		Fechas hoy = Fechas.hoy();
		
		String[] aux1 =  hoy.getFechaStrAAMMDD().split("-");
		Long yearActual = Long.parseLong(aux1[0]);
		Long yearAnterior = yearActual - (long)1;
		
		Map<Long,Long> mapDec = Moneda.numeroDecimal(con, db);
		
		Map<Long,Long> mapIdEquipoVsIdGrupo = Equipo.mapIdEquipoVsIdGrupo(con, db);
		
		
		
		//revisa y llena historico a partir del ultimo ingreso
		for(int i=0; i<listIniFinMes2Anios.size(); i++){
			
			Long compara = Long.parseLong(listIniFinMes2Anios.get(i).get(2));
			
			if(ultimoAnioMes<compara && AAMM_menos4>compara) {
				
				String desdeAAMMDD = Fechas.obtenerFechaDesdeStrAAMMDD(listIniFinMes2Anios.get(i).get(0)).getFechaStrAAMMDD();
				String hastaAAMMDD = Fechas.obtenerFechaDesdeStrAAMMDD(listIniFinMes2Anios.get(i).get(1)).getFechaStrAAMMDD();
				
				TasasCambio tasasCambio = mapAllTasas.get(hastaAAMMDD);
				Calendar auxHastaAAMMDD = Fechas.obtenerFechaDesdeStrAAMMDD(hastaAAMMDD).fechaCal;
				Calendar auxHoyAAMMDD = hoy.getFechaCal();
				if(auxHoyAAMMDD.before(auxHastaAAMMDD)) {
					hoy = hoy.addDias(auxHoyAAMMDD, -2);
					tasasCambio = mapAllTasas.get(hoy.getFechaStrAAMMDD());
				}
				
				Map<Long,Double> tasas = new HashMap<Long,Double>();  // id_moneda, valor tasa
				if(tasasCambio!=null) {
					tasas.put((long)1, (double)1);
					tasas.put((long)2, Double.parseDouble(tasasCambio.getClpUsd().replaceAll(",", "")));
					tasas.put((long)3, Double.parseDouble(tasasCambio.getClpEur().replaceAll(",", "")));
					tasas.put((long)4, Double.parseDouble(tasasCambio.getClpUf().replaceAll(",", "")));
				}else {
					tasas.put((long)1, (double)1);
					tasas.put((long)2, (double)1);
					tasas.put((long)3, (double)1);
					tasas.put((long)4, (double)1);
				}
				
				
				List<String> datos = ReportGerenciales.resumenTotalMesAnioSinAjustes(con, db, desdeAAMMDD, hastaAAMMDD, tasas, listIdBodegaEmpresa, mapBodegaEmpresa, 
						mapPrecios, mapMaestroPrecios, mapPreciosCompra, mapBodegas, id_grupo,mapDec,mapPreciosOdo, mapIdEquipoVsIdGrupo, esPorSucursal, id_sucursal);
				
				ReportGerenciales.actualizaTablaReportGerencial(con, db, datos, listIniFinMes2Anios.get(i).get(2), id_grupo);
				
			}
		}
		
		
		//trae historico desde tabla reportGerencial
		List<ReportGerenciales> historia = ReportGerenciales.datosHistoria(con, db, inicioMes, AAMM_menos4, id_grupo);
		
		String categoriasVentaAnterior = "[";
		String categoriasVentaActual = "[";
		
		String ventaParcialAnterior =  "[";
		String ventaParcialActual =  "[";
		String ventaAcumAnterior =  "[";
		String ventaAcumActual =  "[";
		
		Double sumVtaAnterior = (double) 0;
		Double sumVtaActual = (double) 0;
		
		String categoriasColocacion = "[";
		String colocVtaPReal = "[";
		String colocVtaPLista = "[";
		String colocPVenta = "[";
		String colocPCompra = "[";
		
		String anioAnterior = listIniFinMes2Anios.get(0).get(4);
		
		Long AAMM_menos11 = ReportGerenciales.actualAnioMes(-11);
		
	
		
		for(int i=0; i<historia.size(); i++){
			
			if(historia.get(i).categoria.substring(3, 7).equals(anioAnterior)) {
				categoriasVentaAnterior += "'"+historia.get(i).categoria + "',";
				ventaParcialAnterior += historia.get(i).ventaParcial.longValue() + ",";
				sumVtaAnterior += historia.get(i).ventaParcial;
				ventaAcumAnterior += sumVtaAnterior.longValue() + ",";
			}else {
				categoriasVentaActual += "'"+historia.get(i).categoria + "',";
				ventaParcialActual += historia.get(i).ventaParcial.longValue() + ",";
				sumVtaActual += historia.get(i).ventaParcial;
				ventaAcumActual += sumVtaActual.longValue() + ",";
			}
			
			if( historia.get(i).anioMes>=AAMM_menos11 && historia.get(i).anioMes<AAMM_menos4 ) {
				categoriasColocacion += "'"+historia.get(i).categoria+"',";
				colocVtaPReal += historia.get(i).precioReal.longValue() + ",";
				colocVtaPLista+= historia.get(i).precioLista.longValue() + ",";
				colocPVenta += historia.get(i).precioReposicion.longValue() + ",";
				colocPCompra += historia.get(i).precioCompra.longValue() + ",";
			}
		}
		
		
		//obtiene los ultimos x meses mas proyeccion mes actual
		
		Long menos0 = ReportGerenciales.actualAnioMes(0);
		
		
		
		for(int i=0;i<listIniFinMes2Anios.size();i++){
			
			Long compara = Long.parseLong(listIniFinMes2Anios.get(i).get(2));
			
			if(AAMM_menos4 <= compara &&  compara <= menos0) {
				
				String desdeAAMMDD = Fechas.obtenerFechaDesdeStrAAMMDD(listIniFinMes2Anios.get(i).get(0)).getFechaStrAAMMDD();
				String hastaAAMMDD = Fechas.obtenerFechaDesdeStrAAMMDD(listIniFinMes2Anios.get(i).get(1)).getFechaStrAAMMDD();
				
				TasasCambio tasasCambio = mapAllTasas.get(hastaAAMMDD);
				Calendar auxHastaAAMMDD = Fechas.obtenerFechaDesdeStrAAMMDD(hastaAAMMDD).fechaCal;
				Calendar auxHoyAAMMDD = hoy.getFechaCal();
				if(auxHoyAAMMDD.before(auxHastaAAMMDD)) {
					hoy = hoy.addDias(auxHoyAAMMDD, -2);
					tasasCambio = mapAllTasas.get(hoy.getFechaStrAAMMDD());
				}
				
				
				Map<Long,Double> tasas = new HashMap<Long,Double>();  // id_moneda, valor tasa
				if(tasasCambio!=null) {
					tasas.put((long)1, (double)1);
					tasas.put((long)2, Double.parseDouble(tasasCambio.getClpUsd().replaceAll(",", "")));
					tasas.put((long)3, Double.parseDouble(tasasCambio.getClpEur().replaceAll(",", "")));
					tasas.put((long)4, Double.parseDouble(tasasCambio.getClpUf().replaceAll(",", "")));
				}else {
					tasas.put((long)1, (double)1);
					tasas.put((long)2, (double)1);
					tasas.put((long)3, (double)1);
					tasas.put((long)4, (double)1);
				}
				
				List<String> datos = ReportGerenciales.resumenTotalMesAnioSinAjustes(con, db, desdeAAMMDD, hastaAAMMDD, tasas, listIdBodegaEmpresa, mapBodegaEmpresa, 
						mapPrecios, mapMaestroPrecios, mapPreciosCompra, mapBodegas, id_grupo, mapDec, mapPreciosOdo, mapIdEquipoVsIdGrupo, esPorSucursal, id_sucursal);
				
				String[] aux = listIniFinMes2Anios.get(i).get(3).split("-");

				if(aux[1].equals(yearAnterior.toString())) {
					
					categoriasVentaAnterior = categoriasVentaAnterior +"'"+listIniFinMes2Anios.get(i).get(3) + "',";
					ventaParcialAnterior = ventaParcialAnterior + datos.get(4) + ",";
					sumVtaAnterior = sumVtaAnterior + Double.parseDouble(datos.get(4));
					ventaAcumAnterior = ventaAcumAnterior + sumVtaAnterior.longValue() + ",";
					
				}else {
					
					categoriasVentaActual = categoriasVentaActual +"'"+listIniFinMes2Anios.get(i).get(3) + "',";
					ventaParcialActual = ventaParcialActual + datos.get(4) + ",";
					sumVtaActual = sumVtaActual + Double.parseDouble(datos.get(4));
					ventaAcumActual = ventaAcumActual + sumVtaActual.longValue() + ",";
					
				}
				
				
				categoriasColocacion = categoriasColocacion +"'"+listIniFinMes2Anios.get(i).get(3) + "',";
				colocVtaPReal = colocVtaPReal + datos.get(4) + ",";
				colocVtaPLista = colocVtaPLista + datos.get(5) + ",";
				colocPVenta = colocPVenta + datos.get(6) + ",";
				colocPCompra = colocPCompra + datos.get(7) + ",";
			}
			
			if(compara > menos0) {
				categoriasVentaActual = categoriasVentaActual +"'"+listIniFinMes2Anios.get(i).get(3) + "',";
				ventaParcialActual = ventaParcialActual + "0,";
				ventaAcumActual = ventaAcumActual + " ,";
			}
		}
		
		
//		aqui debo quitar la ultima coma a categoriasVentaAnterior y los otros y cerrar con ]
		
		
		if(categoriasVentaAnterior.length() > 1) {
			categoriasVentaAnterior = categoriasVentaAnterior.substring(0,categoriasVentaAnterior.length()-1)+"]";
			ventaParcialAnterior = ventaParcialAnterior.substring(0,ventaParcialAnterior.length()-1)+"]";
			ventaAcumAnterior = ventaAcumAnterior.substring(0,ventaAcumAnterior.length()-1)+"]";
		}else {
			categoriasVentaAnterior += "]";
			ventaParcialAnterior += "]";
			ventaAcumAnterior += "]";
		}
		
		if(categoriasVentaActual.length() > 1) {
			categoriasVentaActual = categoriasVentaActual.substring(0,categoriasVentaActual.length()-1)+"]";
			ventaParcialActual = ventaParcialActual.substring(0,ventaParcialActual.length()-1)+"]";
			ventaAcumActual = ventaAcumActual.substring(0,ventaAcumActual.length()-1)+"]";
		}else {
			categoriasVentaActual += "]";
			ventaParcialActual += "]";
			ventaAcumActual += "]";
		}
		
		
		if(categoriasColocacion.length() > 1) {
			categoriasColocacion = categoriasColocacion.substring(0,categoriasColocacion.length()-1)+"]";
		}else {
			categoriasColocacion += "]";
		}
		
		if(colocVtaPReal.length() > 1) {
			colocVtaPReal = colocVtaPReal.substring(0,colocVtaPReal.length()-1)+"]";
		}else {
			colocVtaPReal += "]";
		}
		
		if(colocVtaPLista.length() > 1) {
			colocVtaPLista = colocVtaPLista.substring(0,colocVtaPLista.length()-1)+"]";
		}else {
			colocVtaPLista += "]";
		}
		
		if(colocPVenta.length() > 1) {
			colocPVenta = colocPVenta.substring(0,colocPVenta.length()-1)+"]";
		}else {
			colocPVenta += "]";
		}
		
		if(colocPCompra.length() > 1) {
			colocPCompra = colocPCompra.substring(0,colocPCompra.length()-1)+"]";
		}else {
			colocPCompra += "]";
		}
	
		series.add(categoriasVentaAnterior);	// 0 
		series.add(ventaParcialAnterior);		// 1
		series.add(ventaAcumAnterior);			// 2
		series.add(categoriasVentaActual);		// 3
		series.add(ventaParcialActual);			// 4
		series.add(ventaAcumActual);			// 5
		series.add(categoriasColocacion);		// 6
		series.add(colocVtaPReal);				// 7
		series.add(colocVtaPLista);				// 8
		series.add(colocPVenta);				// 9
		series.add(colocPCompra);				// 10
		
		return (series);
	}
	
	public static List<List<String>> graficoMeses2Anios() {
		Calendar fechaActual = Fechas.hoy().fechaCal;		
		int anioActual = fechaActual.get(Calendar.YEAR);
		int anioAnterior = anioActual-1;
		int anio = anioAnterior;
		Calendar fecha = Fechas.hoy().fechaCal;
		List<List<String>> listFechasFinMes = new ArrayList<List<String>>();
		List<String> aux1 = new ArrayList<String>();
		fecha.set(anio, 0, 1);
		aux1.add(fecha.get(Calendar.YEAR) + "-" + (fecha.get(Calendar.MONTH)+1) + "-" + fecha.get(Calendar.DATE));
		fecha.set(anio, 0, 31);
		aux1.add(fecha.get(Calendar.YEAR) + "-" + (fecha.get(Calendar.MONTH)+1) + "-" + fecha.get(Calendar.DATE));
		int auxAnio = fecha.get(Calendar.YEAR);
		int auxMes = fecha.get(Calendar.MONTH)+1;
		String strAnioMes="";
		if(auxMes<10) {strAnioMes = auxAnio+"0"+auxMes;
		}else {strAnioMes = auxAnio+""+auxMes;}
		aux1.add(strAnioMes);
		aux1.add(strAnioMes.substring(5,6)+"-"+strAnioMes.substring(0,4)); // 3 mes-Anio
		aux1.add(strAnioMes.substring(0,4)); // 4 Anio
		listFechasFinMes.add(aux1);
		
		for(int i = 1;i<24;i++){
			fecha.add(Calendar.MONTH,1);
			fecha.set(Calendar.DATE, fecha.getActualMaximum(Calendar.DAY_OF_MONTH));
			String hastaSql = fecha.get(Calendar.YEAR) + "-" + (fecha.get(Calendar.MONTH)+1) + "-" + fecha.get(Calendar.DATE);
			fecha.set(Calendar.DATE, 1);
			String desdeSql = fecha.get(Calendar.YEAR) + "-" + (fecha.get(Calendar.MONTH)+1) + "-" + fecha.get(Calendar.DATE);
			auxAnio = fecha.get(Calendar.YEAR);
			auxMes = fecha.get(Calendar.MONTH)+1;
			strAnioMes="";
			if(auxMes<10) {
				strAnioMes = auxAnio+"0"+auxMes;
			}else {
				strAnioMes = auxAnio+""+auxMes;
			}
			List<String> aux = new ArrayList<String>();
			aux.add(desdeSql);
			aux.add(hastaSql);
			aux.add(strAnioMes); // 2 anioMes
			aux.add(strAnioMes.substring(4,6)+"-"+strAnioMes.substring(0,4)); // 3 mes-Anio
			aux.add(strAnioMes.substring(0,4)); // 4 Anio
			listFechasFinMes.add(aux);
		}
		return (listFechasFinMes);
	}
	
	public static Long ultimoAnioMes(Connection con, String db, Long id_grupo) {
		
		Long aux =(long)0;
		try { 
			  PreparedStatement smt = con.prepareStatement(" select max(anioMes) from `"+db+"`.reportGerencial where id_grupo=?;");
			  smt.setLong(1, id_grupo);
			  ResultSet rs = smt.executeQuery();
			  if (rs.next()) {
				  aux = rs.getLong(1);
			  }
			  rs.close();smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (aux);
	}
	
	public static Long actualAnioMes(int meses) {
		Calendar fechaActual = Fechas.hoy().fechaCal;
		fechaActual.add(Calendar.MONTH, meses);
		int anioActual = fechaActual.get(Calendar.YEAR);
		int mesActual = fechaActual.get(Calendar.MONTH)+1;
		String strAnioMes="";
		if(mesActual<10) {strAnioMes = anioActual+"0"+mesActual;
		}else {strAnioMes = anioActual+""+mesActual;}
		return (Long.parseLong(strAnioMes));
	}
	
	public static List<ReportGerenciales> datosHistoria(Connection con,String db, Long desdeAAMM, Long hastaAAMM, Long idGrupo) {
		List<ReportGerenciales> lista = new ArrayList<ReportGerenciales>();
		try { 
			  PreparedStatement smt = con
					  .prepareStatement("select anioMes,categoria,precioCompra,precioReposicion,precioReal,precioLista,ventaParcial,id_grupo from `"+db+"`.reportGerencial "
					  		+ " where anioMes >= ? and anioMes < ? and id_grupo = ?"+"; ");
			  smt.setLong(1, desdeAAMM);
			  smt.setLong(2, hastaAAMM);
			  smt.setLong(3, idGrupo);
			  ResultSet rs = smt.executeQuery();
			  while (rs.next()) {
				lista.add(new ReportGerenciales(rs.getLong(1),rs.getString(2),rs.getDouble(3),rs.getDouble(4),rs.getDouble(5),rs.getDouble(6),rs.getDouble(7),rs.getLong(8)));
			  }
			  rs.close();smt.close();
		} catch (SQLException e) {
				e.printStackTrace();}
		return(lista);
	}
	
	public static List<String> resumenTotalMesAnio (Connection con, String db, String desdeAAMMDD, String hastaAAMMDD, Map<Long,Double> tasas,
			List<Long> listIdBodegaEmpresa, Map<Long,Calc_BodegaEmpresa> mapBodegaEmpresa, Map<String,Calc_Precio> mapPrecios, Map<Long,Calc_Precio> mapMaestroPrecios, Map<Long,Calc_Precio> mapPreciosCompra, 
			Map<Long,BodegaEmpresa> mapBodegas, Long id_grupo, Map<Long,Long> mapDec, Map<String,ListaPrecioServicio> mapPreciosOdo, Map<Long,Long> mapIdEquipoVsIdGrupo,
			String esPorSucursal, String id_sucursal){
		
		List<ModCalc_InvInicial> inventarioInicial = ModCalc_InvInicial.resumenInvInicial(con, db, desdeAAMMDD, hastaAAMMDD, tasas, listIdBodegaEmpresa, mapBodegaEmpresa, mapPrecios, mapMaestroPrecios);
		if(id_grupo > 0) {
			List<ModCalc_InvInicial> aux = new ArrayList<ModCalc_InvInicial>();
			for(ModCalc_InvInicial x: inventarioInicial) {
				if((long)x.id_grupo == (long)id_grupo) {
					aux.add(x);
				}
			}
			inventarioInicial = aux;
		}
		
		List<ModCalc_GuiasPer> guiasPeriodo = ModCalc_GuiasPer.resumenGuiasPer(con, db, desdeAAMMDD, hastaAAMMDD, tasas, listIdBodegaEmpresa, mapBodegaEmpresa, mapPrecios, mapMaestroPrecios);
		if(id_grupo > 0) {
			List<ModCalc_GuiasPer> aux = new ArrayList<ModCalc_GuiasPer>();
			for(ModCalc_GuiasPer x: guiasPeriodo) {
				if((long)x.id_grupo == (long)id_grupo) {
					aux.add(x);
				}
			}
			guiasPeriodo = aux;
		}
		
		List<ModeloCalculo> listCalculada = ModeloCalculo.valorTotalporBodega(con, db, desdeAAMMDD, hastaAAMMDD, tasas, inventarioInicial, guiasPeriodo);
		
		List<Long> listIdGuia_fechaCorte = ModCalc_InvInicial.listIdGuia_fechaCorte(con, db, hastaAAMMDD);
		
		
		List<Inventarios> inventario = Inventarios.inventario(con, db, listIdBodegaEmpresa, listIdGuia_fechaCorte);
		if(id_grupo > 0) {
			List<Inventarios> aux = new ArrayList<Inventarios>();
			for(Inventarios x: inventario) {
				if((long)x.id_grupo == (long)id_grupo) {
					aux.add(x);
				}
			}
			inventario = aux;
		}
		
		//CALCULO ODO:
		List<VentaServicio> listVentaServicio = VentaServicio.allEntreFechas(con, db, desdeAAMMDD, hastaAAMMDD, esPorSucursal, id_sucursal);
		Map<Long,Double> mapTotalAjustePorBodega = Calc_AjustesEpOdo.mapTotalAjustePorBodega(con, db, desdeAAMMDD, hastaAAMMDD, esPorSucursal, id_sucursal);
		
		List<List<String>> resumenTotalesPorProyecto = ReportOdo.resumenTotalesPorProyecto(con, db, listVentaServicio, tasas, mapDec, mapTotalAjustePorBodega, mapBodegas, mapPreciosOdo, id_grupo, mapIdEquipoVsIdGrupo);
		Double vtaOdo = (double)0;
		for(List<String> lista: resumenTotalesPorProyecto) {
			String tot = lista.get(7).replaceAll(",", "").trim();
			if(tot.equals("")) {
				tot = "0";
			}
			vtaOdo += Double.parseDouble(tot);
		}
		//FIN ODO
			
		
		List<String> lista = new ArrayList<String>();
		
		Double totalA = (double)0;
		Double totalV = (double)0;
		Double totalCfi = (double)0;
		Double totalAVCfi = (double)0;
		
		Double totalAPL = (double)0;
		Double totalVPL = (double)0;
		Double totalAVCfiPL = (double)0;
		
		Double totalColocAPVenta = (double)0;
		Double totalColocAPCompra = (double)0;
		
		String[] fechAux = hastaAAMMDD.split("-");
		String mesAnio = "'" + fechAux[1] + "-" + fechAux[0] + "'";
		
		for(Inventarios x: inventario) {
			BodegaEmpresa bodega = mapBodegas.get(x.id_bodegaEmpresa);
			if(bodega != null && (long) bodega.esInterna == (long)2) {
				Calc_Precio pcompra = mapPreciosCompra.get(x.id_equipo);
				Calc_Precio pventa = mapPrecios.get(x.id_bodegaEmpresa+"_"+x.id_equipo+"_"+x.id_cotizacion);
				if(pcompra != null) {
					Double tasaCambio = tasas.get(pcompra.id_moneda); 
					if(tasaCambio==null) {
						tasaCambio = (double) 1;
					}
					totalColocAPCompra += x.cantidad * pcompra.precioCompra * tasaCambio;
				}
				if(pventa != null) {
					Double tasaCambio = tasas.get(pventa.id_moneda); 
					if(tasaCambio==null) {
						tasaCambio = (double) 1;
					}
					totalColocAPVenta += x.cantidad * pventa.precioVenta * tasaCambio;
				}
			}
		}
		
		for(int i=0; i<listCalculada.size(); i++){
			totalA += listCalculada.get(i).totalArriendo;
			totalV += listCalculada.get(i).totalVenta;
			totalCfi += listCalculada.get(i).totalCfi;
			totalAVCfi += listCalculada.get(i).totalTotal;
			totalAPL += listCalculada.get(i).maestroTotalArriendo;
			totalVPL += listCalculada.get(i).maestroTotalVenta;
			totalAVCfiPL += listCalculada.get(i).maestroTotalTotal;
			
		}
		
		lista.add(mesAnio);
		lista.add(totalA.longValue()+"");
		lista.add(totalV.longValue()+"");
		lista.add(totalCfi.longValue()+"");
		lista.add(totalAVCfi.longValue()+vtaOdo.longValue()+"");	// 4  venta parcial a precio real
		lista.add(totalAVCfiPL.longValue()+"");	// 5 venta parcial a precio de lista
		lista.add(totalColocAPVenta.longValue()+"");	// 6  colocacion a precio venta/reposicion
		lista.add(totalColocAPCompra.longValue()+"");	// 7 colocacion a precio compra
		return(lista);
	}
	
	public static List<String> resumenTotalMesAnioSinAjustes (Connection con, String db, String desdeAAMMDD, String hastaAAMMDD, Map<Long,Double> tasas,
			List<Long> listIdBodegaEmpresa, Map<Long,Calc_BodegaEmpresa> mapBodegaEmpresa, Map<String,Calc_Precio> mapPrecios, Map<Long,Calc_Precio> mapMaestroPrecios, Map<Long,Calc_Precio> mapPreciosCompra, 
			Map<Long,BodegaEmpresa> mapBodegas, Long id_grupo, Map<Long,Long> mapDec, Map<String,ListaPrecioServicio> mapPreciosOdo, Map<Long,Long> mapIdEquipoVsIdGrupo,
			String esPorSucursal, String id_sucursal){
		
		List<ModCalc_InvInicial> inventarioInicial = ModCalc_InvInicial.resumenInvInicial(con, db, desdeAAMMDD, hastaAAMMDD, tasas, listIdBodegaEmpresa, mapBodegaEmpresa, mapPrecios, mapMaestroPrecios);
		if(id_grupo > 0) {
			List<ModCalc_InvInicial> aux = new ArrayList<ModCalc_InvInicial>();
			for(ModCalc_InvInicial x: inventarioInicial) {
				if((long)x.id_grupo == (long)id_grupo) {
					aux.add(x);
				}
			}
			inventarioInicial = aux;
		}
		
		List<ModCalc_GuiasPer> guiasPeriodo = ModCalc_GuiasPer.resumenGuiasPer(con, db, desdeAAMMDD, hastaAAMMDD, tasas, listIdBodegaEmpresa, mapBodegaEmpresa, mapPrecios, mapMaestroPrecios);
		if(id_grupo > 0) {
			List<ModCalc_GuiasPer> aux = new ArrayList<ModCalc_GuiasPer>();
			for(ModCalc_GuiasPer x: guiasPeriodo) {
				if((long)x.id_grupo == (long)id_grupo) {
					aux.add(x);
				}
			}
			guiasPeriodo = aux;
		}
		
		List<ModeloCalculo> listCalculada = ModeloCalculo.valorTotalporBodegaSinAjustes(con, db, desdeAAMMDD, hastaAAMMDD, tasas, inventarioInicial, guiasPeriodo);
		
		List<Long> listIdGuia_fechaCorte = ModCalc_InvInicial.listIdGuia_fechaCorte(con, db, hastaAAMMDD);
		
		
		List<Inventarios> inventario = Inventarios.inventario(con, db, listIdBodegaEmpresa, listIdGuia_fechaCorte);
		if(id_grupo > 0) {
			List<Inventarios> aux = new ArrayList<Inventarios>();
			for(Inventarios x: inventario) {
				if((long)x.id_grupo == (long)id_grupo) {
					aux.add(x);
				}
			}
			inventario = aux;
		}
		
		//CALCULO ODO:
		List<VentaServicio> listVentaServicio = VentaServicio.allEntreFechas(con, db, desdeAAMMDD, hastaAAMMDD, esPorSucursal, id_sucursal);
		Map<Long,Double> mapTotalAjustePorBodega = Calc_AjustesEpOdo.mapTotalAjustePorBodega(con, db, desdeAAMMDD, hastaAAMMDD, esPorSucursal, id_sucursal);
		
		List<List<String>> resumenTotalesPorProyecto = ReportOdo.resumenTotalesPorProyecto(con, db, listVentaServicio, tasas, mapDec, mapTotalAjustePorBodega, mapBodegas, mapPreciosOdo, id_grupo, mapIdEquipoVsIdGrupo);
		Double vtaOdo = (double)0;
		for(List<String> lista: resumenTotalesPorProyecto) {
			String tot = lista.get(7).replaceAll(",", "").trim();
			if(tot.equals("")) {
				tot = "0";
			}
			vtaOdo += Double.parseDouble(tot);
		}
		//FIN ODO
			
		
		List<String> lista = new ArrayList<String>();
		
		Double totalA = (double)0;
		Double totalV = (double)0;
		Double totalCfi = (double)0;
		Double totalAVCfi = (double)0;
		
		Double totalAPL = (double)0;
		Double totalVPL = (double)0;
		Double totalAVCfiPL = (double)0;
		
		Double totalColocAPVenta = (double)0;
		Double totalColocAPCompra = (double)0;
		
		String[] fechAux = hastaAAMMDD.split("-");
		String mesAnio = "'" + fechAux[1] + "-" + fechAux[0] + "'";
		
		for(Inventarios x: inventario) {
			BodegaEmpresa bodega = mapBodegas.get(x.id_bodegaEmpresa);
			if(bodega != null && (long) bodega.esInterna == (long)2) {
				Calc_Precio pcompra = mapPreciosCompra.get(x.id_equipo);
				Calc_Precio pventa = mapPrecios.get(x.id_bodegaEmpresa+"_"+x.id_equipo+"_"+x.id_cotizacion);
				if(pcompra != null) {
					Double tasaCambio = tasas.get(pcompra.id_moneda); 
					if(tasaCambio==null) {
						tasaCambio = (double) 1;
					}
					totalColocAPCompra += x.cantidad * pcompra.precioCompra * tasaCambio;
				}
				if(pventa != null) {
					Double tasaCambio = tasas.get(pventa.id_moneda); 
					if(tasaCambio==null) {
						tasaCambio = (double) 1;
					}
					totalColocAPVenta += x.cantidad * pventa.precioVenta * tasaCambio;
				}
			}
		}
		
		for(int i=0; i<listCalculada.size(); i++){
			totalA += listCalculada.get(i).totalArriendo;
			totalV += listCalculada.get(i).totalVenta;
			totalCfi += listCalculada.get(i).totalCfi;
			totalAVCfi += listCalculada.get(i).totalTotal;
			totalAPL += listCalculada.get(i).maestroTotalArriendo;
			totalVPL += listCalculada.get(i).maestroTotalVenta;
			totalAVCfiPL += listCalculada.get(i).maestroTotalTotal;
			
		}
		
		lista.add(mesAnio);
		lista.add(totalA.longValue()+"");
		lista.add(totalV.longValue()+"");
		lista.add(totalCfi.longValue()+"");
		lista.add(totalAVCfi.longValue()+vtaOdo.longValue()+"");	// 4  venta parcial a precio real
		lista.add(totalAVCfiPL.longValue()+"");	// 5 venta parcial a precio de lista
		lista.add(totalColocAPVenta.longValue()+"");	// 6  colocacion a precio venta/reposicion
		lista.add(totalColocAPCompra.longValue()+"");	// 7 colocacion a precio compra
		return(lista);
	}
	
	
	public static void actualizaTablaReportGerencial(Connection con, String db, List<String> datos, String anioMes, Long id_grupo) {
		String categoria = anioMes.substring(4,6)+"-"+anioMes.substring(0,4);
		Double ventaParcial = Double.parseDouble(datos.get(4));
		Double ventaReal = Double.parseDouble(datos.get(4));
		Double ventaPrecioLista = Double.parseDouble(datos.get(5));
		Double precioReposicion = Double.parseDouble(datos.get(6));
		Double precioCompra = Double.parseDouble(datos.get(7));
		try { 
		  PreparedStatement smt = con
				  .prepareStatement("insert into `"+db+"`.reportGerencial "
				  		+ " (anioMes,categoria,precioCompra,precioReposicion,precioReal,precioLista,ventaParcial,id_grupo) "
				  		+ " values (?,?,?,?,?,?,?,?);");
		  smt.setLong(1, Long.parseLong(anioMes.trim()));
		  smt.setString(2, categoria.trim());
		  smt.setDouble(3, precioCompra);
		  smt.setDouble(4, precioReposicion);
		  smt.setDouble(5, ventaReal);
		  smt.setDouble(6, ventaPrecioLista);
		  smt.setDouble(7, ventaParcial);
		  smt.setLong(8, id_grupo);
		  smt.executeUpdate();
		  smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();}
	}
	
		
	
			
}
	
	
	
