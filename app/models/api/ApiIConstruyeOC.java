package models.api;


import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.fasterxml.jackson.databind.JsonNode;

import models.tables.EmisorTributario;
import models.utilities.Fechas;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;



public class ApiIConstruyeOC {
	
	public List<ApiNuboxDocDet> productos;
	public ApiNuboxDocRef documentoReferenciado;

	public ApiIConstruyeOC(List<ApiNuboxDocDet> productos, ApiNuboxDocRef documentoReferenciado) {
		super();
		this.productos = productos;
		this.documentoReferenciado = documentoReferenciado;
	}

	public ApiIConstruyeOC() {
		super();
	}
	
	
//**************************************************************
// CONSUME LOS SERVICIOS EN ICONSTRUYE:
//**************************************************************
	
	public static String obtieneToken(EmisorTributario emisor, WSClient ws) {
		String apiUser = emisor.apiUserIConstruyeOC;
		String apiKey = emisor.apiKeyIConstruyeOC;
		String apiUrl = emisor.apiUrlIConstruyeOC0;
		
		String original = apiUser+":"+apiKey; 
		byte[] bytes = original.getBytes(StandardCharsets.UTF_8); 
		String base64Encoded = Base64.getEncoder().encodeToString(bytes);
		String dataPost = "Basic "+base64Encoded;
		
		try {
			String rs = ws.url(apiUrl)
					.addHeader("Content-Type","application/x-www-form-urlencoded")
					.addHeader("Accept","application/json, text/plain, */*")
					.addHeader("Authorization",dataPost)
					.post("")
					.thenApply(
			          (WSResponse response) -> {
			        	  JsonNode jsonNode = response.asJson();
			        	  String token = jsonNode.findPath("access_token").textValue();
			        	  token = "Bearer "+ token;
			        	  return(token);
			            }).toCompletableFuture().get(10000,TimeUnit.MILLISECONDS);
			return rs;
		} catch (java.lang.InterruptedException | java.util.concurrent.ExecutionException | TimeoutException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<List<String>> obtieneCtrosGestion(EmisorTributario emisor, WSClient ws, String token, String nEmpresa) {
		String url1 = emisor.apiUrlIConstruyeOC1;
		try {
			List<List<String>> rs = ws.url(url1)
					.addHeader("accept","text/plain")
        			.addHeader("Authorization",token)
        			.addQueryParameter("activo", "1")
					.get()
					.thenApply(
			          (WSResponse response) -> {
			        	  JsonNode jsonNode2 = response.asJson();
			        	  List<List<String>> listIdOrgc = new ArrayList<List<String>>();
			        	  for(int i=0; i<jsonNode2.size(); i++) {
			        		  JsonNode jsonNode4 = jsonNode2.get(i);
			        		  
			        		  String idOrgc = jsonNode4.findPath("idOrgc").asText();
			        		  String codigo = jsonNode4.findPath("codigo").asText();
			        		  String nombre = jsonNode4.findPath("nombre").asText();
			        		  
			        		  if(nEmpresa.equals("MPM RENTAL")){
			        			  if(nombre.substring(0,2).equals("30")) {
				        			  if( !( nombre.substring(0,10).equals("3020100000") || nombre.substring(0,10).equals("3020200000") || nombre.substring(0,10).equals("3020300000") ) ){
				        				  List<String> aux = new ArrayList<String>();
						        		  aux.add(idOrgc);
						        		  aux.add(codigo);
						        		  aux.add(nombre);
						        		  listIdOrgc.add(aux);
				        			  }
				        		  }
			        		  } else {
			        			  List<String> aux = new ArrayList<String>();
				        		  aux.add(idOrgc);
				        		  aux.add(codigo);
				        		  aux.add(nombre);
				        		  listIdOrgc.add(aux);
			        		  }
			        		  
			        	  }
			        	  return(listIdOrgc);
			            }).toCompletableFuture().get(10000,TimeUnit.MILLISECONDS);
			return rs;
		} catch (java.lang.InterruptedException | java.util.concurrent.ExecutionException | TimeoutException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<List<String>> obtieneListaOC(EmisorTributario emisor, WSClient ws, String token, String desdeAAMMDD, String hastaAAMMDD, String idOrgc) {
		String url2 = emisor.apiUrlIConstruyeOC2;
		try {
			List<List<String>> rs = ws.url(url2)
					.addHeader("accept","text/plain")
        			.addHeader("Authorization",token)
        			.addQueryParameter("IdOrgcOC", idOrgc)
        			.addQueryParameter("FechaCreacionDesde", desdeAAMMDD)
        			.addQueryParameter("FechaCreacionHasta",hastaAAMMDD)
        			.addQueryParameter("IdEstadoOc","53")
        			.addQueryParameter("IdTipoOc","56")
					.get()
					.thenApply(
			          (WSResponse response) -> {
			        	  JsonNode jsonNode2 = response.asJson();
			        	  List<List<String>> listOC = new ArrayList<List<String>>();
			        	  for(int i=0; i<jsonNode2.size(); i++) {
			        		  JsonNode jsonNode4 = jsonNode2.get(i);
			        		  List<String> aux = new ArrayList<String>();
			        		  aux.add(jsonNode4.findPath("idDocumento").asText());
			        		  aux.add(jsonNode4.findPath("numeroOc").asText());
			        		  aux.add(jsonNode4.findPath("centroGestion").asText());
			        		  aux.add(jsonNode4.findPath("proveedor").asText());
			        		  aux.add(jsonNode4.findPath("solicitante").asText());
			        		  listOC.add(aux);
			        	  }
			        	  return(listOC);
			            }).toCompletableFuture().get(6000000,TimeUnit.MILLISECONDS);
			return rs;
		} catch (java.lang.InterruptedException | java.util.concurrent.ExecutionException | TimeoutException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static  List<List<String>> obtieneDetalleOC(EmisorTributario emisor, WSClient ws, String token, String idDoc) {
		String url3 = emisor.apiUrlIConstruyeOC3;
		try {
			 List<List<String>> rs = ws.url(url3)
					.addHeader("accept","text/plain")
        			.addHeader("Authorization",token)
        			.addQueryParameter("IdDoc", idDoc)
					.get()
					.thenApply(
			          (WSResponse response) -> {
			        	  
			        	  JsonNode jResponse = response.asJson();
			        	  
			        	  JsonNode jCabecera = jResponse.findPath("cabecera");
			        	  JsonNode jReceptor = jCabecera.findPath("receptor");
			        	  JsonNode jDocumento = jCabecera.findPath("documento");
			        	  
			        	  
			        	  String nombOc = jDocumento.findPath("nombOc").asText();
			        	  String numOc = jDocumento.findPath("numOc").asText();
			        	  String fechaCreacion = jDocumento.findPath("fechaCreacion").asText();
			        	  String fechaEnvio = jDocumento.findPath("fechaEnvio").asText();
			        	  
			        	  if(fechaCreacion.length()<2) {
			        		  fechaCreacion = Fechas.hoy().fechaStrAAMMDD;
			        	  }else {
			        		  fechaCreacion = fechaCreacion.substring(0,fechaCreacion.indexOf("T"));
			        	  }
			        	  
			        	  if(fechaEnvio.length()<2) {
			        		  fechaEnvio = Fechas.hoy().fechaStrAAMMDD;
			        	  }else {
			        		  fechaEnvio = fechaEnvio.substring(0,fechaEnvio.indexOf("T"));
			        	  }
			        	  
			        	  
			        	  
			        	 
			        	  String identificadorReceptor = jReceptor.findPath("identificadorReceptor").asText();
			        	  String razonSocialReceptor = jReceptor.findPath("razonSocialReceptor").asText();
			        	  
			        	  JsonNode jExtra = jReceptor.findPath("extra");
			        	  String notas = jExtra.findPath("notas").asText();   //ojo si este campo es nulo o vacio aplicar hoy
			        	  if(notas.length()<2) {
			        		  notas = "OC: " + numOc + "." + "\n";
			        	  }else {
			        		  notas = "OC: " + numOc + "." + "\n" + notas;
			        	  }
			        	  
			        	  
			        	  JsonNode jDetalle = jResponse.findPath("detalle");
			        	  JsonNode jLineas = jDetalle.findPath("lineas");
			        	  
			        	  List<List<String>> listDetalle = new ArrayList<List<String>>();
			        	  for(int i=0; i<jLineas.size(); i++) {
			        		  JsonNode jRecurso= jLineas.get(i).findPath("recurso");
			        		  JsonNode jMoneda= jLineas.get(i).findPath("moneda");
			        		  
			        		  Double cantidad = Double.parseDouble(jRecurso.findPath("cantidad").asText());
			        		  Double montoTotal = Double.parseDouble(jRecurso.findPath("montoTotal").asText());
			        		  Double precioUnitario = montoTotal/cantidad;
			        		  
			        		  List<String> aux = new ArrayList<String>();
			        		  
			        		  
			        		  
			        		  aux.add(idDoc);												// 0
			        		  aux.add(numOc);												// 1
			        		  aux.add(fechaEnvio);											// 2
			        		  aux.add(identificadorReceptor);								// 3
			        		  aux.add(razonSocialReceptor);									// 4
			        		  aux.add(notas);												// 5
			        		  
			        		  aux.add(jRecurso.findPath("codigoItem").asText());			// 6
			        		  aux.add(jRecurso.findPath("descripItem").asText());			// 7
			        		  aux.add(jRecurso.findPath("glosa").asText());					// 8
			        		  aux.add(jRecurso.findPath("descripUnidadMedida").asText());	// 9
			        		  aux.add(jRecurso.findPath("cantidad").asText());				// 10
			        		  aux.add(jMoneda.findPath("idMoneda").asText());				// 11
			        		  aux.add(jMoneda.findPath("moneda").asText());					// 12
			        		  aux.add(jRecurso.findPath("montoTotal").asText());			// 13
			        		  aux.add("id_equipo");											// 14
			        		  aux.add("id_monedaCompra");									// 15
			        		  aux.add(precioUnitario.toString());							// 16
			        		  aux.add("bodegaDestino");										// 17
			        		  
			        		  aux.add(nombOc);												// 18
			        		  aux.add(fechaCreacion);										// 19
			        		  
			        		  listDetalle.add(aux);
			        		  
			        	  }
			        	  
			        	  
			        	  
			        	  return(listDetalle);
			            }).toCompletableFuture().get(600000,TimeUnit.MILLISECONDS);
			return rs;
		} catch (java.lang.InterruptedException | java.util.concurrent.ExecutionException | TimeoutException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	
}
