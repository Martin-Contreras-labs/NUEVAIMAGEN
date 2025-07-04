package models.tables;

import controllers.HomeController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Propiedad {
	public Long id;
	public String nombre;

	public Propiedad(Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public Propiedad() {super();}

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	public static Map<Long, Propiedad> mapAll(Connection con, String db) {
		Map<Long, Propiedad> map = new HashMap<>();
		String query = String.format("SELECT id,nombre FROM `%s`.actaBaja;", db);
		try (PreparedStatement smt = con.prepareStatement(query);
			 ResultSet rs = smt.executeQuery()){
			map.put((long)0, new Propiedad((long)0,"sin propiedad"));
			while (rs.next()) {
				map.put(rs.getLong(1), new Propiedad(rs.getLong(1),rs.getString(2)));
			}
		} catch (SQLException e) {
			String className = Propiedad.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (map);
	}
	
	public static Map<String, Propiedad> mapAllPorNombre(Connection con, String db) {
		Map<String, Propiedad> map = new HashMap<>();
		String query = String.format("SELECT id,nombre FROM `%s`.actaBaja;", db);
		try (PreparedStatement smt = con.prepareStatement(query);
			 ResultSet rs = smt.executeQuery()){
			map.put("sin propiedad", new Propiedad((long)0,"sin propiedad"));
			while (rs.next()) {
				map.put(rs.getString(2).toUpperCase(), new Propiedad(rs.getLong(1),rs.getString(2)));
			}
		} catch (SQLException e) {
			String className = Propiedad.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (map);
	}
	
	public static List<Propiedad> all(Connection con, String db) {
		List<Propiedad> lista = new ArrayList<Propiedad>();
		String query = String.format("SELECT id,nombre FROM `%s`.propiedad;", db);
		try (PreparedStatement smt = con.prepareStatement(query);
			 ResultSet rs = smt.executeQuery()){
			while (rs.next()) {
				lista.add(new Propiedad(rs.getLong(1),rs.getString(2)));
			}
		} catch (SQLException e) {
			String className = Propiedad.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (lista);
	}

	public static boolean estaEnUso(Connection con, String db, Long id) {
		boolean flag = false;
		String query = String.format("SELECT id FROM `%s`.equipo WHERE id_propiedad = ?;", db);
		try (PreparedStatement smt = con.prepareStatement(query)){
			smt.setLong(1, id);
			try (ResultSet rs = smt.executeQuery()){
				if (rs.next()) {
					flag = true;
				}
			}
		} catch (SQLException e) {
			String className = Propiedad.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (flag);
	}

	public static boolean existe(Connection con, String db, String nombre) {
		boolean flag = false;
		String query = String.format("SELECT id FROM `%s`.propiedad WHERE UPPER(nombre) = ?;", db);
		try (PreparedStatement smt = con.prepareStatement(query)){
			smt.setString(1, nombre.toUpperCase());
			try (ResultSet rs = smt.executeQuery()){
				if (rs.next()) {
					flag=true;
				}
			}
		} catch (SQLException e) {
			String className = Propiedad.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (flag);
	}
	
	public static boolean delete(Connection con, String db, Long id) {
		boolean flag = false;
		String query = String.format("DELETE FROM `%s`.propiedad WHERE id = ?;", db);
		try (PreparedStatement smt = con.prepareStatement(query)){
			smt.setLong(1, id);
			smt.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			String className = Propiedad.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (flag);
	}
	
	public static Propiedad find(Connection con, String db, Long id) {
		Propiedad aux = null;
		String query = String.format("SELECT id,nombre FROM `%s`.propiedad WHERE id = ?;", db);
		try (PreparedStatement smt = con.prepareStatement(query)){
			smt.setLong(1, id);
			try (ResultSet rs = smt.executeQuery()){
				if (rs.next()) {
					aux = new Propiedad(rs.getLong(1),rs.getString(2));
				}
			}
		} catch (SQLException e) {
			String className = Propiedad.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (aux);
	}
	
	public static boolean create(Connection con,String db, String nombrePropiedad) {
		boolean flag = false;
		String query = String.format("INSERT INTO `%s`.propiedad (nombre) VALUES (?);", db);
		try (PreparedStatement smt = con.prepareStatement(query)){
			smt.setString(1, nombrePropiedad.trim());
			smt.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			String className = Propiedad.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (flag);
	}
	
	public static boolean modificaPorCampo(Connection con,String db, String campo, Long id_propiedad, String valor) {
		boolean flag = false;
		String query = "update `"+db+"`.propiedad set `"+campo+"` = ? WHERE id = ?;";
		try (PreparedStatement smt = con.prepareStatement(query)) {
				smt.setString(1, valor.trim());
				smt.setLong(2, id_propiedad);
				int rowsAffected = smt.executeUpdate();
				if (rowsAffected > 0) {
	                flag = true;
	            }
		} catch (SQLException e) {
			String className = Propiedad.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (flag);
	}

	


}
