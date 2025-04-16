package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UnidadMantencion {
	public Long id;
	public String nombre;

	public UnidadMantencion(Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public UnidadMantencion() {super();}

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}

	public static Map<Long,UnidadMantencion> mapAll(Connection con, String db) {
		Map<Long,UnidadMantencion> map = new HashMap<Long,UnidadMantencion>();
		List<UnidadMantencion> all = UnidadMantencion.all(con, db);
		for(UnidadMantencion x: all){
			map.put(x.getId(), x);
		}
		return (map);
	}

	public static List<UnidadMantencion> all(Connection con, String db) {
		List<UnidadMantencion> lista = new ArrayList<UnidadMantencion>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,nombre " +
							" from `"+db+"`.unidadMantencion ");
			ResultSet resultado = smt.executeQuery();
			while (resultado.next()) {
				lista.add(new UnidadMantencion(resultado.getLong(1),resultado.getString(2)));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (lista);
	}
	
	public static UnidadMantencion find(Connection con,String db, Long id) {
		UnidadMantencion aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,nombre " +
							" from `"+db+"`.unidadMantencion WHERE id = ?" );
			smt.setLong(1, id);
			ResultSet resultado = smt.executeQuery();
			if (resultado.next()) {
				aux = new UnidadMantencion(resultado.getLong(1),resultado.getString(2));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (aux);
	}
	

}
