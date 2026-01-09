package models.api;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import controllers.HomeController;
import models.tables.*;
import models.utilities.Archivos;
import models.utilities.Fechas;
import models.xml.XmlFacturaReferencias;
import org.apache.poi.util.TempFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	public static int ultimoFolioGuia(Connection con, String db) {
		int ultimoFolioGuia = 0;
		String query = String.format("select numero from %s.numeroFolioGuia;", db);
		try (PreparedStatement smt = con.prepareStatement(query);
			 ResultSet rs = smt.executeQuery()){
			if (rs.next()) {
				ultimoFolioGuia = rs.getInt(1);
			}
		}catch(Exception e){}
		return ultimoFolioGuia;
	}

	public static int ultimoNumeroFactura(Connection con, String db) {
		int ultimoNumeroFactura = 0;
		String query = String.format("select numero from %s.numeroDocumentoFactura;", db);
		try (PreparedStatement smt = con.prepareStatement(query);
			 ResultSet rs = smt.executeQuery()){
			if (rs.next()) {
				ultimoNumeroFactura = rs.getInt(1);
			}
		}catch(Exception e){}
		return ultimoNumeroFactura;
	}
	
	public static File generaCsvGuia(Connection con, String db, Guia guia, Transportista transportista, Map<String,String> mapDiccionario,
									  EmisorTributario emisorTributario, Map<String,String> mapCampos) {
		File tmp = null;
		try {
			tmp = TempFile.createTempFile("tmp", ".csv");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		List<String> lineasCsv = new ArrayList<>();
		lineasCsv.add("Bodega_Origen_de_los_Productos;Numero_de_Folio_de_la_Guia;Tipo_de_Documento;SubTipo_de_Documento;Fecha_de_generacion_de_la_Guia;" +
				"Concepto_de_Salida_a_Bodega;Observacion;Codigo_de_Cliente;Nombre_del_Cliente;Rut_del_Cliente;Giro_del_Cliente;Direccion_del_Cliente;" +
				"Comuna_del_Cliente;Ciudad_del_Cliente;Codigo_Centro_de_Costo;Codigo_de_Bodega_Destino_Trapaso_entre_Bodegas;Codigo_Lista_de_Precios;" +
				"Numero_Orden_de_Trabajo_Interna;Numero_Orden_de_Produccion_Interna;Numero_Orden_de_Compra_Interna;Numero_Factura_Asociada_Interna;" +
				"SubTipo_de_Factura_Asociada_Interna;Numero_Nota_de_Credito_Asociada_Interna;Codigo_Centro_de_Costo_para_Contabilizar;Codigo_de_Vendedor;" +
				"Codigo_Condicion_de_Pago;Codigo_Lugar_de_Despacho;Direccion_de_despacho;Comuna_de_despacho;Ciudad_de_despacho;Pais_de_despacho;" +
				"Atencion_despacho;Provincia_de_despacho;Region_de_despacho;Codigo_Postal_de_despacho;Codigo_GLN_despacho;Codigo_Vendedor_WALMART;" +
				"Retirado_Por;Patente_del_Camion_de_Despacho;Solicitado_Por;Rut_Transportista;Despachado_Por;Rut_Solicitante;Tipo_de_Despacho;" +
				"Lugar_del_Documento;Numero_Nota_de_Venta;Porcentaje_de_Descuento_1;Valor_de_Descuento_1;Porcentaje_de_Descuento_2;Valor_de_Descuento_2;" +
				"Porcentaje_de_Descuento_3;Valor_de_Descuento_3;Porcentaje_de_Descuento_4;Valor_de_Descuento_4;Porcentaje_de_Descuento_5;" +
				"Valor_de_Descuento_5;Flete;Embalaje;Total_Final;Codigo_de_Producto;Detalle_del_Producto;Descripcion_del_Producto;Codigo_Unidad_de_Medida;" +
				"Cantidad_Despachada;Precio_Unitario;Porcentaje_de_Descuento_de_Linea_1;Valor_de_Descuento_de_Linea_1;Porcentaje_de_Descuento_de_Linea_2;" +
				"Valor_de_Descuento_de_Linea_2;Porcentaje_de_Descuento_de_Linea_3;Valor_de_Descuento_de_Linea_3;Porcentaje_de_Descuento_de_Linea_4;" +
				"Valor_de_Descuento_de_Linea_4;Porcentaje_de_Descuento_de_Linea_5;Valor_de_Descuento_de_Linea_5;Valor_Total_Descuentos_de_Linea;" +
				"Partida_o_Talla;Pieza_o_Color;Fecha_de_Vencimiento;Serie;Cuenta_de_Consumo_del_Movimiento;Conserva_Folio_Asignado_al_DTE;" +
				"Referencia_1_Tipo_Documento_SII;Referencia_1_Descripcion;Referencia_1_Numero_Documento;Referencia_1_Fecha_Documento;" +
				"Referencia_1_Razon_Referencia;Referencia_2_Tipo_Documento_SII;Referencia_2_Descripcion;Referencia_2_Numero_Documento;" +
				"Referencia_2_Fecha_Documento;Referencia_2_Razon_Referencia;Referencia_3_Tipo_Documento_SII;Referencia_3_Descripcion;" +
				"Referencia_3_Numero_Documento;Referencia_3_Fecha_Documento;Referencia_3_Razon_Referencia;Referencia_4_Tipo_Documento_SII;" +
				"Referencia_4_Descripcion;Referencia_4_Numero_Documento;Referencia_4_Fecha_Documento;Referencia_4_Razon_Referencia;" +
				"Referencia_5_Tipo_Documento_SII;Referencia_5_Descripcion;Referencia_5_Numero_Documento;Referencia_5_Fecha_Documento;" +
				"Referencia_5_Razon_Referencia;Manejo_MaderaComuna_del_Rol_Predial_de_Origen;Manejo_MaderaCodigo_Plan_de_Manejo_Conaf;" +
				"Manejo_MaderaLatitud;Manejo_MaderaLongitud;Manejo_MaderaManzana_del_Rol_de_Origen;Manejo_MaderaSistema_de_Referencia;" +
				"Manejo_MaderaPredial_de_Rol_de_Origen;AduanaCodigo_de_Aduana;AduanaIndicador_de_Servicio;AduanaForma_de_Pago_Exportacion;" +
				"AduanaRut_Chofer;AduanaNombre_Chofer;AduanaModalidad_de_Venta;AduanaClausula_de_Exportacion;Aduana_Recargo_sobre_Total_Clausula_de_Venta;" +
				"AduanaTotal_Clausula_de_Ventas_Exportacion;AduanaVia_de_Transporte;AduanaIdentificacion_del_Medio_de_Transporte;AduanaRut_Compania_Transportista;" +
				"AduanaNombre_Compania_Transportadora;AduanaId_Adicional_Cia_Transportadora;AduanaBooking;AduanaMotoNave;AduanaCodigo_de_Operador;" +
				"AduanaPuerto_de_Embarque;AduanaIdentificador_Adicional_Puerto_de_Embarque;AduanaPuerto_de_Desembarque;AduanaIdAdicional_Puerto_de_Desembarque;" +
				"AduanaTara;AduanaUnidad_de_Medida_Tara;AduanaTotal_Peso_Bruto;AduanaUnidad_Peso_Bruto;AduanaTotal_Peso_Neto;AduanaUnidad_Peso_Neto;" +
				"AduanaTotal_Items;AduanaTotal_Bultos;AduanaTipo_de_Bulto;AduanaMarca;AduanaId_Container;AduanaSello;AduanaEmisor_Sello;AduanaFlete;AduanaSeguro;" +
				"AduanaCodigo_Pais_Receptor;AduanaTipo_Moneda_Transaccion;AduanaCodigo_Emisor_Traslado_Excepcional");
		try{
			//******************************
			// OBTIENE DATOS A LLENAR
			//******************************

			Long id_bodegaDestino = guia.getId_bodegaDestino();
			BodegaEmpresa bodegaDestino = BodegaEmpresa.findXIdBodega(con, db,id_bodegaDestino);
			Long id_bodegaOrigen = guia.getId_bodegaOrigen();
			BodegaEmpresa bodegaOrigen = BodegaEmpresa.findXIdBodega(con, db,id_bodegaOrigen);
			Cliente cliente = Cliente.find(con, db, bodegaDestino.getId_cliente());
			String direccionDestino="", comunaDestino="", ciudadDestino="", rutCliente="", nombCliente="", patTransp="", rutTransp="", 
					nomTransp="", giroCliente = "", mailCliente = "", direccionCliente = "", comunaCliente = "", ciudadCliente = "";
			if(cliente != null) {
				rutCliente = cliente.getRut().replace(".", "").replace(".", "").replace(".", "").replace(".", "");
				rutCliente = rutCliente.replace(",", "").replace(",", "").replace(",", "").replace(",", "");
				rutCliente = rutCliente.trim();
				nombCliente = cliente.getNombre();
				giroCliente = cliente.getGiro();
				mailCliente = cliente.getMailFactura();
			}
			List<List<String>> detalleGuia = new ArrayList<List<String>>();
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
					if((long) bodegaOrigen.getEsInterna() == (long) 1){
						direccionCliente = direccionDestino;
						comunaCliente = comunaDestino;
						ciudadCliente = ciudadDestino;
					}else{
						Cliente aux2 = Cliente.find(con, db, bodegaOrigen.getId_cliente());
						if(aux2!=null) {
							direccionCliente = aux2.getDireccion();
							comunaCliente = aux2.getCod_comuna();
							Comunas comuna2 = Comunas.findPorNombre(con, db, aux2.getComuna());
							if(comuna2 != null) {
								Long cod_ciudad = Long.parseLong(comuna2.codigo); //se elimina el 0 antepuesto en comuna
								ciudadCliente = cod_ciudad.toString();
							}
						}
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
			int ultimoFolio = WebSoftlandDesk.ultimoFolioGuia(con,db);
			int folioNew = ultimoFolio + 1; // tabla numeroFolioGuia
			String Concepto_de_Salida_a_Bodega = "";
			String Codigo_Condicion_de_Pago = "";
			String Bodega_Origen_de_los_Productos = "";
			String Codigo_Centro_de_Costo = "";
			if(mapCampos!=null && mapCampos.size()>0){
				Concepto_de_Salida_a_Bodega = mapCampos.get("Concepto_de_Salida_a_Bodega");
				Codigo_Condicion_de_Pago = mapCampos.get("Codigo_Condicion_de_Pago");
				Bodega_Origen_de_los_Productos = mapCampos.get("Bodega_Origen_de_los_Productos");
				Codigo_Centro_de_Costo = mapCampos.get("Codigo_Centro_de_Costo");
			}
			String aux = rutCliente.replaceAll("-","");
			String codCliente = rutCliente.substring(0,aux.length()-1);
			String fechaGuia = Fechas.DDMMAA(guia.getFecha());
			String observaciones = "MOV."+guia.getNumero()+": "+guia.getObservaciones();

			for(int i=0;i<detalleGuia.size();i++) {
				//obtiene datos
				String codigoEquipo = detalleGuia.get(i).get(5);
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
				String nombreEquipo = detalleGuia.get(i).get(6);
				if(nombreEquipo.length()>35) {
					nombreEquipo = nombreEquipo.substring(0,35);
				}
				lineasCsv.add(Bodega_Origen_de_los_Productos+";"+folioNew+";S;T;"+fechaGuia+";"+Concepto_de_Salida_a_Bodega+";"+observaciones+";"+codCliente+";"+nombCliente+";" +
						rutCliente+";"+giroCliente+";"+direccionCliente+";"+comunaCliente+";"+ciudadCliente+";"+Codigo_Centro_de_Costo+";;;;;;;;;;;" +
						Codigo_Condicion_de_Pago+";Independencia;"+direccionDestino+";"+comunaDestino+";"+ciudadDestino+";;;;;;;;"+nomTransp+";"+patTransp+";;"+rutTransp+";;;;;;;;;;;;;;;;;;"+
						auxPTotal+";"+codigoEquipo+";"+nombreEquipo+";"+nombreEquipo+";;"+cantidad+";"+precioUnitario+";;;;;;;;;;;;;;;;;N;" +
						";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;");
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
	
	
	public static File generaCsvFacturaArr(Connection con, String db,
										List<List<String>> resumenSubtotales, Cliente cliente, Proforma proforma,
										Map<String,String> mapPermiso, List<List<String>> detalleAjuste,
										XmlFacturaReferencias referencias, String comentarios, Map<String,String> mapCampos) {
		File tmp = null;
		try {
			tmp = TempFile.createTempFile("tmp", ".csv");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		List<String> lineasCsv = new ArrayList<>();
		lineasCsv.add("Tipo_de_Documento_de_Venta;Subtipo_del_Documento;Estado_del_Documento;Bodega_Origen_de_los_Productos;Numero_de_Documento;Fecha_de_Generacion_del_Documento;Concepto_del_Documento;" +
				"Fecha_de_Vencimiento_del_Documento;Observacion;Moneda_del_Documento;Equivalencia_de_la_Moneda_de_Venta_del_Documento;NCredito_por_Devolucion_o_Refacturacion;Guia_de_Entrada_que_Referencia;" +
				"Motivo_Anulacion;Genera_Guia_de_Entrada;Precio_Unitario_para_Guia_de_Entrada;Nro_Documento_que_Referencia;Tipo_de_documento_que_referencia;Codigo_de_Cliente;Nombre_del_Cliente;Rut_del_Cliente;" +
				"Giro_del_Cliente;Mail_DTE_del_Cliente;Direccion_del_Cliente;N_Direccion_del_Cliente;Comuna_del_Cliente;Ciudad_del_Cliente;Lugar_del_Documento;Direccion_del_Documento;Codigo_Comuna_del_Documento;" +
				"Codigo_Ciudad_del_Documento;Codigo_Region_del_Documento;Codigo_Pais_del_Documento;Codigo_Lista_de_Precios;Codigo_de_Vendedor;Codigo_Centro_de_Costo;Codigo_Condicion_de_Pago;Codigo_de_Distribuidor;" +
				"Nombre_de_Distribuidor;Codigo_Lugar_de_Despacho;Retirado_Por;Patente_del_Camion_de_Despacho;Solicitado_Por;Rut_Solicitante;Despachado_Por;Rut_Transportista;Cuenta_Contable_para_Notas_de_Debito;" +
				"Porcentaje_de_Descuento_1;Valor_de_Descuento_1;Porcentaje_de_Descuento_2;Valor_de_Descuento_2;Porcentaje_de_Descuento_3;Valor_de_Descuento_3;Porcentaje_de_Descuento_4;Valor_de_Descuento_4;" +
				"Porcentaje_de_Descuento_5;Valor_de_Descuento_5;Flete;Embalaje;Codigo_de_Canal_de_Ventas;Total_Final;Codigo_de_Producto;Codigo_Unidad_de_Medida;Detalle_del_Producto;Descripcion_del_Producto;" +
				"Cantidad_Facturada;Cantidad_Despachada;Precio_Unitario_Moneda_Base;Precio_Unitario_Moneda_de_Venta;Equivalencia_de_la_moneda_del_producto_vs_base;Porcentaje_de_Descuento_de_Linea_1;" +
				"Valor_de_Descuento_de_Linea_1;Porcentaje_de_Descuento_de_Linea_2;Valor_de_Descuento_de_Linea_2;Porcentaje_de_Descuento_de_Linea_3;Valor_de_Descuento_de_Linea_3;Porcentaje_de_Descuento_de_Linea_4;" +
				"Valor_de_Descuento_de_Linea_4;Porcentaje_de_Descuento_de_Linea_5;Valor_de_Descuento_de_Linea_5;Valor_Total_Descuentos_de_Linea;Porcentaje_de_credito_Empresas_Constructoras;" +
				"Valor_Total_de_Credito_Empresas_Constructoras;Partida_o_Talla;Pieza_o_Color;Fecha_de_Vencimiento_de_la_Partida_o_Pieza;Serie;Numero_Nota_de_Venta;Identificador_Impresora_Fiscal;" +
				"Conserva_Folio_Asignado_al_DTE;Referencia_1_Tipo_Documento_SII;Referencia_1_Descripcion;Referencia_1_Numero_Documento;Referencia_1_Fecha_Documento;Referencia_1_Codigo_Referencia_Boleta;" +
				"Referencia_1_Razon_Referencia;Referencia_2_Tipo_Documento_SII;Referencia_2_Descripcion;Referencia_2_Numero_Documento;Referencia_2_Fecha_Documento;Referencia_2_Codigo_Referencia_Boleta;Referencia_2_Razon_Referencia;" +
				"Referencia_3_Tipo_Documento_SII;Referencia_3_Descripcion;Referencia_3_Numero_Documento;Referencia_3_Fecha_Documento;Referencia_3_Codigo_Referencia_Boleta;Referencia_3_Razon_Referencia;Referencia_4_Tipo_Documento_SII;" +
				"Referencia_4_Descripcion;Referencia_4_Numero_Documento;Referencia_4_Fecha_Documento;Referencia_4_Codigo_Referencia_Boleta;Referencia_4_Razon_Referencia;Referencia_5_Tipo_Documento_SII;Referencia_5_Descripcion;" +
				"Referencia_5_Numero_Documento;Referencia_5_Fecha_Documento;Referencia_5_Codigo_Referencia_Boleta;Referencia_5_Razon_Referencia;Manejo_MaderaComuna_del_Rol_Predial_de_Origen;Manejo_MaderaCodigo_Plan_de_Manejo_Conaf;" +
				"Manejo_MaderaLatitud;Manejo_MaderaLongitud;Manejo_MaderaManzana_del_Rol_de_Origen;Manejo_MaderaSistema_de_Referencia;Manejo_MaderaPredial_de_Rol_de_Origen;Tipo_de_Transaccion;Forma_Pago_SII;ExportacionIndicador_de_Servicio;" +
				"ExportacionForma_de_Pago_Exportacion;ExportacionIdAdicional_Receptor_Extranjero;ExportacionRut_Chofer;ExportacionNombre_Chofer;ExportacionModalidad_de_Venta;ExportacionClausula_de_Exportacion;" +
				"ExportacionTotal_Clausula_de_Ventas_Exportacion;ExportacionRecargo_sTotal_Clausula_de_Venta;ExportacionVia_de_Transporte;ExportacionIddel_Medio_de_Transporte;ExportacionRut_Compania_Transportista;" +
				"ExportacionNombre_Compania_Transportadora;ExportacionIdAdicional_Cia_Transportadora;ExportacionBooking;ExportacionMotoNave;ExportacionCodigo_de_Operador;ExportacionPuerto_de_Embarque;" +
				"ExportacionIdAdicional_Puerto_de_Embarque;ExportacionPuerto_de_Desembarque;ExportacionIdAdicional_Puerto_de_Desembarque;ExportacionTara;ExportacionUnidad_de_Medida_Tara;ExportacionTotal_Peso_Bruto;" +
				"ExportacionUnidad_Peso_Bruto;ExportacionTotal_Peso_Neto;ExportacionUnidad_Peso_Neto;ExportacionTotal_Items;ExportacionTotal_Bultos;ExportacionTipo_de_Bulto;ExportacionMarca;ExportacionId_Container;" +
				"ExportacionSello;ExportacionEmisor_Sello;ExportacionFlete;ExportacionSeguro;ExportacionCodigo_Pais_Receptor;ExportacionTipo_Moneda_Transaccion");
		try {
			EmisorTributario emisorTributario = EmisorTributario.find(con, db);
			String idDocumento = "madaFactura_"+proforma.getId();
			String valPeriodoDesde = proforma.desde;
			String valPeriodoHasta = proforma.hasta;
			String direccionOrigen = emisorTributario.direccion;
			String comunaOrigen = emisorTributario.comuna;
			String ciudadOrigen = emisorTributario.ciudad;
			if(direccionOrigen.length()>70) direccionOrigen = direccionOrigen.substring(0, 69);
			if(comunaOrigen.length()>20) comunaOrigen = comunaOrigen.substring(0, 19);
			if(ciudadOrigen.length()>20) ciudadOrigen = ciudadOrigen.substring(0, 19);
			String rutCliente = cliente.getRut();
			rutCliente = rutCliente.replace(".", "").replace(".", "").replace(".", "").replace(".", "");
			rutCliente = rutCliente.replace(",", "").replace(",", "").replace(",", "").replace(",", "");
			String[] auxRut = rutCliente.split("-");
			if(auxRut.length == 2) {
				rutCliente = auxRut[0].trim()+"-"+auxRut[1].trim().toUpperCase();
			}
			String nomCliente = cliente.getNombre();
			String giroCliente = cliente.getGiro();
			String direccionCliente = cliente.getDireccion();
			String comunaCliente = cliente.getComuna();
			String ciudadCliente = cliente.getCiudad();
			if(nomCliente.length()>100) nomCliente = nomCliente.substring(0, 99);
			if(giroCliente.length()>40) giroCliente = giroCliente.substring(0, 39);
			if(direccionCliente.length()>70) direccionCliente = direccionCliente.substring(0, 69);
			if(comunaCliente.length()>20) comunaCliente = comunaCliente.substring(0, 19);
			if(ciudadCliente.length()>20) ciudadCliente = ciudadCliente.substring(0, 19);
			BodegaEmpresa bodegaEmpresa = BodegaEmpresa.findXIdBodega(con, db, proforma.id_bodegaEmpresa);
			String nombreBodegaProyecto = bodegaEmpresa.getNickProyecto();
			if(nombreBodegaProyecto.trim().equals("")) {
				nombreBodegaProyecto = bodegaEmpresa.getNombre();
			}
			if(nombreBodegaProyecto.length()>18) nombreBodegaProyecto = nombreBodegaProyecto.substring(0, 17);
			Long totalNeto = Math.round(proforma.getNeto());
			Long totalIva = Math.round(proforma.getIva());
			Long totalTotal = totalNeto+totalIva;
			Double tasaIvaArrAuxiliar = emisorTributario.getTasaIva();
			if(mapPermiso.get("parametro.ivaPorBodega")!=null && mapPermiso.get("parametro.ivaPorBodega").equals("1")) {
				if(bodegaEmpresa!=null) {
					if(bodegaEmpresa.getIvaBodega() > 0) {
						tasaIvaArrAuxiliar = bodegaEmpresa.getIvaBodega() * 100;
					}else {
						Sucursal sucursal = Sucursal.find(con, db, bodegaEmpresa.getId_sucursal().toString());
						if(sucursal!=null && sucursal.getIvaSucursal() > 0) {
							tasaIvaArrAuxiliar = sucursal.getIvaSucursal() * 100;
						}
					}

				}
			}

			int ultimoNumeroFactura = WebSoftlandDesk.ultimoNumeroFactura(con,db);
			int nroDocNew = ultimoNumeroFactura + 1; // tabla numeroDocumentoFactura
			String Tipo_de_Transaccion = "";
			String ObservacionesSoftland = "";
			String Codigo_Forma_Pago_SII = "";
			int Dias_Forma_Pago_SII = 0;
			String Bodega_Origen_de_los_Productos = "";
			String Codigo_Centro_de_Costo = "";
			String Codigo_Condicion_de_Pago = "";
			String Concepto_del_Documento = "";

			if(mapCampos!=null && mapCampos.size()>0){
				Tipo_de_Transaccion = mapCampos.get("Tipo_de_Transaccion");
				ObservacionesSoftland = mapCampos.get("ObservacionesSoftland");
				Codigo_Forma_Pago_SII = mapCampos.get("Codigo_Forma_Pago_SII");
				Dias_Forma_Pago_SII = Integer.parseInt(mapCampos.get("Dias_Forma_Pago_SII"));
				Bodega_Origen_de_los_Productos = mapCampos.get("Bodega_Origen_de_los_Productos");
				Codigo_Centro_de_Costo = mapCampos.get("Codigo_Centro_de_Costo");
				Codigo_Condicion_de_Pago = mapCampos.get("Codigo_Condicion_de_Pago");
				Concepto_del_Documento = mapCampos.get("Concepto_del_Documento");
			}
			String fechaFactura = Fechas.DDMMAA(proforma.getFecha());
			String observaciones = "PROF."+proforma.getId()+" PERIODO: desde "+valPeriodoDesde+" hasta "+valPeriodoHasta+
					" --- PROYECTO: "+nombreBodegaProyecto.toUpperCase();
			if(comentarios!=null && comentarios.trim().length()>1) {
				observaciones = observaciones + " --- COMENTARIOS: "+comentarios.toUpperCase();
			}
			if(observaciones.length()>100) {
				observaciones = observaciones.substring(0,99);
			}
			rutCliente = rutCliente.replaceAll("-","");
			String codCliente = rutCliente.substring(0,rutCliente.length()-1);

			Fechas venc = Fechas.obtenerFechaDesdeStrAAMMDD(proforma.getFecha());
			venc = Fechas.addDias(venc.getFechaCal(), Dias_Forma_Pago_SII);
			String fechaVencimiento = venc.getFechaStrDDMMAA();

			for(int i=0;i<resumenSubtotales.size();i++) {
				Long idGrupo = Grupo.findIdXnombre(con,db, resumenSubtotales.get(i).get(0));
				String valVlrCodigo = idGrupo.toString();
				String valNmbItem = resumenSubtotales.get(i).get(0);
				String valQtyItem = "1";
				String valUnmdItem = "GL";
				if(valVlrCodigo.length()>35) {
					valVlrCodigo = valVlrCodigo.substring(0,34);
				}
				if(valNmbItem.length()>80) {
					valNmbItem = valNmbItem.substring(0,79);
				}
				String auxPrecioArr = resumenSubtotales.get(i).get(1);
				if(auxPrecioArr.equals("")||auxPrecioArr.equals(" "))	auxPrecioArr = "0";
				Double precioArr = (double)0;
				String auxNum = auxPrecioArr.trim();
				if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
				precioArr = Double.parseDouble(auxNum.replaceAll(",", ""));
				String auxPrecioCfi = resumenSubtotales.get(i).get(3);
				if(auxPrecioCfi.equals("")||auxPrecioCfi.equals(" "))	auxPrecioCfi = "0";
				Double precioCfi = (double)0;
				auxNum = auxPrecioCfi.trim();
				if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
				precioCfi = Double.parseDouble(auxNum.replaceAll(",", ""));
				Long auxPrecioRound = Math.round(precioArr+precioCfi);
				String valPrcItem = auxPrecioRound.toString();
				String valMontoItem = auxPrecioRound.toString();

				if( !(valPrcItem.equals("0") && valMontoItem.equals("0")) ) {
					lineasCsv.add("F;T;V;"+Bodega_Origen_de_los_Productos+";"+nroDocNew+";"+fechaFactura+";"+Concepto_del_Documento+";"+fechaVencimiento+";"+ObservacionesSoftland+";;;;;;;;;;"+codCliente+";"+nomCliente+";" +
							rutCliente+";"+giroCliente+";"+cliente.getMailFactura()+";"+direccionCliente+";;" +
							comunaCliente+";"+ciudadCliente+";;;;;;;;;"+Codigo_Centro_de_Costo+";"+Codigo_Condicion_de_Pago+";;;;;;;;;;;;;;;;;;;;;;;001;"+valMontoItem+";"+valVlrCodigo+";"+valUnmdItem+";"+valNmbItem+";;"+
							valQtyItem+";"+valQtyItem+";"+valPrcItem+";"+valPrcItem+
							";;;;;;;;;;;;;;;;;;;;;N;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;");
				}
			}
			String xmlAjustes = "";
			for(int i=0; i<detalleAjuste.size(); i++){
				Double totAjuste = Double.parseDouble(detalleAjuste.get(i).get(1).replaceAll(",", ""));
				String valVlrCodigo = "AJUSTE"+(i+1) + "";
				String valNmbItem = "";
				String valQtyItem = "1";
				String valUnmdItem = "GL";
				Long auxPrecioRound = Math.round(totAjuste);
				if(auxPrecioRound < 0) {
					valNmbItem = "MENOS: "+detalleAjuste.get(i).get(0);
					auxPrecioRound = auxPrecioRound * -1;
				}else {
					valNmbItem = "MAS: "+detalleAjuste.get(i).get(0);
				}
				if(valVlrCodigo.length()>35) {
					valVlrCodigo = valVlrCodigo.substring(0,34);
				}
				if(valNmbItem.length()>80) {
					valNmbItem = valNmbItem.substring(0,79);
				}
				if(auxPrecioRound > 0) {
					String valPrcItem = auxPrecioRound.toString();
					String valMontoItem = auxPrecioRound.toString();

					lineasCsv.add("F;T;V;"+Bodega_Origen_de_los_Productos+";"+nroDocNew+";"+fechaFactura+";"+Concepto_del_Documento+";"+fechaVencimiento+";"+ObservacionesSoftland+";;;;;;;;;;"+codCliente+";"+nomCliente+";" +
							rutCliente+";"+giroCliente+";"+cliente.getMailFactura()+";"+direccionCliente+";;" +
							comunaCliente+";"+ciudadCliente+";;;;;;;;;"+Codigo_Centro_de_Costo+";"+Codigo_Condicion_de_Pago+";;;;;;;;;;;;;;;;;;;;;;;001;"+valMontoItem+";"+valVlrCodigo+";"+valUnmdItem+";"+valNmbItem+";;"+
							valQtyItem+";"+valQtyItem+";"+valPrcItem+";"+valPrcItem+
							";;;;;;;;;;;;;;;;;;;;;N;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;");
				}
			}
//			String xmlReferencias = "";
//			int cantRef = 0;
//			try {
//				cantRef = referencias.tpoDocRef.size();
//			}catch(Exception e) {}
//			Long item = (long)0;
//			Fechas hoy = Fechas.hoy();
//			for (int i = 0; i < cantRef; i++) {
//				item = i + (long)1;
//				String fechRef = referencias.fchRef.get(i);
//				if(fechRef.trim().equals("")) {
//					fechRef = hoy.getFechaStrAAMMDD();
//				}
//				String folioRef = referencias.folioRef.get(i);
//				String razonRef = referencias.razonRef.get(i);
//				if(folioRef.length()>18) {
//					folioRef = folioRef.substring(0,17);
//				}
//				if(folioRef.trim().equals("")) {
//					folioRef = "888";
//				}
//				if(razonRef.length()>90) {
//					razonRef = razonRef.substring(0,89);
//				}
//				xmlReferencias +=
//						"    <Referencia>\n"
//								+ "      <NroLinRef>"+item+"</NroLinRef>\n"
//								+ "      <TpoDocRef>"+referencias.tpoDocRef.get(i)+"</TpoDocRef>\n"
//								+ "      <FolioRef>"+folioRef+"</FolioRef>\n"
//								+ "      <FchRef>"+fechRef+"</FchRef>\n"
//								+ "      <RazonRef>"+razonRef+"</RazonRef>\n"
//								+ "    </Referencia>";
//			}

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


	public static File generaCsvFacturaVta(Connection con, String db,
										   String nEmpresa, List<List<String>> resumenSubtotales, Cliente cliente, Proforma proforma,
										   Map<String,String> mapPermiso, List<List<String>> detalleAjuste,
										   XmlFacturaReferencias referencias, String comentarios, Map<String,String> mapCampos) {
		File tmp = null;
		try {
			tmp = TempFile.createTempFile("tmp", ".csv");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		//*********************************
		// FALTA PPROGRAMAR
		//*********************************


		return tmp;
	}


	public static String mapVariablesGuiaSoftlandDesk(Connection con, String db){
		try {
			Map<String,List<List<String>>> map = new HashMap<String,List<List<String>>>();

			String query = "select "
					+ " codigo,"
					+ " nombre"
					+ " from `"+db+"`.codigoCondicionDePagoGuia; ";
			try (PreparedStatement smt = con.prepareStatement(query);
				 ResultSet rs = smt.executeQuery()) {
				List<List<String>> aux = new ArrayList<List<String>>();
				while (rs.next()) {
					List<String> aux2 = new ArrayList<String>();
					aux2.add(rs.getString(1));
					aux2.add(rs.getString(2));
					aux.add(aux2);
				}
				map.put("codigoCondicionDePagoGuia", aux);
			} catch (SQLException e) {}
			
			query = "select "
					+ " codigo,"
					+ " nombre"
					+ " from `"+db+"`.bodegaOrigenDeLosProductos; ";
			try (PreparedStatement smt = con.prepareStatement(query);
				 ResultSet rs = smt.executeQuery()) {
				List<List<String>> aux = new ArrayList<List<String>>();
				while (rs.next()) {
					List<String> aux2 = new ArrayList<String>();
					aux2.add(rs.getString(1));
					aux2.add(rs.getString(2));
					aux.add(aux2);
				}
				map.put("bodegaOrigenDeLosProductos", aux);
			} catch (SQLException e) {}

			query = "select "
					+ " codigo,"
					+ " nombre"
					+ " from `"+db+"`.conceptoDeSalidaABodega; ";
			try (PreparedStatement smt = con.prepareStatement(query);
				 ResultSet rs = smt.executeQuery()) {
				List<List<String>> aux = new ArrayList<List<String>>();
				while (rs.next()) {
					List<String> aux2 = new ArrayList<String>();
					aux2.add(rs.getString(1));
					aux2.add(rs.getString(2));
					aux.add(aux2);
				}
				map.put("conceptoDeSalidaABodega", aux);
			} catch (SQLException e) {}

			query = "select "
					+ " codigo,"
					+ " nombre"
					+ " from `"+db+"`.codigoCentroDeCosto; ";
			try (PreparedStatement smt = con.prepareStatement(query);
				 ResultSet rs = smt.executeQuery()) {
				List<List<String>> aux = new ArrayList<List<String>>();
				while (rs.next()) {
					List<String> aux2 = new ArrayList<String>();
					aux2.add(rs.getString(1));
					aux2.add(rs.getString(2));
					aux.add(aux2);
				}
				map.put("codigoCentroDeCosto", aux);
			} catch (SQLException e) {}
			
			ObjectMapper objectMapper = new ObjectMapper();
			String jsonString = objectMapper.writeValueAsString(map);
			return jsonString;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public static String mapVariablesFactSoftlandDesk(Connection con, String db){
		try {
			Map<String,List<List<String>>> map = new HashMap<String,List<List<String>>>();
			String query = "select "
					+ " codigo,"
					+ " nombre"
					+ " from `"+db+"`.tipoDeTransaccion; ";
			try (PreparedStatement smt = con.prepareStatement(query);
				 ResultSet rs = smt.executeQuery()) {
				List<List<String>> aux = new ArrayList<List<String>>();
				while (rs.next()) {
					List<String> aux2 = new ArrayList<String>();
					aux2.add(rs.getString(1));
					aux2.add(rs.getString(2));
					aux.add(aux2);
				}
				map.put("tipoDeTransaccion", aux);
			} catch (SQLException e) {}

			query = "select "
					+ " codigo,"
					+ " nombre"
					+ " from `"+db+"`.observacionesSoftland; ";
			try (PreparedStatement smt = con.prepareStatement(query);
				 ResultSet rs = smt.executeQuery()) {
				List<List<String>> aux = new ArrayList<List<String>>();
				while (rs.next()) {
					List<String> aux2 = new ArrayList<String>();
					aux2.add(rs.getString(1));
					aux2.add(rs.getString(2));
					aux.add(aux2);
				}
				map.put("observacionesSoftland", aux);
			} catch (SQLException e) {}

			query = "select "
					+ " codigo,"
					+ " nombre"
					+ " from `"+db+"`.formaPagoSii; ";
			try (PreparedStatement smt = con.prepareStatement(query);
				 ResultSet rs = smt.executeQuery()) {
				List<List<String>> aux = new ArrayList<List<String>>();
				while (rs.next()) {
					List<String> aux2 = new ArrayList<String>();
					aux2.add(rs.getString(1));
					aux2.add(rs.getString(2));
					aux.add(aux2);
				}
				map.put("formaPagoSii", aux);
			} catch (SQLException e) {}

			query = "select "
					+ " codigo,"
					+ " nombre"
					+ " from `"+db+"`.bodegaOrigenDeLosProductos; ";
			try (PreparedStatement smt = con.prepareStatement(query);
				 ResultSet rs = smt.executeQuery()) {
				List<List<String>> aux = new ArrayList<List<String>>();
				while (rs.next()) {
					List<String> aux2 = new ArrayList<String>();
					aux2.add(rs.getString(1));
					aux2.add(rs.getString(2));
					aux.add(aux2);
				}
				map.put("bodegaOrigenDeLosProductos", aux);
			} catch (SQLException e) {}

			query = "select "
					+ " codigo,"
					+ " nombre"
					+ " from `"+db+"`.codigoCentroDeCosto; ";
			try (PreparedStatement smt = con.prepareStatement(query);
				 ResultSet rs = smt.executeQuery()) {
				List<List<String>> aux = new ArrayList<List<String>>();
				while (rs.next()) {
					List<String> aux2 = new ArrayList<String>();
					aux2.add(rs.getString(1));
					aux2.add(rs.getString(2));
					aux.add(aux2);
				}
				map.put("codigoCentroDeCosto", aux);
			} catch (SQLException e) {}

			query = "select "
					+ " codigo,"
					+ " nombre,"
					+ " dias"
					+ " from `"+db+"`.codigoCondicionDePagoFact; ";
			try (PreparedStatement smt = con.prepareStatement(query);
				 ResultSet rs = smt.executeQuery()) {
				List<List<String>> aux = new ArrayList<List<String>>();
				while (rs.next()) {
					List<String> aux2 = new ArrayList<String>();
					aux2.add(rs.getString(1));
					aux2.add(rs.getString(2));
					aux2.add(rs.getString(3));
					aux.add(aux2);
				}
				map.put("codigoCondicionDePagoFact", aux);
			} catch (SQLException e) {}

			query = "select "
					+ " codigo,"
					+ " nombre"
					+ " from `"+db+"`.conceptoDelDocumento; ";
			try (PreparedStatement smt = con.prepareStatement(query);
				 ResultSet rs = smt.executeQuery()) {
				List<List<String>> aux = new ArrayList<List<String>>();
				while (rs.next()) {
					List<String> aux2 = new ArrayList<String>();
					aux2.add(rs.getString(1));
					aux2.add(rs.getString(2));
					aux.add(aux2);
				}
				map.put("conceptoDelDocumento", aux);
			} catch (SQLException e) {}

			ObjectMapper objectMapper = new ObjectMapper();
			String jsonString = objectMapper.writeValueAsString(map);
			
			return jsonString;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
}
