package models.api;


import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.fasterxml.jackson.databind.JsonNode;

import models.forms.FormFactura;
import models.tables.BodegaEmpresa;
import models.tables.Cliente;
import models.tables.EmisorTributario;
import models.tables.Guia;
import models.tables.Proforma;
import models.tables.TasasCambio;
import models.utilities.Fechas;
import models.xml.XmlFacturaReferencias;
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
	  
	public static int generaDTE(Connection con, String db, EmisorTributario emisor, String json, WSClient ws, Long id_guia, Long id_proforma) {
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
			        	  if(id_guia > 0) {
			        		  Guia.modificaPorCampo(con, db, "response", id_guia, jsonNode.toString());
			        	  }else {
			        		  Proforma.updateResponse(con, db, id_proforma, jsonNode.toString());
			        	  }
			        	  
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
	

	public static String generaJsonGuia(Connection con, String db, Map<String,String> mapeoDiccionario, WSClient ws, EmisorTributario emisor,
			Guia guia, String comentarios, Long id_cotizaSolucion, String description) {
		
		Long id_bodegaOrigen = guia.getId_bodegaOrigen();
		Long id_bodegaDestino = guia.getId_bodegaDestino();
		BodegaEmpresa bodegaOrigen = BodegaEmpresa.findXIdBodega(con, db, id_bodegaOrigen);
		BodegaEmpresa bodegaDestino = BodegaEmpresa.findXIdBodega(con, db,id_bodegaDestino);
		Cliente cliente = Cliente.find(con, db, bodegaDestino.getId_cliente());
		if((long) cliente.getId() == (long) 0) {
   			return ("ERROR: "+mapeoDiccionario.get("Bodega")+" \""+bodegaDestino.getNombre()+"\" carece de cliente/propietario asociado.");
   		}
   		String rutCliente = cliente.getRut().replaceAll("[,\\.\\s]", "").toUpperCase();
   		if (rutCliente.length() > 1) {
   			rutCliente = rutCliente.substring(0, rutCliente.length() - 1) + "-" + rutCliente.charAt(rutCliente.length() - 1);
   			rutCliente = "query="+rutCliente;
        }
   		String address = cliente.getDireccion();
   		String str_commune = cliente.getComuna();
   		String str_city = cliente.getCiudad();
   		int idRelBase = ApiRelBase.consultaIdCliente(emisor, ws, rutCliente);
   		if(idRelBase == 0) {
   			return ("ERROR: El RUT "+cliente.rut+" del cliente "+cliente.nickName+" no existe en RELBASE.");
   		}
   		Fechas hoy = Fechas.hoy();
   		
   		List<List<String>> detalleGuia = new ArrayList<List<String>>();
   		Double precioDbl = (double)0;
		if ((long) bodegaOrigen.esInterna == (long) 1 && (long) bodegaDestino.esInterna == (long) 2) {
			detalleGuia = Guia.findDetalleGuiaOrigenDestinoYPrecios(con, db, guia.getId(), guia.getId_bodegaDestino(), mapeoDiccionario.get("pais"), guia.getId_bodegaOrigen());
		} else if ((long) bodegaOrigen.esInterna == (long) 2 && (long) bodegaDestino.esInterna == (long) 1){
			detalleGuia = Guia.findDetalleGuiaOrigenDestinoYPrecios(con, db, guia.getId(), guia.getId_bodegaOrigen(), mapeoDiccionario.get("pais"), guia.getId_bodegaOrigen());
		}
		Map<Long,Double> mapTasas = TasasCambio.mapTasasPorFecha(con, db, hoy.getFechaStrAAMMDD(), mapeoDiccionario.get("pais"));
		for(List<String> x: detalleGuia) {
			String auxPrecio = x.get(9).trim();  // precio unitario de venta en moneda de origen
			Double precioUnitario = (double)0;
			String auxNum = auxPrecio;
   		    if(auxNum==null || auxNum.trim().length()<=0) {
   		    	auxNum = "0";
   		    }
   		    precioUnitario = Double.parseDouble(auxNum.replaceAll(",", ""));
			String auxCantidad = x.get(8).trim();
			if(auxCantidad.equals("") || auxCantidad.length()<=0) {
				auxCantidad = "0";
			}
			Double cantidad = (double)0;
			auxNum = auxCantidad.trim();
			if(auxNum==null || auxNum.trim().length()<=0) {
   		    	auxNum = "0";
   		    }
			cantidad = Double.parseDouble(auxNum.replaceAll(",", ""));
			Long id_moneda =  Long.parseLong(x.get(30).trim());
			Double tasa = mapTasas.get(id_moneda);
			precioDbl += precioUnitario * cantidad * tasa;
		}
		if(precioDbl < 1) {
			precioDbl = (double) 100;
		}
		DecimalFormat myformatapi = new DecimalFormat("###0");
		String precio = myformatapi.format(Math.round(precioDbl));
		String codProducto = "MADA-"+id_cotizaSolucion;
		int product_id = ApiRelBase.consultaIdProducto(emisor, ws, codProducto);
   		String json = "{"
   				+ "  \"type_document\": 52,"
   				+ "  \"start_date\": \""+hoy.getFechaStrAAMMDD()+"\","
   				+ "  \"end_date\": \""+hoy.getFechaStrAAMMDD()+"\","
   				+ "  \"customer_id\": "+idRelBase+","
   				+ "  \"address\": \""+address+"\","
   				+ "  \"is_str_city_and_comune\": true,"
   				+ "  \"str_commune\": \""+str_commune+"\","
   				+ "  \"str_city\": \""+str_city+"\","
   				+ "  \"ware_house_id\": 1953,"
   				+ "  \"comment\": \""+comentarios+"\","
   				+ "  \"type_transfer\": 6,"
   				+ "  \"products\": ["
   				+ "    {"
   				+ "      \"product_id\": "+product_id+","
   				+ "      \"price\": "+precio+","
   				+ "      \"quantity\": 1,"
   				+ "      \"tax_affected\": true,"
   				+ "      \"description\": \""+description+"\""
   				+ "    }"
   				+ "  ]"
   				+ "}";
   		return (json);
	}
	
	public static String generaJsonFactARRVTA(Connection con, String db, WSClient ws, Map<String,String> mapeoDiccionario, 
			XmlFacturaReferencias referencias, Cliente cliente, Proforma proforma, FormFactura form) {
		
		EmisorTributario emisor = EmisorTributario.find(con, db);
		
		BodegaEmpresa bodegaEmpresa = BodegaEmpresa.findXIdBodega(con, db, proforma.id_bodegaEmpresa);
		
		if((long) cliente.getId() == (long) 0) {
   			return ("ERROR: "+mapeoDiccionario.get("Bodega")+" \""+bodegaEmpresa.getNombre()+"\" carece de cliente/propietario asociado.");
   		}
   		String rutCliente = cliente.getRut().replaceAll("[,\\.\\s]", "").toUpperCase();
   		if (rutCliente.length() > 1) {
   			rutCliente = rutCliente.substring(0, rutCliente.length() - 1) + "-" + rutCliente.charAt(rutCliente.length() - 1);
   			rutCliente = "query="+rutCliente;
        }
   		String address = cliente.getDireccion();
   		String str_commune = cliente.getComuna();
   		String str_city = cliente.getCiudad();
   		int idRelBase = ApiRelBase.consultaIdCliente(emisor, ws, rutCliente);
   		if(idRelBase == 0) {
   			return ("ERROR: El RUT "+cliente.rut+" del cliente "+cliente.nickName+" no existe en RELBASE.");
   		}
   		
   		String comentarios = "Nro PROF MADA: "+ proforma.getId() + "\r\n" + form.sol_observaciones + "\r\n";
   		comentarios = comentarios.replace("\r", "\\r").replace("\n", "\\n");
   		String description = form.sol_description.replace("\r", "\\r").replace("\n", "\\n");
   		Long id_cotizaSolucion = form.id_cotizaSolucion;
   		
		Long netoSinAjustes = Math.round(proforma.getNetoSinAjustes());
		Long netoSoloAjustes = Math.round(proforma.getNetoSoloAjustes()*-1);
		if(netoSoloAjustes < 0) {
			netoSoloAjustes = (long)0;
		}
		
		DecimalFormat myformatapi = new DecimalFormat("###0");
		String precio = myformatapi.format(Math.round(netoSinAjustes));
		Double dctoGlobal = ((double) netoSoloAjustes / (double) netoSinAjustes) * 100;
		String codProducto = "MADA-"+id_cotizaSolucion;
		int product_id = ApiRelBase.consultaIdProducto(emisor, ws, codProducto);
		
		String jsonReferencias = "";
		int cantRef = 0;
		try {
			cantRef = referencias.tpoDocRef.size();
		}catch(Exception e) {}
		Fechas hoy = Fechas.hoy();
		for (int i = 0; i < cantRef; i++) {
			String reference_id = referencias.tpoDocRef.get(i);;
			String fechRef = referencias.fchRef.get(i);
			if(fechRef.trim().equals("")) {
				fechRef = hoy.getFechaStrAAMMDD();
			}
			String folioRef = referencias.folioRef.get(i);
			String razonRef = referencias.razonRef.get(i);
			if(folioRef.length()>18) {
				folioRef = folioRef.substring(0,17);
			}
			if(folioRef.trim().equals("")) {
				folioRef = "88888888";
			}
			if(razonRef.length()>90) {
				razonRef = razonRef.substring(0,89);
			}
			
			jsonReferencias +=  
						  "    {"
					    + "        \"reference_id\": "+reference_id+","
					    + "        \"folio_ref\": \""+folioRef+"\","
					    + "        \"date_ref\": \""+fechRef+"\","
					    + "        \"razon_ref\": \""+razonRef+"\""
					    + "    },";
		}
		if(jsonReferencias.length() > 1) {
			jsonReferencias = jsonReferencias.substring(0,jsonReferencias.length()-1);
		}
		String descuento = "";
		if(dctoGlobal > 0) {
			descuento =	  "  \"global_discount\": "+dctoGlobal+","
			   			+ "  \"global_discount_type\": \"%\",";
		}
		
   		String json = "{"
   				+ "  \"type_document\": 33,"
   				+ "  \"start_date\": \""+proforma.fecha+"\","
   				+ "  \"end_date\": \""+proforma.fecha+"\","
   				+ "  \"customer_id\": "+idRelBase+","
   				+ "  \"address\": \""+address+"\","
   				+ "  \"is_str_city_and_comune\": true,"
   				+ "  \"str_commune\": \""+str_commune+"\","
   				+ "  \"type_payment_id\": 8732,"
   				+ "  \"str_city\": \""+str_city+"\","
   				+ "  \"ware_house_id\": 1953,"
   				+ descuento
   				+ "  \"comment\": \""+comentarios+"\","
   				+ "  \"type_transfer\": 6,"
   				+ "  \"products\": ["
   				+ "    {"
   				+ "      \"product_id\": "+product_id+","
   				+ "      \"price\": "+precio+","
   				+ "      \"quantity\": 1,"
   				+ "      \"tax_affected\": true,"
   				+ "      \"description\": \""+description+"\""
   				+ "    }";
   				if(jsonReferencias.length() > 1) {
   					json += "  ],"
   							+ "  \"references\": ["
   							+ jsonReferencias+"  ]}";
   				}else {
   					json += "  ]}";
   				}
   		return (json);
	}
	
	
	
}
