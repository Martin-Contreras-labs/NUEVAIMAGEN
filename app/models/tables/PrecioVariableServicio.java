package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;



public class PrecioVariableServicio {
	public Long id_bodegaEmpresa;
	public Long id_servicio;
	public String fecha;
	public Double precio;
	public String precioSTR;
	public Long id_cotiOdo;
	
	public PrecioVariableServicio(Long id_bodegaEmpresa, Long id_servicio, String fecha, Double precio,
			String precioSTR, Long id_cotiOdo) {
		super();
		this.id_bodegaEmpresa = id_bodegaEmpresa;
		this.id_servicio = id_servicio;
		this.fecha = fecha;
		this.precio = precio;
		this.precioSTR = precioSTR;
		this.id_cotiOdo = id_cotiOdo;
	}
	
	public PrecioVariableServicio() {
		super();
	}

	public Long getId_bodegaEmpresa() {
		return id_bodegaEmpresa;
	}

	public void setId_bodegaEmpresa(Long id_bodegaEmpresa) {
		this.id_bodegaEmpresa = id_bodegaEmpresa;
	}

	public Long getId_servicio() {
		return id_servicio;
	}

	public void setId_servicio(Long id_servicio) {
		this.id_servicio = id_servicio;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getPrecioSTR() {
		return precioSTR;
	}

	public void setPrecioSTR(String precioSTR) {
		this.precioSTR = precioSTR;
	}

	public Long getId_cotiOdo() {
		return id_cotiOdo;
	}

	public void setId_cotiOdo(Long id_cotiOdo) {
		this.id_cotiOdo = id_cotiOdo;
	}

	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	
	public static List<PrecioVariableServicio> allXBodXServ(Connection con, String db, Long id_bodegaEmpresa, Long id_servicio, Long decMon, Long id_cotiOdo) {
		List<PrecioVariableServicio> lista = new ArrayList<PrecioVariableServicio>();
		switch(decMon.toString()) {
		 	case "0": myformatdouble = new DecimalFormat("#,##0"); break;
		 	case "2": myformatdouble = new DecimalFormat("#,##0.00"); break;
		 	case "4": myformatdouble = new DecimalFormat("#,##0.0000"); break;
		 	case "6": myformatdouble = new DecimalFormat("#,##0.000000"); break;
		 	default:  break;
		}
		try {
			PreparedStatement smt2 = con
					.prepareStatement("select id_bodegaEmpresa, id_servicio, fecha, precio, id_cotiOdo from `"+db+"`.precioVariableServicio where id_bodegaEmpresa=? and id_servicio=? and id_cotiOdo=?;");
			smt2.setLong(1, id_bodegaEmpresa);
			smt2.setLong(2, id_servicio);
			smt2.setLong(3, id_cotiOdo);
			ResultSet rs2 = smt2.executeQuery();
			while(rs2.next()) {
				lista.add(new PrecioVariableServicio(
						rs2.getLong(1),
						rs2.getLong(2),
						rs2.getString(3),
						rs2.getDouble(4),
						myformatdouble.format(rs2.getDouble(4)),
						rs2.getLong(5)));
			}
			smt2.close();
			rs2.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static boolean insert(Connection con, String db, Long id_bodegaEmpresa, Long id_servicio, String fecha, Double precio, Long id_cotiOdo) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("select fecha from `"+db+"`.precioVariableServicio where id_bodegaEmpresa=? and id_servicio=? and fecha=? and id_cotiOdo=?;");
			smt.setLong(1, id_bodegaEmpresa);
			smt.setLong(2, id_servicio);
			smt.setString(3, fecha);
			smt.setLong(4, id_cotiOdo);
			ResultSet rs = smt.executeQuery();
			if(!rs.next()) {
				PreparedStatement smt2 = con
						.prepareStatement("insert into `"+db+"`.precioVariableServicio (id_bodegaEmpresa, id_servicio, fecha, precio, id_cotiOdo) "
								+ " values (?,?,?,?,?);");
				smt2.setLong(1, id_bodegaEmpresa);
				smt2.setLong(2, id_servicio);
				smt2.setString(3, fecha);
				smt2.setDouble(4, precio);
				smt2.setLong(5, id_cotiOdo);
				smt2.executeUpdate();
				smt2.close();
			}
			smt.close();
			flag = true;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean modifyPrecio(Connection con, String db, Long id_bodegaEmpresa, Long id_servicio, String valor, String fecha, Long id_cotiOdo) {
		boolean flag = false;
		try {
			PreparedStatement smt2 = con
					.prepareStatement("UPDATE `"+db+"`.precioVariableServicio set precio=? where id_bodegaEmpresa=? and id_servicio=? and fecha=? and id_cotiOdo=?;");
			smt2.setString(1, valor);
			smt2.setLong(2, id_bodegaEmpresa);
			smt2.setLong(3, id_servicio);
			smt2.setString(4, fecha);
			smt2.setLong(5, id_cotiOdo);
			smt2.executeUpdate();
			smt2.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean delete(Connection con, String db, Long id_bodegaEmpresa, Long id_servicio, String fecha, Long id_cotiOdo) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("delete from `"+db+"`.precioVariableServicio where id_bodegaEmpresa=? and id_servicio=? and fecha=? and id_cotiOdo=?;");
			smt.setLong(1, id_bodegaEmpresa);
			smt.setLong(2, id_servicio);
			smt.setString(3, fecha);
			smt.setLong(4, id_cotiOdo);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	
	public static Double buscaPrecio(Connection con, String db, Long id_bodegaEmpresa, Long id_servicio, String fecha, Long id_cotiOdo) {
		Double precio = null;
		try {
			PreparedStatement smt = con
					.prepareStatement("select precio from `"+db+"`.precioVariableServicio where id_bodegaEmpresa=? and id_servicio=? and fecha<=?  and id_cotiOdo=? order by fecha desc limit 1;");
			smt.setLong(1, id_bodegaEmpresa);
			smt.setLong(2, id_servicio);
			smt.setString(3, fecha);
			smt.setLong(4, id_cotiOdo);
			ResultSet rs = smt.executeQuery();
			if(rs.next()) {
				precio = rs.getDouble(1);
			}
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (precio);
	}
	
	

}
