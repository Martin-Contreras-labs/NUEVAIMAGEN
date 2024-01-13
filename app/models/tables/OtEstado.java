package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class OtEstado {
	public Long id;
	public String estado;

	public OtEstado(Long id, String estado) {
		super();
		this.id = id;
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public OtEstado() {super();}
	
	public static Map<Long,OtEstado> mapAll(Connection con,String db){
		Map<Long,OtEstado> map = new HashMap<Long,OtEstado>();
		List<OtEstado> lista = OtEstado.all(con, db);
		lista.forEach(x->{
			map.put(x.getId(), x);
		});
		return (map);
	}

	public static List<OtEstado> all(Connection con,String db) {
		List<OtEstado> lista = new ArrayList<OtEstado>();
		try {

			PreparedStatement smt = con
					.prepareStatement("SELECT id,estado " +
							" FROM `"+db+"`.otEstado");
			ResultSet resultado = smt.executeQuery();
			while (resultado.next()) {
				lista.add(new OtEstado(resultado.getLong(1),resultado.getString(2)));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (lista);
	}
	
	public static OtEstado find(Connection con,String db, Long id) {
		OtEstado aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement("SELECT id,estado " +
							" FROM `"+db+"`.otEstado WHERE id = ?" );
			smt.setLong(1, id);
			ResultSet resultado = smt.executeQuery();
			if (resultado.next()) {
				aux = new OtEstado(resultado.getLong(1),resultado.getString(2));
			}
			resultado.close();
			smt.close();

		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (aux);
	}
	
	public static boolean estaEnUso(Connection con, String db, Long id_otEstado) {
		boolean flag = false;
		try {
			PreparedStatement smt1 = con
					.prepareStatement("Select * FROM `"+db+"`.ot WHERE id_otEstado = ?");
			smt1.setLong(1, id_otEstado);
			ResultSet rs1 = smt1.executeQuery();
			if (rs1.next()) {
				flag = true;
			}
			rs1.close();
			smt1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean delete(Connection con, String db, Long id_otEstado) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("DELETE FROM `"+db+"`.otEstado WHERE id = ?");
			smt.setLong(1, id_otEstado);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean existeEstado(Connection con, String db, String nombre_estado) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("SELECT id,estado FROM `"+db+"`.otEstado WHERE upper(estado) = ?" );
			smt.setString(1, nombre_estado.toUpperCase());
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
	
	public static boolean modificaPorCampo(Connection con,String db, String campo, Long id_otEstado, String valor) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("UPDATE `"+db+"`.otEstado SET `"+campo+"` = ? WHERE id = ?");
			smt.setString(1, valor.trim());
			smt.setLong(2, id_otEstado);
			smt.executeUpdate();
			smt.close();
			flag=true;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean create(Connection con,String db, String nombre_estado) {	
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("INSERT INTO `"+db+"`.otEstado (estado) VALUES (?)");		
			smt.setString(1, nombre_estado.trim());
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	

}
