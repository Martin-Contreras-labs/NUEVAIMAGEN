package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;



public class DctoEquipo {
	public Long id_bodegaEmpresa;
	public Long id_equipo;
	public Double tasaDescto;
	public String codigoEquipo;
	public String nombreEquipo;
	public Long id_cotizacion;
	
	public Long numeroCotizacion;

	public DctoEquipo(Long id_bodegaEmpresa, Long id_equipo, Double tasaDescto,
			String codigoEquipo, String nombreEquipo, Long id_cotizacion,Long numeroCotizacion) {
		super();
		this.id_bodegaEmpresa = id_bodegaEmpresa;
		this.id_equipo = id_equipo;
		this.tasaDescto = tasaDescto;
		this.codigoEquipo = codigoEquipo;
		this.nombreEquipo = nombreEquipo;
		this.id_cotizacion = id_cotizacion;
		this.numeroCotizacion = numeroCotizacion;
	}

	public DctoEquipo() {super();}

	public Long getId_bodegaEmpresa() {return id_bodegaEmpresa;}
	public void setId_bodegaEmpresa(Long id_bodegaEmpresa) {this.id_bodegaEmpresa = id_bodegaEmpresa;}
	public Long getId_equipo() {return id_equipo;}
	public void setId_equipo(Long id_equipo) {this.id_equipo = id_equipo;}
	public Double getTasaDescto() {return tasaDescto;}
	public void setTasaDescto(Double tasaDescto) {this.tasaDescto = tasaDescto;}
	public String getCodigoEquipo() {return codigoEquipo;}
	public void setCodigoEquipo(String codigoEquipo) {this.codigoEquipo = codigoEquipo;}
	public String getNombreEquipo() {return nombreEquipo;}
	public void setNombreEquipo(String nombreEquipo) {this.nombreEquipo = nombreEquipo;}
	public Long getId_cotizacion() {return id_cotizacion;}
	public void setId_cotizacion(Long id_cotizacion) {this.id_cotizacion = id_cotizacion;}
	public Long getNumeroCotizacion() {return numeroCotizacion;}
	public void setNumeroCotizacion(Long numeroCotizacion) {this.numeroCotizacion = numeroCotizacion;}

	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	
	
	
	public static List<DctoEquipo> allXBodegaEmpresa(Connection con, String db, Long id_bodegaEmpresa) {
		List<DctoEquipo> lista = new ArrayList<DctoEquipo>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select dctoEquipo.id_bodegaEmpresa,dctoEquipo.id_equipo,dctoEquipo.tasaDescto,"+
							" equipo.codigo,equipo.nombre,dctoEquipo.id_cotizacion,cotizacion.numero " +
							" from `"+db+"`.dctoEquipo  " +
							" left join `"+db+"`.equipo on equipo.id = dctoEquipo.id_equipo " +
							" left join `"+db+"`.cotizacion on cotizacion.id = dctoEquipo.id_cotizacion " +
							" where dctoEquipo.id_bodegaEmpresa = ? "+
							" order by equipo.nombre;");
			smt.setLong(1, id_bodegaEmpresa);
			ResultSet resultado = smt.executeQuery();
			while (resultado.next()) {
				lista.add(new DctoEquipo(resultado.getLong(1),resultado.getLong(2),resultado.getDouble(3),resultado.getString(4),
						resultado.getString(5),resultado.getLong(6),resultado.getLong(7)));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static boolean modificar(Connection con, String db, Long id_bodegaEmpresa,  Long id_equipo, Long id_cotizacion, Double tasaDcto) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("UPDATE `"+db+"`.dctoEquipo SET tasaDescto = ? "+
							" WHERE id_bodegaEmpresa = ? and id_equipo = ? and id_cotizacion = ?;");
			smt.setDouble(1, tasaDcto);
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
	
	public static boolean eliminar(Connection con, String db, Long id_bodegaEmpresa,  Long id_equipo, Long id_cotizacion) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("delete from `"+db+"`.dctoEquipo  "+
							" WHERE id_bodegaEmpresa = ? and id_equipo = ? and id_cotizacion = ?;");
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
	
	public static boolean agregar(Connection con, String db, Long id_bodegaEmpresa,  Long id_equipo, Long id_cotizacion) {	
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("INSERT INTO `"+db+"`.dctoEquipo (id_bodegaEmpresa,id_equipo,id_cotizacion,tasaDescto) VALUES (?,?,?,?);");		
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
	
	



}
