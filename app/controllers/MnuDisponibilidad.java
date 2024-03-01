package controllers;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import controllers.HomeController.Sessiones;
import models.tables.BodegaEmpresa;
import models.tables.Cronograma;
import models.tables.Equipo;
import models.tables.Sucursal;
import models.utilities.Registro;
import models.utilities.UserMnu;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.Database;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.mensajes;
import viewsMnuDisponibilidad.html.*;

public class MnuDisponibilidad extends Controller {
	public static Database db = HomeController.dbWrite;
	public static FormFactory formFactory = HomeController.formFactory;
	public static String msgError = HomeController.msgError;
	public static String msgErrorFormulario = HomeController.msgErrorFormulario;
	public static String msgSinPermiso = HomeController.msgSinPermiso;
	
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	
	
	//============================================================
    // MNU disponibleActualizaAll   Disponibilidad/disponibleActualizaAll
    //============================================================
	
	public Result disponibleActualizaAll(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("disponibleActualizaAll")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			boolean esPorSucursal = Sucursal.esPorSucursal(mapeoPermiso, s.id_tipoUsuario);
    			List<Cronograma> listaDisponibilidad = Cronograma.all(con, s.baseDato, esPorSucursal, s.id_sucursal);
    			con.close();
    			return ok(disponibleActualizaAll.render(mapeoDiccionario,mapeoPermiso,userMnu,listaDisponibilidad));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result cambiaFechaDisponibleAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	Long id_movimiento = Long.parseLong(form.get("id_movimiento").trim());
	    	  	Long id_bodega = Long.parseLong(form.get("id_bodega").trim());
	    	  	Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
	    	  	String fecha = form.get("fecha");
				try {
	    			Connection con = db.getConnection();
	    			String hoy = Cronograma.actualizaFecha(con, s.baseDato, id_movimiento, id_bodega, id_equipo, fecha);
	    			con.close();
	    			return ok(hoy);
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
				return ok("error");
	       	}
    	}else {
    		return ok("error");
    	}
	}
	
	//============================================================
    // MNU reportDisponibilidadAll   Disponibilidad/Reporte
    //============================================================
	
	public Result reportDisponibilidadAll(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("reportDisponibilidadAll")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			boolean esPorSucursal = Sucursal.esPorSucursal(mapeoPermiso, s.id_tipoUsuario);
    			List<Cronograma> listaDisponibilidad = Cronograma.all(con, s.baseDato, esPorSucursal, s.id_sucursal);
    			String cartaGantt = Cronograma.cartaGanttAll(con, s.baseDato,listaDisponibilidad);
    			con.close();
    			return ok(reportDisponibilidadAll.render(mapeoDiccionario,mapeoPermiso,userMnu,listaDisponibilidad,cartaGantt));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reportDisponibilidadAllExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			boolean esPorSucursal = Sucursal.esPorSucursal(mapeoPermiso, s.id_tipoUsuario);
	    			List<Cronograma> listaDisponibilidad = Cronograma.all(con, s.baseDato, esPorSucursal, s.id_sucursal);
	    			File file = Cronograma.reportDisponibilidadAllExcel(s.baseDato, mapeoDiccionario, listaDisponibilidad);
	    			if(file!=null) {
		       			con.close();
		       			return ok(file,false,Optional.of("DisponibilidadEquipos.xlsx"));
		       		}else {
		       			con.close();
		       			return ok("");
		       		}
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok("");
	       	}
    	}else {
    		return ok("");
    	}
	}
	
	//====================================================================
    // MNU disponibleActualizaAll   Disponibilidad/Inscribir Equipos
    //====================================================================
	
	public Result disponibleInscribirEquipo(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("disponibleActualizaAll")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<Cronograma> listadoNoInscritos = Cronograma.allNOInscritosEquipo(con, s.baseDato);
    			con.close();
    			return ok(disponibleInscribirEquipo.render(mapeoDiccionario,mapeoPermiso,userMnu,listadoNoInscritos));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result disponibleInscribirEquipo2(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
	       		try {
	    			Connection con = db.getConnection();
	    			Cronograma.addEquipo(con, s.baseDato, id_equipo);
	    			Equipo equipo = Equipo.find(con, s.baseDato, id_equipo);
	    			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "cronograma", id_equipo, "insert", "inscribe equipo en Cronograma codigo = "+equipo.getCodigo());
	    			con.close();
	    			return redirect("/disponibleInscribirEquipo/");
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	//====================================================================
    // MNU disponibleActualizaAll   Disponibilidad/Desinscribir Equipos
    //====================================================================
	
	public Result disponibleEliminarEquipo(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("disponibleActualizaAll")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			
    			List<Cronograma> listadoInscritos = Cronograma.allEquiposInscritos(con, s.baseDato);
    			con.close();
    			return ok(disponibleEliminarEquipo.render(mapeoDiccionario,mapeoPermiso,userMnu,listadoInscritos));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result disponibleEliminarEquipo2(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
	       		try {
	    			Connection con = db.getConnection();
	    			Cronograma.dropEquipo(con, s.baseDato, id_equipo);
	    			Equipo equipo = Equipo.find(con, s.baseDato, id_equipo);
	    			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "cronograma", id_equipo, "delete", "des-inscribe equipo en Cronograma codigo= "+equipo.getCodigo());
	    			con.close();
	    			return redirect("/disponibleEliminarEquipo/");
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	//====================================================================
    // MNU disponibleActualizaAll   Disponibilidad/Inscribir Bodegas
    //====================================================================
	
	public Result disponibleInscribirBodega(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("disponibleActualizaAll")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			boolean esPorSucursal = Sucursal.esPorSucursal(mapeoPermiso, s.id_tipoUsuario);
    			List<Cronograma> listadoNoInscritosBod = Cronograma.allNOInscritosBodega(con, s.baseDato, esPorSucursal, s.id_sucursal);
    			con.close();
    			return ok(disponibleInscribirBodega.render(mapeoDiccionario,mapeoPermiso,userMnu,listadoNoInscritosBod));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result disponibleInscribirBodega2(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
	       		try {
	    			Connection con = db.getConnection();
	    			Cronograma.addBodega(con, s.baseDato, id_bodegaEmpresa);
	    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
	    			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "cronograma", id_bodegaEmpresa, "insert", "inscribe bodega en Cronograma = "+bodega.getNombre());
	    			con.close();
	    			return redirect("/disponibleInscribirBodega/");
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	//====================================================================
    // MNU disponibleActualizaAll   Disponibilidad/Desinscribir Bodegas
    //====================================================================
	
	public Result disponibleEliminarBodega(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("disponibleActualizaAll")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			boolean esPorSucursal = Sucursal.esPorSucursal(mapeoPermiso, s.id_tipoUsuario);
    			List<Cronograma> listadoInscritosBod = Cronograma.allBodegasInscritas(con, s.baseDato, esPorSucursal, s.id_sucursal);
    			con.close();
    			return ok(disponibleEliminarBodega.render(mapeoDiccionario,mapeoPermiso,userMnu,listadoInscritosBod));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result disponibleEliminarBodega2(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
	       		try {
	    			Connection con = db.getConnection();
	    			Cronograma.dropBodega(con, s.baseDato, id_bodegaEmpresa);
	    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
	    			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "cronograma", id_bodegaEmpresa, "delete", "des-inscribe bodega en Cronograma = "+bodega.getNombre());
	    			con.close();
	    			return redirect("/disponibleEliminarBodega/");
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
}
