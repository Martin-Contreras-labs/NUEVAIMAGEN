package models.calculo;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controllers.HomeController;
import models.reports.ReportFacturas;
import models.utilities.DatabaseRead;
import models.utilities.Fechas;


public class ModCalc_InvInicial {
	
	public Long id_bodegaEmpresa;
	public Long id_grupo;
	public Long id_equipo;
	public Long id_cotizacion;
	public Long esVenta;
	
	public Double cantidad;
	public Long dias;
	
	public String fecha_primera_guia;
	
	public Long id_moneda;
	public Double pVenta;
	public Double pArr_dia;
	public Double tasaCambio;
	public Double totalArriendo;
	public Double totalTotal;
	
	
	public Long maestroId_moneda;
	public Double maestroPVenta;
	public Double maestroPArr_dia;
	public Double maestroTasaCambio;
	public Double maestroTotalArriendo;
	public Double maestroTotalTotal;

	public ModCalc_InvInicial(Long id_bodegaEmpresa, Long id_grupo, Long id_equipo, Long id_cotizacion, Long esVenta,
			Double cantidad, Long dias, String fecha_primera_guia, Long id_moneda, Double pVenta, Double pArr_dia,
			Double tasaCambio, Double totalArriendo, Double totalTotal, Long maestroId_moneda, Double maestroPVenta,
			Double maestroPArr_dia, Double maestroTasaCambio, Double maestroTotalArriendo, Double maestroTotalTotal) {
		super();
		this.id_bodegaEmpresa = id_bodegaEmpresa;
		this.id_grupo = id_grupo;
		this.id_equipo = id_equipo;
		this.id_cotizacion = id_cotizacion;
		this.esVenta = esVenta;
		this.cantidad = cantidad;
		this.dias = dias;
		this.fecha_primera_guia = fecha_primera_guia;
		this.id_moneda = id_moneda;
		this.pVenta = pVenta;
		this.pArr_dia = pArr_dia;
		this.tasaCambio = tasaCambio;
		this.totalArriendo = totalArriendo;
		this.totalTotal = totalTotal;
		this.maestroId_moneda = maestroId_moneda;
		this.maestroPVenta = maestroPVenta;
		this.maestroPArr_dia = maestroPArr_dia;
		this.maestroTasaCambio = maestroTasaCambio;
		this.maestroTotalArriendo = maestroTotalArriendo;
		this.maestroTotalTotal = maestroTotalTotal;
	}




	public ModCalc_InvInicial() {
		super();
	}

	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	
	
	public static ReportFacturas resumenInvInicial(String db, String desdeAAMMDD, String hastaAAMMDD, Map<String, Double> mapFijaTasas, Map<Long,Double> mapTasas, 
			List<Long> listIdBodegaEmpresa, Map<Long,Calc_BodegaEmpresa> mapBodegaEmpresa, Map<String,Calc_Precio> mapPrecios, Map<Long,Calc_Precio> mapMaestroPrecios,
			List<Long> listIdGuia_fechaCorte, List<Inventarios> inventario) {
		
//		SON FUNCIONES QUE ALIMENTAN ESTA FUNCION
//		String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
//		List<Long> listIdBodegaEmpresa = ModCalc_InvInicial.listIdBodegaEmpresa(con, s.baseDato, permisoPorBodega);
//		Map<Long,Calc_BodegaEmpresa> mapBodegaEmpresa = Calc_BodegaEmpresa.mapAllBodegasVigentes(con, db);
//		Map<String,Calc_Precio> mapPrecios = Calc_Precio.mapPrecios(con, db, listIdBodegaEmpresa);
//		Map<Long,Calc_Precio> mapMaestroPrecios = Calc_Precio.mapMaestroPrecios(con, db);
		
		List<ModCalc_InvInicial> listado = new ArrayList<ModCalc_InvInicial>();
		Fechas desde = Fechas.obtenerFechaDesdeStrAAMMDD(desdeAAMMDD);
		Fechas hasta = Fechas.obtenerFechaDesdeStrAAMMDD(hastaAAMMDD);
		
		
		List<Long> listIdGuia_entreFechas =  new ArrayList<Long>();
		Fechas hastaAjustar = new Fechas();
		Map<Long, List<Inventarios>> mapGuiasPer = new HashMap<Long, List<Inventarios>>();
		try {
			DatabaseRead dbRead = HomeController.dbRead;
			Connection con = dbRead.getConnection();
				Fechas desdeAjustar = Fechas.obtenerFechaDesdeStrAAMMDD(desdeAAMMDD);
				hastaAjustar = Fechas.obtenerFechaDesdeStrAAMMDD(hastaAAMMDD);
				String deAjustado = Fechas.addMeses(desdeAjustar.getFechaCal(), -1).getFechaStrAAMMDD();
				String aAjustado = Fechas.addMeses(hastaAjustar.getFechaCal(), -1).getFechaStrAAMMDD();
				listIdGuia_entreFechas = ModCalc_GuiasPer.listIdGuia_entreFecha(con, db, deAjustado, aAjustado);
				mapGuiasPer = Inventarios.guiasPerAllBodegas(con, db, listIdGuia_entreFechas);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		Map<String, Long> mapIdEquiposConAjuste = new HashMap<String,Long>(); // map bodega VS list<idguia_dias>
		
		for(int i=0; i<inventario.size(); i++) {
			
			Map<Long,Double> tasas = new HashMap<Long,Double>();
			for (Map.Entry<Long, Double> entry : mapTasas.entrySet()) {
    	  		Long k = entry.getKey();
    	  		Double v= entry.getValue();
				Double aux = mapFijaTasas.get(inventario.get(i).id_bodegaEmpresa + "_" +k);
				if(aux != null) {
					tasas.put(k, aux);
				}else {
					tasas.put(k, v);
				}
			}
			
			if((long) inventario.get(i).esVenta == (long) 0 && (double) inventario.get(i).cantidad > (double) 0) {
			
				Long diasPeriodo = (long) Fechas.diasEntreFechas(desde.getFechaCal(), hasta.getFechaCal())+1;
				if(diasPeriodo<0) diasPeriodo = (long) 0;
				Long baseCalculo = (long) 1;
				Calc_BodegaEmpresa bodegaEmpresa = mapBodegaEmpresa.get(inventario.get(i).id_bodegaEmpresa);
				if(bodegaEmpresa!=null) { baseCalculo = bodegaEmpresa.baseCalculo; }else { baseCalculo = (long) 1; }
				if(baseCalculo==2) { diasPeriodo = ModCalc_InvInicial.diasPeriodoBase30(desdeAAMMDD, diasPeriodo);}
				
				
				//obtiene precios de cada bodega
				String keyPrecio = inventario.get(i).id_bodegaEmpresa.toString()+"_"+inventario.get(i).id_equipo.toString()+"_"+inventario.get(i).id_cotizacion.toString();
				Calc_Precio precios = mapPrecios.get(keyPrecio);
				Double precioVenta =  (double) 0, pArr_dia =  (double) 0, tasaCambio = (double) 0, totalArriendo = (double) 0;
				Long id_moneda = (long) 1;
				if(precios!=null) {
					precioVenta = precios.precioVenta;
					pArr_dia = precios.precioArriendo_dia;
					tasaCambio = tasas.get(precios.id_moneda);
					if(tasaCambio==null) {
						tasaCambio = (double) 1;
					}
					id_moneda = precios.id_moneda;
				}
				
				//obtiene precios del maestro de precios
				Calc_Precio maestroPrecios = mapMaestroPrecios.get(inventario.get(i).id_equipo);
				Double maestroPrecioVenta =  (double) 0, maestroPArr_dia =  (double) 0, maestroTasaCambio =  (double) 0, maestroTotalArriendo = (double) 0;
				Long maestroId_moneda = (long) 1;
				if(maestroPrecios!=null) {
					maestroPrecioVenta = maestroPrecios.precioVenta;
					maestroPArr_dia = maestroPrecios.precioArriendo_dia;
					maestroTasaCambio = tasas.get(maestroPrecios.id_moneda); 
					if(maestroTasaCambio==null) {
						maestroTasaCambio = (double) 1;
					}
					maestroId_moneda = maestroPrecios.id_moneda;
				}
				
				// HACE EL CALCULO
				if((long) inventario.get(i).esVenta == (long) 0 ) {
					

					// AJUSTES POR SALDOS DIAS DE GRACIA
							Long nDiaGraciaEnvio = (long) 0;
							if(bodegaEmpresa!=null) { 
								nDiaGraciaEnvio = bodegaEmpresa.nDiaGraciaEnvio;
							} else {
								baseCalculo = (long) 1;
								nDiaGraciaEnvio = (long) 0;
							}
							Double ajustePorGracia = (double)0;
							Double ajustePorGraciaMaestro = (double)0;
							
							
							if(nDiaGraciaEnvio > 0 && (long) inventario.get(i).esVenta == (long) 0) {
								List<Inventarios> guiasPer = mapGuiasPer.get(inventario.get(i).id_bodegaEmpresa);
								if(guiasPer!=null) {
									for(int k=0; k<guiasPer.size(); k++) {
										if(		(long) guiasPer.get(k).id_equipo == (long) inventario.get(i).id_equipo 
												&& (long) guiasPer.get(k).id_bodegaEmpresa == (long) inventario.get(i).id_bodegaEmpresa
												&& (long) guiasPer.get(k).id_cotizacion == (long) inventario.get(i).id_cotizacion )
										{
											Fechas fechaGuia = Fechas.obtenerFechaDesdeStrAAMMDD(guiasPer.get(k).fechaGuia);
											Long diasGuia = (long) Fechas.diasEntreFechas(fechaGuia.getFechaCal(), hastaAjustar.getFechaCal());
											if(guiasPer.get(k).id_tipoMovimiento == 1) {
												diasGuia = diasGuia - nDiaGraciaEnvio + 1;
												if(diasGuia < 0 ) {
													ajustePorGracia += guiasPer.get(k).cantidad * diasGuia * pArr_dia * tasaCambio;
													ajustePorGraciaMaestro += guiasPer.get(k).cantidad * diasGuia * pArr_dia * maestroTasaCambio;
													mapIdEquiposConAjuste.put(guiasPer.get(k).id_bodegaEmpresa+"_"+guiasPer.get(k).id_equipo+"_"+guiasPer.get(k).id_cotizacion, diasGuia*-1);
												}
											}
										}
									}
								}
							}
					// FIN AJUSTES
							
					
					totalArriendo = inventario.get(i).cantidad * (diasPeriodo) * pArr_dia * tasaCambio + ajustePorGracia;
					maestroTotalArriendo = inventario.get(i).cantidad * (diasPeriodo) * maestroPArr_dia * maestroTasaCambio + ajustePorGraciaMaestro;
				}
				
				ModCalc_InvInicial aux = new ModCalc_InvInicial();
				aux.id_bodegaEmpresa = inventario.get(i).id_bodegaEmpresa;
				aux.id_grupo = inventario.get(i).id_grupo;
				aux.id_equipo = inventario.get(i).id_equipo;
				aux.id_cotizacion = inventario.get(i).id_cotizacion;
				aux.esVenta = inventario.get(i).esVenta;
				aux.cantidad = inventario.get(i).cantidad;
				aux.dias = diasPeriodo;
				
				aux.pArr_dia = pArr_dia;
				aux.tasaCambio = tasaCambio;
				aux.totalArriendo = totalArriendo;
				aux.id_moneda = id_moneda;
				aux.pVenta = precioVenta;
				aux.totalTotal = totalArriendo;
				
				aux.maestroPArr_dia = maestroPArr_dia;
				aux.maestroTasaCambio = maestroTasaCambio;
				aux.maestroTotalArriendo = maestroTotalArriendo;
				aux.maestroId_moneda = maestroId_moneda;
				aux.maestroPVenta = maestroPrecioVenta;
				aux.maestroTotalTotal = maestroTotalArriendo;
				listado.add(aux);
			}
			
			
		}
		
		ReportFacturas reporte = new ReportFacturas(mapIdEquiposConAjuste,listado);
		
		return(reporte);
	}
	
	
	
	
	public static Long diasPeriodoBase30 (String desdeAAMMDD, Long diasPeriodo) {
		String[] mes = desdeAAMMDD.split("-");
		if(!mes[1].equals("02") && diasPeriodo==31) diasPeriodo=(long)30;
		if(mes[1].equals("02") && (diasPeriodo==28||diasPeriodo==29))  diasPeriodo=(long)30;
		if(diasPeriodo>31) {
			Long diasDePaso=(long)0;
			Double factorMeses=(double) diasPeriodo/30;
			Long jPart = factorMeses.longValue();
			Double fPart = factorMeses - jPart;
			diasPeriodo=(long)0;
			Long auxMes= Long.parseLong(mes[1].trim());
			for(int j=0;j<jPart;j++) {
				if(auxMes==2)  diasDePaso=(long)28;
				if(auxMes==1||auxMes==3||auxMes==5||auxMes==7||auxMes==8||auxMes==10||auxMes==12)  diasDePaso=(long)30;
				if(auxMes==4||auxMes==6||auxMes==9||auxMes==11)  diasDePaso=(long)30;
				diasPeriodo=diasPeriodo+diasDePaso;
			}
			fPart=fPart*30; 
			diasPeriodo=diasPeriodo+fPart.longValue();
		}
		return diasPeriodo;
	}
	
	public static List<Long> listIdBodegaEmpresa (Connection con, String db, String permisoPorBodega){
		// ZZZZZZZZZZZZZZZ AGREGAR FILTRO DE BODEGA si id_bodega > 0 aplicar filtro ojo con permisoPorBodega + " and id = 104;"
		List<Long> lista = new ArrayList<Long>();
		permisoPorBodega = permisoPorBodega.replaceAll("movimiento", "bodegaEmpresa").replaceAll("id_bodegaEmpresa", "id");
		try {
			PreparedStatement smt = con
					.prepareStatement(" select id from `"+db+"`.bodegaEmpresa where esInterna = 2 and vigente = 1 " + permisoPorBodega + ";"); //" and id = 900;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				lista.add(rs.getLong(1));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return lista;
	}
	
	public static List<Long> listIdGuia_fechaCorte (Connection con, String db, String fechaAAMMDD){
		fechaAAMMDD  = Fechas.AAMMDD(fechaAAMMDD);
		List<Long> lista = new ArrayList<Long>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select id from `"+db+"`.guia where fecha <  '" + fechaAAMMDD + "';");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				lista.add(rs.getLong(1));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return lista;
	}
	
	public static Map<String,String> mapFecha_primera_guiaDeCoti (Connection con, String db, String fechaAAMMDD, Long id_bodegaEmpresa){
		fechaAAMMDD  = Fechas.AAMMDD(fechaAAMMDD);
		Map<String,String> map = new HashMap<String,String>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select movimiento.id_equipo, min(guia.fecha), movimiento.id_cotizacion "
							+ " from `"+db+"`.movimiento "
							+ " left Join `"+db+"`.guia on guia.id = movimiento.id_guia "
							+ " where guia.fecha <  '" + fechaAAMMDD + "' and movimiento.id_bodegaEmpresa = '"+id_bodegaEmpresa.toString()+"' "
							+ "  group by movimiento.id_equipo, movimiento.id_cotizacion;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				map.put(rs.getString(1)+"_"+rs.getString(3),rs.getString(2));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return map;
	}
	
	
	
	
	
	
	
}
	
	
	
