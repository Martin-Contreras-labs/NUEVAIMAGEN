package models.utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Registro {

	public static void accesos(Connection con, String db, String user) {
		try {
			PreparedStatement smt = con
					.prepareStatement("insert into `"+db+"`.registroAccesos (usuario) values (?);");
			smt.setString(1,user.trim());
			smt.executeUpdate();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Long esMoroso(Connection con, String db) {
		Long esMoroso = (long) 0;
		try {
			PreparedStatement smt = con
					.prepareStatement("select moroso from empresa where baseDato=?;");
			smt.setString(1,db.trim());
			ResultSet rs = smt.executeQuery();
			if(rs.next()) {
				esMoroso = rs.getLong(1);
			}
			smt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(esMoroso);
	}
	
	public static boolean modificaciones(Connection con,String db,String id_usuario,String userName,String tabla,Long id_tabla,String accion,String descripcion) {
		boolean flag = true;
		try {
			PreparedStatement smt = con
					.prepareStatement("insert into  `"+db+"`.registroModificaciones " +
							" (id_user, userName, tabla, id_tabla, accion, descripcion)"
							+ " VALUES (?,?,?,?,?,?)");
			smt.setLong(1, Long.parseLong(id_usuario.trim()));
			smt.setString(2, userName.trim());
			smt.setString(3, tabla.trim());
			smt.setLong(4, id_tabla);
			smt.setString(5, accion.trim());
			smt.setString(6, descripcion.trim());
			smt.executeUpdate();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
			flag=false;
		}
		return (flag);
	}
	
	public static List<List<String>> historialRegistro(Connection con,String db) {
		List<List<String>> lista = new ArrayList<List<String>>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select fecha," +
							" userName,tabla,accion,descripcion, id_tabla" +
							" from `"+db+"`.registroModificaciones where fecha> DATE_SUB(NOW(),INTERVAL 180 DAY)" +
							" order by fecha desc;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				List<String> aux = new ArrayList<String>();
				aux.add(rs.getString(1));
				aux.add(rs.getString(2));
				aux.add(rs.getString(3));
				aux.add(rs.getString(4));
				aux.add(rs.getString(5));
				aux.add(rs.getString(6));
				lista.add(aux);
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	
}
