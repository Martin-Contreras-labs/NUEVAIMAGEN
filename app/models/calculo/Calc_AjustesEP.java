package models.calculo;


import controllers.HomeController;
import models.tables.ActaBaja;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Calc_AjustesEP {
	
	public Long id_bodegaEmpresa;
	public Long id_tipoAjuste;  // 1 descuento 2 sobrecargo
	public Long id_tipoAjusteVenta; //1 arriendo 2 venta
	public Long id_moneda;
	public Double totalAjuste;
	
	public Double ajusteSobreArriendo;
	public Double ajusteSobreVenta;


	public Calc_AjustesEP(Long id_bodegaEmpresa, Long id_tipoAjuste, Long id_tipoAjusteVenta, Long id_moneda,
			Double totalAjuste, Double ajusteSobreArriendo, Double ajusteSobreVenta) {
		super();
		this.id_bodegaEmpresa = id_bodegaEmpresa;
		this.id_tipoAjuste = id_tipoAjuste;
		this.id_tipoAjusteVenta = id_tipoAjusteVenta;
		this.id_moneda = id_moneda;
		this.totalAjuste = totalAjuste;
		this.ajusteSobreArriendo = ajusteSobreArriendo;
		this.ajusteSobreVenta = ajusteSobreVenta;
	}



	public Calc_AjustesEP() {
		super();
	}

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);


	public static Map<Long,Calc_AjustesEP> mapResumenAjustePorBodega (List<Calc_AjustesEP> listaAjustes) {
		Map<Long,Calc_AjustesEP> map = new HashMap<Long,Calc_AjustesEP>();
		try{
			Long flagId_Bodega = (long) 0;
			Double ajusteArriendo = (double) 0, ajusteVenta = (double) 0;
			Long id_bodegaEmpresa = (long) 0;
			for(int i=0; i<listaAjustes.size(); i++) {
				if((flagId_Bodega - listaAjustes.get(i).id_bodegaEmpresa) != 0) {
					if(flagId_Bodega>0) {
						Calc_AjustesEP aux = new Calc_AjustesEP();
						aux.id_bodegaEmpresa = id_bodegaEmpresa;
						aux.ajusteSobreArriendo = ajusteArriendo;
						aux.ajusteSobreVenta = ajusteVenta;
						map.put(aux.id_bodegaEmpresa, aux);
					}
					ajusteArriendo = ajusteVenta = (double) 0;
					flagId_Bodega = listaAjustes.get(i).id_bodegaEmpresa;
				}
				id_bodegaEmpresa = listaAjustes.get(i).id_bodegaEmpresa;
				if(listaAjustes.get(i).id_tipoAjuste==1) {
					if(listaAjustes.get(i).id_tipoAjusteVenta==1) {
						ajusteArriendo = ajusteArriendo + listaAjustes.get(i).totalAjuste * -1;
					}else {
						ajusteVenta = ajusteVenta + listaAjustes.get(i).totalAjuste * -1;
					}
				}else {
					if(listaAjustes.get(i).id_tipoAjusteVenta==1) {
						ajusteArriendo = ajusteArriendo + listaAjustes.get(i).totalAjuste;
					}else {
						ajusteVenta = ajusteVenta + listaAjustes.get(i).totalAjuste;
					}
				}
				if(i == listaAjustes.size()-1) {
					Calc_AjustesEP aux = new Calc_AjustesEP();
					aux.id_bodegaEmpresa = id_bodegaEmpresa;
					aux.ajusteSobreArriendo = ajusteArriendo;
					aux.ajusteSobreVenta = ajusteVenta;
					map.put(aux.id_bodegaEmpresa, aux);
				}
			}
		} catch (Exception e) {
			String className = Calc_AjustesEP.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, "", e);
		}
		return map;
	}

	public static List<Calc_AjustesEP> listaAjustesEntreFechas (Connection con, String db, String desdeAAMMDD, String hastaAAMMDD){
		List<Calc_AjustesEP> lista = new ArrayList<Calc_AjustesEP>();
		String query = String.format(" select "
				+ " id_bodegaEmpresa, "
				+ " id_tipoAjuste, "
				+ " id_tipoAjusteVenta, "
				+ " id_moneda, "
				+ " totalAjuste "
				+ " from `%s`.ajustesEP "
				+ " where fechaAjuste between '"+desdeAAMMDD+"' and '"+hastaAAMMDD+"' "
				+ " order by id_bodegaEmpresa;", db);
		try (PreparedStatement smt = con.prepareStatement(query)){
			try(ResultSet rs = smt.executeQuery()) {
				while (rs.next()) {
					Calc_AjustesEP aux = new Calc_AjustesEP();
					aux.id_bodegaEmpresa = rs.getLong(1);
					aux.id_tipoAjuste = rs.getLong(2);
					aux.id_tipoAjusteVenta = rs.getLong(3);
					aux.id_moneda = rs.getLong(4);
					aux.totalAjuste = rs.getDouble(5);
					lista.add(aux);
				}
			}
		} catch (SQLException e) {
			String className = Calc_AjustesEP.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return lista;
	}
	

	
}
	
	
	
