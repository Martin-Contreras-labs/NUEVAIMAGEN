package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 4 y 13
public class AjustesEpOdo {

	private static final Logger logger = LoggerFactory.getLogger(AjustesEpOdo.class);

	public Long id;
	public Long id_bodegaEmpresa;
	public Long id_tipoAjuste;
	public String concepto;
	public String fechaAjuste;
	public Long id_moneda;
	public String totalAjuste;
	public String observaciones;
	public String ajustePDF;

	public String bodegaEmpresa;
	public String tipoAjuste;
	public String moneda;
	public Long factor;
	public String nameSucursal;

	public AjustesEpOdo(Long id, Long id_bodegaEmpresa, Long id_tipoAjuste, String concepto, String fechaAjuste,
						Long id_moneda, String totalAjuste, String observaciones, String ajustePDF, String bodegaEmpresa,
						String tipoAjuste, String moneda, Long factor, String nameSucursal) {
		super();
		this.id = id;
		this.id_bodegaEmpresa = id_bodegaEmpresa;
		this.id_tipoAjuste = id_tipoAjuste;
		this.concepto = concepto;
		this.fechaAjuste = fechaAjuste;
		this.id_moneda = id_moneda;
		this.totalAjuste = totalAjuste;
		this.observaciones = observaciones;
		this.ajustePDF = ajustePDF;
		this.bodegaEmpresa = bodegaEmpresa;
		this.tipoAjuste = tipoAjuste;
		this.moneda = moneda;
		this.factor = factor;
		this.nameSucursal = nameSucursal;
	}

	public AjustesEpOdo() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId_bodegaEmpresa() {
		return id_bodegaEmpresa;
	}
	public void setId_bodegaEmpresa(Long id_bodegaEmpresa) {
		this.id_bodegaEmpresa = id_bodegaEmpresa;
	}
	public Long getId_tipoAjuste() {
		return id_tipoAjuste;
	}
	public void setId_tipoAjuste(Long id_tipoAjuste) {
		this.id_tipoAjuste = id_tipoAjuste;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public String getFechaAjuste() {
		return fechaAjuste;
	}
	public void setFechaAjuste(String fechaAjuste) {
		this.fechaAjuste = fechaAjuste;
	}
	public Long getId_moneda() {
		return id_moneda;
	}
	public void setId_moneda(Long id_moneda) {
		this.id_moneda = id_moneda;
	}
	public String getTotalAjuste() {
		return totalAjuste;
	}
	public void setTotalAjuste(String totalAjuste) {
		this.totalAjuste = totalAjuste;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getAjustePDF() {
		return ajustePDF;
	}
	public void setAjustePDF(String ajustePDF) {
		this.ajustePDF = ajustePDF;
	}
	public String getBodegaEmpresa() {
		return bodegaEmpresa;
	}
	public void setBodegaEmpresa(String bodegaEmpresa) {
		this.bodegaEmpresa = bodegaEmpresa;
	}
	public String getTipoAjuste() {
		return tipoAjuste;
	}
	public void setTipoAjuste(String tipoAjuste) {
		this.tipoAjuste = tipoAjuste;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public Long getFactor() {
		return factor;
	}
	public void setFactor(Long factor) {
		this.factor = factor;
	}
	public String getNameSucursal() {
		return nameSucursal;
	}
	public void setNameSucursal(String nameSucursal) {
		this.nameSucursal = nameSucursal;
	}

	static DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
	static SimpleDateFormat myformatfecha = new SimpleDateFormat("dd-MM-yyyy");
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00",symbols);

	//OBTIENE LA LISTA DETALLE DE AJUSTES POR UNA BODEGAEMPRESA EN UN DETERMINADO PERIODO
	public static List<List<String>> detalleAjuste(Connection con, String db, Long id_bodegaEmpresa, String desdeAAMMDD, String hastaAAMMDD) {
		List<List<String>> lista = new ArrayList<List<String>>();
		Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
		switch(dec.get((long)1).toString()) {
			case "0": myformatdouble = new DecimalFormat("#,##0",symbols); break;
			case "2": myformatdouble = new DecimalFormat("#,##0.00",symbols); break;
			case "4": myformatdouble = new DecimalFormat("#,##0.0000",symbols); break;
			case "6": myformatdouble = new DecimalFormat("#,##0.000000",symbols); break;
			default:  break;
		}
		try (PreparedStatement smt = con.prepareStatement("select if(id_tipoAjuste=1,'Menos ', 'Mas '), concepto, if(id_tipoAjuste=1,-1,1)*totalAjuste from `"+db+"`.ajustesEpOdo " +
				" where id_bodegaEmpresa=? and (fechaAjuste between ? and ?); ")) {
			smt.setLong(1, id_bodegaEmpresa);
			smt.setString(2, desdeAAMMDD);
			smt.setString(3, hastaAAMMDD);
			try (ResultSet rs = smt.executeQuery()) {
				while (rs.next()) {
					List<String> aux = new ArrayList<String>();
					aux.add(rs.getString(1)+rs.getString(2));
					aux.add(myformatdouble.format(rs.getDouble(3)));
					lista.add(aux);
				}
			}
		} catch (SQLException e) {
			String className  = AjustesEpOdo.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (lista);
	}

	public static List<AjustesEpOdo> allPorBodega(Connection con, String db, Long id_bodegaEmpresa, String esPorSucursal, String id_sucursal){
		List<AjustesEpOdo> lista = new ArrayList<AjustesEpOdo>();
		try (PreparedStatement smt = con
				.prepareStatement(" select " +
						" ajustesEpOdo.id," +
						" ajustesEpOdo.id_bodegaEmpresa," +
						" ajustesEpOdo.id_tipoAjuste," +
						" ajustesEpOdo.concepto," +
						" ajustesEpOdo.fechaAjuste," +
						" ajustesEpOdo.id_moneda," +
						" ajustesEpOdo.totalAjuste," +
						" ajustesEpOdo.observaciones," +
						" ajustesEpOdo.ajustePDF," +
						" bodegaEmpresa.nombre," +
						" tipoAjuste.ajuste," +
						" moneda.nickName, " +
						" tipoAjuste.factor, " +
						" bodegaEmpresa.id_sucursal " +
						" from `"+db+"`.ajustesEpOdo" +
						" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = ajustesEpOdo.id_bodegaEmpresa" +
						" left join `"+db+"`.tipoAjuste on tipoAjuste.id = ajustesEpOdo.id_tipoAjuste" +
						" left Join `"+db+"`.moneda on moneda.id = ajustesEpOdo.id_moneda" +
						" where ajustesEpOdo.id_bodegaEmpresa = ? " +
						" order by ajustesEpOdo.fechaAjuste desc, ajustesEpOdo.id desc;")) {
			smt.setLong(1, id_bodegaEmpresa);
			try (ResultSet rs = smt.executeQuery()) {
				Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
				Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
				while (rs.next()) {
					switch(dec.get(rs.getLong(6)).toString()) {
						case "0": myformatdouble = new DecimalFormat("#,##0",symbols); break;
						case "2": myformatdouble = new DecimalFormat("#,##0.00",symbols); break;
						case "4": myformatdouble = new DecimalFormat("#,##0.0000",symbols); break;
						case "6": myformatdouble = new DecimalFormat("#,##0.000000",symbols); break;
						default:  break;
					}
					String fecha = "";
					if (rs.getString(5) != null) {
						fecha = myformatfecha.format(rs.getDate(5));
					}
					String nameSucursal = "";
					Sucursal sucursal  = mapSucursal.get(rs.getLong(14));
					if(sucursal!=null) {
						nameSucursal = sucursal.getNombre();
					}
					lista.add(new AjustesEpOdo(rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getString(4),fecha,rs.getLong(6),myformatdouble.format(rs.getDouble(7)),
							rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getLong(13),nameSucursal));
				}
			}
		} catch (SQLException e) {
			String className  = AjustesEpOdo.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (lista);
	}

	public static boolean modifyPDF(Connection con, String db, String ajustePDF, Long idAjuste) {
		boolean flag=true;
		try (PreparedStatement smt = con
				.prepareStatement("update `"+db+"`.ajustesEpOdo set ajustePDF = ?  WHERE id = ?")) {
			smt.setString(1, ajustePDF.trim());
			smt.setLong(2, idAjuste);
			smt.executeUpdate();
		} catch (SQLException e) {
			String className  = AjustesEpOdo.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
			flag=false;
		}
		return (flag);
	}

	public static AjustesEpOdo find(Connection con, String db, Long id_ajuste, String esPorSucursal, String id_sucursal){
		AjustesEpOdo aux = new AjustesEpOdo();
		try (PreparedStatement smt = con
				.prepareStatement(" select " +
						" ajustesEpOdo.id," +
						" ajustesEpOdo.id_bodegaEmpresa," +
						" ajustesEpOdo.id_tipoAjuste," +
						" ajustesEpOdo.concepto," +
						" ajustesEpOdo.fechaAjuste," +
						" ajustesEpOdo.id_moneda," +
						" ajustesEpOdo.totalAjuste," +
						" ajustesEpOdo.observaciones," +
						" ajustesEpOdo.ajustePDF," +
						" bodegaEmpresa.nombre," +
						" tipoAjuste.ajuste," +
						" moneda.nickName, " +
						" tipoAjuste.factor, " +
						" bodegaEmpresa.id_sucursal " +
						" from `"+db+"`.ajustesEpOdo" +
						" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = ajustesEpOdo.id_bodegaEmpresa" +
						" left join `"+db+"`.tipoAjuste on tipoAjuste.id = ajustesEpOdo.id_tipoAjuste" +
						" left Join `"+db+"`.moneda on moneda.id = ajustesEpOdo.id_moneda" +
						" where ajustesEpOdo.id = ? " +
						" order by ajustesEpOdo.fechaAjuste desc;")) {
			smt.setLong(1, id_ajuste);
			try (ResultSet rs = smt.executeQuery()) {
				Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
				Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
				if (rs.next()) {
					switch(dec.get(rs.getLong(6)).toString()) {
						case "0": myformatdouble = new DecimalFormat("#,##0",symbols); break;
						case "2": myformatdouble = new DecimalFormat("#,##0.00",symbols); break;
						case "4": myformatdouble = new DecimalFormat("#,##0.0000",symbols); break;
						case "6": myformatdouble = new DecimalFormat("#,##0.000000",symbols); break;
						default:  break;
					}
					String fecha = "";
					if (rs.getString(5) != null) {
						fecha = myformatfecha.format(rs.getDate(5));
					}
					String nameSucursal = "";
					Sucursal sucursal  = mapSucursal.get(rs.getLong(14));
					if(sucursal!=null) {
						nameSucursal = sucursal.getNombre();
					}

					aux = new AjustesEpOdo(rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getString(4),fecha,rs.getLong(6),myformatdouble.format(rs.getDouble(7)),
							rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getLong(13),nameSucursal);
				}
			}
		} catch (SQLException e) {
			String className  = AjustesEpOdo.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (aux);
	}

	public static boolean update(Connection con, String db, AjustesEpOdo aux){
		boolean flag = false;
		try {
			String auxNum = aux.totalAjuste;
			if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
			Double valor = myformatdouble.parse(auxNum).doubleValue();
			try (PreparedStatement smt = con
					.prepareStatement("update `"+db+"`.ajustesEpOdo set "+
							" id_bodegaEmpresa=?,id_tipoAjuste=?,concepto=?,fechaAjuste=?,id_moneda=?,totalAjuste=?,observaciones=? " +
							" where id=?;")) {
				smt.setLong(1, aux.id_bodegaEmpresa);
				smt.setLong(2, aux.id_tipoAjuste);
				smt.setString(3, aux.concepto);
				smt.setString(4, aux.fechaAjuste);
				smt.setLong(5, aux.id_moneda);
				smt.setDouble(6, valor);
				smt.setString(7, aux.observaciones);
				smt.setLong(8, aux.id);
				smt.executeUpdate();
				flag = true;
			}
		} catch (SQLException | ParseException e) {
			String className  = AjustesEpOdo.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (flag);
	}

	public static boolean delete(Connection con, String db, Long idAjuste){
		boolean flag = false;
		try (PreparedStatement smt = con
				.prepareStatement("delete from `"+db+"`.ajustesEpOdo where id=?;")) {
			smt.setLong(1, idAjuste);
			smt.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			String className  = AjustesEpOdo.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (flag);
	}

	public static boolean create(Connection con, String db, AjustesEpOdo aux){
		boolean flag = false;
		try {
			String auxNum = aux.totalAjuste;
			if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
			Double valor = myformatdouble.parse(auxNum).doubleValue();

			try (PreparedStatement smt2 = con
					.prepareStatement("Select id from `"+db+"`.ajustesEpOdo where "+
							" id_bodegaEmpresa=? and id_tipoAjuste=? and concepto=? and fechaAjuste=? and id_moneda=? and totalAjuste=? and observaciones=?;")) {
				smt2.setLong(1, aux.id_bodegaEmpresa);
				smt2.setLong(2, aux.id_tipoAjuste);
				smt2.setString(3, aux.concepto);
				smt2.setString(4, aux.fechaAjuste);
				smt2.setLong(5, aux.id_moneda);
				smt2.setDouble(6, valor);
				smt2.setString(7, aux.observaciones);
				try (ResultSet rs2 = smt2.executeQuery()) {
					if(!rs2.next()) {
						try (PreparedStatement smt = con
								.prepareStatement("insert into `"+db+"`.ajustesEpOdo "+
										" (id_bodegaEmpresa,id_tipoAjuste,concepto,fechaAjuste,id_moneda,totalAjuste,observaciones) values " +
										" (?,?,?,?,?,?,?);")) {
							smt.setLong(1, aux.id_bodegaEmpresa);
							smt.setLong(2, aux.id_tipoAjuste);
							smt.setString(3, aux.concepto);
							smt.setString(4, aux.fechaAjuste);
							smt.setLong(5, aux.id_moneda);
							smt.setDouble(6, valor);
							smt.setString(7, aux.observaciones);
							smt.executeUpdate();
							flag = true;
						}
					}
				}
			}
		} catch (SQLException | ParseException e) {
			String className  = AjustesEpOdo.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}

		return (flag);
	}

	public static List<AjustesEpOdo> allPorPeriodos(Connection con, String db, String desdeAAMMDD, String hastaAAMMDD, String esPorSucursal, String id_sucursal){
		List<AjustesEpOdo> lista = new ArrayList<AjustesEpOdo>();

		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and bodegaEmpresa.id_sucursal = " + id_sucursal;
		}

		try (PreparedStatement smt = con
				.prepareStatement(" select " +
						" ajustesEpOdo.id," +
						" ajustesEpOdo.id_bodegaEmpresa," +
						" ajustesEpOdo.id_tipoAjuste," +
						" ajustesEpOdo.concepto," +
						" ajustesEpOdo.fechaAjuste," +
						" ajustesEpOdo.id_moneda," +
						" ajustesEpOdo.totalAjuste," +
						" ajustesEpOdo.observaciones," +
						" ajustesEpOdo.ajustePDF," +
						" bodegaEmpresa.nombre," +
						" tipoAjuste.ajuste," +
						" moneda.nickName, " +
						" tipoAjuste.factor, " +
						" bodegaEmpresa.id_sucursal " +
						" from `"+db+"`.ajustesEpOdo" +
						" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = ajustesEpOdo.id_bodegaEmpresa" +
						" left join `"+db+"`.tipoAjuste on tipoAjuste.id = ajustesEpOdo.id_tipoAjuste" +
						" left Join `"+db+"`.moneda on moneda.id = ajustesEpOdo.id_moneda" +
						" where fechaAjuste between ? and ? " + condSucursal +
						" order by bodegaEmpresa.nombre, ajustesEpOdo.fechaAjuste desc;")) {
			smt.setString(1, desdeAAMMDD);
			smt.setString(2, hastaAAMMDD);
			try (ResultSet rs = smt.executeQuery()) {
				Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
				Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
				while (rs.next()) {
					switch(dec.get(rs.getLong(6)).toString()) {
						case "0": myformatdouble = new DecimalFormat("#,##0",symbols); break;
						case "2": myformatdouble = new DecimalFormat("#,##0.00",symbols); break;
						case "4": myformatdouble = new DecimalFormat("#,##0.0000",symbols); break;
						case "6": myformatdouble = new DecimalFormat("#,##0.000000",symbols); break;
						default:  break;
					}
					String fecha = "";
					if (rs.getString(5) != null) {
						fecha = myformatfecha.format(rs.getDate(5));
					}
					String nameSucursal = "";
					Sucursal sucursal  = mapSucursal.get(rs.getLong(14));
					if(sucursal!=null) {
						nameSucursal = sucursal.getNombre();
					}
					lista.add(new AjustesEpOdo(rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getString(4),fecha,rs.getLong(6),myformatdouble.format(rs.getDouble(7)),
							rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getLong(13),nameSucursal));
				}
			}
		} catch (SQLException e) {
			String className  = AjustesEpOdo.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (lista);
	}

}
