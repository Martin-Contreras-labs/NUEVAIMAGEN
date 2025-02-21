package models.api;


import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.fasterxml.jackson.databind.JsonNode;

import models.tables.BodegaEmpresa;
import models.tables.Cliente;
import models.tables.EmisorTributario;
import models.tables.Grupo;
import models.tables.Guia;
import models.tables.Proforma;
import models.tables.Proyecto;
import models.tables.TasasCambio;
import models.tables.Transportista;
import models.utilities.Fechas;
import play.libs.Json;
import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;
import play.libs.ws.WSResponse;



public class ApiRelBaseDocDoc {
	
	public List<ApiNuboxDocDet> productos;
	public ApiNuboxDocRef documentoReferenciado;

	public ApiRelBaseDocDoc(List<ApiNuboxDocDet> productos, ApiNuboxDocRef documentoReferenciado) {
		super();
		this.productos = productos;
		this.documentoReferenciado = documentoReferenciado;
	}

	public ApiRelBaseDocDoc() {
		super();
	}
	
	
//**************************************************************
// CONSUME LOS SERVICIOS EN RELBASE:
//**************************************************************
	
	
	public static int consultaIdCliente(Connection con, String db, WSClient ws, String filtro) {
		
		EmisorTributario emisor = EmisorTributario.find(con, db);
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
	
	public static String consultaLinkDtePDF(Connection con, String db, WSClient ws, String type_document, String query) {
		
		EmisorTributario emisor = EmisorTributario.find(con, db);
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
	
	public static int generaDTE(Connection con, String db, String json, WSClient ws, Long id_proforma, Long id_guia) {
		
		EmisorTributario emisor = EmisorTributario.find(con, db);
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
			        	  JsonNode folio = jsonNode.path("data").get(0).path("folio");
			        	  int nroFiscal = 0;
			        	  if (!folio.isMissingNode()) {
			        		  nroFiscal = folio.asInt();
			              }
			        	  return(nroFiscal);
			            }).toCompletableFuture().get(10000,TimeUnit.MILLISECONDS);
			return(rs);
		} catch (java.lang.InterruptedException | java.util.concurrent.ExecutionException | TimeoutException e) {
			return 0;
		}
	}
	
	//**************************************************************
	// FINAL DE CONSUMO
	//**************************************************************
	

	static DecimalFormat myformatapi = new DecimalFormat("#.00");
	
	public static ApiRelBaseDocDoc generaGuia (Connection con, String db, Guia guia, Transportista transportista, Map<String,String> mapDiccionario) {
		
			EmisorTributario emisorTributario = EmisorTributario.find(con, db);
			Long id_bodegaDestino = guia.getId_bodegaDestino();
			BodegaEmpresa bodegaDestino = BodegaEmpresa.findXIdBodega(con, db,id_bodegaDestino);
			Cliente cliente = Cliente.find(con, db, bodegaDestino.getId_cliente());
			
			List<List<String>> detalleGuia = new ArrayList<List<String>>();
			String direccionDestino="", comunaDestino="", ciudadDestino="";
			
			if ((long) bodegaDestino.esInterna == (long) 1) {
				Cliente aux = Cliente.find(con, db, bodegaDestino.id_cliente);
				direccionDestino = aux.direccion;
				comunaDestino = aux.comuna;
				ciudadDestino = aux.ciudad;
				detalleGuia = Guia.findDetalleGuiaOrigenDestinoYPrecios(con, db, guia.getId(), guia.getId_bodegaOrigen(), mapDiccionario.get("pais"), guia.getId_bodegaOrigen());
			}else {
				Proyecto aux = Proyecto.find(con, db, bodegaDestino.id_proyecto);
				direccionDestino = aux.direccion;
				comunaDestino = aux.comuna;
				ciudadDestino = aux.ciudad;
				detalleGuia = Guia.findDetalleGuiaOrigenDestinoYPrecios(con, db, guia.getId(), guia.getId_bodegaDestino(), mapDiccionario.get("pais"), guia.getId_bodegaOrigen());
			}
			
			Long totalNeto=(long)0;
			
			for(int i=0;i<detalleGuia.size();i++) {
				String auxPrecio = detalleGuia.get(i).get(9); 				// precio de venta en moneda de origen
				if(auxPrecio==null || auxPrecio.trim().length()<=0)	{
					auxPrecio = "0";
				}
				Double precioUnitario = Double.parseDouble(auxPrecio.replaceAll(",", ""));
				String auxCantidad = detalleGuia.get(i).get(8);				// cantidad
				if(auxCantidad==null||auxCantidad.trim().length()<=0) {
					auxCantidad = "0";
				}
				Double cantidad = Double.parseDouble(auxCantidad.replaceAll(",", ""));
				Double auxPTotal = precioUnitario * cantidad;
				totalNeto = Math.round(totalNeto + auxPTotal);
			}
		
			
		//******************************
		// LLENA EL JSON PARA RELBASE
		//******************************
			ApiRelBaseDocDoc api = new ApiRelBaseDocDoc();
			List<ApiNuboxDocDet> detalle = new ArrayList<ApiNuboxDocDet>();
			Fechas auxFecha = Fechas.obtenerFechaDesdeStrAAMMDD(Fechas.AAMMDD(guia.fecha));
			
			Map<Long,Double> tasaCambio = TasasCambio.mapTasasPorFecha(con, db, auxFecha.getFechaStrAAMMDD(), mapDiccionario.get("pais"));
			
			for(int i=0;i<detalleGuia.size();i++) {
				//obtiene datos
					String auxCantidad = detalleGuia.get(i).get(8);				// cantidad
					if(auxCantidad==null||auxCantidad.trim().length()<=0) {
						auxCantidad = "0";
					}
					Double auxCant = Double.parseDouble(auxCantidad.replaceAll(",", ""));
					String cantidad = myformatapi.format(auxCant);
					String auxPrecio = detalleGuia.get(i).get(9); 				// precio unitario de venta en moneda de origen
					if(auxPrecio==null || auxPrecio.trim().length()<=0)	{
						auxPrecio = "0";
					}
					Double auxPU = Double.parseDouble(auxPrecio.replaceAll(",", ""));
					// lleva PU a moneda principal
					Long id_moneda = Long.parseLong(detalleGuia.get(i).get(30));		// id moneda del PU
					Double tasa = tasaCambio.get(id_moneda);
					if(tasa==null) tasa = (double)1;
					auxPU = auxPU * tasa;
					String precioUnita = myformatapi.format(auxPU);
				//fin obtiene datos
					
				// llena json
					ApiNuboxDocDet det = new ApiNuboxDocDet();
					
					cliente.rut = cliente.rut.trim();
					cliente.rut = cliente.rut.replace(".", "").replace(".", "").replace(".", "").replace(".", "").replace(".", "");
					cliente.rut = cliente.rut.replace(",", "").replace(",", "").replace(",", "").replace(",", "").replace(",", "");
					cliente.rut = cliente.rut.replace(" ", "").replace(" ", "").replace(" ", "").replace(" ", "").replace(" ", "");
					det.rutContraparte = cliente.rut;
					
					det.razonSocialContraparte = cliente.nombre;
					String giroCliente = cliente.giro;
					if(giroCliente.length()>40) {
						giroCliente = cliente.giro.substring(0,39);
					}
					det.giroContraparte = giroCliente; 				//"CONSTRUCCION"; // cliente.giro;
					det.tipo = "52";									// tipo documento 52 = guia
					det.folio = detalleGuia.get(i).get(1); 				//idguia
					det.secuencia = detalleGuia.get(i).get(2); 			// idequipo
					det.fecha = Fechas.AAMMDD(guia.fecha);
					det.codigoItem = detalleGuia.get(i).get(5); 		//codigo
					det.producto = detalleGuia.get(i).get(6); 			//producto
					det.cantidad = cantidad;
					det.precio = "0";
					det.valor = precioUnita;
					det.comunaContraparte = cliente.comuna;
					det.direccionContraparte = cliente.direccion;
					det.indicadorDeTraslado = "6"; 						// no constituye venta
					det.comunaDestino = comunaDestino;
					det.afecto = "SI";
					
					transportista.rutEmpresa = transportista.rutEmpresa.trim();
					transportista.rutEmpresa = transportista.rutEmpresa.replace(".", "").replace(".", "").replace(".", "").replace(".", "").replace(".", "");
					transportista.rutEmpresa = transportista.rutEmpresa.replace(",", "").replace(",", "").replace(",", "").replace(",", "").replace(",", "");
					transportista.rutEmpresa = transportista.rutEmpresa.replace(" ", "").replace(" ", "").replace(" ", "").replace(" ", "").replace(" ", "");
					det.rutTransportista = transportista.rutEmpresa;
					
					transportista.rutConductor = transportista.rutConductor.trim();
					transportista.rutConductor = transportista.rutConductor.replace(".", "").replace(".", "").replace(".", "").replace(".", "").replace(".", "");
					transportista.rutConductor = transportista.rutConductor.replace(",", "").replace(",", "").replace(",", "").replace(",", "").replace(",", "");
					transportista.rutConductor = transportista.rutConductor.replace(" ", "").replace(" ", "").replace(" ", "").replace(" ", "").replace(" ", "");
					det.rutChofer = transportista.rutConductor;
					
					det.patente = transportista.patente;
					det.nombreChofer = transportista.conductor;
					det.direccionDestino = direccionDestino;
					det.ciudadDestino = ciudadDestino;
					
					det.codigoSucursal = emisorTributario.getCodigoSucursal();
					
					detalle.add(det);
			}
			api.productos = detalle;
			api.documentoReferenciado = new ApiNuboxDocRef();
			//api.documentoReferenciado.tipoDocumentoReferenciado = "DES";
			//api.documentoReferenciado.folioDocumentoReferenciado = guia.numero.toString();
			//api.documentoReferenciado.fechaDocumentoReferenciado = Fechas.AAMMDD(guia.fecha);
			//api.documentoReferenciado.motivoReferencia = "Documento Mada";
			String msg = "Documento Mada nro "+guia.numero.toString()+" de fecha "+Fechas.DDMMAA(guia.fecha);
			api.documentoReferenciado.glosa = msg;
			
		return(api);
		
	}
	
	public static String generaFactArriendo(Connection con, String db, List<List<String>> resumenSubtotales, Cliente cliente, Proforma proforma, Map<String,String> mapDiccionario, EmisorTributario emisorTributario, List<List<String>> detalleAjuste, 
			List<List<String>> datos) {
		
		//******************************
		// OBTIENE DATOS A LLENAR
		//******************************
		
		
		
		//******************************
		// LLENA EL JSON PARA NUBOX
		//******************************
			ApiRelBaseDocDoc api = new ApiRelBaseDocDoc();
			List<ApiNuboxDocDet> detalle = new ArrayList<ApiNuboxDocDet>();
			
			
			
			
			for(int i=5; i<datos.size()-2; i++) {
				
				
				if(!datos.get(i).get(2).equals("")) {
					//obtiene datos
					
					Long idGrupo = Grupo.findIdXnombre(con,db, datos.get(i).get(0));
					Double auxPU = Double.parseDouble(datos.get(i).get(16).replaceAll(",", ""));

					String precioUnita = myformatapi.format(Math.round(auxPU));
					
				//fin obtiene datos
					
				// llena json
					ApiNuboxDocDet det = new ApiNuboxDocDet();
					
					cliente.rut = cliente.rut.trim();
					cliente.rut = cliente.rut.replace(".", "").replace(".", "").replace(".", "").replace(".", "").replace(".", "");
					cliente.rut = cliente.rut.replace(",", "").replace(",", "").replace(",", "").replace(",", "").replace(",", "");
					cliente.rut = cliente.rut.replace(" ", "").replace(" ", "").replace(" ", "").replace(" ", "").replace(" ", "");
					det.rutContraparte = cliente.rut;
					
					det.razonSocialContraparte = cliente.nombre;
					
					String giroCliente = cliente.giro;
					if(giroCliente.length()>40) {
						giroCliente = cliente.giro.substring(0,39);
					}
					det.giroContraparte = giroCliente; 				//"CONSTRUCCION"; // cliente.giro;
					det.tipo = "33";									// tipo documento 33 = factura
					det.folio = proforma.getId().toString(); 			// id proforma
					det.secuencia = idGrupo.toString(); 				// id grupo
					det.fecha = Fechas.AAMMDD(proforma.fecha);
					det.codigoItem = idGrupo.toString(); 				// id del grupo en reemplazo del codigo
					det.producto = datos.get(i).get(3); 	// nombre del equipo
					det.cantidad = "1";
					det.precio = "0";
					det.valor = precioUnita;
					det.comunaContraparte = cliente.comuna;
					det.direccionContraparte = cliente.direccion;
					det.afecto = "SI";
					
					det.unidadMedida = "GLB";
					
					
					det.codigoSucursal = emisorTributario.getCodigoSucursal();
					
					detalle.add(det);
				}
				
				
				
			}
			
			
			
			//NO ACEPTA NUMEROS NEGATIVOS
			for(int i=0; i<detalleAjuste.size(); i++){
				Double totAjuste = Double.parseDouble(detalleAjuste.get(i).get(1).replaceAll(",", ""));
				
				//if(totAjuste>0 || totAjuste<0) {
				if(totAjuste>0) {
					
					int secuencia = i+800;
					
					ApiNuboxDocDet det = new ApiNuboxDocDet();
					
					cliente.rut = cliente.rut.trim();
					cliente.rut = cliente.rut.replace(".", "").replace(".", "").replace(".", "").replace(".", "").replace(".", "");
					cliente.rut = cliente.rut.replace(",", "").replace(",", "").replace(",", "").replace(",", "").replace(",", "");
					cliente.rut = cliente.rut.replace(" ", "").replace(" ", "").replace(" ", "").replace(" ", "").replace(" ", "");
					det.rutContraparte = cliente.rut;
					
					det.razonSocialContraparte = cliente.nombre;
					
					String giroCliente = cliente.giro;
					if(giroCliente.length()>40) {
						giroCliente = cliente.giro.substring(0,39);
					}
					det.giroContraparte = giroCliente; 				//"CONSTRUCCION"; // cliente.giro;				
					det.tipo = "33";									
					det.folio = proforma.getId().toString(); 			
					det.secuencia = ""+secuencia; 								
					det.fecha = Fechas.AAMMDD(proforma.fecha);
					det.codigoItem = ""+secuencia; 								
					det.producto = detalleAjuste.get(i).get(0); 	
					det.cantidad = "1";
					det.precio = "0";
					det.valor = myformatapi.format(totAjuste);
					det.comunaContraparte = cliente.comuna;
					det.direccionContraparte = cliente.direccion;
					det.afecto = "SI";
					
					det.unidadMedida = "GLB";
					
					
					det.codigoSucursal = emisorTributario.getCodigoSucursal();
					
					detalle.add(det);
				}
			}
			
			
			
			
			
			
			
			
			
			
			api.productos = detalle;
			
			
			api.documentoReferenciado = new ApiNuboxDocRef();
			//api.documentoReferenciado.tipoDocumentoReferenciado = "DES";
			//api.documentoReferenciado.folioDocumentoReferenciado = guia.numero.toString();
			//api.documentoReferenciado.fechaDocumentoReferenciado = Fechas.AAMMDD(guia.fecha);
			//api.documentoReferenciado.motivoReferencia = "Documento Mada";
			String msg = "Documento Mada nro "+proforma.id.toString()+" de fecha "+Fechas.DDMMAA(proforma.fecha);
			api.documentoReferenciado.glosa = msg;
		
		
			
			String datosJSon = Json.toJson(api).toString();
			
		
		return(datosJSon);
	}
	
	
	
	
	public static String generaFactVenta(Connection con, String db, List<List<String>> guiasPer, Cliente cliente, Proforma proforma, Map<String,List<List<String>>> mapReportPorGuia10, EmisorTributario emisorTributario) {
		String datos = "";
		
		//******************************************************
     	//************       FALTA PROGRAMAR        ************
     	//******************************************************
		
		
		return(datos);
	}
	
	
	
	
}
