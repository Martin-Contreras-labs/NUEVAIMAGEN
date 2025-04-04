package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BodegaRedimensionar{

	public Long id_bodegaEmpresa;

	public BodegaRedimensionar(Long id_bodegaEmpresa) {
		super();
		this.id_bodegaEmpresa = id_bodegaEmpresa;
	}
	
	public BodegaRedimensionar() {
		super();
	}

	public Long getId_bodegaEmpresa() {return id_bodegaEmpresa;}
	public void setId_bodegaEmpresa(Long id_bodegaEmpresa) {this.id_bodegaEmpresa = id_bodegaEmpresa;}


	
	public static Long find(Connection con, String db) {
		Long aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement("Select id_bodegaEmpresa from `"+db+"`.bodegaRedimensionar;");
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				aux = rs.getLong(1);
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}
	
	public static boolean change(Connection con, String db, Long id_bodegaEmpresa) {	
		boolean flag = false;
		try {
			PreparedStatement smt2 = con
					.prepareStatement("truncate `"+db+"`.bodegaRedimensionar;");
			smt2.executeUpdate();
			smt2.close();
			PreparedStatement smt = con
					.prepareStatement("insert into `"+db+"`.bodegaRedimensionar (id_bodegaEmpresa) values (?);");		
			smt.setLong(1, id_bodegaEmpresa);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	

}
