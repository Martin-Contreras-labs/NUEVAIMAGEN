
package models.qr;

import models.tables.PlanMantencion;
import models.utilities.Archivos;
import models.utilities.Fechas;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.TempFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class QrTransacEquipo{

	public Long orden;
	public Long id_equipo;
	public Long id_qrAtributoEquipo;
	public String contenido;
	
	public String campo;
	public Long id_qrTipoContenido;
	public String tipo;
	public String extencion;
	public String fechaVencimiento;
	public Long diasPreAviso;

	public Long activo;

	public String colorCelda;
	

	public QrTransacEquipo(Long orden, Long id_equipo, Long id_qrAtributoEquipo, String contenido, String campo,
			Long id_qrTipoContenido, String tipo, String extencion, String fechaVencimiento, Long diasPreAviso, Long activo, String colorCelda) {
		super();
		this.orden = orden;
		this.id_equipo = id_equipo;
		this.id_qrAtributoEquipo = id_qrAtributoEquipo;
		this.contenido = contenido;
		this.campo = campo;
		this.id_qrTipoContenido = id_qrTipoContenido;
		this.tipo = tipo;
		this.extencion = extencion;
		this.fechaVencimiento = fechaVencimiento;
		this.diasPreAviso = diasPreAviso;
		this.activo = activo;
		this.colorCelda = colorCelda;
	}

	public QrTransacEquipo() {super();}


	public static String colorCelda(String fechaVencimiento, String diasPreAviso) {
		if (fechaVencimiento == null || diasPreAviso == null || fechaVencimiento.trim().equals("") || diasPreAviso.trim().equals("")) {
			return "white";
		}
		Fechas vencimiento = Fechas.obtenerFechaDesdeStrAAMMDD(fechaVencimiento);
		int diasPre = Integer.parseInt(diasPreAviso);
		Fechas hoy = Fechas.hoy();
		Fechas hoyMas15 = Fechas.addDias(hoy.getFechaCal(),diasPre);
		if (vencimiento.getFechaCal() == null || hoy.getFechaCal() == null) {
			return "white";
		}
		if (hoy.getFechaUtil().after(vencimiento.getFechaUtil())) {
			return "red";
		} else if (hoyMas15.getFechaUtil().after(vencimiento.getFechaUtil())) {
			return "yellow";
		} else {
			return "white";
		}
	}

	public static List<QrTransacEquipo> allEquiposActivos(Connection con, String db) {
		List<QrTransacEquipo> lista = new ArrayList<QrTransacEquipo>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select " +
							" qrTransacEquipo.orden, " +
							" qrTransacEquipo.id_equipo, " +
							" qrTransacEquipo.id_qrAtributoEquipo, " +
							" ifnull(qrTransacEquipo.contenido,0), " +
							" qrAtributoEquipo.campo, " +
							" qrAtributoEquipo.id_qrTipoContenido, " +
							" qrTipoContenido.tipo, " +
							" qrTipoContenido.extencion, " +
							" ifnull(qrTransacEquipo.fechaVencimiento,''), " +
							" qrTransacEquipo.diasPreAviso, " +
							" qrTransacEquipo.activo " +
							" from `"+db+"`.qrTransacEquipo " +
							" left join `"+db+"`.qrAtributoEquipo on qrAtributoEquipo.id = qrTransacEquipo.id_qrAtributoEquipo " +
							" left join `"+db+"`.qrTipoContenido on qrTipoContenido.id = qrAtributoEquipo.id_qrTipoContenido " +
							" order by qrTransacEquipo.orden;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				String colorCelda = colorCelda(rs.getString(9),rs.getString(10));
				lista.add(new QrTransacEquipo(rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getString(4),
						rs.getString(5),rs.getLong(6),rs.getString(7),rs.getString(8),
						rs.getString(9),rs.getLong(10),rs.getLong(11), colorCelda));
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}

	public static List<QrTransacEquipo> allPorIdEquipo(Connection con, String db, Long idEquipo) {
		List<QrTransacEquipo> lista = new ArrayList<QrTransacEquipo>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select " + 
							" qrTransacEquipo.orden, " + 
							" qrTransacEquipo.id_equipo, " + 
							" qrTransacEquipo.id_qrAtributoEquipo, " + 
							" ifnull(qrTransacEquipo.contenido,0), " + 
							" qrAtributoEquipo.campo, " + 
							" qrAtributoEquipo.id_qrTipoContenido, " + 
							" qrTipoContenido.tipo, " + 
							" qrTipoContenido.extencion, " +
							" ifnull(qrTransacEquipo.fechaVencimiento,''), " +
							" qrTransacEquipo.diasPreAviso, " +
							" qrTransacEquipo.activo " +
							" from `"+db+"`.qrTransacEquipo " + 
							" left join `"+db+"`.qrAtributoEquipo on qrAtributoEquipo.id = qrTransacEquipo.id_qrAtributoEquipo " + 
							" left join `"+db+"`.qrTipoContenido on qrTipoContenido.id = qrAtributoEquipo.id_qrTipoContenido " + 
							" where qrTransacEquipo.id_equipo =? order by qrTransacEquipo.orden;");
			smt.setLong(1, idEquipo);
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				String colorCelda = colorCelda(rs.getString(9),rs.getString(10));
				lista.add(new QrTransacEquipo(rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getString(4),
						rs.getString(5),rs.getLong(6),rs.getString(7),rs.getString(8),
						rs.getString(9),rs.getLong(10),rs.getLong(11), colorCelda));
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<QrTransacEquipo> allPorIdEquipoSoloActivos(Connection con, String db, Long idEquipo) {
		List<QrTransacEquipo> lista = new ArrayList<QrTransacEquipo>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select " + 
							" qrTransacEquipo.orden, " + 
							" qrTransacEquipo.id_equipo, " + 
							" qrTransacEquipo.id_qrAtributoEquipo, " + 
							" ifnull(qrTransacEquipo.contenido,0), " + 
							" qrAtributoEquipo.campo, " + 
							" qrAtributoEquipo.id_qrTipoContenido, " + 
							" qrTipoContenido.tipo, " + 
							" qrTipoContenido.extencion, " +
							" ifnull(qrTransacEquipo.fechaVencimiento,''), " +
							" qrTransacEquipo.diasPreAviso, " +
							" qrTransacEquipo.activo " +
							" from `"+db+"`.qrTransacEquipo " + 
							" left join `"+db+"`.qrAtributoEquipo on qrAtributoEquipo.id = qrTransacEquipo.id_qrAtributoEquipo " + 
							" left join `"+db+"`.qrTipoContenido on qrTipoContenido.id = qrAtributoEquipo.id_qrTipoContenido " + 
							" where qrTransacEquipo.id_equipo =? and qrTransacEquipo.activo=1 order by qrTransacEquipo.orden;");
			smt.setLong(1, idEquipo);
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				String colorCelda = colorCelda(rs.getString(9),rs.getString(10));
				lista.add(new QrTransacEquipo(rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getString(4),
						rs.getString(5),rs.getLong(6),rs.getString(7),rs.getString(8),
						rs.getString(9),rs.getLong(10),rs.getLong(11), colorCelda));
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static boolean updatePorCampo(Connection con, String db, String idEquipo, String idCampo, String campoMySql, String valor) {
		boolean flag=false;
		if(campoMySql.equals("fechaVencimiento") && valor.trim().equals("")) {
			try {
				PreparedStatement smt = con
						.prepareStatement("update `"+db+"`.qrTransacEquipo set fechaVencimiento = null where id_equipo=? and id_qrAtributoEquipo=?;");
				smt.setString(1, idEquipo.trim());
				smt.setString(2, idCampo.trim());
				smt.executeUpdate();
				smt.close();
				flag=true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else{
			try {
				PreparedStatement smt = con
						.prepareStatement("update `"+db+"`.qrTransacEquipo set `"+campoMySql.trim()+"` = '"+valor.trim()+"' where id_equipo=? and id_qrAtributoEquipo=?;");
				smt.setString(1, idEquipo.trim());
				smt.setString(2, idCampo.trim());
				smt.executeUpdate();
				smt.close();
				flag=true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return (flag);
	}
	
	public static QrTransacEquipo find(Connection con, String db, String idEquipo, String idCampo) {
		QrTransacEquipo aux = new QrTransacEquipo();
		try {
			PreparedStatement smt = con
					.prepareStatement("select " + 
							" qrTransacEquipo.orden, " + 
							" qrTransacEquipo.id_equipo, " + 
							" qrTransacEquipo.id_qrAtributoEquipo, " + 
							" ifnull(qrTransacEquipo.contenido,0), " + 
							" qrAtributoEquipo.campo, " + 
							" qrAtributoEquipo.id_qrTipoContenido, " + 
							" qrTipoContenido.tipo, " + 
							" qrTipoContenido.extencion, " +
							" ifnull(qrTransacEquipo.fechaVencimiento,''), " +
							" qrTransacEquipo.diasPreAviso, " +
							" qrTransacEquipo.activo " +
							" from `"+db+"`.qrTransacEquipo " + 
							" left join `"+db+"`.qrAtributoEquipo on qrAtributoEquipo.id = qrTransacEquipo.id_qrAtributoEquipo " + 
							" left join `"+db+"`.qrTipoContenido on qrTipoContenido.id = qrAtributoEquipo.id_qrTipoContenido " + 
							" where qrTransacEquipo.id_equipo =? and qrTransacEquipo.id_qrAtributoEquipo=?;");
			smt.setString(1, idEquipo.trim());
			smt.setString(2, idCampo.trim());
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				String colorCelda = colorCelda(rs.getString(9),rs.getString(10));
				aux = new QrTransacEquipo(rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getString(4),
						rs.getString(5),rs.getLong(6),rs.getString(7),rs.getString(8),
						rs.getString(9),rs.getLong(10),rs.getLong(11), colorCelda);
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}
	
	public static boolean eliminaCampo(Connection con, String db, QrTransacEquipo qrTransacEquipo) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("delete from `"+db+"`.qrTransacEquipo where id_equipo=? and id_qrAtributoEquipo=?;");
			smt.setLong(1, qrTransacEquipo.id_equipo);
			smt.setLong(2, qrTransacEquipo.id_qrAtributoEquipo);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean create(Connection con, String db, String idEquipo, String idCampo) {
		boolean flag=false;
		try {
			Long orden=(long)1;
			PreparedStatement smt2 = con.prepareStatement("select max(orden) from `"+db+"`.qrTransacEquipo;");
			ResultSet rs2 = smt2.executeQuery();
			if(rs2.next()) {
				orden=rs2.getLong(1)+1;
			}
			rs2.close();smt2.close();
			PreparedStatement smt = con
					.prepareStatement("insert into `"+db+"`.qrTransacEquipo (id_equipo,id_qrAtributoEquipo,orden) values (?,?,?);");
			smt.setString(1, idEquipo);
			smt.setString(2, idCampo);
			smt.setString(3, orden.toString());
			smt.executeUpdate();
			smt.close();
			flag=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}

	public static File qrRevisarDatosAllVigentesExcel(String db, Map<String,String> mapDiccionario, List<List<String>> lista, Fechas hoy) {

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

			libro.setSheetName(0, "PROCESO DE EP-FACTURACION LISTADO");
			Sheet hoja1 = libro.getSheetAt(0);

			Row row = null;
			Cell cell = null;

			row = hoja1.createRow(1);
			cell = row.createCell(1);
			cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("REVISAR ATRIBUTOS QR DE TODOS LOS EQUIPOS VIGENTES");

			row = hoja1.createRow(2);
			cell = row.createCell(1);
			cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("EMPRESA: "+mapDiccionario.get("nEmpresa"));

			row = hoja1.createRow(3);
			cell = row.createCell(1);
			cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FECHA: "+hoy.getFechaStrDDMMAA());



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
			cell.setCellValue("CODIGO");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("EQUIPO");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("ATRIBUTO");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("VALOR");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FECHA VENCIMIENTO");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("PUBLICAR");

			for(List<String> lista1: lista){
				posRow++;
				posCell = 0;
				row = hoja1.createRow(posRow);

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista1.get(0));

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista1.get(1));

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista1.get(2));

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				if(lista1.get(3).equals("1")){
					if(!lista1.get(4).equals("0")){
						cell.setCellValue(lista1.get(4));
					}
				}else{
					if(!lista1.get(4).equals("0")){
						cell.setCellValue(lista1.get(6)+" Adjunto");
					}
				}

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(fecha);
				if(!lista1.get(8).equals("")){
					Fechas auxFecha = Fechas.obtenerFechaDesdeStrAAMMDD(lista1.get(8));
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue(auxFecha.getFechaUtil());
				}


				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				if(lista1.get(11).equals("1")){
					cell.setCellValue("SI");
				}
				if(lista1.get(11).equals("0")){
					cell.setCellValue("NO");
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
