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


public class ReparacionEquipo {
	public Long id;
	public Long id_guia;
	public Long id_movimiento;
	public Long id_estadoEquipo;
	public Long id_tipoEstado;
	public Long id_tipoReparacion;
	public Double cantidad;
	
	public ReparacionEquipo(Long id, Long id_guia, Long id_movimiento, Long id_estadoEquipo, Long id_tipoEstado,
			Long id_tipoReparacion, Double cantidad) {
		super();
		this.id = id;
		this.id_guia = id_guia;
		this.id_movimiento = id_movimiento;
		this.id_estadoEquipo = id_estadoEquipo;
		this.id_tipoEstado = id_tipoEstado;
		this.id_tipoReparacion = id_tipoReparacion;
		this.cantidad = cantidad;
	}

	public ReparacionEquipo() {super();}

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

	public Long getId_estadoEquipo() {
		return id_estadoEquipo;
	}

	public void setId_estadoEquipo(Long id_estadoEquipo) {
		this.id_estadoEquipo = id_estadoEquipo;
	}

	public Long getId_tipoEstado() {
		return id_tipoEstado;
	}

	public void setId_tipoEstado(Long id_tipoEstado) {
		this.id_tipoEstado = id_tipoEstado;
	}

	public Long getId_tipoReparacion() {
		return id_tipoReparacion;
	}

	public void setId_tipoReparacion(Long id_tipoReparacion) {
		this.id_tipoReparacion = id_tipoReparacion;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public Long getId_movimiento() {
		return id_movimiento;
	}

	public void setId_movimiento(Long id_movimiento) {
		this.id_movimiento = id_movimiento;
	}


	
	
	
	public static Map<Long,String> mapStrReparacionEquipo (Connection con, String db, Long id_guia){
		Map<Long,String> map = new HashMap<Long,String>();
		List<ReparacionEquipo> lista = ReparacionEquipo.allPorIdGuia(con, db, id_guia);
		lista.forEach(x->{
			Long key = x.getId_movimiento();
			String value = ""+x.getId_tipoEstado()+":"+x.getId_tipoReparacion()+":"+x.getCantidad()+";";
			if(map.get(key) == null) {
				map.put(key, value);
			}else {
				map.put(key, map.get(key) + value);
			}
		});
		return(map);
	}
	
	
	public static List<ReparacionEquipo> allPorIdGuia (Connection con, String db, Long id_guia){
		List<ReparacionEquipo> lista = new ArrayList<ReparacionEquipo>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select id, id_guia, id_movimiento, id_estadoEquipo, id_tipoEstado, id_tipoReparacion, cantidad " +
							" from `"+db+"`.reparacionEquipo where id_guia=?;");
			smt.setLong(1, id_guia);
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				lista.add(new ReparacionEquipo(rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getLong(4),rs.getLong(5),rs.getLong(6),rs.getDouble(7)));
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(lista);
	}
	
	

	
}
