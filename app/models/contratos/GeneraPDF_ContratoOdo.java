package models.contratos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

import models.forms.FormContratoOdo;
import models.forms.FormCotizaOdo;
import models.utilities.Archivos;
import models.utilities.Fechas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GeneraPDF_ContratoOdo {
	static DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
	static DecimalFormat myformatdouble0 = new DecimalFormat("#,##0",symbols);

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	public static String generaPdfContratoTipoCotizaOdo(Connection con, String db, CotiOdo cotiOdo, FormContratoOdo form) {
		try {
			String fechaContrato= Fechas.DDMMAA(form.fechaContrato);
			String numeroCotizacion= cotiOdo.numero.toString();
			String numeroContrato= cotiOdo.numeroContrato.toString();
			String fechaCotizacion= Fechas.DDMMAA(cotiOdo.getFecha());
			EmisorTributario emisor = EmisorTributario.find(con, db);
			String nombreEmisor= emisor.razonSocial;
			String direccionEmisor=emisor.direccion;
			String comunaEmisor= emisor.comuna;
			String ciudadEmisor= emisor.ciudad;
			String rutEmisor=emisor.rut;
			String rutRep1Emisor=emisor.rutRepresentante1;
			String nombRep1Emisor=emisor.nombreRepresentante1;
			Cliente cliente = Cliente.find(con, db, cotiOdo.id_cliente);
			String rutCliente=cliente.rut;
			String nombreCliente=cliente.nombre;
			String direccionCliente=cliente.direccion;
			String comunaCliente=cliente.comuna;
			String regionCliente=cliente.region;
			String ciudadCliente=cliente.ciudad;
			String mailCliente=cliente.mailFactura;
			String fonoCliente=cliente.fonoContacto;
			String giroCliente=cliente.giro;
			String rutRep1Cliente=cliente.rutRepresentante1;
			String nombRep1Cliente=cliente.nombreRepresentante1;
			String cargoRep1Cliente=cliente.cargoRepresentante1;
			String rutRep2Cliente=cliente.rutRepresentante2;
			String nombRep2Cliente=cliente.nombreRepresentante2;
			String numeroOrdenCompra = cotiOdo.numeroOC;
			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, db, cotiOdo.id_bodegaEmpresa);
			String nombreObra = "";
			String direccionProyecto = "";
			if(bodega!=null) {
				nombreObra = bodega.nombre;
				Proyecto proyecto = Proyecto.find(con, db, bodega.id_proyecto);
				if(proyecto!=null) {
					direccionProyecto = proyecto.direccion + ", " + proyecto.comuna + ", " + proyecto.ciudad;
				}
			}
			List<List<String>> listadoServiciosAux = FormCotizaOdo.listServiciosConValoresCoti(con, db, cotiOdo.id);
			List<List<String>> listadoServicios = new ArrayList<List<String>>();
			for(List<String> l: listadoServiciosAux) {
				if(!l.get(5).equals("0.00")) {
					listadoServicios.add(l);
				}
			}
			File tmp = TempFile.createTempFile("tmp","null");
			String path = db + "/formatos/contratoOdo.docx";
			InputStream formato = Archivos.leerArchivo(path);
			XWPFDocument doc = new XWPFDocument(formato);
			formato.close();
			XWPFTable table = doc.getTables().get(0);
			XWPFTableRow row = table.getRow(1);
			XWPFTableCell cell = row.getCell(2);
			setCelda(cell,"Arial",10,2,"2b5079","Nro: " + numeroContrato,false);
			row = table.getRow(3);
			cell = row.getCell(2);
			setCelda(cell,"Arial",10,2,"2b5079",numeroCotizacion,false);
			table= doc.getTables().get(1);
			cell = table.getRow(1).getCell(1); setCelda(cell,"Arial",10,1,"2b5079",nombreCliente,false);
			cell = table.getRow(2).getCell(1); setCelda(cell,"Arial",10,1,"2b5079",direccionCliente,false);
			cell = table.getRow(3).getCell(1); setCelda(cell,"Arial",10,1,"2b5079",mailCliente,false);
			cell = table.getRow(4).getCell(1); setCelda(cell,"Arial",10,1,"2b5079",fonoCliente,false);
			cell = table.getRow(2).getCell(2); setCelda(cell,"Arial",10,2,"2b5079",rutCliente,false);
			cell = table.getRow(4).getCell(2); setCelda(cell,"Arial",10,2,"2b5079",giroCliente,false);
			Double granTotal = (double) 0;
			table = doc.getTables().get(2);
			for(int i=0;i<listadoServicios.size();i++) {
				String codigo = listadoServicios.get(i).get(2);
				String servicio = listadoServicios.get(i).get(3);
				String unidad = listadoServicios.get(i).get(4);
				String cantidad = listadoServicios.get(i).get(5);
				String moneda = listadoServicios.get(i).get(6);
				String puVenta = listadoServicios.get(i).get(7);
				String aplicaMin = listadoServicios.get(i).get(9);
				String cantMin = listadoServicios.get(i).get(10);
				String preAdic = listadoServicios.get(i).get(11);
				row = table.getRow(i+1);
				cell = row.getCell(0); setCelda(cell,"Arial",8,2,"2b5079",codigo,false);
				cell = row.getCell(1); setCelda(cell,"Arial",8,1,"2b5079",servicio,false);
				cell = row.getCell(2); setCelda(cell,"Arial",8,2,"2b5079",unidad,false);
				cell = row.getCell(3); setCelda(cell,"Arial",8,3,"2b5079",cantidad,false);
				cell = row.getCell(4); setCelda(cell,"Arial",8,3,"2b5079",moneda,false);
				cell = row.getCell(5); setCelda(cell,"Arial",8,3,"2b5079",puVenta,false);
				cell = row.getCell(6); setCelda(cell,"Arial",8,3,"2b5079",aplicaMin,false);
				cell = row.getCell(7); setCelda(cell,"Arial",8,3,"2b5079",cantMin,false);
				cell = row.getCell(8); setCelda(cell,"Arial",8,3,"2b5079",preAdic,false);
				table.createRow();
			}
			for (XWPFParagraph p : doc.getParagraphs()) {
				 for (XWPFRun r : p.getRuns()) {
					 String text = r.getText(0);
					if(text!=null){
						if (text.trim().contains("nombreObra")) {;
							text = text.replace("nombreObra",nombreObra);
							r.setText(text, 0); }
						if (text.trim().contains("comunaEmisor")) {;
							text = text.replace("comunaEmisor",comunaEmisor);
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
						if (text.contains("regionCliente"))  {
							text = text.replace("regionCliente", regionCliente);
							r.setText(text, 0); }
						if (text.contains("ciudadCliente"))  {
							text = text.replace("ciudadCliente", ciudadCliente);
							r.setText(text, 0); }
						if (text.contains("fonoCliente"))  {
							text = text.replace("fonoCliente", fonoCliente);
							r.setText(text, 0); }
						if (text.contains("mailCliente"))  {
							text = text.replace("mailCliente", mailCliente);
							r.setText(text, 0); }
						if (text.contains("cargoRep1Cliente"))  {
							text = text.replace("cargoRep1Cliente", cargoRep1Cliente);
							r.setText(text, 0); }
						if (text.contains("guiaNumero"))  {
							text = text.replace("guiaNumero", cotiOdo.numero.toString());
							r.setText(text, 0); }
						if (text.contains("fechaGuia"))  {
							text = text.replace("fechaGuia", cotiOdo.fecha);
							r.setText(text, 0); }
						if (text.contains("notasAlContrato"))  {
							text = text.replace("notasAlContrato", cotiOdo.notasAlContrato);
							r.setText(text, 0); }
						if (text.contains("numeroOrdenCompra"))  {
							text = text.replace("numeroOrdenCompra", numeroOrdenCompra);
							r.setText(text, 0); }
						if (text.contains("numeroCotizacion"))  {
							text = text.replace("numeroCotizacion", numeroCotizacion);
							r.setText(text, 0); }
						if (text.contains("direccionProyecto"))  {
							text = text.replace("direccionProyecto", direccionProyecto);
							r.setText(text, 0); }
					   if (text.contains("montoTotalNeto"))  {
							text = text.replace("montoTotalNeto", myformatdouble0.format(granTotal));
							r.setText(text, 0); }
					   if (text.contains("fechaCotizacion"))  {
							text = text.replace("fechaCotizacion",fechaCotizacion);
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
				String archivoPdf = "Contrato_"+cotiOdo.getNumeroContrato()+".pdf";
				path = db+"/"+archivoPdf;
				Archivos.grabarArchivo(tmp, path);
				return(archivoPdf);
		} catch (IOException e) {
			String className = ActaBaja.class.getSimpleName();
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
	

	

	
