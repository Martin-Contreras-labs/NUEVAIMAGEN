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

import models.tables.Atributo;
import models.tables.BodegaEmpresa;
import models.tables.Cliente;
import models.tables.EmisorTributario;
import models.tables.Guia;
import models.tables.Proyecto;
import models.tables.Sucursal;
import models.tables.Transportista;
import models.utilities.Archivos;

 
public class XmlGuiaSalida {

	public static String generaXmlGuiaSalida(Connection con, String db, Guia guia, List<List<String>> detalleGuia, Transportista transportista, EmisorTributario emisorTributario, Map<String,String> mapPermiso) {
		
		BodegaEmpresa bodegaOrigen = BodegaEmpresa.findXIdBodega(con, db, guia.getId_bodegaOrigen());
		Cliente clienteOrigen = Cliente.find(con, db, bodegaOrigen.getId_cliente());
		
		BodegaEmpresa bodegaDestino = BodegaEmpresa.findXIdBodega(con, db, guia.getId_bodegaDestino());
		Cliente clienteDestino = Cliente.find(con, db, bodegaDestino.getId_cliente());
		Proyecto proyectoDestino = Proyecto.find(con, db, bodegaDestino.getId_proyecto());
		
		
		
		String archivoXml = "G_" + guia.numero + "_guiaSalida.xml";
		Guia.modificaPorCampo(con, db, "guiaXml", guia.getId(), archivoXml);
		
		String versionDte = "1.0";
		String valTipoDTE = "52";
		String valFolio = guia.id.toString();
		
		String idDocumento = "R"+emisorTributario.rut.toUpperCase()+"T"+valTipoDTE+"F"+valFolio;
		
		String auxFecha[] = guia.fecha.split("-");
		String valFchEmis = auxFecha[2]+"-"+auxFecha[1]+"-"+auxFecha[0];
		
		String valIndTraslado = "6";
		
		String valRUTEmisor = emisorTributario.rut;
		String valRznSoc = emisorTributario.razonSocial;
		String valGiroEmis = emisorTributario.giro;
		String valActeco = emisorTributario.actividadEconomica;
		
		
		String valDirOrigen = clienteOrigen.direccion;
		if(valDirOrigen.length()>100) valDirOrigen=valDirOrigen.substring(0, 99);
		String valCmnaOrigen = clienteOrigen.comuna;
		if(valCmnaOrigen.length()>20) valCmnaOrigen=valCmnaOrigen.substring(0, 19);
		String valCiudadOrigen = clienteOrigen.ciudad;
		if(valCiudadOrigen.length()>20) valCiudadOrigen=valCiudadOrigen.substring(0, 19);
		
		String valRUTRecep = clienteDestino.rut.toUpperCase();
		String valRznSocRecep = clienteDestino.nombre;
		if(valRznSocRecep.length()>100) valRznSocRecep=valRznSocRecep.substring(0, 99);
		String valGiroRecep = clienteDestino.giro;
		if(valGiroRecep.length()>40) valGiroRecep=valGiroRecep.substring(0, 39);
		String valDirRecep = clienteDestino.direccion;
		if(valDirRecep.length()>70) valDirRecep=valDirRecep.substring(0, 69);
		String valCmnaRecep = clienteDestino.comuna;
		if(valCmnaRecep.length()>20) valCmnaRecep=valCmnaRecep.substring(0, 19);
		String valCiudadRecep = clienteDestino.ciudad;
		if(valCiudadRecep.length()>20) valCiudadRecep=valCiudadRecep.substring(0, 19);
		
		String valPatente = transportista.patente;
		String valRUTChofer = transportista.rutConductor;		
		String valNombreChofer = transportista.conductor;
		
		String valDirDest = proyectoDestino.direccion;
		if(valDirDest.length()>100) valDirDest=valDirDest.substring(0, 99);
		String valCmnaDest = proyectoDestino.comuna;
		if(valCmnaDest.length()>20) valCmnaDest=valCmnaDest.substring(0, 19);
		String valCiudadDest = proyectoDestino.ciudad;
		if(valCiudadDest.length()>20) valCiudadDest=valCiudadDest.substring(0, 19);
		
		
		String observaciones = guia.observaciones;
		if(observaciones==null) observaciones = "";
		try {
			observaciones = guia.observaciones.replaceAll("[\\r]", "");
		}catch(Exception e) {}
		String valObs ="Solo despacho, no constituye venta. \n"+observaciones;
 
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
							tipoDte.appendChild(doc.createTextNode(valTipoDTE));//valor
							idDoc.appendChild(tipoDte);
							Element folio = doc.createElement("Folio");
							folio.appendChild(doc.createTextNode(valFolio));
							idDoc.appendChild(folio);
							Element fchEmis = doc.createElement("FchEmis");
							fchEmis.appendChild(doc.createTextNode(valFchEmis));
							idDoc.appendChild(fchEmis);
							Element IndTraslado = doc.createElement("IndTraslado");//llena algó???
							IndTraslado.appendChild(doc.createTextNode(valIndTraslado));
							idDoc.appendChild(IndTraslado);
							
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
							
							Element dirRecep = doc.createElement("DirRecep");
							dirRecep.appendChild(doc.createTextNode(valDirRecep));
							receptor.appendChild(dirRecep);
							
							Element cmnaRecep = doc.createElement("CmnaRecep");
							cmnaRecep.appendChild(doc.createTextNode(valCmnaRecep));
							receptor.appendChild(cmnaRecep);
							
							Element ciudadRecep = doc.createElement("CiudadRecep");
							ciudadRecep.appendChild(doc.createTextNode(valCiudadRecep));
							receptor.appendChild(ciudadRecep);
							
						Element transporte = doc.createElement("Transporte");
						encabezado.appendChild(transporte);
							Element patente = doc.createElement("Patente");
							patente.appendChild(doc.createTextNode(valPatente));
							transporte.appendChild(patente);
							
							Element chofer = doc.createElement("Chofer");
							transporte.appendChild(chofer);
								Element rutChofer = doc.createElement("RUTChofer");
								rutChofer.appendChild(doc.createTextNode(valRUTChofer));
								chofer.appendChild(rutChofer);
								Element nombreChofer = doc.createElement("NombreChofer");
								nombreChofer.appendChild(doc.createTextNode(valNombreChofer));
								chofer.appendChild(nombreChofer);
							
							Element dirDest = doc.createElement("DirDest");
							dirDest.appendChild(doc.createTextNode(valDirDest));
							transporte.appendChild(dirDest);
							Element cmnaDest = doc.createElement("CmnaDest");
							cmnaDest.appendChild(doc.createTextNode(valCmnaDest));
							transporte.appendChild(cmnaDest);
							Element ciudadDest = doc.createElement("CiudadDest");
							ciudadDest.appendChild(doc.createTextNode(valCiudadDest));
							transporte.appendChild(ciudadDest);
						
						
						Long totalNeto=(long)0;
						
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
						
						
						String IvaTasa = tasaIvaArrAuxiliar.toString();
						
						for(int i=0;i<detalleGuia.size();i++) {
							
							String auxPrecio = detalleGuia.get(i).get(9); //precio unitario de venta
    							if(auxPrecio.equals("")||auxPrecio.equals(" "))	auxPrecio = "0";
    							Double precioUnitario = (double)0;
    							String auxNum = auxPrecio.trim().replaceAll(",", "");
				 	   			if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
				 	   			precioUnitario = Double.parseDouble(auxNum.replaceAll(",", ""));
		    					
		    					String auxCantidad = detalleGuia.get(i).get(8);
		    					if(auxCantidad.equals("")||auxCantidad.equals(" "))	auxCantidad = "0";
		    					Double cantidad = (double)0;
		    					auxNum = auxCantidad.trim().replaceAll(",", "");
				 	   			if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
				 	   			cantidad = Double.parseDouble(auxNum.replaceAll(",", ""));
							
							Long auxPTotal = Math.round(precioUnitario*cantidad);
							totalNeto=totalNeto+auxPTotal;
						}
						Long iva = Math.round(totalNeto * tasaIvaArrAuxiliar/100);
						Long total=totalNeto+iva;
							
						Element totales = doc.createElement("Totales");
						encabezado.appendChild(totales);
							Element mntNeto = doc.createElement("MntNeto");
							mntNeto.appendChild(doc.createTextNode(totalNeto.toString()));
							totales.appendChild(mntNeto);
							Element mntExe = doc.createElement("MntExe");
							mntExe.appendChild(doc.createTextNode("0"));
							totales.appendChild(mntExe);
							Element tasaIVA = doc.createElement("TasaIVA");
							tasaIVA.appendChild(doc.createTextNode(IvaTasa));
							totales.appendChild(tasaIVA);
							Element ivaAux = doc.createElement("IVA");
							ivaAux.appendChild(doc.createTextNode(iva.toString()));
							totales.appendChild(ivaAux);
							Element mntTotal = doc.createElement("MntTotal");
							mntTotal.appendChild(doc.createTextNode(total.toString()));
							totales.appendChild(mntTotal);
							
							
					
					
			
	   				for(int i=0;i<detalleGuia.size();i++) {
	   					
						Element detalle = doc.createElement("Detalle");
						documento.appendChild(detalle);
						
							Long linea = i+(long)1;
							String valNroLinDet = linea.toString();
							String valVlrCodigo = detalleGuia.get(i).get(5);
							String valNmbItem = detalleGuia.get(i).get(6);
							String valQtyItem = detalleGuia.get(i).get(8);
							String valUnmdItem = detalleGuia.get(i).get(7);
							
							String auxPrecio = detalleGuia.get(i).get(9); //precio unitario de venta
	    						if(auxPrecio.equals("")||auxPrecio.equals(" "))	auxPrecio = "0";
	    						
	    						String auxCantidad = detalleGuia.get(i).get(8);
		    					if(auxCantidad.equals("")||auxCantidad.equals(" "))	auxCantidad = "0";

		    					Double precioUnitario = (double)0;
		    					String auxNum = auxPrecio.trim().replaceAll(",", "");
				 	   			if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
				 	   			precioUnitario = Double.parseDouble(auxNum.replaceAll(",", ""));
		    					Double cantidad = (double)0;
		    					auxNum = auxCantidad.trim().replaceAll(",", "");
				 	   			if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
				 	   			cantidad = Double.parseDouble(auxNum.replaceAll(",", ""));
		    					
		    					
		    					Long auxPUnitario = Math.round(precioUnitario);
		    					Long auxPTotal = Math.round(precioUnitario*cantidad);
		    					
							String valPrcItem = auxPUnitario.toString();
							String valMontoItem = auxPTotal.toString();
							
							Double kg = Double.parseDouble(detalleGuia.get(i).get(36));
							
							Double auxTotalKG =(double) Math.round(kg*cantidad*100)/100;
							if(auxTotalKG==0) auxTotalKG=0.01;
							
							String valUnmdRef="KG";
							String valQtyRef=auxTotalKG.toString();
							
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
							
							Element qtyRef = doc.createElement("QtyRef");
							qtyRef.appendChild(doc.createTextNode(valQtyRef));
							detalle.appendChild(qtyRef);
							
							Element unmdRef = doc.createElement("UnmdRef");
							unmdRef.appendChild(doc.createTextNode(valUnmdRef));
							detalle.appendChild(unmdRef);
							
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
							
							totalNeto=totalNeto+auxPTotal;
					}
					

					Element parametros = doc.createElement("Parametros");
					dte.appendChild(parametros);
						Element obs = doc.createElement("Obs");
						obs.appendChild(doc.createTextNode(valObs));
						parametros.appendChild(obs);
						
			// escribimos el contenido en un archivo .xml
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
			
			//Insertar saltos de línea al final de cada línea
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			
			//Añadir 3 espacios delante, en función del nivel de cada nodo
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","3");
		
			DOMSource source = new DOMSource(doc);
			
			File tmp = TempFile.createTempFile("tmp","null");
			StreamResult result = new StreamResult(tmp);
			transformer.transform(source, result);
			
			Archivos.grabarArchivo(tmp, db+"/"+archivoXml);
		
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	  return(archivoXml);
	}
	
	
	
	
}
	

	

	
