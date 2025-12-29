package models.api;


import controllers.HomeController;
import models.tables.*;
import models.utilities.Archivos;
import models.utilities.Fechas;
import org.apache.poi.util.TempFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class WebSoftlandDesk {

	public List<ApiNuboxDocDet> productos;
	public ApiNuboxDocRef documentoReferenciado;

	public WebSoftlandDesk(List<ApiNuboxDocDet> productos, ApiNuboxDocRef documentoReferenciado) {
		super();
		this.productos = productos;
		this.documentoReferenciado = documentoReferenciado;
	}

	public WebSoftlandDesk() {
		super();
	}

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
//**************************************************************
// CONSUME LOS SERVICIOS EN SOFTLAND DE ESCRITORIO GUIA:
//**************************************************************
	public static String generaGuia(Connection con, String db, File cvs, WSClient ws, EmisorTributario emisorTributario, Guia guia) {
		try {
			byte[] csvBytes = Files.readAllBytes(cvs.toPath());
			String base64String = Base64.getEncoder().encodeToString(csvBytes);
			try {
				Files.delete(cvs.toPath());
			} catch (IOException e) {
				System.err.println("No se pudo eliminar el archivo temporal inmediatamente.");
			}
			String webUser = emisorTributario.getApiUser();
			String webClave = emisorTributario.getApiKey();
			String webUrl = emisorTributario.getApiUrl();
			String areaDeDatos = "\\\\Softland\\erp\\SOFTLAND\\DATOS\\ALZATEC\\";

			String data = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
					+ "		<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">\n"
					+ "			<soapenv:Header/>\n"
					+ "			<soapenv:Body>\n"
					+ "		    	<tem:CaptudaGuiaSalida>\n"
					+ "		      		<tem:base64File>\n"
					+ "		      			<UserName>webservice</UserName>\n"
					+ "		      			<Password>"+webClave+"</Password>\n"
					+ "		      			<Data>\n"
					+							base64String
					+ "						</Data>\n"
					+ "    			    </tem:base64File>\n"
					+ "    				<tem:extensionArchivo>txt</tem:extensionArchivo>\n"
					+ "    				<tem:areaDeDatos>"+areaDeDatos+"</tem:areaDeDatos>\n"
					+ "    				<tem:usuario>"+webUser+"</tem:usuario>\n"
					+ "    				<tem:nombreCertificadoDigital>"+webClave+"</tem:nombreCertificadoDigital>\n"
					+ "		    	</tem:CaptudaGuiaSalida>\n"
					+ "			</soapenv:Body>\n"
					+ "		</soapenv:Envelope>\n";

			Guia.modificaPorCampo(con, db, "jsonGenerado", guia.getId(), data);

			String rs = ws.url(webUrl)
					.addHeader("Content-Type","text/xml")
					.addHeader("SOAPAction","http://tempuri.org/ICapturaDteExterno/CapturaGuiaSalida")
					.post(data)
					.thenApply(
			          (WSResponse response) -> {
			        	  String rsBody = "response.getBody() = \n"+response.getBody();
						  
						  Guia.modificaPorCampo(con, db, "response", guia.getId(), rsBody);
						  
			        	  if(rsBody.contains("<a:Descripcion>")) {
			        		  	int ini = rsBody.indexOf("<a:Descripcion>");
					       		int fin = rsBody.indexOf("</a:Descripcion>");
					       		rsBody = rsBody.substring(ini+15,fin);
					       		rsBody = rsBody.replaceAll("(\\n|\\r)", "");
							  if(!rsBody.contains("<a:Error>")) {
								  ini = rsBody.indexOf("<a:FolioDte>");
								  fin = rsBody.indexOf("</a:FolioDte>");
								  String folio = rsBody.substring(ini+12,fin);
								  rsBody += "DTE: " + folio;
								  
								  Guia.modificaPorCampo(con, db, "numGuiaCliente", guia.getId(), "DTE_GUIA: "+folio+" - "+guia.getNumGuiaCliente());
								  Guia.modificaPorCampo(con, db, "linkFolio", guia.getId(), folio);
								  
								  ini = rsBody.indexOf("<a:PdfenBase64>");
								  fin = rsBody.indexOf("</a:PdfenBase64>");
								  String pdfBase64 = rsBody.substring(ini+15,fin);
                                  try {
                                      File tmp = TempFile.createTempFile("tmp", ".pdf");
									  byte[] pdfBytes = Base64.getDecoder().decode(pdfBase64.trim());
									  Files.write(tmp.toPath(), pdfBytes);
									  String path = db+"/DTE_GUIA_"+folio + ".pdf";
									  Archivos.grabarArchivo(tmp,path);
                                  } catch (IOException e) {
                                      throw new RuntimeException(e);
                                  }

                              }
			        	  }
				       	  return rsBody;
			           }
			         ).toCompletableFuture().get(600000,TimeUnit.MILLISECONDS);
			return(rs);
		} catch (InterruptedException | java.util.concurrent.ExecutionException | TimeoutException | IOException e) {
			String className = ApiSapConconcreto.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
			return "FALLA: SE PRESENTARON ERRORES";
		}
    }

//**************************************************************
// CONSUME LOS SERVICIOS EN SOFTLAND DE ESCRITORIO FACTURA:
//**************************************************************

	public static String generaFactura(Connection con, String db, File cvs, WSClient ws, EmisorTributario emisorTributario, Proforma proforma) {
		try {
			byte[] csvBytes = Files.readAllBytes(cvs.toPath());
			String base64String = Base64.getEncoder().encodeToString(csvBytes);
			try {
				Files.delete(cvs.toPath());
			} catch (IOException e) {
				System.err.println("No se pudo eliminar el archivo temporal inmediatamente.");
			}
			String webUser = emisorTributario.getApiUser();
			String webClave = emisorTributario.getApiKey();
			String webUrl = emisorTributario.getApiUrl();
			String areaDeDatos = "\\\\Softland\\erp\\SOFTLAND\\DATOS\\ALZATEC\\";

			String data = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
					+ "		<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">\n"
					+ "			<soapenv:Header/>\n"
					+ "			<soapenv:Body>\n"
					+ "		    	<tem:CapturarDte>\n"
					+ "		      		<tem:base64File>\n"
					+ "		      			<UserName>webservice</UserName>\n"
					+ "		      			<Password>"+webClave+"</Password>\n"
					+ "		      			<Data>\n"
					+							base64String
					+ "						</Data>\n"
					+ "    			    </tem:base64File>\n"
					+ "    				<tem:extensionArchivo>txt</tem:extensionArchivo>\n"
					+ "    				<tem:areaDeDatos>"+areaDeDatos+"</tem:areaDeDatos>\n"
					+ "    				<tem:usuario>"+webUser+"</tem:usuario>\n"
					+ "    				<tem:nombreCertificadoDigital>"+webClave+"</tem:nombreCertificadoDigital>\n"
					+ "		    	</tem:CapturarDte>\n"
					+ "			</soapenv:Body>\n"
					+ "		</soapenv:Envelope>\n";

			Guia.modificaPorCampo(con, db, "jsonGenerado", proforma.getId(), data);

			String rs = ws.url(webUrl)
					.addHeader("Content-Type","text/xml")
					.addHeader("SOAPAction","http://tempuri.org/ICapturaDteExterno/CapturarDte")
					.post(data)
					.thenApply(
							(WSResponse response) -> {
								String rsBody = "response.getBody() = \n"+response.getBody();

								Guia.modificaPorCampo(con, db, "response", proforma.getId(), rsBody);

								if(rsBody.contains("<a:Descripcion>")) {
									int ini = rsBody.indexOf("<a:Descripcion>");
									int fin = rsBody.indexOf("</a:Descripcion>");
									rsBody = rsBody.substring(ini+15,fin);
									rsBody = rsBody.replaceAll("(\\n|\\r)", "");
									if(!rsBody.contains("<a:Error>")) {
										ini = rsBody.indexOf("<a:FolioDte>");
										fin = rsBody.indexOf("</a:FolioDte>");
										String nroFiscal = rsBody.substring(ini+12,fin);
										rsBody += "DTE: " + nroFiscal;

										Guia.modificaPorCampo(con, db, "nroFiscal", proforma.getId(), nroFiscal);

										ini = rsBody.indexOf("<a:PdfenBase64>");
										fin = rsBody.indexOf("</a:PdfenBase64>");
										String pdfBase64 = rsBody.substring(ini+15,fin);
										try {
											File tmp = TempFile.createTempFile("tmp", ".pdf");
											byte[] pdfBytes = Base64.getDecoder().decode(pdfBase64.trim());
											Files.write(tmp.toPath(), pdfBytes);
											String path = db+"/DTE_FACT_"+nroFiscal + ".pdf";
											Archivos.grabarArchivo(tmp,path);
										} catch (IOException e) {
											throw new RuntimeException(e);
										}
									}
								}
								return rsBody;
							}
					).toCompletableFuture().get(600000,TimeUnit.MILLISECONDS);
			return(rs);
		} catch (InterruptedException | java.util.concurrent.ExecutionException | TimeoutException | IOException e) {
			String className = ApiSapConconcreto.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
			return "FALLA: SE PRESENTARON ERRORES";
		}
	}


//**************************************************************
// FUNCIONES SOFTLAND DE ESCRITORIO FACTURA:
//**************************************************************
	
	public static File downGuia(String db, String folio) {
		String path = db+"/DTE_GUIA_"+ folio + ".pdf";
		InputStream tmp = Archivos.leerArchivo(path);
		File pdf = Archivos.parseInputStreamToFile(tmp);
		return(pdf);
	}

	public static File downFactura(String db, String nroFiscal) {
		String path = db+"/DTE_FACT_"+ nroFiscal + ".pdf";
		InputStream tmp = Archivos.leerArchivo(path);
		File pdf = Archivos.parseInputStreamToFile(tmp);
		return(pdf);
	}
	
	public static File generaCsvGuia (Connection con, String db, Guia guia, Transportista transportista, Map<String,String> mapDiccionario, EmisorTributario emisorTributario) {
		File tmp = null;
		try {
			tmp = TempFile.createTempFile("tmp", ".csv");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		List<String> lineasCsv = new ArrayList<>();
		lineasCsv.add("Tipo_de_Documento_de_Venta;Subtipo_del_Documento;Estado_del_Documento;Bodega_Origen_de_los_Productos;Numero_de_Documento;Fecha_de_Generacion_del_Documento;" +
				"Concepto_del_Documento;Fecha_de_Vencimiento_del_Documento;Observacion;Moneda_del_Documento;Equivalencia_de_la_Moneda_de_Venta_del_Documento;" +
				"NCredito_por_Devolucion_o_Refacturacion;Guia_de_Entrada_que_Referencia;Motivo_Anulacion;Genera_Guia_de_Entrada;Precio_Unitario_para_Guia_de_Entrada;" +
				"Nro_Documento_que_Referencia;Tipo_de_documento_que_referencia;Codigo_de_Cliente;Nombre_del_Cliente;Rut_del_Cliente;Giro_del_Cliente;Mail_DTE_del_Cliente;" +
				"Direccion_del_Cliente;N_Direccion_del_Cliente;Comuna_del_Cliente;Ciudad_del_Cliente;Lugar_del_Documento;Direccion_del_Documento;Codigo_Comuna_del_Documento;" +
				"Codigo_Ciudad_del_Documento;Codigo_Region_del_Documento;Codigo_Pais_del_Documento;Codigo_Lista_de_Precios;Codigo_de_Vendedor;Codigo_Centro_de_Costo;" +
				"Codigo_Condicion_de_Pago;Codigo_de_Distribuidor;Nombre_de_Distribuidor;Codigo_Lugar_de_Despacho;Retirado_Por;Patente_del_Camion_de_Despacho;Solicitado_Por;" +
				"Rut_Solicitante;Despachado_Por;Rut_Transportista;Cuenta_Contable_para_Notas_de_Debito;Porcentaje_de_Descuento_1;Valor_de_Descuento_1;Porcentaje_de_Descuento_2;" +
				"Valor_de_Descuento_2;Porcentaje_de_Descuento_3;Valor_de_Descuento_3;Porcentaje_de_Descuento_4;Valor_de_Descuento_4;Porcentaje_de_Descuento_5;Valor_de_Descuento_5;" +
				"Flete;Embalaje;Codigo_de_Canal_de_Ventas;Total_Final;Codigo_de_Producto;Codigo_Unidad_de_Medida;Detalle_del_Producto;Descripcion_del_Producto;Cantidad_Facturada;" +
				"Cantidad_Despachada;Precio_Unitario_Moneda_Base;Precio_Unitario_Moneda_de_Venta;Equivalencia_de_la_moneda_del_producto_vs_base;Porcentaje_de_Descuento_de_Linea_1;" +
				"Valor_de_Descuento_de_Linea_1;Porcentaje_de_Descuento_de_Linea_2;Valor_de_Descuento_de_Linea_2;Porcentaje_de_Descuento_de_Linea_3;Valor_de_Descuento_de_Linea_3;" +
				"Porcentaje_de_Descuento_de_Linea_4;Valor_de_Descuento_de_Linea_4;Porcentaje_de_Descuento_de_Linea_5;Valor_de_Descuento_de_Linea_5;" +
				"Valor_Total_Descuentos_de_Linea;Porcentaje_de_credito_Empresas_Constructoras;Valor_Total_de_Credito_Empresas_Constructoras;Partida_o_Talla;Pieza_o_Color;" +
				"Fecha_de_Vencimiento_de_la_Partida_o_Pieza;Serie;Numero_Nota_de_Venta;Identificador_Impresora_Fiscal;Conserva_Folio_Asignado_al_DTE;Referencia_1_Tipo_Documento_SII;" +
				"Referencia_1_Descripcion;Referencia_1_Numero_Documento;Referencia_1_Fecha_Documento;Referencia_1_Codigo_Referencia_Boleta;Referencia_1_Razon_Referencia;" +
				"Referencia_2_Tipo_Documento_SII;Referencia_2_Descripcion;Referencia_2_Numero_Documento;Referencia_2_Fecha_Documento;Referencia_2_Codigo_Referencia_Boleta;" +
				"Referencia_2_Razon_Referencia;Referencia_3_Tipo_Documento_SII;Referencia_3_Descripcion;Referencia_3_Numero_Documento;Referencia_3_Fecha_Documento;" +
				"Referencia_3_Codigo_Referencia_Boleta;Referencia_3_Razon_Referencia;Referencia_4_Tipo_Documento_SII;Referencia_4_Descripcion;Referencia_4_Numero_Documento;" +
				"Referencia_4_Fecha_Documento;Referencia_4_Codigo_Referencia_Boleta;Referencia_4_Razon_Referencia;Referencia_5_Tipo_Documento_SII;Referencia_5_Descripcion;" +
				"Referencia_5_Numero_Documento;Referencia_5_Fecha_Documento;Referencia_5_Codigo_Referencia_Boleta;Referencia_5_Razon_Referencia;Manejo_MaderaComuna_del_Rol_Predial_de_Origen;" +
				"Manejo_MaderaCodigo_Plan_de_Manejo_Conaf;Manejo_MaderaLatitud;Manejo_MaderaLongitud;Manejo_MaderaManzana_del_Rol_de_Origen;Manejo_MaderaSistema_de_Referencia;" +
				"Manejo_MaderaPredial_de_Rol_de_Origen;Tipo_de_Transaccion;Forma_Pago_SII;ExportacionIndicador_de_Servicio;ExportacionForma_de_Pago_Exportacion;" +
				"ExportacionIdAdicional_Receptor_Extranjero;ExportacionRut_Chofer;ExportacionNombre_Chofer;ExportacionModalidad_de_Venta;ExportacionClausula_de_Exportacion;" +
				"ExportacionTotal_Clausula_de_Ventas_Exportacion;ExportacionRecargo_sTotal_Clausula_de_Venta;ExportacionVia_de_Transporte;ExportacionIddel_Medio_de_Transporte;" +
				"ExportacionRut_Compania_Transportista;ExportacionNombre_Compania_Transportadora;ExportacionIdAdicional_Cia_Transportadora;ExportacionBooking;ExportacionMotoNave;" +
				"ExportacionCodigo_de_Operador;ExportacionPuerto_de_Embarque;ExportacionIdAdicional_Puerto_de_Embarque;ExportacionPuerto_de_Desembarque;" +
				"ExportacionIdAdicional_Puerto_de_Desembarque;ExportacionTara;ExportacionUnidad_de_Medida_Tara;ExportacionTotal_Peso_Bruto;ExportacionUnidad_Peso_Bruto;" +
				"ExportacionTotal_Peso_Neto;ExportacionUnidad_Peso_Neto;ExportacionTotal_Items;ExportacionTotal_Bultos;ExportacionTipo_de_Bulto;ExportacionMarca;ExportacionId_Container;" +
				"ExportacionSello;ExportacionEmisor_Sello;ExportacionFlete;ExportacionSeguro;ExportacionCodigo_Pais_Receptor;ExportacionTipo_Moneda_Transaccion");
		try{
			//******************************
			// OBTIENE DATOS A LLENAR
			//******************************
			Long id_bodegaDestino = guia.getId_bodegaDestino();
			BodegaEmpresa bodegaDestino = BodegaEmpresa.findXIdBodega(con, db,id_bodegaDestino);
			Cliente cliente = Cliente.find(con, db, bodegaDestino.getId_cliente());
			List<List<String>> detalleGuia = new ArrayList<List<String>>();
			String direccionDestino="", comunaDestino="", ciudadDestino="", rutCliente="", nombCliente="", patTransp="", rutTransp="", 
					nomTransp="", giroCliente = "", mailCliente = "";
			if(cliente != null) {
				rutCliente = cliente.getRut().replace(".", "").replace(".", "").replace(".", "").replace(".", "");
				rutCliente = rutCliente.replace(",", "").replace(",", "").replace(",", "").replace(",", "");
				rutCliente = rutCliente.trim();
				nombCliente = cliente.getNombre();
				giroCliente = cliente.getGiro();
				mailCliente = cliente.getMailFactura();
			}
			if ((long) bodegaDestino.getEsInterna() == (long) 1) {
				Cliente aux = Cliente.find(con, db, bodegaDestino.getId_cliente());
				if(aux!=null) {
					direccionDestino = aux.getDireccion();
					comunaDestino = aux.getCod_comuna();
					Comunas comuna = Comunas.findPorNombre(con, db, aux.getComuna());
					if(comuna != null) {
						Long cod_ciudad = Long.parseLong(comuna.codigo); //se elimina el 0 antepuesto en comuna
						ciudadDestino = cod_ciudad.toString();
					}
				}
				detalleGuia = Guia.findDetalleGuiaOrigenDestinoYPrecios(con, db, guia.getId(), guia.getId_bodegaOrigen(), mapDiccionario.get("pais"), guia.getId_bodegaOrigen());
			}else {
				Proyecto aux = Proyecto.find(con, db, bodegaDestino.id_proyecto);
				if(aux!=null) {
					direccionDestino = aux.getDireccion();
					comunaDestino = aux.getCod_comuna();
					Comunas comuna = Comunas.findPorNombre(con, db, aux.getComuna());
					if(comuna != null) {
						Long cod_ciudad = Long.parseLong(comuna.codigo);
						ciudadDestino = cod_ciudad.toString();
					}
				}
				detalleGuia = Guia.findDetalleGuiaOrigenDestinoYPrecios(con, db, guia.getId(), guia.getId_bodegaDestino(), mapDiccionario.get("pais"), guia.getId_bodegaOrigen());
			}
			if(direccionDestino.length()>30) {
				direccionDestino = direccionDestino.substring(0,30);
			}
			if(transportista != null) {
				patTransp = transportista.getPatente();
				rutTransp = transportista.getRutConductor().replace(".", "").replace(".", "").replace(".", "").replace(".", "");
				rutTransp = rutTransp.replace(",", "").replace(",", "").replace(",", "").replace(",", "");
				rutTransp = rutTransp.trim();
				nomTransp = transportista.getConductor();
			}
			Fechas auxFecha = Fechas.obtenerFechaDesdeStrAAMMDD(Fechas.AAMMDD(guia.fecha));
			Map<Long,Double> tasaCambio = TasasCambio.mapTasasPorFecha(con, db, auxFecha.getFechaStrAAMMDD(), mapDiccionario.get("pais"));
			DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
			DecimalFormat myformat = new DecimalFormat("0",symbols);
			for(int i=0;i<detalleGuia.size();i++) {
				//obtiene datos
					String auxPrecio = detalleGuia.get(i).get(9); 				// precio de venta en moneda de origen
					if(auxPrecio==null || auxPrecio.trim().length()<=0)	{
						auxPrecio = "0";
					}
					Long precioUnitario = (long)0;
					Double auxPU = Double.parseDouble(auxPrecio.replaceAll(",", ""));
					String auxCantidad = detalleGuia.get(i).get(8);				// cantidad
					if(auxCantidad==null || auxCantidad.trim().length()<=0) {
						auxCantidad = "0";
					}
					Long id_moneda = Long.parseLong(detalleGuia.get(i).get(30));
					Double tasa = tasaCambio.get(id_moneda);
					if(tasa==null) {
						tasa = (double)1;
					}
					precioUnitario = Math.round(auxPU * tasa);		// precio de venta en moneda principal CLP
					Long cantidad = Math.round(Double.parseDouble(auxCantidad.replaceAll(",", "")));
					Long auxPTotal = Math.round(auxPU * tasa * cantidad);
					Long auxIvaTotal =  Math.round(auxPTotal * (emisorTributario.getTasaIva()/100));
				//fin obtiene datos
					String producto = detalleGuia.get(i).get(6);
					if(producto.length()>35) {
						producto = producto.substring(0,35);
					}
				int contador = 0;
				rutCliente = rutCliente.replaceAll("-","");
				String codCliente = rutCliente.substring(0,rutCliente.length()-1);
				String codigo = detalleGuia.get(i).get(5);
				String fechaGuia = Fechas.DDMMAA(guia.getFecha());
				lineasCsv.add("S;T;V;01;"+contador+";"+fechaGuia+";16;"+fechaGuia+";"+guia.getObservaciones()+";;;;;;;;;;" +
						codCliente+";"+nombCliente+";"+rutCliente+";"+giroCliente+";"+mailCliente+";"+direccionDestino+";;"+comunaDestino+";"+ciudadDestino+";;;;;;;;;" +
						"01-000;;;;;;;;;;;;;;;;;;;;;;;;001;"+myformat.format(auxIvaTotal + auxPTotal)+";" +
						codigo+";;"+producto+";"+producto+";"+myformat.format(cantidad)+";"+myformat.format(cantidad)+";"+myformat.format(precioUnitario)+";" +
						myformat.format(precioUnitario)+";;;;;;;;;;;;;;;;;;;;;N;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;");
			}
			if(lineasCsv.size()>0 && tmp!=null) {
				Files.write(tmp.toPath(), lineasCsv, StandardCharsets.UTF_8);
			}
			return(tmp);
		}catch(Exception e){
			String className = ApiSapConconcreto.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
			return null;
		}
	}
	
	
	public static File generaCsvFactura(Connection con, String db, List<List<String>> guiasPer, Cliente cliente, Proforma proforma, Map<String,List<List<String>>> mapReportPorGuia10, EmisorTributario emisorTributario) {
		File tmp = null;
		try {
			tmp = TempFile.createTempFile("tmp", ".csv");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		//******************************************************
     	//************       FALTA PROGRAMAR        ************
     	//******************************************************
		return(tmp);
	}
	
	
	
}
