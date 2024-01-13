package models.api;


import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.util.Base64;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


import models.tables.EmisorTributario;
import models.tables.Guia;
import models.tables.Proforma;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;



public class WebIConstruye {

	public WebIConstruye() {
		super();
	}
	
	
//**************************************************************
// CONSUME LOS SERVICIOS EN WEB ICONSTRUYE
//**************************************************************
	
	public static String genera(Connection con, String db, String xml, WSClient ws, Long id_proforma, Long id_guia) {
		
		EmisorTributario emisor = EmisorTributario.find(con, db);
		
		String webUser = emisor.apiUser;
		String webRut = emisor.getRut();
		String webClave = emisor.apiKey;
		String webUrl = emisor.apiUrl;
		int tipoDocumento = 0;
		if((long) id_proforma > (long) 0) {
			tipoDocumento = 33;
		}
		if((long) id_guia > (long) 0) {
			tipoDocumento = 52;
		}
		
		byte[] bytes = xml.getBytes(StandardCharsets.UTF_8); 
		String xmlEncoded = Base64.getEncoder().encodeToString(bytes);
		
		String data = "<soapenv:Envelope xmlns:iconstruye=\"http://dteservice.iconstruye.cl\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tns=\"http://PITrusted.iconstruye.cl/\">\n"
				+ "			<soapenv:Header/>\n"
				+ "			<soapenv:Body>\n"
				+ "				<tns:Procesar>\n"
				+ "					<tns:cliente>"+webUser+"</tns:cliente>\n"
				+ "					<tns:password>"+webClave+"</tns:password>\n"
				+ "					<tns:data>\n"
				+ "						&lt;Firmar xmlns=&quot;http://dteservice.iconstruye.cl/&quot;&gt;\n"
				+ "							&lt;xmlBase64&gt;"+xmlEncoded+"&lt;/xmlBase64&gt;\n"
				+ "							&lt;rutEmisor&gt;"+webRut.substring(0,8)+"&lt;/rutEmisor&gt;\n"
				+ "							&lt;rutFirma&gt;"+webRut.substring(0,8)+"&lt;/rutFirma&gt;\n"
				+ "							&lt;rutEnvia&gt;"+webRut.substring(0,8)+"&lt;/rutEnvia&gt;\n"
				+ "							&lt;applicationId&gt;A478E031-A07F-4C9D-B672-8BEBC64B3281&lt;/applicationId&gt;\n"
				+ "							&lt;tipoDocumento&gt;"+tipoDocumento+"&lt;/tipoDocumento&gt;\n"
				+ "							&lt;estadoDocumento&gt;0&lt;/estadoDocumento&gt;\n"
				+ "						&lt;/Firmar&gt;\n"
				+ "					</tns:data>\n"
				+ "				</tns:Procesar>\n"
				+ "			</soapenv:Body>\n"
				+ "		</soapenv:Envelope>";
		
		try {
			
			String rs = ws.url(webUrl)
					.addHeader("Content-Type","text/xml")
					.addHeader("SOAPAction","http://PITrusted.iconstruye.cl/IIntegraciones/Procesar")
					.post(data)
					.thenApply(
			          (WSResponse response) -> {
			        	  String rsBody = "response.getBody() = \n"+response.getBody();
			        	  
			        	  rsBody = rsBody.replaceAll("&lt;", "<");
				       	  rsBody = rsBody.replaceAll("&gt;", ">");
				       	  
				       	  return rsBody;
			           }
			         ).toCompletableFuture().get(600000,TimeUnit.MILLISECONDS);
			
			
			String rsBody = rs;
			
	       	String folioStr = "";
	       	
	       	int tipoMensajeIni = rsBody.indexOf("<b:TipoMensaje>") + 15;
	       	int tipoMensajeFin = rsBody.indexOf("</b:TipoMensaje>");
	       	
	       	int iniMsg = rsBody.indexOf("<b:Mensaje>") + 11;
			int finMsg = rsBody.indexOf("</b:Mensaje>");
	       	
			
			String tipoMensaje = "";
			String msg = "";
			
			if(tipoMensajeFin > 0 && finMsg > 0) {
				tipoMensaje = rsBody.substring(tipoMensajeIni,tipoMensajeFin);
				msg = rsBody.substring(iniMsg,finMsg);
			}
			
			int flag =0;
			
			if(tipoMensaje.trim().equals("Info") && msg.trim().equals("Correcto")) {
				
				int existeFolio = rsBody.indexOf("<Folio/>");
		       	  if(existeFolio < 0) {
		       		int iniFolio = rsBody.indexOf("<Folio>") + 7;
		       		int finFolio = rsBody.indexOf("</Folio>");
		       		folioStr = rsBody.substring(iniFolio,finFolio);
		       		Proforma.updateNroFiscal(con, db, id_proforma, folioStr);
		       		flag = 1;
		       	  }else {
		       		folioStr = "ERROR: NO SE ASIGNO FOLIO";
		       		flag = 2;
		       		
		       	  }
			}
			
			if(id_proforma > 0) {
				Proforma.updateResponse(con, db, id_proforma, rsBody);
			}
			
			if(id_guia > 0) {
				Guia.modificaPorCampo(con, db, "response", id_guia, rsBody);
			}
			
			if(flag == 1) {
				return("DTE generado con éxito, folio: "+folioStr);
			}else if(flag == 2){
				return("ERROR: NO SE ASIGNO FOLIO");
			}else {
				return(msg);
			}
				
			
		} catch (java.lang.InterruptedException | java.util.concurrent.ExecutionException | TimeoutException e) {
			e.printStackTrace();
			return "FALLA";
		}
		
	}
	
//**************************************************************
// FINAL DE CONSUME LOS SERVICIOS EN FACTURACION.CL
//**************************************************************
	
	
	
	
	
	
}
