package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PlazoProbable {
	public Long id;
	public String plazo;

	public PlazoProbable(Long id, String plazo) {
		super();
		this.id = id;
		this.plazo = plazo;
	}

	public PlazoProbable() {super();}

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getPlazo() {return plazo;}
	public void setPlazo(String plazo) {this.plazo = plazo;}

	
	public static List<PlazoProbable> all(Connection con, String db) {
		List<PlazoProbable> lista = new ArrayList<PlazoProbable>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,plazo " +
							" from `"+db+"`.plazoProbable  order by id;");
			ResultSet resultado = smt.executeQuery();
			while (resultado.next()) {
				lista.add(new PlazoProbable(resultado.getLong(1),resultado.getString(2)));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (lista);
	}

	
	public static PlazoProbable find(Connection con, String db, Long id) {
		PlazoProbable aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,plazo " +
							" from `"+db+"`.plazoProbable where id = ?;");
			smt.setLong(1, id);
			ResultSet resultado = smt.executeQuery();
			if (resultado.next()) {
				aux = new PlazoProbable(resultado.getLong(1),resultado.getString(2));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (aux);
	}
	

}
