package models.tables;

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
import org.apache.poi.ss.usermodel.DataFormat;
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




public class Redimensionar {
	public Long id;
	public Long id_actaRedimensionar;
	public Long id_equipoOrigen;
	public Double cant_equipoOrigen;
	public Long id_equipoRedimensionar;
	public Double cantEquipoRedimensionar;
	
	public Redimensionar(Long id, Long id_actaRedimensionar, Long id_equipoOrigen, Double cant_equipoOrigen,
			Long id_equipoRedimensionar, Double cantEquipoRedimensionar) {
		super();
		this.id = id;
		this.id_actaRedimensionar = id_actaRedimensionar;
		this.id_equipoOrigen = id_equipoOrigen;
		this.cant_equipoOrigen = cant_equipoOrigen;
		this.id_equipoRedimensionar = id_equipoRedimensionar;
		this.cantEquipoRedimensionar = cantEquipoRedimensionar;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId_actaRedimensionar() {
		return id_actaRedimensionar;
	}

	public void setId_actaRedimensionar(Long id_actaRedimensionar) {
		this.id_actaRedimensionar = id_actaRedimensionar;
	}

	public Long getId_equipoOrigen() {
		return id_equipoOrigen;
	}

	public void setId_equipoOrigen(Long id_equipoOrigen) {
		this.id_equipoOrigen = id_equipoOrigen;
	}

	public Double getCant_equipoOrigen() {
		return cant_equipoOrigen;
	}

	public void setCant_equipoOrigen(Double cant_equipoOrigen) {
		this.cant_equipoOrigen = cant_equipoOrigen;
	}

	public Long getId_equipoRedimensionar() {
		return id_equipoRedimensionar;
	}

	public void setId_equipoRedimensionar(Long id_equipoRedimensionar) {
		this.id_equipoRedimensionar = id_equipoRedimensionar;
	}

	public Double getCantEquipoRedimensionar() {
		return cantEquipoRedimensionar;
	}

	public void setCantEquipoRedimensionar(Double cantEquipoRedimensionar) {
		this.cantEquipoRedimensionar = cantEquipoRedimensionar;
	}

	static SimpleDateFormat myformatfecha = new SimpleDateFormat("dd-MM-yyyy");
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatint = new DecimalFormat("#,##0");
	
	
	
	public static boolean create(Connection con, String db, String detalle) {
		boolean flag = false;
		try {
			PreparedStatement smt1 = con
					.prepareStatement("INSERT INTO `"+db+"`.redimensionar "
							+ " (id_actaRedimensionar, id_equipoOrigen, cantEquipoOrigen, id_equipoRedimensionar, cantEquipoRedimensionar)"
							+ " VALUES "+detalle+";");
			smt1.executeUpdate();
			smt1.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	
	
	
	
	//***********************************
	
	public static Map<Long,Double> totalDeBajasPorIdEquipoPendientes(Connection con, String db){
		Map<Long,Double> aux = new HashMap<Long,Double>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select id_equipo,sum(cantidad) from `"+db+"`.baja where fechaConfirma is null  group by id_equipo;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				aux.put(rs.getLong(1),rs.getDouble(2));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}
	
	public static Map<Long,Double> totalDeBajasPorIdEquipo(Connection con, String db){
		Map<Long,Double> aux = new HashMap<Long,Double>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select id_equipo,sum(cantidad) from `"+db+"`.baja group by id_equipo;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				aux.put(rs.getLong(1),rs.getDouble(2));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}
	
	public static List<List<String>> allPorEquipo(Connection con, String db, Long id_equipo){
		List<List<String>> lista = new ArrayList<List<String>>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" actaBaja.actaBajaPDF, " +
							" actaBaja.fecha, " +
							" actaBaja.numero, " +
							" equipo.codigo, " +
							" equipo.nombre, " +
							" unidad.nombre, " +
							" baja.cantidad, " +
							" baja.motivo, " +
							" baja.fechaConfirma " +
							" from `"+db+"`.baja " +
							" left join `"+db+"`.actaBaja on actaBaja.id = baja.id_actaBaja " +
							" left join `"+db+"`.equipo on equipo.id = baja.id_equipo " +
							" left join `"+db+"`.unidad on unidad.id = equipo.id_unidad " +
							" where baja.id_equipo= ? order by actaBaja.fecha desc;");
			smt.setLong(1, id_equipo);
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				List<String> aux = new ArrayList<String>();
				aux.add(rs.getString(1));
				String fecha = null;		
				if (rs.getDate(2) != null) {fecha = myformatfecha.format(rs.getDate(2));}
				aux.add(fecha);
				aux.add(rs.getString(3));
				aux.add(rs.getString(4));
				aux.add(rs.getString(5));
				aux.add(rs.getString(6));
				aux.add(myformatint.format(rs.getLong(7)));
				aux.add(rs.getString(8));
				if(rs.getDate(9)!=null){
					aux.add(myformatfecha.format(rs.getDate(9)));
				}else{
					aux.add("pendiente");
				}
				lista.add(aux);
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	
	
//	public static List<Redimensionar> allPorIdActaBaja (Connection con, String db, Long id_actaBaja) {
//		List<Redimensionar> lista = new ArrayList<Redimensionar>();
//		try {
//			PreparedStatement smt = con
//					.prepareStatement(" select " +
//							" id, id_actaBaja, id_equipo, cantidad, motivo, id_movimientoOrigen, id_movimiento, esModificable, fechaConfirma " +
//							" from `"+db+"`.baja where id_actaBaja = ?;");
//			smt.setLong(1, id_actaBaja);
//			ResultSet rs = smt.executeQuery();
//			while (rs.next()) {
//				Redimensionar aux = new Redimensionar(rs.getLong(1), rs.getLong(2), rs.getLong(3), rs.getDouble(4), rs.getString(5), rs.getLong(6), rs.getLong(7), rs.getLong(8), rs.getString(9));
//				lista.add(aux);
//			}
//			rs.close();
//			smt.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return(lista);
//	}
//	
//	public static Map<Long,Redimensionar> mapDetalleBajaModifica (Connection con, String db, Long id_actaBaja) {
//		Map<Long,Redimensionar> map = new HashMap<Long,Redimensionar>();
//		try {
//			PreparedStatement smt = con
//					.prepareStatement(" select " +
//							" id, id_actaBaja, id_equipo, cantidad, motivo, id_movimientoOrigen, id_movimiento, esModificable, fechaConfirma " +
//							" from `"+db+"`.baja where id_actaBaja = ? and esModificable=1 and fechaConfirma is null;");
//			smt.setLong(1, id_actaBaja);
//			ResultSet rs = smt.executeQuery();
//			while (rs.next()) {
//				Redimensionar aux = new Redimensionar(rs.getLong(1), rs.getLong(2), rs.getLong(3), rs.getDouble(4), rs.getString(5), rs.getLong(6), rs.getLong(7), rs.getLong(8), rs.getString(9));
//				map.put(rs.getLong(3), aux);
//			}
//			rs.close();
//			smt.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return(map);
//	}
//	
//	public static Map<Long,Redimensionar> mapDetalleBaja (Connection con, String db, Long id_actaBaja) {
//		Map<Long,Redimensionar> map = new HashMap<Long,Redimensionar>();
//		try {
//			PreparedStatement smt = con
//					.prepareStatement(" select " +
//							" id, id_actaBaja, id_equipo, cantidad, motivo, id_movimientoOrigen, id_movimiento, esModificable, fechaConfirma " +
//							" from `"+db+"`.baja where id_actaBaja = ?;");
//			smt.setLong(1, id_actaBaja);
//			ResultSet rs = smt.executeQuery();
//			while (rs.next()) {
//				Redimensionar aux = new Redimensionar(rs.getLong(1), rs.getLong(2), rs.getLong(3), rs.getDouble(4), rs.getString(5), rs.getLong(6), rs.getLong(7), rs.getLong(8), rs.getString(9));
//				map.put(rs.getLong(3), aux);
//			}
//			rs.close();
//			smt.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return(map);
//	}
	
	public static boolean deleteNoConfirmadas (Connection con, String db, Long id_actaBaja) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement(" delete from `"+db+"`.baja where id_actaBaja = ? and esModificable=1 and fechaConfirma is null;");
			smt.setLong(1, id_actaBaja);
			int rs = smt.executeUpdate();
			smt.close();
			if(rs>0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(flag);
	}
	
	public static List<List<String>> listParaConfirmar(Connection con, String db){
		List<List<String>> lista = new ArrayList<List<String>>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select baja.id, fecha, numero, codigo, equipo.nombre, unidad.nombre, cantidad, motivo, actaBajaPDF, equipo.id "
							+ "	from `"+db+"`.baja "
							+ "	left join `"+db+"`.actaBaja on actaBaja.id = baja.id_actaBaja "
							+ "	left join `"+db+"`.equipo on equipo.id = baja.id_equipo "
							+ "	left join `"+db+"`.unidad on unidad.id = equipo.id_unidad "
							+ "	where esModificable=1 and fechaConfirma is null order by equipo.nombre;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				List<String> aux = new ArrayList<String>();
				String fecha = null;		
				if (rs.getDate(2) != null) {fecha = myformatfecha.format(rs.getDate(2));}
				String cantidad = myformatdouble2.format(rs.getLong(7));
				aux.add(rs.getString(1));		// 0 id_baja
				aux.add(fecha);					// 1 fecha acta baja
				aux.add(rs.getString(3));		// 2 numero de acta
				aux.add(rs.getString(4));		// 3 codigo equipo
				aux.add(rs.getString(5));		// 4 equipo
				aux.add(rs.getString(6));		// 5 unidad
				aux.add(cantidad);				// 6 cantidad
				aux.add(rs.getString(8));		// 7 motivo
				aux.add(rs.getString(9));		// 8 doc adjunto
				aux.add(rs.getString(10));		// 9 id_equipo
				lista.add(aux);
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<List<String>> listaBajaPorEquipo(Connection con, String db){
		List<List<String>> lista = new ArrayList<List<String>>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select baja.id_equipo, grupo.nombre, equipo.codigo, equipo.nombre, unidad.nombre, sum(baja.cantidad) "
							+ " from `"+db+"`.baja "
							+ " left join `"+db+"`.equipo on equipo.id = baja.id_equipo "
							+ " left join `"+db+"`.grupo on grupo.id = equipo.id_grupo "
							+ " left join `"+db+"`.unidad on unidad.id = equipo.id_unidad "
							+ " group by baja.id_equipo order by grupo.nombre, equipo.nombre;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				List<String> aux = new ArrayList<String>();
				aux.add(rs.getString(1));							// 0 id_equipo
				aux.add(rs.getString(2));							// 1 grupo.nombre
				aux.add(rs.getString(3));							// 2 equipo.codigo
				aux.add(rs.getString(4));							// 3 equipo.nombre
				aux.add(rs.getString(5));							// 4 unidad.nombre
				aux.add(myformatdouble2.format(rs.getDouble(6)));	// 5 cantidad
				lista.add(aux);				
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static File listBajasPorEquipoExcel(String db, Map<String,String> mapDiccionario, List<List<String>> listaPorEquipo) {
		
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
            
            libro.setSheetName(0, "COMPRAS PR EQUIPO");
            Sheet hoja1 = libro.getSheetAt(0);
            
            Row row = null;
            Cell cell = null;
            
            row = hoja1.createRow(1);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("LISTADO DE COMPRAS POR COMPRA");
			
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
	        anchor.setCol1(5);
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
			cell.setCellValue("GRUPO");
		
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("CODIGO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			hoja1.setColumnWidth(3, 10*1000);
			cell.setCellValue("EQUIPO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			hoja1.setColumnWidth(4, 3*1000);
			cell.setCellValue("UN");
		        
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("CANT");
			
			
	        
			for(int i=0;i<listaPorEquipo.size();i++){
							
				posRow++;
				posCell = 0;
		        row = hoja1.createRow(posRow);
						
		        posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(listaPorEquipo.get(i).get(1));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(listaPorEquipo.get(i).get(2));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(listaPorEquipo.get(i).get(3));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(listaPorEquipo.get(i).get(4));
				
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            Double aux = Double.parseDouble(listaPorEquipo.get(i).get(5).replaceAll(",", ""));
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
