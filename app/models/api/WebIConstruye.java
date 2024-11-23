package models.api;


import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import models.tables.BodegaEmpresa;
import models.tables.Cliente;
import models.tables.EmisorTributario;
import models.tables.Grupo;
import models.tables.Guia;
import models.tables.Proforma;
import models.tables.Proyecto;
import models.tables.Sucursal;
import models.tables.TasasCambio;
import models.tables.Transportista;
import models.utilities.Fechas;
import models.xml.XmlFacturaReferencias;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;



public class WebIConstruye {

	public WebIConstruye() {
		super();
	}
	
	
//**************************************************************
// CONSUME LOS SERVICIOS EN WEB ICONSTRUYE
//**************************************************************
	
	public static String generaDte(Connection con, String db, String xml, WSClient ws, Long id_guia, Long id_proforma) {
		
		EmisorTributario emisor = EmisorTributario.find(con, db);
		
		String webUser = emisor.apiUser;
		String webRut = emisor.getRut();
		String webClave = emisor.apiKey;
		String webUrl = emisor.apiUrl;
		
		byte[] bytesXml = xml.getBytes(StandardCharsets.UTF_8); 
		String xmlEncoded = Base64.getEncoder().encodeToString(bytesXml);
		
		int tipoDocumento = 0;
		if((long) id_proforma > (long) 0) {
			tipoDocumento = 33;
		}else if((long) id_guia > (long) 0) {
			tipoDocumento = 52;
		}else {
			return "FALLA no reconoce tipo de documento";
		}
		
		String data = 
				  "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:pit=\"http://PITrusted.iconstruye.cl/\">\n"
				+ "			<soapenv:Header/>\n"
				+ "			<soapenv:Body>\n"
				+ "				<pit:Procesar>\n"
				+ "					<pit:cliente>"+webUser+"</pit:cliente>\n"
				+ "					<pit:password>"+webClave+"</pit:password>\n"
				+ "					<pit:data>\n"
				+ "					    <![CDATA[\n"
				+ "					       <Firmar xmlns='http://dteservice.iconstruye.cl/'>\n"
				+ "					          <xmlBase64>"+xmlEncoded+"</xmlBase64>\n"
				+ "					          <rutEmisor>"+webRut.substring(0,8)+"</rutEmisor>\n"
				+ "					          <rutFirma>"+webRut.substring(0,8)+"</rutFirma>\n"
				+ "					          <rutEnvia>"+webRut.substring(0,8)+"</rutEnvia>\n"
				+ "					          <applicationId>A478E031-A07F-4C9D-B672-8BEBC64B3281</applicationId>\n"
				+ "					          <tipoDocumento>"+tipoDocumento+"</tipoDocumento>\n"
				+ "					          <estadoDocumento>0</estadoDocumento>\n"
				+ "					       </Firmar>\n"
				+ "					    ]]>\n"
				+ "					</pit:data>\n"
				+ "				</pit:Procesar>\n"
				+ "			</soapenv:Body>\n"
				+ "</soapenv:Envelope>\n";
	        
		
		try {
			
			String rs = ws.url(webUrl)
					.addHeader("Content-Type","text/xml")
					.addHeader("SOAPAction","http://PITrusted.iconstruye.cl/IIntegraciones/Procesar")
					.post(data)
					.thenApply(
			          (WSResponse response) -> {
			        	  String rsBody = "response.getBody() = \n"+response.getBody();
			        	  
			        	  rsBody = rsBody.replaceAll("&lt;", "<");
				       	  rsBody = rsBody.replaceAll("&gt;", ">");
				       	  
				       	  return rsBody;
			           }
			         ).toCompletableFuture().get(600000,TimeUnit.MILLISECONDS);

			String rsBody = rs;
			
			if((long) id_proforma > (long) 0) {
				Proforma.updateResponse(con, db, id_proforma, rsBody);
			}else if((long) id_guia > (long) 0) {
				Guia.modificaPorCampo(con, db, "response", id_guia, rsBody);
			}else {
				return "FALLA no reconoce si es guia o factura";
			}
	       	
	       	int initipoMensaje = rsBody.indexOf("<b:TipoMensaje>") + 15;
	       	int fintipoMensaje= rsBody.indexOf("</b:TipoMensaje>");
	       	
	       	int iniMsg = rsBody.indexOf("<b:Mensaje>") + 11;
			int finMsg = rsBody.indexOf("</b:Mensaje>");
	       	
			
			String tipoMensaje = "";
			String msg = "";
			String folioStr = "";
			
			if(fintipoMensaje > 0 && finMsg > 0) {
				tipoMensaje = rsBody.substring(initipoMensaje,fintipoMensaje);
				msg = rsBody.substring(iniMsg,finMsg);
			} else {
				return ("ERROR: "+rsBody);
			}
			
			
			if(tipoMensaje.trim().equals("Info") && msg.trim().equals("Correcto")) {
				
				int inifolio = rsBody.indexOf("<Folio>") + 7;
		       	int finfolio = rsBody.indexOf("</Folio>");
		       	
		       	if(finfolio > 0) {
		       		folioStr = rsBody.substring(inifolio,finfolio);
		       		if((long) id_proforma > (long) 0) {
		       			if(folioStr.trim().equals("")) {
							folioStr = "0";
						}
		       			Proforma.updateNroFiscal(con, db, id_proforma, folioStr);
					}else if((long) id_guia > (long) 0) {
						if(folioStr.trim().equals("")) {
							folioStr = "0";
							Guia.modificaPorCampo(con, db, "linkFolio", id_guia, folioStr);
						} else {
							Guia guia = Guia.find(con, db, id_guia);
							Guia.modificaPorCampo(con, db, "linkFolio", id_guia, folioStr);
							Guia.modificaPorCampo(con, db, "numGuiaCliente", id_guia, "GUIA: "+folioStr+". "+guia.getNumGuiaCliente());
						}
					}else {
						return "ERROR: no se reconoce la respuesta";
					}
				}
				
			} else {
				return ("ERROR: "+tipoMensaje+" "+msg);
			}
			return("DTE generado con éxito, folio: "+folioStr);
			
		} catch (java.lang.InterruptedException | java.util.concurrent.ExecutionException | TimeoutException e) {
			e.printStackTrace();
			return "ERROR";
		}
		
	}
	

	

	
//**************************************************************
// GENERA XML PARA EMISION DE GUIAS
//**************************************************************
	public static String generaGuiaXMLGuias (Connection con, String db, Map<String,String> mapPermiso,
			Guia guia, Transportista transportista, Map<String,String> mapDiccionario, EmisorTributario emisorTributario) {
		
			Long id_bodegaDestino = guia.getId_bodegaDestino();
			BodegaEmpresa bodegaDestino = BodegaEmpresa.findXIdBodega(con, db,id_bodegaDestino);
			Cliente clienteDestino = Cliente.find(con, db, bodegaDestino.getId_cliente());
			
			Long id_bodegaOrigen = guia.getId_bodegaOrigen();
			BodegaEmpresa bodegaOrigen = BodegaEmpresa.findXIdBodega(con, db,id_bodegaOrigen);
			Cliente clienteOrigen = Cliente.find(con, db, bodegaOrigen.getId_cliente());
			
			String tipo = "TRASLADO";
			if(bodegaOrigen.getEsInterna() == (long)1 && bodegaDestino.getEsInterna() > 1) {
				tipo = "DESPACHO";
			}else if(bodegaOrigen.getEsInterna() > 1) {
				tipo = "DEVOLUCION";
			}
			
			if(bodegaOrigen.getEsInterna() > 1) {
				BodegaEmpresa aux = bodegaDestino;
				bodegaDestino = bodegaOrigen;
				bodegaOrigen = aux;
				Cliente aux1 = clienteDestino;
				clienteDestino = clienteOrigen;
				clienteOrigen = aux1;
				Long aux2 = guia.getId_bodegaDestino();
				guia.setId_bodegaDestino(guia.getId_bodegaOrigen());
				guia.setId_bodegaOrigen(aux2);
			}
			
			String observaciones = tipo + " - OBRA: "+bodegaDestino.getNombre()+" - Solo traslado, no constituye venta.";
			
			if(observaciones.length() > 100) {
				observaciones = observaciones.substring(0,99);
			}
			
			//Map<String,String> mapComunas = Comunas.mapAll(con, db);
			List<List<String>> detalleGuia = new ArrayList<List<String>>();
			String direccionDestino="", comunaDestino="", ciudadDestino="";
			if ((long) bodegaDestino.getEsInterna() == (long) 1) {
				Cliente aux = Cliente.find(con, db, bodegaDestino.getId_cliente());
				if(aux!=null) {
					direccionDestino = aux.getDireccion();
					comunaDestino = aux.getComuna();
					ciudadDestino = aux.getCiudad();
				}else {
					return ("FALTAN DATOS DEL CLIENTE");
				}
				detalleGuia = Guia.findDetalleGuiaOrigenDestinoYPrecios(con, db, guia.getId(), guia.getId_bodegaOrigen(), mapDiccionario.get("pais"), guia.getId_bodegaOrigen());
			}else {
				Proyecto aux = Proyecto.find(con, db, bodegaDestino.id_proyecto);
				if(aux!=null) {
					direccionDestino = aux.getDireccion();
					comunaDestino = aux.getComuna();
					ciudadDestino = aux.getCiudad();
				}else {
					return ("FALTAN DATOS DEL PROYECTO");
				}
				detalleGuia = Guia.findDetalleGuiaOrigenDestinoYPrecios(con, db, guia.getId(), guia.getId_bodegaDestino(), mapDiccionario.get("pais"), guia.getId_bodegaOrigen());
			}
			if(direccionDestino.length()>30) {
				direccionDestino = direccionDestino.substring(0,29);
			}
			
			
			
			
			String direccionCliente="", comunaCliente="", ciudadCliente="", rutCliente="", nomCliente="", 
					giroCliente="", patTransp="", rutTransp="", nomTransp="";
			if(clienteDestino != null) {
				rutCliente = clienteDestino.getRut().replace(".", "").replace(".", "").replace(".", "").replace(".", "");
				rutCliente = rutCliente.replace(",", "").replace(",", "").replace(",", "").replace(",", "");
				String[] auxRut = rutCliente.split("-");
				if(auxRut.length == 2) {
					rutCliente = auxRut[0].trim()+"-"+auxRut[1].trim();
				}
				nomCliente = clienteDestino.getNombre();
				giroCliente = clienteDestino.getGiro();
				direccionCliente = clienteDestino.getDireccion();
				comunaCliente = clienteDestino.getComuna();
				ciudadCliente = clienteDestino.getCiudad();
				if(ciudadCliente == null || ciudadCliente.trim().equals("")) {
					
				}
				if(nomCliente.length()>100) nomCliente = nomCliente.substring(0, 99);
				if(giroCliente.length()>40) giroCliente = giroCliente.substring(0, 39);
				if(direccionCliente.length()>70) direccionCliente = direccionCliente.substring(0, 69);
				if(comunaCliente.length()>20) comunaCliente = comunaCliente.substring(0, 19);
				if(ciudadCliente.length()>20) ciudadCliente = ciudadCliente.substring(0, 19);
				
			}else {
				return ("FALTAN DATOS DEL CLIENTE");
			}
			
			String direccionOrigen="", comunaOrigen="", ciudadOrigen="";
			if(clienteOrigen != null) {
				direccionOrigen = clienteOrigen.getDireccion();
				comunaOrigen = clienteOrigen.getComuna();
				ciudadOrigen = clienteOrigen.getCiudad();
			}else {
				return ("FALTAN DATOS DEL PROPIETARIO");
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
			
			Long totalNeto = (long)0;
			Long totalIva = (long)0;
			Long totalTotal = (long)0;
			String xmlDetalle = "";
			
			for(int i=0;i<detalleGuia.size();i++) {
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
				
				if(precioUnitario <= 0) {
					precioUnitario = (long)1;
				}
				
				Long cantidad = Math.round(Double.parseDouble(auxCantidad.replaceAll(",", "")));
				Long auxPTotal = Math.round((double)precioUnitario * cantidad);
				
				Long auxIvaTotal =  Math.round(auxPTotal * (emisorTributario.getTasaIva()/100));
				
				totalNeto += auxPTotal;
				totalIva += auxIvaTotal;
				totalTotal += totalNeto + totalIva;
				int nroLinea = i + 1;
				
				String producto = detalleGuia.get(i).get(6);
				String codigo = detalleGuia.get(i).get(5);
				String unidad =  detalleGuia.get(i).get(7);
				
				if(codigo.length()>35) {
					codigo = codigo.substring(0,34);
				}
				if(producto.length()>80) {
					producto = producto.substring(0,79);
				}
				
				Double kg = Double.parseDouble(detalleGuia.get(i).get(36));
				Double auxTotalKG =(double) Math.round(kg*cantidad*100)/100;
				if(auxTotalKG==0) auxTotalKG=0.01;
				
				xmlDetalle +=  "     <Detalle>\n"
							+ "         <NroLinDet>"+nroLinea+"</NroLinDet>\n"
							+ "         <CdgItem>\n"
							+ "             <TpoCodigo>INT</TpoCodigo>\n"
							+ "             <VlrCodigo>"+codigo+"</VlrCodigo>\n"
							+ "         </CdgItem>\n"
							+ "         <NmbItem>"+producto+"</NmbItem>\n"
							+ "         <DscItem/>\n"
							+ "         <QtyRef>"+auxTotalKG+"</QtyRef>\n"
							+ "         <UnmdRef>KG</UnmdRef>\n"
							+ "         <QtyItem>"+cantidad+"</QtyItem>\n"
							+ "         <UnmdItem>"+unidad+"</UnmdItem>\n"
							+ "         <PrcItem>"+precioUnitario+"</PrcItem>\n"
							+ "         <MontoItem>"+auxPTotal+"</MontoItem>\n"
							+ "     </Detalle>\n";
				
				
				
			}
			
			Double tasaIvaArrAuxiliar = emisorTributario.getTasaIva();
		     if(mapPermiso.get("parametro.ivaPorBodega")!=null && mapPermiso.get("parametro.ivaPorBodega").equals("1")) {
		        	if(bodegaDestino!=null) {
		        		if(bodegaDestino.getIvaBodega() > 0) {
		        			tasaIvaArrAuxiliar = bodegaDestino.getIvaBodega() * 100;
		        		}else {
		        			Sucursal sucursal = Sucursal.find(con, db, bodegaDestino.getId_sucursal().toString());
		        			if(sucursal!=null && sucursal.getIvaSucursal() > 0) {
		        				tasaIvaArrAuxiliar = sucursal.getIvaSucursal() * 100;
		        			}
		        		}
		        		
		        	}
		      }
		

		String[] auxFechGuia = Fechas.AAMMDD(guia.getFecha()).split("-");
		String fechaGuia = auxFechGuia[0].trim()+"-"+auxFechGuia[1].trim()+"-"+auxFechGuia[2].trim();
		
			String idDocumento = "madaGuia_"+guia.getId();
		
			String xmlCabecera = "<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>\n"
					+ "<DTE version=\"1.0\"\n"
					+ "	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
					+ "	xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"\n"
					+ "	xmlns=\"http://www.sii.cl/SiiDte\">\n"
					+ "	<Documento ID=\""+idDocumento+"\"\n"
					//+ "	<Documento ID=\"R77363473-4T52F177\"\n"
					+ "		xmlns=\"http://www.sii.cl/SiiDte\">\n"
					+ "		<Encabezado>\n"
					+ "			<IdDoc>\n"
					+ "				<TipoDTE>52</TipoDTE>\n"
					+ "				<Folio/>\n"
					+ "				<FchEmis>"+fechaGuia+"</FchEmis>\n"
					+ "				<IndTraslado>6</IndTraslado>\n"
					+ "				<TermPagoGlosa>"+observaciones+"</TermPagoGlosa>\n"
					+ "			</IdDoc>\n"
					+ "			<Emisor>\n"
					+ "				<RUTEmisor>"+emisorTributario.getRut()+"</RUTEmisor>\n"
					+ "				<RznSoc>"+emisorTributario.getRazonSocial()+"</RznSoc>\n"
					+ "				<GiroEmis>"+emisorTributario.getGiro()+"</GiroEmis>\n"
					+ "				<Acteco>"+emisorTributario.getActividadEconomica()+"</Acteco>\n"
					+ "				<DirOrigen>"+direccionOrigen+"</DirOrigen>\n"
					+ "				<CmnaOrigen>"+comunaOrigen+"</CmnaOrigen>\n"
					+ "				<CiudadOrigen>"+ciudadOrigen+"</CiudadOrigen>\n"
					+ "			</Emisor>\n"
					+ "			<Receptor>\n"
					+ "				<RUTRecep>"+rutCliente+"</RUTRecep>\n"
					+ "				<CdgIntRecep>"+rutCliente.substring(0,rutCliente.length()-2)+"</CdgIntRecep>\n"
					+ "				<RznSocRecep>"+nomCliente+"</RznSocRecep>\n"
					+ "				<GiroRecep>"+giroCliente+"</GiroRecep>\n"
					+ "				<DirRecep>"+direccionCliente+"</DirRecep>\n"
					+ "				<CmnaRecep>"+comunaCliente+"</CmnaRecep>\n"
					+ "				<CiudadRecep>"+ciudadCliente+"</CiudadRecep>\n"
					+ "			</Receptor>\n";
				if(rutTransp.equals("")) {
					xmlCabecera +="			<Transporte/>\n";
				}else {
					xmlCabecera +="			<Transporte>\n"
								+ "				<Patente>"+patTransp+"</Patente>\n"
								+ "				<Chofer>\n"
								+ "					<RUTChofer>"+rutTransp+"</RUTChofer>\n"
								+ "					<NombreChofer>"+nomTransp+"</NombreChofer>\n"
								+ "				</Chofer>\n"
								+ "				<DirDest>"+direccionDestino+"</DirDest>\n"
								+ "				<CmnaDest>"+comunaDestino+"</CmnaDest>\n"
								+ "				<CiudadDest>"+ciudadDestino+"</CiudadDest>\n"
								+ "			</Transporte>\n";
				}
				xmlCabecera +="			<Totales>\n"
							+ "				<MntNeto>"+totalNeto+"</MntNeto>\n"
							+ "				<MntExe>0</MntExe>\n"
							+ "				<TasaIVA>"+tasaIvaArrAuxiliar+"</TasaIVA>\n"
							+ "				<IVA>"+totalIva+"</IVA>\n"
							+ "				<MntTotal>"+totalTotal+"</MntTotal>\n"
							+ "			</Totales>\n"
							+ "		</Encabezado>\n";
			 
			String xmlPie = "		<TmstFirma>2017-12-13T16:40:53.0000000-04:00</TmstFirma>\n"
					+ "	</Documento>\n"
					+ "</DTE>";
			
			String xml = xmlCabecera+xmlDetalle+xmlPie;
			
			
			
			
		return(xml);
		
	}
	
	
	//**************************************************************
	// GENERA XML PARA EMISION DE FACTURAS
	//**************************************************************
		public static String generaXMLFacturasArr (Connection con, String db, 
				String nEmpresa, List<List<String>> resumenSubtotales, Cliente cliente, Proforma proforma, 
				Map<String,String> mapPermiso, List<List<String>> detalleAjuste,
				XmlFacturaReferencias referencias, String comentarios) {
			
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
				rutCliente = auxRut[0].trim()+"-"+auxRut[1].trim();
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

			String emis = Fechas.AAMMDD(proforma.getFecha());
			String[] auxFechFact = emis.split("-");
			String fechaFactura = "";
			if(auxFechFact.length == 3) {
				fechaFactura = auxFechFact[0].trim()+"-"+auxFechFact[1].trim()+"-"+auxFechFact[2].trim();
			}
			
			Fechas venc = Fechas.obtenerFechaDesdeStrAAMMDD(emis);
			venc = Fechas.addDias(venc.getFechaCal(), cliente.diasVencPago);
			String[] auxFechVenc = venc.getFechaStrAAMMDD().split("-");
			String fechaVencimiento = "";
			if(auxFechVenc.length == 3) {
				fechaVencimiento = auxFechVenc[0].trim()+"-"+auxFechVenc[1].trim()+"-"+auxFechVenc[2].trim();
			}
			
			String observaciones = "PERIODO: desde "+valPeriodoDesde+" hasta "+valPeriodoHasta+
								   " --- PROYECTO: "+nombreBodegaProyecto.toUpperCase();
			if(comentarios.trim().length()>1) {
				observaciones = observaciones + " --- COMENTARIOS: "+comentarios.toUpperCase();
			}
			
			if(observaciones.length()>100) {
				observaciones = observaciones.substring(0,99);
			}
			
				String xmlCabecera = "<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>\n"
						+ "<DTE version=\"1.0\"\n"
						+ "	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
						+ "	xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"\n"
						+ "	xmlns=\"http://www.sii.cl/SiiDte\">\n"
						+ "	<Documento ID=\""+idDocumento+"\"\n"
						+ "		xmlns=\"http://www.sii.cl/SiiDte\">\n"
						+ "		<Encabezado>\n"
						+ "			<IdDoc>\n"
						+ "				<TipoDTE>33</TipoDTE>\n"
						+ "				<Folio/>\n"
						+ "				<FchEmis>"+fechaFactura+"</FchEmis>\n"
						+ "				<PeriodoDesde>"+valPeriodoDesde+"</PeriodoDesde>\n"
						+ "				<PeriodoHasta>"+valPeriodoHasta+"</PeriodoHasta>\n"
						+ "				<TermPagoGlosa>"+observaciones+"</TermPagoGlosa>\n"
						//+ "             <FmaPago>1</FmaPago>\n"
						//+ "             <TermPagoGlosa>CONTADO</TermPagoGlosa>
						+ "             <FchVenc>"+fechaVencimiento+"</FchVenc>\n"
						+ "			</IdDoc>\n"
						+ "			<Emisor>\n"
						+ "				<RUTEmisor>"+emisorTributario.getRut()+"</RUTEmisor>\n"
						+ "				<RznSoc>"+emisorTributario.getRazonSocial()+"</RznSoc>\n"
						+ "				<GiroEmis>"+emisorTributario.getGiro()+"</GiroEmis>\n"
						+ "				<Acteco>"+emisorTributario.getActividadEconomica()+"</Acteco>\n"
						+ "				<DirOrigen>"+direccionOrigen+"</DirOrigen>\n"
						+ "				<CmnaOrigen>"+comunaOrigen+"</CmnaOrigen>\n"
						+ "				<CiudadOrigen>"+ciudadOrigen+"</CiudadOrigen>\n"
						+ "			</Emisor>\n"
						+ "			<Receptor>\n"
						+ "				<RUTRecep>"+rutCliente+"</RUTRecep>\n"
						+ "				<CdgIntRecep>"+rutCliente.substring(0,rutCliente.length()-2)+"</CdgIntRecep>\n"
						+ "				<RznSocRecep>"+nomCliente+"</RznSocRecep>\n"
						+ "				<GiroRecep>"+giroCliente+"</GiroRecep>\n"
						+ "				<DirRecep>"+direccionCliente+"</DirRecep>\n"
						+ "				<CmnaRecep>"+comunaCliente+"</CmnaRecep>\n"
						+ "				<CiudadRecep>"+ciudadCliente+"</CiudadRecep>\n"
						+ "			</Receptor>\n"

						+ "			<Totales>\n"
						+ "				<MntNeto>"+totalNeto+"</MntNeto>\n"
						+ "				<MntExe>0</MntExe>\n"
						+ "				<TasaIVA>"+tasaIvaArrAuxiliar+"</TasaIVA>\n"
						+ "				<IVA>"+totalIva+"</IVA>\n"
						+ "				<MntTotal>"+totalTotal+"</MntTotal>\n"
						+ "			</Totales>\n"
						+ "		</Encabezado>\n";
				 
				String xmlDetalle = "";
				Long linea = (long) 0;
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
						linea = i+(long)1;
						xmlDetalle += "     <Detalle>\n"
								+ "         <NroLinDet>"+linea+"</NroLinDet>\n"
								+ "         <CdgItem>\n"
								+ "             <TpoCodigo>INT</TpoCodigo>\n"
								+ "             <VlrCodigo>"+valVlrCodigo+"</VlrCodigo>\n"
								+ "         </CdgItem>\n"
								+ "         <NmbItem>"+valNmbItem+"</NmbItem>\n"
								+ "         <QtyItem>"+valQtyItem+"</QtyItem>\n"
								+ "         <UnmdItem>"+valUnmdItem+"</UnmdItem>\n"
								+ "         <PrcItem>"+valPrcItem+"</PrcItem>\n"
								+ "         <MontoItem>"+valMontoItem+"</MontoItem>\n"
								+ "     </Detalle>\n";
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
	    					linea = linea + (long) 1;
	    					String valPrcItem = auxPrecioRound.toString();
							String valMontoItem = auxPrecioRound.toString();
							xmlAjustes += "     <Detalle>\n"
										+ "         <NroLinDet>"+linea+"</NroLinDet>\n"
										+ "         <CdgItem>\n"
										+ "             <TpoCodigo>INT</TpoCodigo>\n"
										+ "             <VlrCodigo>"+valVlrCodigo+"</VlrCodigo>\n"
										+ "         </CdgItem>\n"
										+ "         <NmbItem>"+valNmbItem+"</NmbItem>\n"
										+ "         <QtyItem>"+valQtyItem+"</QtyItem>\n"
										+ "         <UnmdItem>"+valUnmdItem+"</UnmdItem>\n"
										+ "         <PrcItem>"+valPrcItem+"</PrcItem>\n"
										+ "         <MontoItem>"+valMontoItem+"</MontoItem>\n"
										+ "     </Detalle>\n";
	    				}
				}
				
				
				String xmlReferencias = "";
				int cantRef = 0;
				try {
					cantRef = referencias.tpoDocRef.size();
				}catch(Exception e) {}
				Long item = (long)0;
				Fechas hoy = Fechas.hoy();
				for (int i = 0; i < cantRef; i++) {
					item = i + (long)1;
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
						folioRef = "888";
					}
					if(razonRef.length()>90) {
						razonRef = razonRef.substring(0,89);
					}
					
					xmlReferencias +=  
							    "    <Referencia>\n"
							  + "      <NroLinRef>"+item+"</NroLinRef>\n"
							  + "      <TpoDocRef>"+referencias.tpoDocRef.get(i)+"</TpoDocRef>\n"
							  + "      <FolioRef>"+folioRef+"</FolioRef>\n"
							  + "      <FchRef>"+fechRef+"</FchRef>\n"
							  + "      <RazonRef>"+razonRef+"</RazonRef>\n"
							  + "    </Referencia>";
				}
				
				String xmlPie = "		<TmstFirma>2017-12-13T16:40:53.0000000-04:00</TmstFirma>\n"
						+ "	</Documento>\n"
						+ "</DTE>";
				
				String xml = xmlCabecera+xmlDetalle+xmlAjustes+xmlReferencias+xmlPie;
				
			return(xml);
		}
		
		public static String generaXMLFacturasVta (Connection con, String db, 
				String nEmpresa, List<List<String>> guiasPer, Cliente cliente, Proforma proforma, 
				Map<String, List<List<String>>> mapReportPorGuia10, Map<String,String> mapPermiso, List<List<String>> detalleAjuste,
				XmlFacturaReferencias referencias, String comentarios) {
			
			EmisorTributario emisorTributario = EmisorTributario.find(con, db);
			
			String idDocumento = "madaFactura_"+proforma.getId();
			
			String emis = Fechas.AAMMDD(proforma.getFecha());
			String[] auxFechFact = emis.split("-");
			String fechaFactura = "";
			if(auxFechFact.length == 3) {
				fechaFactura = auxFechFact[0].trim()+"-"+auxFechFact[1].trim()+"-"+auxFechFact[2].trim();
			}
			
			String valPeriodoDesde = proforma.desde;
			String valPeriodoHasta = proforma.hasta;
			
			Fechas venc = Fechas.obtenerFechaDesdeStrAAMMDD(emis);
			venc = Fechas.addDias(venc.getFechaCal(), cliente.diasVencPago);
			String[] auxFechVenc = venc.getFechaStrAAMMDD().split("-");
			String fechaVencimiento = "";
			if(auxFechVenc.length == 3) {
				fechaVencimiento = auxFechVenc[0].trim()+"-"+auxFechVenc[1].trim()+"-"+auxFechVenc[2].trim();
			}
			
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
				rutCliente = auxRut[0].trim()+"-"+auxRut[1].trim();
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

			
			
			
			
			String observaciones = "VENTAS: desde "+valPeriodoDesde+" hasta "+valPeriodoHasta+
								   " --- PROYECTO: "+nombreBodegaProyecto.toUpperCase();
			
			if(comentarios.trim().length()>1) {
				observaciones = observaciones + " --- COMENTARIOS: "+comentarios.toUpperCase();
			}
			
			
				String xmlCabecera = "<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>\n"
						+ "<DTE version=\"1.0\"\n"
						+ "	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
						+ "	xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"\n"
						+ "	xmlns=\"http://www.sii.cl/SiiDte\">\n"
						+ "	<Documento ID=\""+idDocumento+"\"\n"
						+ "		xmlns=\"http://www.sii.cl/SiiDte\">\n"
						+ "		<Encabezado>\n"
						+ "			<IdDoc>\n"
						+ "				<TipoDTE>33</TipoDTE>\n"
						+ "				<Folio/>\n"
						+ "				<FchEmis>"+fechaFactura+"</FchEmis>\n"
						+ "				<PeriodoDesde>"+valPeriodoDesde+"</PeriodoDesde>\n"
						+ "				<PeriodoHasta>"+valPeriodoHasta+"</PeriodoHasta>\n"
						+ "				<TermPagoGlosa>"+observaciones+"</TermPagoGlosa>\n"
						//+ "             <FmaPago>1</FmaPago>\n"
						//+ "             <TermPagoGlosa>CONTADO</TermPagoGlosa>
						+ "             <FchVenc>"+fechaVencimiento+"</FchVenc>\n"
						+ "			</IdDoc>\n"
						+ "			<Emisor>\n"
						+ "				<RUTEmisor>"+emisorTributario.getRut()+"</RUTEmisor>\n"
						+ "				<RznSoc>"+emisorTributario.getRazonSocial()+"</RznSoc>\n"
						+ "				<GiroEmis>"+emisorTributario.getGiro()+"</GiroEmis>\n"
						+ "				<Acteco>"+emisorTributario.getActividadEconomica()+"</Acteco>\n"
						+ "				<DirOrigen>"+direccionOrigen+"</DirOrigen>\n"
						+ "				<CmnaOrigen>"+comunaOrigen+"</CmnaOrigen>\n"
						+ "				<CiudadOrigen>"+ciudadOrigen+"</CiudadOrigen>\n"
						+ "			</Emisor>\n"
						+ "			<Receptor>\n"
						+ "				<RUTRecep>"+rutCliente+"</RUTRecep>\n"
						+ "				<CdgIntRecep>"+rutCliente.substring(0,rutCliente.length()-2)+"</CdgIntRecep>\n"
						+ "				<RznSocRecep>"+nomCliente+"</RznSocRecep>\n"
						+ "				<GiroRecep>"+giroCliente+"</GiroRecep>\n"
						+ "				<DirRecep>"+direccionCliente+"</DirRecep>\n"
						+ "				<CmnaRecep>"+comunaCliente+"</CmnaRecep>\n"
						+ "				<CiudadRecep>"+ciudadCliente+"</CiudadRecep>\n"
						+ "			</Receptor>\n"

						+ "			<Totales>\n"
						+ "				<MntNeto>"+totalNeto+"</MntNeto>\n"
						+ "				<MntExe>0</MntExe>\n"
						+ "				<TasaIVA>"+tasaIvaArrAuxiliar+"</TasaIVA>\n"
						+ "				<IVA>"+totalIva+"</IVA>\n"
						+ "				<MntTotal>"+totalTotal+"</MntTotal>\n"
						+ "			</Totales>\n"
						+ "		</Encabezado>\n";
				 
				String xmlDetalle = "";
				Long linea = (long) 0;
				
				Double totalVenta = (double) 0;
				
				for(int i=0;i<guiasPer.size();i++) {
					List<List<String>> detalleAux = mapReportPorGuia10.get(guiasPer.get(i).get(8));
					
					if(detalleAux != null) {
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
						 	   			String valVlrCodigo = detalleAux.get(j).get(10);
										String valNmbItem = detalleAux.get(j).get(11);
										String valQtyItem = detalleAux.get(j).get(13);
										String valUnmdItem = detalleAux.get(j).get(12);
										if(valVlrCodigo.length()>35) {
											valVlrCodigo = valVlrCodigo.substring(0,34);
										}
										if(valNmbItem.length()>80) {
											valNmbItem = valNmbItem.substring(0,79);
										}
										
										String auxCantidad = detalleAux.get(j).get(13);
				    					if(auxCantidad.equals("")||auxCantidad.equals(" "))	auxCantidad = "0";
				    					Double cantidad = (double)0;
				    					auxNum = auxCantidad.trim();
					 	   				if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
					 	   				cantidad = Double.parseDouble(auxNum.replaceAll(",", ""));
					 	   				
										String auxPrecioArr = detalleAux.get(j).get(15);
					    					if(auxPrecioArr.equals("")||auxPrecioArr.equals(" "))	auxPrecioArr = "0";
					    					Double precioUnitario = (double)0;
					    					auxNum = auxPrecioArr.trim();
					    			   		if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
					    			   		precioUnitario = Double.parseDouble(auxNum.replaceAll(",", ""));
					    					
					    			   		Long auxPUnitario = Math.round(precioUnitario);
					    					Long auxPTotal = Math.round(precioUnitario * cantidad);
					    					
					    					auxPUnitario = Math.round(tot/cantidad);
					    					auxPTotal=Math.round(tot);
					    					
										String valPrcItem = auxPUnitario.toString();
										String valMontoItem = auxPTotal.toString();
										
										if( !(valPrcItem.equals("0") && valMontoItem.equals("0")) ) {
											linea = i+(long)1;
											xmlDetalle += "     <Detalle>\n"
													+ "         <NroLinDet>"+linea+"</NroLinDet>\n"
													+ "         <CdgItem>\n"
													+ "             <TpoCodigo>INT</TpoCodigo>\n"
													+ "             <VlrCodigo>"+valVlrCodigo+"</VlrCodigo>\n"
													+ "         </CdgItem>\n"
													+ "         <NmbItem>"+valNmbItem+"</NmbItem>\n"
													+ "         <QtyItem>"+valQtyItem+"</QtyItem>\n"
													+ "         <UnmdItem>"+valUnmdItem+"</UnmdItem>\n"
													+ "         <PrcItem>"+valPrcItem+"</PrcItem>\n"
													+ "         <MontoItem>"+valMontoItem+"</MontoItem>\n"
													+ "     </Detalle>\n";
										}
					 	   			}
		    					}
		    				}
					}
				}
					
				
				String xmlAjustes = "";
				
				for(int i=0; i<detalleAjuste.size(); i++){
					
						Double totAjuste = Double.parseDouble(detalleAjuste.get(i).get(2).replaceAll(",", ""));
						
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
	    					linea = linea + (long) 1;
	    					String valPrcItem = auxPrecioRound.toString();
							String valMontoItem = auxPrecioRound.toString();
							xmlAjustes += "     <Detalle>\n"
										+ "         <NroLinDet>"+linea+"</NroLinDet>\n"
										+ "         <CdgItem>\n"
										+ "             <TpoCodigo>INT</TpoCodigo>\n"
										+ "             <VlrCodigo>"+valVlrCodigo+"</VlrCodigo>\n"
										+ "         </CdgItem>\n"
										+ "         <NmbItem>"+valNmbItem+"</NmbItem>\n"
										+ "         <QtyItem>"+valQtyItem+"</QtyItem>\n"
										+ "         <UnmdItem>"+valUnmdItem+"</UnmdItem>\n"
										+ "         <PrcItem>"+valPrcItem+"</PrcItem>\n"
										+ "         <MontoItem>"+valMontoItem+"</MontoItem>\n"
										+ "     </Detalle>\n";
	    				}
						
				}
				
				
				String xmlReferencias = "";
				int cantRef = 0;
				try {
					cantRef = referencias.tpoDocRef.size();
				}catch(Exception e) {}
				Long item = (long)0;
				Fechas hoy = Fechas.hoy();
				for (int i = 0; i < cantRef; i++) {
					item = i + (long)1;
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
						folioRef = "888";
					}
					if(razonRef.length()>90) {
						razonRef = razonRef.substring(0,89);
					}
					
					xmlReferencias +=  
							    "    <Referencia>\n"
							  + "      <NroLinRef>"+item+"</NroLinRef>\n"
							  + "      <TpoDocRef>"+referencias.tpoDocRef.get(i)+"</TpoDocRef>\n"
							  + "      <FolioRef>"+folioRef+"</FolioRef>\n"
							  + "      <FchRef>"+fechRef+"</FchRef>\n"
							  + "      <RazonRef>"+razonRef+"</RazonRef>\n"
							  + "    </Referencia>";
				}
				
				String xmlPie = "		<TmstFirma>2017-12-13T16:40:53.0000000-04:00</TmstFirma>\n"
						+ "	</Documento>\n"
						+ "</DTE>";
				
				String xml = xmlCabecera+xmlDetalle+xmlAjustes+xmlReferencias+xmlPie;
				
			return(xml);
		}
	
	
	
	
}
