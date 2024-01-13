package models.api;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.fasterxml.jackson.databind.JsonNode;

import models.tables.BodegaEmpresa;
import models.tables.Cliente;
import models.tables.EmisorTributario;
import models.tables.Guia;
import models.tables.Proforma;
import models.utilities.Archivos;
import models.xml.XmlFacturaReferencias;
import play.libs.Json;
import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;
import play.libs.ws.WSResponse;



public class ApiSapConconcreto {
	public String GrpVendedor; 		// obligatorio
	public String OfVentas; 		// obligatorio
	public String OrgVentas;
	public String CanalDist;
	public String Sector;
	public String RespPedido;
	public String MotivoPedido; 	// obligatorio
	public String IdProforma;		// aqui va el id de la proforma
	public String NumPedido;		// aqui va la OC desde referencias
	public String Material; 		// obligatorio
	public String Cantidad;
	public String Solicitante; 		// obligatorio
	public String Destinatario; 	// obligatorio
	public String Valortotal; 		// obligatorio
	public String Moneda;
	public byte[] Proforma;
	public String AvisoDesp;		// aqui va aviso de despacho desde referencias
	public String TextoCabecera;	// aqui va observaciones desde referencias
//	public String TextoPosicion;

	public ApiSapConconcreto(String grpVendedor, String ofVentas, String orgVentas, String canalDist, String sector,
			String respPedido, String motivoPedido, String idProforma, String numPedido, String material, String cantidad,
			String solicitante, String destinatario, String valortotal, String moneda, byte[] proforma, String avisoDesp, String textoCabecera
			//, String textoPosicion
			) {
		super();
		this.GrpVendedor = grpVendedor;
		this.OfVentas = ofVentas;
		this.OrgVentas = orgVentas;
		this.CanalDist = canalDist;
		this.Sector = sector;
		this.RespPedido = respPedido;
		this.MotivoPedido = motivoPedido;
		this.IdProforma = idProforma;
		this.NumPedido = numPedido;
		this.Material = material;
		this.Cantidad = cantidad;
		this.Solicitante = solicitante;
		this.Destinatario = destinatario;
		this.Valortotal = valortotal;
		this.Moneda = moneda;
		this.Proforma = proforma;
		this.AvisoDesp = avisoDesp;
		this.TextoCabecera = textoCabecera;
//		this.TextoPosicion = textoPosicion;
	}


	public ApiSapConconcreto() {
		super();
	}
	
	
//**************************************************************
// CONSUME LOS SERVICIOS EN SAP CONCONCRETO:
//**************************************************************
	
	public static String genera(Connection con, String db, String json, WSClient ws, Long id_proforma, Long id_guia) {
		
		EmisorTributario emisor = EmisorTributario.find(con, db);
		
		String apiUser = emisor.apiUser;
		String apiKey = emisor.apiKey;
		String apiUrl = emisor.apiUrl;
		
		String original = apiUser+":"+apiKey; 
		byte[] bytes = original.getBytes(StandardCharsets.UTF_8); 
		String base64Encoded = Base64.getEncoder().encodeToString(bytes);
		String credenciales = "Basic "+base64Encoded;
		
		
		try {
			String rs = ws.url(apiUrl)
					.addHeader("Content-Type","application/json")
					.addHeader("Accept","application/json")
					.addHeader("Authorization",credenciales)
					.post("")
					.thenApply(
			          (WSResponse response) -> {
			        	  JsonNode jsonNode = response.asJson();
			        	  String token = jsonNode.findPath("access_token").asText();
			        	  token = "Bearer " + token;
			        	  
			        	  String url2 = "https://l251056-iflmap.hcisbp.us3.hana.ondemand.com/http/crear_factura";
			        	  WSRequest rss = ws.url(url2)
			        				.addHeader("Content-Type","application/json")
		                			.addHeader("Accept","application/json")
		                			.addHeader("Authorization",token);
			        	  
			        	  return(rss.post(json)
			        			  .thenApply(
			        						
						          (WSResponse response2) -> {
						        	  
						        	  String respuesta = response2.getBody();
						        	  
						        	  int pos0 = respuesta.indexOf("<ExFactura/>");
						        	  int pos1 = respuesta.indexOf("<ExFactura>") + 11;
						        	  int pos2 = respuesta.indexOf("</ExFactura");
						        	  
						        	  int pos0resp = respuesta.indexOf("<ExMsg/>");
						        	  int pos1resp = respuesta.indexOf("<ExMsg>") + 7;
						        	  int pos2resp = respuesta.indexOf("</ExMsg");
							        	  
						        	  if(pos0 < 0 && pos1 > 11 && pos2 > 0 && pos1 < pos2) {
						        		  
						        		  String nroFact = respuesta.substring(pos1,pos2);
						        		  
						        		  if((long) id_proforma > (long) 0) {
						        			  models.tables.Proforma.updateNroFiscal(con, db, id_proforma, nroFact);
						        			  models.tables.Proforma.updateResponse(con, db, id_proforma, respuesta);
							        	  }
							        	  if((long) id_guia > (long) 0) {
							        		  Guia.modificaPorCampo(con, db, "response", id_guia, respuesta);
							        	  }
							        	  
							        	  
							        	  if(pos0resp < 0 && pos1resp > 7 && pos2resp > 0 && pos1resp < pos2resp) {
							        		  respuesta = respuesta.substring(pos1resp,pos2resp);
							        	  }else {
							        		  respuesta = "API SAP Conconcreto enviada con éxito";
							        	  }
						        	  }else{
						        		  models.tables.Proforma.updateResponse(con, db, id_proforma, respuesta);
						        		  
						        		  if(pos0resp < 0 && pos1resp > 7 && pos2resp > 0 && pos1resp < pos2resp) {
							        		  respuesta = respuesta.substring(pos1resp,pos2resp);
							        	  }else {
							        		  respuesta = "ERROR: " + respuesta;
							        	  }
						        	  }
						        	  
						        	  return(respuesta);
						          }
						     ).toCompletableFuture());
			            }).toCompletableFuture().get(600000,TimeUnit.MILLISECONDS).get(600000,TimeUnit.MILLISECONDS);
			
			return rs;
		} catch (java.lang.InterruptedException | java.util.concurrent.ExecutionException | TimeoutException e) {
			e.printStackTrace();
			return "FALLA";
		}
	}
	
	//**************************************************************
	// FINAL DE CONSUME LOS SERVICIOS SAP CONCONCRETO
	//**************************************************************
	

	static DecimalFormat myformatapi = new DecimalFormat("#.00");

	
	public static String generaFactArriendo(Connection con, String db, List<List<String>> resumenSubtotales, Cliente cliente, Proforma proforma, Map<String,String> mapDiccionario, EmisorTributario emisorTributario, 
			List<List<String>> detalleAjuste, BodegaEmpresa bodegaEmpresa, XmlFacturaReferencias referencias) {
		
		//	List<ApiSapConconcreto> detalle = new ArrayList<ApiSapConconcreto>();
			
			
			Double granTotalNeto = (double) 0;
			
			for(int i=0;i<resumenSubtotales.size();i++) {
				
				Double auxPU = Double.parseDouble(resumenSubtotales.get(i).get(1).replaceAll(",", ""));
				auxPU += Double.parseDouble(resumenSubtotales.get(i).get(3).replaceAll(",", ""));
				
				granTotalNeto += auxPU;
				
				
			}
			
			for(int i=0; i<detalleAjuste.size(); i++){
				Double totAjuste = Double.parseDouble(detalleAjuste.get(i).get(1).replaceAll(",", ""));
				
				if(totAjuste>0 || totAjuste<0) {
					
					granTotalNeto += totAjuste;
					
				}
			}
			
			if((double) granTotalNeto > (double) 0) {
				ApiSapConconcreto det = new ApiSapConconcreto();
				
				String nomBodega = bodegaEmpresa.getNombre().trim();
				String ccost = nomBodega.substring(0,5);
				
				Double iva = bodegaEmpresa.getIvaBodega();
				String motivo = "0";
				
				if((double)iva == (double) 0.19) {
					motivo = "99";
				}else if ((double)iva == (double) 0.16) {
					motivo = "96";
				}
					
				
				cliente.rut = cliente.rut.trim();
				cliente.rut = cliente.rut.replace(".", "").replace(".", "").replace(".", "").replace(".", "").replace(".", "");
				cliente.rut = cliente.rut.replace(",", "").replace(",", "").replace(",", "").replace(",", "").replace(",", "");
				cliente.rut = cliente.rut.replace(" ", "").replace(" ", "").replace(" ", "").replace(" ", "").replace(" ", "");
				cliente.rut = cliente.rut.replace("-", "").replace("-", "").replace("-", "").replace("-", "").replace("-", "");

				
				InputStream inputPdf = Archivos.leerArchivo(db+"/"+proforma.getProformaPdf());
				File filePdf = Archivos.parseInputStreamToFile(inputPdf);
				
				byte[] bytes = null;
				try {
					FileInputStream fileInputStream = new FileInputStream(filePdf);
					BufferedInputStream bufferInputStream = new BufferedInputStream(fileInputStream);
					ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
					BufferedOutputStream bout = new BufferedOutputStream(byteArrayOutputStream);
					byte[] buffer = new byte[1024];
					int len = bufferInputStream.read(buffer);
					while (len != -1) {
						bout.write(buffer, 0, len);
						len = bufferInputStream.read(buffer);
		 
					}
		 
					bout.flush();
					bytes = byteArrayOutputStream.toByteArray();
					bufferInputStream.close();

				} catch (Exception e) {
					 
					e.printStackTrace();
				}
				
				
				//referencias
				
				String numPedido = "0";
				String avisoDesp = "0";
				String textoCabecera = "0";
				if(referencias.tpoDocRef!=null) {
					for(int i=0; i<referencias.tpoDocRef.size(); i++) {
						if(referencias.tpoDocRef.get(i).equals("DESP")) {
							avisoDesp = referencias.folioRef.get(i);
						}
						if(referencias.tpoDocRef.get(i).equals("OBS")) {
							textoCabecera = referencias.folioRef.get(i);
						}
						if(referencias.tpoDocRef.get(i).equals("801")) {
							numPedido = referencias.folioRef.get(i);
						}
					}
				}
				
				
				det.GrpVendedor = "32";
				det.OfVentas = "0001";
				det.OrgVentas = "01";
				det.CanalDist = "07";
				det.Sector = "01";
				det.RespPedido = "Dummy SOAP";
				det.MotivoPedido = motivo; //aqui va el IVA
				det.IdProforma = proforma.id.toString();
				det.NumPedido = numPedido;
				det.Material = "1000000073";
				det.Cantidad = "1";
				det.Solicitante = cliente.rut; //nit cliente
				det.Destinatario = ccost; // centro de costo
				det.Valortotal = myformatapi.format(granTotalNeto); //total neto
				det.Moneda = "COP";
				det.Proforma = bytes;
				det.AvisoDesp = avisoDesp;
				det.TextoCabecera = textoCabecera;
				
		//	detalle.add(det);
				
				
				
				
				String datos = "{ \"ImDataPedido\": " + Json.toJson(det).toString()+ "}";
				
				return(datos);
				
			}else {
				String datos = "{ \"Mensaje\": \"ERROR\"}";
				return(datos);
			}
			
	}
	
	
	
}
