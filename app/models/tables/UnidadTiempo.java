package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UnidadTiempo {
	public Long id;
	public String nombre;

	public UnidadTiempo(Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public UnidadTiempo() {super();}

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	
	
	public static List<UnidadTiempo> all(Connection con, String db) {
		List<UnidadTiempo> lista = new ArrayList<UnidadTiempo>();
		try {
			PreparedStatement smt = con
					.prepareStatement("SELECT id,nombre " +
							" FROM `"+db+"`.unidadTiempo");
			ResultSet resultado = smt.executeQuery();
			while (resultado.next()) {
				lista.add(new UnidadTiempo(resultado.getLong(1),resultado.getString(2)));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (lista);
	}

	
	public static Map<Long,Double> equivalencia(Connection con, String db){
		Map<Long,Double> aux = new HashMap<Long,Double>();
		try {
			PreparedStatement smt = con
					.prepareStatement("SELECT id,factor " +
							" FROM `"+db+"`.unidadTiempo");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				aux.put(rs.getLong(1),rs.getDouble(2));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return(aux);
	}
	
	public static Map<Long,UnidadTiempo> mapUnidadTiempo(Connection con, String db){
		Map<Long,UnidadTiempo> aux = new HashMap<Long,UnidadTiempo>();
		try {
			PreparedStatement smt = con
					.prepareStatement("SELECT id,nombre " +
							" FROM `"+db+"`.unidadTiempo");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				aux.put(rs.getLong(1),new UnidadTiempo(rs.getLong(1),rs.getString(2)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return(aux);
	}
	
	public static Map<String,UnidadTiempo> mapUnidadTiempoPorNombre(Connection con, String db){
		Map<String,UnidadTiempo> aux = new HashMap<String,UnidadTiempo>();
		try {
			PreparedStatement smt = con
					.prepareStatement("SELECT id,nombre " +
							" FROM `"+db+"`.unidadTiempo");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				aux.put(rs.getString(2),new UnidadTiempo(rs.getLong(1),rs.getString(2)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return(aux);
	}

	

}
