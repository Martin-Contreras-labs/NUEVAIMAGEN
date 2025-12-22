package models.tables;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.io.IOUtils;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.util.TempFile;

import models.utilities.Archivos;
import models.utilities.DecimalFormato;
import models.utilities.Fechas;






public class Compra {
	public Long id;
	public Long id_factura;
	public Long id_equipo;
	public Double cantidad;
	public Long id_moneda;
	public Double precioUnidad;
	public Long id_bodegaEmpresa;
	public Long esModificable;

	public Compra(Long id, Long id_factura, Long id_equipo, Double cantidad,
			Long id_moneda, Double precioUnidad, Long id_bodegaEmpresa,
			Long esModificable) {
		super();
		this.id = id;
		this.id_factura = id_factura;
		this.id_equipo = id_equipo;
		this.cantidad = cantidad;
		this.id_moneda = id_moneda;
		this.precioUnidad = precioUnidad;
		this.id_bodegaEmpresa = id_bodegaEmpresa;
		this.esModificable = esModificable;
	}

	public Compra() {
		super();
	}
	
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public Long getId_factura() {return id_factura;}
	public void setId_factura(Long id_factura) {this.id_factura = id_factura;}
	public Long getId_equipo() {return id_equipo;}
	public void setId_equipo(Long id_equipo) {this.id_equipo = id_equipo;}
	public Double getCantidad() {return cantidad;}
	public void setCantidad(Double cantidad) {this.cantidad = cantidad;}
	public Long getId_moneda() {return id_moneda;}
	public void setId_moneda(Long id_moneda) {this.id_moneda = id_moneda;}
	public Double getPrecioUnidad() {return precioUnidad;}
	public void setPrecioUnidad(Double precioUnidad) {this.precioUnidad = precioUnidad;}
	public Long getId_bodegaEmpresa() {return id_bodegaEmpresa;}
	public void setId_bodegaEmpresa(Long id_bodegaEmpresa) {this.id_bodegaEmpresa = id_bodegaEmpresa;}
	public Long getEsModificable() {return esModificable;}
	public void setEsModificable(Long esModificable) {this.esModificable = esModificable;}

	static DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
	static SimpleDateFormat myformatfecha = new SimpleDateFormat("dd-MM-yyyy");
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00",symbols);
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00",symbols);
	static DecimalFormat myformatint = new DecimalFormat("#,##0",symbols);

	
	
	
	
	public static Map<Long,List<Double>> ultimoPrecio(Connection con, String db){
		Map<Long,List<Double>> aux = new HashMap<Long,List<Double>>();
		try {
			PreparedStatement smt2 = con
					.prepareStatement("select max(concat(factura.fecha,'_',compra.id)) from `"+db+"`.compra "
							+ " left join `"+db+"`.factura on factura.id = compra.id_factura  group by id_equipo;");

			ResultSet rs2 = smt2.executeQuery();
			String lista ="";
			while (rs2.next()) {
				String[] arrAux = rs2.getString(1).split("_");
				lista = lista + arrAux[1] + ",";
			}
			rs2.close();smt2.close();
			if(lista.length()>1) {
				lista=lista.substring(0,lista.length()-1);
				PreparedStatement smt = con
						.prepareStatement(" select " +
								" id_equipo,  " +
								" precioUnidad," +
								" id_moneda  " +
								" from `"+db+"`.compra  " +
								" where compra.id in ("+lista+") ;");
				ResultSet rs = smt.executeQuery();
				while (rs.next()) {
					List<Double> aux2 = new ArrayList<Double>();
					aux2.add(rs.getDouble(2)); // 0 precio
					aux2.add(rs.getDouble(3)); // 1 id moneda
					aux.put(rs.getLong(1),aux2);  // k= idequipo, v = precio, id_moneda
				}
				rs.close();
				smt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}
	
	public static Map<Long,List<String>> ultimoPrecioMasFactura(Connection con, String db){
		Map<Long,List<String>> aux = new HashMap<Long,List<String>>();
		try {
			PreparedStatement smt2 = con
					.prepareStatement("select max(concat(factura.fecha,'_',compra.id)) from `"+db+"`.compra "
							+ " left join `"+db+"`.factura on factura.id = compra.id_factura  group by id_equipo;");

			ResultSet rs2 = smt2.executeQuery();
			String lista ="";
			while (rs2.next()) {
				String[] arrAux = rs2.getString(1).split("_");
				lista = lista + arrAux[1] + ",";
			}
			rs2.close();smt2.close();
			if(lista.length()>1) {
				lista=lista.substring(0,lista.length()-1);
				PreparedStatement smt = con
						.prepareStatement(" select"
								+ " compra.id_equipo,"
								+ " compra.precioUnidad,"
								+ " moneda.nickName,"
								+ " factura.fecha,"
								+ " factura.numero,"
								+ " proveedor.nickName,"
								+ " moneda.id"
								+ " from `"+db+"`.compra"
								+ " left join `"+db+"`.moneda on moneda.id = compra.id_moneda"
								+ " left join `"+db+"`.factura on factura.id = compra.id_factura"
								+ " left join `"+db+"`.proveedor on proveedor.id = factura.id_proveedor"
								+ " where compra.id in ("+lista+") ;");
				ResultSet rs = smt.executeQuery();
				while (rs.next()) {
					List<String> aux2 = new ArrayList<String>();
					aux2.add(rs.getString(2)); // 0 precioUnidad
					aux2.add(rs.getString(3)); // 1 moneda.nickName
					aux2.add(rs.getString(4)); // 2 factura.fecha
					aux2.add(rs.getString(5)); // 3 factura.numero
					aux2.add(rs.getString(6)); // 4 proveedor

					aux2.add(rs.getString(7)); // 5 moneda.id

					aux.put(rs.getLong(1),aux2);  
				}
				rs.close();
				smt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}
	
	public static Map<Long,String> ultimoMonedaPrecio(Connection con, String db){
		Map<Long,String> aux = new HashMap<Long,String>();
		try {
			PreparedStatement smt2 = con
					.prepareStatement("select max(concat(factura.fecha,'_',compra.id)) from `"+db+"`.compra "
							+ " left join `"+db+"`.factura on factura.id = compra.id_factura group by id_equipo;");
			String listaCond ="";
			ResultSet rs2 = smt2.executeQuery();
			while (rs2.next()) {
				String[] arrAux = rs2.getString(1).split("_");
				listaCond = listaCond + arrAux[1] + ",";
			}
			rs2.close();smt2.close();
			
			
			if(listaCond.length()>1) {
				listaCond = listaCond.substring(0,listaCond.length()-1);
				PreparedStatement smt = con
						.prepareStatement(" select " +
								" compra.id_equipo,  " +
								" moneda.nickname  " +
								" from `"+db+"`.compra  " +
								" left join `"+db+"`.moneda on moneda.id = compra.id_moneda " +
								" where compra.id in " +
								" ("+listaCond+");");
				ResultSet rs = smt.executeQuery();
				while (rs.next()) {
					aux.put(rs.getLong(1),rs.getString(2));
				}
				rs.close();
				smt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}
	
	public static boolean create(Connection con, String db, String detalle) {
		boolean flag = false;
		try {
			PreparedStatement smt1 = con
					.prepareStatement("insert into `"+db+"`.compra (id_factura, id_equipo, cantidad, id_moneda, precioUnidad, id_bodegaEmpresa)"
							+ " values "+detalle+";");
			smt1.executeUpdate();
			smt1.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean modifyXCampo(Connection con, String db, String campo, String valor, Long id_compra) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("update `"+db+"`.compra set `"+campo+"` = ? WHERE id = ?;");
			smt.setString(1, valor.trim());
			smt.setLong(2, id_compra);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean delete(Connection con, String db, Long id_compra) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("delete from `"+db+"`.compra WHERE id = ?;");
			smt.setLong(1, id_compra);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static List<List<String>> listaConfirmaIngreso(Connection con, String db, String esPorSucursal, String id_sucursal, Map<String,String> mapeoDiccionario) {
		List<List<String>> lista = new ArrayList<List<String>>();
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and bodegaEmpresa.id_sucursal = " + id_sucursal;
		}
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" compra.id, " +
							" esModificable, " +
							" factura.fecha, " +
							" factura.numero, " +
							" equipo.codigo, " +
							" equipo.nombre as equipo, " +
							" unidad.nombre as unidad, " +
							" compra.cantidad as cantidad, " +
							" ifnull(bodegaEmpresa.nombre,' ') as destino, " +
							" bodegaEmpresa.id, " +
							" equipo.id, " +
							" factura.observaciones, " +
							" sucursal.nombre, " +
							" bodegaEmpresa.id_sucursal, " +
							" factura.id, " +
							" compra.id_moneda, " +
							" compra.precioUnidad, " +
							" compra.cantidad*compra.precioUnidad " +
							" from `"+db+"`.factura " +
							" left join `"+db+"`.compra on compra.id_factura = factura.id " +
							" left join `"+db+"`.equipo on equipo.id = compra.id_equipo " +
							" left join `"+db+"`.unidad on unidad.id = equipo.id_unidad " +
							" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = compra.id_bodegaEmpresa " +
							" left join `"+db+"`.sucursal on sucursal.id = bodegaEmpresa.id_sucursal " +
							" where codigo is not null and esModificable=1 and bodegaEmpresa.nombre is not null " + condSucursal +
							" order by factura.fecha desc, equipo.nombre;");
	
			ResultSet rs = smt.executeQuery();
			
			Map<String,Precio> mapAllSucursales = Precio.mapAllSucursales(con, db, mapeoDiccionario);
			Map<Long,Moneda> mapMonedas =  Moneda.mapMonedas(con, db);
			Map<Long,UnidadTiempo> mapUnTiempo = UnidadTiempo.mapUnidadTiempo(con, db);
			
			
			while (rs.next()) {
				Long idSucursal = rs.getLong(14);
				Long idEquipo = rs.getLong(11);
				Long idMoneda =  rs.getLong(16);
				Double pCompra = rs.getDouble(17);
				Double tCompra = rs.getDouble(18);
				
				
				Moneda moneda = mapMonedas.get(idMoneda);
				if(moneda == null) {
					moneda = Moneda.find(con, db, (long)1);
				}
				String monCompra = moneda.getNickName();
				Long decimales = moneda.getNumeroDecimales();
				String puCompra = DecimalFormato.formato(pCompra, decimales);
				String totCompra = DecimalFormato.formato(tCompra, decimales);
				
				Precio precio = mapAllSucursales.get(idSucursal + "_" + idEquipo);
				String monVta = "";
				String puVta = "";
				String unTiempo = "";
				String puArr = "";
				if(precio != null) {
					monVta = precio.getNombreMoneda();
					puVta = precio.getPrecioReposicion();
					unTiempo = precio.getNombreUnidadTiempo();
					puArr = precio.getPrecioArriendo();
				}else {
					moneda = Moneda.find(con, db, (long)1);
					puVta = "0";
					unTiempo = "MES";
					puArr = "0";
				}
				
				
				
				
				
				List<String> aux = new ArrayList<String>();
				aux.add(rs.getString(1));							// 0 compra.id
				aux.add(rs.getString(2));							// 1 esModificable
				aux.add(rs.getString(10));							// 2 bodegaEmpresa.id destino
				aux.add(rs.getString(3));							// 3 factura.fecha
				aux.add(rs.getString(4));							// 4 factura.numero
				aux.add(rs.getString(5));							// 5 equipo.codigo
				aux.add(rs.getString(6));							// 6 equipo.nombre
				aux.add(rs.getString(7));							// 7 equipo.unidad
				aux.add(myformatdouble2.format(rs.getDouble(8)));	// 8 cantidad
				aux.add(rs.getString(9));							// 9 bodegaEmpresa.nombre destino
				aux.add(rs.getString(11));							// 10 equipo.id
				aux.add(rs.getString(12));							// 11 equipo.id
				aux.add(rs.getString(13));							// 12 nameSucursal
				aux.add(rs.getString(14));							// 13 id sucursal
				aux.add(rs.getString(15));							// 14 id factura
				
				aux.add(monCompra);						// 15 monde de compra
				aux.add(puCompra);						// 16 pu compra
				aux.add(totCompra);						// 17 total compra
				aux.add(monVta);						// 18 moneda vta y arr
				aux.add(puVta);							// 19 pu vta
				aux.add(unTiempo);						// 20 unidad tiempo
				aux.add(puArr);							// 21 pu arriendo
				
				
				lista.add(aux);
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static boolean actualizaPorCampo(Connection con, String db, String campo, Long id_compra, String valor) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("update `"+db+"`.compra set `"+campo+"` = ? WHERE id = ?;");
			smt.setString(1, valor);
			smt.setLong(2, id_compra);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static List<List<String>> listaCompraPorEquipo(Connection con, String db){
		List<List<String>> lista = new ArrayList<List<String>>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select max(compra.id), max(id_factura), " +
							" grupo.nombre, equipo.codigo, equipo.nombre, unidad.nombre, sum(cantidad), moneda.nickName, " +
							" moneda.id, equipo.id, factura.facturaPDF, factura.fecha " +
							" from `"+db+"`.compra " +
							" left join `"+db+"`.equipo on equipo.id = id_equipo " +
							" left join `"+db+"`.unidad on unidad.id = equipo.id_unidad " +
							" left join `"+db+"`.grupo on grupo.id = equipo.id_grupo " +
							" left join `"+db+"`.moneda on moneda.id = compra.id_moneda " +
							" left join `"+db+"`.factura on factura.id = compra.id_factura " +
							" group by grupo.id, compra.id_equipo " +
							" order by grupo.nombre,equipo.nombre;");
			ResultSet rs = smt.executeQuery();
			Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
			Map<Long,Double> bajas = Baja.totalDeBajasPorIdEquipo(con, db);
			Map<Long,List<Double>> ultimoPrecio = Compra.ultimoPrecio(con, db);
			Long cont = (long) 1000;
			while (rs.next()) {
				cont++;
				
				Double baja = bajas.get(rs.getLong(10));
				if(baja == null) {
					baja = (double) 0;
				}
				
				List<Double> auxCompra = ultimoPrecio.get(rs.getLong(10));
				Double compra = (double) 0;
				if(auxCompra!=null) {
					compra = auxCompra.get(0);
				}
				
				Long numDec = dec.get(rs.getLong(9));
				if(numDec == null) {
					numDec = (long) 0;
				}
				
				switch(numDec.toString()) {
				 case "0": myformatdouble = new DecimalFormat("#,##0",symbols); break;
				 case "2": myformatdouble = new DecimalFormat("#,##0.00",symbols); break;
				 case "4": myformatdouble = new DecimalFormat("#,##0.0000",symbols); break;
				 case "6": myformatdouble = new DecimalFormat("#,##0.000000",symbols); break;
				 default:  break;
				}
				
				String fecha = Fechas.DDMMAA(rs.getString(12));
				
				List<String> aux = new ArrayList<String>();
				aux.add(rs.getString(10));							// 0 id_equipo
				aux.add(rs.getString(3));							// 1 grupo.nombre
				aux.add(rs.getString(4));							// 2 equipo.codigo
				aux.add(rs.getString(5));							// 3 equipo.nombre
				aux.add(rs.getString(6));							// 4 unidad.nombre
				aux.add(myformatint.format(rs.getDouble(7)-baja));	// 5 cantidad - bajas
				aux.add(rs.getString(8));							// 6 moneda.nickName
				aux.add(myformatdouble.format(compra));				// 7 ultimo precio de compra
				aux.add(fecha);										// 8 fecha factura
				aux.add(rs.getString(11));							// 9 doc pdf de factura
				lista.add(aux);				
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<List<String>> allPorEquipo(Connection con, String db, Long id_equipo){
		List<List<String>> lista = new ArrayList<List<String>>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" factura.facturaPDF, " +
							" proveedor.rut, " +
							" proveedor.nickName, " +
							" factura.fecha, " +
							" factura.numero, " +
							" equipo.codigo, " +
							" equipo.nombre, " +
							" unidad.nombre, " +
							" cantidad, " +
							" moneda.nickName, " +
							" compra.precioUnidad, " +
							" moneda.id " +
							" from `"+db+"`.compra " +
							" left join `"+db+"`.factura on factura.id = compra.id_factura " +
							" left join `"+db+"`.proveedor on proveedor.id = factura.id_proveedor " +
							" left join `"+db+"`.equipo on equipo.id = compra.id_equipo   " +
							" left join `"+db+"`.unidad on unidad.id = equipo.id_unidad   " +
							" left join `"+db+"`.grupo on grupo.id = equipo.id_grupo   " +
							" left join `"+db+"`.moneda on moneda.id = compra.id_moneda   " +
							" where compra.id_equipo= ? "+
							" order by factura.fecha desc;");
			smt.setLong(1, id_equipo);
			ResultSet rs = smt.executeQuery();
			Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
			while (rs.next()) {
				List<String> aux = new ArrayList<String>();
				String fecha = null;		
				if (rs.getDate(4) != null) {
					fecha = myformatfecha.format(rs.getDate(4));
				}
				Long numDec = dec.get(rs.getLong(12));
				if(numDec==null) numDec = (long)0;
				switch(numDec.toString()) {
				 case "0": myformatdouble = new DecimalFormat("#,##0",symbols); break;
				 case "2": myformatdouble = new DecimalFormat("#,##0.00",symbols); break;
				 case "4": myformatdouble = new DecimalFormat("#,##0.0000",symbols); break;
				 case "6": myformatdouble = new DecimalFormat("#,##0.000000",symbols); break;
				 default:  break;
				}
				aux.add(rs.getString(1));								// 0 factura.facturaPDF
				aux.add(rs.getString(2));								// 1 proveedor.rut
				aux.add(rs.getString(3));								// 2 proveedor.nickName
				aux.add(fecha);											// 3 factura.fecha
				aux.add(rs.getString(5));								// 4 factura.numero
				aux.add(rs.getString(6));								// 5 equipo.codigo
				aux.add(rs.getString(7));								// 6 equipo.nombre
				aux.add(rs.getString(8));								// 7 unidad.nombre
				aux.add(myformatint.format(rs.getLong(9)));				// 8 cantidad
				aux.add(rs.getString(10));								// 9 moneda.nickName
				aux.add(myformatdouble.format(rs.getDouble(11)));		// 10 compra.precioUnidad
				lista.add(aux);
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<List<String>> allPorFactura(Connection con, String db, Long id_factura){
		List<List<String>> lista = new ArrayList<List<String>>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" compra.id, " +
							" compra.id_bodegaEmpresa, " +
							" compra.id_equipo, " +
							" compra.cantidad, " +
							" factura.fecha " +
							" from `"+db+"`.compra " +
							" left join `"+db+"`.factura on factura.id = compra.id_factura " +
							" where compra.id_factura = ?;");
			smt.setLong(1, id_factura);
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				List<String> aux = new ArrayList<String>();
				aux.add(rs.getString(1));								// 0 id_compra
				aux.add(rs.getString(2));								// 1 id_bodegaEmpresa
				aux.add(rs.getString(3));								// 2 id_equipo
				aux.add(rs.getString(4));								// 3 cantidad
				aux.add(rs.getString(5));								// 4 fecha_factura
				lista.add(aux);
				
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static boolean deleteAllPorFacturaYfactura(Connection con, String db, Long id_factura) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("delete from `"+db+"`.factura where id=?;");
			smt.setLong(1,id_factura);
			smt.executeUpdate();
			smt.close();
			PreparedStatement smt2 = con
					.prepareStatement("delete from `"+db+"`.compra where id_factura=?;");
			smt2.setLong(1,id_factura);
			smt2.executeUpdate();
			smt2.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean deleteAllDetallePorSucursal(Connection con, String db, Long id_factura, String id_sucursal) {
		boolean flag = false;
		try {
			PreparedStatement smt1 = con
					.prepareStatement("delete `"+db+"`.compra from `"+db+"`.compra "
							+ " left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = compra.id_bodegaEmpresa "
							+ " where id_factura=? and id_sucursal=?;");
			smt1.setLong(1,id_factura);
			smt1.setString(2,id_sucursal);
			smt1.executeUpdate();
			smt1.close();
			
			PreparedStatement smt2 = con
					.prepareStatement("select id from `"+db+"`.compra where id_factura=?;");
			smt2.setLong(1,id_factura);
			ResultSet rs = smt2.executeQuery();
			if(!rs.next()) {
				PreparedStatement smt = con
						.prepareStatement("delete from `"+db+"`.factura where id=?;");
				smt.setLong(1,id_factura);
				smt.executeUpdate();
				smt.close();
			}
			smt2.close();
			rs.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static File plantillaCompras(Connection con, String db) {
		File tmp = null;
try{
	tmp = TempFile.createTempFile("tmp","null");
}catch(Exception e){}
		
		
		List<Moneda> listMonedas = Moneda.all(con,db);
		List<Unidad> listUnidades = Unidad.all(con,db);
		List<List<String>> listBodegas = BodegaEmpresa.listaAllBodegasVigentesInternas(con,db, "0", "0");
		List<Equipo> listEquipos = Equipo.allAll(con,db);
		List<Grupo> listGrupos = Grupo.all(con, db);
		List<Proveedor> listProveedor = Proveedor.all(con, db);
		
		try {
			InputStream formato = Archivos.leerArchivo("formatos/plantillaCompras.xlsx");
            Workbook libro = WorkbookFactory.create(formato);
            formato.close();
            
            CellStyle subtitulo = libro.createCellStyle();
            Font font2 = libro.createFont();
            font2.setBold(true);
            font2.setColor((short)0);
            font2.setFontHeight((short)(12*20));
            subtitulo.setFont(font2);
         
            CellStyle detalle = libro.createCellStyle();
            detalle.setBorderBottom(BorderStyle.THIN);
            detalle.setBorderTop(BorderStyle.THIN);
            detalle.setBorderRight(BorderStyle.THIN);
            detalle.setBorderLeft(BorderStyle.THIN);
            
            Sheet hoja2 = libro.getSheetAt(1);
            Row row = null;
            Cell cell = null;
		
            
          //DETALLE DE AUXILIARES
            
            int posRow = 2;
            for(int i=0; i<listMonedas.size(); i++){
            	int aux = posRow +i;
            	row = hoja2.getRow(aux);
            	if(row == null) {
            		row = hoja2.createRow(aux);
            	}
            	cell = row.createCell(1);
            	cell.setCellValue(listMonedas.get(i).getNickName());
            }
			
            for(int i=0; i<listUnidades.size(); i++){
            	int aux = posRow +i;
            	row = hoja2.getRow(aux);
            	if(row == null) {
            		row = hoja2.createRow(aux);
            	}
            	cell = row.createCell(3);
            	cell.setCellValue(listUnidades.get(i).getNombre());
            }
            
            for(int i=0; i<listBodegas.size(); i++){
            	int aux = posRow +i;
            	row = hoja2.getRow(aux);
            	if(row == null) {
            		row = hoja2.createRow(aux);
            	}
            	cell = row.createCell(5);
            	cell.setCellValue(listBodegas.get(i).get(5));
            }
            
            for(int i=0; i<listEquipos.size(); i++){
            	int aux = posRow +i;
            	row = hoja2.getRow(aux);
            	if(row == null) {
            		row = hoja2.createRow(aux);
            	}
            	cell = row.createCell(7);
            	cell.setCellValue(listEquipos.get(i).getCodigo());
            	cell = row.createCell(8);
            	cell.setCellValue(listEquipos.get(i).getNombre());
            }
            
            for(int i=0; i<listGrupos.size(); i++){
            	int aux = posRow +i;
            	row = hoja2.getRow(aux);
            	if(row == null) {
            		row = hoja2.createRow(aux);
            	}
            	cell = row.createCell(10);
            	cell.setCellValue(listGrupos.get(i).getNombre());
            }
            
            for(int i=0; i<listProveedor.size(); i++){
            	int aux = posRow +i;
            	row = hoja2.getRow(aux);
            	if(row == null) {
            		row = hoja2.createRow(aux);
            	}
            	cell = row.createCell(12);
            	cell.setCellValue(listProveedor.get(i).getNickName());
            }
			
			// Write the output to a file tmp
			FileOutputStream fileOut = new FileOutputStream(tmp);
			libro.write(fileOut);
			fileOut.close();
			
		} catch (Exception e) {
			e.printStackTrace();
        }
		return(tmp);
	}
	

	
}
