package models.xlsx;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.util.TempFile;

import models.utilities.Archivos;
import models.utilities.Fechas;




public class OtListaRevisarEnExcel {
	
	
	
	public static File otListaRevisarExcel(String db, List<List<String>> listOt, Map<String,String> mapDiccionario) {
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
            
            //titulos del archivo
            
            libro.setSheetName(0, "LISTADO");
            Sheet hoja1 = libro.getSheetAt(0);
            
            Row row = null;
            Cell cell = null;
            
            row = hoja1.createRow(1);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellValue("LISTADO DE ORDENES DE TRABAJO (confirmadas y pendientes de confirmar)");
			
			row = hoja1.createRow(2);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("EMPRESA: "+mapDiccionario.get("nEmpresa"));
			
			row = hoja1.createRow(3);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("FECHA: "+Fechas.hoy().getFechaStrDDMMAA());
			
			//anchos de columnas
			for(int i=1; i<20; i++) {
				hoja1.setColumnWidth(i, 5*1000);
			}
			hoja1.setColumnWidth(8, 10*1000);
			hoja1.setColumnWidth(9, 10*1000);
			hoja1.setColumnWidth(10, 10*1000);
            
			//INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
			InputStream x = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
            byte[] bytes = IOUtils.toByteArray(x);
            x.close();
            int pngIndex = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			Drawing draw = hoja1.createDrawingPatriarch();
			CreationHelper helper = libro.getCreationHelper();
			ClientAnchor anchor = helper.createClientAnchor();
	        //set top-left corner for the image
	        anchor.setCol1(7);
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
			cell.setCellValue("SUCURSAL");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("COMERCIAL");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("Nro "+mapDiccionario.get("OT"));
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("FECHA "+mapDiccionario.get("OT"));
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("CONFIRMADA "+mapDiccionario.get("OT"));
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("Nro COTI");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("FECHA COTI");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue(mapDiccionario.get("BODEGA")+"/PROYECTO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("CLIENTE");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("PROYECTO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("OBSERVACIONES "+mapDiccionario.get("OT"));
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("OBSERVACIONES COTI");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("ULTIMA ACTUALIZACION");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("ESTIMACION DE ENTREGA");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("SALDO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("ESTADO");
			
			
			for(int i=0;i<listOt.size();i++){
				
					posRow++;
					posCell = 0;
			        row = hoja1.createRow(posRow);
					
			        posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellValue(listOt.get(i).get(15));
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellValue(listOt.get(i).get(16));
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellValue(listOt.get(i).get(2));
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellValue(Fechas.DDMMAA(listOt.get(i).get(3)));
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellValue(Fechas.DDMMAA(listOt.get(i).get(19)));
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellValue(listOt.get(i).get(4));
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellValue(Fechas.DDMMAA(listOt.get(i).get(5)));

					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellValue(listOt.get(i).get(14));
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellValue(listOt.get(i).get(6));
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellValue(listOt.get(i).get(7));
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellValue(listOt.get(i).get(8));
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellValue(listOt.get(i).get(9));
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellValue(Fechas.DDMMAA(listOt.get(i).get(17)));
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellValue(Fechas.DDMMAA(listOt.get(i).get(18)));
					
					Double saldo = Double.parseDouble(listOt.get(i).get(20).replaceAll(",", ""));
					posCell++;
		            cell = row.createCell(posCell);
		            cell.setCellStyle(detalle);
					cell.setCellValue(saldo);
					
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					cell.setCellValue(listOt.get(i).get(12));
					
					
					
				
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
	
			
}
	
	
	
