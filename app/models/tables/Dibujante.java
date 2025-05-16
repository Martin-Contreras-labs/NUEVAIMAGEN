package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Dibujante {
	public Long id;
	public String nombre;
	public String observaciones;
	public Long vigente;

	public Dibujante(Long id, String nombre, String observaciones, Long vigente) {
		this.id = id;
		this.nombre = nombre;
		this.observaciones = observaciones;
		this.vigente = vigente;
	}

	public Dibujante() {super();}

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Long getVigente() {
		return vigente;
	}
	public void setVigente(Long vigente) {
		this.vigente = vigente;
	}

	public static List<Dibujante> all(Connection con, String db) {
		List<Dibujante> lista = new ArrayList<Dibujante>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,nombre,observaciones,vigente " +
							" from `"+db+"`.dibujante  order by nombre;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				lista.add(new Dibujante(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getLong(4)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (lista);
	}

	public static List<Dibujante> allVigentes(Connection con, String db) {
		List<Dibujante> lista = new ArrayList<Dibujante>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,nombre,observaciones,vigente " +
							" from `"+db+"`.dibujante  where vigente = 1 order by nombre;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				lista.add(new Dibujante(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getLong(4)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static Map<String, Dibujante> mapPorNombre(Connection con, String db) {
		 Map<String, Dibujante> map = new HashMap<String, Dibujante>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,nombre,observaciones,vigente " +
							" from `"+db+"`.dibujante");
			ResultSet resultado = smt.executeQuery();
			while (resultado.next()) {
				map.put(resultado.getString(2), new Dibujante(resultado.getLong(1),resultado.getString(2),resultado.getString(3),resultado.getLong(4)));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (map);
	}
	
	public static Map<Long, Dibujante> mapAll(Connection con, String db) {
		 Map<Long, Dibujante> map = new HashMap<Long, Dibujante>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,nombre,observaciones,vigente " +
							" from `"+db+"`.dibujante");
			ResultSet resultado = smt.executeQuery();
			while (resultado.next()) {
				map.put(resultado.getLong(1), new Dibujante(resultado.getLong(1),resultado.getString(2),resultado.getString(3),resultado.getLong(4)));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
   			e.printStackTrace();
		}
		return (map);
	}
	
	public static Dibujante find(Connection con, String db, Long id) {
		Dibujante aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,nombre,observaciones,vigente " +
							" from `"+db+"`.dibujante where id = ?;");
			smt.setLong(1, id);
			ResultSet resultado = smt.executeQuery();
			if (resultado.next()) {
				aux = new Dibujante(resultado.getLong(1),resultado.getString(2),resultado.getString(3),resultado.getLong(4));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (aux);
	}

	public static boolean create(Connection con, String db, Dibujante dibujante) {
		try {
			PreparedStatement smt = con
					.prepareStatement("insert into `"+db+"`.dibujante (nombre,observaciones,vigente) values (?,?,1);");
			smt.setString(1, dibujante.nombre);
			smt.setString(2, dibujante.observaciones);
			smt.executeUpdate();
			smt.close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public static boolean existe(Connection con, String db, String nombre) {
		boolean flag = false;
		try {
			PreparedStatement smt2 = con
					.prepareStatement("select id, nombre, observaciones, vigente " +
							" from `"+db+"`.dibujante " +
							" where upper(nombre) = ?" );
			smt2.setString(1, nombre.toUpperCase());
			ResultSet rs2 = smt2.executeQuery();
			if (rs2.next()) {
				flag=true;
			}
			rs2.close();
			smt2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}

	public static boolean modificaPorCampo(Connection con,String db,String campo,Long id_dibujante,String valor) {
		boolean flag = false;
		try {
			PreparedStatement smt = con.prepareStatement("update `"+db+"`.dibujante set `" + campo + "` = ? WHERE id = ?;");
			smt.setString(1, valor.trim());
			smt.setLong(2, id_dibujante);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	

}
