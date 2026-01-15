package models.reports;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import controllers.HomeController;
import models.tables.*;

import org.apache.commons.io.IOUtils;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.util.TempFile;

import models.calculo.Inventarios;
import models.utilities.Archivos;
import models.utilities.DecimalFormato;
import models.utilities.Fechas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ReportInventarios {

	static DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
	static SimpleDateFormat myformatfecha = new SimpleDateFormat("dd-MM-yyyy");
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00",symbols);
	static DecimalFormat myformatdoubleV = new DecimalFormat("#,##0.00",symbols);
	static DecimalFormat myformatint = new DecimalFormat("#,##0",symbols);
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00",symbols);
	static DecimalFormat myformatMonedaOrigen = new DecimalFormat("#,##0",symbols);

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	public static List<List<String>> reportInventarioEquipoConTipoBodega(Connection con, String db, String fechaCorte, String permisoPorBodega,
			Map<Long,List<Double>> mapPCompra, Map<Long,List<Double>> mapPLista, Map<Long,String> moneda, String tipo, Map<String,String> mapeoDiccionario,
			String esPorSucursal, String id_sucursal, Map<Long,Equipo> mapEquipo, Map<Long,Sucursal> mapSucursal, Map<Long,UnidadTiempo> mapUnidadTiempo,
			Map<Long,Double> tasasCorte, Map<Long,Long> dec) {
		List<List<String>> lista = new ArrayList<List<String>>();
		
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and bodegaEmpresa.id_sucursal = %s".formatted(id_sucursal);
		}
		
		
		String condicionaSuma = "";
		if(tipo.equals(mapeoDiccionario.get("ARRIENDO")) || tipo.equals("ARRIENDO")) {
			condicionaSuma = new StringBuilder()
					.append(" if(")
					.append("sum(if(movimiento.id_tipoMovimiento=1,1,-1) * movimiento.cantidad  * if(movimiento.esVenta=0, 1, 0)) = -0, 0, ")
					.append("sum(if(movimiento.id_tipoMovimiento=1,1,-1) * movimiento.cantidad  * if(movimiento.esVenta=0, 1, 0))) as cantidad,")
					.toString();
		}else if(tipo.equals("VENTA")) {
			condicionaSuma = new StringBuilder()
					.append(" if(")
					.append("sum(if(movimiento.id_tipoMovimiento=1,1,-1) * movimiento.cantidad * if(bodegaEmpresa.esInterna=1, 0, if(movimiento.esVenta=1, 1, 0))) = -0, 0, ")
					.append("sum(if(movimiento.id_tipoMovimiento=1,1,-1) * movimiento.cantidad * if(bodegaEmpresa.esInterna=1 ,0, if(movimiento.esVenta=1, 1, 0)))) as cantidad,")
					.toString();
		}else if(tipo.equals("TODO")){
			condicionaSuma = new StringBuilder()
					.append("if(")
					.append("sum(if(movimiento.id_tipoMovimiento=1,1,-1)*movimiento.cantidad)=-0,0,")
					.append("sum(if(movimiento.id_tipoMovimiento=1,1,-1)*movimiento.cantidad)), ")
					.toString();
		}else {
			condicionaSuma = new StringBuilder()
					.append("if(")
					.append("sum(if(movimiento.id_tipoMovimiento=1,1,-1)*movimiento.cantidad)=-0,0,")
					.append("sum(if(movimiento.id_tipoMovimiento=1,1,-1)*movimiento.cantidad)), ")
					.toString();
		}
		String query = String.format(" select " +
				/*1*/	" 'fecha_factura',   " +
				/*2*/	" 'fecha_actaBaja',   " +
				/*3*/	" movimiento.id_equipo, " +
				/*4*/	condicionaSuma +
				/*5*/	" guia.fecha,   " +
				/*6*/	" bodegaEmpresa.esInterna, " +
				/*7*/	" bodegaEmpresa.id, " +
				/*8*/	" bodegaEmpresa.nombre, " +
				/*9*/	" bodegaEmpresa.id_sucursal " +
				" from `%s`.movimiento  " +
				" left join `%s`.guia on guia.id = movimiento.id_guia   " +
				" left join `%s`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa " +
				" where (guia.fecha is null or guia.fecha<=?) " +
				" and (fecha_factura is null or fecha_factura<=?) " +
				" and (fecha_actaBaja is null or fecha_actaBaja<=?)" +
				" and movimiento.id_bodegaEmpresa <> 0" +
				condSucursal +
				permisoPorBodega+
				" group by bodegaEmpresa.esInterna, bodegaEmpresa.id, movimiento.id_equipo;",db,db,db);

		
		try (PreparedStatement smt6 = con.prepareStatement(query)){
			smt6.setString(1, fechaCorte.trim());
			smt6.setString(2, fechaCorte.trim());
			smt6.setString(3, fechaCorte.trim());

			try(ResultSet rs6 = smt6.executeQuery()) {
				while (rs6.next()) {
					if (rs6.getDouble(4) != (double) 0) {
						Long id_grupo = (long) 0;
						String nomGrupo = "SIN GRUPO";
						String codEquipo = "";
						String nomEquipo = "";
						String nomUnidad = "";
						String imgEquipo = "0";
						Equipo equipo = mapEquipo.get(rs6.getLong(3));
						if (equipo != null) {
							id_grupo = equipo.getId_grupo();
							nomGrupo = equipo.getGrupo();
							codEquipo = equipo.getCodigo();
							nomEquipo = equipo.getNombre();
							nomUnidad = equipo.getUnidad();
							imgEquipo = equipo.getImg();
						}
						String nameSucursal = "";
						Sucursal sucursal = mapSucursal.get(rs6.getLong(9));
						if (sucursal != null) {
							nameSucursal = sucursal.getNombre();
						}
						List<Double> auxMap = mapPCompra.get(rs6.getLong(3));
						Long idMonedaCompra = (long) 1;
						Double ultimaCompra = (double) 0;
						if (auxMap != null) {
							idMonedaCompra = auxMap.get(1).longValue();
							ultimaCompra = auxMap.get(0);
						}
						auxMap = mapPLista.get(rs6.getLong(3));
						Long idMonedaVentaArr = (long) 1;
						Double precioVenta = (double) 0;
						Double precioArriendo = (double) 0;
						Long idUnidadTiempo = (long) 2;
						if (auxMap != null) {
							idMonedaVentaArr = auxMap.get(3).longValue();
							precioVenta = auxMap.get(0);
							precioArriendo = auxMap.get(1);
							idUnidadTiempo = auxMap.get(2).longValue();
						}
						String unidadTiempo = "";
						if (mapUnidadTiempo != null && mapUnidadTiempo.get(idUnidadTiempo) != null) {
							unidadTiempo = mapUnidadTiempo.get(idUnidadTiempo).getNombre();
						}
						Double tasaCompra = tasasCorte.get(idMonedaCompra);
						if (tasaCompra == null || tasaCompra == (double) 0) {
							tasaCompra = (double) 1;
						}
						Double tasaVenta = tasasCorte.get(idMonedaVentaArr);
						if (tasaVenta == null || tasaVenta == (double) 0) {
							tasaVenta = (double) 1;
						}
						switch (dec.get(idMonedaCompra).toString()) {
							case "0":
								myformatdouble = new DecimalFormat("#,##0", symbols);
								break;
							case "2":
								myformatdouble = new DecimalFormat("#,##0.00", symbols);
								break;
							case "4":
								myformatdouble = new DecimalFormat("#,##0.0000", symbols);
								break;
							case "6":
								myformatdouble = new DecimalFormat("#,##0.000000", symbols);
								break;
							default:
								break;
						}
						switch (dec.get(idMonedaVentaArr).toString()) {
							case "0":
								myformatdoubleV = new DecimalFormat("#,##0", symbols);
								break;
							case "2":
								myformatdoubleV = new DecimalFormat("#,##0.00", symbols);
								break;
							case "4":
								myformatdoubleV = new DecimalFormat("#,##0.0000", symbols);
								break;
							case "6":
								myformatdoubleV = new DecimalFormat("#,##0.000000", symbols);
								break;
							default:
								break;
						}
						switch (dec.get((long) 1).toString()) {
							case "0":
								myformatMonedaOrigen = new DecimalFormat("#,##0", symbols);
								break;
							case "2":
								myformatMonedaOrigen = new DecimalFormat("#,##0.00", symbols);
								break;
							case "4":
								myformatMonedaOrigen = new DecimalFormat("#,##0.0000", symbols);
								break;
							case "6":
								myformatMonedaOrigen = new DecimalFormat("#,##0.000000", symbols);
								break;
							default:
								break;
						}
						List<String> aux = new ArrayList<String>();
						aux.add(rs6.getString(3));                                //  0 id equipo
						aux.add(nomGrupo);                                        //  1 nombre grupo
						aux.add(codEquipo);                                    //  2 codigo equipo
						aux.add(nomEquipo);                                    //  3 nombre equipo
						aux.add(moneda.get(idMonedaCompra));                    //  4 nickname moneda de compra
						aux.add(myformatdouble.format(ultimaCompra));            //  5 precio compra ultima por unidad
						aux.add(moneda.get(idMonedaVentaArr));                    //  6 nickname moneda de venta - arriendo
						aux.add(myformatdoubleV.format(precioVenta));            //  7 precio venta/reposicion por unidad
						aux.add(unidadTiempo);                                    //  8 unidad de tiempo o medida arriendo
						aux.add(myformatdoubleV.format(precioArriendo));        //  9 precio arriendo por unidad
						aux.add(nomUnidad);                                    // 10 unidad de medida
						aux.add(myformatdouble2.format(rs6.getDouble(4)));        // 11 cantidad
						Double total = rs6.getDouble(4) * ultimaCompra;
						aux.add(myformatdouble.format(total));                    // 12 valor total de compra
						Double repos = rs6.getDouble(4) * precioVenta;
						aux.add(myformatdouble.format(repos));                    // 13 valor total de reposicion o venta
						aux.add(myformatMonedaOrigen.format(total * tasaCompra)); // 14 total pesos compra
						aux.add(myformatMonedaOrigen.format(repos * tasaVenta));    // 15 total pesos venta
						aux.add(imgEquipo);                                    // 16 img
						aux.add(myformatdouble2.format(tasaVenta));            // 17 tasa de cambio venta/arriendo
						aux.add(rs6.getString(6));                                // 18 es interna
						aux.add(id_grupo.toString());                            // 19 id_grupo
						aux.add(rs6.getString(7));                                // 20 id_bodegaEmpresa
						aux.add(rs6.getString(8));                                // 21 nombre bodegaEmpresa
						aux.add(nameSucursal);                                    // 22 nameSucursal
						lista.add(aux);
					}
				}
			}
		} catch (SQLException e) {
			String className = ReportInventarios.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (lista);
	}
	
	public static List<List<String>> reportInventarioEquipo(Connection con, String db, String fechaCorte, String permisoPorBodega,
			Map<Long,List<Double>> mapPCompra, Map<Long,List<Double>> mapPLista, Map<Long,String> moneda, String tipo, 
			Map<String,String> mapeoDiccionario, String esPorSucursal, String id_sucursal, Map<Long,Equipo> mapEquipo, Map<Long,Sucursal> mapSucursal, Map<Long,UnidadTiempo> mapUnidadTiempo,
			Map<Long,Double> tasasCorte, Map<Long,Long> dec) {
		
		List<List<String>> lista = new ArrayList<List<String>>();
		
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and bodegaEmpresa.id_sucursal = " + id_sucursal;
		}
		
		String condicionaSuma = "";
		if(tipo.equals(mapeoDiccionario.get("ARRIENDO")) || tipo.equals("ARRIENDO")) {
			condicionaSuma = " if("
					+ "sum(if(movimiento.id_tipoMovimiento=1,1,-1) * movimiento.cantidad  * if(movimiento.esVenta=0, 1, 0)) = -0, 0, "
					+ "sum(if(movimiento.id_tipoMovimiento=1,1,-1) * movimiento.cantidad  * if(movimiento.esVenta=0, 1, 0))) as cantidad,";
		}else if(tipo.equals("VENTA")) {
			condicionaSuma = " if("
					+ "sum(if(movimiento.id_tipoMovimiento=1,1,-1) * movimiento.cantidad * if(bodegaEmpresa.esInterna=1, 0, if(movimiento.esVenta=1, 1, 0))) = -0, 0, "
					+ "sum(if(movimiento.id_tipoMovimiento=1,1,-1) * movimiento.cantidad * if(bodegaEmpresa.esInterna=1, 0, if(movimiento.esVenta=1, 1, 0)))) as cantidad,";
		}else if(tipo.equals("TODO")){
			condicionaSuma = "if("
					+ "sum(if(movimiento.id_tipoMovimiento=1,1,-1)*movimiento.cantidad)=-0,0,"
					+ "sum(if(movimiento.id_tipoMovimiento=1,1,-1)*movimiento.cantidad)), ";
		}
		
		try {
			PreparedStatement smt6 = con
					.prepareStatement(" select " +
							/*1*/	" 'fecha_factura',   " +
							/*2*/	" 'fecha_actaBaja',   " +
							/*3*/	" movimiento.id_equipo, " +  
							/*4*/	condicionaSuma +
							/*5*/	" guia.fecha,   " +
							/*6*/	" bodegaEmpresa.esInterna, " +
							/*7*/	" bodegaEmpresa.id, " +
							/*8*/	" bodegaEmpresa.nombre, " +
							/*9*/	" bodegaEmpresa.id_sucursal " +
								" from `"+db+"`.movimiento  " + 
								" left join `"+db+"`.guia on guia.id = movimiento.id_guia   " +
								" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa " +
								" where (guia.fecha is null or guia.fecha<=?) " +
								" and (fecha_factura is null or fecha_factura<=?) " +
								" and (fecha_actaBaja is null or fecha_actaBaja<=?)" +
								condSucursal +
								permisoPorBodega+
							" group by movimiento.id_equipo;");
			smt6.setString(1, fechaCorte.trim());
			smt6.setString(2, fechaCorte.trim());
			smt6.setString(3, fechaCorte.trim());
			ResultSet rs6 = smt6.executeQuery();

			
			while (rs6.next()) {
				if(rs6.getDouble(4) != (double) 0){
					
					Long id_grupo = (long)0;
					String nomGrupo = "SIN GRUPO";
					String codEquipo = "";
					String nomEquipo = "";
					String nomUnidad = "";
					String imgEquipo = "0";
					String namePropiedad = "";
					Equipo equipo = mapEquipo.get(rs6.getLong(3));
					if(equipo != null) {
						id_grupo = equipo.getId_grupo();
						nomGrupo = equipo.getGrupo();
						codEquipo = equipo.getCodigo();
						nomEquipo = equipo.getNombre();
						nomUnidad = equipo.getUnidad();
						imgEquipo = equipo.getImg();
						namePropiedad = equipo.getPropiedad();
					}
					
					String nameSucursal = "";
					Sucursal sucursal = mapSucursal.get(rs6.getLong(9));
					if(sucursal!=null) {
						nameSucursal = sucursal.getNombre();
					}
					
					List<Double> auxMap = mapPCompra.get(rs6.getLong(3));
					Long idMonedaCompra = (long)1;
					Double ultimaCompra = (double)0;
					if(auxMap!=null) {
						idMonedaCompra=auxMap.get(1).longValue();
						ultimaCompra = auxMap.get(0);
					}
						
					auxMap = mapPLista.get(rs6.getLong(3));
					Long idMonedaVentaArr = (long)1;
					Double precioVenta = (double)0;
					Double precioArriendo = (double)0;
					Long idUnidadTiempo = (long)2;
					if(auxMap != null) {
						idMonedaVentaArr = auxMap.get(3).longValue();
						precioVenta = auxMap.get(0);
						precioArriendo = auxMap.get(1);
						idUnidadTiempo = auxMap.get(2).longValue();
					}
					
					String unidadTiempo = "";
					if(mapUnidadTiempo != null && mapUnidadTiempo.get(idUnidadTiempo)!=null) {
						unidadTiempo = mapUnidadTiempo.get(idUnidadTiempo).getNombre();
					}
					
					Double tasaCompra = tasasCorte.get(idMonedaCompra);
					if(tasaCompra == null || tasaCompra == (double) 0) {
						tasaCompra=(double)1;
					}
					
					Double tasaVenta = tasasCorte.get(idMonedaVentaArr);
					if(tasaVenta == null || tasaVenta == (double)0) {
						tasaVenta=(double)1;
					}
					
					switch(dec.get(idMonedaCompra).toString()) {
					 case "0": myformatdouble = new DecimalFormat("#,##0",symbols); break;
					 case "2": myformatdouble = new DecimalFormat("#,##0.00",symbols); break;
					 case "4": myformatdouble = new DecimalFormat("#,##0.0000",symbols); break;
					 case "6": myformatdouble = new DecimalFormat("#,##0.000000",symbols); break;
					 default:  break;
					}
					
					switch(dec.get(idMonedaVentaArr).toString()) {
					 case "0": myformatdoubleV = new DecimalFormat("#,##0",symbols); break;
					 case "2": myformatdoubleV = new DecimalFormat("#,##0.00",symbols); break;
					 case "4": myformatdoubleV = new DecimalFormat("#,##0.0000",symbols); break;
					 case "6": myformatdoubleV = new DecimalFormat("#,##0.000000",symbols); break;
					 default:  break;
					}
					
					switch(dec.get((long) 1).toString()) {
					 case "0": myformatMonedaOrigen = new DecimalFormat("#,##0",symbols); break;
					 case "2": myformatMonedaOrigen = new DecimalFormat("#,##0.00",symbols); break;
					 case "4": myformatMonedaOrigen = new DecimalFormat("#,##0.0000",symbols); break;
					 case "6": myformatMonedaOrigen = new DecimalFormat("#,##0.000000",symbols); break;
					 default:  break;
					}
					
					List<String> aux = new ArrayList<String>();
					aux.add(rs6.getString(3)); 								//  0 id equipo
					aux.add(nomGrupo); 										//  1 nombre grupo
					aux.add(codEquipo); 									//  2 codigo equipo
					aux.add(nomEquipo); 									//  3 nombre equipo
					aux.add(moneda.get(idMonedaCompra)); 					//  4 nickname moneda de compra					
					aux.add(myformatdouble.format(ultimaCompra)); 			//  5 precio compra ultima por unidad
					
					aux.add(moneda.get(idMonedaVentaArr)); 					//  6 nickname moneda de venta - arriendo
					aux.add(myformatdoubleV.format(precioVenta)); 			//  7 precio venta/reposicion por unidad
					aux.add(unidadTiempo);									//  8 unidad de tiempo o medida arriendo
					aux.add(myformatdoubleV.format(precioArriendo)); 		//  9 precio arriendo por unidad
					
					aux.add(nomUnidad); 									// 10 unidad de medida
					aux.add(myformatdouble2.format(rs6.getDouble(4))); 		// 11 cantidad
					Double total = rs6.getDouble(4)*ultimaCompra;
					aux.add(myformatdouble.format(total)); 					// 12 valor total de compra
					Double repos = rs6.getDouble(4)*precioVenta;
					aux.add(myformatdouble.format(repos)); 					// 13 valor total de reposicion o venta
					
					aux.add(myformatMonedaOrigen.format(total*tasaCompra)); // 14 total pesos compra
					aux.add(myformatMonedaOrigen.format(repos*tasaVenta)); 	// 15 total pesos venta
					aux.add(imgEquipo); 									// 16 img
					
					aux.add(myformatdouble2.format(tasaVenta)); 			// 17 tasa de cambio venta/arriendo
					
					aux.add(rs6.getString(6)); 								// 18 es interna
					
					aux.add(id_grupo.toString()); 							// 19 id_grupo
					
					aux.add(rs6.getString(7)); 								// 20 id_bodegaEmpresa
					aux.add(rs6.getString(8)); 								// 21 nombre bodegaEmpresa
					aux.add(nameSucursal); 									// 22 nameSucursal
					aux.add(namePropiedad); 									// 23 namePropiedad
					
					lista.add(aux);
				}
			}
			rs6.close();
			smt6.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<List<String>> reportInventarioEquipoDesagrupado(Connection con, String db, String fechaCorte, String permisoPorBodega,
			Map<Long,List<Double>> mapPCompra, Map<Long,List<Double>> mapPLista, Map<Long,String> moneda, String tipo, 
			Map<String,String> mapeoDiccionario, String esPorSucursal, String id_sucursal, Map<Long,Equipo> mapEquipo, Map<Long,Sucursal> mapSucursal, Map<Long,UnidadTiempo> mapUnidadTiempo,
			Map<Long,Double> tasasCorte, Map<Long,Long> dec) {
		
		List<List<String>> lista = new ArrayList<List<String>>();
		
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and bodegaEmpresa.id_sucursal = " + id_sucursal;
		}
		
		String condicionaSuma = "";
		if(tipo.equals(mapeoDiccionario.get("ARRIENDO")) || tipo.equals("ARRIENDO")) {
			condicionaSuma = " if("
					+ "sum(if(movimiento.id_tipoMovimiento=1,1,-1) * movimiento.cantidad  * if(movimiento.esVenta=0, 1, 0)) = -0, 0, "
					+ "sum(if(movimiento.id_tipoMovimiento=1,1,-1) * movimiento.cantidad  * if(movimiento.esVenta=0, 1, 0))) as cantidad,";
		}else if(tipo.equals("VENTA")) {
			condicionaSuma = " if("
					+ "sum(if(movimiento.id_tipoMovimiento=1,1,-1) * movimiento.cantidad * if(bodegaEmpresa.esInterna=1, 0, if(movimiento.esVenta=1, 1, 0))) = -0, 0, "
					+ "sum(if(movimiento.id_tipoMovimiento=1,1,-1) * movimiento.cantidad * if(bodegaEmpresa.esInterna=1, 0, if(movimiento.esVenta=1, 1, 0)))) as cantidad,";
		}else if(tipo.equals("TODO")){
			condicionaSuma = "if("
					+ "sum(if(movimiento.id_tipoMovimiento=1,1,-1)*movimiento.cantidad)=-0,0,"
					+ "sum(if(movimiento.id_tipoMovimiento=1,1,-1)*movimiento.cantidad)), ";
		}
		
		try {
			PreparedStatement smt6 = con
					.prepareStatement(" select " +
							/*1*/	" 'fecha_factura',   " +
							/*2*/	" 'fecha_actaBaja',   " +
							/*3*/	" movimiento.id_equipo, " +  
							/*4*/	condicionaSuma +
							/*5*/	" guia.fecha,   " +
							/*6*/	" bodegaEmpresa.esInterna, " +
							/*7*/	" bodegaEmpresa.id, " +
							/*8*/	" bodegaEmpresa.nombre, " +
							/*9*/	" bodegaEmpresa.id_sucursal " +
								" from `"+db+"`.movimiento  " + 
								" left join `"+db+"`.guia on guia.id = movimiento.id_guia   " +
								" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa " +
								" where (guia.fecha is null or guia.fecha<=?) " +
								" and (fecha_factura is null or fecha_factura<=?) " +
								" and (fecha_actaBaja is null or fecha_actaBaja<=?)" +
								condSucursal +
								permisoPorBodega+
							" group by movimiento.id_equipo, bodegaEmpresa.id;");
			smt6.setString(1, fechaCorte.trim());
			smt6.setString(2, fechaCorte.trim());
			smt6.setString(3, fechaCorte.trim());
			ResultSet rs6 = smt6.executeQuery();
			
			while (rs6.next()) {
				if(rs6.getDouble(4) != (double) 0){
					
					Long id_grupo = (long)0;
					String nomGrupo = "SIN GRUPO";
					String codEquipo = "";
					String nomEquipo = "";
					String nomUnidad = "";
					String imgEquipo = "0";
					String namePropiedad = "";
					Equipo equipo = mapEquipo.get(rs6.getLong(3));
					if(equipo != null) {
						id_grupo = equipo.getId_grupo();
						nomGrupo = equipo.getGrupo();
						codEquipo = equipo.getCodigo();
						nomEquipo = equipo.getNombre();
						nomUnidad = equipo.getUnidad();
						imgEquipo = equipo.getImg();
						namePropiedad = equipo.getPropiedad();
					}
					
					String nameSucursal = "";
					Sucursal sucursal = mapSucursal.get(rs6.getLong(9));
					if(sucursal!=null) {
						nameSucursal = sucursal.getNombre();
					}
					
					List<Double> auxMap = mapPCompra.get(rs6.getLong(3));
					Long idMonedaCompra = (long)1;
					Double ultimaCompra = (double)0;
					if(auxMap!=null) {
						idMonedaCompra=auxMap.get(1).longValue();
						ultimaCompra = auxMap.get(0);
					}
						
					auxMap = mapPLista.get(rs6.getLong(3));
					Long idMonedaVentaArr = (long)1;
					Double precioVenta = (double)0;
					Double precioArriendo = (double)0;
					Long idUnidadTiempo = (long)2;
					if(auxMap != null) {
						idMonedaVentaArr = auxMap.get(3).longValue();
						precioVenta = auxMap.get(0);
						precioArriendo = auxMap.get(1);
						idUnidadTiempo = auxMap.get(2).longValue();
					}
					
					String unidadTiempo = "";
					if(mapUnidadTiempo != null && mapUnidadTiempo.get(idUnidadTiempo)!=null) {
						unidadTiempo = mapUnidadTiempo.get(idUnidadTiempo).getNombre();
					}
					
					Double tasaCompra = tasasCorte.get(idMonedaCompra);
					if(tasaCompra == null || tasaCompra == (double) 0) {
						tasaCompra=(double)1;
					}
					
					Double tasaVenta = tasasCorte.get(idMonedaVentaArr);
					if(tasaVenta == null || tasaVenta == (double)0) {
						tasaVenta=(double)1;
					}
					
					switch(dec.get(idMonedaCompra).toString()) {
					 case "0": myformatdouble = new DecimalFormat("#,##0",symbols); break;
					 case "2": myformatdouble = new DecimalFormat("#,##0.00",symbols); break;
					 case "4": myformatdouble = new DecimalFormat("#,##0.0000",symbols); break;
					 case "6": myformatdouble = new DecimalFormat("#,##0.000000",symbols); break;
					 default:  break;
					}
					
					switch(dec.get(idMonedaVentaArr).toString()) {
					 case "0": myformatdoubleV = new DecimalFormat("#,##0",symbols); break;
					 case "2": myformatdoubleV = new DecimalFormat("#,##0.00",symbols); break;
					 case "4": myformatdoubleV = new DecimalFormat("#,##0.0000",symbols); break;
					 case "6": myformatdoubleV = new DecimalFormat("#,##0.000000",symbols); break;
					 default:  break;
					}
					
					switch(dec.get((long) 1).toString()) {
					 case "0": myformatMonedaOrigen = new DecimalFormat("#,##0",symbols); break;
					 case "2": myformatMonedaOrigen = new DecimalFormat("#,##0.00",symbols); break;
					 case "4": myformatMonedaOrigen = new DecimalFormat("#,##0.0000",symbols); break;
					 case "6": myformatMonedaOrigen = new DecimalFormat("#,##0.000000",symbols); break;
					 default:  break;
					}
					
					List<String> aux = new ArrayList<String>();
					aux.add(rs6.getString(3)); 								//  0 id equipo
					aux.add(nomGrupo); 										//  1 nombre grupo
					aux.add(codEquipo); 									//  2 codigo equipo
					aux.add(nomEquipo); 									//  3 nombre equipo
					aux.add(moneda.get(idMonedaCompra)); 					//  4 nickname moneda de compra					
					aux.add(myformatdouble.format(ultimaCompra)); 			//  5 precio compra ultima por unidad
					
					aux.add(moneda.get(idMonedaVentaArr)); 					//  6 nickname moneda de venta - arriendo
					aux.add(myformatdoubleV.format(precioVenta)); 			//  7 precio venta/reposicion por unidad
					aux.add(unidadTiempo);									//  8 unidad de tiempo o medida arriendo
					aux.add(myformatdoubleV.format(precioArriendo)); 		//  9 precio arriendo por unidad
					
					aux.add(nomUnidad); 									// 10 unidad de medida
					aux.add(myformatdouble2.format(rs6.getDouble(4))); 		// 11 cantidad
					Double total = rs6.getDouble(4)*ultimaCompra;
					aux.add(myformatdouble.format(total)); 					// 12 valor total de compra
					Double repos = rs6.getDouble(4)*precioVenta;
					aux.add(myformatdouble.format(repos)); 					// 13 valor total de reposicion o venta
					
					aux.add(myformatMonedaOrigen.format(total*tasaCompra)); // 14 total pesos compra
					aux.add(myformatMonedaOrigen.format(repos*tasaVenta)); 	// 15 total pesos venta
					aux.add(imgEquipo); 									// 16 img
					
					aux.add(myformatdouble2.format(tasaVenta)); 			// 17 tasa de cambio venta/arriendo
					
					aux.add(rs6.getString(6)); 								// 18 es interna
					
					aux.add(id_grupo.toString()); 							// 19 id_grupo
					
					aux.add(rs6.getString(7)); 								// 20 id_bodegaEmpresa
					aux.add(rs6.getString(8)); 								// 21 nombre bodegaEmpresa
					aux.add(nameSucursal); 									// 22 nameSucursal
					aux.add(namePropiedad); 									// 23 namePropiedad
					
					lista.add(aux);
				}
			}
			rs6.close();
			smt6.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static Map<Long,List<String>> mapIdEqVsEnCantBodegas(Connection con, String db) {
		 Map<Long,List<String>> map = new  HashMap<Long,List<String>>();
		try {
			PreparedStatement smt6 = con
					.prepareStatement("select"
							+ " count(id_bodegaEmpresa),"
							+ " id_bodegaEmpresa,"
							+ " id_equipo,"
							+ " nombre,"
							+ " sum(if(id_tipoMovimiento=1,1,-1)*cantidad)"
							+ " from `"+db+"`.movimiento "
							+ " left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa "
							+ " group by id_bodegaEmpresa,id_equipo "
							+ " having sum(if(id_tipoMovimiento=1,1,-1)*cantidad)>0;");
			ResultSet rs6 = smt6.executeQuery();
			while (rs6.next()) {
					String ubicacion = "varias";
					if((long) rs6.getLong(5) == (long)1) {
						ubicacion = rs6.getString(4);
					}
					List<String> aux = new ArrayList<String>();
					aux.add(rs6.getString(1));  							//  0 conteo de bodega con stock
					aux.add(ubicacion);			   							//  1 bodegaEmpresa.nombre	si conteo =0 sino ubicacion varias				
					aux.add(rs6.getString(3));  							//  2 id_equipo
					map.put(rs6.getLong(3), aux);
			}
			rs6.close();
			smt6.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (map);
	}
	
	public static List<List<String>> reportInventarioGeneralXEquipo(Connection con, String db, Equipo equipo, String fechaCorte, String tipo, 
			Map<Long,List<Double>> mapPCompra, Map<Long,List<Double>> mapPLista, Map<Long,String> moneda, Map<String,String> mapeoDiccionario,
			String esPorSucursal, String id_sucursal) {
		List<List<String>> lista = new ArrayList<List<String>>();
		
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and bodegaEmpresa.id_sucursal = " + id_sucursal;
		}
		
		String filtraTipo="";
		if(tipo.equals(mapeoDiccionario.get("ARRIENDO")) || tipo.equals("ARRIENDO")) {
			filtraTipo = " and if(bodegaEmpresa.esInterna=1,0,movimiento.esVenta) = 0 ";
		}else if(tipo.equals("VENTA")) {
			filtraTipo = " and movimiento.esVenta=1 and bodegaEmpresa.esInterna<>1  ";
		}else if(tipo.equals("TODO")){
			filtraTipo="";
		}
		
		Map<Long,Double> tasasCorte = TasasCambio.mapTasasPorFecha(con, db,fechaCorte, mapeoDiccionario.get("pais"));
		Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
		Map<String,List<Double>> mapPBodega = ListaPrecio.mapListaPreciosEquiposPorEquipo(con, db, equipo.getId());
		Map<Long,UnidadTiempo> mapUnidadTiempo = UnidadTiempo.mapUnidadTiempo(con, db);
		Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
		
		Map<Long,Cliente> mapCliente = Cliente.mapAllClientes(con, db);
		Map<Long,TipoBodega> mapTipoBodega = TipoBodega.mapAll(con, db);
		Map<Long,Cotizacion> mapCotizacion = Cotizacion.mapAll(con, db);
		
		String condicionaSuma = "";
		if(tipo.equals(mapeoDiccionario.get("ARRIENDO")) || tipo.equals("ARRIENDO")) {
			condicionaSuma = " if("
					+ "sum(if(movimiento.id_tipoMovimiento=1,1,-1) * movimiento.cantidad) = -0, 0, "
					+ "sum(if(movimiento.id_tipoMovimiento=1,1,-1) * movimiento.cantidad)) as cantidad,";
		}else if(tipo.equals("VENTA")) {
			condicionaSuma = " if("
					+ "sum(if(movimiento.id_tipoMovimiento=1,1,-1) * movimiento.cantidad * if(bodegaEmpresa.esInterna=1, 0, if(movimiento.esVenta=1, 1, 0))) = -0, 0, "
					+ "sum(if(movimiento.id_tipoMovimiento=1,1,-1) * movimiento.cantidad * if(bodegaEmpresa.esInterna=1, 0, if(movimiento.esVenta=1, 1, 0)))) as cantidad,";
		}else if(tipo.equals("TODO")){
			condicionaSuma = "if("
					+ "sum(if(movimiento.id_tipoMovimiento=1,1,-1)*movimiento.cantidad)=-0,0,"
					+ "sum(if(movimiento.id_tipoMovimiento=1,1,-1)*movimiento.cantidad)), ";
		}

		try {
			PreparedStatement smt6 = con
					.prepareStatement(" select " +
							/*1*/" movimiento.id_bodegaEmpresa, " +
							/*2*/" bodegaEmpresa.nombre, " +
							/*3*/condicionaSuma +
							/*4*/" if(guia.id>0,guia.fecha,if(id_factura>0,fecha_factura,fecha_actaBaja)), " +
							/*5*/" bodegaEmpresa.esInterna, " +
							/*6*/" ifnull(movimiento.id_cotizacion,0), "+
							/*7*/" bodegaEmpresa.id_sucursal, " +
							/*8*/" bodegaEmpresa.id_cliente, " +
							/*9*/" movimiento.id_cotizacion " +
							" from `"+db+"`.movimiento " +
							" left join `"+db+"`.guia on guia.id = movimiento.id_guia " +
							" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa " +
							" where (guia.fecha is null or guia.fecha<=?) " +
							" and (fecha_factura is null or fecha_factura<=?) " +
							" and (fecha_actaBaja is null or fecha_actaBaja<=?) " +
							" and movimiento.id_equipo = ? "+
							condSucursal+filtraTipo+
							" group by movimiento.id_bodegaEmpresa,movimiento.id_equipo;");
			smt6.setString(1, fechaCorte.trim());
			smt6.setString(2, fechaCorte.trim());
			smt6.setString(3, fechaCorte.trim());
			smt6.setLong(4, equipo.id);

			ResultSet rs6 = smt6.executeQuery();
			
			while (rs6.next()) {
				if(rs6.getDouble(3)!=0){
					
					String nameSucursal = "";
					Sucursal sucursal = mapSucursal.get(rs6.getLong(7));
					if(sucursal!=null) {
						nameSucursal = sucursal.getNombre();
					}
					
					List<Double> auxMap = mapPCompra.get(equipo.getId());
					Long idMonedaCompra = (long)1;
					Double ultimaCompra = (double)0;
					if(auxMap!=null) {
						idMonedaCompra = auxMap.get(1).longValue();
						ultimaCompra = auxMap.get(0);
					}
					auxMap = mapPLista.get(equipo.getId());
					Long idMonedaVentaArr = (long)1;
					Double precioVenta = (double)0;
					Double precioArriendo = (double)0;
					Long idUnidadTiempo = (long)2;
					String nomUnidadTiempo = mapUnidadTiempo.get(idUnidadTiempo).getNombre();
					
					if(auxMap!=null) {
						idMonedaVentaArr = auxMap.get(3).longValue();
						precioVenta = auxMap.get(0);
						precioArriendo = auxMap.get(1);
						idUnidadTiempo = auxMap.get(2).longValue();
						nomUnidadTiempo = mapUnidadTiempo.get(idUnidadTiempo).getNombre();
					}
					
					String nickMonedaCompra = moneda.get(idMonedaCompra);
					String nickMonedaVentaArr = moneda.get(idMonedaVentaArr);
					
					String nomTipoBod = "";
					Long esInterna = rs6.getLong(5);
					TipoBodega tipoBodega = mapTipoBodega.get(esInterna);
					if(tipoBodega!=null) {
						nomTipoBod = tipoBodega.getNombre();
					}
					
					String id_cotizacion = rs6.getString(6);
						if(esInterna != (long) 1 ){
							idMonedaVentaArr = (long)1;
							precioVenta = (double)0;
							precioArriendo = (double)0;
							List<Double> aux = mapPBodega.get(equipo.getId()+"_"+id_cotizacion);
							if(aux!=null) {
								idMonedaVentaArr = aux.get(2).longValue();
								precioVenta = aux.get(0);
								precioArriendo = aux.get(1);
							}
							
						}
//
//					auxMap = mapPLista.get(equipo.getId());
//					if(auxMap != null) {
//						idMonedaVentaArr = auxMap.get(3).longValue();
//						precioVenta = auxMap.get(0);
//					}
						
					Double tasaCompra = tasasCorte.get(idMonedaCompra);
					if(tasaCompra==null||tasaCompra==0) {
						tasaCompra=(double)1;
					}
					Double tasaVenta = tasasCorte.get(idMonedaVentaArr);
					if(tasaVenta==null||tasaVenta==0) {
						tasaVenta=(double)1;
					}
					
					String nickCliente = "";
					Cliente cliente = mapCliente.get(rs6.getLong(8));
					if(cliente!=null) {
						nickCliente = cliente.getNickName();
					}
					
					String nroCoti = "0";
					Cotizacion coti = mapCotizacion.get(rs6.getLong(9));
					if(coti!=null) {
						nroCoti = coti.getNumero().toString();
					}
					
					
					
					switch(dec.get(idMonedaCompra).toString()) {
						 case "0": myformatdouble = new DecimalFormat("#,##0",symbols); break;
						 case "2": myformatdouble = new DecimalFormat("#,##0.00",symbols); break;
						 case "4": myformatdouble = new DecimalFormat("#,##0.0000",symbols); break;
						 case "6": myformatdouble = new DecimalFormat("#,##0.000000",symbols); break;
						 default:  break;
					}
						
					switch(dec.get(idMonedaVentaArr).toString()) {
						 case "0": myformatdoubleV = new DecimalFormat("#,##0",symbols); break;
						 case "2": myformatdoubleV = new DecimalFormat("#,##0.00",symbols); break;
						 case "4": myformatdoubleV = new DecimalFormat("#,##0.0000",symbols); break;
						 case "6": myformatdoubleV = new DecimalFormat("#,##0.000000",symbols); break;
						 default:  break;
					}
						
					switch(dec.get((long) 1).toString()) {
						 case "0": myformatMonedaOrigen = new DecimalFormat("#,##0",symbols); break;
						 case "2": myformatMonedaOrigen = new DecimalFormat("#,##0.00",symbols); break;
						 case "4": myformatMonedaOrigen = new DecimalFormat("#,##0.0000",symbols); break;
						 case "6": myformatMonedaOrigen = new DecimalFormat("#,##0.000000",symbols); break;
						 default:  break;
					}
	
					List<String> aux = new ArrayList<String>();
					aux.add(equipo.getId().toString());  					//  0 id equipo
					aux.add(nomTipoBod);   									//  1 tipo de cliente					
					aux.add(rs6.getString(2));  							//  2 nombre bodega o empresa
					aux.add(equipo.getGrupo());  							//  3 nombre grupo
					aux.add(equipo.getCodigo());  							//  4 codigo equipo
					aux.add(equipo.getNombre());  							//  5 nombre equipo
					aux.add(nickMonedaCompra);  							//  6 nickname moneda
					aux.add(myformatdouble.format(ultimaCompra));			//  7 precio compra ultima por unidad
					aux.add(nickMonedaVentaArr); 							//  8 nickname moneda de venta - arriendo
					aux.add(myformatdoubleV.format(precioVenta)); 			//  9 precio venta/reposicion por unidad
					aux.add(nomUnidadTiempo);								// 10 unidad de tiempo o medida arriendo
					aux.add(myformatdoubleV.format(precioArriendo)); 		// 11 precio arriendo por unidad
					aux.add(equipo.getUnidad());  							// 12 unidad de medida
					aux.add(myformatdouble2.format(rs6.getDouble(3)));  	// 13 cantidad
					Double total = rs6.getDouble(3)*ultimaCompra;
					aux.add(myformatdouble.format(total));  				// 14 valor total de compra
					Double repos = rs6.getDouble(3)*precioVenta;
					aux.add(myformatdoubleV.format(repos)); 				// 15 valor total de reposicion o venta
					aux.add(myformatMonedaOrigen.format(total*tasaCompra)); // 16 total pesos compra
					aux.add(myformatMonedaOrigen.format(repos*tasaVenta)); 	// 17 total pesos venta
					aux.add(rs6.getString(1));  							// 18 id bodega o empresa
					aux.add(esInterna.toString());  						// 19 esInterna
					aux.add(nickCliente);  									// 20 cliente nickName
					aux.add(id_cotizacion);  								// 21 id cotizacion
					aux.add(nroCoti);  										// 22 numero cotizacion
					aux.add(nameSucursal);  								// 23 nameSucursal
					aux.add(equipo.getPropiedad());  						// 24 namePropiedad
					lista.add(aux);
				}
			}
			rs6.close();
			smt6.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		
		return (lista);
	}
	
	public static List<List<String>> trazaEquipoEnBodega(Connection con, String db, Long id_bodegaEmpresa, Long id_equipo, String tipo, Map<String,String> mapeoDiccionario) {
		List<List<String>> lista = new ArrayList<List<String>>();
		String filtraTipo="";
		if(tipo.equals(mapeoDiccionario.get("ARRIENDO")) || tipo.equals("ARRIENDO")) {
			filtraTipo = " and movimiento.esVenta=0 and movimiento.esVenta=0 ";
		}else if(tipo.equals("VENTA")) {
			filtraTipo = " and movimiento.esVenta=1 and movimiento.id_tipoMovimiento=1 ";
		}else if(tipo.equals("TODO")){
			filtraTipo="";
		}
		try {
			PreparedStatement smt5 = con
					.prepareStatement(" select " +
							" ifnull(guia.numero,ifnull(concat ('compra(',factura.numero,')'),concat('baja(',actaBaja.numero,')'))), " +
							" ifnull(guia.fecha,ifnull(factura.fecha,actaBaja.fecha)),   " +
							" equipo.codigo," +
							" equipo.nombre," +
							" unidad.nombre,     " +
							" movimiento.cantidad*if(id_tipoMovimiento=1,1,-1), " +
							" ifnull(cotizacion.numero,0), "+
							" ifnull(cotizacion.id,0), " +
							" if(movimiento.esVenta=1,'VENTA','"+mapeoDiccionario.get("ARRIENDO")+"'), "+
							" ifnull(guia.numGuiaCliente,'') " +
							" from `"+db+"`.movimiento     " +
							" left join `"+db+"`.equipo on equipo.id=id_equipo " +
							" left join `"+db+"`.guia on guia.id=movimiento.id_guia " +
							" left join `"+db+"`.unidad on unidad.id=equipo.id_unidad   " +
							" left join `"+db+"`.compra on compra.id_equipo=equipo.id  and compra.id = movimiento.id_compra   " +
							" left join `"+db+"`.factura on factura.id=compra.id_factura " +
							" left join `"+db+"`.baja on baja.id=movimiento.id_baja " +
							" left join `"+db+"`.actaBaja on actaBaja.id=baja.id_actaBaja " +
							" left join `"+db+"`.cotizacion on cotizacion.id = movimiento.id_cotizacion " +
							" where movimiento.id_bodegaEmpresa=? "+
									" and movimiento.id_equipo=? "+ filtraTipo+
							" and ifnull(guia.numero,ifnull(concat ('compra(',factura.numero,')'),concat('baja(',actaBaja.numero,')'))) is not null "+
							" order by guia.fecha desc;");
			smt5.setLong(1, id_bodegaEmpresa);
			smt5.setLong(2, id_equipo);
			ResultSet rs5 = smt5.executeQuery();
			while (rs5.next()) {
				List<String> aux = new ArrayList<String>();
				aux.add(rs5.getString(1));
				aux.add(myformatfecha.format(rs5.getDate(2)));
				aux.add(rs5.getString(3));
				aux.add(rs5.getString(4));
				aux.add(rs5.getString(5));
				aux.add(myformatint.format(rs5.getDouble(6)));
				aux.add(rs5.getString(7));		// 6 numero de cotizacion
				aux.add(rs5.getString(8));  	// 7 id cotizacion
				aux.add(rs5.getString(9)); 		//8 tipo
				aux.add(rs5.getString(10)); 	//9 referencia guiaCliente
				lista.add(aux);
			}
			rs5.close();
			smt5.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static File reportInventarioGeneralExcel(String db, List<List<String>> lista, String fechaCorte, Map<String,String> mapDiccionario, String tipo) {
		
		File tmp = null;
try{
	tmp = TempFile.createTempFile("tmp","null");
}catch(Exception e){}
		
		try {
			String path = "formatos/excel.xlsx";
			InputStream formato = Archivos.leerArchivo(path);
            Workbook libro = WorkbookFactory.create(formato);
            formato.close();
            
            // 0 negro 1 blanco 2 rojo 3 verde 4 azul 5 amarillo 
            CellStyle titulo = libro.createCellStyle();
            Font font = libro.createFont();
            font.setBold(true);
            font.setColor((short)4);
            font.setFontHeight((short)(14*20));
            titulo.setFont(font);
            
            CellStyle subtitulo = libro.createCellStyle();
            Font font2 = libro.createFont();
            font2.setBold(true);
            font2.setColor((short)0);
            font2.setFontHeight((short)(12*20));
            subtitulo.setFont(font2);
            
            CellStyle encabezado = libro.createCellStyle();
            encabezado.setBorderBottom(BorderStyle.THIN);
            encabezado.setBorderTop(BorderStyle.THIN);
            encabezado.setBorderRight(BorderStyle.THIN);
            encabezado.setBorderLeft(BorderStyle.THIN);
            encabezado.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            encabezado.setFillForegroundColor((short)19);
            encabezado.setAlignment(HorizontalAlignment.CENTER);
            
            CellStyle detalle = libro.createCellStyle();
            detalle.setBorderBottom(BorderStyle.THIN);
            detalle.setBorderTop(BorderStyle.THIN);
            detalle.setBorderRight(BorderStyle.THIN);
            detalle.setBorderLeft(BorderStyle.THIN);
            
            
            
            
            Sheet hoja1 = libro.getSheetAt(0);
            Row row = null;
            Cell cell = null;
		
            row = hoja1.createRow(1);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellValue("INVENTARIO POR EQUIPO VALORIZADO - "+tipo);
			
			row = hoja1.createRow(2);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("EMPRESA: "+mapDiccionario.get("nEmpresa"));
			
			row = hoja1.createRow(3);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("FECHA: "+Fechas.hoy().getFechaStrDDMMAA());
			
			row = hoja1.createRow(5);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("FECHA DE CORTE: "+Fechas.DDMMAA(fechaCorte));
			
			
			// encabezado de la tabla
			
			int posCell = 0;
			int posColl = 0;
			row = hoja1.createRow(8);
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 7*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("GRUPO");

			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 7*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("PROPIEDAD");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("CODIGO");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 10*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("EQUIPO");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 2*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("MON");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("PU COMPRA");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 2*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("MON");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("PU VENTA");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 2*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("UN "+mapDiccionario.get("ARR"));
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("PU "+mapDiccionario.get("ARRIENDO"));
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 2*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("UN");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("CANTIDAD");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1200);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("TOTAL COMPRA");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1200);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("TOTAL VENTA");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1200);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("COMPRA ("+mapDiccionario.get("PESOS")+")");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1200);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("VENTA ("+mapDiccionario.get("PESOS")+")");
		
			//INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
			InputStream x = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
            byte[] bytes = IOUtils.toByteArray(x);
            x.close();
            int pngIndex = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			Drawing draw = hoja1.createDrawingPatriarch();
			CreationHelper helper = libro.getCreationHelper();
			ClientAnchor anchor = helper.createClientAnchor();
	        //set top-left corner for the image
	        anchor.setCol1(14);
	        anchor.setRow1(1);
			Picture img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);
			
			//DETALLE DE LA TABLA
			int posRow = 9;
			for(int i=0;i<lista.size();i++){
				row = hoja1.createRow(posRow);
				posCell = 0;
				posColl = 0;
				Double aux = (double)0;
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(1));

				posCell++; posColl++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(23));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(2));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(3));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(4));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(lista.get(i).get(5).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(6));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(lista.get(i).get(7).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(8));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(lista.get(i).get(9).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(10));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(lista.get(i).get(11).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(lista.get(i).get(12).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(lista.get(i).get(13).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(lista.get(i).get(14).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(lista.get(i).get(15).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posRow++;
			}
			
			posRow = posRow + 5;
			row = hoja1.createRow(posRow);
			cell = row.createCell(1);
			Hyperlink hiper = helper.createHyperlink(HyperlinkType.URL);
			hiper.setAddress("https://www.inqsol.cl");
			cell.setHyperlink(hiper);
			cell.setCellValue("Documento generado desde MADA propiedad de INQSOL");
			
			// Write the output to a file tmp
			FileOutputStream fileOut = new FileOutputStream(tmp);
			libro.write(fileOut);
			fileOut.close();
			
		} catch (Exception e) {
			e.printStackTrace();
        }
	  return tmp;
	}
	
	public static File reportInvGeneralExcelDesagrupado(String db, List<List<String>> lista, String fechaCorte, Map<String,String> mapDiccionario, String tipo) {
		
		File tmp = null;
try{
	tmp = TempFile.createTempFile("tmp","null");
}catch(Exception e){}
		
		try {
			String path = "formatos/excel.xlsx";
			InputStream formato = Archivos.leerArchivo(path);
            Workbook libro = WorkbookFactory.create(formato);
            formato.close();
            
            // 0 negro 1 blanco 2 rojo 3 verde 4 azul 5 amarillo 
            CellStyle titulo = libro.createCellStyle();
            Font font = libro.createFont();
            font.setBold(true);
            font.setColor((short)4);
            font.setFontHeight((short)(14*20));
            titulo.setFont(font);
            
            CellStyle subtitulo = libro.createCellStyle();
            Font font2 = libro.createFont();
            font2.setBold(true);
            font2.setColor((short)0);
            font2.setFontHeight((short)(12*20));
            subtitulo.setFont(font2);
            
            CellStyle encabezado = libro.createCellStyle();
            encabezado.setBorderBottom(BorderStyle.THIN);
            encabezado.setBorderTop(BorderStyle.THIN);
            encabezado.setBorderRight(BorderStyle.THIN);
            encabezado.setBorderLeft(BorderStyle.THIN);
            encabezado.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            encabezado.setFillForegroundColor((short)19);
            encabezado.setAlignment(HorizontalAlignment.CENTER);
            
            CellStyle detalle = libro.createCellStyle();
            detalle.setBorderBottom(BorderStyle.THIN);
            detalle.setBorderTop(BorderStyle.THIN);
            detalle.setBorderRight(BorderStyle.THIN);
            detalle.setBorderLeft(BorderStyle.THIN);
            
            
            
            
            Sheet hoja1 = libro.getSheetAt(0);
            Row row = null;
            Cell cell = null;
		
            row = hoja1.createRow(1);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellValue("INVENTARIO POR "+mapDiccionario.get("BODEGA")+" Y POR EQUIPO VALORIZADO - "+tipo);
			
			row = hoja1.createRow(2);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("EMPRESA: "+mapDiccionario.get("nEmpresa"));
			
			row = hoja1.createRow(3);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("FECHA: "+Fechas.hoy().getFechaStrDDMMAA());
			
			row = hoja1.createRow(5);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("FECHA DE CORTE: "+Fechas.DDMMAA(fechaCorte));
			
			
			// encabezado de la tabla
			
			int posCell = 0;
			int posColl = 0;
			row = hoja1.createRow(8);

			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 7 * 1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("SUCURSAL");

			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 7 * 1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("BODEGA");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 7*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("GRUPO");

			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 7*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("PPROPIEDAD");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("CODIGO");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 10*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("EQUIPO");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 2*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("MON");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("PU COMPRA");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 2*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("MON");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("PU VENTA");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 2*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("UN "+mapDiccionario.get("ARR"));
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("PU "+mapDiccionario.get("ARRIENDO"));
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 2*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("UN");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("CANTIDAD");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1200);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("TOTAL COMPRA");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1200);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("TOTAL VENTA");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1200);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("COMPRA ("+mapDiccionario.get("PESOS")+")");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1200);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("VENTA ("+mapDiccionario.get("PESOS")+")");
		
			//INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
			InputStream x = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
            byte[] bytes = IOUtils.toByteArray(x);
            x.close();
            int pngIndex = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			Drawing draw = hoja1.createDrawingPatriarch();
			CreationHelper helper = libro.getCreationHelper();
			ClientAnchor anchor = helper.createClientAnchor();
	        //set top-left corner for the image
	        anchor.setCol1(14);
	        anchor.setRow1(1);
			Picture img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);
			
			//DETALLE DE LA TABLA
			int posRow = 9;
			for(int i=0;i<lista.size();i++){
				row = hoja1.createRow(posRow);
				posCell = 0;
				posColl = 0;
				Double aux = (double)0;

				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(22));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(21));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(1));

				posCell++; posColl++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(23));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(2));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(3));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(4));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(lista.get(i).get(5).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(6));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(lista.get(i).get(7).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(8));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(lista.get(i).get(9).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(10));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(lista.get(i).get(11).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(lista.get(i).get(12).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(lista.get(i).get(13).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(lista.get(i).get(14).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(lista.get(i).get(15).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posRow++;
			}
			
			posRow = posRow + 5;
			row = hoja1.createRow(posRow);
			cell = row.createCell(1);
			Hyperlink hiper = helper.createHyperlink(HyperlinkType.URL);
			hiper.setAddress("https://www.inqsol.cl");
			cell.setHyperlink(hiper);
			cell.setCellValue("Documento generado desde MADA propiedad de INQSOL");
			
			// Write the output to a file tmp
			FileOutputStream fileOut = new FileOutputStream(tmp);
			libro.write(fileOut);
			fileOut.close();
			
		} catch (Exception e) {
			e.printStackTrace();
        }
	  return tmp;
	}
	
	public static List<List<String>> reportInventarioGeneralXBodega(Connection con, String db, String fechaCorte, String tipo, 
			Long soloVigente, String permisoPorBodega, Map<String,String> mapeoDiccionario, String esPorSucursal, String id_sucursal) {
		List<List<String>> lista = new ArrayList<List<String>>();
		
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " where bodegaEmpresa.id_sucursal = " + id_sucursal;
		}
		
		String vigente = " or bodegaEmpresa.vigente = 0 ";
		
		String filtraBodegasConArriendo_Venta = "";
		String esInterna = "";
		if(tipo.equals(mapeoDiccionario.get("ARRIENDO")) || tipo.equals("ARRIENDO")) {
//			este if aplica si solo en inventario arriendos deseo no mostrar bodegas internas
					//esInterna = " bodegaEmpresa.esInterna=2 and ";
					esInterna="";
			try {
				PreparedStatement smt = con
						.prepareStatement(" select id_bodegaEmpresa, " + 
								" if(bodegaEmpresa.esInterna=1, 1, if(movimiento.esVenta=0, 1, 0)) as filtro " +
								" from `"+db+"`.movimiento " + 
								" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = id_bodegaEmpresa " + 
								condSucursal +
								" group by id_bodegaEmpresa, esVenta " +
								" having filtro = 1;");
				
				ResultSet rs = smt.executeQuery();
				List<String> aux = new ArrayList<String>();
				while (rs.next()) {
					aux.add(rs.getString(1));
				}
				
				String listIdBodega = aux.toString();
				listIdBodega = listIdBodega.replace("[", "(");
				listIdBodega = listIdBodega.replace("]", ")");
				if(listIdBodega.length()>2) {
					filtraBodegasConArriendo_Venta = " and bodegaEmpresa.id in " + listIdBodega;
				}else {
					filtraBodegasConArriendo_Venta="";
				}
				
				smt.close();
				rs.close();
			} catch (SQLException e) {
					e.printStackTrace();
			}
		}else if(tipo.equals("VENTA")) {
			
			condSucursal = "";
			if(esPorSucursal.equals("1")) {
				condSucursal = " and bodegaEmpresa.id_sucursal = " + id_sucursal;
			}
			
			
			esInterna = " bodegaEmpresa.esInterna=2 and ";
			try {
				PreparedStatement smt = con
						.prepareStatement(" select id_bodegaEmpresa " + 
								" from `"+db+"`.movimiento " + 
								" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = id_bodegaEmpresa " + 
								" where bodegaEmpresa.esInterna <>1 and esVenta=1 " + condSucursal +
								" group by id_bodegaEmpresa, esVenta;");
				
				ResultSet rs = smt.executeQuery();
				List<String> aux = new ArrayList<String>();
				while (rs.next()) {
					aux.add(rs.getString(1));
				}
				String listIdBodega = aux.toString();
				listIdBodega = listIdBodega.replace("[", "(");
				listIdBodega = listIdBodega.replace("]", ")");
				if(listIdBodega.length()>2) {
					filtraBodegasConArriendo_Venta = " and bodegaEmpresa.id in " + listIdBodega;
				}else {
					filtraBodegasConArriendo_Venta="";
				}
				
				smt.close();
				rs.close();
			} catch (SQLException e) {
					e.printStackTrace();
			}
		}else if(tipo.equals("TODO")){
			esInterna = "";
		}
		
		condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and bodegaEmpresa.id_sucursal = " + id_sucursal;
		}
		
		if(soloVigente == 1) vigente = "";
		try {
			PreparedStatement smt5 = con
					.prepareStatement(" select " +
							" bodegaEmpresa.esInterna, " +
							" movimiento.id_bodegaEmpresa, " +
							" ifnull(cliente.id,0), " +
							" ifnull(proyecto.id,0), " +
							" bodegaEmpresa.nombre,   " +
							" ifnull(cliente.rut,''), " +
							" ifnull(cliente.nickName,''), " +
							" ifnull(proyecto.nickName,''), " +
							" ifnull(comunas.nombre,''), " +
							" tipoBodega.nombre, " +
							" ifnull(movimiento.id_cotizacion,0), "+
							" bodegaEmpresa.id_sucursal " +
							" from `"+db+"`.movimiento   " +
							" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa  " +
							" left join `"+db+"`.cliente on cliente.id = bodegaEmpresa.id_cliente " +
							" left join `"+db+"`.proyecto on proyecto.id = bodegaEmpresa.id_proyecto " +
							" left join `"+db+"`.comunas on comunas.codigo = proyecto.cod_comuna " +
							" left join `"+db+"`.tipoBodega on tipoBodega.id = bodegaEmpresa.esInterna " +							
							" left join `"+db+"`.guia on guia.id = movimiento.id_guia " +
							" left join `"+db+"`.compra on compra.id = movimiento.id_compra " +
							" left join `"+db+"`.factura on factura.id = compra.id_factura " +
							" left join `"+db+"`.baja on baja.id = movimiento.id_baja " +
							" left join `"+db+"`.actaBaja on actaBaja.id = baja.id_actaBaja " +
							" where "+esInterna+" (bodegaEmpresa.vigente = 1 " + vigente + ") " + filtraBodegasConArriendo_Venta +
							" and (guia.fecha is null or guia.fecha<=?) " +
							" and (factura.fecha is null or factura.fecha<=?) " +
							" and (actaBaja.fecha is null or actaBaja.fecha<=?) " +
							permisoPorBodega + condSucursal +
							" group by movimiento.id_bodegaEmpresa  " +
							" order by bodegaEmpresa.esInterna,bodegaEmpresa.nombre;");
			smt5.setString(1, fechaCorte.trim());
			smt5.setString(2, fechaCorte.trim());
			smt5.setString(3, fechaCorte.trim());
			
			ResultSet rs5 = smt5.executeQuery();
			
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			while (rs5.next()) {
				String nameSucursal = "";
				Sucursal sucursal = mapSucursal.get(rs5.getLong(12));
				if(sucursal!=null) {
					nameSucursal = sucursal.nombre;
				}
				List<String> aux = new ArrayList<String>();
				aux.add(rs5.getString(1)); // es cliente interno
				aux.add(rs5.getString(2));  // idbodega empresa
				aux.add(rs5.getString(3));  // id de cliente
				aux.add(rs5.getString(4));  // id del proyecto
				aux.add(rs5.getString(10));  // tipo de cliente interno o externo
				aux.add(rs5.getString(5));  // nombre bodega o empresa
				aux.add(rs5.getString(6));  // rut del cliente
				aux.add(rs5.getString(7));  // nombre del cliente
				aux.add(rs5.getString(8));  // nombre del proyecto
				aux.add(rs5.getString(9));  // comuna
				aux.add(nameSucursal);  // 10 nameSucursal
				lista.add(aux);
			}
			rs5.close();
			smt5.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<List<String>> reportInventarioSelectivoXBodega(Connection con, String db, BodegaEmpresa bodega, String fechaCorte, String tipo, Map<String,String> mapeoDiccionario) {
		List<List<String>> lista = new ArrayList<List<String>>();
		Map<Long,Double> tasasCorte = TasasCambio.mapTasasPorFecha(con, db,fechaCorte, mapeoDiccionario.get("pais"));
		Map<Long,List<Double>> mapPCompra = Compra.ultimoPrecio(con, db);
		Map<Long,List<Double>> mapPLista = Precio.maestroPListaPorSucursal(con, db, bodega.getId_sucursal());
		Map<Long,String> moneda = Moneda.mapIdMonedaMoneda(con, db);
		Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
		Long contador = (long) 0;
		Map<Long,String> bloqueo = Movimiento.bloqueoPorBajaPorEmpresa(con, db, bodega.getId());
		Map<String,List<Double>> mapPBodega = ListaPrecio.mapListaPreciosEquiposPorBodega(con, db, bodega.getId());
		
		String filtraTipo="";
		if(tipo.equals(mapeoDiccionario.get("ARRIENDO")) || tipo.equals("ARRIENDO")) {
//			este if aplica si solo en inventario arriendos deseo no mostrar bodegas internas
//			filtraTipo = " and movimiento.esVenta=0 and bodegaEmpresa.esInterna<>1 ";
			filtraTipo = " and if(bodegaEmpresa.esInterna=1,0,movimiento.esVenta) = 0 ";
		}else if(tipo.equals("VENTA")) {
			filtraTipo = " and movimiento.esVenta=1 and bodegaEmpresa.esInterna<>1  ";
		}else if(tipo.equals("TODO")){
			filtraTipo="";
		}
		
		Map<Long,UnidadTiempo> mapUnidadTiempo = UnidadTiempo.mapUnidadTiempo(con, db);
		
		try {
			String agruparPor = ",movimiento.id_cotizacion";
			if(BodegaEmpresa.esInterna(con, db, bodega.getId())) agruparPor="";
			
			PreparedStatement smt6 = con
					.prepareStatement(" select " +
							" movimiento.id_bodegaEmpresa, " +
							" bodegaEmpresa.nombre, " +
							" grupo.id, " +
							" grupo.nombre, " +
							" movimiento.id_equipo, " +
							" equipo.codigo, " +
							" equipo.nombre, " +
							" unidad.nombre, " +
							" if("
							+ "sum(if(movimiento.id_tipoMovimiento=1,1,-1)*movimiento.cantidad)=-0,0,"
							+ "sum(if(movimiento.id_tipoMovimiento=1,1,-1)*movimiento.cantidad)) as cantTotal, " +
							
							" if(guia.id>0,guia.fecha,if(factura.id>0,factura.fecha,actaBaja.fecha)), " +
							" bodegaEmpresa.esInterna, " +
							" max(movimiento.id), " +
							" tipoBodega.nombre,  " +
							" ifnull(movimiento.id_cotizacion,0), "+
							" ifnull(cotizacion.numero,0), " +
							" ifnull(movimiento.id_otDespachado,0), "+
							" equipo.kg, " +
							" equipo.m2, " +
							" equipo.id_propiedad " +
							" from `"+db+"`.movimiento " +
							" left join `"+db+"`.equipo on equipo.id = movimiento.id_equipo " +
							" left join `"+db+"`.grupo on grupo.id = equipo.id_grupo " +
							" left join `"+db+"`.guia on guia.id = movimiento.id_guia " +
							" left join `"+db+"`.unidad on unidad.id = equipo.id_unidad " +
							" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa " +
							" left join `"+db+"`.tipoBodega on tipoBodega.id =  bodegaEmpresa.esInterna " +
							" left join `"+db+"`.compra on compra.id = movimiento.id_compra " +
							" left join `"+db+"`.factura on factura.id = compra.id_factura " +
							" left join `"+db+"`.baja on baja.id = movimiento.id_baja " +
							" left join `"+db+"`.actaBaja on actaBaja.id = baja.id_actaBaja " +
							" left join `"+db+"`.cotizacion on cotizacion.id = movimiento.id_cotizacion " +
							" where (bodegaEmpresa.vigente = 1 or bodegaEmpresa.vigente = 0) " +
							" and (guia.fecha is null or guia.fecha<=?) " +
							" and (factura.fecha is null or factura.fecha<=?) " +
							" and (actaBaja.fecha is null or actaBaja.fecha<=?) " +
							" and bodegaEmpresa.id =? "+ filtraTipo +
							" group by movimiento.id_bodegaEmpresa,movimiento.id_equipo" +agruparPor+
							" having cantTotal <> 0 " +
							" order by equipo.nombre;");
			smt6.setString(1, fechaCorte.trim());
			smt6.setString(2, fechaCorte.trim());
			smt6.setString(3, fechaCorte.trim());
			smt6.setLong(4, bodega.getId());

			ResultSet rs6 = smt6.executeQuery();

			Map<Long,Propiedad> mapPropiedad = Propiedad.mapAll(con, db);

			while (rs6.next()) {
				
				List<Double> auxMap = mapPCompra.get(rs6.getLong(5));
				Long idMonedaCompra = (long)1;
				Double ultimaCompra = (double)0;
				if(auxMap!=null) {
					idMonedaCompra=auxMap.get(1).longValue();
					ultimaCompra = auxMap.get(0);
				}
					
				auxMap = mapPLista.get(rs6.getLong(5));
				Long idMonedaVentaArr = (long)1;
				Double precioVenta = (double)0;
				Double precioArriendo = (double)0;
				Long idUnidadTiempo = (long)2;
				if(auxMap!=null) {
					idMonedaVentaArr=auxMap.get(3).longValue();
					precioVenta=auxMap.get(0);
					precioArriendo=auxMap.get(1);
					idUnidadTiempo=auxMap.get(2).longValue();
				}
				
				String unidadTiempo = "";
				if(mapUnidadTiempo != null && mapUnidadTiempo.get(idUnidadTiempo)!=null) {
					unidadTiempo = mapUnidadTiempo.get(idUnidadTiempo).getNombre();
				}
				
				
					if(rs6.getLong(11)!=1){
						idUnidadTiempo=(long)2;
						try {
							idUnidadTiempo = mapPBodega.get(rs6.getString(5)+"_"+rs6.getString(14)).get(3).longValue();
						}catch(Exception e) {};
						idMonedaVentaArr=(long)1;
						try {
							idMonedaVentaArr = mapPBodega.get(rs6.getString(5)+"_"+rs6.getString(14)).get(2).longValue();
						}catch(Exception e) {};
						precioVenta=(double)0;
						try {
							precioVenta = mapPBodega.get(rs6.getString(5)+"_"+rs6.getString(14)).get(0);
						}catch(Exception e) {};
						precioArriendo=(double)0;
						try {
							precioArriendo = mapPBodega.get(rs6.getString(5)+"_"+rs6.getString(14)).get(1);
						}catch(Exception e) {};
						if(mapUnidadTiempo != null && mapUnidadTiempo.get(idUnidadTiempo)!=null) {
							unidadTiempo = mapUnidadTiempo.get(idUnidadTiempo).getNombre();
						}
					}
				Double tasaCompra = tasasCorte.get(idMonedaCompra);
					if(tasaCompra==null||tasaCompra==0) tasaCompra=(double)1;
				Double tasaVenta = tasasCorte.get(idMonedaVentaArr);
					if(tasaVenta==null||tasaVenta==0) tasaVenta=(double)1;
					switch(dec.get(idMonedaCompra).toString()) {
					 case "0": myformatdouble = new DecimalFormat("#,##0",symbols); break;
					 case "2": myformatdouble = new DecimalFormat("#,##0.00",symbols); break;
					 case "4": myformatdouble = new DecimalFormat("#,##0.0000",symbols); break;
					 case "6": myformatdouble = new DecimalFormat("#,##0.000000",symbols); break;
					 default:  break;
					}
					switch(dec.get(idMonedaVentaArr).toString()) {
					 case "0": myformatdoubleV = new DecimalFormat("#,##0",symbols); break;
					 case "2": myformatdoubleV = new DecimalFormat("#,##0.00",symbols); break;
					 case "4": myformatdoubleV = new DecimalFormat("#,##0.0000",symbols); break;
					 case "6": myformatdoubleV = new DecimalFormat("#,##0.000000",symbols); break;
					 default:  break;
					}
					switch(dec.get((long) 1).toString()) {
					 case "0": myformatMonedaOrigen = new DecimalFormat("#,##0",symbols); break;
					 case "2": myformatMonedaOrigen = new DecimalFormat("#,##0.00",symbols); break;
					 case "4": myformatMonedaOrigen = new DecimalFormat("#,##0.0000",symbols); break;
					 case "6": myformatMonedaOrigen = new DecimalFormat("#,##0.000000",symbols); break;
					 default:  break;
					}
					
					Calendar hoy = Calendar.getInstance();
					Calendar inicio = Calendar.getInstance();
					SimpleDateFormat formatAux = new SimpleDateFormat("yyyy-MM-dd");
					try {hoy.setTime(formatAux.parse(fechaCorte.trim()));} catch (ParseException e) {}
					if(rs6.getDate(10)!=null) inicio.setTime(rs6.getDate(10));
					
					Long diasPermanencia = Math.round(  (double) (hoy.getTimeInMillis() - inicio.getTimeInMillis()) /(24 * 60 * 60 * 1000)  );

					Propiedad propiedad = mapPropiedad.get(rs6.getLong(19));

					List<String> aux = new ArrayList<String>();
					aux.add(rs6.getString(5));  							//  0 id equipo
					aux.add(rs6.getString(13)); 							//  1 tipo de cliente
					aux.add(rs6.getString(2));  							//  2 nombre bodega o empresa
					aux.add(rs6.getString(4));  							//  3 nombre grupo
					aux.add(rs6.getString(6));  							//  4 codigo equipo
					aux.add(rs6.getString(7));  							//  5 nombre equipo
					aux.add(moneda.get(idMonedaCompra));  					//  6 nickname moneda
					aux.add(myformatdouble.format(ultimaCompra)); 			//  7 precio compra ultima por unidad
					
					aux.add(moneda.get(idMonedaVentaArr)); 					//  8 nickname moneda de venta - arriendo
					aux.add(myformatdoubleV.format(precioVenta)); 			//  9 precio venta/reposicion por unidad
					aux.add(unidadTiempo);									// 10 unidad de tiempo o medida arriendo
					aux.add(myformatdoubleV.format(precioArriendo)); 		// 11 precio arriendo por unidad
					
					aux.add(rs6.getString(8));  							// 12 unidad de medida
					aux.add(myformatdouble2.format(rs6.getDouble(9)));  	// 13 cantidad
					Double total = rs6.getDouble(9)*ultimaCompra;
					aux.add(myformatdouble.format(total));  				// 14 valor total de compra
					Double repos = rs6.getDouble(9)*precioVenta;
					aux.add(myformatdoubleV.format(repos)); 				// 15 valor total de reposicion o venta
	
					contador++;
					aux.add(contador.toString()); 							// 16 enumera registros
					String bloq=bloqueo.get(rs6.getLong(12));
					if(bloq==null) bloq="0";
					aux.add(bloq); 											// 17 esta bloqueado?
					
					aux.add(myformatMonedaOrigen.format(total*tasaCompra)); 			// 18 total pesos compra
					aux.add(myformatMonedaOrigen.format(repos*tasaVenta)); 			// 19 total pesos venta
					aux.add(rs6.getString(1));  							// 20 id bodega o empresa
					aux.add(rs6.getString(11));  							// 21 esInterna
					aux.add(diasPermanencia.toString());					// 22 total permanencia
					aux.add(rs6.getString(14));  							// 23 id cotizacion
					aux.add(rs6.getString(15));  							// 24 numero de cotizacion
					aux.add(rs6.getString(16));  							// 25 id otdespachado
					
					Double kg = rs6.getDouble(17);
					Double m2 = rs6.getDouble(18);
					
					aux.add(myformatdouble2.format(kg));  // 26 peso
					aux.add(myformatdouble2.format(m2));  // 27 m2
					
					aux.add(myformatdouble2.format(kg*rs6.getDouble(9)));  // 28 peso por cantidad
					aux.add(myformatdouble2.format(m2*rs6.getDouble(9)));  // 29 m2 por cantidad
					aux.add(propiedad.getNombre());  											//  30 namePropiedad
					
					lista.add(aux);
			}
			rs6.close();
			smt6.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}

	public static List<List<String>> trazaEquipoProyectoEnBodega(Connection con, String db, Long id_bodega, Long id_equipo, Long id_cotizacion, String tipo, Map<String,String> mapeoDiccionario) {
		List<List<String>> lista = new ArrayList<List<String>>();
		String filtraTipo="";
		if(tipo.equals(mapeoDiccionario.get("ARRIENDO")) || tipo.equals("ARRIENDO")) {
			filtraTipo = " and movimiento.esVenta=0 and movimiento.esVenta=0 ";
		}else if(tipo.equals("VENTA")) {
			filtraTipo = " and movimiento.esVenta=1 and movimiento.id_tipoMovimiento=1 ";
		}else if(tipo.equals("TODO")){
			filtraTipo="";
		}
		String cond = " and ifnull(cotizacion.id,0) = '"+id_cotizacion+"' ";
		BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, db, id_bodega);
		if (bodega.esInterna==1) cond="";
		
		try {
			PreparedStatement smt5 = con
					.prepareStatement(" select " +
							" ifnull(guia.numero,ifnull(concat ('compra(',factura.numero,')'),concat('baja(',actaBaja.numero,')'))), " +
							" ifnull(guia.fecha,ifnull(factura.fecha,actaBaja.fecha)),   " +
							" equipo.codigo," +
							" equipo.nombre," +
							" unidad.nombre,     " +
							" movimiento.cantidad*if(id_tipoMovimiento=1,1,-1), " +
							" ifnull(cotizacion.numero,0), "+
							" ifnull(cotizacion.id,0), " +
							" if(movimiento.esVenta=1,'VENTA','"+mapeoDiccionario.get("ARRIENDO")+"'), "+
							" ifnull(guia.numGuiaCliente,'') " +
							" from `"+db+"`.movimiento     " +
							" left join `"+db+"`.equipo on equipo.id=id_equipo " +
							" left join `"+db+"`.guia on guia.id=movimiento.id_guia " +
							" left join `"+db+"`.unidad on unidad.id=equipo.id_unidad   " +
							" left join `"+db+"`.compra on compra.id_equipo=equipo.id  and compra.id = movimiento.id_compra   " +
							" left join `"+db+"`.factura on factura.id=compra.id_factura " +
							" left join `"+db+"`.baja on baja.id=movimiento.id_baja " +
							" left join `"+db+"`.actaBaja on actaBaja.id=baja.id_actaBaja " +
							" left join `"+db+"`.cotizacion on cotizacion.id = movimiento.id_cotizacion " +
							" where movimiento.id_bodegaEmpresa=? "+
									" and movimiento.id_equipo=? "+cond+ filtraTipo +
							" and ifnull(guia.numero,ifnull(concat ('compra(',factura.numero,')'),concat('baja(',actaBaja.numero,')'))) is not null "+
							" order by guia.fecha desc;");
			smt5.setLong(1, id_bodega);
			smt5.setLong(2, id_equipo);
			ResultSet rs5 = smt5.executeQuery();
			while (rs5.next()) {
				List<String> aux = new ArrayList<String>();
				aux.add(rs5.getString(1));
				aux.add(myformatfecha.format(rs5.getDate(2)));
				aux.add(rs5.getString(3));
				aux.add(rs5.getString(4));
				aux.add(rs5.getString(5));
				aux.add(myformatint.format(rs5.getDouble(6)));
				aux.add(rs5.getString(7));
				aux.add(rs5.getString(8));  // 7 id cotizacion
				aux.add(rs5.getString(9)); //8 tipo
				aux.add(rs5.getString(10)); //9 referencia guiaCliente
				lista.add(aux);
			}
			rs5.close();
			smt5.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	

	public static File exportaReportInventarioSelectivoXBodega(String db, List<List<String>> lista, String fechaCorte, Map<String,String> mapDiccionario, BodegaEmpresa bodega, String tipo) {
		
		File tmp = null;
try{
	tmp = TempFile.createTempFile("tmp","null");
}catch(Exception e){}
		
		try {
			String path = "formatos/excel.xlsx";
			InputStream formato = Archivos.leerArchivo(path);
            Workbook libro = WorkbookFactory.create(formato);
            formato.close();
            
            // 0 negro 1 blanco 2 rojo 3 verde 4 azul 5 amarillo 19 celeste
            CellStyle titulo = libro.createCellStyle();
            Font font = libro.createFont();
            font.setBold(true);
            font.setColor((short)4);
            font.setFontHeight((short)(14*20));
            titulo.setFont(font);
            
            CellStyle subtitulo = libro.createCellStyle();
            Font font2 = libro.createFont();
            font2.setBold(true);
            font2.setColor((short)0);
            font2.setFontHeight((short)(12*20));
            subtitulo.setFont(font2);
            
            CellStyle encabezado = libro.createCellStyle();
            encabezado.setBorderBottom(BorderStyle.THIN);
            encabezado.setBorderTop(BorderStyle.THIN);
            encabezado.setBorderRight(BorderStyle.THIN);
            encabezado.setBorderLeft(BorderStyle.THIN);
            encabezado.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            encabezado.setFillForegroundColor((short)19);
            encabezado.setAlignment(HorizontalAlignment.CENTER);
            
            CellStyle detalle = libro.createCellStyle();
            detalle.setBorderBottom(BorderStyle.THIN);
            detalle.setBorderTop(BorderStyle.THIN);
            detalle.setBorderRight(BorderStyle.THIN);
            detalle.setBorderLeft(BorderStyle.THIN);
            
            
            
            
            Sheet hoja1 = libro.getSheetAt(0);
            Row row = null;
            Cell cell = null;
            
            row = hoja1.createRow(1);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellValue("INVENTARIO POR "+mapDiccionario.get("BODEGA")+"/PROYECTO VALORIZADO - "+tipo);
		
            row = hoja1.createRow(2);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellValue(mapDiccionario.get("BODEGA")+"/PROYECTO: "+bodega.getNombre().toUpperCase());
			
			row = hoja1.createRow(3);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("EMPRESA: "+mapDiccionario.get("nEmpresa"));
			
			row = hoja1.createRow(4);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("FECHA: "+Fechas.hoy().getFechaStrDDMMAA());
			
			row = hoja1.createRow(6);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("FECHA DE CORTE: "+Fechas.DDMMAA(fechaCorte));
			
			
			// encabezado de la tabla
			
			int posCell = 0;
			int posColl = 0;
			row = hoja1.createRow(8);
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 7*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("GRUPO");

			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 7*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("PROPIEDAD");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("Nro.COTI");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("CODIGO");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 10*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("EQUIPO");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("KG/UN");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("M2/UN");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 2*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("MON");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("PU COMPRA");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 2*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("MON");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("PU VENTA");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 2*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("UN "+mapDiccionario.get("ARR"));
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("PU "+mapDiccionario.get("ARRIENDO"));
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 2*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("UN");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("CANTIDAD");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1200);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("TOTAL COMPRA");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1200);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("TOTAL VENTA");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1200);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("COMPRA ("+mapDiccionario.get("PESOS")+")");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1200);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("VENTA ("+mapDiccionario.get("PESOS")+")");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("PERMAN");
		
			//INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
			InputStream x = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
            byte[] bytes = IOUtils.toByteArray(x);
            x.close();
            int pngIndex = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			Drawing draw = hoja1.createDrawingPatriarch();
			CreationHelper helper = libro.getCreationHelper();
			ClientAnchor anchor = helper.createClientAnchor();
	        //set top-left corner for the image
	        anchor.setCol1(14);
	        anchor.setRow1(1);
			Picture img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);
			
			//DETALLE DE LA TABLA
			int posRow = 9;
			for(int i=0;i<lista.size();i++){
				row = hoja1.createRow(posRow);
				posCell = 0;
				posColl = 0;
				Double aux = (double)0;
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(3));

				posCell++; posColl++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(30));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(24));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(4));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(5));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(lista.get(i).get(26).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(lista.get(i).get(27).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(6));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(lista.get(i).get(7).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(8));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(lista.get(i).get(9).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(10));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(lista.get(i).get(11).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(12));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(lista.get(i).get(13).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(lista.get(i).get(14).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(lista.get(i).get(15).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(lista.get(i).get(19).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(lista.get(i).get(18).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(lista.get(i).get(22).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posRow++;
			}
			
			
			posRow = posRow + 5;
			row = hoja1.createRow(posRow);
			cell = row.createCell(1);
			Hyperlink hiper = helper.createHyperlink(HyperlinkType.URL);
			hiper.setAddress("https://www.inqsol.cl");
			cell.setHyperlink(hiper);
			cell.setCellValue("Documento generado desde MADA propiedad de INQSOL");
			
			
			// Write the output to a file tmp
			FileOutputStream fileOut = new FileOutputStream(tmp);
			libro.write(fileOut);
			fileOut.close();
			
		} catch (Exception e) {
			e.printStackTrace();
        }
	  return tmp;
	}
	
	public static List<List<String>> reportInventarioSelectivoXGrupo(Connection con, String db, Long id_grupo, String fechaCorte, 
			String permisoPorBodega, String tipo, Map<String,String> mapeoDiccionario, String esPorSucursal, String id_sucursal) {
		List<List<String>> lista = new ArrayList<List<String>>();
		
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and bodegaEmpresa.id_sucursal = " + id_sucursal;
		}
		
		Map<Long,Double> tasasCorte = TasasCambio.mapTasasPorFecha(con, db,fechaCorte, mapeoDiccionario.get("pais"));
		Map<Long,List<Double>> mapPCompra = Compra.ultimoPrecio(con, db);
		Map<Long,String> moneda = Moneda.mapIdMonedaMoneda(con, db);
		Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
		
		String filtraTipo="";
		if(tipo.equals(mapeoDiccionario.get("ARRIENDO")) || tipo.equals("ARRIENDO")) {
			filtraTipo = " and movimiento.esVenta=0 and bodegaEmpresa.esInterna<>1 ";
		}else if(tipo.equals("VENTA")) {
			filtraTipo = " and movimiento.esVenta=1 and bodegaEmpresa.esInterna<>1  ";
		}else if(tipo.equals("TODO")){
			filtraTipo="";
		}
		
		try {
			PreparedStatement smt6 = con
					.prepareStatement(" select " +
							" grupo.id, " +
							" grupo.nombre, " +
							" movimiento.id_equipo, " +
							" equipo.codigo, " +
							" equipo.nombre, " +
							" unidad.nombre, " +
							" if("
							+ "sum(if(movimiento.id_tipoMovimiento=1,1,-1)*movimiento.cantidad)=-0,0,"
							+ "sum(if(movimiento.id_tipoMovimiento=1,1,-1)*movimiento.cantidad)), " +
							
							" if(guia.id>0,guia.fecha,if(factura.id>0,factura.fecha,actaBaja.fecha)), " +
							" movimiento.id_equipo, " +
							" equipo.id_propiedad " +
							" from `"+db+"`.movimiento " +
							" left join `"+db+"`.equipo on equipo.id = movimiento.id_equipo " +
							" left join `"+db+"`.grupo on grupo.id = equipo.id_grupo " +
							" left join `"+db+"`.guia on guia.id = movimiento.id_guia " +
							" left join `"+db+"`.unidad on unidad.id = equipo.id_unidad " +
							" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa " +
							" left join `"+db+"`.tipoBodega on tipoBodega.id = bodegaEmpresa.esInterna " +
							" left join `"+db+"`.compra on compra.id = movimiento.id_compra " +
							" left join `"+db+"`.factura on factura.id = compra.id_factura " +
							" left join `"+db+"`.baja on baja.id = movimiento.id_baja " +
							" left join `"+db+"`.actaBaja on actaBaja.id = baja.id_actaBaja " +
							" where (bodegaEmpresa.vigente = 1 or bodegaEmpresa.vigente = 0) " +
							" and (guia.fecha is null or guia.fecha<=?) " +
							" and (factura.fecha is null or factura.fecha<=?) " +
							" and (actaBaja.fecha is null or actaBaja.fecha<=?) " +
							" and grupo.id = ? " + permisoPorBodega + filtraTipo + condSucursal +
							" group by movimiento.id_equipo;");
			smt6.setString(1, fechaCorte.trim());
			smt6.setString(2, fechaCorte.trim());
			smt6.setString(3, fechaCorte.trim());
			smt6.setLong(4, id_grupo);
			ResultSet rs6 = smt6.executeQuery();

			Map<Long,Propiedad> mapPropiedad = Propiedad.mapAll(con, db);

			while (rs6.next()) {
				if(rs6.getDouble(7) != (double) 0){
					
					List<Double> auxMap = mapPCompra.get(rs6.getLong(3));
					Long idMonedaCompra = (long)1;
					Double ultimaCompra = (double)0;
					if(auxMap!=null) {
						idMonedaCompra=auxMap.get(1).longValue();
						ultimaCompra = auxMap.get(0);
					}
					
					Double tasaCompra = tasasCorte.get(idMonedaCompra);
						if(tasaCompra==null||tasaCompra==0) tasaCompra=(double)1;
						
						switch(dec.get(idMonedaCompra).toString()) {
						 case "0": myformatdouble = new DecimalFormat("#,##0",symbols); break;
						 case "2": myformatdouble = new DecimalFormat("#,##0.00",symbols); break;
						 case "4": myformatdouble = new DecimalFormat("#,##0.0000",symbols); break;
						 case "6": myformatdouble = new DecimalFormat("#,##0.000000",symbols); break;
						 default:  break;
						}
						switch(dec.get((long) 1).toString()) {
						 case "0": myformatMonedaOrigen = new DecimalFormat("#,##0",symbols); break;
						 case "2": myformatMonedaOrigen = new DecimalFormat("#,##0.00",symbols); break;
						 case "4": myformatMonedaOrigen = new DecimalFormat("#,##0.0000",symbols); break;
						 case "6": myformatMonedaOrigen = new DecimalFormat("#,##0.000000",symbols); break;
						 default:  break;
						}

						Propiedad propiedad = mapPropiedad.get(rs6.getLong(10));

					List<String> aux = new ArrayList<String>();
					aux.add(rs6.getString(3)); 							//  0 id equipo
					aux.add(rs6.getString(2)); 							//  1 nombre grupo
					aux.add(rs6.getString(4)); 							//  2 codigo equipo
					aux.add(rs6.getString(5)); 							//  3 nombre equipo
					aux.add(moneda.get(idMonedaCompra)); 				//  4 nickname moneda
					aux.add(myformatdouble.format(ultimaCompra)); 		//  5 precio compra ultima por unidad
					aux.add(rs6.getString(6)); 							//  6 unidad de medida
					aux.add(myformatdouble2.format(rs6.getDouble(7))); 	//  7 cantidad
					Double total = rs6.getDouble(7)*ultimaCompra;
					aux.add(myformatdouble.format(total));  			//  8 valor total de compra
					aux.add(myformatMonedaOrigen.format(total*tasaCompra)); 		// 9 total pesos compra
					aux.add(propiedad.getNombre()); 		// 10 namePropiedad
					lista.add(aux);
				}
			}
			rs6.close();
			smt6.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static File exportaReportInventarioSelectivoXGrupo(String db,List<List<String>> lista, String fechaCorte,Map<String,String> mapDiccionario, Grupo grupo, String tipo) {
		
		File tmp = null;
try{
	tmp = TempFile.createTempFile("tmp","null");
}catch(Exception e){}
		
		try {
			String path = "formatos/excel.xlsx";
			InputStream formato = Archivos.leerArchivo(path);
            Workbook libro = WorkbookFactory.create(formato);
            formato.close();
            
            // 0 negro 1 blanco 2 rojo 3 verde 4 azul 5 amarillo 19 celeste
            CellStyle titulo = libro.createCellStyle();
            Font font = libro.createFont();
            font.setBold(true);
            font.setColor((short)4);
            font.setFontHeight((short)(14*20));
            titulo.setFont(font);
            
            CellStyle subtitulo = libro.createCellStyle();
            Font font2 = libro.createFont();
            font2.setBold(true);
            font2.setColor((short)0);
            font2.setFontHeight((short)(12*20));
            subtitulo.setFont(font2);
            
            CellStyle encabezado = libro.createCellStyle();
            encabezado.setBorderBottom(BorderStyle.THIN);
            encabezado.setBorderTop(BorderStyle.THIN);
            encabezado.setBorderRight(BorderStyle.THIN);
            encabezado.setBorderLeft(BorderStyle.THIN);
            encabezado.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            encabezado.setFillForegroundColor((short)19);
            encabezado.setAlignment(HorizontalAlignment.CENTER);
            
            CellStyle detalle = libro.createCellStyle();
            detalle.setBorderBottom(BorderStyle.THIN);
            detalle.setBorderTop(BorderStyle.THIN);
            detalle.setBorderRight(BorderStyle.THIN);
            detalle.setBorderLeft(BorderStyle.THIN);
            
            Sheet hoja1 = libro.getSheetAt(0);
            Row row = null;
            Cell cell = null;
            
            row = hoja1.createRow(1);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellValue("INVENTARIO POR GRUPO - "+tipo);
		
            row = hoja1.createRow(2);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellValue("GRUPO: "+grupo.getNombre().toUpperCase());
			
			row = hoja1.createRow(3);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("EMPRESA: "+mapDiccionario.get("nEmpresa"));
			
			row = hoja1.createRow(4);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("FECHA: "+Fechas.hoy().getFechaStrDDMMAA());
			
			row = hoja1.createRow(6);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("FECHA DE CORTE: "+Fechas.DDMMAA(fechaCorte));
			
			
			// encabezado de la tabla
			
			int posCell = 0;
			int posColl = 0;
			row = hoja1.createRow(8);
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("PROPIEDAD");

			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("CODIGO");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 10*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("EQUIPO");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 2*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("MON");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("PU COMPRA");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 2*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("UN");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("CANTIDAD");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1200);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("TOTAL COMPRA");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1200);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("COMPRA ("+mapDiccionario.get("PESOS")+")");
		
			//INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
			InputStream x = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
            byte[] bytes = IOUtils.toByteArray(x);
            x.close();
            int pngIndex = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			Drawing draw = hoja1.createDrawingPatriarch();
			CreationHelper helper = libro.getCreationHelper();
			ClientAnchor anchor = helper.createClientAnchor();
	        //set top-left corner for the image
	        anchor.setCol1(6);
	        anchor.setRow1(1);
			Picture img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);
			
			//DETALLE DE LA TABLA
			int posRow = 9;
			for(int i=0;i<lista.size();i++){
				row = hoja1.createRow(posRow);
				posCell = 0;
				posColl = 0;
				Double aux = (double)0;
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(10));

				posCell++; posColl++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(2));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(3));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(4));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(lista.get(i).get(5).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(6));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(lista.get(i).get(7).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(lista.get(i).get(8).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(lista.get(i).get(9).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posRow++;
			}
			
			posRow = posRow + 5;
			row = hoja1.createRow(posRow);
			cell = row.createCell(1);
			Hyperlink hiper = helper.createHyperlink(HyperlinkType.URL);
			hiper.setAddress("https://www.inqsol.cl");
			cell.setHyperlink(hiper);
			cell.setCellValue("Documento generado desde MADA propiedad de INQSOL");
			
			
			// Write the output to a file tmp
			FileOutputStream fileOut = new FileOutputStream(tmp);
			libro.write(fileOut);
			fileOut.close();
			
		} catch (Exception e) {
			e.printStackTrace();
        }
	  return tmp;
	  
	}
	
	public static List<String> listaMatrizEquiposTitulos1(List<List<String>> listaBodegas, String tipo, Map<String,String> mapeoDiccionario) {
		List<String> titulos1 = new ArrayList<String>();
		int flag=0;
		for(int j=0;j<listaBodegas.size();j++) {
			if(listaBodegas.get(j).get(2).equals("1")){
				flag=j;
			}
		}
		// este if aplica si solo en inventario arriendos deseo no mostrar bodegas internas
				//if(tipo.equals("VENTA") || tipo.equals("ARRIENDO") || tipo.equals(mapeoDiccionario.get("ARRIENDO"))) {
				if(tipo.equals("VENTA")) {
			titulos1.add("<TH colspan='"+(listaBodegas.size()-flag)+"' style='text-align:left;'>CLIENTES</TH>");
		}else {
			titulos1.add("<TH colspan='"+(flag+1)+"' style='text-align:left;'>INTERNAS</TH>");
			titulos1.add("<TH colspan='"+(listaBodegas.size()-flag-1)+"' style='text-align:left;'>CLIENTES</TH>");
		}
		
		
		return(titulos1);
	}
	
	public static List<String> listaMatrizEquiposTitulos2(List<List<String>> listaBodegas) {
		List<String> titulos2 = new ArrayList<String>();
		for(int j=0;j<listaBodegas.size();j++) {
			titulos2.add(listaBodegas.get(j).get(1));
		}
		return(titulos2);
	}
	
	public static List<String> listaMatrizEquiposTitulos3(List<List<String>> listaBodegas) {
		List<String> titulos3 = new ArrayList<String>();
		for(int j=0;j<listaBodegas.size();j++) {
			titulos3.add(listaBodegas.get(j).get(2));
		}
		return(titulos3);
	}
	
	public static List<List<String>> listaMatrizEquipos(Connection con, String db, String permisoPorBodega, String tipo, String fechaCorte, List<List<String>> listaBodegas, Map<String,String> mapeoDiccionario) {
		List<List<String>> lista = new ArrayList<List<String>>();
		Map<String,Double> mapCantidades = new HashMap<String,Double>();
		String condicionaSuma = "";
		String interna = "";
		if(tipo.equals(mapeoDiccionario.get("ARRIENDO")) || tipo.equals("ARRIENDO")) {
			condicionaSuma = " if("
					+ "sum(if(movimiento.id_tipoMovimiento=1,1,-1) * movimiento.cantidad  * if(movimiento.esVenta=0, 1, 0)) = -0, 0, "
					+ "sum(if(movimiento.id_tipoMovimiento=1,1,-1) * movimiento.cantidad  * if(movimiento.esVenta=0, 1, 0))) as cantidad";
		}else if(tipo.equals("VENTA")) {
			condicionaSuma = " if("
					+ "sum(if(movimiento.id_tipoMovimiento=1,1,-1) * movimiento.cantidad * if(bodegaEmpresa.esInterna=1, 0, if(movimiento.esVenta=1, 1, 0))) = -0, 0, "
					+ "sum(if(movimiento.id_tipoMovimiento=1,1,-1) * movimiento.cantidad * if(bodegaEmpresa.esInterna=1 ,0, if(movimiento.esVenta=1, 1, 0)))) as cantidad";
			interna = " and esInterna<>1 ";
		}else if(tipo.equals("TODO")){
			condicionaSuma = "if("
					+ "sum(if(movimiento.id_tipoMovimiento=1,1,-1)*movimiento.cantidad)=-0,0,"
					+ "sum(if(movimiento.id_tipoMovimiento=1,1,-1)*movimiento.cantidad)) ";
		}
		
		List<List<String>> listaEquipos = Inventarios.listaEquiposConStock(con, db, permisoPorBodega, fechaCorte);
		try {
			PreparedStatement smt21 = con.prepareStatement(" select id from `"+db+"`.bodegaEmpresa where id>0 "+interna+";");
			String listaCond2 ="";
			ResultSet rs21 = smt21.executeQuery();
			while (rs21.next()) {
				listaCond2 = listaCond2 + rs21.getString(1) + ",";
			}
			rs21.close();
			smt21.close();
			
			if(listaCond2.length()>1) {
				listaCond2 = listaCond2.substring(0,listaCond2.length()-1);
				
				PreparedStatement smt2 = con
						.prepareStatement(" select " + 
								" concat(bodegaEmpresa.id,'_',equipo.id), " + 
								condicionaSuma + 
								" from `"+db+"`.movimiento " +
								" left join `"+db+"`.equipo on equipo.id=movimiento.id_equipo " + 
								" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id=movimiento.id_bodegaEmpresa " + 
								" left join `"+db+"`.guia on guia.id = movimiento.id_guia " + 
								" left join `"+db+"`.compra on compra.id = movimiento.id_compra " + 
								" left join `"+db+"`.factura on factura.id = compra.id_factura " + 
								" left join `"+db+"`.baja on baja.id = movimiento.id_baja " + 
								" left join `"+db+"`.actaBaja on actaBaja.id = baja.id_actaBaja " +
								" where movimiento.id_bodegaEmpresa in ("+listaCond2+") " +
								" and (guia.fecha <=? or guia.fecha is null) and (factura.fecha <=? or factura.fecha is null)  and (actaBaja.fecha <=? or actaBaja.fecha is null) "+
								" group by bodegaEmpresa.id,equipo.id " + 
								" having if(sum(if(movimiento.id_tipoMovimiento=2,movimiento.cantidad*-1,movimiento.cantidad))=-0,0,sum(if(movimiento.id_tipoMovimiento=2,movimiento.cantidad*-1,movimiento.cantidad)))>0;");
				smt2.setString(1, fechaCorte);
				smt2.setString(2, fechaCorte);
				smt2.setString(3, fechaCorte);
				ResultSet rs2 = smt2.executeQuery();
				while (rs2.next()) {
					mapCantidades.put(rs2.getString(1), rs2.getDouble(2));
				}
				rs2.close();smt2.close();
				
				PreparedStatement smt22 = con
						.prepareStatement(" select " + 
								" concat(bodegaEmpresa.id,'_',equipo.id), " + 
								" if("
								+ "sum(if(movimiento.id_tipoMovimiento=1,1,-1)*movimiento.cantidad)=-0,0,"
								+ "sum(if(movimiento.id_tipoMovimiento=1,1,-1)*movimiento.cantidad))  as CANTIDAD " +
								" from `"+db+"`.movimiento " + 
								" left join `"+db+"`.equipo on equipo.id=movimiento.id_equipo " + 
								" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id=movimiento.id_bodegaEmpresa " + 
								" left join `"+db+"`.guia on guia.id = movimiento.id_guia " + 
								" left join `"+db+"`.compra on compra.id = movimiento.id_compra " + 
								" left join `"+db+"`.factura on factura.id = compra.id_factura " + 
								" left join `"+db+"`.baja on baja.id = movimiento.id_baja " + 
								" left join `"+db+"`.actaBaja on actaBaja.id = baja.id_actaBaja " +
								" where movimiento.id_bodegaEmpresa in ("+listaCond2+") and bodegaEmpresa.esInterna=1 " +
								" and (guia.fecha <=? or guia.fecha is null) and (factura.fecha <=? or factura.fecha is null)  and (actaBaja.fecha <=? or actaBaja.fecha is null) "+
								" group by bodegaEmpresa.id,equipo.id " + 
								" having if(sum(if(movimiento.id_tipoMovimiento=2,movimiento.cantidad*-1,movimiento.cantidad))=-0,0,sum(if(movimiento.id_tipoMovimiento=2,movimiento.cantidad*-1,movimiento.cantidad)))>0;");
				smt22.setString(1, fechaCorte);
				smt22.setString(2, fechaCorte);
				smt22.setString(3, fechaCorte);
				ResultSet rs22 = smt22.executeQuery();
				while (rs22.next()) {
					mapCantidades.put(rs22.getString(1), rs22.getDouble(2));
				}
				rs22.close();smt22.close();
			}
				Map<Long,Propiedad> mapPropiedad = Propiedad.mapAll(con, db);

			for(int i=0;i<listaEquipos.size();i++) {
				List<String> datos = new ArrayList<String>();
				datos.add(listaEquipos.get(i).get(4));

				Propiedad prop = mapPropiedad.get(Long.parseLong(listaEquipos.get(i).get(7)));
				datos.add(prop.getNombre());

				datos.add(listaEquipos.get(i).get(1));
				datos.add(listaEquipos.get(i).get(2));
				
				Double kg = Double.parseDouble(listaEquipos.get(i).get(5));
				Double m2 = Double.parseDouble(listaEquipos.get(i).get(6));
				
				if(kg > 0) {
					datos.add(myformatdouble2.format(kg)); 
				}else {
					datos.add(""); 
				}
				
				if(m2 > 0) {
					datos.add(myformatdouble2.format(m2));
				}else {
					datos.add("");
				}
				
				Double cantTot = (double)0;
				for(int j=0;j<listaBodegas.size();j++) {
					Double cantAux = mapCantidades.get(listaBodegas.get(j).get(0)+"_"+listaEquipos.get(i).get(0));
					if(cantAux==null) cantAux=(double)0;
					cantTot=cantTot+cantAux;
				}
				datos.add(myformatint.format(cantTot));
				
				for(int j=0;j<listaBodegas.size();j++) {
					Double cantAux = mapCantidades.get(listaBodegas.get(j).get(0)+"_"+listaEquipos.get(i).get(0));
					if(cantAux==null) cantAux=(double)0;
					datos.add(myformatint.format(cantAux));
				}
				datos.add(myformatint.format(cantTot));


				
				if(cantTot!=0) {
					lista.add(datos);
				}
				
			}
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static File exportaReportInventarioMatriz(String db, List<List<String>> lista, List<String> titulos3, List<String> titulos2, String tipo, String fechaCorte, Map<String,String> mapDiccionario,
			String nameSucursal) {
		
		File tmp = null;
try{
	tmp = TempFile.createTempFile("tmp","null");
}catch(Exception e){}
		
		try {
			String path = "formatos/excel.xlsx";
			InputStream formato = Archivos.leerArchivo(path);
            Workbook libro = WorkbookFactory.create(formato);
            formato.close();
            
            // 0 negro 1 blanco 2 rojo 3 verde 4 azul 5 amarillo 19 celeste
            CellStyle titulo = libro.createCellStyle();
            Font font = libro.createFont();
            font.setBold(true);
            font.setColor((short)4);
            font.setFontHeight((short)(14*20));
            titulo.setFont(font);
            
            CellStyle subtitulo = libro.createCellStyle();
            Font font2 = libro.createFont();
            font2.setBold(true);
            font2.setColor((short)0);
            font2.setFontHeight((short)(12*20));
            subtitulo.setFont(font2);
            
            CellStyle encabezado = libro.createCellStyle();
            encabezado.setBorderBottom(BorderStyle.THIN);
            encabezado.setBorderTop(BorderStyle.THIN);
            encabezado.setBorderRight(BorderStyle.THIN);
            encabezado.setBorderLeft(BorderStyle.THIN);
            encabezado.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            encabezado.setFillForegroundColor((short)19);
            encabezado.setAlignment(HorizontalAlignment.CENTER);
            
            CellStyle detalle = libro.createCellStyle();
            detalle.setBorderBottom(BorderStyle.THIN);
            detalle.setBorderTop(BorderStyle.THIN);
            detalle.setBorderRight(BorderStyle.THIN);
            detalle.setBorderLeft(BorderStyle.THIN);
            
            CellStyle rotateText =  libro.createCellStyle();
			rotateText.setRotation((short)90);
			rotateText.setBorderBottom(BorderStyle.THIN);
			rotateText.setBorderTop(BorderStyle.THIN);
			rotateText.setBorderRight(BorderStyle.THIN);
			rotateText.setBorderLeft(BorderStyle.THIN);
            rotateText.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            rotateText.setFillForegroundColor((short)19);
            rotateText.setAlignment(HorizontalAlignment.CENTER);
            
            
            Sheet hoja1 = libro.getSheetAt(0);
            Row row = null;
            Cell cell = null;
            
            //ARRIENDO: MATRIZ DE INVENTARIO POR TODOS LOS EQUIPOS, BODEGAS Y CLIENTES  (INCLUYE EQUIPOS SIN STOCK)
            
            
            
            row = hoja1.createRow(1);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellValue(tipo+": MATRIZ DE INVENTARIO POR TODOS LOS EQUIPOS, "+mapDiccionario.get("BODEGA")+"/PROYECTO Y CLIENTES  (SOLO EQUIPOS CON STOCK)");
			
			row = hoja1.createRow(3);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("EMPRESA: "+mapDiccionario.get("nEmpresa"));
			
			row = hoja1.createRow(4);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("FECHA: "+Fechas.hoy().getFechaStrDDMMAA());
			
			row = hoja1.createRow(6);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("FECHA DE CORTE: "+Fechas.DDMMAA(fechaCorte));
			
            if(!nameSucursal.equals("0")) {
            	cell = row.createCell(3);
                cell.setCellStyle(subtitulo);
    			cell.setCellValue("SUCURSAL: "+nameSucursal.toString());
            }
			
			
			cell = row.createCell(7);
            cell.setCellStyle(titulo);
			cell.setCellValue("1 = " + mapDiccionario.get("BODEGA") + " internas");
			
            cell = row.createCell(15);
            cell.setCellStyle(titulo);
			cell.setCellValue("2 = PROYECTOS (corresponde a clientes)");
			
			
			// encabezado de la tabla
			
			//TITULOS 2
			
			int posCell = 0;
			int posColl = 0;
			row = hoja1.createRow(8);
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("GRUPO");

			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("PROPIEDAD");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("CODIGO");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 10*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("EQUIPO");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 2*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("KG");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 2*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("M2");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 2*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("TOTAL");
			
			for(int i=0;i<titulos2.size();i++){
				posCell++; posColl++;
				hoja1.setColumnWidth(posColl, 1500);
	            cell = row.createCell(posCell);
	            cell.setCellStyle(rotateText);
				cell.setCellValue(titulos2.get(i));
			}
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("TOTAL");
			
			//TITULOS 3
			
			posCell = 0;
			posColl = 0;
			row = hoja1.createRow(9);
			
			for(int i=0; i<7; i++) {
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("");
			}
			
			for(int i=0;i<titulos3.size();i++){
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
	            Long aux = Long.parseLong(titulos3.get(i).trim());
				cell.setCellValue(aux);
			}
			
			posCell++; posColl++;
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("");
		
			//INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
			InputStream x = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
            byte[] bytes = IOUtils.toByteArray(x);
            x.close();
            int pngIndex = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			Drawing draw = hoja1.createDrawingPatriarch();
			CreationHelper helper = libro.getCreationHelper();
			ClientAnchor anchor = helper.createClientAnchor();
	        //set top-left corner for the image
	        anchor.setCol1(30);
	        anchor.setRow1(1);
			Picture img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);
			
			
			
			//DETALLE DE LA TABLA
			int posRow = 10;
			for(int i=0;i<lista.size();i++){
				row = hoja1.createRow(posRow);
				posCell = 0;
				posColl = 0;
				Double aux = (double)0;
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(0));

				posCell++; posColl++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(1));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(2));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(3));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            if(!lista.get(i).get(4).equals("")) {
	            	aux = Double.parseDouble(lista.get(i).get(4).replaceAll(",", ""));
					cell.setCellValue(aux);
				}
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            if(!lista.get(i).get(5).equals("")) {
	            	aux = Double.parseDouble(lista.get(i).get(5).replaceAll(",", ""));
					cell.setCellValue(aux);
				}
				
				for(int j=6;j<lista.get(i).size();j++) {
					posCell++; posColl++;
		            cell = row.createCell(posCell);
		            cell.setCellStyle(detalle);
		            if(lista.get(i).get(j) != null) {
		            	try {
		            		aux = Double.parseDouble(lista.get(i).get(j).replaceAll(",", ""));
		            	}catch(Exception e) {
		            		aux = (double)0;
		            	}
		            	
		            }else {
		            	aux = (double)0;
		            }
					cell.setCellValue(aux);
				}
				
				posRow++;
			}
			
			
			posRow = posRow + 5;
			row = hoja1.createRow(posRow);
			cell = row.createCell(1);
			Hyperlink hiper = helper.createHyperlink(HyperlinkType.URL);
			hiper.setAddress("https://www.inqsol.cl");
			cell.setHyperlink(hiper);
			cell.setCellValue("Documento generado desde MADA propiedad de INQSOL");
			
			
			// Write the output to a file tmp
			FileOutputStream fileOut = new FileOutputStream(tmp);
			libro.write(fileOut);
			fileOut.close();
			
		} catch (Exception e) {
			e.printStackTrace();
        }
	  return tmp;
	}
	
	public static List<List<String>> listaFullEquipos(Connection con, String db) {
		List<List<String>> lista = new ArrayList<List<String>>();
		List<List<String>> listaEquipos = new ArrayList<List<String>>();
		Map<String,List<String>> mapListaCompras = new HashMap<String,List<String>>();
		Map<String,List<String>> mapListaPrecios = new HashMap<String,List<String>>();
		Map<String,Double> mapBajas = new HashMap<String,Double>();
		Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
		try {
			PreparedStatement smt = con
					.prepareStatement(" select"
							+ " equipo.id,"
							+ " equipo.codigo,"
							+ " equipo.nombre,"
							+ " equipo.id_grupo,"
							+ " grupo.nombre, "
							+ " equipo.kg, "
							+ " equipo.m2 " + 
							" from `"+db+"`.equipo left join `"+db+"`.grupo on grupo.id=equipo.id_grupo;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				List<String> aux = new ArrayList<String>();
				aux.add(rs.getString(1)); // idEquipo
				aux.add(rs.getString(2));  // codigo
				aux.add(rs.getString(3));  // nombre equipo
				aux.add(rs.getString(4));  // idGrupo
				aux.add(rs.getString(5));  // nombre grupo o familia
				aux.add(rs.getString(6)); // kg
				aux.add(rs.getString(7)); // m2
				listaEquipos.add(aux);
			}
			smt.close();
			rs.close();
			
			PreparedStatement smt1 = con
					.prepareStatement("select baja.id_equipo,ifnull(sum(baja.cantidad),0) " + 
							" from `"+db+"`.baja group by baja.id_equipo;");
			ResultSet rs1 = smt1.executeQuery();
			while (rs1.next()) {
				mapBajas.put(rs1.getString(1),rs1.getDouble(2));
			}
			rs1.close();
			smt1.close();
			
			PreparedStatement smt2 = con
					.prepareStatement(" select distinct equipo.id, unidad.nombre, sum(if(id_tipoMovimiento=1,1,-1)*cantidad) from `"+db+"`.movimiento " + 
							" left join `"+db+"`.equipo on equipo.id = movimiento.id_equipo " + 
							" left join `"+db+"`.unidad on unidad.id = equipo.id_unidad " + 
							" group by movimiento.id_equipo;");
			ResultSet rs2 = smt2.executeQuery();
			
			
			Map<Long,List<Double>> mapPCompra = Compra.ultimoPrecio(con, db);
			Map<Long,String> mapMoneda = Moneda.mapIdMonedaMoneda(con, db);
			
			while (rs2.next()) {
				List<String> aux = new ArrayList<String>();
				List<Double> auxMap = mapPCompra.get(rs2.getLong(1));
				Long idMonedaCompra = (long)1;
				Double ultimaCompra = (double)0;
				if(auxMap!=null) {
					idMonedaCompra = auxMap.get(1).longValue();
					ultimaCompra = auxMap.get(0);
				}
				
				switch(dec.get(idMonedaCompra).toString()) {
				 case "0": myformatdouble = new DecimalFormat("#,##0",symbols); break;
				 case "2": myformatdouble = new DecimalFormat("#,##0.00",symbols); break;
				 case "4": myformatdouble = new DecimalFormat("#,##0.0000",symbols); break;
				 case "6": myformatdouble = new DecimalFormat("#,##0.000000",symbols); break;
				 default:  break;
				}
				
				aux.add(rs2.getString(2));  // unidad
				aux.add(myformatdouble2.format(rs2.getDouble(3)));  // cant
				aux.add(mapMoneda.get(idMonedaCompra));  // moneda
				aux.add(myformatdouble.format(ultimaCompra));  // precioUnitario
				mapListaCompras.put(rs2.getString(1),aux);
			}
			rs2.close();
			smt2.close();
			
			PreparedStatement smt3 = con
					.prepareStatement(" select equipo.id, moneda.nickName,precioReposicion, unidadTiempo.nombre, precioArriendo,moneda.id from `"+db+"`.precio" + 
							" left join `"+db+"`.equipo on equipo.id = precio.id_equipo" + 
							" left join `"+db+"`.unidad on unidad.id = equipo.id_unidad" + 
							" left join `"+db+"`.moneda on moneda.id = precio.id_moneda" + 
							" left Join `"+db+"`.unidadTiempo on unidadTiempo.id=precio.id_unidadTiempo;");
			ResultSet rs3 = smt3.executeQuery();
			while (rs3.next()) {
				List<String> aux = new ArrayList<String>();
				Long idMoneda=rs3.getLong(6);
				switch(dec.get(idMoneda).toString()) {
				 case "0": myformatdouble = new DecimalFormat("#,##0",symbols); break;
				 case "2": myformatdouble = new DecimalFormat("#,##0.00",symbols); break;
				 case "4": myformatdouble = new DecimalFormat("#,##0.0000",symbols); break;
				 case "6": myformatdouble = new DecimalFormat("#,##0.000000",symbols); break;
				 default:  break;
				}
				aux.add(rs3.getString(2));  // moneda
				aux.add(myformatdouble.format(rs3.getDouble(3)));  // precioReposicion
				aux.add(rs3.getString(4));  // unidadTiempo
				aux.add(myformatdouble.format(rs3.getDouble(5)));  // precioArriendo
				mapListaPrecios.put(rs3.getString(1),aux);
			}
			rs3.close();
			smt3.close();
			
			for(int i=0;i<listaEquipos.size();i++) {
				List<String> aux = new ArrayList<String>();
				aux.add(listaEquipos.get(i).get(0)); // 0 idEquipo
				aux.add(listaEquipos.get(i).get(1)); // 1 codEquipo
				aux.add(listaEquipos.get(i).get(2)); // 2 nomEquipo
				
				Double kg = Double.parseDouble(listaEquipos.get(i).get(5));
				Double m2 = Double.parseDouble(listaEquipos.get(i).get(6));
				
				if(kg > 0) {
					aux.add(myformatdouble2.format(kg)); // 3 peso en KG
				}else {
					aux.add(""); // 3 peso en KG
				}
				
				List<String> compra = mapListaCompras.get(listaEquipos.get(i).get(0));
				if(compra==null) {
					aux.add(""); // 4 unidad
					aux.add(""); // 5 cantidad
					aux.add(""); // 6 moneda
					aux.add(""); // 7 precioCompra unitario
				}else {
					aux.add(compra.get(0)); // 4 unidad
					aux.add(compra.get(1)); // 5 cantidad
					aux.add(compra.get(2)); // 6 moneda
					aux.add(compra.get(3)); // 7 precioCompra unitario
				}
				List<String> precio = mapListaPrecios.get(listaEquipos.get(i).get(0));
				if(precio==null) {
					aux.add(""); // 8 moneda
					aux.add(""); // 9 precioReposicion
					aux.add(""); // 10 unidad de arriendo
					aux.add(""); // 11 precioArriendo
				}else {
					aux.add(precio.get(0)); // 8 moneda
					aux.add(precio.get(1)); // 9 precioReposicion
					aux.add(precio.get(2)); // 10 unidad de arriendo
					aux.add(precio.get(3)); // 11 precioArriendo
				}
				aux.add(listaEquipos.get(i).get(4)); // 12 nombre del grupo
				
				if(m2 > 0) {
					aux.add(myformatdouble2.format(m2)); // 13 superficie en M2
				}else {
					aux.add(""); // 13 superficie en M2
				}
				lista.add(aux);
			}
			
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static File exportaReportInventarioTodo(String db, List<List<String>> lista, Map<String,String> mapDiccionario) {
		
		File tmp = null;
try{
	tmp = TempFile.createTempFile("tmp","null");
}catch(Exception e){}
		
		try {
			String path = "formatos/excel.xlsx";
			InputStream formato = Archivos.leerArchivo(path);
            Workbook libro = WorkbookFactory.create(formato);
            formato.close();
            
            // 0 negro 1 blanco 2 rojo 3 verde 4 azul 5 amarillo 19 celeste
            CellStyle titulo = libro.createCellStyle();
            Font font = libro.createFont();
            font.setBold(true);
            font.setColor((short)4);
            font.setFontHeight((short)(14*20));
            titulo.setFont(font);
            
            CellStyle subtitulo = libro.createCellStyle();
            Font font2 = libro.createFont();
            font2.setBold(true);
            font2.setColor((short)0);
            font2.setFontHeight((short)(12*20));
            subtitulo.setFont(font2);
            
            CellStyle encabezado = libro.createCellStyle();
            encabezado.setBorderBottom(BorderStyle.THIN);
            encabezado.setBorderTop(BorderStyle.THIN);
            encabezado.setBorderRight(BorderStyle.THIN);
            encabezado.setBorderLeft(BorderStyle.THIN);
            encabezado.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            encabezado.setFillForegroundColor((short)19);
            encabezado.setAlignment(HorizontalAlignment.CENTER);
            
            CellStyle detalle = libro.createCellStyle();
            detalle.setBorderBottom(BorderStyle.THIN);
            detalle.setBorderTop(BorderStyle.THIN);
            detalle.setBorderRight(BorderStyle.THIN);
            detalle.setBorderLeft(BorderStyle.THIN);
            
            Sheet hoja1 = libro.getSheetAt(0);
            Row row = null;
            Cell cell = null;
            
            row = hoja1.createRow(1);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellValue("REPORTE INVENTARIO (CON PRECIOS DE COMPRA Y LISTA - INCLUYE EQUIPOS CON y SIN STOCK)");
			
			row = hoja1.createRow(2);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("EMPRESA: "+mapDiccionario.get("nEmpresa"));
			
			row = hoja1.createRow(4);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("FECHA: "+Fechas.hoy().getFechaStrDDMMAA());
			
			// encabezado de la tabla
			
			int posCell = 0;
			int posColl = 0;
			row = hoja1.createRow(8);
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 7*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("GRUPO/FAMILIA");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("CODIGO");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 10*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("EQUIPO");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("PESO (KG)");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("SUP (M2)");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 2*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("UN");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("CANTIDAD");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 2*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("MONEDA");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("PU COMPRA");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 2*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("MONEDA");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("PU VENTA");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("UN."+mapDiccionario.get("ARRIENDO"));
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("PU "+mapDiccionario.get("ARRIENDO"));
			
		
			//INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
			InputStream x = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
            byte[] bytes = IOUtils.toByteArray(x);
            x.close();
            int pngIndex = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			Drawing draw = hoja1.createDrawingPatriarch();
			CreationHelper helper = libro.getCreationHelper();
			ClientAnchor anchor = helper.createClientAnchor();
	        //set top-left corner for the image
	        anchor.setCol1(10);
	        anchor.setRow1(1);
			Picture img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);
			
			//DETALLE DE LA TABLA
			int posRow = 9;
			for(int i=0;i<lista.size();i++){
				row = hoja1.createRow(posRow);
				posCell = 0;
				posColl = 0;
				Double aux = (double)0;
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(12));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(1));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(2));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            if(!lista.get(i).get(3).trim().equals("")) {
	            	aux = Double.parseDouble(lista.get(i).get(3).replaceAll(",", ""));
					cell.setCellValue(aux);
	            }
	            
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            if(!lista.get(i).get(13).trim().equals("")) {
	            	aux = Double.parseDouble(lista.get(i).get(13).replaceAll(",", ""));
					cell.setCellValue(aux);
	            }
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(4));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            if(!lista.get(i).get(5).trim().equals("")) {
	            	aux = Double.parseDouble(lista.get(i).get(5).replaceAll(",", ""));
					cell.setCellValue(aux);
	            }
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(6));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            if(!lista.get(i).get(7).trim().equals("")) {
	            	aux = Double.parseDouble(lista.get(i).get(7).replaceAll(",", ""));
	 				cell.setCellValue(aux);
	            }
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(8));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            if(!lista.get(i).get(9).trim().equals("")) {
	            	aux = Double.parseDouble(lista.get(i).get(9).replaceAll(",", ""));
					cell.setCellValue(aux);
	            }
	            
	            posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(10));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            if(!lista.get(i).get(11).trim().equals("")) {
	            	aux = Double.parseDouble(lista.get(i).get(11).replaceAll(",", ""));
					cell.setCellValue(aux);
	            }
				
				posRow++;
			}
			
			
			posRow = posRow + 5;
			row = hoja1.createRow(posRow);
			cell = row.createCell(1);
			Hyperlink hiper = helper.createHyperlink(HyperlinkType.URL);
			hiper.setAddress("https://www.inqsol.cl");
			cell.setHyperlink(hiper);
			cell.setCellValue("Documento generado desde MADA propiedad de INQSOL");
			
			
			// Write the output to a file tmp
			FileOutputStream fileOut = new FileOutputStream(tmp);
			libro.write(fileOut);
			fileOut.close();
			
		} catch (Exception e) {
			e.printStackTrace();
        }
		
	  return tmp;
		
	}
	
	public static List<List<String>> reportInventarioProyecto(Connection con, String db, String permisoPorBodega, String esPorSucursal, String id_sucursal) {
		List<List<String>> lista = new ArrayList<List<String>>();
		
		permisoPorBodega = permisoPorBodega.replaceAll("movimiento", "bodegaEmpresa").replaceAll("id_bodegaEmpresa", "id");
		
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and bodegaEmpresa.id_sucursal = " + id_sucursal;
		}
		
		
		try {
			PreparedStatement smt5 = con
					.prepareStatement( " select "
							+ " bodegaEmpresa.id,"
							+ " bodegaEmpresa.esInterna,"
							+ " bodegaEmpresa.id_cliente,"
							+ " bodegaEmpresa.id_proyecto,"
							+ " bodegaEmpresa.nombre,"
							+ " bodegaEmpresa.id_sucursal"
							+ " from `"+db+"`.bodegaEmpresa "
							+ " where bodegaEmpresa.vigente = 1 and bodegaEmpresa.esInterna = 2 "+condSucursal+permisoPorBodega+";");
			ResultSet rs5 = smt5.executeQuery();
			Map<Long,Long> mapIdBodegas = Movimiento.mapAllIdBodEnMov(con, db);
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			Map<Long, TipoBodega> mapTipoBodega = TipoBodega.mapAll(con, db);
			Map<Long, Cliente> mapCliente = Cliente.mapAllClientes(con, db);
			Map<Long,Proyecto> mapProyecto = Proyecto.mapAllProyectos(con, db);
			while (rs5.next()) {
				Long id_bodegaEmpresa = mapIdBodegas.get(rs5.getLong(1));
				if(id_bodegaEmpresa != null) {
					String nameSucursal = "";
					Sucursal sucursal = mapSucursal.get(rs5.getLong(6));
					if(sucursal!=null) {
						nameSucursal = sucursal.getNombre();
					}
					
					String tipoCliente = "";
					TipoBodega tipoBodega = mapTipoBodega.get(rs5.getLong(2));
					if(tipoBodega != null) {
						tipoCliente = tipoBodega.getNombre();
					}
					
					String rutClie = "";
					String nickClie = "";
					Cliente cliente = mapCliente.get(rs5.getLong(3));
					if(cliente != null) {
						rutClie = cliente.getRut();
						nickClie = cliente.getNickName();
					}
					
					String nomProy = "";
					String comuna = "";
					Proyecto proyecto = mapProyecto.get(rs5.getLong(4));
					if(proyecto != null) {
						nomProy = proyecto.getNickName();
						comuna = proyecto.getComuna();
					}
					
					
					List<String> aux = new ArrayList<String>();
					aux.add(rs5.getString(2)); // es cliente interno
					aux.add(rs5.getString(1));  // idbodega empresa
					aux.add(rs5.getString(3));  // id de cliente
					aux.add(rs5.getString(4));  // id del proyecto
					aux.add(tipoCliente); 		 // tipo de cliente interno o externo
					aux.add(rs5.getString(5));  // nombre bodega o empresa
					aux.add(rutClie);  			// rut del cliente
					aux.add(nickClie);  		// nombre del cliente
					aux.add(nomProy);  			// nombre del proyecto
					aux.add(comuna);  			// comuna
					aux.add(nameSucursal);  	//10 nameSucursal
					lista.add(aux);
				}
				
			}
			
			
			rs5.close();
			smt5.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<List<String>> reportInventarioProyectoDetalle(Connection con, String db, Long id_bodegaEmpresa, 
			Map<String,String> mapDiccionario) {
		List<List<String>> lista = new ArrayList<List<String>>();
		Map<String,Long> idMoneda = new HashMap<String,Long>();
		Map<String,String> moneda = new HashMap<String,String>();
		Map<String,Double> precioVenta = new HashMap<String,Double>();
		Map<String,Double> precioReposicion = new HashMap<String,Double>();
		Map<String,Double> precioArriendo = new HashMap<String,Double>();
		Map<String,String> unidadTiempo = new HashMap<String,String>();
		Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
		try {
			PreparedStatement smt5 = con
					.prepareStatement(" select " +
							" listaPrecio.id_bodegaEmpresa, " +
							" listaPrecio.id_equipo, " +
							" listaPrecio.id_moneda, " +
							" moneda.nickName, " +
							" listaPrecio.precioVenta, " +
							" listaPrecio.precioReposicion, " +
							" listaPrecio.precioArriendo, " +
							" unidadTiempo.nombre, " +
							" listaPrecio.id_cotizacion "+
							" from `"+db+"`.listaPrecio " +
							" left join `"+db+"`.moneda on moneda.id = listaPrecio.id_moneda " +
							" left join `"+db+"`.unidadTiempo on unidadTiempo.id = listaPrecio.id_unidadTiempo " +
							" where id_bodegaEmpresa = ?;");
			smt5.setLong(1, id_bodegaEmpresa);
			ResultSet rs5 = smt5.executeQuery();
			
			while (rs5.next()) {
				idMoneda.put(rs5.getString(2)+"-"+rs5.getString(9), rs5.getLong(3));
				moneda.put(rs5.getString(2)+"-"+rs5.getString(9), rs5.getString(4));
				precioVenta.put(rs5.getString(2)+"-"+rs5.getString(9), rs5.getDouble(5));
				precioReposicion.put(rs5.getString(2)+"-"+rs5.getString(9), rs5.getDouble(6));
				precioArriendo.put(rs5.getString(2)+"-"+rs5.getString(9), rs5.getDouble(7));
				unidadTiempo.put(rs5.getString(2)+"-"+rs5.getString(9), rs5.getString(8));
			}
			rs5.close();
			smt5.close();
			
			String agruparPor = ",movimiento.id_cotizacion";
			if(BodegaEmpresa.esInterna(con, db, id_bodegaEmpresa)) agruparPor="";
			
			PreparedStatement smt6 = con
					.prepareStatement(" select " +
							" movimiento.id_bodegaEmpresa,  " +
							" movimiento.id_equipo, " +
							" if(sum(if(movimiento.id_tipoMovimiento=1,1,-1)*movimiento.cantidad)=-0,0,"
									+ "sum(if(movimiento.id_tipoMovimiento=1,1,-1)*movimiento.cantidad)), " +
							" ifnull(movimiento.id_cotizacion,0)  " +
							" from `"+db+"`.movimiento  " +
							" where movimiento.id_bodegaEmpresa = ? " +
							" group by movimiento.id_bodegaEmpresa, movimiento.id_equipo" + agruparPor +";");
			smt6.setLong(1, id_bodegaEmpresa);
			ResultSet rs6 = smt6.executeQuery();
			
			Map<Long,Equipo> mapEquipo = Equipo.mapAllAll(con, db);
			Map<Long, BodegaEmpresa> mapBodega = BodegaEmpresa.mapAll(con, db);
			Map<Long,Proyecto> mapProyecto = Proyecto.mapAllProyectos(con, db);
			Map<Long, Cotizacion> mapCotizacion = Cotizacion.mapAll(con, db);
			
			
			while (rs6.next()) {
				
				Double cantidad = rs6.getDouble(3);
				
				if(cantidad != 0){
					
					BodegaEmpresa bodega = mapBodega.get(id_bodegaEmpresa);
					if(bodega!=null) {
						
						Long id_equipo = rs6.getLong(2);
						
						try{
						switch(dec.get(idMoneda.get(id_equipo+"-"+rs6.getString(14))).toString()) {
						 case "0": myformatdouble = new DecimalFormat("#,##0",symbols); break;
						 case "2": myformatdouble = new DecimalFormat("#,##0.00",symbols); break;
						 case "4": myformatdouble = new DecimalFormat("#,##0.0000",symbols); break;
						 case "6": myformatdouble = new DecimalFormat("#,##0.000000",symbols); break;
						 default:  break;
						}
						}catch(Exception e){
							myformatdouble = new DecimalFormat("#,##0.00",symbols);
						}
						
						Long id_grupo = (long)0;
						Long id_proyecto = (long)0;
						Long id_cotizacion = rs6.getLong(4);
						
						String nomGrupo = "SIN GRUPO";
						String nomProyecto = "";
						String codEquipo = "";
						String nomEquipo = "";
						String unidad = "";
						
						
						
						Equipo equipo = mapEquipo.get(id_equipo);
						if(equipo!=null) {
							id_equipo = equipo.getId();
							id_grupo = equipo.getId_grupo();
							nomGrupo = equipo.getGrupo();
							codEquipo = equipo.getCodigo();
							nomEquipo = equipo.getNombre();
							unidad = equipo.getUnidad();
						}
						
						Proyecto proyecto = mapProyecto.get(bodega.getId_proyecto());
						if(proyecto!=null) {
							nomProyecto = proyecto.getNickName();
							id_proyecto = proyecto.getId();
						}
						
						String auxNickMoneda = moneda.get(id_equipo+"-"+id_cotizacion);
						if(auxNickMoneda == null){
							auxNickMoneda = mapDiccionario.get("CLP");
						}
						
						Double auxPrecioVenta = precioVenta.get(id_equipo+"-"+id_cotizacion);
						if(auxPrecioVenta == null){
							auxPrecioVenta = (double) 0;
						}
						
						Double totalVta = cantidad * auxPrecioVenta;
						
						Double auxPrecioArriendo = precioArriendo.get(id_equipo+"-"+id_cotizacion);
						if(auxPrecioArriendo == null){
							auxPrecioArriendo = (double) 0;
						}
						Double tasaArriendo = (double)0;
						if(auxPrecioArriendo > 0 && auxPrecioVenta > 0) {
							tasaArriendo=auxPrecioArriendo/auxPrecioVenta;
						}
						
						String unTiempo = unidadTiempo.get(id_equipo+"-"+id_cotizacion);
						if(unTiempo == null) {
							unTiempo = "";
						}
						
						Double totalArr = cantidad * auxPrecioArriendo;
						
						Long numCotizacion = (long)0;
						Cotizacion coti = mapCotizacion.get(id_cotizacion);
						
						if(coti!=null) {
							numCotizacion = coti.getNumero();
						}
						
						List<String> aux = new ArrayList<String>();
						aux.add(id_bodegaEmpresa.toString());  	// 0 id bodega
						aux.add(id_grupo.toString());  			// 1 id grupo
						aux.add(id_equipo.toString());  		// 2 id equipo
						aux.add(id_proyecto.toString());  		// 3 id proyecto
						aux.add(nomGrupo);  					// 4 nombre grupo
						aux.add(nomProyecto);  					// 5 nombre proyecto
						aux.add(codEquipo);  					// 6 codigo equipo
						aux.add(nomEquipo);  					// 7 nombre equipo
						aux.add(unidad); 						// 8 unidad de medida equipo
						aux.add(myformatdouble2.format(cantidad));  	// 9 cantidad
						aux.add(auxNickMoneda);  						// 10 nickname moneda
						aux.add(myformatdouble.format(auxPrecioVenta)); // 11 precio de venta unitario
						aux.add(myformatdouble.format(totalVta));  		// 12 precio de venta Total
						aux.add(myformatdouble2.format(tasaArriendo*100)+" %"); // 13 tasa de arriendo
						aux.add(unTiempo);  									// 14 unidad de tiempo valor arriendo
						aux.add(myformatdouble.format(auxPrecioArriendo)); 		// 15 precio de arriendo
						aux.add(myformatdouble.format(totalArr));  				// 16 precio de arriendo Total
						aux.add(id_cotizacion.toString());   					// 17 id cotizacion
						aux.add(numCotizacion.toString());   					// 18 numero cotizacion
						lista.add(aux);
					}
				}
			}
			rs6.close();
			smt6.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static File exportaReportInventarioProyectoDetalle(String db, List<List<String>> lista, Map<String,String> mapDiccionario, BodegaEmpresa bodega) {
		
		File tmp = null;
try{
	tmp = TempFile.createTempFile("tmp","null");
}catch(Exception e){}
		
		try {
			String path = "formatos/excel.xlsx";
			InputStream formato = Archivos.leerArchivo(path);
            Workbook libro = WorkbookFactory.create(formato);
            formato.close();
            
            // 0 negro 1 blanco 2 rojo 3 verde 4 azul 5 amarillo 19 celeste
            CellStyle titulo = libro.createCellStyle();
            Font font = libro.createFont();
            font.setBold(true);
            font.setColor((short)4);
            font.setFontHeight((short)(14*20));
            titulo.setFont(font);
            
            CellStyle subtitulo = libro.createCellStyle();
            Font font2 = libro.createFont();
            font2.setBold(true);
            font2.setColor((short)0);
            font2.setFontHeight((short)(12*20));
            subtitulo.setFont(font2);
            
            CellStyle encabezado = libro.createCellStyle();
            encabezado.setBorderBottom(BorderStyle.THIN);
            encabezado.setBorderTop(BorderStyle.THIN);
            encabezado.setBorderRight(BorderStyle.THIN);
            encabezado.setBorderLeft(BorderStyle.THIN);
            encabezado.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            encabezado.setFillForegroundColor((short)19);
            encabezado.setAlignment(HorizontalAlignment.CENTER);
            
            CellStyle encabezado2 = libro.createCellStyle();
            encabezado2.setBorderBottom(BorderStyle.THIN);
            encabezado2.setBorderTop(BorderStyle.THIN);
            encabezado2.setBorderRight(BorderStyle.THIN);
            encabezado2.setBorderLeft(BorderStyle.THIN);
            encabezado2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            encabezado2.setFillForegroundColor((short)19);
            encabezado2.setAlignment(HorizontalAlignment.LEFT);
            
            CellStyle detalle = libro.createCellStyle();
            detalle.setBorderBottom(BorderStyle.THIN);
            detalle.setBorderTop(BorderStyle.THIN);
            detalle.setBorderRight(BorderStyle.THIN);
            detalle.setBorderLeft(BorderStyle.THIN);
            
            
            
            
            Sheet hoja1 = libro.getSheetAt(0);
            Row row = null;
            Cell cell = null;
            
            row = hoja1.createRow(1);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellValue("EXISTENCIAS POR "+mapDiccionario.get("BODEGA")+"/PROYECTO");
		
            row = hoja1.createRow(2);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellValue(mapDiccionario.get("BODEGA")+"/PROYECTO: "+bodega.getNombre().toUpperCase());
			
			row = hoja1.createRow(3);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("EMPRESA: "+mapDiccionario.get("nEmpresa"));
			
			row = hoja1.createRow(4);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("FECHA: "+Fechas.hoy().getFechaStrDDMMAA());
			
			// encabezado de la tabla
			
			row = hoja1.createRow(8);
			
            cell = row.createCell(8);
            cell.setCellStyle(encabezado2);
			cell.setCellValue("PRECIO REPOSICION");
			
			cell = row.createCell(9);
            cell.setCellStyle(encabezado2);
			
			cell = row.createCell(10);
            cell.setCellStyle(encabezado2);
			cell.setCellValue("PRECIO "+ mapDiccionario.get("ARRIENDO")+" (CANT * PU)");
			
			cell = row.createCell(11);
            cell.setCellStyle(encabezado2);
			
            cell = row.createCell(12);
            cell.setCellStyle(encabezado2);
			
			
			
			int posCell = 0;
			int posColl = 0;
			row = hoja1.createRow(9);
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 7*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("GRUPO");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("Nro.COTI");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("CODIGO");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 10*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("EQUIPO");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 2*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("UN");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("CANTIDAD");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 2*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("MON");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("P.UNITARIO");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("P.TOTAL");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 2*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("UN "+mapDiccionario.get("ARR"));
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("P.UNITARIO");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("P.TOTAL");
		
			//INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
			InputStream x = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
            byte[] bytes = IOUtils.toByteArray(x);
            x.close();
            int pngIndex = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			Drawing draw = hoja1.createDrawingPatriarch();
			CreationHelper helper = libro.getCreationHelper();
			ClientAnchor anchor = helper.createClientAnchor();
	        //set top-left corner for the image
	        anchor.setCol1(10);
	        anchor.setRow1(1);
			Picture img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);
			
			//DETALLE DE LA TABLA
			int posRow = 10;
			for(int i=0;i<lista.size();i++){
				row = hoja1.createRow(posRow);
				posCell = 0;
				posColl = 0;
				Double aux = (double)0;
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(4));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(18));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(6));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(7));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(8));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(lista.get(i).get(9).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(10));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(lista.get(i).get(11).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(lista.get(i).get(12).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(14));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(lista.get(i).get(15).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(lista.get(i).get(16).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posRow++;
			}
			
			posRow = posRow + 5;
			row = hoja1.createRow(posRow);
			cell = row.createCell(1);
			Hyperlink hiper = helper.createHyperlink(HyperlinkType.URL);
			hiper.setAddress("https://www.inqsol.cl");
			cell.setHyperlink(hiper);
			cell.setCellValue("Documento generado desde MADA propiedad de INQSOL");
			
			
			
			// Write the output to a file tmp
			FileOutputStream fileOut = new FileOutputStream(tmp);
			libro.write(fileOut);
			fileOut.close();
			
		} catch (Exception e) {
			e.printStackTrace();
        }
	  return tmp;
	  
	}
	
	public static List<List<String>> listaProyectosAsignados(Connection con, String db, String permisoPorBodega, String esPorSucursal, String id_sucursal) {
		List<List<String>> lista = new ArrayList<List<String>>();
		
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and bodegaEmpresa.id_sucursal = " + id_sucursal;
		}
		
		try {
			PreparedStatement smt5 = con
					.prepareStatement(" select " +
							" ifnull(proyecto.id,0), " +
							" ifnull(proyecto.nickName,'Sin Proyecto'), " +
							" ifnull(comunas.nombre,'') " +
							" from `"+db+"`.movimiento   " +
							" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa  " +
							" left join `"+db+"`.proyecto on proyecto.id = bodegaEmpresa.id_proyecto " +
							" left join `"+db+"`.comunas on comunas.codigo = proyecto.cod_comuna " +
							" where bodegaEmpresa.vigente = 1 and bodegaEmpresa.esInterna = 2 " + permisoPorBodega + condSucursal +
							" group by proyecto.id  " +
							" order by proyecto.nickName;");
			ResultSet rs5 = smt5.executeQuery();
			while (rs5.next()) {
				List<String> aux = new ArrayList<String>();
				aux.add(rs5.getString(1));  // 0 id del proyecto
				aux.add(rs5.getString(2));  // 1 nombre del proyecto
				aux.add(rs5.getString(3));  // 2 comuna
				lista.add(aux);
			}
			rs5.close();
			smt5.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<List<String>> reportInventarioSoloBodegasInternas(Connection con, String db, String permisoPorBodega, String esPorSucursal, String id_sucursal) {
		List<List<String>> lista = new ArrayList<List<String>>();
		
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and bodegaEmpresa.id_sucursal = " + id_sucursal;
		}
		
		try {
			PreparedStatement smt5 = con
					.prepareStatement(" select " +
							" bodegaEmpresa.esInterna, " +
							" movimiento.id_bodegaEmpresa, " +
							" ifnull(cliente.id,0), " +
							" ifnull(proyecto.id,0), " +
							" bodegaEmpresa.nombre,   " +
							" ifnull(cliente.rut,''), " +
							" ifnull(cliente.nickName,''), " +
							" ifnull(proyecto.nickName,''), " +
							" ifnull(comunas.nombre,''), " +
							" tipoBodega.nombre, " +
							" bodegaEmpresa.factorM2Viga, " +
							" ifnull(movimiento.id_cotizacion,0), "+
							" bodegaEmpresa.id_sucursal "+
							" from `"+db+"`.movimiento   " +
							" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa  " +
							" left join `"+db+"`.cliente on cliente.id = bodegaEmpresa.id_cliente " +
							" left join `"+db+"`.proyecto on proyecto.id = bodegaEmpresa.id_proyecto " +
							" left join `"+db+"`.comunas on comunas.codigo = proyecto.cod_comuna " +
							" left join `"+db+"`.tipoBodega on tipoBodega.id = bodegaEmpresa.esInterna " +
							" where bodegaEmpresa.vigente = 1 and tipoBodega.id = 1 " + permisoPorBodega + condSucursal +
							" group by movimiento.id_bodegaEmpresa  " +
							" order by bodegaEmpresa.esInterna,bodegaEmpresa.nombre;");
			ResultSet rs5 = smt5.executeQuery();
			Map<Long, Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			while (rs5.next()) {
				String nameSucursal = "";
				Sucursal sucursal = mapSucursal.get(rs5.getLong(13));
				if(sucursal!=null) {
					nameSucursal = sucursal.getNombre();
				}
				List<String> aux = new ArrayList<String>();
				aux.add(rs5.getString(1));  // 0 es cliente interno
				aux.add(rs5.getString(2));  // 1 idbodega empresa
				aux.add(rs5.getString(3));  // 2 id de cliente
				aux.add(rs5.getString(4));  // 3 id del proyecto
				aux.add(rs5.getString(10));  // 4 tipo de cliente interno o externo
				aux.add(rs5.getString(5));  // 5 nombre bodega o empresa
				aux.add(rs5.getString(6));  // 6 rut del cliente
				aux.add(rs5.getString(7));  // 7 nombre del cliente
				aux.add(rs5.getString(8));  // 8 nombre del proyecto
				aux.add(rs5.getString(9));  // 9 comuna
				aux.add(rs5.getString(11)); // 10 factorViga
				aux.add(nameSucursal); // 11 nameSucursal
				lista.add(aux);
			}
			rs5.close();
			smt5.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<List<String>> reportInventarioPorEstadosDeEquiposTodos(Connection con, String db, String permisoPorBodega, String esPorSucursal, String id_sucursal) {
		List<List<String>> lista = new ArrayList<List<String>>();
		
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and bodegaEmpresa.id_sucursal = " + id_sucursal;
		}
		
		try {
			PreparedStatement smt5 = con
					.prepareStatement(" select  " +
							" bodegaEmpresa.esInterna,  " +
							" movimiento.id_bodegaEmpresa,  " +
							" ifnull(cliente.id,0),  " +
							" ifnull(proyecto.id,0),  " +
							" bodegaEmpresa.nombre,    " +
							" ifnull(cliente.rut,''),  " +
							" ifnull(cliente.nickName,''),  " +
							" ifnull(proyecto.nickName,''),  " +
							" ifnull(comunas.nombre,''),  " +
							" tipoBodega.nombre," +
							" estadoEquipo.id," +
							" tipoEstado.sigla," +
							" tipoEstado.nombre, " +
							" bodegaEmpresa.id_sucursal " +
							" from `"+db+"`.estadoEquipo " +
							" left join `"+db+"`.movimiento on movimiento.id=estadoEquipo.id_movimiento " +
							" left Join `"+db+"`.tipoEstado on tipoEstado.id = estadoEquipo.id_tipoEstado " +
							" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa   " + 
							" left join `"+db+"`.cliente on cliente.id = bodegaEmpresa.id_cliente  " +
							" left join `"+db+"`.proyecto on proyecto.id = bodegaEmpresa.id_proyecto  " +
							" left join `"+db+"`.comunas on comunas.codigo = proyecto.cod_comuna  " +
							" left join `"+db+"`.tipoBodega on tipoBodega.id = bodegaEmpresa.esInterna  " +
							" where bodegaEmpresa.vigente = 1 and tipoBodega.id = 2 " + permisoPorBodega + condSucursal +
							" group by movimiento.id_bodegaEmpresa   " +
							" order by bodegaEmpresa.esInterna,bodegaEmpresa.nombre;");
			ResultSet rs5 = smt5.executeQuery();
			Map<Long, Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			while (rs5.next()) {
				String nameSucursal = "";
				Sucursal sucursal = mapSucursal.get(rs5.getLong(14));
				if(sucursal!=null) {
					nameSucursal = sucursal.getNombre();
				}
				List<String> aux = new ArrayList<String>();
				aux.add(rs5.getString(1)); // es cliente interno
				aux.add(rs5.getString(2));  // idbodega empresa
				aux.add(rs5.getString(3));  // id de cliente
				aux.add(rs5.getString(4));  // id del proyecto
				aux.add(rs5.getString(10));  // tipo de cliente interno o externo
				aux.add(rs5.getString(5));  // nombre bodega o empresa
				aux.add(rs5.getString(6));  // rut del cliente
				aux.add(rs5.getString(7));  // nombre del cliente
				aux.add(rs5.getString(8));  // nombre del proyecto
				aux.add(rs5.getString(9));  // comuna
				aux.add(nameSucursal);  // 10 nameSucursal
				lista.add(aux);
			}
			rs5.close();
			smt5.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<List<String>> reportInventarioPorEstadosPorPeriodo(Connection con, String db, String permisoPorBodega, String desdeAAMMDD, String hastaAAMMDD, String esPorSucursal, String id_sucursal) {
		List<List<String>> lista = new ArrayList<List<String>>();
		
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and bodegaEmpresa.id_sucursal = " + id_sucursal;
		}
		String query = String.format(" select  "
				+ " id_bodegaEmpresa, "
				+ " tipoEstado.id, "
				+ " guia.numero, "
				+ " guia.fecha, "
				+ " movimiento.id_equipo, "
				+ " estadoEquipo.cantidad, "
				+ " movimiento.id_cotizacion, "
				+ " bodegaEmpresa.id_sucursal, "
				+ " guia.numGuiaCliente, "
				+ " estadoEquipo.cobraArriendo, "
				+ " movimiento.id, "
				+ " tipoEstado.cobraArriendo "
				+ " from `%s`.estadoEquipo "
				+ " left join `%s`.movimiento on movimiento.id = estadoEquipo.id_movimiento "
				+ " left Join `%s`.tipoEstado on tipoEstado.id = estadoEquipo.id_tipoEstado "
				+ " left join `%s`.guia on guia.id = movimiento.id_guia "
				+ " left join `%s`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa "
				+ " where numero is not null  and movimiento.id_tipoMovimiento=2 and bodegaEmpresa.vigente = 1 and bodegaEmpresa.esInterna = 2 "
				+ " and fecha between '"+desdeAAMMDD+"' and '"+hastaAAMMDD+"' "
				+   permisoPorBodega + condSucursal
				+ " order by id_bodegaEmpresa, tipoEstado.id, movimiento.id_equipo, guia.fecha;",db,db,db,db,db);

		try (PreparedStatement smt5 = con.prepareStatement(query);
			 ResultSet rs5 = smt5.executeQuery();){
			Map<Long, Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			while (rs5.next()) {
				String nameSucursal = "";
				Sucursal sucursal = mapSucursal.get(rs5.getLong(8));
				if(sucursal!=null) {
					nameSucursal = sucursal.getNombre();
				}
				List<String> aux = new ArrayList<String>();
				aux.add(rs5.getString(1));  // 0 id_bodegaEmpresa
				aux.add(rs5.getString(2));  // 1 tipoEstado.id
				aux.add(rs5.getString(3));  // 2 guia.numero
				aux.add(rs5.getString(4));  // 3 guia.fecha
				aux.add(rs5.getString(5));  // 4 id_equipo
				aux.add(rs5.getString(6));  // 5 cantidad
				aux.add(rs5.getString(7));  // 6 id_cotizacion
				aux.add(nameSucursal);  			   // 7 nameSucursal
				aux.add(rs5.getString(9));  // 8 referencia cliente
				aux.add(rs5.getString(10));  // 9 cobraArriendo por estado la guia
				aux.add(rs5.getString(11));  // 10 movimiento.id
				aux.add(rs5.getString(12));  // 11 cobraArriendo por el tipo estado
				if(rs5.getDouble(6) > 0) {
					lista.add(aux);
				}
				
			}
		} catch (SQLException e) {
			String className = ReportInventarios.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (lista);
	}
	
	public static List<List<String>> listaBodegasConStock(Connection con, String db, String fechaCorte) {
		List<List<String>> listaBodegas = new ArrayList<List<String>>();
		
		String cond ="";
		if(!fechaCorte.equals("0")) {
			cond = " left join `"+db+"`.guia on guia.id = movimiento.id_guia and movimiento.id_guia<>0" +
					" left join `"+db+"`.compra on compra.id = movimiento.id_compra and movimiento.id_compra<>0 " +
					" left join `"+db+"`.factura on factura.id = compra.id_factura " +
					" left join `"+db+"`.baja on baja.id = movimiento.id_baja and movimiento.id_baja<>0 " +
					" left join `"+db+"`.actaBaja on actaBaja.id = baja.id_actaBaja " +
					" where  (guia.fecha <= '"+fechaCorte+"' || movimiento.id_guia=0) and (factura.fecha <= '"+fechaCorte+"' || movimiento.id_compra=0) and (actaBaja.fecha <= '"+fechaCorte+"' || movimiento.id_baja=0) ";
		}
		
		try {
			
			PreparedStatement smt2 = con
					.prepareStatement(" select movimiento.id_bodegaEmpresa " + 
							" from `"+db+"`.movimiento " + cond +
							" group by movimiento.id_bodegaEmpresa " + 
							" having if(sum(movimiento.cantidad*if(movimiento.id_tipoMovimiento=1,1,-1))=-0,0,sum(movimiento.cantidad*if(movimiento.id_tipoMovimiento=1,1,-1)))>0");
			String listaCond ="";
			ResultSet rs2 = smt2.executeQuery();
			while (rs2.next()) {
				listaCond = listaCond + rs2.getString(1) + ",";
			}
			rs2.close();smt2.close();
			
			
			if(listaCond.length()>1) {
				listaCond = listaCond.substring(0,listaCond.length()-1);
				
				PreparedStatement smt1 = con
						.prepareStatement("select id,nombre,esInterna " + 
								" from `"+db+"`.bodegaEmpresa " + 
								" where vigente=1 " + 
								" and id in ("+listaCond+") " + 
								" order by esInterna,nombre;");
				ResultSet rs1 = smt1.executeQuery();
				while (rs1.next()) {
					List<String> aux = new ArrayList<String>();
					aux.add(rs1.getString(1)); // idBodega
					aux.add(rs1.getString(2)); // bodega
					aux.add(rs1.getString(3)); // es interna
					listaBodegas.add(aux);
				}
				rs1.close();smt1.close();
			}
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return(listaBodegas);
	}
	
	public static List<List<String>> reportInventarioEstadosPorPer(Connection con, String db, String permisoPorBodega, String desde, String hasta) {
		List<List<String>> lista = new ArrayList<List<String>>();
		String query = String.format("select "
				+ " bodegaEmpresa.nombre, "
				+ " guia.numero, "
				+ " guia.fecha, "
				+ " equipo.codigo, "
				+ " equipo.nombre, "
				+ " movimiento.cantidad, "
				+ " listaPrecio.id_moneda, "
				+ " listaPrecio.precioVenta, "
				+ " tipoEstado.sigla, "
				+ " estadoEquipo.cantidad, "
				+ " ifnull(tipoReparacion.sigla,''), "
				+ " ifnull(tipoReparacion.nombre,''), "
				+ " ifnull(reparacionEquipo.cantidad,''), "
				+ " ifnull(tipoReparacion.id_moneda,''), "
				+ " ifnull(tipoReparacion.precio,''), "
				+ " equipo.id, "
				+ " equipo.kg, "
				+ " equipo.m2, "
				+ " guia.numGuiaCliente, "
				+ " cliente.nickName, "
				+ " estadoEquipo.cobraArriendo, "
				+ " movimiento.id, "
				+ " tipoEstado.cobraArriendo "
				+ " from `%s`.estadoEquipo "
				+ " left join `%s`.guia on guia.id = estadoEquipo.id_guia "
				+ " left join `%s`.bodegaEmpresa on bodegaEmpresa.id = guia.id_bodegaOrigen "
				+ " left join `%s`.movimiento on movimiento.id = estadoEquipo.id_movimiento "
				+ " left join `%s`.equipo on equipo.id = movimiento.id_equipo "
				+ " left join `%s`.tipoEstado on tipoEstado.id = estadoEquipo.id_tipoEstado "
				+ " left join `%s`.reparacionEquipo on reparacionEquipo.id_estadoEquipo = estadoEquipo.id "
				+ " left join `%s`.tipoReparacion on tipoReparacion.id = reparacionEquipo.id_tipoReparacion "
				+ " left join `%s`.listaPrecio on listaPrecio.id_bodegaEmpresa = bodegaEmpresa.id and listaPrecio.id_equipo = equipo.id and listaPrecio.id_cotizacion = movimiento.id_cotizacion "
				+ "left join `%s`.cliente on cliente.id = bodegaEmpresa.id_cliente "
				+ " where bodegaEmpresa.vigente = 1 and guia.fecha between ? and ? "
				+   permisoPorBodega
				+ " order by movimiento.id_bodegaEmpresa, guia.fecha, movimiento.id_equipo, tipoEstado.sigla;",db,db,db,db,db,db,db,db,db,db);

		try (PreparedStatement smt5 = con.prepareStatement(query)){
			smt5.setString(1, desde);
			smt5.setString(2, hasta);
			try(ResultSet rs5 = smt5.executeQuery()) {
				Map<Long, Moneda> mapMoneda = Moneda.mapMonedas(con, db);
				while (rs5.next()) {

					// kg por unidad
					Double kg = rs5.getDouble(17);
					String kgStr = "";
					if (kg > 0) {
						kgStr = myformatdouble2.format(kg);
					}
					// m2 por unidad
					Double m2 = rs5.getDouble(18);
					String m2Str = "";
					if (m2 > 0) {
						m2Str = myformatdouble2.format(m2);
					}
					Moneda monedaVtaArr = mapMoneda.get(rs5.getLong(7));
					String monVtaArr = "";
					Long decMonVtaArr = (long) 0;
					if (monedaVtaArr != null) {
						monVtaArr = monedaVtaArr.getNickName();
						decMonVtaArr = monedaVtaArr.getNumeroDecimales();
					}
					Moneda monedaRepar = mapMoneda.get(rs5.getLong(14));
					String monRepar = "";
					Long decMonRepar = (long) 0;
					if (monedaRepar != null) {
						monRepar = monedaRepar.getNickName();
						decMonRepar = monedaRepar.getNumeroDecimales();
					}
					List<String> aux = new ArrayList<String>();
					aux.add(rs5.getString(1));                                        // 0 bodegaEmpresa.nombre
					aux.add(rs5.getString(2));                                        // 1 guia.numero
					aux.add(rs5.getString(3));                                        // 2 guia.fecha
					aux.add(rs5.getString(4));                                        // 3 equipo.codigo
					aux.add(rs5.getString(5));                                        // 4 equipo.nombre
					aux.add(DecimalFormato.formato(rs5.getDouble(6), (long) 2));        // 5 movimiento.cantidad
					aux.add(monVtaArr);                                                // 6 listaPrecio.id_moneda
					aux.add(DecimalFormato.formato(rs5.getDouble(8), decMonVtaArr));    // 7 listaPrecio.precioVenta
					aux.add(rs5.getString(9));                                        // 8 tipoEstado.sigla
					aux.add(DecimalFormato.formato(rs5.getDouble(10), (long) 2));        // 9 estadoEquipo.cantidad
					String cantreparacionEquipo = "";
					if (!rs5.getString(13).equals("")) {
						cantreparacionEquipo = DecimalFormato.formato(rs5.getDouble(13), (long) 2);
					}
					String pretreparacionEquipo = "";
					if (!rs5.getString(15).equals("")) {
						pretreparacionEquipo = DecimalFormato.formato(rs5.getDouble(15), decMonRepar);
					}
					aux.add(rs5.getString(11));                                        // 10 tipoReparacion.sigla
					aux.add(rs5.getString(12));                                        // 11 tipoReparacion.nombre
					aux.add(cantreparacionEquipo);                                    // 12 reparacionEquipo.cantidad
					aux.add(monRepar);                                                // 13 reparacionEquipo.id_moneda
					aux.add(pretreparacionEquipo);                                    // 14 reparacionEquipo.precio
					aux.add(kgStr);                            // 15 kg unitario
					aux.add(m2Str);                            // 16 m2 unitario
					aux.add(rs5.getString(19));                                        // 17 nro ref cliente
					aux.add(rs5.getString(20));                                        // 18 nickCliente
					aux.add(rs5.getString(21));  // 19 cobraArriendo por estado la guia
					aux.add(rs5.getString(22));  // 20 movimiento.id
					aux.add(rs5.getString(23));  // 21 cobraArriendo por el tipo estado
					lista.add(aux);
				}
			}
		} catch (SQLException e) {
			String className = ReportInventarios.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (lista);
	}
	
	public static File reportInventarioEstadosPorPerExcel(String db, Map<String,String> mapDiccionario, Map<String,String> mapPermiso, List<List<String>> datos, String desde, String hasta) {
		
		File tmp = null;
try{
	tmp = TempFile.createTempFile("tmp","null");
}catch(Exception e){}
		
		try {
			String path = "formatos/excel.xlsx";
			InputStream formato = Archivos.leerArchivo(path);
            Workbook libro = WorkbookFactory.create(formato);
            formato.close();
            
            // 0 negro 1 blanco 2 rojo 3 verde 4 azul 5 amarillo 19 celeste
            CellStyle titulo = libro.createCellStyle();
            Font font = libro.createFont();
            font.setBold(true);
            font.setColor((short)4);
            font.setFontHeight((short)(14*20));
            titulo.setFont(font);
            
            CellStyle subtitulo = libro.createCellStyle();
            Font font2 = libro.createFont();
            font2.setBold(true);
            font2.setColor((short)0);
            font2.setFontHeight((short)(12*20));
            subtitulo.setFont(font2);
            
            CellStyle encabezado = libro.createCellStyle();
            encabezado.setBorderBottom(BorderStyle.THIN);
            encabezado.setBorderTop(BorderStyle.THIN);
            encabezado.setBorderRight(BorderStyle.THIN);
            encabezado.setBorderLeft(BorderStyle.THIN);
            encabezado.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            encabezado.setFillForegroundColor((short)19);
            encabezado.setAlignment(HorizontalAlignment.CENTER);
            
            CellStyle encabezado2 = libro.createCellStyle();
            encabezado2.setBorderBottom(BorderStyle.THIN);
            encabezado2.setBorderTop(BorderStyle.THIN);
            encabezado2.setBorderRight(BorderStyle.THIN);
            encabezado2.setBorderLeft(BorderStyle.THIN);
            encabezado2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            encabezado2.setFillForegroundColor((short)19);
            encabezado2.setAlignment(HorizontalAlignment.LEFT);
            
            CellStyle detalle = libro.createCellStyle();
            detalle.setBorderBottom(BorderStyle.THIN);
            detalle.setBorderTop(BorderStyle.THIN);
            detalle.setBorderRight(BorderStyle.THIN);
            detalle.setBorderLeft(BorderStyle.THIN);
            
            
            
            
            Sheet hoja1 = libro.getSheetAt(0);
            Row row = null;
            Cell cell = null;
           
            row = hoja1.createRow(1);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellValue("DETALLE ESTADOS Y REPARACIONES POR PERIODO");
		
            row = hoja1.createRow(2);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellValue("desde "+Fechas.DDMMAA(desde)+" hasta "+Fechas.DDMMAA(hasta));
			
			row = hoja1.createRow(3);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("EMPRESA: "+mapDiccionario.get("nEmpresa"));
			
			row = hoja1.createRow(4);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("FECHA: "+Fechas.hoy().getFechaStrDDMMAA());
			
			// encabezado de la tabla
			
			
			
			int posCell = 0;
			int posColl = 0;
			row = hoja1.createRow(8);
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 10*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue(mapDiccionario.get("Bodega"));

			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 4*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("Cliente");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 4*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("Nro Mov");

			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 4*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("Ref Clie");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 4*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("Fecha Guia");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 4*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("Codigo");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 10*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("Equipo");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 4*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("Cant Equipo");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 4*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("Mon Equip");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 4*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("P.U.Equipo");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 4*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("KG");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 4*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("M2");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 4*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("Estado");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 4*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("Cant Estado "+mapDiccionario.get("ARR"));
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 4*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("Sigla");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 10*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("Reparacion");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 4*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("Cant Repar");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 4*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("Mon Repar");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 4*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("P.U. Reparacion");

			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 4*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("Total Reparacion");


			if(mapPermiso.get("parametro.cobraArriendoPorEstadoEquipo")!=null && mapPermiso.get("parametro.cobraArriendoPorEstadoEquipo").equals("1")){
				posCell++; posColl++;
				hoja1.setColumnWidth(posColl, 4*1000);
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellValue("Cobra "+mapDiccionario.get("ARR"));
			}
			
		
			//INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
			InputStream x = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
            byte[] bytes = IOUtils.toByteArray(x);
            x.close();
            int pngIndex = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			Drawing draw = hoja1.createDrawingPatriarch();
			CreationHelper helper = libro.getCreationHelper();
			ClientAnchor anchor = helper.createClientAnchor();
	        //set top-left corner for the image
	        anchor.setCol1(10);
	        anchor.setRow1(1);
			Picture img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);
			
			//DETALLE DE LA TABLA
			Double granTotal = (double) 0;
			int posRow = 9;
			for(int i=0;i<datos.size();i++){
				row = hoja1.createRow(posRow);
				posCell = 0;
				posColl = 0;
				Double aux = (double)0;
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(datos.get(i).get(0));

				posCell++; posColl++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(datos.get(i).get(18));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(datos.get(i).get(1));

				posCell++; posColl++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(datos.get(i).get(17));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(Fechas.DDMMAA(datos.get(i).get(2)));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(datos.get(i).get(3));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(datos.get(i).get(4));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(datos.get(i).get(5).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(datos.get(i).get(6));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(datos.get(i).get(7).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            String aux0 = datos.get(i).get(15);
	            if(!aux0.equals("")) {
	            	aux = Double.parseDouble(aux0.replaceAll(",", ""));
	            	cell.setCellValue(aux);
	            }
	            
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux0 = datos.get(i).get(16);
	            if(!aux0.equals("")) {
	            	aux = Double.parseDouble(aux0.replaceAll(",", ""));
	            	cell.setCellValue(aux);
	            }
	            
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(datos.get(i).get(8));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(datos.get(i).get(9).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(datos.get(i).get(10));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(datos.get(i).get(11));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            String dePaso = datos.get(i).get(12);
				Double cant = (double)0;
	            if(dePaso.equals("")) {
					cell.setCellValue("");
	            }else {
	            	aux = Double.parseDouble(datos.get(i).get(12).replaceAll(",", ""));
					cant = aux;
	            	cell.setCellValue(aux);
	            }

				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(datos.get(i).get(13));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            dePaso = datos.get(i).get(14);
				Double pu = (double)0;
	            if(dePaso.equals("")) {
					cell.setCellValue("");
	            }else {
	            	aux = Double.parseDouble(datos.get(i).get(14).replaceAll(",", ""));
					pu = aux;
					cell.setCellValue(aux);
	            }

				posCell++; posColl++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(cant * pu);
				granTotal += cant * pu;

				if(mapPermiso.get("parametro.cobraArriendoPorEstadoEquipo")!=null && mapPermiso.get("parametro.cobraArriendoPorEstadoEquipo").equals("1")){
					posCell++; posColl++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					if(datos.get(i).get(21).equals("1")){
						if(datos.get(i).get(19).equals("1")){
							cell.setCellValue("checked");
						}
					}
				}

				posRow++;
			}


			// PIE

			row = hoja1.createRow(posRow);
			posCell = 0;

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue("");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue("");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue("");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue("");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue("");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue("");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue("");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue("");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue("");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue("");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue("");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue("");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue("");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue("");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue("");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue("");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue("");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue("");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue("TOTAL");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue(granTotal);

			if(mapPermiso.get("parametro.cobraArriendoPorEstadoEquipo")!=null && mapPermiso.get("parametro.cobraArriendoPorEstadoEquipo").equals("1")){
				posCell++; posColl++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
			}


			posRow = posRow + 5;
			row = hoja1.createRow(posRow);
			cell = row.createCell(1);
			Hyperlink hiper = helper.createHyperlink(HyperlinkType.URL);
			hiper.setAddress("https://www.inqsol.cl");
			cell.setHyperlink(hiper);
			cell.setCellValue("Documento generado desde MADA propiedad de INQSOL");

			// Write the output to a file tmp
			FileOutputStream fileOut = new FileOutputStream(tmp);
			libro.write(fileOut);
			fileOut.close();
			
		} catch (Exception e) {
			e.printStackTrace();
        }
	  return tmp;
	  
	}
	
	public static List<List<String>> reportInventarioPorEstadosAll(Connection con, String db, String permisoPorBodega, Long id_bodegaEmpresa) {
		List<List<String>> lista = new ArrayList<List<String>>();
		String query = """
    SELECT 
        bodegaEmpresa.nombre,
        guia.numero,
        guia.fecha,
        equipo.codigo,
        equipo.nombre,
        movimiento.cantidad,
        listaPrecio.id_moneda,
        listaPrecio.precioVenta,
        tipoEstado.sigla,
        estadoEquipo.cantidad,
        IFNULL(tipoReparacion.sigla, ''),
        IFNULL(tipoReparacion.nombre, ''),
        IFNULL(reparacionEquipo.cantidad, ''),
        IFNULL(tipoReparacion.id_moneda, ''),
        IFNULL(tipoReparacion.precio, ''),
        equipo.id,
        equipo.kg,
        equipo.m2,
        guia.numGuiaCliente,
        cliente.nickName,
        estadoEquipo.cobraArriendo,
		movimiento.id,
		tipoEstado.cobraArriendo
    FROM `%s`.estadoEquipo
    LEFT JOIN `%s`.guia ON guia.id = estadoEquipo.id_guia
    LEFT JOIN `%s`.bodegaEmpresa ON bodegaEmpresa.id = guia.id_bodegaOrigen
    LEFT JOIN `%s`.movimiento ON movimiento.id = estadoEquipo.id_movimiento
    LEFT JOIN `%s`.equipo ON equipo.id = movimiento.id_equipo
    LEFT JOIN `%s`.tipoEstado ON tipoEstado.id = estadoEquipo.id_tipoEstado
    LEFT JOIN `%s`.reparacionEquipo ON reparacionEquipo.id_estadoEquipo = estadoEquipo.id
    LEFT JOIN `%s`.tipoReparacion ON tipoReparacion.id = reparacionEquipo.id_tipoReparacion
    LEFT JOIN `%s`.listaPrecio ON listaPrecio.id_bodegaEmpresa = bodegaEmpresa.id 
        AND listaPrecio.id_equipo = equipo.id 
        AND listaPrecio.id_cotizacion = movimiento.id_cotizacion
    LEFT JOIN `%s`.cliente ON cliente.id = bodegaEmpresa.id_cliente
    WHERE bodegaEmpresa.id = ? 
        AND bodegaEmpresa.vigente = 1
        %s
    ORDER BY movimiento.id_bodegaEmpresa, guia.fecha, movimiento.id_equipo, tipoEstado.sigla
    """.formatted(db, db, db, db, db, db, db, db, db, db, permisoPorBodega);

		try (PreparedStatement smt5 = con.prepareStatement(query)){
			smt5.setLong(1, id_bodegaEmpresa);
			try (ResultSet rs5 = smt5.executeQuery()) {
				Map<Long, Moneda> mapMoneda = Moneda.mapMonedas(con, db);
				while (rs5.next()) {
					// kg por unidad
					Double kg = rs5.getDouble(17);
					String kgStr = "";
					if (kg > 0) {
						kgStr = myformatdouble2.format(kg);
					}
					// m2 por unidad
					Double m2 = rs5.getDouble(18);
					String m2Str = "";
					if (m2 > 0) {
						m2Str = myformatdouble2.format(m2);
					}
					Moneda monedaVtaArr = mapMoneda.get(rs5.getLong(7));
					String monVtaArr = "";
					Long decMonVtaArr = (long) 0;
					if (monedaVtaArr != null) {
						monVtaArr = monedaVtaArr.getNickName();
						decMonVtaArr = monedaVtaArr.getNumeroDecimales();
					}
					Moneda monedaRepar = mapMoneda.get(rs5.getLong(14));
					String monRepar = "";
					Long decMonRepar = (long) 0;
					if (monedaRepar != null) {
						monRepar = monedaRepar.getNickName();
						decMonRepar = monedaRepar.getNumeroDecimales();
					}
					List<String> aux = new ArrayList<String>();
					aux.add(rs5.getString(1));                                        // 0 bodegaEmpresa.nombre
					aux.add(rs5.getString(2));                                        // 1 guia.numero
					aux.add(rs5.getString(3));                                        // 2 guia.fecha
					aux.add(rs5.getString(4));                                        // 3 equipo.codigo
					aux.add(rs5.getString(5));                                        // 4 equipo.nombre
					aux.add(DecimalFormato.formato(rs5.getDouble(6), (long) 2));        // 5 movimiento.cantidad
					aux.add(monVtaArr);                                                // 6 listaPrecio.id_moneda
					aux.add(DecimalFormato.formato(rs5.getDouble(8), decMonVtaArr));    // 7 listaPrecio.precioVenta
					aux.add(rs5.getString(9));                                        // 8 tipoEstado.sigla
					aux.add(DecimalFormato.formato(rs5.getDouble(10), (long) 2));        // 9 estadoEquipo.cantidad
					String cantreparacionEquipo = "";
					if (!rs5.getString(13).equals("")) {
						cantreparacionEquipo = DecimalFormato.formato(rs5.getDouble(13), (long) 2);
					}
					String pretreparacionEquipo = "";
					if (!rs5.getString(15).equals("")) {
						pretreparacionEquipo = DecimalFormato.formato(rs5.getDouble(15), decMonRepar);
					}
					aux.add(rs5.getString(11));                                        // 10 tipoReparacion.sigla
					aux.add(rs5.getString(12));                                        // 11 tipoReparacion.nombre
					aux.add(cantreparacionEquipo);                                    // 12 reparacionEquipo.cantidad
					aux.add(monRepar);                                                // 13 reparacionEquipo.id_moneda
					aux.add(pretreparacionEquipo);                                    // 14 reparacionEquipo.precio
					aux.add(kgStr);                                        // 15 kg unitario
					aux.add(m2Str);                                        // 16 m2 unitario
					aux.add(rs5.getString(19));                            // 17 nro ref cliente
					aux.add(rs5.getString(20));                                        // 18 nickCliente

					aux.add(rs5.getString(21));  // 19 cobraArriendo por estado la guia
					aux.add(rs5.getString(22));  // 20 movimiento.id
					aux.add(rs5.getString(23));  // 21 cobraArriendo por el tipo estado

					if (rs5.getDouble(10) > 0) {
						lista.add(aux);
					}
				}
			}
		} catch (SQLException e) {
			String className = ReportInventarios.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (lista);
	}
	
	public static File reporteEstadosAllExcel(String db, Map<String,String> mapDiccionario, List<List<String>> datos, BodegaEmpresa bodegaEmpresa) {
		
		File tmp = null;
try{
	tmp = TempFile.createTempFile("tmp","null");
}catch(Exception e){}
		
		try {
			String path = "formatos/excel.xlsx";
			InputStream formato = Archivos.leerArchivo(path);
            Workbook libro = WorkbookFactory.create(formato);
            formato.close();
            
            // 0 negro 1 blanco 2 rojo 3 verde 4 azul 5 amarillo 19 celeste
            CellStyle titulo = libro.createCellStyle();
            Font font = libro.createFont();
            font.setBold(true);
            font.setColor((short)4);
            font.setFontHeight((short)(14*20));
            titulo.setFont(font);
            
            CellStyle subtitulo = libro.createCellStyle();
            Font font2 = libro.createFont();
            font2.setBold(true);
            font2.setColor((short)0);
            font2.setFontHeight((short)(12*20));
            subtitulo.setFont(font2);
            
            CellStyle encabezado = libro.createCellStyle();
            encabezado.setBorderBottom(BorderStyle.THIN);
            encabezado.setBorderTop(BorderStyle.THIN);
            encabezado.setBorderRight(BorderStyle.THIN);
            encabezado.setBorderLeft(BorderStyle.THIN);
            encabezado.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            encabezado.setFillForegroundColor((short)19);
            encabezado.setAlignment(HorizontalAlignment.CENTER);
            
            CellStyle encabezado2 = libro.createCellStyle();
            encabezado2.setBorderBottom(BorderStyle.THIN);
            encabezado2.setBorderTop(BorderStyle.THIN);
            encabezado2.setBorderRight(BorderStyle.THIN);
            encabezado2.setBorderLeft(BorderStyle.THIN);
            encabezado2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            encabezado2.setFillForegroundColor((short)19);
            encabezado2.setAlignment(HorizontalAlignment.LEFT);
            
            CellStyle detalle = libro.createCellStyle();
            detalle.setBorderBottom(BorderStyle.THIN);
            detalle.setBorderTop(BorderStyle.THIN);
            detalle.setBorderRight(BorderStyle.THIN);
            detalle.setBorderLeft(BorderStyle.THIN);
            
            
            
            
            Sheet hoja1 = libro.getSheetAt(0);
            Row row = null;
            Cell cell = null;
            
            row = hoja1.createRow(1);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellValue("TODO EL DETALLE ("+mapDiccionario.get("Bodega")+"s Vigentes)");
		
            row = hoja1.createRow(2);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellValue(mapDiccionario.get("BODEGA")+"/PROYECTO: "+bodegaEmpresa.getNombre().toUpperCase());
			
			row = hoja1.createRow(3);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("EMPRESA: "+mapDiccionario.get("nEmpresa"));
			
			row = hoja1.createRow(4);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("FECHA: "+Fechas.hoy().getFechaStrDDMMAA());
			
			// encabezado de la tabla
			
			
			
			int posCell = 0;
			int posColl = 0;
			row = hoja1.createRow(8);
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 10*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue(mapDiccionario.get("Bodega"));
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 4*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("Cliente");

			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 4*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("Nro Mov");

			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 4*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("Ref Clie");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 4*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("Fecha Guia");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 4*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("Codigo");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 10*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("Equipo");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 4*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("Cant Equipo");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 4*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("Mon Equip");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 4*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("P.U.Equipo");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 4*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("KG");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 4*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("M2");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 4*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("Estado");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 4*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("Cant Estado "+mapDiccionario.get("ARR"));
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 4*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("Sigla");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 10*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("Reparacion");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 4*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("Cant Repar");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 4*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("Mon Repar");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 4*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("P.U. Reparacion");

			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 4*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("Total Reparacion");
		
			//INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
			InputStream x = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
            byte[] bytes = IOUtils.toByteArray(x);
            x.close();
            int pngIndex = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			Drawing draw = hoja1.createDrawingPatriarch();
			CreationHelper helper = libro.getCreationHelper();
			ClientAnchor anchor = helper.createClientAnchor();
	        //set top-left corner for the image
	        anchor.setCol1(10);
	        anchor.setRow1(1);
			Picture img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);
			
			//DETALLE DE LA TABLA
			int posRow = 9;
			Double granTotal = (double) 0;
			for(int i=0;i<datos.size();i++){
				row = hoja1.createRow(posRow);
				posCell = 0;
				posColl = 0;
				Double aux = (double)0;
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(datos.get(i).get(0));

				posCell++; posColl++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(datos.get(i).get(18));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(datos.get(i).get(1));

				posCell++; posColl++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(datos.get(i).get(17));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(Fechas.DDMMAA(datos.get(i).get(2)));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(datos.get(i).get(3));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(datos.get(i).get(4));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(datos.get(i).get(5).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(datos.get(i).get(6));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(datos.get(i).get(7).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            String aux0 = datos.get(i).get(15);
	            if(!aux0.equals("")) {
	            	aux = Double.parseDouble(aux0.replaceAll(",", ""));
	            	cell.setCellValue(aux);
	            }
	            
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux0 = datos.get(i).get(16);
	            if(!aux0.equals("")) {
	            	aux = Double.parseDouble(aux0.replaceAll(",", ""));
	            	cell.setCellValue(aux);
	            }
	            
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(datos.get(i).get(8));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(datos.get(i).get(9).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(datos.get(i).get(10));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(datos.get(i).get(11));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            String dePaso = datos.get(i).get(12);
				Double cant = (double)0;
	            if(dePaso.equals("")) {
					cell.setCellValue("");
	            }else {
	            	aux = Double.parseDouble(datos.get(i).get(12).replaceAll(",", ""));
					cant = aux;
	            	cell.setCellValue(aux);
	            }
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(datos.get(i).get(13));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            dePaso = datos.get(i).get(14);
				Double pu = (double)0;
	            if(dePaso.equals("")) {
					cell.setCellValue("");
	            }else {
	            	aux = Double.parseDouble(datos.get(i).get(14).replaceAll(",", ""));
					pu = aux;
					cell.setCellValue(aux);
	            }

				posCell++; posColl++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(cant * pu);
				granTotal += cant * pu;

				posRow++;
			}


			// PIE

			row = hoja1.createRow(posRow);
			posCell = 0;

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue("");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue("");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue("");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue("");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue("");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue("");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue("");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue("");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue("");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue("");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue("");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue("");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue("");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue("");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue("");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue("");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue("");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue("");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue("TOTAL");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue(granTotal);


			
			posRow = posRow + 5;
			row = hoja1.createRow(posRow);
			cell = row.createCell(1);
			Hyperlink hiper = helper.createHyperlink(HyperlinkType.URL);
			hiper.setAddress("https://www.inqsol.cl");
			cell.setHyperlink(hiper);
			cell.setCellValue("Documento generado desde MADA propiedad de INQSOL");
			
			// Write the output to a file tmp
			FileOutputStream fileOut = new FileOutputStream(tmp);
			libro.write(fileOut);
			fileOut.close();
			
		} catch (Exception e) {
			e.printStackTrace();
        }
	  return tmp;
	  
	}
	
	public static List<List<String>> reportInventarioPorEstadosPorNroGuia(Connection con, String db, String permisoPorBodega, Long id_guia) {
		List<List<String>> lista = new ArrayList<List<String>>();
		try {
			PreparedStatement smt5 = con
					.prepareStatement(" select "
							+ " bodegaEmpresa.nombre, "
							+ " guia.numero, "
							+ " guia.fecha, "
							+ " equipo.codigo, "
							+ " equipo.nombre, "
							+ " movimiento.cantidad, "
							+ " listaPrecio.id_moneda, "
							+ " listaPrecio.precioVenta, "
							+ " tipoEstado.sigla, "
							+ " estadoEquipo.cantidad, "
							+ " ifnull(tipoReparacion.sigla,''), "
							+ " ifnull(tipoReparacion.nombre,''), "
							+ " ifnull(reparacionEquipo.cantidad,0), "
							+ " ifnull(tipoReparacion.id_moneda,1), "
							+ " ifnull(tipoReparacion.precio,0), "
							+ " ifnull(estadoEquipo.id_tipoEstado,0) "
							+ " from `"+db+"`.estadoEquipo "
							+ " left join `"+db+"`.guia on guia.id = estadoEquipo.id_guia "
							+ " left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = guia.id_bodegaOrigen "
							+ " left join `"+db+"`.movimiento on movimiento.id = estadoEquipo.id_movimiento "
							+ " left join `"+db+"`.equipo on equipo.id = movimiento.id_equipo "
							+ " left join `"+db+"`.tipoEstado on tipoEstado.id = estadoEquipo.id_tipoEstado "
							+ " left join `"+db+"`.reparacionEquipo on reparacionEquipo.id_estadoEquipo = estadoEquipo.id "
							+ " left join `"+db+"`.tipoReparacion on tipoReparacion.id = reparacionEquipo.id_tipoReparacion "
							+ " left join `"+db+"`.listaPrecio on listaPrecio.id_bodegaEmpresa = bodegaEmpresa.id and listaPrecio.id_equipo = equipo.id and listaPrecio.id_cotizacion = movimiento.id_cotizacion "
							+ " where guia.id=? "
							+   permisoPorBodega 
							+ " order by movimiento.id_bodegaEmpresa, guia.fecha, movimiento.id_equipo, tipoEstado.sigla, tipoReparacion.sigla;");
			smt5.setLong(1, id_guia);
			
			ResultSet rs5 = smt5.executeQuery();
			
			Map<Long,Moneda> mapMoneda = Moneda.mapMonedas(con, db);
			
			while (rs5.next()) {

				Moneda monedaVtaArr = mapMoneda.get(rs5.getLong(7));
				String monVtaArr = "";
				Long decMonVtaArr = (long)0;
				if(monedaVtaArr != null) {
					monVtaArr = monedaVtaArr.getNickName();
					decMonVtaArr = monedaVtaArr.getNumeroDecimales();
				}
				Moneda monedaRepar = mapMoneda.get(rs5.getLong(14));
				String monRepar = "";
				Long decMonRepar = (long)0;
				if(monedaRepar != null) {
					monRepar = monedaRepar.getNickName();
					decMonRepar = monedaRepar.getNumeroDecimales();
				}

				List<String> aux = new ArrayList<String>();
				aux.add(rs5.getString(1));  										// 0 bodegaEmpresa.nombre
				aux.add(rs5.getString(2));  										// 1 guia.numero
				aux.add(rs5.getString(3));  										// 2 guia.fecha
				aux.add(rs5.getString(4));  										// 3 equipo.codigo
				aux.add(rs5.getString(5));  										// 4 equipo.nombre
				aux.add(DecimalFormato.formato(rs5.getDouble(6), (long)0));  		// 5 movimiento.cantidad
				aux.add(monVtaArr);  												// 6 listaPrecio.moneda
				aux.add(DecimalFormato.formato(rs5.getDouble(8), decMonVtaArr));  	// 7 listaPrecio.precioVenta
				aux.add(rs5.getString(9));  										// 8 tipoEstado.sigla
				aux.add(DecimalFormato.formato(rs5.getDouble(10), (long)0));  		// 9 estadoEquipo.cantidad
				
				String cantreparacionEquipo = "";
				if(!rs5.getString(13).equals("")) {
					cantreparacionEquipo = DecimalFormato.formato(rs5.getDouble(13), (long)0); 
				}
				String pretreparacionEquipo = "";
				if(!rs5.getString(15).equals("")) {
					pretreparacionEquipo = DecimalFormato.formato(rs5.getDouble(15), decMonRepar); 
				}
				
				
				aux.add(rs5.getString(11));  										// 10 tipoReparacion.sigla
				aux.add(rs5.getString(12));  										// 11 tipoReparacion.nombre
				aux.add(cantreparacionEquipo);  									// 12 reparacionEquipo.cantidad
				aux.add(monRepar);  												// 13 reparacionEquipo.moneda
				aux.add(pretreparacionEquipo);  									// 14 reparacionEquipo.precio
				aux.add(rs5.getString(16));  									// 15 id_tipoEstado
				lista.add(aux);
			}
			rs5.close();
			smt5.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
}
	
	
	
