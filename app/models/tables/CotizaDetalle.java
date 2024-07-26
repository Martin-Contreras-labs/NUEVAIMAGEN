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




public class CotizaDetalle {
	public Long id;
	public Long id_cotizacion;
	public Long id_equipo;
	public Long id_moneda;
	public String precioVenta;
	public String precioReposicion;
	public String precioArriendo;
	public Long id_unidadTiempo;
	public String cantidad;
	public Double permanencia;
	public Long esVenta;
	
	
	public String codigo;
	public String equipo;
	public String moneda;
	public String unidadTiempo;
	public String unidad;
	
	public String totalReposicion;
	public String totalArriendo;
	public String totalVenta;
	
	public String totalKG;
	public String totalM2;
	
	public Long id_grupo;
	public String grupo;
	
	public CotizaDetalle(Long id, Long id_cotizacion, Long id_equipo, Long id_moneda, String precioVenta,
			String precioReposicion, String precioArriendo, Long id_unidadTiempo, String cantidad, Double permanencia,
			Long esVenta, String codigo, String equipo, String moneda, String unidadTiempo, String unidad,
			String totalReposicion, String totalArriendo, String totalVenta, String totalKG, String totalM2, Long id_grupo, String grupo) {
		super();
		this.id = id;
		this.id_cotizacion = id_cotizacion;
		this.id_equipo = id_equipo;
		this.id_moneda = id_moneda;
		this.precioVenta = precioVenta;
		this.precioReposicion = precioReposicion;
		this.precioArriendo = precioArriendo;
		this.id_unidadTiempo = id_unidadTiempo;
		this.cantidad = cantidad;
		this.permanencia = permanencia;
		this.esVenta = esVenta;
		this.codigo = codigo;
		this.equipo = equipo;
		this.moneda = moneda;
		this.unidadTiempo = unidadTiempo;
		this.unidad = unidad;
		this.totalReposicion = totalReposicion;
		this.totalArriendo = totalArriendo;
		this.totalVenta = totalVenta;
		this.totalKG = totalKG;
		this.totalM2 = totalM2;
		this.id_grupo = id_grupo;
		this.grupo = grupo;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Long getId_cotizacion() {
		return id_cotizacion;
	}



	public void setId_cotizacion(Long id_cotizacion) {
		this.id_cotizacion = id_cotizacion;
	}



	public Long getId_equipo() {
		return id_equipo;
	}



	public void setId_equipo(Long id_equipo) {
		this.id_equipo = id_equipo;
	}



	public Long getId_moneda() {
		return id_moneda;
	}



	public void setId_moneda(Long id_moneda) {
		this.id_moneda = id_moneda;
	}



	public String getPrecioVenta() {
		return precioVenta;
	}



	public void setPrecioVenta(String precioVenta) {
		this.precioVenta = precioVenta;
	}



	public String getPrecioReposicion() {
		return precioReposicion;
	}



	public void setPrecioReposicion(String precioReposicion) {
		this.precioReposicion = precioReposicion;
	}



	public String getPrecioArriendo() {
		return precioArriendo;
	}



	public void setPrecioArriendo(String precioArriendo) {
		this.precioArriendo = precioArriendo;
	}



	public Long getId_unidadTiempo() {
		return id_unidadTiempo;
	}



	public void setId_unidadTiempo(Long id_unidadTiempo) {
		this.id_unidadTiempo = id_unidadTiempo;
	}



	public String getCantidad() {
		return cantidad;
	}



	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}



	public Double getPermanencia() {
		return permanencia;
	}



	public void setPermanencia(Double permanencia) {
		this.permanencia = permanencia;
	}



	public Long getEsVenta() {
		return esVenta;
	}



	public void setEsVenta(Long esVenta) {
		this.esVenta = esVenta;
	}



	public String getCodigo() {
		return codigo;
	}



	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}



	public String getEquipo() {
		return equipo;
	}



	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}



	public String getMoneda() {
		return moneda;
	}



	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}



	public String getUnidadTiempo() {
		return unidadTiempo;
	}



	public void setUnidadTiempo(String unidadTiempo) {
		this.unidadTiempo = unidadTiempo;
	}



	public String getUnidad() {
		return unidad;
	}



	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}



	public String getTotalReposicion() {
		return totalReposicion;
	}



	public void setTotalReposicion(String totalReposicion) {
		this.totalReposicion = totalReposicion;
	}



	public String getTotalArriendo() {
		return totalArriendo;
	}



	public void setTotalArriendo(String totalArriendo) {
		this.totalArriendo = totalArriendo;
	}



	public String getTotalVenta() {
		return totalVenta;
	}



	public void setTotalVenta(String totalVenta) {
		this.totalVenta = totalVenta;
	}



	public String getTotalKG() {
		return totalKG;
	}



	public void setTotalKG(String totalKG) {
		this.totalKG = totalKG;
	}



	public String getTotalM2() {
		return totalM2;
	}



	public void setTotalM2(String totalM2) {
		this.totalM2 = totalM2;
	}


	public String getGrupo() {
		return grupo;
	}


	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}


	public Long getId_grupo() {
		return id_grupo;
	}



	public void setId_grupo(Long id_grupo) {
		this.id_grupo = id_grupo;
	}



	public CotizaDetalle() {
		super();
	}

	static SimpleDateFormat myformatfecha = new SimpleDateFormat("dd-MM-yyyy");
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	static DecimalFormat myformattasa = new DecimalFormat("#,##0.00 %");
	

	public static Map<Long,CotizaDetalle> mapAll(Connection con, String db){
		Map<Long,CotizaDetalle> map = new HashMap<Long,CotizaDetalle>();
		List<CotizaDetalle> lista = CotizaDetalle.all(con, db);
		for(CotizaDetalle x: lista) {
			map.put(x.id, x);
		}
		return(map);
	}
	
	public static List<CotizaDetalle> all(Connection con, String db) {
		List<CotizaDetalle> lista = new ArrayList<CotizaDetalle>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select "
							+ " cotizaDetalle.id,"
							+ " id_cotizacion,"
							+ " id_equipo,"
							+ " id_moneda,"
							+ " precioVenta,"
							+ " precioReposicion,"
							+ " precioArriendo,"
							+ " id_unidadTiempo,"
							+ " cantidad,"
							+ " permanencia,"
							+ " esVenta,"
							+ " equipo.codigo,"
							+ " equipo.nombre,"
							+ " moneda.nickName,"
							+ " unidadTiempo.nombre,"
							+ " unidad.nombre,"
							+ " equipo.id_grupo,"
							+ " ifnull(grupo.nombre,''),"
							+ " equipo.kg,"
							+ " equipo.m2" + 
							" from `"+db+"`.cotizaDetalle" + 
							" left join `"+db+"`.equipo on equipo.id=cotizaDetalle.id_equipo" + 
							" left join `"+db+"`.moneda on moneda.id=cotizaDetalle.id_moneda" + 
							" left join `"+db+"`.unidadTiempo on unidadTiempo.id=cotizaDetalle.id_unidadTiempo" + 
							" left join `"+db+"`.unidad on unidad.id = equipo.id_unidad " +
							" left join `"+db+"`.grupo on equipo.id_grupo = grupo.id " +
							" order by equipo.nombre;");
			ResultSet rs = smt.executeQuery();
			
			Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
			
			while (rs.next()) {
				Long idMoneda = (long) 1;
				if(rs.getString(4)!=null) idMoneda=rs.getLong(4);
				switch((dec.get(idMoneda)).toString()) {
				 case "0": myformatdouble = new DecimalFormat("#,##0"); break;
				 case "2": myformatdouble = new DecimalFormat("#,##0.00"); break;
				 case "4": myformatdouble = new DecimalFormat("#,##0.0000"); break;
				 case "6": myformatdouble = new DecimalFormat("#,##0.000000"); break;
				 default:  break;
				}
				
				Double kg = rs.getDouble(19);
				Double m2 = rs.getDouble(20);
				
				String totalReposicion = myformatdouble.format(rs.getDouble(9)*rs.getDouble(6));
				String totalArriendo = myformatdouble.format(rs.getDouble(9)*rs.getDouble(7)*rs.getDouble(10));
				String totalVenta = myformatdouble.format(rs.getDouble(9)*rs.getDouble(5));
				
				String totalKg = myformatdouble2.format(rs.getDouble(9)*kg);
				String totalM2 = myformatdouble2.format(rs.getDouble(9)*m2);
				
				String cantidad = myformatdouble2.format(rs.getDouble(9));
				
				if(rs.getLong(11)==0) {
					totalVenta = myformatdouble.format((double)0);
				}else {
					totalArriendo = myformatdouble.format((double)0);
				}
				
				lista.add(new CotizaDetalle(rs.getLong(1),rs.getLong(2),rs.getLong(3),
						rs.getLong(4),myformatdouble.format(rs.getDouble(5)),myformatdouble.format(rs.getDouble(6)),
						myformatdouble.format(rs.getDouble(7)),rs.getLong(8),cantidad,rs.getDouble(10),rs.getLong(11),
						rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15),rs.getString(16),totalReposicion,
						totalArriendo,totalVenta,totalKg,totalM2,rs.getLong(17),rs.getString(18)));
				
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<CotizaDetalle> allPorIdCotizacion(Connection con, String db,Long idCotizacion) {
		List<CotizaDetalle> lista = new ArrayList<CotizaDetalle>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select "
							+ " cotizaDetalle.id,"
							+ " id_cotizacion,"
							+ " id_equipo,"
							+ " id_moneda,"
							+ " precioVenta,"
							+ " precioReposicion,"
							+ " precioArriendo,"
							+ " id_unidadTiempo,"
							+ " cantidad,"
							+ " permanencia,"
							+ " esVenta,"
							+ " equipo.codigo,"
							+ " equipo.nombre,"
							+ " moneda.nickName,"
							+ " unidadTiempo.nombre,"
							+ " unidad.nombre,"
							+ " equipo.id_grupo,"
							+ " ifnull(grupo.nombre,''),"
							+ " equipo.kg,"
							+ " equipo.m2" + 
							" from `"+db+"`.cotizaDetalle" + 
							" left join `"+db+"`.equipo on equipo.id=cotizaDetalle.id_equipo" + 
							" left join `"+db+"`.moneda on moneda.id=cotizaDetalle.id_moneda" + 
							" left join `"+db+"`.unidadTiempo on unidadTiempo.id=cotizaDetalle.id_unidadTiempo" + 
							" left join `"+db+"`.unidad on unidad.id = equipo.id_unidad " +
							" left join `"+db+"`.grupo on equipo.id_grupo = grupo.id " +
							" where id_cotizacion=?  order by equipo.nombre;");
			smt.setLong(1, idCotizacion);

			ResultSet rs = smt.executeQuery();
			Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
			
			while (rs.next()) {
				Long idMoneda = (long) 1;
				if(rs.getString(4)!=null) idMoneda=rs.getLong(4);
				switch((dec.get(idMoneda)).toString()) {
				 case "0": myformatdouble = new DecimalFormat("#,##0"); break;
				 case "2": myformatdouble = new DecimalFormat("#,##0.00"); break;
				 case "4": myformatdouble = new DecimalFormat("#,##0.0000"); break;
				 case "6": myformatdouble = new DecimalFormat("#,##0.000000"); break;
				 default:  break;
				}
				
				Double kg = rs.getDouble(19);
				Double m2 = rs.getDouble(20);
				
				String totalReposicion = myformatdouble.format(rs.getDouble(9)*rs.getDouble(6));
				String totalArriendo = myformatdouble.format(rs.getDouble(9)*rs.getDouble(7)*rs.getDouble(10));
				String totalVenta = myformatdouble.format(rs.getDouble(9)*rs.getDouble(5));
				
				String totalKg = myformatdouble2.format(rs.getDouble(9)*kg);
				String totalM2 = myformatdouble2.format(rs.getDouble(9)*m2);
				
				String cantidad = myformatdouble2.format(rs.getDouble(9));
				
				if(rs.getLong(11)==0) {
					totalVenta = myformatdouble.format((double)0);
				}else {
					totalArriendo = myformatdouble.format((double)0);
				}
				
				lista.add(new CotizaDetalle(rs.getLong(1),rs.getLong(2),rs.getLong(3),
						rs.getLong(4),myformatdouble.format(rs.getDouble(5)),myformatdouble.format(rs.getDouble(6)),
						myformatdouble.format(rs.getDouble(7)),rs.getLong(8),cantidad,rs.getDouble(10),rs.getLong(11),
						rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15),rs.getString(16),totalReposicion,
						totalArriendo,totalVenta,totalKg,totalM2,rs.getLong(17),rs.getString(18)));
				
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static boolean create (Connection con, String db, String detalle) {
		boolean flag = false;
		try {
			PreparedStatement smt1 = con
					.prepareStatement("insert into `"+db+"`.cotizaDetalle (id_cotizacion, id_equipo, id_moneda, precioVenta, precioReposicion, precioArriendo, id_unidadTiempo, cantidad, permanencia, esVenta)"
							+ " values "+detalle+";");
			smt1.executeUpdate();
			smt1.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean delete (Connection con, String db, Long id_cotizacion) {
		boolean flag = false;
		try {
			PreparedStatement smt1 = con
					.prepareStatement("delete from `"+db+"`.cotizaDetalle where id_cotizacion = ?;");
			smt1.setLong(1, id_cotizacion);
			smt1.executeUpdate();
			smt1.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static List<CotizaDetalle> allOrdenPorIdCoti(Connection con, String db) {
		List<CotizaDetalle> lista = new ArrayList<CotizaDetalle>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select " 
							+ " cotizaDetalle.id,"
							+ " id_cotizacion,"
							+ " id_equipo,"
							+ " id_moneda,"
							+ " precioVenta,"
							+ " precioReposicion,"
							+ " precioArriendo,"
							+ " id_unidadTiempo,"
							+ " cantidad,"
							+ " permanencia,"
							+ " esVenta,"
							+ " equipo.codigo,"
							+ " equipo.nombre,"
							+ " moneda.nickName,"
							+ " unidadTiempo.nombre,"
							+ " unidad.nombre,"
							+ " equipo.id_grupo,"
							+ " ifnull(grupo.nombre,''),"
							+ " equipo.kg,"
							+ " equipo.m2" + 
							" from `"+db+"`.cotizaDetalle" + 
							" left join `"+db+"`.equipo on equipo.id=cotizaDetalle.id_equipo" + 
							" left join `"+db+"`.moneda on moneda.id=cotizaDetalle.id_moneda" + 
							" left join `"+db+"`.unidadTiempo on unidadTiempo.id=cotizaDetalle.id_unidadTiempo" + 
							" left join `"+db+"`.unidad on unidad.id = equipo.id_unidad " +
							" left join `"+db+"`.grupo on equipo.id_grupo = grupo.id " +
							" order by id_cotizacion;");
			ResultSet rs = smt.executeQuery();
			
			Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
			
			while (rs.next()) {
				Long idMoneda = (long) 1;
				if(rs.getString(4)!=null) idMoneda=rs.getLong(4);
				switch((dec.get(idMoneda)).toString()) {
				 case "0": myformatdouble = new DecimalFormat("#,##0"); break;
				 case "2": myformatdouble = new DecimalFormat("#,##0.00"); break;
				 case "4": myformatdouble = new DecimalFormat("#,##0.0000"); break;
				 case "6": myformatdouble = new DecimalFormat("#,##0.000000"); break;
				 default:  break;
				}
				
				Double kg = rs.getDouble(19);
				Double m2 = rs.getDouble(20);
				
				String totalReposicion = myformatdouble.format(rs.getDouble(9)*rs.getDouble(6));
				String totalArriendo = myformatdouble.format(rs.getDouble(9)*rs.getDouble(7)*rs.getDouble(10));
				String totalVenta = myformatdouble.format(rs.getDouble(9)*rs.getDouble(5));
				
				String totalKg = myformatdouble2.format(rs.getDouble(9)*kg);
				String totalM2 = myformatdouble2.format(rs.getDouble(9)*m2);
				
				String cantidad = myformatdouble2.format(rs.getDouble(9));
				
				if(rs.getLong(11)==0) {
					totalVenta = myformatdouble.format((double)0);
				}else {
					totalArriendo = myformatdouble.format((double)0);
				}
				
				lista.add(new CotizaDetalle(rs.getLong(1),rs.getLong(2),rs.getLong(3),
						rs.getLong(4),myformatdouble.format(rs.getDouble(5)),myformatdouble.format(rs.getDouble(6)),
						myformatdouble.format(rs.getDouble(7)),rs.getLong(8),cantidad,rs.getDouble(10),rs.getLong(11),
						rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15),rs.getString(16),totalReposicion,
						totalArriendo,totalVenta,totalKg,totalM2,rs.getLong(17),rs.getString(18)));
				
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static Map<String,Double> mapAllCotizadoConOt(Connection con, String db, String esPorSucursal, String id_sucursal) {
		Map<String,Double> map = new HashMap<String,Double>();
		
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and bodegaEmpresa.id_sucursal = " + id_sucursal;
		}
		
		try {
			PreparedStatement smt = con
					.prepareStatement("select  cotizacion.id_bodegaEmpresa, cotizaDetalle.id_equipo, sum(cotizaDetalle.cantidad), ot.id_otEstado "
							+ " from `"+db+"`.cotizaDetalle "
							+ " left join `"+db+"`.cotizacion on cotizacion.id = cotizaDetalle.id_cotizacion "
							+ " left join `"+db+"`.ot on ot.id = cotizacion.id_ot "
							+ " left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = cotizacion.id_bodegaEmpresa " + condSucursal
							+ " where cotizacion.id_ot > 0 and cotizacion.id_bodegaEmpresa > 0 and ot.id_otEstado = 1 "
							+ " group by cotizacion.id_bodegaEmpresa,cotizaDetalle.id_equipo;");
			ResultSet rs = smt.executeQuery();
			
			while(rs.next()) {
				map.put(rs.getString(1)+"_"+rs.getString(2), rs.getDouble(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(map);
	}
	
	public static Map<String,Double> mapAllCotizadoConOtPorOt(Connection con, String db, String esPorSucursal, String id_sucursal) {
		Map<String,Double> map = new HashMap<String,Double>();
		
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and bodegaEmpresa.id_sucursal = " + id_sucursal;
		}
		
		try {
			PreparedStatement smt = con
					.prepareStatement("select  cotizacion.id_bodegaEmpresa, cotizaDetalle.id_equipo, cotizacion.id_ot, sum(cotizaDetalle.cantidad), ot.id_otEstado "
							+ " from `"+db+"`.cotizaDetalle "
							+ " left join `"+db+"`.cotizacion on cotizacion.id = cotizaDetalle.id_cotizacion "
							+ " left join `"+db+"`.ot on ot.id = cotizacion.id_ot "
							+ " left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = cotizacion.id_bodegaEmpresa " + condSucursal
							+ " where cotizacion.id_ot > 0 and cotizacion.id_bodegaEmpresa > 0 and ot.id_otEstado = 1 "
							+ " group by cotizacion.id_bodegaEmpresa,cotizaDetalle.id_equipo, cotizacion.id_ot;");
			ResultSet rs = smt.executeQuery();

			while(rs.next()) {
				map.put(rs.getString(1)+"_"+rs.getString(2)+"_"+rs.getString(3), rs.getDouble(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(map);
	}
	
	
	public static Map<Long,Double> mapIdCotiVsSaldo(Connection con, String db) {
		Map<Long, Double> map = new HashMap<Long,Double>();
		try {
			Map<Long, Double> mapDespachos = new HashMap<Long,Double>();
			PreparedStatement smt = con
					.prepareStatement("select otDespachado.id_cotizacion, sum(otDespachado.cantidadRebajaOt) " +
							" from `"+db+"`.otDespachado" + 
							" group by otDespachado.id_cotizacion;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				mapDespachos.put(rs.getLong(1), rs.getDouble(2));
			}
			rs.close();
			smt.close();
			
			PreparedStatement smt2 = con
					.prepareStatement("select " + 
							" cotizaDetalle.id_cotizacion, sum(cotizaDetalle.cantidad) " + 
							" from `"+db+"`.cotizaDetalle" + 
							" group by cotizaDetalle.id_cotizacion;");
			ResultSet rs2 = smt2.executeQuery();
			while (rs2.next()) {
				Double cantCoti = rs2.getDouble(2);
				Double cantDesp = mapDespachos.get(rs2.getLong(1));
				if(cantDesp == null) {
					map.put(rs2.getLong(1), cantCoti);
				}else {
					map.put(rs2.getLong(1), cantCoti - cantDesp);
				}
			}
			rs2.close();
			smt2.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (map);
	}
	
	
}









