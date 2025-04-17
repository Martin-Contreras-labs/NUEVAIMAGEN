package controllers;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.TempFile;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableCell.XWPFVertAlign;

import com.typesafe.config.Config;

import models.tables.AuxHuella;
import models.tables.Diccionario;
import models.tables.EquipoServicio;
import models.tables.Guia;
import models.tables.ListaPrecioServicio;
import models.tables.OperadorServicio;
import models.tables.Sucursal;
import models.tables.TasasCambio;
import models.tables.TipoEstado;
import models.tables.Usuario;
import models.tables.UsuarioPermiso;
import models.tables.VentaServicio;
import models.utilities.Admin;
import models.utilities.Archivos;
import models.utilities.DatabaseExecutionContext;
import models.utilities.DatabaseRead;
import models.utilities.Fechas;
import models.utilities.Inicio;
import models.utilities.Registro;
import models.utilities.UserMnu;
import models.utilities.VerificarCaptcha;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.Database;
import play.libs.mailer.Email;
import play.libs.mailer.MailerClient;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;
import play.mvc.*;

import views.html.*;
import viewsMnuOdo.html.odoShowPDF;
import viewsMnuOdoAppWeb.html.*;
import viewsMnuOdoAppWeb.html.odoAutorizaListarVentasWeb;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */

public class HomeController extends Controller {
	
	
	public Result ping() {
    	boolean flag = false;
    	try {
			Connection con1 = dbRead.getConnection();
			PreparedStatement smt1 = con1
					.prepareStatement("select pais from mada.paises where pais='CHILE';");
			ResultSet rs1 = smt1.executeQuery();
			if(rs1.next()) {
				flag = true;
			}else {
				flag=false;
			}
			rs1.close();
			smt1.close();
			con1.close();
			
			Connection con2 = dbWrite.getConnection();
			PreparedStatement smt2 = con2
					.prepareStatement("select pais from mada.paises where pais='CHILE';");
			ResultSet rs2 = smt2.executeQuery();
			if(rs2.next()) {
				flag = true;
			}else {
				flag=false;
			}
			rs2.close();
			smt2.close();
			con2.close();
		} catch (SQLException e) {
			flag=false;
			e.printStackTrace();
		}
    	if(flag) {
    		return ok("OK");
    	}else {
    		return ok("FALLO");
    	}
    }
	
	
	
    //=============================================
    // DEFINICIONES DE VARIABLES Y CLASES GLOBALES
    //=============================================
	
	public static String msgErrorFormulario = "Se presento un error con el formulario, por favor reingrese.";
	public static String msgError = "Se presento un error de conexion o inactividad por mucho tiempo, por favor vuelva a ingresar.";
	public static String msgSinPermiso = "USTED NO TIENE PERMISO PARA ACCEDER, POR FAVOR NO LO INTENTE, GRACIAS";
	public static String msgReport = "Ocurrió un error al obtener el reporte. Intente nuevamente más tarde.";
	
	public static FormFactory formFactory;
	public static Database dbWrite;
	public static DatabaseRead dbRead;
	public static DatabaseExecutionContext executionContext;
	public static Config config;
	public final MailerClient mailerClient;
	private final WSClient ws;
	
    @SuppressWarnings("static-access")
	@Inject
    public HomeController(Database dbWrite, DatabaseRead dbRead, DatabaseExecutionContext executionContext,FormFactory formFactory,Config config,
    		MailerClient mailerClient, WSClient ws) {
    	this.dbWrite = dbWrite;
	    this.dbRead = dbRead;
	    this.executionContext = executionContext;
	    this.formFactory = formFactory;
	    this.config = config;
	    this.mailerClient = mailerClient;
	    this.ws = ws;
	}
    
    public static class Sessiones{
    	public String userName;
    	public String id_usuario;
    	public String baseDato;
    	public String id_tipoUsuario;
    	public String porProyecto;
    	public String strEncoded;
    	public String id_sucursal;
    	public String aplicaPorSucursal;
    	
    	public Sessiones(Http.Request request) {
			super();
			try {
				this.userName = request.session().get("userName").get();
				this.id_usuario = request.session().get("id_usuario").get();
				this.baseDato = request.session().get("baseDato").get();
				this.id_tipoUsuario = request.session().get("id_tipoUsuario").get();
				this.porProyecto = request.session().get("porProyecto").get();
				this.id_sucursal = request.session().get("id_sucursal").get();
				this.aplicaPorSucursal = request.session().get("aplicaPorSucursal").get();
			}catch (Exception e) {
				this.userName = null;
				this.id_usuario = null;
				this.baseDato = null;
				this.id_tipoUsuario = null;
				this.porProyecto = null;
				this.id_sucursal = null;
				this.aplicaPorSucursal = null;
			}
		}
    	
    	public Sessiones(Http.Request request, String soloQr) {
    		super();
    		try {
				this.strEncoded = request.session().get("strEncoded").get();
			}catch (Exception e) {
				e.printStackTrace();
			}
    	}

		public boolean isValid() {
			return (userName != null && !userName.trim().isEmpty() &&
					id_usuario != null && !id_usuario.trim().isEmpty() &&
					baseDato != null && !baseDato.trim().isEmpty() &&
					id_tipoUsuario != null && !id_tipoUsuario.trim().isEmpty() &&
					porProyecto != null && !porProyecto.trim().isEmpty() &&
					id_sucursal != null && !id_sucursal.trim().isEmpty() &&
					aplicaPorSucursal != null && !aplicaPorSucursal.trim().isEmpty());
		}

	}
    
    
    //=============================================
    // FUNCIONES GLOBALES
    //=============================================
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    
    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    public static Map<String,String> mapPermisos(Http.Request request){
    	Map<String,String> map = new HashMap<String,String>();
    	Sessiones s = new Sessiones(request);
    	if(s.baseDato!=null) {
    		try {
        		Connection con = dbRead.getConnection();
    			map = UsuarioPermiso.mapPermisos(con,s.baseDato,s.id_tipoUsuario);
    			con.close();
    			return(map);
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    	return(map);
	}
    
    public static Map<String,String> mapPermisos(String baseDato, String permiso){
    	Map<String,String> map = new HashMap<String,String>();
    	try {
    		Connection con = dbRead.getConnection();
			map = UsuarioPermiso.mapPermisos(con,baseDato,permiso);
			con.close();
			return(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return(map);
	}
    
    public static Map<String,String> mapDiccionario(Http.Request request){
    	Map<String,String> map = new HashMap<String,String>();
    	Sessiones s = new Sessiones(request);
    	if(s.baseDato!=null) {
    		try {
        		Connection con = dbRead.getConnection();
    			map = Diccionario.mapDiccionario(con, s.baseDato);
    			con.close();
    			return(map);
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    	return(map);
	}
    
    public static Map<String,String> mapDiccionario(String baseDato){
    	Map<String,String> map = new HashMap<String,String>();
    	try {
    		Connection con = dbRead.getConnection();
			map = Diccionario.mapDiccionario(con, baseDato);
			con.close();
			return(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return(map);
	}
    
    public static List<TipoEstado> listaEstados(UserMnu usuario){
    	List<TipoEstado> lista = new ArrayList<TipoEstado>();
		try {
			Connection con = dbRead.getConnection();
			lista = TipoEstado.all(con, usuario.getBaseDato());
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(lista);
	}
    
    public Result viewImg(String fileNameIMG, Http.Request request) {
    	Sessiones s = new Sessiones(request);
		if(s.baseDato!=null) {
			String path = s.baseDato+"/"+fileNameIMG;
			File salida = null;
			try {
				InputStream archivo = Archivos.leerArchivo(path);
				salida = Archivos.parseInputStreamToFile(archivo);
				archivo.close();
			} catch (IOException e) {
				e.printStackTrace();
				return ok("");
			}
	   		return ok(salida);
		}else {
			return ok("");
		}
   	}
    
    public Result viewImgAlbum(String fileNameIMG, String carpeta, Http.Request request) {
    	Sessiones s = new Sessiones(request);
		if(s.baseDato!=null) {
			String path = s.baseDato+"/"+carpeta+"/"+fileNameIMG;
			File salida = null;
			try {
				InputStream archivo = Archivos.leerArchivo(path);
				salida = Archivos.parseInputStreamToFile(archivo);
				archivo.close();
			} catch (IOException e) {
				e.printStackTrace();
				return ok("");
			}
	   		return ok(salida);
		}else {
			return ok("");
		}
   	}
    
    public Result showPdf(String fileNamePDF, Http.Request request){
    	Sessiones s = new Sessiones(request);
		if(s.baseDato!=null) {
			String path = s.baseDato+"/"+fileNamePDF;
			InputStream archivo = Archivos.leerArchivo(path);
			return ok(archivo);
		}else {
			return ok("");
		}
   	}


    public Result redirShowPDF(String fileNamePDF, String titulo, String url, Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    		url = url.replaceAll("&", ",");
    		return ok(showPDF.render(mapeoDiccionario, mapeoPermiso, userMnu, fileNamePDF, url, titulo));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    	
    }
    
    public Result redirOdoShowPDF(String fileNamePDF, String desdeAAMMDD, String hastaAAMMDD, String url, Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		if(desdeAAMMDD.length()<6) {
    			return ok(odoShowPDF.render(fileNamePDF, url));
    		}else {
    			return ok(odoShowPDF.render(fileNamePDF, url+desdeAAMMDD+","+hastaAAMMDD));
    		}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result showPdfManuales(String fileNamePDF, Http.Request request){
    	Sessiones s = new Sessiones(request);
		if(s.baseDato!=null) {
			String path = "aManuales/"+fileNamePDF;
			InputStream archivo = Archivos.leerArchivo(path);
			return ok(archivo);
		}else {
			return ok("");
		}
   	}
    
    public Result downloadPDF(Http.Request request){
    	Sessiones s = new Sessiones(request);
		if(s.baseDato!=null) {
			DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("");
	       	}else {
	       		String nombreArchivo = form.get("nombreArchivo");
	       		String path = s.baseDato+"/"+nombreArchivo;
	       		InputStream archivo = Archivos.leerArchivo(path);
	       		return ok(Archivos.parseInputStreamToFile(archivo),false,Optional.of(nombreArchivo));
	       	}
		}else {
			return ok("");
		}
   	}
    
    public static InputStream getInputStreamFromUrl(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoInput(true);
        connection.setConnectTimeout(10000); // Tiempo de espera para la conexión (10s)
        connection.setReadTimeout(10000);    // Tiempo de espera para la lectura (10s)
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            return connection.getInputStream();
        } else {
            return null;
        }
    }
    
    public Result tasasDeFechaAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	       		String fecha = form.get("fecha");
				try {
	    			Connection con = dbRead.getConnection();
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
        			TasasCambio tasas = TasasCambio.allDeUnaFecha(con, s.baseDato, mapeoDiccionario.get("pais"),fecha);
	    	    	con.close();
	    	    	return ok(""+tasas.getClpUf()+";"+tasas.getClpUsd()+";"+tasas.getClpEur());
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
				return ok("error");
	       	}
    	}else {
    		return ok("error");
    	}
    }
    
    public Result sendEmail(Http.Request request){
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		DynamicForm form = formFactory.form().bindFromRequest(request);
       		if (form.hasErrors()) {
           		return ok("ERROR");
       		}else {
	    		Email email = new Email();
	    		String asunto = "Envio desde ayuda/contacto MADA";
	   			String desde = "desde MADA <informaciones@inqsol.cl>";
	   			email.setSubject(asunto);
				email.setFrom(desde);
				email.setBodyHtml("<html><body>" +
	   		    		" <p>EMPRESA: <b>"+mapeoDiccionario.get("nEmpresa")+"</b></p>" +
	   		    		" <p>USUARIO: <b>"+s.userName+"</b></p>" +
	   		    		" <p>NOMBRE: <b>"+form.get("nombre")+"</b></p>" +
	   		    		" <p>CORREO: <b>"+form.get("email")+"</b></p>" +
	   		    		" <p>TELEFONO:<b>"+form.get("fono")+"</b></p>" +
	   		    		" <p>MENSAJE:<b>"+form.get("mensaje")+"</b></p>" +
	   		    		" </body></html>");
	   		    email.addTo("Pedro Barros <pbarros@inqsol.cl>");
	   		    email.addTo("Jorge Ignacio <jiprieto@inqsol.cl>");
	   		    email.addTo("Christopher Paulsen <cpaulsen@inqsol.cl>");
	   		    mailerClient.send(email);
	   		    String msg = "LISTO: MENSAJE ENVIADO";
	   		    return ok(mensajes.render("/home/",msg));
       		}
    	}
    	return ok("ERROR");
   	}
    
    public Result muestraAlbumFotos(String carpeta, Http.Request request) {
		Sessiones s = new Sessiones(request);
		if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    	
    		List<List<String>> listado = Archivos.listaDeObjetos(s.baseDato, carpeta);
    		
    		String aux[] = carpeta.split("_");
			String origen = aux[1];
    		String titulo = "";
			if(origen.equals("Mov")) {
				titulo = "MOVIMIENTO ";
			}else if(origen.equals("Report")){
				titulo = "REPORT ";
			}

    		return ok(muestraAlbumFotos.render(mapeoDiccionario, mapeoPermiso, userMnu, listado, carpeta, titulo));
		}else {
			return ok(mensajes.render("/",msgError));
		}
	}
    
    public Result muestraAlbumFotosWord(Http.Request request) {
    	DynamicForm form = formFactory.form().bindFromRequest(request);
   		if (form.hasErrors()) {
   			return ok(mensajes.render("/",msgErrorFormulario));
       	}else {
       		Sessiones s = new Sessiones(request);
       		String carpeta = form.get("carpeta");

       		if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
        		List<List<String>> listado = Archivos.listaDeObjetos(s.baseDato, carpeta);
        		List<List<List<String>>> lista = new ArrayList<List<List<String>>>();
        		for(int i=0; i<listado.size(); i++) {
        			List<List<String>> aux = new ArrayList<List<String>>();
        			for(int j=0; j<listado.get(i).size(); j++) {
        				List<String> aux2 = new ArrayList<String>();
        				aux2.add(carpeta+"/"+listado.get(i).get(j));
        				aux2.add(listado.get(i).get(j));
        				aux.add(aux2);
        			}
        			lista.add(aux);
        		}
        		try {
        			Connection con = dbRead.getConnection();
        			
        			
        			String aux[] = carpeta.split("_");
        			String origen = aux[1];
        			File file = null;
        			
        			if(origen.equals("Mov")) {
        				Guia guia = Guia.findPorAlbum(con,  s.baseDato, carpeta);
        				VentaServicio ventaServicio = null;
            			file = HomeController.reporteEnWordHome(s.baseDato, lista, guia, ventaServicio);
        			}else if(origen.equals("Report")){
        				Guia guia = null;
        				VentaServicio ventaServicio = VentaServicio.findPorAlbum(con, s.baseDato, carpeta);
            			file = HomeController.reporteEnWordHome(s.baseDato, lista, guia, ventaServicio);
        			}
        			
        			
        			if(file!=null) {
    	       			con.close();
    	       			return ok(file,false,Optional.of("Imagenes_"+carpeta+".docx"));
    	       		}else {
    	       			con.close();
    	       			return ok("");
    	       		}
        		} catch (SQLException e) {
        			e.printStackTrace();
        		}
       		}
       		return ok(mensajes.render("/",msgError));
       	}
   		
	}

    public static File reporteEnWordHome(String db, List<List<List<String>>> lista, Guia guia, VentaServicio ventaServicio) {
		File tmp = TempFile.createTempFile("tmp","null");
		try {
			String path = "formatos/listadoFOTOS.docx";
			InputStream formato = Archivos.leerArchivo(path);
			XWPFDocument doc = new XWPFDocument(formato);
			
			
			if(guia!=null) {
				XWPFParagraph p1 = doc.getParagraphArray(0);
	            p1.setAlignment(ParagraphAlignment.LEFT);
	            XWPFRun r1 = p1.createRun();
	            r1.setBold(true);
	            r1.setText("Movimiento Nro: " + guia.getNumero() + " de fecha " + Fechas.DDMMAA(guia.getFecha()) );
	            
	            p1 = doc.getParagraphArray(1);
	            r1 = p1.createRun();
	            r1.setText("Referencia: " + guia.getNumGuiaCliente());
	            
	            p1 = doc.getParagraphArray(2);
	            r1 = p1.createRun();
	            r1.setText("Origen: " + guia.getBodegaOrigen());
	            
	            p1 = doc.getParagraphArray(3);
	            r1 = p1.createRun();
	            r1.setText("Destino: " + guia.getBodegaDestino());
	            
	            p1 = doc.getParagraphArray(4);
	            r1 = p1.createRun();
	            r1.setText("Observaciones: " + guia.getObservaciones());
			}else if(ventaServicio!=null) {
				XWPFParagraph p1 = doc.getParagraphArray(0);
	            p1.setAlignment(ParagraphAlignment.LEFT);
	            XWPFRun r1 = p1.createRun();
	            r1.setBold(true);
	            r1.setText("Report Id: " + ventaServicio.getId() + " de fecha " + Fechas.DDMMAA(ventaServicio.getFecha()) );
	            
	            p1 = doc.getParagraphArray(1);
	            r1 = p1.createRun();
	            r1.setText("Servicio: " + ventaServicio.getNomServicio());
	            
	            p1 = doc.getParagraphArray(2);
	            r1 = p1.createRun();
	            r1.setText("Destino: " + ventaServicio.getNomBodegaEmpresa());
			}
            
			XWPFTable table = doc.getTables().get(0);
			XWPFTableCell cell = null;
			for (int i=0; i<lista.size(); i++) {
				for (int j=0; j<lista.get(i).size(); j++) {
					cell=table.getRow(i).getCell(j);
					String ruta = db+"/"+lista.get(i).get(j).get(0);
					InputStream inputStream = Archivos.leerArchivo(ruta);
					byte[] qr = IOUtils.toByteArray(inputStream);
					inputStream.close();
					XWPFParagraph paragraph = cell.addParagraph();
					paragraph.setAlignment(ParagraphAlignment.CENTER);
	    			XWPFRun run2 = paragraph.createRun();
	    			run2.addPicture(new ByteArrayInputStream(qr), XWPFDocument.PICTURE_TYPE_PNG, "firma", Units.toEMU(60), Units.toEMU(60));
	    			HomeController.setCeldaHome(cell,"Arial",6,2,"000000",lista.get(i).get(j).get(1),false);
				}
				table.createRow();
			}
			// Write the output to a file word
			FileOutputStream fileOut = new FileOutputStream(tmp);
			doc.write(fileOut);
			fileOut.close();
		} catch (InvalidFormatException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return(tmp);
	}
	
	public static void setCeldaHome (XWPFTableCell cell,String fontFamily,int fontSize,int alingH,String colorRGB,String text,boolean bold) {
		if(text==null) text="--"; else if(text.trim().length()==0) text="--";
		XWPFParagraph paragraph =null;
		try{cell.removeParagraph(0);}catch(Exception e) {}
		paragraph = cell.addParagraph();
		if(alingH==3) {
			paragraph.setAlignment(ParagraphAlignment.RIGHT);
		}else if (alingH==2) {
			paragraph.setAlignment(ParagraphAlignment.CENTER);
		}else {
			paragraph.setAlignment(ParagraphAlignment.LEFT);
		}
		cell.setVerticalAlignment(XWPFVertAlign.CENTER);
		XWPFRun celda = paragraph.createRun();
		celda.setFontFamily(fontFamily);
		celda.setFontSize(fontSize);
		celda.setColor(colorRGB);
		celda.setText(text);
		celda.setBold(bold);
    }
   
    public Result inicio() throws InterruptedException {
    	
 //   	return ok("SISTEMA EN MANTENCION......");
    	
    	
    	
    	try {
			List<String> lista = new ArrayList<String>();
			Connection con = dbRead.getConnection();
			PreparedStatement smt = con.
					prepareStatement("select pais from paises order by orden;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				lista.add(rs.getString(1));
			}
			rs.close();
			smt.close();
			con.close();
			return ok(inicio.render(lista,"0")).withNewSession()
					.discardingCookie("username")
					.discardingCookie("project")
					.discardingCookie("company")
					.discardingCookie("password");
    	} catch (SQLException e) {
			e.printStackTrace();
		}
    	return ok(mensajes.render("/",msgError));
    }
    
    public Result home(Http.Request request) {
  //  	return ok("SISTEMA EN MANTENCION......");
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		try {
    			Connection con = dbRead.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, userMnu.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			
	    		if(mapeoPermiso.get("odoVentasAppWeb")!=null) {
	    			con.close();
	    			return redirect("/homeOdoVentasWeb/");
	    		} else if(mapeoPermiso.get("odoAutorizadorAppWeb")!=null) {
	    			con.close();
	    			return redirect("/homeOdoAutorizaVentasWeb/");
				} else {
					Long esMoroso = Registro.esMoroso(con, s.baseDato);
	    			con.close();
	    			return ok(vistaPrincipal.render(mapeoDiccionario,mapeoPermiso,userMnu,esMoroso));
				}
    			
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result vistaPrincipal(Http.Request request) {
    	DynamicForm form = formFactory.form().bindFromRequest(request);
   		if (form.hasErrors()) {
   			return ok(mensajes.render("/",msgErrorFormulario));
       	}else {
       		String userName = form.get("userName");
       		String empresa = form.get("empresa");
       		String userKey = form.get("userKey");
       		String pais = form.get("pais");
       		
       		String gRecaptchaResponse = form.get("gRecaptchaResponse");
       		
       		
       		if(!empresa.equals("DEMO")) {
       			boolean verificado = false;
                try {
        			verificado = VerificarCaptcha.verificar(gRecaptchaResponse);
        		} catch (IOException e1) {
        			e1.printStackTrace();
        		}
                if(!verificado) {
                	String mensaje = "CAPTCHA NO VALIDO";
        			return ok(mensajes.render("/",mensaje));
                }
       		}
       		
       		
       		
       		
       		try {
				Connection con = dbWrite.getConnection();
				Inicio inicio = Inicio.findXempresaVigente(con,userName,empresa,pais);
				
				if(inicio.getId()==-1){
					String msg = "La empresa no esta disponible, si desea acceder, pongase en contacto con soporte pbarros@inqsol.com";
					con.close();
					return ok(mensajes.render("/",msg));
				}
				
				if(inicio.getId()==-2){
					String msg = "Los datos ingresados de empresa, no corresponden, vuelva a ingresarlos. En caso de continuar " +
								"este problema, por favor contactar con soporte pbarros@inqsol.com";
					con.close();
					return ok(mensajes.render("/",msg));
				}
				
				if(inicio.getId()==-3){
					String msg = "El usuario no esta vigente o no existe en la empresa, vuelva a ingresar. En caso de continuar " +
								"este problema, por favor contactar con soporte pbarros@inqsol.com";
					con.close();
					return ok(mensajes.render("/",msg));
				}
				
				if(!(inicio.getUserKey().equals(userKey) && inicio.getEmpresa().toLowerCase().equals(empresa.toLowerCase()))){
					String msg = "La clave ingresada no corresponde, vuelva a ingresar. En caso de continuar " +
							"este problema, por favor contactar con soporte pbarros@inqsol.com";
					con.close();
					return ok(mensajes.render("/",msg));
				}else {
					
					Map<String,String> mapeoPermiso = HomeController.mapPermisos(inicio.getBaseDato(), inicio.getId_tipoUsuario().toString());
					
					boolean esPorSucursal = Sucursal.esPorSucursal(mapeoPermiso, inicio.getId_tipoUsuario().toString());
	    			String aplicaPorSucursal = "0";
	    			if(esPorSucursal) {
	    				aplicaPorSucursal = "1";
	    			}
	    			
					if(mapeoPermiso.get("odoVentasAppWeb")!=null) {
						Registro.accesos(con, inicio.getBaseDato(), userName);
						
						if(mapeoPermiso.get("odoVentas")==null && mapeoPermiso.get("odoVentasAppWeb")==null) {
		    				con.close();
		    				return ok(mensajes.render("/odo",msgSinPermiso));
		    			}
						
						con.close();
						return redirect("/homeOdoVentasWeb/")
								.addingToSession(request, "userName", inicio.getUserName())
								.addingToSession(request, "id_usuario", inicio.getId().toString())
								.addingToSession(request, "baseDato", inicio.getBaseDato())
								.addingToSession(request, "id_tipoUsuario", inicio.getId_tipoUsuario().toString())
								.addingToSession(request, "porProyecto", inicio.getPorProyecto().toString())
								.addingToSession(request, "id_sucursal", inicio.getId_sucursal().toString())
								.addingToSession(request, "aplicaPorSucursal", aplicaPorSucursal);
						
					}else if(mapeoPermiso.get("odoAutorizadorAppWeb")!=null) {
						Registro.accesos(con, inicio.getBaseDato(), userName);
						con.close();
						return redirect("/homeOdoAutorizaVentasWeb/")
								.addingToSession(request, "userName", inicio.getUserName())
								.addingToSession(request, "id_usuario", inicio.getId().toString())
								.addingToSession(request, "baseDato", inicio.getBaseDato())
								.addingToSession(request, "id_tipoUsuario", inicio.getId_tipoUsuario().toString())
								.addingToSession(request, "porProyecto", inicio.getPorProyecto().toString())
								.addingToSession(request, "id_sucursal", inicio.getId_sucursal().toString())
								.addingToSession(request, "aplicaPorSucursal", aplicaPorSucursal);
	    			}else {
	    				Registro.accesos(con, inicio.getBaseDato(), userName);
	    				con.close();
	    				
	    				return redirect("/home/")
	    						.addingToSession(request, "userName", inicio.getUserName())
								.addingToSession(request, "id_usuario", inicio.getId().toString())
								.addingToSession(request, "baseDato", inicio.getBaseDato())
								.addingToSession(request, "id_tipoUsuario", inicio.getId_tipoUsuario().toString())
								.addingToSession(request, "porProyecto", inicio.getPorProyecto().toString())
								.addingToSession(request, "id_sucursal", inicio.getId_sucursal().toString())
								.addingToSession(request, "aplicaPorSucursal", aplicaPorSucursal);
	    			}
					
				}
       		} catch (SQLException e) {
				e.printStackTrace();
			}
       	}
   		return ok(mensajes.render("/",msgErrorFormulario));
    }
    
    public Result homeOdoVentasWeb(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		try {
    			Connection con = dbRead.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, userMnu.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			
    			if(mapeoPermiso.get("odoVentasAppWeb")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			
    			
    			String fechaAAMMDD = Fechas.hoy().getFechaStrAAMMDD();
    			List<OperadorServicio> listOperadores = OperadorServicio.allActivos(con, s.baseDato);
    			List<List<String>> listBodegasConEquipos = EquipoServicio.listaBodegasConEquipos(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
    			List<List<String>> listBodegasConServicios = ListaPrecioServicio.listaBodegasConServicios(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
    			
    			Map<String,List<String>> mapLista = new HashMap<String,List<String>>();
    			listBodegasConEquipos.forEach(x->{
    				mapLista.put(x.get(0), x);
    			});
    			listBodegasConServicios.forEach(x->{
    				mapLista.put(x.get(0), x);
    			});
    			
    			List<List<String>> lista = new ArrayList<List<String>>();
    			mapLista.forEach((k,v)->{
    				lista.add(v);
    			});
    			
    			//ORDENA LA LISTA
    			for(int j=0;j<lista.size();j++) {
    		        for(int i=0;i<lista.size()-j;i++) {
    		            if (i+1!=lista.size()&&lista.get(i).get(1).compareTo(lista.get(i+1).get(1))>0) {
    		                List<String> aux = new ArrayList<String>();
    		                aux=lista.get(i);
    		                lista.set(i,lista.get(i+1));
    		                lista.set(i+1, aux);
    		            }
    		        }
    		    }
    			
    			Long aux_huella = AuxHuella.findHuella(con, s.baseDato, userMnu.getId_usuario());
    			con.close();
    			return ok(viewsMnuOdoAppWeb.html.odoVentasWeb.render(mapeoDiccionario,mapeoPermiso,userMnu,fechaAAMMDD,listOperadores,lista,aux_huella));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result homeOdoAutorizaVentasWeb(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		try {
    			Connection con = dbRead.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, userMnu.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			
    			if(mapeoPermiso.get("odoAutorizadorAppWeb")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			
    			List<VentaServicio> listVentas = VentaServicio.allPorBodegas(con, s.baseDato, mapeoPermiso, s.aplicaPorSucursal,s.id_sucursal, 
    					Long.parseLong(s.id_usuario));
    			con.close();
    			return ok(odoAutorizaListarVentasWeb.render(mapeoDiccionario,mapeoPermiso,userMnu,listVentas));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result homeOdoAutorizaVentasWebExcel(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
			DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("");
	       	}else {
	       		try {
		       		Connection con = dbRead.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, userMnu.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			List<VentaServicio> listVentas = VentaServicio.allPorBodegas(con, s.baseDato, mapeoPermiso, s.aplicaPorSucursal,s.id_sucursal, 
	    					Long.parseLong(s.id_usuario));
	    			File file = MnuOdoAppWeb.exportaExcelOdoListarVentasWeb(s.baseDato, mapeoDiccionario, listVentas);
	    			con.close();
	    			return ok(file,false,Optional.of("Lista_de_report.xlsx"));
	       		} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok("");
	       	}
		}else {
			return ok("");
		}
    }
    
    
    // ================================================================================
    // COGNITO INICIOADMIN
    // ================================================================================
    
    public Result login(Http.Request request) {
    	String client_id = config.getString("com.inqsol.cognito.client_id");
    	String redirect = config.getString("com.inqsol.cognito.redirect");
    	String login_url = config.getString("com.inqsol.cognito.login_url");
    	String url = null;
		try {
			url = login_url+"/login?response_type=code&client_id="+client_id+"&redirect_uri="+URLEncoder.encode(redirect, "UTF-8")+"&state=basededdatoscodificada";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    	return redirect(url);
    }
    
    public Result callback(Http.Request request, String code, String state ) {
    	String secret = config.getString("com.inqsol.cognito.secret");
    	String client_id = config.getString("com.inqsol.cognito.client_id");
    	String redirect = config.getString("com.inqsol.cognito.redirect");
    	String login_url = config.getString("com.inqsol.cognito.login_url");
    	try {
			String dataPost = "grant_type=authorization_code&client_id="+client_id+"&client_secret="+secret+"&code="+code+"&redirect_uri="+redirect;
			return ws.url(login_url+"/oauth2/token").setContentType("application/x-www-form-urlencoded").post(dataPost).thenApply(
			(WSResponse response) -> {
				return redirect("/vistaAdminCallback").addingToSession(request, "administrator", "1");
        }).toCompletableFuture().get();
		} catch (Exception e) {
			e.printStackTrace();
			return ok("SE PRESENTO UN ERROR");
		}
    }

    public Result vistaAdminCallback(Http.Request request) {
       		try {
    			Connection con =dbRead.getConnection();
    			List<List<String>> proyectos = Admin.listaProyectos(con);
    			con.close();
    			return ok(vistaAdmin.render(proyectos)).addingToSession(request, "administrador", "esAdmin");
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
       		return ok(mensajes.render("/",msgSinPermiso)).withNewSession();
    }
    
			
    
    public Result inicio2Admin(String baseDato, Http.Request request) {
    	try {
    		String administrador = request.session().get("administrador").get();
    		if(administrador.equals("esAdmin")) {
    			Connection con = dbWrite.getConnection();
        		Usuario usuario = Usuario.findXIdUser(con, baseDato, (long) 1);
        		Long esMoroso = Registro.esMoroso(con, baseDato);
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(baseDato, usuario.getId().toString());
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(baseDato);
    			boolean esPorSucursal = Sucursal.esPorSucursal(mapeoPermiso, usuario.getId_tipoUsuario().toString());
    			String aplicaPorSucursal = "0";
    			if(esPorSucursal) {
    				aplicaPorSucursal = "1";
    			}
    			UserMnu userMnu = new UserMnu(usuario.getUserName(), usuario.getId().toString(), usuario.getId_tipoUsuario().toString(), baseDato, usuario.getId_sucursal().toString(), 
    					usuario.getPorProyecto().toString(),aplicaPorSucursal);
    			
    			con.close();
    			return ok(vistaPrincipal.render(
    					mapeoDiccionario,
    					mapeoPermiso,
    					userMnu,
    					esMoroso))
    					.addingToSession(request, "userName", usuario.getUserName())
    					.addingToSession(request, "id_usuario", usuario.getId().toString())
    					.addingToSession(request, "baseDato", baseDato)
    					.addingToSession(request, "id_tipoUsuario", usuario.getId_tipoUsuario().toString())
    					.addingToSession(request, "porProyecto", usuario.getPorProyecto().toString())
    					.addingToSession(request, "id_sucursal", usuario.getId_sucursal().toString())
    					.addingToSession(request, "aplicaPorSucursal", aplicaPorSucursal);
    			
    		}else {
    			return ok(mensajes.render("/",msgSinPermiso)).withNewSession();
    		}
		}catch (Exception e) {
			e.printStackTrace();
			return ok(mensajes.render("/",msgSinPermiso)).withNewSession();
		}
    }
    
    public Result usuarioModalUpdate2(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && !s.id_usuario.equals("1")) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
        	if (form.hasErrors()) {
        		return ok("error");
        	}else {
        		try {
	        		Connection con = dbRead.getConnection();
	        		String vista = Usuario.vistaUpdateUsuario2(con, s.baseDato, Long.parseLong(s.id_usuario));
	        		con.close();
	        		return ok(vista);
        		} catch (SQLException e) {
        			e.printStackTrace();
        		}
        		return ok("error");
        	}
    	}else {
    		return ok("noCorresponde");
    	}
    }
    
    public Result usuarioValida2(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && !s.id_usuario.equals("1")) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
        	if (form.hasErrors()) {
        		return ok("error");
        	}else {
        		String userName = form.get("userName").trim();
        		try {
	        		Connection con = dbRead.getConnection();
	        		if(Usuario.existe(con, s.baseDato, userName)) {
	        			con.close();
	        			return ok("{ \"status\": true}").as("application/json");
	        		}else{
	        			con.close();
	        			return ok("{ \"status\": false}").as("application/json");
	        		}
        		} catch (SQLException e) {
        			e.printStackTrace();
        		}
        		return ok("error");
        	}
    	}else {
    		return ok("noCorresponde");
    	}
    }
    
    public Result usuarioUpdate2(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && !s.id_usuario.equals("1")) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
        	if (form.hasErrors()) {
        		return ok(mensajes.render("/",msgError));
        	}else {
        		Long id = Long.parseLong(s.id_usuario);
        		String userName = form.get("userName").trim();
        		String userKey = form.get("userKey").trim();
        		String nombre = form.get("nombre").trim();
        		String cargo = form.get("cargo").trim();
        		String email = form.get("email").trim();
        		String fono = form.get("fono").trim();
        		String observaciones = form.get("observaciones").trim();
        		try {
	        		Connection con = dbWrite.getConnection();
	        		Usuario aux = Usuario.findXIdUser(con, s.baseDato, id);
	        		Usuario usuario = new Usuario(id, userName, userKey, nombre, cargo, aux.getId_tipoUsuario(),aux.getPorProyecto(), "", "", email, "", fono, observaciones,(long)1,aux.getId_sucursal(), aux.getNameSucursal());
	        		if(Usuario.modify(con, s.baseDato, usuario)) {
	        			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "usuario", Long.parseLong(s.id_usuario), "update", "Usuario actualiza sus datos");
	        			con.close();
	        			return redirect("/home/");
	        		}
        		} catch (SQLException e) {
        			e.printStackTrace();
        		}
        		return ok(mensajes.render("/",msgError));
        	}
    	}else {
    		return ok("noCorresponde");
    	}
    }
    
    //=============================================
    // RECUPERAR CLAVE
    //=============================================

    public Result recuperarClave(Http.Request request) {
    	DynamicForm form = formFactory.form().bindFromRequest(request);
   		if (form.hasErrors()) {
   			return ok(mensajes.render("/",msgErrorFormulario));
       	}else {
       		String userName = form.get("userName");
       		String empresa = form.get("empresa");
       		String eMail = form.get("eMail");
       		String pais = form.get("pais");
       		
       		String gRecaptchaResponse = form.get("gRecaptchaResponse");
       		
       		
       		if(!empresa.equals("ALTRAD RMDK CHILE")) {
       			boolean verificado = false;
                try {
        			verificado = VerificarCaptcha.verificar(gRecaptchaResponse);
        		} catch (IOException e1) {
        			e1.printStackTrace();
        		}
                if(!verificado) {
                	String mensaje = "CAPTCHA NO VALIDO";
        			return ok(mensajes.render("/",mensaje));
                }
       		}
       		
       		
       		
       		try {
				Connection con = dbRead.getConnection();
				Inicio inicio = Inicio.findXempresaVigente(con,userName,empresa,pais);
				
				if(inicio.getId()==-1){
					String msg = "La empresa no esta disponible, si desea acceder, pongase en contacto con soporte pbarros@inqsol.com";
					con.close();
					return ok(mensajes.render("/",msg));
				}
				
				if(inicio.getId()==-2){
					String msg = "Los datos ingresados de empresa, no corresponden, vuelva a ingresarlos. En caso de continuar " +
								"este problema, por favor contactar con soporte pbarros@inqsol.com";
					con.close();
					return ok(mensajes.render("/",msg));
				}
				
				if(inicio.getId()==-3){
					String msg = "El usuario no esta vigente o no existe en la empresa, vuelva a ingresar. En caso de continuar " +
								"este problema, por favor contactar con soporte pbarros@inqsol.com";
					con.close();
					return ok(mensajes.render("/",msg));
				}
				
				if(!(inicio.getEmail().trim().toLowerCase().equals(eMail.trim().toLowerCase()) && inicio.getEmpresa().toLowerCase().equals(empresa.toLowerCase()))){
					String msg = "El correo electrónico no esta registrado o no es coincidente, por favor contactar con soporte pbarros@inqsol.com";
					con.close();
					return ok(mensajes.render("/",msg));
				}else {
					
					Email email = new Email();
		    		String asunto = "Recuperación de clave de MADA";
		   			String desde = "desde MADA <informaciones@inqsol.cl>";
		   			email.setSubject(asunto);
					email.setFrom(desde);
					email.setBodyHtml("<html><body>" +
							" <p><b>CREDENCIALES</b></p>" +
							" <p>PAIS: <b>"+pais.trim().toUpperCase()+"</b></p>" +
		   		    		" <p>EMPRESA: <b>"+empresa.trim().toUpperCase()+"</b></p>" +
		   		    		" <p>USUARIO: <b>"+userName.trim().toUpperCase()+"</b></p>" +
		   		    		" <p>CLAVE: <b>"+inicio.getUserKey()+"</b></p>" +
		   		    		" <br><br>" +
		   		    		" <p>Nota: por favor no responder este correo</p>" +
		   		    		" </body></html>");
					email.addTo(eMail);
		   		    mailerClient.send(email);
		   		    String msg = "LISTO: LA CLAVE FUE ENVIADA A SU CORREO";
		   		    con.close();
		   		    return ok(mensajes.render("/",msg));
					
				}
				
       		} catch (SQLException e) {
				e.printStackTrace();
			}
       	}
   		return ok(mensajes.render("/",msgErrorFormulario));
    }
    
    
    
    
    

}
