package models.tables;

import controllers.HomeController;
import models.utilities.Archivos;
import models.utilities.DecimalFormato;
import models.utilities.Fechas;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.TempFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class ProformaEstado {
	public Long id;
	public String fecha;
	public String desde;
	public String hasta;
	public Long id_cliente;
	public Long id_bodegaEmpresa;
	public Long id_proyecto;
	public String excel;
	public String pdf;
	public Double neto;
	public Double iva;
	public Double total;
	public String une_ajustesEP;

	public ProformaEstado(Long id, String fecha, String desde, String hasta, Long id_cliente, Long id_bodegaEmpresa, Long id_proyecto, String excel, String pdf,
						  Double neto, Double iva, Double total, String une_ajustesEP) {
		this.id = id;
		this.fecha = fecha;
		this.desde = desde;
		this.hasta = hasta;
		this.id_cliente = id_cliente;
		this.id_bodegaEmpresa = id_bodegaEmpresa;
		this.id_proyecto = id_proyecto;
		this.excel = excel;
		this.pdf = pdf;
		this.neto = neto;
		this.iva = iva;
		this.total = total;
		this.une_ajustesEP = une_ajustesEP;
	}

	public ProformaEstado() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getDesde() {
		return desde;
	}

	public void setDesde(String desde) {
		this.desde = desde;
	}

	public String getHasta() {
		return hasta;
	}

	public void setHasta(String hasta) {
		this.hasta = hasta;
	}

	public Long getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Long id_cliente) {
		this.id_cliente = id_cliente;
	}

	public Long getId_bodegaEmpresa() {
		return id_bodegaEmpresa;
	}

	public void setId_bodegaEmpresa(Long id_bodegaEmpresa) {
		this.id_bodegaEmpresa = id_bodegaEmpresa;
	}

	public Long getId_proyecto() {
		return id_proyecto;
	}

	public void setId_proyecto(Long id_proyecto) {
		this.id_proyecto = id_proyecto;
	}

	public String getExcel() {
		return excel;
	}

	public void setExcel(String excel) {
		this.excel = excel;
	}

	public String getPdf() {
		return pdf;
	}

	public void setPdf(String pdf) {
		this.pdf = pdf;
	}

	public Double getNeto() {
		return neto;
	}

	public void setNeto(Double neto) {
		this.neto = neto;
	}

	public Double getIva() {
		return iva;
	}

	public void setIva(Double iva) {
		this.iva = iva;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getUne_ajustesEP() {
		return une_ajustesEP;
	}

	public void setUne_ajustesEP(String une_ajustesEP) {
		this.une_ajustesEP = une_ajustesEP;
	}

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	public static ProformaEstado create(Connection con, String db, Fechas hoy, Fechas desde, Fechas hasta, BodegaEmpresa bodegaEmpresa) {
		String query = String.format("insert into  `%s`.proformaEstado (fecha,desde,hasta,id_cliente,id_bodegaEmpresa,id_proyecto,une_ajustesEP) values (?,?,?,?,?,?,?);",db);
		try (PreparedStatement smt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			smt.setString(1,hoy.getFechaStrAAMMDD());
			smt.setString(2,desde.getFechaStrAAMMDD());
			smt.setString(3,hasta.getFechaStrAAMMDD());
			smt.setLong(4, bodegaEmpresa.getId_cliente());
			smt.setLong(5, bodegaEmpresa.getId());
			smt.setLong(6, bodegaEmpresa.getId_proyecto());
			String une_ajustesEP = bodegaEmpresa.getId()+"::"+desde.getFechaStrAAMMDD()+"::"+hasta.getFechaStrAAMMDD();
			smt.setString(7,une_ajustesEP);
			smt.executeUpdate();
			try (ResultSet generatedKeys = smt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					Long id = generatedKeys.getLong(1);
					query = String.format("update  `%s`.proformaEstado set excel = ?, pdf=? where id = ?;",db);
					try (PreparedStatement smt2 = con.prepareStatement(query)) {
						smt2.setString(1, "profEstadoXLS_"+id+".xlsx");
						smt2.setString(2, "profEstadoPDF_"+id+".pdf");
						smt2.setLong(3, id);
						smt2.executeUpdate();
						return (new ProformaEstado(id, hoy.getFechaStrAAMMDD(), desde.getFechaStrAAMMDD(), hasta.getFechaStrAAMMDD(),
								bodegaEmpresa.getId_cliente(), bodegaEmpresa.getId(), bodegaEmpresa.getId_proyecto(),
								"profEstadoXLS_"+id+".xlsx", "profEstadoPDF_"+id+".pdf", 0.0, 0.0, 0.0, une_ajustesEP));
					}
				}
			}
		} catch (SQLException e) {
			String className = ActaBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (null);
	}

	public static boolean updateTotales(Connection con, String db, ProformaEstado proforma) {
		String query = String.format("update  `%s`.proformaEstado set neto = ?, iva=?, total=? where id = ?;",db);
		try (PreparedStatement smt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			smt.setDouble(1,proforma.getNeto());
			smt.setDouble(2,proforma.getIva());
			smt.setDouble(3,proforma.getTotal());
			smt.setLong(4, proforma.getId());
			smt.executeUpdate();
			return (true);
		} catch (SQLException e) {
			String className = ActaBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (false);
	}

	public static List<List<String>> listadoPorPeriodo(Connection con, String db, String desde, String hasta) {
		List<List<String>> lista = new ArrayList<List<String>>();
		String query = String.format("select" +
				" id," +
				" fecha," +
				" desde," +
				" hasta," +
				" id_cliente," +
				" id_bodegaEmpresa," +
				" id_proyecto," +
				" excel," +
				" pdf," +
				" neto," +
				" iva," +
				" total," +
				" une_ajustesEP" +
				" from  `%s`.proformaEstado" +
				" where desde >= ? and hasta <= ? ",db);
		try (PreparedStatement smt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			smt.setString(1, desde);
			smt.setString(2, hasta);
			try (ResultSet rs = smt.executeQuery()) {
				Map<Long, List<String>> mapBodegasExternas = BodegaEmpresa.mapAllVigentesExternas(con, db, "0","0");
				Long nroDec = (long) Moneda.numeroDecimalxId(con, db, "1");
				while (rs.next()) {
					List<String> bodega = mapBodegasExternas.get(rs.getLong(6));
					if(bodega != null) {
						List<String> aux = new ArrayList<String>();
						aux.add(rs.getString(1));	// 0 id numero
						aux.add(rs.getString(2));	// 1 fecha
						aux.add(rs.getString(3));	// 2 desde
						aux.add(rs.getString(4));	// 3 hasta
						aux.add(bodega.get(16));				// 4 nom sucursal
						aux.add(bodega.get(5));					// 5 nomBodega
						aux.add(bodega.get(7));					// 6 nomCliente
						aux.add(bodega.get(8));					// 7 nomProyecto
						aux.add(rs.getString(8));	// 8 excel
						aux.add(rs.getString(9));	// 9 pdf
						aux.add(DecimalFormato.formato(rs.getDouble(10),nroDec));	// 10 neto
						aux.add(DecimalFormato.formato(rs.getDouble(11),nroDec));	// 11 iva
						aux.add(DecimalFormato.formato(rs.getDouble(12),nroDec));	// 12 total
						aux.add(rs.getString(13));	// 13 une_ajustesEP
						lista.add(aux);
					}
				}

			}
		} catch (SQLException e) {
			String className = ActaBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (lista);
	}


	public static boolean deleteAll(Connection con, String db, String desde, String hasta) {
		String query = String.format("delete from `%s`.proformaEstado where desde >= ? and hasta <= ?;",db);
		try (PreparedStatement smt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			smt.setString(1,desde);
			smt.setString(2,hasta);
			smt.executeUpdate();
			query = String.format("delete from `%s`.ajustesEP where une_proformaEstado LIKE ?;",db);
			try(PreparedStatement smt2 = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
				smt2.setString(1,"%"+desde+"::"+hasta+"%");
				smt2.executeUpdate();
				return (true);
			}
		} catch (SQLException e) {
			String className = ActaBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (false);
	}

	public static boolean deleteUno(Connection con, String db, String desde, String hasta, Long id_bodegaEmpresa) {
		String query = String.format("delete from `%s`.proformaEstado where desde >= ? and hasta <= ? and id_bodegaEmpresa = ?;",db);
		try (PreparedStatement smt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			smt.setString(1,desde);
			smt.setString(2,hasta);
			smt.setLong(3,id_bodegaEmpresa);
			smt.executeUpdate();
			query = String.format("delete from `%s`.ajustesEP where une_proformaEstado LIKE ?;",db);
			try(PreparedStatement smt2 = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
				smt2.setString(1,"%"+id_bodegaEmpresa+"::"+desde+"::"+hasta+"%");
				smt2.executeUpdate();
				return (true);
			}
		} catch (SQLException e) {
			String className = ActaBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (false);
	}

	public static boolean deletePorId(Connection con, String db, Long id_proformaEstado) {
		String query = String.format("delete from `%s`.proformaEstado where id = ?;",db);
		try (PreparedStatement smt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			smt.setLong(1,id_proformaEstado);
			smt.executeUpdate();
			query = String.format("delete from `%s`.ajustesEP where id_proformaEstado = ?;",db);
			try(PreparedStatement smt2 = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
				smt2.setLong(1,id_proformaEstado);
				smt2.executeUpdate();
				return (true);
			}
		} catch (SQLException e) {
			String className = ActaBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (false);
	}

	public static File listadoPorAnioExcel(String db, Map<String,String> mapDiccionario, List<List<String>> lista) {

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

			DataFormat format = libro.createDataFormat();
			CellStyle formatFecha = libro.createCellStyle();
			formatFecha.setBorderBottom(CellStyle.BORDER_THIN);
			formatFecha.setBorderTop(CellStyle.BORDER_THIN);
			formatFecha.setBorderRight(CellStyle.BORDER_THIN);
			formatFecha.setBorderLeft(CellStyle.BORDER_THIN);
			formatFecha.setDataFormat(format.getFormat("dd-mm-yyyy"));



			//titulos del archivo

			libro.setSheetName(0, "LISTADO");
			Sheet hoja1 = libro.getSheetAt(0);

			Row row = null;
			Cell cell = null;

			row = hoja1.createRow(1);
			cell = row.createCell(1);
			cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("LISTADO ESTADO EQUIPO COBRAR (AJUSTES)");

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



			//anchos de columnas
			for(int i=1; i<17; i++) {
				hoja1.setColumnWidth(i, 4*1000);
			}

			hoja1.setColumnWidth(6, 15*1000);
			hoja1.setColumnWidth(7, 15*1000);
			hoja1.setColumnWidth(8, 15*1000);

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


			// encabezado de la tabla

			int posRow = 8;

			row = hoja1.createRow(posRow);
			int posCell = 0;

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("LISTADO:");

			posRow += 2;
			posCell = 0;

			row = hoja1.createRow(posRow);

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("NUMERO");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FECHA");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("DESDE");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("HASTA");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("SUCURSAL");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(mapDiccionario.get("BODEGA")+"/PROYECTO");

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
			cell.setCellValue("NETO");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("IVA");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TOTAL");

			for(int i=0;i<lista.size();i++){

				posRow++;
				posCell = 0;
				row = hoja1.createRow(posRow);

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(0));

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(formatFecha);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				Fechas fecha = Fechas.obtenerFechaDesdeStrAAMMDD(lista.get(i).get(1));
				cell.setCellValue(fecha.getFechaUtil());

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(formatFecha);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				fecha = Fechas.obtenerFechaDesdeStrAAMMDD(lista.get(i).get(2));
				cell.setCellValue(fecha.getFechaUtil());

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(formatFecha);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				fecha = Fechas.obtenerFechaDesdeStrAAMMDD(lista.get(i).get(3));
				cell.setCellValue(fecha.getFechaUtil());

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(formatFecha);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(4));

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(5));

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(6));

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(7));

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				Double aux = Double.parseDouble(lista.get(i).get(10).replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				aux = Double.parseDouble(lista.get(i).get(11).replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				aux = Double.parseDouble(lista.get(i).get(12).replaceAll(",", ""));
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


}
