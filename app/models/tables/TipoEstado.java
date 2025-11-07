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

import controllers.HomeController;
import models.forms.FormTipoEstadoGraba;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TipoEstado {
	public Long id;
	public String sigla;
	public String nombre;
	public Long reparable;
	public Long id_bodegaAsociada; // Indica bodega asociada, siempre es interna.
	
	public String bodegaAsociada;
	public Long valoriza;
	
	public Long id_sucursal;
	public String nomSucursal;

	public Long cobraArriendo;

	public TipoEstado(Long id, String sigla, String nombre, Long reparable, Long id_bodegaAsociada, String bodegaAsociada, Long valoriza,
			Long id_sucursal, String nomSucursal, Long cobraArriendo) {
		super();
		this.id = id;
		this.sigla = sigla;
		this.nombre = nombre;
		this.reparable = reparable;
		this.id_bodegaAsociada = id_bodegaAsociada;
		this.bodegaAsociada = bodegaAsociada;
		this.valoriza = valoriza;
		
		this.id_sucursal = id_sucursal;
		this.nomSucursal = nomSucursal;

		this.cobraArriendo = cobraArriendo;
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

	public Long getId_sucursal() {
		return id_sucursal;
	}

	public void setId_sucursal(Long id_sucursal) {
		this.id_sucursal = id_sucursal;
	}

	public String getNomSucursal() {
		return nomSucursal;
	}

	public void setNomSucursal(String nomSucursal) {
		this.nomSucursal = nomSucursal;
	}

	public Long getCobraArriendo() {
		return cobraArriendo;
	}

	public void setCobraArriendo(Long cobraArriendo) {
		this.cobraArriendo = cobraArriendo;
	}

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

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
		String query = String.format("select"
				+ " tipoEstado.id,"
				+ " tipoEstado.sigla,"
				+ " tipoEstado.nombre,"
				+ " tipoEstado.reparable,"
				+ " tipoEstado.id_bodegaAsociada,"
				+ " tipoEstado.valoriza,"
				+ " bodegaEmpresa.id_sucursal,"
				+ " sucursal.nombre,"
				+ " tipoEstado.cobraArriendo"
				+ " from `%s`.tipoEstado"
				+ " left join `%s`.bodegaEmpresa on  bodegaEmpresa.id = tipoEstado.id_bodegaAsociada"
				+ " left join `%s`.sucursal on  sucursal.id = bodegaEmpresa.id_sucursal"
				+ " order by tipoEstado.nombre;",db,db,db);
		try (PreparedStatement smt = con.prepareStatement(query);
			 ResultSet rs = smt.executeQuery()){
			while (rs.next()) {
				BodegaEmpresa bodegaAsociada = BodegaEmpresa.findXIdBodega(con, db, rs.getLong(5));
				String nombreBodega = "No asociada";
				if(bodegaAsociada != null) {
					nombreBodega = bodegaAsociada.nombre;
				}
				lista.add(new TipoEstado(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getLong(4),rs.getLong(5),nombreBodega,rs.getLong(6),
						rs.getLong(7),rs.getString(8),rs.getLong(9)));
			}
		} catch (SQLException e) {
			String className = TipoEstado.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (lista);
	}
	
	public static List<TipoEstado> allPorSucursal(Connection con, String db, Long id_sucursal) {
		List<TipoEstado> lista = new ArrayList<TipoEstado>();
		String query = String.format("select"
				+ " tipoEstado.id,"
				+ " tipoEstado.sigla,"
				+ " tipoEstado.nombre,"
				+ " tipoEstado.reparable,"
				+ " tipoEstado.id_bodegaAsociada,"
				+ " tipoEstado.valoriza,"
				+ " bodegaEmpresa.id_sucursal,"
				+ " sucursal.nombre,"
				+ " tipoEstado.cobraArriendo"
				+ " from `%s`.tipoEstado"
				+ " left join `%s`.bodegaEmpresa on  bodegaEmpresa.id = tipoEstado.id_bodegaAsociada"
				+ " left join `%s`.sucursal on  sucursal.id = bodegaEmpresa.id_sucursal"
				+ " where bodegaEmpresa.id_sucursal = ? or bodegaEmpresa.id_sucursal is null "
				+ " order by tipoEstado.nombre;",db,db,db);
		try (PreparedStatement smt = con.prepareStatement(query)){
			smt.setLong(1, id_sucursal);
			try (ResultSet rs = smt.executeQuery()) {
				while (rs.next()) {
					BodegaEmpresa bodegaAsociada = BodegaEmpresa.findXIdBodega(con, db, rs.getLong(5));
					String nombreBodega = "No asociada";
					if (bodegaAsociada != null) {
						nombreBodega = bodegaAsociada.nombre;
					}
					lista.add(new TipoEstado(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getLong(4), rs.getLong(5), nombreBodega, rs.getLong(6),
							rs.getLong(7), rs.getString(8), rs.getLong(9)));
				}
			}
		} catch (SQLException e) {
			String className = TipoEstado.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (lista);
	}
	
	public static TipoEstado find(Connection con, String db, Long id_tipoEstado) {
		TipoEstado aux = null;
		String query = String.format("select"
				+ " tipoEstado.id,"
				+ " tipoEstado.sigla,"
				+ " tipoEstado.nombre,"
				+ " tipoEstado.reparable,"
				+ " tipoEstado.id_bodegaAsociada,"
				+ " tipoEstado.valoriza,"
				+ " bodegaEmpresa.id_sucursal,"
				+ " sucursal.nombre,"
				+ " tipoEstado.cobraArriendo"
				+ " from `%s`.tipoEstado"
				+ " left join `%s`.bodegaEmpresa on  bodegaEmpresa.id = tipoEstado.id_bodegaAsociada"
				+ " left join `%s`.sucursal on  sucursal.id = bodegaEmpresa.id_sucursal"
				+ " where tipoEstado.id = ?"
				+ " order by tipoEstado.nombre;",db,db,db);
		try (PreparedStatement smt = con.prepareStatement(query)){
			smt.setLong(1, id_tipoEstado);
			try (ResultSet rs = smt.executeQuery()) {
				if (rs.next()) {
					BodegaEmpresa bodegaAsociada = BodegaEmpresa.findXIdBodega(con, db, rs.getLong(5));
					String nombreBodega = "No asociada";
					if (bodegaAsociada != null) {
						nombreBodega = bodegaAsociada.nombre;
					}
					aux = new TipoEstado(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getLong(4), rs.getLong(5), nombreBodega, rs.getLong(6),
							rs.getLong(7), rs.getString(8),rs.getLong(9));
				}
			}
		} catch (SQLException e) {
			String className = TipoEstado.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (aux);
	}
	
	public static boolean existeSigla(Connection con, String db, String sigla) {
		boolean flag = false;
		String query = String.format("select id from `%s`.tipoEstado where upper(sigla) = ?",db);
		try (PreparedStatement smt = con.prepareStatement(query)){
			smt.setString(1, sigla.toUpperCase().trim());
			try (ResultSet rs = smt.executeQuery()) {
				if (rs.next()) {
					flag = true;
				}
			}
		} catch (SQLException e) {
			String className = TipoEstado.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (flag);
	}
	
	public static boolean modificaPorCampo(Connection con,String db,String campo,Long id_tipoEstado,String valor) {
		boolean flag = false;
		String query = String.format("update `%s`.tipoEstado set `" + campo + "` = ? where id = ?;",db);
		try (PreparedStatement smt = con.prepareStatement(query)){
			smt.setString(1, valor.trim());
			smt.setLong(2, id_tipoEstado);
			smt.executeUpdate();
			if(campo.equals("reparable")) {
				query = String.format("update `%s`.tipoEstado set `valoriza` = ? where id = ?;",db);
				try (PreparedStatement smt2 = con.prepareStatement(query)) {
					smt2.setString(1, valor.trim());
					smt2.setLong(2, id_tipoEstado);
					smt2.executeUpdate();
				}
			}
			flag = true;
		} catch (SQLException e) {
			String className = TipoEstado.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (flag);
	}
	
	public static boolean estaEnUso(Connection con, String db, Long id_tipoEstado) {
		boolean flag = false;
		String query = String.format("select id from `%s`.estadoEquipo where id_tipoEstado = ?",db);
		try (PreparedStatement smt = con.prepareStatement(query)){
			smt.setLong(1, id_tipoEstado);
			try (ResultSet resultado = smt.executeQuery()) {
				if (resultado.next()) {
					flag = true;
				}
			}
		} catch (SQLException e) {
			String className = TipoEstado.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (flag);
	}
	
	public static boolean delete(Connection con, String db, Long id_tipoEstado) {
		boolean flag = false;
		String query = String.format("delete from `%s`.tipoEstado where id = ?",db);
		try (PreparedStatement smt = con.prepareStatement(query)){
			smt.setLong(1, id_tipoEstado);
			smt.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			String className = TipoEstado.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (flag);
	}
	
	public static boolean create(Connection con,String db, FormTipoEstadoGraba aux) {	
		boolean flag = false;
		String query = String.format("insert into `%s`.tipoEstado (sigla,nombre,id_bodegaAsociada,reparable,valoriza,cobraArriendo) values (?,?,?,?,?,?)",db);
		Long cobraArriendo = aux.cobraArriendo;
		if(cobraArriendo == null) {
			cobraArriendo = 0L;
		}
		try (PreparedStatement smt = con.prepareStatement(query)){
			smt.setString(1, aux.sigla.trim());
			smt.setString(2, aux.nombre.trim());
			smt.setLong(3, aux.id_bodegaAsociada);
			smt.setLong(4, aux.reparable);
			smt.setLong(5, aux.reparable);
			smt.setLong(6, cobraArriendo);
			smt.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			String className = TipoEstado.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (flag);
	}
	
	public static List<TipoEstado> allUsadosPorBodega(Connection con, String db, BodegaEmpresa bodega) {
		List<TipoEstado> lista = new ArrayList<TipoEstado>();
		String query = String.format("select distinct id_tipoEstado from `%s`.estadoEquipo "
				+ " left join `%s`.movimiento on movimiento.id = estadoEquipo.id_movimiento "
				+ " where id_bodegaEmpresa=?;",db,db);
		try (PreparedStatement smt1 = con.prepareStatement(query)){
			smt1.setLong(1, bodega.getId());
			try (ResultSet rs1 = smt1.executeQuery()) {
				List<String> auxLista = new ArrayList<String>();
				while (rs1.next()) {
					auxLista.add(rs1.getString(1));
				}
				String condicion = auxLista.toString();
				condicion = condicion.replace("[", "").replace("]", "");
				query = String.format("select "
						+ " tipoEstado.id,"
						+ " tipoEstado.sigla,"
						+ " tipoEstado.nombre,"
						+ " tipoEstado.reparable,"
						+ " tipoEstado.id_bodegaAsociada,"
						+ " tipoEstado.valoriza,"
						+ " bodegaEmpresa.id_sucursal,"
						+ " sucursal.nombre,"
						+ " tipoEstado.cobraArriendo"
						+ " from `%s`.tipoEstado "
						+ " left join `%s`.bodegaEmpresa on  bodegaEmpresa.id = tipoEstado.id_bodegaAsociada"
						+ " left join `%s`.sucursal on  sucursal.id = bodegaEmpresa.id_sucursal"
						+ " where tipoEstado.id in (" + condicion + ") order by tipoEstado.nombre;",db,db,db);
				try (PreparedStatement smt = con.prepareStatement(query);
					 ResultSet rs = smt.executeQuery()) {
					while (rs.next()) {
						lista.add(new TipoEstado(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getLong(4), rs.getLong(5), bodega.getNombre(), rs.getLong(6),
								rs.getLong(7), rs.getString(8),rs.getLong(9)));
					}
				}
			}
		} catch (SQLException e) {
			String className = TipoEstado.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (lista);
	}

	

	
}
