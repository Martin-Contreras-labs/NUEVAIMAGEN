package models.tables;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.TempFile;

import models.utilities.Archivos;
import models.utilities.Fechas;


public class Precio {
	public Long id_sucursal;
	public Long id_equipo;
	public Long id_moneda;
	public String fecha;
	public String precioVenta;
	public String precioReposicion;
	public String precioArriendo;
	public Long id_unidadTiempo;
	
	public String codigoEquipo;
	public String nombreEquipo;
	public String nombreMoneda;
	public String nombreUnidadTiempo;
	public Long id_grupo;
	public String nombreGrupo;
	public String precioCompra;
	public String monedaCompra;
	public String tasaArriendo;
	
	public String precioMinimo;
	public String permanenciaMinima;

	

	public Precio(Long id_sucursal, Long id_equipo, Long id_moneda, String fecha,
			String precioVenta, String precioReposicion, String precioArriendo,
			Long id_unidadTiempo, String codigoEquipo, String nombreEquipo,
			String nombreMoneda, String nombreUnidadTiempo, Long id_grupo,
			String nombreGrupo, String precioCompra, String monedaCompra,
			String tasaArriendo, String precioMinimo, String permanenciaMinima) {
		super();
		this.id_sucursal = id_sucursal;
		this.id_equipo = id_equipo;
		this.id_moneda = id_moneda;
		this.fecha = fecha;
		this.precioVenta = precioVenta;
		this.precioReposicion = precioReposicion;
		this.precioArriendo = precioArriendo;
		this.id_unidadTiempo = id_unidadTiempo;
		this.codigoEquipo = codigoEquipo;
		this.nombreEquipo = nombreEquipo;
		this.nombreMoneda = nombreMoneda;
		this.nombreUnidadTiempo = nombreUnidadTiempo;
		this.id_grupo = id_grupo;
		this.nombreGrupo = nombreGrupo;
		this.precioCompra = precioCompra;
		this.monedaCompra = monedaCompra;
		this.tasaArriendo = tasaArriendo;
		this.precioMinimo = precioMinimo;
		this.permanenciaMinima = permanenciaMinima;
	}
	
	
	public Precio() {super();}
	
	public Long getId_sucursal() {return id_sucursal;}
	public void setId_sucursal(Long id_sucursal) {this.id_sucursal = id_sucursal;}
	public Long getId_equipo() {return id_equipo;}
	public void setId_equipo(Long id_equipo) {this.id_equipo = id_equipo;}
	public Long getId_moneda() {return id_moneda;}
	public void setId_moneda(Long id_moneda) {this.id_moneda = id_moneda;}
	public String getFecha() {return fecha;}
	public void setFecha(String fecha) {this.fecha = fecha;}
	public String getPrecioVenta() {return precioVenta;}
	public void setPrecioVenta(String precioVenta) {this.precioVenta = precioVenta;}
	public String getPrecioReposicion() {return precioReposicion;}
	public void setPrecioReposicion(String precioReposicion) {this.precioReposicion = precioReposicion;}
	public String getPrecioArriendo() {return precioArriendo;}
	public void setPrecioArriendo(String precioArriendo) {this.precioArriendo = precioArriendo;}
	public Long getId_unidadTiempo() {return id_unidadTiempo;}
	public void setId_unidadTiempo(Long id_unidadTiempo) {this.id_unidadTiempo = id_unidadTiempo;}
	public String getCodigoEquipo() {return codigoEquipo;}
	public void setCodigoEquipo(String codigoEquipo) {this.codigoEquipo = codigoEquipo;}
	public String getNombreEquipo() {return nombreEquipo;}
	public void setNombreEquipo(String nombreEquipo) {this.nombreEquipo = nombreEquipo;}
	public String getNombreMoneda() {return nombreMoneda;}
	public void setNombreMoneda(String nombreMoneda) {this.nombreMoneda = nombreMoneda;}
	public String getNombreUnidadTiempo() {return nombreUnidadTiempo;}
	public void setNombreUnidadTiempo(String nombreUnidadTiempo) {this.nombreUnidadTiempo = nombreUnidadTiempo;}
	public Long getId_grupo() {	return id_grupo;}
	public void setId_grupo(Long id_grupo) {this.id_grupo = id_grupo;}
	public String getNombreGrupo() {return nombreGrupo;}
	public void setNombreGrupo(String nombreGrupo) {this.nombreGrupo = nombreGrupo;}
	public String getPrecioCompra() {return precioCompra;}
	public void setPrecioCompra(String precioCompra) {this.precioCompra = precioCompra;}
	public String getMonedaCompra() {return monedaCompra;}
	public void setMonedaCompra(String monedaCompra) {this.monedaCompra = monedaCompra;}
	public String getTasaArriendo() {return tasaArriendo;}
	public void setTasaArriendo(String tasaArriendo) {this.tasaArriendo = tasaArriendo;}
	public String getPrecioMinimo() {return precioMinimo;}
	public void setPrecioMinimo(String precioMinimo) {this.precioMinimo = precioMinimo;}
	public String getPermanenciaMinima() {return permanenciaMinima;}
	public void setPermanenciaMinima(String permanenciaMinima) {this.permanenciaMinima = permanenciaMinima;}


	static SimpleDateFormat myformatfecha = new SimpleDateFormat("dd-MM-yyyy");
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdoubleCompra = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatint = new DecimalFormat("#,##0");
	
	
	
	public static Map<Long,Precio> mapAll(Connection con, String db, Map<String,String> mapeoDiccionario, Long id_sucursal) {
		Map<Long,Precio> map = new HashMap<Long,Precio>();
		List<Precio> lista = Precio.all(con, db, mapeoDiccionario, id_sucursal);
		lista.forEach(x->{
			map.put(x.getId_equipo(), x);
		});
		return(map);
	}
	
	public static List<Precio> all(Connection con, String db, Map<String,String> mapeoDiccionario, Long id_sucursal) {
		List<Precio> lista = new ArrayList<Precio>();
		Map<Long,List<Double>> compra = Compra.ultimoPrecio(con, db);
		Map<Long,String> monedaCompra = Compra.ultimoMonedaPrecio(con, db);
		Map<String,Long> decCompra = Moneda.numeroDecimalxNombre(con, db);
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" precio.id_sucursal, " +
							" precio.id_equipo, " +
							" precio.id_moneda, " +
							" precio.fecha, " +
							" precio.precioVenta, " +
							" precio.precioReposicion, " +
							" precio.precioArriendo, " +
							" precio.id_unidadTiempo, " +
							" equipo.codigo, " +
							" equipo.nombre, " +
							" moneda.nickName, " +
							" unidadTiempo.nombre, " +
							" grupo.id, " +
							" grupo.nombre, " +
							" precioMinimo, " +
							" permanenciaMinima " +
							" from `"+db+"`.precio " +
							" left join `"+db+"`.equipo on equipo.id = precio.id_equipo " +
							" left join `"+db+"`.moneda on moneda.id = precio.id_moneda " +
							" left join `"+db+"`.unidadTiempo on unidadTiempo.id = precio.id_unidadTiempo " +
							" left join `"+db+"`.grupo on grupo.id = equipo.id_grupo" +
							" where precio.id_sucursal =? " +
							" order by grupo.nombre,equipo.nombre;");
			smt.setLong(1, id_sucursal);
			ResultSet rs = smt.executeQuery();
			Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
			while (rs.next()) {
				switch(dec.get(rs.getLong(3)).toString()) {
				 case "0": myformatdouble = new DecimalFormat("#,##0"); break;
				 case "2": myformatdouble = new DecimalFormat("#,##0.00"); break;
				 case "4": myformatdouble = new DecimalFormat("#,##0.0000"); break;
				 case "6": myformatdouble = new DecimalFormat("#,##0.000000"); break;
				 default:  break;
				}
				String monedaCompraAux = mapeoDiccionario.get("CLP");
				if(monedaCompra.get(rs.getLong(2))!=null) {
					monedaCompraAux = monedaCompra.get(rs.getLong(2));
				}
				Long auxDecimal = decCompra.get(monedaCompraAux.toUpperCase());
				if(auxDecimal==null) auxDecimal = (long)2;
				switch(auxDecimal.toString()) {
				 case "0": myformatdoubleCompra = new DecimalFormat("#,##0"); break;
				 case "2": myformatdoubleCompra = new DecimalFormat("#,##0.00"); break;
				 case "4": myformatdoubleCompra = new DecimalFormat("#,##0.0000"); break;
				 case "6": myformatdoubleCompra = new DecimalFormat("#,##0.000000"); break;
				 default:  break;
				}
				Double compraAux = (double)0;
				try{
					compraAux = compra.get(rs.getLong(2)).get(0);
				}catch(Exception e){}
				Double tasaArriendo=(double)0;
				if(rs.getDouble(7)>=0 && rs.getDouble(5)>0) {
					tasaArriendo= rs.getDouble(7)/rs.getDouble(5);
				}
				lista.add(new Precio(
						rs.getLong(1),
						rs.getLong(2),
						rs.getLong(3),
						myformatfecha.format(rs.getDate(4)),
						myformatdouble.format(rs.getDouble(5)),
						myformatdouble.format(rs.getDouble(6)),
						myformatdouble.format(rs.getDouble(7)),
						rs.getLong(8),
						rs.getString(9),
						rs.getString(10),
						rs.getString(11),
						rs.getString(12),
						rs.getLong(13),
						rs.getString(14),
						myformatdoubleCompra.format(compraAux),
						monedaCompra.get(rs.getLong(2)),
						myformatdouble2.format(tasaArriendo*100)+" %",
						myformatdouble.format(rs.getDouble(15)),
						myformatint.format(rs.getDouble(16))));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (lista);
	}
	
	public static Map<String,Precio> mapAllSucursales(Connection con, String db, Map<String,String> mapeoDiccionario) {
		Map<String,Precio> map = new HashMap<String,Precio>();
		List<Precio> lista = Precio.allSucursales(con, db, mapeoDiccionario);
		lista.forEach(x->{
			map.put(x.getId_sucursal() + "_" + x.getId_equipo(), x);
		});
		return(map);
	}
	
	public static Map<Long,Precio> mapVigPorSucursal(Connection con, String db, Map<String,String> mapeoDiccionario, Long id_sucursal) {
		Map<Long,Precio> map = new HashMap<Long,Precio>();
		List<Precio> lista = Precio.allSucursales(con, db, mapeoDiccionario);
		lista.forEach(x->{
			if((long) x.getId_sucursal() == (long) id_sucursal) {
				map.put(x.getId_equipo(), x);
			}
		});
		return(map);
	}
	
	
	public static List<Precio> allSucursales(Connection con, String db, Map<String,String> mapeoDiccionario) {
		List<Precio> lista = new ArrayList<Precio>();
		Map<Long,List<Double>> compra = Compra.ultimoPrecio(con, db);
		Map<Long,String> monedaCompra = Compra.ultimoMonedaPrecio(con, db);
		Map<String,Long> decCompra = Moneda.numeroDecimalxNombre(con, db);
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" precio.id_sucursal, " +
							" precio.id_equipo, " +
							" precio.id_moneda, " +
							" precio.fecha, " +
							" precio.precioVenta, " +
							" precio.precioReposicion, " +
							" precio.precioArriendo, " +
							" precio.id_unidadTiempo, " +
							" equipo.codigo, " +
							" equipo.nombre, " +
							" moneda.nickName, " +
							" unidadTiempo.nombre, " +
							" grupo.id, " +
							" grupo.nombre, " +
							" precioMinimo, " +
							" permanenciaMinima " +
							" from `"+db+"`.precio " +
							" left join `"+db+"`.equipo on equipo.id = precio.id_equipo " +
							" left join `"+db+"`.moneda on moneda.id = precio.id_moneda " +
							" left join `"+db+"`.unidadTiempo on unidadTiempo.id = precio.id_unidadTiempo " +
							" left join `"+db+"`.grupo on grupo.id = equipo.id_grupo" +
							" order by grupo.nombre,equipo.nombre;");
			ResultSet rs = smt.executeQuery();
			Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
			while (rs.next()) {
				switch(dec.get(rs.getLong(3)).toString()) {
				 case "0": myformatdouble = new DecimalFormat("#,##0"); break;
				 case "2": myformatdouble = new DecimalFormat("#,##0.00"); break;
				 case "4": myformatdouble = new DecimalFormat("#,##0.0000"); break;
				 case "6": myformatdouble = new DecimalFormat("#,##0.000000"); break;
				 default:  break;
				}
				String monedaCompraAux = mapeoDiccionario.get("CLP");
				if(monedaCompra.get(rs.getLong(2))!=null) {
					monedaCompraAux = monedaCompra.get(rs.getLong(2));
				}
				Long auxDecimal = decCompra.get(monedaCompraAux.toUpperCase());
				if(auxDecimal==null) auxDecimal = (long)2;
				switch(auxDecimal.toString()) {
				 case "0": myformatdoubleCompra = new DecimalFormat("#,##0"); break;
				 case "2": myformatdoubleCompra = new DecimalFormat("#,##0.00"); break;
				 case "4": myformatdoubleCompra = new DecimalFormat("#,##0.0000"); break;
				 case "6": myformatdoubleCompra = new DecimalFormat("#,##0.000000"); break;
				 default:  break;
				}
				Double compraAux = (double)0;
				try{
					compraAux = compra.get(rs.getLong(2)).get(0);
				}catch(Exception e){}
				Double tasaArriendo=(double)0;
				if(rs.getDouble(7)>=0 && rs.getDouble(5)>0) {
					tasaArriendo= rs.getDouble(7)/rs.getDouble(5);
				}
				lista.add(new Precio(
						rs.getLong(1),
						rs.getLong(2),
						rs.getLong(3),
						myformatfecha.format(rs.getDate(4)),
						myformatdouble.format(rs.getDouble(5)),
						myformatdouble.format(rs.getDouble(6)),
						myformatdouble.format(rs.getDouble(7)),
						rs.getLong(8),
						rs.getString(9),
						rs.getString(10),
						rs.getString(11),
						rs.getString(12),
						rs.getLong(13),
						rs.getString(14),
						myformatdoubleCompra.format(compraAux),
						monedaCompra.get(rs.getLong(2)),
						myformatdouble2.format(tasaArriendo*100)+" %",
						myformatdouble.format(rs.getDouble(15)),
						myformatint.format(rs.getDouble(16))));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<Precio> allSoloVigentes(Connection con, String db, Map<String,String> mapeoDiccionario, Long id_sucursal) {
		List<Precio> lista = new ArrayList<Precio>();
		Map<Long,List<Double>> compra = Compra.ultimoPrecio(con, db);
		Map<Long,String> monedaCompra = Compra.ultimoMonedaPrecio(con, db);
		Map<String,Long> decCompra = Moneda.numeroDecimalxNombre(con, db);
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" precio.id_sucursal, " +
							" precio.id_equipo, " +
							" precio.id_moneda, " +
							" precio.fecha, " +
							" precio.precioVenta, " +
							" precio.precioReposicion, " +
							" precio.precioArriendo, " +
							" precio.id_unidadTiempo, " +
							" equipo.codigo, " +
							" equipo.nombre, " +
							" moneda.nickName, " +
							" unidadTiempo.nombre, " +
							" grupo.id, " +
							" grupo.nombre, " +
							" precioMinimo, " +
							" permanenciaMinima " +
							" from `"+db+"`.precio " +
							" left join `"+db+"`.equipo on equipo.id = precio.id_equipo " +
							" left join `"+db+"`.moneda on moneda.id = precio.id_moneda " +
							" left join `"+db+"`.unidadTiempo on unidadTiempo.id = precio.id_unidadTiempo " +
							" left join `"+db+"`.grupo on grupo.id = equipo.id_grupo" +
							" where equipo.vigente=1 and precio.id_sucursal = ?" +
							" order by grupo.nombre,equipo.nombre;");
			smt.setLong(1, id_sucursal);
			ResultSet rs = smt.executeQuery();
			Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
			while (rs.next()) {
				switch(dec.get(rs.getLong(3)).toString()) {
				 case "0": myformatdouble = new DecimalFormat("#,##0"); break;
				 case "2": myformatdouble = new DecimalFormat("#,##0.00"); break;
				 case "4": myformatdouble = new DecimalFormat("#,##0.0000"); break;
				 case "6": myformatdouble = new DecimalFormat("#,##0.000000"); break;
				 default:  break;
				}
				String monedaCompraAux = mapeoDiccionario.get("CLP");
				if(monedaCompra.get(rs.getLong(2))!=null) {
					monedaCompraAux = monedaCompra.get(rs.getLong(2));
				}
				Long auxDecimal = decCompra.get(monedaCompraAux.toUpperCase());
				if(auxDecimal==null) auxDecimal = (long)2;
				switch(auxDecimal.toString()) {
				 case "0": myformatdoubleCompra = new DecimalFormat("#,##0"); break;
				 case "2": myformatdoubleCompra = new DecimalFormat("#,##0.00"); break;
				 case "4": myformatdoubleCompra = new DecimalFormat("#,##0.0000"); break;
				 case "6": myformatdoubleCompra = new DecimalFormat("#,##0.000000"); break;
				 default:  break;
				}
				Double compraAux = (double)0;
				try{
					compraAux = compra.get(rs.getLong(2)).get(0);
				}catch(Exception e){}
				Double tasaArriendo=(double)0;
				if(rs.getDouble(7)>=0 && rs.getDouble(5)>0) {
					tasaArriendo= rs.getDouble(7)/rs.getDouble(5);
				}
				lista.add(new Precio(
						rs.getLong(1),
						rs.getLong(2),
						rs.getLong(3),
						myformatfecha.format(rs.getDate(4)),
						myformatdouble.format(rs.getDouble(5)),
						myformatdouble.format(rs.getDouble(6)),
						myformatdouble.format(rs.getDouble(7)),
						rs.getLong(8),
						rs.getString(9),
						rs.getString(10),
						rs.getString(11),
						rs.getString(12),
						rs.getLong(13),
						rs.getString(14),
						myformatdoubleCompra.format(compraAux),
						monedaCompra.get(rs.getLong(2)),
						myformatdouble2.format(tasaArriendo*100)+" %",
						myformatdouble.format(rs.getDouble(15)),
						myformatint.format(rs.getDouble(16))));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (lista);
	}
	
	public static String modifyPorCampo(Connection con, String db, String campo, String valor, String idSucursal, String idEquipo) {
		java.util.Date fecha =new Date();
		try {
			if(valor==null || valor.trim().length()<=0) valor = "0";
			Double numero = myformatdouble.parse(valor.trim()).doubleValue();
			java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());
			PreparedStatement smt1 = con
						.prepareStatement("UPDATE `"+db+"`.precio SET `" + campo + "` = ?, fecha = ?" +
								" WHERE id_sucursal = ? and id_equipo = ?;");
			smt1.setDouble(1, numero);
			smt1.setDate(2, sqlDate);
			smt1.setString(3, idSucursal.trim());
			smt1.setString(4, idEquipo.trim());
			smt1.executeUpdate();
			smt1.close();
	
			if(campo.equals("precioVenta")) {
				PreparedStatement smt2 = con
						.prepareStatement("UPDATE `"+db+"`.precio SET precioReposicion = precioVenta "
								+ " WHERE id_sucursal = ? and id_equipo = ?;");
				smt2.setString(1, idSucursal.trim());
				smt2.setString(2, idEquipo.trim());
				smt2.executeUpdate();
				smt2.close();
			}
				
			PreparedStatement smt = con
					.prepareStatement("insert into `"+db+"`.precioHistorico " +
							" (id_equipo,id_moneda,fecha,precioVenta,precioReposicion,precioArriendo,id_unidadTiempo) " +
							" (select id_equipo,id_moneda,fecha,precioVenta,precioReposicion,precioArriendo,id_unidadTiempo " +
							" from `"+db+"`.precio WHERE id_sucursal = ? and id_equipo = ?);");
			smt.setString(1, idSucursal.trim());
			smt.setString(2, idEquipo.trim());
			smt.executeUpdate();
			smt.close();
		} catch (SQLException | ParseException e) {
				e.printStackTrace();
		}
		return (myformatfecha.format(fecha));
	}
	
	public static Precio findXIdEquipo(Connection con, String db, Long id_sucursal, Long id_equipo, Map<String,String> mapDiccionario) {
		Precio aux = null;
		Map<Long,List<Double>> compra = Compra.ultimoPrecio(con, db);
		Map<Long,String> monedaCompra = Compra.ultimoMonedaPrecio(con, db);
		Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
		Map<String,Long> decCompra = Moneda.numeroDecimalxNombre(con, db);
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" precio.id_sucursal, " +
							" precio.id_equipo, " +
							" precio.id_moneda, " +
							" precio.fecha, " +
							" precio.precioVenta, " +
							" precio.precioReposicion, " +
							" precio.precioArriendo, " +
							" precio.id_unidadTiempo, " +
							" equipo.codigo, " +
							" equipo.nombre, " +
							" moneda.nickName, " +
							" unidadTiempo.nombre, " +
							" grupo.id, " +
							" grupo.nombre, " +
							" precioMinimo, " +
							" permanenciaMinima " +
							" from `"+db+"`.precio " +
							" left join `"+db+"`.equipo on equipo.id = precio.id_equipo " +
							" left join `"+db+"`.moneda on moneda.id = precio.id_moneda " +
							" left join `"+db+"`.unidadTiempo on unidadTiempo.id = precio.id_unidadTiempo " +
							" left join `"+db+"`.grupo on grupo.id = equipo.id_grupo" +
							" where precio.id_sucursal = ? and precio.id_equipo = ? " +
							" order by grupo.nombre,equipo.nombre;");
			smt.setLong(1, id_sucursal);
			smt.setLong(2, id_equipo);
			ResultSet resultado = smt.executeQuery();
			if (resultado.next()) {
				switch(dec.get(resultado.getLong(3)).toString()) {
				 case "0": myformatdouble = new DecimalFormat("#,##0"); break;
				 case "2": myformatdouble = new DecimalFormat("#,##0.00"); break;
				 case "4": myformatdouble = new DecimalFormat("#,##0.0000"); break;
				 case "6": myformatdouble = new DecimalFormat("#,##0.000000"); break;
				 default:  break;
				}
				String monedaCompraAux = monedaCompra.get(resultado.getLong(2));
				if(monedaCompraAux==null) {
					monedaCompraAux = mapDiccionario.get("CLP");
				}
				switch(decCompra.get(monedaCompraAux.toUpperCase()).toString()) {
				 case "0": myformatdoubleCompra = new DecimalFormat("#,##0"); break;
				 case "2": myformatdoubleCompra = new DecimalFormat("#,##0.00"); break;
				 case "4": myformatdoubleCompra = new DecimalFormat("#,##0.0000"); break;
				 case "6": myformatdoubleCompra = new DecimalFormat("#,##0.000000"); break;
				 default:  break;
				}
				Double compraAux = (double)0;
				if(compra.get(resultado.getLong(2))!=null) {
					compraAux = compra.get(resultado.getLong(2)).get(0);
				}
				Double tasaArriendo=(double)0;
				if(resultado.getDouble(7)>=0 && resultado.getDouble(5)>0) {
					tasaArriendo= resultado.getDouble(7)/resultado.getDouble(5);
				}
				aux = new Precio(
						resultado.getLong(1),
						resultado.getLong(2),
						resultado.getLong(3),
						myformatfecha.format(resultado.getDate(4)),
						myformatdouble.format(resultado.getDouble(5)),
						myformatdouble.format(resultado.getDouble(6)),
						myformatdouble.format(resultado.getDouble(7)),
						resultado.getLong(8),
						resultado.getString(9),
						resultado.getString(10),
						resultado.getString(11),
						resultado.getString(12),
						resultado.getLong(13),
						resultado.getString(14),
						myformatdoubleCompra.format(compraAux),
						monedaCompra.get(resultado.getLong(2)),
						myformatdouble2.format(tasaArriendo*100)+" %",
						myformatdouble.format(resultado.getDouble(15)),
						myformatint.format(resultado.getDouble(16)));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (aux);
	}
	
	public static boolean create(Connection con, String db, Long id_equipo, String fecha) {
		boolean flag = false;
		try {
			PreparedStatement smt2 = con
					.prepareStatement("select id from sucursal;");
			ResultSet rs2 = smt2.executeQuery();
			String aux = "";
			while(rs2.next()) {
				aux += "(" + rs2.getString(1) + "," + id_equipo + ",'" +fecha + "'),";
			}
			rs2.close();
			smt2.close();
			if(aux.length()>5) {
				aux = aux.substring(0,aux.length()-1);
				
				PreparedStatement smt = con
						.prepareStatement("INSERT INTO `"+db+"`.precio (id_sucursal, id_equipo, fecha) VALUES " + aux +");");
				smt.executeUpdate();
				smt.close();
				
				flag = true;
			}
			
			
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean createAuxiliar(Connection con, String db, Long id_sucursal, Long id_equipo, 
			Long id_moneda, Double precioUnitario) {
		boolean flag = false;
		Fechas hoy = Fechas.hoy();
		try {
			PreparedStatement smt = con
					.prepareStatement("INSERT INTO `"+db+"`.precio (id_sucursal, id_equipo, id_moneda, fecha, precioVenta, precioReposicion) "
							+ " VALUES (?,?,?,?,?,?)");
			smt.setLong(1, id_sucursal);
			smt.setLong(2, id_equipo);
			smt.setLong(3, id_moneda);
			smt.setString(4, hoy.getFechaStrAAMMDD());
			smt.setDouble(5, precioUnitario);
			smt.setDouble(6, precioUnitario);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	
	public static Map<Long,List<Double>> maestroPListaPorSucursal(Connection con, String db, Long id_sucursal) {
		 Map<Long,List<Double>> preciosLista = new HashMap<Long,List<Double>>();
			try {
				PreparedStatement smt = con
						.prepareStatement(" select " +
								" equipo.codigo, " +
								" precio.precioVenta, " +
								" precio.precioArriendo, " +
								" precio.id_unidadTiempo, " +
								" precio.id_moneda, " +
								" equipo.id " +
								" from `"+db+"`.precio " +
								" left join `"+db+"`.equipo on equipo.id = precio.id_equipo " +
								" where codigo is not null and id_sucursal = ? " +
								" order by equipo.codigo;");
				smt.setLong(1, id_sucursal);
				ResultSet rs = smt.executeQuery();
				while (rs.next()) {
					List<Double> aux = new ArrayList<Double>();
					aux.add(rs.getDouble(2));  // 0 precioVenta
					aux.add(rs.getDouble(3));  // 1 precioArriendo
					aux.add(rs.getDouble(4));  // 2 idUnidadTiempo
					aux.add(rs.getDouble(5));  // 3 idMoneda
					preciosLista.put(rs.getLong(6), aux);
				}
				rs.close();
				smt.close();
			} catch (SQLException e) {
   			e.printStackTrace();
			}
		return (preciosLista);
	}
	
	public static Map<String,List<Double>> maestroPListaAllSucursales(Connection con, String db) {
		 Map<String,List<Double>> preciosLista = new HashMap<String,List<Double>>();
			try {
				PreparedStatement smt = con
						.prepareStatement(" select " +
								" equipo.codigo, " +
								" precio.precioVenta, " +
								" precio.precioArriendo, " +
								" precio.id_unidadTiempo, " +
								" precio.id_moneda, " +
								" equipo.id, " +
								" precio.id_sucursal " +
								" from `"+db+"`.precio " +
								" left join `"+db+"`.equipo on equipo.id = precio.id_equipo " +
								" where codigo is not null " +
								" order by equipo.codigo;");
				ResultSet rs = smt.executeQuery();
				while (rs.next()) {
					List<Double> aux = new ArrayList<Double>();
					aux.add(rs.getDouble(2));  // 0 precioVenta
					aux.add(rs.getDouble(3));  // 1 precioArriendo
					aux.add(rs.getDouble(4));  // 2 idUnidadTiempo
					aux.add(rs.getDouble(5));  // 3 idMoneda
					preciosLista.put(rs.getLong(7) + "_" +rs.getLong(6), aux);
				}
				rs.close();
				smt.close();
			} catch (SQLException e) {
  			e.printStackTrace();
			}
		return (preciosLista);
	}
	
	public static Map<Long,Precio> mapPreciosLista(Connection con, String db, Map<String,String> mapeoDiccionario, Long id_sucursal) {
		 Map<Long,Precio> preciosLista = new HashMap<Long,Precio>();
		 List<Precio> lista = Precio.all(con, db, mapeoDiccionario, id_sucursal);
		 for(int i=0;i<lista.size();i++) {
			 preciosLista.put(lista.get(i).id_equipo, lista.get(i));
		 }
		return (preciosLista);
	}
	
	
	public static File plantillaPrecios(Connection con, String db, Map<String,String> mapeoDiccionario, Long id_sucursal) {
		File tmp = TempFile.createTempFile("tmp","null");
		
		
		List<Precio> listPrecio = Precio.allSoloVigentes(con, db, mapeoDiccionario, id_sucursal);
		Sucursal sucursal = Sucursal.find(con, db, id_sucursal.toString());
		
		try {
			InputStream formato = Archivos.leerArchivo("formatos/plantillaPrecios.xlsx");
            Workbook libro = WorkbookFactory.create(formato);
            formato.close();
            
            CellStyle subtitulo = libro.createCellStyle();
            Font font2 = libro.createFont();
            font2.setBoldweight(Font.BOLDWEIGHT_BOLD);
            font2.setColor((short)0);
            font2.setFontHeight((short)(12*20));
            subtitulo.setFont(font2);
         
            CellStyle detalle = libro.createCellStyle();
            detalle.setBorderBottom(CellStyle.BORDER_THIN);
            detalle.setBorderTop(CellStyle.BORDER_THIN);
            detalle.setBorderRight(CellStyle.BORDER_THIN);
            detalle.setBorderLeft(CellStyle.BORDER_THIN);
            
            Sheet hoja1 = libro.getSheetAt(0);
            Row row = null;
            Cell cell = null;
		
            
          //DETALLE DE AUXILIARES
            
            row = hoja1.getRow(1);
            cell = row.getCell(2);
        	cell.setCellType(Cell.CELL_TYPE_STRING);
        	cell.setCellValue(sucursal.getNombre());
            
            int posRow = 4;
            for(int i=0; i<listPrecio.size(); i++){
            	int aux = posRow +i;
            	row = hoja1.createRow(aux);

        		cell = row.createCell(1);
            	cell.setCellType(Cell.CELL_TYPE_STRING);
            	cell.setCellValue(listPrecio.get(i).getNombreGrupo());
            	
            	cell = row.createCell(2);
            	cell.setCellType(Cell.CELL_TYPE_STRING);
            	cell.setCellValue(listPrecio.get(i).getCodigoEquipo());
            	
            	cell = row.createCell(3);
            	cell.setCellType(Cell.CELL_TYPE_STRING);
            	cell.setCellValue(listPrecio.get(i).getNombreEquipo());
            	
            	cell = row.createCell(4);
            	cell.setCellType(Cell.CELL_TYPE_STRING);
            	cell.setCellValue(listPrecio.get(i).getNombreMoneda());
            	
            	cell = row.createCell(5);
            	Double precio = Double.parseDouble(listPrecio.get(i).precioVenta.replaceAll(",", ""));
            	cell.setCellType(Cell.CELL_TYPE_NUMERIC);
            	cell.setCellValue(precio);
            	
            	cell = row.createCell(6);
            	precio = Double.parseDouble(listPrecio.get(i).precioArriendo.replaceAll(",", ""));
            	cell.setCellType(Cell.CELL_TYPE_NUMERIC);
            	cell.setCellValue(precio);
            	
            	cell = row.createCell(7);
            	cell.setCellType(Cell.CELL_TYPE_STRING);
            	cell.setCellValue(listPrecio.get(i).getNombreUnidadTiempo());
            	
            	cell = row.createCell(8);
            	precio = Double.parseDouble(listPrecio.get(i).precioMinimo.replaceAll(",", ""));
            	cell.setCellType(Cell.CELL_TYPE_NUMERIC);
            	cell.setCellValue(precio);
            	
            	cell = row.createCell(9);
            	Long dias = Long.parseLong(listPrecio.get(i).getPermanenciaMinima().replaceAll(",", ""));
            	cell.setCellType(Cell.CELL_TYPE_NUMERIC);
            	cell.setCellValue(dias);
            	
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
	
	public static Long validaSucursal (Connection con, String db, File file) {
		try {
            Workbook libro = WorkbookFactory.create(file);
            Sheet hoja1 = libro.getSheetAt(0);
            Row row = null;
            Cell cell = null;
            
            row = hoja1.getRow(1);
            if(row != null) {
            	cell = row.getCell(2);
            }
            
            if(row!=null && cell !=null ) {
            	Sucursal sucursal = Sucursal.findPorNombre(con, db, cell.getStringCellValue());
            	if(sucursal == null) {
            		return(null);
            	}else {
            		return(sucursal.getId());
            	}
            }else {
            	return(null);
            }
            
		} catch (InvalidFormatException | IOException e1) {
			return(null);
		}
	}
	
	public static List<String> validaPlantillaPrecio(Connection con, String db, File file, Map<String,Equipo> mapEquipos) {

		
		List<String> mensaje = new ArrayList<String>();
		DecimalFormat df = new DecimalFormat("#");
	    df.setMaximumFractionDigits(8);
	    
	    mensaje.add("EQUIPOS QUE NO EXISTEN EN MADA: <br>");
		
		try {
            Workbook libro = WorkbookFactory.create(file);
            Sheet hoja1 = libro.getSheetAt(0);
            Row row = null;
            Cell cell = null;
            boolean archivoNoCorresponde = false;
            
            //valido titulos y archivo
            if(hoja1 != null) {
            	row = hoja1.getRow(3);
            	if(row != null) {
            		for(int i=1; i<10; i++) {
	            		cell = row.getCell(i);
	                	if(cell == null) {
	                		archivoNoCorresponde = true;
	                	}
	            	}
            	}else {
            		archivoNoCorresponde = true;
            	}
            }else {
            	archivoNoCorresponde = true;
            }
            if(archivoNoCorresponde) {
            	mensaje.set(0,"ARCHIVO NO CORRESPONDE A LA PLANTILLA");
            	return (mensaje);
            }
            // fin
            
            
            // valido datos
            boolean flag = true;
            int fila = 4;
            row = hoja1.getRow(fila);
            if(row != null) {
            	cell = row.getCell(1);
            	if(cell != null) {
            		Long nroFilasExcel = (long) 0;
            		Map<Long,Long> mapValidaRepetido = new HashMap<Long,Long>();
            		while (row != null && cell != null ) {
            			row = hoja1.getRow(fila);
            			if(row != null) {
            				
            				//valido codigos
            				cell = row.getCell(2);
            				if(cell != null) {
            					boolean noEsBlanco = true;
    	                    	try {
    	                    		String dato = cell.getStringCellValue().trim();
    	                    		if(dato.trim().equals("")) {
    	                    			noEsBlanco = false;
    	                    		}
    	                    	}catch(Exception e){
    	                    		Double aux = cell.getNumericCellValue();
    	                    		if(aux.toString().trim().equals("")) {
    	                    			noEsBlanco = false;
    	                    		}
    	                    	}
    	                    	if(noEsBlanco) {
    	                			String dato = "codigo";
    	                			cell = row.getCell(2);
    	                			try {
    	                        		dato = cell.getStringCellValue().trim();
    	                        	}catch(Exception e){
    	                        		Double aux = cell.getNumericCellValue();
    	                        		Long aux2 = aux.longValue();
    	                        		dato = df.format(aux2);
    	                        	}
    	                			Equipo equipo = mapEquipos.get(dato.toUpperCase());
    	                			if(equipo == null) {
    	                				mensaje.add("error en fila "+(fila+1)+": Codigo ["+dato+"] no existe en mada o el equipo esta no vigente.");
    	                        		flag = false;
    	                			}else{
    	                				mapValidaRepetido.put(equipo.getId(), equipo.getId());
    	                			}
    	                		}
            				}
            				
            				//valido numeros
            				for(int i=5; i<10; i++) {
            					cell = row.getCell(i);
            					String celda = "";
            					switch(i) {
            					  case 5: celda  = "F" + (fila+1); break;
            					  case 6: celda  = "G" + (fila+1); break;
            					  case 8: celda  = "I" + (fila+1); break;
            					  case 9: celda  = "J" + (fila+1); break;
            					  default:
            					}
            					
            					if(cell != null && i != 7) {
            						try {
            							Double aux = cell.getNumericCellValue();
            							if(aux < (double)0) {
            								mensaje.add("error en fila "+(fila+1)+": El dato en la celda "+ celda +" no puede ser menor que cero.");
            								flag = false;
            							}
	    	                    	}catch(Exception e){
	    	                    		mensaje.add("error en fila "+(fila+1)+": El dato en la celda "+ celda +" no es numero.");
    	                        		flag = false;
	    	                    	}
            					}
            				}
            				nroFilasExcel++;
            				fila++;
            			}
            		}
            		if(nroFilasExcel - mapValidaRepetido.size() != (long)0) {
            			mensaje.add("Existen codigos duplicados en el archivo.");
                		flag = false;
            		}
            	}
            }
            if(flag) {
            	mensaje.set(0,"true");
            }
        } catch (Exception e) {
			mensaje.set(0,"ARCHIVO NO CORRESPONDE A LA PLANTILLA");
        	return (mensaje);
        }
		return(mensaje);
	}
	
	public static List<List<String>> llenaListaDesdeExcel (File file) {
		List<List<String>> lista = new ArrayList<List<String>>();
		DecimalFormat df = new DecimalFormat("#");
	    df.setMaximumFractionDigits(8);
		try {
            Workbook libro = WorkbookFactory.create(file);
            Sheet hoja1 = libro.getSheetAt(0);
            Row row = null;
            Cell cell = null;
            
            row = hoja1.getRow(1);
            if(row != null) {
            	cell = row.getCell(2);
            }

            int x = 4;
            while (row!=null && cell !=null ) {
            	row = hoja1.getRow(x++);
            	if(row!=null) {
            		cell = row.getCell(2);
                	if(cell!=null) {
                		boolean noEsBlanco = true;
                    	try {
                    		String dato = cell.getStringCellValue().trim();
                    		if(dato.trim().equals("")) {
                    			noEsBlanco = false;
                    		}
                    	}catch(Exception e){
                    		Double aux = cell.getNumericCellValue();
                    		if(aux.toString().trim().equals("")) {
                    			noEsBlanco = false;
                    		}
                    	}
                		if(noEsBlanco) {
                			List<String> auxList = new ArrayList<String>();
                			for(int i=1; i<10; i++) {
                    			String dato = "";
                    			cell = row.getCell(i);
                                if(cell!=null) {
                                	try {
                                		Double aux = cell.getNumericCellValue();
                                		dato = df.format(aux);
                                	}catch(Exception e){
                                		dato = cell.getStringCellValue().trim();
                                		dato = dato.replaceAll("'", "\"");
                                	}
                                }
                                auxList.add(dato);
                            }
                			auxList.set(0, auxList.get(0).toUpperCase());
                    		lista.add(auxList);
                		}
                	}
                	cell = row.getCell(2);
            	}
            }
		} catch (InvalidFormatException | IOException e1) {
		}
		return(lista);
	}
	
	// 0 GRUPO
	// 1 CODIGO
	// 2 EQUIPO
	// 3 MONEDA
	// 4 P.U VENTA
	// 5 P.U ARRIENDO
	// 6 UNIDAD ARRIENDO
	// 7 PRECIO MINIMO
	// 8 CANTIDAD DIAS
	
	
	public static boolean updatePorSucursal(Connection con, String db, Precio precio, String fechaAAMMDD) {
		boolean flag = false;
		try {
			Double tasaArriendo = (double) 0;
			Double pArr = Double.parseDouble(precio.getPrecioArriendo().replaceAll(",", ""));
			Double pRep = Double.parseDouble(precio.getPrecioReposicion().replaceAll(",", ""));
			if(pRep > 0) {
				tasaArriendo = pArr/pRep;
			}
			PreparedStatement smt1 = con
						.prepareStatement("UPDATE `"+db+"`.precio SET precioVenta=?, precioReposicion=?, tasaArriendo=?, precioArriendo=?, precioMinimo=?, permanenciaMinima=?, fecha=? " +
								" WHERE id_sucursal = ? and id_equipo = ?;");
			smt1.setString(1, precio.getPrecioVenta());
			smt1.setString(2, precio.getPrecioReposicion());
			smt1.setDouble(3, tasaArriendo);
			smt1.setString(4, precio.getPrecioArriendo());
			smt1.setString(5, precio.getPrecioMinimo());
			smt1.setString(6, precio.getPermanenciaMinima());
			smt1.setString(7, fechaAAMMDD);
			smt1.setLong(8, precio.getId_sucursal());
			smt1.setLong(9, precio.getId_equipo());
			smt1.executeUpdate();
			smt1.close();
			flag = true;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	

}
