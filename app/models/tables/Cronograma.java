package models.tables;

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



public class Cronograma{
	public Long id_movimiento;
	public Long id_bodegaEmpresa;
	public Long id_equipo;
	public String fechaActualizacion;
	public String fechaDisponible;
	
	public String fechaDesde;
	public String nombreBodega;
	public String nombreGrupo;
	public String codEquipo;
	public String nombreEquipo;
	public String unidad;
	public String cantidad;
	
	public String nameSucursal;


	public Cronograma(Long id_movimiento, Long id_bodegaEmpresa, Long id_equipo, String fechaActualizacion,
			String fechaDisponible, String fechaDesde, String nombreBodega, String nombreGrupo, String codEquipo,
			String nombreEquipo, String unidad, String cantidad, String nameSucursal) {
		super();
		this.id_movimiento = id_movimiento;
		this.id_bodegaEmpresa = id_bodegaEmpresa;
		this.id_equipo = id_equipo;
		this.fechaActualizacion = fechaActualizacion;
		this.fechaDisponible = fechaDisponible;
		this.fechaDesde = fechaDesde;
		this.nombreBodega = nombreBodega;
		this.nombreGrupo = nombreGrupo;
		this.codEquipo = codEquipo;
		this.nombreEquipo = nombreEquipo;
		this.unidad = unidad;
		this.cantidad = cantidad;
		this.nameSucursal = nameSucursal;
		
	}

	public Cronograma() {
		super();
	}

	static DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00",symbols);
	
	public static List<Cronograma> all(Connection con, String db, boolean esPorSucursal, String id_sucursal) {
		List<Cronograma> lista = new ArrayList<Cronograma>();
		String condSucursal = "";
		if(esPorSucursal) {
			condSucursal = " and bodegaEmpresa.id_sucursal = "+id_sucursal+" ";
		}
		try {
			
			PreparedStatement smt2 = con
					.prepareStatement("select "
							+ " bodegaEmpresa.nombre as bodega, "
							+ " '---' as grupo, "
							+ " '---' as codEquipo, "
							+ " '---' as equipo, "
							+ " '1' as cantidad, "
							+ " date(bodegaEmpresa.dateCreate) as fechaDesde, "
							+ " bodegaEmpresa.id as idBodega, "
							+ " '0' as idGrupo, "
							+ " '0' as idEquipo, "
							+ " bodegaEmpresa.esInterna as esInterna, "
							+ " '0' as idMovimiento, "
							+ " 'GL' as nombre, "
							+ " bodegaEmpresa.id as id, "
							+ " cronograma.id_equipo as id_equipo, "
							+ " ifnull(cronograma.fechaActualizacion,'') as fechaActualizado, "
							+ " ifnull(cronograma.fechaDisponible,'No Informada') as fechaEstimada, "
							+ " bodegaEmpresa.id_sucursal "
							+ " from `"+db+"`.cronograma "
							+ " left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = cronograma.id_bodegaEmpresa "
							+ " where cronograma.id_movimiento=0 and cronograma.id_equipo=0 and cronograma.id_bodegaEmpresa>0 and bodegaEmpresa.vigente = 1"+condSucursal
							+ ";");
			ResultSet rs2 = smt2.executeQuery();
			
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);

			while (rs2.next()) {
				
				String nameSucursal = "";
				Sucursal sucursal = mapSucursal.get(rs2.getLong(17));
				if(sucursal!=null) {
					nameSucursal = sucursal.getNombre();
				}
				
				String auxFechaActualizada = rs2.getString(15);
				if(!auxFechaActualizada.equals("")) {
					auxFechaActualizada = Fechas.DDMMAA(rs2.getString(15));
				}
				
				String auxFechaDesde = rs2.getString(6);
				if(!auxFechaDesde.equals("")) {
					auxFechaDesde = Fechas.DDMMAA(rs2.getString(6));
				}
				
				String auxFechaEstimada = rs2.getString(16);
				if(!auxFechaEstimada.equals("")) {
					auxFechaEstimada = Fechas.DDMMAA(rs2.getString(16));
				}
				
				lista.add(new Cronograma(rs2.getLong(11),rs2.getLong(13),rs2.getLong(9),auxFechaActualizada,
						auxFechaEstimada,auxFechaDesde,rs2.getString(1),rs2.getString(2),
						rs2.getString(3),rs2.getString(4),rs2.getString(12),rs2.getString(5),nameSucursal));
			}
			
			
			PreparedStatement smt = con
					.prepareStatement(" select " +
					/*1*/	" bodegaEmpresa.nombre as bodega, " +
					/*2*/	" grupo.nombre as grupo, " +
					/*3*/	" equipo.codigo as codEquipo, " +
					/*4*/	" equipo.nombre as equipo, " +
					/*5*/	" sum(if(movimiento.id_tipoMovimiento=2,-1,1)*movimiento.cantidad) as cantidad, " +
					/*6*/	" DATE_FORMAT(guia.fecha,'%d-%m-%Y') as fechaDesde, " +
					/*7*/	" movimiento.id_bodegaEmpresa as idBodega, " +
					/*8*/	" grupo.id as idGrupo, " +
					/*9*/	" movimiento.id_equipo as idEquipo, " +
					/*10*/	" bodegaEmpresa.esInterna, " +
					/*11*/	" max(movimiento.id) as idMovimiento, " +
					/*12*/	" unidad.nombre, " +
					/*13*/  " bodegaEmpresa.id ," +
					/*14*/  " cronograma.id_equipo, "+
							" bodegaEmpresa.id_sucursal " +
							" from `"+db+"`.movimiento " +
							" left join `"+db+"`.equipo on equipo.id = movimiento.id_equipo " +
							" left join `"+db+"`.grupo on grupo.id = equipo.id_grupo " +
							" left join `"+db+"`.guia on guia.id = movimiento.id_guia " +
							" left join `"+db+"`.unidad on unidad.id = equipo.id_unidad " +
							" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa " +
							" left join (select max(id_movimiento),id_equipo from `"+db+"`.cronograma group by id_equipo) as cronograma on cronograma.id_equipo = movimiento.id_equipo " +
							" where (bodegaEmpresa.vigente = 1 or bodegaEmpresa.vigente = 0) " +
							" and (guia.fecha is null or guia.fecha<='2200-01-01') " +
							" and bodegaEmpresa.id >1 and bodegaEmpresa.esInterna>1 " +condSucursal+
							" group by movimiento.id_bodegaEmpresa,movimiento.id_equipo " +
							" having cantidad > 0 and cronograma.id_equipo is not null " +
							" order by bodegaEmpresa.nombre;");
			ResultSet rs = smt.executeQuery();
			Map<String,List<String>> mapDisponible = Cronograma.mapFechasDisponibles(con, db,(long)0);
			
			while (rs.next()) {
				
				String nameSucursal = "";
				Sucursal sucursal = mapSucursal.get(rs.getLong(15));
				if(sucursal!=null) {
					nameSucursal = sucursal.getNombre();
				}
				
				String fechaActualizado = "";
				String fechaEstimada = "No Informada";
				try{
					fechaActualizado = mapDisponible.get(rs.getLong(11)+"_"+rs.getLong(13)).get(0);
				}catch (Exception e) {}
				try{
					fechaEstimada = mapDisponible.get(rs.getLong(11)+"_"+rs.getLong(13)).get(1);
				}catch (Exception e) {}
				
				String cantidad = myformatdouble2.format(rs.getDouble(5));
				
				if(fechaActualizado == null || fechaActualizado == "") {
					fechaActualizado="";
				}
				if(fechaEstimada == null || fechaEstimada == "") {
					fechaEstimada="No Informada";
				}
				lista.add(new Cronograma(rs.getLong(11),rs.getLong(13),rs.getLong(9),fechaActualizado,
						fechaEstimada,rs.getString(6),rs.getString(1),rs.getString(2),
						rs.getString(3),rs.getString(4),rs.getString(12),cantidad,nameSucursal));				
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<Cronograma> allEquiposInscritos(Connection con, String db) {
		List<Cronograma> lista = new ArrayList<Cronograma>();
		try {
			
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" grupo.nombre as grupo, " +
							" equipo.codigo as codEquipo, " +
							" equipo.nombre as equipo, " +
							" equipo.id as idEquipo "+
							" from `"+db+"`.equipo " +
							" left join `"+db+"`.grupo on grupo.id = equipo.id_grupo " +
							" left join `"+db+"`.cronograma on cronograma.id_equipo = equipo.id " +
							" where cronograma.id_equipo is not null " +
							" group by cronograma.id_equipo " +
							" order by equipo.nombre;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				lista.add(new Cronograma((long)0,(long)0,rs.getLong(4),"","","","",rs.getString(1),
						rs.getString(2),rs.getString(3),"","0",""));				
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<Cronograma> allBodegasInscritas(Connection con, String db, boolean esPorSucursal, String id_sucursal) {
		List<Cronograma> lista = new ArrayList<Cronograma>();
		String condSucursal = "";
		if(esPorSucursal) {
			condSucursal = " and bodegaEmpresa.id_sucursal = "+id_sucursal+" ";
		}
		try {
			
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" bodegaEmpresa.id, " +
							" bodegaEmpresa.nombre, " +
							" bodegaEmpresa.id_sucursal " +
							" from `"+db+"`.bodegaEmpresa " +
							" left join `"+db+"`.cronograma on cronograma.id_bodegaEmpresa = bodegaEmpresa.id " +
							" where cronograma.id_bodegaEmpresa is not null and bodegaEmpresa.vigente = 1 " +condSucursal+
							" group by cronograma.id_bodegaEmpresa " +
							" order by bodegaEmpresa.nombre;");
			ResultSet rs = smt.executeQuery();
			Map<Long, Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			while (rs.next()) {
				String nameSucursal = "";
				Sucursal sucursal = mapSucursal.get(rs.getLong(3));
				if(sucursal!=null) {
					nameSucursal = sucursal.getNombre();
				}
				lista.add(new Cronograma((long)0,rs.getLong(1),(long)0,"","","",rs.getString(2),"",
						"","","","0",nameSucursal));				
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	
	public static File reportDisponibilidadAllExcel(String db, Map<String,String> mapDiccionario, List<Cronograma> listaDisponibilidad) {
		
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
			cell.setCellValue("DISPONIBILIDAD DE EQUIPOS EN "+mapDiccionario.get("BODEGA")+"S/PROYECTOS");
			
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
			row = hoja1.createRow(8);
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("SUCURSAL");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 7*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(mapDiccionario.get("BODEGA")+"/PROYECTO");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("GRUPO/FAMILIA");
			
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
			hoja1.setColumnWidth(posColl, 3*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("UNIDAD");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("CANTIDAD");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("EN PROYECTO DESDE");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("ULTIMA MODIFICACION");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("DISPONIBLE A PARTIR DE");
		
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
			int posRow = 9;
			for(int i=0;i<listaDisponibilidad.size();i++){
				row = hoja1.createRow(posRow);
				posCell = 0;
				posColl = 0;
				Double aux = (double)0;
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(listaDisponibilidad.get(i).nameSucursal);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(listaDisponibilidad.get(i).nombreBodega);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(listaDisponibilidad.get(i).nombreGrupo);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(listaDisponibilidad.get(i).codEquipo);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(listaDisponibilidad.get(i).nombreEquipo);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(listaDisponibilidad.get(i).unidad);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(listaDisponibilidad.get(i).cantidad.replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalleFecha);
				cell.setCellValue(Fechas.obtenerFechaDesdeStrDDMMAA(listaDisponibilidad.get(i).fechaDesde).getFechaUtil());
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalleFecha);
				cell.setCellValue(Fechas.obtenerFechaDesdeStrDDMMAA(listaDisponibilidad.get(i).fechaActualizacion).getFechaUtil());
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalleFecha);
				cell.setCellValue(Fechas.obtenerFechaDesdeStrDDMMAA(listaDisponibilidad.get(i).fechaDisponible).getFechaUtil());
				
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
	
	public static Map<String,List<String>> mapFechasDisponibles(Connection con, String db, Long idBodega) {
		Map<String,List<String>> mapDisponible = new HashMap<String,List<String>>();
		String filtro="";
		if(idBodega>0) filtro=" and id_bodegaEmpresa='"+idBodega.toString()+"'";
		try {
			PreparedStatement smt = con
					.prepareStatement(" select id_movimiento, id_bodegaEmpresa, " +
							" DATE_FORMAT(cronograma.fechaActualizacion,'%d-%m-%Y')	as fechaActualizado,  " +
							" DATE_FORMAT(cronograma.fechaDisponible,'%d-%m-%Y') as fechaEstimada " +
							" from `"+db+"`.cronograma where fechaDisponible is not null "+filtro+";"); 
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				List<String> aux = new ArrayList<String>();
				aux.add(rs.getString(3));
				aux.add(rs.getString(4));
				mapDisponible.put(rs.getLong(1)+"_"+rs.getLong(2), aux);
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(mapDisponible);
	}

	public static String actualizaFecha(Connection con, String db, Long id_movimiento, Long id_bodegaEmpresa, Long id_equipo, String fecha) {
		Fechas hoy = Fechas.hoy();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select * from `"+db+"`.cronograma where id_movimiento=? and id_bodegaEmpresa=? and id_equipo=?;");
			smt.setLong(1, id_movimiento);
			smt.setLong(2, id_bodegaEmpresa);
			smt.setLong(3, id_equipo);
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				PreparedStatement smt1 = con
						.prepareStatement("update `"+db+"`.cronograma set fechaActualizacion=?,fechaDisponible=? where id_movimiento=? and id_bodegaEmpresa=? and id_equipo=?;");
				smt1.setDate(1, hoy.getFechaSql());
				smt1.setString(2, fecha.trim());
				smt1.setLong(3, id_movimiento);
				smt1.setLong(4, id_bodegaEmpresa);
				smt1.setLong(5, id_equipo);
				smt1.executeUpdate();
				smt1.close();
			}else {
				PreparedStatement smt1 = con
						.prepareStatement("insert into `"+db+"`.cronograma (id_movimiento,id_bodegaEmpresa,id_equipo,fechaActualizacion,fechaDisponible) values (?,?,?,?,?);");
				smt1.setLong(1, id_movimiento);
				smt1.setLong(2, id_bodegaEmpresa);
				smt1.setLong(3, id_equipo);
				smt1.setDate(4, hoy.getFechaSql());
				smt1.setString(5, fecha.trim());
				smt1.executeUpdate();
				smt1.close();
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (hoy.getFechaStrDDMMAA());
	}
	
	public static String cartaGanttAll(Connection con, String db, List<Cronograma> lista) {
		String datos = "";
		for(int i=0;i<lista.size();i++) {
			String equipo = lista.get(i).codEquipo+" - "+lista.get(i).nombreEquipo;
			String bodega = lista.get(i).nombreBodega;
			String nivel1 = "";
			if(i<10-1) {
				nivel1 = "100"+(i+1);
			}else if(i<100-1) {
				nivel1 = "10"+(i+1);
			}else {
				nivel1 = "1"+(i+1);
			}
			String nivel11 = nivel1+"1";
			String nivel12 = nivel1+"2";
			String nivel13 = nivel1+"3";
			String despacho = lista.get(i).fechaDesde.substring(6)+"-"+lista.get(i).fechaDesde.substring(3,5)+"-"+lista.get(i).fechaDesde.substring(0,2);
			String actualizado = "";
			
			
			
			
			if(lista.get(i).fechaActualizacion.length()==10) {
				actualizado=lista.get(i).fechaActualizacion.substring(6)+"-"+lista.get(i).fechaActualizacion.substring(3,5)+"-"+lista.get(i).fechaActualizacion.substring(0,2);
			}
			String disponible = ""; 
			if(lista.get(i).fechaDisponible.length()==10) {
				disponible=lista.get(i).fechaDisponible.substring(6)+"-"+lista.get(i).fechaDisponible.substring(3,5)+"-"+lista.get(i).fechaDisponible.substring(0,2);
			}
			
			String aux = "g.AddTaskItem(new JSGantt.TaskItem("+nivel1+",'"+equipo+"','','','ggroupblack','',0,'"+bodega+"',0,2,0,1,'','','',g));"+
						 "g.AddTaskItem(new JSGantt.TaskItem("+nivel11+",'Despacho','"+despacho+"','"+despacho+"','gtaskgreen','',0,'',0,0,"+nivel1+",1,'','','',g));";
			if(!actualizado.equals("")) {
				aux = aux + "g.AddTaskItem(new JSGantt.TaskItem("+nivel12+",'Actualizado','"+actualizado+"','"+actualizado+"','gtaskblue','',0,'',0,0,"+nivel1+",1,'"+nivel11+"','','',g));";
			}
			if(!disponible.equals("")) {
				aux = aux + "g.AddTaskItem(new JSGantt.TaskItem("+nivel13+",'Devolucion','"+disponible+"','"+disponible+"','gtaskred','',0,'',0,0,"+nivel1+",1,'"+nivel12+"','','',g));";
			}
			datos = datos + aux;
		}
		return(datos);
	}
	
	public static List<Cronograma> allNOInscritosEquipo(Connection con, String db) {
		List<Cronograma> lista = new ArrayList<Cronograma>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select equipo.id,grupo.nombre,equipo.codigo,equipo.nombre " +
							" from `"+db+"`.equipo " + 
							" left join `"+db+"`.cronograma on cronograma.id_equipo=equipo.id " + 
							" left join `"+db+"`.grupo on grupo.id = equipo.id_grupo " + 
							" where equipo.vigente = 1 and cronograma.id_equipo is null " + 
							" group by equipo.id order by grupo.nombre,equipo.nombre;"); 
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				lista.add(new Cronograma((long)0,(long)0,rs.getLong(1),"",
						"","","",rs.getString(2),
						rs.getString(3),rs.getString(4),"","",""));				
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<Cronograma> allNOInscritosBodega(Connection con, String db, boolean esPorSucursal, String id_sucursal) {
		List<Cronograma> lista = new ArrayList<Cronograma>();
		String condSucursal = "";
		if(esPorSucursal) {
			condSucursal = " and bodegaEmpresa.id_sucursal = "+id_sucursal+" ";
		}
		try {
			PreparedStatement smt = con
					.prepareStatement("select bodegaEmpresa.id, bodegaEmpresa.nombre, bodegaEmpresa.id_sucursal "
							+ " from `"+db+"`.bodegaEmpresa "
							+ " left join `"+db+"`.cronograma on cronograma.id_bodegaEmpresa = bodegaEmpresa.id "
							+ " where cronograma.id_bodegaEmpresa is null and esInterna = 2 and vigente = 1 " + condSucursal 
							+ " group by bodegaEmpresa.id order by bodegaEmpresa.nombre;"); 
			ResultSet rs = smt.executeQuery();
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			while (rs.next()) {
				String nameSucursal = "";
				Sucursal sucursal = mapSucursal.get(rs.getLong(3));
				if(sucursal!=null) {
					nameSucursal = sucursal.getNombre();
				}
				lista.add(new Cronograma((long)0,rs.getLong(1),(long)0,"","","",rs.getString(2),"","","","","",nameSucursal));				
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	
	public static boolean addEquipo(Connection con, String db, Long id_equipo) {
		boolean flag = false;
		Fechas hoy = Fechas.hoy();
		try {
			PreparedStatement smt = con
					.prepareStatement("insert into `"+db+"`.cronograma (id_equipo, fechaActualizacion) values (?,?);"); 
			smt.setLong(1,id_equipo);
			smt.setDate(2, hoy.getFechaSql());
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean addBodega(Connection con, String db, Long id_bodegaEmpresa) {
		boolean flag = false;
		Fechas hoy = Fechas.hoy();
		try {
			PreparedStatement smt = con
					.prepareStatement("insert into `"+db+"`.cronograma (id_bodegaEmpresa, fechaActualizacion) values (?,?);"); 
			smt.setLong(1,id_bodegaEmpresa);
			smt.setDate(2, hoy.getFechaSql());
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}

	
	public static List<Cronograma> allInscritos(Connection con, String db) {
		List<Cronograma> lista = new ArrayList<Cronograma>();
		try {
			
			PreparedStatement smt = con
					.prepareStatement("select equipo.id,grupo.nombre,equipo.codigo,equipo.nombre " +
							" from `"+db+"`.cronograma " + 
							" left join `"+db+"`.equipo on equipo.id=cronograma.id_equipo " + 
							" left join `"+db+"`.grupo on grupo.id=equipo.id_grupo " + 
							" where cronograma.id_equipo > 0 " +
							" group by id_equipo order by grupo.nombre, equipo.nombre;"); 
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				lista.add(new Cronograma((long)0,(long)0,rs.getLong(1),"",
						"","","",rs.getString(2),
						rs.getString(3),rs.getString(4),"","",""));				
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	

	public static boolean dropEquipo(Connection con, String db,Long idEquipo) {
		boolean flag=true;
		try {
			PreparedStatement smt = con
					.prepareStatement("delete from `"+db+"`.cronograma where id_equipo=?;"); 
			smt.setLong(1,idEquipo);
			smt.executeUpdate();
			smt.close();
		} catch (SQLException e) {
			flag=false;
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean dropBodega(Connection con, String db,Long idBodega) {
		boolean flag=true;
		try {
			PreparedStatement smt = con
					.prepareStatement("delete from `"+db+"`.cronograma where id_bodegaEmpresa=?;"); 
			smt.setLong(1,idBodega);
			smt.executeUpdate();
			smt.close();
		} catch (SQLException e) {
			flag=false;
			e.printStackTrace();
		}
		return (flag);
	}
	

	

}





