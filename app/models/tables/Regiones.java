package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Regiones {
	public String codigo;
	public String nombre;

	public Regiones(String codigo, String nombre) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
	}
	public Regiones() {
		super();
	}
	
	
	public static List<Regiones> all(Connection con,String db) {
		List<Regiones> lista = new ArrayList<Regiones>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select codigo,nombre from `"+db+"`.regiones order by nombre");
			ResultSet resultado = smt.executeQuery();
			while (resultado.next()) {
				lista.add(new Regiones(resultado.getString(1),resultado.getString(2)));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (lista);
	}
	
	public static Regiones findPorNombre(Connection con, String db, String nombre) {
		Regiones aux = new Regiones();
		try {
			PreparedStatement smt = con
					.prepareStatement("select codigo,nombre from `"+db+"`.regiones where nombre = ?" );
			smt.setString(1, nombre.trim());
			ResultSet resultado = smt.executeQuery();
			if (resultado.next()) {
				aux = new Regiones(resultado.getString(1),resultado.getString(2));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (aux);
	}
	
	
}
