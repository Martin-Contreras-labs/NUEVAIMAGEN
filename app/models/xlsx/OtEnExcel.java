package models.xlsx;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.text.DecimalFormat;
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

import models.tables.BodegaEmpresa;
import models.tables.CotizaDetalle;
import models.tables.Cotizacion;
import models.tables.Ot;
import models.tables.OtDespachado;
import models.utilities.Archivos;
import models.utilities.Fechas;




public class OtEnExcel {
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble4 = new DecimalFormat("#,##0.0000");
	static DecimalFormat myformatdouble6 = new DecimalFormat("#,##0.000000");
	
	
	
	public static File otEnExcel(Connection con, String db, Long id_ot, Map<String,String> mapDiccionario){
		
		Ot ot = Ot.find(con, db, id_ot);
		Cotizacion coti = Cotizacion.find(con, db, ot.getId_cotizacion());
		List<CotizaDetalle> detalleOrigen = CotizaDetalle.allPorIdCotizacion(con, db, ot.getId_cotizacion());
		List<List<String>> detalleDespacho = OtDespachado.listSumaDespachadoPorIdOtCantDesp(con, db, ot.getId());
		BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, db, coti.getId_bodegaEmpresa());
		
		
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
            
            CellStyle encabezado1 = libro.createCellStyle();
            Font font3 = libro.createFont();
            font3.setBoldweight(Font.BOLDWEIGHT_BOLD);
            encabezado1.setFont(font3);
            encabezado1.setAlignment(CellStyle.ALIGN_LEFT);
            
            CellStyle encabezado2 = libro.createCellStyle();
            Font font4 = libro.createFont();
            font3.setBoldweight(Font.BOLDWEIGHT_BOLD);
            encabezado2.setFont(font4);
            encabezado2.setBorderBottom(CellStyle.BORDER_THIN);
            encabezado2.setBorderTop(CellStyle.BORDER_THIN);
            encabezado2.setBorderRight(CellStyle.BORDER_THIN);
            encabezado2.setBorderLeft(CellStyle.BORDER_THIN);
            encabezado2.setAlignment(CellStyle.ALIGN_CENTER);
            
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
			cell.setCellValue(mapDiccionario.get("ORDEN_DE_TRABAJO"));
			
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
            
			row = hoja1.createRow(5);
            cell = row.createCell(1);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("NRO "+mapDiccionario.get("OT"));
            cell = row.createCell(2);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(ot.numero.toString());
			
			cell = row.createCell(4);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("NRO COTIZACION");
            cell = row.createCell(5);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(coti.numero.toString());
			
			row = hoja1.createRow(6);
            cell = row.createCell(1);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FECHA "+mapDiccionario.get("OT"));
            cell = row.createCell(2);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(Fechas.DDMMAA(ot.fecha));
			
			cell = row.createCell(4);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FECHA COTIZACION");
            cell = row.createCell(5);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(Fechas.DDMMAA(coti.fecha));
			
			row = hoja1.createRow(7);
            cell = row.createCell(1);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(mapDiccionario.get("BODEGA")+"/PROYECTO");
            cell = row.createCell(2);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(bodega.nombre);
			
			row = hoja1.createRow(8);
            cell = row.createCell(1);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("CLIENTE");
            cell = row.createCell(2);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(bodega.getNickCliente());
			
			row = hoja1.createRow(9);
            cell = row.createCell(1);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("PROYECTO");
            cell = row.createCell(2);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(bodega.getNickProyecto());
            
            
			
			// encabezado de la tabla
			
						int posCell = 0;
						int posColl = 0;
						
						posCell = 2;
						row = hoja1.createRow(11);
						
						posCell++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado1);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("ORDEN ORIGINAL");
						
						posCell++;
			            cell = row.createCell(posCell+3);
			            cell.setCellStyle(encabezado1);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("DESPACHADO A LA FECHA");
						
						
						posCell = 0;
						row = hoja1.createRow(12);
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 4*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("GRUPO");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 4*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("CODIGO");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 10*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("EQUIPO");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 1*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("UN");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 4*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("CANT");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 3*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("TOTAL KG");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 3*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("TIPO");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 3*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("SALDO");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 3*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("SALDO KG");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 4*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("CODIGO");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 10*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("EQUIPO");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 1*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("UN");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 3*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("CANT");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 3*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("EQUIV");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 3*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("EQUIV KG");
	
			
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
						
						Map<Long,Double> mapDespachado = OtDespachado.mapSumaDespachadoPorIdOtCantEquiv(con, db, ot.getId());
						
						Double sumCantOrigen = (double)0;
						Double sumKgOrigen = (double)0;
						Double sumSalCantOrigen = (double)0;
						Double sumSalKgOrigen = (double)0;
						
						Double sumCantDesp = (double)0;
						Double sumKgDesp = (double)0;
						
						Double sumCantEquiv = (double)0;
								
						for(int i=0;i<detalleOrigen.size();i++){
							
							Double cant = Double.parseDouble(detalleOrigen.get(i).getCantidad().replaceAll(",", ""));
							Double saldoPorDespachar = cant;
							Double desp = mapDespachado.get(detalleOrigen.get(i).getId_equipo());
							if(desp != null) {
								saldoPorDespachar = cant - desp;
								if((double)saldoPorDespachar < (double)0) {
									saldoPorDespachar = (double)0;
								}
							}
							
							String concepto = mapDiccionario.get("Arriendo");
							if((long)detalleOrigen.get(i).getEsVenta() == (long)1) {
								concepto = "Venta";
							}
						
							
							Double auxKg = Double.parseDouble(detalleOrigen.get(i).getTotalKG().trim().replaceAll(",", ""));
							Double auxKgSaldo = auxKg/cant * saldoPorDespachar;
							
							sumCantOrigen += cant;
							sumKgOrigen += auxKg;
							sumSalCantOrigen += saldoPorDespachar;
							sumSalKgOrigen += auxKgSaldo;
						
							row = hoja1.createRow(posRow);
							posCell = 0;
							
							posCell++;
				            cell = row.createCell(posCell);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(detalleOrigen.get(i).getGrupo());
							
							posCell++;
				            cell = row.createCell(posCell);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(detalleOrigen.get(i).getCodigo());
							
							posCell++;
				            cell = row.createCell(posCell);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(detalleOrigen.get(i).getEquipo());
							
							posCell++;
				            cell = row.createCell(posCell);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(detalleOrigen.get(i).getUnidad());
							
							posCell++;
				            cell = row.createCell(posCell);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(cant);
							
							posCell++;
				            cell = row.createCell(posCell);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(auxKg);
							
							posCell++;
				            cell = row.createCell(posCell);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(concepto);
							
							posCell++;
				            cell = row.createCell(posCell);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(saldoPorDespachar);
							
							posCell++;
				            cell = row.createCell(posCell);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(auxKgSaldo);
							
							int auxPosRow = posRow;
							posCell++;
							for(int k=0;k<detalleDespacho.size();k++){
								if(detalleOrigen.get(i).getId_equipo().toString().equals(detalleDespacho.get(k).get(1))) {
									row = hoja1.getRow(posRow);
						            cell = row.createCell(posCell);
									cell.setCellType(Cell.CELL_TYPE_STRING);
									cell.setCellValue(detalleDespacho.get(k).get(3));
									posRow++;
									row = hoja1.createRow(posRow);
								}
							}
							
							posRow = auxPosRow;
							posCell++;
							for(int k=0;k<detalleDespacho.size();k++){
								if(detalleOrigen.get(i).getId_equipo().toString().equals(detalleDespacho.get(k).get(1))) {
									row = hoja1.getRow(posRow);
						            cell = row.createCell(posCell);
									cell.setCellType(Cell.CELL_TYPE_STRING);
									cell.setCellValue(detalleDespacho.get(k).get(4));
									posRow++;
								}
							}
							
							posRow = auxPosRow;
							posCell++;
							for(int k=0;k<detalleDespacho.size();k++){
								if(detalleOrigen.get(i).getId_equipo().toString().equals(detalleDespacho.get(k).get(1))) {
									row = hoja1.getRow(posRow);
						            cell = row.createCell(posCell);
									cell.setCellType(Cell.CELL_TYPE_STRING);
									cell.setCellValue(detalleDespacho.get(k).get(5));
									posRow++;
								}
							}
							
							posRow = auxPosRow;
							posCell++;
							for(int k=0;k<detalleDespacho.size();k++){
								if(detalleOrigen.get(i).getId_equipo().toString().equals(detalleDespacho.get(k).get(1))) {
									row = hoja1.getRow(posRow);
									Double aux = Double.parseDouble(detalleDespacho.get(k).get(6).replaceAll(",", ""));
									
									Double cantDespachado = Double.parseDouble(detalleDespacho.get(k).get(6).trim().replaceAll(",", ""));
									sumCantDesp += cantDespachado;
									
						            cell = row.createCell(posCell);
									cell.setCellType(Cell.CELL_TYPE_NUMERIC);
									cell.setCellValue(aux);
									posRow++;
								}
							}
							
							posRow = auxPosRow;
							posCell++;
							for(int k=0;k<detalleDespacho.size();k++){
								if(detalleOrigen.get(i).getId_equipo().toString().equals(detalleDespacho.get(k).get(1))) {
									row = hoja1.getRow(posRow);
									Double aux = Double.parseDouble(detalleDespacho.get(k).get(7).replaceAll(",", ""));
									
									Double cantEquiv = Double.parseDouble(detalleDespacho.get(k).get(7).trim().replaceAll(",", ""));
									sumCantEquiv += cantEquiv;
									
						            cell = row.createCell(posCell);
									cell.setCellType(Cell.CELL_TYPE_NUMERIC);
									cell.setCellValue(aux);
									posRow++;
								}
							}
							
							posRow = auxPosRow;
							posCell++;
							for(int k=0;k<detalleDespacho.size();k++){
								if(detalleOrigen.get(i).getId_equipo().toString().equals(detalleDespacho.get(k).get(1))) {
									row = hoja1.getRow(posRow);
									Double aux = Double.parseDouble(detalleDespacho.get(k).get(8).replaceAll(",", ""));
									
									Double kgDespachado = Double.parseDouble(detalleDespacho.get(k).get(8).trim().replaceAll(",", ""));
									sumKgDesp += kgDespachado;
									
						            cell = row.createCell(posCell);
									cell.setCellType(Cell.CELL_TYPE_NUMERIC);
									cell.setCellValue(aux);
									posRow++;
								}
							}
							
							if(detalleDespacho.size()==0 || auxPosRow == posRow) {
								posRow++;
							}
							
							
			 			}
						posRow++;
						
						posCell = 0;
						row = hoja1.createRow(posRow);
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("");
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("");
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("TOTALES");
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("");
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(sumCantOrigen);
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(sumKgOrigen);
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("");
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(sumSalCantOrigen);
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(sumSalKgOrigen);
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("");
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("");
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("");
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(sumCantDesp);
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(sumCantEquiv);
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(sumKgDesp);
						
						
						
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
		
		return(tmp);
	}
	
			
}
	
	
	
