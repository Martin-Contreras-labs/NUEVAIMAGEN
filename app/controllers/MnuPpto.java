package controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import controllers.HomeController.Sessiones;
import models.tables.BodegaEmpresa;
import models.tables.Grupo;
import models.tables.Moneda;
import models.tables.Ppto;
import models.tables.UsuarioPermiso;
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
import viewsMnuPpto.html.*;

public class MnuPpto extends Controller {

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
    // MNU pptoAdministrar   Ppto/Administrar Pptos
    //============================================================
	
	public Result pptoAdministrar(Http.Request request) {
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
		if(mapeoPermiso.get("pptoAdministrar")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			Map<Long,List<Double>> mapVtaArrTot = Ppto.mapTotalPptoPorAllBodega(con, s.baseDato);
			Map<Long,Long> dec = Moneda.numeroDecimal(con, s.baseDato);
			Long numDecimales = (long)0;
			if(dec!=null) {
				numDecimales = dec.get((long)1);
			}
			switch(numDecimales.toString()) {
			 case "0": myformatdouble = new DecimalFormat("#,##0",symbols); break;
			 case "2": myformatdouble = new DecimalFormat("#,##0.00",symbols); break;
			 case "4": myformatdouble = new DecimalFormat("#,##0.0000",symbols); break;
			 case "6": myformatdouble = new DecimalFormat("#,##0.000000",symbols); break;
			 default:  break;
			}
			List<List<String>> listBodegas = BodegaEmpresa.listaAllBodegasVigentesExternas(con, s.baseDato, mapeoPermiso, s.aplicaPorSucursal, s.id_sucursal);
			List<List<String>> lista = new ArrayList<List<String>>();
			for(int i=0; i<listBodegas.size(); i++) {
				List<String> aux = new ArrayList<String>();
				Long id_bodegaEmpresa = Long.parseLong(listBodegas.get(i).get(1));
				aux.add(listBodegas.get(i).get(1));			//0 id_bodegaEmpresa
				aux.add(listBodegas.get(i).get(5));			//1 nombre bodega
				aux.add(listBodegas.get(i).get(7));			//2 nick cliente
				aux.add(listBodegas.get(i).get(8));			//3 proyecto
				List<Double> vtaArrTot = mapVtaArrTot.get(id_bodegaEmpresa);
				if(mapVtaArrTot!=null && vtaArrTot!=null){
					aux.add(myformatdouble.format(vtaArrTot.get(0)));			//4 total venta
					aux.add(myformatdouble.format(vtaArrTot.get(1)));			//5 total arriendo
					aux.add(myformatdouble.format(vtaArrTot.get(2)));			//6 total total
				}else {
					aux.add(myformatdouble.format((double)0));			//4 total venta
					aux.add(myformatdouble.format((double)0));			//5 total arriendo
					aux.add(myformatdouble.format((double)0));			//6 total total
				}
				aux.add(listBodegas.get(i).get(16));			//7 nameSucursal
				lista.add(aux);
			}
			return ok(pptoAdministrar.render(mapeoDiccionario,mapeoPermiso,userMnu,lista, numDecimales));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}
	
	public Result pptoEditar(Http.Request request, Long id_bodegaEmpresa) {
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
		if(mapeoPermiso.get("pptoAdministrar")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<Ppto> listPpto = Ppto.allPorBodegaEmpresa(con, s.baseDato, id_bodegaEmpresa);
			BodegaEmpresa bodegaEmpresa = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
			Map<Long,Long> dec = Moneda.numeroDecimal(con, s.baseDato);
			Long numDecimales = (long)0;
			if(dec!=null) {
				numDecimales = dec.get((long)1);
			}
			return ok(pptoEditar.render(mapeoDiccionario,mapeoPermiso,userMnu,bodegaEmpresa,listPpto,numDecimales));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}
	
	public Result pptoAgregar(Http.Request request) {
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
				Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				BodegaEmpresa bodegaEmpresa = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
				List<Grupo> listGrupo = Grupo.all(con, s.baseDato);
				Map<Long,Long> dec = Moneda.numeroDecimal(con, s.baseDato);
				Long numDecimales = (long)0;
				if(dec!=null) {
					numDecimales = dec.get((long)1);
				}
				return ok(pptoAgregar.render(mapeoDiccionario,mapeoPermiso,userMnu,bodegaEmpresa,listGrupo,numDecimales));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
    	}
    }
	
	public Result pptoGrabar(Http.Request request) {
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
				Ppto ppto = new Ppto();
				ppto.id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa"));
				ppto.anioMes = form.get("anioMes");
				ppto.id_grupo = Long.parseLong(form.get("id_grupo"));
				ppto.esVenta = Long.parseLong(form.get("esVenta"));
				ppto.total = Double.parseDouble(form.get("total").replaceAll(",", ""));
				ppto.concepto = form.get("concepto");
				if(Ppto.grabarLinea(con, s.baseDato, ppto)){
					BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, ppto.id_bodegaEmpresa);
					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "ppto", (long)0, "insert", "agrega nueva linea presupuesto en bodega: "+bodega.getNombre());
					return redirect("/pptoEditar/"+ppto.id_bodegaEmpresa.toString());
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
	
	public Result pptoModificar(Http.Request request) {
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
				Long id_ppto = Long.parseLong(form.get("id_ppto"));
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				Ppto ppto = Ppto.findPorIdPpto(con, s.baseDato, id_ppto);
				BodegaEmpresa bodegaEmpresa = BodegaEmpresa.findXIdBodega(con, s.baseDato, ppto.id_bodegaEmpresa);
				List<Grupo> listGrupo = Grupo.all(con, s.baseDato);
				Map<Long,Long> dec = Moneda.numeroDecimal(con, s.baseDato);
				Long numDecimales = (long)0;
				if(dec!=null) {
					numDecimales = dec.get((long)1);
				}
				return ok(pptoModificar.render(mapeoDiccionario,mapeoPermiso,userMnu,ppto,bodegaEmpresa,listGrupo,numDecimales));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
    	}
    }
	
	public Result pptoUpdate(Http.Request request) {
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
				Ppto ppto = new Ppto();
				ppto.id = Long.parseLong(form.get("id"));
				ppto.id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa"));
				ppto.anioMes = form.get("anioMes");
				ppto.id_grupo = Long.parseLong(form.get("id_grupo"));
				ppto.esVenta = Long.parseLong(form.get("esVenta"));
				ppto.total = Double.parseDouble(form.get("total").replaceAll(",", ""));
				ppto.concepto = form.get("concepto");
				if(Ppto.updateLinea(con, s.baseDato, ppto)){
					BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, ppto.id_bodegaEmpresa);
					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "ppto", ppto.id, "update", "modifica linea presupuesto en bodega "+bodega.getNombre());
					return redirect("/pptoEditar/"+ppto.id_bodegaEmpresa.toString());
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
	
	public Result pptoEliminar(Http.Request request) {
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
				Long id_ppto = Long.parseLong(form.get("id_ppto"));
				Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa"));
				if(Ppto.deleteLinea(con, s.baseDato, id_ppto)){
					BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "ppto", id_ppto, "delete", "elimina linea presupuesto en bodega "+bodega.getNombre());
					return redirect("/pptoEditar/"+id_bodegaEmpresa.toString());
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
	
	//============================================================
    // MNU pptoReportes   Ppto/Reporte Pptos
    //============================================================
	
	public Result pptoListadoPptos(Http.Request request) {
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
		if(mapeoPermiso.get("pptoReportes")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
			permisoPorBodega = permisoPorBodega.replaceAll("movimiento", "bodegaEmpresa");
			permisoPorBodega = permisoPorBodega.replaceAll("id_bodegaEmpresa", "id");
			Map<Long,List<Double>> mapVtaArrTot = Ppto.mapTotalPptoPorAllBodega(con, s.baseDato);
			Map<Long,Long> dec = Moneda.numeroDecimal(con, s.baseDato);
			Long numDecimales = (long)0;
			if(dec!=null) {
				numDecimales = dec.get((long)1);
			}
			switch(numDecimales.toString()) {
			 case "0": myformatdouble = new DecimalFormat("#,##0",symbols); break;
			 case "2": myformatdouble = new DecimalFormat("#,##0.00",symbols); break;
			 case "4": myformatdouble = new DecimalFormat("#,##0.0000",symbols); break;
			 case "6": myformatdouble = new DecimalFormat("#,##0.000000",symbols); break;
			 default:  break;
			}
			List<List<String>> listBodegas = BodegaEmpresa.listaAllBodegasVigentesExternasFiltradas(con, s.baseDato, permisoPorBodega, s.aplicaPorSucursal, s.id_sucursal);
			List<List<String>> lista = new ArrayList<List<String>>();
			for(int i=0; i<listBodegas.size(); i++) {
				List<String> aux = new ArrayList<String>();
				Long id_bodegaEmpresa = Long.parseLong(listBodegas.get(i).get(1));
				aux.add(listBodegas.get(i).get(1));			//0 id_bodegaEmpresa
				aux.add(listBodegas.get(i).get(5));			//1 nombre bodega
				aux.add(listBodegas.get(i).get(7));			//2 nick cliente
				aux.add(listBodegas.get(i).get(8));			//3 proyecto
				List<Double> vtaArrTot = mapVtaArrTot.get(id_bodegaEmpresa);
				if(mapVtaArrTot!=null && vtaArrTot!=null){
					aux.add(myformatdouble.format(vtaArrTot.get(0)));			//4 total venta
					aux.add(myformatdouble.format(vtaArrTot.get(1)));			//5 total arriendo
					aux.add(myformatdouble.format(vtaArrTot.get(2)));			//6 total total
				}else {
					aux.add(myformatdouble.format((double)0));			//4 total venta
					aux.add(myformatdouble.format((double)0));			//5 total arriendo
					aux.add(myformatdouble.format((double)0));			//6 total total
				}
				aux.add(listBodegas.get(i).get(15));			//7 namesucursal
				lista.add(aux);
			}
			List<List<String>> lista2 = new ArrayList<List<String>>();
			for(List<String> x: lista) {
				Double aux = Double.parseDouble(x.get(6).replaceAll(",", ""));
				if(aux > (double)0) {
					lista2.add(x);
				}
			}
			return ok(pptoListadoPptos.render(mapeoDiccionario,mapeoPermiso,userMnu,lista2, numDecimales));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}
	
	public Result pptoVsRealxBodega(Http.Request request, Long id_bodegaEmpresa) {
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
		if(mapeoPermiso.get("pptoReportes")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<List<String>> listPpto = Ppto.pptoVsRealxBodega(con, s.baseDato, id_bodegaEmpresa, mapeoDiccionario.get("pais"));
			List<String> categoria = new ArrayList<String>();
			List<String> parcialPpto = new ArrayList<String>();
			List<String> acumPpto = new ArrayList<String>();
			List<String> parcialReal = new ArrayList<String>();
			List<String> acumReal = new ArrayList<String>();
			for(int i=0; i<listPpto.size(); i++) {
				categoria.add("'"+listPpto.get(i).get(0)+"'");
				parcialPpto.add(listPpto.get(i).get(1).replaceAll(",", ""));
				acumPpto.add(listPpto.get(i).get(2).replaceAll(",", ""));
				parcialReal.add(listPpto.get(i).get(3).replaceAll(",", ""));
				acumReal.add(listPpto.get(i).get(4).replaceAll(",", ""));
			}
			List<List<String>> listGrafico = new ArrayList<List<String>>();
			listGrafico.add(categoria);
			listGrafico.add(parcialPpto);
			listGrafico.add(acumPpto);
			listGrafico.add(parcialReal);
			listGrafico.add(acumReal);
			BodegaEmpresa bodegaEmpresa = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
			Map<Long,Long> dec = Moneda.numeroDecimal(con, s.baseDato);
			Long numDecimales = (long)0;
			if(dec!=null) {
				numDecimales = dec.get((long)1);
			}
			String moneda = mapeoDiccionario.get("MONEDA PRINCIPAL");
			return ok(pptoVsRealxBodega.render(mapeoDiccionario,mapeoPermiso,userMnu,bodegaEmpresa,listPpto,numDecimales,listGrafico,moneda));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}
	
}
