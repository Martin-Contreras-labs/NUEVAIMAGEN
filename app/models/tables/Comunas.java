package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Comunas {
	public String codigo;
	public String nombre;

	public Comunas(String codigo, String nombre) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
	}
	public Comunas() {
		super();
	}
	
	public static Map<String, String> mapAll(Connection con, String db) {
		Map<String,String> map = new HashMap<String,String>();
		try {
			PreparedStatement smt = con
					.prepareStatement("SELECT codigo,nombre FROM `"+db+"`.comunas;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				map.put(rs.getString(1), rs.getString(2));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (map);
	}
	
	public static List<Comunas> allPorRegion(Connection con, String db,String cod_region) {
		List<Comunas> lista = new ArrayList<Comunas>();
		if(cod_region==null) cod_region="";
		try {
			PreparedStatement smt = con
					.prepareStatement("SELECT codigo,nombre FROM `"+db+"`.comunas WHERE left(codigo,2) = ? order by nombre;");
			smt.setString(1, cod_region.trim());
			ResultSet resultado = smt.executeQuery();
			while (resultado.next()) {
				lista.add(new Comunas(resultado.getString(1),resultado.getString(2)));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static String selectPorRegion1(Connection con, String db,String cod_region) {
		List<Comunas> lista = Comunas.allPorRegion(con, db, cod_region);
		String vista = 
				"<select class='custom-select' name='cod_comuna'> " +
				"<option value='0'></option>";
		for(int i=0;i<lista.size();i++){
	     	vista=vista+"<option value='"+lista.get(i).codigo+"'>"+lista.get(i).nombre+"</option>";
		}
		vista=vista+"</select>";
		return (vista);
	}
	
	public static String selectPorRegion2(Connection con, String db,String cod_region) {
		List<Comunas> lista = Comunas.allPorRegion(con, db, cod_region);
		String vista = 
				"<select class='custom-select' id='cod_comuna' onchange='modificarProyecto(id);'> " +
				"<option value='0'></option>";
		for(int i=0;i<lista.size();i++){
	     	vista=vista+"<option value='"+lista.get(i).codigo+"'>"+lista.get(i).nombre+"</option>";
		}
		vista=vista+"</select>";
		return (vista);
	}
	
	public static String selectPorRegion3(Connection con, String db,String cod_region) {
		List<Comunas> lista = Comunas.allPorRegion(con, db, cod_region);
		String vista = 
				"<select class='custom-select' id='cod_comuna' onchange='modificarCliente(id);'> " +
				"<option value='0'></option>";
		for(int i=0;i<lista.size();i++){
	     	vista=vista+"<option value='"+lista.get(i).codigo+"'>"+lista.get(i).nombre+"</option>";
		}
		vista=vista+"</select>";
		return (vista);
	}
	
	public static String selectPorRegion4(Connection con, String db,String cod_region) {
		List<Comunas> lista = Comunas.allPorRegion(con, db, cod_region);
		String vista = 
				"<select class='custom-select' id='cod_comuna' onchange='modificarFabrica(id);'> " +
				"<option value='0'></option>";
		for(int i=0;i<lista.size();i++){
	     	vista=vista+"<option value='"+lista.get(i).codigo+"'>"+lista.get(i).nombre+"</option>";
		}
		vista=vista+"</select>";
		return (vista);
	}
	
	public static String selectPorRegion5(Connection con, String db,String cod_region) {
		List<Comunas> lista = Comunas.allPorRegion(con, db, cod_region);
		String vista = 
				"<select class='custom-select' id='cod_comuna' onchange='modificarProveedor(id);'> " +
				"<option value='0'></option>";
		for(int i=0;i<lista.size();i++){
	     	vista=vista+"<option value='"+lista.get(i).codigo+"'>"+lista.get(i).nombre+"</option>";
		}
		vista=vista+"</select>";
		return (vista);
	}
	
	public static Comunas findPorNombre(Connection con, String db, String nombre) {
		Comunas aux = new Comunas();
		try {
			PreparedStatement smt = con
					.prepareStatement("SELECT codigo,nombre FROM `"+db+"`.comunas where nombre = ?" );
			smt.setString(1, nombre.trim());
			ResultSet resultado = smt.executeQuery();
			if (resultado.next()) {
				aux = new Comunas(resultado.getString(1),resultado.getString(2));
			}else {
				aux = null;
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}
	
	
	
}
