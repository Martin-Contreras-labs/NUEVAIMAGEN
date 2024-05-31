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

import models.forms.FormTipoEstadoGraba;


public class TipoEstado {
	public Long id;
	public String sigla;
	public String nombre;
	public Long reparable;
	public Long id_bodegaAsociada; // Indica bodega asociada, siempre es interna.
	
	public String bodegaAsociada;
	public Long valoriza;

	public TipoEstado(Long id, String sigla, String nombre, Long reparable, Long id_bodegaAsociada, String bodegaAsociada, Long valoriza) {
		super();
		this.id = id;
		this.sigla = sigla;
		this.nombre = nombre;
		this.reparable = reparable;
		this.id_bodegaAsociada = id_bodegaAsociada;
		this.bodegaAsociada = bodegaAsociada;
		this.valoriza = valoriza;
	}

	public TipoEstado() {
		super();
	}
	
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getSigla() {return sigla;}
	public void setSigla(String sigla) {this.sigla = sigla;}
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}	
	public Long getReparable() {return reparable;}
	public void setReparable(Long reparable) {this.reparable = reparable;}
	public Long getId_bodegaAsociada() {return id_bodegaAsociada;}
	public void setId_bodegaAsociada(Long id_bodegaAsociada) {this.id_bodegaAsociada = id_bodegaAsociada;}
	public String getBodegaAsociada() {return bodegaAsociada;}
	public void setBodegaAsociada(String bodegaAsociada) {this.bodegaAsociada = bodegaAsociada;}
	public Long getValoriza() {return valoriza;}
	public void setValoriza(Long valoriza) {this.valoriza = valoriza;}


	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble4 = new DecimalFormat("#,##0.0000");
	
	static SimpleDateFormat myformatfecha = new SimpleDateFormat("dd-MM-yyyy");

	
	public static Map<Long,TipoEstado> mapAll(Connection con, String db) {
		Map<Long,TipoEstado> map = new HashMap<Long,TipoEstado>();
		List<TipoEstado> lista = TipoEstado.all(con, db);
		lista.forEach(x->{
			map.put(x.getId(), x);
		});
		return (map);
	}
	
	public static List<TipoEstado> all(Connection con, String db) {
		List<TipoEstado> lista = new ArrayList<TipoEstado>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,sigla,nombre,reparable,id_bodegaAsociada,valoriza from `"+db+"`.tipoEstado order by nombre;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				BodegaEmpresa bodegaAsociada = BodegaEmpresa.findXIdBodega(con, db, rs.getLong(5));
				String nombreBodega = "No asociada";
				if(bodegaAsociada != null) {
					nombreBodega = bodegaAsociada.nombre;
				}
				lista.add(new TipoEstado(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getLong(4),rs.getLong(5),nombreBodega,rs.getLong(6)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (lista);
	}
	
	public static TipoEstado find(Connection con, String db, Long id_tipoEstado) {
		TipoEstado aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,sigla,nombre,reparable,id_bodegaAsociada,valoriza from `"+db+"`.tipoEstado WHERE id = ?" );
			smt.setLong(1, id_tipoEstado);
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				BodegaEmpresa bodegaAsociada = BodegaEmpresa.findXIdBodega(con, db, rs.getLong(5));
				String nombreBodega = "No asociada";
				if(bodegaAsociada != null) {
					nombreBodega = bodegaAsociada.nombre;
				}
				aux = new TipoEstado(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getLong(4),rs.getLong(5),nombreBodega,rs.getLong(6));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (aux);
	}
	
	public static boolean existeSigla(Connection con, String db, String sigla) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("select id from `"+db+"`.tipoEstado WHERE upper(sigla) = ?" );
			smt.setString(1, sigla.toUpperCase().trim());
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
	
	public static boolean modificaPorCampo(Connection con,String db,String campo,Long id_tipoEstado,String valor) {
		boolean flag = false;
		try {
			PreparedStatement smt = con.prepareStatement("UPDATE `"+db+"`.tipoEstado set `" + campo + "` = ? WHERE id = ?;");		
			smt.setString(1, valor.trim());
			smt.setLong(2, id_tipoEstado);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
				e.printStackTrace();
			flag=false;
		}
		return (flag);
	}
	
	public static boolean estaEnUso(Connection con, String db, Long id_tipoEstado) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("Select id FROM `"+db+"`.estadoEquipo WHERE id_tipoEstado = ?");
			smt.setLong(1, id_tipoEstado);
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
	
	public static boolean delete(Connection con, String db, Long id_tipoEstado) {
		boolean flag = false;
		try {
			PreparedStatement smt1 = con
					.prepareStatement("DELETE FROM `"+db+"`.tipoEstado WHERE id = ?");
			smt1.setLong(1, id_tipoEstado);
			smt1.executeUpdate();
			smt1.close();
			flag = true;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean create(Connection con,String db, FormTipoEstadoGraba aux) {	
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("INSERT INTO `"+db+"`.tipoEstado (sigla,nombre,id_bodegaAsociada,reparable) VALUES (?,?,?,?)");
			smt.setString(1, aux.sigla.trim());
			smt.setString(2, aux.nombre.trim());
			smt.setLong(3, aux.id_bodegaAsociada);
			smt.setLong(4, aux.reparable);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	
	public static List<TipoEstado> allUsadosPorBodega(Connection con, String db, BodegaEmpresa bodega) {
		List<TipoEstado> lista = new ArrayList<TipoEstado>();
		try {
			
			PreparedStatement smt100 = con
					.prepareStatement("select distinct id_tipoEstado from `"+db+"`.estadoEquipo " 
							+ " left join `"+db+"`.movimiento on movimiento.id = estadoEquipo.id_movimiento "
							+ " where id_bodegaEmpresa=?;");
			smt100.setLong(1, bodega.getId());
			ResultSet rs100 = smt100.executeQuery();
			List<String> auxLista = new ArrayList<String>();
			while (rs100.next()) {
				auxLista.add(rs100.getString(1));
			}
			rs100.close();smt100.close();
			String condicion = auxLista.toString();
			condicion = condicion.replace("[", "").replace("]", "");
			
			PreparedStatement smt = con
					.prepareStatement("select id,sigla,nombre,reparable,id_bodegaAsociada,valoriza from `"+db+"`.tipoEstado "
							+ " where id in ("+condicion+") order by nombre;");
			ResultSet rs = smt.executeQuery();
			
			while (rs.next()) {
				lista.add(new TipoEstado(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getLong(4),rs.getLong(5),bodega.getNombre(),rs.getLong(6)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}

	

	
}
