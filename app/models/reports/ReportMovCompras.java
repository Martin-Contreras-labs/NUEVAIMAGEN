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

import models.tables.Moneda;
import models.utilities.DecimalFormato;
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

import models.utilities.Archivos;
import models.utilities.Fechas;


public class ReportMovCompras {
		
	public Long id_factura;
	public Long id_equipo;
	public String nickProveedor;
	public String numeroFactura;
	public String fechaFactura;
	public String codigoEquipo;
	public String nameEquipo;
	public Double cantidad;
	public Double kg;
	
	public ReportMovCompras(Long id_factura, Long id_equipo, String nickProveedor, String numeroFactura,
			String fechaFactura, String codigoEquipo, String nameEquipo, Double cantidad, Double kg) {
		super();
		this.id_factura = id_factura;
		this.id_equipo = id_equipo;
		this.nickProveedor = nickProveedor;
		this.numeroFactura = numeroFactura;
		this.fechaFactura = fechaFactura;
		this.codigoEquipo = codigoEquipo;
		this.nameEquipo = nameEquipo;
		this.cantidad = cantidad;
		this.kg = kg;
	}

	public ReportMovCompras() {
		super();
	}
	
	public static List<List<String>> listaFacturasBetwen(Connection con, String db, String fechaDesde, String fechaHasta, String filtroPorProveedor) {
		List<List<String>> lista = new ArrayList<List<String>>();
		try {
			
			PreparedStatement smt1 = con
					.prepareStatement("select "
							+ " factura.id, "
							+ " proveedor.nickName, "
							+ " factura.numero, "
							+ " factura.fecha "
							+ " from `"+db+"`.factura "
							+ " left join `"+db+"`.proveedor on proveedor.id = factura.id_proveedor "
							+ " where factura.fecha between ? and ? " + filtroPorProveedor
							+ " group by factura.id order by factura.fecha ;");
			smt1.setString(1, fechaDesde);
			smt1.setString(2, fechaHasta);
			ResultSet rs1 = smt1.executeQuery();
			while (rs1.next()) {
				List<String> aux = new ArrayList<String>();
				aux.add(rs1.getString(1));
				aux.add(rs1.getString(2));
				aux.add(rs1.getString(3));
				aux.add(rs1.getString(4));
				lista.add(aux);
			}
			rs1.close();
			smt1.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<ReportMovCompras> movComprasBetwen(Connection con, String db, String fechaDesde, String fechaHasta, String filtroPorProveedor) {
		List<ReportMovCompras> lista = new ArrayList<ReportMovCompras>();
		try {
			PreparedStatement smt1 = con
					.prepareStatement("select "
							+ " compra.id_factura, "
							+ " compra.id_equipo, "
							+ " proveedor.nickName, "
							+ " factura.numero, "
							+ " factura.fecha, "
							+ " equipo.codigo, "
							+ " equipo.nombre, "
							+ " sum(compra.cantidad), "
							+ " equipo.kg "
							+ " from `"+db+"`.compra "
							+ " left join `"+db+"`.factura on factura.id = compra.id_factura "
							+ " left join `"+db+"`.proveedor on proveedor.id = factura.id_proveedor "
							+ " left join `"+db+"`.equipo on equipo.id = compra.id_equipo"
							+ " where (factura.fecha between ? and ?) " + filtroPorProveedor
							+ " group by compra.id_factura, compra.id_equipo;");
			smt1.setString(1, fechaDesde);
			smt1.setString(2, fechaHasta);
			ResultSet rs1 = smt1.executeQuery();
			while (rs1.next()) {
				if(rs1.getDouble(8) > 0) {
					lista.add(new ReportMovCompras(rs1.getLong(1),rs1.getLong(2),rs1.getString(3),rs1.getString(4),rs1.getString(5),rs1.getString(6),
							rs1.getString(7),rs1.getDouble(8),rs1.getDouble(9)));
				}
			}
			rs1.close();
			smt1.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<ReportMovCompras> movStockIni(Connection con, String db, String fechaDesde, String filtroPorProveedor) {
		List<ReportMovCompras> lista = new ArrayList<ReportMovCompras>();
		try {

			PreparedStatement smt1 = con
					.prepareStatement("select "
							+ " compra.id_factura, "
							+ " compra.id_equipo, "
							+ " proveedor.nickName, "
							+ " factura.numero, "
							+ " factura.fecha, "
							+ " equipo.codigo, "
							+ " equipo.nombre, "
							+ " sum(compra.cantidad), "
							+ " equipo.kg "
							+ " from `"+db+"`.compra "
							+ " left join `"+db+"`.factura on factura.id = compra.id_factura "
							+ " left join `"+db+"`.proveedor on proveedor.id = factura.id_proveedor "
							+ " left join `"+db+"`.equipo on equipo.id = compra.id_equipo"
							+ " where factura.fecha < ? " + filtroPorProveedor
							+ " group by compra.id_equipo;");
			smt1.setString(1, fechaDesde);
			ResultSet rs1 = smt1.executeQuery();
			while (rs1.next()) {
				if(rs1.getDouble(8) > 0) {
					lista.add(new ReportMovCompras(rs1.getLong(1),rs1.getLong(2),rs1.getString(3),rs1.getString(4),rs1.getString(5),rs1.getString(6),
							rs1.getString(7),rs1.getDouble(8),rs1.getDouble(9)));
				}
			}
			rs1.close();
			smt1.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<ReportMovCompras> movStockFin(Connection con, String db, String fechaHasta, String filtroPorProveedor) {
		List<ReportMovCompras> lista = new ArrayList<ReportMovCompras>();
		try {
			PreparedStatement smt1 = con
					.prepareStatement("select "
							+ " compra.id_factura, "
							+ " compra.id_equipo, "
							+ " proveedor.nickName, "
							+ " factura.numero, "
							+ " factura.fecha, "
							+ " equipo.codigo, "
							+ " equipo.nombre, "
							+ " sum(compra.cantidad), "
							+ " equipo.kg "
							+ " from `"+db+"`.compra "
							+ " left join `"+db+"`.factura on factura.id = compra.id_factura "
							+ " left join `"+db+"`.proveedor on proveedor.id = factura.id_proveedor "
							+ " left join `"+db+"`.equipo on equipo.id = compra.id_equipo"
							+ " where factura.fecha <= ? " + filtroPorProveedor
							+ " group by compra.id_equipo;");
			smt1.setString(1, fechaHasta);

			ResultSet rs1 = smt1.executeQuery();
			while (rs1.next()) {
				if(rs1.getDouble(8) > 0) {
					lista.add(new ReportMovCompras(rs1.getLong(1),rs1.getLong(2),rs1.getString(3),rs1.getString(4),rs1.getString(5),rs1.getString(6),
							rs1.getString(7),rs1.getDouble(8),rs1.getDouble(9)));
				}
			}
			rs1.close();
			smt1.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<List<String>> movComprasPeriodo(Connection con, String db, String fechaDesde, String fechaHasta, String filtroPorProveedor,
													   Map<Long,List<String>> ultimoPrecio, Map<Long, Double> tasas) {
		
		List<ReportMovCompras> ini = ReportMovCompras.movStockIni(con, db, fechaDesde, filtroPorProveedor);
		List<ReportMovCompras> betwen = ReportMovCompras.movComprasBetwen(con, db, fechaDesde, fechaHasta, filtroPorProveedor);
		List<ReportMovCompras> fin = ReportMovCompras.movStockFin(con, db, fechaHasta, filtroPorProveedor);
		
		Map<Long,List<String>> mapEncabezado = new HashMap<Long,List<String>>();
		for(ReportMovCompras x: fin) {
			List<String> aux = new ArrayList<String>();
			aux.add(x.id_factura.toString());
			aux.add(x.nickProveedor);
			aux.add(x.numeroFactura);
			aux.add(x.fechaFactura);
			mapEncabezado.put(x.id_factura, aux);
		}
		
		Map<Long,List<String>> mapEquipos = new HashMap<Long,List<String>>();
		for(ReportMovCompras x: fin) {
			List<String> aux = new ArrayList<String>();
			aux.add(x.codigoEquipo);
			aux.add(x.nameEquipo);
			aux.add(x.id_equipo.toString());
			aux.add(x.kg.toString());
			mapEquipos.put(x.id_equipo, aux);
		}
		
		
		Map<Long,Double> mapDetalleIni = new HashMap<Long,Double>();
		for(ReportMovCompras x: ini) {
			mapDetalleIni.put(x.id_equipo, x.cantidad);
		}
		
		Map<String,ReportMovCompras> mapDetalleBetwen = new HashMap<String,ReportMovCompras>();
		for(ReportMovCompras x: betwen) {
			mapDetalleBetwen.put(x.id_factura+"_"+x.id_equipo, x);
		}
		
		Map<Long,Double> mapDetalleFin = new HashMap<Long,Double>();
		for(ReportMovCompras x: fin) {
			mapDetalleFin.put(x.id_equipo, x.cantidad);
		}
		
		List<List<String>> listaFacturas = ReportMovCompras.listaFacturasBetwen(con, db, fechaDesde, fechaHasta, filtroPorProveedor);
		
		List<List<String>> tabla = new ArrayList<List<String>>();
		List<String> linea0 = new ArrayList<String>();
		List<String> linea1 = new ArrayList<String>();
		List<String> linea2 = new ArrayList<String>();
		List<String> linea3 = new ArrayList<String>();
		
		linea0.add("id_factura");
		linea0.add("");
		linea0.add("");
		linea0.add("");
		linea0.add("");
		linea0.add("");
		linea0.add("");
		
		linea1.add("CODIGO");
		linea1.add("EQUIPO");
		linea1.add("ULTIMO PRECIO");
		linea1.add("REGISTRADO ");
		linea1.add("");
		linea1.add("");
		linea1.add("STOCK");
		
		linea2.add("");
		linea2.add("");
		linea2.add("PROVEEDOR");
		linea2.add("FECHA");
		linea2.add("KG");
		linea2.add("PRECIO");
		linea2.add("INI");
		
		linea3.add("");
		linea3.add("");
		linea3.add("");
		linea3.add("");
		linea3.add("");
		linea3.add("");
		linea3.add(Fechas.DDMMAA(fechaDesde).replaceAll("-", "/"));
		
		listaFacturas.forEach( k ->{
			linea0.add(k.get(0));
			linea1.add(k.get(1));
			linea2.add(k.get(2));
			linea3.add(Fechas.DDMMAA(k.get(3)).replaceAll("-", "/"));
		});
		linea0.add("");
		linea1.add("STOCK");
		linea2.add("FIN");
		linea3.add(Fechas.DDMMAA(fechaHasta).replaceAll("-", "/"));

//		linea0.add("");
		linea1.add("");
		linea1.add("");
		linea2.add("KG");
		linea2.add("PRECIO");
		linea3.add("");
		linea3.add("");
		tabla.add(linea0);
		tabla.add(linea1);
		tabla.add(linea2);
		tabla.add(linea3);

		DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
		DecimalFormat format = new DecimalFormat("#,##0",symbols);

		int nroDec = Moneda.numeroDecimalxId(con,db,"1");

		for(Map.Entry<Long,List<String>> entry : mapEquipos.entrySet()) {
			List<String> v = entry.getValue();
			List<String> linea = new ArrayList<String>();
			linea.add(v.get(0));
			linea.add(v.get(1));
			Double precioUnit = 0D;
			List<String> precio = ultimoPrecio.get(Long.parseLong(v.get(2)));
			if(precio!=null) {
				Double tasaCambio = tasas.get(Long.parseLong(precio.get(5)));
				if(tasaCambio!=null) {
					precioUnit = Double.parseDouble(precio.get(0)) * tasaCambio;
				}
				linea.add(precio.get(4));
				linea.add(Fechas.DDMMAA(precio.get(2)));
				String precioFormateado = DecimalFormato.formato(precioUnit,(long) nroDec);
				linea.add(DecimalFormato.formato(Double.parseDouble(v.get(3)),2L));
				linea.add(precioFormateado);
				precioUnit = Double.parseDouble(precioFormateado.replaceAll(",",""));
			}else{
				linea.add("");
				linea.add("");
				linea.add("");
				linea.add("0");
			}
			
			Double cant = mapDetalleIni.get(Long.parseLong(v.get(2)));
			if(cant == null) {
				cant = (double) 0;
			}
			linea.add(format.format(cant));


			for(int i = 6; i < tabla.get(0).size() - 1; i++) {
				if(tabla.get(0).get(i) != null && tabla.get(0).get(i).trim().length() > 0) {
					ReportMovCompras x = mapDetalleBetwen.get(tabla.get(0).get(i) + "_" + v.get(2));
					if(x == null) {
						cant = (double) 0;
					}else {
						cant = x.cantidad;
					}
					linea.add(format.format(cant));
				}

			}
			cant = mapDetalleFin.get(Long.parseLong(v.get(2)));
			if(cant == null) {
				cant = (double) 0;
			}
			linea.add(format.format(cant));
			linea.add(format.format(Double.parseDouble(v.get(3))*cant));
			linea.add(DecimalFormato.formato(precioUnit*cant,(long) nroDec));
			tabla.add(linea);
		}
		return (tabla);
	}
	
	
	public static File movComprasPeriodoExcel(String db, List<List<String>> datos, Map<String,String> mapDiccionario, String fechaDesde,
											  String fechaHasta, Moneda moneda) {

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

			CreationHelper creationHelper = libro.getCreationHelper();
			CellStyle fecha = libro.createCellStyle();
			fecha.setDataFormat(creationHelper.createDataFormat().getFormat("dd/MM/yyyy"));
			fecha.setBorderBottom(CellStyle.BORDER_THIN);
			fecha.setBorderTop(CellStyle.BORDER_THIN);
			fecha.setBorderRight(CellStyle.BORDER_THIN);
			fecha.setBorderLeft(CellStyle.BORDER_THIN);
            

            
            
            Sheet hoja1 = libro.getSheetAt(0);
            Row row = null;
            Cell cell = null;
            
            row = hoja1.createRow(1);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("DETALLE MOVIMIENTOS DE COMPRAS - MONEDA "+moneda.nombre.toUpperCase());
			
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
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("PERIODO: DESDE "+Fechas.DDMMAA(fechaDesde)+" HASTA "+Fechas.DDMMAA(fechaHasta));
			
			// encabezado de la tabla
			
			hoja1.setColumnWidth(1, 7*1000);
			hoja1.setColumnWidth(2, 10*1000);
			for(int i=3;i<datos.size()+1;i++){
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
	        anchor.setCol1(6);
	        anchor.setRow1(1);
			Picture img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);
			
			//DETALLE DE LA TABLA
			int posRow = 8;
			for(int i=1;i<datos.size();i++){
				row = hoja1.createRow(posRow);
				int posCell = 0;
				Double aux = (double)0;
				for(int j=0; j<datos.get(i).size(); j++){
					String dato = datos.get(i).get(j);
					if(i>0 && i<4){
						posCell++; 
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(dato);
					}else{
						if(j<5){
							posCell++;
							cell = row.createCell(posCell);
							cell.setCellStyle(detalle);
							if(j==4){
								aux = Double.parseDouble(dato.replaceAll(",", ""));
								cell.setCellType(Cell.CELL_TYPE_NUMERIC);
								cell.setCellValue(aux);
							}else if(j==3){
								cell.setCellStyle(fecha);
								if(!dato.equals("")){
									Fechas auxFecha = Fechas.obtenerFechaDesdeStrDDMMAA(dato);
									cell.setCellType(Cell.CELL_TYPE_NUMERIC);
									cell.setCellValue(auxFecha.getFechaUtil());
								}
							}else{
								cell.setCellType(Cell.CELL_TYPE_STRING);
								cell.setCellValue(dato);
							}



						}else {
							if(dato.equals("")||dato.equals(" ")) {
								dato = "0";
							}
							posCell++; 
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
				            aux = Double.parseDouble(dato.replaceAll(",", ""));
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(aux);
						}
					}
				}
				posRow++;
			}

			Double granTotPrecio = (double)0;
			if(datos.size()>2){
				row = hoja1.createRow(posRow);
				int posCell = 0;
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("TOTALES");
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("POR COLUMNA");
				for(int i=0; i<4; i++){
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(encabezado);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("");
				}
				for(int j=6; j<datos.get(4).size(); j++){
					Double cant = (double)0;
					for(int i=4; i<datos.size(); i++){
						cant += Double.parseDouble(datos.get(i).get(j).replaceAll(",", ""));
					}
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(cant);
				}
				posRow++;
				row = hoja1.createRow(posRow);
				posCell = 0;
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("TOTAL");
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("KG");
				for(int i=0; i<4; i++){
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(encabezado);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("");
				}
				for(int j=6; j<datos.get(4).size(); j++){
					Double totKg = (double)0;
					for(int i=4; i<datos.size(); i++){
						Double kg = Double.parseDouble(datos.get(i).get(4).replaceAll(",", ""));
						Double cant = Double.parseDouble(datos.get(i).get(j).replaceAll(",", ""));
						totKg += kg * cant;
					}

					if(j != datos.get(4).size()-2 && j != datos.get(4).size()-1){
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(totKg);
					}else{
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					}
				}

				posRow++;
				row = hoja1.createRow(posRow);
				posCell = 0;
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("TOTAL");
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(encabezado);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("PRECIO");
				for(int i=0; i<4; i++){
					posCell++;
					cell = row.createCell(posCell);
					cell.setCellStyle(encabezado);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue("");
				}
				for(int j=6; j<datos.get(4).size(); j++){
					Double totPrecio = (double)0;
					for(int i=4; i<datos.size(); i++){
						Double precio = Double.parseDouble(datos.get(i).get(5).replaceAll(",", ""));
						Double cant = Double.parseDouble(datos.get(i).get(j).replaceAll(",", ""));
						totPrecio += precio * cant;
					}

					if(j != datos.get(4).size()-2 && j != datos.get(4).size()-1){
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(totPrecio);
					}else{
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					}
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
	
	
	
