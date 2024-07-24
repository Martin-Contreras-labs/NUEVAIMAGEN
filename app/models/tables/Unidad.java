package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Unidad{
	public Long id;
	public String nombre;

	public Unidad(Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public Unidad() {super();}

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}

	
	public static Map<Long,Unidad> mapAll (Connection con, String db) {
		Map<Long,Unidad> map = new HashMap<Long,Unidad>();
		List<Unidad> lista = Unidad.all(con, db);
		for(int i=0; i<lista.size(); i++) {
			map.put(lista.get(i).id, lista.get(i));
		}
		return map;
	}
	
	public static List<Unidad> all(Connection con, String db) {
		List<Unidad> lista = new ArrayList<Unidad>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,nombre from `"+db+"`.unidad");
			ResultSet resultado = smt.executeQuery();
			while (resultado.next()) {
				lista.add(new Unidad(resultado.getLong(1),resultado.getString(2)));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
    		e.printStackTrace();
		}
		return (lista);
	}
	
	public static Map<String,Unidad> mapPorNombre (Connection con, String db) {
		Map<String,Unidad> map = new HashMap<String,Unidad>();
		List<Unidad> lista = Unidad.all(con, db);
		for(int i=0; i<lista.size(); i++) {
			map.put(lista.get(i).nombre.toUpperCase(), lista.get(i));
		}
		return map;
	}
	
	public static Unidad findPorNombre (Connection con, String db, String unidad) {
		Unidad aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,nombre from `"+db+"`.unidad where nombre = ?;");
			smt.setString(1, unidad.trim());
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				aux = new Unidad(rs.getLong(1), rs.getString(2));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    		e.printStackTrace();
		}
		return aux;
	}
	
	public static Unidad create(Connection con, String db, String nombre) {
		Unidad aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement("insert into `"+db+"`.unidad (nombre) values ('"+nombre+"');");
			smt.executeUpdate();
			smt.close();
			PreparedStatement smt2 = con
					.prepareStatement("select max(id) from `"+db+"`.unidad");
			ResultSet rs2 = smt2.executeQuery();
			if (rs2.next()) {
				aux = new Unidad(rs2.getLong(1),nombre);
			}
			smt2.close();
			rs2.close();
		} catch (SQLException e) {
    		e.printStackTrace();
		}
		return (aux);
	}
	

}
