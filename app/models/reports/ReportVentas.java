package models.reports;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.TempFile;

import models.utilities.Archivos;
import models.utilities.DecimalFormato;
import models.utilities.Fechas;

public class ReportVentas {

	
	
	
	
	public static List<List<String>> ventasPorPeriodo(Connection con, String db, String desde, String hasta,
			Map<Long,Double> tasas) {
		List<List<String>> lista = new ArrayList<List<String>>();
		Map<String,List<String>> map = new HashMap<String,List<String>>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select"
							+ " guia.fecha,"
							+ " guia.numero,"
							+ " guia.numGuiaCliente,"
							+ " guia.guiaPDF,"
							+ " transportista.patente,"
							+ " transportista.conductor,"
							+ " bodegaEmpresa.nombre,"
							+ " equipo.nombre,"
							+ " movimiento.cantidad,"
							+ " listaPrecio.precioReposicion,"
							+ " listaPrecio.id_moneda,"
							+ " sucursal.nombre"
							+ " from `"+db+"`.movimiento"
							+ " left join `"+db+"`.guia on guia.id = movimiento.id_guia"
							+ " left join `"+db+"`.transportista on transportista.id = guia.id_transportista"
							+ " left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa"
							+ " left join `"+db+"`.equipo on equipo.id = movimiento.id_equipo"
							+ " left join `"+db+"`.listaPrecio on concat(listaPrecio.id_equipo,listaPrecio.id_cotizacion,listaPrecio.id_bodegaEmpresa)"
									+ " = concat(movimiento.id_equipo,movimiento.id_cotizacion, movimiento.id_bodegaEmpresa)"
							+ " left join `"+db+"`.sucursal on sucursal.id = bodegaEmpresa.id_sucursal"
							+ " where movimiento.esVenta=1 and bodegaEmpresa.esInterna=2 and guia.fecha between ? and ?;");
			smt.setString(1, desde);
			smt.setString(2, hasta);
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				
				Double tasaCambio = tasas.get(rs.getLong(11));
				if(tasaCambio == null) {
					tasaCambio = (double) 1;
				}
				
				Double totalPesos = rs.getDouble(9) * rs.getDouble(10) * tasaCambio;
				
				List<String> auxMap = map.get(rs.getString(2));
				if(auxMap == null) {
					List<String> aux = new ArrayList<String>();
					aux.add(rs.getString(1));
					aux.add(rs.getString(2));
					aux.add(rs.getString(3));
					aux.add(rs.getString(4));
					aux.add(rs.getString(5));
					aux.add(rs.getString(6));
					aux.add(rs.getString(7));
					aux.add(rs.getString(8));
					aux.add(DecimalFormato.formato(totalPesos, (long)0));
					aux.add(rs.getString(12));
					map.put(rs.getString(2), aux);
				}else {
					Double aux = Double.parseDouble(auxMap.get(8).replaceAll(",", ""));
					aux += totalPesos;
					auxMap.set(8, DecimalFormato.formato(aux, (long)0));
					map.put(rs.getString(2), auxMap);
				}
			}
			
			for (Map.Entry<String, List<String>> entry : map.entrySet()) {
	            List<String> value = entry.getValue();
	            lista.add(value);
	        }
			
			rs.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (lista);
	}
	
	
	
	public static File reportVentasPorPeriodo1Excel(String db, Map<String,String> mapDiccionario,
			List<List<String>> datos, String desde, String hasta) {
		
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
            
            DataFormat format = libro.createDataFormat();
            CellStyle formatFecha = libro.createCellStyle();
            formatFecha.setBorderBottom(CellStyle.BORDER_THIN);
            formatFecha.setBorderTop(CellStyle.BORDER_THIN);
            formatFecha.setBorderRight(CellStyle.BORDER_THIN);
            formatFecha.setBorderLeft(CellStyle.BORDER_THIN);
            formatFecha.setDataFormat(format.getFormat("dd-mm-yyyy"));
            
            
            
            //titulos del archivo
            
            libro.setSheetName(0, "Toneladas");
            Sheet hoja1 = libro.getSheetAt(0);
            
            Row row = null;
            Cell cell = null;
            
            row = hoja1.createRow(1);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("REPORTE VENTAS: PERIODO DE "+ Fechas.DDMMAA(desde) + " A "+ Fechas.DDMMAA(hasta));
			
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
			
			
			
			//anchos de columnas
			for(int i=1; i<17; i++) {
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
	        anchor.setCol1(10);
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
			cell.setCellValue("FECHA");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("NRO. MOV");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("NRO. REF");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("PATENTE");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("CONDUCTOR");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("SUCURSAL");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(mapDiccionario.get("BODEGA")+" DESTINO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("CONCEPTO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TOTAL NETO");
			
			
			for(int i=0; i<datos.size(); i++){
				posRow++;
				row = hoja1.createRow(posRow);
				posCell = 0;
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(formatFecha);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				Fechas fecha = Fechas.obtenerFechaDesdeStrAAMMDD(datos.get(i).get(0));
				cell.setCellValue(fecha.getFechaUtil());
				
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
				cell.setCellValue(datos.get(i).get(4));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(datos.get(i).get(5));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(datos.get(i).get(9));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(datos.get(i).get(6));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(datos.get(i).get(7));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				Double valor = Double.parseDouble(datos.get(i).get(8).replaceAll(",", ""));
				cell.setCellValue(valor);
				
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
	
	
	
