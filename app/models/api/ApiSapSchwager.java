package models.api;


import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.fasterxml.jackson.databind.JsonNode;

import controllers.HomeController;
import models.tables.*;
import models.utilities.Fechas;
import models.xml.XmlFacturaReferencias;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;



public class ApiSapSchwager {


	public ApiSapSchwager() {
		super();
	}

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
//**************************************************************
// CONSUME LOS SERVICIOS EN SAP CONCONCRETO:
//**************************************************************
	
	public static String obtieneToken(EmisorTributario emisor, WSClient ws) {
		try {
			String apiCompany = emisor.apiCompany;
			String apiUser = emisor.apiUser;
			String apiKey = emisor.apiKey;
			String apiUrl = emisor.apiUrl + "Login";
			String data = "{\"CompanyDB\": \""+apiCompany+"\",\"Password\": \""+apiKey+"\",\"UserName\": \""+apiUser+"\"}";
			String token = ws.url(apiUrl)
					.addHeader("Content-Type","application/json")
					.post(data)
					.thenApply(
			          (WSResponse response) -> {
			        	  JsonNode jsonNode = response.asJson();
			        	  JsonNode jsonToken = jsonNode.path("SessionId");
			        	  String aux = "";
			        	  if (!jsonToken.isMissingNode()) {
			        		  aux = jsonToken.asText();
			              }
			        	  return(aux);
			            }).toCompletableFuture().get(10000,TimeUnit.MILLISECONDS);
			return (token);
		} catch (java.lang.InterruptedException | java.util.concurrent.ExecutionException | TimeoutException e) {
			String className = ApiSapSchwager.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, emisor.getNombreFantasia(), e);
			return "ERROR";
		}
	}
	
	public static String generaDteGuia(Connection con, String db, EmisorTributario emisor, String json, WSClient ws, Long id_guia) {
		try {
			String apiUrl = emisor.apiUrl + "DeliveryNotes";
			String token = ApiSapSchwager.obtieneToken(emisor, ws);
			String rs = ws.url(apiUrl)
					.addHeader("Content-Type","application/json")
					.addHeader("Cookie", "B1SESSION="+token)
					.post(json)
					.thenApply(
			          (WSResponse response) -> {
			        	  JsonNode jsonNode = response.asJson();
			        	  Guia.modificaPorCampo(con, db, "response", id_guia, jsonNode.toString());
			        	  JsonNode jsonError = jsonNode.path("error").path("message").path("value");
			        	  String aux = "ERROR DE SISTEMA 1";
			        	  if ( ! jsonError.isMissingNode()) {
			        		  aux = "ERROR: "+jsonError.asText();
			              }else {
			            	  JsonNode jsonFolioNumber = jsonNode.path("FolioNumber");
			            	  if ( ! jsonFolioNumber.isMissingNode()) {
			            		  aux = ""+jsonFolioNumber.asInt();
			            	  }
			              }
			        	  
			        	  return(aux);
			            }).toCompletableFuture().get(10000,TimeUnit.MILLISECONDS);
			return (rs);
		} catch (java.lang.InterruptedException | java.util.concurrent.ExecutionException | TimeoutException e) {
			String className = ApiSapSchwager.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
			return "ERROR DE SISTEMA 2";
		}
	}
	
	public static String generaDteFactura(Connection con, String db, EmisorTributario emisor, String json, WSClient ws, Long id_proforma) {
		try {
			String apiUrl = emisor.apiUrl + "Invoices";
			String token = ApiSapSchwager.obtieneToken(emisor, ws);
			String rs = ws.url(apiUrl)
					.addHeader("Content-Type","application/json")
					.addHeader("Cookie", "B1SESSION="+token)
					.post(json)
					.thenApply(
			          (WSResponse response) -> {
			        	  JsonNode jsonNode = response.asJson();
			        	  Proforma.updateResponse(con, db, id_proforma, jsonNode.toString());
			        	  JsonNode jsonError = jsonNode.path("error").path("message").path("value");
			        	  String aux = "ERROR DE SISTEMA 1";
			        	  if ( ! jsonError.isMissingNode()) {
			        		  aux = "ERROR: "+jsonError.asText();
			              }else {
			            	  JsonNode jsonFolioNumber = jsonNode.path("FolioNumber");
			            	  if ( ! jsonFolioNumber.isMissingNode()) {
			            		  aux = ""+jsonFolioNumber.asInt();
			            	  }
			              }

			        	  return(aux);
			            }).toCompletableFuture().get(10000,TimeUnit.MILLISECONDS);
			return (rs);
		} catch (java.lang.InterruptedException | java.util.concurrent.ExecutionException | TimeoutException e) {
			String className = ApiSapSchwager.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
			return "ERROR DE SISTEMA 2";
		}
	}
	
	//**************************************************************
	// FINAL DE CONSUME LOS SERVICIOS SAP CONCONCRETO
	//**************************************************************

	public static String generaJsonFactARR( Cliente cliente, Long id_proforma, XmlFacturaReferencias referencias, List<List<String>> detalleAjuste, 
				List<List<String>> inicioPer, List<List<String>> guiasPer, Map<String, List<List<String>>> mapReportPorGuia10) {
		try{
			String rutCliente = cliente.getRut().replaceAll("[,\\.\\s]", "").toUpperCase();
			if (rutCliente.length() > 1) {
				rutCliente = rutCliente.replaceAll("-", "");
			}
			Fechas hoy = Fechas.hoy();
			String refer = "";
			if(referencias.tpoDocRef != null) {
				if(referencias.tpoDocRef.size() > 0) {
					refer +=  "\"U_EXX_FE_INREF\": \""+referencias.tpoDocRef.get(0)+"\","
							+ "\"U_EXX_FE_FOREF\": \""+referencias.folioRef.get(0)+"\","
							+ "\"U_EXX_FE_FEREF\": \""+referencias.fchRef.get(0)+"\",";
					if(referencias.tpoDocRef.size() > 1) {
						refer +=  "\"U_EXX_FE_INREF2\": \""+referencias.tpoDocRef.get(1)+"\","
								+ "\"U_EXX_FE_FOREF2\": \""+referencias.folioRef.get(1)+"\","
								+ "\"U_EXX_FE_FEREF2\": \""+referencias.fchRef.get(1)+"\",";
					}
					if(referencias.tpoDocRef.size() > 2) {
						refer +=  "\"U_EXX_FE_INREF3\": \""+referencias.tpoDocRef.get(2)+"\","
								+ "\"U_EXX_FE_FOREF3\": \""+referencias.folioRef.get(2)+"\","
								+ "\"U_EXX_FE_FEREF3\": \""+referencias.fchRef.get(2)+"\",";
					}
				}
			}
			DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
			DecimalFormat myformatapi = new DecimalFormat("###0.0",symbols);
			//codigo,precioTotal,dias
			Map<String,List<String>> mapDetalle = new HashMap<String,List<String>>();
			for(List<String> i: inicioPer) {
				List<String> aux = mapDetalle.get(i.get(9).trim());
				if(aux == null) {
					aux = new ArrayList<String>();
					aux.add(i.get(9).trim());
					aux.add(i.get(18).replaceAll(",", "").trim());
					aux.add(i.get(16).replaceAll(",", "").trim());
					mapDetalle.put(i.get(9).trim(), aux);
				} else {
					Double p1 = Double.parseDouble(aux.get(1));
					Double p2 = Double.parseDouble(i.get(18).replaceAll(",", "").trim());
					Double d1 = Double.parseDouble(aux.get(2));
					Double d2 = Double.parseDouble(i.get(16).replaceAll(",", "").trim());
					if(p2 < 0) {
						d2 = d2 * (long)-1;
					}
					aux.set(1, myformatapi.format(p1 + p2));
					aux.set(2, myformatapi.format(d1 + d2));
					mapDetalle.put(i.get(9).trim(), aux);
				}
			}
			for(List<String> g: guiasPer) {
				if( ! g.get(4).equals("1")) { // indica si es o no de arriendo si es 1 es venta
					List<List<String>> map = mapReportPorGuia10.get(g.get(8));
					if(map !=null ) {
						for(List<String> d: map) {
							if( ! ( d.get(13).equals("0")|| d.get(13).equals("-0") )) {
								List<String> aux = mapDetalle.get(d.get(10).trim());
								if(aux == null) {
									aux = new ArrayList<String>();
									aux.add(d.get(10).trim());
									aux.add(d.get(19).replaceAll(",", "").trim());
									aux.add(d.get(17).replaceAll(",", "").trim());
									mapDetalle.put(d.get(10).trim(), aux);
								} else {
									Double p1 = Double.parseDouble(aux.get(1));
									Double p2 = Double.parseDouble(d.get(19).replaceAll(",", "").trim());
									Double d1 = Double.parseDouble(aux.get(2));
									Double d2 = Double.parseDouble(d.get(17).replaceAll(",", "").trim());
									if(p2 < 0) {
										d2 = d2 * (long)-1;
									}
									aux.set(1, myformatapi.format(p1 + p2));
									aux.set(2, myformatapi.format(d1 + d2));
									mapDetalle.put(d.get(10).trim(), aux);
								}
							}
						}
					}
				}
			}
			String det = "";
			Double totalNeto = (double)0;
			for (Map.Entry<String, List<String>> entry : mapDetalle.entrySet()) {
				List<String> v = entry.getValue();
				det += "{"
					 + "\"ItemCode\": \""+v.get(0)+"\","
					 + "\"Quantity\": 1,"
					 + "\"TaxCode\": \"IVA\","
					 + "\"UnitPrice\": "+v.get(1)+","
					 + "\"FreeText\": \""+v.get(2)+" días\""
					 + "},";
				totalNeto += Double.parseDouble(v.get(1));
			}
			det = det.substring(0,det.length()-1);
			Double dctos = (double)0;
			for(List<String> x: detalleAjuste) {
				dctos += Double.parseDouble(x.get(1).trim().replaceAll(",", "").trim());
			}
			dctos = dctos * (double)-1;
			if(dctos > 0) {
				dctos = dctos * 100 / totalNeto;
			}else {
				dctos = (double)0;
			}
			String json = "{"
					+ "\"CardCode\": \"CL"+rutCliente+"\","
					+ "\"DocDate\": \""+hoy.getFechaStrAAMMDD()+"\","
					+ "\"DocDueDate\": \""+hoy.getFechaStrAAMMDD()+"\","
					+ "\"Comments\": \"Nro Proforma MADA: "+id_proforma+"\",";
			json += refer
					+ "\"Indicator\": \"33\","
					+ "\"DiscountPercent\": \""+dctos.toString()+"\","
					+ "\"DocumentLines\": [";
			json += det + "]"
						+ "}";
			return(json);
		}catch(Exception e){
			String className = ApiSapSchwager.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, "", e);
			return null;
		}
	}
	
	public static String generaJsonFactVTA( Cliente cliente, Long id_proforma, XmlFacturaReferencias referencias, List<List<String>> detalleAjuste, 
				List<List<String>> guiasPer, Map<String, List<List<String>>> mapReportPorGuia10) {
		try{
			String rutCliente = cliente.getRut().replaceAll("[,\\.\\s]", "").toUpperCase();
			if (rutCliente.length() > 1) {
				rutCliente = rutCliente.replaceAll("-", "");
			}
			Fechas hoy = Fechas.hoy();
			String refer = "";
			if(referencias.tpoDocRef != null) {
				if(referencias.tpoDocRef.size() > 0) {
					refer +=  "\"U_EXX_FE_INREF\": \""+referencias.tpoDocRef.get(0)+"\","
							+ "\"U_EXX_FE_FOREF\": \""+referencias.folioRef.get(0)+"\","
							+ "\"U_EXX_FE_FEREF\": \""+referencias.fchRef.get(0)+"\",";

					if(referencias.tpoDocRef.size() > 1) {
						refer +=  "\"U_EXX_FE_INREF2\": \""+referencias.tpoDocRef.get(1)+"\","
								+ "\"U_EXX_FE_FOREF2\": \""+referencias.folioRef.get(1)+"\","
								+ "\"U_EXX_FE_FEREF2\": \""+referencias.fchRef.get(1)+"\",";
					}

					if(referencias.tpoDocRef.size() > 2) {
						refer +=  "\"U_EXX_FE_INREF3\": \""+referencias.tpoDocRef.get(2)+"\","
								+ "\"U_EXX_FE_FOREF3\": \""+referencias.folioRef.get(2)+"\","
								+ "\"U_EXX_FE_FEREF3\": \""+referencias.fchRef.get(2)+"\",";
					}
				}
			}
			DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
			DecimalFormat myformatapi = new DecimalFormat("###0.0",symbols);
			//codigo,precioTotal
			Map<String,List<String>> mapDetalle = new HashMap<String,List<String>>();
			for(List<String> g: guiasPer) {
				if(g.get(4).equals("1")) { // indica si es venta
					List<List<String>> map = mapReportPorGuia10.get(g.get(8));
					if(map !=null ) {
						for(List<String> d: map) {
							if( ! ( d.get(13).equals("0")|| d.get(13).equals("-0") )) {
								List<String> aux = mapDetalle.get(d.get(10).trim());
								if(aux == null) {
									aux = new ArrayList<String>();
									aux.add(d.get(10).trim());
									aux.add(d.get(20).replaceAll(",", "").trim());
									mapDetalle.put(d.get(10).trim(), aux);
								} else {
									Double p1 = Double.parseDouble(aux.get(1));
									Double p2 = Double.parseDouble(d.get(20).replaceAll(",", "").trim());
									aux.set(1, myformatapi.format(p1 + p2));
									mapDetalle.put(d.get(10).trim(), aux);
								}
							}
						}
					}
				}
			}
			String det = "";
			Double totalNeto = (double)0;
			for (Map.Entry<String, List<String>> entry : mapDetalle.entrySet()) {
				List<String> v = entry.getValue();
				det += "{"
					 + "\"ItemCode\": \""+v.get(0)+"\","
					 + "\"Quantity\": 1,"
					 + "\"TaxCode\": \"IVA\","
					 + "\"UnitPrice\": "+v.get(1)+","
					 + "\"FreeText\": \"\""
					 + "},";
				totalNeto += Double.parseDouble(v.get(1));
			}
			det = det.substring(0,det.length()-1);
			Double dctos = (double)0;
			for(List<String> x: detalleAjuste) {
				dctos += Double.parseDouble(x.get(2).trim().replaceAll(",", ""));
			}
			dctos = dctos * (double)-1;
			if(dctos > 0) {
				dctos = dctos * 100 / totalNeto;
			}else {
				dctos = (double)0;
			}
			String json = "{"
					+ "\"CardCode\": \"CL"+rutCliente+"\","
					+ "\"DocDate\": \""+hoy.getFechaStrAAMMDD()+"\","
					+ "\"DocDueDate\": \""+hoy.getFechaStrAAMMDD()+"\","
					+ "\"Comments\": \"Nro Proforma MADA: "+id_proforma+"\",";
			json += refer
					+ "\"Indicator\": \"33\","
					+ "\"DiscountPercent\": \""+dctos.toString()+"\","
					+ "\"DocumentLines\": [";
			json += det + "]"
						+ "}";
			return(json);
		}catch(Exception e){
			String className = ApiSapSchwager.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, "", e);
			return null;
		}
	}
	
	public static String generaJsonGUIA( String rutCliente, Fechas hoy, Guia guia, Transportista transportista, 
			List<List<String>> detalleGuia, Map<Long,Double> mapTasas) {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
		DecimalFormat myformatapi = new DecimalFormat("###0.0",symbols);
   		String json = "{"
   				+ "\"CardCode\": \"CL"+rutCliente+"\","
   				+ "\"DocDate\": \""+hoy.getFechaStrAAMMDD()+"\","
   				+ "\"DocDueDate\": \""+hoy.getFechaStrAAMMDD()+"\","
   				+ "\"Comments\": \"Nro MOV MADA:"+guia.getNumero()+"\","
   				+ "\"Indicator\": \"52\","
   				+ "\"U_EXX_FE_IndTraslado\": \"6\","
   				+ "\"U_EXX_FE_CHOFER\" : \""+transportista.getConductor()+"\","
   				+ "\"U_EXX_FE_RUTCHOFER\" : \""+transportista.getRutConductor()+"\","
   				+ "\"U_EXX_FE_PATENTE\" : \""+transportista.getPatente()+"\","
   				+ "\"DocumentLines\": [";
   		for(List<String> x: detalleGuia) {
   			String auxPrecio = x.get(9).trim();  // precio unitario de venta en moneda de origen
   			Long id_moneda =  Long.parseLong(x.get(30).trim());
			Double tasa = mapTasas.get(id_moneda);
			Double precioUnitario = Double.parseDouble(auxPrecio.replaceAll(",", "").trim()) * tasa;
			if(precioUnitario==null || precioUnitario<=0) {
				precioUnitario = (double)1;
			}
			String auxCantidad = x.get(8).trim();
			Double cantidad = Double.parseDouble(auxCantidad.replaceAll(",", "").trim());
			if(cantidad==null || cantidad<=0) {
				cantidad = (double)1;
			}
   			json 	+="{"
       				+ "\"ItemCode\": \""+x.get(5)+"\","
       				+ "\"Quantity\": "+myformatapi.format(cantidad)+","
       				+ "\"TaxCode\": \"IVA\","
       				+ "\"UnitPrice\": "+myformatapi.format(Math.round(precioUnitario))
       				+ "},";
   		}
   		json = json.substring(0, json.length()-1);
   		json 	+="]"
   				+ "}";
		return(json);
	}
	
	
	
	
}
