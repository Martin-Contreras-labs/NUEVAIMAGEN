
package models.qr;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.TempFile;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableCell.XWPFVertAlign;

import models.utilities.Archivos;



public class QrEquipo{

	public Long id_equipo;
	public Long id_grupo;
	
	public String grupo;
	public String codigo;
	public String nombre;
	public String qr;
	public Long activo;
	

	public QrEquipo(Long id_equipo, Long id_grupo, String grupo, String codigo, String nombre, String qr, Long activo) {
		super();
		this.id_equipo = id_equipo;
		this.id_grupo = id_grupo;
		this.grupo = grupo;
		this.codigo = codigo;
		this.nombre = nombre;
		this.qr = qr;
		this.activo = activo;
	}



	public QrEquipo() {super();}

	
	
	public static List<QrEquipo> all(Connection con, String db) {
		List<QrEquipo> lista = new ArrayList<QrEquipo>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select qrEquipo.id_equipo, grupo.id, grupo.nombre, equipo.codigo, equipo.nombre,  qrEquipo.qr, qrEquipo.activo "
							+ " from `"+db+"`.qrEquipo "
							+ " left join `"+db+"`.equipo on equipo.id = qrEquipo.id_equipo "
							+ " left join `"+db+"`.grupo on grupo.id = equipo.id_grupo "
							+ " order by qrEquipo.activo desc, equipo.nombre, grupo.nombre;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				lista.add(new QrEquipo(rs.getLong(1),rs.getLong(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getLong(7)));
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<QrEquipo> allSelectEquipoNoEnQr(Connection con, String db) {
		List<QrEquipo> lista = new ArrayList<QrEquipo>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select id_equipo from `"+db+"`.qrEquipo;");
			ResultSet rs = smt.executeQuery();
			List<String> listEnQr = new ArrayList<String>();
			while (rs.next()) {
				listEnQr.add(rs.getString(1)); 
			}
			rs.close();smt.close();
			String aux = "";
			if(listEnQr.size()>0) {
				aux = " where equipo.vigente=1 and equipo.id not in (" + listEnQr.toString().replace("[","").replace("]", "") + ")";
			}
			
			PreparedStatement smt2 = con
					.prepareStatement("select equipo.id, grupo.id, grupo.nombre, equipo.codigo, equipo.nombre "
							+ " from `"+db+"`.equipo " 
							+ " left join `"+db+"`.grupo on grupo.id = equipo.id_grupo " + aux 
							+ " order by equipo.nombre, grupo.nombre;");
			ResultSet rs2 = smt2.executeQuery();
			while (rs2.next()) {
				QrEquipo qrEquipo = new QrEquipo();
				qrEquipo.id_equipo = rs2.getLong(1);
				qrEquipo.id_grupo = rs2.getLong(2);
				qrEquipo.grupo = rs2.getString(3);
				qrEquipo.codigo = rs2.getString(4);
				qrEquipo.nombre = rs2.getString(5);
				qrEquipo.qr = "0";
				qrEquipo.activo = (long)0;
				lista.add(qrEquipo); 
			}
			rs2.close();smt2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static boolean agregaEquipo(Connection con, String db, Long id_equipo, String nombreArchivo) {
		boolean flag = false;
		try {
			
			
			PreparedStatement smt1 = con
					.prepareStatement("select id_equipo from `"+db+"`.qrEquipo where id_equipo=?;");
			smt1.setLong(1, id_equipo);
			ResultSet rs = smt1.executeQuery();
			if(rs.next()) {
				PreparedStatement smt = con
						.prepareStatement("update `"+db+"`.qrEquipo set qr=? where id_equipo=?;");
				smt.setString(1, nombreArchivo);
				smt.setLong(2, id_equipo);
				smt.executeUpdate();
				smt.close();
				flag = true;
			}else {
				PreparedStatement smt = con
						.prepareStatement("insert into `"+db+"`.qrEquipo (id_equipo, qr) values (?,?);");
				smt.setLong(1, id_equipo);
				smt.setString(2, nombreArchivo);
				smt.executeUpdate();
				smt.close();
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(flag);
	}
	
	public static boolean cambiarEstado(Connection con, String db, Long idEquipo, Long activo) {
		boolean flag=false;
		try {
			PreparedStatement smt = con
					.prepareStatement("update `"+db+"`.qrEquipo set activo=? where id_equipo=?;");
			if(activo==1) {
				smt.setLong(1,(long)0);
			}else {
				smt.setLong(1,(long)1);
			}
			smt.setLong(2, idEquipo);
			smt.executeUpdate();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(flag);
	}
	
	public static QrEquipo find(Connection con, String db,Long idEquipo) {
		QrEquipo aux = new QrEquipo();
		try {
			PreparedStatement smt = con
					.prepareStatement("select qrEquipo.id_equipo, grupo.id, grupo.nombre, equipo.codigo, equipo.nombre,  qrEquipo.qr, qrEquipo.activo "
							+ " from `"+db+"`.qrEquipo "
							+ " left join `"+db+"`.equipo on equipo.id = qrEquipo.id_equipo "
							+ " left join `"+db+"`.grupo on grupo.id = equipo.id_grupo "
							+ " where qrEquipo.id_equipo=?;");
			smt.setLong(1, idEquipo);
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				aux = new QrEquipo(rs.getLong(1),rs.getLong(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getLong(7));
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}
	
	public static boolean cambiarPubEquipo(Connection con, String db, String id_equipo, String id_atributo, String publico) {
		boolean flag = false;
		try {
			if(publico.equals("1")) publico = "0"; else publico = "1";
			PreparedStatement smt = con
					.prepareStatement("update `"+db+"`.atributoEquipo set publico=? where id_equipo=? and id_atributo=?;");
			smt.setString(1, publico);
			smt.setString(2, id_equipo);
			smt.setString(3, id_atributo);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(flag);
	}
	
	public static boolean cambiarPubQr(Connection con, String db, String id_equipo, String id_qrAtributoEquipo, String publico) {
		boolean flag = false;
		try {
			if(publico.equals("1")) publico = "0"; else publico = "1";
			PreparedStatement smt = con
					.prepareStatement("update `"+db+"`.qrTransacEquipo set activo=? where id_equipo=? and id_qrAtributoEquipo=?;");
			smt.setString(1, publico);
			smt.setString(2, id_equipo);
			smt.setString(3, id_qrAtributoEquipo);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(flag);
	}
	
	public static List<List<List<String>>> listaDeQrEquipos(Connection con, String db) {
		List<QrEquipo> listEquipQr = QrEquipo.all(con, db);
		List<List<List<String>>> lista = new ArrayList<List<List<String>>>();
		for(int i=0; i<listEquipQr.size();) {
			List<List<String>> aux = new ArrayList<List<String>>();
			for(int j=1; i<listEquipQr.size() && j<6; j++) {
				List<String> aux2 = new ArrayList<String>();
				aux2.add(listEquipQr.get(i).qr);
				aux2.add(listEquipQr.get(i).codigo);
				aux.add(aux2);
				i++;
			}
			lista.add(aux);
		}
		return(lista);
	}
	
	public static File reporteEnWord(Connection con, String db, List<List<List<String>>> lista, Map<String,String> mapDiccionario) {
		File tmp = null;
try{
	tmp = TempFile.createTempFile("tmp","null");
}catch(Exception e){}
		try {
			String path = "formatos/listadoQR.docx";
			InputStream formato = Archivos.leerArchivo(path);
			XWPFDocument doc = new XWPFDocument(formato);
			XWPFTable table = doc.getTables().get(0);
			XWPFTableCell cell = null;
			for (int i=0; i<lista.size(); i++) {
				for (int j=0; j<lista.get(i).size(); j++) {
					cell=table.getRow(i).getCell(j);
					String rutaLogo = db+"/"+mapDiccionario.get("logoEmpresa");
					InputStream inputStreamLogo = Archivos.leerArchivo(rutaLogo);
					byte[] logo = IOUtils.toByteArray(inputStreamLogo);
					BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(logo));
	    			int originalWidth = bufferedImage.getWidth();
	    			int originalHeight = bufferedImage.getHeight();
	    			// Definir un ancho fijo y calcular la altura proporcionalmente
	    			int targetWidth = Units.toEMU(60); // Tamaño fijo en EMU
	    			int targetHeight = (originalHeight * targetWidth) / originalWidth; // Mantener proporción
					inputStreamLogo.close();
					XWPFParagraph paragraphLogo = cell.addParagraph();
					paragraphLogo.setAlignment(ParagraphAlignment.CENTER);
	    			XWPFRun run3 = paragraphLogo.createRun();
	    			run3.addPicture(new ByteArrayInputStream(logo), XWPFDocument.PICTURE_TYPE_PNG, "firma", targetWidth, targetHeight);
					
					
					
					String ruta = db+"/"+lista.get(i).get(j).get(0);
					InputStream inputStream = Archivos.leerArchivo(ruta);
					byte[] qr = IOUtils.toByteArray(inputStream);
					inputStream.close();
					XWPFParagraph paragraph = cell.addParagraph();
					paragraph.setAlignment(ParagraphAlignment.CENTER);
	    			XWPFRun run2 = paragraph.createRun();
	    			run2.addPicture(new ByteArrayInputStream(qr), XWPFDocument.PICTURE_TYPE_PNG, "firma", Units.toEMU(60), Units.toEMU(60));
	    			setCelda(cell,"Arial",10,2,"000000",lista.get(i).get(j).get(1),false);
				}
				table.createRow();
			}
			// Write the output to a file word
			FileOutputStream fileOut = new FileOutputStream(tmp);
			doc.write(fileOut);
			fileOut.close();
		} catch (InvalidFormatException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return(tmp);
	}
	
	public static void setCelda (XWPFTableCell cell,String fontFamily,int fontSize,int alingH,String colorRGB,String text,boolean bold) {
		if(text==null) text="--"; else if(text.trim().length()==0) text="--";
		XWPFParagraph paragraph =null;
		try{cell.removeParagraph(0);}catch(Exception e) {}
		paragraph = cell.addParagraph();
		if(alingH==3) {
			paragraph.setAlignment(ParagraphAlignment.RIGHT);
		}else if (alingH==2) {
			paragraph.setAlignment(ParagraphAlignment.CENTER);
		}else {
			paragraph.setAlignment(ParagraphAlignment.LEFT);
		}
		cell.setVerticalAlignment(XWPFVertAlign.CENTER);
		XWPFRun celda = paragraph.createRun();
		celda.setFontFamily(fontFamily);
		celda.setFontSize(fontSize);
		celda.setColor(colorRGB);
		celda.setText(text);
		celda.setBold(bold);
    }
	
	
	
	

}
