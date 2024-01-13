package models.xml;


import java.io.File;
import java.io.StringWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.util.TempFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import models.tables.BodegaEmpresa;
import models.tables.Proforma;
import models.utilities.Archivos;


public class XmlFacturaReferencias {
	//IdDoc
	public String tipoDTE;
	public String folio;
	public String fchEmis;
	public String periodoDesde;
	public String periodoHasta;
	public String medioPago;
	public String tipoCtaPago;
	public String numCtaPago;
	public String bcoPago;
	public String termPagoCdg;
	public String termPagoGlosa;
	public String fchVenc;
	//Receptor
	public String rUTRecep;
	public String rznSocRecep;
	public String giroRecep;
	public String contacto;
	public String correoRecep;
	public String dirRecep;
	public String cmnaRecep;
	public String ciudadRecep;
	//Totales
	public String mntNeto;
	public String mntExe;
	public String tasaIVA;
	public String iva;
	public String mntTotal;
	//Detalle
	public List<String> nroLinDet;
	public List<String> tpoCodigo;
	public List<String> vlrCodigo;
	public List<String> nmbItem;
	public List<String> qtyItem;
	public List<String> unmdItem;
	public List<String> prcItem;
	public List<String> montoItem;
	//Referencia
	public List<String> nroLinRef;
	public List<String> tpoDocRef;
	public List<String> folioRef;
	public List<String> fchRef;
	public List<String> razonRef;
	//Parametros
	public String obs;


	



	public XmlFacturaReferencias(String tipoDTE, String folio, String fchEmis, String periodoDesde, String periodoHasta,
			String medioPago, String tipoCtaPago, String numCtaPago, String bcoPago, String termPagoCdg,
			String termPagoGlosa, String fchVenc, String rUTRecep, String rznSocRecep, String giroRecep,
			String contacto, String correoRecep, String dirRecep, String cmnaRecep, String ciudadRecep, String mntNeto,
			String mntExe, String tasaIVA, String iva, String mntTotal, List<String> nroLinDet, List<String> tpoCodigo,
			List<String> vlrCodigo, List<String> nmbItem, List<String> qtyItem, List<String> unmdItem,
			List<String> prcItem, List<String> montoItem, List<String> nroLinRef, List<String> tpoDocRef,
			List<String> folioRef, List<String> fchRef, List<String> razonRef, String obs) {
		super();
		this.tipoDTE = tipoDTE;
		this.folio = folio;
		this.fchEmis = fchEmis;
		this.periodoDesde = periodoDesde;
		this.periodoHasta = periodoHasta;
		this.medioPago = medioPago;
		this.tipoCtaPago = tipoCtaPago;
		this.numCtaPago = numCtaPago;
		this.bcoPago = bcoPago;
		this.termPagoCdg = termPagoCdg;
		this.termPagoGlosa = termPagoGlosa;
		this.fchVenc = fchVenc;
		this.rUTRecep = rUTRecep;
		this.rznSocRecep = rznSocRecep;
		this.giroRecep = giroRecep;
		this.contacto = contacto;
		this.correoRecep = correoRecep;
		this.dirRecep = dirRecep;
		this.cmnaRecep = cmnaRecep;
		this.ciudadRecep = ciudadRecep;
		this.mntNeto = mntNeto;
		this.mntExe = mntExe;
		this.tasaIVA = tasaIVA;
		this.iva = iva;
		this.mntTotal = mntTotal;
		this.nroLinDet = nroLinDet;
		this.tpoCodigo = tpoCodigo;
		this.vlrCodigo = vlrCodigo;
		this.nmbItem = nmbItem;
		this.qtyItem = qtyItem;
		this.unmdItem = unmdItem;
		this.prcItem = prcItem;
		this.montoItem = montoItem;
		this.nroLinRef = nroLinRef;
		this.tpoDocRef = tpoDocRef;
		this.folioRef = folioRef;
		this.fchRef = fchRef;
		this.razonRef = razonRef;
		this.obs = obs;
	}





	public XmlFacturaReferencias() {
		super();
	}
	

	public static String grabarReferencias(Connection con, Map<String,String> mapPermiso, String db, String archivoXml, XmlFacturaReferencias factura, Long id_proforma) {
		String xmlStr = "0";
		try {

			File tmp = Archivos.parseInputStreamToFile(Archivos.leerArchivo(db+"/"+archivoXml));
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(tmp);
			doc.getDocumentElement().normalize();
			
			//Referencia
			
			Node nNode = doc.getElementsByTagName("Documento").item(0);
			Element documento = (Element) nNode;
			
			NodeList nList = doc.getElementsByTagName("Referencia");
			
			int cantRef = nList.getLength();
			for (int i = cantRef; i >0 ; i--) {
				Node referencia = nList.item(i-1);
				Element eElement = (Element) referencia;
				eElement.getParentNode().removeChild(eElement);
			}
			doc.normalize();
			
			Long item =(long)0;
			
			cantRef=0;
			try {cantRef=factura.tpoDocRef.size();}catch(Exception e) {}
			for (int i = 0; i < cantRef; i++) {
				if(!factura.tpoDocRef.get(i).equals("0")) {
					item++;
					
					Element referencia = doc.createElement("Referencia");
					documento.appendChild(referencia);
					
					Element nroLinRef = doc.createElement("NroLinRef");
					nroLinRef.appendChild(doc.createTextNode(item.toString()));
					referencia.appendChild(nroLinRef);
					
					Element tpoDocRef = doc.createElement("TpoDocRef");
					tpoDocRef.appendChild(doc.createTextNode(factura.tpoDocRef.get(i)));
					referencia.appendChild(tpoDocRef);
					
					Element dolioRef = doc.createElement("FolioRef");
					dolioRef.appendChild(doc.createTextNode(factura.folioRef.get(i)));
					referencia.appendChild(dolioRef);
					
					Element fchRef = doc.createElement("FchRef");
					fchRef.appendChild(doc.createTextNode(factura.fchRef.get(i)));
					referencia.appendChild(fchRef);
					
					Element razonRef = doc.createElement("RazonRef");
					razonRef.appendChild(doc.createTextNode(factura.razonRef.get(i)));
					referencia.appendChild(razonRef);
				}
				
			}
			
			if(mapPermiso.get("parametro.proformaListar-llenarWebIConstruye")==null && mapPermiso.get("parametro.proformaListar-llenarWebIConstruye").equals("0")){
				//Parametros
				nList = doc.getElementsByTagName("Parametros");
				for (int i = 0; i < nList.getLength(); i++) {
					nNode = nList.item(i);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;
						eElement.getElementsByTagName("Obs").item(0).setTextContent(factura.obs);
						
					}
				}
			}
			
			
			
			
			// escribimos el contenido en un archivo .xml
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			
			transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
//			//Insertar saltos de línea al final de cada línea
//			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//			//Añadir 3 espacios delante, en función del nivel de cada nodo
//			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","3");
			
			DOMSource source = new DOMSource(doc);
			
			StreamResult result = new StreamResult(tmp);
			transformer.transform(source, result);
			
			Archivos.grabarArchivo(tmp, db+"/"+archivoXml);
			
			 StringWriter writer = new StringWriter();
			 
		        //transform document to string 
		        transformer.transform(source, new StreamResult(writer));
		 
		        xmlStr = writer.getBuffer().toString(); 
		        
		        xmlStr = xmlStr.replace("encoding=\"UTF-8\" standalone=\"yes\"", "encoding=\"iso-8859-1\"");
		        xmlStr = xmlStr.replace("DTE version=\"1.0\"", "DTE xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.sii.cl/SiiDte\" version=\"1.0\"");
		        xmlStr = xmlStr.replace("<TermPagoGlosa/>", "<TermPagoGlosa>CONTADO</TermPagoGlosa>");
		        xmlStr = xmlStr.replace("<TermPagoCdg/>", "<FmaPago>1</FmaPago>");
		        
			
		} catch (Exception e) {
			e.printStackTrace();
	    }
		
		return(xmlStr);
	}
	
	
	
	
	
//	public static XmlFacturaReferencias leerArchivoXml(String carpeta,String archivo) {
//		XmlFacturaReferencias factura = new XmlFacturaReferencias();		
//		try {
//
//			File fXmlFile = new File(carpeta+archivo);
//			
//			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//			Document doc = dBuilder.parse(fXmlFile);
//			doc.getDocumentElement().normalize();
//			
//			//IdDoc
//			NodeList nList = doc.getElementsByTagName("IdDoc");
//			for (int i = 0; i < nList.getLength(); i++) {
//				Node nNode = nList.item(i);
//				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//					Element eElement = (Element) nNode;
//					factura.tipoDTE = eElement.getElementsByTagName("TipoDTE").item(0).getTextContent();
//					factura.folio = eElement.getElementsByTagName("Folio").item(0).getTextContent();
//					factura.fchEmis = eElement.getElementsByTagName("FchEmis").item(0).getTextContent();
//					factura.periodoDesde = eElement.getElementsByTagName("PeriodoDesde").item(0).getTextContent();
//					factura.periodoHasta = eElement.getElementsByTagName("PeriodoHasta").item(0).getTextContent();
//					factura.medioPago = eElement.getElementsByTagName("MedioPago").item(0).getTextContent();
//					factura.tipoCtaPago = eElement.getElementsByTagName("TipoCtaPago").item(0).getTextContent();
//					factura.numCtaPago = eElement.getElementsByTagName("NumCtaPago").item(0).getTextContent();
//					factura.bcoPago = eElement.getElementsByTagName("BcoPago").item(0).getTextContent();
//					factura.termPagoCdg = eElement.getElementsByTagName("TermPagoCdg").item(0).getTextContent();
//					factura.termPagoGlosa = eElement.getElementsByTagName("TermPagoGlosa").item(0).getTextContent();
//					factura.fchVenc = eElement.getElementsByTagName("FchVenc").item(0).getTextContent();
//				}
//			}
//			
//			//Receptor
//			nList = doc.getElementsByTagName("Receptor");
//			for (int i = 0; i < nList.getLength(); i++) {
//				Node nNode = nList.item(i);
//				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//					Element eElement = (Element) nNode;
//					factura.rUTRecep = eElement.getElementsByTagName("RUTRecep").item(0).getTextContent();
//					factura.rznSocRecep = eElement.getElementsByTagName("RznSocRecep").item(0).getTextContent();
//					factura.giroRecep = eElement.getElementsByTagName("GiroRecep").item(0).getTextContent();
//					factura.contacto = eElement.getElementsByTagName("Contacto").item(0).getTextContent();
//					factura.correoRecep = eElement.getElementsByTagName("CorreoRecep").item(0).getTextContent();
//					factura.dirRecep = eElement.getElementsByTagName("DirRecep").item(0).getTextContent();
//					factura.cmnaRecep = eElement.getElementsByTagName("CmnaRecep").item(0).getTextContent();
//					factura.ciudadRecep = eElement.getElementsByTagName("CiudadRecep").item(0).getTextContent();
//				}
//			}
//			
//			//Totales
//			nList = doc.getElementsByTagName("Totales");
//			for (int i = 0; i < nList.getLength(); i++) {
//				Node nNode = nList.item(i);
//				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//					Element eElement = (Element) nNode;
//					factura.mntNeto = eElement.getElementsByTagName("MntNeto").item(0).getTextContent();
//					factura.mntExe = eElement.getElementsByTagName("MntExe").item(0).getTextContent();
//					factura.tasaIVA = eElement.getElementsByTagName("TasaIVA").item(0).getTextContent();
//					factura.iva = eElement.getElementsByTagName("IVA").item(0).getTextContent();
//					factura.mntTotal = eElement.getElementsByTagName("MntTotal").item(0).getTextContent();
//				}
//			}
//			
//			//Detalle
//			nList = doc.getElementsByTagName("Detalle");
//			List<String> auxNroLinDet = new ArrayList<String>();
//			List<String> auxTpoCodigo = new ArrayList<String>();
//			List<String> auxVlrCodigo = new ArrayList<String>();
//			List<String> auxNmbItem = new ArrayList<String>();
//			List<String> auxQtyItem = new ArrayList<String>();
//			List<String> auxUnmdItem = new ArrayList<String>();
//			List<String> auxPrcItem = new ArrayList<String>();
//			List<String> auxMontoItem = new ArrayList<String>();
//			for (int i = 0; i < nList.getLength(); i++) {
//				Node nNode = nList.item(i);
//				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//					Element eElement = (Element) nNode;
//					auxNroLinDet.add(eElement.getElementsByTagName("NroLinDet").item(0).getTextContent());
//					auxTpoCodigo.add(eElement.getElementsByTagName("TpoCodigo").item(0).getTextContent());
//					auxVlrCodigo.add(eElement.getElementsByTagName("VlrCodigo").item(0).getTextContent());
//					auxNmbItem.add(eElement.getElementsByTagName("NmbItem").item(0).getTextContent());
//					auxQtyItem.add(eElement.getElementsByTagName("QtyItem").item(0).getTextContent());
//					auxUnmdItem.add(eElement.getElementsByTagName("UnmdItem").item(0).getTextContent());
//					auxPrcItem.add(eElement.getElementsByTagName("PrcItem").item(0).getTextContent());
//					auxMontoItem.add(eElement.getElementsByTagName("MontoItem").item(0).getTextContent());
//				}
//			}
//			factura.nroLinDet=auxNroLinDet;
//			factura.tpoCodigo=auxTpoCodigo;
//			factura.vlrCodigo=auxVlrCodigo;
//			factura.nmbItem=auxNmbItem;
//			factura.qtyItem=auxQtyItem;
//			factura.unmdItem=auxUnmdItem;
//			factura.prcItem=auxPrcItem;
//			factura.montoItem=auxMontoItem;
//			
//			//Referencia
//			List<String> auxNroLinRef = new ArrayList<String>();
//			List<String> auxTpoDocRef = new ArrayList<String>();
//			List<String> auxFolioRef = new ArrayList<String>();
//			List<String> auxFchRef = new ArrayList<String>();
//			List<String> auxRazonRef = new ArrayList<String>();
//			nList = doc.getElementsByTagName("Referencia");
//			for (int i = 0; i < nList.getLength(); i++) {
//				Node nNode = nList.item(i);
//				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//					Element eElement = (Element) nNode;
//					auxNroLinRef.add(eElement.getElementsByTagName("NroLinRef").item(0).getTextContent());
//					auxTpoDocRef.add(eElement.getElementsByTagName("TpoDocRef").item(0).getTextContent());
//					auxFolioRef.add(eElement.getElementsByTagName("FolioRef").item(0).getTextContent());
//					auxFchRef.add(eElement.getElementsByTagName("FchRef").item(0).getTextContent());
//					auxRazonRef.add(eElement.getElementsByTagName("RazonRef").item(0).getTextContent());
//				}
//			}
//			factura.nroLinRef=auxNroLinRef;
//			factura.tpoDocRef=auxTpoDocRef;
//			factura.folioRef=auxFolioRef;
//			factura.fchRef=auxFchRef;
//			factura.razonRef=auxRazonRef;
//			
//			//Parametros
//			nList = doc.getElementsByTagName("Parametros");
//			for (int i = 0; i < nList.getLength(); i++) {
//				Node nNode = nList.item(i);
//				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//					Element eElement = (Element) nNode;
//					factura.obs = eElement.getElementsByTagName("Obs").item(0).getTextContent();				}
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//	    }
//		 return(factura);
//	   
//		
//	}
//	
//	
//	
	
	
	
	
	
}
