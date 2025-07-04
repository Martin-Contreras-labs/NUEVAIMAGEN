package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuxHuella {

	private static final Logger logger = LoggerFactory.getLogger(AuxHuella.class);

	public Long aux;

	public AuxHuella(Long aux) {
		super();
		this.aux = aux;
	}

	public AuxHuella() {
		super();
	}

	public static Long findHuella(Connection con, String base, String id_usuario) {
		Long aux = (long) 0;
		try (PreparedStatement smt = con.prepareStatement(
				"Select huella from `" + base + "`.`auxHuella` where id_usuario=?;")) {

			smt.setString(1, id_usuario);

			try (ResultSet rs = smt.executeQuery()) {
				if (rs.next()) {
					aux = rs.getLong(1);
				} else {
					try (PreparedStatement smt2 = con.prepareStatement(
							"Insert into `" + base + "`.`auxHuella` (id_usuario) values (?);")) {

						smt2.setString(1, id_usuario);
						smt2.executeUpdate();
					}
				}
			}

		} catch (SQLException e) {
			String className = AjustesEP.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, base, e);
		}
		return aux;
	}

	public static Long aumentaHuella(Connection con, String base, String id_usuario) {
		Long aux = (long) 0;
		try (PreparedStatement smt = con.prepareStatement(
				"update `" + base + "`.`auxHuella` set huella = huella+1 where id_usuario=?;")) {

			smt.setString(1, id_usuario);
			smt.executeUpdate();

		} catch (SQLException e) {
			String className = AjustesEP.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, base, e);
		}
		return aux;
	}

	public static boolean existeHuella(Connection con, String base, Long aux_huella, String id_usuario) {
		boolean flag = false;
		try (PreparedStatement smt = con.prepareStatement(
				"select huella "
						+ " from `" + base + "`.`auxHuella` "
						+ " where id_usuario=? and huella=?;")) {

			smt.setString(1, id_usuario);
			smt.setLong(2, aux_huella);

			try (ResultSet rs = smt.executeQuery()) {
				if (rs.next()) {
					flag = true;
				}
			}

		} catch (SQLException e) {
			String className = AjustesEP.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, base, e);
		}
		return flag;
	}
}
