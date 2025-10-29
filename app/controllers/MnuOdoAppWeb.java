package controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.*;

import models.utilities.*;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.TempFile;

import controllers.HomeController.Sessiones;
import models.reports.ReportOdoMovimientos;
import models.tables.AuxHuella;
import models.tables.BodegaEmpresa;
import models.tables.EquipoServicio;
import models.tables.ListaPrecioServicio;
import models.tables.OperadorServicio;
import models.tables.Sucursal;
import models.tables.UsuarioPermiso;
import models.tables.VentaServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.Database;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.mensajes;
import views.html.vistaPrincipal;
import viewsMnuOdoAppWeb.html.*;

public class MnuOdoAppWeb extends Controller {

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
	// WEB odoVentas   Odo/Ingreso Report WEB
	//============================================================


	public Result odoVentasWeb(Http.Request request) {
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, "", "");
			return ok(mensajes.render("/odo", msgErrorFormulario));
		}else {
			try (Connection con = dbWrite.getConnection()){
				String userName = form.get("userName");
				String empresa = form.get("empresa");
				String userKey = form.get("userKey");
				String pais = form.get("pais");
				String gRecaptchaResponse = form.get("gRecaptchaResponse");
				if(!empresa.equals("ALTRAD RMDK CHILE")) {
					boolean verificado = false;
					try {
						verificado = VerificarCaptcha.verificar(gRecaptchaResponse);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					if(!verificado) {
						String mensaje = "CAPTCHA NO VALIDO";
						return ok(mensajes.render("/",mensaje));
					}
				}
				Inicio inicio = Inicio.findXempresaVigente(con,userName,empresa,pais);
				if(inicio.getId()==-1){
					String msg = "La empresa no esta disponible, si desea acceder, pongase en contacto con soporte pbarros@inqsol.com";
					return ok(mensajes.render("/odo",msg));
				}
				if(inicio.getId()==-2){
					String msg = "Los datos ingresados de empresa, no corresponden, vuelva a ingresarlos. En caso de continuar " +
								"este problema, por favor contactar con soporte pbarros@inqsol.com";
					return ok(mensajes.render("/odo",msg));
				}
				if(inicio.getId()==-3){
					String msg = "El usuario no esta vigente o no existe en la empresa, vuelva a ingresar. En caso de continuar " +
								"este problema, por favor contactar con soporte pbarros@inqsol.com";
					return ok(mensajes.render("/odo",msg));
				}
				if(!(inicio.getUserKey().equals(userKey) && inicio.getEmpresa().toLowerCase().equals(empresa.toLowerCase()))){
					String msg = "La clave ingresada no corresponde, vuelva a ingresar. En caso de continuar " +
							"este problema, por favor contactar con soporte pbarros@inqsol.com";
					return ok(mensajes.render("/odo",msg));
				}else {
					Registro.accesos(con, inicio.getBaseDato(), userName);
					Map<String,String> mapeoPermiso = HomeController.mapPermisos(inicio.getBaseDato(), inicio.getId_tipoUsuario().toString());
					Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(inicio.getBaseDato());

					boolean esPorSucursal = Sucursal.esPorSucursal(mapeoPermiso, inicio.getId_tipoUsuario().toString());
					String aplicaPorSucursal = "0";
					if(esPorSucursal) {
						aplicaPorSucursal = "1";
					}
					UserMnu userMnu = new UserMnu(inicio.getUserName(), inicio.getId().toString(), inicio.getId_tipoUsuario().toString(),
							inicio.getBaseDato(), inicio.getId_sucursal().toString(), inicio.getPorProyecto().toString(),aplicaPorSucursal);
					if(mapeoPermiso.get("odoVentasAppWeb")!=null) {
						Registro.accesos(con, inicio.getBaseDato(), userName);
						if(mapeoPermiso.get("odoVentas")==null && mapeoPermiso.get("odoVentasAppWeb")==null) {
							return ok(mensajes.render("/odo",msgSinPermiso));
						}
						String fechaAAMMDD = Fechas.hoy().getFechaStrAAMMDD();
						OperadorServicio operador = OperadorServicio.findPorIdUserMada(con,inicio.baseDato,userMnu.getId_usuario());

						List<List<String>> listBodegasConEquipos = EquipoServicio.listaBodegasConEquipos(con, inicio.getBaseDato(), aplicaPorSucursal, inicio.getId_sucursal().toString());
						List<List<String>> listBodegasConServicios = ListaPrecioServicio.listaBodegasConServicios(con, inicio.getBaseDato(), aplicaPorSucursal, inicio.getId_sucursal().toString());
						Map<String,List<String>> mapLista = new HashMap<String,List<String>>();
						listBodegasConEquipos.forEach(x->{
							mapLista.put(x.get(0), x);
						});
						listBodegasConServicios.forEach(x->{
							mapLista.put(x.get(0), x);
						});
						List<List<String>> lista = new ArrayList<List<String>>();
						mapLista.forEach((k,v)->{
							lista.add(v);
						});
						//ORDENA LA LISTA
						for(int j=0;j<lista.size();j++) {
							for(int i=0;i<lista.size()-j;i++) {
								if (i+1!=lista.size()&&lista.get(i).get(1).compareTo(lista.get(i+1).get(1))>0) {
									List<String> aux = new ArrayList<String>();
									aux=lista.get(i);
									lista.set(i,lista.get(i+1));
									lista.set(i+1, aux);
								}
							}
						}
						Long aux_huella = AuxHuella.findHuella(con, inicio.getBaseDato(), userMnu.getId_usuario());
						return ok(odoVentasWeb.render(mapeoDiccionario,mapeoPermiso,userMnu,fechaAAMMDD,operador,lista,aux_huella))
								.addingToSession(request, "userName", inicio.getUserName())
								.addingToSession(request, "id_usuario", inicio.getId().toString())
								.addingToSession(request, "baseDato", inicio.getBaseDato())
								.addingToSession(request, "id_tipoUsuario", inicio.getId_tipoUsuario().toString())
								.addingToSession(request, "porProyecto", "0")
								.addingToSession(request, "id_sucursal", inicio.getId_sucursal().toString())
								.addingToSession(request, "aplicaPorSucursal", aplicaPorSucursal);
					}else if(mapeoPermiso.get("odoAutorizadorAppWeb")!=null) {
						Registro.accesos(con, inicio.getBaseDato(), userName);
						List<VentaServicio> listVentas = VentaServicio.allPorBodegas(con, inicio.getBaseDato(), mapeoPermiso, aplicaPorSucursal, inicio.getId_sucursal().toString(), inicio.getId());
						return ok(odoAutorizaListarVentasWeb.render(mapeoDiccionario,mapeoPermiso,userMnu,listVentas))
								.addingToSession(request, "userName", inicio.getUserName())
								.addingToSession(request, "id_usuario", inicio.getId().toString())
								.addingToSession(request, "baseDato", inicio.getBaseDato())
								.addingToSession(request, "id_tipoUsuario", inicio.getId_tipoUsuario().toString())
								.addingToSession(request, "porProyecto", inicio.getPorProyecto().toString())
								.addingToSession(request, "id_sucursal", inicio.getId_sucursal().toString())
								.addingToSession(request, "aplicaPorSucursal", aplicaPorSucursal);
					}else {
						Registro.accesos(con, inicio.getBaseDato(), userName);
						Long esMoroso = Registro.esMoroso(con, inicio.getBaseDato());
						return ok(vistaPrincipal.render(mapeoDiccionario,mapeoPermiso,userMnu,esMoroso))
								.addingToSession(request, "userName", inicio.getUserName())
								.addingToSession(request, "id_usuario", inicio.getId().toString())
								.addingToSession(request, "baseDato", inicio.getBaseDato())
								.addingToSession(request, "id_tipoUsuario", inicio.getId_tipoUsuario().toString())
								.addingToSession(request, "porProyecto", inicio.getPorProyecto().toString())
								.addingToSession(request, "id_sucursal", inicio.getId_sucursal().toString())
								.addingToSession(request, "aplicaPorSucursal", aplicaPorSucursal);
					}
				}
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, "", "", e);
				return ok(mensajes.render("/odo", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, "", "", e);
				return ok(mensajes.render("/odo", msgReport));
			}
		}
	}

	public Result odoVentasHomeWeb(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/odo", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("odoVentas")==null || mapeoPermiso.get("parametro.mnuODO")==null || mapeoPermiso.get("parametro.mnuODO").equals("0")) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/odo",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			String fechaAAMMDD = Fechas.hoy().getFechaStrAAMMDD();
			OperadorServicio operador = OperadorServicio.findPorIdUserMada(con,s.baseDato,userMnu.getId_usuario());
			if(operador==null) {
				operador = new OperadorServicio();
				operador.id = 0L;
				operador.id_userAdam = Long.parseLong(userMnu.getId_usuario());
				operador.nombre = userMnu.getUserName();
			}

			List<List<String>> listBodegasConEquipos = EquipoServicio.listaBodegasConEquipos(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
			List<List<String>> listBodegasConServicios = ListaPrecioServicio.listaBodegasConServicios(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
			Map<String,List<String>> mapLista = new HashMap<String,List<String>>();
			listBodegasConEquipos.forEach(x->{
				mapLista.put(x.get(0), x);
			});
			listBodegasConServicios.forEach(x->{
				mapLista.put(x.get(0), x);
			});
			List<List<String>> lista = new ArrayList<List<String>>();
			mapLista.forEach((k,v)->{
				lista.add(v);
			});
			//ORDENA LA LISTA
			for(int j=0;j<lista.size();j++) {
				for(int i=0;i<lista.size()-j;i++) {
					if (i+1!=lista.size()&&lista.get(i).get(1).compareTo(lista.get(i+1).get(1))>0) {
						List<String> aux = new ArrayList<String>();
						aux=lista.get(i);
						lista.set(i,lista.get(i+1));
						lista.set(i+1, aux);
					}
				}
			}
			Long aux_huella = AuxHuella.findHuella(con, s.baseDato, userMnu.getId_usuario());
			return ok(odoVentasWeb.render(mapeoDiccionario,mapeoPermiso,userMnu,fechaAAMMDD,operador,lista,aux_huella));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/odo", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/odo", msgReport));
		}
	}

	public Result odoVentasGrabarWeb(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/odo", msgError));
		}
		VentaServicio form = formFactory.form(VentaServicio.class).withDirectFieldAccess(true).bindFromRequest(request).get();
		if (form.getId_servicio()==null || form.getId_userMada()==null) {
			return ok(mensajes.render("/odo",msgErrorFormulario));
		}else {
			try (Connection con = dbWrite.getConnection()){
				if(AuxHuella.existeHuella(con, s.baseDato, form.getAux_huella(), form.getId_userMada().toString())) {
					AuxHuella.aumentaHuella(con, s.baseDato, form.getId_userMada().toString());
					Long id_ventaServicio = VentaServicio.create(con, s.baseDato, form);
					VentaServicio ventaServicio = VentaServicio.find(con, s.baseDato, id_ventaServicio);
					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "ventaServicio", ventaServicio.getId(), "insert", "agrega report diario nro: "+ventaServicio.getId());
					//EXTRAE FIRMAS
					byte[] decodedStrOper = Base64.getDecoder().decode(ventaServicio.getFirmaPDFoperador());
					byte[] decodedStrAut = Base64.getDecoder().decode(ventaServicio.getFirmaPDFautorizador());
					//FIN DE FIRMA
					VentaServicio.generaPdfVentaReport(con, s.baseDato, ventaServicio, decodedStrOper, decodedStrAut);
					Archivos archivos = formFactory.form(Archivos.class).withDirectFieldAccess(true).bindFromRequest(request).get();
					String nombreDocAdjunto = "0";
					String nombreArchivoSinExtencion = "";
					if (archivos != null) {
						if(archivos.docAdjunto != null) {
							nombreArchivoSinExtencion = "Doc_Odo_" + id_ventaServicio;
							if(archivos.docAdjunto.size() == 1) {
								String fileName = archivos.docAdjunto.get(0).getFilename();
								fileName = fileName.substring(fileName.indexOf("."));
								while(fileName.substring(1).indexOf(".")>0) {
									fileName=fileName.substring(1);
									fileName=fileName.substring(fileName.indexOf("."));
								}
								String ext = fileName;
								nombreDocAdjunto = nombreArchivoSinExtencion + ext;
								Archivos.grabarArchivos(archivos.docAdjunto.get(0), s.baseDato, nombreArchivoSinExtencion);
								VentaServicio.modificaPorCampo(con, s.baseDato, "docAnexo", nombreDocAdjunto, id_ventaServicio);
							}else {
								nombreDocAdjunto = nombreArchivoSinExtencion + ".zip";
								try {
									Archivos.comprimirYgrabar(archivos.docAdjunto, s.baseDato, nombreArchivoSinExtencion);
									VentaServicio.modificaPorCampo(con, s.baseDato, "docAnexo", nombreDocAdjunto, id_ventaServicio);
								} catch (Exception e) {}
							}
						}
					}
					return ok(mensajes.render("/odoVentasHomeWeb/","REPORT GRABADO CORRECTAMENTE"));
				}
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/odo", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/odo", msgReport));
			}
		}
		logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
		return ok(mensajes.render("/odo", msgReport));
	}

	public Result odoVentasGrabaDocAnexoWeb(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/odo", msgError));
		}
		Archivos archivos = formFactory.form(Archivos.class).withDirectFieldAccess(true).bindFromRequest(request).get();
		String nombreDocAdjunto = "0";
		String nombreArchivoSinExtencion = "";
		if (archivos != null) {
			if(archivos.docAdjunto != null) {
				DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
				if (form.hasErrors()) {
					logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
					return ok(mensajes.render("/odo", msgErrorFormulario));
				}else {
					try (Connection con = dbWrite.getConnection()){
						Long id_ventaServicio = Long.parseLong(form.get("id_ventaServicio").trim());
						Long year = Long.parseLong(form.get("year").trim());
						nombreArchivoSinExtencion = "Doc_Odo_" + id_ventaServicio;
						if(archivos.docAdjunto.size() == 1) {
							String fileName = archivos.docAdjunto.get(0).getFilename();
							fileName = fileName.substring(fileName.indexOf("."));
							while(fileName.substring(1).indexOf(".")>0) {
								fileName=fileName.substring(1);
								fileName=fileName.substring(fileName.indexOf("."));
							}
							String ext = fileName;
							nombreDocAdjunto = nombreArchivoSinExtencion + ext;
							Archivos.grabarArchivos(archivos.docAdjunto.get(0), s.baseDato, nombreArchivoSinExtencion);
							VentaServicio.modificaPorCampo(con, s.baseDato, "docAnexo", nombreDocAdjunto, id_ventaServicio);
						}else {
							nombreDocAdjunto = nombreArchivoSinExtencion + ".zip";
							try {
								Archivos.comprimirYgrabar(archivos.docAdjunto, s.baseDato, nombreArchivoSinExtencion);
								VentaServicio.modificaPorCampo(con, s.baseDato, "docAnexo", nombreDocAdjunto, id_ventaServicio);
							} catch (Exception e) {}
						}
						return redirect("/odoListarVentasWeb/"+year);
					} catch (SQLException e) {
						logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
						return ok(mensajes.render("/odo", msgReport));
					} catch (Exception e) {
						logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
						return ok(mensajes.render("/odo", msgReport));
					}
				}
			}
		}
		logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
		return ok(mensajes.render("/odo", msgReport));
	}

	//============================================================
	// MNU odoVentas   Odo/Listar Report
	//============================================================

	public Result odoListarVentasWeb(Http.Request request, Long year) {
		Long auxYear = year;
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/odo", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("odoVentas")==null || mapeoPermiso.get("parametro.mnuODO")==null || mapeoPermiso.get("parametro.mnuODO").equals("0")) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/odo",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			if((long) year == (long) 0) {
				Fechas hoy = Fechas.hoy();
				String[] aux = hoy.getFechaStrAAMMDD().split("-");
				year = Long.parseLong(aux[0]);
			}
			Long id_userMada = Long.parseLong(s.id_usuario);
			List<VentaServicio> listVentas = VentaServicio.allPorUserMada(con, s.baseDato, year, id_userMada);
			Long minYearVenta = VentaServicio.anioPrimeraVenta(con, s.baseDato);
			List<Long> listAnios = new ArrayList<Long>();
			Fechas hoy = Fechas.hoy();
			String[] aux = hoy.getFechaStrAAMMDD().split("-");
			year = Long.parseLong(aux[0]);
			while (minYearVenta <= year) {
				listAnios.add(year);
				year = year - 1;
			}
			return ok(odoListarVentasWeb.render(mapeoDiccionario,mapeoPermiso,userMnu,listVentas,listAnios, auxYear));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/odo", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/odo", msgReport));
		}
	}

	public Result odoDetalleVentaWeb(Http.Request request, Long id_ventaServicio) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/odo", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("odoVentas")==null || mapeoPermiso.get("parametro.mnuODO")==null || mapeoPermiso.get("parametro.mnuODO").equals("0")) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/odo",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			VentaServicio ventaServicio = VentaServicio.find(con, s.baseDato, id_ventaServicio);
			return ok(odoDetalleVentaWeb.render(mapeoDiccionario,mapeoPermiso,userMnu,ventaServicio));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/odo", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/odo", msgReport));
		}
	}

	public Result odoFirmaOperadorWeb(Http.Request request, Long id_ventaServicio) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/odo", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("odoVentas")==null || mapeoPermiso.get("parametro.mnuODO")==null || mapeoPermiso.get("parametro.mnuODO").equals("0")) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/odo",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			VentaServicio ventaServicio = VentaServicio.find(con, s.baseDato, id_ventaServicio);
			return ok(odoFirmaOperadorWeb.render(mapeoDiccionario,mapeoPermiso,userMnu,ventaServicio));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/odo", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/odo", msgReport));
		}
	}

	public Result grabarFirmaOperadorWeb(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/odo", msgError));
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/odo", msgErrorFormulario));
		}else {
			try (Connection con = dbWrite.getConnection()){
				Long id_ventaServicio = Long.parseLong(form.get("id_ventaServicio").trim());
				String firmaPDFoperador = form.get("firmaPDFoperador").trim();
				VentaServicio ventaServicio = VentaServicio.find(con, s.baseDato, id_ventaServicio);
				VentaServicio.modificaPorCampo(con, s.baseDato, "firmaPDFoperador", firmaPDFoperador, id_ventaServicio);
				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "ventaServicio", ventaServicio.getId(), "insert", "agrega o modifica firma operador report diario nro: "+ventaServicio.getId());
				//EXTRAE FIRMAS
				byte[] decodedStrOper = Base64.getDecoder().decode(firmaPDFoperador);
				byte[] decodedStrAut = Base64.getDecoder().decode(ventaServicio.getFirmaPDFautorizador());
				//FIN DE FIRMA
				VentaServicio.generaPdfVentaReport(con, s.baseDato, ventaServicio, decodedStrOper, decodedStrAut);
				return ok(mensajes.render("/odoListarVentasWeb/0","FIRMA OPERADOR GRABADA CORRECTAMENTE"));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/odo", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/odo", msgReport));
			}
		}
	}

	public Result odoFirmaAutorizadorWeb(Http.Request request, Long id_ventaServicio) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/odo", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("odoVentas")==null || mapeoPermiso.get("parametro.mnuODO")==null || mapeoPermiso.get("parametro.mnuODO").equals("0")) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/odo",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			VentaServicio ventaServicio = VentaServicio.find(con, s.baseDato, id_ventaServicio);
			return ok(odoFirmaAutorizadorWeb.render(mapeoDiccionario,mapeoPermiso,userMnu,ventaServicio));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/odo", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/odo", msgReport));
		}
	}

	public Result grabarFirmaAutorizadorWeb(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/odo", msgError));
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/odo", msgErrorFormulario));
		}else {
			try (Connection con = dbWrite.getConnection()) {
				Long id_ventaServicio = Long.parseLong(form.get("id_ventaServicio").trim());
				String firmaPDFaurorizador = form.get("firmaPDFautorizador").trim();
				VentaServicio ventaServicio = VentaServicio.find(con, s.baseDato, id_ventaServicio);
				VentaServicio.modificaPorCampo(con, s.baseDato, "firmaPDFautorizador", firmaPDFaurorizador, id_ventaServicio);
				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "ventaServicio", ventaServicio.getId(), "insert", "agrega o modifica firma autorizador report diario nro: " + ventaServicio.getId());
				//EXTRAE FIRMAS
				byte[] decodedStrOper = Base64.getDecoder().decode(ventaServicio.getFirmaPDFoperador());
				byte[] decodedStrAut = Base64.getDecoder().decode(firmaPDFaurorizador);
				//FIN DE FIRMA
				VentaServicio.generaPdfVentaReport(con, s.baseDato, ventaServicio, decodedStrOper, decodedStrAut);
				return ok(mensajes.render("/odoListarVentasWeb/0", "FIRMA AUTORIZADOR GRABADA CORRECTAMENTE"));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/odo", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/odo", msgReport));
			}
		}
	}

	public Result odoVentaServicioEliminaWeb(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/odo", msgError));
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/odo", msgErrorFormulario));
		}else {
			try (Connection con = dbWrite.getConnection()) {
				Long id_ventaServicio = Long.parseLong(form.get("id_ventaServicio").trim());
				VentaServicio.delete(con, s.baseDato, id_ventaServicio);
				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "ventaServicio", id_ventaServicio, "delete", "elimina report diario nro: " + id_ventaServicio);
				return redirect("/odoListarVentasWeb/0");
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/odo", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/odo", msgReport));
			}
		}
	}

	public Result odoAutorizaDetalleVentaWeb(Http.Request request, Long id_ventaServicio) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/odo", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("odoVentas")==null || mapeoPermiso.get("parametro.mnuODO")==null || mapeoPermiso.get("parametro.mnuODO").equals("0")) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/odo",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			VentaServicio ventaServicio = VentaServicio.find(con, s.baseDato, id_ventaServicio);
			return ok(odoAutorizaDetalleVentaWeb.render(mapeoDiccionario,mapeoPermiso,userMnu,ventaServicio));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/odo", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/odo", msgReport));
		}
	}

	public Result odoAutorizaListarVentasWeb(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/odo", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("odoVentas")==null || mapeoPermiso.get("parametro.mnuODO")==null || mapeoPermiso.get("parametro.mnuODO").equals("0")) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/odo",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<VentaServicio> listVentas = VentaServicio.allPorBodegas(con, s.baseDato, mapeoPermiso, s.aplicaPorSucursal, s.id_sucursal, Long.parseLong(s.id_usuario));
			return ok(odoAutorizaListarVentasWeb.render(mapeoDiccionario,mapeoPermiso,userMnu,listVentas));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/odo", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/odo", msgReport));
		}
	}

	public Result odoAutorizaFirmaWeb(Http.Request request, Long id_ventaServicio) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/odo", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("odoVentas")==null || mapeoPermiso.get("parametro.mnuODO")==null || mapeoPermiso.get("parametro.mnuODO").equals("0")) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/odo",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			VentaServicio ventaServicio = VentaServicio.find(con, s.baseDato, id_ventaServicio);
			return ok(odoAutorizaFirmaWeb.render(mapeoDiccionario,mapeoPermiso,userMnu,ventaServicio));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/odo", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/odo", msgReport));
		}
	}

	public Result grabarAutorizaFirmaWeb(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/odo", msgError));
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/odo", msgErrorFormulario));
		}else {
			try (Connection con = dbWrite.getConnection()){
				Long id_ventaServicio = Long.parseLong(form.get("id_ventaServicio").trim());
				String firmaPDFaurorizador = form.get("firmaPDFautorizador").trim();
				VentaServicio ventaServicio = VentaServicio.find(con, s.baseDato, id_ventaServicio);
				VentaServicio.modificaPorCampo(con, s.baseDato, "firmaPDFautorizador", firmaPDFaurorizador, id_ventaServicio);
				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "ventaServicio", ventaServicio.getId(), "insert", "agrega o modifica firma autorizador report diario nro: "+ventaServicio.getId());
				//EXTRAE FIRMAS
				byte[] decodedStrOper = Base64.getDecoder().decode(ventaServicio.getFirmaPDFoperador());
				byte[] decodedStrAut = Base64.getDecoder().decode(firmaPDFaurorizador);
				//FIN DE FIRMA
				VentaServicio.generaPdfVentaReport(con, s.baseDato, ventaServicio, decodedStrOper, decodedStrAut);
				return ok(mensajes.render("/odoAutorizaListarVentasWeb/","FIRMA AUTORIZADOR GRABADA CORRECTAMENTE"));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/odo", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/odo", msgReport));
			}
		}
	}


	public static File exportaExcelOdoListarVentasWeb(String db, Map<String,String> mapDiccionario, List<VentaServicio> listVentas) {
		File tmp = TempFile.createTempFile("tmp","null");
		String className = "MnuOdoAppWeb.java";
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			String path = "formatos/excel.xlsx";
			InputStream formato = Archivos.leerArchivo(path);
			Workbook libro = WorkbookFactory.create(formato);
			formato.close();
			// 0 negro 1 blanco 2 rojo 3 verde 4 azul 5 amarillo 19 celeste
			CellStyle titulo = libro.createCellStyle();
			Font font = libro.createFont();
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			font.setColor((short)4);
			font.setFontHeight((short)(14*20));
			titulo.setFont(font);
			CellStyle subtitulo = libro.createCellStyle();
			Font font2 = libro.createFont();
			font2.setBoldweight(Font.BOLDWEIGHT_BOLD);
			font2.setColor((short)0);
			font2.setFontHeight((short)(12*20));
			subtitulo.setFont(font2);
			CellStyle encabezado = libro.createCellStyle();
			encabezado.setBorderBottom(CellStyle.BORDER_THIN);
			encabezado.setBorderTop(CellStyle.BORDER_THIN);
			encabezado.setBorderRight(CellStyle.BORDER_THIN);
			encabezado.setBorderLeft(CellStyle.BORDER_THIN);
			encabezado.setFillPattern(CellStyle.SOLID_FOREGROUND);
			encabezado.setFillForegroundColor((short)19);
			encabezado.setAlignment(CellStyle.ALIGN_LEFT);
			CellStyle detalle = libro.createCellStyle();
			detalle.setBorderBottom(CellStyle.BORDER_THIN);
			detalle.setBorderTop(CellStyle.BORDER_THIN);
			detalle.setBorderRight(CellStyle.BORDER_THIN);
			detalle.setBorderLeft(CellStyle.BORDER_THIN);
			CellStyle pie = libro.createCellStyle();
			pie.setBorderBottom(CellStyle.BORDER_THIN);
			pie.setBorderTop(CellStyle.BORDER_THIN);
			pie.setBorderRight(CellStyle.BORDER_THIN);
			pie.setBorderLeft(CellStyle.BORDER_THIN);
			pie.setFillPattern(CellStyle.SOLID_FOREGROUND);
			pie.setFillForegroundColor((short)19);
			pie.setAlignment(CellStyle.ALIGN_RIGHT);
			CreationHelper creationHelper = libro.getCreationHelper();
			CellStyle hora = libro.createCellStyle();
			hora.setDataFormat(creationHelper.createDataFormat().getFormat("hh:mm"));
			hora.setBorderBottom(CellStyle.BORDER_THIN);
			hora.setBorderTop(CellStyle.BORDER_THIN);
			hora.setBorderRight(CellStyle.BORDER_THIN);
			hora.setBorderLeft(CellStyle.BORDER_THIN);
			CellStyle fecha = libro.createCellStyle();
			fecha.setDataFormat(creationHelper.createDataFormat().getFormat("dd/MM/yyyy"));
			fecha.setBorderBottom(CellStyle.BORDER_THIN);
			fecha.setBorderTop(CellStyle.BORDER_THIN);
			fecha.setBorderRight(CellStyle.BORDER_THIN);
			fecha.setBorderLeft(CellStyle.BORDER_THIN);
			//titulos del archivo
			libro.setSheetName(0, "report");
			Sheet hoja1 = libro.getSheetAt(0);
			Row row = null;
			Cell cell = null;
			row = hoja1.createRow(1);
			cell = row.createCell(1);
			cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("LISTA DE REPORT DIARIO");
			row = hoja1.createRow(2);
			cell = row.createCell(1);
			cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("EMPRESA: "+mapDiccionario.get("nEmpresa"));
			row = hoja1.createRow(3);
			cell = row.createCell(1);
			cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FECHA: "+Fechas.hoy().getFechaStrDDMMAA());
			//anchos de columnas
			for(int i=1; i<30; i++) {
				if(i==1||i==7||i==11||i==12||i==13||i==14||i==15||i==16) {
					hoja1.setColumnWidth(i, 2*1000);
				}else if(i==2|| i==17||i==18) {
					hoja1.setColumnWidth(i, 3*1000);
				}else {
					hoja1.setColumnWidth(i, 6*1000);
				}
			}
			//INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
			InputStream x = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
			byte[] bytes = IOUtils.toByteArray(x);
			x.close();
			int pngIndex = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			Drawing draw = hoja1.createDrawingPatriarch();
			CreationHelper helper = libro.getCreationHelper();
			ClientAnchor anchor = helper.createClientAnchor();
			//set top-left corner for the image
			anchor.setCol1(9);
			anchor.setRow1(1);
			Picture img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);
			// encabezado de la tabla
			int posRow = 8;
			row = hoja1.createRow(posRow);
			int posCell = 0;
			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("ID");
			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FECHA");
			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("USER_MADA");
			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("OPERADOR");
			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(mapDiccionario.get("BODEGA"));
			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("COTI");
			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("CODIGO");
			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("SERVICIO");
			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("EQUIPO");
			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Hr_INI");
			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Hr_TER");
			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Lect_INI");
			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Lect_TER");
			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("UN");
			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("CANTIDAD");
			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("OPERADOR");
			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("AUTORIZADOR");
			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("DETALLE");
			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("NOTAS");
			for(VentaServicio venta: listVentas){
				posRow++;
				posCell = 0;
				row = hoja1.createRow(posRow);
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(venta.getId());
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(Fechas.AAMMDD(venta.getFecha()));
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(venta.getNomUserAdam());
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(venta.getNomOperador());
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(venta.getNomBodegaEmpresa());
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(venta.getNroCotiOdo());
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(venta.getCodServicio());
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(venta.getNomServicio());
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(venta.getNomEquipo());
				posCell++;
				cell = row.createCell(posCell);
				java.util.Date horaIni = new SimpleDateFormat("HH:mm").parse(venta.getHoraIni());
				cell.setCellValue(horaIni);
				cell.setCellStyle(hora);
				posCell++;
				cell = row.createCell(posCell);
				java.util.Date horaTer = new SimpleDateFormat("HH:mm").parse(venta.getHoraTer());
				cell.setCellValue(horaTer);
				cell.setCellStyle(hora);
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				Double aux = Double.parseDouble(venta.getLecturaIni().replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				aux = Double.parseDouble(venta.getLecturaTer().replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(venta.getUnidadServicio());
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				aux = Double.parseDouble(venta.getCantidad().replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				byte[] firmaOperador = Base64.getDecoder().decode(venta.getFirmaPDFoperador());
				int indexfo = libro.addPicture(firmaOperador, Workbook.PICTURE_TYPE_PNG);
				Drawing drawfo = hoja1.createDrawingPatriarch();
				CreationHelper helperfo = libro.getCreationHelper();
				ClientAnchor anchorfo = helperfo.createClientAnchor();
				anchorfo.setCol1(posCell);
				anchorfo.setRow1(posRow);
				anchorfo.setCol2(posCell+11);
				anchorfo.setRow2(posRow+1);
				Picture imgfo = drawfo.createPicture(anchorfo, indexfo);
				imgfo.resize(0.2);
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				byte[] firmaAurorizador = Base64.getDecoder().decode(venta.getFirmaPDFautorizador());
				int indexfa = libro.addPicture(firmaAurorizador, Workbook.PICTURE_TYPE_PNG);
				Drawing drawfa = hoja1.createDrawingPatriarch();
				CreationHelper helperfa = libro.getCreationHelper();
				ClientAnchor anchorfa = helperfa.createClientAnchor();
				anchorfa.setCol1(posCell);
				anchorfa.setRow1(posRow);
				anchorfa.setCol2(posCell+11);
				anchorfa.setRow2(posRow+1);
				Picture imgfa = drawfa.createPicture(anchorfa, indexfa);
				imgfa.resize(0.2);
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(venta.getDetalle());
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(venta.getObservaciones());
			}
			posRow = posRow + 5;
			row = hoja1.createRow(posRow);
			cell = row.createCell(1);
			Hyperlink hiper = helper.createHyperlink(0);
			hiper.setAddress("https://www.inqsol.cl");
			cell.setHyperlink(hiper);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Documento generado desde MADA propiedad de INQSOL");
			// Write the output to a file tmp
			FileOutputStream fileOut = new FileOutputStream(tmp);
			libro.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, "", "", e);
		}
		return tmp;
	}

	public Result reporteMovOdoAutorizador(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/odo", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("reporteMovOdo0")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/odo",msgSinPermiso));
		}
		try {
			Fechas hoy = Fechas.hoy();
			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			return ok(reporteMovOdoAutorizador.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, "", "", e);
			return ok(mensajes.render("/odo", msgReport));
		}
	}

	public Result reporteMovOdoAutorizadorDetalle(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/odo", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/odo", msgErrorFormulario));
		}else {
			try (Connection con = dbRead.getConnection()){
				String fechaDesde = form.get("fechaDesde").trim();
				String fechaHasta = form.get("fechaHasta").trim();
				Long id_bodegaEmpresa = UsuarioPermiso.permisoSoloABodega(con, s.baseDato, s.id_usuario);
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				List<List<String>> datos = ReportOdoMovimientos.movimientoOdoCantidad(con, s.baseDato, id_bodegaEmpresa, fechaDesde, fechaHasta);
				BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
				return ok(reporteMovOdoAutorizadorDetalle.render(mapeoDiccionario,mapeoPermiso,userMnu,datos,bodega,fechaDesde,fechaHasta));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/odo", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/odo", msgReport));
			}
		}
	}
		
}
