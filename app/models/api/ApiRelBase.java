package models.api;


import java.sql.Connection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.fasterxml.jackson.databind.JsonNode;

import models.tables.EmisorTributario;
import models.tables.Guia;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;



public class ApiRelBase {
	
	public List<ApiNuboxDocDet> productos;
	public ApiNuboxDocRef documentoReferenciado;

	public ApiRelBase(List<ApiNuboxDocDet> productos, ApiNuboxDocRef documentoReferenciado) {
		super();
		this.productos = productos;
		this.documentoReferenciado = documentoReferenciado;
	}

	public ApiRelBase() {
		super();
	}
	
	
//**************************************************************
// CONSUME LOS SERVICIOS EN RELBASE:
//**************************************************************
	
	
	public static int consultaIdCliente(EmisorTributario emisor, WSClient ws, String filtro) {
		String tokenUsuario = emisor.apiUser;
		String tokenEmpresa = emisor.apiKey;
		String apiUrl = emisor.apiUrl;
		apiUrl += "clientes";
		if(filtro.length() > 1) {
			apiUrl += "?"+filtro;
		}
		try {
			int rs = ws.url(apiUrl)
					.addHeader("accept","application/json")
        			.addHeader("Authorization",tokenUsuario)
        			.addHeader("Company",tokenEmpresa)
					.get()
					.thenApply(
			          (WSResponse response) -> {
			        	  JsonNode jsonNode = response.asJson();
			        	  JsonNode idNode = jsonNode.path("data").path("customers").get(0).path("id");
			        	  int id = 0;
			        	  if (!idNode.isMissingNode()) {
			                  id = idNode.asInt();
			              }
			        	  return(id);
			            }).toCompletableFuture().get(10000,TimeUnit.MILLISECONDS);
			return(rs);
		} catch (java.lang.InterruptedException | java.util.concurrent.ExecutionException | TimeoutException e) {
			return 0;
		}
	}
	
	public static String consultaLinkDtePDF(EmisorTributario emisor, WSClient ws, String type_document, String query) {
		String tokenUsuario = emisor.apiUser;
		String tokenEmpresa = emisor.apiKey;
		String apiUrl = emisor.apiUrl;
		apiUrl += "dtes";
		try {
			String rs = ws.url(apiUrl)
					.addHeader("accept","application/json")
        			.addHeader("Authorization",tokenUsuario)
        			.addHeader("Company",tokenEmpresa)
        			.addQueryParameter("type_document", type_document)
        			.addQueryParameter("query", query)
					.get()
					.thenApply(
			          (WSResponse response) -> {
			        	  JsonNode jsonNode = response.asJson();
			        	  JsonNode linkNode = jsonNode.path("data").path("dtes").get(0).path("pdf_file").path("url");
			        	  String link = "0";
			        	  if (!linkNode.isMissingNode()) {
			        		  link = linkNode.asText();
			              }
			        	  return(link);
			            }).toCompletableFuture().get(10000,TimeUnit.MILLISECONDS);
			return(rs);
		} catch (java.lang.InterruptedException | java.util.concurrent.ExecutionException | TimeoutException e) {
			return "0";
		}
	}
	
	public static int consultaIdProducto(EmisorTributario emisor, WSClient ws, String query) {
		String tokenUsuario = emisor.apiUser;
		String tokenEmpresa = emisor.apiKey;
		String apiUrl = emisor.apiUrl;
		apiUrl += "productos";
		try {
			int rs = ws.url(apiUrl)
					.addHeader("accept","application/json")
        			.addHeader("Authorization",tokenUsuario)
        			.addHeader("Company",tokenEmpresa)
        			.addQueryParameter("query", query)
					.get()
					.thenApply(
			          (WSResponse response) -> {
			        	  JsonNode jsonNode = response.asJson();
			        	  JsonNode idNode = jsonNode.path("data").path("products").get(0).path("id");
			        	  int id = 0;
			        	  if (!idNode.isMissingNode()) {
			        		  id = idNode.asInt();
			              }
			        	  return(id);
			            }).toCompletableFuture().get(10000,TimeUnit.MILLISECONDS);
			return(rs);
		} catch (java.lang.InterruptedException | java.util.concurrent.ExecutionException | TimeoutException e) {
			e.printStackTrace();
			return 0;
		}
	}
	  
	public static int generaDTE(Connection con, String db, EmisorTributario emisor, String json, WSClient ws, Long id_guia) {
		String tokenUsuario = emisor.apiUser;
		String tokenEmpresa = emisor.apiKey;
		String apiUrl = emisor.apiUrl;
		apiUrl += "dtes";
		try {
			int rs = ws.url(apiUrl)
					.addHeader("accept","application/json")
        			.addHeader("Authorization",tokenUsuario)
        			.addHeader("Company",tokenEmpresa)
        			.addHeader("Content-Type","application/json")
					.post(json)
					.thenApply(
			          (WSResponse response) -> {
			        	  JsonNode jsonNode = response.asJson();
			        	  Guia.modificaPorCampo(con, db, "response", id_guia, jsonNode.toString());
			        	  JsonNode folio = jsonNode.path("data").path("folio");
			        	  int folio_dte = 0;
			        	  if ( ! folio.isMissingNode()) {
			        		  folio_dte = folio.asInt();
			              }
			        	  return(folio_dte);
			            }).toCompletableFuture().get(10000,TimeUnit.MILLISECONDS);
			return(rs);
		} catch (java.lang.InterruptedException | java.util.concurrent.ExecutionException | TimeoutException e) {
			return 0;
		}
	}
	
	public static boolean createProducto(EmisorTributario emisor, String json, WSClient ws) {
		String tokenUsuario = emisor.apiUser;
		String tokenEmpresa = emisor.apiKey;
		String apiUrl = emisor.apiUrl;
		apiUrl += "productos";
		try {
			boolean rs = ws.url(apiUrl)
					.addHeader("accept","application/json")
        			.addHeader("Authorization",tokenUsuario)
        			.addHeader("Company",tokenEmpresa)
        			.addHeader("Content-Type","application/json")
					.post(json)
					.thenApply(
			          (WSResponse response) -> {
			        	  return(true);
			            }).toCompletableFuture().get(10000,TimeUnit.MILLISECONDS);
			return(rs);
		} catch (java.lang.InterruptedException | java.util.concurrent.ExecutionException | TimeoutException e) {
			return false;
		}
	}
	
	//**************************************************************
	// FINAL DE CONSUMO
	//**************************************************************
	

	
	
	
	
	
}
