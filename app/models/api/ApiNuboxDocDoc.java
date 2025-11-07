package models.api;


import java.nio.charset.StandardCharsets;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;
import play.libs.ws.WSResponse;



public class ApiNuboxDocDoc {
	
	public List<ApiNuboxDocDet> productos;
	public ApiNuboxDocRef documentoReferenciado;

	public ApiNuboxDocDoc(List<ApiNuboxDocDet> productos, ApiNuboxDocRef documentoReferenciado) {
		super();
		this.productos = productos;
		this.documentoReferenciado = documentoReferenciado;
	}

	public ApiNuboxDocDoc() {
		super();
	}

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
//**************************************************************
// CONSUME LOS SERVICIOS EN NUBOX:
//**************************************************************
	
	public static String genera(Connection con, String db, String json, WSClient ws, Long id_proforma, Long id_guia) {
		try {
			EmisorTributario emisor = EmisorTributario.find(con, db);
			String apiUser = emisor.apiUser;
			String apiKey = emisor.apiKey;
			String apiUrl = emisor.apiUrl;
			final String rutEmpresa = emisor.getRut();
			final String rutFuncionario =  emisor.getRutFuncionario();
			final String emitir = "false";
			final String jsonDefinitivo = json + " \\ {rutFuncionario=["+rutFuncionario+"], emitir=["+emitir+"]} ";
			String original = apiUser+":"+apiKey;
			byte[] bytes = original.getBytes(StandardCharsets.UTF_8);
			String base64Encoded = Base64.getEncoder().encodeToString(bytes);
			String dataPost = "Basic "+base64Encoded;
			String rs = ws.url(apiUrl)
					.addHeader("Content-Type","application/json")
					.addHeader("Accept","application/json")
					.addHeader("Authorization",dataPost)
					.addHeader("Content-Length","0")
					.post(dataPost)
					.thenApply(
			          (WSResponse response) -> {
			        	  JsonNode jsonNode = response.asJson();
			        	  String token = response.getHeaderValues("token").get(0);
			        	  JsonNode aux2 = jsonNode.findPath("Sistemas");
			        	  final String nroSerie = aux2.findPath("NumeroDeSerie").asText();
			        	  String url2 = "https://api.nubox.com/Nubox.API/factura/documento/"+rutEmpresa+"/"+nroSerie+"/rutFuncionario/1/emitir/venta";
			        	  WSRequest rss = ws.url(url2)
			        				.addHeader("Content-Type","application/json")
		                			.addHeader("Accept","application/json")
		                			.addHeader("Token",token)
		                			.addQueryParameter("rutFuncionario", rutFuncionario)
		                			.addQueryParameter("emitir", emitir);
			        		return( rss.post(jsonDefinitivo)
			        				.thenApply(
						          (WSResponse response2) -> {
						        	  String respuesta = response2.getBody();
						        	  JsonNode jsonNode2 = response2.asJson();
						        	  JsonNode aux3 = jsonNode2.findPath("Message");
						        	  if(aux3.asText().trim().length()>0) {
						        		  respuesta = "Error: el giro o dirección del cliente faltan, o el detalle del documento supera el máximo de líneas (20).";
						        	  }else {
						        		  respuesta = "API Nubox enviada con éxito";
						        	  }
						        	  if((long) id_proforma > (long) 0) {
						        		  Proforma.updateResponse(con, db, id_proforma, response2.getBody().substring(2));
						        	  }
						        	  if((long) id_guia > (long) 0) {
						        		  Guia.modificaPorCampo(con, db, "response", id_guia, jsonNode2.toString());
						        	  }
						        	  return(respuesta);
						          }
						     ).toCompletableFuture());
			            }).toCompletableFuture().get(10000,TimeUnit.MILLISECONDS).get(100000,TimeUnit.MILLISECONDS);
			return rs;
		} catch (java.lang.InterruptedException | java.util.concurrent.ExecutionException | TimeoutException e) {
			String className = ApiNuboxDocDoc.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
			return "FALLA";
		}
	}
	
	//**************************************************************
	// FINAL DE CONSUME LOS SERVICIOS EN NUBOX:
	//**************************************************************

	static DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
	static DecimalFormat myformatapi = new DecimalFormat("#.00",symbols);
	
	public static ApiNuboxDocDoc generaGuiaSalida (Connection con, String db, Guia guia, Transportista transportista, Map<String,String> mapDiccionario) {
		try{
			//******************************
			// OBTIENE DATOS A LLENAR
			//******************************
			EmisorTributario emisorTributario = EmisorTributario.find(con, db);
			//			Long id_bodegaOrigen = guia.getId_bodegaOrigen();
			Long id_bodegaDestino = guia.getId_bodegaDestino();
			//			BodegaEmpresa bodegaOrigen = BodegaEmpresa.findXIdBodega(con, db, id_bodegaOrigen);
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
			// FINAL DE OBTIENE DATOS A LLENAR
			//******************************
			//******************************
			// LLENA EL JSON PARA NUBOX
			//******************************
			ApiNuboxDocDoc api = new ApiNuboxDocDoc();
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
		}catch(Exception e){
			String className = ApiNuboxDocDoc.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
			return null;
		}
	}
	
	public static String generaFactArriendo(Connection con, String db, List<List<String>> resumenSubtotales, Cliente cliente, Proforma proforma, Map<String,String> mapDiccionario,
											EmisorTributario emisorTributario, List<List<String>> detalleAjuste, List<List<String>> datos) {
		try{
			//******************************
			// OBTIENE DATOS A LLENAR
			//******************************
			//******************************
			// LLENA EL JSON PARA NUBOX
			//******************************
			ApiNuboxDocDoc api = new ApiNuboxDocDoc();
			List<ApiNuboxDocDet> detalle = new ArrayList<ApiNuboxDocDet>();
			for(int i=5; i<datos.size()-2; i++) {
				if(!datos.get(i).get(2).equals("")) {
					//obtiene datos
					Long idGrupo = Grupo.findIdXnombre(con,db, datos.get(i).get(0));
					Double auxPU = (double) 1;
					try {
						auxPU = Double.parseDouble(datos.get(i).get(16).replaceAll(",", ""));
					}catch(Exception e) {}
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
					det.codigoItem = idGrupo.toString(); 				// id del grupo en reem del codigo
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
		}catch(Exception e){
			String className = ApiNuboxDocDoc.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
			return null;
		}
	}

	public static String generaFactVenta(Connection con, String db, List<List<String>> guiasPer, Cliente cliente, Proforma proforma, Map<String,List<List<String>>> mapReportPorGuia10, EmisorTributario emisorTributario) {
		String datos = "";
		//******************************************************
     	//************       FALTA PROGRAMAR        ************
     	//******************************************************
		return(datos);
	}
	
	
	
	
}
