package models.contratos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

import controllers.HomeController;
import models.tables.*;
import org.apache.poi.util.TempFile;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableCell.XWPFVertAlign;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import models.forms.FormContrato;
import models.utilities.Archivos;
import models.utilities.Fechas;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GeneraPDF_Contrato {
	static DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
	static DecimalFormat myformatdouble0 = new DecimalFormat("#,##0",symbols);

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	public static String generaPdfContratoTipoCotiza(Connection con, String db, Cotizacion coti, FormContrato form) {
		try {
			String listadoPlanos = form.listadoPlanos;
			String fechaPlanos = form.fechaPlanos;
			String nomRepresEmpresa = form.nomRepresEmpresa;
			String rutRepresEmpresa = form.rutRepresEmpresa;
			String direccionObra = form.direccionObra;
			String fechaContrato= Fechas.DDMMAA(form.fechaContrato);
			String numeroCotizacion= coti.numero.toString();
			String numeroContrato= coti.numeroContrato.toString();
			String fechaCotizacion= Fechas.DDMMAA(coti.getFecha());
			EmisorTributario emisor = EmisorTributario.find(con, db);
			String nombreEmisor= emisor.razonSocial;
			String direccionEmisor=emisor.direccion;
			String comunaEmisor= emisor.comuna;
			String ciudadEmisor= emisor.ciudad;
			String rutEmisor=emisor.rut;
			String rutRep1Emisor=emisor.rutRepresentante1;
			String nombRep1Emisor=emisor.nombreRepresentante1;
			Cliente cliente = Cliente.find(con, db, coti.id_cliente);
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
			String numeroOrdenCompra = coti.numeroOC;
			Sucursal sucursal = Sucursal.find(con, db, coti.id_sucursal.toString());
			String direccionSucursal = sucursal.direccion;
			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, db, coti.id_bodegaEmpresa);
			String nombreObra = "";
			String direccionProyecto = "";
			if(bodega!=null) {
				nombreObra = bodega.nombre;
				Proyecto proyecto = Proyecto.find(con, db, bodega.id_proyecto);
				if(proyecto!=null) {
					direccionProyecto = proyecto.direccion + ", " + proyecto.comuna + ", " + proyecto.ciudad;
				}
				sucursal = Sucursal.find(con, db, bodega.id_sucursal.toString());
				direccionSucursal = sucursal.direccion;
			}
			String detalleGarantia = form.garantiaDet;
			List<CotizaDetalle> detalle = CotizaDetalle.allPorIdCotizacion(con, db, coti.id);
			File tmp = null;
try{
	tmp = TempFile.createTempFile("tmp","null");
}catch(Exception e){}
			String path = db + "/formatos/contrato.docx";
			InputStream formato = Archivos.leerArchivo(path);
			XWPFDocument doc = new XWPFDocument(formato);
			formato.close();
			XWPFTable table = doc.getTables().get(0);
			XWPFTableCell cell = null;
			XWPFTableRow row = null;
			row = table.getRow(1);
			cell = row.getCell(2);
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
			if(!db.equals("madaAndinaMontajes")) {
				try {
					table = doc.getTables().get(2);
				}catch(Exception e) {
					table = null;
				}
				boolean flag = false;
				if(table!=null &&  table.getNumberOfRows() == 3) {
					flag = true;
				}
				for(int i=0;i<detalle.size();i++) {
					String precioUnitVenta = detalle.get(i).precioVenta;
					String precioUnitArriendo = detalle.get(i).precioArriendo;
					String unidadTiempo = detalle.get(i).unidadTiempo;
					Double puArr = Double.parseDouble(detalle.get(i).precioArriendo.replaceAll(",", ""));
					Double perm = detalle.get(i).permanencia;
					Double cant = Double.parseDouble(detalle.get(i).cantidad.replaceAll(",", ""));
					granTotal += puArr * perm * cant;
					if(flag) {
						row = table.getRow(i+1);
						cell = row.getCell(0); setCelda(cell,"Arial",10,2,"2b5079",detalle.get(i).codigo,false);
						cell = row.getCell(1); setCelda(cell,"Arial",10,1,"2b5079",detalle.get(i).equipo,false);
						cell = row.getCell(2); setCelda(cell,"Arial",10,2,"2b5079",detalle.get(i).unidad,false);
						cell = row.getCell(3); setCelda(cell,"Arial",10,3,"2b5079",detalle.get(i).cantidad,false);
						cell = row.getCell(4); setCelda(cell,"Arial",10,3,"2b5079",precioUnitVenta,false);
						cell = row.getCell(5); setCelda(cell,"Arial",10,3,"2b5079",precioUnitArriendo+" por "+unidadTiempo,false);
						table.createRow();
					}
				}
			}else {
				for(int i=0;i<detalle.size();i++) {
					Double puArr = Double.parseDouble(detalle.get(i).precioArriendo.replaceAll(",", ""));
					Double perm = detalle.get(i).permanencia;
					Double cant = Double.parseDouble(detalle.get(i).cantidad.replaceAll(",", ""));
					granTotal += puArr * perm * cant;
				}
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
							text = text.replace("guiaNumero", coti.numero.toString());
							r.setText(text, 0); }
						if (text.contains("fechaGuia"))  {
							text = text.replace("fechaGuia", coti.fecha);
							r.setText(text, 0); }
						if (text.contains("notasAlContrato"))  {
							text = text.replace("notasAlContrato", coti.notasAlContrato);
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
						if (text.contains("detalleGarantia"))  {
							text = text.replace("detalleGarantia", detalleGarantia);
							r.setText(text, 0); }
					   if (text.contains("montoTotalNeto"))  {
							text = text.replace("montoTotalNeto", myformatdouble0.format(granTotal));
							r.setText(text, 0); }
					   if (text.contains("fechaCotizacion"))  {
							text = text.replace("fechaCotizacion",fechaCotizacion);
							r.setText(text, 0); }
					  if (text.contains("listadoPlanos"))  {
							text = text.replace("listadoPlanos", listadoPlanos);
							r.setText(text, 0); }
					  if (text.contains("fechaPlanos"))  {
							text = text.replace("fechaPlanos", fechaPlanos);
							r.setText(text, 0); }
					  if (text.contains("nomRepresEmpresa"))  {
							text = text.replace("nomRepresEmpresa", nomRepresEmpresa);
							r.setText(text, 0); }
					  if (text.contains("rutRepresEmpresa"))  {
							text = text.replace("rutRepresEmpresa", rutRepresEmpresa);
							r.setText(text, 0); }
					  if (text.contains("direccionObra"))  {
							text = text.replace("direccionObra", direccionObra);
							r.setText(text, 0); }
					 if (text.contains("direccionSucursal"))  {
							text = text.replace("direccionSucursal", direccionSucursal);
							r.setText(text, 0); }
					if (text.contains("giroCliente"))  {
						text = text.replace("giroCliente", giroCliente);
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
			for (XWPFTable table9 : document.getTables()) {
				for (XWPFTableRow row9 : table9.getRows()) {
					for (XWPFTableCell cell9 : row9.getTableCells()) {
						cell9.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
						CTTcPr tcPr = cell9.getCTTc().isSetTcPr() ? cell9.getCTTc().getTcPr() : cell9.getCTTc().addNewTcPr();
						CTTcMar tcMar = tcPr.isSetTcMar() ? tcPr.getTcMar() : tcPr.addNewTcMar();
						BigInteger padding = BigInteger.valueOf(50);
						if (!tcMar.isSetBottom()) tcMar.addNewBottom();
						tcMar.getBottom().setW(padding);
						tcMar.getBottom().setType(STTblWidth.DXA);
					}
				}
			}
			// 2) Prepare Pdf options
			PdfOptions options = PdfOptions.create();
			options.fontEncoding("UTF-8");
			// 3) Convert XWPFDocument to Pdf
			OutputStream out = new FileOutputStream(tmp);
			PdfConverter.getInstance().convert(document, out, options);
			out.close();
			
				String archivoPdf = "Contrato_"+coti.getNumeroContrato()+".pdf";
				path = db+"/"+archivoPdf;
				Archivos.grabarArchivo(tmp, path);
				return(archivoPdf);
		} catch (IOException e) {
			String className = GeneraPDF_Contrato.class.getSimpleName();
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
	

	

	
