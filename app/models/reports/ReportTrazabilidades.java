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
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.TempFile;

import models.tables.Sucursal;
import models.utilities.Archivos;
import models.utilities.Fechas;


public class ReportTrazabilidades {
	
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	static SimpleDateFormat myformatfecha = new SimpleDateFormat("dd-MM-yyyy");
		
	
	public static List<List<String>> trazaEquipo(Connection con, String db, Long idEquipo, Map<String,String> mapDiccionario, String esPorSucursal, String id_sucursal){
			List<List<String>> lista = new ArrayList<List<String>>();
			
			
			
			Map<Long,List<String>> bodega = new HashMap<Long,List<String>>();
			try {
				
				PreparedStatement smt2 = con
						.prepareStatement(" select bodegaEmpresa.id,tipoBodega.nombre,bodegaEmpresa.nombre, bodegaEmpresa.id_sucursal " +
								" from `"+db+"`.bodegaEmpresa " +
								" left join `"+db+"`.tipoBodega on tipoBodega.id = bodegaEmpresa.esInterna;");
				ResultSet rs2 = smt2.executeQuery();
				Map<Long, Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
				while (rs2.next()) {
					String nameSucursal = "";
					Sucursal sucursal = mapSucursal.get(rs2.getLong(4));
					if(sucursal!=null) {
						nameSucursal = sucursal.getNombre();
					}
					List<String> aux = new ArrayList<String>();
					aux.add(rs2.getString(2));
					aux.add(rs2.getString(3));
					aux.add(nameSucursal);
					aux.add(rs2.getString(4));
					bodega.put(rs2.getLong(1), aux);
				}
				rs2.close();
				smt2.close();
				
				
				PreparedStatement smt1 = con
						.prepareStatement(" select  " +
								" movimiento.id_bodegaEmpresa,  " +
								" movimiento.id_bodegaOrigen, " +
								" bodegaEmpresa.nombre,   " +
								" equipo.id as idEquipo,   " +
								" equipo.codigo as codigoEquipo,   " +
								" equipo.nombre as nombreEquipo,   " +
								" guia.id as idGuia, " +
								" ifnull(guia.fecha,ifnull(factura.fecha,actaBaja.fecha)) as fecha, " +
								" if(guia.id>0,concat('Mov.',guia.numero),concat( 'Fact.',factura.numero)), " +
								" if(movimiento.id_tipoMovimiento=1,1,-1)*movimiento.cantidad as cantidad, " +
								" movimiento.id as idMovimiento, " +
								" if(guia.id>0,guia.fecha,if(factura.id>0,factura.fecha,actaBaja.fecha)),   " +
								" bodegaEmpresa.esInterna,   " +
								" tipoBodega.nombre,   " +
								" movimiento.esVenta, "+
								" ifnull(guia.numGuiaCliente,''), " +
								" bodegaEmpresa.id_sucursal " +
								" from `"+db+"`.movimiento   " +
								" left join `"+db+"`.equipo on equipo.id = movimiento.id_equipo   " +
								" left join `"+db+"`.grupo on grupo.id = equipo.id_grupo   " +
								" left join `"+db+"`.guia on guia.id = movimiento.id_guia   " +
								" left join `"+db+"`.unidad on unidad.id = equipo.id_unidad   " +
								" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa   " +
								" left join `"+db+"`.tipoBodega on tipoBodega.id = bodegaEmpresa.esInterna   " +
								" left join `"+db+"`.compra on compra.id = movimiento.id_compra   " +
								" left join `"+db+"`.factura on factura.id = compra.id_factura   " +
								" left join `"+db+"`.baja on baja.id = movimiento.id_baja   " +
								" left join `"+db+"`.actaBaja on actaBaja.id = baja.id_actaBaja   " +
								" where (bodegaEmpresa.vigente = 1 or bodegaEmpresa.vigente = 0)   " +
								" and (guia.fecha is null or guia.fecha<='2200-04-06')   " +
								" and (factura.fecha is null or factura.fecha<='2200-04-06')   " +
								" and (actaBaja.fecha is null or actaBaja.fecha<='2200-04-06')   " +
								" and movimiento.id_equipo = ? " +
								" group by movimiento.id,guia.numero,movimiento.id_bodegaEmpresa,movimiento.id_equipo " +
								" having cantidad >0   " +
								" order by fecha desc,guia.id desc, " +
								" grupo.nombre,equipo.nombre,cantidad desc;");
				smt1.setLong(1, idEquipo);
				ResultSet rs1 = smt1.executeQuery();
				
				
				
				while (rs1.next()) {
					
					
					List<String> aux = new ArrayList<String>();
					if(rs1.getDate(8)==null) {
						aux.add("");
					}else {
						aux.add(myformatfecha.format(rs1.getDate(8)));
					}
					
					if((long)rs1.getLong(2)==(long)0){
						aux.add("COMPRA");
					}else{
						aux.add(bodega.get(rs1.getLong(2)).get(1));
					}
					
					aux.add(bodega.get(rs1.getLong(1)).get(1));
					
					aux.add(rs1.getString(5));
					aux.add(rs1.getString(6));
					aux.add(rs1.getString(10));
					aux.add(rs1.getString(9));
					if(rs1.getLong(2)==0){
						aux.add("---");
					}else if(rs1.getLong(15)==0){
						aux.add(mapDiccionario.get("Arriendo"));
					}else {
						aux.add("Venta");
					}
					aux.add(rs1.getString(16));
					
					if((long)rs1.getLong(2)==(long)0){
						aux.add("");
					}else{
						aux.add(bodega.get(rs1.getLong(2)).get(2)); // name sucursal desde
					}
					aux.add(bodega.get(rs1.getLong(1)).get(2)); // name sucursal hasta
					
					if(esPorSucursal.equals("1")) {
						if((long)rs1.getLong(2)==(long)0 && bodega.get(rs1.getLong(1)).get(3).equals(id_sucursal)) {
							lista.add(aux);
						}else if(bodega.get(rs1.getLong(2)).get(3).equals(id_sucursal) || bodega.get(rs1.getLong(1)).get(3).equals(id_sucursal)){
							lista.add(aux);
						}
					}else {
						lista.add(aux);
					}
					
				}
				rs1.close();
				smt1.close();
				
			} catch (SQLException e) {
    			e.printStackTrace();
			}
			return (lista);
	}
	
	public static File trazaEquipoExcel(String db, List<List<String>> datos, Map<String,String> mapDiccionario) {
		
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
            
            CellStyle detalleFecha = libro.createCellStyle();
            CreationHelper createHelper = libro.getCreationHelper();
            detalleFecha.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
            detalleFecha.setBorderBottom(CellStyle.BORDER_THIN);
            detalleFecha.setBorderTop(CellStyle.BORDER_THIN);
            detalleFecha.setBorderRight(CellStyle.BORDER_THIN);
            detalleFecha.setBorderLeft(CellStyle.BORDER_THIN);
		
            Sheet hoja1 = libro.getSheetAt(0);
            Row row = null;
            Cell cell = null;
            
            
            row = hoja1.createRow(1);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(mapDiccionario.get("TRAZABILIDAD POR EQUIPO"));
			
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
            
			
			// encabezado de la tabla
			
						int posCell = 0;
						int posColl = 0;
						
						row = hoja1.createRow(6);
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 4*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("FECHA");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 4*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("NRO MOV");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 4*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("NRO REF");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 4*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("TIPO");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 10*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("SUCURSAL");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 10*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("DESDE");
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 10*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("SUCURSAL");
						
						
						posCell++; posColl++;
						hoja1.setColumnWidth(posColl, 10*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("HASTA");
						
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
						hoja1.setColumnWidth(posColl, 4*1000);
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado2);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue("CANTIDAD");
						
			
				//INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
						InputStream x = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
			            byte[] bytes = IOUtils.toByteArray(x);
			            x.close();
			            int pngIndex = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
						Drawing draw = hoja1.createDrawingPatriarch();
						CreationHelper helper = libro.getCreationHelper();
						ClientAnchor anchor = helper.createClientAnchor();
				        //set top-left corner for the image
				        anchor.setCol1(8);
				        anchor.setRow1(1);
						Picture img = draw.createPicture(anchor, pngIndex);
						img.resize(0.4);
						hoja1.createFreezePane(0, 0, 0,0);
			
				//DETALLE DE LA TABLA
						int posRow = 7;
								
						for(int i=0;i<datos.size();i++){
						
							row = hoja1.createRow(posRow);
							posCell = 0;
							
							posCell++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalleFecha);
							//cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(Fechas.obtenerFechaDesdeStrDDMMAA(datos.get(i).get(0)).getFechaUtil());
							
							posCell++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(datos.get(i).get(6));
							
							posCell++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(datos.get(i).get(8));
							
							posCell++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(datos.get(i).get(7));
							
							posCell++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(datos.get(i).get(9));
							
							posCell++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(datos.get(i).get(1));
							
							posCell++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(datos.get(i).get(10));
							
							posCell++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(datos.get(i).get(2));
							
							posCell++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(datos.get(i).get(3));
							
							posCell++;
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(datos.get(i).get(4));
							
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							Double aux = Double.parseDouble(datos.get(i).get(5).replaceAll(",", ""));
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(aux);
							
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
	
	
	
