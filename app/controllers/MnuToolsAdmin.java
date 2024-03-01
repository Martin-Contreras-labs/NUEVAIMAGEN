package controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import controllers.HomeController.Sessiones;
import models.tables.EmisorTributario;
import models.tables.Parametros;
import models.utilities.UserMnu;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.Database;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.mensajes;
import viewsMnuToolsAdmin.html.*;

public class MnuToolsAdmin extends Controller {
	public static Database db = HomeController.dbWrite;
	public static FormFactory formFactory = HomeController.formFactory;
	public static String msgError = HomeController.msgError;
	public static String msgErrorFormulario = HomeController.msgErrorFormulario;
	public static String msgSinPermiso = HomeController.msgSinPermiso;
	
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	
	
	//============================================================
    // MNU Admin   Tools
    //============================================================
	
	public Result administraModulos(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(userMnu.getUserName().equals("Admin") || userMnu.getUserName().equals("PBA")) {
    				List<Parametros> lista = Parametros.all(con, s.baseDato);
        			con.close();
        			return ok(administraModulos.render(mapeoDiccionario,mapeoPermiso,userMnu,lista));
    			}
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    	return ok(mensajes.render("/",msgError));
	}
	
	public Result modificarModulosAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long valor = Long.parseLong(form.get("valor"));
	       		String nombreParametro = form.get("nombreParametro");
    			try {
	    			Connection con = db.getConnection();
	    			Parametros.modify(con, s.baseDato, nombreParametro, valor);
	    			con.close();
	    			return ok("OK");
    			} catch (SQLException e) {
	    			e.printStackTrace();
	    		};
    		}
		}
    	return ok("ERROR");
	}
	
	public Result administrarEmisor(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(userMnu.getUserName().equals("Admin") || userMnu.getUserName().equals("PBA")) {
    				EmisorTributario emisor = EmisorTributario.find(con, s.baseDato);
        			con.close();
        			return ok(administrarEmisor.render(mapeoDiccionario,mapeoPermiso,userMnu,emisor));
    			}
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    	return ok(mensajes.render("/",msgError));
	}
	
	public Result modificarEmisor(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		EmisorTributario form = formFactory.form(EmisorTributario.class).withDirectFieldAccess(true).bindFromRequest(request).get();
    		if (form.id == null) {
    			return ok(mensajes.render("/",msgErrorFormulario));
    		}else {
	    		try {
	    			Connection con = db.getConnection();
	    			EmisorTributario.update(con, s.baseDato, form);
	    			con.close();
	    			return redirect("/home/");
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    	return ok(mensajes.render("/",msgError));
    }
	
	
	
	
	
}
