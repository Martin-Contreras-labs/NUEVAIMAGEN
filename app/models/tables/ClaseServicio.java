package models.tables;

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



public class ClaseServicio {
	public Long id;
	public String nombre;

	public ClaseServicio(Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public ClaseServicio() {super();}

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}

	static final Logger logger = LoggerFactory.getLogger(BodegaRedimensionar.class);

	public static Map<Long, ClaseServicio> mapAll(Connection con, String db) {
		Map<Long, ClaseServicio> map = new HashMap<>();
		try (PreparedStatement smt = con.prepareStatement("select id,nombre from `" + db + "`.claseServicio");
			 ResultSet rs = smt.executeQuery()) {
			while (rs.next()) {
				map.put(rs.getLong(1), new ClaseServicio(rs.getLong(1), rs.getString(2)));
			}
		} catch (SQLException e) {
			String className = AjustesEP.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return map;
	}


	public static List<ClaseServicio> all(Connection con, String db) {
		List<ClaseServicio> lista = new ArrayList<>();
		try (PreparedStatement smt = con.prepareStatement("select id,nombre from `" + db + "`.claseServicio order by nombre");
			 ResultSet resultado = smt.executeQuery()) {
			while (resultado.next()) {
				lista.add(new ClaseServicio(resultado.getLong(1), resultado.getString(2)));
			}
		} catch (SQLException e) {
			String className = AjustesEP.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return lista;
	}


	public static List<ClaseServicio> allFiltroPorNombre(Connection con, String db, String filtroPorNombreClase) {
		List<ClaseServicio> lista = new ArrayList<>();
		if (filtroPorNombreClase.length() > 0) {
			String filtro = " where nombre in (" + filtroPorNombreClase + ") ";
			String sql = "select id,nombre from `" + db + "`.claseServicio " + filtro + " order by nombre";
			try (PreparedStatement smt = con.prepareStatement(sql);
				 ResultSet resultado = smt.executeQuery()) {
				while (resultado.next()) {
					lista.add(new ClaseServicio(resultado.getLong(1), resultado.getString(2)));
				}
			} catch (SQLException e) {
				String className = AjustesEP.class.getSimpleName();
				String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
			}
		}
		return lista;
	}


	public static List<ClaseServicio> allFiltroPorId(Connection con, String db, String filtroPorIdClase) {
		List<ClaseServicio> lista = new ArrayList<>();
		if (filtroPorIdClase.length() > 0) {
			String filtro = " where id in (" + filtroPorIdClase + ") ";
			String sql = "select id,nombre from `" + db + "`.claseServicio " + filtro + " order by nombre";
			try (PreparedStatement smt = con.prepareStatement(sql);
				 ResultSet resultado = smt.executeQuery()) {
				while (resultado.next()) {
					lista.add(new ClaseServicio(resultado.getLong(1), resultado.getString(2)));
				}
			} catch (SQLException e) {
				String className = AjustesEP.class.getSimpleName();
				String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
			}
		}
		return lista;
	}



	public static boolean estaEnUso(Connection con, String db, Long id_clase) {
		boolean flag = false;
		String sql = "Select * from `" + db + "`.servicio WHERE id_claseServicio = ?";
		try (PreparedStatement smt1 = con.prepareStatement(sql)) {
			smt1.setLong(1, id_clase);
			try (ResultSet rs1 = smt1.executeQuery()) {
				if (rs1.next()) {
					flag = true;
				}
			}
		} catch (SQLException e) {
			String className = AjustesEP.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return flag;
	}


	public static boolean delete(Connection con, String db, Long id_clase) {
		boolean flag = false;
		String sql = "delete from `" + db + "`.claseServicio WHERE id = ?";
		try (PreparedStatement smt = con.prepareStatement(sql)) {
			smt.setLong(1, id_clase);
			smt.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			String className = AjustesEP.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return flag;
	}


	public static ClaseServicio find(Connection con, String db, Long id_clase) {
		ClaseServicio aux = new ClaseServicio();
		String sql = "select id,nombre from `" + db + "`.claseServicio WHERE id = ?";
		try (PreparedStatement smt = con.prepareStatement(sql)) {
			smt.setLong(1, id_clase);
			try (ResultSet resultado = smt.executeQuery()) {
				if (resultado.next()) {
					aux = new ClaseServicio(resultado.getLong(1), resultado.getString(2));
				}
			}
		} catch (SQLException e) {
			String className = AjustesEP.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return aux;
	}


	public static boolean existeClase(Connection con, String db, String nombre_clase) {
		boolean flag = false;
		String sql = "select id,nombre from `" + db + "`.claseServicio WHERE upper(nombre) = ?";
		try (PreparedStatement smt = con.prepareStatement(sql)) {
			smt.setString(1, nombre_clase.toUpperCase());
			try (ResultSet resultado = smt.executeQuery()) {
				if (resultado.next()) {
					flag = true;
				}
			}
		} catch (SQLException e) {
			String className = AjustesEP.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return flag;
	}

	public static boolean create(Connection con, String db, String nombre_clase) {
		boolean flag = false;
		String sql = "insert into `" + db + "`.claseServicio (nombre) values (?)";
		try (PreparedStatement smt = con.prepareStatement(sql)) {
			smt.setString(1, nombre_clase.trim());
			smt.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			String className = AjustesEP.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return flag;
	}

	public static boolean modificaPorCampo(Connection con, String db, String campo, Long id_clase, String valor) {
		boolean flag = false;
		String sql = "update `" + db + "`.claseServicio set `" + campo + "` = ? WHERE id = ?";
		try (PreparedStatement smt = con.prepareStatement(sql)) {
			smt.setString(1, valor.trim());
			smt.setLong(2, id_clase);
			smt.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			String className = AjustesEP.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return flag;
	}


	public static List<ClaseServicio> allConServicios(Connection con, String db) {
		List<ClaseServicio> lista = new ArrayList<>();
		String sql = "select distinct servicio.id_claseServicio, claseServicio.nombre " +
				"from `" + db + "`.servicio " +
				"left join `" + db + "`.claseServicio on claseServicio.id = servicio.id_claseServicio " +
				"order by claseServicio.nombre;";
		try (PreparedStatement smt = con.prepareStatement(sql);
			 ResultSet resultado = smt.executeQuery()) {
			while (resultado.next()) {
				lista.add(new ClaseServicio(resultado.getLong(1), resultado.getString(2)));
			}
		} catch (SQLException e) {
			String className = AjustesEP.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return lista;
	}

	public static Long findIdXnombre(Connection con, String db, String nombre) {
		Long aux = 0L;
		String sql = "select id from `" + db + "`.claseServicio where upper(nombre) = upper(?)";
		try (PreparedStatement smt = con.prepareStatement(sql)) {
			smt.setString(1, nombre);
			try (ResultSet resultado = smt.executeQuery()) {
				if (resultado.next()) {
					aux = resultado.getLong(1);
				}
			}
		} catch (SQLException e) {
			String className = AjustesEP.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return aux;
	}



}
