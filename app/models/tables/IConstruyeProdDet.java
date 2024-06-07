package models.tables;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.util.TempFile;

import models.utilities.Archivos;
import models.utilities.Fechas;

public class IConstruyeProdDet {
	
	
	public Long id;
	public String N_OC;
	public String Nombre_OC;
	public String Fecha;
	public String Fecha_Despacho;
	public String Metodo_Despacho;
	public String Obra;
	public String Rut_Proveedor;
	public String Proveedor;
	public String Cod_Maestro;
	public String Descripción;
	public String Glosa;
	public String Codigo_C_C;
	public String Cuentas_de_Costo;
	public String Unidad;
	public String Cantidad;
	public String Moneda;
	public String Prec_Unit;
	public String Prec_Unit_Desc;
	public String Descuento;
	public String Sub_Total;
	public String Cant_Recibida;
	public String Monto_Recibido;
	public String Devolucion;
	public String Saldo_por_Recibir;
	public String Facturado;
	public String Monto_Recepciones_Cerradas;
	public String Estado_Linea_Recepción_OC;

	
	public IConstruyeProdDet(Long id, String n_OC, String nombre_OC, String fecha, String fecha_Despacho,
			String metodo_Despacho, String obra, String rut_Proveedor, String proveedor, String cod_Maestro,
			String descripción, String glosa, String codigo_C_C, String cuentas_de_Costo, String unidad,
			String cantidad, String moneda, String prec_Unit, String prec_Unit_Desc, String descuento, String sub_Total,
			String cant_Recibida, String monto_Recibido, String devolucion, String saldo_por_Recibir, String facturado,
			String monto_Recepciones_Cerradas, String estado_Linea_Recepción_OC) {
		super();
		this.id = id;
		N_OC = n_OC;
		Nombre_OC = nombre_OC;
		Fecha = fecha;
		Fecha_Despacho = fecha_Despacho;
		Metodo_Despacho = metodo_Despacho;
		Obra = obra;
		Rut_Proveedor = rut_Proveedor;
		Proveedor = proveedor;
		Cod_Maestro = cod_Maestro;
		Descripción = descripción;
		Glosa = glosa;
		Codigo_C_C = codigo_C_C;
		Cuentas_de_Costo = cuentas_de_Costo;
		Unidad = unidad;
		Cantidad = cantidad;
		Moneda = moneda;
		Prec_Unit = prec_Unit;
		Prec_Unit_Desc = prec_Unit_Desc;
		Descuento = descuento;
		Sub_Total = sub_Total;
		Cant_Recibida = cant_Recibida;
		Monto_Recibido = monto_Recibido;
		Devolucion = devolucion;
		Saldo_por_Recibir = saldo_por_Recibir;
		Facturado = facturado;
		Monto_Recepciones_Cerradas = monto_Recepciones_Cerradas;
		Estado_Linea_Recepción_OC = estado_Linea_Recepción_OC;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getN_OC() {
		return N_OC;
	}
	public void setN_OC(String n_OC) {
		N_OC = n_OC;
	}
	public String getNombre_OC() {
		return Nombre_OC;
	}
	public void setNombre_OC(String nombre_OC) {
		Nombre_OC = nombre_OC;
	}
	public String getFecha() {
		return Fecha;
	}
	public void setFecha(String fecha) {
		Fecha = fecha;
	}
	public String getFecha_Despacho() {
		return Fecha_Despacho;
	}
	public void setFecha_Despacho(String fecha_Despacho) {
		Fecha_Despacho = fecha_Despacho;
	}
	public String getMetodo_Despacho() {
		return Metodo_Despacho;
	}
	public void setMetodo_Despacho(String metodo_Despacho) {
		Metodo_Despacho = metodo_Despacho;
	}
	public String getObra() {
		return Obra;
	}
	public void setObra(String obra) {
		Obra = obra;
	}
	public String getRut_Proveedor() {
		return Rut_Proveedor;
	}
	public void setRut_Proveedor(String rut_Proveedor) {
		Rut_Proveedor = rut_Proveedor;
	}
	public String getProveedor() {
		return Proveedor;
	}
	public void setProveedor(String proveedor) {
		Proveedor = proveedor;
	}
	public String getCod_Maestro() {
		return Cod_Maestro;
	}
	public void setCod_Maestro(String cod_Maestro) {
		Cod_Maestro = cod_Maestro;
	}
	public String getDescripción() {
		return Descripción;
	}
	public void setDescripción(String descripción) {
		Descripción = descripción;
	}
	public String getGlosa() {
		return Glosa;
	}
	public void setGlosa(String glosa) {
		Glosa = glosa;
	}
	public String getCodigo_C_C() {
		return Codigo_C_C;
	}
	public void setCodigo_C_C(String codigo_C_C) {
		Codigo_C_C = codigo_C_C;
	}
	public String getCuentas_de_Costo() {
		return Cuentas_de_Costo;
	}
	public void setCuentas_de_Costo(String cuentas_de_Costo) {
		Cuentas_de_Costo = cuentas_de_Costo;
	}
	public String getUnidad() {
		return Unidad;
	}
	public void setUnidad(String unidad) {
		Unidad = unidad;
	}
	public String getCantidad() {
		return Cantidad;
	}
	public void setCantidad(String cantidad) {
		Cantidad = cantidad;
	}
	public String getMoneda() {
		return Moneda;
	}
	public void setMoneda(String moneda) {
		Moneda = moneda;
	}
	public String getPrec_Unit() {
		return Prec_Unit;
	}
	public void setPrec_Unit(String prec_Unit) {
		Prec_Unit = prec_Unit;
	}
	public String getPrec_Unit_Desc() {
		return Prec_Unit_Desc;
	}
	public void setPrec_Unit_Desc(String prec_Unit_Desc) {
		Prec_Unit_Desc = prec_Unit_Desc;
	}
	public String getDescuento() {
		return Descuento;
	}
	public void setDescuento(String descuento) {
		Descuento = descuento;
	}
	public String getSub_Total() {
		return Sub_Total;
	}
	public void setSub_Total(String sub_Total) {
		Sub_Total = sub_Total;
	}
	public String getCant_Recibida() {
		return Cant_Recibida;
	}
	public void setCant_Recibida(String cant_Recibida) {
		Cant_Recibida = cant_Recibida;
	}
	public String getMonto_Recibido() {
		return Monto_Recibido;
	}
	public void setMonto_Recibido(String monto_Recibido) {
		Monto_Recibido = monto_Recibido;
	}
	public String getDevolucion() {
		return Devolucion;
	}
	public void setDevolucion(String devolucion) {
		Devolucion = devolucion;
	}
	public String getSaldo_por_Recibir() {
		return Saldo_por_Recibir;
	}
	public void setSaldo_por_Recibir(String saldo_por_Recibir) {
		Saldo_por_Recibir = saldo_por_Recibir;
	}
	public String getFacturado() {
		return Facturado;
	}
	public void setFacturado(String facturado) {
		Facturado = facturado;
	}
	public String getMonto_Recepciones_Cerradas() {
		return Monto_Recepciones_Cerradas;
	}
	public void setMonto_Recepciones_Cerradas(String monto_Recepciones_Cerradas) {
		Monto_Recepciones_Cerradas = monto_Recepciones_Cerradas;
	}
	public String getEstado_Linea_Recepción_OC() {
		return Estado_Linea_Recepción_OC;
	}
	public void setEstado_Linea_Recepción_OC(String estado_Linea_Recepción_OC) {
		Estado_Linea_Recepción_OC = estado_Linea_Recepción_OC;
	}
	
	public IConstruyeProdDet() {
		super();
	}
	
	
	
	public static Map<String,Long> mapStringVsIdIConstruyeProdDet (Connection con, String base) {
		Map<String,Long> map = new HashMap<String,Long>();
		try {
			PreparedStatement smt = con
							.prepareStatement("Select N_OC, Cod_Maestro, Glosa, Codigo_C_C, Sub_Total, id FROM `"+base+"`.`iConstruyeProdDet`;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				map.put(rs.getString(1)+"_"+rs.getString(2)+"_"+rs.getString(3)+"_"+rs.getString(4)+"_"+rs.getString(5), rs.getLong(6));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(map);
	}
	
	public static List<IConstruyeProdDet> all (Connection con, String base) {
		List<IConstruyeProdDet> lista = new ArrayList<IConstruyeProdDet>();
		try {
			PreparedStatement smt = con
							.prepareStatement("select id, N_OC, Nombre_OC, Fecha, Fecha_Despacho, Metodo_Despacho, Obra, Rut_Proveedor, Proveedor, Cod_Maestro, "
									+ " Descripción, Glosa, Codigo_C_C, Cuentas_de_Costo, Unidad, Cantidad, Moneda, Prec_Unit, Prec_Unit_Desc, Descuento, "
									+ " Sub_Total, Cant_Recibida, Monto_Recibido, Devolucion, Saldo_por_Recibir, Facturado, Monto_Recepciones_Cerradas, Estado_Linea_Recepción_OC "
									+ " from `"+base+"`.`iConstruyeProdDet`;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				lista.add(new IConstruyeProdDet(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),
						rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15),rs.getString(16),rs.getString(17),rs.getString(18),rs.getString(19),rs.getString(20),
						rs.getString(21),rs.getString(22),rs.getString(23),rs.getString(24),rs.getString(25),rs.getString(26),rs.getString(27),rs.getString(28)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(lista);
	}
	
	public static boolean insert (Connection con, String base, List<List<String>> insert) {
		boolean flag = false;
		String auxInsert = "";
		for(List<String> l: insert){
			String aux = "(";
			for(String s: l) {
				aux += "'" + s.replaceAll("'", " ") +"',";
			}
			if(aux.length()>1) {
				aux = aux.substring(0,aux.length()-1) + "),";
				auxInsert += aux;
			}
			
		}
		if(auxInsert.length()>2) {
			auxInsert = auxInsert.substring(0,auxInsert.length()-1);
			try {
				PreparedStatement smt = con
								.prepareStatement("insert into `"+base+"`.`iConstruyeProdDet` "
										+ " (N_OC, Nombre_OC, Fecha, Fecha_Despacho, Metodo_Despacho, Obra, Rut_Proveedor, Proveedor, Cod_Maestro, "
										+ " Descripción, Glosa, Codigo_C_C, Cuentas_de_Costo, Unidad, Cantidad, Moneda, Prec_Unit, Prec_Unit_Desc, Descuento, "
										+ " Sub_Total, Cant_Recibida, Monto_Recibido, Devolucion, Saldo_por_Recibir, Facturado, Monto_Recepciones_Cerradas, Estado_Linea_Recepción_OC) "
										+ " values "+ auxInsert + ";");
				smt.executeUpdate();
				smt.close();
				flag = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return(flag);
	}
	
	public static boolean update (Connection con, String base, List<List<String>> update) {
		boolean flag = false;
		for(List<String> l: update){
				try {
					PreparedStatement smt = con
									.prepareStatement("update `"+base+"`.`iConstruyeProdDet` set Cant_Recibida = ?, Monto_Recibido = ?, Devolucion = ?, "
											+ "Saldo_por_Recibir = ?, Facturado = ?, Monto_Recepciones_Cerradas = ?, Estado_Linea_Recepción_OC = ? where "
											+ " id = ?;");
					smt.setString(1, l.get(20));
					smt.setString(2, l.get(21));
					smt.setString(3, l.get(22));
					smt.setString(4, l.get(23));
					smt.setString(5, l.get(24));
					smt.setString(6, l.get(25));
					smt.setString(7, l.get(26));
					smt.setString(8, l.get(27));
					smt.executeUpdate();
					smt.close();
					flag = true;
				} catch (SQLException e) {
					flag = false;
					e.printStackTrace();
				}
		}
		return(flag);
	}
	
	public static List<List<String>> importProductoDetalleExcel(File file) {
		List<List<String>> lista = new ArrayList<List<String>>();
		DecimalFormat df = new DecimalFormat("#");
	    df.setMaximumFractionDigits(8);
		try {
            Workbook libro = WorkbookFactory.create(file);
            Sheet hoja1 = libro.getSheetAt(0);
            Row row = null;
            Cell cell = null;
            int x = 12;
            row = hoja1.getRow(x);
            if(row != null) {
            	cell = row.getCell(0);
            }
            x=13;
            while (row!=null && cell !=null ) {
            	List<String> auxList = new ArrayList<String>();
            	row = hoja1.getRow(x++);
            	if(row!=null) {
            		cell = row.getCell(0);
            		if(cell != null) {
                    	String dato = cell.getStringCellValue().trim();
                    	if(dato.equals("")) {
                    		cell = null;
                    	}
                    }
            		
            		if(cell!=null) {
            			for(int i=0; i<27; i++) {
                    		cell = row.getCell(i);
        	            	if(cell!=null) {
        	            		String dato = "";
        	            		if(i==2 || i==3) {
        	            			try {
                                		Date date = cell.getDateCellValue();
                                		Fechas aux = Fechas.obtenerFechaDesdeDate(date);
                                		dato = aux.getFechaStrAAMMDD();
                                	}catch(Exception e){
                                		dato = "";
                                	}
        	            		}else {
        	            			try {
                                		dato = cell.getStringCellValue().trim();
                                	}catch(Exception e){
                                		Double aux = cell.getNumericCellValue();
                                		dato = df.format(aux);
                                	}
        	            		}
        	            		auxList.add(dato);
        	            	}
                    	}
                    	lista.add(auxList);
            		}
            	}
            }
		} catch (Exception e) {
			e.printStackTrace();
        }
		return(lista);
	}
	
	
	
	public static File detalleProductoDownloadMada(List<IConstruyeProdDet> listProdDetDepurado, List<Equipo> listEquipo, List<List<String>> listBodegas) {
		
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
            
            
            
            //Datos de las listas a restringir
            
            Sheet hoja2 = libro.getSheetAt(1);
            Row row = null;
            Cell cell = null;
            
            
            //agrega a segunda hoja solo como referencia
            row = hoja2.createRow(0);
        	cell = row.createCell(0);
        	cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("CODIGOS");
            for(int i=0; i<listEquipo.size(); i++) {
            	row = hoja2.createRow(i+1);
            	cell = row.createCell(0);
            	cell.setCellType(Cell.CELL_TYPE_STRING);
    			cell.setCellValue(listEquipo.get(i).getCodigo());
            }
            
            row = hoja2.getRow(0);
        	cell = row.createCell(2);
        	cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("BODEGAS/CLIENTE");
            for(int i=0; i<listBodegas.size(); i++) {
            	row = hoja2.getRow(i+1);
            	cell = row.createCell(2);
            	cell.setCellType(Cell.CELL_TYPE_STRING);
    			cell.setCellValue(listBodegas.get(i).get(5));
            }
            //fin agrega
            
            
            
            
            DataValidationHelper helper = hoja2.getDataValidationHelper();
            DataValidationConstraint constraintCodigo = helper.createFormulaListConstraint("'Hoja2'!$A$2:$A$"+(listEquipo.size()+1));
            DataValidationConstraint constraintBodega = helper.createFormulaListConstraint("'Hoja2'!$C$2:$C$"+(listBodegas.size()+2));
            
            
            // encabezado de la tabla
            
            Sheet hoja1 = libro.getSheetAt(0);
   
			int posCell = 0;
			int posColl = 0;
			row = hoja1.createRow(0);
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("CODIGO_EQUIPO");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("GRUPO_FAMILIA");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 10*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("BODEGA_CLIENTE");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("N_OC");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Nombre_OC");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Fecha");
						
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 10*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Obra");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Rut_Proveedor");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 10*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Proveedor");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 10*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Descripción");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 10*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Glosa");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Codigo_C_C");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 10*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Cuentas_de_Costo");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Moneda");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Sub_Total");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Facturado");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("id_interno");
			
			
			//DETALLE DE LA TABLA
			int posRow = 1;
			for(IConstruyeProdDet l: listProdDetDepurado){
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
				cell.setCellValue(l.getN_OC());
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(l.getNombre_OC());
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(Fechas.DDMMAA(l.getFecha()));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(l.getObra());
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(l.getRut_Proveedor());
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(l.getProveedor());
			
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(l.getDescripción());
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(l.getGlosa());
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(l.getCodigo_C_C());
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(l.getCuentas_de_Costo());
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(l.getMoneda());
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				Double costoNeto = (double)0;
				try {
					costoNeto = Double.parseDouble(l.getSub_Total().trim());
				} catch (Exception e) { }
				cell.setCellValue(costoNeto);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				Double facturado = (double)0;
				try {
					facturado = Double.parseDouble(l.getFacturado().trim());
				} catch (Exception e) { }
				cell.setCellValue(facturado);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(l.getId());
				
				posRow++;
			}
			
			if(listProdDetDepurado.size()>1) {
				 
				 CellRangeAddressList addressList = new CellRangeAddressList(1, listProdDetDepurado.size(), 1, 1);
				 DataValidation dataValidation = helper.createValidation(constraintCodigo, addressList);
				 dataValidation.setSuppressDropDownArrow(true);
				 dataValidation.setShowErrorBox(true);
				 hoja1.addValidationData(dataValidation);
				 
				 addressList = new CellRangeAddressList(3, listProdDetDepurado.size(), 3, 3);
				 dataValidation = helper.createValidation(constraintBodega, addressList);
				 dataValidation.setSuppressDropDownArrow(true);
				 dataValidation.setShowErrorBox(true);
				 hoja1.addValidationData(dataValidation);
				 
				 
				 
			}
			
			CreationHelper helper2 = libro.getCreationHelper();
			
			posRow = posRow + 5;
			row = hoja1.createRow(posRow);
			cell = row.createCell(1);
			Hyperlink hiper = helper2.createHyperlink(0);
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
	
	public static List<List<String>> detalleProductoUploadMada(File file) {
		 List<List<String>> lista = new ArrayList<List<String>>();
		 DecimalFormat df = new DecimalFormat("#");
 	     df.setMaximumFractionDigits(8);
		try {
           Workbook libro = WorkbookFactory.create(file);
           Sheet hoja1 = libro.getSheetAt(0);
           Row row = null;
           Cell cell = null;
           int x = 1;
           row = hoja1.getRow(1);
           if(row!=null) {
           	cell = row.getCell(4);
           }
           while (row!=null && cell !=null ) {
           	row = hoja1.getRow(x++);
           	if(row!=null) {
           		cell = row.getCell(1);
               	if(cell!=null) {
               		boolean noEsBlanco = true;
                   	try {
                   		String dato = cell.getStringCellValue().trim();
                   		if(dato.trim().equals("")) {
                   			noEsBlanco = false;
                   		}
                   	}catch(Exception e){
                   		Double aux = cell.getNumericCellValue();
                   		if(aux.toString().trim().equals("")) {
                   			noEsBlanco = false;
                   		}
                   	}
               		if(noEsBlanco) {
               			List<String> auxList = new ArrayList<String>();
                   		for(int i=1; i<18; i++) {
                   			String dato = "";
                   			cell = row.getCell(i);
                               if(cell!=null) {
                               	try {
                               		dato = cell.getStringCellValue().trim();
                               		dato = dato.replaceAll("'", "\"");
                               	}catch(Exception e){
                               		Double aux = cell.getNumericCellValue();
                               		dato = df.format(aux);
                               	}
                               }
                               auxList.add(dato);
                           }
                   		lista.add(auxList);
               		}
               	}
               	cell = row.getCell(4);
           	}
           }
		} catch (Exception e) {
			e.printStackTrace();
       }
		
		/* estructura de la lista
			0 CODIGO_EQUIPO;
			1 GRUPO_FAMILIA;
			2 N_OC;
			3 Nombre_OC;
			4 Fecha;
			5 Obra;
			6 Rut_Proveedor;
			7 Proveedor;
			8 Descripción;
			9 Glosa;
			10 Codigo_C_C;
			11 Cuentas_de_Costo;
			12 Moneda;
			13 Sub_Total;
			14 Facturado;
			15 ID
		 */
		return(lista);
	}
	
	public static boolean insert(Connection con, String db, String insert) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("insert into `"+db+"`.hojaVida (id_tipoPlan, id_equipo, id_tipoMantencion, fechaInicio, id_tipoTrabajo, id_moneda, costoNeto, id_proveedor, numeroDocumento, fechaDocumento, trabajoHecho, fechaTermino, id_iConstruyeProdDet,"
							+ " id_bodegaIconst, familiaIconst) "
							+ " values "+insert+";");
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return(flag);
	}
	
	public static boolean insertAjusteEpp(Connection con, String db, String insertEpp) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("insert into `"+db+"`.ajustesEP (id_bodegaEmpresa, id_tipoAjuste, id_tipoAjusteVenta, concepto, fechaAjuste, id_moneda, totalAjuste, observaciones) "
							+ " values "+insertEpp+";");
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return(flag);
	}
	

}
