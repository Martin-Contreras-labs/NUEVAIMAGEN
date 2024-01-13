package models.reports;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;


import models.tables.TasasCambio;


public class ReportCotiOdo {
	
	public Long id_cotiOdo;
	public Long id_moneda;
	public Double valorizado;
	public Long unidades;
	public String estado;
	public Long confirmada;
	public Long id_otOdo;
	public String fechaCoti;
	public String fechaOt;
	
	public ReportCotiOdo(Long id_cotiOdo, Long id_moneda, Double valorizado, Long unidades, String estado,
			Long confirmada, Long id_ot, String fechaCoti, String fechaOt) {
		super();
		this.id_cotiOdo = id_cotiOdo;
		this.id_moneda = id_moneda;
		this.valorizado = valorizado;
		this.unidades = unidades;
		this.estado = estado;
		this.confirmada = confirmada;
		this.id_otOdo = id_ot;
		this.fechaCoti = fechaCoti;
		this.fechaOt = fechaOt;
	}
	
	public ReportCotiOdo() {
		super();
	}

	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	
	
	public static Map<String,Double> calculoCotiOdo(Connection con, String db, String desdeAAMMDD, String hastaAAMMDD, String pais) {
		Map<String,TasasCambio> mapAllTasas = TasasCambio.mapTasasporAllFecha(con, db, pais);
		Map<String,Double> resultado = new HashMap<String,Double>();
		try {
			
			PreparedStatement smt1 = con
					.prepareStatement(" select sum(cotiOdoDetalle.precio*cotiOdoDetalle.cantidad)*(1-cotiOdo.dctoOdo) as valorizado, "
							+ "	cotiOdo.confirmada, cotiOdoDetalle.id_moneda, cotiOdo.fecha, cotizaEstado.estado "
							+ "	from `"+db+"`.cotiOdoDetalle "
							+ "	left join  `"+db+"`.cotiOdo on cotiOdo.id = cotiOdoDetalle.id_cotiOdo "
							+ "	left join  `"+db+"`.cotizaEstado on cotizaEstado.id = cotiOdo.id_cotizaEstado "
							+ "	where cotiOdo.fecha between ? and ? "
							+ "	group by cotiOdo.confirmada, cotiOdoDetalle.id_moneda, cotiOdo.fecha, cotizaEstado.estado;");
			smt1.setString(1, desdeAAMMDD);
			smt1.setString(2, hastaAAMMDD);
			ResultSet rsValConfNoConf = smt1.executeQuery();
			Double valConfirmadas = (double)0;
			Double valNoConfirmadas = (double)0;
			while(rsValConfNoConf.next()) {
				Double valorTasa = (double)1;
				TasasCambio tasasCambio = mapAllTasas.get(rsValConfNoConf.getString(4));
				Map<Long,Double> tasas = new HashMap<Long,Double>();  // id_moneda, valor tasa
				if(tasasCambio!=null) {
					tasas.put((long)1, (double)1);
					tasas.put((long)2, Double.parseDouble(tasasCambio.getClpUsd().replaceAll(",", "")));
					tasas.put((long)3, Double.parseDouble(tasasCambio.getClpEur().replaceAll(",", "")));
					tasas.put((long)4, Double.parseDouble(tasasCambio.getClpUf().replaceAll(",", "")));
					valorTasa = tasas.get(rsValConfNoConf.getLong(3));
					if(valorTasa == null) {
						valorTasa = (double)1;
					}
				}
				
				if(rsValConfNoConf.getString(2).equals("1")) {
					valConfirmadas += rsValConfNoConf.getDouble(1) * valorTasa;
				}else {
					valNoConfirmadas += rsValConfNoConf.getDouble(1)*valorTasa;
					Double valor = resultado.get("val_"+rsValConfNoConf.getString(5));
					if(valor == null) {
						resultado.put("val_"+rsValConfNoConf.getString(5), rsValConfNoConf.getDouble(1));
					}else {
						resultado.put("val_"+rsValConfNoConf.getString(5), (valor + rsValConfNoConf.getDouble(1)*valorTasa));
					}
				}
			}
			resultado.put("val_NoConfirmadas", valNoConfirmadas);
			resultado.put("val_Confirmadas", valConfirmadas);
			smt1.close();
			rsValConfNoConf.close();
			
			
			PreparedStatement smt3 = con
					.prepareStatement(" select count(cotiOdo.id), confirmada, cotizaEstado.estado "
							+ " from `"+db+"`.cotiOdo "
							+ "	left join  `"+db+"`.cotizaEstado on cotizaEstado.id = cotiOdo.id_cotizaEstado "
							+ " where cotiOdo.fecha between ? and ? "
							+ " group by confirmada, cotizaEstado.estado;");
			smt3.setString(1, desdeAAMMDD);
			smt3.setString(2, hastaAAMMDD);
			ResultSet rsCantConNoConf = smt3.executeQuery();
			Double cantConfirmadas = (double)0;
			Double cantNoConfirmadas = (double)0;
			while(rsCantConNoConf.next()) {
				if(rsCantConNoConf.getString(2).equals("1")) {
					cantConfirmadas += rsCantConNoConf.getDouble(1);
				}else {
					cantNoConfirmadas += rsCantConNoConf.getDouble(1);
					Double valor = resultado.get("cant_"+rsCantConNoConf.getString(3));
					if(valor == null) {
						resultado.put("cant_"+rsCantConNoConf.getString(3), rsCantConNoConf.getDouble(1));
					}else {
						resultado.put("cant_"+rsCantConNoConf.getString(3), (valor + rsCantConNoConf.getDouble(1)));
					}
					
				}
			}
			resultado.put("cant_NoConfirmadas", cantNoConfirmadas);
			resultado.put("cant_Confirmadas", cantConfirmadas);
			smt3.close();
			rsCantConNoConf.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return (resultado);
	}
	
	public static Map<String,Double>  calculoOtOdo(Connection con, String db, String desdeAAMMDD, String hastaAAMMDD, String pais) {
		
		Map<String,TasasCambio> mapAllTasas = TasasCambio.mapTasasporAllFecha(con, db, pais);
		Map<String,Double> resultado = new HashMap<String,Double>();
		try {
			
			PreparedStatement smt1 = con
					.prepareStatement(" select sum(cotiOdoDetalle.precio*cotiOdoDetalle.cantidad)*(1-cotiOdo.dctoOdo) as valorizado, "
							+ "	if(cotiOdo.id_otOdo>0,1,0) as conOt, cotiOdoDetalle.id_moneda, cotiOdo.fecha, otEstado.estado "
							+ "	from `"+db+"`.cotiOdoDetalle "
							+ "	left join  `"+db+"`.cotiOdo on cotiOdo.id = cotiOdoDetalle.id_cotiOdo "
							+ "	left join  `"+db+"`.otOdo on otOdo.id_cotiOdo = cotiOdoDetalle.id_cotiOdo "
							+ "	left join  `"+db+"`.otEstado on otEstado.id = otOdo.id_otEstado "
							+ "	where cotiOdo.fecha between ? and ? and cotiOdo.confirmada = 1 "
							+ "	group by conOt, cotiOdoDetalle.id_moneda, cotiOdo.fecha, otEstado.estado;");
			
			smt1.setString(1, desdeAAMMDD);
			smt1.setString(2, hastaAAMMDD);
			
			ResultSet rsValConOtSinOt = smt1.executeQuery();
			Double valConOt = (double)0;
			Double valSinOt = (double)0;
			while(rsValConOtSinOt.next()) {
				Double valorTasa = (double)1;
				TasasCambio tasasCambio = mapAllTasas.get(rsValConOtSinOt.getString(4));
				Map<Long,Double> tasas = new HashMap<Long,Double>();  // id_moneda, valor tasa
				if(tasasCambio!=null) {
					tasas.put((long)1, (double)1);
					tasas.put((long)2, Double.parseDouble(tasasCambio.getClpUsd().replaceAll(",", "")));
					tasas.put((long)3, Double.parseDouble(tasasCambio.getClpEur().replaceAll(",", "")));
					tasas.put((long)4, Double.parseDouble(tasasCambio.getClpUf().replaceAll(",", "")));
					valorTasa = tasas.get(rsValConOtSinOt.getLong(3));
					if(valorTasa == null) {
						valorTasa = (double)1;
					}
				}
				
				if(rsValConOtSinOt.getString(2).equals("1")) {
					valConOt += rsValConOtSinOt.getDouble(1) * valorTasa;
					Double valor = resultado.get("val_"+rsValConOtSinOt.getString(5));
					if(valor == null) {
						resultado.put("val_"+rsValConOtSinOt.getString(5), rsValConOtSinOt.getDouble(1));
					}else {
						resultado.put("val_"+rsValConOtSinOt.getString(5), (valor + rsValConOtSinOt.getDouble(1)*valorTasa));
					}
				}else {
					valSinOt += rsValConOtSinOt.getDouble(1)*valorTasa;
				}
			}
			resultado.put("val_SinOt", valSinOt);
			resultado.put("val_ConOt", valConOt);
			smt1.close();
			rsValConOtSinOt.close();
			
			
			PreparedStatement smt3 = con
					.prepareStatement(" select count(cotiOdo.id), if(cotiOdo.id_otOdo>0,1,0) as conOt, cotizaEstado.estado "
							+ " from `"+db+"`.cotiOdo "
							+ "	left join  `"+db+"`.cotizaEstado on cotizaEstado.id = cotiOdo.id_cotizaEstado "
							+ " where cotiOdo.fecha between ? and ? "
							+ " group by conOt, cotizaEstado.estado;");
			
			smt3.setString(1, desdeAAMMDD);
			smt3.setString(2, hastaAAMMDD);
			ResultSet rsCantConOtSinOt = smt3.executeQuery();
			Double cantConOt = (double)0;
			Double cantSinOt = (double)0;
			while(rsCantConOtSinOt.next()) {
				if(rsCantConOtSinOt.getString(2).equals("1")) {
					cantConOt += rsCantConOtSinOt.getDouble(1);
					Double valor = resultado.get("cant_"+rsCantConOtSinOt.getString(3));
					if(valor == null) {
						resultado.put("cant_"+rsCantConOtSinOt.getString(3), rsCantConOtSinOt.getDouble(1));
					}else {
						resultado.put("cant_"+rsCantConOtSinOt.getString(3), (valor + rsCantConOtSinOt.getDouble(1)));
					}
				}else {
					cantSinOt += rsCantConOtSinOt.getDouble(1);
				}
			}
			resultado.put("cant_SinOt", cantSinOt);
			resultado.put("cant_ConOt", cantConOt);
			smt3.close();
			rsCantConOtSinOt.close();
			
			
			PreparedStatement smt4 = con
					.prepareStatement(" select count(otOdo.id), otEstado.estado "
							+ "	from `"+db+"`.otOdo "
							+ "	left join  `"+db+"`.otEstado on otEstado.id = otOdo.id_otEstado "
							+ "	where otOdo.id in "
							+ " 	(select cotiOdo.id_otOdo "
							+ "		from `"+db+"`.cotiOdo "
							+ "		where cotiOdo.fecha between ? and ? and id_otOdo>0) "
							+ "	group by otEstado.estado;");
			
			smt4.setString(1, desdeAAMMDD);
			smt4.setString(2, hastaAAMMDD);
			
			ResultSet rs4 = smt4.executeQuery();
			while(rs4.next()) {
				resultado.put("cant_"+rs4.getString(2), rs4.getDouble(1));
			}
			smt4.close();
			rs4.close();
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return (resultado);
		
	}
		
			
}
	
	
	
