package models.reports;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.*;

import controllers.HomeController;
import models.tables.*;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.TempFile;

import models.utilities.Archivos;
import models.utilities.Fechas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ReportEjecutivos {

	static DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00",symbols);
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	public static List<String> graficoDistribucionResumenValorizado(Map<String, String> mapeoDiccionario, List<List<String>> datos, String filtroGrupos,
			Map<Long,TipoBodega> mapTipoBodega) {
		
		Map<String,Double> mapNombGruposVsCompra = new HashMap<String,Double>();
		Map<String,Double> mapTipBodegaNombGruposVsCompra = new HashMap<String,Double>();
		
		Map<String,String> mapFitroGrupo = new HashMap<String,String>();
		if(!filtroGrupos.equals("0") && filtroGrupos.length()>1) {
			filtroGrupos = filtroGrupos.substring(1,filtroGrupos.length()-1);
			String[] arrFiltroGrupo = filtroGrupos.split(",");
			for(int i=0; i<arrFiltroGrupo.length; i++) {
				mapFitroGrupo.put(arrFiltroGrupo[i].trim(),arrFiltroGrupo[i].trim());
			}
		}
		
		
		for(List<String> l: datos) {
			boolean flag = false;
			if(filtroGrupos.equals("0")) {
				flag = true;
			} else {
				String aux = mapFitroGrupo.get(l.get(19));
				if(aux != null) {
					flag = true;
				}
			}
			
			if(flag) {
				Double acumCompra = mapNombGruposVsCompra.get(l.get(1));
				if(acumCompra==null) {
					acumCompra = (double)0;
				}
				Double parcialCompra = Double.parseDouble(l.get(14).replaceAll(",", ""));
				mapNombGruposVsCompra.put(l.get(1), acumCompra+parcialCompra);
				
				Double acumCompraTipo = mapTipBodegaNombGruposVsCompra.get(l.get(18)+"&_&"+l.get(1));
				if(acumCompraTipo==null) {
					acumCompraTipo = (double)0;
				}
				mapTipBodegaNombGruposVsCompra.put(l.get(18)+"&_&"+l.get(1), acumCompraTipo + parcialCompra);
			}
			
		}
		
		List<String> nomGrupos = new ArrayList<String>();
		for (Map.Entry<String, Double> grupo : mapNombGruposVsCompra.entrySet()) {
			Double total = mapNombGruposVsCompra.get(grupo.getKey());
			if((double)total > (double)0) {
				nomGrupos.add(grupo.getKey());
			}
		}
		Collections.sort(nomGrupos);

		String categorias2 = "[";
		String series2 = "[";

		TipoBodega tipoBod = mapTipoBodega.get((long)1);
		series2 += "{name: '" + tipoBod.nombre + "', data: [";
		for (String grupo : nomGrupos) {
			categorias2 += "'" +  grupo + "',";
			Double total = mapNombGruposVsCompra.get(grupo);
			Double valor = mapTipBodegaNombGruposVsCompra.get("1"+"&_&"+grupo);
			if((double)total > (double)0) {
				if(valor==null) {
					series2 += "0,";
				}else {
					DecimalFormat dec = new DecimalFormat("0",symbols);
					if((double)total != (double)0) {
						series2 += "" +  dec.format(valor/total*100) + ",";
					}else {
						series2 += "" +  "0" + ",";
					}
				}
			}
        }
		if(categorias2.length()>2) {
			categorias2 = categorias2.substring(0,categorias2.length()-1)+"]";
		}
		
		if(series2.length()>2) {
			series2 = series2.substring(0,series2.length()-1)+"]},";
		}
		
		
		tipoBod = mapTipoBodega.get((long)2);
		series2 += "{name: '" + tipoBod.nombre + "', data: [";
		for (String grupo : nomGrupos) {
			Double total = mapNombGruposVsCompra.get(grupo);
			Double valor = mapTipBodegaNombGruposVsCompra.get("2"+"&_&"+grupo);
			if((double)total > (double)0) {
				if(valor==null) {
					series2 += "0,";
				}else {
					DecimalFormat dec = new DecimalFormat("0",symbols);
					if((double)total != (double)0) {
						series2 += "" +  dec.format(valor/total*100) + ",";
					}else {
						series2 += "" +  "0" + ",";
					}
				}
			}
        }
		
		if(series2.length()>2) {
			series2 = series2.substring(0,series2.length()-1)+"]}]";
		}
		

		String inversion2 = "[";
		for (Map.Entry<String, Double> entry : mapNombGruposVsCompra.entrySet()) {
			DecimalFormat dec = new DecimalFormat("0",symbols);
			inversion2 += "['"+entry.getKey()+"',"+dec.format(entry.getValue()/1000)+"],";
        }
		
		if(inversion2.length()>2) {
			inversion2 = inversion2.substring(0,inversion2.length()-1)+"]";
		}
		
		
		List<String> graficos2 = new ArrayList<String>();
		graficos2.add(categorias2);
		graficos2.add(series2);
		graficos2.add(inversion2);
		
		return (graficos2);
	}
	
	public static List<String> graficoDistribucionResumenPorUnidades(Map<String, String> mapeoDiccionario, List<List<String>> datos, String filtroGrupos,
			Map<Long,TipoBodega> mapTipoBodega) {
		
		Map<String,Double> mapNombGruposVsCantidad = new HashMap<String,Double>();
		Map<String,Double> mapTipBodegaNombGruposVsCantidad = new HashMap<String,Double>();
		
		Map<String,String> mapFitroGrupo = new HashMap<String,String>();
		if(!filtroGrupos.equals("0") && filtroGrupos.length()>1) {
			filtroGrupos = filtroGrupos.substring(1,filtroGrupos.length()-1);
			String[] arrFiltroGrupo = filtroGrupos.split(",");
			for(int i=0; i<arrFiltroGrupo.length; i++) {
				mapFitroGrupo.put(arrFiltroGrupo[i].trim(),arrFiltroGrupo[i].trim());
			}
		}
		
		for(List<String> l: datos) {
			
			boolean flag = false;
			if(filtroGrupos.equals("0")) {
				flag = true;
			} else {
				String aux = mapFitroGrupo.get(l.get(19));
				if(aux != null) {
					flag = true;
				}
			}
			
			if(flag) {
				Double acumCantidad = mapNombGruposVsCantidad.get(l.get(1));
				if(acumCantidad==null) {
					acumCantidad = (double)0;
				}
				Double parcialCantidad = Double.parseDouble(l.get(11).replaceAll(",", ""));
				mapNombGruposVsCantidad.put(l.get(1), acumCantidad + parcialCantidad);
				
				Double acumCantidadTipo = mapTipBodegaNombGruposVsCantidad.get(l.get(18)+"&_&"+l.get(1));
				if(acumCantidadTipo==null) {
					acumCantidadTipo = (double)0;
				}
				mapTipBodegaNombGruposVsCantidad.put(l.get(18)+"&_&"+l.get(1), acumCantidadTipo + parcialCantidad);
			}
			
			
		}
		
		List<String> nomGrupos = new ArrayList<String>();
		for (Map.Entry<String, Double> grupo : mapNombGruposVsCantidad.entrySet()) {
			Double total = mapNombGruposVsCantidad.get(grupo.getKey());
			if((double)total > (double)0) {
				nomGrupos.add(grupo.getKey());
			}
		}
		Collections.sort(nomGrupos);

		String categorias2 = "[";
		String series2 = "[";

		TipoBodega tipoBod = mapTipoBodega.get((long)1);
		series2 += "{name: '" + tipoBod.nombre + "', data: [";
		for (String grupo : nomGrupos) {
			categorias2 += "'" +  grupo + "',";
			Double total = mapNombGruposVsCantidad.get(grupo);
			Double valor = mapTipBodegaNombGruposVsCantidad.get("1"+"&_&"+grupo);
			if((double)total > (double)0) {
				if(valor==null) {
					series2 += "0,";
				}else {
					DecimalFormat dec = new DecimalFormat("0",symbols);
					if((double)total != (double)0) {
						series2 += "" +  dec.format(valor/total*100) + ",";
					}else {
						series2 += "" +  "0" + ",";
					}
				}
			}
        }

		if(categorias2.length()>2) {
			categorias2 = categorias2.substring(0,categorias2.length()-1)+"]";
		}
		
		if(series2.length()>2) {
			series2 = series2.substring(0,series2.length()-1)+"]},";
		}
		
		
		tipoBod = mapTipoBodega.get((long)2);
		series2 += "{name: '" + tipoBod.nombre + "', data: [";
		for (String grupo : nomGrupos) {
			Double total = mapNombGruposVsCantidad.get(grupo);
			Double valor = mapTipBodegaNombGruposVsCantidad.get("2"+"&_&"+grupo);
			if((double)total > (double)0) {
				if(valor==null) {
					series2 += "0,";
				}else {
					DecimalFormat dec = new DecimalFormat("0",symbols);
					if((double)total != (double)0) {
						series2 += "" +  dec.format(valor/total*100) + ",";
					}else {
						series2 += "" +  "0" + ",";
					}
				}
			}
        }
		
		if(series2.length()>2) {
			series2 = series2.substring(0,series2.length()-1)+"]}]";
		}
		
		
		List<String> graficos2 = new ArrayList<String>();
		graficos2.add(categorias2);
		graficos2.add(series2);
		
		return (graficos2);
	}
	
	public static List<String> graficoOcupacionPorGrupoValorizado(Connection con, String db, String pais, String esPorSucursal, String id_sucursal,
			Map<Long,TipoBodega> mapTipoBodega, Map<Long,Equipo> mapEquipo) {
		List<java.sql.Date> listFechas = new ArrayList<java.sql.Date>();
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and bodegaEmpresa.id_sucursal = " + id_sucursal;
		}
		Calendar fechaCal = Calendar.getInstance();
		fechaCal.set(fechaCal.get(Calendar.YEAR),fechaCal.get(Calendar.MONTH),fechaCal.getActualMinimum(Calendar.DAY_OF_MONTH));
		fechaCal.add(Calendar.DAY_OF_YEAR, -1);
		fechaCal.add(Calendar.MONTH, -11);
		String categoriaFechas ="[";
		SimpleDateFormat myformatfecha = new SimpleDateFormat("MMM-yy");
		for (int i=1;i<=12;i++){
			String aux=",";	if(i==12) aux="";
			java.util.Date date = fechaCal.getTime();
			categoriaFechas=categoriaFechas+"'"+myformatfecha.format(date)+"'"+aux;
			listFechas.add(new java.sql.Date(fechaCal.getTimeInMillis()));
			fechaCal.add(Calendar.MONTH, 1);
			fechaCal.set(fechaCal.get(Calendar.YEAR),fechaCal.get(Calendar.MONTH),fechaCal.getActualMaximum(Calendar.DAY_OF_MONTH));
		}
		categoriaFechas=categoriaFechas+"]";
		List<String> resultado = new ArrayList<String>();
		resultado.add(categoriaFechas);
		List<List<List<String>>> aux = new ArrayList<List<List<String>>>();
		List<String> tipoBodegas = new ArrayList<String>();
		List<String> grupos = new ArrayList<String>();
		List<List<String>> datos = new ArrayList<List<String>>();
		String query = String.format(" SELECT  grupo.nombre  FROM `%s`.grupo ORDER BY grupo.nombre;",db);
		try (PreparedStatement smt8 = con.prepareStatement(query);
			 	ResultSet rs8 = smt8.executeQuery()) {
			TipoBodega tipoBod = mapTipoBodega.get((long)2);
			tipoBodegas.add(tipoBod.getNombre());
			while (rs8.next()) {
				grupos.add(rs8.getString(1));  //nombre de grupo
			}
		} catch (SQLException e) {
			String className = ReportEjecutivos.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		query = String.format("select "
				+ " ifnull(ifnull(guia.fecha,movimiento.fecha_factura),movimiento.fecha_actaBaja),"
				+ " bodegaEmpresa.esInterna, "
				+ " movimiento.id_equipo, "
				+ " if(movimiento.id_tipoMovimiento=2,-1,1)*movimiento.cantidad"
				+ " from `%s`.movimiento"
				+ " left join `%s`.guia on guia.id = movimiento.id_guia  "
				+ " left join `%s`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa  "
				+ " where ifnull(ifnull(guia.fecha,movimiento.fecha_factura),movimiento.fecha_actaBaja) is not null" + condSucursal
				+ ";",db,db,db);
		try (PreparedStatement smt9 = con.prepareStatement(query);
			 ResultSet rs9 = smt9.executeQuery()) {
			while (rs9.next()) {
				String id_tipoBodega = "0";
				String nomTipoBodega = "";
				TipoBodega tipoBodega = mapTipoBodega.get(rs9.getLong(2));
				if(tipoBodega != null) {
					id_tipoBodega = tipoBodega.getId().toString();
					nomTipoBodega = tipoBodega.getNombre();
				}
				String nomGrupo = "";
				Equipo equipo = mapEquipo.get(rs9.getLong(3));
				if(equipo != null) {
					nomGrupo = equipo.getGrupo();
				}
				List<String> aux1 = new ArrayList<String>();
				aux1.add(rs9.getString(1));
				aux1.add(id_tipoBodega+"_"+rs9.getString(3));
				aux1.add(nomTipoBodega);
				aux1.add(nomGrupo);
				aux1.add(rs9.getString(3));
				aux1.add(rs9.getString(4));
				datos.add(aux1);
			}
		} catch (SQLException e) {
			String className = ReportEjecutivos.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		Map<Long,List<Double>> mapPCompra = Compra.ultimoPrecio(con, db);
		Map<String,TasasCambio> mapAllTasas =  TasasCambio.mapTasasporAllFecha(con, db, pais);
		Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
		for(int i=0; i<listFechas.size(); i++){
			aux.add(ReportEjecutivos.ocupacionValorizadoFechaYGrupo(listFechas.get(i).toString(), pais, tipoBodegas, grupos, mapPCompra, mapAllTasas, datos, dec));
		}
		for(int i=0; i<aux.get(0).size(); i++){
			String aux4 = ",";
			if(i == aux.get(0).size()-1) {
				aux4="";
			}
			String aux3 = "{"+
	            " name: ";
			String aux1 = "[";
			String aux2 = "";
			for(int j=0; j<aux.size(); j++){
				String x = ",";
				if(j == aux.size()-1) {
					x = "";
				}
				if(aux.get(j).size()<=aux.get(0).size()) {
					aux1 = aux1 + aux.get(j).get(i).get(1) + x;
					aux2 = aux.get(j).get(i).get(0);
				}
			}
			aux1 = aux1+ "]";
			aux3 = aux3 +"'"+ aux2 +"',"+
	            " type: 'spline',"+
	            " yAxis: 0,"+
	            " data: "+ aux1 +","+
	            " tooltip: {"+
	                "valueSuffix: ' '"+
	            "}"+
	        "}" + aux4;
			resultado.add(aux3);
		}
		return (resultado);
	}

	public static List<List<String>> ocupacionValorizadoFechaYGrupo(String fechaAAMMDD, String pais, List<String> tipoBodegas, List<String> grupos,
																	Map<Long,List<Double>> mapPCompra, Map<String,TasasCambio> mapAllTasas, List<List<String>> datos, Map<Long,Long> dec) {
		Map<String,Double> mapListaGrupoTipBod = new HashMap<String,Double>();
		Map<String,Double> mapListaGrupo = new HashMap<String,Double>();
		TasasCambio tasa = mapAllTasas.get(fechaAAMMDD);
		Map<Long,Double> tasasCorte = TasasCambio.auxiliarMapTasasPorFecha(tasa, pais);
		Map<String, List<String>> mapAux = new HashMap<String,List<String>>();
		Fechas auxFecha = Fechas.obtenerFechaDesdeStrAAMMDD(fechaAAMMDD);
		datos.forEach(x->{
			Fechas auxFecha2 = Fechas.obtenerFechaDesdeStrAAMMDD(x.get(0));
			if(auxFecha2.getFechaCal().before(auxFecha.getFechaCal()) || auxFecha2.getFechaCal().equals(auxFecha.getFechaCal())) {
				List<String> aux = mapAux.get(x.get(1));
				List<String> aux1 = new ArrayList<String>();
				if(aux==null) {
					aux1.add(x.get(2));
					aux1.add(x.get(3));
					aux1.add(x.get(4));
					aux1.add(x.get(5));
					mapAux.put(x.get(1), aux1);
				}else {
					Double cant1 = Double.parseDouble(x.get(5));
					Double cant2 = Double.parseDouble(aux.get(3));
					Double cant3 = cant1+cant2;
					aux1.add(x.get(2));					// 0 tipoBodega.nombre
					aux1.add(x.get(3));					// 1 grupo.nombre
					aux1.add(x.get(4));					// 2 movimiento.id_equipo
					aux1.add(cant3.toString());			// 3 sum(if(movimiento.id_tipoMovimiento=2,-1,1)*movimiento.cantidad)
					mapAux.put(x.get(1), aux1);
				}
			}
		});
		List<List<String>> dePaso = new ArrayList<List<String>>();
		mapAux.forEach((k,v)->{
			dePaso.add(v);
		});
		dePaso.forEach(x->{
			List<Double> auxMap = mapPCompra.get(Long.parseLong(x.get(2)));
			Long idMonedaCompra = (long)1;
			Double ultimaCompra = (double)0;
			if(auxMap!=null) {
				idMonedaCompra=auxMap.get(1).longValue();
				ultimaCompra = auxMap.get(0);
			}
			Double tasaCompra = tasasCorte.get(idMonedaCompra);
			if(tasaCompra==null) tasaCompra=(double)0;
			switch(dec.get(idMonedaCompra).toString()) {
				case "0": myformatdouble = new DecimalFormat("#,##0",symbols); break;
				case "2": myformatdouble = new DecimalFormat("#,##0.00",symbols); break;
				case "4": myformatdouble = new DecimalFormat("#,##0.0000",symbols); break;
				case "6": myformatdouble = new DecimalFormat("#,##0.000000",symbols); break;
				default:  break;
			}
			Double total = ultimaCompra * tasaCompra * Double.parseDouble(x.get(3));
			Double acum1 = mapListaGrupo.get(x.get(1));
			if(acum1==null) {
				acum1 = (double)0;
			}
			Double acum2 = mapListaGrupoTipBod.get(x.get(1)+"_"+x.get(0));
			if(acum2==null) {
				acum2 = (double)0;
			}
			mapListaGrupo.put(x.get(1), total+acum1);
			mapListaGrupoTipBod.put(x.get(1)+"_"+x.get(0), total+acum2);
		});
		Map<String,Double> suma = new HashMap<String,Double>();
		for(int j=0;j<grupos.size();j++){
			Double total = (double) 0;
			Double auxLista = mapListaGrupo.get(grupos.get(j));
			if(auxLista!=null) {
				total = total + auxLista;
			}
			suma.put(grupos.get(j), total);
		}
		List<List<String>> resultado=new ArrayList<List<String>>();
		for(int j=0;j<tipoBodegas.size();j++){
			for(int i=0;i<grupos.size();i++){
				Double suma1 = suma.get(grupos.get(i));
				Double total = (double) 0;
				Double auxLista = mapListaGrupoTipBod.get(grupos.get(i)+"_"+tipoBodegas.get(j));
				if(auxLista!=null) {
					total = total + auxLista;
				}
				if(suma1>0) {
					total = total/suma1;
				}
				DecimalFormat dec0 = new DecimalFormat("0",symbols);
				List<String> aux= new ArrayList<String>();
				aux.add(grupos.get(i));
				aux.add(dec0.format(total*100));
				resultado.add(aux);
			}
		}
		return (resultado);
	}

	public static List<String> graficoOcupacionPorPropiedadValorizado(Connection con, String db, String pais, String esPorSucursal, String id_sucursal,
																  Map<Long,TipoBodega> mapTipoBodega, Map<Long,Equipo> mapEquipo) {
		List<java.sql.Date> listFechas = new ArrayList<java.sql.Date>();
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and bodegaEmpresa.id_sucursal = " + id_sucursal;
		}
		Calendar fechaCal = Calendar.getInstance();
		fechaCal.set(fechaCal.get(Calendar.YEAR),fechaCal.get(Calendar.MONTH),fechaCal.getActualMinimum(Calendar.DAY_OF_MONTH));
		fechaCal.add(Calendar.DAY_OF_YEAR, -1);
		fechaCal.add(Calendar.MONTH, -11);
		String categoriaFechas ="[";
		SimpleDateFormat myformatfecha = new SimpleDateFormat("MMM-yy");
		for (int i=1;i<=12;i++){
			String aux=",";	if(i==12) aux="";
			java.util.Date date = fechaCal.getTime();
			categoriaFechas=categoriaFechas+"'"+myformatfecha.format(date)+"'"+aux;
			listFechas.add(new java.sql.Date(fechaCal.getTimeInMillis()));
			fechaCal.add(Calendar.MONTH, 1);
			fechaCal.set(fechaCal.get(Calendar.YEAR),fechaCal.get(Calendar.MONTH),fechaCal.getActualMaximum(Calendar.DAY_OF_MONTH));
		}
		categoriaFechas=categoriaFechas+"]";
		List<String> resultado = new ArrayList<String>();
		resultado.add(categoriaFechas);
		List<List<List<String>>> aux = new ArrayList<List<List<String>>>();
		List<String> tipoBodegas = new ArrayList<String>();
		List<String> propiedades = new ArrayList<String>();
		List<List<String>> datos = new ArrayList<List<String>>();
		String query = String.format(" SELECT  propiedad.nombre  FROM `%s`.propiedad ORDER BY propiedad.nombre;",db);
		try (PreparedStatement smt8 = con.prepareStatement(query);
			 ResultSet rs8 = smt8.executeQuery()) {
			TipoBodega tipoBod = mapTipoBodega.get((long)2);
			tipoBodegas.add(tipoBod.getNombre());
			while (rs8.next()) {
				propiedades.add(rs8.getString(1));  //nombre de propiedad
			}
		} catch (SQLException e) {
			String className = ReportEjecutivos.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		query = String.format("select "
				+ " ifnull(ifnull(guia.fecha,movimiento.fecha_factura),movimiento.fecha_actaBaja),"
				+ " bodegaEmpresa.esInterna, "
				+ " movimiento.id_equipo, "
				+ " if(movimiento.id_tipoMovimiento=2,-1,1)*movimiento.cantidad"
				+ " from `%s`.movimiento"
				+ " left join `%s`.guia on guia.id = movimiento.id_guia  "
				+ " left join `%s`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa  "
				+ " where ifnull(ifnull(guia.fecha,movimiento.fecha_factura),movimiento.fecha_actaBaja) is not null" + condSucursal
				+ ";",db,db,db);
		try (PreparedStatement smt9 = con.prepareStatement(query);
			 ResultSet rs9 = smt9.executeQuery()) {
			while (rs9.next()) {
				String id_tipoBodega = "0";
				String nomTipoBodega = "";
				TipoBodega tipoBodega = mapTipoBodega.get(rs9.getLong(2));
				if(tipoBodega != null) {
					id_tipoBodega = tipoBodega.getId().toString();
					nomTipoBodega = tipoBodega.getNombre();
				}
				String nomPropiedad = "";
				Equipo equipo = mapEquipo.get(rs9.getLong(3));
				if(equipo != null) {
					nomPropiedad = equipo.getPropiedad();
				}
				List<String> aux1 = new ArrayList<String>();
				aux1.add(rs9.getString(1));
				aux1.add(id_tipoBodega+"_"+rs9.getString(3));
				aux1.add(nomTipoBodega);
				aux1.add(nomPropiedad);
				aux1.add(rs9.getString(3));
				aux1.add(rs9.getString(4));
				datos.add(aux1);
			}
		} catch (SQLException e) {
			String className = ReportEjecutivos.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		Map<Long,List<Double>> mapPCompra = Compra.ultimoPrecio(con, db);
		Map<String,TasasCambio> mapAllTasas =  TasasCambio.mapTasasporAllFecha(con, db, pais);
		Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
		for(int i=0; i<listFechas.size(); i++){
			aux.add(ReportEjecutivos.ocupacionValorizadoFechaYPropiedad(listFechas.get(i).toString(), pais, tipoBodegas, propiedades, mapPCompra, mapAllTasas, datos, dec));
		}
		for(int i=0; i<aux.get(0).size(); i++){
			String aux4 = ",";
			if(i == aux.get(0).size()-1) {
				aux4="";
			}
			String aux3 = "{"+
					" name: ";
			String aux1 = "[";
			String aux2 = "";
			for(int j=0; j<aux.size(); j++){
				String x = ",";
				if(j == aux.size()-1) {
					x = "";
				}
				if(aux.get(j).size()<=aux.get(0).size()) {
					aux1 = aux1 + aux.get(j).get(i).get(1) + x;
					aux2 = aux.get(j).get(i).get(0);
				}
			}
			aux1 = aux1+ "]";
			aux3 = aux3 +"'"+ aux2 +"',"+
					" type: 'spline',"+
					" yAxis: 0,"+
					" data: "+ aux1 +","+
					" tooltip: {"+
					"valueSuffix: ' '"+
					"}"+
					"}" + aux4;
			resultado.add(aux3);
		}
		return (resultado);
	}

	public static List<List<String>> ocupacionValorizadoFechaYPropiedad(String fechaAAMMDD, String pais, List<String> tipoBodegas, List<String> propiedades,
																	Map<Long,List<Double>> mapPCompra, Map<String,TasasCambio> mapAllTasas, List<List<String>> datos, Map<Long,Long> dec) {
		Map<String,Double> mapListaPropiedadTipBod = new HashMap<String,Double>();
		Map<String,Double> mapListaPropiedad = new HashMap<String,Double>();
		TasasCambio tasa = mapAllTasas.get(fechaAAMMDD);
		Map<Long,Double> tasasCorte = TasasCambio.auxiliarMapTasasPorFecha(tasa, pais);
		Map<String, List<String>> mapAux = new HashMap<String,List<String>>();
		Fechas auxFecha = Fechas.obtenerFechaDesdeStrAAMMDD(fechaAAMMDD);
		datos.forEach(x->{
			Fechas auxFecha2 = Fechas.obtenerFechaDesdeStrAAMMDD(x.get(0));
			if(auxFecha2.getFechaCal().before(auxFecha.getFechaCal()) || auxFecha2.getFechaCal().equals(auxFecha.getFechaCal())) {
				List<String> aux = mapAux.get(x.get(1));
				List<String> aux1 = new ArrayList<String>();
				if(aux==null) {
					aux1.add(x.get(2));
					aux1.add(x.get(3));
					aux1.add(x.get(4));
					aux1.add(x.get(5));
					mapAux.put(x.get(1), aux1);
				}else {
					Double cant1 = Double.parseDouble(x.get(5));
					Double cant2 = Double.parseDouble(aux.get(3));
					Double cant3 = cant1+cant2;
					aux1.add(x.get(2));					// 0 tipoBodega.nombre
					aux1.add(x.get(3));					// 1 propiedad.nombre
					aux1.add(x.get(4));					// 2 movimiento.id_equipo
					aux1.add(cant3.toString());			// 3 sum(if(movimiento.id_tipoMovimiento=2,-1,1)*movimiento.cantidad)
					mapAux.put(x.get(1), aux1);
				}
			}
		});
		List<List<String>> dePaso = new ArrayList<List<String>>();
		mapAux.forEach((k,v)->{
			dePaso.add(v);
		});
		dePaso.forEach(x->{
			List<Double> auxMap = mapPCompra.get(Long.parseLong(x.get(2)));
			Long idMonedaCompra = (long)1;
			Double ultimaCompra = (double)0;
			if(auxMap!=null) {
				idMonedaCompra=auxMap.get(1).longValue();
				ultimaCompra = auxMap.get(0);
			}
			Double tasaCompra = tasasCorte.get(idMonedaCompra);
			if(tasaCompra==null) tasaCompra=(double)0;
			switch(dec.get(idMonedaCompra).toString()) {
				case "0": myformatdouble = new DecimalFormat("#,##0",symbols); break;
				case "2": myformatdouble = new DecimalFormat("#,##0.00",symbols); break;
				case "4": myformatdouble = new DecimalFormat("#,##0.0000",symbols); break;
				case "6": myformatdouble = new DecimalFormat("#,##0.000000",symbols); break;
				default:  break;
			}
			Double total = ultimaCompra * tasaCompra * Double.parseDouble(x.get(3));
			Double acum1 = mapListaPropiedad.get(x.get(1));
			if(acum1==null) {
				acum1 = (double)0;
			}
			Double acum2 = mapListaPropiedadTipBod.get(x.get(1)+"_"+x.get(0));
			if(acum2==null) {
				acum2 = (double)0;
			}
			mapListaPropiedad.put(x.get(1), total+acum1);
			mapListaPropiedadTipBod.put(x.get(1)+"_"+x.get(0), total+acum2);
		});
		Map<String,Double> suma = new HashMap<String,Double>();
		for(int j=0; j<propiedades.size(); j++){
			Double total = (double) 0;
			Double auxLista = mapListaPropiedad.get(propiedades.get(j));
			if(auxLista!=null) {
				total = total + auxLista;
			}
			suma.put(propiedades.get(j), total);
		}
		List<List<String>> resultado=new ArrayList<List<String>>();
		for(int j=0;j<tipoBodegas.size();j++){
			for(int i=0;i<propiedades.size();i++){
				Double suma1 = suma.get(propiedades.get(i));
				Double total = (double) 0;
				Double auxLista = mapListaPropiedadTipBod.get(propiedades.get(i)+"_"+tipoBodegas.get(j));
				if(auxLista!=null) {
					total = total + auxLista;
				}
				if(suma1>0) {
					total = total/suma1;
				}
				DecimalFormat dec0 = new DecimalFormat("0",symbols);
				List<String> aux= new ArrayList<String>();
				aux.add(propiedades.get(i));
				aux.add(dec0.format(total*100));
				resultado.add(aux);
			}
		}
		return (resultado);
	}

	
	public static List<String> graficoDistribucionResumenValorizadoPorBodega(Connection con, String db, String pais, String esPorSucursal, String id_sucursal,
			Map<Long,List<Double>> mapPCompra, Map<Long,Double> tasasCorte, Map<Long,Long> dec) {
		List<String> bodega = new ArrayList<String>();
		
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " where bodegaEmpresa.id_sucursal = " + id_sucursal;
		}
		
		Map<String,Double> mapLista = new HashMap<String,Double>();
		
		try {
			PreparedStatement smt6 = con
					.prepareStatement(" select " +
							" bodegaEmpresa.nombre, " + 
							" movimiento.id_equipo, " + 
							" sum(if(movimiento.id_tipoMovimiento=2,-1,1)*movimiento.cantidad) " +
							" from `"+db+"`.movimiento " +
							" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa " +
							condSucursal +
							" group by bodegaEmpresa.nombre, movimiento.id_equipo " +
							" order by bodegaEmpresa.esInterna,bodegaEmpresa.nombre;");
			
			ResultSet rs6 = smt6.executeQuery();
		
			while (rs6.next()) {
				
				List<Double> auxMap = mapPCompra.get(rs6.getLong(2));
				
				Long idMonedaCompra = (long)1;
				Double ultimaCompra = (double)0;
				if(auxMap!=null) {
					idMonedaCompra=auxMap.get(1).longValue();
					ultimaCompra = auxMap.get(0);
				}
				
				if(ultimaCompra==null) ultimaCompra=(double)0;
				Double tasaCompra = tasasCorte.get(idMonedaCompra);
				if(tasaCompra==null) tasaCompra=(double)0;
					
				switch(dec.get(idMonedaCompra).toString()) {
				 case "0": myformatdouble = new DecimalFormat("#,##0",symbols); break;
				 case "2": myformatdouble = new DecimalFormat("#,##0.00",symbols); break;
				 case "4": myformatdouble = new DecimalFormat("#,##0.0000",symbols); break;
				 case "6": myformatdouble = new DecimalFormat("#,##0.000000",symbols); break;
				 default:  break;
				}
				
				Double total = rs6.getDouble(3)*ultimaCompra*tasaCompra;
				
				Double acum2 = mapLista.get(rs6.getString(1)+"_"+rs6.getString(1));
				if(acum2==null) {
					acum2 = (double)0;
				}
				
				mapLista.put(rs6.getString(1)+"_"+rs6.getString(1), total+acum2);
			}
			rs6.close();
			smt6.close();
			
			condSucursal = "";
			if(esPorSucursal.equals("1")) {
				condSucursal = " and bodegaEmpresa.id_sucursal = " + id_sucursal;
			}
			
			PreparedStatement smt8 = con
					.prepareStatement(" select " +
							" bodegaEmpresa.nombre " +
							" from `"+db+"`.bodegaEmpresa   " +
							" where esInterna = 2" + condSucursal +
							" order by bodegaEmpresa.nombre;");
			ResultSet rs8 = smt8.executeQuery();
			while (rs8.next()) {
				bodega.add(rs8.getString(1));  //nombre de bodega
			}

			rs8.close();
			smt8.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		
		List<String> graficos = new ArrayList<String>();
		List<String> bodegaSinCeros = new ArrayList<String>();
		

		for(int j=0;j<bodega.size();j++){
			Double total = (double) 0;
			Double auxLista = mapLista.get(bodega.get(j)+"_"+bodega.get(j));
			if(auxLista!=null && auxLista >(double)1000) {
				total = total + auxLista;
				bodegaSinCeros.add(bodega.get(j));
			}
		}
		
		List<String> ceros = new ArrayList<String>();
		
		String categorias = "[";
		for(int i=0; i<bodegaSinCeros.size(); i++){
			String aux1=",";
			if(i == bodegaSinCeros.size()-1) {
				aux1="";
			}
			categorias = categorias + "'" + bodegaSinCeros.get(i) + "'" + aux1;
			ceros.add("0");
		}
		categorias = categorias + "]";
		
		String series = "[";
		for(int j=0; j<bodegaSinCeros.size(); j++){
			
			series = series + "{showInLegend: false, name: '" + bodegaSinCeros.get(j) + "', data: ";
			String aux2=",";
			if(j==bodegaSinCeros.size()-1) {
				aux2="";
			}
			
			Double auxLista = mapLista.get(bodegaSinCeros.get(j)+"_"+bodegaSinCeros.get(j));
			DecimalFormat decFormat = new DecimalFormat("0",symbols);
			
			List<String> auxceros = new ArrayList<>(ceros);
			
			auxceros.set(j, decFormat.format(auxLista/1000));
			
			String dePaso = auxceros.toString();
			
			series = series + dePaso ;
	
			series = series + "}" + aux2;

		}
		series = series + "]";
		
		
		graficos.add(categorias);
		graficos.add(series);
		return (graficos);
	}
	
	public static List<String> graficoPotencialPorMesYGrupo(Connection con, String db, String pais, String esPorSucursal, String id_sucursal,
			Map<Long,Double> tasasCorte, Map<Long,List<Double>> mapPLista, Map<Long, Double> mapEquivalencia, Map<Long,Long> dec) {
		List<String> tipoBodegas = new ArrayList<String>();
		
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " where bodegaEmpresa.id_sucursal = " + id_sucursal;
		}
		
		List<String> grupos = new ArrayList<String>();
		
		
		
		Map<String,Double> mapListaGrupoTipBod = new HashMap<String,Double>();
		Map<String,Double> mapListaGrupo = new HashMap<String,Double>();
		
		
		try {
			PreparedStatement smt6 = con
					.prepareStatement(" select " +
							" ifnull(tipoBodega.nombre,''), " +
							" grupo.nombre, " +
							" movimiento.id_equipo, " +
							" sum(if(movimiento.id_tipoMovimiento=2,-1,1)*movimiento.cantidad) " +
							" from `"+db+"`.movimiento " +
							" left join `"+db+"`.equipo on equipo.id = movimiento.id_equipo " +
							" left join `"+db+"`.grupo on grupo.id = equipo.id_grupo " +
							" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa " +
							" left join `"+db+"`.tipoBodega on tipoBodega.id = bodegaEmpresa.esInterna " +
							condSucursal +
							" group by tipoBodega.nombre,movimiento.id_equipo " +
							" order by bodegaEmpresa.esInterna,bodegaEmpresa.nombre,grupo.nombre,equipo.nombre;");
			ResultSet rs6 = smt6.executeQuery();
			
			while (rs6.next()) {
				
				List<Double> listPLista =  mapPLista.get(rs6.getLong(3));
				Long idMonedaCompra = (long)1;
				Double precioArriendo = (double)1;
				Double unidadArriendo = (double)1;
						
				if(listPLista != null) {
					idMonedaCompra = listPLista.get(3).longValue();
					precioArriendo = listPLista.get(1);
					unidadArriendo = listPLista.get(2);
				}
				
				Double tasaArriendo = tasasCorte.get(idMonedaCompra);
					if(tasaArriendo==null) tasaArriendo=(double)0;
					
				Double factor = mapEquivalencia.get(unidadArriendo.longValue());	
				
				precioArriendo=precioArriendo/factor;
				switch(dec.get(idMonedaCompra).toString()) {
				 case "0": myformatdouble = new DecimalFormat("#,##0",symbols); break;
				 case "2": myformatdouble = new DecimalFormat("#,##0.00",symbols); break;
				 case "4": myformatdouble = new DecimalFormat("#,##0.0000",symbols); break;
				 case "6": myformatdouble = new DecimalFormat("#,##0.000000",symbols); break;
				 default:  break;
				}
				
				Double total = rs6.getDouble(4) * precioArriendo * 30 * tasaArriendo; 
				
				Double acum1 = mapListaGrupo.get(rs6.getString(2));
				if(acum1==null) {
					acum1 = (double)0;
				}
				
				Double acum2 = mapListaGrupoTipBod.get(rs6.getString(2)+"_"+rs6.getString(1));
				if(acum2==null) {
					acum2 = (double)0;
				}
				
				mapListaGrupo.put(rs6.getString(2), total+acum1);
				mapListaGrupoTipBod.put(rs6.getString(2)+"_"+rs6.getString(1), total+acum2);
				
			}
			rs6.close();
			smt6.close();
			
			PreparedStatement smt7 = con
					.prepareStatement(" select tipoBodega.nombre from `"+db+"`.tipoBodega;");
			ResultSet rs7 = smt7.executeQuery();
			while (rs7.next()) {
				tipoBodegas.add(rs7.getString(1));  //nombre de tipobodega
			}
			rs7.close();
			smt7.close();
			
			PreparedStatement smt8 = con
					.prepareStatement(" select " +
							" grupo.nombre " +
							" from `"+db+"`.grupo   " +
							" order by grupo.nombre;");
			ResultSet rs8 = smt8.executeQuery();
			while (rs8.next()) {
				grupos.add(rs8.getString(1));  //nombre de grupo
			}
			rs8.close();
			smt8.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		List<String> graficos = new ArrayList<String>();
		
		String categorias = "[";
		for(int i=0;i<grupos.size();i++){
			String aux1=",";
			if(i==grupos.size()-1) aux1="";
			categorias = categorias + "'" + grupos.get(i) + "'" + aux1;
		}
		categorias = categorias + "]";
		
		String series = "[";
		for(int j=0;j<tipoBodegas.size();j++){
			series = series + "{name: '" + tipoBodegas.get(j) + "', data: [";
			String aux2=",";
			if(j==tipoBodegas.size()-1) aux2="";
			for(int i=0;i<grupos.size();i++){
				Double total = (double) 0;
				String aux3=",";
				if(i==grupos.size()-1) aux3="";
				Double auxLista = mapListaGrupoTipBod.get(grupos.get(i)+"_"+tipoBodegas.get(j));
				if(auxLista!=null) {
					total = total + auxLista;
				}
				DecimalFormat decFormat = new DecimalFormat("0",symbols);
				series = series + decFormat.format(total/1000) + aux3;
			}
			series = series + "]}" + aux2;
		}
		series = series + "]";
		
		graficos.add(categorias);
		graficos.add(series);
		return (graficos);
	}
	
	
	// GRAFICOS REPORTE EJECUTIVO:
	
	public static File graficoInversion(String db, String tabla, Map<String,String> mapDiccionario) {
		
		tabla=tabla.replace("[[", "").replace("]]", "").replace("],[", ";;");
		String[] aux = tabla.split(";;");
		
		File tmp = TempFile.createTempFile("tmp","null");
		
		try {
			String path = "formatos/graficoInversion.xlsx";
			InputStream formato = Archivos.leerArchivo(path);
            Workbook libro = WorkbookFactory.create(formato);
            formato.close();
			
            CellStyle detalle = libro.createCellStyle();
            detalle.setBorderBottom(CellStyle.BORDER_THIN);
            detalle.setBorderTop(CellStyle.BORDER_THIN);
            detalle.setBorderRight(CellStyle.BORDER_THIN);
            detalle.setBorderLeft(CellStyle.BORDER_THIN);
            
            Sheet hoja1 = libro.getSheetAt(0);
            
          //INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
			InputStream x = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
            byte[] bytes = IOUtils.toByteArray(x);
            x.close();
            int pngIndex = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			Drawing draw = hoja1.createDrawingPatriarch();
			CreationHelper helper = libro.getCreationHelper();
			ClientAnchor anchor = helper.createClientAnchor();
	        //set top-left corner for the image
	        anchor.setCol1(1);
	        anchor.setRow1(1);
			Picture img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);

		  //DETALLE DE LA TABLA
            
            
            Row row = null;
            Cell cell = null;
            int fila=7;
            
            row = hoja1.getRow(fila);
            cell = row.getCell(1);
            cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("GRUPO/FAMILIA");
			cell = row.getCell(2);
            cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("MONTOS");
			
			for(int i=0;i<aux.length;i++) {
				String[] dato =aux[i].split(",");
				
				row = hoja1.createRow(fila++);
				
				cell = row.createCell(1);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(dato[0].replaceAll("'", ""));
				
				cell = row.createCell(2);
				cell.setCellStyle(detalle);
	            cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(Long.parseLong(dato[1].trim()));
			}
			
			
			
			int posRow = fila + 5;
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
		
		return(tmp);
		
	}
	
	public static File graficoOcupacion(String db, List<String> tabla, Map<String,String> mapDiccionario) {
		
		File tmp = TempFile.createTempFile("tmp","null");
		
		try {
			String path = "formatos/graficoOcupacion.xlsx";
			InputStream formato = Archivos.leerArchivo(path);
            Workbook libro = WorkbookFactory.create(formato);
            formato.close();
			
            CellStyle detalle = libro.createCellStyle();
            detalle.setBorderBottom(CellStyle.BORDER_THIN);
            detalle.setBorderTop(CellStyle.BORDER_THIN);
            detalle.setBorderRight(CellStyle.BORDER_THIN);
            detalle.setBorderLeft(CellStyle.BORDER_THIN);
            
            Sheet hoja1 = libro.getSheetAt(0);
            
          //INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
			InputStream x = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
            byte[] bytes = IOUtils.toByteArray(x);
            x.close();
            int pngIndex = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			Drawing draw = hoja1.createDrawingPatriarch();
			CreationHelper helper = libro.getCreationHelper();
			ClientAnchor anchor = helper.createClientAnchor();
	        //set top-left corner for the image
	        anchor.setCol1(1);
	        anchor.setRow1(1);
			Picture img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);

		  //DETALLE DE LA TABLA
            
            
            Row row = null;
            Cell cell = null;
            int fila=8;
            
            row = hoja1.getRow(7);
            
            String[] meses = tabla.get(0).split(",");
            
            for(int i=0;i<meses.length;i++) {
				cell = row.getCell(i+2);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String mes = meses[i].replaceAll("'", "").replace("[", "").replace("]", "");
				cell.setCellValue(mes);
			}
            
            for(int i=1; i<tabla.size(); i++) {
				row = hoja1.createRow(fila++);
				String auxSerie = tabla.get(i).replace("{", "").replace("}", "");
				String nomSerie = auxSerie.substring(auxSerie.indexOf("'")+1);
				nomSerie = nomSerie.substring(0,nomSerie.indexOf("'"));
				cell = row.createCell(1);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(nomSerie);
				String datoSerie = auxSerie.substring(auxSerie.indexOf("["));
				datoSerie = datoSerie.substring(1,datoSerie.indexOf("]"));
				String[] serie = datoSerie.split(",");
				for(int j=0;j<serie.length;j++) {
					cell = row.createCell(j+2);
					cell.setCellStyle(detalle);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(Long.parseLong(serie[j].trim()));
				}
			}
			
			// Write the output to a file tmp
			FileOutputStream fileOut = new FileOutputStream(tmp);
			libro.write(fileOut);
			fileOut.close();
			
		} catch (Exception e) {
    			e.printStackTrace();
        }
		
		return(tmp);
		
	}
	
	public static File graficoValorizadoGrupo(String db, List<String> tabla, Map<String,String> mapDiccionario) {
		
		File tmp = TempFile.createTempFile("tmp","null");
		
		try {
			String path = "formatos/graficoValorizadoGrupo.xlsx";
			InputStream formato = Archivos.leerArchivo(path);
            Workbook libro = WorkbookFactory.create(formato);
            formato.close();
			
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
            
          //INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
			InputStream x = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
            byte[] bytes = IOUtils.toByteArray(x);
            x.close();
            int pngIndex = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			Drawing draw = hoja1.createDrawingPatriarch();
			CreationHelper helper = libro.getCreationHelper();
			ClientAnchor anchor = helper.createClientAnchor();
	        //set top-left corner for the image
	        anchor.setCol1(1);
	        anchor.setRow1(1);
			Picture img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);

		  //DETALLE DE LA TABLA
            
            Row row = null;
            Cell cell = null;
            int fila=7;
            row = hoja1.createRow(fila++);
			String[] categorias = tabla.get(0).split(",");
			cell = row.createCell(1);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Categorias");
			for(int i=0;i<categorias.length;i++) {
				cell = row.createCell(i+2);
				cell.setCellStyle(encabezado);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String categoria = categorias[i].replaceAll("'", "").replace("[", "").replace("]", "");
				cell.setCellValue(categoria);
			}
			String auxSerie = tabla.get(1).replace("{", "").replace("}", "");
			auxSerie = auxSerie.substring(5);
			String[] div1 = auxSerie.split("name");
			for(int i=0;i<div1.length;i++) {
				row = hoja1.createRow(fila++);
				String nomSerie = div1[i].substring(div1[i].indexOf("'")+1);
				nomSerie = nomSerie.substring(0,nomSerie.indexOf("'"));
				cell = row.createCell(1);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(nomSerie);
				div1[i]=auxSerie.substring(div1[i].indexOf("data")+5);
				String[] div2 = div1[i].split("data");
					String datoSerie = div2[i].substring(div2[i].indexOf("["));
					datoSerie = datoSerie.substring(1,datoSerie.indexOf("]"));
					String[] serie = datoSerie.split(",");
					for(int k=0;k<serie.length;k++) {
						cell = row.createCell(k+2);
						cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(Long.parseLong(serie[k].trim()));
					}
			}
			
			// Write the output to a file tmp
			FileOutputStream fileOut = new FileOutputStream(tmp);
			libro.write(fileOut);
			fileOut.close();
			
		} catch (Exception e) {
    			e.printStackTrace();
        }
		
		return(tmp);
		
	}
	
	public static File graficoUnidades(String db, List<String> tabla, Map<String,String> mapDiccionario) {
		
		File tmp = TempFile.createTempFile("tmp","null");
		
		try {
			String path = "formatos/graficoUnidades.xlsx";
			InputStream formato = Archivos.leerArchivo(path);
            Workbook libro = WorkbookFactory.create(formato);
            formato.close();
			
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
            
          //INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
			InputStream x = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
            byte[] bytes = IOUtils.toByteArray(x);
            x.close();
            int pngIndex = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			Drawing draw = hoja1.createDrawingPatriarch();
			CreationHelper helper = libro.getCreationHelper();
			ClientAnchor anchor = helper.createClientAnchor();
	        //set top-left corner for the image
	        anchor.setCol1(1);
	        anchor.setRow1(1);
			Picture img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);

		  //DETALLE DE LA TABLA
            
            Row row = null;
            Cell cell = null;
            int fila=7;
            row = hoja1.createRow(fila++);
			String[] categorias = tabla.get(0).split(",");
			cell = row.createCell(1);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Categorias");
			for(int i=0;i<categorias.length;i++) {
				cell = row.createCell(i+2);
				cell.setCellStyle(encabezado);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String categoria = categorias[i].replaceAll("'", "").replace("[", "").replace("]", "");
				cell.setCellValue(categoria);
			}
			String auxSerie = tabla.get(1).replace("{", "").replace("}", "");
			auxSerie = auxSerie.substring(5);
			String[] div1 = auxSerie.split("name");
			for(int i=0;i<div1.length;i++) {
				row = hoja1.createRow(fila++);
				String nomSerie = div1[i].substring(div1[i].indexOf("'")+1);
				nomSerie = nomSerie.substring(0,nomSerie.indexOf("'"));
				cell = row.createCell(1);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(nomSerie);
				div1[i]=auxSerie.substring(div1[i].indexOf("data")+5);
				String[] div2 = div1[i].split("data");
					String datoSerie = div2[i].substring(div2[i].indexOf("["));
					datoSerie = datoSerie.substring(1,datoSerie.indexOf("]"));
					String[] serie = datoSerie.split(",");
					for(int k=0;k<serie.length;k++) {
						cell = row.createCell(k+2);
						cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(Long.parseLong(serie[k].trim()));
					}
			}
			
			// Write the output to a file tmp
			FileOutputStream fileOut = new FileOutputStream(tmp);
			libro.write(fileOut);
			fileOut.close();
			
		} catch (Exception e) {
    			e.printStackTrace();
        }
		
		return(tmp);
		
	}
	
	public static File graficoValorizadoBodega(String db, List<String> tabla, Map<String,String> mapDiccionario) {
		
		File tmp = TempFile.createTempFile("tmp","null");
		
		try {
			String path = "formatos/graficoValorizadoBodega.xlsx";
			InputStream formato = Archivos.leerArchivo(path);
            Workbook libro = WorkbookFactory.create(formato);
            formato.close();
			
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
            
          //INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
			InputStream x = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
            byte[] bytes = IOUtils.toByteArray(x);
            x.close();
            int pngIndex = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			Drawing draw = hoja1.createDrawingPatriarch();
			CreationHelper helper = libro.getCreationHelper();
			ClientAnchor anchor = helper.createClientAnchor();
	        //set top-left corner for the image
	        anchor.setCol1(1);
	        anchor.setRow1(1);
			Picture img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);

		  //DETALLE DE LA TABLA
            
            Row row = null;
            Cell cell = null;
            int fila=7;
            row = hoja1.createRow(fila++);
			String[] categorias = tabla.get(0).split(",");
			cell = row.createCell(1);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Categorias");
			for(int i=0;i<categorias.length;i++) {
				cell = row.createCell(i+2);
				cell.setCellStyle(encabezado);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String categoria = categorias[i].replaceAll("'", "").replace("[", "").replace("]", "");
				cell.setCellValue(categoria);
			}
			String auxSerie = tabla.get(1).replace("{", "").replace("}", "");
			auxSerie = auxSerie.substring(25);
			String[] div1 = auxSerie.split("name");
			for(int i=0;i<div1.length;i++) {
				row = hoja1.createRow(fila++);
				String nomSerie = div1[i].substring(div1[i].indexOf("'")+1);
				nomSerie = nomSerie.substring(0,nomSerie.indexOf("'"));
				cell = row.createCell(1);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(nomSerie);
				div1[i]=auxSerie.substring(div1[i].indexOf("data")+5);
				String[] div2 = div1[i].split("data");
					String datoSerie = div2[i].substring(div2[i].indexOf("["));
					datoSerie = datoSerie.substring(1,datoSerie.indexOf("]"));
					String[] serie = datoSerie.split(",");
					for(int k=0;k<serie.length;k++) {
						cell = row.createCell(k+2);
						cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(Long.parseLong(serie[k].trim()));
					}
			}
			
			// Write the output to a file tmp
			FileOutputStream fileOut = new FileOutputStream(tmp);
			libro.write(fileOut);
			fileOut.close();
			
		} catch (Exception e) {
    			e.printStackTrace();
        }
		
		return(tmp);
		
	}
	
	public static File graficoPotencial(String db, List<String> tabla, Map<String,String> mapDiccionario) {
		
		File tmp = TempFile.createTempFile("tmp","null");
		
		try {
			String path = "formatos/graficoPotencial.xlsx";
			InputStream formato = Archivos.leerArchivo(path);
            Workbook libro = WorkbookFactory.create(formato);
            formato.close();
			
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
            
          //INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
			InputStream x = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
            byte[] bytes = IOUtils.toByteArray(x);
            x.close();
            int pngIndex = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			Drawing draw = hoja1.createDrawingPatriarch();
			CreationHelper helper = libro.getCreationHelper();
			ClientAnchor anchor = helper.createClientAnchor();
	        //set top-left corner for the image
	        anchor.setCol1(1);
	        anchor.setRow1(1);
			Picture img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);

		  //DETALLE DE LA TABLA
            
            Row row = null;
            Cell cell = null;
            int fila=7;
            row = hoja1.createRow(fila++);
			String[] categorias = tabla.get(0).split(",");
			cell = row.createCell(1);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Categorias");
			for(int i=0;i<categorias.length;i++) {
				cell = row.createCell(i+2);
				cell.setCellStyle(encabezado);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String categoria = categorias[i].replaceAll("'", "").replace("[", "").replace("]", "");
				cell.setCellValue(categoria);
			}
			String auxSerie = tabla.get(1).replace("{", "").replace("}", "");
			auxSerie = auxSerie.substring(5);
			String[] div1 = auxSerie.split("name");
			for(int i=0;i<div1.length;i++) {
				row = hoja1.createRow(fila++);
				String nomSerie = div1[i].substring(div1[i].indexOf("'")+1);
				nomSerie = nomSerie.substring(0,nomSerie.indexOf("'"));
				cell = row.createCell(1);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(nomSerie);
				div1[i]=auxSerie.substring(div1[i].indexOf("data")+5);
				String[] div2 = div1[i].split("data");
					String datoSerie = div2[i].substring(div2[i].indexOf("["));
					datoSerie = datoSerie.substring(1,datoSerie.indexOf("]"));
					String[] serie = datoSerie.split(",");
					for(int k=0;k<serie.length;k++) {
						cell = row.createCell(k+2);
						cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(Long.parseLong(serie[k].trim()));
					}
			}
			
			// Write the output to a file tmp
			FileOutputStream fileOut = new FileOutputStream(tmp);
			libro.write(fileOut);
			fileOut.close();
			
		} catch (Exception e) {
    			e.printStackTrace();
        }
		
		return(tmp);
		
	}
	
		
			
}
	
	
	
