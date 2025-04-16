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

import models.tables.BodegaEmpresa;
import models.tables.Equipo;
import models.tables.MantActividad;
import models.tables.MantComponente;
import models.tables.MantEstadoEnObra;
import models.tables.MantEstadoEnTaller;
import models.tables.MantEstadoOperacional;
import models.tables.MantItemIntervenido;
import models.tables.MantOperadorMecanico;
import models.tables.MantTipoActividad;
import models.tables.MantTransacComponentes;
import models.tables.MantTransacReport;
import models.tables.TipoMantencion;
import models.tables.TipoPlan;
import models.tables.Usuario;
import models.utilities.Archivos;
import models.utilities.DecimalFormato;
import models.utilities.Fechas;

public class FormMantencion {
	public String cantidad_mec;
	public String cantidad_mecP;
	public String cantidad_oper;
	public List<String> cantidadC;
	public List<String> cantidadP;
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
	public Long id_mantItemIntervenido;
	public Long id_mantItemIntervenidoP;
	public List<Long> id_mantComponenteC;
	public List<Long> id_mantComponenteP;
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

	public Long id_unidadMantencion;

	public FormMantencion(String cantidad_mec, String cantidad_mecP, String cantidad_oper, List<String> cantidadC,
			List<String> cantidadP, String comentario, String descTrabajo, String descTrabajoP, String docAnexo,
			String estadoFinal, String estadoFinalP, String fecha, String firmaAprobador, String firmaEjecutor,
			String horaIni_mec, String horaIni_mecP, String horaIni_oper, String horaTer_mec, String horaTer_mecP,
			String horaTer_oper, String fechaIni_mec, String fechaIni_mecP, String fechaIni_oper, String fechaTer_mec,
			String fechaTer_mecP, String fechaTer_oper, Long id_bodega_mec, Long id_bodega_mecP, Long id_bodega_oper,
			Long id_equipo_mec, Long id_equipo_mecP, Long id_equipo_oper, Long id_mantActividad, Long id_mantActividadP,
			Long id_mantActorPersonal, Long id_mantItemIntervenido, Long id_mantItemIntervenidoP,
			List<Long> id_mantComponenteC, List<Long> id_mantComponenteP, Long id_mantEstadoEnObra,
			Long id_mantEstadoEnTaller, Long id_mantEstadoEnTallerP, Long id_mantEstadoOperacional,
			Long id_mantEstadoOperacionalP, Long id_mantTipoActividad, Long id_mantTipoActividadP, Long id_mecanico,
			Long id_operador, Long id_tipoMantencion, Long id_tipoPlan_mec, Long id_tipoPlan_mecP, Long id_userMada,
			String lecturaIni_mec, String lecturaIni_mecP, String lecturaIni_oper, String lecturaTer_mec,
			String lecturaTer_mecP, String lecturaTer_oper, String observaciones_mec, String observaciones_mecP,
			String observaciones_oper,Long id_unidadMantencion) {
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
		this.id_mantItemIntervenido = id_mantItemIntervenido;
		this.id_mantItemIntervenidoP = id_mantItemIntervenidoP;
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
		this.id_unidadMantencion = id_unidadMantencion;
	}

	public FormMantencion() {
		super();
	}

	public static String pdfReportMantOperador(Connection con, String db, MantTransacReport mantTransacReport){
		
		File tmp = TempFile.createTempFile("tmp","null");
		try {
			String path = db+"/formatos/reportMantOperador.docx";
			InputStream formato = Archivos.leerArchivo(path);
			XWPFDocument doc = new XWPFDocument(formato);
			formato.close();

			XWPFTable table = null;
			XWPFTableRow row = null;
			XWPFTableCell cell = null;
			
			table = doc.getTables().get(0);
			row = table.getRow(1);
			cell = row.getCell(2);
			String auxTexto = "NRO: " + mantTransacReport.getId();
			setCelda(cell,"Arial",10,2,"000000",auxTexto,false);
			row = table.getRow(2);
			cell = row.getCell(2);
			auxTexto = Fechas.DDMMAA(mantTransacReport.getFecha());
			setCelda(cell,"Arial",10,2,"000000",auxTexto,false);
			if(mantTransacReport.getId_userMada() > 0) {
				Usuario userMada = Usuario.findXIdUser(con, db, mantTransacReport.getId_userMada());
				row = table.getRow(3);
				cell = row.getCell(2);
				auxTexto = cell.getText() + "\r\nUserMada: " + userMada.getUserName().toUpperCase();
				setCelda(cell,"Arial",10,2,"000000",auxTexto,false);
			}
			
			table = doc.getTables().get(1);
			row = table.getRow(0);
			cell = row.getCell(1);
			MantOperadorMecanico operador = MantOperadorMecanico.findXIdUser(con, db, mantTransacReport.getId_mantOperador());
			auxTexto = operador.getNombre();
			setCelda(cell,"Arial",10,1,"000000",auxTexto.toUpperCase(),false);
			row = table.getRow(1);
			cell = row.getCell(1);
			auxTexto = "INTERNO";
			if(operador.getId_mantTipoPersonal() > 1) {
				auxTexto = "EXTERNO";
			}
			setCelda(cell,"Arial",10,1,"000000",auxTexto.toUpperCase(),false);
			row = table.getRow(2);
			cell = row.getCell(1);
			Equipo equipo = Equipo.find(con, db, mantTransacReport.getId_equipo());
			auxTexto = equipo.getCodigo() + " - " + equipo.getNombre();
			setCelda(cell,"Arial",10,1,"000000",auxTexto.toUpperCase(),false);
			row = table.getRow(3);
			cell = row.getCell(1);
			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, db, mantTransacReport.getId_bodega());
			auxTexto = bodega.getNombre();
			setCelda(cell,"Arial",10,1,"000000",auxTexto.toUpperCase(),false);
			row = table.getRow(4);
			cell = row.getCell(1);
			auxTexto = bodega.getNombreTipoBodega();
			setCelda(cell,"Arial",10,1,"000000",auxTexto.toUpperCase(),false);
			row = table.getRow(5);
			cell = row.getCell(1);
			MantEstadoEnObra mantEstado = MantEstadoEnObra.find(con, db, mantTransacReport.getId_mantEstadoEnObra());
			auxTexto = mantEstado.getNombre();
			setCelda(cell,"Arial",10,1,"000000",auxTexto.toUpperCase(),false);
			
			table = doc.getTables().get(2);
			row = table.getRow(0);
			cell = row.getCell(1);
			auxTexto = mantTransacReport.getHoraIni().substring(0,5);
			setCelda(cell,"Arial",10,2,"000000",auxTexto,false);
			cell = row.getCell(3);
			auxTexto = mantTransacReport.getHoraFin().substring(0,5);
			setCelda(cell,"Arial",10,2,"000000",auxTexto,false);
			cell = row.getCell(5);
			auxTexto = DecimalFormato.formato(mantTransacReport.getHoraDif(),(long)2);
			setCelda(cell,"Arial",10,2,"000000",auxTexto,false);
			row = table.getRow(1);
			cell = row.getCell(1);
			auxTexto = DecimalFormato.formato(mantTransacReport.getLectAnterior(),(long)2);
			setCelda(cell,"Arial",10,2,"000000",auxTexto,false);
			cell = row.getCell(3);
			auxTexto = DecimalFormato.formato(mantTransacReport.getLectActual(),(long)2);
			setCelda(cell,"Arial",10,2,"000000",auxTexto,false);
			cell = row.getCell(5);
			auxTexto = DecimalFormato.formato(mantTransacReport.getLectDif(),(long)2);
			setCelda(cell,"Arial",10,2,"000000",auxTexto,false);
			
			table = doc.getTables().get(3);
			row = table.getRow(0);
			cell = row.getCell(1);
			auxTexto = mantTransacReport.getComentario();
			setCelda(cell,"Arial",10,1,"000000",auxTexto,false);
			row = table.getRow(1);
			cell = row.getCell(1);
			auxTexto = mantTransacReport.getObservaciones();
			setCelda(cell,"Arial",10,1,"000000",auxTexto,false);

			
			table = doc.getTables().get(4);
			//AGREGAR FIRMA OPERADOR
			String firma = mantTransacReport.getFirmaPDFoperador();
			byte[] decodedStr = Base64.getDecoder().decode( firma );
			row = table.getRow(1);
			cell=row.getCell(0);
			XWPFParagraph paragraph = cell.addParagraph();
			XWPFRun run2 = paragraph.createRun();
			run2.addPicture(new ByteArrayInputStream(decodedStr), XWPFDocument.PICTURE_TYPE_PNG, "firma", Units.toEMU(120), Units.toEMU(60));
			//AGREGAR FIRMA SUPERVISOR
			firma = mantTransacReport.getFirmaPDFautorizador();
			decodedStr = Base64.getDecoder().decode( firma );
			cell=row.getCell(2);
			paragraph = cell.addParagraph();
			run2 = paragraph.createRun();
			run2.addPicture(new ByteArrayInputStream(decodedStr), XWPFDocument.PICTURE_TYPE_PNG, "firma", Units.toEMU(120), Units.toEMU(60));
			//FIN AGREGA FIRMAS
			
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

			String archivoPdf = "Pdf_ReportMant_" + mantTransacReport.getId() + ".pdf";
			path = db+"/"+archivoPdf;
			Archivos.grabarArchivo(tmp,path);
			MantTransacReport.modificaPorCampo(con, db, "reportPDF", mantTransacReport.getId(), archivoPdf);
			
			return(archivoPdf);
		} catch (InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
		return("0");
	}
	
	public static String pdfReportMantMecanico(Connection con, String db, MantTransacReport mantTransacReport){
		
		File tmp = TempFile.createTempFile("tmp","null");
		try {
			String path = db+"/formatos/reportMantMecanico.docx";
			InputStream formato = Archivos.leerArchivo(path);
			XWPFDocument doc = new XWPFDocument(formato);
			formato.close();

			XWPFTable table = null;
			XWPFTableRow row = null;
			XWPFTableCell cell = null;
			
			table = doc.getTables().get(0);
			row = table.getRow(1);
			cell = row.getCell(2);
			String auxTexto = "NRO: " + mantTransacReport.getId();
			setCelda(cell,"Arial",10,2,"000000",auxTexto,false);
			row = table.getRow(2);
			cell = row.getCell(2);
			auxTexto = Fechas.DDMMAA(mantTransacReport.getFecha());
			setCelda(cell,"Arial",10,2,"000000",auxTexto,false);
			if(mantTransacReport.getId_userMada() > 0) {
				Usuario userMada = Usuario.findXIdUser(con, db, mantTransacReport.getId_userMada());
				row = table.getRow(3);
				cell = row.getCell(2);
				auxTexto = cell.getText() + "\r\nUserMada: " + userMada.getUserName().toUpperCase();
				setCelda(cell,"Arial",10,2,"000000",auxTexto,false);
			}
			
			table = doc.getTables().get(1);
			row = table.getRow(0);
			cell = row.getCell(1);
			MantOperadorMecanico operador = MantOperadorMecanico.findXIdUser(con, db, mantTransacReport.getId_mantMecanico());
			auxTexto = operador.getNombre();
			setCelda(cell,"Arial",10,1,"000000",auxTexto.toUpperCase(),false);
			row = table.getRow(1);
			cell = row.getCell(1);
			auxTexto = "INTERNO";
			if(operador.getId_mantTipoPersonal() > 1) {
				auxTexto = "EXTERNO";
			}
			setCelda(cell,"Arial",10,1,"000000",auxTexto.toUpperCase(),false);
			row = table.getRow(2);
			cell = row.getCell(1);
			TipoMantencion tipoMantencion = TipoMantencion.find(con, db, mantTransacReport.getId_tipoMantencion());
			auxTexto = tipoMantencion.getNombre();
			setCelda(cell,"Arial",10,1,"000000",auxTexto.toUpperCase(),false);
			row = table.getRow(3);
			cell = row.getCell(1);
			Equipo equipo = Equipo.find(con, db, mantTransacReport.getId_equipo());
			auxTexto = equipo.getCodigo() + " - " + equipo.getNombre();
			setCelda(cell,"Arial",10,1,"000000",auxTexto.toUpperCase(),false);
			row = table.getRow(4);
			cell = row.getCell(1);
			TipoPlan tipoPlan = TipoPlan.find(con, db, mantTransacReport.getId_tipoPlan());
			auxTexto = "NO APLICA";
			if(tipoPlan != null) {
				auxTexto = tipoPlan.getNombre();
			}
			setCelda(cell,"Arial",10,1,"000000",auxTexto.toUpperCase(),false);
			row = table.getRow(5);
			cell = row.getCell(1);
			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, db, mantTransacReport.getId_bodega());
			auxTexto = bodega.getNombre();
			setCelda(cell,"Arial",10,1,"000000",auxTexto.toUpperCase(),false);
			row = table.getRow(6);
			cell = row.getCell(1);
			auxTexto = bodega.getNombreTipoBodega();
			setCelda(cell,"Arial",10,1,"000000",auxTexto.toUpperCase(),false);
			
			row = table.getRow(7);
			cell = row.getCell(1);
			MantEstadoOperacional mantOperacional = MantEstadoOperacional.find(con, db, mantTransacReport.getId_mantEstadoOperacional());
			auxTexto = mantOperacional.getNombre();
			setCelda(cell,"Arial",10,1,"000000",auxTexto.toUpperCase(),false);
			row = table.getRow(8);
			cell = row.getCell(1);
			MantEstadoEnTaller mantEnTaller = MantEstadoEnTaller.find(con, db, mantTransacReport.getId_mantEstadoEnTaller());
			auxTexto = mantEnTaller.getNombre();
			setCelda(cell,"Arial",10,1,"000000",auxTexto.toUpperCase(),false);
			row = table.getRow(9);
			cell = row.getCell(1);
			MantActividad mantActividad = MantActividad.find(con, db, mantTransacReport.getId_mantActividad());
			auxTexto = mantActividad.getNombre();
			setCelda(cell,"Arial",10,1,"000000",auxTexto.toUpperCase(),false);
			row = table.getRow(10);
			cell = row.getCell(1);
			MantTipoActividad mantTipoActividad = MantTipoActividad.find(con, db, mantTransacReport.getId_mantTipoActividad());
			auxTexto = mantTipoActividad.getNombre();
			setCelda(cell,"Arial",10,1,"000000",auxTexto.toUpperCase(),false);
			row = table.getRow(11);
			cell = row.getCell(1);
			MantItemIntervenido mantItemIntervenido = MantItemIntervenido.find(con, db, mantTransacReport.getId_mantItemIntervenido());
			auxTexto = mantItemIntervenido.getNombre();
			setCelda(cell,"Arial",10,1,"000000",auxTexto.toUpperCase(),false);
			
			
			table = doc.getTables().get(2);
			XWPFTableRow oldRow = table.getRow(1);
			CTRow ctrow = null;
			try {
				ctrow = CTRow.Factory.parse(oldRow.getCtRow().newInputStream());
			} catch (XmlException e) {
				e.printStackTrace();
			}
			XWPFTableRow newRow = new XWPFTableRow(ctrow, table);
			List<MantTransacComponentes> listComp = MantTransacComponentes.allPorIdMantTransacReport(con, db, mantTransacReport.getId());
			for(int i=0; i<listComp.size(); i++) {
				row = table.getRow(1 + i);
				cell = row.getCell(0);
				MantComponente mantComponente = MantComponente.find(con, db, listComp.get(i).getId_mantComponente());
				auxTexto = mantComponente.getNombre();
				setCelda(cell,"Arial",10,1,"000000",auxTexto.toUpperCase(),false);
				cell = row.getCell(1);
				auxTexto = DecimalFormato.formato(listComp.get(i).getCantidad(),(long)2);
				setCelda(cell,"Arial",10,1,"000000",auxTexto.toUpperCase(),false);
				table.addRow(newRow, 2);
			}
			
			table = doc.getTables().get(3);
			row = table.getRow(0);
			cell = row.getCell(1);
			auxTexto = Fechas.DDMMAA(mantTransacReport.getFechaIni());
			setCelda(cell,"Arial",10,2,"000000",auxTexto,false);
			cell = row.getCell(3);
			auxTexto = Fechas.DDMMAA(mantTransacReport.getFechaFin());
			setCelda(cell,"Arial",10,2,"000000",auxTexto,false);
			cell = row.getCell(5);
			auxTexto = DecimalFormato.formato(mantTransacReport.getFechaDif(),(long)0);
			setCelda(cell,"Arial",10,2,"000000",auxTexto,false);
			row = table.getRow(1);
			cell = row.getCell(1);
			auxTexto = mantTransacReport.getHoraIni().substring(0,5);
			setCelda(cell,"Arial",10,2,"000000",auxTexto,false);
			cell = row.getCell(3);
			auxTexto = mantTransacReport.getHoraFin().substring(0,5);
			setCelda(cell,"Arial",10,2,"000000",auxTexto,false);
			cell = row.getCell(5);
			auxTexto = DecimalFormato.formato(mantTransacReport.getHoraDif(),(long)2);
			setCelda(cell,"Arial",10,2,"000000",auxTexto,false);
			row = table.getRow(2);
			cell = row.getCell(1);
			auxTexto = DecimalFormato.formato(mantTransacReport.getLectAnterior(),(long)2);
			setCelda(cell,"Arial",10,2,"000000",auxTexto,false);
			cell = row.getCell(3);
			auxTexto = DecimalFormato.formato(mantTransacReport.getLectActual(),(long)2);
			setCelda(cell,"Arial",10,2,"000000",auxTexto,false);
			cell = row.getCell(5);
			auxTexto = DecimalFormato.formato(mantTransacReport.getLectDif(),(long)2);
			setCelda(cell,"Arial",10,2,"000000",auxTexto,false);
			
			table = doc.getTables().get(4);
			row = table.getRow(0);
			cell = row.getCell(1);
			auxTexto = mantTransacReport.getDescTrabajo();
			setCelda(cell,"Arial",10,1,"000000",auxTexto,false);
			row = table.getRow(1);
			cell = row.getCell(1);
			auxTexto = mantTransacReport.getEstadoFinal();
			setCelda(cell,"Arial",10,1,"000000",auxTexto,false);
			row = table.getRow(2);
			cell = row.getCell(1);
			auxTexto = mantTransacReport.getObservaciones();
			setCelda(cell,"Arial",10,1,"000000",auxTexto,false);

			table = doc.getTables().get(5);
			//AGREGAR FIRMA OPERADOR
			String firma = mantTransacReport.getFirmaPDFoperador();
			byte[] decodedStr = Base64.getDecoder().decode( firma );
			row = table.getRow(1);
			cell=row.getCell(0);
			XWPFParagraph paragraph = cell.addParagraph();
			XWPFRun run2 = paragraph.createRun();
			run2.addPicture(new ByteArrayInputStream(decodedStr), XWPFDocument.PICTURE_TYPE_PNG, "firma", Units.toEMU(120), Units.toEMU(60));
			//AGREGAR FIRMA SUPERVISOR
			firma = mantTransacReport.getFirmaPDFautorizador();
			decodedStr = Base64.getDecoder().decode( firma );
			cell=row.getCell(2);
			paragraph = cell.addParagraph();
			run2 = paragraph.createRun();
			run2.addPicture(new ByteArrayInputStream(decodedStr), XWPFDocument.PICTURE_TYPE_PNG, "firma", Units.toEMU(120), Units.toEMU(60));
			//FIN AGREGA FIRMAS
			
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

			String archivoPdf = "Pdf_ReportMantMecanico_" + mantTransacReport.getId() + ".pdf";
			path = db+"/"+archivoPdf;
			Archivos.grabarArchivo(tmp,path);
			MantTransacReport.modificaPorCampo(con, db, "reportPDF", mantTransacReport.getId(), archivoPdf);
			
			return(archivoPdf);
		} catch (InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
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














