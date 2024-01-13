package models.forms;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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

import models.tables.BodegaEmpresa;
import models.tables.Cliente;
import models.tables.CotiOdo;
import models.tables.CotiOdoDetalle;
import models.tables.EmisorTributario;
import models.tables.Proyecto;
import models.tables.Servicio;
import models.utilities.Archivos;
import models.utilities.DecimalFormato;
import models.utilities.Fechas;
import play.libs.Files.TemporaryFile;
import play.mvc.Http;

public class FormCotizaOdo {
	public Long id_cotiOdo;
	public Long numeroCoti;
	public String fechaCoti;
	public Long id_cliente;
	public Long id_proyecto;
	public Long id_bodegaEmpresa;
	
	
	public String rutCliente;
	public String nickCliente;
	public String nickProyecto;
	public String nombreBodega;
	public String observaciones;
	
	public List<Long> id_servicio;
	public List<Long> id_moneda;
	public List<Long> aplicaMinimo;
	
	public List<String> cantidad;
	public List<String> precio;
	public List<String> cantidadMinimo;
	public List<String> precioAdicional;
	
	public String dctoOdo;
	
	
	public String cotiOdoPDF;
	
	public String estadoCotizacion;
	public String fechaConfirmada;
	

	public FormCotizaOdo(Long id_cotizacion, Long numeroCoti, String fechaCoti, Long id_cliente, Long id_proyecto,
			Long id_bodegaEmpresa, String rutCliente, String nickCliente, String nickProyecto, String nombreBodega,
			String observaciones, List<Long> id_servicio, List<Long> id_moneda, List<Long> aplicaMinimo,
			List<String> cantidad, List<String> precio, List<String> cantidadMinimo, List<String> precioAdicional,
			String dctoOdo, String cotiOdoPDF, String estadoCotizacion, String fechaConfirmada) {
		super();
		this.id_cotiOdo = id_cotizacion;
		this.numeroCoti = numeroCoti;
		this.fechaCoti = fechaCoti;
		this.id_cliente = id_cliente;
		this.id_proyecto = id_proyecto;
		this.id_bodegaEmpresa = id_bodegaEmpresa;
		this.rutCliente = rutCliente;
		this.nickCliente = nickCliente;
		this.nickProyecto = nickProyecto;
		this.nombreBodega = nombreBodega;
		this.observaciones = observaciones;
		this.id_servicio = id_servicio;
		this.id_moneda = id_moneda;
		this.aplicaMinimo = aplicaMinimo;
		this.cantidad = cantidad;
		this.precio = precio;
		this.cantidadMinimo = cantidadMinimo;
		this.precioAdicional = precioAdicional;
		this.dctoOdo = dctoOdo;
		this.cotiOdoPDF = cotiOdoPDF;
		this.estadoCotizacion = estadoCotizacion;
		this.fechaConfirmada = fechaConfirmada;
	}

	public FormCotizaOdo(Long numeroCoti, String fecha) {
		super();
		this.id_cotiOdo = (long)0;
		this.numeroCoti = numeroCoti;
		this.fechaCoti = fecha;
		this.id_cliente = (long)0;
		this.id_proyecto = (long)0;
		this.id_bodegaEmpresa = (long)0;
		this.rutCliente = "";
		this.nickCliente = "";
		this.nickProyecto = "";
		this.nombreBodega = "";
		this.observaciones = "";
		this.dctoOdo = "0";
	}
	
	public FormCotizaOdo(CotiOdo cotiOdo, String fecha, String rutCliente, String nomCliente, String nomProyecto) {
		super();
		this.id_cotiOdo = (long)0;
		this.numeroCoti = cotiOdo.getNumero();
		this.fechaCoti = fecha;
		this.id_cliente = cotiOdo.getId_cliente();
		this.id_proyecto = cotiOdo.getId_proyecto();
		this.id_bodegaEmpresa = (long)0;
		this.rutCliente = rutCliente;
		this.nickCliente = nomCliente;
		this.nickProyecto = nomProyecto;
		this.nombreBodega = "";
		this.observaciones = cotiOdo.getObservaciones();
		this.cotiOdoPDF = cotiOdo.getCotiOdoPDF();
		this.dctoOdo = cotiOdo.getDctoOdo().toString();
	}
	
	public FormCotizaOdo(Long numeroCoti, String fecha, Long id_cliente, Long id_proyecto, Long id_bodegaEmpresa, String rutCliente, String nickCliente, String nickProyecto,
			String nombreBodega) {
		super();
		this.id_cotiOdo = (long)0;
		this.numeroCoti = numeroCoti;
		this.fechaCoti = fecha;
		this.id_cliente = id_cliente;
		this.id_proyecto = id_proyecto;
		this.id_bodegaEmpresa = id_bodegaEmpresa;
		this.rutCliente = rutCliente;
		this.nickCliente = nickCliente;
		this.nickProyecto = nickProyecto;
		this.nombreBodega = nombreBodega;
		this.observaciones = "";
		this.dctoOdo = "0";
	}

	public FormCotizaOdo() {
		super();
	}
	

	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	
	public static List<List<String>> listServiciosConValoresCoti(Connection con, String db, Long id_cotiOdo) {
		List<List<String>> lista = new ArrayList<List<String>>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select "
							+ " servicio.id, "
							+ " ifnull(claseServicio.nombre,''), "
							+ " servicio.codigo, "
							+ " servicio.nombre, "
							+ " ifnull(unidadServicio.nombre,''), "
							+ " servicio.precio, "
							+ " ifnull(moneda.nickName,''), "
							+ " ifnull(moneda.numeroDecimales,0),"
							+ " ifnull(moneda.id,'1') "
							+ " from `"+db+"`.servicio "
							+ " left join `"+db+"`.claseServicio on claseServicio.id = servicio.id_claseServicio"
							+ " left join `"+db+"`.unidadServicio on unidadServicio.id = servicio.id_unidadServicio "
							+ " left join `"+db+"`.moneda on moneda.id = servicio.id_moneda "
							+ " order by servicio.nombre; ");
			ResultSet rs = smt.executeQuery();
			
			Map<Long,List<Double>> mapCotiDetalle = CotiOdo.mapDetalleCotiOdo(con, db, id_cotiOdo);
			
			while (rs.next()) {
				Long numDec =  rs.getLong(8);
				List<String> aux = new ArrayList<String>();
				aux.add(rs.getString(1));  									//0 id_servicio
				aux.add(rs.getString(2));  									//1 clase
				aux.add(rs.getString(3));  									//2 codigo
				aux.add(rs.getString(4));  									//3 servicio
				aux.add(rs.getString(5));  									//4 unidadServicio
				aux.add("0.00");  											//5 cantidad
				aux.add(rs.getString(7));									//6 moneda.nickName
				aux.add(DecimalFormato.formato(rs.getDouble(6), numDec));  	//7 servicio.precio
				aux.add(DecimalFormato.formato((double)0, numDec));  		//8 total
				aux.add("0");  												//9 aplicaMinimo
				aux.add("0.00");  											//10 cantidad minimo
				aux.add(DecimalFormato.formato((double)0, numDec));  		//11 precio adicional
				aux.add(rs.getString(8));									//12 numDec
				aux.add(rs.getString(9));									//13 id_moneda
				
				//DETERMINA EL PRECIO CANTIDAD MINIMO  DE ARRIENDO A APLICAR EN LA COTIZACION:
				List<Double> detCoti = mapCotiDetalle.get(rs.getLong(1));
				if(detCoti!=null) {
					Double total = detCoti.get(0)*detCoti.get(1);
					aux.set(5, DecimalFormato.formato(detCoti.get(0),(long)2));		//5 cantidad
					aux.set(7, DecimalFormato.formato(detCoti.get(1),numDec));		//7 servicio.precio
					aux.set(8, DecimalFormato.formato(total, numDec));  			//8 total
					aux.set(9, DecimalFormato.formato(detCoti.get(2),(long)0));		//9 aplicaMinimo
					aux.set(10, DecimalFormato.formato(detCoti.get(3),(long)2));	//10 cantidad minimo
					aux.set(11, DecimalFormato.formato(detCoti.get(4),numDec));		//11 precio adicional
				}
				// FIN DETERMINA PRECIO ARRIENDO
					
				lista.add(aux);
			}
			
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}

	
	public static boolean create (Connection con, String db, FormCotizaOdo form, Http.MultipartFormData.FilePart<TemporaryFile> docAdjunto) {
		boolean flag = false;
		CotiOdo cotiOdo = new CotiOdo(form);
		Long id_cotiOdo = CotiOdo.create(con, db, cotiOdo);
		if((long)id_cotiOdo > (long)0) {
			String detalle = "";
			for(int i=0; form.id_servicio!=null && i<form.id_servicio.size(); i++) {
				Double cantidad = Double.parseDouble(form.cantidad.get(i).replaceAll(",", ""));
				if((double) cantidad > (double) 0) {
					detalle += "('"+id_cotiOdo+"','"+
							form.id_servicio.get(i)+"','"+
							form.id_moneda.get(i)+"','"+
							form.precio.get(i).replaceAll(",", "")+"','"+
							form.cantidad.get(i).replaceAll(",", "")+"','"+
							form.aplicaMinimo.get(i)+"','"+
							form.cantidadMinimo.get(i).replaceAll(",", "")+"','"+
							form.precioAdicional.get(i).replaceAll(",", "")+"'),";
				}
			}
			if(form.id_servicio!=null) {
				detalle = detalle.substring(0,detalle.length()-1);
			}
			if(detalle.length()>2) {
				if(!CotiOdoDetalle.create(con, db, detalle)) {
					CotiOdo.delete(con, db, id_cotiOdo);
				}else {
					if (docAdjunto != null) {
						String nombreArchivoSinExtencion = "Doc_CotiOdo_" + id_cotiOdo;
						String nombreArchivoConExtencion = Archivos.grabarArchivos(docAdjunto, db, nombreArchivoSinExtencion);
						CotiOdo.modifyXCampo(con, db, "cotiOdoPDF", nombreArchivoConExtencion, id_cotiOdo);
					}
					flag = true;
				}
			}
		}
		return(flag);
	}
	
	public static boolean update (Connection con, String db, FormCotizaOdo form, Http.MultipartFormData.FilePart<TemporaryFile> docAdjunto) {
		boolean flag = false;
		CotiOdo cotiOdo = new CotiOdo(form);
		cotiOdo.setId(form.id_cotiOdo);
		if(CotiOdo.update(con, db, cotiOdo)) {
			String detalle = "";
			for(int i=0; form.id_servicio!=null && i<form.id_servicio.size(); i++) {
				Double cantidad = Double.parseDouble(form.cantidad.get(i).replaceAll(",", ""));
				if((double) cantidad > (double) 0) {
					
					detalle += "('"+form.id_cotiOdo+"','"+
							form.id_servicio.get(i)+"','"+
							form.id_moneda.get(i)+"','"+
							form.precio.get(i).replaceAll(",", "")+"','"+
							form.cantidad.get(i).replaceAll(",", "")+"','"+
							form.aplicaMinimo.get(i)+"','"+
							form.cantidadMinimo.get(i).replaceAll(",", "")+"','"+
							form.precioAdicional.get(i).replaceAll(",", "")+"'),";
				}
			}
			if(form.id_servicio!=null) {
				detalle = detalle.substring(0,detalle.length()-1);
			}
			if(detalle.length()>2) {
				if(CotiOdoDetalle.delete(con, db, form.id_cotiOdo)) {
					CotiOdoDetalle.create(con, db, detalle);
					String path = "0";
					if (docAdjunto != null) {
						String nombreArchivoSinExtencion = "Doc_CotiOdo_" + form.id_cotiOdo;
						path = Archivos.grabarArchivos(docAdjunto, db, nombreArchivoSinExtencion);
						CotiOdo.modifyXCampo(con, db, "cotiOdoPDF", path, form.id_cotiOdo);
					}
					flag = true;
				};
			}
		}
		return(flag);
	}
	
	public static String generaPdfCotiOdoVenta(Connection con, String db, Long id_cotiOdo, Map<String,String> mapDiccionario, Map<String,String> mapPermiso){
		
		CotiOdo cotiOdo = CotiOdo.find(con, db,id_cotiOdo);
		List<List<String>> listadoServicios = FormCotizaOdo.listServiciosConValoresCoti(con, db, id_cotiOdo);
		
		Cliente cliente = Cliente.find(con, db, cotiOdo.getId_cliente());
		String nomCliente = "";
		String rutCliente = "";
		if(cliente!=null) {
			nomCliente = cliente.getNickName();
			rutCliente = cliente.getRut();
		}
		Proyecto proyecto = Proyecto.find(con, db, cotiOdo.getId_proyecto());
		String nomProyecto = "";
		if(proyecto!=null) {
			nomProyecto = proyecto.getNickName();
		}
		
		EmisorTributario emisorTributario = models.tables.EmisorTributario.find(con, db);
		 
		
		File tmp = TempFile.createTempFile("tmp","null");
		
		
		try {
			String path = db + "/formatos/cotizaVentaOdo.docx";
			InputStream formato = Archivos.leerArchivo(path);
			XWPFDocument doc = new XWPFDocument(formato);
			formato.close();
		
			XWPFTable table = null;
			XWPFTableRow row = null;
			XWPFTableCell cell = null;
			
			table = doc.getTables().get(0);
			
			row=table.getRow(1);
			cell=row.getCell(2);
			setCelda(cell,"Arial",10,2,"2b5079","Fecha: "+Fechas.DDMMAA(cotiOdo.fecha),false);
			
			row=table.getRow(2);
			cell=row.getCell(2);
			setCelda(cell,"Arial",10,2,"2b5079","Numero: "+cotiOdo.numero.toString(),false);
			
			table= doc.getTables().get(1);
			
			cell=table.getRow(0).getCell(1);
			setCelda(cell,"Arial",10,1,"2b5079",nomCliente,false);
			
			cell=table.getRow(1).getCell(1);
			setCelda(cell,"Arial",10,1,"2b5079",rutCliente,false);
			
			cell=table.getRow(1).getCell(3);
			setCelda(cell,"Arial",10,1,"2b5079",nomProyecto,false);
			
			
			if(table.getNumberOfRows()<5) {
				cell=table.getRow(2).getCell(0);
				setCelda(cell,"Arial",10,1,"2b5079","",false);
			}else {
				cell=table.getRow(2).getCell(1);
				setCelda(cell,"Arial",10,1,"2b5079",cliente.giro,false);
				
				cell=table.getRow(3).getCell(1);
				setCelda(cell,"Arial",10,1,"2b5079",cliente.direccion,false);
				
				cell=table.getRow(4).getCell(1);
				setCelda(cell,"Arial",10,1,"2b5079",cliente.contactoFactura + "   TELEFONO: " + cliente.fonoContacto + "   EMAIL: " + cliente.mailFactura,false);
			}
			
			
			Double total = (double)0;
			Double cant = (double)0;
			Long numDec = (long)0;
			
			int contFilasTabla = 0;
			table = doc.getTables().get(2);
			table.createRow();
			
			
			for(int i=0; i<listadoServicios.size(); i++) {
				
				Double auxCant = Double.parseDouble(listadoServicios.get(i).get(5).replaceAll(",", ""));
				
				if(auxCant > 0) {
					Double auxTotal = Double.parseDouble(listadoServicios.get(i).get(8).replaceAll(",", ""));
					cant += auxCant;
					total += auxTotal;
					
					String auxAplicaMinimo = "SI";
					if(listadoServicios.get(i).get(9).equals("0")){
						auxAplicaMinimo = "NO";
					}
						
					numDec = Long.parseLong(listadoServicios.get(i).get(12).replaceAll(",", ""));
					
					contFilasTabla++;
					
					String codigo = listadoServicios.get(i).get(2);
					String servicio = listadoServicios.get(i).get(3);
					String unidad = listadoServicios.get(i).get(4);
					String cantidad = listadoServicios.get(i).get(5);
					String moneda = listadoServicios.get(i).get(6);
					String precio = listadoServicios.get(i).get(7);
					String ptotal = listadoServicios.get(i).get(8);
					String aplicaMinimo = auxAplicaMinimo;
					String cantMinimo = listadoServicios.get(i).get(10);
					String precioAdicional = listadoServicios.get(i).get(11);
						
					row = table.getRow(contFilasTabla);
					cell=row.getCell(0);setCelda(cell,"Arial",8,1,"2b5079",codigo,false);
					cell=row.getCell(1);setCelda(cell,"Arial",8,1,"2b5079",servicio,false);
					cell=row.getCell(2);setCelda(cell,"Arial",8,2,"2b5079",unidad,false);
					cell=row.getCell(3);setCelda(cell,"Arial",8,3,"2b5079",cantidad,false);
					cell=row.getCell(4);setCelda(cell,"Arial",8,2,"2b5079",moneda,false);
					cell=row.getCell(5);setCelda(cell,"Arial",8,3,"2b5079",precio,false);
					cell=row.getCell(6);setCelda(cell,"Arial",8,3,"2b5079",ptotal,false);
					cell=row.getCell(7);setCelda(cell,"Arial",8,3,"2b5079",aplicaMinimo,false);
					cell=row.getCell(8);setCelda(cell,"Arial",8,3,"2b5079",cantMinimo,false);
					cell=row.getCell(9);setCelda(cell,"Arial",8,3,"2b5079",precioAdicional,false);
					table.createRow();
				}
			}
			
			
	        
			//table.createRow();
			row = table.getRow(contFilasTabla+2);
			
			cell=row.getCell(1);setCelda(cell,"Arial",8,1,"2b5079","TOTALES",true);
			cell=row.getCell(3);setCelda(cell,"Arial",8,3,"2b5079", DecimalFormato.formato(cant, (long)2),true);
			cell=row.getCell(6);setCelda(cell,"Arial",8,3,"2b5079", DecimalFormato.formato(total, numDec),true);
			
			
	        Double totalDcto = total * cotiOdo.getDctoOdo();
	        
	        
	        Double tasaIva = emisorTributario.getTasaIva()/100;
	        
	        if(mapPermiso.get("parametro.ivaPorBodega")!=null && mapPermiso.get("parametro.ivaPorBodega").equals("1") && cotiOdo.getId_bodegaEmpresa()>0) {
	        	BodegaEmpresa bodegaEmpresa = BodegaEmpresa.findXIdBodega(con, db, cotiOdo.getId_bodegaEmpresa());
	        	if(bodegaEmpresa!=null) {
	        		tasaIva = bodegaEmpresa.getIvaBodega();
	        	}
	        }
	        
	        Double totalIva = total * tasaIva;
	        Double totalConIVA = total * (1+tasaIva);
	        
	        table = doc.getTables().get(3);
	        
			cell=table.getRow(0).getCell(2);
			setCelda(cell,"Arial",10,3,"2b5079",DecimalFormato.formato(total, numDec),false);
			
			if(totalDcto > (double) 0) {
				cell=table.getRow(1).getCell(1);
				setCelda(cell,"Arial",10,1,"000000","DESCUENTO "+DecimalFormato.formato( (cotiOdo.getDctoOdo()*100), (long)2)+" %",false);
				
    			cell=table.getRow(1).getCell(2);
    			setCelda(cell,"Arial",10,3,"2b5079",DecimalFormato.formato(totalDcto, numDec),false);
			}else {
				cell=table.getRow(1).getCell(1);
				setCelda(cell,"Arial",10,1,"000000"," ",false);
			}
			
			cell=table.getRow(2).getCell(2);
			setCelda(cell,"Arial",10,3,"2b5079",DecimalFormato.formato(total, numDec),false);
			
			cell=table.getRow(3).getCell(2);
			setCelda(cell,"Arial",10,3,"2b5079",DecimalFormato.formato(totalIva, numDec),false);
			
			cell=table.getRow(4).getCell(2);
			setCelda(cell,"Arial",10,3,"2b5079",DecimalFormato.formato(totalConIVA, numDec),false);
			
			cell=table.getRow(0).getCell(0);
			setCelda(cell,"Arial",10,1,"2b5079","NOTAS:\n"+cotiOdo.getObservaciones(),false);
			
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

				String archivoPdf = "CotiOdoVenta_"+cotiOdo.numero+".pdf";
				path = db+"/"+archivoPdf;
				Archivos.grabarArchivo(tmp, path);
				CotiOdo.modifyXCampo(con, db, "pdfCotiVtaOdo", archivoPdf, id_cotiOdo);
				
				return(archivoPdf);
				
		} catch (IOException e) {
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
	
	
	public static List<String> cotiOdoValidarPlantillaExcel (Connection con, String db, File file) {
		List<String> mensaje = new ArrayList<String>();
		DecimalFormat df = new DecimalFormat("#");
	    df.setMaximumFractionDigits(8);
	    
        mensaje.add("ERROR cod:001");
        
        try {
            Workbook libro = WorkbookFactory.create(file);
            Sheet hoja1 = libro.getSheetAt(0);
            Row row = null;
            Cell cell = null;
            boolean archivoNoCorresponde = false;
            row = hoja1.getRow(1);
            if(row==null || archivoNoCorresponde) {
            	archivoNoCorresponde = true;
            }else {
            	for(int i=1; i<8; i++) {
            		cell = row.getCell(i);
                	if(cell==null) {
                		archivoNoCorresponde = true;
                	}
            	}
            	cell = row.getCell(0);
            	if(cell!=null) {
            		archivoNoCorresponde = true;
            	}
            	cell = row.getCell(2);
            	if(!cell.getStringCellValue().equals("SERVICIO")) {
            		archivoNoCorresponde = true;
            	}
            }
            if(archivoNoCorresponde) {
            	mensaje.set(0,"ARCHIVO NO CORRESPONDE A LA PLANTILLA1");
            	return (mensaje);
            }
            
            Map<String,Servicio> mapServicios = Servicio.mapAllPorCodigo(con, db);
            boolean flag = true;
            int fila = 2;
            int x = 2;
            row = hoja1.getRow(1);
            cell = row.getCell(1);
            while (row!=null && cell !=null ) {
            	fila++;
            	row = hoja1.getRow(x++);
            	if(row!=null) {
            		cell = row.getCell(1);
                	if(cell!=null) {
                		boolean noEsBlanco = true;
                    	try {
                    		String dato = cell.getStringCellValue().trim();
                    		if(dato.trim().equals("")) {
                    			noEsBlanco = false;
                    		}
                    	}catch(Exception e){
                    		Double aux = cell.getNumericCellValue();
                    		if(aux.toString().trim().equals("")) {
                    			noEsBlanco = false;
                    		}
                    	}
                		if(noEsBlanco) {
                			String dato = "codigo";
                			cell = row.getCell(1);
                			try {
                        		dato = cell.getStringCellValue().trim();
                        	}catch(Exception e){
                        		Double aux = cell.getNumericCellValue();
                        		Long aux2 = aux.longValue();
                        		dato = df.format(aux2);
                        	}
                			Servicio servicio = mapServicios.get(dato);
                			if(servicio == null) {
                				mensaje.add("error en fila "+fila+": Codigo ["+dato+"] no existe en mada.");
                        		flag = false;
                			}
                		}
                	}
                	cell = row.getCell(1);
            	}
            }
            if(flag) {
            	mensaje.set(0,"true");
            }
        } catch (Exception e) {
			mensaje.set(0,"ARCHIVO NO CORRESPONDE A LA PLANTILLA2");
        	return (mensaje);
        }
		return(mensaje);
	}
	
	public static List<List<String>> llenaListaDesdeExcel (File file) {
		List<List<String>> lista = new ArrayList<List<String>>();
		DecimalFormat df = new DecimalFormat("#");
	    df.setMaximumFractionDigits(8);
		try {
            Workbook libro = WorkbookFactory.create(file);
            Sheet hoja1 = libro.getSheetAt(0);
            Row row = null;
            Cell cell = null;
            int x = 2;
            row = hoja1.getRow(1);
            cell = row.getCell(1);
            while (row!=null && cell !=null ) {
            	row = hoja1.getRow(x++);
            	if(row!=null) {
            		cell = row.getCell(1);
                	if(cell!=null) {
                		boolean noEsBlanco = true;
                    	try {
                    		String dato = cell.getStringCellValue().trim();
                    		if(dato.trim().equals("")) {
                    			noEsBlanco = false;
                    		}
                    	}catch(Exception e){
                    		Double aux = cell.getNumericCellValue();
                    		if(aux.toString().trim().equals("")) {
                    			noEsBlanco = false;
                    		}
                    	}
                		if(noEsBlanco) {
                			List<String> auxList = new ArrayList<String>();
                			for(int i=1; i<8; i++) {
                    			String dato = "";
                    			cell = row.getCell(i);
                                if(cell!=null) {
                                	try {
                                		dato = cell.getStringCellValue().trim();
                                		dato = dato.replaceAll("'", "\"");
                                	}catch(Exception e){
                                		if(i==1) {
                                			Double aux = cell.getNumericCellValue();
                                    		Long aux2 = aux.longValue();
                                    		dato = df.format(aux2);
                                		}else {
                                			Double aux = cell.getNumericCellValue();
                                    		dato = df.format(aux);
                                		}
                                	}
                                }
                                auxList.add(dato);
                            }
                			auxList.set(0, auxList.get(0).toUpperCase());
                    		lista.add(auxList);
                		}
                	}
                	cell = row.getCell(1);
            	}
            }
		} catch (InvalidFormatException | IOException e1) {
		}
		return(lista);
	}
	
	
}














