package models.xml;

import java.io.File;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.util.TempFile;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import models.reports.ReportFacturas;
import models.tables.BodegaEmpresa;
import models.tables.Cliente;
import models.tables.EmisorTributario;
import models.tables.Proforma;
import models.tables.Sucursal;
import models.utilities.Archivos;
import models.utilities.Fechas;

 
public class XmlFacturaVenta {


	public static String generaXmlVenta(Connection con, String db,String nEmpresa,
			List<List<String>> guiasPer, Cliente cliente, Proforma proforma, Map<String, List<List<String>>> mapReportPorGuia10, Map<String,String> mapPermiso) {
		
		EmisorTributario emisorTributario = EmisorTributario.find(con, db);
		
		String versionDte = "1.0";
		String valTipoDTE = "33";
		String valFolio = proforma.id.toString();
		
		String idDocumento = "R"+emisorTributario.rut+"T"+valTipoDTE+"F"+valFolio;
		
		String valFchEmis = proforma.fecha;
		
		String valPeriodoDesde = proforma.desde;
		String valPeriodoHasta = proforma.hasta;
		
		String valMedioPago = cliente.cod_medioPago;
		String valTipoCtaPago = cliente.cod_tipoCuenta;
		String valNumCtaPago = cliente.ctaPago;
		String valBcoPago = cliente.bcoPago;
		
		
		String valTermPagoCdg = cliente.cod_termPago;
		String valTermPagoGlosa = cliente.glosaPago;
		
		Fechas venc = Fechas.obtenerFechaDesdeStrAAMMDD(valFchEmis);
		venc = Fechas.addDias(venc.getFechaCal(), 30);
		String valFchVenc = venc.fechaStrAAMMDD;
		
		String valRUTEmisor = emisorTributario.rut;
		String valRznSoc = emisorTributario.razonSocial;
		String valGiroEmis = emisorTributario.giro;
		String valActeco = emisorTributario.actividadEconomica;
		String valDirOrigen = emisorTributario.direccion;
		String valCmnaOrigen = emisorTributario.comuna;
		String valCiudadOrigen = emisorTributario.ciudad;
		
		String valRUTRecep = cliente.rut;
		String valRznSocRecep = cliente.nombre;
		if(valRznSocRecep.length()>100) valRznSocRecep=valRznSocRecep.substring(0, 99);
		String valGiroRecep = cliente.giro;
		if(valGiroRecep.length()>40) valGiroRecep=valGiroRecep.substring(0, 39);
		String valDirRecep = cliente.direccion;
		if(valDirRecep.length()>70) valDirRecep=valDirRecep.substring(0, 69);
		String valCmnaRecep = cliente.comuna;
		if(valCmnaRecep.length()>20) valCmnaRecep=valCmnaRecep.substring(0, 19);
		String valCiudadRecep = cliente.ciudad;
		if(valCiudadRecep.length()>20) valCiudadRecep=valCiudadRecep.substring(0, 19);
		String valCorreoRecep = cliente.mailFactura;
		if(valCorreoRecep.length()>80) valCorreoRecep=valCorreoRecep.substring(0, 79);
		String valContactoRecep = cliente.contactoFactura;
		if(valContactoRecep.length()>80) valContactoRecep=valContactoRecep.substring(0, 79);
		
		BodegaEmpresa bodegaEmpresa = BodegaEmpresa.findXIdBodega(con, db, proforma.id_bodegaEmpresa);
		String nombreBodega = bodegaEmpresa.nombre;
		
		
		Long neto = Math.round(proforma.getNeto());
		Long iva = Math.round(proforma.getIva());
		Long total = neto+iva;
		String valMntNeto = neto.toString();
		String valMntExe = "0";
		
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
	     
		String valTasaIVA = tasaIvaArrAuxiliar.toString();
		
		
		String valIVA = iva.toString();
		String valMntTotal = total.toString();
		
		String valObs ="Periodo desde " + proforma.desde + " hasta " + proforma.hasta;
 
		String archivoXml = "";
		
	  try {
 
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		
		Document doc = docBuilder.newDocument();
		doc.setXmlStandalone(true);
			Element dte = doc.createElement("DTE");
			doc.appendChild(dte);
			Attr attr = doc.createAttribute("version");
			attr.setValue(versionDte);
			dte.setAttributeNode(attr);
				Element documento = doc.createElement("Documento");
				dte.appendChild(documento);
				attr = doc.createAttribute("ID");
				attr.setValue(idDocumento);
				documento.setAttributeNode(attr);
				
					Element encabezado = doc.createElement("Encabezado");
					documento.appendChild(encabezado);
					
						Element idDoc = doc.createElement("IdDoc");
						encabezado.appendChild(idDoc);
						
							Element tipoDte = doc.createElement("TipoDTE");
							tipoDte.appendChild(doc.createTextNode(valTipoDTE));
							idDoc.appendChild(tipoDte);
							
							Element folio = doc.createElement("Folio");
							folio.appendChild(doc.createTextNode(valFolio));
							idDoc.appendChild(folio);
							
							Element fchEmis = doc.createElement("FchEmis");
							fchEmis.appendChild(doc.createTextNode(valFchEmis));
							idDoc.appendChild(fchEmis);
							
							Element periodoDesde = doc.createElement("PeriodoDesde");
							periodoDesde.appendChild(doc.createTextNode(valPeriodoDesde));
							idDoc.appendChild(periodoDesde);
							
							Element periodoHasta = doc.createElement("PeriodoHasta");
							periodoHasta.appendChild(doc.createTextNode(valPeriodoHasta));
							idDoc.appendChild(periodoHasta);
							
							Element medioPago = doc.createElement("MedioPago");
							medioPago.appendChild(doc.createTextNode(valMedioPago));
							idDoc.appendChild(medioPago);
							
							Element tipoCtaPago = doc.createElement("TipoCtaPago");
							tipoCtaPago.appendChild(doc.createTextNode(valTipoCtaPago));
							idDoc.appendChild(tipoCtaPago);
							
							Element numCtaPago = doc.createElement("NumCtaPago");
							numCtaPago.appendChild(doc.createTextNode(valNumCtaPago));
							idDoc.appendChild(numCtaPago);
							
							Element bcoPago = doc.createElement("BcoPago");
							bcoPago.appendChild(doc.createTextNode(valBcoPago));
							idDoc.appendChild(bcoPago);
							
							Element termPagoCdg = doc.createElement("TermPagoCdg");
							termPagoCdg.appendChild(doc.createTextNode(valTermPagoCdg));
							idDoc.appendChild(termPagoCdg);
							
							Element termPagoGlosa = doc.createElement("TermPagoGlosa");
							termPagoGlosa.appendChild(doc.createTextNode(valTermPagoGlosa));
							idDoc.appendChild(termPagoGlosa);
							
							Element fchVenc = doc.createElement("FchVenc");
							fchVenc.appendChild(doc.createTextNode(valFchVenc));
							idDoc.appendChild(fchVenc);
							
						Element emisor = doc.createElement("Emisor");
						encabezado.appendChild(emisor);
						
							Element rutEmisor = doc.createElement("RUTEmisor");
							rutEmisor.appendChild(doc.createTextNode(valRUTEmisor));
							emisor.appendChild(rutEmisor);
							
							Element rznSoc = doc.createElement("RznSoc");
							rznSoc.appendChild(doc.createTextNode(valRznSoc));
							emisor.appendChild(rznSoc);
							
							Element giroEmis = doc.createElement("GiroEmis");
							giroEmis.appendChild(doc.createTextNode(valGiroEmis));
							emisor.appendChild(giroEmis);
							
							Element acteco = doc.createElement("Acteco");
							acteco.appendChild(doc.createTextNode(valActeco));
							emisor.appendChild(acteco);
							
							Element dirOrigen = doc.createElement("DirOrigen");
							dirOrigen.appendChild(doc.createTextNode(valDirOrigen));
							emisor.appendChild(dirOrigen);
							
							Element cmnaOrigen = doc.createElement("CmnaOrigen");
							cmnaOrigen.appendChild(doc.createTextNode(valCmnaOrigen));
							emisor.appendChild(cmnaOrigen);
							
							Element ciudadOrigen = doc.createElement("CiudadOrigen");
							ciudadOrigen.appendChild(doc.createTextNode(valCiudadOrigen));
							emisor.appendChild(ciudadOrigen);
							
						Element receptor = doc.createElement("Receptor");
						encabezado.appendChild(receptor);
						
							Element rutRecep = doc.createElement("RUTRecep");
							rutRecep.appendChild(doc.createTextNode(valRUTRecep));
							receptor.appendChild(rutRecep);
							
							Element rznSocRecep = doc.createElement("RznSocRecep");
							rznSocRecep.appendChild(doc.createTextNode(valRznSocRecep));
							receptor.appendChild(rznSocRecep);
							
							Element giroRecep = doc.createElement("GiroRecep");
							giroRecep.appendChild(doc.createTextNode(valGiroRecep));
							receptor.appendChild(giroRecep);
							
							Element contacto = doc.createElement("Contacto");
							contacto.appendChild(doc.createTextNode(valContactoRecep));
							receptor.appendChild(contacto);
							
							Element correoRecep = doc.createElement("CorreoRecep");
							correoRecep.appendChild(doc.createTextNode(valCorreoRecep));
							receptor.appendChild(correoRecep);
							
							Element dirRecep = doc.createElement("DirRecep");
							dirRecep.appendChild(doc.createTextNode(valDirRecep));
							receptor.appendChild(dirRecep);
							
							Element cmnaRecep = doc.createElement("CmnaRecep");
							cmnaRecep.appendChild(doc.createTextNode(valCmnaRecep));
							receptor.appendChild(cmnaRecep);
							
							Element ciudadRecep = doc.createElement("CiudadRecep");
							ciudadRecep.appendChild(doc.createTextNode(valCiudadRecep));
							receptor.appendChild(ciudadRecep);
							
							//NOTA: ESTE TAG NO EXISTE EN EL SII.
							Element proyecto = doc.createElement("Proyecto");
							proyecto.appendChild(doc.createTextNode(nombreBodega));
							receptor.appendChild(proyecto);
							//FIN
							
						Element totales = doc.createElement("Totales");
						encabezado.appendChild(totales);
						
							Element mntNeto = doc.createElement("MntNeto");
							mntNeto.appendChild(doc.createTextNode(valMntNeto));
							totales.appendChild(mntNeto);
							
							Element mntExe = doc.createElement("MntExe");
							mntExe.appendChild(doc.createTextNode(valMntExe));
							totales.appendChild(mntExe);
							
							Element tasaIVA = doc.createElement("TasaIVA");
							tasaIVA.appendChild(doc.createTextNode(valTasaIVA));
							totales.appendChild(tasaIVA);
							
							Element ivaAux = doc.createElement("IVA");
							ivaAux.appendChild(doc.createTextNode(valIVA));
							totales.appendChild(ivaAux);
							
							Element mntTotal = doc.createElement("MntTotal");
							mntTotal.appendChild(doc.createTextNode(valMntTotal));
							totales.appendChild(mntTotal);
			
					
					Double totalVenta = (double) 0;
					
					for(int i=0;i<guiasPer.size();i++) {
						
						List<List<String>> detalleAux = mapReportPorGuia10.get(guiasPer.get(i).get(8));
						
						if(detalleAux != null) {
							Double subTotal = (double) 0;
							int linea =0;
							
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
			    						
											Element detalle = doc.createElement("Detalle");
											documento.appendChild(detalle);
											linea++;
											
											String valNroLinDet = linea+"";
											String valVlrCodigo = detalleAux.get(j).get(10);
											String valNmbItem = detalleAux.get(j).get(11);
											String valQtyItem = detalleAux.get(j).get(13);
											String valUnmdItem = detalleAux.get(j).get(12);

						    					String auxCantidad = detalleAux.get(j).get(13);
						    					if(auxCantidad.equals("")||auxCantidad.equals(" "))	auxCantidad = "0";
						    					Double cantidad = (double)0;
						    					auxNum = auxCantidad.trim();
							 	   				if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
							 	   				cantidad = Double.parseDouble(auxNum.replaceAll(",", ""));
						    					
						    					String auxPrecio = detalleAux.get(j).get(15);
						    					if(auxPrecio.equals("")||auxPrecio.equals(" "))	auxPrecio = "0";
						    					Double precioUnitario = (double)0;
						    					auxNum = auxPrecio.trim();
							 	   				if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
							 	   				precioUnitario = Double.parseDouble(auxNum.replaceAll(",", ""));
						    					
						    					
						    					Long auxPUnitario = Math.round(precioUnitario);
						    					Long auxPTotal = Math.round(precioUnitario*cantidad);
						    					
						    					
						    					auxPUnitario = Math.round(tot/cantidad);
						    					auxPTotal=Math.round(tot);
						    					
						    					
											String valPrcItem = auxPUnitario.toString();
											String valMontoItem = auxPTotal.toString();
											
											Element nroLinDet = doc.createElement("NroLinDet");
											nroLinDet.appendChild(doc.createTextNode(valNroLinDet));
											detalle.appendChild(nroLinDet);
											
											Element cdgItem = doc.createElement("CdgItem");
											detalle.appendChild(cdgItem);
												Element tpoCodigo = doc.createElement("TpoCodigo");
												tpoCodigo.appendChild(doc.createTextNode("INT"));
												cdgItem.appendChild(tpoCodigo);
												Element vlrCodigo = doc.createElement("VlrCodigo");
												vlrCodigo.appendChild(doc.createTextNode(valVlrCodigo));
												cdgItem.appendChild(vlrCodigo);
											
											Element nmbItem = doc.createElement("NmbItem");
											nmbItem.appendChild(doc.createTextNode(valNmbItem));
											detalle.appendChild(nmbItem);
											
											Element qtyItem = doc.createElement("QtyItem");
											qtyItem.appendChild(doc.createTextNode(valQtyItem));
											detalle.appendChild(qtyItem);
											
											Element unmdItem = doc.createElement("UnmdItem");
											unmdItem.appendChild(doc.createTextNode(valUnmdItem));
											detalle.appendChild(unmdItem);
											
											Element prcItem = doc.createElement("PrcItem");
											prcItem.appendChild(doc.createTextNode(valPrcItem));
											detalle.appendChild(prcItem);
											
											Element montoItem = doc.createElement("MontoItem");
											montoItem.appendChild(doc.createTextNode(valMontoItem));
											detalle.appendChild(montoItem);
										}
			    					}
			    				}
							
						}
					}
					
				String valNroLinRef = "1";
				String valTpoDocRef = "0";
				String valFolioRef = "";
				String valFchRef = "";
				String valRazonRef = "";
				
				Element referencia = doc.createElement("Referencia");
				documento.appendChild(referencia);
					Element nroLinRef = doc.createElement("NroLinRef");
					nroLinRef.appendChild(doc.createTextNode(valNroLinRef));
					referencia.appendChild(nroLinRef);
					Element tpoDocRef = doc.createElement("TpoDocRef");
					tpoDocRef.appendChild(doc.createTextNode(valTpoDocRef));
					referencia.appendChild(tpoDocRef);
					Element dolioRef = doc.createElement("FolioRef");
					dolioRef.appendChild(doc.createTextNode(valFolioRef));
					referencia.appendChild(dolioRef);
					Element fchRef = doc.createElement("FchRef");
					fchRef.appendChild(doc.createTextNode(valFchRef));
					referencia.appendChild(fchRef);
					Element razonRef = doc.createElement("RazonRef");
					razonRef.appendChild(doc.createTextNode(valRazonRef));
					referencia.appendChild(razonRef);
					
				valNroLinRef = "2";
				
				referencia = doc.createElement("Referencia");
				documento.appendChild(referencia);
					nroLinRef = doc.createElement("NroLinRef");
					nroLinRef.appendChild(doc.createTextNode(valNroLinRef));
					referencia.appendChild(nroLinRef);
					tpoDocRef = doc.createElement("TpoDocRef");
					tpoDocRef.appendChild(doc.createTextNode(valTpoDocRef));
					referencia.appendChild(tpoDocRef);
					dolioRef = doc.createElement("FolioRef");
					dolioRef.appendChild(doc.createTextNode(valFolioRef));
					referencia.appendChild(dolioRef);
					fchRef = doc.createElement("FchRef");
					fchRef.appendChild(doc.createTextNode(valFchRef));
					referencia.appendChild(fchRef);
					razonRef = doc.createElement("RazonRef");
					razonRef.appendChild(doc.createTextNode(valRazonRef));
					referencia.appendChild(razonRef);

				valNroLinRef = "3";
				
				referencia = doc.createElement("Referencia");
				documento.appendChild(referencia);
					nroLinRef = doc.createElement("NroLinRef");
					nroLinRef.appendChild(doc.createTextNode(valNroLinRef));
					referencia.appendChild(nroLinRef);
					tpoDocRef = doc.createElement("TpoDocRef");
					tpoDocRef.appendChild(doc.createTextNode(valTpoDocRef));
					referencia.appendChild(tpoDocRef);
					dolioRef = doc.createElement("FolioRef");
					dolioRef.appendChild(doc.createTextNode(valFolioRef));
					referencia.appendChild(dolioRef);
					fchRef = doc.createElement("FchRef");
					fchRef.appendChild(doc.createTextNode(valFchRef));
					referencia.appendChild(fchRef);
					razonRef = doc.createElement("RazonRef");
					razonRef.appendChild(doc.createTextNode(valRazonRef));
					referencia.appendChild(razonRef);					
						
				Element parametros = doc.createElement("Parametros");
				dte.appendChild(parametros);
					Element obs = doc.createElement("Obs");
					obs.appendChild(doc.createTextNode(valObs));
					parametros.appendChild(obs);
						
			
					
					
		// escribimos el contenido en un archivo .xml
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
		
		DOMSource source = new DOMSource(doc);
		
		
		File tmp = TempFile.createTempFile("tmp","null");
		StreamResult result = new StreamResult(tmp);
		transformer.transform(source, result);
		archivoXml = proforma.proformaXml;
		Archivos.grabarArchivo(tmp, db+"/"+archivoXml);
		} catch (ParserConfigurationException pce) {
    			pce.printStackTrace();
		} catch (TransformerException tfe) {
    			tfe.printStackTrace();
		}
	  return(archivoXml);
	}
}
	

	
