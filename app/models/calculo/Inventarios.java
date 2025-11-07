package models.calculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import controllers.HomeController;
import models.tables.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Inventarios {
	
	public Long id_bodegaEmpresa;
	public Long id_grupo;
	public Long id_equipo;
	public Long id_cotizacion;
	public Long esVenta;
	public Double cantidad;
	
	
	public Long id_guia;
	public String fechaGuia;
	public Long id_tipoMovimiento;

	public Long numeroGuia;
	public String numGuiaCliente;

	public String fechaIniTerGuia;
	
	
	public Inventarios(Long id_bodegaEmpresa, Long id_grupo, Long id_equipo, Long id_cotizacion, Long esVenta, Double cantidad, Long id_guia, String fechaGuia, Long id_tipoMovimiento,
					   Long numeroGuia, String numGuiaCliente, String fechaIniTerGuia) {
		super();
		this.id_bodegaEmpresa = id_bodegaEmpresa;
		this.id_equipo = id_equipo;
		this.id_grupo = id_grupo;
		this.id_cotizacion = id_cotizacion;
		this.esVenta = esVenta;
		this.cantidad = cantidad;
		this.id_guia = id_guia;
		this.fechaGuia = fechaGuia;
		this.id_tipoMovimiento = id_tipoMovimiento;
		this.numeroGuia = numeroGuia;
		this.numGuiaCliente = numGuiaCliente;
		this.fechaIniTerGuia = fechaIniTerGuia;
	}

	public Inventarios() {
		super();
	}

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	//obtiene inventario POR BODEGA agrupado por equipo y cotizacion:
	public static Map<String ,Movimiento> invPorIdBodega (Connection con, String db, Long id_bodegaEmpresa, Long soloArriendo) {
		try{
			List<Movimiento> listMovimiento = Movimiento.allPorIdBodega(con, db, id_bodegaEmpresa);
			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, db, id_bodegaEmpresa);
			Map<String ,Movimiento> map = listMovimiento.stream()
					.filter(x -> {
						if((long)soloArriendo == (long)1 && (long) bodega.esInterna > (long)1) {
							return((long)x.getEsVenta() == (long)0);
						}else {
							return(true);
						}
					})
					.collect(Collectors.toMap(x -> "" + x.getId_equipo() + "_" + x.getId_cotizacion(), valor -> {
						return valor;
					}, (s1, s2) -> {
						int signoS1 = 1;
						int signoS2 = 1;
						if((long)s1.getId_tipoMovimiento() > (long)1) {
							s1.setId_tipoMovimiento((long)1);
							signoS1 = -1;
						}
						if((long)s2.getId_tipoMovimiento() > (long)1) { signoS2 = -1; }
						s1.setCantidad( s1.getCantidad()*signoS1 + s2.getCantidad()*signoS2 );
						return s1;
					}));
			if(id_bodegaEmpresa==(long)1) {
				Map<Long,Double> bajasSinConfirmar = Baja.totalDeBajasPorIdEquipoPendientes(con, db);
				Map<Long,Double> redimensionarSinConfirmar = Redimensionar.redimensionarPorIdEquipoPendientes(con, db);
				map.forEach((k,v) ->{
					Double baja = bajasSinConfirmar.get(v.id_equipo);
					if(baja!=null) {
						Double aux = v.getCantidad();
						v.setCantidad(aux-baja);
					}
					Double redimensionar = redimensionarSinConfirmar.get(v.id_equipo);
					if(redimensionar!=null) {
						Double aux = v.getCantidad();
						v.setCantidad(aux-redimensionar);
					}
					if(v.getCantidad() < 0 ) {
						v.setCantidad((double)0);
					}
				});
			}
			return(map);
		} catch (Exception e) {
			String className = Inventarios.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
			return(null);
		}
	}
	
	
	//obtiene inventario POR UNA DETERMINADA BODEGA agrupado por equipo:
	public static Map<String ,Movimiento> invPorIdBodegaAgrupado (Connection con, String db, Long id_bodegaEmpresa, Long soloArriendo) {
		try{
			List<Movimiento> listMovimiento = Movimiento.allPorIdBodega(con, db, id_bodegaEmpresa);
			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, db, id_bodegaEmpresa);
			Map<String ,Movimiento> map = listMovimiento.stream()
					.filter(x -> {
						if((long)soloArriendo == (long)1 && (long) bodega.esInterna > (long)1) {
							return((long)x.getEsVenta() == (long)0);
						}else {
							return(true);
						}
					})
					.collect(Collectors.toMap(x -> "" + x.getId_equipo(), valor -> {
						return valor;
					}, (s1, s2) -> {
						int signoS1 = 1;
						int signoS2 = 1;
						if((long)s1.getId_tipoMovimiento() >(long)1) {
							s1.setId_tipoMovimiento((long)1);
							signoS1 = -1;
						}
						if((long)s2.getId_tipoMovimiento() > (long)1) { signoS2 = -1; }
						s1.setCantidad( s1.getCantidad()*signoS1 + s2.getCantidad()*signoS2 );
						return s1;
					}));
			if(id_bodegaEmpresa==(long)1) {
				Map<Long,Double> bajasSinConfirmar = Baja.totalDeBajasPorIdEquipoPendientes(con, db);
				Map<Long,Double> redimensionarSinConfirmar = Redimensionar.redimensionarPorIdEquipoPendientes(con, db);
				map.forEach((k,v) ->{
					Double baja = bajasSinConfirmar.get(v.id_equipo);
					if(baja!=null) {
						Double aux = v.getCantidad();
						v.setCantidad(aux-baja);
					}
					Double redimensionar = redimensionarSinConfirmar.get(v.id_equipo);
					if(redimensionar!=null) {
						Double aux = v.getCantidad();
						v.setCantidad(aux-redimensionar);
					}
					if(v.getCantidad() < 0 ) {
						v.setCantidad((double)0);
					}
				});
			}
			return(map);
		} catch (Exception e) {
			String className = Inventarios.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
			return(null);
		}
	}
	
	//obtiene inventario POR UN DETERMINADO EQUIPO agrupado por bodega:
	public static Map<String ,Movimiento> invPorIdEquipoAgrupadoPorBodega (Connection con, String db, Long id_equipo, Long soloArriendo) {
		try {
			List<Movimiento> listMovimiento = Movimiento.allPorIdEquipo(con, db, id_equipo);
			Map<Long,BodegaEmpresa> mapBodega = BodegaEmpresa.mapAll(con, db);
			Map<String ,Movimiento> map = listMovimiento.stream()
					.filter(x -> {
						BodegaEmpresa bodega = mapBodega.get(x.getId_bodegaEmpresa());
						Long esInterna = (long) 1;
						if(bodega!=null) {
							esInterna = bodega.esInterna;
						}
						if((long)soloArriendo == (long)1 && (long) esInterna > (long) 1) {
							return((long)x.getEsVenta() == (long)0);
						}else {
							return(true);
						}
					})
					.collect(Collectors.toMap(x -> "" + x.getId_bodegaEmpresa(), valor -> {
						return valor;
					}, (s1, s2) -> {
						int signoS1 = 1;
						int signoS2 = 1;
						if((long)s1.getId_tipoMovimiento() >(long)1) {
							s1.setId_tipoMovimiento((long)1);
							signoS1 = -1;
						}
						if((long)s2.getId_tipoMovimiento() > (long)1) { signoS2 = -1; }
						s1.setCantidad( s1.getCantidad()*signoS1 + s2.getCantidad()*signoS2 );
						return s1;
					}));
			Map<Long,Double> bajasSinConfirmar = Baja.totalDeBajasPorIdEquipoPendientes(con, db);
			map.forEach((k,v) ->{
				if(v.id_bodegaEmpresa==(long)1) {
					Double baja = bajasSinConfirmar.get(v.id_equipo);
					if(baja!=null) {
						Double aux = v.getCantidad();
						v.setCantidad(aux-baja);
					}
				}
			});
			return(map);
		} catch (Exception e) {
			String className = Inventarios.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
			return(null);
		}
	}
	
	public static List<Inventarios> inventario (Connection con, String db, List<Long> listIdBodegaEmpresa, List<Long> listIdGuia){
		List<Inventarios> lista = new ArrayList<Inventarios>();
		try {
			String listaInBodega = listIdBodegaEmpresa.toString().replace("[", "").replace("]", "");
			String listaInGuia = listIdGuia.toString().replace("[", "").replace("]", "");
			if(listaInBodega.trim().length()>0 && listaInGuia.trim().length()>0) {
				String query = String.format(" select "
						+ " movimiento.id_bodegaEmpresa, "
						+ " equipo.id_grupo, "
						+ " movimiento.id_equipo,  "
						+ " movimiento.id_cotizacion, "
						+ " movimiento.esVenta, "
						+ " sum(if(movimiento.id_tipoMovimiento=1,1,-1)*movimiento.cantidad) "
						+ " from `%s`.movimiento "
						+ " left join `%s`.equipo on equipo.id = movimiento.id_equipo "
						+ " where movimiento.id_bodegaEmpresa in ("+listaInBodega+") and movimiento.id_guia in ("+listaInGuia+") "
						+ " group by movimiento.id_bodegaEmpresa, movimiento.id_equipo, movimiento.id_cotizacion,movimiento.esVenta "
						+ " order by movimiento.id_bodegaEmpresa, equipo.id_grupo, equipo.id;",db,db);
				try (PreparedStatement smt = con.prepareStatement(query)) {
					try (ResultSet rs = smt.executeQuery()) {
						while (rs.next()) {
							Inventarios aux = new Inventarios();
							aux.id_bodegaEmpresa = rs.getLong(1);
							aux.id_grupo = rs.getLong(2);
							aux.id_equipo = rs.getLong(3);
							aux.id_cotizacion = rs.getLong(4);
							aux.esVenta = rs.getLong(5);
							aux.cantidad = rs.getDouble(6);
							lista.add(aux);
						}
					}
				} catch (SQLException e) {
					String className = Inventarios.class.getSimpleName();
					String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
				}
			}
		} catch (Exception e) {
			String className = Inventarios.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return lista;
	}
	
	public static List<Inventarios> guiasPer (Connection con, String db, List<Long> listIdBodegaEmpresa, List<Long> listIdGuia){
		List<Inventarios> lista = new ArrayList<Inventarios>();
		try {
			String listaInBodega = listIdBodegaEmpresa.toString().replace("[", "").replace("]", "");
			String listaInGuia = listIdGuia.toString().replace("[", "").replace("]", "");
			if(listaInBodega.trim().length()>0 && listaInGuia.trim().length()>0) {
				String query = String.format(" select "
						+ " movimiento.id_guia, "
						+ " movimiento.id_bodegaEmpresa, "
						+ " equipo.id_grupo, "
						+ " movimiento.id_equipo,  "
						+ " movimiento.id_cotizacion,  "
						+ " movimiento.esVenta, "
						+ " guia.fecha, "
						+ " sum(if(movimiento.id_tipoMovimiento=1,1,-1)*movimiento.cantidad), "
						+ " movimiento.id_tipoMovimiento, "
						+ " guia.numero, "
						+ " guia.numGuiaCliente, "
						+ " ifnull(guia.fechaIniTerGuia,guia.fecha) "
						+ " from `%s`.movimiento "
						+ " left join `%s`.guia on guia.id = movimiento.id_guia "
						+ " left join `%s`.equipo on equipo.id = movimiento.id_equipo "
						+ " where movimiento.id_bodegaEmpresa in (" +listaInBodega+") and movimiento.id_guia in ("+listaInGuia+") "
						+ " group by movimiento.id_guia, movimiento.id_bodegaEmpresa, movimiento.id_equipo, "
						+ " movimiento.id_cotizacion,  "
						+ " movimiento.esVenta "
						+ " order by movimiento.id_bodegaEmpresa, equipo.id_grupo, equipo.id, guia.fecha;",db,db,db);
				try (PreparedStatement smt = con.prepareStatement(query)) {
					try (ResultSet rs = smt.executeQuery()) {
						while (rs.next()) {
							Inventarios aux = new Inventarios();
							aux.id_guia = rs.getLong(1);
							aux.id_bodegaEmpresa = rs.getLong(2);
							aux.id_grupo = rs.getLong(3);
							aux.id_equipo = rs.getLong(4);
							aux.id_cotizacion = rs.getLong(5);
							aux.esVenta = rs.getLong(6);
							aux.fechaGuia = rs.getString(7);
							aux.cantidad = rs.getDouble(8);
							aux.id_tipoMovimiento = rs.getLong(9);
							aux.numeroGuia = rs.getLong(10);
							aux.numGuiaCliente = rs.getString(11);
							aux.fechaIniTerGuia = rs.getString(12);
							lista.add(aux);
						}
					}
				} catch (SQLException e) {
					String className = Inventarios.class.getSimpleName();
					String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
				}
			}
		} catch (Exception e) {
			String className = Inventarios.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return lista;
	}
	
	public static Map<Long, List<Inventarios>> guiasPerAllBodegas (Connection con, String db, List<Long> listIdGuia){
		Map<Long, List<Inventarios>> map = new HashMap<Long, List<Inventarios>>();
		try{
			String listaInGuia = listIdGuia.toString().replace("[", "").replace("]", "");
			String query = String.format(" select "
					+ " movimiento.id_guia, "
					+ " movimiento.id_bodegaEmpresa, "
					+ " equipo.id_grupo, "
					+ " movimiento.id_equipo,  "
					+ " movimiento.id_cotizacion,  "
					+ " movimiento.esVenta, "
					+ " guia.fecha, "
					+ " sum(if(movimiento.id_tipoMovimiento=1,1,-1)*movimiento.cantidad), "
					+ " movimiento.id_tipoMovimiento, "
					+ " guia.numero, "
					+ " guia.numGuiaCliente, "
					+ " ifnull(guia.fechaIniTerGuia,guia.fecha) "
					+ " from `%s`.movimiento "
					+ " left join `%s`.guia on guia.id = movimiento.id_guia "
					+ " left join `%s`.equipo on equipo.id = movimiento.id_equipo "
					+ " where movimiento.id_guia in ("+listaInGuia+") "
					+ " group by movimiento.id_guia, movimiento.id_bodegaEmpresa, movimiento.id_equipo, "
					+ " movimiento.id_cotizacion,  "
					+ " movimiento.esVenta "
					+ " order by movimiento.id_bodegaEmpresa, equipo.id_grupo, equipo.id, guia.fecha;",db,db,db);
			if(listaInGuia.trim().length()>0) {
				try (PreparedStatement smt = con.prepareStatement(query)) {
					try (ResultSet rs = smt.executeQuery()) {
						while (rs.next()) {
							Inventarios aux = new Inventarios();
							aux.id_guia = rs.getLong(1);
							aux.id_bodegaEmpresa = rs.getLong(2);
							aux.id_grupo = rs.getLong(3);
							aux.id_equipo = rs.getLong(4);
							aux.id_cotizacion = rs.getLong(5);
							aux.esVenta = rs.getLong(6);
							aux.fechaGuia = rs.getString(7);
							aux.cantidad = rs.getDouble(8);
							aux.id_tipoMovimiento = rs.getLong(9);
							aux.numeroGuia = rs.getLong(10);
							aux.numGuiaCliente = rs.getString(11);
							aux.fechaIniTerGuia = rs.getString(12);
							List<Inventarios> lista = map.get(rs.getLong(2));
							if (lista == null) {
								List<Inventarios> auxLista = new ArrayList<Inventarios>();
								auxLista.add(aux);
								map.put(rs.getLong(2), auxLista);
							} else {
								lista.add(aux);
								map.put(rs.getLong(2), lista);
							}
						}
					}
				} catch (SQLException e) {
					String className = Inventarios.class.getSimpleName();
					String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
				}
			}
		} catch (Exception e) {
			String className = Inventarios.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return map;
	}
	
	//obtiene inventario por bodega de un determinado equipo:
	public static Map<Long,Double> mapIdBodconCantPorUnEquipo (Connection con, String db, Long id_equipo) {
		Map<Long,Double> map = new HashMap<Long,Double>();
		String query = String.format(" select "
				+ " id_bodegaEmpresa, sum(if(movimiento.id_tipoMovimiento=1,1,-1)*movimiento.cantidad)  "
				+ " from `%s`.movimiento "
				+ " where id_equipo=? group by id_bodegaEmpresa;",db);
		try (PreparedStatement smt = con.prepareStatement(query)) {
			smt.setLong(1, id_equipo);
			try (ResultSet rs = smt.executeQuery()) {
				while (rs.next()) {
					if (rs.getDouble(2) > 0) map.put(rs.getLong(1), rs.getDouble(2));
				}
			}
		} catch (SQLException e) {
			String className = Inventarios.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return(map);
	}

	public static Map<Long,Long> mapBodegasVigYnoVigConStock (Connection con, String db){
		Map<Long,Long> map = new HashMap<Long,Long>();
		String query = String.format(" select movimiento.id_bodegaEmpresa " +
				" from `%s`.movimiento " +
				" group by movimiento.id_bodegaEmpresa "+
				" having if(sum(movimiento.cantidad*if(movimiento.id_tipoMovimiento=1,1,-1))=-0,0,sum(movimiento.cantidad*if(movimiento.id_tipoMovimiento=1,1,-1)))>0",db);
		try (PreparedStatement smt = con.prepareStatement(query)) {
			try (ResultSet rs = smt.executeQuery()) {
				while (rs.next()) {
					map.put(rs.getLong(1), rs.getLong(1));
				}
			}
		} catch (SQLException e) {
			String className = Inventarios.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return(map);
	}
	
	public static Map<Long,Long> mapBodegasVigConStock (Connection con, String db){
		Map<Long,Long> map = new HashMap<Long,Long>();
		String query = String.format(" select movimiento.id_bodegaEmpresa " +
				" from `%s`.movimiento " +
				" left join `%s`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa  " +
				" where bodegaEmpresa.vigente = 1 "+
				" group by movimiento.id_bodegaEmpresa "+
				" having if(sum(movimiento.cantidad*if(movimiento.id_tipoMovimiento=1,1,-1))=-0,0,sum(movimiento.cantidad*if(movimiento.id_tipoMovimiento=1,1,-1)))>0",db,db);
		try (PreparedStatement smt = con.prepareStatement(query)) {
			try (ResultSet rs = smt.executeQuery()) {
				while (rs.next()) {
					map.put(rs.getLong(1), rs.getLong(1));
				}
			}
		} catch (SQLException e) {
			String className = Inventarios.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return(map);
	}

	public static Map<Long,Long> mapBodegasVigConStockSoloArr (Connection con, String db){
		Map<Long,Long> map = new HashMap<Long,Long>();
		String query = String.format(" select movimiento.id_bodegaEmpresa " +
				" from `%s`.movimiento " +
				" left join `%s`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa  " +
				" where bodegaEmpresa.vigente = 1 and esVenta = 0"+
				" group by movimiento.id_bodegaEmpresa "+
				" having if(sum(movimiento.cantidad*if(movimiento.id_tipoMovimiento=1,1,-1))=-0,0,sum(movimiento.cantidad*if(movimiento.id_tipoMovimiento=1,1,-1)))>0",db,db);
		try (PreparedStatement smt = con.prepareStatement(query)) {
			try (ResultSet rs = smt.executeQuery()) {
				while (rs.next()) {
					map.put(rs.getLong(1), rs.getLong(1));
				}
			}
		} catch (SQLException e) {
			String className = Inventarios.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return(map);
	}
	
	//obtiene listado de bodegas que poseen inventario positivo mayor que cero con o sin fecha de corte
	public static List<List<String>> listaBodegasConStock(Connection con, String db, String fechaCorte, String permisoPorBodega,
			String esPorSucursal, String id_sucursal, String tipo) {
		List<List<String>> listaBodegas = new ArrayList<List<String>>();
		try {
			String concepto = " and movimiento.esVenta = 0 ";
			if(tipo.equals("VENTA")){
				 concepto = " and movimiento.esVenta = 1 ";
			}else if(tipo.equals("TODO")) {
				concepto = "";
			}
			String condSucursal = "";
			if(esPorSucursal.equals("1") && ! id_sucursal.equals("0")) {
				condSucursal = " and bodegaEmpresa.id_sucursal = " + id_sucursal;
			}
			String cond ="";
			if(!fechaCorte.equals("0")) {
				cond = " left join `"+db+"`.guia on guia.id = movimiento.id_guia and movimiento.id_guia<>0" +
						" left join `"+db+"`.compra on compra.id = movimiento.id_compra and movimiento.id_compra<>0 " +
						" left join `"+db+"`.factura on factura.id = compra.id_factura " +
						" left join `"+db+"`.baja on baja.id = movimiento.id_baja and movimiento.id_baja<>0 " +
						" left join `"+db+"`.actaBaja on actaBaja.id = baja.id_actaBaja " +
						" where  (guia.fecha <= '"+fechaCorte+"' || movimiento.id_guia=0) and (factura.fecha <= '"+fechaCorte+"' || movimiento.id_compra=0) "
								+ " and (actaBaja.fecha <= '"+fechaCorte+"' || movimiento.id_baja=0) " + concepto;
				permisoPorBodega.replace("where", "and");
			}
			permisoPorBodega = permisoPorBodega.replace("and", "where");
			String query = String.format(" select movimiento.id_bodegaEmpresa " +
					" from `%s`.movimiento " + cond + permisoPorBodega +
					" group by movimiento.id_bodegaEmpresa "+
					" having if(sum(movimiento.cantidad*if(movimiento.id_tipoMovimiento=1,1,-1))=-0,0,sum(movimiento.cantidad*if(movimiento.id_tipoMovimiento=1,1,-1)))>0",db);
			try (PreparedStatement smt = con.prepareStatement(query)) {
				String listaCond = "";
				try (ResultSet rs = smt.executeQuery()) {
					while (rs.next()) {
						listaCond = listaCond + rs.getString(1) + ",";
					}
				}
				if(listaCond.length()>1) {
					listaCond = listaCond.substring(0,listaCond.length()-1);
					query = String.format("select id,nombre,esInterna, vigente " +
									" from `%s`.bodegaEmpresa " +
									" where id in ("+listaCond+") " + condSucursal +
									" order by esInterna,nombre;",db);
					try (PreparedStatement smt1 = con.prepareStatement(query)) {
						try (ResultSet rs1 = smt1.executeQuery()) {
							while (rs1.next()) {
								List<String> aux = new ArrayList<String>();
								aux.add(rs1.getString(1)); // 0 idBodega
								aux.add(rs1.getString(2)); // 1 bodega
								aux.add(rs1.getString(3)); // 2 es interna
								aux.add(rs1.getString(4)); // 3 vigente
								listaBodegas.add(aux);
							}
						}
					}
				}
			} catch (SQLException e) {
				String className = Inventarios.class.getSimpleName();
				String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
			}
		} catch (Exception e) {
			String className = Inventarios.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return(listaBodegas);
	}
	
	//obtiene listado de equipos que poseen inventario positivo mayor que cero con o sin fecha de corte
	public static List<List<String>> listaEquiposConStock(Connection con, String db, String permisoPorBodega, String fechaCorte){
		List<List<String>> listaEquipos = new ArrayList<List<String>>();
		try {
			String cond ="";
			if(!fechaCorte.equals("0")) {
				cond = " left join `"+db+"`.guia on guia.id = movimiento.id_guia and movimiento.id_guia<>0" +
						" left join `"+db+"`.compra on compra.id = movimiento.id_compra and movimiento.id_compra<>0 " +
						" left join `"+db+"`.factura on factura.id = compra.id_factura " +
						" left join `"+db+"`.baja on baja.id = movimiento.id_baja and movimiento.id_baja<>0 " +
						" left join `"+db+"`.actaBaja on actaBaja.id = baja.id_actaBaja " +
						" where  (guia.fecha <= '"+fechaCorte+"' || movimiento.id_guia=0) and (factura.fecha <= '"+fechaCorte+"' || movimiento.id_compra=0) and (actaBaja.fecha <= '"+fechaCorte+"' || movimiento.id_baja=0) ";
				permisoPorBodega.replace("where", "and");
			}
			permisoPorBodega = permisoPorBodega.replace("and", "where");
			String query = String.format(" select movimiento.id_equipo from `%s`.movimiento "+cond+permisoPorBodega+ " group by id_equipo " +
					" having if(sum(movimiento.cantidad*if(movimiento.id_tipoMovimiento=1,1,-1))=-0,0,sum(movimiento.cantidad*if(movimiento.id_tipoMovimiento=1,1,-1)))>0",db);
			try (PreparedStatement smt = con.prepareStatement(query)) {
				String listaCond = "";
				try (ResultSet rs = smt.executeQuery()) {
					while (rs.next()) {
						listaCond = listaCond + rs.getString(1) + ",";
					}
				}
				if(listaCond.length()>1) {
					listaCond = listaCond.substring(0,listaCond.length()-1);
					query = String.format(" select"
									+ " equipo.id,"
									+ " equipo.codigo,"
									+ " equipo.nombre,"
									+ " equipo.id_grupo,"
									+ " grupo.nombre, "
									+ " equipo.kg, "
									+ " equipo.m2, "
									+ " equipo.id_propiedad " +
									" from `%s`.equipo left join `%s`.grupo on grupo.id=equipo.id_grupo " +
									" where equipo.id in ("+listaCond+") " +
									" order by equipo.nombre;",db,db);
					try (PreparedStatement smt2 = con.prepareStatement(query)) {
						try (ResultSet rs2 = smt2.executeQuery()) {
							while (rs2.next()) {
								List<String> aux = new ArrayList<String>();
								aux.add(rs2.getString(1)); // 0 idEquipo
								aux.add(rs2.getString(2)); // 1 codigo
								aux.add(rs2.getString(3)); // 2 nombre equipo
								aux.add(rs2.getString(4)); // 3 idGrupo
								aux.add(rs2.getString(5)); // 4 nombre grupo o familia
								aux.add(rs2.getString(6)); // 5 kg
								aux.add(rs2.getString(7)); // 6 m2
								aux.add(rs2.getString(8)); // 7 id_propiedad
								listaEquipos.add(aux);
							}
						}
					}
				}
			} catch (SQLException e) {
				String className = Inventarios.class.getSimpleName();
				String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
			}
		} catch (Exception e) {
			String className = Inventarios.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return(listaEquipos);
	}
	
	
	//map equipos con inventario positivo mayor que cero con o sin fecha de corte
	public static Map<Long,Double> mapEquiposConStock(Connection con, String db, String tipo, Map<String,String> mapeoDiccionario) {
		Map<Long, Double> map = new HashMap<Long, Double>();
		try {
			String condicionaSuma = "";
			if (tipo.equals(mapeoDiccionario.get("ARRIENDO")) || tipo.equals("ARRIENDO")) {
				condicionaSuma = " if("
						+ "sum(if(movimiento.id_tipoMovimiento=1,1,-1) * movimiento.cantidad  * if(movimiento.esVenta=0, 1, 0)) = -0, 0, "
						+ "sum(if(movimiento.id_tipoMovimiento=1,1,-1) * movimiento.cantidad  * if(movimiento.esVenta=0, 1, 0))) as cantidad";
			} else if (tipo.equals("VENTA")) {
				condicionaSuma = " if("
						+ "sum(if(movimiento.id_tipoMovimiento=1,1,-1) * movimiento.cantidad * if(bodegaEmpresa.esInterna=1, 0, if(movimiento.esVenta=1, 1, 0))) = -0, 0, "
						+ "sum(if(movimiento.id_tipoMovimiento=1,1,-1) * movimiento.cantidad * if(bodegaEmpresa.esInterna=1, 0, if(movimiento.esVenta=1, 1, 0)))) as cantidad";
			} else if (tipo.equals("TODO")) {
				condicionaSuma = "if("
						+ "sum(if(movimiento.id_tipoMovimiento=1,1,-1)*movimiento.cantidad)=-0,0,"
						+ "sum(if(movimiento.id_tipoMovimiento=1,1,-1)*movimiento.cantidad)) as cantidad ";
			}
			String query = String.format(" select "
					+ " movimiento.id_equipo, "
					+ condicionaSuma
					+ " from `" + db + "`.movimiento "
					+ " group by id_equipo "
					+ " having cantidad > 0", db);
			try {
				try (PreparedStatement smt2 = con.prepareStatement(query)) {
					try (ResultSet rs2 = smt2.executeQuery()) {
						while (rs2.next()) {
							map.put(rs2.getLong(1), rs2.getDouble(2));
						}
					}
				}
			} catch (SQLException e) {
				String className = Inventarios.class.getSimpleName();
				String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
			}
		} catch (Exception e) {
			String className = Inventarios.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (map);
	}


}
