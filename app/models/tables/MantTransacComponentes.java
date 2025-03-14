package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class MantTransacComponentes {
	public Long id_mantTransacReport;
	public Long id_mantComponente;
	public Double cantidad;

	public MantTransacComponentes(Long id_mantTransacReport, Long id_mantComponente, Double cantidad) {
		super();
		this.id_mantTransacReport = id_mantTransacReport;
		this.id_mantComponente = id_mantComponente;
		this.cantidad = cantidad;
	}

	public MantTransacComponentes() {super();}
	
	public Long getId_mantTransacReport() {
		return id_mantTransacReport;
	}

	public void setId_mantTransacReport(Long id_mantTransacReport) {
		this.id_mantTransacReport = id_mantTransacReport;
	}

	public Long getId_mantComponente() {
		return id_mantComponente;
	}

	public void setId_mantComponente(Long id_mantComponente) {
		this.id_mantComponente = id_mantComponente;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public static List<MantTransacComponentes> allPorIdMantTransacReport(Connection con, String db, Long id_mantTransacReport) {
		List<MantTransacComponentes> lista = new ArrayList<MantTransacComponentes>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select"
							+ " id_mantTransacReport,"
							+ " id_mantComponente, "
							+ " cantidad"
							+ " from `"+db+"`.mantTransacComponentes"
							+ " where id_mantTransacReport = ?;");
			smt.setLong(1, id_mantTransacReport);
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				lista.add(new MantTransacComponentes(rs.getLong(1),rs.getLong(2),rs.getDouble(3)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    		e.printStackTrace();
		}
		return (lista);
	}
	
	
	

}
