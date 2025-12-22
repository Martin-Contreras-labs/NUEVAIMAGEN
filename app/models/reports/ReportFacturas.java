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

import models.utilities.DecimalFormato;

import org.apache.commons.io.IOUtils;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.util.TempFile;

import models.calculo.Calc_BodegaEmpresa;
import models.calculo.Calc_Precio;
import models.calculo.Inventarios;
import models.calculo.ModCalc_GuiasPer;
import models.calculo.ModCalc_InvInicial;
import models.calculo.ModeloCalculo;
import models.tables.AjustesEP;
import models.tables.BodegaEmpresa;
import models.tables.Cliente;
import models.tables.Cotizacion;
import models.tables.Equipo;
import models.tables.Grupo;
import models.tables.Guia;
import models.tables.Moneda;
import models.tables.Proyecto;
import models.utilities.Archivos;
import models.utilities.Fechas;

public class ReportFacturas {
	
	public Map<String, Long> mapGuiasConAjuste;
	public List<ModCalc_InvInicial> resumenInvInicial;
	
	public ReportFacturas(Map<String, Long> mapGuiasConAjuste, List<ModCalc_InvInicial> resumenInvInicial) {
		super();
		this.mapGuiasConAjuste = mapGuiasConAjuste;
		this.resumenInvInicial = resumenInvInicial;
	}

	public ReportFacturas() {
		super();
	}

	static DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00",symbols);
	static DecimalFormat myformatint = new DecimalFormat("#,##0",symbols);
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00",symbols);
	static DecimalFormat myformatdouble4 = new DecimalFormat("#,##0.0000",symbols);
	static SimpleDateFormat myformatfecha = new SimpleDateFormat("dd-MM-yyyy");
	
	static DecimalFormat myformatMonedaPrincipal = new DecimalFormat("#,##0",symbols);
	static DecimalFormat myformatMoneda = new DecimalFormat("#,##0",symbols);
	
	
	
	
	public static List<List<String>> reportFacturaProyecto(List<ModeloCalculo> listado, Map<Long,List<String>> mapBodega) {
		List<List<String>> lista = new ArrayList<List<String>>();
		
		for(int i=0; i<listado.size(); i++) {
			List<String> aux = mapBodega.get(listado.get(i).id_bodegaEmpresa);
			if(aux != null) lista.add(aux);
		}
		
		//ORDENA LA LISTA por nombre de bodega
		for(int j=0;j<lista.size();j++) {
            for(int i=0;i<lista.size()-j;i++) {
                if (i+1!=lista.size()&&lista.get(i).get(5).compareToIgnoreCase(lista.get(i+1).get(5))>0) {
                    List<String> aux;
                    aux=lista.get(i);
                    lista.set(i,lista.get(i+1));
                    lista.set(i+1, aux);
                }
            }
        }
		return (lista);
	}
	
	public static List<List<String>> reportFacturaProyectoALL(Connection con, String db) {
		List<List<String>> lista = new ArrayList<List<String>>();
		
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" bodegaEmpresa.esInterna, " +
							" movimiento.id_bodegaEmpresa "+
							" from `"+db+"`.movimiento   " +
							" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa  " +
							" where bodegaEmpresa.esInterna = 2 "+
							" group by movimiento.id_bodegaEmpresa  " +
							" order by bodegaEmpresa.esInterna, bodegaEmpresa.nombre;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				List<String> aux = new ArrayList<String>();
				aux.add(rs.getString(1)); 	// 0 es cliente interno
				aux.add(rs.getString(2));  	// 1 idbodega empresa
				lista.add(aux);
			}
			
			rs.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<List<String>> reportEstadoInicial10(String db, Long id_bodegaEmpresa, String desdeAAMMDD, String hastaAAMMDD, Map<String, Double> mapFijaTasas, Map<Long,Double> mapTasas, 
			List<Long> listIdBodegaEmpresa, Map<Long,Calc_BodegaEmpresa> mapBodegaEmpresa, Map<String,Calc_Precio> mapPrecios, Map<Long,Calc_Precio> mapMaestroPrecios,
			List<Long> listIdGuia_fechaCorte, List<Inventarios> inventario,
			Map<String,String> mapFecha_primera_guia, Map<Long,Cotizacion> mapAllConfirmadas, Map<Long,String> mapMoneda, Map<Long,Equipo> mapAllEquipos, Map<Long,Long> dec) {
		
		List<List<String>> lista = new ArrayList<List<String>>();
		
		
		ReportFacturas reporte = ModCalc_InvInicial.resumenInvInicial(db,desdeAAMMDD, hastaAAMMDD, mapFijaTasas, mapTasas, listIdBodegaEmpresa, mapBodegaEmpresa, mapPrecios, mapMaestroPrecios,
				listIdGuia_fechaCorte, inventario);
		List<ModCalc_InvInicial> resumenInvInicialAll = reporte.resumenInvInicial;
		Map<String,Long> mapGuiasConAjuste = reporte.mapGuiasConAjuste;
		
		
		List<ModCalc_InvInicial> resumenInvInicial = new ArrayList<ModCalc_InvInicial>();
		for(ModCalc_InvInicial x: resumenInvInicialAll) {
			if((long)x.id_bodegaEmpresa==(long)id_bodegaEmpresa) {
				resumenInvInicial.add(x);
			}
		}
		
		
		switch(dec.get((long)1).toString()) {
		 case "0": myformatMonedaPrincipal = new DecimalFormat("#,##0",symbols); break;
		 case "2": myformatMonedaPrincipal = new DecimalFormat("#,##0.00",symbols); break;
		 case "4": myformatMonedaPrincipal = new DecimalFormat("#,##0.0000",symbols); break;
		 case "6": myformatMonedaPrincipal = new DecimalFormat("#,##0.000000",symbols); break;
		 default:  break;
		}
		
		for(int i=0; i<resumenInvInicial.size(); i++) {
			List<String> aux = new ArrayList<String>();
			String keyPrecio = resumenInvInicial.get(i).id_bodegaEmpresa.toString()+"_"+resumenInvInicial.get(i).id_equipo.toString()+"_"+resumenInvInicial.get(i).id_cotizacion.toString();
			
			Calc_Precio precios = mapPrecios.get(keyPrecio);
			Double precioReposicion = (double) 0; 
			Double precioArriendo_dia = (double) 0;
			Double tasaCambio = (double) 0;
			Long id_moneda = (long) 1;
			
			
			Map<Long,Double> tasas = new HashMap<Long,Double>();
			for (Map.Entry<Long, Double> entry : mapTasas.entrySet()) {
    	  		Long k = entry.getKey();
    	  		Double v= entry.getValue();
				Double aux2 = mapFijaTasas.get(resumenInvInicial.get(i).id_bodegaEmpresa + "_" +k);
				if(aux2 != null) {
					tasas.put(k, aux2);
				}else {
					tasas.put(k, v);
				}
			}
			
			if(precios!=null) {
				precioReposicion = precios.precioReposicion;
				precioArriendo_dia = precios.precioArriendo_dia;
				tasaCambio = tasas.get(precios.id_moneda); if(tasaCambio==null) tasaCambio = (double) 1;
				id_moneda = precios.id_moneda;
			}
			String fecha_primera_guia = mapFecha_primera_guia.get(resumenInvInicial.get(i).id_equipo.toString()+"_"+resumenInvInicial.get(i).id_bodegaEmpresa+"_"+resumenInvInicial.get(i).id_cotizacion.toString());
			if(fecha_primera_guia==null) {
				fecha_primera_guia = "";
			}
			
			switch(dec.get(id_moneda).toString()) {
			 case "0": myformatMoneda = new DecimalFormat("#,##0",symbols); break;
			 case "2": myformatMoneda = new DecimalFormat("#,##0.00",symbols); break;
			 case "4": myformatMoneda = new DecimalFormat("#,##0.0000",symbols); break;
			 case "6": myformatMoneda = new DecimalFormat("#,##0.000000",symbols); break;
			 default:  break;
			}
			
			Cotizacion cotizacion = mapAllConfirmadas.get(resumenInvInicial.get(i).id_cotizacion);
			String numeroCotizacion = "0";
			Double cotiDctoArr = (double)0, cotiDctoVta = (double)0;
			if(cotizacion!=null) {
				numeroCotizacion = cotizacion.numero.toString();
				cotiDctoArr = cotizacion.dctoArriendo;
				cotiDctoVta = cotizacion.dctoVenta;
			}
			
			String moneda = mapMoneda.get(id_moneda);
			
			Equipo equipo = mapAllEquipos.get(resumenInvInicial.get(i).id_equipo);
			String codEquipo = "", nomEquipo = "", unEquipo = "", grupo = "";
			if(equipo!=null) {
				codEquipo = equipo.codigo;
				nomEquipo = equipo.nombre;
				unEquipo = equipo.unidad;
				grupo = equipo.grupo;
			}
			
			
			String keyAjuste = resumenInvInicial.get(i).id_bodegaEmpresa+"_"+resumenInvInicial.get(i).id_equipo+"_"+resumenInvInicial.get(i).id_cotizacion;
			Long diasAjuste = mapGuiasConAjuste.get(keyAjuste);
			String ajusteDias = "";
			if(diasAjuste != null) {
				ajusteDias = "(*) Considera descuento por saldo de días de gracia pendientes";
			}
			
			
			aux.add(resumenInvInicial.get(i).id_bodegaEmpresa.toString()); 		// 0 idBodegaEmpresa
			aux.add(resumenInvInicial.get(i).id_equipo.toString()); 			// 1 idEquipo
			aux.add("0"); 														// 2 idTipoMovimiento
			aux.add("0"); 														// 3 idGuia
			aux.add("0"); 														// 4 idUnidad
			aux.add("0"); 														// 5 idUnidadTiempo
			aux.add(id_moneda.toString()); 										// 6 idMoneda
			aux.add(grupo); 													// 7 nombre de grupo
			aux.add(numeroCotizacion); 											// 8 numero cotizacion
			aux.add(codEquipo); 												// 9 codigo equipo
			aux.add(nomEquipo + " (ARRIENDO)"+ajusteDias); 								// 10 nombre equipo
			aux.add(unEquipo); 													// 11 unidad
			aux.add(myformatdouble2.format(resumenInvInicial.get(i).cantidad)); 	// 12 cantidad
			aux.add(moneda); 													// 13 moneda
			aux.add(myformatMoneda.format(precioReposicion)); 					// 14 precioVenta
			if(db.equals("madaHohe")){
				aux.add(myformatdouble.format(precioArriendo_dia)); 			// 15 precioArriendo por día
			}else {
				aux.add(myformatMoneda.format(precioArriendo_dia)); 			// 15 precioArriendo por día
			}
			aux.add(myformatint.format(resumenInvInicial.get(i).dias));							// 16 cantidad de dias a cobrar
			aux.add(myformatdouble2.format(resumenInvInicial.get(i).tasaCambio));				// 17 tasa de cambio
			aux.add(myformatMonedaPrincipal.format(resumenInvInicial.get(i).totalArriendo));		// 18 total arriendo en pesos
			aux.add("0");																		// 19 total venta en pesos
			aux.add(resumenInvInicial.get(i).esVenta.toString()); 								// 20 es venta (1)
			aux.add(Fechas.DDMMAA(fecha_primera_guia)); 						// 21 fecha primera guia
			aux.add("0"); 														// 22 cfi en moneda original
			aux.add("0"); 														// 23 cfi en pesos
			aux.add(myformatdouble2.format(cotiDctoArr*100)+" %");  			//24 % dcto arriendo
			aux.add(myformatdouble2.format(cotiDctoVta*100)+" %"); 				//25 % dcto venta
			lista.add(aux);
		}
		
		//ORDENA LA LISTA
			for(int j=0;j<lista.size();j++) {
	            for(int i=0;i<lista.size()-j;i++) {
	            	if(i+1 != lista.size()) {
	            		String A = lista.get(i).get(7) + "_" + lista.get(i).get(10);
		            	String B = lista.get(i+1).get(7) + "_" + lista.get(i+1).get(10);
		                if (A.compareToIgnoreCase(B) > 0) {
		                    List<String> aux;
		                    aux=lista.get(i);
		                    lista.set(i,lista.get(i+1));
		                    lista.set(i+1, aux);
		                }
	            	}
	            }
	        }
		
		return(lista);
	}
	
	public static List<List<String>> resumenTotalesPorProyecto(List<ModeloCalculo> listado, Map<Long,Long> dec) {
		List<List<String>> lista = new ArrayList<List<String>>();
		
		switch(dec.get((long) 1).toString()) {
		 case "0": myformatMonedaPrincipal = new DecimalFormat("#,##0",symbols); break;
		 case "2": myformatMonedaPrincipal = new DecimalFormat("#,##0.00",symbols); break;
		 case "4": myformatMonedaPrincipal = new DecimalFormat("#,##0.0000",symbols); break;
		 case "6": myformatMonedaPrincipal = new DecimalFormat("#,##0.000000",symbols); break;
		 default:  break;
		}
		Double totalArriendo = (double) 0, totalVenta = (double) 0, totalCfi = (double) 0, totalTotal = (double) 0, ajusteArriendo = (double) 0, ajusteVenta = (double) 0;
		for(int i=0; i<listado.size(); i++) {
			List<String> aux = new ArrayList<String>();
			aux.add(listado.get(i).id_bodegaEmpresa.toString());
			aux.add(myformatMonedaPrincipal.format(listado.get(i).totalArriendoSinAjuste));
			aux.add(myformatMonedaPrincipal.format(listado.get(i).totalVentaSinAjuste));
			aux.add(myformatMonedaPrincipal.format(listado.get(i).totalCfi));
			aux.add(myformatMonedaPrincipal.format(listado.get(i).totalTotal));
			
			aux.add(myformatMonedaPrincipal.format(listado.get(i).ajusteArriendo));
			aux.add(myformatMonedaPrincipal.format(listado.get(i).ajusteVenta));
			
			lista.add(aux);
			totalArriendo += listado.get(i).totalArriendoSinAjuste;
			totalVenta += listado.get(i).totalVentaSinAjuste;
			totalCfi += listado.get(i).totalCfi;
			totalTotal += listado.get(i).totalTotal;
			
			ajusteArriendo += listado.get(i).ajusteArriendo;
			ajusteVenta += listado.get(i).ajusteVenta;
			
		}
		List<String> aux = new ArrayList<String>();
		aux.add("SUBTOTAL");
		aux.add(myformatMonedaPrincipal.format(totalArriendo));
		aux.add(myformatMonedaPrincipal.format(totalVenta));
		aux.add(myformatMonedaPrincipal.format(totalCfi));
		aux.add(myformatMonedaPrincipal.format(totalTotal));
		
		aux.add(myformatMonedaPrincipal.format(ajusteArriendo));
		aux.add(myformatMonedaPrincipal.format(ajusteVenta));
		
		lista.add(aux);
		List<String> aux2 = new ArrayList<String>();
		aux2.add("TOTAL");
		aux2.add("");
		aux2.add(myformatMonedaPrincipal.format(totalArriendo+totalVenta+ajusteArriendo+ajusteVenta));
		aux2.add(myformatMonedaPrincipal.format(totalArriendo+totalVenta+totalCfi+ajusteArriendo+ajusteVenta));
		aux2.add("");
		lista.add(aux2);
		return(lista);
	}
	
	public static List<List<String>> reportEstadoResumen10(String db, List<List<String>> inicioPer, List<List<String>> guiasPer,
			Long id_bodegaEmpresa, String desdeAAMMDD, String hastaAAMMDD, Map<String, Double> mapFijaTasas, Map<Long,Double> tasas,  
			List<Long> listIdBodegaEmpresa, Map<Long,Calc_BodegaEmpresa> mapBodegaEmpresa, Map<String,Calc_Precio> mapPrecios, Map<Long,Calc_Precio> mapMaestroPrecios,
			List<Long> listIdGuia_entreFechas,  Map<String,String> mapPermanencias, Map<Long,Long> dec, Map<Long,Cotizacion> mapCotizaciones, 
			Map<Long,Equipo> mapEquipo, Map<Long,String> mapMoneda, List<Inventarios> guiasPeriodo) {

		
		List<List<String>> lista = new ArrayList<List<String>>();
		
		for(int i=0;i<inicioPer.size();i++){
			if(inicioPer.get(i).get(0).equals(id_bodegaEmpresa.toString()) || id_bodegaEmpresa.toString().equals("NoAplica")) {
				List<String> aux = new ArrayList<String>();
				aux.add(inicioPer.get(i).get(7)); // 0 nombre de grupo
				aux.add(inicioPer.get(i).get(18)); // 1 total arriendo en pesos
				aux.add(inicioPer.get(i).get(19)); // 2 total venta en pesos
				aux.add(inicioPer.get(i).get(23)); // 3 total cfi en pesos
				aux.add(inicioPer.get(i).get(1));
				aux.add("aqui");
				lista.add(aux);
			}
		}
		
		Map<String,List<List<String>>> mapDetalle = ReportFacturas.mapReportPorGuia10(db, id_bodegaEmpresa, desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, mapBodegaEmpresa, mapPrecios, mapMaestroPrecios,
				guiasPeriodo, mapPermanencias, dec, mapCotizaciones, mapEquipo, mapMoneda);
			
		
		
		for(int i=0;i<guiasPer.size();i++){
			List<List<String>> detGuia = mapDetalle.get(guiasPer.get(i).get(8));
			
			for(int j=0;detGuia!=null && j<detGuia.size();j++){
				
				if(detGuia.get(j).get(1).equals(id_bodegaEmpresa.toString()) || id_bodegaEmpresa.toString().equals("NoAplica")) {
					List<String> aux = new ArrayList<String>();
					aux.add(detGuia.get(j).get(8)); // 0 nombre de grupo
					aux.add(detGuia.get(j).get(19)); // 1 total arriendo en pesos con recargos
					aux.add(detGuia.get(j).get(20)); // 2 total venta en pesos
					aux.add(detGuia.get(j).get(23)); // 3 total cfi en pesos
					aux.add(detGuia.get(j).get(4));
					aux.add("guia");
					aux.add(detGuia.get(j).get(1));
					lista.add(aux);
				}
				
			}
		}
		
		//ORDENA LA LISTA
		List<List<String>> listaResumen = new ArrayList<List<String>>();
		for(int j=0;j<lista.size();j++) {
            for(int i=0;i<lista.size()-j;i++) {
                if (i+1!=lista.size()&&lista.get(i).get(0).compareToIgnoreCase(lista.get(i+1).get(0))>0) {
                    List<String> aux;
                    aux=lista.get(i);
                    lista.set(i,lista.get(i+1));
                    lista.set(i+1, aux);
                }
            }
        }
		
		switch(dec.get((long) 1).toString()) {
		 case "0": myformatMonedaPrincipal = new DecimalFormat("#,##0",symbols); break;
		 case "2": myformatMonedaPrincipal = new DecimalFormat("#,##0.00",symbols); break;
		 case "4": myformatMonedaPrincipal = new DecimalFormat("#,##0.0000",symbols); break;
		 case "6": myformatMonedaPrincipal = new DecimalFormat("#,##0.000000",symbols); break;
		 default:  break;
		}
		
		Double sumaArriendo =(double) 0;
		Double sumaCompra =(double) 0;
		Double sumaCfi =(double) 0;
		String grupo= "";
		for(int j=0;j<lista.size();j++) {
			grupo = lista.get(j).get(0);
			try {sumaArriendo = sumaArriendo + myformatMonedaPrincipal.parse(lista.get(j).get(1).trim()).doubleValue();} catch (ParseException e) {}
			try {sumaCompra = sumaCompra + myformatMonedaPrincipal.parse(lista.get(j).get(2).trim()).doubleValue();} catch (ParseException e) {}
			try {sumaCfi = sumaCfi + myformatMonedaPrincipal.parse(lista.get(j).get(3).trim()).doubleValue();} catch (ParseException e) {}
			if(j+1!=lista.size()&&lista.get(j).get(0).equals(lista.get(j+1).get(0))) {
            }else{
            	List<String> aux = new ArrayList<String>();
                aux.add(grupo);
                aux.add(myformatMonedaPrincipal.format(sumaArriendo));
                aux.add(myformatMonedaPrincipal.format(sumaCompra));
                aux.add(myformatMonedaPrincipal.format(sumaCfi));
                listaResumen.add(aux);
                sumaArriendo=(double)0;
                sumaCompra=(double)0;
                sumaCfi=(double)0;
            }
        }
		return(listaResumen);
	}
	
	public static List<List<String>> reportListGuiasEntreFechas(Connection con, String db, Long id_bodegaEmpresa, String fechaDesde, String fechaHasta) {
		List<List<String>> lista = new ArrayList<List<String>>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select  distinct " +
							" movimiento.id_bodegaEmpresa, " +
							" movimiento.id_guia, " +
							" guia.numero,   " +
							" guia.fecha, " +
							" max(movimiento.esVenta), " +
							" ifnull(guia.numGuiaCliente,''), " +
							" movimiento.id_tipoMovimiento, " +
							" tipoMovimiento.nombre, " +
							" guia.observaciones, " +
							" ifnull(guia.fechaIniTerGuia,guia.fecha) " +
							" from `"+db+"`.movimiento   " +
							" left join `"+db+"`.guia on guia.id = movimiento.id_guia    " +
							" left join `"+db+"`.equipo on equipo.id = movimiento.id_equipo " +
							" left join `"+db+"`.tipoMovimiento on tipoMovimiento.id = movimiento.id_tipoMovimiento " +
							" where movimiento.id_bodegaEmpresa=? "+
							" and (guia.fecha between ? and ? or (fecha < ? and ifnull(fechaIniTerGuia,fecha) between  ? and ?)) "+
							" group by movimiento.id_guia order by guia.fecha;");
			smt.setLong(1, id_bodegaEmpresa);
			smt.setString(2, fechaDesde.trim());
			smt.setString(3, fechaHasta.trim());
			smt.setString(4, fechaDesde.trim());
			smt.setString(5, fechaDesde.trim());
			smt.setString(6, fechaHasta.trim());
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				List<String> aux = new ArrayList<String>();
				aux.add(rs.getString(1)); 						// 0 idBodegaEmpresa
				aux.add(rs.getString(2)); 						// 1 idGuia
				aux.add(rs.getString(3)); 						// 2 numero guia
				aux.add(myformatfecha.format(rs.getDate(4))); 	// 3 fecha guia
				aux.add(rs.getString(5)); 						// 4 esVenta
				aux.add(rs.getString(6)); 						// 5 numero guia del cliente
				aux.add(rs.getString(7)); 						// 6 ifTipo-Guia
				aux.add(rs.getString(8)); 						// 7 nombre del tipo de guia
				aux.add(rs.getString(1)+"_"+rs.getString(2)); 	// 8 idBodega_idGuia
				aux.add(rs.getString(9)); 						// 9 observaciones
				aux.add(myformatfecha.format(rs.getDate(10))); 	// 10 fecha inicio termino guia
				lista.add(aux);
			}
			rs.close();
			smt.close();




		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return(lista);
	}
	
	public static Map<String,List<List<String>>> mapReportPorGuia10(String db, Long id_bodegaEmpresa, String desdeAAMMDD,String hastaAAMMDD, Map<String, Double> mapFijaTasas, Map<Long,Double> tasas,
			Map<Long,Calc_BodegaEmpresa> mapBodegaEmpresa, Map<String,Calc_Precio> mapPrecios, Map<Long,Calc_Precio> mapMaestroPrecios, 
			List<Inventarios> guiasPer, Map<String,String> mapPermanencias, Map<Long,Long> dec, Map<Long,Cotizacion> mapCotizaciones, 
			Map<Long,Equipo> mapEquipo, Map<Long,String> mapMoneda) {
		Map<String,List<List<String>>> map = new HashMap<String,List<List<String>>>();
		List<ModCalc_GuiasPer> guiasPerAll = ModCalc_GuiasPer.resumenGuiasPer(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, mapBodegaEmpresa, mapPrecios, mapMaestroPrecios,
				guiasPer, mapPermanencias);
		List<ModCalc_GuiasPer> guiasPeriodo = new ArrayList<ModCalc_GuiasPer>();
		for(ModCalc_GuiasPer x: guiasPerAll) {
			if((long)x.id_bodegaEmpresa==(long)id_bodegaEmpresa) {	
				if(Math.abs(x.cantidad) > 0.001) {
					guiasPeriodo.add(x);
				}
			}
		}
		Long flagId_Guia = (long) 0;
		List<String> detalle = new ArrayList<String>();
		List<List<String>> lista = new ArrayList<List<String>>();
		//ordena la lista por id_empresa y  id_guia alfabeticamente ascendente
		for(int k=0;k<(guiasPeriodo.size()-1);k++){
            for(int j=k+1;j<guiasPeriodo.size();j++){
            	String A = guiasPeriodo.get(k).id_bodegaEmpresa+"_"+guiasPeriodo.get(k).id_guia;
            	String B = guiasPeriodo.get(j).id_bodegaEmpresa+"_"+guiasPeriodo.get(j).id_guia;
                if(A.compareToIgnoreCase(B)>0){
                    //Intercambiamos valores
                	ModCalc_GuiasPer listaAuxiliar=guiasPeriodo.get(k);
                    guiasPeriodo.set(k, guiasPeriodo.get(j));
                    guiasPeriodo.set(j, listaAuxiliar);
                }
            }
        }
		for(int i=0; i<guiasPeriodo.size(); i++) {
			if(guiasPeriodo.get(i).cantidad != 0 || i == guiasPeriodo.size()-1) {
				if((flagId_Guia - guiasPeriodo.get(i).id_guia) != 0) {
					if(flagId_Guia>0) {
						//ordena la lista por grupo y nombre equipo alfabeticamente ascendente
						for(int k=0;k<(lista.size()-1);k++){
				            for(int j=k+1;j<lista.size();j++){
				                if((lista.get(k).get(8)+lista.get(k).get(11)).
				                		compareToIgnoreCase(lista.get(j).get(8)+lista.get(j).get(11))>0){
				                    //Intercambiamos valores
				                    List<String> listaAuxiliar=lista.get(k);
				                    lista.set(k, lista.get(j));
				                    lista.set(j, listaAuxiliar);
				                }
				            }
				        }
						//agrega al mapa
						map.put(detalle.get(1)+"_"+detalle.get(4), lista);
						lista = new ArrayList<List<String>>();
					}
					flagId_Guia = guiasPeriodo.get(i).id_guia;
				}
				switch(dec.get(guiasPeriodo.get(i).id_moneda).toString()) {
				 case "0": myformatdouble = new DecimalFormat("#,##0",symbols); break;
				 case "2": myformatdouble = new DecimalFormat("#,##0.00",symbols); break;
				 case "4": myformatdouble = new DecimalFormat("#,##0.0000",symbols); break;
				 case "6": myformatdouble = new DecimalFormat("#,##0.000000",symbols); break;
				 default:  break;
				}
				switch(dec.get((long)1).toString()) {
				 case "0": myformatMonedaPrincipal = new DecimalFormat("#,##0",symbols); break;
				 case "2": myformatMonedaPrincipal = new DecimalFormat("#,##0.00",symbols); break;
				 case "4": myformatMonedaPrincipal = new DecimalFormat("#,##0.0000",symbols); break;
				 case "6": myformatMonedaPrincipal = new DecimalFormat("#,##0.000000",symbols); break;
				 default:  break;
				}
				detalle = new ArrayList<String>();
				Long id_equipo = guiasPeriodo.get(i).id_equipo;
				Equipo equipo = mapEquipo.get(id_equipo);
				Cotizacion cotizacion = mapCotizaciones.get(guiasPeriodo.get(i).id_cotizacion);
				String moneda = mapMoneda.get(guiasPeriodo.get(i).id_moneda);
				String grupo = "", numCoti  = "", codEquip = "", nomEquip = "", unidad = "";
				Double dctoArriendo = (double)0, dctoVenta = (double)0;
				if(equipo!=null) {
					grupo = equipo.grupo;
					codEquip = equipo.codigo;
					nomEquip = equipo.nombre;
					unidad = equipo.unidad;
				}
				if(cotizacion!=null) {
					numCoti = cotizacion.numero.toString();
					dctoArriendo = cotizacion.dctoArriendo;
					dctoVenta = cotizacion.dctoVenta;
				}
				if(moneda==null) {
					moneda = mapMoneda.get((long)1);
				}
				detalle.add("0"); 															// 0 idMovimiento --NO SE OCUPA--
				detalle.add(guiasPeriodo.get(i).id_bodegaEmpresa.toString().trim()); 		// 1 idBodegaEmpresa
				detalle.add(id_equipo.toString()); 											// 2 idEquipo
				detalle.add("0"); 															// 3 idTipoMovimiento --NO SE OCUPA--
				detalle.add(guiasPeriodo.get(i).id_guia.toString().trim()); 					// 4 idGuia
				detalle.add("0"); // 5 idUnidad --NO SE OCUPA--
				detalle.add("0"); // 6 idUnidadTiempo --NO SE OCUPA--
				detalle.add(guiasPeriodo.get(i).id_moneda.toString()); // 7 idMoneda 
				detalle.add(grupo); 														// 8 nombre de grupo
				detalle.add(numCoti); 														// 9 numero cotizacion
				detalle.add(codEquip); 														// 10 codigo equipo
				detalle.add(nomEquip); 														// 11 nombre equipo
				detalle.add(unidad); 														// 12 unidad
				detalle.add(myformatdouble2.format(guiasPeriodo.get(i).cantidad)); 					// 13 cantidad
				detalle.add(moneda); 														// 14 moneda
				detalle.add(myformatdouble.format(guiasPeriodo.get(i).pVenta));					// 15 precio de venta en moneda original
				if(db.equals("madaHohe")){
					detalle.add(myformatdouble.format(guiasPeriodo.get(i).pArr_dia)); 			// 16 precioArriendo por día con descuento
				}else {
					detalle.add(myformatdouble.format(guiasPeriodo.get(i).pArr_dia)); 			// 16 precioArriendo por día con descuento
				}
				detalle.add(myformatint.format(guiasPeriodo.get(i).dias)); 						// 17 cantidad de dias a cobrar
				detalle.add(myformatdouble2.format(guiasPeriodo.get(i).tasaCambio));			// 18 tasa de cambio
				detalle.add(myformatMonedaPrincipal.format(guiasPeriodo.get(i).totalArriendo)); 	// 19 total arriendo en pesos con recargos
				detalle.add(myformatMonedaPrincipal.format(guiasPeriodo.get(i).totalVenta));							// 20 total venta en pesos
				detalle.add(guiasPeriodo.get(i).esVenta.toString().trim()); 										// 21 es venta (1);
				detalle.add(myformatdouble.format(guiasPeriodo.get(i).totalCfi/guiasPeriodo.get(i).tasaCambio)); 		// 22 cfi en moneda de origen;
				detalle.add(myformatMonedaPrincipal.format(guiasPeriodo.get(i).totalCfi)); 							// 23 cfi en moneda pesos;
				detalle.add(myformatdouble2.format(dctoArriendo*100)+" %");  										//24 % dcto arriendo
				detalle.add(myformatdouble2.format(dctoVenta*100)+" %"); 											// 25 % dcto venta
				lista.add(detalle);
				if(i == guiasPeriodo.size()-1) {
					//ordena la lista por grupo y nombre equipo alfabeticamente ascendente
					for(int k=0;k<(lista.size()-1);k++){
			            for(int j=k+1;j<lista.size();j++){
			                if((lista.get(k).get(8)+lista.get(k).get(11)).
			                		compareToIgnoreCase(lista.get(j).get(8)+lista.get(j).get(11))>0){
			                    //Intercambiamos valores
			                    List<String> listaAuxiliar=lista.get(k);
			                    lista.set(k, lista.get(j));
			                    lista.set(j, listaAuxiliar);
			                }
			            }
			        }
					//agrega al mapa
					map.put(detalle.get(1)+"_"+detalle.get(4), lista);
					lista = new ArrayList<List<String>>();
				}
			}
		}
		return (map);
	}
	
	public static File reportFacturaProyectoDetExcel(String db, Map<String,String> mapDiccionario, Map<String,String> mapPermiso,
			List<List<String>> inicioPer, List<List<String>> guiasPer, List<String> fechas,
			BodegaEmpresa bodega, Proyecto proyecto, List<Double> tasaCambio,
			List<List<String>> resumenSubtotales, List<List<String>> finalPer, Cliente cliente, List<List<String>> detalleAjuste,
			Map<String,List<List<String>>> mapReportPorGuia10, Long cantDec) {

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
            
            //********************************
            //ESTADO DE PAGO DETALLADO
            //***********************************
            
            libro.setSheetName(0, "EP PROFORMA DETALLE");
            Sheet hoja1 = libro.getSheetAt(0);
            
            Row row = null;
            Cell cell = null;
            
            row = hoja1.createRow(1);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellValue("DETALLE EP/FACTURA PROFORMA");
			
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
            cell.setCellStyle(titulo);
			cell.setCellValue("CLIENTE: "+cliente.rut + " --- " + cliente.nombre);
			
            row = hoja1.createRow(6);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellValue(mapDiccionario.get("BODEGA")+"/PROYECTO: "+bodega.getNombre().toUpperCase());
			
			row = hoja1.createRow(7);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("PROYECTO: "+proyecto.nickName.toUpperCase());
			
			row = hoja1.createRow(8);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("PERIODO: desde " + fechas.get(2)  + " hasta " + fechas.get(3));
			
			row = hoja1.createRow(9);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("COMERCIAL: "+bodega.comercial);
			
			
			// encabezado de la tabla
			
			int posRow = 11;
			
			row = hoja1.createRow(posRow);
			int posCell = 0;
			int auxPosCell = 0;
			
			posCell++; 
			hoja1.setColumnWidth(posCell, 7*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("GRUPO");
			
			posCell++; 
			hoja1.setColumnWidth(posCell, 3*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("Nro.Coti");
			
			posCell++;
			hoja1.setColumnWidth(posCell, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("CODIGO");
			
			posCell++; 
			hoja1.setColumnWidth(posCell, 10*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("EQUIPO");
			
			posCell++; 
			hoja1.setColumnWidth(posCell, 3*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("UN");
			
			posCell++; 
			hoja1.setColumnWidth(posCell, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("CANT");
			
			posCell++; 
			hoja1.setColumnWidth(posCell, 3*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("MON");
			
			if(mapPermiso.get("parametro.proforma_conDctosCoti").equals("1")) {
				posCell++; 
				hoja1.setColumnWidth(posCell, 5*1000);
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("P.Venta sin dcto");
				
				posCell++; 
				hoja1.setColumnWidth(posCell, 5*1000);
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("Dcto");
				
				posCell++; 
				hoja1.setColumnWidth(posCell, 5*1000);
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("Precio Venta");
				
			}else {
				posCell++; 
				hoja1.setColumnWidth(posCell, 5*1000);
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("Precio Venta");
			}
			
			if(!mapPermiso.get("parametro.proforma_ocultaTasaArriendo").equals("1")) {
				posCell++; 
				hoja1.setColumnWidth(posCell, 5*1000);
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("Tasa Mes "+mapDiccionario.get("Arr"));
			}
			
			if(mapPermiso.get("parametro.proforma_conDctosCoti").equals("1")) {
				posCell++; 
				hoja1.setColumnWidth(posCell, 5*1000);
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("P."+mapDiccionario.get("ARR")+" DIA sin dcto");
				
				posCell++; 
				hoja1.setColumnWidth(posCell, 5*1000);
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("Dcto");
				
				posCell++; 
				hoja1.setColumnWidth(posCell, 5*1000);
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("P."+mapDiccionario.get("ARR")+" DIA con Dcto");
				
			}else {
				posCell++; 
				hoja1.setColumnWidth(posCell, 5*1000);
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("P."+mapDiccionario.get("ARR")+" DIA");
			}
			
			posCell++; 
			hoja1.setColumnWidth(posCell, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("CANT DIAS");
			
			posCell++; 
			hoja1.setColumnWidth(posCell, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("TASA CAMBIO");
			
			if(mapDiccionario.get("nEmpresa").equals("EMIN") && tasaCambio.get(0).toString().equals("1")) {
				posCell++; 
				hoja1.setColumnWidth(posCell, 5*1000);
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("CFI en UF");
				auxPosCell = posCell;
				
				posCell++; 
				hoja1.setColumnWidth(posCell, 5*1000);
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue(mapDiccionario.get("ARRIENDO")+" en UF");
				
				posCell++; 
				hoja1.setColumnWidth(posCell, 5*1000);
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("VENTA en UF");
				
			}else {
				posCell++; 
				hoja1.setColumnWidth(posCell, 5*1000);
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("CFI en "+mapDiccionario.get("PESOS"));
				auxPosCell = posCell;
				
				posCell++; 
				hoja1.setColumnWidth(posCell, 5*1000);
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue(mapDiccionario.get("ARRIENDO")+" en "+mapDiccionario.get("PESOS"));
				
				posCell++; 
				hoja1.setColumnWidth(posCell, 5*1000);
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("VENTA en "+mapDiccionario.get("PESOS"));
				
			}
			
			posCell++; 
			hoja1.setColumnWidth(posCell, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("DESDE FECHA");
			
		
			//INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
			InputStream x = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
            byte[] bytes = IOUtils.toByteArray(x);
            x.close();
            int pngIndex = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			Drawing draw = hoja1.createDrawingPatriarch();
			CreationHelper helper = libro.getCreationHelper();
			ClientAnchor anchor = helper.createClientAnchor();
	        //set top-left corner for the image
	        anchor.setCol1(13);
	        anchor.setRow1(1);
			Picture img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);
			
			
			Double cfi = (double)0;
			Double arr = (double)0;
			Double vta = (double)0;
			
			
			
			//DETALLE DE LA TABLA EP DETALLADO
			posRow++;
			posRow++;
			posCell = 0;
			
			row = hoja1.createRow(posRow);
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(titulo);
			cell.setCellValue("INVENTARIO INICIAL");
			
			posRow++;
			Double aux = (double)0;
			
			for(int i=0; i<inicioPer.size(); i++){
				row = hoja1.createRow(posRow);
				posCell = 0;
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(inicioPer.get(i).get(7));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(inicioPer.get(i).get(8));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(inicioPer.get(i).get(9));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(inicioPer.get(i).get(10));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(inicioPer.get(i).get(11));
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(inicioPer.get(i).get(12).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(inicioPer.get(i).get(13));
				
				if(inicioPer.get(i).get(19).trim().equals("0") && mapDiccionario.get("nEmpresa").equals("SANTAFE")){
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellValue("");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellValue("");
				}else{
					if(mapPermiso.get("parametro.proforma_conDctosCoti").equals("1")){
						Double auxVta = Double.parseDouble(inicioPer.get(i).get(14).replaceAll(",", ""));
						Double auxDcto = Double.parseDouble(inicioPer.get(i).get(25).replaceAll(",", "").replaceAll("%", "").trim());
						if((double)auxDcto > (double)0){
							posCell++; 
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
				            aux = auxVta/(1-auxDcto/100);
										cell.setCellValue(aux);
						}else {
							posCell++; 
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
										cell.setCellValue(auxVta);
						}
						posCell++; 
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
								cell.setCellValue(auxDcto/100);
					}
					posCell++; 
		            cell = row.createCell(posCell);
		            cell.setCellStyle(detalle);
		            aux = Double.parseDouble(inicioPer.get(i).get(14).replaceAll(",", ""));
					cell.setCellValue(aux);
					
					if(!mapPermiso.get("parametro.proforma_ocultaTasaArriendo").equals("1")){
						Double auxVta = Double.parseDouble(inicioPer.get(i).get(14).replaceAll(",", ""));
						Double auxArr = Double.parseDouble(inicioPer.get(i).get(15).replaceAll(",", ""));
						if((double)auxVta == (double)0){
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							cell.setCellValue("");
						}else{
							posCell++; 
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
										cell.setCellValue(auxArr*30/auxVta);
						}
					}
				}
				
				if(mapPermiso.get("parametro.proforma_conDctosCoti").equals("1")){
					Double auxArr = Double.parseDouble(inicioPer.get(i).get(15).replaceAll(",", ""));
					Double auxDcto = Double.parseDouble(inicioPer.get(i).get(24).replaceAll(",", "").replaceAll("%", "").trim());
					if((double)auxDcto > (double)0){
						posCell++; 
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
			            aux = auxArr/(1-auxDcto/100);
								cell.setCellValue(aux);
					}else {
						posCell++; 
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
								cell.setCellValue(auxArr);
					}
					posCell++; 
		            cell = row.createCell(posCell);
		            cell.setCellStyle(detalle);
					cell.setCellValue(auxDcto/100);
				}
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(inicioPer.get(i).get(15).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(inicioPer.get(i).get(16).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(inicioPer.get(i).get(17).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(inicioPer.get(i).get(23).replaceAll(",", ""));
				cell.setCellValue(aux);
				cfi += aux;
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(inicioPer.get(i).get(18).replaceAll(",", ""));
				cell.setCellValue(aux);
				arr += aux;
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(inicioPer.get(i).get(19).replaceAll(",", ""));
				cell.setCellValue(aux);
				vta += aux;
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(inicioPer.get(i).get(21).replaceAll("-", "/"));
				
				posRow++;
			}
			
			posRow++;
			posRow++;
			posCell = 0;
			
			row = hoja1.createRow(posRow);
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(titulo);
			cell.setCellValue("DETALLE MOVIMIENTOS DEL MES");
			
			posRow++;
			
			for(int i=0; i<guiasPer.size(); i++){
				posRow++;
				posCell = 0;
				
				row = hoja1.createRow(posRow);
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(subtitulo);
				cell.setCellValue(guiasPer.get(i).get(7)+": "+guiasPer.get(i).get(2));
				
				posCell++; 
	            cell = row.createCell(posCell+2);
	            cell.setCellStyle(subtitulo);
				cell.setCellValue("FECHA GUIA: "+guiasPer.get(i).get(3)+"  --  INI/TER: "+guiasPer.get(i).get(10));
				
				posCell++; 
	            cell = row.createCell(posCell+4);
	            cell.setCellStyle(subtitulo);
				cell.setCellValue("Nro de Ref: "+guiasPer.get(i).get(5));
				
				posCell++; 
	            cell = row.createCell(posCell+5);
	            cell.setCellStyle(subtitulo);
				cell.setCellValue("Observaciones: "+guiasPer.get(i).get(9));
				
				posRow++;
				
				List<List<String>> reportPorGuia = mapReportPorGuia10.get(guiasPer.get(i).get(8));
				
				if(reportPorGuia!=null) {
					
					for(int j=0; j<reportPorGuia.size(); j++){
						
						if(!(reportPorGuia.get(j).get(13).equals("0") || reportPorGuia.get(j).get(13).equals("-0"))) {
							posCell = 0;
							row = hoja1.createRow(posRow);
							
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							cell.setCellValue(reportPorGuia.get(j).get(8));
							
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							cell.setCellValue(reportPorGuia.get(j).get(9));
							
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							cell.setCellValue(reportPorGuia.get(j).get(10));
							
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							cell.setCellValue(reportPorGuia.get(j).get(11));
							
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							cell.setCellValue(reportPorGuia.get(j).get(12));
							
							posCell++; 
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
				            aux = Double.parseDouble(reportPorGuia.get(j).get(13).replaceAll(",", ""));
										cell.setCellValue(aux);
							
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							cell.setCellValue(reportPorGuia.get(j).get(14));
							
							if(reportPorGuia.get(j).get(19).trim().equals("0") && mapDiccionario.get("nEmpresa").equals("SANTAFE")){
								posCell++;
								cell = row.createCell(posCell);
								cell.setCellStyle(detalle);
								cell.setCellValue("");
								
								posCell++;
								cell = row.createCell(posCell);
								cell.setCellStyle(detalle);
								cell.setCellValue("");
							}else{
								if(mapPermiso.get("parametro.proforma_conDctosCoti").equals("1")){
									Double auxVta = Double.parseDouble(reportPorGuia.get(j).get(15).replaceAll(",", ""));
									Double auxDcto = Double.parseDouble(reportPorGuia.get(j).get(25).replaceAll(",", "").replaceAll("%", "").trim());
									if((double)auxDcto > (double)0){
										posCell++; 
							            cell = row.createCell(posCell);
							            cell.setCellStyle(detalle);
							            aux = auxVta/(1-auxDcto/100);
																cell.setCellValue(aux);
									}else {
										posCell++; 
							            cell = row.createCell(posCell);
							            cell.setCellStyle(detalle);
																cell.setCellValue(auxVta);
									}
									posCell++; 
						            cell = row.createCell(posCell);
						            cell.setCellStyle(detalle);
														cell.setCellValue(auxDcto/100);
								}
								
								posCell++; 
					            cell = row.createCell(posCell);
					            cell.setCellStyle(detalle);
					            aux = Double.parseDouble(reportPorGuia.get(j).get(15).replaceAll(",", ""));
												cell.setCellValue(aux);
								
								if(!mapPermiso.get("parametro.proforma_ocultaTasaArriendo").equals("1")){
									Double auxVta = Double.parseDouble(reportPorGuia.get(j).get(15).replaceAll(",", ""));
									Double auxArr = Double.parseDouble(reportPorGuia.get(j).get(16).replaceAll(",", ""));
									if((double)auxVta == (double)0){
										posCell++;
										cell = row.createCell(posCell);
										cell.setCellStyle(detalle);
										cell.setCellValue("");
									}else{
										posCell++; 
							            cell = row.createCell(posCell);
							            cell.setCellStyle(detalle);
																cell.setCellValue(auxArr*30/auxVta);
									}
								}
							}
							
							if(mapPermiso.get("parametro.proforma_conDctosCoti").equals("1")){
								Double auxArr = Double.parseDouble(reportPorGuia.get(j).get(16).replaceAll(",", ""));
								Double auxDcto = Double.parseDouble(reportPorGuia.get(j).get(24).replaceAll(",", "").replaceAll("%", "").trim());
								if((double)auxDcto > (double)0){
									posCell++; 
						            cell = row.createCell(posCell);
						            cell.setCellStyle(detalle);
						            aux = auxArr/(1-auxDcto/100);
														cell.setCellValue(aux);
								}else {
									posCell++; 
						            cell = row.createCell(posCell);
						            cell.setCellStyle(detalle);
														cell.setCellValue(auxArr);
								}
								posCell++; 
					            cell = row.createCell(posCell);
					            cell.setCellStyle(detalle);
												cell.setCellValue(auxDcto/100);
							}
							
							posCell++; 
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
				            aux = Double.parseDouble(reportPorGuia.get(j).get(16).replaceAll(",", ""));
										cell.setCellValue(aux);
							
							posCell++; 
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
				            aux = Double.parseDouble(reportPorGuia.get(j).get(17).replaceAll(",", ""));
										cell.setCellValue(aux);
							
							posCell++; 
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
				            aux = Double.parseDouble(reportPorGuia.get(j).get(18).replaceAll(",", ""));
										cell.setCellValue(aux);
							
							posCell++; 
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
				            aux = Double.parseDouble(reportPorGuia.get(j).get(23).replaceAll(",", ""));
										cell.setCellValue(aux);
							cfi += aux;
							
							posCell++; 
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
				            aux = Double.parseDouble(reportPorGuia.get(j).get(19).replaceAll(",", ""));
										cell.setCellValue(aux);
							arr += aux;
							
							posCell++; 
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
				            aux = Double.parseDouble(reportPorGuia.get(j).get(20).replaceAll(",", ""));
										cell.setCellValue(aux);
							vta += aux;
							
							posCell++; 
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellValue("");
							
							posRow++;
						}
					}
				}
			}
			posRow++;
			posRow++;
			
			
			row = hoja1.createRow(posRow);
			posCell = auxPosCell -2;
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("SUBTOTALES NETO");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(subtitulo);
			cell.setCellValue(cfi);
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(subtitulo);
			cell.setCellValue(arr);
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(subtitulo);
			cell.setCellValue(vta);
			
			posRow++;
			
			
			for(List<String> lista: detalleAjuste){
				posRow++;
				row = hoja1.createRow(posRow);
				posCell = auxPosCell - 2;
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellValue(lista.get(0));
				
				posCell++; 
	            cell = row.createCell(posCell);
				cell.setCellValue("");
				
				posCell++; 
	            cell = row.createCell(posCell);
				aux = Double.parseDouble(lista.get(1).replaceAll(",","").trim());
				cell.setCellValue(aux);
				arr+=aux;
				
				posCell++; 
	            cell = row.createCell(posCell);
				aux = Double.parseDouble(lista.get(2).replaceAll(",","").trim());
				cell.setCellValue(aux);
				vta+=aux;
			}
			
			posRow++;
			posRow++;
			
			row = hoja1.createRow(posRow);
			posCell = auxPosCell - 2;
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("TOTAL NETO");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(subtitulo);
			cell.setCellValue(cfi);
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(subtitulo);
			cell.setCellValue(arr);
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(subtitulo);
			cell.setCellValue(vta);
			
			posRow++;
			posRow++;
			
			row = hoja1.createRow(posRow);
			posCell = auxPosCell - 2;
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("TOTAL TOTAL NETO");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(subtitulo);
			cell.setCellValue(cfi + arr + vta);
			
			
			//************************
			//RESUMEN
			//************************
			//libro.createSheet();
			libro.setSheetName(1, "RESUMEN");
			Sheet hoja2 = libro.getSheetAt(1);
            
            row = hoja2.createRow(1);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellValue("RESUMEN EP/FACTURA PROFORMA");
			
			row = hoja2.createRow(2);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("EMPRESA: "+mapDiccionario.get("nEmpresa"));
			
			row = hoja2.createRow(3);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("FECHA: "+Fechas.hoy().getFechaStrDDMMAA());
			
			row = hoja2.createRow(5);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellValue("CLIENTE: "+cliente.rut + " --- " + cliente.nombre);
			
            row = hoja2.createRow(6);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellValue(mapDiccionario.get("BODEGA")+"/PROYECTO: "+bodega.getNombre().toUpperCase());
			
			row = hoja2.createRow(7);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("PROYECTO: "+proyecto.nickName.toUpperCase());
			
			row = hoja2.createRow(8);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("PERIODO: desde " + fechas.get(2)  + " hasta " + fechas.get(3));
			
			row = hoja2.createRow(9);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("COMERCIAL: "+bodega.comercial);
			
			
			// encabezado de la tabla RESUMEN
			
			posRow = 11;
			
			row = hoja2.createRow(posRow);
			posCell = 0;
			auxPosCell = 0;
			
			posCell++; 
			hoja2.setColumnWidth(posCell, 7*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("GRUPO");
			
			posCell++; 
			hoja2.setColumnWidth(posCell, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("Total CFI"+" ("+mapDiccionario.get("PESOS")+")");
			
			posCell++;
			hoja2.setColumnWidth(posCell, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("Total "+mapDiccionario.get("ARRIENDO")+" ("+mapDiccionario.get("PESOS")+")");
			
			posCell++; 
			hoja2.setColumnWidth(posCell, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("Total Venta"+" ("+mapDiccionario.get("PESOS")+")");
			
			//INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
            int pngIndex2 = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			Drawing draw2 = hoja2.createDrawingPatriarch();
			CreationHelper helper2 = libro.getCreationHelper();
			ClientAnchor anchor2 = helper2.createClientAnchor();
	        //set top-left corner for the image
	        anchor2.setCol1(4);
	        anchor2.setRow1(1);
			Picture img2 = draw2.createPicture(anchor2, pngIndex2);
			img2.resize(0.4);
			hoja2.createFreezePane(0, 0, 0,0);
			
			
			cfi = (double)0;
			arr = (double)0;
			vta = (double)0;
			
			
			
			//DETALLE DE LA TABLA
			posRow++;
			
			for(int j=0;j<resumenSubtotales.size();j++){
				row = hoja2.createRow(posRow);
				posCell = 0;
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(resumenSubtotales.get(j).get(0));
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(resumenSubtotales.get(j).get(3).replaceAll(",", ""));
				cell.setCellValue(aux);
				cfi += aux;
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(resumenSubtotales.get(j).get(1).replaceAll(",", ""));
				cell.setCellValue(aux);
				arr += aux;
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(resumenSubtotales.get(j).get(2).replaceAll(",", ""));
				cell.setCellValue(aux);
				vta += aux;
				
				posRow++;
				
			}
			
			posRow++;
			row = hoja2.createRow(posRow);
			
			posCell = 0;
			
			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue("SUBTOTAL NETO");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(detalle);
            aux = cfi;
			cell.setCellValue(aux);
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(detalle);
            aux = arr;
			cell.setCellValue(aux);
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(detalle);
            aux = vta;
			cell.setCellValue(aux);
			
			posRow++;
			posRow++;
			
			for(List<String> lista: detalleAjuste){
				
				row = hoja2.createRow(posRow);
				posCell = 0;
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellValue(lista.get(0));
				
				posCell++; 
	            cell = row.createCell(posCell);
				cell.setCellValue("");
				
				posCell++; 
	            cell = row.createCell(posCell);
				aux = Double.parseDouble(lista.get(1).replaceAll(",","").trim());
				cell.setCellValue(aux);
				arr += aux;
				
				posCell++; 
	            cell = row.createCell(posCell);
				aux = Double.parseDouble(lista.get(2).replaceAll(",","").trim());
				cell.setCellValue(aux);
				vta += aux;
				
				posRow++;
			}
			
			posRow++;
			row = hoja2.createRow(posRow);
			
			posCell = 0;
			
			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue("TOTAL NETO");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(detalle);
            aux = cfi;
			cell.setCellValue(aux);
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(detalle);
            aux = arr;
			cell.setCellValue(aux);
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(detalle);
            aux = vta;
			cell.setCellValue(aux);
			
			posRow++;
			posRow++;
			row = hoja2.createRow(posRow);
			
			posCell = 0;
			
			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue("TOTAL TOTAL NETO");
			
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
            aux = cfi+arr+vta;
			cell.setCellValue(aux);
			
			
			
			//************************
			//INVENTARIO FINAL
			//************************
			//libro.createSheet();
			libro.setSheetName(2, "INVENTARIO FINAL");
			Sheet hoja3 = libro.getSheetAt(2);
            
            row = hoja3.createRow(1);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellValue("INVENTARIO FINAL");
			
			row = hoja3.createRow(2);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("EMPRESA: "+mapDiccionario.get("nEmpresa"));
			
			row = hoja3.createRow(3);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("FECHA: "+Fechas.hoy().getFechaStrDDMMAA());
			
			row = hoja3.createRow(5);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellValue("CLIENTE: "+cliente.rut + " --- " + cliente.nombre);
			
            row = hoja3.createRow(6);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellValue(mapDiccionario.get("BODEGA")+"/PROYECTO: "+bodega.getNombre().toUpperCase());
			
			row = hoja3.createRow(7);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("PROYECTO: "+proyecto.nickName.toUpperCase());
			
			row = hoja3.createRow(8);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("INVENTARIO: al " + fechas.get(3));
			
			row = hoja3.createRow(9);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("COMERCIAL: "+bodega.comercial);
			
			
			// encabezado de la tabla INVENTARIO FINAL
			
			posRow = 11;
			
			row = hoja3.createRow(posRow);
			posCell = 0;
			auxPosCell = 0;
			
			posCell++; 
			hoja3.setColumnWidth(posCell, 7*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("GRUPO");
			
			posCell++; 
			hoja3.setColumnWidth(posCell, 3*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("Nro.Coti");
			
			posCell++;
			hoja3.setColumnWidth(posCell, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("CODIGO");
			
			posCell++; 
			hoja3.setColumnWidth(posCell, 10*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("EQUIPO");
			
			posCell++; 
			hoja3.setColumnWidth(posCell, 3*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("UN");
			
			posCell++; 
			hoja3.setColumnWidth(posCell, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("CANT");
			
			posCell++; 
			hoja3.setColumnWidth(posCell, 3*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("MON");
			
			if(mapPermiso.get("parametro.proforma_conDctosCoti").equals("1")) {
				posCell++; 
				hoja3.setColumnWidth(posCell, 5*1000);
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("P.Venta sin dcto");
				
				posCell++; 
				hoja3.setColumnWidth(posCell, 5*1000);
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("Dcto");
				
				posCell++; 
				hoja3.setColumnWidth(posCell, 5*1000);
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("Precio Venta");
				
			}else {
				posCell++; 
				hoja3.setColumnWidth(posCell, 5*1000);
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("Precio Venta");
			}
			
			if(!mapPermiso.get("parametro.proforma_ocultaTasaArriendo").equals("1")) {
				posCell++; 
				hoja3.setColumnWidth(posCell, 5*1000);
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("Tasa Mes "+mapDiccionario.get("Arr"));
			}
			
			if(mapPermiso.get("parametro.proforma_conDctosCoti").equals("1")) {
				posCell++; 
				hoja3.setColumnWidth(posCell, 5*1000);
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("P."+mapDiccionario.get("ARR")+" DIA sin dcto");
				
				posCell++; 
				hoja3.setColumnWidth(posCell, 5*1000);
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("Dcto");
				
				posCell++; 
				hoja3.setColumnWidth(posCell, 5*1000);
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("P."+mapDiccionario.get("ARR")+" DIA con Dcto");
				
			}else {
				posCell++; 
				hoja3.setColumnWidth(posCell, 5*1000);
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("P."+mapDiccionario.get("ARR")+" DIA");
			}
			
			posCell++; 
			hoja3.setColumnWidth(posCell, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("CANT DIAS");
			
			posCell++; 
			hoja3.setColumnWidth(posCell, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("TASA CAMBIO");
			
			if(mapDiccionario.get("nEmpresa").equals("EMIN") && tasaCambio.get(0).toString().equals("1")) {
				posCell++; 
				hoja3.setColumnWidth(posCell, 5*1000);
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("CFI en UF");
				auxPosCell = posCell;
				
				posCell++; 
				hoja3.setColumnWidth(posCell, 5*1000);
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue(mapDiccionario.get("ARRIENDO")+" en UF");
				
				posCell++; 
				hoja3.setColumnWidth(posCell, 5*1000);
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("VENTA en UF");
				
			}else {
				posCell++; 
				hoja3.setColumnWidth(posCell, 5*1000);
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("CFI en "+mapDiccionario.get("PESOS"));
				auxPosCell = posCell;
				
				posCell++; 
				hoja3.setColumnWidth(posCell, 5*1000);
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue(mapDiccionario.get("ARRIENDO")+" en "+mapDiccionario.get("PESOS"));
				
				posCell++; 
				hoja3.setColumnWidth(posCell, 5*1000);
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("VENTA en "+mapDiccionario.get("PESOS"));
				
			}
			
			posCell++; 
			hoja3.setColumnWidth(posCell, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("DESDE FECHA");

			//INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
            int pngIndex3 = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			Drawing draw3 = hoja3.createDrawingPatriarch();
			CreationHelper helper3 = libro.getCreationHelper();
			ClientAnchor anchor3 = helper3.createClientAnchor();
	        //set top-left corner for the image
	        anchor3.setCol1(4);
	        anchor3.setRow1(1);
			Picture img3 = draw3.createPicture(anchor3, pngIndex3);
			img3.resize(0.4);
			hoja3.createFreezePane(0, 0, 0,0);
			
			
			cfi = (double)0;
			arr = (double)0;
			vta = (double)0;
			
			
			
			//DETALLE DE LA TABLA
			posRow++;
			posRow++;
			posCell = 0;
			
			row = hoja3.createRow(posRow);
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(titulo);
			cell.setCellValue("INVENTARIO FINAL");
			
			posRow++;
			aux = (double)0;
			
			for(int i=0; i<finalPer.size(); i++){
				row = hoja3.createRow(posRow);
				posCell = 0;
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(finalPer.get(i).get(7));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(finalPer.get(i).get(8));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(finalPer.get(i).get(9));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(finalPer.get(i).get(10));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(finalPer.get(i).get(11));
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(finalPer.get(i).get(12).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(finalPer.get(i).get(13));
				
				if(finalPer.get(i).get(19).trim().equals("0") && mapDiccionario.get("nEmpresa").equals("SANTAFE")){
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellValue("");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellValue("");
				}else{
					if(mapPermiso.get("parametro.proforma_conDctosCoti").equals("1")){
						Double auxVta = Double.parseDouble(finalPer.get(i).get(14).replaceAll(",", ""));
						Double auxDcto = Double.parseDouble(finalPer.get(i).get(25).replaceAll(",", "").replaceAll("%", "").trim());
						if((double)auxDcto > (double)0){
							posCell++; 
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
				            aux = auxVta/(1-auxDcto/100);
										cell.setCellValue(aux);
						}else {
							posCell++; 
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
										cell.setCellValue(auxVta);
						}
						posCell++; 
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
								cell.setCellValue(auxDcto/100);
					}
					posCell++; 
		            cell = row.createCell(posCell);
		            cell.setCellStyle(detalle);
		            aux = Double.parseDouble(finalPer.get(i).get(14).replaceAll(",", ""));
					cell.setCellValue(aux);
					
					if(!mapPermiso.get("parametro.proforma_ocultaTasaArriendo").equals("1")){
						Double auxVta = Double.parseDouble(finalPer.get(i).get(14).replaceAll(",", ""));
						Double auxArr = Double.parseDouble(finalPer.get(i).get(15).replaceAll(",", ""));
						if((double)auxVta == (double)0){
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							cell.setCellValue("");
						}else{
							posCell++; 
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
										cell.setCellValue(auxArr*30/auxVta);
						}
					}
				}
				
				if(mapPermiso.get("parametro.proforma_conDctosCoti").equals("1")){
					Double auxArr = Double.parseDouble(finalPer.get(i).get(15).replaceAll(",", ""));
					Double auxDcto = Double.parseDouble(finalPer.get(i).get(24).replaceAll(",", "").replaceAll("%", "").trim());
					if((double)auxDcto > (double)0){
						posCell++; 
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
			            aux = auxArr/(1-auxDcto/100);
								cell.setCellValue(aux);
					}else {
						posCell++; 
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
								cell.setCellValue(auxArr);
					}
					posCell++; 
		            cell = row.createCell(posCell);
		            cell.setCellStyle(detalle);
					cell.setCellValue(auxDcto/100);
				}
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(finalPer.get(i).get(15).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(finalPer.get(i).get(16).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(finalPer.get(i).get(17).replaceAll(",", ""));
				cell.setCellValue(aux);
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(finalPer.get(i).get(23).replaceAll(",", ""));
				cell.setCellValue(aux);
				cfi += aux;
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(finalPer.get(i).get(18).replaceAll(",", ""));
				cell.setCellValue(aux);
				arr += aux;
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(finalPer.get(i).get(19).replaceAll(",", ""));
				cell.setCellValue(aux);
				vta += aux;
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellValue(finalPer.get(i).get(21).replaceAll("-", "/"));
				
				posRow++;
			}
			
			
			posRow = posRow + 5;
			row = hoja3.createRow(posRow);
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

	public static File reportFacturaProyectoDetHExcel(String db, Map<String,String> mapDiccionario, Map<String,String> mapPermiso,
													 String idTipoUsuario, List<List<String>> lista, BodegaEmpresa bodega, String esVenta,
													  String concepto, String fechaDesde, String fechaHasta, Double usd, Double eur, Double uf,
													 Cliente cliente, Proyecto proyecto,
													  String oc, Double tasaIva){

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

			CreationHelper creationHelper = libro.getCreationHelper();

			CellStyle hora = libro.createCellStyle();
			hora.setDataFormat(creationHelper.createDataFormat().getFormat("hh:mm"));
			hora.setBorderBottom(BorderStyle.THIN);
			hora.setBorderTop(BorderStyle.THIN);
			hora.setBorderRight(BorderStyle.THIN);
			hora.setBorderLeft(BorderStyle.THIN);

			CellStyle fecha = libro.createCellStyle();
			fecha.setDataFormat(creationHelper.createDataFormat().getFormat("dd/MM/yyyy"));
			fecha.setBorderBottom(BorderStyle.THIN);
			fecha.setBorderTop(BorderStyle.THIN);
			fecha.setBorderRight(BorderStyle.THIN);
			fecha.setBorderLeft(BorderStyle.THIN);

			//********************************
			//ESTADO DE PAGO DETALLADO
			//***********************************

			libro.setSheetName(0, "EP PROFORMA DETALLE");
			Sheet hoja1 = libro.getSheetAt(0);

			Row row = null;
			Cell cell = null;

			row = hoja1.createRow(1);
			cell = row.createCell(1);
			cell.setCellStyle(titulo);
			cell.setCellValue("DETALLE EP/FACTURA PROFORMA SIMPLE");

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
			cell.setCellStyle(titulo);
			cell.setCellValue("CLIENTE: "+cliente.rut + " --- " + cliente.nombre);

			row = hoja1.createRow(6);
			cell = row.createCell(1);
			cell.setCellStyle(titulo);
			cell.setCellValue(mapDiccionario.get("BODEGA")+"/PROYECTO: "+bodega.getNombre().toUpperCase());

			row = hoja1.createRow(7);
			cell = row.createCell(1);
			cell.setCellStyle(subtitulo);
			cell.setCellValue("PROYECTO: "+proyecto.nickName.toUpperCase());

			row = hoja1.createRow(8);
			cell = row.createCell(1);
			cell.setCellStyle(subtitulo);
			cell.setCellValue("PERIODO: desde " + Fechas.DDMMAA(fechaDesde)  + " hasta " + Fechas.DDMMAA(fechaHasta));

			row = hoja1.createRow(9);
			cell = row.createCell(1);
			cell.setCellStyle(subtitulo);
			cell.setCellValue("COMERCIAL: "+bodega.comercial);

			int posRow = 11;

			row = hoja1.createRow(posRow);
			int posCell = 0;

			Double arr = (double)0;

			//RESUMEN
			posRow++;
			posRow++;

			row = hoja1.createRow(posRow);

			posRow++;
			posCell = 0;


			posCell++;
			hoja1.setColumnWidth(posCell, 7*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("GRUPO");

			posCell++;
			hoja1.setColumnWidth(posCell, 3*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("Nro.Coti");

			posCell++;
			hoja1.setColumnWidth(posCell, 5*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("Codigo");

			posCell++;
			hoja1.setColumnWidth(posCell, 10*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("Descripcion");

			posCell++;
			hoja1.setColumnWidth(posCell, 3*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("Moneda");

			posCell++;
			hoja1.setColumnWidth(posCell, 5*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("Precio Venta");

			posCell++;
			hoja1.setColumnWidth(posCell, 5*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue(mapDiccionario.get("Arriendo")+ "Mes");

			posCell++;
			hoja1.setColumnWidth(posCell, 5*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue(mapDiccionario.get("Arriendo")+ "Dia");

			posCell++;
			hoja1.setColumnWidth(posCell, 5*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("Cant Dias");

			posCell++;
			hoja1.setColumnWidth(posCell, 5*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("TOTAL MON ORIGEN");

			posCell++;
			hoja1.setColumnWidth(posCell, 5*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("TOTAL MON CLP");

			posCell++;
			hoja1.setColumnWidth(posCell, 5*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("NRO GUIA ULTIMO INGRESO");

			posCell++;
			hoja1.setColumnWidth(posCell, 5*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("FECHA INI/TER INGRESO");

			posCell++;
			hoja1.setColumnWidth(posCell, 5*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("NRO GUIA ULTIMA SALIDA");

			posCell++;
			hoja1.setColumnWidth(posCell, 5*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("FECHA INI/TER SALIDA");



			//INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
			InputStream x = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
			byte[] bytes = IOUtils.toByteArray(x);
			x.close();
			int pngIndex = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			Drawing draw = hoja1.createDrawingPatriarch();
			CreationHelper helper = libro.getCreationHelper();
			ClientAnchor anchor = helper.createClientAnchor();
			//set top-left corner for the image
			anchor.setCol1(13);
			anchor.setRow1(1);
			Picture img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);

			posRow--;
			for(int i=0; i<lista.size(); i++) {
				if (i > 2) {
					posRow++;
					row = hoja1.createRow(posRow);
					posCell = 0;

					for (int j = 0; j < lista.get(i).size(); j++) {
						if (j < 4) {
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							cell.setCellValue(lista.get(i).get(j));
						} else if (j == 12 || j == 14){
							posCell++;
							cell = row.createCell(posCell);
							if(lista.get(i).get(j).length() > 6){
								Fechas fechax = Fechas.obtenerFechaDesdeStrAAMMDD(Fechas.AAMMDD(lista.get(i).get(j)));
								cell.setCellValue(fechax.fechaUtil);
								cell.setCellStyle(fecha);
							}else{
								cell.setCellStyle(detalle);
							}
						}else{
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							try {
								Double aux = Double.parseDouble(lista.get(i).get(j).replaceAll(",", "").trim());
												cell.setCellValue(aux);
							}catch (Exception e) {
								cell.setCellValue(lista.get(i).get(j));
							}
						}
					}
				}
			}

			row = hoja1.getRow(6);
			cell = row.createCell(6);
			cell.setCellStyle(encabezado);
			cell.setCellValue("TOTALES");

			row = hoja1.getRow(7);
			cell = row.createCell(6);
			cell.setCellStyle(detalle);
			cell.setCellValue("NETO");

			row = hoja1.getRow(8);
			cell = row.createCell(6);
			cell.setCellStyle(detalle);
			cell.setCellValue(mapDiccionario.get("IVA") + " " +tasaIva*100 + "%");

			row = hoja1.getRow(9);
			cell = row.createCell(6);
			cell.setCellStyle(detalle);
			cell.setCellValue("TOTAL");

			row = hoja1.getRow(6);
			cell = row.createCell(7);
			cell.setCellStyle(encabezado);
			cell.setCellValue("");

			Double tot = (double)0;
			try {
				Double aux = Double.parseDouble(lista.get(lista.size()-1).get(10).replaceAll(",", "").trim());
				tot = aux;
			}catch (Exception e) {}


			row = hoja1.getRow(7);
			cell = row.createCell(7);
			cell.setCellStyle(detalle);
			Double neto = tot;
			cell.setCellValue(neto);

			row = hoja1.getRow(8);
			cell = row.createCell(7);
			cell.setCellStyle(detalle);
			cell.setCellValue(neto * 0.19);

			row = hoja1.getRow(9);
			cell = row.createCell(7);
			cell.setCellStyle(detalle);
			cell.setCellValue(neto * 1.19);

			// Write the output to a file tmp
			FileOutputStream fileOut = new FileOutputStream(tmp);
			libro.write(fileOut);
			fileOut.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return tmp;

	}

	public static File reportFacturaProyectoDetHVtaExcel(String db, Map<String,String> mapDiccionario, List<List<String>> lista, BodegaEmpresa bodega, String esVenta,
													  String fechaDesde, String fechaHasta, Cliente cliente, Proyecto proyecto, Moneda moneda ,String oc, Double tasaIva){
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

			CreationHelper creationHelper = libro.getCreationHelper();

			CellStyle hora = libro.createCellStyle();
			hora.setDataFormat(creationHelper.createDataFormat().getFormat("hh:mm"));
			hora.setBorderBottom(BorderStyle.THIN);
			hora.setBorderTop(BorderStyle.THIN);
			hora.setBorderRight(BorderStyle.THIN);
			hora.setBorderLeft(BorderStyle.THIN);

			CellStyle fecha = libro.createCellStyle();
			fecha.setDataFormat(creationHelper.createDataFormat().getFormat("dd/MM/yyyy"));
			fecha.setBorderBottom(BorderStyle.THIN);
			fecha.setBorderTop(BorderStyle.THIN);
			fecha.setBorderRight(BorderStyle.THIN);
			fecha.setBorderLeft(BorderStyle.THIN);

			//********************************
			//ESTADO DE PAGO DETALLADO
			//***********************************

			libro.setSheetName(0, "EP PROFORMA DETALLE");
			Sheet hoja1 = libro.getSheetAt(0);

			Row row = null;
			Cell cell = null;

			row = hoja1.createRow(1);
			cell = row.createCell(1);
			cell.setCellStyle(titulo);
			cell.setCellValue("DETALLE EP/FACTURA PROFORMA SIMPLE");

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
			cell.setCellStyle(titulo);
			cell.setCellValue("CLIENTE: "+cliente.rut + " --- " + cliente.nombre);

			row = hoja1.createRow(6);
			cell = row.createCell(1);
			cell.setCellStyle(titulo);
			cell.setCellValue(mapDiccionario.get("BODEGA")+"/PROYECTO: "+bodega.getNombre().toUpperCase());

			row = hoja1.createRow(7);
			cell = row.createCell(1);
			cell.setCellStyle(subtitulo);
			cell.setCellValue("PROYECTO: "+proyecto.nickName.toUpperCase());

			row = hoja1.createRow(8);
			cell = row.createCell(1);
			cell.setCellStyle(subtitulo);
			cell.setCellValue("PERIODO: desde " + Fechas.DDMMAA(fechaDesde)  + " hasta " + Fechas.DDMMAA(fechaHasta));

			row = hoja1.createRow(9);
			cell = row.createCell(1);
			cell.setCellStyle(subtitulo);
			cell.setCellValue("COMERCIAL: "+bodega.comercial);

			int posRow = 11;

			row = hoja1.createRow(posRow);
			int posCell = 0;

			Double arr = (double)0;

			//RESUMEN
			posRow++;
			posRow++;

			row = hoja1.createRow(posRow);

			posRow++;
			posCell = 0;


			posCell++;
			hoja1.setColumnWidth(posCell, 7*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("GRUPO");

			posCell++;
			hoja1.setColumnWidth(posCell, 3*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("COTI");

			posCell++;
			hoja1.setColumnWidth(posCell, 5*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("CODIGO");

			posCell++;
			hoja1.setColumnWidth(posCell, 10*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("EQUIPO");

			posCell++;
			hoja1.setColumnWidth(posCell, 3*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("MONEDA");

			posCell++;
			hoja1.setColumnWidth(posCell, 5*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("PRECIO UNITARIO");

			posCell++;
			hoja1.setColumnWidth(posCell, 5*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("UNIDAD");

			posCell++;
			hoja1.setColumnWidth(posCell, 5*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("CANTIDAD");

			posCell++;
			hoja1.setColumnWidth(posCell, 5*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("TOTAL MON ORIGEN");

			posCell++;
			hoja1.setColumnWidth(posCell, 5*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("TOTAL MON "+moneda.nickName);

			posCell++;
			hoja1.setColumnWidth(posCell, 5*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("NRO GUIA");

			posCell++;
			hoja1.setColumnWidth(posCell, 5*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("FECHA");



			//INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
			InputStream x = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
			byte[] bytes = IOUtils.toByteArray(x);
			x.close();
			int pngIndex = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			Drawing draw = hoja1.createDrawingPatriarch();
			CreationHelper helper = libro.getCreationHelper();
			ClientAnchor anchor = helper.createClientAnchor();
			//set top-left corner for the image
			anchor.setCol1(11);
			anchor.setRow1(1);
			Picture img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);

			posRow--;
			Double cant = (double)0;
			Double tot = (double)0;
			for(int i=0; i<lista.size(); i++) {
				posRow++;
				row = hoja1.createRow(posRow);
				posCell = 0;
				for (int j = 1; j < lista.get(i).size(); j++) {
					if (j == 6 || j == 8 || j == 9 || j == 10){
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						try {
							Double aux = Double.parseDouble(lista.get(i).get(j).replaceAll(",", "").trim());
										cell.setCellValue(aux);
							if (j == 8){
								cant += aux;
							}
							if (j == 10){
								tot += aux;
							}
						}catch (Exception e) {
							cell.setCellValue(lista.get(i).get(j));
						}
					} else if (j == 12){
						posCell++;
						cell = row.createCell(posCell);
						if(lista.get(i).get(j).length() > 6){
							Fechas fechax = Fechas.obtenerFechaDesdeStrAAMMDD(Fechas.AAMMDD(lista.get(i).get(j)));
							cell.setCellValue(fechax.fechaUtil);
							cell.setCellStyle(fecha);
						}else{
							cell.setCellStyle(detalle);
						}
					}else{
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellValue(lista.get(i).get(j));
					}
				}
			}

			posRow++;
			row = hoja1.createRow(posRow);
			posCell = 0;
			for (int j = 1; lista.size() > 2 && j < lista.get(0).size(); j++) {
				if (j == 1){
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellValue("TOTALES");
				} else if (j == 8){
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					try {
						Double aux = cant;
								cell.setCellValue(aux);
					}catch (Exception e) {
						cell.setCellValue("");
					}
				} else if (j == 10){
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					try {
						Double aux = tot;
								cell.setCellValue(aux);
					}catch (Exception e) {
						cell.setCellValue("");
					}
				}else{
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellValue("");
				}
			}

			row = hoja1.getRow(6);
			cell = row.createCell(6);
			cell.setCellStyle(encabezado);
			cell.setCellValue("TOTALES");

			row = hoja1.getRow(7);
			cell = row.createCell(6);
			cell.setCellStyle(detalle);
			cell.setCellValue("NETO");

			row = hoja1.getRow(8);
			cell = row.createCell(6);
			cell.setCellStyle(detalle);
			cell.setCellValue(mapDiccionario.get("IVA") + " " +tasaIva*100 + "%");

			row = hoja1.getRow(9);
			cell = row.createCell(6);
			cell.setCellStyle(detalle);
			cell.setCellValue("TOTAL");

			row = hoja1.getRow(6);
			cell = row.createCell(7);
			cell.setCellStyle(encabezado);
			cell.setCellValue("");

			row = hoja1.getRow(7);
			cell = row.createCell(7);
			cell.setCellStyle(detalle);
			Double neto = tot;
			cell.setCellValue(neto);

			row = hoja1.getRow(8);
			cell = row.createCell(7);
			cell.setCellStyle(detalle);
			cell.setCellValue(neto * 0.19);

			row = hoja1.getRow(9);
			cell = row.createCell(7);
			cell.setCellStyle(detalle);
			cell.setCellValue(neto * 1.19);

			// Write the output to a file tmp
			FileOutputStream fileOut = new FileOutputStream(tmp);
			libro.write(fileOut);
			fileOut.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return tmp;

	}

	public static File reportFacturaProyectoDetHServExcel(String db, Map<String,String> mapDiccionario, List<List<String>> lista, BodegaEmpresa bodega,
														 String fechaDesde, String fechaHasta, Cliente cliente, Proyecto proyecto, Double tasaIva){
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

			CreationHelper creationHelper = libro.getCreationHelper();

			CellStyle hora = libro.createCellStyle();
			hora.setDataFormat(creationHelper.createDataFormat().getFormat("hh:mm"));
			hora.setBorderBottom(BorderStyle.THIN);
			hora.setBorderTop(BorderStyle.THIN);
			hora.setBorderRight(BorderStyle.THIN);
			hora.setBorderLeft(BorderStyle.THIN);

			CellStyle fecha = libro.createCellStyle();
			fecha.setDataFormat(creationHelper.createDataFormat().getFormat("dd/MM/yyyy"));
			fecha.setBorderBottom(BorderStyle.THIN);
			fecha.setBorderTop(BorderStyle.THIN);
			fecha.setBorderRight(BorderStyle.THIN);
			fecha.setBorderLeft(BorderStyle.THIN);

			//********************************
			//ESTADO DE PAGO DETALLADO
			//***********************************

			libro.setSheetName(0, "EP PROFORMA DETALLE");
			Sheet hoja1 = libro.getSheetAt(0);

			Row row = null;
			Cell cell = null;

			row = hoja1.createRow(1);
			cell = row.createCell(1);
			cell.setCellStyle(titulo);
			cell.setCellValue("DETALLE EP/FACTURA PROFORMA SIMPLE");

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
			cell.setCellStyle(titulo);
			cell.setCellValue("CLIENTE: "+cliente.rut + " --- " + cliente.nombre);

			row = hoja1.createRow(6);
			cell = row.createCell(1);
			cell.setCellStyle(titulo);
			cell.setCellValue(mapDiccionario.get("BODEGA")+"/PROYECTO: "+bodega.getNombre().toUpperCase());

			row = hoja1.createRow(7);
			cell = row.createCell(1);
			cell.setCellStyle(subtitulo);
			cell.setCellValue("PROYECTO: "+proyecto.nickName.toUpperCase());

			row = hoja1.createRow(8);
			cell = row.createCell(1);
			cell.setCellStyle(subtitulo);
			cell.setCellValue("PERIODO: desde " + Fechas.DDMMAA(fechaDesde)  + " hasta " + Fechas.DDMMAA(fechaHasta));

			row = hoja1.createRow(9);
			cell = row.createCell(1);
			cell.setCellStyle(subtitulo);
			cell.setCellValue("COMERCIAL: "+bodega.comercial);

			int posRow = 11;

			row = hoja1.createRow(posRow);
			int posCell = 0;

			Double arr = (double)0;

			posRow++;
			posRow++;

			row = hoja1.createRow(posRow);

			posRow++;
			posCell = 0;


			posCell++;
			hoja1.setColumnWidth(posCell, 7*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("FAMILIA");

			posCell++;
			hoja1.setColumnWidth(posCell, 3*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("NRO.COTI");

			posCell++;
			hoja1.setColumnWidth(posCell, 5*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("COD-SERVICIO");

			posCell++;
			hoja1.setColumnWidth(posCell, 10*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("SERVICIO");

			posCell++;
			hoja1.setColumnWidth(posCell, 3*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("COD-EQUIPO");

			posCell++;
			hoja1.setColumnWidth(posCell, 5*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("EQUIPO");

			posCell++;
			hoja1.setColumnWidth(posCell, 5*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("UN");

			posCell++;
			hoja1.setColumnWidth(posCell, 5*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("CANT");

			posCell++;
			hoja1.setColumnWidth(posCell, 5*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("TOTAL");

			posCell++;
			hoja1.setColumnWidth(posCell, 5*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("CANT MIN ");

			posCell++;
			hoja1.setColumnWidth(posCell, 5*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("TOTAL MINIMO");

			posCell++;
			hoja1.setColumnWidth(posCell, 5*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("TOTAL ADICINAL");

			posCell++;
			hoja1.setColumnWidth(posCell, 5*1000);
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("GRAN TOTAL");



			//INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
			InputStream x = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
			byte[] bytes = IOUtils.toByteArray(x);
			x.close();
			int pngIndex = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			Drawing draw = hoja1.createDrawingPatriarch();
			CreationHelper helper = libro.getCreationHelper();
			ClientAnchor anchor = helper.createClientAnchor();
			//set top-left corner for the image
			anchor.setCol1(11);
			anchor.setRow1(1);
			Picture img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);

			posRow--;
			Double cant = (double)0;
			Double tot = (double)0;
			Double cantMin = (double)0;
			Double totMin = (double)0;
			Double totAdic = (double)0;
			Double granTot = (double)0;
			for(int i=0; i<lista.size(); i++) {
				posRow++;
				row = hoja1.createRow(posRow);
				posCell = 0;

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(0));

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(12));

				for (int j = 1; j < lista.get(i).size(); j++) {
					if (j <= 5){
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellValue(lista.get(i).get(j));
					}else if (j <=11){
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						try {
							Double aux = Double.parseDouble(lista.get(i).get(j).replaceAll(",", "").trim());
										cell.setCellValue(aux);
							if (j == 6){cant += aux;}
							if (j == 7){tot += aux;}
							if (j == 8){cantMin += aux;}
							if (j == 9){totMin += aux;}
							if (j == 10){totAdic += aux;}
							if (j == 11){granTot += aux;}
						}catch (Exception e) {
							cell.setCellValue(lista.get(i).get(j));
						}
					}
				}
			}

			posRow++;
			row = hoja1.createRow(posRow);
			posCell = 0;
			for (int j = 0; lista.size() > 2 && j < lista.get(0).size(); j++) {
				if (j == 3){
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellValue("TOTALES");
				} else if (j >= 7 && j <= 12){
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					try {
						Double aux = (double)0;
						if (j == 7){aux = cant;}
						if (j == 8){aux = tot;}
						if (j == 9){aux = cantMin;}
						if (j == 10){aux = totMin;}
						if (j == 11){aux = totAdic;}
						if (j == 12){aux = granTot;}
								cell.setCellValue(aux);
					}catch (Exception e) {
						cell.setCellValue("");
					}
				}else{
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellValue("");
				}
			}

			row = hoja1.getRow(6);
			cell = row.createCell(6);
			cell.setCellStyle(encabezado);
			cell.setCellValue("TOTALES");

			row = hoja1.getRow(7);
			cell = row.createCell(6);
			cell.setCellStyle(detalle);
			cell.setCellValue("NETO");

			row = hoja1.getRow(8);
			cell = row.createCell(6);
			cell.setCellStyle(detalle);
			cell.setCellValue(mapDiccionario.get("IVA") + " " +tasaIva*100 + "%");

			row = hoja1.getRow(9);
			cell = row.createCell(6);
			cell.setCellStyle(detalle);
			cell.setCellValue("TOTAL");

			row = hoja1.getRow(6);
			cell = row.createCell(7);
			cell.setCellStyle(encabezado);
			cell.setCellValue("");

			row = hoja1.getRow(7);
			cell = row.createCell(7);
			cell.setCellStyle(detalle);
			Double neto = granTot;
			cell.setCellValue(neto);

			row = hoja1.getRow(8);
			cell = row.createCell(7);
			cell.setCellStyle(detalle);
			cell.setCellValue(neto * 0.19);

			row = hoja1.getRow(9);
			cell = row.createCell(7);
			cell.setCellStyle(detalle);
			cell.setCellValue(neto * 1.19);

			// Write the output to a file tmp
			FileOutputStream fileOut = new FileOutputStream(tmp);
			libro.write(fileOut);
			fileOut.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return tmp;

	}

	public static File reportFacturaProyectoDetHAllExcel(String db, Map<String,String> mapDiccionario,
				 List<List<String>> datosSeleccionados, BodegaEmpresa bodega, String esVenta, String fechaDesde, String fechaHasta, Double usd, Double eur, Double uf, Cliente cliente, Proyecto proyecto, String oc,
				 List<List<String>> datos, Long nroDecimales, String mon,
				 Long cantDec, List<List<String>> groupPorClaseServicioEquipo, Double tasaIva){
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

			CellStyle encabezadoNro = libro.createCellStyle();
			encabezadoNro.setBorderBottom(BorderStyle.THIN);
			encabezadoNro.setBorderTop(BorderStyle.THIN);
			encabezadoNro.setBorderRight(BorderStyle.THIN);
			encabezadoNro.setBorderLeft(BorderStyle.THIN);
			encabezadoNro.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			encabezadoNro.setFillForegroundColor((short)19);
			encabezadoNro.setAlignment(HorizontalAlignment.RIGHT);

			CellStyle detalle = libro.createCellStyle();
			detalle.setBorderBottom(BorderStyle.THIN);
			detalle.setBorderTop(BorderStyle.THIN);
			detalle.setBorderRight(BorderStyle.THIN);
			detalle.setBorderLeft(BorderStyle.THIN);

			CreationHelper creationHelper = libro.getCreationHelper();

			CellStyle hora = libro.createCellStyle();
			hora.setDataFormat(creationHelper.createDataFormat().getFormat("hh:mm"));
			hora.setBorderBottom(BorderStyle.THIN);
			hora.setBorderTop(BorderStyle.THIN);
			hora.setBorderRight(BorderStyle.THIN);
			hora.setBorderLeft(BorderStyle.THIN);

			CellStyle fecha = libro.createCellStyle();
			fecha.setDataFormat(creationHelper.createDataFormat().getFormat("dd/MM/yyyy"));
			fecha.setBorderBottom(BorderStyle.THIN);
			fecha.setBorderTop(BorderStyle.THIN);
			fecha.setBorderRight(BorderStyle.THIN);
			fecha.setBorderLeft(BorderStyle.THIN);

			//********************************
			//ESTADO DE PAGO DETALLADO
			//***********************************

			libro.setSheetName(0, "EP PROFORMA DETALLE");
			Sheet hoja1 = libro.getSheetAt(0);

			Row row = null;
			Cell cell = null;

			row = hoja1.createRow(1);
			cell = row.createCell(1);
			cell.setCellStyle(titulo);
			cell.setCellValue("DETALLE EP/FACTURA PROFORMA SIMPLE");

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
			cell.setCellStyle(titulo);
			cell.setCellValue("CLIENTE: "+cliente.rut + " --- " + cliente.nombre);

			row = hoja1.createRow(6);
			cell = row.createCell(1);
			cell.setCellStyle(titulo);
			cell.setCellValue(mapDiccionario.get("BODEGA")+"/PROYECTO: "+bodega.getNombre().toUpperCase());

			row = hoja1.createRow(7);
			cell = row.createCell(1);
			cell.setCellStyle(subtitulo);
			cell.setCellValue("PROYECTO: "+proyecto.nickName.toUpperCase());

			row = hoja1.createRow(8);
			cell = row.createCell(1);
			cell.setCellStyle(subtitulo);
			cell.setCellValue("PERIODO: desde " + Fechas.DDMMAA(fechaDesde)  + " hasta " + Fechas.DDMMAA(fechaHasta));

			row = hoja1.createRow(9);
			cell = row.createCell(1);
			cell.setCellStyle(subtitulo);
			cell.setCellValue("COMERCIAL: "+bodega.comercial);


			row = hoja1.getRow(3);
			cell = row.createCell(6);
			cell.setCellStyle(encabezado);
			cell.setCellValue("TOTALES");

			row = hoja1.createRow(4);
			cell = row.createCell(6);
			cell.setCellStyle(detalle);
			cell.setCellValue("ALQUILERES:");

			row = hoja1.getRow(5);
			cell = row.createCell(6);
			cell.setCellStyle(detalle);
			cell.setCellValue("VENTAS:");

			row = hoja1.getRow(6);
			cell = row.createCell(6);
			cell.setCellStyle(detalle);
			cell.setCellValue("SERVICIOS:");

			row = hoja1.getRow(7);
			cell = row.createCell(6);
			cell.setCellStyle(encabezado);
			cell.setCellValue("NETO");

			row = hoja1.getRow(8);
			cell = row.createCell(6);
			cell.setCellStyle(encabezado);
			cell.setCellValue(mapDiccionario.get("IVA") + " " +tasaIva*100 + "%");

			row = hoja1.getRow(9);
			cell = row.createCell(6);
			cell.setCellStyle(encabezado);
			cell.setCellValue("TOTAL");

			Double totalAlquiler = (double)0;
			Double totalVenta = (double)0;
			Double totalServicio = (double)0;

			int posRow = 11;

			row = hoja1.createRow(posRow);
			int posCell = 0;

			Double arr = (double)0;


			hoja1.setColumnWidth(1, 7*1000);
			hoja1.setColumnWidth(2, 3*1000);
			hoja1.setColumnWidth(3, 5*1000);
			hoja1.setColumnWidth(4, 10*1000);
			hoja1.setColumnWidth(5, 3*1000);
			hoja1.setColumnWidth(6, 5*1000);
			hoja1.setColumnWidth(7, 5*1000);
			hoja1.setColumnWidth(8, 5*1000);
			hoja1.setColumnWidth(9, 5*1000);
			hoja1.setColumnWidth(10, 5*1000);
			hoja1.setColumnWidth(11, 5*1000);
			hoja1.setColumnWidth(12, 5*1000);
			hoja1.setColumnWidth(13, 5*1000);
			hoja1.setColumnWidth(14, 5*1000);
			hoja1.setColumnWidth(15, 5*1000);
			//INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
			InputStream x = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
			byte[] bytes = IOUtils.toByteArray(x);
			x.close();
			int pngIndex = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			Drawing draw = hoja1.createDrawingPatriarch();
			CreationHelper helper = libro.getCreationHelper();
			ClientAnchor anchor = helper.createClientAnchor();
			//set top-left corner for the image
			anchor.setCol1(13);
			anchor.setRow1(1);
			Picture img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);

			if(datosSeleccionados.size()>0) {
				//ALQUILERES:
				posRow++;
				posRow++;
				row = hoja1.createRow(posRow);
				cell = row.createCell(1);
				cell.setCellStyle(titulo);
				cell.setCellValue("ALQUILERES:");

				posRow++;
				row = hoja1.createRow(posRow);
				posRow++;
				posCell = 0;

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellValue("GRUPO");

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellValue("Nro.Coti");

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellValue("Codigo");

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellValue("Descripcion");

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellValue("Moneda");

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellValue("Precio Venta");

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellValue(mapDiccionario.get("Arriendo") + "Mes");

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellValue(mapDiccionario.get("Arriendo") + "Dia");

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellValue("Cant Dias");

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellValue("TOTAL MON ORIGEN");

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellValue("TOTAL MON CLP");

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellValue("NRO GUIA ULTIMO INGRESO");

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellValue("FECHA INI/TER INGRESO");

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellValue("NRO GUIA ULTIMA SALIDA");

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellValue("FECHA INI/TER SALIDA");


				posRow--;
				for (int i = 0; i < datosSeleccionados.size(); i++) {
					if (i > 2) {
						posRow++;
						row = hoja1.createRow(posRow);
						posCell = 0;

						for (int j = 0; j < datosSeleccionados.get(i).size(); j++) {
							if (j < 4) {
								posCell++;
								cell = row.createCell(posCell);
								cell.setCellStyle(detalle);
								cell.setCellValue(datosSeleccionados.get(i).get(j));
							} else if (j == 12 || j == 14) {
								posCell++;
								cell = row.createCell(posCell);
								if (datosSeleccionados.get(i).get(j).length() > 6) {
									Fechas fechax = Fechas.obtenerFechaDesdeStrAAMMDD(Fechas.AAMMDD(datosSeleccionados.get(i).get(j)));
									cell.setCellValue(fechax.fechaUtil);
									cell.setCellStyle(fecha);
								} else {
									cell.setCellStyle(detalle);
								}
							} else {
								posCell++;
								cell = row.createCell(posCell);
								cell.setCellStyle(detalle);
								try {
									Double aux = Double.parseDouble(datosSeleccionados.get(i).get(j).replaceAll(",", "").trim());
														cell.setCellValue(aux);
								} catch (Exception e) {
									cell.setCellValue(datosSeleccionados.get(i).get(j));
								}
							}
						}
					}
				}
			}

			if(datos.size()>0) {
				//VENTAS:
				posRow++;
				posRow++;
				row = hoja1.createRow(posRow);
				cell = row.createCell(1);
				cell.setCellStyle(titulo);
				cell.setCellValue("VENTAS:");

				posRow++;
				row = hoja1.createRow(posRow);
				posRow++;
				posCell = 0;

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellValue("GRUPO");

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellValue("COTI");

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellValue("CODIGO");

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellValue("EQUIPO");

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellValue("MONEDA");

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellValue("PRECIO UNITARIO");

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellValue("UNIDAD");

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellValue("CANTIDAD");

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellValue("TOTAL MON ORIGEN");

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellValue("TOTAL MON " + mon);

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellValue("NRO GUIA");

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellValue("FECHA");

				posRow--;
				Double cant = (double) 0;
				Double tot = (double) 0;
				for (int i = 0; i < datos.size(); i++) {
					posRow++;
					row = hoja1.createRow(posRow);
					posCell = 0;
					for (int j = 1; j < datos.get(i).size(); j++) {
						if (j == 6 || j == 8 || j == 9 || j == 10) {
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							try {
								Double aux = Double.parseDouble(datos.get(i).get(j).replaceAll(",", "").trim());
												cell.setCellValue(aux);
								if (j == 8) {
									cant += aux;
								}
								if (j == 10) {
									tot += aux;
								}
							} catch (Exception e) {
								cell.setCellValue(datos.get(i).get(j));
							}
						} else if (j == 12) {
							posCell++;
							cell = row.createCell(posCell);
							if (datos.get(i).get(j).length() > 6) {
								Fechas fechax = Fechas.obtenerFechaDesdeStrAAMMDD(Fechas.AAMMDD(datos.get(i).get(j)));
								cell.setCellValue(fechax.fechaUtil);
								cell.setCellStyle(fecha);
							} else {
								cell.setCellStyle(detalle);
							}
						} else {
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							cell.setCellValue(datos.get(i).get(j));
						}
					}
				}

				posRow++;
				row = hoja1.createRow(posRow);
				posCell = 0;
				for (int j = 1; j < datos.get(0).size(); j++) {
					if (j == 1) {
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellValue("TOTALES");
					} else if (j == 8) {
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						try {
							Double aux = cant;
										cell.setCellValue(aux);
						} catch (Exception e) {
							cell.setCellValue("");
						}
					} else if (j == 10) {
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						try {
							Double aux = tot;
										cell.setCellValue(aux);
							totalVenta = aux;
						} catch (Exception e) {
							cell.setCellValue("");
						}
					} else {
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellValue("");
					}
				}
			}

			if(groupPorClaseServicioEquipo.size()>0) {
				// SERVICIOS:
				posRow++;
				posRow++;
				row = hoja1.createRow(posRow);
				cell = row.createCell(1);
				cell.setCellStyle(titulo);
				cell.setCellValue("SERVICIOS:");

				posRow++;
				row = hoja1.createRow(posRow);
				posRow++;
				posCell = 0;


				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellValue("FAMILIA");

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellValue("NRO.COTI");

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellValue("COD-SERVICIO");

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellValue("SERVICIO");

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellValue("COD-EQUIPO");

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellValue("EQUIPO");

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellValue("UN");

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellValue("CANT");

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellValue("TOTAL");

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellValue("CANT MIN ");

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellValue("TOTAL MINIMO");

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellValue("TOTAL ADICINAL");

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellValue("GRAN TOTAL");


				posRow--;
				Double cant = (double) 0;
				Double tot = (double) 0;
				Double cantMin = (double) 0;
				Double totMin = (double) 0;
				Double totAdic = (double) 0;
				Double granTot = (double) 0;
				for (int i = 0; i < groupPorClaseServicioEquipo.size(); i++) {
					posRow++;
					row = hoja1.createRow(posRow);
					posCell = 0;

					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellValue(groupPorClaseServicioEquipo.get(i).get(0));

					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellValue(groupPorClaseServicioEquipo.get(i).get(12));

					for (int j = 1; j < groupPorClaseServicioEquipo.get(i).size(); j++) {
						if (j <= 5) {
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							cell.setCellValue(groupPorClaseServicioEquipo.get(i).get(j));
						} else if (j <= 11) {
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							try {
								Double aux = Double.parseDouble(groupPorClaseServicioEquipo.get(i).get(j).replaceAll(",", "").trim());
												cell.setCellValue(aux);
								if (j == 6) {
									cant += aux;
								}
								if (j == 7) {
									tot += aux;
								}
								if (j == 8) {
									cantMin += aux;
								}
								if (j == 9) {
									totMin += aux;
								}
								if (j == 10) {
									totAdic += aux;
								}
								if (j == 11) {
									granTot += aux;
								}
							} catch (Exception e) {
								cell.setCellValue(groupPorClaseServicioEquipo.get(i).get(j));
							}
						}
					}
				}

				posRow++;
				row = hoja1.createRow(posRow);
				posCell = 0;
				for (int j = 0; j < groupPorClaseServicioEquipo.get(0).size(); j++) {
					if (j == 3) {
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellValue("TOTALES");
					} else if (j >= 7 && j <= 12) {
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						try {
							Double aux = (double) 0;
							if (j == 7) {
								aux = cant;
							}
							if (j == 8) {
								aux = tot;
							}
							if (j == 9) {
								aux = cantMin;
							}
							if (j == 10) {
								aux = totMin;
							}
							if (j == 11) {
								aux = totAdic;
							}
							if (j == 12) {
								aux = granTot;
							}
										cell.setCellValue(aux);
							totalServicio = aux;
						} catch (Exception e) {
							cell.setCellValue("");
						}
					} else {
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellValue("");
					}
				}
			}


			row = hoja1.getRow(3);
			cell = row.createCell(7);
			cell.setCellStyle(encabezado);
			cell.setCellValue("");

			row = hoja1.getRow(4);
			cell = row.createCell(7);
			cell.setCellStyle(detalle);
			try {
				Double aux = Double.parseDouble(datosSeleccionados.get(datosSeleccionados.size()-1).get(10).replaceAll(",", "").trim());
				cell.setCellValue(aux);
				totalAlquiler = aux;
			} catch (Exception e) {
				cell.setCellValue(totalAlquiler);
			}

			row = hoja1.getRow(5);
			cell = row.createCell(7);
			cell.setCellStyle(detalle);
			cell.setCellValue(totalVenta);

			row = hoja1.getRow(6);
			cell = row.createCell(7);
			cell.setCellStyle(detalle);
			cell.setCellValue(totalServicio);

			row = hoja1.getRow(7);
			cell = row.createCell(7);
			cell.setCellStyle(encabezadoNro);
			Double neto = totalAlquiler + totalVenta + totalServicio;
			cell.setCellValue(neto);

			row = hoja1.getRow(8);
			cell = row.createCell(7);
			cell.setCellStyle(encabezadoNro);
			cell.setCellValue(neto * 0.19);

			row = hoja1.getRow(9);
			cell = row.createCell(7);
			cell.setCellStyle(encabezadoNro);
			cell.setCellValue(neto * 1.19);


			// Write the output to a file tmp
			FileOutputStream fileOut = new FileOutputStream(tmp);
			libro.write(fileOut);
			fileOut.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return tmp;

	}


	
	public static Map<String, List<List<String>>> mapResumenPorGrupo2(Connection con, String db, List<ModeloCalculo> valorTotalporBodegaYGrupo){
		Map<String, List<List<String>>> map = new HashMap<String, List<List<String>>>();
		Long flagId_BodegaEmpresa = (long) 0;
		List<List<String>> lista = new ArrayList<List<String>>();
		Map<Long,Grupo> mapGrupo = Grupo.mapAll(con, db);
		Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
		switch(dec.get((long) 1).toString()) {
		 case "0": myformatdouble = new DecimalFormat("#,##0",symbols); break;
		 case "2": myformatdouble = new DecimalFormat("#,##0.00",symbols); break;
		 case "4": myformatdouble = new DecimalFormat("#,##0.0000",symbols); break;
		 case "6": myformatdouble = new DecimalFormat("#,##0.000000",symbols); break;
		 default:  break;
		}
		for(int i=0;i<valorTotalporBodegaYGrupo.size();i++){
			if((flagId_BodegaEmpresa - valorTotalporBodegaYGrupo.get(i).id_bodegaEmpresa) != 0) {
				if(flagId_BodegaEmpresa>0) {
					map.put(flagId_BodegaEmpresa.toString(), lista);
					lista = new ArrayList<List<String>>();
				}
				flagId_BodegaEmpresa = valorTotalporBodegaYGrupo.get(i).id_bodegaEmpresa;
			}
			List<String> aux = new ArrayList<String>();
			aux.add(mapGrupo.get(valorTotalporBodegaYGrupo.get(i).id_grupo).nombre); 			// 0 nombre de grupo
			aux.add(myformatdouble.format(valorTotalporBodegaYGrupo.get(i).totalArriendo)); 	// 1 total arriendo en pesos
			aux.add(myformatdouble.format(valorTotalporBodegaYGrupo.get(i).totalVenta)); 		// 2 total venta en pesos
			aux.add(myformatdouble.format(valorTotalporBodegaYGrupo.get(i).totalCfi)); 			// 3 total cfi en pesos
			lista.add(aux);
			if(i == valorTotalporBodegaYGrupo.size()-1) {
				map.put(flagId_BodegaEmpresa.toString(), lista);
			}
		}
		return(map);
	}
	
	public static List<List<String>> resumenPorGrupoYProyecto (Connection con, String db,List<List<String>> proyectos, Map<String, List<List<String>>> mapResumenSubtotales ){
		List<List<String>> listaAux = new ArrayList<List<String>>();
		
		Map<String,BodegaEmpresa> mapBodega = BodegaEmpresa.mapAllNombreExternas(con, db);
		
		for(int i=0;i<proyectos.size();i++){
			List<List<String>> resumenSubtotales = mapResumenSubtotales.get(proyectos.get(i).get(11));
			
			for(int j=0; resumenSubtotales!=null && j<resumenSubtotales.size(); j++){
				
				BodegaEmpresa bodega = mapBodega.get(proyectos.get(i).get(5).toUpperCase());
				
				if(bodega!=null) {
					List<String> aux = new ArrayList<String>();
					aux.add(resumenSubtotales.get(j).get(0));		//0 grupo
					aux.add(proyectos.get(i).get(5));				//1 bodegaEmpresa
					aux.add(proyectos.get(i).get(6));				//2 rut
					aux.add(proyectos.get(i).get(7));				//3 cliente
					aux.add(resumenSubtotales.get(j).get(1));		//4 total arriendo
					aux.add(resumenSubtotales.get(j).get(2));		//5 total compra
					aux.add(resumenSubtotales.get(j).get(3));		//6 total CFI
					aux.add(proyectos.get(i).get(8));				//7 proyecto
					
					aux.add(bodega.getNameSucursal());							//8 sucursal
					
					String auxArriendo=resumenSubtotales.get(j).get(1);
					String auxVenta=resumenSubtotales.get(j).get(2);
					String auxCfi=resumenSubtotales.get(j).get(3);
					auxArriendo=auxArriendo.replaceAll(",", "");
					auxVenta=auxVenta.replaceAll(",", "");
					auxCfi=auxCfi.replaceAll(",", "");
					
					if(Double.parseDouble(auxArriendo.trim())>0||Double.parseDouble(auxVenta.trim())>0||Double.parseDouble(auxCfi.trim())>0) {
						listaAux.add(aux);
					}
				}
				
			}
		}
		
		//ordena por grupos alfabeticamente ascendente
		for(int i=0;i<(listaAux.size()-1);i++){
	        for(int j=i+1;j<listaAux.size();j++){
	            if((listaAux.get(i).get(0)+listaAux.get(i).get(1)).
	            		compareToIgnoreCase(listaAux.get(j).get(0)+listaAux.get(j).get(1))>0){
	                //Intercambiamos valores
	                List<String> listaAuxiliar=listaAux.get(i);
	                listaAux.set(i, listaAux.get(j));
	                listaAux.set(j, listaAuxiliar);
	            }
	        }
	    }
	
		List<List<String>> listaRs = new ArrayList<List<String>>();
		String auxGrupo="";
		Double subtotalA=(double)0;
		Double subtotalV=(double)0;
		Double subtotalCfi=(double)0;
		Double totalA=(double)0;
		Double totalV=(double)0;
		Double totalCfi=(double)0;
		
		Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
		switch(dec.get((long)1).toString()) {
		 case "0": myformatMonedaPrincipal = new DecimalFormat("#,##0",symbols); break;
		 case "2": myformatMonedaPrincipal = new DecimalFormat("#,##0.00",symbols); break;
		 case "4": myformatMonedaPrincipal = new DecimalFormat("#,##0.0000",symbols); break;
		 case "6": myformatMonedaPrincipal = new DecimalFormat("#,##0.000000",symbols); break;
		 default:  break;
		}
		
		
		for(int i=0;i<listaAux.size();i++){
			if(!listaAux.get(i).get(0).equals(auxGrupo)){
				subtotalA=(double)0;
				subtotalV=(double)0;
				subtotalCfi=(double)0;
				auxGrupo=listaAux.get(i).get(0);
				
	
				Double auxSubTotA = (double)0;
				Double auxSubTotV = (double)0;
				Double auxSubTotCfi = (double)0;
				
				String auxNum = listaAux.get(i).get(4).trim();
		   			if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
				try {auxSubTotA=myformatdouble2.parse(auxNum).doubleValue();}catch(ParseException e) {}
				
				auxNum = listaAux.get(i).get(5).trim();
		   			if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
				try {auxSubTotV=myformatdouble2.parse(auxNum).doubleValue();}catch (ParseException e) {}
				
				auxNum = listaAux.get(i).get(6).trim();
		   			if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
				try {auxSubTotCfi=myformatdouble2.parse(auxNum).doubleValue();}catch (ParseException e) {}
				subtotalA=subtotalA+auxSubTotA;
				subtotalV=subtotalV+auxSubTotV;
				subtotalCfi=subtotalCfi+auxSubTotCfi;
				
				List<String> aux1 = new ArrayList<String>();
				aux1.add(listaAux.get(i).get(0));
				aux1.add("");
				aux1.add("");
				aux1.add("");
				aux1.add("");
				aux1.add("");
				aux1.add("");
				aux1.add("");
				aux1.add("");
				aux1.add(listaAux.get(i).get(8));
				listaRs.add(aux1);
				
				List<String> aux2 = new ArrayList<String>();
				aux2.add("");
				aux2.add(listaAux.get(i).get(1));
				aux2.add(listaAux.get(i).get(2));
				aux2.add(listaAux.get(i).get(3));
				aux2.add(listaAux.get(i).get(6));
				aux2.add(listaAux.get(i).get(4));
				aux2.add(listaAux.get(i).get(5));
				aux2.add(myformatMonedaPrincipal.format(auxSubTotA+auxSubTotV+auxSubTotCfi));
				aux2.add(listaAux.get(i).get(7));
				aux2.add(listaAux.get(i).get(8));
				listaRs.add(aux2);
			}else{
				
				Double auxSubTotA = (double)0;
				Double auxSubTotV = (double)0;
				Double auxSubTotCfi = (double)0;
				
				String auxNum = listaAux.get(i).get(4).trim();
		   			if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
				try {auxSubTotA=myformatdouble2.parse(auxNum).doubleValue();}catch(ParseException e) {}
				
				auxNum = listaAux.get(i).get(5).trim();
		   			if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
				try {auxSubTotV=myformatdouble2.parse(auxNum).doubleValue();} catch (ParseException e) {}
				
				auxNum = listaAux.get(i).get(6).trim();
		   			if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
				try {auxSubTotCfi=myformatdouble2.parse(auxNum).doubleValue();}catch (ParseException e) {}
				subtotalA=subtotalA+auxSubTotA;
				subtotalV=subtotalV+auxSubTotV;
				subtotalCfi=subtotalCfi+auxSubTotCfi;
				
				List<String> aux3 = new ArrayList<String>();
				aux3.add("");
				aux3.add(listaAux.get(i).get(1));
				aux3.add(listaAux.get(i).get(2));
				aux3.add(listaAux.get(i).get(3));
				aux3.add(listaAux.get(i).get(6));
				aux3.add(listaAux.get(i).get(4));
				aux3.add(listaAux.get(i).get(5));
				aux3.add(myformatMonedaPrincipal.format(auxSubTotA+auxSubTotV+auxSubTotCfi));
				aux3.add(listaAux.get(i).get(7));
				aux3.add(listaAux.get(i).get(8));
				listaRs.add(aux3);
			}
			int x = 0;
			if(i<listaAux.size()-1) x = i+1; else x=i;
			if(!listaAux.get(x).get(0).equals(auxGrupo)){
				List<String> aux4 = new ArrayList<String>();
				aux4.add("");
				aux4.add("");
				aux4.add("");
				aux4.add("SUBTOTAL");
				aux4.add(myformatMonedaPrincipal.format(subtotalCfi));
				aux4.add(myformatMonedaPrincipal.format(subtotalA));
				aux4.add(myformatMonedaPrincipal.format(subtotalV));
				aux4.add(myformatMonedaPrincipal.format(subtotalA+subtotalV+subtotalCfi));
				aux4.add("");
				aux4.add(listaAux.get(i).get(8));
				listaRs.add(aux4);
				totalA=totalA+subtotalA;
				totalV=totalV+subtotalV;
				totalCfi=totalCfi+subtotalCfi;
			}
			if(x==i){
				List<String> aux4 = new ArrayList<String>();
				aux4.add("");
				aux4.add("");
				aux4.add("");
				aux4.add("SUBTOTAL");
				aux4.add(myformatMonedaPrincipal.format(subtotalCfi));
				aux4.add(myformatMonedaPrincipal.format(subtotalA));
				aux4.add(myformatMonedaPrincipal.format(subtotalV));
				aux4.add(myformatMonedaPrincipal.format(subtotalA+subtotalV+subtotalCfi));
				aux4.add("");
				aux4.add(listaAux.get(i).get(8));
				listaRs.add(aux4);
				totalA=totalA+subtotalA;
				totalV=totalV+subtotalV;
				totalCfi=totalCfi+subtotalCfi;
			}
			
		}
		List<String> aux4 = new ArrayList<String>();
		aux4.add("");
		aux4.add("");
		aux4.add("");
		aux4.add("");
		aux4.add("");
		aux4.add("");
		aux4.add("");
		aux4.add("");
		aux4.add("");
		aux4.add("");
		listaRs.add(aux4);
		List<String> aux5 = new ArrayList<String>();
		aux5.add("SUBTOTALES");
		aux5.add("");
		aux5.add("");
		aux5.add("");
		aux5.add(myformatMonedaPrincipal.format(totalCfi));
		aux5.add(myformatMonedaPrincipal.format(totalA));
		aux5.add(myformatMonedaPrincipal.format(totalV));
		aux5.add(myformatMonedaPrincipal.format(totalA+totalV+totalCfi));
		aux5.add("");
		aux4.add("");
		listaRs.add(aux5);
		List<String> aux6 = new ArrayList<String>();
		aux6.add("");
		aux6.add("");
		aux6.add("");
		aux6.add("TOTAL");
		aux6.add("");
		aux6.add(myformatint.format(totalA+totalV));
		aux6.add(myformatMonedaPrincipal.format(totalA+totalV+totalCfi));
		aux6.add("");
		aux6.add("");
		aux4.add("");
		listaRs.add(aux6);
		
		return(listaRs);
	}
	
	public static Map<String, List<List<String>>> mapInicioPerAllBodegas(Connection con, String db,List<ModCalc_InvInicial> invInicicial){
		Map<String, List<List<String>>> map = new HashMap<String, List<List<String>>>();
		Map<Long,Equipo> mapEquipo = Equipo.mapAllAll(con, db);
		Map<Long,String> mapMoneda = Moneda.mapIdMonedaMoneda(con, db);
		Map<Long,Cotizacion> mapCotizacion = Cotizacion.mapAll(con, db);
		
		List<List<String>> lista = new ArrayList<List<String>>();
		Long flagId_BodegaEmpresa = (long) 0;
		
		Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
		switch(dec.get((long)1).toString()) {
		 case "0": myformatMonedaPrincipal = new DecimalFormat("#,##0",symbols); break;
		 case "2": myformatMonedaPrincipal = new DecimalFormat("#,##0.00",symbols); break;
		 case "4": myformatMonedaPrincipal = new DecimalFormat("#,##0.0000",symbols); break;
		 case "6": myformatMonedaPrincipal = new DecimalFormat("#,##0.000000",symbols); break;
		 default:  break;
		}
		
		for(int i=0; i<invInicicial.size(); i++) {
			if(invInicicial.get(i).cantidad > 0) {
				if((flagId_BodegaEmpresa - invInicicial.get(i).id_bodegaEmpresa) != 0) {
					if(i>0) {
						// ordena la lista por grupo y nombre equipo alfabeticamente ascendente
						for(int k=0;k<(lista.size()-1);k++){
				            for(int j=k+1;j<lista.size();j++){
				            	String A = lista.get(k).get(7) + "_" + lista.get(k).get(10);
        		            	String B = lista.get(k+1).get(7) + "_" + lista.get(k+1).get(10);
				                if(A.compareToIgnoreCase(B) > 0){
				                    //Intercambiamos valores
				                    List<String> listaAuxiliar=lista.get(k);
				                    lista.set(k, lista.get(j));
				                    lista.set(j, listaAuxiliar);
				                }
				            }
						}
			            //agrega al mapa
			            map.put(flagId_BodegaEmpresa.toString(), lista);
			            lista = new ArrayList<List<String>>();
					}
					flagId_BodegaEmpresa = invInicicial.get(i).id_bodegaEmpresa;
				}
				//OBTIENE DETALLE
				List<String> aux = new ArrayList<String>();
				Long id_bodegaEmpresa = invInicicial.get(i).id_bodegaEmpresa;
				Long id_equipo = invInicicial.get(i).id_equipo;
				Long id_cotizacion = invInicicial.get(i).id_cotizacion;
				Double cantidad = invInicicial.get(i).cantidad;
				Double pVenta = invInicicial.get(i).pVenta;
				Double pArr_dia = invInicicial.get(i).pArr_dia;
				Long dias = invInicicial.get(i).dias;
				Double tasaCambio = invInicicial.get(i).tasaCambio;
				Double totalArriendo = invInicicial.get(i).totalArriendo;
				Long esVenta = invInicicial.get(i).esVenta;
				String fecha_primera_guia = invInicicial.get(i).fecha_primera_guia;
				if(fecha_primera_guia==null) {
					
					fecha_primera_guia = "";
				}
				String grupo = "", codEquipo = "", nomEquipo = "", unidad = "";
				Equipo equipo = mapEquipo.get(invInicicial.get(i).id_equipo);
				if(equipo!=null) {
					grupo = equipo.grupo;
					codEquipo = equipo.codigo;
					nomEquipo = equipo.nombre;
					unidad = equipo.unidad;
				}

				String numCotizacion = "0", dctoVenta = "0.00 %", dctoArriendo = "0.00 %";
				
				Cotizacion coti = mapCotizacion.get(id_cotizacion);
				if(coti!=null) {
					numCotizacion = coti.numero.toString();
					dctoVenta = myformatdouble2.format(coti.getDctoVenta()*100)+" %";
					dctoArriendo = myformatdouble2.format(coti.getDctoArriendo()*100)+" %";
				}
				
				String moneda = mapMoneda.get(invInicicial.get(i).id_moneda); 
				if(moneda==null) {
					moneda = "";
				}

				switch(dec.get(invInicicial.get(i).id_moneda).toString()) {
				 case "0": myformatMoneda = new DecimalFormat("#,##0",symbols); break;
				 case "2": myformatMoneda = new DecimalFormat("#,##0.00",symbols); break;
				 case "4": myformatMoneda = new DecimalFormat("#,##0.0000",symbols); break;
				 case "6": myformatMoneda = new DecimalFormat("#,##0.000000",symbols); break;
				 default:  break;
				}
				
				aux.add(id_bodegaEmpresa.toString()); 		// 0 idBodegaEmpresa
				aux.add(id_equipo.toString()); 				// 1 idEquipo
				aux.add("0"); 								// 2 idTipoMovimiento
				aux.add("0"); 								// 3 idGuia
				aux.add("0"); 								// 4 idUnidad
				aux.add("0"); 								// 5 idUnidadTiempo
				aux.add("0"); 								// 6 idMoneda
				aux.add(grupo); 							// 7 nombre de grupo
				aux.add(numCotizacion); 					// 8 numero cotizacion
				aux.add(codEquipo); 						// 9 codigo equipo
				aux.add(nomEquipo + " (ARRIENDO)"); 		// 10 nombre equipo
				aux.add(unidad); 							// 11 unidad
				aux.add(myformatdouble2.format(cantidad)); 		// 12 cantidad
				aux.add(moneda); 							// 13 moneda
				aux.add(myformatMoneda.format(pVenta)); 	// 14 precioVenta
				if(db.equals("madaHohe")){
					aux.add(myformatdouble.format(pArr_dia)); 			// 15 precioArriendo por día
				}else {
					aux.add(myformatMoneda.format(pArr_dia)); 			// 15 precioArriendo por día
				}
				aux.add(myformatint.format(dias));						// 16 cantidad de dias a cobrar
				aux.add(myformatdouble2.format(tasaCambio));			// 17 tasa de cambio
				aux.add(myformatMonedaPrincipal.format(totalArriendo));	// 18 total arriendo en pesos
				aux.add("0");											// 19 total venta en pesos
				aux.add(esVenta.toString()); 							// 20 es venta (1)
				aux.add(Fechas.DDMMAA(fecha_primera_guia)); 			// 21 fecha primera guia
				aux.add("0"); 											// 22 cfi en moneda original
				aux.add("0"); 											// 23 cfi en pesos
				aux.add(dctoVenta);  									//24 % dcto arriendo
				aux.add(dctoArriendo); 									//25 % dcto venta
				lista.add(aux);
				if(i == invInicicial.size()-1) {
					// ordena la lista por grupo y nombre equipo alfabeticamente ascendente
					for(int k=0;k<(lista.size()-1);k++){
			            for(int j=k+1;j<lista.size();j++){
			            	String A = lista.get(k).get(7) + "_" + lista.get(k).get(10);
    		            	String B = lista.get(k+1).get(7) + "_" + lista.get(k+1).get(10);
			                if(A.compareToIgnoreCase(B) > 0){
			                    //Intercambiamos valores
			                    List<String> listaAuxiliar=lista.get(k);
			                    lista.set(k, lista.get(j));
			                    lista.set(j, listaAuxiliar);
			                }
			            }
					}
		            //agrega al mapa
		            map.put(flagId_BodegaEmpresa.toString(), lista);
		            lista = new ArrayList<List<String>>();
				}
			}
		}
		return map;
	}
	
	public static Map<String, List<List<String>>> mapGuiasPer(Connection con, String db, List<ModCalc_GuiasPer> guiasPer){
		Map<String, List<List<String>>> map = new HashMap<String, List<List<String>>>();
		Map<Long,Equipo> mapEquipo = Equipo.mapAllAll(con, db);
		Map<Long,String> mapMoneda = Moneda.mapIdMonedaMoneda(con, db);
		Map<Long,Cotizacion> mapCotizacion = Cotizacion.mapAll(con, db);
		
		List<List<String>> lista = new ArrayList<List<String>>();
		Long flagId_BodegaEmpresa = (long) 0;
		
		Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
		switch(dec.get((long)1).toString()) {
		 case "0": myformatMonedaPrincipal = new DecimalFormat("#,##0",symbols); break;
		 case "2": myformatMonedaPrincipal = new DecimalFormat("#,##0.00",symbols); break;
		 case "4": myformatMonedaPrincipal = new DecimalFormat("#,##0.0000",symbols); break;
		 case "6": myformatMonedaPrincipal = new DecimalFormat("#,##0.000000",symbols); break;
		 default:  break;
		}
		
		for(int i=0; i<guiasPer.size(); i++) {
			if(guiasPer.get(i).cantidad != 0) {
				if((flagId_BodegaEmpresa - guiasPer.get(i).id_bodegaEmpresa) != 0) {
					if(i>0) {
						// ordena la lista por grupo y nombre equipo alfabeticamente ascendente
						for(int k=0;k<(lista.size()-1);k++){
				            for(int j=k+1;j<lista.size();j++){
				            	String A = lista.get(k).get(7) + "_" + lista.get(k).get(10);
	    		            	String B = lista.get(k+1).get(7) + "_" + lista.get(k+1).get(10);
				                if(A.compareToIgnoreCase(B) > 0){
				                    //Intercambiamos valores
				                    List<String> listaAuxiliar=lista.get(k);
				                    lista.set(k, lista.get(j));
				                    lista.set(j, listaAuxiliar);
				                }
				            }
						}
			            //agrega al mapa
			            map.put(flagId_BodegaEmpresa.toString(), lista);
			            lista = new ArrayList<List<String>>();
				        
					}
					flagId_BodegaEmpresa = guiasPer.get(i).id_bodegaEmpresa;
				}
				//OBTIENE DETALLE
				
				Long id_cotizacion = guiasPer.get(i).id_cotizacion;
				String grupo = "", codEquipo = "", nomEquipo = "", unidad = "", idUnidad = "";
				Equipo equipo = mapEquipo.get(guiasPer.get(i).id_equipo);
				if(equipo!=null) {
					grupo = equipo.grupo;
					codEquipo = equipo.codigo;
					nomEquipo = equipo.nombre;
					unidad = equipo.unidad;
					idUnidad = equipo.id_unidad.toString();
				}
				
				String numCotizacion = "0", dctoVenta = "0.00 %", dctoArriendo = "0.00 %";
				
				Cotizacion coti = mapCotizacion.get(id_cotizacion);
				if(coti!=null) {
					numCotizacion = coti.numero.toString();
					dctoVenta = myformatdouble2.format(coti.getDctoVenta()*100)+" %";
					dctoArriendo = myformatdouble2.format(coti.getDctoArriendo()*100)+" %";
				}
				
				String moneda = mapMoneda.get(guiasPer.get(i).id_moneda); if(moneda==null) moneda = "";
				
				switch(dec.get(guiasPer.get(i).id_moneda).toString()) {
				 case "0": myformatMoneda = new DecimalFormat("#,##0",symbols); break;
				 case "2": myformatMoneda = new DecimalFormat("#,##0.00",symbols); break;
				 case "4": myformatMoneda = new DecimalFormat("#,##0.0000",symbols); break;
				 case "6": myformatMoneda = new DecimalFormat("#,##0.000000",symbols); break;
				 default:  break;
				}
				
				List<String> aux = new ArrayList<String>();
				aux.add(guiasPer.get(i).id_bodegaEmpresa.toString()); 		// 0 idBodegaEmpresa
				aux.add(guiasPer.get(i).id_equipo.toString()); 				// 1 idEquipo
				aux.add(guiasPer.get(i).id_tipoMovimiento.toString()); 		// 2 idTipoMovimiento
				aux.add(guiasPer.get(i).id_guia.toString()); 				// 3 idGuia
				aux.add(idUnidad); 											// 4 idUnidad
				aux.add("0"); 												// 5 idUnidadTiempo
				aux.add(guiasPer.get(i).id_moneda.toString()); 				// 6 idMoneda
				aux.add(grupo); 											// 7 nombre de grupo
				aux.add(numCotizacion); 									// 8 numero cotizacion
				aux.add(codEquipo); 										// 9 codigo equipo
				aux.add(nomEquipo); 										// 10 nombre equipo
				aux.add(unidad); 											// 11 unidad
				aux.add(myformatdouble2.format(guiasPer.get(i).cantidad)); 		// 12 cantidad
				aux.add(moneda); 											// 13 moneda
				aux.add(myformatMoneda.format(guiasPer.get(i).pVenta)); 	// 14 precioVenta
				if(db.equals("madaHohe")){
					aux.add(myformatdouble.format(guiasPer.get(i).pArr_dia)); 			// 15 precioArriendo por día
				}else {
					aux.add(myformatMoneda.format(guiasPer.get(i).pArr_dia)); 			// 15 precioArriendo por día
				}
				aux.add(myformatint.format(guiasPer.get(i).dias));						// 16 cantidad de dias a cobrar
				aux.add(myformatdouble2.format(guiasPer.get(i).tasaCambio));			// 17 tasa de cambio
				aux.add(myformatMonedaPrincipal.format(guiasPer.get(i).totalArriendo));	// 18 total arriendo en pesos
				aux.add(myformatMonedaPrincipal.format(guiasPer.get(i).totalVenta));		// 19 total venta en pesos
				aux.add(guiasPer.get(i).esVenta.toString()); 							// 20 es venta (1)
				aux.add(""); 															// 21 fecha primera guia
				aux.add("0"); 															// 22 cfi en moneda original
				aux.add(myformatMonedaPrincipal.format(guiasPer.get(i).totalCfi)); 		// 23 cfi en pesos
				aux.add(dctoVenta);  													// 24 % dcto arriendo
				aux.add(dctoArriendo); 													// 25 % dcto venta
				
				
				
				lista.add(aux);
				
				if(i == guiasPer.size()-1) {
					// ordena la lista por grupo y nombre equipo alfabeticamente ascendente
					for(int k=0;k<(lista.size()-1);k++){
			            for(int j=k+1;j<lista.size();j++){
			            	String A = lista.get(k).get(7) + "_" + lista.get(k).get(10);
			            	String B = lista.get(k+1).get(7) + "_" + lista.get(k+1).get(10);
			                if(A.compareToIgnoreCase(B) > 0){
			                    //Intercambiamos valores
			                    List<String> listaAuxiliar=lista.get(k);
			                    lista.set(k, lista.get(j));
			                    lista.set(j, listaAuxiliar);
			                }
			            }
					}
		            //agrega al mapa
		            map.put(flagId_BodegaEmpresa.toString(), lista);
		            lista = new ArrayList<List<String>>();
				}
			}
		}
		return(map);
	}
	
	public static List<List<String>> resumenEstadosDePago (Connection con, String db,List<List<String>> proyectos,
			String desdeSql,String hastaSql,String nEmpresa, Map<String, List<List<String>>> mapInicioPer, Map<String, List<List<String>>> mapGuiasPer ){
		
		List<List<String>> listaRs = new ArrayList<List<String>>();
		
		Map<Long,Guia> mapGuias = Guia.mapAll(con, db);
		
		Map<Long,List<List<String>>> mapDetalleAjuste = AjustesEP.mapDetalleAjuste(con, db, desdeSql, hastaSql);
				
		for(int i=0;i<proyectos.size();i++){
			Long id_bodegaEmpresa = Long.parseLong(proyectos.get(i).get(11));
			
			List<List<String>> detalleAjuste = mapDetalleAjuste.get(id_bodegaEmpresa);
			if(detalleAjuste == null) {
				detalleAjuste = new ArrayList<List<String>>();
			}
			List<List<String>> inicioPer = mapInicioPer.get(proyectos.get(i).get(11));
			if(inicioPer==null) {
				inicioPer = new ArrayList<List<String>>();
			}
			List<List<String>> guiasPer = mapGuiasPer.get(proyectos.get(i).get(11));
			if(guiasPer==null) {
				guiasPer = new ArrayList<List<String>>();
			}
			Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
			switch(dec.get((long)1).toString()) {
			 case "0": myformatMonedaPrincipal = new DecimalFormat("#,##0",symbols); break;
			 case "2": myformatMonedaPrincipal = new DecimalFormat("#,##0.00",symbols); break;
			 case "4": myformatMonedaPrincipal = new DecimalFormat("#,##0.0000",symbols); break;
			 case "6": myformatMonedaPrincipal = new DecimalFormat("#,##0.000000",symbols); break;
			 default:  break;
			}
			
			List<String> aux1 = new ArrayList<String>();
			aux1.add("PROYECTO/BODEGA: "+proyectos.get(i).get(5).toUpperCase()+"    CLIENTE: "+proyectos.get(i).get(8));
			aux1.add("PROYECTO");
			aux1.add("");
			aux1.add("");
			aux1.add("");
			aux1.add("");
			aux1.add("");
			aux1.add("");
			aux1.add("");
			aux1.add("");
			aux1.add("");
			aux1.add("");
			listaRs.add(aux1);
			
			List<String> aux3 = new ArrayList<String>();
			aux3.add("INVENTARIO INICIAL");
			aux3.add("INVENTARIO");
			aux3.add("");
			aux3.add("");
			aux3.add("");
			aux3.add("");
			aux3.add("");
			aux3.add("");
			aux3.add("");
			aux3.add("");
			aux3.add("");
			aux3.add("");
			aux3.add("");
			aux1.add("");
			listaRs.add(aux3);
			
			Double totCfi = (double) 0, totArr = (double) 0, totVta = (double) 0;
				
			for(int k=0;k<inicioPer.size();k++){
					List<String> aux = new ArrayList<String>();
					aux.add("");
					aux.add("");
					aux.add(inicioPer.get(k).get(8));
					aux.add(inicioPer.get(k).get(9));
					aux.add(inicioPer.get(k).get(10));
					aux.add(inicioPer.get(k).get(11));
					aux.add(inicioPer.get(k).get(12));
					if(nEmpresa.equals("SANTAFE")&&inicioPer.get(k).get(18).trim().equals("0")){
						aux.add("");
					}else{
						aux.add(inicioPer.get(k).get(13));
					}
					aux.add(inicioPer.get(k).get(14));
					aux.add(inicioPer.get(k).get(15));
					aux.add(inicioPer.get(k).get(16));
					aux.add(inicioPer.get(k).get(17));
					aux.add(inicioPer.get(k).get(23));
					aux.add(inicioPer.get(k).get(18));
					aux.add(inicioPer.get(k).get(19));
					listaRs.add(aux);
					try {
						totArr += myformatdouble.parse(inicioPer.get(k).get(18)).doubleValue();
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
			}
				
			List<String> aux4 = new ArrayList<String>();
			aux4.add("DETALLE DE MOVIMIENTOS");
			aux4.add("DETALLE");
			aux4.add("");
			aux4.add("");
			aux4.add("");
			aux4.add("");
			aux4.add("");
			aux4.add("");
			aux4.add("");
			aux4.add("");
			aux4.add("");
			aux4.add("");
			aux4.add("");
			aux4.add("");
			aux4.add("");
			listaRs.add(aux4);
					
			for(int k=0;k<guiasPer.size();k++){
				
				Long id_guia = Long.parseLong(guiasPer.get(k).get(3));
				Guia guia = mapGuias.get(id_guia);
				if(guia!=null) {
					List<String> aux = new ArrayList<String>();
					aux.add(guia.numero.toString());
					aux.add(guia.fecha);
					aux.add(guiasPer.get(k).get(8));
					
					aux.add(guiasPer.get(k).get(9));
					aux.add(guiasPer.get(k).get(10));
					aux.add(guiasPer.get(k).get(11));
					aux.add(guiasPer.get(k).get(12));
					if(nEmpresa.equals("SANTAFE")&&guiasPer.get(k).get(18).trim().equals("0")){
						aux.add("");
					}else{
						aux.add(guiasPer.get(k).get(13));
					}
					aux.add(guiasPer.get(k).get(14));
					aux.add(guiasPer.get(k).get(15));
					aux.add(guiasPer.get(k).get(16));
					aux.add(guiasPer.get(k).get(17));
					aux.add(guiasPer.get(k).get(23));
					aux.add(guiasPer.get(k).get(18));
					aux.add(guiasPer.get(k).get(19));
					listaRs.add(aux);
					try {
						totArr += myformatdouble.parse(guiasPer.get(k).get(18)).doubleValue();
						totVta += myformatdouble.parse(guiasPer.get(k).get(19)).doubleValue();
						totCfi += myformatdouble.parse(guiasPer.get(k).get(23)).doubleValue();
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
				
				List<String> aux7 = new ArrayList<String>();
				aux7.add("");
				aux7.add("");
				aux7.add("");
				aux7.add("");
				aux7.add("");
				aux7.add("");
				aux7.add("");
				aux7.add("");
				aux7.add("");
				aux7.add("");
				aux7.add("");
				aux7.add("");
				aux7.add("");
				aux7.add("");
				aux7.add("");
				listaRs.add(aux7);
				listaRs.add(aux7);
				List<String> aux5 = new ArrayList<String>();
				aux5.add("SUBTOTALES");
				aux5.add("");
				aux5.add("");
				aux5.add("");
				aux5.add("");
				aux5.add("");
				aux5.add("");
				aux5.add("");
				aux5.add("");
				aux5.add("");
				aux5.add("");
				aux5.add("");
				aux5.add(myformatMonedaPrincipal.format(totCfi));
				aux5.add(myformatMonedaPrincipal.format(totArr));
				aux5.add(myformatMonedaPrincipal.format(totVta));
				listaRs.add(aux5);
				
				Double ajusteArriendo=(double)0;
				Double ajusteVenta=(double)0;
				
				for(List<String> lista: detalleAjuste ) {
					List<String> aux50 = new ArrayList<String>();
					aux50.add("AJUSTES");
					aux50.add("");
					aux50.add("");
					aux50.add("");
					aux50.add(lista.get(0));
					aux50.add("");
					aux50.add("");
					aux50.add("");
					aux50.add("");
					aux50.add("");
					aux50.add("");
					aux50.add("");
					aux50.add(myformatMonedaPrincipal.format((double)0));
					Double aux = (double)0;
					try {
						String auxNum = lista.get(1).trim();
		 	   			if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
						aux = myformatdouble.parse(auxNum).doubleValue();
						ajusteArriendo += aux;
					} catch (ParseException e) {}
					aux50.add(myformatMonedaPrincipal.format(aux));
					
					try {
						String auxNum = lista.get(2).trim();
		 	   			if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
						aux = myformatdouble.parse(auxNum).doubleValue();
						ajusteVenta += aux;
					} catch (ParseException e) {}
					aux50.add(myformatMonedaPrincipal.format(aux));
					listaRs.add(aux50);
				}
				
				List<String> aux51 = new ArrayList<String>();
				aux51.add("SUBTOTALES2");
				aux51.add("");
				aux51.add("");
				aux51.add("");
				aux51.add("");
				aux51.add("");
				aux51.add("");
				aux51.add("");
				aux51.add("");
				aux51.add("");
				aux51.add("");
				aux51.add("");
				aux51.add(myformatMonedaPrincipal.format(totCfi));
				aux51.add(myformatMonedaPrincipal.format(totArr+ajusteArriendo));
				aux51.add(myformatMonedaPrincipal.format(totVta+ajusteVenta));
				listaRs.add(aux51);
				
				List<String> aux6 = new ArrayList<String>();
				aux6.add("TOTAL");
				aux6.add("");
				aux6.add("");
				aux6.add("");
				aux6.add("");
				aux6.add("");
				aux6.add("");
				aux6.add("");
				aux6.add("");
				aux6.add("");
				aux6.add("");
				aux6.add("");
				aux6.add("");
				aux6.add("");
				aux6.add(myformatMonedaPrincipal.format((totArr+totVta+totCfi+ajusteArriendo+ajusteVenta)));
				listaRs.add(aux6);
			}
			
			
		return(listaRs);
	}
	
	public static File exportaProformaExcelResumen0(String db, Map<String,String> mapDiccionario, List<List<String>> proyectos, Fechas desde, Fechas hasta,
			Double uf, Double usd, Double eur, List<List<String>> resumenTotales, List<List<String>> resumenPorGrupoYProyecto, List<List<String>> resumenPorProyectoGrupoYdetalle) {
		
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
            encabezado.setAlignment(HorizontalAlignment.LEFT);
            
            CellStyle detalle = libro.createCellStyle();
            detalle.setBorderBottom(BorderStyle.THIN);
            detalle.setBorderTop(BorderStyle.THIN);
            detalle.setBorderRight(BorderStyle.THIN);
            detalle.setBorderLeft(BorderStyle.THIN);
            
            CellStyle pie = libro.createCellStyle();
            pie.setBorderBottom(BorderStyle.THIN);
            pie.setBorderTop(BorderStyle.THIN);
            pie.setBorderRight(BorderStyle.THIN);
            pie.setBorderLeft(BorderStyle.THIN);
            pie.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            pie.setFillForegroundColor((short)19);
            pie.setAlignment(HorizontalAlignment.RIGHT);
            
            
            
            
            //titulos del archivo
            
            libro.setSheetName(0, "EP PROFORMA RESUMEN");
            Sheet hoja1 = libro.getSheetAt(0);
            
            Row row = null;
            Cell cell = null;
            
            row = hoja1.createRow(1);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellValue("REPORTE RESUMEN Y DETALLE POR TODOS LOS PROYECTOS AGRUPADO (INCLUIDOS AJUSTES DE EP)");
			
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
            cell.setCellStyle(titulo);
			cell.setCellValue("PERIODO: desde " + desde.getFechaStrDDMMAA()  + " hasta " + hasta.getFechaStrDDMMAA());
			
			
			//anchos de columnas
			for(int i=1; i<17; i++) {
				hoja1.setColumnWidth(i, 6*1000);
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
	        anchor.setCol1(13);
	        anchor.setRow1(1);
			Picture img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);
			
			
			// encabezado de la tabla
			
			int posRow = 8;
			
			row = hoja1.createRow(posRow);
			int posCell = 0;
			
			posCell++;
            cell = row.createCell(posCell);
            cell.setCellStyle(titulo);
			cell.setCellValue("RESUMEN POR PROYECTOS  (INCLUYE AJUSTES A EP):");
			
			posRow += 2;
			posCell = 0;
			
			row = hoja1.createRow(posRow);
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("SUCURSAL");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("NOMBRE "+mapDiccionario.get("BODEGA")+"/PROYECTO");
		
			if(mapDiccionario.get("nEmpresa").equals("CIHLA")) {
				posCell++;
				cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("Nro CARGO");
			}else{
				posCell++;
				cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue(mapDiccionario.get("RUT")+" CLIENTE");
				
				posCell++;
				cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("NOMBRE CLIENTE");
			}
		
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("NOMBRE PROYECTO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("CFI (en "+mapDiccionario.get("Pesos")+")");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("SubTotal "+mapDiccionario.get("ARRIENDO"));
		        
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("SubTotal VENTA");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("Ajustes "+mapDiccionario.get("ARRIENDO"));

			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("Ajustes VENTA");
		        
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("TOTAL (en "+mapDiccionario.get("Pesos")+")");
				
		       
	        String auxNumero="";
	        Double venta=(double)0;
	        Double arriendo=(double)0;
	        Double cfi=(double)0;
	        Double totaltotal=(double)0;
	        Double ajusteArriendo=(double)0;
	        Double ajusteVenta=(double)0;
	        
	        
	        
	        
	        
			for(int i=0;i<proyectos.size();i++){
				for(int j=0;j<resumenTotales.size();j++){
					if(proyectos.get(i).get(1).equals(resumenTotales.get(j).get(0))){
							
						posRow++;
						posCell = 0;
				        row = hoja1.createRow(posRow);
						
				        posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellValue(proyectos.get(i).get(14));
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellValue(proyectos.get(i).get(5));
							
						if(mapDiccionario.get("nEmpresa").equals("CIHLA")) {
							posCell++;
							cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellValue("");
						}else{
							posCell++;
							cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellValue(proyectos.get(i).get(6));
							
							posCell++;
							cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellValue(proyectos.get(i).get(7));
						}
				        
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellValue(proyectos.get(i).get(8));
				        
						posCell++; 
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
			            Double aux = Double.parseDouble(resumenTotales.get(j).get(3).replaceAll(",", ""));
								cell.setCellValue(aux);
						cfi += aux;
						
						posCell++; 
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
			            aux = Double.parseDouble(resumenTotales.get(j).get(1).replaceAll(",", ""));
								cell.setCellValue(aux);
						arriendo += aux;
						
						posCell++; 
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
			            aux = Double.parseDouble(resumenTotales.get(j).get(2).replaceAll(",", ""));
								cell.setCellValue(aux);
						venta += aux;
						
						posCell++; 
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
			            aux = Double.parseDouble(resumenTotales.get(j).get(5).replaceAll(",", ""));
								cell.setCellValue(aux);
						ajusteArriendo += aux;
						
						posCell++; 
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
			            aux = Double.parseDouble(resumenTotales.get(j).get(6).replaceAll(",", ""));
								cell.setCellValue(aux);
						ajusteVenta += aux;
						
						posCell++; 
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
			            aux = Double.parseDouble(resumenTotales.get(j).get(4).replaceAll(",", ""));
								cell.setCellValue(aux);
						totaltotal += aux;
					}
				}
			}
			
			posRow++;
			posCell = 0;
	        row = hoja1.createRow(posRow);
			
			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("TOTAL");
			
			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("");
			
			if(mapDiccionario.get("nEmpresa").equals("CIHLA")) {
				posCell++;
				cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("");
			}else{
				posCell++;
				cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("");
				
				posCell++;
				cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("");
			}
			
			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(pie);
            Double aux = cfi;
			cell.setCellValue(aux);
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(pie);
            aux = arriendo;
			cell.setCellValue(aux);
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(pie);
            aux = venta;
			cell.setCellValue(aux);
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(pie);
            aux = ajusteArriendo;
			cell.setCellValue(aux);
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(pie);
            aux = ajusteVenta;
			cell.setCellValue(aux);
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(pie);
            aux = totaltotal;
			cell.setCellValue(aux);
			
			
			//datos de RESUMEN POR FAMILIAS Y PROYECTOS:
			
			posRow += 4;
			posCell = 0;
	        row = hoja1.createRow(posRow);
	        
	        posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(titulo);
			cell.setCellValue("RESUMEN POR FAMILIAS Y PROYECTOS (SIN AJUSTES A EP):");
	        
			posRow += 2;
			posCell = 0;
	        row = hoja1.createRow(posRow);
	        
	        posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("FAMILIA/GRUPO");
			
			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("NOMBRE "+mapDiccionario.get("BODEGA")+"/PROYECTO");
	        
			if(!mapDiccionario.get("nEmpresa").equals("CIHLA")) {
				posCell++;
				cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue(mapDiccionario.get("RUT")+" CLIENTE");
				
				posCell++;
				cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("NOMBRE CLIENTE");
			}
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("NOMBRE PROYECTO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("CFI (en "+mapDiccionario.get("Pesos")+")");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue(mapDiccionario.get("ARRIENDO")+" (en "+mapDiccionario.get("Pesos")+")");
		        
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("VENTA (en "+mapDiccionario.get("Pesos")+")");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("TOTAL (en "+mapDiccionario.get("Pesos")+")");
			
			
			venta = (double)0;
	        arriendo = (double)0;
	        cfi = (double)0;
	        totaltotal = (double)0;
			
	        for(int i=0;i<resumenPorGrupoYProyecto.size()-2;i++){
	        	
	        	posRow++;
				posCell = 0;
		        row = hoja1.createRow(posRow);
		        
		        if(resumenPorGrupoYProyecto.get(i).get(3).equals("SUBTOTAL")||resumenPorGrupoYProyecto.get(i).get(0).equals("TOTAL")){
		        	posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellValue(resumenPorGrupoYProyecto.get(i).get(0));
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellValue(resumenPorGrupoYProyecto.get(i).get(1));
					
					if(!mapDiccionario.get("nEmpresa").equals("CIHLA")) {
						posCell++;
						cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellValue(resumenPorGrupoYProyecto.get(i).get(2));
						
						posCell++;
						cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellValue(resumenPorGrupoYProyecto.get(i).get(3));
					}
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellValue(resumenPorGrupoYProyecto.get(i).get(8));
					
					posCell++; 
		            cell = row.createCell(posCell);
		            cell.setCellStyle(detalle);
		            aux = Double.parseDouble(resumenPorGrupoYProyecto.get(i).get(4).replaceAll(",", ""));
					cell.setCellValue(aux);
					cfi += aux;
					
					posCell++; 
		            cell = row.createCell(posCell);
		            cell.setCellStyle(detalle);
		            aux = Double.parseDouble(resumenPorGrupoYProyecto.get(i).get(5).replaceAll(",", ""));
					cell.setCellValue(aux);
					arriendo += aux;
					
					posCell++; 
		            cell = row.createCell(posCell);
		            cell.setCellStyle(detalle);
		            aux = Double.parseDouble(resumenPorGrupoYProyecto.get(i).get(6).replaceAll(",", ""));
					cell.setCellValue(aux);
					venta += aux;
					
					posCell++; 
		            cell = row.createCell(posCell);
		            cell.setCellStyle(detalle);
		            aux = Double.parseDouble(resumenPorGrupoYProyecto.get(i).get(7).replaceAll(",", ""));
					cell.setCellValue(aux);
					totaltotal += aux;
					
		        }else {
		        	posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellValue(resumenPorGrupoYProyecto.get(i).get(0));
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellValue(resumenPorGrupoYProyecto.get(i).get(1));
					
					if(!mapDiccionario.get("nEmpresa").equals("CIHLA")) {
						posCell++;
						cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellValue(resumenPorGrupoYProyecto.get(i).get(2));
						
						posCell++;
						cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellValue(resumenPorGrupoYProyecto.get(i).get(3));
					}
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellValue(resumenPorGrupoYProyecto.get(i).get(8));
					
					posCell++; 
		            cell = row.createCell(posCell);
		            cell.setCellStyle(detalle);
		            if(resumenPorGrupoYProyecto.get(i).get(4).equals("")) {
		            	aux = (double)0;
		            }else {
		            	aux = Double.parseDouble(resumenPorGrupoYProyecto.get(i).get(4).replaceAll(",", ""));
		            }
					cell.setCellValue(aux);
					cfi += aux;
					
					posCell++; 
		            cell = row.createCell(posCell);
		            cell.setCellStyle(detalle);
		            if(resumenPorGrupoYProyecto.get(i).get(5).equals("")) {
		            	aux = (double)0;
		            }else {
		            	aux = Double.parseDouble(resumenPorGrupoYProyecto.get(i).get(5).replaceAll(",", ""));
		            }
					cell.setCellValue(aux);
					arriendo += aux;
					
					posCell++; 
		            cell = row.createCell(posCell);
		            cell.setCellStyle(detalle);
		            if(resumenPorGrupoYProyecto.get(i).get(6).equals("")) {
		            	aux = (double)0;
		            }else {
		            	aux = Double.parseDouble(resumenPorGrupoYProyecto.get(i).get(6).replaceAll(",", ""));
		            }
					cell.setCellValue(aux);
					venta += aux;
					
					posCell++; 
		            cell = row.createCell(posCell);
		            cell.setCellStyle(detalle);
		            if(resumenPorGrupoYProyecto.get(i).get(7).equals("")) {
		            	aux = (double)0;
		            }else {
		            	aux = Double.parseDouble(resumenPorGrupoYProyecto.get(i).get(7).replaceAll(",", ""));
		            }
					cell.setCellValue(aux);
					totaltotal += aux;
					
		        }
	        }
	        
	        posRow++;
			posCell = 0;
	        row = hoja1.createRow(posRow);
	        
	        posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("TOTAL");
			
			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("");
	        
			if(!mapDiccionario.get("nEmpresa").equals("CIHLA")) {
				posCell++;
				cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("");
				
				posCell++;
				cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("");
			}
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(pie);
            aux = cfi/2;
			cell.setCellValue(aux);
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(pie);
            aux = arriendo/2;
			cell.setCellValue(aux);
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(pie);
            aux = venta/2;
			cell.setCellValue(aux);
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(pie);
            aux = totaltotal/2;
			cell.setCellValue(aux);
			
			
			// llena RESUMEN POR PROYECTO Y FAMILIA
			
			posRow += 4;
			posCell = 0;
	        row = hoja1.createRow(posRow);
	        
	        posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(titulo);
			cell.setCellValue("DETALLE POR PROYECTO (INCLUYE AJUSTES A EP):");
			
			posRow++;
			
			for(int i=0;i<resumenPorProyectoGrupoYdetalle.size();i++){
				
				posRow++;
				posCell = 0;
		        row = hoja1.createRow(posRow);
		        
		        if(resumenPorProyectoGrupoYdetalle.get(i).get(1).equals("PROYECTO")){
		        	
		        	posRow++;
					posCell = 0;
			        row = hoja1.createRow(posRow);
			        
		        	posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(encabezado);
					cell.setCellValue(resumenPorProyectoGrupoYdetalle.get(i).get(0));
			        
					for(int j=0;j<14;j++){
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(encabezado);
					}
		        }else {
		        	if(resumenPorProyectoGrupoYdetalle.get(i).get(1).equals("GRUPO")){
		        		posRow++;
						posCell = 0;
				        row = hoja1.createRow(posRow);
				        
			        	posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(subtitulo);
						cell.setCellValue(resumenPorProyectoGrupoYdetalle.get(i).get(0));
						
		        	}else {
		        		if(resumenPorProyectoGrupoYdetalle.get(i).get(1).equals("INVENTARIO")){
		        			posRow++;
							posCell = 0;
					        row = hoja1.createRow(posRow);
					        
				        	posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(subtitulo);
							cell.setCellValue(resumenPorProyectoGrupoYdetalle.get(i).get(0));
		        			
							posRow++;
							posCell = 0;
					        row = hoja1.createRow(posRow);
					        
					        posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(subtitulo);
							cell.setCellValue("");
							
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(subtitulo);
							cell.setCellValue("");
							
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(subtitulo);
							cell.setCellValue("Nro.Coti");
							
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(subtitulo);
							cell.setCellValue("CODIGO");
							
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(subtitulo);
							cell.setCellValue("EQUIPO");
							
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(subtitulo);
							cell.setCellValue("UN");
							
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(subtitulo);
							cell.setCellValue("CANTIDAD");
							
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(subtitulo);
							cell.setCellValue("MONEDA");
							
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(subtitulo);
							cell.setCellValue("P.Venta");
							
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(subtitulo);
							cell.setCellValue(mapDiccionario.get("ARR")+" x DIA");
							
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(subtitulo);
							cell.setCellValue("DIAS");
							
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(subtitulo);
							cell.setCellValue("TASA CAMBIO");
							
							posCell++;
							cell = row.createCell(posCell);
				            cell.setCellStyle(subtitulo);
							cell.setCellValue("CFI (en "+mapDiccionario.get("Pesos")+")");
							
							posCell++;
							cell = row.createCell(posCell);
				            cell.setCellStyle(subtitulo);
							cell.setCellValue(mapDiccionario.get("ARRIENDO")+" (en "+mapDiccionario.get("Pesos")+")");
						        
							posCell++;
							cell = row.createCell(posCell);
				            cell.setCellStyle(subtitulo);
							cell.setCellValue("VENTA (en "+mapDiccionario.get("Pesos")+")");
		        			
		        		}else {
		        			if(resumenPorProyectoGrupoYdetalle.get(i).get(1).equals("DETALLE")){
			        			posRow++;
								posCell = 0;
						        row = hoja1.createRow(posRow);
						        
					        	posCell++;
								cell = row.createCell(posCell);
								cell.setCellStyle(subtitulo);
								cell.setCellValue(resumenPorProyectoGrupoYdetalle.get(i).get(0));
			        			
								posRow++;
								posCell = 0;
						        row = hoja1.createRow(posRow);
						        
						        posCell++;
								cell = row.createCell(posCell);
								cell.setCellStyle(subtitulo);
								cell.setCellValue("");
								
								posCell++;
								cell = row.createCell(posCell);
								cell.setCellStyle(subtitulo);
								cell.setCellValue("");
								
								posCell++;
								cell = row.createCell(posCell);
								cell.setCellStyle(subtitulo);
								cell.setCellValue("Nro.Coti");
								
								posCell++;
								cell = row.createCell(posCell);
								cell.setCellStyle(subtitulo);
								cell.setCellValue("CODIGO");
								
								posCell++;
								cell = row.createCell(posCell);
								cell.setCellStyle(subtitulo);
								cell.setCellValue("EQUIPO");
								
								posCell++;
								cell = row.createCell(posCell);
								cell.setCellStyle(subtitulo);
								cell.setCellValue("UN");
								
								posCell++;
								cell = row.createCell(posCell);
								cell.setCellStyle(subtitulo);
								cell.setCellValue("CANTIDAD");
								
								posCell++;
								cell = row.createCell(posCell);
								cell.setCellStyle(subtitulo);
								cell.setCellValue("MONEDA");
								
								posCell++;
								cell = row.createCell(posCell);
								cell.setCellStyle(subtitulo);
								cell.setCellValue("P.Venta");
								
								posCell++;
								cell = row.createCell(posCell);
								cell.setCellStyle(subtitulo);
								cell.setCellValue(mapDiccionario.get("ARR")+" x DIA");
								
								posCell++;
								cell = row.createCell(posCell);
								cell.setCellStyle(subtitulo);
								cell.setCellValue("DIAS");
								
								posCell++;
								cell = row.createCell(posCell);
								cell.setCellStyle(subtitulo);
								cell.setCellValue("TASA CAMBIO");
								
								posCell++;
								cell = row.createCell(posCell);
					            cell.setCellStyle(subtitulo);
								cell.setCellValue("CFI (en "+mapDiccionario.get("Pesos")+")");
								
								posCell++;
								cell = row.createCell(posCell);
					            cell.setCellStyle(subtitulo);
								cell.setCellValue(mapDiccionario.get("ARRIENDO")+" (en "+mapDiccionario.get("Pesos")+")");
							        
								posCell++;
								cell = row.createCell(posCell);
					            cell.setCellStyle(subtitulo);
								cell.setCellValue("VENTA (en "+mapDiccionario.get("Pesos")+")");
			        			
			        		}else {
			        			if(resumenPorProyectoGrupoYdetalle.get(i).get(0).equals("TOTAL")){
			        				posRow++;
									posCell = 0;
							        row = hoja1.createRow(posRow);
							        
						        	posCell++;
									cell = row.createCell(posCell);
									cell.setCellStyle(subtitulo);
									cell.setCellValue(resumenPorProyectoGrupoYdetalle.get(i).get(0));
				        			
									posCell++;
									cell = row.createCell(posCell);
									cell.setCellStyle(subtitulo);
									cell.setCellValue(resumenPorProyectoGrupoYdetalle.get(i).get(1));
				        			
									posCell++; 
						            cell = row.createCell(posCell+13);
						            cell.setCellStyle(detalle);
						            aux = Double.parseDouble(resumenPorProyectoGrupoYdetalle.get(i).get(14).replaceAll(",", ""));
														cell.setCellValue(aux);
			        			}else {
			        				
			        				posCell = 0;
							        
							        posCell++;
									cell = row.createCell(posCell);
									cell.setCellValue(resumenPorProyectoGrupoYdetalle.get(i).get(0));
									
									posCell++;
									cell = row.createCell(posCell);
									cell.setCellValue(resumenPorProyectoGrupoYdetalle.get(i).get(1));
									
									posCell++;
									cell = row.createCell(posCell);
									cell.setCellValue(resumenPorProyectoGrupoYdetalle.get(i).get(2));
									
									posCell++;
									cell = row.createCell(posCell);
									cell.setCellValue(resumenPorProyectoGrupoYdetalle.get(i).get(3));
									
									posCell++;
									cell = row.createCell(posCell);
									cell.setCellValue(resumenPorProyectoGrupoYdetalle.get(i).get(4));
									
									posCell++;
									cell = row.createCell(posCell);
									cell.setCellValue(resumenPorProyectoGrupoYdetalle.get(i).get(5));
									
									posCell++; 
						            cell = row.createCell(posCell);
						            if(resumenPorProyectoGrupoYdetalle.get(i).get(6).equals("")) {
						            	aux = (double)0;
						            }else {
						            	aux = Double.parseDouble(resumenPorProyectoGrupoYdetalle.get(i).get(6).replaceAll(",", ""));
						            }
														cell.setCellValue(aux);
									
									posCell++;
									cell = row.createCell(posCell);
									cell.setCellStyle(subtitulo);
									cell.setCellValue(resumenPorProyectoGrupoYdetalle.get(i).get(7));
									
									posCell++; 
						            cell = row.createCell(posCell);
						            if(resumenPorProyectoGrupoYdetalle.get(i).get(8).equals("")) {
						            	aux = (double)0;
						            }else {
						            	aux = Double.parseDouble(resumenPorProyectoGrupoYdetalle.get(i).get(8).replaceAll(",", ""));
						            }
														cell.setCellValue(aux);
									
									posCell++; 
						            cell = row.createCell(posCell);
						            if(resumenPorProyectoGrupoYdetalle.get(i).get(9).equals("")) {
						            	aux = (double)0;
						            }else {
						            	aux = Double.parseDouble(resumenPorProyectoGrupoYdetalle.get(i).get(9).replaceAll(",", ""));
						            }
														cell.setCellValue(aux);
									
									posCell++; 
						            cell = row.createCell(posCell);
						            if(resumenPorProyectoGrupoYdetalle.get(i).get(10).equals("")) {
						            	aux = (double)0;
						            }else {
						            	aux = Double.parseDouble(resumenPorProyectoGrupoYdetalle.get(i).get(10).replaceAll(",", ""));
						            }
														cell.setCellValue(aux);
									
									if(auxNumero.equals("TOTAL")){
										posCell++;
										cell = row.createCell(posCell);
										cell.setCellStyle(subtitulo);
										cell.setCellValue(resumenPorProyectoGrupoYdetalle.get(i).get(11));
									}else {
										posCell++; 
							            cell = row.createCell(posCell);
							            if(resumenPorProyectoGrupoYdetalle.get(i).get(11).equals("")) {
							            	aux = (double)0;
							            }else {
							            	aux = Double.parseDouble(resumenPorProyectoGrupoYdetalle.get(i).get(11).replaceAll(",", ""));
							            }
																cell.setCellValue(aux);
									}
									
									posCell++; 
						            cell = row.createCell(posCell);
						            if(resumenPorProyectoGrupoYdetalle.get(i).get(12).equals("")) {
						            	aux = (double)0;
						            }else {
						            	aux = Double.parseDouble(resumenPorProyectoGrupoYdetalle.get(i).get(12).replaceAll(",", ""));
						            }
														cell.setCellValue(aux);
									
									posCell++; 
						            cell = row.createCell(posCell);
						            if(resumenPorProyectoGrupoYdetalle.get(i).get(13).equals("")) {
						            	aux = (double)0;
						            }else {
						            	aux = Double.parseDouble(resumenPorProyectoGrupoYdetalle.get(i).get(13).replaceAll(",", ""));
						            }
														cell.setCellValue(aux);
									
									posCell++; 
						            cell = row.createCell(posCell);
						            if(resumenPorProyectoGrupoYdetalle.get(i).get(14).equals("")) {
						            	aux = (double)0;
						            }else {
						            	aux = Double.parseDouble(resumenPorProyectoGrupoYdetalle.get(i).get(14).replaceAll(",", ""));
						            }
														cell.setCellValue(aux);
			        			}
			        		}
		        		}
		        	}
		        }
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
	
	public static File exportaProformaExcelResumen(String db, Map<String,String> mapDiccionario, List<List<String>> proyectos, Fechas desde, Fechas hasta,
			Double uf, Double usd, Double eur, List<List<String>> resumenTotales, List<List<String>> resumenPorGrupoYProyecto, List<List<String>> resumenPorProyectoGrupoYdetalle,
			Map<String,Equipo> mapEquipo) {
		
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
            encabezado.setAlignment(HorizontalAlignment.LEFT);
            
            CellStyle detalle = libro.createCellStyle();
            detalle.setBorderBottom(BorderStyle.THIN);
            detalle.setBorderTop(BorderStyle.THIN);
            detalle.setBorderRight(BorderStyle.THIN);
            detalle.setBorderLeft(BorderStyle.THIN);
            
            CellStyle pie = libro.createCellStyle();
            pie.setBorderBottom(BorderStyle.THIN);
            pie.setBorderTop(BorderStyle.THIN);
            pie.setBorderRight(BorderStyle.THIN);
            pie.setBorderLeft(BorderStyle.THIN);
            pie.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            pie.setFillForegroundColor((short)19);
            pie.setAlignment(HorizontalAlignment.RIGHT);
            
            
            
            
            //titulos del archivo
            
            libro.setSheetName(0, "EP PROFORMA RESUMEN");
            Sheet hoja1 = libro.getSheetAt(0);
            
            Row row = null;
            Cell cell = null;
            
            row = hoja1.createRow(1);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellValue("REPORTE RESUMEN Y DETALLE POR TODOS LOS PROYECTOS POR FAMILIA (INCLUIDOS AJUSTES DE EP)");
			
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
            cell.setCellStyle(titulo);
			cell.setCellValue("PERIODO: desde " + desde.getFechaStrDDMMAA()  + " hasta " + hasta.getFechaStrDDMMAA());
			
			
			//anchos de columnas
			for(int i=1; i<18; i++) {
				hoja1.setColumnWidth(i, 6*1000);
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
	        anchor.setCol1(13);
	        anchor.setRow1(1);
			Picture img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);
			
			
			// encabezado de la tabla
			
			int posRow = 8;
			
			row = hoja1.createRow(posRow);
			int posCell = 0;
			
			posCell++;
            cell = row.createCell(posCell);
            cell.setCellStyle(titulo);
			cell.setCellValue("RESUMEN POR PROYECTOS  (INCLUYE AJUSTES A EP):");
			
			posRow += 2;
			posCell = 0;
			
			row = hoja1.createRow(posRow);
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("SUCURSAL");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("NOMBRE "+mapDiccionario.get("BODEGA")+"/PROYECTO");
		
			if(mapDiccionario.get("nEmpresa").equals("CIHLA")) {
				posCell++;
				cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("Nro CARGO");
			}else{
				posCell++;
				cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue(mapDiccionario.get("RUT")+" CLIENTE");
				
				posCell++;
				cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("NOMBRE CLIENTE");
			}
		
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("NOMBRE PROYECTO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("CFI (en "+mapDiccionario.get("Pesos")+")");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("SubTotal "+mapDiccionario.get("ARRIENDO"));
		        
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("SubTotal VENTA");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("Ajustes "+mapDiccionario.get("ARRIENDO"));

			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("Ajustes VENTA");
		        
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("TOTAL (en "+mapDiccionario.get("Pesos")+")");
				
		       
	        String auxNumero="";
	        Double venta=(double)0;
	        Double arriendo=(double)0;
	        Double cfi=(double)0;
	        Double totaltotal=(double)0;
	        Double ajusteArriendo=(double)0;
	        Double ajusteVenta=(double)0;
	        
	        
	        
	        
	        
			for(int i=0;i<proyectos.size();i++){
				for(int j=0;j<resumenTotales.size();j++){
					if(proyectos.get(i).get(1).equals(resumenTotales.get(j).get(0))){
							
						posRow++;
						posCell = 0;
				        row = hoja1.createRow(posRow);
						
				        posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellValue(proyectos.get(i).get(14));
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellValue(proyectos.get(i).get(5));
							
						if(mapDiccionario.get("nEmpresa").equals("CIHLA")) {
							posCell++;
							cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellValue("");
						}else{
							posCell++;
							cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellValue(proyectos.get(i).get(6));
							
							posCell++;
							cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellValue(proyectos.get(i).get(7));
						}
				        
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellValue(proyectos.get(i).get(8));
				        
						posCell++; 
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
			            Double aux = Double.parseDouble(resumenTotales.get(j).get(3).replaceAll(",", ""));
								cell.setCellValue(aux);
						cfi += aux;
						
						posCell++; 
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
			            aux = Double.parseDouble(resumenTotales.get(j).get(1).replaceAll(",", ""));
								cell.setCellValue(aux);
						arriendo += aux;
						
						posCell++; 
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
			            aux = Double.parseDouble(resumenTotales.get(j).get(2).replaceAll(",", ""));
								cell.setCellValue(aux);
						venta += aux;
						
						posCell++; 
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
			            aux = Double.parseDouble(resumenTotales.get(j).get(5).replaceAll(",", ""));
								cell.setCellValue(aux);
						ajusteArriendo += aux;
						
						posCell++; 
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
			            aux = Double.parseDouble(resumenTotales.get(j).get(6).replaceAll(",", ""));
								cell.setCellValue(aux);
						ajusteVenta += aux;
						
						posCell++; 
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
			            aux = Double.parseDouble(resumenTotales.get(j).get(4).replaceAll(",", ""));
								cell.setCellValue(aux);
						totaltotal += aux;
					}
				}
			}
			
			posRow++;
			posCell = 0;
	        row = hoja1.createRow(posRow);
			
			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("TOTAL");
			
			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("");
			
			if(mapDiccionario.get("nEmpresa").equals("CIHLA")) {
				posCell++;
				cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("");
			}else{
				posCell++;
				cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("");
				
				posCell++;
				cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("");
			}
			
			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(pie);
            Double aux = cfi;
			cell.setCellValue(aux);
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(pie);
            aux = arriendo;
			cell.setCellValue(aux);
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(pie);
            aux = venta;
			cell.setCellValue(aux);
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(pie);
            aux = ajusteArriendo;
			cell.setCellValue(aux);
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(pie);
            aux = ajusteVenta;
			cell.setCellValue(aux);
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(pie);
            aux = totaltotal;
			cell.setCellValue(aux);
			
			
			//datos de RESUMEN POR FAMILIAS Y PROYECTOS:
			
			posRow += 4;
			posCell = 0;
	        row = hoja1.createRow(posRow);
	        
	        posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(titulo);
			cell.setCellValue("RESUMEN POR FAMILIAS Y PROYECTOS (SIN AJUSTES A EP):");
	        
			posRow += 2;
			posCell = 0;
	        row = hoja1.createRow(posRow);
	        
	        posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("FAMILIA/GRUPO");
			
			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("SUCURSAL");
			
			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("NOMBRE "+mapDiccionario.get("BODEGA")+"/PROYECTO");
	        
			if(!mapDiccionario.get("nEmpresa").equals("CIHLA")) {
				posCell++;
				cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue(mapDiccionario.get("RUT")+" CLIENTE");
				
				posCell++;
				cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("NOMBRE CLIENTE");
			}
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("NOMBRE PROYECTO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("CFI (en "+mapDiccionario.get("Pesos")+")");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue(mapDiccionario.get("ARRIENDO")+" (en "+mapDiccionario.get("Pesos")+")");
		        
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("VENTA (en "+mapDiccionario.get("Pesos")+")");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("TOTAL (en "+mapDiccionario.get("Pesos")+")");
			
			
			venta = (double)0;
	        arriendo = (double)0;
	        cfi = (double)0;
	        totaltotal = (double)0;
			
	        String auxGrupo = "0";
	        for(int i=0;i<resumenPorGrupoYProyecto.size()-3;i++){
	        	
	        	
		        
		        if(!resumenPorGrupoYProyecto.get(i).get(0).equals("")) {
		        	auxGrupo = resumenPorGrupoYProyecto.get(i).get(0);
		        }else {
		        	if( ! (resumenPorGrupoYProyecto.get(i).get(3).equals("SUBTOTAL")) && ! (resumenPorGrupoYProyecto.get(i).get(3).equals("TOTAL")) ){
		        		
		        		posRow++;
						posCell = 0;
				        row = hoja1.createRow(posRow);
				        
			        	posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellValue(auxGrupo);
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellValue(resumenPorGrupoYProyecto.get(i).get(9));
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellValue(resumenPorGrupoYProyecto.get(i).get(1));
						
						if(!mapDiccionario.get("nEmpresa").equals("CIHLA")) {
							posCell++;
							cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellValue(resumenPorGrupoYProyecto.get(i).get(2));
							
							posCell++;
							cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellValue(resumenPorGrupoYProyecto.get(i).get(3));
						}
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellValue(resumenPorGrupoYProyecto.get(i).get(8));
						
						posCell++; 
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
			            if(resumenPorGrupoYProyecto.get(i).get(4).equals("")) {
			            	aux = (double)0;
			            }else {
			            	aux = Double.parseDouble(resumenPorGrupoYProyecto.get(i).get(4).replaceAll(",", ""));
			            }
								cell.setCellValue(aux);
						cfi += aux;
						
						posCell++; 
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
			            if(resumenPorGrupoYProyecto.get(i).get(5).equals("")) {
			            	aux = (double)0;
			            }else {
			            	aux = Double.parseDouble(resumenPorGrupoYProyecto.get(i).get(5).replaceAll(",", ""));
			            }
								cell.setCellValue(aux);
						arriendo += aux;
						
						posCell++; 
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
			            if(resumenPorGrupoYProyecto.get(i).get(6).equals("")) {
			            	aux = (double)0;
			            }else {
			            	aux = Double.parseDouble(resumenPorGrupoYProyecto.get(i).get(6).replaceAll(",", ""));
			            }
								cell.setCellValue(aux);
						venta += aux;
						
						posCell++; 
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
			            if(resumenPorGrupoYProyecto.get(i).get(7).equals("")) {
			            	aux = (double)0;
			            }else {
			            	aux = Double.parseDouble(resumenPorGrupoYProyecto.get(i).get(7).replaceAll(",", ""));
			            }
								cell.setCellValue(aux);
						totaltotal += aux;
						
			        }
		        }
		        
		        
		        
	        }
	        
			// llena RESUMEN POR PROYECTO Y FAMILIA
			
			posRow += 4;
			posCell = 0;
	        row = hoja1.createRow(posRow);
	        
	        posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(titulo);
			cell.setCellValue("DETALLE POR PROYECTO (INCLUYE AJUSTES A EP):");
			
			posRow++;
			
			for(int i=0;i<resumenPorProyectoGrupoYdetalle.size();i++){
				
				posRow++;
				posCell = 0;
		        row = hoja1.createRow(posRow);
		        
		        if(resumenPorProyectoGrupoYdetalle.get(i).get(1).equals("PROYECTO")){
		        	
		        	posRow++;
					posCell = 0;
			        row = hoja1.createRow(posRow);
			        
		        	posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(encabezado);
					cell.setCellValue(resumenPorProyectoGrupoYdetalle.get(i).get(0));
			        
					for(int j=0;j<15;j++){
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(encabezado);
					}
		        }else {
		        	if(resumenPorProyectoGrupoYdetalle.get(i).get(1).equals("GRUPO")){
		        		posRow++;
						posCell = 0;
				        row = hoja1.createRow(posRow);
				        
			        	posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(subtitulo);
						cell.setCellValue(resumenPorProyectoGrupoYdetalle.get(i).get(0));
						
		        	}else {
		        		if(resumenPorProyectoGrupoYdetalle.get(i).get(1).equals("INVENTARIO")){
		        			posRow++;
							posCell = 0;
					        row = hoja1.createRow(posRow);
					        
				        	posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(subtitulo);
							cell.setCellValue(resumenPorProyectoGrupoYdetalle.get(i).get(0));
		        			
							posRow++;
							posCell = 0;
					        row = hoja1.createRow(posRow);
					        
					        posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(subtitulo);
							cell.setCellValue("");
							
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(subtitulo);
							cell.setCellValue("");
							
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(subtitulo);
							cell.setCellValue("Nro.Coti");
							
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(subtitulo);
							cell.setCellValue("GRUPO");
							
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(subtitulo);
							cell.setCellValue("CODIGO");
							
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(subtitulo);
							cell.setCellValue("EQUIPO");
							
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(subtitulo);
							cell.setCellValue("UN");
							
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(subtitulo);
							cell.setCellValue("CANTIDAD");
							
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(subtitulo);
							cell.setCellValue("MONEDA");
							
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(subtitulo);
							cell.setCellValue("P.Venta");
							
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(subtitulo);
							cell.setCellValue(mapDiccionario.get("ARR")+" x DIA");
							
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(subtitulo);
							cell.setCellValue("DIAS");
							
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(subtitulo);
							cell.setCellValue("TASA CAMBIO");
							
							posCell++;
							cell = row.createCell(posCell);
				            cell.setCellStyle(subtitulo);
							cell.setCellValue("CFI (en "+mapDiccionario.get("Pesos")+")");
							
							posCell++;
							cell = row.createCell(posCell);
				            cell.setCellStyle(subtitulo);
							cell.setCellValue(mapDiccionario.get("ARRIENDO")+" (en "+mapDiccionario.get("Pesos")+")");
						        
							posCell++;
							cell = row.createCell(posCell);
				            cell.setCellStyle(subtitulo);
							cell.setCellValue("VENTA (en "+mapDiccionario.get("Pesos")+")");
		        			
		        		}else {
		        			if(resumenPorProyectoGrupoYdetalle.get(i).get(1).equals("DETALLE")){
			        			posRow++;
								posCell = 0;
						        row = hoja1.createRow(posRow);
						        
					        	posCell++;
								cell = row.createCell(posCell);
								cell.setCellStyle(subtitulo);
								cell.setCellValue(resumenPorProyectoGrupoYdetalle.get(i).get(0));
			        			
								posRow++;
								posCell = 0;
						        row = hoja1.createRow(posRow);
						        
						        posCell++;
								cell = row.createCell(posCell);
								cell.setCellStyle(subtitulo);
								cell.setCellValue("");
								
								posCell++;
								cell = row.createCell(posCell);
								cell.setCellStyle(subtitulo);
								cell.setCellValue("");
								
								posCell++;
								cell = row.createCell(posCell);
								cell.setCellStyle(subtitulo);
								cell.setCellValue("Nro.Coti");
								
								posCell++;
								cell = row.createCell(posCell);
								cell.setCellStyle(subtitulo);
								cell.setCellValue("GRUPO");
								
								posCell++;
								cell = row.createCell(posCell);
								cell.setCellStyle(subtitulo);
								cell.setCellValue("CODIGO");
								
								posCell++;
								cell = row.createCell(posCell);
								cell.setCellStyle(subtitulo);
								cell.setCellValue("EQUIPO");
								
								posCell++;
								cell = row.createCell(posCell);
								cell.setCellStyle(subtitulo);
								cell.setCellValue("UN");
								
								posCell++;
								cell = row.createCell(posCell);
								cell.setCellStyle(subtitulo);
								cell.setCellValue("CANTIDAD");
								
								posCell++;
								cell = row.createCell(posCell);
								cell.setCellStyle(subtitulo);
								cell.setCellValue("MONEDA");
								
								posCell++;
								cell = row.createCell(posCell);
								cell.setCellStyle(subtitulo);
								cell.setCellValue("P.Venta");
								
								posCell++;
								cell = row.createCell(posCell);
								cell.setCellStyle(subtitulo);
								cell.setCellValue(mapDiccionario.get("ARR")+" x DIA");
								
								posCell++;
								cell = row.createCell(posCell);
								cell.setCellStyle(subtitulo);
								cell.setCellValue("DIAS");
								
								posCell++;
								cell = row.createCell(posCell);
								cell.setCellStyle(subtitulo);
								cell.setCellValue("TASA CAMBIO");
								
								posCell++;
								cell = row.createCell(posCell);
					            cell.setCellStyle(subtitulo);
								cell.setCellValue("CFI (en "+mapDiccionario.get("Pesos")+")");
								
								posCell++;
								cell = row.createCell(posCell);
					            cell.setCellStyle(subtitulo);
								cell.setCellValue(mapDiccionario.get("ARRIENDO")+" (en "+mapDiccionario.get("Pesos")+")");
							        
								posCell++;
								cell = row.createCell(posCell);
					            cell.setCellStyle(subtitulo);
								cell.setCellValue("VENTA (en "+mapDiccionario.get("Pesos")+")");
			        			
			        		}else {
			        			if(resumenPorProyectoGrupoYdetalle.get(i).get(0).equals("TOTAL")){
			        				posRow++;
									posCell = 0;
							        row = hoja1.createRow(posRow);
							        
						        	posCell++;
									cell = row.createCell(posCell);
									cell.setCellStyle(subtitulo);
									cell.setCellValue(resumenPorProyectoGrupoYdetalle.get(i).get(0));
				        			
									posCell++;
									cell = row.createCell(posCell);
									cell.setCellStyle(subtitulo);
									cell.setCellValue(resumenPorProyectoGrupoYdetalle.get(i).get(1));
				        			
									posCell++; 
						            cell = row.createCell(posCell+14);
						            cell.setCellStyle(detalle);
						            aux = Double.parseDouble(resumenPorProyectoGrupoYdetalle.get(i).get(14).replaceAll(",", ""));
														cell.setCellValue(aux);
			        			}else {
			        				
			        				posCell = 0;
							        
							        posCell++;
									cell = row.createCell(posCell);
									cell.setCellValue(resumenPorProyectoGrupoYdetalle.get(i).get(0));
									
									posCell++;
									cell = row.createCell(posCell);
									cell.setCellValue(resumenPorProyectoGrupoYdetalle.get(i).get(1));
									
									posCell++;
									cell = row.createCell(posCell);
									cell.setCellValue(resumenPorProyectoGrupoYdetalle.get(i).get(2));
									
									String nameGrupo = "";
									String codEquipo = resumenPorProyectoGrupoYdetalle.get(i).get(3);
									if(codEquipo!=null) {
										codEquipo = codEquipo.trim().toUpperCase();
									}
									Equipo equipo = mapEquipo.get(codEquipo);
									if(equipo != null) {
										nameGrupo = equipo.getGrupo();
									}
									posCell++;
									cell = row.createCell(posCell);
									cell.setCellValue(nameGrupo);
									
									posCell++;
									cell = row.createCell(posCell);
									cell.setCellValue(resumenPorProyectoGrupoYdetalle.get(i).get(3));
									
									posCell++;
									cell = row.createCell(posCell);
									cell.setCellValue(resumenPorProyectoGrupoYdetalle.get(i).get(4));
									
									posCell++;
									cell = row.createCell(posCell);
									cell.setCellValue(resumenPorProyectoGrupoYdetalle.get(i).get(5));
									
									posCell++; 
						            cell = row.createCell(posCell);
						            if(resumenPorProyectoGrupoYdetalle.get(i).get(6).equals("")) {
						            	aux = (double)0;
						            }else {
						            	aux = Double.parseDouble(resumenPorProyectoGrupoYdetalle.get(i).get(6).replaceAll(",", ""));
						            }
														cell.setCellValue(aux);
									
									posCell++;
									cell = row.createCell(posCell);
									cell.setCellStyle(subtitulo);
									cell.setCellValue(resumenPorProyectoGrupoYdetalle.get(i).get(7));
									
									posCell++; 
						            cell = row.createCell(posCell);
						            if(resumenPorProyectoGrupoYdetalle.get(i).get(8).equals("")) {
						            	aux = (double)0;
						            }else {
						            	aux = Double.parseDouble(resumenPorProyectoGrupoYdetalle.get(i).get(8).replaceAll(",", ""));
						            }
														cell.setCellValue(aux);
									
									posCell++; 
						            cell = row.createCell(posCell);
						            if(resumenPorProyectoGrupoYdetalle.get(i).get(9).equals("")) {
						            	aux = (double)0;
						            }else {
						            	aux = Double.parseDouble(resumenPorProyectoGrupoYdetalle.get(i).get(9).replaceAll(",", ""));
						            }
														cell.setCellValue(aux);
									
									posCell++; 
						            cell = row.createCell(posCell);
						            if(resumenPorProyectoGrupoYdetalle.get(i).get(10).equals("")) {
						            	aux = (double)0;
						            }else {
						            	aux = Double.parseDouble(resumenPorProyectoGrupoYdetalle.get(i).get(10).replaceAll(",", ""));
						            }
														cell.setCellValue(aux);
									
									if(auxNumero.equals("TOTAL")){
										posCell++;
										cell = row.createCell(posCell);
										cell.setCellStyle(subtitulo);
										cell.setCellValue(resumenPorProyectoGrupoYdetalle.get(i).get(11));
									}else {
										posCell++; 
							            cell = row.createCell(posCell);
							            if(resumenPorProyectoGrupoYdetalle.get(i).get(11).equals("")) {
							            	aux = (double)0;
							            }else {
							            	aux = Double.parseDouble(resumenPorProyectoGrupoYdetalle.get(i).get(11).replaceAll(",", ""));
							            }
																cell.setCellValue(aux);
									}
									
									posCell++; 
						            cell = row.createCell(posCell);
						            if(resumenPorProyectoGrupoYdetalle.get(i).get(12).equals("")) {
						            	aux = (double)0;
						            }else {
						            	aux = Double.parseDouble(resumenPorProyectoGrupoYdetalle.get(i).get(12).replaceAll(",", ""));
						            }
														cell.setCellValue(aux);
									
									posCell++; 
						            cell = row.createCell(posCell);
						            if(resumenPorProyectoGrupoYdetalle.get(i).get(13).equals("")) {
						            	aux = (double)0;
						            }else {
						            	aux = Double.parseDouble(resumenPorProyectoGrupoYdetalle.get(i).get(13).replaceAll(",", ""));
						            }
														cell.setCellValue(aux);
									
									posCell++; 
						            cell = row.createCell(posCell);
						            if(resumenPorProyectoGrupoYdetalle.get(i).get(14).equals("")) {
						            	aux = (double)0;
						            }else {
						            	aux = Double.parseDouble(resumenPorProyectoGrupoYdetalle.get(i).get(14).replaceAll(",", ""));
						            }
														cell.setCellValue(aux);
			        			}
			        		}
		        		}
		        	}
		        }
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
	
	public static File exportaProformaExcelProyectos(String db, Map<String,String> mapDiccionario, List<List<String>> proyectos, Fechas desde, Fechas hasta,
			Double uf, Double usd, Double eur, List<List<String>> resumenTotales) {
		
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
            encabezado.setAlignment(HorizontalAlignment.LEFT);
            
            CellStyle detalle = libro.createCellStyle();
            detalle.setBorderBottom(BorderStyle.THIN);
            detalle.setBorderTop(BorderStyle.THIN);
            detalle.setBorderRight(BorderStyle.THIN);
            detalle.setBorderLeft(BorderStyle.THIN);
            
            CellStyle pie = libro.createCellStyle();
            pie.setBorderBottom(BorderStyle.THIN);
            pie.setBorderTop(BorderStyle.THIN);
            pie.setBorderRight(BorderStyle.THIN);
            pie.setBorderLeft(BorderStyle.THIN);
            pie.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            pie.setFillForegroundColor((short)19);
            pie.setAlignment(HorizontalAlignment.RIGHT);
            
            
            //titulos del archivo
            
            libro.setSheetName(0, "PROCESO DE EP-FACTURACION LISTADO");
            Sheet hoja1 = libro.getSheetAt(0);
            
            Row row = null;
            Cell cell = null;
            
            row = hoja1.createRow(1);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellValue("LISTADO DE EP PROYECTOS (INCLUIDOS AJUSTES DE EP)");
			
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
            cell.setCellStyle(titulo);
			cell.setCellValue("PERIODO: desde " + desde.getFechaStrDDMMAA()  + " hasta " + hasta.getFechaStrDDMMAA());
			
			
			//anchos de columnas
			for(int i=1; i<17; i++) {
				hoja1.setColumnWidth(i, 6*1000);
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
	        anchor.setCol1(9);
	        anchor.setRow1(1);
			Picture img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);
			
			
			// encabezado de la tabla
			
			int posRow = 8;
			
			row = hoja1.createRow(posRow);
			int posCell = 0;
			
			posCell++;
            cell = row.createCell(posCell);
            cell.setCellStyle(titulo);
			cell.setCellValue("RESUMEN POR PROYECTOS  (INCLUYE AJUSTES A EP):");
			
			posRow += 2;
			posCell = 0;
			
			row = hoja1.createRow(posRow);
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("SUCURSAL");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("COMERCIAL");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("RUBRO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("NOMBRE "+mapDiccionario.get("BODEGA")+"/PROYECTO");
		
			if(mapDiccionario.get("nEmpresa").equals("CIHLA")) {
				posCell++;
				cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("Nro CARGO");
			}else{
				posCell++;
				cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue(mapDiccionario.get("RUT")+" CLIENTE");
				
				posCell++;
				cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("NOMBRE CLIENTE");
			}
		
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("NOMBRE PROYECTO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("CFI (en "+mapDiccionario.get("Pesos")+")");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("SubTotal "+mapDiccionario.get("ARRIENDO"));
		        
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("SubTotal VENTA");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("Ajustes "+mapDiccionario.get("ARRIENDO"));

			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("Ajustes VENTA");
		        
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("TOTAL (en "+mapDiccionario.get("Pesos")+")");
				
		       
	        Double venta=(double)0;
	        Double arriendo=(double)0;
	        Double cfi=(double)0;
	        Double totaltotal=(double)0;
	        Double ajusteArriendo=(double)0;
	        Double ajusteVenta=(double)0;
	        
	        
	        
	        
	        
			for(int i=0;i<proyectos.size();i++){
				for(int j=0;j<resumenTotales.size();j++){
					if(proyectos.get(i).get(1).equals(resumenTotales.get(j).get(0))){
							
						posRow++;
						posCell = 0;
				        row = hoja1.createRow(posRow);
						
				        posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellValue(proyectos.get(i).get(14));
						

						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellValue(proyectos.get(i).get(10));

						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellValue(proyectos.get(i).get(15));
						
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellValue(proyectos.get(i).get(5));
							
						if(mapDiccionario.get("nEmpresa").equals("CIHLA")) {
							posCell++;
							cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellValue("");
						}else{
							posCell++;
							cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellValue(proyectos.get(i).get(6));
							
							posCell++;
							cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellValue(proyectos.get(i).get(7));
						}
				        
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellValue(proyectos.get(i).get(8));
				        
						posCell++; 
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
			            Double aux = Double.parseDouble(resumenTotales.get(j).get(3).replaceAll(",", ""));
								cell.setCellValue(aux);
						cfi += aux;
						
						posCell++; 
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
			            aux = Double.parseDouble(resumenTotales.get(j).get(1).replaceAll(",", ""));
								cell.setCellValue(aux);
						arriendo += aux;
						
						posCell++; 
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
			            aux = Double.parseDouble(resumenTotales.get(j).get(2).replaceAll(",", ""));
								cell.setCellValue(aux);
						venta += aux;
						
						posCell++; 
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
			            aux = Double.parseDouble(resumenTotales.get(j).get(5).replaceAll(",", ""));
								cell.setCellValue(aux);
						ajusteArriendo += aux;
						
						posCell++; 
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
			            aux = Double.parseDouble(resumenTotales.get(j).get(6).replaceAll(",", ""));
								cell.setCellValue(aux);
						ajusteVenta += aux;
						
						posCell++; 
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
			            aux = Double.parseDouble(resumenTotales.get(j).get(4).replaceAll(",", ""));
								cell.setCellValue(aux);
						totaltotal += aux;
					}
				}
			}
			
			posRow++;
			posCell = 0;
	        row = hoja1.createRow(posRow);
			
			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("TOTAL");
			
			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("");
			
			if(mapDiccionario.get("nEmpresa").equals("CIHLA")) {
				posCell++;
				cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("");
			}else{
				posCell++;
				cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("");
				
				posCell++;
				cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellValue("");
			}
			
			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("");
			
			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(pie);
            Double aux = cfi;
			cell.setCellValue(aux);
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(pie);
            aux = arriendo;
			cell.setCellValue(aux);
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(pie);
            aux = venta;
			cell.setCellValue(aux);
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(pie);
            aux = ajusteArriendo;
			cell.setCellValue(aux);
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(pie);
            aux = ajusteVenta;
			cell.setCellValue(aux);
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(pie);
            aux = totaltotal;
			cell.setCellValue(aux);
			
			
			
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

	public static File exportaProformaHExcelProyectos(String db, Map<String,String> mapDiccionario,
													  List<List<String>> proyectos, Map<String,List<String>> mapServicios,
													  Map<Long, Long> mapDec, String desde, String hasta,
													 Double uf, Double usd, Double eur, List<List<String>> resumenTotales) {


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
			encabezado.setAlignment(HorizontalAlignment.LEFT);

			CellStyle detalle = libro.createCellStyle();
			detalle.setBorderBottom(BorderStyle.THIN);
			detalle.setBorderTop(BorderStyle.THIN);
			detalle.setBorderRight(BorderStyle.THIN);
			detalle.setBorderLeft(BorderStyle.THIN);

			CellStyle pie = libro.createCellStyle();
			pie.setBorderBottom(BorderStyle.THIN);
			pie.setBorderTop(BorderStyle.THIN);
			pie.setBorderRight(BorderStyle.THIN);
			pie.setBorderLeft(BorderStyle.THIN);
			pie.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			pie.setFillForegroundColor((short)19);
			pie.setAlignment(HorizontalAlignment.RIGHT);


			//titulos del archivo

			libro.setSheetName(0, "PROCESO DE EP-FACTURACION LISTADO");
			Sheet hoja1 = libro.getSheetAt(0);

			Row row = null;
			Cell cell = null;

			row = hoja1.createRow(1);
			cell = row.createCell(1);
			cell.setCellStyle(titulo);
			cell.setCellValue("LISTADO DE EP/FACTURACION PROFORMA SIMPLE");

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
			cell.setCellStyle(titulo);
			cell.setCellValue("PERIODO: desde " + Fechas.DDMMAA(desde)  + " hasta " + Fechas.DDMMAA(hasta));


			//anchos de columnas
			for(int i=1; i<17; i++) {
				hoja1.setColumnWidth(i, 6*1000);
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
			anchor.setCol1(9);
			anchor.setRow1(1);
			Picture img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);


			// encabezado de la tabla

			int posRow = 8;

			row = hoja1.createRow(posRow);
			int posCell = 0;

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(titulo);
			cell.setCellValue("RESUMEN POR PROYECTOS  (INCLUYE AJUSTES A EP):");

			posRow += 2;
			posCell = 0;

			row = hoja1.createRow(posRow);

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("SUCURSAL");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("NOMBRE "+mapDiccionario.get("BODEGA")+"/PROYECTO");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("NOMBRE CLIENTE");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("NOMBRE PROYECTO");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("COMERCIAL");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("CFI (en "+mapDiccionario.get("Pesos")+")");



			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("SubTotal "+mapDiccionario.get("ARRIENDO"));

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("SubTotal VENTA");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("SubTotal SERVICIO");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("Ajustes "+mapDiccionario.get("ARRIENDO"));

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("Ajustes VENTA");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("Ajustes SERVICIO");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("TOTAL (en "+mapDiccionario.get("Pesos")+")");

			Double vta=(double)0;
			Double arr=(double)0;
			Double serv=(double)0;
			Double cfi=(double)0;
			Double ajusteArr=(double)0;
			Double ajusteVta=(double)0;
			Double ajusteServ=(double)0;
			Double tot=(double)0;

			Map<String,String> mapAuxParaRevisaServ = new HashMap<String,String>();
			for (List<String> lista1 : proyectos) {
				for (List<String> total : resumenTotales) {
					if (lista1.get(1).equals(total.get(0))) {
						Double auxTotal = (double)0;
						try{
							auxTotal=Double.parseDouble(total.get(4).replaceAll(",",""));
						}catch(Exception e){}
						List<String> servicios = mapServicios.get(total.get(0));
						String servVta = DecimalFormato.formato(0.0,mapDec.get(1L));
						String servAjuste =  DecimalFormato.formato(0.0,mapDec.get(1L));

						if(servicios!=null){
							Double auxServVta=0.0;
							Double auxServAjuste=0.0;
							try{
								auxServVta = Double.parseDouble(servicios.get(5).replaceAll(",",""));
								servVta = servicios.get(5);
							}catch(Exception e){}
							try{
								auxServAjuste = Double.parseDouble(servicios.get(6).replaceAll(",",""));
								servAjuste = servicios.get(6);
							}catch(Exception e){}
							auxTotal += auxServVta + auxServAjuste;
						}
						if( auxTotal > 0) {
							mapAuxParaRevisaServ.put(lista1.get(1),lista1.get(1));
							String totales = DecimalFormato.formato(auxTotal,mapDec.get(1L));

							posRow++;
							posCell = 0;
							row = hoja1.createRow(posRow);

							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							cell.setCellValue(lista1.get(14));

							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							cell.setCellValue(lista1.get(5));

							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							cell.setCellValue(lista1.get(7));

							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							cell.setCellValue(lista1.get(8));

							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							cell.setCellValue(lista1.get(10));

							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							Double aux = Double.parseDouble(total.get(3).replaceAll(",", ""));
										cell.setCellValue(aux);
							cfi += aux;

							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							aux = Double.parseDouble(total.get(1).replaceAll(",", ""));
										cell.setCellValue(aux);
							arr += aux;

							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							aux = Double.parseDouble(total.get(2).replaceAll(",", ""));
										cell.setCellValue(aux);
							vta += aux;

							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							aux = Double.parseDouble(servVta.replaceAll(",", ""));
										cell.setCellValue(aux);
							serv += aux;

							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							aux = Double.parseDouble(total.get(5).replaceAll(",", ""));
										cell.setCellValue(aux);
							ajusteArr += aux;

							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							aux = Double.parseDouble(total.get(6).replaceAll(",", ""));
										cell.setCellValue(aux);
							ajusteVta += aux;

							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							aux = Double.parseDouble(servAjuste.replaceAll(",", ""));
										cell.setCellValue(aux);
							ajusteServ += aux;

							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							aux = Double.parseDouble(totales.replaceAll(",", ""));
										cell.setCellValue(aux);
							tot += aux;
						}
					}
				}
			}


			for (Map.Entry<String, List<String>> entry : mapServicios.entrySet()) {
				String key = entry.getKey();
				List<String> servicios = entry.getValue();
				String valida = mapAuxParaRevisaServ.get(key);
				if(valida == null) {
					String cliente = "clente";
					String proyecto = "proyecto";
					String comercial = "comercial";
					Double auxTotal = 0.0;
					Double auxServVta=0.0;
					Double auxServAjuste=0.0;
					String servVta = DecimalFormato.formato(0.0,mapDec.get(1L));
					String servAjuste =  DecimalFormato.formato(0.0,mapDec.get(1L));
					try{
						auxServVta = Double.parseDouble(servicios.get(5).replaceAll(",",""));
						servVta = servicios.get(5);
					}catch(Exception e){}
					try{
						auxServAjuste = Double.parseDouble(servicios.get(6).replaceAll(",",""));
						servAjuste = servicios.get(6);
					}catch(Exception e){}
					auxTotal += auxServVta + auxServAjuste;

					posRow++;
					posCell = 0;
					row = hoja1.createRow(posRow);

					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellValue("");

					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellValue(servicios.get(0));

					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellValue(cliente);

					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellValue(proyecto);

					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellValue(comercial);

					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					Double aux = (double)0;
					cell.setCellValue(aux);
					cfi += aux;

					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					aux = (double)0;
					cell.setCellValue(aux);
					arr += aux;

					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					aux = (double)0;
					cell.setCellValue(aux);
					vta += aux;

					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					aux = Double.parseDouble(servVta.replaceAll(",", ""));
					cell.setCellValue(aux);
					serv += aux;

					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					aux = (double)0;
					cell.setCellValue(aux);
					ajusteArr += aux;

					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					aux = (double)0;
					cell.setCellValue(aux);
					ajusteVta += aux;

					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					aux = Double.parseDouble(servAjuste.replaceAll(",", ""));
					cell.setCellValue(aux);
					ajusteServ += aux;

					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					aux = auxTotal;
					cell.setCellValue(aux);
					tot += aux;
				}
			}

			posRow++;
			posCell = 0;
			row = hoja1.createRow(posRow);

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue("TOTALES");

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
			cell.setCellValue(cfi);

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue(arr);

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue(vta);

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue(serv);

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue(ajusteArr);

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue(ajusteVta);

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue(ajusteServ);

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue(tot);

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

	public static File exportaFacturaResumen2Excel(String db, Map<String,String> mapDiccionario,
													  List<List<String>> proyectos, Map<Long, Long> mapDec, String desde, String hasta,
													  Double uf, Double usd, Double eur, List<List<String>> resumenTotales) {

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
			encabezado.setAlignment(HorizontalAlignment.LEFT);

			CellStyle detalle = libro.createCellStyle();
			detalle.setBorderBottom(BorderStyle.THIN);
			detalle.setBorderTop(BorderStyle.THIN);
			detalle.setBorderRight(BorderStyle.THIN);
			detalle.setBorderLeft(BorderStyle.THIN);

			CellStyle pie = libro.createCellStyle();
			pie.setBorderBottom(BorderStyle.THIN);
			pie.setBorderTop(BorderStyle.THIN);
			pie.setBorderRight(BorderStyle.THIN);
			pie.setBorderLeft(BorderStyle.THIN);
			pie.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			pie.setFillForegroundColor((short)19);
			pie.setAlignment(HorizontalAlignment.RIGHT);


			//titulos del archivo


			libro.setSheetName(1, "DETALLE");
			Sheet hoja1 = libro.getSheetAt(1);

			Row row = null;
			Cell cell = null;

			row = hoja1.createRow(1);
			cell = row.createCell(1);
			cell.setCellStyle(titulo);
			cell.setCellValue("DETALLE DE EP/FACTURACION PROFORMA (POR COMERCIAL)");

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
			cell.setCellStyle(titulo);
			cell.setCellValue("PERIODO: desde " + Fechas.DDMMAA(desde)  + " hasta " + Fechas.DDMMAA(hasta));


			//anchos de columnas
			for(int i=1; i<17; i++) {
				hoja1.setColumnWidth(i, 6*1000);
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
			anchor.setCol1(9);
			anchor.setRow1(1);
			Picture img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);


			// encabezado de la tabla

			int posRow = 8;

			row = hoja1.createRow(posRow);
			int posCell = 0;

			row = hoja1.createRow(posRow);

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("SUCURSAL");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("NOMBRE "+mapDiccionario.get("BODEGA")+"/PROYECTO");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("NOMBRE CLIENTE");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("NOMBRE PROYECTO");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("COMERCIAL");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("CFI (en "+mapDiccionario.get("Pesos")+")");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("SubTotal "+mapDiccionario.get("ARRIENDO"));

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("SubTotal VENTA");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("Ajustes "+mapDiccionario.get("ARRIENDO"));

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("Ajustes VENTA");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("TOTAL (en "+mapDiccionario.get("Pesos")+")");

			Double vta=(double)0;
			Double arr=(double)0;
			Double cfi=(double)0;
			Double ajusteArr=(double)0;
			Double ajusteVta=(double)0;
			Double tot=(double)0;

			Map<String,List<Double>> mapResumen = new HashMap<String,List<Double>>();
			for (List<String> lista1 : proyectos) {
				for (List<String> total : resumenTotales) {
					if (lista1.get(1).equals(total.get(0))) {
						Double auxTotal = (double)0;
						try{
							auxTotal=Double.parseDouble(total.get(4).replaceAll(",",""));
						}catch(Exception e){}
						String servVta = DecimalFormato.formato(0.0,mapDec.get(1L));
						String servAjuste =  DecimalFormato.formato(0.0,mapDec.get(1L));
						if( auxTotal > 0) {

							String totales = DecimalFormato.formato(auxTotal,mapDec.get(1L));

							posRow++;
							posCell = 0;
							row = hoja1.createRow(posRow);

							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							cell.setCellValue(lista1.get(14));

							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							cell.setCellValue(lista1.get(5));

							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							cell.setCellValue(lista1.get(7));

							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							cell.setCellValue(lista1.get(8));

							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							cell.setCellValue(lista1.get(10));

							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							Double aux = Double.parseDouble(total.get(3).replaceAll(",", ""));
										cell.setCellValue(aux);
							cfi += aux;

							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							aux = Double.parseDouble(total.get(1).replaceAll(",", ""));
										cell.setCellValue(aux);
							arr += aux;

							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							aux = Double.parseDouble(total.get(2).replaceAll(",", ""));
										cell.setCellValue(aux);
							vta += aux;

							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							aux = Double.parseDouble(total.get(5).replaceAll(",", ""));
										cell.setCellValue(aux);
							ajusteArr += aux;

							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							aux = Double.parseDouble(total.get(6).replaceAll(",", ""));
										cell.setCellValue(aux);
							ajusteVta += aux;

							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							aux = Double.parseDouble(totales.replaceAll(",", ""));
										cell.setCellValue(aux);
							tot += aux;

							List<Double> aux2 = new ArrayList<Double>();
							aux2.add(Double.parseDouble(total.get(3).replaceAll(",","")));
							aux2.add(Double.parseDouble(total.get(1).replaceAll(",","")));
							aux2.add(Double.parseDouble(total.get(2).replaceAll(",","")));
							aux2.add(Double.parseDouble(total.get(5).replaceAll(",","")));
							aux2.add(Double.parseDouble(total.get(6).replaceAll(",","")));
							aux2.add(Double.parseDouble(totales.replaceAll(",","")));
							List<Double> aux22 = mapResumen.get(lista1.get(10));
							if(aux22==null){
								mapResumen.put(lista1.get(10),aux2);
							}else{
								aux2.set(0,aux2.get(0)+aux22.get(0));
								aux2.set(1,aux2.get(1)+aux22.get(1));
								aux2.set(2,aux2.get(2)+aux22.get(2));
								aux2.set(3,aux2.get(3)+aux22.get(3));
								aux2.set(4,aux2.get(4)+aux22.get(4));
								aux2.set(5,aux2.get(5)+aux22.get(5));
								mapResumen.put(lista1.get(10),aux2);
							}

						}
					}
				}
			}

			posRow++;
			posCell = 0;
			row = hoja1.createRow(posRow);

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue("TOTALES");

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
			cell.setCellValue(cfi);

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue(arr);

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue(vta);

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue(ajusteArr);

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue(ajusteVta);

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue(tot);

			posRow = posRow + 5;
			row = hoja1.createRow(posRow);
			cell = row.createCell(1);
			Hyperlink hiper = helper.createHyperlink(HyperlinkType.URL);
			hiper.setAddress("https://www.inqsol.cl");
			cell.setHyperlink(hiper);
			cell.setCellValue("Documento generado desde MADA propiedad de INQSOL");



			libro.setSheetName(0, "RESUMEN");
			hoja1 = libro.getSheetAt(0);

			row = hoja1.createRow(1);
			cell = row.createCell(1);
			cell.setCellStyle(titulo);
			cell.setCellValue("RESUMEN DE EP/FACTURACION PROFORMA (POR COMERCIAL)");

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
			cell.setCellStyle(titulo);
			cell.setCellValue("PERIODO: desde " + Fechas.DDMMAA(desde)  + " hasta " + Fechas.DDMMAA(hasta));


			//anchos de columnas
			for(int i=1; i<17; i++) {
				hoja1.setColumnWidth(i, 6*1000);
			}
			//INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
			x = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
			bytes = IOUtils.toByteArray(x);
			x.close();
			pngIndex = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			draw = hoja1.createDrawingPatriarch();
			helper = libro.getCreationHelper();
			anchor = helper.createClientAnchor();
			//set top-left corner for the image
			anchor.setCol1(9);
			anchor.setRow1(1);
			img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);


			// encabezado de la tabla

			posRow = 8;

			row = hoja1.createRow(posRow);
			posCell = 0;

			row = hoja1.createRow(posRow);

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("COMERCIAL");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("CFI (en "+mapDiccionario.get("Pesos")+")");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("SubTotal "+mapDiccionario.get("ARRIENDO"));

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("SubTotal VENTA");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("Ajustes "+mapDiccionario.get("ARRIENDO"));

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("Ajustes VENTA");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellValue("TOTAL (en "+mapDiccionario.get("Pesos")+")");

			vta=(double)0;
			arr=(double)0;
			cfi=(double)0;
			ajusteArr=(double)0;
			ajusteVta=(double)0;
			tot=(double)0;


			for (String key : mapResumen.keySet()) {
				posRow++;
				posCell = 0;
				row = hoja1.createRow(posRow);

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(key);

				List<Double> values = mapResumen.get(key);
				for (int i = 0; i < values.size(); i++) {
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellValue(values.get(i));
					if(i==0) vta+=values.get(i);
					if(i==1) arr+=values.get(i);
					if(i==2) cfi+=values.get(i);
					if(i==3) ajusteArr+=values.get(i);
					if(i==4) ajusteVta+=values.get(i);
					if(i==5) tot+=values.get(i);
				}

			}

			posRow++;
			posCell = 0;
			row = hoja1.createRow(posRow);

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue("TOTALES");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue(cfi);

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue(arr);

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue(vta);

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue(ajusteArr);

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue(ajusteVta);

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellValue(tot);

			posRow = posRow + 5;
			row = hoja1.createRow(posRow);
			cell = row.createCell(1);
			hiper = helper.createHyperlink(HyperlinkType.URL);
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
	
	

	
}
	
	
	
