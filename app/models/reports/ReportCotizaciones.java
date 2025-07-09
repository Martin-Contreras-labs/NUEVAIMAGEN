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

import models.tables.*;
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
import models.utilities.DecimalFormato;
import models.utilities.Fechas;


public class ReportCotizaciones {
	
	public Long id_cotizacion;
	public Long id_moneda;
	public Double valorizado;
	public Long unidades;
	public String estado;
	public Long confirmada;
	public Long id_ot;
	public String fechaCoti;
	public String fechaOt;
	
	public ReportCotizaciones(Long id_cotizacion, Long id_moneda, Double valorizado, Long unidades, String estado,
			Long confirmada, Long id_ot, String fechaCoti, String fechaOt) {
		super();
		this.id_cotizacion = id_cotizacion;
		this.id_moneda = id_moneda;
		this.valorizado = valorizado;
		this.unidades = unidades;
		this.estado = estado;
		this.confirmada = confirmada;
		this.id_ot = id_ot;
		this.fechaCoti = fechaCoti;
		this.fechaOt = fechaOt;
	}
	
	public ReportCotizaciones() {
		super();
	}


	
	
	public static Map<String,Double> calculoCoti(Connection con, String db, String desdeAAMMDD, String hastaAAMMDD, String pais, String condSucursal, String condComercial) {
		Map<String,TasasCambio> mapAllTasas = TasasCambio.mapTasasporAllFecha(con, db, pais);
		Map<String,Double> resultado = new HashMap<String,Double>();

		try {
			
			PreparedStatement smt1 = con
					.prepareStatement(" select if(cotizaDetalle.esVenta=1,  sum(cotizaDetalle.precioVenta*cotizaDetalle.cantidad)*(1-cotizacion.dctoVenta),  "
							+ "	sum(cotizaDetalle.precioArriendo*cotizaDetalle.cantidad*cotizaDetalle.permanencia)*(1-cotizacion.dctoArriendo)  ) as valorizado, "
							+ "	cotizacion.confirmada, cotizaDetalle.id_moneda, cotizacion.fecha, cotizaEstado.estado, cotizaEstado.id "
							+ "	from `"+db+"`.cotizaDetalle "
							+ "	left join  `"+db+"`.cotizacion on cotizacion.id = cotizaDetalle.id_cotizacion "
							+ "	left join  `"+db+"`.cotizaEstado on cotizaEstado.id = cotizacion.id_cotizaEstado "
							+ "	where cotizacion.fecha between ? and ? " + condSucursal + " " + condComercial
							+ "	group by cotizacion.confirmada, cotizaDetalle.id_moneda, cotizacion.fecha, cotizaEstado.estado;");
			smt1.setString(1, desdeAAMMDD);
			smt1.setString(2, hastaAAMMDD);
			ResultSet rsValConfNoConf = smt1.executeQuery();
			Double valConfirmadas = (double)0;
			Double valConOt = (double)0;
			Double valNoConfirmadas = (double)0;
			while(rsValConfNoConf.next()) {
				Double valorTasa = (double)1;
				TasasCambio tasasCambio = mapAllTasas.get(rsValConfNoConf.getString(4));
				Map<Long,Double> tasas = new HashMap<Long,Double>();  // id_moneda, valor tasa
				if(tasasCambio!=null) {
					tasas.put((long)1, (double)1);
					tasas.put((long)2, Double.parseDouble(tasasCambio.getClpUsd().replaceAll(",", "")));
					tasas.put((long)3, Double.parseDouble(tasasCambio.getClpEur().replaceAll(",", "")));
					tasas.put((long)4, Double.parseDouble(tasasCambio.getClpUf().replaceAll(",", "")));
					valorTasa = tasas.get(rsValConfNoConf.getLong(3));
					if(valorTasa == null) {
						valorTasa = (double)1;
					}
				}
				
				if(rsValConfNoConf.getString(2).equals("1")) {
					
					if(rsValConfNoConf.getString(6).equals("-2")) {
						valConOt += rsValConfNoConf.getDouble(1) * valorTasa;
					}else {
						valConfirmadas += rsValConfNoConf.getDouble(1) * valorTasa;
					}
					
				}else {
					valNoConfirmadas += rsValConfNoConf.getDouble(1)*valorTasa;
					Double valor = resultado.get("val_"+rsValConfNoConf.getString(5));
					if(valor == null) {
						resultado.put("val_"+rsValConfNoConf.getString(5), rsValConfNoConf.getDouble(1));
					}else {
						resultado.put("val_"+rsValConfNoConf.getString(5), (valor + rsValConfNoConf.getDouble(1)*valorTasa));
					}
				}
			}
			resultado.put("val_NoConfirmadas", valNoConfirmadas);
			resultado.put("val_Confirmadas", valConfirmadas);
			resultado.put("val_ConOt", valConOt);
			smt1.close();
			rsValConfNoConf.close();
			
			
			PreparedStatement smt3 = con
					.prepareStatement(" select count(cotizacion.id), confirmada, cotizaEstado.estado, cotizaEstado.id "
							+ " from `"+db+"`.cotizacion "
							+ "	left join  `"+db+"`.cotizaEstado on cotizaEstado.id = cotizacion.id_cotizaEstado "
							+ " where cotizacion.fecha between ? and ? "  + condSucursal + " " + condComercial
							+ " group by confirmada, cotizaEstado.estado;");
			smt3.setString(1, desdeAAMMDD);
			smt3.setString(2, hastaAAMMDD);

			ResultSet rsCantConNoConf = smt3.executeQuery();
			Double cantConfirmadas = (double)0;
			Double cantConOt = (double)0;
			Double cantNoConfirmadas = (double)0;
			while(rsCantConNoConf.next()) {
				if(rsCantConNoConf.getString(2).equals("1")) {
					
					if(rsCantConNoConf.getString(4).equals("-2")) {
						cantConOt += rsCantConNoConf.getDouble(1);
					}else {
						cantConfirmadas += rsCantConNoConf.getDouble(1);
					}
					
				}else {
					cantNoConfirmadas += rsCantConNoConf.getDouble(1);
					Double valor = resultado.get("cant_"+rsCantConNoConf.getString(3));
					if(valor == null) {
						resultado.put("cant_"+rsCantConNoConf.getString(3), rsCantConNoConf.getDouble(1));
					}else {
						resultado.put("cant_"+rsCantConNoConf.getString(3), (valor + rsCantConNoConf.getDouble(1)));
					}
					
				}
			}
			resultado.put("cant_NoConfirmadas", cantNoConfirmadas);
			resultado.put("cant_Confirmadas", cantConfirmadas);
			resultado.put("cant_ConOt", cantConOt);
			smt3.close();
			rsCantConNoConf.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return (resultado);
	}
	
	public static List<List<String>> detallePipelineRes(Connection con, String db, String desdeAAMMDD, String hastaAAMMDD, String pais,
			String condSucursal) {
		
		List<List<String>> detalle = new ArrayList<List<String>>();

		try {
			
			PreparedStatement smt = con
					.prepareStatement("select "
							+ " cotizacion.fecha,"
							+ " cotizacion.id_sucursal,"
							+ " cotizacion.id_comercial,"
							+ " cotizacion.id_cotizaSolucion,"
							+ " cotizacion.id_cliente,"
							+ " cotizacion.id_proyecto,"
							+ " cotizacion.id_cotizaEstado,"		// 7
							+ " cotizaDetalle.cantidad," 			// 8 double
							+ " cotizaDetalle.esVenta," 			// 9
							+ " cotizaDetalle.id_moneda," 			// 10
							+ " cotizaDetalle.precioVenta," 		// 11
							+ " cotizaDetalle.precioArriendo," 		// 12
							+ " cotizaDetalle.permanencia," 		// 13
							+ " cotizaDetalle.id_equipo,"  			// 14
							+ " ifnull(ot.fecha,''),"  				// 15
							+ " cotizacion.id,"  					// 16
							+ " ifnull(ot.confirmada,0)"  			// 17
							+ " from `"+db+"`.cotizaDetalle"
							+ " left join `"+db+"`.cotizacion on cotizacion.id = cotizaDetalle.id_cotizacion"
							+ " left join `"+db+"`.ot on ot.id_cotizacion = cotizacion.id"
							+ " where cotizacion.fecha between ? and ? " + condSucursal + ";");
			smt.setString(1, desdeAAMMDD);
			smt.setString(2, hastaAAMMDD);

			ResultSet rs = smt.executeQuery();
			
			Map<String,List<Double>> mapDatosAux = new HashMap<String,List<Double>>();
			
			Map<String,TasasCambio> mapAllTasas = TasasCambio.mapTasasporAllFecha(con, db, pais);
			
			Map<Long, Equipo> mapEquipos = Equipo.mapAllAll(con, db);

			while(rs.next()) {
				
				Double valorTasa = (double)1;
				TasasCambio tasasCambio = mapAllTasas.get(rs.getString(1));
				Map<Long,Double> tasas = new HashMap<Long,Double>();  // id_moneda, valor tasa
				if(tasasCambio!=null) {
					tasas.put((long)1, (double)1);
					tasas.put((long)2, Double.parseDouble(tasasCambio.getClpUsd().replaceAll(",", "")));
					tasas.put((long)3, Double.parseDouble(tasasCambio.getClpEur().replaceAll(",", "")));
					tasas.put((long)4, Double.parseDouble(tasasCambio.getClpUf().replaceAll(",", "")));
					valorTasa = tasas.get(rs.getLong(10));
					if(valorTasa == null) {
						valorTasa = (double)1;
					}
				}
				
				String[] fecha = rs.getString(1).split("-");
				String mesAnio = fecha[0] + "" + fecha[1];
				
				String mesAnioOT = "";
				if(!rs.getString(15).equals("")) {
					String[] fechaOT = rs.getString(15).split("-");
					mesAnioOT = fechaOT[0] + "" + fechaOT[1];
				}

				String key = mesAnio +"_"+rs.getString(3)+"_"+rs.getString(7)+"_"+rs.getString(17);
				// key es mesAnio id_comercial id_cotizaEstado ot.confirmada
				
				Double cantidad = rs.getDouble(8);
				Long esVenta = rs.getLong(9);
				Double precioVenta = rs.getDouble(11);
				Double precioArriendo = rs.getDouble(12);
				Double permanencia = rs.getDouble(13);
				Long id_equipo = rs.getLong(14);
				Double peso = (double)0;
				
				List<Double> auxMapDatos = mapDatosAux.get(key);
				
				if(auxMapDatos == null) {
					
					Double total = (double) 0;
					if((long) esVenta == (long)1 ) {
						total = cantidad * precioVenta * valorTasa;
					}else {
						total = cantidad * precioArriendo * permanencia * valorTasa;
					}
					
					Equipo equipo = mapEquipos.get(id_equipo);
					if(equipo != null) {
						peso = cantidad * equipo.getKg();
					}
					
					List<Double> lista = new ArrayList<Double>();
					lista.add(total);
					lista.add(peso);
					
					mapDatosAux.put(key, lista);
					
				} else {
					
					Double total = (double) 0;
					if((long) esVenta == (long)1 ) {
						total = cantidad * precioVenta * valorTasa;
					}else {
						total = cantidad * precioArriendo * permanencia * valorTasa;
					}
					
					Equipo equipo = mapEquipos.get(id_equipo);
					if(equipo != null) {
						peso = cantidad * equipo.getKg();
					}
					
					total += auxMapDatos.get(0);
					peso += auxMapDatos.get(1);
					
					List<Double> lista = new ArrayList<Double>();
					lista.add(total);
					lista.add(peso);
					
					mapDatosAux.put(key, lista);
				}
				
			}
			
			rs.close();
			smt.close();

			Map<Long,Usuario> mapComercial = Usuario.mapAll(con, db);
			Map<Long,CotizaEstado> mapEstado = CotizaEstado.mapAll(con, db);
			int nroDec = Moneda.numeroDecimalxId(con,db,"1");

			// key es mesAnio id_comercial id_cotizaEstado ot.confirmada
			for (Map.Entry<String, List<Double>> entry : mapDatosAux.entrySet()) {
	            String[] key = entry.getKey().split("_");
	            List<Double> value = entry.getValue();
	            String mesAnio = key[0];
				String id_comercial = key[1];
				String id_cotizaEstado = key[2];
				String otConfirmada = key[3];
				String nameComercial = "";
	            String nameEstado = "";
	            Usuario comercial = mapComercial.get(Long.parseLong(id_comercial));
	            if(comercial != null) {
	            	nameComercial = comercial.getNombre();
	            }
	            CotizaEstado estado = mapEstado.get(Long.parseLong(id_cotizaEstado));
	            if(estado != null) {
	            	nameEstado = estado.getEstado();
					if(estado.getId() == (long)-2) {
						if(otConfirmada.equals("0")) {
							nameEstado = nameEstado + " (PEND)";
						}else {
							nameEstado = nameEstado + " (CONF)";
						}
					}
	            }
	            List<String> aux = new ArrayList<String>();
				aux.add(mesAnio); 		//0 anioMes
				aux.add(nameEstado); 	//1 cotizaEstado.estado
				aux.add(nameComercial); //2 comercial usuario.nombre
				aux.add(DecimalFormato.formato(value.get(0), (long)nroDec)); //3 valorizado
				aux.add(DecimalFormato.formato(value.get(1), (long)2)); //4 peso en KG
				aux.add(key[1]); 		//5 id_comercial
				aux.add(key[2]); 		//6 cotizaEstado.id

				detalle.add(aux);
	        }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return (detalle);
	}

	public static File detallePipelineResExcel(String db, Map<String,String> mapDiccionario, List<List<String>> lista1,
											   String tituloSucursal, String fechaDe, String fechaA) {

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

			CellStyle pie = libro.createCellStyle();
			pie.setBorderBottom(CellStyle.BORDER_THIN);
			pie.setBorderTop(CellStyle.BORDER_THIN);
			pie.setBorderRight(CellStyle.BORDER_THIN);
			pie.setBorderLeft(CellStyle.BORDER_THIN);
			pie.setFillPattern(CellStyle.SOLID_FOREGROUND);
			pie.setFillForegroundColor((short)19);
			pie.setAlignment(CellStyle.ALIGN_RIGHT);


			//titulos del archivo

			libro.setSheetName(0, "PIPELINE");
			Sheet hoja1 = libro.getSheetAt(0);

			Row row = null;
			Cell cell = null;

			row = hoja1.createRow(1);
			cell = row.createCell(1);
			cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("REPORTE PIPELINE RESUMIDO "+tituloSucursal);

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
			cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("PERIODO: desde " + fechaDe  + " hasta " + fechaA);


			//anchos de columnas
			for(int i=1; i<17; i++) {
				hoja1.setColumnWidth(i, 6*1000);
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
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("AÑO MES COTI");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("COMERCIAL");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("ESTADO");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TOTAL NETO");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("PESO KG");


			for(int i=0;i<lista1.size();i++){

				posRow++;
				posCell = 0;
				row = hoja1.createRow(posRow);

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista1.get(i).get(0));

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista1.get(i).get(2));

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista1.get(i).get(1));

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				Double aux = Double.parseDouble(lista1.get(i).get(3).replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				aux = Double.parseDouble(lista1.get(i).get(4).replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);


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

	public static List<List<String>> detallePipelineDet(Connection con, String db, String desdeAAMMDD, String hastaAAMMDD, String pais,
														String condSucursal, String condComercial) {

		List<List<String>> detalle = new ArrayList<List<String>>();

		try {

			PreparedStatement smt = con
					.prepareStatement("select "
							+ " cotizacion.fecha,"
							+ " cotizacion.id_sucursal,"
							+ " cotizacion.id_comercial,"
							+ " cotizacion.id_cotizaSolucion,"
							+ " cotizacion.id_cliente,"
							+ " cotizacion.id_proyecto,"
							+ " cotizacion.id_cotizaEstado,"
							+ " cotizaDetalle.cantidad," // 8 double
							+ " cotizaDetalle.esVenta," // 9
							+ " cotizaDetalle.id_moneda," // 10
							+ " cotizaDetalle.precioVenta," // 11
							+ " cotizaDetalle.precioArriendo," // 12
							+ " cotizaDetalle.permanencia," // 13
							+ " cotizaDetalle.id_equipo,"  // 14
							+ " ifnull(ot.fecha,''),"  	// 15
							+ " cotizacion.id,"  			// 16
							+ " ifnull(ot.id,0),"  					// 17
							+ " cotizacion.numero,"  		// 18
							+ " ifnull(ot.numero,''),"  				// 19
							+ " ifnull(ot.confirmada,''),"  				// 20
							+ " ifnull(cotizacion.id_dibujante,'0'),"  				// 21
							+ " ifnull(cotizacion.fechaProbable,'')"  				// 22
									+ " from `"+db+"`.cotizaDetalle"
							+ " left join `"+db+"`.cotizacion on cotizacion.id = cotizaDetalle.id_cotizacion"
							+ " left join `"+db+"`.ot on ot.id_cotizacion = cotizacion.id"
							+ " where cotizacion.fecha between ? and ? " + condSucursal + " " + condComercial + ";");
			smt.setString(1, desdeAAMMDD);
			smt.setString(2, hastaAAMMDD);

			ResultSet rs = smt.executeQuery();

			Map<String,List<Double>> mapDatosAux = new HashMap<String,List<Double>>();
			Map<String,TasasCambio> mapAllTasas = TasasCambio.mapTasasporAllFecha(con, db, pais);
			Map<Long, Equipo> mapEquipos = Equipo.mapAllAll(con, db);

			while(rs.next()) {

				Double valorTasa = (double)1;
				TasasCambio tasasCambio = mapAllTasas.get(rs.getString(1));
				Map<Long,Double> tasas = new HashMap<Long,Double>();  // id_moneda, valor tasa
				if(tasasCambio!=null) {
					tasas.put((long)1, (double)1);
					tasas.put((long)2, Double.parseDouble(tasasCambio.getClpUsd().replaceAll(",", "")));
					tasas.put((long)3, Double.parseDouble(tasasCambio.getClpEur().replaceAll(",", "")));
					tasas.put((long)4, Double.parseDouble(tasasCambio.getClpUf().replaceAll(",", "")));
					valorTasa = tasas.get(rs.getLong(10));
					if(valorTasa == null) {
						valorTasa = (double)1;
					}
				}

				String fechaCoti = rs.getString(1);
				String fechaOt = "";
				if( ! rs.getString(15).equals("")) {
					fechaOt = rs.getString(15);
				}

				String id_sucursal = rs.getString(2);
				String id_comercial = rs.getString(3);
				String id_cotizaSolucion = rs.getString(4);
				String id_cliente = rs.getString(5);
				String id_proyecto = rs.getString(6);
				String id_cotizaEstado = rs.getString(7);
				String id_cotizacion = rs.getString(16);
				String nroCoti = rs.getString(18);
				String id_dibujante = rs.getString(21);
				String fechaProbable = rs.getString(22);

				String key = fechaCoti +"_"+id_sucursal+"_"+id_comercial+"_"+id_cotizaSolucion+"_"+id_cliente+"_"+id_proyecto+"_"+id_cotizaEstado+"_"+fechaOt+"_"+id_cotizacion+"_"+nroCoti+"_"+id_dibujante+"_"+fechaProbable;

				Double cantidad = rs.getDouble(8);
				Long esVenta = rs.getLong(9);
				Double precioVenta = rs.getDouble(11);
				Double precioArriendo = rs.getDouble(12);
				Double permanencia = rs.getDouble(13);
				Long id_equipo = rs.getLong(14);
				Double peso = (double)0;

				List<Double> auxMapDatos = mapDatosAux.get(key);

				if(auxMapDatos == null) {

					Double total = (double) 0;
					if((long) esVenta == (long)1 ) {
						total = cantidad * precioVenta * valorTasa;
					}else {
						total = cantidad * precioArriendo * permanencia * valorTasa;
					}

					Equipo equipo = mapEquipos.get(id_equipo);
					if(equipo != null) {
						peso = cantidad * equipo.getKg();
					}

					List<Double> lista = new ArrayList<Double>();
					lista.add(total);
					lista.add(peso);

					mapDatosAux.put(key, lista);

				} else {

					Double total = (double) 0;
					if((long) esVenta == (long)1 ) {
						total = cantidad * precioVenta * valorTasa;
					}else {
						total = cantidad * precioArriendo * permanencia * valorTasa;
					}

					Equipo equipo = mapEquipos.get(id_equipo);
					if(equipo != null) {
						peso = cantidad * equipo.getKg();
					}

					total += auxMapDatos.get(0);
					peso += auxMapDatos.get(1);

					List<Double> lista = new ArrayList<Double>();
					lista.add(total);
					lista.add(peso);

					mapDatosAux.put(key, lista);
				}

			}

			rs.close();
			smt.close();

			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			Map<Long,Usuario> mapComercial = Usuario.mapAll(con, db);
			Map<Long,CotizaSolucion> mapSolucion = CotizaSolucion.mapAll(con, db);
			Map<Long,Cliente> mapCliente = Cliente.mapAllClientes(con, db);
			Map<Long,Proyecto> mapProyecto = Proyecto.mapAllProyectos(con, db);
			Map<Long,CotizaEstado> mapEstado = CotizaEstado.mapAll(con, db);
			Map<Long,Ot> mapOt = Ot.mapAll_idCoti_vs_Ot(con, db);
			Map<Long,Dibujante> mapDibujante = Dibujante.mapAll(con, db);

			for (Map.Entry<String, List<Double>> entry : mapDatosAux.entrySet()) {
				String[] key = entry.getKey().split("_");
				List<Double> value = entry.getValue();
				//String key = fechaCoti +"_"+id_sucursal+"_"+id_comercial+"_"+id_cotizaSolucion+"_"+id_cliente+"_"+id_proyecto+"_"+id_cotizaEstado+"_"+fechaOt+"_"+id_cotizacion+"_"+nroCoti+"_"+id_dibujante+"_"+fechaProbable;
				String idCotizacion = key[8];
				String fechaCoti = key[0];
				String fechaOt = key[7];
				String nameSucursal = "";
				String nameComercial = "";
				String nameSolucion = "";
				String nameCliente = "";
				String nameProyecto = "";
				String nameEstado = "";
				String nroCoti = key[9];
				String id_dibujante = key[10];
				String fechaProbable = "";
				if( key.length > 11) {
					fechaProbable = key[11];
				}

				Sucursal sucursal = mapSucursal.get(Long.parseLong(key[1]));
				if(sucursal != null) {
					nameSucursal = sucursal.getNombre();
				}

				Usuario comercial = mapComercial.get(Long.parseLong(key[2]));
				if(comercial != null) {
					nameComercial = comercial.getNombre();
				}

				CotizaSolucion solucion = mapSolucion.get(Long.parseLong(key[3]));
				if(solucion != null) {
					nameSolucion = solucion.getSolucion();
				}

				Cliente cliente = mapCliente.get(Long.parseLong(key[4]));
				if(cliente != null) {
					nameCliente = cliente.getNickName();
				}

				Proyecto proyecto = mapProyecto.get(Long.parseLong(key[5]));
				if(proyecto != null) {
					nameProyecto = proyecto.getNickName();
				}

				String nroOt = "";
				String idOt = "0";
				CotizaEstado estado = mapEstado.get(Long.parseLong(key[6]));
				if(estado != null) {
					nameEstado = estado.getEstado();
					if(estado.getId() == (long)-2) {
						Ot ot = mapOt.get(Long.parseLong(idCotizacion));
						if(ot != null) {
							nroOt = ot.getNumero().toString();
							idOt = ot.getId().toString();
							if(ot.getConfirmada() == (long)0) {
								nameEstado = nameEstado + " (PEND)";
							}else {
								nameEstado = nameEstado + " (CONF)";
							}
						}
					}
				}

				Dibujante dibujante = mapDibujante.get(Long.parseLong(id_dibujante));
				String nombreDibujante = "";
				if(dibujante != null) {
					nombreDibujante = dibujante.getNombre();
				}

				List<String> aux = new ArrayList<String>();
				aux.add(fechaCoti); //0 fecha Coti
				aux.add(nameSucursal); //1 sucursal.nombre
				aux.add(nameCliente); //2 cliente.nickName
				aux.add(nameProyecto); //3 proyecto.nombre
				aux.add(nameSolucion); //4 cotizaSolucion.solucion
				aux.add(nameEstado); //5 cotizaEstado.estado
				aux.add(nameComercial); //6 comercial usuario.nombre
				aux.add(DecimalFormato.formato(value.get(0), (long)0)); //7 valorizado
				aux.add(key[6]); //8 cotizaEstado.id
				aux.add(key[1]); //9 sucursal.id
				aux.add(key[4]); //10 cliente.id
				aux.add(key[5]); //11 proyecto.id
				aux.add(key[2]); //12 id_comercial
				aux.add(key[3]); //13 id_cotizaSolucion
				aux.add(DecimalFormato.formato(value.get(1), (long)0)); //14 peso en KG
				aux.add(fechaOt); //15 fecha Ot

				aux.add(nroCoti); //16 nro coti
				aux.add(nroOt); //17 nro Ot
				aux.add(idCotizacion); //18 id_cotizacion
				aux.add(idOt); //19 id_ot

				aux.add(Fechas.DDMMAA(fechaProbable)); //20 fechaProbable
				aux.add(nombreDibujante); //21 nombreDibujante

				detalle.add(aux);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return (detalle);
	}
	
	public static File detallePipelineDetExcel(String db, Map<String,String> mapDiccionario, List<List<String>> lista1,
			String tituloSucursal, String fechaDe, String fechaA) {
		
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
            
            CellStyle pie = libro.createCellStyle();
            pie.setBorderBottom(CellStyle.BORDER_THIN);
            pie.setBorderTop(CellStyle.BORDER_THIN);
            pie.setBorderRight(CellStyle.BORDER_THIN);
            pie.setBorderLeft(CellStyle.BORDER_THIN);
            pie.setFillPattern(CellStyle.SOLID_FOREGROUND);
            pie.setFillForegroundColor((short)19);
            pie.setAlignment(CellStyle.ALIGN_RIGHT);

			CreationHelper creationHelper = libro.getCreationHelper();

			CellStyle hora = libro.createCellStyle();
			hora.setDataFormat(creationHelper.createDataFormat().getFormat("hh:mm"));
			hora.setBorderBottom(CellStyle.BORDER_THIN);
			hora.setBorderTop(CellStyle.BORDER_THIN);
			hora.setBorderRight(CellStyle.BORDER_THIN);
			hora.setBorderLeft(CellStyle.BORDER_THIN);

			CellStyle fecha = libro.createCellStyle();
			fecha.setDataFormat(creationHelper.createDataFormat().getFormat("dd/MM/yyyy"));
			fecha.setBorderBottom(CellStyle.BORDER_THIN);
			fecha.setBorderTop(CellStyle.BORDER_THIN);
			fecha.setBorderRight(CellStyle.BORDER_THIN);
			fecha.setBorderLeft(CellStyle.BORDER_THIN);
            
            
            //titulos del archivo
            
            libro.setSheetName(0, "PIPELINE");
            Sheet hoja1 = libro.getSheetAt(0);
            
            Row row = null;
            Cell cell = null;
            
            row = hoja1.createRow(1);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("REPORTE PIPELINE DETALLADO "+tituloSucursal);
			
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
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("PERIODO: desde " + fechaDe  + " hasta " + fechaA);
			
			
			//anchos de columnas
			for(int i=1; i<17; i++) {
				hoja1.setColumnWidth(i, 6*1000);
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
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FECHA COTI");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("NRO COTI");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("SUCURSAL");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("COMERCIAL");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("SOLUCION");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("CLIENTE");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("PROYECTO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("ESTADO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FECHA " + mapDiccionario.get("OT"));

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("NRO " + mapDiccionario.get("OT"));
		        
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TOTAL NETO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("PESO KG");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FECHA PROBABLE");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("DIBUJANTE/PROYECTISTA");
				
	        
			for(int i=0;i<lista1.size();i++){
				
						posRow++;
						posCell = 0;
				        row = hoja1.createRow(posRow);
						
				        posCell++;
						cell = row.createCell(posCell);
						Fechas fechax = Fechas.obtenerFechaDesdeStrAAMMDD(Fechas.AAMMDD(lista1.get(i).get(0)));
						cell.setCellValue(fechax.fechaUtil);
						cell.setCellStyle(fecha);

						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						Long auxL = Long.parseLong(lista1.get(i).get(16));
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(auxL);
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(lista1.get(i).get(1));
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(lista1.get(i).get(6));
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(lista1.get(i).get(4));
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(lista1.get(i).get(2));
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(lista1.get(i).get(3));
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(lista1.get(i).get(5));
						
						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						if( ! lista1.get(i).get(19).equals("0")) {
							fechax = Fechas.obtenerFechaDesdeStrAAMMDD(Fechas.AAMMDD(lista1.get(i).get(0)));
							cell.setCellValue(fechax.fechaUtil);
							cell.setCellStyle(fecha);
						}

						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						if( ! lista1.get(i).get(19).equals("0")) {
							auxL = Long.parseLong(lista1.get(i).get(17));
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(auxL);
						}
							
						posCell++; 
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						Double aux = Double.parseDouble(lista1.get(i).get(7).replaceAll(",", ""));
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(aux);
						
						posCell++; 
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
			            aux = Double.parseDouble(lista1.get(i).get(14).replaceAll(",", ""));
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(aux);

						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						if( ! lista1.get(i).get(20).equals("")) {
							fechax = Fechas.obtenerFechaDesdeStrAAMMDD(Fechas.AAMMDD(lista1.get(i).get(20)));
							cell.setCellValue(fechax.fechaUtil);
							cell.setCellStyle(fecha);
						}

						posCell++;
						cell = row.createCell(posCell);
						cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(lista1.get(i).get(21));
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
	
	public static Map<String,Double>  calculoOt(Connection con, String db, String desdeAAMMDD, String hastaAAMMDD, String pais, String condSucursal, String condComercial) {
		
		Map<String,TasasCambio> mapAllTasas = TasasCambio.mapTasasporAllFecha(con, db, pais);
		Map<String,Double> resultado = new HashMap<String,Double>();
		
		try {
		
			PreparedStatement smt1 = con
					.prepareStatement(" select if(cotizaDetalle.esVenta=1,  sum(cotizaDetalle.precioVenta*cotizaDetalle.cantidad)*(1-cotizacion.dctoVenta),  "
							+ "	sum(cotizaDetalle.precioArriendo*cotizaDetalle.cantidad*cotizaDetalle.permanencia)*(1-cotizacion.dctoArriendo)  ) as valorizado, "
							+ "	if(cotizacion.id_ot>0,1,0) as conOt, cotizaDetalle.id_moneda, cotizacion.fecha, otEstado.estado "
							+ "	from `"+db+"`.cotizaDetalle "
							+ "	left join  `"+db+"`.cotizacion on cotizacion.id = cotizaDetalle.id_cotizacion "
							+ "	left join  `"+db+"`.ot on ot.id_cotizacion = cotizaDetalle.id_cotizacion "
							+ "	left join  `"+db+"`.otEstado on otEstado.id = ot.id_otEstado "
							+ " left join  `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = cotizacion.id_bodegaEmpresa "
							+ "	where cotizacion.fecha between ? and ? and cotizacion.confirmada = 1 " + condSucursal + " " + condComercial
							+ "	group by conOt, cotizaDetalle.id_moneda, cotizacion.fecha, otEstado.estado;");
			smt1.setString(1, desdeAAMMDD);
			smt1.setString(2, hastaAAMMDD);

			ResultSet rsValConOtSinOt = smt1.executeQuery();
			Double valConOt = (double)0;
			Double valSinOt = (double)0;
			while(rsValConOtSinOt.next()) {
				Double valorTasa = (double)1;
				TasasCambio tasasCambio = mapAllTasas.get(rsValConOtSinOt.getString(4));
				Map<Long,Double> tasas = new HashMap<Long,Double>();  // id_moneda, valor tasa
				if(tasasCambio!=null) {
					tasas.put((long)1, (double)1);
					tasas.put((long)2, Double.parseDouble(tasasCambio.getClpUsd().replaceAll(",", "")));
					tasas.put((long)3, Double.parseDouble(tasasCambio.getClpEur().replaceAll(",", "")));
					tasas.put((long)4, Double.parseDouble(tasasCambio.getClpUf().replaceAll(",", "")));
					valorTasa = tasas.get(rsValConOtSinOt.getLong(3));
					if(valorTasa == null) {
						valorTasa = (double)1;
					}
				}
				
				if(rsValConOtSinOt.getString(2).equals("1")) {
					valConOt += rsValConOtSinOt.getDouble(1) * valorTasa;
					Double valor = resultado.get("val_"+rsValConOtSinOt.getString(5));
					if(valor == null) {
						resultado.put("val_"+rsValConOtSinOt.getString(5), rsValConOtSinOt.getDouble(1));
					}else {
						resultado.put("val_"+rsValConOtSinOt.getString(5), (valor + rsValConOtSinOt.getDouble(1)*valorTasa));
					}
				}else {
					valSinOt += rsValConOtSinOt.getDouble(1)*valorTasa;
				}
			}
			resultado.put("val_SinOt", valSinOt);
			resultado.put("val_ConOt", valConOt);
			smt1.close();
			rsValConOtSinOt.close();
			
			
			PreparedStatement smt3 = con
					.prepareStatement(" select count(cotizacion.id), if(cotizacion.id_ot>0,1,0) as conOt, cotizaEstado.estado "
							+ " from `"+db+"`.cotizacion "
							+ "	left join  `"+db+"`.cotizaEstado on cotizaEstado.id = cotizacion.id_cotizaEstado "
							+ " left join  `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = cotizacion.id_bodegaEmpresa "
							+ " where cotizacion.fecha between ? and ? and cotizacion.confirmada = 1 " + condSucursal + " " + condComercial
							+ " group by conOt, cotizaEstado.estado;");
			
			smt3.setString(1, desdeAAMMDD);
			smt3.setString(2, hastaAAMMDD);
			
			ResultSet rsCantConOtSinOt = smt3.executeQuery();
			Double cantConOt = (double)0;
			Double cantSinOt = (double)0;
			while(rsCantConOtSinOt.next()) {
				if(rsCantConOtSinOt.getString(2).equals("1")) {
					cantConOt += rsCantConOtSinOt.getDouble(1);
					Double valor = resultado.get("cant_"+rsCantConOtSinOt.getString(3));
					if(valor == null) {
						resultado.put("cant_"+rsCantConOtSinOt.getString(3), rsCantConOtSinOt.getDouble(1));
					}else {
						resultado.put("cant_"+rsCantConOtSinOt.getString(3), (valor + rsCantConOtSinOt.getDouble(1)));
					}
				}else {
					cantSinOt += rsCantConOtSinOt.getDouble(1);
				}
			}
			resultado.put("cant_SinOt", cantSinOt);
			resultado.put("cant_ConOt", cantConOt);
			smt3.close();
			rsCantConOtSinOt.close();
			
			
			PreparedStatement smt4 = con
					.prepareStatement(" select count(ot.id), otEstado.estado "
							+ "	from `"+db+"`.ot "
							+ "	left join  `"+db+"`.otEstado on otEstado.id = ot.id_otEstado "
							+ "	where ot.id in "
							+ " 	(select cotizacion.id_ot "
							+ "		from `"+db+"`.cotizacion "
							+ " 	left join  `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = cotizacion.id_bodegaEmpresa "
							+ "		where cotizacion.fecha between ? and ? and id_ot>0 " + condSucursal + " " + condComercial+") "
							+ "	group by otEstado.estado;");
			
			smt4.setString(1, desdeAAMMDD);
			smt4.setString(2, hastaAAMMDD);
		
			ResultSet rs4 = smt4.executeQuery();
			while(rs4.next()) {
				resultado.put("cant_"+rs4.getString(2), rs4.getDouble(1));
			}
			smt4.close();
			rs4.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return (resultado);
		
	}
	
		
			
}
	
	
	
