package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.utilities.DecimalFormato;




public class CotiOdoDetalle {
	public Long id;
	public Long id_cotiOdo;
	public Long id_servicio;
	public Long id_moneda;
	public String precio;
	public String cantidad;
	public Long aplicaMinimo;
	public String cantidadMinimo;
	public String precioAdicional;
	
	public String codigo;
	public String servicio;
	public String moneda;
	public String unidadServicio;
	
	public String totalVenta;

	public Long id_equipo;
	public String codigoEquipo;
	public String nombreEquipo;

	public CotiOdoDetalle(Long id, Long id_cotiOdo, Long id_servicio, Long id_moneda, String precio, String cantidad,
			Long aplicaMinimo, String cantidadMinimo, String precioAdicional, String codigo, String servicio, String moneda,
			String unidadServicio, String totalVenta, Long id_equipo, String codigoEquipo, String nombreEquipo) {
		super();
		this.id = id;
		this.id_cotiOdo = id_cotiOdo;
		this.id_servicio = id_servicio;
		this.id_moneda = id_moneda;
		this.precio = precio;
		this.cantidad = cantidad;
		this.aplicaMinimo = aplicaMinimo;
		this.cantidadMinimo = cantidadMinimo;
		this.precioAdicional = precioAdicional;
		this.codigo = codigo;
		this.servicio = servicio;
		this.moneda = moneda;
		this.unidadServicio = unidadServicio;
		this.totalVenta = totalVenta;
		this.id_equipo = id_equipo;
		this.codigoEquipo = codigoEquipo;
		this.nombreEquipo = nombreEquipo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId_cotiOdo() {
		return id_cotiOdo;
	}

	public void setId_cotiOdo(Long id_cotiOdo) {
		this.id_cotiOdo = id_cotiOdo;
	}

	public Long getId_servicio() {
		return id_servicio;
	}

	public void setId_servicio(Long id_servicio) {
		this.id_servicio = id_servicio;
	}

	public Long getId_moneda() {
		return id_moneda;
	}

	public void setId_moneda(Long id_moneda) {
		this.id_moneda = id_moneda;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public Long getAplicaMinimo() {
		return aplicaMinimo;
	}

	public void setAplicaMinimo(Long aplicaMinimo) {
		this.aplicaMinimo = aplicaMinimo;
	}

	public String getCantidadMinimo() {
		return cantidadMinimo;
	}

	public void setCantidadMinimo(String cantidadMinimo) {
		this.cantidadMinimo = cantidadMinimo;
	}

	public String getPrecioAdicional() {
		return precioAdicional;
	}

	public void setPrecioAdicional(String precioAdicional) {
		this.precioAdicional = precioAdicional;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getUnidadServicio() {
		return unidadServicio;
	}

	public void setUnidadServicio(String unidadServicio) {
		this.unidadServicio = unidadServicio;
	}

	public String getTotalVenta() {
		return totalVenta;
	}

	public void setTotalVenta(String totalVenta) {
		this.totalVenta = totalVenta;
	}

	public Long getId_equipo() {
		return id_equipo;
	}

	public void setId_equipo(Long id_equipo) {
		this.id_equipo = id_equipo;
	}

	public String getCodigoEquipo() {
		return codigoEquipo;
	}

	public void setCodigoEquipo(String codigoEquipo) {
		this.codigoEquipo = codigoEquipo;
	}

	public String getNombreEquipo() {
		return nombreEquipo;
	}

	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}

	public CotiOdoDetalle() {
		super();
	}


	public static Map<Long,CotiOdoDetalle> mapAll(Connection con, String db){
		Map<Long,CotiOdoDetalle> map = new HashMap<Long,CotiOdoDetalle>();
		List<CotiOdoDetalle> lista = CotiOdoDetalle.all(con, db);
		for(CotiOdoDetalle x: lista) {
			map.put(x.id, x);
		}
		return(map);
	}

	public static Map<String,CotiOdoDetalle> mapIdCotiOdoIdSevicio(Connection con, String db){
		Map<String,CotiOdoDetalle> map = new HashMap<String,CotiOdoDetalle>();
		List<CotiOdoDetalle> lista = CotiOdoDetalle.all(con, db);
		for(CotiOdoDetalle x: lista) {
			map.put(x.getId_cotiOdo()+"_"+x.getId_servicio(),x);
		}
		return(map);
	}

	public static Map<Long,CotiOdoDetalle> mapDetalleCotiOdo(Connection con, String db,Long id_cotiOdo){
		Map<Long,CotiOdoDetalle> mapDetalle = new HashMap<Long,CotiOdoDetalle>();
		List<CotiOdoDetalle> list = CotiOdoDetalle.allPorIdCotiOdo(con, db, id_cotiOdo);
		list.forEach(x -> mapDetalle.put(x.getId_servicio(), x));
		return(mapDetalle);
	}
	
	public static List<CotiOdoDetalle> all(Connection con, String db) {
		List<CotiOdoDetalle> lista = new ArrayList<CotiOdoDetalle>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select " 
							+ " cotiOdoDetalle.id, "
							+ "	cotiOdoDetalle.id_cotiOdo, "
							+ "	cotiOdoDetalle.id_servicio, "
							+ "	ifnull(cotiOdoDetalle.id_moneda,1), "
							+ "	cotiOdoDetalle.precio, "
							+ "	cotiOdoDetalle.cantidad, "
							+ "	cotiOdoDetalle.aplicaMinimo, "
							+ "	cotiOdoDetalle.cantidadMinimo, "
							+ "	cotiOdoDetalle.precioAdicional, "
							+ "	servicio.codigo, "
							+ "	servicio.nombre, "
							+ "	moneda.nickName, "
							+ "	unidadServicio.nombre, "
							+ "	cotiOdoDetalle.id_equipo, "
							+ "	ifnull(equipo.codigo,''), "
							+ "	ifnull(equipo.nombre,'') " +
							" from `"+db+"`.cotiOdoDetalle" +
							" left join `"+db+"`.servicio on servicio.id = cotiOdoDetalle.id_servicio " + 
							" left join `"+db+"`.moneda on moneda.id = cotiOdoDetalle.id_moneda " + 
							" left join `"+db+"`.unidadServicio on unidadServicio.id = servicio.id_unidadServicio " +
							" left join `"+db+"`.equipo on equipo.id = cotiOdoDetalle.id_equipo " +
							" order by servicio.nombre;");
			ResultSet rs = smt.executeQuery();
			Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
			while (rs.next()) {
				Long idMoneda = rs.getLong(4);
				Long numDec = dec.get(idMoneda);
				if(numDec == null) {
					numDec = (long)0;
				}
				String precio = DecimalFormato.formato(rs.getDouble(5),numDec);
				String cantidad = DecimalFormato.formato(rs.getDouble(6),(long)2);
				String totalVenta = DecimalFormato.formato((rs.getDouble(5)*rs.getDouble(6)), numDec);
				String cantidadMinimo = DecimalFormato.formato(rs.getDouble(8),(long)2);
				String precioAdicional = DecimalFormato.formato(rs.getDouble(9),numDec);
				lista.add(new CotiOdoDetalle(rs.getLong(1),rs.getLong(2),rs.getLong(3),
						rs.getLong(4),precio,cantidad,rs.getLong(7),cantidadMinimo,precioAdicional,
						rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),
						totalVenta,rs.getLong(14),rs.getString(15),rs.getString(16)));
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<CotiOdoDetalle> allPorIdCotiOdo(Connection con, String db, Long id_cotiOdo) {
		List<CotiOdoDetalle> lista = new ArrayList<CotiOdoDetalle>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select "  
							+ " cotiOdoDetalle.id, "
							+ "	cotiOdoDetalle.id_cotiOdo, "
							+ "	cotiOdoDetalle.id_servicio, "
							+ "	ifnull(cotiOdoDetalle.id_moneda,1), "
							+ "	cotiOdoDetalle.precio, "
							+ "	cotiOdoDetalle.cantidad, "
							+ "	cotiOdoDetalle.aplicaMinimo, "
							+ "	cotiOdoDetalle.cantidadMinimo, "
							+ "	cotiOdoDetalle.precioAdicional, "
							+ "	servicio.codigo, "
							+ "	servicio.nombre, "
							+ "	moneda.nickName, "
							+ "	unidadServicio.nombre, "
							+ "	cotiOdoDetalle.id_equipo, "
							+ "	ifnull(equipo.codigo,''), "
							+ "	ifnull(equipo.nombre,'') " +
							" from `"+db+"`.cotiOdoDetalle" + 
							" left join `"+db+"`.servicio on servicio.id = cotiOdoDetalle.id_servicio " + 
							" left join `"+db+"`.moneda on moneda.id = cotiOdoDetalle.id_moneda " + 
							" left join `"+db+"`.unidadServicio on unidadServicio.id = servicio.id_unidadServicio " +
							" left join `"+db+"`.equipo on equipo.id = cotiOdoDetalle.id_equipo " +
							" where cotiOdoDetalle.id_cotiOdo=?  order by servicio.nombre;");
			smt.setLong(1, id_cotiOdo);
			ResultSet rs = smt.executeQuery();
			Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
			while (rs.next()) {
				Long idMoneda = rs.getLong(4);
				Long numDec = dec.get(idMoneda);
				if(numDec == null) {
					numDec = (long)0;
				}
				String precio = DecimalFormato.formato(rs.getDouble(5),numDec);
				String cantidad = DecimalFormato.formato(rs.getDouble(6),(long)2);
				String totalVenta = DecimalFormato.formato((rs.getDouble(5)*rs.getDouble(6)), numDec);
				String cantidadMinimo = DecimalFormato.formato(rs.getDouble(8),(long)2);
				String precioAdicional = DecimalFormato.formato(rs.getDouble(9),numDec);
				lista.add(new CotiOdoDetalle(rs.getLong(1),rs.getLong(2),rs.getLong(3),
						rs.getLong(4),precio,cantidad,rs.getLong(7),cantidadMinimo,precioAdicional,
						rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),
						totalVenta,rs.getLong(14),rs.getString(15),rs.getString(16)));
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}

	public static boolean modifyXCampo(Connection con, String db, String campo, String valor, String id_cotiOdoDetalle) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("update `"+db+"`.cotiOdoDetalle set `"+campo+"` = ? WHERE id = ?;");
			smt.setString(1, valor.trim());
			smt.setString(2, id_cotiOdoDetalle);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean create (Connection con, String db, String detalle) {
		boolean flag = false;
		try {
			PreparedStatement smt1 = con
					.prepareStatement("insert into `"+db+"`.cotiOdoDetalle (id_cotiOdo, id_servicio, id_moneda, precio, cantidad, aplicaMinimo, cantidadMinimo, precioAdicional, id_equipo)"
							+ " values "+detalle+";");
			smt1.executeUpdate();
			smt1.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean delete (Connection con, String db, Long id_cotiOdo) {
		boolean flag = false;
		try {
			PreparedStatement smt1 = con
					.prepareStatement("delete from `"+db+"`.cotiOdoDetalle where id_cotiOdo = ?;");
			smt1.setLong(1, id_cotiOdo);
			smt1.executeUpdate();
			smt1.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static List<CotiOdoDetalle> allOrdenPorIdCoti(Connection con, String db) {
		List<CotiOdoDetalle> lista = new ArrayList<CotiOdoDetalle>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select " 
							+ " cotiOdoDetalle.id, "
							+ "	cotiOdoDetalle.id_cotiOdo, "
							+ "	cotiOdoDetalle.id_servicio, "
							+ "	ifnull(cotiOdoDetalle.id_moneda,1), "
							+ "	cotiOdoDetalle.precio, "
							+ "	cotiOdoDetalle.cantidad, "
							+ "	cotiOdoDetalle.aplicaMinimo, "
							+ "	cotiOdoDetalle.cantidadMinimo, "
							+ "	cotiOdoDetalle.precioAdicional, "
							+ "	servicio.codigo, "
							+ "	servicio.nombre, "
							+ "	moneda.nickName, "
							+ "	unidadServicio.nombre, "
							+ "	cotiOdoDetalle.id_equipo, "
							+ "	ifnull(equipo.codigo,''), "
							+ "	ifnull(equipo.nombre,'') " +
							" from `"+db+"`.cotiOdoDetalle" + 
							" left join `"+db+"`.servicio on servicio.id = cotiOdoDetalle.id_servicio " + 
							" left join `"+db+"`.moneda on moneda.id = cotiOdoDetalle.id_moneda " + 
							" left join `"+db+"`.unidadServicio on unidadServicio.id = servicio.id_unidadServicio " +
							" left join `"+db+"`.equipo on equipo.id = cotiOdoDetalle.id_equipo " +
							" order by cotiOdoDetalle.id_cotiOdo;");
			ResultSet rs = smt.executeQuery();
			Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
			while (rs.next()) {
				Long idMoneda = rs.getLong(4);
				Long numDec = dec.get(idMoneda);
				if(numDec == null) {
					numDec = (long)0;
				}
				String precio = DecimalFormato.formato(rs.getDouble(5),numDec);
				String cantidad = DecimalFormato.formato(rs.getDouble(6),(long)2);
				String totalVenta = DecimalFormato.formato((rs.getDouble(5)*rs.getDouble(6)), numDec);
				String cantidadMinimo = DecimalFormato.formato(rs.getDouble(8),(long)2);
				String precioAdicional = DecimalFormato.formato(rs.getDouble(9),numDec);
				lista.add(new CotiOdoDetalle(rs.getLong(1),rs.getLong(2),rs.getLong(3),
						rs.getLong(4),precio,cantidad,rs.getLong(7),cantidadMinimo,precioAdicional,
						rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),
						totalVenta,rs.getLong(14),rs.getString(15),rs.getString(16)));
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	
	
	
}









