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



public class ListaPrecio {
	public Long id_bodegaEmpresa;			// 0 listaPrecio.id_bodegaEmpresa
	public Long id_equipo;					// 1 listaPrecio.id_equipo
	public Long id_moneda;					// 2 listaPrecio.id_moneda
	public String fecha;					// 3 listaPrecio.fecha
	public String precioVenta;				// 4 listaPrecio.precioVenta
	public String precioReposicion;			// 5 listaPrecio.PrecioReposicion
	public String precioArriendo;			// 6 listaPrecio.precioArriendo
	public Long id_unidadTiempo;			// 7 listaPrecio.id_unidadTiempo
	
	
	public String nombreGrupo;				// 8 grupo.nombre
	public String codigoEquipo;				// 9 equipo.codigo
	public String nombreEquipo;				// 10 equipo.nombre
	public String nickNameMoneda;			// 11 moneda.nickName
	public String nombreUnidadTiempo;		// 12 unidadTiempo.nombre
	public String tasaDesctoGlobal;			// 13
	public String tasaDesctoGrupo;			// 14
	public String tasaDesctoEquipo;			// 15
	public String totalArriendo;			// 16 
	public String tasaCambio;				// 17
	public String ventaPesos;				// 18
	public String reposicionPesos;			// 19
	public String arriendoPesos;			// 20
	public String tasaArriendo;				// 21
	
	public String precioMinimo;				// 22
	public String permanenciaMinima;		// 23
	public Long id_cotizacion;				// 24
	
	public Long numeroCotizacion;			// 25
	


	public ListaPrecio(Long id_bodegaEmpresa, Long id_equipo, Long id_moneda,
			String fecha, String precioVenta, String precioReposicion,
			String precioArriendo, Long id_unidadTiempo, String nombreGrupo,
			String codigoEquipo, String nombreEquipo, String nickNameMoneda,
			String nombreUnidadTiempo, String tasaDesctoGlobal,
			String tasaDesctoGrupo, String tasaDesctoEquipo,
			String totalArriendo, String tasaCambio, String ventaPesos,
			String reposicionPesos, String arriendoPesos, String tasaArriendo,
			String precioMinimo, String permanenciaMinima,Long id_cotizacion,Long numeroCotizacion) {
		super();
		this.id_bodegaEmpresa = id_bodegaEmpresa;
		this.id_equipo = id_equipo;
		this.id_moneda = id_moneda;
		this.fecha = fecha;
		this.precioVenta = precioVenta;
		this.precioReposicion = precioReposicion;
		this.precioArriendo = precioArriendo;
		this.id_unidadTiempo = id_unidadTiempo;
		this.nombreGrupo = nombreGrupo;
		this.codigoEquipo = codigoEquipo;
		this.nombreEquipo = nombreEquipo;
		this.nickNameMoneda = nickNameMoneda;
		this.nombreUnidadTiempo = nombreUnidadTiempo;
		this.tasaDesctoGlobal = tasaDesctoGlobal;
		this.tasaDesctoGrupo = tasaDesctoGrupo;
		this.tasaDesctoEquipo = tasaDesctoEquipo;
		this.totalArriendo = totalArriendo;
		this.tasaCambio = tasaCambio;
		this.ventaPesos = ventaPesos;
		this.reposicionPesos = reposicionPesos;
		this.arriendoPesos = arriendoPesos;
		this.tasaArriendo = tasaArriendo;
		this.precioMinimo = precioMinimo;
		this.permanenciaMinima = permanenciaMinima;
		this.id_cotizacion = id_cotizacion;
		this.numeroCotizacion = numeroCotizacion;
	}

	public ListaPrecio() {
		super();
	}

	public Long getId_bodegaEmpresa() {return id_bodegaEmpresa;}
	public void setId_bodegaEmpresa(Long id_bodegaEmpresa) {this.id_bodegaEmpresa = id_bodegaEmpresa;}
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
	public String getNickNameMoneda() {return nickNameMoneda;}
	public void setNickNameMoneda(String nickNameMoneda) {this.nickNameMoneda = nickNameMoneda;}
	public String getNombreUnidadTiempo() {return nombreUnidadTiempo;}
	public void setNombreUnidadTiempo(String nombreUnidadTiempo) {this.nombreUnidadTiempo = nombreUnidadTiempo;}
	public String gettasaDesctoGlobal() {return tasaDesctoGlobal;}
	public void settasaDesctoGlobal(String tasaDesctoGlobal) {this.tasaDesctoGlobal = tasaDesctoGlobal;}
	public String getTasaDesctoGrupo() {return tasaDesctoGrupo;}
	public void setTasaDesctoGrupo(String tasaDesctoGrupo) {this.tasaDesctoGrupo = tasaDesctoGrupo;}
	public String getTasaDesctoEquipo() {return tasaDesctoEquipo;}
	public void setTasaDesctoEquipo(String tasaDesctoEquipo) {this.tasaDesctoEquipo = tasaDesctoEquipo;}
	public String getNombreGrupo() {return nombreGrupo;	}
	public void setNombreGrupo(String nombreGrupo) {this.nombreGrupo = nombreGrupo;}
	public String getTotalArriendo() {return totalArriendo;}
	public void setTotalArriendo(String totalArriendo) {this.totalArriendo = totalArriendo;}
	public String getTasaCambio() {return tasaCambio;}
	public void setTasaCambio(String tasaCambio) {this.tasaCambio = tasaCambio;}
	public String getVentaPesos() {return ventaPesos;}
	public void setVentaPesos(String ventaPesos) {this.ventaPesos = ventaPesos;}
	public String getReposicionPesos() {return reposicionPesos;}
	public void setReposicionPesos(String reposicionPesos) {this.reposicionPesos = reposicionPesos;}
	public String getArriendoPesos() {return arriendoPesos;}
	public void setArriendoPesos(String arriendoPesos) {this.arriendoPesos = arriendoPesos;}
	public String getTasaArriendo() {return tasaArriendo;}
	public void setTasaArriendo(String tasaArriendo) {this.tasaArriendo = tasaArriendo;}
	public String getTasaDesctoGlobal() {return tasaDesctoGlobal;}
	public void setTasaDesctoGlobal(String tasaDesctoGlobal) {this.tasaDesctoGlobal = tasaDesctoGlobal;}
	public String getPrecioMinimo() {return precioMinimo;}
	public void setPrecioMinimo(String precioMinimo) {this.precioMinimo = precioMinimo;}
	public String getPermanenciaMinima() {return permanenciaMinima;}
	public void setPermanenciaMinima(String permanenciaMinima) {this.permanenciaMinima = permanenciaMinima;}
	public Long getId_cotizacion() {return id_cotizacion;}
	public void setId_cotizacion(Long id_cotizacion) {this.id_cotizacion = id_cotizacion;}
	public Long getNumeroCotizacion() {return numeroCotizacion;}
	public void setNumeroCotizacion(Long numeroCotizacion) {this.numeroCotizacion = numeroCotizacion;}


	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatentero = new DecimalFormat("#,##0");
	static SimpleDateFormat myformatfecha = new SimpleDateFormat("dd-MM-yyyy");
	static DecimalFormat myformatint = new DecimalFormat("#,##0");
	
	
	
	public static Map<String,ListaPrecio> mapListaPrecio(Connection con, String db, Long id_bodegaEmpresa, String pais){
		Map<String,ListaPrecio> map = new HashMap<String,ListaPrecio>();
		List<ListaPrecio> lista = ListaPrecio.allXBodega(con, db, id_bodegaEmpresa, pais);
		lista.forEach(x->{
			map.put(x.getId_equipo()+"_"+x.getId_cotizacion(), x);
		});
		return(map);
	}
	
	public static List<ListaPrecio> allXBodega(Connection con, String db, Long id_bodegaEmpresa, String pais) {
		List<ListaPrecio> lista = new ArrayList<ListaPrecio>();
		try {
			PreparedStatement smt1 = con
					.prepareStatement(" select " +
							" listaPrecio.id_bodegaEmpresa, " +
							" listaPrecio.id_equipo, " +
							" listaPrecio.id_moneda, " +
							" listaPrecio.fecha, " +
							" listaPrecio.precioVenta, " +
							" listaPrecio.PrecioReposicion, " +
							" listaPrecio.precioArriendo, " +
							" listaPrecio.id_unidadTiempo, " +
							" grupo.nombre, " +
							" equipo.codigo, " +
							" equipo.nombre, " +
							" moneda.nickName, " +
							" unidadTiempo.nombre, " +
							" bodegaEmpresa.tasaDescto, " +
							" grupo.id, " +
							" precioMinimo, " +
							" permanenciaMinima, " +
							" listaPrecio.id_cotizacion, "+
							" cotizacion.numero "+
							" from `"+db+"`.listaPrecio " +
							" left join `"+db+"`.equipo on equipo.id=listaPrecio.id_equipo " +
							" left join `"+db+"`.moneda on moneda.id=listaPrecio.id_moneda " +
							" left join `"+db+"`.unidadTiempo on unidadTiempo.id=listaPrecio.id_unidadTiempo " +
							" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id=listaPrecio.id_bodegaEmpresa " +
							" left join `"+db+"`.grupo on grupo.id=equipo.id_grupo " +
							" left join `"+db+"`.cotizacion on cotizacion.id=listaPrecio.id_cotizacion " +
							" where listaPrecio.id_bodegaEmpresa = ? " + 
							" order by bodegaEmpresa.nombre,grupo.nombre,equipo.nombre;");
			smt1.setLong(1, id_bodegaEmpresa);
			ResultSet rs1 = smt1.executeQuery();
		
			PreparedStatement smt2 = con
					.prepareStatement("select id_grupo,tasaDescto from `"+db+"`.dctoGrupo where id_bodegaEmpresa = ?;");
			smt2.setLong(1, id_bodegaEmpresa);
			ResultSet rs2 = smt2.executeQuery();
			Map<Long,Double> tasaGrupo = new HashMap<Long,Double>();
			while (rs2.next()) {
				tasaGrupo.put(rs2.getLong(1),rs2.getDouble(2));
			}
			rs2.close();
			smt2.close();
			
			PreparedStatement smt3 = con
					.prepareStatement("select concat(id_equipo,'-',id_cotizacion),tasaDescto from `"+db+"`.dctoEquipo where id_bodegaEmpresa = ?;");
			smt3.setLong(1, id_bodegaEmpresa);
			ResultSet rs3 = smt3.executeQuery();
			Map<String,Double> tasaEquipo = new HashMap<String,Double>();
			while (rs3.next()) {
				tasaEquipo.put(rs3.getString(1),rs3.getDouble(2));
			}
			rs3.close();
			smt3.close();
			
			Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
			TasasCambio cambio = TasasCambio.allDeHoy(con, db, pais);
			
			while (rs1.next()) {
				String tasaGlobalSTR = myformatdouble2.format(rs1.getDouble(14)*100) + " %";
				String tasaGrupoSTR = "0.00 %";
				String tasaEquipoSTR = "0.00 %";
				Double tasaGrupoDouble = (double) 0;
				Double tasaEquipoDouble = (double) 0;
				try{ 
					tasaGrupoSTR = myformatdouble2.format(tasaGrupo.get(rs1.getLong(15))*100) + " %";
					tasaGrupoDouble=tasaGrupo.get(rs1.getLong(15));
				}catch (Exception e){}
				try{ 
					tasaEquipoSTR = myformatdouble2.format(tasaEquipo.get(rs1.getString(2)+"-"+rs1.getShort(18))*100) + " %";
					tasaEquipoDouble=tasaEquipo.get(rs1.getString(2)+"-"+rs1.getShort(18));
				}catch (Exception e){}
				String tasaSTR = "1.00";
				
				if(pais.equals("CHILE")){
					if(rs1.getString(12).equals("EUR")) tasaSTR = cambio.getClpEur();
					if(rs1.getString(12).equals("USD")) tasaSTR = cambio.getClpUsd();
					if(rs1.getString(12).equals("UF")) tasaSTR = cambio.getClpUf();
				}
				
				
				Double arriendoTotal = rs1.getDouble(7)*(1-rs1.getDouble(14))*(1-tasaGrupoDouble)*(1-tasaEquipoDouble);
				
				Double tasa = (double) 1;
				String auxNum = tasaSTR.trim();
		   		if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
				try { tasa = myformatdouble2.parse(auxNum).doubleValue();} catch (ParseException e) {}
				String vtaPesos = myformatentero.format(rs1.getDouble(5)*tasa);
				String reposPesos = myformatentero.format(rs1.getDouble(6)*tasa);
				String arrPesos = myformatentero.format(arriendoTotal*tasa);				
				switch((dec.get(rs1.getLong(3))).toString()) {
					 case "0": myformatdouble = new DecimalFormat("#,##0"); break;
					 case "2": myformatdouble = new DecimalFormat("#,##0.00"); break;
					 case "4": myformatdouble = new DecimalFormat("#,##0.0000"); break;
					 case "6": myformatdouble = new DecimalFormat("#,##0.000000"); break;
					 default:  break;
					}
				Double tasaArriendo=(double)0;
				if(rs1.getDouble(7)>=0 && rs1.getDouble(5)>0) {
					tasaArriendo= rs1.getDouble(7)/rs1.getDouble(5);
				}
				lista.add(new ListaPrecio(
						rs1.getLong(1),									// 0 listaPrecio.id_bodegaEmpresa
						rs1.getLong(2),									// 1 listaPrecio.id_equipo
						rs1.getLong(3),									// 2 listaPrecio.id_moneda
						rs1.getString(4),								// 3 listaPrecio.fecha
						myformatdouble.format(rs1.getDouble(5)),		// 4 listaPrecio.precioVenta
						myformatdouble.format(rs1.getDouble(6)),		// 5 listaPrecio.PrecioReposicion
						myformatdouble.format(rs1.getDouble(7)),		// 6 listaPrecio.precioArriendo
						rs1.getLong(8),									// 7 listaPrecio.id_unidadTiempo
						rs1.getString(9),								// 8 grupo.nombre
						rs1.getString(10),								// 9 equipo.codigo
						rs1.getString(11),								// 10 equipo.nombre
						rs1.getString(12),								// 11 moneda.nickName
						rs1.getString(13),								// 12 unidadTiempo.nombre
						tasaGlobalSTR,									// 13 tasaGlobalSTR con formato %
						tasaGrupoSTR,									// 14 tasaGrupoSTR con formato %
						tasaEquipoSTR,									// 15 tasaEquipoSTR con formato %
						myformatdouble.format(arriendoTotal),			// 16 precio de arriendo con los descuentos globales aplicados(descuentos descontinuedo)
						tasaSTR,										// 17 tasa de cambio
						vtaPesos,										// 18 precio venta unitario por la tasa de cambio
						reposPesos,										// 19 precio reposicion unitario por la tasa de cambio
						arrPesos,										// 20 precio arriendo unitario por la tasa de cambio (aplicado descuentos descontinuedo)
						myformatdouble2.format(tasaArriendo*100) + " %",// 21 tasa arriendo con formato %
						myformatdouble.format(rs1.getDouble(16)),		// 22 precioMinimo
						myformatint.format(rs1.getDouble(17)),			// 23 permanenciaMinima
						rs1.getLong(18),								// 24 listaPrecio.id_cotizacion
						rs1.getLong(19)));								// 25 cotizacion.numero
			}
			rs1.close();
			smt1.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static String modifyPorCampo(Connection con, String db, String campo, String valor, Long id_bodegaEmpresa, Long id_equipo, Long id_cotizacion) {
		java.util.Date fecha =new Date();
		try {
			if(valor==null || valor.trim().length()<=0) valor = "0";
			Double numero = myformatdouble.parse(valor.trim()).doubleValue();
			java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());
			PreparedStatement smt1 = con
						.prepareStatement("UPDATE `"+db+"`.listaPrecio SET `" + campo + "` = ?, fecha = ?" +
								" WHERE id_bodegaEmpresa = ? and id_equipo = ? and id_cotizacion = ?;");
			smt1.setDouble(1, numero);
			smt1.setDate(2, sqlDate);
			smt1.setLong(3, id_bodegaEmpresa);
			smt1.setLong(4, id_equipo);
			smt1.setLong(5, id_cotizacion);
			smt1.executeUpdate();
			smt1.close();
	
			if(campo.equals("precioVenta")) {
				PreparedStatement smt2 = con
						.prepareStatement("UPDATE `"+db+"`.listaPrecio SET precioReposicion = precioVenta WHERE id_bodegaEmpresa = ? and id_equipo = ? and id_cotizacion = ?;");
				smt2.setLong(1, id_bodegaEmpresa);
				smt2.setLong(2, id_equipo);
				smt2.setLong(3, id_cotizacion);
				smt2.executeUpdate();
				smt2.close();
			}
		} catch (SQLException | ParseException e) {
				e.printStackTrace();
		}
		return (myformatfecha.format(fecha));
	}
	
	public static Map<String,List<Double>> mapListaPreciosEquiposPorEquipo(Connection con, String db,Long idEquipo) {
		Map<String,List<Double>> lista = new HashMap<String,List<Double>>();
		String condicion = " where listaPrecio.id_equipo = '"+idEquipo.toString().trim()+"' ";
		if (idEquipo==0) return (lista);
		try {
			PreparedStatement smt = con
					.prepareStatement(" select  " +
							" listaPrecio.id_equipo, " +
							" listaPrecio.id_moneda, " +
							" listaPrecio.fecha, " +
							" listaPrecio.precioVenta, " +
							" listaPrecio.precioReposicion, " +
							" listaPrecio.precioArriendo, " +
							" moneda.nickName, " +
							" unidadTiempo.id, " +
							" ifnull(bodegaEmpresa.tasaDescto,0) as dsctoGlobal, " +
							" ifnull(dctoGrupo.tasaDescto,0) as dsctoGrupo, " +
							" ifnull(dctoEquipo.tasaDescto,0) as dsctoEquipo, " +
							
							" listaPrecio.precioArriendo*(1-ifnull(bodegaEmpresa.tasaDescto,0))* " +
							" (1-ifnull(dctoGrupo.tasaDescto,0))*(1-ifnull(dctoEquipo.tasaDescto,0)) " +
							" as ValorArriendo, " +
							
							" listaPrecio.id_cotizacion " +
							" from `"+db+"`.listaPrecio   " +
							" left join `"+db+"`.equipo on equipo.id=listaPrecio.id_equipo   " +
							" left join `"+db+"`.moneda on moneda.id=listaPrecio.id_moneda   " +
							" left join `"+db+"`.unidadTiempo on unidadTiempo.id=listaPrecio.id_unidadTiempo   " +
							" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id=listaPrecio.id_bodegaEmpresa " +
							" left join `"+db+"`.dctoGrupo on dctoGrupo.id_grupo=equipo.id_grupo " +
							" left join `"+db+"`.dctoEquipo on dctoEquipo.id_equipo=equipo.id" + condicion +";");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				List<Double> aux = new ArrayList<Double>();
				aux.add(rs.getDouble(5));				//  0 precio de reposicion
				aux.add(rs.getDouble(12));				//  1 precio de arriendo con dctos aplicados
				aux.add(rs.getDouble(2)); 				//  2 idMoneda
				aux.add(rs.getDouble(8)); 				//  3 id unidadTiempo
				lista.put(rs.getString(1)+"_"+rs.getString(13), aux);
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static Map<String,List<Double>> mapListaPreciosEquiposPorBodega(Connection con, String db, Long idBodegaEmpresa) {
		Map<String,List<Double>> lista = new HashMap<String,List<Double>>();
		String condicion = " where listaPrecio.id_bodegaEmpresa = '"+idBodegaEmpresa.toString().trim()+"' ";
		if (idBodegaEmpresa==0) return (lista);
		try {
			PreparedStatement smt = con
					.prepareStatement(" select  " +
							" listaPrecio.id_equipo, " +
							" listaPrecio.id_moneda, " +
							" listaPrecio.fecha, " +
							" listaPrecio.precioVenta, " +
							" listaPrecio.precioReposicion, " +
							" listaPrecio.precioArriendo, " +
							" moneda.nickName, " +
							" unidadTiempo.id, " +
							" ifnull(bodegaEmpresa.tasaDescto,0) as dsctoGlobal, " +
							" ifnull(dctoGrupo.tasaDescto,0) as dsctoGrupo, " +
							" ifnull(dctoEquipo.tasaDescto,0) as dsctoEquipo, " +
							
							" listaPrecio.precioArriendo*(1-ifnull(bodegaEmpresa.tasaDescto,0))* " +
							" (1-ifnull(dctoGrupo.tasaDescto,0))*(1-ifnull(dctoEquipo.tasaDescto,0)) " +
							" as ValorArriendo, " +
							
							" listaPrecio.id_cotizacion " +
							" from `"+db+"`.listaPrecio   " +
							" left join `"+db+"`.equipo on equipo.id=listaPrecio.id_equipo   " +
							" left join `"+db+"`.moneda on moneda.id=listaPrecio.id_moneda   " +
							" left join `"+db+"`.unidadTiempo on unidadTiempo.id=listaPrecio.id_unidadTiempo   " +
							" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id=listaPrecio.id_bodegaEmpresa " +
							" left join `"+db+"`.dctoGrupo on dctoGrupo.id_grupo=equipo.id_grupo " +
							" left join `"+db+"`.dctoEquipo on dctoEquipo.id_equipo=equipo.id" + condicion +";");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				List<Double> aux = new ArrayList<Double>();
				aux.add(rs.getDouble(5));				//  0 precio de reposicion
				aux.add(rs.getDouble(12));				//  1 precio de arriendo con dctos aplicados
				aux.add(rs.getDouble(2)); 				//  2 idMoneda
				aux.add(rs.getDouble(8)); 				//  3 id unidadTiempo
				lista.put(rs.getString(1)+"_"+rs.getString(13), aux);
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static Map<String,List<Double>> mapListaPreciosAll(Connection con, String db) {
		Map<String,List<Double>> lista = new HashMap<String,List<Double>>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select distinct " +
							" listaPrecio.id_equipo, " +
							" listaPrecio.id_moneda, " +
							" listaPrecio.fecha, " +
							" listaPrecio.precioVenta, " +
							" listaPrecio.precioReposicion, " +
							" listaPrecio.precioArriendo, " +
							" moneda.nickName, " +
							" unidadTiempo.id, " +
							" ifnull(bodegaEmpresa.tasaDescto,0) as dsctoGlobal, " +
							" ifnull(dctoGrupo.tasaDescto,0) as dsctoGrupo, " +
							" ifnull(dctoEquipo.tasaDescto,0) as dsctoEquipo, " +
							
							" listaPrecio.precioArriendo*(1-ifnull(bodegaEmpresa.tasaDescto,0))* " +
							" (1-ifnull(dctoGrupo.tasaDescto,0))*(1-ifnull(dctoEquipo.tasaDescto,0)) as ValorArriendo, " +
							
							" listaPrecio.id_cotizacion, " + 	//13
							" bodegaEmpresa.id " +				//14
							" from `"+db+"`.listaPrecio   " +
							" left join `"+db+"`.equipo on equipo.id=listaPrecio.id_equipo   " +
							" left join `"+db+"`.moneda on moneda.id=listaPrecio.id_moneda   " +
							" left join `"+db+"`.unidadTiempo on unidadTiempo.id=listaPrecio.id_unidadTiempo   " +
							" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id=listaPrecio.id_bodegaEmpresa " +
							" left join `"+db+"`.dctoGrupo on dctoGrupo.id_grupo=equipo.id_grupo " +
							" left join `"+db+"`.dctoEquipo on dctoEquipo.id_equipo=equipo.id;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				List<Double> aux = new ArrayList<Double>();
				aux.add(rs.getDouble(5));				//  0 precio de reposicion
				aux.add(rs.getDouble(12));				//  1 precio de arriendo con dctos aplicados
				aux.add(rs.getDouble(8)); 				//  2 id unidadTiempo
				aux.add(rs.getDouble(2)); 				//  3 idMoneda
				lista.put(rs.getString(14)+"_"+rs.getString(13)+"_"+rs.getString(1), aux); 
				// key = id_bodegaEmpresa + id_cotizacion + id_equipo
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (lista);
	}

		

}
