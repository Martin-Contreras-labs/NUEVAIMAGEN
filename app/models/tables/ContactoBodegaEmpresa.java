package models.tables;

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

import models.forms.FormContactoBodegaGraba;
import models.utilities.Archivos;
import models.utilities.Fechas;


public class ContactoBodegaEmpresa {
	public Long id;
	public Long id_bodegaEmpresa;
	public String nombre;
	public String cargo;
	public String movil;
	public String fijo;
	public String mail;
	
	public ContactoBodegaEmpresa(Long id, Long id_bodegaEmpresa, String nombre,
			String cargo, String movil, String fijo, String mail) {
		super();
		this.id = id;
		this.id_bodegaEmpresa = id_bodegaEmpresa;
		this.nombre = nombre;
		this.cargo = cargo;
		this.movil = movil;
		this.fijo = fijo;
		this.mail = mail;
	}

	public ContactoBodegaEmpresa() {
		super();
	}

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	public String getCargo() {return cargo;}
	public void setCargo(String cargo) {this.cargo = cargo;}
	public String getMovil() {return movil;}
	public void setMovil(String movil) {this.movil = movil;}
	public String getFijo() {return fijo;}
	public void setFijo(String fijo) {this.fijo = fijo;}
	public String getMail() {return mail;}
	public void setMail(String mail) {this.mail = mail;}
	public Long getId_bodegaEmpresa() {return id_bodegaEmpresa;}
	public void setId_bodegaEmpresa(Long id_bodegaEmpresa) {this.id_bodegaEmpresa = id_bodegaEmpresa;}
	
	public static List<List<String>> all(Connection con, String db, String esPorSucursal, String id_sucursal) {
		List<List<String>> lista = new ArrayList<List<String>>();
		
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " where bodegaEmpresa.id_sucursal = " + id_sucursal;
		}
		
		try {
			PreparedStatement smt = con
					.prepareStatement(" select "
							+ "bodegaEmpresa.nombre, "
							+ "contactoBodegaEmpresa.nombre, "
							+ "contactoBodegaEmpresa.cargo, "
							+ "contactoBodegaEmpresa.movil, "
							+ "contactoBodegaEmpresa.fijo, "
							+ "contactoBodegaEmpresa.mail, "
							+ "ifnull(bodegaEmpresa.comercial,''), "
							+ "bodegaEmpresa.id_sucursal, "
							+ "bodegaEmpresa.id_comercial, "
							+ "bodegaEmpresa.id_cliente "
							+ "from `"+db+"`.contactoBodegaEmpresa "
							+ "left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = contactoBodegaEmpresa.id_bodegaEmpresa "
							+ condSucursal
							+ " order by bodegaEmpresa.nombre, contactoBodegaEmpresa.nombre;");
			ResultSet rs = smt.executeQuery();
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
			Map<Long, Cliente> mapCliente = Cliente.mapAllClientes(con, db);
			while (rs.next()) {
				String nameSucursal = "";
				Sucursal sucursal = mapSucursal.get(rs.getLong(8));
				if(sucursal!=null) {
					nameSucursal = sucursal.getNombre();
				}
				String nameComercial = "";
				Comercial comercial = mapComercial.get(rs.getLong(9));
				if(comercial!=null) {
					nameComercial = comercial.getNameUsuario();
				}else {
					nameComercial = rs.getString(7);
				}
				Cliente cliente = mapCliente.get(rs.getLong(10));
				String nombreCliente = "";
				if(cliente != null) {
					nombreCliente = cliente.getNickName();
				}
				
				List<String> aux = new ArrayList<String>();
				aux.add(rs.getString(1));
				aux.add(rs.getString(2));
				aux.add(rs.getString(3));
				aux.add(rs.getString(4));
				aux.add(rs.getString(5));
				aux.add(rs.getString(6));
				aux.add(nameSucursal);
				aux.add(nameComercial);
				aux.add(nombreCliente);
				lista.add(aux);
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    return (lista);
	}
	
	public static List<ContactoBodegaEmpresa> allxBodega(Connection con, String db, Long id_bodegaEmpresa) {
		List<ContactoBodegaEmpresa> lista = new ArrayList<ContactoBodegaEmpresa>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" id,  id_bodegaEmpresa, nombre, cargo, movil,  fijo, mail " +
							" from `"+db+"`.contactoBodegaEmpresa " +
							" where id_bodegaEmpresa = ?;");
			smt.setLong(1, id_bodegaEmpresa);
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				lista.add(new ContactoBodegaEmpresa(rs.getLong(1),rs.getLong(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    return (lista);
	}
	
	public static ContactoBodegaEmpresa find(Connection con, String db, Long id_contactoBodega) {
		ContactoBodegaEmpresa aux = new ContactoBodegaEmpresa();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" id, id_bodegaEmpresa, nombre, cargo, movil, fijo, mail " +
							" from `"+db+"`.contactoBodegaEmpresa " +
							" where id = ?;");
			smt.setLong(1, id_contactoBodega);
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				aux = new ContactoBodegaEmpresa(rs.getLong(1),rs.getLong(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}
	
	public static boolean create(Connection con, String db, FormContactoBodegaGraba aux) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("insert into `"+db+"`.contactoBodegaEmpresa (id_bodegaEmpresa,nombre,cargo,fijo,movil,mail) " +
							" values (?,?,?,?,?,?)");
			smt.setLong(1, aux.id_bodega);
			smt.setString(2, aux.nombre.trim());
			smt.setString(3, aux.cargo.trim());
			smt.setString(4, aux.fijo.trim());
			smt.setString(5, aux.movil.trim());
			smt.setString(6, aux.mail.trim());
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean delete(Connection con, String db, Long id_contactoBodegaEmpresa) {
		boolean flag = false;
		try {
			PreparedStatement smt1 = con
						.prepareStatement("delete from `"+db+"`.contactoBodegaEmpresa WHERE id = ?");
				smt1.setLong(1, id_contactoBodegaEmpresa);
				smt1.executeUpdate();
				smt1.close();
				flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean modificaPorCampo(Connection con, String db, String campo, Long id_contacto, String valor) {
		boolean flag = false;
		try {
			PreparedStatement smt = con.prepareStatement("update `"+db+"`.contactoBodegaEmpresa set `" + campo + "` = ? WHERE id = ?;");	
			smt.setString(1, valor.trim());
			smt.setLong(2, id_contacto);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static String contactos(Connection con, String db, Long id_bodegaEmpresa) {
		String vistaHtml = "<table class='table table-sm table-hover table-bordered table-condensed table-fluid'>"+
				"<thead style='background-color: #eeeeee'><TR>" +
		        "<TH width='20%' style= 'text-align: center;'>BODEGA/EMPRESA</TH>" +
				"<TH width='20%' style= 'text-align: center;'>NOMBRE</TH>" +
				"<TH width='15%' style= 'text-align: center;'>CARGO</TH>" +
				"<TH width='15%' style= 'text-align: center;'>MOVIL<br>Compra</TH>" +
				"<TH width='15%' style= 'text-align: center;'>FIJO</TH>" +
				"<TH width='15%' style= 'text-align: center;'>eMAIL</TH>" +
				"</TR></thead><tbody>";
			try {
				PreparedStatement smt = con
						.prepareStatement(" select  " +
								" contactoBodegaEmpresa.id_bodegaEmpresa ,   " +
								" bodegaEmpresa.nombre,   " +
								" contactoBodegaEmpresa.nombre,   " +
								" contactoBodegaEmpresa.cargo,   " +
								" contactoBodegaEmpresa.movil,   " +
								" contactoBodegaEmpresa.fijo,   " +
								" contactoBodegaEmpresa.mail   " +
								" from `"+db+"`.contactoBodegaEmpresa   " +
								" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = contactoBodegaEmpresa.id_bodegaEmpresa  " + 
								" where contactoBodegaEmpresa.id_bodegaEmpresa = ?;");
				smt.setLong(1, id_bodegaEmpresa);
				ResultSet rs = smt.executeQuery();
				while (rs.next()) {
					vistaHtml = vistaHtml + "<TR>" +
							"<td style= 'text-align: left;'>"+rs.getString(2)+"</td>"+
							"<td style= 'text-align: left;'>"+rs.getString(3)+"</td>"+
							"<td style= 'text-align: left;'>"+rs.getString(4)+"</td>"+
							"<td style= 'text-align: left;'>"+rs.getString(5)+"</td>"+
							"<td style= 'text-align: left;'>"+rs.getString(6)+"</td>"+
							"<td style= 'text-align: left;'>"+rs.getString(7)+"</td></TR>";
				}
				vistaHtml = vistaHtml + "<TR>" + "</tbody></table>";
				rs.close();
				smt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        return (vistaHtml);
    }

	public static File contactoBodegasExcel(String db, List<List<String>> lista, Map<String,String> mapDiccionario) {
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
            
            //titulos del archivo
            
            libro.setSheetName(0, "LISTADO");
            Sheet hoja1 = libro.getSheetAt(0);
            
            Row row = null;
            Cell cell = null;
            
            row = hoja1.createRow(1);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("LISTADO DE CONTACTOS BODEGAS");
			
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
			for(int i=1; i<6; i++) {
				hoja1.setColumnWidth(i, 5*1000);
			}
			hoja1.setColumnWidth(6, 10*1000);
			hoja1.setColumnWidth(7, 10*1000);
            
			//INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
			InputStream x = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
            byte[] bytes = IOUtils.toByteArray(x);
            x.close();
            int pngIndex = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			Drawing draw = hoja1.createDrawingPatriarch();
			CreationHelper helper = libro.getCreationHelper();
			ClientAnchor anchor = helper.createClientAnchor();
	        //set top-left corner for the image
	        anchor.setCol1(7);
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
			cell.setCellValue("CONTACTO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("CARGO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("MOVIL");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FIJO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("MAIL");
			
			for(int i=0;i<lista.size();i++){
				
				posRow++;
				posCell = 0;
		        row = hoja1.createRow(posRow);
					
		        posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(6));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(0));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(8));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(1));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(2));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(3));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(4));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(5));
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
