package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TipoMantencion {
	public Long id;
	public String nombre;

	public TipoMantencion(Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public TipoMantencion() {super();}

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}

	
	public static List<TipoMantencion> all(Connection con, String db) {
		List<TipoMantencion> lista = new ArrayList<TipoMantencion>();
		try {
			PreparedStatement smt = con
					.prepareStatement("SELECT id,nombre " +
							" FROM `"+db+"`.tipoMantencion");
			ResultSet resultado = smt.executeQuery();
			while (resultado.next()) {
				lista.add(new TipoMantencion(resultado.getLong(1),resultado.getString(2)));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (lista);
	}
	
	public static Map<String,TipoMantencion> mapPorNombre(Connection con, String db) {
		 Map<String,TipoMantencion> map = new HashMap<String,TipoMantencion>();
		try {
			PreparedStatement smt = con
					.prepareStatement("SELECT id,nombre " +
							" FROM `"+db+"`.tipoMantencion");
			ResultSet resultado = smt.executeQuery();
			while (resultado.next()) {
				map.put(resultado.getString(2), new TipoMantencion(resultado.getLong(1),resultado.getString(2)));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (map);
	}
	
	public static TipoMantencion find(Connection con, String db, Long id) {
		TipoMantencion aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement("SELECT id,nombre " +
							" FROM `"+db+"`.tipoMantencion where id = ?;");
			smt.setLong(1, id);
			ResultSet resultado = smt.executeQuery();
			if (resultado.next()) {
				aux = new TipoMantencion(resultado.getLong(1),resultado.getString(2));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (aux);
	}
	

}
