package controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import controllers.HomeController.Sessiones;
import models.tables.AuxHuella;
import models.tables.BodegaEmpresa;
import models.tables.Equipo;
import models.tables.MantActividad;
import models.tables.MantActorPersonal;
import models.tables.MantComponente;
import models.tables.MantEstadoEnObra;
import models.tables.MantEstadoEnTaller;
import models.tables.MantEstadoOperacional;
import models.tables.MantItemIntervenido;
import models.tables.MantOperadorMecanico;
import models.tables.MantTipoActividad;
import models.tables.MantTipoPersonal;
import models.tables.OperadorServicio;
import models.tables.PlanMantencion;
import models.tables.TipoMantencion;
import models.tables.VentaServicio;
import models.utilities.Fechas;
import models.utilities.Registro;
import models.utilities.UserMnu;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.Database;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.mensajes;
import viewsMnuMantencion.html.*;
import viewsMnuOdo.html.odoVentas;


/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class MnuMantencion extends Controller {
	
   
	public static Database db = HomeController.dbWrite;
	public static FormFactory formFactory = HomeController.formFactory;
	public static String msgError = HomeController.msgError;
	public static String msgErrorFormulario = HomeController.msgErrorFormulario;
	public static String msgSinPermiso = HomeController.msgSinPermiso;
	
    
    
	
	
	//============================================================
    // MENU INGRESO REPORT ADAM
    //============================================================
    
    public Result mantReportNew(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("mantReportAdam")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			String fechaAAMMDD = Fechas.hoy().getFechaStrAAMMDD();
    			List<MantActorPersonal> listActor = MantActorPersonal.all(con, s.baseDato);
    			List<MantOperadorMecanico> listOperMec = MantOperadorMecanico.allVigentes(con, s.baseDato);
    			List<PlanMantencion> listPlanMant = PlanMantencion.all(con, s.baseDato);
    			Map<String,String> mapIdEquipVsBododega = Equipo.mapConExistenciaUnaUnidad(con, s.baseDato);
    			String mapIdEquipVsBod = Json.toJson(mapIdEquipVsBododega).toString();
    			List<BodegaEmpresa> listBod = BodegaEmpresa.allVigentes(con, s.baseDato);
    			List<MantEstadoEnObra> listEstaObra = MantEstadoEnObra.all(con, s.baseDato);
    			
    			
    			Map<Long,List<String>> mapEquipos = new HashMap<Long,List<String>>();
    			for(PlanMantencion x: listPlanMant) {
    				List<String> aux = mapEquipos.get(x.getId_equipo());
    				if(aux == null) {
    					aux = new ArrayList<String>();
    					aux.add(x.getId_equipo().toString());
    					aux.add(x.getEquipoGrupo());
    					aux.add(x.getEquipoCodigo());
    					aux.add(x.getEquipoNombre());
    					String u = x.getUnidadMantencion();
    					aux.add(u);
    					if(u.equals("HR") || u.equals("KG")) {
    						aux.add(x.getEstadoActual());
    					}else {
    						aux.add("0.00");
    					}
    					mapEquipos.put(x.getId_equipo(), aux);
    				}else {
    					String u = x.getUnidadMantencion();
    					if(u.equals("HR") || u.equals("KG") && ! aux.get(4).equals("HR")) {
    						aux.set(4,u);
    						aux.set(5,x.getEstadoActual());
    					}else {
    						aux.add("0.00");
    					} 
    					mapEquipos.put(x.getId_equipo(), aux);
    				}
    			}
    			List<List<String>> listEquipos = new ArrayList<List<String>>();
    			mapEquipos.forEach((k,v) -> {
    				listEquipos.add(v);
    			});
    			
    			List<TipoMantencion> listTipoMantencion = TipoMantencion.all(con, s.baseDato);
    			List<MantEstadoEnTaller> listEstadoEnTaller = MantEstadoEnTaller.all(con, s.baseDato);
    			List<MantActividad> listActividad = MantActividad.all(con, s.baseDato);
    			List<MantTipoActividad> listTipoActividad = MantTipoActividad.all(con, s.baseDato);
    			
    			Long aux_huella = AuxHuella.findHuella(con, s.baseDato, userMnu.getId_usuario());
    			con.close();
    			return ok(mantReportNew.render(mapeoDiccionario,mapeoPermiso,userMnu,fechaAAMMDD,listActor,listOperMec,
    					listPlanMant,mapIdEquipVsBod,listBod,listEstaObra,aux_huella, listEquipos, listTipoMantencion,
    					listEstadoEnTaller,listActividad,listTipoActividad));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    	return ok(mensajes.render("/",msgError));
    }
    
    
    
    
    
    
    //============================================================
    // MNU PLANES MANTENCION TABLAS
    //============================================================
	
	//============================================================
	// ITEMS A INTERVENIR 
	public Result mantTblItemMant(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("mantTblItemMant")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<MantItemIntervenido> listItems = MantItemIntervenido.all(con, s.baseDato);
    			con.close();
    			return ok(mantTblItemMant.render(mapeoDiccionario,mapeoPermiso,userMnu,listItems));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
	
	public Result mantTblItemNew(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("mantTblItemMant")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			con.close();
    			return ok(mantTblItemNew.render(mapeoDiccionario,mapeoPermiso,userMnu));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result mantTblItemNewSave(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String nombreItem = form.get("nombreItem").trim();
				try {
	    			Connection con = db.getConnection();
	    			if(MantItemIntervenido.existeItem(con, s.baseDato, nombreItem)) {
	    				String msg = "No es posible crear este item, el nombre de item ya existe.";
	    				con.close();
	    				return ok(mensajes.render("/routes3/mantTblItemMant/",msg));
	    			}else {
	    				if(MantItemIntervenido.create(con, s.baseDato, nombreItem)) {
	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "mantItemIntervenido", (long)0, "create", "agrega nuevo item: "+nombreItem);
	    					con.close();
	        				return redirect("/routes3/mantTblItemMant/");
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
    
    public Result mantTblItemUpdate(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	    	  	Long id_item = Long.parseLong(form.get("id_item").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("mantTblItemMant")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			MantItemIntervenido item = MantItemIntervenido.find(con, s.baseDato, id_item);
	    			con.close();
	    			return ok(mantTblItemUpdate.render(mapeoDiccionario,mapeoPermiso,userMnu,item));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result modificaItemPorCampoAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String campo = form.get("campo").trim();
	       		Long id_item = Long.parseLong(form.get("id_item").trim());
	       		String valor = form.get("valor").trim();
				try {
	    			Connection con = db.getConnection();
	    			if(MantItemIntervenido.existeItem(con, s.baseDato, valor)) {
	    				con.close();
    	    			return ok("existe");
	    			}else {
	    				if(MantItemIntervenido.modificaPorCampo(con, s.baseDato, campo, id_item, valor)) {
	    					MantItemIntervenido item = MantItemIntervenido.find(con, s.baseDato, id_item);
	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "mantItemIntervenido", id_item, "update", "cambia nombre del item a "+item.getNombre());
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
    
    public Result mantTblItemDel(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	    	  	Long id_item = Long.parseLong(form.get("id_item").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			if(mapeoPermiso.get("mantTblItemMant")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			if(MantItemIntervenido.estaEnUso(con, s.baseDato, id_item)) {
	    				String msg = "No es posible eliminar este item, esta en uso";
	    				con.close();
	    				return ok(mensajes.render("/routes3/mantTblItemMant/",msg));
	    			}else {
	    				MantItemIntervenido item = MantItemIntervenido.find(con, s.baseDato, id_item);
	    				if(MantItemIntervenido.delete(con, s.baseDato, id_item)) {
	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "mantItemIntervenido", id_item, "delete", "elimina item "+item.nombre);
	    					con.close();
	        				return redirect("/routes3/mantTblItemMant/");
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
  	// ACTIVIDADES 
  	public Result mantTblActividadMant(Http.Request request) {
      	Sessiones s = new Sessiones(request);
      	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
      		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
      		try {
      			Connection con = db.getConnection();
      			
      			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
      			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
      			if(mapeoPermiso.get("mantTblActividadMant")==null) {
      				con.close();
      				return ok(mensajes.render("/",msgSinPermiso));
      			}
      			List<MantActividad> listActividad = MantActividad.all(con, s.baseDato);
      			con.close();
      			return ok(mantTblActividadMant.render(mapeoDiccionario,mapeoPermiso,userMnu,listActividad));
          	} catch (SQLException e) {
      			e.printStackTrace();
      		}
      		return ok(mensajes.render("/",msgError));
      	}else {
      		return ok(mensajes.render("/",msgError));
      	}
      }
  	
  	public Result mantTblActividadNew(Http.Request request) {
      	Sessiones s = new Sessiones(request);
      	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
      		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
      		try {
      			Connection con = db.getConnection();
      			
      			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
      			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
      			if(mapeoPermiso.get("mantTblActividadMant")==null) {
      				con.close();
      				return ok(mensajes.render("/",msgSinPermiso));
      			}
      			con.close();
      			return ok(mantTblActividadNew.render(mapeoDiccionario,mapeoPermiso,userMnu));
          	} catch (SQLException e) {
      			e.printStackTrace();
      		}
      		return ok(mensajes.render("/",msgError));
      	}else {
      		return ok(mensajes.render("/",msgError));
      	}
      }
      
      public Result mantTblActividadNewSave(Http.Request request) {
      	Sessiones s = new Sessiones(request);
      	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
      		 
      		DynamicForm form = formFactory.form().bindFromRequest(request);
  	   		if (form.hasErrors()) {
  	   			return ok(mensajes.render("/",msgErrorFormulario));
  	       	}else {
  	       		String nombreActividad = form.get("nombreActividad").trim();
  				try {
  	    			Connection con = db.getConnection();
  	    			if(MantActividad.existeActividad(con, s.baseDato, nombreActividad)) {
  	    				String msg = "No es posible crear esta actividad, el nombre de actividad ya existe.";
  	    				con.close();
  	    				return ok(mensajes.render("/routes3/mantTblActividadMant/",msg));
  	    			}else {
  	    				if(MantActividad.create(con, s.baseDato, nombreActividad)) {
  	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "mantActividad", (long)0, "create", "agrega nueva actividad: "+nombreActividad);
  	    					con.close();
  	        				return redirect("/routes3/mantTblActividadMant/");
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
      
      public Result mantTblActividadUpdate(Http.Request request) {
      	Sessiones s = new Sessiones(request);
      	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
      		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
      		DynamicForm form = formFactory.form().bindFromRequest(request);
  	   		if (form.hasErrors()) {
  	   			return ok(mensajes.render("/",msgErrorFormulario));
  	       	}else {
  	    	  	Long id_actividad = Long.parseLong(form.get("id_actividad").trim());
  	    		try {
  	    			Connection con = db.getConnection();
  	    			
  	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
  	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
  	    			if(mapeoPermiso.get("mantTblActividadMant")==null) {
  	    				con.close();
  	    				return ok(mensajes.render("/",msgSinPermiso));
  	    			}
  	    			MantActividad actividad = MantActividad.find(con, s.baseDato, id_actividad);
  	    			con.close();
  	    			return ok(mantTblActividadUpdate.render(mapeoDiccionario,mapeoPermiso,userMnu,actividad));
  	        	} catch (SQLException e) {
  	    			e.printStackTrace();
  	    		}
  	    		return ok(mensajes.render("/",msgError));
  	       	}
      	}else {
      		return ok(mensajes.render("/",msgError));
      	}
      }
      
      public Result modificaActividadPorCampoAjax(Http.Request request) {
      	Sessiones s = new Sessiones(request);
      	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
      		 
      		DynamicForm form = formFactory.form().bindFromRequest(request);
  	   		if (form.hasErrors()) {
  	   			return ok(mensajes.render("/",msgErrorFormulario));
  	       	}else {
  	       		String campo = form.get("campo").trim();
  	       		Long id_actividad = Long.parseLong(form.get("id_actividad").trim());
  	       		String valor = form.get("valor").trim();
  				try {
  	    			Connection con = db.getConnection();
  	    			if(MantActividad.existeActividad(con, s.baseDato, valor)) {
  	    				con.close();
      	    			return ok("existe");
  	    			}else {
  	    				if(MantActividad.modificaPorCampo(con, s.baseDato, campo, id_actividad, valor)) {
  	    					MantActividad actividad = MantActividad.find(con, s.baseDato, id_actividad);
  	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "mantActividad", id_actividad, "update", "cambia nombre de la actividad a "+actividad.getNombre());
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
      
      public Result mantTblActividadDel(Http.Request request) {
      	Sessiones s = new Sessiones(request);
      	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
      		 
      		DynamicForm form = formFactory.form().bindFromRequest(request);
  	   		if (form.hasErrors()) {
  	   			return ok(mensajes.render("/",msgErrorFormulario));
  	       	}else {
  	    	  	Long id_actividad = Long.parseLong(form.get("id_actividad").trim());
  	    		try {
  	    			Connection con = db.getConnection();
  	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
  	    			if(mapeoPermiso.get("mantTblActividadMant")==null) {
  	    				con.close();
  	    				return ok(mensajes.render("/",msgSinPermiso));
  	    			}
  	    			if(MantActividad.estaEnUso(con, s.baseDato, id_actividad)) {
  	    				String msg = "No es posible eliminar esta actividad, esta en uso";
  	    				con.close();
  	    				return ok(mensajes.render("/routes3/mantTblActividadMant/",msg));
  	    			}else {
  	    				MantActividad actividad = MantActividad.find(con, s.baseDato, id_actividad);
  	    				if(MantActividad.delete(con, s.baseDato, id_actividad)) {
  	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "mantActividad", id_actividad, "delete", "elimina actividad "+actividad.nombre);
  	    					con.close();
  	        				return redirect("/routes3/mantTblActividadMant/");
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
  	// TIPO DE ACTIVIDADES
  	public Result mantTblTipoActividadMant(Http.Request request) {
      	Sessiones s = new Sessiones(request);
      	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
      		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
      		try {
      			Connection con = db.getConnection();
      			
      			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
      			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
      			if(mapeoPermiso.get("mantTblTipoActividadMant")==null) {
      				con.close();
      				return ok(mensajes.render("/",msgSinPermiso));
      			}
      			List<MantTipoActividad> listTipoActividad = MantTipoActividad.all(con, s.baseDato);
      			con.close();
      			return ok(mantTblTipoActividadMant.render(mapeoDiccionario,mapeoPermiso,userMnu,listTipoActividad));
          	} catch (SQLException e) {
      			e.printStackTrace();
      		}
      		return ok(mensajes.render("/",msgError));
      	}else {
      		return ok(mensajes.render("/",msgError));
      	}
    }
  	
  	public Result mantTblTipoActividadNew(Http.Request request) {
      	Sessiones s = new Sessiones(request);
      	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
      		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
      		try {
      			Connection con = db.getConnection();
      			
      			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
      			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
      			if(mapeoPermiso.get("mantTblTipoActividadMant")==null) {
      				con.close();
      				return ok(mensajes.render("/",msgSinPermiso));
      			}
      			con.close();
      			return ok(mantTblTipoActividadNew.render(mapeoDiccionario,mapeoPermiso,userMnu));
          	} catch (SQLException e) {
      			e.printStackTrace();
      		}
      		return ok(mensajes.render("/",msgError));
      	}else {
      		return ok(mensajes.render("/",msgError));
      	}
    }
      
    public Result mantTblTipoActividadNewSave(Http.Request request) {
      	Sessiones s = new Sessiones(request);
      	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
      		 
      		DynamicForm form = formFactory.form().bindFromRequest(request);
  	   		if (form.hasErrors()) {
  	   			return ok(mensajes.render("/",msgErrorFormulario));
  	       	}else {
  	       		String nombreTipoActividad = form.get("nombreTipoActividad").trim();
  				try {
  	    			Connection con = db.getConnection();
  	    			if(MantTipoActividad.existeTipoActividad(con, s.baseDato, nombreTipoActividad)) {
  	    				String msg = "No es posible crear este tipo de actividad, el nombre ya existe.";
  	    				con.close();
  	    				return ok(mensajes.render("/routes3/mantTblTipoActividadMant/",msg));
  	    			}else {
  	    				if(MantTipoActividad.create(con, s.baseDato, nombreTipoActividad)) {
  	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "mantTipoActividad", (long)0, "create", "agrega nuevo tipo de actividad: "+nombreTipoActividad);
  	    					con.close();
  	        				return redirect("/routes3/mantTblTipoActividadMant/");
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
      
    public Result mantTblTipoActividadUpdate(Http.Request request) {
      	Sessiones s = new Sessiones(request);
      	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
      		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
      		DynamicForm form = formFactory.form().bindFromRequest(request);
  	   		if (form.hasErrors()) {
  	   			return ok(mensajes.render("/",msgErrorFormulario));
  	       	}else {
  	    	  	Long id_tipoActividad = Long.parseLong(form.get("id_tipoActividad").trim());
  	    		try {
  	    			Connection con = db.getConnection();
  	    			
  	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
  	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
  	    			if(mapeoPermiso.get("mantTblTipoActividadMant")==null) {
  	    				con.close();
  	    				return ok(mensajes.render("/",msgSinPermiso));
  	    			}
  	    			MantTipoActividad tipoActividad = MantTipoActividad.find(con, s.baseDato, id_tipoActividad);
  	    			con.close();
  	    			return ok(mantTblTipoActividadUpdate.render(mapeoDiccionario,mapeoPermiso,userMnu,tipoActividad));
  	        	} catch (SQLException e) {
  	    			e.printStackTrace();
  	    		}
  	    		return ok(mensajes.render("/",msgError));
  	       	}
      	}else {
      		return ok(mensajes.render("/",msgError));
      	}
    }
      
    public Result modificaTipoActividadPorCampoAjax(Http.Request request) {
      	Sessiones s = new Sessiones(request);
      	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
      		 
      		DynamicForm form = formFactory.form().bindFromRequest(request);
  	   		if (form.hasErrors()) {
  	   			return ok(mensajes.render("/",msgErrorFormulario));
  	       	}else {
  	       		String campo = form.get("campo").trim();
  	       		Long id_tipoActividad = Long.parseLong(form.get("id_tipoActividad").trim());
  	       		String valor = form.get("valor").trim();
  				try {
  	    			Connection con = db.getConnection();
  	    			if(MantTipoActividad.existeTipoActividad(con, s.baseDato, valor)) {
  	    				con.close();
      	    			return ok("existe");
  	    			}else {
  	    				if(MantTipoActividad.modificaPorCampo(con, s.baseDato, campo, id_tipoActividad, valor)) {
  	    					MantTipoActividad tipoActividad = MantTipoActividad.find(con, s.baseDato, id_tipoActividad);
  	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "mantTipoActividad", id_tipoActividad, "update", "cambia nombre del tipo de actividad a "+tipoActividad.getNombre());
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
      
    public Result mantTblTipoActividadDel(Http.Request request) {
      	Sessiones s = new Sessiones(request);
      	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
      		 
      		DynamicForm form = formFactory.form().bindFromRequest(request);
  	   		if (form.hasErrors()) {
  	   			return ok(mensajes.render("/",msgErrorFormulario));
  	       	}else {
  	    	  	Long id_tipoActividad = Long.parseLong(form.get("id_tipoActividad").trim());
  	    		try {
  	    			Connection con = db.getConnection();
  	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
  	    			if(mapeoPermiso.get("mantTblTipoActividadMant")==null) {
  	    				con.close();
  	    				return ok(mensajes.render("/",msgSinPermiso));
  	    			}
  	    			if(MantTipoActividad.estaEnUso(con, s.baseDato, id_tipoActividad)) {
  	    				String msg = "No es posible eliminar este tipo de actividad, esta en uso";
  	    				con.close();
  	    				return ok(mensajes.render("/routes3/mantTblTipoActividadMant/",msg));
  	    			}else {
  	    				MantTipoActividad tipoActividad = MantTipoActividad.find(con, s.baseDato, id_tipoActividad);
  	    				if(MantTipoActividad.delete(con, s.baseDato, id_tipoActividad)) {
  	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "mantTipoActividad", id_tipoActividad, "delete", "elimina tipo de actividad "+tipoActividad.nombre);
  	    					con.close();
  	        				return redirect("/routes3/mantTblTipoActividadMant/");
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
  	// COMPONENTES
  	public Result mantTblComponenteMant(Http.Request request) {
      	Sessiones s = new Sessiones(request);
      	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
      		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
      		try {
      			Connection con = db.getConnection();
      			
      			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
      			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
      			if(mapeoPermiso.get("mantTblComponenteMant")==null) {
      				con.close();
      				return ok(mensajes.render("/",msgSinPermiso));
      			}
      			List<MantComponente> listComponentes = MantComponente.all(con, s.baseDato);
      			con.close();
      			return ok(mantTblComponenteMant.render(mapeoDiccionario,mapeoPermiso,userMnu,listComponentes));
          	} catch (SQLException e) {
      			e.printStackTrace();
      		}
      		return ok(mensajes.render("/",msgError));
      	}else {
      		return ok(mensajes.render("/",msgError));
      	}
    }
  	
  	public Result mantTblComponenteNew(Http.Request request) {
      	Sessiones s = new Sessiones(request);
      	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
      		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
      		try {
      			Connection con = db.getConnection();
      			
      			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
      			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
      			if(mapeoPermiso.get("mantTblComponenteMant")==null) {
      				con.close();
      				return ok(mensajes.render("/",msgSinPermiso));
      			}
      			con.close();
      			return ok(mantTblComponenteNew.render(mapeoDiccionario,mapeoPermiso,userMnu));
          	} catch (SQLException e) {
      			e.printStackTrace();
      		}
      		return ok(mensajes.render("/",msgError));
      	}else {
      		return ok(mensajes.render("/",msgError));
      	}
    }
      
    public Result mantTblComponenteNewSave(Http.Request request) {
      	Sessiones s = new Sessiones(request);
      	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
      		 
      		DynamicForm form = formFactory.form().bindFromRequest(request);
  	   		if (form.hasErrors()) {
  	   			return ok(mensajes.render("/",msgErrorFormulario));
  	       	}else {
  	       		String nombreComponente = form.get("nombreComponente").trim();
  				try {
  	    			Connection con = db.getConnection();
  	    			if(MantComponente.existeComponente(con, s.baseDato, nombreComponente)) {
  	    				String msg = "No es posible crear este componente, el nombre ya existe.";
  	    				con.close();
  	    				return ok(mensajes.render("/routes3/mantTblComponenteMant/",msg));
  	    			}else {
  	    				if(MantComponente.create(con, s.baseDato, nombreComponente)) {
  	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "mantComponente", (long)0, "create", "agrega nuevo componente: "+nombreComponente);
  	    					con.close();
  	        				return redirect("/routes3/mantTblComponenteMant/");
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
      
    public Result mantTblComponenteUpdate(Http.Request request) {
      	Sessiones s = new Sessiones(request);
      	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
      		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
      		DynamicForm form = formFactory.form().bindFromRequest(request);
  	   		if (form.hasErrors()) {
  	   			return ok(mensajes.render("/",msgErrorFormulario));
  	       	}else {
  	    	  	Long id_componente = Long.parseLong(form.get("id_componente").trim());
  	    		try {
  	    			Connection con = db.getConnection();
  	    			
  	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
  	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
  	    			if(mapeoPermiso.get("mantTblComponenteMant")==null) {
  	    				con.close();
  	    				return ok(mensajes.render("/",msgSinPermiso));
  	    			}
  	    			MantComponente componente = MantComponente.find(con, s.baseDato, id_componente);
  	    			con.close();
  	    			return ok(mantTblComponenteUpdate.render(mapeoDiccionario,mapeoPermiso,userMnu,componente));
  	        	} catch (SQLException e) {
  	    			e.printStackTrace();
  	    		}
  	    		return ok(mensajes.render("/",msgError));
  	       	}
      	}else {
      		return ok(mensajes.render("/",msgError));
      	}
    }
      
    public Result modificaComponentePorCampoAjax(Http.Request request) {
      	Sessiones s = new Sessiones(request);
      	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
      		 
      		DynamicForm form = formFactory.form().bindFromRequest(request);
  	   		if (form.hasErrors()) {
  	   			return ok(mensajes.render("/",msgErrorFormulario));
  	       	}else {
  	       		String campo = form.get("campo").trim();
  	       		Long id_componente = Long.parseLong(form.get("id_componente").trim());
  	       		String valor = form.get("valor").trim();
  				try {
  	    			Connection con = db.getConnection();
  	    			if(MantComponente.existeComponente(con, s.baseDato, valor)) {
  	    				con.close();
      	    			return ok("existe");
  	    			}else {
  	    				if(MantComponente.modificaPorCampo(con, s.baseDato, campo, id_componente, valor)) {
  	    					MantComponente componente = MantComponente.find(con, s.baseDato, id_componente);
  	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "mantComponente", id_componente, "update", "cambia nombre del componente a "+componente.getNombre());
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
      
    public Result mantTblComponenteDel(Http.Request request) {
      	Sessiones s = new Sessiones(request);
      	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
      		 
      		DynamicForm form = formFactory.form().bindFromRequest(request);
  	   		if (form.hasErrors()) {
  	   			return ok(mensajes.render("/",msgErrorFormulario));
  	       	}else {
  	    	  	Long id_componente = Long.parseLong(form.get("id_componente").trim());
  	    		try {
  	    			Connection con = db.getConnection();
  	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
  	    			if(mapeoPermiso.get("mantTblComponenteMant")==null) {
  	    				con.close();
  	    				return ok(mensajes.render("/",msgSinPermiso));
  	    			}
  	    			if(MantComponente.estaEnUso(con, s.baseDato, id_componente)) {
  	    				String msg = "No es posible eliminar este componente, esta en uso";
  	    				con.close();
  	    				return ok(mensajes.render("/routes3/mantTblComponenteMant/",msg));
  	    			}else {
  	    				MantComponente componente = MantComponente.find(con, s.baseDato, id_componente);
  	    				if(MantComponente.delete(con, s.baseDato, id_componente)) {
  	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "mantComponente", id_componente, "delete", "elimina componente "+componente.nombre);
  	    					con.close();
  	        				return redirect("/routes3/mantTblComponenteMant/");
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
  	// ESTADOS EN OBRA SITIO
  	public Result mantTblEstadoEnObraMant(Http.Request request) {
      	Sessiones s = new Sessiones(request);
      	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
      		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
      		try {
      			Connection con = db.getConnection();
      			
      			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
      			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
      			if(mapeoPermiso.get("mantTblEstadoEnObraMant")==null) {
      				con.close();
      				return ok(mensajes.render("/",msgSinPermiso));
      			}
      			List<MantEstadoEnObra> listEstados = MantEstadoEnObra.all(con, s.baseDato);
      			con.close();
      			return ok(mantTblEstadoEnObraMant.render(mapeoDiccionario,mapeoPermiso,userMnu,listEstados));
          	} catch (SQLException e) {
      			e.printStackTrace();
      		}
      		return ok(mensajes.render("/",msgError));
      	}else {
      		return ok(mensajes.render("/",msgError));
      	}
    }
  	
  	public Result mantTblEstadoEnObraNew(Http.Request request) {
      	Sessiones s = new Sessiones(request);
      	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
      		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
      		try {
      			Connection con = db.getConnection();
      			
      			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
      			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
      			if(mapeoPermiso.get("mantTblEstadoEnObraMant")==null) {
      				con.close();
      				return ok(mensajes.render("/",msgSinPermiso));
      			}
      			con.close();
      			return ok(mantTblEstadoEnObraNew.render(mapeoDiccionario,mapeoPermiso,userMnu));
          	} catch (SQLException e) {
      			e.printStackTrace();
      		}
      		return ok(mensajes.render("/",msgError));
      	}else {
      		return ok(mensajes.render("/",msgError));
      	}
    }
      
    public Result mantTblEstadoEnObraNewSave(Http.Request request) {
      	Sessiones s = new Sessiones(request);
      	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
      		 
      		DynamicForm form = formFactory.form().bindFromRequest(request);
  	   		if (form.hasErrors()) {
  	   			return ok(mensajes.render("/",msgErrorFormulario));
  	       	}else {
  	       		String nombreEstado = form.get("nombreEstado").trim();
  				try {
  	    			Connection con = db.getConnection();
  	    			if(MantEstadoEnObra.existeEstado(con, s.baseDato, nombreEstado)) {
  	    				String msg = "No es posible crear este estado, el nombre ya existe.";
  	    				con.close();
  	    				return ok(mensajes.render("/routes3/mantTblEstadoEnObraMant/",msg));
  	    			}else {
  	    				if(MantEstadoEnObra.create(con, s.baseDato, nombreEstado)) {
  	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "mantEstadoEnObra", (long)0, "create", "agrega nuevo estado: "+nombreEstado);
  	    					con.close();
  	        				return redirect("/routes3/mantTblEstadoEnObraMant/");
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
      
    public Result mantTblEstadoEnObraUpdate(Http.Request request) {
      	Sessiones s = new Sessiones(request);
      	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
      		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
      		DynamicForm form = formFactory.form().bindFromRequest(request);
  	   		if (form.hasErrors()) {
  	   			return ok(mensajes.render("/",msgErrorFormulario));
  	       	}else {
  	    	  	Long id_estado = Long.parseLong(form.get("id_estado").trim());
  	    		try {
  	    			Connection con = db.getConnection();
  	    			
  	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
  	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
  	    			if(mapeoPermiso.get("mantTblEstadoEnObraMant")==null) {
  	    				con.close();
  	    				return ok(mensajes.render("/",msgSinPermiso));
  	    			}
  	    			MantEstadoEnObra estado = MantEstadoEnObra.find(con, s.baseDato, id_estado);
  	    			con.close();
  	    			return ok(mantTblEstadoEnObraUpdate.render(mapeoDiccionario,mapeoPermiso,userMnu,estado));
  	        	} catch (SQLException e) {
  	    			e.printStackTrace();
  	    		}
  	    		return ok(mensajes.render("/",msgError));
  	       	}
      	}else {
      		return ok(mensajes.render("/",msgError));
      	}
    }
      
    public Result modificaEstadoEnObraPorCampoAjax(Http.Request request) {
      	Sessiones s = new Sessiones(request);
      	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
      		 
      		DynamicForm form = formFactory.form().bindFromRequest(request);
  	   		if (form.hasErrors()) {
  	   			return ok(mensajes.render("/",msgErrorFormulario));
  	       	}else {
  	       		String campo = form.get("campo").trim();
  	       		Long id_estado = Long.parseLong(form.get("id_estado").trim());
  	       		String valor = form.get("valor").trim();
  				try {
  	    			Connection con = db.getConnection();
  	    			if(MantEstadoEnObra.existeEstado(con, s.baseDato, valor)) {
  	    				con.close();
      	    			return ok("existe");
  	    			}else {
  	    				if(MantEstadoEnObra.modificaPorCampo(con, s.baseDato, campo, id_estado, valor)) {
  	    					MantEstadoEnObra estado = MantEstadoEnObra.find(con, s.baseDato, id_estado);
  	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "mantEstadoEnObra", id_estado, "update", "cambia nombre del estado a "+estado.getNombre());
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
      
    public Result mantTblEstadoEnObraDel(Http.Request request) {
      	Sessiones s = new Sessiones(request);
      	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
      		 
      		DynamicForm form = formFactory.form().bindFromRequest(request);
  	   		if (form.hasErrors()) {
  	   			return ok(mensajes.render("/",msgErrorFormulario));
  	       	}else {
  	    	  	Long id_estado = Long.parseLong(form.get("id_estado").trim());
  	    		try {
  	    			Connection con = db.getConnection();
  	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
  	    			if(mapeoPermiso.get("mantTblEstadoEnObraMant")==null) {
  	    				con.close();
  	    				return ok(mensajes.render("/",msgSinPermiso));
  	    			}
  	    			if(MantEstadoEnObra.estaEnUso(con, s.baseDato, id_estado)) {
  	    				String msg = "No es posible eliminar este estado, esta en uso";
  	    				con.close();
  	    				return ok(mensajes.render("/routes3/mantTblEstadoEnObraMant/",msg));
  	    			}else {
  	    				MantEstadoEnObra estado = MantEstadoEnObra.find(con, s.baseDato, id_estado);
  	    				if(MantEstadoEnObra.delete(con, s.baseDato, id_estado)) {
  	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "mantEstadoEnObra", id_estado, "delete", "elimina estado "+estado.nombre);
  	    					con.close();
  	        				return redirect("/routes3/mantTblEstadoEnObraMant/");
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
  	// ESTADOS EN TALLER
  	public Result mantTblEstadoEnTallerMant(Http.Request request) {
      	Sessiones s = new Sessiones(request);
      	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
      		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
      		try {
      			Connection con = db.getConnection();
      			
      			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
      			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
      			if(mapeoPermiso.get("mantTblEstadoEnTallerMant")==null) {
      				con.close();
      				return ok(mensajes.render("/",msgSinPermiso));
      			}
      			List<MantEstadoEnTaller> listEstados = MantEstadoEnTaller.all(con, s.baseDato);
      			con.close();
      			return ok(mantTblEstadoEnTallerMant.render(mapeoDiccionario,mapeoPermiso,userMnu,listEstados));
          	} catch (SQLException e) {
      			e.printStackTrace();
      		}
      		return ok(mensajes.render("/",msgError));
      	}else {
      		return ok(mensajes.render("/",msgError));
      	}
    }
  	
  	public Result mantTblEstadoEnTallerNew(Http.Request request) {
      	Sessiones s = new Sessiones(request);
      	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
      		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
      		try {
      			Connection con = db.getConnection();
      			
      			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
      			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
      			if(mapeoPermiso.get("mantTblEstadoEnTallerMant")==null) {
      				con.close();
      				return ok(mensajes.render("/",msgSinPermiso));
      			}
      			con.close();
      			return ok(mantTblEstadoEnTallerNew.render(mapeoDiccionario,mapeoPermiso,userMnu));
          	} catch (SQLException e) {
      			e.printStackTrace();
      		}
      		return ok(mensajes.render("/",msgError));
      	}else {
      		return ok(mensajes.render("/",msgError));
      	}
    }
      
    public Result mantTblEstadoEnTallerNewSave(Http.Request request) {
      	Sessiones s = new Sessiones(request);
      	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
      		 
      		DynamicForm form = formFactory.form().bindFromRequest(request);
  	   		if (form.hasErrors()) {
  	   			return ok(mensajes.render("/",msgErrorFormulario));
  	       	}else {
  	       		String nombreEstado = form.get("nombreEstado").trim();
  				try {
  	    			Connection con = db.getConnection();
  	    			if(MantEstadoEnTaller.existeEstado(con, s.baseDato, nombreEstado)) {
  	    				String msg = "No es posible crear este estado, el nombre ya existe.";
  	    				con.close();
  	    				return ok(mensajes.render("/routes3/mantTblEstadoEnTallerMant/",msg));
  	    			}else {
  	    				if(MantEstadoEnTaller.create(con, s.baseDato, nombreEstado)) {
  	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "mantEstadoEnTaller", (long)0, "create", "agrega nuevo estado: "+nombreEstado);
  	    					con.close();
  	        				return redirect("/routes3/mantTblEstadoEnTallerMant/");
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
      
    public Result mantTblEstadoEnTallerUpdate(Http.Request request) {
      	Sessiones s = new Sessiones(request);
      	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
      		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
      		DynamicForm form = formFactory.form().bindFromRequest(request);
  	   		if (form.hasErrors()) {
  	   			return ok(mensajes.render("/",msgErrorFormulario));
  	       	}else {
  	    	  	Long id_estado = Long.parseLong(form.get("id_estado").trim());
  	    		try {
  	    			Connection con = db.getConnection();
  	    			
  	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
  	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
  	    			if(mapeoPermiso.get("mantTblEstadoEnTallerMant")==null) {
  	    				con.close();
  	    				return ok(mensajes.render("/",msgSinPermiso));
  	    			}
  	    			MantEstadoEnTaller estado = MantEstadoEnTaller.find(con, s.baseDato, id_estado);
  	    			con.close();
  	    			return ok(mantTblEstadoEnTallerUpdate.render(mapeoDiccionario,mapeoPermiso,userMnu,estado));
  	        	} catch (SQLException e) {
  	    			e.printStackTrace();
  	    		}
  	    		return ok(mensajes.render("/",msgError));
  	       	}
      	}else {
      		return ok(mensajes.render("/",msgError));
      	}
    }
      
    public Result modificaEstadoEnTallerPorCampoAjax(Http.Request request) {
      	Sessiones s = new Sessiones(request);
      	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
      		 
      		DynamicForm form = formFactory.form().bindFromRequest(request);
  	   		if (form.hasErrors()) {
  	   			return ok(mensajes.render("/",msgErrorFormulario));
  	       	}else {
  	       		String campo = form.get("campo").trim();
  	       		Long id_estado = Long.parseLong(form.get("id_estado").trim());
  	       		String valor = form.get("valor").trim();
  				try {
  	    			Connection con = db.getConnection();
  	    			if(MantEstadoEnTaller.existeEstado(con, s.baseDato, valor)) {
  	    				con.close();
      	    			return ok("existe");
  	    			}else {
  	    				if(MantEstadoEnTaller.modificaPorCampo(con, s.baseDato, campo, id_estado, valor)) {
  	    					MantEstadoEnTaller estado = MantEstadoEnTaller.find(con, s.baseDato, id_estado);
  	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "mantEstadoEnTaller", id_estado, "update", "cambia nombre del estado a "+estado.getNombre());
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
      
    public Result mantTblEstadoEnTallerDel(Http.Request request) {
      	Sessiones s = new Sessiones(request);
      	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
      		 
      		DynamicForm form = formFactory.form().bindFromRequest(request);
  	   		if (form.hasErrors()) {
  	   			return ok(mensajes.render("/",msgErrorFormulario));
  	       	}else {
  	    	  	Long id_estado = Long.parseLong(form.get("id_estado").trim());
  	    		try {
  	    			Connection con = db.getConnection();
  	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
  	    			if(mapeoPermiso.get("mantTblEstadoEnTallerMant")==null) {
  	    				con.close();
  	    				return ok(mensajes.render("/",msgSinPermiso));
  	    			}
  	    			if(MantEstadoEnTaller.estaEnUso(con, s.baseDato, id_estado)) {
  	    				String msg = "No es posible eliminar este estado, esta en uso";
  	    				con.close();
  	    				return ok(mensajes.render("/routes3/mantTblEstadoEnTallerMant/",msg));
  	    			}else {
  	    				MantEstadoEnTaller estado = MantEstadoEnTaller.find(con, s.baseDato, id_estado);
  	    				if(MantEstadoEnTaller.delete(con, s.baseDato, id_estado)) {
  	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "mantEstadoEnTaller", id_estado, "delete", "elimina estado "+estado.nombre);
  	    					con.close();
  	        				return redirect("/routes3/mantTblEstadoEnTallerMant/");
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
  	// ESTADOS OPERACIONALES
  	public Result mantTblEstadoOperacionalMant(Http.Request request) {
      	Sessiones s = new Sessiones(request);
      	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
      		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
      		try {
      			Connection con = db.getConnection();
      			
      			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
      			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
      			if(mapeoPermiso.get("mantTblEstadoOperacionalMant")==null) {
      				con.close();
      				return ok(mensajes.render("/",msgSinPermiso));
      			}
      			List<MantEstadoOperacional> listEstados = MantEstadoOperacional.all(con, s.baseDato);
      			con.close();
      			return ok(mantTblEstadoOperacionalMant.render(mapeoDiccionario,mapeoPermiso,userMnu,listEstados));
          	} catch (SQLException e) {
      			e.printStackTrace();
      		}
      		return ok(mensajes.render("/",msgError));
      	}else {
      		return ok(mensajes.render("/",msgError));
      	}
    }
  	
  	public Result mantTblEstadoOperacionalNew(Http.Request request) {
      	Sessiones s = new Sessiones(request);
      	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
      		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
      		try {
      			Connection con = db.getConnection();
      			
      			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
      			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
      			if(mapeoPermiso.get("mantTblEstadoOperacionalMant")==null) {
      				con.close();
      				return ok(mensajes.render("/",msgSinPermiso));
      			}
      			con.close();
      			return ok(mantTblEstadoOperacionalNew.render(mapeoDiccionario,mapeoPermiso,userMnu));
          	} catch (SQLException e) {
      			e.printStackTrace();
      		}
      		return ok(mensajes.render("/",msgError));
      	}else {
      		return ok(mensajes.render("/",msgError));
      	}
    }
      
    public Result mantTblEstadoOperacionalNewSave(Http.Request request) {
      	Sessiones s = new Sessiones(request);
      	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
      		 
      		DynamicForm form = formFactory.form().bindFromRequest(request);
  	   		if (form.hasErrors()) {
  	   			return ok(mensajes.render("/",msgErrorFormulario));
  	       	}else {
  	       		String nombreEstado = form.get("nombreEstado").trim();
  				try {
  	    			Connection con = db.getConnection();
  	    			if(MantEstadoOperacional.existeEstado(con, s.baseDato, nombreEstado)) {
  	    				String msg = "No es posible crear este estado, el nombre ya existe.";
  	    				con.close();
  	    				return ok(mensajes.render("/routes3/mantTblEstadoOperacionalMant/",msg));
  	    			}else {
  	    				if(MantEstadoOperacional.create(con, s.baseDato, nombreEstado)) {
  	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "mantEstadoOperacional", (long)0, "create", "agrega nuevo estado: "+nombreEstado);
  	    					con.close();
  	        				return redirect("/routes3/mantTblEstadoOperacionalMant/");
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
      
    public Result mantTblEstadoOperacionalUpdate(Http.Request request) {
      	Sessiones s = new Sessiones(request);
      	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
      		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
      		DynamicForm form = formFactory.form().bindFromRequest(request);
  	   		if (form.hasErrors()) {
  	   			return ok(mensajes.render("/",msgErrorFormulario));
  	       	}else {
  	    	  	Long id_estado = Long.parseLong(form.get("id_estado").trim());
  	    		try {
  	    			Connection con = db.getConnection();
  	    			
  	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
  	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
  	    			if(mapeoPermiso.get("mantTblEstadoOperacionalMant")==null) {
  	    				con.close();
  	    				return ok(mensajes.render("/",msgSinPermiso));
  	    			}
  	    			MantEstadoOperacional estado = MantEstadoOperacional.find(con, s.baseDato, id_estado);
  	    			con.close();
  	    			return ok(mantTblEstadoOperacionalUpdate.render(mapeoDiccionario,mapeoPermiso,userMnu,estado));
  	        	} catch (SQLException e) {
  	    			e.printStackTrace();
  	    		}
  	    		return ok(mensajes.render("/",msgError));
  	       	}
      	}else {
      		return ok(mensajes.render("/",msgError));
      	}
    }
      
    public Result modificaEstadoOperacionalPorCampoAjax(Http.Request request) {
      	Sessiones s = new Sessiones(request);
      	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
      		 
      		DynamicForm form = formFactory.form().bindFromRequest(request);
  	   		if (form.hasErrors()) {
  	   			return ok(mensajes.render("/",msgErrorFormulario));
  	       	}else {
  	       		String campo = form.get("campo").trim();
  	       		Long id_estado = Long.parseLong(form.get("id_estado").trim());
  	       		String valor = form.get("valor").trim();
  				try {
  	    			Connection con = db.getConnection();
  	    			if(MantEstadoOperacional.existeEstado(con, s.baseDato, valor)) {
  	    				con.close();
      	    			return ok("existe");
  	    			}else {
  	    				if(MantEstadoOperacional.modificaPorCampo(con, s.baseDato, campo, id_estado, valor)) {
  	    					MantEstadoOperacional estado = MantEstadoOperacional.find(con, s.baseDato, id_estado);
  	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "mantEstadoOperacional", id_estado, "update", "cambia nombre del estado a "+estado.getNombre());
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
      
    public Result mantTblEstadoOperacionalDel(Http.Request request) {
      	Sessiones s = new Sessiones(request);
      	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
      		 
      		DynamicForm form = formFactory.form().bindFromRequest(request);
  	   		if (form.hasErrors()) {
  	   			return ok(mensajes.render("/",msgErrorFormulario));
  	       	}else {
  	    	  	Long id_estado = Long.parseLong(form.get("id_estado").trim());
  	    		try {
  	    			Connection con = db.getConnection();
  	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
  	    			if(mapeoPermiso.get("mantTblEstadoOperacionalMant")==null) {
  	    				con.close();
  	    				return ok(mensajes.render("/",msgSinPermiso));
  	    			}
  	    			if(MantEstadoOperacional.estaEnUso(con, s.baseDato, id_estado)) {
  	    				String msg = "No es posible eliminar este estado, esta en uso";
  	    				con.close();
  	    				return ok(mensajes.render("/routes3/mantTblEstadoOperacionalMant/",msg));
  	    			}else {
  	    				MantEstadoOperacional estado = MantEstadoOperacional.find(con, s.baseDato, id_estado);
  	    				if(MantEstadoOperacional.delete(con, s.baseDato, id_estado)) {
  	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "mantEstadoOperacional", id_estado, "delete", "elimina estado "+estado.nombre);
  	    					con.close();
  	        				return redirect("/routes3/mantTblEstadoOperacionalMant/");
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
  	// USER OPERADOR MECANICO
    public Result mantTblOperadorMecanicoMant(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("mantTblOperadorMecanicoMant")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<MantOperadorMecanico> listOperadorMecanico = MantOperadorMecanico.all(con, s.baseDato);
    			con.close();
    			return ok(mantTblOperadorMecanicoMant.render(mapeoDiccionario,mapeoPermiso,userMnu,listOperadorMecanico));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result mantTblOperadorMecanicoNew(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("mantTblOperadorMecanicoMant")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<MantActorPersonal> listActorPersonal = MantActorPersonal.all(con, s.baseDato);
    			List<MantTipoPersonal> listTipoPersonal = MantTipoPersonal.all(con, s.baseDato);
    			con.close();
    			return ok(mantTblOperadorMecanicoNew.render(mapeoDiccionario,mapeoPermiso,userMnu,listActorPersonal,listTipoPersonal));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result mantTblOperadorMecanicoNewSave(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		MantOperadorMecanico form = formFactory.form(MantOperadorMecanico.class).withDirectFieldAccess(true).bindFromRequest(request).get();
    		if (form.userName==null) {
    			return ok(mensajes.render("/",msgErrorFormulario));
    		}else {
    			try {
	    			Connection con = db.getConnection();
	    			if(MantOperadorMecanico.create(con, s.baseDato, form)) {
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "mantOperadorMecanico", (long)0, "create", "crea nuevo operador mecanico");
	    				con.close();
	    				return redirect("/routes3/mantTblOperadorMecanicoMant/");
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
    
    public Result mantTblOperadorMecanicoUpdate(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	Long id_operadorMecanico = Long.parseLong(form.get("id_operadorMecanico").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			MantOperadorMecanico operadorMecanico = MantOperadorMecanico.findXIdUser(con, s.baseDato, id_operadorMecanico);
	    			List<MantActorPersonal> listActorPersonal = MantActorPersonal.all(con, s.baseDato);
	    			List<MantTipoPersonal> listTipoPersonal = MantTipoPersonal.all(con, s.baseDato);
	    			con.close();
	    			return ok(mantTblOperadorMecanicoUpdate.render(mapeoDiccionario,mapeoPermiso,userMnu,operadorMecanico,listActorPersonal,listTipoPersonal));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result modificaOperadorMecanicoPorCampoAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	String campo = form.get("campo").trim();
	       		Long id_operadorMecanico = Long.parseLong(form.get("id_operadorMecanico").trim());
	       		String valor = form.get("valor").trim();
				try {
	    			Connection con = db.getConnection();
	    			if(campo.equals("userName")) {
	    				if(MantOperadorMecanico.existe(con, s.baseDato, valor)) {
	    	    			con.close();
	    	    			return ok("existe");
	    				}
	    			}
	    			if(!MantOperadorMecanico.modificaPorCampo(con, s.baseDato, campo, id_operadorMecanico, valor)){
	    				con.close();
	    				return ok("error");
	    			}else {
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "mantOperadorMecanico", id_operadorMecanico, "update", "cambia el valor de: "+campo);
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
    
    public Result mantTblOperadorMecanicoVig(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	Long id_operadorMecanico = Long.parseLong(form.get("id_operadorMecanico").trim());
	    	  	Long valor = Long.parseLong(form.get("valor").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			if(mapeoPermiso.get("mantTblOperadorMecanicoMant")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
					if(MantOperadorMecanico.modificaVigencia(con, s.baseDato, id_operadorMecanico, valor)) {
						Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "mantOperadorMecanico", id_operadorMecanico, "update", "cambia vigencia de usuario");
	    				return ok("OK");
					}else {
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
    
    public Result mantTblOperadorMecanicoVerifica(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	String userName = form.get("userName").trim();
				try {
	    			Connection con = db.getConnection();
	    				if(MantOperadorMecanico.existe(con, s.baseDato, userName)) {
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
    
    

}
