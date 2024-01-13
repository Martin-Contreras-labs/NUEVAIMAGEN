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

import models.forms.FormTipoReparacionGraba;


public class TipoReparacion {
	public Long id;
	public Long id_tipoEstado;
	public Long id_moneda;
	public String sigla;
	public String nombre;
	public String descripcion;
	public String precio;
	
	public String moneda;
	public String siglaTipoEstado;
	public String nombreTipoEstado;
	
	public TipoReparacion(Long id, Long id_tipoEstado, Long id_moneda,
			String sigla, String nombre, String descripcion, String precio,
			String moneda, String siglaTipoEstado, String nombreTipoEstado) {
		super();
		this.id = id;
		this.id_tipoEstado = id_tipoEstado;
		this.id_moneda = id_moneda;
		this.sigla = sigla;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.moneda = moneda;
		this.siglaTipoEstado = siglaTipoEstado;
		this.nombreTipoEstado = nombreTipoEstado;
	}

	public TipoReparacion() {
		super();
	}
	
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getSigla() {return sigla;}
	public void setSigla(String sigla) {this.sigla = sigla;}
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	public Long getId_tipoEstado() {return id_tipoEstado;}
	public void setId_tipoEstado(Long id_tipoEstado) {this.id_tipoEstado = id_tipoEstado;}
	public String getDescripcion() {return descripcion;}
	public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
	public String getPrecio() {return precio;}
	public void setPrecio(String precio) {this.precio = precio;}
	public String getMoneda() {return moneda;}
	public void setMoneda(String moneda) {this.moneda = moneda;}
	public String getSiglaTipoEstado() {return siglaTipoEstado;}
	public void setSiglaTipoEstado(String siglaTipoEstado) {this.siglaTipoEstado = siglaTipoEstado;}
	public String getNombreTipoEstado() {return nombreTipoEstado;}
	public void setNombreTipoEstado(String nombreTipoEstado) {this.nombreTipoEstado = nombreTipoEstado;}
	public Long getId_moneda() {return id_moneda;}
	public void setId_moneda(Long id_moneda) {this.id_moneda = id_moneda;}



	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.0000000000");
	
	
	public static Map<String, TipoReparacion> mapAll(Connection con, String db) {
		Map<String, TipoReparacion> map = new HashMap<String, TipoReparacion>();
		List<TipoReparacion> all = TipoReparacion.all(con, db);
		for(TipoReparacion x: all) {
			map.put(x.getSigla()+"_"+x.getId_tipoEstado(), x);
		}
		return(map);
	}
	
	public static List<TipoReparacion> all(Connection con, String db) {
		List<TipoReparacion> lista = new ArrayList<TipoReparacion>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select " +
							" tipoReparacion.id," +
							" tipoReparacion.id_tipoEstado," +
							" tipoReparacion.id_moneda, " +
							" tipoReparacion.sigla," +
							" tipoReparacion.nombre, " +
							" tipoReparacion.descripcion," +
							" tipoReparacion.precio," +
							" moneda.nickName, " +
							" tipoEstado.sigla, " +
							" tipoEstado.nombre " +
							" from `"+db+"`.tipoReparacion " +
							" left join `"+db+"`.moneda on moneda.id = id_moneda " +
							" left join `"+db+"`.tipoEstado on tipoEstado.id = id_tipoEstado " +
							" order by tipoReparacion.nombre;");
			ResultSet rs = smt.executeQuery();
			Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
			while (rs.next()) {
				switch((dec.get(rs.getLong(3))).toString()) {
				 case "0": myformatdouble = new DecimalFormat("#,##0"); break;
				 case "2": myformatdouble = new DecimalFormat("#,##0.00"); break;
				 case "4": myformatdouble = new DecimalFormat("#,##0.0000"); break;
				 case "6": myformatdouble = new DecimalFormat("#,##0.000000"); break;
				 default:  break;
				}
				String precio = myformatdouble.format(rs.getDouble(7));
				lista.add(new TipoReparacion(rs.getLong(1),rs.getLong(2),rs.getLong(3),
						rs.getString(4),rs.getString(5),rs.getString(6),precio,rs.getString(8),
						rs.getString(9),rs.getString(10)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<TipoReparacion> allxIdTipoEstado(Connection con, String db,Long id_tipoEstado) {
		List<TipoReparacion> lista = new ArrayList<TipoReparacion>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select " +
							" tipoReparacion.id," +
							" tipoReparacion.id_tipoEstado," +
							" tipoReparacion.id_moneda, " +
							" tipoReparacion.sigla," +
							" tipoReparacion.nombre, " +
							" tipoReparacion.descripcion," +
							" tipoReparacion.precio," +
							" moneda.nickName, " +
							" tipoEstado.sigla, " +
							" tipoEstado.nombre " +
							" from `"+db+"`.tipoReparacion " +
							" left join `"+db+"`.moneda on moneda.id = id_moneda " +
							" left join `"+db+"`.tipoEstado on tipoEstado.id = id_tipoEstado " +
							" where id_tipoEstado = ? order by tipoReparacion.nombre;");
			smt.setLong(1, id_tipoEstado);
			ResultSet rs = smt.executeQuery();
			Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
			while (rs.next()) {
				switch((dec.get(rs.getLong(3))).toString()) {
				 case "0": myformatdouble = new DecimalFormat("#,##0"); break;
				 case "2": myformatdouble = new DecimalFormat("#,##0.00"); break;
				 case "4": myformatdouble = new DecimalFormat("#,##0.0000"); break;
				 case "6": myformatdouble = new DecimalFormat("#,##0.000000"); break;
				 default:  break;
				}
				String precio = myformatdouble.format(rs.getDouble(7));
				lista.add(new TipoReparacion(rs.getLong(1),rs.getLong(2),rs.getLong(3),
						rs.getString(4),rs.getString(5),rs.getString(6),precio,rs.getString(8),
						rs.getString(9),rs.getString(10)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static TipoReparacion find(Connection con, String db, Long id_tipoReparacion) {
		TipoReparacion aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement("select " +
							" tipoReparacion.id," +
							" tipoReparacion.id_tipoEstado," +
							" tipoReparacion.id_moneda, " +
							" tipoReparacion.sigla," +
							" tipoReparacion.nombre, " +
							" tipoReparacion.descripcion," +
							" tipoReparacion.precio," +
							" moneda.nickName, " +
							" tipoEstado.sigla, " +
							" tipoEstado.nombre " +
							" from `"+db+"`.tipoReparacion " +
							" left join `"+db+"`.moneda on moneda.id = id_moneda " +
							" left join `"+db+"`.tipoEstado on tipoEstado.id = id_tipoEstado " +
							" WHERE tipoReparacion.id=?;" );
			smt.setLong(1, id_tipoReparacion);
			ResultSet rs = smt.executeQuery();
			Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
			if (rs.next()) {
				switch((dec.get(rs.getLong(3))).toString()) {
				 case "0": myformatdouble = new DecimalFormat("#,##0"); break;
				 case "2": myformatdouble = new DecimalFormat("#,##0.00"); break;
				 case "4": myformatdouble = new DecimalFormat("#,##0.0000"); break;
				 case "6": myformatdouble = new DecimalFormat("#,##0.000000"); break;
				 default:  break;
				}
				String precio = myformatdouble.format(rs.getDouble(7));
				aux = new TipoReparacion(rs.getLong(1),rs.getLong(2),rs.getLong(3),
						rs.getString(4),rs.getString(5),rs.getString(6),precio,rs.getString(8),
						rs.getString(9),rs.getString(10));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (aux);
	}
	
	public static boolean existeSigla(Connection con, String db, String sigla, Long id_tipoEstado) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("select id from `"+db+"`.tipoReparacion where id_tipoEstado = ? and upper(sigla) = ?;" );
			smt.setLong(1, id_tipoEstado);
			smt.setString(2, sigla.toUpperCase().trim());
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				flag = true;
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean create(Connection con,String db, FormTipoReparacionGraba aux) {	
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("INSERT INTO `"+db+"`.tipoReparacion (id_tipoEstado,id_moneda,sigla," +
							" nombre,descripcion,precio) VALUES (?,?,?,?,?,?)");		
			smt.setLong(1,aux.id_tipoEstado);
			smt.setLong(2,aux.id_moneda);
			smt.setString(3,aux.sigla.trim());
			smt.setString(4,aux.nombre.trim());
			smt.setString(5,aux.descripcion.trim());
			smt.setDouble(6,aux.precio);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean estaEnUso(Connection con, String db, Long id_tipoReparacion) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("Select * FROM `"+db+"`.reparacionEquipo WHERE id_tipoReparacion = ?");
			smt.setLong(1, id_tipoReparacion);
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
	
	
	public static boolean delete(Connection con, String db, Long id_tipoReparacion) {
		boolean flag = false;
		try {
			PreparedStatement smt1 = con
					.prepareStatement("DELETE FROM `"+db+"`.tipoReparacion WHERE id = ?");
			smt1.setLong(1, id_tipoReparacion);
			smt1.executeUpdate();
			smt1.close();
			flag = true;
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (flag);
	}

	public static boolean modificaPorCampo(Connection con,String db,String campo,Long id_tipoReparacion,String valor) {
		boolean flag = false;
		try {
			PreparedStatement smt = con.prepareStatement("UPDATE `"+db+"`.tipoReparacion set `" + campo + "` = ? WHERE id = ?;");		
			smt.setString(1, valor.trim());
			smt.setLong(2, id_tipoReparacion);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
				e.printStackTrace();
			flag=false;
		}
		return (flag);
	}


	
	
	
	
}
