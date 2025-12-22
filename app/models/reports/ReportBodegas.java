package models.reports;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.util.TempFile;

import models.tables.Cliente;
import models.tables.Comercial;
import models.tables.Proyecto;
import models.tables.Sucursal;
import models.utilities.Archivos;
import models.utilities.Fechas;

public class ReportBodegas {
	

	
	public static List<List<String>> estadoBodegas(Connection con, String db, String esPorSucursal, String id_sucursal) {
		List<List<String>> datos = new ArrayList<List<String>>();

		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and bodegaEmpresa.id_sucursal = " + id_sucursal;
		}

		try {
			List<List<String>> lista = new ArrayList<List<String>>();
			PreparedStatement smt = con.prepareStatement("select id_sucursal, id_cliente, id_proyecto, id_comercial, id_bodegaEmpresa," +
					" vigente, nombre, sum(if(id_tipoMovimiento=1,1,-1)*cantidad)"
					+ " from `"+db+"`.movimiento"
					+ " left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa"
					+ " where esVenta=0 and esInterna=2" + condSucursal
					+ " group by id_bodegaEmpresa;"); 
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				List<String> aux = new ArrayList<String>();
				aux.add(rs.getString(1)); //0 id_sucursal
				aux.add(rs.getString(2)); //1 id_cliente
				aux.add(rs.getString(3)); //2 id_proyecto
				aux.add(rs.getString(4)); //3 id_comercial
				aux.add(rs.getString(5)); //4 id_bodegaEmpresa
				aux.add(rs.getString(6)); //5 vigente
				aux.add(rs.getString(7)); //6 bodegaEmpresa.nombre
				aux.add(rs.getString(8)); //7 cant de equipos total
				lista.add(aux);
			}
			rs.close();
			smt.close();
			
			Map<String, List<String>> mapPrimerDespacho = new HashMap<String,List<String>>();
			PreparedStatement smt2 = con.prepareStatement("select id_bodegaEmpresa, numero, numGuiaCliente, fecha"
					+ " from `"+db+"`.movimiento"
					+ " left join `"+db+"`.guia on movimiento.id_guia = guia.id"
					+ " where id_guia>0 and id_tipoMovimiento=1 and esVenta=0 and fecha is not null"
					+ " group by id_bodegaEmpresa, id_guia;"); 
			ResultSet rs2 = smt2.executeQuery();
	
			while (rs2.next()) {
				List<String> aux = mapPrimerDespacho.get(rs2.getString(1));
				if(aux==null) {
					List<String> dePaso = new ArrayList<String>();
	            	dePaso.add(rs2.getString(2));
	            	dePaso.add(rs2.getString(3));
	            	dePaso.add(rs2.getString(4));
					mapPrimerDespacho.put(rs2.getString(1), dePaso);
				}else {
					java.sql.Date xRs = rs2.getDate(4);
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		            java.util.Date parsedDate;
					try {
						parsedDate = dateFormat.parse(aux.get(2));
						java.sql.Date xMap = new java.sql.Date(parsedDate.getTime());
			            if(xRs.compareTo(xMap) < 0) {
			            	List<String> dePaso = new ArrayList<String>();
			            	dePaso.add(rs2.getString(2));
			            	dePaso.add(rs2.getString(3));
			            	dePaso.add(rs2.getString(4));
							mapPrimerDespacho.put(rs2.getString(1), dePaso);
			            }
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
			rs2.close();
			smt2.close();
			
			Map<String, List<String>> mapUltimaDevol = new HashMap<String,List<String>>();
			PreparedStatement smt3 = con.prepareStatement("select id_bodegaEmpresa, numero, numGuiaCliente, fecha"
					+ " from `"+db+"`.movimiento"
					+ " left join `"+db+"`.guia on movimiento.id_guia = guia.id"
					+ " where id_guia>0 and id_tipoMovimiento=2 and esVenta=0 and fecha is not null"
					+ " group by id_bodegaEmpresa, id_guia;"); 
			ResultSet rs3 = smt3.executeQuery();
			while (rs3.next()) {
				List<String> aux = mapUltimaDevol.get(rs3.getString(1));
				if(aux==null) {
					List<String> dePaso = new ArrayList<String>();
	            	dePaso.add(rs3.getString(2));
	            	dePaso.add(rs3.getString(3));
	            	dePaso.add(rs3.getString(4));
	            	mapUltimaDevol.put(rs3.getString(1), dePaso);;
				}else {
					java.sql.Date xRs = rs3.getDate(4);
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date parsedDate;
					try {
						parsedDate = dateFormat.parse(aux.get(2));
						java.sql.Date xMap = new java.sql.Date(parsedDate.getTime());
			            if(xRs.compareTo(xMap) > 0) {
			            	List<String> dePaso = new ArrayList<String>();
			            	dePaso.add(rs3.getString(2));
			            	dePaso.add(rs3.getString(3));
			            	dePaso.add(rs3.getString(4));
			            	mapUltimaDevol.put(rs3.getString(1), dePaso);
			            }
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
			rs3.close();
			smt3.close();
			
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			Map<Long,Cliente> mapCliente = Cliente.mapAllClientes(con, db);
			Map<Long,Proyecto> mapProyecto = Proyecto.mapAllProyectos(con, db);
			Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
			
			
			lista.forEach(x->{
				String vigente = "Vigente";
				if(x.get(5).equals("0")) {
					vigente = "NO Vigente";
				}
				String nomSucursal = "";
				Sucursal sucursal = mapSucursal.get(Long.parseLong(x.get(0)));
				if(sucursal !=null) {
					nomSucursal = sucursal.getNombre();
				}
				String bodega = x.get(6);
				String nomClie = "";
				String rutClie = "";
				Cliente cliente = mapCliente.get(Long.parseLong(x.get(1)));
				if(cliente !=null) {
					nomClie = cliente.getNickName();
					rutClie = cliente.getRut();
				}
				String nomProyecto = "";
				Proyecto proyecto = mapProyecto.get(Long.parseLong(x.get(2)));
				if(proyecto !=null) {
					nomProyecto = proyecto.getNickName();
				}
				String nomComercial = "";
				Comercial comercial = mapComercial.get(Long.parseLong(x.get(3)));
				if(comercial !=null) {
					nomComercial = comercial.getNameUsuario();
				}
				String guiaPrimerDesp = "";
				String nroRefPrimerDesp = "";
				String fechPrimerDespacho = "";
				List<String> guiaPrimera = mapPrimerDespacho.get(x.get(4));
				if(guiaPrimera!=null) {
					guiaPrimerDesp = guiaPrimera.get(0);
					nroRefPrimerDesp = guiaPrimera.get(1);
					fechPrimerDespacho = guiaPrimera.get(2);
				}
				String guiaUltimoDesp = "";
				String nroRefUltimoDesp = "";
				String fechUltimoDespacho = "";
				List<String> guiaUltima = mapUltimaDevol.get(x.get(4));
				if(guiaUltima!=null) {
					guiaUltimoDesp = guiaUltima.get(0);
					nroRefUltimoDesp = guiaUltima.get(1);
					fechUltimoDespacho = guiaUltima.get(2);
				}
				String cantidad = x.get(7);
				Double auxCant = Double.parseDouble(cantidad);
				if(auxCant < 0) {
					cantidad = "0";
				}
				
				List<String> aux = new ArrayList<String>();
				
				aux.add(nomSucursal);
				aux.add(bodega);
				aux.add(vigente);
				aux.add(nomClie);
				aux.add(rutClie);
				aux.add(nomProyecto);
				aux.add(nomComercial);
				aux.add(guiaPrimerDesp);
				aux.add(nroRefPrimerDesp);
				aux.add(fechPrimerDespacho);
				aux.add(guiaUltimoDesp);
				aux.add(nroRefUltimoDesp);
				aux.add(fechUltimoDespacho);
				aux.add(cantidad);

				datos.add(aux);
				
			});
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(datos);
	}
	
	
	
	public static File estadoBodegasExcel(String db, Map<String,String> mapDiccionario, List<List<String>> datos) {
		
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


            
            
            //titulos del archivo
            
            libro.setSheetName(0, "report");
            Sheet hoja1 = libro.getSheetAt(0);
            
            Row row = null;
            Cell cell = null;
            
            row = hoja1.createRow(1);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellValue("REPORTE ESTADO "+mapDiccionario.get("BODEGAS")+"/PROYECTO (solo externas)");
			
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
				hoja1.setColumnWidth(i, 8*1000);
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
			cell.setCellValue("SUCURSAL");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue(mapDiccionario.get("BODEGA")+"/PROYECTO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("VIGENTE");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("NOMBRE CLIENTE");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("RUT CLIENTE");
			
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
			cell.setCellValue("NRO GUIA (PRIMER DESPACHO)");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("REF GUIA (PRIMER DESPACHO)");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("FECHA GUIA (PRIMER DESPACHO)");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("NRO GUIA (ULTIMA DEVOLUCION)");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("REF GUIA (ULTIMA DEVOLUCION)");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("FECHA GUIA (ULTIMA DEVOLUCION)");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("CANTIDAD EQUIPOS");
			
			
			for(List<String> lista: datos){
				posRow++;
				posCell = 0;
		        row = hoja1.createRow(posRow);
				
		        posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(0));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(1));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(2));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(3));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(4));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(5));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(6));
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            try {
	            	Double aux = Double.parseDouble(lista.get(7));
	 				cell.setCellValue(aux);
	            }catch(Exception e) {
					cell.setCellValue(lista.get(7));
	            }
	           
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(8));

				posCell++;
				cell = row.createCell(posCell);

				if(lista.get(9).trim().isEmpty()) {
					cell.setCellValue("");
				}else{
					Fechas fechax = Fechas.obtenerFechaDesdeStrAAMMDD(lista.get(9));
					cell.setCellValue(fechax.fechaUtil);
					cell.setCellStyle(fecha);
				}
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            try {
	            	Double aux = Double.parseDouble(lista.get(10));
	 				cell.setCellValue(aux);
	            }catch(Exception e) {
					cell.setCellValue(lista.get(10));
	            }
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(11));
				
				posCell++;
				cell = row.createCell(posCell);
				if(lista.get(12).trim().isEmpty()) {
					cell.setCellValue("");
				}else{
					Fechas fechax = Fechas.obtenerFechaDesdeStrAAMMDD(lista.get(12));
					cell.setCellValue(fechax.fechaUtil);
					cell.setCellStyle(fecha);
				}

				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            Double aux = Double.parseDouble(lista.get(13));
				cell.setCellValue(aux);
				
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
	
	
	
