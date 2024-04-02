package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.calculo.Calc_AjustesEP;
import models.calculo.Calc_BodegaEmpresa;
import models.calculo.Calc_Precio;
import models.calculo.Inventarios;
import models.calculo.ModCalc_GuiasPer;
import models.calculo.ModCalc_InvInicial;
import models.calculo.ModeloCalculo;
import models.utilities.Fechas;



public class Ppto {

	public Long id;
	public Long id_bodegaEmpresa;
	public String anioMes; // por defecto 2020;01
	public Long id_grupo;
	public Long esVenta;  // valor cero es arriendo
	public Long id_moneda;
	
	public Double venta;
	public Double arriendo;
	public Double total;
	
	public String concepto;
	
	public String nombreGrupo;
	
	public String ventaConFormato;
	public String arriendoConFormato;
	public String totalConFormato;
	
	
	public Ppto(Long id, Long id_bodegaEmpresa, String anioMes, Long id_grupo, Long esVenta, Long id_moneda,
			Double venta, Double arriendo, Double total, String concepto, String nombreGrupo, String ventaConFormato,
			String arriendoConFormato, String totalConFormato) {
		super();
		this.id = id;
		this.id_bodegaEmpresa = id_bodegaEmpresa;
		this.anioMes = anioMes;
		this.id_grupo = id_grupo;
		this.esVenta = esVenta;
		this.id_moneda = id_moneda;
		this.venta = venta;
		this.arriendo = arriendo;
		this.total = total;
		this.concepto = concepto;
		this.nombreGrupo = nombreGrupo;
		this.ventaConFormato = ventaConFormato;
		this.arriendoConFormato = arriendoConFormato;
		this.totalConFormato = totalConFormato;
	}

	public Ppto() {
		super();
	}
	
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	
	public static Map<Long,List<Double>> mapTotalPptoPorAllBodega(Connection con, String db){
		Map<Long,List<Double>> map = new HashMap<Long,List<Double>>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select id_bodegaEmpresa, "
							+ " sum(if(esVenta=1,total,0)) as venta, "
							+ " sum(if(esVenta=0,total,0)) as arriendo, "
							+ " sum(total) as total "
							+ " from `"+db+"`.ppto group by id_bodegaEmpresa;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				List<Double> aux = new ArrayList<Double>();
				aux.add(rs.getDouble(2)); //0 venta
				aux.add(rs.getDouble(3)); //1 arriendo
				aux.add(rs.getDouble(4)); //2 total
				map.put(rs.getLong(1), aux);
			}
			smt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (map);
	}
	
	public static Map<String,Double> mapTotalPptoPorBodega(Connection con, String db, Long id_bodegaEmpresa){
		Map<String,Double> map = new HashMap<String,Double>(); // anio-mes, valor
		try {
			PreparedStatement smt = con
					.prepareStatement(" select ppto.anioMes, sum(ppto.total) from `"+db+"`.ppto "
							+ " where id_bodegaEmpresa=? group by anioMes order by anioMes;");
			smt.setLong(1, id_bodegaEmpresa);
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				map.put(rs.getString(1), rs.getDouble(2));
			}
			smt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (map);
	}
	
	public static Ppto findPorIdPpto(Connection con, String db, Long id_ppto){
		Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
		Long numDecimales = (long)0;
		if(dec!=null) {
			numDecimales = dec.get((long)1);
		}
		switch(numDecimales.toString()) {
		 case "0": myformatdouble = new DecimalFormat("#,##0"); break;
		 case "2": myformatdouble = new DecimalFormat("#,##0.00"); break;
		 case "4": myformatdouble = new DecimalFormat("#,##0.0000"); break;
		 case "6": myformatdouble = new DecimalFormat("#,##0.000000"); break;
		 default:  break;
		}
		Ppto aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement(" select "
							+ " ppto.id, "
							+ " ppto.id_bodegaEmpresa, "
							+ " ppto.anioMes, "
							+ " ppto.id_grupo, "
							+ " ppto.esVenta, "
							+ " ppto.id_moneda,"
							+ " if(esVenta=1,total,0) as venta, "
							+ " if(esVenta=0,total,0) as arriendo, "
							+ " ppto.total as total, "
							+ " ifnull(ppto.concepto,''), "
							+ " ifnull(grupo.nombre,'No Aplica') "
							+ " from `"+db+"`.ppto "
							+ " left join `"+db+"`.grupo on grupo.id = ppto.id_grupo "
							+ " where ppto.id = ? "
							+ " order by anioMes, grupo.nombre;");
			smt.setLong(1, id_ppto);
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				aux = new Ppto(rs.getLong(1),rs.getLong(2),rs.getString(3),rs.getLong(4),rs.getLong(5),rs.getLong(6)
						,rs.getDouble(7),rs.getDouble(8),rs.getDouble(9),rs.getString(10),rs.getString(11), 
						myformatdouble.format(rs.getDouble(7)),myformatdouble.format(rs.getDouble(8)),myformatdouble.format(rs.getDouble(9)));
			}
			smt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(aux);
	}
	
	
	public static List<Ppto> allPorBodegaEmpresa(Connection con, String db, Long id_bodegaEmpresa){
		Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
		Long numDecimales = (long)0;
		if(dec!=null) {
			numDecimales = dec.get((long)1);
		}
		switch(numDecimales.toString()) {
		 case "0": myformatdouble = new DecimalFormat("#,##0"); break;
		 case "2": myformatdouble = new DecimalFormat("#,##0.00"); break;
		 case "4": myformatdouble = new DecimalFormat("#,##0.0000"); break;
		 case "6": myformatdouble = new DecimalFormat("#,##0.000000"); break;
		 default:  break;
		}
		List<Ppto> lista = new ArrayList<Ppto>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select "
							+ " ppto.id, "
							+ " ppto.id_bodegaEmpresa, "
							+ " ppto.anioMes, "
							+ " ppto.id_grupo, "
							+ " ppto.esVenta, "
							+ " ppto.id_moneda,"
							+ " if(esVenta=1,total,0) as venta, "
							+ " if(esVenta=0,total,0) as arriendo, "
							+ " ppto.total as total, "
							+ " ifnull(ppto.concepto,''), "
							+ " ifnull(grupo.nombre,'No Aplica') "
							+ " from `"+db+"`.ppto "
							+ " left join `"+db+"`.grupo on grupo.id = ppto.id_grupo "
							+ " where ppto.id_bodegaEmpresa = ? "
							+ " order by anioMes, grupo.nombre;");
			smt.setLong(1, id_bodegaEmpresa);
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				lista.add(new Ppto(rs.getLong(1),rs.getLong(2),rs.getString(3),rs.getLong(4),rs.getLong(5),rs.getLong(6)
						,rs.getDouble(7),rs.getDouble(8),rs.getDouble(9),rs.getString(10),rs.getString(11), 
						myformatdouble.format(rs.getDouble(7)),myformatdouble.format(rs.getDouble(8)),myformatdouble.format(rs.getDouble(9))));
			}
			smt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(lista);
	}
	
	public static boolean grabarLinea(Connection con, String db, Ppto ppto){
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement(" insert into `"+db+"`.ppto (id_bodegaEmpresa, anioMes, id_grupo, esVenta, total, concepto) "
							+ " values (?,?,?,?,?,?); ");
			smt.setLong(1, ppto.id_bodegaEmpresa);
			smt.setString(2, ppto.anioMes);
			smt.setLong(3, ppto.id_grupo);
			smt.setLong(4, ppto.esVenta);
			smt.setDouble(5, ppto.total);
			smt.setString(6, ppto.concepto);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(flag);
	}
	
	public static boolean updateLinea(Connection con, String db, Ppto ppto){
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement(" update `"+db+"`.ppto set id_bodegaEmpresa=?, anioMes=?, id_grupo=?, esVenta=?, total=?, concepto=? "
							+ " where ppto.id=?; ");
			smt.setLong(1, ppto.id_bodegaEmpresa);
			smt.setString(2, ppto.anioMes);
			smt.setLong(3, ppto.id_grupo);
			smt.setLong(4, ppto.esVenta);
			smt.setDouble(5, ppto.total);
			smt.setString(6, ppto.concepto);
			smt.setLong(7, ppto.id);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(flag);
	}
	
	public static boolean deleteLinea(Connection con, String db, Long id_ppto){
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement(" delete from `"+db+"`.ppto where ppto.id=?; ");
			smt.setLong(1, id_ppto);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(flag);
	}
	
	public static List<List<String>> pptoVsRealxBodega(Connection con, String db, Long id_bodegaEmpresa, String pais){
		Map<String,Double> mapPpto = Ppto.mapTotalPptoPorBodega(con, db, id_bodegaEmpresa); // anio-mes, valor
		Map<String,Double> mapReal = Ppto.mapTotalRealPorBodega(con, db, id_bodegaEmpresa, pais); // anio-mes, valor
		Map<String,String> aux = new HashMap<String,String>();
		for (String anioMes : mapPpto.keySet()) { 
			aux.put(anioMes, anioMes); 
		}
		for (String anioMes : mapReal.keySet()) { 
			aux.put(anioMes, anioMes); 
		}
		List<List<String>> lista2 = new ArrayList<List<String>>();
		for (String anioMes : aux.values()) {
			Double ppto = mapPpto.get(anioMes); if(ppto==null) ppto = (double) 0;
			Double real = mapReal.get(anioMes); if(real==null) real = (double) 0;
			List<String> aux2 = new ArrayList<String>();
			aux2.add(anioMes);
			aux2.add(ppto.toString());
			aux2.add(real.toString());
			lista2.add(aux2);
		}
		//ORDENA LA LISTA
		for(int j=0;j<lista2.size();j++) {
            for(int i=0;i<lista2.size()-j;i++) {
                if (i+1!=lista2.size() && lista2.get(i).get(0).compareToIgnoreCase(lista2.get(i+1).get(0))>0) {
                    List<String> auxOrden;
                    auxOrden=lista2.get(i);
                    lista2.set(i,lista2.get(i+1));
                    lista2.set(i+1, auxOrden);
                }
            }
        }
		Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
		Long numDecimales = (long)0;
		if(dec!=null) {
			numDecimales = dec.get((long)1);
		}
		switch(numDecimales.toString()) {
		 case "0": myformatdouble = new DecimalFormat("#,##0"); break;
		 case "2": myformatdouble = new DecimalFormat("#,##0.00"); break;
		 case "4": myformatdouble = new DecimalFormat("#,##0.0000"); break;
		 case "6": myformatdouble = new DecimalFormat("#,##0.000000"); break;
		 default:  break;
		}
		Double acumPpto = (double)0;
		Double acumReal = (double)0;
		List<List<String>> lista3 = new ArrayList<List<String>>();
		for(int i=0;i<lista2.size();i++) {
			String categoria = lista2.get(i).get(0);
			String parcialPpto = lista2.get(i).get(1);
			String parcialReal = lista2.get(i).get(2);
			if(!parcialPpto.equals("")) acumPpto += Double.parseDouble(parcialPpto);
			if(!parcialReal.equals("")) acumReal += Double.parseDouble(parcialReal);
			List<String> aux3 = new ArrayList<String>();
			aux3.add(categoria);
			aux3.add(myformatdouble.format(Double.parseDouble(parcialPpto)));
			aux3.add(myformatdouble.format(acumPpto));
			if(parcialReal.equals("")) {
				aux3.add("");
				aux3.add("");
			}else {
				aux3.add(myformatdouble.format(Double.parseDouble(parcialReal)));
				aux3.add(myformatdouble.format(acumReal));
			}
			lista3.add(aux3);
		}
		
		return(lista3);
	}
	
	public static Map<String,Double> mapTotalRealPorBodega (Connection con, String db, Long id_Bodega, String pais) {
		Map<String,Double> mapReal = new HashMap<String,Double>();
		List<List<String>> listaFechas = new ArrayList<List<String>>();
		Fechas minFecha = Movimiento.findMinFechaGuiaPorBod(con, db, id_Bodega);
		Fechas hoy = Fechas.hoy();
		Fechas inicio = Fechas.obtenerInicioMes(minFecha.getFechaCal());
		Calendar termino = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaCal();
		while(inicio.getFechaCal().before(termino)) {
			List<String> aux = new ArrayList<String>();
			inicio = Fechas.obtenerFinMes(inicio.getFechaCal());
	    	aux.add(inicio.getFechaStrAAMMDD());
	    	inicio = Fechas.obtenerInicioMes(inicio.getFechaCal());
	    	aux.add(inicio.getFechaStrAAMMDD());
	    	listaFechas.add(aux);
	    	inicio = Fechas.addMeses(inicio.getFechaCal(), 1);
		}
    	Map<String,List<ModeloCalculo>> mapTotales = new HashMap<String,List<ModeloCalculo>>();
    	Map<Long,Long> mapIdBodegas = new HashMap<Long,Long>();
    	//List<Long> listIdBodegaEmpresa = ModCalc_InvInicial.listIdBodegaEmpresa(con, db, "");
    	List<Long> listIdBodegaEmpresa = new ArrayList<Long>();
    	listIdBodegaEmpresa.add(id_Bodega);
		Map<Long,Calc_BodegaEmpresa> mapBodegaEmpresa = Calc_BodegaEmpresa.mapAllBodegasVigentes(con, db);
		Map<String,Calc_Precio> mapPrecios = Calc_Precio.mapPrecios(con, db, listIdBodegaEmpresa);
		Map<Long,Calc_Precio> mapMaestroPrecios = Calc_Precio.mapMaestroPrecios(con, db);
		Map<String, Double> mapFijaTasas = BodegaEmpresa.mapFijaTasasAll(con, db);
		
		Map<String,String> mapPermanencias = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, db);
		
    	for(int i=0;i<listaFechas.size();i++) {
    		String desdeAAMMDD = listaFechas.get(i).get(1);
    		String hastaAAMMDD = listaFechas.get(i).get(0);
    		Map<Long,Double> tasas = TasasCambio.mapTasasPorFecha(con, db, hastaAAMMDD, pais);
    		
    		
    		List<Long> listIdGuia_fechaCorte = ModCalc_InvInicial.listIdGuia_fechaCorte(con, db, desdeAAMMDD);
    		List<Inventarios> inventarioAux = Inventarios.inventario(con, db, listIdBodegaEmpresa, listIdGuia_fechaCorte);
    		List<Long> listIdGuia_entreFechas = ModCalc_GuiasPer.listIdGuia_entreFecha(con, db, desdeAAMMDD, hastaAAMMDD);
    		List<Inventarios> guiasPerAux = Inventarios.guiasPer(con, db, listIdBodegaEmpresa, listIdGuia_entreFechas);
    		List<Calc_AjustesEP> listaAjustes = Calc_AjustesEP.listaAjustesEntreFechas(con, db, desdeAAMMDD, hastaAAMMDD);

    		List<ModCalc_InvInicial> inventarioInicial = ModCalc_InvInicial.resumenInvInicial(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa, 
    				mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, listIdGuia_fechaCorte, inventarioAux);
    		
    		
    		
    		List<ModCalc_GuiasPer> guiasPeriodo = ModCalc_GuiasPer.resumenGuiasPer(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa, 
    				mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, listIdGuia_entreFechas, guiasPerAux, mapPermanencias);
    		
    		List<ModeloCalculo> calculo = ModeloCalculo.valorTotalporBodega(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, inventarioInicial,guiasPeriodo, listaAjustes);
    		
    		mapTotales.put(desdeAAMMDD, calculo);
    		for(int k=0; k<calculo.size(); k++) {
    			Long id_bodegaEmpresa = calculo.get(k).id_bodegaEmpresa;
    			mapIdBodegas.put(id_bodegaEmpresa, id_bodegaEmpresa);
    		}
    		
    	}
    	for(int i=0; i<listaFechas.size(); i++) {
    		
    		String[] auxFech = listaFechas.get(i).get(1).split("-");
    		String anioMes = auxFech[0]+"-"+auxFech[1];
    		Double valor = (double) 0;
    		
    		
    		for (Long value : mapIdBodegas.values()) {
    			
				String desdeAAMMDD = listaFechas.get(i).get(1);
				List<ModeloCalculo> calculo = mapTotales.get(desdeAAMMDD);
				Double totAux = (double) 0;
				if(calculo!=null) {
					for(int k=0;k<calculo.size();k++) {
						if(calculo.get(k).id_bodegaEmpresa-value==0) {
							totAux = calculo.get(k).totalTotal;
						}
					}
				}
				valor = totAux;
    		}
			mapReal.put(anioMes, valor);
    	}
		return (mapReal);
	}


        
}
