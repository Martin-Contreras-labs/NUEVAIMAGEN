package models.tables;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	static final Logger logger = LoggerFactory.getLogger(BodegaRedimensionar.class);


	public static Long find(Connection con, String db) {
		Long aux = null;
		String sql = "Select id_bodegaEmpresa from `" + db + "`.bodegaRedimensionar;";
		try (PreparedStatement smt = con.prepareStatement(sql);
			 ResultSet rs = smt.executeQuery()) {
			if (rs.next()) {
				aux = rs.getLong(1);
			}
		} catch (SQLException e) {
			String className = BodegaRedimensionar.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return aux;
	}

	public static boolean change(Connection con, String db, Long id_bodegaEmpresa) {
		boolean flag = false;
		String sqlTruncate = "truncate `" + db + "`.bodegaRedimensionar;";
		String sqlInsert = "insert into `" + db + "`.bodegaRedimensionar (id_bodegaEmpresa) values (?);";
		try (PreparedStatement smtTruncate = con.prepareStatement(sqlTruncate);
			 PreparedStatement smtInsert = con.prepareStatement(sqlInsert)) {
			smtTruncate.executeUpdate();
			smtInsert.setLong(1, id_bodegaEmpresa);
			smtInsert.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			String className = BodegaRedimensionar.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return flag;
	}
	

}
