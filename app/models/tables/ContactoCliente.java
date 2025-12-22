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
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.util.TempFile;

import models.forms.FormContactoClienteGraba;
import models.utilities.Archivos;
import models.utilities.Fechas;


public class ContactoCliente {

	public Long id;
	public Long id_cliente;
	public String nombre;
	public String cargo;
	public String movil;
	public String fijo;
	public String mail;
	
	public ContactoCliente(Long id, Long id_cliente, String nombre,
			String cargo, String movil, String fijo, String mail) {
		super();
		this.id = id;
		this.id_cliente = id_cliente;
		this.nombre = nombre;
		this.cargo = cargo;
		this.movil = movil;
		this.fijo = fijo;
		this.mail = mail;
	}

	public ContactoCliente() {
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
	public Long getId_cliente() {return id_cliente;}
	public void setId_cliente(Long id_cliente) {this.id_cliente = id_cliente;}

	
	public static List<ContactoCliente> allxCliente(Connection con, String db, Long id_cliente) {
		List<ContactoCliente> lista = new ArrayList<ContactoCliente>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" contactoCliente.id, " +
							" contactoCliente.id_cliente, " +
							" contactoCliente.nombre, " +
							" contactoCliente.cargo, " +
							" contactoCliente.movil, " +
							" contactoCliente.fijo, " +
							" contactoCliente.mail " +
							" from `"+db+"`.contactoCliente " +
							" where contactoCliente.id_cliente = ?;");
			smt.setLong(1, id_cliente);
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				lista.add(new ContactoCliente(rs.getLong(1),rs.getLong(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    return (lista);
	}
	
	public static ContactoCliente find(Connection con, String db, Long id_contactoCliente) {
		ContactoCliente aux = new ContactoCliente();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" contactoCliente.id, " +
							" contactoCliente.id_cliente, " +
							" contactoCliente.nombre, " +
							" contactoCliente.cargo, " +
							" contactoCliente.movil, " +
							" contactoCliente.fijo, " +
							" contactoCliente.mail " +
							" from `"+db+"`.contactoCliente " +
							" where contactoCliente.id = ?;");
			smt.setLong(1, id_contactoCliente);
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				aux = new ContactoCliente(rs.getLong(1),rs.getLong(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}
	
	public static boolean create(Connection con, String db, FormContactoClienteGraba aux) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("insert into `"+db+"`.contactoCliente (id_cliente,nombre,cargo,fijo,movil,mail) " +
							" values (?,?,?,?,?,?)");
			smt.setLong(1, aux.id_cliente);
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
	
	public static boolean delete(Connection con, String db, Long id_contactoCliente) {
		boolean flag = false;
		try {
			PreparedStatement smt1 = con
						.prepareStatement("delete from `"+db+"`.contactoCliente WHERE id = ?");
				smt1.setLong(1, id_contactoCliente);
				smt1.executeUpdate();
				smt1.close();
				flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean modificaPorCampo(Connection con, String db,String campo,Long id_contacto,String valor) {
		boolean flag = false;
		try {
			PreparedStatement smt = con.prepareStatement("update `"+db+"`.contactoCliente set " + campo + " = ? WHERE id = ?;");		
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
	
	public static String contactos(Connection con, String db, Long id_cliente) {
		String vistaHtml = "<table class='table table-sm table-hover table-bordered table-condensed table-fluid'>"+
				"<thead style='background-color: #eeeeee'><TR>" +
		        "<TH width='20%' style= 'text-align: center;'>CLIENTE</TH>" +
				"<TH width='20%' style= 'text-align: center;'>NOMBRE</TH>" +
				"<TH width='15%' style= 'text-align: center;'>CARGO</TH>" +
				"<TH width='15%' style= 'text-align: center;'>MOVIL<br>Compra</TH>" +
				"<TH width='15%' style= 'text-align: center;'>FIJO</TH>" +
				"<TH width='15%' style= 'text-align: center;'>eMAIL</TH>" +
				"</TR></thead><tbody>";
			try {
				PreparedStatement smt = con
						.prepareStatement(" select " +
								" contactoCliente.id_cliente,  " + 
								" cliente.nickName,   " +
								" contactoCliente.nombre,   " +
								" contactoCliente.cargo,  " + 
								" contactoCliente.movil,  " + 
								" contactoCliente.fijo,   " +
								" contactoCliente.mail   " +
								" from `"+db+"`.contactoCliente  " + 
								" left join `"+db+"`.cliente on cliente.id = contactoCliente.id_cliente   " +
								" where contactoCliente.id_cliente = ?;");
				smt.setLong(1, id_cliente);
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
	
	
	public static List<List<String>> allContactosCliProvFabr(Connection con, String db) {
		List<List<String>> lista = new ArrayList<List<String>>();
		try {
			PreparedStatement smt1 = con
					.prepareStatement("	select  "
							+ "	'CLIENTE', "
							+ "	cliente.nickName, "
							+ "	contactoCliente.nombre, "
							+ "	contactoCliente.cargo, "
							+ "	contactoCliente.movil, "
							+ "	contactoCliente.fijo, "
							+ "	contactoCliente.mail "
							+ "	from `"+db+"`.contactoCliente "
							+ "	left join `"+db+"`.cliente on cliente.id = contactoCliente.id_cliente "
							+ "	order by cliente.nickName, contactoCliente.nombre; ");
			ResultSet rs1 = smt1.executeQuery();
			while (rs1.next()) {
				List<String> aux = new ArrayList<String>();
				aux.add(rs1.getString(1));
				aux.add(rs1.getString(2));
				aux.add(rs1.getString(3));
				aux.add(rs1.getString(4));
				aux.add(rs1.getString(5));
				aux.add(rs1.getString(6));
				aux.add(rs1.getString(7));
				lista.add(aux);
			}
			rs1.close();
			smt1.close();
			PreparedStatement smt2 = con
					.prepareStatement("	select  "
							+ "	'PROVEEDOR',"
							+ "	proveedor.nickName, "
							+ "	contactoProveedor.nombre, "
							+ "	contactoProveedor.cargo, "
							+ "	contactoProveedor.movil, "
							+ "	contactoProveedor.fijo, "
							+ "	contactoProveedor.mail "
							+ "	from `"+db+"`.contactoProveedor "
							+ "	left join `"+db+"`.proveedor on proveedor.id = contactoProveedor.id_proveedor "
							+ "	order by proveedor.nickName, contactoProveedor.nombre; ");
			ResultSet rs2 = smt2.executeQuery();
			while (rs2.next()) {
				List<String> aux = new ArrayList<String>();
				aux.add(rs2.getString(1));
				aux.add(rs2.getString(2));
				aux.add(rs2.getString(3));
				aux.add(rs2.getString(4));
				aux.add(rs2.getString(5));
				aux.add(rs2.getString(6));
				aux.add(rs2.getString(7));
				lista.add(aux);
			}
			rs2.close();
			smt2.close();
			PreparedStatement smt3 = con
					.prepareStatement(" select  "
							+ "	'FABRICANTE', "
							+ "	fabrica.nickName, "
							+ "	contactoFabrica.nombre, "
							+ "	contactoFabrica.cargo, "
							+ "	contactoFabrica.movil, "
							+ "	contactoFabrica.fijo, "
							+ "	contactoFabrica.mail "
							+ "	from `"+db+"`.contactoFabrica "
							+ "	left join `"+db+"`.fabrica on fabrica.id = contactoFabrica.id_fabrica "
							+ "	order by fabrica.nickName, contactoFabrica.nombre; ");
			ResultSet rs3 = smt3.executeQuery();
			while (rs3.next()) {
				List<String> aux = new ArrayList<String>();
				aux.add(rs3.getString(1));
				aux.add(rs3.getString(2));
				aux.add(rs3.getString(3));
				aux.add(rs3.getString(4));
				aux.add(rs3.getString(5));
				aux.add(rs3.getString(6));
				aux.add(rs3.getString(7));
				lista.add(aux);
			}
			rs3.close();
			smt3.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    return (lista);
	}
	
	public static File contactoCliProvFabrsExcel(String db, List<List<String>> lista, Map<String,String> mapDiccionario) {
		File tmp = null;
try{
	tmp = TempFile.createTempFile("tmp","null");
}catch(Exception e){}
		try {
			String path = "formatos/excel.xlsx";
			InputStream formato = Archivos.leerArchivo(path);
            Workbook libro = WorkbookFactory.create(formato);
            formato.close();
            
            // 0 negro 1 blanco 2 rojo 3 verde 4 azul 5 amarillo 19 celeste
            CellStyle titulo = libro.createCellStyle();
            Font font = libro.createFont();
            font.setBold(true);
            font.setColor((short)4);
            font.setFontHeight((short)(14*20));
            titulo.setFont(font);
            
            CellStyle subtitulo = libro.createCellStyle();
            Font font2 = libro.createFont();
            font2.setBold(true);
            font2.setColor((short)0);
            font2.setFontHeight((short)(12*20));
            subtitulo.setFont(font2);
            
            CellStyle encabezado = libro.createCellStyle();
            encabezado.setBorderBottom(BorderStyle.THIN);
            encabezado.setBorderTop(BorderStyle.THIN);
            encabezado.setBorderRight(BorderStyle.THIN);
            encabezado.setBorderLeft(BorderStyle.THIN);
            encabezado.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            encabezado.setFillForegroundColor((short)19);
            encabezado.setAlignment(HorizontalAlignment.LEFT);
            
            CellStyle detalle = libro.createCellStyle();
            detalle.setBorderBottom(BorderStyle.THIN);
            detalle.setBorderTop(BorderStyle.THIN);
            detalle.setBorderRight(BorderStyle.THIN);
            detalle.setBorderLeft(BorderStyle.THIN);
            
            //titulos del archivo
            
            libro.setSheetName(0, "LISTADO");
            Sheet hoja1 = libro.getSheetAt(0);
            
            Row row = null;
            Cell cell = null;
            
            row = hoja1.createRow(1);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellValue("LISTADO DE CONTACTOS DE CLIENTES, PROVEEDORES Y FABRICANTES");
			
			row = hoja1.createRow(2);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("EMPRESA: "+mapDiccionario.get("nEmpresa"));
			
			row = hoja1.createRow(3);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
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
			cell.setCellValue("ORIGEN");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("CLIENTE");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("CONTACTO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("CARGO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("MOVIL");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("FIJO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("MAIL");
			
			for(int i=0;i<lista.size();i++){
				
				posRow++;
				posCell = 0;
		        row = hoja1.createRow(posRow);
					
		        posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(0));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(1));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(2));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(3));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(4));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(5));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(lista.get(i).get(6));
			}
			
			posRow = posRow + 5;
			row = hoja1.createRow(posRow);
			cell = row.createCell(1);
			Hyperlink hiper = helper.createHyperlink(HyperlinkType.URL);
			hiper.setAddress("https://www.inqsol.cl");
			cell.setHyperlink(hiper);
			cell.setCellValue("Documento generado desde MADA propiedad de INQSOL");
			cell.setHyperlink(hiper);
            
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
