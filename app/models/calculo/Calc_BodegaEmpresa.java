package models.calculo;


import controllers.HomeController;
import models.tables.ActaBaja;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Calc_BodegaEmpresa {
	
	public Long id;
	public Long esInterna;
	public String nombre;
	public Long vigente;
	public Long id_cliente;
	public Long id_proyecto;
	public Double tasaDescto;
	public Double tasaArriendo;
	public Double tasaCfi;
	public Long cobraDiaDespacho;
	public Long nDiaGraciaEnvio;
	public Long nDiaGraciaRegreso;
	public Double factorM2Viga;
	public Long baseCalculo;
	public Long tratoDevoluciones;
	public String comercial;

	public Calc_BodegaEmpresa(Long id, Long esInterna, String nombre, Long vigente, Long id_cliente, Long id_proyecto,
			Double tasaDescto, Double tasaArriendo, Double tasaCfi, Long cobraDiaDespacho, Long nDiaGraciaEnvio,
			Long nDiaGraciaRegreso, Double factorM2Viga, Long baseCalculo, Long tratoDevoluciones, String comercial) {
		super();
		this.id = id;
		this.esInterna = esInterna;
		this.nombre = nombre;
		this.vigente = vigente;
		this.id_cliente = id_cliente;
		this.id_proyecto = id_proyecto;
		this.tasaDescto = tasaDescto;
		this.tasaArriendo = tasaArriendo;
		this.tasaCfi = tasaCfi;
		this.cobraDiaDespacho = cobraDiaDespacho;
		this.nDiaGraciaEnvio = nDiaGraciaEnvio;
		this.nDiaGraciaRegreso = nDiaGraciaRegreso;
		this.factorM2Viga = factorM2Viga;
		this.baseCalculo = baseCalculo;
		this.tratoDevoluciones = tratoDevoluciones;
		this.comercial = comercial;
	}



	public Calc_BodegaEmpresa() {
		super();
	}

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	public static Map<Long,Calc_BodegaEmpresa> mapAllBodegasVigentes(Connection con, String db) {
		Map<Long,Calc_BodegaEmpresa> map = new HashMap<Long,Calc_BodegaEmpresa>();
		String query = String.format(" select "
				+ " id,"
				+ " esInterna,"
				+ " nombre, "
				+ " vigente,"
				+ " id_cliente,"
				+ " id_proyecto,"
				+ " tasaDescto,"
				+ " tasaArriendo,"
				+ " tasaCfi,"
				+ " cobraDiaDespacho,"
				+ " nDiaGraciaEnvio,"
				+ " nDiaGraciaRegreso,"
				+ " factorM2Viga,"
				+ " baseCalculo,"
				+ " tratoDevoluciones,"
				+ " comercial "
				+ " from `%s`.bodegaEmpresa "
				+ " where bodegaEmpresa.vigente = 1;",db);
		try (PreparedStatement smt = con.prepareStatement(query)) {
			try (ResultSet rs = smt.executeQuery()) {
				while (rs.next()) {
					Calc_BodegaEmpresa aux = new Calc_BodegaEmpresa();
					aux.id = rs.getLong(1);
					aux.esInterna = rs.getLong(2);
					aux.nombre = rs.getString(3);
					aux.vigente = rs.getLong(4);
					aux.id_cliente = rs.getLong(5);
					aux.id_proyecto = rs.getLong(6);
					aux.tasaDescto = rs.getDouble(7);
					aux.tasaArriendo = rs.getDouble(8);
					aux.tasaCfi = rs.getDouble(9);
					aux.cobraDiaDespacho = rs.getLong(10);
					aux.nDiaGraciaEnvio = rs.getLong(11);
					aux.nDiaGraciaRegreso = rs.getLong(12);
					aux.factorM2Viga = rs.getDouble(13);
					aux.baseCalculo = rs.getLong(14);
					aux.tratoDevoluciones = rs.getLong(15);
					aux.comercial = rs.getString(16);
					map.put(rs.getLong(1), aux);
				}
			}
		} catch (SQLException e) {
			String className = ActaBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (map);
	}
	
	
	
	
	
	
	
	
	
	
	
}
	
	
	
