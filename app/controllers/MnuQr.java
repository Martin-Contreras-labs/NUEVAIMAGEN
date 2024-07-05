package controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import models.tables.Atributo;
import models.tables.BodegaEmpresa;
import models.tables.Equipo;
import models.tables.HojaVida;
import models.utilities.Archivos;
import models.utilities.Registro;
import models.utilities.UserMnu;
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
	public static Database db = HomeController.dbWrite;
	public static FormFactory formFactory = HomeController.formFactory;
	public static String msgError = HomeController.msgError;
	public static String msgErrorFormulario = HomeController.msgErrorFormulario;
	public static String msgSinPermiso = HomeController.msgSinPermiso;
	
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	
	
	//============================================================
    // MNU qrAdminEquipos   Qr/Administrar QR
    //============================================================
	
	public Result qrListaEquipos(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("qrAdminEquipos")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<QrEquipo> listEquipQr = QrEquipo.all(con, s.baseDato);
    			con.close();
    			return ok(qrListaEquipos.render(mapeoDiccionario,mapeoPermiso,userMnu,listEquipQr));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result qrSelectEquipos(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("qrAdminEquipos")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<QrEquipo> listEquipNoQr = QrEquipo.allSelectEquipoNoEnQr(con, s.baseDato);
    			con.close();
    			return ok(qrSelectEquipos.render(mapeoDiccionario,mapeoPermiso,userMnu,listEquipNoQr));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result qrAgregaEquipo(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
	       		try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			Equipo equipo = Equipo.find(con, s.baseDato, id_equipo);
	    			String nombreArchivo = GeneraQr.generaQR(mapeoDiccionario.get("nEmpresa"), s.baseDato, equipo.codigo, id_equipo, "equipo", mapeoDiccionario.get("web"));
	    			if(!nombreArchivo.equals("0")) {
	    				if(QrEquipo.agregaEquipo(con, s.baseDato, id_equipo, nombreArchivo)) {
	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "qrEquipo", (long)0, "insert", "agrega o actualiza equipo QR con codigo: "+equipo.getCodigo());
	    					con.close();
	    					return redirect("/qrListaEquipos/");
	    				}
	    			}
	    			con.close();
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result qrCambiaEstadoEquipos(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
	       		Long activo = Long.parseLong(form.get("activo").trim());
	       		try {
	    			Connection con = db.getConnection();
	    			QrEquipo.cambiarEstado(con, s.baseDato, id_equipo, activo);
	    			Equipo equipo = Equipo.find(con, s.baseDato, id_equipo);
	    			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "qrEquipo", id_equipo, "update", "cambiar el estado del equipo codigo "+equipo.getCodigo());
	    			con.close();
					return redirect("/qrListaEquipos/");
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result qrRevisarDatos(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
	       		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			Equipo equipo = Equipo.find(con, s.baseDato, id_equipo);
	    			List<List<String>> listAtribEquipo = MnuQr.atributosPorEquipo(con, s.baseDato, equipo.id_grupo, id_equipo);
	    			List<List<String>> listaFiltrada = new ArrayList<List<String>>();
	        		for(int i=0; i<listAtribEquipo.size(); i++) {
	        			if(listAtribEquipo.get(i).get(4).equals("1") || listAtribEquipo.get(i).get(4).equals("0")) {
	        				listaFiltrada.add(listAtribEquipo.get(i));
	        			}
	        		}
	    			List<QrTransacEquipo> listaTransac = QrTransacEquipo.allPorIdEquipo(con, s.baseDato, id_equipo);
	    			con.close();
	    			return ok(qrRevisarDatos.render(mapeoDiccionario,mapeoPermiso,userMnu,equipo, listaFiltrada, listaTransac));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
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
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String id_equipo = form.get("id_equipo").trim();
	       		String id_atributo = form.get("id_atributo").trim();
	       		String publico = form.get("publico").trim();
	       		try {
	    			Connection con = db.getConnection();
	    			if(QrEquipo.cambiarPubEquipo(con, s.baseDato, id_equipo, id_atributo, publico)) {
		    			con.close();
		    			return ok("{ \"status\": true}").as("application/json");
		    		}else {
		    			con.close();
		    			return ok("{ \"status\": false}").as("application/json");
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
	
	public Result qrCambiarPubQr(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String id_equipo = form.get("id_equipo").trim();
	       		String id_qrAtributoEquipo = form.get("id_qrAtributoEquipo").trim();
	       		String publico = form.get("publico").trim();
	       		try {
	    			Connection con = db.getConnection();
	    			if(QrEquipo.cambiarPubQr(con, s.baseDato,id_equipo,id_qrAtributoEquipo,publico)) {
		    			con.close();
		    			return ok("{ \"status\": true}").as("application/json");
		    		}else {
		    			con.close();
		    			return ok("{ \"status\": false}").as("application/json");
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
	
	public Result qrEditEquipo(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
	       		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			QrEquipo qrEquipo = QrEquipo.find(con, s.baseDato, id_equipo);
	    			List<QrTransacEquipo> listaTransac = QrTransacEquipo.allPorIdEquipo(con, s.baseDato, id_equipo);
	    			List<QrAtributoEquipo> listaCampos = QrAtributoEquipo.all(con, s.baseDato);
	    			List<QrAtributoEquipo> listaCamposFiltrado = QrAtributoEquipo.allFiltrado(con, s.baseDato, listaTransac);
	    			con.close();
	    			return ok(qrEditEquipo.render(mapeoDiccionario,mapeoPermiso,userMnu, qrEquipo, listaTransac, listaCampos, listaCamposFiltrado));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result qrAgregaCampoEquipo(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String idEquipo = form.get("idEquipo").trim();
	       		String idCampo = form.get("idCampo").trim();
	       		try {
	    			Connection con = db.getConnection();
	    			if(QrTransacEquipo.create(con, s.baseDato, idEquipo, idCampo)) {
			    		QrTransacEquipo qrTransacEquipo = QrTransacEquipo.find(con, s.baseDato, idEquipo, idCampo);
			    		con.close();
			    		return ok("{ \"status\": true,\"orden\": "+qrTransacEquipo.orden+"}").as("application/json");
			    	}else {
			    		con.close();
			    		return ok("{ \"status\": false}").as("application/json");
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
	
	public Result qrActualizaPorCampo(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String idEquipo = form.get("idEquipo").trim();
	       		String idCampo = form.get("idCampo").trim();
		    	String campoMySql = form.get("campoMySql").trim();
		    	String valor = form.get("valor").trim();
	       		try {
	    			Connection con = db.getConnection();
	    			if(QrTransacEquipo.updatePorCampo(con, s.baseDato, idEquipo, idCampo, campoMySql, valor)) {
		    			con.close();
		    			return ok("{ \"status\": true}").as("application/json");
		    		}else {
		    			con.close();
		    			return ok("{ \"status\": false}").as("application/json");
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
	
	public Result qrEliminaCampoEquipo(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String idEquipo = form.get("idEquipo").trim();
	       		String idCampo = form.get("idCampo").trim();
	       		try {
	    			Connection con = db.getConnection();
	    			QrTransacEquipo transac = QrTransacEquipo.find(con,s.baseDato, idEquipo, idCampo);
	    	    	if(QrTransacEquipo.eliminaCampo(con, s.baseDato, transac)) {
	    	    		con.close();
	    	    		return ok("{ \"status\": true}").as("application/json");
	    	    	}else {
	    	    		con.close();
	    	    		return ok("{ \"status\": false}").as("application/json");
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
	
	public Result qrGrabaAnexoEquipo(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Http.MultipartFormData<TemporaryFile> body = request.body().asMultipartFormData();
				Http.MultipartFormData.FilePart<TemporaryFile> archivo = body.getFile("anexo");
	       		String idEquipo = form.get("idEquipo").trim();
	       		String idCampo = form.get("idCampo").trim();
	       		String extencion = form.get("extencion").trim();
	       		if (archivo != null) {
	       			String nombreArchivoSinExtencion = "QR_Equipo_"+extencion.trim().toUpperCase()+"_"+idEquipo+"_"+idCampo;
					String nombreArchivoConExtencion = Archivos.grabarArchivos(archivo, s.baseDato, nombreArchivoSinExtencion);
					return ok("{ \"status\": true,\"archivo\":\""+nombreArchivoConExtencion+"\"}").as("application/json");
	       		}else {
	       			return ok("{ \"status\": false}").as("application/json");
	       		}
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	
	
	//============================================================
    // MNU qrAdminEquipos   Qr/Atributos QR
    //============================================================
	
	public Result qrListaAtributoEquipos(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("qrAdminEquipos")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<QrAtributoEquipo> listAtributos = QrAtributoEquipo.all(con, s.baseDato);
    			con.close();
    			return ok(qrListaAtributoEquipos.render(mapeoDiccionario,mapeoPermiso,userMnu,listAtributos));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result qrEditarAtributoEquipo(Http.Request request, Long id_qrAtributoEquipo) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("qrAdminEquipos")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			QrAtributoEquipo atributoEquipo = QrAtributoEquipo.find(con, s.baseDato, id_qrAtributoEquipo);
    			Long bloquearTipo = QrAtributoEquipo.bloquearTipo(con, s.baseDato, id_qrAtributoEquipo);
    			List<QrTipoContenido> listaTipo = QrTipoContenido.all(con, s.baseDato);
    			con.close();
    			return ok(qrEditarAtributoEquipo.render(mapeoDiccionario,mapeoPermiso,userMnu, atributoEquipo, listaTipo, bloquearTipo));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result qrEditarAtributoEquipo2(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return redirect("/qrListaAtributoEquipos/");
	       	}else {
	       		String idTipo = form.get("idTipo").trim();
		    	String nombre = form.get("nombre").trim();
		    	String idQrAtributoEquipo = form.get("idQrAtributoEquipo").trim();
	       		try {
	    			Connection con = db.getConnection();
	    			QrAtributoEquipo.update(con, s.baseDato, idTipo, nombre, idQrAtributoEquipo);
	    			con.close();
	    			return redirect("/qrListaAtributoEquipos/");
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result qrDeleteAtributoEquipo(Http.Request request, Long id_qrAtributoEquipo) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		try {
    			Connection con = db.getConnection();
    			if(!QrAtributoEquipo.delete(con, s.baseDato, id_qrAtributoEquipo)){
    				String mensaje = "No puede ser eliminado, esta en uso o esta en el historico";
    				con.close();
    				return ok(mensajes.render("qrListaAtributoEquipos/",mensaje));
    			}
    			con.close();
    			return redirect("/qrListaAtributoEquipos/");
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result qrAgregaAtributoEquipo(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("qrAdminEquipos")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<QrTipoContenido> listaTipo = QrTipoContenido.all(con,s.baseDato);
    			con.close();
    			return ok(qrAgregaAtributoEquipo.render(mapeoDiccionario,mapeoPermiso,userMnu,listaTipo));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result qrAgregaAtributoEquipo2(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return redirect("/qrListaAtributoEquipos/");
	       	}else {
	       		String idTipo = form.get("idTipo").trim();
		    	String nombre = form.get("nombre").trim();
	       		try {
	    			Connection con = db.getConnection();
	    			QrAtributoEquipo.create(con, s.baseDato, idTipo, nombre);
	    			con.close();
	    			return redirect("/qrListaAtributoEquipos/");
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
    // MNU qrAdminEquipos   Qr/Print QR Equipos
    //============================================================
	
	public Result qrPrintQrEquipos(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("qrAdminEquipos")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<List<List<String>>> lista = QrEquipo.listaDeQrEquipos(con, s.baseDato);
    			con.close();
    			return ok(qrPrintQrEquipos.render(mapeoDiccionario,mapeoPermiso,userMnu,lista));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result qrPrintQrEquiposWord(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			if(mapeoPermiso.get("qrAdminEquipos")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<List<List<String>>> lista = QrEquipo.listaDeQrEquipos(con, s.baseDato);
    			File file = QrEquipo.reporteEnWord(con, s.baseDato, lista);
    			if(file!=null) {
	       			con.close();
	       			return ok(file,false,Optional.of("Listado_de_QR.docx"));
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
	
	
	
	// ****************************************************
	// LECTURA DEL QR
	// ****************************************************
	
	public Result leeUnQr(Http.Request request, String strEncoded) {
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
			try {
				Connection con = db.getConnection();
				Equipo equipo = Equipo.find(con, base, Long.parseLong(id_equipo));
				QrEquipo qrEquipo = QrEquipo.find(con, base, Long.parseLong(id_equipo));
				if(qrEquipo.activo==0) {
					con.close();
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
				con.close();
				return ok(inicioQr.render(nEmpresa.toUpperCase(), base, equipo, stock, ubicacion, (long) listTransacEquipo.size()))
						.addingToSession(request, "strEncoded", strEncoded);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			//AQUI PROGRAMAR ACCESO PERSONAS
			return ok("AQUI PROGRAMAR ACCESO PERSONAS");
		}
		return ok("ERROR");
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
		Sessiones s = new Sessiones(request, "soloQr");
    	if(s.strEncoded!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
    		if (form.hasErrors()) {
    			return ok(mensajesQr.render("leeUnQr/"+s.strEncoded,"No existe imagen a mostrar"));
    	    } else {
    	    	String nEmpresa = form.get("nEmpresa");
    	    	String base = form.get("base");
    	    	Long id_equipo = Long.parseLong(form.get("id_equipo"));
    	    	Double stock = Double.parseDouble(form.get("stock"));
    	    	String ubicacion = form.get("ubicacion");
    	    	try {
    				Connection con = db.getConnection();
    	    		Equipo equipo = Equipo.find(con, base, id_equipo);
    	    		if(equipo.img.equals("0")) {
    	    			con.close();
            			return ok(mensajesQr.render("leeUnQr/"+s.strEncoded,"No existe imagen a mostrar"));
            		}else {
            			con.close();
            			return ok(imagenQr.render(nEmpresa.toUpperCase(), base, equipo, stock, ubicacion));
            		}
    	    	} catch (SQLException e) {
    	    		e.printStackTrace();
    	    	}
    	    }
    	}else {
    		return ok("ERROR");
    	}
    	return ok("ERROR");
	}
	
	public Result qrAtributo(Http.Request request) {
		Sessiones s = new Sessiones(request, "soloQr");
    	if(s.strEncoded!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
    		if (form.hasErrors()) {
    			return ok(mensajesQr.render("leeUnQr/"+s.strEncoded,"No existen atributos a mostrar"));
    	    } else {
    	    	String nEmpresa = form.get("nEmpresa");
    	    	String base = form.get("base");
    	    	Long id_equipo = Long.parseLong(form.get("id_equipo"));
    	    	Double stock = Double.parseDouble(form.get("stock"));
    	    	String ubicacion = form.get("ubicacion");
    	    	try {
    				Connection con = db.getConnection();
    				Equipo equipo = Equipo.find(con, base, id_equipo);
    	    		List<List<String>> lista = MnuQr.atributosPorEquipo(con, base, equipo.id_grupo, id_equipo);
    	    		List<List<String>> listaFiltrada = new ArrayList<List<String>>();
    	    		for(int i=0; i<lista.size(); i++) {
    	    			if(lista.get(i).get(4).equals("1")) {
    	    				listaFiltrada.add(lista.get(i));
    	    			}
    	    		}
    	    		con.close();
    	    		return ok(atributoQr.render(nEmpresa.toUpperCase(), base, equipo, stock, ubicacion, listaFiltrada));
    	    	} catch (SQLException e) {
    	    		e.printStackTrace();
    	    	}
    	    }
    	}else {
    		return ok("ERROR");
    	}
    	return ok("ERROR");
	}
	
	public Result qrUbicacion(Http.Request request) {
		Sessiones s = new Sessiones(request, "soloQr");
    	if(s.strEncoded!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
    		if (form.hasErrors()) {
				return ok(mensajesQr.render("leeUnQr/"+s.strEncoded,"No existe ubicación a mostrar"));
		    } else {
		    	String nEmpresa = form.get("nEmpresa");
		    	String base = form.get("base");
		    	Long id_equipo = Long.parseLong(form.get("id_equipo"));
		    	Double stock = Double.parseDouble(form.get("stock"));
		    	String ubicacion = form.get("ubicacion");
		    	try {
		    		Connection con = db.getConnection();
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
					
					
    	    		con.close();
    	    		return ok(ubicacionQr.render(nEmpresa.toUpperCase(), base, equipo, stock, ubicacion, lista));
    	    	} catch (SQLException e) {
    	    		e.printStackTrace();
    	    	}
    		}
    	}else {
    		return ok("ERROR");
    	}
    	return ok("ERROR");
	}

	public Result qrTrazabilidad(Http.Request request) {
		Sessiones s = new Sessiones(request, "soloQr");
    	if(s.strEncoded!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
    		if (form.hasErrors()) {
    			return ok(mensajesQr.render("leeUnQr/"+s.strEncoded,"No existe ubicación a mostrar"));
		    } else {
		    	String nEmpresa = form.get("nEmpresa");
		    	String base = form.get("base");
		    	Long id_equipo = Long.parseLong(form.get("id_equipo"));
		    	Double stock = Double.parseDouble(form.get("stock"));
		    	String ubicacion = form.get("ubicacion");
		    	try {
		    		Connection con = db.getConnection();
		    		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(base);
		    		Equipo equipo = Equipo.find(con, base, id_equipo);
		    		List<List<String>> lista = ReportTrazabilidades.trazaEquipo(con, base, equipo.id, mapeoDiccionario, s.aplicaPorSucursal, s.id_sucursal);
    	    		con.close();
    	    		return ok(trazabilidadQr.render(nEmpresa.toUpperCase(), base, equipo, stock, ubicacion, lista));
    	    	} catch (SQLException e) {
    	    		e.printStackTrace();
    	    	}
    		}
    	}else {
    		return ok("ERROR");
    	}
    	return ok("ERROR");
	}

	public Result qrHistoria(Http.Request request) {
		Sessiones s = new Sessiones(request, "soloQr");
    	if(s.strEncoded!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
    		if (form.hasErrors()) {
    			return ok(mensajesQr.render("leeUnQr/"+s.strEncoded,"No existe ubicación a mostrar"));
		    } else {
		    	String nEmpresa = form.get("nEmpresa");
		    	String base = form.get("base");
		    	Long id_equipo = Long.parseLong(form.get("id_equipo"));
		    	Double stock = Double.parseDouble(form.get("stock"));
		    	String ubicacion = form.get("ubicacion");
		    	try {
		    		Connection con = db.getConnection();
		    		Equipo equipo = Equipo.find(con, base, id_equipo);
		    		List<HojaVida> lista = HojaVida.allPorEquipo(con, base, equipo.getId());
    	    		con.close();
    	    		return ok(historiaQr.render(nEmpresa.toUpperCase(), base, equipo, stock, ubicacion, lista));
    	    	} catch (SQLException e) {
    	    		e.printStackTrace();
    	    	}
    		}
    	}else {
    		return ok("ERROR");
    	}
    	return ok("ERROR");
	}
	
	
	public Result qrAtributosQr(Http.Request request) {
		Sessiones s = new Sessiones(request, "soloQr");
    	if(s.strEncoded!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
    		if (form.hasErrors()) {
    			return ok(mensajesQr.render("leeUnQr/"+s.strEncoded,"No existe ubicación a mostrar"));
		    } else {
		    	String nEmpresa = form.get("nEmpresa");
		    	String base = form.get("base");
		    	Long id_equipo = Long.parseLong(form.get("id_equipo"));
		    	Double stock = Double.parseDouble(form.get("stock"));
		    	String ubicacion = form.get("ubicacion");
		    	try {
		    		Connection con = db.getConnection();
		    		Equipo equipo = Equipo.find(con, base, id_equipo);
		    		List<QrTransacEquipo> lista = QrTransacEquipo.allPorIdEquipoSoloActivos(con, base, id_equipo);
    	    		con.close();
    	    		return ok(atributosQrEquipo.render(nEmpresa.toUpperCase(), base, equipo, stock, ubicacion, lista));
    	    	} catch (SQLException e) {
    	    		e.printStackTrace();
    	    	}
    		}
    	}else {
    		return ok("ERROR");
    	}
    	return ok("ERROR");
	}
	
	public Result qrImagenQr(Http.Request request){
		Sessiones s = new Sessiones(request, "soloQr");
    	if(s.strEncoded!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
    		if (form.hasErrors()) {
    			return ok(mensajesQr.render("leeUnQr/"+s.strEncoded,"No existe imagen a mostrar"));
		    } else {
		    	String nEmpresa = form.get("nEmpresa");
		    	String base = form.get("base");
		    	Long id_equipo = Long.parseLong(form.get("id_equipo"));
		    	Double stock = Double.parseDouble(form.get("stock"));
		    	String ubicacion = form.get("ubicacion");
		    	String archivo = form.get("archivo");
		    	String tipo = form.get("tipo");
		    	
		    	try {
		    		Connection con = db.getConnection();
		    		Equipo equipo = Equipo.find(con, base, id_equipo);
		    		if(tipo.equals("Archivo PDF")) {
	    				InputStream auxfile = Archivos.leerArchivo(base+"/"+archivo);
	    	       		File file = Archivos.parseInputStreamToFile(auxfile);
	    				con.close();
	    				return ok(file,false,Optional.of(archivo)); 
	    			}else {
	    				con.close();
	    				return ok(imagenQrQr.render(nEmpresa.toUpperCase(), base, equipo, stock, ubicacion, archivo));
	    			}
    	    	} catch (SQLException e) {
    	    		e.printStackTrace();
    	    	}
    		}
    	}else {
    		return ok("ERROR");
    	}
    	return ok("ERROR");
	}
	
	
	
	
	
	
	
}
