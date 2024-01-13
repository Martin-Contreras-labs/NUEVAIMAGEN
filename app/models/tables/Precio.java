package models.tables;

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
	public void setId_sucursal(Long id_sucursal) {this.id_equipo = id_sucursal;}
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
				Long auxDecimal = decCompra.get(monedaCompraAux);
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
				Long auxDecimal = decCompra.get(monedaCompraAux);
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
				Long auxDecimal = decCompra.get(monedaCompraAux);
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
				switch(decCompra.get(monedaCompraAux).toString()) {
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
	
	public static boolean create(Connection con, String db, Long id_sucursal, Long id_equipo, String fecha) {
		boolean flag = false;
		try {
			PreparedStatement smt2 = con
					.prepareStatement("select id from sucursal;");
			smt2.setLong(1, id_sucursal);
			ResultSet rs2 = smt2.executeQuery();
			String aux = "";
			while(rs2.next()) {
				aux += "(" + rs2.getString(1) + "," + id_equipo + ",'" +fecha + "'),";
			}
			rs2.close();
			smt2.close();
			aux = aux.substring(0,aux.length()-1);
			
			PreparedStatement smt = con
					.prepareStatement("INSERT INTO `"+db+"`.precio (id_sucursal, id_equipo, fecha) VALUES " + aux +");");
			smt.executeUpdate();
			smt.close();
			
			flag = true;
			
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
	
	public static Map<Long,List<Double>> maestroPreciosLista(Connection con, String db, Long id_sucursal) {
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
	
	

}
