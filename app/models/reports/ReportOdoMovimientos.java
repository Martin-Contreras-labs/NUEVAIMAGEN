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
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.io.IOUtils;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.util.TempFile;

import models.tables.BodegaEmpresa;
import models.tables.CotiOdo;
import models.utilities.Archivos;
import models.utilities.Fechas;


public class ReportOdoMovimientos {

	static DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00",symbols);

		
	
	
	public static List<List<String>> movimientoOdoCantidad(Connection con, String db, Long id_bodegaEmpresa, String fechaDesde, String fechaHasta) {
		List<List<String>> lista = new ArrayList<List<String>>();
		
		try {
			PreparedStatement smt1 = con
					.prepareStatement(" select  ventaServicio.fecha, operadorServicio.nombre " +
							" from `"+db+"`.ventaServicio " +
							" left join `"+db+"`.operadorServicio on operadorServicio.id = ventaServicio.id_operador " +
							" where ventaServicio.id_bodegaEmpresa =  ? and fecha between ? and ? " +
							" group by ventaServicio.fecha "+
							" order by ventaServicio.fecha;");
			smt1.setLong(1, id_bodegaEmpresa);
			smt1.setString(2, fechaDesde);
			smt1.setString(3, fechaHasta);
			
			ResultSet rs1 = smt1.executeQuery();
			
			List<String> fecha = new ArrayList<String>();
			List<String> blanco = new ArrayList<String>();
				
			fecha.add(" ");
			blanco.add("CLASE");
			
			fecha.add(" ");
			blanco.add("CODIGO");
			
			fecha.add(" ");
			blanco.add("DESCRIPCION");
			
			fecha.add("Fechas");
			blanco.add("COTI");
			
			
			while (rs1.next()) {
				String aux3[] = rs1.getString(1).split("-");
				fecha.add(""+aux3[2]+"/"+aux3[1]+"/"+aux3[0]);	
				blanco.add(rs1.getString(2));
			}
			rs1.close();
			smt1.close();
			
			fecha.add("TOTAL");
			blanco.add("");
			
			
			
			
			List<List<String>> listServicios = new ArrayList<List<String>>();
			PreparedStatement smt2 = con
					.prepareStatement(" select ventaServicio.id_servicio, claseServicio.nombre, servicio.codigo, servicio.nombre, unidadServicio.nombre, ventaServicio.id_cotiOdo "
							+ " from `"+db+"`.ventaServicio "
							+ " left join `"+db+"`.servicio on servicio.id = ventaServicio.id_servicio "
							+ " left join `"+db+"`.claseServicio on claseServicio.id = servicio.id_claseServicio "
							+ " left join `"+db+"`.unidadServicio on unidadServicio.id = servicio.id_unidadServicio "
							+ " where ventaServicio.id_bodegaEmpresa = ? and ventaServicio.fecha between ? and ? "
							+ " group by ventaServicio.id_servicio, claseServicio.nombre, servicio.codigo, servicio.nombre, unidadServicio.nombre, ventaServicio.id_cotiOdo "
							+ " order by claseServicio.nombre, servicio.nombre;");
			smt2.setLong(1, id_bodegaEmpresa);
			smt2.setString(2, fechaDesde);
			smt2.setString(3, fechaHasta);
			ResultSet rs2 = smt2.executeQuery();
			
			Map<Long,Long> mapNroCoti = CotiOdo.mapIdvsNroCoti(con, db);
			
			while (rs2.next()) {
				List<String> aux = new ArrayList<String>();
				aux.add(rs2.getString(1));
				aux.add(rs2.getString(2));
				
				Long nroCoti = mapNroCoti.get(rs2.getLong(6));
				if(nroCoti==null) {
					nroCoti = (long)0;
				}
				aux.add(nroCoti.toString());
				
				aux.add(rs2.getString(3));
				aux.add(rs2.getString(4));
				aux.add(rs2.getString(5));
				aux.add(rs2.getString(6));
				listServicios.add(aux);
			}
			rs1.close();
			smt1.close();

			
			Map<List<String>,Double> mapCantidades = new HashMap<List<String>,Double>();
			PreparedStatement smt3 = con
					.prepareStatement(" select ventaServicio.fecha, ventaServicio.id_servicio, sum(ventaServicio.cantidad), ventaServicio.id_cotiOdo "
							+ " from `"+db+"`.ventaServicio "
							+ " where ventaServicio.id_bodegaEmpresa = ? and ventaServicio.fecha between ? and ? "
							+ " group by ventaServicio.fecha, ventaServicio.id_servicio, ventaServicio.id_cotiOdo;");
			smt3.setLong(1, id_bodegaEmpresa);
			smt3.setString(2, fechaDesde);
			smt3.setString(3, fechaHasta);
			
			ResultSet rs3 = smt3.executeQuery();
			while (rs3.next()) {
				List<String> aux = new ArrayList<String>();
				String aux3[] = rs3.getString(1).split("-");
				aux.add(""+aux3[2]+"/"+aux3[1]+"/"+aux3[0]);
				
				aux.add(rs3.getString(2)+"_"+rs3.getString(4));
				
				mapCantidades.put(aux, rs3.getDouble(3));
			}
			rs3.close();
			smt3.close();
			
			lista.add(fecha);
			lista.add(blanco);
			
			for(int i=0; i< listServicios.size(); i++) {
				List<String> aux2 = new ArrayList<String>();
				aux2.add(listServicios.get(i).get(1));
				
				aux2.add(listServicios.get(i).get(3));
				aux2.add(listServicios.get(i).get(4));
				aux2.add(listServicios.get(i).get(2));
				Double total = (double)0;
				for(int j=4; j< fecha.size()-1; j++) {
					List<String> aux = new ArrayList<String>();
					
					aux.add(fecha.get(j));
					aux.add(listServicios.get(i).get(0)+"_"+listServicios.get(i).get(6));
					
					String cantStr = "";
					Double cant = mapCantidades.get(aux);
					if(cant!=null) {
						cantStr = myformatdouble2.format(cant);
						total += cant;
					}
					
					aux2.add(cantStr);
				}
				aux2.add(myformatdouble2.format(total));
				lista.add(aux2);
			}
			
			
		} catch (SQLException e) {
				e.printStackTrace();
		}
		
		return (lista);
	}
	
	public static File movimientosOdoCantidadExcel(String db, List<List<String>> datos, Map<String,String> mapDiccionario, BodegaEmpresa bodega, String fechaDesde, String fechaHasta) {

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
			cell.setCellValue("REPORTE ODO MOVIMIENTO");
		
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
			cell.setCellValue("PERIODO: DESDE "+Fechas.DDMMAA(fechaDesde)+" HASTA "+Fechas.DDMMAA(fechaHasta));
			
			// encabezado de la tabla
			
			hoja1.setColumnWidth(1, 7*1000);
			hoja1.setColumnWidth(2, 5*1000);
			hoja1.setColumnWidth(3, 10*1000);
			hoja1.setColumnWidth(4, 2*1000);
			
			for(int i=5; i<datos.get(0).size()+3; i++) {
				hoja1.setColumnWidth(i, 3*1000);
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
			
			//DETALLE DE LA TABLA
			int posRow = 8;
			for(int i=0;i<datos.size();i++){
				row = hoja1.createRow(posRow);
				int posCell = 0;
				Double aux = (double)0;
				for(int j=0;j<datos.get(i).size();j++){
					String dato = datos.get(i).get(j);
					if(i<2){
						posCell++; 
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellValue(dato);
					}else{
						if(j<4){
							posCell++; 
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellValue(dato);
						}else {
							if(dato.equals("")||dato.equals(" ")) {
								dato = "0";
							}
							posCell++; 
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
				            aux = Double.parseDouble(dato.replaceAll(",", ""));
							cell.setCellValue(aux);
						}
					}
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
	
	
	public static File movimientosOdoValorizadoExcel(String db, List<List<String>> datos, Map<String,String> mapDiccionario, BodegaEmpresa bodega, String fechaDesde, String fechaHasta, List<List<String>> listaAjustes) {

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
			cell.setCellValue("REPORTE ODO MOVIMIENTO");
		
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
			cell.setCellValue("PERIODO: DESDE "+Fechas.DDMMAA(fechaDesde)+" HASTA "+Fechas.DDMMAA(fechaHasta));
			
			// encabezado de la tabla
			
			hoja1.setColumnWidth(1, 7*1000);
			hoja1.setColumnWidth(2, 5*1000);
			hoja1.setColumnWidth(3, 10*1000);
			hoja1.setColumnWidth(4, 2*1000);
			
			for(int i=5; i<datos.get(0).size()+3; i++) {
				hoja1.setColumnWidth(i, 3*1000);
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
			
			//DETALLE DE LA TABLA
			int posRow = 8;
			for(int i=0;i<datos.size();i++){
				row = hoja1.createRow(posRow);
				int posCell = 0;
				Double aux = (double)0;
				for(int j=0;j<datos.get(i).size();j++){
					String dato = datos.get(i).get(j);
					if(i<2){
						posCell++; 
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellValue(dato);
					}else{
						if(j<4 || j==datos.get(i).size()-5){
							posCell++; 
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellValue(dato);
						}else {
							if(dato.equals("")||dato.equals(" ")) {
								dato = "0";
							}
							posCell++; 
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
				            aux = Double.parseDouble(dato.replaceAll(",", ""));
							cell.setCellValue(aux);
						}
					}
				}
				posRow++;
			}
			
			posRow++;
			posRow++;
			for(int i=0;i<listaAjustes.size();i++){
				row = hoja1.createRow(posRow);
				String cod = listaAjustes.get(i).get(5);
				String det = listaAjustes.get(i).get(6);
				Double val = Double.parseDouble(listaAjustes.get(i).get(4).replaceAll(",", ""));
				
				cell = row.createCell(1);
	            cell.setCellStyle(detalle);
				cell.setCellValue(cod);
				
				cell = row.createCell(2);
	            cell.setCellStyle(detalle);
				cell.setCellValue(det);
				
				cell = row.createCell(3);
	            cell.setCellStyle(detalle);
				cell.setCellValue(val);
				
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
	

			
}
	
	
	
