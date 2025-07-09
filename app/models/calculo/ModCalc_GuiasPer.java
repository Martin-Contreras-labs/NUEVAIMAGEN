package models.calculo;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

import controllers.HomeController;
import models.tables.ActaBaja;
import models.tables.Guia;
import models.utilities.Fechas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ModCalc_GuiasPer {
	
	public Long id_bodegaEmpresa; 
	public Long id_grupo;
	public Long id_equipo;
	public Long id_cotizacion;
	public Long esVenta;
	public Long id_guia;
	public String fechaGuia;
	public Long id_tipoMovimiento;
	
	public Double cantidad;
	public Long dias;	

	public Long id_moneda;
	public Double pVenta;	//precio de venta antes de aplicar tasa de cambio
	public Double pArr_dia;	//precio de arriendo antes de aplicar tasa de cambio
	public Double tasaCambio;
	public Double totalVenta;
	public Double totalArriendo;
	public Double totalCfi;
	public Double totalTotal;
	
	public Long maestroId_moneda;
	public Double maestroPVenta;
	public Double maestroPArr_dia;
	public Double maestroTasaCambio;
	public Double maestroTotalVenta;
	public Double maestroTotalArriendo;
	public Double maestroTotalCfi;
	public Double maestroTotalTotal;


	public ModCalc_GuiasPer(Long id_bodegaEmpresa, Long id_grupo, Long id_equipo, Long id_cotizacion, Long esVenta,
			Long id_guia, String fechaGuia, Long id_tipoMovimiento, Double cantidad, Long dias, Long id_moneda,
			Double pVenta, Double pArr_dia, Double tasaCambio, Double totalVenta, Double totalArriendo, Double totalCfi,
			Double totalTotal, Long maestroId_moneda, Double maestroPVenta, Double maestroPArr_dia,
			Double maestroTasaCambio, Double maestroTotalVenta, Double maestroTotalArriendo, Double maestroTotalCfi,
			Double maestroTotalTotal) {
		super();
		this.id_bodegaEmpresa = id_bodegaEmpresa;
		this.id_grupo = id_grupo;
		this.id_equipo = id_equipo;
		this.id_cotizacion = id_cotizacion;
		this.esVenta = esVenta;
		this.id_guia = id_guia;
		this.fechaGuia = fechaGuia;
		this.id_tipoMovimiento = id_tipoMovimiento;
		this.cantidad = cantidad;
		this.dias = dias;
		this.id_moneda = id_moneda;
		this.pVenta = pVenta;
		this.pArr_dia = pArr_dia;
		this.tasaCambio = tasaCambio;
		this.totalVenta = totalVenta;
		this.totalArriendo = totalArriendo;
		this.totalCfi = totalCfi;
		this.totalTotal = totalTotal;
		this.maestroId_moneda = maestroId_moneda;
		this.maestroPVenta = maestroPVenta;
		this.maestroPArr_dia = maestroPArr_dia;
		this.maestroTasaCambio = maestroTasaCambio;
		this.maestroTotalVenta = maestroTotalVenta;
		this.maestroTotalArriendo = maestroTotalArriendo;
		this.maestroTotalCfi = maestroTotalCfi;
		this.maestroTotalTotal = maestroTotalTotal;
	}




	public ModCalc_GuiasPer() {
		super();
	}

	static DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00",symbols);

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	public static List<ModCalc_GuiasPer> resumenGuiasPer(String desdeAAMMDD, String hastaAAMMDD, Map<String, Double> mapFijaTasas, Map<Long,Double> mapTasas, 
			Map<Long,Calc_BodegaEmpresa> mapBodegaEmpresa, Map<String,Calc_Precio> mapPrecios, Map<Long,Calc_Precio> mapMaestroPrecios, 
			List<Inventarios> guiasPer, Map<String,String> mapPermanencias) {
		List<ModCalc_GuiasPer> listado = new ArrayList<ModCalc_GuiasPer>();
		try {
			//		SON FUNCIONES QUE ALIMENTAN ESTA FUNCION
			//		List<Long> listIdBodegaEmpresa = ModCalc_InvInicial.listIdBodegaEmpresa(con, db, "");
			//		Map<Long,Calc_BodegaEmpresa> mapBodegaEmpresa = Calc_BodegaEmpresa.mapAllBodegasVigentes(con, db);
			//		Map<String,Calc_Precio> mapPrecios = Calc_Precio.mapPrecios(con, db, listIdBodegaEmpresa);
			//		Map<Long,Calc_Precio> mapMaestroPrecios = Calc_Precio.mapMaestroPrecios(con, db);
			Fechas desde = Fechas.obtenerFechaDesdeStrAAMMDD(desdeAAMMDD);
			Fechas hasta = Fechas.obtenerFechaDesdeStrAAMMDD(hastaAAMMDD);
			Calc_BodegaEmpresa bodegaEmpresa = new Calc_BodegaEmpresa();
			Long baseCalculo = (long) 1;
			//DETERMINA LOS TOTALES DE GUIAS DEL PERIODO
			for (int i = 0; i < guiasPer.size(); i++) {
				Map<Long, Double> tasas = new HashMap<Long, Double>();
				for (Map.Entry<Long, Double> entry : mapTasas.entrySet()) {
					Long k = entry.getKey();
					Double v = entry.getValue();
					Double aux = mapFijaTasas.get(guiasPer.get(i).id_bodegaEmpresa + "_" + k);
					if (aux != null) {
						tasas.put(k, aux);
					} else {
						tasas.put(k, v);
					}
				}
				Long cobraDiaDespacho = (long) 0, nDiaGraciaEnvio = (long) 0, nDiaGraciaRegreso = (long) 0, tratoDevoluciones = (long) 0;
				Double tasaCfi = (double) 0;
				bodegaEmpresa = mapBodegaEmpresa.get(guiasPer.get(i).id_bodegaEmpresa);
				if (bodegaEmpresa != null) {
					baseCalculo = bodegaEmpresa.baseCalculo;
					tasaCfi = bodegaEmpresa.tasaCfi;
					cobraDiaDespacho = bodegaEmpresa.cobraDiaDespacho;
					nDiaGraciaEnvio = bodegaEmpresa.nDiaGraciaEnvio;
					nDiaGraciaRegreso = bodegaEmpresa.nDiaGraciaRegreso;
					tratoDevoluciones = bodegaEmpresa.tratoDevoluciones - 1;
				} else {
					baseCalculo = (long) 1;
					tasaCfi = (double) 0;
					cobraDiaDespacho = (long) 0;
					nDiaGraciaEnvio = (long) 0;
					nDiaGraciaRegreso = (long) 0;
				}
				//determina cantidad de dias entre guia y fecha hasta
				Fechas fechaGuia = Fechas.obtenerFechaDesdeStrAAMMDD(guiasPer.get(i).fechaGuia);
				Long diasGuia = (long) 0;
				// si es una venta dias es cero sino determina los dias entre guia y hasta
				if (guiasPer.get(i).esVenta != 1) {
					diasGuia = (long) Fechas.diasEntreFechas(fechaGuia.getFechaCal(), hasta.getFechaCal());
					//devolucion
					if (guiasPer.get(i).id_tipoMovimiento == 2) {
						diasGuia = diasGuia + nDiaGraciaRegreso - tratoDevoluciones;
					}
					//entrada
					if (guiasPer.get(i).id_tipoMovimiento == 1) {
						diasGuia = diasGuia + cobraDiaDespacho - nDiaGraciaEnvio;
						if (diasGuia < 0) diasGuia = (long) 0;
					}
					if (baseCalculo == 2) {

						String[] mes = fechaGuia.fechaStrAAMMDD.split("-");
						Long auxDia = Long.parseLong(mes[2].trim());

						if ((long) auxDia == (long) 1 && (long) guiasPer.get(i).id_tipoMovimiento == (long) 1) {
							diasGuia = (long) 30;
						} else if ((long) auxDia == (long) 1 && (long) guiasPer.get(i).id_tipoMovimiento == (long) 2) {
							diasGuia = (long) 29;
						} else {
							diasGuia = ModCalc_GuiasPer.diasGuiaBase30(desdeAAMMDD, diasGuia, guiasPer.get(i).id_tipoMovimiento, desde);
						}
					}
				}
				//obtiene precios de cada bodega
				String keyPrecio = guiasPer.get(i).id_bodegaEmpresa.toString() + "_" + guiasPer.get(i).id_equipo.toString() + "_" + guiasPer.get(i).id_cotizacion.toString();
				Calc_Precio precios = mapPrecios.get(keyPrecio);
				Double precioVenta = (double) 0, pArr_dia = (double) 0, tasaCambio = (double) 0, totalArriendo = (double) 0, totalCfi = (double) 0, totalVenta = (double) 0;
				Long id_moneda = (long) 1;
				if (precios != null) {
					precioVenta = precios.precioVenta;
					pArr_dia = precios.precioArriendo_dia;
					tasaCambio = tasas.get(precios.id_moneda);
					if (tasaCambio == null) {
						tasaCambio = (double) 1;
					}
					id_moneda = precios.id_moneda;
				}
				//obtiene precios del maestro de precios
				Calc_Precio maestroPrecios = mapMaestroPrecios.get(guiasPer.get(i).id_equipo);
				Double maestroPrecioVenta = (double) 0, maestroPArr_dia = (double) 0, maestroTasaCambio = (double) 0, maestroTotalArriendo = (double) 0, maestroTotalCfi = (double) 0, maestroTotalVenta = (double) 0;
				Long maestroId_moneda = (long) 1;
				if (maestroPrecios != null) {
					maestroPrecioVenta = maestroPrecios.precioVenta;
					maestroPArr_dia = maestroPrecios.precioArriendo_dia;
					maestroTasaCambio = tasas.get(maestroPrecios.id_moneda);
					if (maestroTasaCambio == null) {
						maestroTasaCambio = (double) 1;
					}
					maestroId_moneda = maestroPrecios.id_moneda;
				}
				//valoriza la guia
				if ((long) guiasPer.get(i).esVenta == (long) 0) {
					totalArriendo = guiasPer.get(i).cantidad * diasGuia * pArr_dia * tasaCambio;
					maestroTotalArriendo = guiasPer.get(i).cantidad * diasGuia * maestroPArr_dia * maestroTasaCambio;
					if ((long) guiasPer.get(i).id_tipoMovimiento == (long) 1) {
						totalCfi = guiasPer.get(i).cantidad * precioVenta * tasaCfi * tasaCambio;
						maestroTotalCfi = (double) 0;
						//modifica arriendo si existe precio minimo y aplica
						Long permanenciaMinima = (long) 0;
						Double precioMinimo = (double) 0;
						if (precios != null) {
							permanenciaMinima = precios.permanenciaMinima;
							precioMinimo = precios.precioMinimo;
						}
						if (permanenciaMinima > 0 && precioMinimo > 0) {
							String auxFechaGuia = guiasPer.get(i).fechaGuia;
							Long id_equipo = guiasPer.get(i).id_equipo;
							Long id_bodegaEmpresa = guiasPer.get(i).id_bodegaEmpresa;
							Long id_cotizacion = guiasPer.get(i).id_cotizacion;
							String fechMin = mapPermanencias.get(id_equipo + "_" + id_bodegaEmpresa + "_" + id_cotizacion);
							if (fechMin != null) {
								Fechas minCal = Fechas.obtenerFechaDesdeStrAAMMDD(fechMin);
								Fechas guiaCal = Fechas.obtenerFechaDesdeStrAAMMDD(auxFechaGuia);
								int perm = Fechas.diasEntreFechas(minCal.fechaCal, guiaCal.fechaCal);
								Long auxPerm = (long) perm;
								if (auxPerm < diasGuia) {
									auxPerm = diasGuia;
								}
								if (auxPerm <= permanenciaMinima) {
									totalArriendo = guiasPer.get(i).cantidad * precios.precioMinimo * tasaCambio;
								}
							}
						}
					}
				} else {
					totalVenta = guiasPer.get(i).cantidad * precioVenta * tasaCambio;
					maestroTotalVenta = guiasPer.get(i).cantidad * maestroPrecioVenta * maestroTasaCambio;
				}
				ModCalc_GuiasPer aux = new ModCalc_GuiasPer();
				aux.id_bodegaEmpresa = guiasPer.get(i).id_bodegaEmpresa;
				aux.id_grupo = guiasPer.get(i).id_grupo;
				aux.id_equipo = guiasPer.get(i).id_equipo;
				aux.id_cotizacion = guiasPer.get(i).id_cotizacion;
				aux.esVenta = guiasPer.get(i).esVenta;
				aux.id_guia = guiasPer.get(i).id_guia;
				aux.fechaGuia = guiasPer.get(i).fechaGuia;
				aux.id_tipoMovimiento = guiasPer.get(i).id_tipoMovimiento;
				aux.cantidad = guiasPer.get(i).cantidad;
				aux.dias = diasGuia;
				aux.pArr_dia = pArr_dia;
				aux.tasaCambio = tasaCambio;
				aux.totalVenta = totalVenta;
				aux.totalArriendo = totalArriendo;
				aux.totalCfi = totalCfi;
				aux.id_moneda = id_moneda;
				aux.pVenta = precioVenta;
				aux.totalTotal = totalVenta + totalArriendo + totalCfi;
				aux.maestroPArr_dia = maestroPArr_dia;
				aux.maestroTasaCambio = maestroTasaCambio;
				aux.maestroTotalVenta = maestroTotalVenta;
				aux.maestroTotalArriendo = maestroTotalArriendo;
				aux.maestroTotalCfi = maestroTotalCfi;
				aux.maestroId_moneda = maestroId_moneda;
				aux.maestroPVenta = maestroPrecioVenta;
				aux.maestroTotalTotal = maestroTotalVenta + maestroTotalArriendo + maestroTotalCfi;
				listado.add(aux);
			}
		} catch (Exception e) {
			String className = ActaBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, "", e);
		}
		return(listado);
	}

	public static Long diasGuiaBase30 (String desdeAAMMDD, Long diasPeriodo, Long id_tipoMovimiento, Fechas desde) {
		try {
			String[] mes = desdeAAMMDD.split("-");
			Long auxMes= Long.parseLong(mes[1].trim());
			if(auxMes!=2 && diasPeriodo==31) diasPeriodo=(long)30;
			if(diasPeriodo>31) {
				Long diasDePaso=(long)0;
				Double factorMeses=(double) diasPeriodo/30;
				Long jPart = factorMeses.longValue();
				Double fPart = factorMeses - jPart;
				diasPeriodo=(long)0;
				for(int j=0;j<jPart;j++) {
					if(auxMes==2)  diasDePaso = (long)28;
					if(auxMes==1||auxMes==3||auxMes==5||auxMes==7||auxMes==8||auxMes==10||auxMes==12)  diasDePaso=(long)30;
					if(auxMes==4||auxMes==6||auxMes==9||auxMes==11)  diasDePaso=(long)30;
					diasPeriodo=diasPeriodo+diasDePaso;
				}
				fPart=fPart*30;
				diasPeriodo=diasPeriodo+fPart.longValue();
			}
			if(id_tipoMovimiento == 2 && (auxMes==1||auxMes==3||auxMes==5||auxMes==7||auxMes==8||auxMes==10||auxMes==12)) {
				diasPeriodo = diasPeriodo - 1;
			}
			if(id_tipoMovimiento == 2 && auxMes == 2) {
				int diasMes = desde.getFechaCal().getActualMaximum(Calendar.DAY_OF_MONTH);
				if(diasMes==28) {
					diasPeriodo = diasPeriodo + 2;
				} else {
					diasPeriodo = diasPeriodo + 1;
				}
			}
			return diasPeriodo;
		} catch (Exception e) {
			String className = ActaBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, "", e);
			return null;
		}
	}
	
	public static List<Long> listIdBodegaEmpresa (Connection con, String db, String permisoPorBodega){
		List<Long> lista = new ArrayList<Long>();
		try {
			permisoPorBodega = permisoPorBodega.replaceAll("movimiento", "bodegaEmpresa").replaceAll("id_bodegaEmpresa", "id");
			String query = String.format(" select id from `%s`.bodegaEmpresa where esInterna = 2 and vigente = 1 " + permisoPorBodega + ";",db);
			try (PreparedStatement smt = con.prepareStatement(query)) {
				try (ResultSet rs = smt.executeQuery()) {
					while (rs.next()) {
						lista.add(rs.getLong(1));
					}
				}
			} catch (SQLException e) {
				String className = ActaBaja.class.getSimpleName();
				String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
			}
		} catch (Exception e) {
			String className = ActaBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return lista;
	}
	
	
	public static List<Long> listIdGuia_entreFecha (Connection con, String db, String desdeAAMMDD, String hastaAAMMDD){
		List<Long> lista = new ArrayList<Long>();
		try {
			desdeAAMMDD  = Fechas.AAMMDD(desdeAAMMDD);
			hastaAAMMDD  = Fechas.AAMMDD(hastaAAMMDD);
			String query = String.format(" select id from `%s`.guia where fecha between  '" + desdeAAMMDD + "' and '" + hastaAAMMDD + "';",db);
			try (PreparedStatement smt = con.prepareStatement(query)) {
				try (ResultSet rs = smt.executeQuery()) {
					while (rs.next()) {
						lista.add(rs.getLong(1));
					}
				}
			} catch (SQLException e) {
				String className = ActaBaja.class.getSimpleName();
				String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
			}
		} catch (Exception e) {
			String className = ActaBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return lista;
	}
	
	public static Map<String,String> mapDiasFechaMinGuiaPorEquipo(Connection con, String db){
		Map<String,String> map = new HashMap<String,String>();
		String query = String.format(" select min(guia.fecha), "
				+ " movimiento.id_equipo, movimiento.id_bodegaEmpresa, movimiento.id_cotizacion "
				+ " from `%s`.movimiento  "
				+ " left join `%s`.guia on guia.id = movimiento.id_guia "
				+ " where id_tipoMovimiento = 1"
				+ " group by movimiento.id_equipo, movimiento.id_bodegaEmpresa, movimiento.id_cotizacion;",db,db);
		try (PreparedStatement smt = con.prepareStatement(query)) {
			try (ResultSet rs = smt.executeQuery()) {
				while (rs.next()) {
					map.put(rs.getString(2) + "_" + rs.getString(3) + "_" + rs.getString(4), rs.getString(1));
				}
			}
		} catch (SQLException e) {
			String className = ActaBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return map;
	}

	public static Map<String,Guia> mapUltimaGuiaArrIngreso(Connection con, String db, Map<Long, Guia> allGuias){
		Map<String, Guia> map = new HashMap<String,Guia>();
		String query = String.format(" select" +
				" movimiento.id_bodegaEmpresa," +
				" equipo.codigo," +
				" ifnull(cotizacion.numero,0)," +
				" max(id_guia)" +
				" from`%s`.movimiento" +
				" left join `%s`.equipo on equipo.id = movimiento.id_equipo" +
				" left join `%s`.cotizacion on cotizacion.id = movimiento.id_cotizacion" +
				" where id_tipoMovimiento=1 and id_guia > 0 and movimiento.id_bodegaEmpresa > 0" +
				" group by movimiento.id_equipo, movimiento.id_bodegaEmpresa, movimiento.id_cotizacion;",db,db,db);
		try (PreparedStatement smt = con.prepareStatement(query)) {
			try (ResultSet rs = smt.executeQuery()) {
				while (rs.next()) {
					Guia guia = allGuias.get(rs.getLong(4));
					if (guia != null && guia.getNumero() > 0) {
						map.put(rs.getString(1) + "_" + rs.getString(2) + "_" + rs.getString(3), guia);
					}
				}
			}
		} catch (SQLException e) {
			String className = ActaBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return map;
	}

	public static Map<String,Guia> mapUltimaGuiaArrEgreso(Connection con, String db, Map<Long, Guia> allGuias){
		Map<String, Guia> map = new HashMap<String,Guia>();
		String query = String.format(" select" +
				" movimiento.id_bodegaEmpresa," +
				" equipo.codigo," +
				" ifnull(cotizacion.numero,0)," +
				" max(id_guia)" +
				" from `%s`.movimiento" +
				" left join `%s`.equipo on equipo.id = movimiento.id_equipo" +
				" left join `%s`.cotizacion on cotizacion.id = movimiento.id_cotizacion" +
				" where id_tipoMovimiento=2 and id_guia > 0 and movimiento.id_bodegaEmpresa > 0" +
				" group by movimiento.id_equipo, movimiento.id_bodegaEmpresa, movimiento.id_cotizacion;",db,db,db);
		try (PreparedStatement smt = con.prepareStatement(query)) {
			try (ResultSet rs = smt.executeQuery()) {
				while (rs.next()) {
					Guia guia = allGuias.get(rs.getLong(4));
					if (guia != null && guia.getNumero() > 0) {
						map.put(rs.getString(1) + "_" + rs.getString(2) + "_" + rs.getString(3), guia);
					}
				}
			}
		} catch (SQLException e) {
			String className = ActaBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return map;
	}

	
}
	
	
	
