package models.forms;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.poi.xwpf.usermodel.XWPFTableCell.XWPFVertAlign;

import models.api.ApiManagerDocDoc;
import models.api.ApiNuboxDocDoc;
import models.api.ApiRelBase;
import models.api.ApiSapConconcreto;
import models.api.ApiSapSchwager;
import models.api.WebIConstruye;
import models.api.WebMaximise;
import models.reports.ReportMovimientos;
import models.utilities.Archivos;
import models.utilities.Fechas;
import models.xml.XMLFacturaArriendo;
import models.xml.XmlFacturaReferencias;
import models.xml.XmlFacturaVenta;
import play.data.DynamicForm;
import play.libs.ws.WSClient;




public class FormFactura {
	
	public String idBodega;
	public String fechaDesde;
	public String fechaHasta;
	public String uf;
	public String usd;
	public String eur;
	public List<String> tpoDocRef; 
	public List<String> folioRef; 
	public List<String> fchRef; 
	public List<String> razonRef; 
	
	
	public String oc;
	public String comentarios;
	
	public Long id_cotizaSolucion;
	public String sol_description;
	public String sol_observaciones;
	public Long id_proforma;

	public FormFactura(String idBodega, String fechaDesde, String fechaHasta, String uf, String usd, String eur,
			List<String> tpoDocRef, List<String> folioRef, List<String> fchRef, List<String> razonRef, String oc,
			String comentarios, Long id_cotizaSolucion, String sol_description, String sol_observaciones, Long id_proforma) {
		super();
		this.idBodega = idBodega;
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.uf = uf;
		this.usd = usd;
		this.eur = eur;
		this.tpoDocRef = tpoDocRef;
		this.folioRef = folioRef;
		this.fchRef = fchRef;
		this.razonRef = razonRef;
		this.oc = oc;
		this.comentarios = comentarios;
		this.id_cotizaSolucion = id_cotizaSolucion;
		this.sol_description = sol_description;
		this.sol_observaciones = sol_observaciones;
		this.id_proforma = id_proforma;
	}

	public FormFactura() {
		super();
	}
	static DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00",symbols);
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00",symbols);
	static DecimalFormat myformattasa = new DecimalFormat("#,##0.00%",symbols);
	
	public static String generaProformaArriendo(Connection con, String db, WSClient ws, Map<String,String> mapDiccionario, Map<String,String> mapPermiso,
			List<List<String>> resumenSubtotales, Cliente cliente, Proforma proforma, XmlFacturaReferencias referencias, 
			List<List<String>> detalleAjuste, String conDetalle, 
			List<List<String>> inicioPer, List<List<String>> guiasPer, Map<String,List<List<String>>> mapReportPorGuia10, List<List<String>> finalPer,
			Double uf, Double usd, Double eur, String oc,
			Map<Long,Long> dec, EmisorTributario emisorTributario, BodegaEmpresa bodegaEmpresa, String comentarios, FormFactura form) {
		
			
			
			File tmp = TempFile.createTempFile("tmp","null");
	       
    		try {
    			
    			String path = db + "/formatos/proformaArriendo.docx";
    			if(conDetalle.equals("1")) {
    				path = db + "/formatos/proformaArriendoMasDetalle.docx";
    			}
    			InputStream formato = Archivos.leerArchivo(path);
    			XWPFDocument doc = new XWPFDocument(formato);
    			formato.close();
    			
    			
    			XWPFTable table = null;
    			XWPFTableRow row = null;
				XWPFTableCell cell = null;
    			
    			table = doc.getTables().get(0);
    			row=table.getRow(1);cell=row.getCell(2);setCelda(cell,"Arial",10,2,"2b5079",Fechas.DDMMAA(proforma.fecha),false);
    			row=table.getRow(2);cell=row.getCell(2);setCelda(cell,"Arial",12,2,"2b5079",proforma.id.toString(),false);
    			
    			Proyecto proyecto = Proyecto.find(con, db, bodegaEmpresa.id_proyecto);
    			String dirProyecto = "";
    			if(proyecto.id > 0) {
    				dirProyecto = proyecto.getDireccion() + ", "+ proyecto.getComuna() + ", " + proyecto.getCiudad();
    			}
    			
    			
    			table= doc.getTables().get(1);
    			cell=table.getRow(0).getCell(1);setCelda(cell,"Arial",10,1,"2b5079",cliente.nombre,false);
    			cell=table.getRow(1).getCell(1);setCelda(cell,"Arial",10,1,"2b5079",cliente.rut,false);
    			cell=table.getRow(1).getCell(3);setCelda(cell,"Arial",10,1,"2b5079",bodegaEmpresa.nombre,false);
    			cell=table.getRow(2).getCell(1);setCelda(cell,"Arial",10,1,"2b5079",cliente.giro,false);
    			cell=table.getRow(3).getCell(1);setCelda(cell,"Arial",10,1,"2b5079",cliente.direccion,false);
    			cell=table.getRow(4).getCell(1);setCelda(cell,"Arial",10,1,"2b5079",cliente.region,false);
    			cell=table.getRow(5).getCell(1);setCelda(cell,"Arial",10,1,"2b5079",cliente.comuna,false);
    			cell=table.getRow(3).getCell(3);setCelda(cell,"Arial",10,1,"2b5079","de "+Fechas.DDMMAA(proforma.desde)+" a "+Fechas.DDMMAA(proforma.hasta),false);
    			//cell=table.getRow(4).getCell(3);setCelda(cell,"Arial",10,1,"2b5079",proforma.docRef,false);
    			cell=table.getRow(4).getCell(3);setCelda(cell,"Arial",10,1,"2b5079",bodegaEmpresa.comercial,false);
    			
    			cell=table.getRow(5).getCell(2);setCelda(cell,"Arial",9,1,"000000","DIR_PROY:",false);
    			cell=table.getRow(5).getCell(3);setCelda(cell,"Arial",10,1,"2b5079",dirProyecto,false);
    			
    			
    			
    			
    			switch(dec.get((long) 1).toString()) {
    			 case "0": myformatdouble = new DecimalFormat("#,##0",symbols); break;
    			 case "2": myformatdouble = new DecimalFormat("#,##0.00",symbols); break;
    			 case "4": myformatdouble = new DecimalFormat("#,##0.0000",symbols); break;
    			 case "6": myformatdouble = new DecimalFormat("#,##0.000000",symbols); break;
    			 default:  break;
    			}
    			
    			Double totalPrecio=(double)0;
    			
    			table = doc.getTables().get(2);
    			int auxLinea = 1;
    			for(int i=0;i<resumenSubtotales.size();i++) {
    				
    				String auxPrecioArr = resumenSubtotales.get(i).get(1);
	    			if(auxPrecioArr.equals("")||auxPrecioArr.equals(" ")) {
	    				auxPrecioArr = "0";
	    			}
	 	   			
	 	   			String auxNum = auxPrecioArr.trim();
	 	   			if(auxNum==null){
	 	   				auxNum = "0";
	 	   			}else if(auxNum.trim().length()<=0) {
	 	   				auxNum = "0";
	 	   			}
	 	   			Double precioArr = Double.parseDouble(auxNum.replaceAll(",", "").trim());
	   				totalPrecio += precioArr;
	   				
	   				auxNum = resumenSubtotales.get(i).get(3);
	    			if(auxNum.equals("")||auxNum.equals(" ")) {
	    				auxNum = "0";
	    			}
	 	   			Double precioCfi = Double.parseDouble(auxNum.replaceAll(",", "").trim());
	 	   			totalPrecio += precioCfi;
	   				
	   				Double totalLinea = precioArr + precioCfi;
	   				if( totalLinea > (double)0) {
	   					row = table.getRow(auxLinea);
	    				cell=row.getCell(1);setCelda(cell,"Arial",10,1,"2b5079",resumenSubtotales.get(i).get(0),false);
	    				cell=row.getCell(2);setCelda(cell,"Arial",10,2,"2b5079","GL",false);
	    				cell=row.getCell(3);setCelda(cell,"Arial",10,2,"2b5079","1",false);
	    				cell=row.getCell(4);setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(totalLinea),false);
	    				cell=row.getCell(5);setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(totalLinea),false);
	    				table.createRow();
	    				auxLinea = auxLinea+1;
	   				}
    			}
    			
    			
    			proforma.setNeto(totalPrecio);
    			
    			proforma.setIva(proforma.neto * emisorTributario.tasaIva/100);
    			
    			if(mapPermiso.get("parametro.ivaPorBodega")!=null && mapPermiso.get("parametro.ivaPorBodega").equals("1")) {
		        	if(bodegaEmpresa!=null) {
		        		if(bodegaEmpresa.getIvaBodega() > 0) {
		        			proforma.setIva(proforma.neto * bodegaEmpresa.getIvaBodega());
		        		}else {
		        			Sucursal sucursal = Sucursal.find(con, db, bodegaEmpresa.getId_sucursal().toString());
		        			if(sucursal!=null && sucursal.getIvaSucursal() > 0) {
		        				proforma.setIva(proforma.neto * sucursal.getIvaSucursal());
		        			}
		        		}
		        		
		        	}
		        }
    			
    			
    			proforma.setTotal(proforma.neto+proforma.iva);
    			
    			auxLinea = auxLinea+1;
    			row = table.getRow(auxLinea);
				cell=row.getCell(1);
				setCelda(cell,"Arial",10,1,"2b5079","SUBTOTALES",false);
				cell=row.getCell(2);
				setCelda(cell,"Arial",10,2,"2b5079","",false);
				cell=row.getCell(3);
				setCelda(cell,"Arial",10,2,"2b5079","",false);
				cell=row.getCell(4);
				setCelda(cell,"Arial",10,3,"2b5079","",false);
				cell=row.getCell(5);
				setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(totalPrecio),false);
				table.createRow();
    			
				Double totalAjuste = (double)0;
				auxLinea = auxLinea+1;
				for(List<String>ajuste: detalleAjuste){
					Double totAjuste = Double.parseDouble(ajuste.get(1).replaceAll(",", ""));
					if(totAjuste>0 || totAjuste<0) {
						row = table.getRow(auxLinea++);
        				cell=row.getCell(1);
        				setCelda(cell,"Arial",10,1,"2b5079",ajuste.get(0),false);
        				cell=row.getCell(2);
        				setCelda(cell,"Arial",10,2,"2b5079","",false);
        				cell=row.getCell(3);
        				setCelda(cell,"Arial",10,2,"2b5079","",false);
        				cell=row.getCell(4);
        				setCelda(cell,"Arial",10,3,"2b5079","",false);
        				cell=row.getCell(5);
        				setCelda(cell,"Arial",10,3,"2b5079",ajuste.get(1),false);
        				table.createRow();
        				totalAjuste += totAjuste;
					}
				}
    			
				proforma.setNeto(totalPrecio+totalAjuste);
				
    			proforma.setIva(proforma.neto * emisorTributario.tasaIva/100);
    			
    			if(mapPermiso.get("parametro.ivaPorBodega")!=null && mapPermiso.get("parametro.ivaPorBodega").equals("1")) {
		        	if(bodegaEmpresa!=null) {
		        		if(bodegaEmpresa.getIvaBodega() > 0) {
		        			proforma.setIva(proforma.neto * bodegaEmpresa.getIvaBodega());
		        		}else {
		        			Sucursal sucursal = Sucursal.find(con, db, bodegaEmpresa.getId_sucursal().toString());
		        			if(sucursal!=null && sucursal.getIvaSucursal() > 0) {
		        				proforma.setIva(proforma.neto * sucursal.getIvaSucursal());
		        			}
		        		}
		        		
		        	}
		        }
    			
    			proforma.setTotal(proforma.neto+proforma.iva);
    			
    			proforma.setNetoSinAjustes(totalPrecio);
    			proforma.setNetoSoloAjustes(totalAjuste);
				
    			table = doc.getTables().get(3);
    			
    			
    			int cantRef=0;
    			try {cantRef=referencias.tpoDocRef.size();}catch(Exception e) {}
    			String strReferencia = "";
    			if(cantRef>0) {
    				strReferencia="REFERENCIAS:\n";
    			}
    			for (int i = 0; i < cantRef; i++) {
    				if(!referencias.tpoDocRef.get(i).equals("0")) {

    					String docRef = TipoReferencia.find(con, db, referencias.tpoDocRef.get(i)).concepto;
    					
    					strReferencia = strReferencia +
    							docRef+": "+referencias.folioRef.get(i)+" - "+referencias.fchRef.get(i)+" - "+referencias.razonRef.get(i)+"\n";
    				}
    				
    			}
    			
    			if(oc!=null && oc.length()>0) {
    				strReferencia += "\n" + oc;
    			}else {
    				oc = "";
    			}
    			
    			if(comentarios!=null && comentarios.length()>1) {
    				strReferencia += "\n COMENTARIOS: " + comentarios.trim();
    			}else {
    				comentarios = "";
    			}
    			
    			cell=table.getRow(0).getCell(0);
    			setCelda(cell,"Arial",10,1,"2b5079",strReferencia,false);
    			
    			
    			
    			
    			cell=table.getRow(0).getCell(2);
    			setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(proforma.neto),false);
    			
    			cell=table.getRow(1).getCell(1);
    			setCelda(cell,"Arial",10,3,"2b5079","",false);
    			cell=table.getRow(2).getCell(2);
    			setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(proforma.neto),false);
    			cell=table.getRow(3).getCell(2);
    			setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(proforma.iva),false);
    			cell=table.getRow(4).getCell(2);
    			setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(proforma.total),false);
    			
    			
    			if(conDetalle.equals("1")) {
    				
    				Double totalInicial=(double) 0;
    				Double totalDespachoCfi=(double)0;
		            Double totalDespachoArr=(double)0;
		            Double totalDespachoVta=(double)0;
		            Double totalDevolucionArr=(double)0;
		            Double totalDevolucionVta=(double)0;
	    	        
	    	        

		            table = doc.getTables().get(5);
		            table.setInsideHBorder(XWPFTable.XWPFBorderType.SINGLE, 0, 0, "FFFFFF");
		            
		            String dato = "";
		            Double datoNum = (double)0;
		            int fontSize = 6;
		            
		            //INVENTARIO INICIAL

		            for(int i=0; i<inicioPer.size(); i++){
		            	
		            	Long id_moneda = Long.parseLong(inicioPer.get(i).get(6));
		            	
		            	switch(dec.get(id_moneda).toString()) {
		    			 case "0": myformatdouble = new DecimalFormat("#,##0",symbols); break;
		    			 case "2": myformatdouble = new DecimalFormat("#,##0.00",symbols); break;
		    			 case "4": myformatdouble = new DecimalFormat("#,##0.0000",symbols); break;
		    			 case "6": myformatdouble = new DecimalFormat("#,##0.000000",symbols); break;
		    			 default:  break;
		    			}
		            	
		            	int celda = 0;
		            	int linea = i+1;
		            	
		            	row = table.getRow(linea);
		            	
		            	dato = inicioPer.get(i).get(7);
		            	cell = row.getCell(celda);
		            	setCelda(cell,"Arial",fontSize,1,"2b5079",dato,false);
		            	celda++;
		            	
		            	dato = inicioPer.get(i).get(8);
		            	cell = row.getCell(celda);
		            	setCelda(cell,"Arial",fontSize,1,"2b5079",dato,false);
		            	celda++;
		            	
		            	dato = inicioPer.get(i).get(9);
		            	cell = row.getCell(celda);
		            	setCelda(cell,"Arial",fontSize,1,"2b5079",dato,false);
		            	celda++;
		            	
		            	dato = inicioPer.get(i).get(10);
		            	cell = row.getCell(celda);
		            	setCelda(cell,"Arial",fontSize,1,"2b5079",dato,false);
		            	celda++;
		            	
		            	dato = inicioPer.get(i).get(11);
		            	cell = row.getCell(celda);
		            	setCelda(cell,"Arial",fontSize,1,"2b5079",dato,false);
		            	celda++;
		            	
		            	datoNum = Double.parseDouble(inicioPer.get(i).get(12).replaceAll(",", ""));
		            	cell = row.getCell(celda);
		            	setCelda(cell,"Arial",fontSize,3,"2b5079",myformatdouble2.format(datoNum),false);
		            	celda++;
		            	
		            	dato = inicioPer.get(i).get(13);
		            	cell = row.getCell(celda);
		            	setCelda(cell,"Arial",fontSize,1,"2b5079",dato,false);
		            	celda++;
		            	
		            	
		            	if(inicioPer.get(i).get(19).trim().equals("0") && mapDiccionario.get("nEmpresa").equals("SANTAFE")){
		            		
			            	cell = row.getCell(celda);
			            	setCelda(cell,"Arial",fontSize,1,"2b5079","",false);
			            	celda++;
			            	
			            	cell = row.getCell(celda);
			            	setCelda(cell,"Arial",fontSize,1,"2b5079","",false);
			            	celda++;
		            		
		            	}else {
			            	if(mapPermiso.get("parametro.proforma_conDctosCoti").equals("1")){
								Double auxDcto = Double.parseDouble(inicioPer.get(i).get(24).replaceAll(",", "").replaceAll("%", "").trim());
								datoNum = auxDcto;
								cell = row.getCell(celda);
				            	setCelda(cell,"Arial",fontSize,3,"2b5079",myformattasa.format(datoNum/100),false);
				            	celda++;
							}else {
								datoNum = Double.parseDouble(inicioPer.get(i).get(14).replaceAll(",", ""));
								cell = row.getCell(celda);
				            	setCelda(cell,"Arial",fontSize,3,"2b5079",myformatdouble.format(datoNum),false);
				            	celda++;
				            	
				            	if(!mapPermiso.get("parametro.proforma_ocultaTasaArriendo").equals("1")){
									Double auxVta = Double.parseDouble(inicioPer.get(i).get(14).replaceAll(",", ""));
									Double auxArr = Double.parseDouble(inicioPer.get(i).get(15).replaceAll(",", ""));
									if((double)auxVta == (double)0){
										cell = row.getCell(celda);
						            	setCelda(cell,"Arial",fontSize,1,"2b5079","",false);
						            	celda++;
									}else{
										datoNum = auxArr*30/auxVta;
										cell = row.getCell(celda);
						            	setCelda(cell,"Arial",fontSize,3,"2b5079",myformattasa.format(datoNum),false);
						            	celda++;
									}
								}
				            	
							}
		            	}
		            	
		            	datoNum = Double.parseDouble(inicioPer.get(i).get(15).replaceAll(",", ""));
						cell = row.getCell(celda);
		            	setCelda(cell,"Arial",fontSize,3,"2b5079",myformatdouble.format(datoNum),false);
		            	celda++;
		            	
		            	datoNum = Double.parseDouble(inicioPer.get(i).get(16).replaceAll(",", ""));
						cell = row.getCell(celda);
		            	setCelda(cell,"Arial",fontSize,3,"2b5079",myformatdouble.format(datoNum),false);
		            	celda++;
		            	
		            	datoNum = Double.parseDouble(inicioPer.get(i).get(17).replaceAll(",", ""));
						cell = row.getCell(celda);
		            	setCelda(cell,"Arial",fontSize,3,"2b5079",myformatdouble.format(datoNum),false);
		            	celda++;
		            	
		            	datoNum = Double.parseDouble(inicioPer.get(i).get(23).replaceAll(",", ""));
						cell = row.getCell(celda);
		            	setCelda(cell,"Arial",fontSize,3,"2b5079",myformatdouble.format(datoNum),false);
		            	celda++;
		            	
		            	datoNum = Double.parseDouble(inicioPer.get(i).get(18).replaceAll(",", ""));
	            		totalInicial += datoNum;
						cell = row.getCell(celda);
		            	setCelda(cell,"Arial",fontSize,3,"2b5079",myformatdouble.format(datoNum),false);
		            	celda++;
						
		            	datoNum = Double.parseDouble(inicioPer.get(i).get(19).replaceAll(",", ""));
						cell = row.getCell(celda);
		            	setCelda(cell,"Arial",fontSize,3,"2b5079",myformatdouble.format(datoNum),false);
		            	celda++;
		            	
		            	dato = inicioPer.get(i).get(21).replaceAll(",", "");
						cell = row.getCell(celda);
		            	setCelda(cell,"Arial",fontSize,3,"2b5079",dato,false);
		            	celda++;
		            	
		            	table.createRow();
		            	
		            }
		            
		            
		            table = doc.getTables().get(6);
		            table.setInsideHBorder(XWPFTable.XWPFBorderType.SINGLE, 0, 0, "FFFFFF");
		            
		            //DETALLE DE MOVIMIENTOS
		            
		            int linea = 0;

		            for(int i=0; i<guiasPer.size(); i++){
		            	
		            	int celda = 3;
		            	linea++;
		            	
		            	row = table.getRow(linea);
		            	
		            	dato = "=======================";
		            	dato += "\n"+guiasPer.get(i).get(7)+": "+guiasPer.get(i).get(2);
		            	dato += "\n"+"FECHA GUIA: "+guiasPer.get(i).get(3);
		            	dato += "\n"+"Nro de Ref: "+guiasPer.get(i).get(5);
		            	dato += "\n"+"=======================";
		            	cell = row.getCell(celda);
		            	setCelda(cell,"Arial",fontSize,1,"2b5079",dato,false);
		            	
		            	table.createRow();
		            	
		            	List<List<String>> reportPorGuia = mapReportPorGuia10.get(guiasPer.get(i).get(8));
		            	
		            	
		            	if(reportPorGuia!=null) {
		            		
		            		for(int j=0; j<reportPorGuia.size(); j++){
		            			
		            			Long id_moneda = Long.parseLong(reportPorGuia.get(j).get(7));
				            	
				            	switch(dec.get(id_moneda).toString()) {
				    			 case "0": myformatdouble = new DecimalFormat("#,##0",symbols); break;
				    			 case "2": myformatdouble = new DecimalFormat("#,##0.00",symbols); break;
				    			 case "4": myformatdouble = new DecimalFormat("#,##0.0000",symbols); break;
				    			 case "6": myformatdouble = new DecimalFormat("#,##0.000000",symbols); break;
				    			 default:  break;
				    			}
		            			
		            			celda = 0;
				            	linea ++;
				            	
				            	row = table.getRow(linea);
				            	
				            	dato = reportPorGuia.get(j).get(8);
				            	cell = row.getCell(celda);
				            	setCelda(cell,"Arial",fontSize,1,"2b5079",dato,false);
				            	celda++;
				            	
				            	dato = reportPorGuia.get(j).get(9);
				            	cell = row.getCell(celda);
				            	setCelda(cell,"Arial",fontSize,1,"2b5079",dato,false);
				            	celda++;
				            	
				            	dato = reportPorGuia.get(j).get(10);
				            	cell = row.getCell(celda);
				            	setCelda(cell,"Arial",fontSize,1,"2b5079",dato,false);
				            	celda++;
				            	
				            	dato = reportPorGuia.get(j).get(11);
				            	cell = row.getCell(celda);
				            	setCelda(cell,"Arial",fontSize,1,"2b5079",dato,false);
				            	celda++;
				            	
				            	dato = reportPorGuia.get(j).get(12);
				            	cell = row.getCell(celda);
				            	setCelda(cell,"Arial",fontSize,1,"2b5079",dato,false);
				            	celda++;
				            	
				            	datoNum = Double.parseDouble(reportPorGuia.get(j).get(13).replaceAll(",", ""));
				            	cell = row.getCell(celda);
				            	setCelda(cell,"Arial",fontSize,3,"2b5079",myformatdouble2.format(datoNum),false);
				            	celda++;
				            	
				            	dato = reportPorGuia.get(j).get(14);
				            	cell = row.getCell(celda);
				            	setCelda(cell,"Arial",fontSize,1,"2b5079",dato,false);
				            	celda++;
		            			
				            	if(reportPorGuia.get(j).get(19).trim().equals("0") && mapDiccionario.get("nEmpresa").equals("SANTAFE")){
				            		
					            	cell = row.getCell(celda);
					            	setCelda(cell,"Arial",fontSize,1,"2b5079","",false);
					            	celda++;
					            	
					            	cell = row.getCell(celda);
					            	setCelda(cell,"Arial",fontSize,1,"2b5079","",false);
					            	celda++;
				            		
				            	}else {
				            		
				            		if(mapPermiso.get("parametro.proforma_conDctosCoti").equals("1")){
										Double auxDcto = Double.parseDouble(reportPorGuia.get(j).get(24).replaceAll(",", "").replaceAll("%", "").trim());
										datoNum = auxDcto;
										cell = row.getCell(celda);
						            	setCelda(cell,"Arial",fontSize,3,"2b5079",myformattasa.format(datoNum/100),false);
						            	celda++;
									}else {
										datoNum = Double.parseDouble(reportPorGuia.get(j).get(15).replaceAll(",", ""));
										cell = row.getCell(celda);
						            	setCelda(cell,"Arial",fontSize,3,"2b5079",myformatdouble.format(datoNum),false);
						            	celda++;
						            	
						            	if(!mapPermiso.get("parametro.proforma_ocultaTasaArriendo").equals("1")){
											Double auxVta = Double.parseDouble(reportPorGuia.get(j).get(15).replaceAll(",", ""));
											Double auxArr = Double.parseDouble(reportPorGuia.get(j).get(16).replaceAll(",", ""));
											if((double)auxVta == (double)0){
												cell = row.getCell(celda);
								            	setCelda(cell,"Arial",fontSize,1,"2b5079","",false);
								            	celda++;
											}else{
												datoNum = auxArr*30/auxVta;
												cell = row.getCell(celda);
								            	setCelda(cell,"Arial",fontSize,3,"2b5079",myformattasa.format(datoNum),false);
								            	celda++;
											}
										}
						            	
									}
				            	}
		            			
				            	datoNum = Double.parseDouble(reportPorGuia.get(j).get(16).replaceAll(",", ""));
								cell = row.getCell(celda);
				            	setCelda(cell,"Arial",fontSize,3,"2b5079",myformatdouble.format(datoNum),false);
				            	celda++;
				            	
				            	datoNum = Double.parseDouble(reportPorGuia.get(j).get(17).replaceAll(",", ""));
								cell = row.getCell(celda);
				            	setCelda(cell,"Arial",fontSize,3,"2b5079",myformatdouble.format(datoNum),false);
				            	celda++;
				            	
				            	datoNum = Double.parseDouble(reportPorGuia.get(j).get(18).replaceAll(",", ""));
								cell = row.getCell(celda);
				            	setCelda(cell,"Arial",fontSize,3,"2b5079",myformatdouble.format(datoNum),false);
				            	celda++;
				            	
				            	datoNum = Double.parseDouble(reportPorGuia.get(j).get(23).replaceAll(",", ""));
				            	totalDespachoCfi += datoNum;
								cell = row.getCell(celda);
				            	setCelda(cell,"Arial",fontSize,3,"2b5079",myformatdouble.format(datoNum),false);
				            	celda++;
				            	
				            	datoNum = Double.parseDouble(reportPorGuia.get(j).get(19).replaceAll(",", ""));
				            	if((double)datoNum > (double)0) {
				            		totalDespachoArr += datoNum;
				            	}else {
				            		totalDevolucionArr += datoNum;
				            	}
								cell = row.getCell(celda);
				            	setCelda(cell,"Arial",fontSize,3,"2b5079",myformatdouble.format(datoNum),false);
				            	celda++;
								
				            	datoNum = Double.parseDouble(reportPorGuia.get(j).get(20).replaceAll(",", ""));
				            	if((double)datoNum > (double)0) {
				            		totalDespachoVta += datoNum;
				            	}else {
				            		totalDevolucionVta += datoNum;
				            	}
								cell = row.getCell(celda);
				            	setCelda(cell,"Arial",fontSize,3,"2b5079",myformatdouble.format(datoNum),false);
				            	celda++;
				            	
								cell = row.getCell(celda);
				            	setCelda(cell,"Arial",fontSize,3,"2b5079","",false);
				            	celda++;
				            	
				            	table.createRow();
				            	
		            		}
		            		table.createRow();
		            		linea++;
		            	}
		            }
		            
		            //AGREGAR RESUMEN DESPACHO Y DEVOLUCIONES
    				
    				Double totalCfi = totalDespachoCfi;
	    	        Double totalArr = totalInicial +totalDespachoArr + totalDevolucionArr;
	    	        Double totalVta = totalDespachoVta + totalDevolucionVta;
		            
			           
	    	        table = doc.getTables().get(4);
	    	        table.setInsideHBorder(XWPFTable.XWPFBorderType.SINGLE, 0, 0, "FFFFFF");
	    	        
	    	        cell=table.getRow(1).getCell(1);setCelda(cell,"Arial",9,3,"2b5079",myformatdouble.format((double)0),false);
	    	        cell=table.getRow(2).getCell(1);setCelda(cell,"Arial",9,3,"2b5079",myformatdouble.format(totalDespachoCfi),false);
	    	        cell=table.getRow(3).getCell(1);setCelda(cell,"Arial",9,3,"2b5079",myformatdouble.format((double)0),false);
	    	        cell=table.getRow(4).getCell(1);setCelda(cell,"Arial",9,3,"2b5079",myformatdouble.format(totalCfi),false);
	    	        
	    	        cell=table.getRow(1).getCell(2);setCelda(cell,"Arial",9,3,"2b5079",myformatdouble.format(totalInicial),false);
	    	        cell=table.getRow(2).getCell(2);setCelda(cell,"Arial",9,3,"2b5079",myformatdouble.format(totalDespachoArr),false);
	    	        cell=table.getRow(3).getCell(2);setCelda(cell,"Arial",9,3,"2b5079",myformatdouble.format(totalDevolucionArr),false);
	    	        cell=table.getRow(4).getCell(2);setCelda(cell,"Arial",9,3,"2b5079",myformatdouble.format(totalArr),false);
	    	        
	    	        cell=table.getRow(1).getCell(3);setCelda(cell,"Arial",9,3,"2b5079",myformatdouble.format((double)0),false);
	    	        cell=table.getRow(2).getCell(3);setCelda(cell,"Arial",9,3,"2b5079",myformatdouble.format(totalDespachoVta),false);
	    	        cell=table.getRow(3).getCell(3);setCelda(cell,"Arial",9,3,"2b5079",myformatdouble.format(totalDevolucionVta),false);
	    	        cell=table.getRow(4).getCell(3);setCelda(cell,"Arial",9,3,"2b5079",myformatdouble.format(totalVta),false);
	    	        
	    	        
		            
		            table = doc.getTables().get(7);
		            table.setInsideHBorder(XWPFTable.XWPFBorderType.SINGLE, 0, 0, "FFFFFF");
		            
		            
		            //INVENTARIO FINAL

		            for(int i=0; i<finalPer.size(); i++){
		            	
		            	Long id_moneda = Long.parseLong(finalPer.get(i).get(6));
		            	
		            	switch(dec.get(id_moneda).toString()) {
		    			 case "0": myformatdouble = new DecimalFormat("#,##0",symbols); break;
		    			 case "2": myformatdouble = new DecimalFormat("#,##0.00",symbols); break;
		    			 case "4": myformatdouble = new DecimalFormat("#,##0.0000",symbols); break;
		    			 case "6": myformatdouble = new DecimalFormat("#,##0.000000",symbols); break;
		    			 default:  break;
		    			}
		            	
		            	int celda = 0;
		            	linea = i+1;
		            	
		            	row = table.getRow(linea);
		            	
		            	dato = finalPer.get(i).get(7);
		            	cell = row.getCell(celda);
		            	setCelda(cell,"Arial",fontSize,1,"2b5079",dato,false);
		            	celda++;
		            	
		            	dato = finalPer.get(i).get(8);
		            	cell = row.getCell(celda);
		            	setCelda(cell,"Arial",fontSize,1,"2b5079",dato,false);
		            	celda++;
		            	
		            	dato = finalPer.get(i).get(9);
		            	cell = row.getCell(celda);
		            	setCelda(cell,"Arial",fontSize,1,"2b5079",dato,false);
		            	celda++;
		            	
		            	dato = finalPer.get(i).get(10);
		            	cell = row.getCell(celda);
		            	setCelda(cell,"Arial",fontSize,1,"2b5079",dato,false);
		            	celda++;
		            	
		            	dato = finalPer.get(i).get(11);
		            	cell = row.getCell(celda);
		            	setCelda(cell,"Arial",fontSize,1,"2b5079",dato,false);
		            	celda++;
		            	
		            	datoNum = Double.parseDouble(finalPer.get(i).get(12).replaceAll(",", ""));
		            	cell = row.getCell(celda);
		            	setCelda(cell,"Arial",fontSize,3,"2b5079",myformatdouble2.format(datoNum),false);
		            	celda++;
		            	
		            	dato = finalPer.get(i).get(13);
		            	cell = row.getCell(celda);
		            	setCelda(cell,"Arial",fontSize,1,"2b5079",dato,false);
		            	celda++;
		            	
		            	
		            	if(finalPer.get(i).get(19).trim().equals("0") && mapDiccionario.get("nEmpresa").equals("SANTAFE")){
		            		
			            	cell = row.getCell(celda);
			            	setCelda(cell,"Arial",fontSize,1,"2b5079","",false);
			            	celda++;
			            	
			            	cell = row.getCell(celda);
			            	setCelda(cell,"Arial",fontSize,1,"2b5079","",false);
			            	celda++;
		            		
		            	}else {
		            		
		            		if(mapPermiso.get("parametro.proforma_conDctosCoti").equals("1")){
								Double auxDcto = Double.parseDouble(finalPer.get(i).get(24).replaceAll(",", "").replaceAll("%", "").trim());
								datoNum = auxDcto;
								cell = row.getCell(celda);
				            	setCelda(cell,"Arial",fontSize,3,"2b5079",myformattasa.format(datoNum/100),false);
				            	celda++;
							}else {
								datoNum = Double.parseDouble(finalPer.get(i).get(14).replaceAll(",", ""));
								cell = row.getCell(celda);
				            	setCelda(cell,"Arial",fontSize,3,"2b5079",myformatdouble.format(datoNum),false);
				            	celda++;
				            	
				            	if(!mapPermiso.get("parametro.proforma_ocultaTasaArriendo").equals("1")){
									Double auxVta = Double.parseDouble(finalPer.get(i).get(14).replaceAll(",", ""));
									Double auxArr = Double.parseDouble(finalPer.get(i).get(15).replaceAll(",", ""));
									if((double)auxVta == (double)0){
										cell = row.getCell(celda);
						            	setCelda(cell,"Arial",fontSize,1,"2b5079","",false);
						            	celda++;
									}else{
										datoNum = auxArr*30/auxVta;
										cell = row.getCell(celda);
						            	setCelda(cell,"Arial",fontSize,3,"2b5079",myformattasa.format(datoNum),false);
						            	celda++;
									}
								}
				            	
							}
		            	}
		            	
		            	datoNum = Double.parseDouble(finalPer.get(i).get(15).replaceAll(",", ""));
						cell = row.getCell(celda);
		            	setCelda(cell,"Arial",fontSize,3,"2b5079",myformatdouble.format(datoNum),false);
		            	celda++;
		            	
		            	datoNum = Double.parseDouble(finalPer.get(i).get(16).replaceAll(",", ""));
						cell = row.getCell(celda);
		            	setCelda(cell,"Arial",fontSize,3,"2b5079",myformatdouble.format(datoNum),false);
		            	celda++;
		            	
		            	datoNum = Double.parseDouble(finalPer.get(i).get(17).replaceAll(",", ""));
						cell = row.getCell(celda);
		            	setCelda(cell,"Arial",fontSize,3,"2b5079",myformatdouble.format(datoNum),false);
		            	celda++;
		            	
		            	datoNum = Double.parseDouble(finalPer.get(i).get(23).replaceAll(",", ""));
						cell = row.getCell(celda);
		            	setCelda(cell,"Arial",fontSize,3,"2b5079",myformatdouble.format(datoNum),false);
		            	celda++;
		            	
		            	datoNum = Double.parseDouble(finalPer.get(i).get(18).replaceAll(",", ""));
						cell = row.getCell(celda);
		            	setCelda(cell,"Arial",fontSize,3,"2b5079",myformatdouble.format(datoNum),false);
		            	celda++;
						
		            	datoNum = Double.parseDouble(finalPer.get(i).get(19).replaceAll(",", ""));
						cell = row.getCell(celda);
		            	setCelda(cell,"Arial",fontSize,3,"2b5079",myformatdouble.format(datoNum),false);
		            	celda++;
		            	
		            	dato = finalPer.get(i).get(21).replaceAll(",", "");
						cell = row.getCell(celda);
		            	setCelda(cell,"Arial",fontSize,3,"2b5079",dato,false);
		            	celda++;
		            	
		            	table.createRow();
		            	
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

				String archivoPdf = proforma.getProformaPdf();
				path = db+"/"+archivoPdf;
				Archivos.grabarArchivo(tmp, path);
				
				proforma.setComentarios(comentarios.trim());
				
				Proforma.modify(con, db, proforma);
				
				
				
				
				//XML Y API:
				
				
				
				if(mapPermiso.get("parametro.proformaLista-descargarXLM")!=null && mapPermiso.get("parametro.proformaLista-descargarXLM").equals("1")){
 	            	String archivoXml = XMLFacturaArriendo.generaXmlArriendo(con, db, mapDiccionario.get("nEmpresa"), resumenSubtotales, cliente, proforma, mapPermiso, detalleAjuste);
 	            	XmlFacturaReferencias.grabarReferencias(con, mapPermiso, db, archivoXml, referencias, proforma.getId());
 	            }
 	            
				if(mapPermiso.get("parametro.proformaListar-llenarApiManager")!=null && mapPermiso.get("parametro.proformaListar-llenarApiManager").equals("1")){
 	            	String jsonApi = ApiManagerDocDoc.generaFactArriendo(con, db, cliente, proforma);
 	            	Proforma.updateJsonApi(con, db, proforma.id, jsonApi);
 	            }
 	            
				if(mapPermiso.get("parametro.proformaListar-llenarApiNubox")!=null && mapPermiso.get("parametro.proformaListar-llenarApiNubox").equals("1")){
					String esVenta = "0";
					String concepto = mapDiccionario.get("ARRIENDO");
					if("1".equals(esVenta)) {
						concepto = "VENTA";
					}
 	            	List<List<String>> datos = ReportMovimientos.movimientoGuiasValorizado(con, db, mapDiccionario, bodegaEmpresa.getId(), esVenta, proforma.desde, proforma.hasta, usd, eur, uf, concepto);
					String jsonApi = ApiNuboxDocDoc.generaFactArriendo(con, db, resumenSubtotales, cliente, proforma, mapDiccionario, emisorTributario, detalleAjuste, datos);
 	            	Proforma.updateJsonApi(con, db, proforma.id, jsonApi);
				}
 	            
				if(mapPermiso.get("parametro.proformaListar-llenarApiSapConconcreto")!=null && mapPermiso.get("parametro.proformaListar-llenarApiSapConconcreto").equals("1")){
					String jsonApi = ApiSapConconcreto.generaFactArriendo(con, db, resumenSubtotales, cliente, proforma, mapDiccionario, emisorTributario, detalleAjuste, bodegaEmpresa, referencias);
 	            	Proforma.updateJsonApi(con, db, proforma.id, jsonApi);
				}
				
				//generaProformaWebIConstruye
				if(mapPermiso.get("parametro.proformaListar-llenarWebIConstruye")!=null && mapPermiso.get("parametro.proformaListar-llenarWebIConstruye").equals("1")){
					String archivoXml = WebIConstruye.generaXMLFacturasArr(con, db, mapDiccionario.get("nEmpresa"), resumenSubtotales, cliente, 
							proforma, mapPermiso, detalleAjuste, referencias, comentarios);
					Proforma.updateJsonApi(con, db, proforma.id, archivoXml);
				}
				
				if(mapPermiso.get("parametro.proformaListar-llenarWebMaximise")!=null && mapPermiso.get("parametro.proformaListar-llenarWebMaximise").equals("1")){
					String xmlEncode = WebMaximise.encodeXmlArriendo(con, db, mapDiccionario.get("nEmpresa"), resumenSubtotales, cliente, proforma, mapPermiso, detalleAjuste, referencias);
					Proforma.updateJsonApi(con, db, proforma.id, xmlEncode);
				}
				
				if(mapPermiso.get("parametro.proformaListar-llenarApiRelBase")!=null && mapPermiso.get("parametro.proformaListar-llenarApiRelBase").equals("1")){
					String jsonApi = ApiRelBase.generaJsonFactARRVTA(con, db, ws, mapDiccionario, 
							referencias, cliente, proforma, form);
 	            	Proforma.updateJsonApi(con, db, proforma.id, jsonApi);
				} 
				
				if(mapPermiso.get("parametro.proformaListar-llenarApiSapSchwager")!=null && mapPermiso.get("parametro.proformaListar-llenarApiSapSchwager").equals("1")){
					String jsonApi = ApiSapSchwager.generaJsonFactARR(cliente, proforma.getId(), referencias, detalleAjuste, 
							inicioPer, guiasPer, mapReportPorGuia10);
 	            	Proforma.updateJsonApi(con, db, proforma.id, jsonApi);
				} 
				
				
				return(archivoPdf);
    	    
        } catch (Throwable e) {
			e.printStackTrace();
        }
		return("0");
	}



	public static String generaProformaH(String db, Proyecto proyecto, ProformaSimple proforma, Cliente cliente, BodegaEmpresa bodegaEmpresa, DynamicForm form) {
		File tmp = TempFile.createTempFile("tmp","null");
		try {
			String path = db + "/formatos/proformaArriendo.docx";
			InputStream formato = Archivos.leerArchivo(path);
			XWPFDocument doc = new XWPFDocument(formato);
			formato.close();

			XWPFTable table = null;
			XWPFTableRow row = null;
			XWPFTableCell cell = null;

			table = doc.getTables().get(0);
			row=table.getRow(1);cell=row.getCell(2);setCelda(cell,"Arial",10,2,"2b5079",Fechas.DDMMAA(proforma.fecha),false);
			row=table.getRow(2);cell=row.getCell(2);setCelda(cell,"Arial",12,2,"2b5079",proforma.id.toString(),false);

			String dirProyecto = "";
			if(proyecto.id > 0) {
				dirProyecto = proyecto.getDireccion() + ", "+ proyecto.getComuna() + ", " + proyecto.getCiudad();
			}

			table= doc.getTables().get(1);
			cell=table.getRow(0).getCell(1);setCelda(cell,"Arial",10,1,"2b5079",cliente.nombre,false);
			cell=table.getRow(1).getCell(1);setCelda(cell,"Arial",10,1,"2b5079",cliente.rut,false);
			cell=table.getRow(1).getCell(3);setCelda(cell,"Arial",10,1,"2b5079",bodegaEmpresa.nombre,false);
			cell=table.getRow(2).getCell(1);setCelda(cell,"Arial",10,1,"2b5079",cliente.giro,false);
			cell=table.getRow(3).getCell(1);setCelda(cell,"Arial",10,1,"2b5079",cliente.direccion,false);
			cell=table.getRow(4).getCell(1);setCelda(cell,"Arial",10,1,"2b5079",cliente.region,false);
			cell=table.getRow(5).getCell(1);setCelda(cell,"Arial",10,1,"2b5079",cliente.comuna,false);
			cell=table.getRow(3).getCell(3);setCelda(cell,"Arial",10,1,"2b5079","de "+Fechas.DDMMAA(proforma.desde)+" a "+Fechas.DDMMAA(proforma.hasta),false);
			//cell=table.getRow(4).getCell(3);setCelda(cell,"Arial",10,1,"2b5079",proforma.docRef,false);
			cell=table.getRow(4).getCell(3);setCelda(cell,"Arial",10,1,"2b5079",bodegaEmpresa.comercial,false);

			cell=table.getRow(5).getCell(2);setCelda(cell,"Arial",9,1,"000000","DIR_PROY:",false);
			cell=table.getRow(5).getCell(3);setCelda(cell,"Arial",10,1,"2b5079",dirProyecto,false);


			table = doc.getTables().get(2);
			int auxLinea = 1;
			row = table.getRow(auxLinea);
			cell=row.getCell(1);setCelda(cell,"Arial",10,1,"2b5079","ALQUILERES",false);
			cell=row.getCell(2);setCelda(cell,"Arial",10,2,"2b5079","GL",false);
			cell=row.getCell(3);setCelda(cell,"Arial",10,2,"2b5079","1",false);
			cell=row.getCell(4);setCelda(cell,"Arial",10,3,"2b5079",form.get("alq"),false);
			cell=row.getCell(5);setCelda(cell,"Arial",10,3,"2b5079",form.get("alq"),false);
			table.createRow();
			auxLinea = auxLinea+1;
			row = table.getRow(auxLinea);
			cell=row.getCell(1);setCelda(cell,"Arial",10,1,"2b5079","VENTAS",false);
			cell=row.getCell(2);setCelda(cell,"Arial",10,2,"2b5079","GL",false);
			cell=row.getCell(3);setCelda(cell,"Arial",10,2,"2b5079","1",false);
			cell=row.getCell(4);setCelda(cell,"Arial",10,3,"2b5079",form.get("vta"),false);
			cell=row.getCell(5);setCelda(cell,"Arial",10,3,"2b5079",form.get("vta"),false);
			table.createRow();
			auxLinea = auxLinea+1;
			row = table.getRow(auxLinea);
			cell=row.getCell(1);setCelda(cell,"Arial",10,1,"2b5079","SERVICIOS",false);
			cell=row.getCell(2);setCelda(cell,"Arial",10,2,"2b5079","GL",false);
			cell=row.getCell(3);setCelda(cell,"Arial",10,2,"2b5079","1",false);
			cell=row.getCell(4);setCelda(cell,"Arial",10,3,"2b5079",form.get("serv"),false);
			cell=row.getCell(5);setCelda(cell,"Arial",10,3,"2b5079",form.get("serv"),false);
			table.createRow();

			table = doc.getTables().get(3);




			cell=table.getRow(0).getCell(2);
			setCelda(cell,"Arial",10,3,"2b5079",form.get("neto"),false);

			cell=table.getRow(1).getCell(1);
			setCelda(cell,"Arial",10,3,"2b5079","",false);
			cell=table.getRow(2).getCell(2);
			setCelda(cell,"Arial",10,3,"2b5079",form.get("neto"),false);
			cell=table.getRow(3).getCell(2);
			setCelda(cell,"Arial",10,3,"2b5079",form.get("iva"),false);
			cell=table.getRow(4).getCell(2);
			setCelda(cell,"Arial",10,3,"2b5079",form.get("total"),false);


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

			String archivoPdf = "EP_Prof_Simple_"+proforma.getId()+".pdf";
			path = db+"/"+archivoPdf;
			Archivos.grabarArchivo(tmp, path);

			return(archivoPdf);

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return("0");
	}
	
	public static String generaProformaVenta(Connection con, String db, WSClient ws, Map<String,String> mapDiccionario, Map<String,String> mapPermiso,
			Cliente cliente, Proforma proforma, XmlFacturaReferencias referencias, List<List<String>> detalleAjuste, 
			List<List<String>> guiasPer, Map<String,List<List<String>>> mapReportPorGuia10, String oc, String comentarios,
			FormFactura form) {
		
		BodegaEmpresa bodegaEmpresa = BodegaEmpresa.findXIdBodega(con, db, proforma.id_bodegaEmpresa);
		
		File tmp = TempFile.createTempFile("tmp","null");
       
		try {
			
			String path = db + "/formatos/proformaVenta.docx";
			
			InputStream formato = Archivos.leerArchivo(path);
			XWPFDocument doc = new XWPFDocument(formato);
			formato.close();
			
			
			XWPFTable table = null;
			XWPFTableRow row = null;
			XWPFTableCell cell = null;
			
			table = doc.getTables().get(0);
			row=table.getRow(1);cell=row.getCell(2);setCelda(cell,"Arial",10,2,"2b5079",Fechas.DDMMAA(proforma.fecha),false);
			row=table.getRow(2);cell=row.getCell(2);setCelda(cell,"Arial",12,2,"2b5079",proforma.id.toString(),false);
			
			Proyecto proyecto = Proyecto.find(con, db, bodegaEmpresa.id_proyecto);
			String dirProyecto = "";
			if(proyecto.id > 0) {
				dirProyecto = proyecto.getDireccion() + ", "+ proyecto.getComuna() + ", " + proyecto.getCiudad();
			}
			
			
			table= doc.getTables().get(1);
			cell=table.getRow(0).getCell(1);setCelda(cell,"Arial",10,1,"2b5079",cliente.nombre,false);
			cell=table.getRow(1).getCell(1);setCelda(cell,"Arial",10,1,"2b5079",cliente.rut,false);
			cell=table.getRow(1).getCell(3);setCelda(cell,"Arial",10,1,"2b5079",bodegaEmpresa.nombre,false);
			cell=table.getRow(2).getCell(1);setCelda(cell,"Arial",10,1,"2b5079",cliente.giro,false);
			cell=table.getRow(3).getCell(1);setCelda(cell,"Arial",10,1,"2b5079",cliente.direccion,false);
			cell=table.getRow(4).getCell(1);setCelda(cell,"Arial",10,1,"2b5079",cliente.region,false);
			cell=table.getRow(5).getCell(1);setCelda(cell,"Arial",10,1,"2b5079",cliente.comuna,false);
			cell=table.getRow(3).getCell(3);setCelda(cell,"Arial",10,1,"2b5079","de "+Fechas.DDMMAA(proforma.desde)+" a "+Fechas.DDMMAA(proforma.hasta),false);
			//cell=table.getRow(4).getCell(3);setCelda(cell,"Arial",10,1,"2b5079",proforma.docRef,false);
			cell=table.getRow(4).getCell(3);setCelda(cell,"Arial",10,1,"2b5079",bodegaEmpresa.comercial,false);
			
			cell=table.getRow(5).getCell(2);setCelda(cell,"Arial",9,1,"000000","DIR_PROY:",false);
			cell=table.getRow(5).getCell(3);setCelda(cell,"Arial",10,1,"2b5079",dirProyecto,false);
			
			Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
			switch(dec.get((long) 1).toString()) {
			 case "0": myformatdouble = new DecimalFormat("#,##0",symbols); break;
			 case "2": myformatdouble = new DecimalFormat("#,##0.00",symbols); break;
			 case "4": myformatdouble = new DecimalFormat("#,##0.0000",symbols); break;
			 case "6": myformatdouble = new DecimalFormat("#,##0.000000",symbols); break;
			 default:  break;
			}
			
			table = doc.getTables().get(2);
			Double totalVenta = (double)0;
	        int linea = 0;

            for(int i=0; i<guiasPer.size(); i++){
            	
            	List<List<String>> reportPorGuia = mapReportPorGuia10.get(guiasPer.get(i).get(8));
            	
            	if(reportPorGuia!=null) {
            		
            		boolean flag = true;
            		
            		for(int j=0; j<reportPorGuia.size(); j++){
            			
            			Double vta = Double.parseDouble(reportPorGuia.get(j).get(20).replaceAll(",", ""));
            			
            			if((double)vta > (double)0) {
            				
            				if(flag) {
                				linea ++;
                        		row = table.getRow(linea);
                        		cell = row.getCell(1);
                        		setCelda(cell,"Arial",10,1,"2b5079",mapDiccionario.get("GUIA")+ " "+guiasPer.get(i).get(2)+" de fecha "+guiasPer.get(i).get(3),false);
                        		table.createRow();
                        		flag = false;
                			}
            				
            				int celda = 0;
            				linea ++;
            				row = table.getRow(linea);
            				
            				String dato = reportPorGuia.get(j).get(10);
			            	cell = row.getCell(celda);
			            	setCelda(cell,"Arial",10,1,"2b5079",dato,false);
			            	celda++;
			            	
			            	dato = reportPorGuia.get(j).get(11);
			            	cell = row.getCell(celda);
			            	setCelda(cell,"Arial",10,1,"2b5079",dato,false);
			            	celda++;
			            	
			            	dato = reportPorGuia.get(j).get(12);
			            	cell = row.getCell(celda);
			            	setCelda(cell,"Arial",10,1,"2b5079",dato,false);
			            	celda++;
			            	
			            	dato = reportPorGuia.get(j).get(13);
			            	cell = row.getCell(celda);
			            	setCelda(cell,"Arial",10,1,"2b5079",dato,false);
			            	celda++;
            			
			            	Double cant = Double.parseDouble(reportPorGuia.get(j).get(13).replaceAll(",", ""));
			            	String pu = myformatdouble.format(vta/cant);
			            	
			            	cell = row.getCell(celda);
			            	setCelda(cell,"Arial",10,3,"2b5079",pu,false);
			            	celda++;
			            	
			            	cell = row.getCell(celda);
			            	setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(vta),false);
			            	celda++;
			            	
			            	totalVenta += vta;
			            	
			            	table.createRow();
            			}
		            	
            		}
            	}
            }
            
            EmisorTributario emisorTributario = EmisorTributario.find(con, db);
			proforma.setNeto(totalVenta);
			
			proforma.setIva(proforma.neto * emisorTributario.tasaIva / 100);
			
			if(mapPermiso.get("parametro.ivaPorBodega")!=null && mapPermiso.get("parametro.ivaPorBodega").equals("1")) {
	        	if(bodegaEmpresa!=null) {
	        		if(bodegaEmpresa.getIvaBodega() > 0) {
	        			proforma.setIva(proforma.neto * bodegaEmpresa.getIvaBodega());
	        		}else {
	        			Sucursal sucursal = Sucursal.find(con, db, bodegaEmpresa.getId_sucursal().toString());
	        			if(sucursal!=null && sucursal.getIvaSucursal() > 0) {
	        				proforma.setIva(proforma.neto * sucursal.getIvaSucursal());
	        			}
	        		}
	        		
	        	}
	        }
			
			proforma.setTotal(proforma.neto + proforma.iva);
			
			linea++;
			row = table.getRow(linea);
			cell=row.getCell(1);
			setCelda(cell,"Arial",10,1,"2b5079","SUBTOTALES",false);
			cell=row.getCell(2);
			setCelda(cell,"Arial",10,2,"2b5079","",false);
			cell=row.getCell(3);
			setCelda(cell,"Arial",10,2,"2b5079","",false);
			cell=row.getCell(4);
			setCelda(cell,"Arial",10,3,"2b5079","",false);
			cell=row.getCell(5);
			setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(totalVenta),false);
			table.createRow();
			
			Double totalAjuste = (double)0;
			
			for(List<String>ajuste: detalleAjuste){
				Double totAjuste = Double.parseDouble(ajuste.get(2).replaceAll(",", ""));
				if(totAjuste>0 || totAjuste<0) {
					table.createRow();
					linea++;
					row = table.getRow(linea);
    				cell=row.getCell(1);
    				setCelda(cell,"Arial",10,1,"2b5079",ajuste.get(0),false);
    				cell=row.getCell(2);
    				setCelda(cell,"Arial",10,2,"2b5079","",false);
    				cell=row.getCell(3);
    				setCelda(cell,"Arial",10,2,"2b5079","",false);
    				cell=row.getCell(4);
    				setCelda(cell,"Arial",10,3,"2b5079","",false);
    				cell=row.getCell(5);
    				setCelda(cell,"Arial",10,3,"2b5079",ajuste.get(2),false);
    				table.createRow();
    				totalAjuste += totAjuste;
				}
			}
			
			proforma.setNeto(totalVenta + totalAjuste);
			
			proforma.setIva(proforma.neto * emisorTributario.tasaIva / 100);
			
			if(mapPermiso.get("parametro.ivaPorBodega")!=null && mapPermiso.get("parametro.ivaPorBodega").equals("1")) {
	        	if(bodegaEmpresa!=null) {
	        		if(bodegaEmpresa.getIvaBodega() > 0) {
	        			proforma.setIva(proforma.neto * bodegaEmpresa.getIvaBodega());
	        		}else {
	        			Sucursal sucursal = Sucursal.find(con, db, bodegaEmpresa.getId_sucursal().toString());
	        			if(sucursal!=null && sucursal.getIvaSucursal() > 0) {
	        				proforma.setIva(proforma.neto * sucursal.getIvaSucursal());
	        			}
	        		}
	        		
	        	}
	        }
			
			proforma.setTotal(proforma.neto+proforma.iva);
			proforma.setNetoSinAjustes(totalVenta);
			proforma.setNetoSoloAjustes(totalAjuste);
			
			
			table = doc.getTables().get(3);
			
			
			int cantRef=0;
			try {cantRef=referencias.tpoDocRef.size();}catch(Exception e) {}
			String strReferencia = "";
			
			if(cantRef>0) {
				strReferencia="REFERENCIAS:\n";
			}
			
			for (int i = 0; i < cantRef; i++) {
				if(!referencias.tpoDocRef.get(i).equals("0")) {
					String docRef = TipoReferencia.find(con, db, referencias.tpoDocRef.get(i)).concepto;
					strReferencia = strReferencia +
							docRef+": "+referencias.folioRef.get(i)+" - "+referencias.fchRef.get(i)+" - "+referencias.razonRef.get(i)+"\n";
				}
				
			}
			
			if(oc!=null && oc.length()>0) {
				strReferencia += "\n" + oc;
			}else {
				oc = "";
			}
			
			if(comentarios!=null && comentarios.length()>1) {
				strReferencia += "\n COMENTARIOS: " + comentarios.trim();
			}else {
				comentarios = "";
			}
			
			cell=table.getRow(0).getCell(0);
			setCelda(cell,"Arial",10,1,"2b5079",strReferencia,false);
			
			 
			cell=table.getRow(0).getCell(2);
			setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(proforma.neto),false);
			
			cell=table.getRow(1).getCell(1);
			setCelda(cell,"Arial",10,3,"2b5079","",false);
			cell=table.getRow(2).getCell(2);
			setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(proforma.neto),false);
			cell=table.getRow(3).getCell(2);
			setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(proforma.iva),false);
			cell=table.getRow(4).getCell(2);
			setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(proforma.total),false);
	            
	            
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

			String archivoPdf = proforma.getProformaPdf();
			path = db+"/"+archivoPdf;
			Archivos.grabarArchivo(tmp, path);
			
			proforma.setComentarios(comentarios.trim());
			
			Proforma.modify(con, db, proforma);
			  
			if(mapPermiso.get("parametro.proformaLista-descargarXLM").equals("1")){
				String archivoXml = XmlFacturaVenta.generaXmlVenta(con, db, mapDiccionario.get("nEmpresa"), guiasPer, cliente, proforma, mapReportPorGuia10, mapPermiso);
				XmlFacturaReferencias.grabarReferencias(con, mapPermiso, db, archivoXml, referencias, proforma.getId());
            }
			if(mapPermiso.get("parametro.proformaListar-llenarApiManager").equals("1")){
            	String jsonApi = ApiManagerDocDoc.generaFactVenta(con, db, guiasPer, cliente, proforma, mapReportPorGuia10);
            	Proforma.updateJsonApi(con, db, proforma.id, jsonApi);
            }
            
			if(mapPermiso.get("parametro.proformaListar-llenarApiNubox").equals("1")){
				String jsonApi = ApiNuboxDocDoc.generaFactVenta(con, db, guiasPer, cliente, proforma, mapReportPorGuia10, emisorTributario);
            	Proforma.updateJsonApi(con, db, proforma.id, jsonApi);
			}
			
			//generaProformaWebIConstruye
			if(mapPermiso.get("parametro.proformaListar-llenarWebIConstruye")!=null && mapPermiso.get("parametro.proformaListar-llenarWebIConstruye").equals("1")){
				String archivoXml = WebIConstruye.generaXMLFacturasVta(con, db, mapDiccionario.get("nEmpresa"), guiasPer, cliente, 
						proforma, mapReportPorGuia10, mapPermiso, detalleAjuste, referencias, comentarios);
				Proforma.updateJsonApi(con, db, proforma.id, archivoXml);
			}
			
			if(mapPermiso.get("parametro.proformaListar-llenarApiRelBase")!=null && mapPermiso.get("parametro.proformaListar-llenarApiRelBase").equals("1")){
				String jsonApi = ApiRelBase.generaJsonFactARRVTA(con, db, ws, mapDiccionario, 
						referencias, cliente, proforma, form);
	            	Proforma.updateJsonApi(con, db, proforma.id, jsonApi);
			} 
			
			if(mapPermiso.get("parametro.proformaListar-llenarApiSapSchwager")!=null && mapPermiso.get("parametro.proformaListar-llenarApiSapSchwager").equals("1")){
				String jsonApi = ApiSapSchwager.generaJsonFactVTA(cliente, proforma.getId(), referencias, detalleAjuste, 
						guiasPer, mapReportPorGuia10);
	            	Proforma.updateJsonApi(con, db, proforma.id, jsonApi);
			} 
			
			return(archivoPdf);
	    
	    } catch (Throwable e) {
			e.printStackTrace();
	    }
		return("0");
	}
	
	public static String generaProformaOdo(Connection con, String db, Map<String,String> mapDiccionario, Map<String,String> mapPermiso, ProformaOdo proformaOdo, XmlFacturaReferencias referencias,
			List<List<String>> detalleProformaPorServicio, List<String> fechas, List<Double> tasaCambio, 
			BodegaEmpresa bodegaEmpresa, Proyecto proyecto, Cliente cliente, Long cantDec, List<List<String>> groupPorClaseServicioEquipo, List<List<String>> listaAjustes,
			List<List<String>> agrupadoPorServicio) {
		
			File tmp = TempFile.createTempFile("tmp","null");
	       
    		try {
    			
    			String path = db + "/formatos/proformaOdo.docx";
    			InputStream formato = Archivos.leerArchivo(path);
    			XWPFDocument doc = new XWPFDocument(formato);
    			formato.close();
    			
    			
    			XWPFTable table = null;
    			XWPFTableRow row = null;
				XWPFTableCell cell = null;
    			
    			table = doc.getTables().get(0);
    			row=table.getRow(1);cell=row.getCell(2);setCelda(cell,"Arial",10,2,"2b5079",Fechas.DDMMAA(proformaOdo.fecha),false);
    			row=table.getRow(2);cell=row.getCell(2);setCelda(cell,"Arial",12,2,"2b5079",proformaOdo.id.toString(),false);
    			table= doc.getTables().get(1);
    			cell=table.getRow(0).getCell(1);setCelda(cell,"Arial",10,1,"2b5079",cliente.nombre,false);
    			cell=table.getRow(1).getCell(1);setCelda(cell,"Arial",10,1,"2b5079",cliente.rut,false);
    			cell=table.getRow(1).getCell(3);setCelda(cell,"Arial",10,1,"2b5079",bodegaEmpresa.nombre,false);
    			cell=table.getRow(2).getCell(1);setCelda(cell,"Arial",10,1,"2b5079",cliente.giro,false);
    			cell=table.getRow(3).getCell(1);setCelda(cell,"Arial",10,1,"2b5079",cliente.direccion,false);
    			cell=table.getRow(4).getCell(1);setCelda(cell,"Arial",10,1,"2b5079",cliente.region,false);
    			cell=table.getRow(5).getCell(1);setCelda(cell,"Arial",10,1,"2b5079",cliente.comuna,false);
    			cell=table.getRow(3).getCell(3);setCelda(cell,"Arial",10,1,"2b5079","de "+Fechas.DDMMAA(proformaOdo.desde)+" a "+Fechas.DDMMAA(proformaOdo.hasta),false);
    			cell=table.getRow(4).getCell(3);setCelda(cell,"Arial",10,1,"2b5079",bodegaEmpresa.comercial,false);
    			
    			switch(cantDec.toString()) {
    			 case "0": myformatdouble = new DecimalFormat("#,##0",symbols); break;
    			 case "2": myformatdouble = new DecimalFormat("#,##0.00",symbols); break;
    			 case "4": myformatdouble = new DecimalFormat("#,##0.0000",symbols); break;
    			 case "6": myformatdouble = new DecimalFormat("#,##0.000000",symbols); break;
    			 default:  break;
    			}
    			
    			Double totalPrecio=(double)0;
    			
    			table = doc.getTables().get(2);
    			int auxLinea = 1;
    			for(int i=0; i<agrupadoPorServicio.size(); i++) {
    				Double precio = Double.parseDouble(agrupadoPorServicio.get(i).get(1).replaceAll(",", ""));
	   				totalPrecio += precio;
	   					row = table.getRow(auxLinea);
	    				cell=row.getCell(1);setCelda(cell,"Arial",10,1,"2b5079",agrupadoPorServicio.get(i).get(0),false);
	    				cell=row.getCell(2);setCelda(cell,"Arial",10,2,"2b5079","GL",false);
	    				cell=row.getCell(3);setCelda(cell,"Arial",10,2,"2b5079","1",false);
	    				cell=row.getCell(4);setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(precio),false);
	    				cell=row.getCell(5);setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(precio),false);
	    				table.createRow();
	    				auxLinea = auxLinea+1;

    			}
    			
    			EmisorTributario emisorTributario = EmisorTributario.find(con, db);
    			proformaOdo.setNeto(totalPrecio);
    			
    			proformaOdo.setIva(proformaOdo.neto * emisorTributario.tasaIva/100);
    			
    			if(mapPermiso.get("parametro.ivaPorBodega")!=null && mapPermiso.get("parametro.ivaPorBodega").equals("1")) {
		        	if(bodegaEmpresa!=null) {
		        		if(bodegaEmpresa.getIvaBodega() > 0) {
		        			proformaOdo.setIva(proformaOdo.neto * bodegaEmpresa.getIvaBodega());
		        		}else {
		        			Sucursal sucursal = Sucursal.find(con, db, bodegaEmpresa.getId_sucursal().toString());
		        			if(sucursal!=null && sucursal.getIvaSucursal() > 0) {
		        				proformaOdo.setIva(proformaOdo.neto * sucursal.getIvaSucursal());
		        			}
		        		}
		        		
		        	}
		        }
    			
    			proformaOdo.setTotal(proformaOdo.neto + proformaOdo.iva);
    			
    			auxLinea = auxLinea+1;
    			row = table.getRow(auxLinea);
				cell=row.getCell(1);
				setCelda(cell,"Arial",10,1,"2b5079","SUBTOTAL",false);
				cell=row.getCell(2);
				setCelda(cell,"Arial",10,2,"2b5079","",false);
				cell=row.getCell(3);
				setCelda(cell,"Arial",10,2,"2b5079","",false);
				cell=row.getCell(4);
				setCelda(cell,"Arial",10,3,"2b5079","",false);
				cell=row.getCell(5);
				setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(totalPrecio),false);
				table.createRow();
    			
				Double totalAjuste = (double)0;
				auxLinea = auxLinea+1;
				for(List<String> ajuste: listaAjustes){
					Double totAjuste = Double.parseDouble(ajuste.get(4).replaceAll(",", ""));
					if(totAjuste>0 || totAjuste<0) {
						row = table.getRow(auxLinea++);
        				cell=row.getCell(1);
        				setCelda(cell,"Arial",10,1,"2b5079",ajuste.get(5)+": "+ajuste.get(6),false);
        				cell=row.getCell(2);
        				setCelda(cell,"Arial",10,2,"2b5079","",false);
        				cell=row.getCell(3);
        				setCelda(cell,"Arial",10,2,"2b5079","",false);
        				cell=row.getCell(4);
        				setCelda(cell,"Arial",10,3,"2b5079","",false);
        				cell=row.getCell(5);
        				setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(totAjuste),false);
        				table.createRow();
        				totalAjuste += totAjuste;
					}
				}
    			
				proformaOdo.setNeto(totalPrecio + totalAjuste);
				
				proformaOdo.setIva(proformaOdo.neto * emisorTributario.tasaIva/100);
				
				if(mapPermiso.get("parametro.ivaPorBodega")!=null && mapPermiso.get("parametro.ivaPorBodega").equals("1")) {
		        	if(bodegaEmpresa!=null) {
		        		if(bodegaEmpresa.getIvaBodega() > 0) {
		        			proformaOdo.setIva(proformaOdo.neto * bodegaEmpresa.getIvaBodega());
		        		}else {
		        			Sucursal sucursal = Sucursal.find(con, db, bodegaEmpresa.getId_sucursal().toString());
		        			if(sucursal!=null && sucursal.getIvaSucursal() > 0) {
		        				proformaOdo.setIva(proformaOdo.neto * sucursal.getIvaSucursal());
		        			}
		        		}
		        		
		        	}
		        }
				
				proformaOdo.setTotal(proformaOdo.neto + proformaOdo.iva);
    			
				proformaOdo.setNetoSinAjustes(totalPrecio);
				proformaOdo.setNetoSoloAjustes(totalAjuste);
				
    			table = doc.getTables().get(3);
    			
    			
    			int cantRef=0;
    			try {
    				cantRef = referencias.tpoDocRef.size();
    			}catch(Exception e) {}
    			String strReferencia = "";
    			if(cantRef>0) {
    				strReferencia="REFERENCIAS:\n";
    			}
    			for (int i = 0; i < cantRef; i++) {
    				if(!referencias.tpoDocRef.get(i).equals("0")) {
    					String docRef = TipoReferencia.find(con, db, referencias.tpoDocRef.get(i)).concepto;
    					strReferencia = strReferencia +
    							docRef+": "+referencias.folioRef.get(i)+" - "+referencias.fchRef.get(i)+" - "+referencias.razonRef.get(i)+"\n";
    				}
    			}
    			cell=table.getRow(0).getCell(0);
    			setCelda(cell,"Arial",10,1,"2b5079",strReferencia,false);
    			
    			cell=table.getRow(0).getCell(2);
    			setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(proformaOdo.neto),false);
    			
    			cell=table.getRow(1).getCell(1);
    			setCelda(cell,"Arial",10,3,"2b5079","",false);
    			cell=table.getRow(2).getCell(2);
    			setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(proformaOdo.neto),false);
    			cell=table.getRow(3).getCell(2);
    			setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(proformaOdo.iva),false);
    			cell=table.getRow(4).getCell(2);
    			setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(proformaOdo.total),false);
    			
    			
    			//DETALLE SEGUNDA HOJA
    				

	    	        

		            table = doc.getTables().get(4);
		            
		            String dato = "";
		            int fontSize = 6;
		            
		            //INVENTARIO INICIAL

		            for(int i=0; i<detalleProformaPorServicio.size(); i++){
		            	
		            	int celda = 0;
		            	int linea = i+1;
		            	
		            	row = table.getRow(linea);
		            	
		            	dato = detalleProformaPorServicio.get(i).get(6);
		            	cell = row.getCell(celda);
		            	setCelda(cell,"Arial",fontSize,2,"2b5079",dato,false);
		            	celda++;
		            	
		            	dato = detalleProformaPorServicio.get(i).get(1);
		            	cell = row.getCell(celda);
		            	setCelda(cell,"Arial",fontSize,1,"2b5079",dato,false);
		            	celda++;
		            	
		            	dato = detalleProformaPorServicio.get(i).get(2);
		            	cell = row.getCell(celda);
		            	setCelda(cell,"Arial",fontSize,1,"2b5079",dato,false);
		            	celda++;
		            	
		            	dato = detalleProformaPorServicio.get(i).get(3);
		            	cell = row.getCell(celda);
		            	setCelda(cell,"Arial",fontSize,1,"2b5079",dato,false);
		            	celda++;
		            	
		            	
		            	dato = detalleProformaPorServicio.get(i).get(4);
		            	cell = row.getCell(celda);
		            	setCelda(cell,"Arial",fontSize,2,"2b5079",dato,false);
		            	celda++;
		            	
		            	dato = detalleProformaPorServicio.get(i).get(5);
		            	cell = row.getCell(celda);
		            	setCelda(cell,"Arial",fontSize,1,"2b5079",dato,false);
		            	celda++;
		            	
		            	dato = detalleProformaPorServicio.get(i).get(9);
		            	cell = row.getCell(celda);
		            	setCelda(cell,"Arial",fontSize,1,"2b5079",dato,false);
		            	celda++;
		            	
		            	dato = detalleProformaPorServicio.get(i).get(10);
		            	cell = row.getCell(celda);
		            	setCelda(cell,"Arial",fontSize,1,"2b5079",dato,false);
		            	celda++;
		            	
		            	dato = detalleProformaPorServicio.get(i).get(11);
		            	cell = row.getCell(celda);
		            	setCelda(cell,"Arial",fontSize,1,"2b5079",dato,false);
		            	celda++;
		            	
		            	dato = detalleProformaPorServicio.get(i).get(14);
		            	cell = row.getCell(celda);
		            	setCelda(cell,"Arial",fontSize,3,"2b5079",dato,false);
		            	celda++;
		            	
		            	Double totalPesos = Double.parseDouble(detalleProformaPorServicio.get(i).get(19).replaceAll(",", ""));
		            	Double cantidad = Double.parseDouble(detalleProformaPorServicio.get(i).get(14).replaceAll(",", ""));
		            	
		            	dato = myformatdouble2.format(totalPesos/cantidad);
		            	cell = row.getCell(celda);
		            	setCelda(cell,"Arial",fontSize,3,"2b5079",dato,false);
		            	celda++;
						
		            	dato = detalleProformaPorServicio.get(i).get(19);
		            	cell = row.getCell(celda);
		            	setCelda(cell,"Arial",fontSize,3,"2b5079",dato,false);
		            	celda++;
						
		            	table.createRow();
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

				String archivoPdf = proformaOdo.getProformaPdf();
				path = db+"/"+archivoPdf;
				Archivos.grabarArchivo(tmp, path);
				
				ProformaOdo.modify(con, db, proformaOdo);
  
				
				return(archivoPdf);
    	    
        } catch (Throwable e) {
			e.printStackTrace();
        }
		return("0");
	}
	
	
	private static void setCelda (XWPFTableCell cell,String fontFamily,int fontSize,int alingH,String colorRGB,String text,boolean bold) {
		cell.removeParagraph(0);
		XWPFParagraph paragraph =null;
		if(text==null) {
			text="--";
		}else if(text.trim().length()==0) {
			text="--";
		}
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
