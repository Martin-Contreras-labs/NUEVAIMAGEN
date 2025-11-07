package models.contratos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.List;

import controllers.HomeController;
import models.tables.*;
import org.apache.poi.util.TempFile;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableCell.XWPFVertAlign;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import models.utilities.Archivos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GeneraPDF_ContratoMontax {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	public static String generaPdfContratoMontax(Connection con, String db, Cotizacion coti) {
		try {
			String fechaContrato = coti.fecha;
			String numeroContrato = coti.numero.toString();
			String numeroCotizacion = coti.numero.toString();
			EmisorTributario emisor = EmisorTributario.find(con, db);
			String nombreEmisor = emisor.razonSocial;
			String direccionEmisor =emisor.direccion;
			String comunaEmisor = emisor.comuna;
			String ciudadEmisor = emisor.ciudad;
			String rutEmisor = emisor.rut;
			String rutRep1Emisor = emisor.rutRepresentante1;
			String nombRep1Emisor = emisor.nombreRepresentante1;
			Cliente cliente = Cliente.find(con, db, coti.id_cliente);
			String rutCliente=cliente.rut;
			String nombreCliente=cliente.nombre;
			String direccionCliente=cliente.direccion;
			String comunaCliente=cliente.comuna;
			String ciudadCliente=cliente.ciudad;
			String fonoCliente=cliente.fonoContacto;
			String rutRep1Cliente=cliente.rutRepresentante1;
			String nombRep1Cliente=cliente.nombreRepresentante1;
			String rutRep2Cliente=cliente.rutRepresentante2;
			String nombRep2Cliente=cliente.nombreRepresentante2;
			List<CotizaDetalle> detalle = CotizaDetalle.allPorIdCotizacion(con, db, coti.id);
			File tmp = TempFile.createTempFile("tmp","null");
			String path = db + "/formatos/contrato.docx";
			InputStream formato = Archivos.leerArchivo(path);
			XWPFDocument doc = new XWPFDocument(formato);
			formato.close();
			XWPFTable table = doc.getTables().get(0);
			XWPFTableRow row = table.getRow(1);
			XWPFTableCell cell = row.getCell(0);
			setCelda(cell,"Arial",10,2,"2b5079","Nro: " + numeroContrato,false);
			table = doc.getTables().get(1);
			for(int i=0;i<detalle.size();i++) {
				String precioUnitVenta = detalle.get(i).precioVenta;
				String precioUnitArriendo = detalle.get(i).precioArriendo;
				String unidadTiempo = detalle.get(i).unidadTiempo;
				row = table.getRow(i+1);
				cell = row.getCell(0); setCelda(cell,"Arial",10,2,"2b5079",detalle.get(i).codigo,false);
				cell = row.getCell(1); setCelda(cell,"Arial",10,1,"2b5079",detalle.get(i).equipo,false);
				cell = row.getCell(2); setCelda(cell,"Arial",10,2,"2b5079",detalle.get(i).unidad,false);
				cell = row.getCell(3); setCelda(cell,"Arial",10,3,"2b5079",detalle.get(i).cantidad,false);
				cell = row.getCell(4); setCelda(cell,"Arial",10,3,"2b5079",precioUnitVenta,false);
				cell = row.getCell(5); setCelda(cell,"Arial",10,3,"2b5079",precioUnitArriendo+" por "+unidadTiempo,false);
				table.createRow();
			}
			for (XWPFParagraph p : doc.getParagraphs()) {
				 for (XWPFRun r : p.getRuns()) {
					 String text = r.getText(0);
					if(text!=null){
						if (text.trim().contains("comunaEmisor  ")) {;
							text = text.replace("comunaEmisor  ",comunaEmisor);
							r.setText(text, 0); }
						if (text.contains("fechaContrato"))   {
							text = text.replace("fechaContrato", fechaContrato);
							r.setText(text, 0); }
						if (text.contains("nombreEmisor"))  {
							text = text.replace("nombreEmisor", nombreEmisor);
							r.setText(text, 0); }
						if (text.contains("rutEmisor"))  {
							text = text.replace("rutEmisor",rutEmisor);
							r.setText(text, 0); }
						if (text.contains("nombRep1Emisor"))  {
							text = text.replace("nombRep1Emisor",nombRep1Emisor);
							r.setText(text, 0); }
						if (text.contains("rutRep1Emisor"))  {
							text = text.replace("rutRep1Emisor",rutRep1Emisor);
							r.setText(text, 0); }
						if (text.contains("nombRep2Emisor"))  {
							text = text.replace("rutEmisor",rutEmisor);
							r.setText(text, 0); }
						if (text.contains("rutRep2Emisor"))  {
						   text = text.replace("rutEmisor",rutEmisor);
							r.setText(text, 0); }
						if (text.contains("direccionEmisor"))  {
							text = text.replace("direccionEmisor", direccionEmisor);
							r.setText(text, 0); }
						if (text.contains("comunaEmisor"))  {
							text = text.replace("comunaEmisor", comunaEmisor);
							r.setText(text, 0); }
						if (text.contains("ciudadEmisor"))  {
							text = text.replace("ciudadEmisor", ciudadEmisor);
							r.setText(text, 0); }
						if (text.contains("nombreCliente"))  {
							text = text.replace("nombreCliente", nombreCliente);
							r.setText(text, 0); }
						if (text.contains("rutCliente"))  {
							text = text.replace("rutCliente", rutCliente );
							r.setText(text, 0); }
						if (text.contains("nombRep1Cliente"))  {
							text = text.replace("nombRep1Cliente",nombRep1Cliente);
							r.setText(text, 0); }
						if (text.contains("rutRep1Cliente"))  {
						   text = text.replace("rutRep1Cliente",rutRep1Cliente);
								r.setText(text, 0); }
						if (text.contains("nombRep2Cliente"))  {
							text = text.replace("nombRep2Cliente",nombRep2Cliente);
							r.setText(text, 0); }
						if (text.contains("rutRep2Cliente"))  {
						   text = text.replace("rutRep2Cliente",rutRep2Cliente);
								r.setText(text, 0); }
						if (text.contains("direccionCliente"))  {
							text = text.replace("direccionCliente", direccionCliente);
							r.setText(text, 0); }
						if (text.contains("comunaCliente"))  {
							text = text.replace("comunaCliente", comunaCliente);
							r.setText(text, 0); }
						if (text.contains("ciudadCliente"))  {
							text = text.replace("ciudadCliente", ciudadCliente);
							r.setText(text, 0); }
						if (text.contains("fonoCliente"))  {
							text = text.replace("fonoCliente", fonoCliente);
							r.setText(text, 0); }
						if (text.contains("guiaNumero"))  {
							text = text.replace("guiaNumero", coti.numero.toString());
							r.setText(text, 0); }
						if (text.contains("fechaGuia"))  {
							text = text.replace("fechaGuia", coti.fecha);
							 r.setText(text, 0); }
						if (text.contains("numeroCotizacion"))  {
							text = text.replace("numeroCotizacion", numeroCotizacion);
							r.setText(text, 0); }
					}
				 }
			}
			// Write the output to a file word
			FileOutputStream fileOut = new FileOutputStream(tmp);
			doc.write(fileOut);
			fileOut.close();
			// 1) Load DOCX into XWPFDocument
			InputStream is = new FileInputStream(tmp);
			XWPFDocument document = new XWPFDocument(is);
			is.close();
			// 2) Prepare Pdf options
			PdfOptions options = PdfOptions.create().fontEncoding("iso-8859-15");
			// 3) Convert XWPFDocument to Pdf
			OutputStream out = new FileOutputStream(tmp);
			PdfConverter.getInstance().convert(document, out, options);
			out.close();
			String archivoPdf = "Contrato_"+coti.getNumeroContrato()+".pdf";
			path = db+"/"+archivoPdf;
			Archivos.grabarArchivo(tmp, path);
			return(archivoPdf);
		} catch (IOException e) {
			String className = GeneraPDF_ContratoMontax.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return("0");
	}
	
	
	public static void setCelda (XWPFTableCell cell,String fontFamily,int fontSize,int alingH,String colorRGB,String text,boolean bold) {
		cell.removeParagraph(0);
		XWPFParagraph paragraph =null;
		if(text==null) text="--"; else if(text.trim().length()==0) text="--";
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
	

	

	
