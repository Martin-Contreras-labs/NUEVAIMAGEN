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
import models.tables.BodegaEmpresa;
import models.tables.Cliente;
import models.tables.CotiOdo;
import models.tables.OtOdo;
import models.tables.Proyecto;
import models.tables.VentaServicio;
import models.utilities.Archivos;
import models.utilities.DecimalFormato;
import models.utilities.Fechas;




public class OtOdoEnExcel {
	
	
	
	public static File otOdoEnExcel(Connection con, String db, Long id_otOdo, Map<String,String> mapDiccionario){
		
		String vista="";
		OtOdo otOdo = OtOdo.find(con, db, id_otOdo);
		CotiOdo cotiOdo = CotiOdo.find(con, db, otOdo.getId_cotiOdo());
		Long id_cotiOdo = otOdo.getId_cotiOdo();
		Map<Long,Double> mapVentas = VentaServicio.mapTotalCantPorServ(con, db, id_otOdo);
		
		String fecha = Fechas.AAMMDD(cotiOdo.getFecha());
		Cliente cliente = Cliente.find(con, db, cotiOdo.getId_cliente());
		String rutCliente = "";
		String nomCliente = "";
		if(cliente!=null) {
			rutCliente = cliente.getRut();
			nomCliente = cliente.getNickName();
		}
		Proyecto proyecto = Proyecto.find(con, db, cotiOdo.getId_proyecto());
		String nomProyecto = "";
		if(proyecto!=null) {
			nomProyecto = proyecto.getNickName();
		}
		
		FormCotizaOdo formCotizaOdo = new FormCotizaOdo(cotiOdo, fecha, rutCliente, nomCliente, nomProyecto);
		formCotizaOdo.id_cotiOdo = id_cotiOdo;
		formCotizaOdo.dctoOdo = DecimalFormato.formato(cotiOdo.getDctoOdo()*100, (long)2);
		
		List<List<String>> listadoServicios = FormCotizaOdo.listServiciosConValoresCoti(con, db, id_cotiOdo);
		
		
		BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, db, cotiOdo.getId_bodegaEmpresa());
		
		
		File tmp = null;
try{
	tmp = TempFile.createTempFile("tmp","null");
}catch(Exception e){}
		
		try {
			String path = "formatos/excel.xlsx";
			InputStream formato = Archivos.leerArchivo(path);
            Workbook libro = WorkbookFactory.create(formato);
            formato.close();
            
            // 0 negro 1 blanco 2 rojo 3 verde 4 azul 5 amarillo 
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
            
            CellStyle encabezado1 = libro.createCellStyle();
            Font font3 = libro.createFont();
            font3.setBold(true);
            encabezado1.setFont(font3);
            encabezado1.setAlignment(HorizontalAlignment.LEFT);
            
            CellStyle encabezado2 = libro.createCellStyle();
            Font font4 = libro.createFont();
            font3.setBold(true);
            encabezado2.setFont(font4);
            encabezado2.setBorderBottom(BorderStyle.THIN);
            encabezado2.setBorderTop(BorderStyle.THIN);
            encabezado2.setBorderRight(BorderStyle.THIN);
            encabezado2.setBorderLeft(BorderStyle.THIN);
            encabezado2.setAlignment(HorizontalAlignment.CENTER);
            
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
			cell.setCellValue(mapDiccionario.get("ORDEN_DE_TRABAJO"));
			
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
			cell.setCellValue("NRO "+mapDiccionario.get("OT"));
            cell = row.createCell(2);
			cell.setCellValue(otOdo.numero.toString());
			
			cell = row.createCell(4);
			cell.setCellValue("NRO COTIZACION");
            cell = row.createCell(5);
			cell.setCellValue(cotiOdo.numero.toString());
			
			row = hoja1.createRow(6);
            cell = row.createCell(1);
			cell.setCellValue("FECHA "+mapDiccionario.get("OT"));
            cell = row.createCell(2);
			cell.setCellValue(Fechas.DDMMAA(otOdo.fecha));
			
			cell = row.createCell(4);
			cell.setCellValue("FECHA COTIZACION");
            cell = row.createCell(5);
			cell.setCellValue(Fechas.DDMMAA(cotiOdo.fecha));
			
			row = hoja1.createRow(7);
            cell = row.createCell(1);
			cell.setCellValue(mapDiccionario.get("BODEGA")+"/PROYECTO");
            cell = row.createCell(2);
			cell.setCellValue(bodega.nombre);
			
			row = hoja1.createRow(8);
            cell = row.createCell(1);
			cell.setCellValue("CLIENTE");
            cell = row.createCell(2);
			cell.setCellValue(bodega.getNickCliente());
			
			row = hoja1.createRow(9);
            cell = row.createCell(1);
			cell.setCellValue("PROYECTO");
            cell = row.createCell(2);
			cell.setCellValue(bodega.getNickProyecto());
            
            
			
			// encabezado de la tabla
			
						int posCell = 0;
						int posColl = 0;
						
						posCell = 2;
						row = hoja1.createRow(11);
						
						posCell++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado1);
						cell.setCellValue("ORDEN ORIGINAL");
						
						posCell++;
			            cell = row.createCell(posCell+3);
			            cell.setCellStyle(encabezado1);
						cell.setCellValue("VENTAS A LA FECHA");
						
						
						posCell = 0;
						row = hoja1.createRow(12);
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 4*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellValue("FAMILIA");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 4*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellValue("CODIGO");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 10*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellValue("SERVICIO");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 2*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellValue("UN");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 4*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellValue("CANTIDAD");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 2*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellValue("MON");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 4*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellValue("PRECIO UNITARIO");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 4*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellValue("TOTAL");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 4*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellValue("APLICA MINIOM");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 4*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellValue("CANTIDAD MINIMO");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 4*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellValue("PRECIO ADICIONAL");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 4*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellValue("CANTIDAD");

						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 13*1000);
						cell = row.createCell(posCell);
						cell.setCellStyle(encabezado2);
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
				        anchor.setCol1(9);
				        anchor.setRow1(1);
						Picture img = draw.createPicture(anchor, pngIndex);
						img.resize(0.4);
						hoja1.createFreezePane(0, 0, 0,0);
			
				//DETALLE DE LA TABLA
						int posRow = 13;
						
						Double total = (double)0;
						Double cant = (double)0;
						Double totalCantVendida = (double)0;
								
						for(int i=0;i<listadoServicios.size();i++){
							Double cantidad = Double.parseDouble(listadoServicios.get(i).get(5).replaceAll(",", ""));
							if(cantidad > 0) {
								
								cant += cantidad;
								total += Double.parseDouble(listadoServicios.get(i).get(8).replaceAll(",", ""));
								
								Long id_servicio = Long.parseLong(listadoServicios.get(i).get(0));
								Double cantVendida = mapVentas.get(id_servicio);
								
								if(cantVendida==null) {
									cantVendida = (double)0;
								}
								totalCantVendida += cantVendida;
								
								String aplicaMinimo = "SI";
								if(listadoServicios.get(i).get(9).equals("0")){
									aplicaMinimo = vista + "NO";
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
								cell.setCellValue(Double.parseDouble(listadoServicios.get(i).get(7).replaceAll(",", "")));
								
								posCell++;
					            cell = row.createCell(posCell);
								cell.setCellStyle(detalle);
								cell.setCellValue(Double.parseDouble(listadoServicios.get(i).get(8).replaceAll(",", "")));
								
								posCell++;
					            cell = row.createCell(posCell);
								cell.setCellStyle(detalle);
								cell.setCellValue(aplicaMinimo);
								
								posCell++;
					            cell = row.createCell(posCell);
								cell.setCellStyle(detalle);
								cell.setCellValue(Double.parseDouble(listadoServicios.get(i).get(10).replaceAll(",", "")));
								
								posCell++;
					            cell = row.createCell(posCell);
								cell.setCellStyle(detalle);
								cell.setCellValue(Double.parseDouble(listadoServicios.get(i).get(11).replaceAll(",", "")));
								
								posCell++;
					            cell = row.createCell(posCell);
								cell.setCellStyle(detalle);
								cell.setCellValue(cantVendida);
								posRow++;

								posCell++;
								cell = row.createCell(posCell);
								cell.setCellStyle(detalle);
								cell.setCellValue(listadoServicios.get(i).get(15)+" - "+listadoServicios.get(i).get(16));
							}
							
							
			 			}
						
						Double dcto = Double.parseDouble(formCotizaOdo.dctoOdo.replaceAll(",", "")) / 100;
						
						if((double) cotiOdo.getDctoOdo() > (double) 0 ){
							
							
							posCell = 0;
							row = hoja1.createRow(posRow);
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellValue("SUB-TOTALES");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellValue(cant);
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellValue(total);
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellValue(totalCantVendida);

							posCell++; posColl++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							cell.setCellValue("");
							
							posRow++;
							
							posCell = 0;
							row = hoja1.createRow(posRow);
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellValue("DESCUENTO");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellValue(dcto);
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellValue("");

							posCell++; posColl++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							cell.setCellValue("");

						}
						
						posRow++;
						
						posCell = 0;
						row = hoja1.createRow(posRow);
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellValue("TOTALES");
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellValue("");
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellValue("");
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellValue("");
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellValue(cant);
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellValue("");
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellValue("");
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellValue(total*(1-dcto));
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellValue("");
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellValue("");
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellValue("");
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellValue(totalCantVendida);

						posCell++; posColl++;
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
	
	
	
