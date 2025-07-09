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
import java.util.*;

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
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.TempFile;

import models.tables.Grupo;
import models.tables.Sucursal;
import models.utilities.Archivos;
import models.utilities.DecimalFormato;
import models.utilities.Fechas;
import models.utilities.GenParesColumnExcel;


public class ReportKilos {

	static DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00",symbols);
	
	
	public static HashMap<String, Object> reportKgArrPorGrupoPorPeriodo(Connection con, String db, String desde, String hasta) {
		
		List<String> cabecera1 = new ArrayList<String>();
		List<String> cabecera2 = new ArrayList<String>();
		List<List<String>> datos = new ArrayList<List<String>>();
		Map<String,Double> mapToneladas = new HashMap<String,Double>();
		
		try {
			
			PreparedStatement smt5 = con
					.prepareStatement("select"
							+ " bodegaEmpresa.id_sucursal,"
							+ " equipo.id_grupo,"
							+ " movimiento.id_tipoMovimiento,"
							+ " sum(movimiento.cantidad * equipo.kg/1000) as totalToneladas"
							+ " from `"+db+"`.movimiento"
							+ " left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id=movimiento.id_bodegaEmpresa"
							+ " left join `"+db+"`.guia on guia.id = movimiento.id_guia"
							+ " left join `"+db+"`.equipo on equipo.id = movimiento.id_equipo"
							+ " where bodegaEmpresa.esInterna=2 and guia.fecha between ? and ?"
							+ " group by movimiento.id_tipoMovimiento, equipo.id_grupo, bodegaEmpresa.id_sucursal;");
			smt5.setString(1, desde);
			smt5.setString(2, hasta);

			ResultSet rs5 = smt5.executeQuery();
			while (rs5.next()) {
				String key = rs5.getString(1) + "_" + rs5.getString(2) + "_" + rs5.getString(3);
				Double ton = rs5.getDouble(4);
				mapToneladas.put(key,ton);
			}
			rs5.close();
			smt5.close();
			
			List<Grupo> listGrupos = Grupo.allConEquipos (con,db);
			List<Sucursal> listSucursal = Sucursal.all(con, db);

			
			cabecera1.add("SUCURSALES");
			cabecera2.add("FAMILIA/GRUPO");
			for(Sucursal s: listSucursal) {
				cabecera1.add(s.getNombre());
				cabecera2.add("Salida");
				cabecera2.add("Entrada");
			}
			cabecera1.add("TOTALES");
			cabecera2.add("Salida");
			cabecera2.add("Entrada");
			
			for(Grupo g: listGrupos) {
				List<String> aux = new ArrayList<String>();
				aux.add(g.nombre);
				Double entradas = (double)0;
				Double salidas = (double)0;
				for(Sucursal s: listSucursal) {
					String key = s.getId() + "_" + g.getId() + "_1"; 
					Double ton = mapToneladas.get(key);
					if(ton != null) {
						aux.add(DecimalFormato.formato(ton, (long)2));
						entradas += ton;
					}else {
						aux.add("0.00");
					}
					key = s.getId() + "_" + g.getId() + "_2"; 
					ton = mapToneladas.get(key);
					if(ton != null) {
						aux.add(DecimalFormato.formato(ton, (long)2));
						salidas += ton;
					}else {
						aux.add("0.00");
					}
				}
				aux.add(DecimalFormato.formato(entradas, (long)2));
				aux.add(DecimalFormato.formato(salidas, (long)2));
				datos.add(aux);
			}
			
			
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("cabecera1", cabecera1);
		map.put("cabecera2", cabecera2);
		map.put("datos", datos);
		
		return (map);
	}

	
	public static List<List<String>> reportKilosArriendoPorGrupoAnual(Connection con, String db, Long anio) {
		
		List<List<String>> lista = new ArrayList<List<String>>();
		Map<String,List<String>> mapListaIdGrupo_Mes_Tipo = new HashMap<String,List<String>>();
		
		try {
			PreparedStatement smt5 = con
					.prepareStatement(" select " + 
							" year(guia.fecha)," + 
							" month(guia.fecha)," + 
							" grupo.id," + 
							" grupo.nombre," + 
							" movimiento.id_tipoMovimiento as tipo," + 
							" sum(movimiento.cantidad * equipo.kg/1000) as total" + 
							" from `"+db+"`.movimiento" + 
							" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id=movimiento.id_bodegaEmpresa" + 
							" left join `"+db+"`.guia on guia.id = movimiento.id_guia" + 
							" left join `"+db+"`.equipo on equipo.id=movimiento.id_equipo" + 
							" left join `"+db+"`.grupo on grupo.id=equipo.id_grupo" + 
							" where bodegaEmpresa.esInterna=2 and year(guia.fecha)=?" + 
							" group by year(guia.fecha),month(guia.fecha),movimiento.id_tipoMovimiento,grupo.id" + 
							" order by grupo.nombre,guia.fecha;");
			smt5.setLong(1, anio);
			ResultSet rs5 = smt5.executeQuery();
			while (rs5.next()) {
				List<String> aux = new ArrayList<String>();
				aux.add(rs5.getString(1)); // anio
				aux.add(rs5.getString(2)); // mes
				aux.add(rs5.getString(3)); // idGrupo
				aux.add(rs5.getString(4)); // nombre grupo
				aux.add(rs5.getString(5)); // tipoMovimiento
				aux.add(rs5.getString(6)); // total en toneladas
				mapListaIdGrupo_Mes_Tipo.put(rs5.getString(3)+"_"+rs5.getString(2)+"_"+rs5.getString(5), aux);
			}
			rs5.close();
			smt5.close();
			
			List<Grupo> listGrupos = Grupo.allConEquipos (con,db);

			for(int i=0;i<listGrupos.size();i++) {
				List<String> aux = new ArrayList<String>();
				
				aux.add(listGrupos.get(i).nombre);
				Double auxKGsalida = (double)0;
				Double auxKGentrada = (double)0;
				Double auxNumero = (double)0;
				
				for(int j=1;j<13;j++) {
					List<String> lista1 = mapListaIdGrupo_Mes_Tipo.get(listGrupos.get(i).id+"_"+j+"_"+1);
					if(lista1==null) {
						auxNumero = (double)0;
					}else {
						auxNumero = Double.parseDouble(lista1.get(5));
					}
					aux.add(myformatdouble.format(auxNumero));
					auxKGsalida=auxKGsalida+auxNumero;
					
					List<String> lista2 = mapListaIdGrupo_Mes_Tipo.get(listGrupos.get(i).id+"_"+j+"_"+2);
					if(lista2==null) {
						auxNumero = (double)0;
					}else {
						auxNumero = Double.parseDouble(lista2.get(5));
					}
					aux.add(myformatdouble.format(auxNumero));
					auxKGentrada=auxKGentrada+auxNumero;
				}
				aux.add(myformatdouble.format(auxKGsalida));
				aux.add(myformatdouble.format(auxKGentrada));
				lista.add(aux);
			}
			
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		
		return (lista);
	}
	
	public static File reportGerenKGPorPeriodo1Excel(String db, Map<String,String> mapDiccionario, 
			List<String> cabecera1, List<String> cabecera2, List<List<String>> datos, String desde, String hasta) {
		
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
			cell.setCellValue("TONELADAS MOVIDAS: PERIODO DE "+ Fechas.DDMMAA(desde) + " A "+ Fechas.DDMMAA(hasta));
			
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
			for(int i=1; i<40; i++) {
				hoja1.setColumnWidth(i, 4*1000);
			}
			hoja1.setColumnWidth(1, 6*1000);
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
			
			for(int i=0; i<cabecera1.size(); i++){
				if(i == 0){
					posCell++;
					cell = row.createCell(posCell);
		            cell.setCellStyle(encabezado);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(cabecera1.get(i));
				}else{
					posCell++;
					int pos1 = posCell;
					cell = row.createCell(posCell);
		            cell.setCellStyle(encabezado);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(cabecera1.get(i));
					posCell++;
					int pos2 = posCell;
					cell = row.createCell(posCell);
					cell.setCellStyle(encabezado);
					hoja1.addMergedRegion(new CellRangeAddress(posRow, posRow, pos1, pos2));

				}
			}
			
			posRow++;
			row = hoja1.createRow(posRow);
			posCell = 0;
			
			for(int i=0; i<cabecera2.size(); i++){
				posCell++;
				cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(cabecera2.get(i));
			}
			
			for(int i=0; i<datos.size(); i++){
				posRow++;
				row = hoja1.createRow(posRow);
				posCell = 0;
				for(int j=0; j<datos.get(i).size(); j++){
					if(j == 0){
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(datos.get(i).get(j));
					}else{
						posCell++; 
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
			            Double aux = Double.parseDouble(datos.get(i).get(j).replaceAll(",", ""));
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(aux);
					}
				}
			}
			
			posRow++;
			row = hoja1.createRow(posRow);
			posCell = 0;
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(detalle);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TOTALES");
			
			
			
			Map<Long,String> mapColumn = GenParesColumnExcel.pares();
			
			for(int i=1; i<cabecera2.size(); i++){
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				String de = mapColumn.get((long)posCell) + 11;
				String a = mapColumn.get((long)posCell) + posRow;
				cell.setCellFormula("SUM(" + de + ":" + a + ")");
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
	
	public static String obtenerCoordenadaCelda(int fila, int columna) {
	    // Convertir el índice de columna a la letra de la columna
	    String letraColumna = Character.toString((char)('A' + columna - 1));
	    // Concatenar la letra de la columna y el número de fila para obtener la coordenada de la celda
	    return letraColumna + fila;
	}
	
	public static File reporteGerencialKGExcel(String db, Map<String,String> mapDiccionario, 
			List<List<String>> datosActual, List<List<String>> datosAnterior, List<List<String>> datosAnteriorAnterior, String anioActual, String anioAnterior, String anioAnteriorAnterior) {
		
		File tmp = TempFile.createTempFile("tmp","null");
		
		try {
			String path = "formatos/excel.xlsx";
			InputStream formato = Archivos.leerArchivo(path);
            Workbook libro = WorkbookFactory.create(formato);
            formato.close();
            
            // 0 negro 1 blanco 2 rojo 3 verde 4 azul 5 amarillo 
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
			cell.setCellValue("REPORTE TONELADAS MOVIDOS");
			
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
			
			
			// ENCABEZADO ANIO ACTUAL
			
			int posCell = 0;
			int posColl = 0;
			
			
			row = hoja1.createRow(8);
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 10*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TONELADAS MOVIDAS AÑO " + anioActual);
			
			for(int i=0; i<25; i++) {
				posCell++; posColl++;
				hoja1.setColumnWidth(posColl, 3*1000);
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
	            cell.setCellType(Cell.CELL_TYPE_STRING);
	            String mes = "";
	            switch (i) {
	            	case 0:  mes = "ENERO"; break;
	            	case 2:  mes = "FEBRERO"; break;
	            	case 4:  mes = "MARZO"; break;
	            	case 6:  mes = "ABRIL"; break;
	            	case 8:  mes = "MAYO"; break;
	            	case 10:  mes = "JUNIO"; break;
	            	case 12:  mes = "JULIO"; break;
	            	case 14:  mes = "AGOSTO"; break;
	            	case 16:  mes = "SEPTIEMBRE"; break;
	            	case 18:  mes = "OCTUBRE"; break;
	            	case 20:  mes = "NOVIEMBRE"; break;
	            	case 22:  mes = "DICIEMBRE"; break;
	            	case 24:  mes = "TOTALES"; break;
	            }
	            cell.setCellValue(mes);
			}
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
            cell.setCellType(Cell.CELL_TYPE_STRING);
			
			row = hoja1.createRow(9);
			posCell = 0;
			cell = row.createCell(1);
			cell.setCellStyle(encabezado);
			cell.setCellValue("FAMILIA/GRUPO");
			for (int i=0; i<25; i+=2) {
				cell = row.createCell(i+2);
				cell.setCellStyle(encabezado);
				cell.setCellValue("Salida");
				cell = row.createCell(i+3);
				cell.setCellStyle(encabezado);
				cell.setCellValue("Entrada");
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
	        anchor.setCol1(24);
	        anchor.setRow1(1);
			Picture img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);
			
			
			
			//DETALLE DE LA TABLA
			
			int posRow = 10;
			
			for(int i=0;i<datosActual.size();i++){
				row = hoja1.createRow(posRow++);
				posCell = 0;
				for(int j=0; j<datosActual.get(i).size(); j++){
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					Double suma = (double)0;
					if(j==0) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(datosActual.get(i).get(j));
					}else {
						Double aux = (double)0;
						String auxNum = "";
						auxNum = datosActual.get(i).get(j).trim();
						if(auxNum==null || auxNum.trim().length()<=0) {
							auxNum = "0";
						}
			            aux = Double.parseDouble(auxNum.replaceAll(",", ""));
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(aux);
						suma += aux;
					}
				}
			}
			
			row = hoja1.createRow(posRow++);
			posCell = 0;
			posCell++;
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
            cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TOTALES");
			for(int j=1;j<datosActual.get(2).size();j++){
				posCell++;
				Double suma=(double)0;
				for(int i=0;i<datosActual.size();i++){
					Double aux = (double)0;
					String auxNum = "";
					auxNum = datosActual.get(i).get(j).trim();
					if(auxNum==null || auxNum.trim().length()<=0) {
						auxNum = "0";
					}
					aux = Double.parseDouble(auxNum.replaceAll(",", ""));
					suma += aux;
				}
				cell = row.createCell(posCell);
				 cell.setCellStyle(encabezado);
				if(j==0) {
					cell.setCellValue("TOTALES");
				}else {
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue(suma);
				}
			}
			
			
			
			// ENCABEZADO ANIO ANTERIOR
			
			posRow += 2;
			
			row = hoja1.createRow(posRow);
			posCell = 0;
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TONELADAS MOVIDAS AÑO " + anioAnterior);
			
			for(int i=0; i<25; i++) {
				posCell++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
	            cell.setCellType(Cell.CELL_TYPE_STRING);
	            String mes = "";
	            switch (i) {
		            case 0:  mes = "ENERO"; break;
	            	case 2:  mes = "FEBRERO"; break;
	            	case 4:  mes = "MARZO"; break;
	            	case 6:  mes = "ABRIL"; break;
	            	case 8:  mes = "MAYO"; break;
	            	case 10:  mes = "JUNIO"; break;
	            	case 12:  mes = "JULIO"; break;
	            	case 14:  mes = "AGOSTO"; break;
	            	case 16:  mes = "SEPTIEMBRE"; break;
	            	case 18:  mes = "OCTUBRE"; break;
	            	case 20:  mes = "NOVIEMBRE"; break;
	            	case 22:  mes = "DICIEMBRE"; break;
	            	case 24:  mes = "TOTALES"; break;
	            }
	            cell.setCellValue(mes);
			}
			posCell++;
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
            cell.setCellType(Cell.CELL_TYPE_STRING);
			
			posRow++;
			row = hoja1.createRow(posRow);
			posCell = 0;
			cell = row.createCell(1);
			cell.setCellStyle(encabezado);
			cell.setCellValue("FAMILIA/GRUPO");
			for (int i=0; i<25; i+=2) {
				cell = row.createCell(i+2);
				cell.setCellStyle(encabezado);
				cell.setCellValue("Salida");
				cell = row.createCell(i+3);
				cell.setCellStyle(encabezado);
				cell.setCellValue("Entrada");
			}
			
			
			//DETALLE DE LA TABLA
			
			posRow++;
			
			for(int i=0;i<datosAnterior.size();i++){
				row = hoja1.createRow(posRow++);
				posCell = 0;
				for(int j=0; j<datosAnterior.get(i).size(); j++){
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					if(j==0) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(datosAnterior.get(i).get(j));
					}else {
						Double aux = (double)0;
						String auxNum = "";
						auxNum = datosAnterior.get(i).get(j).trim();
						if(auxNum==null || auxNum.trim().length()<=0) {
							auxNum = "0";
						}
			            aux = Double.parseDouble(auxNum.replaceAll(",", ""));
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(aux);
					}
				}
			}
			
			row = hoja1.createRow(posRow++);
			posCell = 0;
			posCell++;
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
            cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TOTALES");
			for(int j=1;j<datosAnterior.get(2).size();j++){
				posCell++;
				Double suma=(double)0;
				for(int i=0;i<datosAnterior.size();i++){
					Double aux = (double)0;
					String auxNum = "";
					auxNum = datosAnterior.get(i).get(j).trim();
					if(auxNum==null || auxNum.trim().length()<=0) {
						auxNum = "0";
					}
					aux = Double.parseDouble(auxNum.replaceAll(",", ""));
					suma += aux;
				}
				cell = row.createCell(posCell);
				 cell.setCellStyle(encabezado);
				if(j==0) {
					cell.setCellValue("TOTALES");
				}else {
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue(suma);
				}
			}
			
			
			// ENCABEZADO ANIO ANTERIOR ANTERIOR
			
			posRow += 2;
			
			row = hoja1.createRow(posRow);
			posCell = 0;
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TONELADAS MOVIDAS AÑO " + anioAnteriorAnterior);
			
			for(int i=0; i<25; i++) {
				posCell++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
	            cell.setCellType(Cell.CELL_TYPE_STRING);
	            String mes = "";
	            switch (i) {
		            case 0:  mes = "ENERO"; break;
	            	case 2:  mes = "FEBRERO"; break;
	            	case 4:  mes = "MARZO"; break;
	            	case 6:  mes = "ABRIL"; break;
	            	case 8:  mes = "MAYO"; break;
	            	case 10:  mes = "JUNIO"; break;
	            	case 12:  mes = "JULIO"; break;
	            	case 14:  mes = "AGOSTO"; break;
	            	case 16:  mes = "SEPTIEMBRE"; break;
	            	case 18:  mes = "OCTUBRE"; break;
	            	case 20:  mes = "NOVIEMBRE"; break;
	            	case 22:  mes = "DICIEMBRE"; break;
	            	case 24:  mes = "TOTALES"; break;
	            }
	            cell.setCellValue(mes);
			}
			posCell++;
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
            cell.setCellType(Cell.CELL_TYPE_STRING);
			
			posRow++;
			row = hoja1.createRow(posRow);
			posCell = 0;
			cell = row.createCell(1);
			cell.setCellStyle(encabezado);
			cell.setCellValue("FAMILIA/GRUPO");
			for (int i=0; i<25; i+=2) {
				cell = row.createCell(i+2);
				cell.setCellStyle(encabezado);
				cell.setCellValue("Salida");
				cell = row.createCell(i+3);
				cell.setCellStyle(encabezado);
				cell.setCellValue("Entrada");
			}
			
			
			//DETALLE DE LA TABLA
			
			posRow++;
			
			for(int i=0;i<datosAnteriorAnterior.size();i++){
				row = hoja1.createRow(posRow++);
				posCell = 0;
				for(int j=0; j<datosAnteriorAnterior.get(i).size(); j++){
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(detalle);
					if(j==0) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(datosAnteriorAnterior.get(i).get(j));
					}else {
						Double aux = (double)0;
						String auxNum = "";
						auxNum = datosAnteriorAnterior.get(i).get(j).trim();
						if(auxNum==null || auxNum.trim().length()<=0) {
							auxNum = "0";
						}
			            aux = Double.parseDouble(auxNum.replaceAll(",", ""));
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(aux);
					}
				}
			}
			
			row = hoja1.createRow(posRow++);
			posCell = 0;
			posCell++;
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
            cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TOTALES");
			for(int j=1;j<datosAnteriorAnterior.get(2).size();j++){
				posCell++;
				Double suma=(double)0;
				for(int i=0;i<datosAnteriorAnterior.size();i++){
					Double aux = (double)0;
					String auxNum = "";
					auxNum = datosAnteriorAnterior.get(i).get(j).trim();
					if(auxNum==null || auxNum.trim().length()<=0) {
						auxNum = "0";
					}
					aux = Double.parseDouble(auxNum.replaceAll(",", ""));
					suma += aux;
				}
				cell = row.createCell(posCell);
				 cell.setCellStyle(encabezado);
				if(j==0) {
					cell.setCellValue("TOTALES");
				}else {
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue(suma);
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
	
	
	
