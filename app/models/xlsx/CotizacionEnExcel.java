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
import models.tables.Cliente;
import models.tables.CotizaDetalle;
import models.tables.Cotizacion;
import models.tables.EmisorTributario;
import models.tables.Equipo;
import models.tables.Proyecto;
import models.tables.Sucursal;
import models.utilities.Archivos;
import models.utilities.Fechas;




public class CotizacionEnExcel {
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble4 = new DecimalFormat("#,##0.0000");
	static DecimalFormat myformatdouble6 = new DecimalFormat("#,##0.000000");
	
	
	
	public static File cotizacionEnExcel(Connection con, String db, Long id_cotizacion, Map<String,String> mapDiccionario, Map<String,String> mapeoPermiso){
		
		Cotizacion cotizacion = Cotizacion.find(con, db,id_cotizacion);
		List<CotizaDetalle> detalleCoti = CotizaDetalle.allPorIdCotizacion(con, db,id_cotizacion);
		Cliente cliente = Cliente.find(con, db, cotizacion.getId_cliente());
		String nickCliente = "";
		if(cliente!=null) {
			nickCliente = cliente.getNickName();
		}
		Proyecto proyecto = Proyecto.find(con, db, cotizacion.getId_proyecto());
		String nickProyecto = "";
		if(proyecto!=null) {
			nickProyecto = proyecto.getNickName();
		}
		
		
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
			cell.setCellValue("COTIZACION");
			
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
			cell.setCellValue("NRO COTIZACION");
            cell = row.createCell(2);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(cotizacion.numero.toString());
			
			row = hoja1.createRow(6);
            cell = row.createCell(1);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FECHA COTIZACION");
            cell = row.createCell(2);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(Fechas.DDMMAA(cotizacion.fecha));
			
			row = hoja1.createRow(7);
            cell = row.createCell(1);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("CLIENTE");
            cell = row.createCell(2);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(nickCliente);
			
			row = hoja1.createRow(8);
            cell = row.createCell(1);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("PROYECTO");
            cell = row.createCell(2);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(nickProyecto);
            
            
			
			// encabezado de la tabla
			
						int posCell = 0;
						int posColl = 0;
						row = hoja1.createRow(10);
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 4*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("GRUPO");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 4*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("CODIGO");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 10*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("EQUIPO");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 1*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("UN");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 3*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("CANT");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 3*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("CONCEPTO");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 1*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("MON");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 3*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("P.UNIT VTA/REPOS");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 3*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("UN "+mapDiccionario.get("ARR"));
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 3*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("P.UNIT "+mapDiccionario.get("ARR"));
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 3*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("PERM");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 3*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("P.TOTAL REPOS");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 3*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("P.TOTAL "+mapDiccionario.get("ARR"));
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 3*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("P.TOTAL VENTA");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 3*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("KG");
						
						if( !(mapeoPermiso.get("parametro.escondeLosM2")!=null && mapeoPermiso.get("parametro.escondeLosM2").equals("1")) ) {
							posCell++; posColl++;
							hoja1.setColumnWidth(posColl, 3*1000);
				            cell = row.createCell(posCell);
				            cell.setCellStyle(encabezado);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("M2");
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
				        anchor.setCol1(14);
				        anchor.setRow1(1);
						Picture img = draw.createPicture(anchor, pngIndex);
						img.resize(0.4);
						hoja1.createFreezePane(0, 0, 0,0);
			
				//DETALLE DE LA TABLA
						int posRow = 11;
						Double totalReposicion=(double)0;
						Double totalArriendo=(double)0;
						Double totalVenta=(double)0;
						Double totalKG=(double)0;
						Double totalM2=(double)0;
						Double totaCantidad=(double)0;
								
						for(int i=0;i<detalleCoti.size();i++){
							
							Double totRep = (double)0;
							String auxNum = detalleCoti.get(i).totalReposicion.trim();
				    		if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
							try {totRep = myformatdouble.parse(auxNum).doubleValue();}catch(Exception e) {}
							if((long)detalleCoti.get(i).esVenta == (long)0) {
								totalReposicion = totalReposicion + totRep;
							}else {
								totRep = (double)0;
							}
							
							Double totArr = (double)0;
							auxNum = detalleCoti.get(i).totalArriendo.trim();
				    		if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
							try {totArr = myformatdouble.parse(auxNum).doubleValue();}catch(Exception e) {}
							totalArriendo = totalArriendo + totArr;
							
							Double totVta = (double)0;
							auxNum = detalleCoti.get(i).totalVenta.trim();
				    		if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
							try {totVta = myformatdouble.parse(auxNum).doubleValue();}catch(Exception e) {}
							totalVenta = totalVenta + totVta;
							
							Double totKg = (double)0;
							auxNum = detalleCoti.get(i).totalKG.trim();
				    		if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
							try {totKg = myformatdouble.parse(auxNum).doubleValue();}catch(Exception e) {}
							totalKG = totalKG + totKg;
							
							Double totM2 = (double)0;
							auxNum = detalleCoti.get(i).totalM2.trim();
				    		if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
							try {totM2 = myformatdouble.parse(auxNum).doubleValue();}catch(Exception e) {}
							totalM2 = totalM2 + totM2;
							
							Double cantidad = (double)0;
							auxNum = detalleCoti.get(i).cantidad.trim();
				    		if( auxNum == null || auxNum.trim().length()<=0) auxNum = "0";
							try { cantidad = myformatdouble.parse(auxNum).doubleValue(); }catch(Exception e) {}
							totaCantidad = totaCantidad + cantidad;
							
							Double precioReposicion = (double)0;
							auxNum = detalleCoti.get(i).precioReposicion.trim();
				    		if( auxNum == null || auxNum.trim().length()<=0) auxNum = "0";
							try { precioReposicion = myformatdouble.parse(auxNum).doubleValue(); }catch(Exception e) {}
							
							Double precioArriendo = (double)0;
							auxNum = detalleCoti.get(i).precioArriendo.trim();
				    		if( auxNum == null || auxNum.trim().length()<=0) auxNum = "0";
							try { precioArriendo = myformatdouble.parse(auxNum).doubleValue(); }catch(Exception e) {}
							
							
							
							
							
							String concepto = "Venta";
							if((long)detalleCoti.get(i).esVenta == (long)0){
								concepto = mapDiccionario.get("Arriendo");
							}
						
						
							row = hoja1.createRow(posRow);
							posCell = 0;
							posColl = 0;
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(detalleCoti.get(i).grupo);
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(detalleCoti.get(i).codigo);
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(detalleCoti.get(i).equipo);
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(detalleCoti.get(i).unidad);
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(cantidad);
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(concepto);
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(detalleCoti.get(i).moneda);
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(precioReposicion);
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(detalleCoti.get(i).unidadTiempo);
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(precioArriendo);
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(detalleCoti.get(i).permanencia);
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(totRep);
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(totArr);
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(totVta);
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(totKg);
							
							if( !(mapeoPermiso.get("parametro.escondeLosM2")!=null && mapeoPermiso.get("parametro.escondeLosM2").equals("1")) ) {
								posCell++; posColl++;
					            cell = row.createCell(posCell);
					            cell.setCellStyle(detalle);
								cell.setCellType(Cell.CELL_TYPE_NUMERIC);
								cell.setCellValue(totM2);
							}
							
							posRow++;
			 			}
						
						if((double) cotizacion.getDctoArriendo() > (double) 0 || (double) cotizacion.getDctoVenta() > (double) 0){
							row = hoja1.createRow(posRow);
							posCell = 0;
							posColl = 0;
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("SUBTOTAL");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(totalReposicion);
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(totalArriendo);
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(totalVenta);
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							
							if( !(mapeoPermiso.get("parametro.escondeLosM2")!=null && mapeoPermiso.get("parametro.escondeLosM2").equals("1")) ) {
								posCell++; posColl++;
					            cell = row.createCell(posCell);
					            cell.setCellStyle(detalle);
							}
							
							posRow++;
							
							row = hoja1.createRow(posRow);
							posCell = 0;
							posColl = 0;
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("DESCUENTOS");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(cotizacion.getDctoArriendo());
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(cotizacion.getDctoVenta());
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							
							if( !(mapeoPermiso.get("parametro.escondeLosM2")!=null && mapeoPermiso.get("parametro.escondeLosM2").equals("1")) ) {
								posCell++; posColl++;
					            cell = row.createCell(posCell);
					            cell.setCellStyle(detalle);
							}
							
							posRow++;
							
						}
						
						Double totalArriendoConDcto = totalArriendo*(1-cotizacion.getDctoArriendo());
						Double totalVentaConDcto = totalVenta*(1-cotizacion.getDctoVenta());
						
						row = hoja1.createRow(posRow);
						posCell = 0;
						posColl = 0;
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("");
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("");
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("");
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("");
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(totaCantidad);
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("");
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("");
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("");
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("");
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("");
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("TOTAL");
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(totalReposicion);
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(totalArriendoConDcto);
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(totalVentaConDcto);
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(totalKG);
						
						if( !(mapeoPermiso.get("parametro.escondeLosM2")!=null && mapeoPermiso.get("parametro.escondeLosM2").equals("1")) ) {
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(totalM2);
						}
						
						posRow++;
						
						if(mapeoPermiso.get("parametro.cotizaciones-con-iva")!=null && mapeoPermiso.get("parametro.cotizaciones-con-iva").equals("1")) {
							EmisorTributario emisor = EmisorTributario.find(con, db);
							
							row = hoja1.createRow(posRow);
							posCell = 0;
							posColl = 0;
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("IVA");
							
							
							Double tasaIva = emisor.getTasaIva()/100;
							
							Sucursal sucursal = Sucursal.find(con, db, cotizacion.getId_sucursal().toString());
							
							if(mapeoPermiso.get("parametro.ivaPorBodega")!=null && mapeoPermiso.get("parametro.ivaPorBodega").equals("1")) {
					        	if(cotizacion.getId_bodegaEmpresa()>0) {
					        		BodegaEmpresa bodegaEmpresa = BodegaEmpresa.findXIdBodega(con, db, cotizacion.getId_bodegaEmpresa());
					        		if(bodegaEmpresa!=null && bodegaEmpresa.getIvaBodega() > 0) {
					        			tasaIva = bodegaEmpresa.getIvaBodega();
					        		}else {
					        			if(sucursal.getIvaSucursal() > 0) {
					        				tasaIva = sucursal.getIvaSucursal();
					        			}
					        		}
					        	}else {
					        		if(sucursal.getIvaSucursal() > 0) {
				        				tasaIva = sucursal.getIvaSucursal();
				        			}
					        	}
					        }
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(totalReposicion*tasaIva);
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(totalArriendoConDcto*tasaIva);
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(totalVentaConDcto*tasaIva);
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							
							if( !(mapeoPermiso.get("parametro.escondeLosM2")!=null && mapeoPermiso.get("parametro.escondeLosM2").equals("1")) ) {
								posCell++; posColl++;
					            cell = row.createCell(posCell);
					            cell.setCellStyle(detalle);
							}
							
							posRow++;
							
							
							row = hoja1.createRow(posRow);
							posCell = 0;
							posColl = 0;
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("TOTAL CON IVA");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(totalReposicion*(1+tasaIva));
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(totalArriendoConDcto*(1+tasaIva));
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(totalVentaConDcto*(1+tasaIva));
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							
							if( !(mapeoPermiso.get("parametro.escondeLosM2")!=null && mapeoPermiso.get("parametro.escondeLosM2").equals("1")) ) {
								posCell++; posColl++;
					            cell = row.createCell(posCell);
					            cell.setCellStyle(detalle);
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
		
		return(tmp);
	}
	
	
	public static File reportOtSaldosExcel(String db, Map<String,String> mapDiccionario, Map<String,Double> mapAllPorDespachar, Map<String,Double> mapAllStockBodInt, List<List<String>> listaBodExternasVigentes, List<List<String>> listaBodInternas, List<Equipo> listaEquipos){
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
            
            CellStyle detallePintado = libro.createCellStyle();
            detallePintado.setBorderBottom(CellStyle.BORDER_THIN);
            detallePintado.setBorderTop(CellStyle.BORDER_THIN);
            detallePintado.setBorderRight(CellStyle.BORDER_THIN);
            detallePintado.setBorderLeft(CellStyle.BORDER_THIN);
            detallePintado.setFillPattern(CellStyle.SOLID_FOREGROUND);
            detallePintado.setFillForegroundColor((short)19);
            
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
			cell.setCellValue("SALDOS POR DESPACHAR (SOLO "+mapDiccionario.get("BODEGA")+" VIGENTES) VS STOCK EN "+mapDiccionario.get("BODEGA")+" INTERNAS");
			
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
			
			
			// encabezado de la tabla
			
			int posCell = 0;
			int posColl = 0;
			row = hoja1.createRow(6);
			
			posCell = 4;
            cell = row.createCell(posCell);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("INTERNAS");
			
			posCell = posCell + listaBodInternas.size()+1;
            cell = row.createCell(posCell);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("EXTERNAS");
			
			
			posCell = 0;
			posColl = 0;
			row = hoja1.createRow(7);
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("GRUPO");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("CODIGO");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 10*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("EQUIPO");
			
			for(List<String> x: listaBodInternas) {
				posCell++; posColl++;
				hoja1.setColumnWidth(posColl, 1500);
	            cell = row.createCell(posCell);
	            cell.setCellStyle(rotateText);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(x.get(5));
			}
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 1500);
            cell = row.createCell(posCell);
            cell.setCellStyle(rotateText);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TOTAL STOCK");
			
			for(List<String> x: listaBodExternasVigentes) {
				posCell++; posColl++;
				hoja1.setColumnWidth(posColl, 1500);
	            cell = row.createCell(posCell);
	            cell.setCellStyle(rotateText);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(x.get(1));
			}
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 1500);
            cell = row.createCell(posCell);
            cell.setCellStyle(rotateText);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("PENDIENTE X DESPACHAR");
			
		
			//INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
			InputStream xz = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
            byte[] bytes = IOUtils.toByteArray(xz);
            xz.close();
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
			
			
			posCell = 0;
			posColl = 0;
			row = hoja1.createRow(8);
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("CLIENTE");
			
			for(@SuppressWarnings("unused") List<String> x: listaBodInternas) {
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("");
			}
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			for(List<String> x: listaBodExternasVigentes) {
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(x.get(2));
			}
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			
			posCell = 0;
			posColl = 0;
			row = hoja1.createRow(9);
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("SUCURSAL");
			
			for(List<String> x: listaBodInternas) {
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(x.get(16));
			}
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			for(List<String> x: listaBodExternasVigentes) {
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(x.get(3));
			}
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			posCell = 0;
			posColl = 0;
			row = hoja1.createRow(10);
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("COMERCIAL");
			
			for(List<String> x: listaBodInternas) {
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(x.get(11));
			}
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			for(List<String> x: listaBodExternasVigentes) {
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(x.get(4));
			}
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			
			//DETALLE DE LA TABLA
			int posRow = 11;
			for(Equipo q: listaEquipos) {
				row = hoja1.createRow(posRow);
				posCell = 0;
				
				posCell++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(q.getGrupo());
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(q.getCodigo());
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(q.getNombre());
				
				Double subTotalInt = (double)0;
				for(List<String> x: listaBodInternas) {
					Double aux = mapAllStockBodInt.get(x.get(1)+"_"+q.getId());
					if(aux != null) {
						subTotalInt += aux;
					}else {
						aux = (double)0;
					}
					posCell++;
		            cell = row.createCell(posCell);
		            cell.setCellStyle(detalle);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue(aux);
				}
				posCell++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detallePintado);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(subTotalInt);
				
				Double subTotalDesp = (double)0;
				for(List<String> x: listaBodExternasVigentes) {
					Double aux = mapAllPorDespachar.get(x.get(0)+"_"+q.getId());
					if(aux != null) {
						subTotalDesp += aux;
					}else {
						aux = (double)0;
					}
					posCell++;
		            cell = row.createCell(posCell);
		            cell.setCellStyle(detalle);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue(aux);
				}
				posCell++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detallePintado);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(subTotalDesp);
				
				Double totalTotal = subTotalInt + subTotalDesp;
				if(totalTotal > 0) {
					posRow++;
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
		return(tmp);
	}
	
	public static File reportOtSaldosPorOtExcel(String db, Map<String,String> mapDiccionario, Map<String,Double> mapAllPorDespachar, Map<String,Double> mapAllStockBodInt, List<List<String>> listaBodExternasVigentes, List<List<String>> listaBodInternas, List<Equipo> listaEquipos){
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
            
            CellStyle detallePintado = libro.createCellStyle();
            detallePintado.setBorderBottom(CellStyle.BORDER_THIN);
            detallePintado.setBorderTop(CellStyle.BORDER_THIN);
            detallePintado.setBorderRight(CellStyle.BORDER_THIN);
            detallePintado.setBorderLeft(CellStyle.BORDER_THIN);
            detallePintado.setFillPattern(CellStyle.SOLID_FOREGROUND);
            detallePintado.setFillForegroundColor((short)19);
            
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
			cell.setCellValue("SALDOS POR DESPACHAR POR OT (SOLO "+mapDiccionario.get("BODEGA")+" VIGENTES) VS STOCK EN "+mapDiccionario.get("BODEGA")+" INTERNAS");
			
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
			
			
			// encabezado de la tabla
			
			int posCell = 0;
			int posColl = 0;
			row = hoja1.createRow(6);
			
			posCell = 5;
            cell = row.createCell(posCell);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("INTERNAS");
			
			posCell = posCell + listaBodInternas.size()+1;
            cell = row.createCell(posCell);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("EXTERNAS");
			
			
			posCell = 0;
			posColl = 0;
			row = hoja1.createRow(7);
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("GRUPO");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("CODIGO");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 10*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("EQUIPO");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("KG");
			
			for(List<String> x: listaBodInternas) {
				posCell++; posColl++;
				hoja1.setColumnWidth(posColl, 1500);
	            cell = row.createCell(posCell);
	            cell.setCellStyle(rotateText);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(x.get(5));
			}
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 1500);
            cell = row.createCell(posCell);
            cell.setCellStyle(rotateText);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TOTAL STOCK");
			
			for(List<String> x: listaBodExternasVigentes) {
				posCell++; posColl++;
				hoja1.setColumnWidth(posColl, 1500);
	            cell = row.createCell(posCell);
	            cell.setCellStyle(rotateText);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(x.get(1));
			}
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 1500);
            cell = row.createCell(posCell);
            cell.setCellStyle(rotateText);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("PENDIENTE X DESPACHAR");
			
		
			//INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
			InputStream xz = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
            byte[] bytes = IOUtils.toByteArray(xz);
            xz.close();
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
			
			
			posCell = 0;
			posColl = 0;
			row = hoja1.createRow(8);
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Cliente");
			
			for(@SuppressWarnings("unused") List<String> x: listaBodInternas) {
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("");
			}
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			for(List<String> x: listaBodExternasVigentes) {
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(x.get(2));
			}
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			posCell = 0;
			posColl = 0;
			row = hoja1.createRow(9);
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Nro_Coti");
			
			for(@SuppressWarnings("unused") List<String> x: listaBodInternas) {
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("");
			}
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			for(List<String> x: listaBodExternasVigentes) {
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(x.get(8));
			}
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			
			posCell = 0;
			posColl = 0;
			row = hoja1.createRow(10);
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Nro_OT");
			
			for(@SuppressWarnings("unused") List<String> x: listaBodInternas) {
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("");
			}
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			for(List<String> x: listaBodExternasVigentes) {
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(x.get(9));
			}
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			
			posCell = 0;
			posColl = 0;
			row = hoja1.createRow(11);
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Fecha Entrega");
			
			for(@SuppressWarnings("unused") List<String> x: listaBodInternas) {
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("");
			}
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			for(List<String> x: listaBodExternasVigentes) {
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(x.get(5));
			}
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			
			posCell = 0;
			posColl = 0;
			row = hoja1.createRow(12);
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Sucursal");
			
			for(@SuppressWarnings("unused") List<String> x: listaBodInternas) {
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("");
			}
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			for(List<String> x: listaBodExternasVigentes) {
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(x.get(6));
			}
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			
			posCell = 0;
			posColl = 0;
			row = hoja1.createRow(13);
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Comercial");
			
			for(@SuppressWarnings("unused") List<String> x: listaBodInternas) {
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("");
			}
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			for(List<String> x: listaBodExternasVigentes) {
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(encabezado);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(x.get(7));
			}
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			
			
			
			//DETALLE DE LA TABLA
			int posRow = 14;
			for(Equipo q: listaEquipos) {
				row = hoja1.createRow(posRow);
				posCell = 0;
				
				posCell++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(q.getGrupo());
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(q.getCodigo());
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(q.getNombre());
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(q.getKg());
				
				Double subTotalInt = (double)0;
				for(List<String> x: listaBodInternas) {
					Double aux = mapAllStockBodInt.get(x.get(1)+"_"+q.getId());
					if(aux != null) {
						subTotalInt += aux;
					}else {
						aux = (double)0;
					}
					posCell++;
		            cell = row.createCell(posCell);
		            cell.setCellStyle(detalle);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue(aux);
				}
				posCell++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detallePintado);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(subTotalInt);
				
				Double subTotalDesp = (double)0;
				for(List<String> x: listaBodExternasVigentes) {
					Double aux = mapAllPorDespachar.get(x.get(0)+"_"+q.getId()+"_"+x.get(4));
					if(aux != null) {
						subTotalDesp += aux;
					}else {
						aux = (double)0;
					}
					posCell++;
		            cell = row.createCell(posCell);
		            cell.setCellStyle(detalle);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue(aux);
				}
				posCell++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detallePintado);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(subTotalDesp);
				
				Double totalTotal = subTotalInt + subTotalDesp;
				if(totalTotal > 0) {
					posRow++;
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
		return(tmp);
	}
		
	public static File cotizacionEnExcelResumen(Connection con, String db, List<List<String>> resumen, Cliente cliente, Map<String,String> mapDiccionario, 
			Proyecto proyecto, Map<String,String> mapeoPermiso, EmisorTributario emisor){
		
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
			cell.setCellValue("RESUMEN DE COTIZACION (CONSOLIDADO)");
			
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
			cell.setCellValue(mapDiccionario.get("RUT") +" CLIENTE");
            cell = row.createCell(2);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(cliente.getRut());
			
			row = hoja1.createRow(6);
            cell = row.createCell(1);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("CLIENTE");
            cell = row.createCell(2);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(cliente.getNickName());
			
			row = hoja1.createRow(7);
            cell = row.createCell(1);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("PROYECTO");
            cell = row.createCell(2);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(proyecto.getNickName());
			
			
			// encabezado de la tabla
			
						int posCell = 0;
						int posColl = 0;
						row = hoja1.createRow(9);
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 4*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("SUCURSAL");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 4*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("COMERCIAL");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 4*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("NRO COTI");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 4*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("FECHA COTI");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 10*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("OBSERVACIONES");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 4*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("CODIGO");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 10*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("EQUIPO");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 1*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("UN");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 3*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("CANTIDAD");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 3*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("CONCEPTO");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 1*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("MON");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 3*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("P.UNIT VTA/REPOS");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 3*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("UN "+mapDiccionario.get("ARR"));
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 3*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("P.UNIT "+mapDiccionario.get("ARR"));
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 3*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("PERM");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 3*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("P.TOTAL REPOS");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 3*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("P.TOTAL "+mapDiccionario.get("ARR"));
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 3*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("P.TOTAL VENTA");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 3*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("KG");
						if( !(mapeoPermiso.get("parametro.escondeLosM2")!=null && mapeoPermiso.get("parametro.escondeLosM2").equals("1")) ) {
							posCell++; posColl++;
							hoja1.setColumnWidth(posColl, 3*1000);
				            cell = row.createCell(posCell);
				            cell.setCellStyle(encabezado);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("M2");
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
				        anchor.setCol1(14);
				        anchor.setRow1(1);
						Picture img = draw.createPicture(anchor, pngIndex);
						img.resize(0.4);
						hoja1.createFreezePane(0, 0, 0,0);
			
				//DETALLE DE LA TABLA
						int posRow = 10;
						
						Double totalRepos=(double)0;
						Double totalArr=(double)0;
						Double totalVta=(double)0;
						
						Double totalCant=(double)0;
						Double totalKg=(double)0;
						Double totalM2=(double)0;
								
						for(int i=0; i<resumen.size(); i++){
						
							row = hoja1.createRow(posRow);
							posCell = 0;
							posColl = 0;
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(resumen.get(i).get(0));
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(resumen.get(i).get(1));
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(resumen.get(i).get(2));
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(resumen.get(i).get(3));
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(resumen.get(i).get(4));
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(resumen.get(i).get(5));
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(resumen.get(i).get(6));
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(resumen.get(i).get(7));
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							Double aux = Double.parseDouble(resumen.get(i).get(8).replaceAll(",", ""));
							totalCant += aux;
							cell.setCellValue(aux);
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(resumen.get(i).get(9));
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(resumen.get(i).get(10));
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(Double.parseDouble(resumen.get(i).get(11).replaceAll(",", "")));
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(resumen.get(i).get(12));
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(Double.parseDouble(resumen.get(i).get(13).replaceAll(",", "")));
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(Double.parseDouble(resumen.get(i).get(14).replaceAll(",", "")));
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							aux = Double.parseDouble(resumen.get(i).get(15).replaceAll(",", ""));
							totalRepos += aux;
							cell.setCellValue(aux);
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							aux = Double.parseDouble(resumen.get(i).get(16).replaceAll(",", ""));
							totalArr += aux;
							cell.setCellValue(aux);
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							aux = Double.parseDouble(resumen.get(i).get(17).replaceAll(",", ""));
							totalVta += aux;
							cell.setCellValue(aux);
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							aux = Double.parseDouble(resumen.get(i).get(18).replaceAll(",", ""));
							totalKg += aux;
							cell.setCellValue(aux);
							
							if( !(mapeoPermiso.get("parametro.escondeLosM2")!=null && mapeoPermiso.get("parametro.escondeLosM2").equals("1")) ) {
								posCell++; posColl++;
					            cell = row.createCell(posCell);
					            cell.setCellStyle(detalle);
								cell.setCellType(Cell.CELL_TYPE_NUMERIC);
								aux = Double.parseDouble(resumen.get(i).get(19).replaceAll(",", ""));
								totalM2 += aux;
								cell.setCellValue(aux);
							}
							
							posRow++;
			 			}
						
						row = hoja1.createRow(posRow);
						posCell = 0;
						posColl = 0;
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("");
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("");
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("");
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("");
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("");
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("");
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("Total cantidad");
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("");
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(totalCant);
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("");
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("");
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("");
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("");
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("");
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("TOTALES");
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(totalRepos);
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(totalArr);
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(totalVta);
						
						posCell++; posColl++;
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(totalKg);
						
						if( !(mapeoPermiso.get("parametro.escondeLosM2")!=null && mapeoPermiso.get("parametro.escondeLosM2").equals("1")) ) {
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(totalM2);
						}
						
						posRow++;
						
						
						if(mapeoPermiso.get("parametro.cotizaciones-con-iva")!=null && mapeoPermiso.get("parametro.cotizaciones-con-iva").equals("1")) {
							row = hoja1.createRow(posRow);
							posCell = 0;
							posColl = 0;
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("IVA");
							
							Double tasaIVA = emisor.getTasaIva()/100;
							
							if(mapeoPermiso.get("parametro.ivaPorBodega")!=null && mapeoPermiso.get("parametro.ivaPorBodega").equals("1")) {
					        	if(resumen.size() > 0) {
					        		String idSucursal = resumen.get(0).get(24);
					        		Sucursal sucursal = Sucursal.find(con, db, idSucursal);
					        		if(sucursal!=null && sucursal.getIvaSucursal()>0) {
					        			tasaIVA = sucursal.getIvaSucursal();
					        		}
					        	}
					        }
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(totalRepos*tasaIVA);
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(totalArr*tasaIVA);
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(totalVta*tasaIVA);
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							
							if( !(mapeoPermiso.get("parametro.escondeLosM2")!=null && mapeoPermiso.get("parametro.escondeLosM2").equals("1")) ) {
								posCell++; posColl++;
					            cell = row.createCell(posCell);
					            cell.setCellStyle(detalle);
							}
							
							posRow++;
							row = hoja1.createRow(posRow);
							posCell = 0;
							posColl = 0;
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("TOTAL CON IVA");
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(totalRepos*(1+tasaIVA));
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(totalArr*(1+tasaIVA));
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(totalVta*(1+tasaIVA));
							
							posCell++; posColl++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							
							if( !(mapeoPermiso.get("parametro.escondeLosM2")!=null && mapeoPermiso.get("parametro.escondeLosM2").equals("1")) ) {
								posCell++; posColl++;
					            cell = row.createCell(posCell);
					            cell.setCellStyle(detalle);
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
		
		return(tmp);
	}
		
			
}
	
	
	
