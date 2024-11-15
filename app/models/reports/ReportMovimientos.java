package models.reports;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.TempFile;

import models.calculo.Inventarios;
import models.calculo.ModCalc_GuiasPer;
import models.tables.AjustesEP;
import models.tables.BodegaEmpresa;
import models.tables.Cotizacion;
import models.tables.Equipo;
import models.tables.ListaPrecio;
import models.tables.Moneda;
import models.tables.OtDespachado;
import models.tables.Proyecto;
import models.tables.TipoEstado;
import models.tables.UnidadTiempo;
import models.utilities.Archivos;
import models.utilities.DecimalFormato;
import models.utilities.Fechas;


public class ReportMovimientos {
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble0 = new DecimalFormat("#,##0");
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble4 = new DecimalFormat("#,##0.0000");
	
	static SimpleDateFormat myformatfecha = new SimpleDateFormat("dd-MM-yyyy");
	
	static DecimalFormat myformatMoneda = new DecimalFormat("#,##0");
		
	
	
	public static List<List<String>> movimientoGuiasAgrupado(Connection con, String db, Long id_bodegaEmpresa, String esVenta, String fechaDesde, String fechaHasta) {
		List<List<String>> lista = new ArrayList<List<String>>();
		esVenta = esVenta.trim();
		Double granTotalArriendo = (double)0;
		Map<String,Double> excedentes = ReportExcedentes.totalExcedentesPorCodyBod(con, db, id_bodegaEmpresa);
		
		try {
			
			PreparedStatement smt1 = con
					.prepareStatement(" select  distinct " +
							" guia.numero, " +
							" concat(day(guia.fecha),'/',month(guia.fecha),'/',year(guia.fecha)),  " +   
							" tipoMovimiento.nombre, " +
							" guia.fecha, " +
							" ifnull(guia.numGuiaCliente,'') "+
							" from `"+db+"`.movimiento " +
							" left join `"+db+"`.guia on guia.id = movimiento.id_guia " +
							" left join `"+db+"`.tipoMovimiento on tipoMovimiento.id = id_tipoMovimiento  " +
							" where id_bodegaEmpresa =  ? and numero is not null " +
							" and movimiento.esVenta= ?  "
							+ " and (guia.fecha between ? and ?) order by guia.fecha,guia.numero;");
			smt1.setLong(1, id_bodegaEmpresa);
			smt1.setString(2, esVenta);
			smt1.setString(3, fechaDesde);
			smt1.setString(4, fechaHasta);
			

			ResultSet rs1 = smt1.executeQuery();
			
			List<String> numGuia = new ArrayList<String>();
			List<String> fechGuia = new ArrayList<String>();
			List<String> guiaClie = new ArrayList<String>();
			List<String> tipGuia = new ArrayList<String>();
			List<String> blanco = new ArrayList<String>();
				
			numGuia.add(" ");
			fechGuia.add(" ");
			guiaClie.add(" ");
			tipGuia.add(" ");
			blanco.add("Grupo");
			
			numGuia.add(" ");
			fechGuia.add(" ");
			guiaClie.add(" ");
			tipGuia.add(" ");
			blanco.add("Código");
			
			numGuia.add(" ");
			fechGuia.add(" ");
			guiaClie.add(" ");
			tipGuia.add(" ");
			blanco.add("Equipo");
			
			numGuia.add(" ");
			fechGuia.add(" ");
			guiaClie.add(" ");
			tipGuia.add(" ");
			blanco.add("kg");
			
			numGuia.add("Nro.Mov:");
			fechGuia.add("Fecha:");
			guiaClie.add("Nro.Ref");
			tipGuia.add("Tipo.Mov: ");
			blanco.add("m2");
				
			Fechas desde = Fechas.obtenerFechaDesdeStrAAMMDD(fechaDesde);
			Fechas hasta = Fechas.obtenerFechaDesdeStrAAMMDD(fechaHasta);
			Fechas hastaAux = Fechas.obtenerFechaDesdeStrAAMMDD(fechaHasta);
			Calendar hastaMas1 = hastaAux.getFechaCal();
			hastaMas1.add(Calendar.DAY_OF_MONTH, 1);
				
			numGuia.add("STOCK INICIAL");
				String aux2[] = fechaDesde.split("-");
			fechGuia.add(""+aux2[2]+"/"+aux2[1]+"/"+aux2[0]);
			guiaClie.add("");
			tipGuia.add(" ");
			blanco.add(" ");
			
			while (rs1.next()) {
				Fechas fechaGuia = Fechas.obtenerFechaDesdeStrAAMMDD(rs1.getString(4));
				if(!(fechaGuia.fechaCal.before(desde.fechaCal)||fechaGuia.fechaCal.after(hasta.fechaCal))) {
					numGuia.add(rs1.getString(1));
					fechGuia.add(rs1.getString(2));
					guiaClie.add(rs1.getString(5));
					tipGuia.add(rs1.getString(3));
					blanco.add(" ");
				}
			}
			rs1.close();
			smt1.close();
			
			numGuia.add("STOCK FINAL");
				String aux3[] = fechaHasta.split("-");
			fechGuia.add(""+aux3[2]+"/"+aux3[1]+"/"+aux3[0]);
			guiaClie.add(" ");
			tipGuia.add(" ");
			blanco.add("");
			numGuia.add("Excedentes");
			fechGuia.add(" ");
			guiaClie.add(" ");
			tipGuia.add(" ");
			blanco.add("");
				
			PreparedStatement smt2 = con
					.prepareStatement(" select distinct  guia.numero,guia.fecha " +
							" from `"+db+"`.guia " +
							" left join `"+db+"`.movimiento on movimiento.id_guia = guia.id " +
							" where movimiento.esVenta= ?  and id_bodegaEmpresa = ? "+
							" order by guia.fecha,guia.numero; ");
			smt2.setString(1, esVenta);
			smt2.setLong(2, id_bodegaEmpresa);
			
			ResultSet rs2 = smt2.executeQuery();
			List<List<String>> listaGuias = new ArrayList<List<String>>();
			while (rs2.next()) {
				List<String> aux = new ArrayList<String>();
				aux.add(rs2.getString(1));
				aux.add(rs2.getString(2));
				listaGuias.add(aux);
			}
			rs2.close();
			smt2.close();
				
			PreparedStatement smt3 = con
					.prepareStatement(" select distinct " +
							" grupo.nombre, " +
							" equipo.codigo, " +
							" equipo.nombre, " +
							" ifnull(moneda.nickName,''), " + 
							" ifnull(listaPrecio.precioVenta,0), " + 
							" ifnull(listaPrecio.precioReposicion,0), " + 
							" ifnull(listaPrecio.precioArriendo,0), " + 
							" ifnull(listaPrecio.id_unidadTiempo,1), " +
							" ifnull(bodegaEmpresa.tasaDescto,0), " +
							" ifnull(dctoGrupo.tasaDescto,0), " +
							" ifnull(dctoEquipo.tasaDescto,0), " +
							" 0, " +
							" 0, " +
							" equipo.id, " +
							" equipo.kg, " +
							" equipo.m2 " +
							" from `"+db+"`.movimiento " + 
							" left join `"+db+"`.cotizacion on cotizacion.id = movimiento.id_cotizacion " +
							" left join `"+db+"`.equipo on equipo.id=movimiento.id_equipo " + 
							" left join `"+db+"`.listaPrecio on "
									+ "listaPrecio.id_equipo = equipo.id "
									+ "and listaPrecio.id_cotizacion = movimiento.id_cotizacion "
									+ "and listaPrecio.id_bodegaEmpresa = " +id_bodegaEmpresa +
							" left join `"+db+"`.grupo on grupo.id = equipo.id_grupo  " + 
							" left join `"+db+"`.moneda on moneda.id = listaPrecio.id_moneda " + 
							" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa " +
							" left join `"+db+"`.dctoGrupo on dctoGrupo.id_bodegaEmpresa = movimiento.id_bodegaEmpresa and dctoGrupo.id_grupo = equipo.id_grupo " +
							" left join `"+db+"`.dctoEquipo on dctoEquipo.id_bodegaEmpresa = movimiento.id_bodegaEmpresa and dctoEquipo.id_equipo = movimiento.id_equipo " +
							" where " + 
							" movimiento.id_bodegaEmpresa=? "+ 
							" and equipo.nombre is not null and movimiento.esVenta= ? "+
							" order by grupo.nombre,equipo.nombre;");
			
			smt3.setLong(1, id_bodegaEmpresa);
			smt3.setString(2, esVenta);
			
			ResultSet rs3 = smt3.executeQuery();
			List<List<String>> listaCodigos = new ArrayList<List<String>>();
			
			Map<Long,Double> mapUnidadTiempo = UnidadTiempo.equivalencia(con, db);
			
			while (rs3.next()) {
				List<String> aux = new ArrayList<String>();
				Double factor = mapUnidadTiempo.get(rs3.getLong(8));
				if(factor == null){
					factor = (double)1;
				}
				aux.add(rs3.getString(1)); //grupo
				aux.add(rs3.getString(2));  //codigo
				aux.add(rs3.getString(3));  //equipo
				aux.add(rs3.getString(4));  //moneda
				aux.add(myformatdouble2.format(rs3.getDouble(5))); //precioventa
				Double tasa=(double)0;
				Double tasaDcto = 1-((1-rs3.getDouble(9))*(1-rs3.getDouble(10))*(1-rs3.getDouble(11)));
				if(rs3.getDouble(7)>0&&rs3.getDouble(5)>0) tasa=((rs3.getDouble(7)*30/factor*(1-tasaDcto))/rs3.getDouble(5))*100;
				if(esVenta.equals("1")) {
					aux.add("0 %"); //tasaArriendo
					aux.add("0"); // arriendo mes
					aux.add("0");  //arriendo dia
				}else {
					aux.add(myformatdouble2.format(tasa)+" %"); //tasaArriendo
					aux.add(myformatdouble2.format(rs3.getDouble(7)*30/factor*(1-tasaDcto))); // arriendo mes
					aux.add(myformatdouble4.format(rs3.getDouble(7)/factor*(1-tasaDcto)));  //arriendo dia
				}
				aux.add(rs3.getString(12)); //  8 id cotizacion
				aux.add(rs3.getString(13)); //  9 numero coti
				aux.add(rs3.getString(14)); //  10 idEquipo
				aux.add(rs3.getString(15)); //  11 kg
				aux.add(rs3.getString(16)); //  12 m2
				listaCodigos.add(aux);
			}
			rs3.close();
			smt3.close();
				
			PreparedStatement smt4 = con
					.prepareStatement(" select " +
							" equipo.codigo,    " +
							" guia.numero, " +
							" sum(if(movimiento.id_tipoMovimiento=2,movimiento.cantidad*-1,movimiento.cantidad)),   " +
							" movimiento.id_cotizacion "+
							" from `"+db+"`.movimiento " +
							" left join `"+db+"`.equipo on equipo.id = movimiento.id_equipo " +
							" left join `"+db+"`.guia on guia.id = movimiento.id_guia " +
							" where movimiento.id_bodegaEmpresa =  ? "+  
							" and guia.fecha is not null  and movimiento.esVenta= ?  " +
							" group by equipo.codigo, guia.numero  " + 
							" order by guia.fecha,guia.numero,equipo.codigo;");
			smt4.setLong(1, id_bodegaEmpresa);
			smt4.setString(2, esVenta);
			
			ResultSet rs4 = smt4.executeQuery();
			Map<String,String> codGuiaCant = new HashMap<String,String>();
			while (rs4.next()) {
				codGuiaCant.put(rs4.getString(1).trim()+"_"+rs4.getString(2).trim(), rs4.getString(3));
			}
			rs4.close();
			smt4.close();
			
			lista.add(numGuia);
			lista.add(fechGuia);
			lista.add(guiaClie);
			lista.add(tipGuia);
			lista.add(blanco);
				
			Map<Long,BodegaEmpresa> mapBodegaEmpresa = BodegaEmpresa.mapAll(con, db);
				
			String auxiliarDeReparacion="";
			
			Fechas hastaAjustar = new Fechas();
			Fechas desdeAjustar = Fechas.obtenerFechaDesdeStrAAMMDD(fechaDesde);
			hastaAjustar = Fechas.obtenerFechaDesdeStrAAMMDD(fechaHasta);
			String deAjustado = Fechas.addMeses(desdeAjustar.getFechaCal(), -6).getFechaStrAAMMDD();
			String aAjustado = Fechas.addMeses(hastaAjustar.getFechaCal(), -1).getFechaStrAAMMDD();
			List<Long> listIdGuia_entreFechas = ModCalc_GuiasPer.listIdGuia_entreFecha(con, db, deAjustado, aAjustado);
			
			Map<Long, List<Inventarios>> mapGuiasPer = Inventarios.guiasPerAllBodegas(con, db, listIdGuia_entreFechas);
			
			for(int i=0;i<listaCodigos.size();i++){
				
				if(!auxiliarDeReparacion.equals(listaCodigos.get(i).get(0)+listaCodigos.get(i).get(9)+listaCodigos.get(i).get(1)+listaCodigos.get(i).get(2))) {
					
					auxiliarDeReparacion=listaCodigos.get(i).get(0)+listaCodigos.get(i).get(9)+listaCodigos.get(i).get(1)+listaCodigos.get(i).get(2);
				
					List<String> aux = new ArrayList<String>();
					aux.add(listaCodigos.get(i).get(0)); //grupo
					aux.add(listaCodigos.get(i).get(1)); //codigo
					aux.add(listaCodigos.get(i).get(2)); //equipo
					
					// kg por unidad
					Double kg = Double.parseDouble(listaCodigos.get(i).get(11).trim());
					if(kg > 0) {
						aux.add(myformatdouble2.format(kg));
					}else {
						aux.add(""); 
					}
					// m2 por unidad
					Double m2 =  Double.parseDouble(listaCodigos.get(i).get(12).trim());
					if(m2 > 0) {
						aux.add(myformatdouble2.format(m2));
					}else {
						aux.add("");
					}
						
					Double cantStockIni = (double) 0;
					Double cantStockFin=(double)0;
					Double arriendo = (double) 0;
					Long totalDias = (long)0;
					Long dias = (long)0;
						
					dias = Math.round( (double) (hasta.fechaCal.getTimeInMillis() - desde.fechaCal.getTimeInMillis())  /  (24 * 60 * 60 * 1000)  ) + 1;
					
					BodegaEmpresa bodega = mapBodegaEmpresa.get(id_bodegaEmpresa);
					Long baseCalculo = bodega.baseCalculo;
					if(baseCalculo == null) {
						baseCalculo = (long) 1;
					}
						
					if((long)baseCalculo == (long)2) {
						String[] mes = fechaDesde.split("-");
						
						if(!mes[1].equals("02") && (long)dias == (long)31) {
							dias=(long)30;
						}
						if(mes[1].equals("02") && ((long)dias == (long)28 || (long)dias == (long)29)) {
							dias=(long)30;
						}
						
						if((long)dias > (long)31) {
							Long diasDePaso=(long)0;
							Double factorMeses=(double) dias/30;
							Long iPart = factorMeses.longValue();
							Double fPart = factorMeses - iPart;
							
							dias=(long)0;
							Long auxMes= Long.parseLong(mes[1].trim());
							for(int j=0;j<iPart;j++) {
								if(auxMes==2)  diasDePaso=(long)28;
								if(auxMes==1||auxMes==3||auxMes==5||auxMes==7||auxMes==8||auxMes==10||auxMes==12)  diasDePaso=(long)30;
								if(auxMes==4||auxMes==6||auxMes==9||auxMes==11)  diasDePaso=(long)30;
								dias=dias+diasDePaso;
							}
							fPart=fPart*30; 
							dias=dias+fPart.longValue();
						}
					}
						
					for(int k=0;k<listaGuias.size();k++){
						Fechas fechaGuia = Fechas.obtenerFechaDesdeStrAAMMDD(listaGuias.get(k).get(1));
						if(fechaGuia.fechaCal.before(desde.fechaCal)){
							String keyAux = listaCodigos.get(i).get(1).trim()+"_"+listaGuias.get(k).get(0).trim();
							String cantidad = codGuiaCant.get(keyAux);
							if(cantidad != null){
								cantStockIni = cantStockIni + Double.parseDouble(cantidad.trim());
							}
						}
						if(fechaGuia.fechaCal.before(hastaMas1)){
							String cantidad = codGuiaCant.get(listaCodigos.get(i).get(1).trim()+"_"+listaGuias.get(k).get(0).trim());
							if(cantidad!=null){
								cantStockFin = cantStockFin + Double.parseDouble(cantidad.trim());
							}
						}
					}
					
					aux.add(myformatdouble2.format(cantStockIni));
					
					String auxNum = listaCodigos.get(i).get(4).trim();
	 	   			if(auxNum == null || auxNum.trim().length() <= 0) {
	 	   				auxNum = "0";
	 	   			}
	 	   			
					Double precioVenta=(double)0;
					try {
						precioVenta  = myformatdouble.parse(auxNum).doubleValue();
					}catch(Exception e) {};
					
					auxNum = listaCodigos.get(i).get(7).trim();
	 	   			if(auxNum == null || auxNum.trim().length()<=0) {
	 	   				auxNum = "0";
	 	   			}
	 	   			
					Double precioDia=(double)0;
					try {
						precioDia  = myformatdouble.parse(auxNum).doubleValue();
					}catch(Exception e) {};
					
					if(esVenta.equals("0")) {
						
						// AJUSTES POR SALDOS DIAS DE GRACIA
						Long nDiaGraciaEnvio = bodega.getnDiaGraciaEnvio();
							Double ajustePorGracia = (double)0;
							if(nDiaGraciaEnvio > 0) {
								
								List<Inventarios> guiasPer = mapGuiasPer.get(bodega.getId());
								
								for(int k=0; guiasPer!=null && k<guiasPer.size(); k++) {
									String idEquipo = listaCodigos.get(i).get(10);
									String idCotizacion = listaCodigos.get(i).get(8);
									if(	(long) guiasPer.get(k).id_equipo == Long.parseLong(idEquipo)
											&& (long) guiasPer.get(k).id_bodegaEmpresa == (long) bodega.getId()
											&& (long) guiasPer.get(k).id_cotizacion == Long.parseLong(idCotizacion) )
									{
										Fechas fechaGuia = Fechas.obtenerFechaDesdeStrAAMMDD(guiasPer.get(k).fechaGuia);
										Long diasGuia = (long) Fechas.diasEntreFechas(fechaGuia.getFechaCal(), hastaAjustar.getFechaCal());
										if(guiasPer.get(k).id_tipoMovimiento == 1) {
											diasGuia = diasGuia - nDiaGraciaEnvio + 1;
											if(diasGuia < 0 ) {
												ajustePorGracia += guiasPer.get(k).cantidad * diasGuia * precioDia ;
											}
										}
									}
								}
							}
						// FIN AJUSTES
							
							arriendo = cantStockIni * dias * precioDia + ajustePorGracia;
						
					}else {
						arriendo = (double)0;
					}
					
					totalDias=totalDias+dias;
					Double cantDeDespachos=(double)0;
						
					for(int k=0;k<listaGuias.size();k++){
						Fechas fechaGuia = Fechas.obtenerFechaDesdeStrAAMMDD(listaGuias.get(k).get(1));
						if(!(fechaGuia.fechaCal.before(desde.fechaCal)||fechaGuia.fechaCal.after(hasta.fechaCal))) {
							String keyAux=listaCodigos.get(i).get(1).trim()+"_"+listaGuias.get(k).get(0).trim();
							String cantidad = codGuiaCant.get(keyAux);
							
							if(cantidad==null){
								aux.add(" ");
							}else{
								aux.add(cantidad);
								Long diasPeriodo = (long)0;
								BodegaEmpresa bodegaEmpresa = mapBodegaEmpresa.get(id_bodegaEmpresa); 
								Long nDiaGraciaRegreso = bodegaEmpresa.getnDiaGraciaRegreso();
								Long nDiaGraciaEnvio = bodegaEmpresa.getnDiaGraciaEnvio();
								Long tratoDevoluciones = bodegaEmpresa.getTratoDevoluciones();
								Long cobraDiaDespacho = bodega.getCobraDiaDespacho();
								if(nDiaGraciaRegreso == null) {
									nDiaGraciaRegreso = (long)0;
								}
								if(nDiaGraciaEnvio == null) {
									nDiaGraciaEnvio = (long)0;
								}
								if(tratoDevoluciones == null) {
									tratoDevoluciones = (long)0;
								}
								if(cobraDiaDespacho == null) {
									cobraDiaDespacho = (long)1;
								}
								if(Double.parseDouble(cantidad.trim())>0) {
									cantDeDespachos = cantDeDespachos + Double.parseDouble(cantidad.trim());
									diasPeriodo = Math.round ( (double) (hasta.fechaCal.getTimeInMillis() - fechaGuia.fechaCal.getTimeInMillis() ) / (24 * 60 * 60 * 1000) );
									diasPeriodo = diasPeriodo + cobraDiaDespacho-nDiaGraciaEnvio;
									if(diasPeriodo < 0) {
										diasPeriodo = (long)0;
									}
								}else {
									diasPeriodo = Math.round ( (double) ( hasta.fechaCal.getTimeInMillis() - fechaGuia.fechaCal.getTimeInMillis() )/(24 * 60 * 60 * 1000));
									diasPeriodo = diasPeriodo + nDiaGraciaRegreso;
									if(tratoDevoluciones==2) diasPeriodo=diasPeriodo-1;
									if(diasPeriodo<0) {
										diasPeriodo=(long)0;
									}
								}
								
								if((long)baseCalculo == (long)2) {
									String[] mes = fechaDesde.split("-");
									if(!mes[1].equals("02")&&diasPeriodo==31) diasPeriodo=(long)30;
									if(diasPeriodo>31) {
										Long diasDePaso=(long)0;
										Double factorMeses=(double) diasPeriodo/30;
										Long iPart = factorMeses.longValue();
										Double fPart = factorMeses - iPart;
										
										diasPeriodo=(long)0;
										Long auxMes= Long.parseLong(mes[1].trim());
										for(int j=0;j<iPart;j++) {
											if(auxMes==2)  diasDePaso=(long)28;
											if(auxMes==1||auxMes==3||auxMes==5||auxMes==7||auxMes==8||auxMes==10||auxMes==12)  diasDePaso=(long)30;
											if(auxMes==4||auxMes==6||auxMes==9||auxMes==11)  diasDePaso=(long)30;
											diasPeriodo=diasPeriodo+diasDePaso;
										}
										fPart=fPart*30; 
										diasPeriodo=diasPeriodo+fPart.longValue();
									}
									if(Double.parseDouble(cantidad.trim())<0 && (mes[1].equals("01")||mes[1].equals("03")||mes[1].equals("05")||mes[1].equals("07")||mes[1].equals("08")||mes[1].equals("10")||mes[1].equals("12"))) {
										diasPeriodo=diasPeriodo-1;
									}
									if(Double.parseDouble(cantidad.trim())<0 && (mes[1].equals("02"))) {
										Fechas fecha = Fechas.obtenerFechaDesdeStrAAMMDD(fechaDesde);
										int diasMes = fecha.getFechaCal().getActualMaximum(Calendar.DAY_OF_MONTH);
										if(diasMes==28) { diasPeriodo=diasPeriodo+2; }else { diasPeriodo=diasPeriodo+1; }
										
									}
									
								}
								if(esVenta.equals("0")) {
									arriendo = arriendo + Double.parseDouble(cantidad.trim()) *diasPeriodo * precioDia;
								}else {
									arriendo = arriendo + Double.parseDouble(cantidad.trim()) * precioVenta;
								}
								
							}
							
						}
					}
						
					
					
					aux.add(myformatdouble2.format(cantStockFin));
					
					Double pventa = (double)0;
					auxNum = listaCodigos.get(i).get(4).trim();
	 	   			if(auxNum==null || auxNum.trim().length()<=0) {
	 	   				auxNum = "0";
	 	   			}
					try {
						pventa=myformatdouble.parse(auxNum).doubleValue();
					}catch(Exception e) {}
					
					Double cfi = (double)0;
					
					auxNum = bodega.tasaCfi.toString().trim();
	 	   			if(auxNum==null || auxNum.trim().length()<=0) {
	 	   				auxNum = "0";
	 	   			}
					try {
						cfi=myformatdouble.parse(auxNum).doubleValue();
					}catch(Exception e) {}
					Double totalCfi=cantDeDespachos*pventa*cfi/100;
					if(esVenta.equals("1")||totalCfi<0) {
						totalCfi=(double)0;
					}
					granTotalArriendo=granTotalArriendo+arriendo+totalCfi;
						
					Double auxExcedente = excedentes.get(aux.get(1)); 
					if(auxExcedente==null) {
						auxExcedente = (double)0;
						aux.add("");
					}else {
						aux.add(myformatdouble.format(auxExcedente));
					}
					
					if(auxExcedente!=0 || cantStockFin!=0 || cantStockIni!=0 || cantDeDespachos!=0) {
						lista.add(aux);
					}
					
						
				}
			}
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static File movimientosExcelAgrupado(String db, List<List<String>> datos, Map<String,String> mapDiccionario, BodegaEmpresa bodega, String concepto, String fechaDesde, String fechaHasta) {

   		File tmp = TempFile.createTempFile("tmp","null");
		
		try {
			String path = "formatos/excel.xlsx";
			InputStream formato = Archivos.leerArchivo(path);
            Workbook libro = WorkbookFactory.create(formato);
            formato.close();
            
            // 0 negro 1 blanco 2 rojo 3 verde 4 azul 5 amarillo 19 celeste
            CellStyle titulo = libro.createCellStyle();
            Font font = libro.createFont();
            font.setBoldweight(Font.BOLDWEIGHT_BOLD);
            font.setColor((short)4);
            font.setFontHeight((short)(14*20));
            titulo.setFont(font);
            
            CellStyle subtitulo = libro.createCellStyle();
            Font font2 = libro.createFont();
            font2.setBoldweight(Font.BOLDWEIGHT_BOLD);
            font2.setColor((short)0);
            font2.setFontHeight((short)(12*20));
            subtitulo.setFont(font2);
            
            CellStyle encabezado = libro.createCellStyle();
            encabezado.setBorderBottom(CellStyle.BORDER_THIN);
            encabezado.setBorderTop(CellStyle.BORDER_THIN);
            encabezado.setBorderRight(CellStyle.BORDER_THIN);
            encabezado.setBorderLeft(CellStyle.BORDER_THIN);
            encabezado.setFillPattern(CellStyle.SOLID_FOREGROUND);
            encabezado.setFillForegroundColor((short)19);
            encabezado.setAlignment(CellStyle.ALIGN_CENTER);
            
            CellStyle detalle = libro.createCellStyle();
            detalle.setBorderBottom(CellStyle.BORDER_THIN);
            detalle.setBorderTop(CellStyle.BORDER_THIN);
            detalle.setBorderRight(CellStyle.BORDER_THIN);
            detalle.setBorderLeft(CellStyle.BORDER_THIN);
            
            
            
            
            Sheet hoja1 = libro.getSheetAt(0);
            Row row = null;
            Cell cell = null;
            
            row = hoja1.createRow(1);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("REPORTE MOVIMIENTOS DE "+concepto+" POR "+mapDiccionario.get("BODEGA")+"/PROYECTO");
		
            row = hoja1.createRow(2);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(mapDiccionario.get("BODEGA")+"/PROYECTO: "+bodega.getNombre().toUpperCase());
			
			row = hoja1.createRow(3);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("EMPRESA: "+mapDiccionario.get("nEmpresa"));
			
			row = hoja1.createRow(4);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FECHA: "+Fechas.hoy().getFechaStrDDMMAA());
			
			row = hoja1.createRow(6);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("PERIODO: DESDE "+Fechas.DDMMAA(fechaDesde)+" HASTA "+Fechas.DDMMAA(fechaHasta));
			
			// encabezado de la tabla
			
			hoja1.setColumnWidth(1, 7*1000);
			hoja1.setColumnWidth(2, 5*1000);
			hoja1.setColumnWidth(3, 10*1000);
			for(int i=4;i<datos.size()+1;i++){
				hoja1.setColumnWidth(i, 4*1000);
			}
		
			//INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
			InputStream x = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
            byte[] bytes = IOUtils.toByteArray(x);
            x.close();
            int pngIndex = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			Drawing draw = hoja1.createDrawingPatriarch();
			CreationHelper helper = libro.getCreationHelper();
			ClientAnchor anchor = helper.createClientAnchor();
	        //set top-left corner for the image
	        anchor.setCol1(6);
	        anchor.setRow1(1);
			Picture img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);
			
			//DETALLE DE LA TABLA
			int posRow = 8;
			for(int i=0;i<datos.size();i++){
				row = hoja1.createRow(posRow);
				int posCell = 0;
				Double aux = (double)0;
				for(int j=0;j<datos.get(i).size();j++){
					String dato = datos.get(i).get(j);
					if(i<5){
						posCell++; 
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(dato);
					}else{
						if(j<3){
							posCell++; 
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(dato);
						}else {
							if(dato.equals("")||dato.equals(" ")) {
								dato = "0";
							}
							posCell++; 
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
				            aux = Double.parseDouble(dato.replaceAll(",", ""));
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(aux);
						}
					}
				}
				posRow++;
			}
			
			
			posRow = posRow + 5;
			row = hoja1.createRow(posRow);
			cell = row.createCell(1);
			Hyperlink hiper = helper.createHyperlink(0);
			hiper.setAddress("https://www.inqsol.cl");
			cell.setHyperlink(hiper);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Documento generado desde MADA propiedad de INQSOL");
			
			
			// Write the output to a file tmp
			FileOutputStream fileOut = new FileOutputStream(tmp);
			libro.write(fileOut);
			fileOut.close();
			
		} catch (Exception e) {
			e.printStackTrace();
        }
	  return tmp;
	}
	
	public static File movPorProyectoExcelAgrupado(String db, List<List<String>> datos, 
			Map<String,String> mapDiccionario, Proyecto proyecto, String concepto, String fechaDesde, String fechaHasta) {

   		File tmp = TempFile.createTempFile("tmp","null");
		
		try {
			String path = "formatos/excel.xlsx";
			InputStream formato = Archivos.leerArchivo(path);
            Workbook libro = WorkbookFactory.create(formato);
            formato.close();
            
            // 0 negro 1 blanco 2 rojo 3 verde 4 azul 5 amarillo 19 celeste
            CellStyle titulo = libro.createCellStyle();
            Font font = libro.createFont();
            font.setBoldweight(Font.BOLDWEIGHT_BOLD);
            font.setColor((short)4);
            font.setFontHeight((short)(14*20));
            titulo.setFont(font);
            
            CellStyle subtitulo = libro.createCellStyle();
            Font font2 = libro.createFont();
            font2.setBoldweight(Font.BOLDWEIGHT_BOLD);
            font2.setColor((short)0);
            font2.setFontHeight((short)(12*20));
            subtitulo.setFont(font2);
            
            CellStyle encabezado = libro.createCellStyle();
            encabezado.setBorderBottom(CellStyle.BORDER_THIN);
            encabezado.setBorderTop(CellStyle.BORDER_THIN);
            encabezado.setBorderRight(CellStyle.BORDER_THIN);
            encabezado.setBorderLeft(CellStyle.BORDER_THIN);
            encabezado.setFillPattern(CellStyle.SOLID_FOREGROUND);
            encabezado.setFillForegroundColor((short)19);
            encabezado.setAlignment(CellStyle.ALIGN_CENTER);
            
            CellStyle detalle = libro.createCellStyle();
            detalle.setBorderBottom(CellStyle.BORDER_THIN);
            detalle.setBorderTop(CellStyle.BORDER_THIN);
            detalle.setBorderRight(CellStyle.BORDER_THIN);
            detalle.setBorderLeft(CellStyle.BORDER_THIN);
            
            
            
            
            Sheet hoja1 = libro.getSheetAt(0);
            Row row = null;
            Cell cell = null;
            
            row = hoja1.createRow(1);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("REPORTE MOVIMIENTOS DE "+concepto+" POR "+mapDiccionario.get("BODEGA")+"/PROYECTO");
		
            row = hoja1.createRow(2);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("PROYECTO: "+proyecto.getNombre().toUpperCase());
			
			row = hoja1.createRow(3);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("EMPRESA: "+mapDiccionario.get("nEmpresa"));
			
			row = hoja1.createRow(4);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FECHA: "+Fechas.hoy().getFechaStrDDMMAA());
			
			row = hoja1.createRow(6);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("PERIODO: DESDE "+Fechas.DDMMAA(fechaDesde)+" HASTA "+Fechas.DDMMAA(fechaHasta));
			
			// encabezado de la tabla
			
			hoja1.setColumnWidth(1, 7*1000);
			hoja1.setColumnWidth(2, 5*1000);
			hoja1.setColumnWidth(3, 10*1000);
			for(int i=4;i<datos.size()+1;i++){
				hoja1.setColumnWidth(i, 4*1000);
			}
		
			//INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
			InputStream x = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
            byte[] bytes = IOUtils.toByteArray(x);
            x.close();
            int pngIndex = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			Drawing draw = hoja1.createDrawingPatriarch();
			CreationHelper helper = libro.getCreationHelper();
			ClientAnchor anchor = helper.createClientAnchor();
	        //set top-left corner for the image
	        anchor.setCol1(6);
	        anchor.setRow1(1);
			Picture img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);
			
			//DETALLE DE LA TABLA
			int posRow = 8;
			for(int i=0;i<datos.size();i++){
				row = hoja1.createRow(posRow);
				int posCell = 0;
				Double aux = (double)0;
				for(int j=0;j<datos.get(i).size();j++){
					String dato = datos.get(i).get(j);
					if(i<6){
						posCell++; 
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(dato);
					}else{
						if(j<3){
							posCell++; 
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(dato);
						}else if(j==3 || j==4) {
							posCell++; 
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
						}else {
							if(dato.equals("")||dato.equals(" ")) {
								dato = "0";
							}
							posCell++; 
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
				            aux = Double.parseDouble(dato.replaceAll(",", ""));
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(aux);
						}
					}
				}
				posRow++;
			}
			
			
			posRow = posRow + 5;
			row = hoja1.createRow(posRow);
			cell = row.createCell(1);
			Hyperlink hiper = helper.createHyperlink(0);
			hiper.setAddress("https://www.inqsol.cl");
			cell.setHyperlink(hiper);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Documento generado desde MADA propiedad de INQSOL");
			
			
			// Write the output to a file tmp
			FileOutputStream fileOut = new FileOutputStream(tmp);
			libro.write(fileOut);
			fileOut.close();
			
		} catch (Exception e) {
			e.printStackTrace();
        }
	  return tmp;
	}
	
	public static List<List<String>> movimientoGuiasIE(Connection con, String db, Long id_bodegaEmpresa, String esVenta) {
		List<List<String>> lista = new ArrayList<List<String>>();
		Map<String,Double> excedentes = ReportExcedentes.totalExcedentesPorNumCotiCodyBod(con, db, id_bodegaEmpresa);
		try {
			PreparedStatement smt1 = con
					.prepareStatement(" select  distinct " +
							" guia.numero, " +
							" concat(day(guia.fecha),'/',month(guia.fecha),'/',year(guia.fecha)),  " +   
							" tipoMovimiento.nombre, " +
							" guia.fecha, " +
							" ifnull(guia.numGuiaCliente,'') "+
							" from `"+db+"`.movimiento " +
							" left join `"+db+"`.guia on guia.id = movimiento.id_guia " +
							" left join `"+db+"`.tipoMovimiento on tipoMovimiento.id = id_tipoMovimiento  " +
							" where id_bodegaEmpresa =  ? and numero is not null " +
							" and tipoMovimiento.id=1 " +
							" and movimiento.esVenta= ? order by guia.fecha,guia.numero;");
			smt1.setLong(1, id_bodegaEmpresa);
			smt1.setString(2, esVenta.trim());
			ResultSet rs1 = smt1.executeQuery();
				List<String> numGuia = new ArrayList<String>();
				List<String> fechGuia = new ArrayList<String>();
				List<String> guiaClie = new ArrayList<String>();
				List<String> tipGuia = new ArrayList<String>();
				List<String> blanco = new ArrayList<String>();
				numGuia.add(" ");
				fechGuia.add(" ");
				guiaClie.add(" ");
				tipGuia.add(" ");
				blanco.add("Grupo");
				numGuia.add(" ");
				fechGuia.add(" ");
				guiaClie.add(" ");
				tipGuia.add(" ");
				blanco.add("Nro.Coti");
				numGuia.add(" ");
				fechGuia.add(" ");
				guiaClie.add(" ");
				tipGuia.add(" ");
				blanco.add("Código");
				numGuia.add(" ");
				fechGuia.add(" ");
				guiaClie.add(" ");
				tipGuia.add(" ");
				blanco.add("Equipo");
				numGuia.add(" ");
				fechGuia.add(" ");
				guiaClie.add(" ");
				tipGuia.add(" ");
				blanco.add("kg");
				numGuia.add(" ");
				fechGuia.add(" ");
				guiaClie.add(" ");
				tipGuia.add(" ");
				blanco.add("m2");
				numGuia.add(" ");
				fechGuia.add(" ");
				guiaClie.add(" ");
				tipGuia.add(" ");
				blanco.add("Moneda");
				numGuia.add(" ");
				fechGuia.add(" ");
				guiaClie.add(" ");
				tipGuia.add(" ");
				blanco.add("Precio Venta");
				numGuia.add(" ");
				fechGuia.add(" ");
				guiaClie.add(" ");
				tipGuia.add(" ");
				blanco.add("Tasa Arriendo");
				numGuia.add(" ");
				fechGuia.add(" ");
				guiaClie.add(" ");
				tipGuia.add(" ");
				blanco.add("Arriendo Mes");
				numGuia.add("Nro.Mov:");
				fechGuia.add("Fecha:");
				guiaClie.add("Nro.Ref");
				tipGuia.add("Tipo.Mov: ");
				blanco.add("Arriendo Dia");
				
				while (rs1.next()) {
					numGuia.add(rs1.getString(1));
					fechGuia.add(rs1.getString(2));
					guiaClie.add(rs1.getString(5));
					tipGuia.add(rs1.getString(3));
					blanco.add(" ");
				}
				rs1.close();
				smt1.close();
				
				numGuia.add("TOTAL");
				fechGuia.add("INGRESOS");
				guiaClie.add(" ");
				tipGuia.add("TOTAL");
				blanco.add("INGRESOS");
				
				
				PreparedStatement smt100 = con
						.prepareStatement(" select  distinct " +
								" guia.numero, " +
								" concat(day(guia.fecha),'/',month(guia.fecha),'/',year(guia.fecha)),  " +   
								" tipoMovimiento.nombre, " +
								" guia.fecha, " +
								" ifnull(guia.numGuiaCliente,'') "+
								" from `"+db+"`.movimiento " +
								" left join `"+db+"`.guia on guia.id = movimiento.id_guia " +
								" left join `"+db+"`.tipoMovimiento on tipoMovimiento.id = id_tipoMovimiento  " +
								" where  movimiento.esVenta= ? and id_bodegaEmpresa =  ? and numero is not null " +
								" and tipoMovimiento.id=2 " +
								" order by guia.fecha,guia.numero;");
				smt100.setString(1, esVenta.trim());
				smt100.setLong(2, id_bodegaEmpresa);
				ResultSet rs100 = smt100.executeQuery();
				while (rs100.next()) {
					numGuia.add(rs100.getString(1));
					fechGuia.add(rs100.getString(2));
					guiaClie.add(rs100.getString(5));
					tipGuia.add(rs100.getString(3));
					blanco.add(" ");
				}
				rs100.close();
				smt100.close();
				
				numGuia.add("TOTAL");
				fechGuia.add("SALIDAS");
				guiaClie.add(" ");
				tipGuia.add("TOTAL");
				blanco.add("SALIDAS");
				
				numGuia.add("TOTAL");
				fechGuia.add("TOTAL");
				guiaClie.add(" ");
				tipGuia.add("TOTAL");
				blanco.add("TOTAL");
				
				numGuia.add("");
				fechGuia.add("");
				guiaClie.add(" ");
				tipGuia.add("EXCEDENTES");
				blanco.add("");
				
				
				
				PreparedStatement smt2 = con
						.prepareStatement(" select distinct  guia.numero " +
								" from `"+db+"`.guia " +
								" left join `"+db+"`.movimiento on movimiento.id_guia = guia.id " +
								" where id_bodegaEmpresa = ? "+
								" and movimiento.esVenta= ? and movimiento.id_tipoMovimiento=1 " +
								" order by guia.fecha,guia.numero; "); 
				smt2.setLong(1, id_bodegaEmpresa);
				smt2.setString(2, esVenta.trim());
				ResultSet rs2 = smt2.executeQuery();
				List<String> listaGuias = new ArrayList<String>();
				while (rs2.next()) {
					listaGuias.add(rs2.getString(1));
				}
				rs2.close();
				smt2.close();
				
				PreparedStatement smt200 = con
						.prepareStatement(" select distinct  guia.numero " +
								" from `"+db+"`.guia " +
								" left join `"+db+"`.movimiento on movimiento.id_guia = guia.id " +
								" where id_bodegaEmpresa = ? "+
								" and movimiento.esVenta= ? and movimiento.id_tipoMovimiento=2 " +
								" order by guia.fecha,guia.numero; "); 
				smt200.setLong(1, id_bodegaEmpresa);
				smt200.setString(2, esVenta.trim());
				ResultSet rs200 = smt200.executeQuery();
				List<String> listaGuias200 = new ArrayList<String>();
				while (rs200.next()) {
					listaGuias200.add(rs200.getString(1));
				}
				rs200.close();
				smt200.close();
				
				PreparedStatement smt3 = con
						.prepareStatement(" select distinct " +
								" grupo.nombre, " +
								" equipo.codigo, " +
								" equipo.nombre, " +
								" ifnull(moneda.nickName,''), " + 
								" ifnull(listaPrecio.precioVenta,0), " + 
								" ifnull(listaPrecio.precioReposicion,0), " + 
								" ifnull(listaPrecio.precioArriendo,0), " + 
								" ifnull(listaPrecio.id_unidadTiempo,1), " +
								" ifnull(bodegaEmpresa.tasaDescto,0), " +
								" ifnull(dctoGrupo.tasaDescto,0), " +
								" ifnull(dctoEquipo.tasaDescto,0), " +
								" movimiento.id_cotizacion, " +
								" ifnull(cotizacion.numero,0), " +
								" equipo.id, " +
								" equipo.kg, " +
								" equipo.m2 " +
								" from `"+db+"`.movimiento " + 
								" left join `"+db+"`.cotizacion on cotizacion.id = movimiento.id_cotizacion " +
								" left join `"+db+"`.equipo on equipo.id = movimiento.id_equipo " + 
								" left join `"+db+"`.listaPrecio on listaPrecio.id_equipo = equipo.id and listaPrecio.id_cotizacion=movimiento.id_cotizacion and listaPrecio.id_bodegaEmpresa=" +id_bodegaEmpresa + 
								" left join `"+db+"`.grupo on grupo.id = equipo.id_grupo  " + 
								" left join `"+db+"`.moneda on moneda.id = listaPrecio.id_moneda " + 
								" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa " +
								" left join `"+db+"`.dctoGrupo on dctoGrupo.id_bodegaEmpresa = movimiento.id_bodegaEmpresa and dctoGrupo.id_grupo = equipo.id_grupo " +
								" left join `"+db+"`.dctoEquipo on dctoEquipo.id_bodegaEmpresa = movimiento.id_bodegaEmpresa and dctoEquipo.id_equipo = movimiento.id_equipo " +
								" where " + 
								" movimiento.id_bodegaEmpresa=? and listaPrecio.id_unidadTiempo is not null"+
								" and equipo.nombre is not null and movimiento.esVenta= ? "+ 
								" order by grupo.nombre,equipo.nombre;"); 
				smt3.setLong(1, id_bodegaEmpresa);
				smt3.setString(2, esVenta.trim());

				ResultSet rs3 = smt3.executeQuery();
				List<List<String>> listaCodigos = new ArrayList<List<String>>();
				Map<Long,Double> mapUnidadTiempo = UnidadTiempo.equivalencia(con, db);
				while (rs3.next()) {
					List<String> aux = new ArrayList<String>();
					Double factor = mapUnidadTiempo.get(rs3.getLong(8));
					if(factor == null){
						factor = (double)1;
					}
					
					aux.add(rs3.getString(1));
					aux.add(rs3.getString(2));
					aux.add(rs3.getString(3));
					aux.add(rs3.getString(4));
					aux.add(myformatdouble2.format(rs3.getDouble(5)));
					Double tasa=(double)0;
					Double tasaDcto = 1-((1-rs3.getDouble(9))*(1-rs3.getDouble(10))*(1-rs3.getDouble(11)));
					if(rs3.getDouble(7)>0&&rs3.getDouble(5)>0) tasa=((rs3.getDouble(7)*30/factor*(1-tasaDcto))/rs3.getDouble(5))*100;
					
					if(esVenta.equals("1")) {
						aux.add("0 %"); //tasaArriendo
						aux.add("0"); // arriendo mes
						aux.add("0");  //arriendo dia
					}else {
						aux.add(myformatdouble2.format(tasa)+" %"); //tasaArriendo
						aux.add(myformatdouble2.format(rs3.getDouble(7)*30/factor*(1-tasaDcto))); // arriendo mes
						aux.add(myformatdouble4.format(rs3.getDouble(7)/factor*(1-tasaDcto)));  //arriendo dia
					}
					aux.add(rs3.getString(12)); //  8 id cotizacion
					aux.add(rs3.getString(13)); //  9 numero coti
					aux.add(rs3.getString(14)); //  10 idequipo
					aux.add(rs3.getString(15)); //  11 kg
					aux.add(rs3.getString(16)); //  12 m2
					listaCodigos.add(aux);
				}
				rs3.close();
				smt3.close();
					
				PreparedStatement smt4 = con
						.prepareStatement(" select " +
								" equipo.codigo,    " +
								" guia.numero, " +
								" sum(if(movimiento.id_tipoMovimiento=2,movimiento.cantidad*-1,movimiento.cantidad)),   " +
								" movimiento.id_cotizacion "+
								" from `"+db+"`.movimiento " +
								" left join `"+db+"`.equipo on equipo.id = movimiento.id_equipo " +
								" left join `"+db+"`.guia on guia.id = movimiento.id_guia " +
								" where movimiento.id_bodegaEmpresa =  ? "+  
								" and guia.fecha is not null   " +
								" and movimiento.esVenta= ? and movimiento.id_tipoMovimiento=1   " +
								" group by equipo.codigo, guia.numero, movimiento.id_cotizacion  " + 
								" order by guia.fecha, guia.numero, equipo.codigo;");
				smt4.setLong(1, id_bodegaEmpresa);
				smt4.setString(2, esVenta.trim());
				
				ResultSet rs4 = smt4.executeQuery();
				Map<String,String> codGuiaCant = new HashMap<String,String>();
				while (rs4.next()) {
					codGuiaCant.put(rs4.getString(1).trim()+"_"+rs4.getString(2).trim()+"_"+rs4.getString(4).trim(), rs4.getString(3));
				}
				rs4.close();
				smt4.close();
				
				PreparedStatement smt400 = con
						.prepareStatement(" select " +
								" equipo.codigo,    " +
								" guia.numero, " +
								" sum(if(movimiento.id_tipoMovimiento=2,movimiento.cantidad*-1,movimiento.cantidad)),   " +
								" movimiento.id_cotizacion "+
								" from `"+db+"`.movimiento " +
								" left join `"+db+"`.equipo on equipo.id = movimiento.id_equipo " +
								" left join `"+db+"`.guia on guia.id = movimiento.id_guia " +
								" where movimiento.id_bodegaEmpresa = ? " +  
								" and guia.fecha is not null   " +
								" and movimiento.esVenta= ? and movimiento.id_tipoMovimiento=2   " +
								" group by equipo.codigo, guia.numero, movimiento.id_cotizacion  " + 
								" order by guia.fecha,guia.numero,equipo.codigo;");
				smt400.setLong(1, id_bodegaEmpresa);
				smt400.setString(2, esVenta.trim());
				ResultSet rs400 = smt400.executeQuery();
				Map<String,String> codGuiaCant400 = new HashMap<String,String>();
				while (rs400.next()) {
					codGuiaCant400.put(rs400.getString(1).trim()+"_"+rs400.getString(2).trim()+"_"+rs400.getString(4).trim(), rs400.getString(3));
				}
				rs400.close();
				smt400.close();
				
				lista.add(numGuia);
				lista.add(fechGuia);
				lista.add(guiaClie);
				lista.add(tipGuia);
				lista.add(blanco);
				
				String auxiliarDeReparacion="";
				
				for(int i=0;i<listaCodigos.size();i++){
					String dePaso = listaCodigos.get(i).get(0)+listaCodigos.get(i).get(9)+listaCodigos.get(i).get(1)+listaCodigos.get(i).get(2);
					if(!auxiliarDeReparacion.equals(dePaso)) {
						auxiliarDeReparacion=dePaso;
						List<String> aux = new ArrayList<String>();
						aux.add(listaCodigos.get(i).get(0)); //grupo
						aux.add(listaCodigos.get(i).get(9)); //numero cotizacion
						aux.add(listaCodigos.get(i).get(1)); //codigo
						aux.add(listaCodigos.get(i).get(2)); //equipo
						
						// kg por unidad
						Double kg = Double.parseDouble(listaCodigos.get(i).get(11).trim());
						if(kg > 0) {
							aux.add(myformatdouble2.format(kg));
						}else {
							aux.add(""); 
						}
						// m2 por unidad
						Double m2 = Double.parseDouble(listaCodigos.get(i).get(12).trim());
						if(m2 > 0) {
							aux.add(myformatdouble2.format(m2));
						}else {
							aux.add("");
						}
						aux.add(listaCodigos.get(i).get(3)); //moneda
						aux.add(listaCodigos.get(i).get(4)); //precioVenta
						aux.add(listaCodigos.get(i).get(5)); //tasaArriendo
						aux.add(listaCodigos.get(i).get(6)); //precioArriendo mes
						aux.add(listaCodigos.get(i).get(7)); //precioArriendo dia
						Double subtotal=(double)0;
						for(int k=0;k<listaGuias.size();k++){
							String keyAux=listaCodigos.get(i).get(1).trim()+"_"+listaGuias.get(k).trim()+"_"+listaCodigos.get(i).get(8);
							String cantidad = codGuiaCant.get(keyAux);
							
							if(cantidad==null){
								aux.add(" ");
							
							}else{
								String auxSubTot=cantidad;
								subtotal = Double.parseDouble(auxSubTot.trim()) + subtotal;
								aux.add(auxSubTot);
							}
						}
						Long sub = subtotal.longValue();
						aux.add(sub.toString());
						Double subtotal2=(double)0;
						for(int k=0;k<listaGuias200.size();k++){
							String keyAux=listaCodigos.get(i).get(1).trim()+"_"+listaGuias200.get(k).trim()+"_"+listaCodigos.get(i).get(8);
							String cantidad = codGuiaCant400.get(keyAux);
							if(cantidad==null){
								aux.add(" ");
							}else{
								String auxSubTot=cantidad;
								subtotal2 = Double.parseDouble(auxSubTot.trim()) + subtotal2;
								aux.add(auxSubTot);
							}
						}
						sub = subtotal2.longValue();
						aux.add(sub.toString());
						Double tot=subtotal+subtotal2;
						Long total = tot.longValue();
						aux.add(total.toString());
						
						Double auxExcedente = excedentes.get(aux.get(2)+"_"+aux.get(1)); // aux.get(1) = numero cotizacion // aux.get(2) = codigo equipo
						
						if(auxExcedente==null) {
							auxExcedente = (double)0;
							aux.add("0");
						}else {
							aux.add(myformatdouble.format(auxExcedente));	//14 excedente
						}
						
						if(subtotal>0||subtotal2<0||auxExcedente>0) {
							lista.add(aux);
						}
					}
				}
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static File movimientosExcelIE(String db, List<List<String>> datos, Map<String,String> mapDiccionario, BodegaEmpresa bodega, String concepto) {
		
		File tmp = TempFile.createTempFile("tmp","null");
		
		try {
			String path = "formatos/excel.xlsx";
			InputStream formato = Archivos.leerArchivo(path);
            Workbook libro = WorkbookFactory.create(formato);
            formato.close();
            
            // 0 negro 1 blanco 2 rojo 3 verde 4 azul 5 amarillo 19 celeste
            CellStyle titulo = libro.createCellStyle();
            Font font = libro.createFont();
            font.setBoldweight(Font.BOLDWEIGHT_BOLD);
            font.setColor((short)4);
            font.setFontHeight((short)(14*20));
            titulo.setFont(font);
            
            CellStyle subtitulo = libro.createCellStyle();
            Font font2 = libro.createFont();
            font2.setBoldweight(Font.BOLDWEIGHT_BOLD);
            font2.setColor((short)0);
            font2.setFontHeight((short)(12*20));
            subtitulo.setFont(font2);
            
            CellStyle encabezado = libro.createCellStyle();
            encabezado.setBorderBottom(CellStyle.BORDER_THIN);
            encabezado.setBorderTop(CellStyle.BORDER_THIN);
            encabezado.setBorderRight(CellStyle.BORDER_THIN);
            encabezado.setBorderLeft(CellStyle.BORDER_THIN);
            encabezado.setFillPattern(CellStyle.SOLID_FOREGROUND);
            encabezado.setFillForegroundColor((short)19);
            encabezado.setAlignment(CellStyle.ALIGN_CENTER);
            
            CellStyle detalle = libro.createCellStyle();
            detalle.setBorderBottom(CellStyle.BORDER_THIN);
            detalle.setBorderTop(CellStyle.BORDER_THIN);
            detalle.setBorderRight(CellStyle.BORDER_THIN);
            detalle.setBorderLeft(CellStyle.BORDER_THIN);
            
            
            Sheet hoja1 = libro.getSheetAt(0);
            Row row = null;
            Cell cell = null;
            
            row = hoja1.createRow(1);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("REPORTE MOVIMIENTOS DE "+concepto+" POR "+mapDiccionario.get("BODEGA")+"/PROYECTO");
		
            row = hoja1.createRow(2);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(mapDiccionario.get("BODEGA")+"/PROYECTO: "+bodega.getNombre().toUpperCase());
			
			row = hoja1.createRow(3);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("EMPRESA: "+mapDiccionario.get("nEmpresa"));
			
			row = hoja1.createRow(4);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FECHA: "+Fechas.hoy().getFechaStrDDMMAA());
			
			
			// encabezado de la tabla
			
			hoja1.setColumnWidth(1, 7*1000);
			hoja1.setColumnWidth(2, 3*1000);
			hoja1.setColumnWidth(3, 5*1000);
			hoja1.setColumnWidth(4, 10*1000);
			for(int i=5;i<datos.size()+1;i++){
				hoja1.setColumnWidth(i, 4*1000);
			}
		
			//INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
			InputStream x = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
            byte[] bytes = IOUtils.toByteArray(x);
            x.close();
            int pngIndex = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			Drawing draw = hoja1.createDrawingPatriarch();
			CreationHelper helper = libro.getCreationHelper();
			ClientAnchor anchor = helper.createClientAnchor();
	        //set top-left corner for the image
	        anchor.setCol1(11);
	        anchor.setRow1(1);
			Picture img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);
			
			//DETALLE DE LA TABLA
			int posRow = 8;
			for(int i=0;i<datos.size();i++){
				row = hoja1.createRow(posRow);
				int posCell = 0;
				Double aux = (double)0;
				for(int j=0;j<datos.get(i).size();j++){
					String dato = datos.get(i).get(j);
					if(i<5){
						posCell++; 
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(dato);
					}else{
						if(j<4||j==6){
							posCell++; 
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(dato);
						}else if(j==8){
							if(dato.equals("")||dato.equals(" ")) {
								dato = "0";
							}
							posCell++; 
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
				            aux = Double.parseDouble(dato.replaceAll("%", "").trim().replaceAll(",", ""));
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(aux/100);
						}else {
							if(dato.equals("")||dato.equals(" ")) {
								dato = "0";
							}
							posCell++; 
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
				            aux = Double.parseDouble(dato.replaceAll(",", ""));
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(aux);
						}
					}
				}
				posRow++;
			}
			
			
			posRow = posRow + 5;
			row = hoja1.createRow(posRow);
			cell = row.createCell(1);
			Hyperlink hiper = helper.createHyperlink(0);
			hiper.setAddress("https://www.inqsol.cl");
			cell.setHyperlink(hiper);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Documento generado desde MADA propiedad de INQSOL");
			
			
			// Write the output to a file tmp
			FileOutputStream fileOut = new FileOutputStream(tmp);
			libro.write(fileOut);
			fileOut.close();
			
		} catch (Exception e) {
			e.printStackTrace();
        }
	  return tmp;
	}
	
	public static List<List<String>> movimientoGuiasPorEstado(Connection con, String db, Long id_bodegaEmpresa, Long id_tipoEstado, Map<Long,Double> mapUnidadTiempo) {
		List<List<String>> lista = new ArrayList<List<String>>();
		try {
			PreparedStatement smt1 = con
					.prepareStatement(" select  distinct " +
							" guia.numero, " +
							" concat(day(guia.fecha),'/',month(guia.fecha),'/',year(guia.fecha)),  " +   
							" tipoMovimiento.nombre " +
							" from `"+db+"`.estadoEquipo " +
							" left join `"+db+"`.movimiento on movimiento.id=estadoEquipo.id_movimiento " +
							" left Join `"+db+"`.tipoEstado on tipoEstado.id = estadoEquipo.id_tipoEstado " +
							" left join `"+db+"`.guia on guia.id = movimiento.id_guia " +
							" left join `"+db+"`.tipoMovimiento on tipoMovimiento.id = id_tipoMovimiento  " +
							" where id_bodegaEmpresa =  ? and numero is not null " +
							" and tipoEstado.id = ? "+ 
							" order by guia.fecha,guia.numero;");
			smt1.setLong(1, id_bodegaEmpresa);
			smt1.setLong(2, id_tipoEstado);
			
			ResultSet rs1 = smt1.executeQuery();
			
			List<String> numGuia = new ArrayList<String>();
			List<String> fechGuia = new ArrayList<String>();
			List<String> tipGuia = new ArrayList<String>();
			List<String> blanco = new ArrayList<String>();
			numGuia.add(" ");
			fechGuia.add(" ");
			tipGuia.add(" ");
			blanco.add("Grupo");
			numGuia.add(" ");
			fechGuia.add(" ");
			tipGuia.add(" ");
			blanco.add("Nro Coti");
			numGuia.add(" ");
			fechGuia.add(" ");
			tipGuia.add(" ");
			blanco.add("Código");
			numGuia.add(" ");
			fechGuia.add(" ");
			tipGuia.add(" ");
			blanco.add("Equipo");
			numGuia.add(" ");
			fechGuia.add(" ");
			tipGuia.add(" ");
			blanco.add("Moneda");
			numGuia.add(" ");
			fechGuia.add(" ");
			tipGuia.add(" ");
			blanco.add("P.Reposicion");
			numGuia.add(" ");
			fechGuia.add(" ");
			tipGuia.add(" ");
			blanco.add("Tasa Arriendo");
			numGuia.add(" ");
			fechGuia.add(" ");
			tipGuia.add(" ");
			blanco.add("Arriendo Mes");
			numGuia.add(" ");
			fechGuia.add(" ");
			tipGuia.add(" ");
			blanco.add("Arriendo Dia");
			numGuia.add(" ");
			fechGuia.add(" ");
			tipGuia.add(" ");
			blanco.add("KG");
			numGuia.add("Nro.Mov:");
			fechGuia.add("Fecha:");
			tipGuia.add("Tipo.Mov: ");
			blanco.add("M2");
			
			List<String> listaGuias = new ArrayList<String>();
			while (rs1.next()) {
				numGuia.add(rs1.getString(1));
				fechGuia.add(rs1.getString(2));
				tipGuia.add(rs1.getString(3));
				blanco.add(" ");
				listaGuias.add(rs1.getString(1));
			}
			numGuia.add("TOTALES");
			fechGuia.add(" ");
			tipGuia.add(" ");
			blanco.add(" ");
			rs1.close();
			smt1.close();
				
			PreparedStatement smt3 = con
					.prepareStatement(" select distinct  " + 
							" grupo.nombre, " + 
							" equipo.codigo, " + 
							" equipo.nombre, " + 
							" ifnull(moneda.nickName,''), " + 
							" ifnull(listaPrecio.precioVenta,0), " + 
							" ifnull(listaPrecio.precioReposicion,0), " + 
							" ifnull(listaPrecio.precioArriendo,0), " + 
							" ifnull(listaPrecio.id_unidadTiempo,1), " + 
							" ifnull(cotizacion.id,0), " +
							" ifnull(cotizacion.numero,0), " +
							" equipo.id, " +
							" equipo.kg, " +
							" equipo.m2 " +
							" from `"+db+"`.estadoEquipo " + 
							" left join `"+db+"`.movimiento on movimiento.id = estadoEquipo.id_movimiento " + 
							" left join `"+db+"`.cotizacion on cotizacion.id = movimiento.id_cotizacion " +
							" left join `"+db+"`.listaPrecio on listaPrecio.id_equipo = movimiento.id_equipo and listaPrecio.id_cotizacion =movimiento.id_cotizacion and listaPrecio.id_bodegaEmpresa = " +id_bodegaEmpresa + 
							" left Join `"+db+"`.tipoEstado on tipoEstado.id = estadoEquipo.id_tipoEstado " + 
							" left join `"+db+"`.equipo on equipo.id = listaPrecio.id_equipo " + 
							" left join `"+db+"`.grupo on grupo.id = equipo.id_grupo " + 
							" left join `"+db+"`.moneda on moneda.id = listaPrecio.id_moneda " + 
							" where movimiento.id_bodegaEmpresa=? and listaPrecio.id_unidadTiempo is not null"+
							" and equipo.nombre is not null and tipoEstado.id =? "+
							" order by grupo.nombre, equipo.nombre;");
			smt3.setLong(1, id_bodegaEmpresa);
			smt3.setLong(2, id_tipoEstado);
			
			ResultSet rs3 = smt3.executeQuery();
			
					
			List<List<String>> listaCodigos = new ArrayList<List<String>>();
			while (rs3.next()) {
				List<String> aux = new ArrayList<String>();
				Double factor = mapUnidadTiempo.get(rs3.getLong(8));
				if(factor == null){
					factor = (double)1;
				}
				
				// kg por unidad
				Double kg = Double.parseDouble(rs3.getString(12));
				String kgStr = "";
				if(kg > 0) {
					kgStr = myformatdouble2.format(kg); 
				}
				// m2 por unidad
				Double m2 = Double.parseDouble(rs3.getString(13));
				String m2Str = "";
				if(m2 > 0) {
					m2Str = myformatdouble2.format(m2);
				}
				
				aux.add(rs3.getString(1));									// 0 grupo
				aux.add(rs3.getString(2));									// 1 codigo
				aux.add(rs3.getString(3));									// 2 nombre
				aux.add(rs3.getString(4));									// 3 moneda
				aux.add(myformatdouble2.format(rs3.getDouble(5)));			// 4 precio repos
				Double tasa=(double)0;
				if(rs3.getDouble(7)>0 && rs3.getDouble(5)>0) {
					tasa=((rs3.getDouble(7)*30/factor)/rs3.getDouble(5))*100;
				}
				aux.add(myformatdouble2.format(tasa)+" %");						// 5 tasa arr
				aux.add(myformatdouble.format(rs3.getDouble(7)*30/factor));		// 6 arr mes
				aux.add(myformatdouble.format(rs3.getDouble(7)/factor));		// 7 arr dia
				aux.add(rs3.getString(9));										// 8 id_cotizacion
				aux.add(rs3.getString(10));										// 9 numero cotizacion
				
				aux.add(kgStr);							// 10 kg unitario
				aux.add(m2Str);							// 11 m2 unitario
				
				listaCodigos.add(aux);
			}
			rs3.close();
			smt3.close();
				
			PreparedStatement smt4 = con
					.prepareStatement(" select " +
							" equipo.codigo,    " +
							" guia.numero, " +
							" sum(estadoEquipo.cantidad), " +
							" movimiento.id_cotizacion " +
							" from `"+db+"`.estadoEquipo " +
							" left join `"+db+"`.movimiento on movimiento.id=estadoEquipo.id_movimiento " +
							" left join `"+db+"`.equipo on equipo.id = movimiento.id_equipo " +
							" left join `"+db+"`.guia on guia.id = movimiento.id_guia " +
							" left Join `"+db+"`.tipoEstado on tipoEstado.id = estadoEquipo.id_tipoEstado " +
							" where movimiento.id_bodegaEmpresa =  ? "+
							" and guia.fecha is not null   " +
							" and tipoEstado.id = ? "+
							" group by equipo.codigo, guia.numero , movimiento.id_cotizacion " + 
							" order by guia.fecha,guia.numero,equipo.codigo;");
			smt4.setLong(1, id_bodegaEmpresa);
			smt4.setLong(2, id_tipoEstado);
		
			ResultSet rs4 = smt4.executeQuery();
			Map<String,String> codGuiaCant = new HashMap<String,String>();
			while (rs4.next()) {
				codGuiaCant.put(rs4.getString(1).trim()+"_"+rs4.getString(2).trim()+"_"+rs4.getString(4).trim(), rs4.getString(3));
			}
			rs4.close();
			smt4.close();
				
			
			// ARMAR MATRIZ
			lista.add(numGuia);
			lista.add(fechGuia);
			lista.add(tipGuia);
			lista.add(blanco);
				
			for(int i=0;i<listaCodigos.size();i++){
				List<String> aux = new ArrayList<String>();
				Double cantTotal=(double)0;
				
				aux.add(listaCodigos.get(i).get(0));	//0 grupo
				aux.add(listaCodigos.get(i).get(9));	//1 nro coti
				aux.add(listaCodigos.get(i).get(1));	//2 codigo
				aux.add(listaCodigos.get(i).get(2));	//3 equipo
				aux.add(listaCodigos.get(i).get(3));	//4 moneda
				aux.add(listaCodigos.get(i).get(4));	//5 prepos
				aux.add(listaCodigos.get(i).get(5));	//6 tasaarr
				aux.add(listaCodigos.get(i).get(6));	//7 arrmes
				aux.add(listaCodigos.get(i).get(7));	//8 arrdia
				aux.add(listaCodigos.get(i).get(10));	//9 arrdia
				aux.add(listaCodigos.get(i).get(11));	//10 arrdia
				
				for(int k=0;k<listaGuias.size();k++){
					if(codGuiaCant.get(listaCodigos.get(i).get(1).trim()+"_"+listaGuias.get(k).trim()+"_"+listaCodigos.get(i).get(8).trim())==null){
						aux.add(" ");
					}else{
						String auxCant = codGuiaCant.get(listaCodigos.get(i).get(1).trim()+"_"+listaGuias.get(k).trim()+"_"+listaCodigos.get(i).get(8).trim());
						aux.add(auxCant);
						Double dePaso = (double)0;
						String auxNum = auxCant.trim();
		 	   			if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
		 	   			dePaso = Double.parseDouble(auxNum.replaceAll(",", ""));
						cantTotal += dePaso;
					}
				}
				aux.add(cantTotal.toString());
				
				lista.add(aux);
			}
				
		} catch (SQLException e) {
				e.printStackTrace();
		}	
		return (lista);
	}

	public static File estadosExcel(String db, List<TipoEstado> listTipoEstados, Map<Long,List<List<String>>> mapDatos, BodegaEmpresa bodega, Map<String,String> mapDiccionario) {
		
		File tmp = TempFile.createTempFile("tmp","null");
		
		try {
			String path = "formatos/excel.xlsx";
			InputStream formato = Archivos.leerArchivo(path);
            Workbook libro = WorkbookFactory.create(formato);
            formato.close();
            
            // 0 negro 1 blanco 2 rojo 3 verde 4 azul 5 amarillo 19 celeste
            CellStyle titulo = libro.createCellStyle();
            Font font = libro.createFont();
            font.setBoldweight(Font.BOLDWEIGHT_BOLD);
            font.setColor((short)4);
            font.setFontHeight((short)(14*20));
            titulo.setFont(font);
            
            CellStyle subtitulo = libro.createCellStyle();
            Font font2 = libro.createFont();
            font2.setBoldweight(Font.BOLDWEIGHT_BOLD);
            font2.setColor((short)0);
            font2.setFontHeight((short)(12*20));
            subtitulo.setFont(font2);
            
            CellStyle encabezado = libro.createCellStyle();
            encabezado.setBorderBottom(CellStyle.BORDER_THIN);
            encabezado.setBorderTop(CellStyle.BORDER_THIN);
            encabezado.setBorderRight(CellStyle.BORDER_THIN);
            encabezado.setBorderLeft(CellStyle.BORDER_THIN);
            encabezado.setFillPattern(CellStyle.SOLID_FOREGROUND);
            encabezado.setFillForegroundColor((short)19);
            encabezado.setAlignment(CellStyle.ALIGN_CENTER);
            
            CellStyle detalle = libro.createCellStyle();
            detalle.setBorderBottom(CellStyle.BORDER_THIN);
            detalle.setBorderTop(CellStyle.BORDER_THIN);
            detalle.setBorderRight(CellStyle.BORDER_THIN);
            detalle.setBorderLeft(CellStyle.BORDER_THIN);
            
            
            Sheet hoja1 = libro.getSheetAt(0);
            Row row = null;
            Cell cell = null;
            
            row = hoja1.createRow(1);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("DETALLE POR ESTADOS DE EQUIPOS (DEVOLUCIONES)");
		
            row = hoja1.createRow(2);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(mapDiccionario.get("BODEGA")+"/PROYECTO: "+bodega.getNombre().toUpperCase());
			
			row = hoja1.createRow(3);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("EMPRESA: "+mapDiccionario.get("nEmpresa"));
			
			row = hoja1.createRow(4);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FECHA: "+Fechas.hoy().getFechaStrDDMMAA());
			
			
			// encabezado de la tabla
			
			hoja1.setColumnWidth(1, 7*1000);
			hoja1.setColumnWidth(2, 3*1000);
			hoja1.setColumnWidth(3, 3*1000);
			hoja1.setColumnWidth(4, 10*1000);
			hoja1.setColumnWidth(5, 2*1000);
			for(int i=6;i<200+1;i++){
				hoja1.setColumnWidth(i, 3*1000);
			}
			
		
			//INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
			InputStream x = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
            byte[] bytes = IOUtils.toByteArray(x);
            x.close();
            int pngIndex = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			Drawing draw = hoja1.createDrawingPatriarch();
			CreationHelper helper = libro.getCreationHelper();
			ClientAnchor anchor = helper.createClientAnchor();
	        //set top-left corner for the image
	        anchor.setCol1(11);
	        anchor.setRow1(1);
			Picture img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);
			
			//DETALLE DE LA TABLA
			int posRow = 8;
			for(int i=0;i<listTipoEstados.size();i++){
				row = hoja1.createRow(posRow);
				int posCell = 0;
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(titulo);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(listTipoEstados.get(i).getNombre().toUpperCase());
				posRow++;
				
				Double aux = (double)0;
				List<List<String>> datos = mapDatos.get(listTipoEstados.get(i).getId());
				
				
				
				for(int k=0; k<datos.size(); k++){
					row = hoja1.createRow(posRow);
					posCell = 0;
					for(int j=0;j<datos.get(k).size();j++){
						String dato = datos.get(k).get(j);
						if(k<4){
							posCell++; 
				            cell = row.createCell(posCell);
				            cell.setCellStyle(encabezado);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(dato);
						}else{
							if(j<5){
								posCell++; 
					            cell = row.createCell(posCell);
					            cell.setCellStyle(detalle);
								cell.setCellType(Cell.CELL_TYPE_STRING);
								cell.setCellValue(dato);
							}else if(j==6){
								if(dato.equals("")||dato.equals(" ")) {
									dato = "0";
								}
								posCell++; 
					            cell = row.createCell(posCell);
					            cell.setCellStyle(detalle);
					            aux = Double.parseDouble(dato.replaceAll("%", "").trim().replaceAll(",", ""));
								cell.setCellType(Cell.CELL_TYPE_NUMERIC);
								cell.setCellValue(aux/100);
							}else{
								if(dato.equals("")||dato.equals(" ")) {
									dato = "0";
								}
								posCell++; 
					            cell = row.createCell(posCell);
					            cell.setCellStyle(detalle);
					            aux = Double.parseDouble(dato.replaceAll(",", ""));
								cell.setCellType(Cell.CELL_TYPE_NUMERIC);
								cell.setCellValue(aux);
							}
						}
					}
					posRow++;
				}
				posRow++;
				posRow++;
			}
			
			
			posRow = posRow + 5;
			row = hoja1.createRow(posRow);
			cell = row.createCell(1);
			Hyperlink hiper = helper.createHyperlink(0);
			hiper.setAddress("https://www.madainqsol.com");
			cell.setHyperlink(hiper);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("documento generado desde www.mada.com");
			
			
			
			cell = row.createCell(2);
			cell.setHyperlink(hiper);
			cell = row.createCell(3);
			cell.setHyperlink(hiper);
			
			
			// Write the output to a file tmp
			FileOutputStream fileOut = new FileOutputStream(tmp);
			libro.write(fileOut);
			fileOut.close();
			
		} catch (Exception e) {
			e.printStackTrace();
        }
	  return tmp;
		
	}
	
	public static List<List<String>> movimientoGuias(Connection con, String db, Map<String,String> mapDiccionario, Long id_bodegaEmpresa, String esVenta, String fechaDesde, String fechaHasta,
			Double usd, Double eur, Double uf) {
		List<List<String>> lista = new ArrayList<List<String>>();
		Double granTotalArriendo = (double)0;
		Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
		String idMoneda = "1";
		Double totArr =(double)0;
		
		
		Map<String,Double> excedentes = ReportExcedentes.totalExcedentesPorNumCotiCodyBod(con, db, id_bodegaEmpresa);
		
		
		
		try {
			PreparedStatement smt1 = con
					.prepareStatement(" select  distinct " +
							" guia.numero, " +
							" concat(day(guia.fecha),'/',month(guia.fecha),'/',year(guia.fecha)),  " +   
							" tipoMovimiento.nombre, " +
							" guia.fecha, " +
							" ifnull(guia.numGuiaCliente,''), "+
							" guia.id " +
							" from `"+db+"`.movimiento " +
							" left join `"+db+"`.guia on guia.id = movimiento.id_guia " +
							" left join `"+db+"`.tipoMovimiento on tipoMovimiento.id = id_tipoMovimiento  " +
							" where id_bodegaEmpresa =  ? and numero is not null " +
							" and movimiento.esVenta= ?  order by guia.fecha,guia.numero;");
			smt1.setLong(1, id_bodegaEmpresa);
			smt1.setString(2, esVenta.trim());
			ResultSet rs1 = smt1.executeQuery();
			
				List<String> numGuia = new ArrayList<String>();
				List<String> fechGuia = new ArrayList<String>();
				
				List<String> guiaClie = new ArrayList<String>();
				
				List<String> tipGuia = new ArrayList<String>();
				List<String> blanco = new ArrayList<String>();
				
				List<Long> idsGuia = new ArrayList<Long>();
				
				numGuia.add(" ");
				fechGuia.add(" ");
				guiaClie.add(" ");
				tipGuia.add(" ");
				blanco.add("Grupo");
				numGuia.add(" ");
				fechGuia.add(" ");
				guiaClie.add(" ");
				tipGuia.add(" ");
				blanco.add("Nro.Coti");
				numGuia.add(" ");
				fechGuia.add(" ");
				guiaClie.add(" ");
				tipGuia.add(" ");
				blanco.add("Código");
				numGuia.add(" ");
				fechGuia.add(" ");
				guiaClie.add(" ");
				tipGuia.add(" ");
				blanco.add("Descripción");
				
				numGuia.add(" ");
				fechGuia.add(" ");
				guiaClie.add(" ");
				tipGuia.add(" ");
				blanco.add("kg");
				
				numGuia.add(" ");
				fechGuia.add(" ");
				guiaClie.add(" ");
				tipGuia.add(" ");
				blanco.add("m2");
				
				
				numGuia.add(" ");
				fechGuia.add(" ");
				guiaClie.add(" ");
				tipGuia.add(" ");
				blanco.add("Moneda");
				numGuia.add(" ");
				fechGuia.add(" ");
				guiaClie.add(" ");
				tipGuia.add(" ");
				blanco.add("Precio Venta");
				numGuia.add(" ");
				fechGuia.add(" ");
				guiaClie.add(" ");
				tipGuia.add(" ");
				blanco.add("Tasa Arriendo");
				numGuia.add(" ");
				fechGuia.add(" ");
				guiaClie.add(" ");
				tipGuia.add(" ");
				blanco.add(mapDiccionario.get("Arriendo")+" Mes");
				numGuia.add("Nro.Mov:");
				fechGuia.add("Fecha:");
				guiaClie.add("Nro.Ref");
				tipGuia.add("Tipo.Mov: ");
				blanco.add(mapDiccionario.get("Arriendo")+" Dia");
				
				
				Fechas desde = Fechas.obtenerFechaDesdeStrAAMMDD(fechaDesde);
				Fechas hasta = Fechas.obtenerFechaDesdeStrAAMMDD(fechaHasta);
				Fechas hastaAux = Fechas.obtenerFechaDesdeStrAAMMDD(fechaHasta);
				Calendar hastaMas1 = hastaAux.getFechaCal();
				hastaMas1.add(Calendar.DAY_OF_MONTH, 1);
				
				
				numGuia.add("STOCK INICIAL");
					String aux2[] = fechaDesde.split("-");
				fechGuia.add(""+aux2[2]+"/"+aux2[1]+"/"+aux2[0]);
				guiaClie.add("");
				tipGuia.add(" ");
				blanco.add(" ");
				while (rs1.next()) {
					idsGuia.add(rs1.getLong(6));
					Fechas fechaGuia = Fechas.obtenerFechaDesdeStrAAMMDD(rs1.getString(4));
					if(!(fechaGuia.fechaCal.before(desde.fechaCal)||fechaGuia.fechaCal.after(hasta.fechaCal))) {
						numGuia.add(rs1.getString(1));
						fechGuia.add(rs1.getString(2));
						guiaClie.add(rs1.getString(5));
						tipGuia.add(rs1.getString(3));
						blanco.add(" ");
					}
				}
				rs1.close();
				smt1.close();
				
				numGuia.add("STOCK FINAL");
					String aux3[] = fechaHasta.split("-");
				fechGuia.add(""+aux3[2]+"/"+aux3[1]+"/"+aux3[0]);
				guiaClie.add(" ");
				tipGuia.add(" ");
				blanco.add("");
				
				numGuia.add("");
				fechGuia.add("CFI");
				guiaClie.add(" ");
				tipGuia.add("");
				blanco.add("");
			
				
				numGuia.add("TOTAL MON");
				fechGuia.add("ORIGEN");
				guiaClie.add(" ");
				tipGuia.add("");
				blanco.add("");
				
				numGuia.add("TOTAL EN");
				fechGuia.add(mapDiccionario.get("MONEDA PRINCIPAL"));
				guiaClie.add(" ");
				tipGuia.add("");
				blanco.add("");
				
				
				numGuia.add("Excedentes");
				fechGuia.add(" ");
				guiaClie.add(" ");
				tipGuia.add(" ");
				blanco.add("");
				
				numGuia.add("COMPROMETIDO");
				fechGuia.add("EN COTIZACION");
				guiaClie.add(" ");
				tipGuia.add(" ");
				blanco.add("");
				
				numGuia.add("DESPACHADO");
				fechGuia.add("DE COTIZACION");
				guiaClie.add(" ");
				tipGuia.add(" ");
				blanco.add("");
				
				numGuia.add("SALDO POR");
				fechGuia.add("DESPACHAR");
				guiaClie.add("DE COTIZACION");
				tipGuia.add(" ");
				blanco.add("");
			
				PreparedStatement smt2 = con
						.prepareStatement(" select distinct  guia.numero,guia.fecha " +
								" from `"+db+"`.guia " +
								" left join `"+db+"`.movimiento on movimiento.id_guia = guia.id " +
								" where movimiento.esVenta= ?  and id_bodegaEmpresa = ? "+
								" order by guia.fecha,guia.numero; "); 
				smt2.setString(1, esVenta.trim());
				smt2.setLong(2, id_bodegaEmpresa);
				ResultSet rs2 = smt2.executeQuery();
				List<List<String>> listaGuias = new ArrayList<List<String>>();
				while (rs2.next()) {
					List<String> aux = new ArrayList<String>();
					aux.add(rs2.getString(1));
					aux.add(rs2.getString(2));
					listaGuias.add(aux);
				}
				rs2.close();
				smt2.close();
				
				PreparedStatement smt3 = con
						.prepareStatement(" select distinct " +
								" grupo.nombre, " +
								" equipo.codigo, " +
								" equipo.nombre, " +
								" ifnull(moneda.nickName,''), " + 
								" ifnull(listaPrecio.precioVenta,0), " + 
								" ifnull(listaPrecio.precioReposicion,0), " + 
								" ifnull(listaPrecio.precioArriendo,0), " + 
								" ifnull(listaPrecio.id_unidadTiempo,1), " +
								" ifnull(bodegaEmpresa.tasaDescto,0), " +
								" ifnull(dctoGrupo.tasaDescto,0), " +
								" ifnull(dctoEquipo.tasaDescto,0), " +
								" movimiento.id_cotizacion, " +
								" ifNull(cotizacion.numero,0), " +
								" equipo.id, " +
								" ifnull(moneda.id,1), " +
								" equipo.kg, " +
								" equipo.m2 " +
								" from `"+db+"`.movimiento " + 
								" left join `"+db+"`.cotizacion on cotizacion.id = movimiento.id_cotizacion " +
								" left join `"+db+"`.equipo on equipo.id = movimiento.id_equipo " + 
								" left join `"+db+"`.listaPrecio on listaPrecio.id_equipo = equipo.id and listaPrecio.id_cotizacion = movimiento.id_cotizacion and listaPrecio.id_bodegaEmpresa = " +id_bodegaEmpresa +
								" left join `"+db+"`.grupo on grupo.id = equipo.id_grupo  " + 
								" left join `"+db+"`.moneda on moneda.id = listaPrecio.id_moneda " + 
								" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa " +
								" left join `"+db+"`.dctoGrupo on dctoGrupo.id_bodegaEmpresa = movimiento.id_bodegaEmpresa and dctoGrupo.id_grupo = equipo.id_grupo " +
								" left join `"+db+"`.dctoEquipo on dctoEquipo.id_bodegaEmpresa = movimiento.id_bodegaEmpresa and dctoEquipo.id_equipo = movimiento.id_equipo " +
								" where " + 
								" movimiento.id_bodegaEmpresa=? and listaPrecio.id_unidadTiempo is not null"+
								" and equipo.nombre is not null and movimiento.esVenta= ? "+ 
								" order by grupo.nombre,equipo.nombre;");
				smt3.setLong(1, id_bodegaEmpresa);
				smt3.setString(2, esVenta.trim());
				ResultSet rs3 = smt3.executeQuery();
				List<List<String>> listaCodigos = new ArrayList<List<String>>();
				
				
				
				while (rs3.next()) {
					
					Double factor = UnidadTiempo.equivalencia(con, db).get(rs3.getLong(8));
					
					List<String> aux = new ArrayList<String>();
					
					switch(dec.get(rs3.getLong(15)).toString()) {
					 case "0": myformatMoneda = new DecimalFormat("#,##0"); break;
					 case "2": myformatMoneda = new DecimalFormat("#,##0.00"); break;
					 case "4": myformatMoneda = new DecimalFormat("#,##0.0000"); break;
					 case "6": myformatMoneda = new DecimalFormat("#,##0.000000"); break;
					 default:  break;
					}
					
					
					aux.add(rs3.getString(1)); //grupo
					aux.add(rs3.getString(2));  //codigo
					aux.add(rs3.getString(3));  //equipo
					aux.add(rs3.getString(4));  //moneda
					aux.add(myformatMoneda.format(rs3.getDouble(5))); //precioventa
					Double tasa=(double)0;
					
					Double tasaDcto = 1-((1-rs3.getDouble(9))*(1-rs3.getDouble(10))*(1-rs3.getDouble(11)));
					
					if(rs3.getDouble(7)>0&&rs3.getDouble(5)>0) tasa=((rs3.getDouble(7)*30/factor*(1-tasaDcto))/rs3.getDouble(5))*100;
					if(esVenta.equals("1")) {
						aux.add("0 %"); //tasaArriendo
						aux.add("0"); // arriendo mes
						aux.add("0");  //arriendo dia
					}else {
						aux.add(myformatdouble2.format(tasa)+" %"); //tasaArriendo
						aux.add(myformatMoneda.format(rs3.getDouble(7)*30/factor*(1-tasaDcto))); // arriendo mes
						aux.add(myformatdouble4.format(rs3.getDouble(7)/factor*(1-tasaDcto)));  //arriendo dia
					}
					aux.add(rs3.getString(12)); //  8 id cotizacion
					aux.add(rs3.getString(13)); //  9 numero coti
					aux.add(rs3.getString(14)); //  10 idEquipo
					aux.add(rs3.getString(15)); //  11 idMoneda
					aux.add(rs3.getString(16)); //  12 kg
					aux.add(rs3.getString(17)); //  13 m2
					listaCodigos.add(aux);
				}
				rs3.close();
				smt3.close();
				
				PreparedStatement smt4 = con
						.prepareStatement(" select " +
								" equipo.codigo,    " +
								" guia.numero, " +
								" sum(if(movimiento.id_tipoMovimiento=2,movimiento.cantidad*-1,movimiento.cantidad)),   " +
								" movimiento.id_cotizacion "+
								" from `"+db+"`.movimiento " +
								" left join `"+db+"`.equipo on equipo.id = movimiento.id_equipo " +
								" left join `"+db+"`.guia on guia.id = movimiento.id_guia " +
								" where movimiento.id_bodegaEmpresa =  ? "+  
								" and guia.fecha is not null  and movimiento.esVenta= ?  " +
								" group by equipo.codigo, guia.numero, movimiento.id_cotizacion  " + 
								" order by guia.fecha,guia.numero,equipo.codigo;");
				smt4.setLong(1, id_bodegaEmpresa);
				smt4.setString(2, esVenta.trim());
				ResultSet rs4 = smt4.executeQuery();
				Map<String,String> codGuiaCant = new HashMap<String,String>();
				while (rs4.next()) {
					codGuiaCant.put(rs4.getString(1).trim()+rs4.getString(2).trim()+"-"+rs4.getString(4).trim(), rs4.getString(3));
				}
				rs4.close();
				smt4.close();
				
				lista.add(numGuia);
				lista.add(fechGuia);
				lista.add(guiaClie);
				lista.add(tipGuia);
				lista.add(blanco);
				
				String auxiliarDeReparacion="";
				
				BodegaEmpresa bodegaEmpresa = BodegaEmpresa.findXIdBodega(con, db, id_bodegaEmpresa);
				
				Fechas hastaAjustar = new Fechas();
				Fechas desdeAjustar = Fechas.obtenerFechaDesdeStrAAMMDD(fechaDesde);
				hastaAjustar = Fechas.obtenerFechaDesdeStrAAMMDD(fechaHasta);
				String deAjustado = Fechas.addMeses(desdeAjustar.getFechaCal(), -6).getFechaStrAAMMDD();
				String aAjustado = Fechas.addMeses(hastaAjustar.getFechaCal(), -1).getFechaStrAAMMDD();
				List<Long> listIdGuia_entreFechas = ModCalc_GuiasPer.listIdGuia_entreFecha(con, db, deAjustado, aAjustado);
				
				Map<Long, List<Inventarios>> mapGuiasPer = Inventarios.guiasPerAllBodegas(con, db, listIdGuia_entreFechas);
				
				
				Map<String,Double> mapDespachado = OtDespachado.mapIdCotiIdEquipVsSumaDespachado(con, db, idsGuia, esVenta);
				List<String> idsCoti = new ArrayList<String>();
				for(Map.Entry<String, Double> entry: mapDespachado.entrySet()) {
					String[] clave = entry.getKey().split("_");
					idsCoti.add(clave[0]);
				}
				Set<String> conjunto = new HashSet<>(idsCoti);  // Eliminar duplicados
				idsCoti = new ArrayList<>(conjunto);
				Map<String,Double> mapComprometido = Cotizacion.mapIdCotiIdEquipVsSumaComprometido(con, db, idsCoti, esVenta);
				
				
				
				
				for(int i=0;i<listaCodigos.size();i++){
					if(!auxiliarDeReparacion.equals(listaCodigos.get(i).get(0)+listaCodigos.get(i).get(9)+listaCodigos.get(i).get(1)+listaCodigos.get(i).get(2))) {
					auxiliarDeReparacion=listaCodigos.get(i).get(0)+listaCodigos.get(i).get(9)+listaCodigos.get(i).get(1)+listaCodigos.get(i).get(2);
					
						List<String> aux = new ArrayList<String>();
						aux.add(listaCodigos.get(i).get(0)); //grupo
						aux.add(listaCodigos.get(i).get(9)); //numero cotizacion
						aux.add(listaCodigos.get(i).get(1)); //codigo
						aux.add(listaCodigos.get(i).get(2)); //equipo
						
						// kg por unidad
						Double kg = Double.parseDouble(listaCodigos.get(i).get(12).trim());
						if(kg > 0) {
							aux.add(myformatdouble2.format(kg));
						}else {
							aux.add("");
						}
						// m2 por unidad
						Double m2 = Double.parseDouble(listaCodigos.get(i).get(13).trim());
						if(m2 > 0) {
							aux.add(myformatdouble2.format(m2));
						}else {
							aux.add("");
						}
						aux.add(listaCodigos.get(i).get(3)); //moneda
						aux.add(listaCodigos.get(i).get(4)); //precioVenta
						aux.add(listaCodigos.get(i).get(5)); //tasaArriendo
						aux.add(listaCodigos.get(i).get(6)); //precioArriendo mes
						aux.add(listaCodigos.get(i).get(7)); //precioArriendo dia
						
						Double cantStockIni = (double) 0;
						Double cantStockFin=(double)0;
						Double arriendo = (double) 0;
						Long totalDias = (long)0;
						Long dias = (long)0;
						
						dias = Math.round( (double) (hasta.fechaCal.getTimeInMillis() - desde.fechaCal.getTimeInMillis())  /  (24 * 60 * 60 * 1000)  ) + 1;
						
						if((long)bodegaEmpresa.baseCalculo==(long)2) {
							String[] mes = fechaDesde.split("-");
							
							if(!mes[1].equals("02")&&dias==31) dias=(long)30;
							if(mes[1].equals("02")&&(dias==28||dias==29))  dias=(long)30;
							
							if(dias>31) {
								Long diasDePaso=(long)0;
								Double factorMeses=(double) dias/30;
								Long iPart = factorMeses.longValue();
								Double fPart = factorMeses - iPart;
								
								dias=(long)0;
								Long auxMes= Long.parseLong(mes[1].trim());
								for(int j=0;j<iPart;j++) {
									if(auxMes==2)  diasDePaso=(long)28;
									if(auxMes==1||auxMes==3||auxMes==5||auxMes==7||auxMes==8||auxMes==10||auxMes==12)  diasDePaso=(long)30;
									if(auxMes==4||auxMes==6||auxMes==9||auxMes==11)  diasDePaso=(long)30;
									dias=dias+diasDePaso;
								}
								fPart=fPart*30; 
								dias=dias+fPart.longValue();
							}
						}
						
						
						for(int k=0;k<listaGuias.size();k++){
							Fechas fechaGuia = Fechas.obtenerFechaDesdeStrAAMMDD(listaGuias.get(k).get(1));
							if(fechaGuia.fechaCal.before(desde.fechaCal)){
								String keyAux=listaCodigos.get(i).get(1).trim()+listaGuias.get(k).get(0).trim()+"-"+listaCodigos.get(i).get(8);
								String cantidad = codGuiaCant.get(keyAux);
								if(cantidad==null){
								}else{
									cantStockIni = cantStockIni + Double.parseDouble(cantidad.trim());
								}
							}
							if(fechaGuia.fechaCal.before(hastaMas1)){
								String cantidad = codGuiaCant.get(listaCodigos.get(i).get(1).trim()+listaGuias.get(k).get(0).trim()+"-"+listaCodigos.get(i).get(8));
								if(cantidad!=null){
									cantStockFin = cantStockFin + Double.parseDouble(cantidad.trim());
								}
							}
						}
						
						
						aux.add(myformatdouble2.format(cantStockIni));
						String auxNum = listaCodigos.get(i).get(4).trim();
		 	   			if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
						Double precioVenta=(double)0;try {precioVenta  = myformatdouble.parse(auxNum).doubleValue();}catch(Exception e) {};
						
						auxNum = listaCodigos.get(i).get(7).trim();
		 	   			if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
						Double precioDia=(double)0;try {precioDia  = myformatdouble.parse(auxNum).doubleValue();}catch(Exception e) {};
						if(esVenta.equals("0")) {
							
							// AJUSTES POR SALDOS DIAS DE GRACIA
								Long nDiaGraciaEnvio = bodegaEmpresa.getnDiaGraciaEnvio();
								Double ajustePorGracia = (double)0;
								if(nDiaGraciaEnvio > 0) {
									//List<Inventarios> guiasPer = new ArrayList<Inventarios>();
									//List<Long> auxListIdBodegaEmpresa = new ArrayList<Long>();
									//auxListIdBodegaEmpresa.add(bodegaEmpresa.getId());
									
									List<Inventarios> guiasPer = mapGuiasPer.get(bodegaEmpresa.getId());
									
									
									//guiasPer = Inventarios.guiasPer(con, db, auxListIdBodegaEmpresa, listIdGuia_entreFechas);
									for(int k=0; k<guiasPer.size(); k++) {
										String idEquipo = listaCodigos.get(i).get(10);
										String idCotizacion = listaCodigos.get(i).get(8);
										if(	(long) guiasPer.get(k).id_equipo == Long.parseLong(idEquipo)
												&& (long) guiasPer.get(k).id_bodegaEmpresa == (long) bodegaEmpresa.getId()
												&& (long) guiasPer.get(k).id_cotizacion == Long.parseLong(idCotizacion) )
										{
											Fechas fechaGuia = Fechas.obtenerFechaDesdeStrAAMMDD(guiasPer.get(k).fechaGuia);
											Long diasGuia = (long) Fechas.diasEntreFechas(fechaGuia.getFechaCal(), hastaAjustar.getFechaCal());
											if(guiasPer.get(k).id_tipoMovimiento == 1) {
												diasGuia = diasGuia - nDiaGraciaEnvio + 1;
												if(diasGuia < 0 ) {
													ajustePorGracia += guiasPer.get(k).cantidad * diasGuia * precioDia ;
												}
											}
										}
									}
								}
							// FIN AJUSTES
								
								arriendo = cantStockIni * dias * precioDia + ajustePorGracia;
							
						}else {
							arriendo=(double)0;
						}
						totalDias=totalDias+dias;
						Double cantDeDespachos=(double)0;
						int flag=0;
						for(int k=0;k<listaGuias.size();k++){
							Fechas fechaGuia = Fechas.obtenerFechaDesdeStrAAMMDD(listaGuias.get(k).get(1));
							if(!(fechaGuia.fechaCal.before(desde.fechaCal)||fechaGuia.fechaCal.after(hasta.fechaCal))) {
								String keyAux=listaCodigos.get(i).get(1).trim()+listaGuias.get(k).get(0).trim()+"-"+listaCodigos.get(i).get(8);
								String cantidad = codGuiaCant.get(keyAux);
								if(cantidad==null){
									aux.add(" ");
								}else{
									if (Double.parseDouble(cantidad.trim())!=0) {
										flag =1;
									}
									aux.add(cantidad);
									Long diasPeriodo = (long)0;
									
									if(Double.parseDouble(cantidad.trim())>0) {
										cantDeDespachos=cantDeDespachos+Double.parseDouble(cantidad.trim());
										diasPeriodo = Math.round ( (double) (hasta.fechaCal.getTimeInMillis() - fechaGuia.fechaCal.getTimeInMillis() ) / (24 * 60 * 60 * 1000) );
										diasPeriodo = diasPeriodo + bodegaEmpresa.cobraDiaDespacho - bodegaEmpresa.nDiaGraciaEnvio;
										if(diasPeriodo < 0) {
											diasPeriodo = (long)0;
										}
									}else {
										
										diasPeriodo = Math.round ( (double) ( hasta.fechaCal.getTimeInMillis() - fechaGuia.fechaCal.getTimeInMillis() )/(24 * 60 * 60 * 1000));
										diasPeriodo=diasPeriodo + bodegaEmpresa.nDiaGraciaRegreso;
										if((double)bodegaEmpresa.tratoDevoluciones==(double)2) diasPeriodo=diasPeriodo-1;
										if(diasPeriodo < 0) {
											diasPeriodo = (long)0;
										}
									}
									if((long)bodegaEmpresa.baseCalculo==(long)2) {
										String[] mes = fechaDesde.split("-");
										if(!mes[1].equals("02")&&diasPeriodo==31) diasPeriodo=(long)30;
										if(diasPeriodo>31) {
											Long diasDePaso=(long)0;
											Double factorMeses=(double) diasPeriodo/30;
											Long iPart = factorMeses.longValue();
											Double fPart = factorMeses - iPart;
											
											diasPeriodo=(long)0;
											Long auxMes= Long.parseLong(mes[1].trim());
											for(int j=0;j<iPart;j++) {
												if(auxMes==2)  diasDePaso=(long)28;
												if(auxMes==1||auxMes==3||auxMes==5||auxMes==7||auxMes==8||auxMes==10||auxMes==12)  diasDePaso=(long)30;
												if(auxMes==4||auxMes==6||auxMes==9||auxMes==11)  diasDePaso=(long)30;
												diasPeriodo=diasPeriodo+diasDePaso;
											}
											fPart=fPart*30; 
											diasPeriodo=diasPeriodo+fPart.longValue();
										}
										if(Double.parseDouble(cantidad.trim())<0&&(mes[1].equals("01")||mes[1].equals("03")||mes[1].equals("05")||mes[1].equals("07")||mes[1].equals("08")||mes[1].equals("10")||mes[1].equals("12"))) {
											diasPeriodo=diasPeriodo-1;
										}
										if(Double.parseDouble(cantidad.trim())<0&&(mes[1].equals("02"))) {
											Fechas fecha = Fechas.obtenerFechaDesdeStrAAMMDD(fechaDesde);
											int diasMes = fecha.getFechaCal().getActualMaximum(Calendar.DAY_OF_MONTH);
											if(diasMes==28) { diasPeriodo=diasPeriodo+2; }else { diasPeriodo=diasPeriodo+1; }
											
										}
										
									}
									if(esVenta.equals("0")) {
										arriendo=arriendo+Double.parseDouble(cantidad.trim())*diasPeriodo*precioDia;
									}else {
										arriendo=arriendo+Double.parseDouble(cantidad.trim())*precioVenta;
									}
									
								}
								
							}
						}
						
						
						aux.add(myformatdouble2.format(cantStockFin));
						Double pventa = (double)0;
						
						idMoneda = listaCodigos.get(i).get(11);
						
						switch(dec.get(Long.parseLong(idMoneda)).toString()) {
						 case "0": myformatMoneda = new DecimalFormat("#,##0"); break;
						 case "2": myformatMoneda = new DecimalFormat("#,##0.00"); break;
						 case "4": myformatMoneda = new DecimalFormat("#,##0.0000"); break;
						 case "6": myformatMoneda = new DecimalFormat("#,##0.000000"); break;
						 default:  break;
						}
						
						auxNum = listaCodigos.get(i).get(4).trim();
		 	   			if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
						try {pventa=myformatdouble.parse(auxNum).doubleValue();}catch(Exception e) {}
						Double cfi = bodegaEmpresa.tasaCfi;
						Double totalCfi = cantDeDespachos * pventa * cfi/100;
						if(esVenta.equals("1")||totalCfi<0) totalCfi=(double)0;
						aux.add(myformatMoneda.format(totalCfi));
						aux.add(myformatMoneda.format(arriendo+totalCfi));
						granTotalArriendo=granTotalArriendo+arriendo+totalCfi;
						
						
						Double auxTasaCambio = (double) 1;
						
						
						
						if(idMoneda.equals("2")) {
							auxTasaCambio = usd;
						}else if(idMoneda.equals("3")){
							auxTasaCambio = eur;
						}else if(idMoneda.equals("4")){
							auxTasaCambio = uf;
						}
						
						
						switch(dec.get((long)1).toString()) {
						 case "0": myformatMoneda = new DecimalFormat("#,##0"); break;
						 case "2": myformatMoneda = new DecimalFormat("#,##0.00"); break;
						 case "4": myformatMoneda = new DecimalFormat("#,##0.0000"); break;
						 case "6": myformatMoneda = new DecimalFormat("#,##0.000000"); break;
						 default:  break;
						}
						
						aux.add(myformatMoneda.format((arriendo+totalCfi)*auxTasaCambio));
						
						
						
						
						Double auxExcedente = excedentes.get(aux.get(2)+"_"+aux.get(1)); 
						if(auxExcedente==null) {
							auxExcedente = (double)0;
							aux.add("");
						}else {
							aux.add(myformatdouble.format(auxExcedente));
						}

						String idCoti = listaCodigos.get(i).get(8);
						String idEquipo = listaCodigos.get(i).get(10);
						Double comprometido = mapComprometido.get(idCoti+"_"+idEquipo);
						if(comprometido == null) {
							comprometido = (double)0;
						}
						aux.add(DecimalFormato.formato(comprometido, (long)2));
						
						Double despachado = mapDespachado.get(idCoti+"_"+idEquipo);
						if(despachado == null) {
							despachado = (double)0;
						}
						aux.add(DecimalFormato.formato(despachado, (long)2));
						Double saldo = comprometido - despachado;
						if(saldo < 0) {
							saldo = (double) 0;
						}
						aux.add(DecimalFormato.formato(saldo, (long)2));
						
						
						
						
						if(auxExcedente!=0 || cantStockFin!=0 || cantStockIni!=0 || flag==1) {
							totArr += (arriendo+totalCfi)*auxTasaCambio;
							lista.add(aux);
						}
						
					}
				}
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		
		
	//	Double totCfi =(double)0;
	//	Double totArr =(double)0;
		
		
		//AJUSTES EP
		List<String> aux3 = new ArrayList<String>();
		
		switch(dec.get((long)1).toString()) {
		 case "0": myformatMoneda = new DecimalFormat("#,##0"); break;
		 case "2": myformatMoneda = new DecimalFormat("#,##0.00"); break;
		 case "4": myformatMoneda = new DecimalFormat("#,##0.0000"); break;
		 case "6": myformatMoneda = new DecimalFormat("#,##0.000000"); break;
		 default:  break;
		}
		
		if(esVenta.equals("0")) {
			List<List<String>> detalleAjuste = AjustesEP.detalleAjuste(con, db, id_bodegaEmpresa, fechaDesde, fechaHasta);
			
			for(List<String> ajuste: detalleAjuste) {
				
				Double auxTot = (double)0;
				try {
					String auxNum = ajuste.get(1).trim();
	 	   			if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
					auxTot = myformatdouble.parse(auxNum).doubleValue();
				} catch (ParseException e) {}
				if(auxTot<0||auxTot>0) {
					List<String> aux2 = new ArrayList<String>();
					aux2.add("");
					aux2.add(""); 
					aux2.add(""); 
					aux2.add(ajuste.get(0)); 
					aux2.add(""); 
					aux2.add(""); 
					aux2.add(""); 
					aux2.add(""); 
					aux2.add("");
					aux2.add("");
					aux2.add("");
					for(int cell=11; cell<lista.get(0).size(); cell++) {
						if(cell==lista.get(0).size()-2) {
							totArr+=auxTot;
							aux2.add(myformatMoneda.format(auxTot));
						}else {
							aux2.add("");
						}
					}
					lista.add(aux2);
				}
			}
			
			
			
			//TOTALES
				
					aux3.add("TOTALES (ALQUILER)");
					aux3.add(""); 
					aux3.add(""); 
					aux3.add(""); 
					aux3.add(""); 
					aux3.add(""); 
					aux3.add(""); 
					aux3.add(""); 
					aux3.add("");
					aux3.add("");
					aux3.add("");
					for(int cell=11; cell<lista.get(0).size(); cell++) {
						if(cell == lista.get(0).size()-3) {
							aux3.add("");
						//	aux3.add(myformatMoneda.format(totCfi));
						}else if(cell==lista.get(0).size()-5){
							aux3.add(myformatMoneda.format(totArr));
						}else {
							aux3.add("");
						}
					}
		}else {
			List<List<String>> detalleAjuste = AjustesEP.detalleAjuste(con, db, id_bodegaEmpresa, fechaDesde, fechaHasta);
			for(List<String> ajuste: detalleAjuste) {
				Double auxTot = (double)0;
				try {
					String auxNum = ajuste.get(2).trim();
	 	   			if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
					auxTot = myformatdouble.parse(auxNum).doubleValue();
				} catch (ParseException e) {}
				if(auxTot<0||auxTot>0) {
					List<String> aux2 = new ArrayList<String>();
					aux2.add("");
					aux2.add(""); 
					aux2.add(""); 
					aux2.add(ajuste.get(0)); 
					aux2.add(""); 
					aux2.add(""); 
					aux2.add(""); 
					aux2.add(""); 
					aux2.add("");
					aux2.add("");
					aux2.add("");
					for(int cell=11;cell<lista.get(0).size();cell++) {
						if(cell==lista.get(0).size()-2) {
							totArr+=auxTot;
							aux2.add(myformatMoneda.format(auxTot));
						}else {
							aux2.add("");
						}
					}
					lista.add(aux2);
				}
			}
			
			//TOTALES
					
					aux3.add("TOTALES");
					aux3.add(""); 
					aux3.add(""); 
					aux3.add(""); 
					aux3.add(""); 
					aux3.add(""); 
					aux3.add(""); 
					aux3.add(""); 
					aux3.add("");
					aux3.add("");
					aux3.add("");
					for(int cell=11;cell<lista.get(0).size();cell++) {
						if(cell==lista.get(0).size()-3) {
							aux3.add("");
						//	aux3.add(myformatMoneda.format(totCfi));
						}else if(cell==lista.get(0).size()-2){
							aux3.add(myformatMoneda.format(totArr));
						}else {
							aux3.add("");
						}
					}
					
		}
		
		//SUMA TOTALES POR COLUMNA
		
				
				List<String> aux = new ArrayList<String>();
				aux.add("SUBTOTAL CANTIDADES");
				aux.add(""); 
				aux.add(""); 
				aux.add(""); 
				aux.add(""); 
				aux.add(""); 
				aux.add(""); 
				aux.add(""); 
				aux.add("");
				aux.add("");
				aux.add("");
				for(int cell=11; cell < lista.get(0).size()-4; cell++) {
					Double totPorColl = (double)0;
					
					for(int coll=5; coll < lista.size()-3; coll++) {
						Double auxTot = (double)0;
						String auxNum = lista.get(coll).get(cell).trim();
		 	   			if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
						try {auxTot = myformatdouble.parse(auxNum).doubleValue();}catch(Exception e) {}
						totPorColl=totPorColl+auxTot;
					}
					
					if(cell > 12) {
						aux.add("");
					} else if(cell==lista.get(0).size()-4) {
						totArr = totPorColl;
					} else {
						aux.add(myformatdouble2.format(totPorColl));
					}
					
					//aux.add(myformatdouble2.format(totPorColl));
				}
				
				Double xx = (double)0;
				for(int coll=5; coll < lista.size(); coll++) {
					Double auxTot = (double)0;
					String auxNum = lista.get(coll).get(lista.get(0).size()-4).trim();
	 	   			if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
					try {auxTot = myformatdouble.parse(auxNum).doubleValue();}catch(Exception e) {}
					xx += auxTot;
				}
				aux.add(myformatdouble2.format(xx));
				
				aux.add("");
				aux.add("");
				aux.add("");
				
				lista.add(aux);
				
				List<String> auxKg = new ArrayList<String>();
				auxKg.add("SUBTOTAL PESOS (KG)");
				auxKg.add(""); 
				auxKg.add(""); 
				auxKg.add(""); 
				auxKg.add(""); 
				auxKg.add(""); 
				auxKg.add(""); 
				auxKg.add(""); 
				auxKg.add("");
				auxKg.add("");
				auxKg.add("");
				for(int cell=11;cell<lista.get(0).size()-4;cell++) {
					Double totPorColl = (double)0;
					for(int coll=5;coll<lista.size();coll++) {
						
						Double auxTotCant = (double)0;
						String auxNum = lista.get(coll).get(cell).trim();
		 	   			if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
						try {auxTotCant = myformatdouble.parse(auxNum).doubleValue();}catch(Exception e) {}
						
						Double auxTotKg = (double)0;
						auxNum = lista.get(coll).get(4).trim();
		 	   			if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
						try {auxTotKg = myformatdouble.parse(auxNum).doubleValue();}catch(Exception e) {}
						
						totPorColl=totPorColl+(auxTotCant*auxTotKg);
					}
					
					if(cell>12) {
						auxKg.add("");
					}else {
						auxKg.add(myformatdouble2.format(totPorColl));
					}
				} 
				
				
				
				
				
				Double xxx = (double)0;
				for(int coll=5;coll<lista.size();coll++) {
					
					Double auxTotCant = (double)0;
					String auxNum = lista.get(coll).get(lista.get(0).size()-4).trim();
	 	   			if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
					try {auxTotCant = myformatdouble.parse(auxNum).doubleValue();}catch(Exception e) {}
					
					Double auxTotKg = (double)0;
					auxNum = lista.get(coll).get(4).trim();
	 	   			if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
					try {auxTotKg = myformatdouble.parse(auxNum).doubleValue();}catch(Exception e) {}
					
					xxx +=(auxTotCant*auxTotKg);
				}
				auxKg.add(myformatdouble2.format(xxx));
				
				auxKg.add("");
				auxKg.add("");
				auxKg.add("");
				
				lista.add(auxKg);
				
				List<String> auxValorizado = new ArrayList<String>();
				auxValorizado.add("SUBTOTAL VALORIZADO");
				auxValorizado.add(""); 
				auxValorizado.add(""); 
				auxValorizado.add(""); 
				auxValorizado.add(""); 
				auxValorizado.add(""); 
				auxValorizado.add(""); 
				auxValorizado.add(""); 
				auxValorizado.add("");
				auxValorizado.add("");
				auxValorizado.add("");
				
				switch(dec.get(Long.parseLong(idMoneda)).toString()) {
				 case "0": myformatMoneda = new DecimalFormat("#,##0"); break;
				 case "2": myformatMoneda = new DecimalFormat("#,##0.00"); break;
				 case "4": myformatMoneda = new DecimalFormat("#,##0.0000"); break;
				 case "6": myformatMoneda = new DecimalFormat("#,##0.000000"); break;
				 default:  break;
				}
				
				for(int cell=11;cell<lista.get(0).size()-4;cell++) {
					Double totPorColl = (double)0;
					for(int coll=5;coll<lista.size();coll++) {
						
						Double auxTotCant = (double)0;
						String auxNum = lista.get(coll).get(cell).trim();
		 	   			if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
						try {auxTotCant = myformatdouble.parse(auxNum).doubleValue();}catch(Exception e) {}
						
						Double auxTotVal = (double)0;
						auxNum = lista.get(coll).get(7).trim();
		 	   			if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
						try {auxTotVal = myformatdouble.parse(auxNum).doubleValue();}catch(Exception e) {}
						
						totPorColl=totPorColl+(auxTotCant*auxTotVal);
					}
					
					if(cell > 12) {
						auxValorizado.add("");
					}else {
						auxValorizado.add(myformatMoneda.format(totPorColl));
					}
					
				} 
				
				
				
				
				Double xxxx = (double)0;
				for(int coll=5;coll<lista.size();coll++) {
					
					Double auxTotCant = (double)0;
					String auxNum = lista.get(coll).get(lista.get(0).size()-4).trim();
	 	   			if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
					try {auxTotCant = myformatdouble.parse(auxNum).doubleValue();}catch(Exception e) {}
					
					Double auxTotVal = (double)0;
					auxNum = lista.get(coll).get(7).trim();
	 	   			if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
					try {auxTotVal = myformatdouble.parse(auxNum).doubleValue();}catch(Exception e) {}
					
					xxxx +=(auxTotCant*auxTotVal);
				}
				auxValorizado.add(myformatMoneda.format(xxxx));
				
				auxValorizado.add("");
				auxValorizado.add("");
				auxValorizado.add("");
				
				lista.add(auxValorizado);
				
				lista.add(aux3);
				
		return (lista);
	}
	
	public static File movimientosExcel(String db, List<List<String>> datos, Map<String,String> mapDiccionario, BodegaEmpresa bodega, String concepto, String fechaDesde, String fechaHasta) {

   		File tmp = TempFile.createTempFile("tmp","null");
		
		try {
			String path = "formatos/excel.xlsx";
			InputStream formato = Archivos.leerArchivo(path);
            Workbook libro = WorkbookFactory.create(formato);
            formato.close();
            
            // 0 negro 1 blanco 2 rojo 3 verde 4 azul 5 amarillo 19 celeste
            CellStyle titulo = libro.createCellStyle();
            Font font = libro.createFont();
            font.setBoldweight(Font.BOLDWEIGHT_BOLD);
            font.setColor((short)4);
            font.setFontHeight((short)(14*20));
            titulo.setFont(font);
            
            CellStyle subtitulo = libro.createCellStyle();
            Font font2 = libro.createFont();
            font2.setBoldweight(Font.BOLDWEIGHT_BOLD);
            font2.setColor((short)0);
            font2.setFontHeight((short)(12*20));
            subtitulo.setFont(font2);
            
            CellStyle encabezado = libro.createCellStyle();
            encabezado.setBorderBottom(CellStyle.BORDER_THIN);
            encabezado.setBorderTop(CellStyle.BORDER_THIN);
            encabezado.setBorderRight(CellStyle.BORDER_THIN);
            encabezado.setBorderLeft(CellStyle.BORDER_THIN);
            encabezado.setFillPattern(CellStyle.SOLID_FOREGROUND);
            encabezado.setFillForegroundColor((short)19);
            encabezado.setAlignment(CellStyle.ALIGN_CENTER);
            
            CellStyle detalle = libro.createCellStyle();
            detalle.setBorderBottom(CellStyle.BORDER_THIN);
            detalle.setBorderTop(CellStyle.BORDER_THIN);
            detalle.setBorderRight(CellStyle.BORDER_THIN);
            detalle.setBorderLeft(CellStyle.BORDER_THIN);
            
            
            
            
            Sheet hoja1 = libro.getSheetAt(0);
            Row row = null;
            Cell cell = null;
            
            row = hoja1.createRow(1);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("REPORTE MOVIMIENTOS DE "+concepto+" (VALORIZADO) POR "+mapDiccionario.get("BODEGA")+"/PROYECTO");
		
            row = hoja1.createRow(2);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(mapDiccionario.get("BODEGA")+"/PROYECTO: "+bodega.getNombre().toUpperCase());
			
			row = hoja1.createRow(3);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("EMPRESA: "+mapDiccionario.get("nEmpresa"));
			
			row = hoja1.createRow(4);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FECHA: "+Fechas.hoy().getFechaStrDDMMAA());
			
			row = hoja1.createRow(6);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("PERIODO: DESDE "+Fechas.DDMMAA(fechaDesde)+" HASTA "+Fechas.DDMMAA(fechaHasta));
			
			// encabezado de la tabla
			
			hoja1.setColumnWidth(1, 7*1000);
			hoja1.setColumnWidth(2, 5*1000);
			hoja1.setColumnWidth(3, 5*1000);
			hoja1.setColumnWidth(4, 10*1000);
			for(int i=5;i<datos.size()+10;i++){
				hoja1.setColumnWidth(i, 4*1000);
			}
		
			//INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
			InputStream x = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
            byte[] bytes = IOUtils.toByteArray(x);
            x.close();
            int pngIndex = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			Drawing draw = hoja1.createDrawingPatriarch();
			CreationHelper helper = libro.getCreationHelper();
			ClientAnchor anchor = helper.createClientAnchor();
	        //set top-left corner for the image
	        anchor.setCol1(6);
	        anchor.setRow1(1);
			Picture img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);
			
			//DETALLE DE LA TABLA
			int posRow = 8;
			for(int i=0;i<datos.size();i++){
				row = hoja1.createRow(posRow);
				int posCell = 0;
				Double aux = (double)0;
				for(int j=0;j<datos.get(i).size();j++){
					String dato = datos.get(i).get(j);
					if(i<5){
						posCell++; 
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(dato);
					}else{
						if(j<4||j==6){
							posCell++; 
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(dato);
						}else if(j==8) {
							if(dato.equals("")||dato.equals(" ")) {
								dato = "0";
							}
							posCell++; 
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
				            aux = Double.parseDouble(dato.replaceAll(",", "").replaceAll("%", "").trim());
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(aux/100);
						}else {
							if(dato.equals("")||dato.equals(" ")) {
								dato = "0";
							}
							posCell++; 
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
				            aux = Double.parseDouble(dato.replaceAll(",", ""));
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(aux);
						}
					}
				}
				posRow++;
			}
			
			
			posRow = posRow + 5;
			row = hoja1.createRow(posRow);
			cell = row.createCell(1);
			Hyperlink hiper = helper.createHyperlink(0);
			hiper.setAddress("https://www.inqsol.cl");
			cell.setHyperlink(hiper);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Documento generado desde MADA propiedad de INQSOL");
			
			
			// Write the output to a file tmp
			FileOutputStream fileOut = new FileOutputStream(tmp);
			libro.write(fileOut);
			fileOut.close();
			
		} catch (Exception e) {
			e.printStackTrace();
        }
	  return tmp;
	}
	
	
	public static List<List<String>> movGuiasPorEstadoPeriodo(Connection con, String db, List<List<String>> datos) {
		List<List<String>> lista = new ArrayList<List<String>>();
		
		Map<Long,BodegaEmpresa> mapBodega = BodegaEmpresa.mapAll(con, db);
		Map<Long,TipoEstado> mapEstado = TipoEstado.mapAll(con, db);
		Map<Long,Equipo> mapEquipo = Equipo.mapAllAll(con, db);
		Map<String,List<Double>> mapPrecios = ListaPrecio.mapListaPreciosAll(con, db);
		Map<Long,Moneda> mapMoneda = Moneda.mapMonedas(con, db);
		Map<Long,UnidadTiempo> mapUnTiempo = UnidadTiempo.mapUnidadTiempo(con, db);
		Map<Long,Cotizacion> mapCotizacion = Cotizacion.mapAll(con, db);
		
		for(List<String> d: datos) {
			List<String> aux = new ArrayList<String>();
			
			BodegaEmpresa bodega = mapBodega.get(Long.parseLong(d.get(0)));
			TipoEstado estado = mapEstado.get(Long.parseLong(d.get(1)));
			Equipo equipo = mapEquipo.get(Long.parseLong(d.get(4)));
			
			if(bodega!=null && estado !=null && equipo!=null) {
				Double cantidad = Double.parseDouble(d.get(5));
				
				List<Double> precio = mapPrecios.get(bodega.getId()+"_"+d.get(6)+"_"+equipo.getId());
				if(precio==null) {
					precio = new ArrayList<Double>();
					precio.add((double)0);	//  0 precio de reposicion
					precio.add((double)0);	//  1 precio de arriendo con dctos aplicados
					precio.add((double)4);	//  2 id unidadTiempo
					precio.add((double)1);	//  3 idMoneda
				}
				
				Long id_moneda = Long.parseLong(DecimalFormato.formato(precio.get(3), (long)0));
				String moneda = "";
				Long decimales = (long) 0;
				Moneda mon = mapMoneda.get(id_moneda);
				if(mon!=null) {
					moneda = mon.getNickName();
					decimales = mon.getNumeroDecimales();
				}
				
				Long id_unidadTiempo = Long.parseLong(DecimalFormato.formato(precio.get(2), (long)0));
				String unTiempo = "MES";
				UnidadTiempo unidadTiempo = mapUnTiempo.get(id_unidadTiempo);
				if(unidadTiempo!=null) {
					unTiempo = unidadTiempo.getNombre();
				}
				
				Long id_cotizacion = Long.parseLong(d.get(6));
				String numCotizacion = "0";
				Cotizacion coti = mapCotizacion.get(id_cotizacion);
				if(coti!=null) {
					numCotizacion = coti.getNumero().toString();
				}
			
				
				aux.add(bodega.getNombre());									// 0bodega
				aux.add(estado.getNombre());									// 1estado
				aux.add(equipo.getGrupo());										// 2grupo
				aux.add(equipo.getCodigo());									// 3codigo
				aux.add(equipo.getNombre());									// 4equipo
				aux.add(equipo.getUnidad());									// 5unidad
				aux.add(DecimalFormato.formato(cantidad, (long)2));				// 6cantidad
				
				aux.add(moneda);												// 7moneda
				aux.add(DecimalFormato.formato(precio.get(0), decimales));		// 8precio reposicion
				aux.add(unTiempo);											    // 9unidad tiempo
				aux.add(DecimalFormato.formato(precio.get(1), decimales));		// 10precio arriendo
				
				aux.add(d.get(2));												// 11guia
				aux.add(d.get(3));												// 12fecha AAMMDD
				aux.add(Fechas.DDMMAA(d.get(3)));								// 13fecha DDMMAA
				
				aux.add(numCotizacion);											// 14Numero cotizacion
				
				aux.add(bodega.getNameSucursal());								// 15 namesucursal
				
				lista.add(aux);
			}
		}
		
		return (lista);
	}
	
	public static File estadosExcelPeriodo(String db, Map<String,String> mapDiccionario, String desdeAAMMDD, String hastaAAMMDD, List<List<String>> listado) {
		
		File tmp = TempFile.createTempFile("tmp","null");
		
		try {
			String path = "formatos/excel.xlsx";
			InputStream formato = Archivos.leerArchivo(path);
            Workbook libro = WorkbookFactory.create(formato);
            formato.close();
            
            // 0 negro 1 blanco 2 rojo 3 verde 4 azul 5 amarillo 19 celeste
            CellStyle titulo = libro.createCellStyle();
            Font font = libro.createFont();
            font.setBoldweight(Font.BOLDWEIGHT_BOLD);
            font.setColor((short)4);
            font.setFontHeight((short)(14*20));
            titulo.setFont(font);
            
            CellStyle subtitulo = libro.createCellStyle();
            Font font2 = libro.createFont();
            font2.setBoldweight(Font.BOLDWEIGHT_BOLD);
            font2.setColor((short)0);
            font2.setFontHeight((short)(12*20));
            subtitulo.setFont(font2);
            
            CellStyle encabezado = libro.createCellStyle();
            encabezado.setBorderBottom(CellStyle.BORDER_THIN);
            encabezado.setBorderTop(CellStyle.BORDER_THIN);
            encabezado.setBorderRight(CellStyle.BORDER_THIN);
            encabezado.setBorderLeft(CellStyle.BORDER_THIN);
            encabezado.setFillPattern(CellStyle.SOLID_FOREGROUND);
            encabezado.setFillForegroundColor((short)19);
            encabezado.setAlignment(CellStyle.ALIGN_CENTER);
            
            CellStyle detalle = libro.createCellStyle();
            detalle.setBorderBottom(CellStyle.BORDER_THIN);
            detalle.setBorderTop(CellStyle.BORDER_THIN);
            detalle.setBorderRight(CellStyle.BORDER_THIN);
            detalle.setBorderLeft(CellStyle.BORDER_THIN);
            
            
            Sheet hoja1 = libro.getSheetAt(0);
            Row row = null;
            Cell cell = null;
            
            row = hoja1.createRow(1);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("REPORTE POR ESTADOS DE EQUIPOS (DEVOLUCIONES)");
		
            row = hoja1.createRow(2);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("PERIODO: DESDE "+Fechas.DDMMAA(desdeAAMMDD)+" HASTA "+Fechas.DDMMAA(hastaAAMMDD));
			
			row = hoja1.createRow(3);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("EMPRESA: "+mapDiccionario.get("nEmpresa"));
			
			row = hoja1.createRow(4);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FECHA: "+Fechas.hoy().getFechaStrDDMMAA());
			
			
			// encabezado de la tabla
			
			hoja1.setColumnWidth(1, 7*1000);
			hoja1.setColumnWidth(2, 4*1000);
			hoja1.setColumnWidth(3, 4*1000);
			hoja1.setColumnWidth(4, 3*1000);
			hoja1.setColumnWidth(5, 10*1000);
			for(int i=6;i<200+1;i++){
				hoja1.setColumnWidth(i, 4*1000);
			}
		
			int posCell = 0;
			
			row = hoja1.createRow(6);
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(mapDiccionario.get("BODEGA")+"/PROYECTO");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("ESTADO");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("GRUPO");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("CODIGO");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("EQUIPO");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("UN");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("CANTIDAD ESTADO");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("MON");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("PRECIO REPOSICION");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("UNIDAD TIEMPO");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("PRECIO "+mapDiccionario.get("ARRIENDO"));
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("COTI");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("GUIA");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FECHA");
			
			
			
			
			
			
		
			//INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
			InputStream x = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
            byte[] bytes = IOUtils.toByteArray(x);
            x.close();
            int pngIndex = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			Drawing draw = hoja1.createDrawingPatriarch();
			CreationHelper helper = libro.getCreationHelper();
			ClientAnchor anchor = helper.createClientAnchor();
	        //set top-left corner for the image
	        anchor.setCol1(11);
	        anchor.setRow1(1);
			Picture img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);
			
			
			
			
			//DETALLE DE LA TABLA
			int posRow = 7;
			for(int i=0;i<listado.size();i++){
					row = hoja1.createRow(posRow);
					posCell = 0;
					
					posCell++; 
		            cell = row.createCell(posCell);
		            cell.setCellStyle(detalle);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(listado.get(i).get(0));
					
					posCell++; 
		            cell = row.createCell(posCell);
		            cell.setCellStyle(detalle);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(listado.get(i).get(1));
					
					posCell++; 
		            cell = row.createCell(posCell);
		            cell.setCellStyle(detalle);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(listado.get(i).get(2));
					
					posCell++; 
		            cell = row.createCell(posCell);
		            cell.setCellStyle(detalle);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(listado.get(i).get(3));
					
					posCell++; 
		            cell = row.createCell(posCell);
		            cell.setCellStyle(detalle);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(listado.get(i).get(4));
					
					posCell++; 
		            cell = row.createCell(posCell);
		            cell.setCellStyle(detalle);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(listado.get(i).get(5));
					
					String dato = listado.get(i).get(6);
					if(dato.equals("")||dato.equals(" ")) {
						dato = "0";
					}
					posCell++; 
		            cell = row.createCell(posCell);
		            cell.setCellStyle(detalle);
		            Double aux = Double.parseDouble(dato.replaceAll(",", "").trim());
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue(aux);
					
					posCell++; 
		            cell = row.createCell(posCell);
		            cell.setCellStyle(detalle);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(listado.get(i).get(7));
					
					dato = listado.get(i).get(8);
					if(dato.equals("")||dato.equals(" ")) {
						dato = "0";
					}
					posCell++; 
		            cell = row.createCell(posCell);
		            cell.setCellStyle(detalle);
		            aux = Double.parseDouble(dato.replaceAll(",", "").trim());
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue(aux);
					
					posCell++; 
		            cell = row.createCell(posCell);
		            cell.setCellStyle(detalle);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(listado.get(i).get(9));
					
					dato = listado.get(i).get(10);
					if(dato.equals("")||dato.equals(" ")) {
						dato = "0";
					}
					posCell++; 
		            cell = row.createCell(posCell);
		            cell.setCellStyle(detalle);
		            aux = Double.parseDouble(dato.replaceAll(",", "").trim());
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue(aux);
					
					posCell++; 
		            cell = row.createCell(posCell);
		            cell.setCellStyle(detalle);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(listado.get(i).get(14));
					
					posCell++; 
		            cell = row.createCell(posCell);
		            cell.setCellStyle(detalle);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(listado.get(i).get(11));
					
					posCell++; 
		            cell = row.createCell(posCell);
		            cell.setCellStyle(detalle);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(listado.get(i).get(13));
					
					
					
					
				posRow++;
			}
			
			
			posRow = posRow + 5;
			row = hoja1.createRow(posRow);
			cell = row.createCell(1);
			Hyperlink hiper = helper.createHyperlink(0);
			hiper.setAddress("https://www.inqsol.cl");
			cell.setHyperlink(hiper);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Documento generado desde MADA propiedad de INQSOL");
			
			
			
			// Write the output to a file tmp
			FileOutputStream fileOut = new FileOutputStream(tmp);
			libro.write(fileOut);
			fileOut.close();
			
		} catch (Exception e) {
			e.printStackTrace();
        }
	  return tmp;
		
	}
	
	
	public static List<List<String>> movimientoGuiasSoloBodInternas(Connection con, String db, Long id_bodegaEmpresa, String fechaDesde, String fechaHasta) {
		List<List<String>> lista = new ArrayList<List<String>>();
		
		try {
			PreparedStatement smt1 = con
					.prepareStatement("select  distinct "
							+ " if(movimiento.id_compra > 0,factura.numero,guia.numero) as numero,  "
							+ " if(movimiento.id_compra > 0,concat(day(factura.fecha),'/',month(factura.fecha),'/',year(factura.fecha)),concat(day(guia.fecha),'/',month(guia.fecha),'/',year(guia.fecha))) as fecha,   "
							+ " if(movimiento.id_compra > 0,'Factura',if(guia.id_bodegaDestino=1,'Baja',if(movimiento.id_tipoMovimiento=2 and movimiento.esVenta=1,'Venta',if(tipoMovimiento.id=1,'Entrada','Salida')))) as tipo, "
							+ " if(movimiento.id_guia=0,factura.fecha,guia.fecha) as fecha,  "
							+ " ifnull(guia.numGuiaCliente,''),  "
							+ " id_tipoMovimiento "
							+ " from `"+db+"`.movimiento  "
							+ " left join `"+db+"`.guia on guia.id = movimiento.id_guia  "
							+ " left join `"+db+"`.compra on compra.id = movimiento.id_compra"
							+ " left join `"+db+"`.factura on factura.id = compra.id_factura"
							+ " left join `"+db+"`.tipoMovimiento on tipoMovimiento.id = id_tipoMovimiento   "
							+ " where movimiento.id_bodegaEmpresa =  ? "
							+ " and (guia.numero is not null or factura.numero is not null) "
							+ " and (movimiento.id_guia > 0 or movimiento.id_compra > 0)  "
							+ " order by guia.fecha,guia.numero;");
			smt1.setLong(1, id_bodegaEmpresa);

			ResultSet rs1 = smt1.executeQuery();
			List<String> numGuia = new ArrayList<String>();
			List<String> fechGuia = new ArrayList<String>();
			List<String> guiaClie = new ArrayList<String>();
			List<String> tipGuia = new ArrayList<String>();
			List<String> blanco = new ArrayList<String>();
				
			numGuia.add(" ");
			fechGuia.add(" ");
			guiaClie.add(" ");
			tipGuia.add(" ");
			blanco.add("Grupo");
			
			numGuia.add(" ");
			fechGuia.add(" ");
			guiaClie.add(" ");
			tipGuia.add(" ");
			blanco.add("Código");
			
			numGuia.add(" ");
			fechGuia.add(" ");
			guiaClie.add(" ");
			tipGuia.add(" ");
			blanco.add("Equipo");
			
			numGuia.add(" ");
			fechGuia.add(" ");
			guiaClie.add(" ");
			tipGuia.add(" ");
			blanco.add("kg");
			
			numGuia.add("Nro.Mov:");
			fechGuia.add("Fecha:");
			guiaClie.add("Nro.Ref");
			tipGuia.add("Tipo.Mov: ");
			blanco.add("m2");
			
			numGuia.add("");
			fechGuia.add("");
			guiaClie.add("");
			tipGuia.add("Stock");
			blanco.add("Inicial");
				
			Fechas desde = Fechas.obtenerFechaDesdeStrAAMMDD(fechaDesde);
			Fechas hasta = Fechas.obtenerFechaDesdeStrAAMMDD(fechaHasta);
			Fechas hastaAux = Fechas.obtenerFechaDesdeStrAAMMDD(fechaHasta);
			Calendar hastaMas1 = hastaAux.getFechaCal();
			hastaMas1.add(Calendar.DAY_OF_MONTH, 1);
			
			List<List<String>> listaGuias = new ArrayList<List<String>>();
			while (rs1.next()) {
				Fechas fechaGuia = Fechas.obtenerFechaDesdeStrAAMMDD(rs1.getString(4));
				if(!(fechaGuia.fechaCal.before(desde.fechaCal)||fechaGuia.fechaCal.after(hasta.fechaCal))) {
					numGuia.add(rs1.getString(1));
					fechGuia.add(rs1.getString(2));
					guiaClie.add(rs1.getString(5));
					String tipo = rs1.getString(3);
					tipGuia.add(tipo);
					blanco.add(" ");
				}
				List<String> aux = new ArrayList<String>();
				aux.add(rs1.getString(1));
				aux.add(rs1.getString(4));
				aux.add(rs1.getString(3));
				listaGuias.add(aux);
			}
			
			numGuia.add("");
			fechGuia.add("");
			guiaClie.add("");
			tipGuia.add("Stock");
			blanco.add("Final");
			
			rs1.close();
			smt1.close();
				
			PreparedStatement smt3 = con
					.prepareStatement(" select distinct " +
							" grupo.nombre, " +
							" equipo.codigo, " +
							" equipo.nombre, " +
							" equipo.id, " +
							" equipo.kg, " +
							" equipo.m2 " +
							" from `"+db+"`.movimiento " + 
							" left join `"+db+"`.equipo on equipo.id=movimiento.id_equipo " + 
							" left join `"+db+"`.grupo on grupo.id = equipo.id_grupo  " + 
							" where " + 
							" movimiento.id_bodegaEmpresa=? "+ 
							" and equipo.nombre is not null "+
							" order by grupo.nombre,equipo.nombre;");
			
			smt3.setLong(1, id_bodegaEmpresa);

			ResultSet rs3 = smt3.executeQuery();
			List<List<String>> listaCodigos = new ArrayList<List<String>>();
			
			while (rs3.next()) {
				List<String> aux = new ArrayList<String>();
				aux.add(rs3.getString(1)); //grupo
				aux.add(rs3.getString(2));  //codigo
				aux.add(rs3.getString(3));  //equipo
				aux.add(rs3.getString(4)); // idEquipo
				aux.add(rs3.getString(5)); // kg
				aux.add(rs3.getString(6)); // m2
				listaCodigos.add(aux);
			}
			rs3.close();
			smt3.close();
				
			PreparedStatement smt4 = con
					.prepareStatement("select "
							+ " equipo.codigo, "
							+ " if(movimiento.id_compra > 0,factura.numero,guia.numero) as numero,   "
							+ " sum(if(movimiento.id_tipoMovimiento=2,movimiento.cantidad*-1,movimiento.cantidad)) as cantidad, "
							+ " movimiento.id_cotizacion, "
							+ " if(movimiento.id_compra > 0,'Factura',if(guia.id_bodegaDestino=1,'Baja',if(movimiento.id_tipoMovimiento=2 and movimiento.esVenta=1,'Venta',if(tipoMovimiento.id=1,'Entrada','Salida')))) as tipo, "
							+ " if(movimiento.id_compra > 0,factura.fecha,guia.fecha) as fecha"
							+ " from `"+db+"`.movimiento "
							+ " left join `"+db+"`.equipo on equipo.id = movimiento.id_equipo "
							+ " left join `"+db+"`.guia on guia.id = movimiento.id_guia "
							+ " left join `"+db+"`.compra on compra.id = movimiento.id_compra "
							+ " left join `"+db+"`.factura on factura.id = compra.id_factura "
							+ " left join `"+db+"`.tipoMovimiento on tipoMovimiento.id = id_tipoMovimiento    "
							+ " where movimiento.id_bodegaEmpresa = ? "
							+ " and (guia.numero is not null or factura.numero is not null) "
							+ " and (movimiento.id_guia > 0 or movimiento.id_compra > 0)  "
							+ " group by equipo.codigo, numero , tipo "
							+ " order by guia.fecha,guia.numero,equipo.codigo;");
			smt4.setLong(1, id_bodegaEmpresa);
			ResultSet rs4 = smt4.executeQuery();
			Map<String,String> codGuiaCant = new HashMap<String,String>();
			while (rs4.next()) {
				codGuiaCant.put(rs4.getString(1).trim()+"_"+rs4.getString(2).trim()+"_"+rs4.getString(5)+"_"+rs4.getString(6), rs4.getString(3));
			}
			rs4.close();
			smt4.close();
		
			lista.add(numGuia);
			lista.add(fechGuia);
			lista.add(guiaClie);
			lista.add(tipGuia);
			lista.add(blanco);
				
			for(int i=0;i<listaCodigos.size();i++){
				
				List<String> aux = new ArrayList<String>();
				aux.add(listaCodigos.get(i).get(0)); //grupo
				aux.add(listaCodigos.get(i).get(1)); //codigo
				aux.add(listaCodigos.get(i).get(2)); //equipo
				
				// kg por unidad
				Double kg = Double.parseDouble(listaCodigos.get(i).get(4).trim());
				if(kg > 0) {
					aux.add(myformatdouble2.format(kg)); 
				}else {
					aux.add("");
				}
				// m2 por unidad
				Double m2 =  Double.parseDouble(listaCodigos.get(i).get(5).trim());
				if(m2 > 0) {
					aux.add(myformatdouble2.format(m2));
				}else {
					aux.add("");
				}
					
				
				Double totStockPorLinea =  (double)0;
				Double totCantPorLinea = (double)0;
				
				
				
				
				for(int k=0;k<listaGuias.size();k++){
					Fechas fechaGuia = Fechas.obtenerFechaDesdeStrAAMMDD(listaGuias.get(k).get(1));

					String keyAux = listaCodigos.get(i).get(1).trim()+"_"+listaGuias.get(k).get(0).trim()+"_"+listaGuias.get(k).get(2)+"_"+listaGuias.get(k).get(1);
					String cantidad = codGuiaCant.get(keyAux);
					
					if(!(fechaGuia.fechaCal.before(desde.fechaCal)||fechaGuia.fechaCal.after(hasta.fechaCal))) {
						if(cantidad == null){
							aux.add(" ");
						}else{
							totCantPorLinea += Double.parseDouble(cantidad.replaceAll(",", ""));
							aux.add(cantidad);
						}
						
					} else if(fechaGuia.fechaCal.before(desde.fechaCal)){
						if(cantidad != null){
							totStockPorLinea += Double.parseDouble(cantidad.replaceAll(",", ""));
						}
					}
				}
				aux.add(5, myformatdouble0.format(totStockPorLinea));
				aux.add(myformatdouble0.format(totStockPorLinea + totCantPorLinea));
				lista.add(aux);
				
			}
		} catch (SQLException e) {
				e.printStackTrace();
		}
		
		return (lista);
	}
	
	public static File reporteMovSoloBodInternas3Excel(String db, List<List<String>> datos, Map<String,String> mapDiccionario, BodegaEmpresa bodega, String fechaDesde, String fechaHasta) {

   		File tmp = TempFile.createTempFile("tmp","null");
		
		try {
			String path = "formatos/excel.xlsx";
			InputStream formato = Archivos.leerArchivo(path);
            Workbook libro = WorkbookFactory.create(formato);
            formato.close();
            
            // 0 negro 1 blanco 2 rojo 3 verde 4 azul 5 amarillo 19 celeste
            CellStyle titulo = libro.createCellStyle();
            Font font = libro.createFont();
            font.setBoldweight(Font.BOLDWEIGHT_BOLD);
            font.setColor((short)4);
            font.setFontHeight((short)(14*20));
            titulo.setFont(font);
            
            CellStyle subtitulo = libro.createCellStyle();
            Font font2 = libro.createFont();
            font2.setBoldweight(Font.BOLDWEIGHT_BOLD);
            font2.setColor((short)0);
            font2.setFontHeight((short)(12*20));
            subtitulo.setFont(font2);
            
            CellStyle encabezado = libro.createCellStyle();
            encabezado.setBorderBottom(CellStyle.BORDER_THIN);
            encabezado.setBorderTop(CellStyle.BORDER_THIN);
            encabezado.setBorderRight(CellStyle.BORDER_THIN);
            encabezado.setBorderLeft(CellStyle.BORDER_THIN);
            encabezado.setFillPattern(CellStyle.SOLID_FOREGROUND);
            encabezado.setFillForegroundColor((short)19);
            encabezado.setAlignment(CellStyle.ALIGN_CENTER);
            
            CellStyle detalle = libro.createCellStyle();
            detalle.setBorderBottom(CellStyle.BORDER_THIN);
            detalle.setBorderTop(CellStyle.BORDER_THIN);
            detalle.setBorderRight(CellStyle.BORDER_THIN);
            detalle.setBorderLeft(CellStyle.BORDER_THIN);
            
            
            
            
            Sheet hoja1 = libro.getSheetAt(0);
            Row row = null;
            Cell cell = null;
            
            row = hoja1.createRow(1);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("DETALLE MOVIMIENTO POR "+mapDiccionario.get("BODEGA")+" INTERNA ");
		
            row = hoja1.createRow(2);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(mapDiccionario.get("BODEGA")+" INTERNA: "+bodega.getNombre().toUpperCase());
			
			row = hoja1.createRow(3);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("EMPRESA: "+mapDiccionario.get("nEmpresa"));
			
			row = hoja1.createRow(4);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FECHA: "+Fechas.hoy().getFechaStrDDMMAA());
			
			row = hoja1.createRow(6);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("PERIODO: DESDE "+Fechas.DDMMAA(fechaDesde)+" HASTA "+Fechas.DDMMAA(fechaHasta));
			
			// encabezado de la tabla
			
			hoja1.setColumnWidth(1, 7*1000);
			hoja1.setColumnWidth(2, 5*1000);
			hoja1.setColumnWidth(3, 10*1000);
			for(int i=4;i<datos.size()+1;i++){
				hoja1.setColumnWidth(i, 4*1000);
			}
		
			//INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
			InputStream x = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
            byte[] bytes = IOUtils.toByteArray(x);
            x.close();
            int pngIndex = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			Drawing draw = hoja1.createDrawingPatriarch();
			CreationHelper helper = libro.getCreationHelper();
			ClientAnchor anchor = helper.createClientAnchor();
	        //set top-left corner for the image
	        anchor.setCol1(6);
	        anchor.setRow1(1);
			Picture img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);
			
			//DETALLE DE LA TABLA
			int posRow = 8;
			for(int i=0;i<datos.size();i++){
				row = hoja1.createRow(posRow);
				int posCell = 0;
				Double aux = (double)0;
				for(int j=0;j<datos.get(i).size();j++){
					String dato = datos.get(i).get(j);
					if(i<5){
						posCell++; 
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(dato);
					}else{
						if(j<3){
							posCell++; 
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(dato);
						}else {
							if(dato.equals("")||dato.equals(" ")) {
								dato = "0";
							}
							posCell++; 
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
				            aux = Double.parseDouble(dato.replaceAll(",", ""));
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(aux);
						}
					}
				}
				posRow++;
			}
			
			
			posRow = posRow + 5;
			row = hoja1.createRow(posRow);
			cell = row.createCell(1);
			Hyperlink hiper = helper.createHyperlink(0);
			hiper.setAddress("https://www.inqsol.cl");
			cell.setHyperlink(hiper);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Documento generado desde MADA propiedad de INQSOL");
			
			
			
			// Write the output to a file tmp
			FileOutputStream fileOut = new FileOutputStream(tmp);
			libro.write(fileOut);
			fileOut.close();
			
		} catch (Exception e) {
			e.printStackTrace();
        }
	  return tmp;
	}
	
	public static List<List<String>> movimientoGuiasPorProyecto(Connection con, String db, 
			Long id_proyecto, String esVenta, String fechaDesde, String fechaHasta, 
			String permisoPorBodega, String esPorSucursal, String id_sucursal, Map<String,String> mapDiccionario) {
		List<List<String>> lista = new ArrayList<List<String>>();
		esVenta = esVenta.trim();
		
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and bodegaEmpresa.id_sucursal = " + id_sucursal;
		}
		
		try {
			PreparedStatement smt1 = con
					.prepareStatement("select  "
							+ " guia.numero, "
							+ " concat(day(guia.fecha),'/',month(guia.fecha),'/',year(guia.fecha)), "
							+ " tipoMovimiento.nombre, "
							+ " guia.fecha, "
							+ " ifnull(guia.numGuiaCliente,''), "
							+ " bodegaEmpresa.nombre "
							+ " from `"+db+"`.movimiento "
							+ " left join `"+db+"`.guia on guia.id = movimiento.id_guia "
							+ " left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa "
							+ " left join `"+db+"`.tipoMovimiento on tipoMovimiento.id = id_tipoMovimiento "
							+ " where bodegaEmpresa.id_proyecto =  ? and numero is not null and bodegaEmpresa.esInterna=2 "
							+ " and movimiento.esVenta= ? and  "
							+ " (guia.fecha between ? and ?) " + permisoPorBodega + condSucursal
							+ " group by guia.fecha,guia.numero "
							+ " order by guia.fecha,guia.numero;");
			smt1.setLong(1, id_proyecto);
			smt1.setString(2, esVenta);
			smt1.setString(3, fechaDesde);
			smt1.setString(4, fechaHasta);
			ResultSet rs1 = smt1.executeQuery();
			
			List<String> bodega = new ArrayList<String>();
			List<String> numGuia = new ArrayList<String>();
			List<String> fechGuia = new ArrayList<String>();
			List<String> guiaClie = new ArrayList<String>();
			List<String> tipGuia = new ArrayList<String>();
			List<String> blanco = new ArrayList<String>();
				
			bodega.add(" ");
			numGuia.add(" ");
			fechGuia.add(" ");
			guiaClie.add(" ");
			tipGuia.add(" ");
			blanco.add("Grupo");
			
			bodega.add(" ");
			numGuia.add(" ");
			fechGuia.add(" ");
			guiaClie.add(" ");
			tipGuia.add(" ");
			blanco.add("Código");
			
			bodega.add(" ");
			numGuia.add(" ");
			fechGuia.add(" ");
			guiaClie.add(" ");
			tipGuia.add(" ");
			blanco.add("Equipo");
			
			bodega.add(" ");
			numGuia.add(" ");
			fechGuia.add(" ");
			guiaClie.add(" ");
			tipGuia.add(" ");
			blanco.add("");
			
			bodega.add(mapDiccionario.get("BODEGA"));
			numGuia.add("Nro.Mov:");
			fechGuia.add("Fecha:");
			guiaClie.add("Nro.Ref");
			tipGuia.add("Tipo.Mov: ");
			blanco.add("");
				
			Fechas desde = Fechas.obtenerFechaDesdeStrAAMMDD(fechaDesde);
			Fechas hasta = Fechas.obtenerFechaDesdeStrAAMMDD(fechaHasta);
			Fechas hastaAux = Fechas.obtenerFechaDesdeStrAAMMDD(fechaHasta);
			Calendar hastaMas1 = hastaAux.getFechaCal();
			hastaMas1.add(Calendar.DAY_OF_MONTH, 1);
				
			bodega.add(" ");
			numGuia.add("STOCK INICIAL");
				String aux2[] = fechaDesde.split("-");
			fechGuia.add(""+aux2[2]+"/"+aux2[1]+"/"+aux2[0]);
			guiaClie.add("");
			tipGuia.add(" ");
			blanco.add(" ");
			
			while (rs1.next()) {
				Fechas fechaGuia = Fechas.obtenerFechaDesdeStrAAMMDD(rs1.getString(4));
				if(!(fechaGuia.fechaCal.before(desde.fechaCal)||fechaGuia.fechaCal.after(hasta.fechaCal))) {
					bodega.add(rs1.getString(6));
					numGuia.add(rs1.getString(1));
					fechGuia.add(rs1.getString(2));
					guiaClie.add(rs1.getString(5));
					tipGuia.add(rs1.getString(3));
					blanco.add(" ");
				}
			}
			rs1.close();
			smt1.close();
			
			bodega.add(" ");
			numGuia.add("STOCK FINAL");
				String aux3[] = fechaHasta.split("-");
			fechGuia.add(""+aux3[2]+"/"+aux3[1]+"/"+aux3[0]);
			guiaClie.add(" ");
			tipGuia.add(" ");
			blanco.add("");
			
			
				
			PreparedStatement smt2 = con
					.prepareStatement(" select  guia.numero, guia.fecha, guia.id, movimiento.id_bodegaEmpresa " +
							" from `"+db+"`.guia " +
							" left join `"+db+"`.movimiento on movimiento.id_guia = guia.id " +
							" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa " +
							" where movimiento.esVenta= ?  and id_proyecto = ?  and bodegaEmpresa.esInterna=2 "+ permisoPorBodega + condSucursal +
							" group by guia.numero " +
							" order by guia.fecha,guia.numero; ");
			smt2.setString(1, esVenta);
			smt2.setLong(2, id_proyecto);
			
			ResultSet rs2 = smt2.executeQuery();
			List<List<String>> listaGuias = new ArrayList<List<String>>();
			while (rs2.next()) {
				List<String> aux = new ArrayList<String>();
				aux.add(rs2.getString(1));
				aux.add(rs2.getString(2));
				aux.add(rs2.getString(3));
				aux.add(rs2.getString(4));
				listaGuias.add(aux);
			}
			rs2.close();
			smt2.close();
				
			PreparedStatement smt3 = con
					.prepareStatement(" select distinct " +
							" grupo.nombre, " +
							" equipo.codigo, " +
							" equipo.nombre, " +
							" equipo.id " +
							" from `"+db+"`.movimiento " +
							" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa " +
							" left join `"+db+"`.equipo on equipo.id = movimiento.id_equipo " +
							" left join `"+db+"`.grupo on grupo.id = equipo.id_grupo " +
							" where " + 
							" bodegaEmpresa.id_proyecto=? and bodegaEmpresa.esInterna=2 " + permisoPorBodega + condSucursal+ 
							" and equipo.nombre is not null and movimiento.esVenta= ? "+
							" order by grupo.nombre,equipo.nombre;");
			
			smt3.setLong(1, id_proyecto);
			smt3.setString(2, esVenta);
			
			ResultSet rs3 = smt3.executeQuery();
			List<List<String>> listaCodigos = new ArrayList<List<String>>();
			
			while (rs3.next()) {
				List<String> aux = new ArrayList<String>();
				aux.add(rs3.getString(1)); //grupo
				aux.add(rs3.getString(2));  //codigo
				aux.add(rs3.getString(3));  //equipo
				aux.add(rs3.getString(4)); //idEquipo
				listaCodigos.add(aux);
			}
			rs3.close();
			smt3.close();
				
			PreparedStatement smt4 = con
					.prepareStatement(" select " +
							" movimiento.id_equipo, " +
							" movimiento.id_guia, " +
							" movimiento.id_bodegaEmpresa, " +
							" sum(if(movimiento.id_tipoMovimiento=2,movimiento.cantidad*-1,movimiento.cantidad)) " +
							" from `"+db+"`.movimiento " +
							" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa " +
							" where bodegaEmpresa.id_proyecto=? and bodegaEmpresa.esInterna=2 " + permisoPorBodega + condSucursal+ 
							" and movimiento.esVenta= ?  " +
							" group by movimiento.id_equipo, movimiento.id_guia, movimiento.id_bodegaEmpresa;");
			smt4.setLong(1, id_proyecto);
			smt4.setString(2, esVenta);
			
			ResultSet rs4 = smt4.executeQuery();
			Map<String,Double> codGuiaCant = new HashMap<String,Double>();
			Map<String,String> codGuiaBodCant = new HashMap<String,String>();
			while (rs4.next()) {
				Double aux = codGuiaCant.get(rs4.getString(1).trim());
				if(aux==null) {
					codGuiaCant.put(rs4.getString(1).trim()+"_"+rs4.getString(2).trim(), rs4.getDouble(4));
				}else{
					codGuiaCant.put(rs4.getString(1).trim()+"_"+rs4.getString(2).trim(), rs4.getDouble(4) + aux);
				}
				codGuiaBodCant.put(rs4.getString(1).trim()+"_"+rs4.getString(2).trim()+"_"+rs4.getString(3).trim(), rs4.getString(4));
			}
			rs4.close();
			smt4.close();
			
			lista.add(bodega);
			lista.add(numGuia);
			lista.add(fechGuia);
			lista.add(guiaClie);
			lista.add(tipGuia);
			lista.add(blanco);
				
				
			
			for(int i=0;i<listaCodigos.size();i++){
					List<String> aux = new ArrayList<String>();
					aux.add(listaCodigos.get(i).get(0)); //grupo
					aux.add(listaCodigos.get(i).get(1)); //codigo
					aux.add(listaCodigos.get(i).get(2)); //equipo
					
					aux.add(""); //kg
					aux.add(""); //m2
					
					Double cantStockIni = (double)0;
					Double cantStockFin = (double)0;
					for(int k=0;k<listaGuias.size();k++){
						Fechas fechaGuia = Fechas.obtenerFechaDesdeStrAAMMDD(listaGuias.get(k).get(1));
						if(fechaGuia.fechaCal.before(desde.fechaCal)){
							String keyAux = listaCodigos.get(i).get(3).trim()+"_"+listaGuias.get(k).get(2).trim();
							Double cantidad = codGuiaCant.get(keyAux);
							if(cantidad != null){
								cantStockIni += cantidad;
							}
						}
						if(fechaGuia.fechaCal.before(hastaMas1)){
							String keyAux = listaCodigos.get(i).get(3).trim()+"_"+listaGuias.get(k).get(2).trim();
							Double cantidad = codGuiaCant.get(keyAux);
							if(cantidad != null){
								cantStockFin += cantidad;
							}
						}
					}
					
					aux.add(myformatdouble2.format(cantStockIni));
					
						
					for(int k=0;k<listaGuias.size();k++){
						Fechas fechaGuia = Fechas.obtenerFechaDesdeStrAAMMDD(listaGuias.get(k).get(1));
						if(!(fechaGuia.fechaCal.before(desde.fechaCal)||fechaGuia.fechaCal.after(hasta.fechaCal))) {
							String keyAux=listaCodigos.get(i).get(3).trim()+"_"+listaGuias.get(k).get(2).trim()+"_"+listaGuias.get(k).get(3).trim();
							String cantidad = codGuiaBodCant.get(keyAux);
							
							if(cantidad==null){
								aux.add(" ");
							}else{
								aux.add(cantidad);
							}
						}
					}
						
					
					aux.add(myformatdouble2.format(cantStockFin));
					lista.add(aux);
					
			}
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	
	

			
}
	
	
	
