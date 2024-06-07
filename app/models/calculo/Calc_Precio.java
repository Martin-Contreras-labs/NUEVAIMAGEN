package models.calculo;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.tables.UnidadTiempo;


public class Calc_Precio {
	
	public Long id_bodegaEmpresa;
	public Long id_equipo;
	public Long id_cotizacion;
	public Long id_moneda;
	
	public Double precioCompra;  // precio de compra desde facturas
	public Double precioVenta;	// precio de venta desde precios o maestro de precios
	public Double precioReposicion; // precio de reposicion desde precios o maestro de precios
	public Double precioArriendo_dia;
	public Double precioMinimo;
	public Long permanenciaMinima;


	



	public Calc_Precio(Long id_bodegaEmpresa, Long id_equipo, Long id_cotizacion, Long id_moneda, Double precioCompra,
			Double precioVenta, Double precioReposicion, Double precioArriendo_dia, Double precioMinimo,
			Long permanenciaMinima) {
		super();
		this.id_bodegaEmpresa = id_bodegaEmpresa;
		this.id_equipo = id_equipo;
		this.id_cotizacion = id_cotizacion;
		this.id_moneda = id_moneda;
		this.precioCompra = precioCompra;
		this.precioVenta = precioVenta;
		this.precioReposicion = precioReposicion;
		this.precioArriendo_dia = precioArriendo_dia;
		this.precioMinimo = precioMinimo;
		this.permanenciaMinima = permanenciaMinima;
	}



	public Calc_Precio() {
		super();
	}



	public static Map<String,Calc_Precio> mapPrecios (Connection con, String db, List<Long> listIdBodegaEmpresa){
		Map<String,Calc_Precio> map = new HashMap<String,Calc_Precio>();
		String listaInBodega = listIdBodegaEmpresa.toString().replace("[", "").replace("]", "");
		Map<Long,Double> equivTiempo = UnidadTiempo.equivalencia(con, db);
		
		Map<Long,Double> mapDctoBodega = Calc_Precio.mapDctoBodega(con, db);
		Map<String,Double> mapDctoGrupo = Calc_Precio.mapDctoGrupo(con, db);
		Map<String,Double> mapDctoEquipo = Calc_Precio.mapDctoEquipo(con, db);
		
		
		if(listaInBodega.trim().length()>0) {
			try {
				PreparedStatement smt = con
						.prepareStatement(" select "
								+ " id_bodegaEmpresa, "
								+ " id_equipo, "
								+ " id_cotizacion, "
								+ " id_moneda, "
								+ " precioVenta, "
								+ " precioReposicion, "
								+ " precioArriendo, "
								+ " precioMinimo, "
								+ " permanenciaMinima, "
								+ " id_unidadTiempo, "
								+ " equipo.id_grupo "
								+ " from `"+db+"`.listaPrecio "
								+ " left join `"+db+"`.equipo on equipo.id = listaPrecio.id_equipo "
								+ " where id_bodegaEmpresa in ("+listaInBodega+");");
				ResultSet rs = smt.executeQuery();
				while (rs.next()) {
					Calc_Precio aux = new Calc_Precio();
					Long id_unidadTiempo = rs.getLong(10); if(id_unidadTiempo == 0) id_unidadTiempo = (long) 1;
					
					Double factor = equivTiempo.get(id_unidadTiempo);
					Double precioArriendo_dia = rs.getDouble(7) / factor;
					//determina la tasa de descuento que se debe eliminar
					
					Double dctoBodega = mapDctoBodega.get(rs.getLong(1));
					if(dctoBodega==null) dctoBodega =  (double) 0;
					
					Double dctoGrupo = mapDctoGrupo.get(rs.getString(1)+"_"+rs.getString(11));
					if(dctoGrupo==null) dctoGrupo =  (double) 0;
					
					Double dctoEquipo = mapDctoEquipo.get(rs.getString(1)+"_"+rs.getString(2));
					if(dctoEquipo==null) dctoEquipo =  (double) 0;
					
					Double tasaDcto = ((1-dctoBodega)*(1-dctoGrupo)*(1-dctoEquipo));
					
					//fin descuento
					
					aux.id_bodegaEmpresa = rs.getLong(1);
					aux.id_equipo = rs.getLong(2);
					aux.id_cotizacion = rs.getLong(3);
					aux.id_moneda = rs.getLong(4);
					aux.precioVenta = rs.getDouble(5);
					aux.precioReposicion = rs.getDouble(6);
					aux.precioArriendo_dia = precioArriendo_dia * tasaDcto;
					aux.precioMinimo = rs.getDouble(8);
					aux.permanenciaMinima = rs.getLong(9);
					aux.precioCompra = (double)0;
					
					String key = rs.getString(1)+"_"+rs.getString(2)+"_"+rs.getString(3);
					map.put(key, aux);
				}
				rs.close();
				smt.close();
			} catch (SQLException e) {
	    			e.printStackTrace();
			}
		}
		
		return map;
		
	}
	
	public static Map<Long,Calc_Precio> mapMaestroPrecios (Connection con, String db){
		Map<Long,Calc_Precio> map = new HashMap<Long,Calc_Precio>();
		Map<Long,Double> equivTiempo = UnidadTiempo.equivalencia(con, db);
			try {
				PreparedStatement smt = con
						.prepareStatement(" select "
								+ " 0, "
								+ " id_equipo, "
								+ " 0, "
								+ " id_moneda, "
								+ " precioVenta, "
								+ " precioReposicion, "
								+ " precioArriendo, "
								+ " precioMinimo, "
								+ " permanenciaMinima, "
								+ " id_unidadTiempo "
								+ " from `"+db+"`.precio;");
				ResultSet rs = smt.executeQuery();
				while (rs.next()) {
					Calc_Precio aux = new Calc_Precio();
					Long id_unidadTiempo = rs.getLong(10); 
					if(id_unidadTiempo == 0) {
						id_unidadTiempo = (long) 1;
					}
					
					Double factor = equivTiempo.get(id_unidadTiempo);
					Double precioArriendo_dia = rs.getDouble(7) / factor;
					
					aux.id_bodegaEmpresa = (long) 0;
					aux.id_equipo = rs.getLong(2);
					aux.id_cotizacion =  (long) 0;
					aux.id_moneda = rs.getLong(4);
					aux.precioVenta = rs.getDouble(5);
					aux.precioReposicion = rs.getDouble(6);
					aux.precioArriendo_dia = precioArriendo_dia;
					aux.precioMinimo = rs.getDouble(8);
					aux.permanenciaMinima = rs.getLong(9);
					aux.precioCompra = (double)0;
					map.put(rs.getLong(2), aux);
				}
				rs.close();
				smt.close();
			} catch (SQLException e) {
	    			e.printStackTrace();
			}
		return map;
		
	}
	
	public static Map<Long,Calc_Precio> mapPreciosCompra (Connection con, String db){
		Map<Long,Calc_Precio> map = new HashMap<Long,Calc_Precio>();
		try {
			PreparedStatement smt2 = con
					.prepareStatement("select max(concat(factura.fecha,'_',compra.id)) from `"+db+"`.compra "
							+ " left join `"+db+"`.factura on factura.id = compra.id_factura  group by id_equipo;");
			ResultSet rs2 = smt2.executeQuery();
			String lista ="";
			while (rs2.next()) {
				String[] arrAux = rs2.getString(1).split("_");
				lista = lista + arrAux[1] + ",";
			}
			rs2.close();smt2.close();
			
			if(lista.length()>1) {
				lista=lista.substring(0,lista.length()-1);
				PreparedStatement smt = con
						.prepareStatement(" select " +
								" id_equipo,  " +
								" precioUnidad," +
								" id_moneda  " +
								" from `"+db+"`.compra  " +
								" where id in ("+lista+") ;");
				ResultSet rs = smt.executeQuery();
				while (rs.next()) {
					Calc_Precio aux = new Calc_Precio();
					
					aux.id_bodegaEmpresa = (long) 0;
					aux.id_equipo = rs.getLong(1);
					aux.id_cotizacion =  (long) 0;
					aux.id_moneda = rs.getLong(3);
					aux.precioVenta =  (double)0;
					aux.precioReposicion =  (double)0;
					aux.precioArriendo_dia =  (double)0;
					aux.precioMinimo =  (double)0;
					aux.permanenciaMinima =  (long)0;
					aux.precioCompra = rs.getDouble(2);
					map.put(rs.getLong(1), aux);
				}
				rs.close();
				smt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
		
	}
	
	public static Map<Long,Double> mapDctoBodega (Connection con, String db){
		Map<Long,Double> map = new HashMap<Long,Double>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select id, tasaDescto from `"+db+"`.bodegaEmpresa where tasaDescto > 0;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				map.put(rs.getLong(1), rs.getDouble(2));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return map;
	}
	
	public static Map<String,Double> mapDctoGrupo (Connection con, String db){
		Map<String,Double> map = new HashMap<String,Double>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select id_bodegaEmpresa, id_grupo, tasaDescto from `"+db+"`.dctoGrupo where tasaDescto > 0;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				map.put(rs.getString(1)+"_"+rs.getString(2), rs.getDouble(3));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return map;
	}
	
	public static Map<String,Double> mapDctoEquipo (Connection con, String db){
		Map<String,Double> map = new HashMap<String,Double>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select id_bodegaEmpresa, id_equipo, tasaDescto from `"+db+"`.dctoEquipo where tasaDescto > 0;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				map.put(rs.getString(1)+"_"+rs.getString(2), rs.getDouble(3));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return map;
	}
		
	
	
	
}
	
	
	
