package models.reports;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.TempFile;

import models.tables.BodegaEmpresa;
import models.tables.Cliente;
import models.tables.ListaPrecioServicio;
import models.tables.Proyecto;
import models.tables.VentaServicio;
import models.utilities.Archivos;
import models.utilities.Fechas;

public class ReportOdo {
	
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatint = new DecimalFormat("#,##0");
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble4 = new DecimalFormat("#,##0.0000");
	static SimpleDateFormat myformatfecha = new SimpleDateFormat("dd-MM-yyyy");
	
	static DecimalFormat myformatMonedaOrigen = new DecimalFormat("#,##0");
	static DecimalFormat myformatMoneda = new DecimalFormat("#,##0");
	
	
	
	
	
	public static List<List<String>> resumenTotalesPorProyecto(Connection con, String db, List<VentaServicio> listVentaServicio, Map<String, Double> mapFijaTasas, Map<Long,Double> mapTasas, 
			Map<Long,Long> mapDec, Map<Long,Double> mapTotalAjustePorBodega,
			Map<Long,BodegaEmpresa> mapBodegas, Map<String,ListaPrecioServicio> mapPrecios, Long id_grupo, Map<Long,Long> mapIdEquipoVsIdGrupo) {
		List<List<String>> lista = new ArrayList<List<String>>();
		switch(mapDec.get((long) 1).toString()) {
		 case "0": myformatMonedaOrigen = new DecimalFormat("#,##0"); break;
		 case "2": myformatMonedaOrigen = new DecimalFormat("#,##0.00"); break;
		 case "4": myformatMonedaOrigen = new DecimalFormat("#,##0.0000"); break;
		 case "6": myformatMonedaOrigen = new DecimalFormat("#,##0.000000"); break;
		 default:  break;
		}
		
		Map<String,List<String>> auxCantPorServicio = new HashMap<String,List<String>>();
		
		listVentaServicio.forEach(x->{
			ListaPrecioServicio precioLista = mapPrecios.get(x.getId_bodegaEmpresa()+"_"+x.getId_servicio()+"_"+x.getId_cotiOdo());
		
			if(precioLista!=null) {
				Double cantidad = Double.parseDouble(x.getCantidad().replaceAll(",", ""));
				String fecha = x.getFecha();
				List<String> auxLista = new ArrayList<String>();
				List<String> auxCantFech = auxCantPorServicio.get(x.getId_bodegaEmpresa()+"_"+x.getId_servicio()+"_"+x.getId_equipo()+"_"+x.getId_cotiOdo());
				if(auxCantFech==null) {
					auxLista.add(cantidad.toString());
					auxLista.add(fecha);
					auxCantPorServicio.put(x.getId_bodegaEmpresa()+"_"+x.getId_servicio()+"_"+x.getId_equipo()+"_"+x.getId_cotiOdo(), auxLista);
				}else {
					Double auxCant = Double.parseDouble(auxCantFech.get(0));
					cantidad = auxCant + cantidad;
					auxLista.add(cantidad.toString());
					auxLista.add(fecha);
					auxCantPorServicio.put(x.getId_bodegaEmpresa()+"_"+x.getId_servicio()+"_"+x.getId_equipo()+"_"+x.getId_cotiOdo(), auxLista);
				}
			}
		});
			
		
		Map<Long,Double> auxTotalSinAjuste = new HashMap<Long,Double>();
		
		auxCantPorServicio.forEach((k,vcantFech)->{
			String aux1[] = k.split("_");
			String idBodegaEmpresa = aux1[0];
			String idServicio = aux1[1];
			Long id_equipo = Long.parseLong(aux1[2]);
			Long id_cotiOdo = Long.parseLong(aux1[3]);
			
			Map<Long,Double> tasas = new HashMap<Long,Double>();
			for (Map.Entry<Long, Double> entry : mapTasas.entrySet()) {
    	  		Long kk = entry.getKey();
    	  		Double v= entry.getValue();
    	  		Double aux = mapFijaTasas.get(idBodegaEmpresa + "_" +kk);
				if(aux != null) {
					tasas.put(kk, aux);
				}else {
					tasas.put(kk, v);
				}
			}
			
			
			boolean flag = true;
			
			if((long)id_grupo > (long)0) {
				flag = false;
				Long idGrupoAux = mapIdEquipoVsIdGrupo.get(id_equipo); 
				if(idGrupoAux!=null && (long)idGrupoAux == (long)id_grupo) {
					flag = true;
				}
			}
			
			if(flag) {
				Double totalFinal = (double)0;
				Double cant = Double.parseDouble(vcantFech.get(0));
				Double cantAdicional = (double)0;
				
				Long aplicaMinimo = (long)0;
				Double cantMinimo = (double)0;
				Double precioMinimo = (double)0;
				Double precioAdicional = (double)0;
				
				ListaPrecioServicio precioLista = mapPrecios.get(idBodegaEmpresa+"_"+idServicio+"_"+id_cotiOdo);
				
				if(precioLista!=null) {
					aplicaMinimo = precioLista.getAplicaMinimo();
					cantMinimo = precioLista.getCantMinimo();
					precioMinimo = cantMinimo * precioLista.getPrecio();
					
					Double tasaCambio = tasas.get(precioLista.getId_moneda());
					if(tasaCambio==null) {
						tasaCambio = (double)1;
					}
					
					//calcula si aplica minimos:
					if(aplicaMinimo == (long)1) {
					//	if(cant > cantMinimo || cant < cantMinimo) {
							cantAdicional = cant - cantMinimo;
							precioMinimo = precioMinimo * tasaCambio;
							if((double) cantAdicional > (double) 0) {
								precioAdicional = precioLista.getPrecioAdicional();
								precioAdicional = cantAdicional * precioAdicional * tasaCambio;
							}
							totalFinal = precioMinimo + precioAdicional;
//						}
//						totalFinal = cant * precioLista.getPrecio() * tasaCambio;
					}else {
						totalFinal = cant * precioLista.getPrecio() * tasaCambio;
					}
				}
				
				Double aux = auxTotalSinAjuste.get(Long.parseLong(idBodegaEmpresa));
				if(aux != null) {
					totalFinal += aux;
				}
				auxTotalSinAjuste.put(Long.parseLong(idBodegaEmpresa), totalFinal);
				
			}
		});
		
		auxTotalSinAjuste.forEach((k,v)->{
			if((double)v > (double)0) {
				Long id_bodegaEmpresa = k;
				String nomBodega = "";
				String nickCliente = "";
				String nickProyecto = "";
				String comercial = "";
				Long id_proyecto = (long)0;
				Long id_cliente = (long)0;
				String nameSucursal = "";
				BodegaEmpresa bodega = mapBodegas.get(id_bodegaEmpresa);
				if(bodega!=null) {
					nomBodega = bodega.getNombre();
					nickCliente = bodega.getNickCliente();
					nickProyecto = bodega.getNickProyecto();
					comercial = bodega.getComercial();
					id_proyecto = bodega.getId_proyecto();
					id_cliente = bodega.getId_cliente();
					nameSucursal = bodega.getNameSucursal();
				}
				Double precioAntesDeAjuste = v;
				Double ajuste = mapTotalAjustePorBodega.get(k);
				if(ajuste==null) {
					ajuste=(double)0;
				}
				Double precioDespuesDeAjuste = v + ajuste;
				List<String> aux = new ArrayList<String>();
				aux.add(id_bodegaEmpresa.toString());							//0 id_bodegaEmpresa
				aux.add(nomBodega);												//1 nomBodega
				aux.add(nickCliente);											//2 nickCliente
				aux.add(nickProyecto);											//3 nickProyecto
				aux.add(comercial);												//4 comercial
				aux.add(myformatMonedaOrigen.format(precioAntesDeAjuste));		//5 precioAntesDeAjuste
				aux.add(myformatMonedaOrigen.format(ajuste));					//6 ajuste
				aux.add(myformatMonedaOrigen.format(precioDespuesDeAjuste));	//7 precioDespuesDeAjuste
				aux.add(id_proyecto.toString());							//8 id_proyecto
				aux.add(id_cliente.toString());								//9 id_cliente
				aux.add(nameSucursal);								//10 namesucursal
				
				lista.add(aux);
			}
		});
		return(lista);
	}
	
	
	public static List<List<String>> detalleProformaPorServicio(Connection con, String db, List<VentaServicio> listVentaServicio, Map<Long,Double> tasas, Map<Long,Long> mapDec ) {
		List<List<String>> lista = new ArrayList<List<String>>();
		switch(mapDec.get((long) 1).toString()) {
		 case "0": myformatMonedaOrigen = new DecimalFormat("#,##0"); break;
		 case "2": myformatMonedaOrigen = new DecimalFormat("#,##0.00"); break;
		 case "4": myformatMonedaOrigen = new DecimalFormat("#,##0.0000"); break;
		 case "6": myformatMonedaOrigen = new DecimalFormat("#,##0.000000"); break;
		 default:  break;
		}
		Map<String,ListaPrecioServicio> mapPrecios = ListaPrecioServicio.mapAllListaPrecioServicio(con, db);
		
		listVentaServicio.forEach(x->{
			ListaPrecioServicio precioLista = mapPrecios.get(x.getId_bodegaEmpresa()+"_"+x.getId_servicio()+"_"+x.getId_cotiOdo());

			Double cantidad = Double.parseDouble(x.getCantidad().replaceAll(",", ""));
			Double precio = (double)0;
			Double tasa = (double)1;
			Long id_moneda = (long)1;
			String nickMoneda = "";
			
			Long aplicaMinimo = (long)0;
			Double cantMinimo = (double)0;
			Double precioAdicional = (double)0;
			
			if(precioLista!=null) {
				precio = precioLista.getPrecio();
				tasa = tasas.get(precioLista.getId_moneda());
				if(tasa==null) {
					tasa = (double)1;
				}
				id_moneda = precioLista.getId_moneda();
				nickMoneda = precioLista.getNickMoneda();
				
				aplicaMinimo = precioLista.getAplicaMinimo();
				cantMinimo = precioLista.getCantMinimo();
				precioAdicional = precioLista.getPrecioAdicional();
			}
			
			switch(mapDec.get(id_moneda).toString()) {
			 case "0": myformatMoneda = new DecimalFormat("#,##0"); break;
			 case "2": myformatMoneda = new DecimalFormat("#,##0.00"); break;
			 case "4": myformatMoneda = new DecimalFormat("#,##0.0000"); break;
			 case "6": myformatMoneda = new DecimalFormat("#,##0.000000"); break;
			 default:  break;
			}
			
			List<String> aux = new ArrayList<String>();
			aux.add(x.getId().toString());					//0 id_ventaServicio
			aux.add(x.getNomClaseServicio());				//1 getNomClaseServicio
			aux.add(x.getCodServicio());					//2 getCodServicio
			aux.add(x.getNomServicio());					//3 getNomServicio
			aux.add(x.getFecha());							//4 getFecha
			aux.add(x.getDetalle());						//5 getDetalle
			aux.add(x.getId().toString());					//6 Id_ventaServicio
			aux.add(x.getHoraIni());						//7 getHoraIni
			aux.add(x.getHoraTer());						//8 getHoraTer
			aux.add(x.getCodEquipo());						//9 getCodEquipo
			aux.add(x.getNomEquipo());						//10 getNomEquipo
			aux.add(x.getUnidadServicio());					//11 getUnidadServicio
			aux.add(x.getLecturaIni());						//12 getLecturaIni
			aux.add(x.getLecturaTer());						//13 getLecturaTer
			aux.add(x.getCantidad());						//14 getCantidad
			aux.add(nickMoneda);							//15 nickMoneda
			aux.add(myformatMoneda.format(precio));								//16 pu
			aux.add(myformatMoneda.format(precio * cantidad));					//17 total
			aux.add(myformatdouble2.format(tasa));								//18 tasa cambio
			aux.add(myformatMonedaOrigen.format(precio * cantidad * tasa));		//19 total * tasa cambio
			
			aux.add(aplicaMinimo.toString());				//20 aplicaMinimo
			aux.add(cantMinimo.toString());					//21 cantminimo
			aux.add(precioAdicional.toString());			//22 pu precioAdicional
			
			aux.add(x.getId_cotiOdo().toString());			//23 getId_cotiOdo
			aux.add(x.getNroCotiOdo().toString());			//24 getNroCotiOdo
			
			lista.add(aux);
		});
		
		
		return(lista);
	}
	
	public static List<List<String>> proformaAgrupadoPorServicio(List<List<String>> groupPorClaseServicioEquipo, List<List<String>> detalleProformaPorServicio, Map<Long,Long> mapDec) {
		List<List<String>> lista = new ArrayList<List<String>>();
		switch(mapDec.get((long) 1).toString()) {
		 case "0": myformatMonedaOrigen = new DecimalFormat("#,##0"); break;
		 case "2": myformatMonedaOrigen = new DecimalFormat("#,##0.00"); break;
		 case "4": myformatMonedaOrigen = new DecimalFormat("#,##0.0000"); break;
		 case "6": myformatMonedaOrigen = new DecimalFormat("#,##0.000000"); break;
		 default:  break;
		}
		Map<String,Double> map = new HashMap<String,Double>();
		
		
		groupPorClaseServicioEquipo.forEach(x->{
			Double total = Double.parseDouble(x.get(11).replaceAll(",", ""));
			
	
			Double aux = map.get(x.get(12)+" - "+x.get(2));
			if(aux == null) {
				aux = (double)0;
			}
			map.put(x.get(12)+" - "+x.get(2), total + aux);
		});
		
		map.forEach((k,v)->{
			String total = myformatMonedaOrigen.format(v);
			List<String> aux = new ArrayList<String>();
			aux.add(k); //0 = getCodServicio_getNroCotiOdo
			aux.add(total);
			lista.add(aux);
		});
		return(lista);
	}
	
	public static List<List<String>> proformaGroupPorClaseServicioEquipo(List<List<String>> detalleProformaPorServicio, Map<Long,Long> mapDec) {
		List<List<String>> lista = new ArrayList<List<String>>();
		switch(mapDec.get((long) 1).toString()) {
		 case "0": myformatMonedaOrigen = new DecimalFormat("#,##0"); break;
		 case "2": myformatMonedaOrigen = new DecimalFormat("#,##0.00"); break;
		 case "4": myformatMonedaOrigen = new DecimalFormat("#,##0.0000"); break;
		 case "6": myformatMonedaOrigen = new DecimalFormat("#,##0.000000"); break;
		 default:  break;
		}
		
		Map<String,Double> mapCant = new HashMap<String,Double>();
		Map<String,Double> mapTot = new HashMap<String,Double>();
		Map<String,List<String>> mapServicios = new HashMap<String,List<String>>();
		
		detalleProformaPorServicio.forEach(x->{
			Double cant = Double.parseDouble(x.get(14).replaceAll(",", ""));
			Double total = Double.parseDouble(x.get(19).replaceAll(",", ""));
			
			Double auxCant = mapCant.get(x.get(1)+"_"+x.get(2)+"_"+x.get(9)+"_"+x.get(24));
			Double auxTotal = mapTot.get(x.get(1)+"_"+x.get(2)+"_"+x.get(9)+"_"+x.get(24));
			
			if(auxCant == null || auxTotal == null) {
				auxCant = (double)0;
				auxTotal = (double)0;
			}
			
			mapCant.put(x.get(1)+"_"+x.get(2)+"_"+x.get(9)+"_"+x.get(24),cant + auxCant);
			mapTot.put(x.get(1)+"_"+x.get(2)+"_"+x.get(9)+"_"+x.get(24),total + auxTotal);
			
			List<String> aux = new ArrayList<String>();
			aux.add(x.get(1));	// 0 getNomClaseServicio
			aux.add(x.get(2));	// 1 getCodServicio
			aux.add(x.get(3));	// 2 getNomServicio
			aux.add(x.get(9));	// 3 getCodEquipo
			aux.add(x.get(10));	// 4 getNomEquipo
			aux.add(x.get(11));	// 5 getUnidadServicio
			
			aux.add(x.get(18));	// 6 tasa cambio
			aux.add(x.get(20));	// 7 aplicaMinimo
			aux.add(x.get(21));	// 8 cantminimo
			aux.add(x.get(22));	// 9 pu precioAdicional
			aux.add(x.get(24));	// 10 getNroCotiOdo
			
			mapServicios.put(x.get(1)+"_"+x.get(2)+"_"+x.get(9)+"_"+x.get(24),aux);
			//getNomClaseServicio_getCodServicio_getCodEquipo_getId_cotiOdo
		});
		
		mapServicios.forEach((k,v)->{
			
			Double cant = mapCant.get(k);
			Double tot = mapTot.get(k);
			if(cant==null || tot==null) {
				cant = (double)0;
				tot = (double)0;
			}
			
			Double cantMinimo = (double)0;
			Double precioMinimo = (double)0;
			Double precioAdicional = (double)0;
			Double cantAdicional = (double)0;
			Double totalFinal = (double)0;
			
			//calcula si aplica minimos:
			if(v.get(7).equals("1")) {
				cantMinimo = Double.parseDouble(v.get(8).replaceAll(",",""));
	//			if(cant > cantMinimo || cant < cantMinimo) {
					Double tasaCambio = Double.parseDouble(v.get(6).replaceAll(",",""));
					cantAdicional = cant - cantMinimo;
					precioMinimo = (tot/cant) * cantMinimo;
					if((double) cantAdicional > (double) 0) {
						precioAdicional = Double.parseDouble(v.get(9).replaceAll(",",""));
						precioAdicional = cantAdicional * precioAdicional * tasaCambio;
					}
					totalFinal = precioMinimo + precioAdicional;
	//			}
			}else {
				totalFinal = tot;
			}
			
			
			String cantidad = myformatdouble2.format(cant);
			String total = myformatMonedaOrigen.format(tot);
			
			
			String cantMinimoStr = myformatdouble2.format(cantMinimo);
			String precioMinimoStr = myformatMonedaOrigen.format(precioMinimo);
			String precioAdicionalStr = myformatMonedaOrigen.format(precioAdicional);
			String precioTotalFinal = myformatMonedaOrigen.format(totalFinal);
			
			
			List<String> aux = new ArrayList<String>();
			aux.add(v.get(0));					//0 id_ventaServicio
			aux.add(v.get(1));					//1 getNomClaseServicio
			aux.add(v.get(2));					//2 getCodServicio
			aux.add(v.get(3));					//3 getNomServicio
			aux.add(v.get(4));					//4 getFecha
			aux.add(v.get(5));					//5 getDetalle
			aux.add(cantidad);					//6 cantidad
			aux.add(total);						//7 total
			
			aux.add(cantMinimoStr);				//8 cantidad minima
			aux.add(precioMinimoStr);			//9 precio total minimo
			aux.add(precioAdicionalStr);		//10 precio total adicional
			aux.add(precioTotalFinal);			//11 precio total final
			aux.add(v.get(10));					//12 getNroCotiOdo
			lista.add(aux);
		});
		return(lista);
	}
	
	
	public static File reportProfProyDetalleOdoExcel(String db, Map<String,String> mapDiccionario, Map<String,String> mapPermiso,
			List<List<String>> detalleProformaPorServicio, List<String> fechas, List<Double> tasaCambio, 
			BodegaEmpresa bodega, Proyecto proyecto, Cliente cliente, List<List<String>> groupPorClaseServicioEquipo, List<List<String>> listaAjustes) {
		
		File tmp = TempFile.createTempFile("tmp","null");
		
		try {
			String path = "formatos/excel.xlsx";
			InputStream formato = Archivos.leerArchivo(path);
		    Workbook libro = WorkbookFactory.create(formato);
		    formato.close();
		    
		    // 0 negro 1 blanco 2 rojo 3 verde 4 azul 5 amarillo 19 celeste
		    CellStyle titulo = libro.createCellStyle();
		    Font font = libro.createFont();
		    font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		    font.setColor((short)4);
		    font.setFontHeight((short)(14*20));
		    titulo.setFont(font);
		    
		    CellStyle subtitulo = libro.createCellStyle();
		    Font font2 = libro.createFont();
		    font2.setBoldweight(Font.BOLDWEIGHT_BOLD);
		    font2.setColor((short)0);
		    font2.setFontHeight((short)(12*20));
		    subtitulo.setFont(font2);
		    
		    CellStyle encabezado = libro.createCellStyle();
		    encabezado.setBorderBottom(CellStyle.BORDER_THIN);
		    encabezado.setBorderTop(CellStyle.BORDER_THIN);
		    encabezado.setBorderRight(CellStyle.BORDER_THIN);
		    encabezado.setBorderLeft(CellStyle.BORDER_THIN);
		    encabezado.setFillPattern(CellStyle.SOLID_FOREGROUND);
		    encabezado.setFillForegroundColor((short)19);
		    encabezado.setAlignment(CellStyle.ALIGN_CENTER);
		    
		    CellStyle detalle = libro.createCellStyle();
		    detalle.setBorderBottom(CellStyle.BORDER_THIN);
		    detalle.setBorderTop(CellStyle.BORDER_THIN);
		    detalle.setBorderRight(CellStyle.BORDER_THIN);
		    detalle.setBorderLeft(CellStyle.BORDER_THIN);
		    
		    //********************************
		    //ESTADO DE PAGO DETALLADO
		    //***********************************
		    
		    libro.setSheetName(0, "SERVICIO EP DETALLE");
		    Sheet hoja1 = libro.getSheetAt(0);
		    
		    Row row = null;
		    Cell cell = null;
		    
		    row = hoja1.createRow(1);
		    cell = row.createCell(1);
		    cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("DETALLE EP/FACTURA PROFORMA SERVICIO");
			
			row = hoja1.createRow(2);
		    cell = row.createCell(1);
		    cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("EMPRESA: "+mapDiccionario.get("nEmpresa"));
			
			row = hoja1.createRow(3);
		    cell = row.createCell(1);
		    cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FECHA: "+Fechas.hoy().getFechaStrDDMMAA());
			
			row = hoja1.createRow(5);
		    cell = row.createCell(1);
		    cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("CLIENTE: "+cliente.rut + " --- " + cliente.nombre);
			
		    row = hoja1.createRow(6);
		    cell = row.createCell(1);
		    cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(mapDiccionario.get("BODEGA")+"/PROYECTO: "+bodega.getNombre().toUpperCase());
			
			row = hoja1.createRow(7);
		    cell = row.createCell(1);
		    cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("PROYECTO: "+proyecto.nickName.toUpperCase());
			
			row = hoja1.createRow(8);
		    cell = row.createCell(1);
		    cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("PERIODO: desde " + fechas.get(2)  + " hasta " + fechas.get(3));
			
			row = hoja1.createRow(9);
		    cell = row.createCell(1);
		    cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("COMERCIAL: "+bodega.comercial);
			
			
			// encabezado tabla resumen:
			
			int posRow = 11;
			int posCell = 0;
			
			row = hoja1.createRow(posRow);
			posCell++; 
		    cell = row.createCell(posCell);
		    cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("RESUMEN AGRUPADO:");
			
			
			posRow++;
			posRow++;
			
			row = hoja1.createRow(posRow);
			posCell = 1;
			
			posCell++; 
			hoja1.setColumnWidth(posCell, 5*1000);
		    cell = row.createCell(posCell);
		    cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("CLASE");
			
			posCell++;
			hoja1.setColumnWidth(posCell, 5*1000);
		    cell = row.createCell(posCell);
		    cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("NRO.COTI");
			
			posCell++;
			hoja1.setColumnWidth(posCell, 5*1000);
		    cell = row.createCell(posCell);
		    cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("COD-SERVICIO");
			
			posCell++; 
			hoja1.setColumnWidth(posCell, 10*1000);
		    cell = row.createCell(posCell);
		    cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("SERVICIO");
			
			posCell++;
			hoja1.setColumnWidth(posCell, 5*1000);
		    cell = row.createCell(posCell);
		    cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("COD-EQUIPO");
			
			posCell++; 
			hoja1.setColumnWidth(posCell, 10*1000);
		    cell = row.createCell(posCell);
		    cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("EQUIPO");
			
			posCell++; 
			hoja1.setColumnWidth(posCell, 3*1000);
		    cell = row.createCell(posCell);
		    cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("UN");
			
			posCell++; 
			hoja1.setColumnWidth(posCell, 5*1000);
		    cell = row.createCell(posCell);
		    cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("CANT");
			
			posCell++; 
			hoja1.setColumnWidth(posCell, 5*1000);
		    cell = row.createCell(posCell);
		    cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TOTAL");
			
			
			
			posCell++; 
			hoja1.setColumnWidth(posCell, 5*1000);
		    cell = row.createCell(posCell);
		    cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("CANT MIN");
			
			posCell++; 
			hoja1.setColumnWidth(posCell, 5*1000);
		    cell = row.createCell(posCell);
		    cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TOTAL MINIMO");
			
			posCell++; 
			hoja1.setColumnWidth(posCell, 5*1000);
		    cell = row.createCell(posCell);
		    cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TOTAL ADICIONAL");
			
			posCell++; 
			hoja1.setColumnWidth(posCell, 5*1000);
		    cell = row.createCell(posCell);
		    cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("GRAN TOTAL");
			
			
			//RESUMEN AGRUPADO DATOS

			Double subCant = (double)0;
			Double subTot = (double)0;
			Double subCantMin = (double)0;
			Double subTotMin = (double)0;
			Double subTotAdic = (double)0;
			Double subTotTot = (double)0;
			
			posRow++;
			Double aux = (double)0;
			
			for(int i=0; i<groupPorClaseServicioEquipo.size(); i++){
				
				row = hoja1.createRow(posRow);
				posCell = 1;
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(groupPorClaseServicioEquipo.get(i).get(0));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(groupPorClaseServicioEquipo.get(i).get(12));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(groupPorClaseServicioEquipo.get(i).get(1));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(groupPorClaseServicioEquipo.get(i).get(2));
				
				posCell++; 
		        cell = row.createCell(posCell);
		        cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(groupPorClaseServicioEquipo.get(i).get(3));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(groupPorClaseServicioEquipo.get(i).get(4));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(groupPorClaseServicioEquipo.get(i).get(5));
				
				posCell++; 
		        cell = row.createCell(posCell);
		        cell.setCellStyle(detalle);
		        aux = Double.parseDouble(groupPorClaseServicioEquipo.get(i).get(6).replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);
				subCant += aux;
				
				posCell++; 
		        cell = row.createCell(posCell);
		        cell.setCellStyle(detalle);
		        aux = Double.parseDouble(groupPorClaseServicioEquipo.get(i).get(7).replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);
				subTot += aux;
				
				
				
				
				
				posCell++; 
		        cell = row.createCell(posCell);
		        cell.setCellStyle(detalle);
		        aux = Double.parseDouble(groupPorClaseServicioEquipo.get(i).get(8).replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);
				subCantMin += aux;
				
				posCell++; 
		        cell = row.createCell(posCell);
		        cell.setCellStyle(detalle);
		        aux = Double.parseDouble(groupPorClaseServicioEquipo.get(i).get(9).replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);
				subTotMin += aux;
				
				posCell++; 
		        cell = row.createCell(posCell);
		        cell.setCellStyle(detalle);
		        aux = Double.parseDouble(groupPorClaseServicioEquipo.get(i).get(10).replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);
				subTotAdic += aux;
				
				posCell++; 
		        cell = row.createCell(posCell);
		        cell.setCellStyle(detalle);
		        aux = Double.parseDouble(groupPorClaseServicioEquipo.get(i).get(11).replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);
				subTotTot += aux;
				
				
				posRow++;
				
			}
			
			posRow++;
			
			row = hoja1.createRow(posRow);
			
			cell = row.createCell(posCell - 2);
		    cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TOTALES (sin ajuste)");
			
			
			cell = row.createCell(posCell);
		    cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			cell.setCellValue(subTotTot);
			
			
			Double totconajust = subTotTot;
			posRow++;
			
			for(List<String> lista: listaAjustes){
				posRow++;
				row = hoja1.createRow(posRow);
				
				
				cell = row.createCell(posCell - 2);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(5)+": "+lista.get(6));
				
			
		        cell = row.createCell(posCell);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				aux = Double.parseDouble(lista.get(4).replaceAll(",","").trim());
				cell.setCellValue(aux);
				totconajust += aux;
			}
			
			
			posRow++;
			posRow++;
			
			row = hoja1.createRow(posRow);

			
		    cell = row.createCell(posCell - 2);
		    cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("GRAN TOTAL");
			
	
			cell = row.createCell(posCell);
		    cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			cell.setCellValue(totconajust);
			
			
			
			
			
			
			
			
			// encabezado de la tabla DE DETALLE
			
			posRow = posRow + 2;
			posCell = 0;
			
			row = hoja1.createRow(posRow);
			posCell++; 
		    cell = row.createCell(posCell);
		    cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("DETALLE DE LOS SERVICIOS:");
			
			
			
			posRow++;
			posRow++;
			
			row = hoja1.createRow(posRow);
			posCell = 0;
			
			posCell++; 
			hoja1.setColumnWidth(posCell, 2*1000);
		    cell = row.createCell(posCell);
		    cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("ID");
			
			posCell++; 
			hoja1.setColumnWidth(posCell, 5*1000);
		    cell = row.createCell(posCell);
		    cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("CLASE");
			
			posCell++;
			hoja1.setColumnWidth(posCell, 5*1000);
		    cell = row.createCell(posCell);
		    cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("NRO.COTI");
			
			posCell++;
			hoja1.setColumnWidth(posCell, 5*1000);
		    cell = row.createCell(posCell);
		    cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("COD-SERVICIO");
			
			posCell++; 
			hoja1.setColumnWidth(posCell, 10*1000);
		    cell = row.createCell(posCell);
		    cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("SERVICIO");
			
			posCell++; 
			hoja1.setColumnWidth(posCell, 5*1000);
		    cell = row.createCell(posCell);
		    cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FECHA");
			
			posCell++; 
			hoja1.setColumnWidth(posCell, 10*1000);
		    cell = row.createCell(posCell);
		    cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("DETALLE");
			
			posCell++;
			hoja1.setColumnWidth(posCell, 5*1000);
		    cell = row.createCell(posCell);
		    cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("COD-EQUIPO");
			
			posCell++; 
			hoja1.setColumnWidth(posCell, 10*1000);
		    cell = row.createCell(posCell);
		    cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("EQUIPO");
			
			posCell++; 
			hoja1.setColumnWidth(posCell, 3*1000);
		    cell = row.createCell(posCell);
		    cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("HR-INI");
			
			posCell++; 
			hoja1.setColumnWidth(posCell, 3*1000);
		    cell = row.createCell(posCell);
		    cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("HR-TER");
			
			posCell++; 
			hoja1.setColumnWidth(posCell, 3*1000);
		    cell = row.createCell(posCell);
		    cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("UN");
			
			posCell++; 
			hoja1.setColumnWidth(posCell, 5*1000);
		    cell = row.createCell(posCell);
		    cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("LECT-INI");
			
			posCell++; 
			hoja1.setColumnWidth(posCell, 5*1000);
		    cell = row.createCell(posCell);
		    cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("LECT-TER");
			
			posCell++; 
			hoja1.setColumnWidth(posCell, 5*1000);
		    cell = row.createCell(posCell);
		    cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("CANT");
			
			posCell++; 
			hoja1.setColumnWidth(posCell, 3*1000);
		    cell = row.createCell(posCell);
		    cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("MON");
			
			posCell++; 
			hoja1.setColumnWidth(posCell, 5*1000);
		    cell = row.createCell(posCell);
		    cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("P.U.");
			
			posCell++; 
			hoja1.setColumnWidth(posCell, 5*1000);
		    cell = row.createCell(posCell);
		    cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("SUBTOTAL");
			
			posCell++; 
			hoja1.setColumnWidth(posCell, 5*1000);
		    cell = row.createCell(posCell);
		    cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TASA");
			
			posCell++; 
			hoja1.setColumnWidth(posCell, 5*1000);
		    cell = row.createCell(posCell);
		    cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TOTAL");
			
		
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
			
			
			
			//DETALLE DE LA TABLA EP DETALLADO

			Double subtotal = (double)0;
			
			posRow++;
			aux = (double)0;
			
			for(int i=0; i<detalleProformaPorServicio.size(); i++){
				row = hoja1.createRow(posRow);
				posCell = 0;
				
				posCell++; 
		        cell = row.createCell(posCell);
		        cell.setCellStyle(detalle);
		        aux = Double.parseDouble(detalleProformaPorServicio.get(i).get(6).replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(detalleProformaPorServicio.get(i).get(1));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(detalleProformaPorServicio.get(i).get(24));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(detalleProformaPorServicio.get(i).get(2));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(detalleProformaPorServicio.get(i).get(3));
				
				posCell++; 
		        cell = row.createCell(posCell);
		        cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(Fechas.DDMMAA(detalleProformaPorServicio.get(i).get(4)).replaceAll("-", "/"));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(detalleProformaPorServicio.get(i).get(5));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(detalleProformaPorServicio.get(i).get(9));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(detalleProformaPorServicio.get(i).get(10));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(detalleProformaPorServicio.get(i).get(7));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(detalleProformaPorServicio.get(i).get(8));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(detalleProformaPorServicio.get(i).get(11));
				
				posCell++; 
		        cell = row.createCell(posCell);
		        cell.setCellStyle(detalle);
		        aux = Double.parseDouble(detalleProformaPorServicio.get(i).get(12).replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);
				
				posCell++; 
		        cell = row.createCell(posCell);
		        cell.setCellStyle(detalle);
		        aux = Double.parseDouble(detalleProformaPorServicio.get(i).get(13).replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);
				
				posCell++; 
		        cell = row.createCell(posCell);
		        cell.setCellStyle(detalle);
		        aux = Double.parseDouble(detalleProformaPorServicio.get(i).get(14).replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(detalleProformaPorServicio.get(i).get(15));
				
				posCell++; 
		        cell = row.createCell(posCell);
		        cell.setCellStyle(detalle);
		        aux = Double.parseDouble(detalleProformaPorServicio.get(i).get(16).replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);
				
				posCell++; 
		        cell = row.createCell(posCell);
		        cell.setCellStyle(detalle);
		        aux = Double.parseDouble(detalleProformaPorServicio.get(i).get(17).replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);
				
				posCell++; 
		        cell = row.createCell(posCell);
		        cell.setCellStyle(detalle);
		        aux = Double.parseDouble(detalleProformaPorServicio.get(i).get(18).replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);
				
				posCell++; 
		        cell = row.createCell(posCell);
		        cell.setCellStyle(detalle);
		        aux = Double.parseDouble(detalleProformaPorServicio.get(i).get(19).replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);
				
				subtotal += aux;
				posRow++;
			}
			
//			posRow++;
			posRow++;
			
			row = hoja1.createRow(posRow);
			posCell = posCell - 2;
			
		    cell = row.createCell(posCell);
		    cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TOTALES (sin ajuste)");
			
			posCell = posCell + 2;
		    cell = row.createCell(posCell);
		    cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			cell.setCellValue(subtotal);
			
			
			posRow = posRow + 5;
			row = hoja1.createRow(posRow);
			cell = row.createCell(1);
			Hyperlink hiper = helper.createHyperlink(0);
			hiper.setAddress("https://www.inqsol.cl");
			cell.setHyperlink(hiper);
			cell.setCellType(Cell.CELL_TYPE_STRING);
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
	
	
	public static File exportaProformaExcelResumen(String db, Map<String,String> mapDiccionario, Fechas desde, Fechas hasta, List<List<String>> totalesPorProyecto, Map<String, List<List<String>>> mapAgrupado, Map<String, List<List<String>>> mapDetallado) {
		
		File tmp = TempFile.createTempFile("tmp","null");
		
		try {
			String path = "formatos/excel.xlsx";
			InputStream formato = Archivos.leerArchivo(path);
            Workbook libro = WorkbookFactory.create(formato);
            formato.close();
            
            // 0 negro 1 blanco 2 rojo 3 verde 4 azul 5 amarillo 19 celeste
            CellStyle titulo = libro.createCellStyle();
            Font font = libro.createFont();
            font.setBoldweight(Font.BOLDWEIGHT_BOLD);
            font.setColor((short)4);
            font.setFontHeight((short)(14*20));
            titulo.setFont(font);
            
            CellStyle subtitulo = libro.createCellStyle();
            Font font2 = libro.createFont();
            font2.setBoldweight(Font.BOLDWEIGHT_BOLD);
            font2.setColor((short)0);
            font2.setFontHeight((short)(12*20));
            subtitulo.setFont(font2);
            
            CellStyle encabezado = libro.createCellStyle();
            encabezado.setBorderBottom(CellStyle.BORDER_THIN);
            encabezado.setBorderTop(CellStyle.BORDER_THIN);
            encabezado.setBorderRight(CellStyle.BORDER_THIN);
            encabezado.setBorderLeft(CellStyle.BORDER_THIN);
            encabezado.setFillPattern(CellStyle.SOLID_FOREGROUND);
            encabezado.setFillForegroundColor((short)19);
            encabezado.setAlignment(CellStyle.ALIGN_LEFT);
            
            CellStyle encabezadoRight = libro.createCellStyle();
            encabezadoRight.setBorderBottom(CellStyle.BORDER_THIN);
            encabezadoRight.setBorderTop(CellStyle.BORDER_THIN);
            encabezadoRight.setBorderRight(CellStyle.BORDER_THIN);
            encabezadoRight.setBorderLeft(CellStyle.BORDER_THIN);
            encabezadoRight.setFillPattern(CellStyle.SOLID_FOREGROUND);
            encabezadoRight.setFillForegroundColor((short)19);
            encabezadoRight.setAlignment(CellStyle.ALIGN_RIGHT);
            
            CellStyle detalle = libro.createCellStyle();
            detalle.setBorderBottom(CellStyle.BORDER_THIN);
            detalle.setBorderTop(CellStyle.BORDER_THIN);
            detalle.setBorderRight(CellStyle.BORDER_THIN);
            detalle.setBorderLeft(CellStyle.BORDER_THIN);
            
            
            
            //titulos del archivo
            
            libro.setSheetName(0, "EP PROFORMA RESUMEN");
            Sheet hoja1 = libro.getSheetAt(0);
            
            Row row = null;
            Cell cell = null;
            
            row = hoja1.createRow(1);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("REPORTE ODO RESUMEN Y DETALLE POR TODOS LOS PROYECTOS (INCLUIDOS AJUSTES DE EP)");
			
			row = hoja1.createRow(2);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("EMPRESA: "+mapDiccionario.get("nEmpresa"));
			
			row = hoja1.createRow(3);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FECHA: "+Fechas.hoy().getFechaStrDDMMAA());
			
			row = hoja1.createRow(5);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("PERIODO: desde " + desde.getFechaStrDDMMAA()  + " hasta " + hasta.getFechaStrDDMMAA());
			
			
			//anchos de columnas
			for(int i=1; i<22; i++) {
				if(i<9) {
					hoja1.setColumnWidth(i, 6*1000);
				} else {
					hoja1.setColumnWidth(i, 3*1000);
				}
				
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
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("RESUMEN POR PROYECTOS  (INCLUYE AJUSTES A EP):");
			
			posRow += 2;
			posCell = 0;
			
			row = hoja1.createRow(posRow);
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("SUCURSAL");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("NOMBRE "+mapDiccionario.get("BODEGA")+"/PROYECTO");
		
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("NOMBRE CLIENTE");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("NOMBRE PROYECTO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("COMERCIAL");
		        
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("SUBTOTAL (en "+mapDiccionario.get("PESOS")+")");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("AJUSTES (en "+mapDiccionario.get("PESOS")+")");

			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TOTAL (en "+mapDiccionario.get("PESOS")+")");
				
		       
	        Double subtotal=(double)0;
	        Double ajuste=(double)0;
	        Double total=(double)0;
	        
	        // RESUMEN TOTALES PRO PROYECTO
			for(int i=0; i<totalesPorProyecto.size(); i++){
							
				posRow++;
				posCell = 0;
		        row = hoja1.createRow(posRow);
						
		        posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(totalesPorProyecto.get(i).get(10));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(totalesPorProyecto.get(i).get(1));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(totalesPorProyecto.get(i).get(2));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(totalesPorProyecto.get(i).get(3));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(totalesPorProyecto.get(i).get(4));
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            Double aux = Double.parseDouble(totalesPorProyecto.get(i).get(5).replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);
				subtotal += aux;
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(totalesPorProyecto.get(i).get(6).replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);
				ajuste += aux;
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(totalesPorProyecto.get(i).get(7).replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);
				total += aux;
			}
			
			posRow++;
			posCell = 0;
	        row = hoja1.createRow(posRow);
			
			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TOTAL");
			
			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezadoRight);
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			cell.setCellValue(subtotal);
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezadoRight);
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			cell.setCellValue(ajuste);
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezadoRight);
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			cell.setCellValue(total);
			
			
			//EP DETALLADO POR CADA PROYECTO
			
			posRow += 4;
			posCell = 0;
	        row = hoja1.createRow(posRow);
	        
	        posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("DETALLE POR PROYECTO (INCLUYE AJUSTES A EP):");
			
			posRow++;
			
			for(int i=0; i<totalesPorProyecto.size(); i++){
				
				posRow++;
				posCell = 0;
		        row = hoja1.createRow(posRow);
		        if(mapAgrupado.get(totalesPorProyecto.get(i).get(0))!=null && mapDetallado.get(totalesPorProyecto.get(i).get(0))!=null){
		        	
		        	posRow++;
					posCell = 0;
			        row = hoja1.createRow(posRow);
			        
		        	posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(encabezado);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(totalesPorProyecto.get(i).get(1));
			        
					for(int j=0;j<19;j++){
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(encabezado);
					}
					
					//AGRUPADO
					posRow++;
					posRow++;
					posCell = 0;
			        row = hoja1.createRow(posRow);
			        
			        posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("AGRUPADO POR SERVICIO:");
					
					posRow++;
					posCell = 0;
			        row = hoja1.createRow(posRow);
			        
			        posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("CLASE");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("NRO.COTI");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("COD-SERVICIO");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("SERVICIO");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("COD-EQUIPO");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("EQUIPO");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("UN");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("CANT");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("TOTAL");
					
					
					posCell++; 
					hoja1.setColumnWidth(posCell, 5*1000);
				    cell = row.createCell(posCell);
				    cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("CANT MIN");
					
					posCell++; 
					hoja1.setColumnWidth(posCell, 5*1000);
				    cell = row.createCell(posCell);
				    cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("TOTAL MINIMO");
					
					posCell++; 
					hoja1.setColumnWidth(posCell, 5*1000);
				    cell = row.createCell(posCell);
				    cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("TOTAL ADICIONAL");
					
					posCell++; 
					hoja1.setColumnWidth(posCell, 5*1000);
				    cell = row.createCell(posCell);
				    cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("GRAN TOTAL");
					
					
					
					Double totalcant=(double)0;
					Double totaltotal=(double)0;
					Double subCantMin = (double)0;
					Double subTotMin = (double)0;
					Double subTotAdic = (double)0;
					Double subTotTot = (double)0;
					
					
					for(List<String> lista: mapAgrupado.get(totalesPorProyecto.get(i).get(0))) {
						posRow++;
						posCell = 0;
				        row = hoja1.createRow(posRow);
				        
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(lista.get(0));
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(lista.get(12));
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(lista.get(1));
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(lista.get(2));
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(lista.get(3));
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(lista.get(4));
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(lista.get(5));
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						Double aux = Double.parseDouble(lista.get(6).replaceAll(",", ""));
						cell.setCellValue(aux);
						totalcant += aux;
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						aux = Double.parseDouble(lista.get(7).replaceAll(",", ""));
						cell.setCellValue(aux);
						totaltotal += aux;
						
						
						
						posCell++; 
				        cell = row.createCell(posCell);
				        aux = Double.parseDouble(lista.get(8).replaceAll(",", ""));
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(aux);
						subCantMin += aux;
						
						posCell++; 
				        cell = row.createCell(posCell);
				        aux = Double.parseDouble(lista.get(9).replaceAll(",", ""));
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(aux);
						subTotMin += aux;
						
						posCell++; 
				        cell = row.createCell(posCell);
				        aux = Double.parseDouble(lista.get(10).replaceAll(",", ""));
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(aux);
						subTotAdic += aux;
						
						posCell++; 
				        cell = row.createCell(posCell);
				        aux = Double.parseDouble(lista.get(11).replaceAll(",", ""));
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(aux);
						subTotTot += aux;
						
					}
					
					posRow++;
					posCell = 0;
			        row = hoja1.createRow(posRow);
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("TOTALES NETO (sin ajustes)");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("");
					
					posCell++; 
		            cell = row.createCell(posCell);
		            cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue("");
					
					posCell++; 
		            cell = row.createCell(posCell);
		            cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue(totalcant);
					
					posCell++; 
		            cell = row.createCell(posCell);
		            cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue(totaltotal);
					
					
					
					posCell++; 
		            cell = row.createCell(posCell);
		            cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue(subCantMin);
					
					posCell++; 
		            cell = row.createCell(posCell);
		            cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue(subTotMin);
					
					posCell++; 
		            cell = row.createCell(posCell);
		            cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue(subTotAdic);
					
					posCell++; 
		            cell = row.createCell(posCell);
		            cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue(subTotTot);
					
					
					//DETALLADO
					posRow++;
					posRow++;
					posCell = 0;
			        row = hoja1.createRow(posRow);
			        
			        posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("DETALLADO POR REPORT:");
					
					for(int j=0;j<7;j++){
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(subtitulo);
					}
					
					posRow++;
					posCell = 0;
			        row = hoja1.createRow(posRow);
			        
			        posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("ID");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("CLASE");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("NRO.COTI");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("COD-SERVICIO");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("SERVICIO");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("FECHA");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("DETALLE");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("COD-EQUIPO");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("EQUIPO");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("HR INI");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("HR TER");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("UN");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("LECT INI");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("LECT TER");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("CANT");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("MON");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("PU");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("SUBTOTAL");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("TASA");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("TOTAL");
					
					
					
					totaltotal=(double)0;
					for(List<String> lista: mapDetallado.get(totalesPorProyecto.get(i).get(0))) {
						posRow++;
						posCell = 0;
				        row = hoja1.createRow(posRow);
				        
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(lista.get(6));
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(lista.get(1));
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(lista.get(24));
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(lista.get(2));
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(lista.get(3));
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(lista.get(4));
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(lista.get(5));
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(lista.get(9));
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(lista.get(10));
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(lista.get(7));
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(lista.get(8));
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(lista.get(11));
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						Double aux = Double.parseDouble(lista.get(12).replaceAll(",", ""));
						cell.setCellValue(aux);
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						aux = Double.parseDouble(lista.get(13).replaceAll(",", ""));
						cell.setCellValue(aux);
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						aux = Double.parseDouble(lista.get(14).replaceAll(",", ""));
						cell.setCellValue(aux);
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(lista.get(15));
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						aux = Double.parseDouble(lista.get(16).replaceAll(",", ""));
						cell.setCellValue(aux);
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						aux = Double.parseDouble(lista.get(17).replaceAll(",", ""));
						cell.setCellValue(aux);
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						aux = Double.parseDouble(lista.get(18).replaceAll(",", ""));
						cell.setCellValue(aux);
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						aux = Double.parseDouble(lista.get(19).replaceAll(",", ""));
						cell.setCellValue(aux);
						
						totaltotal += aux;
					}
					
					
					posRow++;
					posCell = 0;
			        row = hoja1.createRow(posRow);
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("TOTALES NETO (sin ajustes)");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("");
					
					posCell++; 
		            cell = row.createCell(posCell);
		            cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue("");
					
					posCell++; 
		            cell = row.createCell(posCell);
		            cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue("");
					
					posCell++; 
		            cell = row.createCell(posCell);
		            cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue("");
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("");
					
					posCell++; 
		            cell = row.createCell(posCell);
		            cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue("");
					
					posCell++; 
		            cell = row.createCell(posCell);
		            cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue("");
					
					posCell++; 
		            cell = row.createCell(posCell);
		            cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue("");
					
					posCell++; 
		            cell = row.createCell(posCell);
		            cell.setCellStyle(subtitulo);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue(totaltotal);
					
					
					
		        	
		        }
			}
			
			
			posRow = posRow + 5;
			row = hoja1.createRow(posRow);
			cell = row.createCell(1);
			Hyperlink hiper = helper.createHyperlink(0);
			hiper.setAddress("https://www.inqsol.cl");
			cell.setHyperlink(hiper);
			cell.setCellType(Cell.CELL_TYPE_STRING);
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
	
	
	public static File exportaProformaExcelProyectos(String db, Map<String,String> mapDiccionario, List<List<String>> resumenTotalesPorProyecto, String desdeAAMMDD, String hastaAAMMDD,
			Double uf, Double usd, Double eur) {
		
		File tmp = TempFile.createTempFile("tmp","null");
		
		try {
			String path = "formatos/excel.xlsx";
			InputStream formato = Archivos.leerArchivo(path);
            Workbook libro = WorkbookFactory.create(formato);
            formato.close();
            
            // 0 negro 1 blanco 2 rojo 3 verde 4 azul 5 amarillo 19 celeste
            CellStyle titulo = libro.createCellStyle();
            Font font = libro.createFont();
            font.setBoldweight(Font.BOLDWEIGHT_BOLD);
            font.setColor((short)4);
            font.setFontHeight((short)(14*20));
            titulo.setFont(font);
            
            CellStyle subtitulo = libro.createCellStyle();
            Font font2 = libro.createFont();
            font2.setBoldweight(Font.BOLDWEIGHT_BOLD);
            font2.setColor((short)0);
            font2.setFontHeight((short)(12*20));
            subtitulo.setFont(font2);
            
            CellStyle encabezado = libro.createCellStyle();
            encabezado.setBorderBottom(CellStyle.BORDER_THIN);
            encabezado.setBorderTop(CellStyle.BORDER_THIN);
            encabezado.setBorderRight(CellStyle.BORDER_THIN);
            encabezado.setBorderLeft(CellStyle.BORDER_THIN);
            encabezado.setFillPattern(CellStyle.SOLID_FOREGROUND);
            encabezado.setFillForegroundColor((short)19);
            encabezado.setAlignment(CellStyle.ALIGN_LEFT);
            
            CellStyle detalle = libro.createCellStyle();
            detalle.setBorderBottom(CellStyle.BORDER_THIN);
            detalle.setBorderTop(CellStyle.BORDER_THIN);
            detalle.setBorderRight(CellStyle.BORDER_THIN);
            detalle.setBorderLeft(CellStyle.BORDER_THIN);
            
            CellStyle pie = libro.createCellStyle();
            pie.setBorderBottom(CellStyle.BORDER_THIN);
            pie.setBorderTop(CellStyle.BORDER_THIN);
            pie.setBorderRight(CellStyle.BORDER_THIN);
            pie.setBorderLeft(CellStyle.BORDER_THIN);
            pie.setFillPattern(CellStyle.SOLID_FOREGROUND);
            pie.setFillForegroundColor((short)19);
            pie.setAlignment(CellStyle.ALIGN_RIGHT);
            
            
            
            //titulos del archivo
            
            libro.setSheetName(0, "PROCESO DE EP-FACTURACION LISTADO ODO");
            Sheet hoja1 = libro.getSheetAt(0);
            
            Row row = null;
            Cell cell = null;
            
            row = hoja1.createRow(1);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("LISTADO DE EP ODO PROYECTOS (INCLUIDOS AJUSTES DE EP ODO)");
			
			row = hoja1.createRow(2);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("EMPRESA: "+mapDiccionario.get("nEmpresa"));
			
			row = hoja1.createRow(3);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FECHA: "+Fechas.hoy().getFechaStrDDMMAA());
			
			row = hoja1.createRow(5);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("PERIODO: desde " + Fechas.DDMMAA(desdeAAMMDD)  + " hasta " + Fechas.DDMMAA(hastaAAMMDD));
			
			
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
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("RESUMEN POR PROYECTOS  (INCLUYE AJUSTES A EP):");
			
			posRow += 2;
			posCell = 0;
			
			row = hoja1.createRow(posRow);
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("SUCURSAL");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("NOMBRE "+mapDiccionario.get("BODEGA")+"/PROYECTO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("NOMBRE CLIENTE");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("NOMBRE PROYECTO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("COMERCIAL");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("SUBTOTAL (en "+mapDiccionario.get("PESOS")+")");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("AJUSTES (en "+mapDiccionario.get("PESOS")+")");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TOTAL (en "+mapDiccionario.get("PESOS")+")");
				
		       
	        Double subtotal = (double)0;
	        Double ajustes = (double)0;
	        Double total = (double)0;
	        
			for(List<String> proyectos: resumenTotalesPorProyecto){
				posRow++;
				posCell = 0;
		        row = hoja1.createRow(posRow);
				
		        posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(proyectos.get(10));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(proyectos.get(1));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(proyectos.get(2));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(proyectos.get(3));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(proyectos.get(4));
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            Double aux = Double.parseDouble(proyectos.get(5).replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);
				subtotal += aux;
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(proyectos.get(6).replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);
				ajustes += aux;
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(proyectos.get(7).replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);
				total += aux;
			}
			
			posRow++;
			posCell = 0;
	        row = hoja1.createRow(posRow);
			
			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TOTALES");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(pie);
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			cell.setCellValue(subtotal);
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(pie);
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			cell.setCellValue(ajustes);
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(pie);
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			cell.setCellValue(total);
			
			
			posRow = posRow + 5;
			row = hoja1.createRow(posRow);
			cell = row.createCell(1);
			Hyperlink hiper = helper.createHyperlink(0);
			hiper.setAddress("https://www.inqsol.cl");
			cell.setHyperlink(hiper);
			cell.setCellType(Cell.CELL_TYPE_STRING);
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
	
	
	public static File exportaExcelOdoListarVentas1(String db, Map<String,String> mapDiccionario, List<VentaServicio> listVentas, 
			String desdeAAMMDD, String hastaAAMMDD) {
		
		File tmp = TempFile.createTempFile("tmp","null");
		
		try {
			String path = "formatos/excel.xlsx";
			InputStream formato = Archivos.leerArchivo(path);
            Workbook libro = WorkbookFactory.create(formato);
            formato.close();
            
            // 0 negro 1 blanco 2 rojo 3 verde 4 azul 5 amarillo 19 celeste
            CellStyle titulo = libro.createCellStyle();
            Font font = libro.createFont();
            font.setBoldweight(Font.BOLDWEIGHT_BOLD);
            font.setColor((short)4);
            font.setFontHeight((short)(14*20));
            titulo.setFont(font);
            
            CellStyle subtitulo = libro.createCellStyle();
            Font font2 = libro.createFont();
            font2.setBoldweight(Font.BOLDWEIGHT_BOLD);
            font2.setColor((short)0);
            font2.setFontHeight((short)(12*20));
            subtitulo.setFont(font2);
            
            CellStyle encabezado = libro.createCellStyle();
            encabezado.setBorderBottom(CellStyle.BORDER_THIN);
            encabezado.setBorderTop(CellStyle.BORDER_THIN);
            encabezado.setBorderRight(CellStyle.BORDER_THIN);
            encabezado.setBorderLeft(CellStyle.BORDER_THIN);
            encabezado.setFillPattern(CellStyle.SOLID_FOREGROUND);
            encabezado.setFillForegroundColor((short)19);
            encabezado.setAlignment(CellStyle.ALIGN_LEFT);
            
            CellStyle detalle = libro.createCellStyle();
            detalle.setBorderBottom(CellStyle.BORDER_THIN);
            detalle.setBorderTop(CellStyle.BORDER_THIN);
            detalle.setBorderRight(CellStyle.BORDER_THIN);
            detalle.setBorderLeft(CellStyle.BORDER_THIN);
            
            CellStyle pie = libro.createCellStyle();
            pie.setBorderBottom(CellStyle.BORDER_THIN);
            pie.setBorderTop(CellStyle.BORDER_THIN);
            pie.setBorderRight(CellStyle.BORDER_THIN);
            pie.setBorderLeft(CellStyle.BORDER_THIN);
            pie.setFillPattern(CellStyle.SOLID_FOREGROUND);
            pie.setFillForegroundColor((short)19);
            pie.setAlignment(CellStyle.ALIGN_RIGHT);
            
            
            
            CreationHelper creationHelper = libro.getCreationHelper();
            CellStyle hora = libro.createCellStyle();
            hora.setDataFormat(creationHelper.createDataFormat().getFormat("hh:mm"));
            hora.setBorderBottom(CellStyle.BORDER_THIN);
            hora.setBorderTop(CellStyle.BORDER_THIN);
            hora.setBorderRight(CellStyle.BORDER_THIN);
            hora.setBorderLeft(CellStyle.BORDER_THIN);
            
            CellStyle fecha = libro.createCellStyle();
            fecha.setDataFormat(creationHelper.createDataFormat().getFormat("dd/MM/yyyy"));
            fecha.setBorderBottom(CellStyle.BORDER_THIN);
            fecha.setBorderTop(CellStyle.BORDER_THIN);
            fecha.setBorderRight(CellStyle.BORDER_THIN);
            fecha.setBorderLeft(CellStyle.BORDER_THIN);


            
            
            //titulos del archivo
            
            libro.setSheetName(0, "report");
            Sheet hoja1 = libro.getSheetAt(0);
            
            Row row = null;
            Cell cell = null;
            
            row = hoja1.createRow(1);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("LISTA DE REPORT DIARIO");
			
			row = hoja1.createRow(2);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("EMPRESA: "+mapDiccionario.get("nEmpresa"));
			
			row = hoja1.createRow(3);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FECHA: "+Fechas.hoy().getFechaStrDDMMAA());
			
			row = hoja1.createRow(5);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("PERIODO: desde " + Fechas.DDMMAA(desdeAAMMDD)  + " hasta " + Fechas.DDMMAA(hastaAAMMDD));
			
			
			//anchos de columnas
			for(int i=1; i<30; i++) {
				if(i==1||i==7||i==11||i==12||i==13||i==14||i==15||i==16) {
					hoja1.setColumnWidth(i, 2*1000);
				}else if(i==2|| i==17||i==18) {
					hoja1.setColumnWidth(i, 3*1000);
				}else {
					hoja1.setColumnWidth(i, 6*1000);
				}
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
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("ID");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FECHA");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("USER_MADA");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("OPERADOR");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("SUCURSAL");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(mapDiccionario.get("BODEGA"));
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("COTI");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("CODIGO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("SERVICIO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("EQUIPO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Hr_INI");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Hr_TER");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Lect_INI");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Lect_TER");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("UN");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("CANTIDAD");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("OPERADOR");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("AUTORIZADOR");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("DETALLE");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("NOTAS");
			
			
			for(VentaServicio venta: listVentas){
				posRow++;
				posCell = 0;
		        row = hoja1.createRow(posRow);
				
		        posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(venta.getId());
				
				posCell++;
				cell = row.createCell(posCell);
				Fechas fechax = Fechas.obtenerFechaDesdeStrAAMMDD(Fechas.AAMMDD(venta.getFecha()));
				cell.setCellValue(fechax.fechaUtil);
				cell.setCellStyle(fecha);
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(venta.getNomUserAdam());
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(venta.getNomOperador());
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(venta.getNameSucursal());
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(venta.getNomBodegaEmpresa());
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(venta.getNroCotiOdo());
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(venta.getCodServicio());
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(venta.getNomServicio());
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(venta.getNomEquipo());
				
				posCell++;
				cell = row.createCell(posCell);
				java.util.Date horaIni = new SimpleDateFormat("HH:mm").parse(venta.getHoraIni());
				cell.setCellValue(horaIni);
				cell.setCellStyle(hora);
				
				
				posCell++;
				cell = row.createCell(posCell);
				java.util.Date horaTer = new SimpleDateFormat("HH:mm").parse(venta.getHoraTer());
				cell.setCellValue(horaTer);
				cell.setCellStyle(hora);
				
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            Double aux = Double.parseDouble(venta.getLecturaIni().replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(venta.getLecturaTer().replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(venta.getUnidadServicio());
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(venta.getCantidad().replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);
			
				posCell++; 
				cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				byte[] firmaOperador = Base64.getDecoder().decode(venta.getFirmaPDFoperador());
				int indexfo = libro.addPicture(firmaOperador, Workbook.PICTURE_TYPE_PNG);
				Drawing drawfo = hoja1.createDrawingPatriarch();
				CreationHelper helperfo = libro.getCreationHelper();
				ClientAnchor anchorfo = helperfo.createClientAnchor();
				anchorfo.setCol1(posCell);
				anchorfo.setRow1(posRow);
				anchorfo.setCol2(posCell+11);
				anchorfo.setRow2(posRow+1);
				Picture imgfo = drawfo.createPicture(anchorfo, indexfo);
				imgfo.resize(0.2);
				
				posCell++; 
				cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				byte[] firmaAurorizador = Base64.getDecoder().decode(venta.getFirmaPDFautorizador());
				int indexfa = libro.addPicture(firmaAurorizador, Workbook.PICTURE_TYPE_PNG);
				Drawing drawfa = hoja1.createDrawingPatriarch();
				CreationHelper helperfa = libro.getCreationHelper();
				ClientAnchor anchorfa = helperfa.createClientAnchor();
				anchorfa.setCol1(posCell);
				anchorfa.setRow1(posRow);
				anchorfa.setCol2(posCell+11);
				anchorfa.setRow2(posRow+1);
				Picture imgfa = drawfa.createPicture(anchorfa, indexfa);
				imgfa.resize(0.2);
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(venta.getDetalle());
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(venta.getObservaciones());
				
			
			}
			
			
			
			posRow = posRow + 5;
			row = hoja1.createRow(posRow);
			cell = row.createCell(1);
			Hyperlink hiper = helper.createHyperlink(0);
			hiper.setAddress("https://www.inqsol.cl");
			cell.setHyperlink(hiper);
			cell.setCellType(Cell.CELL_TYPE_STRING);
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
	
	
	
