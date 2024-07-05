package models.api;


import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.poi.util.TempFile;

import models.tables.BodegaEmpresa;
import models.tables.Cliente;
import models.tables.Comunas;
import models.tables.EmisorTributario;
import models.tables.Guia;
import models.tables.Proforma;
import models.tables.Proyecto;
import models.tables.TasasCambio;
import models.tables.Transportista;
import models.utilities.Fechas;
import models.xml.XmlFacturaReferencias;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;



public class WebMaximise {
	
	public List<ApiNuboxDocDet> productos;
	public ApiNuboxDocRef documentoReferenciado;

	public WebMaximise(List<ApiNuboxDocDet> productos, ApiNuboxDocRef documentoReferenciado) {
		super();
		this.productos = productos;
		this.documentoReferenciado = documentoReferenciado;
	}

	public WebMaximise() {
		super();
	}
	
	
//**************************************************************
// CONSUME LOS SERVICIOS EN MAXIMISE GUIA:
//**************************************************************
	
	public static String generaGuia(Connection con, String db, String xml, WSClient ws, EmisorTributario emisorTributario, Long id_guia) {
		
		String webUser = emisorTributario.getApiUser();
		String webClave = emisorTributario.getApiKey();
		String webUrl = "http://asp2.maximise.cl/wsv/WayBill.asmx";
		
		String data = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"
				+ "		<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n"
				+ "			<soap:Body>\n"
				+ "		    	<SaveDocument xmlns=\"MaximiseWS\">\n"
				+ "		      		<AliasName>"+webUser+"</AliasName>\n"
				+ "		      		<UserName>webservice</UserName>\n"
				+ "		      		<Password>"+webClave+"</Password>\n"
				+ "		      		<Data>\n"
				+						xml
				+ "					</Data>\n"
				+ "    			</SaveDocument>\n"
				+ "			</soap:Body>\n"
				+ "		</soap:Envelope>";
		
		try {
			
			String rs = ws.url(webUrl)
					.addHeader("Content-Type","text/xml; charset=utf-8")
					.addHeader("SOAPAction","MaximiseWS/SaveDocument")
					.post(data)
					.thenApply(
			          (WSResponse response) -> {
			        	  String rsBody = "response.getBody() = \n"+response.getBody();
			        	  Guia.modificaPorCampo(con, db, "response", id_guia, rsBody);
			        	  
			        	  if(rsBody.indexOf("<SaveDocumentResult>")>0) {
			        		  	int ini = rsBody.indexOf("<SaveDocumentResult>");
					       		int fin = rsBody.indexOf("</SaveDocumentResult>");
					       		rsBody = rsBody.substring(ini+20,fin);
					       		rsBody = rsBody.replaceAll("(\\n|\\r)", "");
			        	  }else if (rsBody.indexOf("<faultcode>")>0) {
			        		  	int ini = rsBody.indexOf("<faultstring>");
					       		int fin = rsBody.indexOf("</faultstring>");
					       		rsBody = rsBody.substring(ini+13,fin);
					       		rsBody = rsBody.replaceAll("(\\n|\\r)", "");
			        	  }else {
					       		rsBody = rsBody.replaceAll("(\\n|\\r)", "");
			        	  }
			        	    
				       	  return rsBody;
			           }
			         ).toCompletableFuture().get(600000,TimeUnit.MILLISECONDS);
			
			
			return(rs);
				
			
		} catch (java.lang.InterruptedException | java.util.concurrent.ExecutionException | TimeoutException e) {
			e.printStackTrace();
			return "FALLA: SE PRESENTARON ERRORES";
		}
	}
	
	public static File downGuiaMaximise(Connection con, String db, String nroIntGuia, WSClient ws, EmisorTributario emisorTributario) {
		
		String webUser = emisorTributario.getApiUser();
		String webClave = emisorTributario.getApiKey();
		String webUrl = "http://asp2.maximise.cl/wsv/WayBill.asmx";
		
		String data = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"
				+ "		<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n"
				+ "			<soap:Body>\n"
				+ "		    	<DownloadPdf xmlns=\"MaximiseWS\">\n"
				+ "		      		<AliasName>"+webUser+"</AliasName>\n"
				+ "		      		<UserName>webservice</UserName>\n"
				+ "		      		<Password>"+webClave+"</Password>\n"
				+ "		      		<Company>01</Company>\n"
				+ "					<DocumentNumber>"+nroIntGuia+"</DocumentNumber>\n"
				+ "    			</DownloadPdf>\n"
				+ "			</soap:Body>\n"
				+ "		</soap:Envelope>";
		
		
		try {
			
			File rs = ws.url(webUrl)
					.addHeader("Content-Type","text/xml; charset=utf-8")
					.addHeader("SOAPAction","MaximiseWS/DownloadPdf")
					.post(data)
					.thenApply(
			          (WSResponse response) -> {
			        	 Map<String,List<String>> head = response.getHeaders();
			        	 if(head.get("Content-Disposition") == null) {
			        		 return(null);
			        	 }else {
			        		 List<String> aux = head.get("Content-Disposition");
			        		 if(aux.get(0).trim().equals("attachment; filename= documento.pdf")) {
			        			byte[] x = response.asByteArray();
					        	File file = TempFile.createTempFile("tmp","null");
					        	OutputStream os;
								try {
									os = new FileOutputStream(file);
									os.write(x);
									os.close();
									return(file);
								} catch (Exception e) {
									e.printStackTrace();
									return(null);
								}
			        		 }else {
			        			 return(null);
			        		 }
			        	 }
			           }
			         ).toCompletableFuture().get(600000,TimeUnit.MILLISECONDS);
			return(rs);
				
			
		} catch (java.lang.InterruptedException | java.util.concurrent.ExecutionException | TimeoutException e) {
			e.printStackTrace();
			return null;
		}
	}
	

	
	
	public static String generaGuiaSalida (Connection con, String db, Guia guia, Transportista transportista, Map<String,String> mapDiccionario, EmisorTributario emisorTributario, String sucurMaximise) {
		
		//******************************
		// OBTIENE DATOS A LLENAR
		//******************************
			
			Long id_bodegaDestino = guia.getId_bodegaDestino();
			BodegaEmpresa bodegaDestino = BodegaEmpresa.findXIdBodega(con, db,id_bodegaDestino);
			Cliente cliente = Cliente.find(con, db, bodegaDestino.getId_cliente());
			
			
			List<List<String>> detalleGuia = new ArrayList<List<String>>();
			String direccionDestino="", comunaDestino="", ciudadDestino="", rutCliente="", nickCliente="", patTransp="", rutTransp="", nomTransp="";
			
			
			if(cliente != null) {
				rutCliente = cliente.getRut().replace(".", "").replace(".", "").replace(".", "").replace(".", "");
				rutCliente = rutCliente.replace(",", "").replace(",", "").replace(",", "").replace(",", "");
				rutCliente = rutCliente.trim();
				nickCliente = cliente.getNickName();
			}
			
			if ((long) bodegaDestino.getEsInterna() == (long) 1) {
				Cliente aux = Cliente.find(con, db, bodegaDestino.getId_cliente());
				if(aux!=null) {
					direccionDestino = aux.getDireccion();
					comunaDestino = aux.getCod_comuna();
					Comunas comuna = Comunas.findPorNombre(con, db, aux.getComuna());
					if(comuna != null) {
						Long cod_ciudad = Long.parseLong(comuna.codigo); //se elimina el 0 antepuesto en comuna
						ciudadDestino = cod_ciudad.toString();
					}
				}
				detalleGuia = Guia.findDetalleGuiaOrigenDestinoYPrecios(con, db, guia.getId(), guia.getId_bodegaOrigen(), mapDiccionario.get("pais"), guia.getId_bodegaOrigen());
			}else {
				Proyecto aux = Proyecto.find(con, db, bodegaDestino.id_proyecto);
				if(aux!=null) {
					direccionDestino = aux.getDireccion();
					comunaDestino = aux.getCod_comuna();
					Comunas comuna = Comunas.findPorNombre(con, db, aux.getComuna());
					if(comuna != null) {
						Long cod_ciudad = Long.parseLong(comuna.codigo);
						ciudadDestino = cod_ciudad.toString();
					}
				}
				detalleGuia = Guia.findDetalleGuiaOrigenDestinoYPrecios(con, db, guia.getId(), guia.getId_bodegaDestino(), mapDiccionario.get("pais"), guia.getId_bodegaOrigen());
			}
			
			if(direccionDestino.length()>30) {
				direccionDestino = direccionDestino.substring(0,30);
			}
			
			if(transportista != null) {
				patTransp = transportista.getPatente();
				rutTransp = transportista.getRutConductor().replace(".", "").replace(".", "").replace(".", "").replace(".", "");
				rutTransp = rutTransp.replace(",", "").replace(",", "").replace(",", "").replace(",", "");
				rutTransp = rutTransp.trim();
				nomTransp = transportista.getConductor();
			}
			
			Fechas auxFecha = Fechas.obtenerFechaDesdeStrAAMMDD(Fechas.AAMMDD(guia.fecha));
			Map<Long,Double> tasaCambio = TasasCambio.mapTasasPorFecha(con, db, auxFecha.getFechaStrAAMMDD(), mapDiccionario.get("pais"));
			
			Long totalNeto = (long)0;
			Long totalIva = (long)0;
			
			for(int i=0;i<detalleGuia.size();i++) {
				
				String auxPrecio = detalleGuia.get(i).get(9); 				// precio de venta en moneda de origen
				if(auxPrecio==null || auxPrecio.trim().length()<=0)	{
					auxPrecio = "0";
				}
				Double precioUnitario = Double.parseDouble(auxPrecio.replaceAll(",", ""));
				String auxCantidad = detalleGuia.get(i).get(8);				// cantidad
				if(auxCantidad==null || auxCantidad.trim().length()<=0) {
					auxCantidad = "0";
				}
				Long id_moneda = Long.parseLong(detalleGuia.get(i).get(30));
				Double tasa = tasaCambio.get(id_moneda);
				if(tasa==null) {
					tasa = (double)1;
				}
				precioUnitario = precioUnitario * tasa;		// precio de venta en moneda principal CLP
				
				Double cantidad = Double.parseDouble(auxCantidad.replaceAll(",", ""));
				Double auxPTotal = precioUnitario * cantidad;
				totalNeto += Math.round(auxPTotal);
				totalIva += Math.round(auxPTotal * (emisorTributario.getTasaIva()/100));
				
			}
			
			
			String bodega = "BSTGO";
			if(sucurMaximise.equals("2")) {
				bodega = "BANTO";
			}
		//******************************
		// FINAL DE OBTIENE DATOS A LLENAR
		//******************************
		
		DecimalFormat myformat = new DecimalFormat("0");
		//******************************
		// LLENA XML para MAXIMISE
		//******************************
		
		String[] auxFechGuia = Fechas.AAMMDD(guia.getFecha()).split("-");
		Long auxMesGuia = Long.parseLong(auxFechGuia[1]);
		
		
			String xml = 
					"&lt;WAYBILL&gt;\n"
					+ "		&lt;HEAD&gt;\n"
					+ "			&lt;CMPY_CODE&gt;01&lt;/CMPY_CODE&gt;\n"
					+ "			&lt;NAME_TEXT&gt;"+nickCliente+"&lt;/NAME_TEXT&gt;\n"
					+ "			&lt;LAST_INV_TYPE&gt;G&lt;/LAST_INV_TYPE&gt;\n"
					+ "			&lt;ENTRY_CODE&gt;webservice&lt;/ENTRY_CODE&gt;\n"
					+ "			&lt;ENTRY_DATE&gt;"+Fechas.AAMMDD(guia.getFecha())+"&lt;/ENTRY_DATE&gt;\n"
					+ "			&lt;GUIA_DATE&gt;"+Fechas.AAMMDD(guia.getFecha())+"&lt;/GUIA_DATE&gt;\n"
					+ "			&lt;YEAR_NUM&gt;"+Fechas.AAMMDD(guia.getFecha()).substring(0,4)+"&lt;/YEAR_NUM&gt;\n"
					+ "			&lt;PERIOD_NUM&gt;"+auxMesGuia+"&lt;/PERIOD_NUM&gt;\n"
					+ "			&lt;COD_MOV&gt;GDT&lt;/COD_MOV&gt;\n"
					+ "			&lt;GOODS_AMT&gt;"+myformat.format(totalNeto)+"&lt;/GOODS_AMT&gt;\n"
					+ "			&lt;TAX_AMT&gt;"+myformat.format(totalIva)+"&lt;/TAX_AMT&gt;\n"
					+ "			&lt;TOTAL_AMT&gt;"+myformat.format((totalNeto + totalIva))+"&lt;/TOTAL_AMT&gt;\n"
					+ "			&lt;LINE_NUM&gt;1&lt;/LINE_NUM&gt;\n"
					+ "			&lt;COM_TEXT&gt;"+guia.observaciones+"&lt;/COM_TEXT&gt;\n"
					+ "			&lt;SHIP_CODE&gt;"+rutCliente+"&lt;/SHIP_CODE&gt;\n"
					+ "			&lt;SHIP_TEXT&gt;"+direccionDestino+"&lt;/SHIP_TEXT&gt;\n"
					+ "			&lt;SHIP_IDCOMUNA&gt;"+ciudadDestino+"&lt;/SHIP_IDCOMUNA&gt;\n"
					+ "			&lt;SHIP_IDCIUDAD&gt;"+ciudadDestino+"&lt;/SHIP_IDCIUDAD&gt;\n"
					+ "			&lt;SHIP_IDDISTRITO&gt;"+comunaDestino+"&lt;/SHIP_IDDISTRITO&gt;\n"
					+ "			&lt;SHIP_NAME_TEXT&gt;"+nickCliente+"&lt;/SHIP_NAME_TEXT&gt;\n"
					+ "			&lt;SHIP_IDPAIS&gt;01&lt;/SHIP_IDPAIS&gt;\n"
					+ "			&lt;SHIP_LICENCEPLATE&gt;"+patTransp+"&lt;/SHIP_LICENCEPLATE&gt;\n"
					+ "			&lt;SHIP_DRIVERNAME&gt;"+nomTransp+"&lt;/SHIP_DRIVERNAME&gt;\n"
					+ "			&lt;SHIP_DRIVERCODE&gt;"+rutTransp+"&lt;/SHIP_DRIVERCODE&gt;\n"
					+ "			&lt;SHIP_DATE&gt;"+Fechas.AAMMDD(guia.getFecha())+"&lt;/SHIP_DATE&gt;\n"
					+ "			&lt;PRINTED_FLAG&gt;N&lt;/PRINTED_FLAG&gt;\n"
					+ "			&lt;POSTED_FLAG&gt;N&lt;/POSTED_FLAG&gt;\n"
					+ "			&lt;ORIGEN_IND&gt;I&lt;/ORIGEN_IND&gt;\n"
					+ "			&lt;TRANTYPE_IND&gt;G&lt;/TRANTYPE_IND&gt;\n"
					+ "			&lt;REF_CODE&gt;"+rutCliente+"&lt;/REF_CODE&gt;\n"
					+ "			&lt;TAX_CODE&gt;IDF&lt;/TAX_CODE&gt;\n"
					+ "			&lt;TAX_PER&gt;"+(emisorTributario.getTasaIva())+"&lt;/TAX_PER&gt;\n"
					+ "			&lt;ACCT_CODE&gt;11010701&lt;/ACCT_CODE&gt;\n"
					+ "			&lt;BRAN_CODE&gt;"+sucurMaximise+"&lt;/BRAN_CODE&gt;\n"
					+ "			&lt;PROFIT_CODE /&gt;\n"
					+ "			&lt;CURRENCY_CODE&gt;CLP&lt;/CURRENCY_CODE&gt;\n"
					+ "			&lt;RATE_EXCHANGE&gt;1&lt;/RATE_EXCHANGE&gt;\n"
					+ "			&lt;ESPTAX_AMT&gt;0&lt;/ESPTAX_AMT&gt;\n"
					+ "			&lt;IDPROYECTO /&gt;\n"
					+ "			&lt;DISC_PER&gt;0&lt;/DISC_PER&gt;\n"
					+ "			&lt;DISC_AMT&gt;0&lt;/DISC_AMT&gt;\n"
					+ "			&lt;IMPORT_PER&gt;1&lt;/IMPORT_PER&gt;\n"
					+ "		&lt;/HEAD&gt;\n";
			
			
			for(int i=0;i<detalleGuia.size();i++) {
				//obtiene datos
					String auxPrecio = detalleGuia.get(i).get(9); 				// precio de venta en moneda de origen
					if(auxPrecio==null || auxPrecio.trim().length()<=0)	{
						auxPrecio = "0";
					}
					Long precioUnitario = (long)0;
					Double auxPU = Double.parseDouble(auxPrecio.replaceAll(",", ""));
					String auxCantidad = detalleGuia.get(i).get(8);				// cantidad
					if(auxCantidad==null || auxCantidad.trim().length()<=0) {
						auxCantidad = "0";
					}
					Long id_moneda = Long.parseLong(detalleGuia.get(i).get(30));
					Double tasa = tasaCambio.get(id_moneda);
					if(tasa==null) {
						tasa = (double)1;
					}
					precioUnitario = Math.round(auxPU * tasa);		// precio de venta en moneda principal CLP
					
					Long cantidad = Math.round(Double.parseDouble(auxCantidad.replaceAll(",", "")));
					Long auxPTotal = Math.round(auxPU * tasa * cantidad);
					
					Long auxIvaTotal =  Math.round(auxPTotal * (emisorTributario.getTasaIva()/100));
					Long auxIvaPU =  Math.round(precioUnitario * (emisorTributario.getTasaIva()/100));
				//fin obtiene datos
					
				// llena json
					
					String producto = detalleGuia.get(i).get(6);
					if(producto.length()>35) {
						producto = producto.substring(0,35);
					}
					
					
					xml +=
						  "		&lt;DETAIL&gt;\n"
						+ "			&lt;CMPY_CODE&gt;01&lt;/CMPY_CODE&gt;\n"
						+ "			&lt;LINE_NUM&gt;"+(i+1)+"&lt;/LINE_NUM&gt;\n"
						+ "			&lt;PART_CODE&gt;"+detalleGuia.get(i).get(5)+"&lt;/PART_CODE&gt;\n"	// codigo
						+ "			&lt;START_WARE_CODE&gt;"+bodega+"&lt;/START_WARE_CODE&gt;\n"
						+ "			&lt;END_WARE_CODE&gt;"+bodega+"&lt;/END_WARE_CODE&gt;\n"
						+ "			&lt;ORDER_QTY&gt;"+myformat.format(cantidad)+"&lt;/ORDER_QTY&gt;\n"
						+ "			&lt;DESC_TEXT&gt;"+producto+"&lt;/DESC_TEXT&gt;\n" // equipo
						+ "			&lt;UNIT_PRICE_AMT&gt;"+myformat.format(precioUnitario)+"&lt;/UNIT_PRICE_AMT&gt;\n"
						+ "			&lt;EXT_PRICE_AMT&gt;"+myformat.format(auxPTotal)+"&lt;/EXT_PRICE_AMT&gt;\n"
						+ "			&lt;UNIT_TAX_AMT&gt;"+myformat.format(auxIvaPU)+"&lt;/UNIT_TAX_AMT&gt;\n"	// iva punitario
						+ "			&lt;EXT_TAX_AMT&gt;"+myformat.format(auxIvaTotal)+"&lt;/EXT_TAX_AMT&gt;\n"	// iva total
						+ "			&lt;LINE_TOT_AMT&gt;"+myformat.format(auxIvaTotal + auxPTotal)+"&lt;/LINE_TOT_AMT&gt;\n"
						+ "			&lt;ACCT_CODE&gt;11010701&lt;/ACCT_CODE&gt;\n"
						+ "			&lt;UNIT_COST_AMT&gt;0&lt;/UNIT_COST_AMT&gt;\n"
						+ "			&lt;EXT_COST_AMT&gt;0&lt;/EXT_COST_AMT&gt;\n"
						+ "			&lt;UNIT_COST_AMT1&gt;0&lt;/UNIT_COST_AMT1&gt;\n"
						+ "			&lt;PROFIT_CODE/&gt;\n"
						+ "			&lt;UOM_CODE&gt;UN&lt;/UOM_CODE&gt;\n"
						+ "			&lt;UOM_QTY&gt;"+myformat.format(cantidad)+"&lt;/UOM_QTY&gt;\n"
						+ "			&lt;UNIT_IFRSCOST_AMT&gt;0&lt;/UNIT_IFRSCOST_AMT&gt;\n"
						+ "		&lt;/DETAIL&gt;\n";
							
			}
			xml += 
				"&lt;/WAYBILL&gt;";
			
			
		return(xml);
		
	}
	
	
	//**************************************************************
	// CONSUME LOS SERVICIOS EN MAXIMISE FACTURA:
	//**************************************************************
	
	public static String generaFactura(Connection con, String db, String xmlEncode, WSClient ws, EmisorTributario emisorTributario, Long id_proforma) {
		String webUser = emisorTributario.getApiUser();
		String webClave = emisorTributario.getApiKey();
		String webUrl = "http://asp2.maximise.cl/wsv/SalesOrder.asmx/SaveOrder";
		String data = "AliasName="+webUser+"&UserName=webservice&Password="+webClave+"&Data="+xmlEncode;
		try {
			String rs = ws.url(webUrl)
					.addHeader("Content-Type","application/x-www-form-urlencoded")
					.post(data)
					.thenApply(
			          (WSResponse response) -> {
			        	  String rsBody = "response.getBody() = \n"+response.getBody();
			        	  Proforma.updateResponse(con, db, id_proforma, rsBody);
			        	  if(rsBody.indexOf("<string xmlns=")>0) {
			        		  	int ini = rsBody.indexOf("<string xmlns=");
					       		int fin = rsBody.indexOf("</string>");
					       		rsBody = rsBody.substring(ini+36,fin);
					       		rsBody = rsBody.replaceAll("(\\n|\\r)", "");
			        	  }else {
					       		rsBody = rsBody.replaceAll("(\\n|\\r)", "");
			        	  }
				       	  return rsBody;
			           }
			         ).toCompletableFuture().get(600000,TimeUnit.MILLISECONDS);
			return(rs);
		} catch (java.lang.InterruptedException | java.util.concurrent.ExecutionException | TimeoutException e) {
			e.printStackTrace();
			return "FALLA: SE PRESENTARON ERRORES";
		}
	}
	
	
	
	
	public static String encodeXmlArriendo(Connection con, String db, String nEmpresa, List<List<String>> resumenSubtotales, Cliente cliente, Proforma proforma, Map<String,String> mapPermiso, 
			List<List<String>> detalleAjuste, XmlFacturaReferencias referencias) {
		
		EmisorTributario emisorTributario = EmisorTributario.find(con, db);
				
		String order_date = proforma.fecha;
		
		String bodega = "";
		String periodoDesde = proforma.desde;
		String periodoHasta = proforma.hasta;
		
		BodegaEmpresa bodegaEmpresa = BodegaEmpresa.findXIdBodega(con, db, proforma.getId_bodegaEmpresa());
		if(bodegaEmpresa!=null) {
			bodega = bodegaEmpresa.getNombre();
		}
		
		Long neto = Math.round(proforma.getNeto());
		Long iva = Math.round(proforma.getIva());
		Long total = neto+iva;
		String rutCliente = "";
		String nameCliente = "";
		String direccionCliente = "";
		String comunaCliente = "";
		String ciudadCliente = "";
		if(cliente != null) {
			rutCliente = cliente.getRut().replace(".", "").replace(".", "").replace(".", "").replace(".", "");
			rutCliente = rutCliente.replace(",", "").replace(",", "").replace(",", "").replace(",", "");
			rutCliente = rutCliente.trim();
			nameCliente = cliente.getNombre();
			direccionCliente = cliente.getDireccion();
			comunaCliente = cliente.getCod_comuna();
			Comunas comuna = Comunas.findPorNombre(con, db, cliente.getComuna());
			if(comuna != null) {
				Long cod_ciudad = Long.parseLong(comuna.codigo); //se elimina el 0 antepuesto en comuna
				ciudadCliente = cod_ciudad.toString();
			}
			if(direccionCliente.length()>30) {
				direccionCliente = direccionCliente.substring(0,30);
			}
		}
		
		
		String fechaOC = "";
		String nroOC = "";
		
		String fechaHES = "";
		String nroHES = "";
		
		String agregaReferencias = "";
		
		int cantRef=0;
		try {
			cantRef=referencias.tpoDocRef.size();
		}catch(Exception e) {}
		
		for (int i = 0; i < cantRef; i++) {
			
			if(!referencias.tpoDocRef.get(i).equals("0")) {
				
				if(referencias.tpoDocRef.get(i).equals("801")) {
					fechaOC = referencias.fchRef.get(i);
					nroOC = referencias.folioRef.get(i);
					agregaReferencias += "<ORD_TEXT>"+nroOC+"</ORD_TEXT>" + "<ORD_DATE>"+fechaOC+"</ORD_DATE>";
				}
				
				if(referencias.tpoDocRef.get(i).equals("HES")) {
					fechaHES = referencias.fchRef.get(i);
					nroHES = referencias.folioRef.get(i);
					agregaReferencias += "<ORD_TEXT1>"+nroHES+"</ORD_TEXT1>" + "<ORD_DATE1>"+fechaHES+"</ORD_DATE1>";
				}
				
			}
			
		}
		
			
		DecimalFormat myformat = new DecimalFormat("0");
 
		//******************************
		// LLENA XML para MAXIMISE
		//******************************
			String xml = 
					"<Documento>"
					+ "		<head>"
					+ "			<CMPY_CODE>01</CMPY_CODE>"
					+ "			<CUST_CODE>"+rutCliente+"</CUST_CODE>"
					+ "			<ENTRY_CODE>pmada</ENTRY_CODE>"
					+ "			<ENTRY_DATE>"+order_date+"</ENTRY_DATE>"
					+ "			<ORDER_DATE>"+order_date+"</ORDER_DATE>"
					+ "			<SALES_CODE>4</SALES_CODE>"
					+ "			<SALES_CODE1/>"
					+ "			<TERM_CODE>30D</TERM_CODE>"
					+ "			<TAX_CODE>IDF</TAX_CODE>"
					+ "			<CURR_CODE>CLP</CURR_CODE>"
					+ "			<RATE_EXCHANGE>1</RATE_EXCHANGE>"
					+ "			<FLAG_CALC_INV>S</FLAG_CALC_INV>"
					+ "			<GOODS_AMT>"+myformat.format(neto)+"</GOODS_AMT>"
					+ "			<TAX_AMT>"+myformat.format(iva)+"</TAX_AMT>"
					+ "			<TOTAL_AMT>"+myformat.format(total)+"</TOTAL_AMT>"
					+ "			<COST_AMT>0</COST_AMT>"
					+ "			<STATUS_IND>A</STATUS_IND>"
					+ "			<LINE_NUM>1</LINE_NUM>"
					+ "			<SHIP_CODE>"+rutCliente+"</SHIP_CODE>"
					+ "			<SHIP_NAME_TEXT>"+nameCliente+"</SHIP_NAME_TEXT>"
					+ "			<SHIP_ADDR_TEXT>"+direccionCliente+"</SHIP_ADDR_TEXT>"
					+ "			<SHIP_IDCOMUNA>"+ciudadCliente+"</SHIP_IDCOMUNA>"
					+ "			<SHIP_IDCIUDAD>"+ciudadCliente+"</SHIP_IDCIUDAD>"
					+ "			<SHIP_IDDISTRITO>"+comunaCliente+"</SHIP_IDDISTRITO>"
					+ "			<SHIP_IDPAIS>01</SHIP_IDPAIS>"
					+ "			<BILL_CODE>"+rutCliente+"</BILL_CODE>"
					+ "			<BILL_NAME_TEXT>"+nameCliente+"</BILL_NAME_TEXT>"
					+ "			<BILL_ADDR_TEXT>"+direccionCliente+"</BILL_ADDR_TEXT>"
					+ "			<BILL_IDCOMUNA>"+ciudadCliente+"</BILL_IDCOMUNA>"
					+ "			<BILL_IDCIUDAD>"+ciudadCliente+"</BILL_IDCIUDAD>"
					+ "			<BILL_IDDISTRITO>"+comunaCliente+"</BILL_IDDISTRITO>"
					+ "			<BILL_IDPAIS>01</BILL_IDPAIS>"
					+ "			<HOLD_CODE>N</HOLD_CODE>"
					+ "			<SALETYPE_CODE>GRL</SALETYPE_CODE>"
					+ "			<TAX_PER>"+(emisorTributario.getTasaIva()/100)+"</TAX_PER>"
					+ "			<BRAN_CODE>2</BRAN_CODE>"
					+ "			<TYPE_IND>V</TYPE_IND>"
					+ "			<APPROVAL_REQUIRED>N</APPROVAL_REQUIRED>"
					+ "			<DUE_DATE>"+order_date+"</DUE_DATE>"
					+ "			<COMM_PER>0</COMM_PER>"
					+ "			<COMM_PER1>0</COMM_PER1>"
					+ "			<DISC_AMT>0</DISC_AMT>"
					+ "			<SHIPBY_IND>U</SHIPBY_IND>"
					+ "			<PACKBY_IND>U</PACKBY_IND>"
					+ "			<PRIORITY_IND>4</PRIORITY_IND>"
					+ "			<MEATTAX_AMT>0</MEATTAX_AMT>"
					+ "			<INSURANCE_IND/>"
					+ "			<SELTAX_AMT>0</SELTAX_AMT>"
					+ "			<ILA1TAX_AMT>0</ILA1TAX_AMT>"
					+ "			<ILA2TAX_AMT>0</ILA2TAX_AMT>"
					+ "			<ILA3TAX_AMT>0</ILA3TAX_AMT>"
					+ "			<ILA4TAX_AMT>0</ILA4TAX_AMT>"
					+ "			<ILA5TAX_AMT>0</ILA5TAX_AMT>"
					+ "			<RETTAX_PER>0</RETTAX_PER>"
					+ "			<RET_PER>0</RET_PER>"
					+ "			<RET_PER1>0</RET_PER1>"
					+ "			<RET_PER2>0</RET_PER2>"
					+ "			<RET_AMT>0</RET_AMT>"
					+ "			<RET_AMT1>0</RET_AMT1>"
					+ "			<RET_AMT2>0</RET_AMT2>"
					+ "			<RETTAX_AMT>0</RETTAX_AMT>"
					+ "			<RECTAX_AMT>0</RECTAX_AMT>"
					+ "			<CUSTPICK_FLAG>N</CUSTPICK_FLAG>"
					+ "			<INTERESTCHARGE_IND>N</INTERESTCHARGE_IND>"
					
					+ agregaReferencias
					
					+ "		</head>"
					+ "		<detail>"
					+ "			<CMPY_CODE>01</CMPY_CODE>"
					+ "			<LINE_NUM>1</LINE_NUM>"
					+ "			<PART_CODE>colocaPartCode</PART_CODE>"
					+ "			<WARE_CODE>colocaWareCode</WARE_CODE>"
					+ "			<SHIP_DATE>"+order_date+"</SHIP_DATE>"
					+ "			<UOM_CODE>UN</UOM_CODE>"
					+ "			<UOM_QTY>1</UOM_QTY>"
					+ "			<ORDER_QTY>1</ORDER_QTY>"
					+ "			<SCHED_QTY>0</SCHED_QTY>"
					+ "			<INV_QTY>1</INV_QTY>"
					+ "			<BACK_QTY>0</BACK_QTY>"
					+ "			<PICKED_QTY>0</PICKED_QTY>"
					+ "			<CONF_QTY>0</CONF_QTY>"
					+ "			<DESC_TEXT>colocaDescText</DESC_TEXT>"
					+ "			<EXT_PRICE_AMT>"+myformat.format(neto)+"</EXT_PRICE_AMT>"
					+ "			<UNIT_TAX_AMT>"+myformat.format(iva)+"</UNIT_TAX_AMT>"
					+ "			<EXT_TAX_AMT>"+myformat.format(iva)+"</EXT_TAX_AMT>"
					+ "			<LINE_TOT_AMT>"+myformat.format(total)+"</LINE_TOT_AMT>"
					+ "			<ACCT_CODE>41010101</ACCT_CODE>"
					+ "			<UNIT_COST_AMT>0</UNIT_COST_AMT>"
					+ "			<EXT_COST_AMT>0</EXT_COST_AMT>"
					+ "			<LEVEL_IND/>"
					+ "			<UNIT_PRICE_AMT>"+myformat.format(neto)+"</UNIT_PRICE_AMT>"
					+ "			<LIST_PRICE_AMT>0</LIST_PRICE_AMT>"
					+ "			<CUST_PRICE_AMT>"+myformat.format(neto)+"</CUST_PRICE_AMT>"
					+ "			<DISC_CODE>03</DISC_CODE>"
					+ "			<PLENGHT>0</PLENGHT>"
					+ "			<PHEIGHT>0</PHEIGHT>"
					+ "			<PWIDTH>0</PWIDTH>"
					+ "			<UNIT_VOL_QTY>0</UNIT_VOL_QTY>"
					+ "			<DESC_TEXT1>OBRA: "+bodega+" PERIODO: de "+periodoDesde+" a "+periodoHasta+"</DESC_TEXT1>"
					+ "		</detail>"
					+ "	</Documento>";
			String xmlEncode = "ERROR";
			try {
				xmlEncode = URLEncoder.encode(xml, StandardCharsets.UTF_8.toString());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
					
	  return(xmlEncode);
	}
	
	
	
	
	
	public static String generaFactVenta(Connection con, String db, List<List<String>> guiasPer, Cliente cliente, Proforma proforma, Map<String,List<List<String>>> mapReportPorGuia10, EmisorTributario emisorTributario) {
		String datos = "";
		
		//******************************************************
     	//************       FALTA PROGRAMAR        ************
     	//******************************************************
		
		
		return(datos);
	}
	
	
	public static File downFacturaMaximise(Connection con, String db, String nroIntFactura, WSClient ws, EmisorTributario emisorTributario) {
		String webUser = emisorTributario.getApiUser();
		String webClave = emisorTributario.getApiKey();
		String webUrl = "http://asp2.maximise.cl/wsv/Invoice.asmx/DownloadPdf";
		String data = "AliasName="+webUser+"&UserName=webservice&Password="+webClave+"&Company=01&DocumentNumber="+nroIntFactura;
		try {
			File rs = ws.url(webUrl)
					.addHeader("Content-Type","application/x-www-form-urlencoded")
					.addHeader("SOAPAction","MaximiseWS/DownloadPdf")
					.post(data)
					.thenApply(
			          (WSResponse response) -> {
			        	 Map<String,List<String>> head = response.getHeaders();
			        	 if(head.get("Content-Disposition") == null) {
			        		 return(null);
			        	 }else {
			        		 List<String> aux = head.get("Content-Disposition");
			        		 if(aux.get(0).trim().equals("attachment; filename= documento.pdf")) {
			        			byte[] x = response.asByteArray();
					        	File file = TempFile.createTempFile("tmp","null");
					        	OutputStream os;
								try {
									os = new FileOutputStream(file);
									os.write(x);
									os.close();
									return(file);
								} catch (Exception e) {
									e.printStackTrace();
									return(null);
								}
			        		 }else {
			        			 return(null);
			        		 }
			        	 }
			           }
			         ).toCompletableFuture().get(600000,TimeUnit.MILLISECONDS);
			return(rs);
		} catch (java.lang.InterruptedException | java.util.concurrent.ExecutionException | TimeoutException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String downOrderMaximise(Connection con, String db, WSClient ws, EmisorTributario emisorTributario, Long nroIntOrden) {
			
			String webUser = emisorTributario.getApiUser();
			String webClave = emisorTributario.getApiKey();
			String webUrl = "http://asp2.maximise.cl/wsv/SalesOrder.asmx/OrderListWmsNotReady";
			String data = "AliasName="+webUser+"&UserName=webservice&Password="+webClave+"&Company=01";
			try {
				String rs = ws.url(webUrl)
						.addHeader("Content-Type","application/x-www-form-urlencoded")
						.post(data)
						.thenApply(
				          (WSResponse response) -> {
				        	  String rsBody = "response.getBody() = \n"+response.getBody();
				        	  int ini = rsBody.indexOf("&lt;ORDER_NUM&gt;"+nroIntOrden+"&lt;/ORDER_NUM&gt;");
				        	  if(ini>0) {
				        		  	rsBody = rsBody.substring(ini);
						       		int fin = rsBody.indexOf("&lt;ENTRY_CODE&gt;");
						       		rsBody = rsBody.substring(0,fin);
						       		if(rsBody.indexOf("&lt;LAST_INV_NUM&gt;")>0) {
						       			ini = rsBody.indexOf("&lt;LAST_INV_NUM&gt;");
						       			fin = rsBody.indexOf("&lt;/LAST_INV_NUM&gt;");
						       			rsBody = rsBody.substring(ini+20,fin);
						       		}else {
						       			rsBody = "DTE aun no emitido";
						       		}
				        	  }else {
				        		  rsBody = "Orden no existente";
				        	  }
				        	  rsBody = rsBody.replaceAll("(\\n|\\r)", "");
					       	  return rsBody;
				           }
				         ).toCompletableFuture().get(600000,TimeUnit.MILLISECONDS);
				return(rs);
			} catch (java.lang.InterruptedException | java.util.concurrent.ExecutionException | TimeoutException e) {
				e.printStackTrace();
				return "FALLA: SE PRESENTARON ERRORES";
			}
	}
	
	
	
	
	
	
	
	
	
}
