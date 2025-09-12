package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EstadoEquipo {
	public Long id;
	public Long id_guia;
	public Long id_movimiento;
	public Long id_tipoEstado;
	public Double cantidad;
	public Long cobraArriendo;
	
	public EstadoEquipo(Long id, Long id_guia, Long id_movimiento, Long id_tipoEstado, Double cantidad, Long cobraArriendo) {
		super();
		this.id = id;
		this.id_guia = id_guia;
		this.id_movimiento = id_movimiento;
		this.id_tipoEstado = id_tipoEstado;
		this.cantidad = cantidad;
		this.cobraArriendo = cobraArriendo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId_guia() {
		return id_guia;
	}
	public void setId_guia(Long id_guia) {
		this.id_guia = id_guia;
	}
	public Long getId_movimiento() {
		return id_movimiento;
	}
	public void setId_movimiento(Long id_movimiento) {
		this.id_movimiento = id_movimiento;
	}
	public Long getId_tipoEstado() {
		return id_tipoEstado;
	}
	public void setId_tipoEstado(Long id_tipoEstado) {
		this.id_tipoEstado = id_tipoEstado;
	}
	public Double getCantidad() {
		return cantidad;
	}
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}
	public EstadoEquipo() {super();}

	public Long getCobraArriendo() {
		return cobraArriendo;
	}

	public void setCobraArriendo(Long cobraArriendo) {
		this.cobraArriendo = cobraArriendo;
	}

	public static Map<Long,String> mapStrEstadoEquipo (Connection con, String db, Long id_guia){
		Map<Long,String> map = new HashMap<Long,String>();
		List<EstadoEquipo> lista = EstadoEquipo.allPorIdGuia(con, db, id_guia);
		lista.forEach(x->{
			Long key = x.getId_movimiento();
			String value = ""+x.getId_tipoEstado()+":"+x.getCantidad()+";";
			if(map.get(key) == null) {
				map.put(key, value);
			}else {
				map.put(key, map.get(key) + value);
			}
		});
		return(map);
	}
	
	public static List<EstadoEquipo> allPorIdGuia (Connection con, String db, Long id_guia){
		List<EstadoEquipo> lista = new ArrayList<EstadoEquipo>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select id, id_guia, id_movimiento, id_tipoEstado, cantidad, cobraArriendo " +
							" from `"+db+"`.estadoEquipo where id_guia=?;");
			smt.setLong(1, id_guia);
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				lista.add(new EstadoEquipo(rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getLong(4),rs.getDouble(5),rs.getLong(6)));
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(lista);
	}
	
	public static Map<String,Double> mapEstadoEquipos(Connection con, String db,String idMovimiento) {
		Map<String,Double> par = new HashMap<String,Double>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select movimiento.id_equipo, estadoEquipo.id_tipoEstado, estadoEquipo.cantidad " + 
							"	from `"+db+"`.estadoEquipo " + 
							"	left join `"+db+"`.movimiento on movimiento.id = estadoEquipo.id_movimiento " +
							"   where id_movimiento=?;"); 
			smt.setString(1, idMovimiento.trim());
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				par.put(""+rs.getString(1)+rs.getString(2),rs.getDouble(3));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (par);
	}

	public static Map<Long,Long> mapEquipVsCobraArriendo (Connection con, String db, Long id_guia){
		Map<Long,Long> map = new HashMap<Long,Long>() ;
		try {
			PreparedStatement smt = con
					.prepareStatement(" select" +
							" estadoEquipo.id," +
							" estadoEquipo.id_guia," +
							" estadoEquipo.id_movimiento," +
							" estadoEquipo.id_tipoEstado," +
							" estadoEquipo.cantidad," +
							" estadoEquipo.cobraArriendo," +
							" movimiento.id_equipo" +
							" from `"+db+"`.estadoEquipo " +
							" left join `"+db+"`.movimiento on movimiento.id = id_movimiento" +
							" left join `"+db+"`.tipoEstado on tipoEstado.id = estadoEquipo.id_tipoEstado" +
							" where estadoEquipo.id_guia=? and estadoEquipo.id_tipoEstado = 1;");
			smt.setLong(1, id_guia);
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				map.put(rs.getLong(7),rs.getLong(6));
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(map);
	}

	public static boolean updateCobraArriendo(Connection con, String db, Long valor, Long id_equipo, Long id_guia) {
		try {
			PreparedStatement smt = con
					.prepareStatement("UPDATE `"+db+"`.estadoEquipo" +
							" JOIN `"+db+"`.movimiento ON movimiento.id = estadoEquipo.id_movimiento" +
							" SET estadoEquipo.cobraArriendo = ?" +
							" WHERE movimiento.id_equipo = ? and estadoEquipo.id_guia = ?;");
			smt.setLong(1, valor);
			smt.setLong(2, id_equipo);
			smt.setLong(3, id_guia);
			smt.executeUpdate();
			smt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean deletePorIdGuia(Connection con, String db, Long id_guia) {
		try {
			PreparedStatement smt = con
					.prepareStatement("DELETE FROM `"+db+"`.estadoEquipo" +
							" WHERE estadoEquipo.id_guia = ?;");
			smt.setLong(1, id_guia);
			smt.executeUpdate();
			smt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	
	
}
