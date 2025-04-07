package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AjustesEP {
	public Long id;
	public Long id_bodegaEmpresa;
	public Long id_tipoAjuste;
	public Long id_tipoAjusteVenta;
	public String concepto;
	public String fechaAjuste;
	public Long id_moneda;
	public String totalAjuste;
	public String observaciones;
	public String ajustePDF;

	public String bodegaEmpresa;
	public String tipoAjuste;
	public String tipoAjusteVenta;
	public String moneda;
	public Long factor;
	public String nameSucursal;

	public AjustesEP(Long id, Long id_bodegaEmpresa, Long id_tipoAjuste, Long id_tipoAjusteVenta, String concepto,
					 String fechaAjuste, Long id_moneda, String totalAjuste, String observaciones, String ajustePDF,
					 String bodegaEmpresa, String tipoAjuste, String tipoAjusteVenta, String moneda, Long factor, String nameSucursal) {
		super();
		this.id = id;
		this.id_bodegaEmpresa = id_bodegaEmpresa;
		this.id_tipoAjuste = id_tipoAjuste;
		this.id_tipoAjusteVenta = id_tipoAjusteVenta;
		this.concepto = concepto;
		this.fechaAjuste = fechaAjuste;
		this.id_moneda = id_moneda;
		this.totalAjuste = totalAjuste;
		this.observaciones = observaciones;
		this.ajustePDF = ajustePDF;
		this.bodegaEmpresa = bodegaEmpresa;
		this.tipoAjuste = tipoAjuste;
		this.tipoAjusteVenta = tipoAjusteVenta;
		this.moneda = moneda;
		this.factor = factor;
		this.nameSucursal = nameSucursal;
	}

	public AjustesEP() {
		super();
	}

	static SimpleDateFormat myformatfecha = new SimpleDateFormat("dd-MM-yyyy");
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");

	private static final Logger logger = LoggerFactory.getLogger(AjustesEP.class);

	// OBTIENE LA LISTA DETALLE DE AJUSTES POR UNA BODEGAEMPRESA EN UN DETERMINADO PERIODO PARA ARRIENDO Y VENTA
	public static List<List<String>> detalleAjuste(Connection con, String db, Long id_bodegaEmpresa, String desdeSql, String hastaSql) {
		List<List<String>> lista = new ArrayList<List<String>>();
		Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
		switch(dec.get((long)1).toString()) {
			case "0": myformatdouble = new DecimalFormat("#,##0"); break;
			case "2": myformatdouble = new DecimalFormat("#,##0.00"); break;
			case "4": myformatdouble = new DecimalFormat("#,##0.0000"); break;
			case "6": myformatdouble = new DecimalFormat("#,##0.000000"); break;
			default:  break;
		}

		String query = String.format("select "
				+ " if(id_tipoAjuste=1,'Menos ', 'Mas '),"
				+ " concepto,"
				+ " if(id_tipoAjuste=1,-1,1)*totalAjuste,"
				+ " id_tipoAjusteVenta"
				+ " from `"+db+"`.ajustesEP "
				+ " where id_bodegaEmpresa=? and (fechaAjuste between ? and ?); ", db);

		try (PreparedStatement smt = con.prepareStatement(query)) {
			smt.setLong(1, id_bodegaEmpresa);
			smt.setString(2, desdeSql);
			smt.setString(3, hastaSql);
			try (ResultSet rs = smt.executeQuery()) {
				while (rs.next()) {
					List<String> aux = new ArrayList<String>();
					aux.add(rs.getString(1) + rs.getString(2));
					if (rs.getLong(4) == 1) {
						aux.add(myformatdouble.format(rs.getDouble(3)));
						aux.add(myformatdouble.format((double)0));
					} else {
						aux.add(myformatdouble.format((double)0));
						aux.add(myformatdouble.format(rs.getDouble(3)));
					}
					lista.add(aux);
				}
			}
		} catch (SQLException e) {
			String className = AjustesEP.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (lista);
	}

	// OBTIENE LA LISTA DETALLE DE AJUSTES POR UNA BODEGAEMPRESA EN UN DETERMINADO PERIODO PARA ARRIENDO Y VENTA
	public static Map<Long,List<List<String>>> mapDetalleAjuste(Connection con, String db, String desdeSql, String hastaSql) {
		Map<Long,List<List<String>>> map = new HashMap<Long,List<List<String>>>();
		Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
		switch(dec.get((long)1).toString()) {
			case "0": myformatdouble = new DecimalFormat("#,##0"); break;
			case "2": myformatdouble = new DecimalFormat("#,##0.00"); break;
			case "4": myformatdouble = new DecimalFormat("#,##0.0000"); break;
			case "6": myformatdouble = new DecimalFormat("#,##0.000000"); break;
			default:  break;
		}
		String query = "select "
				+ " if(id_tipoAjuste=1,'Menos ', 'Mas '),"
				+ " concepto,"
				+ " if(id_tipoAjuste=1,-1,1)*totalAjuste,"
				+ " id_tipoAjusteVenta,"
				+ " id_bodegaEmpresa"
				+ " from `"+db+"`.ajustesEP "
				+ " where fechaAjuste between ? and ?; ";
		try (PreparedStatement smt = con.prepareStatement(query)) {
			smt.setString(1, desdeSql);
			smt.setString(2, hastaSql);
			try (ResultSet rs = smt.executeQuery()) {
				while (rs.next()) {
					List<List<String>> lista = map.get(rs.getLong(5));
					if (lista == null) {
						lista = new ArrayList<List<String>>();
					}
					List<String> aux = new ArrayList<String>();
					aux.add(rs.getString(1) + rs.getString(2));
					if (rs.getLong(4) == 1) {
						aux.add(myformatdouble.format(rs.getDouble(3)));
						aux.add(myformatdouble.format((double)0));
					} else {
						aux.add(myformatdouble.format((double)0));
						aux.add(myformatdouble.format(rs.getDouble(3)));
					}
					lista.add(aux);
					map.put(rs.getLong(5), lista);
				}
			}
		} catch (SQLException e) {
			String className = AjustesEP.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (map);
	}

	// OBTIENE LA LISTA DETALLE DE AJUSTES POR UNA BODEGAEMPRESA EN UN DETERMINADO PERIODO PARA ARRIENDO Y VENTA
	public static List<AjustesEP> allPorBodega(Connection con, String db, Long id_bodegaEmpresa, String esPorSucursal, String id_sucursal){
		List<AjustesEP> lista = new ArrayList<AjustesEP>();

		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and bodegaEmpresa.id_sucursal = " + id_sucursal;
		}

		String query = " select " +
				" ajustesEP.id," +
				" ajustesEP.id_bodegaEmpresa," +
				" ajustesEP.id_tipoAjuste," +
				" ajustesEP.id_tipoAjusteVenta," +
				" ajustesEP.concepto," +
				" ajustesEP.fechaAjuste," +
				" ajustesEP.id_moneda," +
				" ajustesEP.totalAjuste," +
				" ajustesEP.observaciones," +
				" ajustesEP.ajustePDF," +
				" bodegaEmpresa.nombre," +
				" tipoAjuste.ajuste," +
				" tipoAjusteVenta.ajusteVenta," +
				" moneda.nickName, " +
				" tipoAjuste.factor, " +
				" bodegaEmpresa.id_sucursal " +
				" from `"+db+"`.ajustesEP" +
				" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = ajustesEP.id_bodegaEmpresa" +
				" left join `"+db+"`.tipoAjuste on tipoAjuste.id = ajustesEP.id_tipoAjuste" +
				" left join `"+db+"`.tipoAjusteVenta on tipoAjusteVenta.id = ajustesEP.id_tipoAjusteVenta" +
				" left Join `"+db+"`.moneda on moneda.id = ajustesEP.id_moneda" +
				" where ajustesEP.id_bodegaEmpresa = ? " + condSucursal +
				" order by ajustesEP.fechaAjuste desc;";
		try (PreparedStatement smt = con.prepareStatement(query)) {
			smt.setLong(1, id_bodegaEmpresa);
			try (ResultSet rs = smt.executeQuery()) {
				Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
				Map<Long, Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
				while (rs.next()) {
					String nameSucursal = "";
					Sucursal sucursal = mapSucursal.get(rs.getLong(16));
					if(sucursal!=null) {
						nameSucursal = sucursal.getNombre();
					}
					switch(dec.get(rs.getLong(7)).toString()) {
						case "0": myformatdouble = new DecimalFormat("#,##0"); break;
						case "2": myformatdouble = new DecimalFormat("#,##0.00"); break;
						case "4": myformatdouble = new DecimalFormat("#,##0.0000"); break;
						case "6": myformatdouble = new DecimalFormat("#,##0.000000"); break;
						default:  break;
					}
					String fecha = "";
					if (rs.getString(6) != null) {
						fecha = myformatfecha.format(rs.getDate(6));
					}
					lista.add(new AjustesEP(
							rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getLong(4),
							rs.getString(5),fecha,rs.getLong(7),myformatdouble.format(rs.getDouble(8)),
							rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),
							rs.getString(13),rs.getString(14),rs.getLong(15),nameSucursal));
				}
			}
		} catch (SQLException e) {
			String className = AjustesEP.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (lista);
	}

	public static boolean modifyPDF(Connection con, String db, String ajustePDF, Long idAjuste) {
		boolean flag = true;
		String query = "update `"+db+"`.ajustesEP set ajustePDF = ?  WHERE id = ?";
		try (PreparedStatement smt = con.prepareStatement(query)) {
			smt.setString(1, ajustePDF.trim());
			smt.setLong(2, idAjuste);
			smt.executeUpdate();
		} catch (SQLException e) {
			String className = AjustesEP.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
			flag = false;
		}
		return (flag);
	}

	public static AjustesEP find(Connection con, String db, Long id_ajuste, String esPorSucursal, String id_sucursal){
		AjustesEP aux = new AjustesEP();

		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and bodegaEmpresa.id_sucursal = " + id_sucursal;
		}

		String query = " select " +
				" ajustesEP.id," +
				" ajustesEP.id_bodegaEmpresa," +
				" ajustesEP.id_tipoAjuste," +
				" ajustesEP.id_tipoAjusteVenta," +
				" ajustesEP.concepto," +
				" ajustesEP.fechaAjuste," +
				" ajustesEP.id_moneda," +
				" ajustesEP.totalAjuste," +
				" ajustesEP.observaciones," +
				" ajustesEP.ajustePDF," +
				" bodegaEmpresa.nombre," +
				" tipoAjuste.ajuste," +
				" tipoAjusteVenta.ajusteVenta," +
				" moneda.nickName, " +
				" tipoAjuste.factor, " +
				" bodegaEmpresa.id_sucursal " +
				" from `"+db+"`.ajustesEP" +
				" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = ajustesEP.id_bodegaEmpresa" +
				" left join `"+db+"`.tipoAjuste on tipoAjuste.id = ajustesEP.id_tipoAjuste" +
				" left join `"+db+"`.tipoAjusteVenta on tipoAjusteVenta.id = ajustesEP.id_tipoAjusteVenta" +
				" left Join `"+db+"`.moneda on moneda.id = ajustesEP.id_moneda" +
				" where ajustesEP.id = ? " + condSucursal +
				" order by ajustesEP.fechaAjuste desc;";
		try (PreparedStatement smt = con.prepareStatement(query)) {
			smt.setLong(1, id_ajuste);
			try (ResultSet rs = smt.executeQuery()) {
				Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
				Map<Long, Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
				if (rs.next()) {
					String nameSucursal = "";
					Sucursal sucursal = mapSucursal.get(rs.getLong(16));
					if(sucursal != null) {
						nameSucursal = sucursal.getNombre();
					}
					switch(dec.get(rs.getLong(7)).toString()) {
						case "0": myformatdouble = new DecimalFormat("#,##0"); break;
						case "2": myformatdouble = new DecimalFormat("#,##0.00"); break;
						case "4": myformatdouble = new DecimalFormat("#,##0.0000"); break;
						case "6": myformatdouble = new DecimalFormat("#,##0.000000"); break;
						default:  break;
					}
					String fecha = "";
					if (rs.getString(6) != null) {
						fecha = myformatfecha.format(rs.getDate(6));
					}
					aux = new AjustesEP(
							rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getLong(4),
							rs.getString(5),fecha,rs.getLong(7),myformatdouble.format(rs.getDouble(8)),
							rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),
							rs.getString(13),rs.getString(14),rs.getLong(15),nameSucursal);
				}
			}
		} catch (SQLException e) {
			String className = AjustesEP.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (aux);
	}

	public static boolean update(Connection con, String db, AjustesEP aux){
		boolean flag = false;
		try {
			String auxNum = aux.totalAjuste;
			if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
			Double valor = myformatdouble.parse(auxNum).doubleValue();
			String query = "update `"+db+"`.ajustesEP set "+
					" id_bodegaEmpresa=?,id_tipoAjuste=?,id_tipoAjusteVenta=?,concepto=?,fechaAjuste=?,id_moneda=?,totalAjuste=?,observaciones=? " +
					" where id=?;";
			try (PreparedStatement smt = con.prepareStatement(query)) {
				smt.setLong(1, aux.id_bodegaEmpresa);
				smt.setLong(2, aux.id_tipoAjuste);
				smt.setLong(3, aux.id_tipoAjusteVenta);
				smt.setString(4, aux.concepto);
				smt.setString(5, aux.fechaAjuste);
				smt.setLong(6, aux.id_moneda);
				smt.setDouble(7, valor);
				smt.setString(8, aux.observaciones);
				smt.setLong(9, aux.id);
				smt.executeUpdate();
			}
			flag = true;
		} catch (SQLException | ParseException e) {
			String className = AjustesEP.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (flag);
	}

	public static boolean delete(Connection con, String db, Long idAjuste){
		boolean flag = false;
		String query = "delete from `"+db+"`.ajustesEP where id=?;";
		try (PreparedStatement smt = con.prepareStatement(query)) {
			smt.setLong(1, idAjuste);
			smt.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			String className = AjustesEP.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (flag);
	}

	public static boolean create(Connection con, String db, AjustesEP aux){
		boolean flag = false;
		try {
			String auxNum = aux.totalAjuste;
			if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
			Double valor = myformatdouble.parse(auxNum).doubleValue();
			String query2 = "Select id from `"+db+"`.ajustesEP where "+
					" id_bodegaEmpresa=? and id_tipoAjuste=? and id_tipoAjusteVenta=? and concepto=? and fechaAjuste=? and id_moneda=? and totalAjuste=? and observaciones=?;";
			try (PreparedStatement smt2 = con.prepareStatement(query2)) {
				smt2.setLong(1, aux.id_bodegaEmpresa);
				smt2.setLong(2, aux.id_tipoAjuste);
				smt2.setLong(3, aux.id_tipoAjusteVenta);
				smt2.setString(4, aux.concepto);
				smt2.setString(5, aux.fechaAjuste);
				smt2.setLong(6, aux.id_moneda);
				smt2.setDouble(7, valor);
				smt2.setString(8, aux.observaciones);
				try (ResultSet rs2 = smt2.executeQuery()) {
					if(!rs2.next()) {
						String queryInsert = "insert into `"+db+"`.ajustesEP "+
								" (id_bodegaEmpresa,id_tipoAjuste,id_tipoAjusteVenta,concepto,fechaAjuste,id_moneda,totalAjuste,observaciones) values " +
								" (?,?,?,?,?,?,?,?);";
						try (PreparedStatement smt = con.prepareStatement(queryInsert)) {
							smt.setLong(1, aux.id_bodegaEmpresa);
							smt.setLong(2, aux.id_tipoAjuste);
							smt.setLong(3, aux.id_tipoAjusteVenta);
							smt.setString(4, aux.concepto);
							smt.setString(5, aux.fechaAjuste);
							smt.setLong(6, aux.id_moneda);
							smt.setDouble(7, valor);
							smt.setString(8, aux.observaciones);
							smt.executeUpdate();
							flag = true;
						}
					}
				}
			}
		} catch (SQLException | ParseException e) {
			String className = AjustesEP.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (flag);
	}

	// OBTIENE LA LISTA DETALLE DE AJUSTES POR UNA BODEGAEMPRESA EN UN DETERMINADO PERIODO PARA ARRIENDO Y VENTA
	public static List<AjustesEP> allPorPeriodos(Connection con, String db, String desdeAAMMDD, String hastaAAMMDD,
												 String permisoPorBodega, String esPorSucursal, String id_sucursal){

		List<AjustesEP> lista = new ArrayList<AjustesEP>();
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and bodegaEmpresa.id_sucursal = " + id_sucursal;
		}

		String query = " select " +
				" ajustesEP.id," +
				" ajustesEP.id_bodegaEmpresa," +
				" ajustesEP.id_tipoAjuste," +
				" ajustesEP.id_tipoAjusteVenta," +
				" ajustesEP.concepto," +
				" ajustesEP.fechaAjuste," +
				" ajustesEP.id_moneda," +
				" ajustesEP.totalAjuste," +
				" ajustesEP.observaciones," +
				" ajustesEP.ajustePDF," +
				" bodegaEmpresa.nombre," +
				" tipoAjuste.ajuste," +
				" tipoAjusteVenta.ajusteVenta," +
				" moneda.nickName, " +
				" tipoAjuste.factor, " +
				" bodegaEmpresa.id_sucursal " +
				" from `"+db+"`.ajustesEP" +
				" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = ajustesEP.id_bodegaEmpresa" +
				" left join `"+db+"`.tipoAjuste on tipoAjuste.id = ajustesEP.id_tipoAjuste" +
				" left join `"+db+"`.tipoAjusteVenta on tipoAjusteVenta.id = ajustesEP.id_tipoAjusteVenta" +
				" left Join `"+db+"`.moneda on moneda.id = ajustesEP.id_moneda" +
				" where fechaAjuste between ? and ? " + permisoPorBodega + condSucursal +
				" order by bodegaEmpresa.nombre, ajustesEP.fechaAjuste desc;";
		try (PreparedStatement smt = con.prepareStatement(query)) {
			smt.setString(1, desdeAAMMDD);
			smt.setString(2, hastaAAMMDD);
			try (ResultSet rs = smt.executeQuery()) {
				Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
				Map<Long, Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
				while (rs.next()) {
					String nameSucursal = "";
					Sucursal sucursal = mapSucursal.get(rs.getLong(16));
					if(sucursal!=null) {
						nameSucursal = sucursal.getNombre();
					}
					switch(dec.get(rs.getLong(7)).toString()) {
						case "0": myformatdouble = new DecimalFormat("#,##0"); break;
						case "2": myformatdouble = new DecimalFormat("#,##0.00"); break;
						case "4": myformatdouble = new DecimalFormat("#,##0.0000"); break;
						case "6": myformatdouble = new DecimalFormat("#,##0.000000"); break;
						default:  break;
					}
					String fecha = "";
					if (rs.getString(6) != null) {
						fecha = myformatfecha.format(rs.getDate(6));
					}
					lista.add(new AjustesEP(
							rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getLong(4),
							rs.getString(5),fecha,rs.getLong(7),myformatdouble.format(rs.getDouble(8)),
							rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),
							rs.getString(13),rs.getString(14),rs.getLong(15),nameSucursal));
				}
			}
		} catch (SQLException e) {
			String className = AjustesEP.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (lista);
	}
}
