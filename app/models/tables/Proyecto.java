package models.tables;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import models.forms.FormProyectoGraba;
import models.utilities.Archivos;
import models.utilities.Fechas;



public class Proyecto {
	public Long id;
	public String nombre; //VARCHAR(100)
	public String nickName; //VARCHAR(20) not null
	public String direccion;  //VARCHAR(100)
	public String cod_region;
	public String cod_comuna;
	public String ciudad;
	public String region;
	public String comuna;
	

	public Proyecto(Long id, String nombre, String nickName, String direccion, String cod_region, String cod_comuna,
			String ciudad, String region, String comuna) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.nickName = nickName;
		this.direccion = direccion;
		this.cod_region = cod_region;
		this.cod_comuna = cod_comuna;
		this.ciudad = ciudad;
		this.region = region;
		this.comuna = comuna;
	}

	public Proyecto() {
		super();
	}
	
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	public String getNickName() {return nickName;}
	public void setNickName(String nickName) {this.nickName = nickName;}
	public String getDireccion() {return direccion;}
	public void setDireccion(String direccion) {this.direccion = direccion;}
	public String getCod_comuna() {return cod_comuna;}
	public void setCod_comuna(String cod_comuna) {this.cod_comuna = cod_comuna;}
	public String getComuna() {return comuna;}
	public void setComuna(String comuna) {this.comuna = comuna;}
	public String getCod_region() {return cod_region;}
	public void setCod_region(String cod_region) {this.cod_region = cod_region;}
	public String getRegion() {return region;}
	public void setRegion(String region) {this.region = region;}
	public String getCiudad() {return ciudad;}
	public void setCiudad(String ciudad) {this.ciudad = ciudad;}
	
	public static Map<Long,Proyecto> mapAllProyectos(Connection con, String db){
		Map<Long,Proyecto> map = new HashMap<Long,Proyecto>();
		List<Proyecto> listProyectos = Proyecto.all(con, db);
		listProyectos.forEach(x->{
			map.put(x.getId(), x);
		});
		return(map);
	}

	public static List<Proyecto> all(Connection con, String db) {
		List<Proyecto> lista = new ArrayList<Proyecto>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" proyecto.id, " +
							" proyecto.nombre, " +
							" proyecto.nickName, " +
							" proyecto.direccion, " +
							" proyecto.cod_region, " +
							" proyecto.cod_comuna, " +
							" proyecto.ciudad, " +
							" ifnull(regiones.nombre,'--'), " +
							" ifnull(comunas.nombre,'--') " +
							" from `"+db+"`.proyecto " +
							" left join `"+db+"`.regiones on regiones.codigo = proyecto.cod_region" +
							" left join `"+db+"`.comunas on comunas.codigo = proyecto.cod_comuna" +
							" order by proyecto.nickName;");
			ResultSet resultado = smt.executeQuery();
			while (resultado.next()) {		
				lista.add(new Proyecto(resultado.getLong(1),resultado.getString(2),resultado.getString(3),
						resultado.getString(4),resultado.getString(5),resultado.getString(6),resultado.getString(7),resultado.getString(8),resultado.getString(9)));
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (lista);
	}
	
	public static Proyecto find(Connection con, String db, Long id_proyecto) {
		Proyecto aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement(" select  " +
							" proyecto.id, " +
							" proyecto.nombre, " +
							" proyecto.nickName, " +
							" proyecto.direccion, " +
							" proyecto.cod_region, " +
							" proyecto.cod_comuna, " +
							" proyecto.ciudad, " +
							" ifnull(regiones.nombre,'--'), " +
							" ifnull(comunas.nombre,'--') " +
							" from `"+db+"`.proyecto " +
							" left join `"+db+"`.regiones on regiones.codigo = proyecto.cod_region" +
							" left join `"+db+"`.comunas on comunas.codigo = proyecto.cod_comuna" +
							" where proyecto.id = ?;" );
			smt.setLong(1, id_proyecto);
			ResultSet resultado = smt.executeQuery();
			if (resultado.next()) {				
				aux = new Proyecto(resultado.getLong(1),resultado.getString(2),resultado.getString(3),
						resultado.getString(4),resultado.getString(5),resultado.getString(6),resultado.getString(7),resultado.getString(8),resultado.getString(9));
			}else{
				aux = new Proyecto((long) 0,"sin Proyecto","sin Proyecto","","","","","","");
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (aux);
	}
	
	public static Proyecto findPorNickName(Connection con, String db, String nickName) {
		Proyecto aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement(" select  " +
							" proyecto.id, " +
							" proyecto.nombre, " +
							" proyecto.nickName, " +
							" proyecto.direccion, " +
							" proyecto.cod_region, " +
							" proyecto.cod_comuna, " +
							" proyecto.ciudad, " +
							" ifnull(regiones.nombre,'--'), " +
							" ifnull(comunas.nombre,'--') " +
							" from `"+db+"`.proyecto " +
							" left join `"+db+"`.regiones on regiones.codigo = proyecto.cod_region" +
							" left join `"+db+"`.comunas on comunas.codigo = proyecto.cod_comuna" +
							" where proyecto.nickName = ?;" );
			smt.setString(1, nickName);
			ResultSet resultado = smt.executeQuery();
			if (resultado.next()) {				
				aux = new Proyecto(resultado.getLong(1),resultado.getString(2),resultado.getString(3),
						resultado.getString(4),resultado.getString(5),resultado.getString(6),resultado.getString(7),resultado.getString(8),resultado.getString(9));
			}else{
				aux = new Proyecto((long) 0,"","","","","","","","");
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (aux);
	}
	
	public static boolean existeNickName(Connection con, String db, String nickName) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement(" select id from `"+db+"`.proyecto where upper(nickName) = ?;" );
			smt.setString(1, nickName.toUpperCase());
			ResultSet resultado = smt.executeQuery();
			if (resultado.next()) {	
				flag = true;
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean modificaPorCampo(Connection con,String db,String campo,Long id_proyecto,String valor) {
		boolean flag = false;
		try {
			PreparedStatement smt = con.prepareStatement("UPDATE `"+db+"`.proyecto set `" + campo + "` = ? WHERE id = ?;");		
			smt.setString(1, valor.trim());
			smt.setLong(2, id_proyecto);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
				e.printStackTrace();
			flag=false;
		}
		return (flag);
	}
	
	public static boolean estaEnUso(Connection con, String db, Long id_proyecto) {
		boolean flag = false;
		try {
			PreparedStatement smt2 = con
					.prepareStatement("select * from `"+db+"`.bodegaEmpresa WHERE id_proyecto = ?");
			smt2.setLong(1, id_proyecto);
			ResultSet resultado2 = smt2.executeQuery();
			if (resultado2.next()) {
				flag = true;
			}
			resultado2.close();
			smt2.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean delete(Connection con, String db, Long id_proyecto) {
		boolean flag = false;
		try {
			PreparedStatement smt3 = con
					.prepareStatement("DELETE FROM `"+db+"`.contactoProyecto WHERE id_proyecto = ?");
			smt3.setLong(1, id_proyecto);
			smt3.executeUpdate();
			smt3.close();
			PreparedStatement smt4 = con
					.prepareStatement("DELETE FROM `"+db+"`.proyecto WHERE id = ?");
			smt4.setLong(1, id_proyecto);
			smt4.executeUpdate();
			smt4.close();
			flag = true;
		} catch (SQLException e) {
    			e.printStackTrace();
			flag=false;
		}
		return (flag);
	}
	
	public static boolean create(Connection con,String db, FormProyectoGraba aux) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("INSERT INTO `"+db+"`.proyecto (nombre,nickName,direccion,cod_region,cod_comuna,ciudad) " +
							" VALUES (?,?,?,?,?,?)");
			smt.setString(1, aux.nombre.trim());
			smt.setString(2, aux.nickName.trim());
			smt.setString(3, aux.direccion.trim());
			smt.setString(4, aux.cod_region.trim());
			smt.setString(5, aux.cod_comuna.trim());
			smt.setString(6, aux.ciudad.trim());
			smt.executeUpdate();
			smt.close();
			flag=true;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	
	public static File exportaAllProyectosExcel(String db, Map<String,String> mapDiccionario, List<Proyecto> listProyectos) {
		
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
            
            libro.setSheetName(0, "LISTADO PROYECTOS");
            Sheet hoja1 = libro.getSheetAt(0);
            
            Row row = null;
            Cell cell = null;
            
            row = hoja1.createRow(1);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("LISTADO DE PROYECTOS");
			
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
	        anchor.setCol1(9);
	        anchor.setRow1(1);
			Picture img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);
			
			
			// encabezado de la tabla
			
			int posRow = 8;
			int posCell = 0;
			
			row = hoja1.createRow(posRow);
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("NOMBRE LARGO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("NOMBRE CORTO");
		
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("DIRECCION");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("REGION");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("COMUNA");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("CIUDAD");
	        
			for(Proyecto p:listProyectos){
				posRow++;
				posCell = 0;
		        row = hoja1.createRow(posRow);
						
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(p.getNombre());
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(p.getNickName());
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(p.getDireccion());
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(p.getRegion());
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(p.getComuna());
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(p.getCiudad());
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
