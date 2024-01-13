package models.contratos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.text.DecimalFormat;

import org.apache.poi.util.TempFile;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableCell.XWPFVertAlign;

import models.forms.FormContrato;
import models.tables.Cotizacion;
import models.utilities.Archivos;

 
public class GeneraPDF_ContratoPeruTeca {
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble4 = new DecimalFormat("#,##0.0000");
	static DecimalFormat myformatdouble6 = new DecimalFormat("#,##0.000000");
	
	
	public static String generaPdfContratoTeca1(Connection con, String db, FormContrato form, Cotizacion coti) {
		
		String clienteNombre = form.clienteNombre;
		String clienteRut = form.clienteRut;
		String clienteDireccion = form.clienteDireccion;
		String clienteComuna = form.clienteComuna;
		String clienteRegion = form.clienteRegion;
		String clienteRepresentanteRut = form.clienteRutRepresentante1;
		String clienteRepresentante = form.clienteRepresentante1;
		String clienteContacto = form.clienteContacto;
		String clienteCargoContacto = form.clienteCargoContacto;
		String clienteRepFirma = form.clienteRepresentante1;
		String clienteRepCargoFirma = form.clienteCargoRepresentante1;
		String clienteOC = form.clienteOC;
		String clienteFechaOC = form.clienteFechaOC;
		String fechaContrato = form.fechaContrato;
		String numeroContrato = form.numeroContrato;
		String usadosEn = form.usadosEn;
		String garantiaDoc = form.garantiaDoc;
		String garantiaDet = form.garantiaDet;
		String garantiaEquiv = form.garantiaDet;
		String garantiaVenc = form.garantiaVenc;
		
		File tmp = TempFile.createTempFile("tmp","null");
	   
			try {
				
				String path = db + "/formatos/contratoTeca1.docx";
				InputStream formato = Archivos.leerArchivo(path);
				XWPFDocument doc = new XWPFDocument(formato);
				formato.close();
				
				for (XWPFParagraph p : doc.getParagraphs()) {
		             for (XWPFRun r : p.getRuns()) {
		                 String text = r.getText(0);
		 	            if(text!=null){
		 	                if (text.trim().contains("clienteNombre")) {text = text.replace("clienteNombre",clienteNombre);r.setText(text,0);}
		 	                if (text.trim().contains("clienteRut")) {text = text.replace("clienteRut",clienteRut);r.setText(text,0);}
		 	                if (text.trim().contains("clienteDireccion")) {text = text.replace("clienteDireccion",clienteDireccion);r.setText(text,0);}
		 	                if (text.trim().contains("clienteComuna")) {text = text.replace("clienteComuna",clienteComuna);r.setText(text,0);}
		 	                if (text.trim().contains("clienteRegion")) {text = text.replace("clienteRegion",clienteRegion);r.setText(text,0);}
		 	                if (text.trim().contains("clienteRepresentanteRut")) {text = text.replace("clienteRepresentanteRut",clienteRepresentanteRut);r.setText(text,0);}
		 	                if (text.trim().contains("clienteRepresentante")) {text = text.replace("clienteRepresentante",clienteRepresentante);r.setText(text,0);}
		 	                if (text.trim().contains("clienteContacto")) {text = text.replace("clienteContacto",clienteContacto);r.setText(text,0);}
		 	                if (text.trim().contains("clienteCargoContacto")) {text = text.replace("clienteCargoContacto",clienteCargoContacto);r.setText(text,0);}
		 	                if (text.trim().contains("clienteRepFirma")) {text = text.replace("clienteRepFirma",clienteRepFirma);r.setText(text,0);}
		 	                if (text.trim().contains("clienteRepCargoFirma")) {text = text.replace("clienteRepCargoFirma",clienteRepCargoFirma);r.setText(text,0);}
		 	                if (text.trim().contains("clienteOC")) {text = text.replace("clienteOC",clienteOC);r.setText(text,0);}
		 	                if (text.trim().contains("clienteFechaOC")) {text = text.replace("clienteFechaOC",clienteFechaOC);r.setText(text,0);}
		 	                if (text.trim().contains("fechaContrato")) {text = text.replace("fechaContrato",fechaContrato);r.setText(text,0);}
		 	                if (text.trim().contains("numeroContrato")) {text = text.replace("numeroContrato",numeroContrato);r.setText(text,0);}
		 	                
		 	               if (text.trim().contains("usadosEn")) {text = text.replace("usadosEn",usadosEn);r.setText(text,0);}
		 	               if (text.trim().contains("garantiaDoc")) {text = text.replace("garantiaDoc",garantiaDoc);r.setText(text,0);}
		 	               if (text.trim().contains("garantiaDet")) {text = text.replace("garantiaDet",garantiaDet);r.setText(text,0);}
		 	               if (text.trim().contains("garantiaEquiv")) {text = text.replace("garantiaEquiv",garantiaEquiv);r.setText(text,0);}
		 	               if (text.trim().contains("garantiaVenc")) {text = text.replace("garantiaVenc",garantiaVenc);r.setText(text,0);}
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
				e.printStackTrace();
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
	

	

	
