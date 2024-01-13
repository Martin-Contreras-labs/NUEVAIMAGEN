package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Parametros {
	public String nombre;
	public Long valor;
	public String observaciones;
	public Long esModificable;
	public Long id;

	public Parametros(String nombre, Long valor, String observaciones, Long esModificable, Long id) {
		super();
		this.nombre = nombre;
		this.valor = valor;
		this.observaciones = observaciones;
		this.esModificable = esModificable;
		this.id = id;
	}

	public Parametros() {super();}

	
	public static List<Parametros> all(Connection con, String db) {
		List<Parametros> lista = new ArrayList<Parametros>();
		try {
			PreparedStatement smt = con
					.prepareStatement("SELECT nombre, valor, observaciones, esModificable,id " +
							" FROM `"+db+"`.parametros order by nombre");
			ResultSet resultado = smt.executeQuery();
			while (resultado.next()) {
				lista.add(new Parametros(resultado.getString(1),resultado.getLong(2),resultado.getString(3),resultado.getLong(4),resultado.getLong(5)));
			}
			resultado.close();
			smt.close();
			
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (lista);
	}
	
	public static boolean modify(Connection con, String db, String nombreParametro, Long valor) {
		try {

			PreparedStatement smt = con
					.prepareStatement("UPDATE `"+db+"`.parametros SET valor = ? where nombre = ?;");
			smt.setLong(1, valor);
			smt.setString(2, nombreParametro);
			smt.executeUpdate();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
			return (false);
		}
		return (true);
	}
	
	
	
	

}
