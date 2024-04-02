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

import models.calculo.Calc_AjustesEP;
import models.calculo.Calc_BodegaEmpresa;
import models.calculo.Calc_Precio;
import models.calculo.Inventarios;
import models.calculo.ModCalc_GuiasPer;
import models.calculo.ModCalc_InvInicial;
import models.calculo.ModeloCalculo;
import models.tables.BodegaEmpresa;
import models.tables.Equipo;
import models.tables.Grupo;
import models.tables.TasasCambio;
import models.utilities.Archivos;
import models.utilities.Fechas;

public class ReportFacturaConsolidado {
	static DecimalFormat myformatint = new DecimalFormat("#,##0");
	
	
	
	
	public static List<List<String>> reportFacturaConsolidadoRtp (Connection con, String db, Fechas fecha, Long meses, String permisoPorBodega, String pais, String esPorSucursal, String id_sucursal) {
		
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
    	
    	Map<String,List<ModeloCalculo>> mapTotales = new HashMap<String,List<ModeloCalculo>>();
    	Map<Long,Long> mapIdBodegas = new HashMap<Long,Long>();
    	Map<Long,List<String>> bodegas = BodegaEmpresa.mapIdBod_BodegaEmpresaExternas(con, db, esPorSucursal, id_sucursal);
    	
    	Map<String,TasasCambio> mapAllTasas = TasasCambio.mapTasasporAllFecha(con, db, pais);
    	
    	
    	
    	List<Long> listIdBodegaEmpresa = ModCalc_InvInicial.listIdBodegaEmpresa(con, db, permisoPorBodega);
		Map<Long,Calc_BodegaEmpresa> mapBodegaEmpresa = Calc_BodegaEmpresa.mapAllBodegasVigentes(con, db);
		Map<String,Calc_Precio> mapPrecios = Calc_Precio.mapPrecios(con, db, listIdBodegaEmpresa);
		Map<Long,Calc_Precio> mapMaestroPrecios = Calc_Precio.mapMaestroPrecios(con, db);
		Map<String, Double> mapFijaTasas = BodegaEmpresa.mapFijaTasasAll(con, db);
		
		Map<String,String> mapPermanencias = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, db);
    	
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
    		

			
			List<Long> listIdGuia_fechaCorte = ModCalc_InvInicial.listIdGuia_fechaCorte(con, db, desdeAAMMDD);
			List<Inventarios> inventarioAux = Inventarios.inventario(con, db, listIdBodegaEmpresa, listIdGuia_fechaCorte);
			List<Long> listIdGuia_entreFechas = ModCalc_GuiasPer.listIdGuia_entreFecha(con, db, desdeAAMMDD, hastaAAMMDD);
			List<Inventarios> guiasPerAux = Inventarios.guiasPer(con, db, listIdBodegaEmpresa, listIdGuia_entreFechas);
			List<Calc_AjustesEP> listaAjustes = Calc_AjustesEP.listaAjustesEntreFechas(con, db, desdeAAMMDD, hastaAAMMDD);

			List<ModCalc_InvInicial> inventarioInicial = ModCalc_InvInicial.resumenInvInicial(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa, 
					mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, listIdGuia_fechaCorte, inventarioAux);
			
			List<ModCalc_GuiasPer> guiasPeriodo = ModCalc_GuiasPer.resumenGuiasPer(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa, 
					mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, listIdGuia_entreFechas, guiasPerAux, mapPermanencias);
			
			List<ModeloCalculo> calculo = ModeloCalculo.valorTotalporBodega(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, inventarioInicial,guiasPeriodo, listaAjustes);
			

    		
    		mapTotales.put(desdeAAMMDD, calculo);
    		for(int k=0;k<calculo.size();k++) {
    			Long id_bodegaEmpresa = calculo.get(k).id_bodegaEmpresa;
    			mapIdBodegas.put(id_bodegaEmpresa, id_bodegaEmpresa);
    		}
    	}
		
    	List<List<String>> datos = new ArrayList<List<String>>();
    	
    	List<String> titulos = new ArrayList<String>();
    	titulos.add("SUCURSAL");
    	titulos.add("BODEGA/PROYECTO");
    	for(int i = listaFechas.size()-1; i >= 0; i--) {
    		String[] auxFech = listaFechas.get(i).get(1).split("-");
    		titulos.add(auxFech[1]+"/"+auxFech[0]);
    		//agregar subtitulos ARR+CFI - VTA - TOTAL
    	}
    	titulos.add("TOTALES");
    	datos.add(titulos);
    	
    	for (Long value : mapIdBodegas.values()) {
    		List<String> auxDato = new ArrayList<String>();
    		List<String> bod = bodegas.get(value);
    		if(bod!=null) {
    			auxDato.add(bod.get(14));
    			auxDato.add(bod.get(5));
    			Double granTotal = (double) 0;
    			
    			for(int i = listaFechas.size()-1; i >= 0; i--) {
    				String desdeAAMMDD = listaFechas.get(i).get(1);
    				List<ModeloCalculo> calculo = mapTotales.get(desdeAAMMDD);
    				
    				Double totAux = (double) 0;
    				if(calculo!=null) {
    					for(int k=0;k<calculo.size();k++) {
    						if(calculo.get(k).id_bodegaEmpresa-value==0) {
    							totAux = calculo.get(k).totalTotal;
    						}
    					}
    				}
    				granTotal += totAux;
    				auxDato.add(myformatint.format(totAux));
    			}
    			auxDato.add(myformatint.format(granTotal));
    			datos.add(auxDato);
    		}
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
    	
    	Double granTotal = (double) 0;
    	for(int i = listaFechas.size()-1; i >= 0; i--) {
    		String desdeAAMMDD = listaFechas.get(i).get(1);
			List<ModeloCalculo> calculo = mapTotales.get(desdeAAMMDD);
			Double total = (double) 0;
			if(calculo!=null) {
				for(int k=0;k<calculo.size();k++) {
					total += calculo.get(k).totalTotal;
				}
			}
			granTotal += total;
			totales.add(myformatint.format(total));
    	}
    	totales.add(myformatint.format(granTotal));
    	datos.add(totales);
    	
		return (datos);
	}
	
	public static File reportFacturaConsolidadoRtpExcel(String db, Map<String,String> mapDiccionario, List<List<String>> datos) {
		
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
			cell.setCellValue("REPORTE CONSOLIDADO DE PROFORMAS POR MESES");
			
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
				cell.setCellValue(datos.get(i).get(0).replace("<div style='display:none'>ZZZZZZZZZZZZZZZZZZZZZZZZ</div>", ""));
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(datos.get(i).get(1));
            	
				for(int k=2; k<datos.get(i).size(); k++){
					posCell++; 
		            cell = row.createCell(posCell);
		            cell.setCellStyle(detalle);
		            Double aux = Double.parseDouble(datos.get(i).get(k).replaceAll(",", ""));
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
	
	public static List<List<String>> reportConsDetalladoRtp (Connection con, String db, Fechas fecha, Long meses, String permisoPorBodega, String pais, String esPorSucursal, String id_sucursal) {
			
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
	    	
	    	Map<String,List<ModeloCalculo>> mapTotales = new HashMap<String,List<ModeloCalculo>>();
	    	Map<Long,List<String>> bodegas = BodegaEmpresa.mapIdBod_BodegaEmpresaExternas(con, db, esPorSucursal, id_sucursal);
	    	Map<Long,Grupo> grupos = Grupo.mapAll(con, db);
	    	Map<String,String> mapIdBodGrupo = new HashMap<String,String>();
	    	
	    	Map<String,TasasCambio> mapAllTasas = TasasCambio.mapTasasporAllFecha(con, db, pais);
	    	
	    	
	    	
	    	List<Long> listIdBodegaEmpresa = ModCalc_InvInicial.listIdBodegaEmpresa(con, db, permisoPorBodega);
			Map<Long,Calc_BodegaEmpresa> mapBodegaEmpresa = Calc_BodegaEmpresa.mapAllBodegasVigentes(con, db);
			Map<String,Calc_Precio> mapPrecios = Calc_Precio.mapPrecios(con, db, listIdBodegaEmpresa);
			Map<Long,Calc_Precio> mapMaestroPrecios = Calc_Precio.mapMaestroPrecios(con, db);
			Map<String, Double> mapFijaTasas = BodegaEmpresa.mapFijaTasasAll(con, db);
			
			Map<String,String> mapPermanencias = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, db);
			
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
	    		
	    		List<Long> listIdGuia_fechaCorte = ModCalc_InvInicial.listIdGuia_fechaCorte(con, db, desdeAAMMDD);
				List<Inventarios> inventarioAux = Inventarios.inventario(con, db, listIdBodegaEmpresa, listIdGuia_fechaCorte);
				List<Long> listIdGuia_entreFechas = ModCalc_GuiasPer.listIdGuia_entreFecha(con, db, desdeAAMMDD, hastaAAMMDD);
				List<Inventarios> guiasPerAux = Inventarios.guiasPer(con, db, listIdBodegaEmpresa, listIdGuia_entreFechas);
				List<Calc_AjustesEP> listaAjustes = Calc_AjustesEP.listaAjustesEntreFechas(con, db, desdeAAMMDD, hastaAAMMDD);
		
				List<ModCalc_InvInicial> inventarioInicial = ModCalc_InvInicial.resumenInvInicial(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa, 
						mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, listIdGuia_fechaCorte, inventarioAux);
				
				List<ModCalc_GuiasPer> guiasPeriodo = ModCalc_GuiasPer.resumenGuiasPer(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa, 
						mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, listIdGuia_entreFechas, guiasPerAux, mapPermanencias);
				
				List<ModeloCalculo> calculo = ModeloCalculo.valorTotalporBodega(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, inventarioInicial,guiasPeriodo, listaAjustes);
				
	    		
	    		mapTotales.put(desdeAAMMDD, calculo);
	    		for(int k=0;k<calculo.size();k++) {
	    			String idBodega = calculo.get(k).id_bodegaEmpresa.toString();
	    			String idGrupo = calculo.get(k).id_grupo.toString();
	    			mapIdBodGrupo.put(idBodega+"_"+idGrupo, idBodega+"_"+idGrupo);
	    		}
	    	}
	    	
	    	List<List<String>> datos = new ArrayList<List<String>>();
	    	
	    	List<String> titulos = new ArrayList<String>();
	    	titulos.add("BODEGA/CLIENTE");
	    	titulos.add("GRUPO");
	    	for(int i = listaFechas.size()-1; i >= 0; i--) {
	    		String[] auxFech = listaFechas.get(i).get(1).split("-");
	    		titulos.add(auxFech[1]+"/"+auxFech[0]);
	    		titulos.add(auxFech[1]+"/"+auxFech[0]);
	    		titulos.add(auxFech[1]+"/"+auxFech[0]);
	    	}
	    	titulos.add("ALQUILER");
	    	titulos.add("VENTA");
	    	titulos.add("TOTAL");
	    	datos.add(titulos);
	    	
	    	titulos = new ArrayList<String>();
	    	titulos.add("");
	    	titulos.add("");
	    	for(int i = listaFechas.size()-1; i >= 0; i--) {
	    		titulos.add("Alquiler");
	    		titulos.add("Venta");
	    		titulos.add("Total");
	    	}
	    	titulos.add("");
	    	titulos.add("");
	    	titulos.add("");
	    	datos.add(titulos);
	    	
	    	
	    	for (String value : mapIdBodGrupo.values()) {
	    		List<String> auxDato = new ArrayList<String>();
	    		
	    		String[] arrIdBodIdGrupo = value.split("_");
	    		List<String> bod = bodegas.get(Long.parseLong(arrIdBodIdGrupo[0]));
	    		Grupo grupo = grupos.get(Long.parseLong(arrIdBodIdGrupo[1]));
	    		
	    		if(bod!=null && grupo!=null) {
	    			auxDato.add(bod.get(5));
	    			auxDato.add(grupo.nombre);
	    			Double granAlq = (double) 0;
	    			Double granVta = (double) 0;
	    			Double granTotal = (double) 0;
	    			for(int i = listaFechas.size()-1; i >= 0; i--) {
	    				String desdeAAMMDD = listaFechas.get(i).get(1);
	    				List<ModeloCalculo> calculo = mapTotales.get(desdeAAMMDD);
	    				Double totAlq = (double) 0;
	    				Double totVta = (double) 0;
	    				Double totAux = (double) 0;
	    				if(calculo!=null) {
	    					for(int k=0;k<calculo.size();k++) {
	    						String auxIdBod = calculo.get(k).id_bodegaEmpresa.toString();
	    						String auxIdGrupo = calculo.get(k).id_grupo.toString();
	    						String idBod_idGrupo = auxIdBod + "_" + auxIdGrupo;
	    						if(idBod_idGrupo.equals(value)) {
	    							totAlq = calculo.get(k).totalArriendo + calculo.get(k).totalCfi;
	    							totVta = calculo.get(k).totalVenta;
	    							totAux = calculo.get(k).totalTotal;
	    						}
	    					}
	    				}
	    				granAlq += totAlq;
	    				granVta += totVta;
	    				granTotal += totAux;
	    				auxDato.add(myformatint.format(totAlq));
	    				auxDato.add(myformatint.format(totVta));
	    				auxDato.add(myformatint.format(totAux));
	    			}
	    			auxDato.add(myformatint.format(granAlq));
	    			auxDato.add(myformatint.format(granVta));
	    			auxDato.add(myformatint.format(granTotal));
	    			datos.add(auxDato);
	    		}
	    	}
	    	
	    	//ORDENA LA LISTA
			for(int j=2;j<datos.size();j++) {
	            for(int i=2;i<datos.size()-j;i++) {
	            	String A = datos.get(i).get(0) + "_" + datos.get(i).get(1);
	            	String B = datos.get(i+1).get(0) + "_" + datos.get(i+1).get(1);
	                if (i+1!=datos.size() && A.compareToIgnoreCase(B)>0) {
	                    List<String> aux;
	                    aux=datos.get(i);
	                    datos.set(i,datos.get(i+1));
	                    datos.set(i+1, aux);
	                }
	            }
	        }
	    	
	    	List<String> totales = new ArrayList<String>();
	    	totales.add("TOTALES");
	    	totales.add("");
	    	Double granArr = (double) 0;
	    	Double granVta = (double) 0;
	    	Double granTotal = (double) 0;
	    	for(int i = listaFechas.size()-1; i >= 0; i--) {
	    		String desdeAAMMDD = listaFechas.get(i).get(1);
				List<ModeloCalculo> calculo = mapTotales.get(desdeAAMMDD);
				Double alq = (double) 0;
				Double vta = (double) 0;
				Double total = (double) 0;
				if(calculo!=null) {
					for(int k=0;k<calculo.size();k++) {
						alq += calculo.get(k).totalArriendo + calculo.get(k).totalCfi;
						vta += calculo.get(k).totalVenta;
						total += calculo.get(k).totalTotal;
					}
				}
				granArr += alq;
				granVta += vta;
				granTotal += total;
				totales.add(myformatint.format(alq));
				totales.add(myformatint.format(vta));
				totales.add(myformatint.format(total));
	    	}
	    	totales.add(myformatint.format(granArr));
	    	totales.add(myformatint.format(granVta));
	    	totales.add(myformatint.format(granTotal));
	    	datos.add(totales);
			return (datos);
	}
	
	public static File reportConsDetalladoRtpExcel(String db, Map<String,String> mapDiccionario, List<List<String>> datos) {
		
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
			cell.setCellValue("REPORTE CONSOLIDADO POR GRUPOS Y MESES");
			
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
			hoja1.setColumnWidth(2, 10*1000);
			for(int i=3; i<datos.get(0).size()+1; i++){
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
            
            posRow++;
            posCell = 0;
 			row = hoja1.createRow(posRow);
            
            for(int i=0; i<datos.get(1).size(); i++){
            	posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(datos.get(1).get(i));
            }
            // detalle de la tabla
            
            for(int i=2; i<datos.size(); i++){
            	
            	posRow++;
                posCell = 0;
     			row = hoja1.createRow(posRow);
     			
            	posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(datos.get(i).get(0));
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(datos.get(i).get(1));
            	
				for(int k=2; k<datos.get(i).size(); k++){
					posCell++; 
		            cell = row.createCell(posCell);
		            cell.setCellStyle(detalle);
		            Double aux = Double.parseDouble(datos.get(i).get(k).replaceAll(",", ""));
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
	
	
	
	//CONSOLIDADO POR EQUIPO
	
	public static List<List<String>> reportConsDetalladoPorEquipoRtp (Connection con, String db, Fechas fecha, Long meses, String permisoPorBodega, String pais, String esPorSucursal, String id_sucursal) {
		
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
    	
    	Map<String,List<ModeloCalculo>> mapTotales = new HashMap<String,List<ModeloCalculo>>();
    	Map<Long,List<String>> bodegas = BodegaEmpresa.mapIdBod_BodegaEmpresaExternas(con, db, esPorSucursal, id_sucursal);
    	Map<Long,Equipo> equipos = Equipo.mapAllAll(con, db);
    	Map<String,String> mapIdBodEquipo = new HashMap<String,String>();
    	
    	Map<String,TasasCambio> mapAllTasas = TasasCambio.mapTasasporAllFecha(con, db, pais);
    	
    	
    	
    	List<Long> listIdBodegaEmpresa = ModCalc_InvInicial.listIdBodegaEmpresa(con, db, permisoPorBodega);
		Map<Long,Calc_BodegaEmpresa> mapBodegaEmpresa = Calc_BodegaEmpresa.mapAllBodegasVigentes(con, db);
		Map<String,Calc_Precio> mapPrecios = Calc_Precio.mapPrecios(con, db, listIdBodegaEmpresa);
		Map<Long,Calc_Precio> mapMaestroPrecios = Calc_Precio.mapMaestroPrecios(con, db);
		Map<String, Double> mapFijaTasas = BodegaEmpresa.mapFijaTasasAll(con, db);
		
		Map<String,String> mapPermanencias = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, db);
		
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
    		
    		List<Long> listIdGuia_fechaCorte = ModCalc_InvInicial.listIdGuia_fechaCorte(con, db, desdeAAMMDD);
			List<Inventarios> inventarioAux = Inventarios.inventario(con, db, listIdBodegaEmpresa, listIdGuia_fechaCorte);
			List<Long> listIdGuia_entreFechas = ModCalc_GuiasPer.listIdGuia_entreFecha(con, db, desdeAAMMDD, hastaAAMMDD);
			List<Inventarios> guiasPerAux = Inventarios.guiasPer(con, db, listIdBodegaEmpresa, listIdGuia_entreFechas);
			List<Calc_AjustesEP> listaAjustes = Calc_AjustesEP.listaAjustesEntreFechas(con, db, desdeAAMMDD, hastaAAMMDD);
	
			List<ModCalc_InvInicial> inventarioInicial = ModCalc_InvInicial.resumenInvInicial(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa, 
					mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, listIdGuia_fechaCorte, inventarioAux);
			
			List<ModCalc_GuiasPer> guiasPeriodo = ModCalc_GuiasPer.resumenGuiasPer(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa, 
					mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, listIdGuia_entreFechas, guiasPerAux, mapPermanencias);
			
			List<ModeloCalculo> calculo = ModeloCalculo.valorTotalporBodega(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, inventarioInicial,guiasPeriodo, listaAjustes);
			
    		mapTotales.put(desdeAAMMDD, calculo);
    		for(int k=0;k<calculo.size();k++) {
    			String idBodega = calculo.get(k).id_bodegaEmpresa.toString();
    			String idEquipo = calculo.get(k).id_equipo.toString();
    			mapIdBodEquipo.put(idBodega+"_"+idEquipo, idBodega+"_"+idEquipo);
    		}
    	}
    	
    	List<List<String>> datos = new ArrayList<List<String>>();
    	
    	List<String> titulos = new ArrayList<String>();
    	titulos.add("BODEGA/CLIENTE");
    	titulos.add("GRUPO");
    	titulos.add("CODIGO");
    	titulos.add("EQUIPO");
    	for(int i = listaFechas.size()-1; i >= 0; i--) {
    		String[] auxFech = listaFechas.get(i).get(1).split("-");
    		titulos.add(auxFech[1]+"/"+auxFech[0]);
    		titulos.add(auxFech[1]+"/"+auxFech[0]);
    		titulos.add(auxFech[1]+"/"+auxFech[0]);
    	}
    	titulos.add("ALQUILER");
    	titulos.add("VENTA");
    	titulos.add("TOTAL");
    	datos.add(titulos);
    	
    	titulos = new ArrayList<String>();
    	titulos.add("");
    	titulos.add("");
    	titulos.add("");
    	titulos.add("");
    	for(int i = listaFechas.size()-1; i >= 0; i--) {
    		titulos.add("Alquiler");
    		titulos.add("Venta");
    		titulos.add("Total");
    	}
    	titulos.add("");
    	titulos.add("");
    	titulos.add("");
    	datos.add(titulos);
    	
    	
    	for (String value : mapIdBodEquipo.values()) {
    		List<String> auxDato = new ArrayList<String>();
    		
    		String[] arrIdBodIdEquipo = value.split("_");
    		List<String> bod = bodegas.get(Long.parseLong(arrIdBodIdEquipo[0]));
    		Equipo equipo = equipos.get(Long.parseLong(arrIdBodIdEquipo[1]));
    		
    		if(bod!=null && equipo!=null) {
    			auxDato.add(bod.get(5));
    			auxDato.add(equipo.getGrupo());
    			auxDato.add(equipo.getCodigo());
    			auxDato.add(equipo.getNombre());
    			
    			Double granAlq = (double) 0;
    			Double granVta = (double) 0;
    			Double granTotal = (double) 0;
    			for(int i = listaFechas.size()-1; i >= 0; i--) {
    				String desdeAAMMDD = listaFechas.get(i).get(1);
    				List<ModeloCalculo> calculo = mapTotales.get(desdeAAMMDD);
    				Double totAlq = (double) 0;
    				Double totVta = (double) 0;
    				Double totAux = (double) 0;
    				if(calculo!=null) {
    					for(int k=0;k<calculo.size();k++) {
    						String auxIdBod = calculo.get(k).id_bodegaEmpresa.toString();
    						String auxIdEquipo = calculo.get(k).id_equipo.toString();
    						String idBod_idEquipo = auxIdBod + "_" + auxIdEquipo;
    						if(idBod_idEquipo.equals(value)) {
    							totAlq = calculo.get(k).totalArriendo + calculo.get(k).totalCfi;
    							totVta = calculo.get(k).totalVenta;
    							totAux = calculo.get(k).totalTotal;
    						}
    					}
    				}
    				granAlq += totAlq;
    				granVta += totVta;
    				granTotal += totAux;
    				auxDato.add(myformatint.format(totAlq));
    				auxDato.add(myformatint.format(totVta));
    				auxDato.add(myformatint.format(totAux));
    			}
    			auxDato.add(myformatint.format(granAlq));
    			auxDato.add(myformatint.format(granVta));
    			auxDato.add(myformatint.format(granTotal));
    			datos.add(auxDato);
    		}
    	}
    	
    	//ORDENA LA LISTA
		for(int j=2;j<datos.size();j++) {
            for(int i=2;i<datos.size()-j;i++) {
            	String A = datos.get(i).get(0) + "_" + datos.get(i).get(1);
            	String B = datos.get(i+1).get(0) + "_" + datos.get(i+1).get(1);
                if (i+1!=datos.size() && A.compareToIgnoreCase(B)>0) {
                    List<String> aux;
                    aux=datos.get(i);
                    datos.set(i,datos.get(i+1));
                    datos.set(i+1, aux);
                }
            }
        }
    	
    	List<String> totales = new ArrayList<String>();
    	totales.add("TOTALES");
    	totales.add("");
    	totales.add("");
    	totales.add("");
    	Double granArr = (double) 0;
    	Double granVta = (double) 0;
    	Double granTotal = (double) 0;
    	for(int i = listaFechas.size()-1; i >= 0; i--) {
    		String desdeAAMMDD = listaFechas.get(i).get(1);
			List<ModeloCalculo> calculo = mapTotales.get(desdeAAMMDD);
			Double alq = (double) 0;
			Double vta = (double) 0;
			Double total = (double) 0;
			if(calculo!=null) {
				for(int k=0;k<calculo.size();k++) {
					alq += calculo.get(k).totalArriendo + calculo.get(k).totalCfi;
					vta += calculo.get(k).totalVenta;
					total += calculo.get(k).totalTotal;
				}
			}
			granArr += alq;
			granVta += vta;
			granTotal += total;
			totales.add(myformatint.format(alq));
			totales.add(myformatint.format(vta));
			totales.add(myformatint.format(total));
    	}
    	totales.add(myformatint.format(granArr));
    	totales.add(myformatint.format(granVta));
    	totales.add(myformatint.format(granTotal));
    	datos.add(totales);
		return (datos);
	}
	
	public static File reportConsDetalladoPorEquipoRtpExcel(String db, Map<String,String> mapDiccionario, List<List<String>> datos) {
		
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
			cell.setCellValue("REPORTE CONSOLIDADO POR GRUPOS Y MESES");
			
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
			hoja1.setColumnWidth(2, 10*1000);
			hoja1.setColumnWidth(3, 5*1000);
			hoja1.setColumnWidth(4, 10*1000);
			for(int i=5; i<datos.get(0).size()+1; i++){
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
            
            posRow++;
            posCell = 0;
 			row = hoja1.createRow(posRow);
            
            for(int i=0; i<datos.get(1).size(); i++){
            	posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(datos.get(1).get(i));
            }
            // detalle de la tabla
            
            for(int i=2; i<datos.size(); i++){
            	
            	posRow++;
                posCell = 0;
     			row = hoja1.createRow(posRow);
     			
            	posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(datos.get(i).get(0));
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(datos.get(i).get(1));
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(datos.get(i).get(2));
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(datos.get(i).get(3));
            	
				for(int k=4; k<datos.get(i).size(); k++){
					posCell++; 
		            cell = row.createCell(posCell);
		            cell.setCellStyle(detalle);
		            Double aux = Double.parseDouble(datos.get(i).get(k).replaceAll(",", ""));
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
	
	
	
