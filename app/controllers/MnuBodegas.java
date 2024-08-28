package controllers;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import controllers.HomeController.Sessiones;
import models.forms.FormBodegaGraba;
import models.forms.FormContactoBodegaGraba;
import models.tables.BodegaEmpresa;
import models.tables.Cliente;
import models.tables.Comercial;
import models.tables.ContactoBodegaEmpresa;
import models.tables.DctoEquipo;
import models.tables.DctoGrupo;
import models.tables.Equipo;
import models.tables.Grupo;
import models.tables.ListaPrecio;
import models.tables.Moneda;
import models.tables.Proyecto;
import models.tables.Regiones;
import models.tables.Sucursal;
import models.tables.TasaEquipo;
import models.tables.TasaGrupo;
import models.tables.TipoBodega;
import models.tables.UnidadTiempo;
import models.utilities.Registro;
import models.utilities.UserMnu;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.Database;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.mensajes;
import viewsMnuBodegas.html.*;

public class MnuBodegas extends Controller {
	
	public static Database db = HomeController.dbWrite;
	public static FormFactory formFactory = HomeController.formFactory;
	public static String msgError = HomeController.msgError;
	public static String msgErrorFormulario = HomeController.msgErrorFormulario;
	public static String msgSinPermiso = HomeController.msgSinPermiso;
	
	
	
	//============================================================
    // MNU bodegaAdministrar   Bodegas/Administrar Bodegas
    //============================================================

    public Result bodegaAdministrar(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("bodegaAdministrar")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<List<String>> listBodegas = BodegaEmpresa.listaAllBodegasVigentesInternasExternas(con, s.baseDato, mapeoPermiso, s.aplicaPorSucursal, s.id_sucursal);
    			con.close();
    			return ok(bodegaAdministrar.render(mapeoDiccionario,mapeoPermiso,userMnu,listBodegas));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result bodegaCrear(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("bodegaAdministrar")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<Cliente> listClientes = Cliente.all(con, s.baseDato);
    			List<Proyecto> listProyectos = Proyecto.all(con, s.baseDato);
    			List<TipoBodega> listTipoBodega = TipoBodega.all(con, s.baseDato);
    			List<Regiones> listRegiones = Regiones.all(con, s.baseDato);
    			List<Sucursal> listSucursal = Sucursal.all(con, s.baseDato);
    			Sucursal sucursal = Sucursal.find(con, s.baseDato, s.id_sucursal);
    			List<Moneda> listMon = Moneda.allSinMonPrincipal(con, s.baseDato);
    			con.close();
    			return ok(bodegaCrear.render(mapeoDiccionario,mapeoPermiso,userMnu,listClientes,listProyectos,listTipoBodega,listRegiones,listSucursal,sucursal, s.aplicaPorSucursal, listMon));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result vistaSelectComercialAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	String id_sucursal = form.get("id_sucursal").trim();
				try {
	    			Connection con = db.getConnection();
	    			List<Comercial> listComercial = Comercial.allPorIdSucursalVig(con, s.baseDato, s.aplicaPorSucursal, id_sucursal);
	    			String vistaHtml = "<select class=\"custom-select\" name=\"id_comercial\">"
	    					+ "<option value=\"0\">Sin Comercial</option>";
	    				for(Comercial x: listComercial) {
	    					vistaHtml += "<option value=\""+x.getId()+"\">"+x.getNameUsuario()+"</option>";
	    				}
	    				vistaHtml += "</select>";
	    			con.close();
	    			return ok(vistaHtml);
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
				return ok("error");
	       	}
    	}else {
    		return ok("error");
    	}
	}
    
    public Result verificaNombreBodegaAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	String nombreBodega = form.get("nombreBodega").trim();
				try {
	    			Connection con = db.getConnection();
	    				if(BodegaEmpresa.existeBodega(con, s.baseDato, nombreBodega)) {
	    	    			con.close();
	    	    			return ok("existe");
	    				}else {
	    					con.close();
	    					return ok("");
	    				}
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
				return ok("error");
	       	}
    	}else {
    		return ok("error");
    	}
    }
    
    public Result bodegaGraba(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		FormBodegaGraba form = formFactory.form(FormBodegaGraba.class).withDirectFieldAccess(true).bindFromRequest(request).get();
    		if (form.id_bodegaEmpresa==null || form.nombre==null) {
    			return ok(mensajes.render("/",msgErrorFormulario));
    		}else {
    			try {
	    			Connection con = db.getConnection();
	    			if(BodegaEmpresa.create(con, s.baseDato, form)) {
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "bodegaEmpresa", (long)0, "create", "crea nueva bodega/almacen: "+form.nombre);
	    				con.close();
	    				return redirect("/bodegaAdministrar/");
	    			}else {
	    				con.close();
	    				return ok(mensajes.render("/",msgError));
	    			}
    			} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
    		}
    	}
    	return ok(mensajes.render("/",msgError));
    }
    
    public Result bodegaModificar(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	    	  	Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodega").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("bodegaAdministrar")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			BodegaEmpresa bodegaEmpresa = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
	    			List<Cliente> listClientes = Cliente.all(con, s.baseDato);
	    			List<Proyecto> listProyectos = Proyecto.all(con, s.baseDato);
	    			TipoBodega tipoBodega = TipoBodega.find(con, s.baseDato, bodegaEmpresa.getEsInterna());
	    			List<Regiones> listRegiones = Regiones.all(con, s.baseDato);
	    			List<Comercial> listComercial = Comercial.allPorIdSucursalVig(con, s.baseDato, s.aplicaPorSucursal, bodegaEmpresa.getId_sucursal().toString());
	    			List<Moneda> listMon = Moneda.allSinMonPrincipal(con, s.baseDato);
	    			List<List<String>> fijaTasas = BodegaEmpresa.listaFijaTasas(con, s.baseDato, id_bodegaEmpresa);
	    			List<Sucursal> listSucursal = Sucursal.all(con, s.baseDato);
	    			con.close();
	    			return ok(bodegaModificar.render(mapeoDiccionario,mapeoPermiso,userMnu,listClientes,listProyectos,tipoBodega,bodegaEmpresa,listRegiones,listComercial, 
	    					listMon, fijaTasas, listSucursal, s.aplicaPorSucursal));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result bodegaModifica(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		FormBodegaGraba form = formFactory.form(FormBodegaGraba.class).withDirectFieldAccess(true).bindFromRequest(request).get();
    		if (form.id_bodegaEmpresa==null || form.nombre==null) {
    			return ok(mensajes.render("/",msgErrorFormulario));
    		}else {
    			try {
	    			Connection con = db.getConnection();
	    			if(BodegaEmpresa.update(con, s.baseDato, form)) {
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "bodegaEmpresa", form.id_bodegaEmpresa, "update", "modifica datos de bodega: "+form.nombre);
	    				con.close();
	    				return redirect("/bodegaAdministrar/");
	    			}else {
	    				con.close();
	    				return ok(mensajes.render("/",msgError));
	    			}
    			} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
    		}
    	}
    	return ok(mensajes.render("/",msgError));
    }
    
    public Result bodegaElimina(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	    	  	Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodega").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			if(mapeoPermiso.get("bodegaAdministrar")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			if(BodegaEmpresa.estaEnUso(con, s.baseDato, id_bodegaEmpresa)) {
	    				String msg = "No es posible eliminar esta bodega, debido a que esta en uso";
	    				con.close();
	    				return ok(mensajes.render("/bodegaAdministrar/",msg));
	    			}else {
	    				BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
	    				if(BodegaEmpresa.delete(con, s.baseDato, id_bodegaEmpresa)) {
	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "bodegaEmpresa", id_bodegaEmpresa, "delete", "elimina bodega: "+bodega.getNombre());
	    					con.close();
	        				return redirect("/bodegaAdministrar/");
	    				}else {
	    					con.close();
	    					return ok(mensajes.render("/",msgError));
	    				}
	    			}
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    //============================================================
    // MNU bodegaAdministrar   Bodegas/Administrar Sucursales
    //============================================================
    
    public Result sucursalAdministrar(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			
    			if(mapeoPermiso.get("bodegaAdministrar")==null || mapeoPermiso.get("cambiarSucursal")==null) {
    				
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<Sucursal> listSucursales = Sucursal.all(con, s.baseDato);
    			con.close();
    			return ok(sucursalAdministrar.render(mapeoDiccionario,mapeoPermiso,userMnu,listSucursales));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result sucursalElimina(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	    	  	Long id_sucursal = Long.parseLong(form.get("id_sucursal").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			if(mapeoPermiso.get("bodegaAdministrar")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			if(Sucursal.estaEnUso(con, s.baseDato, id_sucursal)) {
	    				String msg = "No es posible eliminar esta sucursal, esta en uso con equipos vigentes ";
	    				con.close();
	    				return ok(mensajes.render("/routes2/sucursalAdministrar/",msg));
	    			}else {
	    				Sucursal sucursal = Sucursal.find(con, s.baseDato, id_sucursal.toString());
	    				if(Sucursal.delete(con, s.baseDato, id_sucursal)) {
	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "sucursal", id_sucursal, "delete", "elimina Sucursal "+sucursal.nombre);
	    					con.close();
	        				return redirect("/routes2/sucursalAdministrar/");
	    				}else {
	    					con.close();
	    					return ok(mensajes.render("/",msgError));
	    				}
	    			}
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result sucursalModifica(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	    	  	Long id_sucursal = Long.parseLong(form.get("id_sucursal").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("bodegaAdministrar")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			Sucursal sucursal = Sucursal.find(con, s.baseDato, id_sucursal.toString());
	    			con.close();
	    			return ok(sucursalModifica.render(mapeoDiccionario,mapeoPermiso,userMnu,sucursal));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result sucursalAgrega(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("bodegaAdministrar")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			con.close();
    			return ok(sucursalAgrega.render(mapeoDiccionario,mapeoPermiso,userMnu));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result sucursalNuevo(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String nombreSucursal = form.get("nombreSucursal").trim();
				try {
	    			Connection con = db.getConnection();
	    			if(Sucursal.existeSucursal(con, s.baseDato, nombreSucursal)) {
	    				String msg = "No es posible crear esta sucursal, el nombre de sucursal ya existe.";
	    				con.close();
	    				return ok(mensajes.render("/routes2/sucursalAdministrar/",msg));
	    			}else {
	    				if(Sucursal.create(con, s.baseDato, nombreSucursal)) {
	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "sucursal", (long)0, "create", "agrega nuevo sucursal: "+nombreSucursal);
	    					Sucursal sucursal = Sucursal.find(con, s.baseDato, "1");
	    					con.close();
	    					
	    					String msg = "Sucursal creada con exito, recuerde actualizar los precios en el maestro, por ahora se heredaron "
	    							+ " los precios desde la sucursal "+sucursal.getNombre();
		    				return ok(mensajes.render("/routes2/sucursalAdministrar/",msg));
	    				}else {
	    					con.close();
	    					return ok(mensajes.render("/",msgError));
	    				}
	    			}
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
				return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result modificaSucursalPorCampoAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String campo = form.get("campo").trim();
	       		Long id_sucursal = Long.parseLong(form.get("id_sucursal").trim());
	       		String valor = form.get("valor").trim();
				try {
	    			Connection con = db.getConnection();
	    			if(Sucursal.existeSucursal(con, s.baseDato, valor)) {
	    				con.close();
    	    			return ok("existe");
	    			}else {
	    				if(Sucursal.modificaPorCampo(con, s.baseDato, campo, id_sucursal, valor)) {
	    					Sucursal sucursal = Sucursal.find(con, s.baseDato, id_sucursal.toString());
	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "sucursal", id_sucursal, "update", "cambia nombre de la sucursal a "+sucursal.getNombre());
	    					con.close();
	    					return ok("");
	    				}
	    				con.close();
	    				return ok("error");
	    			}
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
    // MNU bodegaAsignaDctos   Bodegas/Asignar Descuentos
    //============================================================

    public Result bodegaAsignaDctos(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("bodegaAsignaDctos")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<List<String>> listBodegas = BodegaEmpresa.listaAllBodegasVigentesExternas(con, s.baseDato, mapeoPermiso, s.aplicaPorSucursal, s.id_sucursal);
    			con.close();
    			return ok(bodegaAsignaDctos.render(mapeoDiccionario,mapeoPermiso,userMnu,listBodegas));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result bodegaFijaDctos(Long id_bodegaEmpresa, Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("bodegaAsignaDctos")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			BodegaEmpresa bodegaEmpresa = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
    			List<DctoGrupo> listDctoGrupo = DctoGrupo.allXBodegaEmpresa(con, s.baseDato, id_bodegaEmpresa);
    			List<DctoEquipo> listDctoEquipo = DctoEquipo.allXBodegaEmpresa(con, s.baseDato, id_bodegaEmpresa);
    			List<Grupo> listGrupos = Grupo.all(con, s.baseDato);
    			List<Equipo> listEquipos = Equipo.allVigentes(con, s.baseDato);
    			con.close();
    			return ok(bodegaFijaDctos.render(mapeoDiccionario,mapeoPermiso,userMnu,bodegaEmpresa,listDctoGrupo,listDctoEquipo, listGrupos, listEquipos));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result modificarDctoArriendoAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	       		String modifica = form.get("modifica").trim();
	       		if(modifica.equals("dctoGlobal")) {
	       			Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
	       			Double dcto = Double.parseDouble(form.get("dcto").trim())/100;
	       			try {
		    			Connection con = db.getConnection();
		    				if(BodegaEmpresa.modificarBodegaPorCampo(con, s.baseDato, id_bodegaEmpresa, "tasaDescto", dcto.toString())) {
		    					BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
		    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "bodegaEmpresa", id_bodegaEmpresa, "update", "modifica tasa descuento global id_bodega: "+id_bodegaEmpresa+" de bodega: "+bodega.getNombre());
		    	    			con.close();
		    	    			return ok("");
		    				}else {
		    					con.close();
		    					return ok("error");
		    				}
					} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
	       		}
	       		if(modifica.equals("dctoGrupo")) {
	       			Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
	       			Long id_grupo = Long.parseLong(form.get("id_grupo").trim());
	       			Double dcto = Double.parseDouble(form.get("dcto").trim())/100;
	       			try {
		    			Connection con = db.getConnection();
		    				if(DctoGrupo.modificar(con, s.baseDato, id_bodegaEmpresa, id_grupo, dcto)) {
		    					BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
		    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "dctoGrupo", id_grupo, "update", "modifica tasa descuento grupo id_bodega: "+id_bodegaEmpresa+" de bodega: "+bodega.getNombre());
		    	    			con.close();
		    	    			return ok("");
		    				}else {
		    					con.close();
		    					return ok("error");
		    				}
					} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
	       		}
	       		if(modifica.equals("dctoEquipo")) {
	       			Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
	       			Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
	       			Long id_cotizacion = Long.parseLong(form.get("id_cotizacion").trim());
	       			Double dcto = Double.parseDouble(form.get("dcto").trim())/100;
	       			try {
		    			Connection con = db.getConnection();
		    				if(DctoEquipo.modificar(con, s.baseDato, id_bodegaEmpresa, id_equipo, id_cotizacion, dcto)) {
		    					BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
		    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "dctoEquipo", id_equipo, "update", "modifica tasa descuento equipo id_bodegaEmpresa: "+id_bodegaEmpresa+" de bodega: "+bodega.getNombre());
		    	    			con.close();
		    	    			return ok("");
		    				}else {
		    					con.close();
		    					return ok("error");
		    				}
					} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
	       		}
				return ok("error");
	       	}
    	}else {
    		return ok("error");
    	}
    }
    
    public Result eliminarDctoArriendoAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	       		String elimina = form.get("elimina").trim();
	       		if(elimina.equals("dctoGrupo")) {
	       			Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
	       			Long id_grupo = Long.parseLong(form.get("id_grupo").trim());
	       			try {
		    			Connection con = db.getConnection();
		    				if(DctoGrupo.eliminar(con, s.baseDato, id_bodegaEmpresa, id_grupo)) {
		    					BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
		    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "dctoGrupo", id_grupo, "delete", "elimina tasa descuento grupo id_bodegaEmpresa: "+id_bodegaEmpresa+" de bodega: "+bodega.getNombre());
		    	    			con.close();
		    	    			return ok("");
		    				}else {
		    					con.close();
		    					return ok("error");
		    				}
					} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
	       		}
	       		if(elimina.equals("dctoEquipo")) {
	       			Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
	       			Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
	       			Long id_cotizacion = Long.parseLong(form.get("id_cotizacion").trim());
	       			try {
		    			Connection con = db.getConnection();
		    				if(DctoEquipo.eliminar(con, s.baseDato, id_bodegaEmpresa, id_equipo, id_cotizacion)) {
		    					BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
		    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "dctoEquipo", id_equipo, "delete", "elimina tasa descuento equipo id_bodegaEmpresa: "+id_bodegaEmpresa+" de bodega: "+bodega.getNombre());
		    	    			con.close();
		    	    			return ok("");
		    				}else {
		    					con.close();
		    					return ok("error");
		    				}
					} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
	       		}
				return ok("error");
	       	}
    	}else {
    		return ok("error");
    	}
    }
    
    public Result grabaDctoArriendoAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	       		String graba = form.get("graba").trim();
	       		if(graba.equals("dctoGrupo")) {
	       			Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
	       			Long id_grupo = Long.parseLong(form.get("id_grupo").trim());
	       			try {
		    			Connection con = db.getConnection();
		    				if(DctoGrupo.agregar(con, s.baseDato, id_bodegaEmpresa, id_grupo)) {
		    					BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
		    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "dctoGrupo", id_grupo, "create", "agrega tasa descuento grupo id_bodegaEmpresa: "+id_bodegaEmpresa+" de bodega: "+bodega.getNombre());
		    	    			con.close();
		    	    			return ok("");
		    				}else {
		    					con.close();
		    					return ok("error");
		    				}
					} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
	       		}
	       		if(graba.equals("dctoEquipo")) {
	       			Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
	       			Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
	       			Long id_cotizacion = Long.parseLong(form.get("id_cotizacion").trim());
	       			try {
		    			Connection con = db.getConnection();
		    				if(DctoEquipo.agregar(con, s.baseDato, id_bodegaEmpresa, id_equipo, id_cotizacion)) {
		    					BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
		    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "dctoEquipo", id_equipo, "create", "agrega tasa descuento equipo id_bodegaEmpresa: "+id_bodegaEmpresa+" de bodega: "+bodega.getNombre());
		    	    			con.close();
		    	    			return ok("");
		    				}else {
		    					con.close();
		    					return ok("error");
		    				}
					} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
	       		}
				return ok("error");
	       	}
    	}else {
    		return ok("error");
    	}
    }
    
    
    //============================================================
    // MNU bodegaAsignaTasas   Bodegas/Asignar Tasas
    //============================================================
    
    public Result bodegaAsignaTasas(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("bodegaAsignaTasas")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<List<String>> listBodegas = BodegaEmpresa.listaAllBodegasVigentesExternas(con, s.baseDato, mapeoPermiso, s.aplicaPorSucursal, s.id_sucursal);
    			con.close();
    			return ok(bodegaAsignaTasas.render(mapeoDiccionario,mapeoPermiso,userMnu,listBodegas));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result bodegaFijaTasas(Long id_bodegaEmpresa, Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("bodegaAsignaTasas")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			BodegaEmpresa bodegaEmpresa = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
    			List<TasaGrupo> listTasaGrupo = TasaGrupo.allXBodegaEmpresa(con, s.baseDato, id_bodegaEmpresa);
    			List<TasaEquipo> listTasaEquipo = TasaEquipo.allXBodegaEmpresa(con, s.baseDato, id_bodegaEmpresa);
    			List<Grupo> listGrupos = Grupo.all(con, s.baseDato);
    			List<Equipo> listEquipos = Equipo.allVigentes(con, s.baseDato);
    			con.close();
    			return ok(bodegaFijaTasas.render(mapeoDiccionario,mapeoPermiso,userMnu,bodegaEmpresa,listTasaGrupo,listTasaEquipo, listGrupos, listEquipos));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result modificarTasaArriendoAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	       		String modifica = form.get("modifica").trim();
	       		if(modifica.equals("tasaGlobal")) {
	       			Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
	       			Double tasa = Double.parseDouble(form.get("tasa").trim())/100;
	       			try {
		    			Connection con = db.getConnection();
		    				if(BodegaEmpresa.modificarBodegaPorCampo(con, s.baseDato, id_bodegaEmpresa, "tasaArriendo", tasa.toString())) {
		    					BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
		    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "bodegaEmpresa", id_bodegaEmpresa, "update", "modifica tasa arriendo global"+id_bodegaEmpresa+" de bodega: "+bodega.getNombre());
		    	    			con.close();
		    	    			return ok("");
		    				}else {
		    					con.close();
		    					return ok("error");
		    				}
					} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
	       		}
	       		if(modifica.equals("tasaGrupo")) {
	       			Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
	       			Long id_grupo = Long.parseLong(form.get("id_grupo").trim());
	       			Double tasa = Double.parseDouble(form.get("tasa").trim())/100;
	       			try {
		    			Connection con = db.getConnection();
		    				if(TasaGrupo.modificar(con, s.baseDato, id_bodegaEmpresa, id_grupo, tasa)) {
		    					BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
		    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "tasaGrupo", id_grupo, "update", "modifica tasa arriendo grupo id_bodegaEmpresa: "+id_bodegaEmpresa+" de bodega: "+bodega.getNombre());
		    	    			con.close();
		    	    			return ok("");
		    				}else {
		    					con.close();
		    					return ok("error");
		    				}
					} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
	       		}
	       		if(modifica.equals("tasaEquipo")) {
	       			Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
	       			Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
	       			Long id_cotizacion = Long.parseLong(form.get("id_cotizacion").trim());
	       			Double tasa = Double.parseDouble(form.get("tasa").trim())/100;
	       			try {
		    			Connection con = db.getConnection();
		    				if(TasaEquipo.modificar(con, s.baseDato, id_bodegaEmpresa, id_equipo, id_cotizacion, tasa)) {
		    					BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
		    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "tasaEquipo", id_equipo, "update", "modifica tasa arriendo equipo id_bodegaEmpresa: "+id_bodegaEmpresa+" de bodega: "+bodega.getNombre());
		    	    			con.close();
		    	    			return ok("");
		    				}else {
		    					con.close();
		    					return ok("error");
		    				}
					} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
	       		}
				return ok("error");
	       	}
    	}else {
    		return ok("error");
    	}
    }
    
    public Result eliminarTasaArriendoAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	       		String elimina = form.get("elimina").trim();
	       		if(elimina.equals("tasaGrupo")) {
	       			Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
	       			Long id_grupo = Long.parseLong(form.get("id_grupo").trim());
	       			try {
		    			Connection con = db.getConnection();
		    				if(TasaGrupo.eliminar(con, s.baseDato, id_bodegaEmpresa, id_grupo)) {
		    					BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
		    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "tasaGrupo", id_grupo, "delete", "elimina tasa arriendo grupo id_bodegaEmpresa: "+id_bodegaEmpresa+" de bodega: "+bodega.getNombre());
		    	    			con.close();
		    	    			return ok("");
		    				}else {
		    					con.close();
		    					return ok("error");
		    				}
					} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
	       		}
	       		if(elimina.equals("tasaEquipo")) {
	       			Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
	       			Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
	       			Long id_cotizacion = Long.parseLong(form.get("id_cotizacion").trim());
	       			try {
		    			Connection con = db.getConnection();
		    				if(TasaEquipo.eliminar(con, s.baseDato, id_bodegaEmpresa, id_equipo, id_cotizacion)) {
		    					BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
		    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "tasaEquipo", id_equipo, "delete", "elimina tasa arriendo equipo id_bodegaEmpresa: "+id_bodegaEmpresa+" de bodega: "+bodega.getNombre());
		    	    			con.close();
		    	    			return ok("");
		    				}else {
		    					con.close();
		    					return ok("error");
		    				}
					} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
	       		}
				return ok("error");
	       	}
    	}else {
    		return ok("error");
    	}
    }
    
    public Result grabaTasaArriendoAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	       		String graba = form.get("graba").trim();
	       		if(graba.equals("tasaGrupo")) {
	       			Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
	       			Long id_grupo = Long.parseLong(form.get("id_grupo").trim());
	       			try {
		    			Connection con = db.getConnection();
		    				if(TasaGrupo.agregar(con, s.baseDato, id_bodegaEmpresa, id_grupo)) {
		    					BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
		    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "tasaGrupo", id_grupo, "create", "agrega tasa arriendo grupo id_bodegaEmpresa: "+id_bodegaEmpresa+" de bodega: "+bodega.getNombre());
		    	    			con.close();
		    	    			return ok("");
		    				}else {
		    					con.close();
		    					return ok("error");
		    				}
					} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
	       		}
	       		if(graba.equals("tasaEquipo")) {
	       			Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
	       			Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
	       			Long id_cotizacion = Long.parseLong(form.get("id_cotizacion").trim());
	       			try {
		    			Connection con = db.getConnection();
		    				if(TasaEquipo.agregar(con, s.baseDato, id_bodegaEmpresa, id_equipo, id_cotizacion)) {
		    					BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
		    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "tasaEquipo", id_equipo, "create", "agrega tasa arriendo equipo id_bodegaEmpresa: "+id_bodegaEmpresa+" de bodega: "+bodega.getNombre());
		    	    			con.close();
		    	    			return ok("");
		    				}else {
		    					con.close();
		    					return ok("error");
		    				}
					} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
	       		}
				return ok("error");
	       	}
    	}else {
    		return ok("error");
    	}
    }
    
    
    //============================================================
    // MNU bodegaPrecios   Bodegas/Precios por Bodega
    //============================================================
    
    public Result bodegaPrecios(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("bodegaPrecios")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<List<String>> listBodegas = BodegaEmpresa.listaAllBodegasVigentesExternas(con, s.baseDato, mapeoPermiso, s.aplicaPorSucursal, s.id_sucursal);
    			con.close();
    			return ok(bodegaPrecios.render(mapeoDiccionario,mapeoPermiso,userMnu,listBodegas));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result bodegaListaPrecios(Long id_bodegaEmpresa, Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("bodegaPrecios")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
    			List<ListaPrecio> listPrecios = ListaPrecio.allXBodega(con, s.baseDato, id_bodegaEmpresa, mapeoDiccionario.get("pais"));
    			List<Moneda> listMoneda = Moneda.all(con, s.baseDato);
    			List<UnidadTiempo> listUnidadTiempo = UnidadTiempo.all(con, s.baseDato);
    			con.close();
    			return ok(bodegaListaPrecios.render(mapeoDiccionario,mapeoPermiso,userMnu,bodega,listPrecios,listMoneda,listUnidadTiempo));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result actualizaListaPrecioAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
	    	DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	       		Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
	       		Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
	       		Long id_cotizacion = Long.parseLong(form.get("id_cotizacion").trim());
	       		String id_moneda = form.get("id_moneda").trim();
	       		String campo = form.get("campo").trim();
	       		String valor = form.get("valor").replaceAll(",", "").trim();
	       		
	       		if(campo.equals("NM")) campo = "id_moneda";
				if(campo.equals("PV")) campo = "precioVenta";
				if(campo.equals("PA")) campo = "precioArriendo";
				if(campo.equals("UT")) campo = "id_unidadTiempo";
				if(campo.equals("PM")) campo = "precioMinimo";
				if(campo.equals("PE")) campo = "permanenciaMinima";
				try {
	    			Connection con = db.getConnection();
	    			String fecha = ListaPrecio.modifyPorCampo(con, s.baseDato, campo, valor, id_bodegaEmpresa, id_equipo, id_cotizacion);
	    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
	    			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "listaPrecio", (long)0, "update", "cambia lista precio de bodega en campo:"+campo+
	    					" de idBod_idEq_idCot="+id_bodegaEmpresa+"_"+id_equipo+"_"+id_cotizacion+" de bodega: "+bodega.getNombre());
	    			int numDec = Moneda.numeroDecimalxId(con, s.baseDato, id_moneda);
	    			con.close();
	    			return ok("{ \"status\": true, \"fecha\": \""+fecha+"\", \"numDec\": \""+numDec+"\"}").as("application/json");
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
				return ok("{ \"status\": false}").as("application/json");
	       	}
    	}
    	return ok("error");
    }
    
    //============================================================
    // MNU bodegaFactorViga   Bodegas/Factor Viga (m2/ml)
    //============================================================
    
    public Result bodegaFactorViga(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("bodegaFactorViga")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<List<String>> listBodegas = BodegaEmpresa.listaAllBodegasVigentesExternas(con, s.baseDato, mapeoPermiso, s.aplicaPorSucursal, s.id_sucursal);
    			con.close();
    			return ok(bodegaFactorViga.render(mapeoDiccionario,mapeoPermiso,userMnu,listBodegas));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result actualizaFactorVigaAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
	    	DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	       		Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
       			Double factorM2Viga = Double.parseDouble(form.get("factorM2Viga").trim());
       			try {
	    			Connection con = db.getConnection();
	    				if(BodegaEmpresa.modificarBodegaPorCampo(con, s.baseDato, id_bodegaEmpresa, "factorM2Viga", factorM2Viga.toString())) {
	    					BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "bodegaEmpresa", id_bodegaEmpresa, "update", "modifica factor m2/ml de viga"+" de bodega: "+bodega.getNombre());
	    	    			con.close();
	    	    			return ok("");
	    				}else {
	    					con.close();
	    					return ok("error");
	    				}
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
       			return ok("error");
	       	}
    	}
    	return ok("error");
    }
    
    //============================================================
    // MNU bodegaContactos   Bodegas/Administrar Contactos
    //============================================================
    
    public Result bodegaContactos(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("bodegaContactos")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<List<String>> listBodegas = BodegaEmpresa.listaAllBodegasVigentesInternasExternas(con, s.baseDato, mapeoPermiso, s.aplicaPorSucursal, s.id_sucursal);
    			con.close();
    			return ok(bodegaContactos.render(mapeoDiccionario,mapeoPermiso,userMnu,listBodegas));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result bodegaContactosExcel(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("bodegaContactos")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<List<String>> listContactos = ContactoBodegaEmpresa.all(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
    			File file = ContactoBodegaEmpresa.contactoBodegasExcel(s.baseDato, listContactos, mapeoDiccionario);
    			if(file!=null) {
	       			con.close();
	       			return ok(file,false,Optional.of("ListaDeContactosBodegas.xlsx"));
	       		}else {
	       			con.close();
	       			return ok("");
	       		}
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result bodegaModificaContacto(Long id_bodegaEmpresa, Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("bodegaContactos")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
    			List<ContactoBodegaEmpresa> listContactos = ContactoBodegaEmpresa.allxBodega(con, s.baseDato, id_bodegaEmpresa);
    			// utilizar vista proyectoModifica y sus funciones
    			con.close();
    			return ok(bodegaModificaContacto.render(mapeoDiccionario,mapeoPermiso,userMnu,bodega,listContactos));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result bodegaContactoModifica(Long id_contacto, Long id_bodegaEmpresa, Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("bodegaContactos")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			ContactoBodegaEmpresa contacto = ContactoBodegaEmpresa.find(con, s.baseDato, id_contacto);
    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
    			con.close();
    			return ok(bodegaContactoModifica.render(mapeoDiccionario,mapeoPermiso,userMnu,contacto,bodega));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result bodegaContactoAgrega(Long id_bodegaEmpresa, Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("bodegaContactos")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
    			con.close();
    			return ok(bodegaContactoAgrega.render(mapeoDiccionario,mapeoPermiso,userMnu,bodega));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result bodegaContactoGraba(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		FormContactoBodegaGraba form = formFactory.form(FormContactoBodegaGraba.class).withDirectFieldAccess(true).bindFromRequest(request).get();
    		if (form.id_bodega==null) {
    			return ok(mensajes.render("/",msgErrorFormulario));
    		}else {
    			try {
	    			Connection con = db.getConnection();
	    			if(ContactoBodegaEmpresa.create(con, s.baseDato, form)) {
	    				BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, form.id_bodega);
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "contactoBodegaEmpresa", (long)0, "create", "crea nuevo contacto id_bodegaEmpresa: "+form.id_bodega+" de bodega: "+bodega.getNombre());
	    				con.close();
	    				return redirect("/bodegaModificaContacto/"+form.id_bodega);
	    			}else {
	    				con.close();
	    				return ok(mensajes.render("/",msgError));
	    			}
    			} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
    		}
    	}
    	return ok(mensajes.render("/",msgError));
    }
    
    public Result bodegaContactoElimina(Long id_contacto, Long id_bodega, Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			if(mapeoPermiso.get("bodegaContactos")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			ContactoBodegaEmpresa.delete(con, s.baseDato, id_contacto);
    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodega);
    			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "contactoBodegaEmpresa", id_contacto, "delete", "elimina contacto de id_bodegaEmpresa: "+id_bodega+" de bodega: "+bodega.getNombre());
    			con.close();
    			return redirect("/bodegaModificaContacto/"+id_bodega);
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result modificaContactoBodegaPorCampoAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	String campo = form.get("campo").trim();
	       		Long id_contacto = Long.parseLong(form.get("id_contacto").trim());
	       		String valor = form.get("valor").trim();
				try {
	    			Connection con = db.getConnection();
	    			ContactoBodegaEmpresa.modificaPorCampo(con, s.baseDato, campo, id_contacto, valor);
	    			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "contactoBodegaEmpresa", id_contacto, "update", "cambia contacto en campo: "+campo);
    				con.close();
	    			return ok("");
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    	return ok("error");
    }
    
    public Result bodegaContactosAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	       		Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
				try {
	    			Connection con = db.getConnection();
	    			String vista = ContactoBodegaEmpresa.contactos(con, s.baseDato, id_bodegaEmpresa);
    				con.close();
	    			return ok(vista);
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    	return ok("error");
    }
    
    public Result bodegaFindIdForNomAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	String nombreBodega = form.get("nombreBodega").trim();
				try {
	    			Connection con = db.getConnection();
	    			BodegaEmpresa bodega = BodegaEmpresa.findXNombreBodega(con, s.baseDato, nombreBodega);
	    			if(bodega != null && (long) bodega.id != (long) 0) {
	    				con.close();
		    			return ok(bodega.id.toString());
	    			}
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    	return ok("error");
    }
    
    
    //======================================================================
    // MNU bodegaVigenteNoVigente   Bodegas/Cambiar el Estado BodegaEmpresa
    //======================================================================
    
    public Result bodegaVigenteNoVigente(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("bodegaVigenteNoVigente")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<List<String>> listBodegas = BodegaEmpresa.listaAllBodegasVigentesNoVigentes(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
    			con.close();
    			return ok(bodegaVigenteNoVigente.render(mapeoDiccionario,mapeoPermiso,userMnu,listBodegas));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result modificarBodegaEstado(Long id_bodega, Long estado, Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("bodegaVigenteNoVigente")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			if(BodegaEmpresa.existeStockEnArriendo(con, s.baseDato, id_bodega)) {
    				String mensaje = " No es posible cambiar de estado VIGENTE a NO VIGENTE, hay equipos existentes en "+
    						mapeoDiccionario.get("Arriendo")+" en "+mapeoDiccionario.get("Bodega")+"/Proyecto";
    				con.close();
    				return ok(mensajes.render("/bodegaVigenteNoVigente/",mensaje));
    			}else {
    				BodegaEmpresa.modificarBodegaPorCampo(con, s.baseDato, id_bodega, "vigente", estado.toString());
    				con.close();
    				return redirect("/bodegaVigenteNoVigente/");
    			}
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
	

}
