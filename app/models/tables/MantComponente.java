package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class MantComponente {
	public Long id;
	public String nombre;

	public MantComponente(Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public MantComponente() {super();}

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	
	public static List<MantComponente> all(Connection con, String db) {
		List<MantComponente> lista = new ArrayList<MantComponente>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,nombre from `"+db+"`.mantComponente order by nombre");
			ResultSet resultado = smt.executeQuery();
			while (resultado.next()) {
				lista.add(new MantComponente(resultado.getLong(1),resultado.getString(2)));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
    		e.printStackTrace();
		}
		return (lista);
	}
	
	public static boolean estaEnUso(Connection con, String db, Long id) {
		boolean flag = false;
		try {
			PreparedStatement smt1 = con
					.prepareStatement("Select * from `"+db+"`.mantTransacReport WHERE id_mantComponente = ?");
			smt1.setLong(1, id);
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
	
	public static boolean delete(Connection con, String db, Long id) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("delete from `"+db+"`.mantComponente WHERE id = ?");
			smt.setLong(1, id);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static MantComponente find(Connection con, String db, Long id) {
		MantComponente aux = new MantComponente();
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,nombre from `"+db+"`.mantComponente WHERE id = ?" );
			smt.setLong(1, id);
			ResultSet resultado = smt.executeQuery();
			if (resultado.next()) {
				aux = new MantComponente(resultado.getLong(1),resultado.getString(2));
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
					.prepareStatement("select id,nombre from `"+db+"`.mantComponente;" );
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				map.put(rs.getLong(1), rs.getString(2));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (map);
	}
	
	public static boolean existeComponente(Connection con, String db, String nombre_componente) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,nombre from `"+db+"`.mantComponente WHERE upper(nombre) = ?" );
			smt.setString(1, nombre_componente.toUpperCase());
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
	
	public static boolean create(Connection con,String db, String nombreComponente) {	
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("insert into `"+db+"`.mantComponente (nombre) values (?)");		
			smt.setString(1, nombreComponente.trim());
			smt.executeUpdate();
            smt.close();
			flag = true;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean modificaPorCampo(Connection con,String db, String campo, Long id_componente, String valor) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("update `"+db+"`.mantComponente set `"+campo+"` = ? WHERE id = ?");
			smt.setString(1, valor.trim());
			smt.setLong(2, id_componente);
			System.out.println(smt);
			smt.executeUpdate();
			smt.close();
			flag=true;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	

}
