package models.reports;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.TempFile;

import models.tables.Atributo;
import models.tables.BodegaEmpresa;
import models.tables.Cliente;
import models.tables.CotizaDetalle;
import models.tables.CotizaEstado;
import models.tables.Cotizacion;
import models.tables.Equipo;
import models.tables.ListaPrecio;
import models.tables.Ot;
import models.tables.OtDespachado;
import models.tables.OtEstado;
import models.tables.Precio;
import models.tables.Proyecto;
import models.tables.UnidadTiempo;
import models.utilities.Fechas;






public class ReportHohe {
	static DecimalFormat myformatint = new DecimalFormat("#,##0");
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	
	public static List<List<String>> datos(Connection con,String db, String desde, String hasta) {
		List<List<String>> datos = new ArrayList<List<String>>();
			try {
				
				PreparedStatement smt100 = con
						.prepareStatement("select id from `"+db+"`.atributo where atributo like '%peso%';");
				ResultSet rs100 = smt100.executeQuery();
				List<String> auxLista = new ArrayList<String>();
				while (rs100.next()) {
					auxLista.add(rs100.getString(1));
				}
				rs100.close();smt100.close();
				String condicion = auxLista.toString();
				condicion = condicion.replace("[", "").replace("]", "");
				
					PreparedStatement smt = con
							.prepareStatement(" select "
						/*1*/	+ "m.id as id_movimiento, "
						/*2*/	+ "b.id as id_bodegaEmpresa, "
						/*3*/	+ "t.id as id_tipoMovimiento, "
						/*4*/	+ "ifnull(g.id,'NA') as id_guia, "
						/*5*/	+ "e.id as id_equipo, "
						/*6*/	+ "gr.id as id_grupo, "
						/*7*/	+ "if(m.id_tipoMovimiento=2,-1,1)*m.cantidad as cantidad, "
						/*8*/	+ "if(m.id_tipoMovimiento=2,-1,1)*m.cantidad* ifnull((select numAtributo from `"+db+"`.atributoEquipo " +
									" where id_atributo in ("+condicion+") and id_equipo=e.id),0) as peso, "
						/*9*/	+ "b.nombre as bodegaEmpresa, "
						/*10*/	+ "ifnull((select nombre from `"+db+"`.bodegaEmpresa where id = m.id_bodegaOrigen),'NA') as origen, "
						/*11*/	+ "t.nombre as tipoMovimiento, "
						/*12*/	+ "ifnull(g.numero,'NA') as guiaInterna, "
						/*13*/	+ "ifnull(g.numGuiaCliente,'NA') as guiaCliente, "
						/*14*/	+ "ifnull(day(g.fecha),'NA') as fechaGuiaDay, "
						/*15*/	+ "ifnull(month(g.fecha),'NA') as fechaGuiaMonth, "
						/*16*/	+ "ifnull(year(g.fecha),'NA') as fechaGuiaYear, "
						/*17*/	+ "e.codigo as codigo, "
						/*18*/	+ "e.nombre as equipo, "
						/*19*/	+ "gr.nombre as grupo, "
						/*20*/	+ "if(m.esVenta=0,'Arriendo','Venta'), "

								+ "'21', "
								+ "'22', "
						
//						/*21*/	+ "ifnull(p.precioReposicion,0)  as PL_Reposicion, "
//						/*22*/	+ "ifnull(p.precioArriendo,0) as PL_Arriendo, "

						/*23*/	+ "ifnull(lp.precioReposicion,0) as PB_Reposicion, "
						/*24*/	+ "ifnull(lp.precioArriendo,0) as PB_Arriendo, " 
						
						/*25*/	+ "ifnull((select esInterna from `"+db+"`.bodegaEmpresa where id = m.id_bodegaOrigen),'NA') as esInternaDESDE, "
						/*26*/	+ "b.esInterna as esInternaHASTA, " 
						/*27*/	+ "m.id_compra as id_compra, " 
						/*28*/  + "ifnull(f.numero,'NA') as num_factura, " 
						/*29*/  + "ifnull(f.fecha,'NA') as fecha_factura, "
						/*30*/  + "ifnull(prov.nickName,'NA') " +

									" from `"+db+"`.movimiento as m " +
									" left join `"+db+"`.bodegaEmpresa as b on b.id=m.id_bodegaEmpresa "+
									" left join `"+db+"`.tipoMovimiento as t on t.id=m.id_tipoMovimiento "+
									" left join `"+db+"`.guia as g on g.id=m.id_guia "+
									" left join `"+db+"`.equipo as e on e.id=m.id_equipo "+
									" left join `"+db+"`.grupo as gr on gr.id=e.id_grupo "+
									// " left join `"+db+"`.precio as p on p.id_equipo = m.id_equipo "+
									" left join `"+db+"`.listaPrecio as lp on lp.id_equipo = m.id_equipo and lp.id_bodegaEmpresa = m.id_bodegaEmpresa "+ 
									" and lp.id_cotizacion = m.id_cotizacion "+
									" left join `"+db+"`.compra as c on c.id = m.id_compra "+
									" left join `"+db+"`.factura as f on f.id = c.id_factura "+
									" left join `"+db+"`.proveedor as prov on prov.id = f.id_proveedor "+
									" where b.id is not null "+
									" and g.fecha between '"+desde+"' and '"+hasta+"' or (m.id_guia=0 and m.id_compra>0);");
					ResultSet rs = smt.executeQuery();
					
					//id_equipo vs list  0 precioVenta 1 precioArriendo 2 idUnidadTiempo 3 idMoneda
					Map<Long,List<Double>> mapPrecio = Precio.maestroPListaPorSucursal(con, db, (long)1);
					
					while (rs.next()) {		
						List<String> aux = new ArrayList<String>();
						aux.add(rs.getString(1));         //  0  id_movimiento
						aux.add(rs.getString(2));         //  1  id_bodegaEmpresa
						aux.add(rs.getString(3));         //  2  id_tipoMovimiento
						aux.add(rs.getString(4));         //  3  id_guia
						aux.add(rs.getString(5));         //  4  id_equipo
						aux.add(rs.getString(6));         //  5  id_grupo
						aux.add(rs.getString(7));         //  6  cantidad
						aux.add(rs.getString(8));         //  7  peso
						aux.add(rs.getString(9));         //  8  bodegaEmpresa
						aux.add(rs.getString(10));        //  9  origen
						
						aux.add(rs.getString(11));        // 10  tipoMovimiento
						aux.add(rs.getString(12));        // 11  guiaInterna
						aux.add(rs.getString(13));        // 12  guiaCliente
						aux.add(rs.getString(14));        // 13  fechaGuiaDay
						aux.add(rs.getString(15));        // 14  fechaGuiaMonth
						aux.add(rs.getString(16));        // 15  fechaGuiaYear
						aux.add(rs.getString(17));        // 16  codigo
						aux.add(rs.getString(18));        // 17  equipo
						aux.add(rs.getString(19));        // 18  grupo
						aux.add(rs.getString(20));        // 19  tipo (arriendo/venta)
						
						List<Double> precio = mapPrecio.get(rs.getLong(5));
						aux.add(precio.get(0).toString());        // 20  PL_Reposicion
						aux.add(precio.get(1).toString());        // 21  PL_Arriendo
						
//						aux.add(rs.getString(21));        // 20  PL_Reposicion
//						aux.add(rs.getString(22));        // 21  PL_Arriendo
						aux.add(rs.getString(23));        // 22  PB_Reposicion
						aux.add(rs.getString(24));        // 23  PB_Arriendo
						
						aux.add(rs.getString(25));        // 24  esInternaDESDE
						aux.add(rs.getString(26));        // 25  esInternaHASTA
						aux.add(rs.getString(27));        // 26  id_compra
						aux.add(rs.getString(28));        // 27  num_factura
						aux.add(rs.getString(29));        // 28  fecha_factura
						aux.add(rs.getString(30));        // 29  proveedor
						datos.add(aux);
					}
					smt.close();
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		
		return (datos);
	}
	
	public static File reporteHoheExcel(String desde, String hasta, List<List<String>> lista, InputStream formato) {
		
		File tmp = TempFile.createTempFile("tmp","null");
		
		try {
			Workbook libro = WorkbookFactory.create(formato);
			formato.close();
            
         // define formatos
            DataFormat format = libro.createDataFormat();
            CellStyle formatFecha = libro.createCellStyle();
            	formatFecha.setDataFormat(format.getFormat("dd-mm-yyyy"));
            CellStyle formatDecimal0 = libro.createCellStyle();
            	formatDecimal0.setDataFormat(format.getFormat("#,##0"));
            CellStyle formatDecimal2 = libro.createCellStyle();
            	formatDecimal2.setDataFormat(format.getFormat("#,##0.00"));
            CellStyle formatDecimal4 = libro.createCellStyle();
            	formatDecimal4.setDataFormat(format.getFormat("#,##0.0000"));
            CellStyle formatDecimal6 = libro.createCellStyle();
            	formatDecimal6.setDataFormat(format.getFormat("#,##0.000000"));
            CellStyle formatPorcentaje = libro.createCellStyle();
            	formatPorcentaje.setDataFormat(format.getFormat("#,##0.00%"));
            CellStyle negrita = libro.createCellStyle();
            	Font font = libro.createFont();
            		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
            		negrita.setFont(font);
            CellStyle total = libro.createCellStyle();
            	font.setBoldweight(Font.BOLDWEIGHT_BOLD);
            	total.setFont(font);
            	total.setDataFormat(format.getFormat("#,##0.00"));
            CellStyle fondoYellow = libro.createCellStyle();
            	fondoYellow.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
            	fondoYellow.setFillPattern(CellStyle.SOLID_FOREGROUND);
            
            // llena datos
            
            Sheet hoja2 = libro.getSheetAt(1);
            Row row = null;
            Cell cell = null;
            
            int fila=3;
			row = hoja2.createRow(0);
			cell = row.createCell(0);
			cell.setCellValue("Desde: " + desde + " Hasta: " + hasta);
			
			for (int i=0; i<lista.size(); i++) {
				row = hoja2.createRow(fila+i);
				for (int j=0;j<lista.get(i).size();j++) {
					cell = row.createCell(j);
					if(j<6) {
						String aux = lista.get(i).get(j).trim();
						if(aux.equals("")) {aux="0";}
						if(aux.equals("NA")) {
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("NA");
						}else {
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(Long.parseLong(aux));
						}
					}else if(j<8) {
						String aux = lista.get(i).get(j).trim();
						if(aux.equals("") || aux.equals("NA")) {aux="0";}
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(Double.parseDouble(aux));
					}else if(j==10) {
						String aux = lista.get(i).get(9).trim();
						String tipo = "Mov_Interno";
						String esInternaDESDE = lista.get(i).get(24).trim();
						String esInternaHASTA = lista.get(i).get(25).trim();
						String id_compra = lista.get(i).get(26).trim();
						if(aux.equals("")) {
							tipo = "NA";
						}else if (!id_compra.equals("0")) {
							tipo = "Compra";
						}else if(!id_compra.equals("0")) {
							tipo = "Mov_Interno";
						}else if(esInternaDESDE.equals("1") && esInternaHASTA.equals("2")) {
							tipo = "Despacho";
						}else if(esInternaDESDE.equals("2") && esInternaHASTA.equals("1")) {
							tipo = "Devolucion";
						}else if(esInternaDESDE.equals("2") && esInternaHASTA.equals("2")) {
							tipo = "Mov_Cliente";
						}else {
							tipo = "Mov_Interno";
						}
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(tipo);
					}else if(j<13) {
						String aux = lista.get(i).get(j).trim();
						if(aux.equals("")) {aux="NA";}
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(aux);
					}else if(j<16) {
						String aux = lista.get(i).get(j).trim();
						if(aux.equals("") || aux.equals("NA")) {
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("NA");
						}else {
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(Long.parseLong(aux));
						}
						
					}else if(j<20) {
						String aux = lista.get(i).get(j).trim();
						if(aux.equals("")) {aux="NA";}
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(aux);
					}else if(j<lista.get(i).size()-3){
						String aux = lista.get(i).get(j).trim();
						if(aux.equals("") || aux.equals("NA")) {aux="0";}
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(Double.parseDouble(aux));
					}
					
					if(j==lista.get(i).size()-1) {
						
						String aux = lista.get(i).get(15).trim();
						if(aux.equals("") || aux.equals("NA")) {
							cell = row.createCell(j+1-3);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("NA");
							
							cell = row.createCell(j+2-3);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("NA");
						}else {
							cell = row.createCell(j+1-3);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellStyle(formatFecha);
							Fechas fechaGuia = Fechas.obtenerFechaDesdeStrAAMMDD(lista.get(i).get(15).trim()+"-"+lista.get(i).get(14).trim()+"-"+lista.get(i).get(13).trim());
							cell.setCellValue(fechaGuia.getFechaUtil());
							
							cell = row.createCell(j+2-3);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellStyle(formatFecha);
							cell.setCellValue(fechaGuia.getFechaUtil());
						}
						
						aux = lista.get(i).get(8).trim();
						if(aux.equals("")) {aux="NA";}
						cell = row.createCell(j+3-3);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(aux);
						
						aux = lista.get(i).get(27).trim();
						if(aux.equals("")) {aux="NA";}
						cell = row.createCell(j+4-3);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(aux);
						
						aux = lista.get(i).get(28).trim();
						if(aux.equals("") || aux.equals("NA")) {
							cell = row.createCell(j+5-3);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("NA");
						}else {
							cell = row.createCell(j+5-3);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellStyle(formatFecha);
							Fechas fechaFactura = Fechas.obtenerFechaDesdeStrAAMMDD(aux);
							cell.setCellValue(fechaFactura.getFechaUtil());
						}
						aux = lista.get(i).get(29).trim();
						if(aux.equals("")) {aux="NA";}
						cell = row.createCell(j+6-3);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(aux);
					}
					
				}
			}
			
			// Write the output to a file tmp
			FileOutputStream fileOut = new FileOutputStream(tmp);
			libro.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
    			e.printStackTrace();
        }
		return tmp;
	}
	
	public static List<List<String>> datosResumen(Connection con,String db, String desde, String hasta) {
		List<List<String>> datos = new ArrayList<List<String>>();
			try {
				
				PreparedStatement smt100 = con
						.prepareStatement("select id from `"+db+"`.atributo where atributo like '%peso%';");
				ResultSet rs100 = smt100.executeQuery();
				List<String> auxLista = new ArrayList<String>();
				while (rs100.next()) {
					auxLista.add(rs100.getString(1));
				}
				rs100.close();smt100.close();
				String condicion = auxLista.toString();
				condicion = condicion.replace("[", "").replace("]", "");
				
					PreparedStatement smt = con
							.prepareStatement(" select "
						/*1*/	+ "m.id as id_movimiento, "
						/*2*/	+ "b.id as id_bodegaEmpresa, "
						/*3*/	+ "t.id as id_tipoMovimiento, "
						/*4*/	+ "ifnull(g.id,'NA') as id_guia, "
						/*5*/	+ "e.id as id_equipo, "
						/*6*/	+ "gr.id as id_grupo, "
						/*7*/	+ "if(m.id_tipoMovimiento=2,-1,1)*m.cantidad as cantidad, "
						/*8*/	+ "if(m.id_tipoMovimiento=2,-1,1)*m.cantidad* ifnull((select numAtributo from `"+db+"`.atributoEquipo " +
									" where id_atributo in ("+condicion+") and id_equipo=e.id),0) as peso, "
						/*9*/	+ "b.nombre as bodegaEmpresa, "
						/*10*/	+ "ifnull((select nombre from `"+db+"`.bodegaEmpresa where id = m.id_bodegaOrigen),'NA') as origen, "
						/*11*/	+ "t.nombre as tipoMovimiento, "
						/*12*/	+ "ifnull(g.numero,'NA') as guiaInterna, "
						/*13*/	+ "ifnull(g.numGuiaCliente,'NA') as guiaCliente, "
						/*14*/	+ "ifnull(day(g.fecha),'NA') as fechaGuiaDay, "
						/*15*/	+ "ifnull(month(g.fecha),'NA') as fechaGuiaMonth, "
						/*16*/	+ "ifnull(year(g.fecha),'NA') as fechaGuiaYear, "
						/*17*/	+ "e.codigo as codigo, "
						/*18*/	+ "e.nombre as equipo, "
						/*19*/	+ "gr.nombre as grupo, "
						/*20*/	+ "if(m.esVenta=0,'Arriendo','Venta'), "
						
								+ "'21', "
								+ "'22', "
						
//						/*21*/	+ "ifnull(p.precioReposicion,0)  as PL_Reposicion, "
//						/*22*/	+ "ifnull(p.precioArriendo,0) as PL_Arriendo, "
						/*23*/	+ "ifnull(lp.precioReposicion,0) as PB_Reposicion, "
						/*24*/	+ "ifnull(lp.precioArriendo,0) as PB_Arriendo, " 
						
						/*25*/	+ "ifnull((select esInterna from `"+db+"`.bodegaEmpresa where id = m.id_bodegaOrigen),'NA') as esInternaDESDE, "
						/*26*/	+ "b.esInterna as esInternaHASTA " +
									" from `"+db+"`.movimiento as m " +
									" left join `"+db+"`.bodegaEmpresa as b on b.id=m.id_bodegaEmpresa "+
									" left join `"+db+"`.tipoMovimiento as t on t.id=m.id_tipoMovimiento "+
									" left join `"+db+"`.guia as g on g.id=m.id_guia "+
									" left join `"+db+"`.equipo as e on e.id=m.id_equipo "+
									" left join `"+db+"`.grupo as gr on gr.id=e.id_grupo "+
									//" left join `"+db+"`.precio as p on p.id_equipo = m.id_equipo "+
									" left join `"+db+"`.listaPrecio as lp on lp.id_equipo = m.id_equipo and lp.id_bodegaEmpresa = m.id_bodegaEmpresa "+ 
									" and lp.id_cotizacion = m.id_cotizacion "+
									" where b.id is not null "+
									" and g.fecha between '"+desde+"' and '"+hasta+"';");

					ResultSet rs = smt.executeQuery();
					
					//id_equipo vs list  0 precioVenta 1 precioArriendo 2 idUnidadTiempo 3 idMoneda
					Map<Long,List<Double>> mapPrecio = Precio.maestroPListaPorSucursal(con, db, (long)1);
					
					while (rs.next()) {		
						List<String> aux = new ArrayList<String>();
						if(!rs.getString(10).equals("NA")) {
							aux.add(rs.getString(10));        //  0  origen - DESDE
							aux.add(rs.getString(9));         //  1  bodegaEmpresa - HASTA
							
							if(rs.getString(25).trim().equals("1") && rs.getString(26).trim().equals("2")) {
								aux.add("Despacho");
							}else if(rs.getString(25).trim().equals("2") && rs.getString(26).trim().equals("1")){
								aux.add("Devolucion");
							}else if(rs.getString(25).trim().equals("2") && rs.getString(26).trim().equals("2")) {
								aux.add("Traslado Obras");
							}else {
								aux.add("Traslado Internos");
							}
							aux.add(rs.getString(12));        // 3  guiaInterna
							aux.add(rs.getString(13));        // 4  guiaCliente
							aux.add(rs.getString(14));        // 5  fechaGuiaDay
							aux.add(rs.getString(15));        // 6  fechaGuiaMonth
							aux.add(rs.getString(16));        // 7  fechaGuiaYear
							aux.add(rs.getString(17));        // 8  codigo
							aux.add(rs.getString(18));        // 9  equipo
							aux.add(rs.getString(19));        // 10  grupo
							aux.add(rs.getString(20));        // 11  tipo (arriendo/venta)
							aux.add(rs.getString(7));         // 12  cantidad
							aux.add(rs.getString(8));         // 13  peso
							
							
							List<Double> precio = mapPrecio.get(rs.getLong(5));
							aux.add(precio.get(0).toString());        // 14  PL_Reposicion
							aux.add(precio.get(1).toString());        // 15  PL_Arriendo
							
							
							aux.add(rs.getString(23));        // 16  PB_Reposicion
							aux.add(rs.getString(24));        // 17  PB_Arriendo
							datos.add(aux);
						}
					}
					smt.close();
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		
		return (datos);
	}
	
	
	public static File reporteHoheResumenExcel(String desde, String hasta, List<List<String>> lista, InputStream formato) {
		
		File tmp = TempFile.createTempFile("tmp","null");
		
		try {
			Workbook libro = WorkbookFactory.create(formato);
			formato.close();
            
         // define formatos
            DataFormat format = libro.createDataFormat();
            CellStyle formatFecha = libro.createCellStyle();
            	formatFecha.setDataFormat(format.getFormat("dd-mm-yyyy"));
            CellStyle formatDecimal0 = libro.createCellStyle();
            	formatDecimal0.setDataFormat(format.getFormat("#,##0"));
            CellStyle formatDecimal2 = libro.createCellStyle();
            	formatDecimal2.setDataFormat(format.getFormat("#,##0.00"));
            CellStyle formatDecimal4 = libro.createCellStyle();
            	formatDecimal4.setDataFormat(format.getFormat("#,##0.0000"));
            CellStyle formatDecimal6 = libro.createCellStyle();
            	formatDecimal6.setDataFormat(format.getFormat("#,##0.000000"));
            CellStyle formatPorcentaje = libro.createCellStyle();
            	formatPorcentaje.setDataFormat(format.getFormat("#,##0.00%"));
            CellStyle negrita = libro.createCellStyle();
            	Font font = libro.createFont();
            		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
            		negrita.setFont(font);
            CellStyle total = libro.createCellStyle();
            	font.setBoldweight(Font.BOLDWEIGHT_BOLD);
            	total.setFont(font);
            	total.setDataFormat(format.getFormat("#,##0.00"));
            CellStyle fondoYellow = libro.createCellStyle();
            	fondoYellow.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
            	fondoYellow.setFillPattern(CellStyle.SOLID_FOREGROUND);
            
            // llena datos
            	
            Row row = null;
            Cell cell = null;
                
            Sheet hoja1 = libro.getSheetAt(0);
            row = hoja1.createRow(0);
			cell = row.createCell(0);
			cell.setCellValue("Desde: " + desde + " Hasta: " + hasta);
			
            Sheet hoja2 = libro.getSheetAt(1);
            int fila=3;
			row = hoja2.createRow(0);
			cell = row.createCell(0);
			cell.setCellValue("Desde: " + desde + " Hasta: " + hasta);
			
			for (int i=0; i<lista.size(); i++) {
				row = hoja2.createRow(fila+i);
				for (int j=0;j<lista.get(i).size();j++) {
					cell = row.createCell(j);
					if(j<5) {
						String aux = lista.get(i).get(j).trim();
						if(aux.equals("")) {aux="NA";}
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(aux);
					}else if(j<8) {
						String aux = lista.get(i).get(j).trim();
						if(aux.equals("") || aux.equals("NA")) {
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("NA");
						}else {
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(Long.parseLong(aux));
						}
					}else if(j<12) {
						String aux = lista.get(i).get(j).trim();
						if(aux.equals("")) {aux="NA";}
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(aux);
					}else {
						String aux = lista.get(i).get(j).trim();
						if(aux.equals("") || aux.equals("NA")) {aux="0";}
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(Double.parseDouble(aux));
					}
					
					if(j==lista.get(i).size()-1) {
						
						String aux = lista.get(i).get(15).trim();
						if(aux.equals("") || aux.equals("NA")) {
							cell = row.createCell(j+1);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("NA");
							
							cell = row.createCell(j+2);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue("NA");
						}else {
							cell = row.createCell(j+1);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellStyle(formatFecha);
							Fechas fechaGuia = Fechas.obtenerFechaDesdeStrAAMMDD(lista.get(i).get(7).trim()+"-"+lista.get(i).get(6).trim()+"-"+lista.get(i).get(5).trim());
							cell.setCellValue(fechaGuia.getFechaUtil());
							
							cell = row.createCell(j+2);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellStyle(formatFecha);
							cell.setCellValue(fechaGuia.getFechaUtil());
						}
						
						aux = lista.get(i).get(1).trim();
						if(aux.equals("")) {aux="NA";}
						cell = row.createCell(j+3);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(aux);
					}
					
				}
			}
			
			// Write the output to a file tmp
			FileOutputStream fileOut = new FileOutputStream(tmp);
			libro.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
    			e.printStackTrace();
        }
		return tmp;
	}
	
	
	public static List<List<String>> reporteHoheResumenJson(String desde, String hasta, List<List<String>> lista) {
		
		List<List<String>> rs = new ArrayList<List<String>>();
		List<String> aux = new ArrayList<String>();
		
            aux.add("DESDE");
            aux.add("HASTA");
            aux.add("MOVIMENTO");
            aux.add("GUIA_MADA");
            aux.add("GUIA_CLIENTE");
            aux.add("DIA");
            aux.add("MES");
            aux.add("ANIO");
            aux.add("CODIGO");
            aux.add("EQUIPO");
            aux.add("GRUPO");
            aux.add("TIPO");
            aux.add("CANTIDAD");
            aux.add("PESO_KG");
            aux.add("P.LISTA_REPOSICION");
            aux.add("P.LISTA_ARRIENDO");
            aux.add("P.BODEGA_REPOSICION");
            aux.add("P.BODEGA_ARRIENDO");
            aux.add("FECHA_GUIA");
            aux.add("BODEGA_DESTINO");
            rs.add(aux);
            
			for (int i=0; i<lista.size(); i++) {
				aux = new ArrayList<String>();
				for (int j=0;j<lista.get(i).size();j++) {
					if(j<5) {
						aux.add(lista.get(i).get(j).trim());
					}else if(j<8) {
						String aux2 = lista.get(i).get(j).trim();
						if(aux2.equals("") || aux2.equals("NA")) {
							aux.add("NA");
						}else {
							aux.add(aux2);
						}
					}else if(j<12) {
						String aux2 = lista.get(i).get(j).trim();
						if(aux2.equals("")) {aux2="NA";}
						aux.add(aux2);
					}else {
						String aux2 = lista.get(i).get(j).trim();
						if(aux2.equals("") || aux2.equals("NA")) {
							aux2 = "0";
						}
						aux.add(aux2);
					}
					
					if(j==lista.get(i).size()-1) {
						
						String aux2 = lista.get(i).get(15).trim();
						if(aux2.equals("") || aux2.equals("NA")) {
							aux.add("NA");
						}else {
							Fechas fechaGuia = Fechas.obtenerFechaDesdeStrAAMMDD(lista.get(i).get(7).trim()+"-"+lista.get(i).get(6).trim()+"-"+lista.get(i).get(5).trim());
							aux.add(fechaGuia.getFechaStrAAMMDD());
						}
						
						aux2 = lista.get(i).get(1).trim();
						if(aux2.equals("")) {
							aux2 = "NA";
						}
						aux.add(aux2);
					}
				}
				rs.add(aux);
			}
		return(rs);
	}
	
	
	
	
	public static Map<Long,List<Double>> valorizaDespachos (Connection con,String db){
		List<OtDespachado> listOtDespacho = OtDespachado.all(con, db);
		Map<Long, Ot> mapOt = Ot.mapAll(con, db);
		// precios de venta y arriendo con descuentos incluidos
		Map<String, List<Double>> mapListaPrecio = ListaPrecio.mapListaPreciosAll(con, db); // map id_bodega+_+id_cotizacion+_+id_equipo = precioVenta,precioArriendo,idUnidadTiempo,idMoneda
		Map<Long,CotizaDetalle> mapCotizaDetalle = CotizaDetalle.mapAll(con, db); // map id_cotizaDetalle
		Map<String,List<Double>> mapPrecioAllSucursal = Precio.maestroPListaAllSucursales(con, db); // map idSucursal_idEquipo = precioVenta,precioArriendo,idUnidadTiempo,idMoneda
		Map<Long,Double> equivTiempo = UnidadTiempo.equivalencia(con, db);
		Map<Long,Cotizacion> mapCotizacion = Cotizacion.mapAllConfirm(con, db);
		Map<Long,List<Double>> map2 = new HashMap<Long,List<Double>>();
		Double v4 =  (double)0;
		Double v5 =  (double)0;
		Double v6 =  (double)0;
		Double v7 =  (double)0;
		Long flagId_ot = (long) 0;
		Long id_ot = (long) 0;
		
		for(int i=0; i<listOtDespacho.size(); i++) {
			if((flagId_ot - listOtDespacho.get(i).getId_ot()) != 0) {
				if(flagId_ot>0) {
					List<Double> aux = new ArrayList<Double>();
					aux.add(v4);
					aux.add(v5);
					aux.add(v6);
					aux.add(v7);
					map2.put(id_ot, aux);
				}
				v4=v5=v6=v7=(double)0;
				flagId_ot = listOtDespacho.get(i).getId_ot();
			}
			// ********
			id_ot = listOtDespacho.get(i).getId_ot();
			Double cantDespacho = listOtDespacho.get(i).getCantidadDespacho();
			Double precioVtaDespacho =  (double)0;
			Double precioArrDespacho =  (double)0;
			Double listaPrecioVtaDespacho =  (double)0;
			Double listaPrecioArrDespacho =  (double)0;
			
			Ot ot = mapOt.get(listOtDespacho.get(i).getId_ot());
			Long id_sucursal = (long)1;
			if(ot!=null) {
				id_sucursal = ot.getId_sucursal();
			}
			
			List<Double> precioDespacho = mapPrecioAllSucursal.get(id_sucursal + "_" + listOtDespacho.get(i).getId_equipoDespacho());
			
			if(precioDespacho!=null) {
				precioVtaDespacho = precioDespacho.get(0);
				Double factor = equivTiempo.get(precioDespacho.get(2).longValue());
				precioArrDespacho = precioDespacho.get(1) / factor; //precio de arriendo diario
			}
			CotizaDetalle cotizaDetalle = mapCotizaDetalle.get(listOtDespacho.get(i).getId_cotizaDetalle());
			if(cotizaDetalle!=null) {
				cantDespacho = listOtDespacho.get(i).cantidadDespacho;
				Cotizacion coti = mapCotizacion.get(cotizaDetalle.id_cotizacion);
				if(coti!=null) {
					List<Double> listaPrecio = mapListaPrecio.get(coti.id_bodegaEmpresa+"_"+cotizaDetalle.id_cotizacion.toString()+"_"+listOtDespacho.get(i).getId_equipoDespacho().toString());
					if(listaPrecio!=null) {
						listaPrecioVtaDespacho =  listaPrecio.get(0);
						Double factor = equivTiempo.get(listaPrecio.get(2).longValue());
						listaPrecioArrDespacho = listaPrecio.get(1) / factor; //precio de arriendo diario
					}
				}
			}
			// ********
			v4 += (cantDespacho*precioVtaDespacho);  			// 4 mto Despacho a Plista
			v5 += (cantDespacho*listaPrecioVtaDespacho);  		// 5 mto Despacho a Preal
			v6 += (cantDespacho*precioArrDespacho*30);  		// 6 arr Despacho 30 dias a Plista
			v7 += (cantDespacho*listaPrecioArrDespacho*30);  	// 7 arr Despacho 30 dias a Preal
			// ********
			if(i == listOtDespacho.size()-1) {
				List<Double> aux = new ArrayList<Double>();
				aux.add(v4);
				aux.add(v5);
				aux.add(v6);
				aux.add(v7);
				map2.put(id_ot, aux);
			}
		}
		return (map2);
	}
	
	public static Map<Long,List<Double>> valorizaPedidos (Connection con,String db){
		List<CotizaDetalle> listCotiDetalle = CotizaDetalle.allOrdenPorIdCoti(con, db);
		Map<Long,Long> map_idCoti_vs_idOt = Ot.mapAll_idCoti_vs_idOt(con, db);
		// precios de venta y arriendo con descuentos incluidos
		Map<String, List<Double>> mapListaPrecio = ListaPrecio.mapListaPreciosAll(con, db); // map id_cotizacion+_+id_equipo = precioVenta,precioArriendo,idUnidadTiempo,idMoneda
		Map<String,List<Double>> mapPrecioAllSucursal = Precio.maestroPListaAllSucursales(con, db); // map idSucursal_idEquipo = precioVenta,precioArriendo,idUnidadTiempo,idMoneda
		Map<Long,Double> equivTiempo = UnidadTiempo.equivalencia(con, db);
		Map<Long,Cotizacion> mapCotizacion = Cotizacion.mapAllConfirm(con, db);
		Map<Long,List<Double>> map2 = new HashMap<Long,List<Double>>();
		Double v0 =  (double)0;
		Double v1 =  (double)0;
		Double v2 =  (double)0;
		Double v3 =  (double)0;
		Long flagId_ot = (long) 0;
		Long id_ot = (long) 0;
		for(int i=0; i<listCotiDetalle.size(); i++) {
			Long auxOt = map_idCoti_vs_idOt.get(listCotiDetalle.get(i).id_cotizacion);
			if(auxOt!=null) {
				if((flagId_ot - auxOt) != 0) {
					if(flagId_ot>0) {
						List<Double> aux = new ArrayList<Double>();
						aux.add(v0);
						aux.add(v1);
						aux.add(v2);
						aux.add(v3);
						map2.put(id_ot, aux);
					}
					v0=v1=v2=v3=(double)0;
					flagId_ot = auxOt;
				}
				// ********
				id_ot = auxOt;
				Double cantOriginal = (double)0;
				Double precioVtaOrigen =  (double)0;
				Double precioArrOrigen =  (double)0;
				Double listaPrecioVtaOrigen =  (double)0;
				Double listaPrecioArrOrigen =  (double)0;
				
				Cotizacion coti = mapCotizacion.get(listCotiDetalle.get(i).id_cotizacion);
				
				List<Double> precioOrigen = mapPrecioAllSucursal.get(coti.id_bodegaEmpresa+"_"+ "_" + listCotiDetalle.get(i).id_equipo);
				
				if(precioOrigen!=null) {
					precioVtaOrigen = precioOrigen.get(0);
					Double factor = equivTiempo.get(precioOrigen.get(2).longValue());
					precioArrOrigen = precioOrigen.get(1) / factor; //precio de arriendo diario
				}
				
				cantOriginal = Double.parseDouble(listCotiDetalle.get(i).cantidad.toString().replaceAll(",", ""));
				
				
				if(coti!=null) {
					List<Double> listaPrecio = mapListaPrecio.get(coti.id_bodegaEmpresa + "_" + listCotiDetalle.get(i).id_cotizacion +"_" + listCotiDetalle.get(i).id_equipo);
					if(listaPrecio!=null) {
						listaPrecioVtaOrigen =  listaPrecio.get(0);
						Double factor = equivTiempo.get(listaPrecio.get(2).longValue());
						listaPrecioArrOrigen = listaPrecio.get(1) / factor; //precio de arriendo diario
					}
				}
				// ********
				v0 += (cantOriginal*precioVtaOrigen);  			// 0 mto Original a Plista
				v1 += (cantOriginal*listaPrecioVtaOrigen);  	// 1 mto Original a Preal
				v2 += (cantOriginal*precioArrOrigen*30);  		// 2 arr Original 30 dias a Plista
				v3 += (cantOriginal*listaPrecioArrOrigen*30);  	// 3 arr Original 30 dias a Preal
				// ********
				if(i == listCotiDetalle.size()-1) {
					List<Double> aux = new ArrayList<Double>();
					aux.add(v0);
					aux.add(v1);
					aux.add(v2);
					aux.add(v3);
					map2.put(id_ot, aux);
				}
			}
		}
		return (map2);
	}
	
	public static List<List<String>> estadoCotiConfYnoConfSinOt (Connection con,String db){
		List<List<String>> datos = new ArrayList<List<String>>();
		
		List<Cotizacion> allSinConfirmarYconfirmadasSinOt = Cotizacion.allSinConfirmarYconfirmadasSinOt(con, db);
		
		Map<Long,Cliente> mapCliente = Cliente.mapAllClientes(con, db);
		Map<Long,Proyecto> mapProyecto = Proyecto.mapAllProyectos(con, db);
		Map<Long,BodegaEmpresa> mapBodega = BodegaEmpresa.mapAll(con, db);
		
		Map<Long,List<Double>> mapValorizaCotizaciones = valorizaCotizaciones(con,db);
		
		Map<Long,CotizaEstado> mapCotizaEstado = CotizaEstado.mapAll(con, db);
		
		for(Cotizacion coti: allSinConfirmarYconfirmadasSinOt) {
			
			String nickCliente = "";
			String nickProyecto = "";
			String nomBodega = "";
			
			Cliente cliente = mapCliente.get(coti.getId_cliente());
			Proyecto proyecto = mapProyecto.get(coti.getId_proyecto());
			BodegaEmpresa bodega = mapBodega.get(coti.getId_bodegaEmpresa());
			
			if(cliente!=null) {
				nickCliente = cliente.getNickName();
			}
			if(proyecto!=null) {
				nickProyecto = proyecto.getNickName();
			}
			if(bodega!=null) {
				nomBodega = bodega.getNombre();
			}
			
			
			String fechaConfirmada = "";
			if((long) coti.getConfirmada() == (long) 1) {
				fechaConfirmada = Fechas.DDMMAA(coti.getFechaConfirmada());
			}
			
			String estadoCoti = "";
			CotizaEstado estado = mapCotizaEstado.get(coti.getId_cotizaEstado());
			if(estado!=null) {
				estadoCoti = estado.getEstado();
			}
			
			
			List<Double> cotiValorizada = mapValorizaCotizaciones.get(coti.getId());
			
			if(cotiValorizada!=null) {
				List<String> aux = new ArrayList<String>();
				aux.add(coti.numero.toString());					// numero de cotizacion
				aux.add(coti.fecha);
				aux.add(nickCliente);
				aux.add(nickProyecto);
				aux.add(nomBodega);
				aux.add(myformatint.format(cotiValorizada.get(0))); // 5 original vta a plista
				aux.add(myformatint.format(cotiValorizada.get(1))); // 6 original vta a Preal
				aux.add(myformatint.format(cotiValorizada.get(2))); // 7 original arr a plista
				aux.add(myformatint.format(cotiValorizada.get(3))); // 8 original arr a Preal
				aux.add(coti.getObservaciones());					// 9
				aux.add(fechaConfirmada);							// 10
				aux.add(estadoCoti);								// 11
				datos.add(aux);	
			}
			
		}
		
		return(datos);
	}
	
	
	public static Map<Long,List<Double>> valorizaCotizaciones (Connection con,String db){
		List<CotizaDetalle> listCotiDetalle = CotizaDetalle.allOrdenPorIdCoti(con, db);
		// precios de venta y arriendo con descuentos incluidos
		Map<String, List<Double>> mapListaPrecio = ListaPrecio.mapListaPreciosAll(con, db); // map id_cotizacion+_+id_equipo = precioVenta,precioArriendo,idUnidadTiempo,idMoneda
		Map<String,List<Double>> mapPrecioAllSucursal = Precio.maestroPListaAllSucursales(con, db); // map idSucursal_idEquipo = precioVenta,precioArriendo,idUnidadTiempo,idMoneda
		Map<Long,Double> equivTiempo = UnidadTiempo.equivalencia(con, db);
		Map<Long,Cotizacion> mapCotizacion = Cotizacion.mapAllConfirm(con, db);
		Map<Long,List<Double>> map2 = new HashMap<Long,List<Double>>();
		Double v0 =  (double)0;
		Double v1 =  (double)0;
		Double v2 =  (double)0;
		Double v3 =  (double)0;
		Long flagId_coti = (long) 0;
		Long id_coti = (long) 0;
		for(int i=0; i<listCotiDetalle.size(); i++) {
				id_coti = listCotiDetalle.get(i).getId_cotizacion();
				if(((long) flagId_coti - (long) id_coti) != 0) {
					if((long) flagId_coti != (long) 0) {
						List<Double> aux = new ArrayList<Double>();
						aux.add(v0);
						aux.add(v1);
						aux.add(v2);
						aux.add(v3);
						map2.put(id_coti, aux);
					}
					v0=v1=v2=v3=(double)0;
					flagId_coti = id_coti;
				}
				
				Double cantOriginal = (double)0;
				Double precioVtaOrigen =  (double)0;
				Double precioArrOrigen =  (double)0;
				Double listaPrecioVtaOrigen =  (double)0;
				Double listaPrecioArrOrigen =  (double)0;
				
				Cotizacion coti = mapCotizacion.get(listCotiDetalle.get(i).id_cotizacion);
				
				List<Double> precioOrigen = mapPrecioAllSucursal.get(coti.id_bodegaEmpresa+"_"+ "_" + listCotiDetalle.get(i).id_equipo);
				
				if(precioOrigen!=null) {
					precioVtaOrigen = precioOrigen.get(0);
					Double factor = equivTiempo.get(precioOrigen.get(2).longValue());
					precioArrOrigen = precioOrigen.get(1) / factor; //precio de arriendo diario
				}
				cantOriginal = Double.parseDouble(listCotiDetalle.get(i).cantidad.toString().replaceAll(",", ""));
				if(coti!=null) {
					List<Double> listaPrecio = mapListaPrecio.get(coti.id_bodegaEmpresa+"_"+listCotiDetalle.get(i).id_cotizacion+"_"+listCotiDetalle.get(i).id_equipo);
					if(listaPrecio!=null) {
						listaPrecioVtaOrigen =  listaPrecio.get(0);
						Double factor = equivTiempo.get(listaPrecio.get(2).longValue());
						listaPrecioArrOrigen = listaPrecio.get(1) / factor; //precio de arriendo diario
					}
				}
				// ********
				v0 += (cantOriginal*precioVtaOrigen);  			// 0 mto Original a Plista
				v1 += (cantOriginal*listaPrecioVtaOrigen);  	// 1 mto Original a Preal
				v2 += (cantOriginal*precioArrOrigen*30);  		// 2 arr Original 30 dias a Plista
				v3 += (cantOriginal*listaPrecioArrOrigen*30);  	// 3 arr Original 30 dias a Preal
				// ********
				if(i == listCotiDetalle.size()-1) {
					List<Double> aux = new ArrayList<Double>();
					aux.add(v0);
					aux.add(v1);
					aux.add(v2);
					aux.add(v3);
					map2.put(id_coti, aux);
				}
		}
		return (map2);
	}
	
	
	public static List<List<String>> estadoOtDespachos (Connection con,String db){
		
		Map<Long,List<Double>> mapPedidos = valorizaPedidos(con,db);
		Map<Long,List<Double>> mapDespachos = valorizaDespachos(con,db);
		
		Map<Long,Long> mapListOt = new HashMap<Long,Long>();
		
		for (Map.Entry<Long,List<Double>> entry : mapPedidos.entrySet()) {
			mapListOt.put(entry.getKey(), entry.getKey());
		}
		
		for (Map.Entry<Long,List<Double>> entry : mapDespachos.entrySet()) {
			mapListOt.put(entry.getKey(), entry.getKey());
		}
		
		Map<Long,List<Double>> map2 = new HashMap<Long,List<Double>>();
		
		for (Map.Entry<Long,Long> entry : mapListOt.entrySet()) {
			List<Double> aux = new ArrayList<Double>();
			List<Double> pedidos = mapPedidos.get(entry.getKey());
			if(pedidos!=null) {
				aux.add(pedidos.get(0));
				aux.add(pedidos.get(1));
				aux.add(pedidos.get(2));
				aux.add(pedidos.get(3));
			}else {
				aux.add((double)0);
				aux.add((double)0);
				aux.add((double)0);
				aux.add((double)0);
			}
			List<Double> despachos = mapDespachos.get(entry.getKey());
			if(despachos!=null) {
				aux.add(despachos.get(0));
				aux.add(despachos.get(1));
				aux.add(despachos.get(2));
				aux.add(despachos.get(3));
			}else {
				aux.add((double)0);
				aux.add((double)0);
				aux.add((double)0);
				aux.add((double)0);
			}
			map2.put(entry.getKey(), aux);
			
		}
		
		Map<Long,Ot> mapOt = Ot.mapAll(con, db);
		Map<Long,Cotizacion> mapCotizacion = Cotizacion.mapAll(con, db);
		Map<Long,BodegaEmpresa> mapBodega = BodegaEmpresa.mapAll(con, db);
		
		Map<Long,OtEstado> mapOtEstado = OtEstado.mapAll(con, db);
		
		
		List<List<String>> datos = new ArrayList<List<String>>();
		
		for (Map.Entry<Long,List<Double>> entry : map2.entrySet()) {
			Ot ot = mapOt.get(entry.getKey());
			if(ot!=null) {
				
				Cotizacion coti = mapCotizacion.get(ot.getId_cotizacion());
				String numeroCotizacion = "", fechaCotizacion = "", observaciones_coti = "";
				Long id_bodega = (long)0;
				if(coti!=null) {
					numeroCotizacion = coti.getNumero().toString();
					fechaCotizacion = coti.getFecha();
					id_bodega = coti.getId_bodegaEmpresa();
					observaciones_coti = coti.getObservaciones();
				}
				
				BodegaEmpresa bodega = mapBodega.get(id_bodega);
				String nomProyecto = "", nomCliente = "", nomBodega = "";
				if(bodega!=null) {
					nomProyecto = bodega.getNickProyecto();
					nomCliente = bodega.getNickCliente();
					nomBodega = bodega.getNombre();
				}
				
				String estadoOt = "";
				OtEstado otEstado = mapOtEstado.get(ot.getId_otEstado());
				if(otEstado!=null) {
					estadoOt = otEstado.getEstado();
				}
				
				List<String> aux = new ArrayList<String>();
				aux.add(numeroCotizacion);			// numero de cotizacion
				aux.add(fechaCotizacion);
				aux.add(ot.numero.toString());
				aux.add(ot.fecha);
				aux.add(nomCliente);
				aux.add(nomProyecto);
				aux.add(nomBodega);
				aux.add(myformatint.format(entry.getValue().get(0))); // original a plista
				aux.add(myformatint.format(entry.getValue().get(4))); // despacho a plista
				aux.add(myformatint.format(entry.getValue().get(1))); // original a Preal
				aux.add(myformatint.format(entry.getValue().get(5))); // despacho a Preal
				aux.add(myformatint.format(entry.getValue().get(2)));
				aux.add(myformatint.format(entry.getValue().get(6)));
				aux.add(myformatint.format(entry.getValue().get(3)));
				aux.add(myformatint.format(entry.getValue().get(7)));
				
				aux.add(observaciones_coti);							//15
				aux.add(ot.observaciones);								//16
				
				aux.add(estadoOt);										//17
				
				datos.add(aux);	
			}
		}
		
		//ordena por numero cotizacion descendente
		for(int i=0;i<(datos.size()-1);i++){
            for(int j=i+1;j<datos.size();j++){
            	Long a = Long.parseLong(datos.get(i).get(2));
            	Long b = Long.parseLong(datos.get(j).get(2));
            	
                if(a < b){
                    //Intercambiamos valores
                	List<String> listaAuxiliar = datos.get(i);
                	datos.set(i, datos.get(j));
                	datos.set(j, listaAuxiliar);
                }
            }
		}
		
		return(datos);
		
	}
	
	public static File estadoCotiOtExcel(List<List<String>> lista, InputStream formato) {
		
		File tmp = TempFile.createTempFile("tmp","null");
		
		 try {
			 Workbook libro = WorkbookFactory.create(formato);
			formato.close();
            
            Sheet hoja1 = libro.getSheetAt(0);
            Row row = null;
            Cell cell = null;
            
            Long ent = (long)0;
            Double dec = (double)0;
            
            // limpia los datos
            int numRow = 1;
            for(int i=numRow;i<40000;i++){
            	row = hoja1.createRow(i);
            	hoja1.removeRow(row);
            }
			
			// DATOS:
			for(int i=0;i<lista.size();i++){
				
				row = hoja1.createRow(numRow+i);
				row = hoja1.getRow(numRow+i); 
				
				int numCell = 0;
				
				ent = Long.parseLong(lista.get(i).get(0));
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(ent);
				
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(1));
				
				ent = Long.parseLong(lista.get(i).get(2));
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(ent);
				
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(3));
				
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(4));
				
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(5));
				
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(6));
				
				dec = Double.parseDouble(lista.get(i).get(7).replaceAll(",", ""));
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(dec);
				
				dec = Double.parseDouble(lista.get(i).get(8).replaceAll(",", ""));
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(dec);
				
				dec = Double.parseDouble(lista.get(i).get(9).replaceAll(",", ""));
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(dec);
				
				dec = Double.parseDouble(lista.get(i).get(10).replaceAll(",", ""));
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(dec);
				
				dec = Double.parseDouble(lista.get(i).get(11).replaceAll(",", ""));
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(dec);
				
				dec = Double.parseDouble(lista.get(i).get(12).replaceAll(",", ""));
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(dec);
				
				dec = Double.parseDouble(lista.get(i).get(13).replaceAll(",", ""));
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(dec);
				
				dec = Double.parseDouble(lista.get(i).get(14).replaceAll(",", ""));
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(dec);
				
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(17));
				
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(15));
				
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(16));
			}
				
			// Write the output to a file tmp
			FileOutputStream fileOut = new FileOutputStream(tmp);
			libro.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
    			e.printStackTrace();
        }
		return tmp;
	}
	
public static File estadoCotiSinOtExcel(List<List<String>> lista, InputStream formato) {
		
		File tmp = TempFile.createTempFile("tmp","null");
		
		 try {
			 Workbook libro = WorkbookFactory.create(formato);
			formato.close();
            
            Sheet hoja1 = libro.getSheetAt(0);
            Row row = null;
            Cell cell = null;
            
            Long ent = (long)0;
            Double dec = (double)0;
            
            // limpia los datos
            int numRow = 1;
            for(int i=numRow;i<40000;i++){
            	row = hoja1.createRow(i);
            	hoja1.removeRow(row);
            }
			
			// DATOS:
			for(int i=0;i<lista.size();i++){
				
				row = hoja1.createRow(numRow+i);
				row = hoja1.getRow(numRow+i); 
				
				int numCell = 0;
				
				ent = Long.parseLong(lista.get(i).get(0));
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(ent);
				
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(1));
				
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(2));
				
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(3));
				
				dec = Double.parseDouble(lista.get(i).get(5).replaceAll(",", ""));
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(dec);
				
				dec = Double.parseDouble(lista.get(i).get(6).replaceAll(",", ""));
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(dec);
				
				dec = Double.parseDouble(lista.get(i).get(7).replaceAll(",", ""));
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(dec);
				
				dec = Double.parseDouble(lista.get(i).get(8).replaceAll(",", ""));
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(dec);
				
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(10));
				
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(11));
				
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(9));
			}
				
			// Write the output to a file tmp
			FileOutputStream fileOut = new FileOutputStream(tmp);
			libro.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
    			e.printStackTrace();
        }
		return tmp;
	}
	
	public static File listaMatrizEquiposHOHE0(Connection con, String db, String corte, InputStream formato) {
		List<List<String>> listaHohe = new ArrayList<List<String>>();
		Map<String,List<String>> mapArr = ReportHohe.listaMatrizEquiposHOHE2(con, db, "ARRIENDO", corte);
		Map<String,List<String>> mapVta = ReportHohe.listaMatrizEquiposHOHE2(con, db, "VENTA", corte);
		Map<String,List<String>> mapUne = new HashMap<String,List<String>>();
		for (Map.Entry<String,List<String>> map : mapArr.entrySet()) {
			mapUne.put(map.getKey(), map.getValue());
		}
		for (Map.Entry<String,List<String>> map : mapVta.entrySet()) {
			mapUne.put(map.getKey(), map.getValue());
		}
		
		for (Map.Entry<String,List<String>> map : mapUne.entrySet()) {
			
			List<String> list1 = map.getValue();
			Double arr = (double)0;
			
			List<String> laux = mapArr.get(map.getKey());
			if(laux!=null) { arr = Double.parseDouble(laux.get(9)); }
			Double vta = (double)0;
			
			laux = mapVta.get(map.getKey());
			if(laux!=null) { vta = Double.parseDouble(laux.get(9)); }
			Double tot = arr + vta;
			
			List<String> aux = new ArrayList<String>();
			aux.add(list1.get(0));	//0 id_bodega
			aux.add(list1.get(1));	//1 bodega
			aux.add(list1.get(2));	//2 tipoBodega (interna externa)
			aux.add(list1.get(3));	//3 grupo
			aux.add(list1.get(4));	//4 id_equipo
			aux.add(list1.get(5));	//5 codigo
			aux.add(list1.get(6));	//6 equipo
			aux.add(list1.get(7));	//7 kg
			aux.add(list1.get(8));	//8 m2
			aux.add(arr.toString());	//9 cantidad arriendo
			aux.add(vta.toString());	//10 cantidad venta
			aux.add(tot.toString());	//11 cantidad todo
			aux.add(corte);				//12 fecha corte
			listaHohe.add(aux);
		}
		Map<String,List<Double>> mapPrecios = ReportHohe.mapListaPrecioMinimos(con, db);
		
		for (int i=0; i<listaHohe.size(); i++) {
			List<String> aux = listaHohe.get(i);
			List<Double> precios = mapPrecios.get(aux.get(0)+"_"+aux.get(4));
			if(precios!=null) {
				aux.add(precios.get(0).toString());	//13 precio de reposicion minimo
				aux.add(precios.get(1).toString()); //14 precio de arriendo minimo
			}else {
				aux.add("0");
				aux.add("0");
			}
		}
		File tmp = listaMatrizEquiposHOHE1(listaHohe, formato);
		return(tmp);
	}
	
	public static Map<String, List<Double>> mapListaPrecioMinimos(Connection con, String db){
		Map<String,List<Double>> map = new HashMap<String,List<Double>>();
		try {
			PreparedStatement smt20 = con
					.prepareStatement("select id_bodegaEmpresa, id_equipo, min(precioReposicion), min(precioArriendo) from `"+db+"`.listaPrecio group by id_bodegaEmpresa, id_equipo;");
			ResultSet rs20 = smt20.executeQuery();
			while (rs20.next()) {
				List<Double> aux = new ArrayList<Double>();
				aux.add(rs20.getDouble(3));   								// 0 precio venta
				aux.add(rs20.getDouble(4));									// 1 precio arriendo
				map.put(rs20.getString(1)+"_"+rs20.getString(2), aux);
			}
			rs20.close();smt20.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return(map);
	}
	
	public static File listaMatrizEquiposHOHE0Coti(Connection con, String db, String corte, InputStream formato) {
		List<List<String>> listaHohe = new ArrayList<List<String>>();
		
		// KEY = id_bodega _ id_equipo _ id_cotizacion _ tipo:
		Map<String,List<String>> mapArr = ReportHohe.listaMatrizEquiposHOHE2Coti(con, db, "ARRIENDO", corte);
		Map<String,List<String>> mapVta = ReportHohe.listaMatrizEquiposHOHE2Coti(con, db, "VENTA", corte);
		
		//UNE AMBOS RESULTADOS
		Map<String,List<String>> mapUne = new HashMap<String,List<String>>();
		for (Map.Entry<String,List<String>> map : mapArr.entrySet()) {
			mapUne.put(map.getKey(), map.getValue());
		}
		for (Map.Entry<String,List<String>> map : mapVta.entrySet()) {
			mapUne.put(map.getKey(), map.getValue());
		}
		
		
		for (Map.Entry<String,List<String>> map : mapUne.entrySet()) {
			
			String auxKey[] = map.getKey().split("_");
			
			List<String> list1 = map.getValue();
			Double arr = (double)0;
			
			List<String> laux = mapArr.get(map.getKey());
			if(laux!=null) { 
				arr = Double.parseDouble(laux.get(9)); 
			}
			Double vta = (double)0;
			
			laux = mapVta.get(map.getKey());
			if(laux!=null) { 
				vta = Double.parseDouble(laux.get(9)); 
			}
			Double tot = arr + vta;
			
			
			List<String> aux = new ArrayList<String>();
			aux.add(list1.get(0));	//0 id_bodega
			aux.add(list1.get(1));	//1 bodega
			aux.add(list1.get(2));	//2 tipoBodega (interna externa)
			aux.add(list1.get(3));	//3 grupo
			aux.add(list1.get(4));	//4 id_equipo
			aux.add(list1.get(5));	//5 codigo
			aux.add(list1.get(6));	//6 equipo
			aux.add(list1.get(7));	//7 kg
			aux.add(list1.get(8));	//8 m2
			aux.add(arr.toString());	//9 cantidad arriendo
			aux.add(vta.toString());	//10 cantidad venta
			aux.add(tot.toString());	//11 cantidad todo
			aux.add(corte);				//12 fecha corte
			aux.add(list1.get(11));		//13 numeroCotizacion
			aux.add(auxKey[2]);			//14 id_cotizacion
			listaHohe.add(aux);
		}
		
		Map<String,List<Double>> mapPrecios = ReportHohe.mapListaPrecioCoti(con, db);
		
		for (int i=0; i<listaHohe.size(); i++) {
			
			List<String> aux = listaHohe.get(i);
			
			List<Double> precios = mapPrecios.get(aux.get(0)+"_"+aux.get(4)+"_"+aux.get(14));
			
			if(precios!=null) {
				aux.add(precios.get(0).toString());	//15 precio de reposicion 
				aux.add(precios.get(1).toString()); //16 precio de arriendo 
			}else {
				aux.add("0");
				aux.add("0");
			}
		}
		
		
		File tmp = listaMatrizEquiposHOHE1Coti(listaHohe, formato);
		
		
		return(tmp);
	}
	
	public static Map<String, List<Double>> mapListaPrecioCoti(Connection con, String db){
		Map<String,List<Double>> map = new HashMap<String,List<Double>>();
		try {
			PreparedStatement smt20 = con
					.prepareStatement("select id_bodegaEmpresa, id_equipo, avg(precioReposicion), avg(precioArriendo), id_cotizacion from `"+db+"`.listaPrecio group by id_bodegaEmpresa, id_equipo, id_cotizacion;");
			ResultSet rs20 = smt20.executeQuery();
			while (rs20.next()) {
				List<Double> aux = new ArrayList<Double>();
				aux.add(rs20.getDouble(3));   								// 0 precio venta
				aux.add(rs20.getDouble(4));									// 1 precio arriendo
				map.put(rs20.getString(1)+"_"+rs20.getString(2)+"_"+rs20.getString(5), aux);	// key id_bodegaEmpresa_id_equipo_id_cotizacion
			}
			rs20.close();smt20.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return(map);
	}
	
public static File listaMatrizEquiposHOHE1Coti(List<List<String>> lista, InputStream formato) {
		
		File tmp = TempFile.createTempFile("tmp","null");
		
		 try {
		 	Workbook libro = WorkbookFactory.create(formato);
			formato.close();
            
            Sheet hoja1 = libro.getSheetAt(0);
            Row row = null;
            Cell cell = null;
            
            
            row = hoja1.getRow(0); 
            
            cell = row.getCell(13);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("p_repos");
			
			cell = row.getCell(14);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("p_arriendo");
			
			cell = row.createCell(15);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("num_coti");
			
			
            Double numero = (double)0;
            
            // limpia los datos
            int numRow = 1;
            for(int i=numRow;i<40000;i++){
            	row = hoja1.createRow(i);
            	hoja1.removeRow(row);
            }
			
			// DATOS:
			for(int i=0;i<lista.size();i++){
				
				row = hoja1.createRow(numRow+i);
				row = hoja1.getRow(numRow+i); 
				
				int numCell = 0;
				
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(12));
				
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(0));
				
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(1));
				
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(2));
				
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(3));
				
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(4));
				
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(5));
				
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(6));
				
				numero = Double.parseDouble(lista.get(i).get(7));
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(numero);
				
				numero = Double.parseDouble(lista.get(i).get(8));
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(numero);
				
				numero = Double.parseDouble(lista.get(i).get(9));
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(numero);
				
				numero = Double.parseDouble(lista.get(i).get(10));
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(numero);
				
				numero = Double.parseDouble(lista.get(i).get(11));
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(numero);
				
				numero = Double.parseDouble(lista.get(i).get(15));
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(numero);
				
				numero = Double.parseDouble(lista.get(i).get(16));
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(numero);
				
				numero = Double.parseDouble(lista.get(i).get(13));
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(numero);
				
				
				
			}
			
			// Write the output to a file tmp
			FileOutputStream fileOut = new FileOutputStream(tmp);
			libro.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
    			e.printStackTrace();
        }
		return tmp;
	}

	
	public static File listaMatrizEquiposHOHE1(List<List<String>> lista, InputStream formato) {
		
		File tmp = TempFile.createTempFile("tmp","null");
		
		 try {
		 	Workbook libro = WorkbookFactory.create(formato);
			formato.close();
            
            Sheet hoja1 = libro.getSheetAt(0);
            Row row = null;
            Cell cell = null;
            
            Double numero = (double)0;
            
            // limpia los datos
            int numRow = 1;
            for(int i=numRow;i<40000;i++){
            	row = hoja1.createRow(i);
            	hoja1.removeRow(row);
            }
			
			// DATOS:
			for(int i=0;i<lista.size();i++){
				
				row = hoja1.createRow(numRow+i);
				row = hoja1.getRow(numRow+i); 
				
				int numCell = 0;
				
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(12));
				
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(0));
				
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(1));
				
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(2));
				
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(3));
				
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(4));
				
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(5));
				
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(6));
				
				numero = Double.parseDouble(lista.get(i).get(7));
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(numero);
				
				numero = Double.parseDouble(lista.get(i).get(8));
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(numero);
				
				numero = Double.parseDouble(lista.get(i).get(9));
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(numero);
				
				numero = Double.parseDouble(lista.get(i).get(10));
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(numero);
				
				numero = Double.parseDouble(lista.get(i).get(11));
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(numero);
				
				numero = Double.parseDouble(lista.get(i).get(13));
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(numero);
				
				numero = Double.parseDouble(lista.get(i).get(14));
				cell = row.createCell(numCell++);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(numero);
				
			}
			
			// Write the output to a file tmp
			FileOutputStream fileOut = new FileOutputStream(tmp);
			libro.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
    			e.printStackTrace();
        }
		return tmp;
	}
	
	public static Map<String,List<String>>  listaMatrizEquiposHOHE2(Connection con, String db, String tipo, String corte) {
		Map<String,List<String>> map = new HashMap<String,List<String>>();
		try {
			
			List<List<String>> listaEquipos = new ArrayList<List<String>>();
			PreparedStatement smt20 = con
					.prepareStatement(" select id_equipo from `"+db+"`.movimiento group by id_equipo " + 
							" having if(sum(cantidad*if(id_tipoMovimiento=1,1,-1))=-0,0,sum(cantidad*if(id_tipoMovimiento=1,1,-1)))>0");
			String listaCond ="";
			ResultSet rs20 = smt20.executeQuery();
			while (rs20.next()) {
				listaCond = listaCond + rs20.getString(1) + ",";
			}
			rs20.close();smt20.close();
			
			
			if(listaCond.length()>0) {
				listaCond = listaCond.substring(0,listaCond.length()-1);
				
				PreparedStatement smt = con
						.prepareStatement(" select equipo.id, equipo.codigo, equipo.nombre,equipo.id_grupo,grupo.nombre " + 
								" from `"+db+"`.equipo left join `"+db+"`.grupo on grupo.id=equipo.id_grupo " + 
								" where equipo.id in ("+listaCond+") " + 
								" order by equipo.nombre;");
				ResultSet rs = smt.executeQuery();
				while (rs.next()) {
					List<String> aux = new ArrayList<String>();
					aux.add(rs.getString(1)); // idEquipo
					aux.add(rs.getString(2)); // codigo
					aux.add(rs.getString(3)); // nombre equipo
					aux.add(rs.getString(4)); // idGrupo
					aux.add(rs.getString(5)); // nombre grupo o familia
					listaEquipos.add(aux);
				}
				smt.close();rs.close();
			}
			
			// MAP CANTIDADES POR TIPO (ARRIENDO VENTA O TODO) SOLO BODEGAS EXTERNAS
			String filtraTipo="";
			if(tipo.equals("ARRIENDO")) {
				filtraTipo = " and movimiento.esVenta=0 ";
			}else if(tipo.equals("VENTA")) {
				filtraTipo = " and movimiento.esVenta=1 "; 
			}else if(tipo.equals("TODO")){
				filtraTipo=" ";
			}
			Map<String,Double> mapCantidades = new HashMap<String,Double>();
			
			PreparedStatement smt21 = con
					.prepareStatement(" select id from `"+db+"`.bodegaEmpresa where vigente=1");
			String listaCond2 ="";
			ResultSet rs21 = smt21.executeQuery();
			while (rs21.next()) {
				listaCond2 = listaCond2 + rs21.getString(1) + ",";
			}
			rs21.close();smt21.close();
			
			if(listaCond2.length()>0) {
				listaCond2 = listaCond2.substring(0,listaCond2.length()-1);
				PreparedStatement smt2 = con
						.prepareStatement(" select " + 
								" concat(bodegaEmpresa.id,'_',equipo.id), " + 
								" if("
								+ "sum(if(movimiento.id_tipoMovimiento=1,1,-1)*movimiento.cantidad)=-0,0,"
								+ "sum(if(movimiento.id_tipoMovimiento=1,1,-1)*movimiento.cantidad))  as CANTIDAD " +
								" from `"+db+"`.movimiento " + 
								" left join `"+db+"`.equipo on equipo.id=movimiento.id_equipo " + 
								" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id=movimiento.id_bodegaEmpresa " + 
								" left join `"+db+"`.guia on guia.id = movimiento.id_guia " + 
								" left join `"+db+"`.compra on compra.id = movimiento.id_compra " + 
								" left join `"+db+"`.factura on factura.id = compra.id_factura " + 
								" left join `"+db+"`.baja on baja.id = movimiento.id_baja " + 
								" left join `"+db+"`.actaBaja on actaBaja.id = baja.id_actaBaja " +
								" where movimiento.id_bodegaEmpresa in ("+listaCond2+") " + filtraTipo +  " and bodegaEmpresa.esInterna <> 1 "+
								" and (guia.fecha <=? or guia.fecha is null) and (factura.fecha <=? or factura.fecha is null)  and (actaBaja.fecha <=? or actaBaja.fecha is null) "+
								" group by bodegaEmpresa.id,equipo.id " + 
								" having if(sum(if(movimiento.id_tipoMovimiento=2,movimiento.cantidad*-1,movimiento.cantidad))=-0,0,sum(if(movimiento.id_tipoMovimiento=2,movimiento.cantidad*-1,movimiento.cantidad)))>0;");
				smt2.setString(1, corte);
				smt2.setString(2, corte);
				smt2.setString(3, corte);
				ResultSet rs2 = smt2.executeQuery();
				while (rs2.next()) {
					mapCantidades.put(rs2.getString(1), rs2.getDouble(2));
				}
				rs2.close();smt2.close();
				// MAP CANTIDADES SOLO BODEGAS INTERNAS (NO SE CLASIFICA POR TIPO ARRIENDO VENTA O TODO)
				PreparedStatement smt22 = con
						.prepareStatement(" select " + 
								" concat(bodegaEmpresa.id,'_',equipo.id), " + 
								" if("
								+ "sum(if(movimiento.id_tipoMovimiento=1,1,-1)*movimiento.cantidad)=-0,0,"
								+ "sum(if(movimiento.id_tipoMovimiento=1,1,-1)*movimiento.cantidad))  as CANTIDAD " +
								" from `"+db+"`.movimiento " + 
								" left join `"+db+"`.equipo on equipo.id=movimiento.id_equipo " + 
								" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id=movimiento.id_bodegaEmpresa " + 
								" left join `"+db+"`.guia on guia.id = movimiento.id_guia " + 
								" left join `"+db+"`.compra on compra.id = movimiento.id_compra " + 
								" left join `"+db+"`.factura on factura.id = compra.id_factura " + 
								" left join `"+db+"`.baja on baja.id = movimiento.id_baja " + 
								" left join `"+db+"`.actaBaja on actaBaja.id = baja.id_actaBaja " +
								" where movimiento.id_bodegaEmpresa in ("+listaCond2+") and bodegaEmpresa.esInterna = 1 " +
								" and (guia.fecha <=? or guia.fecha is null) and (factura.fecha <=? or factura.fecha is null)  and (actaBaja.fecha <=? or actaBaja.fecha is null) "+
								" group by bodegaEmpresa.id,equipo.id " + 
								" having if(sum(if(movimiento.id_tipoMovimiento=2,movimiento.cantidad*-1,movimiento.cantidad))=-0,0,sum(if(movimiento.id_tipoMovimiento=2,movimiento.cantidad*-1,movimiento.cantidad)))>0;");
				smt22.setString(1, corte);
				smt22.setString(2, corte);
				smt22.setString(3, corte);
				ResultSet rs22 = smt22.executeQuery();
				while (rs22.next()) {
					mapCantidades.put(rs22.getString(1), rs22.getDouble(2));
				}
				rs22.close();smt22.close();
			}
				
			
			
			List<List<String>> listaBodegas = ReportInventarios.listaBodegasConStock(con, db, corte);
			Map<Long,Double> pesos = Atributo.mapAtributoPESO(con, db);
			Map<Long,Double> m2 = Atributo.mapAtributoM2(con, db);
				
			for(int i=0;i<listaEquipos.size();i++) {
				Double kgHohe = pesos.get(Long.parseLong(listaEquipos.get(i).get(0).trim()));
				if(kgHohe==null) {
					kgHohe = (double)0; 
				}
				Double supM2Hohe = m2.get(Long.parseLong(listaEquipos.get(i).get(0).trim()));
				if(supM2Hohe==null) {
					supM2Hohe = (double)0;
				}
				for(int j=0;j<listaBodegas.size();j++) {
					Double cantAux = mapCantidades.get(listaBodegas.get(j).get(0)+"_"+listaEquipos.get(i).get(0));
					if(cantAux==null) cantAux=(double)0;
					if(cantAux>0) {
						String tipoBodega = "externa";
						if(listaBodegas.get(j).get(2).equals("1")) {
							tipoBodega = "interna";
						}
						List<String> aux = new ArrayList<String>();
						aux.add(listaBodegas.get(j).get(0));	//0 id_bodega
						aux.add(listaBodegas.get(j).get(1));	//1 bodega
						aux.add(tipoBodega);					//2 tipoBodega (interna externa)
						aux.add(listaEquipos.get(i).get(4));	//3 grupo
						aux.add(listaEquipos.get(i).get(0));	//4 id_equipo
						aux.add(listaEquipos.get(i).get(1));	//5 codigo
						aux.add(listaEquipos.get(i).get(2));	//6 equipo
						aux.add(kgHohe.toString());				//7 kg
						aux.add(supM2Hohe.toString());			//8 m2
						aux.add(cantAux.toString());			//9 CANTIDAD
						aux.add(tipo);							//10 tipo arr o vta
						// MAPEA ID_BODEGA, ID_EQUIPO, TIPO(VTA ARR TODO) , LIST AUX
						map.put(aux.get(0)+"_"+aux.get(4)+"_"+aux.get(10), aux);
					}
				}
			}
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		
		return (map);
	}
	
	public static Map<String,List<String>>  listaMatrizEquiposHOHE2Coti(Connection con, String db, String tipo, String corte) {
		Map<String,List<String>> map = new HashMap<String,List<String>>();
		try {
			
			
			// MAP CANTIDADES POR TIPO (ARRIENDO VENTA O TODO) SOLO BODEGAS EXTERNAS
			String filtraTipo="";
			if(tipo.equals("ARRIENDO")) {
				filtraTipo = " and movimiento.esVenta=0 ";
			}else if(tipo.equals("VENTA")) {
				filtraTipo = " and movimiento.esVenta=1 "; 
			}else if(tipo.equals("TODO")){
				filtraTipo=" ";
			}
			
			Map<String,Double> mapCantidades = new HashMap<String,Double>();
			
			PreparedStatement smt21 = con
					.prepareStatement(" select id from `"+db+"`.bodegaEmpresa where vigente=1");
			String listaCond2 ="";
			ResultSet rs21 = smt21.executeQuery();
			while (rs21.next()) {
				listaCond2 = listaCond2 + rs21.getString(1) + ",";
			}
			rs21.close();smt21.close();
			
			if(listaCond2.length()>0) {
				listaCond2 = listaCond2.substring(0,listaCond2.length()-1);
				PreparedStatement smt2 = con
						.prepareStatement(" select " + 
								" concat(movimiento.id_bodegaEmpresa,'_',movimiento.id_equipo,'_',movimiento.id_cotizacion), " + 
								" if("
								+ "sum(if(movimiento.id_tipoMovimiento=1,1,-1)*movimiento.cantidad)=-0,0,"
								+ "sum(if(movimiento.id_tipoMovimiento=1,1,-1)*movimiento.cantidad))  as CANTIDAD " +
								" from `"+db+"`.movimiento " + 
//								" left join `"+db+"`.equipo on equipo.id=movimiento.id_equipo " + 
								" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id=movimiento.id_bodegaEmpresa " + 
								" left join `"+db+"`.guia on guia.id = movimiento.id_guia " + 
								" left join `"+db+"`.compra on compra.id = movimiento.id_compra " + 
								" left join `"+db+"`.factura on factura.id = compra.id_factura " + 
								" left join `"+db+"`.baja on baja.id = movimiento.id_baja " + 
								" left join `"+db+"`.actaBaja on actaBaja.id = baja.id_actaBaja " +
								" where movimiento.id_bodegaEmpresa in ("+listaCond2+") " + filtraTipo +  " and bodegaEmpresa.esInterna <> 1 "+
								" and (guia.fecha <=? or guia.fecha is null) and (factura.fecha <=? or factura.fecha is null)  and (actaBaja.fecha <=? or actaBaja.fecha is null) "+
								" group by bodegaEmpresa.id, movimiento.id_equipo, movimiento.id_cotizacion " + 
								" having if(sum(if(movimiento.id_tipoMovimiento=2,movimiento.cantidad*-1,movimiento.cantidad))=-0,0,sum(if(movimiento.id_tipoMovimiento=2,movimiento.cantidad*-1,movimiento.cantidad)))>0;");
				smt2.setString(1, corte);
				smt2.setString(2, corte);
				smt2.setString(3, corte);
				ResultSet rs2 = smt2.executeQuery();
				while (rs2.next()) {
					mapCantidades.put(rs2.getString(1), rs2.getDouble(2)); // key movimiento.id_bodegaEmpresa,'_',movimiento.id_equipo,'_',movimiento.id_cotizacion value cantidad
				}
				rs2.close();smt2.close();
				
				// MAP CANTIDADES SOLO BODEGAS INTERNAS (NO SE CLASIFICA POR TIPO ARRIENDO VENTA O TODO)
				PreparedStatement smt22 = con
						.prepareStatement(" select " + 
								" concat(movimiento.id_bodegaEmpresa,'_',movimiento.id_equipo,'_0'), " + 
								" if("
								+ "sum(if(movimiento.id_tipoMovimiento=1,1,-1)*movimiento.cantidad)=-0,0,"
								+ "sum(if(movimiento.id_tipoMovimiento=1,1,-1)*movimiento.cantidad))  as CANTIDAD " +
								" from `"+db+"`.movimiento " + 
//								" left join `"+db+"`.equipo on equipo.id=movimiento.id_equipo " + 
								" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id=movimiento.id_bodegaEmpresa " + 
								" left join `"+db+"`.guia on guia.id = movimiento.id_guia " + 
								" left join `"+db+"`.compra on compra.id = movimiento.id_compra " + 
								" left join `"+db+"`.factura on factura.id = compra.id_factura " + 
								" left join `"+db+"`.baja on baja.id = movimiento.id_baja " + 
								" left join `"+db+"`.actaBaja on actaBaja.id = baja.id_actaBaja " +
								" where movimiento.id_bodegaEmpresa in ("+listaCond2+") and bodegaEmpresa.esInterna = 1 " +
								" and (guia.fecha <=? or guia.fecha is null) and (factura.fecha <=? or factura.fecha is null)  and (actaBaja.fecha <=? or actaBaja.fecha is null) "+
								" group by movimiento.id_bodegaEmpresa,'_',movimiento.id_equipo, movimiento.id_cotizacion " + 
								" having if(sum(if(movimiento.id_tipoMovimiento=2,movimiento.cantidad*-1,movimiento.cantidad))=-0,0,sum(if(movimiento.id_tipoMovimiento=2,movimiento.cantidad*-1,movimiento.cantidad)))>0;");
				smt22.setString(1, corte);
				smt22.setString(2, corte);
				smt22.setString(3, corte);
				ResultSet rs22 = smt22.executeQuery();
				while (rs22.next()) {
					mapCantidades.put(rs22.getString(1), rs22.getDouble(2)); // key movimiento.id_bodegaEmpresa,'_',movimiento.id_equipo,'_',movimiento.id_cotizacion value cantidad
				}
				rs22.close();smt22.close();
			}
			
			Map<Long, Equipo> mapEquipos = Equipo.mapAllAll(con, db);
			Map<Long, BodegaEmpresa> mapBodegas = BodegaEmpresa.mapAll(con, db);
			Map<Long, Cotizacion> mapCoti = Cotizacion.mapAll(con, db);
			
			mapCantidades.forEach((k,v) ->{
				String auxKey[] = k.split("_");
				Equipo equipo = mapEquipos.get(Long.parseLong(auxKey[1]));
				BodegaEmpresa bodega = mapBodegas.get(Long.parseLong(auxKey[0]));
				
				if(equipo!=null && bodega!=null) {
					
					Cotizacion coti = mapCoti.get(Long.parseLong(auxKey[2]));
					Long numeroCoti = (long)0;
					if(coti != null) {
						numeroCoti = coti.getNumero();
					}
					
					
					List<String> aux = new ArrayList<String>();
					aux.add(bodega.getId().toString());			//0 id_bodega
					aux.add(bodega.getNombre());				//1 bodega
					aux.add(bodega.getNombreTipoBodega());		//2 tipoBodega (interna externa)
					aux.add(equipo.getGrupo());					//3 grupo
					aux.add(equipo.getId().toString());			//4 id_equipo
					aux.add(equipo.getCodigo());				//5 codigo
					aux.add(equipo.getNombre());				//6 equipo
					aux.add(equipo.getKG().toString());			//7 kg
					aux.add(equipo.getM2().toString());			//8 m2
					aux.add(v.toString());						//9 CANTIDAD
					aux.add(tipo);								//10 tipo arr o vta
					aux.add(numeroCoti.toString());				//11 numeroCotizacion
					
					aux.add(bodega.getId_sucursal().toString());	//12 id_sucursal
					aux.add(bodega.getNameSucursal());				//13 sucursal nombre
					
					// MAPEA ID_BODEGA, ID_EQUIPO, TIPO(VTA ARR TODO) , LIST AUX
					// KEY = id_bodega_id_equipo_id_cotizacion_tipo
					map.put(aux.get(0)+"_"+aux.get(4)+"_"+auxKey[2]+"_"+aux.get(10), aux);
				}

			});
			
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		
		return (map);
	}
	
			
}
	
	
	
