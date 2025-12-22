package models.xlsx;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
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

import models.forms.FormCotizaOdo;
import models.tables.Cliente;
import models.tables.CotiOdo;
import models.tables.Proyecto;
import models.utilities.Archivos;
import models.utilities.Fechas;




public class CotiOdoEnExcel {

	
	
	
	public static File cotiOdoEnExcel(Connection con, String db, Long id_cotiOdo, Map<String,String> mapDiccionario){
		
		CotiOdo cotiOdo = CotiOdo.find(con, db,id_cotiOdo);
		List<List<String>> listadoServicios = FormCotizaOdo.listServiciosConValoresCoti(con, db, id_cotiOdo);
		
		Cliente cliente = Cliente.find(con, db, cotiOdo.getId_cliente());
		String nomCliente = "";
		if(cliente!=null) {
			nomCliente = cliente.getNickName();
		}
		Proyecto proyecto = Proyecto.find(con, db, cotiOdo.getId_proyecto());
		String nomProyecto = "";
		if(proyecto!=null) {
			nomProyecto = proyecto.getNickName();
		}
		
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
			cell.setCellValue("COTIZACION ODO");
			
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
			cell.setCellValue("NRO COTIZACION");
            cell = row.createCell(2);
			cell.setCellValue(cotiOdo.getNumero().toString());
			
			row = hoja1.createRow(6);
            cell = row.createCell(1);
			cell.setCellValue("FECHA COTIZACION");
            cell = row.createCell(2);
			cell.setCellValue(Fechas.DDMMAA(cotiOdo.getFecha()));
			
			row = hoja1.createRow(7);
            cell = row.createCell(1);
			cell.setCellValue("CLIENTE");
            cell = row.createCell(2);
			cell.setCellValue(nomCliente);
			
			row = hoja1.createRow(8);
            cell = row.createCell(1);
			cell.setCellValue("PROYECTO");
            cell = row.createCell(2);
			cell.setCellValue(nomProyecto);
            
            
			
			// encabezado de la tabla
			
						int posCell = 0;
						int posColl = 0;
						row = hoja1.createRow(10);
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 4*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellValue("FAMILIA");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 4*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellValue("CODIGO");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 10*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellValue("SERVICIO");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 1*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellValue("UN");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 4*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellValue("CANTIDAD");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 1*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellValue("MON");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 4*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellValue("PRECIO UNITARIO");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 4*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellValue("TOTAL");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 4*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellValue("APLICA MINIMO");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 4*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellValue("CANTIDAD MINIMO");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 4*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellValue("PRECIO ADICIONAL");

						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 12*1000);
						cell = row.createCell(posCell);
						cell.setCellStyle(encabezado);
						cell.setCellValue("EQUIPO ASOCIADO");
			
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
			
				//DETALLE DE LA TABLA
						int posRow = 11;
						Double total = (double)0;
						Double cant = (double)0;
								
						for(int i=0;i<listadoServicios.size();i++){
							Double cantidad = Double.parseDouble(listadoServicios.get(i).get(5).replaceAll(",", ""));
							Double precio = Double.parseDouble(listadoServicios.get(i).get(7).replaceAll(",", ""));
							Double ptotal = Double.parseDouble(listadoServicios.get(i).get(8).replaceAll(",", ""));
							Double cantMin = Double.parseDouble(listadoServicios.get(i).get(10).replaceAll(",", ""));
							Double preAdic = Double.parseDouble(listadoServicios.get(i).get(11).replaceAll(",", ""));
							
							if(cantidad > 0) {
								cant += cantidad;
								total += precio;
								
								String aplicaMinimo = "SI";
								if(listadoServicios.get(i).get(9).equals("0")){
									aplicaMinimo = "NO";
								}
								
								row = hoja1.createRow(posRow);
								posCell = 0;
								
								posCell++;
					            cell = row.createCell(posCell);
					            cell.setCellStyle(detalle);
								cell.setCellValue(listadoServicios.get(i).get(1));
								
								posCell++;
					            cell = row.createCell(posCell);
					            cell.setCellStyle(detalle);
								cell.setCellValue(listadoServicios.get(i).get(2));
								
								posCell++;
					            cell = row.createCell(posCell);
					            cell.setCellStyle(detalle);
								cell.setCellValue(listadoServicios.get(i).get(3));
								
								posCell++;
					            cell = row.createCell(posCell);
					            cell.setCellStyle(detalle);
								cell.setCellValue(listadoServicios.get(i).get(4));
								
								posCell++;
					            cell = row.createCell(posCell);
					            cell.setCellStyle(detalle);
								cell.setCellValue(cantidad);
								
								posCell++;
					            cell = row.createCell(posCell);
					            cell.setCellStyle(detalle);
								cell.setCellValue(listadoServicios.get(i).get(6));
								
								posCell++;
					            cell = row.createCell(posCell);
					            cell.setCellStyle(detalle);
								cell.setCellValue(precio);
								
								posCell++;
					            cell = row.createCell(posCell);
					            cell.setCellStyle(detalle);
								cell.setCellValue(ptotal);
								
								posCell++;
					            cell = row.createCell(posCell);
					            cell.setCellStyle(detalle);
								cell.setCellValue(aplicaMinimo);
								
								posCell++;
					            cell = row.createCell(posCell);
					            cell.setCellStyle(detalle);
								cell.setCellValue(cantMin);
								
								posCell++;
					            cell = row.createCell(posCell);
					            cell.setCellStyle(detalle);
								cell.setCellValue(preAdic);

								posCell++;
								cell = row.createCell(posCell);
								cell.setCellStyle(detalle);
								cell.setCellValue(listadoServicios.get(i).get(15)+" - "+listadoServicios.get(i).get(16));
								
								posRow++;
							}
			 			}
						
						
						if((double) cotiOdo.getDctoOdo() > (double) 0 ){
							
							row = hoja1.createRow(posRow);
							posCell = 0;
							
							posCell++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellValue("SUB-TOTALES");
							
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
							cell.setCellValue(cant);
							
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
							cell.setCellValue(total);
							
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
							
							posRow++;
							
							
							row = hoja1.createRow(posRow);
							posCell = 0;
							posCell++;
							
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellValue("DESCUENTO");
							
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
							cell.setCellValue("");
							
							posCell++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellValue("");
							
							posCell++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellValue(cotiOdo.getDctoOdo());
							
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
							
							posRow++;
						}
						
						Double totalConDcto = total*(1-cotiOdo.getDctoOdo());
						
						row = hoja1.createRow(posRow);
						posCell = 0;
						
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
						cell.setCellValue(cant);
						
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
						cell.setCellValue(totalConDcto);
						
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
		
		return(tmp);
	}
	
			
}
	
	
	
