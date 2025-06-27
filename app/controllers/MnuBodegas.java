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
import models.tables.*;
import models.utilities.DatabaseRead;
import models.utilities.Registro;
import models.utilities.UserMnu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.Database;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.mensajes;
import viewsMnuBodegas.html.*;

public class MnuBodegas extends Controller {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private static final Database dbWrite = HomeController.dbWrite;
	private static final DatabaseRead dbRead = HomeController.dbRead;
	public static FormFactory formFactory = HomeController.formFactory;
	public static String msgError = HomeController.msgError;
	public static String msgErrorFormulario = HomeController.msgErrorFormulario;
	public static String msgSinPermiso = HomeController.msgSinPermiso;
	private static final String msgReport = HomeController.msgReport;
	
	
	//============================================================
    // MNU bodegaAdministrar   Bodegas/Administrar Bodegas
    //============================================================

    public Result bodegaAdministrar(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("bodegaAdministrar")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<List<String>> listBodegas = BodegaEmpresa.listaAllBodegasVigentesInternasExternas(con, s.baseDato, mapeoPermiso, s.aplicaPorSucursal, s.id_sucursal);
			return ok(bodegaAdministrar.render(mapeoDiccionario,mapeoPermiso,userMnu,listBodegas));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result bodegaCrear(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("bodegaAdministrar")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<Cliente> listClientes = Cliente.all(con, s.baseDato);
			List<Proyecto> listProyectos = Proyecto.all(con, s.baseDato);
			List<TipoBodega> listTipoBodega = TipoBodega.all(con, s.baseDato);
			List<Regiones> listRegiones = Regiones.all(con, s.baseDato);
			List<Sucursal> listSucursal = Sucursal.all(con, s.baseDato);
			Sucursal sucursal = Sucursal.find(con, s.baseDato, s.id_sucursal);
			List<Moneda> listMon = Moneda.allSinMonPrincipal(con, s.baseDato);
			List<Rubro> listRubro = Rubro.all(con, s.baseDato);
			return ok(bodegaCrear.render(mapeoDiccionario,mapeoPermiso,userMnu,listClientes,listProyectos,listTipoBodega,listRegiones,
					listSucursal,sucursal, s.aplicaPorSucursal, listMon, listRubro));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result vistaSelectComercialAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok("error");
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok("error");
		}else {
			String id_sucursal = form.get("id_sucursal").trim();
			try (Connection con = dbRead.getConnection()){
				List<Comercial> listComercial = Comercial.allPorIdSucursalVig(con, s.baseDato, s.aplicaPorSucursal, id_sucursal);
				String vistaHtml = "<select class=\"custom-select\" name=\"id_comercial\">"
						+ "<option value=\"0\">Sin Comercial</option>";
					for(Comercial x: listComercial) {
						vistaHtml += "<option value=\""+x.getId()+"\">"+x.getNameUsuario()+"</option>";
					}
					vistaHtml += "</select>";
				return ok(vistaHtml);
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("error");
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("error");
			}
    	}
	}
    
    public Result verificaNombreBodegaAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok("error");
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok("error");
		}else {
			String nombreBodega = form.get("nombreBodega").trim();
			try (Connection con = dbRead.getConnection()){
				if(BodegaEmpresa.existeBodega(con, s.baseDato, nombreBodega)) {
					return ok("existe");
				}else {
					return ok("");
				}
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("error");
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("error");
			}
    	}
    }
    
    public Result bodegaGraba(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		FormBodegaGraba form = formFactory.form(FormBodegaGraba.class).withDirectFieldAccess(true).bindFromRequest(request).get();
		if (form.id_bodegaEmpresa==null || form.nombre==null) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgErrorFormulario));
		}else {
			try (Connection con = dbWrite.getConnection()){
				if(BodegaEmpresa.create(con, s.baseDato, form)) {
					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "bodegaEmpresa", (long)0, "create", "crea nueva bodega/almacen: "+form.nombre);
					return redirect("/bodegaAdministrar/");
				}else {
					return ok(mensajes.render("/",msgError));
				}
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
    	}
    }
    
    public Result bodegaModificar(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("bodegaAdministrar")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgErrorFormulario));
		}else {
			Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodega").trim());
			try (Connection con = dbRead.getConnection()){
				BodegaEmpresa bodegaEmpresa = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
				List<Cliente> listClientes = Cliente.all(con, s.baseDato);
				List<Proyecto> listProyectos = Proyecto.all(con, s.baseDato);
				TipoBodega tipoBodega = TipoBodega.find(con, s.baseDato, bodegaEmpresa.getEsInterna());
				List<Regiones> listRegiones = Regiones.all(con, s.baseDato);
				List<Comercial> listComercial = Comercial.allPorIdSucursalVig(con, s.baseDato, s.aplicaPorSucursal, bodegaEmpresa.getId_sucursal().toString());
				List<Moneda> listMon = Moneda.allSinMonPrincipal(con, s.baseDato);
				List<List<String>> fijaTasas = BodegaEmpresa.listaFijaTasas(con, s.baseDato, id_bodegaEmpresa);
				List<Sucursal> listSucursal = Sucursal.all(con, s.baseDato);
				List<Rubro> listRubro = Rubro.all(con, s.baseDato);
				return ok(bodegaModificar.render(mapeoDiccionario,mapeoPermiso,userMnu,listClientes,listProyectos,tipoBodega,bodegaEmpresa,listRegiones,listComercial,
						listMon, fijaTasas, listSucursal, s.aplicaPorSucursal, listRubro));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
    	}
    }
    
    public Result bodegaModifica(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		FormBodegaGraba form = formFactory.form(FormBodegaGraba.class).withDirectFieldAccess(true).bindFromRequest(request).get();
		if (form.id_bodegaEmpresa==null || form.nombre==null) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgErrorFormulario));
		} else {
			try (Connection con = dbWrite.getConnection()){
				if(BodegaEmpresa.update(con, s.baseDato, form)) {
					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "bodegaEmpresa", form.id_bodegaEmpresa, "update", "modifica datos de bodega: "+form.nombre);
					return redirect("/bodegaAdministrar/");
				}else {
					return ok(mensajes.render("/",msgError));
				}
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
    	}
    }
    
    public Result bodegaElimina(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgErrorFormulario));
		}else {
			Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodega").trim());
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			if(mapeoPermiso.get("bodegaAdministrar")==null) {
				logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
				return ok(mensajes.render("/",msgSinPermiso));
			}
			try (Connection con = dbWrite.getConnection()) {
				if(BodegaEmpresa.estaEnUso(con, s.baseDato, id_bodegaEmpresa)) {
					String msg = "No es posible eliminar esta bodega, debido a que esta en uso";
					return ok(mensajes.render("/bodegaAdministrar/",msg));
				}else {
					BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
					if(BodegaEmpresa.delete(con, s.baseDato, id_bodegaEmpresa)) {
						Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "bodegaEmpresa", id_bodegaEmpresa, "delete", "elimina bodega: "+bodega.getNombre());
						return redirect("/bodegaAdministrar/");
					}else {
						return ok(mensajes.render("/",msgError));
					}
				}
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
    	}
    }
    
    //============================================================
    // MNU bodegaAdministrar   Bodegas/Administrar Sucursales
    //============================================================
    
    public Result sucursalAdministrar(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("bodegaAdministrar")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<Sucursal> listSucursales = Sucursal.all(con, s.baseDato);
			return ok(sucursalAdministrar.render(mapeoDiccionario,mapeoPermiso,userMnu,listSucursales));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result sucursalElimina(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgErrorFormulario));
		}else {
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			if(mapeoPermiso.get("bodegaAdministrar")==null) {
				logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
				return ok(mensajes.render("/",msgSinPermiso));
			}
			Long id_sucursal = Long.parseLong(form.get("id_sucursal").trim());
			try (Connection con = dbWrite.getConnection()){
				if(Sucursal.estaEnUso(con, s.baseDato, id_sucursal)) {
					String msg = "No es posible eliminar esta sucursal, esta en uso con equipos vigentes ";
					return ok(mensajes.render("/routes2/sucursalAdministrar/",msg));
				}else {
					Sucursal sucursal = Sucursal.find(con, s.baseDato, id_sucursal.toString());
					if(Sucursal.delete(con, s.baseDato, id_sucursal)) {
						Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "sucursal", id_sucursal, "delete", "elimina Sucursal "+sucursal.nombre);
						return redirect("/routes2/sucursalAdministrar/");
					}else {
						return ok(mensajes.render("/",msgError));
					}
				}
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
    	}
    }
    
    public Result sucursalModifica(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("bodegaAdministrar")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgErrorFormulario));
		}else {
			Long id_sucursal = Long.parseLong(form.get("id_sucursal").trim());
			try (Connection con = dbRead.getConnection()){
				Sucursal sucursal = Sucursal.find(con, s.baseDato, id_sucursal.toString());
				return ok(sucursalModifica.render(mapeoDiccionario,mapeoPermiso,userMnu,sucursal));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
    	}
    }
    
    public Result sucursalAgrega(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("bodegaAdministrar")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		return ok(sucursalAgrega.render(mapeoDiccionario,mapeoPermiso,userMnu));
    }
    
    public Result sucursalNuevo(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgErrorFormulario));
		}else {
			String nombreSucursal = form.get("nombreSucursal").trim();
			String strIvaSucursal = form.get("ivaSucursal").trim();
			String ccost = form.get("ccost").trim();
			try (Connection con = dbWrite.getConnection()){
				Double ivaSucursal = Double.parseDouble(strIvaSucursal.replaceAll(",", "").replaceAll("%", "").trim());
				ivaSucursal = ivaSucursal/100;
				if(Sucursal.existeSucursal(con, s.baseDato, nombreSucursal)) {
					String msg = "No es posible crear esta sucursal, el nombre de sucursal ya existe.";
					return ok(mensajes.render("/routes2/sucursalAdministrar/",msg));
				}else {
					if(Sucursal.create(con, s.baseDato, nombreSucursal, ivaSucursal, ccost)) {
						Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "sucursal", (long)0, "create", "agrega nuevo sucursal: "+nombreSucursal);
						Sucursal sucursal = Sucursal.find(con, s.baseDato, "1");
						String msg = "Sucursal creada con exito, recuerde actualizar los precios en el maestro, por ahora se heredaron "
								+ " los precios desde la sucursal "+sucursal.getNombre();
						return ok(mensajes.render("/routes2/sucursalAdministrar/",msg));
					}else {
						logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, "");
						return ok(mensajes.render("/home/", msgReport));
					}
				}
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
    	}
    }
    
    public Result modificaSucursalPorCampoAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok("error");
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok("error");
		}else {
			String campo = form.get("campo").trim();
			Long id_sucursal = Long.parseLong(form.get("id_sucursal").trim());
			String valor = form.get("valor").trim();
			try (Connection con = dbWrite.getConnection()){
				if(Sucursal.existeSucursal(con, s.baseDato, valor)) {
					return ok("existe");
				}else {
					String msg = "cambia nombre de la sucursal a ";
					if(campo.equals("ivaSucursal")) {
						valor = valor.replaceAll(",", "").replaceAll("%", "").trim();
						Double aux = Double.parseDouble(valor)/100;
						valor = aux.toString();
						msg = "cambia IVA de la sucursal a ";
					}
					if(Sucursal.modificaPorCampo(con, s.baseDato, campo, id_sucursal, valor)) {
						Sucursal sucursal = Sucursal.find(con, s.baseDato, id_sucursal.toString());
						Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "sucursal", id_sucursal, "update", msg+sucursal.getNombre());
						return ok("");
					}
					return ok("error");
				}
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("error");
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("error");
			}
    	}
    }
    
    //============================================================
    // MNU bodegaAsignaDctos   Bodegas/Asignar Descuentos
    //============================================================

    public Result bodegaAsignaDctos(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("bodegaAsignaDctos")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<List<String>> listBodegas = BodegaEmpresa.listaAllBodegasVigentesExternas(con, s.baseDato, mapeoPermiso, s.aplicaPorSucursal, s.id_sucursal);
			return ok(bodegaAsignaDctos.render(mapeoDiccionario,mapeoPermiso,userMnu,listBodegas));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result bodegaFijaDctos(Long id_bodegaEmpresa, Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("bodegaAsignaDctos")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			BodegaEmpresa bodegaEmpresa = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
			List<DctoGrupo> listDctoGrupo = DctoGrupo.allXBodegaEmpresa(con, s.baseDato, id_bodegaEmpresa);
			List<DctoEquipo> listDctoEquipo = DctoEquipo.allXBodegaEmpresa(con, s.baseDato, id_bodegaEmpresa);
			List<Grupo> listGrupos = Grupo.all(con, s.baseDato);
			List<Equipo> listEquipos = Equipo.allVigentes(con, s.baseDato);
			return ok(bodegaFijaDctos.render(mapeoDiccionario,mapeoPermiso,userMnu,bodegaEmpresa,listDctoGrupo,listDctoEquipo, listGrupos, listEquipos));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result modificarDctoArriendoAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok("error");
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok("error");
		}else {
			String modifica = form.get("modifica").trim();
			if(modifica.equals("dctoGlobal")) {
				try (Connection con = dbWrite.getConnection()) {
					Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
					Double dcto = Double.parseDouble(form.get("dcto").trim())/100;
					if(BodegaEmpresa.modificarBodegaPorCampo(con, s.baseDato, id_bodegaEmpresa, "tasaDescto", dcto.toString())) {
						BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
						Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "bodegaEmpresa", id_bodegaEmpresa, "update", "modifica tasa descuento global id_bodega: "+id_bodegaEmpresa+" de bodega: "+bodega.getNombre());
						return ok("");
					}else {
						return ok("error");
					}
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok("error");
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok("error");
				}
			}
			if(modifica.equals("dctoGrupo")) {
				try (Connection con = dbWrite.getConnection()){
					Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
					Long id_grupo = Long.parseLong(form.get("id_grupo").trim());
					Double dcto = Double.parseDouble(form.get("dcto").trim())/100;
					if(DctoGrupo.modificar(con, s.baseDato, id_bodegaEmpresa, id_grupo, dcto)) {
						BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
						Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "dctoGrupo", id_grupo, "update", "modifica tasa descuento grupo id_bodega: "+id_bodegaEmpresa+" de bodega: "+bodega.getNombre());
						return ok("");
					}else {
						return ok("error");
					}
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok("error");
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok("error");
				}
			}
			if(modifica.equals("dctoEquipo")) {
				try (Connection con = dbWrite.getConnection()){
					Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
					Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
					Long id_cotizacion = Long.parseLong(form.get("id_cotizacion").trim());
					Double dcto = Double.parseDouble(form.get("dcto").trim())/100;
					if(DctoEquipo.modificar(con, s.baseDato, id_bodegaEmpresa, id_equipo, id_cotizacion, dcto)) {
						BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
						Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "dctoEquipo", id_equipo, "update", "modifica tasa descuento equipo id_bodegaEmpresa: "+id_bodegaEmpresa+" de bodega: "+bodega.getNombre());
						return ok("");
					}else {
						return ok("error");
					}
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok("error");
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok("error");
				}
			}
			return ok("error");
    	}
    }
    
    public Result eliminarDctoArriendoAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok("error");
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok("error");
		}else {
			String elimina = form.get("elimina").trim();
			if(elimina.equals("dctoGrupo")) {
				try (Connection con = dbWrite.getConnection()){
					Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
					Long id_grupo = Long.parseLong(form.get("id_grupo").trim());
					if(DctoGrupo.eliminar(con, s.baseDato, id_bodegaEmpresa, id_grupo)) {
						BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
						Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "dctoGrupo", id_grupo, "delete", "elimina tasa descuento grupo id_bodegaEmpresa: "+id_bodegaEmpresa+" de bodega: "+bodega.getNombre());
						return ok("");
					}else {
						return ok("error");
					}
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok("error");
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok("error");
				}
			}
			if(elimina.equals("dctoEquipo")) {
				try (Connection con = dbWrite.getConnection()){
					Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
					Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
					Long id_cotizacion = Long.parseLong(form.get("id_cotizacion").trim());
					if(DctoEquipo.eliminar(con, s.baseDato, id_bodegaEmpresa, id_equipo, id_cotizacion)) {
						BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
						Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "dctoEquipo", id_equipo, "delete", "elimina tasa descuento equipo id_bodegaEmpresa: "+id_bodegaEmpresa+" de bodega: "+bodega.getNombre());
						return ok("");
					}else {
						return ok("error");
					}
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok("error");
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok("error");
				}
			}
			return ok("error");
    	}
    }
    
    public Result grabaDctoArriendoAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok("error");
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok("error");
		}else {
			String graba = form.get("graba").trim();
			if(graba.equals("dctoGrupo")) {
				try (Connection con = dbWrite.getConnection()){
					Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
					Long id_grupo = Long.parseLong(form.get("id_grupo").trim());
					if(DctoGrupo.agregar(con, s.baseDato, id_bodegaEmpresa, id_grupo)) {
						BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
						Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "dctoGrupo", id_grupo, "create", "agrega tasa descuento grupo id_bodegaEmpresa: "+id_bodegaEmpresa+" de bodega: "+bodega.getNombre());
						return ok("");
					}else {
						return ok("error");
					}
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok("error");
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok("error");
				}
			}
			if(graba.equals("dctoEquipo")) {
				try (Connection con = dbWrite.getConnection()){
					Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
					Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
					Long id_cotizacion = Long.parseLong(form.get("id_cotizacion").trim());
					if(DctoEquipo.agregar(con, s.baseDato, id_bodegaEmpresa, id_equipo, id_cotizacion)) {
						BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
						Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "dctoEquipo", id_equipo, "create", "agrega tasa descuento equipo id_bodegaEmpresa: "+id_bodegaEmpresa+" de bodega: "+bodega.getNombre());
						return ok("");
					}else {
						return ok("error");
					}
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok("error");
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok("error");
				}
			}
			return ok("error");
    	}
    }
    
    
    //============================================================
    // MNU bodegaAsignaTasas   Bodegas/Asignar Tasas
    //============================================================
    
    public Result bodegaAsignaTasas(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("bodegaAsignaTasas")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<List<String>> listBodegas = BodegaEmpresa.listaAllBodVigExternasClientesVig(con, s.baseDato, mapeoPermiso, s.aplicaPorSucursal, s.id_sucursal);
			return ok(bodegaAsignaTasas.render(mapeoDiccionario,mapeoPermiso,userMnu,listBodegas));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result bodegaFijaTasas(Long id_bodegaEmpresa, Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("bodegaAsignaTasas")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			BodegaEmpresa bodegaEmpresa = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
			List<TasaGrupo> listTasaGrupo = TasaGrupo.allXBodegaEmpresa(con, s.baseDato, id_bodegaEmpresa);
			List<TasaEquipo> listTasaEquipo = TasaEquipo.allXBodegaEmpresa(con, s.baseDato, id_bodegaEmpresa);
			List<Grupo> listGrupos = Grupo.all(con, s.baseDato);
			List<Equipo> listEquipos = Equipo.allVigentes(con, s.baseDato);
			return ok(bodegaFijaTasas.render(mapeoDiccionario,mapeoPermiso,userMnu,bodegaEmpresa,listTasaGrupo,listTasaEquipo, listGrupos, listEquipos));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result modificarTasaArriendoAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok("error");
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok("error");
		}else {
			String modifica = form.get("modifica").trim();
			if(modifica.equals("tasaGlobal")) {
				try (Connection con = dbWrite.getConnection()){
					Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
					Double tasa = Double.parseDouble(form.get("tasa").trim())/100;
					if(BodegaEmpresa.modificarBodegaPorCampo(con, s.baseDato, id_bodegaEmpresa, "tasaArriendo", tasa.toString())) {
						BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
						Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "bodegaEmpresa", id_bodegaEmpresa, "update", "modifica tasa arriendo global"+id_bodegaEmpresa+" de bodega: "+bodega.getNombre());
						return ok("");
					}else {
						return ok("error");
					}
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok("error");
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok("error");
				}
			}
			if(modifica.equals("tasaGrupo")) {
				try (Connection con = dbWrite.getConnection()){
					Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
					Long id_grupo = Long.parseLong(form.get("id_grupo").trim());
					Double tasa = Double.parseDouble(form.get("tasa").trim())/100;
					if(TasaGrupo.modificar(con, s.baseDato, id_bodegaEmpresa, id_grupo, tasa)) {
						BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
						Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "tasaGrupo", id_grupo, "update", "modifica tasa arriendo grupo id_bodegaEmpresa: "+id_bodegaEmpresa+" de bodega: "+bodega.getNombre());
						return ok("");
					}else {
						return ok("error");
					}
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok("error");
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok("error");
				}
			}
			if(modifica.equals("tasaEquipo")) {
				try (Connection con = dbWrite.getConnection()){
					Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
					Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
					Long id_cotizacion = Long.parseLong(form.get("id_cotizacion").trim());
					Double tasa = Double.parseDouble(form.get("tasa").trim())/100;
					if(TasaEquipo.modificar(con, s.baseDato, id_bodegaEmpresa, id_equipo, id_cotizacion, tasa)) {
						BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
						Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "tasaEquipo", id_equipo, "update", "modifica tasa arriendo equipo id_bodegaEmpresa: "+id_bodegaEmpresa+" de bodega: "+bodega.getNombre());
						return ok("");
					}else {
						return ok("error");
					}
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok("error");
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok("error");
				}
			}
			return ok("error");
    	}
    }
    
    public Result eliminarTasaArriendoAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok("error");
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok("error");
		}else {
			String elimina = form.get("elimina").trim();
			if(elimina.equals("tasaGrupo")) {
				try (Connection con = dbWrite.getConnection()) {
					Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
					Long id_grupo = Long.parseLong(form.get("id_grupo").trim());
					if(TasaGrupo.eliminar(con, s.baseDato, id_bodegaEmpresa, id_grupo)) {
						BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
						Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "tasaGrupo", id_grupo, "delete", "elimina tasa arriendo grupo id_bodegaEmpresa: "+id_bodegaEmpresa+" de bodega: "+bodega.getNombre());
						return ok("");
					}else {
						return ok("error");
					}
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok("error");
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok("error");
				}
			}
			if(elimina.equals("tasaEquipo")) {
				try (Connection con = dbWrite.getConnection()){
					Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
					Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
					Long id_cotizacion = Long.parseLong(form.get("id_cotizacion").trim());
					if(TasaEquipo.eliminar(con, s.baseDato, id_bodegaEmpresa, id_equipo, id_cotizacion)) {
						BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
						Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "tasaEquipo", id_equipo, "delete", "elimina tasa arriendo equipo id_bodegaEmpresa: "+id_bodegaEmpresa+" de bodega: "+bodega.getNombre());
						return ok("");
					}else {
						return ok("error");
					}
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok("error");
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok("error");
				}
			}
			return ok("error");
    	}
    }
    
    public Result grabaTasaArriendoAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok("error");
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok("error");
		}else {
			String graba = form.get("graba").trim();
			if(graba.equals("tasaGrupo")) {
				try (Connection con = dbWrite.getConnection()){
					Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
					Long id_grupo = Long.parseLong(form.get("id_grupo").trim());
					if(TasaGrupo.agregar(con, s.baseDato, id_bodegaEmpresa, id_grupo)) {
						BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
						Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "tasaGrupo", id_grupo, "create", "agrega tasa arriendo grupo id_bodegaEmpresa: "+id_bodegaEmpresa+" de bodega: "+bodega.getNombre());
						return ok("");
					}else {
						return ok("error");
					}
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok("error");
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok("error");
				}
			}
			if(graba.equals("tasaEquipo")) {
				try (Connection con = dbWrite.getConnection()){
					Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
					Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
					Long id_cotizacion = Long.parseLong(form.get("id_cotizacion").trim());
					if(TasaEquipo.agregar(con, s.baseDato, id_bodegaEmpresa, id_equipo, id_cotizacion)) {
						BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
						Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "tasaEquipo", id_equipo, "create", "agrega tasa arriendo equipo id_bodegaEmpresa: "+id_bodegaEmpresa+" de bodega: "+bodega.getNombre());
						return ok("");
					}else {
						return ok("error");
					}
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok("error");
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok("error");
				}
			}
			return ok("error");
    	}
    }
    
    
    //============================================================
    // MNU bodegaPrecios   Bodegas/Precios por Bodega
    //============================================================
    
    public Result bodegaPrecios(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("bodegaPrecios")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<List<String>> listBodegas = BodegaEmpresa.listaAllBodVigExternasClientesVig(con, s.baseDato, mapeoPermiso, s.aplicaPorSucursal, s.id_sucursal);
			return ok(bodegaPrecios.render(mapeoDiccionario,mapeoPermiso,userMnu,listBodegas));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result bodegaListaPrecios(Long id_bodegaEmpresa, Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("bodegaPrecios")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
			List<ListaPrecio> listPrecios = ListaPrecio.allXBodega(con, s.baseDato, id_bodegaEmpresa, mapeoDiccionario.get("pais"));
			List<Moneda> listMoneda = Moneda.all(con, s.baseDato);
			List<UnidadTiempo> listUnidadTiempo = UnidadTiempo.all(con, s.baseDato);
			return ok(bodegaListaPrecios.render(mapeoDiccionario,mapeoPermiso,userMnu,bodega,listPrecios,listMoneda,listUnidadTiempo));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result actualizaListaPrecioAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok("error");
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok("error");
		}else {
			try (Connection con = dbWrite.getConnection()){
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
				String fecha = ListaPrecio.modifyPorCampo(con, s.baseDato, campo, valor, id_bodegaEmpresa, id_equipo, id_cotizacion);
				BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "listaPrecio", (long)0, "update", "cambia lista precio de bodega en campo:"+campo+
						" de idBod_idEq_idCot="+id_bodegaEmpresa+"_"+id_equipo+"_"+id_cotizacion+" de bodega: "+bodega.getNombre());
				int numDec = Moneda.numeroDecimalxId(con, s.baseDato, id_moneda);
				return ok("{ \"status\": true, \"fecha\": \""+fecha+"\", \"numDec\": \""+numDec+"\"}").as("application/json");
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("error");
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("error");
			}
    	}
    }
    
    //============================================================
    // MNU bodegaFactorViga   Bodegas/Factor Viga (m2/ml)
    //============================================================
    
    public Result bodegaFactorViga(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("bodegaFactorViga")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<List<String>> listBodegas = BodegaEmpresa.listaAllBodegasVigentesExternas(con, s.baseDato, mapeoPermiso, s.aplicaPorSucursal, s.id_sucursal);
			return ok(bodegaFactorViga.render(mapeoDiccionario,mapeoPermiso,userMnu,listBodegas));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result actualizaFactorVigaAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok("error");
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok("error");
		}else {
			try (Connection con = dbWrite.getConnection()){
				Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
				Double factorM2Viga = Double.parseDouble(form.get("factorM2Viga").trim());
				if(BodegaEmpresa.modificarBodegaPorCampo(con, s.baseDato, id_bodegaEmpresa, "factorM2Viga", factorM2Viga.toString())) {
					BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "bodegaEmpresa", id_bodegaEmpresa, "update", "modifica factor m2/ml de viga"+" de bodega: "+bodega.getNombre());
					return ok("");
				}else {
					return ok("error");
				}
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("error");
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("error");
			}
    	}
    }
    
    //============================================================
    // MNU bodegaContactos   Bodegas/Administrar Contactos
    //============================================================
    
    public Result bodegaContactos(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("bodegaContactos")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<List<String>> listBodegas = BodegaEmpresa.listaAllBodegasVigentesInternasExternas(con, s.baseDato, mapeoPermiso, s.aplicaPorSucursal, s.id_sucursal);
			return ok(bodegaContactos.render(mapeoDiccionario,mapeoPermiso,userMnu,listBodegas));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result bodegaContactosExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("bodegaContactos")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<List<String>> listContactos = ContactoBodegaEmpresa.all(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
			File file = ContactoBodegaEmpresa.contactoBodegasExcel(s.baseDato, listContactos, mapeoDiccionario);
			return ok(file,false,Optional.of("ListaDeContactosBodegas.xlsx"));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result bodegaModificaContacto(Long id_bodegaEmpresa, Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("bodegaContactos")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
			List<ContactoBodegaEmpresa> listContactos = ContactoBodegaEmpresa.allxBodega(con, s.baseDato, id_bodegaEmpresa);
			return ok(bodegaModificaContacto.render(mapeoDiccionario,mapeoPermiso,userMnu,bodega,listContactos));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result bodegaContactoModifica(Long id_contacto, Long id_bodegaEmpresa, Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("bodegaContactos")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			ContactoBodegaEmpresa contacto = ContactoBodegaEmpresa.find(con, s.baseDato, id_contacto);
			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
			return ok(bodegaContactoModifica.render(mapeoDiccionario,mapeoPermiso,userMnu,contacto,bodega));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result bodegaContactoAgrega(Long id_bodegaEmpresa, Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("bodegaContactos")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
			return ok(bodegaContactoAgrega.render(mapeoDiccionario,mapeoPermiso,userMnu,bodega));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result bodegaContactoGraba(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		FormContactoBodegaGraba form = formFactory.form(FormContactoBodegaGraba.class).withDirectFieldAccess(true).bindFromRequest(request).get();
		if (form.id_bodega==null) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgErrorFormulario));
		}else {
			try (Connection con = dbWrite.getConnection()){
				if(ContactoBodegaEmpresa.create(con, s.baseDato, form)) {
					BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, form.id_bodega);
					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "contactoBodegaEmpresa", (long)0, "create", "crea nuevo contacto id_bodegaEmpresa: "+form.id_bodega+" de bodega: "+bodega.getNombre());
					return redirect("/bodegaModificaContacto/"+form.id_bodega);
				}else {
					return ok(mensajes.render("/",msgError));
				}
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
    	}
    }
    
    public Result bodegaContactoElimina(Long id_contacto, Long id_bodega, Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		if(mapeoPermiso.get("bodegaContactos")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbWrite.getConnection()){
			ContactoBodegaEmpresa.delete(con, s.baseDato, id_contacto);
			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodega);
			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "contactoBodegaEmpresa", id_contacto, "delete", "elimina contacto de id_bodegaEmpresa: "+id_bodega+" de bodega: "+bodega.getNombre());
			return redirect("/bodegaModificaContacto/"+id_bodega);
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result modificaContactoBodegaPorCampoAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok("error");
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgErrorFormulario));
		}else {
			try (Connection con = dbWrite.getConnection()){
				String campo = form.get("campo").trim();
				Long id_contacto = Long.parseLong(form.get("id_contacto").trim());
				String valor = form.get("valor").trim();
				ContactoBodegaEmpresa.modificaPorCampo(con, s.baseDato, campo, id_contacto, valor);
				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "contactoBodegaEmpresa", id_contacto, "update", "cambia contacto en campo: "+campo);
				return ok("");
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("error");
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("error");
			}
    	}
    }
    
    public Result bodegaContactosAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok("error");
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgErrorFormulario));
		}else {
			try (Connection con = dbRead.getConnection()){
				Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
				String vista = ContactoBodegaEmpresa.contactos(con, s.baseDato, id_bodegaEmpresa);
				return ok(vista);
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("error");
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("error");
			}
    	}
    }
    
    public Result bodegaFindIdForNomAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok("error");
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgErrorFormulario));
		}else {
			try (Connection con = dbRead.getConnection()){
				String nombreBodega = form.get("nombreBodega").trim();
				BodegaEmpresa bodega = BodegaEmpresa.findXNombreBodega(con, s.baseDato, nombreBodega);
				if(bodega != null && (long) bodega.id != (long) 0) {
					return ok(bodega.id.toString());
				}else{
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, "");
					return ok("error");
				}
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("error");
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("error");
			}
    	}
    }
    
    
    //======================================================================
    // MNU bodegaVigenteNoVigente   Bodegas/Cambiar el Estado BodegaEmpresa
    //======================================================================
    
    public Result bodegaVigenteNoVigente(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("bodegaVigenteNoVigente")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<List<String>> listBodegas = BodegaEmpresa.listaAllBodegasVigentesNoVigentes(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
			return ok(bodegaVigenteNoVigente.render(mapeoDiccionario,mapeoPermiso,userMnu,listBodegas));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result modificarBodegaEstado(Long id_bodega, Long estado, Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("bodegaVigenteNoVigente")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbWrite.getConnection()){
			if(BodegaEmpresa.existeStockEnArriendo(con, s.baseDato, mapeoPermiso, id_bodega)) {
				String mensaje = " No es posible cambiar de estado VIGENTE a NO VIGENTE, hay equipos existentes en "+
						mapeoDiccionario.get("Arriendo")+" en "+mapeoDiccionario.get("Bodega")+"/Proyecto";
				return ok(mensajes.render("/bodegaVigenteNoVigente/",mensaje));
			}else {
				BodegaEmpresa.modificarBodegaPorCampo(con, s.baseDato, id_bodega, "vigente", estado.toString());
				return redirect("/bodegaVigenteNoVigente/");
			}
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }


	public Result rubroMantencion(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("bodegaAdministrar")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<Rubro> listRubro = Rubro.all(con, s.baseDato);
			return ok(rubroMantencion.render(mapeoDiccionario,mapeoPermiso,userMnu,listRubro));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result rubroAgrega(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("bodegaAdministrar")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		return ok(rubroAgrega.render(mapeoDiccionario,mapeoPermiso,userMnu));
	}

	public Result rubroGraba(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		Rubro form = formFactory.form(Rubro.class).withDirectFieldAccess(true).bindFromRequest(request).get();
		if (form.getNombre() == null) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgErrorFormulario));
		}else {
			try (Connection con = dbWrite.getConnection()){
				if(Rubro.create(con, s.baseDato, form)) {
					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "rubro", (long)0, "create", "crea nuevo rubro");
					return redirect("/routes2/rubroMantencion/");
				}else {
					return ok(mensajes.render("/routes2/rubroMantencion/","El rubro ya existe"));
				}
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result rubroModifica(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgErrorFormulario));
		}else {
			Long id_rubro = Long.parseLong(form.get("id_rubro").trim());
			try (Connection con = dbRead.getConnection()){
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				Rubro rubro = Rubro.find(con, s.baseDato, id_rubro);
				return ok(rubroModifica.render(mapeoDiccionario,mapeoPermiso,userMnu,rubro));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result modificaRubroPorCampoAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok("error");
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok("error");
		}else {
			try (Connection con = dbWrite.getConnection()){
				String campo = form.get("campo").trim();
				Long id_rubro = Long.parseLong(form.get("id_rubro").trim());
				String valor = form.get("valor").trim();
				if(campo.equals("nombre")) {
					if(Rubro.existe(con, s.baseDato, valor)) {
						return ok("existe");
					}
				}
				if(!Rubro.modificaPorCampo(con, s.baseDato, campo, id_rubro, valor)){
					return ok("error");
				}else {
					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "rubro", id_rubro, "update", "cambia el valor de: "+campo);
					return ok("");
				}
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("error");
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("error");
			}
		}
	}
	

}
