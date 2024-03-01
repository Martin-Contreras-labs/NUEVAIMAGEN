package controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Map;

import controllers.HomeController.Sessiones;
import models.utilities.UserMnu;
import play.data.FormFactory;
import play.db.Database;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.mensajes;
import viewsMnuAyuda.html.*;

public class MnuAyuda extends Controller {
	public static Database db = HomeController.dbWrite;
	public static FormFactory formFactory = HomeController.formFactory;
	public static String msgError = HomeController.msgError;
	public static String msgErrorFormulario = HomeController.msgErrorFormulario;
	public static String msgSinPermiso = HomeController.msgSinPermiso;
	
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	
	
	//============================================================
    // MNU Ayuda
    //============================================================
	
	public Result manualBasico(Http.Request request) {
		Sessiones s = new Sessiones(request);
		if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
        		con.close();
        		return ok(manualBasico.render(mapeoDiccionario,mapeoPermiso,userMnu));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    	return ok(mensajes.render("/",msgError));
	}
	
	public Result manualPrimerosPasos(Http.Request request) {
		Sessiones s = new Sessiones(request);
		if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
        		con.close();
        		return ok(manualPrimerosPasos.render(mapeoDiccionario,mapeoPermiso,userMnu));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    	return ok(mensajes.render("/",msgError));
	}
	
	
	
	
	
	
}
