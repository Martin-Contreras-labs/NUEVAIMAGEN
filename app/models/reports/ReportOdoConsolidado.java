package models.reports;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
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

import models.calculo.Calc_AjustesEpOdo;
import models.tables.BodegaEmpresa;
import models.tables.Equipo;
import models.tables.ListaPrecioServicio;
import models.tables.Moneda;
import models.tables.TasasCambio;
import models.tables.VentaServicio;
import models.utilities.Archivos;
import models.utilities.Fechas;

public class ReportOdoConsolidado {
	static DecimalFormat myformatint = new DecimalFormat("#,##0");
	
	
	
	
	public static List<List<String>> reportOdoConsolidadoRtp (Connection con, String db, Fechas fecha, Long meses, String permisoPorBodega, String pais, String esPorSucursal, String id_sucursal) {
		
		List<List<String>> listaFechas = new ArrayList<List<String>>();
    	for(int i=0;i<meses;i++) {
    		List<String> aux = new ArrayList<String>();
    		fecha = Fechas.obtenerFinMes(fecha.getFechaCal());
	    	aux.add(fecha.getFechaStrAAMMDD());
	    	fecha = Fechas.obtenerInicioMes(fecha.getFechaCal());
	    	aux.add(fecha.getFechaStrAAMMDD());
	    	listaFechas.add(aux);
	    	fecha = Fechas.addDias(fecha.getFechaCal(), -1);
    	}
    	
    	
    	
		Map<Long,Long> mapDec = Moneda.numeroDecimal(con, db);
		
		
		
		Map<String,TasasCambio> mapAllTasas = TasasCambio.mapTasasporAllFecha(con, db, pais);
		
		Map<String,String> mapNomBod_MMAAconTotal = new HashMap<String,String>();
		Map<String,List<String>> mapNomBodNomSuc = new HashMap<String,List<String>>();
		
		Map<Long,BodegaEmpresa> mapBodegas = BodegaEmpresa.mapAll(con, db);
		Map<String,ListaPrecioServicio> mapPreciosOdo = ListaPrecioServicio.mapAllListaPrecioServicio(con, db);
		
		
		Long id_grupo = (long)0; // 0 es todos los grupos de equipos
		Map<Long,Long> mapIdEquipoVsIdGrupo = Equipo.mapIdEquipoVsIdGrupo(con, db);
		Map<String, Double> mapFijaTasas = BodegaEmpresa.mapFijaTasasAll(con, db);
    	
		for(int i=0;i<listaFechas.size();i++) {
    		
    		String desdeAAMMDD = listaFechas.get(i).get(1);
    		String hastaAAMMDD = listaFechas.get(i).get(0);
    		
    		TasasCambio tasasCambio = mapAllTasas.get(hastaAAMMDD);
    		Map<Long,Double> tasas = new HashMap<Long,Double>();
    		if(tasasCambio==null) {
    			tasas.put((long)2,(double)1);	// el id 2 es usd
				tasas.put((long)3,(double)1);	// el id 3 es eur
				tasas.put((long)4,(double)1);	// el id 4 es uf
    		}else {
    			tasas.put((long)2,Double.parseDouble(tasasCambio.getClpUsd().replaceAll(",", "")));	// el id 2 es usd
				tasas.put((long)3,Double.parseDouble(tasasCambio.getClpEur().replaceAll(",", "")));	// el id 3 es eur
				tasas.put((long)4,Double.parseDouble(tasasCambio.getClpUf().replaceAll(",", "")));	// el id 4 es uf
    		}
    		
    		List<VentaServicio> listVentaServicio = VentaServicio.allEntreFechas(con, db, desdeAAMMDD, hastaAAMMDD, esPorSucursal, id_sucursal);
    		Map<Long,Double> mapTotalAjustePorBodega = Calc_AjustesEpOdo.mapTotalAjustePorBodega(con, db, desdeAAMMDD, hastaAAMMDD, esPorSucursal, id_sucursal);
			List<List<String>> resumenTotalesPorProyecto = ReportOdo.resumenTotalesPorProyecto(con, db, listVentaServicio, mapFijaTasas, tasas, mapDec, mapTotalAjustePorBodega, mapBodegas, mapPreciosOdo, id_grupo, mapIdEquipoVsIdGrupo);
			
			
			
			
			String[] auxFech = listaFechas.get(i).get(1).split("-"); 
			String MMAA = auxFech[1]+"/"+auxFech[0];
			
			for(List<String> lista: resumenTotalesPorProyecto) {
				String key = lista.get(1)+"_"+MMAA;							//1 nomBodega
				String total =  lista.get(7);   							//7 precioDespuesDeAjuste
				mapNomBod_MMAAconTotal.put(key, total);
				List<String> aux = new ArrayList<String>();
				aux.add(lista.get(10)); // nom sucursal
				aux.add(lista.get(1));  // nom bodega
				mapNomBodNomSuc.put(lista.get(1), aux);
			}
			
    	}
    	
		
    	List<List<String>> datos = new ArrayList<List<String>>();
    	
    	List<String> titulos = new ArrayList<String>();
    	titulos.add("SUCURSAL");
    	titulos.add("BODEGA/PROYECTO");
    	for(int i = listaFechas.size()-1; i >= 0; i--) {
    		String[] auxFech = listaFechas.get(i).get(1).split("-");
    		String MMAA = auxFech[1]+"/"+auxFech[0];
    		titulos.add(MMAA);
    	}
    	titulos.add("TOTALES");
    	datos.add(titulos);
    	
    	for (List<String> x : mapNomBodNomSuc.values()) {
    		List<String> auxDato = new ArrayList<String>();
    		auxDato.add(x.get(0));
    		auxDato.add(x.get(1)); 
    		Double granTotal = (double) 0;
    		//for(int i = titulos.size()-2; i >= 1; i--) {
    		for(int i = 2; i < titulos.size()-1; i++) {
    			Double totalNum = (double)0;
    			String totalStr = mapNomBod_MMAAconTotal.get(x.get(1)+"_"+titulos.get(i));
    			if(totalStr!=null) {
    				totalNum = Double.parseDouble(totalStr.replaceAll(",", ""));
    			}else {
    				totalStr = "";
    			}
    			auxDato.add(totalStr);
    			granTotal += totalNum;
    		}
    		auxDato.add(myformatint.format(granTotal));
    		datos.add(auxDato);
    	}
    		
    	
    	//ORDENA LA LISTA
		for(int j=1;j<datos.size();j++) {
            for(int i=1;i<datos.size()-j;i++) {
                if (i+1!=datos.size() && datos.get(i).get(1).compareToIgnoreCase(datos.get(i+1).get(1))>0) {
                    List<String> aux;
                    aux=datos.get(i);
                    datos.set(i,datos.get(i+1));
                    datos.set(i+1, aux);
                }
            }
        }
    	
		List<String> totales = new ArrayList<String>();
		totales.add("<div style='display:none'>ZZZZZZZZZZZZZZZZZZZZZZZZ</div>TOTALES");
		totales.add("");
		for(int i=2; i < datos.get(0).size(); i++) {
			Double subTotal = (double) 0;
			for(int j=1; j<datos.size(); j++) {
				if(!datos.get(j).get(i).trim().equals("")) {
					subTotal += Double.parseDouble(datos.get(j).get(i).replaceAll(",", ""));
				}
			}
			totales.add(myformatint.format(subTotal));
		}
    	datos.add(totales);
    	
		return (datos);
	}
	
	
	
	
	
	
	public static File reportOdoConsolidadoRtpExcel(String db, Map<String,String> mapDiccionario, List<List<String>> datos) {
		
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
            
            
            Sheet hoja1 = libro.getSheetAt(0);
            Row row = null;
            Cell cell = null;
            
            row = hoja1.createRow(1);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("REPORTE ODO CONSOLIDADO DE PROFORMAS POR MESES");
			
			row = hoja1.createRow(3);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("EMPRESA: "+mapDiccionario.get("nEmpresa"));
			
			row = hoja1.createRow(4);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FECHA: "+Fechas.hoy().getFechaStrDDMMAA());
            
			// anchos de columnas
			
			hoja1.setColumnWidth(1, 10*1000);
			for(int i=2; i<datos.get(0).size()+1; i++){
				hoja1.setColumnWidth(i, 4*1000);
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
	        anchor.setCol1(6);
	        anchor.setRow1(1);
			Picture img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);
            
		 
			// encabezado de la tabla
			
			int posRow = 7;
			int posCell = 0;
			row = hoja1.createRow(posRow);
			
            for(int i=0; i<datos.get(0).size(); i++){
            	posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(datos.get(0).get(i));
            }
            
            // detalle de la tabla
            
            for(int i=1; i<datos.size(); i++){
            	
            	posRow++;
                posCell = 0;
     			row = hoja1.createRow(posRow);
     			
            	posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				
				if(datos.get(i).get(0).substring(0,1).equals("<")) {
					cell.setCellValue("TOTALES");
				}else {
					cell.setCellValue(datos.get(i).get(0));
				}
				
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(datos.get(i).get(1));
            	
				for(int k=2; k<datos.get(i).size(); k++){
					posCell++; 
		            cell = row.createCell(posCell);
		            cell.setCellStyle(detalle);
		            String auxStr = datos.get(i).get(k).replaceAll(",", "").trim();
		            if(auxStr.equals("")) {
		            	auxStr = "0";
		            }
		            Double aux = Double.parseDouble(auxStr);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue(aux);
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
	
	
	
	
}
	
	
	
