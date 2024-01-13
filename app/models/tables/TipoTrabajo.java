package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TipoTrabajo {
	public Long id;
	public String nombre;

	public TipoTrabajo(Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public TipoTrabajo() {super();}

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}

	
	public static List<TipoTrabajo> all(Connection con, String db) {
		List<TipoTrabajo> lista = new ArrayList<TipoTrabajo>();
		try {
			PreparedStatement smt = con
					.prepareStatement("SELECT id,nombre " +
							" FROM `"+db+"`.tipoTrabajo order by nombre");
			ResultSet resultado = smt.executeQuery();
			while (resultado.next()) {
				lista.add(new TipoTrabajo(resultado.getLong(1),resultado.getString(2)));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (lista);
	}
	
	public static Map<String,TipoTrabajo> mapPorNombre(Connection con, String db) {
		Map<String,TipoTrabajo> map = new HashMap<String,TipoTrabajo>();
		try {
			PreparedStatement smt = con
					.prepareStatement("SELECT id,nombre " +
							" FROM `"+db+"`.tipoTrabajo order by nombre");
			ResultSet resultado = smt.executeQuery();
			while (resultado.next()) {
				map.put(resultado.getString(2), new TipoTrabajo(resultado.getLong(1),resultado.getString(2)));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (map);
	}
	
	public static Map<String,TipoTrabajo> mapPorId(Connection con, String db) {
		Map<String,TipoTrabajo> map = new HashMap<String,TipoTrabajo>();
		try {
			PreparedStatement smt = con
					.prepareStatement("SELECT id,nombre " +
							" FROM `"+db+"`.tipoTrabajo order by nombre");
			ResultSet resultado = smt.executeQuery();
			while (resultado.next()) {
				map.put(resultado.getString(1), new TipoTrabajo(resultado.getLong(1),resultado.getString(2)));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (map);
	}
	
	public static Map<String,TipoTrabajo> mapPorCod_CCcost_iconstruye(Connection con, String db) {
		Map<String,TipoTrabajo> map = new HashMap<String,TipoTrabajo>();
		try {
			PreparedStatement smt = con
					.prepareStatement("SELECT id, nombre, cod_CCcost_iconstruye " +
							" FROM `"+db+"`.tipoTrabajo order by nombre");
			ResultSet resultado = smt.executeQuery();
			while (resultado.next()) {
				map.put(resultado.getString(3), new TipoTrabajo(resultado.getLong(1),resultado.getString(2)));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (map);
	}
	
	public static TipoTrabajo find(Connection con,String db, Long id) {
		TipoTrabajo aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement("SELECT id,nombre " +
							" FROM `"+db+"`.tipoTrabajo WHERE id = ?" );
			smt.setLong(1, id);
			ResultSet resultado = smt.executeQuery();
			if (resultado.next()) {
				aux = new TipoTrabajo(resultado.getLong(1),resultado.getString(2));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (aux);
	}
	
	public static TipoTrabajo findPorCCOST(Connection con,String db, String cod_CCcost_iconstruye) {
		TipoTrabajo aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement("SELECT id,nombre " +
							" FROM `"+db+"`.tipoTrabajo WHERE cod_CCcost_iconstruye = ?" );
			smt.setString(1, cod_CCcost_iconstruye);
			ResultSet resultado = smt.executeQuery();
			if (resultado.next()) {
				aux = new TipoTrabajo(resultado.getLong(1),resultado.getString(2));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (aux);
	}
	
	public static boolean existeTipoTrabajo(Connection con, String db, String nombre_tipo) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("SELECT id,nombre FROM `"+db+"`.tipoTrabajo WHERE upper(nombre) = ?" );
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
					.prepareStatement("INSERT INTO `"+db+"`.tipoTrabajo (nombre) VALUES (?)");		
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
	
	public static boolean createConCCOST(Connection con, String db, String cod_CCcost_iconstruye, String trabajo) {	
		if (trabajo.equals("") || trabajo.equals(null)){
			return(false);
		}
		int falso = 0;
		try {
			PreparedStatement smt = con
					.prepareStatement("INSERT INTO `"+db+"`.tipoTrabajo (cod_CCcost_iconstruye, nombre) VALUES (?, ?)");		
			smt.setString(1, cod_CCcost_iconstruye);
			smt.setString(2, trabajo.trim());
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
					.prepareStatement("UPDATE `"+db+"`.tipoTrabajo SET `"+campo+"` = ? WHERE id = ?");
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
	
	public static boolean delete(Connection con,String db, Long id_tipoTrabajo) {
		int falso = 0;
		try {
			PreparedStatement smt = con
					.prepareStatement("Select * FROM `"+db+"`.hojaVida WHERE id_tipoTrabajo = ?");
			smt.setLong(1, id_tipoTrabajo);
			ResultSet resultado = smt.executeQuery();
			if (resultado.next()) {
				falso=1;
			}else{
				
				PreparedStatement smt1 = con
						.prepareStatement("DELETE FROM `"+db+"`.tipoTrabajo WHERE id = ?");
				smt1.setLong(1, id_tipoTrabajo);
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
