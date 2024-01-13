package models.reports;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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

import models.tables.VentaServicio;
import models.utilities.Archivos;
import models.utilities.Fechas;


public class ReportOdoOperadores {
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble4 = new DecimalFormat("#,##0.0000");
	
	static SimpleDateFormat myformatfecha = new SimpleDateFormat("dd-MM-yyyy");
	
	static DecimalFormat myformatMoneda = new DecimalFormat("#,##0");
		
	
	
	public static List<List<String>> reportOperadorConsol1(String desdeAAMMDD, String hastaAAMMDD, List<VentaServicio> vtas){
		Map<String,List<String>> mapAuxListOperServ = new HashMap<String,List<String>>();
		List<List<String>> listOperUnid = new ArrayList<List<String>>();
		Map<String,String> datos = new HashMap<String,String>(); // getFecha+_+getId_operador+_+getUnidadServicio vs cantidad
		
		for(VentaServicio v: vtas) {
			List<String> aux = new ArrayList<String>();
			aux.add(v.getNomOperador());
			aux.add(v.getUnidadServicio());
			aux.add(v.getId_operador().toString());
			mapAuxListOperServ.put(v.getId_operador().toString()+"_"+v.getUnidadServicio().toString(), aux);
			String cantAux = datos.get(v.getFecha()+"_"+v.getId_operador()+"_"+v.getUnidadServicio());
			if(cantAux == null) {
				datos.put(v.getFecha()+"_"+v.getId_operador()+"_"+v.getUnidadServicio(), v.cantidad);
			}else {
				Double aux1 = Double.parseDouble(cantAux.replaceAll(",", ""));
				Double aux2 = Double.parseDouble(v.cantidad.replaceAll(",", ""));
				Double aux3 = aux1 + aux2;
				datos.put(v.getFecha()+"_"+v.getId_operador()+"_"+v.getUnidadServicio(), myformatdouble2.format(aux3));
			}
		}
		
		List<String> encabezado = new ArrayList<String>();
		encabezado.add("OPERADOR");
		encabezado.add("UNIDAD");
		
		Fechas ini = Fechas.obtenerFechaDesdeStrAAMMDD(desdeAAMMDD);
		Fechas fin = Fechas.obtenerFechaDesdeStrAAMMDD(hastaAAMMDD);
		fin = Fechas.addDias(fin.getFechaCal(), 1);
		List<String> listFechas = new ArrayList<String>();
		while(ini.fechaCal.before(fin.fechaCal)) {
			encabezado.add(ini.getFechaStrDDMMAA());
			listFechas.add(ini.getFechaStrDDMMAA());
			ini = Fechas.addDias(ini.fechaCal, 1);
			
		}
		
		for(List<String> aux: mapAuxListOperServ.values()) {
			listOperUnid.add(aux);
		}
		
		//ORDENA LA LISTA OPERADORES
		for(int j=0;j<listOperUnid.size();j++) {
            for(int i=0;i<listOperUnid.size()-j;i++) {
            	
            	if (i+1!=listOperUnid.size()) {
	            	String a = listOperUnid.get(i).get(0)+"_"+listOperUnid.get(i).get(1);
	            	String b = listOperUnid.get(i+1).get(0)+"_"+listOperUnid.get(i+1).get(1);
	                if (a.compareToIgnoreCase(b)>0) {
	                    List<String> aux;
	                    aux=listOperUnid.get(i);
	                    listOperUnid.set(i,listOperUnid.get(i+1));
	                    listOperUnid.set(i+1, aux);
	                }
            	}
            }
        }
		
		List<List<String>> plantilla = new ArrayList<List<String>>();
		
		plantilla.add(encabezado);
		
		for(List<String> l: listOperUnid) {
			List<String> aux = new ArrayList<String>();
			aux.add(l.get(0));
			aux.add(l.get(1));
			for(String x: listFechas) {
				String key = x +"_"+l.get(2)+"_"+l.get(1);
				String cantidad = datos.get(key);
				if (cantidad == null) {
					aux.add("");
				}else {
					aux.add(cantidad);
				}
			}
			plantilla.add(aux);
		}
		return(plantilla);
	}
	
	public static File reportOperadorConsol1Excel(String db, Map<String,String> mapDiccionario, String desdeAAMMDD, String hastaAAMMDD, List<List<String>> plantilla) {
		
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
            
            CellStyle rotateText =  libro.createCellStyle();
			rotateText.setRotation((short)90);
			rotateText.setBorderBottom(CellStyle.BORDER_THIN);
			rotateText.setBorderTop(CellStyle.BORDER_THIN);
			rotateText.setBorderRight(CellStyle.BORDER_THIN);
			rotateText.setBorderLeft(CellStyle.BORDER_THIN);
            rotateText.setFillPattern(CellStyle.SOLID_FOREGROUND);
            rotateText.setFillForegroundColor((short)19);
            rotateText.setAlignment(CellStyle.ALIGN_CENTER);
            
            
            Sheet hoja1 = libro.getSheetAt(0);
            Row row = null;
            Cell cell = null;
            
            
            row = hoja1.createRow(1);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("CONSOLIDADO POR OPERADORES (SOLO CANTIDADES)");
			
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
			
			row = hoja1.createRow(6);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("PERIODO: "+Fechas.DDMMAA(desdeAAMMDD)+" al "+Fechas.DDMMAA(hastaAAMMDD));
			
           
			
			
			// encabezado de la tabla
			
			int posCell = 0;
			int posColl = 0;
			row = hoja1.createRow(8);
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 10*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("NOMBRE OPERADOR");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 1500);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("UN");
			
			for(int i=2;i<plantilla.get(0).size();i++){
				posCell++; posColl++;
				hoja1.setColumnWidth(posColl, 1500);
	            cell = row.createCell(posCell);
	            cell.setCellStyle(rotateText);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(plantilla.get(0).get(i));
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
	        anchor.setCol1(30);
	        anchor.setRow1(1);
			Picture img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);
			
			
			
			//DETALLE DE LA TABLA
			int posRow = 9;
			for(int i=1;i<plantilla.size();i++){
				row = hoja1.createRow(posRow);
				posCell = 0;
				Double aux = (double)0;
				
				posCell++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(plantilla.get(i).get(0));
				
				posCell++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(plantilla.get(i).get(1));
				
				for(int j=2;j<plantilla.get(i).size();j++) {
					posCell++;
		            cell = row.createCell(posCell);
		            cell.setCellStyle(detalle);
		            if(!plantilla.get(i).get(j).equals("")) {
		            	aux = Double.parseDouble(plantilla.get(i).get(j).replaceAll(",", ""));
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(aux);
					}
				}
				
				posRow++;
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
	
	
	
