package controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;

import com.google.protobuf.MapEntry;
import models.calculo.*;
import models.reports.*;
import models.tables.*;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.TempFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import controllers.HomeController.Sessiones;
import models.api.ApiManagerDocDet;
import models.api.ApiManagerDocDoc;
import models.api.ApiNuboxDocDoc;
import models.api.ApiRelBase;
import models.api.ApiSapConconcreto;
import models.api.ApiSapSchwager;
import models.api.WebIConstruye;
import models.api.WebMaximise;
import models.forms.FormFactura;
import models.forms.FormGrafico;
import models.utilities.Archivos;
import models.utilities.DatabaseRead;
import models.utilities.DecimalFormato;
import models.utilities.Fechas;
import models.utilities.Registro;
import models.utilities.UserMnu;
import models.xml.FormXmlFactura;
import models.xml.XmlFacturaReferencias;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.Database;
import play.libs.Json;
import play.libs.Files.TemporaryFile;
import play.libs.mailer.Email;
import play.libs.mailer.MailerClient;
import play.libs.ws.WSClient;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.mensajes;
import viewsMnuOdo.html.reportProfProyDetalleOdo;
import viewsMnuReportes.html.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MnuReportes extends Controller {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private static final Database dbWrite = HomeController.dbWrite;
	private static final DatabaseRead dbRead = HomeController.dbRead;
	private static final FormFactory formFactory = HomeController.formFactory;
	private static final String msgError = HomeController.msgError;
	private static final String msgErrorFormulario = HomeController.msgErrorFormulario;
	private static final String msgSinPermiso = HomeController.msgSinPermiso;
	private static final String msgReport = HomeController.msgReport;

	private static WSClient ws;
	private final MailerClient mailerClient;

	@Inject
	public MnuReportes(WSClient ws, MailerClient mailerClient) {
		MnuReportes.ws = ws;
		this.mailerClient = mailerClient;
	}

	//====================================================================================
	// MNU reportInventarioGeneral   Reportes/Inventario/Arriendo/Arriendo: por equipo
	//====================================================================================

	public Result reportInventarioEquipoCorte(Http.Request request, String tipo) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		try {
			UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
			if(mapeoPermiso.get("reportInventarioGeneral")==null) {
				logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
				return ok(mensajes.render("/",msgSinPermiso));
			}
			Fechas hoy = Fechas.hoy();
			return ok(reportInventarioEquipoCorte.render(mapeoDiccionario,mapeoPermiso,userMnu,hoy.getFechaStrAAMMDD(),tipo));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result reportInventarioEquipo(Http.Request request) {
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
		} else {
			try (Connection con = dbRead.getConnection()) {
				UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
				String tipo = form.get("tipo").trim();
				String fechaCorte = form.get("fechaCorte").trim();
				Map<String, String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String, String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				Map<Long, List<Double>> mapPCompra = Compra.ultimoPrecio(con, s.baseDato);
				Map<Long, List<Double>> mapPLista = Precio.maestroPListaPorSucursal(con, s.baseDato, Long.parseLong(s.id_sucursal));
				Map<Long, String> moneda = Moneda.mapIdMonedaMoneda(con, s.baseDato);
				String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
				Map<Long, Double> tasasCorte = TasasCambio.mapTasasPorFecha(con, s.baseDato, fechaCorte, mapeoDiccionario.get("pais"));
				Map<Long, Long> dec = Moneda.numeroDecimal(con, s.baseDato);
				Map<Long, UnidadTiempo> mapUnidadTiempo = UnidadTiempo.mapUnidadTiempo(con, s.baseDato);
				Map<Long, Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, s.baseDato);
				Map<Long, Equipo> mapEquipo = Equipo.mapAllAll(con, s.baseDato);
				List<List<String>> datos = ReportInventarios.reportInventarioEquipo(con, s.baseDato, fechaCorte, permisoPorBodega, mapPCompra, mapPLista, moneda,
						tipo, mapeoDiccionario, s.aplicaPorSucursal, s.id_sucursal, mapEquipo, mapSucursal, mapUnidadTiempo, tasasCorte, dec);
				return ok(reportInventarioEquipo.render(mapeoDiccionario, mapeoPermiso, userMnu, datos, fechaCorte, tipo));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reportInventarioGeneralXEquipo(Http.Request request) {
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
				Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
				String tipo = form.get("tipo").trim();
				String fechaCorte = form.get("fechaCorte").trim();
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				Map<Long,List<Double>> mapPCompra = Compra.ultimoPrecio(con, s.baseDato);
				Map<Long,List<Double>> mapPLista = Precio.maestroPListaPorSucursal(con, s.baseDato, Long.parseLong(s.id_sucursal));
				Map<Long,String> moneda = Moneda.mapIdMonedaMoneda(con, s.baseDato);
				Equipo equipo = Equipo.find(con, s.baseDato, id_equipo);
				List<List<String>> datos = ReportInventarios.reportInventarioGeneralXEquipo(con, s.baseDato, equipo, fechaCorte, tipo,
						mapPCompra, mapPLista, moneda, mapeoDiccionario, s.aplicaPorSucursal, s.id_sucursal);
				return ok(reportInventarioGeneralXEquipo.render(mapeoDiccionario,mapeoPermiso,userMnu,datos,fechaCorte,tipo));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reportInventTrazaEquipoEnBod(Http.Request request) {
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
				Long id_bodega = Long.parseLong(form.get("id_bodega").trim());
				Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
				String tipo = form.get("tipo").trim();
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				List<List<String>> datos = ReportInventarios.trazaEquipoEnBodega(con, s.baseDato, id_bodega, id_equipo, tipo, mapeoDiccionario);
				BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodega);
				return ok(reportInventTrazaEquipoEnBod.render(mapeoDiccionario,mapeoPermiso,userMnu,bodega, datos, tipo));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reportInventarioEquipoExcel(Http.Request request) {
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
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
			String tipo = null;
			String fechaCorte = null;
			List<List<String>> datos = null;
			try (Connection con = dbRead.getConnection()){
				tipo = form.get("tipo").trim();
				fechaCorte = form.get("fechaCorte").trim();
				Map<Long,List<Double>> mapPCompra = Compra.ultimoPrecio(con, s.baseDato);
				Map<Long,List<Double>> mapPLista = Precio.maestroPListaPorSucursal(con, s.baseDato, Long.parseLong(s.id_sucursal));
				Map<Long,String> moneda = Moneda.mapIdMonedaMoneda(con, s.baseDato);
				String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
				Map<Long,Double> tasasCorte = TasasCambio.mapTasasPorFecha(con, s.baseDato,fechaCorte, mapeoDiccionario.get("pais"));
				Map<Long,Long> dec = Moneda.numeroDecimal(con, s.baseDato);
				Map<Long,UnidadTiempo> mapUnidadTiempo = UnidadTiempo.mapUnidadTiempo(con, s.baseDato);
				Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, s.baseDato);
				Map<Long,Equipo> mapEquipo = Equipo.mapAllAll(con, s.baseDato);
				datos = ReportInventarios.reportInventarioEquipo(con, s.baseDato, fechaCorte, permisoPorBodega, mapPCompra, mapPLista, moneda,
						tipo, mapeoDiccionario, s.aplicaPorSucursal, s.id_sucursal, mapEquipo, mapSucursal, mapUnidadTiempo, tasasCorte, dec);
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
			try {
				File file = ReportInventarios.reportInventarioGeneralExcel(s.baseDato, datos, fechaCorte, mapeoDiccionario, tipo);
				return ok(file,false,Optional.of("InventarioPorEquipo.xlsx"));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	//====================================================================================
	// MNU reportInventarioGeneral  EQUIPO-BODEGA
	//====================================================================================

	public Result reportEquipoBodegaCorte(Http.Request request, String tipo) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("reportInventarioGeneral")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try {
			UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
			Fechas hoy = Fechas.hoy();
			return ok(reportEquipoBodegaCorte.render(mapeoDiccionario,mapeoPermiso,userMnu,hoy.getFechaStrAAMMDD(),tipo));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result reportEquipoBodega(Http.Request request) {
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
				String tipo = form.get("tipo").trim();
				String fechaCorte = form.get("fechaCorte").trim();
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				Map<Long,List<Double>> mapPCompra = Compra.ultimoPrecio(con, s.baseDato);
				Map<Long,List<Double>> mapPLista = Precio.maestroPListaPorSucursal(con, s.baseDato, Long.parseLong(s.id_sucursal));
				Map<Long,String> moneda = Moneda.mapIdMonedaMoneda(con, s.baseDato);
				String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
				Map<Long,Double> tasasCorte = TasasCambio.mapTasasPorFecha(con, s.baseDato,fechaCorte, mapeoDiccionario.get("pais"));
				Map<Long,Long> dec = Moneda.numeroDecimal(con, s.baseDato);
				Map<Long,UnidadTiempo> mapUnidadTiempo = UnidadTiempo.mapUnidadTiempo(con, s.baseDato);
				Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, s.baseDato);
				Map<Long,Equipo> mapEquipo = Equipo.mapAllAll(con, s.baseDato);
				List<List<String>> datos = ReportInventarios.reportInventarioEquipoDesagrupado(con, s.baseDato, fechaCorte, permisoPorBodega, mapPCompra, mapPLista, moneda,
						tipo, mapeoDiccionario, s.aplicaPorSucursal, s.id_sucursal, mapEquipo, mapSucursal, mapUnidadTiempo, tasasCorte, dec);
				return ok(reportEquipoBodega.render(mapeoDiccionario,mapeoPermiso,userMnu,datos,fechaCorte,tipo));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reportEquipoBodegaXEquipo(Http.Request request) {
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
				Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
				String tipo = form.get("tipo").trim();
				String fechaCorte = form.get("fechaCorte").trim();
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				Map<Long,List<Double>> mapPCompra = Compra.ultimoPrecio(con, s.baseDato);
				Map<Long,List<Double>> mapPLista = Precio.maestroPListaPorSucursal(con, s.baseDato, Long.parseLong(s.id_sucursal));
				Map<Long,String> moneda = Moneda.mapIdMonedaMoneda(con, s.baseDato);
				Equipo equipo = Equipo.find(con, s.baseDato, id_equipo);
				List<List<String>> datos = ReportInventarios.reportInventarioGeneralXEquipo(con, s.baseDato, equipo, fechaCorte, tipo,
						mapPCompra, mapPLista, moneda, mapeoDiccionario, s.aplicaPorSucursal, s.id_sucursal);
				return ok(reportEquipoBodegaXEquipo.render(mapeoDiccionario,mapeoPermiso,userMnu,datos,fechaCorte,tipo));
			}catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reportEquipoBodegaExcel(Http.Request request) {
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
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
			String tipo = null;
			String fechaCorte = null;
			List<List<String>> datos = null;
			try (Connection con = dbRead.getConnection()){
				tipo = form.get("tipo").trim();
				fechaCorte = form.get("fechaCorte").trim();
				Map<Long,List<Double>> mapPCompra = Compra.ultimoPrecio(con, s.baseDato);
				Map<Long,List<Double>> mapPLista = Precio.maestroPListaPorSucursal(con, s.baseDato, Long.parseLong(s.id_sucursal));
				Map<Long,String> moneda = Moneda.mapIdMonedaMoneda(con, s.baseDato);
				String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
				Map<Long,Double> tasasCorte = TasasCambio.mapTasasPorFecha(con, s.baseDato,fechaCorte, mapeoDiccionario.get("pais"));
				Map<Long,Long> dec = Moneda.numeroDecimal(con, s.baseDato);
				Map<Long,UnidadTiempo> mapUnidadTiempo = UnidadTiempo.mapUnidadTiempo(con, s.baseDato);
				Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, s.baseDato);
				Map<Long,Equipo> mapEquipo = Equipo.mapAllAll(con, s.baseDato);
				datos = ReportInventarios.reportInventarioEquipoDesagrupado(con, s.baseDato, fechaCorte, permisoPorBodega, mapPCompra, mapPLista, moneda,
						tipo, mapeoDiccionario, s.aplicaPorSucursal, s.id_sucursal, mapEquipo, mapSucursal, mapUnidadTiempo, tasasCorte, dec);
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
			try {
				File file = ReportInventarios.reportInvGeneralExcelDesagrupado(s.baseDato, datos, fechaCorte, mapeoDiccionario, tipo);
				return ok(file,false,Optional.of("InventarioPorEquipoBodega.xlsx"));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reportEquipoBodegaTrazaEquipoEnBod(Http.Request request) {
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
				Long id_bodega = Long.parseLong(form.get("id_bodega").trim());
				Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
				String tipo = form.get("tipo").trim();
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				List<List<String>> datos = ReportInventarios.trazaEquipoEnBodega(con, s.baseDato, id_bodega, id_equipo, tipo, mapeoDiccionario);
				BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodega);
				return ok(reportEquipoBodegaTrazaEquipoEnBod.render(mapeoDiccionario,mapeoPermiso,userMnu,bodega, datos, tipo));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}


	//====================================================================================
	// MNU reportInventarioSelectivo   Reportes/Inventario/Arriendo/Arriendo: por bodega
	//====================================================================================

	public Result reportInventarioBodegaCorte(Http.Request request, String tipo) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("reportInventarioSelectivo")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try {
			UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
			Fechas hoy = Fechas.hoy();
			return ok(reportInventarioBodegaCorte.render(mapeoDiccionario,mapeoPermiso,userMnu,hoy.getFechaStrAAMMDD(),tipo));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result reportInventarioBodega(Http.Request request) {
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
				String tipo = form.get("tipo").trim();
				String fechaCorte = form.get("fechaCorte").trim();
				Long soloVigentes = Long.parseLong(form.get("soloVigentes").trim());
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
				List<List<String>> datos = ReportInventarios.reportInventarioGeneralXBodega(con, s.baseDato, fechaCorte, tipo,
						soloVigentes, permisoPorBodega, mapeoDiccionario, s.aplicaPorSucursal, s.id_sucursal);
				return ok(reportInventarioBodega.render(mapeoDiccionario,mapeoPermiso,userMnu,datos,fechaCorte,tipo));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reportInventarioSelectivoXBodega(Http.Request request) {
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
				Long id_bodega = Long.parseLong(form.get("id_bodega").trim());
				String tipo = form.get("tipo").trim();  // tipo es VENTA o ARRIENDO o
				String fechaCorte = form.get("fechaCorte").trim();
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodega);
				List<List<String>> datos = ReportInventarios.reportInventarioSelectivoXBodega(con, s.baseDato, bodega, fechaCorte, tipo, mapeoDiccionario);
				return ok(reportInventarioSelectivoXBodega.render(mapeoDiccionario,mapeoPermiso,userMnu,bodega,datos,fechaCorte,tipo));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reportInventProyectoTrazaEquipoEnBod(Http.Request request) {
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
				Long id_bodega = Long.parseLong(form.get("id_bodega").trim());
				Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
				Long id_cotizacion = Long.parseLong(form.get("id_cotizacion").trim());
				String tipo = form.get("tipo").trim();
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodega);
				List<List<String>> datos = ReportInventarios.trazaEquipoProyectoEnBodega(con, s.baseDato, id_bodega, id_equipo, id_cotizacion, tipo, mapeoDiccionario);
				return ok(reportInventTrazaEquipoEnBod.render(mapeoDiccionario,mapeoPermiso,userMnu,bodega, datos, tipo));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reportInventarioSelectivoXBodegaExcel(Http.Request request) {
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
			String tipo = null;
			String fechaCorte = null;
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
			BodegaEmpresa bodega = null;
			List<List<String>> datos = null;
			try (Connection con = dbRead.getConnection()){
				Long id_bodega = Long.parseLong(form.get("id_bodega").trim());
				tipo = form.get("tipo").trim();
				fechaCorte = form.get("fechaCorte").trim();
				bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodega);
				datos = ReportInventarios.reportInventarioSelectivoXBodega(con, s.baseDato, bodega, fechaCorte, tipo, mapeoDiccionario);
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
			try {
				File file = ReportInventarios.exportaReportInventarioSelectivoXBodega(s.baseDato, datos, fechaCorte, mapeoDiccionario, bodega, tipo);
				return ok(file,false,Optional.of("InventarioPorBodega.xlsx"));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	//====================================================================================
	// MNU reportInventarioGrupo   Reportes/Inventario/Arriendo/Arriendo: por grupo
	//====================================================================================

	public Result reportInventarioGrupoCorte(Http.Request request, String tipo) {
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
		if(mapeoPermiso.get("reportInventarioGrupo")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		Fechas hoy = Fechas.hoy();
		return ok(reportInventarioGrupoCorte.render(mapeoDiccionario,mapeoPermiso,userMnu,hoy.getFechaStrAAMMDD(),tipo));
	}

	public Result reportInventarioGrupo(Http.Request request) {
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
				String tipo = form.get("tipo").trim();
				String fechaCorte = form.get("fechaCorte").trim();
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				List<Grupo> grupos = Grupo.all(con, s.baseDato);
				return ok(reportInventarioGrupo.render(mapeoDiccionario,mapeoPermiso,userMnu,grupos,fechaCorte,tipo));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reportInventarioSelectivoXGrupo(Http.Request request) {
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
				Long id_grupo = Long.parseLong(form.get("id_grupo").trim());
				String tipo = form.get("tipo").trim();
				String fechaCorte = form.get("fechaCorte").trim();
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
				List<List<String>> datos = ReportInventarios.reportInventarioSelectivoXGrupo(con, s.baseDato, id_grupo, fechaCorte,
						permisoPorBodega, tipo, mapeoDiccionario, s.aplicaPorSucursal, s.id_sucursal);
				Grupo grupo = Grupo.find(con, s.baseDato, id_grupo);
				return ok(reportInventarioSelectivoXGrupo.render(mapeoDiccionario,mapeoPermiso,userMnu,grupo,datos,fechaCorte,tipo));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reportInventarioSelectivoXGrupoExcel(Http.Request request) {
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
			String tipo = null;
			String fechaCorte = null;
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
			Grupo grupo = null;
			List<List<String>> datos = null;
			try (Connection con = dbRead.getConnection()){
				Long id_grupo = Long.parseLong(form.get("id_grupo").trim());
				tipo = form.get("tipo").trim();
				fechaCorte = form.get("fechaCorte").trim();
				grupo = Grupo.find(con, s.baseDato, id_grupo);
				String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
				datos = ReportInventarios.reportInventarioSelectivoXGrupo(con, s.baseDato, id_grupo, fechaCorte,
						permisoPorBodega, tipo, mapeoDiccionario, s.aplicaPorSucursal, s.id_sucursal);
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
			try {
				File file = ReportInventarios.exportaReportInventarioSelectivoXGrupo(s.baseDato, datos, fechaCorte, mapeoDiccionario, grupo, tipo);
				return ok(file,false,Optional.of("InventarioPorGrupo.xlsx"));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	//====================================================================================
	// MNU inventarioMatriz   Reportes/Inventario/Arriendo/Arriendo: Equipos por Bodegas
	//====================================================================================

	public Result reportInventarioMatrizCorte(Http.Request request, String tipo) {
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
		if(mapeoPermiso.get("inventarioMatriz")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			Fechas hoy = Fechas.hoy();
			List<Sucursal> listSucursal = new ArrayList<Sucursal>();
			if(s.aplicaPorSucursal.equals("1")) {
				Sucursal sucursal = Sucursal.find(con, s.baseDato, s.id_sucursal);
				listSucursal.add(sucursal);
			}else {
				listSucursal = Sucursal.all(con, s.baseDato);
			}
			return ok(reportInventarioMatrizCorte.render(mapeoDiccionario,mapeoPermiso,userMnu,hoy.getFechaStrAAMMDD(),tipo, listSucursal));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result reportInventarioMatriz(Http.Request request) {
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
				String tipo = form.get("tipo").trim();
				String fechaCorte = form.get("fechaCorte").trim();
				String id_sucursal = form.get("id_sucursal").trim();
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
				List<List<String>> listaBodegas = Inventarios.listaBodegasConStock(con, s.baseDato, fechaCorte,
						permisoPorBodega, "1", id_sucursal, tipo);
				if(tipo.equals("VENTA")) {
					List<List<String>> aux = new ArrayList<List<String>>();
					for(List<String> l: listaBodegas) {
						if(!l.get(2).equals("1")) {
							aux.add(l);
						}
					}
					listaBodegas = aux;
				}
				List<List<String>> aux = new ArrayList<List<String>>();
				for(List<String> l: listaBodegas) {
					if(l.get(3).equals("1")) {
						aux.add(l);
					}
				}
				listaBodegas = aux;
				aux = null;
				List<String> titulos1 = ReportInventarios.listaMatrizEquiposTitulos1(listaBodegas, tipo, mapeoDiccionario);
				List<String> titulos2 = ReportInventarios.listaMatrizEquiposTitulos2(listaBodegas);
				List<String> titulos3 = ReportInventarios.listaMatrizEquiposTitulos3(listaBodegas);
				List<List<String>> datos = ReportInventarios.listaMatrizEquipos(con, s.baseDato, permisoPorBodega, tipo, fechaCorte, listaBodegas, mapeoDiccionario);
				Sucursal sucursal = new Sucursal();
				sucursal.setId((long)0);
				sucursal.setNombre("TODAS");
				if( ! id_sucursal.equals("0")) {
					sucursal = Sucursal.find(con, s.baseDato, id_sucursal);
				}
				if(mapeoDiccionario.get("nEmpresa").equals("SM8 DE MEXICO")) {
					File file = ReportInventarios.exportaReportInventarioMatriz(s.baseDato, datos, titulos3, titulos2, tipo, fechaCorte, mapeoDiccionario, sucursal.getNombre());
					return ok(file,false,Optional.of("InventarioMatriz.xlsx"));
				}
				return ok(reportInventarioMatriz.render(mapeoDiccionario,mapeoPermiso,userMnu,datos,titulos1,titulos2,tipo,fechaCorte,sucursal));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reportInventarioMatrizExcel(Http.Request request) {
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
			List<String> titulos2 = null;
			List<String> titulos3 = null;
			List<List<String>> datos = null;
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
			String tipo = null;
			String fechaCorte = null;
			Sucursal sucursal = new Sucursal();
			try (Connection con = dbRead.getConnection()){
				tipo = form.get("tipo").trim();
				fechaCorte = form.get("fechaCorte").trim();
				String id_sucursal = form.get("id_sucursal").trim();
				String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
				List<List<String>> listaBodegas = Inventarios.listaBodegasConStock(con, s.baseDato, fechaCorte,
						permisoPorBodega, "1", id_sucursal, tipo);
				if(tipo.equals("VENTA")) {
					List<List<String>> aux = new ArrayList<List<String>>();
					for(List<String> l: listaBodegas) {
						if(!l.get(2).equals("1")) {
							aux.add(l);
						}
					}
					listaBodegas = aux;
				}
				List<List<String>> aux = new ArrayList<List<String>>();
				for(List<String> l: listaBodegas) {
					if(l.get(3).equals("1")) {
						aux.add(l);
					}
				}
				listaBodegas = aux;
				aux = null;
				titulos2 = ReportInventarios.listaMatrizEquiposTitulos2(listaBodegas);
				titulos3 = ReportInventarios.listaMatrizEquiposTitulos3(listaBodegas);
				datos = ReportInventarios.listaMatrizEquipos(con, s.baseDato, permisoPorBodega, tipo, fechaCorte, listaBodegas, mapeoDiccionario);
				sucursal.setId((long)0);
				sucursal.setNombre("TODAS");
				if( ! id_sucursal.equals("0")) {
					sucursal = Sucursal.find(con, s.baseDato, id_sucursal);
				}
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
			try {
				File file = ReportInventarios.exportaReportInventarioMatriz(s.baseDato, datos, titulos3, titulos2, tipo, fechaCorte, mapeoDiccionario, sucursal.getNombre());
				return ok(file,false,Optional.of("InventarioMatriz.xlsx"));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	//====================================================================================
	// MNU inventarioStock   Reportes/Inventario/Arriendo/Todos: Equipos con Stock y Precios
	//====================================================================================

	public Result reportInventarioTodo(Http.Request request) {
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
		if(mapeoPermiso.get("inventarioStock")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<List<String>> datos = ReportInventarios.listaFullEquipos(con, s.baseDato);
			return ok(reportInventarioTodo.render(mapeoDiccionario,mapeoPermiso,userMnu,datos));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result reportInventarioTodoExcel(Http.Request request) {
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
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
			List<List<String>> datos = null;
			try (Connection con = dbRead.getConnection()){
				datos = ReportInventarios.listaFullEquipos(con, s.baseDato);
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
			try {
				File file = ReportInventarios.exportaReportInventarioTodo(s.baseDato, datos, mapeoDiccionario);
				return ok(file,false,Optional.of("TodoElInventario.xlsx"));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	//====================================================================================
	// MNU inventarioStock   Reportes/Inventario/Estado Bodegas
	//====================================================================================

	public Result reportEstadoBodegas(Http.Request request) {
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
		if(mapeoPermiso.get("reportInventarioGeneral")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<List<String>> datos = ReportBodegas.estadoBodegas(con, s.baseDato);
			return ok(reportEstadoBodegas.render(mapeoDiccionario,mapeoPermiso,userMnu,datos));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result reportEstadoBodegasExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgErrorFormulario));
		}else {
			List<List<String>> datos = null;
			try (Connection con = dbRead.getConnection()){
				datos = ReportBodegas.estadoBodegas(con, s.baseDato);
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
			try {
				File file = ReportBodegas.estadoBodegasExcel(s.baseDato, mapeoDiccionario, datos);
				return ok(file,false,Optional.of("ReporteEstadoBodegas.xlsx"));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	//====================================================================================
	// MNU reportInventarioProyecto   Reportes/Existencias
	//====================================================================================

	public Result reportInventarioProyecto(Http.Request request) {
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
		if(mapeoPermiso.get("reportInventarioProyecto")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
			List<List<String>> datos = ReportInventarios.reportInventarioProyecto(con, s.baseDato, permisoPorBodega, s.aplicaPorSucursal, s.id_sucursal);
			return ok(reportInventarioProyecto.render(mapeoDiccionario,mapeoPermiso,userMnu,datos));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result reportInventarioProyectoDetalle(Http.Request request) {
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
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgErrorFormulario));
		}else {
			try (Connection con = dbRead.getConnection()){
				Long id_bodega = Long.parseLong(form.get("id_bodega").trim());
				BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodega);
				List<List<String>> datos = ReportInventarios.reportInventarioProyectoDetalle(con, s.baseDato, bodega.getId(), mapeoDiccionario);
				return ok(reportInventarioProyectoDetalle.render(mapeoDiccionario,mapeoPermiso,userMnu,bodega,datos));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reportInventarioProyectoDetalleExcel(Http.Request request) {
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
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
			BodegaEmpresa bodega = null;
			List<List<String>> datos = null;
			try (Connection con = dbRead.getConnection()){
				Long id_bodega = Long.parseLong(form.get("id_bodega").trim());
				bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodega);
				datos = ReportInventarios.reportInventarioProyectoDetalle(con, s.baseDato, bodega.getId(), mapeoDiccionario);
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
			try {
				File file = ReportInventarios.exportaReportInventarioProyectoDetalle(s.baseDato, datos, mapeoDiccionario, bodega);
				return ok(file,false,Optional.of("ExistenciasPorBodega.xlsx"));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	//====================================================================================
	// MNU reporteMovimientos0   Reportes/Movimientos/Por Fecha (Agrupado)
	//====================================================================================

	public Result reporteMovimientosPeriodoAgrupado(Http.Request request) {
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
		if(mapeoPermiso.get("reporteMovimientos0")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try {
			Fechas hoy = Fechas.hoy();
			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			return ok(reporteMovimientosPeriodoAgrupado.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result reporteMovimientosListaProyectosAgrupado(Http.Request request) {
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
			String desdeAAMMDD = form.get("fechaDesde").trim();
			String hastaAAMMDD = form.get("fechaHasta").trim();
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
			Map<Long,Double> tasas = null;
			Map<String, Double> mapFijaTasas = null;
			List<Long> listIdBodegaEmpresa = null;
			Map<Long,Calc_BodegaEmpresa> mapBodegaEmpresa = null;
			Map<String,Calc_Precio> mapPrecios = null;
			Map<Long,Calc_Precio> mapMaestroPrecios = null;
			List<Long> listIdGuia_fechaCorte = null;
			List<Inventarios> inventario = null;
			List<Inventarios> guiasPer = null;
			Map<String,String> mapPermanencias = null;
			Map<Long,List<String>> mapBodega = null;
			List<Calc_AjustesEP> listaAjustes = null;
			Map<Long,Long> dec = null;
			Map<String,String> map = null;
			try (Connection con = dbRead.getConnection()){
				Fechas hoy = Fechas.hoy();
				tasas = TasasCambio.mapTasasPorFecha(con, s.baseDato,hoy.getFechaStrAAMMDD(), mapeoDiccionario.get("pais"));
				String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
				listIdBodegaEmpresa = ModCalc_InvInicial.listIdBodegaEmpresa(con, s.baseDato, permisoPorBodega);
				mapBodegaEmpresa = Calc_BodegaEmpresa.mapAllBodegasVigentes(con, s.baseDato);
				mapPrecios = Calc_Precio.mapPrecios(con, s.baseDato, listIdBodegaEmpresa);
				mapMaestroPrecios = Calc_Precio.mapMaestroPrecios(con, s.baseDato);
				mapFijaTasas = BodegaEmpresa.mapFijaTasasAll(con, s.baseDato);
				listIdGuia_fechaCorte = ModCalc_InvInicial.listIdGuia_fechaCorte(con, s.baseDato, desdeAAMMDD);
				inventario = Inventarios.inventario(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_fechaCorte);
				List<Long> listIdGuia_entreFechas = ModCalc_GuiasPer.listIdGuia_entreFecha(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
				guiasPer = Inventarios.guiasPer(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_entreFechas);
				listaAjustes = Calc_AjustesEP.listaAjustesEntreFechas(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
				mapBodega = BodegaEmpresa.mapIdBod_BodegaEmpresaInternasExternas(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
				dec = Moneda.numeroDecimal(con, s.baseDato);
				map = UsuarioPermiso.mapPermisoIdBodega(con, s.baseDato, Long.parseLong(s.id_usuario));
				mapPermanencias = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, s.baseDato);
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
			try {
				ReportFacturas reporte = ModCalc_InvInicial.resumenInvInicial(s.baseDato,desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa,
						mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, listIdGuia_fechaCorte, inventario);
				inventario = null;
				listIdGuia_fechaCorte = null;
				List<ModCalc_InvInicial> inventarioInicial = reporte.resumenInvInicial;
				reporte = null;
				listIdBodegaEmpresa = null;
				List<ModCalc_GuiasPer> guiasPeriodo = ModCalc_GuiasPer.resumenGuiasPer(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas,
						mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, guiasPer, mapPermanencias);
				mapBodegaEmpresa = null;
				mapPrecios = null;
				mapMaestroPrecios = null;
				guiasPer = null;
				mapPermanencias = null;
				List<ModeloCalculo> listado = ModeloCalculo.valorTotalporBodega(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, inventarioInicial,guiasPeriodo, listaAjustes);
				guiasPeriodo = null;
				inventarioInicial = null;
				tasas = null;
				mapFijaTasas = null;
				listaAjustes = null;
				List<List<String>> proyectosAux = ReportFacturas.reportFacturaProyecto(listado, mapBodega);
				mapBodega = null;
				List<List<String>> resumenTotales = ReportFacturas.resumenTotalesPorProyecto(listado, dec);
				listado = null;
				dec = null;
				List<List<String>> proyectos = new ArrayList<List<String>>();
				if(!map.isEmpty()) {
					for(List<String> aux:proyectosAux) {
						String idBodega = map.get(aux.get(1));
						if(idBodega!=null) {
							proyectos.add(aux);
						}
					}
				}else {
					proyectos = proyectosAux;
				}
				proyectosAux = null;
				map = null;
				Map<String,List<String>> mapTotal = new HashMap<String,List<String>>();
				for(List<String> total: resumenTotales){
					mapTotal.put(total.get(0), total);
				}
				resumenTotales = null;
				List<List<String>> datos = new ArrayList<List<String>>();
				for(List<String> lista1: proyectos){
					List<String> x = mapTotal.get(lista1.get(1));
					if(x!=null){
						List<String> aux = new ArrayList<String>();
						aux.add(lista1.get(0));  // 0 es cliente interno
						aux.add(lista1.get(1));  	// 1 idbodega empresa
						aux.add(lista1.get(2));  	// 2 id de cliente
						aux.add(lista1.get(3));  	// 3 id del proyecto
						aux.add("");  				// 4 tipo de cliente interno o externo
						aux.add(lista1.get(5));  	// 5 nombre bodega o empresa
						aux.add(lista1.get(6));  	// 6 rut del cliente
						aux.add(lista1.get(7));  	// 7 nombre del cliente
						aux.add(lista1.get(8));  	// 8 nombre del proyecto
						aux.add(lista1.get(9));  	// 9 comuna
						aux.add("");  				// 10 factorViga
						aux.add(lista1.get(14)); 	// 11 nameSucursal
						datos.add(aux);
					}
				}
				return ok(reporteMovimientosListaProyectosAgrupado.render(mapeoDiccionario,mapeoPermiso,userMnu,datos, desdeAAMMDD,hastaAAMMDD));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}

	}

	public Result reporteMovimientosDetalleAgrupado(Http.Request request) {
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
				Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodega").trim());
				String fechaDesde = form.get("fechaDesde").trim();
				String fechaHasta = form.get("fechaHasta").trim();
				String esVenta = form.get("esVenta").trim();
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				List<List<String>> datos = ReportMovimientos.movimientoGuiasAgrupado(con, s.baseDato, id_bodegaEmpresa, esVenta, fechaDesde, fechaHasta);
				BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
				String concepto = mapeoDiccionario.getOrDefault("ARRIENDO","ARRIENDO");
				if("1".equals(esVenta)) {
					concepto = "VENTA";
				}
				return ok(reporteMovimientosDetalleAgrupado.render(mapeoDiccionario,mapeoPermiso,userMnu,datos,bodega,esVenta,concepto,fechaDesde,fechaHasta));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reporteMovimientosDetalleAgrupadoExcel(Http.Request request) {
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
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
			String fechaDesde = form.get("fechaDesde").trim();
			String fechaHasta = form.get("fechaHasta").trim();
			String concepto = mapeoDiccionario.get("ARRIENDO");
			List<List<String>> datos = null;
			BodegaEmpresa bodega = null;
			try (Connection con = dbRead.getConnection()){
				Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
				fechaDesde = form.get("fechaDesde").trim();
				fechaHasta = form.get("fechaHasta").trim();
				String esVenta = form.get("esVenta").trim();
				datos = ReportMovimientos.movimientoGuiasAgrupado(con, s.baseDato, id_bodegaEmpresa, esVenta, fechaDesde, fechaHasta);
				bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
				if("1".equals(esVenta)) {
					concepto = "VENTA";
				}
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
			try {
				File file = ReportMovimientos.movimientosExcelAgrupado(s.baseDato, datos, mapeoDiccionario, bodega, concepto, fechaDesde, fechaHasta);
				return ok(file,false,Optional.of("MovimientosPorBodegaAgrupado.xlsx"));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	//====================================================================================
	// MNU reporteMovimientos0   Reportes/Movimientos/SoloBodegas Internas
	//====================================================================================

	public Result reporteMovSoloBodInternas0(Http.Request request) {
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
		if(mapeoPermiso.get("reporteMovimientos0")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()) {
			Fechas hoy = Fechas.hoy();
			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			return ok(reporteMovSoloBodInternas0.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result reporteMovSoloBodInternas1(Http.Request request) {
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
			try (Connection con = dbRead.getConnection()) {
				String fechaDesde = form.get("fechaDesde").trim();
				String fechaHasta = form.get("fechaHasta").trim();
				Map<String, String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String, String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
				List<List<String>> datos = ReportInventarios.reportInventarioSoloBodegasInternas(con, s.baseDato, permisoPorBodega, s.aplicaPorSucursal, s.id_sucursal);
				return ok(reporteMovSoloBodInternas1.render(mapeoDiccionario, mapeoPermiso, userMnu, datos, fechaDesde, fechaHasta));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reporteMovSoloBodInternas2(Http.Request request) {
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
			try (Connection con = dbRead.getConnection()) {
				Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodega").trim());
				String fechaDesde = form.get("fechaDesde").trim();
				String fechaHasta = form.get("fechaHasta").trim();
				Map<String, String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String, String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				List<List<String>> datos = ReportMovimientos.movimientoGuiasSoloBodInternas(con, s.baseDato, id_bodegaEmpresa, fechaDesde, fechaHasta);
				BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
				return ok(reporteMovSoloBodInternas2.render(mapeoDiccionario, mapeoPermiso, userMnu, datos, bodega, fechaDesde, fechaHasta));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reporteMovSoloBodInternas2Excel(Http.Request request) {
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
			Map<String, String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
			List<List<String>> datos = null;
			BodegaEmpresa bodega = null;
			String fechaDesde = null;
			String fechaHasta = null;
			try (Connection con = dbRead.getConnection()) {
				Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
				fechaDesde = form.get("fechaDesde").trim();
				fechaHasta = form.get("fechaHasta").trim();
				datos = ReportMovimientos.movimientoGuiasSoloBodInternas(con, s.baseDato, id_bodegaEmpresa, fechaDesde, fechaHasta);
				bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
			try {
				File file = ReportMovimientos.reporteMovSoloBodInternas3Excel(s.baseDato, datos, mapeoDiccionario, bodega, fechaDesde, fechaHasta);
				return ok(file, false, Optional.of("MovimientosPorBodegaInterna.xlsx"));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	//====================================================================================
	// MNU reporteMovimientos0   Reportes/Movimientos/Por proyecto (Agrupado)
	//====================================================================================

	public Result reportePorProyectoAgrupado(Http.Request request) {
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
		if(mapeoPermiso.get("reporteMovimientos0")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try {
			Fechas hoy = Fechas.hoy();
			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			return ok(reportePorProyectoAgrupado.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result reportePorProyectoListaAgrupado(Http.Request request) {
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
			try (Connection con = dbRead.getConnection()) {
				String fechaDesde = form.get("fechaDesde").trim();
				String fechaHasta = form.get("fechaHasta").trim();
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
				List<List<String>> datos = ReportInventarios.listaProyectosAsignados(con, s.baseDato, permisoPorBodega, s.aplicaPorSucursal, s.id_sucursal);
				return ok(reportePorProyectoListaAgrupado.render(mapeoDiccionario,mapeoPermiso,userMnu,datos,fechaDesde,fechaHasta));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reportePorProyectoDetalleAgrupado(Http.Request request) {
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
			try (Connection con = dbRead.getConnection()) {
				Long id_proyecto = Long.parseLong(form.get("id_proyecto").trim());
				String fechaDesde = form.get("fechaDesde").trim();
				String fechaHasta = form.get("fechaHasta").trim();
				String esVenta = form.get("esVenta").trim();
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
				List<List<String>> datos = ReportMovimientos.movimientoGuiasPorProyecto(con, s.baseDato, id_proyecto, esVenta, fechaDesde, fechaHasta,
						permisoPorBodega, s.aplicaPorSucursal, s.id_sucursal, mapeoDiccionario);
				Proyecto proyecto = Proyecto.find(con, s.baseDato, id_proyecto);
				String concepto = mapeoDiccionario.get("ARRIENDO");
				if("1".equals(esVenta)) {
					concepto = "VENTA";
				}
				return ok(reportePorProyectoDetalleAgrupado.render(mapeoDiccionario,mapeoPermiso,userMnu,datos,proyecto,esVenta,concepto,fechaDesde,fechaHasta));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reportePorProyectoDetalleAgrupadoExcel(Http.Request request) {
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
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
			String permisoPorBodega = null;
			List<List<String>> datos = null;
			Proyecto proyecto = null;
			String fechaDesde = null;
			String fechaHasta = null;
			String esVenta = null;
			try (Connection con = dbRead.getConnection()) {
				Long id_proyecto = Long.parseLong(form.get("id_proyecto").trim());
				fechaDesde = form.get("fechaDesde").trim();
				fechaHasta = form.get("fechaHasta").trim();
				esVenta = form.get("esVenta").trim();
				permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
				datos = ReportMovimientos.movimientoGuiasPorProyecto(con, s.baseDato, id_proyecto, esVenta, fechaDesde, fechaHasta,
						permisoPorBodega, s.aplicaPorSucursal, s.id_sucursal, mapeoDiccionario);
				proyecto = Proyecto.find(con, s.baseDato, id_proyecto);
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
			try {
				String concepto = mapeoDiccionario.get("ARRIENDO");
				if("1".equals(esVenta)) {
					concepto = "VENTA";
				}
				File file = ReportMovimientos.movPorProyectoExcelAgrupado(s.baseDato, datos, mapeoDiccionario, proyecto, concepto, fechaDesde, fechaHasta);
				return ok(file,false,Optional.of("MovimientosPorBodegaAgrupado.xlsx"));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	//====================================================================================
	// MNU reporteMovimientos0   Reportes/Movimientos/Por proyecto (Valorizado)
	//====================================================================================

	public Result reportePorProyectoValorizado(Http.Request request) {
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
		if(mapeoPermiso.get("reporteMovimientos0")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()) {
			Fechas hoy = Fechas.hoy();
			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			TasasCambio tasas = TasasCambio.allDeUnaFecha(con, s.baseDato, mapeoDiccionario.get("pais"),hasta);
			return ok(reportePorProyectoValorizado.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta, tasas));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result reportePorProyectoListaValorizado(Http.Request request) {
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
			Map<String, String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String, String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
			String permisoPorBodega = null;
			List<Long> listIdBodegaEmpresa = null;
			Map<Long, Calc_BodegaEmpresa> mapBodegaEmpresa = null;
			Map<String, Calc_Precio> mapPrecios = null;
			Map<Long, Calc_Precio> mapMaestroPrecios = null;
			Map<String, Double> mapFijaTasas = null;
			List<Long> listIdGuia_fechaCorte = null;
			List<Inventarios> inventario = null;
			String desdeAAMMDD = null;
			String hastaAAMMDD = null;
			Map<Long, Double> tasas = null;
			List<Long> listIdGuia_entreFechas = null;
			Double uf = null;
			Double usd = null;
			Double eur = null;
			try (Connection con = dbRead.getConnection()) {
				desdeAAMMDD = form.get("fechaDesde").trim();
				hastaAAMMDD = form.get("fechaHasta").trim();
				uf = Double.parseDouble(form.get("uf").replaceAll(",", "").trim());
				usd = Double.parseDouble(form.get("usd").replaceAll(",", "").trim());
				eur = Double.parseDouble(form.get("eur").replaceAll(",", "").trim());
				tasas = new HashMap<Long, Double>();
				tasas.put((long) 1, (double) 1);    // 'Peso Chileno', 'CLP', '0'
				tasas.put((long) 2, usd);            // 'Dólar', 'USD', '2'
				tasas.put((long) 3, eur);            // 'Euro', 'EUR', '3'
				tasas.put((long) 4, uf);            // 'Unidad Fomento', 'UF', '4'
				permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
				listIdBodegaEmpresa = ModCalc_InvInicial.listIdBodegaEmpresa(con, s.baseDato, permisoPorBodega);
				mapBodegaEmpresa = Calc_BodegaEmpresa.mapAllBodegasVigentes(con, s.baseDato);
				mapPrecios = Calc_Precio.mapPrecios(con, s.baseDato, listIdBodegaEmpresa);
				mapMaestroPrecios = Calc_Precio.mapMaestroPrecios(con, s.baseDato);
				mapFijaTasas = BodegaEmpresa.mapFijaTasasAll(con, s.baseDato);
				listIdGuia_fechaCorte = ModCalc_InvInicial.listIdGuia_fechaCorte(con, s.baseDato, desdeAAMMDD);
				inventario = Inventarios.inventario(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_fechaCorte);
				listIdGuia_entreFechas = ModCalc_GuiasPer.listIdGuia_entreFecha(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
			ReportFacturas reporte = ModCalc_InvInicial.resumenInvInicial(s.baseDato, desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa,
					mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, listIdGuia_fechaCorte, inventario);
			inventario = null;
			listIdGuia_fechaCorte = null;
			List<ModCalc_InvInicial> inventarioInicial = reporte.resumenInvInicial;
			reporte = null;
			List<Inventarios> guiasPer = null;
			Map<String, String> mapPermanencias = null;
			List<Calc_AjustesEP> listaAjustes = null;
			Map<Long, List<String>> mapBodega = null;
			Map<String, String> map = null;
			Map<Long, Long> dec = null;
			try (Connection con = dbRead.getConnection()) {
				guiasPer = Inventarios.guiasPer(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_entreFechas);
				listIdGuia_entreFechas = null;
				listaAjustes = Calc_AjustesEP.listaAjustesEntreFechas(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
				mapBodega = BodegaEmpresa.mapIdBod_BodegaEmpresaInternasExternas(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
				dec = Moneda.numeroDecimal(con, s.baseDato);
				map = UsuarioPermiso.mapPermisoIdBodega(con, s.baseDato, Long.parseLong(s.id_usuario));
				mapPermanencias = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, s.baseDato);
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
			List<ModCalc_GuiasPer> guiasPeriodo = ModCalc_GuiasPer.resumenGuiasPer(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas,
					mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, guiasPer, mapPermanencias);
			guiasPer = null;
			mapBodegaEmpresa = null;
			mapPrecios = null;
			mapMaestroPrecios = null;
			mapPermanencias = null;
			List<ModeloCalculo> listado = null;
			List<List<String>> proyectosAux = null;
			List<List<String>> resumenTotales = null;
			try {
				listado = ModeloCalculo.valorTotalporBodega(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, inventarioInicial, guiasPeriodo, listaAjustes);
				mapFijaTasas = null;
				tasas = null;
				listaAjustes = null;
				proyectosAux = ReportFacturas.reportFacturaProyecto(listado, mapBodega);
				mapBodega = null;
				resumenTotales = ReportFacturas.resumenTotalesPorProyecto(listado, dec);
				List<List<String>> proyAux = new ArrayList<List<String>>();
				for (List<String> aux : proyectosAux) {
					if (!aux.get(3).equals("0")) {
						proyAux.add(aux);
					}
					proyectosAux = proyAux;
				}
				List<List<String>> proyectos = new ArrayList<List<String>>();
				if (!map.isEmpty()) {
					for (List<String> aux : proyectosAux) {
						String idBodega = map.get(aux.get(1));
						if (idBodega != null && !aux.get(3).equals("0")) {
							proyectos.add(aux);
						}
					}
				} else {
					proyectos = proyectosAux;
				}
				map = null;
				Map<String, List<Double>> mapListado = new HashMap<String, List<Double>>();
				for (List<String> lista1 : proyectos) {
					for (List<String> total : resumenTotales) {
						if (lista1.get(1).equals(total.get(0))) {
							String idProyecto = lista1.get(3);
							String nomProy = lista1.get(8);
							Double cfi = Double.parseDouble(total.get(3).replaceAll(",", ""));
							Double arr = Double.parseDouble(total.get(1).replaceAll(",", ""));
							Double vta = Double.parseDouble(total.get(2).replaceAll(",", ""));
							Double ajusteArr = Double.parseDouble(total.get(5).replaceAll(",", ""));
							Double ajusteVta = Double.parseDouble(total.get(6).replaceAll(",", ""));
							Double totTot = Double.parseDouble(total.get(4).replaceAll(",", ""));
							List<Double> aux = mapListado.get(idProyecto + "_:_" + nomProy);
							if (aux == null) {
								List<Double> l = new ArrayList<Double>();
								l.add(cfi);
								l.add(arr);
								l.add(vta);
								l.add(ajusteArr);
								l.add(ajusteVta);
								l.add(totTot);
								mapListado.put(idProyecto + "_:_" + nomProy, l);
							} else {
								aux.set(0, aux.get(0) + cfi);
								aux.set(1, aux.get(1) + arr);
								aux.set(2, aux.get(2) + vta);
								aux.set(3, aux.get(3) + ajusteArr);
								aux.set(4, aux.get(4) + ajusteVta);
								aux.set(5, aux.get(5) + totTot);
								mapListado.put(idProyecto + "_:_" + nomProy, aux);
							}
						}
					}
				}
				String tabla = "<table id=\"tablaPrincipal\" class=\"table table-sm table-hover table-bordered table-condensed table-fluid\">"
						+ "<thead style=\"background-color: #eeeeee\">"
						+ "<TR> "
						+ "<TH style= \"text-align: center;vertical-align: top;\">NOMBRE<br>PROYECTO</TH>"
						+ "<TH style= \"text-align:center;vertical-align:top;\">CFI (en " + mapeoDiccionario.get("PESOS") + ")</TH>"
						+ "<TH style= \"text-align:center;vertical-align:top;\">SubTotal<br>" + mapeoDiccionario.get("ARRIENDO") + "</TH>"
						+ "<TH style= \"text-align:center;vertical-align:top;\">SubTotal<br>VENTA</TH>"
						+ "<TH style= \"text-align:center;vertical-align:top;\">Ajustes<BR>(" + mapeoDiccionario.get("ARRIENDO") + ")</TH>"
						+ "<TH style= \"text-align:center;vertical-align:top;\">Ajustes<BR>(VENTA)</TH>"
						+ "<TH style= \"text-align:center;vertical-align:top;\">TOTAL<BR>(en " + mapeoDiccionario.get("PESOS") + ")</TH>"
						+ "<TH width=\"5%\" >" + mapeoDiccionario.get("ARRIENDO") + "<BR></TH>"
						+ "<TH width=\"5%\" >VENTA<BR></TH>"
						+ "</TR>"
						+ "</thead>"
						+ "<tbody>";
				for (Map.Entry<String, List<Double>> entry : mapListado.entrySet()) {
					String[] aux = entry.getKey().split("_:_");
					List<Double> val = entry.getValue();

					tabla += "<TR>"
							+ "<td style=\"text-align:left;vertical-align:middle;\"><a href=\"#\" onclick=\"modalContactoProyecto('" + aux[0] + "');\">" + aux[1] + "</a></td>"
							+ "<td style= \"text-align:right;\" class=\"cfi\">" + DecimalFormato.formato(val.get(0), (long) 0) + "</td>"
							+ "<td style= \"text-align:right;\" class=\"arr\">" + DecimalFormato.formato(val.get(1), (long) 0) + "</td>"
							+ "<td style= \"text-align:right;\" class=\"vta\">" + DecimalFormato.formato(val.get(2), (long) 0) + "</td>"
							+ "<td style= \"text-align:right;\" class=\"ajustArr\">" + DecimalFormato.formato(val.get(3), (long) 0) + "</td>"
							+ "<td style= \"text-align:right;\" class=\"ajustVta\">" + DecimalFormato.formato(val.get(4), (long) 0) + "</td>"
							+ "<td style= \"text-align:right;\" class=\"granTotal\">" + DecimalFormato.formato(val.get(5), (long) 0) + "</td>"

							+ "<td style=\"text-align:center;vertical-align:middle;\">"
							+ "<form id=\"form0_" + aux[0] + "\" class=\"formulario\" method=\"post\" action=\"/reporteMovimientosListaProyectos/\">"
							+ "<input type=\"hidden\" name=\"id_proyecto\" value=\"" + aux[0] + "\">"
							+ "<input type=\"hidden\" name=\"fechaDesde\" value=\"" + desdeAAMMDD + "\">"
							+ "<input type=\"hidden\" name=\"fechaHasta\" value=\"" + hastaAAMMDD + "\">"
							+ "<input type=\"hidden\" name=\"esVenta\" value=\"0\">"
							+ "<input type=\"hidden\" name=\"uf\" value=\"" + uf + "\">"
							+ "<input type=\"hidden\" name=\"usd\" value=\"" + usd + "\">"
							+ "<input type=\"hidden\" name=\"eur\" value=\"" + eur + "\">"
							+ "<a href=\"#\" onclick=\"document.getElementById('form0_" + aux[0] + "').submit()\">"
							+ "<kbd style=\"background-color: #73C6B6\">" + mapeoDiccionario.get("Arriendos") + "</kbd>"
							+ "</a>"
							+ "</form>"
							+ "</td>"
							+ "<td style=\"text-align:center;vertical-align:middle;\">"
							+ "<form id=\"form1_" + aux[0] + "\" class=\"formulario\" method=\"post\" action=\"/reporteMovimientosListaProyectos/\">"
							+ "<input type=\"hidden\" name=\"id_proyecto\" value=\"" + aux[0] + "\">"
							+ "<input type=\"hidden\" name=\"fechaDesde\" value=\"" + desdeAAMMDD + "\">"
							+ "<input type=\"hidden\" name=\"fechaHasta\" value=\"" + hastaAAMMDD + "\">"
							+ "<input type=\"hidden\" name=\"esVenta\" value=\"1\">"
							+ "<input type=\"hidden\" name=\"uf\" value=\"" + uf + "\">"
							+ "<input type=\"hidden\" name=\"usd\" value=\"" + usd + "\">"
							+ "<input type=\"hidden\" name=\"eur\" value=\"" + eur + "\">"

							+ "<a href=\"#\" onclick=\"document.getElementById('form1_" + aux[0] + "').submit()\">"
							+ "<kbd style=\"background-color: #73C6B6\">Ventas</kbd>"
							+ "</a>"
							+ "</form>"
							+ "</td>"
							+ "</TR>";
				}
				tabla += "</tbody>"
						+ "<tfoot>"
						+ "<TR>"
						+ "<td style=\"background-color: #eeeeee\">TOTALES</td>"
						+ "<td style=\"background-color: #eeeeee;text-align:right;\" id=\"cfi\"></td>"
						+ "<td style=\"background-color: #eeeeee;text-align:right;\" id=\"arr\"></td>"
						+ "<td style=\"background-color: #eeeeee;text-align:right;\" id=\"vta\"></td>"
						+ "<td style=\"background-color: #eeeeee;text-align:right;\" id=\"ajustArr\"></td>"
						+ "<td style=\"background-color: #eeeeee;text-align:right;\" id=\"ajustVta\"></td>"
						+ "<td style=\"background-color: #eeeeee;text-align:right;\" id=\"granTotal\"></td>"
						+ "<td style=\"background-color: #eeeeee;text-align:right;\">"
						+ "<td style=\"background-color: #eeeeee;text-align:right;\">"
						+ "</TR>"
						+ "</tfoot>"
						+ "</table>";
				return ok(reportePorProyectoListaValorizado.render(mapeoDiccionario, mapeoPermiso, userMnu, tabla, Fechas.DDMMAA(desdeAAMMDD), Fechas.DDMMAA(hastaAAMMDD)));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}


	//====================================================================================
	// MNU reporteMovimientos1   Reportes/Movimientos/Por Fecha (Valorizado)
	//====================================================================================

	public Result reporteMovimientosPeriodo(Http.Request request) {
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
		if(mapeoPermiso.get("reporteMovimientos1")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()) {
			Fechas hoy = Fechas.hoy();
			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			TasasCambio tasas = TasasCambio.allDeUnaFecha(con, s.baseDato, mapeoDiccionario.get("pais"),hasta);
			return ok(reporteMovimientosPeriodo.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta, tasas));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result reporteMovimientosListaProyectos(Http.Request request) {
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
		} else {
			Map<String, String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String, String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
			String desdeAAMMDD = null;
			String hastaAAMMDD = null;
			Map<Long, Double> tasas = null;
			Map<String, Double> mapFijaTasas = null;
			List<Long> listIdBodegaEmpresa = null;
			Map<Long, Calc_BodegaEmpresa> mapBodegaEmpresa = null;
			Map<String, Calc_Precio> mapPrecios = null;
			Map<Long, Calc_Precio> mapMaestroPrecios = null;
			List<Long> listIdGuia_fechaCorte = null;
			List<Inventarios> inventario = null;
			List<Long> listIdGuia_entreFechas = null;
			List<Calc_AjustesEP> listaAjustes = null;
			Map<Long, List<String>> mapBodega = null;
			Map<Long, Long> dec = null;
			Map<String, String> map = null;
			String id_proyecto = null;
			Double uf = null;
			Double usd = null;
			Double eur = null;
			try (Connection con = dbRead.getConnection()) {
				desdeAAMMDD = form.get("fechaDesde").trim();
				hastaAAMMDD = form.get("fechaHasta").trim();
				uf = Double.parseDouble(form.get("uf").replaceAll(",", "").trim());
				usd = Double.parseDouble(form.get("usd").replaceAll(",", "").trim());
				eur = Double.parseDouble(form.get("eur").replaceAll(",", "").trim());
				id_proyecto = form.get("id_proyecto");
				if (id_proyecto == null) {
					id_proyecto = "0";
				}
				id_proyecto = id_proyecto.trim();
				tasas = new HashMap<Long, Double>();
				tasas.put((long) 1, (double) 1);    // 'Peso Chileno', 'CLP', '0'
				tasas.put((long) 2, usd);            // 'Dólar', 'USD', '2'
				tasas.put((long) 3, eur);            // 'Euro', 'EUR', '3'
				tasas.put((long) 4, uf);            // 'Unidad Fomento', 'UF', '4'
				String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
				listIdBodegaEmpresa = ModCalc_InvInicial.listIdBodegaEmpresa(con, s.baseDato, permisoPorBodega);
				mapBodegaEmpresa = Calc_BodegaEmpresa.mapAllBodegasVigentes(con, s.baseDato);
				mapPrecios = Calc_Precio.mapPrecios(con, s.baseDato, listIdBodegaEmpresa);
				mapMaestroPrecios = Calc_Precio.mapMaestroPrecios(con, s.baseDato);
				mapFijaTasas = BodegaEmpresa.mapFijaTasasAll(con, s.baseDato);
				listIdGuia_fechaCorte = ModCalc_InvInicial.listIdGuia_fechaCorte(con, s.baseDato, desdeAAMMDD);
				inventario = Inventarios.inventario(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_fechaCorte);
				listIdGuia_entreFechas = ModCalc_GuiasPer.listIdGuia_entreFecha(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
				listaAjustes = Calc_AjustesEP.listaAjustesEntreFechas(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
				mapBodega = BodegaEmpresa.mapIdBod_BodegaEmpresaInternasExternas(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
				dec = Moneda.numeroDecimal(con, s.baseDato);
				map = UsuarioPermiso.mapPermisoIdBodega(con, s.baseDato, Long.parseLong(s.id_usuario));

			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
			ReportFacturas reporte = ModCalc_InvInicial.resumenInvInicial(s.baseDato, desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa,
					mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, listIdGuia_fechaCorte, inventario);
			List<ModCalc_InvInicial> inventarioInicial = reporte.resumenInvInicial;
			listIdGuia_fechaCorte = null;
			inventario = null;
			List<Inventarios> guiasPer = null;
			Map<String, String> mapPermanencias = null;
			try (Connection con = dbRead.getConnection()) {
				guiasPer = Inventarios.guiasPer(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_entreFechas);
				mapPermanencias = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, s.baseDato);
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
			try {
				List<ModCalc_GuiasPer> guiasPeriodo = ModCalc_GuiasPer.resumenGuiasPer(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas,
						mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, guiasPer, mapPermanencias);
				mapPermanencias = null;
				mapBodegaEmpresa = null;
				mapPrecios = null;
				mapMaestroPrecios = null;
				guiasPer = null;
				List<ModeloCalculo> listado = ModeloCalculo.valorTotalporBodega(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, inventarioInicial, guiasPeriodo, listaAjustes);
				mapFijaTasas = null;
				tasas = null;
				inventarioInicial = null;
				guiasPeriodo = null;
				listaAjustes = null;
				List<List<String>> proyectosAux = ReportFacturas.reportFacturaProyecto(listado, mapBodega);
				mapBodega = null;
				List<List<String>> resumenTotales = ReportFacturas.resumenTotalesPorProyecto(listado, dec);
				listado = null;
				List<List<String>> proyectos = new ArrayList<List<String>>();
				if (!map.isEmpty()) {
					for (List<String> aux : proyectosAux) {
						String idBodega = map.get(aux.get(1));
						if (idBodega != null) {
							proyectos.add(aux);
						}
					}
				} else {
					proyectos = proyectosAux;
				}
				proyectosAux = null;
				if (!id_proyecto.equals("0")) {
					List<List<String>> aux = new ArrayList<List<String>>();
					for (List<String> lista1 : proyectos) {
						if (lista1.get(3).trim().equals(id_proyecto)) {
							aux.add(lista1);
						}
					}
					proyectos = aux;
				}
				String tabla = "<table id=\"tablaPrincipal\" class=\"table table-sm table-hover table-bordered table-condensed table-fluid\">"
						+ "<thead style=\"background-color: #eeeeee\">"
						+ "<TR> "
						+ "<TH style= \"text-align: center;vertical-align: top;\">SUCURSAL</TH>"
						+ "<TH style= \"text-align: center;vertical-align: top;\">" + mapeoDiccionario.get("BODEGA") + "/PROYECTO</TH>"
						+ "<TH style= \"text-align: center;vertical-align: top;\">NOMBRE<BR>CLIENTE</TH>"
						+ "<TH style= \"text-align: center;vertical-align: top;\">NOMBRE<br>PROYECTO</TH>"
						+ "<TH style= \"text-align: center;vertical-align: top;\">COMERCIAL</TH>"
						+ "<TH style= \"text-align:center;vertical-align:top;\">CFI (en " + mapeoDiccionario.get("PESOS") + ")</TH>"
						+ "<TH style= \"text-align:center;vertical-align:top;\">SubTotal<br>" + mapeoDiccionario.get("ARRIENDO") + "</TH>"
						+ "<TH style= \"text-align:center;vertical-align:top;\">SubTotal<br>VENTA</TH>"
						+ "<TH style= \"text-align:center;vertical-align:top;\">Ajustes<BR>(" + mapeoDiccionario.get("ARRIENDO") + ")</TH>"
						+ "<TH style= \"text-align:center;vertical-align:top;\">Ajustes<BR>(VENTA)</TH>"
						+ "<TH style= \"text-align:center;vertical-align:top;\">TOTAL<BR>(en " + mapeoDiccionario.get("PESOS") + ")</TH>"
						+ "<TH width=\"5%\" >" + mapeoDiccionario.get("ARRIENDO") + "<BR></TH>"
						+ "<TH width=\"5%\" >VENTA<BR></TH>"
						+ "</TR>"
						+ "</thead>"
						+ "<tbody>";
				for (List<String> lista1 : proyectos) {
					tabla += "<TR>";
					for (List<String> total : resumenTotales) {
						if (lista1.get(1).equals(total.get(0))) {
							tabla += "<td style=\"text-align:left;vertical-align:middle;\">" + lista1.get(14) + "</td>"
									+ "<td style=\"text-align:left;vertical-align:middle;\"><a href=\"#\" onclick=\"modalContactoBodega('" + lista1.get(1) + "');\">" + lista1.get(5) + "</a></td>"
									+ "<td style=\"text-align:left;vertical-align:middle;\"><a href=\"#\" onclick=\"modalContactoCliente('" + lista1.get(2) + "');\">" + lista1.get(7) + "</a></td>"
									+ "<td style=\"text-align:left;vertical-align:middle;\"><a href=\"#\" onclick=\"modalContactoProyecto('" + lista1.get(3) + "');\">" + lista1.get(8) + "</a></td>"
									+ "<td style=\"text-align:left;vertical-align:middle;\">" + lista1.get(10) + "</td>"
									+ "<td style= \"text-align:right;\" class=\"cfi\">" + total.get(3) + "</td>"
									+ "<td style= \"text-align:right;\" class=\"arr\">" + total.get(1) + "</td>"
									+ "<td style= \"text-align:right;\" class=\"vta\">" + total.get(2) + "</td>"
									+ "<td style= \"text-align:right;\" class=\"ajustArr\">" + total.get(5) + "</td>"
									+ "<td style= \"text-align:right;\" class=\"ajustVta\">" + total.get(6) + "</td>"
									+ "<td style= \"text-align:right;\" class=\"granTotal\">" + total.get(4) + "</td>"
									+ "											"
									+ "<td style=\"text-align:center;vertical-align:middle;\">"
									+ "<form id=\"form0_" + lista1.get(1) + "\" class=\"formulario\" method=\"post\" action=\"/reporteMovimientosDetalle/\">"
									+ "<input type=\"hidden\" name=\"id_bodega\" value=\"" + lista1.get(1) + "\">"
									+ "<input type=\"hidden\" name=\"fechaDesde\" value=\"" + desdeAAMMDD + "\">"
									+ "<input type=\"hidden\" name=\"fechaHasta\" value=\"" + hastaAAMMDD + "\">"
									+ "<input type=\"hidden\" name=\"esVenta\" value=\"0\">"
									+ "<input type=\"hidden\" name=\"uf\" value=\"" + uf + "\">"
									+ "<input type=\"hidden\" name=\"usd\" value=\"" + usd + "\">"
									+ "<input type=\"hidden\" name=\"eur\" value=\"" + eur + "\">"
									+ "<a href=\"#\" onclick=\"document.getElementById('form0_" + lista1.get(1) + "').submit()\">"
									+ "<kbd style=\"background-color: #73C6B6\">" + mapeoDiccionario.get("Arriendos") + "</kbd>"
									+ "</a>"
									+ "</form>"
									+ "</td>"
									+ "<td style=\"text-align:center;vertical-align:middle;\">"
										+ "<form id=\"form1_" + lista1.get(1) + "\" class=\"formulario\" method=\"post\" action=\"/reporteMovimientosDetalle/\">"
											+ "<input type=\"hidden\" name=\"id_bodega\" value=\"" + lista1.get(1) + "\">"
											+ "<input type=\"hidden\" name=\"fechaDesde\" value=\"" + desdeAAMMDD + "\">"
											+ "<input type=\"hidden\" name=\"fechaHasta\" value=\"" + hastaAAMMDD + "\">"
											+ "<input type=\"hidden\" name=\"esVenta\" value=\"1\">"
											+ "<input type=\"hidden\" name=\"uf\" value=\"" + uf + "\">"
											+ "<input type=\"hidden\" name=\"usd\" value=\"" + usd + "\">"
											+ "<input type=\"hidden\" name=\"eur\" value=\"" + eur + "\">"
											+ "<a href=\"#\" onclick=\"document.getElementById('form1_" + lista1.get(1) + "').submit()\">"
												+ "<kbd style=\"background-color: #73C6B6\">Ventas</kbd>"
											+ "</a>"
										+ "</form>"
									+ "</td>";
						}
					}
					tabla += "</TR>";
				}
				tabla += "</tbody>"
						+ "<tfoot>"
						+ "<TR>"
						+ "<td style=\"background-color: #eeeeee\">TOTALES</td>"
						+ "<td style=\"background-color: #eeeeee\"></td>"
						+ "<td style=\"background-color: #eeeeee\"></td>"
						+ "<td style=\"background-color: #eeeeee\"></td>"
						+ "<td style=\"background-color: #eeeeee\"></td>"
						+ "<td style=\"background-color: #eeeeee;text-align:right;\" id=\"cfi\"></td>"
						+ "<td style=\"background-color: #eeeeee;text-align:right;\" id=\"arr\"></td>"
						+ "<td style=\"background-color: #eeeeee;text-align:right;\" id=\"vta\"></td>"
						+ "<td style=\"background-color: #eeeeee;text-align:right;\" id=\"ajustArr\"></td>"
						+ "<td style=\"background-color: #eeeeee;text-align:right;\" id=\"ajustVta\"></td>"
						+ "<td style=\"background-color: #eeeeee;text-align:right;\" id=\"granTotal\"></td>"
						+ "<td style=\"background-color: #eeeeee;text-align:right;\">"
						+ "<td style=\"background-color: #eeeeee;text-align:right;\">"
						+ "</TR>"
						+ "</tfoot>"
						+ "</table>";
				return ok(reporteMovimientosListaProyectos.render(mapeoDiccionario, mapeoPermiso, userMnu, tabla, Fechas.DDMMAA(desdeAAMMDD), Fechas.DDMMAA(hastaAAMMDD), id_proyecto,
						uf, usd, eur, desdeAAMMDD, hastaAAMMDD));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reporteMovimientosDetalle(Http.Request request) {
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
		} else {
			Map<String, String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String, String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
			try (Connection con = dbRead.getConnection()) {
				String auxIdBodega = form.get("id_bodega");
				Long id_bodegaEmpresa = (long) 0;
				if (auxIdBodega != null) {
					id_bodegaEmpresa = Long.parseLong(auxIdBodega.trim());
				}
				String fechaDesde = form.get("fechaDesde").trim();
				String fechaHasta = form.get("fechaHasta").trim();
				String esVenta = form.get("esVenta").trim();
				Double uf = Double.parseDouble(form.get("uf").replaceAll(",", "").trim());
				Double usd = Double.parseDouble(form.get("usd").replaceAll(",", "").trim());
				Double eur = Double.parseDouble(form.get("eur").replaceAll(",", "").trim());
				String id_proyecto = form.get("id_proyecto");
				if (id_proyecto == null) {
					id_proyecto = "0";
				}
				id_proyecto = id_proyecto.trim();
				if (id_proyecto.equals("0")) {
					String concepto = mapeoDiccionario.get("ARRIENDO");
					if ("1".equals(esVenta)) {
						concepto = "VENTA";
					}
					List<List<String>> datos = ReportMovimientos.movimientoGuias(con, s.baseDato, mapeoDiccionario, id_bodegaEmpresa, esVenta, fechaDesde, fechaHasta, usd, eur, uf, concepto);
					BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
					return ok(reporteMovimientosDetalle.render(mapeoDiccionario, mapeoPermiso, userMnu, datos, bodega, esVenta, concepto, fechaDesde, fechaHasta, usd, eur, uf, id_proyecto));
				} else {
					Proyecto proyecto = Proyecto.find(con, s.baseDato, Long.parseLong(id_proyecto));
					String concepto = mapeoDiccionario.get("ARRIENDO");
					if ("1".equals(esVenta)) {
						concepto = "VENTA";
					}
					List<Long> listIdBodegas = BodegaEmpresa.allIdBodPorIdProy(con, s.baseDato, id_proyecto);
					List<List<List<String>>> listDatos = new ArrayList<List<List<String>>>();
					for (Long i : listIdBodegas) {
						List<List<String>> datos = ReportMovimientos.movimientoGuias(con, s.baseDato, mapeoDiccionario, i, esVenta, fechaDesde, fechaHasta, usd, eur, uf, concepto);
						List<String> x = datos.get(datos.size() - 1);
						String y = x.get(x.size() - 5);
						Double total = Double.parseDouble(y.replaceAll(",", ""));
						if (total > 0) {
							listDatos.add(datos);
						}
					}
					return ok(reporteMovimientosDetallePorProyecto.render(mapeoDiccionario, mapeoPermiso, userMnu, listDatos, proyecto, esVenta, concepto, fechaDesde, fechaHasta, usd, eur, uf, id_proyecto));
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

	public Result reporteMovimientosDetalleExcel(Http.Request request) {
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
				String auxIdBodega = form.get("id_bodega");
				Long id_bodegaEmpresa = (long)0;
				if(auxIdBodega!=null) {
					id_bodegaEmpresa = Long.parseLong(auxIdBodega.trim());
				}
				String fechaDesde = form.get("fechaDesde").trim();
				String fechaHasta = form.get("fechaHasta").trim();
				String esVenta = form.get("esVenta").trim();
				Double uf = Double.parseDouble(form.get("uf").replaceAll(",", "").trim());
				Double usd = Double.parseDouble(form.get("usd").replaceAll(",", "").trim());
				Double eur = Double.parseDouble(form.get("eur").replaceAll(",", "").trim());
				String id_proyecto = form.get("id_proyecto");
				if(id_proyecto == null) {
					id_proyecto = "0";
				}
				id_proyecto = id_proyecto.trim();
				if(id_proyecto.equals("0")) {
					Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
					List<List<String>> datos = null;
					BodegaEmpresa bodega = null;
					String concepto = mapeoDiccionario.get("ARRIENDO");
					if("1".equals(esVenta)) {
						concepto = "VENTA";
					}
					try (Connection con = dbRead.getConnection()) {
						datos = ReportMovimientos.movimientoGuias(con, s.baseDato, mapeoDiccionario, id_bodegaEmpresa, esVenta, fechaDesde, fechaHasta, usd, eur, uf, concepto);
						bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
					} catch (SQLException e) {
						logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
						return ok(mensajes.render("/home/", msgReport));
					} catch (Exception e) {
						logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
						return ok(mensajes.render("/home/", msgReport));
					}

					File file = ReportMovimientos.movimientosExcel(s.baseDato, datos, mapeoDiccionario, bodega, concepto, fechaDesde, fechaHasta);
					return ok(file,false,Optional.of("MovimientosPorBodegaValorizado.xlsx"));
				} else {
					Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
					List<List<List<String>>> listDatos = null;
					Proyecto proyecto = null;
					String concepto = mapeoDiccionario.get("ARRIENDO");
					if("1".equals(esVenta)) {
						concepto = "VENTA";
					}
					try (Connection con = dbRead.getConnection()) {
						proyecto = Proyecto.find(con, s.baseDato, Long.parseLong(id_proyecto));
						List<Long> listIdBodegas = BodegaEmpresa.allIdBodPorIdProy(con, s.baseDato, id_proyecto);
						listDatos = new ArrayList<List<List<String>>>();
						for(Long i: listIdBodegas) {
							List<List<String>> datos = ReportMovimientos.movimientoGuias(con, s.baseDato, mapeoDiccionario, i, esVenta, fechaDesde, fechaHasta, usd, eur, uf, concepto);
							listDatos.add(datos);
						}
					} catch (SQLException e) {
						logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
						return ok(mensajes.render("/home/", msgReport));
					} catch (Exception e) {
						logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
						return ok(mensajes.render("/home/", msgReport));
					}

					File file = ReportMovimientos.movimientosExcelPorProyecto(s.baseDato, listDatos, mapeoDiccionario, proyecto, concepto, fechaDesde, fechaHasta);
					return ok(file,false,Optional.of("MovimientosPorProyectoValorizado.xlsx"));
				}
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	//====================================================================================
	// MNU reporteMovimientos2   Reportes/Movimientos/Ordenado por Ingreso-Egreso
	//====================================================================================

	public Result reporteMovimientosListaProyectosIE(Http.Request request) {
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
		if(mapeoPermiso.get("reporteMovimientos2")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try {
			String desdeAAMMDD = "2016-01-01";
			String hastaAAMMDD = "3000-01-01";
			Fechas hoy = Fechas.hoy();
			Map<Long,Double> tasas = null;
			List<Long> listIdBodegaEmpresa = null;
			Map<Long,Calc_BodegaEmpresa> mapBodegaEmpresa = null;
			Map<String,Calc_Precio> mapPrecios = null;
			Map<Long,Calc_Precio> mapMaestroPrecios = null;
			Map<String, Double> mapFijaTasas = null;
			List<Long> listIdGuia_fechaCorte = null;
			List<Inventarios> inventario = null;
			List<Inventarios> guiasPer = null;
			List<Calc_AjustesEP> listaAjustes = null;
			Map<Long,List<String>> mapBodega = null;
			Map<Long,Long> dec = null;
			Map<String,String> map = null;
			Map<String,String> mapPermanencias = null;
			try (Connection con = dbRead.getConnection()) {
				hoy = Fechas.hoy();
				tasas = TasasCambio.mapTasasPorFecha(con, s.baseDato,hoy.getFechaStrAAMMDD(), mapeoDiccionario.get("pais"));
				String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
				listIdBodegaEmpresa = ModCalc_InvInicial.listIdBodegaEmpresa(con, s.baseDato, permisoPorBodega);
				mapBodegaEmpresa = Calc_BodegaEmpresa.mapAllBodegasVigentes(con, s.baseDato);
				mapPrecios = Calc_Precio.mapPrecios(con, s.baseDato, listIdBodegaEmpresa);
				mapMaestroPrecios = Calc_Precio.mapMaestroPrecios(con, s.baseDato);
				mapFijaTasas = BodegaEmpresa.mapFijaTasasAll(con, s.baseDato);
				listIdGuia_fechaCorte = ModCalc_InvInicial.listIdGuia_fechaCorte(con, s.baseDato, desdeAAMMDD);
				inventario = Inventarios.inventario(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_fechaCorte);
				List<Long> listIdGuia_entreFechas = ModCalc_GuiasPer.listIdGuia_entreFecha(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
				guiasPer = Inventarios.guiasPer(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_entreFechas);
				listaAjustes = Calc_AjustesEP.listaAjustesEntreFechas(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
				mapBodega = BodegaEmpresa.mapIdBod_BodegaEmpresaInternasExternas(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
				dec = Moneda.numeroDecimal(con, s.baseDato);
				map = UsuarioPermiso.mapPermisoIdBodega(con, s.baseDato, Long.parseLong(s.id_usuario));
				mapPermanencias = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, s.baseDato);
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
			ReportFacturas reporte = ModCalc_InvInicial.resumenInvInicial(s.baseDato,desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa,
					mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, listIdGuia_fechaCorte, inventario);
			listIdBodegaEmpresa = null;
			listIdGuia_fechaCorte = null;
			inventario = null;
			List<ModCalc_InvInicial> inventarioInicial = reporte.resumenInvInicial;
			reporte = null;
			List<ModCalc_GuiasPer> guiasPeriodo = ModCalc_GuiasPer.resumenGuiasPer(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas,
					mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, guiasPer, mapPermanencias);
			mapBodegaEmpresa = null;
			mapPrecios = null;
			guiasPer = null;
			mapPermanencias = null;
			List<ModeloCalculo> listado = ModeloCalculo.valorTotalporBodega(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, inventarioInicial,guiasPeriodo, listaAjustes);
			tasas = null;
			mapFijaTasas = null;
			listaAjustes = null;
			inventarioInicial = null;
			guiasPeriodo = null;
			List<List<String>> proyectosAux = ReportFacturas.reportFacturaProyecto(listado, mapBodega);
			mapBodega = null;
			List<List<String>> resumenTotales = ReportFacturas.resumenTotalesPorProyecto(listado, dec);
			listado = null;
			List<List<String>> proyectos = new ArrayList<List<String>>();
			if(!map.isEmpty()) {
				for(List<String> aux:proyectosAux) {
					String idBodega = map.get(aux.get(1));
					if(idBodega!=null) {
						proyectos.add(aux);
					}
				}
			}else {
				proyectos = proyectosAux;
			}
			map = null;
			proyectosAux = null;
			Map<String,List<String>> mapTotal = new HashMap<String,List<String>>();
			for(List<String> total: resumenTotales){
				mapTotal.put(total.get(0), total);
			}
			resumenTotales = null;
			List<List<String>> datos = new ArrayList<List<String>>();
			for(List<String> lista1: proyectos){
				List<String> x = mapTotal.get(lista1.get(1));
				if(x!=null){
					List<String> aux = new ArrayList<String>();
					aux.add(lista1.get(0));  // 0 es cliente interno
					aux.add(lista1.get(1));  	// 1 idbodega empresa
					aux.add(lista1.get(2));  	// 2 id de cliente
					aux.add(lista1.get(3));  	// 3 id del proyecto
					aux.add("");  				// 4 tipo de cliente interno o externo
					aux.add(lista1.get(5));  	// 5 nombre bodega o empresa
					aux.add(lista1.get(6));  	// 6 rut del cliente
					aux.add(lista1.get(7));  	// 7 nombre del cliente
					aux.add(lista1.get(8));  	// 8 nombre del proyecto
					aux.add(lista1.get(9));  	// 9 comuna
					aux.add("");  				// 10 factorViga
					aux.add(lista1.get(14)); 	// 11 nameSucursal
					datos.add(aux);
				}
			}
			return ok(reporteMovimientosListaProyectosIE.render(mapeoDiccionario,mapeoPermiso,userMnu,datos));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result reporteMovimientosDetalleIE(Http.Request request) {
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
			try (Connection con = dbRead.getConnection()) {
				Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodega").trim());
				String esVenta = form.get("esVenta").trim();
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				List<List<String>> datos = ReportMovimientos.movimientoGuiasIE(con, s.baseDato, id_bodegaEmpresa, esVenta);
				BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
				String concepto = mapeoDiccionario.get("ARRIENDO");
				if("1".equals(esVenta)) {
					concepto = "VENTA";
				}
				return ok(reporteMovimientosDetalleIE.render(mapeoDiccionario,mapeoPermiso,userMnu,datos,bodega,esVenta,concepto));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reporteMovimientosIEExcel(Http.Request request) {
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
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				List<List<String>> datos = null;
				BodegaEmpresa bodega = null;
				String esVenta = form.get("esVenta").trim();
				try (Connection con = dbRead.getConnection()) {
					Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
					datos = ReportMovimientos.movimientoGuiasIE(con, s.baseDato, id_bodegaEmpresa, esVenta);
					bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				}
				String concepto = mapeoDiccionario.get("ARRIENDO");
				if("1".equals(esVenta)) {
					concepto = "VENTA";
				}
				File file = ReportMovimientos.movimientosExcelIE(s.baseDato, datos, mapeoDiccionario, bodega, concepto);
				return ok(file,false,Optional.of("MovimientosPorBodegaIE.xlsx"));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	//====================================================================================
	// MNU reporteExcedentes   Reportes/Movimientos/Excedentes por bodega
	//====================================================================================

	public Result reporteExcedentesListaProyectos(Http.Request request) {
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
		if(mapeoPermiso.get("reporteExcedentes")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()) {
			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
			List<List<String>> datos = ReportExcedentes.reportExcedentesSoloProyectosSelectivo(con, s.baseDato, permisoPorBodega, s.aplicaPorSucursal, s.id_sucursal);
			return ok(reporteExcedentesListaProyectos.render(mapeoDiccionario,mapeoPermiso,userMnu,datos));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result reporteExcedentesDetalle(Http.Request request) {
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
			try (Connection con = dbRead.getConnection()) {
				Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodega").trim());
				Map<String, String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String, String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				List<List<String>> datos = ReportExcedentes.reportExcedentesDetallePorProyecto(con, s.baseDato, id_bodegaEmpresa);
				BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
				return ok(reporteExcedentesDetalle.render(mapeoDiccionario, mapeoPermiso, userMnu, datos, bodega));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reporteExcedentesDetalleExcel(Http.Request request) {
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
			try (Connection con = dbRead.getConnection()) {
				Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				List<List<String>> datos = ReportExcedentes.reportExcedentesDetallePorProyecto(con, s.baseDato, id_bodegaEmpresa);
				BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
				File file = ReportExcedentes.excedentesExcel(con, s.baseDato, datos, mapeoDiccionario, bodega);
				return ok(file,false,Optional.of("ExcedentesPorBodega.xlsx"));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	//====================================================================================
	// MNU reporteExcedentes   Reportes/Movimientos/Excedentes por Equipo
	//====================================================================================

	public Result reporteExcedentesListaEquipos(Http.Request request) {
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
		if(mapeoPermiso.get("reporteExcedentes")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()) {
			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
			List<List<String>> datos = ReportExcedentes.reportExcedentesEquiposSelectivo(con, s.baseDato, permisoPorBodega, s.aplicaPorSucursal, s.id_sucursal);
			return ok(reporteExcedentesListaEquipos.render(mapeoDiccionario,mapeoPermiso,userMnu,datos));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result reporteExcedentesDetalleEquipo(Http.Request request) {
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
			try (Connection con = dbRead.getConnection()) {
				String id_equipo = form.get("id_equipo").trim();
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
				List<List<String>> datos = ReportExcedentes.reportExcedentesEquiposDetalle(con, s.baseDato, permisoPorBodega, s.aplicaPorSucursal, s.id_sucursal, id_equipo);
				Equipo equipo = Equipo.find(con, s.baseDato, Long.parseLong(id_equipo));
				return ok(reporteExcedentesDetalleEquipo.render(mapeoDiccionario,mapeoPermiso,userMnu,datos,equipo));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reporteExcedentesDetalleEquipoExcel(Http.Request request) {
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
			try (Connection con = dbRead.getConnection()) {
				String id_equipo = form.get("id_equipo").trim();
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				String permisoPorBodega = null;
				List<List<String>> datos = null;
				Equipo equipo = null;
				permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
				datos = ReportExcedentes.reportExcedentesEquiposDetalle(con, s.baseDato, permisoPorBodega, s.aplicaPorSucursal, s.id_sucursal, id_equipo);
				equipo = Equipo.find(con, s.baseDato, Long.parseLong(id_equipo));
				File file = ReportExcedentes.excedentesExcelEquipo(s.baseDato, datos, mapeoDiccionario, equipo);
				return ok(file,false,Optional.of("ExcedentesPorBodega.xlsx"));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	//====================================================================================
	// MNU reportEstados   Reportes/Estados/Estados por Bodega/Cliente
	//====================================================================================

	public Result reporteEstadosTodos(Http.Request request) {
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
		if(mapeoPermiso.get("reportEstados")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()) {
			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
			List<List<String>> datos = ReportInventarios.reportInventarioPorEstadosDeEquiposTodos(con, s.baseDato, permisoPorBodega, s.aplicaPorSucursal, s.id_sucursal);
			return ok(reporteEstadosTodos.render(mapeoDiccionario,mapeoPermiso,userMnu,datos));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result reporteEstadosTodosDetalle(Http.Request request) {
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
			try (Connection con = dbRead.getConnection()) {
				Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodega").trim());
				Map<String, String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String, String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
				List<TipoEstado> listTipoEstados = TipoEstado.allUsadosPorBodega(con, s.baseDato, bodega);
				Map<Long, Double> mapUnidadTiempo = UnidadTiempo.equivalencia(con, s.baseDato);
				Map<Long, List<List<String>>> mapDatos = new HashMap<Long, List<List<String>>>();
				for (TipoEstado x : listTipoEstados) {
					List<List<String>> datos = ReportMovimientos.movimientoGuiasPorEstado(con, s.baseDato, id_bodegaEmpresa, x.getId(), mapUnidadTiempo);
					mapDatos.put(x.getId(), datos);
				}
				return ok(reporteEstadosTodosDetalle.render(mapeoDiccionario, mapeoPermiso, userMnu, listTipoEstados, mapDatos, bodega));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reporteEstadosTodosDetalleExcel(Http.Request request) {
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
			Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
			try (Connection con = dbRead.getConnection()) {
				BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
				List<TipoEstado> listTipoEstados = TipoEstado.allUsadosPorBodega(con, s.baseDato, bodega);
				Map<Long,Double> mapUnidadTiempo = UnidadTiempo.equivalencia(con, s.baseDato);
				Map<Long,List<List<String>>> mapDatos = new HashMap<Long,List<List<String>>>();
				for(TipoEstado x: listTipoEstados) {
					List<List<String>> datos = ReportMovimientos.movimientoGuiasPorEstado(con, s.baseDato, id_bodegaEmpresa, x.getId(), mapUnidadTiempo);
					mapDatos.put(x.getId(), datos);
				}
				File file = ReportMovimientos.estadosExcel(s.baseDato, listTipoEstados, mapDatos, bodega, mapeoDiccionario);
				return ok(file,false,Optional.of("EstadosPorBodega.xlsx"));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	//====================================================================================
	// MNU reportEstados   Reportes/Estados/Estados por Periodo
	//====================================================================================

	public Result reporteEstadosPeriodo0(Http.Request request) {
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
		if(mapeoPermiso.get("reportEstados")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try {
			Fechas hoy = Fechas.hoy();
			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			return ok(reporteEstadosPeriodo0.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result reporteEstadosPeriodo1(Http.Request request) {
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
			try (Connection con = dbRead.getConnection()) {
				String desdeAAMMDD = form.get("fechaDesde").trim();
				String hastaAAMMDD = form.get("fechaHasta").trim();
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
				List<List<String>> datos = ReportInventarios.reportInventarioPorEstadosPorPeriodo(con, s.baseDato, permisoPorBodega, desdeAAMMDD, hastaAAMMDD, s.aplicaPorSucursal, s.id_sucursal);
				List<List<String>> listado = ReportMovimientos.movGuiasPorEstadoPeriodo(con, s.baseDato, datos);
				return ok(reporteEstadosPeriodo1.render(mapeoDiccionario,mapeoPermiso,userMnu,desdeAAMMDD,hastaAAMMDD,listado));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reporteEstadosPeriodo1Excel(Http.Request request) {
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
			try (Connection con = dbRead.getConnection()) {
				String desdeAAMMDD = form.get("desdeAAMMDD").trim();
				String hastaAAMMDD = form.get("hastaAAMMDD").trim();
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				String permisoPorBodega = null;
				List<List<String>> datos = null;
				List<List<String>> listado = null;
				permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
				datos = ReportInventarios.reportInventarioPorEstadosPorPeriodo(con, s.baseDato, permisoPorBodega, desdeAAMMDD, hastaAAMMDD, s.aplicaPorSucursal, s.id_sucursal);
				listado = ReportMovimientos.movGuiasPorEstadoPeriodo(con, s.baseDato, datos);
				File file = ReportMovimientos.estadosExcelPeriodo(s.baseDato, mapeoDiccionario, desdeAAMMDD, hastaAAMMDD, listado);
				return ok(file,false,Optional.of("EstadosPorPeriodo.xlsx"));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	//====================================================================================
	// MNU reportEstados   Reportes/Estados/Reparaciones por Bodega/Proyecto
	//====================================================================================

	public Result reporteEstadosAll0(Http.Request request) {
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
		if(mapeoPermiso.get("reportEstados")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()) {
			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
			List<List<String>> datos = ReportInventarios.reportInventarioPorEstadosDeEquiposTodos(con, s.baseDato, permisoPorBodega, s.aplicaPorSucursal, s.id_sucursal);
			return ok(reporteEstadosAll0.render(mapeoDiccionario,mapeoPermiso,userMnu,datos));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result reporteEstadosAll1(Http.Request request) {
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
			Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
			if(mapeoPermiso.get("reportEstados")==null) {
				logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
				return ok(mensajes.render("/",msgSinPermiso));
			}
			try (Connection con = dbRead.getConnection()) {
				BodegaEmpresa bodegaEmpresa = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
				String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
				List<List<String>> datos = ReportInventarios.reportInventarioPorEstadosAll(con, s.baseDato, permisoPorBodega, bodegaEmpresa.getId());
				return ok(reporteEstadosAll1.render(mapeoDiccionario,mapeoPermiso,userMnu, datos, bodegaEmpresa));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reporteEstadosAll1Excel(Http.Request request) {
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
			try (Connection con = dbRead.getConnection()) {
				Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
				BodegaEmpresa bodegaEmpresa = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
				List<List<String>> datos = ReportInventarios.reportInventarioPorEstadosAll(con, s.baseDato, permisoPorBodega, bodegaEmpresa.getId());
				File file = ReportInventarios.reporteEstadosAllExcel(s.baseDato, mapeoDiccionario, datos, bodegaEmpresa);
				return ok(file,false,Optional.of("ReporteEstadosTodo.xlsx"));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	//====================================================================================
	// MNU reportEstados   Reportes/Estados/Reparaciones por Periodo
	//====================================================================================

	public Result reporteEstadosPer0(Http.Request request) {
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
		if(mapeoPermiso.get("reporteMovimientos0")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try {
			Fechas hoy = Fechas.hoy();
			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			return ok(reporteEstadosPer0.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result reporteEstadosPer1(Http.Request request) {
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
		} else {
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
			if(mapeoPermiso.get("reportEstados")==null) {
				logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
				return ok(mensajes.render("/",msgSinPermiso));
			}
			try (Connection con = dbRead.getConnection()) {
				String desdeAAMMDD = form.get("fechaDesde").trim();
				String hastaAAMMDD = form.get("fechaHasta").trim();
				String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
				List<List<String>> datos = ReportInventarios.reportInventarioEstadosPorPer(con, s.baseDato, permisoPorBodega, desdeAAMMDD, hastaAAMMDD);
				return ok(reporteEstadosPer1.render(mapeoDiccionario,mapeoPermiso,userMnu, datos, desdeAAMMDD, hastaAAMMDD));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reporteEstadosPer1Excel(Http.Request request) {
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
			try (Connection con = dbRead.getConnection()) {
				String desdeAAMMDD = form.get("desdeAAMMDD").trim();
				String hastaAAMMDD = form.get("hastaAAMMDD").trim();
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
				List<List<String>> datos = ReportInventarios.reportInventarioEstadosPorPer(con, s.baseDato, permisoPorBodega, desdeAAMMDD, hastaAAMMDD);
				File file = ReportInventarios.reportInventarioEstadosPorPerExcel(s.baseDato, mapeoDiccionario, datos, desdeAAMMDD, hastaAAMMDD);
				return ok(file,false,Optional.of("ReporteEstadosPorPeriodo.xlsx"));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	//====================================================================================
	// MNU reportTrazaEquipo   Reportes/Trazabilidad
	//====================================================================================

	public Result reportTrazaEquipo(Http.Request request) {
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
		if(mapeoPermiso.get("reportTrazaEquipo")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()) {
			List<Equipo> datos = Equipo.allVigentes(con, s.baseDato);
			return ok(reportTrazaEquipo.render(mapeoDiccionario,mapeoPermiso,userMnu,datos));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result reportTrazaEquipo2(Http.Request request) {
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
			try (Connection con = dbRead.getConnection()) {
				Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				List<List<String>> datos = ReportTrazabilidades.trazaEquipo(con, s.baseDato, id_equipo, mapeoDiccionario, s.aplicaPorSucursal, s.id_sucursal);
				return ok(reportTrazaEquipo2.render(mapeoDiccionario,mapeoPermiso,userMnu,datos,id_equipo));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reportTrazaEquipo2Excel(Http.Request request) {
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
			try (Connection con = dbRead.getConnection()) {
				Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				List<List<String>> datos = ReportTrazabilidades.trazaEquipo(con, s.baseDato, id_equipo, mapeoDiccionario, s.aplicaPorSucursal, s.id_sucursal);
				File file = ReportTrazabilidades.trazaEquipoExcel(s.baseDato, datos, mapeoDiccionario);
				return ok(file,false,Optional.of("TrazabilidadPorEquipo.xlsx"));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	//====================================================================================
	// MNU reporteEjecutivo1   Reportes/Reporte Ejecutivo
	//====================================================================================

	public Result reporteEjecutivo1(Http.Request request) {
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
		if(mapeoPermiso.get("reporteEjecutivo1")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()) {
			Map<Long,List<Double>> mapPCompra = Compra.ultimoPrecio(con, s.baseDato);
			Map<Long,List<Double>> mapPLista = Precio.maestroPListaPorSucursal(con, s.baseDato, Long.parseLong(s.id_sucursal));
			Map<Long,String> moneda = Moneda.mapIdMonedaMoneda(con, s.baseDato);
			Fechas hoy = Fechas.hoy();
			Map<Long,Double> tasasCorte = TasasCambio.mapTasasPorFecha(con, s.baseDato,hoy.getFechaStrAAMMDD(), mapeoDiccionario.get("pais"));
			Map<Long,Long> dec = Moneda.numeroDecimal(con, s.baseDato);
			Map<Long,UnidadTiempo> mapUnidadTiempo = UnidadTiempo.mapUnidadTiempo(con, s.baseDato);
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, s.baseDato);
			Map<Long,Equipo> mapEquipo = Equipo.mapAllAll(con, s.baseDato);
			Map<Long,TipoBodega> mapTipoBodega = TipoBodega.mapAll(con, s.baseDato);
			Map<Long, Double> mapEquivalencia = UnidadTiempo.equivalencia(con,s.baseDato);
			List<List<String>> datos = ReportInventarios.reportInventarioEquipoConTipoBodega(con, s.baseDato, hoy.getFechaStrAAMMDD(), "", mapPCompra, mapPLista, moneda,
					mapeoDiccionario.get("ARRIENDO"), mapeoDiccionario, s.aplicaPorSucursal, s.id_sucursal, mapEquipo, mapSucursal, mapUnidadTiempo, tasasCorte, dec);
			moneda = null;
			List<String> valorizado = ReportEjecutivos.graficoDistribucionResumenValorizado(mapeoDiccionario, datos, "0", mapTipoBodega);
			String grafInversion = valorizado.get(2);
			List<String> porUnidades = ReportEjecutivos.graficoDistribucionResumenPorUnidades(mapeoDiccionario, datos, "0", mapTipoBodega);
			datos = null;
			List<String> graficoOcupacionValorizado = ReportEjecutivos.graficoOcupacionPorGrupoValorizado(con, s.baseDato, mapeoDiccionario.get("pais"), s.aplicaPorSucursal, s.id_sucursal,
					mapTipoBodega, mapEquipo);
			mapEquipo = null;
			List<String> valorizadoPorBodega = ReportEjecutivos.graficoDistribucionResumenValorizadoPorBodega(con, s.baseDato, mapeoDiccionario.get("pais"), s.aplicaPorSucursal, s.id_sucursal,
					mapPCompra, tasasCorte, dec);
			mapPCompra = null;
			List<String> graficoPotencialPorMesYGrupo = ReportEjecutivos.graficoPotencialPorMesYGrupo(con, s.baseDato, mapeoDiccionario.get("pais"), s.aplicaPorSucursal, s.id_sucursal,
					tasasCorte, mapPLista, mapEquivalencia, dec);
			return ok(reporteEjecutivo1.render(mapeoDiccionario,mapeoPermiso,userMnu,valorizado,porUnidades,grafInversion,graficoOcupacionValorizado,valorizadoPorBodega,
					graficoPotencialPorMesYGrupo));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result graficoInversionExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("reporteEjecutivo1")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()) {
			Map<Long,List<Double>> mapPCompra = Compra.ultimoPrecio(con, s.baseDato);
			Map<Long,List<Double>> mapPLista = Precio.maestroPListaPorSucursal(con, s.baseDato, Long.parseLong(s.id_sucursal));
			Map<Long,String> moneda = Moneda.mapIdMonedaMoneda(con, s.baseDato);
			Fechas hoy = Fechas.hoy();
			Map<Long,Double> tasasCorte = TasasCambio.mapTasasPorFecha(con, s.baseDato,hoy.getFechaStrAAMMDD(), mapeoDiccionario.get("pais"));
			Map<Long,Long> dec = Moneda.numeroDecimal(con, s.baseDato);
			Map<Long,UnidadTiempo> mapUnidadTiempo = UnidadTiempo.mapUnidadTiempo(con, s.baseDato);
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, s.baseDato);
			Map<Long,Equipo> mapEquipo = Equipo.mapAllAll(con, s.baseDato);
			Map<Long,TipoBodega> mapTipoBodega = TipoBodega.mapAll(con, s.baseDato);
			List<List<String>> datos = ReportInventarios.reportInventarioEquipoConTipoBodega(con, s.baseDato, hoy.getFechaStrAAMMDD(), "", mapPCompra, mapPLista, moneda,
					mapeoDiccionario.get("ARRIENDO"), mapeoDiccionario, s.aplicaPorSucursal, s.id_sucursal, mapEquipo, mapSucursal, mapUnidadTiempo, tasasCorte, dec);
			mapPCompra = null;
			mapPLista = null;
			mapEquipo = null;
			List<String> valorizado = ReportEjecutivos.graficoDistribucionResumenValorizado(mapeoDiccionario, datos, "0", mapTipoBodega);
			datos = null;
			String grafInversion = valorizado.get(2);
			File file = ReportEjecutivos.graficoInversion(s.baseDato, grafInversion, mapeoDiccionario);
			return ok(file,false,Optional.of("GraficoInversion.xlsx"));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result graficoOcupacionExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("reporteEjecutivo1")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()) {
			Map<Long,Equipo> mapEquipo = Equipo.mapAllAll(con, s.baseDato);
			Map<Long,TipoBodega> mapTipoBodega = TipoBodega.mapAll(con, s.baseDato);
			List<String> grafOcupacion = ReportEjecutivos.graficoOcupacionPorGrupoValorizado(con, s.baseDato, mapeoDiccionario.get("pais"), s.aplicaPorSucursal, s.id_sucursal,
					mapTipoBodega, mapEquipo);
			mapEquipo = null;
			File file = ReportEjecutivos.graficoOcupacion(s.baseDato, grafOcupacion, mapeoDiccionario);
			return ok(file,false,Optional.of("GraficoOcupacion.xlsx"));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result graficoValorizadoGrupoExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("reporteEjecutivo1")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()) {
			Map<Long,List<Double>> mapPCompra = Compra.ultimoPrecio(con, s.baseDato);
			Map<Long,List<Double>> mapPLista = Precio.maestroPListaPorSucursal(con, s.baseDato, Long.parseLong(s.id_sucursal));
			Map<Long,String> moneda = Moneda.mapIdMonedaMoneda(con, s.baseDato);
			Fechas hoy = Fechas.hoy();
			Map<Long,Double> tasasCorte = TasasCambio.mapTasasPorFecha(con, s.baseDato,hoy.getFechaStrAAMMDD(), mapeoDiccionario.get("pais"));
			Map<Long,Long> dec = Moneda.numeroDecimal(con, s.baseDato);
			Map<Long,UnidadTiempo> mapUnidadTiempo = UnidadTiempo.mapUnidadTiempo(con, s.baseDato);
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, s.baseDato);
			Map<Long,Equipo> mapEquipo = Equipo.mapAllAll(con, s.baseDato);
			Map<Long,TipoBodega> mapTipoBodega = TipoBodega.mapAll(con, s.baseDato);
			List<List<String>> datos = ReportInventarios.reportInventarioEquipoConTipoBodega(con, s.baseDato, hoy.getFechaStrAAMMDD(), "", mapPCompra, mapPLista, moneda,
					mapeoDiccionario.get("ARRIENDO"), mapeoDiccionario, s.aplicaPorSucursal, s.id_sucursal, mapEquipo, mapSucursal, mapUnidadTiempo, tasasCorte, dec);
			mapPCompra = null;
			mapPLista = null;
			mapEquipo = null;
			List<String> valorizado = ReportEjecutivos.graficoDistribucionResumenValorizado(mapeoDiccionario, datos, "0", mapTipoBodega);
			datos = null;
			File file = ReportEjecutivos.graficoValorizadoGrupo(s.baseDato, valorizado, mapeoDiccionario);
			return ok(file,false,Optional.of("GraficoValorizadoGrupo.xlsx"));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result graficoUnidadesExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("reporteEjecutivo1")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()) {
			Map<Long,List<Double>> mapPCompra = Compra.ultimoPrecio(con, s.baseDato);
			Map<Long,List<Double>> mapPLista = Precio.maestroPListaPorSucursal(con, s.baseDato, Long.parseLong(s.id_sucursal));
			Map<Long,String> moneda = Moneda.mapIdMonedaMoneda(con, s.baseDato);
			Fechas hoy = Fechas.hoy();
			Map<Long,Double> tasasCorte = TasasCambio.mapTasasPorFecha(con, s.baseDato,hoy.getFechaStrAAMMDD(), mapeoDiccionario.get("pais"));
			Map<Long,Long> dec = Moneda.numeroDecimal(con, s.baseDato);
			Map<Long,UnidadTiempo> mapUnidadTiempo = UnidadTiempo.mapUnidadTiempo(con, s.baseDato);
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, s.baseDato);
			Map<Long,Equipo> mapEquipo = Equipo.mapAllAll(con, s.baseDato);
			Map<Long,TipoBodega> mapTipoBodega = TipoBodega.mapAll(con, s.baseDato);
			List<List<String>> datos = ReportInventarios.reportInventarioEquipoConTipoBodega(con, s.baseDato, hoy.getFechaStrAAMMDD(), "", mapPCompra, mapPLista, moneda,
					mapeoDiccionario.get("ARRIENDO"), mapeoDiccionario, s.aplicaPorSucursal, s.id_sucursal, mapEquipo, mapSucursal, mapUnidadTiempo, tasasCorte, dec);
			mapPCompra = null;
			mapPLista = null;
			mapEquipo = null;
			List<String> porUnidades = ReportEjecutivos.graficoDistribucionResumenPorUnidades(mapeoDiccionario, datos, "0", mapTipoBodega);
			datos = null;
			File file = ReportEjecutivos.graficoUnidades(s.baseDato, porUnidades, mapeoDiccionario);
			return ok(file,false,Optional.of("GraficoPorUnidadesGrupo.xlsx"));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result graficoValorizadoBodegaExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("reporteEjecutivo1")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()) {
			Map<Long,List<Double>> mapPCompra = Compra.ultimoPrecio(con, s.baseDato);
			Fechas hoy = Fechas.hoy();
			Map<Long,Double> tasasCorte = TasasCambio.mapTasasPorFecha(con, s.baseDato,hoy.getFechaStrAAMMDD(), mapeoDiccionario.get("pais"));
			Map<Long,Long> dec = Moneda.numeroDecimal(con, s.baseDato);
			List<String> valorizadoPorBodega = ReportEjecutivos.graficoDistribucionResumenValorizadoPorBodega(con, s.baseDato, mapeoDiccionario.get("pais"), s.aplicaPorSucursal, s.id_sucursal,
					mapPCompra, tasasCorte, dec);
			mapPCompra = null;
			File file = ReportEjecutivos.graficoValorizadoBodega(s.baseDato, valorizadoPorBodega, mapeoDiccionario);
			return ok(file,false,Optional.of("GraficoValorizadoBodega.xlsx"));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result graficoPotencialExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("reporteEjecutivo1")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()) {
			Map<Long,List<Double>> mapPLista = Precio.maestroPListaPorSucursal(con, s.baseDato, Long.parseLong(s.id_sucursal));
			Fechas hoy = Fechas.hoy();
			Map<Long,Double> tasasCorte = TasasCambio.mapTasasPorFecha(con, s.baseDato,hoy.getFechaStrAAMMDD(), mapeoDiccionario.get("pais"));
			Map<Long,Long> dec = Moneda.numeroDecimal(con, s.baseDato);
			Map<Long, Double> mapEquivalencia = UnidadTiempo.equivalencia(con,s.baseDato);
			List<String> graficoPotencialPorMesYGrupo = ReportEjecutivos.graficoPotencialPorMesYGrupo(con, s.baseDato, mapeoDiccionario.get("pais"), s.aplicaPorSucursal, s.id_sucursal,
					tasasCorte, mapPLista, mapEquivalencia, dec);
			mapPLista = null;
			File file = ReportEjecutivos.graficoPotencial(s.baseDato, graficoPotencialPorMesYGrupo, mapeoDiccionario);
			return ok(file,false,Optional.of("GraficoPotencialPorMesYGrupo.xlsx"));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	//====================================================================================
	// MNU reporteGerencial1   Reportes/Reporte Gerencial/Reporte Gerencial
	//====================================================================================

	public Result reporteGerencial(Http.Request request) {
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
		if(mapeoPermiso.get("reporteGerencial1")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbWrite.getConnection()) {
			Long id_grupo = (long)0;
			List<String> series = ReportGerenciales.graficoCategoriasAnio(con, s.baseDato, id_grupo, mapeoDiccionario, "0", "1");
			return ok(reporteGerencial.render(mapeoDiccionario,mapeoPermiso,userMnu,series,"TODO EL INVENTARIO"));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	//====================================================================================
	// MNU reporteGerencial2   Reportes/Reporte Gerencial/Reporte por Grupos
	//====================================================================================

	public Result reporteGerencialGrupo(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("reporteGerencial2")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()) {
			UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
			List<Grupo> lista = Grupo.all(con, s.baseDato);
			return ok(reporteGerencialGrupo.render(mapeoDiccionario,mapeoPermiso,userMnu,lista));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result reporteGerencialGrupoDetalle(Http.Request request) {
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
			try (Connection con = dbWrite.getConnection()) {
				Long id_grupo = Long.parseLong(form.get("id_grupo").trim());
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				Grupo grupo = Grupo.find(con, s.baseDato, id_grupo);
				List<String> series = ReportGerenciales.graficoCategoriasAnioPorGrupo(con, s.baseDato, id_grupo, mapeoDiccionario, s.aplicaPorSucursal, s.id_sucursal);
				return ok(reporteGerencial.render(mapeoDiccionario,mapeoPermiso,userMnu,series,"(NO CONSIDERA AJUSTES)  GRUPO: "+grupo.nombre));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	//====================================================================================
	// MNU reporteGerencial2   Reportes/Reporte Gerencial/Resumen KG movidos
	//====================================================================================

	public Result reportGerenKGPorPeriodo0(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("reporteGerencial2")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbWrite.getConnection()) {
			UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
			Fechas hoy = Fechas.hoy();
			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			return ok(reportGerenKGPorPeriodo0.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}


	public Result reportGerenKGPorPeriodo1(Http.Request request) {
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
			String desdeAAMMDD = form.get("fechaDesde").trim();
			String hastaAAMMDD = form.get("fechaHasta").trim();
			try (Connection con = dbRead.getConnection()) {
				UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				HashMap<String, Object> map = ReportKilos.reportKgArrPorGrupoPorPeriodo(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
				// Conversiones seguras con verificación de tipo
				@SuppressWarnings("unchecked")
				List<String> cabecera1 = Optional.ofNullable(map.get("cabecera1"))
						.filter(obj -> obj instanceof List<?>)
						.map(obj -> (List<String>) obj)
						.orElse(new ArrayList<>());

				@SuppressWarnings("unchecked")
				List<String> cabecera2 = Optional.ofNullable(map.get("cabecera2"))
						.filter(obj -> obj instanceof List<?>)
						.map(obj -> (List<String>) obj)
						.orElse(new ArrayList<>());

				@SuppressWarnings("unchecked")
				List<List<String>> datos = Optional.ofNullable(map.get("datos"))
						.filter(obj -> obj instanceof List<?>)
						.map(obj -> (List<List<String>>) obj)
						.orElse(new ArrayList<>());

				return ok(reportGerenKGPorPeriodo1.render(mapeoDiccionario,mapeoPermiso,userMnu,cabecera1,cabecera2,datos, desdeAAMMDD, hastaAAMMDD));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reportGerenKGPorPeriodo1Excel(Http.Request request) {
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
			try (Connection con = dbRead.getConnection()) {
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				String desdeAAMMDD = form.get("desde").trim();
				String hastaAAMMDD = form.get("hasta").trim();
				HashMap<String, Object> map = ReportKilos.reportKgArrPorGrupoPorPeriodo(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
				// Conversiones seguras con verificación de tipo
				@SuppressWarnings("unchecked")
				List<String> cabecera1 = Optional.ofNullable(map.get("cabecera1"))
						.filter(obj -> obj instanceof List<?>)
						.map(obj -> (List<String>) obj)
						.orElse(new ArrayList<>());

				@SuppressWarnings("unchecked")
				List<String> cabecera2 = Optional.ofNullable(map.get("cabecera2"))
						.filter(obj -> obj instanceof List<?>)
						.map(obj -> (List<String>) obj)
						.orElse(new ArrayList<>());

				@SuppressWarnings("unchecked")
				List<List<String>> datos = Optional.ofNullable(map.get("datos"))
						.filter(obj -> obj instanceof List<?>)
						.map(obj -> (List<List<String>>) obj)
						.orElse(new ArrayList<>());
				File file = ReportKilos.reportGerenKGPorPeriodo1Excel(s.baseDato, mapeoDiccionario, cabecera1, cabecera2, datos, desdeAAMMDD, hastaAAMMDD);
				return ok(file,false,Optional.of("ReporteToneladasMovidas.xlsx"));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reporteGerencialKG(Http.Request request) {
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
		if(mapeoPermiso.get("reporteGerencial2")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbWrite.getConnection()) {
			Fechas hoy = Fechas.hoy();
			String anioActual = hoy.getFechaStrAAMMDD().substring(0,4);
			Long dePaso = Long.parseLong(anioActual)-1;
			String anioAnterior = dePaso.toString();
			dePaso = dePaso - 1;
			String anioAnteriorAnterior = dePaso.toString();
			List<List<String>> datosActual = ReportKilos.reportKilosArriendoPorGrupoAnual(con, s.baseDato, Long.parseLong(anioActual));
			List<List<String>> datosAnterior = ReportKilos.reportKilosArriendoPorGrupoAnual(con,s.baseDato, Long.parseLong(anioAnterior));
			List<List<String>> datosAnteriorAnterior = ReportKilos.reportKilosArriendoPorGrupoAnual(con,s.baseDato, Long.parseLong(anioAnteriorAnterior));
			return ok(reporteGerencialKG.render(mapeoDiccionario,mapeoPermiso,userMnu,datosActual,datosAnterior,datosAnteriorAnterior, anioActual,anioAnterior,anioAnteriorAnterior));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result reporteGerencialKGExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("reporteEjecutivo1")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbWrite.getConnection()) {
			Fechas hoy = Fechas.hoy();
			String anioActual = hoy.getFechaStrAAMMDD().substring(0,4);
			Long dePaso = Long.parseLong(anioActual)-1;
			String anioAnterior = dePaso.toString();
			dePaso = dePaso -1;
			String anioAnteriorAnterior = dePaso.toString();
			List<List<String>> datosActual = ReportKilos.reportKilosArriendoPorGrupoAnual(con, s.baseDato, Long.parseLong(anioActual));
			List<List<String>> datosAnterior = ReportKilos.reportKilosArriendoPorGrupoAnual(con,s.baseDato, Long.parseLong(anioAnterior));
			List<List<String>> datosAnteriorAnterior = ReportKilos.reportKilosArriendoPorGrupoAnual(con,s.baseDato, Long.parseLong(anioAnteriorAnterior));
			File file = ReportKilos.reporteGerencialKGExcel(s.baseDato, mapeoDiccionario, datosActual, datosAnterior, datosAnteriorAnterior, anioActual, anioAnterior, anioAnteriorAnterior);
			return ok(file,false,Optional.of("ReporteToneladasMovidas.xlsx"));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	//====================================================================================
	// MNU reportGraficoGrupo1   Reportes/Graficos
	//====================================================================================

	public Result reportGraficoMovResumen(Http.Request request, String filtroGrupos, String vistaCheckBox ) {
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
		if(mapeoPermiso.get("reportGraficoGrupo1")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbWrite.getConnection()) {
			Map<Long,List<Double>> mapPCompra = Compra.ultimoPrecio(con, s.baseDato);
			Map<Long,List<Double>> mapPLista = Precio.maestroPListaPorSucursal(con, s.baseDato, Long.parseLong(s.id_sucursal));
			Map<Long,String> moneda = Moneda.mapIdMonedaMoneda(con, s.baseDato);
			Fechas hoy = Fechas.hoy();
			Map<Long,Double> tasasCorte = TasasCambio.mapTasasPorFecha(con, s.baseDato,hoy.getFechaStrAAMMDD(), mapeoDiccionario.get("pais"));
			Map<Long,Long> dec = Moneda.numeroDecimal(con, s.baseDato);
			Map<Long,UnidadTiempo> mapUnidadTiempo = UnidadTiempo.mapUnidadTiempo(con, s.baseDato);
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, s.baseDato);
			Map<Long,Equipo> mapEquipo = Equipo.mapAllAll(con, s.baseDato);
			Map<Long,TipoBodega> mapTipoBodega = TipoBodega.mapAll(con, s.baseDato);
			List<List<String>> datos = ReportInventarios.reportInventarioEquipoConTipoBodega(con, s.baseDato, hoy.getFechaStrAAMMDD(), "", mapPCompra, mapPLista, moneda,
					mapeoDiccionario.get("ARRIENDO"), mapeoDiccionario, s.aplicaPorSucursal, s.id_sucursal, mapEquipo, mapSucursal, mapUnidadTiempo, tasasCorte, dec);
			mapPCompra = null;
			mapPLista = null;
			mapEquipo = null;
			List<String> valorizado = ReportEjecutivos.graficoDistribucionResumenValorizado(mapeoDiccionario, datos, "0", mapTipoBodega);
			List<String> porUnidades = ReportEjecutivos.graficoDistribucionResumenPorUnidades(mapeoDiccionario, datos, "0", mapTipoBodega);
			datos = null;
			List<Grupo> listGrupos = Grupo.all(con, s.baseDato);
			String subtitulo="RESUMEN POR "+mapeoDiccionario.get("BODEGA")+"/INTERNA y "+mapeoDiccionario.get("BODEGA")+"/PROYECTO";
			return ok(reportGraficoMovResumen.render(mapeoDiccionario,mapeoPermiso,userMnu,valorizado,porUnidades,listGrupos,vistaCheckBox,subtitulo));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result refreshGraficoMovResumen(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		FormGrafico form = formFactory.form(FormGrafico.class).withDirectFieldAccess(true).bindFromRequest(request).get();
		if (form.listIdGrupos==null) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgErrorFormulario));
		}else {
			try (Connection con = dbWrite.getConnection()) {
				List<String> listIdGrupos = form.listIdGrupos;
				List<Grupo> listGrupos = Grupo.all(con, s.baseDato);
				String vistaCheckBox = "";
				vistaCheckBox = "<h6>GRUPOS:</h6>";
				for(int i=0; i<listGrupos.size(); i++){
					String aux = "";
					for(int k=0; k<listIdGrupos.size(); k++){
						if(listIdGrupos.get(k).equals(listGrupos.get(i).getId().toString())) aux ="checked";
					}
					vistaCheckBox = vistaCheckBox  + "<input type='checkbox' name='listIdGrupos[]' value='" + listGrupos.get(i).getId() +
							"' " + aux +">" + listGrupos.get(i).getNombre() + "<br>";
				}
				return (reportGraficoMovResumen(request,listIdGrupos.toString(),vistaCheckBox));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reportGraficoMovPorGrupo(Http.Request request, String filtroGrupos, String filtroBodegas, String vistaCheckBox, String idTipoBodega ) {
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
		if(mapeoPermiso.get("reportGraficoGrupo2")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbWrite.getConnection()) {
			String subtitulo="";
			if(idTipoBodega.equals("0") ||idTipoBodega.equals("")) {
				subtitulo = "RESUMEN POR "+mapeoDiccionario.get("BODEGA")+"/INTERNA y "+mapeoDiccionario.get("BODEGA")+"/PROYECTO";
			}
			if(idTipoBodega.equals("1")){
				subtitulo = "RESUMEN POR "+mapeoDiccionario.get("BODEGA")+"/INTERNA";
			}
			if(idTipoBodega.equals("2")){
				subtitulo = "DETALLE POR "+mapeoDiccionario.get("BODEGA")+"/PROYECTO";
			}
			Map<Long,List<Double>> mapPCompra = Compra.ultimoPrecio(con, s.baseDato);
			Map<Long,List<Double>> mapPLista = Precio.maestroPListaPorSucursal(con, s.baseDato, Long.parseLong(s.id_sucursal));
			Map<Long,String> moneda = Moneda.mapIdMonedaMoneda(con, s.baseDato);
			Fechas hoy = Fechas.hoy();
			Map<Long,Double> tasasCorte = TasasCambio.mapTasasPorFecha(con, s.baseDato,hoy.getFechaStrAAMMDD(), mapeoDiccionario.get("pais"));
			Map<Long,Long> dec = Moneda.numeroDecimal(con, s.baseDato);
			Map<Long,UnidadTiempo> mapUnidadTiempo = UnidadTiempo.mapUnidadTiempo(con, s.baseDato);
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, s.baseDato);
			Map<Long,Equipo> mapEquipo = Equipo.mapAllAll(con, s.baseDato);
			List<List<String>> datos = ReportInventarios.reportInventarioEquipoConTipoBodega(con, s.baseDato, hoy.getFechaStrAAMMDD(), "", mapPCompra, mapPLista, moneda,
					mapeoDiccionario.get("ARRIENDO"), mapeoDiccionario, s.aplicaPorSucursal, s.id_sucursal, mapEquipo, mapSucursal, mapUnidadTiempo, tasasCorte, dec);
			mapPCompra = null;
			mapPLista = null;
			mapEquipo = null;
			List<String> valorizado = ReportGraficos.graficoDistribucionGrupoBodegasValorizado(mapeoDiccionario, filtroGrupos, filtroBodegas, idTipoBodega,
					s.aplicaPorSucursal, s.id_sucursal, datos);
			List<String> porUnidades = ReportGraficos.graficoDistribucionGrupoBodegasPorUnidades(mapeoDiccionario, filtroGrupos, filtroBodegas,idTipoBodega,
					s.aplicaPorSucursal, s.id_sucursal, datos);
			datos = null;
			String filtroPorNombreBodega = valorizado.get(2);
			String filtroPorNombreGrupo = valorizado.get(3);
			List<Grupo> listGrupos = Grupo.allFiltroPorNombre(con, s.baseDato, filtroPorNombreGrupo);
			List<BodegaEmpresa> listBodegas2 = BodegaEmpresa.allFiltroPorNombre(con, s.baseDato, filtroPorNombreBodega);
			return ok(reportGraficoMovPorGrupo.render(mapeoDiccionario,mapeoPermiso,userMnu,valorizado,porUnidades,listGrupos,listBodegas2,vistaCheckBox,idTipoBodega,subtitulo));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result refreshGraficoMovPorGrupo(Http.Request request, String idTipoBodega) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		FormGrafico form = formFactory.form(FormGrafico.class).withDirectFieldAccess(true).bindFromRequest(request).get();
		if (form.listIdGruposAll==null) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/",msgErrorFormulario));
		}else {
			try (Connection con = dbWrite.getConnection()) {
				List<String> listIdGruposAll = form.listIdGruposAll;
				List<String> listIdGrupos = form.listIdGrupos;
				if(listIdGrupos==null) {
					listIdGrupos = new ArrayList<String>();
				}
				List<String> listIdBodegasAll = form.listIdBodegasAll;
				List<String> listIdBodegas = form.listIdBodegas;
				if(listIdBodegas==null) {
					listIdBodegas = new ArrayList<String>();
				}
				List<Grupo> listGrupos = Grupo.allFiltroPorId(con, s.baseDato, listIdGruposAll.toString().substring(1,listIdGruposAll.toString().length()-1));
				List<BodegaEmpresa> listBodegas = BodegaEmpresa.allFiltroPorId(con, s.baseDato, listIdBodegasAll.toString().substring(1,listIdBodegasAll.toString().length()-1));
				String vistaCheckBox = "<h6>GRUPOS:</h6>";
				for(int i=0; i<listGrupos.size(); i++){
					String aux = "";
					for(int k=0; k<listIdGrupos.size(); k++){
						if(listIdGrupos.get(k).equals(listGrupos.get(i).getId().toString())) aux ="checked";
					}
					vistaCheckBox = vistaCheckBox  + "<input type='hidden' name='listIdGruposAll[]' value='"+listGrupos.get(i).getId()+"'>" + "<input type='checkbox' name='listIdGrupos[]' value='" + listGrupos.get(i).getId() +
							"' " + aux +">" + listGrupos.get(i).getNombre() + "<br>";
				}
				vistaCheckBox = vistaCheckBox  + "<br><h6>BODEGAS:</h6>";
				for(int i=0;i<listBodegas.size();i++){
					String aux = "";
					for(int k=0; k<listIdBodegas.size(); k++){
						if(listIdBodegas.get(k).equals(listBodegas.get(i).getId().toString().trim())) aux ="checked";
					}
					vistaCheckBox = vistaCheckBox  + "<input type='hidden' name='listIdBodegasAll[]' value='"+listBodegas.get(i).getId()+"'>" + "<input type='checkbox' name='listIdBodegas[]' value='" + listBodegas.get(i).getId() +
							"' " + aux +">" + listBodegas.get(i).getNombre() + "<br>";
				}
				return (reportGraficoMovPorGrupo(request,listIdGrupos.toString(),listIdBodegas.toString(),vistaCheckBox,idTipoBodega));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reportGraficoMovUso(Http.Request request) {
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
		if(mapeoPermiso.get("reportGraficoOcupacion1")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbWrite.getConnection()) {
			Map<Long,Equipo> mapEquipo = Equipo.mapAllAll(con, s.baseDato);
			Map<Long,TipoBodega> mapTipoBodega = TipoBodega.mapAll(con, s.baseDato);
			List<String> graficoOcupacionValorizado = ReportEjecutivos.graficoOcupacionPorGrupoValorizado(con, s.baseDato, mapeoDiccionario.get("pais"), s.aplicaPorSucursal, s.id_sucursal,
					mapTipoBodega, mapEquipo);
			List<String> graficoOcupacionUnidades = ReportGraficos.graficoOcupacionPorGrupoUnidades(con, s.baseDato, mapeoDiccionario.get("pais"), s.aplicaPorSucursal, s.id_sucursal);
			return ok(reportGraficoMovUso.render(mapeoDiccionario,mapeoPermiso,userMnu,graficoOcupacionValorizado,graficoOcupacionUnidades));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	//====================================================================================
	// MNU reportFacturaDetalleProyecto   Reportes/Proforma/ClienteProyecto
	//====================================================================================

	public Result reportFacturaPeriodo(Http.Request request) {
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
		if(mapeoPermiso.get("reportFacturaDetalleProyecto")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()) {
			Fechas hoy = Fechas.hoy();
			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			TasasCambio tasas = TasasCambio.allDeUnaFecha(con, s.baseDato, mapeoDiccionario.get("pais"),hasta);
			return ok(reportFacturaPeriodo.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta, tasas));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result reportFacturaProyecto(Http.Request request, String archivoPDF) {
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
			return ok(mensajes.render("/home/",msgErrorFormulario));
		}else {
			try {
				String desdeAAMMDD = form.get("fechaDesde").trim();
				String hastaAAMMDD = form.get("fechaHasta").trim();
				Double uf = Double.parseDouble(form.get("uf").replaceAll(",", "").trim());
				Double usd = Double.parseDouble(form.get("usd").replaceAll(",", "").trim());
				Double eur = Double.parseDouble(form.get("eur").replaceAll(",", "").trim());
				Map<Long, Double> tasas = new HashMap<Long, Double>();
				tasas.put((long) 1, (double) 1);    // 'Peso Chileno', 'CLP', '0'
				tasas.put((long) 2, usd);            // 'Dólar', 'USD', '2'
				tasas.put((long) 3, eur);            // 'Euro', 'EUR', '3'
				tasas.put((long) 4, uf);            // 'Unidad Fomento', 'UF', '4'
				Map<String, String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String, String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				List<Long> listIdBodegaEmpresa = null;
				Map<Long, Calc_BodegaEmpresa> mapBodegaEmpresa = null;
				Map<String, Calc_Precio> mapPrecios = null;
				Map<Long, Calc_Precio> mapMaestroPrecios = null;
				Map<String, Double> mapFijaTasas = null;
				List<Long> listIdGuia_fechaCorte = null;
				List<Inventarios> inventario = null;
				List<Long> listIdGuia_entreFechas = null;
				List<Inventarios> guiasPer = null;
				List<Calc_AjustesEP> listaAjustes = null;
				Map<Long, List<String>> mapBodega = null;
				Map<Long, Long> dec = null;
				Map<String, String> map = null;
				Map<String, String> mapPermanencias = null;
				try (Connection con = dbRead.getConnection()) {
					String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
					listIdBodegaEmpresa = ModCalc_InvInicial.listIdBodegaEmpresa(con, s.baseDato, permisoPorBodega);
					mapBodegaEmpresa = Calc_BodegaEmpresa.mapAllBodegasVigentes(con, s.baseDato);
					mapPrecios = Calc_Precio.mapPrecios(con, s.baseDato, listIdBodegaEmpresa);
					mapMaestroPrecios = Calc_Precio.mapMaestroPrecios(con, s.baseDato);
					mapFijaTasas = BodegaEmpresa.mapFijaTasasAll(con, s.baseDato);
					listIdGuia_fechaCorte = ModCalc_InvInicial.listIdGuia_fechaCorte(con, s.baseDato, desdeAAMMDD);
					inventario = Inventarios.inventario(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_fechaCorte);
					listIdGuia_entreFechas = ModCalc_GuiasPer.listIdGuia_entreFecha(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
					guiasPer = Inventarios.guiasPer(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_entreFechas);
					listaAjustes = Calc_AjustesEP.listaAjustesEntreFechas(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
					mapBodega = BodegaEmpresa.mapIdBod_BodegaEmpresaInternasExternas(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
					dec = Moneda.numeroDecimal(con, s.baseDato);
					map = UsuarioPermiso.mapPermisoIdBodega(con, s.baseDato, Long.parseLong(s.id_usuario));
					mapPermanencias = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, s.baseDato);
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				}
				ReportFacturas reporte = ModCalc_InvInicial.resumenInvInicial(s.baseDato, desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa,
						mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, listIdGuia_fechaCorte, inventario);
				inventario = null;
				List<ModCalc_InvInicial> inventarioInicial = reporte.resumenInvInicial;
				List<ModCalc_GuiasPer> guiasPeriodo = ModCalc_GuiasPer.resumenGuiasPer(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas,
						mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, guiasPer, mapPermanencias);
				mapBodegaEmpresa = null;
				mapPrecios = null;
				mapMaestroPrecios = null;
				guiasPer = null;
				mapPermanencias = null;
				List<ModeloCalculo> listado = ModeloCalculo.valorTotalporBodega(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, inventarioInicial, guiasPeriodo, listaAjustes);
				inventarioInicial = null;
				guiasPeriodo = null;
				listaAjustes = null;
				List<List<String>> proyectosAux = ReportFacturas.reportFacturaProyecto(listado, mapBodega);
				List<List<String>> resumenTotales = ReportFacturas.resumenTotalesPorProyecto(listado, dec);
				listado = null;
				List<List<String>> proyectos = new ArrayList<List<String>>();
				if (!map.isEmpty()) {
					for (List<String> aux : proyectosAux) {
						String idBodega = map.get(aux.get(1));
						if (idBodega != null) {
							proyectos.add(aux);
						}
					}
				} else {
					for (int i = 0; i < proyectosAux.size(); i++) {
						proyectos.add(proyectosAux.get(i));
					}
				}
				proyectosAux = null;
				String tabla = ""
						+ " <table id=\"tablaPrincipal\" class=\"table table-sm table-hover table-bordered table-condensed table-fluid\">\n"
						+ "		<thead style=\"background-color: #eeeeee\">\n"
						+ "			<TR> \n"
						+ "				<TH style= \"text-align: center;vertical-align: top;\">SUCURSAL</TH>\n"
						+ "				<TH style= \"text-align: center;vertical-align: top;\">COMERCIAL</TH>\n"
						+ "				<TH style= \"text-align: center;vertical-align: top;\">RUBRO</TH>\n"
						+ "				<TH width=\"5%\" >DETALLE<BR></TH>\n"
						+ "				<TH style= \"text-align: center;vertical-align: top;\">" + mapeoDiccionario.get("BODEGA") + "/PROYECTO</TH>\n"
						+ "				<TH style= \"text-align: center;vertical-align: top;\">NOMBRE<BR>CLIENTE</TH>\n"
						+ "				<TH style= \"text-align: center;vertical-align: top;\">NOMBRE<br>PROYECTO</TH>\n"
						+ "				<TH style= \"text-align:center;vertical-align:top;\">CFI (en " + mapeoDiccionario.get("PESOS") + ")</TH>\n"
						+ "				<TH style= \"text-align:center;vertical-align:top;\">SubTotal<br>" + mapeoDiccionario.get("ARRIENDO") + "</TH>\n"
						+ "				<TH style= \"text-align:center;vertical-align:top;\">SubTotal<br>VENTA</TH>\n"
						+ "				<TH style= \"text-align:center;vertical-align:top;\">Ajustes<BR>(" + mapeoDiccionario.get("ARRIENDO") + ")</TH>\n"
						+ "				<TH style= \"text-align:center;vertical-align:top;\">Ajustes<BR>(VENTA)</TH>\n"
						+ "				<TH style= \"text-align:center;vertical-align:top;\">TOTAL<BR>(en " + mapeoDiccionario.get("PESOS") + ")</TH>\n"
						+ "				<TH width=\"5%\" >DETALLE<BR></TH>\n"
						+ "			</TR>\n"
						+ "			</thead>\n"
						+ "			<tbody>\n";
				for (List<String> lista1 : proyectos) {
					tabla += ""
							+ "<TR class='lineasTR'>\n";
					for (List<String> total : resumenTotales) {
						if (lista1.get(1).equals(total.get(0))) {
							Double auxUf = uf;
							Double auxUsd = usd;
							Double auxEur = eur;
							for (Map.Entry<Long, Double> entry : tasas.entrySet()) {
								Long k = entry.getKey();
								Double aux = mapFijaTasas.get(lista1.get(1) + "_" + k);
								if (aux != null) {
									if (k == (long) 2) {
										auxUsd = aux;
									} else if (k == (long) 3) {
										auxEur = aux;
									} else if (k == (long) 4) {
										auxUf = aux;
									}
								}
							}
							tabla += ""
									+ "<td style=\"text-align:left;vertical-align:middle;\">" + lista1.get(14) + "</td>\n"
									+ "<td style=\"text-align:left;vertical-align:middle;\">" + lista1.get(10) + "</td>\n"
									+ "<td style=\"text-align:center;vertical-align:middle;\">" + lista1.get(15) + "</td>\n"
									+ "<td style=\"text-align:center;vertical-align:middle;\">\n"
									+ "	<form id=\"form0_" + lista1.get(1) + "\" class=\"formulario\" method=\"post\" action=\"/reportFacturaProyectoDetalle/\">\n"
									+ "		<input type=\"hidden\" class=\"idBodega\" name=\"id_bodega\" value=\"" + lista1.get(1) + "\">\n"
									+ "		<input type=\"hidden\" name=\"fechaDesde\" value=\"" + desdeAAMMDD + "\">\n"
									+ "		<input type=\"hidden\" name=\"fechaHasta\" value=\"" + hastaAAMMDD + "\">\n"
									+ "		<input type=\"hidden\" name=\"esVenta\" value=\"0\">\n"
									+ "		<input type=\"hidden\" name=\"uf\" value=\"" + uf + "\">\n"
									+ "		<input type=\"hidden\" name=\"usd\" value=\"" + usd + "\">\n"
									+ "		<input type=\"hidden\" name=\"eur\" value=\"" + eur + "\">\n"
									+ "		<a href=\"#\" onclick=\"document.getElementById('form0_" + lista1.get(1) + "').submit()\">\n"
									+ "			<kbd style=\"background-color: #73C6B6\">Emitir</kbd>\n"
									+ "		</a>\n"
									+ "	</form>\n"
									+ "</td>\n"
									+ "<td style=\"text-align:left;vertical-align:middle;\"><a href=\"#\" onclick=\"modalContactoBodega('" + lista1.get(1) + "');\">" + lista1.get(5) + "</a></td>\n"
									+ "<td style=\"text-align:left;vertical-align:middle;\"><a href=\"#\" onclick=\"modalContactoCliente('" + lista1.get(2) + "');\">" + lista1.get(7) + "</a></td>\n"
									+ "<td style=\"text-align:left;vertical-align:middle;\"><a href=\"#\" onclick=\"modalContactoProyecto('" + lista1.get(3) + "');\">" + lista1.get(8) + "</a></td>\n"
									+ "<td style= \"text-align:right;\" class=\"cfi\">" + total.get(3) + "</td>\n"
									+ "<td style= \"text-align:right;\" class=\"arr\">" + total.get(1) + "</td>\n"
									+ "<td style= \"text-align:right;\" class=\"vta\">" + total.get(2) + "</td>\n"
									+ "<td style= \"text-align:right;\" class=\"ajustArr\">" + total.get(5) + "</td>\n"
									+ "<td style= \"text-align:right;\" class=\"ajustVta\">" + total.get(6) + "</td>\n"
									+ "<td style= \"text-align:right;\" class=\"granTotal\">" + total.get(4) + "</td>\n"
									+ "<td style=\"text-align:center;vertical-align:middle;\">\n"
									+ "	<form id=\"form0_" + lista1.get(1) + "\" class=\"formulario\" method=\"post\" action=\"/reportFacturaProyectoDetalle/\">\n"
									+ "		<input type=\"hidden\" name=\"id_bodega\" value=\"" + lista1.get(1) + "\">\n"
									+ "		<input type=\"hidden\" name=\"fechaDesde\" value=\"" + desdeAAMMDD + "\">\n"
									+ "		<input type=\"hidden\" name=\"fechaHasta\" value=\"" + hastaAAMMDD + "\">\n"
									+ "		<input type=\"hidden\" name=\"esVenta\" value=\"0\">\n"
									+ "		<input type=\"hidden\" name=\"uf\" value=\"" + auxUf + "\">\n"
									+ "		<input type=\"hidden\" name=\"usd\" value=\"" + auxUsd + "\">\n"
									+ "		<input type=\"hidden\" name=\"eur\" value=\"" + auxEur + "\">\n"
									+ "		<a href=\"#\" onclick=\"document.getElementById('form0_" + lista1.get(1) + "').submit()\">\n"
									+ "			<kbd style=\"background-color: #73C6B6\">Emitir</kbd>\n"
									+ "		</a>\n"
									+ "	</form>\n"
									+ "</td>\n";
						}
					}
					tabla += ""
							+ "</TR>\n";
				}
				tabla += ""
						+ "</tbody>\n"
						+ "<tfoot>\n"
						+ "	<TR>\n"
						+ "		<td style=\"background-color: #eeeeee\">TOTALES</td>\n"
						+ "		<td style=\"background-color: #eeeeee\"></td>\n"
						+ "		<td style=\"background-color: #eeeeee\"></td>\n"
						+ "		<td style=\"background-color: #eeeeee\"></td>\n"
						+ "		<td style=\"background-color: #eeeeee\"></td>\n"
						+ "		<td style=\"background-color: #eeeeee\"></td>\n"
						+ "		<td style=\"background-color: #eeeeee\"></td>\n"
						+ "		<td style=\"background-color: #eeeeee;text-align:right;\" id=\"cfi\"></td>\n"
						+ "		<td style=\"background-color: #eeeeee;text-align:right;\" id=\"arr\"></td>\n"
						+ "		<td style=\"background-color: #eeeeee;text-align:right;\" id=\"vta\"></td>\n"
						+ "		<td style=\"background-color: #eeeeee;text-align:right;\" id=\"ajustArr\"></td>\n"
						+ "		<td style=\"background-color: #eeeeee;text-align:right;\" id=\"ajustVta\"></td>\n"
						+ "		<td style=\"background-color: #eeeeee;text-align:right;\" id=\"granTotal\"></td>\n"
						+ "		<td style=\"background-color: #eeeeee;text-align:right;\">\n"
						+ "	</TR>\n"
						+ "</tfoot>\n"
						+ "</table>";
				return ok(reportFacturaProyecto.render(mapeoDiccionario, mapeoPermiso, userMnu, tabla, desdeAAMMDD, hastaAAMMDD, usd, eur, uf,
						Fechas.DDMMAA(desdeAAMMDD), Fechas.DDMMAA(hastaAAMMDD), archivoPDF));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reportFacturaProyectoGet(Http.Request request, String archivoPDF,
										   String desdeAAMMDD,String hastaAAMMDD,Double uf,Double usd,Double eur) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		if(mapeoPermiso.get("reportFacturaDetalleProyecto")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/",msgErrorFormulario));
		}else {
			try {
				Map<Long, Double> tasas = new HashMap<Long, Double>();
				tasas.put((long) 1, (double) 1);    // 'Peso Chileno', 'CLP', '0'
				tasas.put((long) 2, usd);            // 'Dólar', 'USD', '2'
				tasas.put((long) 3, eur);            // 'Euro', 'EUR', '3'
				tasas.put((long) 4, uf);            // 'Unidad Fomento', 'UF', '4'
				Map<String, String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				List<Long> listIdBodegaEmpresa = null;
				Map<Long, Calc_BodegaEmpresa> mapBodegaEmpresa = null;
				Map<String, Calc_Precio> mapPrecios = null;
				Map<Long, Calc_Precio> mapMaestroPrecios = null;
				Map<String, Double> mapFijaTasas = null;
				List<Long> listIdGuia_fechaCorte = null;
				List<Inventarios> inventario = null;
				List<Long> listIdGuia_entreFechas = null;
				List<Inventarios> guiasPer = null;
				List<Calc_AjustesEP> listaAjustes = null;
				Map<Long, List<String>> mapBodega = null;
				Map<Long, Long> dec = null;
				Map<String, String> map = null;
				Map<String, String> mapPermanencias = null;
				try (Connection con = dbRead.getConnection()) {
					String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
					listIdBodegaEmpresa = ModCalc_InvInicial.listIdBodegaEmpresa(con, s.baseDato, permisoPorBodega);
					mapBodegaEmpresa = Calc_BodegaEmpresa.mapAllBodegasVigentes(con, s.baseDato);
					mapPrecios = Calc_Precio.mapPrecios(con, s.baseDato, listIdBodegaEmpresa);
					mapMaestroPrecios = Calc_Precio.mapMaestroPrecios(con, s.baseDato);
					mapFijaTasas = BodegaEmpresa.mapFijaTasasAll(con, s.baseDato);
					listIdGuia_fechaCorte = ModCalc_InvInicial.listIdGuia_fechaCorte(con, s.baseDato, desdeAAMMDD);
					inventario = Inventarios.inventario(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_fechaCorte);
					listIdGuia_entreFechas = ModCalc_GuiasPer.listIdGuia_entreFecha(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
					guiasPer = Inventarios.guiasPer(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_entreFechas);
					listaAjustes = Calc_AjustesEP.listaAjustesEntreFechas(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
					mapBodega = BodegaEmpresa.mapIdBod_BodegaEmpresaInternasExternas(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
					dec = Moneda.numeroDecimal(con, s.baseDato);
					map = UsuarioPermiso.mapPermisoIdBodega(con, s.baseDato, Long.parseLong(s.id_usuario));
					mapPermanencias = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, s.baseDato);
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				}
				ReportFacturas reporte = ModCalc_InvInicial.resumenInvInicial(s.baseDato, desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa,
						mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, listIdGuia_fechaCorte, inventario);
				inventario = null;
				List<ModCalc_InvInicial> inventarioInicial = reporte.resumenInvInicial;
				List<ModCalc_GuiasPer> guiasPeriodo = ModCalc_GuiasPer.resumenGuiasPer(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas,
						mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, guiasPer, mapPermanencias);
				mapBodegaEmpresa = null;
				mapPrecios = null;
				mapMaestroPrecios = null;
				guiasPer = null;
				mapPermanencias = null;
				List<ModeloCalculo> listado = ModeloCalculo.valorTotalporBodega(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, inventarioInicial, guiasPeriodo, listaAjustes);
				inventarioInicial = null;
				guiasPeriodo = null;
				listaAjustes = null;
				List<List<String>> proyectosAux = ReportFacturas.reportFacturaProyecto(listado, mapBodega);
				List<List<String>> resumenTotales = ReportFacturas.resumenTotalesPorProyecto(listado, dec);
				listado = null;
				List<List<String>> proyectos = new ArrayList<List<String>>();
				if (!map.isEmpty()) {
					for (List<String> aux : proyectosAux) {
						String idBodega = map.get(aux.get(1));
						if (idBodega != null) {
							proyectos.add(aux);
						}
					}
				} else {
					for (int i = 0; i < proyectosAux.size(); i++) {
						proyectos.add(proyectosAux.get(i));
					}
				}
				proyectosAux = null;
				String tabla = ""
						+ " <table id=\"tablaPrincipal\" class=\"table table-sm table-hover table-bordered table-condensed table-fluid\">\n"
						+ "		<thead style=\"background-color: #eeeeee\">\n"
						+ "			<TR> \n"
						+ "				<TH style= \"text-align: center;vertical-align: top;\">SUCURSAL</TH>\n"
						+ "				<TH style= \"text-align: center;vertical-align: top;\">COMERCIAL</TH>\n"
						+ "				<TH style= \"text-align: center;vertical-align: top;\">RUBRO</TH>\n"
						+ "				<TH width=\"5%\" >DETALLE<BR></TH>\n"
						+ "				<TH style= \"text-align: center;vertical-align: top;\">" + mapeoDiccionario.get("BODEGA") + "/PROYECTO</TH>\n"
						+ "				<TH style= \"text-align: center;vertical-align: top;\">NOMBRE<BR>CLIENTE</TH>\n"
						+ "				<TH style= \"text-align: center;vertical-align: top;\">NOMBRE<br>PROYECTO</TH>\n"
						+ "				<TH style= \"text-align:center;vertical-align:top;\">CFI (en " + mapeoDiccionario.get("PESOS") + ")</TH>\n"
						+ "				<TH style= \"text-align:center;vertical-align:top;\">SubTotal<br>" + mapeoDiccionario.get("ARRIENDO") + "</TH>\n"
						+ "				<TH style= \"text-align:center;vertical-align:top;\">SubTotal<br>VENTA</TH>\n"
						+ "				<TH style= \"text-align:center;vertical-align:top;\">Ajustes<BR>(" + mapeoDiccionario.get("ARRIENDO") + ")</TH>\n"
						+ "				<TH style= \"text-align:center;vertical-align:top;\">Ajustes<BR>(VENTA)</TH>\n"
						+ "				<TH style= \"text-align:center;vertical-align:top;\">TOTAL<BR>(en " + mapeoDiccionario.get("PESOS") + ")</TH>\n"
						+ "				<TH width=\"5%\" >DETALLE<BR></TH>\n"
						+ "			</TR>\n"
						+ "			</thead>\n"
						+ "			<tbody>\n";
				for (List<String> lista1 : proyectos) {
					tabla += ""
							+ "<TR class='lineasTR'>\n";
					for (List<String> total : resumenTotales) {
						if (lista1.get(1).equals(total.get(0))) {
							Double auxUf = uf;
							Double auxUsd = usd;
							Double auxEur = eur;
							for (Map.Entry<Long, Double> entry : tasas.entrySet()) {
								Long k = entry.getKey();
								Double aux = mapFijaTasas.get(lista1.get(1) + "_" + k);
								if (aux != null) {
									if (k == (long) 2) {
										auxUsd = aux;
									} else if (k == (long) 3) {
										auxEur = aux;
									} else if (k == (long) 4) {
										auxUf = aux;
									}
								}
							}
							tabla += ""
									+ "<td style=\"text-align:left;vertical-align:middle;\">" + lista1.get(14) + "</td>\n"
									+ "<td style=\"text-align:left;vertical-align:middle;\">" + lista1.get(10) + "</td>\n"
									+ "<td style=\"text-align:left;vertical-align:middle;\">" + lista1.get(15) + "</td>\n"
									+ "<td style=\"text-align:center;vertical-align:middle;\">\n"
									+ "	<form id=\"form0_" + lista1.get(1) + "\" class=\"formulario\" method=\"post\" action=\"/reportFacturaProyectoDetalle/\">\n"
									+ "		<input type=\"hidden\" class=\"idBodega\" name=\"id_bodega\" value=\"" + lista1.get(1) + "\">\n"
									+ "		<input type=\"hidden\" name=\"fechaDesde\" value=\"" + desdeAAMMDD + "\">\n"
									+ "		<input type=\"hidden\" name=\"fechaHasta\" value=\"" + hastaAAMMDD + "\">\n"
									+ "		<input type=\"hidden\" name=\"esVenta\" value=\"0\">\n"
									+ "		<input type=\"hidden\" name=\"uf\" value=\"" + uf + "\">\n"
									+ "		<input type=\"hidden\" name=\"usd\" value=\"" + usd + "\">\n"
									+ "		<input type=\"hidden\" name=\"eur\" value=\"" + eur + "\">\n"
									+ "		<a href=\"#\" onclick=\"document.getElementById('form0_" + lista1.get(1) + "').submit()\">\n"
									+ "			<kbd style=\"background-color: #73C6B6\">Emitir</kbd>\n"
									+ "		</a>\n"
									+ "	</form>\n"
									+ "</td>\n"
									+ "<td style=\"text-align:left;vertical-align:middle;\"><a href=\"#\" onclick=\"modalContactoBodega('" + lista1.get(1) + "');\">" + lista1.get(5) + "</a></td>\n"
									+ "<td style=\"text-align:left;vertical-align:middle;\"><a href=\"#\" onclick=\"modalContactoCliente('" + lista1.get(2) + "');\">" + lista1.get(7) + "</a></td>\n"
									+ "<td style=\"text-align:left;vertical-align:middle;\"><a href=\"#\" onclick=\"modalContactoProyecto('" + lista1.get(3) + "');\">" + lista1.get(8) + "</a></td>\n"
									+ "<td style= \"text-align:right;\" class=\"cfi\">" + total.get(3) + "</td>\n"
									+ "<td style= \"text-align:right;\" class=\"arr\">" + total.get(1) + "</td>\n"
									+ "<td style= \"text-align:right;\" class=\"vta\">" + total.get(2) + "</td>\n"
									+ "<td style= \"text-align:right;\" class=\"ajustArr\">" + total.get(5) + "</td>\n"
									+ "<td style= \"text-align:right;\" class=\"ajustVta\">" + total.get(6) + "</td>\n"
									+ "<td style= \"text-align:right;\" class=\"granTotal\">" + total.get(4) + "</td>\n"
									+ "<td style=\"text-align:center;vertical-align:middle;\">\n"
									+ "	<form id=\"form0_" + lista1.get(1) + "\" class=\"formulario\" method=\"post\" action=\"/reportFacturaProyectoDetalle/\">\n"
									+ "		<input type=\"hidden\" name=\"id_bodega\" value=\"" + lista1.get(1) + "\">\n"
									+ "		<input type=\"hidden\" name=\"fechaDesde\" value=\"" + desdeAAMMDD + "\">\n"
									+ "		<input type=\"hidden\" name=\"fechaHasta\" value=\"" + hastaAAMMDD + "\">\n"
									+ "		<input type=\"hidden\" name=\"esVenta\" value=\"0\">\n"
									+ "		<input type=\"hidden\" name=\"uf\" value=\"" + auxUf + "\">\n"
									+ "		<input type=\"hidden\" name=\"usd\" value=\"" + auxUsd + "\">\n"
									+ "		<input type=\"hidden\" name=\"eur\" value=\"" + auxEur + "\">\n"
									+ "		<a href=\"#\" onclick=\"document.getElementById('form0_" + lista1.get(1) + "').submit()\">\n"
									+ "			<kbd style=\"background-color: #73C6B6\">Emitir</kbd>\n"
									+ "		</a>\n"
									+ "	</form>\n"
									+ "</td>\n";
						}
					}
					tabla += ""
							+ "</TR>\n";
				}
				tabla += ""
						+ "</tbody>\n"
						+ "<tfoot>\n"
						+ "	<TR>\n"
						+ "		<td style=\"background-color: #eeeeee\">TOTALES</td>\n"
						+ "		<td style=\"background-color: #eeeeee\"></td>\n"
						+ "		<td style=\"background-color: #eeeeee\"></td>\n"
						+ "		<td style=\"background-color: #eeeeee\"></td>\n"
						+ "		<td style=\"background-color: #eeeeee\"></td>\n"
						+ "		<td style=\"background-color: #eeeeee\"></td>\n"
						+ "		<td style=\"background-color: #eeeeee\"></td>\n"
						+ "		<td style=\"background-color: #eeeeee;text-align:right;\" id=\"cfi\"></td>\n"
						+ "		<td style=\"background-color: #eeeeee;text-align:right;\" id=\"arr\"></td>\n"
						+ "		<td style=\"background-color: #eeeeee;text-align:right;\" id=\"vta\"></td>\n"
						+ "		<td style=\"background-color: #eeeeee;text-align:right;\" id=\"ajustArr\"></td>\n"
						+ "		<td style=\"background-color: #eeeeee;text-align:right;\" id=\"ajustVta\"></td>\n"
						+ "		<td style=\"background-color: #eeeeee;text-align:right;\" id=\"granTotal\"></td>\n"
						+ "		<td style=\"background-color: #eeeeee;text-align:right;\">\n"
						+ "	</TR>\n"
						+ "</tfoot>\n"
						+ "</table>";
				return ok(reportFacturaProyecto.render(mapeoDiccionario, mapeoPermiso, userMnu, tabla, desdeAAMMDD, hastaAAMMDD, usd, eur, uf,
						Fechas.DDMMAA(desdeAAMMDD), Fechas.DDMMAA(hastaAAMMDD), archivoPDF));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reportFacturaProyectoExcel(Http.Request request) {
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
			return ok(mensajes.render("/home/",msgErrorFormulario));
		}else {
			try {
				String desdeAAMMDD = form.get("fechaDesde").trim();
				String hastaAAMMDD = form.get("fechaHasta").trim();
				Double uf = Double.parseDouble(form.get("uf").replaceAll(",", "").trim());
				Double usd = Double.parseDouble(form.get("usd").replaceAll(",", "").trim());
				Double eur = Double.parseDouble(form.get("eur").replaceAll(",", "").trim());
				Map<Long, Double> tasas = new HashMap<Long, Double>();
				tasas.put((long) 1, (double) 1);    // 'Peso Chileno', 'CLP', '0'
				tasas.put((long) 2, usd);            // 'Dólar', 'USD', '2'
				tasas.put((long) 3, eur);            // 'Euro', 'EUR', '3'
				tasas.put((long) 4, uf);            // 'Unidad Fomento', 'UF', '4'
				Map<String, String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				Fechas desde = null;
				Fechas hasta = null;
				List<Long> listIdBodegaEmpresa = null;
				Map<Long, Calc_BodegaEmpresa> mapBodegaEmpresa = null;
				Map<String, Calc_Precio> mapPrecios = null;
				Map<Long, Calc_Precio> mapMaestroPrecios = null;
				Map<String, Double> mapFijaTasas = null;
				List<Long> listIdGuia_fechaCorte = null;
				List<Inventarios> inventario = null;
				List<Long> listIdGuia_entreFechas = null;
				List<Inventarios> guiasPer = null;
				List<Calc_AjustesEP> listaAjustes = null;
				Map<Long, List<String>> mapBodega = null;
				Map<Long, Long> dec = null;
				Map<String, String> map = null;
				Map<String, String> mapPermanencias = null;
				try (Connection con = dbRead.getConnection()) {
					desde = Fechas.obtenerFechaDesdeStrAAMMDD(desdeAAMMDD);
					hasta = Fechas.obtenerFechaDesdeStrAAMMDD(hastaAAMMDD);
					String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
					listIdBodegaEmpresa = ModCalc_InvInicial.listIdBodegaEmpresa(con, s.baseDato, permisoPorBodega);
					mapBodegaEmpresa = Calc_BodegaEmpresa.mapAllBodegasVigentes(con, s.baseDato);
					mapPrecios = Calc_Precio.mapPrecios(con, s.baseDato, listIdBodegaEmpresa);
					mapMaestroPrecios = Calc_Precio.mapMaestroPrecios(con, s.baseDato);
					mapFijaTasas = BodegaEmpresa.mapFijaTasasAll(con, s.baseDato);
					listIdGuia_fechaCorte = ModCalc_InvInicial.listIdGuia_fechaCorte(con, s.baseDato, desdeAAMMDD);
					inventario = Inventarios.inventario(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_fechaCorte);
					listIdGuia_entreFechas = ModCalc_GuiasPer.listIdGuia_entreFecha(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
					guiasPer = Inventarios.guiasPer(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_entreFechas);
					listaAjustes = Calc_AjustesEP.listaAjustesEntreFechas(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
					mapBodega = BodegaEmpresa.mapIdBod_BodegaEmpresaInternasExternas(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
					dec = Moneda.numeroDecimal(con, s.baseDato);
					map = UsuarioPermiso.mapPermisoIdBodega(con, s.baseDato, Long.parseLong(s.id_usuario));
					mapPermanencias = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, s.baseDato);
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				}
				ReportFacturas reporte = ModCalc_InvInicial.resumenInvInicial(s.baseDato, desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa,
						mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, listIdGuia_fechaCorte, inventario);
				inventario = null;
				List<ModCalc_InvInicial> inventarioInicial = reporte.resumenInvInicial;
				List<ModCalc_GuiasPer> guiasPeriodo = ModCalc_GuiasPer.resumenGuiasPer(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas,
						mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, guiasPer, mapPermanencias);
				mapBodegaEmpresa = null;
				mapPrecios = null;
				mapMaestroPrecios = null;
				guiasPer = null;
				mapPermanencias = null;
				List<ModeloCalculo> listado = ModeloCalculo.valorTotalporBodega(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, inventarioInicial, guiasPeriodo, listaAjustes);
				inventarioInicial = null;
				guiasPeriodo = null;
				listaAjustes = null;
				List<List<String>> proyectosAux = ReportFacturas.reportFacturaProyecto(listado, mapBodega);
				List<List<String>> resumenTotales = ReportFacturas.resumenTotalesPorProyecto(listado, dec);
				listado = null;
				List<List<String>> proyectos = new ArrayList<List<String>>();
				if (!map.isEmpty()) {
					for (List<String> aux : proyectosAux) {
						String idBodega = map.get(aux.get(1));
						if (idBodega != null) {
							proyectos.add(aux);
						}
					}
				} else {
                    proyectos.addAll(proyectosAux);
				}
				File file = ReportFacturas.exportaProformaExcelProyectos(s.baseDato, mapeoDiccionario,
						proyectos,desde,hasta,uf,usd,eur,resumenTotales);
				return ok(file,false,Optional.of("EP_Proforma_Listado.xlsx"));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public class envioMasivoListadoProforma0 extends Thread {
		String desdeAAMMDD;
		String hastaAAMMDD;
		Double uf;
		Double usd;
		Double eur;
		String esVenta;
		String eMail;
		Sessiones s;
		FormFactura form;

		public envioMasivoListadoProforma0(String desdeAAMMDD, String hastaAAMMDD, Double uf,
										   Double usd, Double eur, String esVenta, String eMail, Sessiones s, FormFactura form) {
			super();
			this.desdeAAMMDD = desdeAAMMDD;
			this.hastaAAMMDD = hastaAAMMDD;
			this.uf = uf;
			this.usd = usd;
			this.eur = eur;
			this.esVenta = esVenta;
			this.eMail = eMail;
			this.s = s;
			this.form = form;
		}

		public void run() {
			String className = this.getClass().getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			try (Connection con = dbWrite.getConnection()) {
				Parametros.modify(con, s.baseDato, "envioMasivoProformas", (long)2);
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			}
			String nros = "";
			try {
				Map<Long,Double> tasas = new HashMap<Long,Double>();
				tasas.put((long)1, (double) 1); 	// 'Peso Chileno', 'CLP', '0'
				tasas.put((long)2, usd); 			// 'Dólar', 'USD', '2'
				tasas.put((long)3, eur); 			// 'Euro', 'EUR', '3'
				tasas.put((long)4, uf); 			// 'Unidad Fomento', 'UF', '4'
				Map<String,String> mapeoPermisoMs2 = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionarioMs2 = HomeController.mapDiccionario(s.baseDato);
				String permisoPorBodegaMs2 = null;
				List<Long> listIdBodegaEmpresaMs2 = null;
				Map<Long,Calc_BodegaEmpresa> mapBodegaEmpresaMs2 = null;
				Map<String,Calc_Precio> mapPreciosMs2 = null;
				Map<Long,Calc_Precio> mapMaestroPreciosMs2 = null;
				Map<String, Double> mapFijaTasasMs2 = null;
				List<Long> listIdGuia_fechaCorteMs2 = null;
				List<Inventarios> inventarioMs2 = null;
				List<Long> listIdGuia_entreFechasMs2 = null;
				List<Inventarios> guiasPerMs2 = null;
				List<Calc_AjustesEP> listaAjustesMs2 = null;
				Map<Long,List<String>> mapBodegaMs2 = null;
				Map<Long,Long> decMs2 = null;
				Map<String,String> mapMs2 = null;
				Map<String,String> mapPermanenciasMs2 = null;
				Map<Long,String> mapMonedaMs2 = null;
				Map<Long,Equipo> mapAllEquiposMs2 = null;
				Map<String,String> mapFecha_primera_guiaMs2 = null;
				Fechas hoy = null;
				try (Connection con = dbWrite.getConnection()) {
					Parametros.modify(con, s.baseDato, "envioMasivoProformas", (long)2);
					permisoPorBodegaMs2 = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
					listIdBodegaEmpresaMs2 = ModCalc_InvInicial.listIdBodegaEmpresa(con, s.baseDato, permisoPorBodegaMs2);
					mapBodegaEmpresaMs2 = Calc_BodegaEmpresa.mapAllBodegasVigentes(con, s.baseDato);
					mapPreciosMs2 = Calc_Precio.mapPrecios(con, s.baseDato, listIdBodegaEmpresaMs2);
					mapMaestroPreciosMs2 = Calc_Precio.mapMaestroPrecios(con, s.baseDato);
					mapFijaTasasMs2 = BodegaEmpresa.mapFijaTasasAll(con, s.baseDato);
					listIdGuia_fechaCorteMs2 = ModCalc_InvInicial.listIdGuia_fechaCorte(con, s.baseDato, desdeAAMMDD);
					inventarioMs2 = Inventarios.inventario(con, s.baseDato, listIdBodegaEmpresaMs2, listIdGuia_fechaCorteMs2);
					listIdGuia_entreFechasMs2 = ModCalc_GuiasPer.listIdGuia_entreFecha(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
					guiasPerMs2 = Inventarios.guiasPer(con, s.baseDato, listIdBodegaEmpresaMs2, listIdGuia_entreFechasMs2);
					listaAjustesMs2 = Calc_AjustesEP.listaAjustesEntreFechas(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
					mapBodegaMs2 = BodegaEmpresa.mapIdBod_BodegaEmpresaInternasExternas(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
					decMs2 = Moneda.numeroDecimal(con, s.baseDato);
					mapMs2 = UsuarioPermiso.mapPermisoIdBodega(con, s.baseDato, Long.parseLong(s.id_usuario));
					mapPermanenciasMs2 = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, s.baseDato);
					mapMonedaMs2 = Moneda.mapIdMonedaMoneda(con, s.baseDato);
					mapAllEquiposMs2 = Equipo.mapAllAll(con, s.baseDato);
					mapFecha_primera_guiaMs2 = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, s.baseDato);
					hoy = Fechas.hoy();
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				}
				ReportFacturas reporte = ModCalc_InvInicial.resumenInvInicial(s.baseDato,desdeAAMMDD, hastaAAMMDD, mapFijaTasasMs2, tasas, listIdBodegaEmpresaMs2,
						mapBodegaEmpresaMs2, mapPreciosMs2, mapMaestroPreciosMs2, listIdGuia_fechaCorteMs2, inventarioMs2);
				List<ModCalc_InvInicial> inventarioInicialMs2 = reporte.resumenInvInicial;
				reporte = null;
				List<ModCalc_GuiasPer> guiasPeriodoMs2 = ModCalc_GuiasPer.resumenGuiasPer(desdeAAMMDD, hastaAAMMDD, mapFijaTasasMs2, tasas,
						mapBodegaEmpresaMs2, mapPreciosMs2, mapMaestroPreciosMs2, guiasPerMs2, mapPermanenciasMs2);
				List<ModeloCalculo> listadoMs2 = ModeloCalculo.valorTotalporBodega(desdeAAMMDD, hastaAAMMDD, mapFijaTasasMs2, tasas, inventarioInicialMs2,guiasPeriodoMs2, listaAjustesMs2);
				inventarioInicialMs2 = null;
				guiasPeriodoMs2 = null;
				List<List<String>> proyectosAuxMs2 = ReportFacturas.reportFacturaProyecto(listadoMs2, mapBodegaMs2);
				List<List<String>> resumenTotalesMs2 = ReportFacturas.resumenTotalesPorProyecto(listadoMs2, decMs2);
				listadoMs2 = null;
				List<List<String>> proyectosMs2 = new ArrayList<List<String>>();
				if(!mapMs2.isEmpty()) {
					for(List<String> aux: proyectosAuxMs2) {
						String idBodega = mapMs2.get(aux.get(1));
						if(idBodega!=null) {
							proyectosMs2.add(aux);
						}
					}
				}else {
					for(int i=0;i<proyectosAuxMs2.size();i++ ) {
						proyectosMs2.add(proyectosAuxMs2.get(i));
					}
				}
				proyectosAuxMs2 = null;
				for(List<String> lista1: proyectosMs2){
					for(List<String> total: resumenTotalesMs2){
						if(lista1.get(1).equals(total.get(0))){
							Double valorArr = Double.parseDouble((total.get(1)).replaceAll(",", ""));
							Double valorVta = Double.parseDouble((total.get(2)).replaceAll(",", ""));
							Double valorGranTotal = Double.parseDouble((total.get(4)).replaceAll(",", ""));
							Double valorCompara = valorArr;
							if("1".equals(esVenta)) {
								valorCompara = valorVta;
							}
							if(valorGranTotal>(double)0 && valorCompara>(double)0) {
								String aux = MnuReportes.envioMasivoListadoProforma1(s, desdeAAMMDD, hastaAAMMDD,tasas,
										Long.parseLong(lista1.get(1)), esVenta,
										permisoPorBodegaMs2, listIdBodegaEmpresaMs2, mapBodegaEmpresaMs2,mapPreciosMs2,
										mapMaestroPreciosMs2, mapFijaTasasMs2, listIdGuia_fechaCorteMs2, inventarioMs2,
										listIdGuia_entreFechasMs2, guiasPerMs2, decMs2, mapPermanenciasMs2, mapFecha_primera_guiaMs2,
										mapeoPermisoMs2, mapeoDiccionarioMs2, form,
										mapMonedaMs2, mapAllEquiposMs2, hoy);
								nros += aux +", ";
							}
						}
					}
				}
				proyectosMs2 = null;
				resumenTotalesMs2 = null;
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			}
			try {
				Email email = new Email();
				String asunto = "ENVIO MASIVO A: Listado de Proformas";
				String desde = "desde MADA <informaciones@inqsol.cl>";
				email.setSubject(asunto);
				email.setFrom(desde);
				email.setBodyHtml("<html><body>" +
						" <p>Numeros de proforma generados: "+nros+"</p>" +
						" <p>Nota: por favor no responder este correo</p>" +
						" </body></html>");
				email.addTo(eMail);
				mailerClient.send(email);
			} catch (Exception x) {
				Email email = new Email();
				String asunto = "ENVIO MASIVO A: Listado de Proformas";
				String desde = "desde MADA <informaciones@inqsol.cl>";
				email.setSubject(asunto);
				email.setFrom(desde);
				email.setBodyHtml("<html><body>" +
						" <p><b>NO SE GENERARON PROFORMAS</b></p>" +
						" <p>Nota: por favor no responder este correo</p>" +
						" </body></html>");
				email.addTo(eMail);
				mailerClient.send(email);
			}
			try (Connection con = dbWrite.getConnection()) {
				Parametros.modify(con, s.baseDato, "envioMasivoProformas", (long)1);
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			}
		}
	}

	public static String envioMasivoListadoProforma1(Sessiones s, String desdeAAMMDDMs2, String hastaAAMMDDMs2, Map<Long,Double> tasas,
													 Long id_bodegaEmpresa, String esVenta,
													 String permisoPorBodega, List<Long> listIdBodegaEmpresa, Map<Long,Calc_BodegaEmpresa> mapBodegaEmpresa, Map<String,Calc_Precio> mapPrecios,
													 Map<Long,Calc_Precio> mapMaestroPrecios, Map<String, Double> mapFijaTasas, List<Long> listIdGuia_fechaCorteMs2, List<Inventarios> inventarioMs2,
													 List<Long> listIdGuia_entreFechas, List<Inventarios> guiasPer, Map<Long,Long> dec, Map<String,String> mapPermanencias, Map<String,String> mapFecha_primera_guia,
													 Map<String,String> mapeoPermiso, Map<String,String> mapeoDiccionario, FormFactura form,
													 Map<Long,String> mapMoneda, Map<Long,Equipo> mapAllEquipos, Fechas hoy) {
		String className = ActaBaja.class.getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		Map<Long,Cotizacion> mapCotiAllConfirmadasMs2 = null;
		List<List<String>> listGuiasPerMs2 = null;
		BodegaEmpresa bodegaMs2 = null;
		Proyecto proyectoMs2 = null;
		Cliente clienteMs2 = null;
		List<List<String>> detalleAjusteMs2 = null;
		String ocMs2 = null;
		Proforma proformaMs2 = null;
		Long cantDecMs2 = null;
		List<List<String>> inicioPerMs2 = null;
		Map<String,List<List<String>>> mapReportPorGuia10Ms2 = null;
		List<List<String>> resumenSubtotalesMs2 = null;
		Fechas hastaMs2 = null;
		Calendar hastaMas1Ms2 = null;
		java.sql.Date masUnDiaMs2 = null;
		try (Connection con = dbWrite.getConnection()) {
			mapCotiAllConfirmadasMs2 = Cotizacion.mapAllConfirmadasUnaBodega(con, s.baseDato, id_bodegaEmpresa);
			listGuiasPerMs2 = ReportFacturas.reportListGuiasEntreFechas(con, s.baseDato, id_bodegaEmpresa, desdeAAMMDDMs2, hastaAAMMDDMs2);
			bodegaMs2 = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
			proyectoMs2 = Proyecto.find(con,s.baseDato , bodegaMs2.getId_proyecto());
			clienteMs2 = Cliente.find(con, s.baseDato, bodegaMs2.getId_cliente());
			detalleAjusteMs2 = AjustesEP.detalleAjuste(con, s.baseDato, id_bodegaEmpresa, desdeAAMMDDMs2, hastaAAMMDDMs2);
			ocMs2 = Cotizacion.ocParticiaEnBodega(con, s.baseDato, id_bodegaEmpresa);
			proformaMs2 = Proforma.createSinNada(con, s.baseDato, hoy.getFechaStrAAMMDD());
			cantDecMs2 = dec.get((long)1);
			inicioPerMs2 = ReportFacturas.reportEstadoInicial10(s.baseDato, id_bodegaEmpresa, desdeAAMMDDMs2, hastaAAMMDDMs2, mapFijaTasas, tasas, listIdBodegaEmpresa, mapBodegaEmpresa,
					mapPrecios, mapMaestroPrecios, listIdGuia_fechaCorteMs2, inventarioMs2, mapFecha_primera_guia, mapCotiAllConfirmadasMs2, mapMoneda, mapAllEquipos, dec);
			mapReportPorGuia10Ms2 = ReportFacturas.mapReportPorGuia10(s.baseDato, id_bodegaEmpresa, desdeAAMMDDMs2, hastaAAMMDDMs2, mapFijaTasas, tasas,
					mapBodegaEmpresa, mapPrecios, mapMaestroPrecios,
					guiasPer, mapPermanencias, dec,  mapCotiAllConfirmadasMs2, mapAllEquipos, mapMoneda);
			resumenSubtotalesMs2 = ReportFacturas.reportEstadoResumen10(s.baseDato, inicioPerMs2, listGuiasPerMs2, id_bodegaEmpresa, desdeAAMMDDMs2, hastaAAMMDDMs2, mapFijaTasas, tasas,
					listIdBodegaEmpresa, mapBodegaEmpresa, mapPrecios, mapMaestroPrecios,
					listIdGuia_entreFechas, mapPermanencias, dec, mapCotiAllConfirmadasMs2, mapAllEquipos, mapMoneda, guiasPer);
			hastaMs2 = Fechas.obtenerFechaDesdeStrAAMMDD(hastaAAMMDDMs2);
			hastaMas1Ms2 = hastaMs2.getFechaCal();
			hastaMas1Ms2.add(Calendar.DAY_OF_MONTH, 1);
			masUnDiaMs2 = new java.sql.Date(hastaMas1Ms2.getTimeInMillis());
			listIdGuia_fechaCorteMs2 = ModCalc_InvInicial.listIdGuia_fechaCorte(con, s.baseDato, masUnDiaMs2.toString());
			inventarioMs2 = Inventarios.inventario(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_fechaCorteMs2);
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
		}
		try{
			List<List<String>> finalPerMs2 = ReportFacturas.reportEstadoInicial10(s.baseDato, id_bodegaEmpresa, masUnDiaMs2.toString(), hastaAAMMDDMs2, mapFijaTasas, tasas, listIdBodegaEmpresa, mapBodegaEmpresa,
					mapPrecios, mapMaestroPrecios, listIdGuia_fechaCorteMs2, inventarioMs2, mapFecha_primera_guia, mapCotiAllConfirmadasMs2, mapMoneda, mapAllEquipos, dec);
			mapCotiAllConfirmadasMs2 = null;
			listIdGuia_fechaCorteMs2 = null;
			inventarioMs2 = null;
			mapAllEquipos = null;
			mapBodegaEmpresa = null;
			listIdBodegaEmpresa = null;
			mapPrecios = null;
			mapMaestroPrecios = null;
			List<String> fechasMs2 = new ArrayList<String>();
			fechasMs2.add(desdeAAMMDDMs2);
			fechasMs2.add(hastaAAMMDDMs2);
			fechasMs2.add(Fechas.DDMMAA(desdeAAMMDDMs2));
			fechasMs2.add(Fechas.DDMMAA(hastaAAMMDDMs2));
			List<Double> tasaCambioMs2 = new ArrayList<Double>();
			tasaCambioMs2.add(tasas.get((long)4)); // 'Unidad Fomento', 'UF', '4'
			tasaCambioMs2.add(tasas.get((long)2)); // 'Dólar', 'USD', '2'
			tasaCambioMs2.add(tasas.get((long)3)); // 'Euro', 'EUR', '3'
			proformaMs2.setFecha(hoy.getFechaStrAAMMDD());
			proformaMs2.setDesde(desdeAAMMDDMs2);
			proformaMs2.setHasta(hastaAAMMDDMs2);
			proformaMs2.setId_cliente(clienteMs2.id);
			proformaMs2.setId_bodegaEmpresa(bodegaMs2.id);
			proformaMs2.setId_proyecto(proyectoMs2.id);
			proformaMs2.setDocRef("--");
			proformaMs2.setEpExcelMov("PRmov_" + proformaMs2.id + "_proformaArriendo.xlsx");
			proformaMs2.setEpExcelEp("PRep_" + proformaMs2.id + "_proformaArriendo.xlsx");
			proformaMs2.setProformaPdf("PRpdf" + proformaMs2.id + "_proformaArriendo.pdf");
			proformaMs2.setProformaXml("PRxml" + proformaMs2.id + "_proformaArriendo.xml");
			proformaMs2.setDocAnexo("0");
			proformaMs2.setDescuento((double)0);
			proformaMs2.setNeto((double)0);
			proformaMs2.setIva((double)0);
			proformaMs2.setTotal((double)0);
			proformaMs2.setTipo(mapeoDiccionario.get("Arriendo"));
			File fileMs2 = ReportFacturas.reportFacturaProyectoDetExcel(s.baseDato,mapeoDiccionario,mapeoPermiso,
					inicioPerMs2,listGuiasPerMs2,fechasMs2,bodegaMs2,proyectoMs2,tasaCambioMs2,
					resumenSubtotalesMs2,finalPerMs2,clienteMs2,detalleAjusteMs2,mapReportPorGuia10Ms2, cantDecMs2);
			String fileOutNameDetalle = s.baseDato+"/"+proformaMs2.getEpExcelEp();
			Archivos.grabarArchivo(fileMs2, fileOutNameDetalle);
			String conceptoMs2 = mapeoDiccionario.get("ARRIENDO");
			if("1".equals(esVenta)) {
				conceptoMs2 = "VENTA";
			}
			try (Connection con2 = dbWrite.getConnection()) {
				List<List<String>> datosMs2 = ReportMovimientos.movimientoGuias(con2, s.baseDato, mapeoDiccionario, id_bodegaEmpresa, esVenta, desdeAAMMDDMs2, hastaAAMMDDMs2,
						tasas.get((long)2), tasas.get((long)3), tasas.get((long)4), conceptoMs2);
				String fileOutNameMovimientosMs2 = proformaMs2.getEpExcelMov();
				fileMs2 = ReportMovimientos.movimientosExcel(s.baseDato, datosMs2, mapeoDiccionario, bodegaMs2, conceptoMs2, desdeAAMMDDMs2, hastaAAMMDDMs2);
				Archivos.grabarArchivo(fileMs2, s.baseDato+"/"+fileOutNameMovimientosMs2);
				bodegaMs2 = null;
				XmlFacturaReferencias referenciasMs2 = new XmlFacturaReferencias();
				if(form.tpoDocRef!=null) {
					referenciasMs2.tpoDocRef = form.tpoDocRef;
					referenciasMs2.folioRef = form.folioRef;
					referenciasMs2.fchRef = form.fchRef;
					referenciasMs2.razonRef = form.razonRef;
					referenciasMs2.obs = "";
				}
				String comentarios = form.comentarios;
				EmisorTributario emisorTributarioMs2 = EmisorTributario.find(con2, s.baseDato);
				BodegaEmpresa bodegaEmpresaMs2 = BodegaEmpresa.findXIdBodega(con2, s.baseDato, proformaMs2.id_bodegaEmpresa);
				if(esVenta.equals("0")) {
					// genera PDF de arriendo, XML, JSON y guarda json en proforma
					proformaMs2.setTipo(mapeoDiccionario.get("Arriendo"));
					String conDetalleMs2 = mapeoPermiso.get("parametro.proformaInvConCompra");
					FormFactura.generaProformaArriendo(con2, s.baseDato, ws, mapeoDiccionario, mapeoPermiso,
							resumenSubtotalesMs2,clienteMs2,proformaMs2,referenciasMs2,detalleAjusteMs2,conDetalleMs2, inicioPerMs2, listGuiasPerMs2, mapReportPorGuia10Ms2, finalPerMs2,
							tasas.get((long)4), tasas.get((long)2), tasas.get((long)3), ocMs2,dec, emisorTributarioMs2, bodegaEmpresaMs2, comentarios, form);
				}else {
					// genera PDF de venta, XML, JSON y guarda json en proforma
					proformaMs2.setTipo("Venta");
					FormFactura.generaProformaVenta(con2, s.baseDato, ws, mapeoDiccionario, mapeoPermiso,
							clienteMs2,proformaMs2,referenciasMs2,detalleAjusteMs2, listGuiasPerMs2, mapReportPorGuia10Ms2, ocMs2, comentarios, form);
				}
				listGuiasPerMs2 = null;
				inicioPerMs2 = null;
				mapReportPorGuia10Ms2 = null;
				resumenSubtotalesMs2 = null;
				finalPerMs2 = null;
				return(proformaMs2.id.toString());
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			}
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
		}
		return(null);
	}

	public Result envioMasivoListadoProforma2(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		DynamicForm formEsVenta = formFactory.form().bindFromRequest(request);
		FormFactura form = formFactory.form(FormFactura.class).withDirectFieldAccess(true).bindFromRequest(request).get();
		if (formEsVenta.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/",msgErrorFormulario));
		}else {
			try {
				String desdeAAMMDD = form.fechaDesde;
				String hastaAAMMDD = form.fechaHasta;
				Double uf = Double.parseDouble(form.uf.replaceAll(",", "").trim());
				Double usd = Double.parseDouble(form.usd.replaceAll(",", "").trim());
				Double eur = Double.parseDouble(form.eur.replaceAll(",", "").trim());
				String esVenta = formEsVenta.get("esVenta").trim();
				String mailDestino = null;
				try (Connection con = dbRead.getConnection()) {
					Usuario usuario = Usuario.findXIdUser(con, s.baseDato, Long.parseLong(s.id_usuario));
					if (usuario != null) {
						mailDestino = usuario.getEmail().trim().toLowerCase();
						if (mailDestino.length() < 4) {
							usuario = null;
						}
					}
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				}
				if (HomeController.isValidEmail(mailDestino)) {
					if (mailDestino != null) {
						MnuReportes.envioMasivoListadoProforma0 generar = new MnuReportes.envioMasivoListadoProforma0(desdeAAMMDD, hastaAAMMDD, uf, usd, eur, esVenta, mailDestino, s, form);
						generar.start();
						String mensaje = "Solicitud en preparación, recibira el resultado al correo:" + mailDestino + ". Tomara varios minutos u horas para recibir el correo, dependiendo de la cantidad de proformas a generar.";
						return ok(mensajes.render("/home/", mensaje));
					} else {
						String mensaje = "No es posible generar la solicitud debido a que no existe dato de email en la configuración de su usuario";
						return ok(mensajes.render("/home/", mensaje));
					}
				} else {
					String mensaje = "No es posible generar la solicitud debido a que el mail que existe en la configuración de su usuario no es valido";
					return ok(mensajes.render("/home/", mensaje));
				}
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reportFacturaProyectoDetalle(Http.Request request) {
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
			return ok(mensajes.render("/home/",msgErrorFormulario));
		}else {
			try {
				String desdeAAMMDD = form.get("fechaDesde").trim();
				String hastaAAMMDD = form.get("fechaHasta").trim();
				Double uf = Double.parseDouble(form.get("uf").replaceAll(",", "").trim());
				Double usd = Double.parseDouble(form.get("usd").replaceAll(",", "").trim());
				Double eur = Double.parseDouble(form.get("eur").replaceAll(",", "").trim());
				Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodega").trim());
				Map<Long,Double> tasas = new HashMap<Long,Double>();
				tasas.put((long)1, (double) 1); 	// 'Peso Chileno', 'CLP', '0'
				tasas.put((long)2, usd); 			// 'Dólar', 'USD', '2'
				tasas.put((long)3, eur); 			// 'Euro', 'EUR', '3'
				tasas.put((long)4, uf); 			// 'Unidad Fomento', 'UF', '4'
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				java.sql.Date masUnDia = null;
				Map<String, Double> mapFijaTasas = null;
				List<Long> listIdBodegaEmpresa = null;
				Map<Long,Calc_BodegaEmpresa> mapBodegaEmpresa = null;
				Map<String,Calc_Precio> mapPrecios = null;
				Map<Long,Calc_Precio> mapMaestroPrecios = null;
				List<Long> listIdGuia_fechaCorte = null;
				List<Inventarios> inventario = null;
				Map<String,String> mapFecha_primera_guia = null;
				Map<Long,Cotizacion> mapCotiAllConfirmadas = null;
				Map<Long,String> mapMoneda = null;
				Map<Long,Equipo> mapAllEquipos = null;
				Map<Long,Long> dec = null;
				List<List<String>> inicioPer = null;
				List<List<String>> listGuiasPer = null;
				BodegaEmpresa bodega = null;
				Proyecto proyecto = null;
				String idTipoUsuario = null;
				Cliente cliente = null;
				Map<String,List<List<String>>> mapReportPorGuia10 = null;
				List<List<String>> resumenSubtotales = null;
				List<List<String>> detalleAjuste = null;
				Long cantDec = null;
				List<TipoReferencia> listReferencias = null;
				List<CotizaSolucion> listSol = null;
				String oc = null;
				try (Connection con = dbRead.getConnection()) {
					String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
					listIdBodegaEmpresa = ModCalc_InvInicial.listIdBodegaEmpresa(con, s.baseDato, permisoPorBodega);
					mapBodegaEmpresa = Calc_BodegaEmpresa.mapAllBodegasVigentes(con, s.baseDato);
					mapPrecios = Calc_Precio.mapPrecios(con, s.baseDato, listIdBodegaEmpresa);
					mapMaestroPrecios = Calc_Precio.mapMaestroPrecios(con, s.baseDato);
					mapFijaTasas = BodegaEmpresa.mapFijaTasasAll(con, s.baseDato);
					listIdGuia_fechaCorte = ModCalc_InvInicial.listIdGuia_fechaCorte(con, s.baseDato, desdeAAMMDD);
					inventario = Inventarios.inventario(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_fechaCorte);
					List<Long> listIdGuia_entreFechas = ModCalc_GuiasPer.listIdGuia_entreFecha(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
					List<Inventarios> guiasPer = Inventarios.guiasPer(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_entreFechas);
					dec = Moneda.numeroDecimal(con, s.baseDato);
					Map<String,String> mapPermanencias = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, s.baseDato);
					mapFecha_primera_guia = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, s.baseDato);
					mapCotiAllConfirmadas = Cotizacion.mapAllConfirmadasUnaBodega(con, s.baseDato, id_bodegaEmpresa);
					mapMoneda = Moneda.mapIdMonedaMoneda(con, s.baseDato);
					mapAllEquipos = Equipo.mapAllAll(con, s.baseDato);
					listGuiasPer = ReportFacturas.reportListGuiasEntreFechas(con, s.baseDato, id_bodegaEmpresa, desdeAAMMDD, hastaAAMMDD);
					bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
					proyecto = Proyecto.find(con,s.baseDato , bodega.getId_proyecto());
					cliente = Cliente.find(con, s.baseDato, bodega.getId_cliente());
					detalleAjuste = AjustesEP.detalleAjuste(con, s.baseDato, id_bodegaEmpresa, desdeAAMMDD, hastaAAMMDD);
					listReferencias = TipoReferencia.all(con, s.baseDato);
					oc = Cotizacion.ocParticiaEnBodega(con, s.baseDato, id_bodegaEmpresa);
					cantDec = dec.get((long)1);
					idTipoUsuario = s.id_tipoUsuario;
					inicioPer = ReportFacturas.reportEstadoInicial10(s.baseDato, id_bodegaEmpresa, desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa, mapBodegaEmpresa,
							mapPrecios, mapMaestroPrecios, listIdGuia_fechaCorte, inventario, mapFecha_primera_guia, mapCotiAllConfirmadas, mapMoneda, mapAllEquipos, dec);
					mapReportPorGuia10 = ReportFacturas.mapReportPorGuia10(s.baseDato, id_bodegaEmpresa, desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas,
							mapBodegaEmpresa, mapPrecios, mapMaestroPrecios,
							guiasPer, mapPermanencias, dec,  mapCotiAllConfirmadas, mapAllEquipos, mapMoneda);
					resumenSubtotales = ReportFacturas.reportEstadoResumen10(s.baseDato, inicioPer, listGuiasPer, id_bodegaEmpresa, desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas,
							listIdBodegaEmpresa, mapBodegaEmpresa, mapPrecios, mapMaestroPrecios,
							listIdGuia_entreFechas, mapPermanencias, dec, mapCotiAllConfirmadas, mapAllEquipos, mapMoneda, guiasPer);
					listIdGuia_entreFechas = null;
					guiasPer = null;
					mapPermanencias = null;
					Fechas hasta = Fechas.obtenerFechaDesdeStrAAMMDD(hastaAAMMDD);
					Calendar hastaMas1 = hasta.getFechaCal();
					hastaMas1.add(Calendar.DAY_OF_MONTH, 1);
					masUnDia = new java.sql.Date(hastaMas1.getTimeInMillis());
					listIdGuia_fechaCorte = ModCalc_InvInicial.listIdGuia_fechaCorte(con, s.baseDato, masUnDia.toString());
					inventario = Inventarios.inventario(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_fechaCorte);
					listSol = CotizaSolucion.all(con, s.baseDato);
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				}
				List<List<String>> finalPer = ReportFacturas.reportEstadoInicial10(s.baseDato, id_bodegaEmpresa, masUnDia.toString(), hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa, mapBodegaEmpresa,
						mapPrecios, mapMaestroPrecios, listIdGuia_fechaCorte, inventario, mapFecha_primera_guia, mapCotiAllConfirmadas, mapMoneda, mapAllEquipos, dec);
				listIdBodegaEmpresa = null;
				mapBodegaEmpresa = null;
				mapPrecios = null;
				mapMaestroPrecios = null;
				mapFijaTasas = null;
				listIdGuia_fechaCorte = null;
				inventario = null;
				mapCotiAllConfirmadas = null;
				mapAllEquipos = null;
				List<String> fechas = new ArrayList<String>();
				fechas.add(desdeAAMMDD);
				fechas.add(hastaAAMMDD);
				fechas.add(Fechas.DDMMAA(desdeAAMMDD));
				fechas.add(Fechas.DDMMAA(hastaAAMMDD));
				fechas.add(Fechas.DDMMAA(hastaAAMMDD));
				List<Double> tasaCambio = new ArrayList<Double>();
				tasaCambio.add(uf);
				tasaCambio.add(usd);
				tasaCambio.add(eur);
				return ok(reportFacturaProyectoDetalle.render(mapeoDiccionario,mapeoPermiso,userMnu,idTipoUsuario,
						inicioPer,listGuiasPer,fechas,bodega,proyecto,tasaCambio,
						resumenSubtotales,id_bodegaEmpresa,finalPer,cliente,detalleAjuste,mapReportPorGuia10, cantDec, listReferencias,
						oc, listSol));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reportFacturaProyectoDetExcel(Http.Request request) {
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
			return ok(mensajes.render("/home/",msgErrorFormulario));
		}else {
			try {
				String desdeAAMMDD = form.get("fechaDesde").trim();
				String hastaAAMMDD = form.get("fechaHasta").trim();
				Double uf = Double.parseDouble(form.get("uf").replaceAll(",", "").trim());
				Double usd = Double.parseDouble(form.get("usd").replaceAll(",", "").trim());
				Double eur = Double.parseDouble(form.get("eur").replaceAll(",", "").trim());
				Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodega").trim());
				Map<Long,Double> tasas = new HashMap<Long,Double>();
				tasas.put((long)1, (double) 1); 	// 'Peso Chileno', 'CLP', '0'
				tasas.put((long)2, usd); 			// 'Dólar', 'USD', '2'
				tasas.put((long)3, eur); 			// 'Euro', 'EUR', '3'
				tasas.put((long)4, uf); 			// 'Unidad Fomento', 'UF', '4'
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				java.sql.Date masUnDia = null;
				Map<String, Double> mapFijaTasas = null;
				List<Long> listIdBodegaEmpresa = null;
				Map<Long,Calc_BodegaEmpresa> mapBodegaEmpresa = null;
				Map<String,Calc_Precio> mapPrecios = null;
				Map<Long,Calc_Precio> mapMaestroPrecios = null;
				List<Long> listIdGuia_fechaCorte = null;
				List<Inventarios> inventario = null;
				Map<String,String> mapFecha_primera_guia = null;
				Map<Long,Cotizacion> mapCotiAllConfirmadas = null;
				Map<Long,String> mapMoneda = null;
				Map<Long,Equipo> mapAllEquipos = null;
				Map<Long,Long> dec = null;
				List<List<String>> inicioPer = null;
				List<List<String>> listGuiasPer = null;
				BodegaEmpresa bodega = null;
				Proyecto proyecto = null;
				Cliente cliente = null;
				Map<String,List<List<String>>> mapReportPorGuia10 = null;
				List<List<String>> resumenSubtotales = null;
				List<List<String>> detalleAjuste = null;
				Long cantDec = null;
				String oc = null;
				try (Connection con = dbRead.getConnection()) {
					String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
					listIdBodegaEmpresa = ModCalc_InvInicial.listIdBodegaEmpresa(con, s.baseDato, permisoPorBodega);
					mapBodegaEmpresa = Calc_BodegaEmpresa.mapAllBodegasVigentes(con, s.baseDato);
					mapPrecios = Calc_Precio.mapPrecios(con, s.baseDato, listIdBodegaEmpresa);
					mapMaestroPrecios = Calc_Precio.mapMaestroPrecios(con, s.baseDato);
					mapFijaTasas = BodegaEmpresa.mapFijaTasasAll(con, s.baseDato);
					listIdGuia_fechaCorte = ModCalc_InvInicial.listIdGuia_fechaCorte(con, s.baseDato, desdeAAMMDD);
					inventario = Inventarios.inventario(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_fechaCorte);
					List<Long> listIdGuia_entreFechas = ModCalc_GuiasPer.listIdGuia_entreFecha(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
					List<Inventarios> guiasPer = Inventarios.guiasPer(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_entreFechas);
					dec = Moneda.numeroDecimal(con, s.baseDato);
					Map<String,String> mapPermanencias = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, s.baseDato);
					mapFecha_primera_guia = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, s.baseDato);
					mapCotiAllConfirmadas = Cotizacion.mapAllConfirmadasUnaBodega(con, s.baseDato, id_bodegaEmpresa);
					mapMoneda = Moneda.mapIdMonedaMoneda(con, s.baseDato);
					mapAllEquipos = Equipo.mapAllAll(con, s.baseDato);
					listGuiasPer = ReportFacturas.reportListGuiasEntreFechas(con, s.baseDato, id_bodegaEmpresa, desdeAAMMDD, hastaAAMMDD);
					bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
					proyecto = Proyecto.find(con,s.baseDato , bodega.getId_proyecto());
					cliente = Cliente.find(con, s.baseDato, bodega.getId_cliente());
					detalleAjuste = AjustesEP.detalleAjuste(con, s.baseDato, id_bodegaEmpresa, desdeAAMMDD, hastaAAMMDD);
					cantDec = dec.get((long)1);
					inicioPer = ReportFacturas.reportEstadoInicial10(s.baseDato, id_bodegaEmpresa, desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa, mapBodegaEmpresa,
							mapPrecios, mapMaestroPrecios, listIdGuia_fechaCorte, inventario, mapFecha_primera_guia, mapCotiAllConfirmadas, mapMoneda, mapAllEquipos, dec);
					mapReportPorGuia10 = ReportFacturas.mapReportPorGuia10(s.baseDato, id_bodegaEmpresa, desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas,
							mapBodegaEmpresa, mapPrecios, mapMaestroPrecios,
							guiasPer, mapPermanencias, dec,  mapCotiAllConfirmadas, mapAllEquipos, mapMoneda);
					resumenSubtotales = ReportFacturas.reportEstadoResumen10(s.baseDato, inicioPer, listGuiasPer, id_bodegaEmpresa, desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas,
							listIdBodegaEmpresa, mapBodegaEmpresa, mapPrecios, mapMaestroPrecios,
							listIdGuia_entreFechas, mapPermanencias, dec, mapCotiAllConfirmadas, mapAllEquipos, mapMoneda, guiasPer);
					listIdGuia_entreFechas = null;
					guiasPer = null;
					mapPermanencias = null;
					Fechas hasta = Fechas.obtenerFechaDesdeStrAAMMDD(hastaAAMMDD);
					Calendar hastaMas1 = hasta.getFechaCal();
					hastaMas1.add(Calendar.DAY_OF_MONTH, 1);
					masUnDia = new java.sql.Date(hastaMas1.getTimeInMillis());
					listIdGuia_fechaCorte = ModCalc_InvInicial.listIdGuia_fechaCorte(con, s.baseDato, masUnDia.toString());
					inventario = Inventarios.inventario(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_fechaCorte);
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				}
				List<List<String>> finalPer = ReportFacturas.reportEstadoInicial10(s.baseDato, id_bodegaEmpresa, masUnDia.toString(), hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa, mapBodegaEmpresa,
						mapPrecios, mapMaestroPrecios, listIdGuia_fechaCorte, inventario, mapFecha_primera_guia, mapCotiAllConfirmadas, mapMoneda, mapAllEquipos, dec);
				listIdBodegaEmpresa = null;
				mapBodegaEmpresa = null;
				mapPrecios = null;
				mapMaestroPrecios = null;
				mapFijaTasas = null;
				listIdGuia_fechaCorte = null;
				inventario = null;
				mapCotiAllConfirmadas = null;
				mapAllEquipos = null;
				List<String> fechas = new ArrayList<String>();
				fechas.add(desdeAAMMDD);
				fechas.add(hastaAAMMDD);
				fechas.add(Fechas.DDMMAA(desdeAAMMDD));
				fechas.add(Fechas.DDMMAA(hastaAAMMDD));
				fechas.add(Fechas.DDMMAA(hastaAAMMDD));
				List<Double> tasaCambio = new ArrayList<Double>();
				tasaCambio.add(uf);
				tasaCambio.add(usd);
				tasaCambio.add(eur);
				File file = ReportFacturas.reportFacturaProyectoDetExcel(s.baseDato,mapeoDiccionario,mapeoPermiso,
						inicioPer,listGuiasPer,fechas,bodega,proyecto,tasaCambio,
						resumenSubtotales,finalPer,cliente,detalleAjuste,mapReportPorGuia10, cantDec);
				return ok(file,false,Optional.of("EP_Proforma_"+bodega.nombre+".xlsx"));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result generarProformaPdfXlsxXmlJson(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		DynamicForm formEsVenta = formFactory.form().bindFromRequest(request);
		FormFactura form = formFactory.form(FormFactura.class).withDirectFieldAccess(true).bindFromRequest(request).get();
		if (formEsVenta.hasErrors() || form.idBodega==null) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/",msgErrorFormulario));
		}else {
			try {
				String desdeAAMMDD = form.fechaDesde;
				String hastaAAMMDD = form.fechaHasta;
				Double uf = Double.parseDouble(form.uf.replaceAll(",", "").trim());
				Double usd = Double.parseDouble(form.usd.replaceAll(",", "").trim());
				Double eur = Double.parseDouble(form.eur.replaceAll(",", "").trim());
				Long id_bodegaEmpresa = Long.parseLong(form.idBodega.trim());
				String esVenta = formEsVenta.get("esVenta").trim();
				Map<Long,Double> tasas = new HashMap<Long,Double>();
				tasas.put((long)1, (double) 1); 	// 'Peso Chileno', 'CLP', '0'
				tasas.put((long)2, usd); 			// 'Dólar', 'USD', '2'
				tasas.put((long)3, eur); 			// 'Euro', 'EUR', '3'
				tasas.put((long)4, uf); 			// 'Unidad Fomento', 'UF', '4'
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				List<Long> listIdBodegaEmpresa = null;
				Map<Long,Calc_BodegaEmpresa> mapBodegaEmpresa = null;
				Map<String,Calc_Precio> mapPrecios = null;
				Map<Long,Calc_Precio> mapMaestroPrecios = null;
				Map<String, Double> mapFijaTasas = null;
				List<Long> listIdGuia_fechaCorte = null;
				List<Inventarios> inventario = null;
				List<Long> listIdGuia_entreFechas = null;
				Map<Long,Long> dec = null;
				Map<String,String> mapPermanencias = null;
				Map<String,String> mapFecha_primera_guia = null;
				Map<Long,Cotizacion> mapCotiAllConfirmadas = null;
				Map<Long,String> mapMoneda = null;
				Map<Long,Equipo> mapAllEquipos = null;
				List<List<String>> listGuiasPer = null;
				BodegaEmpresa bodega = null;
				Proyecto proyecto = null;
				Cliente cliente = null;
				List<List<String>> detalleAjuste = null;
				String oc = null;
				Fechas hoy = null;
				Proforma proforma = null;
				Long cantDec = null;
				List<List<String>> inicioPer = null;
				Map<String,List<List<String>>> mapReportPorGuia10 = null;
				List<List<String>> resumenSubtotales = null;
				java.sql.Date masUnDia = null;
				try (Connection con = dbWrite.getConnection()) {
					String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
					listIdBodegaEmpresa = ModCalc_InvInicial.listIdBodegaEmpresa(con, s.baseDato, permisoPorBodega);
					mapBodegaEmpresa = Calc_BodegaEmpresa.mapAllBodegasVigentes(con, s.baseDato);
					mapPrecios = Calc_Precio.mapPrecios(con, s.baseDato, listIdBodegaEmpresa);
					mapMaestroPrecios = Calc_Precio.mapMaestroPrecios(con, s.baseDato);
					mapFijaTasas = BodegaEmpresa.mapFijaTasasAll(con, s.baseDato);
					listIdGuia_fechaCorte = ModCalc_InvInicial.listIdGuia_fechaCorte(con, s.baseDato, desdeAAMMDD);
					inventario = Inventarios.inventario(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_fechaCorte);
					listIdGuia_entreFechas = ModCalc_GuiasPer.listIdGuia_entreFecha(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
					List<Inventarios> guiasPer = Inventarios.guiasPer(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_entreFechas);
					dec = Moneda.numeroDecimal(con, s.baseDato);
					mapPermanencias = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, s.baseDato);
					mapFecha_primera_guia = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, s.baseDato);
					mapCotiAllConfirmadas = Cotizacion.mapAllConfirmadasUnaBodega(con, s.baseDato, id_bodegaEmpresa);
					mapMoneda = Moneda.mapIdMonedaMoneda(con, s.baseDato);
					mapAllEquipos = Equipo.mapAllAll(con, s.baseDato);
					listGuiasPer = ReportFacturas.reportListGuiasEntreFechas(con, s.baseDato, id_bodegaEmpresa, desdeAAMMDD, hastaAAMMDD);
					bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
					proyecto = Proyecto.find(con,s.baseDato , bodega.getId_proyecto());
					cliente = Cliente.find(con, s.baseDato, bodega.getId_cliente());
					detalleAjuste = AjustesEP.detalleAjuste(con, s.baseDato, id_bodegaEmpresa, desdeAAMMDD, hastaAAMMDD);
					oc = Cotizacion.ocParticiaEnBodega(con, s.baseDato, id_bodegaEmpresa);
					hoy = Fechas.hoy();
					proforma = Proforma.createSinNada(con, s.baseDato, hoy.getFechaStrAAMMDD());
					cantDec = dec.get((long)1);
					inicioPer = ReportFacturas.reportEstadoInicial10(s.baseDato, id_bodegaEmpresa, desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa, mapBodegaEmpresa,
							mapPrecios, mapMaestroPrecios, listIdGuia_fechaCorte, inventario, mapFecha_primera_guia, mapCotiAllConfirmadas, mapMoneda, mapAllEquipos, dec);
					mapReportPorGuia10 = ReportFacturas.mapReportPorGuia10(s.baseDato, id_bodegaEmpresa, desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas,
							mapBodegaEmpresa, mapPrecios, mapMaestroPrecios,
							guiasPer, mapPermanencias, dec,  mapCotiAllConfirmadas, mapAllEquipos, mapMoneda);
					resumenSubtotales = ReportFacturas.reportEstadoResumen10(s.baseDato, inicioPer, listGuiasPer, id_bodegaEmpresa, desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas,
							listIdBodegaEmpresa, mapBodegaEmpresa, mapPrecios, mapMaestroPrecios,
							listIdGuia_entreFechas, mapPermanencias, dec, mapCotiAllConfirmadas, mapAllEquipos, mapMoneda, guiasPer);
					guiasPer = null;
					Fechas hasta = Fechas.obtenerFechaDesdeStrAAMMDD(hastaAAMMDD);
					Calendar hastaMas1 = hasta.getFechaCal();
					hastaMas1.add(Calendar.DAY_OF_MONTH, 1);
					masUnDia = new java.sql.Date(hastaMas1.getTimeInMillis());
					listIdGuia_fechaCorte = ModCalc_InvInicial.listIdGuia_fechaCorte(con, s.baseDato, masUnDia.toString());
					inventario = Inventarios.inventario(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_fechaCorte);
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				}
				List<List<String>> finalPer = ReportFacturas.reportEstadoInicial10(s.baseDato, id_bodegaEmpresa, masUnDia.toString(), hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa, mapBodegaEmpresa,
						mapPrecios, mapMaestroPrecios, listIdGuia_fechaCorte, inventario, mapFecha_primera_guia, mapCotiAllConfirmadas, mapMoneda, mapAllEquipos, dec);
				mapBodegaEmpresa = null;
				mapPrecios = null;
				mapMaestroPrecios = null;
				inventario = null;
				mapCotiAllConfirmadas = null;
				mapAllEquipos = null;
				List<String> fechas = new ArrayList<String>();
				fechas.add(desdeAAMMDD);
				fechas.add(hastaAAMMDD);
				fechas.add(Fechas.DDMMAA(desdeAAMMDD));
				fechas.add(Fechas.DDMMAA(hastaAAMMDD));
				List<Double> tasaCambio = new ArrayList<Double>();
				tasaCambio.add(uf);
				tasaCambio.add(usd);
				tasaCambio.add(eur);
				proforma.setFecha(hoy.getFechaStrAAMMDD());
				proforma.setDesde(desdeAAMMDD);
				proforma.setHasta(hastaAAMMDD);
				proforma.setId_cliente(cliente.id);
				proforma.setId_bodegaEmpresa(bodega.id);
				proforma.setId_proyecto(proyecto.id);
				proforma.setDocRef("--");
				proforma.setEpExcelMov("PRmov_" + proforma.id + "_proformaArriendo.xlsx");
				proforma.setEpExcelEp("PRep_" + proforma.id + "_proformaArriendo.xlsx");
				proforma.setProformaPdf("PRpdf" + proforma.id + "_proformaArriendo.pdf");
				proforma.setProformaXml("PRxml" + proforma.id + "_proformaArriendo.xml");
				proforma.setDocAnexo("0");
				proforma.setDescuento((double)0);
				proforma.setNeto((double)0);
				proforma.setIva((double)0);
				proforma.setTotal((double)0);
				proforma.setTipo(mapeoDiccionario.get("Arriendo"));
				File file = ReportFacturas.reportFacturaProyectoDetExcel(s.baseDato,mapeoDiccionario,mapeoPermiso,
						inicioPer,listGuiasPer,fechas,bodega,proyecto,tasaCambio,
						resumenSubtotales,finalPer,cliente,detalleAjuste,mapReportPorGuia10, cantDec);
				String fileOutNameDetalle = s.baseDato+"/"+proforma.getEpExcelEp();
				Archivos.grabarArchivo(file, fileOutNameDetalle);
				String concepto = mapeoDiccionario.get("ARRIENDO");
				if("1".equals(esVenta)) {
					concepto = "VENTA";
				}
				String archivoPDF = "0";
				try (Connection con2 = dbWrite.getConnection()) {
					List<List<String>> datos = ReportMovimientos.movimientoGuias(con2, s.baseDato, mapeoDiccionario, id_bodegaEmpresa, esVenta, desdeAAMMDD, hastaAAMMDD, usd, eur, uf, concepto);
					String fileOutNameMovimientos = proforma.getEpExcelMov();
					file = ReportMovimientos.movimientosExcel(s.baseDato, datos, mapeoDiccionario, bodega, concepto, desdeAAMMDD, hastaAAMMDD);
					Archivos.grabarArchivo(file, s.baseDato+"/"+fileOutNameMovimientos);
					XmlFacturaReferencias referencias = new XmlFacturaReferencias();
					if(form.tpoDocRef!=null) {
						referencias.tpoDocRef = form.tpoDocRef;
						referencias.folioRef = form.folioRef;
						referencias.fchRef = form.fchRef;
						referencias.razonRef = form.razonRef;
						referencias.obs = "";
					}
					String comentarios = form.comentarios;
					EmisorTributario emisorTributario = EmisorTributario.find(con2, s.baseDato);
					BodegaEmpresa bodegaEmpresa = BodegaEmpresa.findXIdBodega(con2, s.baseDato, proforma.id_bodegaEmpresa);
					if(esVenta.equals("0")) {
						// genera PDF de arriendo, XML, JSON y guarda json en proforma
						proforma.setTipo(mapeoDiccionario.get("Arriendo"));
						String conDetalle = mapeoPermiso.get("parametro.proformaInvConCompra");
						archivoPDF = FormFactura.generaProformaArriendo(con2, s.baseDato, ws, mapeoDiccionario, mapeoPermiso,
								resumenSubtotales,cliente,proforma,referencias,detalleAjuste,conDetalle, inicioPer, listGuiasPer, mapReportPorGuia10, finalPer, uf, usd, eur, oc,
								dec, emisorTributario, bodegaEmpresa, comentarios, form);
					}else {
						// genera PDF de venta, XML, JSON y guarda json en proforma
						proforma.setTipo("Venta");
						archivoPDF = FormFactura.generaProformaVenta(con2, s.baseDato, ws, mapeoDiccionario, mapeoPermiso,
								cliente,proforma,referencias,detalleAjuste, listGuiasPer, mapReportPorGuia10, oc, comentarios, form);
					}
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				}
				String titulo = "NOTA DE VENTA";
				String url = "%2FreportFacturaProyectoGet%2F0&"+desdeAAMMDD+"&"+hastaAAMMDD+"&"+uf+"&"+usd+"&"+eur;
				return redirect("/routes2/redirShowPDF/"+archivoPDF+","+titulo+","+url);
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}



	//====================================================================================
	// MNU reportFacturaPeriodoH   Reportes/Proforma/Pre-Factura Simple
	//====================================================================================

	public Result reportFacturaPeriodoH(Http.Request request) {
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
		if(mapeoPermiso.get("reportFacturaDetalleProyecto")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()) {
			Fechas hoy = Fechas.hoy();
			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			TasasCambio tasas = TasasCambio.allDeUnaFecha(con, s.baseDato, mapeoDiccionario.get("pais"),hasta);
			return ok(reportFacturaPeriodoH.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta, tasas));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result reportFacturaProyectoH(Http.Request request, String archivoPDF) {
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
		} else {
			Map<String, String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String, String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
			String desdeAAMMDD = null;
			String hastaAAMMDD = null;
			Map<Long, Double> tasas = null;
			Map<String, Double> mapFijaTasas = null;
			List<Long> listIdBodegaEmpresa = null;
			Map<Long, Calc_BodegaEmpresa> mapBodegaEmpresa = null;
			Map<String, Calc_Precio> mapPrecios = null;
			Map<Long, Calc_Precio> mapMaestroPrecios = null;
			List<Long> listIdGuia_fechaCorte = null;
			List<Inventarios> inventario = null;
			List<Long> listIdGuia_entreFechas = null;
			List<Calc_AjustesEP> listaAjustes = null;
			Map<Long, List<String>> mapBodega = null;
			Map<Long, Long> dec = null;
			Map<String, String> map = null;
			String id_proyecto = null;
			Double uf = null;
			Double usd = null;
			Double eur = null;
			Map<Long,Long> mapDec = null;
			try (Connection con = dbRead.getConnection()) {
				desdeAAMMDD = form.get("fechaDesde").trim();
				hastaAAMMDD = form.get("fechaHasta").trim();
				uf = Double.parseDouble(form.get("uf").replaceAll(",", "").trim());
				usd = Double.parseDouble(form.get("usd").replaceAll(",", "").trim());
				eur = Double.parseDouble(form.get("eur").replaceAll(",", "").trim());
				id_proyecto = form.get("id_proyecto");
				if (id_proyecto == null) {
					id_proyecto = "0";
				}
				id_proyecto = id_proyecto.trim();
				tasas = new HashMap<Long, Double>();
				tasas.put((long) 1, (double) 1);    // 'Peso Chileno', 'CLP', '0'
				tasas.put((long) 2, usd);            // 'Dólar', 'USD', '2'
				tasas.put((long) 3, eur);            // 'Euro', 'EUR', '3'
				tasas.put((long) 4, uf);            // 'Unidad Fomento', 'UF', '4'
				String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
				listIdBodegaEmpresa = ModCalc_InvInicial.listIdBodegaEmpresa(con, s.baseDato, permisoPorBodega);
				mapBodegaEmpresa = Calc_BodegaEmpresa.mapAllBodegasVigentes(con, s.baseDato);
				mapPrecios = Calc_Precio.mapPrecios(con, s.baseDato, listIdBodegaEmpresa);
				mapMaestroPrecios = Calc_Precio.mapMaestroPrecios(con, s.baseDato);
				mapFijaTasas = BodegaEmpresa.mapFijaTasasAll(con, s.baseDato);
				listIdGuia_fechaCorte = ModCalc_InvInicial.listIdGuia_fechaCorte(con, s.baseDato, desdeAAMMDD);
				inventario = Inventarios.inventario(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_fechaCorte);
				listIdGuia_entreFechas = ModCalc_GuiasPer.listIdGuia_entreFecha(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
				listaAjustes = Calc_AjustesEP.listaAjustesEntreFechas(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
				mapBodega = BodegaEmpresa.mapIdBod_BodegaEmpresaInternasExternas(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
				dec = Moneda.numeroDecimal(con, s.baseDato);
				map = UsuarioPermiso.mapPermisoIdBodega(con, s.baseDato, Long.parseLong(s.id_usuario));
				mapDec = Moneda.numeroDecimal(con, s.baseDato);

			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
			ReportFacturas reporte = ModCalc_InvInicial.resumenInvInicial(s.baseDato, desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa,
					mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, listIdGuia_fechaCorte, inventario);
			List<ModCalc_InvInicial> inventarioInicial = reporte.resumenInvInicial;
			listIdGuia_fechaCorte = null;
			inventario = null;
			List<Inventarios> guiasPer = null;
			Map<String, String> mapPermanencias = null;
			int nroDec = 0;
			try (Connection con = dbRead.getConnection()) {
				guiasPer = Inventarios.guiasPer(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_entreFechas);
				mapPermanencias = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, s.baseDato);
				nroDec = Moneda.numeroDecimalxId(con, s.baseDato, "1");
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
			try {
				List<ModCalc_GuiasPer> guiasPeriodo = ModCalc_GuiasPer.resumenGuiasPer(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas,
						mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, guiasPer, mapPermanencias);
				mapPermanencias = null;
				mapBodegaEmpresa = null;
				mapPrecios = null;
				mapMaestroPrecios = null;
				guiasPer = null;
				List<ModeloCalculo> listado = ModeloCalculo.valorTotalporBodega(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, inventarioInicial, guiasPeriodo, listaAjustes);
				inventarioInicial = null;
				guiasPeriodo = null;
				listaAjustes = null;
				List<List<String>> proyectosAux = ReportFacturas.reportFacturaProyecto(listado, mapBodega);
				mapBodega = null;
				List<List<String>> resumenTotales = ReportFacturas.resumenTotalesPorProyecto(listado, dec);
				listado = null;
				List<List<String>> proyectos = new ArrayList<List<String>>();
				if (!map.isEmpty()) {
					for (List<String> aux : proyectosAux) {
						String idBodega = map.get(aux.get(1));
						if (idBodega != null) {
							proyectos.add(aux);
						}
					}
				} else {
					proyectos = proyectosAux;
				}
				proyectosAux = null;
				if (!id_proyecto.equals("0")) {
					List<List<String>> aux = new ArrayList<List<String>>();
					for (List<String> lista1 : proyectos) {
						if (lista1.get(3).trim().equals(id_proyecto)) {
							aux.add(lista1);
						}
					}
					proyectos = aux;
				}

				Map<String,List<String>> mapServicios = new HashMap<String,List<String>>();
				try (Connection con = dbRead.getConnection()) {
					Map<Long,Long> mapIdEquipoVsIdGrupo = Equipo.mapIdEquipoVsIdGrupo(con, s.baseDato);
					Long id_grupo = (long)0;
					Map<String,ListaPrecioServicio> mapPreciosOdo = ListaPrecioServicio.mapAllListaPrecioServicio(con, s.baseDato);

					Map<Long,BodegaEmpresa> mapBodegas = BodegaEmpresa.mapAll(con, s.baseDato);
					Map<Long,Double> mapTotalAjustePorBodega = Calc_AjustesEpOdo.mapTotalAjustePorBodega(con, s.baseDato, desdeAAMMDD, hastaAAMMDD, s.aplicaPorSucursal, s.id_sucursal);
					List<VentaServicio> listVentaServicio = VentaServicio.allEntreFechas(con, s.baseDato, desdeAAMMDD, hastaAAMMDD, s.aplicaPorSucursal, s.id_sucursal);
					List<List<String>> resumenTotalesPorProyecto = ReportOdo.resumenTotalesPorProyecto(s.baseDato, listVentaServicio, mapFijaTasas, tasas, mapDec, mapTotalAjustePorBodega, mapBodegas, mapPreciosOdo, id_grupo, mapIdEquipoVsIdGrupo);
					for(List<String> x: resumenTotalesPorProyecto){
						mapServicios.put(x.get(0),x);
					}
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				}



				String tabla = "<table id=\"tablaPrincipal\" class=\"table table-sm table-hover table-bordered table-condensed table-fluid\">"
						+ "<thead style=\"background-color: #eeeeee\">"
						+ "<TR> "
						+ "<TH style= \"text-align: center;vertical-align: top;\">SUCURSAL</TH>"
						+ "<TH style= \"text-align: center;vertical-align: top;\">" + mapeoDiccionario.get("BODEGA") + "/PROYECTO</TH>"
						+ "<TH style= \"text-align: center;vertical-align: top;\">NOMBRE<BR>CLIENTE</TH>"
						+ "<TH style= \"text-align: center;vertical-align: top;\">NOMBRE<br>PROYECTO</TH>"
						+ "<TH style= \"text-align: center;vertical-align: top;\">COMERCIAL</TH>"
						+ "<TH style= \"text-align:center;vertical-align:top;\">CFI (en " + mapeoDiccionario.get("PESOS") + ")</TH>"
						+ "<TH style= \"text-align:center;vertical-align:top;\">SubTotal<br>" + mapeoDiccionario.get("ARRIENDO") + "</TH>"
				+ "<TH style= \"text-align:center;vertical-align:top;\">SubTotal<br>VENTA</TH>"
				+ "<TH style= \"text-align:center;vertical-align:top;\">SubTotal<br>SERVICIO</TH>"
						+ "<TH style= \"text-align:center;vertical-align:top;\">Ajustes<BR>(" + mapeoDiccionario.get("ARRIENDO") + ")</TH>"
				+ "<TH style= \"text-align:center;vertical-align:top;\">Ajustes<BR>(VENTA)</TH>"
				+ "<TH style= \"text-align:center;vertical-align:top;\">Ajustes<BR>(SERVICIO)</TH>"
						+ "<TH style= \"text-align:center;vertical-align:top;\">TOTAL<BR>(en " + mapeoDiccionario.get("PESOS") + ")</TH>"
						+ "<TH width=\"5%\" >" + mapeoDiccionario.get("ARRIENDO") + "<BR></TH>"
				+ "<TH width=\"5%\" >VENTA<BR></TH>"
				+ "<TH width=\"5%\" >SERVICIO<BR></TH>"
				+ "<TH width=\"5%\" >TODO<BR></TH>"
						+ "</TR>"
						+ "</thead>"
						+ "<tbody>";

				Map<String,String> mapAuxParaRevisaServ = new HashMap<String,String>();
				for (List<String> lista1 : proyectos) {
					for (List<String> total : resumenTotales) {
						if (lista1.get(1).equals(total.get(0))) {
							Double auxTotal = (double)0;
							try{
								auxTotal=Double.parseDouble(total.get(4).replaceAll(",",""));
							}catch(Exception e){}
							List<String> servicios = mapServicios.get(total.get(0));
							String servVta = DecimalFormato.formato(0.0,mapDec.get(1L));
							String servAjuste =  DecimalFormato.formato(0.0,mapDec.get(1L));

							if(servicios!=null){
								Double auxServVta=0.0;
								Double auxServAjuste=0.0;
								try{
									auxServVta = Double.parseDouble(servicios.get(5).replaceAll(",",""));
									servVta = servicios.get(5);
								}catch(Exception e){}
								try{
									auxServAjuste = Double.parseDouble(servicios.get(6).replaceAll(",",""));
									servAjuste = servicios.get(6);
								}catch(Exception e){}
								auxTotal += auxServVta + auxServAjuste;
							}
							if( auxTotal > 0) {
								mapAuxParaRevisaServ.put(lista1.get(1),lista1.get(1));
								String totales = DecimalFormato.formato(auxTotal,mapDec.get(1L));
								tabla += "<TR>";
								tabla += "<td style=\"text-align:left;vertical-align:middle;\">" + lista1.get(14) + "</td>"
										+ "<td style=\"text-align:left;vertical-align:middle;\"><a href=\"#\" onclick=\"modalContactoBodega('" + lista1.get(1) + "');\">" + lista1.get(5) + "</a></td>"
										+ "<td style=\"text-align:left;vertical-align:middle;\"><a href=\"#\" onclick=\"modalContactoCliente('" + lista1.get(2) + "');\">" + lista1.get(7) + "</a></td>"
										+ "<td style=\"text-align:left;vertical-align:middle;\"><a href=\"#\" onclick=\"modalContactoProyecto('" + lista1.get(3) + "');\">" + lista1.get(8) + "</a></td>"
										+ "<td style=\"text-align:left;vertical-align:middle;\">" + lista1.get(10) + "</td>"
										+ "<td style= \"text-align:right;\" class=\"cfi\">" + total.get(3) + "</td>"
										+ "<td style= \"text-align:right;\" class=\"arr\">" + total.get(1) + "</td>"
							+ "<td style= \"text-align:right;\" class=\"vta\">" + total.get(2) + "</td>"
							+ "<td style= \"text-align:right;\" class=\"serv\">"+servVta+"</td>"
										+ "<td style= \"text-align:right;\" class=\"ajustArr\">" + total.get(5) + "</td>"
							+ "<td style= \"text-align:right;\" class=\"ajustVta\">" + total.get(6) + "</td>"
							+ "<td style= \"text-align:right;\" class=\"ajustServ\">"+servAjuste+"</td>"
										+ "<td style= \"text-align:right;\" class=\"granTotal\">" + totales + "</td>"

								+ "<td style=\"text-align:center;vertical-align:middle;\">"
									+ "<form id=\"form0_" + lista1.get(1) + "\" class=\"formulario\" method=\"post\" action=\"/reportFacturaProyectoDetalleH/\">"
										+ "<input type=\"hidden\" name=\"id_bodega\" value=\"" + lista1.get(1) + "\">"
										+ "<input type=\"hidden\" name=\"fechaDesde\" value=\"" + desdeAAMMDD + "\">"
										+ "<input type=\"hidden\" name=\"fechaHasta\" value=\"" + hastaAAMMDD + "\">"
										+ "<input type=\"hidden\" name=\"esVenta\" value=\"0\">"
										+ "<input type=\"hidden\" name=\"uf\" value=\"" + uf + "\">"
										+ "<input type=\"hidden\" name=\"usd\" value=\"" + usd + "\">"
										+ "<input type=\"hidden\" name=\"eur\" value=\"" + eur + "\">"
										+ "<a href=\"#\" onclick=\"document.getElementById('form0_" + lista1.get(1) + "').submit()\">"
										+ "<kbd style=\"background-color: #73C6B6\">" + mapeoDiccionario.get("Arriendo") + "</kbd>"
										+ "</a>"
									+ "</form>"
								+ "</td>"

								+ "<td style=\"text-align:center;vertical-align:middle;\">"
										+ "<form id=\"form0Vta_" + lista1.get(1) + "\" class=\"formulario\" method=\"post\" action=\"/reportFacturaProyectoDetalleHVta/\">"
										+ "<input type=\"hidden\" name=\"id_bodega\" value=\"" + lista1.get(1) + "\">"
										+ "<input type=\"hidden\" name=\"fechaDesde\" value=\"" + desdeAAMMDD + "\">"
										+ "<input type=\"hidden\" name=\"fechaHasta\" value=\"" + hastaAAMMDD + "\">"
										+ "<input type=\"hidden\" name=\"esVenta\" value=\"0\">"
										+ "<input type=\"hidden\" name=\"uf\" value=\"" + uf + "\">"
										+ "<input type=\"hidden\" name=\"usd\" value=\"" + usd + "\">"
										+ "<input type=\"hidden\" name=\"eur\" value=\"" + eur + "\">"
										+ "<a href=\"#\" onclick=\"document.getElementById('form0Vta_" + lista1.get(1) + "').submit()\">"
										+ "<kbd style=\"background-color: #73C6B6\">Venta</kbd>"
										+ "</a>"
										+ "</form>"
										+ "</td>"

								+ "<td style=\"text-align:center;vertical-align:middle;\">"
										+ "<form id=\"form0Serv_" + lista1.get(1) + "\" class=\"formulario\" method=\"post\" action=\"/reportFacturaProyectoDetalleHServ/\">"
										+ "<input type=\"hidden\" name=\"id_bodega\" value=\"" + lista1.get(1) + "\">"
										+ "<input type=\"hidden\" name=\"fechaDesde\" value=\"" + desdeAAMMDD + "\">"
										+ "<input type=\"hidden\" name=\"fechaHasta\" value=\"" + hastaAAMMDD + "\">"
										+ "<input type=\"hidden\" name=\"esVenta\" value=\"0\">"
										+ "<input type=\"hidden\" name=\"uf\" value=\"" + uf + "\">"
										+ "<input type=\"hidden\" name=\"usd\" value=\"" + usd + "\">"
										+ "<input type=\"hidden\" name=\"eur\" value=\"" + eur + "\">"
										+ "<a href=\"#\" onclick=\"document.getElementById('form0Serv_" + lista1.get(1) + "').submit()\">"
										+ "<kbd style=\"background-color: #73C6B6\">Servicio</kbd>"
										+ "</a>"
										+ "</form>"
										+ "</td>"

								+ "<td style=\"text-align:center;vertical-align:middle;\">"
										+ "<form id=\"form0All_" + lista1.get(1) + "\" class=\"formulario\" method=\"post\" action=\"/reportFacturaProyectoDetalleHALL/\">"
										+ "<input type=\"hidden\" name=\"id_bodega\" value=\"" + lista1.get(1) + "\">"
										+ "<input type=\"hidden\" name=\"fechaDesde\" value=\"" + desdeAAMMDD + "\">"
										+ "<input type=\"hidden\" name=\"fechaHasta\" value=\"" + hastaAAMMDD + "\">"
										+ "<input type=\"hidden\" name=\"esVenta\" value=\"0\">"
										+ "<input type=\"hidden\" name=\"uf\" value=\"" + uf + "\">"
										+ "<input type=\"hidden\" name=\"usd\" value=\"" + usd + "\">"
										+ "<input type=\"hidden\" name=\"eur\" value=\"" + eur + "\">"
										+ "<a href=\"#\" onclick=\"document.getElementById('form0All_" + lista1.get(1) + "').submit()\">"
										+ "<kbd style=\"background-color: #73C6B6\">Todo</kbd>"
										+ "</a>"
										+ "</form>"
										+ "</td>";
								tabla += "</TR>";
							}
						}
					}
				}

				for (Map.Entry<String, List<String>> entry : mapServicios.entrySet()) {
					String key = entry.getKey();
					List<String> servicios = entry.getValue();
					String valida = mapAuxParaRevisaServ.get(key);
					if(valida == null) {
						String cliente = "clente";
						String proyecto = "proyecto";
						String comercial = "comercial";
						Double auxTotal = 0.0;
						Double auxServVta=0.0;
						Double auxServAjuste=0.0;
						String servVta = DecimalFormato.formato(0.0,mapDec.get(1L));
						String servAjuste =  DecimalFormato.formato(0.0,mapDec.get(1L));
						try{
							auxServVta = Double.parseDouble(servicios.get(5).replaceAll(",",""));
							servVta = servicios.get(5);
						}catch(Exception e){}
						try{
							auxServAjuste = Double.parseDouble(servicios.get(6).replaceAll(",",""));
							servAjuste = servicios.get(6);
						}catch(Exception e){}
						auxTotal += auxServVta + auxServAjuste;
						tabla += "<TR>";
						tabla += "<td style=\"text-align:left;vertical-align:middle;\">" + "" + "</td>"
								+ "<td style=\"text-align:left;vertical-align:middle;\"><a href=\"#\" onclick=\"modalContactoBodega('" + servicios.get(0) + "');\">" + servicios.get(2) + "</a></td>"
								+ "<td style=\"text-align:left;vertical-align:middle;\"><a href=\"#\" onclick=\"modalContactoCliente('" + cliente + "');\">" + cliente + "</a></td>"
								+ "<td style=\"text-align:left;vertical-align:middle;\"><a href=\"#\" onclick=\"modalContactoProyecto('" + proyecto + "');\">" + proyecto + "</a></td>"
								+ "<td style=\"text-align:left;vertical-align:middle;\">" + comercial + "</td>"
								+ "<td style= \"text-align:right;\" class=\"cfi\">" + DecimalFormato.formato(0.0,mapDec.get(1L)) + "</td>"
								+ "<td style= \"text-align:right;\" class=\"arr\">" + DecimalFormato.formato(0.0,mapDec.get(1L)) + "</td>"
								+ "<td style= \"text-align:right;\" class=\"vta\">" + DecimalFormato.formato(0.0,mapDec.get(1L)) + "</td>"
								+ "<td style= \"text-align:right;\" class=\"serv\">"+servVta+"</td>"
								+ "<td style= \"text-align:right;\" class=\"ajustArr\">" + DecimalFormato.formato(0.0,mapDec.get(1L)) + "</td>"
								+ "<td style= \"text-align:right;\" class=\"ajustVta\">" + DecimalFormato.formato(0.0,mapDec.get(1L)) + "</td>"
								+ "<td style= \"text-align:right;\" class=\"ajustServ\">"+servAjuste+"</td>"
								+ "<td style= \"text-align:right;\" class=\"granTotal\">" + auxTotal + "</td>"

								+ "<td style=\"text-align:center;vertical-align:middle;\">"
								+ "<form id=\"form0_" + servicios.get(0) + "\" class=\"formulario\" method=\"post\" action=\"/reportFacturaProyectoDetalleH/\">"
								+ "<input type=\"hidden\" name=\"id_bodega\" value=\"" + servicios.get(0) + "\">"
								+ "<input type=\"hidden\" name=\"fechaDesde\" value=\"" + desdeAAMMDD + "\">"
								+ "<input type=\"hidden\" name=\"fechaHasta\" value=\"" + hastaAAMMDD + "\">"
								+ "<input type=\"hidden\" name=\"esVenta\" value=\"0\">"
								+ "<input type=\"hidden\" name=\"uf\" value=\"" + uf + "\">"
								+ "<input type=\"hidden\" name=\"usd\" value=\"" + usd + "\">"
								+ "<input type=\"hidden\" name=\"eur\" value=\"" + eur + "\">"
								+ "<a href=\"#\" onclick=\"document.getElementById('form0_" + servicios.get(0) + "').submit()\">"
								+ "<kbd style=\"background-color: #73C6B6\">" + mapeoDiccionario.get("Arriendo") + "</kbd>"
								+ "</a>"
								+ "</form>"
								+ "</td>"

								+ "<td style=\"text-align:center;vertical-align:middle;\">"
								+ "<form id=\"form0Vta_" + servicios.get(0) + "\" class=\"formulario\" method=\"post\" action=\"/reportFacturaProyectoDetalleHVta/\">"
								+ "<input type=\"hidden\" name=\"id_bodega\" value=\"" + servicios.get(0) + "\">"
								+ "<input type=\"hidden\" name=\"fechaDesde\" value=\"" + desdeAAMMDD + "\">"
								+ "<input type=\"hidden\" name=\"fechaHasta\" value=\"" + hastaAAMMDD + "\">"
								+ "<input type=\"hidden\" name=\"esVenta\" value=\"0\">"
								+ "<input type=\"hidden\" name=\"uf\" value=\"" + uf + "\">"
								+ "<input type=\"hidden\" name=\"usd\" value=\"" + usd + "\">"
								+ "<input type=\"hidden\" name=\"eur\" value=\"" + eur + "\">"
								+ "<a href=\"#\" onclick=\"document.getElementById('form0Vta_" + servicios.get(0) + "').submit()\">"
								+ "<kbd style=\"background-color: #73C6B6\">Venta</kbd>"
								+ "</a>"
								+ "</form>"
								+ "</td>"

								+ "<td style=\"text-align:center;vertical-align:middle;\">"
								+ "<form id=\"form0All_" + servicios.get(0) + "\" class=\"formulario\" method=\"post\" action=\"/reportFacturaProyectoDetalleHServ/\">"
								+ "<input type=\"hidden\" name=\"id_bodega\" value=\"" + servicios.get(0) + "\">"
								+ "<input type=\"hidden\" name=\"fechaDesde\" value=\"" + desdeAAMMDD + "\">"
								+ "<input type=\"hidden\" name=\"fechaHasta\" value=\"" + hastaAAMMDD + "\">"
								+ "<input type=\"hidden\" name=\"esVenta\" value=\"0\">"
								+ "<input type=\"hidden\" name=\"uf\" value=\"" + uf + "\">"
								+ "<input type=\"hidden\" name=\"usd\" value=\"" + usd + "\">"
								+ "<input type=\"hidden\" name=\"eur\" value=\"" + eur + "\">"
								+ "<a href=\"#\" onclick=\"document.getElementById('form0All_" + servicios.get(0) + "').submit()\">"
								+ "<kbd style=\"background-color: #73C6B6\">Servicio</kbd>"
								+ "</a>"
								+ "</form>"
								+ "</td>"

								+ "<td style=\"text-align:center;vertical-align:middle;\">"
								+ "<form id=\"form0Serv_" + servicios.get(0) + "\" class=\"formulario\" method=\"post\" action=\"/reportFacturaProyectoDetalleHALL/\">"
								+ "<input type=\"hidden\" name=\"id_bodega\" value=\"" + servicios.get(0) + "\">"
								+ "<input type=\"hidden\" name=\"fechaDesde\" value=\"" + desdeAAMMDD + "\">"
								+ "<input type=\"hidden\" name=\"fechaHasta\" value=\"" + hastaAAMMDD + "\">"
								+ "<input type=\"hidden\" name=\"esVenta\" value=\"0\">"
								+ "<input type=\"hidden\" name=\"uf\" value=\"" + uf + "\">"
								+ "<input type=\"hidden\" name=\"usd\" value=\"" + usd + "\">"
								+ "<input type=\"hidden\" name=\"eur\" value=\"" + eur + "\">"
								+ "<a href=\"#\" onclick=\"document.getElementById('form0Serv_" + servicios.get(0) + "').submit()\">"
								+ "<kbd style=\"background-color: #73C6B6\">Todo</kbd>"
								+ "</a>"
								+ "</form>"
								+ "</td>";
						tabla += "</TR>";
					}
				}



				tabla += "</tbody>"
						+ "<tfoot>"
						+ "<TR>"
						+ "<td style=\"background-color: #eeeeee\">TOTALES</td>"
						+ "<td style=\"background-color: #eeeeee\"></td>"
						+ "<td style=\"background-color: #eeeeee\"></td>"
						+ "<td style=\"background-color: #eeeeee\"></td>"
						+ "<td style=\"background-color: #eeeeee\"></td>"
						+ "<td style=\"background-color: #eeeeee;text-align:right;\" id=\"cfi\"></td>"
						+ "<td style=\"background-color: #eeeeee;text-align:right;\" id=\"arr\"></td>"
				+ "<td style=\"background-color: #eeeeee;text-align:right;\" id=\"vta\"></td>"
				+ "<td style=\"background-color: #eeeeee;text-align:right;\" id=\"serv\"></td>"
						+ "<td style=\"background-color: #eeeeee;text-align:right;\" id=\"ajustArr\"></td>"
				+ "<td style=\"background-color: #eeeeee;text-align:right;\" id=\"ajustVta\"></td>"
				+ "<td style=\"background-color: #eeeeee;text-align:right;\" id=\"ajustServ\"></td>"
						+ "<td style=\"background-color: #eeeeee;text-align:right;\" id=\"granTotal\"></td>"
						+ "<td style=\"background-color: #eeeeee;text-align:right;\">"
				+ "<td style=\"background-color: #eeeeee;text-align:right;\">"
				+ "<td style=\"background-color: #eeeeee;text-align:right;\">"
				+ "<td style=\"background-color: #eeeeee;text-align:right;\">"
						+ "</TR>"
						+ "</tfoot>"
						+ "</table>";
				return ok(reportFacturaProyectoH.render(mapeoDiccionario, mapeoPermiso, userMnu, tabla, Fechas.DDMMAA(desdeAAMMDD), Fechas.DDMMAA(hastaAAMMDD), id_proyecto,
						uf, usd, eur, desdeAAMMDD, hastaAAMMDD, mapDec.get(1L), "0"));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reportFacturaProyectoHGet(Http.Request request, String archivoPDF, String desdeAAMMDD, String hastaAAMMDD,
											Double uf, Double usd, Double eur) {
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
		if(mapeoPermiso.get("reportFacturaDetalleProyecto")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
			Map<Long, Double> tasas = null;
			Map<String, Double> mapFijaTasas = null;
			List<Long> listIdBodegaEmpresa = null;
			Map<Long, Calc_BodegaEmpresa> mapBodegaEmpresa = null;
			Map<String, Calc_Precio> mapPrecios = null;
			Map<Long, Calc_Precio> mapMaestroPrecios = null;
			List<Long> listIdGuia_fechaCorte = null;
			List<Inventarios> inventario = null;
			List<Long> listIdGuia_entreFechas = null;
			List<Calc_AjustesEP> listaAjustes = null;
			Map<Long, List<String>> mapBodega = null;
			Map<Long, Long> dec = null;
			Map<String, String> map = null;
			String id_proyecto = null;
			Map<Long,Long> mapDec = null;
			try (Connection con = dbRead.getConnection()) {
				if (id_proyecto == null) {
					id_proyecto = "0";
				}
				id_proyecto = id_proyecto.trim();
				tasas = new HashMap<Long, Double>();
				tasas.put((long) 1, (double) 1);    // 'Peso Chileno', 'CLP', '0'
				tasas.put((long) 2, usd);            // 'Dólar', 'USD', '2'
				tasas.put((long) 3, eur);            // 'Euro', 'EUR', '3'
				tasas.put((long) 4, uf);            // 'Unidad Fomento', 'UF', '4'
				String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
				listIdBodegaEmpresa = ModCalc_InvInicial.listIdBodegaEmpresa(con, s.baseDato, permisoPorBodega);
				mapBodegaEmpresa = Calc_BodegaEmpresa.mapAllBodegasVigentes(con, s.baseDato);
				mapPrecios = Calc_Precio.mapPrecios(con, s.baseDato, listIdBodegaEmpresa);
				mapMaestroPrecios = Calc_Precio.mapMaestroPrecios(con, s.baseDato);
				mapFijaTasas = BodegaEmpresa.mapFijaTasasAll(con, s.baseDato);
				listIdGuia_fechaCorte = ModCalc_InvInicial.listIdGuia_fechaCorte(con, s.baseDato, desdeAAMMDD);
				inventario = Inventarios.inventario(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_fechaCorte);
				listIdGuia_entreFechas = ModCalc_GuiasPer.listIdGuia_entreFecha(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
				listaAjustes = Calc_AjustesEP.listaAjustesEntreFechas(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
				mapBodega = BodegaEmpresa.mapIdBod_BodegaEmpresaInternasExternas(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
				dec = Moneda.numeroDecimal(con, s.baseDato);
				map = UsuarioPermiso.mapPermisoIdBodega(con, s.baseDato, Long.parseLong(s.id_usuario));
				mapDec = Moneda.numeroDecimal(con, s.baseDato);

			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
			ReportFacturas reporte = ModCalc_InvInicial.resumenInvInicial(s.baseDato, desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa,
					mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, listIdGuia_fechaCorte, inventario);
			List<ModCalc_InvInicial> inventarioInicial = reporte.resumenInvInicial;
			listIdGuia_fechaCorte = null;
			inventario = null;
			List<Inventarios> guiasPer = null;
			Map<String, String> mapPermanencias = null;
			int nroDec = 0;
			try (Connection con = dbRead.getConnection()) {
				guiasPer = Inventarios.guiasPer(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_entreFechas);
				mapPermanencias = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, s.baseDato);
				nroDec = Moneda.numeroDecimalxId(con, s.baseDato, "1");
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
			try {
				List<ModCalc_GuiasPer> guiasPeriodo = ModCalc_GuiasPer.resumenGuiasPer(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas,
						mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, guiasPer, mapPermanencias);
				mapPermanencias = null;
				mapBodegaEmpresa = null;
				mapPrecios = null;
				mapMaestroPrecios = null;
				guiasPer = null;
				List<ModeloCalculo> listado = ModeloCalculo.valorTotalporBodega(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, inventarioInicial, guiasPeriodo, listaAjustes);
				inventarioInicial = null;
				guiasPeriodo = null;
				listaAjustes = null;
				List<List<String>> proyectosAux = ReportFacturas.reportFacturaProyecto(listado, mapBodega);
				mapBodega = null;
				List<List<String>> resumenTotales = ReportFacturas.resumenTotalesPorProyecto(listado, dec);
				listado = null;
				List<List<String>> proyectos = new ArrayList<List<String>>();
				if (!map.isEmpty()) {
					for (List<String> aux : proyectosAux) {
						String idBodega = map.get(aux.get(1));
						if (idBodega != null) {
							proyectos.add(aux);
						}
					}
				} else {
					proyectos = proyectosAux;
				}
				proyectosAux = null;
				if (!id_proyecto.equals("0")) {
					List<List<String>> aux = new ArrayList<List<String>>();
					for (List<String> lista1 : proyectos) {
						if (lista1.get(3).trim().equals(id_proyecto)) {
							aux.add(lista1);
						}
					}
					proyectos = aux;
				}

				Map<String,List<String>> mapServicios = new HashMap<String,List<String>>();
				try (Connection con = dbRead.getConnection()) {
					Map<Long,Long> mapIdEquipoVsIdGrupo = Equipo.mapIdEquipoVsIdGrupo(con, s.baseDato);
					Long id_grupo = (long)0;
					Map<String,ListaPrecioServicio> mapPreciosOdo = ListaPrecioServicio.mapAllListaPrecioServicio(con, s.baseDato);

					Map<Long,BodegaEmpresa> mapBodegas = BodegaEmpresa.mapAll(con, s.baseDato);
					Map<Long,Double> mapTotalAjustePorBodega = Calc_AjustesEpOdo.mapTotalAjustePorBodega(con, s.baseDato, desdeAAMMDD, hastaAAMMDD, s.aplicaPorSucursal, s.id_sucursal);
					List<VentaServicio> listVentaServicio = VentaServicio.allEntreFechas(con, s.baseDato, desdeAAMMDD, hastaAAMMDD, s.aplicaPorSucursal, s.id_sucursal);
					List<List<String>> resumenTotalesPorProyecto = ReportOdo.resumenTotalesPorProyecto(s.baseDato, listVentaServicio, mapFijaTasas, tasas, mapDec, mapTotalAjustePorBodega, mapBodegas, mapPreciosOdo, id_grupo, mapIdEquipoVsIdGrupo);
					for(List<String> x: resumenTotalesPorProyecto){
						mapServicios.put(x.get(0),x);
					}
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				}



				String tabla = "<table id=\"tablaPrincipal\" class=\"table table-sm table-hover table-bordered table-condensed table-fluid\">"
						+ "<thead style=\"background-color: #eeeeee\">"
						+ "<TR> "
						+ "<TH style= \"text-align: center;vertical-align: top;\">SUCURSAL</TH>"
						+ "<TH style= \"text-align: center;vertical-align: top;\">" + mapeoDiccionario.get("BODEGA") + "/PROYECTO</TH>"
						+ "<TH style= \"text-align: center;vertical-align: top;\">NOMBRE<BR>CLIENTE</TH>"
						+ "<TH style= \"text-align: center;vertical-align: top;\">NOMBRE<br>PROYECTO</TH>"
						+ "<TH style= \"text-align: center;vertical-align: top;\">COMERCIAL</TH>"
						+ "<TH style= \"text-align:center;vertical-align:top;\">CFI (en " + mapeoDiccionario.get("PESOS") + ")</TH>"
						+ "<TH style= \"text-align:center;vertical-align:top;\">SubTotal<br>" + mapeoDiccionario.get("ARRIENDO") + "</TH>"
						+ "<TH style= \"text-align:center;vertical-align:top;\">SubTotal<br>VENTA</TH>"
						+ "<TH style= \"text-align:center;vertical-align:top;\">SubTotal<br>SERVICIO</TH>"
						+ "<TH style= \"text-align:center;vertical-align:top;\">Ajustes<BR>(" + mapeoDiccionario.get("ARRIENDO") + ")</TH>"
						+ "<TH style= \"text-align:center;vertical-align:top;\">Ajustes<BR>(VENTA)</TH>"
						+ "<TH style= \"text-align:center;vertical-align:top;\">Ajustes<BR>(SERVICIO)</TH>"
						+ "<TH style= \"text-align:center;vertical-align:top;\">TOTAL<BR>(en " + mapeoDiccionario.get("PESOS") + ")</TH>"
						+ "<TH width=\"5%\" >" + mapeoDiccionario.get("ARRIENDO") + "<BR></TH>"
						+ "<TH width=\"5%\" >VENTA<BR></TH>"
						+ "<TH width=\"5%\" >SERVICIO<BR></TH>"
						+ "<TH width=\"5%\" >TODO<BR></TH>"
						+ "</TR>"
						+ "</thead>"
						+ "<tbody>";

				Map<String,String> mapAuxParaRevisaServ = new HashMap<String,String>();
				for (List<String> lista1 : proyectos) {
					for (List<String> total : resumenTotales) {
						if (lista1.get(1).equals(total.get(0))) {
							Double auxTotal = (double)0;
							try{
								auxTotal=Double.parseDouble(total.get(4).replaceAll(",",""));
							}catch(Exception e){}
							List<String> servicios = mapServicios.get(total.get(0));
							String servVta = DecimalFormato.formato(0.0,mapDec.get(1L));
							String servAjuste =  DecimalFormato.formato(0.0,mapDec.get(1L));

							if(servicios!=null){
								Double auxServVta=0.0;
								Double auxServAjuste=0.0;
								try{
									auxServVta = Double.parseDouble(servicios.get(5).replaceAll(",",""));
									servVta = servicios.get(5);
								}catch(Exception e){}
								try{
									auxServAjuste = Double.parseDouble(servicios.get(6).replaceAll(",",""));
									servAjuste = servicios.get(6);
								}catch(Exception e){}
								auxTotal += auxServVta + auxServAjuste;
							}
							if( auxTotal > 0) {
								mapAuxParaRevisaServ.put(lista1.get(1),lista1.get(1));
								String totales = DecimalFormato.formato(auxTotal,mapDec.get(1L));
								tabla += "<TR>";
								tabla += "<td style=\"text-align:left;vertical-align:middle;\">" + lista1.get(14) + "</td>"
										+ "<td style=\"text-align:left;vertical-align:middle;\"><a href=\"#\" onclick=\"modalContactoBodega('" + lista1.get(1) + "');\">" + lista1.get(5) + "</a></td>"
										+ "<td style=\"text-align:left;vertical-align:middle;\"><a href=\"#\" onclick=\"modalContactoCliente('" + lista1.get(2) + "');\">" + lista1.get(7) + "</a></td>"
										+ "<td style=\"text-align:left;vertical-align:middle;\"><a href=\"#\" onclick=\"modalContactoProyecto('" + lista1.get(3) + "');\">" + lista1.get(8) + "</a></td>"
										+ "<td style=\"text-align:left;vertical-align:middle;\">" + lista1.get(10) + "</td>"
										+ "<td style= \"text-align:right;\" class=\"cfi\">" + total.get(3) + "</td>"
										+ "<td style= \"text-align:right;\" class=\"arr\">" + total.get(1) + "</td>"
										+ "<td style= \"text-align:right;\" class=\"vta\">" + total.get(2) + "</td>"
										+ "<td style= \"text-align:right;\" class=\"serv\">"+servVta+"</td>"
										+ "<td style= \"text-align:right;\" class=\"ajustArr\">" + total.get(5) + "</td>"
										+ "<td style= \"text-align:right;\" class=\"ajustVta\">" + total.get(6) + "</td>"
										+ "<td style= \"text-align:right;\" class=\"ajustServ\">"+servAjuste+"</td>"
										+ "<td style= \"text-align:right;\" class=\"granTotal\">" + totales + "</td>"

										+ "<td style=\"text-align:center;vertical-align:middle;\">"
										+ "<form id=\"form0_" + lista1.get(1) + "\" class=\"formulario\" method=\"post\" action=\"/reportFacturaProyectoDetalleH/\">"
										+ "<input type=\"hidden\" name=\"id_bodega\" value=\"" + lista1.get(1) + "\">"
										+ "<input type=\"hidden\" name=\"fechaDesde\" value=\"" + desdeAAMMDD + "\">"
										+ "<input type=\"hidden\" name=\"fechaHasta\" value=\"" + hastaAAMMDD + "\">"
										+ "<input type=\"hidden\" name=\"esVenta\" value=\"0\">"
										+ "<input type=\"hidden\" name=\"uf\" value=\"" + uf + "\">"
										+ "<input type=\"hidden\" name=\"usd\" value=\"" + usd + "\">"
										+ "<input type=\"hidden\" name=\"eur\" value=\"" + eur + "\">"
										+ "<a href=\"#\" onclick=\"document.getElementById('form0_" + lista1.get(1) + "').submit()\">"
										+ "<kbd style=\"background-color: #73C6B6\">" + mapeoDiccionario.get("Arriendo") + "</kbd>"
										+ "</a>"
										+ "</form>"
										+ "</td>"

										+ "<td style=\"text-align:center;vertical-align:middle;\">"
										+ "<form id=\"form0Vta_" + lista1.get(1) + "\" class=\"formulario\" method=\"post\" action=\"/reportFacturaProyectoDetalleHVta/\">"
										+ "<input type=\"hidden\" name=\"id_bodega\" value=\"" + lista1.get(1) + "\">"
										+ "<input type=\"hidden\" name=\"fechaDesde\" value=\"" + desdeAAMMDD + "\">"
										+ "<input type=\"hidden\" name=\"fechaHasta\" value=\"" + hastaAAMMDD + "\">"
										+ "<input type=\"hidden\" name=\"esVenta\" value=\"0\">"
										+ "<input type=\"hidden\" name=\"uf\" value=\"" + uf + "\">"
										+ "<input type=\"hidden\" name=\"usd\" value=\"" + usd + "\">"
										+ "<input type=\"hidden\" name=\"eur\" value=\"" + eur + "\">"
										+ "<a href=\"#\" onclick=\"document.getElementById('form0Vta_" + lista1.get(1) + "').submit()\">"
										+ "<kbd style=\"background-color: #73C6B6\">Venta</kbd>"
										+ "</a>"
										+ "</form>"
										+ "</td>"

										+ "<td style=\"text-align:center;vertical-align:middle;\">"
										+ "<form id=\"form0Serv_" + lista1.get(1) + "\" class=\"formulario\" method=\"post\" action=\"/reportFacturaProyectoDetalleHServ/\">"
										+ "<input type=\"hidden\" name=\"id_bodega\" value=\"" + lista1.get(1) + "\">"
										+ "<input type=\"hidden\" name=\"fechaDesde\" value=\"" + desdeAAMMDD + "\">"
										+ "<input type=\"hidden\" name=\"fechaHasta\" value=\"" + hastaAAMMDD + "\">"
										+ "<input type=\"hidden\" name=\"esVenta\" value=\"0\">"
										+ "<input type=\"hidden\" name=\"uf\" value=\"" + uf + "\">"
										+ "<input type=\"hidden\" name=\"usd\" value=\"" + usd + "\">"
										+ "<input type=\"hidden\" name=\"eur\" value=\"" + eur + "\">"
										+ "<a href=\"#\" onclick=\"document.getElementById('form0Serv_" + lista1.get(1) + "').submit()\">"
										+ "<kbd style=\"background-color: #73C6B6\">Servicio</kbd>"
										+ "</a>"
										+ "</form>"
										+ "</td>"

										+ "<td style=\"text-align:center;vertical-align:middle;\">"
										+ "<form id=\"form0All_" + lista1.get(1) + "\" class=\"formulario\" method=\"post\" action=\"/reportFacturaProyectoDetalleHALL/\">"
										+ "<input type=\"hidden\" name=\"id_bodega\" value=\"" + lista1.get(1) + "\">"
										+ "<input type=\"hidden\" name=\"fechaDesde\" value=\"" + desdeAAMMDD + "\">"
										+ "<input type=\"hidden\" name=\"fechaHasta\" value=\"" + hastaAAMMDD + "\">"
										+ "<input type=\"hidden\" name=\"esVenta\" value=\"0\">"
										+ "<input type=\"hidden\" name=\"uf\" value=\"" + uf + "\">"
										+ "<input type=\"hidden\" name=\"usd\" value=\"" + usd + "\">"
										+ "<input type=\"hidden\" name=\"eur\" value=\"" + eur + "\">"
										+ "<a href=\"#\" onclick=\"document.getElementById('form0All_" + lista1.get(1) + "').submit()\">"
										+ "<kbd style=\"background-color: #73C6B6\">Todo</kbd>"
										+ "</a>"
										+ "</form>"
										+ "</td>";
								tabla += "</TR>";
							}
						}
					}
				}

				for (Map.Entry<String, List<String>> entry : mapServicios.entrySet()) {
					String key = entry.getKey();
					List<String> servicios = entry.getValue();
					String valida = mapAuxParaRevisaServ.get(key);
					if(valida == null) {
						String cliente = "clente";
						String proyecto = "proyecto";
						String comercial = "comercial";
						Double auxTotal = 0.0;
						Double auxServVta=0.0;
						Double auxServAjuste=0.0;
						String servVta = DecimalFormato.formato(0.0,mapDec.get(1L));
						String servAjuste =  DecimalFormato.formato(0.0,mapDec.get(1L));
						try{
							auxServVta = Double.parseDouble(servicios.get(5).replaceAll(",",""));
							servVta = servicios.get(5);
						}catch(Exception e){}
						try{
							auxServAjuste = Double.parseDouble(servicios.get(6).replaceAll(",",""));
							servAjuste = servicios.get(6);
						}catch(Exception e){}
						auxTotal += auxServVta + auxServAjuste;
						tabla += "<TR>";
						tabla += "<td style=\"text-align:left;vertical-align:middle;\">" + "" + "</td>"
								+ "<td style=\"text-align:left;vertical-align:middle;\"><a href=\"#\" onclick=\"modalContactoBodega('" + servicios.get(0) + "');\">" + servicios.get(2) + "</a></td>"
								+ "<td style=\"text-align:left;vertical-align:middle;\"><a href=\"#\" onclick=\"modalContactoCliente('" + cliente + "');\">" + cliente + "</a></td>"
								+ "<td style=\"text-align:left;vertical-align:middle;\"><a href=\"#\" onclick=\"modalContactoProyecto('" + proyecto + "');\">" + proyecto + "</a></td>"
								+ "<td style=\"text-align:left;vertical-align:middle;\">" + comercial + "</td>"
								+ "<td style= \"text-align:right;\" class=\"cfi\">" + DecimalFormato.formato(0.0,mapDec.get(1L)) + "</td>"
								+ "<td style= \"text-align:right;\" class=\"arr\">" + DecimalFormato.formato(0.0,mapDec.get(1L)) + "</td>"
								+ "<td style= \"text-align:right;\" class=\"vta\">" + DecimalFormato.formato(0.0,mapDec.get(1L)) + "</td>"
								+ "<td style= \"text-align:right;\" class=\"serv\">"+servVta+"</td>"
								+ "<td style= \"text-align:right;\" class=\"ajustArr\">" + DecimalFormato.formato(0.0,mapDec.get(1L)) + "</td>"
								+ "<td style= \"text-align:right;\" class=\"ajustVta\">" + DecimalFormato.formato(0.0,mapDec.get(1L)) + "</td>"
								+ "<td style= \"text-align:right;\" class=\"ajustServ\">"+servAjuste+"</td>"
								+ "<td style= \"text-align:right;\" class=\"granTotal\">" + auxTotal + "</td>"

								+ "<td style=\"text-align:center;vertical-align:middle;\">"
								+ "<form id=\"form0_" + servicios.get(0) + "\" class=\"formulario\" method=\"post\" action=\"/reportFacturaProyectoDetalleH/\">"
								+ "<input type=\"hidden\" name=\"id_bodega\" value=\"" + servicios.get(0) + "\">"
								+ "<input type=\"hidden\" name=\"fechaDesde\" value=\"" + desdeAAMMDD + "\">"
								+ "<input type=\"hidden\" name=\"fechaHasta\" value=\"" + hastaAAMMDD + "\">"
								+ "<input type=\"hidden\" name=\"esVenta\" value=\"0\">"
								+ "<input type=\"hidden\" name=\"uf\" value=\"" + uf + "\">"
								+ "<input type=\"hidden\" name=\"usd\" value=\"" + usd + "\">"
								+ "<input type=\"hidden\" name=\"eur\" value=\"" + eur + "\">"
								+ "<a href=\"#\" onclick=\"document.getElementById('form0_" + servicios.get(0) + "').submit()\">"
								+ "<kbd style=\"background-color: #73C6B6\">" + mapeoDiccionario.get("Arriendo") + "</kbd>"
								+ "</a>"
								+ "</form>"
								+ "</td>"

								+ "<td style=\"text-align:center;vertical-align:middle;\">"
								+ "<form id=\"form0Vta_" + servicios.get(0) + "\" class=\"formulario\" method=\"post\" action=\"/reportFacturaProyectoDetalleHVta/\">"
								+ "<input type=\"hidden\" name=\"id_bodega\" value=\"" + servicios.get(0) + "\">"
								+ "<input type=\"hidden\" name=\"fechaDesde\" value=\"" + desdeAAMMDD + "\">"
								+ "<input type=\"hidden\" name=\"fechaHasta\" value=\"" + hastaAAMMDD + "\">"
								+ "<input type=\"hidden\" name=\"esVenta\" value=\"0\">"
								+ "<input type=\"hidden\" name=\"uf\" value=\"" + uf + "\">"
								+ "<input type=\"hidden\" name=\"usd\" value=\"" + usd + "\">"
								+ "<input type=\"hidden\" name=\"eur\" value=\"" + eur + "\">"
								+ "<a href=\"#\" onclick=\"document.getElementById('form0Vta_" + servicios.get(0) + "').submit()\">"
								+ "<kbd style=\"background-color: #73C6B6\">Venta</kbd>"
								+ "</a>"
								+ "</form>"
								+ "</td>"

								+ "<td style=\"text-align:center;vertical-align:middle;\">"
								+ "<form id=\"form0All_" + servicios.get(0) + "\" class=\"formulario\" method=\"post\" action=\"/reportFacturaProyectoDetalleHServ/\">"
								+ "<input type=\"hidden\" name=\"id_bodega\" value=\"" + servicios.get(0) + "\">"
								+ "<input type=\"hidden\" name=\"fechaDesde\" value=\"" + desdeAAMMDD + "\">"
								+ "<input type=\"hidden\" name=\"fechaHasta\" value=\"" + hastaAAMMDD + "\">"
								+ "<input type=\"hidden\" name=\"esVenta\" value=\"0\">"
								+ "<input type=\"hidden\" name=\"uf\" value=\"" + uf + "\">"
								+ "<input type=\"hidden\" name=\"usd\" value=\"" + usd + "\">"
								+ "<input type=\"hidden\" name=\"eur\" value=\"" + eur + "\">"
								+ "<a href=\"#\" onclick=\"document.getElementById('form0All_" + servicios.get(0) + "').submit()\">"
								+ "<kbd style=\"background-color: #73C6B6\">Servicio</kbd>"
								+ "</a>"
								+ "</form>"
								+ "</td>"

								+ "<td style=\"text-align:center;vertical-align:middle;\">"
								+ "<form id=\"form0Serv_" + servicios.get(0) + "\" class=\"formulario\" method=\"post\" action=\"/reportFacturaProyectoDetalleHALL/\">"
								+ "<input type=\"hidden\" name=\"id_bodega\" value=\"" + servicios.get(0) + "\">"
								+ "<input type=\"hidden\" name=\"fechaDesde\" value=\"" + desdeAAMMDD + "\">"
								+ "<input type=\"hidden\" name=\"fechaHasta\" value=\"" + hastaAAMMDD + "\">"
								+ "<input type=\"hidden\" name=\"esVenta\" value=\"0\">"
								+ "<input type=\"hidden\" name=\"uf\" value=\"" + uf + "\">"
								+ "<input type=\"hidden\" name=\"usd\" value=\"" + usd + "\">"
								+ "<input type=\"hidden\" name=\"eur\" value=\"" + eur + "\">"
								+ "<a href=\"#\" onclick=\"document.getElementById('form0Serv_" + servicios.get(0) + "').submit()\">"
								+ "<kbd style=\"background-color: #73C6B6\">Todo</kbd>"
								+ "</a>"
								+ "</form>"
								+ "</td>";
						tabla += "</TR>";
					}
				}



				tabla += "</tbody>"
						+ "<tfoot>"
						+ "<TR>"
						+ "<td style=\"background-color: #eeeeee\">TOTALES</td>"
						+ "<td style=\"background-color: #eeeeee\"></td>"
						+ "<td style=\"background-color: #eeeeee\"></td>"
						+ "<td style=\"background-color: #eeeeee\"></td>"
						+ "<td style=\"background-color: #eeeeee\"></td>"
						+ "<td style=\"background-color: #eeeeee;text-align:right;\" id=\"cfi\"></td>"
						+ "<td style=\"background-color: #eeeeee;text-align:right;\" id=\"arr\"></td>"
						+ "<td style=\"background-color: #eeeeee;text-align:right;\" id=\"vta\"></td>"
						+ "<td style=\"background-color: #eeeeee;text-align:right;\" id=\"serv\"></td>"
						+ "<td style=\"background-color: #eeeeee;text-align:right;\" id=\"ajustArr\"></td>"
						+ "<td style=\"background-color: #eeeeee;text-align:right;\" id=\"ajustVta\"></td>"
						+ "<td style=\"background-color: #eeeeee;text-align:right;\" id=\"ajustServ\"></td>"
						+ "<td style=\"background-color: #eeeeee;text-align:right;\" id=\"granTotal\"></td>"
						+ "<td style=\"background-color: #eeeeee;text-align:right;\">"
						+ "<td style=\"background-color: #eeeeee;text-align:right;\">"
						+ "<td style=\"background-color: #eeeeee;text-align:right;\">"
						+ "<td style=\"background-color: #eeeeee;text-align:right;\">"
						+ "</TR>"
						+ "</tfoot>"
						+ "</table>";
				return ok(reportFacturaProyectoH.render(mapeoDiccionario, mapeoPermiso, userMnu, tabla, Fechas.DDMMAA(desdeAAMMDD), Fechas.DDMMAA(hastaAAMMDD), id_proyecto,
						uf, usd, eur, desdeAAMMDD, hastaAAMMDD, mapDec.get(1L), archivoPDF));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
	}

	public Result reportFacturaProyectoDetalleH(Http.Request request) {
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
		} else {
			Map<String, String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String, String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
			try (Connection con = dbRead.getConnection()) {
				String auxIdBodega = form.get("id_bodega");
				Long id_bodegaEmpresa = (long) 0;
				if (auxIdBodega != null) {
					id_bodegaEmpresa = Long.parseLong(auxIdBodega.trim());
				}
				String fechaDesde = form.get("fechaDesde").trim();
				String fechaHasta = form.get("fechaHasta").trim();
				String esVenta = form.get("esVenta").trim();
				Double uf = Double.parseDouble(form.get("uf").replaceAll(",", "").trim());
				Double usd = Double.parseDouble(form.get("usd").replaceAll(",", "").trim());
				Double eur = Double.parseDouble(form.get("eur").replaceAll(",", "").trim());
				String concepto = mapeoDiccionario.get("ARRIENDO");
				if ("1".equals(esVenta)) {
					concepto = "VENTA";
				}
				List<List<String>> datos = ReportMovimientos.movimientoGuias(con, s.baseDato, mapeoDiccionario, id_bodegaEmpresa, esVenta, fechaDesde, fechaHasta, usd, eur, uf, concepto);
				BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
				List<List<String>> datosSeleccionados = new ArrayList<List<String>>();
				Map<String, Double> mapSubtotales = new HashMap<String,Double>();
				Map<Long,Guia> mapGuias = Guia.mapAll(con, s.baseDato);
				Map<String,Guia> mapGuiasIngreso = ModCalc_GuiasPer.mapUltimaGuiaArrIngreso(con, s.baseDato, mapGuias);
				Map<String,Guia> mapGuiasEgreso = ModCalc_GuiasPer.mapUltimaGuiaArrEgreso(con, s.baseDato, mapGuias);
				for(int i=0; i<datos.size(); i++) {
					List<String> aux = new ArrayList<String>();
					aux.add(datos.get(i).get(0));
					aux.add(datos.get(i).get(1));
					aux.add(datos.get(i).get(2));
					aux.add(datos.get(i).get(3));
					aux.add(datos.get(i).get(6));
					aux.add(datos.get(i).get(7));
					aux.add(datos.get(i).get(9));
					if(i==0 || i==2){
						aux.add("");
					}else{
						aux.add(datos.get(i).get(10));
					}
					if(i==0 || i==1){
						aux.add("");
					}else if(i==4){
						aux.add("Cant Dias");
					}else if(i > 4 && i < datos.size()-3){
						Long cantDia = (long) 0;
						try{
							Double totArr = Double.parseDouble(datos.get(i).get(datos.get(i).size() - 6).replaceAll(",", "").trim());
							Double pArrDia = Double.parseDouble(datos.get(i).get(10).replaceAll(",", "").trim());
							if(totArr > 0) {
								cantDia = Math.round(totArr / pArrDia);
							}
						}catch(Exception e){}
						aux.add(cantDia.toString());
					}else{
						aux.add("");
					}
					aux.add(datos.get(i).get(datos.get(i).size() - 6));
					aux.add(datos.get(i).get(datos.get(i).size() - 5));
					if( ! (i==2 || i==3 || i==datos.size()-2 || i==datos.size()-3) ){
						if(i > 4 && i < datos.size()-3) {
							try {
								Double dblAux = Double.parseDouble(datos.get(i).get(datos.get(i).size() - 5).replaceAll(",", "").trim());
								Double aux1 = mapSubtotales.get(datos.get(i).get(0));
								if (aux1 == null) {
									mapSubtotales.put(datos.get(i).get(0), dblAux);
								} else {
									mapSubtotales.put(datos.get(i).get(0), dblAux + aux1);
								}
							}catch(Exception e){}
						}
						Guia guia = mapGuiasIngreso.get(id_bodegaEmpresa+"_"+datos.get(i).get(2)+"_"+datos.get(i).get(1));
						if(guia != null) {
							aux.add(guia.getNumero().toString());
							aux.add(Fechas.DDMMAA(guia.getFecha()));
						}else{
							if (i == 0) {
								aux.add("Guia");
								aux.add("Guia");
							} else if (i == 1) {
								aux.add("Ultimo");
								aux.add("Fecha");
							} else if (i == 4) {
								aux.add("Ingreso");
								aux.add("Ingreso");
							} else {
								aux.add("");
								aux.add("");
							}
						}
						guia = mapGuiasEgreso.get(id_bodegaEmpresa+"_"+datos.get(i).get(2)+"_"+datos.get(i).get(1));
						if(guia != null) {
							aux.add(guia.getNumero().toString());
							aux.add(Fechas.DDMMAA(guia.getFecha()));
						}else{
							if (i == 0) {
								aux.add("Guia");
								aux.add("Guia");
							} else if (i == 1) {
								aux.add("Ultima");
								aux.add("Fecha");
							} else if (i == 4) {
								aux.add("Salida");
								aux.add("Salida");
							} else {
								aux.add("");
								aux.add("");
							}
						}
						datosSeleccionados.add(aux);
					}
				}
				datosSeleccionados.get(datosSeleccionados.size()-1).set(0,"TOTAL NETO");
				String idTipoUsuario = s.id_tipoUsuario;
				Cliente cliente = Cliente.find(con, s.baseDato, bodega.getId_cliente());
				Proyecto proyecto = Proyecto.find(con,s.baseDato , bodega.getId_proyecto());
				String oc = Cotizacion.ocParticiaEnBodega(con, s.baseDato, id_bodegaEmpresa);

				EmisorTributario emisor = EmisorTributario.find(con, s.baseDato);
				Double tasaIva = emisor.getTasaIva()/100;
				if(mapeoPermiso.get("parametro.ivaPorBodega")!=null && mapeoPermiso.get("parametro.ivaPorBodega").equals("1")) {
					if(bodega.getIvaBodega() > 0) {
						tasaIva = bodega.getIvaBodega();
					}else {
						Sucursal sucursal = Sucursal.find(con, s.baseDato, bodega.getId_sucursal().toString());
						if(sucursal.getIvaSucursal() > 0) {
							tasaIva = sucursal.getIvaSucursal();
						}
					}
				}
				Long cantDec = (long) Moneda.numeroDecimalxId(con, s.baseDato, "1");

				return ok(reportFacturaProyectoDetalleH.render(mapeoDiccionario, mapeoPermiso, userMnu, idTipoUsuario,
						datosSeleccionados, bodega, esVenta, fechaDesde, fechaHasta, usd, eur, uf, cliente, proyecto, oc,
						tasaIva, cantDec));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reportFacturaProyectoDetalleHExcel(Http.Request request) {
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
			return ok(mensajes.render("/home/",msgErrorFormulario));
		}else {
			Map<String, String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String, String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
			File file = detalleHExcel(s,form);
			try (Connection con = dbRead.getConnection()) {
				String auxIdBodega = form.get("id_bodega");
				Long id_bodegaEmpresa = (long) 0;
				if (auxIdBodega != null) {
					id_bodegaEmpresa = Long.parseLong(auxIdBodega.trim());
				}
				BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
				if(file != null){
					return ok(file,false,Optional.of("EP_Proforma_Simple_"+bodega.nombre+".xlsx"));
				}else {
					return ok("");
				}
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reportFacturaProyectoDetalleHProforma(Http.Request request) {
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
			return ok(mensajes.render("/home/",msgErrorFormulario));
		}else {
			Map<String, String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String, String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
			File file = detalleHVtaExcel(s,form);
			Long id_bodegaEmpresa = (long) 0;
			BodegaEmpresa bodega = null;
			try (Connection con = dbRead.getConnection()) {
				String auxIdBodega = form.get("id_bodega");
				id_bodegaEmpresa = (long) 0;
				if (auxIdBodega != null) {
					id_bodegaEmpresa = Long.parseLong(auxIdBodega.trim());
				}
				bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
			String tipo = form.get("tipo").trim();
			String fechaDesde = form.get("fechaDesde").trim();
			String fechaHasta = form.get("fechaHasta").trim();
			String neto = form.get("neto").trim();
			String iva = form.get("iva").trim();
			String total = form.get("total").trim();

			Long id = (long)0;
			String sql = "INSERT INTO `"+s.baseDato+"`.proformaSimple (" +
					"tipo, fecha, desde, hasta, id_bodegaEmpresa, neto, iva, total " +
					") VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

			Proyecto proyecto = null;
			ProformaSimple proformaSimple = null;
			Cliente cliente = null;
			String archivoPDF = "0";

			try (Connection con = dbWrite.getConnection();
				 PreparedStatement smt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
				Fechas hoy = Fechas.hoy();
				smt.setString(1, tipo);
				smt.setString(2, hoy.getFechaStrAAMMDD());
				smt.setString(3, fechaDesde);
				smt.setString(4, fechaHasta);
				smt.setLong(5, id_bodegaEmpresa);
				smt.setString(6, neto);
				smt.setString(7, iva);
				smt.setString(8, total);
				smt.executeUpdate();
				ResultSet rs = smt.getGeneratedKeys();
				if (rs.next()) {
					id = rs.getLong(1);
				}
				rs.close();
				if(file != null){
					String nameFile = "EP_Prof_Simple_"+id+".xlsx";
					Archivos.grabarArchivo(file, s.baseDato+"/"+nameFile);
					PreparedStatement smt1 = con.prepareStatement("update `"+s.baseDato+"`.proformaSimple set epExcel='"+nameFile+"' where id=?;");
					smt1.setLong(1, id);
					smt1.executeUpdate();
					smt1.close();
				}

				proyecto = Proyecto.find(con,s.baseDato , bodega.getId_proyecto());
				proformaSimple = ProformaSimple.find(con,s.baseDato , id);
				cliente = Cliente.find(con,s.baseDato , bodega.getId_cliente());

				archivoPDF = FormFactura.generaProformaH(s.baseDato, proyecto, proformaSimple, cliente, bodega, form);
				PreparedStatement smt1 = con.prepareStatement("update `"+s.baseDato+"`.proformaSimple set epPdf='"+archivoPDF+"' where id=?;");
				smt1.setLong(1, id);
				smt1.executeUpdate();
				smt1.close();


			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}

			String titulo = "NOTA DE VENTA ALQUILERES";
			String url = "%2FreportFacturaProyectoHGet%2F0&"+fechaDesde+"&"+fechaHasta+"&"+form.get("uf")+"&"+form.get("usd")+"&"+form.get("eur");
			return redirect("/routes2/redirShowPDF/"+archivoPDF+","+titulo+","+url);
		}
	}

	public File detalleHExcel(Sessiones s, DynamicForm form) {
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		Map<String, String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String, String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		try (Connection con = dbRead.getConnection()) {
			String auxIdBodega = form.get("id_bodega");
			Long id_bodegaEmpresa = (long) 0;
			if (auxIdBodega != null) {
				id_bodegaEmpresa = Long.parseLong(auxIdBodega.trim());
			}
			String fechaDesde = form.get("fechaDesde").trim();
			String fechaHasta = form.get("fechaHasta").trim();
			String esVenta = form.get("esVenta").trim();
			Double uf = Double.parseDouble(form.get("uf").replaceAll(",", "").trim());
			Double usd = Double.parseDouble(form.get("usd").replaceAll(",", "").trim());
			Double eur = Double.parseDouble(form.get("eur").replaceAll(",", "").trim());
			String concepto = mapeoDiccionario.get("ARRIENDO");
			if ("1".equals(esVenta)) {
				concepto = "VENTA";
			}
			List<List<String>> datos = ReportMovimientos.movimientoGuias(con, s.baseDato, mapeoDiccionario, id_bodegaEmpresa, esVenta, fechaDesde, fechaHasta, usd, eur, uf, concepto);
			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
			List<List<String>> datosSeleccionados = new ArrayList<List<String>>();
			Map<String, Double> mapSubtotales = new HashMap<String,Double>();
			Map<Long,Guia> mapGuias = Guia.mapAll(con, s.baseDato);
			Map<String,Guia> mapGuiasIngreso = ModCalc_GuiasPer.mapUltimaGuiaArrIngreso(con, s.baseDato, mapGuias);
			Map<String,Guia> mapGuiasEgreso = ModCalc_GuiasPer.mapUltimaGuiaArrEgreso(con, s.baseDato, mapGuias);
			for(int i=0; i<datos.size(); i++) {
				List<String> aux = new ArrayList<String>();
				aux.add(datos.get(i).get(0));
				aux.add(datos.get(i).get(1));
				aux.add(datos.get(i).get(2));
				aux.add(datos.get(i).get(3));
				aux.add(datos.get(i).get(6));
				aux.add(datos.get(i).get(7));
				aux.add(datos.get(i).get(9));
				if(i==0 || i==2){
					aux.add("");
				}else{
					aux.add(datos.get(i).get(10));
				}
				if(i==0 || i==1){
					aux.add("");
				}else if(i==4){
					aux.add("Cant Dias");
				}else if(i > 4 && i < datos.size()-3){
					Long cantDia = (long) 0;
					try{
						Double totArr = Double.parseDouble(datos.get(i).get(datos.get(i).size() - 6).replaceAll(",", "").trim());
						Double pArrDia = Double.parseDouble(datos.get(i).get(10).replaceAll(",", "").trim());
						if(totArr > 0) {
							cantDia = Math.round(totArr / pArrDia);
						}
					}catch(Exception e){}
					aux.add(cantDia.toString());
				}else{
					aux.add("");
				}
				aux.add(datos.get(i).get(datos.get(i).size() - 6));
				aux.add(datos.get(i).get(datos.get(i).size() - 5));
				if( ! (i==2 || i==3 || i==datos.size()-2 || i==datos.size()-3) ){
					if(i > 4 && i < datos.size()-3) {
						try {
							Double dblAux = Double.parseDouble(datos.get(i).get(datos.get(i).size() - 5).replaceAll(",", "").trim());
							Double aux1 = mapSubtotales.get(datos.get(i).get(0));
							if (aux1 == null) {
								mapSubtotales.put(datos.get(i).get(0), dblAux);
							} else {
								mapSubtotales.put(datos.get(i).get(0), dblAux + aux1);
							}
						}catch(Exception e){}
					}
					Guia guia = mapGuiasIngreso.get(id_bodegaEmpresa+"_"+datos.get(i).get(2)+"_"+datos.get(i).get(1));
					if(guia != null) {
						aux.add(guia.getNumero().toString());
						aux.add(Fechas.DDMMAA(guia.getFecha()));
					}else{
						if (i == 0) {
							aux.add("Guia");
							aux.add("Guia");
						} else if (i == 1) {
							aux.add("Ultimo");
							aux.add("Fecha");
						} else if (i == 4) {
							aux.add("Ingreso");
							aux.add("Ingreso");
						} else {
							aux.add("");
							aux.add("");
						}
					}
					guia = mapGuiasEgreso.get(id_bodegaEmpresa+"_"+datos.get(i).get(2)+"_"+datos.get(i).get(1));
					if(guia != null) {
						aux.add(guia.getNumero().toString());
						aux.add(Fechas.DDMMAA(guia.getFecha()));
					}else{
						if (i == 0) {
							aux.add("Guia");
							aux.add("Guia");
						} else if (i == 1) {
							aux.add("Ultima");
							aux.add("Fecha");
						} else if (i == 4) {
							aux.add("Salida");
							aux.add("Salida");
						} else {
							aux.add("");
							aux.add("");
						}
					}
					datosSeleccionados.add(aux);
				}
			}
			datosSeleccionados.get(datosSeleccionados.size()-1).set(0,"TOTAL NETO");
			String idTipoUsuario = s.id_tipoUsuario;
			Cliente cliente = Cliente.find(con, s.baseDato, bodega.getId_cliente());
			Proyecto proyecto = Proyecto.find(con,s.baseDato , bodega.getId_proyecto());
			String oc = Cotizacion.ocParticiaEnBodega(con, s.baseDato, id_bodegaEmpresa);

			EmisorTributario emisor = EmisorTributario.find(con, s.baseDato);
			Double tasaIva = emisor.getTasaIva()/100;
			if(mapeoPermiso.get("parametro.ivaPorBodega")!=null && mapeoPermiso.get("parametro.ivaPorBodega").equals("1")) {
				if(bodega.getIvaBodega() > 0) {
					tasaIva = bodega.getIvaBodega();
				}else {
					Sucursal sucursal = Sucursal.find(con, s.baseDato, bodega.getId_sucursal().toString());
					if(sucursal.getIvaSucursal() > 0) {
						tasaIva = sucursal.getIvaSucursal();
					}
				}
			}

			File file = ReportFacturas.reportFacturaProyectoDetHExcel(s.baseDato,mapeoDiccionario,mapeoPermiso, idTipoUsuario,
					datosSeleccionados, bodega, esVenta, concepto, fechaDesde, fechaHasta, usd, eur, uf,
					cliente, proyecto, oc, tasaIva);

			return(file);
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return(null);
		}
	}

	public Result reportFacturaProyectoDetalleHVta(Http.Request request) {
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
		} else {
			Map<String, String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String, String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
			String fechaDesde = form.get("fechaDesde").trim();
			String fechaHasta = form.get("fechaHasta").trim();
			String esVenta = form.get("esVenta").trim();
			Double uf = Double.parseDouble(form.get("uf").replaceAll(",", "").trim());
			Double usd = Double.parseDouble(form.get("usd").replaceAll(",", "").trim());
			Double eur = Double.parseDouble(form.get("eur").replaceAll(",", "").trim());
			Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodega").trim());
			Map<Long,Double> tasas = new HashMap<Long,Double>();
			tasas.put((long)1, (double) 1); 	// 'Peso Chileno', 'CLP', '0'
			tasas.put((long)2, usd); 			// 'Dólar', 'USD', '2'
			tasas.put((long)3, eur); 			// 'Euro', 'EUR', '3'
			tasas.put((long)4, uf); 			// 'Unidad Fomento', 'UF', '4'
			Map<String, Double> mapFijaTasas = null;
			List<Long> listIdBodegaEmpresa = null;
			Map<Long, Calc_BodegaEmpresa> mapBodegaEmpresa = null;
			Map<String, Calc_Precio> mapPrecios = null;
			Map<Long,Calc_Precio> mapMaestroPrecios = null;
			Map<Long, Cotizacion> mapCotiAllConfirmadas = null;
			Map<Long,String> mapMoneda = null;
			Map<Long, Equipo> mapAllEquipos = null;
			Map<Long,Long> dec = null;
			BodegaEmpresa bodega = null;
			Proyecto proyecto = null;
			String idTipoUsuario = null;
			Cliente cliente = null;
			Map<String,List<List<String>>> mapReportPorGuia10 = null;
			List<List<String>> detalleAjuste = null;
			String oc = null;
			try (Connection con = dbRead.getConnection()) {
				String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
				listIdBodegaEmpresa = ModCalc_InvInicial.listIdBodegaEmpresa(con, s.baseDato, permisoPorBodega);
				mapBodegaEmpresa = Calc_BodegaEmpresa.mapAllBodegasVigentes(con, s.baseDato);
				mapPrecios = Calc_Precio.mapPrecios(con, s.baseDato, listIdBodegaEmpresa);
				mapMaestroPrecios = Calc_Precio.mapMaestroPrecios(con, s.baseDato);
				mapFijaTasas = BodegaEmpresa.mapFijaTasasAll(con, s.baseDato);
				List<Long> listIdGuia_entreFechas = ModCalc_GuiasPer.listIdGuia_entreFecha(con, s.baseDato, fechaDesde, fechaHasta);
				List<Inventarios> guiasPer = Inventarios.guiasPer(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_entreFechas);
				dec = Moneda.numeroDecimal(con, s.baseDato);
				Map<String,String> mapPermanencias = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, s.baseDato);
				mapCotiAllConfirmadas = Cotizacion.mapAllConfirmadasUnaBodega(con, s.baseDato, id_bodegaEmpresa);
				mapMoneda = Moneda.mapIdMonedaMoneda(con, s.baseDato);
				mapAllEquipos = Equipo.mapAllAll(con, s.baseDato);
				bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
				proyecto = Proyecto.find(con,s.baseDato , bodega.getId_proyecto());
				cliente = Cliente.find(con, s.baseDato, bodega.getId_cliente());
				detalleAjuste = AjustesEP.detalleAjuste(con, s.baseDato, id_bodegaEmpresa, fechaDesde, fechaHasta);
				oc = Cotizacion.ocParticiaEnBodega(con, s.baseDato, id_bodegaEmpresa);
				idTipoUsuario = s.id_tipoUsuario;

				Map<Long,List<String>> mapGuias = new HashMap<Long,List<String>>();
				for(Inventarios inv : guiasPer) {
					List<String> aux = new ArrayList<String>();
					aux.add(inv.numeroGuia.toString());
					aux.add(inv.fechaGuia);
					mapGuias.put(inv.id_guia, aux);
				}
				mapReportPorGuia10 = ReportFacturas.mapReportPorGuia10(s.baseDato, id_bodegaEmpresa, fechaDesde, fechaHasta, mapFijaTasas, tasas,
						mapBodegaEmpresa, mapPrecios, mapMaestroPrecios,
						guiasPer, mapPermanencias, dec,  mapCotiAllConfirmadas, mapAllEquipos, mapMoneda);
				Map<String,Long> mapDec = Moneda.numeroDecimalxNombre(con, s.baseDato);
				List<List<String>> datos = new ArrayList<List<String>>();
				for(Map.Entry<String,List<List<String>>> entry : mapReportPorGuia10.entrySet()) {
					for(List<String> list : entry.getValue()) {
						if(list.get(21).equals("1")) {
							Long id_guia = Long.parseLong(list.get(4));
							List<String> guia = mapGuias.get(id_guia);
							Double cant = Double.parseDouble(list.get(13).replaceAll(",", "").trim());
							Double totMonOrigen = Double.parseDouble(list.get(15).replaceAll(",", "").trim());
							String totalMonOrigen = DecimalFormato.formato(cant * totMonOrigen, mapDec.get(list.get(14)));
							if(guia != null) {
								List<String> aux = new ArrayList<String>();
								aux.add(list.get(4)); // 0 id guia
								aux.add(list.get(8)); // 1 nombre de grupo
								aux.add(list.get(9)); // 2 numero cotizacion
								aux.add(list.get(10)); // 3 codigo equipo
								aux.add(list.get(11)); // 4 nombre equipo
								aux.add(list.get(14)); // 5 moneda
								aux.add(list.get(15)); // 6 precio de venta en moneda original
								aux.add(list.get(12)); // 7 unidad
								aux.add(list.get(13)); // 8 cantidad
								aux.add(totalMonOrigen); // 9 total en moneda origen calcular cantidad * precio de venta en moneda original
								aux.add(list.get(20)); // 10 total venta en pesos
								aux.add(guia.get(0)); // 11 numero guia
								aux.add(guia.get(1)); // 12 fecha guia
								datos.add(aux);
							}
						}
					}
				}
				for(List<String> ajustes: detalleAjuste){
					Double valor = Double.parseDouble(ajustes.get(2).replaceAll(",", "").trim());
					if(valor < (double)0 || valor > (double)100){
						List<String> aux = new ArrayList<String>();
						aux.add(""); // 0 id guia
						aux.add(""); // 1 nombre de grupo
						aux.add(""); // 2 numero cotizacion
						aux.add(""); // 3 codigo equipo
						aux.add(ajustes.get(0)); // 4 nombre equipo
						aux.add(""); // 5 moneda
						aux.add(""); // 6 precio de venta en moneda original
						aux.add(""); // 7 unidad
						aux.add(""); // 8 cantidad
						aux.add(""); // 9 total en moneda origen calcular cantidad * precio de venta en moneda original
						aux.add(ajustes.get(2)); // 10 total venta en pesos
						aux.add(""); // 11 numero guia
						aux.add(""); // 12 fecha guia
						datos.add(aux);
					};
				}
				Moneda moneda = Moneda.find(con, s.baseDato, (long)1);
				EmisorTributario emisor = EmisorTributario.find(con, s.baseDato);
				Double tasaIva = emisor.getTasaIva()/100;
				if(mapeoPermiso.get("parametro.ivaPorBodega")!=null && mapeoPermiso.get("parametro.ivaPorBodega").equals("1")) {
					if(bodega.getIvaBodega() > 0) {
						tasaIva = bodega.getIvaBodega();
					}else {
						Sucursal sucursal = Sucursal.find(con, s.baseDato, bodega.getId_sucursal().toString());
						if(sucursal.getIvaSucursal() > 0) {
							tasaIva = sucursal.getIvaSucursal();
						}
					}
				}

				return ok(reportFacturaProyectoDetalleHVta.render(mapeoDiccionario, mapeoPermiso, userMnu, idTipoUsuario,
						datos, bodega, esVenta, fechaDesde, fechaHasta, usd, eur, uf,
						cliente, proyecto, oc, moneda.getNumeroDecimales(), moneda.getNickName(), tasaIva));

			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reportFacturaProyectoDetalleHVtaExcel(Http.Request request) {
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
			return ok(mensajes.render("/home/",msgErrorFormulario));
		}else {
			Map<String, String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String, String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
			File file = detalleHVtaExcel(s,form);
			try (Connection con = dbRead.getConnection()) {
				String auxIdBodega = form.get("id_bodega");
				Long id_bodegaEmpresa = (long) 0;
				if (auxIdBodega != null) {
					id_bodegaEmpresa = Long.parseLong(auxIdBodega.trim());
				}
				BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
				if(file != null){
					return ok(file,false,Optional.of("EP_Proforma_Simple_"+bodega.nombre+".xlsx"));
				}else {
					return ok("");
				}
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reportFacturaProyectoDetalleHVtaProforma(Http.Request request) {
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
			return ok(mensajes.render("/home/",msgErrorFormulario));
		}else {
			Map<String, String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String, String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
			File file = detalleHVtaExcel(s,form);
			Long id_bodegaEmpresa = (long) 0;
			BodegaEmpresa bodega = null;
			try (Connection con = dbRead.getConnection()) {
				String auxIdBodega = form.get("id_bodega");
				id_bodegaEmpresa = (long) 0;
				if (auxIdBodega != null) {
					id_bodegaEmpresa = Long.parseLong(auxIdBodega.trim());
				}
				bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
			String tipo = form.get("tipo").trim();
			String fechaDesde = form.get("fechaDesde").trim();
			String fechaHasta = form.get("fechaHasta").trim();
			String neto = form.get("neto").trim();
			String iva = form.get("iva").trim();
			String total = form.get("total").trim();

			Long id = (long)0;
			String sql = "INSERT INTO `"+s.baseDato+"`.proformaSimple (" +
					"tipo, fecha, desde, hasta, id_bodegaEmpresa, neto, iva, total " +
					") VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

			Proyecto proyecto = null;
			ProformaSimple proformaSimple = null;
			Cliente cliente = null;
			String archivoPDF = "0";

			try (Connection con = dbWrite.getConnection();
				 PreparedStatement smt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
				Fechas hoy = Fechas.hoy();
				smt.setString(1, tipo);
				smt.setString(2, hoy.getFechaStrAAMMDD());
				smt.setString(3, fechaDesde);
				smt.setString(4, fechaHasta);
				smt.setLong(5, id_bodegaEmpresa);
				smt.setString(6, neto);
				smt.setString(7, iva);
				smt.setString(8, total);
				smt.executeUpdate();
				ResultSet rs = smt.getGeneratedKeys();
				if (rs.next()) {
					id = rs.getLong(1);
				}
				rs.close();
				if(file != null){
					String nameFile = "EP_Prof_Simple_"+id+".xlsx";
					Archivos.grabarArchivo(file, s.baseDato+"/"+nameFile);
					PreparedStatement smt1 = con.prepareStatement("update `"+s.baseDato+"`.proformaSimple set epExcel='"+nameFile+"' where id=?;");
					smt1.setLong(1, id);
					smt1.executeUpdate();
					smt1.close();
				}

				proyecto = Proyecto.find(con,s.baseDato , bodega.getId_proyecto());
				proformaSimple = ProformaSimple.find(con,s.baseDato , id);
				cliente = Cliente.find(con,s.baseDato , bodega.getId_cliente());

				archivoPDF = FormFactura.generaProformaH(s.baseDato, proyecto, proformaSimple, cliente, bodega, form);
				PreparedStatement smt1 = con.prepareStatement("update `"+s.baseDato+"`.proformaSimple set epPdf='"+archivoPDF+"' where id=?;");
				smt1.setLong(1, id);
				smt1.executeUpdate();
				smt1.close();

			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}

			String titulo = "NOTA DE VENTA VENTAS";
			String url = "%2FreportFacturaProyectoHGet%2F0&"+fechaDesde+"&"+fechaHasta+"&"+form.get("uf")+"&"+form.get("usd")+"&"+form.get("eur");
			return redirect("/routes2/redirShowPDF/"+archivoPDF+","+titulo+","+url);
		}
	}

	public File detalleHVtaExcel(Sessiones s, DynamicForm form) {
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		Map<String, String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String, String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		String fechaDesde = form.get("fechaDesde").trim();
		String fechaHasta = form.get("fechaHasta").trim();
		String esVenta = form.get("esVenta").trim();
		Double uf = Double.parseDouble(form.get("uf").replaceAll(",", "").trim());
		Double usd = Double.parseDouble(form.get("usd").replaceAll(",", "").trim());
		Double eur = Double.parseDouble(form.get("eur").replaceAll(",", "").trim());
		Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodega").trim());
		Map<Long,Double> tasas = new HashMap<Long,Double>();
		tasas.put((long)1, (double) 1); 	// 'Peso Chileno', 'CLP', '0'
		tasas.put((long)2, usd); 			// 'Dólar', 'USD', '2'
		tasas.put((long)3, eur); 			// 'Euro', 'EUR', '3'
		tasas.put((long)4, uf); 			// 'Unidad Fomento', 'UF', '4'
		Map<String, Double> mapFijaTasas = null;
		List<Long> listIdBodegaEmpresa = null;
		Map<Long, Calc_BodegaEmpresa> mapBodegaEmpresa = null;
		Map<String, Calc_Precio> mapPrecios = null;
		Map<Long,Calc_Precio> mapMaestroPrecios = null;
		Map<Long, Cotizacion> mapCotiAllConfirmadas = null;
		Map<Long,String> mapMoneda = null;
		Map<Long, Equipo> mapAllEquipos = null;
		Map<Long,Long> dec = null;
		BodegaEmpresa bodega = null;
		Proyecto proyecto = null;
		String idTipoUsuario = null;
		Cliente cliente = null;
		Map<String,List<List<String>>> mapReportPorGuia10 = null;
		List<List<String>> detalleAjuste = null;
		List<TipoReferencia> listReferencias = null;
		List<CotizaSolucion> listSol = null;
		String oc = null;
		try (Connection con = dbRead.getConnection()) {
			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
			listIdBodegaEmpresa = ModCalc_InvInicial.listIdBodegaEmpresa(con, s.baseDato, permisoPorBodega);
			mapBodegaEmpresa = Calc_BodegaEmpresa.mapAllBodegasVigentes(con, s.baseDato);
			mapPrecios = Calc_Precio.mapPrecios(con, s.baseDato, listIdBodegaEmpresa);
			mapMaestroPrecios = Calc_Precio.mapMaestroPrecios(con, s.baseDato);
			mapFijaTasas = BodegaEmpresa.mapFijaTasasAll(con, s.baseDato);
			List<Long> listIdGuia_entreFechas = ModCalc_GuiasPer.listIdGuia_entreFecha(con, s.baseDato, fechaDesde, fechaHasta);
			List<Inventarios> guiasPer = Inventarios.guiasPer(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_entreFechas);
			dec = Moneda.numeroDecimal(con, s.baseDato);
			Map<String,String> mapPermanencias = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, s.baseDato);
			mapCotiAllConfirmadas = Cotizacion.mapAllConfirmadasUnaBodega(con, s.baseDato, id_bodegaEmpresa);
			mapMoneda = Moneda.mapIdMonedaMoneda(con, s.baseDato);
			mapAllEquipos = Equipo.mapAllAll(con, s.baseDato);
			bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
			proyecto = Proyecto.find(con,s.baseDato , bodega.getId_proyecto());
			cliente = Cliente.find(con, s.baseDato, bodega.getId_cliente());
			detalleAjuste = AjustesEP.detalleAjuste(con, s.baseDato, id_bodegaEmpresa, fechaDesde, fechaHasta);
			listReferencias = TipoReferencia.all(con, s.baseDato);
			oc = Cotizacion.ocParticiaEnBodega(con, s.baseDato, id_bodegaEmpresa);
			idTipoUsuario = s.id_tipoUsuario;

			Map<Long,List<String>> mapGuias = new HashMap<Long,List<String>>();
			for(Inventarios inv : guiasPer) {
				List<String> aux = new ArrayList<String>();
				aux.add(inv.numeroGuia.toString());
				aux.add(inv.fechaGuia);
				mapGuias.put(inv.id_guia, aux);
			}
			mapReportPorGuia10 = ReportFacturas.mapReportPorGuia10(s.baseDato, id_bodegaEmpresa, fechaDesde, fechaHasta, mapFijaTasas, tasas,
					mapBodegaEmpresa, mapPrecios, mapMaestroPrecios,
					guiasPer, mapPermanencias, dec,  mapCotiAllConfirmadas, mapAllEquipos, mapMoneda);
			Map<String,Long> mapDec = Moneda.numeroDecimalxNombre(con, s.baseDato);
			List<List<String>> datos = new ArrayList<List<String>>();
			for(Map.Entry<String,List<List<String>>> entry : mapReportPorGuia10.entrySet()) {
				for(List<String> list : entry.getValue()) {
					if(list.get(21).equals("1")) {
						Long id_guia = Long.parseLong(list.get(4));
						List<String> guia = mapGuias.get(id_guia);
						Double cant = Double.parseDouble(list.get(13).replaceAll(",", "").trim());
						Double totMonOrigen = Double.parseDouble(list.get(15).replaceAll(",", "").trim());
						String totalMonOrigen = DecimalFormato.formato(cant * totMonOrigen, mapDec.get(list.get(14)));
						if(guia != null) {
							List<String> aux = new ArrayList<String>();
							aux.add(list.get(4)); // 0 id guia
							aux.add(list.get(8)); // 1 nombre de grupo
							aux.add(list.get(9)); // 2 numero cotizacion
							aux.add(list.get(10)); // 3 codigo equipo
							aux.add(list.get(11)); // 4 nombre equipo
							aux.add(list.get(14)); // 5 moneda
							aux.add(list.get(15)); // 6 precio de venta en moneda original
							aux.add(list.get(12)); // 7 unidad
							aux.add(list.get(13)); // 8 cantidad
							aux.add(totalMonOrigen); // 9 total en moneda origen calcular cantidad * precio de venta en moneda original
							aux.add(list.get(20)); // 10 total venta en pesos
							aux.add(guia.get(0)); // 11 numero guia
							aux.add(guia.get(1)); // 12 fecha guia
							datos.add(aux);
						}
					}
				}
			}
			for(List<String> ajustes: detalleAjuste){
				Double valor = Double.parseDouble(ajustes.get(2).replaceAll(",", "").trim());
				if(valor < (double)0 || valor > (double)100){
					List<String> aux = new ArrayList<String>();
					aux.add(""); // 0 id guia
					aux.add(""); // 1 nombre de grupo
					aux.add(""); // 2 numero cotizacion
					aux.add(""); // 3 codigo equipo
					aux.add(ajustes.get(0)); // 4 nombre equipo
					aux.add(""); // 5 moneda
					aux.add(""); // 6 precio de venta en moneda original
					aux.add(""); // 7 unidad
					aux.add(""); // 8 cantidad
					aux.add(""); // 9 total en moneda origen calcular cantidad * precio de venta en moneda original
					aux.add(ajustes.get(2)); // 10 total venta en pesos
					aux.add(""); // 11 numero guia
					aux.add(""); // 12 fecha guia
					datos.add(aux);
				};
			}
			listSol = CotizaSolucion.all(con, s.baseDato);
			Moneda moneda = Moneda.find(con, s.baseDato, (long)1);

			EmisorTributario emisor = EmisorTributario.find(con, s.baseDato);
			Double tasaIva = emisor.getTasaIva()/100;
			if(mapeoPermiso.get("parametro.ivaPorBodega")!=null && mapeoPermiso.get("parametro.ivaPorBodega").equals("1")) {
				if(bodega.getIvaBodega() > 0) {
					tasaIva = bodega.getIvaBodega();
				}else {
					Sucursal sucursal = Sucursal.find(con, s.baseDato, bodega.getId_sucursal().toString());
					if(sucursal.getIvaSucursal() > 0) {
						tasaIva = sucursal.getIvaSucursal();
					}
				}
			}

			File file = ReportFacturas.reportFacturaProyectoDetHVtaExcel(s.baseDato,mapeoDiccionario,
					datos, bodega, esVenta, fechaDesde, fechaHasta, cliente, proyecto, moneda, oc, tasaIva);

			return(file);
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return(null);
		}
	}

	public Result reportFacturaProyectoDetalleHServ(Http.Request request) {
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
				Double uf = Double.parseDouble(form.get("uf").replaceAll(",", "").trim());
				Double usd = Double.parseDouble(form.get("usd").replaceAll(",", "").trim());
				Double eur = Double.parseDouble(form.get("eur").replaceAll(",", "").trim());
				Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodega").trim());
				Map<Long,Double> tasas = new HashMap<Long,Double>();
				tasas.put((long)1, (double) 1); 	// 'Peso Chileno', 'CLP', '0'
				tasas.put((long)2, usd); 			// 'Dólar', 'USD', '2'
				tasas.put((long)3, eur); 			// 'Euro', 'EUR', '3'
				tasas.put((long)4, uf); 			// 'Unidad Fomento', 'UF', '4'
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				List<VentaServicio> listVentaServicio = VentaServicio.allEntreFechasPorBodega(con, s.baseDato, desdeAAMMDD, hastaAAMMDD, id_bodegaEmpresa);
				Map<Long,Long> mapDec = Moneda.numeroDecimal(con, s.baseDato);
				List<List<String>> detalleProformaPorServicio = ReportOdo.detalleProformaPorServicio(con, s.baseDato, listVentaServicio, tasas, mapDec);
				List<String> fechas = new ArrayList<String>();
				fechas.add(desdeAAMMDD);
				fechas.add(hastaAAMMDD);
				fechas.add(Fechas.DDMMAA(desdeAAMMDD));
				fechas.add(Fechas.DDMMAA(hastaAAMMDD));
				fechas.add(Fechas.DDMMAA(hastaAAMMDD));
				List<Double> tasaCambio = new ArrayList<Double>();
				tasaCambio.add(uf);
				tasaCambio.add(usd);
				tasaCambio.add(eur);
				BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
				Proyecto proyecto = Proyecto.find(con,s.baseDato , bodega.getId_proyecto());
				Cliente cliente = Cliente.find(con, s.baseDato, bodega.getId_cliente());
				Long cantDec = (long) Moneda.numeroDecimalxId(con, s.baseDato, "1");
				List<TipoReferencia> listReferencias = TipoReferencia.all(con, s.baseDato);
				List<List<String>> groupPorClaseServicioEquipo = ReportOdo.proformaGroupPorClaseServicioEquipo(detalleProformaPorServicio, mapDec);
				List<List<String>> listaAjustes = Calc_AjustesEpOdo.listaEntreFechasPorBodega(con, s.baseDato, desdeAAMMDD, hastaAAMMDD, id_bodegaEmpresa);
				for(List<String> a: listaAjustes){
					List<String> aux = new ArrayList<String>();
					aux.add("");						//0 familia
					aux.add("");						//1 getNomClaseServicio
					aux.add(a.get(5)+": "+a.get(6));	//2 getCodServicio
					aux.add("");						//3 getNomServicio
					aux.add("");						//4 getFecha
					aux.add("");						//5 getDetalle
					aux.add("");						//6 cantidad
					aux.add("");						//7 total
					aux.add("");				//8 cantidad minima
					aux.add("");			//9 precio total minimo
					aux.add("");		//10 precio total adicional
					aux.add(a.get(4));			//11 precio total final
					aux.add("");					//12 getNroCotiOdo
					groupPorClaseServicioEquipo.add(aux);
				}

				Moneda moneda = Moneda.find(con, s.baseDato, (long)1);
				EmisorTributario emisor = EmisorTributario.find(con, s.baseDato);
				Double tasaIva = emisor.getTasaIva()/100;
				if(mapeoPermiso.get("parametro.ivaPorBodega")!=null && mapeoPermiso.get("parametro.ivaPorBodega").equals("1")) {
					if(bodega.getIvaBodega() > 0) {
						tasaIva = bodega.getIvaBodega();
					}else {
						Sucursal sucursal = Sucursal.find(con, s.baseDato, bodega.getId_sucursal().toString());
						if(sucursal.getIvaSucursal() > 0) {
							tasaIva = sucursal.getIvaSucursal();
						}
					}
				}

				return ok(reportFacturaProyectoDetalleHServ.render(mapeoDiccionario,mapeoPermiso,userMnu,
						fechas, tasaCambio, bodega, proyecto, cliente, cantDec, groupPorClaseServicioEquipo, tasaIva));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reportFacturaProyectoDetalleHServExcel(Http.Request request) {
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
			return ok(mensajes.render("/home/",msgErrorFormulario));
		}else {
			Map<String, String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String, String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
			File file = detalleHServExcel(s,form);
			try (Connection con = dbRead.getConnection()) {
				String auxIdBodega = form.get("id_bodega");
				Long id_bodegaEmpresa = (long) 0;
				if (auxIdBodega != null) {
					id_bodegaEmpresa = Long.parseLong(auxIdBodega.trim());
				}
				BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
				if(file != null){
					return ok(file,false,Optional.of("EP_Proforma_Simple_"+bodega.nombre+".xlsx"));
				}else {
					return ok("");
				}
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reportFacturaProyectoDetalleHServProforma(Http.Request request) {
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
			return ok(mensajes.render("/home/",msgErrorFormulario));
		}else {
			Map<String, String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String, String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
			File file = detalleHServExcel(s,form);
			Long id_bodegaEmpresa = (long) 0;
			BodegaEmpresa bodega = null;
			try (Connection con = dbRead.getConnection()) {
				String auxIdBodega = form.get("id_bodega");
				id_bodegaEmpresa = (long) 0;
				if (auxIdBodega != null) {
					id_bodegaEmpresa = Long.parseLong(auxIdBodega.trim());
				}
				bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
			String tipo = form.get("tipo").trim();
			String fechaDesde = form.get("fechaDesde").trim();
			String fechaHasta = form.get("fechaHasta").trim();
			String neto = form.get("neto").trim();
			String iva = form.get("iva").trim();
			String total = form.get("total").trim();

			Long id = (long)0;
			String sql = "INSERT INTO `"+s.baseDato+"`.proformaSimple (" +
					"tipo, fecha, desde, hasta, id_bodegaEmpresa, neto, iva, total " +
					") VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

			Proyecto proyecto = null;
			ProformaSimple proformaSimple = null;
			Cliente cliente = null;
			String archivoPDF = "0";

			try (Connection con = dbWrite.getConnection();
				 PreparedStatement smt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
				Fechas hoy = Fechas.hoy();
				smt.setString(1, tipo);
				smt.setString(2, hoy.getFechaStrAAMMDD());
				smt.setString(3, fechaDesde);
				smt.setString(4, fechaHasta);
				smt.setLong(5, id_bodegaEmpresa);
				smt.setString(6, neto);
				smt.setString(7, iva);
				smt.setString(8, total);
				smt.executeUpdate();
				ResultSet rs = smt.getGeneratedKeys();
				if (rs.next()) {
					id = rs.getLong(1);
				}
				rs.close();
				if(file != null){
					String nameFile = "EP_Prof_Simple_"+id+".xlsx";
					Archivos.grabarArchivo(file, s.baseDato+"/"+nameFile);
					PreparedStatement smt1 = con.prepareStatement("update `"+s.baseDato+"`.proformaSimple set epExcel='"+nameFile+"' where id=?;");
					smt1.setLong(1, id);
					smt1.executeUpdate();
					smt1.close();
				}

				proyecto = Proyecto.find(con,s.baseDato , bodega.getId_proyecto());
				proformaSimple = ProformaSimple.find(con,s.baseDato , id);
				cliente = Cliente.find(con,s.baseDato , bodega.getId_cliente());

				archivoPDF = FormFactura.generaProformaH(s.baseDato, proyecto, proformaSimple, cliente, bodega, form);
				PreparedStatement smt1 = con.prepareStatement("update `"+s.baseDato+"`.proformaSimple set epPdf='"+archivoPDF+"' where id=?;");
				smt1.setLong(1, id);
				smt1.executeUpdate();
				smt1.close();

			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}

			String titulo = "NOTA DE VENTA SERVICIOS";
			String url = "%2FreportFacturaProyectoHGet%2F0&"+fechaDesde+"&"+fechaHasta+"&"+form.get("uf")+"&"+form.get("usd")+"&"+form.get("eur");
			return redirect("/routes2/redirShowPDF/"+archivoPDF+","+titulo+","+url);
		}
	}

	public File detalleHServExcel(Sessiones s, DynamicForm form) {
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try (Connection con = dbRead.getConnection()){
			String desdeAAMMDD = form.get("fechaDesde").trim();
			String hastaAAMMDD = form.get("fechaHasta").trim();
			Double uf = Double.parseDouble(form.get("uf").replaceAll(",", "").trim());
			Double usd = Double.parseDouble(form.get("usd").replaceAll(",", "").trim());
			Double eur = Double.parseDouble(form.get("eur").replaceAll(",", "").trim());
			Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodega").trim());
			Map<Long,Double> tasas = new HashMap<Long,Double>();
			tasas.put((long)1, (double) 1); 	// 'Peso Chileno', 'CLP', '0'
			tasas.put((long)2, usd); 			// 'Dólar', 'USD', '2'
			tasas.put((long)3, eur); 			// 'Euro', 'EUR', '3'
			tasas.put((long)4, uf); 			// 'Unidad Fomento', 'UF', '4'
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
			List<VentaServicio> listVentaServicio = VentaServicio.allEntreFechasPorBodega(con, s.baseDato, desdeAAMMDD, hastaAAMMDD, id_bodegaEmpresa);
			Map<Long,Long> mapDec = Moneda.numeroDecimal(con, s.baseDato);
			List<List<String>> detalleProformaPorServicio = ReportOdo.detalleProformaPorServicio(con, s.baseDato, listVentaServicio, tasas, mapDec);
			List<String> fechas = new ArrayList<String>();
			fechas.add(desdeAAMMDD);
			fechas.add(hastaAAMMDD);
			fechas.add(Fechas.DDMMAA(desdeAAMMDD));
			fechas.add(Fechas.DDMMAA(hastaAAMMDD));
			fechas.add(Fechas.DDMMAA(hastaAAMMDD));
			List<Double> tasaCambio = new ArrayList<Double>();
			tasaCambio.add(uf);
			tasaCambio.add(usd);
			tasaCambio.add(eur);
			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
			Proyecto proyecto = Proyecto.find(con,s.baseDato , bodega.getId_proyecto());
			Cliente cliente = Cliente.find(con, s.baseDato, bodega.getId_cliente());
			Long cantDec = (long) Moneda.numeroDecimalxId(con, s.baseDato, "1");
			List<TipoReferencia> listReferencias = TipoReferencia.all(con, s.baseDato);
			List<List<String>> groupPorClaseServicioEquipo = ReportOdo.proformaGroupPorClaseServicioEquipo(detalleProformaPorServicio, mapDec);
			List<List<String>> listaAjustes = Calc_AjustesEpOdo.listaEntreFechasPorBodega(con, s.baseDato, desdeAAMMDD, hastaAAMMDD, id_bodegaEmpresa);
			for(List<String> a: listaAjustes){
				List<String> aux = new ArrayList<String>();
				aux.add("");						//0 familia
				aux.add("");						//1 getNomClaseServicio
				aux.add(a.get(5)+": "+a.get(6));	//2 getCodServicio
				aux.add("");						//3 getNomServicio
				aux.add("");						//4 getFecha
				aux.add("");						//5 getDetalle
				aux.add("");						//6 cantidad
				aux.add("");						//7 total
				aux.add("");				//8 cantidad minima
				aux.add("");			//9 precio total minimo
				aux.add("");		//10 precio total adicional
				aux.add(a.get(4));			//11 precio total final
				aux.add("");					//12 getNroCotiOdo
				groupPorClaseServicioEquipo.add(aux);
			}

			Moneda moneda = Moneda.find(con, s.baseDato, (long)1);
			EmisorTributario emisor = EmisorTributario.find(con, s.baseDato);
			Double tasaIva = emisor.getTasaIva()/100;
			if(mapeoPermiso.get("parametro.ivaPorBodega")!=null && mapeoPermiso.get("parametro.ivaPorBodega").equals("1")) {
				if(bodega.getIvaBodega() > 0) {
					tasaIva = bodega.getIvaBodega();
				}else {
					Sucursal sucursal = Sucursal.find(con, s.baseDato, bodega.getId_sucursal().toString());
					if(sucursal.getIvaSucursal() > 0) {
						tasaIva = sucursal.getIvaSucursal();
					}
				}
			}

			File file = ReportFacturas.reportFacturaProyectoDetHServExcel(s.baseDato,mapeoDiccionario,
					groupPorClaseServicioEquipo, bodega, desdeAAMMDD, hastaAAMMDD, cliente, proyecto, tasaIva);
			return(file);
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return(null);
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return(null);
		}
	}

	public Result reportFacturaProyectoDetalleHALL(Http.Request request) {
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
		} else {
			Map<String, String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String, String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
			String auxIdBodega = form.get("id_bodega");
			Long id_bodegaEmpresa = (long) 0;
			if (auxIdBodega != null) {
				id_bodegaEmpresa = Long.parseLong(auxIdBodega.trim());
			}
			String fechaDesde = form.get("fechaDesde").trim();
			String fechaHasta = form.get("fechaHasta").trim();
			String esVenta = form.get("esVenta").trim();
			Double uf = Double.parseDouble(form.get("uf").replaceAll(",", "").trim());
			Double usd = Double.parseDouble(form.get("usd").replaceAll(",", "").trim());
			Double eur = Double.parseDouble(form.get("eur").replaceAll(",", "").trim());
			String concepto = mapeoDiccionario.get("ARRIENDO");
			if ("1".equals(esVenta)) {
				concepto = "VENTA";
			}
			String oc = null;
			String idTipoUsuario = null;
			Cliente cliente = null;
			Proyecto proyecto = null;
			BodegaEmpresa bodega = null;

			List<List<String>> datosSeleccionados = new ArrayList<List<String>>();
			try (Connection con = dbRead.getConnection()) {
				List<List<String>> datos = ReportMovimientos.movimientoGuias(con, s.baseDato, mapeoDiccionario, id_bodegaEmpresa, esVenta, fechaDesde, fechaHasta, usd, eur, uf, concepto);
				bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
				Map<String, Double> mapSubtotales = new HashMap<String,Double>();
				Map<Long,Guia> mapGuias = Guia.mapAll(con, s.baseDato);
				Map<String,Guia> mapGuiasIngreso = ModCalc_GuiasPer.mapUltimaGuiaArrIngreso(con, s.baseDato, mapGuias);
				Map<String,Guia> mapGuiasEgreso = ModCalc_GuiasPer.mapUltimaGuiaArrEgreso(con, s.baseDato, mapGuias);
				for(int i=0; i<datos.size(); i++) {
					List<String> aux = new ArrayList<String>();
					aux.add(datos.get(i).get(0));
					aux.add(datos.get(i).get(1));
					aux.add(datos.get(i).get(2));
					aux.add(datos.get(i).get(3));
					aux.add(datos.get(i).get(6));
					aux.add(datos.get(i).get(7));
					aux.add(datos.get(i).get(9));
					if(i==0 || i==2){
						aux.add("");
					}else{
						aux.add(datos.get(i).get(10));
					}
					if(i==0 || i==1){
						aux.add("");
					}else if(i==4){
						aux.add("Cant Dias");
					}else if(i > 4 && i < datos.size()-3){
						Long cantDia = (long) 0;
						try{
							Double totArr = Double.parseDouble(datos.get(i).get(datos.get(i).size() - 6).replaceAll(",", "").trim());
							Double pArrDia = Double.parseDouble(datos.get(i).get(10).replaceAll(",", "").trim());
							if(totArr > 0) {
								cantDia = Math.round(totArr / pArrDia);
							}
						}catch(Exception e){}
						aux.add(cantDia.toString());
					}else{
						aux.add("");
					}
					aux.add(datos.get(i).get(datos.get(i).size() - 6));
					aux.add(datos.get(i).get(datos.get(i).size() - 5));
					if( ! (i==2 || i==3 || i==datos.size()-2 || i==datos.size()-3) ){
						if(i > 4 && i < datos.size()-3) {
							try {
								Double dblAux = Double.parseDouble(datos.get(i).get(datos.get(i).size() - 5).replaceAll(",", "").trim());
								Double aux1 = mapSubtotales.get(datos.get(i).get(0));
								if (aux1 == null) {
									mapSubtotales.put(datos.get(i).get(0), dblAux);
								} else {
									mapSubtotales.put(datos.get(i).get(0), dblAux + aux1);
								}
							}catch(Exception e){}
						}
						Guia guia = mapGuiasIngreso.get(id_bodegaEmpresa+"_"+datos.get(i).get(2)+"_"+datos.get(i).get(1));
						if(guia != null) {
							aux.add(guia.getNumero().toString());
							aux.add(Fechas.DDMMAA(guia.getFecha()));
						}else{
							if (i == 0) {
								aux.add("Guia");
								aux.add("Guia");
							} else if (i == 1) {
								aux.add("Ultimo");
								aux.add("Fecha");
							} else if (i == 4) {
								aux.add("Ingreso");
								aux.add("Ingreso");
							} else {
								aux.add("");
								aux.add("");
							}
						}
						guia = mapGuiasEgreso.get(id_bodegaEmpresa+"_"+datos.get(i).get(2)+"_"+datos.get(i).get(1));
						if(guia != null) {
							aux.add(guia.getNumero().toString());
							aux.add(Fechas.DDMMAA(guia.getFecha()));
						}else{
							if (i == 0) {
								aux.add("Guia");
								aux.add("Guia");
							} else if (i == 1) {
								aux.add("Ultima");
								aux.add("Fecha");
							} else if (i == 4) {
								aux.add("Salida");
								aux.add("Salida");
							} else {
								aux.add("");
								aux.add("");
							}
						}
						datosSeleccionados.add(aux);
					}
				}
				datosSeleccionados.get(datosSeleccionados.size()-1).set(0,"TOTAL NETO");
				idTipoUsuario = s.id_tipoUsuario;
				cliente = Cliente.find(con, s.baseDato, bodega.getId_cliente());
				proyecto = Proyecto.find(con,s.baseDato , bodega.getId_proyecto());
				oc = Cotizacion.ocParticiaEnBodega(con, s.baseDato, id_bodegaEmpresa);
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}

			// venta:
			if (!s.isValid()) {
				// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
				return ok(mensajes.render("/", msgError));
			}
			Map<Long,Double> tasas = new HashMap<Long,Double>();
			tasas.put((long)1, (double) 1); 	// 'Peso Chileno', 'CLP', '0'
			tasas.put((long)2, usd); 			// 'Dólar', 'USD', '2'
			tasas.put((long)3, eur); 			// 'Euro', 'EUR', '3'
			tasas.put((long)4, uf); 			// 'Unidad Fomento', 'UF', '4'
			Map<String, Double> mapFijaTasas = null;
			List<Long> listIdBodegaEmpresa = null;
			Map<Long, Calc_BodegaEmpresa> mapBodegaEmpresa = null;
			Map<String, Calc_Precio> mapPrecios = null;
			Map<Long,Calc_Precio> mapMaestroPrecios = null;
			Map<Long, Cotizacion> mapCotiAllConfirmadas = null;
			Map<Long,String> mapMoneda = null;
			Map<Long, Equipo> mapAllEquipos = null;
			Map<Long,Long> dec = null;
			Map<String,List<List<String>>> mapReportPorGuia10 = null;
			List<List<String>> detalleAjuste = null;
			Map<Long,List<String>> mapGuiasVta = null;
			Moneda moneda = null;
			Map<String,Long> mapDec = null;
			try (Connection con = dbRead.getConnection()) {
				String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
				listIdBodegaEmpresa = ModCalc_InvInicial.listIdBodegaEmpresa(con, s.baseDato, permisoPorBodega);
				mapBodegaEmpresa = Calc_BodegaEmpresa.mapAllBodegasVigentes(con, s.baseDato);
				mapPrecios = Calc_Precio.mapPrecios(con, s.baseDato, listIdBodegaEmpresa);
				mapMaestroPrecios = Calc_Precio.mapMaestroPrecios(con, s.baseDato);
				mapFijaTasas = BodegaEmpresa.mapFijaTasasAll(con, s.baseDato);
				List<Long> listIdGuia_entreFechas = ModCalc_GuiasPer.listIdGuia_entreFecha(con, s.baseDato, fechaDesde, fechaHasta);
				List<Inventarios> guiasPer = Inventarios.guiasPer(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_entreFechas);
				dec = Moneda.numeroDecimal(con, s.baseDato);
				Map<String,String> mapPermanencias = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, s.baseDato);
				mapCotiAllConfirmadas = Cotizacion.mapAllConfirmadasUnaBodega(con, s.baseDato, id_bodegaEmpresa);
				mapMoneda = Moneda.mapIdMonedaMoneda(con, s.baseDato);
				mapAllEquipos = Equipo.mapAllAll(con, s.baseDato);
				detalleAjuste = AjustesEP.detalleAjuste(con, s.baseDato, id_bodegaEmpresa, fechaDesde, fechaHasta);
				oc = Cotizacion.ocParticiaEnBodega(con, s.baseDato, id_bodegaEmpresa);
				mapGuiasVta = new HashMap<Long,List<String>>();
				for(Inventarios inv : guiasPer) {
					List<String> aux = new ArrayList<String>();
					aux.add(inv.numeroGuia.toString());
					aux.add(inv.fechaGuia);
					mapGuiasVta.put(inv.id_guia, aux);
				}
				mapReportPorGuia10 = ReportFacturas.mapReportPorGuia10(s.baseDato, id_bodegaEmpresa, fechaDesde, fechaHasta, mapFijaTasas, tasas,
						mapBodegaEmpresa, mapPrecios, mapMaestroPrecios,
						guiasPer, mapPermanencias, dec,  mapCotiAllConfirmadas, mapAllEquipos, mapMoneda);
				mapDec = Moneda.numeroDecimalxNombre(con, s.baseDato);
				moneda = Moneda.find(con, s.baseDato, (long)1);
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
			List<List<String>> datos = new ArrayList<List<String>>();
			for(Map.Entry<String,List<List<String>>> entry : mapReportPorGuia10.entrySet()) {
				for(List<String> list : entry.getValue()) {
					if(list.get(21).equals("1")) {
						Long id_guia = Long.parseLong(list.get(4));
						List<String> guia = mapGuiasVta.get(id_guia);
						Double cant = Double.parseDouble(list.get(13).replaceAll(",", "").trim());
						Double totMonOrigen = Double.parseDouble(list.get(15).replaceAll(",", "").trim());
						String totalMonOrigen = DecimalFormato.formato(cant * totMonOrigen, mapDec.get(list.get(14)));
						if(guia != null) {
							List<String> aux = new ArrayList<String>();
							aux.add(list.get(4)); // 0 id guia
							aux.add(list.get(8)); // 1 nombre de grupo
							aux.add(list.get(9)); // 2 numero cotizacion
							aux.add(list.get(10)); // 3 codigo equipo
							aux.add(list.get(11)); // 4 nombre equipo
							aux.add(list.get(14)); // 5 moneda
							aux.add(list.get(15)); // 6 precio de venta en moneda original
							aux.add(list.get(12)); // 7 unidad
							aux.add(list.get(13)); // 8 cantidad
							aux.add(totalMonOrigen); // 9 total en moneda origen calcular cantidad * precio de venta en moneda original
							aux.add(list.get(20)); // 10 total venta en pesos
							aux.add(guia.get(0)); // 11 numero guia
							aux.add(guia.get(1)); // 12 fecha guia
							datos.add(aux);
						}
					}
				}
			}
			for(List<String> ajustes: detalleAjuste){
				Double valor = Double.parseDouble(ajustes.get(2).replaceAll(",", "").trim());
				if(valor < (double)0 || valor > (double)100){
					List<String> aux = new ArrayList<String>();
					aux.add(""); // 0 id guia
					aux.add(""); // 1 nombre de grupo
					aux.add(""); // 2 numero cotizacion
					aux.add(""); // 3 codigo equipo
					aux.add(ajustes.get(0)); // 4 nombre equipo
					aux.add(""); // 5 moneda
					aux.add(""); // 6 precio de venta en moneda original
					aux.add(""); // 7 unidad
					aux.add(""); // 8 cantidad
					aux.add(""); // 9 total en moneda origen calcular cantidad * precio de venta en moneda original
					aux.add(ajustes.get(2)); // 10 total venta en pesos
					aux.add(""); // 11 numero guia
					aux.add(""); // 12 fecha guia
					datos.add(aux);
				};
			}

			// servicio
			if (!s.isValid()) {
				// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
				return ok(mensajes.render("/", msgError));
			}
			List<String> fechas = null;
			List<Double> tasaCambio = new ArrayList<Double>();
			Long cantDec = null;
			List<List<String>> groupPorClaseServicioEquipo = null;
			Double tasaIva = null;
			try (Connection con = dbRead.getConnection()){
				String desdeAAMMDD = form.get("fechaDesde").trim();
				String hastaAAMMDD = form.get("fechaHasta").trim();
				List<VentaServicio> listVentaServicio = VentaServicio.allEntreFechasPorBodega(con, s.baseDato, desdeAAMMDD, hastaAAMMDD, id_bodegaEmpresa);
				Map<Long,Long> mapDecServ = Moneda.numeroDecimal(con, s.baseDato);
				List<List<String>> detalleProformaPorServicio = ReportOdo.detalleProformaPorServicio(con, s.baseDato, listVentaServicio, tasas, mapDecServ);
				fechas = new ArrayList<String>();
				fechas.add(desdeAAMMDD);
				fechas.add(hastaAAMMDD);
				fechas.add(Fechas.DDMMAA(desdeAAMMDD));
				fechas.add(Fechas.DDMMAA(hastaAAMMDD));
				fechas.add(Fechas.DDMMAA(hastaAAMMDD));
				tasaCambio.add(uf);
				tasaCambio.add(usd);
				tasaCambio.add(eur);
				cantDec = (long) Moneda.numeroDecimalxId(con, s.baseDato, "1");
				List<TipoReferencia> listReferencias = TipoReferencia.all(con, s.baseDato);
				groupPorClaseServicioEquipo = ReportOdo.proformaGroupPorClaseServicioEquipo(detalleProformaPorServicio, mapDecServ);
				List<List<String>> listaAjustes = Calc_AjustesEpOdo.listaEntreFechasPorBodega(con, s.baseDato, desdeAAMMDD, hastaAAMMDD, id_bodegaEmpresa);
				for(List<String> a: listaAjustes){
					List<String> aux = new ArrayList<String>();
					aux.add("");						//0 familia
					aux.add("");						//1 getNomClaseServicio
					aux.add(a.get(5)+": "+a.get(6));	//2 getCodServicio
					aux.add("");						//3 getNomServicio
					aux.add("");						//4 getFecha
					aux.add("");						//5 getDetalle
					aux.add("");						//6 cantidad
					aux.add("");						//7 total
					aux.add("");				//8 cantidad minima
					aux.add("");			//9 precio total minimo
					aux.add("");		//10 precio total adicional
					aux.add(a.get(4));			//11 precio total final
					aux.add("");					//12 getNroCotiOdo
					groupPorClaseServicioEquipo.add(aux);
				}

				EmisorTributario emisor = EmisorTributario.find(con, s.baseDato);
				tasaIva = emisor.getTasaIva()/100;
				if(mapeoPermiso.get("parametro.ivaPorBodega")!=null && mapeoPermiso.get("parametro.ivaPorBodega").equals("1")) {
					if(bodega.getIvaBodega() > 0) {
						tasaIva = bodega.getIvaBodega();
					}else {
						Sucursal sucursal = Sucursal.find(con, s.baseDato, bodega.getId_sucursal().toString());
						if(sucursal.getIvaSucursal() > 0) {
							tasaIva = sucursal.getIvaSucursal();
						}
					}
				}

			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}

			return ok(reportFacturaProyectoDetalleHALL.render(mapeoDiccionario,mapeoPermiso,userMnu, idTipoUsuario,
					datosSeleccionados, bodega, esVenta, fechaDesde, fechaHasta, usd, eur, uf, cliente, proyecto, oc,
					datos, moneda.getNumeroDecimales(), moneda.getNickName(),
					cantDec, groupPorClaseServicioEquipo, tasaIva));
		}
	}

	public Result reportFacturaProyectoDetalleHAllExcel(Http.Request request) {
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
			return ok(mensajes.render("/home/",msgErrorFormulario));
		}else {
			Map<String, String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String, String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
			File file = detalleHAllExcel(s,form);
			try (Connection con = dbRead.getConnection()) {
				String auxIdBodega = form.get("id_bodega");
				Long id_bodegaEmpresa = (long) 0;
				if (auxIdBodega != null) {
					id_bodegaEmpresa = Long.parseLong(auxIdBodega.trim());
				}
				BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
				if(file != null){
					return ok(file,false,Optional.of("EP_Proforma_Simple_"+bodega.nombre+".xlsx"));
				}else {
					return ok("");
				}
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reportFacturaProyectoDetalleHAllProforma(Http.Request request) {
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
			return ok(mensajes.render("/home/",msgErrorFormulario));
		}else {
			Map<String, String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String, String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
			File file = detalleHAllExcel(s,form);
			Long id_bodegaEmpresa = (long) 0;
			BodegaEmpresa bodega = null;
			try (Connection con = dbRead.getConnection()) {
				String auxIdBodega = form.get("id_bodega");
				id_bodegaEmpresa = (long) 0;
				if (auxIdBodega != null) {
					id_bodegaEmpresa = Long.parseLong(auxIdBodega.trim());
				}
				bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
			String tipo = form.get("tipo").trim();
			String fechaDesde = form.get("fechaDesde").trim();
			String fechaHasta = form.get("fechaHasta").trim();
			String neto = form.get("neto").trim();
			String iva = form.get("iva").trim();
			String total = form.get("total").trim();

			Long id = (long)0;
			String sql = "INSERT INTO `"+s.baseDato+"`.proformaSimple (" +
					"tipo, fecha, desde, hasta, id_bodegaEmpresa, neto, iva, total " +
					") VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

			Proyecto proyecto = null;
			ProformaSimple proformaSimple = null;
			Cliente cliente = null;
			String archivoPDF = "0";

			try (Connection con = dbWrite.getConnection();
				 PreparedStatement smt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
				Fechas hoy = Fechas.hoy();
				smt.setString(1, tipo);
				smt.setString(2, hoy.getFechaStrAAMMDD());
				smt.setString(3, fechaDesde);
				smt.setString(4, fechaHasta);
				smt.setLong(5, id_bodegaEmpresa);
				smt.setString(6, neto);
				smt.setString(7, iva);
				smt.setString(8, total);
				smt.executeUpdate();
				ResultSet rs = smt.getGeneratedKeys();
				if (rs.next()) {
					id = rs.getLong(1);
				}
				rs.close();
				if(file != null){
					String nameFile = "EP_Prof_Simple_"+id+".xlsx";
					Archivos.grabarArchivo(file, s.baseDato+"/"+nameFile);
					PreparedStatement smt1 = con.prepareStatement("update `"+s.baseDato+"`.proformaSimple set epExcel='"+nameFile+"' where id=?;");
					smt1.setLong(1, id);
					smt1.executeUpdate();
					smt1.close();
				}

				proyecto = Proyecto.find(con,s.baseDato , bodega.getId_proyecto());
				proformaSimple = ProformaSimple.find(con,s.baseDato , id);
				cliente = Cliente.find(con,s.baseDato , bodega.getId_cliente());

				archivoPDF = FormFactura.generaProformaH(s.baseDato, proyecto, proformaSimple, cliente, bodega, form);
				PreparedStatement smt1 = con.prepareStatement("update `"+s.baseDato+"`.proformaSimple set epPdf='"+archivoPDF+"' where id=?;");
				smt1.setLong(1, id);
				smt1.executeUpdate();
				smt1.close();

			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}

			String titulo = "NOTA DE VENTA TODO";
			String url = "%2FreportFacturaProyectoHGet%2F0&"+fechaDesde+"&"+fechaHasta+"&"+form.get("uf")+"&"+form.get("usd")+"&"+form.get("eur");
			return redirect("/routes2/redirShowPDF/"+archivoPDF+","+titulo+","+url);
		}
	}


	public File detalleHAllExcel(Sessiones s, DynamicForm form) {
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		Map<String, String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String, String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		String auxIdBodega = form.get("id_bodega");
		Long id_bodegaEmpresa = (long) 0;
		if (auxIdBodega != null) {
			id_bodegaEmpresa = Long.parseLong(auxIdBodega.trim());
		}
		String fechaDesde = form.get("fechaDesde").trim();
		String fechaHasta = form.get("fechaHasta").trim();
		String esVenta = form.get("esVenta").trim();
		Double uf = Double.parseDouble(form.get("uf").replaceAll(",", "").trim());
		Double usd = Double.parseDouble(form.get("usd").replaceAll(",", "").trim());
		Double eur = Double.parseDouble(form.get("eur").replaceAll(",", "").trim());
		String concepto = mapeoDiccionario.get("ARRIENDO");
		if ("1".equals(esVenta)) {
			concepto = "VENTA";
		}
		String oc = null;
		String idTipoUsuario = null;
		Cliente cliente = null;
		Proyecto proyecto = null;
		BodegaEmpresa bodega = null;

		List<List<String>> datosSeleccionados = new ArrayList<List<String>>();
		try (Connection con = dbRead.getConnection()) {
			List<List<String>> datos = ReportMovimientos.movimientoGuias(con, s.baseDato, mapeoDiccionario, id_bodegaEmpresa, esVenta, fechaDesde, fechaHasta, usd, eur, uf, concepto);
			bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
			Map<String, Double> mapSubtotales = new HashMap<String,Double>();
			Map<Long,Guia> mapGuias = Guia.mapAll(con, s.baseDato);
			Map<String,Guia> mapGuiasIngreso = ModCalc_GuiasPer.mapUltimaGuiaArrIngreso(con, s.baseDato, mapGuias);
			Map<String,Guia> mapGuiasEgreso = ModCalc_GuiasPer.mapUltimaGuiaArrEgreso(con, s.baseDato, mapGuias);
			for(int i=0; i<datos.size(); i++) {
				List<String> aux = new ArrayList<String>();
				aux.add(datos.get(i).get(0));
				aux.add(datos.get(i).get(1));
				aux.add(datos.get(i).get(2));
				aux.add(datos.get(i).get(3));
				aux.add(datos.get(i).get(6));
				aux.add(datos.get(i).get(7));
				aux.add(datos.get(i).get(9));
				if(i==0 || i==2){
					aux.add("");
				}else{
					aux.add(datos.get(i).get(10));
				}
				if(i==0 || i==1){
					aux.add("");
				}else if(i==4){
					aux.add("Cant Dias");
				}else if(i > 4 && i < datos.size()-3){
					Long cantDia = (long) 0;
					try{
						Double totArr = Double.parseDouble(datos.get(i).get(datos.get(i).size() - 6).replaceAll(",", "").trim());
						Double pArrDia = Double.parseDouble(datos.get(i).get(10).replaceAll(",", "").trim());
						if(totArr > 0) {
							cantDia = Math.round(totArr / pArrDia);
						}
					}catch(Exception e){}
					aux.add(cantDia.toString());
				}else{
					aux.add("");
				}
				aux.add(datos.get(i).get(datos.get(i).size() - 6));
				aux.add(datos.get(i).get(datos.get(i).size() - 5));
				if( ! (i==2 || i==3 || i==datos.size()-2 || i==datos.size()-3) ){
					if(i > 4 && i < datos.size()-3) {
						try {
							Double dblAux = Double.parseDouble(datos.get(i).get(datos.get(i).size() - 5).replaceAll(",", "").trim());
							Double aux1 = mapSubtotales.get(datos.get(i).get(0));
							if (aux1 == null) {
								mapSubtotales.put(datos.get(i).get(0), dblAux);
							} else {
								mapSubtotales.put(datos.get(i).get(0), dblAux + aux1);
							}
						}catch(Exception e){}
					}
					Guia guia = mapGuiasIngreso.get(id_bodegaEmpresa+"_"+datos.get(i).get(2)+"_"+datos.get(i).get(1));
					if(guia != null) {
						aux.add(guia.getNumero().toString());
						aux.add(Fechas.DDMMAA(guia.getFecha()));
					}else{
						if (i == 0) {
							aux.add("Guia");
							aux.add("Guia");
						} else if (i == 1) {
							aux.add("Ultimo");
							aux.add("Fecha");
						} else if (i == 4) {
							aux.add("Ingreso");
							aux.add("Ingreso");
						} else {
							aux.add("");
							aux.add("");
						}
					}
					guia = mapGuiasEgreso.get(id_bodegaEmpresa+"_"+datos.get(i).get(2)+"_"+datos.get(i).get(1));
					if(guia != null) {
						aux.add(guia.getNumero().toString());
						aux.add(Fechas.DDMMAA(guia.getFecha()));
					}else{
						if (i == 0) {
							aux.add("Guia");
							aux.add("Guia");
						} else if (i == 1) {
							aux.add("Ultima");
							aux.add("Fecha");
						} else if (i == 4) {
							aux.add("Salida");
							aux.add("Salida");
						} else {
							aux.add("");
							aux.add("");
						}
					}
					datosSeleccionados.add(aux);
				}
			}
			datosSeleccionados.get(datosSeleccionados.size()-1).set(0,"TOTAL NETO");
			idTipoUsuario = s.id_tipoUsuario;
			cliente = Cliente.find(con, s.baseDato, bodega.getId_cliente());
			proyecto = Proyecto.find(con,s.baseDato , bodega.getId_proyecto());
			oc = Cotizacion.ocParticiaEnBodega(con, s.baseDato, id_bodegaEmpresa);
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return null;
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return null;
		}

		Map<Long,Double> tasas = new HashMap<Long,Double>();
		tasas.put((long)1, (double) 1); 	// 'Peso Chileno', 'CLP', '0'
		tasas.put((long)2, usd); 			// 'Dólar', 'USD', '2'
		tasas.put((long)3, eur); 			// 'Euro', 'EUR', '3'
		tasas.put((long)4, uf); 			// 'Unidad Fomento', 'UF', '4'
		Map<String, Double> mapFijaTasas = null;
		List<Long> listIdBodegaEmpresa = null;
		Map<Long, Calc_BodegaEmpresa> mapBodegaEmpresa = null;
		Map<String, Calc_Precio> mapPrecios = null;
		Map<Long,Calc_Precio> mapMaestroPrecios = null;
		Map<Long, Cotizacion> mapCotiAllConfirmadas = null;
		Map<Long,String> mapMoneda = null;
		Map<Long, Equipo> mapAllEquipos = null;
		Map<Long,Long> dec = null;
		Map<String,List<List<String>>> mapReportPorGuia10 = null;
		List<List<String>> detalleAjuste = null;
		Map<Long,List<String>> mapGuiasVta = null;
		Moneda moneda = null;
		Map<String,Long> mapDec = null;
		try (Connection con = dbRead.getConnection()) {
			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
			listIdBodegaEmpresa = ModCalc_InvInicial.listIdBodegaEmpresa(con, s.baseDato, permisoPorBodega);
			mapBodegaEmpresa = Calc_BodegaEmpresa.mapAllBodegasVigentes(con, s.baseDato);
			mapPrecios = Calc_Precio.mapPrecios(con, s.baseDato, listIdBodegaEmpresa);
			mapMaestroPrecios = Calc_Precio.mapMaestroPrecios(con, s.baseDato);
			mapFijaTasas = BodegaEmpresa.mapFijaTasasAll(con, s.baseDato);
			List<Long> listIdGuia_entreFechas = ModCalc_GuiasPer.listIdGuia_entreFecha(con, s.baseDato, fechaDesde, fechaHasta);
			List<Inventarios> guiasPer = Inventarios.guiasPer(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_entreFechas);
			dec = Moneda.numeroDecimal(con, s.baseDato);
			Map<String,String> mapPermanencias = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, s.baseDato);
			mapCotiAllConfirmadas = Cotizacion.mapAllConfirmadasUnaBodega(con, s.baseDato, id_bodegaEmpresa);
			mapMoneda = Moneda.mapIdMonedaMoneda(con, s.baseDato);
			mapAllEquipos = Equipo.mapAllAll(con, s.baseDato);
			detalleAjuste = AjustesEP.detalleAjuste(con, s.baseDato, id_bodegaEmpresa, fechaDesde, fechaHasta);
			oc = Cotizacion.ocParticiaEnBodega(con, s.baseDato, id_bodegaEmpresa);
			mapGuiasVta = new HashMap<Long,List<String>>();
			for(Inventarios inv : guiasPer) {
				List<String> aux = new ArrayList<String>();
				aux.add(inv.numeroGuia.toString());
				aux.add(inv.fechaGuia);
				mapGuiasVta.put(inv.id_guia, aux);
			}
			mapReportPorGuia10 = ReportFacturas.mapReportPorGuia10(s.baseDato, id_bodegaEmpresa, fechaDesde, fechaHasta, mapFijaTasas, tasas,
					mapBodegaEmpresa, mapPrecios, mapMaestroPrecios,
					guiasPer, mapPermanencias, dec,  mapCotiAllConfirmadas, mapAllEquipos, mapMoneda);
			mapDec = Moneda.numeroDecimalxNombre(con, s.baseDato);
			moneda = Moneda.find(con, s.baseDato, (long)1);
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return null;
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return null;
		}
		List<List<String>> datos = new ArrayList<List<String>>();
		for(Map.Entry<String,List<List<String>>> entry : mapReportPorGuia10.entrySet()) {
			for(List<String> list : entry.getValue()) {
				if(list.get(21).equals("1")) {
					Long id_guia = Long.parseLong(list.get(4));
					List<String> guia = mapGuiasVta.get(id_guia);
					Double cant = Double.parseDouble(list.get(13).replaceAll(",", "").trim());
					Double totMonOrigen = Double.parseDouble(list.get(15).replaceAll(",", "").trim());
					String totalMonOrigen = DecimalFormato.formato(cant * totMonOrigen, mapDec.get(list.get(14)));
					if(guia != null) {
						List<String> aux = new ArrayList<String>();
						aux.add(list.get(4)); // 0 id guia
						aux.add(list.get(8)); // 1 nombre de grupo
						aux.add(list.get(9)); // 2 numero cotizacion
						aux.add(list.get(10)); // 3 codigo equipo
						aux.add(list.get(11)); // 4 nombre equipo
						aux.add(list.get(14)); // 5 moneda
						aux.add(list.get(15)); // 6 precio de venta en moneda original
						aux.add(list.get(12)); // 7 unidad
						aux.add(list.get(13)); // 8 cantidad
						aux.add(totalMonOrigen); // 9 total en moneda origen calcular cantidad * precio de venta en moneda original
						aux.add(list.get(20)); // 10 total venta en pesos
						aux.add(guia.get(0)); // 11 numero guia
						aux.add(guia.get(1)); // 12 fecha guia
						datos.add(aux);
					}
				}
			}
		}
		for(List<String> ajustes: detalleAjuste){
			Double valor = Double.parseDouble(ajustes.get(2).replaceAll(",", "").trim());
			if(valor < (double)0 || valor > (double)100){
				List<String> aux = new ArrayList<String>();
				aux.add(""); // 0 id guia
				aux.add(""); // 1 nombre de grupo
				aux.add(""); // 2 numero cotizacion
				aux.add(""); // 3 codigo equipo
				aux.add(ajustes.get(0)); // 4 nombre equipo
				aux.add(""); // 5 moneda
				aux.add(""); // 6 precio de venta en moneda original
				aux.add(""); // 7 unidad
				aux.add(""); // 8 cantidad
				aux.add(""); // 9 total en moneda origen calcular cantidad * precio de venta en moneda original
				aux.add(ajustes.get(2)); // 10 total venta en pesos
				aux.add(""); // 11 numero guia
				aux.add(""); // 12 fecha guia
				datos.add(aux);
			};
		}

		// servicio
		List<String> fechas = null;
		List<Double> tasaCambio = new ArrayList<Double>();
		Long cantDec = null;
		List<List<String>> groupPorClaseServicioEquipo = null;
		Double tasaIva = null;
		try (Connection con = dbRead.getConnection()){
			String desdeAAMMDD = form.get("fechaDesde").trim();
			String hastaAAMMDD = form.get("fechaHasta").trim();
			List<VentaServicio> listVentaServicio = VentaServicio.allEntreFechasPorBodega(con, s.baseDato, desdeAAMMDD, hastaAAMMDD, id_bodegaEmpresa);
			Map<Long,Long> mapDecServ = Moneda.numeroDecimal(con, s.baseDato);
			List<List<String>> detalleProformaPorServicio = ReportOdo.detalleProformaPorServicio(con, s.baseDato, listVentaServicio, tasas, mapDecServ);
			fechas = new ArrayList<String>();
			fechas.add(desdeAAMMDD);
			fechas.add(hastaAAMMDD);
			fechas.add(Fechas.DDMMAA(desdeAAMMDD));
			fechas.add(Fechas.DDMMAA(hastaAAMMDD));
			fechas.add(Fechas.DDMMAA(hastaAAMMDD));
			tasaCambio.add(uf);
			tasaCambio.add(usd);
			tasaCambio.add(eur);
			cantDec = (long) Moneda.numeroDecimalxId(con, s.baseDato, "1");
			List<TipoReferencia> listReferencias = TipoReferencia.all(con, s.baseDato);
			groupPorClaseServicioEquipo = ReportOdo.proformaGroupPorClaseServicioEquipo(detalleProformaPorServicio, mapDecServ);
			List<List<String>> listaAjustes = Calc_AjustesEpOdo.listaEntreFechasPorBodega(con, s.baseDato, desdeAAMMDD, hastaAAMMDD, id_bodegaEmpresa);
			for(List<String> a: listaAjustes){
				List<String> aux = new ArrayList<String>();
				aux.add("");						//0 familia
				aux.add("");						//1 getNomClaseServicio
				aux.add(a.get(5)+": "+a.get(6));	//2 getCodServicio
				aux.add("");						//3 getNomServicio
				aux.add("");						//4 getFecha
				aux.add("");						//5 getDetalle
				aux.add("");						//6 cantidad
				aux.add("");						//7 total
				aux.add("");				//8 cantidad minima
				aux.add("");			//9 precio total minimo
				aux.add("");		//10 precio total adicional
				aux.add(a.get(4));			//11 precio total final
				aux.add("");					//12 getNroCotiOdo
				groupPorClaseServicioEquipo.add(aux);
			}

			EmisorTributario emisor = EmisorTributario.find(con, s.baseDato);
			tasaIva = emisor.getTasaIva()/100;
			if(mapeoPermiso.get("parametro.ivaPorBodega")!=null && mapeoPermiso.get("parametro.ivaPorBodega").equals("1")) {
				if(bodega.getIvaBodega() > 0) {
					tasaIva = bodega.getIvaBodega();
				}else {
					Sucursal sucursal = Sucursal.find(con, s.baseDato, bodega.getId_sucursal().toString());
					if(sucursal.getIvaSucursal() > 0) {
						tasaIva = sucursal.getIvaSucursal();
					}
				}
			}
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return null;
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return null;
		}

		File file = ReportFacturas.reportFacturaProyectoDetHAllExcel(s.baseDato,mapeoDiccionario,
				datosSeleccionados, bodega, esVenta, fechaDesde, fechaHasta, usd, eur, uf, cliente, proyecto, oc,
				datos, moneda.getNumeroDecimales(), moneda.getNickName(),
				cantDec, groupPorClaseServicioEquipo, tasaIva);
		return(file);
	}




	//====================================================================================
	// MNU proformaResumen   Reportes/Proforma/Resumen y Detalle (por mes)
	//====================================================================================

	public Result reportFacturaPeriodoResumen0(Http.Request request) {
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
		if(mapeoPermiso.get("proformaResumen")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()) {
			Fechas hoy = Fechas.hoy();
			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			TasasCambio tasas = TasasCambio.allDeUnaFecha(con, s.baseDato, mapeoDiccionario.get("pais"),hasta);
			return ok(reportFacturaPeriodoResumen0.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta, tasas));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result reportFacturaPeriodoResumen(Http.Request request) {
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
		if(mapeoPermiso.get("proformaResumen")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()) {
			Fechas hoy = Fechas.hoy();
			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			TasasCambio tasas = TasasCambio.allDeUnaFecha(con, s.baseDato, mapeoDiccionario.get("pais"),hasta);
			return ok(reportFacturaPeriodoResumen.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta, tasas));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result reportFacturaResumen0(Http.Request request) {
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
			return ok(mensajes.render("/home/",msgErrorFormulario));
		}else {
			try {
				String desdeAAMMDD = form.get("fechaDesde").trim();
				String hastaAAMMDD = form.get("fechaHasta").trim();
				Double uf = Double.parseDouble(form.get("uf").replaceAll(",", "").trim());
				Double usd = Double.parseDouble(form.get("usd").replaceAll(",", "").trim());
				Double eur = Double.parseDouble(form.get("eur").replaceAll(",", "").trim());
				Fechas desde = Fechas.obtenerFechaDesdeStrAAMMDD(desdeAAMMDD);
				Fechas hasta = Fechas.obtenerFechaDesdeStrAAMMDD(hastaAAMMDD);
				Map<Long, Double> tasas = new HashMap<Long, Double>();
				tasas.put((long) 1, (double) 1);    // 'Peso Chileno', 'CLP', '0'
				tasas.put((long) 2, usd);            // 'Dólar', 'USD', '2'
				tasas.put((long) 3, eur);            // 'Euro', 'EUR', '3'
				tasas.put((long) 4, uf);            // 'Unidad Fomento', 'UF', '4'
				Map<String, String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String, String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				return ok(reportFacturaResumen0.render(mapeoDiccionario, mapeoPermiso, userMnu,
						desde, hasta, uf, usd, eur));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reportFacturaResumen(Http.Request request) {
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
			return ok(mensajes.render("/home/",msgErrorFormulario));
		}else {
			try {
				String desdeAAMMDD = form.get("fechaDesde").trim();
				String hastaAAMMDD = form.get("fechaHasta").trim();
				Double uf = Double.parseDouble(form.get("uf").replaceAll(",", "").trim());
				Double usd = Double.parseDouble(form.get("usd").replaceAll(",", "").trim());
				Double eur = Double.parseDouble(form.get("eur").replaceAll(",", "").trim());
				Fechas desde = Fechas.obtenerFechaDesdeStrAAMMDD(desdeAAMMDD);
				Fechas hasta = Fechas.obtenerFechaDesdeStrAAMMDD(hastaAAMMDD);
				Map<Long, Double> tasas = new HashMap<Long, Double>();
				tasas.put((long) 1, (double) 1);    // 'Peso Chileno', 'CLP', '0'
				tasas.put((long) 2, usd);            // 'Dólar', 'USD', '2'
				tasas.put((long) 3, eur);            // 'Euro', 'EUR', '3'
				tasas.put((long) 4, uf);            // 'Unidad Fomento', 'UF', '4'
				Map<String, String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String, String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				try (Connection con = dbRead.getConnection()) {
					Map<Long, Long> dec = Moneda.numeroDecimal(con, s.baseDato);
					Long nroDec = dec.get((long) 1);
					return ok(reportFacturaResumen.render(mapeoDiccionario, mapeoPermiso, userMnu,
							desde, hasta, uf, usd, eur, nroDec));
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				}
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reportFacturaResumenJson(Http.Request request) {
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
			return ok(mensajes.render("/home/",msgErrorFormulario));
		}else {
			try {
				String desdeAAMMDD = form.get("fechaDesde").trim();
				String hastaAAMMDD = form.get("fechaHasta").trim();
				Double uf = Double.parseDouble(form.get("uf").replaceAll(",", "").trim());
				Double usd = Double.parseDouble(form.get("usd").replaceAll(",", "").trim());
				Double eur = Double.parseDouble(form.get("eur").replaceAll(",", "").trim());
				Map<Long,Double> tasas = new HashMap<Long,Double>();
				tasas.put((long)1, (double) 1); 	// 'Peso Chileno', 'CLP', '0'
				tasas.put((long)2, usd); 			// 'Dólar', 'USD', '2'
				tasas.put((long)3, eur); 			// 'Euro', 'EUR', '3'
				tasas.put((long)4, uf); 			// 'Unidad Fomento', 'UF', '4'
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				List<Long> listIdBodegaEmpresa = null;
				Map<Long,Calc_BodegaEmpresa> mapBodegaEmpresa = null;
				Map<String,Calc_Precio> mapPrecios = null;
				Map<Long,Calc_Precio> mapMaestroPrecios = null;
				Map<String, Double> mapFijaTasas = null;
				List<Long> listIdGuia_fechaCorte = null;
				List<Inventarios> inventario = null;
				List<Inventarios> guiasPer = null;
				List<Calc_AjustesEP> listaAjustes = null;
				Map<Long,List<String>> mapBodega = null;
				Map<Long,Long> dec = null;
				Map<String,String> map = null;
				Map<String,String> mapPermanencias = null;
				try (Connection con = dbRead.getConnection()) {
					String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
					listIdBodegaEmpresa = ModCalc_InvInicial.listIdBodegaEmpresa(con, s.baseDato, permisoPorBodega);
					mapBodegaEmpresa = Calc_BodegaEmpresa.mapAllBodegasVigentes(con, s.baseDato);
					mapPrecios = Calc_Precio.mapPrecios(con, s.baseDato, listIdBodegaEmpresa);
					mapMaestroPrecios = Calc_Precio.mapMaestroPrecios(con, s.baseDato);
					mapFijaTasas = BodegaEmpresa.mapFijaTasasAll(con, s.baseDato);
					listIdGuia_fechaCorte = ModCalc_InvInicial.listIdGuia_fechaCorte(con, s.baseDato, desdeAAMMDD);
					inventario = Inventarios.inventario(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_fechaCorte);
					List<Long> listIdGuia_entreFechas = ModCalc_GuiasPer.listIdGuia_entreFecha(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
					guiasPer = Inventarios.guiasPer(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_entreFechas);
					listIdGuia_entreFechas = null;
					listaAjustes = Calc_AjustesEP.listaAjustesEntreFechas(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
					mapBodega = BodegaEmpresa.mapIdBod_BodegaEmpresaInternasExternas(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
					dec = Moneda.numeroDecimal(con, s.baseDato);
					map = UsuarioPermiso.mapPermisoIdBodega(con, s.baseDato, Long.parseLong(s.id_usuario));
					mapPermanencias = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, s.baseDato);
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				}
				ReportFacturas reporte = ModCalc_InvInicial.resumenInvInicial(s.baseDato,desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa,
						mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, listIdGuia_fechaCorte, inventario);
				List<ModCalc_InvInicial> inventarioInicial = reporte.resumenInvInicial;
				List<ModCalc_GuiasPer> guiasPeriodo = ModCalc_GuiasPer.resumenGuiasPer(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas,
						mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, guiasPer, mapPermanencias);
				mapBodegaEmpresa = null;
				mapPrecios = null;
				mapMaestroPrecios = null;
				guiasPer = null;
				mapPermanencias = null;
				List<ModeloCalculo> valorTotalPorBodega = ModeloCalculo.valorTotalporBodega(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, inventarioInicial,guiasPeriodo, listaAjustes);
				List<List<String>> proyectosAux = ReportFacturas.reportFacturaProyecto(valorTotalPorBodega, mapBodega);
				mapBodega = null;
				List<List<String>> resumenTotales = ReportFacturas.resumenTotalesPorProyecto(valorTotalPorBodega, dec);
				valorTotalPorBodega = null;
				List<ModeloCalculo> valorTotalporBodegaYGrupo = ModeloCalculo.valorTotalporBodegaYGrupo(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, inventarioInicial,guiasPeriodo);
				List<List<String>> proyectos = new ArrayList<List<String>>();
				if(!map.isEmpty()) {
					for(List<String> aux: proyectosAux) {
						String idBodega = map.get(aux.get(1));
						if(idBodega!=null) {
							proyectos.add(aux);
						}
					}
				}else {
                    proyectos.addAll(proyectosAux);
				}
				proyectosAux = null;
				List<List<String>> resumenPorGrupoYProyecto = new ArrayList<List<String>>();
				if(!mapeoDiccionario.get("nEmpresa").equals("SM8 DE MEXICO")) {
					try (Connection con2 = dbRead.getConnection()) {
						Map<String, List<List<String>>> mapResumenPorGrupo = ReportFacturas.mapResumenPorGrupo2(con2, s.baseDato, valorTotalporBodegaYGrupo); 		// RESUMEN POR GRUPO
						resumenPorGrupoYProyecto = ReportFacturas.resumenPorGrupoYProyecto(con2, s.baseDato, proyectos, mapResumenPorGrupo); 		// RESUMEN POR GRUPO
					} catch (SQLException e) {
						logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
						return ok(mensajes.render("/home/", msgReport));
					} catch (Exception e) {
						logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
						return ok(mensajes.render("/home/", msgReport));
					}
				}
				String resumenPorPJson = Json.toJson(proyectos).toString();
				String resumenPorPyGJson = Json.toJson(resumenPorGrupoYProyecto).toString();
				String resumenTotalesJson = Json.toJson(resumenTotales).toString();
				String json = "{\"resumenPorPJson\":"+resumenPorPJson+",\"resumenPorPyGJson\":"+resumenPorPyGJson
						+",\"resumenTotalesJson\":"+resumenTotalesJson+"}";
				return ok(json).as("application/json");
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reportFacturaResumenExcel0(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/",msgErrorFormulario));
		}else {
			try {
				String desdeAAMMDD = form.get("fechaDesde").trim();
				String hastaAAMMDD = form.get("fechaHasta").trim();
				Double uf = Double.parseDouble(form.get("uf").replaceAll(",", "").trim());
				Double usd = Double.parseDouble(form.get("usd").replaceAll(",", "").trim());
				Double eur = Double.parseDouble(form.get("eur").replaceAll(",", "").trim());
				if(mapeoPermiso.get("parametro.excel_porEMail")!=null && mapeoPermiso.get("parametro.excel_porEMail").equals("1")) {
					String mailDestino = "";
					try (Connection con = dbRead.getConnection()) {
						Usuario usuario = Usuario.findXIdUser(con, s.baseDato, Long.parseLong(s.id_usuario));
						if( usuario != null) {
							mailDestino = usuario.getEmail().trim().toLowerCase();
							if(mailDestino.length() < 4) {
								usuario = null;
							}
						}
					} catch (SQLException e) {
						logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
						return ok(mensajes.render("/home/", msgReport));
					} catch (Exception e) {
						logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
						return ok(mensajes.render("/home/", msgReport));
					}
					if(HomeController.isValidEmail(mailDestino)) {
						MnuReportes.reportFacturaResumenExcel0mail generar = new MnuReportes.reportFacturaResumenExcel0mail(s.baseDato, desdeAAMMDD, hastaAAMMDD,
								uf, usd, eur, s.id_usuario, s.aplicaPorSucursal, s.id_sucursal, mailDestino);
						generar.start();
					}
					String mensaje = "Solicitud en preparación, recibira el resultado al correo:"+mailDestino+". Tomara algunos minutos para recibir el correo";
					return ok(mensajes.render("/home/",mensaje));
				}
				Fechas desde = Fechas.obtenerFechaDesdeStrAAMMDD(desdeAAMMDD);
				Fechas hasta = Fechas.obtenerFechaDesdeStrAAMMDD(hastaAAMMDD);
				Map<Long,Double> tasas = new HashMap<Long,Double>();
				tasas.put((long)1, (double) 1); 	// 'Peso Chileno', 'CLP', '0'
				tasas.put((long)2, usd); 			// 'Dólar', 'USD', '2'
				tasas.put((long)3, eur); 			// 'Euro', 'EUR', '3'
				tasas.put((long)4, uf); 			// 'Unidad Fomento', 'UF', '4'
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				List<Long> listIdBodegaEmpresa = null;
				Map<Long,Calc_BodegaEmpresa> mapBodegaEmpresa = null;
				Map<String,Calc_Precio> mapPrecios = null;
				Map<Long,Calc_Precio> mapMaestroPrecios = null;
				Map<String, Double> mapFijaTasas = null;
				List<Long> listIdGuia_fechaCorte = null;
				List<Inventarios> inventario = null;
				List<Inventarios> guiasPer = null;
				List<Calc_AjustesEP> listaAjustes = null;
				Map<Long,List<String>> mapBodega = null;
				Map<Long,Long> dec = null;
				Map<String,String> map = null;
				Map<String,String> mapPermanencias = null;
				try (Connection con = dbRead.getConnection()) {
					String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
					listIdBodegaEmpresa = ModCalc_InvInicial.listIdBodegaEmpresa(con, s.baseDato, permisoPorBodega);
					mapBodegaEmpresa = Calc_BodegaEmpresa.mapAllBodegasVigentes(con, s.baseDato);
					mapPrecios = Calc_Precio.mapPrecios(con, s.baseDato, listIdBodegaEmpresa);
					mapMaestroPrecios = Calc_Precio.mapMaestroPrecios(con, s.baseDato);
					mapFijaTasas = BodegaEmpresa.mapFijaTasasAll(con, s.baseDato);
					listIdGuia_fechaCorte = ModCalc_InvInicial.listIdGuia_fechaCorte(con, s.baseDato, desdeAAMMDD);
					inventario = Inventarios.inventario(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_fechaCorte);
					List<Long> listIdGuia_entreFechas = ModCalc_GuiasPer.listIdGuia_entreFecha(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
					guiasPer = Inventarios.guiasPer(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_entreFechas);
					listIdGuia_entreFechas = null;
					listaAjustes = Calc_AjustesEP.listaAjustesEntreFechas(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
					mapBodega = BodegaEmpresa.mapIdBod_BodegaEmpresaInternasExternas(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
					dec = Moneda.numeroDecimal(con, s.baseDato);
					map = UsuarioPermiso.mapPermisoIdBodega(con, s.baseDato, Long.parseLong(s.id_usuario));
					mapPermanencias = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, s.baseDato);
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				}
				ReportFacturas reporte = ModCalc_InvInicial.resumenInvInicial(s.baseDato,desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa,
							mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, listIdGuia_fechaCorte, inventario);
				listIdBodegaEmpresa = null;
				listIdGuia_fechaCorte = null;
				inventario = null;
				List<ModCalc_InvInicial> inventarioInicial = reporte.resumenInvInicial;
				reporte = null;
				List<ModCalc_GuiasPer> guiasPeriodo = ModCalc_GuiasPer.resumenGuiasPer(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas,
							mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, guiasPer, mapPermanencias);
				mapBodegaEmpresa = null;
				mapPrecios = null;
				mapMaestroPrecios = null;
				guiasPer = null;
				mapPermanencias = null;
				List<ModeloCalculo> valorTotalPorBodega = ModeloCalculo.valorTotalporBodega(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, inventarioInicial,guiasPeriodo, listaAjustes);
				listaAjustes = null;
				List<List<String>> proyectosAux = ReportFacturas.reportFacturaProyecto(valorTotalPorBodega, mapBodega);
				mapBodega = null;
				List<List<String>> resumenTotales = ReportFacturas.resumenTotalesPorProyecto(valorTotalPorBodega, dec);
				valorTotalPorBodega = null;
				List<ModeloCalculo> valorTotalporBodegaYGrupo = ModeloCalculo.valorTotalporBodegaYGrupo(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, inventarioInicial,guiasPeriodo);
				List<List<String>> proyectos = new ArrayList<List<String>>();
				if(!map.isEmpty()) {
					for(List<String> aux: proyectosAux) {
						String idBodega = map.get(aux.get(1));
						if(idBodega!=null) {
							proyectos.add(aux);
						}
					}
				}else {
                    proyectos.addAll(proyectosAux);
				}
				proyectosAux = null;
				try (Connection con2 = dbRead.getConnection()) {
					Map<String, List<List<String>>> mapResumenPorGrupo = ReportFacturas.mapResumenPorGrupo2(con2, s.baseDato, valorTotalporBodegaYGrupo); 		// RESUMEN POR GRUPO
					List<List<String>> resumenPorGrupoYProyecto = ReportFacturas.resumenPorGrupoYProyecto(con2, s.baseDato, proyectos, mapResumenPorGrupo); 		// RESUMEN POR GRUPO
					mapResumenPorGrupo = null;
					Map<String, List<List<String>>> mapInicioPer = ReportFacturas.mapInicioPerAllBodegas(con2, s.baseDato, inventarioInicial);  					// RESUMEN POR ESTADOS DE PAGO
					Map<String, List<List<String>>> mapGuiasPer = ReportFacturas.mapGuiasPer(con2, s.baseDato, guiasPeriodo);  									// RESUMEN POR ESTADOS DE PAGO
					List<List<String>> resumenPorProyectoGrupoYdetalle =
							ReportFacturas.resumenEstadosDePago(con2, s.baseDato, proyectos, desdeAAMMDD, hastaAAMMDD,mapeoDiccionario.get("nEmpresa"), mapInicioPer, mapGuiasPer);   // RESUMEN POR ESTADOS DE PAGO
					mapInicioPer = null;
					mapGuiasPer = null;
					File file = ReportFacturas.exportaProformaExcelResumen0(s.baseDato, mapeoDiccionario,
							proyectos,desde,hasta,uf,usd,eur,resumenTotales,resumenPorGrupoYProyecto,resumenPorProyectoGrupoYdetalle);
					return ok(file,false,Optional.of("EP_Proforma_Resumen.xlsx"));
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				}
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public class reportFacturaResumenExcel0mail extends Thread {
		String baseDato;
		String desdeAAMMDD;
		String hastaAAMMDD;
		Double uf;
		Double usd;
		Double eur;
		String id_usuario;
		String aplicaPorSucursal;
		String id_sucursal;
		String eMail;

		public reportFacturaResumenExcel0mail(String baseDato, String desdeAAMMDD, String hastaAAMMDD, Double uf, Double usd, Double eur, String id_usuario,
											  String aplicaPorSucursal, String id_sucursal, String eMail) {
			super();
			this.baseDato = baseDato;
			this.desdeAAMMDD = desdeAAMMDD;
			this.hastaAAMMDD = hastaAAMMDD;
			this.uf = uf;
			this.usd = usd;
			this.eur = eur;
			this.id_usuario = id_usuario;
			this.aplicaPorSucursal = aplicaPorSucursal;
			this.id_sucursal = id_sucursal;
			this.eMail = eMail;
		}

		public void run() {
			String className = this.getClass().getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			try {
				Fechas desde = Fechas.obtenerFechaDesdeStrAAMMDD(desdeAAMMDD);
				Fechas hasta = Fechas.obtenerFechaDesdeStrAAMMDD(hastaAAMMDD);
				Map<Long,Double> tasas = new HashMap<Long,Double>();
				tasas.put((long)1, (double) 1); 	// 'Peso Chileno', 'CLP', '0'
				tasas.put((long)2, usd); 			// 'Dólar', 'USD', '2'
				tasas.put((long)3, eur); 			// 'Euro', 'EUR', '3'
				tasas.put((long)4, uf); 			// 'Unidad Fomento', 'UF', '4'
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(baseDato);
				List<Long> listIdBodegaEmpresa = null;
				Map<Long,Calc_BodegaEmpresa> mapBodegaEmpresa = null;
				Map<String,Calc_Precio> mapPrecios = null;
				Map<Long,Calc_Precio> mapMaestroPrecios = null;
				Map<String, Double> mapFijaTasas = null;
				List<Long> listIdGuia_fechaCorte = null;
				List<Inventarios> inventario = null;
				List<Inventarios> guiasPer = null;
				List<Calc_AjustesEP> listaAjustes = null;
				Map<Long,List<String>> mapBodega = null;
				Map<Long,Long> dec = null;
				Map<String,String> map = null;
				Map<String,String> mapPermanencias = null;
				try (Connection con = dbRead.getConnection()){
					String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, baseDato, Long.parseLong(id_usuario));
					listIdBodegaEmpresa = ModCalc_InvInicial.listIdBodegaEmpresa(con, baseDato, permisoPorBodega);
					mapBodegaEmpresa = Calc_BodegaEmpresa.mapAllBodegasVigentes(con, baseDato);
					mapPrecios = Calc_Precio.mapPrecios(con, baseDato, listIdBodegaEmpresa);
					mapMaestroPrecios = Calc_Precio.mapMaestroPrecios(con, baseDato);
					mapFijaTasas = BodegaEmpresa.mapFijaTasasAll(con, baseDato);
					listIdGuia_fechaCorte = ModCalc_InvInicial.listIdGuia_fechaCorte(con, baseDato, desdeAAMMDD);
					inventario = Inventarios.inventario(con, baseDato, listIdBodegaEmpresa, listIdGuia_fechaCorte);
					List<Long> listIdGuia_entreFechas = ModCalc_GuiasPer.listIdGuia_entreFecha(con, baseDato, desdeAAMMDD, hastaAAMMDD);
					guiasPer = Inventarios.guiasPer(con, baseDato, listIdBodegaEmpresa, listIdGuia_entreFechas);
					listaAjustes = Calc_AjustesEP.listaAjustesEntreFechas(con, baseDato, desdeAAMMDD, hastaAAMMDD);
					mapBodega = BodegaEmpresa.mapIdBod_BodegaEmpresaInternasExternas(con, baseDato, aplicaPorSucursal, id_sucursal);
					dec = Moneda.numeroDecimal(con, baseDato);
					map = UsuarioPermiso.mapPermisoIdBodega(con, baseDato, Long.parseLong(id_usuario));
					mapPermanencias = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, baseDato);
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, baseDato, eMail, e);
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, baseDato, eMail, e);
				}
				ReportFacturas reporte = ModCalc_InvInicial.resumenInvInicial(baseDato,desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa,
						mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, listIdGuia_fechaCorte, inventario);
				listIdBodegaEmpresa = null;
				listIdGuia_fechaCorte = null;
				inventario = null;
				List<ModCalc_InvInicial> inventarioInicial = reporte.resumenInvInicial;
				reporte = null;
				List<ModCalc_GuiasPer> guiasPeriodo = ModCalc_GuiasPer.resumenGuiasPer(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas,
						mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, guiasPer, mapPermanencias);
				mapBodegaEmpresa = null;
				mapPrecios = null;
				mapMaestroPrecios = null;
				guiasPer = null;
				mapPermanencias = null;
				List<ModeloCalculo> valorTotalPorBodega = ModeloCalculo.valorTotalporBodega(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, inventarioInicial,guiasPeriodo, listaAjustes);
				listaAjustes = null;
				List<List<String>> proyectosAux = ReportFacturas.reportFacturaProyecto(valorTotalPorBodega, mapBodega);
				List<List<String>> resumenTotales = ReportFacturas.resumenTotalesPorProyecto(valorTotalPorBodega, dec);
				valorTotalPorBodega = null;
				List<ModeloCalculo> valorTotalporBodegaYGrupo = ModeloCalculo.valorTotalporBodegaYGrupo(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, inventarioInicial,guiasPeriodo);
				List<List<String>> proyectos = new ArrayList<List<String>>();
				if(!map.isEmpty()) {
					for(List<String> aux: proyectosAux) {
						String idBodega = map.get(aux.get(1));
						if(idBodega!=null) {
							proyectos.add(aux);
						}
					}
				}else {
                    proyectos.addAll(proyectosAux);
				}
				proyectosAux = null;
				try (Connection con2 = dbRead.getConnection()) {
					Map<String, List<List<String>>> mapResumenPorGrupo = ReportFacturas.mapResumenPorGrupo2(con2, baseDato, valorTotalporBodegaYGrupo);
					valorTotalporBodegaYGrupo = null;
					List<List<String>> resumenPorGrupoYProyecto = ReportFacturas.resumenPorGrupoYProyecto(con2, baseDato, proyectos, mapResumenPorGrupo);
					mapResumenPorGrupo = null;
					Map<String, List<List<String>>> mapInicioPer = ReportFacturas.mapInicioPerAllBodegas(con2, baseDato, inventarioInicial);
					inventarioInicial = null;
					Map<String, List<List<String>>> mapGuiasPer = ReportFacturas.mapGuiasPer(con2, baseDato, guiasPeriodo);
					List<List<String>> resumenPorProyectoGrupoYdetalle =
							ReportFacturas.resumenEstadosDePago(con2, baseDato, proyectos, desdeAAMMDD, hastaAAMMDD,mapeoDiccionario.get("nEmpresa"), mapInicioPer, mapGuiasPer);
					mapInicioPer = null;
					mapGuiasPer = null;
					File file = ReportFacturas.exportaProformaExcelResumen0(baseDato, mapeoDiccionario,
						proyectos,desde,hasta,uf,usd,eur,resumenTotales,resumenPorGrupoYProyecto,resumenPorProyectoGrupoYdetalle);
					try {
						Email email = new Email();
						String asunto = "REPORTE RESUMEN Y DETALLE POR TODOS LOS PROYECTOS AGRUPADO (PERIODO: desde "+desde.getFechaStrDDMMAA()+" a "+hasta.getFechaStrDDMMAA();
						email.setSubject(asunto);
						email.setFrom("desde MADA <informaciones@inqsol.cl>");
						email.setBodyHtml("<html>Archivo adjunto</html>");
						email.addTo(eMail);
						email.addAttachment("EP_Proforma_Resumen.xlsx", file);
						mailerClient.send(email);
					} catch (Exception e) {
						logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, baseDato, eMail, e);
					}
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, baseDato, eMail, e);
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, baseDato, eMail, e);
				}
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, baseDato, eMail, e);
			}
		}
	}

	public Result reportFacturaResumenExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/",msgErrorFormulario));
		}else {
			try {
				String desdeAAMMDD = form.get("fechaDesde").trim();
				String hastaAAMMDD = form.get("fechaHasta").trim();
				Double uf = Double.parseDouble(form.get("uf").replaceAll(",", "").trim());
				Double usd = Double.parseDouble(form.get("usd").replaceAll(",", "").trim());
				Double eur = Double.parseDouble(form.get("eur").replaceAll(",", "").trim());
				Fechas desde = Fechas.obtenerFechaDesdeStrAAMMDD(desdeAAMMDD);
				Fechas hasta = Fechas.obtenerFechaDesdeStrAAMMDD(hastaAAMMDD);
				Map<Long,Double> tasas = new HashMap<Long,Double>();
				tasas.put((long)1, (double) 1); 	// 'Peso Chileno', 'CLP', '0'
				tasas.put((long)2, usd); 			// 'Dólar', 'USD', '2'
				tasas.put((long)3, eur); 			// 'Euro', 'EUR', '3'
				tasas.put((long)4, uf); 			// 'Unidad Fomento', 'UF', '4'
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				if(mapeoPermiso.get("parametro.excel_porEMail")!=null && mapeoPermiso.get("parametro.excel_porEMail").equals("1")) {
					String mailDestino = "";
					try (Connection con = dbRead.getConnection()) {
						Usuario usuario = Usuario.findXIdUser(con, s.baseDato, Long.parseLong(s.id_usuario));
						if( usuario != null) {
							mailDestino = usuario.getEmail().trim().toLowerCase();
							if(mailDestino.length() < 4) {
								usuario = null;
							}
						}
					} catch (SQLException e) {
						logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
						return ok(mensajes.render("/home/", msgReport));
					} catch (Exception e) {
						logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
						return ok(mensajes.render("/home/", msgReport));
					}
					if(HomeController.isValidEmail(mailDestino)) {
						MnuReportes.reportFacturaResumenExcelMail generar = new MnuReportes.reportFacturaResumenExcelMail(s.baseDato, desdeAAMMDD, hastaAAMMDD,
								uf, usd, eur, s.id_usuario, s.aplicaPorSucursal, s.id_sucursal, mailDestino);
						generar.start();
					}
					String mensaje = "Solicitud en preparación, recibira el resultado al correo:"+mailDestino+". Tomara algunos minutos para recibir el correo";
					return ok(mensajes.render("/home/",mensaje));
				}
				List<Long> listIdBodegaEmpresa = null;
				Map<Long,Calc_BodegaEmpresa> mapBodegaEmpresa = null;
				Map<String,Calc_Precio> mapPrecios = null;
				Map<Long,Calc_Precio> mapMaestroPrecios = null;
				Map<String, Double> mapFijaTasas = null;
				List<Long> listIdGuia_fechaCorte = null;
				List<Inventarios> inventario = null;
				List<Long> listIdGuia_entreFechas = null;
				List<Inventarios> guiasPer = null;
				List<Calc_AjustesEP> listaAjustes = null;
				Map<Long,List<String>> mapBodega = null;
				Map<Long,Long> dec = null;
				Map<String,String> map = null;
				Map<String,String> mapPermanencias = null;
				try (Connection con = dbRead.getConnection()) {
					String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
					listIdBodegaEmpresa = ModCalc_InvInicial.listIdBodegaEmpresa(con, s.baseDato, permisoPorBodega);
					mapBodegaEmpresa = Calc_BodegaEmpresa.mapAllBodegasVigentes(con, s.baseDato);
					mapPrecios = Calc_Precio.mapPrecios(con, s.baseDato, listIdBodegaEmpresa);
					mapMaestroPrecios = Calc_Precio.mapMaestroPrecios(con, s.baseDato);
					mapFijaTasas = BodegaEmpresa.mapFijaTasasAll(con, s.baseDato);
					listIdGuia_fechaCorte = ModCalc_InvInicial.listIdGuia_fechaCorte(con, s.baseDato, desdeAAMMDD);
					inventario = Inventarios.inventario(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_fechaCorte);
					listIdGuia_entreFechas = ModCalc_GuiasPer.listIdGuia_entreFecha(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
					guiasPer = Inventarios.guiasPer(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_entreFechas);
					listaAjustes = Calc_AjustesEP.listaAjustesEntreFechas(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
					mapBodega = BodegaEmpresa.mapIdBod_BodegaEmpresaInternasExternas(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
					dec = Moneda.numeroDecimal(con, s.baseDato);
					map = UsuarioPermiso.mapPermisoIdBodega(con, s.baseDato, Long.parseLong(s.id_usuario));
					mapPermanencias = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, s.baseDato);
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				}
				ReportFacturas reporte = ModCalc_InvInicial.resumenInvInicial(s.baseDato,desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa,
						mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, listIdGuia_fechaCorte, inventario);
				listIdBodegaEmpresa = null;
				listIdGuia_fechaCorte = null;
				inventario = null;
				List<ModCalc_InvInicial> inventarioInicial = reporte.resumenInvInicial;
				List<ModCalc_GuiasPer> guiasPeriodo = ModCalc_GuiasPer.resumenGuiasPer(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas,
						mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, guiasPer, mapPermanencias);
				mapBodegaEmpresa = null;
				mapPrecios = null;
				mapMaestroPrecios = null;
				guiasPer = null;
				mapPermanencias = null;
				List<ModeloCalculo> valorTotalPorBodega = ModeloCalculo.valorTotalporBodega(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, inventarioInicial,guiasPeriodo, listaAjustes);
				listaAjustes = null;
				List<List<String>> proyectosAux = ReportFacturas.reportFacturaProyecto(valorTotalPorBodega, mapBodega);
				List<List<String>> resumenTotales = ReportFacturas.resumenTotalesPorProyecto(valorTotalPorBodega, dec);
				valorTotalPorBodega = null;
				List<ModeloCalculo> valorTotalporBodegaYGrupo = ModeloCalculo.valorTotalporBodegaYGrupo(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, inventarioInicial,guiasPeriodo);
				List<List<String>> proyectos = new ArrayList<List<String>>();
				if(!map.isEmpty()) {
					for(List<String> aux: proyectosAux) {
						String idBodega = map.get(aux.get(1));
						if(idBodega!=null) {
							proyectos.add(aux);
						}
					}
				}else {
                    proyectos.addAll(proyectosAux);
				}
				try (Connection con2 = dbRead.getConnection()) {
					Map<String, List<List<String>>> mapResumenPorGrupo = ReportFacturas.mapResumenPorGrupo2(con2, s.baseDato, valorTotalporBodegaYGrupo); 		// RESUMEN POR GRUPO
					valorTotalporBodegaYGrupo = null;
					List<List<String>> resumenPorGrupoYProyecto = ReportFacturas.resumenPorGrupoYProyecto(con2, s.baseDato, proyectos, mapResumenPorGrupo);
					mapResumenPorGrupo = null;
					Map<String, List<List<String>>> mapInicioPer = ReportFacturas.mapInicioPerAllBodegas(con2, s.baseDato, inventarioInicial);
					inventarioInicial = null;
					Map<String, List<List<String>>> mapGuiasPer = ReportFacturas.mapGuiasPer(con2, s.baseDato, guiasPeriodo);
					List<List<String>> resumenPorProyectoGrupoYdetalle =
							ReportFacturas.resumenEstadosDePago(con2, s.baseDato, proyectos, desdeAAMMDD, hastaAAMMDD,mapeoDiccionario.get("nEmpresa"), mapInicioPer, mapGuiasPer);
					mapInicioPer = null;
					mapGuiasPer = null;Map<String,Equipo> mapEquipo = Equipo.mapAllAllPorCodigo(con2, s.baseDato);
					File file = ReportFacturas.exportaProformaExcelResumen(s.baseDato, mapeoDiccionario,
							proyectos,desde,hasta,uf,usd,eur,resumenTotales,resumenPorGrupoYProyecto,resumenPorProyectoGrupoYdetalle, mapEquipo);
					return ok(file,false,Optional.of("EP_Proforma_Resumen.xlsx"));
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				}
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public class reportFacturaResumenExcelMail extends Thread {
		String baseDato;
		String desdeAAMMDD;
		String hastaAAMMDD;
		Double uf;
		Double usd;
		Double eur;
		String id_usuario;
		String aplicaPorSucursal;
		String id_sucursal;
		String eMail;

		public reportFacturaResumenExcelMail(String baseDato, String desdeAAMMDD, String hastaAAMMDD, Double uf, Double usd, Double eur, String id_usuario,
											 String aplicaPorSucursal, String id_sucursal, String eMail) {
			super();
			this.baseDato = baseDato;
			this.desdeAAMMDD = desdeAAMMDD;
			this.hastaAAMMDD = hastaAAMMDD;
			this.uf = uf;
			this.usd = usd;
			this.eur = eur;
			this.id_usuario = id_usuario;
			this.aplicaPorSucursal = aplicaPorSucursal;
			this.id_sucursal = id_sucursal;
			this.eMail = eMail;
		}
		public void run() {
			String className = this.getClass().getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			try{
				Fechas desde = Fechas.obtenerFechaDesdeStrAAMMDD(desdeAAMMDD);
				Fechas hasta = Fechas.obtenerFechaDesdeStrAAMMDD(hastaAAMMDD);
				Map<Long,Double> tasas = new HashMap<Long,Double>();
				tasas.put((long)1, (double) 1); 	// 'Peso Chileno', 'CLP', '0'
				tasas.put((long)2, usd); 			// 'Dólar', 'USD', '2'
				tasas.put((long)3, eur); 			// 'Euro', 'EUR', '3'
				tasas.put((long)4, uf); 			// 'Unidad Fomento', 'UF', '4'
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(baseDato);
				List<Long> listIdBodegaEmpresa = null;
				Map<Long,Calc_BodegaEmpresa> mapBodegaEmpresa = null;
				Map<String,Calc_Precio> mapPrecios = null;
				Map<Long,Calc_Precio> mapMaestroPrecios = null;
				Map<String, Double> mapFijaTasas = null;
				List<Long> listIdGuia_fechaCorte = null;
				List<Inventarios> inventario = null;
				List<Long> listIdGuia_entreFechas = null;
				List<Inventarios> guiasPer = null;
				List<Calc_AjustesEP> listaAjustes = null;
				Map<Long,List<String>> mapBodega = null;
				Map<Long,Long> dec = null;
				Map<String,String> map = null;
				Map<String,String> mapPermanencias = null;
				try (Connection con = dbRead.getConnection()) {
					String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, baseDato, Long.parseLong(id_usuario));
					listIdBodegaEmpresa = ModCalc_InvInicial.listIdBodegaEmpresa(con, baseDato, permisoPorBodega);
					mapBodegaEmpresa = Calc_BodegaEmpresa.mapAllBodegasVigentes(con, baseDato);
					mapPrecios = Calc_Precio.mapPrecios(con, baseDato, listIdBodegaEmpresa);
					mapMaestroPrecios = Calc_Precio.mapMaestroPrecios(con, baseDato);
					mapFijaTasas = BodegaEmpresa.mapFijaTasasAll(con, baseDato);
					listIdGuia_fechaCorte = ModCalc_InvInicial.listIdGuia_fechaCorte(con, baseDato, desdeAAMMDD);
					inventario = Inventarios.inventario(con, baseDato, listIdBodegaEmpresa, listIdGuia_fechaCorte);
					listIdGuia_entreFechas = ModCalc_GuiasPer.listIdGuia_entreFecha(con, baseDato, desdeAAMMDD, hastaAAMMDD);
					guiasPer = Inventarios.guiasPer(con, baseDato, listIdBodegaEmpresa, listIdGuia_entreFechas);
					listaAjustes = Calc_AjustesEP.listaAjustesEntreFechas(con, baseDato, desdeAAMMDD, hastaAAMMDD);
					mapBodega = BodegaEmpresa.mapIdBod_BodegaEmpresaInternasExternas(con, baseDato, aplicaPorSucursal, id_sucursal);
					dec = Moneda.numeroDecimal(con, baseDato);
					map = UsuarioPermiso.mapPermisoIdBodega(con, baseDato, Long.parseLong(id_usuario));
					mapPermanencias = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, baseDato);
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, baseDato, eMail, e);
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, baseDato, eMail, e);
				}
				ReportFacturas reporte = ModCalc_InvInicial.resumenInvInicial(baseDato,desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa,
						mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, listIdGuia_fechaCorte, inventario);
				listIdBodegaEmpresa = null;
				listIdGuia_fechaCorte = null;
				inventario = null;
				List<ModCalc_InvInicial> inventarioInicial = reporte.resumenInvInicial;
				List<ModCalc_GuiasPer> guiasPeriodo = ModCalc_GuiasPer.resumenGuiasPer(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas,
						mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, guiasPer, mapPermanencias);
				mapBodegaEmpresa = null;
				mapPrecios = null;
				mapMaestroPrecios = null;
				guiasPer = null;
				mapPermanencias = null;
				List<ModeloCalculo> valorTotalPorBodega = ModeloCalculo.valorTotalporBodega(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, inventarioInicial,guiasPeriodo, listaAjustes);
				listaAjustes = null;
				List<List<String>> proyectosAux = ReportFacturas.reportFacturaProyecto(valorTotalPorBodega, mapBodega);
				List<List<String>> resumenTotales = ReportFacturas.resumenTotalesPorProyecto(valorTotalPorBodega, dec);
				valorTotalPorBodega = null;
				List<ModeloCalculo> valorTotalporBodegaYGrupo = ModeloCalculo.valorTotalporBodegaYGrupo(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, inventarioInicial,guiasPeriodo);
				List<List<String>> proyectos = new ArrayList<List<String>>();
				if(!map.isEmpty()) {
					for(List<String> aux: proyectosAux) {
						String idBodega = map.get(aux.get(1));
						if(idBodega!=null) {
							proyectos.add(aux);
						}
					}
				}else {
                    proyectos.addAll(proyectosAux);
				}
				File file = null;
				try (Connection con2 = dbRead.getConnection()) {
					Map<String, List<List<String>>> mapResumenPorGrupo = ReportFacturas.mapResumenPorGrupo2(con2, baseDato, valorTotalporBodegaYGrupo); 		// RESUMEN POR GRUPO
					valorTotalporBodegaYGrupo = null;
					List<List<String>> resumenPorGrupoYProyecto = ReportFacturas.resumenPorGrupoYProyecto(con2, baseDato, proyectos, mapResumenPorGrupo);
					mapResumenPorGrupo = null;
					Map<String, List<List<String>>> mapInicioPer = ReportFacturas.mapInicioPerAllBodegas(con2, baseDato, inventarioInicial);
					inventarioInicial = null;
					Map<String, List<List<String>>> mapGuiasPer = ReportFacturas.mapGuiasPer(con2, baseDato, guiasPeriodo);
					List<List<String>> resumenPorProyectoGrupoYdetalle =
							ReportFacturas.resumenEstadosDePago(con2, baseDato, proyectos, desdeAAMMDD, hastaAAMMDD,mapeoDiccionario.get("nEmpresa"), mapInicioPer, mapGuiasPer);
					mapInicioPer = null;
					mapGuiasPer = null;Map<String,Equipo> mapEquipo = Equipo.mapAllAllPorCodigo(con2, baseDato);
					file = ReportFacturas.exportaProformaExcelResumen(baseDato, mapeoDiccionario,
							proyectos,desde,hasta,uf,usd,eur,resumenTotales,resumenPorGrupoYProyecto,resumenPorProyectoGrupoYdetalle, mapEquipo);
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, baseDato, eMail, e);
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, baseDato, eMail, e);
				}
				try {
					Email email = new Email();
					String asunto = "REPORTE RESUMEN Y DETALLE POR TODOS LOS PROYECTOS POR FAMILIA (PERIODO: desde "+desde.getFechaStrDDMMAA()+" a "+hasta.getFechaStrDDMMAA();
					email.setSubject(asunto);
					email.setFrom("desde MADA <informaciones@inqsol.cl>");
					email.setBodyHtml("<html>Archivo adjunto</html>");
					email.addTo(eMail);
					email.addAttachment("EP_Proforma_Resumen.xlsx", file);
					mailerClient.send(email);
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, baseDato, eMail, e);
				}
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, baseDato, eMail, e);
			}
		}
	}


	//====================================================================================
	// MNU proformaConsolidado   Reportes/Proforma/Consolidado (varios Meses)
	//====================================================================================

	public Result reportFacturaConsolidado(Http.Request request) {
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
		if(mapeoPermiso.get("proformaConsolidado")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try {
			Fechas hoy = Fechas.hoy();
			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
			String fecha = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			return ok(reportFacturaConsolidado.render(mapeoDiccionario,mapeoPermiso,userMnu, fecha));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result reportFacturaConsolidadoRtp(Http.Request request) {
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
			return ok(mensajes.render("/home/",msgErrorFormulario));
		}else {
			try {
				Fechas fecha = Fechas.obtenerFechaDesdeStrAAMMDD(form.get("fecha"));
				fecha = Fechas.obtenerFinMes(fecha.getFechaCal());
				Long meses = Long.parseLong(form.get("cantMeses").trim());
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				List<List<String>> datos = null;
				try (Connection con = dbRead.getConnection()) {
					String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
					datos = ReportFacturaConsolidado.reportFacturaConsolidadoRtp(con, s.baseDato, fecha, meses, permisoPorBodega, mapeoDiccionario.get("pais"), s.aplicaPorSucursal, s.id_sucursal);
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				}
				List<String> categorias = new ArrayList<String>();
				for(int i=1; i<datos.get(0).size()-1; i++) {
					categorias.add("'" + datos.get(0).get(i) + "'");
				}
				List<String> nameSerie = new ArrayList<String>();
				Map<String,List<String>> mapDataSerie = new HashMap<String,List<String>>();
				for(int i=1; i<datos.size()-1; i++) {
					nameSerie.add("'" + datos.get(i).get(1) + "'");
					List<String> dataSerie = new ArrayList<String>();
					for(int j=2; j<datos.get(i).size()-1; j++) {
						dataSerie.add(datos.get(i).get(j).replaceAll(",", ""));
					}
					mapDataSerie.put("'" + datos.get(i).get(1) + "'",dataSerie);
				}
				if(mapeoDiccionario.get("nEmpresa").equals("SM8 DE MEXICO")) {
					File file = ReportFacturaConsolidado.reportFacturaConsolidadoRtpExcel(s.baseDato, mapeoDiccionario, datos);
					return ok(file,false,Optional.of("Consol_ep_por_meses.xlsx"));
				}
				return ok(reportFacturaConsolidadoRtp.render(mapeoDiccionario,mapeoPermiso,userMnu, datos, form.get("fecha"), form.get("cantMeses"), categorias, nameSerie, mapDataSerie));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reportFacturaConsolidadoRtpExcel(Http.Request request) {
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
			return ok(mensajes.render("/home/",msgErrorFormulario));
		}else {
			try {
				Fechas fecha = Fechas.obtenerFechaDesdeStrAAMMDD(form.get("fecha"));
				Long meses = Long.parseLong(form.get("cantMeses"));
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				List<List<String>> datos = null;
				try (Connection con = dbRead.getConnection()){
					String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
					datos = ReportFacturaConsolidado.reportFacturaConsolidadoRtp(con, s.baseDato, fecha, meses, permisoPorBodega, mapeoDiccionario.get("pais"), s.aplicaPorSucursal, s.id_sucursal);
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				}
					File file = ReportFacturaConsolidado.reportFacturaConsolidadoRtpExcel(s.baseDato, mapeoDiccionario, datos);
					return ok(file,false,Optional.of("Consol_ep_por_meses.xlsx"));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}


	//====================================================================================
	// MNU proformaConsolidado   Reportes/Proforma/Consolidado (detalle por grupos)
	//====================================================================================

	public Result reportFactConsolconGrupo(Http.Request request) {
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
		if(mapeoPermiso.get("proformaConsolidado")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try {
			Fechas hoy = Fechas.hoy();
			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
			String fecha = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			return ok(reportFactConsolconGrupo.render(mapeoDiccionario,mapeoPermiso,userMnu, fecha));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result reportFactConsolconGrupoRtp(Http.Request request) {
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
			return ok(mensajes.render("/home/",msgErrorFormulario));
		}else {
			try {
				Fechas fecha = Fechas.obtenerFechaDesdeStrAAMMDD(form.get("fecha"));
				fecha = Fechas.obtenerFinMes(fecha.getFechaCal());
				Long meses = Long.parseLong(form.get("cantMeses").trim());
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				List<List<String>> datos = null;
				try (Connection con = dbRead.getConnection()) {
					String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
					datos = ReportFacturaConsolidado.reportConsDetalladoRtp(con, s.baseDato, fecha, meses, permisoPorBodega, mapeoDiccionario.get("pais"), s.aplicaPorSucursal, s.id_sucursal);
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				}
				List<String> categorias = new ArrayList<String>();
				for(int i=4; i<datos.get(0).size()-1; i=i+3) {
					categorias.add("'" + datos.get(0).get(i) + "'");
				}
				//GRAFICOS POR EMPRESA
				String aux ="";
				List<String> listSeleccion = new ArrayList<String>();
				List<String> listGrupo = new ArrayList<String>();
				Map<String,List<String>> mapGrupo = new HashMap<String,List<String>>();
				Map<String,List<String>> mapDatosEmpresa = new HashMap<String,List<String>>();
				for(int i=2; i<datos.size()-1; i++) {
					if(!aux.equals("'"+datos.get(i).get(0)+"'")) {
						if(i>2) {
							mapGrupo.put(aux, listGrupo);
							listGrupo = new ArrayList<String>();
						}
						listSeleccion.add("'"+datos.get(i).get(0)+"'");
						aux = "'"+datos.get(i).get(0)+"'";
					}

					if(aux.equals("'"+datos.get(i).get(0)+"'")) {
						listGrupo.add("'"+datos.get(i).get(1)+"'");
						mapGrupo.put(aux, listGrupo);
					}

					List<String> listDatos = new ArrayList<String>();
					for(int j=4; j<datos.get(i).size()-3; j = j+3) {
						listDatos.add(datos.get(i).get(j).replaceAll(",", ""));
					}
					mapDatosEmpresa.put("'"+datos.get(i).get(0)+"'"+"_"+"'"+datos.get(i).get(1)+"'", listDatos);
				}
				//GRAFICOS POR GRUPO
				//ORDENA LA LISTA POR GRUPO
				for(int j=2;j<datos.size()-1;j++) {
					for(int i=2;i<datos.size()-j;i++) {
						String A = datos.get(i).get(1) + "_" + datos.get(i).get(0);
						String B = datos.get(i+1).get(1) + "_" + datos.get(i+1).get(0);
						if (i+1!=datos.size() && A.compareToIgnoreCase(B)>0) {
							List<String> auxlista;
							auxlista=datos.get(i);
							datos.set(i,datos.get(i+1));
							datos.set(i+1, auxlista);
						}
					}
				}
				String aux2 ="";
				List<String> listSeleccion2 = new ArrayList<String>();
				List<String> listEmpresa = new ArrayList<String>();
				Map<String,List<String>> mapEmpresas = new HashMap<String,List<String>>();
				Map<String,List<String>> mapDatosGrupo = new HashMap<String,List<String>>();
				for(int i=2; i<datos.size()-1; i++) {
					if(!aux2.equals("'"+datos.get(i).get(1)+"'")) {
						if(i>2) {
							mapEmpresas.put(aux2, listEmpresa);
							listEmpresa = new ArrayList<String>();
						}
						listSeleccion2.add("'"+datos.get(i).get(1)+"'");
						aux2 = "'"+datos.get(i).get(1)+"'";
					}

					if(aux2.equals("'"+datos.get(i).get(1)+"'")) {
						listEmpresa.add("'"+datos.get(i).get(0)+"'");
						mapEmpresas.put(aux2, listEmpresa);
					}

					List<String> listDatos = new ArrayList<String>();
					for(int j=4; j<datos.get(i).size()-3; j = j+3) {
						listDatos.add(datos.get(i).get(j).replaceAll(",", ""));
					}
					mapDatosGrupo.put("'"+datos.get(i).get(1)+"'"+"_"+"'"+datos.get(i).get(0)+"'", listDatos);
				}
				if(mapeoDiccionario.get("nEmpresa").equals("SM8 DE MEXICO")) {
					File file = ReportFacturaConsolidado.reportConsDetalladoRtpExcel(s.baseDato, mapeoDiccionario, datos);
					if(file!=null) {
						return ok(file,false,Optional.of("Consol_ep_por_grupo_meses.xlsx"));
					}else {
						return ok("");
					}
				}
				return ok(reportFactConsolconGrupoRtp.render(mapeoDiccionario,mapeoPermiso,userMnu, datos, form.get("fecha"), form.get("cantMeses"), categorias, listSeleccion, mapGrupo, mapDatosEmpresa, listSeleccion2, mapEmpresas, mapDatosGrupo));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reportFactConsolconGrupoRtpExcel(Http.Request request) {
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
			return ok(mensajes.render("/home/",msgErrorFormulario));
		}else {
			try {
				Fechas fecha = Fechas.obtenerFechaDesdeStrAAMMDD(form.get("fecha"));
				Long meses = Long.parseLong(form.get("cantMeses"));
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				List<List<String>> datos = null;
				try (Connection con = dbRead.getConnection()) {
					String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
					datos = ReportFacturaConsolidado.reportConsDetalladoRtp(con, s.baseDato, fecha, meses, permisoPorBodega, mapeoDiccionario.get("pais"), s.aplicaPorSucursal, s.id_sucursal);
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				}
				File file = ReportFacturaConsolidado.reportConsDetalladoRtpExcel(s.baseDato, mapeoDiccionario, datos);
				return ok(file,false,Optional.of("Consol_ep_por_grupo_meses.xlsx"));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	//====================================================================================
	// MNU proformaConsolidado   Reportes/Proforma/Consolidado (detalle por equipos)
	//====================================================================================

	public Result reportFactConsolconEquipos(Http.Request request) {
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
		if(mapeoPermiso.get("proformaConsolidado")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try {
			Fechas hoy = Fechas.hoy();
			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
			String fecha = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			return ok(reportFactConsolconEquipos.render(mapeoDiccionario,mapeoPermiso,userMnu, fecha));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result reportFactConsolconEquiposRtp(Http.Request request) {
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
			return ok(mensajes.render("/home/",msgErrorFormulario));
		}else {
			try {
				Fechas fecha = Fechas.obtenerFechaDesdeStrAAMMDD(form.get("fecha"));
				fecha = Fechas.obtenerFinMes(fecha.getFechaCal());
				Long meses = Long.parseLong(form.get("cantMeses").trim());
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				List<List<String>> datos = null;
				try (Connection con = dbRead.getConnection()) {
					String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
					datos = ReportFacturaConsolidado.reportConsDetalladoPorEquipoRtp(con, s.baseDato, fecha, meses, permisoPorBodega, mapeoDiccionario.get("pais"), s.aplicaPorSucursal, s.id_sucursal);
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				}
				List<String> categorias = new ArrayList<String>();
				for(int i=4; i<datos.get(0).size()-1; i=i+3) {
					categorias.add("'" + datos.get(0).get(i) + "'");
				}
				//GRAFICOS POR EMPRESA
				String aux ="";
				List<String> listSeleccion = new ArrayList<String>();
				List<String> listEquipo = new ArrayList<String>();
				Map<String,List<String>> mapEquipo = new HashMap<String,List<String>>();
				Map<String,List<String>> mapDatosEmpresa = new HashMap<String,List<String>>();
				for(int i=2; i<datos.size()-1; i++) {
				if(!aux.equals("'"+datos.get(i).get(0)+"'")) {
					if(i>2) {
						mapEquipo.put(aux, listEquipo);
						listEquipo = new ArrayList<String>();
					}
					listSeleccion.add("'"+datos.get(i).get(0)+"'");
					aux = "'"+datos.get(i).get(0)+"'";
				}
					if(aux.equals("'"+datos.get(i).get(0)+"'")) {
						listEquipo.add("'"+datos.get(i).get(2)+"'");
						mapEquipo.put(aux, listEquipo);
					}
					List<String> listDatos = new ArrayList<String>();
					for(int j=4; j<datos.get(i).size()-3; j = j+3) {
						listDatos.add(datos.get(i).get(j).replaceAll(",", ""));
					}
					mapDatosEmpresa.put("'"+datos.get(i).get(0)+"'"+"_"+"'"+datos.get(i).get(2)+"'", listDatos);
				}
				//GRAFICOS POR EQUIPO
				//ORDENA LA LISTA POR EQUIPO
				for(int j=2;j<datos.size()-1;j++) {
					for(int i=2;i<datos.size()-j;i++) {
						String A = datos.get(i).get(2) + "_" + datos.get(i).get(0);
						String B = datos.get(i+1).get(2) + "_" + datos.get(i+1).get(0);
						if (i+1!=datos.size() && A.compareToIgnoreCase(B)>0) {
							List<String> auxlista;
							auxlista=datos.get(i);
							datos.set(i,datos.get(i+1));
							datos.set(i+1, auxlista);
						}
					}
				}
				String aux2 ="";
				List<String> listSeleccion2 = new ArrayList<String>();
				List<String> listEmpresa = new ArrayList<String>();
				Map<String,List<String>> mapEmpresas = new HashMap<String,List<String>>();
				Map<String,List<String>> mapDatosEquipo = new HashMap<String,List<String>>();
				Map<String, String> mapNomEquip = new HashMap<String,String>();
				for(int i=2; i<datos.size()-1; i++) {
					if(!aux2.equals("'"+datos.get(i).get(2)+"'")) {
						if(i>2) {
							mapEmpresas.put(aux2, listEmpresa);
							listEmpresa = new ArrayList<String>();
						}
						listSeleccion2.add("'"+datos.get(i).get(2)+"'");
						mapNomEquip.put("'"+datos.get(i).get(2)+"'", datos.get(i).get(2)+" - "+datos.get(i).get(3));
						aux2 = "'"+datos.get(i).get(2)+"'";
					}
					if(aux2.equals("'"+datos.get(i).get(2)+"'")) {
						listEmpresa.add("'"+datos.get(i).get(0)+"'");
						mapEmpresas.put(aux2, listEmpresa);
					}
					List<String> listDatos = new ArrayList<String>();
					for(int j=6; j<datos.get(i).size()-3; j = j+3) {
						listDatos.add(datos.get(i).get(j).replaceAll(",", ""));
					}
					mapDatosEquipo.put("'"+datos.get(i).get(2)+"'"+"_"+"'"+datos.get(i).get(0)+"'", listDatos);
				}
				if(mapeoDiccionario.get("nEmpresa").equals("SM8 DE MEXICO")) {
					File file = ReportFacturaConsolidado.reportConsDetalladoPorEquipoRtpExcel(s.baseDato, mapeoDiccionario, datos);
					return ok(file,false,Optional.of("Consol_ep_por_equipo_meses.xlsx"));
				}
				return ok(reportFactConsolconEquiposRtp.render(mapeoDiccionario,mapeoPermiso,userMnu, datos, form.get("fecha"), form.get("cantMeses"),
							categorias, listSeleccion, mapEquipo, mapDatosEmpresa, listSeleccion2, mapEmpresas, mapDatosEquipo, mapNomEquip));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reportFactConsolconEquiposRtpExcel(Http.Request request) {
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
			return ok(mensajes.render("/home/",msgErrorFormulario));
		}else {
			try {
				Fechas fecha = Fechas.obtenerFechaDesdeStrAAMMDD(form.get("fecha"));
				Long meses = Long.parseLong(form.get("cantMeses"));
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				List<List<String>> datos = null;
				try (Connection con = dbRead.getConnection()) {
					String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
					datos = ReportFacturaConsolidado.reportConsDetalladoPorEquipoRtp(con, s.baseDato, fecha, meses, permisoPorBodega, mapeoDiccionario.get("pais"), s.aplicaPorSucursal, s.id_sucursal);
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				}
				File file = ReportFacturaConsolidado.reportConsDetalladoPorEquipoRtpExcel(s.baseDato, mapeoDiccionario, datos);
				return ok(file,false,Optional.of("Consol_ep_por_equipo_meses.xlsx"));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	//====================================================================================
	// MNU proformaListado   Reportes/Proforma/Listado Pre-Factura Simple
	//====================================================================================

	public Result proformaListaHPeriodo(Http.Request request) {
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
		if(mapeoPermiso.get("proformaListado")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try {
			Fechas hoy = Fechas.hoy();
			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			return ok(proformaListaHPeriodo.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result proformaListaH(Http.Request request) {
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
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if(mapeoPermiso.get("proformaListado")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/",msgErrorFormulario));
		}else {
			try (Connection con = dbRead.getConnection()) {
				String fechaDesde = form.get("fechaDesde");
				String fechaHasta = form.get("fechaHasta");
				String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
				List<List<String>> lista = ProformaSimple.listadoPorPeriodo(con, s.baseDato, permisoPorBodega, fechaDesde, fechaHasta, s.aplicaPorSucursal, s.id_sucursal);
				return ok(proformaListaH.render(mapeoDiccionario,mapeoPermiso,userMnu, lista, fechaDesde, fechaHasta));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result proformaListaHGet(Http.Request request, String fechaDesde, String fechaHasta) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		Map<String, String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String, String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if (mapeoPermiso.get("proformaListado") == null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/", msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()) {
			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
			List<List<String>> lista = ProformaSimple.listadoPorPeriodo(con, s.baseDato, permisoPorBodega, fechaDesde, fechaHasta, s.aplicaPorSucursal, s.id_sucursal);
			return ok(proformaListaH.render(mapeoDiccionario,mapeoPermiso,userMnu, lista, fechaDesde, fechaHasta));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result proformaEliminaH(Http.Request request) {
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
				Long id_proforma = Long.parseLong(form.get("id_proforma"));
				String desde = form.get("fechaDesde");
				String hasta = form.get("fechaHasta");
				ProformaSimple.eliminaProforma(con, s.baseDato, id_proforma);
				return ok(mensajes.render("/proformaListaHGet/"+desde+","+hasta,"Proforma nro: "+id_proforma+" eliminada" ));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}


	//====================================================================================
	// MNU proformaListado   Reportes/Proforma/Listado de Proformas
	//====================================================================================


	public class proformaListaPdf0 extends Thread {
		String baseDato;
		int desdeNro;
		int hastaNro;
		Map<String, String> map;
		String eMail;

		public proformaListaPdf0(String baseDato, int desdeNro, int hastaNro, Map<String, String> map, String eMail) {
			super();
			this.baseDato = baseDato;
			this.desdeNro = desdeNro;
			this.hastaNro = hastaNro;
			this.map = map;
			this.eMail = eMail;

		}

		public void run() {
			String className = this.getClass().getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			PDFMergerUtility merger = new PDFMergerUtility();
			File outputFile = TempFile.createTempFile("tmp","null");
			try {
				String nros = "";
				for (int i = desdeNro; i<(hastaNro+1); i++) {
					String valida = map.get(i+"");
					if(valida != null) {
						nros += i+", ";
						String path = baseDato+"/"+"PRpdf"+i+"_proformaArriendo.pdf";
						try {
							InputStream archivo = Archivos.leerArchivo(path);
							merger.addSource(archivo);
						} catch (Exception e) {
							logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, baseDato, eMail, e);
						}

					}

				}
				merger.setDestinationFileName(outputFile.getAbsolutePath());
				MemoryUsageSetting memoryUsageSetting = MemoryUsageSetting.setupMainMemoryOnly();
				merger.mergeDocuments(memoryUsageSetting);
				try {
					Email email = new Email();
					String asunto = "Lista de proformas desde nro: "+desdeNro+" hasta nro: "+hastaNro;
					String desde = "desde MADA <informaciones@inqsol.cl>";
					email.setSubject(asunto);
					email.setFrom(desde);
					email.setBodyHtml("<html><body>" +
							" <p><b>PDF adjunto</b></p>" +
							" <p>Numeros de proforma agregados: "+nros+"</p>" +
							" <p>Nota: por favor no responder este correo</p>" +
							" </body></html>");
					email.addTo(eMail);
					email.addAttachment("proformas.pdf", outputFile);
					mailerClient.send(email);
				} catch (Exception x) {
					x.printStackTrace();
					long fileSizeInBytes = outputFile.length();
					double fileSizeInKB = (double) fileSizeInBytes / 1024;
					double fileSizeInMB = fileSizeInKB / 1024;
					Email email = new Email();
					String asunto = "Lista de proformas desde nro: "+desdeNro+" hasta nro: "+hastaNro;
					String desde = "desde MADA <informaciones@inqsol.cl>";
					email.setSubject(asunto);
					email.setFrom(desde);
					email.setBodyHtml("<html><body>" +
							" <p><b>NO EXISTEN PDF ASOCIADOS EN EL RANGO SOLICITADO</b></p>" +
							" <p><b>TAMAÑO FILE: "+fileSizeInMB+" MB</b></p>" +
							" <p>Nota: por favor no responder este correo</p>" +
							" </body></html>");
					email.addTo(eMail);
					mailerClient.send(email);
				}
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, baseDato, eMail, e);
			}
		}
	}

	public Result proformaListaPdf(Http.Request request) {
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
			return ok(mensajes.render("/home/",msgErrorFormulario));
		}else {
			try {
				String desde = form.get("fechaDesde");
				String hasta = form.get("fechaHasta");
				int desdeNro = Integer.parseInt(form.get("nroIni"));
				int hastaNro = Integer.parseInt(form.get("nroFin"));
				String mailDestino =  null;
				Map<String,String> map = new HashMap<String,String>();
				Usuario usuario = null;
				try (Connection con = dbRead.getConnection()) {
					String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
					List<List<String>> lista = Proforma.listadoPorPeriodo(con, s.baseDato, permisoPorBodega, desde, hasta, s.aplicaPorSucursal, s.id_sucursal);
					for(List<String> l: lista) {
						map.put(l.get(0), l.get(0));
					}
					usuario = Usuario.findXIdUser(con, s.baseDato, Long.parseLong(s.id_usuario));
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				}
				if( usuario != null) {
					mailDestino = usuario.getEmail().trim().toLowerCase();
					if(mailDestino.length() < 4) {
						usuario = null;
					}
				}
				if(HomeController.isValidEmail(mailDestino)) {
					if(map !=null && mailDestino != null) {
						MnuReportes.proformaListaPdf0 generar = new MnuReportes.proformaListaPdf0(s.baseDato, desdeNro, hastaNro, map, mailDestino);
						generar.start();
						String mensaje = "Solicitud en preparación, recibira el resultado al correo:"+mailDestino+". Tomara varios minutos para recibir el correo";
						return ok(mensajes.render("/home/",mensaje));
					}else {
						String mensaje = "No es posible generar la solicitud debido a que no existe dato de email en la configuración de su usuario";
						return ok(mensajes.render("/home/",mensaje));
					}
				}else{
					String mensaje = "No es posible generar la solicitud debido a que el mail que existe en la configuración de su usuario no es valido";
					return ok(mensajes.render("/home/",mensaje));
				}
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result proformaListaPeriodo(Http.Request request) {
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
		if(mapeoPermiso.get("proformaListado")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try {
			Fechas hoy = Fechas.hoy();
			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			return ok(proformaListaPeriodo.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result proformaLista(Http.Request request) {
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
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if(mapeoPermiso.get("proformaListado")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/",msgErrorFormulario));
		}else {
			try (Connection con = dbRead.getConnection()) {
				String fechaDesde = form.get("fechaDesde");
				String fechaHasta = form.get("fechaHasta");
				String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
				List<List<String>> lista = Proforma.listadoPorPeriodo(con, s.baseDato, permisoPorBodega, fechaDesde, fechaHasta, s.aplicaPorSucursal, s.id_sucursal);
				return ok(proformaLista.render(mapeoDiccionario,mapeoPermiso,userMnu, lista, fechaDesde, fechaHasta));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result proformaListaGet(Http.Request request, String desde, String hasta) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		Map<String, String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String, String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if (mapeoPermiso.get("proformaListado") == null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/", msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()) {
			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
			List<List<String>> lista = Proforma.listadoPorPeriodo(con, s.baseDato, permisoPorBodega, desde, hasta, s.aplicaPorSucursal, s.id_sucursal);
			return ok(proformaLista.render(mapeoDiccionario, mapeoPermiso, userMnu, lista, desde, hasta));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result proformaListaExcel(Http.Request request) {
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
			return ok(mensajes.render("/home/",msgErrorFormulario));
		}else {
			try {
				String fechaDesde = form.get("fechaDesde");
				String fechaHasta = form.get("fechaHasta");
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				if(mapeoPermiso.get("proformaListado")==null) {
					logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
					return ok(mensajes.render("/", msgSinPermiso));
				}
				List<List<String>> lista = null;
				try (Connection con = dbRead.getConnection()) {
					String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
					lista = Proforma.listadoPorPeriodo(con, s.baseDato, permisoPorBodega, fechaDesde, fechaHasta, s.aplicaPorSucursal, s.id_sucursal);
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				}
				File file = Proforma.listadoPorAnioExcel(s.baseDato, mapeoDiccionario, lista);
				return ok(file,false,Optional.of("ListadoDeProformas.xlsx"));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result generaProformaXml(Http.Request request) {
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
			return ok(mensajes.render("/home/",msgErrorFormulario));
		}else {
			try (Connection con = dbWrite.getConnection()) {
				Long id_proforma = Long.parseLong(form.get("id_proforma"));
				String desde = form.get("fechaDesde");
				String hasta = form.get("fechaHasta");
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				Proforma proforma = Proforma.find(con, s.baseDato, id_proforma);
				InputStream auxfile = Archivos.leerArchivo(s.baseDato+"/"+proforma.proformaXml);
				File file = Archivos.parseInputStreamToFile(auxfile);
				FormXmlFactura facturaXml = FormXmlFactura.leerArchivoXml(file);
				List<TipoReferencia> listTipoReferencias = TipoReferencia.all(con, s.baseDato);
				Map<String,String> mapAllCodVsConcep = TipoReferencia.mapAllCodVsConcep(con, s.baseDato);
				return ok(generaProformaXml.render(mapeoDiccionario,mapeoPermiso,userMnu, facturaXml, id_proforma, listTipoReferencias, mapAllCodVsConcep,
						desde, hasta));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result sendXMLFacura(Http.Request request, Long id_proforma, String desde, String hasta){
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		FormXmlFactura form = formFactory.form(FormXmlFactura.class).withDirectFieldAccess(true).bindFromRequest(request).get();
		try {
			Proforma proforma = null;
			EmisorTributario emisor = null;
			try (Connection con = dbWrite.getConnection()) {
					Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
					proforma = Proforma.find(con, s.baseDato, id_proforma);
					FormXmlFactura.grabarReferencias(s.baseDato, mapeoPermiso, proforma.proformaXml, form);
					emisor = EmisorTributario.find(con, s.baseDato);
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
			InputStream auxfile = Archivos.leerArchivo(s.baseDato+"/"+proforma.proformaXml);
			File file = Archivos.parseInputStreamToFile(auxfile);
			String correoDestino = emisor.emailEnvioXML;
			Email email = new Email();
			String asunto = "Envio de XML desde MADA ";
			String desdeMail = "desde MADA <informaciones@inqsol.cl>";
			email.setSubject(asunto);
			email.setFrom(desdeMail);
			email.setBodyHtml("<html>Archivo adjunto</html>");
			email.addTo(correoDestino);
			email.addAttachment(proforma.proformaXml, file);
			mailerClient.send(email);
			return ok(mensajes.render("/proformaListaGet/"+desde+","+hasta,"DTE enviado enviado al correo: "+correoDestino ));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result generaProformaApiManager(Http.Request request) {
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
			return ok(mensajes.render("/home/",msgErrorFormulario));
		}else {
			try (Connection con = dbWrite.getConnection()) {
				Long id_proforma = Long.parseLong(form.get("id_proforma"));
				String desde = form.get("fechaDesde");
				String hasta = form.get("fechaHasta");
				Proforma proforma = Proforma.find(con, s.baseDato, id_proforma);
				String rs = ApiManagerDocDoc.genera(con, s.baseDato, proforma.jsonGenerado, ws, id_proforma);
				return ok(mensajes.render("/proformaListaGet/" + desde + "," + hasta, "API Manager enviada: " + rs));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result generaProformaApiNubox(Http.Request request) {
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
			return ok(mensajes.render("/home/",msgErrorFormulario));
		}else {
			try (Connection con = dbWrite.getConnection()) {
				Long id_proforma = Long.parseLong(form.get("id_proforma"));
				String desde = form.get("fechaDesde");
				String hasta = form.get("fechaHasta");
				Proforma proforma = Proforma.find(con, s.baseDato, id_proforma);
				Long id_guia = (long) 0;
				String rs = ApiNuboxDocDoc.genera(con, s.baseDato, proforma.jsonGenerado, ws, id_proforma, id_guia);
				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "proforma", id_proforma, "update", "hace envio de factura API NUBOX nro: " + proforma.getId());
				return ok(mensajes.render("/proformaListaGet/" + desde + "," + hasta, rs));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result generaProformaApiSapConconcreto(Http.Request request) {
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
			return ok(mensajes.render("/home/",msgErrorFormulario));
		}else {
			try (Connection con = dbWrite.getConnection()) {
				Long id_proforma = Long.parseLong(form.get("id_proforma"));
				String desde = form.get("fechaDesde");
				String hasta = form.get("fechaHasta");
				Proforma proforma = Proforma.find(con, s.baseDato, id_proforma);
				Long id_guia = (long)0;
				String rs = ApiSapConconcreto.genera(con, s.baseDato, proforma.jsonGenerado, ws, id_proforma, id_guia);
				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "proforma", id_proforma, "update", "hace envio de factura API SAP Conconcreto nro: "+proforma.getId());
				rs = rs.replace("\r", "").replace("\n", "");
				return ok(mensajes.render("/proformaListaGet/"+desde+","+hasta,rs ));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result generaProformaWebMaximise(Http.Request request){
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
			return ok(mensajes.render("/home/",msgErrorFormulario));
		}else {
			try (Connection con = dbWrite.getConnection()) {
				Long id_proforma = Long.parseLong(form.get("id_proforma").trim());
				String sucurMaximise = form.get("sucursalMaximise").trim();
				String codigoMaximise = form.get("codigoMaximise").trim();
				String desde = form.get("fechaDesde");
				String hasta = form.get("fechaHasta");
				String ware_code = "BSTGO";
				if (sucurMaximise.equals("2")) {
					ware_code = "BANTO";
				}
				String part_code = "VT-001";
				String desc_text = "Arriendo Andamios";
				if (codigoMaximise.equals("VT-002")) {
					part_code = "VT-002";
					desc_text = "Arriendo Encofrados";
				}
				Proforma proforma = Proforma.find(con, s.baseDato, id_proforma);
				EmisorTributario emisorTributario = EmisorTributario.find(con, s.baseDato);
				String xmlEncode = proforma.getJsonGenerado();
				xmlEncode = xmlEncode.replace("colocaPartCode", part_code);
				xmlEncode = xmlEncode.replace("colocaWareCode", ware_code);
				xmlEncode = xmlEncode.replace("colocaDescText", desc_text);
				Proforma.updateJsonApi(con, s.baseDato, id_proforma, xmlEncode);
				String rs = WebMaximise.generaFactura(con, s.baseDato, xmlEncode, ws, emisorTributario, id_proforma);
				Long nroInterno = Long.parseLong(rs);
				Proforma.updateNroFiscal(con, s.baseDato, id_proforma, nroInterno.toString());
				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "proforma", id_proforma, "update", "hace envio de factura  WS MAXIMISE nro: " + proforma.getId());
				rs = "Orden enviada a Maximise";
				rs = rs.replace("\r", "").replace("\n", "");
				return ok(mensajes.render("/proformaListaGet/" + desde + "," + hasta, rs));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result downFacturaMaximise(Long nroIntOrden, Http.Request request, String desde, String hasta){
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
			return ok(mensajes.render("/home/",msgErrorFormulario));
		}else {
			try (Connection con = dbWrite.getConnection()) {
				EmisorTributario emisorTributario = EmisorTributario.find(con, s.baseDato);
				String nroIntFactura = WebMaximise.downOrderMaximise(con, s.baseDato, ws, emisorTributario, nroIntOrden);
				if (nroIntFactura.equals("DTE aun no emitido")) {
					return ok(mensajes.render("/proformaListaGet/" + desde + "," + hasta, "DTE aun no emitido"));
				} else {
					File file = WebMaximise.downFacturaMaximise(con, s.baseDato, nroIntFactura, ws, emisorTributario);
					if (file != null) {
						return ok(file, false, Optional.of(nroIntFactura + "_FacturaInterna.pdf"));
					} else {
						return ok(mensajes.render("/proformaListaGet/" + desde + "," + hasta, "Documento aun no ha sido enviado al portal o inexistente."));
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

	public Result generaFacturaIConstruye(Http.Request request){
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
			return ok(mensajes.render("/home/",msgErrorFormulario));
		}else {
			try (Connection con = dbWrite.getConnection()) {
				Long id_proforma = Long.parseLong(form.get("id_proforma").trim());
				String desde = form.get("fechaDesde");
				String hasta = form.get("fechaHasta");
				Proforma proforma = Proforma.find(con, s.baseDato, id_proforma);
				String xml = proforma.getJsonGenerado();
				String rs = WebIConstruye.generaDte(con, s.baseDato, xml, ws, (long)0, id_proforma);
				return ok(mensajes.render("/proformaListaGet/"+desde+","+hasta,rs));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result downFacturaIconstruye(Http.Request request){
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
			return ok(mensajes.render("/home/",msgErrorFormulario));
		}else {
			try (Connection con = dbRead.getConnection()) {
				Long id_proforma = Long.parseLong(form.get("id_proforma").trim());
				Proforma proforma = Proforma.find(con, s.baseDato, id_proforma);
				String rsBody = proforma.getResponse();
				int inipdf64 = rsBody.indexOf("<a:PdfDte>") + 10;
				int finpdf64 = rsBody.indexOf("</a:PdfDte>");
				String pdf64 = rsBody.substring(inipdf64,finpdf64);
				File file = TempFile.createTempFile("tmp","null");
				try (FileOutputStream fos = new FileOutputStream(file)) {
					byte[] bytes = Base64.getDecoder().decode(pdf64);
					fos.write(bytes);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return ok(file,false,Optional.of("factura_"+proforma.getNroFiscal()+".pdf"));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result generaProformaApiRelBase(Http.Request request) {
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
		} else {
			try (Connection con = dbWrite.getConnection()) {
				Long id_proforma = Long.parseLong(form.get("id_proforma"));
				String desde = form.get("fechaDesde");
				String hasta = form.get("fechaHasta");
				Map<String, String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String, String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				Proforma proforma = Proforma.find(con, s.baseDato, id_proforma);
				String json = proforma.getJsonGenerado();
				Map<String, String> mapReferencias = TipoReferencia.mapAllCodVsConcep(con, s.baseDato);
				ObjectMapper objectMapper = new ObjectMapper();
				String jsonRefer = objectMapper.writeValueAsString(mapReferencias);
				return ok(generaProformaApiRelBase.render(mapeoDiccionario, mapeoPermiso, userMnu, json, jsonRefer, id_proforma, desde, hasta));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result generaProformaApiRelBase2(Http.Request request){
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		FormFactura form = formFactory.form(FormFactura.class).withDirectFieldAccess(true).bindFromRequest(request).get();
		if (form.id_proforma == null) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgErrorFormulario));
		}else {
			try (Connection con = dbWrite.getConnection()) {
				Proforma proforma = Proforma.find(con, s.baseDato, form.id_proforma);
				String json = proforma.getJsonGenerado();
				ObjectMapper objectMapper = new ObjectMapper();
				JsonNode jsonNode = objectMapper.readTree(json);
				ArrayNode newReferences = objectMapper.createArrayNode();
				int cantRef = 0;
				try {
					cantRef = form.tpoDocRef.size();
				} catch (Exception e) {
				}
				if (cantRef > 0) {
					if (!jsonNode.has("references")) {
						((ObjectNode) jsonNode).set("references", objectMapper.createArrayNode());
					}
					for (int i = 0; i < cantRef; i++) {
						if (!form.folioRef.get(i).trim().equals("")) {
							ObjectNode aux = objectMapper.createObjectNode();
							aux.put("reference_id", form.tpoDocRef.get(i));
							aux.put("folio_ref", form.folioRef.get(i));
							aux.put("date_ref", form.fchRef.get(i));
							aux.put("razon_ref", form.razonRef.get(i));
							newReferences.add(aux);
						}
					}
					((ObjectNode) jsonNode).set("references", newReferences);
				} else {
					if (jsonNode.has("references")) {
						((ObjectNode) jsonNode).remove("references");
					}
				}
				String comentarios = form.sol_observaciones;
				comentarios = comentarios.replace("\r", "\\r").replace("\n", "\\n");
				((ObjectNode) jsonNode).put("comment", comentarios);
				if (Proforma.updateJsonApi(con, s.baseDato, proforma.id, jsonNode.toString())) {
					EmisorTributario emisor = EmisorTributario.find(con, s.baseDato);
					int folio_dte = ApiRelBase.generaDTE(con, s.baseDato, emisor, json, ws, (long) 0, form.id_proforma);
					String desdeAAMMDD = Fechas.AAMMDD(form.fechaDesde);
					String hastaAAMMDD = Fechas.AAMMDD(form.fechaHasta);
					if (folio_dte > 0) {
						Proforma.updateNroFiscal(con, s.baseDato, form.id_proforma, "" + folio_dte);
						String retorno = "/proformaListaGet/" + desdeAAMMDD + "," + hastaAAMMDD;
						Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "proforma", (long) 0, "send", "envia DTE a RelBase nro: " + folio_dte);
						return ok(mensajes.render(retorno, "DTE enviado a RelBase con exito"));
					}
					String retorno = "/proformaListaGet/" + desdeAAMMDD + "," + hastaAAMMDD;
					return ok(mensajes.render(retorno, "ERROR: DTE rechazado por RelBase, revise si el rut del cliente existe tanto en RelBase como en Mada."));
				} else {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
					return ok(mensajes.render("/home/", msgReport));
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

	public Result validarClienteAjax(Http.Request request){
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
				Long id_cliente = Long.parseLong(form.get("id_cliente").trim());
				Cliente cliente = Cliente.find(con, s.baseDato, id_cliente);
				EmisorTributario emisor = EmisorTributario.find(con, s.baseDato);
				String rutCliente = cliente.getRut().replaceAll("[,\\.\\s]", "").toUpperCase();
				if (rutCliente.length() > 1) {
					rutCliente = rutCliente.substring(0, rutCliente.length() - 1) + "-" + rutCliente.charAt(rutCliente.length() - 1);
					rutCliente = "query="+rutCliente;
				}
				int idRelBase = ApiRelBase.consultaIdCliente(emisor, ws, rutCliente);
				if(idRelBase == 0) {
					return ok("0");
				}else {
					return ok(""+idRelBase);
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

	public Result generaProformaApiSapSchwager(Http.Request request){
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
				Long id_proforma = Long.parseLong(form.get("id_proforma").trim());
				String desde = form.get("fechaDesde");
				String hasta = form.get("fechaHasta");
				Proforma proforma = Proforma.find(con, s.baseDato, id_proforma);
				EmisorTributario emisor = EmisorTributario.find(con, s.baseDato);
				String json = proforma.getJsonGenerado();
				String rs = ApiSapSchwager.generaDteFactura(con, s.baseDato, emisor, json, ws, proforma.getId());
				if( ! rs.contains("ERROR:")) {
					String folioNumber = rs;
					Proforma.updateNroFiscal(con, s.baseDato, id_proforma, "Fact: "+folioNumber);
					rs = "DTE enviado a SAP con exito";
				}
				return ok(mensajes.render("/proformaListaGet/"+desde+","+hasta, rs));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result proformaElimina(Http.Request request) {
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
				Long id_proforma = Long.parseLong(form.get("id_proforma"));
				String desde = form.get("fechaDesde");
				String hasta = form.get("fechaHasta");
				Proforma.eliminaProforma(con, s.baseDato, id_proforma);
				return ok(mensajes.render("/proformaListaGet/"+desde+","+hasta,"Proforma nro: "+id_proforma+" eliminada" ));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	//====================================================================================
	// MNU proformaHaceAjustes   Reportes/Proforma/Hacer Ajustes EP-Proforma
	//====================================================================================

	public Result ajustesEp(Http.Request request) {
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
		if(mapeoPermiso.get("proformaHaceAjustes")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()) {
			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
			List<List<String>> lista = BodegaEmpresa.listaAllBodVigExtFiltClientesVig(con, s.baseDato, permisoPorBodega, s.aplicaPorSucursal, s.id_sucursal);
			return ok(ajustesEp.render(mapeoDiccionario,mapeoPermiso,userMnu,lista));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result ajustesEpListado(Http.Request request) {
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
			try (Connection con = dbRead.getConnection()) {
				Long id_bodega = Long.parseLong(form.get("id_bodega"));
				Map<String, String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String, String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				List<AjustesEP> lista = AjustesEP.allPorBodega(con, s.baseDato, id_bodega, s.aplicaPorSucursal, s.id_sucursal);
				BodegaEmpresa bodegaEmpresa = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodega);
				return ok(ajustesEpListado.render(mapeoDiccionario, mapeoPermiso, userMnu, lista, bodegaEmpresa));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result ajusteGrabaPDF(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		Http.MultipartFormData<TemporaryFile> body = request.body().asMultipartFormData();
		Http.MultipartFormData.FilePart<TemporaryFile> docAdjunto = body.getFile("docAdjunto");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgErrorFormulario));
		}else {
			try (Connection con = dbWrite.getConnection()) {
				Long id_ajuste = Long.parseLong(form.get("id_ajuste"));
				String path = "0";
				if (docAdjunto != null) {
					String nombreArchivoSinExtencion = "Ajuste-ID-" + id_ajuste;
					path = Archivos.grabarArchivos(docAdjunto, s.baseDato, nombreArchivoSinExtencion);
					AjustesEP.modifyPDF(con, s.baseDato, path, id_ajuste);
				}
				AjustesEP ajuste = AjustesEP.find(con, s.baseDato, id_ajuste, s.aplicaPorSucursal, s.id_sucursal);
				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "ajustesEP", id_ajuste, "update", "agrega documento a ajusteEP en bodega "+ajuste.bodegaEmpresa );
				return (ajustesEpListado(request));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result ajustesEpModificar(Http.Request request) {
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
			try (Connection con = dbRead.getConnection()) {
				Long id_ajuste = Long.parseLong(form.get("id_ajuste"));
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				AjustesEP ajusteEp = AjustesEP.find(con, s.baseDato, id_ajuste, s.aplicaPorSucursal, s.id_sucursal);
				List<TipoAjustes> listAjustes = TipoAjustes.all(con, s.baseDato);
				List<TipoAjustesVenta> listAjustesVenta = TipoAjustesVenta.all(con, s.baseDato);
				Long numDec = (long) Moneda.numeroDecimalxId(con, s.baseDato, "1");
				return ok(ajustesEpModificar.render(mapeoDiccionario,mapeoPermiso,userMnu,ajusteEp,listAjustes,listAjustesVenta,numDec));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result ajustesEpUpdate(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		AjustesEP form = formFactory.form(AjustesEP.class).withDirectFieldAccess(true).bindFromRequest(request).get();
		if (form.id == null) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgErrorFormulario));
		}else {
			try (Connection con = dbWrite.getConnection()){
				if(AjustesEP.update(con, s.baseDato, form)) {
					AjustesEP ajuste = AjustesEP.find(con, s.baseDato, form.id, s.aplicaPorSucursal, s.id_sucursal);
					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "ajustesEP", form.id, "update", "modifica ajuste de ep en bodega "+ajuste.bodegaEmpresa);
					return (ajustesEpListado(request));
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

	public Result ajustesEpEliminar(Http.Request request) {
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
				Long id_ajuste = Long.parseLong(form.get("id_ajuste"));
				if (AjustesEP.delete(con, s.baseDato, id_ajuste)) {
					AjustesEP ajuste = AjustesEP.find(con, s.baseDato, id_ajuste, s.aplicaPorSucursal, s.id_sucursal);
					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "ajustesEP", id_ajuste, "delete", "elimina ajuste de ep en bodega " + ajuste.bodegaEmpresa);
					return (ajustesEpListado(request));
				} else {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
					return ok(mensajes.render("/home/", msgReport));
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

	public Result ajustesEpNuevo(Http.Request request) {
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
			try (Connection con = dbRead.getConnection()) {
				Long id_bodega = Long.parseLong(form.get("id_bodega"));
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodega);
				Fechas hoy = Fechas.hoy();
				List<TipoAjustes> listAjustes = TipoAjustes.all(con, s.baseDato);
				List<TipoAjustesVenta> listAjustesVenta = TipoAjustesVenta.all(con, s.baseDato);
				Long numDec = (long) Moneda.numeroDecimalxId(con, s.baseDato, "1");
				Moneda moneda = Moneda.find(con, s.baseDato, (long)1);
				return ok(ajustesEpNuevo.render(mapeoDiccionario,mapeoPermiso,userMnu,bodega,hoy,listAjustes,listAjustesVenta,numDec,moneda));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result ajustesEpGrabar(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		AjustesEP form = formFactory.form(AjustesEP.class).withDirectFieldAccess(true).bindFromRequest(request).get();
		if (form.id_bodegaEmpresa == null) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgErrorFormulario));
		}else {
			try (Connection con = dbWrite.getConnection()) {
				if(AjustesEP.create(con, s.baseDato, form)) {
					BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, form.id_bodegaEmpresa);
					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "ajustesEP", (long)0, "create", "agrega nuevo ajuste de ep en bodega "+bodega.getNombre());
					return (ajustesEpListado(request));
				}else {
					return (ajustesEpListado(request));
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


	//====================================================================================
	// MNU proformaListaAjustes   Reportes/Proforma/Reporte Ajustes X Bodegas/proyecto
	//====================================================================================

	public Result ajustesEpRpt(Http.Request request) {
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
		if(mapeoPermiso.get("proformaListaAjustes")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()) {
			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
			permisoPorBodega = permisoPorBodega.replace("movimiento", "bodegaEmpresa");
			permisoPorBodega = permisoPorBodega.replace("id_bodegaEmpresa", "id");
			List<List<String>> lista = BodegaEmpresa.listaAllBodegasVigentesExternasConAjustes(con, s.baseDato, permisoPorBodega, s.aplicaPorSucursal, s.id_sucursal);
			return ok(ajustesEpRpt.render(mapeoDiccionario,mapeoPermiso,userMnu,lista));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result ajustesEpRptDetalle(Http.Request request) {
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
			try (Connection con = dbRead.getConnection()) {
				Long id_bodega = Long.parseLong(form.get("id_bodega"));
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				List<AjustesEP> lista = AjustesEP.allPorBodega(con, s.baseDato, id_bodega, s.aplicaPorSucursal, s.id_sucursal);
				BodegaEmpresa bodegaEmpresa = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodega);
				return ok(ajustesEpRptDetalle.render(mapeoDiccionario,mapeoPermiso,userMnu,lista,bodegaEmpresa));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	//====================================================================================
	// MNU proformaAjustesPorPeriodo   Reportes/Proforma/Reporte Ajustes X Periodo
	//====================================================================================

	public Result ajustesPeriodoEpRpt1(Http.Request request) {
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
		if(mapeoPermiso.get("proformaAjustesPorPeriodo")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try {
			Fechas hoy = Fechas.hoy();
			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			return ok(ajustesPeriodoEpRpt1.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result ajustesPeriodoEpRpt2(Http.Request request) {
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
			try (Connection con = dbRead.getConnection()) {
				String fechaDesde = form.get("fechaDesde").trim();
				String fechaHasta = form.get("fechaHasta").trim();
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
				permisoPorBodega = permisoPorBodega.replace("movimiento", "bodegaEmpresa");
				permisoPorBodega = permisoPorBodega.replace("id_bodegaEmpresa", "id");
				List<AjustesEP> lista = AjustesEP.allPorPeriodos(con, s.baseDato, fechaDesde, fechaHasta, permisoPorBodega, s.aplicaPorSucursal, s.id_sucursal);
				return ok(ajustesPeriodoEpRpt2.render(mapeoDiccionario,mapeoPermiso,userMnu,lista, Fechas.DDMMAA(fechaDesde), Fechas.DDMMAA(fechaHasta)));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}


	//====================================================================================
	// MNU reportFacturaDetalleProyecto   Reportes/Proforma/Ventas por Periodo
	//====================================================================================

	public Result reportVentasPorPeriodo0(Http.Request request) {
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
		if(mapeoPermiso.get("reportVentasPorPeriodo")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()) {
			Fechas hoy = Fechas.hoy();
			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			TasasCambio tasas = TasasCambio.allDeUnaFecha(con, s.baseDato, mapeoDiccionario.get("pais"),hasta);
			return ok(reportVentasPorPeriodo0.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta, tasas));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result reportVentasPorPeriodo1(Http.Request request) {
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
			try (Connection con = dbRead.getConnection()) {
				String desdeAAMMDD = form.get("fechaDesde").trim();
				String hastaAAMMDD = form.get("fechaHasta").trim();
				Double uf = Double.parseDouble(form.get("uf").replaceAll(",", "").trim());
				Double usd = Double.parseDouble(form.get("usd").replaceAll(",", "").trim());
				Double eur = Double.parseDouble(form.get("eur").replaceAll(",", "").trim());
				Map<Long,Double> tasas = new HashMap<Long,Double>();
				tasas.put((long)1, (double) 1); 	// 'Peso Chileno', 'CLP', '0'
				tasas.put((long)2, usd); 			// 'Dólar', 'USD', '2'
				tasas.put((long)3, eur); 			// 'Euro', 'EUR', '3'
				tasas.put((long)4, uf); 			// 'Unidad Fomento', 'UF', '4'
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				List<List<String>> datos = ReportVentas.ventasPorPeriodo(con, s.baseDato, desdeAAMMDD, hastaAAMMDD, tasas);
				return ok(reportVentasPorPeriodo1.render(mapeoDiccionario,mapeoPermiso,userMnu,datos,desdeAAMMDD,hastaAAMMDD, usd, eur, uf,
						Fechas.DDMMAA(desdeAAMMDD), Fechas.DDMMAA(hastaAAMMDD)));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result reportVentasPorPeriodo1Excel(Http.Request request) {
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
				String desdeAAMMDD = form.get("fechaDesde").trim();
				String hastaAAMMDD = form.get("fechaHasta").trim();
				Double uf = Double.parseDouble(form.get("uf").replaceAll(",", "").trim());
				Double usd = Double.parseDouble(form.get("usd").replaceAll(",", "").trim());
				Double eur = Double.parseDouble(form.get("eur").replaceAll(",", "").trim());
				Map<Long,Double> tasas = new HashMap<Long,Double>();
				tasas.put((long)1, (double) 1); 	// 'Peso Chileno', 'CLP', '0'
				tasas.put((long)2, usd); 			// 'Dólar', 'USD', '2'
				tasas.put((long)3, eur); 			// 'Euro', 'EUR', '3'
				tasas.put((long)4, uf); 			// 'Unidad Fomento', 'UF', '4'
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				List<List<String>> datos = null;
				try (Connection con = dbRead.getConnection()) {
					datos = ReportVentas.ventasPorPeriodo(con, s.baseDato, desdeAAMMDD, hastaAAMMDD, tasas);
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				}
				File file = ReportVentas.reportVentasPorPeriodo1Excel(s.baseDato, mapeoDiccionario, datos, desdeAAMMDD, hastaAAMMDD);
				return ok(file,false,Optional.of("ReportVentasPorPeriodo.xlsx"));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	//====================================================================================
	// MNU reportesHOHE   Reportes/Proforma/ SOLO HOHE
	//====================================================================================

	public Result hoheReportTodo0(Http.Request request) {
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
		if(mapeoPermiso.get("reportesHOHE")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try {
			Fechas hoy = Fechas.hoy();
			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			return ok(hoheReportTodo0.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result hoheReportTodo(Http.Request request) {
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
				String fechaDesde = form.get("fechaDesde").trim();
				String fechaHasta = form.get("fechaHasta").trim();
				List<List<String>> lista = null;
				try (Connection con = dbRead.getConnection()) {
					lista = ReportHohe.datos(con, s.baseDato, fechaDesde, fechaHasta);
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				}
					InputStream formato = Archivos.leerArchivo("formatos/hoheReportTodo.xlsx");
					File file = ReportHohe.reporteHoheExcel(fechaDesde, fechaHasta, lista, formato);
					return ok(file,false,Optional.of("ReporteMatrizTodo.xlsx"));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result hoheTodoResumen0(Http.Request request) {
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
		if(mapeoPermiso.get("reportesHOHE")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try {
			Fechas hoy = Fechas.hoy();
			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			return ok(hoheTodoResumen0.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result hoheTodoResumen(Http.Request request) {
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
				List<List<String>> lista = null;
				String fechaDesde = null;
				String fechaHasta = null;
				try (Connection con = dbRead.getConnection()) {
					fechaDesde = form.get("fechaDesde").trim();
					fechaHasta = form.get("fechaHasta").trim();
					lista = ReportHohe.datosResumen(con, s.baseDato, fechaDesde, fechaHasta);
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				}
				InputStream formato = Archivos.leerArchivo("formatos/hoheReportResumen.xlsx");
				File file = ReportHohe.reporteHoheResumenExcel(fechaDesde, fechaHasta, lista, formato);
				return ok(file,false,Optional.of("ReporteMatrizResumen.xlsx"));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result hoheMatrizVerticalInventario0(Http.Request request) {
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
		if(mapeoPermiso.get("reportesHOHE")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try {
			Fechas hoy = Fechas.hoy();
			return ok(hoheMatrizVerticalInventario0.render(mapeoDiccionario,mapeoPermiso,userMnu,hoy.getFechaStrAAMMDD()));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result hoheMatrizVerticalInv0Coti(Http.Request request) {
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
		if(mapeoPermiso.get("reportesHOHE")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try {
			Fechas hoy = Fechas.hoy();
			return ok(hoheMatrizVerticalInv0Coti.render(mapeoDiccionario,mapeoPermiso,userMnu,hoy.getFechaStrAAMMDD()));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result hoheMatrizVerticalInventario(Http.Request request) {
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
			try (Connection con = dbRead.getConnection()) {
				String fechaCorte = form.get("fechaCorte").trim();
				InputStream formato = Archivos.leerArchivo("formatos/hoheReporteInventarioMatriz.xlsx");
				File file = ReportHohe.listaMatrizEquiposHOHE0(con, s.baseDato, fechaCorte, formato);
				return ok(file,false,Optional.of("ReporteInventarioMatrizVertical.xlsx"));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result hoheMatrizVerticalInvCoti(Http.Request request) {
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
			String fechaCorte = form.get("fechaCorte").trim();
			try (Connection con = dbRead.getConnection()) {
				InputStream formato = Archivos.leerArchivo("formatos/hoheReporteInventarioMatriz.xlsx");
				File file = ReportHohe.listaMatrizEquiposHOHE0Coti(con, s.baseDato, fechaCorte, formato);
				return ok(file,false,Optional.of("ReporteInventarioMatrizVertical.xlsx"));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result hoheEstadoCotiOt(Http.Request request) {
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
		if(mapeoPermiso.get("reportesHOHE")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()) {
			List<List<String>> datos = ReportHohe.estadoOtDespachos(con, s.baseDato);
			return ok(hoheEstadoCotiOt.render(mapeoDiccionario,mapeoPermiso,userMnu,datos));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result hoheEstadoCotiOtExcel(Http.Request request) {
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
				List<List<String>> datos = null;
				try (Connection con = dbRead.getConnection()) {
					datos = ReportHohe.estadoOtDespachos(con, s.baseDato);
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				}
				InputStream formato = Archivos.leerArchivo("formatos/hoheReporteEstadoCotiOt.xlsx");
				File file = ReportHohe.estadoCotiOtExcel(datos, formato);
				return ok(file,false,Optional.of("ReporteEstadoCotiOt.xlsx"));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result hoheEstadoCotiSinOt(Http.Request request) {
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
		if(mapeoPermiso.get("reportesHOHE")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<List<String>> datos = ReportHohe.estadoCotiConfYnoConfSinOt(con, s.baseDato);
			return ok(hoheEstadoCotiSinOt.render(mapeoDiccionario,mapeoPermiso,userMnu,datos));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result hoheEstadoCotiSinOtExcel(Http.Request request) {
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
				List<List<String>> datos = null;
				try (Connection con = dbRead.getConnection()) {
					datos = ReportHohe.estadoCotiConfYnoConfSinOt(con, s.baseDato);
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				}
				InputStream formato = Archivos.leerArchivo("formatos/hoheReporteEstadoCotiSinOt.xlsx");
				File file = ReportHohe.estadoCotiSinOtExcel(datos, formato);
				return ok(file,false,Optional.of("ReporteEstadoCotiSinOt.xlsx"));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}



	//====================================================================================
	// MNU reportesDOM   Reportes/Proforma/ SOLO DOM
	//====================================================================================

	public Result domStockDiarioExcel0(Http.Request request) {
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
		if(mapeoPermiso.get("reportesDOM")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try {
			Fechas hoy = Fechas.hoy();
			Fechas ayer = Fechas.addDias(hoy.getFechaCal(), -1);
			return ok(domStockDiarioExcel0.render(mapeoDiccionario,mapeoPermiso,userMnu,ayer.getFechaStrAAMMDD()));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}

	public Result domStockDiarioExcel1(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		if(mapeoPermiso.get("reportesDOM")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgErrorFormulario));
		}else {
			try {
				String fechaCorte = form.get("fechaCorte").trim();
				List<List<String>> listEquip = new ArrayList<List<String>>(); // lista con id, cod, equipo, kg, m2
				Map<String,Long> mapCantStock = new HashMap<String,Long>(); // map idBodega_idEquip vs stock
				Map<String,Long> mapCantArr = new HashMap<String,Long>(); // map idSucursal_idEquip vs cantidad en arriendo
				Map<String,Long> mapPorDespachar = new HashMap<String,Long>(); // map idSucursal_idEquip vs cant por desp de OT confirmadas
				Map<Long,Double> mapMediaMovil = new HashMap<Long,Double>(); // map idEquipo vs precioArr movil
				Map<String,Long> mapSumCantPorDias = null;
				List<Double> listPrecios = null;
				Map<String,List<Double>> mapListaPrecios = null;
				try (Connection con = dbRead.getConnection()) {
					String query = String.format(
							"select"
							+ " movimiento.id_bodegaEmpresa,"
							+ " movimiento.id_equipo,"
							+ " if(sum(if(movimiento.id_tipoMovimiento=1,1,-1)*movimiento.cantidad)<0,0,"
							+ " sum(if(movimiento.id_tipoMovimiento=1,1,-1)*movimiento.cantidad)) as cantidad"
							+ " from `%s`.movimiento"
							+ " left join `%s`.guia on guia.id = movimiento.id_guia"
							+ " where movimiento.id_bodegaEmpresa in (2, 404, 405, 406)"
							+ " and movimiento.esVenta = 0 and (guia.fecha <= ? || movimiento.fecha_factura <= ? || movimiento.fecha_actaBaja <= ?)"
							+ " group by movimiento.id_bodegaEmpresa, movimiento.id_equipo;", s.baseDato, s.baseDato);
					try (PreparedStatement smt = con.prepareStatement(query)) {
						smt.setString(1, fechaCorte);
						smt.setString(2, fechaCorte);
						smt.setString(3, fechaCorte);
						try (ResultSet rs = smt.executeQuery()) {
							while (rs.next()) {
								String key = rs.getString(1) + "_" + rs.getString(2);
								mapCantStock.put(key, rs.getLong(3));
							}
						}
					}
					query = String.format("select select id, codigo, nombre, kg, m2 from `%s`.equipo where vigente = 1;", s.baseDato);
					try (PreparedStatement smt1 = con.prepareStatement(query)) {
						try (ResultSet rs1 = smt1.executeQuery()) {
							while (rs1.next()) {
								List<String> aux = new ArrayList<String>();
								aux.add(rs1.getString(1)); // 0 id_equipo
								aux.add(rs1.getString(2)); // 1 codigo
								aux.add(rs1.getString(3)); // 2 nombre
								aux.add(rs1.getString(4)); // 3 kg
								aux.add(rs1.getString(5)); // 4 m2
								listEquip.add(aux);
							}
						}
					}
					query = String.format(
							"select auxiliar.id_susucursal, auxiliar.id_equipo, sum(auxiliar.cantidad)"
							+ " from"
							+ "	 ("
							+ "	select"
							+ " bodegaEmpresa.id_sucursal as id_susucursal,"
							+ "	movimiento.id_bodegaEmpresa as id_bodegaEmpresa,"
							+ "	movimiento.id_equipo as id_equipo,"
							+ "	if(sum(if(movimiento.id_tipoMovimiento=1,1,-1)*movimiento.cantidad)<0,0,sum(if(movimiento.id_tipoMovimiento=1,1,-1)*movimiento.cantidad)) as cantidad"
							+ "	from `%s`.movimiento"
							+ "	left join `%s`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa"
							+ " left join `%s`.guia on guia.id = movimiento.id_guia"
							+ "	where bodegaEmpresa.esInterna = 2 and bodegaEmpresa.vigente = 1"
							+ " and movimiento.esVenta = 0 and (guia.fecha <= ?)"
							+ "	group by movimiento.id_bodegaEmpresa, movimiento.id_equipo, bodegaEmpresa.id_sucursal"
							+ "	) as auxiliar"
							+ " group by auxiliar.id_susucursal, auxiliar.id_equipo;", s.baseDato, s.baseDato, s.baseDato);
					try (PreparedStatement smt2 = con.prepareStatement(query)) {
						smt2.setString(1, fechaCorte);
						try (ResultSet rs2 = smt2.executeQuery()) {
							while (rs2.next()) {
								String key = rs2.getString(1) + "_" + rs2.getString(2);
								mapCantArr.put(key, rs2.getLong(3));
							}
						}
					}
					query = String.format(
							" select"
							+ " bodegaEmpresa.id_sucursal,"
							+ " cotizaDetalle.id_equipo, sum(cotizaDetalle.cantidad) - sum(ifnull(otDespachado.cantidadRebajaOt,0)) as porDespachar"
							+ " from `%s`.cotizaDetalle"
							+ " left join `%s`.cotizacion on cotizacion.id = cotizaDetalle.id_cotizacion"
							+ " left join `%s`.ot on ot.id = cotizacion.id_ot"
							+ " left join `%s`.bodegaEmpresa on bodegaEmpresa.id = cotizacion.id_bodegaEmpresa"
							+ " left join `%s`.otDespachado on otDespachado.id_cotizaDetalle = cotizaDetalle.id"
							+ " left Join `%s`.guia on guia.id = otDespachado.id_guia"
							+ " where cotizacion.id_ot > 0 and ot.confirmada = 1 and ot.fechaConfirmada <= ? and (guia.fecha <= ? || guia.fecha is null)"
							+ " group by bodegaEmpresa.id_sucursal, cotizaDetalle.id_equipo"
							+ " having porDespachar > 0;", s.baseDato, s.baseDato, s.baseDato, s.baseDato, s.baseDato, s.baseDato);
					try (PreparedStatement smt3 = con.prepareStatement(query)) {
						smt3.setString(1, fechaCorte);
						smt3.setString(2, fechaCorte);
						try (ResultSet rs3 = smt3.executeQuery()) {
							while (rs3.next()) {
								// sucursal_equipo vs cantidad de equipos pendientes por despachar desde ot por sucursal
								String key = rs3.getString(1) + "_" + rs3.getString(2);
								mapPorDespachar.put(key, rs3.getLong(3));
							}
						}
					}
					mapSumCantPorDias = new HashMap<String,Long>(); // bod_cot_equ vs cant-dias para calculo de promedio 60 dias
					Fechas ini = Fechas.obtenerFechaDesdeStrAAMMDD(fechaCorte);
					ini = Fechas.addMeses(ini.getFechaCal(), -2);
					Fechas fin = Fechas.obtenerFechaDesdeStrAAMMDD(fechaCorte);
					String fechaIni = ini.getFechaStrAAMMDD();
					int dias = Fechas.diasEntreFechas(ini.getFechaCal(), fin.getFechaCal()) + 1;
					query = String.format("select auxiliar.id_bodegaEmpresa, auxiliar.id_cotizacion, auxiliar.id_equipo, sum(auxiliar.cantidad)"
							+ " from ("
							+ "	select "
							+ "	movimiento.id_bodegaEmpresa as id_bodegaEmpresa,"
							+ " movimiento.id_cotizacion as id_cotizacion,"
							+ "	movimiento.id_equipo as id_equipo,"
							+ "	if(sum(if(movimiento.id_tipoMovimiento=1,1,-1) * movimiento.cantidad) < 0, 0 , sum(if(movimiento.id_tipoMovimiento=1,1,-1) * movimiento.cantidad)) as cantidad"
							+ "	from `%s`.movimiento"
							+ "	left join `%s`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa"
							+ "	left join `%s`.guia on guia.id = movimiento.id_guia"
							+ "	where bodegaEmpresa.esInterna = 2 and bodegaEmpresa.vigente = 1 and movimiento.esVenta = 0 and (guia.fecha <= ?)"
							+ "	group by movimiento.id_bodegaEmpresa, movimiento.id_cotizacion, movimiento.id_equipo"
							+ ") as auxiliar"
							+ " group by auxiliar.id_bodegaEmpresa, auxiliar.id_cotizacion, auxiliar.id_equipo;", s.baseDato, s.baseDato, s.baseDato);
					try (PreparedStatement smt4 = con.prepareStatement(query)) {
						smt4.setString(1, fechaIni);
						try (ResultSet rs4 = smt4.executeQuery()) {
							while (rs4.next()) {
								// bod_cot_equ vs cant - dias inventario inicial
								String key = rs4.getString(1) + "_" + rs4.getString(2) + "_" + rs4.getString(3);
								Long aux = mapSumCantPorDias.get(key);
								if (aux != null) {
									aux += rs4.getLong(4) * dias;
									mapSumCantPorDias.put(key, aux);
								} else {
									mapSumCantPorDias.put(key, rs4.getLong(4) * dias);
								}
							}
						}
					}
					query = String.format(
							"select auxiliar.id_bodegaEmpresa, auxiliar.id_cotizacion, auxiliar.id_equipo, sum(auxiliar.cantidad), auxiliar.fecha"
							+ " from ("
							+ "	select "
							+ "	movimiento.id_bodegaEmpresa as id_bodegaEmpresa,"
							+ " movimiento.id_cotizacion as id_cotizacion,"
							+ "	movimiento.id_equipo as id_equipo,"
							+ "	sum(if(movimiento.id_tipoMovimiento=1,1,-1) * movimiento.cantidad) as cantidad,"
							+ "	guia.fecha as fecha"
							+ "	from `%s`.movimiento"
							+ "	left join `%s`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa"
							+ "	left join `%s`.guia on guia.id = movimiento.id_guia"
							+ "	where bodegaEmpresa.esInterna = 2 and bodegaEmpresa.vigente = 1 and movimiento.esVenta = 0 and (guia.fecha > ? and guia.fecha <= ?)"
							+ "	group by guia.fecha, movimiento.id_bodegaEmpresa, movimiento.id_cotizacion, movimiento.id_equipo"
							+ ") as auxiliar"
							+ " group by auxiliar.fecha, auxiliar.id_bodegaEmpresa, auxiliar.id_cotizacion, auxiliar.id_equipo;", s.baseDato, s.baseDato, s.baseDato);
					try (PreparedStatement smt5 = con.prepareStatement(query)) {
						smt5.setString(1, fechaIni);
						smt5.setString(2, fechaCorte);
						try (ResultSet rs5 = smt5.executeQuery()) {
							while (rs5.next()) {
								Fechas auxFech = Fechas.obtenerFechaDesdeStrAAMMDD(rs5.getString(5));
								dias = Fechas.diasEntreFechas(auxFech.getFechaCal(), fin.getFechaCal()) + 1;
								String key = rs5.getString(1) + "_" + rs5.getString(2) + "_" + rs5.getString(3);
								Long aux = mapSumCantPorDias.get(key);
								if (aux != null) {
									aux += rs5.getLong(4) * dias;
									mapSumCantPorDias.put(key, aux);
								} else {
									mapSumCantPorDias.put(key, rs5.getLong(4) * dias);
								}
							}
						}
					}
					// AGRUPO RESULTADOS POR EQUIPO cantPorDia y total suma producto Precio
					mapListaPrecios = ListaPrecio.mapListaPreciosAll(con, s.baseDato);
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				}
				Map<Long,List<Double>> mapPrecios = new HashMap<Long,List<Double>>();
				for (Map.Entry<String, Long> entry : mapSumCantPorDias.entrySet()) {
					// key = id_bodegaEmpresa + id_cotizacion + id_equipo
					String key = entry.getKey();
					String aux[] = key.split("_");
					Long id_equipo = Long.parseLong(aux[2]);
					Long cantPorDias = entry.getValue();
					listPrecios = mapListaPrecios.get(key);
					Double precioDiaUnit = (double) 0;
					if(listPrecios != null) {
						Double precioUnit = listPrecios.get(1);
						Double factor = listPrecios.get(4);
						precioDiaUnit = precioUnit / factor;
					}
					List<Double> precio = mapPrecios.get(id_equipo);
					if(precio != null) {
						precio.set(0, precio.get(0) + cantPorDias);
						precio.set(1, precio.get(1) + cantPorDias * precioDiaUnit);
						mapPrecios.put(id_equipo, precio);
					}else {
						List<Double> aux2 = new ArrayList<Double>();
						aux2.add((double) cantPorDias);
						aux2.add(cantPorDias * precioDiaUnit);
						mapPrecios.put(id_equipo, aux2);
					}
				}
				for (Map.Entry<Long, List<Double>> entry : mapPrecios.entrySet()) {
					Long key = entry.getKey();
					List<Double> val = entry.getValue();
					Double media = (double) 0;
					if(val.get(0) > 0) {
						media = val.get(1)/val.get(0);
					}
					mapMediaMovil.put(key, media);
				}
				List<List<String>> datos = new ArrayList<List<String>>();
				for(List<String> l: listEquip) {
					List<String> aux = new ArrayList<String>();
					Long id_equipo = Long.parseLong(l.get(0));
					Long stockMantStgo = mapCantStock.get("404_"+id_equipo.toString());
					if(stockMantStgo == null) {
						stockMantStgo = (long) 0;
					}
					Long stockDispStgo = mapCantStock.get("2_"+id_equipo.toString());
					if(stockDispStgo == null) {
						stockDispStgo = (long) 0;
					}
					Long pendDespStgo = mapPorDespachar.get("1_"+id_equipo.toString());
					if(pendDespStgo == null) {
						pendDespStgo = (long) 0;
					}
					Long totArrStgo = mapCantArr.get("1_"+id_equipo.toString());
					if(totArrStgo == null) {
						totArrStgo = (long) 0;
					}
					Long stockMantPtoV = mapCantStock.get("406_"+id_equipo.toString());
					if(stockMantPtoV == null) {
						stockMantPtoV = (long) 0;
					}
					Long stockDispPtoV = mapCantStock.get("405_"+id_equipo.toString());
					if(stockDispPtoV == null) {
						stockDispPtoV = (long) 0;
					}
					Long pendDespPtoV = mapPorDespachar.get("2_"+id_equipo.toString());
					if(pendDespPtoV == null) {
						pendDespPtoV = (long) 0;
					}
					Long totArrPtoV = mapCantArr.get("2_"+id_equipo.toString());
					if(totArrPtoV == null) {
						totArrPtoV = (long) 0;
					}
					Double medMovArrUn = mapMediaMovil.get(id_equipo);
					if(medMovArrUn == null) {
						medMovArrUn = (double) 0;
					}
					aux.add(l.get(1));					// 0 N° 1 - Codigo de Articulo
					aux.add(l.get(2));					// 1 N° 2 - Nombre de Articulo
					aux.add(stockMantPtoV.toString());	// 2 N° 52 - Bodega Mantencion Puerto Varas
					aux.add(stockMantStgo.toString());	// 3 N° 63 - Bodega Mantencion Santiago
					aux.add(l.get(4));					// 4 N° 98 - Dimensión en M2 del Artículo
					aux.add(pendDespPtoV.toString());	// 5 N° 101 - Pendientes por despachar Puerto Varas
					aux.add(totArrPtoV.toString());		// 6 N° 102 - En arriendo Puerto Varas
					aux.add(medMovArrUn.toString());	// 7 N° 108 - Valor promedio de arriendo diario últimos 60 días.
					aux.add(pendDespStgo.toString());	// 8 N° 112 - Pendientes por despachar Santiago
					aux.add(stockDispStgo.toString());	// 9 N° 113 - Bodega Disponible Santiago
					aux.add(totArrStgo.toString());		// 10 N° 114 - Total en Arriendo Santiago
					aux.add(stockDispPtoV.toString());	// 11 N° 115 - Bodega Disponible Puerto Varas
					datos.add(aux);
				}
				File tmp = TempFile.createTempFile("tmp","null");
				String path = s.baseDato+"/formatos/DOM_Stock_Detallado.xlsx";
				InputStream formato = Archivos.leerArchivo(path);
				Workbook libro = WorkbookFactory.create(formato);
				formato.close();
				Sheet hoja1 = libro.getSheetAt(0);
				Row row = null;
				Cell cell = null;
				Double auxDbl = (double) 0;
				row = hoja1.getRow(0);
				cell = row.getCell(116);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				Fechas fecha = Fechas.obtenerFechaDesdeStrAAMMDD(fechaCorte);
				cell.setCellValue(fecha.fechaUtil);
				Fechas hoyChile = Fechas.hoyChile();
				cell = row.getCell(118);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(hoyChile.fechaUtil);
				for(int i=0;i<datos.size();i++){
					row = hoja1.createRow(i+1);
					cell = row.createCell(0);
					try {
						auxDbl = Double.parseDouble(datos.get(i).get(0));
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(auxDbl);
					}catch(Exception e) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(datos.get(i).get(0));
					}
					cell = row.createCell(1);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(datos.get(i).get(1));
					cell = row.createCell(51);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					auxDbl = Double.parseDouble(datos.get(i).get(2));
					cell.setCellValue(auxDbl);
					cell = row.createCell(62);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					auxDbl = Double.parseDouble(datos.get(i).get(3));
					cell.setCellValue(auxDbl);
					cell = row.createCell(97);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					auxDbl = Double.parseDouble(datos.get(i).get(4));
					cell.setCellValue(auxDbl);
					cell = row.createCell(100);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					auxDbl = Double.parseDouble(datos.get(i).get(5));
					cell.setCellValue(auxDbl);
					cell = row.createCell(101);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					auxDbl = Double.parseDouble(datos.get(i).get(6));
					cell.setCellValue(auxDbl);
					cell = row.createCell(107);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					auxDbl = Double.parseDouble(datos.get(i).get(7));
					cell.setCellValue(auxDbl);
					cell = row.createCell(111);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					auxDbl = Double.parseDouble(datos.get(i).get(8));
					cell.setCellValue(auxDbl);
					cell = row.createCell(112);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					auxDbl = Double.parseDouble(datos.get(i).get(9));
					cell.setCellValue(auxDbl);
					cell = row.createCell(113);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					auxDbl = Double.parseDouble(datos.get(i).get(10));
					cell.setCellValue(auxDbl);
					cell = row.createCell(114);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					auxDbl = Double.parseDouble(datos.get(i).get(11));
					cell.setCellValue(auxDbl);
				}
				FormulaEvaluator evaluator = libro.getCreationHelper().createFormulaEvaluator();
				evaluator.evaluateAll(); // Recalcular todas las fórmulas en todas las hojas
				// Write the output to a file tmp
				FileOutputStream fileOut = new FileOutputStream(tmp);
				libro.write(fileOut);
				fileOut.close();
				if (tmp != null) {
					return ok(tmp, Optional.of("plantillaFacturasManager.xlsx"));
				} else {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
					return ok(mensajes.render("/home/", msgReport));
				}
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public Result hoheValySubePlantillaNV(Http.Request request) {
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
				String dePaso = form.get("hoheValySubePlantillaNV");
				if (dePaso != null && !dePaso.equals("hoheValySubePlantillaNV")) {
					return ok("{\"status\":false}").as("application/json");
				}
				Archivos aux = formFactory.form(Archivos.class).withDirectFieldAccess(true).bindFromRequest(request).get();
				Http.MultipartFormData.FilePart<TemporaryFile> archivo = aux.file;
				if (archivo != null) {
					File file = Archivos.parseMultipartFormDatatoFile(archivo);
					Workbook libro = WorkbookFactory.create(file);
					Sheet hoja1 = libro.getSheetAt(0);
					Row row = null;
					Cell cell = null;
					List<String> listErr = new ArrayList<String>();
					List<List<String>> listado = new ArrayList<List<String>>();
					// valido datos
					int fila = 1;
					row = hoja1.getRow(fila);
					if (row != null) {
						cell = row.getCell(1);
						Map<String, Cliente> mapClientes = null;
						Map<Long, BodegaEmpresa> mapBodegas = null;
						try (Connection con = dbRead.getConnection()) {
							mapClientes = Cliente.mapAllClientesPorRut(con, s.baseDato);
							mapBodegas = BodegaEmpresa.mapAll(con, s.baseDato);
						} catch (SQLException e) {
							logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
							return ok(mensajes.render("/home/", msgReport));
						} catch (Exception e) {
							logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
							return ok(mensajes.render("/home/", msgReport));
						}
						while (row != null && cell != null) {
							boolean flag = true;
							List<String> auxList = new ArrayList<String>();
							// Valido fechas
							try {
								Date fecha = cell.getDateCellValue();
								Fechas aux1 = Fechas.obtenerFechaDesdeDate(fecha);
								auxList.add(aux1.getFechaStrAAMMDD());
							} catch (Exception e) {
								listErr.add("EN FILA " + (fila + 1) + ": fecha no valida.");
								flag = false;
							}
							// Valido rut
							cell = row.getCell(2);
							if (cell != null) {
								try {
									String rut = cell.getStringCellValue().trim();
									Cliente cliente = mapClientes.get(rut);
									if (cliente != null) {
										auxList.add(rut);
									} else {
										listErr.add("EN FILA " + (fila + 1) + ": RUT no valido, campo obligado.");
										flag = false;
									}
								} catch (Exception e) {
									listErr.add("EN FILA " + (fila + 1) + ": RUT no valido, campo obligado.");
									flag = false;
								}
							} else {
								listErr.add("EN FILA " + (fila + 1) + ": RUT no valido, campo obligado.");
								flag = false;
							}
							// Valido neto
							cell = row.getCell(3);
							if (cell != null) {
								try {
									Double neto = cell.getNumericCellValue();
									if (neto <= (double) 0) {
										listErr.add("EN FILA " + (fila + 1) + ": neto no valido menor o igual que cero.");
										flag = false;
									} else {
										auxList.add(neto.toString());
									}
								} catch (Exception e) {
									listErr.add("EN FILA " + (fila + 1) + ": neto no valido, debe ser un tipo numero obligado.");
									flag = false;
								}
							} else {
								listErr.add("EN FILA " + (fila + 1) + ": neto no valido, debe ser un tipo numero obligado.");
								flag = false;
							}
							// Valido concepto 1
							cell = row.getCell(4);
							if (cell != null) {
								try {
									String concepto1 = cell.getStringCellValue().trim();
									auxList.add(concepto1);
								} catch (Exception e) {
									Double concepto1 = cell.getNumericCellValue();
									auxList.add(concepto1.toString());
								}
							} else {
								listErr.add("EN FILA " + (fila + 1) + ": el concepto 1 esta vacio (es obligatorio).");
								flag = false;
							}
							// agregar el concepto 2
							cell = row.getCell(5);
							if (cell != null) {
								try {
									String concepto2 = cell.getStringCellValue().trim();
									auxList.add(concepto2);
								} catch (Exception e) {
									Double concepto2 = cell.getNumericCellValue();
									auxList.add(concepto2.toString());
								}
							} else {
								auxList.add("");
							}
							// Valido id bodega
							cell = row.getCell(6);
							if (cell != null) {
								try {
									Long id_bodega = (long) cell.getNumericCellValue();
									BodegaEmpresa bodega = mapBodegas.get(id_bodega);
									if (bodega != null) {
										if (id_bodega <= (long) 0) {
											listErr.add("EN FILA " + (fila + 1) + ": el ID BODEGA no es valido obligatorio.");
											flag = false;
										} else {
											auxList.add(id_bodega.toString());
										}
									} else {
										listErr.add("EN FILA " + (fila + 1) + ": el ID BODEGA no es valido obligatorio.");
										flag = false;
									}
								} catch (Exception e) {
									listErr.add("EN FILA " + (fila + 1) + ": el ID BODEGA no es valido obligatorio.");
									flag = false;
								}
							} else {
								listErr.add("EN FILA " + (fila + 1) + ": el ID BODEGA no es valido obligatorio.");
								flag = false;
							}
							if (flag) {
								listado.add(auxList);
							}
							//siguiente linea
							fila++;
							row = hoja1.getRow(fila);
							if (row != null) {
								cell = row.getCell(1);
							} else {
								cell = null;
							}
						}
					} else {
						listErr.add("LA PLANTILLA NO CORRESPONDE");
						return ok("{\"status\":false}").as("application/json");
					}
					// GRABACION Y ENVIO DE NV
					if (listErr.size() > 0) {
						String jsonMsg = Json.toJson(listErr).toString();
						return ok("{\"status\":true,\"error\":true,\"detalle\":" + jsonMsg + "}").as("application/json");
					} else {
						String mailDestino = "";
						try (Connection con0 = dbRead.getConnection()) {
							Usuario usuario = Usuario.findXIdUser(con0, s.baseDato, Long.parseLong(s.id_usuario));
							if (usuario != null) {
								mailDestino = usuario.getEmail().trim().toLowerCase();
							}
						} catch (SQLException e) {
							logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
							return ok(mensajes.render("/home/", msgReport));
						} catch (Exception e) {
							logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
							return ok(mensajes.render("/home/", msgReport));
						}
						MnuReportes.hoheGeneraNVdesdeExcel generar = new MnuReportes.hoheGeneraNVdesdeExcel(listado, s, mailDestino);
						generar.start();
						String msg = "NV en preparación, recibira el resultado al correo:" + mailDestino + ". Tomara varios minutos para recibir el correo";
						return ok("{\"status\":true,\"error\":false,\"msg\":\"" + msg + "\"}").as("application/json");
					}
				} else {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
					return ok(mensajes.render("/home/", msgReport));
				}
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}

	public class hoheGeneraNVdesdeExcel extends Thread {
		List<List<String>> listado;
		Sessiones s;
		String mailDestino;

		public hoheGeneraNVdesdeExcel(List<List<String>> listado, Sessiones s, String mailDestino) {
			super();
			this.listado = listado;
			this.s = s;
			this.mailDestino = mailDestino;
		}

		public void run() {
			try {
				Map<String, Cliente> mapClie = null;
				Map<Long, BodegaEmpresa> mapBodegas = null;
				EmisorTributario emisor = null;
				try (Connection con0 = dbRead.getConnection()) {
					mapClie = Cliente.mapAllClientesPorRut(con0, s.baseDato);
					mapBodegas = BodegaEmpresa.mapAll(con0, s.baseDato);
					emisor = EmisorTributario.find(con0, s.baseDato);
				} catch (SQLException e) {
					String className = this.getClass().getSimpleName();
					String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				} catch (Exception e) {
					String className = this.getClass().getSimpleName();
					String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				}
				List<List<String>> newListado = new ArrayList<List<String>>();
				List<String> resultado = new ArrayList<String>();
				try (Connection con1 = dbWrite.getConnection()) {
					for (List<String> l : listado) {
						String fecha = l.get(0);
						String rutClie = l.get(1);
						String auxNeto = l.get(2);
						Cliente cliente = mapClie.get(rutClie);
						BodegaEmpresa bodega = mapBodegas.get(Long.parseLong(l.get(5)));
						if (cliente != null && bodega != null) {
							Long neto = Math.round(Double.parseDouble(auxNeto));
							Long iva = Math.round(neto * emisor.getTasaIva() / 100);
							Long total = neto + iva;
							Long id_proforma = null;
							String query = query = String.format(
									"insert into  `%s`.proforma"
											+ " (fecha,desde,hasta,id_cliente,neto,iva,total,tipo,id_bodegaEmpresa) VALUES"
											+ " (?,?,?,?,?,?,?,?,?);", s.baseDato);
							try (PreparedStatement smt1 = con1.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
								smt1.setString(1, fecha);
								smt1.setString(2, fecha);
								smt1.setString(3, fecha);
								smt1.setString(4, cliente.getId().toString());
								smt1.setString(5, neto.toString());
								smt1.setString(6, iva.toString());
								smt1.setString(7, total.toString());
								smt1.setString(8, "EXCEL");
								smt1.setString(9, l.get(5));
								smt1.executeUpdate();
								id_proforma = (long) 0;
								try (ResultSet rs1 = smt1.getGeneratedKeys()) {
									if (rs1.next()) {
										id_proforma = rs1.getLong(1);
										l.add(id_proforma.toString());
										l.add(iva.toString());
										l.add(total.toString());
										newListado.add(l);
									}
								}
							}
							String concepto1 = l.get(3);
							String concepto2 = l.get(4);
							ApiManagerDocDoc api = new ApiManagerDocDoc();
							api.rut_empresa = emisor.rut;
							api.tipodocumento = "NV";
							api.num_doc = id_proforma.toString();
							api.fecha_doc = Fechas.DDMMAA(fecha);
							api.fecha_ref = Fechas.DDMMAA(fecha);
							api.rut_cliente = rutClie;
							api.dire_cliente = "LEGAL";
							api.cod_vendedor = bodega.getComercial();
							api.lista_precio = "2";
							api.plazo_pago = "0";
							api.cod_moneda = "CLP";
							api.afecto = neto.toString();
							api.tipo_desc_global = "M";
							api.monto_desc_global = "0";
							api.iva = iva.toString();
							api.total = total.toString();
							String[] bod = bodega.nombre.split(" ");
							String comercial = bodega.getComercial();
							String[] fechaArr = Fechas.AAMMDD(fecha).split("-");
							api.comentario1 = bod[0];
							api.comentario2 = comercial;
							api.comentario3 = fechaArr[1];
							api.comentario4 = fechaArr[0];
							api.comentario5 = "";
							api.modalidad = "";
							api.emitir = "S";
							api.referencia = "";
							List<ApiManagerDocDet> detalle = new ArrayList<ApiManagerDocDet>();
							ApiManagerDocDet det = new ApiManagerDocDet();
							det.cod_producto = "I001";
							det.cantidad = "1";
							det.unidad = "UNS";
							det.precio_unit = neto.toString();
							det.moneda_det = "CLP";
							det.cen_cos = "1";
							det.tipo_desc = "M";
							det.ubicacion = "";
							det.bodega = "";
							det.descrip = concepto1;
							det.stock = "R";
							det.desc_adic = concepto2;
							detalle.add(det);
							api.detalles = detalle;
							String datos = Json.toJson(api).toString();
							Proforma.updateJsonApi(con1, s.baseDato, id_proforma, datos);
							String rs = ApiManagerDocDoc.genera(con1, s.baseDato, datos, ws, id_proforma);
							rs = "NV nro " + id_proforma + ": " + rs;
							resultado.add(rs);
						}
					}
				} catch (SQLException e) {
					String className = this.getClass().getSimpleName();
					String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				} catch (Exception e) {
					String className = this.getClass().getSimpleName();
					String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				}
				try {
					Email email = new Email();
					String asunto = "NV generadas";
					String desde = "desde MADA <informaciones@inqsol.cl>";
					email.setSubject(asunto);
					email.setFrom(desde);
					String body = "<html><body>" +
							" <p>Se generaron las siguientes NV:</p>";
					for (String x : resultado) {
						body += x + "<br>";
					}
					body += " </body></html>";
					email.setBodyHtml(body);
					email.addTo(mailDestino);
					mailerClient.send(email);
				} catch (Exception e) {
					String className = this.getClass().getSimpleName();
					String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				}
			} catch (Exception e) {
				String className = this.getClass().getSimpleName();
				String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			}
		}
	}

	public Result hohePlantillaNV(Http.Request request) {
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
				File tmp = TempFile.createTempFile("tmp", "null");
				String path = "formatos/excel.xlsx";
				InputStream formato = Archivos.leerArchivo(path);
				Workbook libro = WorkbookFactory.create(formato);
				formato.close();
				CellStyle encabezado = libro.createCellStyle();
				encabezado.setBorderBottom(CellStyle.BORDER_THIN);
				encabezado.setBorderTop(CellStyle.BORDER_THIN);
				encabezado.setBorderRight(CellStyle.BORDER_THIN);
				encabezado.setBorderLeft(CellStyle.BORDER_THIN);
				encabezado.setFillPattern(CellStyle.SOLID_FOREGROUND);
				encabezado.setFillForegroundColor((short) 19);
				encabezado.setAlignment(CellStyle.ALIGN_LEFT);
				libro.setSheetName(0, "PLANTILLA");
				Sheet hoja1 = libro.getSheetAt(0);
				Row row = null;
				Cell cell = null;
				row = hoja1.createRow(0);
				cell = row.createCell(1);
				cell.setCellStyle(encabezado);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("FECHA");
				cell = row.createCell(2);
				cell.setCellStyle(encabezado);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("RUT CLIENTE");
				cell = row.createCell(3);
				cell.setCellStyle(encabezado);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("TOTAL NETO");
				cell = row.createCell(4);
				cell.setCellStyle(encabezado);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("CONCEPTO 1");
				cell = row.createCell(5);
				cell.setCellStyle(encabezado);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("CONCEPTO 2");
				cell = row.createCell(6);
				cell.setCellStyle(encabezado);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("ID_BODEGA");
				libro.setSheetName(1, "AUXILIAR");
				Sheet hoja2 = libro.getSheetAt(1);
				List<Cliente> listCliente = null;
				List<BodegaEmpresa> listBodegas = null;
				try (Connection con = dbRead.getConnection()) {
					listCliente = Cliente.all(con, s.baseDato);
					listBodegas = BodegaEmpresa.allExternas(con, s.baseDato);
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				}
				int largo = listCliente.size();
				if (largo < listBodegas.size()) {
					largo = listBodegas.size();
				}
				largo = largo + 20;

				for (int i = 0; i < largo; i++) {
					hoja2.createRow(i);
				}
				hoja2.setColumnWidth(1, 15 * 1000);
				hoja2.setColumnWidth(5, 15 * 1000);
				row = hoja2.getRow(0);
				cell = row.createCell(0);
				cell.setCellStyle(encabezado);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("RUT");
				cell = row.createCell(1);
				cell.setCellStyle(encabezado);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("CLIENTE");
				for (int i = 0; i < listCliente.size(); i++) {
					row = hoja2.getRow(i + 1);
					cell = row.createCell(0);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(listCliente.get(i).getRut());
					cell = row.createCell(1);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(listCliente.get(i).getNickName());
				}
				row = hoja2.getRow(0);
				cell = row.createCell(3);
				cell.setCellStyle(encabezado);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("ID_BODEGA");
				cell = row.createCell(4);
				cell.setCellStyle(encabezado);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("COMERCIAL");
				cell = row.createCell(5);
				cell.setCellStyle(encabezado);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("NOMBRE BODEGA");
				cell = row.createCell(6);
				cell.setCellStyle(encabezado);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("ID_BODEGA");
				int cont = 1;
				for (int i = 0; i < listBodegas.size(); i++) {
					if (listBodegas.get(i).getComercial().length() > 0) {
						row = hoja2.getRow(cont);
						cell = row.createCell(3);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(listBodegas.get(i).getId());
						cell = row.createCell(4);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(listBodegas.get(i).getComercial());
						cell = row.createCell(5);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(listBodegas.get(i).getNombre());
						cell = row.createCell(6);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(listBodegas.get(i).getId());
						cont++;
					}
				}
				// Write the output to a file tmp
				FileOutputStream fileOut = new FileOutputStream(tmp);
				libro.write(fileOut);
				fileOut.close();
				if (tmp != null) {
					return ok(tmp, Optional.of("plantillaFacturasManager.xlsx"));
				} else {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
					return ok(mensajes.render("/home/", msgReport));
				}
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}


}





