package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UnidadServicio{
	public Long id;
	public String nombre;

	public UnidadServicio(Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public UnidadServicio() {super();}

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}

	
	public static Map<Long,UnidadServicio> mapAll (Connection con, String db) {
		Map<Long,UnidadServicio> map = new HashMap<Long,UnidadServicio>();
		List<UnidadServicio> lista = UnidadServicio.all(con, db);
		for(int i=0; i<lista.size(); i++) {
			map.put(lista.get(i).id, lista.get(i));
		}
		return map;
	}
	
	public static List<UnidadServicio> all(Connection con, String db) {
		List<UnidadServicio> lista = new ArrayList<UnidadServicio>();
		try {
			PreparedStatement smt = con
					.prepareStatement("SELECT id,nombre FROM `"+db+"`.unidadServicio order by nombre;");
			ResultSet resultado = smt.executeQuery();
			while (resultado.next()) {
				lista.add(new UnidadServicio(resultado.getLong(1),resultado.getString(2)));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
    		e.printStackTrace();
		}
		return (lista);
	}
	
	public static UnidadServicio find(Connection con, String db, Long id_unidadServicio) {
		UnidadServicio aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement("SELECT id,nombre FROM `"+db+"`.unidadServicio where id = ?;");
			ResultSet resultado = smt.executeQuery();
			if (resultado.next()) {
				aux = new UnidadServicio(resultado.getLong(1),resultado.getString(2));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
    		e.printStackTrace();
		}
		return (aux);
	}
	

}
