package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TipoPlan {
	public Long id;
	public String nombre;

	public TipoPlan(Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public TipoPlan() {super();}

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}

	
	public static List<TipoPlan> all(Connection con, String db) {
		List<TipoPlan> lista = new ArrayList<TipoPlan>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,nombre " +
							" from `"+db+"`.tipoPlan order by nombre");
			ResultSet resultado = smt.executeQuery();
			while (resultado.next()) {
				lista.add(new TipoPlan(resultado.getLong(1),resultado.getString(2)));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (lista);
	}
	
	public static Map<String,TipoPlan> mapPorNombre(Connection con, String db) {
		Map<String,TipoPlan> map = new HashMap<String,TipoPlan>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,nombre " +
							" from `"+db+"`.tipoPlan order by nombre");
			ResultSet resultado = smt.executeQuery();
			while (resultado.next()) {
				map.put(resultado.getString(2), new TipoPlan(resultado.getLong(1),resultado.getString(2)));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (map);
	}
	
	public static TipoPlan find(Connection con,String db, Long id) {
		TipoPlan aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,nombre " +
							" from `"+db+"`.tipoPlan WHERE id = ?" );
			smt.setLong(1, id);
			ResultSet resultado = smt.executeQuery();
			if (resultado.next()) {
				aux = new TipoPlan(resultado.getLong(1),resultado.getString(2));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (aux);
	}
	
	public static boolean existeTipoPlan(Connection con, String db, String nombre_tipo) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,nombre from `"+db+"`.tipoPlan WHERE upper(nombre) = ?" );
			smt.setString(1, nombre_tipo.toUpperCase());
			ResultSet resultado = smt.executeQuery();
			if (resultado.next()) {
				flag = true;
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean create(Connection con, String db, String trabajo) {	
		if (trabajo.equals("") || trabajo.equals(null)){
			return(false);
		}
		int falso = 0;
		try {
			PreparedStatement smt = con
					.prepareStatement("insert into `"+db+"`.tipoPlan (nombre) values (?)");		
			smt.setString(1, trabajo.trim());
			smt.executeUpdate();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
			falso=1;
		}
		if(falso==1) return(false);
		return (true);
	}
	
	public static boolean modificaPorCampo(Connection con, String db, String campo, Long id_grupo, String valor) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("update `"+db+"`.tipoPlan set `"+campo+"` = ? WHERE id = ?");
			smt.setString(1, valor.trim());
			smt.setLong(2, id_grupo);
			smt.executeUpdate();
			smt.close();
			flag=true;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean delete(Connection con,String db, Long id_tipoPlan) {
		int falso = 0;
		try {
			PreparedStatement smt = con
					.prepareStatement("Select * from `"+db+"`.hojaVida WHERE id_tipoPlan = ?");
			smt.setLong(1, id_tipoPlan);
			ResultSet resultado = smt.executeQuery();
			if (resultado.next()) {
				falso=1;
			}else{
				
				PreparedStatement smt1 = con
						.prepareStatement("delete from `"+db+"`.tipoPlan WHERE id = ?");
				smt1.setLong(1, id_tipoPlan);
				smt1.executeUpdate();
				smt1.close();
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
			falso=1;
		}
		if(falso==1) return(false);
		return (true);
	}

}
