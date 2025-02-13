package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class MantTipoPersonal {
	public Long id;
	public String nombre;

	public MantTipoPersonal(Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public MantTipoPersonal() {super();}

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	
	public static List<MantTipoPersonal> all(Connection con, String db) {
		List<MantTipoPersonal> lista = new ArrayList<MantTipoPersonal>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,nombre from `"+db+"`.mantTipoPersonal;");
			ResultSet resultado = smt.executeQuery();
			while (resultado.next()) {
				lista.add(new MantTipoPersonal(resultado.getLong(1),resultado.getString(2)));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
    		e.printStackTrace();
		}
		return (lista);
	}
	
	public static MantTipoPersonal find(Connection con, String db, Long id_tipoPersonal) {
		MantTipoPersonal aux = new MantTipoPersonal();
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,nombre from `"+db+"`.mantTipoPersonal WHERE id = ?" );
			smt.setLong(1, id_tipoPersonal);
			ResultSet resultado = smt.executeQuery();
			if (resultado.next()) {
				aux = new MantTipoPersonal(resultado.getLong(1),resultado.getString(2));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}
	
	

}
