package controllers;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import controllers.HomeController.Sessiones;
import models.forms.FormMantencion;
import models.tables.*;
import models.utilities.*;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.Database;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.mensajes;
import viewsMnuMantencion.html.*;


/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class MnuMantencion extends Controller {


	public static Database dbWrite = HomeController.dbWrite;
	public static DatabaseRead dbRead = HomeController.dbRead;
	public static FormFactory formFactory = HomeController.formFactory;
	public static String msgError = HomeController.msgError;
	public static String msgErrorFormulario = HomeController.msgErrorFormulario;
	public static String msgSinPermiso = HomeController.msgSinPermiso;


	//============================================================
	// MENU INGRESO APP WEB
	//============================================================

	public Result mantWebInicio0() throws InterruptedException {
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
			return ok(mantWebInicio0.render(lista,"0")).withNewSession()
					.discardingCookie("username")
					.discardingCookie("project")
					.discardingCookie("company")
					.discardingCookie("password");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ok(mensajes.render("/report",msgError));
	}

	public Result mantWebInicio1(Http.Request request) {
		DynamicForm form = formFactory.form().bindFromRequest(request);
		if (form.hasErrors()) {
			return ok(mensajes.render("/report",msgErrorFormulario));
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
					return ok(mensajes.render("/report",mensaje));
				}
			}

			try {
				Connection con = dbWrite.getConnection();
				String db = Inicio.findBaseDatoVigente(con,empresa,pais);

				if( db.equals("sinBd")){
					String msg = "La empresa no esta disponible o no existe, pongase en contacto con soporte pbarros@inqsol.com";
					con.close();
					return ok(mensajes.render("/report",msg));
				}
				if( ! Parametros.validaParametro(con, db, "mnuMANTENCION")){
					String msg = "La empresa no esta disponible o no posee este modulo activo, pongase en contacto con soporte pbarros@inqsol.com";
					con.close();
					return ok(mensajes.render("/report",msg));
				}
				MantOperadorMecanico user = MantOperadorMecanico.findXUserNameUser(con,db,userName);
				if(user==null){
					String msg = "Los datos ingresados no corresponden, vuelva a ingresarlos. En caso de continuar " +
							"este problema, por favor contactar con soporte pbarros@inqsol.com";
					con.close();
					return ok(mensajes.render("/report",msg));
				}

				if( ! (user.getUserKey().equals(userKey))){
					String msg = "La clave ingresada no corresponde, vuelva a ingresar. En caso de continuar " +
							"este problema, por favor contactar con soporte pbarros@inqsol.com";
					con.close();
					return ok(mensajes.render("/report",msg));
				}else {
					Registro.accesos(con, db, "MANT: "+userName);
					Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(db);
					String fechaAAMMDD = Fechas.hoy().getFechaStrAAMMDD();
					MantActorPersonal actor = MantActorPersonal.find(con, db, user.getId_mantActorPersonal());
					List<PlanMantencion> listPlanMant = PlanMantencion.allEquiposVigentes(con,db);
					Map<String,String> mapIdEquipVsBododega = Equipo.mapConExistenciaUnaUnidad(con, db, mapeoDiccionario);
					String mapIdEquipVsBod = Json.toJson(mapIdEquipVsBododega).toString();
					List<BodegaEmpresa> listBod = BodegaEmpresa.allVigentes(con, db);
					List<MantEstadoEnObra> listEstaObra = MantEstadoEnObra.all(con, db);
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
								aux.add(x.getId_unidadMantencion().toString());
							}else {
								aux.add("0.00");
								aux.add("0");
							}
							mapEquipos.put(x.getId_equipo(), aux);
						}else {
							String u = x.getUnidadMantencion();
							if(u.equals("HR") || u.equals("KG") && ! aux.get(4).equals("HR")) {
								aux.set(4,u);
								aux.set(5,x.getEstadoActual());
								aux.set(6,x.getId_unidadMantencion().toString());
							}else {
								aux.set(5,"0.00");
								aux.set(6,"0");
							}
							mapEquipos.put(x.getId_equipo(), aux);
						}
					}
					List<List<String>> listEquipos = new ArrayList<List<String>>();
					mapEquipos.forEach((k,v) -> {
						listEquipos.add(v);
					});
					List<TipoMantencion> listTipoMantencion = TipoMantencion.all(con, db);
					List<MantEstadoEnTaller> listEstadoEnTaller = MantEstadoEnTaller.all(con, db);
					List<MantActividad> listActividad = MantActividad.all(con, db);
					List<MantTipoActividad> listTipoActividad = MantTipoActividad.all(con, db);
					List<MantComponente> listComponentes = MantComponente.all(con, db);
					List<MantItemIntervenido> listItemIntervenido = MantItemIntervenido.all(con, db);
					con.close();
					return ok(mantWebReportNew.render(mapeoDiccionario, fechaAAMMDD,actor,user,
							listPlanMant,mapIdEquipVsBod,listBod,listEstaObra, listEquipos, listTipoMantencion,
							listEstadoEnTaller,listActividad,listTipoActividad,listComponentes,listItemIntervenido))
							.addingToSession(request, "userName", user.getUserName())
							.addingToSession(request, "id_usuario", user.getId().toString())
							.addingToSession(request, "baseDato", db)
							.addingToSession(request, "id_tipoUsuario", "0")
							.addingToSession(request, "porProyecto", "0")
							.addingToSession(request, "id_sucursal", "0")
							.addingToSession(request, "aplicaPorSucursal", "0");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ok(mensajes.render("/report",msgErrorFormulario));
	}

	public Result mantWebInicio1get(Http.Request request, Long id_operMec) {
		Sessiones s = new Sessiones(request);
		if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			if( ! (s.id_tipoUsuario.equals("0") || mapeoPermiso.get("id_sucursal").equals("0"))) {
				return ok(mensajes.render("/report",msgSinPermiso));
			}
				try {
					Connection con = dbRead.getConnection();
					MantOperadorMecanico user = MantOperadorMecanico.findXIdUser(con, s.baseDato, id_operMec);
					if(user == null){
						return ok(mensajes.render("/report",msgError));
					}
					String fechaAAMMDD = Fechas.hoy().getFechaStrAAMMDD();
					MantActorPersonal actor = MantActorPersonal.find(con, s.baseDato, user.getId_mantActorPersonal());
					List<PlanMantencion> listPlanMant = PlanMantencion.allEquiposVigentes(con,s.baseDato);
					Map<String,String> mapIdEquipVsBododega = Equipo.mapConExistenciaUnaUnidad(con, s.baseDato, mapeoDiccionario);
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
								aux.add(x.getId_unidadMantencion().toString());
							}else {
								aux.add("0.00");
								aux.add("0");
							}
							mapEquipos.put(x.getId_equipo(), aux);
						}else {
							String u = x.getUnidadMantencion();
							if(u.equals("HR") || u.equals("KG") && ! aux.get(4).equals("HR")) {
								aux.set(4,u);
								aux.set(5,x.getEstadoActual());
								aux.set(6,x.getId_unidadMantencion().toString());
							}else {
								aux.set(5,"0.00");
								aux.set(6,"0");
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
					List<MantComponente> listComponentes = MantComponente.all(con, s.baseDato);
					List<MantItemIntervenido> listItemIntervenido = MantItemIntervenido.all(con, s.baseDato);
					con.close();
					return ok(mantWebReportNew.render(mapeoDiccionario, fechaAAMMDD,actor,user,
							listPlanMant,mapIdEquipVsBod,listBod,listEstaObra, listEquipos, listTipoMantencion,
							listEstadoEnTaller,listActividad,listTipoActividad,listComponentes,listItemIntervenido));

				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return ok(mensajes.render("/report",msgError));
	}

	public Result mantWebReportNewSave(Http.Request request) {
		Sessiones s = new Sessiones(request);
		if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
			FormMantencion form = formFactory.form(FormMantencion.class).withDirectFieldAccess(true).bindFromRequest(request).get();
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			if( ! (s.id_tipoUsuario.equals("0") || mapeoPermiso.get("id_sucursal").equals("0"))) {
				return ok(mensajes.render("/report",msgSinPermiso));
			}
			if (form.id_mantActorPersonal == null || form.id_mecanico == null || form.id_operador == null) {
				return ok(mensajes.render("/report",msgErrorFormulario));
			}else {
				Long id_mantActorPersonal = form.id_mantActorPersonal; //1 operador 2 mecanico
				try {
					Connection con = dbWrite.getConnection();
					MantOperadorMecanico user = null;
					if(id_mantActorPersonal == (long)1) {
						Long id_operador = form.id_operador;
						user = MantOperadorMecanico.findXIdUser(con, s.baseDato, id_operador);
						Long id_mantTransacReport = MantTransacReport.newReportOperador(con, s.baseDato, form);
						if(id_mantTransacReport > 0) {
							Archivos archivos = formFactory.form(Archivos.class).withDirectFieldAccess(true).bindFromRequest(request).get();
							if (archivos != null && archivos.docAdjunto != null) {
								String nombreArchivoSinExtencion = MantTransacReport.nombreDocAnexo(con, s.baseDato, archivos, id_mantTransacReport);
								MnuMantencion.grabarFilesThread grabarFile = new MnuMantencion.grabarFilesThread(s.baseDato, archivos, nombreArchivoSinExtencion);
								grabarFile.run();
							}
							MantTransacReport mantTransacReport = MantTransacReport.find(con, s.baseDato, id_mantTransacReport);
							FormMantencion.pdfReportMantOperador(con, s.baseDato, mantTransacReport);
							Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "mantTransacReport", id_mantTransacReport, "create", "Ingresa nuevo report Mantencion usuario MADA: "+s.userName);
						}
					}

					if(id_mantActorPersonal == (long)2) {
						Long id_mecanico = form.id_mecanico;
						user = MantOperadorMecanico.findXIdUser(con, s.baseDato, id_mecanico);
						Long id_mantTransacReport = MantTransacReport.newReportMecanico(con, s.baseDato, form, mapeoPermiso);
						if(id_mantTransacReport > 0) {
							Archivos archivos = formFactory.form(Archivos.class).withDirectFieldAccess(true).bindFromRequest(request).get();
							if (archivos != null && archivos.docAdjunto != null) {
								String nombreArchivoSinExtencion = MantTransacReport.nombreDocAnexo(con, s.baseDato, archivos, id_mantTransacReport);
								MnuMantencion.grabarFilesThread grabarFile = new MnuMantencion.grabarFilesThread(s.baseDato, archivos, nombreArchivoSinExtencion);
								grabarFile.run();
							}
							MantTransacReport mantTransacReport = MantTransacReport.find(con, s.baseDato, id_mantTransacReport);
							FormMantencion.pdfReportMantMecanico(con, s.baseDato, mantTransacReport);
							Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "mantTransacReport", id_mantTransacReport, "create", "Ingresa nuevo report Mantencion usuario MADA: "+s.userName);
						}
					}
					con.close();
					if(user != null) {
						String msg = "Report grabado con exito";
						return ok(mensajes.render("/routes3/mantWebInicio1get/"+user.id,msg));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return ok(mensajes.render("/report",msgError));
			}
		}
		return ok(mensajes.render("/report",msgError));
	}

	public Result mantWebListarReports0(Http.Request request) {
		Sessiones s = new Sessiones(request);
		if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
			if( ! (s.id_tipoUsuario.equals("0") || mapeoPermiso.get("id_sucursal").equals("0"))) {
				return ok(mensajes.render("/report",msgSinPermiso));
			}
			DynamicForm form = formFactory.form().bindFromRequest(request);
			if (form.hasErrors()) {
				return ok(mensajes.render("/report",msgErrorFormulario));
			}else {
				Long id_userOperMec = Long.parseLong(form.get("id_userOperMec").trim());
				Fechas hoy = Fechas.hoy();
				String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
				String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
				return ok(mantWebListarReports0.render(mapeoDiccionario,mapeoPermiso, desde, hasta, id_userOperMec));
			}

		}
		return ok(mensajes.render("/report",msgError));
	}

	public Result mantWebListarReports1(Http.Request request) {
		Sessiones s = new Sessiones(request);
		if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
			DynamicForm form = formFactory.form().bindFromRequest(request);
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			if( ! (s.id_tipoUsuario.equals("0") || mapeoPermiso.get("id_sucursal").equals("0"))) {
				return ok(mensajes.render("/report",msgSinPermiso));
			}
			if (form.hasErrors()) {
				return ok(mensajes.render("/report",msgErrorFormulario));
			}else {
				String desdeAAMMDD = form.get("fechaDesde").trim();
				String hastaAAMMDD = form.get("fechaHasta").trim();
				Long id_userOperMec = Long.parseLong(form.get("id_userOperMec").trim());
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				try {
					Connection con = dbWrite.getConnection();
					List<MantTransacReport> listReport = MantTransacReport.allEntreFechas(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
					List<List<String>> listado = MantTransacReport.listaDeReportsPorIdOperMec(listReport,id_userOperMec);
					PreparedStatement smt = con.prepareStatement("select max(id) from `"+s.baseDato+"`.mantTransacReport where (id_mantMecanico>0 and id_tipoPlan = 0) or id_mantOperador>0 group by id_equipo" +
							" union " +
							" select max(id) from `"+s.baseDato+"`.mantTransacReport where id_mantMecanico>0 and id_tipoPlan > 0  group by id_equipo;");
					ResultSet rs = smt.executeQuery();
					Map<String,String> mapDelete = new HashMap<String,String>();
					while(rs.next()) {
						mapDelete.put(rs.getString(1), rs.getString(1));
					}
					rs.close();
					smt.close();
					con.close();
					return ok(mantWebListarReports1.render(mapeoDiccionario,mapeoPermiso,listado,desdeAAMMDD,hastaAAMMDD, mapDelete, id_userOperMec));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ok(mensajes.render("/report",msgError));
	}

	public Result mantWebListarReports1Get(Http.Request request, String desdeAAMMDD, String hastaAAMMDD) {
		Sessiones s = new Sessiones(request);
		if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
			try {
				Connection con = dbWrite.getConnection();
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				if( ! (s.id_tipoUsuario.equals("0") || mapeoPermiso.get("id_sucursal").equals("0"))) {
					return ok(mensajes.render("/report",msgSinPermiso));
				}
				Long id_userOperMec = Long.parseLong(s.id_usuario);
				List<MantTransacReport> listReport = MantTransacReport.allEntreFechas(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
				List<List<String>> listado = MantTransacReport.listaDeReportsPorIdOperMec(listReport,id_userOperMec);
				PreparedStatement smt = con.prepareStatement("select max(id) from `"+s.baseDato+"`.mantTransacReport where (id_mantMecanico>0 and id_tipoPlan = 0) or id_mantOperador>0 group by id_equipo" +
						" union " +
						" select max(id) from `"+s.baseDato+"`.mantTransacReport where id_mantMecanico>0 and id_tipoPlan > 0  group by id_equipo;");
				ResultSet rs = smt.executeQuery();
				Map<String,String> mapDelete = new HashMap<String,String>();
				while(rs.next()) {
					mapDelete.put(rs.getString(1), rs.getString(1));
				}
				rs.close();
				smt.close();
				con.close();
				return ok(mantWebListarReports1.render(mapeoDiccionario,mapeoPermiso,listado,desdeAAMMDD,hastaAAMMDD, mapDelete, id_userOperMec));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ok(mensajes.render("/",msgError));
	}

	public Result mantWebListarReports1Excel(Http.Request request) {
		Sessiones s = new Sessiones(request);
		if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
			DynamicForm form = formFactory.form().bindFromRequest(request);
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			if( ! (s.id_tipoUsuario.equals("0") || mapeoPermiso.get("id_sucursal").equals("0"))) {
				return ok(mensajes.render("/report",msgSinPermiso));
			}
			if (form.hasErrors()) {
				return ok(mensajes.render("/report",msgErrorFormulario));
			}else {
				String desdeAAMMDD = form.get("fechaDesde").trim();
				String hastaAAMMDD = form.get("fechaHasta").trim();
				Long id_userOperMec = Long.parseLong(form.get("id_userOperMec").trim());
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				try {
					Connection con = dbWrite.getConnection();
					List<MantTransacReport> listReport = MantTransacReport.allEntreFechas(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
					List<List<String>> listado = MantTransacReport.listaDeReportsPorIdOperMec(listReport,id_userOperMec);
					listado.sort(Comparator.comparing((List<String> list) -> list.get(1), Comparator.reverseOrder())
							.thenComparing(list -> list.get(0), Comparator.reverseOrder()));
					File file = MantTransacReport.exportaListaDeReportsExcel(s.baseDato, mapeoDiccionario, listado, desdeAAMMDD, hastaAAMMDD);
					if(file!=null) {
						con.close();
						return ok(file,false,Optional.of("Lista_de_report_diario.xlsx"));
					}else {
						con.close();
						return ok("");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ok(mensajes.render("/report",msgError));
	}

	public Result mantWebReportDetalle(Http.Request request) {
		Sessiones s = new Sessiones(request);
		if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
			DynamicForm form = formFactory.form().bindFromRequest(request);
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			if( ! (s.id_tipoUsuario.equals("0") || mapeoPermiso.get("id_sucursal").equals("0"))) {
				return ok(mensajes.render("/report",msgSinPermiso));
			}
			if (form.hasErrors()) {
				return ok(mensajes.render("/report",msgErrorFormulario));
			}else {
				Long id_mantTransacReport = Long.parseLong(form.get("id_mantTransacReport"));
				try {
					Connection con = dbWrite.getConnection();
					Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
					MantTransacReport mantTransacReport = MantTransacReport.find(con, s.baseDato, id_mantTransacReport);
					if(mantTransacReport.getId_mantActorPersonal() == (long)1) {
						con.close();
						return ok(mantWebReportOperador.render(mapeoDiccionario,mapeoPermiso,mantTransacReport));
					}else {
						List<MantTransacComponentes> mantTransacComponentes = MantTransacComponentes.allPorIdMantTransacReport(con, s.baseDato, id_mantTransacReport);
						con.close();
						return ok(mantWebReportMecanico.render(mapeoDiccionario,mapeoPermiso,mantTransacReport,mantTransacComponentes));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ok(mensajes.render("/report",msgError));
	}

	public Result mantWebReportGrabaDocAnexo(Http.Request request) {
		Sessiones s = new Sessiones(request);
		if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
			Archivos archivos = formFactory.form(Archivos.class).withDirectFieldAccess(true).bindFromRequest(request).get();
			if (archivos != null) {
				if(archivos.docAdjunto != null) {
					DynamicForm form = formFactory.form().bindFromRequest(request);
					Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
					System.out.println("XX "+s.id_tipoUsuario);
					if( ! (s.id_tipoUsuario.equals("0") || mapeoPermiso.get("id_sucursal").equals("0"))) {
						return ok(mensajes.render("/report",msgSinPermiso));
					}
					if (form.hasErrors()) {
						return ok(mensajes.render("/report",msgErrorFormulario));
					}else {
						try {
							Connection con = dbWrite.getConnection();
							Long id_mantTransacReport = Long.parseLong(form.get("id_mantTransacReport").trim());
							String desdeAAMMDD = form.get("desdeAAMMDD").trim();
							String hastaAAMMDD = form.get("hastaAAMMDD").trim();
							String nombreArchivoSinExtencion = MantTransacReport.nombreDocAnexo(con, s.baseDato, archivos, id_mantTransacReport);
							MnuMantencion.grabarFilesThread grabarFile = new MnuMantencion.grabarFilesThread(s.baseDato, archivos, nombreArchivoSinExtencion);
							grabarFile.run();
							String msg = "Archivos subidos con exito, esto puede tomar algunos minutos en actualizar la lista";
							con.close();
							return ok(mensajes.render("/routes3/mantWebListarReports1Get/"+desdeAAMMDD+","+hastaAAMMDD,msg));

						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			}
			return redirect("odoListarVentas1");
		}
		return ok(mensajes.render("/report",msgError));
	}

	public Result mantWebFirmaOperador(Http.Request request, Long id_mantTransacReport, String desdeAAMMDD, String hastaAAMMDD) {
		Sessiones s = new Sessiones(request);
		if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			if( ! (s.id_tipoUsuario.equals("0") || mapeoPermiso.get("id_sucursal").equals("0"))) {
				return ok(mensajes.render("/report",msgSinPermiso));
			}
			try {
				Connection con = dbWrite.getConnection();
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				MantTransacReport mantTransacReport = MantTransacReport.find(con, s.baseDato, id_mantTransacReport);
				if(mantTransacReport.getId_mantActorPersonal() == (long)1) {
					con.close();
					return ok(mantWebReportOperadorFirmaOper.render(mapeoDiccionario,mapeoPermiso,mantTransacReport, desdeAAMMDD, hastaAAMMDD));
				}else {
					List<MantTransacComponentes> mantTransacComponentes = MantTransacComponentes.allPorIdMantTransacReport(con, s.baseDato, id_mantTransacReport);
					con.close();
					return ok(mantWebReportMecanicoFirmaMec.render(mapeoDiccionario,mapeoPermiso,mantTransacReport,mantTransacComponentes,desdeAAMMDD,hastaAAMMDD));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ok(mensajes.render("/report",msgError));
	}


	public Result mantWebGrabarFirmaOperador(Http.Request request) {
		Sessiones s = new Sessiones(request);
		if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
			DynamicForm form = formFactory.form().bindFromRequest(request);
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			if( ! (s.id_tipoUsuario.equals("0") || mapeoPermiso.get("id_sucursal").equals("0"))) {
				return ok(mensajes.render("/report",msgSinPermiso));
			}
			if (form.hasErrors()) {
				return ok(mensajes.render("/report",msgErrorFormulario));
			}else {
				Long id_mantTransacReport = Long.parseLong(form.get("id_mantTransacReport").trim());
				String firmaPDFoperador = form.get("firmaPDFoperador").trim();
				String desdeAAMMDD = form.get("fechaDesde").trim();
				String hastaAAMMDD = form.get("fechaHasta").trim();
				try {
					Connection con = dbWrite.getConnection();
					if(MantTransacReport.modificaPorCampo(con, s.baseDato, "firmaPDFoperador", id_mantTransacReport, firmaPDFoperador)) {
						Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "mantTransacReport", id_mantTransacReport, "insert", "agrega o modifica firma operador/mecanico en reporte nro: "+id_mantTransacReport);
						MantTransacReport mantTransacReport = MantTransacReport.find(con, s.baseDato, id_mantTransacReport);
						if(mantTransacReport.getId_mantActorPersonal() == (long)1) {
							FormMantencion.pdfReportMantOperador(con, s.baseDato, mantTransacReport);
						}else {
							FormMantencion.pdfReportMantMecanico(con, s.baseDato, mantTransacReport);
						}
					}
					con.close();
					return redirect("/routes3/mantWebListarReports1Get/"+desdeAAMMDD+","+hastaAAMMDD);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ok(mensajes.render("/report",msgError));
	}

	public Result mantWebFirmaAutorizador(Http.Request request, Long id_mantTransacReport, String desdeAAMMDD, String hastaAAMMDD) {
		Sessiones s = new Sessiones(request);
		if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			if( ! (s.id_tipoUsuario.equals("0") || mapeoPermiso.get("id_sucursal").equals("0"))) {
				return ok(mensajes.render("/report",msgSinPermiso));
			}
			try {
				Connection con = dbWrite.getConnection();
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				MantTransacReport mantTransacReport = MantTransacReport.find(con, s.baseDato, id_mantTransacReport);
				if(mantTransacReport.getId_mantActorPersonal() == (long)1) {
					con.close();
					return ok(mantWebReportOperadorFirmaSuper.render(mapeoDiccionario,mapeoPermiso,mantTransacReport, desdeAAMMDD, hastaAAMMDD));
				}else {
					List<MantTransacComponentes> mantTransacComponentes = MantTransacComponentes.allPorIdMantTransacReport(con, s.baseDato, id_mantTransacReport);
					con.close();
					return ok(mantWebReportMecanicoFirmaSuper.render(mapeoDiccionario,mapeoPermiso,mantTransacReport,mantTransacComponentes,desdeAAMMDD,hastaAAMMDD));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ok(mensajes.render("/report",msgError));
	}

	public Result mantWebGrabarFirmaAutorizador(Http.Request request) {
		Sessiones s = new Sessiones(request);
		if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
			DynamicForm form = formFactory.form().bindFromRequest(request);
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			if( ! (s.id_tipoUsuario.equals("0") || mapeoPermiso.get("id_sucursal").equals("0"))) {
				return ok(mensajes.render("/report",msgSinPermiso));
			}
			if (form.hasErrors()) {
				return ok(mensajes.render("/report",msgErrorFormulario));
			}else {
				Long id_mantTransacReport = Long.parseLong(form.get("id_mantTransacReport").trim());
				String firmaPDFautorizador = form.get("firmaPDFautorizador").trim();
				String desdeAAMMDD = form.get("fechaDesde").trim();
				String hastaAAMMDD = form.get("fechaHasta").trim();
				try {
					Connection con = dbWrite.getConnection();
					if(MantTransacReport.modificaPorCampo(con, s.baseDato, "firmaPDFautorizador", id_mantTransacReport, firmaPDFautorizador)) {
						Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "mantTransacReport", id_mantTransacReport, "insert", "agrega o modifica firma supervisor en reporte nro: "+id_mantTransacReport);
						MantTransacReport mantTransacReport = MantTransacReport.find(con, s.baseDato, id_mantTransacReport);
						if(mantTransacReport.getId_mantActorPersonal() == (long)1) {
							FormMantencion.pdfReportMantOperador(con, s.baseDato, mantTransacReport);
						}else {
							FormMantencion.pdfReportMantMecanico(con, s.baseDato, mantTransacReport);
						}
					}
					con.close();
					return redirect("/routes3/mantWebListarReports1Get/"+desdeAAMMDD+","+hastaAAMMDD);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ok(mensajes.render("/report",msgError));
	}



	//============================================================
	//============================================================
	
	public class grabarFilesThread extends Thread {
		String db;
		Archivos archivos;
		String nombreArchivoSinExtencion;
		
		public grabarFilesThread(String db, Archivos archivos, String nombreArchivoSinExtencion) {
			super();
			this.db = db;
			this.archivos = archivos;
			this.nombreArchivoSinExtencion = nombreArchivoSinExtencion;
		}
		
		public void run() {
			if(archivos.docAdjunto != null) {
				if(archivos.docAdjunto.size() == 1) {
					Archivos.grabarArchivos(archivos.docAdjunto.get(0), db, nombreArchivoSinExtencion);
				}else {
					try {
						Archivos.comprimirYgrabar(archivos.docAdjunto, db, nombreArchivoSinExtencion);
					} catch (Exception e) {}
				}
			}
		}
		
	}
	
	//============================================================
    // MENU INGRESO REPORT ADAM
    //============================================================
    
    public Result mantReportNew(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = dbRead.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("mantReportEnMada")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			String fechaAAMMDD = Fechas.hoy().getFechaStrAAMMDD();
    			List<MantActorPersonal> listActor = MantActorPersonal.all(con, s.baseDato);
    			List<MantOperadorMecanico> listOperMec = MantOperadorMecanico.allVigentes(con, s.baseDato);
    			List<PlanMantencion> listPlanMant = PlanMantencion.allEquiposVigentes(con, s.baseDato);
    			Map<String,String> mapIdEquipVsBododega = Equipo.mapConExistenciaUnaUnidad(con, s.baseDato, mapeoDiccionario);
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
							aux.add(x.getId_unidadMantencion().toString());
    					}else {
    						aux.add("0.00");
							aux.add("0");
    					}

    					mapEquipos.put(x.getId_equipo(), aux);
    				}else {
    					String u = x.getUnidadMantencion();
    					if(u.equals("HR") || u.equals("KG") && ! aux.get(4).equals("HR")) {
    						aux.set(4,u);
    						aux.set(5,x.getEstadoActual());
							aux.set(6,x.getId_unidadMantencion().toString());
    					}else {
							aux.set(5,"0.00");
							aux.set(6,"0");
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
    			List<MantComponente> listComponentes = MantComponente.all(con, s.baseDato);
    			List<MantItemIntervenido> listItemIntervenido = MantItemIntervenido.all(con, s.baseDato);

    			con.close();
    			return ok(mantReportNew.render(mapeoDiccionario,mapeoPermiso,userMnu,fechaAAMMDD,listActor,listOperMec,
    					listPlanMant,mapIdEquipVsBod,listBod,listEstaObra, listEquipos, listTipoMantencion,
    					listEstadoEnTaller,listActividad,listTipoActividad,listComponentes,listItemIntervenido));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    	return ok(mensajes.render("/",msgError));
    }
    
   
    public Result mantReportNewSave(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		
    		FormMantencion form = formFactory.form(FormMantencion.class).withDirectFieldAccess(true).bindFromRequest(request).get();
    		if (form.id_mantActorPersonal == null || form.id_mecanico == null || form.id_operador == null) {
    			return ok(mensajes.render("/",msgErrorFormulario));
    		}else {
    			Long id_mantActorPersonal = form.id_mantActorPersonal; //1 operador 2 mecanico
				try {
	    			Connection con = dbWrite.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			
	    			if(id_mantActorPersonal == (long)1) {
		    			Long id_mantTransacReport = MantTransacReport.newReportOperador(con, s.baseDato, form);
		    			if(id_mantTransacReport > 0) {
	    	    			Archivos archivos = formFactory.form(Archivos.class).withDirectFieldAccess(true).bindFromRequest(request).get();
	    	    			if (archivos != null && archivos.docAdjunto != null) {
	    	    				String nombreArchivoSinExtencion = MantTransacReport.nombreDocAnexo(con, s.baseDato, archivos, id_mantTransacReport);
		    					MnuMantencion.grabarFilesThread grabarFile = new MnuMantencion.grabarFilesThread(s.baseDato, archivos, nombreArchivoSinExtencion);
				    			grabarFile.run();
	    	    			}
	    	    			MantTransacReport mantTransacReport = MantTransacReport.find(con, s.baseDato, id_mantTransacReport);
	    	    			FormMantencion.pdfReportMantOperador(con, s.baseDato, mantTransacReport);
	    	    			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "mantTransacReport", id_mantTransacReport, "create", "Ingresa nuevo report Mantencion usuario MADA: "+s.userName);
	    	    			con.close();
	    	    			return redirect("/routes3/mantReportNew/");
		    			}
	    			}
	    			
	    			if(id_mantActorPersonal == (long)2) {
		    			Long id_mantTransacReport = MantTransacReport.newReportMecanico(con, s.baseDato, form, mapeoPermiso);
		    			if(id_mantTransacReport > 0) {
	    	    			Archivos archivos = formFactory.form(Archivos.class).withDirectFieldAccess(true).bindFromRequest(request).get();
	    	    			if (archivos != null && archivos.docAdjunto != null) {
	    	    				String nombreArchivoSinExtencion = MantTransacReport.nombreDocAnexo(con, s.baseDato, archivos, id_mantTransacReport);
		    					MnuMantencion.grabarFilesThread grabarFile = new MnuMantencion.grabarFilesThread(s.baseDato, archivos, nombreArchivoSinExtencion);
				    			grabarFile.run();
	    	    			}
	    	    			MantTransacReport mantTransacReport = MantTransacReport.find(con, s.baseDato, id_mantTransacReport);
	    	    			FormMantencion.pdfReportMantMecanico(con, s.baseDato, mantTransacReport);
	    	    			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "mantTransacReport", id_mantTransacReport, "create", "Ingresa nuevo report Mantencion usuario MADA: "+s.userName);
	    	    			con.close();
	    	    			return redirect("/routes3/mantReportNew/");
		    			}
	    			}
	    			
	    			con.close();
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
				return ok(mensajes.render("/",msgError));
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
    			Connection con = dbWrite.getConnection();
    			
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
    			Connection con = dbWrite.getConnection();
    			
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
	    			Connection con = dbWrite.getConnection();
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
	    			Connection con = dbWrite.getConnection();
	    			
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
	    			Connection con = dbWrite.getConnection();
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
	    			Connection con = dbWrite.getConnection();
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
      			Connection con = dbWrite.getConnection();
      			
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
      			Connection con = dbWrite.getConnection();
      			
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
  	    			Connection con = dbWrite.getConnection();
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
  	    			Connection con = dbWrite.getConnection();
  	    			
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
  	    			Connection con = dbWrite.getConnection();
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
  	    			Connection con = dbWrite.getConnection();
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
      			Connection con = dbWrite.getConnection();
      			
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
      			Connection con = dbWrite.getConnection();
      			
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
  	    			Connection con = dbWrite.getConnection();
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
  	    			Connection con = dbWrite.getConnection();
  	    			
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
  	    			Connection con = dbWrite.getConnection();
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
  	    			Connection con = dbWrite.getConnection();
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
      			Connection con = dbWrite.getConnection();
      			
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
      			Connection con = dbWrite.getConnection();
      			
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
  	    			Connection con = dbWrite.getConnection();
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
  	    			Connection con = dbWrite.getConnection();
  	    			
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
  	    			Connection con = dbWrite.getConnection();
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
  	    			Connection con = dbWrite.getConnection();
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
      			Connection con = dbWrite.getConnection();
      			
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
      			Connection con = dbWrite.getConnection();
      			
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
  	    			Connection con = dbWrite.getConnection();
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
  	    			Connection con = dbWrite.getConnection();
  	    			
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
  	    			Connection con = dbWrite.getConnection();
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
  	    			Connection con = dbWrite.getConnection();
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
      			Connection con = dbWrite.getConnection();
      			
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
      			Connection con = dbWrite.getConnection();
      			
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
  	    			Connection con = dbWrite.getConnection();
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
  	    			Connection con = dbWrite.getConnection();
  	    			
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
  	    			Connection con = dbWrite.getConnection();
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
  	    			Connection con = dbWrite.getConnection();
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
      			Connection con = dbWrite.getConnection();
      			
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
      			Connection con = dbWrite.getConnection();
      			
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
  	    			Connection con = dbWrite.getConnection();
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
  	    			Connection con = dbWrite.getConnection();
  	    			
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
  	    			Connection con = dbWrite.getConnection();
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
  	    			Connection con = dbWrite.getConnection();
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
    			Connection con = dbWrite.getConnection();
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
    			Connection con = dbWrite.getConnection();
    			
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
	    			Connection con = dbWrite.getConnection();
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
	    			Connection con = dbWrite.getConnection();
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
	    			Connection con = dbWrite.getConnection();
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
	    			Connection con = dbWrite.getConnection();
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
	    			Connection con = dbWrite.getConnection();
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
    
    
    //============================================================
    // MNU PLANES MANTENCION   Listar Report
    //============================================================
    
    public Result mantListarReports0(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = dbWrite.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("mantReportEnMada")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Fechas hoy = Fechas.hoy();
    			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			con.close();
    			return ok(mantListarReports0.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    	return ok(mensajes.render("/",msgError));
	}
    
    public Result mantListarReports1(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String desdeAAMMDD = form.get("fechaDesde").trim();
	       		String hastaAAMMDD = form.get("fechaHasta").trim();
	    		try {
	    			Connection con = dbWrite.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("mantReportEnMada")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			List<MantTransacReport> listReport = MantTransacReport.allEntreFechas(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
	    			List<List<String>> listado = MantTransacReport.listaDeReports(listReport);
					PreparedStatement smt = con.prepareStatement("select max(id) from `"+s.baseDato+"`.mantTransacReport where (id_mantMecanico>0 and id_tipoPlan = 0) or id_mantOperador>0 group by id_equipo" +
							" union " +
							" select max(id) from `"+s.baseDato+"`.mantTransacReport where id_mantMecanico>0 and id_tipoPlan > 0  group by id_equipo;");
					ResultSet rs = smt.executeQuery();
					Map<String,String> mapDelete = new HashMap<String,String>();
					while(rs.next()) {
						mapDelete.put(rs.getString(1), rs.getString(1));
					}
					rs.close();
					smt.close();
    				con.close();
    				return ok(mantListarReports1.render(mapeoDiccionario,mapeoPermiso,userMnu,listado,desdeAAMMDD,hastaAAMMDD, mapDelete));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    	}
    	}
    	return ok(mensajes.render("/",msgError));
    }
    
    public Result mantListarReports1Get(Http.Request request, String desdeAAMMDD, String hastaAAMMDD) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		try {
			Connection con = dbWrite.getConnection();
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
			if(mapeoPermiso.get("mantReportEnMada")==null) {
				con.close();
				return ok(mensajes.render("/",msgSinPermiso));
			}
			List<MantTransacReport> listReport = MantTransacReport.allEntreFechas(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
			List<List<String>> listado = MantTransacReport.listaDeReports(listReport);
			PreparedStatement smt = con.prepareStatement("select max(id) from `"+s.baseDato+"`.mantTransacReport where (id_mantMecanico>0 and id_tipoPlan = 0) or id_mantOperador>0 group by id_equipo" +
					" union " +
					" select max(id) from `"+s.baseDato+"`.mantTransacReport where id_mantMecanico>0 and id_tipoPlan > 0  group by id_equipo;");
			ResultSet rs = smt.executeQuery();
			Map<String,String> mapDelete = new HashMap<String,String>();
			while(rs.next()) {
				mapDelete.put(rs.getString(1), rs.getString(1));
			}
			rs.close();
			smt.close();
		con.close();
		return ok(mantListarReports1.render(mapeoDiccionario,mapeoPermiso,userMnu,listado,desdeAAMMDD,hastaAAMMDD, mapDelete));
    	} catch (SQLException e) {
			e.printStackTrace();
		}
    	}
    	return ok(mensajes.render("/",msgError));
    }
    
    public Result mantListarReports1Excel(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String desdeAAMMDD = form.get("fechaDesde").trim();
	       		String hastaAAMMDD = form.get("fechaHasta").trim();
	    		try {
	    			Connection con = dbWrite.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("mantReportEnMada")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			List<MantTransacReport> listReport = MantTransacReport.allEntreFechas(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
	    			List<List<String>> listado = MantTransacReport.listaDeReports(listReport);
	    			listado.sort(Comparator.comparing((List<String> list) -> list.get(1), Comparator.reverseOrder())
                            .thenComparing(list -> list.get(0), Comparator.reverseOrder()));
	    			File file = MantTransacReport.exportaListaDeReportsExcel(s.baseDato, mapeoDiccionario, listado, desdeAAMMDD, hastaAAMMDD);
	    			if(file!=null) {
		       			con.close();
		       			return ok(file,false,Optional.of("Lista_de_report_diario.xlsx"));
		       		}else {
		       			con.close();
		       			return ok("");
		       		}
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    	}
    	}
    	return ok(mensajes.render("/",msgError));
    }
    
    public Result mantReportDetalle(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_mantTransacReport = Long.parseLong(form.get("id_mantTransacReport"));
	    		try {
	    			Connection con = dbWrite.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			MantTransacReport mantTransacReport = MantTransacReport.find(con, s.baseDato, id_mantTransacReport);
	    			if(mantTransacReport.getId_mantActorPersonal() == (long)1) {
	    				con.close();
	    				return ok(mantReportOperador.render(mapeoDiccionario,mapeoPermiso,userMnu,mantTransacReport));
	    			}else {
	    				List<MantTransacComponentes> mantTransacComponentes = MantTransacComponentes.allPorIdMantTransacReport(con, s.baseDato, id_mantTransacReport);
	    				con.close();
	    				return ok(mantReportMecanico.render(mapeoDiccionario,mapeoPermiso,userMnu,mantTransacReport,mantTransacComponentes));
	    			}
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    	}
    	}
    	return ok(mensajes.render("/",msgError));
    }
    
    public Result mantReportGrabaDocAnexo(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		Archivos archivos = formFactory.form(Archivos.class).withDirectFieldAccess(true).bindFromRequest(request).get();
			if (archivos != null) {
				if(archivos.docAdjunto != null) {
					DynamicForm form = formFactory.form().bindFromRequest(request);
			   		if (form.hasErrors()) {
			   			return ok(mensajes.render("/",msgErrorFormulario));
			       	}else {
			       		try {
			    			Connection con = dbWrite.getConnection();
    			       		Long id_mantTransacReport = Long.parseLong(form.get("id_mantTransacReport").trim());
    			       		String desdeAAMMDD = form.get("desdeAAMMDD").trim();
    			       		String hastaAAMMDD = form.get("hastaAAMMDD").trim();
    			       		String nombreArchivoSinExtencion = MantTransacReport.nombreDocAnexo(con, s.baseDato, archivos, id_mantTransacReport);
	    					MnuMantencion.grabarFilesThread grabarFile = new MnuMantencion.grabarFilesThread(s.baseDato, archivos, nombreArchivoSinExtencion);
			    			grabarFile.run();
			    			String msg = "Archivos subidos con exito, esto puede tomar algunos minutos en actualizar la lista";
		    				con.close();
		    				return ok(mensajes.render("/routes3/mantListarReports1Get/"+desdeAAMMDD+","+hastaAAMMDD,msg));
		    				
		    			} catch (SQLException e) {
			    			e.printStackTrace();
			    		}
			       	}
				}
			}
    		return redirect("odoListarVentas1");
    	}
    	return ok(mensajes.render("/",msgError));
    }
    
    public Result mantFirmaOperador(Http.Request request, Long id_mantTransacReport, String desdeAAMMDD, String hastaAAMMDD) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = dbWrite.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("mantReportEnMada")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			MantTransacReport mantTransacReport = MantTransacReport.find(con, s.baseDato, id_mantTransacReport);
    			if(mantTransacReport.getId_mantActorPersonal() == (long)1) {
    				con.close();
    				return ok(mantReportOperadorFirmaOper.render(mapeoDiccionario,mapeoPermiso,userMnu,mantTransacReport, desdeAAMMDD, hastaAAMMDD));
    			}else {
    				List<MantTransacComponentes> mantTransacComponentes = MantTransacComponentes.allPorIdMantTransacReport(con, s.baseDato, id_mantTransacReport);
    				con.close();
    				return ok(mantReportMecanicoFirmaMec.render(mapeoDiccionario,mapeoPermiso,userMnu,mantTransacReport,mantTransacComponentes,desdeAAMMDD,hastaAAMMDD));
    			}
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    	return ok(mensajes.render("/",msgError));
    }
    
    
    public Result mantGrabarFirmaOperador(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
    		}else {
    			Long id_mantTransacReport = Long.parseLong(form.get("id_mantTransacReport").trim());
    			String firmaPDFoperador = form.get("firmaPDFoperador").trim();
    			String desdeAAMMDD = form.get("fechaDesde").trim();
	       		String hastaAAMMDD = form.get("fechaHasta").trim();
    			try {
	    			Connection con = dbWrite.getConnection();
	    			if(MantTransacReport.modificaPorCampo(con, s.baseDato, "firmaPDFoperador", id_mantTransacReport, firmaPDFoperador)) {
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "mantTransacReport", id_mantTransacReport, "insert", "agrega o modifica firma operador/mecanico en reporte nro: "+id_mantTransacReport);
	    				MantTransacReport mantTransacReport = MantTransacReport.find(con, s.baseDato, id_mantTransacReport);
	    				if(mantTransacReport.getId_mantActorPersonal() == (long)1) {
	    					FormMantencion.pdfReportMantOperador(con, s.baseDato, mantTransacReport);
	        			}else {
	        				FormMantencion.pdfReportMantMecanico(con, s.baseDato, mantTransacReport);
	        			}
	    			}
    				con.close();
    				return redirect("/routes3/mantListarReports1Get/"+desdeAAMMDD+","+hastaAAMMDD);
    			} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
    		}
    	}
    	return ok(mensajes.render("/",msgError));
    }
    
    public Result mantFirmaAutorizador(Http.Request request, Long id_mantTransacReport, String desdeAAMMDD, String hastaAAMMDD) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = dbWrite.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("mantReportEnMada")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			MantTransacReport mantTransacReport = MantTransacReport.find(con, s.baseDato, id_mantTransacReport);
    			if(mantTransacReport.getId_mantActorPersonal() == (long)1) {
    				con.close();
    				return ok(mantReportOperadorFirmaSuper.render(mapeoDiccionario,mapeoPermiso,userMnu,mantTransacReport, desdeAAMMDD, hastaAAMMDD));
    			}else {
    				List<MantTransacComponentes> mantTransacComponentes = MantTransacComponentes.allPorIdMantTransacReport(con, s.baseDato, id_mantTransacReport);
    				con.close();
    				return ok(mantReportMecanicoFirmaSuper.render(mapeoDiccionario,mapeoPermiso,userMnu,mantTransacReport,mantTransacComponentes,desdeAAMMDD,hastaAAMMDD));
    			}
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    	return ok(mensajes.render("/",msgError));
    }
    
    public Result mantGrabarFirmaAutorizador(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
    		}else {
    			Long id_mantTransacReport = Long.parseLong(form.get("id_mantTransacReport").trim());
    			String firmaPDFautorizador = form.get("firmaPDFautorizador").trim();
    			String desdeAAMMDD = form.get("fechaDesde").trim();
	       		String hastaAAMMDD = form.get("fechaHasta").trim();
    			try {
	    			Connection con = dbWrite.getConnection();
	    			if(MantTransacReport.modificaPorCampo(con, s.baseDato, "firmaPDFautorizador", id_mantTransacReport, firmaPDFautorizador)) {
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "mantTransacReport", id_mantTransacReport, "insert", "agrega o modifica firma supervisor en reporte nro: "+id_mantTransacReport);
	    				MantTransacReport mantTransacReport = MantTransacReport.find(con, s.baseDato, id_mantTransacReport);
	    				if(mantTransacReport.getId_mantActorPersonal() == (long)1) {
	    					FormMantencion.pdfReportMantOperador(con, s.baseDato, mantTransacReport);
	        			}else {
	        				FormMantencion.pdfReportMantMecanico(con, s.baseDato, mantTransacReport);
	        			}
	    			}
    				con.close();
    				return redirect("/routes3/mantListarReports1Get/"+desdeAAMMDD+","+hastaAAMMDD);
    			} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
    		}
    	}
    	return ok(mensajes.render("/",msgError));
    }
    
    public Result mantReportElimina(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_mantTransacReport = Long.parseLong(form.get("id_mantTransacReport").trim());
	       		String desdeAAMMDD = form.get("fechaDesde").trim();
	       		String hastaAAMMDD = form.get("fechaHasta").trim();
	    		try {
	    			Connection con = dbWrite.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			if(mapeoPermiso.get("mantEliminaReportAdam")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			MantTransacReport.delete(con, s.baseDato, mapeoPermiso, id_mantTransacReport);
	    			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "mantTransacReport", id_mantTransacReport, "delete", "elimina report mant nro: "+id_mantTransacReport);
	    			con.close();
	    			return redirect("/routes3/mantListarReports1Get/"+desdeAAMMDD+","+hastaAAMMDD);
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    	return ok(mensajes.render("/",msgError));
    }
    
    
    //============================================================
    // MNU PLANES MANTENCION   Historial Report
    //============================================================
    
    public Result mantHistorialReports0(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = dbRead.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("mantReportEnMada")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Fechas hoy = Fechas.hoy();
    			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			
    			List<MantActorPersonal> listActor = MantActorPersonal.all(con, s.baseDato); 
    			List<MantOperadorMecanico> listOperMec = MantOperadorMecanico.allVigentes(con, s.baseDato);
    			List<PlanMantencion> listPlanMant = PlanMantencion.all(con, s.baseDato);
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
    			
    			con.close();
    			return ok(mantHistorialReports0.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta,
    					listActor, listOperMec, listTipoMantencion, listEquipos, listPlanMant));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    	return ok(mensajes.render("/",msgError));
	}
    
    public Result mantHistorialReports1(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String desdeAAMMDD = form.get("fechaDesde").trim();
	       		String hastaAAMMDD = form.get("fechaHasta").trim();
	       		
	       		Long id_mantActorPersonal = Long.parseLong(form.get("id_mantActorPersonal").trim());
	       		Long id_operMec = Long.parseLong(form.get("id_operMec").trim());
	       		Long id_tipoMantencion = Long.parseLong(form.get("id_tipoMantencion").trim());
	       		Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
	       		
	    		try {
	    			Connection con = dbRead.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("mantReportEnMada")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			List<MantTransacReport> listReport = MantTransacReport.allEntreFechas(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
	    			List<List<String>> listado = MantTransacReport.historialDeReports(con, s.baseDato, listReport, 
	    					id_mantActorPersonal, id_operMec, id_tipoMantencion, id_equipo);
    			con.close();
    			return ok(mantHistorialReports1.render(mapeoDiccionario,mapeoPermiso,userMnu,listado,desdeAAMMDD,hastaAAMMDD,
    					id_mantActorPersonal, id_operMec, id_tipoMantencion, id_equipo));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    	}
    	}
    	return ok(mensajes.render("/",msgError));
    }
    
    public Result mantHistorialReports1Excel(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String desdeAAMMDD = form.get("fechaDesde").trim();
	       		String hastaAAMMDD = form.get("fechaHasta").trim();
	       		Long id_mantActorPersonal = Long.parseLong(form.get("id_mantActorPersonal").trim());
	       		Long id_operMec = Long.parseLong(form.get("id_operMec").trim());
	       		Long id_tipoMantencion = Long.parseLong(form.get("id_tipoMantencion").trim());
	       		Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
	    		try {
	    			Connection con = dbRead.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("mantReportEnMada")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			List<MantTransacReport> listReport = MantTransacReport.allEntreFechas(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
	    			List<List<String>> listado = MantTransacReport.historialDeReports(con, s.baseDato, listReport, 
	    					id_mantActorPersonal, id_operMec, id_tipoMantencion, id_equipo);
	    	        listado.sort(Comparator.comparing((List<String> list) -> list.get(1), Comparator.reverseOrder())
	    	                               .thenComparing(list -> list.get(0), Comparator.reverseOrder()));
	    			File file = MantTransacReport.exportaHistorialDeReportsExcel(s.baseDato, mapeoDiccionario, listado, desdeAAMMDD, hastaAAMMDD);
	    			if(file!=null) {
		       			con.close();
		       			return ok(file,false,Optional.of("Historial_de_report.xlsx"));
		       		}else {
		       			con.close();
		       			return ok("");
		       		}
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    	}
    	}
    	return ok(mensajes.render("/",msgError));
    }
    
    //============================================================
    // MNU PLANES MANTENCION   Control Mantenciones
    //============================================================
    
    public Result mantControlMantenciones(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = dbRead.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("mantReportEnMada")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Map<String, MantTransacReport> mapeoPreventivo = MantTransacReport.mapeoMantPreventivo(con, s.baseDato);
    			Map<Long, MantTransacReport> mapeoOperYCorrectivo = MantTransacReport.mapeoMantOperYCorrectivo(con, s.baseDato);
    			List<PlanMantencion> listPlanMant = PlanMantencion.allEquiposVigentes(con, s.baseDato);
    			List<List<String>> lista = MantTransacReport.controlMantenciones(con, s.baseDato, mapeoPreventivo, mapeoOperYCorrectivo, listPlanMant);
    			con.close();
    			return ok(mantControlMantenciones.render(mapeoDiccionario, mapeoPermiso, userMnu, lista));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    	return ok(mensajes.render("/",msgError));
	}
    
    public Result mantControlMantencionesExcel(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		try {
	    			Connection con = dbRead.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("mantReportEnMada")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			Map<String, MantTransacReport> mapeoPreventivo = MantTransacReport.mapeoMantPreventivo(con, s.baseDato);
	    			Map<Long, MantTransacReport> mapeoOperYCorrectivo = MantTransacReport.mapeoMantOperYCorrectivo(con, s.baseDato);
	    			List<PlanMantencion> listPlanMant = PlanMantencion.allEquiposVigentes(con, s.baseDato);
	    			List<List<String>> lista = MantTransacReport.controlMantenciones(con, s.baseDato, mapeoPreventivo, mapeoOperYCorrectivo, listPlanMant);
	    			File file = MantTransacReport.exportaControlMantencionesExcel(s.baseDato, mapeoDiccionario, lista);
	    			if(file!=null) {
		       			con.close();
		       			return ok(file,false,Optional.of("Control_mantencion.xlsx"));
		       		}else {
		       			con.close();
		       			return ok("");
		       		}
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    	}
    	}
    	return ok(mensajes.render("/",msgError));
    }

	//============================================================
	// MNU PLANES MANTENCION   Cantidad Operacional y Mantencion
	//============================================================

	public Result mantCantOperacional0(Http.Request request) {
		Sessiones s = new Sessiones(request);
		if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
			UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
			if(mapeoPermiso.get("mantReportEnMada")==null) {
				return ok(mensajes.render("/",msgSinPermiso));
			}
			Fechas hoy = Fechas.hoy();
			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			return ok(mantCantOperacional0.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta));
		}
		return ok(mensajes.render("/",msgError));
	}

	public Result mantCantOperacional1(Http.Request request) {
		Sessiones s = new Sessiones(request);
		if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
			UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
			DynamicForm form = formFactory.form().bindFromRequest(request);
			if (form.hasErrors()) {
				return ok(mensajes.render("/",msgErrorFormulario));
			}else {
				String desdeAAMMDD = form.get("fechaDesde").trim();
				String hastaAAMMDD = form.get("fechaHasta").trim();
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				if(mapeoPermiso.get("mantReportEnMada")==null) {
					return ok(mensajes.render("/",msgSinPermiso));
				}
				try {
					Connection con = dbRead.getConnection();
					Map<Long,List<String>> mapCantMecCorr = MantTransacReport.mapCantMecCorr(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
					Map<Long,List<String>> mapCantOper = MantTransacReport.mapCantOper(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
					Map<Long,List<String>> mapAux = new HashMap<Long,List<String>>();

					for(Map.Entry<Long, List<String>> entry : mapCantOper.entrySet()) {
						Long k = entry.getKey();
						List<String> v = entry.getValue();
						if(k!=null) {
							mapAux.put(k,Arrays.asList(k.toString(), v.get(1), v.get(2)));
						}
					}
					for(Map.Entry<Long, List<String>> entry : mapCantMecCorr.entrySet()) {
						Long k = entry.getKey();
						List<String> v = entry.getValue();
						if(k!=null) {
							mapAux.put(k,Arrays.asList(k.toString(), v.get(1), v.get(2)));
						}
					}
					List<List<String>> lista = new ArrayList<List<String>>();
					for(Map.Entry<Long, List<String>> entry : mapAux.entrySet()) {
						Long k = entry.getKey();
						List<String> v = entry.getValue();
						List<String> aux = Arrays.asList(k.toString(), v.get(1), v.get(2), "", "", "", "");
						List<String> auxCantOper = mapCantOper.get(k);
						if(auxCantOper!=null) {
							aux.set(3,auxCantOper.get(3));
							aux.set(4,auxCantOper.get(4));
						}else {
							aux.set(3,"0.00");
							aux.set(4,"0.00");
						}
						List<String> auxCantMecCorr = mapCantMecCorr.get(k);
						if(auxCantMecCorr!=null) {
							aux.set(5,auxCantMecCorr.get(3));
							aux.set(6,auxCantMecCorr.get(4));
						}else {
							aux.set(5,"0.00");
							aux.set(6,"0.00");
						}
						lista.add(aux);
					}
					con.close();
					return ok(mantCantOperacional1.render(mapeoDiccionario,mapeoPermiso,userMnu, lista, desdeAAMMDD, hastaAAMMDD));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ok(mensajes.render("/",msgError));
	}

	public Result mantCantOperacional1Excel(Http.Request request) {
		Sessiones s = new Sessiones(request);
		if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
			UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
			DynamicForm form = formFactory.form().bindFromRequest(request);
			if (form.hasErrors()) {
				return ok(mensajes.render("/",msgErrorFormulario));
			}else {
				String desdeAAMMDD = form.get("fechaDesde").trim();
				String hastaAAMMDD = form.get("fechaHasta").trim();
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				if(mapeoPermiso.get("mantReportEnMada")==null) {
					return ok(mensajes.render("/",msgSinPermiso));
				}
				try {
					Connection con = dbRead.getConnection();
					Map<Long,List<String>> mapCantMecCorr = MantTransacReport.mapCantMecCorr(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
					Map<Long,List<String>> mapCantOper = MantTransacReport.mapCantOper(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
					Map<Long,List<String>> mapAux = new HashMap<Long,List<String>>();

					for(Map.Entry<Long, List<String>> entry : mapCantOper.entrySet()) {
						Long k = entry.getKey();
						List<String> v = entry.getValue();
						if(k!=null) {
							mapAux.put(k,Arrays.asList(k.toString(), v.get(1), v.get(2)));
						}
					}
					for(Map.Entry<Long, List<String>> entry : mapCantMecCorr.entrySet()) {
						Long k = entry.getKey();
						List<String> v = entry.getValue();
						if(k!=null) {
							mapAux.put(k,Arrays.asList(k.toString(), v.get(1), v.get(2)));
						}
					}
					List<List<String>> lista = new ArrayList<List<String>>();
					for(Map.Entry<Long, List<String>> entry : mapAux.entrySet()) {
						Long k = entry.getKey();
						List<String> v = entry.getValue();
						List<String> aux = Arrays.asList(k.toString(), v.get(1), v.get(2), "", "", "", "");
						List<String> auxCantOper = mapCantOper.get(k);
						if(auxCantOper!=null) {
							aux.set(3,auxCantOper.get(3));
							aux.set(4,auxCantOper.get(4));
						}else {
							aux.set(3,"0.00");
							aux.set(4,"0.00");
						}
						List<String> auxCantMecCorr = mapCantMecCorr.get(k);
						if(auxCantMecCorr!=null) {
							aux.set(5,auxCantMecCorr.get(3));
							aux.set(6,auxCantMecCorr.get(4));
						}else {
							aux.set(5,"0.00");
							aux.set(6,"0.00");
						}
						lista.add(aux);
					}
					con.close();
					File file = MantTransacReport.mantCantOperacionalExcel(s.baseDato, mapeoDiccionario, lista, desdeAAMMDD, hastaAAMMDD);
					if(file!=null) {
						con.close();
						return ok(file,false,Optional.of("cant_operacional_correctivo.xlsx"));
					}else {
						con.close();
						return ok("");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ok(mensajes.render("/",msgError));
	}

	public Result mantCantMantencion0(Http.Request request) {
		Sessiones s = new Sessiones(request);
		if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
			UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
			if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				if(mapeoPermiso.get("mantReportEnMada")==null) {
					return ok(mensajes.render("/",msgSinPermiso));
				}
				Fechas hoy = Fechas.hoy();
				String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
				String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
				return ok(mantCantMantencion0.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta));
			}
		}
		return ok(mensajes.render("/",msgError));
	}

	public Result mantCantMantencion1(Http.Request request) {
		Sessiones s = new Sessiones(request);
		if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
			UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
			DynamicForm form = formFactory.form().bindFromRequest(request);
			if (form.hasErrors()) {
				return ok(mensajes.render("/",msgErrorFormulario));
			}else {
				String desdeAAMMDD = form.get("fechaDesde").trim();
				String hastaAAMMDD = form.get("fechaHasta").trim();
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				if(mapeoPermiso.get("mantReportEnMada")==null) {
					return ok(mensajes.render("/",msgSinPermiso));
				}
				try {
					Connection con = dbRead.getConnection();
					List<List<String>> lista = MantTransacReport.listCantMecPrev(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
					con.close();
					return ok(mantCantMantencion1.render(mapeoDiccionario,mapeoPermiso,userMnu, lista, desdeAAMMDD, hastaAAMMDD));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ok(mensajes.render("/",msgError));
	}

	public Result mantCantMantencion1Excel(Http.Request request) {
		Sessiones s = new Sessiones(request);
		if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
			UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
			DynamicForm form = formFactory.form().bindFromRequest(request);
			if (form.hasErrors()) {
				return ok(mensajes.render("/",msgErrorFormulario));
			}else {
				String desdeAAMMDD = form.get("fechaDesde").trim();
				String hastaAAMMDD = form.get("fechaHasta").trim();
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				if(mapeoPermiso.get("mantReportEnMada")==null) {
					return ok(mensajes.render("/",msgSinPermiso));
				}
				try {
					Connection con = dbRead.getConnection();
					List<List<String>> lista = MantTransacReport.listCantMecPrev(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
					con.close();
					File file = MantTransacReport.mantCantMantencionExcel(s.baseDato, mapeoDiccionario, lista, desdeAAMMDD, hastaAAMMDD);
					if(file!=null) {
						con.close();
						return ok(file,false,Optional.of("cant_preventivo_mantencion.xlsx"));
					}else {
						con.close();
						return ok("");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ok(mensajes.render("/",msgError));
	}
}
