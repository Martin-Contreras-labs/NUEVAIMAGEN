package controllers;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import controllers.HomeController.Sessiones;
import models.forms.FormBaja;
import models.tables.ActaBaja;
import models.tables.Baja;
import models.tables.Compra;
import models.tables.Movimiento;
import models.utilities.DatabaseRead;
import models.utilities.Fechas;
import models.utilities.Registro;
import models.utilities.UserMnu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.Database;
import play.libs.Files.TemporaryFile;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.mensajes;
import viewsMnuBajas.html.*;

public class MnuBajas extends Controller {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private static final Database dbWrite = HomeController.dbWrite;
	private static final DatabaseRead dbRead = HomeController.dbRead;
	public static FormFactory formFactory = HomeController.formFactory;
	public static String msgError = HomeController.msgError;
	public static String msgErrorFormulario = HomeController.msgErrorFormulario;
	public static String msgSinPermiso = HomeController.msgSinPermiso;
	private static final String msgReport = HomeController.msgReport;
	
	
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdoubleCompra = new DecimalFormat("#,##0.00");
	
	
	//============================================================
    // MNU bajaIngreso   Bajas/Ingresar Actas
    //============================================================

    public Result bajaIngreso(Http.Request request) {
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
		if(mapeoPermiso.get("bajaIngreso")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			Long numeroActaBaja = ActaBaja.findNuevoNumero(con, s.baseDato);
			String fecha = Fechas.hoy().getFechaStrAAMMDD();
			List<List<String>> listEquipBodOrige = FormBaja.listEquipEnBodBaja(con, s.baseDato, mapeoPermiso, mapeoDiccionario);
			return ok(bajaIngreso.render(mapeoDiccionario,mapeoPermiso,userMnu,numeroActaBaja,listEquipBodOrige, fecha));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result bajaNuevo(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		FormBaja form = formFactory.form(FormBaja.class).withDirectFieldAccess(true).bindFromRequest(request).get();
		if (form.numero==null || form.id_actaBaja==null) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgErrorFormulario));
		}else {
			Http.MultipartFormData<TemporaryFile> body = request.body().asMultipartFormData();
			Http.MultipartFormData.FilePart<TemporaryFile> docAdjunto = body.getFile("docAdjunto");
			try (Connection con = dbWrite.getConnection()){
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				if(ActaBaja.existeNumero(con, s.baseDato, form.numero)) {
					String msg = "El numero de acta de baja ya fue utilizado, debe volver a ingresar el acta de baja";
					return ok(mensajes.render("/home/",msg));
				}
				if(FormBaja.create(con, s.baseDato, mapeoPermiso, form, docAdjunto)) {
					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "baja", (long)0, "create", "ingresa nueva acta de baja nro: "+form.numero);
				}
				return redirect("/home/");
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
    // MNU bajaConfirma   Bajas/Confirmar Bajas 
    //============================================================

    public Result confirmaBaja(Http.Request request) {
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
		if(mapeoPermiso.get("bajaConfirma")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<List<String>> listado = Baja.listParaConfirmar(con, s.baseDato);
			return ok(confirmaBaja.render(mapeoDiccionario,mapeoPermiso,userMnu,listado));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result bajaConfirmaIngreso(Http.Request request) {
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
			try (Connection con = dbWrite.getConnection()) {
				String arrIdBajas = form.get("cambiosDeEstados").trim();
				if (arrIdBajas.length() > 0) {
					String[] arr = arrIdBajas.split(";");
					String insertMovimiento = "";
					for (int i = 0; i < arr.length; i++) {
						String[] detalle = arr[i].split(",");
						String id_bodegaEmpresa = "1";
						String id_equipo = detalle[1];
						String id_tipoMovimiento = "2";
						String cantidad = detalle[2];
						String id_baja = detalle[0];
						String id_actaBaja = detalle[3];
						String fecha_actaBaja = detalle[4];
						insertMovimiento += "('"
								+ id_bodegaEmpresa + "','"
								+ id_equipo + "','"
								+ id_tipoMovimiento + "','"
								+ cantidad + "','"
								+ id_baja + "','"
								+ id_actaBaja + "','"
								+ fecha_actaBaja + "'),";
					}
					if (insertMovimiento.length() > 2) {
						insertMovimiento = insertMovimiento.substring(0, insertMovimiento.length() - 1);
						if (Movimiento.createMovimientoBaja(con, s.baseDato, insertMovimiento)) {
							String listIdBaja = "";
							for (int i = 0; i < arr.length; i++) {
								String[] detalle = arr[i].split(",");
								String id_baja = detalle[0];
								listIdBaja += id_baja + ",";
								FormBaja.cambiaAconfirmada(con, s.baseDato, Long.parseLong(id_baja));
							}
							Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "baja", (long) 0, "confirma", "confirma bajas id: " + listIdBaja);
						}
					}
				}
				return redirect("/home/");
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
    // MNU bajaModifica2   Bajas/Modificar Actas 
    //============================================================

    public Result bajaListaModifica(Http.Request request) {
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
		if(mapeoPermiso.get("bajaModifica2")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<ActaBaja> listActas = ActaBaja.allModificables(con, s.baseDato);
			return ok(bajaListaModifica.render(mapeoDiccionario,mapeoPermiso,userMnu,listActas));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result bajaModifica(Long id_actaBaja, Http.Request request) {
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
		if(mapeoPermiso.get("bajaModifica2")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			ActaBaja actaBaja = ActaBaja.find(con, s.baseDato, id_actaBaja);
			Map<Long, Baja> mapDetalleBajaModifica = Baja.mapDetalleBajaModifica(con, s.baseDato, id_actaBaja);
			List<List<String>> detalleBaja = FormBaja.detalleDeLaBaja(con, s.baseDato, mapeoPermiso, mapeoDiccionario, id_actaBaja, mapDetalleBajaModifica);
			return ok(bajaModifica.render(mapeoDiccionario,mapeoPermiso,userMnu,actaBaja,detalleBaja));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result bajaModificaGraba(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		FormBaja form = formFactory.form(FormBaja.class).withDirectFieldAccess(true).bindFromRequest(request).get();
		if (form.numero==null || form.id_actaBaja==null) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgErrorFormulario));
		}else {
			Http.MultipartFormData<TemporaryFile> body = request.body().asMultipartFormData();
			Http.MultipartFormData.FilePart<TemporaryFile> docAdjunto = body.getFile("docAdjunto");
			try (Connection con = dbWrite.getConnection()){
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				if(Baja.deleteNoConfirmadas(con, s.baseDato, form.id_actaBaja)) {
					FormBaja.update(con, s.baseDato, mapeoPermiso, form, docAdjunto);
					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "baja", form.id_actaBaja, "update", "modifica acta de baja id: "+form.id_actaBaja);
					return redirect("/home/");
				}
				return ok(mensajes.render("/",msgError));
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
    // MNU bajaListado   Bajas/Listar Bajas
    //============================================================
    
    public Result listBajasPorActa(Http.Request request) {
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
		if(mapeoPermiso.get("bajaListado")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<ActaBaja> listActas = ActaBaja.all(con, s.baseDato);
			return ok(listBajasPorActa.render(mapeoDiccionario,mapeoPermiso,userMnu,listActas));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result bajaActaPrint(Long id_actaBaja, Http.Request request) {
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
		if(mapeoPermiso.get("bajaListado")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			ActaBaja actaBaja = ActaBaja.find(con, s.baseDato, id_actaBaja);
			Map<Long, Baja> mapDetalleBaja = Baja.mapDetalleBaja(con, s.baseDato, id_actaBaja);
			List<List<String>> detalleBaja = FormBaja.detalleDeLaBaja(con, s.baseDato, mapeoPermiso, mapeoDiccionario, id_actaBaja, mapDetalleBaja);
			List<List<String>> detSinCantCero = new ArrayList<List<String>>();
			detalleBaja.forEach(x->{
				Double cantidad = Double.parseDouble(x.get(13).replaceAll(",", ""));
				if((double)cantidad > (double)0) {
					detSinCantCero.add(x);
				}
			});
			return ok(bajaActaPrint.render(mapeoDiccionario,mapeoPermiso,userMnu,actaBaja,detSinCantCero));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result listBajasPorEquipo(Http.Request request) {
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
		if(mapeoPermiso.get("bajaListado")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<List<String>> listaPorEquipo = Baja.listaBajaPorEquipo(con, s.baseDato);
			return ok(listBajasPorEquipo.render(mapeoDiccionario,mapeoPermiso,userMnu,listaPorEquipo));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result listBajasPorEquipoExcel(Http.Request request) {
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
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
			if(mapeoPermiso.get("bajaListado")==null) {
				logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
				return ok(mensajes.render("/",msgSinPermiso));
			}
			try (Connection con = dbRead.getConnection()){
				List<List<String>> listaPorEquipo = Baja.listaBajaPorEquipo(con, s.baseDato);
				File file = Baja.listBajasPorEquipoExcel(s.baseDato, mapeoDiccionario, listaPorEquipo);
				return ok(file,false,Optional.of("ListadoDeComprasPorCompra.xlsx"));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
    }
    
    public Result bajaEquipoPrint(Long id_equipo, Http.Request request) {
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
		if(mapeoPermiso.get("bajaListado")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<List<String>> listaCompra = Compra.allPorEquipo(con, s.baseDato, id_equipo);
			List<List<String>> listaBaja = Baja.allPorEquipo(con, s.baseDato, id_equipo);
			return ok(bajaEquipoPrint.render(mapeoDiccionario,mapeoPermiso,userMnu,listaCompra,listaBaja));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    
    

}
