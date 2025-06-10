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
import models.tables.ActaBaja;
import models.tables.Moneda;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Calc_AjustesEpOdo {
	
	public Long id;
	public Long id_bodegaEmpresa;
	public Long id_tipoAjuste;  // 1 descuento 2 sobrecargo
	public Long id_moneda;
	public Double totalAjuste;
	


	public Calc_AjustesEpOdo(Long id, Long id_bodegaEmpresa, Long id_tipoAjuste, Long id_moneda,
			Double totalAjuste) {
		super();
		this.id = id;
		this.id_bodegaEmpresa = id_bodegaEmpresa;
		this.id_tipoAjuste = id_tipoAjuste;
		this.id_moneda = id_moneda;
		this.totalAjuste = totalAjuste;
	}



	public Calc_AjustesEpOdo() {
		super();
	}

	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	public static Map<Long,Double> mapTotalAjustePorBodega (Connection con, String db, String desdeAAMMDD, String hastaAAMMDD, String esPorSucursal, String id_sucursal) {
		Map<Long,Double> map = new HashMap<Long,Double>();
		try{
			List<Calc_AjustesEpOdo> lista = Calc_AjustesEpOdo.listaAjustesEntreFechas(con, db, desdeAAMMDD, hastaAAMMDD, esPorSucursal, id_sucursal);
			for(int i=0; i<lista.size(); i++) {
				Double ajuste = lista.get(i).totalAjuste;
				Long id_tipoAjuste = lista.get(i).id_tipoAjuste;
				if((long) id_tipoAjuste == (long) 1) {
					ajuste = ajuste * (double)-1;
				}
				Double rs = map.get(lista.get(i).id_bodegaEmpresa);
				if(rs!=null) {
					map.put(lista.get(i).id_bodegaEmpresa, rs + ajuste);
				}else {
					map.put(lista.get(i).id_bodegaEmpresa,ajuste);
				}
			}
		} catch (Exception e) {
			String className = ActaBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return map;
	}
	
	public static List<Calc_AjustesEpOdo> listaAjustesEntreFechas (Connection con, String db, String desdeAAMMDD, String hastaAAMMDD, String esPorSucursal, String id_sucursal){
		List<Calc_AjustesEpOdo> lista = new ArrayList<Calc_AjustesEpOdo>();
		String query = String.format(" select "
				+ " ajustesEpOdo.id, "
				+ " ajustesEpOdo.id_bodegaEmpresa, "
				+ " ajustesEpOdo.id_tipoAjuste, "
				+ " ajustesEpOdo.id_moneda, "
				+ " ajustesEpOdo.totalAjuste, "
				+ " bodegaEmpresa.id_sucursal "
				+ " from `%s`.ajustesEpOdo "
				+ " left join `%s`.bodegaEmpresa on bodegaEmpresa.id = ajustesEpOdo.id_bodegaEmpresa "
				+ " where ajustesEpOdo.fechaAjuste between '"+desdeAAMMDD+"' and '"+hastaAAMMDD+"' "
				+ " order by ajustesEpOdo.id_bodegaEmpresa;",db,db);
		try (PreparedStatement smt = con.prepareStatement(query)) {
			try (ResultSet rs = smt.executeQuery()) {
				while (rs.next()) {
					if (esPorSucursal.equals("1")) {
						if (rs.getString(6).equals(id_sucursal)) {
							Calc_AjustesEpOdo aux = new Calc_AjustesEpOdo();
							aux.id = rs.getLong(1);
							aux.id_bodegaEmpresa = rs.getLong(2);
							aux.id_tipoAjuste = rs.getLong(3);
							aux.id_moneda = rs.getLong(4);
							aux.totalAjuste = rs.getDouble(5);
							lista.add(aux);
						}
					} else {
						Calc_AjustesEpOdo aux = new Calc_AjustesEpOdo();
						aux.id = rs.getLong(1);
						aux.id_bodegaEmpresa = rs.getLong(2);
						aux.id_tipoAjuste = rs.getLong(3);
						aux.id_moneda = rs.getLong(4);
						aux.totalAjuste = rs.getDouble(5);
						lista.add(aux);
					}

				}
			}
		} catch (SQLException e) {
			String className = ActaBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return lista;
	}
	
	public static List<List<String>> listaEntreFechasPorBodega (Connection con, String db, String desdeAAMMDD, String hastaAAMMDD, Long id_bodegaEmpresa){
		List<List<String>> lista = new ArrayList<List<String>>();
		String query = String.format(" select "
				+ " ajustesEpOdo.id, "
				+ " ajustesEpOdo.id_bodegaEmpresa, "
				+ " ajustesEpOdo.id_tipoAjuste, "
				+ " ajustesEpOdo.id_moneda, "
				+ " ajustesEpOdo.totalAjuste, "
				+ " tipoAjuste.ajuste, "
				+ " ajustesEpOdo.concepto "
				+ " from `%s`.ajustesEpOdo "
				+ " left join `%s`.tipoAjuste on tipoAjuste.id = ajustesEpOdo.id_tipoAjuste"
				+ " where id_bodegaEmpresa=? and fechaAjuste between '"+desdeAAMMDD+"' and '"+hastaAAMMDD+"' "
				+ " order by id_bodegaEmpresa;",db,db);
		try (PreparedStatement smt = con.prepareStatement(query)) {
			smt.setLong(1, id_bodegaEmpresa);
			try (ResultSet rs = smt.executeQuery()) {
				Map<Long, Long> dec = Moneda.numeroDecimal(con, db);
				switch (dec.get((long) 1).toString()) {
					case "0":
						myformatdouble = new DecimalFormat("#,##0");
						break;
					case "2":
						myformatdouble = new DecimalFormat("#,##0.00");
						break;
					case "4":
						myformatdouble = new DecimalFormat("#,##0.0000");
						break;
					case "6":
						myformatdouble = new DecimalFormat("#,##0.000000");
						break;
					default:
						break;
				}
				while (rs.next()) {
					List<String> aux = new ArrayList<String>();
					Long tipoAjuste = (long) -1;
					if ((long) rs.getLong(3) == 2) {
						tipoAjuste = (long) 1;
					}
					Double totAjuste = rs.getDouble(5) * tipoAjuste;
					aux.add(rs.getString(1));                        // 0 ajustesEpOdo.id
					aux.add(rs.getString(2));                        // 1 id_bodegaEmpresa
					aux.add(rs.getString(3));                        // 2 id_tipoAjuste
					aux.add(rs.getString(4));                        // 3 id_moneda
					aux.add(myformatdouble.format(totAjuste));        // 4 total ajuste con formato
					aux.add(rs.getString(6));                        // 5 tipo de ajuste
					aux.add(rs.getString(7));                        // 6 concepto de ajuste
					lista.add(aux);
				}
			}
		} catch (SQLException e) {
			String className = ActaBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return lista;
	}

	
}
	
	
	
