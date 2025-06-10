package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controllers.HomeController;
import models.forms.FormBaja;

public class ActaBaja {
	public Long id;
	public Long numero;
	public String fecha;
	public String actaBajaPDF;
	public String observaciones;

	public ActaBaja(Long id, Long numero, String fecha, String actaBajaPDF, String observaciones) {
		super();
		this.id = id;
		this.numero = numero;
		this.fecha = fecha;
		this.actaBajaPDF = actaBajaPDF;
		this.observaciones = observaciones;
	}

	public ActaBaja(FormBaja form) {
		super();
		this.id = (long)0;
		this.numero = form.numero;
		this.fecha = form.fecha;
		this.actaBajaPDF = "";
		this.observaciones = form.observaciones;
	}

	public ActaBaja() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getActaBajaPDF() {
		return actaBajaPDF;
	}

	public void setActaBajaPDF(String actaBajaPDF) {
		this.actaBajaPDF = actaBajaPDF;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	static SimpleDateFormat myformatfecha = new SimpleDateFormat("dd-MM-yyyy");

	public static boolean modifyXCampo(Connection con, String db, String campo, String valor, Long id_actaBaja) {
		boolean flag = false;
		String query = String.format("UPDATE `%s`.actaBaja SET `%s` = ? WHERE id = ?", db, campo);
		try (PreparedStatement smt = con.prepareStatement(query)) {
			smt.setString(1, valor.trim());
			smt.setLong(2, id_actaBaja);
			smt.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			String className = ActaBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (flag);
	}

	public static Long findNuevoNumero(Connection con, String db) {
		Long nuevoNumero = 1L;
		String query = "SELECT COALESCE(MAX(numero), 1) + 1 FROM `" + db + "`.actaBaja;";
		try (PreparedStatement smt = con.prepareStatement(query)) {
			try (ResultSet rs = smt.executeQuery()) {
				if (rs.next()) {
					nuevoNumero = rs.getLong(1);
				}
			}
		} catch (SQLException e) {
			String className = ActaBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (nuevoNumero > 0 ? nuevoNumero : 1L);
	}

	public static boolean existeNumero (Connection con, String db, Long numero) {
		boolean aux = false;
		String query = "select numero from `"+db+"`.actaBaja where numero = ?;";
		try (PreparedStatement smt = con.prepareStatement(query)) {
			smt.setLong(1, numero);
			try(ResultSet resultado = smt.executeQuery()){
				if (resultado.next()) {
					aux = true;
				}
			}
		} catch (SQLException e) {
			String className = ActaBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (aux);
	}

	public static Long findIdActaBaja (Connection con, String db, Long numero) {
		Long aux = (long)0;
		String query = "select id from `"+db+"`.actaBaja where numero = ?;";
		try (PreparedStatement smt = con.prepareStatement(query)) {
			smt.setLong(1, numero);
			try(ResultSet rs = smt.executeQuery()){
				if (rs.next()) {
					aux = rs.getLong(1);
				}
			}
		} catch (SQLException e) {
			String className = ActaBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (aux);
	}

	public static Long create(Connection con, String db, ActaBaja actaBaja) {
		Long id_acta = (long)0;
		String query = "insert into  `"+db+"`.actaBaja (numero,fecha,observaciones) values (?,?,?)";
		try (PreparedStatement smt = con.prepareStatement(query)) {
			smt.setLong(1, actaBaja.getNumero());
			smt.setString(2, actaBaja.getFecha());
			smt.setString(3, actaBaja.getObservaciones());
			smt.executeUpdate();
		} catch (SQLException e) {
			String className = ActaBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR 1. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		String query2 = "select id from `"+db+"`.actaBaja where numero = ?";
		try (PreparedStatement smt2 = con.prepareStatement(query2)) {
			smt2.setLong(1, actaBaja.getNumero());
			try(ResultSet rs2 = smt2.executeQuery()){
				if(rs2.next()) {
					id_acta = rs2.getLong(1);
				}
			}
		} catch (SQLException e) {
			String className = ActaBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR 2. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (id_acta);
	}

	public static boolean delete(Connection con, String db, Long id_actaBaja) {
		boolean flag = false;
		String query1 = "select id from `"+db+"`.baja WHERE id_actaBaja = ?;";
		try (PreparedStatement smt1 = con.prepareStatement(query1)) {
			smt1.setLong(1, id_actaBaja);
			try(ResultSet resultado1 = smt1.executeQuery()){
				if (resultado1.next()) {
					flag = false;
				} else {
					String query2 = "delete from `"+db+"`.actaBaja WHERE id = ?;";
					try (PreparedStatement smt = con.prepareStatement(query2)) {
						smt.setLong(1, id_actaBaja);
						smt.executeUpdate();
						flag = true;
					}
				}
			}
		} catch (SQLException e) {
			String className = ActaBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (flag);
	}

	public static List<ActaBaja> all(Connection con, String db) {
		List<ActaBaja> lista = new ArrayList<ActaBaja>();
		String query = " select " +
				" id, numero, fecha, actaBajaPDF, observaciones " +
				" from `"+db+"`.actaBaja;";
		try (PreparedStatement smt = con.prepareStatement(query)) {
			try (ResultSet rs = smt.executeQuery()) {
				while (rs.next()) {
					String fecha = null;
					if (rs.getString(3) != null) {
						fecha = myformatfecha.format(rs.getDate(3));
					}
					lista.add(new ActaBaja(rs.getLong(1),rs.getLong(2),fecha,rs.getString(4),rs.getString(5)));
				}
			}
		} catch (SQLException e) {
			String className = ActaBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (lista);
	}

	public static List<ActaBaja> allModificables(Connection con, String db) {
		List<ActaBaja> lista = new ArrayList<ActaBaja>();
		String query = "select id_actaBaja from `"+db+"`.baja where esModificable=1 and fechaConfirma is null group by id_actaBaja;";
		try (PreparedStatement smt2 = con.prepareStatement(query)) {
			Map<Long,Long> mapIdActas = new HashMap<Long,Long>();
			try (ResultSet rs2 = smt2.executeQuery()) {
				while (rs2.next()) {
					mapIdActas.put(rs2.getLong(1), rs2.getLong(1));
				}
				String query2 = " select " +
						" id, numero, fecha, actaBajaPDF, observaciones " +
						" from `"+db+"`.actaBaja;";
				try (PreparedStatement smt = con.prepareStatement(query2)) {
					try (ResultSet rs = smt.executeQuery()){
						while (rs.next()) {
							if(mapIdActas.get(rs.getLong(1)) != null) {
								String fecha = null;
								if (rs.getString(3) != null) {
									fecha = myformatfecha.format(rs.getDate(3));
								}
								lista.add(new ActaBaja(rs.getLong(1),rs.getLong(2),fecha,rs.getString(4),rs.getString(5)));
							}
						}
					}
				}
			} catch (SQLException e) {
				String className = ActaBaja.class.getSimpleName();
				String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
				logger.error("DB ERROR 1. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
			}
		} catch (SQLException e) {
			String className = ActaBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR 2. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (lista);
	}

	public static ActaBaja find(Connection con, String db, Long id_actaBaja) {
		ActaBaja aux = null;
		String query = " select " +
				" id, numero, fecha, actaBajaPDF, observaciones " +
				" from `"+db+"`.actaBaja where id = ?;";
		try (PreparedStatement smt = con.prepareStatement(query)) {
			smt.setLong(1, id_actaBaja);
			try (ResultSet rs = smt.executeQuery()) {
				if (rs.next()) {
					String fecha="";
					if (rs.getString(3) != null) {
						fecha = myformatfecha.format(rs.getDate(3));
					}
					aux = new ActaBaja(rs.getLong(1),rs.getLong(2),fecha,rs.getString(4),rs.getString(5));
				}
			}
		} catch (SQLException e) {
			String className = ActaBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (aux);
	}

}
