package controllers;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

import controllers.HomeController.Sessiones;
import models.calculo.Inventarios;
import models.contratos.GeneraPDF_ContratoOdo;
import models.forms.CotiJsonA;
import models.forms.FormContratoOdo;
import models.forms.FormCotizaOdo;
import models.forms.FormOtOdo;
import models.reports.ReportCotiOdo;
import models.tables.*;
import models.utilities.*;
import models.xlsx.CotiOdoEnExcel;
import models.xlsx.OtOdoEnExcel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.Database;
import play.libs.Files.TemporaryFile;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.mensajes;
import viewsMnuCotiOdo.html.*;

public class MnuCotiOdo extends Controller {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private static final Database dbWrite = HomeController.dbWrite;
	private static final DatabaseRead dbRead = HomeController.dbRead;
	public static Database db = HomeController.dbWrite;
	public static FormFactory formFactory = HomeController.formFactory;
	public static String msgError = HomeController.msgError;
	public static String msgErrorFormulario = HomeController.msgErrorFormulario;
	public static String msgSinPermiso = HomeController.msgSinPermiso;
	private static final String msgReport = HomeController.msgReport;

	static DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00",symbols);
	static DecimalFormat myformatdoubleCompra = new DecimalFormat("#,##0.00",symbols);
	
	
	
	
	//==============================================================
    // MNU cotiOdoIngreso   CotiOdo/Cotizaciones Odo/Hacer Coti Odo
    //==============================================================

	public Result cotiOdoIngreso(Http.Request request) {

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
		if(mapeoPermiso.get("cotiOdoIngreso")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			Long numCotiOdo  = CotiOdo.findNuevoNumeroOdo(con, s.baseDato);
			String fecha = Fechas.hoy().getFechaStrAAMMDD();
			FormCotizaOdo formCotizaOdo = new FormCotizaOdo();
			formCotizaOdo = new FormCotizaOdo(numCotiOdo, fecha);
			formCotizaOdo.id_cotiOdo = (long)0;
			List<Servicio> listServicios = Servicio.all(con, s.baseDato);
			List<Cliente> listClientes = Cliente.allsoloVigentes(con, s.baseDato);
			List<Proyecto> listProyectos = Proyecto.all(con, s.baseDato);
			List<Regiones> listRegiones = Regiones.all(con, s.baseDato);
			List<CotiOdo> listaCotiOdo = CotiOdo.all(con, s.baseDato);
			Map<Long,Cliente> mapCliente = Cliente.mapAllClientes(con, s.baseDato);
			Map<Long,Proyecto> mapProyecto = Proyecto.mapAllProyectos(con, s.baseDato);
			List<List<String>> listCotiOdo = new ArrayList<List<String>>();
			listaCotiOdo.forEach(x->{
				String nomCliente = "";
				String nomProyecto = "";
				Cliente cliente = mapCliente.get(x.getId_cliente());
				Proyecto proyecto = mapProyecto.get(x.getId_proyecto());
				if(cliente!=null) {
					nomCliente = cliente.getNickName();
				}
				if(proyecto!=null) {
					nomProyecto = proyecto.getNickName();
				}
				List<String> aux = new ArrayList<String>();
				aux.add(x.getId().toString());
				aux.add(x.getNumero().toString());
				aux.add(x.getFecha());
				aux.add(nomCliente);
				aux.add(nomProyecto);
				listCotiOdo.add(aux);
			});
			List<List<String>> listadoServicios = new ArrayList<List<String>>();
			Map<Long,Moneda> mapMoneda = Moneda.mapMonedas(con, s.baseDato);
			Long numDec = (long) 0;
			for(Servicio x: listServicios) {
				Moneda moneda = mapMoneda.get(x.getId_moneda());
				if(moneda!=null) {
					numDec = moneda.getNumeroDecimales();
				}else {
					numDec = (long) 0;
				}
				List<String> aux = new ArrayList<String>();
				aux.add(x.getId().toString());		//0 id_servicio
				aux.add(x.getNombreClase());		//1 clase
				aux.add(x.getCodigo());				//2 codigo
				aux.add(x.getNombre());				//3 nombre servicio
				aux.add(x.getNombreUnidad());		//4 unidad
				aux.add(DecimalFormato.formato((double)0,(long)2));		//5 cantidad
				aux.add(x.getNickMoneda());								//6 moneda
				aux.add(DecimalFormato.formato(x.getPrecio(),numDec));	//7 precio
				aux.add(DecimalFormato.formato((double)0,numDec));		//8 total
				aux.add("0");											//9 aplicaMinimo
				aux.add(DecimalFormato.formato((double)0,(long)2));		//10 cantidadMinimo
				aux.add(DecimalFormato.formato((double)0,numDec));		//11 precio adicional
				aux.add(numDec.toString());								//12 num decimales
				aux.add(x.getId_moneda().toString());					//13 id_moneda
				listadoServicios.add(aux);
			}

			List<Equipo> auxlistEquipos = Equipo.allVigentes(con, s.baseDato);
			Map<Long,Double> mapEquipConStock = Inventarios.mapEquiposConStock(con, s.baseDato,"ARRIENDO",mapeoDiccionario);
			List<Equipo> listEquipos = new ArrayList<Equipo>();
			for(Equipo x: auxlistEquipos) {
				Double stock = mapEquipConStock.get(x.getId());
				if(stock!=null && stock.doubleValue() > 0) {
					listEquipos.add(x);
				}
			}

			return ok(cotiOdoIngreso.render(mapeoDiccionario,mapeoPermiso,userMnu,listClientes,listProyectos,listRegiones, formCotizaOdo, listadoServicios, listCotiOdo, numDec, listEquipos));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result existeNumeroCotiOdoAjax(Http.Request request) {
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
			try (Connection con = dbRead.getConnection()){
				Long numeroCoti = Long.parseLong(form.get("numeroCoti").trim());
				if(CotiOdo.existeNumero(con, s.baseDato, numeroCoti)) {
					return ok("El número "+numeroCoti+" de cotizacion odo ya existe, intente con otro número");
				}else {
					return ok("OK");
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
    
    
    public Result cotiOdoNuevo(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		FormCotizaOdo form = formFactory.form(FormCotizaOdo.class).withDirectFieldAccess(true).bindFromRequest(request).get();
		if (form.numeroCoti==null || form.id_cliente==null) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgErrorFormulario));
		}else {
			Http.MultipartFormData<TemporaryFile> body = request.body().asMultipartFormData();
			Http.MultipartFormData.FilePart<TemporaryFile> docAdjunto = body.getFile("docAdjunto");
			try (Connection con = dbWrite.getConnection()) {
				String msg = "0";
				if (CotiOdo.existeNumero(con, s.baseDato, form.numeroCoti)) {
					Long nuevoNumero = CotiOdo.findNuevoNumeroOdo(con, s.baseDato);
					msg = "El número de cotizacion ya existe, se asigna nuevo número:" + nuevoNumero + ", anote el número y si desea cambiarlo vaya a modificar cotización";
					form.numeroCoti = nuevoNumero;
				}
				if (FormCotizaOdo.create(con, s.baseDato, form, docAdjunto)) {
					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "cotiOdo", (long) 0, "create", "ingresa nueva cotizacion Odo nro: " + form.numeroCoti);
				}
				if (msg.equals("0")) {
					return redirect("/home/");
				} else {
					return ok(mensajes.render("/home/", msg));
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
    
    public Result cotiOdoModificaJson(Http.Request request) {
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
			try (Connection con = dbRead.getConnection()){
				Long id_cotiOdo = Long.parseLong(form.get("id_cotiOdo").trim());
				List<CotiOdoDetalle> detalleCoti = CotiOdoDetalle.allPorIdCotiOdo(con, s.baseDato,id_cotiOdo);
				List<CotiJsonA> prueba = new ArrayList<CotiJsonA>();
				Map<Long,Long> mapDec = Moneda.numeroDecimal(con, s.baseDato);
				for(int k=0; k<detalleCoti.size(); k++) {
					Long numDec = mapDec.get(detalleCoti.get(k).getId_moneda());
					if(numDec == null) {
						numDec = (long)0;
					}
					CotiJsonA aux = new CotiJsonA();
					aux.id_servicio = detalleCoti.get(k).getId_servicio().toString();
					aux.cantidad = detalleCoti.get(k).getCantidad();
					aux.precio = detalleCoti.get(k).getPrecio();
					aux.aplicaMinimo = detalleCoti.get(k).getAplicaMinimo().toString();
					aux.cantidadMinimo = detalleCoti.get(k).getCantidadMinimo();
					aux.precioAdicional = detalleCoti.get(k).getPrecioAdicional();
					aux.nroDecimal = numDec.toString();
					prueba.add(aux);
				}
				return ok(Json.toJson(prueba).toString());
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
    	}
	}
    
    //==================================================================
    // MNU cotiOdoModifica   CotiOdo/Cotizaciones Odo/Modificar Coti Odo
    //==================================================================
    
    public Result cotiOdoListaModifica(Http.Request request) {
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
		if(mapeoPermiso.get("cotiOdoModifica")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<CotiOdo> listCotiOdo = CotiOdo.allSinConfirmar(con, s.baseDato);
			Map<Long,Cliente> mapCliente = Cliente.mapAllClientes(con, s.baseDato);
			Map<Long,Proyecto> mapProyecto = Proyecto.mapAllProyectos(con, s.baseDato);
			List<List<String>> lista = new ArrayList<List<String>>();
			listCotiOdo.forEach(x->{
				Cliente cliente = mapCliente.get(x.getId_cliente());
				String nomCliente = "";
				if(cliente!=null && cliente.getVigente() == (long)1) {
					nomCliente = cliente.nickName;
					Proyecto proyecto = mapProyecto.get(x.getId_proyecto());
					String nomProyecto = "";
					if(proyecto!=null) {
						nomProyecto = proyecto.nickName;
					}
					List<String> aux = new ArrayList<String>();
					aux.add(x.getId().toString());			// 0 id_cotiOdo
					aux.add(x.getNumero().toString());		// 1 numero de cotizacion
					aux.add(Fechas.AAMMDD(x.getFecha()));	// 2 fecha
					aux.add(nomCliente);					// 3 nombre de cliente
					aux.add(x.getObservaciones());			// 4 observaciones
					aux.add(x.getCotiOdoPDF());				// 5 doc adjunto
					aux.add(nomProyecto);					// 6 nombre de proyecto
					lista.add(aux);
				}
			});
			return ok(cotiOdoListaModifica.render(mapeoDiccionario,mapeoPermiso,userMnu,lista));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result cotiOdoElimina(Http.Request request) {
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
			if(mapeoPermiso.get("cotiOdoModifica")==null) {
				logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
				return ok(mensajes.render("/",msgSinPermiso));
			}
			try (Connection con = dbWrite.getConnection()){
				Long id_cotiOdo = Long.parseLong(form.get("id_cotiOdo").trim());
				CotiOdo coti = CotiOdo.find(con, s.baseDato, id_cotiOdo);
				if(CotiOdo.delete(con, s.baseDato, id_cotiOdo)) {
					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "cotiOdo", id_cotiOdo, "delete", "elimina una cotizacion odo nro: "+coti.getNumero());
					con.close();
					return redirect("/routes2/cotiOdoListaModifica/");
				}else {
					con.close();
					return ok(mensajes.render("/home/","No fue posible eliminar la cotización"));
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
    
    public Result cotiOdoModifica(Http.Request request) {
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
			return ok("error");
		}else {
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
			if(mapeoPermiso.get("cotiOdoModifica")==null) {
				logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
				return ok(mensajes.render("/",msgSinPermiso));
			}
			try (Connection con = dbRead.getConnection()){
				Long id_cotiOdo = Long.parseLong(form.get("id_cotiOdo").trim());
				CotiOdo cotiOdo = CotiOdo.find(con, s.baseDato, id_cotiOdo);
				String fecha = Fechas.AAMMDD(cotiOdo.getFecha());
				Cliente cliente = Cliente.find(con, s.baseDato, cotiOdo.getId_cliente());
				String rutCliente = "";
				String nomCliente = "";
				if(cliente!=null) {
					rutCliente = cliente.getRut();
					nomCliente = cliente.getNickName();
				}
				Proyecto proyecto = Proyecto.find(con, s.baseDato, cotiOdo.getId_proyecto());
				String nomProyecto = "";
				if(proyecto!=null) {
					nomProyecto = proyecto.getNickName();
				}
				FormCotizaOdo formCotizaOdo = new FormCotizaOdo(cotiOdo, fecha, rutCliente, nomCliente, nomProyecto);
				formCotizaOdo.id_cotiOdo = id_cotiOdo;
				formCotizaOdo.dctoOdo = DecimalFormato.formato(cotiOdo.getDctoOdo()*100, (long)2);
				List<List<String>> listadoServicios = FormCotizaOdo.listServiciosConValoresCoti(con, s.baseDato, id_cotiOdo);
				List<Cliente> listClientes = Cliente.allsoloVigentes(con, s.baseDato);
				List<Proyecto> listProyectos = Proyecto.all(con, s.baseDato);
				List<Regiones> listRegiones = Regiones.all(con, s.baseDato);
				Long numDec = (long) 0;
				if(listadoServicios.size()>0) {
					String auxNumDec = listadoServicios.get(listadoServicios.size()-1).get(12);
					numDec = Long.parseLong(auxNumDec);
				}

				List<Equipo> auxlistEquipos = Equipo.allVigentes(con, s.baseDato);
				Map<Long,Double> mapEquipConStock = Inventarios.mapEquiposConStock(con, s.baseDato,"ARRIENDO",mapeoDiccionario);
				List<Equipo> listEquipos = new ArrayList<Equipo>();
				for(Equipo x: auxlistEquipos) {
					Double stock = mapEquipConStock.get(x.getId());
					if(stock!=null && stock.doubleValue() > 0) {
						listEquipos.add(x);
					}
				}

				return ok(cotiOdoModifica.render(mapeoDiccionario,mapeoPermiso,userMnu,listClientes,listProyectos,listRegiones, formCotizaOdo, listadoServicios, numDec, listEquipos));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
    	}
    }
    
    public Result cotiOdoUpdate(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		FormCotizaOdo form = formFactory.form(FormCotizaOdo.class).withDirectFieldAccess(true).bindFromRequest(request).get();
		if (form.numeroCoti==null || form.id_cliente==null) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgErrorFormulario));
		}else {
			Http.MultipartFormData<TemporaryFile> body = request.body().asMultipartFormData();
			Http.MultipartFormData.FilePart<TemporaryFile> docAdjunto = body.getFile("docAdjunto");
			try (Connection con = dbWrite.getConnection()) {
				if (FormCotizaOdo.update(con, s.baseDato, form, docAdjunto)) {
					CotiOdo cotiOdo = CotiOdo.find(con, s.baseDato, form.id_cotiOdo);
					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "cotiOdo", form.id_cotiOdo, "update", "modifica cotizacion Odo nro: " + cotiOdo.getNumero());
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
    
    public Result verCotiOdoAjax(Http.Request request) {
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
			try (Connection con = dbRead.getConnection()){
				Long id_cotiOdo = Long.parseLong(form.get("id_cotiOdo").trim());
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				String vista = CotiOdo.vistaModalVerCotiOdo(con, s.baseDato, id_cotiOdo, mapeoDiccionario);
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
    
    
    //=========================================================================
    // MNU cotiOdoCambiarEstado   CotiOdo/Cotizaciones/Cambiar Estado Coti Odo
    //=========================================================================
    
    public Result cotiOdoListaCambiaEstado(Http.Request request) {
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
		if(mapeoPermiso.get("cotiOdoCambiarEstado")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<CotiOdo> listCoti = CotiOdo.allParaCambiarEstado(con, s.baseDato);
			Map<Long,Cliente> mapCliente = Cliente.mapAllClientes(con, s.baseDato);
			Map<Long,Proyecto> mapProyecto = Proyecto.mapAllProyectos(con, s.baseDato);
			Map<Long,CotizaEstado> mapEstado = CotizaEstado.mapAll(con, s.baseDato);
			List<List<String>> lista = new ArrayList<List<String>>();
			listCoti.forEach(x->{
				Cliente cliente = mapCliente.get(x.getId_cliente());
				String nomCliente = "";
				if(cliente!=null) {
					nomCliente = cliente.nickName;
					Proyecto proyecto = mapProyecto.get(x.getId_proyecto());
					String nomProyecto = "";
					if(proyecto!=null) {
						nomProyecto = proyecto.nickName;
					}
					CotizaEstado estado = mapEstado.get(x.getId_cotizaEstado());
					String nomEstado = "";
					if(estado!=null) {
						nomEstado = estado.getEstado();
					}
					List<String> aux = new ArrayList<String>();
					aux.add(x.getId().toString());				// 0 id_cotizacion
					aux.add(x.getNumero().toString());			// 1 numero de cotizacion
					aux.add(Fechas.AAMMDD(x.getFecha()));		// 2 fecha
					aux.add(nomCliente);						// 3 nombre de cliente
					aux.add(x.getObservaciones());				// 4 observaciones
					aux.add(x.getCotiOdoPDF());				// 5 doc adjunto
					aux.add(nomProyecto);						// 6 nombre de proyecto
					aux.add(x.getId_cotizaEstado().toString());	// 7 id_cotizaEstado
					aux.add(nomEstado);							// 8 nombre de estado
					lista.add(aux);
				}
			});
			List<CotizaEstado> listEstados = CotizaEstado.all(con, s.baseDato);
			return ok(cotiOdoListaCambiaEstado.render(mapeoDiccionario,mapeoPermiso,userMnu,lista, listEstados));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result cambiarCotiOdoEstadoAjax(Http.Request request) {
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
				Long id_cotiOdo = Long.parseLong(form.get("id_cotiOdo").trim());
				String id_cotizaEstado = form.get("id_cotizaEstado").trim();
				CotiOdo.modifyXCampo(con, s.baseDato, "id_cotizaEstado", id_cotizaEstado, id_cotiOdo);
				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "cotiOdo", id_cotiOdo, "update", "cambia el estado de cotiOdo id: "+id_cotiOdo.toString());
				return ok("OK");
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
    // MNU cotiOdoImprime   CotiOdo/Cotizaciones/Imprimir Coti Odo
    //============================================================
    
    public Result cotiOdoListaImprimir(Http.Request request) {
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
		if(mapeoPermiso.get("cotiOdoImprime")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<CotiOdo> listCotiOdo = CotiOdo.listCotiAllParaImprimir(con, s.baseDato);
			Map<Long,Cliente> mapCliente = Cliente.mapAllClientes(con, s.baseDato);
			Map<Long,Proyecto> mapProyecto = Proyecto.mapAllProyectos(con, s.baseDato);
			Map<Long,CotizaEstado> mapEstado = CotizaEstado.mapAll(con, s.baseDato);
			List<List<String>> lista = new ArrayList<List<String>>();
			listCotiOdo.forEach(x->{
				Cliente cliente = mapCliente.get(x.getId_cliente());
				String nomCliente = "";
				if(cliente!=null) {
					nomCliente = cliente.nickName;
				}
				Proyecto proyecto = mapProyecto.get(x.getId_proyecto());
				String nomProyecto = "";
				if(proyecto!=null) {
					nomProyecto = proyecto.nickName;
				}
				CotizaEstado estado = mapEstado.get(x.getId_cotizaEstado());
				String nomEstado = "";
				if(estado!=null) {
					nomEstado = estado.getEstado();
				}
				List<String> aux = new ArrayList<String>();
				aux.add(x.getId().toString());			// 0 id_cotiOdo
				aux.add(x.getNumero().toString());		// 1 numero de cotizacion
				aux.add(Fechas.AAMMDD(x.getFecha()));	// 2 fecha
				aux.add(nomCliente);					// 3 nombre de cliente
				aux.add(x.getObservaciones());			// 4 observaciones
				aux.add(x.getCotiOdoPDF());				// 5 doc adjunto
				aux.add(nomProyecto);					// 6 nombre de proyecto
				aux.add(x.getId_cotizaEstado().toString());	// 7 id_cotizaEstado
				aux.add(nomEstado);							// 8 nombre de estado
				aux.add(Fechas.AAMMDD(x.getFechaConfirmada()));			// 9 fecha de confirmada
				aux.add(x.getPdfCotiVtaOdo());				// 10 pdf venta generado
				lista.add(aux);
			});
			return ok(cotiOdoListaImprimir.render(mapeoDiccionario,mapeoPermiso,userMnu,lista));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result cotiOdoImprimir(Http.Request request, Long id_cotiOdo) {
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
		if(mapeoPermiso.get("cotiOdoImprime")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			String tabla = CotiOdo.vistaModalVerCotiOdo(con, s.baseDato, id_cotiOdo, mapeoDiccionario);
			return ok(cotiOdoImprimir.render(mapeoDiccionario,mapeoPermiso,userMnu,id_cotiOdo, tabla));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result cotiOdoExcel(Http.Request request) {
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
			try (Connection con = dbRead.getConnection()){
				Long id_cotiOdo = Long.parseLong(form.get("id_cotiOdo").trim());
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				File file = CotiOdoEnExcel.cotiOdoEnExcel(con, s.baseDato, id_cotiOdo, mapeoDiccionario);
				return ok(file,false,Optional.of("CotizacionOdo.xlsx"));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
    	}
	}
    
    public Result pdfCotiOdoVta(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgErrorFormulario));
		}else {
			try (Connection con = dbWrite.getConnection()){
				Long id_cotiOdo = Long.parseLong(form.get("id_cotiOdo").trim());
				String fileNamePdf = FormCotizaOdo.generaPdfCotiOdoVenta(con, s.baseDato, id_cotiOdo, mapeoDiccionario, mapeoPermiso);
				String titulo = "COTIZACION DE VENTA ODO";
				String url = "%2Fhome%2F";
				return redirect("/routes2/redirShowPDF/"+fileNamePdf+","+titulo+","+url);
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
    	}
	}
    
    
    
    //===================================================================
    // MNU cotiOdoImprime   CotiOdo/Cotizaciones/Reporte Movil Coti Odo
    //============================================================================
	
	public Result reportCotiOdoSel(Http.Request request) {
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
		if(mapeoPermiso.get("cotizaImprime")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try {
			Fechas hoy = Fechas.hoy();
			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			return ok(reportCotiOdoSel.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}
	
	public Result reportCotiOdoRpt(Http.Request request) {
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
			try (Connection con = dbRead.getConnection()){
				String desdeAAMMDD = form.get("fechaDesde").trim();
				String hastaAAMMDD = form.get("fechaHasta").trim();
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				DecimalFormat myformaNumber = new DecimalFormat("0",symbols);
				Map<String,Double> map = ReportCotiOdo.calculoCotiOdo(con, s.baseDato, desdeAAMMDD, hastaAAMMDD,  mapeoDiccionario.get("pais"));
				Double val_Confirmadas = map.get("val_Confirmadas");
				if(val_Confirmadas == null) val_Confirmadas = (double)0;
				Double val_NoConfirmadas = map.get("val_NoConfirmadas");
				if(val_NoConfirmadas == null) val_NoConfirmadas = (double)0;
				Double cant_Confirmadas = map.get("cant_Confirmadas");
				if(cant_Confirmadas == null) cant_Confirmadas = (double)0;
				Double cant_NoConfirmadas = map.get("cant_NoConfirmadas");
				if(cant_NoConfirmadas == null) cant_NoConfirmadas = (double)0;
				String grafValConf = "[['CONFIRMADAS',"+myformaNumber.format(val_Confirmadas)+"],['NO CONFIRMADAS',"+myformaNumber.format(val_NoConfirmadas)+"]]";
				String grafCantConf = "[['CONFIRMADAS',"+cant_Confirmadas+"],['NO CONFIRMADAS',"+cant_NoConfirmadas+"]]";
				List<CotizaEstado> listCotizaEstado = CotizaEstado.all(con, s.baseDato);
				String grafValNoConf = "[";
				String grafCantNoConf = "[";
				for(CotizaEstado x: listCotizaEstado) {
					Double val = map.get("val_"+x.estado);
					if(val!=null) {
						grafValNoConf += "['"+x.estado+"',"+myformaNumber.format(val)+"],";
					}
					Double cant = map.get("cant_"+x.estado);
					if(cant!=null) {
						grafCantNoConf += "['"+x.estado+"',"+myformaNumber.format(cant)+"],";
					}
				}
				if(grafValNoConf.length()>2) {
					grafValNoConf = grafValNoConf.substring(0, grafValNoConf.lastIndexOf(",")) + "]";
				}else {
					grafValNoConf = "[]";
				}
				if(grafCantNoConf.length()>2) {
					grafCantNoConf = grafCantNoConf.substring(0, grafCantNoConf.lastIndexOf(",")) + "]";
				}else {
					grafCantNoConf = "[]";
				}
				String fechaDe = Fechas.DDMMAA(desdeAAMMDD);
				String fechaA = Fechas.DDMMAA(hastaAAMMDD);
				return ok(reportCotiOdoRpt.render(mapeoDiccionario,mapeoPermiso,userMnu, grafValConf, grafCantConf, grafValNoConf, grafCantNoConf, fechaDe, fechaA));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
    	}
	}
    
    //===============================================================
    // MNU cotiOdoConfirma   CotiOdo/Cotizaciones/Confirma Coti Odo
    //===============================================================
    
    public Result cotiOdoListaConfirma(Http.Request request) {
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
		if(mapeoPermiso.get("cotiOdoConfirma")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<CotiOdo> listCoti = CotiOdo.allSinConfirmar(con, s.baseDato);
			Map<Long,Cliente> mapCliente = Cliente.mapAllClientes(con, s.baseDato);
			Map<Long,Proyecto> mapProyecto = Proyecto.mapAllProyectos(con, s.baseDato);
			Map<Long,CotizaEstado> mapEstado = CotizaEstado.mapAll(con, s.baseDato);
			List<List<String>> lista = new ArrayList<List<String>>();
			listCoti.forEach(x->{
				Cliente cliente = mapCliente.get(x.getId_cliente());
				String nomCliente = "";
				if(cliente!=null && cliente.getVigente() == (long)1) {
					nomCliente = cliente.nickName;
					Proyecto proyecto = mapProyecto.get(x.getId_proyecto());
					String nomProyecto = "";
					if(proyecto!=null) {
						nomProyecto = proyecto.nickName;
					}
					CotizaEstado estado = mapEstado.get(x.getId_cotizaEstado());
					String nomEstado = "";
					if(estado!=null) {
						nomEstado = estado.getEstado();
					}
					List<String> aux = new ArrayList<String>();
					aux.add(x.getId().toString());				// 0 id_cotiOdo
					aux.add(x.getNumero().toString());			// 1 numero de cotizacion
					aux.add(Fechas.AAMMDD(x.getFecha()));		// 2 fecha
					aux.add(nomCliente);						// 3 nombre de cliente
					aux.add(x.getObservaciones());				// 4 observaciones
					aux.add(x.getCotiOdoPDF());					// 5 doc adjunto
					aux.add(nomProyecto);						// 6 nombre de proyecto
					aux.add(x.getId_cotizaEstado().toString());	// 7 id_cotizaEstado
					aux.add(nomEstado);							// 8 nombre de estado
					aux.add(x.getFechaConfirmada());			// 9 fecha de confirmada
					aux.add(x.getPdfCotiVtaOdo());				// 10 pdf venta generado
					aux.add(x.dateCreate.toString());			// 11 pdf fecha y hora generado
					lista.add(aux);
				}
			});
			return ok(cotiOdoListaConfirma.render(mapeoDiccionario,mapeoPermiso,userMnu,lista));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result cotiOdoConfirma(Http.Request request) {
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
			try (Connection con = dbWrite.getConnection()){
				String listadoIdCotiConfirmar = form.get("cambiosDeEstados").trim();
				if(listadoIdCotiConfirmar.length()>1) {
					listadoIdCotiConfirmar = "("+listadoIdCotiConfirmar.substring(0,listadoIdCotiConfirmar.length()-1)+")";
					CotiOdo.confirmar(con, s.baseDato, listadoIdCotiConfirmar);
					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "cotiOdo", (long)0, "confirma", "confirma coti odo id: "+listadoIdCotiConfirmar);
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
    
    //==================================================================
    // MNU otOdoIngreso   CotiOdo/Ordenes de trabajo/Hacer OT u OS Odo
    //==================================================================
    
    public Result otOdoListaCotizaSinOt(Http.Request request) {
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
		if(mapeoPermiso.get("otOdoIngreso")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<CotiOdo> listCoti = CotiOdo.allConfirmadasSinOt(con, s.baseDato);
			Map<Long,Cliente> mapCliente = Cliente.mapAllClientes(con, s.baseDato);
			Map<Long,Proyecto> mapProyecto = Proyecto.mapAllProyectos(con, s.baseDato);
			Map<Long,CotizaEstado> mapEstado = CotizaEstado.mapAll(con, s.baseDato);
			List<List<String>> lista = new ArrayList<List<String>>();
			listCoti.forEach(x->{
				Cliente cliente = mapCliente.get(x.getId_cliente());
				String nomCliente = "";
				if(cliente!=null && cliente.getVigente() == (long)1) {
					nomCliente = cliente.nickName;
					Proyecto proyecto = mapProyecto.get(x.getId_proyecto());
					String nomProyecto = "";
					if(proyecto!=null) {
						nomProyecto = proyecto.nickName;
					}
					CotizaEstado estado = mapEstado.get(x.getId_cotizaEstado());
					String nomEstado = "";
					if(estado!=null) {
						nomEstado = estado.getEstado();
					}
					List<String> aux = new ArrayList<String>();
					aux.add(x.getId().toString());				// 0 id_cotizacion
					aux.add(x.getNumero().toString());			// 1 numero de cotizacion
					aux.add(Fechas.AAMMDD(x.getFecha()));		// 2 fecha
					aux.add(nomCliente);						// 3 nombre de cliente
					aux.add(x.getObservaciones());				// 4 observaciones
					aux.add(x.getCotiOdoPDF());					// 5 doc adjunto
					aux.add(nomProyecto);						// 6 nombre de proyecto
					aux.add(x.getId_cotizaEstado().toString());	// 7 id_cotizaEstado
					aux.add(nomEstado);							// 8 nombre de estado
					aux.add(x.getFechaConfirmada());			// 9 fecha de confirmada
					aux.add(x.getPdfCotiVtaOdo());				// 10 pdf arriendo generado
					aux.add(x.dateCreate.toString());			// 11 pdf venta generado
					lista.add(aux);
				}
			});
			return ok(otOdoListaCotizaSinOt.render(mapeoDiccionario,mapeoPermiso,userMnu,lista));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
	public Result otOdoHacer(Http.Request request) {
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
		if(mapeoPermiso.get("otOdoIngreso")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			return ok(mensajes.render("/",msgErrorFormulario));
		}else {
			try (Connection con = dbRead.getConnection()){
				Long id_cotiOdo = Long.parseLong(form.get("id_cotiOdo").trim());
				CotiOdo cotiOdo = CotiOdo.find(con, s.baseDato, id_cotiOdo);
				Long numeroOtOdo = OtOdo.findNuevoNumero(con, s.baseDato);
				String fecha = Fechas.hoy().getFechaStrAAMMDD();
				BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, cotiOdo.getId_bodegaEmpresa());
				Proyecto proyecto = Proyecto.find(con, s.baseDato, cotiOdo.id_proyecto);
				String nickProyecto = "";
				Long id_proyecto = (long)0;
				if(proyecto != null) {
					nickProyecto = proyecto.nickName;
					id_proyecto = proyecto.id;
				}
				FormOtOdo formOtOdo = new FormOtOdo();
				if(bodega != null) {
					formOtOdo = new FormOtOdo((long)0, cotiOdo.getId(), cotiOdo.getId_bodegaEmpresa(), numeroOtOdo, fecha, "", bodega.nombre, id_proyecto, nickProyecto);
				} else {
					formOtOdo = new FormOtOdo((long)0, cotiOdo.getId(), cotiOdo.getId_bodegaEmpresa(), numeroOtOdo, fecha, "", id_proyecto, nickProyecto, (long)0, "");
				}
				List<List<String>> listBodegas = BodegaEmpresa.listaAllBodegasVigentesExternas(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
				List<Proyecto> listProyectos = Proyecto.all(con, s.baseDato);

				List<Equipo> auxlistEquipos = Equipo.allVigentes(con, s.baseDato);
				Map<Long,Double> mapEquipConStock = Inventarios.mapEquiposConStock(con, s.baseDato,"ARRIENDO",mapeoDiccionario);
				List<Equipo> listEquipos = new ArrayList<Equipo>();
				for(Equipo x: auxlistEquipos) {
					Double stock = mapEquipConStock.get(x.getId());
					if(stock!=null && stock.doubleValue() > 0) {
						listEquipos.add(x);
					}
				}


				String tabla = CotiOdo.vistaHaceOT(con, s.baseDato, id_cotiOdo, mapeoDiccionario, listEquipos);
				List<Regiones> listRegiones = Regiones.all(con, s.baseDato);
				List<OperadorServicio> listOperadoresServicio = OperadorServicio.all(con, s.baseDato);



				return ok(otOdoHacer.render(mapeoDiccionario,mapeoPermiso,userMnu,formOtOdo,listBodegas,tabla,listProyectos,listRegiones,listOperadoresServicio,listEquipos));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
    	}
    }


	public Result cotiOdoModifyXCampo(Http.Request request) {
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
		}else {
			String campo = form.get("campo").trim();
			String valor = form.get("valor").trim();
			String id_cotiOdoDetalle = form.get("id_cotiOdoDetalle").trim();
			try (Connection con = dbWrite.getConnection()){
				if(CotiOdoDetalle.modifyXCampo(con,s.baseDato,campo,valor,id_cotiOdoDetalle)){
					return ok("OK");
				}else{
					return ok("error");
				}
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			}
		}
		return ok("error");
	}
	
	public Result otOdoGrabar(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		FormOtOdo form = formFactory.form(FormOtOdo.class).withDirectFieldAccess(true).bindFromRequest(request).get();
		if (form.numeroOt==null || form.id_otOdo==null) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgErrorFormulario));
		}else {
			Http.MultipartFormData<TemporaryFile> body = request.body().asMultipartFormData();
			Http.MultipartFormData.FilePart<TemporaryFile> docAdjunto = body.getFile("docAdjunto");
			try (Connection con = dbWrite.getConnection()){
				String msg = "0";
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				if(OtOdo.existeIdOdoCoti(con, s.baseDato, form.id_cotiOdo)) {
					msg = "La cotización ya fue convertida a ordén de trabajo o servicio, no es posible repetir";
					con.close();
					return ok(mensajes.render("/home/",msg));
				}
				if(OtOdo.existeNumero(con, s.baseDato, form.numeroOt)) {
					Long nuevoNumero = OtOdo.findNuevoNumero(con, s.baseDato);
					msg = "El numero de "+mapeoDiccionario.get("OT")+" ya existe, se asigna nuevo numero:" + nuevoNumero;
					form.numeroOt = nuevoNumero;
				}
				if(FormOtOdo.create(con, s.baseDato, form, docAdjunto)) {
					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "otOdo", (long)0, "create", "crea nueva ot odo nro: "+form.numeroOt);
				}
				if(msg.equals("0")) {
					return redirect("/home/");
				}else {
					return ok(mensajes.render("/home/",msg));
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
	
	//=======================================================================
    // MNU otOdoConfirma   CotiOdo/Ordenes de trabajo/Confirma OT u OS ODO
    //======================================================================
    
    public Result otOdoListaConfirma(Http.Request request) {
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
		if(mapeoPermiso.get("otOdoConfirma")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<OtOdo> listOtOdo = OtOdo.allxConfirmar(con, s.baseDato);
			Map<Long,BodegaEmpresa> mapBodegas = BodegaEmpresa.mapAll(con, s.baseDato);
			Map<Long,CotiOdo> mapCotiOdo = CotiOdo.mapAllConOt(con, s.baseDato);
			Map<Long,Cliente> mapClientes = Cliente.mapAllClientes(con, s.baseDato);
			List<List<String>> lista = new ArrayList<List<String>>();
			listOtOdo.forEach(x->{
				CotiOdo cotiOdo = mapCotiOdo.get(x.getId_cotiOdo());
				String numCoti = "";
				String fechaCoti = "";
				Long id_bodegaEmpresa = (long)0;
				String obsCotiOdo = "";
				String cotiOdoPDF = "";
				if(cotiOdo!=null) {
					numCoti = cotiOdo.getNumero().toString();
					fechaCoti = Fechas.AAMMDD(cotiOdo.getFecha());
					id_bodegaEmpresa = cotiOdo.getId_bodegaEmpresa();
					obsCotiOdo = cotiOdo.getObservaciones();
					cotiOdoPDF = cotiOdo.getCotiOdoPDF();
					BodegaEmpresa bodega = mapBodegas.get(id_bodegaEmpresa);
					String nomCliente = "";
					String nomProyecto = "";
					String nomBodega = "";
					if(bodega!=null && bodega.getClienteVigente() == (long)1) {
						nomCliente = bodega.getNickCliente();
						nomProyecto = bodega.getNickProyecto();
						nomBodega = bodega.getNombre();
						List<String> aux = new ArrayList<String>();
						aux.add(x.getId().toString());				// 0 id_OtOdo
						aux.add(x.getId_cotiOdo().toString());		// 1 id_CotiOdo
						aux.add(x.getNumero().toString());			// 2 numero de ot odo
						aux.add(Fechas.AAMMDD(x.getFecha()));		// 3 fecha de ot odo
						aux.add(numCoti);							// 4 numero de coti odo
						aux.add(fechaCoti);							// 5 fecha de coti odo
						aux.add(nomCliente);						// 6 nombre de cliente desde bodegaempresa
						aux.add(nomProyecto);						// 7 nombre de proyecto desde bodegaempresa
						aux.add(x.getObservaciones());				// 8 observaciones de ot odo
						aux.add(obsCotiOdo);						// 9 observaciones de coti odo
						aux.add(x.getOtOdoPDF());					// 10 doc adjunto de ot odo
						aux.add(cotiOdoPDF);						// 11 doc adjunto de coti odo
						aux.add(nomBodega);							// 12 nombre bodega empresa
						lista.add(aux);
					}else {
						Cliente cliente = mapClientes.get(cotiOdo.getId_cliente());
						if(cliente!=null && cliente.getVigente() == (long)1) {
							nomCliente = cliente.getNickName();
							List<String> aux = new ArrayList<String>();
							aux.add(x.getId().toString());				// 0 id_OtOdo
							aux.add(x.getId_cotiOdo().toString());		// 1 id_CotiOdo
							aux.add(x.getNumero().toString());			// 2 numero de ot odo
							aux.add(Fechas.AAMMDD(x.getFecha()));		// 3 fecha de ot odo
							aux.add(numCoti);							// 4 numero de coti odo
							aux.add(fechaCoti);							// 5 fecha de coti odo
							aux.add(nomCliente);						// 6 nombre de cliente desde bodegaempresa
							aux.add(nomProyecto);						// 7 nombre de proyecto desde bodegaempresa
							aux.add(x.getObservaciones());				// 8 observaciones de ot odo
							aux.add(obsCotiOdo);						// 9 observaciones de coti odo
							aux.add(x.getOtOdoPDF());					// 10 doc adjunto de ot odo
							aux.add(cotiOdoPDF);						// 11 doc adjunto de coti odo
							aux.add(nomBodega);							// 12 nombre bodega empresa
							lista.add(aux);
						}
					}
				}
			});
			return ok(otOdoListaConfirma.render(mapeoDiccionario,mapeoPermiso,userMnu,lista));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result otOdoConfirma(Http.Request request) {
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
			try (Connection con = dbWrite.getConnection()){
				String listadoIdOtOdoConfirmar = form.get("cambiosDeEstados").trim();

				if(listadoIdOtOdoConfirmar.length()>1) {
					listadoIdOtOdoConfirmar = "("+listadoIdOtOdoConfirmar.substring(0,listadoIdOtOdoConfirmar.length()-1)+")";
					OtOdo.confirmar(con, s.baseDato, listadoIdOtOdoConfirmar);
					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "otOdo", (long)0, "confirma", "confirma ot odo id: "+listadoIdOtOdoConfirmar);
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
    
    
	//======================================================================
    // MNU otOdoIngreso   CotiOdo/Ordenes de trabajo/Eliminar OT u OS ODO
    //======================================================================
    
    public Result otOdoListaEliminar(Http.Request request) {
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
		if(mapeoPermiso.get("otOdoIngreso")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<OtOdo> listOtOdo = OtOdo.allEliminables(con, s.baseDato);
			Map<Long,BodegaEmpresa> mapBodegas = BodegaEmpresa.mapAll(con, s.baseDato);
			Map<Long,CotiOdo> mapCotiOdo = CotiOdo.mapAllConOt(con, s.baseDato);
			List<List<String>> lista = new ArrayList<List<String>>();
			Map<Long,Cliente> mapClientes = Cliente.mapAllClientes(con, s.baseDato);
			listOtOdo.forEach(x->{
				CotiOdo coti = mapCotiOdo.get(x.getId_cotiOdo());
				String numCoti = "";
				String fechaCoti = "";
				Long id_bodegaEmpresa = (long)0;
				String obsCoti = "";
				String cotiPDF = "";
				if(coti!=null) {
					numCoti = coti.getNumero().toString();
					fechaCoti = Fechas.AAMMDD(coti.getFecha());
					id_bodegaEmpresa = coti.getId_bodegaEmpresa();
					obsCoti = coti.getObservaciones();
					cotiPDF = coti.getCotiOdoPDF();
					BodegaEmpresa bodega = mapBodegas.get(id_bodegaEmpresa);
					String nomCliente = "";
					String nomProyecto = "";
					String nomBodega = "";
					if(bodega!=null && bodega.getClienteVigente() == (long)1) {
						nomCliente = bodega.getNickCliente();
						nomProyecto = bodega.getNickProyecto();
						nomBodega = bodega.getNombre();
						List<String> aux = new ArrayList<String>();
						aux.add(x.getId().toString());				// 0 id_Ot
						aux.add(x.getId_cotiOdo().toString());		// 1 id_Cotizacion
						aux.add(x.getNumero().toString());			// 2 numero de ot
						aux.add(Fechas.AAMMDD(x.getFecha()));		// 3 fecha de ot
						aux.add(numCoti);							// 4 numero de cotizacion
						aux.add(fechaCoti);							// 5 fecha de cotizacion
						aux.add(nomCliente);						// 6 nombre de cliente desde bodegaempresa
						aux.add(nomProyecto);						// 7 nombre de proyecto desde bodegaempresa
						aux.add(x.getObservaciones());				// 8 observaciones de ot
						aux.add(obsCoti);							// 9 observaciones de cotizacion
						aux.add(x.getOtOdoPDF());					// 10 doc adjunto de ot
						aux.add(cotiPDF);							// 11 doc adjunto de cotizacion
						aux.add(nomBodega);							// 12 nombre bodega empresa
						lista.add(aux);
					}else if(bodega == null) {
						Cliente cliente = mapClientes.get(coti.getId_cliente());
						if(cliente!=null && cliente.getVigente() == (long)1) {
							nomCliente = cliente.getNickName();
							List<String> aux = new ArrayList<String>();
							aux.add(x.getId().toString());				// 0 id_Ot
							aux.add(x.getId_cotiOdo().toString());		// 1 id_Cotizacion
							aux.add(x.getNumero().toString());			// 2 numero de ot
							aux.add(Fechas.AAMMDD(x.getFecha()));		// 3 fecha de ot
							aux.add(numCoti);							// 4 numero de cotizacion
							aux.add(fechaCoti);							// 5 fecha de cotizacion
							aux.add(nomCliente);						// 6 nombre de cliente desde bodegaempresa
							aux.add(nomProyecto);						// 7 nombre de proyecto desde bodegaempresa
							aux.add(x.getObservaciones());				// 8 observaciones de ot
							aux.add(obsCoti);							// 9 observaciones de cotizacion
							aux.add(x.getOtOdoPDF());					// 10 doc adjunto de ot
							aux.add(cotiPDF);							// 11 doc adjunto de cotizacion
							aux.add(nomBodega);							// 12 nombre bodega empresa
							lista.add(aux);
						}
					}
				}
			});
			return ok(otOdoListaEliminar.render(mapeoDiccionario,mapeoPermiso,userMnu,lista));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result verOtOdoAjax(Http.Request request) {
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
			try (Connection con = dbRead.getConnection()){
				Long id_otOdo = Long.parseLong(form.get("id_otOdo").trim());
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				String vista = OtOdo.vistaModalVerOtOdo(con, s.baseDato, id_otOdo, mapeoDiccionario);
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
    
    public Result actualFechaEnvioOtOdoAjax(Http.Request request) {
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
			try (Connection con = dbWrite.getConnection()) {
				Long id_ot = Long.parseLong(form.get("id_ot").trim());
				String fechaEnvio = form.get("fechaEntrega").trim();
				Fechas hoy = Fechas.hoy();
				String fechaActual = hoy.getFechaStrAAMMDD();
				if(fechaEnvio.equals("")) {
					fechaEnvio = null;
					fechaActual = null;
				}
				if(OtOdo.modifyFechaEnvio(con, s.baseDato, fechaActual, fechaEnvio, id_ot)){
						if(fechaActual!=null) {
							return ok(Fechas.DDMMAA(fechaActual));
						}else {
							return ok("");
						}
				}
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
				return ok("error");
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("error");
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("error");
			}
    	}
	}
    
    public Result actualOperServOtOdoAjax(Http.Request request) {
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
			try (Connection con = dbWrite.getConnection()) {
				Long id_ot = Long.parseLong(form.get("id_ot").trim());
				String id_operadorServicio = form.get("id_operadorServicio").trim();
				if(OtOdo.modifyOperadorServicio(con, s.baseDato, id_operadorServicio, id_ot)){
					return ok("");
				}
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
				return ok("error");
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("error");
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("error");
			}
    	}
	}
    
    public Result otOdoElimina(Http.Request request) {
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
				Long id_otOdo = Long.parseLong(form.get("id_otOdo").trim());
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				if(mapeoPermiso.get("otOdoIngreso")==null) {
					return ok(mensajes.render("/",msgSinPermiso));
				}
				OtOdo otOdo = OtOdo.find(con, s.baseDato, id_otOdo);
				String msg = "";
				if(VentaServicio.existenVentaAsociado(con, s.baseDato, otOdo.id_cotiOdo)){
					msg = "No es posible eliminar la órden, existen despachos o movimientos asociados, "
							+ " primero debe eliminar todo despacho o movimientos asociados a la ot";
				}else {
					OtOdo.delete(con, s.baseDato, otOdo);
					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "otOdo", id_otOdo, "delete", "elimina una orden de trabajo odo nro: "+otOdo.getNumero());
					msg = "Se eliminó la órden cambiando la cotización asociada de confirmada a no confirmada, "
							+ "para generar una nueva órden a partir de la misma cotización debe confirmarla nuevamente";
				}
				return ok(mensajes.render("/routes2/otOdoListaEliminar/",msg));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
    	}
    }
    
    
    //============================================================================
    // MNU otOdoIngreso   CotiOdo/Ordenes de trabajo/Cambiar Estado OT u OS ODO
    //===========================================================================
    
    public Result otOdoListaCambiarEstado(Http.Request request) {
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
		if(mapeoPermiso.get("otOdoIngreso")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<OtOdo> listOtOdo = OtOdo.all(con, s.baseDato);
			Map<Long,BodegaEmpresa> mapBodegas = BodegaEmpresa.mapAll(con, s.baseDato);
			Map<Long,CotiOdo> mapCotiOdo = CotiOdo.mapAllConOt(con, s.baseDato);
			Map<Long,OtEstado> mapOtEstado = OtEstado.mapAll(con, s.baseDato);
			List<List<String>> lista = new ArrayList<List<String>>();
			Map<Long,OperadorServicio> mapOperadorServicio = OperadorServicio.mapAll(con, s.baseDato);
			Map<Long,Cliente> mapClientes = Cliente.mapAllClientes(con, s.baseDato);
			listOtOdo.forEach(x->{
				CotiOdo cotiOdo = mapCotiOdo.get(x.getId_cotiOdo());
				String numCoti = "";
				String fechaCoti = "";
				Long id_bodegaEmpresa = (long)0;
				String obsCoti = "";
				String cotiPDF = "";
				if(cotiOdo!=null) {
					numCoti = cotiOdo.getNumero().toString();
					fechaCoti = Fechas.AAMMDD(cotiOdo.getFecha());
					id_bodegaEmpresa = cotiOdo.getId_bodegaEmpresa();
					obsCoti = cotiOdo.getObservaciones();
					cotiPDF = cotiOdo.getCotiOdoPDF();
					BodegaEmpresa bodega = mapBodegas.get(id_bodegaEmpresa);
					String nomCliente = "";
					String nomProyecto = "";
					String nomBodega = "";
					OtEstado estado = mapOtEstado.get(x.getId_otEstado());
					String id_estado = "0";
					String nomEstado = "";
					if(estado!=null) {
						id_estado = estado.getId().toString();
						nomEstado = estado.getEstado();
					}
					OperadorServicio operadorServicio = mapOperadorServicio.get(x.getId_operadorServicio());
					if(operadorServicio==null) {
						operadorServicio = new OperadorServicio();
						operadorServicio.id = (long)0;
						operadorServicio.nombre = "";
					}
					if(bodega!=null) {
						nomCliente = bodega.getNickCliente();
						nomProyecto = bodega.getNickProyecto();
						nomBodega = bodega.getNombre();
						List<String> aux = new ArrayList<String>();
						aux.add(x.getId().toString());				// 0 id_Ot
						aux.add(x.getId_cotiOdo().toString());		// 1 id_Cotizacion
						aux.add(x.getNumero().toString());			// 2 numero de ot
						aux.add(Fechas.AAMMDD(x.getFecha()));		// 3 fecha de ot
						aux.add(numCoti);							// 4 numero de cotizacion
						aux.add(fechaCoti);							// 5 fecha de cotizacion
						aux.add(nomCliente);						// 6 nombre de cliente desde bodegaempresa
						aux.add(nomProyecto);						// 7 nombre de proyecto desde bodegaempresa
						aux.add(x.getObservaciones());				// 8 observaciones de ot
						aux.add(obsCoti);							// 9 observaciones de cotizacion
						aux.add(x.getOtOdoPDF());					// 10 doc adjunto de ot
						aux.add(cotiPDF);							// 11 doc adjunto de cotizacion
						aux.add(id_estado);							// 12 id_estado
						aux.add(nomEstado);							// 13 nombre estado
						aux.add(nomBodega);							// 14 nombre bodega empresa
						aux.add(x.getId_operadorServicio().toString());	// 15 id operadorServicio
						aux.add(x.getFechaActualizacion());				// 16 fecha actualizacion
						aux.add(x.fechaEnvio);							// 17 fecha de envio
						aux.add(operadorServicio.nombre);				// 18 nombre operadorServicio
						lista.add(aux);
					}else {
						Cliente cliente = mapClientes.get(cotiOdo.getId_cliente());
						if(cliente!=null) {
							nomCliente = cliente.getNickName();
							List<String> aux = new ArrayList<String>();
							aux.add(x.getId().toString());				// 0 id_Ot
							aux.add(x.getId_cotiOdo().toString());		// 1 id_Cotizacion
							aux.add(x.getNumero().toString());			// 2 numero de ot
							aux.add(Fechas.AAMMDD(x.getFecha()));		// 3 fecha de ot
							aux.add(numCoti);							// 4 numero de cotizacion
							aux.add(fechaCoti);							// 5 fecha de cotizacion
							aux.add(nomCliente);						// 6 nombre de cliente desde bodegaempresa
							aux.add(nomProyecto);						// 7 nombre de proyecto desde bodegaempresa
							aux.add(x.getObservaciones());				// 8 observaciones de ot
							aux.add(obsCoti);							// 9 observaciones de cotizacion
							aux.add(x.getOtOdoPDF());					// 10 doc adjunto de ot
							aux.add(cotiPDF);							// 11 doc adjunto de cotizacion
							aux.add(id_estado);							// 12 id_estado
							aux.add(nomEstado);							// 13 nombre estado
							aux.add(nomBodega);							// 14 nombre bodega empresa
							aux.add(x.getId_operadorServicio().toString());	// 15 id operadorServicio
							aux.add(x.getFechaActualizacion());				// 16 fecha actualizacion
							aux.add(x.fechaEnvio);							// 17 fecha de envio
							aux.add(operadorServicio.nombre);				// 18 nombre operadorServicio
							lista.add(aux);
						}
					}
				}
			});
			List<OtEstado> listEstados = OtEstado.all(con, s.baseDato);
			List<OperadorServicio> listOperadoresServicio = OperadorServicio.all(con, s.baseDato);
			return ok(otOdoListaCambiarEstado.render(mapeoDiccionario,mapeoPermiso,userMnu,lista, listEstados, listOperadoresServicio));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result cambiarOtOdoEstadoAjax(Http.Request request) {
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
				Long id_otOdo = Long.parseLong(form.get("id_otOdo").trim());
				String id_otEstado = form.get("id_otEstado").trim();
				OtOdo.modifyXCampo(con, s.baseDato, "id_otEstado", id_otEstado, id_otOdo);
				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "otOdo", id_otOdo, "update", "cambia el estado de otOdo id: "+id_otOdo.toString());
				return ok("OK");
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("error");
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("error");
			}
    	}
	}
    
    //============================================================================
    // MNU otOdoRevisa   CotiOdo/Ordenes de trabajo/Revisar Imprimir OT u OS ODO
    //============================================================================
    
    public Result otOdoListaRevisar(Http.Request request) {
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
		if(mapeoPermiso.get("otOdoRevisa")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<OtOdo> listOtOdo = OtOdo.all(con, s.baseDato);
			Map<Long,BodegaEmpresa> mapBodegas = BodegaEmpresa.mapAll(con, s.baseDato);
			Map<Long,CotiOdo> mapCotiOdo = CotiOdo.mapAllConOt(con, s.baseDato);
			Map<Long,OtEstado> mapEstado = OtEstado.mapAll(con, s.baseDato);
			Map<Long,OperadorServicio> mapOperadorServicio = OperadorServicio.mapAll(con, s.baseDato);
			List<List<String>> lista = new ArrayList<List<String>>();
			listOtOdo.forEach(x->{
				CotiOdo cotiOdo = mapCotiOdo.get(x.getId_cotiOdo());
				String numCoti = "";
				String fechaCoti = "";
				Long id_bodegaEmpresa = (long)0;
				String obsCoti = "";
				String cotiPDF = "";
				if(cotiOdo!=null) {
					numCoti = cotiOdo.getNumero().toString();
					fechaCoti = Fechas.AAMMDD(cotiOdo.getFecha());
					id_bodegaEmpresa = cotiOdo.getId_bodegaEmpresa();
					obsCoti = cotiOdo.getObservaciones();
					cotiPDF = cotiOdo.getCotiOdoPDF();
				}
				BodegaEmpresa bodega = mapBodegas.get(id_bodegaEmpresa);
				String nomCliente = "";
				String nomProyecto = "";
				String nomBodega = "";
				if(bodega!=null) {
					nomCliente = bodega.getNickCliente();
					nomProyecto = bodega.getNickProyecto();
					nomBodega = bodega.getNombre();
				}
				OtEstado estado = mapEstado.get(x.getId_otEstado());
				String nomEstado = "";
				if(estado!=null) {
					nomEstado = estado.getEstado();
				}
				OperadorServicio operadorServicio = mapOperadorServicio.get(x.getId_operadorServicio());
				if(operadorServicio==null) {
					operadorServicio = new OperadorServicio();
					operadorServicio.id = (long)0;
					operadorServicio.nombre = "";
				}
				List<String> aux = new ArrayList<String>();
				aux.add(x.getId().toString());				// 0 id_Ot
				aux.add(x.getId_cotiOdo().toString());		// 1 id_CotiOdo
				aux.add(x.getNumero().toString());			// 2 numero de ot
				aux.add(Fechas.AAMMDD(x.getFecha()));		// 3 fecha de ot
				aux.add(numCoti);							// 4 numero de cotizacion
				aux.add(fechaCoti);							// 5 fecha de cotizacion
				aux.add(nomCliente);						// 6 nombre de cliente desde bodegaempresa
				aux.add(nomProyecto);						// 7 nombre de proyecto desde bodegaempresa
				aux.add(x.getObservaciones());				// 8 observaciones de ot
				aux.add(obsCoti);							// 9 observaciones de cotizacion
				aux.add(x.getOtOdoPDF());					// 10 doc adjunto de ot odo
				aux.add(cotiPDF);							// 11 doc adjunto de cotizacion odo
				aux.add(nomEstado);							// 12 estado de la ot
				aux.add(x.getFechaConfirmada());			// 13 fecha confirmado
				aux.add(nomBodega);							// 14 nombre bodega empresa
				aux.add(x.getId_operadorServicio().toString());	// 15 id operadorServicio
				aux.add(x.getFechaActualizacion());				// 16 fecha actualizacion
				aux.add(x.fechaEnvio);							// 17 fecha de envio
				aux.add(operadorServicio.nombre);				// 18 nombre operadorServicio
				lista.add(aux);
			});
			return ok(otOdoListaRevisar.render(mapeoDiccionario,mapeoPermiso,userMnu,lista));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result revisarOtOdo(Http.Request request) {
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
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
			if(mapeoPermiso.get("otOdoRevisa")==null) {
				logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
				return ok(mensajes.render("/",msgSinPermiso));
			}
			try (Connection con = dbRead.getConnection()){
				Long id_otOdo = Long.parseLong(form.get("id_otOdo").trim());
				String tabla = OtOdo.vistaRevisarOtOdo(con, s.baseDato, id_otOdo, mapeoDiccionario);
				return ok(revisarOtOdo.render(mapeoDiccionario,mapeoPermiso,userMnu, id_otOdo, tabla));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
    	}
    }
    
    public Result trazaServicioOtOdoAjax(Http.Request request) {
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
			try (Connection con = dbRead.getConnection()){
				Long id_otOdo = Long.parseLong(form.get("id_otOdo").trim());
				Long id_servicio = Long.parseLong(form.get("id_servicio").trim());
				String tabla = VentaServicio.modalTrazaPorIdOtOdoIdServicio(con, s.baseDato, id_otOdo, id_servicio);
				return ok(tabla);
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("error");
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("error");
			}
    	}
	}
    
    public Result otOdoRevisarExcel(Http.Request request) {
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
			try (Connection con = dbRead.getConnection()){
				Long id_otOdo = Long.parseLong(form.get("id_otOdo").trim());
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				File file = OtOdoEnExcel.otOdoEnExcel(con, s.baseDato, id_otOdo, mapeoDiccionario);
				return ok(file,false,Optional.of("Orden.xlsx"));
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
    // MNU otOdoRevisa   CotiOdo/Cotizar/Reporte Movil OT ODO
    //============================================================
	
	public Result reportOtOdoSel(Http.Request request) {
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
		if(mapeoPermiso.get("otOdoRevisa")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try {
			Fechas hoy = Fechas.hoy();
			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			return ok(reportOtOdoSel.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}
	
	public Result reportOtOdoRpt(Http.Request request) {
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
			try (Connection con = dbRead.getConnection()){
				String desdeAAMMDD = form.get("fechaDesde").trim();
				String hastaAAMMDD = form.get("fechaHasta").trim();
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				DecimalFormat myformaNumber = new DecimalFormat("0",symbols);
				Map<String,Double> map = ReportCotiOdo.calculoOtOdo(con, s.baseDato, desdeAAMMDD, hastaAAMMDD,  mapeoDiccionario.get("pais"));
				Double val_ConOt = map.get("val_ConOt");
				if(val_ConOt == null) val_ConOt = (double)0;
				Double val_SinOt = map.get("val_SinOt");
				if(val_SinOt == null) val_SinOt = (double)0;
				Double cant_ConOt = map.get("cant_ConOt");
				if(cant_ConOt == null) cant_ConOt = (double)0;
				Double cant_SinOt = map.get("cant_SinOt");
				if(cant_SinOt == null) cant_SinOt = (double)0;
				String grafValConOt = "[['CON "+mapeoDiccionario.get("OT")+"',"+myformaNumber.format(val_ConOt)+"],['SIN "+mapeoDiccionario.get("OT")+"',"+myformaNumber.format(val_SinOt)+"]]";
				String grafCantConOt = "[['CON "+mapeoDiccionario.get("OT")+"',"+cant_ConOt+"],['SIN "+mapeoDiccionario.get("OT")+"',"+cant_SinOt+"]]";
				List<OtEstado> listOtEstado = OtEstado.all(con, s.baseDato);
				String grafValSinOt = "[";
				String grafCantSinOt = "[";
				for(OtEstado x: listOtEstado) {
					Double val = map.get("val_"+x.estado);
					if(val!=null) {
						grafValSinOt += "['"+x.estado+"',"+myformaNumber.format(val)+"],";
					}
					Double cant = map.get("cant_"+x.estado);
					if(cant!=null) {
						grafCantSinOt += "['"+x.estado+"',"+myformaNumber.format(cant)+"],";
					}
				}
				if(grafValSinOt.length()>2) {
					grafValSinOt = grafValSinOt.substring(0, grafValSinOt.lastIndexOf(",")) + "]";
				}else {
					grafValSinOt = "[]";
				}
				if(grafCantSinOt.length()>2) {
					grafCantSinOt = grafCantSinOt.substring(0, grafCantSinOt.lastIndexOf(",")) + "]";
				}else {
					grafCantSinOt = "[]";
				}
				String fechaDe = Fechas.DDMMAA(desdeAAMMDD);
				String fechaA = Fechas.DDMMAA(hastaAAMMDD);
				return ok(reportOtOdoRpt.render(mapeoDiccionario,mapeoPermiso,userMnu, grafValConOt, grafCantConOt, grafValSinOt, grafCantSinOt, fechaDe, fechaA));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
    	}
	}
	
	
	//======================================================================
    // MNU otOdoAgregaOC   CotiOdo/Agregar OC del Cliente ODO
    //======================================================================
    
    public Result otOdoListaAgregarOC(Http.Request request, Long year) {
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
		if(mapeoPermiso.get("otOdoAgregaOC")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			if((long) year == (long) 0) {
				Fechas hoy = Fechas.hoy();
				String[] aux = hoy.getFechaStrAAMMDD().split("-");
				year = Long.parseLong(aux[0]);
			}
			List<OtOdo> listOtOdo = OtOdo.allConfirmadasPorAnio(con, s.baseDato, year);
			Long minYearCoti = OtOdo.anioPrimeraOt(con, s.baseDato);
			List<Long> listAnios = new ArrayList<Long>();
			Fechas hoy = Fechas.hoy();
			String[] auxiliar = hoy.getFechaStrAAMMDD().split("-");
			year = Long.parseLong(auxiliar[0]);
			while (minYearCoti <= year) {
				listAnios.add(year);
				year = year - 1;
			}
			Map<Long,BodegaEmpresa> mapBodegas = BodegaEmpresa.mapAll(con, s.baseDato);
			Map<Long,CotiOdo> mapCotizacion = CotiOdo.mapAllConOt(con, s.baseDato);
			Map<Long,OtEstado> mapEstado = OtEstado.mapAll(con, s.baseDato);
			List<List<String>> lista = new ArrayList<List<String>>();
			listOtOdo.forEach(x->{
				CotiOdo cotiOdo = mapCotizacion.get(x.getId_cotiOdo());
				String numCoti = "";
				String fechaCoti = "";
				Long id_bodegaEmpresa = (long)0;
				String obsCoti = "";
				String cotiPDF = "";
				String cotiNumeroOC = "";
				if(cotiOdo!=null) {
					numCoti = cotiOdo.getNumero().toString();
					fechaCoti = Fechas.AAMMDD(cotiOdo.getFecha());
					id_bodegaEmpresa = cotiOdo.getId_bodegaEmpresa();
					obsCoti = cotiOdo.getObservaciones();
					cotiPDF = cotiOdo.getCotiOdoPDF();
					cotiNumeroOC = cotiOdo.getNumeroOC();
				}
				BodegaEmpresa bodega = mapBodegas.get(id_bodegaEmpresa);
				String nomCliente = "";
				String nomProyecto = "";
				String nomBodega = "";
				if(bodega!=null) {
					nomCliente = bodega.getNickCliente();
					nomProyecto = bodega.getNickProyecto();
					nomBodega = bodega.getNombre();
				}
				OtEstado estado = mapEstado.get(x.getId_otEstado());
				String nomEstado = "";
				if(estado!=null) {
					nomEstado = estado.getEstado();
				}
				List<String> aux = new ArrayList<String>();
				aux.add(x.getId().toString());				// 0 id_OtOdo
				aux.add(x.getId_cotiOdo().toString());		// 1 id_CotiOdo
				aux.add(x.getNumero().toString());			// 2 numero de ot
				aux.add(Fechas.AAMMDD(x.getFecha()));		// 3 fecha de ot
				aux.add(numCoti);							// 4 numero de cotizacion
				aux.add(fechaCoti);							// 5 fecha de cotizacion
				aux.add(nomCliente);						// 6 nombre de cliente desde bodegaempresa
				aux.add(nomProyecto);						// 7 nombre de proyecto desde bodegaempresa
				aux.add(x.getObservaciones());				// 8 observaciones de ot
				aux.add(obsCoti);							// 9 observaciones de cotizacion
				aux.add(x.getOtOdoPDF());					// 10 doc adjunto de ot
				aux.add(cotiPDF);							// 11 doc adjunto de cotizacion
				aux.add(nomEstado);							// 12 estado de la ot
				aux.add(x.getFechaConfirmada());			// 13 fecha confirmado
				aux.add(nomBodega);							// 14 nombre bodega empresa
				aux.add(x.getOtOdoPDF());						// 15 orden de compra adjunta
				aux.add(cotiNumeroOC);						// 16 numero de orden de compra del cliente
				lista.add(aux);
			});
			return ok(otOdoListaAgregarOC.render(mapeoDiccionario,mapeoPermiso,userMnu,lista,listAnios));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result grabarOcOdoPdf(Http.Request request, Long id_otOdo) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		Http.MultipartFormData<TemporaryFile> body = request.body().asMultipartFormData();
		Http.MultipartFormData.FilePart<TemporaryFile> docAdjunto = body.getFile("docAdjunto");
		try (Connection con = dbWrite.getConnection()){
			String path = "0";
			if (docAdjunto != null) {
				String nombreArchivoSinExtencion = "ocCliente_OtOdo_ID_" + id_otOdo;
				path = Archivos.grabarArchivos(docAdjunto, s.baseDato, nombreArchivoSinExtencion);
				OtOdo.modifyXCampo(con, s.baseDato, "otOdoPDF", path, id_otOdo);
			}
			OtOdo ot = OtOdo.find(con, s.baseDato, id_otOdo);
			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "otOdo", id_otOdo, "update", "agrega orden de compra a ot odo nro: "+ot.getNumero());
			return redirect("/routes2/otOdoListaAgregarOC/0");
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result oc_cotiOdoClienteAjax(Http.Request request) {
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
				Long id_cotiOdo = Long.parseLong(form.get("id_cotiOdo").trim());
				String numeroOC = form.get("numeroOC").trim();
				CotiOdo.modifyXCampo(con, s.baseDato, "numeroOC", numeroOC, id_cotiOdo);
				CotiOdo coti = CotiOdo.find(con, s.baseDato, id_cotiOdo);
				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "cotiOdo", id_cotiOdo, "update", "agrega numero de orden de compra a coti odo nro: "+coti.getNumero());
				con.close();
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
    
    //======================================================================
    // MNU cotiOdoGeneraContrato   CotiOdo/Generar Contrato Odo
    //======================================================================
    
    public Result otOdoGenerarContrato(Http.Request request, Long year) {
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
		if(mapeoPermiso.get("cotiOdoGeneraContrato")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			if((long) year == (long) 0) {
				Fechas hoy = Fechas.hoy();
				String[] aux = hoy.getFechaStrAAMMDD().split("-");
				year = Long.parseLong(aux[0]);
			}
			List<CotiOdo> listCotiOdo = CotiOdo.allConfirmadasCon_o_SinOtPorAnio(con, s.baseDato, year);
			Long minYearCoti = CotiOdo.anioPrimeraCotiOdo(con, s.baseDato);
			List<Long> listAnios = new ArrayList<Long>();
			Fechas hoy = Fechas.hoy();
			String[] auxiliar = hoy.getFechaStrAAMMDD().split("-");
			year = Long.parseLong(auxiliar[0]);
			while (minYearCoti <= year) {
				listAnios.add(year);
				year = year - 1;
			}
			Map<Long,Cliente> mapCliente = Cliente.mapAllClientes(con, s.baseDato);
			Map<Long,Proyecto> mapProyecto = Proyecto.mapAllProyectos(con, s.baseDato);
			Map<Long,CotizaEstado> mapEstado = CotizaEstado.mapAll(con, s.baseDato);
			List<List<String>> lista = new ArrayList<List<String>>();
			listCotiOdo.forEach(x->{
				Cliente cliente = mapCliente.get(x.getId_cliente());
				String nomCliente = "";
				if(cliente!=null && cliente.getVigente() == (long)1) {
					nomCliente = cliente.nickName;
					Proyecto proyecto = mapProyecto.get(x.getId_proyecto());
					String nomProyecto = "";
					if(proyecto!=null) {
						nomProyecto = proyecto.nickName;
					}
					CotizaEstado estado = mapEstado.get(x.getId_cotizaEstado());
					String nomEstado = "";
					if(estado!=null) {
						nomEstado = estado.getEstado();
					}
					List<String> aux = new ArrayList<String>();
					aux.add(x.getId().toString());				// 0 id_cotizacion
					aux.add(x.getNumero().toString());			// 1 numero de cotizacion
					aux.add(Fechas.AAMMDD(x.getFecha()));		// 2 fecha
					aux.add(nomCliente);						// 3 nombre de cliente
					aux.add(x.getObservaciones());				// 4 observaciones
					aux.add(x.getCotiOdoPDF());				// 5 doc adjunto
					aux.add(nomProyecto);						// 6 nombre de proyecto
					aux.add(x.getId_cotizaEstado().toString());	// 7 id_cotizaEstado
					aux.add(nomEstado);							// 8 nombre de estado
					aux.add(x.getFechaConfirmada());			// 9 fecha de confirmada
					aux.add(x.getPdfCotiVtaOdo());				// 10 pdf arriendo generado
					aux.add(x.getContratoPDF());				// 11 pdf contrato pdf anexado
					lista.add(aux);
				}
			});
			return ok(otOdoGenerarContrato.render(mapeoDiccionario,mapeoPermiso,userMnu,lista,listAnios));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result grabarContratoPdfOdo(Http.Request request, Long id_cotiOdo) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		Http.MultipartFormData<TemporaryFile> body = request.body().asMultipartFormData();
		Http.MultipartFormData.FilePart<TemporaryFile> docAdjunto = body.getFile("docAdjunto");
		try (Connection con = dbWrite.getConnection()){
			String path = "0";
			if (docAdjunto != null) {
				String nombreArchivoSinExtencion = "contrato_ClienteCotiOdo_ID_" + id_cotiOdo;
				path = Archivos.grabarArchivos(docAdjunto, s.baseDato, nombreArchivoSinExtencion);
				CotiOdo.modifyXCampo(con, s.baseDato, "contratoPDF", path, id_cotiOdo);
			}
			CotiOdo cotiOdo = CotiOdo.find(con, s.baseDato, id_cotiOdo);
			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "cotiOdo", id_cotiOdo, "update", "agrega contrato pdf a cotizacion nro: "+cotiOdo.getNumero());
			return redirect("/routes2/otOdoGenerarContrato/0");
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result datosContratoOdo(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgErrorFormulario));
		}else {
			try (Connection con = dbRead.getConnection()){
				Long id_cotiOdo = Long.parseLong(form.get("id_cotiOdo").trim());
				FormContratoOdo datos = FormContratoOdo.find(con, s.baseDato, id_cotiOdo);
				Regiones region = Regiones.findPorNombre(con, s.baseDato, datos.clienteRegion);
				Comunas comuna = Comunas.findPorNombre(con, s.baseDato, datos.clienteComuna);
				String codRegion = "0";
				if(region!=null) {
					codRegion = region.codigo;
				}
				List<Regiones> listRegiones = Regiones.all(con, s.baseDato);
				List<Comunas> listComunas = Comunas.allPorRegion(con, s.baseDato, codRegion);
				List<List<String>> listBodegas = BodegaEmpresa.listaAllBodegasVigentesExternas(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
				List<Proyecto> listProyectos = Proyecto.all(con, s.baseDato);
				return ok(datosContratoOdo.render(mapeoDiccionario,mapeoPermiso,userMnu,datos,region,comuna,listRegiones, listComunas, listBodegas, listProyectos));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
    	}
	}

    public Result hacerContratoOdo(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		FormContratoOdo form = formFactory.form(FormContratoOdo.class).withDirectFieldAccess(true).bindFromRequest(request).get();
		if (form.id_cotiOdo==null || form.id_cliente==null) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgErrorFormulario));
		}else {
			try (Connection con = dbWrite.getConnection()){
				String fileNamePdf = "0";
				String titulo = "";
				if(FormContratoOdo.update(con, s.baseDato, form)) {
					CotiOdo cotiOdo = CotiOdo.find(con, s.baseDato, form.id_cotiOdo);
					fileNamePdf = GeneraPDF_ContratoOdo.generaPdfContratoTipoCotizaOdo(con, s.baseDato, cotiOdo, form);
					titulo = "CONTRATO_"+ cotiOdo.getNumeroContrato();
				}
				String url = "%2Fhome%2F";
				return redirect("/routes2/redirShowPDF/"+fileNamePdf+","+titulo+","+url);
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
    	}
	}
    
    public Result cotiOdoPlantilla(Http.Request request) {
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
			try {
				InputStream tmp = Archivos.leerArchivo("formatos/plantillaCotiOdo.xlsx");
				File file = Archivos.parseInputStreamToFile(tmp);
				return ok(file, Optional.of("plantillaCargaCotiOdo.xlsx"));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}
    
    public Result cotiOdoValidarPlantilla(Http.Request request) {
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
			List<String> mensaje = new ArrayList<String>();
			Http.MultipartFormData<TemporaryFile> body = request.body().asMultipartFormData();
			Http.MultipartFormData.FilePart<TemporaryFile> archivo = body.getFile("archivoXLSX");
			if (archivo != null) {
				File file = Archivos.parseMultipartFormDatatoFile(archivo);
				try (Connection con = dbRead.getConnection()){
					Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
					Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
					Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
					mensaje = FormCotizaOdo.cotiOdoValidarPlantillaExcel(con, s.baseDato, file);
					if(mensaje.get(0).equals("true")) {
						List<List<String>> listaExcel = FormCotizaOdo.llenaListaDesdeExcel(file);
						Map<String,List<String>> mapListaExcel = new HashMap<String,List<String>>();
						for(List<String> l: listaExcel){
							if(l.get(2).trim().equals("")) {
								l.set(2, "1");
							}
							mapListaExcel.put(l.get(0), l);
						}
						if(mapListaExcel.size() == listaExcel.size()) {
							Long numCotiOdo  = CotiOdo.findNuevoNumeroOdo(con, s.baseDato);
							String fecha = Fechas.hoy().getFechaStrAAMMDD();
							FormCotizaOdo formCotizaOdo = new FormCotizaOdo();
							formCotizaOdo = new FormCotizaOdo(numCotiOdo, fecha);
							formCotizaOdo.id_cotiOdo = (long)0;
							List<Servicio> listServicios = Servicio.all(con, s.baseDato);
							List<Cliente> listClientes = Cliente.all(con, s.baseDato);
							List<Proyecto> listProyectos = Proyecto.all(con, s.baseDato);
							List<Regiones> listRegiones = Regiones.all(con, s.baseDato);
							List<CotiOdo> listaCotiOdo = CotiOdo.all(con, s.baseDato);
							Map<Long,Cliente> mapCliente = Cliente.mapAllClientes(con, s.baseDato);
							Map<Long,Proyecto> mapProyecto = Proyecto.mapAllProyectos(con, s.baseDato);
							List<List<String>> listCotiOdo = new ArrayList<List<String>>();
							listaCotiOdo.forEach(x->{
								String nomCliente = "";
								String nomProyecto = "";
								Cliente cliente = mapCliente.get(x.getId_cliente());
								Proyecto proyecto = mapProyecto.get(x.getId_proyecto());
								if(cliente!=null) {
									nomCliente = cliente.getNickName();
								}
								if(proyecto!=null) {
									nomProyecto = proyecto.getNickName();
								}
								List<String> aux = new ArrayList<String>();
								aux.add(x.getId().toString());
								aux.add(x.getNumero().toString());
								aux.add(x.getFecha());
								aux.add(nomCliente);
								aux.add(nomProyecto);
								listCotiOdo.add(aux);
							});
							List<List<String>> listadoServicios = new ArrayList<List<String>>();
							Map<Long,Moneda> mapMoneda = Moneda.mapMonedas(con, s.baseDato);
							Long numDec = (long) 0;
							for(Servicio x: listServicios) {
								Moneda moneda = mapMoneda.get(x.getId_moneda());
								if(moneda!=null) {
									numDec = moneda.getNumeroDecimales();
								}else {
									numDec = (long) 0;
								}
								String cantidad = DecimalFormato.formato((double)0,(long)2);
								String precio = DecimalFormato.formato(x.getPrecio(),numDec);
								String total = DecimalFormato.formato((double)0,numDec);
								String aplicaMinimo = "0";
								String cantidadMinimo = DecimalFormato.formato((double)0,(long)2);
								String precioAdicional = DecimalFormato.formato((double)0,numDec);
								List<String> lista = mapListaExcel.get(x.getCodigo());
								if(lista!=null) {
									double auxCantidad = (double)1;
									if(!lista.get(2).trim().equals("")) {
										auxCantidad = Double.parseDouble(lista.get(2));
									}
									double auxPrecio = (double)0;
									if(!lista.get(3).trim().equals("")) {
										auxPrecio = Double.parseDouble(lista.get(3));
									}else {
										auxPrecio = x.getPrecio();
									}
									double auxCantidadMinimo = (double)0;
									if(!lista.get(5).trim().equals("")) {
										auxCantidadMinimo = Double.parseDouble(lista.get(5));
									}
									double auxPrecioAdicional = (double)0;
									if(!lista.get(6).trim().equals("")) {
										auxPrecioAdicional = Double.parseDouble(lista.get(6));
									}
									if(lista.get(4).equals("SI")) {
										aplicaMinimo = "1";
									}
									cantidad = DecimalFormato.formato(auxCantidad,(long)2);
									precio = DecimalFormato.formato(auxPrecio,numDec);
									total = DecimalFormato.formato((auxCantidad*auxPrecio),numDec);
									cantidadMinimo = DecimalFormato.formato(auxCantidadMinimo,(long)2);
									precioAdicional = DecimalFormato.formato(auxPrecioAdicional,numDec);
								}
								List<String> aux = new ArrayList<String>();
								aux.add(x.getId().toString());		//0 id_servicio
								aux.add(x.getNombreClase());		//1 clase
								aux.add(x.getCodigo());				//2 codigo
								aux.add(x.getNombre());				//3 clase
								aux.add(x.getNombreUnidad());		//4 unidad
								aux.add(cantidad);					//5 cantidad
								aux.add(x.getNickMoneda());			//6 moneda
								aux.add(precio);					//7 precio
								aux.add(total);						//8 total
								aux.add(aplicaMinimo);				//9 aplicaMinimo
								aux.add(cantidadMinimo);			//10 cantidadMinimo
								aux.add(precioAdicional);			//11 precio adicional
								aux.add(numDec.toString());								//12 num decimales
								aux.add(x.getId_moneda().toString());					//13 id_moneda
								listadoServicios.add(aux);
							}

							List<Equipo> auxlistEquipos = Equipo.allVigentes(con, s.baseDato);
							Map<Long,Double> mapEquipConStock = Inventarios.mapEquiposConStock(con, s.baseDato,"ARRIENDO",mapeoDiccionario);
							List<Equipo> listEquipos = new ArrayList<Equipo>();
							for(Equipo x: auxlistEquipos) {
								Double stock = mapEquipConStock.get(x.getId());
								if(stock!=null && stock.doubleValue() > 0) {
									listEquipos.add(x);
								}
							}

							return ok(cotiOdoIngreso.render(mapeoDiccionario,mapeoPermiso,userMnu,listClientes,listProyectos,listRegiones, formCotizaOdo, listadoServicios, listCotiOdo, numDec, listEquipos));
						}else {
							return ok(mensajes.render("/routes2/cotiOdoIngreso/"+id_bodegaEmpresa, "Existen codigos duplicados en el archivo"));
						}
					}else {
						String msg = "";
						for(String m: mensaje) {
							msg += m + " ";
						}
						return ok(mensajes.render("/routes2/cotiOdoIngreso/"+id_bodegaEmpresa, msg));
					}
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				}
			}
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgReport));
		}
	}
    
    
    

}






