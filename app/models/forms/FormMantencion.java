package models.forms;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.TempFile;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.poi.xwpf.usermodel.XWPFTableCell.XWPFVertAlign;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;

import models.tables.MantTransacReport;
import models.utilities.Archivos;

public class FormMantencion {
	public String cantidad_mec;
	public String cantidad_mecP;
	public String cantidad_oper;
	public List<String> cantidadC[];
	public List<String> cantidadP[];
	public String comentario;
	public String descTrabajo;
	public String descTrabajoP;
	public String docAnexo;
	public String estadoFinal;
	public String estadoFinalP;
	public String fecha;
	public String firmaAprobador;
	public String firmaEjecutor;
	public String horaIni_mec;
	public String horaIni_mecP;
	public String horaIni_oper;
	public String horaTer_mec;
	public String horaTer_mecP;
	public String horaTer_oper;
	public String fechaIni_mec;
	public String fechaIni_mecP;
	public String fechaIni_oper;
	public String fechaTer_mec;
	public String fechaTer_mecP;
	public String fechaTer_oper;
	public Long id_bodega_mec;
	public Long id_bodega_mecP;
	public Long id_bodega_oper;
	public Long id_equipo_mec;
	public Long id_equipo_mecP;
	public Long id_equipo_oper;
	public Long id_mantActividad;
	public Long id_mantActividadP;
	public Long id_mantActorPersonal;
	public List<Long> id_mantComponenteC[];
	public List<Long> id_mantComponenteP[];
	public Long id_mantEstadoEnObra;
	public Long id_mantEstadoEnTaller;
	public Long id_mantEstadoEnTallerP;
	public Long id_mantEstadoOperacional;
	public Long id_mantEstadoOperacionalP;
	public Long id_mantTipoActividad;
	public Long id_mantTipoActividadP;
	public Long id_mecanico;
	public Long id_operador;
	public Long id_tipoMantencion;
	public Long id_tipoPlan_mec;
	public Long id_tipoPlan_mecP;
	public Long id_userMada;
	public String lecturaIni_mec;
	public String lecturaIni_mecP;
	public String lecturaIni_oper;
	public String lecturaTer_mec;
	public String lecturaTer_mecP;
	public String lecturaTer_oper;
	public String observaciones_mec;
	public String observaciones_mecP;
	public String observaciones_oper;

	public FormMantencion(String cantidad_mec, String cantidad_mecP, String cantidad_oper, List<String>[] cantidadC,
			List<String>[] cantidadP, String comentario, String descTrabajo, String descTrabajoP, String docAnexo,
			String estadoFinal, String estadoFinalP, String fecha, String firmaAprobador, String firmaEjecutor,
			String horaIni_mec, String horaIni_mecP, String horaIni_oper, String horaTer_mec, String horaTer_mecP,
			String horaTer_oper, String fechaIni_mec, String fechaIni_mecP, String fechaIni_oper, String fechaTer_mec,
			String fechaTer_mecP, String fechaTer_oper, Long id_bodega_mec, Long id_bodega_mecP, Long id_bodega_oper,
			Long id_equipo_mec, Long id_equipo_mecP, Long id_equipo_oper, Long id_mantActividad, Long id_mantActividadP,
			Long id_mantActorPersonal, List<Long>[] id_mantComponenteC, List<Long>[] id_mantComponenteP,
			Long id_mantEstadoEnObra, Long id_mantEstadoEnTaller, Long id_mantEstadoEnTallerP,
			Long id_mantEstadoOperacional, Long id_mantEstadoOperacionalP, Long id_mantTipoActividad,
			Long id_mantTipoActividadP, Long id_mecanico, Long id_operador, Long id_tipoMantencion,
			Long id_tipoPlan_mec, Long id_tipoPlan_mecP, Long id_userMada, String lecturaIni_mec,
			String lecturaIni_mecP, String lecturaIni_oper, String lecturaTer_mec, String lecturaTer_mecP,
			String lecturaTer_oper, String observaciones_mec, String observaciones_mecP, String observaciones_oper) {
		super();
		this.cantidad_mec = cantidad_mec;
		this.cantidad_mecP = cantidad_mecP;
		this.cantidad_oper = cantidad_oper;
		this.cantidadC = cantidadC;
		this.cantidadP = cantidadP;
		this.comentario = comentario;
		this.descTrabajo = descTrabajo;
		this.descTrabajoP = descTrabajoP;
		this.docAnexo = docAnexo;
		this.estadoFinal = estadoFinal;
		this.estadoFinalP = estadoFinalP;
		this.fecha = fecha;
		this.firmaAprobador = firmaAprobador;
		this.firmaEjecutor = firmaEjecutor;
		this.horaIni_mec = horaIni_mec;
		this.horaIni_mecP = horaIni_mecP;
		this.horaIni_oper = horaIni_oper;
		this.horaTer_mec = horaTer_mec;
		this.horaTer_mecP = horaTer_mecP;
		this.horaTer_oper = horaTer_oper;
		this.fechaIni_mec = fechaIni_mec;
		this.fechaIni_mecP = fechaIni_mecP;
		this.fechaIni_oper = fechaIni_oper;
		this.fechaTer_mec = fechaTer_mec;
		this.fechaTer_mecP = fechaTer_mecP;
		this.fechaTer_oper = fechaTer_oper;
		this.id_bodega_mec = id_bodega_mec;
		this.id_bodega_mecP = id_bodega_mecP;
		this.id_bodega_oper = id_bodega_oper;
		this.id_equipo_mec = id_equipo_mec;
		this.id_equipo_mecP = id_equipo_mecP;
		this.id_equipo_oper = id_equipo_oper;
		this.id_mantActividad = id_mantActividad;
		this.id_mantActividadP = id_mantActividadP;
		this.id_mantActorPersonal = id_mantActorPersonal;
		this.id_mantComponenteC = id_mantComponenteC;
		this.id_mantComponenteP = id_mantComponenteP;
		this.id_mantEstadoEnObra = id_mantEstadoEnObra;
		this.id_mantEstadoEnTaller = id_mantEstadoEnTaller;
		this.id_mantEstadoEnTallerP = id_mantEstadoEnTallerP;
		this.id_mantEstadoOperacional = id_mantEstadoOperacional;
		this.id_mantEstadoOperacionalP = id_mantEstadoOperacionalP;
		this.id_mantTipoActividad = id_mantTipoActividad;
		this.id_mantTipoActividadP = id_mantTipoActividadP;
		this.id_mecanico = id_mecanico;
		this.id_operador = id_operador;
		this.id_tipoMantencion = id_tipoMantencion;
		this.id_tipoPlan_mec = id_tipoPlan_mec;
		this.id_tipoPlan_mecP = id_tipoPlan_mecP;
		this.id_userMada = id_userMada;
		this.lecturaIni_mec = lecturaIni_mec;
		this.lecturaIni_mecP = lecturaIni_mecP;
		this.lecturaIni_oper = lecturaIni_oper;
		this.lecturaTer_mec = lecturaTer_mec;
		this.lecturaTer_mecP = lecturaTer_mecP;
		this.lecturaTer_oper = lecturaTer_oper;
		this.observaciones_mec = observaciones_mec;
		this.observaciones_mecP = observaciones_mecP;
		this.observaciones_oper = observaciones_oper;
	}

	public FormMantencion() {
		super();
	}

	public static String pdfReportMantOperador(Connection con, String db, FormMantencion form, Long id_mantTransacReport,
			Map<String,String> mapDiccionario, Map<String,String> mapeoPermiso, Long id_usuarioMada){
		
//		File tmp = TempFile.createTempFile("tmp","null");
//		try {
//			String path = db+"/formatos/mantReportOperador.docx";
//			InputStream formato = Archivos.leerArchivo(path);
//			XWPFDocument doc = new XWPFDocument(formato);
//			formato.close();
//
//			XWPFTable table = null;
//			XWPFTableRow row = null;
//			XWPFTableCell cell = null;
//			String texto = "";
//
//			table = doc.getTables().get(0);
//
//
//			row=table.getRow(2);
//			cell=row.getCell(3);
//			texto = "";
//			setCelda(cell,"Arial",10,2,"000000",texto,false);
//			
//
//			//AGREGAR FIRMA
//			String firma = form.firmaEjecutor;
//			byte[] decodedStr = Base64.getDecoder().decode( firma );
//			row=table.getRow(8);
//			cell=row.getCell(0);
//			XWPFParagraph paragraph = cell.addParagraph();
//			XWPFRun run2 = paragraph.createRun();
//			run2.addPicture(new ByteArrayInputStream(decodedStr), XWPFDocument.PICTURE_TYPE_PNG, "firma", Units.toEMU(120), Units.toEMU(60));
//			//FIN AGREGA FIRMA
//
//			// Write the output to a file word
//			FileOutputStream fileOut = new FileOutputStream(tmp);
//			doc.write(fileOut);
//			fileOut.close();
//
//			// 1) Load DOCX into XWPFDocument
//			InputStream is = new FileInputStream(tmp);
//			XWPFDocument document = new XWPFDocument(is);
//			is.close();
//			// 2) Prepare Pdf options
//			PdfOptions options = PdfOptions.create().fontEncoding("iso-8859-15");
//			// 3) Convert XWPFDocument to Pdf
//			OutputStream out = new FileOutputStream(tmp);
//			PdfConverter.getInstance().convert(document, out, options);
//			out.close();
//
//			String archivoPdf = "Pdf_ReportMant_" + id_mantTransacReport + ".pdf";
//			path = db+"/"+archivoPdf;
//			Archivos.grabarArchivo(tmp,path);
//			MantTransacReport.modificaPorCampo(con, db, "reportPDF", id_mantTransacReport, archivoPdf);
//			
//			return(archivoPdf);
//		} catch (InvalidFormatException | IOException e) {
//			e.printStackTrace();
//		}
		return("0");
	}
	
	
	private static void setCelda (XWPFTableCell cell,String fontFamily,int fontSize,int alingH,String colorRGB,String text,boolean bold) {
		cell.removeParagraph(0);
		XWPFParagraph paragraph =null;
		if(text==null) text="--"; else if(text.trim().length()==0) text="--";
		paragraph = cell.addParagraph();
		if(alingH==3) {
			paragraph.setAlignment(ParagraphAlignment.RIGHT);
		}else if (alingH==2) {
			paragraph.setAlignment(ParagraphAlignment.CENTER);
		}else if (alingH==1) {
			paragraph.setAlignment(ParagraphAlignment.LEFT);
		}else {
			paragraph.setAlignment(ParagraphAlignment.BOTH);
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














