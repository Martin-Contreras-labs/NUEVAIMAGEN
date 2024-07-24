package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CotizaEstado {
	public Long id;
	public String estado;

	public CotizaEstado(Long id, String estado) {
		super();
		this.id = id;
		this.estado = estado;
	}

	public CotizaEstado() {super();}
	
	

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

	public static Map<Long,CotizaEstado> mapAll(Connection con, String db){
		List<CotizaEstado> lista = CotizaEstado.allconIdNegativos(con, db);
		Map<Long, CotizaEstado> map = new HashMap<Long, CotizaEstado>();
		lista.forEach(x->{
			map.put(x.getId(), x);
		});
		return(map);
	}
	
	public static List<CotizaEstado> all(Connection con, String db) {
		List<CotizaEstado> lista = new ArrayList<CotizaEstado>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,estado from `"+db+"`.cotizaEstado");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				if(rs.getLong(1) > 0) {
					lista.add(new CotizaEstado(rs.getLong(1),rs.getString(2)));
				}
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<CotizaEstado> allconIdNegativos(Connection con, String db) {
		List<CotizaEstado> lista = new ArrayList<CotizaEstado>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,estado from `"+db+"`.cotizaEstado");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				lista.add(new CotizaEstado(rs.getLong(1),rs.getString(2)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (lista);
	}
	
	public static CotizaEstado find(Connection con,String db, Long id) {
		CotizaEstado aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,estado " +
							" from `"+db+"`.cotizaEstado WHERE id = ?" );
			smt.setLong(1, id);
			ResultSet resultado = smt.executeQuery();
			if (resultado.next()) {
				aux = new CotizaEstado(resultado.getLong(1),resultado.getString(2));
			}
			resultado.close();
			smt.close();

		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (aux);
	}
	
	public static boolean estaEnUso(Connection con, String db, Long id_cotizaEstado) {
		boolean flag = false;
		try {
			PreparedStatement smt1 = con
					.prepareStatement("Select * from `"+db+"`.cotizacion WHERE id_cotizaEstado = ?");
			smt1.setLong(1, id_cotizaEstado);
			ResultSet rs1 = smt1.executeQuery();
			if (rs1.next()) {
				flag = true;
			}
			rs1.close();
			smt1.close();
			
			PreparedStatement smt2 = con
					.prepareStatement("Select * from `"+db+"`.cotiOdo WHERE id_cotizaEstado = ?");
			smt2.setLong(1, id_cotizaEstado);
			ResultSet rs2 = smt2.executeQuery();
			if (rs2.next()) {
				flag = true;
			}
			rs2.close();
			smt2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean delete(Connection con, String db, Long id_cotizaEstado) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("delete from `"+db+"`.cotizaEstado WHERE id = ?");
			smt.setLong(1, id_cotizaEstado);
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
					.prepareStatement("select id,estado from `"+db+"`.cotizaEstado WHERE upper(estado) = ?" );
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
	
	public static boolean modificaPorCampo(Connection con,String db, String campo, Long id_cotizaEstado, String valor) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("update `"+db+"`.cotizaEstado set `"+campo+"` = ? WHERE id = ?");
			smt.setString(1, valor.trim());
			smt.setLong(2, id_cotizaEstado);
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
					.prepareStatement("insert into `"+db+"`.cotizaEstado (estado) values (?)");		
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
