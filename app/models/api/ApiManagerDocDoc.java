package models.api;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

import play.libs.ws.*;

import play.libs.Json;
import models.tables.BodegaEmpresa;
import models.tables.Cliente;
import models.tables.EmisorTributario;
import models.tables.Guia;
import models.tables.Proforma;
import models.tables.Proyecto;
import models.tables.Sucursal;
import models.tables.Transportista;
import models.utilities.Fechas;




public class ApiManagerDocDoc {
	public String rut_empresa;
	public String tipodocumento;
	public String num_doc;
	public String fecha_doc;
	public String fecha_ref;
	public String fecha_vcto;
	public String cod_unidnegocio;
	public String rut_cliente;
	public String dire_cliente;
	public String rut_facturador;
	public String cod_vendedor;
	public String cod_comisionista;
	public String probabilidad;
	public String lista_precio;
	public String plazo_pago;
	public String cod_moneda;
	public String tasa_cambio;
	public String afecto;
	public String exento;
	public String iva;
	public String imp_esp;
	public String iva_ret;
	public String imp_ret;
	public String tipo_desc_global;
	public String monto_desc_global;
	public String total;
	public String deuda_pendiente;
	
	public String comentario1;
	public String comentario2;
	public String comentario3;
	public String comentario4;
	public String comentario5;
	public String modalidad;
	public String glosa;
	public String emitir; // "S" es nv y nada si es guia
	
	public String referencia;
	
	
	public List<ApiManagerDocDet> detalles;

	

	public ApiManagerDocDoc(String rut_empresa, String tipodocumento, String num_doc, String fecha_doc, String fecha_ref,
			String fecha_vcto, String cod_unidnegocio, String rut_cliente, String dire_cliente, String rut_facturador,
			String cod_vendedor, String cod_comisionista, String probabilidad, String lista_precio, String plazo_pago,
			String cod_moneda, String tasa_cambio, String afecto, String exento, String iva, String imp_esp,
			String iva_ret, String imp_ret, String tipo_desc_global, String monto_desc_global, String total,
			String deuda_pendiente, String comentario1, String comentario2, String comentario3, String comentario4,
			String comentario5, String modalidad, String glosa, String emitir, String referencia, List<ApiManagerDocDet> detalles) {
		super();
		this.rut_empresa = rut_empresa;
		this.tipodocumento = tipodocumento;
		this.num_doc = num_doc;
		this.fecha_doc = fecha_doc;
		this.fecha_ref = fecha_ref;
		this.fecha_vcto = fecha_vcto;
		this.cod_unidnegocio = cod_unidnegocio;
		this.rut_cliente = rut_cliente;
		this.dire_cliente = dire_cliente;
		this.rut_facturador = rut_facturador;
		this.cod_vendedor = cod_vendedor;
		this.cod_comisionista = cod_comisionista;
		this.probabilidad = probabilidad;
		this.lista_precio = lista_precio;
		this.plazo_pago = plazo_pago;
		this.cod_moneda = cod_moneda;
		this.tasa_cambio = tasa_cambio;
		this.afecto = afecto;
		this.exento = exento;
		this.iva = iva;
		this.imp_esp = imp_esp;
		this.iva_ret = iva_ret;
		this.imp_ret = imp_ret;
		this.tipo_desc_global = tipo_desc_global;
		this.monto_desc_global = monto_desc_global;
		this.total = total;
		this.deuda_pendiente = deuda_pendiente;
		this.comentario1 = comentario1;
		this.comentario2 = comentario2;
		this.comentario3 = comentario3;
		this.comentario4 = comentario4;
		this.comentario5 = comentario5;
		this.modalidad = modalidad;
		this.glosa = glosa;
		this.emitir = emitir;
		this.referencia = referencia;
		this.detalles = detalles;
		
	}



	public ApiManagerDocDoc() {
		super();
		this.rut_empresa = "";
		this.tipodocumento = "";
		this.num_doc = "0";
		this.fecha_doc = "";
		this.fecha_ref = "";
		this.fecha_vcto = "";
		this.cod_unidnegocio = "1";
		this.rut_cliente = "";
		this.dire_cliente = "LEGAL"; //corresponde a la direccion de destino
		this.rut_facturador = "";
		this.cod_vendedor = "apimada";
		this.cod_comisionista = "";
		this.probabilidad = "";
		this.lista_precio = "2";
		this.plazo_pago = "0";
		this.cod_moneda = "";
		this.tasa_cambio = "1";
		this.afecto = "0";
		this.exento = "0";
		this.iva = "0";
		this.imp_esp = "0";
		this.iva_ret = "0";
		this.imp_ret = "0";
		this.tipo_desc_global = "";
		this.monto_desc_global = "0";
		this.total = "0";
		this.comentario1 = "";
		this.comentario2 = "";
		this.comentario3 = "";
		this.comentario4 = "";
		this.comentario5 = "";
		this.modalidad = "6";
		this.glosa = "";
		this.emitir = "";
		this.referencia = "";
		this.deuda_pendiente = "0";
		
	}
	
	

	  
	  

//	public static String generaProforma(Connection con, String db, String json) {
//		String rs = ApiManagerDocDoc.genera(con, db, json);
//		return(rs + "\n JSON: \n "+json);
//	}
//	
//	public static String generaGuia(Connection con, String db, Long id_guia, Long id_transportista) {
//		Guia guiaFinal = Guia.find(con, db, id_guia);
//		Transportista transp = Transportista.find(con, db, id_transportista);
//		ApiManagerDocDoc api = ApiManagerDocDoc.generaGuiaSalida(con, db, guiaFinal, transp);
//		String jsonApi = Json.toJson(api).toString();
//		String rs = ApiManagerDocDoc.genera(con, db, jsonApi);
//		Guia.modificaPorCampo(con, db, "jsonGenerado", id_guia, jsonApi);
//		return(rs + "\n JSON: \n "+Json.toJson(api).toString());
//	}

	
	//**************************************************************
	// CONSUME LOS SERVICIOS EN MANAGER:
	//**************************************************************
	

		public static String genera(Connection con, String db, String json, WSClient ws, Long id_proforma) {
			
			EmisorTributario emisor = EmisorTributario.find(con, db);
			
			String apiUser = emisor.apiUser;
			String apiKey = emisor.apiKey;
			
			String typeAuth = "application/x-www-form-urlencoded";
			String urlAuth = "https://aries.managermas.cl/api/auth/";
			String typeSend = "application/json";
			String urlSend = "https://aries.managermas.cl/api/import/create-document/";
			
			try {
				String dataPost = "username="+URLEncoder.encode(apiUser,"UTF8")+"&password="+URLEncoder.encode(apiKey,"UTF8");
				String rs = ws.url(urlAuth).setContentType(typeAuth).post(dataPost).thenApply(
				          (WSResponse response) -> {
						        	  JsonNode jsonNode = response.asJson();
						        	  String token = jsonNode.findPath("auth_token").toString().replaceAll("\"", "");
				        		return(ws.url(urlSend).setContentType(typeSend).addHeader("Authorization", "Token "+token).post(json).thenApply(
							          (WSResponse response2) -> {
							        	  JsonNode contact = response2.asJson();
							        	  String respuesta = contact.findPath("mensaje").toString();
							        	  if((long) id_proforma > (long) 0) {
							        		  Proforma.updateResponse(con, db, id_proforma, contact.toString());
							        	  }
							        	  return(respuesta);
							          }
							     ).toCompletableFuture());
				            }).toCompletableFuture().get().get();
				return rs;
			} catch (UnsupportedEncodingException | java.lang.InterruptedException | java.util.concurrent.ExecutionException e) {
				e.printStackTrace();
				return "FALLA";
			}
		}
	
	//**************************************************************
	// FINAL DE CONSUME LOS SERVICIOS EN MANAGER:
	//**************************************************************
	
	


	static DecimalFormat myformatapi = new DecimalFormat("#.00");
	
	public static ApiManagerDocDoc generaGuiaSalida (Connection con, String db,Guia guia,Transportista transportista, Map<String,String> mapDiccionario, Map<String,String> mapPermiso) {
		
		//******************************
		// OBTIENE DATOS A LLENAR
		//******************************
			
			EmisorTributario emisorTributario = EmisorTributario.find(con, db);
			Long id_bodegaOrigen = guia.getId_bodegaOrigen();
			Long id_bodegaDestino = guia.getId_bodegaDestino();
			BodegaEmpresa bodegaOrigen = BodegaEmpresa.findXIdBodega(con, db, id_bodegaOrigen);
			BodegaEmpresa bodegaDestino = BodegaEmpresa.findXIdBodega(con, db,id_bodegaDestino);
			Cliente cliente = Cliente.find(con, db, bodegaDestino.getId_cliente());
			
			List<List<String>> detalleGuia = new ArrayList<List<String>>();
			
			if ((long) bodegaOrigen.esInterna == (long) 1) {
				detalleGuia = Guia.findDetalleGuiaOrigenDestinoYPrecios(con, db, guia.getId(), guia.getId_bodegaDestino(), mapDiccionario.get("pais"), guia.getId_bodegaOrigen());
			}else {
				detalleGuia = Guia.findDetalleGuiaOrigenDestinoYPrecios(con, db, guia.getId(), guia.getId_bodegaOrigen(), mapDiccionario.get("pais"), guia.getId_bodegaOrigen());
			}
		
			Long totalNeto=(long)0;
			
			
			Double tasaIvaArrAuxiliar = emisorTributario.getTasaIva();
		    
		     if(mapPermiso.get("parametro.ivaPorBodega")!=null && mapPermiso.get("parametro.ivaPorBodega").equals("1")) {
		        	if(bodegaDestino!=null) {
		        		if(bodegaDestino.getIvaBodega() > 0) {
		        			tasaIvaArrAuxiliar = bodegaDestino.getIvaBodega() * 100;
		        		}else {
		        			Sucursal sucursal = Sucursal.find(con, db, bodegaDestino.getId_sucursal().toString());
		        			if(sucursal!=null && sucursal.getIvaSucursal() > 0) {
		        				tasaIvaArrAuxiliar = sucursal.getIvaSucursal();
		        			}
		        		}
		        		
		        	}
		      }
		     
			String IvaTasa = tasaIvaArrAuxiliar.toString();
		
			for(int i=0;i<detalleGuia.size();i++) {
				
				String auxPrecio = detalleGuia.get(i).get(9).trim();		 				// precio unitario de venta en moneda de origen
				
				// ***************************
				// SOLO APLICA HOHE
				Long esVenta = Long.parseLong(detalleGuia.get(i).get(20).trim());	// es venta
				if(mapDiccionario.get("nEmpresa").equals("HOHE") && (long)esVenta==(long)0) {
					auxPrecio = detalleGuia.get(i).get(16);		 					// valorArriendo unitario aplicando todos los descuentos golbales descontinuados
					if(auxPrecio.equals("")||auxPrecio.trim().length()<=0) {
						auxPrecio = "0";
					}
				}
				// ***************************
				
					
				Double precioUnitario = (double)0;
				String auxNum = auxPrecio;
	   		    if(auxNum==null || auxNum.trim().length()<=0) {
	   		    	auxNum = "0";
	   		    }
	   		    precioUnitario = Double.parseDouble(auxNum.replaceAll(",", ""));
				
				String auxCantidad = detalleGuia.get(i).get(8).trim();
				if(auxCantidad.equals("") || auxCantidad.length()<=0) {
					auxCantidad = "0";
				}
				
				Double cantidad = (double)0;
				auxNum = auxCantidad.trim();
				if(auxNum==null || auxNum.trim().length()<=0) {
	   		    	auxNum = "0";
	   		    }
				cantidad = Double.parseDouble(auxNum.replaceAll(",", ""));
				
				Double auxPTotal = precioUnitario*cantidad;
			
				totalNeto = Math.round(totalNeto+auxPTotal);
			}
		
		Long iva = Math.round(totalNeto * Double.parseDouble(IvaTasa.trim())/100);
		Long total=totalNeto+iva;
		
		String strNeto = myformatapi.format(totalNeto);
		String strIva = myformatapi.format(iva);
		String strTotal = myformatapi.format(total);
		
		// lleno objeto para generar json a futuro
		
		ApiManagerDocDoc api = new ApiManagerDocDoc();
		emisorTributario.rut = emisorTributario.rut.trim();
		emisorTributario.rut = emisorTributario.rut.replace(".", "").replace(".", "").replace(".", "").replace(".", "").replace(".", "");
		emisorTributario.rut = emisorTributario.rut.replace(",", "").replace(",", "").replace(",", "").replace(",", "").replace(",", "");
		emisorTributario.rut = emisorTributario.rut.replace(" ", "").replace(" ", "").replace(" ", "").replace(" ", "").replace(" ", "");
		api.rut_empresa = emisorTributario.rut;
		
		api.tipodocumento = "GDVE"; //se debe parametrizar GDVE ES GUIA Y NV ES NOTA VENTA PARA FACTURA
		api.num_doc = "0"; // guia.numero.toString();
		api.fecha_doc = Fechas.DDMMAA(guia.fecha);
		// api.fecha_ref = "";
		// api.fecha_vcto = "";
		// api.cod_unidnegocio = "";
		
		cliente.rut = cliente.rut.trim();
		cliente.rut = cliente.rut.replace(".", "").replace(".", "").replace(".", "").replace(".", "").replace(".", "");
		cliente.rut = cliente.rut.replace(",", "").replace(",", "").replace(",", "").replace(",", "").replace(",", "");
		cliente.rut = cliente.rut.replace(" ", "").replace(" ", "").replace(" ", "").replace(" ", "").replace(" ", "");
		api.rut_cliente = cliente.rut;
		
		api.dire_cliente = "LEGAL"; //cliente.direccion; CORRESPONDE AL CODIGO DE DIRECCION DE DESTINO
		// api.rut_facturador = "";
		api.cod_vendedor = bodegaDestino.comercial; //"apimada"; // ESTA ASOCIADO AL USERNAME
		// api.cod_comisionista = "";
		// api.probabilidad = "";
		api.lista_precio = "2";  // ESTE CODIGO 582 CAMBIARA CUANDO ESTE DEFINITIVO - definitivo es 2
		api.plazo_pago = "0"; // 0 PARA GUIA Y "30" PARA FACTURA
		api.cod_moneda = "CLP";
		// api.tasa_cambio = "0";
		api.afecto = strNeto;
		// api.Exento = "0";
		api.iva = strIva;
		// api.imp_esp = "0";
		// api.iva_ret = "0";
		// api.imp_ret = "0";
		// api.tipo_desc_global = "";
		// api.monto_desc_global = "0";
		api.total = strTotal;
		// api.deuda_pendiente = "0";
		
		String[] bod = bodegaDestino.nombre.split(" ");
		String comercial = bodegaDestino.comercial;
		String[] fecha = Fechas.AAMMDD(guia.fecha).split("-");
		Proyecto proyecto = Proyecto.find(con, db, bodegaDestino.id_proyecto);
		String dirObra="";
		if(proyecto!=null) {
			dirObra = proyecto.direccion+", "+proyecto.comuna+", "+proyecto.ciudad;
		}
		
		api.comentario1 = bod[0];
		api.comentario2 = comercial;
		api.comentario3 = fecha[1];
		api.comentario4 = fecha[0];
		api.comentario5 = dirObra;
		
		api.modalidad = "6"; //6 INDICA SOLO TRASLADO NO CONSTITUYE VENTA
		
		transportista.rutConductor = transportista.rutConductor.trim();
		transportista.rutConductor = transportista.rutConductor.replace(".", "").replace(".", "").replace(".", "").replace(".", "").replace(".", "");
		transportista.rutConductor = transportista.rutConductor.replace(",", "").replace(",", "").replace(",", "").replace(",", "").replace(",", "");
		transportista.rutConductor = transportista.rutConductor.replace(" ", "").replace(" ", "").replace(" ", "").replace(" ", "").replace(" ", "");
		api.glosa = "Conductor: "+transportista.conductor+" - RUT: "+transportista.rutConductor+" - PLACA: "+transportista.patente+" - OBRA: "+bodegaDestino.nombre + 
				" - Nro_MADA: " + guia.numero.toString();
		api.emitir="";
		api.referencia = bodegaDestino.nombre;
		
		
		
		List<ApiManagerDocDet> detalle = new ArrayList<ApiManagerDocDet>();
		for(int i=0;i<detalleGuia.size();i++) {
			
			Double auxCant = (double)0;
			String auxNum = detalleGuia.get(i).get(8).trim();
   		    if(auxNum==null || auxNum.trim().length()<=0) {
   		    	auxNum = "0";
   		    }
   		    auxCant = Double.parseDouble(auxNum.replaceAll(",", ""));
			String cantidad = myformatapi.format(auxCant);
			
			Double auxPU = (double)0;
			auxNum = detalleGuia.get(i).get(9).trim();
			// ***************************
			// SOLO APLICA HOHE
			Long esVenta = Long.parseLong(detalleGuia.get(i).get(20).trim());
			if(mapDiccionario.get("nEmpresa").equals("HOHE") && esVenta==0) {
				auxNum = detalleGuia.get(i).get(16);
			}
			// ***************************
   		    if(auxNum==null || auxNum.trim().length()<=0) {
   		    	auxNum = "0";
   		    }
   		    auxPU = Double.parseDouble(auxNum.replaceAll(",", ""));
			String precioUnita = myformatapi.format(auxPU);
			
			ApiManagerDocDet det = new ApiManagerDocDet();
			det.cod_producto = detalleGuia.get(i).get(5);  // EN CASO DE FACTURA USAR "I001"
			det.cantidad = cantidad;
			det.unidad = detalleGuia.get(i).get(7);  // la unidad de equipo en caso de factura arriendo es fija UNS
			det.precio_unit = precioUnita;
			det.moneda_det = "CLP"; // eliminado -> det.moneda_det = detalleGuia.get(i).get(11);
			// det.tasa_cambio_det = (double) 1;
			// det.nro_serie = "";
			// det.num_lote = "0";
			// det.fecha_vec = "";
			det.cen_cos = "1";
			det.tipo_desc = "M";
			// det.descuento = "0";
			det.ubicacion = "U1";
			det.bodega = "B1";
			// det.concepto1 = "";
			// det.concepto2 = "";
			// det.concepto3 = "";
			// det.concepto4 = "";
			det.descrip = detalleGuia.get(i).get(6);
			// det.desc_adic = "";
			det.stock = "1";  // 1 EN GUIAS "0" EN FACTURAS
			// det.cod_impesp1 = "";
			// det.mon_impesp1 = "";
			// det.cod_impesp2 = "";
			// det.mon_impesp2 = "";
			
			detalle.add(det);
		}
		api.detalles = detalle;
		
		
		return(api);
		
	}
	
	public static String generaFactArriendo(Connection con, String db, Cliente cliente, Proforma proforma) {
		ApiManagerDocDoc api = new ApiManagerDocDoc();
		EmisorTributario emisorTributario = EmisorTributario.find(con, db);
		Long neto = Math.round(proforma.getNeto());
		Long iva = Math.round(proforma.getIva());
		Long netoSinAjustes = Math.round(proforma.getNetoSinAjustes());
		Long netoSoloAjustes = Math.round(proforma.getNetoSoloAjustes()*-1);
		
		Long total = neto + iva;
		String valPeriodoDesde = proforma.desde;
		String valPeriodoHasta = proforma.hasta;
		BodegaEmpresa bodegaEmpresa = BodegaEmpresa.findXIdBodega(con, db, proforma.id_bodegaEmpresa);
		// lleno objeto para generar json a futuro
				emisorTributario.rut = emisorTributario.rut.trim();
				emisorTributario.rut = emisorTributario.rut.replace(".", "").replace(".", "").replace(".", "").replace(".", "").replace(".", "");
				emisorTributario.rut = emisorTributario.rut.replace(",", "").replace(",", "").replace(",", "").replace(",", "").replace(",", "");
				emisorTributario.rut = emisorTributario.rut.replace(" ", "").replace(" ", "").replace(" ", "").replace(" ", "").replace(" ", "");
				api.rut_empresa = emisorTributario.rut;
				
				api.tipodocumento = "NV"; //se debe parametrizar GDVE ES GUIA Y NV ES NOTA VENTA PARA FACTURA
				api.num_doc = proforma.id.toString();
				api.fecha_doc = Fechas.DDMMAA(valPeriodoHasta); //Fechas.DDMMAA(proforma.fecha);
				api.fecha_ref = Fechas.DDMMAA(valPeriodoHasta);
				
				cliente.rut = cliente.rut.trim();
				cliente.rut = cliente.rut.replace(".", "").replace(".", "").replace(".", "").replace(".", "").replace(".", "");
				cliente.rut = cliente.rut.replace(",", "").replace(",", "").replace(",", "").replace(",", "").replace(",", "");
				cliente.rut = cliente.rut.replace(" ", "").replace(" ", "").replace(" ", "").replace(" ", "").replace(" ", "");
				api.rut_cliente = cliente.rut;
				
				api.dire_cliente = "LEGAL"; //cliente.direccion; CORRESPONDE AL CODIGO DE DIRECCION DE DESTINO
				api.cod_vendedor = bodegaEmpresa.comercial; //"apimada"; // ESTA ASOCIADO AL USERNAME
				api.lista_precio = "2";  // ESTE CODIGO 582 CAMBIARA CUANDO ESTE DEFINITIVO - definitivo es 2
				api.plazo_pago = "0"; // 0 PARA GUIA Y "30" PARA FACTURA
				api.cod_moneda = "CLP";
			//	api.afecto = netoSinAjustes.toString(); //neto.toString();
				api.afecto = neto.toString(); //neto.toString();
				
				api.tipo_desc_global = "M";
				api.monto_desc_global = netoSoloAjustes.toString();
				
				api.iva = iva.toString();
				api.total = total.toString();
				
				String[] bod = bodegaEmpresa.nombre.split(" ");
				String comercial = bodegaEmpresa.comercial;
				String[] fecha = Fechas.AAMMDD(valPeriodoHasta).split("-");
				Proyecto proyecto = Proyecto.find(con, db, bodegaEmpresa.id_proyecto);
				String dirObra="";
				if(proyecto!=null) {
					dirObra = proyecto.direccion+", "+proyecto.comuna+", "+proyecto.ciudad;
				}
				
				api.comentario1 = bod[0];
				api.comentario2 = comercial;
				api.comentario3 = fecha[1];
				api.comentario4 = fecha[0];
				api.comentario5 = dirObra;
				
//				api.comentario1 = "Obra: "+bodegaEmpresa.nombre+" - Periodo desde "+Fechas.DDMMAA(valPeriodoDesde)+ " hasta "+Fechas.DDMMAA(valPeriodoHasta) +
//						" - Nro_MADA: " + proforma.id.toString();
				
				api.modalidad = ""; //6 INDICA SOLO TRASLADO NO CONSTITUYE VENTA
				api.emitir = "S";
				api.referencia = ""; // bodegaEmpresa.nombre;
				
				List<ApiManagerDocDet> detalle = new ArrayList<ApiManagerDocDet>();
					ApiManagerDocDet det = new ApiManagerDocDet();
					det.cod_producto = "I001";  // EN CASO DE FACTURA USAR "I001"
					det.cantidad = "1";
					det.unidad = "UNS"; //UNIDAD SOLO PARA ACTIVO EN EXISTENCIA ES "U"
					det.precio_unit = netoSinAjustes.toString();
					det.moneda_det = "CLP";
					det.cen_cos = "1";
					det.tipo_desc = "M";
					det.ubicacion = "";
					det.bodega = "";
					det.descrip = "ARRIENDO";
					det.stock = "R";  // 1 EN GUIAS "R" EN FACTURAS
					det.desc_adic = "Obra: "+bodegaEmpresa.nombre+" - Periodo desde "+Fechas.DDMMAA(valPeriodoDesde)+ " hasta "+Fechas.DDMMAA(valPeriodoHasta);
					detalle.add(det);
				api.detalles = detalle;
				String datos = Json.toJson(api).toString();
		return(datos);
	}
	
	
	
	
	
	public static String generaFactVenta(Connection con, String db, List<List<String>> guiasPer, Cliente cliente, Proforma proforma, Map<String,List<List<String>>> mapReportPorGuia10) {
		
		
		
		ApiManagerDocDoc api = new ApiManagerDocDoc();
		EmisorTributario emisorTributario = EmisorTributario.find(con, db);
		Long neto = Math.round(proforma.getNeto());
		Long iva = Math.round(proforma.getIva());
		Long total = neto+iva;
	//	Long netoSinAjustes = Math.round(proforma.getNetoSinAjustes());
		Long netoSoloAjustes = Math.round(proforma.getNetoSoloAjustes()*-1);
	//	String valPeriodoDesde = proforma.desde;
		String valPeriodoHasta = proforma.hasta;
		BodegaEmpresa bodegaEmpresa = BodegaEmpresa.findXIdBodega(con, db, proforma.id_bodegaEmpresa);
		// lleno objeto para generar json a futuro
				emisorTributario.rut = emisorTributario.rut.trim();
				emisorTributario.rut = emisorTributario.rut.replace(".", "").replace(".", "").replace(".", "").replace(".", "").replace(".", "");
				emisorTributario.rut = emisorTributario.rut.replace(",", "").replace(",", "").replace(",", "").replace(",", "").replace(",", "");
				emisorTributario.rut = emisorTributario.rut.replace(" ", "").replace(" ", "").replace(" ", "").replace(" ", "").replace(" ", "");
				api.rut_empresa = emisorTributario.rut;
				
				api.tipodocumento = "NV"; //se debe parametrizar GDVE ES GUIA Y NV ES NOTA VENTA PARA FACTURA
				api.num_doc = proforma.id.toString();
				api.fecha_doc = Fechas.DDMMAA(valPeriodoHasta); //Fechas.DDMMAA(proforma.fecha);
				api.fecha_ref = Fechas.DDMMAA(valPeriodoHasta);
				
				cliente.rut = cliente.rut.trim();
				cliente.rut = cliente.rut.replace(".", "").replace(".", "").replace(".", "").replace(".", "").replace(".", "");
				cliente.rut = cliente.rut.replace(",", "").replace(",", "").replace(",", "").replace(",", "").replace(",", "");
				cliente.rut = cliente.rut.replace(" ", "").replace(" ", "").replace(" ", "").replace(" ", "").replace(" ", "");
				api.rut_cliente = cliente.rut;
				
				api.dire_cliente = "LEGAL"; //cliente.direccion; CORRESPONDE AL CODIGO DE DIRECCION DE DESTINO
				api.cod_vendedor = bodegaEmpresa.comercial; //"apimada"; // ESTA ASOCIADO AL USERNAME
				api.lista_precio = "2";  // ESTE CODIGO 582 CAMBIARA CUANDO ESTE DEFINITIVO - definitivo es 2
				api.plazo_pago = "0"; // 0 PARA GUIA Y "30" PARA FACTURA
				api.cod_moneda = "CLP";
				api.afecto = neto.toString();
				
				api.tipo_desc_global = "M";
				api.monto_desc_global = netoSoloAjustes.toString();
				
				api.iva = iva.toString();
				
				
				api.total = total.toString();
				
				String[] bod = bodegaEmpresa.nombre.split(" ");
				String comercial = bodegaEmpresa.comercial;
				String[] fecha = Fechas.AAMMDD(valPeriodoHasta).split("-");
				Proyecto proyecto = Proyecto.find(con, db, bodegaEmpresa.id_proyecto);
				String dirObra="";
				if(proyecto!=null) {
					dirObra = proyecto.direccion+", "+proyecto.comuna+", "+proyecto.ciudad;
				}
				
				api.comentario1 = bod[0];
				api.comentario2 = comercial;
				api.comentario3 = fecha[1];
				api.comentario4 = fecha[0];
				api.comentario5 = dirObra;
				
				// api.comentario1 = "Obra: "+bodegaEmpresa.nombre+" - Periodo desde "+Fechas.DDMMAA(valPeriodoDesde)+ " hasta "+Fechas.DDMMAA(valPeriodoHasta);
				
				api.modalidad = ""; //6 INDICA SOLO TRASLADO NO CONSTITUYE VENTA
				api.emitir = "S";
				api.referencia = ""; //bodegaEmpresa.nombre;
				
				List<ApiManagerDocDet> detalle = new ArrayList<ApiManagerDocDet>();
				Double totalVenta = (double) 0;
				Double totalNeto = (double)0;
				
		
				for(int i=0;i<guiasPer.size();i++) {
					
					List<List<String>> detalleAux = mapReportPorGuia10.get(guiasPer.get(i).get(8));
	            	
	            	if(detalleAux!=null) {
	            		Double subTotal = (double) 0;
	    				for(int j=0;j<detalleAux.size();j++){
	    					String dato = detalleAux.get(j).get(20);
							if(dato.equals("")||dato.equals(" ")) dato = "0";
							Double tot = (double)0;
							String auxNum = dato.trim();
				   		    if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
				   		    tot = Double.parseDouble(auxNum.replaceAll(",", ""));
							totalVenta = totalVenta + tot;
							subTotal=subTotal+tot;
	    				}
	    				
	    				if(subTotal>0) {
	    					for(int j=0;j<detalleAux.size();j++){
	    						String dato = detalleAux.get(j).get(20);
								if(dato.equals("")||dato.equals(" ")) dato = "0";
	    						Double tot = (double)0;
	    						String auxNum = dato.trim();
	    			   		    if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
	    			   		    tot = Double.parseDouble(auxNum.replaceAll(",", ""));
								
	    						if(tot>0) {
	    							Double auxCant = (double)0;
	    							auxNum = detalleAux.get(j).get(13).trim();
	        			   		    if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
	        			   		    auxCant = Double.parseDouble(auxNum.replaceAll(",", ""));
	    							String cantidad = myformatapi.format(auxCant);
	    							Double auxTasa = (double)0;
	    							auxNum = detalleAux.get(j).get(18).trim();
	        			   		    if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
	        			   		    auxTasa = Double.parseDouble(auxNum.replaceAll(",", ""));
	    							Double auxPU = (double)0;
	    							auxNum = detalleAux.get(j).get(15).trim();
	        			   		    if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
	        			   		    auxPU = Double.parseDouble(auxNum.replaceAll(",", ""));
	    							String precioUnita = myformatapi.format(auxPU*auxTasa);
	    							String descripcion = detalleAux.get(j).get(11);
	    							
	    							ApiManagerDocDet det = new ApiManagerDocDet();
	    							det.cod_producto = detalleAux.get(j).get(10);  // EN CASO DE FACTURA USAR "I001"
	    							det.cantidad = cantidad;
	    							det.unidad = detalleAux.get(j).get(12);  // la unidad de equipo en caso de factura arriendo es fija UNS
	    							det.precio_unit = precioUnita;
	    							det.moneda_det = "CLP";
	    							det.cen_cos = "1";
	    							det.tipo_desc = "M";
	    							det.ubicacion = "";
	    							det.bodega = "";
	    							det.descrip = descripcion;
	    							det.stock = "R";  // 1 EN GUIAS "R" EN FACTURAS
	    							det.desc_adic = ""; // se deja en blanco "Obra: "+bodegaEmpresa.nombre+" - Periodo desde "+Fechas.DDMMAA(valPeriodoDesde)+ " hasta "+Fechas.DDMMAA(valPeriodoHasta);
	    							detalle.add(det);
	    							totalNeto += Double.parseDouble(det.precio_unit)*Double.parseDouble(det.cantidad);
	    						}
	    					}
	    				}
	            	}
				}
				
				
				
				api.afecto = myformatapi.format(totalNeto-netoSoloAjustes);
				
				//api.iva = myformatapi.format(totalNeto*0.19);
				api.total = myformatapi.format(Double.parseDouble(api.afecto)+Double.parseDouble(api.iva));
				
				api.detalles = detalle;
				String datos = Json.toJson(api).toString();
		return(datos);
	}
	
	
	
}
