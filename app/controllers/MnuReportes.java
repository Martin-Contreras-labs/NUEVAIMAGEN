package controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.poi.util.TempFile;

import controllers.HomeController.Sessiones;
import models.api.ApiManagerDocDoc;
import models.api.ApiNuboxDocDoc;
import models.api.ApiSapConconcreto;
import models.api.WebIConstruye;
import models.api.WebMaximise;
import models.calculo.Calc_AjustesEP;
import models.calculo.Calc_BodegaEmpresa;
import models.calculo.Calc_Precio;
import models.calculo.Inventarios;
import models.calculo.ModCalc_GuiasPer;
import models.calculo.ModCalc_InvInicial;
import models.calculo.ModeloCalculo;
import models.forms.FormFactura;
import models.forms.FormGrafico;
import models.reports.ReportBodegas;
import models.reports.ReportEjecutivos;
import models.reports.ReportExcedentes;
import models.reports.ReportFacturaConsolidado;
import models.reports.ReportFacturas;
import models.reports.ReportGerenciales;
import models.reports.ReportGraficos;
import models.reports.ReportHohe;
import models.reports.ReportInventarios;
import models.reports.ReportKilos;
import models.reports.ReportMovimientos;
import models.reports.ReportTrazabilidades;
import models.reports.ReportVentas;
import models.tables.AjustesEP;
import models.tables.BodegaEmpresa;
import models.tables.Cliente;
import models.tables.Compra;
import models.tables.Cotizacion;
import models.tables.EmisorTributario;
import models.tables.Equipo;
import models.tables.Grupo;
import models.tables.Moneda;
import models.tables.Parametros;
import models.tables.Precio;
import models.tables.Proforma;
import models.tables.Proyecto;
import models.tables.Sucursal;
import models.tables.TasasCambio;
import models.tables.TipoAjustes;
import models.tables.TipoAjustesVenta;
import models.tables.TipoBodega;
import models.tables.TipoEstado;
import models.tables.TipoReferencia;
import models.tables.UnidadTiempo;
import models.tables.Usuario;
import models.tables.UsuarioPermiso;
import models.utilities.Archivos;
import models.utilities.DatabaseRead;
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
import viewsMnuReportes.html.*;

public class MnuReportes extends Controller {
	
	
	public static Database dbWrite = HomeController.dbWrite;
	public static DatabaseRead dbRead = HomeController.dbRead;
	public static FormFactory formFactory = HomeController.formFactory;
	public static String msgError = HomeController.msgError;
	public static String msgErrorFormulario = HomeController.msgErrorFormulario;
	public static String msgSinPermiso = HomeController.msgSinPermiso;
	

	
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble0 = new DecimalFormat("#,##0");
	
	private final WSClient ws;
	public final MailerClient mailerClient;
	
	@Inject
	  public MnuReportes(WSClient ws, MailerClient mailerClient) {
	    this.ws = ws;
	    this.mailerClient = mailerClient;
	  }
	
	
	//====================================================================================
    // MNU reportInventarioGeneral   Reportes/Inventario/Arriendo/Arriendo: por equipo
    //====================================================================================
	
	public Result reportInventarioEquipoCorte(Http.Request request, String tipo) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		try {
    			Connection con = dbRead.getConnection(dbRead);
	    			if(mapeoPermiso.get("reportInventarioGeneral")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			Fechas hoy = Fechas.hoy();
	    			con.close();
    			return ok(reportInventarioEquipoCorte.render(mapeoDiccionario,mapeoPermiso,userMnu,hoy.getFechaStrAAMMDD(),tipo));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reportInventarioEquipo(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String tipo = form.get("tipo").trim();
	       		String fechaCorte = form.get("fechaCorte").trim();
	       		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	       			Connection con = dbRead.getConnection(dbRead);
	    			Map<Long,List<Double>> mapPCompra = Compra.ultimoPrecio(con, s.baseDato);
	    			Map<Long,List<Double>> mapPLista = Precio.maestroPListaPorSucursal(con, s.baseDato, Long.parseLong(s.id_sucursal));
	    			Map<Long,String> moneda = Moneda.mapIdMonedaMoneda(con, s.baseDato);
	    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));

	    			Map<Long,Double> tasasCorte = TasasCambio.mapTasasPorFecha(con, s.baseDato,fechaCorte, mapeoDiccionario.get("pais"));
	    			Map<Long,Long> dec = Moneda.numeroDecimal(con, s.baseDato);
	    			Map<Long,UnidadTiempo> mapUnidadTiempo = UnidadTiempo.mapUnidadTiempo(con, s.baseDato);
	    			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, s.baseDato);
	    			Map<Long,Equipo> mapEquipo = Equipo.mapAllAll(con, s.baseDato);
	    			
	    			List<List<String>> datos = ReportInventarios.reportInventarioEquipo(con, s.baseDato, fechaCorte, permisoPorBodega, mapPCompra, mapPLista, moneda, 
	    					tipo, mapeoDiccionario, s.aplicaPorSucursal, s.id_sucursal, mapEquipo, mapSucursal, mapUnidadTiempo, tasasCorte, dec);
	    			
	    			con.close();
	    			
	    			return ok(reportInventarioEquipo.render(mapeoDiccionario,mapeoPermiso,userMnu,datos,fechaCorte,tipo));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reportInventarioGeneralXEquipo(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
	       		String tipo = form.get("tipo").trim();
	       		String fechaCorte = form.get("fechaCorte").trim();
	       		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	       			Connection con = dbRead.getConnection(dbRead);
	    			Map<Long,List<Double>> mapPCompra = Compra.ultimoPrecio(con, s.baseDato);
	    			Map<Long,List<Double>> mapPLista = Precio.maestroPListaPorSucursal(con, s.baseDato, Long.parseLong(s.id_sucursal));
	    			Map<Long,String> moneda = Moneda.mapIdMonedaMoneda(con, s.baseDato);
	    			Equipo equipo = Equipo.find(con, s.baseDato, id_equipo);
	    			List<List<String>> datos = ReportInventarios.reportInventarioGeneralXEquipo(con, s.baseDato, equipo, fechaCorte, tipo, 
	    					mapPCompra, mapPLista, moneda, mapeoDiccionario, s.aplicaPorSucursal, s.id_sucursal);
	    			con.close();
	    			return ok(reportInventarioGeneralXEquipo.render(mapeoDiccionario,mapeoPermiso,userMnu,datos,fechaCorte,tipo));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reportInventTrazaEquipoEnBod(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_bodega = Long.parseLong(form.get("id_bodega").trim());
	       		Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
	       		String tipo = form.get("tipo").trim();
	       		try {
	       			Connection con = dbRead.getConnection(dbRead);
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			List<List<String>> datos = ReportInventarios.trazaEquipoEnBodega(con, s.baseDato, id_bodega, id_equipo, tipo, mapeoDiccionario);
	    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodega);
	    			con.close();
	    			
	    			return ok(reportInventTrazaEquipoEnBod.render(mapeoDiccionario,mapeoPermiso,userMnu,bodega, datos, tipo));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reportInventarioEquipoExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String tipo = form.get("tipo").trim();
	       		String fechaCorte = form.get("fechaCorte").trim();
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	       			Connection con = dbRead.getConnection(dbRead);
	    			Map<Long,List<Double>> mapPCompra = Compra.ultimoPrecio(con, s.baseDato);
	    			Map<Long,List<Double>> mapPLista = Precio.maestroPListaPorSucursal(con, s.baseDato, Long.parseLong(s.id_sucursal));
	    			Map<Long,String> moneda = Moneda.mapIdMonedaMoneda(con, s.baseDato);
	    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
	    			
	    			Map<Long,Double> tasasCorte = TasasCambio.mapTasasPorFecha(con, s.baseDato,fechaCorte, mapeoDiccionario.get("pais"));
	    			Map<Long,Long> dec = Moneda.numeroDecimal(con, s.baseDato);
	    			Map<Long,UnidadTiempo> mapUnidadTiempo = UnidadTiempo.mapUnidadTiempo(con, s.baseDato);
	    			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, s.baseDato);
	    			Map<Long,Equipo> mapEquipo = Equipo.mapAllAll(con, s.baseDato);
	    			
	    			List<List<String>> datos = ReportInventarios.reportInventarioEquipo(con, s.baseDato, fechaCorte, permisoPorBodega, mapPCompra, mapPLista, moneda, 
	    					tipo, mapeoDiccionario, s.aplicaPorSucursal, s.id_sucursal, mapEquipo, mapSucursal, mapUnidadTiempo, tasasCorte, dec);
	    			
	    			File file = ReportInventarios.reportInventarioGeneralExcel(s.baseDato, datos, fechaCorte, mapeoDiccionario, tipo);
	    			if(file!=null) {
		       			con.close();
		       			return ok(file,false,Optional.of("InventarioPorEquipo.xlsx"));
		       		}else {
		       			con.close();
		       			return ok("");
		       		}
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok("");
	       	}
    	}else {
    		return ok("");
    	}
	}
	
	
	//====================================================================================
    // MNU reportInventarioSelectivo   Reportes/Inventario/Arriendo/Arriendo: por bodega
    //====================================================================================
	
	public Result reportInventarioBodegaCorte(Http.Request request, String tipo) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		try {
    			Connection con = dbRead.getConnection(dbRead);
    			if(mapeoPermiso.get("reportInventarioSelectivo")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Fechas hoy = Fechas.hoy();
    			con.close();
    			return ok(reportInventarioBodegaCorte.render(mapeoDiccionario,mapeoPermiso,userMnu,hoy.getFechaStrAAMMDD(),tipo));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reportInventarioBodega(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String tipo = form.get("tipo").trim();
	       		String fechaCorte = form.get("fechaCorte").trim();
	       		Long soloVigentes = Long.parseLong(form.get("soloVigentes").trim());
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
	    			List<List<String>> datos = ReportInventarios.reportInventarioGeneralXBodega(con, s.baseDato, fechaCorte, tipo, 
	    					soloVigentes, permisoPorBodega, mapeoDiccionario, s.aplicaPorSucursal, s.id_sucursal);
	    			con.close();
	    			return ok(reportInventarioBodega.render(mapeoDiccionario,mapeoPermiso,userMnu,datos,fechaCorte,tipo));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reportInventarioSelectivoXBodega(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_bodega = Long.parseLong(form.get("id_bodega").trim());
	       		String tipo = form.get("tipo").trim();  // tipo es VENTA o ARRIENDO o todo
	       		String fechaCorte = form.get("fechaCorte").trim();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodega);
	    			List<List<String>> datos = ReportInventarios.reportInventarioSelectivoXBodega(con, s.baseDato, bodega, fechaCorte, tipo, mapeoDiccionario);
	    			con.close();
	    			return ok(reportInventarioSelectivoXBodega.render(mapeoDiccionario,mapeoPermiso,userMnu,bodega,datos,fechaCorte,tipo));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reportInventProyectoTrazaEquipoEnBod(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_bodega = Long.parseLong(form.get("id_bodega").trim());
	       		Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
	       		Long id_cotizacion = Long.parseLong(form.get("id_cotizacion").trim());
	       		String tipo = form.get("tipo").trim();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodega);
	    			List<List<String>> datos = ReportInventarios.trazaEquipoProyectoEnBodega(con, s.baseDato, id_bodega, id_equipo, id_cotizacion, tipo, mapeoDiccionario);
	    			con.close();
	    			return ok(reportInventTrazaEquipoEnBod.render(mapeoDiccionario,mapeoPermiso,userMnu,bodega, datos, tipo));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reportInventarioSelectivoXBodegaExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_bodega = Long.parseLong(form.get("id_bodega").trim());
	       		String tipo = form.get("tipo").trim();
	       		String fechaCorte = form.get("fechaCorte").trim();
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodega);
	    			List<List<String>> datos = ReportInventarios.reportInventarioSelectivoXBodega(con, s.baseDato, bodega, fechaCorte, tipo, mapeoDiccionario);
	    			File file = ReportInventarios.exportaReportInventarioSelectivoXBodega(s.baseDato, datos, fechaCorte, mapeoDiccionario, bodega, tipo);
	    			if(file!=null) {
		       			con.close();
		       			return ok(file,false,Optional.of("InventarioPorBodega.xlsx"));
		       		}else {
		       			con.close();
		       			return ok("");
		       		}
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok("");
	       	}
    	}else {
    		return ok("");
    	}
	}
	
	//====================================================================================
    // MNU reportInventarioGrupo   Reportes/Inventario/Arriendo/Arriendo: por grupo
    //====================================================================================
	
	public Result reportInventarioGrupoCorte(Http.Request request, String tipo) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		try {
    			Connection con = dbRead.getConnection(dbRead);
    			if(mapeoPermiso.get("reportInventarioGrupo")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Fechas hoy = Fechas.hoy();
    			con.close();
    			return ok(reportInventarioGrupoCorte.render(mapeoDiccionario,mapeoPermiso,userMnu,hoy.getFechaStrAAMMDD(),tipo));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reportInventarioGrupo(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String tipo = form.get("tipo").trim();
	       		String fechaCorte = form.get("fechaCorte").trim();
	       		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			List<Grupo> grupos = Grupo.all(con, s.baseDato);
	    			con.close();
	    			return ok(reportInventarioGrupo.render(mapeoDiccionario,mapeoPermiso,userMnu,grupos,fechaCorte,tipo));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reportInventarioSelectivoXGrupo(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_grupo = Long.parseLong(form.get("id_grupo").trim());
	       		String tipo = form.get("tipo").trim();
	       		String fechaCorte = form.get("fechaCorte").trim();
	       		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
	    			List<List<String>> datos = ReportInventarios.reportInventarioSelectivoXGrupo(con, s.baseDato, id_grupo, fechaCorte, 
	    					permisoPorBodega, tipo, mapeoDiccionario, s.aplicaPorSucursal, s.id_sucursal);
	    			Grupo grupo = Grupo.find(con, s.baseDato, id_grupo);
	    			con.close();
	    			return ok(reportInventarioSelectivoXGrupo.render(mapeoDiccionario,mapeoPermiso,userMnu,grupo,datos,fechaCorte,tipo));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reportInventarioSelectivoXGrupoExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_grupo = Long.parseLong(form.get("id_grupo").trim());
	       		String tipo = form.get("tipo").trim();
	       		String fechaCorte = form.get("fechaCorte").trim();
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			Grupo grupo = Grupo.find(con, s.baseDato, id_grupo);
	    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
	    			List<List<String>> datos = ReportInventarios.reportInventarioSelectivoXGrupo(con, s.baseDato, id_grupo, fechaCorte, 
	    					permisoPorBodega, tipo, mapeoDiccionario, s.aplicaPorSucursal, s.id_sucursal);
	    			File file = ReportInventarios.exportaReportInventarioSelectivoXGrupo(s.baseDato, datos, fechaCorte, mapeoDiccionario, grupo, tipo);
	    			if(file!=null) {
		       			con.close();
		       			return ok(file,false,Optional.of("InventarioPorGrupo.xlsx"));
		       		}else {
		       			con.close();
		       			return ok("");
		       		}
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok("");
	       	}
    	}else {
    		return ok("");
    	}
	}
	
	//====================================================================================
    // MNU inventarioMatriz   Reportes/Inventario/Arriendo/Arriendo: Equipos por Bodegas
    //====================================================================================
	
	public Result reportInventarioMatrizCorte(Http.Request request, String tipo) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		try {
    			Connection con = dbRead.getConnection(dbRead);
    			if(mapeoPermiso.get("inventarioMatriz")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Fechas hoy = Fechas.hoy();
    			List<Sucursal> listSucursal = new ArrayList<Sucursal>();
    			if(s.aplicaPorSucursal.equals("1")) {
    				Sucursal sucursal = Sucursal.find(con, s.baseDato, s.id_sucursal);
    				listSucursal.add(sucursal);
    			}else {
    				listSucursal = Sucursal.all(con, s.baseDato);
    			}
    			
    			con.close();
    			return ok(reportInventarioMatrizCorte.render(mapeoDiccionario,mapeoPermiso,userMnu,hoy.getFechaStrAAMMDD(),tipo, listSucursal));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reportInventarioMatriz(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String tipo = form.get("tipo").trim();
	       		String fechaCorte = form.get("fechaCorte").trim();
	       		String id_sucursal = form.get("id_sucursal").trim();
	       		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
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
		    		con.close();
		    			
		    			if(mapeoDiccionario.get("nEmpresa").equals("SM8 DE MEXICO")) {
		    				File file = ReportInventarios.exportaReportInventarioMatriz(s.baseDato, datos, titulos3, titulos2, tipo, fechaCorte, mapeoDiccionario, sucursal.getNombre());
			    			if(file!=null) {
				       			return ok(file,false,Optional.of("InventarioMatriz.xlsx"));
				       		}else {
				       			return ok("");
				       		}
		    			}
		    			return ok(reportInventarioMatriz.render(mapeoDiccionario,mapeoPermiso,userMnu,datos,titulos1,titulos2,tipo,fechaCorte,sucursal));
	    			
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reportInventarioMatrizExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String tipo = form.get("tipo").trim();
	       		String fechaCorte = form.get("fechaCorte").trim();
	       		String id_sucursal = form.get("id_sucursal").trim();
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
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
	    			
	    			List<String> titulos2 = ReportInventarios.listaMatrizEquiposTitulos2(listaBodegas);
	    			List<String> titulos3 = ReportInventarios.listaMatrizEquiposTitulos3(listaBodegas);
	    			List<List<String>> datos = ReportInventarios.listaMatrizEquipos(con, s.baseDato, permisoPorBodega, tipo, fechaCorte, listaBodegas, mapeoDiccionario);
	    			Sucursal sucursal = new Sucursal();
	    			sucursal.setId((long)0);
    				sucursal.setNombre("TODAS");
	    			if( ! id_sucursal.equals("0")) {
	    				sucursal = Sucursal.find(con, s.baseDato, id_sucursal);
	    			}
	    			File file = ReportInventarios.exportaReportInventarioMatriz(s.baseDato, datos, titulos3, titulos2, tipo, fechaCorte, mapeoDiccionario, sucursal.getNombre());
	    			if(file!=null) {
		       			con.close();
		       			return ok(file,false,Optional.of("InventarioMatriz.xlsx"));
		       		}else {
		       			con.close();
		       			return ok("");
		       		}
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok("");
	       	}
    	}else {
    		return ok("");
    	}
	}
	
	//====================================================================================
    // MNU inventarioStock   Reportes/Inventario/Arriendo/Todo: Equipos con Stock y Precios
    //====================================================================================
	
	public Result reportInventarioTodo(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		try {
    			Connection con = dbRead.getConnection(dbRead);
    			if(mapeoPermiso.get("inventarioStock")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<List<String>> datos = ReportInventarios.listaFullEquipos(con, s.baseDato);
    			con.close();
    			return ok(reportInventarioTodo.render(mapeoDiccionario,mapeoPermiso,userMnu,datos));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reportInventarioTodoExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		List<List<String>> datos = null;
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
		    			datos = ReportInventarios.listaFullEquipos(con, s.baseDato);
	    			con.close();
	       		} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		if(datos != null) {
	       			File file = ReportInventarios.exportaReportInventarioTodo(s.baseDato, datos, mapeoDiccionario);
	    			if(file!=null) {
		       			return ok(file,false,Optional.of("TodoElInventario.xlsx"));
		       		}
	       		}
	       	}
    	}
    	return ok("");
	}
	
	//====================================================================================
    // MNU inventarioStock   Reportes/Inventario/Estado Bodegas
    //====================================================================================
	
	public Result reportEstadoBodegas(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		try {
    			Connection con = dbRead.getConnection(dbRead);
    			if(mapeoPermiso.get("reportInventarioGeneral")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<List<String>> datos = ReportBodegas.estadoBodegas(con, s.baseDato);
    			con.close();
    			return ok(reportEstadoBodegas.render(mapeoDiccionario,mapeoPermiso,userMnu,datos));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reportEstadoBodegasExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			List<List<String>> datos = ReportBodegas.estadoBodegas(con, s.baseDato);
	    			File file = ReportBodegas.estadoBodegasExcel(s.baseDato, mapeoDiccionario, datos);
	    			if(file!=null) {
		       			con.close();
		       			return ok(file,false,Optional.of("ReporteEstadoBodegas.xlsx"));
		       		}else {
		       			con.close();
		       			return ok("");
		       		}
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok("");
	       	}
    	}else {
    		return ok("");
    	}
	}
	
	
	//====================================================================================
    // MNU reportInventarioProyecto   Reportes/Existencias
    //====================================================================================
	
	public Result reportInventarioProyecto(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato); 
    		try {
    			Connection con = dbRead.getConnection(dbRead);
    			if(mapeoPermiso.get("reportInventarioProyecto")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
    			List<List<String>> datos = ReportInventarios.reportInventarioProyecto(con, s.baseDato, permisoPorBodega, s.aplicaPorSucursal, s.id_sucursal);
    			con.close();
    			return ok(reportInventarioProyecto.render(mapeoDiccionario,mapeoPermiso,userMnu,datos));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reportInventarioProyectoDetalle(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_bodega = Long.parseLong(form.get("id_bodega").trim());
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodega);
	    			List<List<String>> datos = ReportInventarios.reportInventarioProyectoDetalle(con, s.baseDato, bodega.getId(), mapeoDiccionario);
	    			con.close();
	    			return ok(reportInventarioProyectoDetalle.render(mapeoDiccionario,mapeoPermiso,userMnu,bodega,datos));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reportInventarioProyectoDetalleExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_bodega = Long.parseLong(form.get("id_bodega").trim());
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodega);
	    			List<List<String>> datos = ReportInventarios.reportInventarioProyectoDetalle(con, s.baseDato, bodega.getId(), mapeoDiccionario);
	    			File file = ReportInventarios.exportaReportInventarioProyectoDetalle(s.baseDato, datos, mapeoDiccionario, bodega);
	    			if(file!=null) {
		       			con.close();
		       			return ok(file,false,Optional.of("ExistenciasPorBodega.xlsx"));
		       		}else {
		       			con.close();
		       			return ok("");
		       		}
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok("");
	       	}
    	}else {
    		return ok("");
    	}
	}
	
	//====================================================================================
    // MNU reporteMovimientos0   Reportes/Movimientos/Por Fecha (Agrupado)
    //====================================================================================
	
	public Result reporteMovimientosPeriodoAgrupado(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		try {
    			Connection con = dbRead.getConnection(dbRead);
    			if(mapeoPermiso.get("reporteMovimientos0")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Fechas hoy = Fechas.hoy();
    			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			con.close();
    			return ok(reporteMovimientosPeriodoAgrupado.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reporteMovimientosListaProyectosAgrupado(Http.Request request) {
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
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    				Fechas hoy = Fechas.hoy();
	    				Map<Long,Double> tasas = TasasCambio.mapTasasPorFecha(con, s.baseDato,hoy.getFechaStrAAMMDD(), mapeoDiccionario.get("pais"));
		    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
		    			List<Long> listIdBodegaEmpresa = ModCalc_InvInicial.listIdBodegaEmpresa(con, s.baseDato, permisoPorBodega);
		    			Map<Long,Calc_BodegaEmpresa> mapBodegaEmpresa = Calc_BodegaEmpresa.mapAllBodegasVigentes(con, s.baseDato);
		    			Map<String,Calc_Precio> mapPrecios = Calc_Precio.mapPrecios(con, s.baseDato, listIdBodegaEmpresa);
		    			Map<Long,Calc_Precio> mapMaestroPrecios = Calc_Precio.mapMaestroPrecios(con, s.baseDato);
		    			Map<String, Double> mapFijaTasas = BodegaEmpresa.mapFijaTasasAll(con, s.baseDato);
		    			
		    			List<Long> listIdGuia_fechaCorte = ModCalc_InvInicial.listIdGuia_fechaCorte(con, s.baseDato, desdeAAMMDD);
		    			List<Inventarios> inventario = Inventarios.inventario(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_fechaCorte);
		    			List<Long> listIdGuia_entreFechas = ModCalc_GuiasPer.listIdGuia_entreFecha(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
		    			List<Inventarios> guiasPer = Inventarios.guiasPer(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_entreFechas);
		    			List<Calc_AjustesEP> listaAjustes = Calc_AjustesEP.listaAjustesEntreFechas(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
		    			Map<Long,List<String>> mapBodega = BodegaEmpresa.mapIdBod_BodegaEmpresaInternasExternas(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
		    			Map<Long,Long> dec = Moneda.numeroDecimal(con, s.baseDato);
		    			
		    			Map<String,String> map = UsuarioPermiso.mapPermisoIdBodega(con, s.baseDato, Long.parseLong(s.id_usuario));
		    			
		    			Map<String,String> mapPermanencias = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, s.baseDato);
	    			
	    			con.close();
	    			
	    			List<ModCalc_InvInicial> inventarioInicial = ModCalc_InvInicial.resumenInvInicial(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa, 
	    					mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, listIdGuia_fechaCorte, inventario);
	    			
	    			List<ModCalc_GuiasPer> guiasPeriodo = ModCalc_GuiasPer.resumenGuiasPer(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, 
	    					mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, guiasPer, mapPermanencias);
	    			
	    			List<ModeloCalculo> listado = ModeloCalculo.valorTotalporBodega(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, inventarioInicial,guiasPeriodo, listaAjustes);
	    			
	    			List<List<String>> proyectosAux = ReportFacturas.reportFacturaProyecto(listado, mapBodega);
	    			
	    			List<List<String>> resumenTotales = ReportFacturas.resumenTotalesPorProyecto(listado, dec);
	    			
	    			List<List<String>> proyectos = new ArrayList<List<String>>();
	    			
	    			
	    			if(map.size()>0) {
		    			for(List<String> aux:proyectosAux) {
		    				String idBodega = map.get(aux.get(1));
		    				if(idBodega!=null) {
		    					proyectos.add(aux);
		    				}
		    			}
	    			}else {
	    				proyectos = proyectosAux;
	    			}
	    			
	    			Map<String,List<String>> mapTotal = new HashMap<String,List<String>>();
	    			for(List<String> total: resumenTotales){
	    				mapTotal.put(total.get(0), total);
	    			}
	    			
	    			
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
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reporteMovimientosDetalleAgrupado(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodega").trim());
	       		String fechaDesde = form.get("fechaDesde").trim();
	       		String fechaHasta = form.get("fechaHasta").trim();
	       		String esVenta = form.get("esVenta").trim();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			List<List<String>> datos = ReportMovimientos.movimientoGuiasAgrupado(con, s.baseDato, id_bodegaEmpresa, esVenta, fechaDesde, fechaHasta);
	    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
	    			String concepto = mapeoDiccionario.get("ARRIENDO");
	    			if(esVenta.equals("1")) {
	    				concepto = "VENTA";
	    			}
	    			con.close();
	    			return ok(reporteMovimientosDetalleAgrupado.render(mapeoDiccionario,mapeoPermiso,userMnu,datos,bodega,esVenta,concepto,fechaDesde,fechaHasta));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reporteMovimientosDetalleAgrupadoExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
	       		String fechaDesde = form.get("fechaDesde").trim();
	       		String fechaHasta = form.get("fechaHasta").trim();
	       		String esVenta = form.get("esVenta").trim();
	       		List<List<String>> datos = null;
	       		BodegaEmpresa bodega = null;
	       		File file = null;
	       		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			datos = ReportMovimientos.movimientoGuiasAgrupado(con, s.baseDato, id_bodegaEmpresa, esVenta, fechaDesde, fechaHasta);
	    			bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
	    			con.close();
	       		} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		
	       		if(datos!=null && bodega!=null) {
	    			String concepto = mapeoDiccionario.get("ARRIENDO");
	    			if(esVenta.equals("1")) {
	    				concepto = "VENTA";
	    			}
	    			file = ReportMovimientos.movimientosExcelAgrupado(s.baseDato, datos, mapeoDiccionario, bodega, concepto, fechaDesde, fechaHasta);
	    			if(file!=null) {
		       			return ok(file,false,Optional.of("MovimientosPorBodegaAgrupado.xlsx"));
		       		}
	       		}
	       	}
    	}
    	return ok("");
	}
	
	//====================================================================================
    // MNU reporteMovimientos0   Reportes/Movimientos/SoloBodegas Internas
    //====================================================================================
	
	public Result reporteMovSoloBodInternas0(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato); 
    		try {
    			Connection con = dbRead.getConnection(dbRead);
    			if(mapeoPermiso.get("reporteMovimientos0")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Fechas hoy = Fechas.hoy();
    			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			con.close();
    			return ok(reporteMovSoloBodInternas0.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reporteMovSoloBodInternas1(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String fechaDesde = form.get("fechaDesde").trim();
	       		String fechaHasta = form.get("fechaHasta").trim();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
	    			List<List<String>> datos = ReportInventarios.reportInventarioSoloBodegasInternas(con, s.baseDato, permisoPorBodega, s.aplicaPorSucursal, s.id_sucursal);
	    			con.close();
	    			return ok(reporteMovSoloBodInternas1.render(mapeoDiccionario,mapeoPermiso,userMnu,datos,fechaDesde,fechaHasta));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reporteMovSoloBodInternas2(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodega").trim());
	       		String fechaDesde = form.get("fechaDesde").trim();
	       		String fechaHasta = form.get("fechaHasta").trim();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			List<List<String>> datos = ReportMovimientos.movimientoGuiasSoloBodInternas(con, s.baseDato, id_bodegaEmpresa, fechaDesde, fechaHasta);
	    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
	    			con.close();
	    			return ok(reporteMovSoloBodInternas2.render(mapeoDiccionario,mapeoPermiso,userMnu,datos,bodega,fechaDesde,fechaHasta));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reporteMovSoloBodInternas2Excel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
	       		String fechaDesde = form.get("fechaDesde").trim();
	       		String fechaHasta = form.get("fechaHasta").trim();
	       		
	       		List<List<String>> datos = null;
	       		BodegaEmpresa bodega = null;
	       		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			datos = ReportMovimientos.movimientoGuiasSoloBodInternas(con, s.baseDato, id_bodegaEmpresa, fechaDesde, fechaHasta);
	    			bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
	    			con.close();
	       		} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		
	       		if(datos!=null && bodega!=null) {
	    			File file = ReportMovimientos.reporteMovSoloBodInternas3Excel(s.baseDato, datos, mapeoDiccionario, bodega, fechaDesde, fechaHasta);
	    			if(file!=null) {
		       			return ok(file,false,Optional.of("MovimientosPorBodegaInterna.xlsx"));
		       		}
	       		}
	       	}
    	}
    	return ok("");
	}
	
	//====================================================================================
    // MNU reporteMovimientos0   Reportes/Movimientos/Por proyecto (Agrupado)
    //====================================================================================
	
	public Result reportePorProyectoAgrupado(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		try {
    			Connection con = dbRead.getConnection(dbRead);
    			if(mapeoPermiso.get("reporteMovimientos0")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Fechas hoy = Fechas.hoy();
    			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			con.close();
    			return ok(reportePorProyectoAgrupado.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reportePorProyectoListaAgrupado(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String fechaDesde = form.get("fechaDesde").trim();
	       		String fechaHasta = form.get("fechaHasta").trim();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
	    			List<List<String>> datos = ReportInventarios.listaProyectosAsignados(con, s.baseDato, permisoPorBodega, s.aplicaPorSucursal, s.id_sucursal);
	    			con.close();
	    			return ok(reportePorProyectoListaAgrupado.render(mapeoDiccionario,mapeoPermiso,userMnu,datos,fechaDesde,fechaHasta));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reportePorProyectoDetalleAgrupado(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_proyecto = Long.parseLong(form.get("id_proyecto").trim());
	       		String fechaDesde = form.get("fechaDesde").trim();
	       		String fechaHasta = form.get("fechaHasta").trim();
	       		String esVenta = form.get("esVenta").trim();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
	    			
	    			List<List<String>> datos = ReportMovimientos.movimientoGuiasPorProyecto(con, s.baseDato, id_proyecto, esVenta, fechaDesde, fechaHasta,
	    					permisoPorBodega, s.aplicaPorSucursal, s.id_sucursal, mapeoDiccionario);
	    			
	    			Proyecto proyecto = Proyecto.find(con, s.baseDato, id_proyecto);
	    			
	    			String concepto = mapeoDiccionario.get("ARRIENDO");
	    			if(esVenta.equals("1")) {
	    				concepto = "VENTA";
	    			}
	    			con.close();
	    			return ok(reportePorProyectoDetalleAgrupado.render(mapeoDiccionario,mapeoPermiso,userMnu,datos,proyecto,esVenta,concepto,fechaDesde,fechaHasta));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reportePorProyectoDetalleAgrupadoExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_proyecto = Long.parseLong(form.get("id_proyecto").trim());
	       		String fechaDesde = form.get("fechaDesde").trim();
	       		String fechaHasta = form.get("fechaHasta").trim();
	       		String esVenta = form.get("esVenta").trim();
	       		
	       		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		String permisoPorBodega = null;
	       		List<List<String>> datos = null;
	       		Proyecto proyecto = null;
	       		
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
	    			datos = ReportMovimientos.movimientoGuiasPorProyecto(con, s.baseDato, id_proyecto, esVenta, fechaDesde, fechaHasta,
	    					permisoPorBodega, s.aplicaPorSucursal, s.id_sucursal, mapeoDiccionario);
	    			proyecto = Proyecto.find(con, s.baseDato, id_proyecto);
	    			con.close();
	       		} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		
	       		if(permisoPorBodega!=null && datos!=null && proyecto!=null) {
	    			String concepto = mapeoDiccionario.get("ARRIENDO");
	    			if(esVenta.equals("1")) {
	    				concepto = "VENTA";
	    			}
	    			File file = ReportMovimientos.movPorProyectoExcelAgrupado(s.baseDato, datos, mapeoDiccionario, proyecto, concepto, fechaDesde, fechaHasta);
	    			if(file!=null) {
		       			return ok(file,false,Optional.of("MovimientosPorBodegaAgrupado.xlsx"));
		       		}
		       	}
	       	}
    	}
    	return ok("");
	}
	
	
	
	
	//====================================================================================
    // MNU reporteMovimientos1   Reportes/Movimientos/Por Fecha (Valorizado)
    //====================================================================================
	
	public Result reporteMovimientosPeriodo(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		try {
    			Connection con = dbRead.getConnection(dbRead);
    			if(mapeoPermiso.get("reporteMovimientos1")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Fechas hoy = Fechas.hoy();
    			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
    			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			TasasCambio tasas = TasasCambio.allDeUnaFecha(con, s.baseDato, mapeoDiccionario.get("pais"),hasta);
    			con.close();
    			return ok(reporteMovimientosPeriodo.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta, tasas));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reporteMovimientosListaProyectos(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
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
	       		try {
	       			Connection con = dbRead.getConnection(dbRead);
		    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
		    			List<Long> listIdBodegaEmpresa = ModCalc_InvInicial.listIdBodegaEmpresa(con, s.baseDato, permisoPorBodega);
		    			Map<Long,Calc_BodegaEmpresa> mapBodegaEmpresa = Calc_BodegaEmpresa.mapAllBodegasVigentes(con, s.baseDato);
		    			Map<String,Calc_Precio> mapPrecios = Calc_Precio.mapPrecios(con, s.baseDato, listIdBodegaEmpresa);
		    			Map<Long,Calc_Precio> mapMaestroPrecios = Calc_Precio.mapMaestroPrecios(con, s.baseDato);
		    			Map<String, Double> mapFijaTasas = BodegaEmpresa.mapFijaTasasAll(con, s.baseDato);
		    			
		    			List<Long> listIdGuia_fechaCorte = ModCalc_InvInicial.listIdGuia_fechaCorte(con, s.baseDato, desdeAAMMDD);
		    			List<Inventarios> inventario = Inventarios.inventario(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_fechaCorte);
		    			List<Long> listIdGuia_entreFechas = ModCalc_GuiasPer.listIdGuia_entreFecha(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
		    			List<Inventarios> guiasPer = Inventarios.guiasPer(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_entreFechas);
		    			List<Calc_AjustesEP> listaAjustes = Calc_AjustesEP.listaAjustesEntreFechas(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
		    			Map<Long,List<String>> mapBodega = BodegaEmpresa.mapIdBod_BodegaEmpresaInternasExternas(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
		    			Map<Long,Long> dec = Moneda.numeroDecimal(con, s.baseDato);
		    			
		    			Map<String,String> map = UsuarioPermiso.mapPermisoIdBodega(con, s.baseDato, Long.parseLong(s.id_usuario));
		    			
		    			Map<String,String> mapPermanencias = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, s.baseDato);
	    			
	    			con.close();
    			
    			
    			
    			
    			List<ModCalc_InvInicial> inventarioInicial = ModCalc_InvInicial.resumenInvInicial(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa, 
    					mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, listIdGuia_fechaCorte, inventario);
    			
    			List<ModCalc_GuiasPer> guiasPeriodo = ModCalc_GuiasPer.resumenGuiasPer(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, 
    					mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, guiasPer, mapPermanencias);
    			
    			
    			List<ModeloCalculo> listado = ModeloCalculo.valorTotalporBodega(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, inventarioInicial,guiasPeriodo, listaAjustes);
    			
    			List<List<String>> proyectosAux = ReportFacturas.reportFacturaProyecto(listado, mapBodega);
    			
    			List<List<String>> resumenTotales = ReportFacturas.resumenTotalesPorProyecto(listado, dec);
	    			
	    			
	    			
	    			List<List<String>> proyectos = new ArrayList<List<String>>();
	    			if(map.size()>0) {
		    			for(List<String> aux:proyectosAux) {
		    				String idBodega = map.get(aux.get(1));
		    				if(idBodega!=null) {
		    					proyectos.add(aux);
		    				}
		    			}
	    			}else {
	    				proyectos = proyectosAux;
	    			}
	    			
	    			
	    			String tabla = "<table id=\"tablaPrincipal\" class=\"table table-sm table-hover table-bordered table-condensed table-fluid\">"
	    					+ "<thead style=\"background-color: #eeeeee\">"
	    					+ "<TR> "
	    					+ "<TH style= \"text-align: center;vertical-align: top;\">SUCURSAL</TH>"
	    					+ "<TH style= \"text-align: center;vertical-align: top;\">"+mapeoDiccionario.get("BODEGA")+"/PROYECTO</TH>"
	    					+ "<TH style= \"text-align: center;vertical-align: top;\">NOMBRE<BR>CLIENTE</TH>"
	    					+ "<TH style= \"text-align: center;vertical-align: top;\">NOMBRE<br>PROYECTO</TH>"
	    					+ "<TH style= \"text-align: center;vertical-align: top;\">COMERCIAL</TH>"
	    					+ "<TH style= \"text-align:center;vertical-align:top;\">CFI (en "+mapeoDiccionario.get("PESOS")+")</TH>"
	    					+ "<TH style= \"text-align:center;vertical-align:top;\">SubTotal<br>"+mapeoDiccionario.get("ARRIENDO")+"</TH>"
	    					+ "<TH style= \"text-align:center;vertical-align:top;\">SubTotal<br>VENTA</TH>"
	    					+ "<TH style= \"text-align:center;vertical-align:top;\">Ajustes<BR>("+mapeoDiccionario.get("ARRIENDO")+")</TH>"
	    					+ "<TH style= \"text-align:center;vertical-align:top;\">Ajustes<BR>(VENTA)</TH>"
	    					+ "<TH style= \"text-align:center;vertical-align:top;\">TOTAL<BR>(en "+mapeoDiccionario.get("PESOS")+")</TH>"
	    					+ "<TH width=\"5%\" >@mapDiccionario.get(\"ARRIENDO\")<BR></TH>"
	    					+ "<TH width=\"5%\" >VENTA<BR></TH>"
	    					+ "</TR>"
	    					+ "</thead>"
	    					+ "<tbody>";
	    					for(List<String> lista1: proyectos){
	    						tabla += "<TR>";
		    					for(List<String> total: resumenTotales){
		    						if(lista1.get(1).equals(total.get(0))){
		    							tabla += "<td style=\"text-align:left;vertical-align:middle;\">"+lista1.get(14)+"</td>"
					    					+ "<td style=\"text-align:left;vertical-align:middle;\"><a href=\"#\" onclick=\"modalContactoBodega('"+lista1.get(1)+"');\">"+lista1.get(5)+"</a></td>"
					    					+ "<td style=\"text-align:left;vertical-align:middle;\"><a href=\"#\" onclick=\"modalContactoCliente('"+lista1.get(2)+"');\">"+lista1.get(7)+"</a></td>"
					    					+ "<td style=\"text-align:left;vertical-align:middle;\"><a href=\"#\" onclick=\"modalContactoProyecto('"+lista1.get(3)+"');\">"+lista1.get(8)+"</a></td>"
					    					+ "<td style=\"text-align:left;vertical-align:middle;\">"+lista1.get(10)+"</td>"
					    					+ "<td style= \"text-align:right;\" class=\"cfi\">"+total.get(3)+"</td>"
					    					+ "<td style= \"text-align:right;\" class=\"arr\">"+total.get(1)+"</td>"
					    					+ "<td style= \"text-align:right;\" class=\"vta\">"+total.get(2)+"</td>"
					    					+ "<td style= \"text-align:right;\" class=\"ajustArr\">"+total.get(5)+"</td>"
					    					+ "<td style= \"text-align:right;\" class=\"ajustVta\">"+total.get(6)+"</td>"
					    					+ "<td style= \"text-align:right;\" class=\"granTotal\">"+total.get(4)+"</td>"
					    					+ "											"
					    					+ "<td style=\"text-align:center;vertical-align:middle;\">"
					    					+ "<form id=\"form0_"+lista1.get(1)+"\" class=\"formulario\" method=\"post\" action=\"/reporteMovimientosDetalle/\">"
					    					+ "<input type=\"hidden\" name=\"id_bodega\" value=\""+lista1.get(1)+"\">"
					    					+ "<input type=\"hidden\" name=\"fechaDesde\" value=\""+desdeAAMMDD+"\">"
					    					+ "<input type=\"hidden\" name=\"fechaHasta\" value=\""+hastaAAMMDD+"\">"
					    					+ "<input type=\"hidden\" name=\"esVenta\" value=\"0\">"
					    					+ "<input type=\"hidden\" name=\"uf\" value=\""+uf+"\">"
					    					+ "<input type=\"hidden\" name=\"usd\" value=\""+usd+"\">"
					    					+ "<input type=\"hidden\" name=\"eur\" value=\""+eur+"\">"
					    					+ "<a href=\"#\" onclick=\"document.getElementById('form0_"+lista1.get(1)+"').submit()\">"
					    					+ "<kbd style=\"background-color: #73C6B6\">"+mapeoDiccionario.get("Arriendos")+"</kbd>"
					    					+ "</a>"
					    					+ "</form>"
					    					+ "</td>"
					    					+ "<td style=\"text-align:center;vertical-align:middle;\">"
					    					+ "<form id=\"form1_"+lista1.get(1)+"\" class=\"formulario\" method=\"post\" action=\"/reporteMovimientosDetalle/\">"
					    					+ "<input type=\"hidden\" name=\"id_bodega\" value=\""+lista1.get(1)+"\">"
					    					+ "<input type=\"hidden\" name=\"fechaDesde\" value=\""+desdeAAMMDD+"\">"
					    					+ "<input type=\"hidden\" name=\"fechaHasta\" value=\""+hastaAAMMDD+"\">"
					    					+ "<input type=\"hidden\" name=\"esVenta\" value=\"1\">"
					    					+ "<input type=\"hidden\" name=\"uf\" value=\""+uf+"\">"
					    					+ "<input type=\"hidden\" name=\"usd\" value=\""+usd+"\">"
					    					+ "<input type=\"hidden\" name=\"eur\" value=\""+eur+"\">"
					    					+ "<a href=\"#\" onclick=\"document.getElementById('form1_"+lista1.get(1)+"').submit()\">"
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
	    			
	    	
	    			return ok(reporteMovimientosListaProyectos.render(mapeoDiccionario,mapeoPermiso,userMnu, tabla, Fechas.DDMMAA(desdeAAMMDD),Fechas.DDMMAA(hastaAAMMDD)));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reporteMovimientosDetalle(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodega").trim());
	       		String fechaDesde = form.get("fechaDesde").trim();
	       		String fechaHasta = form.get("fechaHasta").trim();
	       		String esVenta = form.get("esVenta").trim();
	       		Double uf = Double.parseDouble(form.get("uf").replaceAll(",", "").trim());
	       		Double usd = Double.parseDouble(form.get("usd").replaceAll(",", "").trim());
	       		Double eur = Double.parseDouble(form.get("eur").replaceAll(",", "").trim());
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			List<List<String>> datos = ReportMovimientos.movimientoGuias(con, s.baseDato, mapeoDiccionario, id_bodegaEmpresa, esVenta, fechaDesde, fechaHasta, usd, eur, uf);
	    			//agregar las tasas de uf usd eur
	    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
	    			
	    			String concepto = mapeoDiccionario.get("ARRIENDO");
	    			if(esVenta.equals("1")) {
	    				concepto = "VENTA";
	    			}
	    			con.close();
	    			return ok(reporteMovimientosDetalle.render(mapeoDiccionario,mapeoPermiso,userMnu,datos,bodega,esVenta,concepto,fechaDesde,fechaHasta, usd, eur, uf));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reporteMovimientosDetalleExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodega").trim());
	       		String fechaDesde = form.get("fechaDesde").trim();
	       		String fechaHasta = form.get("fechaHasta").trim();
	       		String esVenta = form.get("esVenta").trim();
	       		Double uf = Double.parseDouble(form.get("uf").replaceAll(",", "").trim());
	       		Double usd = Double.parseDouble(form.get("usd").replaceAll(",", "").trim());
	       		Double eur = Double.parseDouble(form.get("eur").replaceAll(",", "").trim());
	       		
	       		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			List<List<String>> datos = null;
    			BodegaEmpresa bodega = null;
    			
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			datos = ReportMovimientos.movimientoGuias(con, s.baseDato, mapeoDiccionario, id_bodegaEmpresa, esVenta, fechaDesde, fechaHasta, usd, eur, uf);
	    			bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
	    			con.close();
	       		} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		
	       		if(datos!=null && bodega!=null) {
	    			String concepto = mapeoDiccionario.get("ARRIENDO");
	    			if(esVenta.equals("1")) {
	    				concepto = "VENTA";
	    			}
	    			File file = ReportMovimientos.movimientosExcel(s.baseDato, datos, mapeoDiccionario, bodega, concepto, fechaDesde, fechaHasta);
	    			if(file!=null) {
		       			return ok(file,false,Optional.of("MovimientosPorBodegaValorizado.xlsx"));
		       		}
		       	}
	       	}
    	}
    	return ok("");
	}
	
	
	
	
	//====================================================================================
    // MNU reporteMovimientos2   Reportes/Movimientos/Ordenado por Ingreso-Egreso
    //====================================================================================
	
	public Result reporteMovimientosListaProyectosIE(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		String desdeAAMMDD = "2016-01-01";
       		String hastaAAMMDD = "3000-01-01";
       		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
       		try {
       			Connection con = dbRead.getConnection(dbRead);
       			Fechas hoy = Fechas.hoy();
    			Map<Long,Double> tasas = TasasCambio.mapTasasPorFecha(con, s.baseDato,hoy.getFechaStrAAMMDD(), mapeoDiccionario.get("pais"));
    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
    			List<Long> listIdBodegaEmpresa = ModCalc_InvInicial.listIdBodegaEmpresa(con, s.baseDato, permisoPorBodega);
    			Map<Long,Calc_BodegaEmpresa> mapBodegaEmpresa = Calc_BodegaEmpresa.mapAllBodegasVigentes(con, s.baseDato);
    			Map<String,Calc_Precio> mapPrecios = Calc_Precio.mapPrecios(con, s.baseDato, listIdBodegaEmpresa);
    			Map<Long,Calc_Precio> mapMaestroPrecios = Calc_Precio.mapMaestroPrecios(con, s.baseDato);
    			Map<String, Double> mapFijaTasas = BodegaEmpresa.mapFijaTasasAll(con, s.baseDato);
    			
    			List<Long> listIdGuia_fechaCorte = ModCalc_InvInicial.listIdGuia_fechaCorte(con, s.baseDato, desdeAAMMDD);
    			List<Inventarios> inventario = Inventarios.inventario(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_fechaCorte);
    			List<Long> listIdGuia_entreFechas = ModCalc_GuiasPer.listIdGuia_entreFecha(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
    			List<Inventarios> guiasPer = Inventarios.guiasPer(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_entreFechas);
    			List<Calc_AjustesEP> listaAjustes = Calc_AjustesEP.listaAjustesEntreFechas(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
    			Map<Long,List<String>> mapBodega = BodegaEmpresa.mapIdBod_BodegaEmpresaInternasExternas(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
    			Map<Long,Long> dec = Moneda.numeroDecimal(con, s.baseDato);
    			
    			Map<String,String> map = UsuarioPermiso.mapPermisoIdBodega(con, s.baseDato, Long.parseLong(s.id_usuario));
    			
    			Map<String,String> mapPermanencias = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, s.baseDato);
			
			con.close();
			
			
			
			
			List<ModCalc_InvInicial> inventarioInicial = ModCalc_InvInicial.resumenInvInicial(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa, 
					mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, listIdGuia_fechaCorte, inventario);
			
			List<ModCalc_GuiasPer> guiasPeriodo = ModCalc_GuiasPer.resumenGuiasPer(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, 
					mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, guiasPer, mapPermanencias);
			
			
			List<ModeloCalculo> listado = ModeloCalculo.valorTotalporBodega(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, inventarioInicial,guiasPeriodo, listaAjustes);
			
			List<List<String>> proyectosAux = ReportFacturas.reportFacturaProyecto(listado, mapBodega);
			
			List<List<String>> resumenTotales = ReportFacturas.resumenTotalesPorProyecto(listado, dec);
    			
    			
    			
    			List<List<String>> proyectos = new ArrayList<List<String>>();
    			if(map.size()>0) {
	    			for(List<String> aux:proyectosAux) {
	    				String idBodega = map.get(aux.get(1));
	    				if(idBodega!=null) {
	    					proyectos.add(aux);
	    				}
	    			}
    			}else {
    				proyectos = proyectosAux;
    			}
    			
    			Map<String,List<String>> mapTotal = new HashMap<String,List<String>>();
    			for(List<String> total: resumenTotales){
    				mapTotal.put(total.get(0), total);
    			}
    			
    			
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
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reporteMovimientosDetalleIE(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodega").trim());
	       		String esVenta = form.get("esVenta").trim();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			List<List<String>> datos = ReportMovimientos.movimientoGuiasIE(con, s.baseDato, id_bodegaEmpresa, esVenta);
	    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
	    			String concepto = mapeoDiccionario.get("ARRIENDO");
	    			if(esVenta.equals("1")) {
	    				concepto = "VENTA";
	    			}
	    			con.close();
	    			return ok(reporteMovimientosDetalleIE.render(mapeoDiccionario,mapeoPermiso,userMnu,datos,bodega,esVenta,concepto));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reporteMovimientosIEExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
	       		String esVenta = form.get("esVenta").trim();
	       		
	       		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		List<List<String>> datos = null;
    			BodegaEmpresa bodega = null;
    			
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			datos = ReportMovimientos.movimientoGuiasIE(con, s.baseDato, id_bodegaEmpresa, esVenta);
	    			bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
	    			con.close();
	       		} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		
	       		if(datos!=null && bodega!=null) {
	    			String concepto = mapeoDiccionario.get("ARRIENDO");
	    			if(esVenta.equals("1")) {
	    				concepto = "VENTA";
	    			}
	    			File file = ReportMovimientos.movimientosExcelIE(s.baseDato, datos, mapeoDiccionario, bodega, concepto);
	    			if(file!=null) {
		       			return ok(file,false,Optional.of("MovimientosPorBodegaIE.xlsx"));
		       		}
		       	}
	       	}
    	}
    	return ok("");
	}
	
	//====================================================================================
    // MNU reporteExcedentes   Reportes/Movimientos/Excedentes por bodega
    //====================================================================================
	
	public Result reporteExcedentesListaProyectos(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		try {
    			Connection con = dbRead.getConnection(dbRead);
    			if(mapeoPermiso.get("reporteExcedentes")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
    			List<List<String>> datos = ReportExcedentes.reportExcedentesSoloProyectosSelectivo(con, s.baseDato, permisoPorBodega, s.aplicaPorSucursal, s.id_sucursal);
    			con.close();
    			return ok(reporteExcedentesListaProyectos.render(mapeoDiccionario,mapeoPermiso,userMnu,datos));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reporteExcedentesDetalle(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodega").trim());
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			List<List<String>> datos = ReportExcedentes.reportExcedentesDetallePorProyecto(con, s.baseDato, id_bodegaEmpresa);
	    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
	    			con.close();
	    			return ok(reporteExcedentesDetalle.render(mapeoDiccionario,mapeoPermiso,userMnu,datos,bodega));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reporteExcedentesDetalleExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			List<List<String>> datos = ReportExcedentes.reportExcedentesDetallePorProyecto(con, s.baseDato, id_bodegaEmpresa);
	    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
	    			File file = ReportExcedentes.excedentesExcel(con, s.baseDato, datos, mapeoDiccionario, bodega);
	    			if(file!=null) {
		       			con.close();
		       			return ok(file,false,Optional.of("ExcedentesPorBodega.xlsx"));
		       		}else {
		       			con.close();
		       			return ok("");
		       		}
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok("");
	       	}
    	}else {
    		return ok("");
    	}
	}
	
	//====================================================================================
    // MNU reporteExcedentes   Reportes/Movimientos/Excedentes por Equipo
    //====================================================================================
	
	public Result reporteExcedentesListaEquipos(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		try {
    			Connection con = dbRead.getConnection(dbRead);
    			if(mapeoPermiso.get("reporteExcedentes")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
    			List<List<String>> datos = ReportExcedentes.reportExcedentesEquiposSelectivo(con, s.baseDato, permisoPorBodega, s.aplicaPorSucursal, s.id_sucursal);
    			con.close();
    			return ok(reporteExcedentesListaEquipos.render(mapeoDiccionario,mapeoPermiso,userMnu,datos));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reporteExcedentesDetalleEquipo(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String id_equipo = form.get("id_equipo").trim();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
	    			List<List<String>> datos = ReportExcedentes.reportExcedentesEquiposDetalle(con, s.baseDato, permisoPorBodega, s.aplicaPorSucursal, s.id_sucursal, id_equipo);
	    			Equipo equipo = Equipo.find(con, s.baseDato, Long.parseLong(id_equipo));
	    			con.close();
	    			return ok(reporteExcedentesDetalleEquipo.render(mapeoDiccionario,mapeoPermiso,userMnu,datos,equipo));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reporteExcedentesDetalleEquipoExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String id_equipo = form.get("id_equipo").trim();
	       		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		String permisoPorBodega = null;
	       		List<List<String>> datos = null;
	       		Equipo equipo = null;
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
		    			permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
		    			datos = ReportExcedentes.reportExcedentesEquiposDetalle(con, s.baseDato, permisoPorBodega, s.aplicaPorSucursal, s.id_sucursal, id_equipo);
		    			equipo = Equipo.find(con, s.baseDato, Long.parseLong(id_equipo));
		    		con.close();
	       		} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		if(permisoPorBodega!=null && datos!=null && equipo!=null) {
	       			File file = ReportExcedentes.excedentesExcelEquipo(s.baseDato, datos, mapeoDiccionario, equipo);
	    			if(file!=null) {
	    				return ok(file,false,Optional.of("ExcedentesPorBodega.xlsx"));
	    			}
	       		}
	       	}
    	}
    	return ok("");
	}
	
	//====================================================================================
    // MNU reportEstados   Reportes/Estados/Estados por Bodega/Cliente
    //====================================================================================
	
	public Result reporteEstadosTodos(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		try {
    			Connection con = dbRead.getConnection(dbRead);
    			if(mapeoPermiso.get("reportEstados")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
    			List<List<String>> datos = ReportInventarios.reportInventarioPorEstadosDeEquiposTodos(con, s.baseDato, permisoPorBodega, s.aplicaPorSucursal, s.id_sucursal);
    			con.close();
    			return ok(reporteEstadosTodos.render(mapeoDiccionario,mapeoPermiso,userMnu,datos));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reporteEstadosTodosDetalle(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodega").trim());
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
	    			List<TipoEstado> listTipoEstados = TipoEstado.allUsadosPorBodega(con, s.baseDato, bodega);
	    			Map<Long,Double> mapUnidadTiempo = UnidadTiempo.equivalencia(con, s.baseDato);
	    			Map<Long,List<List<String>>> mapDatos = new HashMap<Long,List<List<String>>>();
	    			for(TipoEstado x: listTipoEstados) {
	    				List<List<String>> datos = ReportMovimientos.movimientoGuiasPorEstado(con, s.baseDato, id_bodegaEmpresa, x.getId(), mapUnidadTiempo);
	    				mapDatos.put(x.getId(), datos);
	    			}
	    			con.close();
	    			return ok(reporteEstadosTodosDetalle.render(mapeoDiccionario,mapeoPermiso,userMnu,listTipoEstados,mapDatos,bodega));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reporteEstadosTodosDetalleExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
	    			List<TipoEstado> listTipoEstados = TipoEstado.allUsadosPorBodega(con, s.baseDato, bodega);
	    			Map<Long,Double> mapUnidadTiempo = UnidadTiempo.equivalencia(con, s.baseDato);
	    			Map<Long,List<List<String>>> mapDatos = new HashMap<Long,List<List<String>>>();
	    			for(TipoEstado x: listTipoEstados) {
	    				List<List<String>> datos = ReportMovimientos.movimientoGuiasPorEstado(con, s.baseDato, id_bodegaEmpresa, x.getId(), mapUnidadTiempo);
	    				mapDatos.put(x.getId(), datos);
	    			}
	    			File file = ReportMovimientos.estadosExcel(s.baseDato, listTipoEstados, mapDatos, bodega, mapeoDiccionario);
	    			if(file!=null) {
		       			con.close();
		       			return ok(file,false,Optional.of("EstadosPorBodega.xlsx"));
		       		}else {
		       			con.close();
		       			return ok("");
		       		}
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok("");
	       	}
    	}else {
    		return ok("");
    	}
	}
	
	//====================================================================================
    // MNU reportEstados   Reportes/Estados/Estados por Periodo
    //====================================================================================
	
	public Result reporteEstadosPeriodo0(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		try {
    			Connection con = dbRead.getConnection(dbRead);
    			if(mapeoPermiso.get("reportEstados")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Fechas hoy = Fechas.hoy();
    			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
    			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			con.close();
    			return ok(reporteEstadosPeriodo0.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reporteEstadosPeriodo1(Http.Request request) {
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
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
	    			List<List<String>> datos = ReportInventarios.reportInventarioPorEstadosPorPeriodo(con, s.baseDato, permisoPorBodega, desdeAAMMDD, hastaAAMMDD, s.aplicaPorSucursal, s.id_sucursal);
	    			List<List<String>> listado = ReportMovimientos.movGuiasPorEstadoPeriodo(con, s.baseDato, datos);
	    			

	    			con.close();
	    			return ok(reporteEstadosPeriodo1.render(mapeoDiccionario,mapeoPermiso,userMnu,desdeAAMMDD,hastaAAMMDD,listado));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    	
	}
	
	public Result reporteEstadosPeriodo1Excel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String desdeAAMMDD = form.get("desdeAAMMDD").trim();
	       		String hastaAAMMDD = form.get("hastaAAMMDD").trim();
	       		
	       		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			String permisoPorBodega = null;
    			List<List<String>> datos = null;
    			List<List<String>> listado = null;
	       		
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
	    			datos = ReportInventarios.reportInventarioPorEstadosPorPeriodo(con, s.baseDato, permisoPorBodega, desdeAAMMDD, hastaAAMMDD, s.aplicaPorSucursal, s.id_sucursal);
	    			listado = ReportMovimientos.movGuiasPorEstadoPeriodo(con, s.baseDato, datos);
	    			con.close();
	       		} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		
	       		if(permisoPorBodega!=null && datos!=null && listado!=null) {
	    			File file = ReportMovimientos.estadosExcelPeriodo(s.baseDato, mapeoDiccionario, desdeAAMMDD, hastaAAMMDD, listado);
	    			if(file!=null) {
		       			return ok(file,false,Optional.of("EstadosPorPeriodo.xlsx"));
		       		}
		       	}
	       	}
    	}
	   	return ok("");
	}
	
	//====================================================================================
    // MNU reportEstados   Reportes/Estados/Reparaciones por Bodega/Proyecto
    //====================================================================================
	
	public Result reporteEstadosAll0(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		try {
    			Connection con = dbRead.getConnection(dbRead);
    			if(mapeoPermiso.get("reportEstados")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
    			List<List<String>> datos = ReportInventarios.reportInventarioPorEstadosDeEquiposTodos(con, s.baseDato, permisoPorBodega, s.aplicaPorSucursal, s.id_sucursal);
    			con.close();
    			return ok(reporteEstadosAll0.render(mapeoDiccionario,mapeoPermiso,userMnu,datos));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reporteEstadosAll1(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			if(mapeoPermiso.get("reportEstados")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			BodegaEmpresa bodegaEmpresa = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
	    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
	    			List<List<String>> datos = ReportInventarios.reportInventarioPorEstadosAll(con, s.baseDato, permisoPorBodega, bodegaEmpresa.getId());
	    			con.close();
	    			return ok(reporteEstadosAll1.render(mapeoDiccionario,mapeoPermiso,userMnu, datos, bodegaEmpresa));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		return ok(mensajes.render("/",msgError));
	    	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reporteEstadosAll1Excel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
	    			BodegaEmpresa bodegaEmpresa = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
	    			List<List<String>> datos = ReportInventarios.reportInventarioPorEstadosAll(con, s.baseDato, permisoPorBodega, bodegaEmpresa.getId());
	    			File file = ReportInventarios.reporteEstadosAllExcel(s.baseDato, mapeoDiccionario, datos, bodegaEmpresa);
	    			if(file!=null) {
		       			con.close();
		       			return ok(file,false,Optional.of("ReporteEstadosTodo.xlsx"));
		       		}else {
		       			con.close();
		       			return ok("");
		       		}
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok("");
	       	}
    	}else {
    		return ok("");
    	}
	}
	
	//====================================================================================
    // MNU reportEstados   Reportes/Estados/Reparaciones por Periodo
    //====================================================================================
	
	public Result reporteEstadosPer0(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		try {
    			Connection con = dbRead.getConnection(dbRead);
    			if(mapeoPermiso.get("reporteMovimientos0")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Fechas hoy = Fechas.hoy();
    			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
    			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			con.close();
    			return ok(reporteEstadosPer0.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reporteEstadosPer1(Http.Request request) {
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
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			if(mapeoPermiso.get("reportEstados")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
	    			List<List<String>> datos = ReportInventarios.reportInventarioEstadosPorPer(con, s.baseDato, permisoPorBodega, desdeAAMMDD, hastaAAMMDD);
	    			con.close();
	    			return ok(reporteEstadosPer1.render(mapeoDiccionario,mapeoPermiso,userMnu, datos, desdeAAMMDD, hastaAAMMDD));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		return ok(mensajes.render("/",msgError));
	    	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reporteEstadosPer1Excel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String desdeAAMMDD = form.get("desdeAAMMDD").trim();
	       		String hastaAAMMDD = form.get("hastaAAMMDD").trim();
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
	    			List<List<String>> datos = ReportInventarios.reportInventarioEstadosPorPer(con, s.baseDato, permisoPorBodega, desdeAAMMDD, hastaAAMMDD);
	    			File file = ReportInventarios.reportInventarioEstadosPorPerExcel(s.baseDato, mapeoDiccionario, datos, desdeAAMMDD, hastaAAMMDD);
	    			if(file!=null) {
		       			con.close();
		       			return ok(file,false,Optional.of("ReporteEstadosPorPeriodo.xlsx"));
		       		}else {
		       			con.close();
		       			return ok("");
		       		}
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok("");
	       	}
    	}else {
    		return ok("");
    	}
	}
	
	//====================================================================================
    // MNU reportTrazaEquipo   Reportes/Trazabilidad
    //====================================================================================
	
	public Result reportTrazaEquipo(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		try {
    			Connection con = dbRead.getConnection(dbRead);
    			if(mapeoPermiso.get("reportTrazaEquipo")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<Equipo> datos = Equipo.allVigentes(con, s.baseDato);
    			con.close();
    			return ok(reportTrazaEquipo.render(mapeoDiccionario,mapeoPermiso,userMnu,datos));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reportTrazaEquipo2(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			List<List<String>> datos = ReportTrazabilidades.trazaEquipo(con, s.baseDato, id_equipo, mapeoDiccionario, s.aplicaPorSucursal, s.id_sucursal);
	    			con.close();
	    			return ok(reportTrazaEquipo2.render(mapeoDiccionario,mapeoPermiso,userMnu,datos,id_equipo));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reportTrazaEquipo2Excel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			List<List<String>> datos = ReportTrazabilidades.trazaEquipo(con, s.baseDato, id_equipo, mapeoDiccionario, s.aplicaPorSucursal, s.id_sucursal);
	    			File file = ReportTrazabilidades.trazaEquipoExcel(s.baseDato, datos, mapeoDiccionario);
	    			if(file!=null) {
		       			con.close();
		       			return ok(file,false,Optional.of("TrazabilidadPorEquipo.xlsx"));
		       		}else {
		       			con.close();
		       			return ok("");
		       		}
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok("");
	       	}
    	}else {
    		return ok("");
    	}
	}
	
	//====================================================================================
    // MNU reporteEjecutivo1   Reportes/Reporte Ejecutivo
    //====================================================================================
	
	public Result reporteEjecutivo1(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		try {
    			Connection con = dbRead.getConnection(dbRead);
    			if(mapeoPermiso.get("reporteEjecutivo1")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
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
    			Map<Long,TipoBodega> mapTipoBodega = TipoBodega.mapAll(con, s.baseDato);
    			Map<Long, Double> mapEquivalencia = UnidadTiempo.equivalencia(con,s.baseDato);
    			
    			List<List<String>> datos = ReportInventarios.reportInventarioEquipoConTipoBodega(con, s.baseDato, hoy.getFechaStrAAMMDD(), "", mapPCompra, mapPLista, moneda, 
    					mapeoDiccionario.get("ARRIENDO"), mapeoDiccionario, s.aplicaPorSucursal, s.id_sucursal, mapEquipo, mapSucursal, mapUnidadTiempo, tasasCorte, dec);
    			
    			List<String> valorizado = ReportEjecutivos.graficoDistribucionResumenValorizado(mapeoDiccionario, datos, "0", mapTipoBodega);
    			String grafInversion = valorizado.get(2);
    			
    			List<String> porUnidades = ReportEjecutivos.graficoDistribucionResumenPorUnidades(mapeoDiccionario, datos, "0", mapTipoBodega);

    			List<String> graficoOcupacionValorizado = ReportEjecutivos.graficoOcupacionPorGrupoValorizado(con, s.baseDato, mapeoDiccionario.get("pais"), s.aplicaPorSucursal, s.id_sucursal, 
    					mapTipoBodega, mapEquipo);
    			
    			List<String> valorizadoPorBodega = ReportEjecutivos.graficoDistribucionResumenValorizadoPorBodega(con, s.baseDato, mapeoDiccionario.get("pais"), s.aplicaPorSucursal, s.id_sucursal,
    					mapPCompra, tasasCorte, dec);
    			
    			List<String> graficoPotencialPorMesYGrupo = ReportEjecutivos.graficoPotencialPorMesYGrupo(con, s.baseDato, mapeoDiccionario.get("pais"), s.aplicaPorSucursal, s.id_sucursal,
    					tasasCorte, mapPLista, mapEquivalencia, dec);
    			
    			con.close();
    			return ok(reporteEjecutivo1.render(mapeoDiccionario,mapeoPermiso,userMnu,valorizado,porUnidades,grafInversion,graficoOcupacionValorizado,valorizadoPorBodega,graficoPotencialPorMesYGrupo));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result graficoInversionExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		try {
    			Connection con = dbRead.getConnection(dbRead);
    			if(mapeoPermiso.get("reporteEjecutivo1")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
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
    			Map<Long,TipoBodega> mapTipoBodega = TipoBodega.mapAll(con, s.baseDato);
    			
    			List<List<String>> datos = ReportInventarios.reportInventarioEquipoConTipoBodega(con, s.baseDato, hoy.getFechaStrAAMMDD(), "", mapPCompra, mapPLista, moneda, 
    					mapeoDiccionario.get("ARRIENDO"), mapeoDiccionario, s.aplicaPorSucursal, s.id_sucursal, mapEquipo, mapSucursal, mapUnidadTiempo, tasasCorte, dec);
    			
    			List<String> valorizado = ReportEjecutivos.graficoDistribucionResumenValorizado(mapeoDiccionario, datos, "0", mapTipoBodega);
    			
    			String grafInversion = valorizado.get(2);
    			File file = ReportEjecutivos.graficoInversion(s.baseDato, grafInversion, mapeoDiccionario);
    			if(file!=null) {
	       			con.close();
	       			return ok(file,false,Optional.of("GraficoInversion.xlsx"));
	       		}else {
	       			con.close();
	       			return ok("");
	       		}
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok("");
    	}else {
    		return ok("");
    	}
	}
	
	public Result graficoOcupacionExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		try {
    			Connection con = dbRead.getConnection(dbRead);
    			if(mapeoPermiso.get("reporteEjecutivo1")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			
    			Map<Long,Equipo> mapEquipo = Equipo.mapAllAll(con, s.baseDato);
    			Map<Long,TipoBodega> mapTipoBodega = TipoBodega.mapAll(con, s.baseDato);
    			
    			List<String> grafOcupacion = ReportEjecutivos.graficoOcupacionPorGrupoValorizado(con, s.baseDato, mapeoDiccionario.get("pais"), s.aplicaPorSucursal, s.id_sucursal, 
    					mapTipoBodega, mapEquipo);
    			
    			File file = ReportEjecutivos.graficoOcupacion(s.baseDato, grafOcupacion, mapeoDiccionario);
    			if(file!=null) {
	       			con.close();
	       			return ok(file,false,Optional.of("GraficoOcupacion.xlsx"));
	       		}else {
	       			con.close();
	       			return ok("");
	       		}
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok("");
    	}else {
    		return ok("");
    	}
	}
	
	public Result graficoValorizadoGrupoExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		 
    		try {
    			Connection con = dbRead.getConnection(dbRead);
    			if(mapeoPermiso.get("reporteEjecutivo1")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
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
    			Map<Long,TipoBodega> mapTipoBodega = TipoBodega.mapAll(con, s.baseDato);
    			
    			List<List<String>> datos = ReportInventarios.reportInventarioEquipoConTipoBodega(con, s.baseDato, hoy.getFechaStrAAMMDD(), "", mapPCompra, mapPLista, moneda, 
    					mapeoDiccionario.get("ARRIENDO"), mapeoDiccionario, s.aplicaPorSucursal, s.id_sucursal, mapEquipo, mapSucursal, mapUnidadTiempo, tasasCorte, dec);
    			
    			List<String> valorizado = ReportEjecutivos.graficoDistribucionResumenValorizado(mapeoDiccionario, datos, "0", mapTipoBodega);
    			
    			
    			File file = ReportEjecutivos.graficoValorizadoGrupo(s.baseDato, valorizado, mapeoDiccionario);
    			if(file!=null) {
	       			con.close();
	       			return ok(file,false,Optional.of("GraficoValorizadoGrupo.xlsx"));
	       		}else {
	       			con.close();
	       			return ok("");
	       		}
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok("");
    	}else {
    		return ok("");
    	}
	}
	
	public Result graficoUnidadesExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		 
    		try {
    			Connection con = dbRead.getConnection(dbRead);
    			if(mapeoPermiso.get("reporteEjecutivo1")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
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
    			Map<Long,TipoBodega> mapTipoBodega = TipoBodega.mapAll(con, s.baseDato);
    			
    			List<List<String>> datos = ReportInventarios.reportInventarioEquipoConTipoBodega(con, s.baseDato, hoy.getFechaStrAAMMDD(), "", mapPCompra, mapPLista, moneda, 
    					mapeoDiccionario.get("ARRIENDO"), mapeoDiccionario, s.aplicaPorSucursal, s.id_sucursal, mapEquipo, mapSucursal, mapUnidadTiempo, tasasCorte, dec);
    			
    			List<String> porUnidades = ReportEjecutivos.graficoDistribucionResumenPorUnidades(mapeoDiccionario, datos, "0", mapTipoBodega);
    			
    			File file = ReportEjecutivos.graficoUnidades(s.baseDato, porUnidades, mapeoDiccionario);
    			if(file!=null) {
	       			con.close();
	       			return ok(file,false,Optional.of("GraficoPorUnidadesGrupo.xlsx"));
	       		}else {
	       			con.close();
	       			return ok("");
	       		}
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok("");
    	}else {
    		return ok("");
    	}
	}
	
	public Result graficoValorizadoBodegaExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		 
    		try {
    			Connection con = dbRead.getConnection(dbRead);
    			if(mapeoPermiso.get("reporteEjecutivo1")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			
    			Map<Long,List<Double>> mapPCompra = Compra.ultimoPrecio(con, s.baseDato);
    			Fechas hoy = Fechas.hoy();
    			
    			Map<Long,Double> tasasCorte = TasasCambio.mapTasasPorFecha(con, s.baseDato,hoy.getFechaStrAAMMDD(), mapeoDiccionario.get("pais"));
    			Map<Long,Long> dec = Moneda.numeroDecimal(con, s.baseDato);
    			
    			List<String> valorizadoPorBodega = ReportEjecutivos.graficoDistribucionResumenValorizadoPorBodega(con, s.baseDato, mapeoDiccionario.get("pais"), s.aplicaPorSucursal, s.id_sucursal,
    					mapPCompra, tasasCorte, dec);
    			
    			File file = ReportEjecutivos.graficoValorizadoBodega(s.baseDato, valorizadoPorBodega, mapeoDiccionario);
    			if(file!=null) {
	       			con.close();
	       			return ok(file,false,Optional.of("GraficoValorizadoBodega.xlsx"));
	       		}else {
	       			con.close();
	       			return ok("");
	       		}
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok("");
    	}else {
    		return ok("");
    	}
	}
	
	public Result graficoPotencialExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		 
    		try {
    			Connection con = dbRead.getConnection(dbRead);
    			if(mapeoPermiso.get("reporteEjecutivo1")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			
    			Map<Long,List<Double>> mapPLista = Precio.maestroPListaPorSucursal(con, s.baseDato, Long.parseLong(s.id_sucursal));
    			Fechas hoy = Fechas.hoy();
    			
    			Map<Long,Double> tasasCorte = TasasCambio.mapTasasPorFecha(con, s.baseDato,hoy.getFechaStrAAMMDD(), mapeoDiccionario.get("pais"));
    			Map<Long,Long> dec = Moneda.numeroDecimal(con, s.baseDato);
    			Map<Long, Double> mapEquivalencia = UnidadTiempo.equivalencia(con,s.baseDato);
    			
    			List<String> graficoPotencialPorMesYGrupo = ReportEjecutivos.graficoPotencialPorMesYGrupo(con, s.baseDato, mapeoDiccionario.get("pais"), s.aplicaPorSucursal, s.id_sucursal,
    					tasasCorte, mapPLista, mapEquivalencia, dec);
    			
    			File file = ReportEjecutivos.graficoPotencial(s.baseDato, graficoPotencialPorMesYGrupo, mapeoDiccionario);
    			if(file!=null) {
	       			con.close();
	       			return ok(file,false,Optional.of("GraficoPotencialPorMesYGrupo.xlsx"));
	       		}else {
	       			con.close();
	       			return ok("");
	       		}
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok("");
    	}else {
    		return ok("");
    	}
	}
	
	//====================================================================================
    // MNU reporteGerencial1   Reportes/Reporte Gerencial/Reporte Gerencial
    //====================================================================================
	
	public Result reporteGerencial(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		try {
    			Connection con = dbWrite.getConnection();
    			if(mapeoPermiso.get("reporteGerencial1")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Long id_grupo = (long)0;
    			List<String> series = ReportGerenciales.graficoCategoriasAnio(con, s.baseDato, id_grupo, mapeoDiccionario, "0", "1");
    			con.close();
    			return ok(reporteGerencial.render(mapeoDiccionario,mapeoPermiso,userMnu,series,"TODO EL INVENTARIO"));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	//====================================================================================
    // MNU reporteGerencial2   Reportes/Reporte Gerencial/Reporte por Grupos
    //====================================================================================
	
	public Result reporteGerencialGrupo(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		try {
    			Connection con = dbRead.getConnection(dbRead);
    			if(mapeoPermiso.get("reporteGerencial2")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<Grupo> lista = Grupo.all(con, s.baseDato);
    			con.close();
    			return ok(reporteGerencialGrupo.render(mapeoDiccionario,mapeoPermiso,userMnu,lista));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reporteGerencialGrupoDetalle(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_grupo = Long.parseLong(form.get("id_grupo").trim());
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	    			Connection con = dbWrite.getConnection();
	    			
	    			Grupo grupo = Grupo.find(con, s.baseDato, id_grupo);
	    			List<String> series = ReportGerenciales.graficoCategoriasAnioPorGrupo(con, s.baseDato, id_grupo, mapeoDiccionario, s.aplicaPorSucursal, s.id_sucursal);
	    			con.close();
	    			return ok(reporteGerencial.render(mapeoDiccionario,mapeoPermiso,userMnu,series,"(NO CONSIDERA AJUSTES)  GRUPO: "+grupo.nombre));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	//====================================================================================
    // MNU reporteGerencial2   Reportes/Reporte Gerencial/Resumen KG movidos
    //====================================================================================
	
	public Result reportGerenKGPorPeriodo0(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = dbRead.getConnection(dbRead);
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("reporteGerencial2")==null) {
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			
    			Fechas hoy = Fechas.hoy();
    			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
    			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			con.close();
    			return ok(reportGerenKGPorPeriodo0.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}
    	return ok(mensajes.render("/",msgError));
	}
	
	@SuppressWarnings("unchecked")
	public Result reportGerenKGPorPeriodo1(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
			
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String desdeAAMMDD = form.get("fechaDesde").trim();
	       		String hastaAAMMDD = form.get("fechaHasta").trim();
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			HashMap<String, Object> map = ReportKilos.reportKgArrPorGrupoPorPeriodo(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
	    			List<String> cabecera1 = (List<String>) map.get("cabecera1");
	    			List<String> cabecera2 = (List<String>) map.get("cabecera2");
	    			List<List<String>> datos = (List<List<String>>) map.get("datos");
	    			con.close();
	    			return ok(reportGerenKGPorPeriodo1.render(mapeoDiccionario,mapeoPermiso,userMnu,cabecera1,cabecera2,datos, desdeAAMMDD, hastaAAMMDD));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	@SuppressWarnings("unchecked")
	public Result reportGerenKGPorPeriodo1Excel(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String desdeAAMMDD = form.get("desde").trim();
	       		String hastaAAMMDD = form.get("hasta").trim();
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			HashMap<String, Object> map = ReportKilos.reportKgArrPorGrupoPorPeriodo(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
	    			List<String> cabecera1 = (List<String>) map.get("cabecera1");
	    			List<String> cabecera2 = (List<String>) map.get("cabecera2");
	    			List<List<String>> datos = (List<List<String>>) map.get("datos");
	    			File file = ReportKilos.reportGerenKGPorPeriodo1Excel(s.baseDato, mapeoDiccionario, cabecera1, cabecera2, datos, desdeAAMMDD, hastaAAMMDD);
	    			if(file!=null) {
		       			con.close();
		       			return ok(file,false,Optional.of("ReporteToneladasMovidas.xlsx"));
		       		}else {
		       			con.close();
		       			return ok("");
		       		}
	       		} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
	   		return ok("");
    	}else {
    		return ok("");
    	}
    }
	
	public Result reporteGerencialKG(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		try {
    			Connection con = dbWrite.getConnection();
    			
    			if(mapeoPermiso.get("reporteGerencial2")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Fechas hoy = Fechas.hoy();
    			String anioActual = hoy.getFechaStrAAMMDD().substring(0,4);
    			Long dePaso = Long.parseLong(anioActual)-1;
    			String anioAnterior = dePaso.toString();
    			dePaso = dePaso -1;
    			String anioAnteriorAnterior = dePaso.toString();
    			List<List<String>> datosActual = ReportKilos.reportKilosArriendoPorGrupoAnual(con, s.baseDato, Long.parseLong(anioActual));
    			List<List<String>> datosAnterior = ReportKilos.reportKilosArriendoPorGrupoAnual(con,s.baseDato, Long.parseLong(anioAnterior));
    			List<List<String>> datosAnteriorAnterior = ReportKilos.reportKilosArriendoPorGrupoAnual(con,s.baseDato, Long.parseLong(anioAnteriorAnterior));
    			con.close();
    			return ok(reporteGerencialKG.render(mapeoDiccionario,mapeoPermiso,userMnu,datosActual,datosAnterior,datosAnteriorAnterior, anioActual,anioAnterior,anioAnteriorAnterior));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reporteGerencialKGExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		 
    		try {
    			Connection con = dbWrite.getConnection();
    			if(mapeoPermiso.get("reporteEjecutivo1")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
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
    			
    			if(file!=null) {
	       			con.close();
	       			return ok(file,false,Optional.of("ReporteToneladasMovidas.xlsx"));
	       		}else {
	       			con.close();
	       			return ok("");
	       		}
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok("");
    	}else {
    		return ok("");
    	}
	}
	
	//====================================================================================
    // MNU reportGraficoGrupo1   Reportes/Graficos
    //====================================================================================
	
	public Result reportGraficoMovResumen(Http.Request request, String filtroGrupos, String vistaCheckBox ) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		try {
    			Connection con = dbWrite.getConnection();
    			
    			if(mapeoPermiso.get("reportGraficoGrupo1")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
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
    			Map<Long,TipoBodega> mapTipoBodega = TipoBodega.mapAll(con, s.baseDato);
    			
    			List<List<String>> datos = ReportInventarios.reportInventarioEquipoConTipoBodega(con, s.baseDato, hoy.getFechaStrAAMMDD(), "", mapPCompra, mapPLista, moneda, 
    					mapeoDiccionario.get("ARRIENDO"), mapeoDiccionario, s.aplicaPorSucursal, s.id_sucursal, mapEquipo, mapSucursal, mapUnidadTiempo, tasasCorte, dec);

    			List<String> valorizado = ReportEjecutivos.graficoDistribucionResumenValorizado(mapeoDiccionario, datos, "0", mapTipoBodega);
    			
    			List<String> porUnidades = ReportEjecutivos.graficoDistribucionResumenPorUnidades(mapeoDiccionario, datos, "0", mapTipoBodega);
    			
    			List<Grupo> listGrupos = Grupo.all(con, s.baseDato);
    			
    			String subtitulo="RESUMEN POR "+mapeoDiccionario.get("BODEGA")+"/INTERNA y "+mapeoDiccionario.get("BODEGA")+"/PROYECTO";
    			con.close();
    			return ok(reportGraficoMovResumen.render(mapeoDiccionario,mapeoPermiso,userMnu,valorizado,porUnidades,listGrupos,vistaCheckBox,subtitulo));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result refreshGraficoMovResumen(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		FormGrafico form = formFactory.form(FormGrafico.class).withDirectFieldAccess(true).bindFromRequest(request).get();
    		if (form.listIdGrupos==null) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		List<String> listIdGrupos = form.listIdGrupos;
	       		try {
	       			Connection con = dbWrite.getConnection();
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
	    			con.close();
	    			return (reportGraficoMovResumen(request,listIdGrupos.toString(),vistaCheckBox));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reportGraficoMovPorGrupo(Http.Request request, String filtroGrupos, String filtroBodegas, String vistaCheckBox, String idTipoBodega ) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		try {
    			Connection con = dbWrite.getConnection();
    			
    			if(mapeoPermiso.get("reportGraficoGrupo2")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			
    			String subtitulo="";
    			if(idTipoBodega.equals("0")||idTipoBodega.equals("")) {
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
    			
    			List<String> valorizado = ReportGraficos.graficoDistribucionGrupoBodegasValorizado(mapeoDiccionario, filtroGrupos, filtroBodegas, idTipoBodega, 
    					s.aplicaPorSucursal, s.id_sucursal, datos);
    			
    			List<String> porUnidades = ReportGraficos.graficoDistribucionGrupoBodegasPorUnidades(mapeoDiccionario, filtroGrupos, filtroBodegas,idTipoBodega, 
    					s.aplicaPorSucursal, s.id_sucursal, datos);
    		
    			String filtroPorNombreBodega = valorizado.get(2);
    			String filtroPorNombreGrupo = valorizado.get(3);
    			
    			List<Grupo> listGrupos = Grupo.allFiltroPorNombre(con, s.baseDato, filtroPorNombreGrupo);
    			
    			List<BodegaEmpresa> listBodegas2 = BodegaEmpresa.allFiltroPorNombre(con, s.baseDato, filtroPorNombreBodega);

    			con.close();
    			return ok(reportGraficoMovPorGrupo.render(mapeoDiccionario,mapeoPermiso,userMnu,valorizado,porUnidades,listGrupos,listBodegas2,vistaCheckBox,idTipoBodega,subtitulo));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result refreshGraficoMovPorGrupo(Http.Request request, String idTipoBodega) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		FormGrafico form = formFactory.form(FormGrafico.class).withDirectFieldAccess(true).bindFromRequest(request).get();
    		if (form.listIdGruposAll==null) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
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
	       		
	       		try {
	       			Connection con = dbWrite.getConnection();
	    			
	    			List<Grupo> listGrupos = Grupo.allFiltroPorId(con, s.baseDato, listIdGruposAll.toString().substring(1,listIdGruposAll.toString().length()-1));
	    			List<BodegaEmpresa> listBodegas = BodegaEmpresa.allFiltroPorId(con, s.baseDato, listIdBodegasAll.toString().substring(1,listIdBodegasAll.toString().length()-1));
	    			
	    			String vistaCheckBox = "<h6>GRUPOS:</h6>";
	    	  		for(int i=0;i<listGrupos.size();i++){
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
	    			con.close();
	    			return (reportGraficoMovPorGrupo(request,listIdGrupos.toString(),listIdBodegas.toString(),vistaCheckBox,idTipoBodega));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reportGraficoMovUso(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		try {
    			Connection con = dbWrite.getConnection();
    			
    			if(mapeoPermiso.get("reportGraficoOcupacion1")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			
    			Map<Long,Equipo> mapEquipo = Equipo.mapAllAll(con, s.baseDato);
    			Map<Long,TipoBodega> mapTipoBodega = TipoBodega.mapAll(con, s.baseDato);
    			
    			List<String> graficoOcupacionValorizado = ReportEjecutivos.graficoOcupacionPorGrupoValorizado(con, s.baseDato, mapeoDiccionario.get("pais"), s.aplicaPorSucursal, s.id_sucursal, 
    					mapTipoBodega, mapEquipo);
    			
    			List<String> graficoOcupacionUnidades = ReportGraficos.graficoOcupacionPorGrupoUnidades(con, s.baseDato, mapeoDiccionario.get("pais"), s.aplicaPorSucursal, s.id_sucursal);
    			con.close();
    			return ok(reportGraficoMovUso.render(mapeoDiccionario,mapeoPermiso,userMnu,graficoOcupacionValorizado,graficoOcupacionUnidades));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	//====================================================================================
    // MNU reportFacturaDetalleProyecto   Reportes/Proforma/ClienteProyecto
    //====================================================================================
	
	public Result reportFacturaPeriodo(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		try {
    			Connection con = dbRead.getConnection(dbRead);
    			
    			if(mapeoPermiso.get("reportFacturaDetalleProyecto")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Fechas hoy = Fechas.hoy();
    			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
    			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			TasasCambio tasas = TasasCambio.allDeUnaFecha(con, s.baseDato, mapeoDiccionario.get("pais"),hasta);
    			con.close();
    			return ok(reportFacturaPeriodo.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta, tasas));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reportFacturaProyecto(Http.Request request, String archivoPDF) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
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
	       		try {
	       			Connection con = dbRead.getConnection(dbRead);
	    			
		    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
		    			List<Long> listIdBodegaEmpresa = ModCalc_InvInicial.listIdBodegaEmpresa(con, s.baseDato, permisoPorBodega);
		    			Map<Long,Calc_BodegaEmpresa> mapBodegaEmpresa = Calc_BodegaEmpresa.mapAllBodegasVigentes(con, s.baseDato);
		    			Map<String,Calc_Precio> mapPrecios = Calc_Precio.mapPrecios(con, s.baseDato, listIdBodegaEmpresa);
		    			Map<Long,Calc_Precio> mapMaestroPrecios = Calc_Precio.mapMaestroPrecios(con, s.baseDato);
		    			Map<String, Double> mapFijaTasas = BodegaEmpresa.mapFijaTasasAll(con, s.baseDato);
		    			
		    			List<Long> listIdGuia_fechaCorte = ModCalc_InvInicial.listIdGuia_fechaCorte(con, s.baseDato, desdeAAMMDD);
		    			List<Inventarios> inventario = Inventarios.inventario(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_fechaCorte);
		    			List<Long> listIdGuia_entreFechas = ModCalc_GuiasPer.listIdGuia_entreFecha(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
		    			List<Inventarios> guiasPer = Inventarios.guiasPer(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_entreFechas);
		    			List<Calc_AjustesEP> listaAjustes = Calc_AjustesEP.listaAjustesEntreFechas(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
		    			Map<Long,List<String>> mapBodega = BodegaEmpresa.mapIdBod_BodegaEmpresaInternasExternas(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
		    			Map<Long,Long> dec = Moneda.numeroDecimal(con, s.baseDato);
		    			
		    			Map<String,String> map = UsuarioPermiso.mapPermisoIdBodega(con, s.baseDato, Long.parseLong(s.id_usuario));
		    			
		    			Map<String,String> mapPermanencias = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, s.baseDato);
	    			
	    			con.close();
	    			
	    			
	    			List<ModCalc_InvInicial> inventarioInicial = ModCalc_InvInicial.resumenInvInicial(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa, 
	    					mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, listIdGuia_fechaCorte, inventario);
	    			
	    			List<ModCalc_GuiasPer> guiasPeriodo = ModCalc_GuiasPer.resumenGuiasPer(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, 
	    					mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, guiasPer, mapPermanencias);
	    			
	    			
	    			List<ModeloCalculo> listado = ModeloCalculo.valorTotalporBodega(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, inventarioInicial,guiasPeriodo, listaAjustes);
	    			
	    			
	    			List<List<String>> proyectosAux = ReportFacturas.reportFacturaProyecto(listado, mapBodega);
	    			
	    			List<List<String>> resumenTotales = ReportFacturas.resumenTotalesPorProyecto(listado, dec);
	    			
	    			
	    			
	    			List<List<String>> proyectos = new ArrayList<List<String>>();
	    			if(map.size()>0) {
		    			for(List<String> aux: proyectosAux) {
		    				String idBodega = map.get(aux.get(1));
		    				if(idBodega!=null) {
		    					proyectos.add(aux);
		    				}
		    			}
	    			}else {
	    				for(int i=0;i<proyectosAux.size();i++ ) {
	    					proyectos.add(proyectosAux.get(i));
	    				}
	    				
	    			}
	    			
	    			String tabla = ""
	    					+ " <table id=\"tablaPrincipal\" class=\"table table-sm table-hover table-bordered table-condensed table-fluid\">\n"
	    					+ "		<thead style=\"background-color: #eeeeee\">\n"
	    					+ "			<TR> \n"
	    					+ "				<TH style= \"text-align: center;vertical-align: top;\">SUCURSAL</TH>\n"
	    					+ "				<TH style= \"text-align: center;vertical-align: top;\">COMERCIAL</TH>\n"
	    					+ "				<TH width=\"5%\" >DETALLE<BR></TH>\n"
	    					+ "				<TH style= \"text-align: center;vertical-align: top;\">"+mapeoDiccionario.get("BODEGA")+"/PROYECTO</TH>\n"
	    					+ "				<TH style= \"text-align: center;vertical-align: top;\">NOMBRE<BR>CLIENTE</TH>\n"
	    					+ "				<TH style= \"text-align: center;vertical-align: top;\">NOMBRE<br>PROYECTO</TH>\n"
	    					+ "				<TH style= \"text-align:center;vertical-align:top;\">CFI (en "+mapeoDiccionario.get("PESOS")+")</TH>\n"
	    					+ "				<TH style= \"text-align:center;vertical-align:top;\">SubTotal<br>"+mapeoDiccionario.get("ARRIENDO")+"</TH>\n"
	    					+ "				<TH style= \"text-align:center;vertical-align:top;\">SubTotal<br>VENTA</TH>\n"
	    					+ "				<TH style= \"text-align:center;vertical-align:top;\">Ajustes<BR>("+mapeoDiccionario.get("ARRIENDO")+")</TH>\n"
	    					+ "				<TH style= \"text-align:center;vertical-align:top;\">Ajustes<BR>(VENTA)</TH>\n"
	    					+ "				<TH style= \"text-align:center;vertical-align:top;\">TOTAL<BR>(en "+mapeoDiccionario.get("PESOS")+")</TH>\n"
	    					+ "				<TH width=\"5%\" >DETALLE<BR></TH>\n"
	    					+ "			</TR>\n"
	    					+ "			</thead>\n"
	    					+ "			<tbody>\n";
	    					
	    					for(List<String> lista1: proyectos){
	    						tabla += ""
	    								+ "<TR>\n";
	    						for(List<String> total: resumenTotales){
	    							if(lista1.get(1).equals(total.get(0))){
	    								
	    								Double auxUf = uf;
	    								Double auxUsd = usd;
	    								Double auxEur = eur;
	    								
	    								for (Map.Entry<Long, Double> entry : tasas.entrySet()) {
	    				        	  		Long k = entry.getKey();
	    				    				Double aux = mapFijaTasas.get(lista1.get(1) + "_" +k);
	    				    				if(aux != null) {
	    				    					if(k == (long)2) {
	    				    						auxUsd = aux;
	    				    					}else if (k == (long)3) {
	    				    						auxEur = aux;
	    				    					}else if (k == (long)4) {
	    				    						auxUf = aux;
	    				    					}
	    				    				}
	    				    			}
	    								
	    								tabla += ""
    										+ "<td style=\"text-align:left;vertical-align:middle;\">"+lista1.get(14)+"</td>\n"
    										+ "<td style=\"text-align:left;vertical-align:middle;\">"+lista1.get(10)+"</td>\n"
    												+ "<td style=\"text-align:center;vertical-align:middle;\">\n"
    	    				    					+ "	<form id=\"form0_"+lista1.get(1)+"\" class=\"formulario\" method=\"post\" action=\"/reportFacturaProyectoDetalle/\">\n"
    	    				    					+ "		<input type=\"hidden\" class=\"idBodega\" name=\"id_bodega\" value=\""+lista1.get(1)+"\">\n"
    	    				    					+ "		<input type=\"hidden\" name=\"fechaDesde\" value=\""+desdeAAMMDD+"\">\n"
    	    				    					+ "		<input type=\"hidden\" name=\"fechaHasta\" value=\""+hastaAAMMDD+"\">\n"
    	    				    					+ "		<input type=\"hidden\" name=\"esVenta\" value=\"0\">\n"
    	    				    					+ "		<input type=\"hidden\" name=\"uf\" value=\""+uf+"\">\n"
    	    				    					+ "		<input type=\"hidden\" name=\"usd\" value=\""+usd+"\">\n"
    	    				    					+ "		<input type=\"hidden\" name=\"eur\" value=\""+eur+"\">\n"
    	    				    					+ "		<a href=\"#\" onclick=\"document.getElementById('form0_"+lista1.get(1)+"').submit()\">\n"
    	    				    					+ "			<kbd style=\"background-color: #73C6B6\">Emitir</kbd>\n"
    	    				    					+ "		</a>\n"
    	    				    					+ "	</form>\n"
    	    				    			+ "</td>\n"
    				    					+ "<td style=\"text-align:left;vertical-align:middle;\"><a href=\"#\" onclick=\"modalContactoBodega('"+lista1.get(1)+"');\">"+lista1.get(5)+"</a></td>\n"
    				    					+ "<td style=\"text-align:left;vertical-align:middle;\"><a href=\"#\" onclick=\"modalContactoCliente('"+lista1.get(2)+"');\">"+lista1.get(7)+"</a></td>\n"
    				    					+ "<td style=\"text-align:left;vertical-align:middle;\"><a href=\"#\" onclick=\"modalContactoProyecto('"+lista1.get(3)+"');\">"+lista1.get(8)+"</a></td>\n"
    				    					+ "<td style= \"text-align:right;\" class=\"cfi\">"+total.get(3)+"</td>\n"
    				    					+ "<td style= \"text-align:right;\" class=\"arr\">"+total.get(1)+"</td>\n"
    				    					+ "<td style= \"text-align:right;\" class=\"vta\">"+total.get(2)+"</td>\n"
    				    					+ "<td style= \"text-align:right;\" class=\"ajustArr\">"+total.get(5)+"</td>\n"
    				    					+ "<td style= \"text-align:right;\" class=\"ajustVta\">"+total.get(6)+"</td>\n"
    				    					+ "<td style= \"text-align:right;\" class=\"granTotal\">"+total.get(4)+"</td>\n"
    				    					+ "<td style=\"text-align:center;vertical-align:middle;\">\n"
	    				    					+ "	<form id=\"form0_"+lista1.get(1)+"\" class=\"formulario\" method=\"post\" action=\"/reportFacturaProyectoDetalle/\">\n"
	    				    					+ "		<input type=\"hidden\" name=\"id_bodega\" value=\""+lista1.get(1)+"\">\n"
	    				    					+ "		<input type=\"hidden\" name=\"fechaDesde\" value=\""+desdeAAMMDD+"\">\n"
	    				    					+ "		<input type=\"hidden\" name=\"fechaHasta\" value=\""+hastaAAMMDD+"\">\n"
	    				    					+ "		<input type=\"hidden\" name=\"esVenta\" value=\"0\">\n"
	    				    					+ "		<input type=\"hidden\" name=\"uf\" value=\""+auxUf+"\">\n"
	    				    					+ "		<input type=\"hidden\" name=\"usd\" value=\""+auxUsd+"\">\n"
	    				    					+ "		<input type=\"hidden\" name=\"eur\" value=\""+auxEur+"\">\n"
	    				    					+ "		<a href=\"#\" onclick=\"document.getElementById('form0_"+lista1.get(1)+"').submit()\">\n"
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
	    			
	    			
	    			return ok(reportFacturaProyecto.render(mapeoDiccionario,mapeoPermiso,userMnu,tabla,desdeAAMMDD,hastaAAMMDD, usd, eur, uf, 
	    					Fechas.DDMMAA(desdeAAMMDD), Fechas.DDMMAA(hastaAAMMDD),archivoPDF));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reportFacturaProyectoExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
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
	       		try {
	       			Connection con = dbRead.getConnection(dbRead);
	    			
		    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
		    			List<Long> listIdBodegaEmpresa = ModCalc_InvInicial.listIdBodegaEmpresa(con, s.baseDato, permisoPorBodega);
		    			Map<Long,Calc_BodegaEmpresa> mapBodegaEmpresa = Calc_BodegaEmpresa.mapAllBodegasVigentes(con, s.baseDato);
		    			Map<String,Calc_Precio> mapPrecios = Calc_Precio.mapPrecios(con, s.baseDato, listIdBodegaEmpresa);
		    			Map<Long,Calc_Precio> mapMaestroPrecios = Calc_Precio.mapMaestroPrecios(con, s.baseDato);
		    			Map<String, Double> mapFijaTasas = BodegaEmpresa.mapFijaTasasAll(con, s.baseDato);
		    			
		    			List<Long> listIdGuia_fechaCorte = ModCalc_InvInicial.listIdGuia_fechaCorte(con, s.baseDato, desdeAAMMDD);
		    			List<Inventarios> inventario = Inventarios.inventario(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_fechaCorte);
		    			List<Long> listIdGuia_entreFechas = ModCalc_GuiasPer.listIdGuia_entreFecha(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
		    			List<Inventarios> guiasPer = Inventarios.guiasPer(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_entreFechas);
		    			List<Calc_AjustesEP> listaAjustes = Calc_AjustesEP.listaAjustesEntreFechas(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
		    			Map<Long,List<String>> mapBodega = BodegaEmpresa.mapIdBod_BodegaEmpresaInternasExternas(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
		    			Map<Long,Long> dec = Moneda.numeroDecimal(con, s.baseDato);
		    			
		    			Map<String,String> map = UsuarioPermiso.mapPermisoIdBodega(con, s.baseDato, Long.parseLong(s.id_usuario));
		    			
		    			Map<String,String> mapPermanencias = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, s.baseDato);
	    			
	    			con.close();
    			
    			
    			
    			
    			List<ModCalc_InvInicial> inventarioInicial = ModCalc_InvInicial.resumenInvInicial(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa, 
    					mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, listIdGuia_fechaCorte, inventario);
    			
    			List<ModCalc_GuiasPer> guiasPeriodo = ModCalc_GuiasPer.resumenGuiasPer(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, 
    					mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, guiasPer, mapPermanencias);
    			
    			
    			List<ModeloCalculo> listado = ModeloCalculo.valorTotalporBodega(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, inventarioInicial,guiasPeriodo, listaAjustes);
    			
    			
    			List<List<String>> proyectosAux = ReportFacturas.reportFacturaProyecto(listado, mapBodega);
    			
    			List<List<String>> resumenTotales = ReportFacturas.resumenTotalesPorProyecto(listado, dec);
	       			
	    			List<List<String>> proyectos = new ArrayList<List<String>>();
	    			if(map.size()>0) {
		    			for(List<String> aux: proyectosAux) {
		    				String idBodega = map.get(aux.get(1));
		    				if(idBodega!=null) {
		    					proyectos.add(aux);
		    				}
		    			}
	    			}else {
	    				for(int i=0;i<proyectosAux.size();i++ ) {
	    					proyectos.add(proyectosAux.get(i));
	    				}
	    				
	    			}
	    			
	    			
	    			File file = ReportFacturas.exportaProformaExcelProyectos(s.baseDato, mapeoDiccionario,
	    					proyectos,desde,hasta,uf,usd,eur,resumenTotales);
	    			
	    			if(file!=null) {
		       			return ok(file,false,Optional.of("EP_Proforma_Listado.xlsx"));
		       		}else {
		       			return ok("");
		       		}
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok("");
	       	}
    	}else {
    		return ok("");
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
			try {
    			Connection con0 = dbWrite.getConnection();
    					Parametros.modify(con0, s.baseDato, "envioMasivoProformas", (long)2);
	    		con0.close();
            } catch (SQLException e) {
    			e.printStackTrace();
    		}
			Map<Long,Double> tasas = new HashMap<Long,Double>();
    		tasas.put((long)1, (double) 1); 	// 'Peso Chileno', 'CLP', '0'
    		tasas.put((long)2, usd); 			// 'Dólar', 'USD', '2'
    		tasas.put((long)3, eur); 			// 'Euro', 'EUR', '3'
    		tasas.put((long)4, uf); 			// 'Unidad Fomento', 'UF', '4'
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
			String nros = "";
			try {
    			Connection con = dbRead.getConnection(dbRead);
    					Parametros.modify(con, s.baseDato, "envioMasivoProformas", (long)2);
		    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
		    			List<Long> listIdBodegaEmpresa = ModCalc_InvInicial.listIdBodegaEmpresa(con, s.baseDato, permisoPorBodega);
		    			Map<Long,Calc_BodegaEmpresa> mapBodegaEmpresa = Calc_BodegaEmpresa.mapAllBodegasVigentes(con, s.baseDato);
		    			Map<String,Calc_Precio> mapPrecios = Calc_Precio.mapPrecios(con, s.baseDato, listIdBodegaEmpresa);
		    			Map<Long,Calc_Precio> mapMaestroPrecios = Calc_Precio.mapMaestroPrecios(con, s.baseDato);
		    			Map<String, Double> mapFijaTasas = BodegaEmpresa.mapFijaTasasAll(con, s.baseDato);
		    			List<Long> listIdGuia_fechaCorte = ModCalc_InvInicial.listIdGuia_fechaCorte(con, s.baseDato, desdeAAMMDD);
		    			List<Inventarios> inventario = Inventarios.inventario(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_fechaCorte);
		    			List<Long> listIdGuia_entreFechas = ModCalc_GuiasPer.listIdGuia_entreFecha(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
		    			List<Inventarios> guiasPer = Inventarios.guiasPer(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_entreFechas);
		    			List<Calc_AjustesEP> listaAjustes = Calc_AjustesEP.listaAjustesEntreFechas(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
		    			Map<Long,List<String>> mapBodega = BodegaEmpresa.mapIdBod_BodegaEmpresaInternasExternas(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
		    			Map<Long,Long> dec = Moneda.numeroDecimal(con, s.baseDato);
		    			Map<String,String> map = UsuarioPermiso.mapPermisoIdBodega(con, s.baseDato, Long.parseLong(s.id_usuario));
		    			Map<String,String> mapPermanencias = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, s.baseDato);
		    			Map<Long,String> mapMoneda = Moneda.mapIdMonedaMoneda(con, s.baseDato);
		    			Map<Long,Equipo> mapAllEquipos = Equipo.mapAllAll(con, s.baseDato);
		    			Map<String,String> mapFecha_primera_guia = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, s.baseDato);
		    			Fechas hoy = Fechas.hoy();
	    		con.close();
	    			List<ModCalc_InvInicial> inventarioInicial = ModCalc_InvInicial.resumenInvInicial(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa, 
	    					mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, listIdGuia_fechaCorte, inventario);
	    			List<ModCalc_GuiasPer> guiasPeriodo = ModCalc_GuiasPer.resumenGuiasPer(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, 
	    					mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, guiasPer, mapPermanencias);
	    			List<ModeloCalculo> listado = ModeloCalculo.valorTotalporBodega(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, inventarioInicial,guiasPeriodo, listaAjustes);
	    			List<List<String>> proyectosAux = ReportFacturas.reportFacturaProyecto(listado, mapBodega);
	    			List<List<String>> resumenTotales = ReportFacturas.resumenTotalesPorProyecto(listado, dec);
	    			List<List<String>> proyectos = new ArrayList<List<String>>();
	    			if(map.size()>0) {
		    			for(List<String> aux: proyectosAux) {
		    				String idBodega = map.get(aux.get(1));
		    				if(idBodega!=null) {
		    					proyectos.add(aux);
		    				}
		    			}
	    			}else {
	    				for(int i=0;i<proyectosAux.size();i++ ) {
	    					proyectos.add(proyectosAux.get(i));
	    				}
	    				
	    			}
	    			for(List<String> lista1: proyectos){
						for(List<String> total: resumenTotales){
							if(lista1.get(1).equals(total.get(0))){
								
								Double valorArr = Double.parseDouble((total.get(1)).replaceAll(",", ""));
				    			Double valorVta = Double.parseDouble((total.get(2)).replaceAll(",", ""));
				    			Double valorGranTotal = Double.parseDouble((total.get(4)).replaceAll(",", ""));
				    			Double valorCompara = valorArr;
				    			if(esVenta.equals("1")) {
				    				valorCompara = valorVta;
				    			}
								if(valorGranTotal>(double)0 && valorCompara>(double)0) {
									String aux = MnuReportes.envioMasivoListadoProforma1(s, desdeAAMMDD, hastaAAMMDD,tasas,
											Long.parseLong(lista1.get(1)), esVenta, 
											permisoPorBodega, listIdBodegaEmpresa, mapBodegaEmpresa,mapPrecios,
											mapMaestroPrecios, mapFijaTasas, listIdGuia_fechaCorte, inventario, 
											listIdGuia_entreFechas, guiasPer, dec, mapPermanencias, mapFecha_primera_guia,
											mapeoPermiso, mapeoDiccionario, form,
											mapMoneda, mapAllEquipos, hoy);
									nros += aux +", ";
								}
							}
						}
	    			}
    		} catch (SQLException e) {
    			e.printStackTrace();
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
                try {
        			Connection con2 = dbWrite.getConnection();
        					Parametros.modify(con2, s.baseDato, "envioMasivoProformas", (long)1);
    	    		con2.close();
                } catch (SQLException e) {
        			e.printStackTrace();
        		}
		}
		
	}
	
	public static String envioMasivoListadoProforma1(Sessiones s, String desdeAAMMDD, String hastaAAMMDD, Map<Long,Double> tasas,
			Long id_bodegaEmpresa, String esVenta, 
			String permisoPorBodega, List<Long> listIdBodegaEmpresa, Map<Long,Calc_BodegaEmpresa> mapBodegaEmpresa, Map<String,Calc_Precio> mapPrecios,
			Map<Long,Calc_Precio> mapMaestroPrecios, Map<String, Double> mapFijaTasas, List<Long> listIdGuia_fechaCorte, List<Inventarios> inventario, 
			List<Long> listIdGuia_entreFechas, List<Inventarios> guiasPer, Map<Long,Long> dec, Map<String,String> mapPermanencias, Map<String,String> mapFecha_primera_guia,
			Map<String,String> mapeoPermiso, Map<String,String> mapeoDiccionario, FormFactura form,
			Map<Long,String> mapMoneda, Map<Long,Equipo> mapAllEquipos, Fechas hoy) {
    		try {
    			Connection con = dbWrite.getConnection();
	    			Map<Long,Cotizacion> mapCotiAllConfirmadas = Cotizacion.mapAllConfirmadasUnaBodega(con, s.baseDato, id_bodegaEmpresa);
	    			List<List<String>> listGuiasPer = ReportFacturas.reportListGuiasEntreFechas(con, s.baseDato, id_bodegaEmpresa, desdeAAMMDD, hastaAAMMDD);
	    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
	    			Proyecto proyecto = Proyecto.find(con,s.baseDato , bodega.getId_proyecto());
	    			Cliente cliente = Cliente.find(con, s.baseDato, bodega.getId_cliente());
	    			List<List<String>> detalleAjuste = AjustesEP.detalleAjuste(con, s.baseDato, id_bodegaEmpresa, desdeAAMMDD, hastaAAMMDD);
	    			String oc = Cotizacion.ocParticiaEnBodega(con, s.baseDato, id_bodegaEmpresa);
	    			Proforma proforma = Proforma.createSinNada(con, s.baseDato, hoy.getFechaStrAAMMDD());
	    			Long cantDec = dec.get((long)1);
	    			List<List<String>> inicioPer = ReportFacturas.reportEstadoInicial10(s.baseDato, id_bodegaEmpresa, desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa, mapBodegaEmpresa, 
	    					mapPrecios, mapMaestroPrecios, listIdGuia_fechaCorte, inventario, mapFecha_primera_guia, mapCotiAllConfirmadas, mapMoneda, mapAllEquipos, dec);
	    			Map<String,List<List<String>>> mapReportPorGuia10 = ReportFacturas.mapReportPorGuia10(s.baseDato, id_bodegaEmpresa, desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, 
	    					mapBodegaEmpresa, mapPrecios, mapMaestroPrecios,
	    					guiasPer, mapPermanencias, dec,  mapCotiAllConfirmadas, mapAllEquipos, mapMoneda);
	    			List<List<String>> resumenSubtotales = ReportFacturas.reportEstadoResumen10(s.baseDato, inicioPer, listGuiasPer, id_bodegaEmpresa, desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, 
	    					listIdBodegaEmpresa, mapBodegaEmpresa, mapPrecios, mapMaestroPrecios,
	    					listIdGuia_entreFechas, mapPermanencias, dec, mapCotiAllConfirmadas, mapAllEquipos, mapMoneda, guiasPer);
	    			Fechas hasta = Fechas.obtenerFechaDesdeStrAAMMDD(hastaAAMMDD);
	    			Calendar hastaMas1 = hasta.getFechaCal();
	    			hastaMas1.add(Calendar.DAY_OF_MONTH, 1);
	    			java.sql.Date masUnDia = new java.sql.Date(hastaMas1.getTimeInMillis());
	    			listIdGuia_fechaCorte = ModCalc_InvInicial.listIdGuia_fechaCorte(con, s.baseDato, masUnDia.toString());
	    			inventario = Inventarios.inventario(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_fechaCorte);
	    		con.close();
    			List<List<String>> finalPer = ReportFacturas.reportEstadoInicial10(s.baseDato, id_bodegaEmpresa, masUnDia.toString(), hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa, mapBodegaEmpresa, 
    					mapPrecios, mapMaestroPrecios, listIdGuia_fechaCorte, inventario, mapFecha_primera_guia, mapCotiAllConfirmadas, mapMoneda, mapAllEquipos, dec);
    			List<String> fechas = new ArrayList<String>();
    			fechas.add(desdeAAMMDD);
    			fechas.add(hastaAAMMDD);
    			fechas.add(Fechas.DDMMAA(desdeAAMMDD));
    			fechas.add(Fechas.DDMMAA(hastaAAMMDD));
    			List<Double> tasaCambio = new ArrayList<Double>();
    			tasaCambio.add(tasas.get((long)4)); // 'Unidad Fomento', 'UF', '4'
    			tasaCambio.add(tasas.get((long)2)); // 'Dólar', 'USD', '2'
    			tasaCambio.add(tasas.get((long)3)); // 'Euro', 'EUR', '3'
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
    			if(esVenta.equals("1")) {
    				concepto = "VENTA";
    			}
    			Connection con2 = dbWrite.getConnection();
    			
	    			List<List<String>> datos = ReportMovimientos.movimientoGuias(con2, s.baseDato, mapeoDiccionario, id_bodegaEmpresa, esVenta, desdeAAMMDD, hastaAAMMDD, 
	    					tasas.get((long)2), tasas.get((long)3), tasas.get((long)4));
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
 	            
	    			EmisorTributario emisorTributario = EmisorTributario.find(con2, s.baseDato);
	    			BodegaEmpresa bodegaEmpresa = BodegaEmpresa.findXIdBodega(con2, s.baseDato, proforma.id_bodegaEmpresa);
	    			
	 	           if(esVenta.equals("0")) {
	 	        	   // genera PDF de arriendo, XML, JSON y guarda json en proforma
	 	        	   	proforma.setTipo(mapeoDiccionario.get("Arriendo"));
	 	        	    String conDetalle = mapeoPermiso.get("parametro.proformaInvConCompra");
		 	            FormFactura.generaProformaArriendo(con2, s.baseDato, mapeoDiccionario, mapeoPermiso, 
								resumenSubtotales,cliente,proforma,referencias,detalleAjuste,conDetalle, inicioPer, listGuiasPer, mapReportPorGuia10, finalPer, 
								tasas.get((long)4), tasas.get((long)2), tasas.get((long)3), oc,dec, emisorTributario, bodegaEmpresa);
		 	            
	    			}else {
	    				// genera PDF de venta, XML, JSON y guarda json en proforma
	    				proforma.setTipo("Venta");
			 	        FormFactura.generaProformaVenta(con2, s.baseDato, mapeoDiccionario, mapeoPermiso, 
								cliente,proforma,referencias,detalleAjuste, listGuiasPer, mapReportPorGuia10, oc);
	    			}
 	           con2.close();
 	          return(proforma.id.toString());
 	          
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	return(null);
	}
	
	public Result envioMasivoListadoProforma2(Http.Request request) {
		Sessiones s = new Sessiones(request);
		if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
			DynamicForm formEsVenta = formFactory.form().bindFromRequest(request);
			FormFactura form = formFactory.form(FormFactura.class).withDirectFieldAccess(true).bindFromRequest(request).get();
	   		if (formEsVenta.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String desdeAAMMDD = form.fechaDesde;
	       		String hastaAAMMDD = form.fechaHasta;
	       		Double uf = Double.parseDouble(form.uf.replaceAll(",", "").trim());
	       		Double usd = Double.parseDouble(form.usd.replaceAll(",", "").trim());
	       		Double eur = Double.parseDouble(form.eur.replaceAll(",", "").trim());
	       		String esVenta = formEsVenta.get("esVenta").trim();
	       		String mailDestino =  null;
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			Usuario usuario = Usuario.findXIdUser(con, s.baseDato, Long.parseLong(s.id_usuario));
	    			if( usuario != null) {
	    				mailDestino = usuario.getEmail().trim().toLowerCase();
	    				if(mailDestino.length() < 4) {
	    					usuario = null;
	    				}
	    			}
	    			con.close();
	       		} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		
	       		if(HomeController.isValidEmail(mailDestino)) {
	       			if(mailDestino != null) {
		       			MnuReportes.envioMasivoListadoProforma0 generar = new MnuReportes.envioMasivoListadoProforma0(desdeAAMMDD, hastaAAMMDD, uf, usd, eur, esVenta, mailDestino, s, form);
		       			generar.start();
		    			String mensaje = "Solicitud en preparación, recibira el resultado al correo:"+mailDestino+". Tomara varios minutos u horas para recibir el correo, dependiendo de la cantidad de proformas a generar.";
		    			return ok(mensajes.render("/home/",mensaje));
		       		}else {
		       			String mensaje = "No es posible generar la solicitud debido a que no existe dato de email en la configuración de su usuario";
		       			return ok(mensajes.render("/home/",mensaje));
		       		}
	       		}else {
	       			String mensaje = "No es posible generar la solicitud debido a que el mail que existe en la configuración de su usuario no es valido";
	       			return ok(mensajes.render("/home/",mensaje));
	       		}
	       	}
		}else {
			
			return ok(mensajes.render("/",msgErrorFormulario));
		}
	}
	
	public Result reportFacturaProyectoDetalle(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
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
	    		
	    		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			
		    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
		    			
		    			List<Long> listIdBodegaEmpresa = ModCalc_InvInicial.listIdBodegaEmpresa(con, s.baseDato, permisoPorBodega);
		    			Map<Long,Calc_BodegaEmpresa> mapBodegaEmpresa = Calc_BodegaEmpresa.mapAllBodegasVigentes(con, s.baseDato);
		    			
		    			Map<String,Calc_Precio> mapPrecios = Calc_Precio.mapPrecios(con, s.baseDato, listIdBodegaEmpresa);
		    			Map<Long,Calc_Precio> mapMaestroPrecios = Calc_Precio.mapMaestroPrecios(con, s.baseDato);
		    			Map<String, Double> mapFijaTasas = BodegaEmpresa.mapFijaTasasAll(con, s.baseDato);
		    			
		    			List<Long> listIdGuia_fechaCorte = ModCalc_InvInicial.listIdGuia_fechaCorte(con, s.baseDato, desdeAAMMDD);
		    			List<Inventarios> inventario = Inventarios.inventario(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_fechaCorte);
		    			List<Long> listIdGuia_entreFechas = ModCalc_GuiasPer.listIdGuia_entreFecha(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
		    			List<Inventarios> guiasPer = Inventarios.guiasPer(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_entreFechas);
		    			Map<Long,Long> dec = Moneda.numeroDecimal(con, s.baseDato);
		    			
		    			
		    			Map<String,String> mapPermanencias = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, s.baseDato);
	    			
		    			Map<String,String> mapFecha_primera_guia = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, s.baseDato);
		    			
		    			Map<Long,Cotizacion> mapCotiAllConfirmadas = Cotizacion.mapAllConfirmadasUnaBodega(con, s.baseDato, id_bodegaEmpresa);
		    			Map<Long,String> mapMoneda = Moneda.mapIdMonedaMoneda(con, s.baseDato);
		    			Map<Long,Equipo> mapAllEquipos = Equipo.mapAllAll(con, s.baseDato);
		    			List<List<String>> listGuiasPer = ReportFacturas.reportListGuiasEntreFechas(con, s.baseDato, id_bodegaEmpresa, desdeAAMMDD, hastaAAMMDD);
		    			
		    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
		    			Proyecto proyecto = Proyecto.find(con,s.baseDato , bodega.getId_proyecto());
		    			Cliente cliente = Cliente.find(con, s.baseDato, bodega.getId_cliente());
		    			
		    			List<List<String>> detalleAjuste = AjustesEP.detalleAjuste(con, s.baseDato, id_bodegaEmpresa, desdeAAMMDD, hastaAAMMDD);
		    			
		    			List<TipoReferencia> listReferencias = TipoReferencia.all(con, s.baseDato);
		    			String oc = Cotizacion.ocParticiaEnBodega(con, s.baseDato, id_bodegaEmpresa);
		    			
		    			Long cantDec = dec.get((long)1);
		    			String idTipoUsuario = s.id_tipoUsuario;
		        		
		    			List<List<String>> inicioPer = ReportFacturas.reportEstadoInicial10(s.baseDato, id_bodegaEmpresa, desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa, mapBodegaEmpresa, 
		    					mapPrecios, mapMaestroPrecios, listIdGuia_fechaCorte, inventario, mapFecha_primera_guia, mapCotiAllConfirmadas, mapMoneda, mapAllEquipos, dec);
		    			
		    			Map<String,List<List<String>>> mapReportPorGuia10 = ReportFacturas.mapReportPorGuia10(s.baseDato, id_bodegaEmpresa, desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, 
		    					mapBodegaEmpresa, mapPrecios, mapMaestroPrecios,
		    					guiasPer, mapPermanencias, dec,  mapCotiAllConfirmadas, mapAllEquipos, mapMoneda);
		    			
		    			List<List<String>> resumenSubtotales = ReportFacturas.reportEstadoResumen10(s.baseDato, inicioPer, listGuiasPer, id_bodegaEmpresa, desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, 
		    					listIdBodegaEmpresa, mapBodegaEmpresa, mapPrecios, mapMaestroPrecios,
		    					listIdGuia_entreFechas, mapPermanencias, dec, mapCotiAllConfirmadas, mapAllEquipos, mapMoneda, guiasPer);
		    			
		    			Fechas hasta = Fechas.obtenerFechaDesdeStrAAMMDD(hastaAAMMDD);
		    			
		    			Calendar hastaMas1 = hasta.getFechaCal();
		    			hastaMas1.add(Calendar.DAY_OF_MONTH, 1);
		    			java.sql.Date masUnDia = new java.sql.Date(hastaMas1.getTimeInMillis());
		    			
		    			listIdGuia_fechaCorte = ModCalc_InvInicial.listIdGuia_fechaCorte(con, s.baseDato, masUnDia.toString());
		    			inventario = Inventarios.inventario(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_fechaCorte);
		    			
		    			con.close();
	    			
	    			List<List<String>> finalPer = ReportFacturas.reportEstadoInicial10(s.baseDato, id_bodegaEmpresa, masUnDia.toString(), hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa, mapBodegaEmpresa, 
	    					mapPrecios, mapMaestroPrecios, listIdGuia_fechaCorte, inventario, mapFecha_primera_guia, mapCotiAllConfirmadas, mapMoneda, mapAllEquipos, dec);
	    			
	    			
	    			
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
		    				oc));
	    		} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		return ok(mensajes.render("/",msgError));
	       	}
	    		
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
		
	}
	
	public Result reportFacturaProyectoDetExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
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
	    		
	    		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			
		    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
		    			List<Long> listIdBodegaEmpresa = ModCalc_InvInicial.listIdBodegaEmpresa(con, s.baseDato, permisoPorBodega);
		    			Map<Long,Calc_BodegaEmpresa> mapBodegaEmpresa = Calc_BodegaEmpresa.mapAllBodegasVigentes(con, s.baseDato);
		    			Map<String,Calc_Precio> mapPrecios = Calc_Precio.mapPrecios(con, s.baseDato, listIdBodegaEmpresa);
		    			Map<Long,Calc_Precio> mapMaestroPrecios = Calc_Precio.mapMaestroPrecios(con, s.baseDato);
		    			Map<String, Double> mapFijaTasas = BodegaEmpresa.mapFijaTasasAll(con, s.baseDato);
		    			
		    			List<Long> listIdGuia_fechaCorte = ModCalc_InvInicial.listIdGuia_fechaCorte(con, s.baseDato, desdeAAMMDD);
		    			List<Inventarios> inventario = Inventarios.inventario(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_fechaCorte);
		    			List<Long> listIdGuia_entreFechas = ModCalc_GuiasPer.listIdGuia_entreFecha(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
		    			List<Inventarios> guiasPer = Inventarios.guiasPer(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_entreFechas);
		    			Map<Long,Long> dec = Moneda.numeroDecimal(con, s.baseDato);
		    			
		    			Map<String,String> mapPermanencias = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, s.baseDato);
	    			
		    			Map<String,String> mapFecha_primera_guia = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, s.baseDato);
		    			
		    			Map<Long,Cotizacion> mapCotiAllConfirmadas = Cotizacion.mapAllConfirmadasUnaBodega(con, s.baseDato, id_bodegaEmpresa);
		    			Map<Long,String> mapMoneda = Moneda.mapIdMonedaMoneda(con, s.baseDato);
		    			Map<Long,Equipo> mapAllEquipos = Equipo.mapAllAll(con, s.baseDato);
		    			List<List<String>> listGuiasPer = ReportFacturas.reportListGuiasEntreFechas(con, s.baseDato, id_bodegaEmpresa, desdeAAMMDD, hastaAAMMDD);
		    			
		    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
		    			Proyecto proyecto = Proyecto.find(con,s.baseDato , bodega.getId_proyecto());
		    			Cliente cliente = Cliente.find(con, s.baseDato, bodega.getId_cliente());
		    			
		    			List<List<String>> detalleAjuste = AjustesEP.detalleAjuste(con, s.baseDato, id_bodegaEmpresa, desdeAAMMDD, hastaAAMMDD);
		    			
		    			Long cantDec = dec.get((long)1);
	    			
		    			List<List<String>> inicioPer = ReportFacturas.reportEstadoInicial10(s.baseDato, id_bodegaEmpresa, desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa, mapBodegaEmpresa, 
		    					mapPrecios, mapMaestroPrecios, listIdGuia_fechaCorte, inventario, mapFecha_primera_guia, mapCotiAllConfirmadas, mapMoneda, mapAllEquipos, dec);
		    			
		    			Map<String,List<List<String>>> mapReportPorGuia10 = ReportFacturas.mapReportPorGuia10(s.baseDato, id_bodegaEmpresa, desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, 
		    					mapBodegaEmpresa, mapPrecios, mapMaestroPrecios,
		    					guiasPer, mapPermanencias, dec,  mapCotiAllConfirmadas, mapAllEquipos, mapMoneda);
		    			
		    			List<List<String>> resumenSubtotales = ReportFacturas.reportEstadoResumen10(s.baseDato, inicioPer, listGuiasPer, id_bodegaEmpresa, desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, 
		    					listIdBodegaEmpresa, mapBodegaEmpresa, mapPrecios, mapMaestroPrecios,
		    					listIdGuia_entreFechas, mapPermanencias, dec, mapCotiAllConfirmadas, mapAllEquipos, mapMoneda, guiasPer);
		    			
		    			Fechas hasta = Fechas.obtenerFechaDesdeStrAAMMDD(hastaAAMMDD);
		    			
		    			Calendar hastaMas1 = hasta.getFechaCal();
		    			hastaMas1.add(Calendar.DAY_OF_MONTH, 1);
		    			java.sql.Date masUnDia = new java.sql.Date(hastaMas1.getTimeInMillis());
		    			
		    			listIdGuia_fechaCorte = ModCalc_InvInicial.listIdGuia_fechaCorte(con, s.baseDato, masUnDia.toString());
		    			inventario = Inventarios.inventario(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_fechaCorte);
	    			
	    			con.close();
	    			
	    			List<List<String>> finalPer = ReportFacturas.reportEstadoInicial10(s.baseDato, id_bodegaEmpresa, masUnDia.toString(), hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa, mapBodegaEmpresa, 
	    					mapPrecios, mapMaestroPrecios, listIdGuia_fechaCorte, inventario, mapFecha_primera_guia, mapCotiAllConfirmadas, mapMoneda, mapAllEquipos, dec);
	    			
	    			
	    			
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
	    			
	    			if(file!=null) {
		       			return ok(file,false,Optional.of("EP_Proforma_"+bodega.nombre+".xlsx"));
		       		}else {
		       			return ok("");
		       		}
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok("");
	       	}
    	}else {
    		return ok("");
    	}
	}
	
	public Result generarProformaPdfXlsxXmlJson(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm formEsVenta = formFactory.form().bindFromRequest(request);
    		FormFactura form = formFactory.form(FormFactura.class).withDirectFieldAccess(true).bindFromRequest(request).get();
	   		if (formEsVenta.hasErrors() || form.idBodega==null) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
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
	    			
	    		try {
	    			Connection con = dbWrite.getConnection();
	    			
		    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
		    			List<Long> listIdBodegaEmpresa = ModCalc_InvInicial.listIdBodegaEmpresa(con, s.baseDato, permisoPorBodega);
		    			Map<Long,Calc_BodegaEmpresa> mapBodegaEmpresa = Calc_BodegaEmpresa.mapAllBodegasVigentes(con, s.baseDato);
		    			Map<String,Calc_Precio> mapPrecios = Calc_Precio.mapPrecios(con, s.baseDato, listIdBodegaEmpresa);
		    			Map<Long,Calc_Precio> mapMaestroPrecios = Calc_Precio.mapMaestroPrecios(con, s.baseDato);
		    			Map<String, Double> mapFijaTasas = BodegaEmpresa.mapFijaTasasAll(con, s.baseDato);
		    			
		    			List<Long> listIdGuia_fechaCorte = ModCalc_InvInicial.listIdGuia_fechaCorte(con, s.baseDato, desdeAAMMDD);
		    			List<Inventarios> inventario = Inventarios.inventario(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_fechaCorte);
		    			List<Long> listIdGuia_entreFechas = ModCalc_GuiasPer.listIdGuia_entreFecha(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
		    			List<Inventarios> guiasPer = Inventarios.guiasPer(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_entreFechas);
		    			Map<Long,Long> dec = Moneda.numeroDecimal(con, s.baseDato);
		    			
		    			Map<String,String> mapPermanencias = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, s.baseDato);
	    			
		    			Map<String,String> mapFecha_primera_guia = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, s.baseDato);
		    			
		    			Map<Long,Cotizacion> mapCotiAllConfirmadas = Cotizacion.mapAllConfirmadasUnaBodega(con, s.baseDato, id_bodegaEmpresa);
		    			Map<Long,String> mapMoneda = Moneda.mapIdMonedaMoneda(con, s.baseDato);
		    			Map<Long,Equipo> mapAllEquipos = Equipo.mapAllAll(con, s.baseDato);
		    			List<List<String>> listGuiasPer = ReportFacturas.reportListGuiasEntreFechas(con, s.baseDato, id_bodegaEmpresa, desdeAAMMDD, hastaAAMMDD);
		    			
		    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
		    			Proyecto proyecto = Proyecto.find(con,s.baseDato , bodega.getId_proyecto());
		    			Cliente cliente = Cliente.find(con, s.baseDato, bodega.getId_cliente());
		    			
		    			List<List<String>> detalleAjuste = AjustesEP.detalleAjuste(con, s.baseDato, id_bodegaEmpresa, desdeAAMMDD, hastaAAMMDD);
		    			
		    			String oc = Cotizacion.ocParticiaEnBodega(con, s.baseDato, id_bodegaEmpresa);
		    			
		    			Fechas hoy = Fechas.hoy();
		    			Proforma proforma = Proforma.createSinNada(con, s.baseDato, hoy.getFechaStrAAMMDD());
		    			
		    			Long cantDec = dec.get((long)1);
	    			
		    			List<List<String>> inicioPer = ReportFacturas.reportEstadoInicial10(s.baseDato, id_bodegaEmpresa, desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa, mapBodegaEmpresa, 
		    					mapPrecios, mapMaestroPrecios, listIdGuia_fechaCorte, inventario, mapFecha_primera_guia, mapCotiAllConfirmadas, mapMoneda, mapAllEquipos, dec);
		    			
		    			Map<String,List<List<String>>> mapReportPorGuia10 = ReportFacturas.mapReportPorGuia10(s.baseDato, id_bodegaEmpresa, desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, 
		    					mapBodegaEmpresa, mapPrecios, mapMaestroPrecios,
		    					guiasPer, mapPermanencias, dec,  mapCotiAllConfirmadas, mapAllEquipos, mapMoneda);
		 
		    			List<List<String>> resumenSubtotales = ReportFacturas.reportEstadoResumen10(s.baseDato, inicioPer, listGuiasPer, id_bodegaEmpresa, desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, 
		    					listIdBodegaEmpresa, mapBodegaEmpresa, mapPrecios, mapMaestroPrecios,
		    					listIdGuia_entreFechas, mapPermanencias, dec, mapCotiAllConfirmadas, mapAllEquipos, mapMoneda, guiasPer);
		    			
		    			Fechas hasta = Fechas.obtenerFechaDesdeStrAAMMDD(hastaAAMMDD);
		    			Calendar hastaMas1 = hasta.getFechaCal();
		    			hastaMas1.add(Calendar.DAY_OF_MONTH, 1);
		    			java.sql.Date masUnDia = new java.sql.Date(hastaMas1.getTimeInMillis());
		    			
		    			listIdGuia_fechaCorte = ModCalc_InvInicial.listIdGuia_fechaCorte(con, s.baseDato, masUnDia.toString());
		    			inventario = Inventarios.inventario(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_fechaCorte);
	    			
		    		con.close();
		    			
	    			List<List<String>> finalPer = ReportFacturas.reportEstadoInicial10(s.baseDato, id_bodegaEmpresa, masUnDia.toString(), hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa, mapBodegaEmpresa, 
	    					mapPrecios, mapMaestroPrecios, listIdGuia_fechaCorte, inventario, mapFecha_primera_guia, mapCotiAllConfirmadas, mapMoneda, mapAllEquipos, dec);
	    			
	    			
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
	    			if(esVenta.equals("1")) {
	    				concepto = "VENTA";
	    			}
	    			
	    			
	    			
	    			
	    			Connection con2 = dbWrite.getConnection();
	    			
		    			List<List<String>> datos = ReportMovimientos.movimientoGuias(con2, s.baseDato, mapeoDiccionario, id_bodegaEmpresa, esVenta, desdeAAMMDD, hastaAAMMDD, usd, eur, uf);
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
	 	            
		    			String archivoPDF = "0";
		    			
		    			EmisorTributario emisorTributario = EmisorTributario.find(con2, s.baseDato);
		    			BodegaEmpresa bodegaEmpresa = BodegaEmpresa.findXIdBodega(con2, s.baseDato, proforma.id_bodegaEmpresa);
		    			
		 	           if(esVenta.equals("0")) {
		 	        	   // genera PDF de arriendo, XML, JSON y guarda json en proforma
		 	        	   	proforma.setTipo(mapeoDiccionario.get("Arriendo"));
		 	        	    String conDetalle = mapeoPermiso.get("parametro.proformaInvConCompra");
			 	            archivoPDF = FormFactura.generaProformaArriendo(con2, s.baseDato, mapeoDiccionario, mapeoPermiso, 
									resumenSubtotales,cliente,proforma,referencias,detalleAjuste,conDetalle, inicioPer, listGuiasPer, mapReportPorGuia10, finalPer, uf, usd, eur, oc,
									dec, emisorTributario, bodegaEmpresa);
			 	            
		    			}else {
		    				// genera PDF de venta, XML, JSON y guarda json en proforma
		    				proforma.setTipo("Venta");
				 	        archivoPDF = FormFactura.generaProformaVenta(con2, s.baseDato, mapeoDiccionario, mapeoPermiso, 
									cliente,proforma,referencias,detalleAjuste, listGuiasPer, mapReportPorGuia10, oc);
		    			}
	 	           con2.close();
	    		
	 	
	 	           return (reportFacturaProyecto(request, archivoPDF));
	 	          
	    		} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		return ok(mensajes.render("/",msgError));
	       	}
	    		
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
		
	}
	
	
	//====================================================================================
    // MNU proformaResumen   Reportes/Proforma/Resumen y Detalle (por mes)
    //====================================================================================
	
	public Result reportFacturaPeriodoResumen0(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		try {
    			Connection con = dbRead.getConnection(dbRead);
    			
    			if(mapeoPermiso.get("proformaResumen")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Fechas hoy = Fechas.hoy();
    			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
    			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			TasasCambio tasas = TasasCambio.allDeUnaFecha(con, s.baseDato, mapeoDiccionario.get("pais"),hasta);
    			con.close();
    			return ok(reportFacturaPeriodoResumen0.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta, tasas));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reportFacturaPeriodoResumen(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		try {
    			Connection con = dbRead.getConnection(dbRead);
    			
    			if(mapeoPermiso.get("proformaResumen")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Fechas hoy = Fechas.hoy();
    			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
    			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			TasasCambio tasas = TasasCambio.allDeUnaFecha(con, s.baseDato, mapeoDiccionario.get("pais"),hasta);
    			con.close();
    			return ok(reportFacturaPeriodoResumen.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta, tasas));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reportFacturaResumen0(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
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

    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		return ok(reportFacturaResumen0.render(mapeoDiccionario,mapeoPermiso,userMnu,
    					desde,hasta,uf,usd,eur));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reportFacturaResumen(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
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
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			Map<Long,Long> dec = Moneda.numeroDecimal(con, s.baseDato);
	    			Long nroDec = dec.get((long) 1);
	    			con.close();
	    			return ok(reportFacturaResumen.render(mapeoDiccionario,mapeoPermiso,userMnu,
	    					desde,hasta,uf,usd,eur,nroDec));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reportFacturaResumenJson(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("ERROR");
	       	}else {
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
	       		try {
	       			Connection con = dbRead.getConnection(dbRead);
	    			
		    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
		    			List<Long> listIdBodegaEmpresa = ModCalc_InvInicial.listIdBodegaEmpresa(con, s.baseDato, permisoPorBodega);
		    			Map<Long,Calc_BodegaEmpresa> mapBodegaEmpresa = Calc_BodegaEmpresa.mapAllBodegasVigentes(con, s.baseDato);
		    			Map<String,Calc_Precio> mapPrecios = Calc_Precio.mapPrecios(con, s.baseDato, listIdBodegaEmpresa);
		    			Map<Long,Calc_Precio> mapMaestroPrecios = Calc_Precio.mapMaestroPrecios(con, s.baseDato);
		    			
		    			Map<String, Double> mapFijaTasas = BodegaEmpresa.mapFijaTasasAll(con, s.baseDato);
		    			
		    			List<Long> listIdGuia_fechaCorte = ModCalc_InvInicial.listIdGuia_fechaCorte(con, s.baseDato, desdeAAMMDD);
		    			List<Inventarios> inventario = Inventarios.inventario(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_fechaCorte);
		    			List<Long> listIdGuia_entreFechas = ModCalc_GuiasPer.listIdGuia_entreFecha(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
		    			List<Inventarios> guiasPer = Inventarios.guiasPer(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_entreFechas);
		    			List<Calc_AjustesEP> listaAjustes = Calc_AjustesEP.listaAjustesEntreFechas(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
		    			Map<Long,List<String>> mapBodega = BodegaEmpresa.mapIdBod_BodegaEmpresaInternasExternas(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
		    			Map<Long,Long> dec = Moneda.numeroDecimal(con, s.baseDato);
		    			
		    			Map<String,String> map = UsuarioPermiso.mapPermisoIdBodega(con, s.baseDato, Long.parseLong(s.id_usuario));
		    			
		    			Map<String,String> mapPermanencias = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, s.baseDato);
	    			
		    			
		    			
	    			con.close();
	    			
	    			List<ModCalc_InvInicial> inventarioInicial = ModCalc_InvInicial.resumenInvInicial(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa, 
	    					mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, listIdGuia_fechaCorte, inventario);
	    			
	    			List<ModCalc_GuiasPer> guiasPeriodo = ModCalc_GuiasPer.resumenGuiasPer(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, 
	    					mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, guiasPer, mapPermanencias);
	    			
	    			List<ModeloCalculo> valorTotalPorBodega = ModeloCalculo.valorTotalporBodega(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, inventarioInicial,guiasPeriodo, listaAjustes);
	    			
	    			List<List<String>> proyectosAux = ReportFacturas.reportFacturaProyecto(valorTotalPorBodega, mapBodega);
	    			
	    			List<List<String>> resumenTotales = ReportFacturas.resumenTotalesPorProyecto(valorTotalPorBodega, dec);
	    			
	    			List<ModeloCalculo> valorTotalporBodegaYGrupo = ModeloCalculo.valorTotalporBodegaYGrupo(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, inventarioInicial,guiasPeriodo);

	    			
	    			//filtro solo las bodegas con permiso:
	    			List<List<String>> proyectos = new ArrayList<List<String>>();
	    			if(map.size()>0) {
		    			for(List<String> aux: proyectosAux) {
		    				String idBodega = map.get(aux.get(1));
		    				if(idBodega!=null) {
		    					proyectos.add(aux);
		    				}
		    			}
	    			}else {
	    				for(int i=0;i<proyectosAux.size();i++ ) {
	    					proyectos.add(proyectosAux.get(i));
	    				}
	    				
	    			}

	    			List<List<String>> resumenPorGrupoYProyecto = new ArrayList<List<String>>();

	    			if(!mapeoDiccionario.get("nEmpresa").equals("SM8 DE MEXICO")) {
	    				Connection con2 = dbRead.getConnection(dbRead);
	    					Map<String, List<List<String>>> mapResumenPorGrupo = ReportFacturas.mapResumenPorGrupo2(con2, s.baseDato, valorTotalporBodegaYGrupo); 		// RESUMEN POR GRUPO
	    					resumenPorGrupoYProyecto = ReportFacturas.resumenPorGrupoYProyecto(con2, s.baseDato, proyectos, mapResumenPorGrupo); 		// RESUMEN POR GRUPO
		    			con2.close();
	    			}
	    			
	    			String resumenPorPJson = Json.toJson(proyectos).toString();
	    			String resumenPorPyGJson = Json.toJson(resumenPorGrupoYProyecto).toString();
	    			String resumenTotalesJson = Json.toJson(resumenTotales).toString();
	    			
	    			String json = "{\"resumenPorPJson\":"+resumenPorPJson+",\"resumenPorPyGJson\":"+resumenPorPyGJson
					+",\"resumenTotalesJson\":"+resumenTotalesJson+"}";
	    			
	    			return ok(json).as("application/json");
	    			
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok("ERROR");
	       	}
    	}else {
    		return ok("ERROR");
    	}
	}
	
	public Result reportFacturaResumenExcel0(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
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
	    		
	       		try {
	       			Connection con = dbRead.getConnection(dbRead);
	    			
		    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
		    			List<Long> listIdBodegaEmpresa = ModCalc_InvInicial.listIdBodegaEmpresa(con, s.baseDato, permisoPorBodega);
		    			Map<Long,Calc_BodegaEmpresa> mapBodegaEmpresa = Calc_BodegaEmpresa.mapAllBodegasVigentes(con, s.baseDato);
		    			Map<String,Calc_Precio> mapPrecios = Calc_Precio.mapPrecios(con, s.baseDato, listIdBodegaEmpresa);
		    			Map<Long,Calc_Precio> mapMaestroPrecios = Calc_Precio.mapMaestroPrecios(con, s.baseDato);
		    			Map<String, Double> mapFijaTasas = BodegaEmpresa.mapFijaTasasAll(con, s.baseDato);
		    			
		    			List<Long> listIdGuia_fechaCorte = ModCalc_InvInicial.listIdGuia_fechaCorte(con, s.baseDato, desdeAAMMDD);
		    			List<Inventarios> inventario = Inventarios.inventario(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_fechaCorte);
		    			List<Long> listIdGuia_entreFechas = ModCalc_GuiasPer.listIdGuia_entreFecha(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
		    			List<Inventarios> guiasPer = Inventarios.guiasPer(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_entreFechas);
		    			List<Calc_AjustesEP> listaAjustes = Calc_AjustesEP.listaAjustesEntreFechas(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
		    			Map<Long,List<String>> mapBodega = BodegaEmpresa.mapIdBod_BodegaEmpresaInternasExternas(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
		    			Map<Long,Long> dec = Moneda.numeroDecimal(con, s.baseDato);
		    			
		    			Map<String,String> map = UsuarioPermiso.mapPermisoIdBodega(con, s.baseDato, Long.parseLong(s.id_usuario));
		    			
		    			Map<String,String> mapPermanencias = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, s.baseDato);
	    			
		    			
	    			con.close();
    			
    			List<ModCalc_InvInicial> inventarioInicial = ModCalc_InvInicial.resumenInvInicial(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa, 
    					mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, listIdGuia_fechaCorte, inventario);
    			
    			List<ModCalc_GuiasPer> guiasPeriodo = ModCalc_GuiasPer.resumenGuiasPer(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, 
    					mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, guiasPer, mapPermanencias);
    			
    			List<ModeloCalculo> valorTotalPorBodega = ModeloCalculo.valorTotalporBodega(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, inventarioInicial,guiasPeriodo, listaAjustes);
    			
    			List<List<String>> proyectosAux = ReportFacturas.reportFacturaProyecto(valorTotalPorBodega, mapBodega);
    			
    			List<List<String>> resumenTotales = ReportFacturas.resumenTotalesPorProyecto(valorTotalPorBodega, dec);
    			
    			List<ModeloCalculo> valorTotalporBodegaYGrupo = ModeloCalculo.valorTotalporBodegaYGrupo(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, inventarioInicial,guiasPeriodo);

	    			
	    			//filtro solo las bodegas con permiso:
	    			
	    			List<List<String>> proyectos = new ArrayList<List<String>>();
	    			if(map.size()>0) {
		    			for(List<String> aux: proyectosAux) {
		    				String idBodega = map.get(aux.get(1));
		    				if(idBodega!=null) {
		    					proyectos.add(aux);
		    				}
		    			}
	    			}else {
	    				for(int i=0;i<proyectosAux.size();i++ ) {
	    					proyectos.add(proyectosAux.get(i));
	    				}
	    				
	    			}
	    
	    			
	    			Connection con2 = dbRead.getConnection(dbRead);
	    		
		    			Map<String, List<List<String>>> mapResumenPorGrupo = ReportFacturas.mapResumenPorGrupo2(con2, s.baseDato, valorTotalporBodegaYGrupo); 		// RESUMEN POR GRUPO
		    			
		    			List<List<String>> resumenPorGrupoYProyecto = ReportFacturas.resumenPorGrupoYProyecto(con2, s.baseDato, proyectos, mapResumenPorGrupo); 		// RESUMEN POR GRUPO
		    			
		    			Map<String, List<List<String>>> mapInicioPer = ReportFacturas.mapInicioPerAllBodegas(con2, s.baseDato, inventarioInicial);  					// RESUMEN POR ESTADOS DE PAGO
		    			Map<String, List<List<String>>> mapGuiasPer = ReportFacturas.mapGuiasPer(con2, s.baseDato, guiasPeriodo);  									// RESUMEN POR ESTADOS DE PAGO
		    			
		    			List<List<String>> resumenPorProyectoGrupoYdetalle = 
		    					ReportFacturas.resumenEstadosDePago(con2, s.baseDato, proyectos, desdeAAMMDD, hastaAAMMDD,mapeoDiccionario.get("nEmpresa"), mapInicioPer, mapGuiasPer);   // RESUMEN POR ESTADOS DE PAGO
		    			
	    			
	    			con2.close();
	    			
	    			File file = ReportFacturas.exportaProformaExcelResumen0(s.baseDato, mapeoDiccionario,
	    					proyectos,desde,hasta,uf,usd,eur,resumenTotales,resumenPorGrupoYProyecto,resumenPorProyectoGrupoYdetalle);


	    			if(file!=null) {
		       			return ok(file,false,Optional.of("EP_Proforma_Resumen.xlsx"));
		       		}else {
		       			return ok("");
		       		}
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok("");
	       	}
    	}else {
    		return ok("");
    	}
	}
	
	public Result reportFacturaResumenExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
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
	    		
	       		try {
	       			Connection con = dbRead.getConnection(dbRead);
	    			
		    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
		    			List<Long> listIdBodegaEmpresa = ModCalc_InvInicial.listIdBodegaEmpresa(con, s.baseDato, permisoPorBodega);
		    			Map<Long,Calc_BodegaEmpresa> mapBodegaEmpresa = Calc_BodegaEmpresa.mapAllBodegasVigentes(con, s.baseDato);
		    			Map<String,Calc_Precio> mapPrecios = Calc_Precio.mapPrecios(con, s.baseDato, listIdBodegaEmpresa);
		    			Map<Long,Calc_Precio> mapMaestroPrecios = Calc_Precio.mapMaestroPrecios(con, s.baseDato);
		    			Map<String, Double> mapFijaTasas = BodegaEmpresa.mapFijaTasasAll(con, s.baseDato);
		    			
		    			List<Long> listIdGuia_fechaCorte = ModCalc_InvInicial.listIdGuia_fechaCorte(con, s.baseDato, desdeAAMMDD);
		    			List<Inventarios> inventario = Inventarios.inventario(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_fechaCorte);
		    			List<Long> listIdGuia_entreFechas = ModCalc_GuiasPer.listIdGuia_entreFecha(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
		    			List<Inventarios> guiasPer = Inventarios.guiasPer(con, s.baseDato, listIdBodegaEmpresa, listIdGuia_entreFechas);
		    			List<Calc_AjustesEP> listaAjustes = Calc_AjustesEP.listaAjustesEntreFechas(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
		    			Map<Long,List<String>> mapBodega = BodegaEmpresa.mapIdBod_BodegaEmpresaInternasExternas(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
		    			Map<Long,Long> dec = Moneda.numeroDecimal(con, s.baseDato);
		    			
		    			Map<String,String> map = UsuarioPermiso.mapPermisoIdBodega(con, s.baseDato, Long.parseLong(s.id_usuario));
		    			
		    			Map<String,String> mapPermanencias = ModCalc_GuiasPer.mapDiasFechaMinGuiaPorEquipo(con, s.baseDato);
	    			
		    			
	    			con.close();
    			
    			List<ModCalc_InvInicial> inventarioInicial = ModCalc_InvInicial.resumenInvInicial(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa, 
    					mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, listIdGuia_fechaCorte, inventario);
    			
    			List<ModCalc_GuiasPer> guiasPeriodo = ModCalc_GuiasPer.resumenGuiasPer(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, 
    					mapBodegaEmpresa, mapPrecios, mapMaestroPrecios, guiasPer, mapPermanencias);
    			
    			List<ModeloCalculo> valorTotalPorBodega = ModeloCalculo.valorTotalporBodega(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, inventarioInicial,guiasPeriodo, listaAjustes);
    			
    			List<List<String>> proyectosAux = ReportFacturas.reportFacturaProyecto(valorTotalPorBodega, mapBodega);
    			
    			List<List<String>> resumenTotales = ReportFacturas.resumenTotalesPorProyecto(valorTotalPorBodega, dec);
    			
    			List<ModeloCalculo> valorTotalporBodegaYGrupo = ModeloCalculo.valorTotalporBodegaYGrupo(desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, inventarioInicial,guiasPeriodo);

	    			
	    			//filtro solo las bodegas con permiso:
	    			
	    			List<List<String>> proyectos = new ArrayList<List<String>>();
	    			if(map.size()>0) {
		    			for(List<String> aux: proyectosAux) {
		    				String idBodega = map.get(aux.get(1));
		    				if(idBodega!=null) {
		    					proyectos.add(aux);
		    				}
		    			}
	    			}else {
	    				for(int i=0;i<proyectosAux.size();i++ ) {
	    					proyectos.add(proyectosAux.get(i));
	    				}
	    				
	    			}
	    
	    			
	    			Connection con2 = dbRead.getConnection(dbRead);
	    		
		    			Map<String, List<List<String>>> mapResumenPorGrupo = ReportFacturas.mapResumenPorGrupo2(con2, s.baseDato, valorTotalporBodegaYGrupo); 		// RESUMEN POR GRUPO
		    			
		    			List<List<String>> resumenPorGrupoYProyecto = ReportFacturas.resumenPorGrupoYProyecto(con2, s.baseDato, proyectos, mapResumenPorGrupo); 		// RESUMEN POR GRUPO
		    			
		    			Map<String, List<List<String>>> mapInicioPer = ReportFacturas.mapInicioPerAllBodegas(con2, s.baseDato, inventarioInicial);  					// RESUMEN POR ESTADOS DE PAGO
		    			Map<String, List<List<String>>> mapGuiasPer = ReportFacturas.mapGuiasPer(con2, s.baseDato, guiasPeriodo);  									// RESUMEN POR ESTADOS DE PAGO
		    			
		    			List<List<String>> resumenPorProyectoGrupoYdetalle = 
		    					ReportFacturas.resumenEstadosDePago(con2, s.baseDato, proyectos, desdeAAMMDD, hastaAAMMDD,mapeoDiccionario.get("nEmpresa"), mapInicioPer, mapGuiasPer);   // RESUMEN POR ESTADOS DE PAGO
		    			
		    			Map<String,Equipo> mapEquipo = Equipo.mapAllAllPorCodigo(con2, s.baseDato);
	    			
	    			con2.close();

	    			File file = ReportFacturas.exportaProformaExcelResumen(s.baseDato, mapeoDiccionario,
	    					proyectos,desde,hasta,uf,usd,eur,resumenTotales,resumenPorGrupoYProyecto,resumenPorProyectoGrupoYdetalle, mapEquipo);
	    			
	    			if(file!=null) {
		       			return ok(file,false,Optional.of("EP_Proforma_Resumen.xlsx"));
		       		}else {
		       			return ok("");
		       		}
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok("");
	       	}
    	}else {
    		return ok("");
    	}
	}
	
	
	//====================================================================================
    // MNU proformaConsolidado   Reportes/Proforma/Consolidado (varios Meses)
    //====================================================================================
	
	public Result reportFacturaConsolidado(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		try {
    			Connection con = dbRead.getConnection(dbRead);
    			
    			if(mapeoPermiso.get("proformaConsolidado")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Fechas hoy = Fechas.hoy();
    			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
    			String fecha = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			con.close();
    			return ok(reportFacturaConsolidado.render(mapeoDiccionario,mapeoPermiso,userMnu, fecha));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reportFacturaConsolidadoRtp(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Fechas fecha = Fechas.obtenerFechaDesdeStrAAMMDD(form.get("fecha"));
	       		fecha = Fechas.obtenerFinMes(fecha.getFechaCal());
	       		
	       		Long meses = Long.parseLong(form.get("cantMeses").trim());
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			
	    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
	    			List<List<String>> datos = ReportFacturaConsolidado.reportFacturaConsolidadoRtp(con, s.baseDato, fecha, meses, permisoPorBodega, mapeoDiccionario.get("pais"), s.aplicaPorSucursal, s.id_sucursal);
	    			
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
		    			if(file!=null) {
			       			con.close();
			       			return ok(file,false,Optional.of("Consol_ep_por_meses.xlsx"));
			       		}else {
			       			con.close();
			       			return ok("");
			       		}
	    			}
	    			
	    			con.close();
	    			return ok(reportFacturaConsolidadoRtp.render(mapeoDiccionario,mapeoPermiso,userMnu, datos, form.get("fecha"), form.get("cantMeses"), categorias, nameSerie, mapDataSerie));
	    			
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reportFacturaConsolidadoRtpExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Fechas fecha = Fechas.obtenerFechaDesdeStrAAMMDD(form.get("fecha"));
		    	Long meses = Long.parseLong(form.get("cantMeses"));
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	       			Connection con = dbRead.getConnection(dbRead);
	    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
	    			List<List<String>> datos = ReportFacturaConsolidado.reportFacturaConsolidadoRtp(con, s.baseDato, fecha, meses, permisoPorBodega, mapeoDiccionario.get("pais"), s.aplicaPorSucursal, s.id_sucursal);
	    			File file = ReportFacturaConsolidado.reportFacturaConsolidadoRtpExcel(s.baseDato, mapeoDiccionario, datos);
	    			if(file!=null) {
		       			con.close();
		       			return ok(file,false,Optional.of("Consol_ep_por_meses.xlsx"));
		       		}else {
		       			con.close();
		       			return ok("");
		       		}
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok("");
	       	}
    	}else {
    		return ok("");
    	}
	}
	
	
	//====================================================================================
    // MNU proformaConsolidado   Reportes/Proforma/Consolidado (detalle por grupos)
    //====================================================================================
	
	public Result reportFactConsolconGrupo(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		try {
    			Connection con = dbRead.getConnection(dbRead);
    			
    			if(mapeoPermiso.get("proformaConsolidado")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Fechas hoy = Fechas.hoy();
    			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
    			String fecha = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			con.close();
    			return ok(reportFactConsolconGrupo.render(mapeoDiccionario,mapeoPermiso,userMnu, fecha));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reportFactConsolconGrupoRtp(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Fechas fecha = Fechas.obtenerFechaDesdeStrAAMMDD(form.get("fecha"));
	       		fecha = Fechas.obtenerFinMes(fecha.getFechaCal());
	       		
	       		Long meses = Long.parseLong(form.get("cantMeses").trim());
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			
		    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
		    			
		    			List<List<String>> datos = ReportFacturaConsolidado.reportConsDetalladoRtp(con, s.baseDato, fecha, meses, permisoPorBodega, mapeoDiccionario.get("pais"), s.aplicaPorSucursal, s.id_sucursal);
		    			
		    		con.close();
		    		
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
	    	    	
	    			con.close();
	    			return ok(reportFactConsolconGrupoRtp.render(mapeoDiccionario,mapeoPermiso,userMnu, datos, form.get("fecha"), form.get("cantMeses"), categorias, listSeleccion, mapGrupo, mapDatosEmpresa, listSeleccion2, mapEmpresas, mapDatosGrupo));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reportFactConsolconGrupoRtpExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Fechas fecha = Fechas.obtenerFechaDesdeStrAAMMDD(form.get("fecha"));
		    	Long meses = Long.parseLong(form.get("cantMeses"));
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	       			Connection con = dbRead.getConnection(dbRead);
	    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
	    			List<List<String>> datos = ReportFacturaConsolidado.reportConsDetalladoRtp(con, s.baseDato, fecha, meses, permisoPorBodega, mapeoDiccionario.get("pais"), s.aplicaPorSucursal, s.id_sucursal);
	    			File file = ReportFacturaConsolidado.reportConsDetalladoRtpExcel(s.baseDato, mapeoDiccionario, datos);
	    			if(file!=null) {
		       			con.close();
		       			return ok(file,false,Optional.of("Consol_ep_por_grupo_meses.xlsx"));
		       		}else {
		       			con.close();
		       			return ok("");
		       		}
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok("");
	       	}
    	}else {
    		return ok("");
    	}
	}
	
	
	//====================================================================================
    // MNU proformaConsolidado   Reportes/Proforma/Consolidado (detalle por equipos)
    //====================================================================================
	
	public Result reportFactConsolconEquipos(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		try {
    			Connection con = dbRead.getConnection(dbRead);
    			
    			if(mapeoPermiso.get("proformaConsolidado")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Fechas hoy = Fechas.hoy();
    			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
    			String fecha = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			con.close();
    			return ok(reportFactConsolconEquipos.render(mapeoDiccionario,mapeoPermiso,userMnu, fecha));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reportFactConsolconEquiposRtp(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Fechas fecha = Fechas.obtenerFechaDesdeStrAAMMDD(form.get("fecha"));
	       		fecha = Fechas.obtenerFinMes(fecha.getFechaCal());
	       		
	       		Long meses = Long.parseLong(form.get("cantMeses").trim());
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			
		    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
		    			
		    			List<List<String>> datos = ReportFacturaConsolidado.reportConsDetalladoPorEquipoRtp(con, s.baseDato, fecha, meses, permisoPorBodega, mapeoDiccionario.get("pais"), s.aplicaPorSucursal, s.id_sucursal);
		    			
	    			con.close();
	    			
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
		    			if(file!=null) {
			       			return ok(file,false,Optional.of("Consol_ep_por_equipo_meses.xlsx"));
			       		}else {
			       			return ok("");
			       		}
	    			}
	    			return ok(reportFactConsolconEquiposRtp.render(mapeoDiccionario,mapeoPermiso,userMnu, datos, form.get("fecha"), form.get("cantMeses"), 
	    					categorias, listSeleccion, mapEquipo, mapDatosEmpresa, listSeleccion2, mapEmpresas, mapDatosEquipo, mapNomEquip));
	    			
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reportFactConsolconEquiposRtpExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Fechas fecha = Fechas.obtenerFechaDesdeStrAAMMDD(form.get("fecha"));
		    	Long meses = Long.parseLong(form.get("cantMeses"));
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	       			Connection con = dbRead.getConnection(dbRead);
	    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
	    			List<List<String>> datos = ReportFacturaConsolidado.reportConsDetalladoPorEquipoRtp(con, s.baseDato, fecha, meses, permisoPorBodega, mapeoDiccionario.get("pais"), s.aplicaPorSucursal, s.id_sucursal);
	    			File file = ReportFacturaConsolidado.reportConsDetalladoPorEquipoRtpExcel(s.baseDato, mapeoDiccionario, datos);
	    			if(file!=null) {
		       			con.close();
		       			return ok(file,false,Optional.of("Consol_ep_por_equipo_meses.xlsx"));
		       		}else {
		       			con.close();
		       			return ok("");
		       		}
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok("");
	       	}
    	}else {
    		return ok("");
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
			PDFMergerUtility merger = new PDFMergerUtility();
            File outputFile = TempFile.createTempFile("tmp","null");
            try {
            	String nros = "";
            	for (int i = desdeNro; i<(hastaNro+1); i++) {
            		String valida = map.get(i+"");
            		if(valida != null) {
            			nros += i+", ";
            			String path = baseDato+"/"+"PRpdf"+i+"_proformaArriendo.pdf";
		    			InputStream archivo = Archivos.leerArchivo(path);
		    			merger.addSource(archivo);
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
            } catch (IOException e) {
                e.printStackTrace();
            }
		}
	}
	
	public Result proformaListaPdf(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long year = Long.parseLong(form.get("year"));
	       		int desdeNro = Integer.parseInt(form.get("nroIni"));
	       		int hastaNro = Integer.parseInt(form.get("nroFin"));
	       		String mailDestino =  null;
	       		Map<String,String> map = new HashMap<String,String>();
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
	    			List<List<String>> lista = Proforma.listadoPorAnio(con, s.baseDato, permisoPorBodega, year, s.aplicaPorSucursal, s.id_sucursal);
	    			for(List<String> l: lista) {
	    				map.put(l.get(0), l.get(0));
	    			}
	    			
	    			Usuario usuario = Usuario.findXIdUser(con, s.baseDato, Long.parseLong(s.id_usuario));
	    			if( usuario != null) {
	    				mailDestino = usuario.getEmail().trim().toLowerCase();
	    				if(mailDestino.length() < 4) {
	    					usuario = null;
	    				}
	    			}
	    			con.close();
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		
	       		if(HomeController.isValidEmail(mailDestino)) {
	       			if(map !=null && mailDestino != null) {
		       			MnuReportes.proformaListaPdf0 generar = new MnuReportes.proformaListaPdf0(s.baseDato, desdeNro, hastaNro, map, mailDestino);
		    			generar.start();
		    			String mensaje = "Solicitud en preparación, recibira el resultado al correo:"+mailDestino+". Tomara varios minutos para recibir el correo";
		    			return ok(mensajes.render("/proformaLista/"+year,mensaje));
		       			
		       		}else {
		       			String mensaje = "No es posible generar la solicitud debido a que no existe dato de email en la configuración de su usuario";
		       			return ok(mensajes.render("/home/",mensaje));
		       		}
	       		}else{
	       			String mensaje = "No es posible generar la solicitud debido a que el mail que existe en la configuración de su usuario no es valido";
	       			return ok(mensajes.render("/home/",mensaje));
	       		}
	       	}
    	}
    	return ok("SE PRESENTO UN ERROR");
    }
	
	public Result proformaLista(Http.Request request, Long year) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		try {
    			Connection con = dbRead.getConnection(dbRead);
    			
    			if(mapeoPermiso.get("proformaListado")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			
    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
    			if((long) year == (long) 0) {
    				Fechas hoy = Fechas.hoy();
    				String[] aux = hoy.getFechaStrAAMMDD().split("-");
    				year = Long.parseLong(aux[0]);
    			}
    			
    			List<List<String>> lista = Proforma.listadoPorAnio(con, s.baseDato, permisoPorBodega, year, s.aplicaPorSucursal, s.id_sucursal);
    			Long anio = year;
    			
    			Long minYearGuia = Proforma.anioPrimeraProforma(con, s.baseDato);
    			List<Long> listAnios = new ArrayList<Long>();
    			Fechas hoy = Fechas.hoy();
				String[] aux = hoy.getFechaStrAAMMDD().split("-");
				year = Long.parseLong(aux[0]);
    			while (minYearGuia <= year) {
    				listAnios.add(year);
    				year = year - 1;
    			}
    			
    			con.close();
    			return ok(proformaLista.render(mapeoDiccionario,mapeoPermiso,userMnu, lista, listAnios, anio));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	 public Result proformaListaExcel(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long year = Long.parseLong(form.get("year"));
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			if(mapeoPermiso.get("proformaListado")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
	    			
	    			List<List<String>> lista = Proforma.listadoPorAnio(con, s.baseDato, permisoPorBodega, year, s.aplicaPorSucursal, s.id_sucursal);
	    			
	    			File file = Proforma.listadoPorAnioExcel(s.baseDato, mapeoDiccionario, lista);
	       		
	    			if(file!=null) {
		       			con.close();
		       			return ok(file,false,Optional.of("ListadoDeProformas.xlsx"));
		       		}else {
		       			con.close();
		       			return ok("");
		       		}
	       		} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
	   		return ok("");
    	}else {
    		return ok("");
    	}
    }
	
	public Result generaProformaXml(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_proforma = Long.parseLong(form.get("id_proforma"));
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	    			Connection con = dbWrite.getConnection();
	    			
	    			Proforma proforma = Proforma.find(con, s.baseDato, id_proforma);
	    			InputStream auxfile = Archivos.leerArchivo(s.baseDato+"/"+proforma.proformaXml);
		       		File file = Archivos.parseInputStreamToFile(auxfile);
		       		if(file!=null) {
		       			FormXmlFactura facturaXml = FormXmlFactura.leerArchivoXml(file);
		       			List<TipoReferencia> listTipoReferencias = TipoReferencia.all(con, s.baseDato);
		       			Map<String,String> mapAllCodVsConcep = TipoReferencia.mapAllCodVsConcep(con, s.baseDato);
		       			con.close();
		       			return ok(generaProformaXml.render(mapeoDiccionario,mapeoPermiso,userMnu, facturaXml, id_proforma, listTipoReferencias, mapAllCodVsConcep));
		       		}else {
		       			con.close();
		       			return ok("NO LEO");
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
	
	public Result sendXMLFacura(Http.Request request, Long id_proforma){
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		FormXmlFactura form = formFactory.form(FormXmlFactura.class).withDirectFieldAccess(true).bindFromRequest(request).get();
	       		try {
	       			Connection con = dbWrite.getConnection();
		       		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		       		Proforma proforma = Proforma.find(con, s.baseDato, id_proforma);
		       		FormXmlFactura.grabarReferencias(s.baseDato, mapeoPermiso, proforma.proformaXml, form);
		       		InputStream auxfile = Archivos.leerArchivo(s.baseDato+"/"+proforma.proformaXml);
		       		File file = Archivos.parseInputStreamToFile(auxfile);
		       		if(!file.canRead()){
		       			con.close();
		       			return ok("NO LEO");
		       		}else {
		       			EmisorTributario emisor = EmisorTributario.find(con, s.baseDato);
			       		String correoDestino = emisor.emailEnvioXML;
			       		Email email = new Email();
		       			String asunto = "Envio de XML desde MADA ";
		       			String desde = "desde MADA <informaciones@inqsol.cl>";
		       		    email.setSubject(asunto);
		       			email.setFrom(desde);
		       		    email.setBodyHtml("<html>Archivo adjunto</html>");
		       		    email.addTo(correoDestino);
		       		    email.addAttachment(proforma.proformaXml, file);
		       		    mailerClient.send(email);
		       			//MailerPlugin.send(email);
		       		    con.close();
		       			return ok(mensajes.render("/proformaLista/0","DTE enviado enviado al correo: "+correoDestino ));
		       		}
	       		} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok("");
		}else {
			return ok("");
		}
   	}
	
	public Result generaProformaApiManager(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_proforma = Long.parseLong(form.get("id_proforma"));
	       		try {
	       			Connection con = dbWrite.getConnection();
	    			Proforma proforma = Proforma.find(con, s.baseDato, id_proforma);
	    			String rs = ApiManagerDocDoc.genera(con, s.baseDato, proforma.jsonGenerado, ws, id_proforma);
	    			con.close();
	    			return ok(mensajes.render("/proformaLista/0","API Manager enviada: "+rs ));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result generaProformaApiNubox(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_proforma = Long.parseLong(form.get("id_proforma"));
	       		try {
	       			Connection con = dbWrite.getConnection();
	    			Proforma proforma = Proforma.find(con, s.baseDato, id_proforma);
	    			Long id_guia = (long)0;
	    			String rs = ApiNuboxDocDoc.genera(con, s.baseDato, proforma.jsonGenerado, ws, id_proforma, id_guia);
	    			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "proforma", id_proforma, "update", "hace envio de factura API NUBOX nro: "+proforma.getId());
	    			con.close();
	    			return ok(mensajes.render("/proformaLista/0",rs ));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result generaProformaApiSapConconcreto(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_proforma = Long.parseLong(form.get("id_proforma"));
	       		try {
	       			Connection con = dbWrite.getConnection();
	    			Proforma proforma = Proforma.find(con, s.baseDato, id_proforma);
	    			Long id_guia = (long)0;
	    			String rs = ApiSapConconcreto.genera(con, s.baseDato, proforma.jsonGenerado, ws, id_proforma, id_guia);
	    			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "proforma", id_proforma, "update", "hace envio de factura API SAP Conconcreto nro: "+proforma.getId());
	    			con.close();
	    			rs = rs.replace("\r", "").replace("\n", "");
	    			return ok(mensajes.render("/proformaLista/0",rs ));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result generaProformaWebIConstruye(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_proforma = Long.parseLong(form.get("id_proforma"));
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	       			Connection con = dbWrite.getConnection();
	    			
	    			Proforma proforma = Proforma.find(con, s.baseDato, id_proforma);
	    			InputStream auxfile = Archivos.leerArchivo(s.baseDato+"/"+proforma.proformaXml);
		       		File file = Archivos.parseInputStreamToFile(auxfile);
		       		if(file!=null) {
		       			FormXmlFactura facturaXml = FormXmlFactura.leerArchivoXml(file);
		       			List<TipoReferencia> listTipoReferencias = TipoReferencia.all(con, s.baseDato);
		       			Map<String,String> mapAllCodVsConcep = TipoReferencia.mapAllCodVsConcep(con, s.baseDato);
		       			con.close();
		       			return ok(generaProformaXmlIConstruye.render(mapeoDiccionario,mapeoPermiso,userMnu, facturaXml, id_proforma, listTipoReferencias, mapAllCodVsConcep));
		       		}else {
		       			con.close();
		       			return ok("NO LEO");
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
	
	public Result sendXMLFacturaIConstruye(Http.Request request, Long id_proforma){
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		FormXmlFactura form = formFactory.form(FormXmlFactura.class).withDirectFieldAccess(true).bindFromRequest(request).get();
	       		try {
	       			Connection con = dbWrite.getConnection();
		       		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		       		Proforma proforma = Proforma.find(con, s.baseDato, id_proforma);
		       		String xmlStr = FormXmlFactura.grabarReferencias(s.baseDato, mapeoPermiso, proforma.proformaXml, form);
		       		
		       		
		       		xmlStr = xmlStr.replace("encoding=\"UTF-8\" standalone=\"yes\"", "encoding=\"iso-8859-1\"");
			        xmlStr = xmlStr.replace("DTE version=\"1.0\"", "DTE xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.sii.cl/SiiDte\" version=\"1.0\"");
			        xmlStr = xmlStr.replace("<TermPagoGlosa/>", "<TermPagoGlosa>CONTADO</TermPagoGlosa>");
			        xmlStr = xmlStr.replace("<TermPagoCdg/>", "<FmaPago>1</FmaPago>");
			        
		       		xmlStr = xmlStr.replace("<MedioPago/>", "");
		       		xmlStr = xmlStr.replace("<TipoCtaPago/>", "");
		       		xmlStr = xmlStr.replace("<NumCtaPago/>", "");
		       		xmlStr = xmlStr.replace("<BcoPago/>", "");
		       		xmlStr = xmlStr.replace("<PeriodoDesde/>", "");
		       		xmlStr = xmlStr.replace("<PeriodoHasta/>", "");
		       		xmlStr = xmlStr.replace("<Proyecto/>", "");
		       		xmlStr = xmlStr.replace("</Documento>", "<TmstFirma>2016-11-04T20:33:18</TmstFirma></Documento>");
		       		
		       		xmlStr = xmlStr.replaceAll("&#13;", "\n");
		       		
		       		
	
		       		Proforma.updateJsonApi(con, s.baseDato, proforma.id, xmlStr);
		       		
		       		
		       		Long id_guia = (long)0;
		       		
		       		String rs = WebIConstruye.genera(con, s.baseDato, xmlStr, ws, id_proforma, id_guia);
		       		
		       		
		       		con.close();
		       		return ok(mensajes.render("/proformaLista/0",rs ));
		       		
	       		} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok("");
		}else {
			return ok("");
		}
   	}
	
	public Result generaProformaWebMaximise(Http.Request request){
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
			DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_proforma = Long.parseLong(form.get("id_proforma").trim());
	       		String sucurMaximise = form.get("sucursalMaximise").trim();
	       		String codigoMaximise = form.get("codigoMaximise").trim();
	    		
	    		String ware_code = "BSTGO";
				if(sucurMaximise.equals("2")) {
					ware_code = "BANTO";
				}
				
				String part_code = "VT-001";
				String desc_text = "Arriendo Andamios";
				if(codigoMaximise.equals("VT-002")) {
					part_code = "VT-002";
					desc_text = "Arriendo Encofrados";
				}
				
	       		try {
	       			Connection con = dbWrite.getConnection();
	    			Proforma proforma = Proforma.find(con, s.baseDato, id_proforma);
	    			EmisorTributario emisorTributario = EmisorTributario.find(con, s.baseDato);
	    			
	    			String xmlEncode = proforma.getJsonGenerado();
	    			xmlEncode = xmlEncode.replace("colocaPartCode", part_code);
	    			xmlEncode = xmlEncode.replace("colocaWareCode", ware_code);
	    			xmlEncode = xmlEncode.replace("colocaDescText", desc_text);
	    			Proforma.updateJsonApi(con, s.baseDato, id_proforma, xmlEncode);
	    			
	    			String rs = WebMaximise.generaFactura(con, s.baseDato, xmlEncode, ws, emisorTributario, id_proforma);
	    			try {
		       			Long nroInterno = Long.parseLong(rs);
		       			Proforma.updateNroFiscal(con, s.baseDato, id_proforma, nroInterno.toString());
		       			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "proforma", id_proforma, "update", "hace envio de factura  WS MAXIMISE nro: "+proforma.getId());
		       			rs = "Orden enviada a Maximise";
		       		}catch(Exception e) {
		       			rs = "ERROR: "+ rs;
		       		};
	    			con.close();
	    			rs = rs.replace("\r", "").replace("\n", "");
	    			return ok(mensajes.render("/proformaLista/0",rs));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    	return ok(mensajes.render("/proformaLista/0","SE PRESENTARON ERRORES"));
	}
	
	public Result downFacturaMaximise(Long nroIntOrden, Http.Request request){
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
			DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		try {
	       			Connection con = dbWrite.getConnection();
	       			EmisorTributario emisorTributario = EmisorTributario.find(con, s.baseDato);
	       			String nroIntFactura = WebMaximise.downOrderMaximise(con, s.baseDato, ws, emisorTributario, nroIntOrden);
	       			
	       			if(nroIntFactura.equals("DTE aun no emitido")) {
	       				return ok(mensajes.render("/proformaLista/0","DTE aun no emitido"));
	       			}else {
	       				File file = WebMaximise.downFacturaMaximise(con, s.baseDato, nroIntFactura, ws, emisorTributario);
			       		con.close();
			       		if(file!=null) {
			       			return ok(file,false,Optional.of(nroIntFactura+"_FacturaInterna.pdf"));
			       		}else {
			       			return ok(mensajes.render("/proformaLista/0","Documento aun no ha sido enviado al portal o inexistente."));
			       		}
	       			}
	       		} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    	return ok(mensajes.render("/proformaLista/0","SE PRESENTARON ERRORES"));
	}
	
	public Result proformaElimina(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_proforma = Long.parseLong(form.get("id_proforma"));
	       		try {
	       			Connection con = dbWrite.getConnection();
	    			Proforma.eliminaProforma(con, s.baseDato, id_proforma);
    				con.close();
	       			return (proformaLista(request, (long)0));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	
	
	
	//====================================================================================
    // MNU proformaHaceAjustes   Reportes/Proforma/Hacer Ajustes EP-Proforma
    //====================================================================================
	
	public Result ajustesEp(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		try {
    			Connection con = dbRead.getConnection(dbRead);
    			
    			if(mapeoPermiso.get("proformaHaceAjustes")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
    			List<List<String>> lista = BodegaEmpresa.listaAllBodegasVigentesExternasFiltradas(con, s.baseDato, permisoPorBodega, s.aplicaPorSucursal, s.id_sucursal);
    			con.close();
    			return ok(ajustesEp.render(mapeoDiccionario,mapeoPermiso,userMnu,lista));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result ajustesEpListado(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_bodega = Long.parseLong(form.get("id_bodega"));
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			
	    			List<AjustesEP> lista = AjustesEP.allPorBodega(con, s.baseDato, id_bodega, s.aplicaPorSucursal, s.id_sucursal);
	    			BodegaEmpresa bodegaEmpresa = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodega);
	    			con.close();
	    			return ok(ajustesEpListado.render(mapeoDiccionario,mapeoPermiso,userMnu,lista,bodegaEmpresa));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result ajusteGrabaPDF(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
    		Http.MultipartFormData<TemporaryFile> body = request.body().asMultipartFormData();
			Http.MultipartFormData.FilePart<TemporaryFile> docAdjunto = body.getFile("docAdjunto");
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_ajuste = Long.parseLong(form.get("id_ajuste"));
	       		try {
	       			Connection con = dbWrite.getConnection();
	    			String path = "0";
					if (docAdjunto != null) {
						String nombreArchivoSinExtencion = "Ajuste-ID-" + id_ajuste;
						path = Archivos.grabarArchivos(docAdjunto, s.baseDato, nombreArchivoSinExtencion);
						AjustesEP.modifyPDF(con, s.baseDato, path, id_ajuste);
					}
					AjustesEP ajuste = AjustesEP.find(con, s.baseDato, id_ajuste, s.aplicaPorSucursal, s.id_sucursal);
					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "ajustesEP", id_ajuste, "update", "agrega documento a ajusteEP en bodega "+ajuste.bodegaEmpresa );
					con.close();
					return (ajustesEpListado(request));
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result ajustesEpModificar(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_ajuste = Long.parseLong(form.get("id_ajuste"));
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			
	    			AjustesEP ajusteEp = AjustesEP.find(con, s.baseDato, id_ajuste, s.aplicaPorSucursal, s.id_sucursal);
	    			List<TipoAjustes> listAjustes = TipoAjustes.all(con, s.baseDato);
	    			List<TipoAjustesVenta> listAjustesVenta = TipoAjustesVenta.all(con, s.baseDato);
	    			Long numDec = (long) Moneda.numeroDecimalxId(con, s.baseDato, "1");
					con.close();
					return ok(ajustesEpModificar.render(mapeoDiccionario,mapeoPermiso,userMnu,ajusteEp,listAjustes,listAjustesVenta,numDec));
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result ajustesEpUpdate(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		AjustesEP form = formFactory.form(AjustesEP.class).withDirectFieldAccess(true).bindFromRequest(request).get();
	   		if (form.id == null) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		try {
	       			Connection con = dbWrite.getConnection();
	    			if(AjustesEP.update(con, s.baseDato, form)) {
	    				AjustesEP ajuste = AjustesEP.find(con, s.baseDato, form.id, s.aplicaPorSucursal, s.id_sucursal);
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "ajustesEP", form.id, "update", "modifica ajuste de ep en bodega "+ajuste.bodegaEmpresa);
	    				con.close();
	    				return (ajustesEpListado(request));
	    			}else {
	    				con.close();
	    				return ok(mensajes.render("/",msgError));
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
	
	public Result ajustesEpEliminar(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_ajuste = Long.parseLong(form.get("id_ajuste"));
	       		try {
	       			Connection con = dbWrite.getConnection();
	    			if(AjustesEP.delete(con, s.baseDato, id_ajuste)) {
	    				AjustesEP ajuste = AjustesEP.find(con, s.baseDato, id_ajuste, s.aplicaPorSucursal, s.id_sucursal);
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "ajustesEP", id_ajuste, "delete", "elimina ajuste de ep en bodega "+ajuste.bodegaEmpresa);
	    				con.close();
	    				return (ajustesEpListado(request));
	    			}else {
	    				con.close();
	    				return ok(mensajes.render("/",msgError));
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
	
	public Result ajustesEpNuevo(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_bodega = Long.parseLong(form.get("id_bodega"));
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			
	    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodega);
	    			Fechas hoy = Fechas.hoy();
	    			List<TipoAjustes> listAjustes = TipoAjustes.all(con, s.baseDato);
	    			List<TipoAjustesVenta> listAjustesVenta = TipoAjustesVenta.all(con, s.baseDato);
	    			Long numDec = (long) Moneda.numeroDecimalxId(con, s.baseDato, "1");
	    			Moneda moneda = Moneda.find(con, s.baseDato, (long)1);
	    			con.close();
	    			return ok(ajustesEpNuevo.render(mapeoDiccionario,mapeoPermiso,userMnu,bodega,hoy,listAjustes,listAjustesVenta,numDec,moneda));
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result ajustesEpGrabar(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		AjustesEP form = formFactory.form(AjustesEP.class).withDirectFieldAccess(true).bindFromRequest(request).get();
	   		if (form.id_bodegaEmpresa == null) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		try {
	       			Connection con = dbWrite.getConnection();
	    			if(AjustesEP.create(con, s.baseDato, form)) {
	    				BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, form.id_bodegaEmpresa);
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "ajustesEP", (long)0, "create", "agrega nuevo ajuste de ep en bodega "+bodega.getNombre());
	    				con.close();
	    				return (ajustesEpListado(request));
	    			}else {
	    				con.close();
	    				return (ajustesEpListado(request));
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
	
	
	//====================================================================================
    // MNU proformaListaAjustes   Reportes/Proforma/Reporte Ajustes X Bodegas/proyecto
    //====================================================================================
	
	public Result ajustesEpRpt(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		try {
    			Connection con = dbRead.getConnection(dbRead);
    			
    			if(mapeoPermiso.get("proformaListaAjustes")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
    			permisoPorBodega = permisoPorBodega.replace("movimiento", "bodegaEmpresa");
    			permisoPorBodega = permisoPorBodega.replace("id_bodegaEmpresa", "id");
    			List<List<String>> lista = BodegaEmpresa.listaAllBodegasVigentesExternasConAjustes(con, s.baseDato, permisoPorBodega, s.aplicaPorSucursal, s.id_sucursal);
    			con.close();
    			return ok(ajustesEpRpt.render(mapeoDiccionario,mapeoPermiso,userMnu,lista));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result ajustesEpRptDetalle(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_bodega = Long.parseLong(form.get("id_bodega"));
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			
	    			List<AjustesEP> lista = AjustesEP.allPorBodega(con, s.baseDato, id_bodega, s.aplicaPorSucursal, s.id_sucursal);
	    			BodegaEmpresa bodegaEmpresa = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodega);
	    			con.close();
	    			return ok(ajustesEpRptDetalle.render(mapeoDiccionario,mapeoPermiso,userMnu,lista,bodegaEmpresa));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	//====================================================================================
    // MNU proformaAjustesPorPeriodo   Reportes/Proforma/Reporte Ajustes X Periodo
    //====================================================================================
	
	public Result ajustesPeriodoEpRpt1(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		try {
    			Connection con = dbRead.getConnection(dbRead);
    			
    			if(mapeoPermiso.get("proformaAjustesPorPeriodo")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Fechas hoy = Fechas.hoy();
    			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			con.close();
    			return ok(ajustesPeriodoEpRpt1.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result ajustesPeriodoEpRpt2(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String fechaDesde = form.get("fechaDesde").trim();
	       		String fechaHasta = form.get("fechaHasta").trim();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			
	    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
	    			permisoPorBodega = permisoPorBodega.replace("movimiento", "bodegaEmpresa");
	    			permisoPorBodega = permisoPorBodega.replace("id_bodegaEmpresa", "id");
	    			List<AjustesEP> lista = AjustesEP.allPorPeriodos(con, s.baseDato, fechaDesde, fechaHasta, permisoPorBodega, s.aplicaPorSucursal, s.id_sucursal);
	    			
	    			con.close();
	    			return ok(ajustesPeriodoEpRpt2.render(mapeoDiccionario,mapeoPermiso,userMnu,lista, Fechas.DDMMAA(fechaDesde), Fechas.DDMMAA(fechaHasta)));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	
	//====================================================================================
    // MNU reportFacturaDetalleProyecto   Reportes/Proforma/Ventas por Periodo
    //====================================================================================
	
	public Result reportVentasPorPeriodo0(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		try {
    			Connection con = dbRead.getConnection(dbRead);
    			
    			if(mapeoPermiso.get("reportVentasPorPeriodo")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Fechas hoy = Fechas.hoy();
    			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
    			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			TasasCambio tasas = TasasCambio.allDeUnaFecha(con, s.baseDato, mapeoDiccionario.get("pais"),hasta);
    			con.close();
    			return ok(reportVentasPorPeriodo0.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta, tasas));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}

	public Result reportVentasPorPeriodo1(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
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
	       		try {
	       			Connection con = dbRead.getConnection(dbRead);
	    			List<List<String>> datos = ReportVentas.ventasPorPeriodo(con, s.baseDato, desdeAAMMDD, hastaAAMMDD, tasas);
	    			con.close();
	    			return ok(reportVentasPorPeriodo1.render(mapeoDiccionario,mapeoPermiso,userMnu,datos,desdeAAMMDD,hastaAAMMDD, usd, eur, uf, 
	    					Fechas.DDMMAA(desdeAAMMDD), Fechas.DDMMAA(hastaAAMMDD)));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reportVentasPorPeriodo1Excel(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
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
	       		try {
	    			Connection con = dbRead.getConnection(dbRead);
	    			List<List<String>> datos = ReportVentas.ventasPorPeriodo(con, s.baseDato, desdeAAMMDD, hastaAAMMDD, tasas);
	    			File file = ReportVentas.reportVentasPorPeriodo1Excel(s.baseDato, mapeoDiccionario, datos, desdeAAMMDD, hastaAAMMDD);
	    			if(file!=null) {
		       			con.close();
		       			return ok(file,false,Optional.of("ReportVentasPorPeriodo.xlsx"));
		       		}else {
		       			con.close();
		       			return ok("");
		       		}
	       		} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
	   		return ok("");
    	}else {
    		return ok("");
    	}
    }
	
	//====================================================================================
    // MNU reportesHOHE   Reportes/Proforma/todo SOLO HOHE
    //====================================================================================
	
	public Result hoheReportTodo0(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		try {
    			Connection con = dbRead.getConnection(dbRead);
    			
    			if(mapeoPermiso.get("reportesHOHE")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Fechas hoy = Fechas.hoy();
    			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			con.close();
    			return ok(hoheReportTodo0.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result hoheReportTodo(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String fechaDesde = form.get("fechaDesde").trim();
	       		String fechaHasta = form.get("fechaHasta").trim();
	       		try {
	       			Connection con = dbRead.getConnection(dbRead);
	       			List<List<String>> lista = ReportHohe.datos(con, s.baseDato, fechaDesde, fechaHasta);
	       			InputStream formato = Archivos.leerArchivo("formatos/hoheReportTodo.xlsx");
	       			File file = ReportHohe.reporteHoheExcel(fechaDesde, fechaHasta, lista, formato);
	    			if(file!=null) {
		       			con.close();
		       			return ok(file,false,Optional.of("ReporteMatrizTodo.xlsx"));
		       		}else {
		       			con.close();
		       			return ok("");
		       		}
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok("");
	       	}
    	}else {
    		return ok("");
    	}
	}
	
	public Result hoheTodoResumen0(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		try {
    			Connection con = dbRead.getConnection(dbRead);
    			
    			if(mapeoPermiso.get("reportesHOHE")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Fechas hoy = Fechas.hoy();
    			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			con.close();
    			return ok(hoheTodoResumen0.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result hoheTodoResumen(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String fechaDesde = form.get("fechaDesde").trim();
	       		String fechaHasta = form.get("fechaHasta").trim();
	       		try {
	       			Connection con = dbRead.getConnection(dbRead);
	       			List<List<String>> lista = ReportHohe.datosResumen(con, s.baseDato, fechaDesde, fechaHasta);
	       			InputStream formato = Archivos.leerArchivo("formatos/hoheReportResumen.xlsx");
	       			File file = ReportHohe.reporteHoheResumenExcel(fechaDesde, fechaHasta, lista, formato);
	    			if(file!=null) {
		       			con.close();
		       			return ok(file,false,Optional.of("ReporteMatrizResumen.xlsx"));
		       		}else {
		       			con.close();
		       			return ok("");
		       		}
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok("");
	       	}
    	}else {
    		return ok("");
    	}
	}
	
	public Result hoheMatrizVerticalInventario0(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		try {
    			Connection con = dbRead.getConnection(dbRead);
    			
    			if(mapeoPermiso.get("reportesHOHE")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Fechas hoy = Fechas.hoy();
    			con.close();
    			return ok(hoheMatrizVerticalInventario0.render(mapeoDiccionario,mapeoPermiso,userMnu,hoy.getFechaStrAAMMDD()));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result hoheMatrizVerticalInv0Coti(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		try {
    			Connection con = dbRead.getConnection(dbRead);
    			
    			if(mapeoPermiso.get("reportesHOHE")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Fechas hoy = Fechas.hoy();
    			con.close();
    			return ok(hoheMatrizVerticalInv0Coti.render(mapeoDiccionario,mapeoPermiso,userMnu,hoy.getFechaStrAAMMDD()));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result hoheMatrizVerticalInventario(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String fechaCorte = form.get("fechaCorte").trim();
	       		try {
	       			Connection con = dbRead.getConnection(dbRead);
	       			InputStream formato = Archivos.leerArchivo("formatos/hoheReporteInventarioMatriz.xlsx");
	       			File file = ReportHohe.listaMatrizEquiposHOHE0(con, s.baseDato, fechaCorte, formato);
	    			if(file!=null) {
		       			con.close();
		       			return ok(file,false,Optional.of("ReporteInventarioMatrizVertical.xlsx"));
		       		}else {
		       			con.close();
		       			return ok("");
		       		}
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok("");
	       	}
    	}else {
    		return ok("");
    	}
	}
	
	public Result hoheMatrizVerticalInvCoti(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String fechaCorte = form.get("fechaCorte").trim();
	       		try {
	       			Connection con = dbRead.getConnection(dbRead);
	       			InputStream formato = Archivos.leerArchivo("formatos/hoheReporteInventarioMatriz.xlsx");
	       			File file = ReportHohe.listaMatrizEquiposHOHE0Coti(con, s.baseDato, fechaCorte, formato);
	    			if(file!=null) {
		       			con.close();
		       			return ok(file,false,Optional.of("ReporteInventarioMatrizVertical.xlsx"));
		       		}else {
		       			con.close();
		       			return ok("");
		       		}
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok("");
	       	}
    	}else {
    		return ok("");
    	}
	}
	
	public Result hoheEstadoCotiOt(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		try {
    			Connection con = dbRead.getConnection(dbRead);
    			
    			if(mapeoPermiso.get("reportesHOHE")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<List<String>> datos = ReportHohe.estadoOtDespachos(con, s.baseDato);
    			con.close();
    			return ok(hoheEstadoCotiOt.render(mapeoDiccionario,mapeoPermiso,userMnu,datos));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result hoheEstadoCotiOtExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		try {
	       			Connection con = dbRead.getConnection(dbRead);
	       			InputStream formato = Archivos.leerArchivo("formatos/hoheReporteEstadoCotiOt.xlsx");
	       			List<List<String>> datos = ReportHohe.estadoOtDespachos(con, s.baseDato);
	       			File file = ReportHohe.estadoCotiOtExcel(datos, formato);
	    			if(file!=null) {
		       			con.close();
		       			return ok(file,false,Optional.of("ReporteEstadoCotiOt.xlsx"));
		       		}else {
		       			con.close();
		       			return ok("");
		       		}
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok("");
	       	}
    	}else {
    		return ok("");
    	}
	}
	
	public Result hoheEstadoCotiSinOt(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		try {
    			Connection con = dbRead.getConnection(dbRead);
    			
    			if(mapeoPermiso.get("reportesHOHE")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<List<String>> datos = ReportHohe.estadoCotiConfYnoConfSinOt(con, s.baseDato);
    			con.close();
    			return ok(hoheEstadoCotiSinOt.render(mapeoDiccionario,mapeoPermiso,userMnu,datos));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result hoheEstadoCotiSinOtExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		try {
	       			Connection con = dbRead.getConnection(dbRead);
	       			InputStream formato = Archivos.leerArchivo("formatos/hoheReporteEstadoCotiSinOt.xlsx");
	       			List<List<String>> datos = ReportHohe.estadoCotiConfYnoConfSinOt(con, s.baseDato);
	       			File file = ReportHohe.estadoCotiSinOtExcel(datos, formato);
	    			if(file!=null) {
		       			con.close();
		       			return ok(file,false,Optional.of("ReporteEstadoCotiSinOt.xlsx"));
		       		}else {
		       			con.close();
		       			return ok("");
		       		}
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok("");
	       	}
    	}else {
    		return ok("");
    	}
	}
}





