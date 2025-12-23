package controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import controllers.HomeController.Sessiones;
import models.calculo.Inventarios;
import models.qr.GeneraQr;
import models.qr.QrAtributoEquipo;
import models.qr.QrEquipo;
import models.qr.QrTipoContenido;
import models.qr.QrTransacEquipo;
import models.reports.ReportTrazabilidades;
import models.tables.*;
import models.utilities.*;
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
import viewsMnuQr.html.*;

public class MnuQr extends Controller {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private static final Database dbWrite = HomeController.dbWrite;
	private static final DatabaseRead dbRead = HomeController.dbRead;
	public static FormFactory formFactory = HomeController.formFactory;
	public static String msgError = HomeController.msgError;
	public static String msgErrorFormulario = HomeController.msgErrorFormulario;
	public static String msgSinPermiso = HomeController.msgSinPermiso;
	private static final String msgReport = HomeController.msgReport;

	static DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00",symbols);
	
	
	//============================================================
    // MNU qrAdminEquipos   Qr/Administrar QR
    //============================================================
	
	public Result qrListaEquipos(Http.Request request) {
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
		if(mapeoPermiso.get("qrAdminEquipos")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<QrEquipo> listEquipQr = QrEquipo.all(con, s.baseDato);
			return ok(qrListaEquipos.render(mapeoDiccionario,mapeoPermiso,userMnu,listEquipQr));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}
	
	public Result qrSelectEquipos(Http.Request request) {
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
		if(mapeoPermiso.get("qrAdminEquipos")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<QrEquipo> listEquipNoQr = QrEquipo.allSelectEquipoNoEnQr(con, s.baseDato);
			return ok(qrSelectEquipos.render(mapeoDiccionario,mapeoPermiso,userMnu,listEquipNoQr));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}
	
	public Result qrAgregaEquipo(Http.Request request) {
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
			return ok(mensajes.render("/",msgErrorFormulario));
		}else {
			try (Connection con = dbWrite.getConnection()){
				Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				Equipo equipo = Equipo.find(con, s.baseDato, id_equipo);
				String nombreArchivo = GeneraQr.generaQR(mapeoDiccionario.get("nEmpresa"), s.baseDato, equipo.codigo, id_equipo, "equipo", mapeoDiccionario.get("web"));
				if(!nombreArchivo.equals("0")) {
					if(QrEquipo.agregaEquipo(con, s.baseDato, id_equipo, nombreArchivo)) {
						Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "qrEquipo", (long)0, "insert", "agrega o actualiza equipo QR con codigo: "+equipo.getCodigo());
						return redirect("/qrListaEquipos/");
					}
				}
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
				return ok(mensajes.render("/home/", msgReport));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
    	}
	}
	
	public Result qrCambiaEstadoEquipos(Http.Request request) {
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
			return ok(mensajes.render("/",msgErrorFormulario));
		}else {
			try (Connection con = dbWrite.getConnection()){
				Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
				Long activo = Long.parseLong(form.get("activo").trim());
				QrEquipo.cambiarEstado(con, s.baseDato, id_equipo, activo);
				Equipo equipo = Equipo.find(con, s.baseDato, id_equipo);
				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "qrEquipo", id_equipo, "update", "cambiar el estado del equipo codigo "+equipo.getCodigo());
				return redirect("/qrListaEquipos/");
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
    	}
	}
	
	public Result qrRevisarDatos(Http.Request request) {
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
			return ok(mensajes.render("/",msgErrorFormulario));
		}else {
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
			try (Connection con = dbRead.getConnection()){
				Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
				Equipo equipo = Equipo.find(con, s.baseDato, id_equipo);
				List<List<String>> listAtribEquipo = MnuQr.atributosPorEquipo(con, s.baseDato, equipo.id_grupo, id_equipo);
				List<List<String>> listaFiltrada = new ArrayList<List<String>>();
				for(int i=0; i<listAtribEquipo.size(); i++) {
					if(listAtribEquipo.get(i).get(4).equals("1") || listAtribEquipo.get(i).get(4).equals("0")) {
						listaFiltrada.add(listAtribEquipo.get(i));
					}
				}
				List<QrTransacEquipo> listaTransac = QrTransacEquipo.allPorIdEquipo(con, s.baseDato, id_equipo);
				return ok(qrRevisarDatos.render(mapeoDiccionario,mapeoPermiso,userMnu,equipo, listaFiltrada, listaTransac));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
    	}
	}

	public Result qrRevisarDatosAllVigentes(Http.Request request) {
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
		if(mapeoPermiso.get("qrAdminEquipos")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<QrTransacEquipo> listaTransacAll = QrTransacEquipo.allEquiposActivos(con, s.baseDato);
			Map<Long,Equipo> mapEquipo = Equipo.mapAllVigentes(con,s.baseDato);
			List<List<String>> lista = new ArrayList<List<String>>();
			for(int i=0; i<listaTransacAll.size(); i++) {
				Equipo equipoAux = mapEquipo.get(listaTransacAll.get(i).id_equipo);
				if(equipoAux != null) {
					List<String> aux = new ArrayList<String>();

					aux.add(equipoAux.codigo);		// 0
					aux.add(equipoAux.nombre);		// 1
					aux.add(listaTransacAll.get(i).campo);		// 2

					aux.add(listaTransacAll.get(i).id_qrTipoContenido.toString());		// 3
					aux.add(listaTransacAll.get(i).contenido);		// 4
					aux.add(listaTransacAll.get(i).extencion);		// 5
					aux.add(listaTransacAll.get(i).tipo);			// 6

					aux.add(listaTransacAll.get(i).colorCelda);				// 7
					aux.add(listaTransacAll.get(i).fechaVencimiento);		// 8

					aux.add(listaTransacAll.get(i).id_equipo.toString());				// 9
					aux.add(listaTransacAll.get(i).id_qrAtributoEquipo.toString());		// 10
					aux.add(listaTransacAll.get(i).activo.toString());					// 11

					lista.add(aux);

				}
			}
			return ok(qrRevisarDatosALL.render(mapeoDiccionario,mapeoPermiso,userMnu, lista));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}


	public Result qrRevisarDatosAllVigentesExcel(Http.Request request) {
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
		if(mapeoPermiso.get("qrAdminEquipos")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<QrTransacEquipo> listaTransacAll = QrTransacEquipo.allEquiposActivos(con, s.baseDato);
			Map<Long,Equipo> mapEquipo = Equipo.mapAllVigentes(con,s.baseDato);
			List<List<String>> lista = new ArrayList<List<String>>();
			for(int i=0; i<listaTransacAll.size(); i++) {
				Equipo equipoAux = mapEquipo.get(listaTransacAll.get(i).id_equipo);
				if(equipoAux != null) {
					List<String> aux = new ArrayList<String>();

					aux.add(equipoAux.codigo);		// 0
					aux.add(equipoAux.nombre);		// 1
					aux.add(listaTransacAll.get(i).campo);		// 2

					aux.add(listaTransacAll.get(i).id_qrTipoContenido.toString());		// 3
					aux.add(listaTransacAll.get(i).contenido);		// 4
					aux.add(listaTransacAll.get(i).extencion);		// 5
					aux.add(listaTransacAll.get(i).tipo);			// 6

					aux.add(listaTransacAll.get(i).colorCelda);				// 7
					aux.add(listaTransacAll.get(i).fechaVencimiento);		// 8

					aux.add(listaTransacAll.get(i).id_equipo.toString());				// 9
					aux.add(listaTransacAll.get(i).id_qrAtributoEquipo.toString());		// 10
					aux.add(listaTransacAll.get(i).activo.toString());					// 11

					lista.add(aux);

				}
			}
			Fechas hoy = Fechas.hoy();
			File file = QrTransacEquipo.qrRevisarDatosAllVigentesExcel(s.baseDato, mapeoDiccionario, lista, hoy);
			return ok(file,false,Optional.of("RevisaDatosQrTodos.xlsx"));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}
	
	public static List<List<String>> atributosPorEquipo (Connection con, String base, Long id_grupo, Long id_equipo){
		List<Atributo> listAtributos = Atributo.allXGrupo(con, base, id_grupo);
		List<List<String>> lista = new ArrayList<List<String>>();
		for(int i=0; i<listAtributos.size(); i++) {
			List<String> valor = Atributo.findValorAtributoEquipo(con, base, id_equipo,listAtributos.get(i).id);
			List<String> aux = new ArrayList<String>();
			aux.add(listAtributos.get(i).atributo+" ("+listAtributos.get(i).unidad+")");
			if(listAtributos.get(i).esNumerico==1) {
				aux.add(valor.get(3));
			}else {
				aux.add(valor.get(2));
			}
			aux.add(valor.get(0)); //2 id_equipo
			aux.add(valor.get(1)); //3 id_atributo
			aux.add(valor.get(4)); //4 publico
			lista.add(aux);
		}
		return(lista);
	}
	
	public Result qrCambiarPubEquipo(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok("{ \"status\": false}").as("application/json");
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			return ok(mensajes.render("/",msgErrorFormulario));
		}else {
			try (Connection con = dbWrite.getConnection()){
				String id_equipo = form.get("id_equipo").trim();
				String id_atributo = form.get("id_atributo").trim();
				String publico = form.get("publico").trim();
				if(QrEquipo.cambiarPubEquipo(con, s.baseDato, id_equipo, id_atributo, publico)) {
					return ok("{ \"status\": true}").as("application/json");
				}else {
					return ok("{ \"status\": false}").as("application/json");
				}
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("{ \"status\": false}").as("application/json");
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("{ \"status\": false}").as("application/json");
			}
    	}
	}
	
	public Result qrCambiarPubQr(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok("{ \"status\": false}").as("application/json");
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			return ok(mensajes.render("/",msgErrorFormulario));
		}else {
			try (Connection con = dbWrite.getConnection()){
				String id_equipo = form.get("id_equipo").trim();
				String id_qrAtributoEquipo = form.get("id_qrAtributoEquipo").trim();
				String publico = form.get("publico").trim();
				if(QrEquipo.cambiarPubQr(con, s.baseDato,id_equipo,id_qrAtributoEquipo,publico)) {
					return ok("{ \"status\": true}").as("application/json");
				}else {
					return ok("{ \"status\": false}").as("application/json");
				}
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("{ \"status\": false}").as("application/json");
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("{ \"status\": false}").as("application/json");
			}
    	}
	}
	
	public Result qrEditEquipo(Http.Request request) {
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
			return ok(mensajes.render("/",msgErrorFormulario));
		}else {
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
			try (Connection con = dbRead.getConnection()){
				Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
				QrEquipo qrEquipo = QrEquipo.find(con, s.baseDato, id_equipo);
				List<QrTransacEquipo> listaTransac = QrTransacEquipo.allPorIdEquipo(con, s.baseDato, id_equipo);
				List<QrAtributoEquipo> listaCampos = QrAtributoEquipo.all(con, s.baseDato);
				List<QrAtributoEquipo> listaCamposFiltrado = QrAtributoEquipo.allFiltrado(con, s.baseDato, listaTransac);
				return ok(qrEditEquipo.render(mapeoDiccionario,mapeoPermiso,userMnu, qrEquipo, listaTransac, listaCampos, listaCamposFiltrado));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
    	}
	}
	
	public Result qrAgregaCampoEquipo(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok("{ \"status\": false}").as("application/json");
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			return ok(mensajes.render("/",msgErrorFormulario));
		}else {
			try (Connection con = dbWrite.getConnection()){
				String idEquipo = form.get("idEquipo").trim();
				String idCampo = form.get("idCampo").trim();
				if(QrTransacEquipo.create(con, s.baseDato, idEquipo, idCampo)) {
					QrTransacEquipo qrTransacEquipo = QrTransacEquipo.find(con, s.baseDato, idEquipo, idCampo);
					return ok("{ \"status\": true,\"orden\": "+qrTransacEquipo.orden+"}").as("application/json");
				}else {
					return ok("{ \"status\": false}").as("application/json");
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
	
	public Result qrActualizaPorCampo(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok("{ \"status\": false}").as("application/json");
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			return ok(mensajes.render("/",msgErrorFormulario));
		}else {
			try (Connection con = dbWrite.getConnection()){
				String idEquipo = form.get("idEquipo").trim();
				String idCampo = form.get("idCampo").trim();
				String campoMySql = form.get("campoMySql").trim();
				String valor = form.get("valor").trim();
				if(QrTransacEquipo.updatePorCampo(con, s.baseDato, idEquipo, idCampo, campoMySql, valor)) {
					return ok("{ \"status\": true}").as("application/json");
				}else {
					return ok("{ \"status\": false}").as("application/json");
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
	
	public Result qrEliminaCampoEquipo(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok("{ \"status\": false}").as("application/json");
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			return ok(mensajes.render("/",msgErrorFormulario));
		}else {
			try (Connection con = dbWrite.getConnection()){
				String idEquipo = form.get("idEquipo").trim();
				String idCampo = form.get("idCampo").trim();
				QrTransacEquipo transac = QrTransacEquipo.find(con,s.baseDato, idEquipo, idCampo);
				if(QrTransacEquipo.eliminaCampo(con, s.baseDato, transac)) {
					return ok("{ \"status\": true}").as("application/json");
				}else {
					return ok("{ \"status\": false}").as("application/json");
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
	
	public Result qrGrabaAnexoEquipo(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok("{ \"status\": false}").as("application/json");
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			return ok(mensajes.render("/",msgErrorFormulario));
		}else {
			Http.MultipartFormData<TemporaryFile> body = request.body().asMultipartFormData();
			Http.MultipartFormData.FilePart<TemporaryFile> archivo = body.getFile("anexo");
			try {
				if (archivo != null) {
					String idEquipo = form.get("idEquipo").trim();
					String idCampo = form.get("idCampo").trim();
					String extencion = form.get("extencion").trim();
					String nombreArchivoSinExtencion = "QR_Equipo_" + extencion.trim().toUpperCase() + "_" + idEquipo + "_" + idCampo;
					String nombreArchivoConExtencion = Archivos.grabarArchivos(archivo, s.baseDato, nombreArchivoSinExtencion);
					return ok("{ \"status\": true,\"archivo\":\"" + nombreArchivoConExtencion + "\"}").as("application/json");
				} else {
					return ok("{ \"status\": false}").as("application/json");
				}
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}
	
	
	
	//============================================================
    // MNU qrAdminEquipos   Qr/Atributos QR
    //============================================================
	
	public Result qrListaAtributoEquipos(Http.Request request) {
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
		if(mapeoPermiso.get("qrAdminEquipos")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<QrAtributoEquipo> listAtributos = QrAtributoEquipo.all(con, s.baseDato);
			return ok(qrListaAtributoEquipos.render(mapeoDiccionario,mapeoPermiso,userMnu,listAtributos));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}
	
	public Result qrEditarAtributoEquipo(Http.Request request, Long id_qrAtributoEquipo) {
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
		if(mapeoPermiso.get("qrAdminEquipos")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			QrAtributoEquipo atributoEquipo = QrAtributoEquipo.find(con, s.baseDato, id_qrAtributoEquipo);
			Long bloquearTipo = QrAtributoEquipo.bloquearTipo(con, s.baseDato, id_qrAtributoEquipo);
			List<QrTipoContenido> listaTipo = QrTipoContenido.all(con, s.baseDato);
			return ok(qrEditarAtributoEquipo.render(mapeoDiccionario,mapeoPermiso,userMnu, atributoEquipo, listaTipo, bloquearTipo));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}
	
	public Result qrEditarAtributoEquipo2(Http.Request request) {
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
			return redirect("/qrListaAtributoEquipos/");
		}else {
			try (Connection con = dbWrite.getConnection()){
				String idTipo = form.get("idTipo").trim();
				String nombre = form.get("nombre").trim();
				String idQrAtributoEquipo = form.get("idQrAtributoEquipo").trim();
				QrAtributoEquipo.update(con, s.baseDato, idTipo, nombre, idQrAtributoEquipo);
				return redirect("/qrListaAtributoEquipos/");
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
    	}
	}
	
	public Result qrDeleteAtributoEquipo(Http.Request request, Long id_qrAtributoEquipo) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		try (Connection con = dbWrite.getConnection()){
			if(!QrAtributoEquipo.delete(con, s.baseDato, id_qrAtributoEquipo)){
				String mensaje = "No puede ser eliminado, esta en uso o esta en el historico";
				return ok(mensajes.render("qrListaAtributoEquipos/",mensaje));
			}
			return redirect("/qrListaAtributoEquipos/");
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}
	
	public Result qrAgregaAtributoEquipo(Http.Request request) {
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
		if(mapeoPermiso.get("qrAdminEquipos")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<QrTipoContenido> listaTipo = QrTipoContenido.all(con,s.baseDato);
			return ok(qrAgregaAtributoEquipo.render(mapeoDiccionario,mapeoPermiso,userMnu,listaTipo));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}
	
	public Result qrAgregaAtributoEquipo2(Http.Request request) {
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
			return redirect("/qrListaAtributoEquipos/");
		}else {
			try (Connection con = dbWrite.getConnection()){
				String idTipo = form.get("idTipo").trim();
				String nombre = form.get("nombre").trim();
				QrAtributoEquipo.create(con, s.baseDato, idTipo, nombre);
				return redirect("/qrListaAtributoEquipos/");
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
    // MNU qrAdminEquipos   Qr/Print QR Equipos
    //============================================================
	
	public Result qrPrintQrEquipos(Http.Request request) {
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
		if(mapeoPermiso.get("qrAdminEquipos")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<List<List<String>>> lista = QrEquipo.listaDeQrEquipos(con, s.baseDato);
			return ok(qrPrintQrEquipos.render(mapeoDiccionario,mapeoPermiso,userMnu,lista));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}
	
	public Result qrPrintQrEquiposWord(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("qrAdminEquipos")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<List<List<String>>> lista = QrEquipo.listaDeQrEquipos(con, s.baseDato);
			File file = QrEquipo.reporteEnWord(con, s.baseDato, lista, mapeoDiccionario);
			if(file!=null) {
				return ok(file,false,Optional.of("Listado_de_QR.docx"));
			}else {
				return ok("");
			}
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	// ****************************************************
	// LECTURA DEL QR
	// ****************************************************
	
	public Result leeUnQr(Http.Request request, String strEncoded) {
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		JsonNode jsonObject = null;
		try {
			byte[] decodedStr = Base64.getDecoder().decode( strEncoded );
			String strDecoded = new String( decodedStr, "utf-8" );
			ObjectMapper mapper = new ObjectMapper();
			jsonObject = mapper.readTree(strDecoded);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String nEmpresa = jsonObject.findPath("empresa").toString().replaceAll("\"", "");
		String base = jsonObject.findPath("base").toString().replaceAll("\"", "");
		String id_equipo = jsonObject.findPath("id_equipo").toString().replaceAll("\"", "");
		String tipo = jsonObject.findPath("tipo").toString().replaceAll("\"", "");
		if(tipo.equals("equipo")) {
			try (Connection con = dbRead.getConnection()){
				Equipo equipo = Equipo.find(con, base, Long.parseLong(id_equipo));
				QrEquipo qrEquipo = QrEquipo.find(con, base, Long.parseLong(id_equipo));
				if(qrEquipo.activo==0) {
					return ok(msgNoDisponible.render(nEmpresa.toUpperCase(), base, equipo));
				}
				Map<Long,Double> mapStockPorBod = Inventarios.mapIdBodconCantPorUnEquipo(con, base, Long.parseLong(id_equipo.trim()));
				Double stock = (double) 0;
				Long id_bodega = (long) 0;
				String ubicacion = "";
				if(mapStockPorBod!=null) {
					for (Map.Entry<Long,Double> entry : mapStockPorBod.entrySet()) {
					    id_bodega = entry.getKey();
					    stock += entry.getValue();
					}
					BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, base, id_bodega);
					if(bodega!=null) {
						ubicacion = bodega.nombre;
					}
				}
				List<QrTransacEquipo> listTransacEquipo = QrTransacEquipo.allPorIdEquipo(con, base, Long.parseLong(id_equipo));
				Long conBtnMantencion = 0L;
				if(Parametros.validaParametro(con, base,"mnuMANTENCION")){
					Map<Long,PlanMantencion> mapAllEquiposVigentes = PlanMantencion.mapAllEquiposVigentes(con, base);
					PlanMantencion plan = mapAllEquiposVigentes.get(equipo.getId());
					if(plan != null) {
						conBtnMantencion = 1L;
					}
				}
				return ok(inicioQr.render(nEmpresa.toUpperCase(), base, equipo, stock, ubicacion, (long) listTransacEquipo.size(), conBtnMantencion))
						.addingToSession(request, "strEncoded", strEncoded);
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, "", "", e);
				return ok("ERROR");
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, "", "", e);
				return ok("ERROR");
			}
		}else {
			//AQUI PROGRAMAR ACCESO PERSONAS
			return ok("AQUI PROGRAMAR ACCESO PERSONAS");
		}
	}
	
	public Result viewImgQr (Http.Request request, String base, String name){
		InputStream auxfile = Archivos.leerArchivo(base+"/"+name);
   		File file = Archivos.parseInputStreamToFile(auxfile);
   		//response().setHeader("Content-Disposition", "inline");
		if(!file.exists()) { 
			return ok(""); 
		}else { 
			return ok(file,false,Optional.of(name)); 
		}
	}
	
	public Result qrImagen(Http.Request request) {
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		Sessiones s = new Sessiones(request, "soloQr");
    	if(s.strEncoded!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
    		if (form.hasErrors()) {
    			return ok(mensajesQr.render("leeUnQr/"+s.strEncoded,"No existe imagen a mostrar"));
    	    } else {
    	    	try (Connection con = dbRead.getConnection()){
					String nEmpresa = form.get("nEmpresa");
					String base = form.get("base");
					Long id_equipo = Long.parseLong(form.get("id_equipo"));
					Double stock = Double.parseDouble(form.get("stock"));
					String ubicacion = form.get("ubicacion");
    	    		Equipo equipo = Equipo.find(con, base, id_equipo);
    	    		if(equipo.img.equals("0")) {
            			return ok(mensajesQr.render("leeUnQr/"+s.strEncoded,"No existe imagen a mostrar"));
            		}else {
            			return ok(imagenQr.render(nEmpresa.toUpperCase(), base, equipo, stock, ubicacion));
            		}
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, "", "", e);
					return ok("ERROR");
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, "", "", e);
					return ok("ERROR");
				}
    	    }
    	}else {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, "", "");
			return ok("ERROR");
    	}
	}
	
	public Result qrAtributo(Http.Request request) {
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		Sessiones s = new Sessiones(request, "soloQr");
    	if(s.strEncoded!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
    		if (form.hasErrors()) {
    			return ok(mensajesQr.render("leeUnQr/"+s.strEncoded,"No existen atributos a mostrar"));
    	    } else {
    	    	try (Connection con = dbRead.getConnection()){
					String nEmpresa = form.get("nEmpresa");
					String base = form.get("base");
					Long id_equipo = Long.parseLong(form.get("id_equipo"));
					Double stock = Double.parseDouble(form.get("stock"));
					String ubicacion = form.get("ubicacion");
    				Equipo equipo = Equipo.find(con, base, id_equipo);
    	    		List<List<String>> lista = MnuQr.atributosPorEquipo(con, base, equipo.id_grupo, id_equipo);
    	    		List<List<String>> listaFiltrada = new ArrayList<List<String>>();
    	    		for(int i=0; i<lista.size(); i++) {
    	    			if(lista.get(i).get(4).equals("1")) {
    	    				listaFiltrada.add(lista.get(i));
    	    			}
    	    		}
    	    		return ok(atributoQr.render(nEmpresa.toUpperCase(), base, equipo, stock, ubicacion, listaFiltrada));
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, "", "", e);
					return ok("ERROR");
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, "", "", e);
					return ok("ERROR");
				}
			}
		}else {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, "", "");
			return ok("ERROR");
		}
	}
	
	public Result qrUbicacion(Http.Request request) {
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		Sessiones s = new Sessiones(request, "soloQr");
    	if(s.strEncoded!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
    		if (form.hasErrors()) {
				return ok(mensajesQr.render("leeUnQr/"+s.strEncoded,"No existe ubicación a mostrar"));
		    } else {
		    	try (Connection con = dbRead.getConnection()){
					String nEmpresa = form.get("nEmpresa");
					String base = form.get("base");
					Long id_equipo = Long.parseLong(form.get("id_equipo"));
					Double stock = Double.parseDouble(form.get("stock"));
					String ubicacion = form.get("ubicacion");
		    		Equipo equipo = Equipo.find(con, base, id_equipo);
					Map<Long,Double> mapStockPorBod = Inventarios.mapIdBodconCantPorUnEquipo(con, base, id_equipo);
					List<List<String>> lista = new ArrayList<List<String>>();
					Map<Long,BodegaEmpresa> mapBodega = BodegaEmpresa.mapAll(con, base);
					if(mapStockPorBod!=null) {
						for (Map.Entry<Long,Double> entry : mapStockPorBod.entrySet()) {
							Long id_bodega = entry.getKey();
							BodegaEmpresa bodega = mapBodega.get(id_bodega);
						   if(bodega!=null) {
							   List<String> aux = new ArrayList<String>();
							   if((long) bodega.getEsInterna() == (long)1) {
								   aux.add("INTERNA");
							   }else {
								   aux.add("CLIENTE");
							   }
							   aux.add(bodega.getNombre());
							   aux.add(entry.getValue().toString());
							   if(nEmpresa.equals("GFS") ) {
								   if( bodega.getEsInterna() == (long)1) {
									   lista.add(aux);
								   }
							   }else {
								   lista.add(aux);
							   }
						   }
						}
					}
					//ORDENA LA LISTA DESC
					for(int j=0;j<lista.size();j++) {
			            for(int i=0;i<lista.size()-j;i++) {
			                if (i+1!=lista.size() && lista.get(i+1).get(0).compareToIgnoreCase(lista.get(i).get(0))>0) {
			                    List<String> auxOrden;
			                    auxOrden=lista.get(i);
			                    lista.set(i,lista.get(i+1));
			                    lista.set(i+1, auxOrden);
			                }
			            }
			        }
    	    		return ok(ubicacionQr.render(nEmpresa.toUpperCase(), base, equipo, stock, ubicacion, lista));
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, "", "", e);
					return ok("ERROR");
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, "", "", e);
					return ok("ERROR");
				}
			}
		}else {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, "", "");
			return ok("ERROR");
		}
	}

	public Result qrTrazabilidad(Http.Request request) {
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		Sessiones s = new Sessiones(request, "soloQr");
    	if(s.strEncoded!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
    		if (form.hasErrors()) {
    			return ok(mensajesQr.render("leeUnQr/"+s.strEncoded,"No existe ubicación a mostrar"));
		    } else {
		    	try (Connection con = dbRead.getConnection()){
					String nEmpresa = form.get("nEmpresa");
					String base = form.get("base");
					Long id_equipo = Long.parseLong(form.get("id_equipo"));
					Double stock = Double.parseDouble(form.get("stock"));
					String ubicacion = form.get("ubicacion");
		    		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(base);
		    		Equipo equipo = Equipo.find(con, base, id_equipo);
		    		List<List<String>> lista = ReportTrazabilidades.trazaEquipo(con, base, equipo.id, mapeoDiccionario, s.aplicaPorSucursal, s.id_sucursal);
    	    		return ok(trazabilidadQr.render(nEmpresa.toUpperCase(), base, equipo, stock, ubicacion, lista));
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, "", "", e);
					return ok("ERROR");
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, "", "", e);
					return ok("ERROR");
				}
			}
		}else {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, "", "");
			return ok("ERROR");
		}
	}

	public Result qrHistoria(Http.Request request) {
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		Sessiones s = new Sessiones(request, "soloQr");
    	if(s.strEncoded!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
    		if (form.hasErrors()) {
    			return ok(mensajesQr.render("leeUnQr/"+s.strEncoded,"No existe ubicación a mostrar"));
		    } else {
		    	try (Connection con = dbRead.getConnection()){
					String nEmpresa = form.get("nEmpresa");
					String base = form.get("base");
					Long id_equipo = Long.parseLong(form.get("id_equipo"));
					Double stock = Double.parseDouble(form.get("stock"));
					String ubicacion = form.get("ubicacion");
		    		Equipo equipo = Equipo.find(con, base, id_equipo);
		    		List<HojaVida> lista = HojaVida.allPorEquipo(con, base, equipo.getId());
    	    		return ok(historiaQr.render(nEmpresa.toUpperCase(), base, equipo, stock, ubicacion, lista));
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, "", "", e);
					return ok("ERROR");
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, "", "", e);
					return ok("ERROR");
				}
			}
		}else {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, "", "");
			return ok("ERROR");
		}
	}
	
	
	public Result qrAtributosQr(Http.Request request) {
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		Sessiones s = new Sessiones(request, "soloQr");
    	if(s.strEncoded!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
    		if (form.hasErrors()) {
    			return ok(mensajesQr.render("leeUnQr/"+s.strEncoded,"No existe ubicación a mostrar"));
		    } else {
		    	try (Connection con = dbRead.getConnection()){
					String nEmpresa = form.get("nEmpresa");
					String base = form.get("base");
					Long id_equipo = Long.parseLong(form.get("id_equipo"));
					Double stock = Double.parseDouble(form.get("stock"));
					String ubicacion = form.get("ubicacion");
		    		Equipo equipo = Equipo.find(con, base, id_equipo);
		    		List<QrTransacEquipo> lista = QrTransacEquipo.allPorIdEquipoSoloActivos(con, base, id_equipo);
    	    		return ok(atributosQrEquipo.render(nEmpresa.toUpperCase(), base, equipo, stock, ubicacion, lista));
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, "", "", e);
					return ok("ERROR");
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, "", "", e);
					return ok("ERROR");
				}
			}
		}else {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, "", "");
			return ok("ERROR");
		}
	}
	
	public Result qrImagenQr(Http.Request request){
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		Sessiones s = new Sessiones(request, "soloQr");
    	if(s.strEncoded!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
    		if (form.hasErrors()) {
    			return ok(mensajesQr.render("leeUnQr/"+s.strEncoded,"No existe imagen a mostrar"));
		    } else {
		    	try (Connection con = dbRead.getConnection()){
					String nEmpresa = form.get("nEmpresa");
					String base = form.get("base");
					Long id_equipo = Long.parseLong(form.get("id_equipo"));
					Double stock = Double.parseDouble(form.get("stock"));
					String ubicacion = form.get("ubicacion");
					String archivo = form.get("archivo");
					String tipo = form.get("tipo");
		    		Equipo equipo = Equipo.find(con, base, id_equipo);
		    		if(tipo.equals("Archivo PDF")) {
	    				InputStream auxfile = Archivos.leerArchivo(base+"/"+archivo);
	    	       		File file = Archivos.parseInputStreamToFile(auxfile);
	    				return ok(file,false,Optional.of(archivo)); 
	    			}else {
	    				return ok(imagenQrQr.render(nEmpresa.toUpperCase(), base, equipo, stock, ubicacion, archivo));
	    			}
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, "", "", e);
					return ok("ERROR");
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, "", "", e);
					return ok("ERROR");
				}
			}
		}else {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, "", "");
			return ok("ERROR");
		}
	}
	
	
}
