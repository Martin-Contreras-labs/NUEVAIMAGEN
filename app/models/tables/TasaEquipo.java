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


public class TasaEquipo {
	public Long id_bodegaEmpresa;
	public Long id_equipo;
	public Double tasaArriendo;
	public String codigoEquipo;
	public String nombreEquipo;
	public Long id_cotizacion;
	
	public Long numeroCotizacion;

	public TasaEquipo(Long id_bodegaEmpresa, Long id_equipo, Double tasaArriendo, String codigoEquipo,
			String nombreEquipo, Long id_cotizacion, Long numeroCotizacion) {
		super();
		this.id_bodegaEmpresa = id_bodegaEmpresa;
		this.id_equipo = id_equipo;
		this.tasaArriendo = tasaArriendo;
		this.codigoEquipo = codigoEquipo;
		this.nombreEquipo = nombreEquipo;
		this.id_cotizacion = id_cotizacion;
		this.numeroCotizacion = numeroCotizacion;
	}

	public TasaEquipo() {super();}

	public Long getId_bodegaEmpresa() {return id_bodegaEmpresa;}
	public void setId_bodegaEmpresa(Long id_bodegaEmpresa) {this.id_bodegaEmpresa = id_bodegaEmpresa;}
	public Long getId_equipo() {return id_equipo;}
	public void setId_equipo(Long id_equipo) {this.id_equipo = id_equipo;}
	public Double getTasaArriendo() {return tasaArriendo;}
	public void setTasaArriendo(Double tasaArriendo) {this.tasaArriendo = tasaArriendo;}
	public String getCodigoEquipo() {return codigoEquipo;}
	public void setCodigoEquipo(String codigoEquipo) {this.codigoEquipo = codigoEquipo;}
	public String getNombreEquipo() {return nombreEquipo;}
	public void setNombreEquipo(String nombreEquipo) {this.nombreEquipo = nombreEquipo;}
	public Long getId_cotizacion() {return id_cotizacion;}
	public void setId_cotizacion(Long id_cotizacion) {this.id_cotizacion = id_cotizacion;}
	public Long getNumeroCotizacion() {return numeroCotizacion;}
	public void setNumeroCotizacion(Long numeroCotizacion) {this.numeroCotizacion = numeroCotizacion;}

	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	
	
	public static Map<String,TasaEquipo> mapAllXBodegaEmpresa(Connection con, String db, Long id_bodegaEmpresa) {
		Map<String,TasaEquipo> map = new HashMap<String,TasaEquipo>();
		List<TasaEquipo> lista = TasaEquipo.allXBodegaEmpresa(con, db, id_bodegaEmpresa);
		lista.forEach(x->{
			map.put(x.getId_bodegaEmpresa()+"_"+x.getId_equipo(), x);
		});
		return(map);
	}
	
	public static List<TasaEquipo> allXBodegaEmpresa(Connection con, String db, Long id_bodegaEmpresa) {
		List<TasaEquipo> lista = new ArrayList<TasaEquipo>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select tasaEquipo.id_bodegaEmpresa,tasaEquipo.id_equipo,tasaEquipo.tasaArriendo,equipo.codigo,equipo.nombre,"+
							" tasaEquipo.id_cotizacion,cotizacion.numero " +
							" from `"+db+"`.tasaEquipo  " +
							" left join `"+db+"`.equipo on equipo.id = tasaEquipo.id_equipo " +
							" left join `"+db+"`.cotizacion on cotizacion.id = tasaEquipo.id_cotizacion " +
							" where tasaEquipo.id_bodegaEmpresa = ? "+
							" order by equipo.nombre;");
			smt.setLong(1, id_bodegaEmpresa);
			ResultSet resultado = smt.executeQuery();
			while (resultado.next()) {
				lista.add(new TasaEquipo(resultado.getLong(1),resultado.getLong(2),resultado.getDouble(3),resultado.getString(4),resultado.getString(5),
						resultado.getLong(6),resultado.getLong(7)));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static boolean modificar(Connection con, String db, Long id_bodegaEmpresa, Long id_equipo, Long id_cotizacion, Double tasa) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("UPDATE `"+db+"`.tasaEquipo SET tasaArriendo = ? " +
							" WHERE id_bodegaEmpresa = ? and id_equipo = ? and id_cotizacion = ?");
			smt.setDouble(1, tasa);
			smt.setLong(2, id_bodegaEmpresa);
			smt.setLong(3, id_equipo);
			smt.setLong(4, id_cotizacion);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean eliminar(Connection con,String db, Long id_bodegaEmpresa,  Long id_equipo, Long id_cotizacion) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("delete from `"+db+"`.tasaEquipo " +
							" WHERE id_bodegaEmpresa = ? and id_equipo = ? and id_cotizacion = ?");
			smt.setLong(1, id_bodegaEmpresa);
			smt.setLong(2, id_equipo);
			smt.setLong(3, id_cotizacion);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean agregar(Connection con,String db, Long id_bodegaEmpresa,  Long id_equipo, Long id_cotizacion) {	
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("INSERT INTO `"+db+"`.tasaEquipo (id_bodegaEmpresa,id_equipo,id_cotizacion,tasaArriendo) VALUES (?,?,?,?);");		
			smt.setLong(1, id_bodegaEmpresa);
			smt.setLong(2, id_equipo);
			smt.setLong(3, id_cotizacion);
			smt.setDouble(4, (double)0);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	
	public static Map<String,TasaEquipo> mapTasaPorBodegaEquipo(Connection con, String db) {
		Map<String,TasaEquipo> map = new HashMap<String,TasaEquipo>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select id_bodegaEmpresa, id_equipo, tasaArriendo from `"+db+"`.tasaEquipo;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				TasaEquipo tasaEquipo = new TasaEquipo(rs.getLong(1),rs.getLong(2),rs.getDouble(3),"","",(long)0,(long)0);
				map.put(rs.getString(1)+"_"+rs.getString(2), tasaEquipo);
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (map);
	}
	



}
