package models.reports;


import controllers.HomeController;
import models.tables.ActaBaja;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.*;


public class ReportGraficos {

	static DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	public static List<String> graficoDistribucionGrupoBodegasValorizado(Map<String, String> mapeoDiccionario, String filtroGrupos, String filtroBodegas, 
			String idTipoBodega, String esPorSucursal, String id_sucursal, List<List<String>> datos) {
		
		Map<String,Double> mapNombGruposVsCompra = new HashMap<String,Double>();
		Map<String,Double> mapNombBodegasVsCompra = new HashMap<String,Double>();
		Map<String,Double> mapGrupoBodegaNombBodegaVsCompra = new HashMap<String,Double>();
		
		Map<String,String> mapFiltroBodegas = new HashMap<String,String>();
		if(!filtroBodegas.equals("0") && filtroBodegas.length()>1) {
			filtroBodegas = filtroBodegas.substring(1,filtroBodegas.length()-1);
			String[] arrFiltroBodega = filtroBodegas.split(",");
			for(int i=0; i<arrFiltroBodega.length; i++) {
				mapFiltroBodegas.put(arrFiltroBodega[i].trim(),arrFiltroBodega[i].trim());
			}
		}
		
		Map<String,String> mapFiltroGrupo = new HashMap<String,String>();
		if(!filtroGrupos.equals("0") && filtroGrupos.length()>1) {
			filtroGrupos = filtroGrupos.substring(1,filtroGrupos.length()-1);
			String[] arrFiltroGrupo = filtroGrupos.split(",");
			for(int i=0; i<arrFiltroGrupo.length; i++) {
				mapFiltroGrupo.put(arrFiltroGrupo[i].trim(),arrFiltroGrupo[i].trim());
			}
		}
		
		for(List<String> l: datos) {
			boolean flag = false;
			if(filtroBodegas.equals("0") && filtroGrupos.equals("0") && idTipoBodega.equals("0")) {
				flag = true;
			} else {
				String aux = "";
				boolean flagBod = false;
				if(filtroBodegas.equals("0")) {
					flagBod = true;
				}else {
					aux = mapFiltroBodegas.get(l.get(20));
					if(aux != null) {
						flagBod = true;
					}
				}
				
				
				boolean flagGru = false;
				if(filtroGrupos.equals("0")) {
					flagGru = true;
				}else {
					aux = mapFiltroGrupo.get(l.get(19));
					if(aux != null) {
						flagGru = true;
					}
				}
				
				boolean flagTip = false;
				if(idTipoBodega.equals("0")) {
					flagTip = true;
				}else {
					if(l.get(18).equals(idTipoBodega.trim())) {
						flagTip = true;
					}
				}
				
				flag = flagBod && flagGru && flagTip;
			}
			
			
			if(flag) {
				
				Double parcialCompra = Double.parseDouble(l.get(14).replaceAll(",", ""));
				
				Double acumGrupo = mapNombGruposVsCompra.get(l.get(1));
				if(acumGrupo==null) {
					acumGrupo = (double)0;
				}
				mapNombGruposVsCompra.put(l.get(1), acumGrupo + parcialCompra);
				
				Double acumBodega = mapNombBodegasVsCompra.get(l.get(21));
				if(acumBodega==null) {
					acumBodega = (double)0;
				}
				mapNombBodegasVsCompra.put(l.get(21), acumBodega + parcialCompra);
				
				
				Double acumGrupBod = mapGrupoBodegaNombBodegaVsCompra.get(l.get(1)+"&_&"+l.get(21));
				if(acumGrupBod==null) {
					acumGrupBod = (double)0;
				}
				
				mapGrupoBodegaNombBodegaVsCompra.put(l.get(1)+"&_&"+l.get(21), acumGrupBod + parcialCompra);
				
				
				
			}
			
		}
		
		List<String> nomBodegas = new ArrayList<String>();
		for (Map.Entry<String, Double> bodega : mapNombBodegasVsCompra.entrySet()) {

			Double total = mapNombBodegasVsCompra.get(bodega.getKey());
			if((double)total > (double)0) {
				nomBodegas.add(bodega.getKey());
			}
		}
		Collections.sort(nomBodegas);
		
		List<String> nomGrupos = new ArrayList<String>();
		for (Map.Entry<String, Double> grupo : mapNombGruposVsCompra.entrySet()) {
			Double total = mapNombGruposVsCompra.get(grupo.getKey());
			if((double)total > (double)0) {
				nomGrupos.add(grupo.getKey());
			}
		}
		Collections.sort(nomGrupos);
		
		
		String categorias2 = "";
		String series2 = "";
		for (String bodega : nomBodegas) {
				boolean flag = false;
				String series3 = "";
				for (String grupo : nomGrupos) {
					Double total = mapNombGruposVsCompra.get(grupo);
					if((double)total > (double)0) {
						categorias2 += "'" +  grupo + "',";
					}
					if((double)total > (double)0) {
						Double valor = mapGrupoBodegaNombBodegaVsCompra.get(grupo+"&_&"+bodega);
						
						if(valor==null) {
							series3 += "0,";
						}else {
							DecimalFormat dec = new DecimalFormat("0",symbols);
							if((double)total != (double)0) {
								series3 += "" +  dec.format(valor/total*100) + ",";
							}else {
								series3 += "" +  "0" + ",";
							}
						}
						flag = true;
					}
				}
				if(flag) {
					series3 = series3.substring(0,series3.length()-1)+"]},";
					series2 += "{name: '" + bodega + "', data: [" + series3;
				}
		}
		if(categorias2.length()>1) {
			categorias2 = "["+categorias2.substring(0,categorias2.length()-1)+"]";
		}else {
			categorias2 = "[]";
		}
		if(series2.length()>1) {
			series2 = "["+series2.substring(0,series2.length()-1)+"]";
		}else {
			series2 = "[]";
		}
		
		
		List<String> graficos2 = new ArrayList<String>();
		
		graficos2.add(categorias2);
		graficos2.add(series2);
		
		
		
		String listNomBodegas = " ";
		for(String x: nomBodegas){
			listNomBodegas += "'"+x+"',";
		};
		
		if(listNomBodegas.length()>1) {
			graficos2.add(listNomBodegas.substring(0,listNomBodegas.length()-1).trim());
		}
		
		
		String listNomGrupos = " ";
		for(String x: nomGrupos){
			listNomGrupos += "'"+x+"',";
		};
		
		if(listNomGrupos.length()>1) {
			graficos2.add(listNomGrupos.substring(0,listNomGrupos.length()-1).trim());
		}
		
		
		return (graficos2);
	}
	
	public static List<String> graficoDistribucionGrupoBodegasPorUnidades(Map<String, String> mapeoDiccionario, String filtroGrupos, String filtroBodegas, 
			String idTipoBodega, String esPorSucursal, String id_sucursal, List<List<String>> datos) {
		
		Map<String,Double> mapNombGruposVsCantidad = new HashMap<String,Double>();
		Map<String,Double> mapNombBodegasVsCantidad = new HashMap<String,Double>();
		Map<String,Double> mapGrupoBodegaNombBodegaVsCantidad = new HashMap<String,Double>();
		
		Map<String,String> mapFiltroBodegas = new HashMap<String,String>();
		if(!filtroBodegas.equals("0") && filtroBodegas.length()>1) {
			filtroBodegas = filtroBodegas.substring(1,filtroBodegas.length()-1);
			String[] arrFiltroBodega = filtroBodegas.split(",");
			for(int i=0; i<arrFiltroBodega.length; i++) {
				mapFiltroBodegas.put(arrFiltroBodega[i].trim(),arrFiltroBodega[i].trim());
			}
		}
		
		Map<String,String> mapFiltroGrupo = new HashMap<String,String>();
		if(!filtroGrupos.equals("0") && filtroGrupos.length()>1) {
			filtroGrupos = filtroGrupos.substring(1,filtroGrupos.length()-1);
			String[] arrFiltroGrupo = filtroGrupos.split(",");
			for(int i=0; i<arrFiltroGrupo.length; i++) {
				mapFiltroGrupo.put(arrFiltroGrupo[i].trim(),arrFiltroGrupo[i].trim());
			}
		}
		
		
		for(List<String> l: datos) {
			
			boolean flag = false;
			if(filtroBodegas.equals("0") && filtroGrupos.equals("0") && idTipoBodega.equals("0")) {
				flag = true;
			} else {
				String aux = "";
				boolean flagBod = false;
				if(filtroBodegas.equals("0")) {
					flagBod = true;
				}else {
					aux = mapFiltroBodegas.get(l.get(20));
					if(aux != null) {
						flagBod = true;
					}
				}
				
				
				boolean flagGru = false;
				if(filtroGrupos.equals("0")) {
					flagGru = true;
				}else {
					aux = mapFiltroGrupo.get(l.get(19));
					if(aux != null) {
						flagGru = true;
					}
				}
				
				
				boolean flagTip = false;
				if(idTipoBodega.equals("0")) {
					flagTip = true;
				}else {
					if(l.get(18).equals(idTipoBodega.trim())) {
						flagTip = true;
					}
				}
				
				flag = flagBod && flagGru && flagTip;
			}
			
			if(flag) {
				
				Double parcialCantidad = Double.parseDouble(l.get(11).replaceAll(",", ""));
				
				Double acumGrupo = mapNombGruposVsCantidad.get(l.get(1));
				if(acumGrupo==null) {
					acumGrupo = (double)0;
				}
				mapNombGruposVsCantidad.put(l.get(1), acumGrupo + parcialCantidad);
				
				Double acumBodega = mapNombBodegasVsCantidad.get(l.get(21));
				if(acumBodega==null) {
					acumBodega = (double)0;
				}
				mapNombBodegasVsCantidad.put(l.get(21), acumBodega + parcialCantidad);
				
				
				Double acumGrupBod = mapGrupoBodegaNombBodegaVsCantidad.get(l.get(1)+"&_&"+l.get(21));
				if(acumGrupBod==null) {
					acumGrupBod = (double)0;
				}
				
				
				mapGrupoBodegaNombBodegaVsCantidad.put(l.get(1)+"&_&"+l.get(21), acumGrupBod + parcialCantidad);
				
				
				
			}
		}
		
		
		List<String> nomBodegas = new ArrayList<String>();
		for (Map.Entry<String, Double> bodega : mapNombBodegasVsCantidad.entrySet()) {
			Double total = mapNombBodegasVsCantidad.get(bodega.getKey());
			if((double)total > (double)0) {
				nomBodegas.add(bodega.getKey());
			}
		}
		Collections.sort(nomBodegas);
		
		List<String> nomGrupos = new ArrayList<String>();
		for (Map.Entry<String, Double> grupo : mapNombGruposVsCantidad.entrySet()) {
			Double total = mapNombGruposVsCantidad.get(grupo.getKey());
			if((double)total > (double)0) {
				nomGrupos.add(grupo.getKey());
			}
		}
		Collections.sort(nomGrupos);
		
		
		String categorias2 = "";
		String series2 = "";
		for (String bodega : nomBodegas) {
				boolean flag = false;
				String series3 = "";
				for (String grupo : nomGrupos) {
					Double total = mapNombGruposVsCantidad.get(grupo);
					if((double)total > (double)0) {
						categorias2 += "'" +  grupo + "',";
					}
					if((double)total > (double)0) {
						//Double total = grupo.getValue();
						Double valor = mapGrupoBodegaNombBodegaVsCantidad.get(grupo+"&_&"+bodega);
						
						if(valor==null) {
							series3 += "0,";
						}else {
							DecimalFormat dec = new DecimalFormat("0",symbols);
							if((double)total != (double)0) {
								series3 += "" +  dec.format(valor/total*100) + ",";
							}else {
								series3 += "" +  "0" + ",";
							}
						}
						flag = true;
					}
				}
				if(flag) {
					series3 = series3.substring(0,series3.length()-1)+"]},";
					series2 += "{name: '" + bodega + "', data: [" + series3;
				}
		}
		
		if(categorias2.length()>1) {
			categorias2 = "["+categorias2.substring(0,categorias2.length()-1)+"]";
		}else {
			categorias2 = "[]";
		}
		if(series2.length()>1) {
			series2 = "["+series2.substring(0,series2.length()-1)+"]";
		}else {
			series2 = "[]";
		}
		
		List<String> graficos2 = new ArrayList<String>();
		graficos2.add(categorias2);
		graficos2.add(series2);
		
		return (graficos2);
	}
	
	
	
	public static List<String> graficoOcupacionPorGrupoUnidades(Connection con, String db, String pais, String esPorSucursal, String id_sucursal) {
		List<java.sql.Date> listFechas = new ArrayList<java.sql.Date>();
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
		for(int i=0;i<listFechas.size();i++){
			aux.add(ReportGraficos.ocupacionUnidadesFechaYGrupo(con, db, listFechas.get(i).toString(), pais, esPorSucursal, id_sucursal));
		}
		for(int i=0;i<aux.get(0).size();i++){
			String aux4 = ",";if(i==aux.get(0).size()-1) aux4="";
			String aux3 = "{"+
	            " name: ";
			String aux1 = "[";
			String aux2 = "";
			for(int j=0;j<aux.size();j++){
				String x=",";if(j==aux.size()-1) x="";
				aux1=aux1+aux.get(j).get(i).get(1)+x;
				aux2=aux.get(j).get(i).get(0);
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
	
	public static List<List<String>> ocupacionUnidadesFechaYGrupo(Connection con, String db, String fechaAAMMDD, String pais, String esPorSucursal, String id_sucursal) {
		List<String> tipoBodegas = new ArrayList<String>();
		List<String> grupos = new ArrayList<String>();
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and bodegaEmpresa.id_sucursal = " + id_sucursal;
		}
		Map<String,Double> mapListaGrupoTipBod = new HashMap<String,Double>();
		Map<String,Double> mapListaGrupo = new HashMap<String,Double>();
		String query = String.format(" select " +
				" tipoBodega.nombre, " +
				" grupo.nombre, " +
				" movimiento.id_equipo, " +
				" sum(if(movimiento.id_tipoMovimiento=2,-1,1)*movimiento.cantidad) " +
				" from `%s`.movimiento " +
				" left join `%s`.equipo on equipo.id = movimiento.id_equipo " +
				" left join `%s`.grupo on grupo.id = equipo.id_grupo " +
				" left join `%s`.guia on guia.id = movimiento.id_guia " +
				" left join `%s`.compra on compra.id = movimiento.id_compra " +
				" left join `%s`.factura on factura.id = compra.id_factura " +
				" left join `%s`.baja on baja.id = movimiento.id_baja " +
				" left join `%s`.actaBaja on actaBaja.id = baja.id_actaBaja " +
				" left join `%s`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa " +
				" left join `%s`.tipoBodega on tipoBodega.id = bodegaEmpresa.esInterna " +
				" where ifnull(ifnull(guia.fecha,factura.fecha),actaBaja.fecha) <= ? " + condSucursal +
				" group by tipoBodega.nombre,movimiento.id_equipo " +
				" order by grupo.nombre,equipo.nombre;",db,db,db,db,db,db,db,db,db,db);
		try (PreparedStatement smt6 = con.prepareStatement(query)) {
			smt6.setString(1, fechaAAMMDD);
			try(ResultSet rs6 = smt6.executeQuery()) {
				while (rs6.next()) {
					Double total = rs6.getDouble(4);
					Double acum1 = mapListaGrupo.get(rs6.getString(2));
					if (acum1 == null) {
						acum1 = (double) 0;
					}
					Double acum2 = mapListaGrupoTipBod.get(rs6.getString(2) + "_" + rs6.getString(1));
					if (acum2 == null) {
						acum2 = (double) 0;
					}
					mapListaGrupo.put(rs6.getString(2), total + acum1);
					mapListaGrupoTipBod.put(rs6.getString(2) + "_" + rs6.getString(1), total + acum2);
				}
			}
			query = String.format(" select tipoBodega.nombre from `%s`.tipoBodega where id=2;",db);
			try (PreparedStatement smt7 = con.prepareStatement(query);
					ResultSet rs7 = smt7.executeQuery()) {
				while (rs7.next()) {
					tipoBodegas.add(rs7.getString(1));  //nombre de tipobodega
				}
			}
			query = String.format(" select  grupo.nombre  from `%s`.grupo  order by grupo.nombre;",db);
			try (PreparedStatement smt8 = con.prepareStatement(query);
				 ResultSet rs8 = smt8.executeQuery()) {
				while (rs8.next()) {
					grupos.add(rs8.getString(1));  //nombre de grupo
				}
			}
		} catch (SQLException e) {
			String className = ReportGraficos.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
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
				if((double)suma1>(double)0){
					Double total = (double) 0;
					Double auxLista = mapListaGrupoTipBod.get(grupos.get(i)+"_"+tipoBodegas.get(j));
					if(auxLista!=null) {
						total = total + auxLista;
					}
					total= total/suma1;
					DecimalFormat dec = new DecimalFormat("0",symbols);
					List<String> aux= new ArrayList<String>();
					aux.add(grupos.get(i));
					aux.add(dec.format(total*100));
					resultado.add(aux);
				}
				
			}
		}
		return (resultado);
	}

	public static List<String> graficoOcupacionPorPropiedadUnidades(Connection con, String db, String pais, String esPorSucursal, String id_sucursal) {
		List<java.sql.Date> listFechas = new ArrayList<java.sql.Date>();
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
		for(int i=0;i<listFechas.size();i++){
			aux.add(ReportGraficos.ocupacionUnidadesFechaYPropiedad(con, db, listFechas.get(i).toString(), pais, esPorSucursal, id_sucursal));
		}
		for(int i=0;i<aux.get(0).size();i++){
			String aux4 = ",";if(i==aux.get(0).size()-1) aux4="";
			String aux3 = "{"+
					" name: ";
			String aux1 = "[";
			String aux2 = "";
			for(int j=0;j<aux.size();j++){
				String x=",";if(j==aux.size()-1) x="";
				aux1=aux1+aux.get(j).get(i).get(1)+x;
				aux2=aux.get(j).get(i).get(0);
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

	public static List<List<String>> ocupacionUnidadesFechaYPropiedad(Connection con, String db, String fechaAAMMDD, String pais, String esPorSucursal, String id_sucursal) {
		List<String> tipoBodegas = new ArrayList<String>();
		List<String> propiedades = new ArrayList<String>();
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and bodegaEmpresa.id_sucursal = " + id_sucursal;
		}
		Map<String,Double> mapListaPropiedadTipBod = new HashMap<String,Double>();
		Map<String,Double> mapListaPropiedad = new HashMap<String,Double>();
		String query = String.format(" select " +
				" tipoBodega.nombre, " +
				" propiedad.nombre, " +
				" movimiento.id_equipo, " +
				" sum(if(movimiento.id_tipoMovimiento=2,-1,1)*movimiento.cantidad) " +
				" from `%s`.movimiento " +
				" left join `%s`.equipo on equipo.id = movimiento.id_equipo " +
				" left join `%s`.propiedad on propiedad.id = equipo.id_propiedad " +
				" left join `%s`.guia on guia.id = movimiento.id_guia " +
				" left join `%s`.compra on compra.id = movimiento.id_compra " +
				" left join `%s`.factura on factura.id = compra.id_factura " +
				" left join `%s`.baja on baja.id = movimiento.id_baja " +
				" left join `%s`.actaBaja on actaBaja.id = baja.id_actaBaja " +
				" left join `%s`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa " +
				" left join `%s`.tipoBodega on tipoBodega.id = bodegaEmpresa.esInterna " +
				" where ifnull(ifnull(guia.fecha,factura.fecha),actaBaja.fecha) <= ? " + condSucursal +
				" group by tipoBodega.nombre,movimiento.id_equipo " +
				" order by propiedad.nombre,equipo.nombre;",db,db,db,db,db,db,db,db,db,db);
		try (PreparedStatement smt6 = con.prepareStatement(query)) {
			smt6.setString(1, fechaAAMMDD);
			try(ResultSet rs6 = smt6.executeQuery()) {
				while (rs6.next()) {
					Double total = rs6.getDouble(4);
					Double acum1 = mapListaPropiedad.get(rs6.getString(2));
					if (acum1 == null) {
						acum1 = (double) 0;
					}
					Double acum2 = mapListaPropiedadTipBod.get(rs6.getString(2) + "_" + rs6.getString(1));
					if (acum2 == null) {
						acum2 = (double) 0;
					}
					mapListaPropiedad.put(rs6.getString(2), total + acum1);
					mapListaPropiedadTipBod.put(rs6.getString(2) + "_" + rs6.getString(1), total + acum2);
				}
			}
			query = String.format(" select tipoBodega.nombre from `%s`.tipoBodega where id=2;",db);
			try (PreparedStatement smt7 = con.prepareStatement(query);
				 ResultSet rs7 = smt7.executeQuery()) {
				while (rs7.next()) {
					tipoBodegas.add(rs7.getString(1));  //nombre de tipobodega
				}
			}
			query = String.format(" select  propiedad.nombre  from `%s`.propiedad  order by propiedad.nombre;",db);
			try (PreparedStatement smt8 = con.prepareStatement(query);
				 ResultSet rs8 = smt8.executeQuery()) {
				while (rs8.next()) {
					propiedades.add(rs8.getString(1));  //nombre de propiedad
				}
			}
		} catch (SQLException e) {
			String className = ReportGraficos.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		Map<String,Double> suma = new HashMap<String,Double>();
		for(int j=0;j<propiedades.size();j++){
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
				if((double)suma1>(double)0){
					Double total = (double) 0;
					Double auxLista = mapListaPropiedadTipBod.get(propiedades.get(i)+"_"+tipoBodegas.get(j));
					if(auxLista!=null) {
						total = total + auxLista;
					}
					total= total/suma1;
					DecimalFormat dec = new DecimalFormat("0",symbols);
					List<String> aux= new ArrayList<String>();
					aux.add(propiedades.get(i));
					aux.add(dec.format(total*100));
					resultado.add(aux);
				}

			}
		}
		return (resultado);
	}
	
	
	
			
}
	
	
	
