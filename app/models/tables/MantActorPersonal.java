package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class MantActorPersonal {
	public Long id;
	public String nombre;

	public MantActorPersonal(Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public MantActorPersonal() {super();}

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	
	public static List<MantActorPersonal> all(Connection con, String db) {
		List<MantActorPersonal> lista = new ArrayList<MantActorPersonal>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,nombre from `"+db+"`.mantActorPersonal;");
			ResultSet resultado = smt.executeQuery();
			while (resultado.next()) {
				lista.add(new MantActorPersonal(resultado.getLong(1),resultado.getString(2)));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
    		e.printStackTrace();
		}
		return (lista);
	}
	
	public static MantActorPersonal find(Connection con, String db, Long id_actorPersonal) {
		MantActorPersonal aux = new MantActorPersonal();
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,nombre from `"+db+"`.mantActorPersonal WHERE id = ?" );
			smt.setLong(1, id_actorPersonal);
			ResultSet resultado = smt.executeQuery();
			if (resultado.next()) {
				aux = new MantActorPersonal(resultado.getLong(1),resultado.getString(2));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}
	
	public static Map<Long,String> mapAll(Connection con, String db) {
		Map<Long,String> map = new HashMap<Long,String>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,nombre from `"+db+"`.mantActorPersonal;" );
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				map.put(rs.getLong(1), rs.getString(2)) ;
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (map);
	}
	
	

}
