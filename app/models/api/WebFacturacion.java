package models.api;


import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.util.Base64;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


import controllers.HomeController;
import models.tables.ActaBaja;
import models.tables.EmisorTributario;
import models.tables.Guia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;



public class WebFacturacion {

	public WebFacturacion() {
		super();
	}

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

//**************************************************************
// CONSUME LOS SERVICIOS EN FACTURACION.CL:
//**************************************************************
	
public static String genera(Connection con, String db, String xml, WSClient ws, Long id_guia) {
	try {
		EmisorTributario emisor = EmisorTributario.find(con, db);
		String webUser = emisor.apiUser;
		String webRut = emisor.getRut();
		String webClave = emisor.apiKey;
		//		String webRut = "1-9";
		//		String webClave = "plano91098";
		String webPuerto = "0";
		String webLink = "0";
		String webUrl = emisor.apiUrl;
		String webFormato = "2";
		byte[] bytes = webUser.getBytes(StandardCharsets.UTF_8);
		String webUserEncoded = Base64.getEncoder().encodeToString(bytes);
		bytes = webRut.getBytes(StandardCharsets.UTF_8);
		String webRutEncoded = Base64.getEncoder().encodeToString(bytes);
		bytes = webClave.getBytes(StandardCharsets.UTF_8);
		String webClaveEncoded = Base64.getEncoder().encodeToString(bytes);
		bytes = webPuerto.getBytes(StandardCharsets.UTF_8);
		String webPuertoEncoded = Base64.getEncoder().encodeToString(bytes);
		bytes = xml.getBytes(StandardCharsets.UTF_8);
		String xmlEncoded = Base64.getEncoder().encodeToString(bytes);
		String data = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://tempuri.org\" xmlns:sdn=\"http://wsdl.sinacofi.cl/SDN122REQ\">\n"
				+ "			<soapenv:Header/>\n"
				+ "				<soapenv:Body>\n"
				+ "					<web:Procesar>\n"
				+ "						<web:login>\n"
				+ "							<web:Usuario>"+webUserEncoded+"</web:Usuario>\n"
				+ "							<web:Rut>"+webRutEncoded+"</web:Rut>\n"
				+ "							<web:Clave>"+webClaveEncoded+"</web:Clave>\n"
				+ "							<web:Puerto>"+webPuertoEncoded+"</web:Puerto>\n"
				+ "							<web:IncluyeLink>"+webLink+"</web:IncluyeLink>\n"
				+ "						</web:login>\n"
				+ "						<web:file>"+xmlEncoded+"</web:file>\n"
				+ "						<web:formato>"+webFormato+"</web:formato>\n"
				+ "					</web:Procesar>\n"
				+ "				</soapenv:Body>\n"
				+ "			</soapenv:Envelope>";
		String rs = ws.url(webUrl)
				.addHeader("Content-Type","text/xml")
				.addHeader("SOAPAction","http://tempuri.org/Procesar")
				.post(data)
				.thenApply(
				  (WSResponse response) -> {
					  String rsBody = "response.getBody() = \n"+response.getBody();
					  int ini = rsBody.indexOf("<ProcesarResult>");
						int fin = rsBody.indexOf("</ProcesarResult>");
						rsBody = rsBody.substring(ini+16,fin);
						rsBody = rsBody.replaceAll("&amp;", "&");
						rsBody = rsBody.replaceAll("&lt;", "<");
						rsBody = rsBody.replaceAll("&gt;", ">");
						ini = rsBody.indexOf("<Documento>");
						fin = rsBody.indexOf("</Documento>");
						rsBody = rsBody.substring(ini+11,fin);
						Guia.modificaPorCampo(con, db, "response", id_guia, rsBody);
						ini = rsBody.indexOf("<Resultado>");
						fin = rsBody.indexOf("</Resultado>");
						String auxError = rsBody.substring(ini+11,fin);
						if(auxError.equals("True")) {
							return(rsBody);
						}else {
							return(auxError);
						}

				   }
				 ).toCompletableFuture().get(600000,TimeUnit.MILLISECONDS);
		return rs;
	} catch (java.lang.InterruptedException | java.util.concurrent.ExecutionException | TimeoutException e) {
		String className = WebFacturacion.class.getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		return "FALLA";
	}
}

//**************************************************************
// FINAL DE CONSUME LOS SERVICIOS EN FACTURACION.CL
//**************************************************************

	public static String obtieneLinkDte(Connection con, String db, WSClient ws, String folio) {
		try {
			EmisorTributario emisor = EmisorTributario.find(con, db);
			String webUser = emisor.apiUser;
			String webRut = emisor.getRut();
			String webClave = emisor.apiKey;
			String webTpomov = "V";
			String webFolio = folio.trim();
			String webTipo = "52";
			String webCedible = "False";
			String webPuerto = "0";
			String webLink = "0";
			String webUrl = emisor.apiUrl;
			byte[] bytes = webUser.getBytes(StandardCharsets.UTF_8);
			String webUserEncoded = Base64.getEncoder().encodeToString(bytes);
			bytes = webRut.getBytes(StandardCharsets.UTF_8);
			String webRutEncoded = Base64.getEncoder().encodeToString(bytes);
			bytes = webClave.getBytes(StandardCharsets.UTF_8);
			String webClaveEncoded = Base64.getEncoder().encodeToString(bytes);
			bytes = webPuerto.getBytes(StandardCharsets.UTF_8);
			String webPuertoEncoded = Base64.getEncoder().encodeToString(bytes);
			bytes = webTpomov.getBytes(StandardCharsets.UTF_8);
			String webTpomovEncoded = Base64.getEncoder().encodeToString(bytes);
			bytes = webFolio.getBytes(StandardCharsets.UTF_8);
			String webFolioEncoded = Base64.getEncoder().encodeToString(bytes);
			bytes = webTipo.getBytes(StandardCharsets.UTF_8);
			String webTipoEncoded = Base64.getEncoder().encodeToString(bytes);
			bytes = webCedible.getBytes(StandardCharsets.UTF_8);
			String webCedibleEncoded = Base64.getEncoder().encodeToString(bytes);
			String data = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://tempuri.org\" xmlns:sdn=\"http://wsdl.sinacofi.cl/SDN122REQ\">\n"
					+ "			<soapenv:Header/>\n"
					+ "				<soapenv:Body>\n"
					+ "					<web:ObtenerLink>\n"
					+ "						<web:login>\n"
					+ "							<web:Usuario>"+webUserEncoded+"</web:Usuario>\n"
					+ "							<web:Rut>"+webRutEncoded+"</web:Rut>\n"
					+ "							<web:Clave>"+webClaveEncoded+"</web:Clave>\n"
					+ "							<web:Puerto>"+webPuertoEncoded+"</web:Puerto>\n"
					+ "							<web:IncluyeLink>"+webLink+"</web:IncluyeLink>\n"
					+ "						</web:login>\n"
					+ "						<web:tpomov>"+webTpomovEncoded+"</web:tpomov>\n"    	// C = Compra / V = Venta / B = Boleta
					+ "						<web:folio>"+webFolioEncoded+"</web:folio>\n"
					+ "						<web:tipo>"+webTipoEncoded+"</web:tipo>\n"   		// tipo DTE 52 guia 33 factura
					+ "						<web:cedible>"+webCedibleEncoded+"</web:cedible>\n"	// True = Se obtiene copia Cedible. False = Se obtiene la copia Original.
					+ "					</web:ObtenerLink>\n"
					+ "				</soapenv:Body>\n"
					+ "			</soapenv:Envelope>";
			String rs = ws.url(webUrl)
					.addHeader("Content-Type","text/xml")
					.addHeader("SOAPAction","http://tempuri.org/ObtenerLink")
					.post(data)
					.thenApply(
			          (WSResponse response) -> {
			        	  String rsBody = "response.getBody() = \n"+response.getBody();
				       		rsBody = rsBody.replaceAll("&amp;", "&");
				       		rsBody = rsBody.replaceAll("&lt;", "<");
				       		rsBody = rsBody.replaceAll("&gt;", ">");
				       		String decodedString = "0";
				       		int ini = rsBody.indexOf("faultcode");
				       		if(ini==-1) {
				       			ini = rsBody.indexOf("<Mensaje>");
					       		int fin = rsBody.indexOf("</Mensaje>");
					       		rsBody = rsBody.substring(ini+9,fin);
					       		byte[] decodedBytes = Base64.getDecoder().decode(rsBody);
					       		decodedString = new String(decodedBytes);
				       		}
			        	  return(decodedString);
			           }
			         ).toCompletableFuture().get(10000,TimeUnit.MILLISECONDS);
			rs.replace("http:", "https:");
			return rs;
		} catch (java.lang.InterruptedException | java.util.concurrent.ExecutionException | TimeoutException e) {
			String className = WebFacturacion.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
			return "FALLA";
		}
		
	}
	
	
	
	
	
}
