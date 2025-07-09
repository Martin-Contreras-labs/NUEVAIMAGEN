package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import models.utilities.Fechas;

public class TasasCambio {
	public String fecha;
	public String clpUf;
	public String clpUsd;
	public String clpEur;

	public TasasCambio(String fecha, String clpUf, String clpUsd, String clpEur) {
		super();
		this.fecha = fecha;
		this.clpUf = clpUf;
		this.clpUsd = clpUsd;
		this.clpEur = clpEur;
	}

	public TasasCambio() {super();}

	

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getClpUf() {
		return clpUf;
	}

	public void setClpUf(String clpUf) {
		this.clpUf = clpUf;
	}

	public String getClpUsd() {
		return clpUsd;
	}

	public void setClpUsd(String clpUsd) {
		this.clpUsd = clpUsd;
	}

	public String getClpEur() {
		return clpEur;
	}

	public void setClpEur(String clpEur) {
		this.clpEur = clpEur;
	}


	static DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00",symbols);
	
	public static TasasCambio allDeUnaFecha(Connection con, String db, String pais, String fechaAAMMDD) {
		TasasCambio indices = new TasasCambio();
		if(pais.equals("CHILE")){
			String CLPUF = "1";
			String CLPUSD= "1";
			String CLPEUR= "1";
			if(fechaAAMMDD.equals("")) {
				Fechas hoy = Fechas.hoy();
				fechaAAMMDD = hoy.getFechaStrAAMMDD();
			}
			try {
				PreparedStatement smt2 = con
						.prepareStatement(" select UF,USD,EUR from tasasMoneda.tasas where fecha = ?;");
				smt2.setString(1,fechaAAMMDD);
				ResultSet rs2 = smt2.executeQuery();
				if (rs2.next()) {
					CLPUF = myformatdouble2.format(rs2.getDouble(1));
					CLPUSD= myformatdouble2.format(rs2.getDouble(2));
					CLPEUR= myformatdouble2.format(rs2.getDouble(3));
				}
				rs2.close();
				smt2.close();
			} catch (SQLException e) {
	    			e.printStackTrace();
			}
			indices.setFecha(fechaAAMMDD);
			indices.setClpUf(CLPUF);
			indices.setClpUsd(CLPUSD);
			indices.setClpEur(CLPEUR);
		}else {
			indices.setFecha(fechaAAMMDD);
			indices.setClpUf("1");
			indices.setClpUsd("1");
			indices.setClpEur("1");
		}
		return(indices);
	}
	
	public static TasasCambio allDeHoy(Connection con, String db, String pais) {
		TasasCambio indices = new TasasCambio();
		Fechas hoy = Fechas.hoy();
		if(pais.equals("CHILE")){
			String CLPUF = "1";
			String CLPUSD= "1";
			String CLPEUR= "1";
			try {
				PreparedStatement smt2 = con
						.prepareStatement(" select UF,USD,EUR from tasasMoneda.tasas where fecha = ?;");
				smt2.setString(1,hoy.getFechaStrAAMMDD());
				ResultSet rs2 = smt2.executeQuery();
				if (rs2.next()) {
					CLPUF = myformatdouble2.format(rs2.getDouble(1));
					CLPUSD= myformatdouble2.format(rs2.getDouble(2));
					CLPEUR= myformatdouble2.format(rs2.getDouble(3));
				}
				rs2.close();
				smt2.close();
			} catch (SQLException e) {
	    			e.printStackTrace();
			}
			indices.setFecha(hoy.getFechaStrAAMMDD());
			indices.setClpUf(CLPUF);
			indices.setClpUsd(CLPUSD);
			indices.setClpEur(CLPEUR);
		}else {
			indices.setFecha(hoy.getFechaStrAAMMDD());
			indices.setClpUf("1");
			indices.setClpUsd("1");
			indices.setClpEur("1");
		}
		return(indices);
	}
	
	public static Map<Long,Double> mapTasasPorFecha(Connection con, String db, String fechaAAMMDD, String pais) {
		Map<Long,Double> tasas = new HashMap<Long,Double>();
		tasas.put((long)1,(double)1); // 'Peso Chileno', 'CLP', '0'
		tasas.put((long)2,(double)1);
		tasas.put((long)3,(double)1);
		tasas.put((long)4,(double)1);
		if(pais.equals("CHILE")){
			try {
				PreparedStatement smt2 = con
						.prepareStatement(" select UF,USD,EUR from tasasMoneda.tasas where fecha = ?;");
				smt2.setString(1, fechaAAMMDD);
				ResultSet rs2 = smt2.executeQuery();
				if (rs2.next()) {
					tasas.put((long)2,rs2.getDouble(2));	// el id 2 es usd
					tasas.put((long)3,rs2.getDouble(3));	// el id 3 es eur
					tasas.put((long)4,rs2.getDouble(1));	// el id 4 es uf
				}
				rs2.close();
				smt2.close();
			} catch (SQLException e) {
	    			e.printStackTrace();
			}
		}
		return(tasas);
	}
	
	public static Map<String,TasasCambio> mapTasasporAllFecha(Connection con, String db, String pais) {
		Map<String,TasasCambio> map = new HashMap<String,TasasCambio>();
		try {
			PreparedStatement smt2 = con
					.prepareStatement(" select UF,USD,EUR,fecha from tasasMoneda.tasas;");
			ResultSet rs2 = smt2.executeQuery();
			while (rs2.next()) {
				TasasCambio aux = new TasasCambio();
				if(pais.equals("CHILE")){
					aux.setFecha(rs2.getString(4));
					aux.setClpUf(rs2.getString(1));
					aux.setClpUsd(rs2.getString(2));
					aux.setClpEur(rs2.getString(3));
				}else {
					aux.setFecha(rs2.getString(4));
					aux.setClpUf("1");
					aux.setClpUsd("1");
					aux.setClpEur("1");
				}
				map.put(rs2.getString(4), aux);
			}
			rs2.close();
			smt2.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return(map);
	}
	
	public static Map<Long,Double> auxiliarMapTasasPorFecha(TasasCambio tasa, String pais) {
		Map<Long,Double> tasas = new HashMap<Long,Double>();
		tasas.put((long)1,(double)1); // 'Peso Chileno', 'CLP', '0'
		tasas.put((long)2,(double)1);
		tasas.put((long)3,(double)1);
		tasas.put((long)4,(double)1);
		if(pais.equals("CHILE")){
			if(tasa!=null) {
				tasas.put((long)2,Double.parseDouble(tasa.getClpUsd()));	// el id 2 es usd
				tasas.put((long)3,Double.parseDouble(tasa.getClpEur()));	// el id 3 es eur
				tasas.put((long)4,Double.parseDouble(tasa.getClpUf()));		// el id 4 es uf
			}
		}
		return(tasas);
	}

	
}




