package controllers;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;

import controllers.HomeController.Sessiones;
import models.contratos.GeneraPDF_Contrato;
import models.contratos.GeneraPDF_ContratoMontax;
import models.contratos.GeneraPDF_ContratoPeruTeca;
import models.forms.CotiJsonA;
import models.forms.FormContrato;
import models.forms.FormCotiza;
import models.forms.FormDespacho;
import models.forms.FormMovimiento;
import models.forms.FormOt;
import models.reports.ReportCotizaciones;
import models.tables.Atributo;
import models.tables.BodegaEmpresa;
import models.tables.Cliente;
import models.tables.Comercial;
import models.tables.Comunas;
import models.tables.CotiBiblioteca;
import models.tables.CotizaDetalle;
import models.tables.CotizaEstado;
import models.tables.CotizaSolucion;
import models.tables.Cotizacion;
import models.tables.EmisorTributario;
import models.tables.Equipo;
import models.tables.Grupo;
import models.tables.Guia;
import models.tables.Moneda;
import models.tables.Movimiento;
import models.tables.Ot;
import models.tables.OtDespachado;
import models.tables.OtEstado;
import models.tables.Precio;
import models.tables.Proyecto;
import models.tables.Regiones;
import models.tables.Sucursal;
import models.tables.TasasCambio;
import models.tables.TipoEstado;
import models.tables.TipoReparacion;
import models.tables.Transportista;
import models.tables.Unidad;
import models.tables.UnidadTiempo;
import models.tables.Usuario;
import models.tables.UsuarioPermiso;
import models.utilities.Archivos;
import models.utilities.DecimalFormato;
import models.utilities.Fechas;
import models.utilities.Registro;
import models.utilities.UserMnu;
import models.xlsx.CotizacionEnExcel;
import models.xlsx.OtEnExcel;
import models.xlsx.OtListaRevisarEnExcel;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.Database;
import play.libs.Files.TemporaryFile;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.mensajes;
import viewsMnuCotizar.html.*;

public class MnuCotizar extends Controller {
	
	public static Database db = HomeController.dbWrite;
	public static FormFactory formFactory = HomeController.formFactory;
	public static String msgError = HomeController.msgError;
	public static String msgErrorFormulario = HomeController.msgErrorFormulario;
	public static String msgSinPermiso = HomeController.msgSinPermiso;
	
	static DecimalFormat myformatdouble0 = new DecimalFormat("#,##0");
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdoubleCompra = new DecimalFormat("#,##0.00");
	
	
	
	
	//============================================================
    // MNU cotizaIngreso   Cotizar/Cotizar/Hacer Cotizacion
    //============================================================

	
	public Result cotizaIngreso(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("cotizaIngreso")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Long id_bodegaEmpresa = (long)0;
    			if(mapeoPermiso.get("parametro.cotizaPorTasa").equals("1")) {
        			List<List<String>> listBodegas = BodegaEmpresa.listaAllBodegasVigentesExternas(con, s.baseDato, mapeoPermiso, s.aplicaPorSucursal, s.id_sucursal);
    				con.close();
        			return ok(cotizaIngreso.render(mapeoDiccionario,mapeoPermiso,userMnu,listBodegas));
    				
    			}
    			con.close();
				return redirect("/cotizaIngreso2/"+id_bodegaEmpresa);
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
    public Result cotizaIngreso2(Http.Request request, Long id_bodegaEmpresa) {
    	Long id_cotizacion = (long) 0;
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("cotizaIngreso")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Long numeroCoti = Cotizacion.findNuevoNumero(con, s.baseDato);
    			String fecha = Fechas.hoy().getFechaStrAAMMDD();
    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
    			FormCotiza formCotiza = new FormCotiza();
    			if(bodega != null) {
    				Cliente cliente = Cliente.find(con, s.baseDato, bodega.getId_cliente());
    				String rutCliente = "";
    				if(cliente!=null) {
    					rutCliente = cliente.getRut();
    				}
    				formCotiza = new FormCotiza(numeroCoti, fecha, bodega.getId_cliente(), bodega.getId_proyecto(), bodega.getId(), 
    						rutCliente, bodega.nickCliente, bodega.nickProyecto, bodega.nombre);
    			} else {
    				formCotiza = new FormCotiza(numeroCoti, fecha);
    			}
    			List<Cliente> listClientes = Cliente.all(con, s.baseDato);
    			List<Proyecto> listProyectos = Proyecto.all(con, s.baseDato);
    			List<List<String>> detalle = FormCotiza.detalleCotizacion(con, s.baseDato, mapeoPermiso, mapeoDiccionario, id_bodegaEmpresa, id_cotizacion, s.id_sucursal, "0", "1",
    					null);
    			String numDecParaTot = "0";
    			
    			if(detalle.size()>0) {
    				numDecParaTot = detalle.get(detalle.size()-1).get(20);
    			}
    			formCotiza.id_cotizacion = id_cotizacion;
    			
    			List<Regiones> listRegiones = Regiones.all(con, s.baseDato);
    			
    			List<UnidadTiempo> listUnTiempo = UnidadTiempo.all(con, s.baseDato);
    			
    			
    			Comercial comercial = new Comercial();
    			Comercial auxComercial = Comercial.findPorIdUsuario(con, s.baseDato, s.id_usuario);
    			if(bodega != null){
    				if(bodega.id_comercial>0) {
    					auxComercial = Comercial.findPorIdUsuario(con, s.baseDato, bodega.id_comercial.toString());
    				}else {
    					auxComercial = null;
    				}
    				
    			}
    			if(auxComercial==null) {
    				comercial.setId((long)0);
    				comercial.setNameUsuario("");
    				if(mapeoPermiso.get("cambiarComercial")!=null) {
    					comercial.setNameUsuario("-- select comercial --");
    				}
    			}else {
    				comercial = auxComercial;
    			}
    			
    			
    			List<Comercial> listComercial = new ArrayList<Comercial>();
    			if(mapeoPermiso.get("cambiarComercial")!=null) {
    				listComercial = Comercial.allPorIdSucursalVig(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
    			}
    			
    			List<Sucursal> listSucursal = new ArrayList<Sucursal>();
    			if(mapeoPermiso.get("cambiarSucursal")!=null) {
    				listSucursal = Sucursal.all(con, s.baseDato);
    			}
    			
    			
    			Sucursal sucursal = Sucursal.find(con, s.baseDato, s.id_sucursal);
    			
    			String importDesdeExcel = "0";
    			
    			String jsonDetalle = Json.toJson(detalle).toString();
    			String jsonListUnTiempo = Json.toJson(listUnTiempo).toString();
    			
    			List<CotizaSolucion> listSoluciones = CotizaSolucion.all(con, s.baseDato);
    			
    			EmisorTributario emisor = EmisorTributario.find(con, s.baseDato);
    			Double tasaIva = emisor.tasaIva/100;
    			if(mapeoPermiso.get("parametro.ivaPorBodega")!=null && mapeoPermiso.get("parametro.ivaPorBodega").equals("1")) {
    	        	if(bodega!=null) {
    	        		Double aux = bodega.getIvaBodega();
    	        		if(aux > 0) {
    	        			tasaIva = bodega.getIvaBodega();
    	        		}
    	        		
    	        	}
    	        }
    			
    			con.close();
    			return ok(cotizaIngreso2.render(mapeoDiccionario,mapeoPermiso,userMnu,formCotiza,listClientes,listProyectos,numDecParaTot,listRegiones, jsonListUnTiempo, 
    					sucursal, comercial, listSucursal,listComercial,importDesdeExcel, jsonDetalle, listSoluciones, tasaIva));
    			
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result tablaInvPorEquipoAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
				try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			String vistaHTML = FormCotiza.vistaInvPorEquipoAjax(con, s.baseDato, id_equipo, mapeoPermiso, mapeoDiccionario);
	    			con.close();
	    			return ok(vistaHTML);
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
				return ok("error");
	       	}
    	}else {
    		return ok("error");
    	}
	}
    
    public Result existeNumeroCotizacionAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	Long numeroCoti = Long.parseLong(form.get("numeroCoti").trim());
				try {
	    			Connection con = db.getConnection();
	    			if(Cotizacion.existeNumero(con, s.baseDato, numeroCoti)) {
	    				con.close();
		    			return ok("El número "+numeroCoti+" de cotizacion ya existe, intente con otro número");
	    			}else {
	    				con.close();
		    			return ok("OK");
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
    
    public Result actualizaComercialesAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	String id_sucursal = form.get("id_sucursal").trim();
				try {
	    			Connection con = db.getConnection();
	    			List<Comercial> listComercial = Comercial.allPorIdSucursalVig(con, s.baseDato, "1", id_sucursal);
	    			String vistaHtml = "<input type=\"hidden\" id=\"id_comercial\" name=\"id_comercial\" value=\"0\">"+
							"<div class=\"input-group\">"+
							"<div class=\"input-group-prepend\">"+
				    			"<span class=\"input-group-text\" id=\"basic-addon1\">Comercial</span>"+
				  			"</div>"+
							"<select id='selComercial' class=\"custom-select\"  style=\"width: 80px;\" onchange=\"$('#id_comercial').val(value)\">"+
								"<option value=\"0\">-- selec comercial --</option>";
	    				for(Comercial x: listComercial) {
	    					vistaHtml += "<option value=\""+x.getId()+"\">"+x.getNameUsuario()+"</option>";
	    				}
	    				vistaHtml += "</select></div>";
	    			con.close();
	    			return ok(vistaHtml);
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
				return ok("error");
	       	}
    	}else {
    		return ok("error");
    	}
	}
    
    public Result actualizaComerciales2Ajax(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	String id_sucursal = form.get("id_sucursal").trim();
				try {
	    			Connection con = db.getConnection();
	    			List<Comercial> listComercial = Comercial.allPorIdSucursalVig(con, s.baseDato, "1", id_sucursal);
	    			String vistaHtml = "<input type=\"hidden\" id=\"id_comercial\" name=\"id_comercial\" value=\"0\">"+
							"<select id='selComercial' class=\"custom-select center\" onchange=\"$('#id_comercial').val(value)\">"+
								"<option value=\"0\">-- TODOS --</option>";
	    				for(Comercial x: listComercial) {
	    					vistaHtml += "<option value=\""+x.getId()+"\">"+x.getNameUsuario()+"</option>";
	    				}
	    				vistaHtml += "</select>";
	    			con.close();
	    			return ok(vistaHtml);
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
				return ok("error");
	       	}
    	}else {
    		return ok("error");
    	}
	}
    
    public Result actualizaPreciosAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	String id_sucursal = form.get("id_sucursal").trim();
				try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			Sucursal sucursal = Sucursal.find(con, s.baseDato, id_sucursal);
	    			List<Precio> listPrecio = Precio.all(con, s.baseDato, mapeoDiccionario, sucursal.getId());
	    			for(Precio p: listPrecio) {
	    				Double pcompra = Double.parseDouble(p.getPrecioCompra().replaceAll(",", ""));
	    				Double pvta = Double.parseDouble(p.getPrecioVenta().replaceAll(",", ""));
	    				Double prepos = Double.parseDouble(p.getPrecioReposicion().replaceAll(",", ""));
	    				Double parr = Double.parseDouble(p.getPrecioArriendo().replaceAll(",", ""));
	    				
	    				
	    				DecimalFormat formato = new DecimalFormat("#.########");
	    				p.setPrecioCompra(formato.format(pcompra));
	    				p.setPrecioVenta(formato.format(pvta));
	    				p.setPrecioReposicion(formato.format(prepos));
	    				p.setPrecioArriendo(formato.format(parr));
	    			}
	    			JsonNode rsJson =  Json.toJson(listPrecio);
	    			con.close();
	    			return ok(rsJson.toString()).as("application/json");
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
				return ok("error");
	       	}
    	}else {
    		return ok("error");
    	}
	}
    
    
    public Result actualizaListaCotiAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
				try {
	    			Connection con = db.getConnection();
	    			List<List<String>> listCotizacion = Cotizacion.listCotiAll(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
	    			String vistaHtml = 
	    					"<table id=\"tablaListaCotizacion\" class=\"table table-sm table-bordered table-condensed table-hover table-fluid\">"+
    								"<thead style=\"background-color: #eeeeee\">"+
    									"<TR>"+
    										"<TH>SUCURSAL</TH>"+
    										"<TH>NUMERO</TH>"+
    										"<TH>FECHA</TH>"+
    										"<TH>CLIENTE</TH>"+
    										"<TH>PROYECTO</TH>"+
    										"<TH>OBSERVACIONES</TH>"+
    									"</TR>"+
    								"</thead>"+
	    							"<tbody>";
	    						for(List<String> x: listCotizacion) {
	    							vistaHtml += "<TR onClick=\"seleccionaCotizacion('"+x.get(0)+"')\" data-dismiss=\"modal\">"+
											"<td  style=\"text-align:center;\">"+x.get(1)+"</td>"+
											"<td  style=\"text-align:center;\">"+x.get(2)+"</td>"+
											"<td  style=\"text-align:center; min-width: 80px;\">"+
												"<div style=\"display:none\">"+Fechas.AAMMDD(x.get(3))+"</div>"+
												Fechas.DDMMAA(x.get(3))+
											"</td>"+
											"<td  style=\"text-align:left;\">"+x.get(4)+"</td>"+
											"<td  style=\"text-align:left;\">"+x.get(5)+"</td>"+
											"<td  style=\"text-align:left;\">"+x.get(6)+"</td>"+
										"</TR>";
	    						}
	    						vistaHtml += "</tbody>"+
		    							"</table>";
	    			con.close();
	    			return ok(vistaHtml);
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
				return ok("error");
	       	}
    	}else {
    		return ok("error");
    	}
	}
    
    
    public Result cotizarNuevo(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    			FormCotiza form = formFactory.form(FormCotiza.class).withDirectFieldAccess(true).bindFromRequest(request).get();
        		if (form.numeroCoti==null || form.id_cliente==null) {
        			return ok(mensajes.render("/",msgErrorFormulario));
        		}else {
        			Http.MultipartFormData<TemporaryFile> body = request.body().asMultipartFormData();
    				Http.MultipartFormData.FilePart<TemporaryFile> docAdjunto = body.getFile("docAdjunto");
        			try {
        				String msg = "0";
    	    			Connection con = db.getConnection();
    	    			if(Cotizacion.existeNumero(con, s.baseDato, form.numeroCoti)) {
    	    				Long nuevoNumero = Cotizacion.findNuevoNumero(con, s.baseDato);
    	    				msg = "El número de cotizacion ya existe, se asigna nuevo número:" + nuevoNumero +", anote el número y si desea cambiarlo vaya a modificar cotización";
    	    				form.numeroCoti = nuevoNumero;
    	    			}
    	    			if(FormCotiza.create(con, s.baseDato, form, docAdjunto, s.id_usuario)) {
    	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "cotizacion", (long)0, "create", "ingresa nueva cotizacion nro: "+form.numeroCoti);
    	    			}
    	    			con.close();
    	    			if(msg.equals("0")) {
    	    				return redirect("/home/");
    	    			}else {
    	    				return ok(mensajes.render("/home/",msg));
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
    
    public Result cotizaModificaJson(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	       		Long id_cotizacion = Long.parseLong(form.get("id_cotizacion").trim());
				try {
	    			Connection con = db.getConnection();
	    			List<CotizaDetalle> detalleCoti = CotizaDetalle.allPorIdCotizacion(con, s.baseDato,id_cotizacion);
	    			List<CotiJsonA> cotiJsonA = new ArrayList<CotiJsonA>();
	    			for(int k=0; k<detalleCoti.size(); k++) {
	    				int numeroDecimale = Moneda.numeroDecimalxId(con, s.baseDato, detalleCoti.get(k).getId_moneda().toString());
	    				CotiJsonA aux = new CotiJsonA();
	    				aux.id_equipo = detalleCoti.get(k).getId_equipo().toString();
	    				aux.cantidad = detalleCoti.get(k).getCantidad();
	    				aux.esVenta = detalleCoti.get(k).getEsVenta().toString();
	    				aux.puReposicion = detalleCoti.get(k).getPrecioReposicion(); 
	    				aux.puArriendo = detalleCoti.get(k).getPrecioArriendo();
	    				aux.permanencia = detalleCoti.get(k).getPermanencia().toString();
	    				aux.nroDecimal = ""+numeroDecimale;
	    				cotiJsonA.add(aux);
	    			}
	    			
	    			Cotizacion coti = Cotizacion.find(con, s.baseDato, id_cotizacion);
	    			
	    			String detalle = Json.toJson(cotiJsonA).toString();
	    			String cotizacion = Json.toJson(coti).toString();
	    			
	    			con.close();
	    			return ok("{\"cotizacion\":"+cotizacion+",\"detalle\":"+detalle+"}").as("application/json");
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
    // MNU cotizaModifica   Cotizar/Cotizar/Modificar Cotizacion
    //============================================================
    
    public Result cotizaListaModificaPeriodo(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("cotizaModifica")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Fechas hoy = Fechas.hoy();
    			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
    			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			con.close();
    			return ok(cotizaListaModificaPeriodo.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result cotizaListaModifica(Http.Request request) {
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
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("cotizaModifica")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			List<List<String>> listCotizacion = Cotizacion.listCotiAllSinConfirmar(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal, desdeAAMMDD, hastaAAMMDD);
	    			con.close();
	    			return ok(cotizaListaModifica.render(mapeoDiccionario,mapeoPermiso,userMnu,listCotizacion));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result cotizaElimina(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	       		Long id_cotizacion = Long.parseLong(form.get("id_cotizacion").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			if(mapeoPermiso.get("cotizaModifica")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			Cotizacion coti = Cotizacion.find(con, s.baseDato, id_cotizacion);
	    			if(Cotizacion.delete(con, s.baseDato, id_cotizacion)){
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "cotizacion", id_cotizacion, "delete", "elimina una cotizacion nro: "+coti.getNumero());
	    				con.close();
	    				return redirect("/home/");
	    			}else {
	    				con.close();
    	    			return ok(mensajes.render("/home/","No fue posible eliminar la cotización"));
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
    
    public Result cotizaModifica(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	       		Long id_cotizacion = Long.parseLong(form.get("id_cotizacion").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("cotizaModifica")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			Cotizacion cotizacion = Cotizacion.find(con, s.baseDato, id_cotizacion);
	    			Long numeroCoti = cotizacion.getNumero();
	    			String fecha = Fechas.AAMMDD(cotizacion.getFecha());
	    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, cotizacion.getId_bodegaEmpresa());
	    			FormCotiza formCotiza = new FormCotiza();
	    			Long id_bodegaEmpresa = (long)0;
	    			if(bodega != null) {
	    				id_bodegaEmpresa = bodega.getId();
	    				Cliente cliente = Cliente.find(con, s.baseDato, bodega.getId_cliente());
	    				String rutCliente = "";
	    				if(cliente!=null) {
	    					rutCliente = cliente.getRut();
	    				}
	    				formCotiza = new FormCotiza(numeroCoti, fecha, bodega.getId_cliente(), bodega.getId_proyecto(), id_bodegaEmpresa, 
	    						rutCliente, bodega.nickCliente, bodega.nickProyecto, bodega.nombre);
	    			} else {
	    				Cliente cliente = Cliente.find(con, s.baseDato, cotizacion.getId_cliente());
	    				String rutCliente = "";
	    				String nomCliente = "";
	    				Long id_cliente = (long)0;
	    				if(cliente!=null) {
	    					rutCliente = cliente.getRut();
	    					nomCliente = cliente.getNickName();
	    					id_cliente = cliente.getId();
	    				}
	    				Proyecto proyecto = Proyecto.find(con, s.baseDato, cotizacion.getId_proyecto());
	    				String nomProyecto = "";
	    				Long id_proyecto = (long)0;
	    				if(proyecto!=null) {
	    					nomProyecto = proyecto.getNickName();
	    					id_proyecto = proyecto.getId();
	    				}
	    				
	    				formCotiza = new FormCotiza(numeroCoti, fecha, id_cliente, rutCliente, nomCliente, id_proyecto, nomProyecto);
	    			}
	    			
	    			
	    			List<Cliente> listClientes = Cliente.all(con, s.baseDato);
	    			List<Proyecto> listProyectos = Proyecto.all(con, s.baseDato);
	    			List<List<String>> lista = FormCotiza.detalleCotizacion(con, s.baseDato, mapeoPermiso, mapeoDiccionario, id_bodegaEmpresa, id_cotizacion, s.id_sucursal, "1", "0",
	    					null);
	    			String numDecParaTot = "0";
	    			if(lista.size()>0) {
	    				numDecParaTot = lista.get(lista.size()-1).get(20);
	    			}
	    			formCotiza.id_cotizacion = id_cotizacion;
	    			formCotiza.id_cliente = cotizacion.getId_cliente();
	    			formCotiza.id_proyecto = cotizacion.getId_proyecto();
	    			formCotiza.id_bodegaEmpresa = cotizacion.getId_bodegaEmpresa();
	    			formCotiza.dctoArriendo = ""+(cotizacion.getDctoArriendo()*100);
	    			formCotiza.dctoVenta = ""+(cotizacion.getDctoVenta()*100);
	    			formCotiza.observaciones = cotizacion.getObservaciones();
	    			formCotiza.cotizacionPDF = cotizacion.getCotizacionPDF();
	    			List<Regiones> listRegiones = Regiones.all(con, s.baseDato);
	    			
	    			List<UnidadTiempo> listUnTiempo = UnidadTiempo.all(con, s.baseDato);
	    			
	    			List<Comercial> listComercial = new ArrayList<Comercial>();
	    			if(mapeoPermiso.get("cambiarComercial")!=null) {
	    				listComercial = Comercial.allPorIdSucursalVig(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
	    			}
	    			
	    			List<Sucursal> listSucursal = new ArrayList<Sucursal>();
	    			if(mapeoPermiso.get("cambiarSucursal")!=null) {
	    				listSucursal = Sucursal.all(con, s.baseDato);
	    			}
	    			
	    			String jsonDetalle = Json.toJson(lista).toString();
	    			String jsonListUnTiempo = Json.toJson(listUnTiempo).toString();
	    			
	    			List<CotizaSolucion> listSoluciones = CotizaSolucion.all(con, s.baseDato);
	    			
	    			EmisorTributario emisor = EmisorTributario.find(con, s.baseDato);
	    			Double tasaIva = emisor.tasaIva/100;
	    			if(mapeoPermiso.get("parametro.ivaPorBodega")!=null && mapeoPermiso.get("parametro.ivaPorBodega").equals("1")) {
	    	        	if(bodega!=null) {
	    	        		Double aux = bodega.getIvaBodega();
	    	        		if(aux > 0) {
	    	        			tasaIva = bodega.getIvaBodega();
	    	        		}
	    	        		
	    	        	}
	    	        }
	    			
	    			con.close();
	    			return ok(cotizaModifica.render(mapeoDiccionario,mapeoPermiso,userMnu,formCotiza,listClientes,listProyectos, numDecParaTot, listRegiones, jsonListUnTiempo, 
	    					listSucursal, listComercial,jsonDetalle, listSoluciones, cotizacion, tasaIva));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result cotizarUpdate(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    			FormCotiza form = formFactory.form(FormCotiza.class).withDirectFieldAccess(true).bindFromRequest(request).get();
        		if (form.numeroCoti==null || form.id_cliente==null) {
        			return ok(mensajes.render("/",msgErrorFormulario));
        		}else {
        			Http.MultipartFormData<TemporaryFile> body = request.body().asMultipartFormData();
    				Http.MultipartFormData.FilePart<TemporaryFile> docAdjunto = body.getFile("docAdjunto");
        			try {
    	    			Connection con = db.getConnection();
    	    			if(FormCotiza.update(con, s.baseDato, form, docAdjunto, s.id_usuario)) {
    	    				Cotizacion coti = Cotizacion.find(con, s.baseDato, form.id_cotizacion);
    	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "cotizacion", form.id_cotizacion, "update", "modifica cotizacion nro: "+coti.getNumero());
    	    			}
    	    			con.close();
    	    			return redirect("/home/");
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
    // MNU cotizaCambiarEstado   Cotizar/Cotizar/Cambiar Estado
    //============================================================
    
    public Result cotizaListaCambiaEstadoPeriodo(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("cotizaCambiarEstado")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Fechas hoy = Fechas.hoy();
    			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
    			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			con.close();
    			return ok(cotizaListaCambiaEstadoPeriodo.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result cotizaListaCambiaEstado(Http.Request request) {
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
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("cotizaCambiarEstado")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			
	    			List<List<String>> listCotizacion = Cotizacion.listCotiallParaCambiarEstado(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal, desdeAAMMDD, hastaAAMMDD);
	    			
	    			List<CotizaEstado> listEstados = CotizaEstado.all(con, s.baseDato);
	    			con.close();
	    			return ok(cotizaListaCambiaEstado.render(mapeoDiccionario,mapeoPermiso,userMnu,listCotizacion, listEstados));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result cambiarCotizaEstadoAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	Long id_cotizacion = Long.parseLong(form.get("id_cotizacion").trim());
	    	  	String id_cotizaEstado = form.get("id_cotizaEstado").trim();
				try {
	    			Connection con = db.getConnection();
	    			Cotizacion.modifyXCampo(con, s.baseDato, "id_cotizaEstado", id_cotizaEstado, id_cotizacion);
	    			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "cotizacion", id_cotizacion, "update", "cambia el estado de cotizacion id: "+id_cotizacion.toString());
	    			con.close();
	    			return ok("OK");
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
				return ok("error");
	       	}
    	}else {
    		return ok("error");
    	}
	}
    
    public Result cambiarCotizaNotaAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	Long id_cotizacion = Long.parseLong(form.get("id_cotizacion").trim());
	    	  	String nota = form.get("nota").trim();
				try {
	    			Connection con = db.getConnection();
	    			Cotizacion.modifyXCampo(con, s.baseDato, "notaCotizaEstado", nota, id_cotizacion);
	    			con.close();
	    			return ok("OK");
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
				return ok("error");
	       	}
    	}else {
    		return ok("error");
    	}
	}
    
    public Result cotizacionFindIdForNumAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	Long numero = Long.parseLong(form.get("numero").trim());
				try {
	    			Connection con = db.getConnection();
	    			Cotizacion coti = Cotizacion.findPorNumero(con, s.baseDato, numero);
	    			if(coti != null) {
	    				con.close();
						return ok(coti.getId().toString());
	    			}
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    	return ok("error");
	}
    
    //===========================================================================
    // MNU cotizaCambiarEstado   Cotizar/Cotizar/Admin Estados Cotizaciones
    //===========================================================================
    
    public Result cotizaEstadoMantencion(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("cotizaCambiarEstado")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<CotizaEstado> listEstados = CotizaEstado.all(con, s.baseDato);
    			con.close();
    			return ok(cotizaEstadoMantencion.render(mapeoDiccionario,mapeoPermiso,userMnu,listEstados));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result cotizaEstadoElimina(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	    	  	Long id_cotizaEstado = Long.parseLong(form.get("id_cotizaEstado").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			if(mapeoPermiso.get("cotizaCambiarEstado")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			if(CotizaEstado.estaEnUso(con, s.baseDato, id_cotizaEstado)) {
	    				String msg = "No es posible eliminar este estado, esta en uso en cotizaciones ";
	    				con.close();
	    				return ok(mensajes.render("/cotizaEstadoMantencion/",msg));
	    			}else {
	    				CotizaEstado coti = CotizaEstado.find(con, s.baseDato, id_cotizaEstado);
	    				if(CotizaEstado.delete(con, s.baseDato, id_cotizaEstado)) {
	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "cotizaEstado", id_cotizaEstado, "delete", "elimina estado "+coti.estado);
	    					con.close();
	        				return redirect("/cotizaEstadoMantencion/");
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
    
    public Result cotizaEstadoModifica(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	    	  	Long id_cotizaEstado = Long.parseLong(form.get("id_cotizaEstado").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("cotizaCambiarEstado")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			CotizaEstado coti = CotizaEstado.find(con, s.baseDato, id_cotizaEstado);
	    			con.close();
	    			return ok(cotizaEstadoModifica.render(mapeoDiccionario,mapeoPermiso,userMnu,coti));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result modificaCotizaEstadoPorCampoAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String campo = form.get("campo").trim();
	       		Long id_cotizaEstado = Long.parseLong(form.get("id_cotizaEstado").trim());
	       		String valor = form.get("valor").trim();
				try {
	    			Connection con = db.getConnection();
	    			if(CotizaEstado.existeEstado(con, s.baseDato, valor)) {
	    				con.close();
    	    			return ok("existe");
	    			}else {
	    				if(CotizaEstado.modificaPorCampo(con, s.baseDato, campo, id_cotizaEstado, valor)) {
	    					CotizaEstado coti = CotizaEstado.find(con, s.baseDato, id_cotizaEstado);
	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "cotizaEstado", id_cotizaEstado, "update", "cambia nombre del estado a "+coti.getEstado());
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
    
    public Result cotizaEstadoAgrega(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("cotizaCambiarEstado")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			con.close();
    			return ok(cotizaEstadoAgrega.render(mapeoDiccionario,mapeoPermiso,userMnu));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result cotizaEstadoNuevo(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String nombreEstado = form.get("nombreEstado").trim();
				try {
	    			Connection con = db.getConnection();
	    			if(CotizaEstado.existeEstado(con, s.baseDato, nombreEstado)) {
	    				String msg = "No es posible crear este estado, el nombre de estado ya existe.";
	    				con.close();
	    				return ok(mensajes.render("/cotizaEstadoMantencion/",msg));
	    			}else {
	    				if(CotizaEstado.create(con, s.baseDato, nombreEstado)) {
	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "cotizaEstado", (long)0, "create", "agrega nuevo estado: "+nombreEstado);
	    					con.close();
	        				return redirect("/cotizaEstadoMantencion/");
	    				}else {
	    					con.close();
	    					return ok(mensajes.render("/",msgError));
	    				}
	    			}
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
				return ok("{ \"status\": false}").as("application/json");
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
  //===========================================================================
  // MNU cotizaCambiarEstado   Cotizar/Cotizar/Admin Tipos de Solucion
  //===========================================================================
    
    public Result cotizaSolucionMantencion(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("cotizaCambiarEstado")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<CotizaSolucion> listSoluciones = CotizaSolucion.all(con, s.baseDato);
    			con.close();
    			return ok(cotizaSolucionMantencion.render(mapeoDiccionario,mapeoPermiso,userMnu,listSoluciones));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result cotizaSolucionElimina(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	    	  	Long id_cotizaSolucion = Long.parseLong(form.get("id_cotizaSolucion").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			if(mapeoPermiso.get("cotizaCambiarEstado")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			if(CotizaSolucion.estaEnUso(con, s.baseDato, id_cotizaSolucion)) {
	    				String msg = "No es posible eliminar esta solucion, esta en uso en cotizaciones ";
	    				con.close();
	    				return ok(mensajes.render("/routes2/cotizaSolucionMantencion/",msg));
	    			}else {
	    				CotizaSolucion coti = CotizaSolucion.find(con, s.baseDato, id_cotizaSolucion);
	    				if(CotizaSolucion.delete(con, s.baseDato, id_cotizaSolucion)) {
	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "cotizaSolucion", id_cotizaSolucion, "delete", "elimina solucion "+coti.solucion);
	    					con.close();
	        				return redirect("/routes2/cotizaSolucionMantencion/");
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
    
    public Result cotizaSolucionModifica(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	    	  	Long id_cotizaSolucion = Long.parseLong(form.get("id_cotizaSolucion").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("cotizaCambiarEstado")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			CotizaSolucion coti = CotizaSolucion.find(con, s.baseDato, id_cotizaSolucion);
	    			con.close();
	    			return ok(cotizaSolucionModifica.render(mapeoDiccionario,mapeoPermiso,userMnu,coti));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result modificaCotizaSolucionPorCampoAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String campo = form.get("campo").trim();
	       		Long id_cotizaSolucion = Long.parseLong(form.get("id_cotizaSolucion").trim());
	       		String valor = form.get("valor").trim();
				try {
	    			Connection con = db.getConnection();
	    			if(CotizaSolucion.existeSolucion(con, s.baseDato, valor)) {
	    				con.close();
    	    			return ok("existe");
	    			}else {
	    				if(CotizaSolucion.modificaPorCampo(con, s.baseDato, campo, id_cotizaSolucion, valor)) {
	    					CotizaSolucion coti = CotizaSolucion.find(con, s.baseDato, id_cotizaSolucion);
	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "cotizaSolucion", id_cotizaSolucion, "update", "cambia nombre de la solucion a "+coti.getSolucion());
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
    
    public Result cotizaSolucionAgrega(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("cotizaCambiarEstado")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			con.close();
    			return ok(cotizaSolucionAgrega.render(mapeoDiccionario,mapeoPermiso,userMnu));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result cotizaSolucionNuevo(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String nombreSolucion = form.get("nombreSolucion").trim();
				try {
	    			Connection con = db.getConnection();
	    			if(CotizaSolucion.existeSolucion(con, s.baseDato, nombreSolucion)) {
	    				String msg = "No es posible crear esta solucion, el nombre de la solucion ya existe.";
	    				con.close();
	    				return ok(mensajes.render("/cotizaSolucionMantencion/",msg));
	    			}else {
	    				if(CotizaSolucion.create(con, s.baseDato, nombreSolucion)) {
	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "cotizaSolucion", (long)0, "create", "agrega nueva solucion: "+nombreSolucion);
	    					con.close();
	        				return redirect("/routes2/cotizaSolucionMantencion/");
	    				}else {
	    					con.close();
	    					return ok(mensajes.render("/",msgError));
	    				}
	    			}
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
				return ok("{ \"status\": false}").as("application/json");
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    //============================================================
    // MNU cotizaImprime   Cotizar/Cotizar/Imprimir Cotizaciones
    //============================================================
    
    public Result cotizaListaImprimirPeriodo(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("cotizaImprime")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Fechas hoy = Fechas.hoy();
    			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
    			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			con.close();
    			return ok(cotizaListaImprimirPeriodo.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result cotizaListaImprimir(Http.Request request) {
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
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("cotizaImprime")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			
	    			List<List<String>> listCotizacion = Cotizacion.listCotiAllParaImprimir(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal, desdeAAMMDD, hastaAAMMDD);
	    			con.close();
	    			return ok(cotizaListaImprimir.render(mapeoDiccionario,mapeoPermiso,userMnu,listCotizacion));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result cotizaImprimir(Http.Request request, Long id_cotizacion) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("cotizaImprime")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Cotizacion coti = Cotizacion.find(con, s.baseDato, id_cotizacion);
    			String tabla = Cotizacion.vistaModalVerCotizacion(con, s.baseDato, coti, mapeoDiccionario, mapeoPermiso);
    			List<Grupo> listGrupos = Grupo.allConEquipos(con, s.baseDato);
    			
    			EmisorTributario emisorTributario = EmisorTributario.find(con, s.baseDato);
    			String tasaIva = emisorTributario.getTasaIva() + " %";
    					
    			con.close();
    			return ok(cotizaImprimir.render(mapeoDiccionario,mapeoPermiso,userMnu,id_cotizacion, tabla, listGrupos, tasaIva));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result cotizaExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	Long id_cotizacion = Long.parseLong(form.get("id_cotizacion").trim());
				try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			File file = CotizacionEnExcel.cotizacionEnExcel(con, s.baseDato, id_cotizacion, mapeoDiccionario, mapeoPermiso);
		       		if(file!=null) {
		       			con.close();
		       			return ok(file,false,Optional.of("Cotizacion.xlsx"));
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
    
    public Result pdfCotizaArriendo(Http.Request request) {
		Sessiones s = new Sessiones(request);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	Long id_cotizacion = Long.parseLong(form.get("id_cotizacion").trim());
	    	  	String cfi = form.get("cfi").trim();
	    	  	String sinDetalle = form.get("sinDetalle");
	    	  	String selectGrupos = form.get("selectGrupos");
	    	  	String tasaIva = form.get("tasaIva").trim();
				try {
	    			Connection con = db.getConnection();
	    			Cotizacion cotizacion = Cotizacion.find(con, s.baseDato,id_cotizacion);
	    			String fileNamePdf = FormCotiza.generaPdfCotizaArriendo(con, s.baseDato, id_cotizacion, mapeoDiccionario, cfi, cotizacion, mapeoPermiso, 
	    					sinDetalle, selectGrupos, tasaIva);
		       		
		       		String titulo = "COTIZACION DE "+ mapeoDiccionario.get("ARRIENDO");
		       		//String url = "%2FcotizaListaImprimir%2F";
		       		String url = "%2Fhome%2F";
		       		
		       		con.close();
		       		return redirect("/routes2/redirShowPDF/"+fileNamePdf+","+titulo+","+url);
		       		
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    	return ok(mensajes.render("/",msgError));
	}
    
    public Result pdfCotizaVenta(Http.Request request) {
		Sessiones s = new Sessiones(request);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	Long id_cotizacion = Long.parseLong(form.get("id_cotizacion").trim());
	    	  	Double tasaCambio = Double.parseDouble(form.get("tipoCambio").replaceAll(",", "").trim());
	    	  	String sinDetalle = form.get("sinDetalle");
	    	  	String tasaIva = form.get("tasaIva").trim();
				try {
	    			Connection con = db.getConnection();
	    			Cotizacion cotizacion = Cotizacion.find(con, s.baseDato,id_cotizacion);
	    			String fileNamePdf = FormCotiza.generaPdfCotizaVenta(con, s.baseDato, id_cotizacion, mapeoDiccionario, tasaCambio, cotizacion, mapeoPermiso, 
	    					sinDetalle, tasaIva);
		       		
		       		String titulo = "COTIZACION DE VENTA";
		       		//String url = "%2FcotizaListaImprimir%2F";
		       		String url = "%2Fhome%2F";
		       		
		       		con.close();
		       		return redirect("/routes2/redirShowPDF/"+fileNamePdf+","+titulo+","+url);
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    	return ok(mensajes.render("/",msgError));
	}
    
    public Result pdfCotizaArrVta(Http.Request request) {
		Sessiones s = new Sessiones(request);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	Long id_cotizacion = Long.parseLong(form.get("id_cotizacion").trim());
	    	  	String sinDetalle = form.get("sinDetalle");
	    	  	String selectGrupos = form.get("selectGrupos");
	    	  	String tasaIva = form.get("tasaIva").trim();
				try {
	    			Connection con = db.getConnection();
	    			Cotizacion cotizacion = Cotizacion.find(con, s.baseDato,id_cotizacion);
	    			
	    			String fileNamePdf = null;
	    			
	    			if(selectGrupos.length()>0) {
	    				fileNamePdf = FormCotiza.generaPdfCotizaArriendo(con, s.baseDato, id_cotizacion, mapeoDiccionario, "0", cotizacion, mapeoPermiso, sinDetalle, 
	    						selectGrupos, tasaIva);
	    			}else {
	    				fileNamePdf = FormCotiza.generaPdfCotizaArrVta(con, s.baseDato, id_cotizacion, mapeoDiccionario, cotizacion, mapeoPermiso, sinDetalle,
	    						tasaIva);
	    			}
	    			
		       		con.close();
		       		String titulo = "COTIZACION DE "+ mapeoDiccionario.get("ARRIENDO");
		       		//String url = "%2FcotizaListaImprimir%2F";
		       		String url = "%2Fhome%2F";
		       		return redirect("/routes2/redirShowPDF/"+fileNamePdf+","+titulo+","+url);
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    	return ok(mensajes.render("/",msgError));
	}
    
    //============================================================
    // MNU cotizaImprime   Cotizar/Cotizar/Resumen Cotizaciones
    //============================================================
    
    public Result cotizaListaResumen0(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("cotizaImprime")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<List<String>> listCotizacion = Cotizacion.listCotiAllClienteSelect(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
    			con.close();
    			return ok(cotizaListaResumen0.render(mapeoDiccionario,mapeoPermiso,userMnu,listCotizacion));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result cotizaListaResumen00(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String id_cliente = form.get("id_cliente").trim();
	       		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
	       		try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			List<List<String>> listProyectos = Cotizacion.listCotiAllProyectoSelect(con, s.baseDato, id_cliente, s.aplicaPorSucursal, s.id_sucursal);
	    			con.close();
	    			return ok(cotizaListaResumen00.render(mapeoDiccionario, mapeoPermiso, userMnu, listProyectos, id_cliente));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result cotizaListaResumen1(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String id_cliente = form.get("id_cliente").trim();
	       		String id_proyecto = form.get("id_proyecto").trim();
	       		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
	       		try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			List<List<String>> listCoti = Cotizacion.listCotiAllCotiPorCliente(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal, id_cliente, id_proyecto);
	    			Cliente cliente = Cliente.find(con, s.baseDato, Long.parseLong(id_cliente));
	    			Proyecto proyecto = Proyecto.find(con, s.baseDato, Long.parseLong(id_proyecto));
	    			con.close();
	    			return ok(cotizaListaResumen1.render(mapeoDiccionario,mapeoPermiso,userMnu,listCoti, cliente, proyecto));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result cotizaListaResumen2(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String listadoIdCoti = form.get("cambiosDeEstados").trim();
	       		String id_cliente = form.get("id_cliente").trim();
	       		String id_proyecto = form.get("id_proyecto").trim();
	       		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    			try {
	    			Connection con = db.getConnection();
	    			if(listadoIdCoti.length()>0) {
	    				listadoIdCoti = "("+listadoIdCoti.substring(0,listadoIdCoti.length()-1)+")";
	    				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		    			List<List<String>> resumen = Cotizacion.listCotiDetallePorCotiSelect(con, s.baseDato, mapeoDiccionario, listadoIdCoti);
		    			Cliente cliente = Cliente.find(con, s.baseDato, Long.parseLong(id_cliente));
		    			Proyecto proyecto = Proyecto.find(con, s.baseDato, Long.parseLong(id_proyecto));
		    			EmisorTributario emisor = EmisorTributario.find(con, s.baseDato);
		    			String tabla = Cotizacion.vistaModalVerCotiResumen(mapeoDiccionario, resumen, cliente, proyecto, mapeoPermiso, emisor);
	    				con.close();
	    				return ok(cotizaListaResumen2.render(mapeoDiccionario,mapeoPermiso,userMnu, tabla , cliente, listadoIdCoti, proyecto));
	    			}
	    			con.close();
	    			return ok(mensajes.render("/home/","Falto seleccionar al menos una cotización"));
    			} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
    			return ok(mensajes.render("/",msgError));
    		}
		}else {
			return ok(mensajes.render("/",msgError));
		}
	}
    
    public Result cotizaListaResumen3(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	       		String listadoIdCoti = form.get("listadoIdCoti").trim();
	       		String id_cliente = form.get("id_cliente").trim();
	       		String id_proyecto = form.get("id_proyecto").trim();
				try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			List<List<String>> resumen = Cotizacion.listCotiDetallePorCotiSelect(con, s.baseDato, mapeoDiccionario, listadoIdCoti);
	    			Cliente cliente = Cliente.find(con, s.baseDato, Long.parseLong(id_cliente));
	    			Proyecto proyecto = Proyecto.find(con, s.baseDato, Long.parseLong(id_proyecto));
	    			
	    			Usuario usuario = Usuario.findXIdUser(con, s.baseDato, Long.parseLong(s.id_usuario));
	    			
	    			Long id_cotiBiblioteca = FormCotiza.generaPdfCotizaArrVtaResumenConDetalle(con, s.baseDato, mapeoPermiso, mapeoDiccionario, resumen, cliente, listadoIdCoti, 
	    					proyecto, s.id_sucursal, s.aplicaPorSucursal, usuario);
	    			
	    			if(id_cotiBiblioteca > 0) {
	    				File pdfSin = FormCotiza.generaPdfCotizaArrVtaResumenSinDet(con, s.baseDato, mapeoPermiso, mapeoDiccionario, resumen, cliente, 
		    					listadoIdCoti, proyecto, s.id_sucursal, s.aplicaPorSucursal, usuario);
	    				if(pdfSin !=null) {
	    					String archivoPdf = "CotiResumenSinDet_"+id_cotiBiblioteca+".pdf";
		    				CotiBiblioteca.updatePdfSinDetalle(con, s.baseDato, archivoPdf, id_cotiBiblioteca);
		    				String path = s.baseDato+"/"+archivoPdf;
							Archivos.grabarArchivo(pdfSin, path);
	    				}
	    				EmisorTributario emisor = EmisorTributario.find(con, s.baseDato);
						File fileXls = CotizacionEnExcel.cotizacionEnExcelResumen(s.baseDato, resumen, cliente, mapeoDiccionario, proyecto, mapeoPermiso,emisor);
						if(fileXls !=null) {
							String archivoXls = "CotiResumenXls_"+id_cotiBiblioteca+".xlsx";
		    				CotiBiblioteca.updateXlsConDetalle(con, s.baseDato, archivoXls, id_cotiBiblioteca);
		    				String path = s.baseDato+"/"+archivoXls;
							Archivos.grabarArchivo(fileXls, path);
						}
	    			}
	    			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "cotiBiblioteca", id_cotiBiblioteca, "create", "agrega nuevo resumen a biblioteca id: "+id_cotiBiblioteca);
	    			con.close();
	    			return redirect("/routes2/cotizaListaBiblioteca/");
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
				return ok("error");
	       	}
    	}else {
    		return ok("error");
    	}
	}
    
    public Result cotizaListaResumen2Excel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	       		String listadoIdCoti = form.get("listadoIdCoti").trim();
	       		String id_cliente = form.get("id_cliente").trim();
	       		String id_proyecto = form.get("id_proyecto").trim();
				try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			List<List<String>> resumen = Cotizacion.listCotiDetallePorCotiSelect(con, s.baseDato, mapeoDiccionario, listadoIdCoti);
	    			Cliente cliente = Cliente.find(con, s.baseDato, Long.parseLong(id_cliente));
	    			Proyecto proyecto = Proyecto.find(con, s.baseDato, Long.parseLong(id_proyecto));
	    			EmisorTributario emisor = EmisorTributario.find(con, s.baseDato);
	    			File file = CotizacionEnExcel.cotizacionEnExcelResumen(s.baseDato, resumen, cliente, mapeoDiccionario, proyecto, mapeoPermiso, emisor);
		       		if(file!=null) {
		       			con.close();
		       			return ok(file,false,Optional.of("Cotizacion.xlsx"));
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
    
    public Result cotizaListaBiblioteca(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("cotizaImprime")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<CotiBiblioteca> listBiblioteca = CotiBiblioteca.all(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
    			con.close();
    			return ok(cotizaListaBiblioteca.render(mapeoDiccionario,mapeoPermiso,userMnu,listBiblioteca));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result cotiBibliotecaElimina(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	       		String id_cotiBiblioteca = form.get("id_cotiBiblioteca").trim();
	    		try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			if(mapeoPermiso.get("cotizaImprime")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			CotiBiblioteca.delete(con, s.baseDato, id_cotiBiblioteca);
	    			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "cotiBiblioteca", Long.parseLong(id_cotiBiblioteca), "delete", "elimina resumen de biblioteca id: "+id_cotiBiblioteca);
	    			con.close();
	    			return redirect("/routes2/cotizaListaBiblioteca/");
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    
    
    
    
    //============================================================
    // MNU cotizaReporte   Cotizar/Cotizar/Reporte Movil
    //============================================================
	
	public Result reportCotizaSel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    		
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("cotizaImprime")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Fechas hoy = Fechas.hoy();
    			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
    			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			
    			
    			Comercial comercial = new Comercial();
    			Comercial auxComercial = Comercial.findPorIdUsuario(con, s.baseDato, s.id_usuario);
    			
    			if(auxComercial==null) {
    				comercial.setId((long)0);
    				comercial.setNameUsuario("");
    				if(mapeoPermiso.get("cambiarComercial")!=null) {
    					comercial.setNameUsuario("-- TODOS --");
    				}
    			}else {
    				comercial = auxComercial;
    			}
    			
    			List<Comercial> listComercial = new ArrayList<Comercial>();
    			if(mapeoPermiso.get("cambiarComercial")!=null) {
    				listComercial = Comercial.allPorIdSucursalVig(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
    			}
    			
    			List<Sucursal> listSucursal = new ArrayList<Sucursal>();
    			if(mapeoPermiso.get("cambiarSucursal")!=null) {
    				listSucursal = Sucursal.all(con, s.baseDato);
    			}
    			
    			Sucursal sucursal = Sucursal.find(con, s.baseDato, s.id_sucursal);
    			
    			con.close();
    			return ok(reportCotizaSel.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta, sucursal, comercial, listSucursal,listComercial));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reportCotizaRpt(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String desdeAAMMDD = form.get("fechaDesde").trim();
	       		String hastaAAMMDD = form.get("fechaHasta").trim();
	       		Long id_sucursal = Long.parseLong(form.get("id_sucursal").trim());
	       		Long id_comercial = Long.parseLong(form.get("id_comercial").trim());
	       		
	       		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			
	    			DecimalFormat myformaNumber = new DecimalFormat("0");
	    			
	    			String nameSucursal = "TODAS";
	    			String condSucursal = "";
	    			
	    			if(id_sucursal != 0) {
	    				condSucursal = " and cotizacion.id_sucursal = " + id_sucursal.toString();
	    				Sucursal sucursal = Sucursal.find(con, s.baseDato, id_sucursal.toString());
	    				nameSucursal = sucursal.getNombre();
	    			}
	    			
	    			String nameComercial = "TODOS";
	    			String condComercial = "";
	    			if(id_comercial != 0) {
	    				condComercial = " and cotizacion.id_comercial = " + id_comercial.toString();
	    				Comercial auxComercial = Comercial.findPorIdUsuario(con, s.baseDato, id_comercial.toString());
	    				nameComercial = auxComercial.getNameUsuario();
	    			}
	    			
	    			String tituloSucursal = " (SUCURSAL: "+nameSucursal+" - COMERCIAL: "+nameComercial+") ";
	    			
	    			Map<String,Double> map = ReportCotizaciones.calculoCoti(con, s.baseDato, desdeAAMMDD, hastaAAMMDD, mapeoDiccionario.get("pais"), condSucursal, condComercial);
	    			
	    			Double val_Confirmadas = map.get("val_Confirmadas");
	    			if(val_Confirmadas == null) val_Confirmadas = (double)0;
	    			Double val_ConOt = map.get("val_ConOt");
	    			if(val_ConOt == null) val_ConOt = (double)0;
	    			Double val_NoConfirmadas = map.get("val_NoConfirmadas");
	    			if(val_NoConfirmadas == null) val_NoConfirmadas = (double)0;
	    			
	    			Double cant_Confirmadas = map.get("cant_Confirmadas");
	    			if(cant_Confirmadas == null) cant_Confirmadas = (double)0;
	    			Double cant_ConOt = map.get("cant_ConOt");
	    			if(cant_ConOt == null) cant_ConOt = (double)0;
	    			Double cant_NoConfirmadas = map.get("cant_NoConfirmadas");
	    			if(cant_NoConfirmadas == null) cant_NoConfirmadas = (double)0;
	    			
	    			String grafValConf = "[['CONFIRMADAS SIN OT',"+myformaNumber.format(val_Confirmadas)+"],['CONFIRMADAS CON OT',"+myformaNumber.format(val_ConOt)+"],['NO CONFIRMADAS',"+myformaNumber.format(val_NoConfirmadas)+"]]";
	    			String grafCantConf = "[['CONFIRMADAS SIN OT',"+cant_Confirmadas+"],['CONFIRMADAS CON OT',"+cant_ConOt+"],['NO CONFIRMADAS',"+cant_NoConfirmadas+"]]";
	    			
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
	    			
	    			con.close();
	    			return ok(reportCotizaRpt.render(mapeoDiccionario,mapeoPermiso,userMnu, grafValConf, grafCantConf, grafValNoConf, grafCantNoConf, fechaDe, fechaA, tituloSucursal));
	    			
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
    // MNU cotizaReporte   Cotizar/Cotizar/Pipeline
    //============================================================
	
	public Result reportPipelineSel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    		
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("cotizaImprime")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Fechas hoy = Fechas.hoy();
    			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
    			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			
    			
    			Comercial comercial = new Comercial();
    			Comercial auxComercial = Comercial.findPorIdUsuario(con, s.baseDato, s.id_usuario);
    			
    			if(auxComercial==null) {
    				comercial.setId((long)0);
    				comercial.setNameUsuario("");
    				if(mapeoPermiso.get("cambiarComercial")!=null) {
    					comercial.setNameUsuario("-- TODOS --");
    				}
    			}else {
    				comercial = auxComercial;
    			}
    			
    			List<Comercial> listComercial = new ArrayList<Comercial>();
    			if(mapeoPermiso.get("cambiarComercial")!=null) {
    				listComercial = Comercial.allPorIdSucursalVig(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
    			}
    			
    			List<Sucursal> listSucursal = new ArrayList<Sucursal>();
    			if(mapeoPermiso.get("cambiarSucursal")!=null) {
    				listSucursal = Sucursal.all(con, s.baseDato);
    			}
    			
    			Sucursal sucursal = Sucursal.find(con, s.baseDato, s.id_sucursal);
    			
    			con.close();
    			return ok(reportPipelineSel.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta, sucursal, comercial, listSucursal,listComercial));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reportPipelineRpt(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String desdeAAMMDD = form.get("fechaDesde").trim();
	       		String hastaAAMMDD = form.get("fechaHasta").trim();
	       		Long id_sucursal = Long.parseLong(form.get("id_sucursal").trim());
	       		Long id_comercial = Long.parseLong(form.get("id_comercial").trim());
	       		
	       		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			
	    			String nameSucursal = "TODAS";
	    			String condSucursal = "";
	    			
	    			if(id_sucursal != 0) {
	    				condSucursal = " and cotizacion.id_sucursal = " + id_sucursal.toString();
	    				Sucursal sucursal = Sucursal.find(con, s.baseDato, id_sucursal.toString());
	    				nameSucursal = sucursal.getNombre();
	    			}
	    			
	    			String nameComercial = "TODOS";
	    			String condComercial = "";
	    			if(id_comercial != 0) {
	    				condComercial = " and cotizacion.id_comercial = " + id_comercial.toString();
	    				Comercial auxComercial = Comercial.findPorIdUsuario(con, s.baseDato, id_comercial.toString());
	    				nameComercial = auxComercial.getNameUsuario();
	    			}
	    			
	    			String tituloSucursal = " (SUCURSAL: "+nameSucursal+" - COMERCIAL: "+nameComercial+") ";
	    			
	    			
	    			List<List<String>> detalle = ReportCotizaciones.detallePipeline(con, s.baseDato, desdeAAMMDD, hastaAAMMDD, mapeoDiccionario.get("pais"), condSucursal, condComercial);
	    			
	    			
	    			String fechaDe = Fechas.DDMMAA(desdeAAMMDD);
	    			String fechaA = Fechas.DDMMAA(hastaAAMMDD);
	    			
	    			con.close();
	    			return ok(reportPipelineRpt.render(mapeoDiccionario,mapeoPermiso,userMnu, detalle, fechaDe, fechaA, tituloSucursal, desdeAAMMDD, hastaAAMMDD, id_sucursal, id_comercial));
	    			
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reportPipelineRptExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String desdeAAMMDD = form.get("desdeAAMMDD").trim();
	       		String hastaAAMMDD = form.get("hastaAAMMDD").trim();
	       		Long id_sucursal = Long.parseLong(form.get("id_sucursal").trim());
	       		Long id_comercial = Long.parseLong(form.get("id_comercial").trim());
	       		
	       		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			
	    			String nameSucursal = "TODAS";
	    			String condSucursal = "";
	    			
	    			if(id_sucursal != 0) {
	    				condSucursal = " and cotizacion.id_sucursal = " + id_sucursal.toString();
	    				Sucursal sucursal = Sucursal.find(con, s.baseDato, id_sucursal.toString());
	    				nameSucursal = sucursal.getNombre();
	    			}
	    			
	    			String nameComercial = "TODOS";
	    			String condComercial = "";
	    			if(id_comercial != 0) {
	    				condComercial = " and cotizacion.id_comercial = " + id_comercial.toString();
	    				Comercial auxComercial = Comercial.findPorIdUsuario(con, s.baseDato, id_comercial.toString());
	    				nameComercial = auxComercial.getNameUsuario();
	    			}
	    			
	    			String tituloSucursal = " (SUCURSAL: "+nameSucursal+" - COMERCIAL: "+nameComercial+") ";
	    			
	    			
	    			List<List<String>> detalle = ReportCotizaciones.detallePipeline(con, s.baseDato, desdeAAMMDD, hastaAAMMDD, mapeoDiccionario.get("pais"), condSucursal, condComercial);
	    			
	    			
	    			String fechaDe = Fechas.DDMMAA(desdeAAMMDD);
	    			String fechaA = Fechas.DDMMAA(hastaAAMMDD);
	    			
	    			File file = ReportCotizaciones.detallePipelineExcel(s.baseDato, mapeoDiccionario, detalle, tituloSucursal, fechaDe, fechaA);
	    			
	    			if(file!=null) {
		       			con.close();
		       			return ok(file,false,Optional.of("Pipeline.xlsx"));
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
	
	public Result reportPipelineDetalle(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	       		try {
	       			String anioMes = form.get("anioMes");
	       			Long id_cotizaEstado = Long.parseLong(form.get("id_cotizaEstado"));
	       			Long id_sucursal = Long.parseLong(form.get("id_sucursal"));
	       			Long id_cliente = Long.parseLong(form.get("id_cliente"));
	       			Long id_proyecto = Long.parseLong(form.get("id_proyecto"));
	       			Long id_comercial = Long.parseLong(form.get("id_comercial"));
	       			Long id_cotizaSolucion = Long.parseLong(form.get("id_cotizaSolucion"));
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			if(mapeoPermiso.get("cotizaImprime")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			List<List<String>> listCotizacion = Cotizacion.listCotiFiltrado(con, s.baseDato, anioMes, id_cotizaEstado, id_sucursal, id_cliente, id_proyecto, id_comercial, id_cotizaSolucion);
	    			String json = Json.toJson(listCotizacion).toString();
	    			con.close();
	    			return ok(json).as("application/json");
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
	   		return ok("error");
    	}else {
    		return ok("error");
    	}
    }
	
    
    //============================================================
    // MNU cotizaConfirma   Cotizar/Cotizaciones/Confirma Cotizaciones
    //============================================================
    
    public Result cotizaListaConfirma(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("cotizaConfirma")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			
    			List<List<String>> listCotizacion = Cotizacion.listCotiAllParaConfirmar(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
    			con.close();
    			return ok(cotizaListaConfirma.render(mapeoDiccionario,mapeoPermiso,userMnu,listCotizacion));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result cotizaConfirma(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String listadoIdCotiConfirmar = form.get("cambiosDeEstados").trim();
    			try {
	    			Connection con = db.getConnection();
	    			if(listadoIdCotiConfirmar.length()>0) {
	    				listadoIdCotiConfirmar = "("+listadoIdCotiConfirmar.substring(0,listadoIdCotiConfirmar.length()-1)+")";
	    				Cotizacion.confirmar(con, s.baseDato, listadoIdCotiConfirmar);
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "cotizacion", (long)0, "confirma", "confirma cotizaciones id: "+listadoIdCotiConfirmar);
	    			}
	    			con.close();
	    			return redirect("/home/");
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
    // MNU otIngreso   Cotizar/Ordenes de trabajo/Hacer OT u OS
    //============================================================
    
    public Result otListaCotizaSinOt(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("otIngreso")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			
    			List<List<String>> listCotizacion = Cotizacion.listCotiSinOt(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
    			con.close();
    			return ok(otListaCotizaSinOt.render(mapeoDiccionario,mapeoPermiso,userMnu,listCotizacion));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
	public Result otHacer(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_cotizacion = Long.parseLong(form.get("id_cotizacion").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("otIngreso")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			Cotizacion coti = Cotizacion.find(con, s.baseDato, id_cotizacion);
	    			Long numeroOt = Ot.findNuevoNumero(con, s.baseDato);
	    			String fecha = Fechas.hoy().getFechaStrAAMMDD();
	    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, coti.getId_bodegaEmpresa());
	    			Proyecto proyecto = Proyecto.find(con, s.baseDato, coti.id_proyecto);
	    			String nickProyecto = "";
	    			Long id_proyecto = (long)0;
	    			if(proyecto != null) {
	    				nickProyecto = proyecto.nickName;
	    				id_proyecto = proyecto.id;
	    			}
	    			FormOt formOt = new FormOt();
	    			if(bodega != null) {
	    				formOt = new FormOt((long)0, coti.getId(), coti.getId_bodegaEmpresa(), numeroOt, fecha, "", bodega.nombre, id_proyecto, nickProyecto, "");
	    			} else {
	    				formOt = new FormOt((long)0, coti.getId(), coti.getId_bodegaEmpresa(), numeroOt, fecha, "", id_proyecto, nickProyecto);
	    			}
	    			
	    			String esPorSucursal = "1";
	    			if(mapeoPermiso.get("cambiarSucursal")!=null) {
	    				esPorSucursal = "0";
	    			}
	    			
	    			
	    			
	    			List<List<String>> listBodegas = BodegaEmpresa.listaAllBodegasVigentesExternas(con, s.baseDato, mapeoPermiso, esPorSucursal, coti.getId_sucursal().toString());
	    			List<Proyecto> listProyectos = Proyecto.all(con, s.baseDato);
	    			String tabla = Cotizacion.vistaModalVerCotizacion(con, s.baseDato, coti, mapeoDiccionario, mapeoPermiso);
	    			List<Regiones> listRegiones = Regiones.all(con, s.baseDato);
	    			
	    			con.close();
	    			return ok(otHacer.render(mapeoDiccionario,mapeoPermiso,userMnu,formOt,listBodegas,tabla,listProyectos,listRegiones));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
	
	public Result otGrabar(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    			FormOt form = formFactory.form(FormOt.class).withDirectFieldAccess(true).bindFromRequest(request).get();
        		if (form.numeroOt==null || form.id_ot==null) {
        			return ok(mensajes.render("/",msgErrorFormulario));
        		}else {
        			Http.MultipartFormData<TemporaryFile> body = request.body().asMultipartFormData();
    				Http.MultipartFormData.FilePart<TemporaryFile> docAdjunto = body.getFile("docAdjunto");
        			try {
        				String msg = "0";
    	    			Connection con = db.getConnection();
    	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    	    			
    	    			if(Ot.existeIdCoti(con, s.baseDato, form.id_cotizacion)) {
    	    				msg = "La cotización ya fue convertida a ordén de trabajo o servicio, no es posible repetir";
    	    				con.close();
    	    				return ok(mensajes.render("/home/",msg));
    	    			}
    	    			
    	    			if(Ot.existeNumero(con, s.baseDato, form.numeroOt)) {
    	    				Long nuevoNumero = Ot.findNuevoNumero(con, s.baseDato);
    	    				msg = "El numero de "+mapeoDiccionario.get("OT")+" ya existe, se asigna nuevo numero:" + nuevoNumero;
    	    				form.numeroOt = nuevoNumero;
    	    			}
    	    			
    	    			if(FormOt.create(con, s.baseDato, form, docAdjunto, s.id_usuario)) {
    	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "ot", (long)0, "create", "crea nueva ot nro: "+form.numeroOt);
    	    			}
    	    			con.close();
    	    			if(msg.equals("0")) {
    	    				return redirect("/home/");
    	    			}else {
    	    				return ok(mensajes.render("/home/",msg));
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
	
	//======================================================================
    // MNU otIngreso   Cotizar/Ordenes de trabajo/Eliminar OT u OS
    //======================================================================
    
    public Result otListaEliminar(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("otIngreso")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			
    			List<List<String>> listOt = Ot.listOtEliminar(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
    			con.close();
    			return ok(otListaEliminar.render(mapeoDiccionario,mapeoPermiso,userMnu,listOt));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result verOtAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	Long id_ot = Long.parseLong(form.get("id_ot").trim());
				try {
	    			Connection con = db.getConnection();
    				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    				String vista = Ot.vistaModalVerOt(con, s.baseDato, id_ot, mapeoDiccionario);
					con.close();
					return ok(vista);
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
				return ok("error");
	       	}
    	}else {
    		return ok("error");
    	}
	}
    
    public Result actualFechaEnvioOtAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	Long id_ot = Long.parseLong(form.get("id_ot").trim());
	    	  	String fechaEnvio = form.get("fechaEntrega").trim();
				try {
	    			Connection con = db.getConnection();
    				Fechas hoy = Fechas.hoy();
    				String fechaActual = hoy.getFechaStrAAMMDD();
    				
    				if(fechaEnvio.equals("")) {
    					fechaEnvio = null;
    					fechaActual = null;
    				}
    				
    				if(Ot.modifyFechaEnvio(con, s.baseDato, fechaActual, fechaEnvio, id_ot)){
    						
    						if(fechaActual!=null) {
    							con.close();
    							return ok(Fechas.DDMMAA(fechaActual));
    						}else {
    							con.close();
    							return ok("");
    						}
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
    
    public Result otElimina(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_ot = Long.parseLong(form.get("id_ot").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			if(mapeoPermiso.get("otIngreso")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			Ot ot = Ot.find(con, s.baseDato, id_ot);
	    			
	    			String msg = "";
	    					
	    			if(OtDespachado.existenDespachosAsociados(con, s.baseDato, id_ot) || Movimiento.existeMovAsosiado_a_Coti(con, s.baseDato, ot.id_cotizacion)){
	    				msg = "No es posible eliminar la órden, existen despachos o movimientos asociados, "
	    						+ " primero debe eliminar todo despacho o movimientos asociados a la ot";
	    			}else {
	    				Ot.delete(con, s.baseDato, ot, mapeoPermiso);
		    			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "ot", id_ot, "delete", "elimina una orden de trabajo nro: "+ot.getNumero());
						msg = "Se eliminó la órden cambiando la cotización asociada de confirmada a no confirmada, "
								+ "para generar una nueva órden a partir de la misma cotización debe confirmarla nuevamente";
	    			}
	    			
					con.close();
					return ok(mensajes.render("/otListaEliminar/",msg));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    
    //======================================================================
    // MNU otIngreso   Cotizar/Ordenes de trabajo/Cambiar Estado OT u OS
    //======================================================================
    
    public Result otListaCambiarEstadoPeriodo(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("cotizaCambiarEstado")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Fechas hoy = Fechas.hoy();
    			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
    			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			con.close();
    			return ok(otListaCambiarEstadoPeriodo.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result otListaCambiarEstado(Http.Request request) {
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
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("cotizaCambiarEstado")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			
	    			List<List<String>> listOt = Ot.listOtCambiarEstado(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal, desdeAAMMDD, hastaAAMMDD);
	    			List<OtEstado> listEstados = OtEstado.all(con, s.baseDato);
	    			con.close();
	    			return ok(otListaCambiarEstado.render(mapeoDiccionario,mapeoPermiso,userMnu,listOt, listEstados));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result cambiarOtEstadoAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	Long id_ot = Long.parseLong(form.get("id_ot").trim());
	    	  	String id_otEstado = form.get("id_otEstado").trim();
				try {
	    			Connection con = db.getConnection();
	    			Ot.modifyXCampo(con, s.baseDato, "id_otEstado", id_otEstado, id_ot);
	    			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "ot", id_ot, "update", "cambia el estado de ot id: "+id_ot.toString());
	    			con.close();
	    			return ok("OK");
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
				return ok("error");
	       	}
    	}else {
    		return ok("error");
    	}
	}
    
    public Result cambiarOtNotaAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	Long id_ot = Long.parseLong(form.get("id_ot").trim());
	    	  	String nota = form.get("nota").trim();
				try {
	    			Connection con = db.getConnection();
	    			Ot.modifyXCampo(con, s.baseDato, "notaOtEstado", nota, id_ot);
	    			con.close();
	    			return ok("OK");
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
				return ok("error");
	       	}
    	}else {
    		return ok("error");
    	}
	}
    
  //===========================================================================
    // MNU otIngreso   Cotizar/Cotizar/Admin Estados OT
    //===========================================================================
    
    public Result otEstadoMantencion(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("otIngreso")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			
    			List<OtEstado> listOt = OtEstado.all(con, s.baseDato);
    			con.close();
    			return ok(otEstadoMantencion.render(mapeoDiccionario,mapeoPermiso,userMnu,listOt));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result otEstadoElimina(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	    	  	Long id_otEstado = Long.parseLong(form.get("id_otEstado").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("otIngreso")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			if(OtEstado.estaEnUso(con, s.baseDato, id_otEstado)) {
	    				String msg = "No es posible eliminar este estado, esta en uso en "+ mapeoDiccionario.get("Ordenes_de_trabajo");
	    				con.close();
	    				return ok(mensajes.render("/otEstadoMantencion/",msg));
	    			}else {
	    				OtEstado ot = OtEstado.find(con, s.baseDato, id_otEstado);
	    				if(OtEstado.delete(con, s.baseDato, id_otEstado)) {
	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "otEstado", id_otEstado, "delete", "elimina estado "+ot.estado);
	    					con.close();
	        				return redirect("/otEstadoMantencion/");
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
    
    public Result otEstadoModifica(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	    	  	Long id_otEstado = Long.parseLong(form.get("id_otEstado").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("otIngreso")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			OtEstado ot = OtEstado.find(con, s.baseDato, id_otEstado);
	    			con.close();
	    			return ok(otEstadoModifica.render(mapeoDiccionario,mapeoPermiso,userMnu,ot));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result modificaOtEstadoPorCampoAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String campo = form.get("campo").trim();
	       		Long id_otEstado = Long.parseLong(form.get("id_otEstado").trim());
	       		String valor = form.get("valor").trim();
				try {
	    			Connection con = db.getConnection();
	    			if(OtEstado.existeEstado(con, s.baseDato, valor)) {
	    				con.close();
    	    			return ok("existe");
	    			}else {
	    				if(OtEstado.modificaPorCampo(con, s.baseDato, campo, id_otEstado, valor)) {
	    					OtEstado ot = OtEstado.find(con, s.baseDato, id_otEstado);
	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "otEstado", id_otEstado, "update", "cambia nombre del estado a "+ot.getEstado());
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
    
    public Result otEstadoAgrega(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("otIngreso")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			con.close();
    			return ok(otEstadoAgrega.render(mapeoDiccionario,mapeoPermiso,userMnu));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result otEstadoNuevo(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String nombreEstado = form.get("nombreEstado").trim();
				try {
	    			Connection con = db.getConnection();
	    			if(OtEstado.existeEstado(con, s.baseDato, nombreEstado)) {
	    				String msg = "No es posible crear este estado, el nombre de estado ya existe.";
	    				con.close();
	    				return ok(mensajes.render("/otEstadoMantencion/",msg));
	    			}else {
	    				if(OtEstado.create(con, s.baseDato, nombreEstado)) {
	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "otEstado", (long)0, "create", "agrega nuevo estado: "+nombreEstado);
	    					con.close();
	        				return redirect("/otEstadoMantencion/");
	    				}else {
	    					con.close();
	    					return ok(mensajes.render("/",msgError));
	    				}
	    			}
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
				return ok("{ \"status\": false}").as("application/json");
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    //======================================================================
    // MNU otIngreso   Cotizar/Ordenes de trabajo/Revisar Imprimir OT u OS
    //======================================================================
    public Result otListaRevisarPeriodo(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("otRevisa")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Fechas hoy = Fechas.hoy();
    			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
    			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			con.close();
    			return ok(otListaRevisarPeriodo.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result otListaRevisar(Http.Request request) {
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
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("otRevisa")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			
	    			List<List<String>> listOt = Ot.listOtRevisar(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal, desdeAAMMDD, hastaAAMMDD);
	    			con.close();
	    			return ok(otListaRevisar.render(mapeoDiccionario,mapeoPermiso,userMnu,listOt,desdeAAMMDD,hastaAAMMDD));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result otListaRevisarExcel(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgError));
	       	}else {
	       		
	       		String desdeAAMMDD = form.get("fechaDesde").trim();
	       		String hastaAAMMDD = form.get("fechaHasta").trim();
	       		
	       		try {
	    			Connection con = db.getConnection();
	    		//	
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("otRevisa")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			
	    			List<List<String>> listOt = Ot.listOtRevisar(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal, desdeAAMMDD, hastaAAMMDD);
	    			File file = OtListaRevisarEnExcel.otListaRevisarExcel(s.baseDato, listOt, mapeoDiccionario);
	   
	    			if(file!=null) {
		       			con.close();
		       			return ok(file,false,Optional.of("ListaDeMovimientos.xlsx"));
		       		}else {
		       			con.close();
		       			return ok("");
		       		}
	    			
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    		
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result revisarOt(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_ot = Long.parseLong(form.get("id_ot").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("otRevisa")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			String tabla = Ot.vistaRevisarOt(con, s.baseDato, id_ot, mapeoDiccionario);
	    			con.close();
	    			return ok(revisarOt.render(mapeoDiccionario,mapeoPermiso,userMnu, id_ot, tabla));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result trazaEqOtAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	       		Long id_ot = Long.parseLong(form.get("id_ot").trim());
	    	  	Long id_equipoOrigen = Long.parseLong(form.get("id_equipoOrigen").trim());
				try {
	    			Connection con = db.getConnection();
	    			String tabla = OtDespachado.modalTrazaPorIdEquipOrigen(con, s.baseDato, id_ot, id_equipoOrigen);
	    			con.close();
	    			return ok(tabla);
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
				return ok("error");
	       	}
    	}else {
    		return ok("error");
    	}
	}
    
    public Result otRevisarExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	       		Long id_ot = Long.parseLong(form.get("id_ot").trim());
				try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			File file = OtEnExcel.otEnExcel(con, s.baseDato, id_ot, mapeoDiccionario);
		       		if(file!=null) {
		       			con.close();
		       			return ok(file,false,Optional.of("Orden.xlsx"));
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
  // MNU cotizaReporte   Cotizar/Cotizar/Reporte Movil OT
  //============================================================
	
	public Result reportOtSel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("cotizaImprime")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Fechas hoy = Fechas.hoy();
    			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
    			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			
    			Comercial comercial = new Comercial();
    			Comercial auxComercial = Comercial.findPorIdUsuario(con, s.baseDato, s.id_usuario);
    			
    			if(auxComercial==null) {
    				comercial.setId((long)0);
    				comercial.setNameUsuario("");
    				if(mapeoPermiso.get("cambiarComercial")!=null) {
    					comercial.setNameUsuario("-- TODOS --");
    				}
    			}else {
    				comercial = auxComercial;
    			}
    			
    			List<Comercial> listComercial = new ArrayList<Comercial>();
    			if(mapeoPermiso.get("cambiarComercial")!=null) {
    				listComercial = Comercial.allPorIdSucursalVig(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
    			}
    			
    			List<Sucursal> listSucursal = new ArrayList<Sucursal>();
    			if(mapeoPermiso.get("cambiarSucursal")!=null) {
    				listSucursal = Sucursal.all(con, s.baseDato);
    			}
    			
    			Sucursal sucursal = Sucursal.find(con, s.baseDato, s.id_sucursal);
    			
    			con.close();
    			return ok(reportOtSel.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta, sucursal, comercial, listSucursal,listComercial));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result reportOtRpt(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String desdeAAMMDD = form.get("fechaDesde").trim();
	       		String hastaAAMMDD = form.get("fechaHasta").trim();
	       		Long id_sucursal = Long.parseLong(form.get("id_sucursal").trim());
	       		Long id_comercial = Long.parseLong(form.get("id_comercial").trim());
	       		
	       		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			
	    			DecimalFormat myformaNumber = new DecimalFormat("0");
	    			
	    			String nameSucursal = "TODAS";
	    			String condSucursal = "";
	    			if(id_sucursal != 0) {
	    				condSucursal = " and bodegaEmpresa.id_sucursal = " + id_sucursal.toString();
	    				Sucursal sucursal = Sucursal.find(con, s.baseDato, id_sucursal.toString());
	    				nameSucursal = sucursal.getNombre();
	    			}
	    			
	    			String nameComercial = "TODOS";
	    			String condComercial = "";
	    			
	    			if(id_comercial != 0) {
	    				condComercial = " and bodegaEmpresa.id_comercial = " + id_comercial.toString();
	    				Comercial auxComercial = Comercial.findPorIdUsuario(con, s.baseDato, id_comercial.toString());
	    				nameComercial = auxComercial.getNameUsuario();
	    			}
	    			
	    			String tituloSucursal = " (SUCURSAL: "+nameSucursal+" - COMERCIAL: "+nameComercial+") ";
	    			
	    			Map<String,Double> map = ReportCotizaciones.calculoOt(con, s.baseDato, desdeAAMMDD, hastaAAMMDD,  mapeoDiccionario.get("pais"),  condSucursal, condComercial);

	    			
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
	    			
	    			
	    			con.close();
	    			return ok(reportOtRpt.render(mapeoDiccionario,mapeoPermiso,userMnu, grafValConOt, grafCantConOt, grafValSinOt, grafCantSinOt, fechaDe, fechaA, tituloSucursal));
	    			
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
  // MNU cotizaReporte   Cotizar/Cotizar/Saldos de OT
  //============================================================
	
	public Result reportOtSaldos(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("cotizaImprime")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Map<String,Double> mapAllPorDespachar = new HashMap<String,Double>();
    			Map<String,Double> mapAllStockBodInt = Movimiento.mapAllStockBodInt(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
    			List<List<String>> listaBodExternasVigentes = new ArrayList<List<String>>();
    			List<List<String>> listaBodInternas = BodegaEmpresa.listaAllBodegasVigentesInternas(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
    			List<Equipo> listaEquipos = new ArrayList<Equipo>();
				/*******************************************************************************************/
				Map<String, Double> mapAllDespachado = OtDespachado.mapAllDespachado(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
				Map<String, Double> mapAllCotizadoConOt = CotizaDetalle.mapAllCotizadoConOt(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
				/*******************************************************************************************/
				Map<Long, Long> mapAuxEquipos = new HashMap<Long, Long>();
				Map<Long,List<String>> mapBodegaVigExter = BodegaEmpresa.mapAllVigentesExternas(con, s.baseDato, mapeoPermiso, s.aplicaPorSucursal, s.id_sucursal); //0=id_bodega 5=nombre
				Map<List<String>,List<String>> mapAuxBodExternas = new HashMap<List<String>,List<String>>();
				
				mapAllStockBodInt.forEach((k, v) -> {
					String[] dePaso = k.split("_");
					mapAuxEquipos.put(Long.parseLong(dePaso[1]), Long.parseLong(dePaso[1]));
				});
				
				mapAllCotizadoConOt.forEach((k, v) -> {
					Double aux = mapAllDespachado.get(k);
					if(aux == null) {
						aux = (double)0;
					}
					aux = v - aux;
					
					if (aux != 0) {
						String[] dePaso = k.split("_");
						List<String> bodega = mapBodegaVigExter.get(Long.parseLong(dePaso[0]));
						if(bodega != null) {
							mapAllPorDespachar.put(k, aux);
							List<String> aux1 = new ArrayList<String>();
							aux1.add(bodega.get(1)); // 0 id_bodegaEmpresa
							aux1.add(bodega.get(5)); // 1 nombre bodega;
							
							aux1.add(bodega.get(7)); 	// 2 nickCliente;
							aux1.add(bodega.get(16)); 	// 3 nombre sucursal;
							aux1.add(bodega.get(11)); 	// 4 nombre comercial;
							
							mapAuxBodExternas.put(aux1, aux1);
							mapAuxEquipos.put(Long.parseLong(dePaso[1]), Long.parseLong(dePaso[1]));
						}
					}
				});
				
				
				mapAuxBodExternas.forEach((k,v)->{
					listaBodExternasVigentes.add(k);
				});
				Map<Long, Equipo> mapEquipo = Equipo.mapAllVigentes(con, s.baseDato);
				mapAuxEquipos.forEach((k, v) -> {
					Equipo aux = mapEquipo.get(k);
					if (aux != null) {
						listaEquipos.add(aux);
					}
				});
				/*******************************************************************************************/
    			
    			
				
				String titulo1 = "<th>Grupo</th><th>Codigo</th><th style='min-width:200px'>Equipo</th><th colspan='"+(listaBodInternas.size()+1)+"'>INTERNAS</th><th colspan='"+(listaBodExternasVigentes.size()+1)+"'>CLIENTES</th>";
				String titulo2 = "<th></th><th></th><th></th>";
				String titulo3 = "<th></th><th></th><th>Cliente</th>";
				String titulo7 = "<th></th><th></th><th>Sucursal</th>";
				String titulo8 = "<th></th><th></th><th>Comercial</th>";
				String titulo9 = "<th>Grupo</th><th>Codigo</th><th>Equipo</th>";
				
						for(List<String> x: listaBodInternas) {
							titulo2 += "<th style=\"text-align:center; white-space: nowrap;vertical-align: middle\">"
									+ "<div style=\"display: flex;flex-direction: row;\">"
									+ "<span style=\"writing-mode: vertical-lr;transform: rotate(180deg);width: 23px;\">"
									+ x.get(5)+"</span></div></th>";
							
							titulo3 += "<th style=\"text-align:center;vertical-align: middle\"></th>";
							titulo7 += "<th style=\"text-align:center;vertical-align: middle\">"+ x.get(16)+"</th>";
							titulo8 += "<th style=\"text-align:center;vertical-align: middle\">"+ x.get(11)+"</th>";
							titulo9 += "<th style=\"text-align:center;vertical-align: middle\"></th>";
						}
						titulo2 += "<th style=\"text-align:center; white-space: nowrap; vertical-align: middle\">"
								+ "<div style=\"display: flex;flex-direction: row;\">"
								+ "<span style=\"writing-mode: vertical-lr;transform: rotate(180deg);width: 23px;\">"
								+ "TOTAL STOCK</span></div></th>";
						
						titulo3 += "<th style=\"text-align:center;vertical-align: middle\"></th>";
						titulo7 += "<th style=\"text-align:center;vertical-align: middle\"></th>";
						titulo8 += "<th style=\"text-align:center;vertical-align: middle\"></th>";
						titulo9 += "<th style=\"text-align:center;vertical-align: middle\"></th>";
						
						for(List<String> x: listaBodExternasVigentes) {
							titulo2 += "<th style=\"text-align:center; white-space: nowrap;vertical-align: middle\">"
									+ "<div style=\"display: flex;flex-direction: row;\">"
									+ "<span style=\"writing-mode: vertical-lr;transform: rotate(180deg);width: 23px;\">"
									+ x.get(1)+"</span></div></th>";
							
							titulo3 += "<th style=\"text-align:center;vertical-align: middle\">"+ x.get(2)+"</th>";
							titulo7 += "<th style=\"text-align:center;vertical-align: middle\">"+ x.get(3)+"</th>";
							titulo8 += "<th style=\"text-align:center;vertical-align: middle\">"+ x.get(4)+"</th>";
							titulo9 += "<th style=\"text-align:center;vertical-align: middle\"></th>";
							
						}
						titulo2 += "<th style=\"text-align:center; white-space: nowrap;vertical-align: middle\">"
								+ "<div style=\"display: flex;flex-direction: row;\">"
								+ "<span style=\"writing-mode: vertical-lr;transform: rotate(180deg);width: 23px;\">"
								+ "PENDIENTE X DESPACHAR</span></div></th>";
						
						titulo3 += "<th style=\"text-align:center;vertical-align: middle\"></th>";
						titulo7 += "<th style=\"text-align:center;vertical-align: middle\"></th>";
						titulo8 += "<th style=\"text-align:center;vertical-align: middle\"></th>";
						titulo9 += "<th style=\"text-align:center;vertical-align: middle\"></th>";
						
						
				String encabezado = "<tr>"+titulo1+"</tr><tr>"+titulo2+"</tr>"
						+ "<tr>"+titulo3+"</tr><tr>"+titulo7+"</tr><tr>"+titulo8+"</tr><tr>"+titulo9+"</tr>";
				
				String datosFinal = "";
					for(Equipo q: listaEquipos) {
						String datos = "";
						datos += "<tr>";
						datos += "<td>"+q.getGrupo()+"</td>";
						datos += "<td>"+q.getCodigo()+"</td>";
						datos += "<td>"+q.getNombre()+"</td>";
						Double subTotalInt = (double)0;
						for(List<String> x: listaBodInternas) {
							Double aux = mapAllStockBodInt.get(x.get(1)+"_"+q.getId());
							if(aux != null) {
								subTotalInt += aux;
								datos += "<td style='text-align:center; background-color: #ABEBC6'>"+myformatdouble0.format(aux)+"</td>";
							}else {
								datos += "<td style='text-align:center'>0</td>";
							}
						}
						datos += "<td style=\"background-color: #eeeeee; text-align:center\">"+myformatdouble0.format(subTotalInt)+"</td>";
						Double subTotalExt = (double)0;
						for(List<String> x: listaBodExternasVigentes) {
							//AGREGAR EL ID_OT
							Double aux = mapAllPorDespachar.get(x.get(0)+"_"+q.getId());
							if(aux != null) {
								subTotalExt += aux;
								//OJO SIN MUESTRA OT
								datos += "<td style='text-align:center; background-color: #ABEBC6'><a href='#' onclick='muestraOT("+x.get(0)+","+q.getId()+")'>"+myformatdouble0.format(aux)+"</a></td>";
							}else {
								datos += "<td style='text-align:center'>0</td>";
							}
						}
						datos += "<td style=\"background-color: #eeeeee; text-align:center\">"+myformatdouble0.format(subTotalExt)+"</td>";
						datos += "</tr>";
						
						Double totalTotal = subTotalInt + subTotalExt;
						if(totalTotal>0) {
							datosFinal += datos;
						}
					}
					
					
				String tablaHtml = "<table id=\"tablaPrincipal\" class=\"table table-sm table-hover table-bordered table-condensed table-fluid\" width='100%'>"
						+ "<thead style=\"background-color: #eeeeee\">"
						+ encabezado
						+ "</thead>"
						+ "<tbody>"
						+ datosFinal
						+ "</tbody>\n"
						+ "</table>";	
					
				
						
				Fechas hoy = Fechas.hoy();
    			
    			
    			con.close();
    			return ok(reportOTSaldos.render(mapeoDiccionario,mapeoPermiso,userMnu,tablaHtml, hoy.getFechaStrDDMMAA()));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    	return ok(mensajes.render("/",msgError));
	}
	
	public Result reportOtSaldosExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("cotizaImprime")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Map<String,Double> mapAllPorDespachar = new HashMap<String,Double>();
    			Map<String,Double> mapAllStockBodInt = Movimiento.mapAllStockBodInt(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
    			List<List<String>> listaBodExternasVigentes = new ArrayList<List<String>>();
    			List<List<String>> listaBodInternas = BodegaEmpresa.listaAllBodegasVigentesInternas(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
    			List<Equipo> listaEquipos = new ArrayList<Equipo>();
    			/*******************************************************************************************/
				Map<String, Double> mapAllDespachado = OtDespachado.mapAllDespachado(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
				Map<String, Double> mapAllCotizadoConOt = CotizaDetalle.mapAllCotizadoConOt(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
				/*******************************************************************************************/
				Map<String, String> mapAuxBodExternas = new HashMap<String, String>();
				Map<Long, Long> mapAuxEquipos = new HashMap<Long, Long>();
				Map<Long,List<String>> mapBodegaVigExter = BodegaEmpresa.mapAllVigentesExternas(con, s.baseDato, mapeoPermiso, s.aplicaPorSucursal, s.id_sucursal); //0=id_bodega 5=nombre
				Map<List<String>,List<String>> mapAuxBodInternas = new HashMap<List<String>,List<String>>();
				
				mapAllStockBodInt.forEach((k, v) -> {
					String[] dePaso = k.split("_");
					mapAuxEquipos.put(Long.parseLong(dePaso[1]), Long.parseLong(dePaso[1]));
				});
				
				mapAllCotizadoConOt.forEach((k, v) -> {
					Double aux = mapAllDespachado.get(k);
					if(aux == null) {
						aux = (double)0;
					}
					aux = v - aux;
					if (aux != 0) {
						String[] dePaso = k.split("_");
						List<String> bodega = mapBodegaVigExter.get(Long.parseLong(dePaso[0]));
						if(bodega != null) {
							mapAllPorDespachar.put(k, aux);
							List<String> aux1 = new ArrayList<String>();
							aux1.add(bodega.get(1)); // 0 id_bodegaEmpresa
							aux1.add(bodega.get(5)); // 1 nombre bodega;
							
							aux1.add(bodega.get(7)); 	// 2 nickCliente;
							aux1.add(bodega.get(16)); 	// 3 nombre sucursal;
							aux1.add(bodega.get(11)); 	// 4 nombre comercial;
							
							mapAuxBodInternas.put(aux1, aux1);
							mapAuxEquipos.put(Long.parseLong(dePaso[1]), Long.parseLong(dePaso[1]));
						}
						mapAuxBodExternas.put(dePaso[0], dePaso[0]);
					}
				});
				mapAuxBodInternas.forEach((k,v)->{
					listaBodExternasVigentes.add(k);
				});
				Map<Long, Equipo> mapEquipo = Equipo.mapAllVigentes(con, s.baseDato);
				mapAuxEquipos.forEach((k, v) -> {
					Equipo aux = mapEquipo.get(k);
					if (aux != null) {
						listaEquipos.add(aux);
					}
				});
				/*******************************************************************************************/
				
				File file = CotizacionEnExcel.reportOtSaldosExcel(s.baseDato, mapeoDiccionario, mapAllPorDespachar, mapAllStockBodInt, listaBodExternasVigentes, listaBodInternas, listaEquipos);
				
				
				con.close();
				return ok(file, Optional.of("reportOtSaldosPorDespachar.xlsx"));
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    	return ok(mensajes.render("/",msgError));
	}
	
	public Result listadoOTAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	Long id_bodegaOrigen = Long.parseLong(form.get("id_bodegaOrigen").trim());
	    	  	Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
				try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			List<List<String>> listadoDeOT = OtDespachado.listadoDeOT(con, s.baseDato, id_bodegaOrigen, id_equipo);
	    			String vistaHTML = 
		    				"<table id='tablaListadoEquipos' class='table table-sm table-hover table-bordered table-condensed table-fluid'>"+
		    					"<thead style='background-color: #eeeeee'>"+
			    					"<tr>"+
			    						"<th>Nro COTI</th>"+
			    						"<th>Nro " + mapeoDiccionario.get("OT") + "</th>"+
			    						"<th>Bodega Externa</th>"+
			    						"<th>Codigo</th>"+
			    						"<th>Equipo</th>"+
			    					"</tr>"+
			    				"</thead>"+
			    				"<tbody>";
		    			for(int i=0; i<listadoDeOT.size(); i++){
		    				vistaHTML += 
		    					"<tr>"+
			    						"<td style='text-align:center;'>"+listadoDeOT.get(i).get(0)+"</td>"+
			    						"<td style='text-align:center;'>"+listadoDeOT.get(i).get(1)+"</td>"+
			    						"<td style='text-align:left;'>"+listadoDeOT.get(i).get(2)+"</td>"+ 
			    						"<td style='text-align:left;'>"+listadoDeOT.get(i).get(3)+"</td>"+ 
			    						"<td style='text-align:left;'>"+listadoDeOT.get(i).get(4)+"</td>"+ 
		    					"</tr>";
		    			}
		    			vistaHTML +=
		    					"</tbody>"+
		    				"</table>";
		    		con.close();
		    		return ok(vistaHTML);
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
				return ok("error");
	       	}
    	}else {
    		return ok("error");
    	}
	}
	
	public Result reportOtSaldosPorOt(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("cotizaImprime")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Map<String,Double> mapAllPorDespachar = new HashMap<String,Double>();
    			Map<String,Double> mapAllStockBodInt = Movimiento.mapAllStockBodInt(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
    			List<List<String>> listaBodExternasVigentes = new ArrayList<List<String>>();
    			List<List<String>> listaBodInternas = BodegaEmpresa.listaAllBodegasVigentesInternas(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
    			List<Equipo> listaEquipos = new ArrayList<Equipo>();
				/*******************************************************************************************/
				Map<String, Double> mapAllDespachado = OtDespachado.mapAllDespachadoPorOt(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
				Map<String, Double> mapAllCotizadoConOt = CotizaDetalle.mapAllCotizadoConOtPorOt(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
				/*******************************************************************************************/
				Map<Long, Long> mapAuxEquipos = new HashMap<Long, Long>();
				Map<Long,List<String>> mapBodegaVigExter = BodegaEmpresa.mapAllVigentesExternas(con, s.baseDato, mapeoPermiso, s.aplicaPorSucursal, s.id_sucursal); //0=id_bodega 5=nombre
				Map<List<String>,List<String>> mapAuxBodExternas = new HashMap<List<String>,List<String>>();
				
				mapAllStockBodInt.forEach((k, v) -> {
					String[] dePaso = k.split("_");
					mapAuxEquipos.put(Long.parseLong(dePaso[1]), Long.parseLong(dePaso[1]));
				});
				
				Map<Long,Ot> mapOt = Ot.mapAll(con, s.baseDato);
				Map<Long,Cotizacion> mapCoti = Cotizacion.mapAll(con, s.baseDato);
				
				mapAllCotizadoConOt.forEach((k, v) -> {
					Double aux = mapAllDespachado.get(k);
					if(aux == null) {
						aux = (double)0;
					}
					aux = v - aux;
					
					if (aux != 0) {
						String[] dePaso = k.split("_");
						List<String> bodega = mapBodegaVigExter.get(Long.parseLong(dePaso[0]));
						if(bodega != null) {
							String fechaEntrega = "";
							String idCoti = "";
							String nroCoti = "";
							String nroOt = "";
							Ot ot = mapOt.get(Long.parseLong(dePaso[2]));
							if(ot!=null) {
								fechaEntrega = Fechas.DDMMAA(ot.getFecha());
								idCoti = ot.getId_cotizacion().toString();
								nroOt = ot.getNumero().toString();
								Cotizacion coti = mapCoti.get(Long.parseLong(idCoti));
								if(coti!=null) {
									nroCoti = coti.getNumero().toString();
								}
							}
							mapAllPorDespachar.put(k, aux);
							List<String> aux1 = new ArrayList<String>();
							aux1.add(bodega.get(1)); 	// 0 id_bodegaEmpresa
							aux1.add(bodega.get(5)); 	// 1 nombre bodega;
							
							aux1.add(bodega.get(7)); 	// 2 nickCliente;
							
							aux1.add(idCoti); 			// 3 id_cotizacion;
							aux1.add(dePaso[2]); 		// 4 id_ot;
							aux1.add(fechaEntrega); 	// 5 fechaEnvio equiv a fechaEntrega;
							aux1.add(bodega.get(16)); 	// 6 nombre sucursal;
							aux1.add(bodega.get(11)); 	// 7 nombre comercial;
							
							aux1.add(nroCoti); 	// 8 nro coti;
							aux1.add(nroOt); 	// 9 nro ot;
							
							
							
							mapAuxBodExternas.put(aux1, aux1);
							mapAuxEquipos.put(Long.parseLong(dePaso[1]), Long.parseLong(dePaso[1]));
						}
					}
				});
				
				
				mapAuxBodExternas.forEach((k,v)->{
					listaBodExternasVigentes.add(k);
				});
				
				
				
				Collections.sort(listaBodExternasVigentes, new Comparator<List<String>>() {
		            public int compare(List<String> o1, List<String> o2) {
		                int compareByIdOt = o1.get(1).compareTo(o2.get(1));
		                if (compareByIdOt == 0) {
		                    return Integer.compare(Integer.parseInt(o1.get(4)), Integer.parseInt(o2.get(4)));
		                }
		                return compareByIdOt;
		            }
		        });
				
				
				Map<Long, Equipo> mapEquipo = Equipo.mapAllVigentes(con, s.baseDato);
				mapAuxEquipos.forEach((k, v) -> {
					Equipo aux = mapEquipo.get(k);
					if (aux != null) {
						listaEquipos.add(aux);
					}
				});
				/*******************************************************************************************/
    			
    			
				String titulo1 = "<th>Grupo</th><th>Codigo</th><th style='min-width:200px'>Equipo</th><th colspan='"+(listaBodInternas.size()+1)+"'>INTERNAS</th><th colspan='"+(listaBodExternasVigentes.size()+1)+"'>CLIENTES</th>";
				String titulo2 = "<th></th><th></th><th></th>";
				
				String titulo3 = "<th></th><th></th><th>Cliente</th>";
				String titulo4 = "<th></th><th></th><th>Nro_Coti</th>";
				String titulo5 = "<th></th><th></th><th>Nro_Ot</th>";
				String titulo6 = "<th></th><th></th><th>Fecha Entrega</th>";
				String titulo7 = "<th></th><th></th><th>Sucursal</th>";
				String titulo8 = "<th></th><th></th><th>Comercial</th>";
				String titulo9 = "<th>Grupo</th><th>Codigo</th><th>Equipo</th>";
				
				// AGREGAR TIT3 TIT4 ETC Y SUMAR A FOR
						for(List<String> x: listaBodInternas) {
							titulo2 += "<th style=\"text-align:center; white-space: nowrap;vertical-align: middle\">"
									+ "<div style=\"display: flex;flex-direction: row;\">"
									+ "<span style=\"writing-mode: vertical-lr;transform: rotate(180deg);width: 23px;\">"
									+ x.get(5)+"</span></div></th>";
							
							titulo3 += "<th style=\"text-align:center;vertical-align: middle\"></th>";
							titulo4 += "<th style=\"text-align:center;vertical-align: middle\"></th>";
							titulo5 += "<th style=\"text-align:center;vertical-align: middle\"></th>";
							titulo6 += "<th style=\"text-align:center;vertical-align: middle\"></th>";
							titulo7 += "<th style=\"text-align:center;vertical-align: middle\">"+ x.get(16)+"</th>";
							titulo8 += "<th style=\"text-align:center;vertical-align: middle\">"+ x.get(11)+"</th>";
							titulo9 += "<th style=\"text-align:center;vertical-align: middle\"></th>";
						}
						titulo2 += "<th style=\"text-align:center; white-space: nowrap; vertical-align: middle\">"
								+ "<div style=\"display: flex;flex-direction: row;\">"
								+ "<span style=\"writing-mode: vertical-lr;transform: rotate(180deg);width: 23px;\">"
								+ "TOTAL STOCK</span></div></th>";
						
						titulo3 += "<th style=\"text-align:center;vertical-align: middle\"></th>";
						titulo4 += "<th style=\"text-align:center;vertical-align: middle\"></th>";
						titulo5 += "<th style=\"text-align:center;vertical-align: middle\"></th>";
						titulo6 += "<th style=\"text-align:center;vertical-align: middle\"></th>";
						titulo7 += "<th style=\"text-align:center;vertical-align: middle\"></th>";
						titulo8 += "<th style=\"text-align:center;vertical-align: middle\"></th>";
						titulo9 += "<th style=\"text-align:center;vertical-align: middle\"></th>";
						
						
						for(List<String> x: listaBodExternasVigentes) {
							titulo2 += "<th style=\"text-align:center; white-space: nowrap;vertical-align: middle\">"
									+ "<div style=\"display: flex;flex-direction: row;\">"
									+ "<span style=\"writing-mode: vertical-lr;transform: rotate(180deg);width: 23px;\">"
									+ x.get(1)+"</span></div></th>";
							
							titulo3 += "<th style=\"text-align:center;vertical-align: middle\">"+ x.get(2)+"</th>";
							titulo4 += "<th style=\"text-align:center;vertical-align: middle\">"+ x.get(8)+"</th>";
							titulo5 += "<th style=\"text-align:center;vertical-align: middle\">"+ x.get(9)+"</th>";
							titulo6 += "<th style=\"text-align:center;vertical-align: middle; min-width:80px;\">"+ x.get(5)+"</th>";
							titulo7 += "<th style=\"text-align:center;vertical-align: middle\">"+ x.get(6)+"</th>";
							titulo8 += "<th style=\"text-align:center;vertical-align: middle\">"+ x.get(7)+"</th>";
							titulo9 += "<th style=\"text-align:center;vertical-align: middle\"></th>";
							
						}
						titulo2 += "<th style=\"text-align:center; white-space: nowrap;vertical-align: middle\">"
								+ "<div style=\"display: flex;flex-direction: row;\">"
								+ "<span style=\"writing-mode: vertical-lr;transform: rotate(180deg);width: 23px;\">"
								+ "PENDIENTE X DESPACHAR</span></div></th>";
						
						titulo3 += "<th style=\"text-align:center;vertical-align: middle\"></th>";
						titulo4 += "<th style=\"text-align:center;vertical-align: middle\"></th>";
						titulo5 += "<th style=\"text-align:center;vertical-align: middle\"></th>";
						titulo6 += "<th style=\"text-align:center;vertical-align: middle\"></th>";
						titulo7 += "<th style=\"text-align:center;vertical-align: middle\"></th>";
						titulo8 += "<th style=\"text-align:center;vertical-align: middle\"></th>";
						titulo9 += "<th style=\"text-align:center;vertical-align: middle\"></th>";
						
				String encabezado = "<tr>"+titulo1+"</tr><tr>"+titulo2+"</tr>"
						+ "<tr>"+titulo3+"</tr><tr>"+titulo4+"</tr><tr>"+titulo5+"</tr><tr>"+titulo6+"</tr><tr>"+titulo7+"</tr><tr>"+titulo8+"</tr><tr>"+titulo9+"</tr>";
				
				
				String datosFinal = "";
					for(Equipo q: listaEquipos) {
						String datos = "";
						datos += "<tr>";
						datos += "<td>"+q.getGrupo()+"</td>";
						datos += "<td>"+q.getCodigo()+"</td>";
						datos += "<td>"+q.getNombre()+"</td>";
						Double subTotalInt = (double)0;
						for(List<String> x: listaBodInternas) {
							Double aux = mapAllStockBodInt.get(x.get(1)+"_"+q.getId());
							if(aux != null) {
								subTotalInt += aux;
								datos += "<td style='text-align:center; background-color: #ABEBC6'>"+myformatdouble0.format(aux)+"</td>";
							}else {
								datos += "<td style='text-align:center'>0</td>";
							}
						}
						datos += "<td style=\"background-color: #eeeeee; text-align:center\">"+myformatdouble0.format(subTotalInt)+"</td>";
						Double subTotalExt = (double)0;
						for(List<String> x: listaBodExternasVigentes) {
							Double aux = mapAllPorDespachar.get(x.get(0)+"_"+q.getId()+"_"+x.get(4));
							if(aux != null) {
								subTotalExt += aux;
								datos += "<td style='text-align:center; background-color: #ABEBC6'>"+myformatdouble0.format(aux)+"</td>";
							}else {
								datos += "<td style='text-align:center'>0</td>";
							}
						}
						datos += "<td style=\"background-color: #eeeeee; text-align:center\">"+myformatdouble0.format(subTotalExt)+"</td>";
						datos += "</tr>";
						
						Double totalTotal = subTotalInt + subTotalExt;
						if(totalTotal>0) {
							datosFinal += datos;
						}
					}
					
					
				String tablaHtml = "<table id=\"tablaPrincipal\" class=\"table table-sm table-hover table-bordered table-condensed table-fluid\" width='100%'>"
						+ "<thead style=\"background-color: #eeeeee\">"
						+ encabezado
						+ "</thead>"
						+ "<tbody>"
						+ datosFinal
						+ "</tbody>\n"
						+ "</table>";	
					
				
						
				Fechas hoy = Fechas.hoy();
    			
    			
    			con.close();
    			return ok(reportOTSaldosPorOt.render(mapeoDiccionario,mapeoPermiso,userMnu,tablaHtml, hoy.getFechaStrDDMMAA()));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    	return ok(mensajes.render("/",msgError));
	}
	
	public Result reportOtSaldosPorOtExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("cotizaImprime")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Map<String,Double> mapAllPorDespachar = new HashMap<String,Double>();
    			Map<String,Double> mapAllStockBodInt = Movimiento.mapAllStockBodInt(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
    			List<List<String>> listaBodExternasVigentes = new ArrayList<List<String>>();
    			List<List<String>> listaBodInternas = BodegaEmpresa.listaAllBodegasVigentesInternas(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
    			List<Equipo> listaEquipos = new ArrayList<Equipo>();
				/*******************************************************************************************/
				Map<String, Double> mapAllDespachado = OtDespachado.mapAllDespachadoPorOt(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
				Map<String, Double> mapAllCotizadoConOt = CotizaDetalle.mapAllCotizadoConOtPorOt(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
				/*******************************************************************************************/
				Map<Long, Long> mapAuxEquipos = new HashMap<Long, Long>();
				Map<Long,List<String>> mapBodegaVigExter = BodegaEmpresa.mapAllVigentesExternas(con, s.baseDato, mapeoPermiso, s.aplicaPorSucursal, s.id_sucursal); //0=id_bodega 5=nombre
				Map<List<String>,List<String>> mapAuxBodExternas = new HashMap<List<String>,List<String>>();
				
				mapAllStockBodInt.forEach((k, v) -> {
					String[] dePaso = k.split("_");
					mapAuxEquipos.put(Long.parseLong(dePaso[1]), Long.parseLong(dePaso[1]));
				});
				
				Map<Long,Ot> mapOt = Ot.mapAll(con, s.baseDato);
				Map<Long,Cotizacion> mapCoti = Cotizacion.mapAll(con, s.baseDato);
				
				mapAllCotizadoConOt.forEach((k, v) -> {
					Double aux = mapAllDespachado.get(k);
					if(aux == null) {
						aux = (double)0;
					}
					aux = v - aux;
					
					if (aux != 0) {
						String[] dePaso = k.split("_");
						List<String> bodega = mapBodegaVigExter.get(Long.parseLong(dePaso[0]));
						if(bodega != null) {
							String fechaEntrega = "";
							String idCoti = "";
							String nroCoti = "";
							String nroOt = "";
							Ot ot = mapOt.get(Long.parseLong(dePaso[2]));
							if(ot!=null) {
								fechaEntrega = Fechas.DDMMAA(ot.getFecha());
								idCoti = ot.getId_cotizacion().toString();
								nroOt = ot.getNumero().toString();
								Cotizacion coti = mapCoti.get(Long.parseLong(idCoti));
								if(coti!=null) {
									nroCoti = coti.getNumero().toString();
								}
							}
							mapAllPorDespachar.put(k, aux);
							List<String> aux1 = new ArrayList<String>();
							aux1.add(bodega.get(1)); 	// 0 id_bodegaEmpresa
							aux1.add(bodega.get(5)); 	// 1 nombre bodega;
							
							aux1.add(bodega.get(7)); 	// 2 nickCliente;
							
							aux1.add(idCoti); 			// 3 id_cotizacion;
							aux1.add(dePaso[2]); 		// 4 id_ot;
							aux1.add(fechaEntrega); 	// 5 fechaEnvio equiv a fechaEntrega;
							aux1.add(bodega.get(16)); 	// 6 nombre sucursal;
							aux1.add(bodega.get(11)); 	// 7 nombre comercial;
							
							aux1.add(nroCoti); 	// 8 nro coti;
							aux1.add(nroOt); 	// 9 nro ot;
							
							mapAuxBodExternas.put(aux1, aux1);
							mapAuxEquipos.put(Long.parseLong(dePaso[1]), Long.parseLong(dePaso[1]));
						}
					}
				});
				
				
				mapAuxBodExternas.forEach((k,v)->{
					listaBodExternasVigentes.add(k);
				});
				
				Collections.sort(listaBodExternasVigentes, new Comparator<List<String>>() {
		            public int compare(List<String> o1, List<String> o2) {
		                int compareByIdOt = o1.get(1).compareTo(o2.get(1));
		                if (compareByIdOt == 0) {
		                    return Integer.compare(Integer.parseInt(o1.get(4)), Integer.parseInt(o2.get(4)));
		                }
		                return compareByIdOt;
		            }
		        });
				
				Map<Long, Equipo> mapEquipo = Equipo.mapAllVigentes(con, s.baseDato);
				mapAuxEquipos.forEach((k, v) -> {
					Equipo aux = mapEquipo.get(k);
					if (aux != null) {
						listaEquipos.add(aux);
					}
				});
				/*******************************************************************************************/
				
				File file = CotizacionEnExcel.reportOtSaldosPorOtExcel(s.baseDato, mapeoDiccionario, mapAllPorDespachar, mapAllStockBodInt, listaBodExternasVigentes, listaBodInternas, listaEquipos);
				
				
				con.close();
				return ok(file, Optional.of("reportOtSaldosPorDespachar.xlsx"));
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    	return ok(mensajes.render("/",msgError));
	}
	
	public Result otAdminPrecios0(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("cotizaIngreso")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
    			List<List<String>> listBod = BodegaEmpresa.listaAllBodegasVigentesExternasFiltradas(con, s.baseDato, permisoPorBodega, s.aplicaPorSucursal, s.id_sucursal);
    			Map<Long,Long> mapAllIdBodConOt = Cotizacion.mapAllIdBodConOt(con, s.baseDato);
    			List<List<String>> datos = new ArrayList<List<String>>();
    			listBod.forEach(x ->{
    				Long aux = Long.parseLong(x.get(1));
    				Long id_bodega = mapAllIdBodConOt.get(aux);
    				if(id_bodega != null) {
    					datos.add(x);
    				}
    			});
    			
    			con.close();
    			return ok(otAdminPrecios0.render(mapeoDiccionario,mapeoPermiso,userMnu,datos));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
	
	public Result otAdminPrecios1(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_bodega = Long.parseLong(form.get("id_bodega").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("otRevisa")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			List<Long> listOt = Ot.listOtPorBodega(con, s.baseDato, id_bodega);
	    			List<String> listTablas = new ArrayList<String>();
	    			listOt.forEach(id_ot -> {
	    				String tabla = Ot.vistaCambiarPreciosOt(con, s.baseDato, id_ot, mapeoDiccionario);
	    				listTablas.add(tabla);
	    			});
	    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodega);
	    			con.close();
	    			return ok(otAdminPrecios1.render(mapeoDiccionario,mapeoPermiso,userMnu, bodega, listTablas));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
	
	public Result reportOtPrecios0(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("otRevisa")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
    			List<List<String>> listBod = BodegaEmpresa.listaAllBodegasVigentesExternasFiltradas(con, s.baseDato, permisoPorBodega, s.aplicaPorSucursal, s.id_sucursal);
    			Map<Long,Long> mapAllIdBodConOt = Cotizacion.mapAllIdBodConOt(con, s.baseDato);
    			List<List<String>> datos = new ArrayList<List<String>>();
    			listBod.forEach(x ->{
    				Long aux = Long.parseLong(x.get(1));
    				Long id_bodega = mapAllIdBodConOt.get(aux);
    				if(id_bodega != null) {
    					datos.add(x);
    				}
    			});
    			
    			con.close();
    			return ok(reportOtPrecios0.render(mapeoDiccionario,mapeoPermiso,userMnu,datos));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
	
	public Result reportOtPrecios1(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_bodega = Long.parseLong(form.get("id_bodega").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("otRevisa")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			List<Long> listOt = Ot.listOtPorBodega(con, s.baseDato, id_bodega);
	    			List<String> listTablas = new ArrayList<String>();
	    			listOt.forEach(id_ot -> {
	    				String tabla = Ot.vistaReportPreciosOt(con, s.baseDato, id_ot, mapeoDiccionario);
	    				listTablas.add(tabla);
	    			});
	    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodega);
	    			con.close();
	    			return ok(reportOtPrecios1.render(mapeoDiccionario,mapeoPermiso,userMnu, bodega, listTablas));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    //==============================================================
    // MNU otConfirma   Cotizar/Ordenes de trabajo/Confirma OT u OS
    //==============================================================
    
    public Result otListaConfirma(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("otConfirma")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			
    			List<List<String>> listOt = Ot.listOtConfirma(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
    			con.close();
    			return ok(otListaConfirma.render(mapeoDiccionario,mapeoPermiso,userMnu,listOt));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result otConfirma(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String listadoIdOtConfirmar = form.get("cambiosDeEstados").trim();
    			try {
	    			Connection con = db.getConnection();
	    			if(listadoIdOtConfirmar.length()>0) {
	    				listadoIdOtConfirmar = "("+listadoIdOtConfirmar.substring(0,listadoIdOtConfirmar.length()-1)+")";
	    				Ot.confirmar(con, s.baseDato, listadoIdOtConfirmar);
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "ot", (long)0, "confirma", "confirma ot id: "+listadoIdOtConfirmar);
	    			}
	    			con.close();
	    			return redirect("/home/");
    			} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
    			return ok(mensajes.render("/",msgError));
    		}
		}else {
			return ok(mensajes.render("/",msgError));
		}
	}
    
    //======================================================================
    // MNU otDespacha   Cotizar/Despachos/Despachar
    //======================================================================
    
    public Result otListaDespacharPeriodo(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("otDespacha")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Fechas hoy = Fechas.hoy();
    			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
    			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			con.close();
    			return ok(otListaDespacharPeriodo.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result otListaDespachar(Http.Request request) {
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
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("otDespacha")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			
	    			List<List<String>> listOt = Ot.listOtDespachar(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal, desdeAAMMDD, hastaAAMMDD);
	    			con.close();
	    			return ok(otListaDespachar.render(mapeoDiccionario,mapeoPermiso,userMnu,listOt));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result otDespachar(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_ot = Long.parseLong(form.get("id_ot").trim());
	       		try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			Ot ot = Ot.find(con, s.baseDato, id_ot);
	    			Cotizacion coti = Cotizacion.find(con, s.baseDato, ot.getId_cotizacion());
	    			String cabezeraOt = Ot.vistaCabezeraOt(con, s.baseDato, ot, coti, mapeoDiccionario);
	    			BodegaEmpresa bodegaDestino = BodegaEmpresa.findXIdBodega(con, s.baseDato, coti.getId_bodegaEmpresa());
	    			List<List<String>> listBodegasOrigen =  new ArrayList<List<String>>();
	    			List<List<String>> listBodegas = BodegaEmpresa.listaAllBodegasVigentesInternas(con, s.baseDato, s.aplicaPorSucursal, coti.getId_sucursal().toString());
	    			listBodegas.forEach(l->{
	    				if(!l.get(1).equals(coti.getId_bodegaEmpresa().toString())) {
	    					listBodegasOrigen.add(l);
	    				}
	    			});
	    			
	    			Long nuevoNumeroGuia = Guia.findNuevoNumero(con, s.baseDato);
	    			Fechas hoy = Fechas.hoy();
	    			
	    			Map<Long,Double> mapDespachado = OtDespachado.mapSumaDespachadoPorIdOtCantEquiv(con, s.baseDato, ot.getId());
	    			List<CotizaDetalle> detalleOrigen = CotizaDetalle.allPorIdCotizacion(con, s.baseDato, ot.getId_cotizacion());
	    			List<List<String>> detOrigen = new ArrayList<List<String>>();
	    			for(int i=0;i<detalleOrigen.size();i++){
	    				
	    				Double cant = Double.parseDouble(detalleOrigen.get(i).getCantidad().replaceAll(",", ""));
	    				Double saldoPorDespachar = cant;
	    				Double desp = mapDespachado.get(detalleOrigen.get(i).getId_equipo());
	    				
	    				if(desp != null) {
	    					saldoPorDespachar = cant - desp;
	    					if((double)saldoPorDespachar < (double)0) {
	    						saldoPorDespachar = (double)0;
	    					}
	    				}
	    				
	    				String concepto = mapeoDiccionario.get("Arriendo");
	    				if((long)detalleOrigen.get(i).getEsVenta() == (long)1) {
	    					concepto = "Venta";
	    				}
	    				
	    				List<String> aux = new ArrayList<String>();
	    				aux.add(detalleOrigen.get(i).getId_equipo().toString()); 	// 0 id_equipo origen
	    				aux.add(detalleOrigen.get(i).getEsVenta().toString()); 		// 1 esVenta = 1
	    				aux.add(detalleOrigen.get(i).getCodigo()); 					// 2 codigo equipo origen
	    				aux.add(detalleOrigen.get(i).getEquipo()); 					// 3 nombre equipo origen
	    				aux.add(detalleOrigen.get(i).getUnidad()); 					// 4 unidad equipo origen
	    				aux.add(detalleOrigen.get(i).getCantidad()); 				// 5 cantidad equipo origen
	    				aux.add(concepto); 											// 6 tipo arr o venta
	    				aux.add(myformatdouble2.format(saldoPorDespachar)); 		// 7 saldo por despachar
	    				aux.add(detalleOrigen.get(i).getId().toString());			// 8 id_cotizaDetalle
	    				aux.add(detalleOrigen.get(i).getGrupo());					// 9 grupo
	    				detOrigen.add(aux);
	    				
	    			}
	    			
	    			String vistaDetOrigen = 
		    			"<table id='detalle' class='table table-sm table-bordered table-condensed table-fluid'>"+
		    	        "<thead style='background-color: #eeeeee'>"+
		    				"<TR>"+
		    				        "<TH width='10%' rowspan='2' style='text-align:center;'>GRUPO</TH>"+
		    				        "<TH width='10%' rowspan='2' style='text-align:center;'>COD</TH>"+
		    						"<TH width='20%' rowspan='2' style='text-align:center;'>EQUIPO</TH>"+
		    						"<TH colspan='4' style='text-align:center;'>ORDEN ORIGINAL</TH>"+
		    						"<TH colspan='10' style='text-align:center;'>POR DESPACHAR</TH>"+
		    				"</TR>"+
		    				"<TR> "+
		    					"<TH width='3%' style= 'text-align: center;'>UN</TH>"+
		    					"<TH width='4%' style= 'text-align: center;'>CANT</TH>"+
		    					"<TH width='4%' style= 'text-align: center;'>TIPO</TH>"+
		    					"<th width='4%' style='text-align:center; font-size:10px;'>SALDO</th>"+
		    					"<TH width='9%' style= 'text-align: center;'>COD</TH>"+
		    					"<TH width='17%' style= 'text-align: center;'>EQUIPO</TH>"+
		    					"<TH width='3%' style= 'text-align: center;'>UN</TH>"+
		    					"<TH width='5%' style= 'text-align: center;'>STOCK</TH>"+
		    					"<TH width='5%' style= 'text-align: center;'>CANT</TH>"+
		    					"<TH width='5%' style='text-align: center; font-size:10px;'>EQUIV</TH>"+
		    					"<TH width='4%' style= 'text-align: center;'>KG</TH>"+
		    					"<TH width='4%' style= 'text-align: center;'>M2</TH>"+
		    					"<th style='display:none'>id_equipo</th>"+
		    					"<th style='display:none'>esVenta</th>"+
		    					"<th style='display:none'>id_cotizaDetalle</th>"+
		    				"</TR>"+
		    			"</thead>"+
		    			"<tbody>";
	    				for(int i=0; i<detOrigen.size(); i++){
	    					vistaDetOrigen += 
	    					"<tr>"+
	    						"<td style='text-align:left'>"+detOrigen.get(i).get(9)+"</td>"+
	    						"<td style='text-align:left' class='tdCodOrigen'>"+detOrigen.get(i).get(2)+"</td>"+
	    						"<td style='text-align:left' class='tdEquipOrigen'>"+detOrigen.get(i).get(3).replaceAll("\"", "")+"</td>"+
	    						"<td style='text-align:center' class='tdUnOrigen'>"+detOrigen.get(i).get(4)+"</td>"+
	    						"<td style='text-align:right'>"+detOrigen.get(i).get(5)+"</td>"+
	    						"<td style='text-align:center'>"+detOrigen.get(i).get(6)+"</td>"+
	    						"<td style='text-align:right' class='tdSaldo'>"+detOrigen.get(i).get(7)+"</td>"+
	    						"<td colspan='9'>"+
	    							"<table id='tabla_"+i+"'class='table table-sm table-bordered table-condensed table-fluid'>"+
	    								"<tr>"+
	    									"<td width='18%' style='text-align:left'>"+
	    										"<input type='button' class='btnBuscar btn btn-info btn-sm rounded-pill btn-block' value='buscar' "
	    										+ " onclick='index="+i+";"
	    												+ " idCotizaDetalle="+detOrigen.get(i).get(8)+";"
	    												+ " esVenta="+detOrigen.get(i).get(1)+";"
	    												+ " idEquipOrigen="+detOrigen.get(i).get(0)+";"
	    												+ " muestraEquipos();'>"+
	    									"</td>"+
	    									"<td width='35%' style='text-align:left'></td>"+
	    									"<td width='5%' style='text-align:center'></td>"+
	    									"<td width='11%' style='text-align:right'></td>"+
	    									"<td width='10%' style='text-align:right'></td>"+
	    									"<td width='10%' style='text-align:right'></td>"+
	    									"<td width='4%' style='text-align:right'></td>"+
	    									"<td width='4%' style='text-align:right'></td>"+
	    								"</tr>"+
	    							"</table>"+
	    						"</td>"+
	    						"<td style='display:none' class='tdIdEquipOrigen'>"+detOrigen.get(i).get(0)+"</td>"+
	    						"<td style='display:none' class='tdEsVentaOrigen'>"+detOrigen.get(i).get(1)+"</td>"+
	    						"<td style='display:none' class='tdIdCotizaDetalle'>"+detOrigen.get(i).get(8)+"</td>"+
	    					"</tr>";
	    				}
	    			vistaDetOrigen += "</tbody>"
	    					+ "<tfoot>"+
		    					"<TR> "+
		    						"<TH></TH>"+
			    					"<TH style='text-align:left;'>TOTALES</TH>"+
			    					"<TH></TH>"+
			    					"<TH></TH>"+
			    					"<TH></TH>"+
			    					"<TH></TH>"+
			    					"<th></th>"+
			    					"<TH></TH>"+
			    					"<TH></TH>"+
			    					"<TH></TH>"+
			    					"<TH></TH>"+
			    					"<TH style='text-align:right;'><div id='totalCant'>0.00</div></TH>"+
			    					"<TH></TH>"+
			    					"<TH style='text-align:right;'><div id='totalKG'>0.00</div></TH>"+
			    					"<TH style='text-align:right;'><div id='totalM2'>0.00</div></TH>"+
			    					"<th style='display:none'></th>"+
			    					"<th style='display:none'></th>"+
			    					"<th style='display:none'></th>"+
			    				"</TR>"+
			    				"</tfoot>"+
	    					"</table>";
	    			
	    			List<Transportista> listaTransporte = Transportista.listaTransportista(con, s.baseDato);
	    			
	    			con.close();
	    			return ok(otDespachar.render(mapeoDiccionario,mapeoPermiso,userMnu, id_ot, cabezeraOt, bodegaDestino, listBodegasOrigen, 
	    					nuevoNumeroGuia, hoy.getFechaStrAAMMDD(), vistaDetOrigen, coti.getId(), listaTransporte, coti.getId_comercial()));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		return ok(mensajes.render("/",msgError));
    		}
		}else {
			return ok(mensajes.render("/",msgError));
		}
	}
    
   
    public Result tblListEquipoConStockAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	Long id_bodegaOrigen = Long.parseLong(form.get("id_bodegaOrigen").trim());
				try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<Long,Grupo> mapGrupo = Grupo.mapAll(con, s.baseDato);
	    			Map<Long,Equipo> mapEquipo = Equipo.mapAllVigentes(con, s.baseDato);
	    			Map<Long,Cotizacion> mapCotizacion = Cotizacion.mapAll(con, s.baseDato);
	    			Map<Long,Double> mapPeso = Atributo.mapAtributoPESO(con, s.baseDato);
	    			Map<Long,Double> mapM2 = Atributo.mapAtributoM2(con, s.baseDato);
	    			List<List<String>> listaEquipos = OtDespachado.listEquipoConStock(con, s.baseDato, mapeoPermiso, id_bodegaOrigen, mapGrupo, mapEquipo, mapCotizacion, mapPeso, mapM2);
	    			con.close();
	    			if(listaEquipos.size()>0) {
	    				return ok(Json.toJson(listaEquipos).toString()).as("application/json");
	    			}else {
	    				return ok("vacia");
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
    
    public Result grabaDespacho(Http.Request request) {
    	Sessiones s = new Sessiones(request);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		FormDespacho form = formFactory.form(FormDespacho.class).withDirectFieldAccess(true).bindFromRequest(request).get();
    		if (form.id_bodegaDestino==null || form.id_ot==null) {
    			return ok(mensajes.render("/",msgErrorFormulario));
    		}else {
    			Archivos archivos = formFactory.form(Archivos.class).withDirectFieldAccess(true).bindFromRequest(request).get();
    			try {
	    			Connection con = db.getConnection();
	    			
	    			if(Guia.existeNumero(con, s.baseDato, form.numeroGuia)) {
	    				Long nuevoNumero = Guia.findNuevoNumero(con, s.baseDato);
	    				form.numeroGuia = nuevoNumero;
	    			}
	    			
	    			String nombreDocAdjunto = "0";
	    			String nombreArchivoSinExtencion = "";
	    			if (archivos != null) {
	    				if(archivos.docAdjunto != null) {
	    					nombreArchivoSinExtencion = "Doc_Mov_" + form.numeroGuia;
	    					if(archivos.docAdjunto.size() == 1) {
	    						String fileName = archivos.docAdjunto.get(0).getFilename();
	    			         	fileName = fileName.substring(fileName.indexOf("."));
	    						while(fileName.substring(1).indexOf(".")>0) {
	    							fileName=fileName.substring(1);
	    							fileName=fileName.substring(fileName.indexOf("."));
	    						}
	    						String ext = fileName;
	    						nombreDocAdjunto = nombreArchivoSinExtencion + ext;
	    					}else {
	    						nombreDocAdjunto = nombreArchivoSinExtencion + ".zip";
	    					}
	    				}
	    			}
	    			form.docAnexo = nombreDocAdjunto;
	    			form.fotos = "0";
	    			
	    			if(FormDespacho.create(con, s.baseDato, form, mapeoDiccionario, s.id_usuario, "0")) {
	    				FormMovimiento formMov = new FormMovimiento();
	    				formMov.numeroGuia = form.numeroGuia;
	    				formMov.numGuiaCliente = form.numGuiaCliente;
	    				formMov.fechaGuia = form.fechaGuia;
	    				formMov.observaciones = form.observaciones;
	    				formMov.id_transportista = form.id_transportista;
	    				
	    				if (archivos != null) {
	    					MnuCotizar.grabarFilesThread grabarFile = new MnuCotizar.grabarFilesThread(s.baseDato, archivos, nombreArchivoSinExtencion);
		    				grabarFile.run();
	    				}
	    				
	    				
	    				String fileNamePdf = FormMovimiento.generaGuiaPDF(con, s.baseDato, formMov, mapeoDiccionario, mapeoPermiso, Long.parseLong(s.id_usuario));
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "otDespacho", (long)0, "create", "crea nuevo despacho movimiento nro "+form.numeroGuia);
	    				con.close();
	    				String titulo = "DESPACHO ENTRE "+mapeoDiccionario.get("BODEGA")+" O PROYECTOS";
	    				//String url = "%2FotListaDespachoModificar%2F0";
	    				String url = "%2Fhome%2F";
	    				return redirect("/routes2/redirShowPDF/"+fileNamePdf+","+titulo+","+url);
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
    
    
    
    //======================================================================
    // MNU otModificarDespacho   Cotizar/Despachos/Modificar Despacho
    //======================================================================
	
    public Result otListaDespachoModificarPeriodo(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("otModificarDespacho")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			
    			Fechas hoy = Fechas.hoy();
    			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
    			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			con.close();
    			return ok(otListaDespachoModificarPeriodo.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
	public Result otListaDespachoModificar(Http.Request request) {
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
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("otModificarDespacho")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			
	    			
	    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
	    			permisoPorBodega = permisoPorBodega.replaceAll("`movimiento`.`id_bodegaEmpresa`", "`guia`.`id_bodegaDestino`");
	    			
	    			List<Guia> listaGuias = Guia.allDespachosConOtDesdeHasta(con, s.baseDato, desdeAAMMDD, hastaAAMMDD, permisoPorBodega, s.aplicaPorSucursal, s.id_sucursal);
	    			List<Guia> listaGuiasSinNroNegativo = new ArrayList<Guia>();
	    			listaGuias.forEach(x->{
	    				if((long)x.getNumero()>(long)0) {
	    					listaGuiasSinNroNegativo.add(x);
	    				}
	    			});

	    			con.close();
	    			return ok(otListaDespachoModificar.render(mapeoDiccionario,mapeoPermiso,userMnu,listaGuiasSinNroNegativo));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
	
	public Result otDespachoElimina(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_guia = Long.parseLong(form.get("id_guia").trim());
	       		Long id_ot = Long.parseLong(form.get("id_ot").trim());
	       		try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			if(mapeoPermiso.get("otModificarDespacho")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			if(Movimiento.esPosibleEliminar(con, s.baseDato, id_guia)) {
	    				Guia guia = Guia.find(con, s.baseDato, id_guia);
	    				String listaIdEliminados = Movimiento.listaIdEliminados(con, s.baseDato, id_guia);
	    				if(Movimiento.delete(con, s.baseDato, id_guia)) {
	    					if(OtDespachado.eliminarDespacho(con, s.baseDato, id_guia)) {
	    						Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "otDespachado", id_guia, "delete",  "elimina despacho guia nro "+guia.numero+" de fecha "+guia.fecha+
	    								" y sus movimientos asociados id: "+listaIdEliminados);
	    						if(OtDespachado.existenDespachosAsociados(con, s.baseDato, id_ot)) {
	    							con.close();
			            			return redirect("/otListaDespachoModificarPeriodo/");
	    						} else {
	    							if(Ot.cambiar_a_NoConfirmada(con, s.baseDato, id_ot)) {
	    								Ot.eliminarListaPrecio(con, s.baseDato, id_ot);
	    								String msg = "Se elimino el último o único despacho asociado a esta órden, para volver a "
		    									+ "despachar desde la misma es necesario volver a confirmar la órden, cambio su estado de confirmada a no confirmada";
				    					con.close();
				        				return ok(mensajes.render("/otListaDespachoModificarPeriodo/",msg));
	    							}
	    						}
	    					}else {
	    						String msg = "No es posible eliminar este despacho, se presentó un problema";
		    					con.close();
		        				return ok(mensajes.render("/otListaDespachoModificarPeriodo/",msg));
	    					}
	    					
	    				}else {
	    					String msg = "No es posible eliminar este despacho, su eliminacion provocaría inventarios negativos";
	    					con.close();
	        				return ok(mensajes.render("/otListaDespachoModificarPeriodo/",msg));
	    				}
	    			}else {
	    				String msg = "No es posible eliminar este despacho, su eliminacion provocaría inventarios negativos";
	    				con.close();
	    				return ok(mensajes.render("/otListaDespachoModificarPeriodo/",msg));
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
	
	public Result otDespachoModificar(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		
	       		if(form.get("id_guia").trim() == null) {
	       			return redirect("/home/");
	       		}
	       	
	       		Long id_guia = Long.parseLong(form.get("id_guia").trim());
	       		
	       		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			
	    			Guia guia = Guia.find(con, s.baseDato, id_guia);
	    			
	    			if(guia == null || guia.getId_ot() == null) {
	    				con.close();
		       			return redirect("/home/");
		       		}

	    			Ot ot = Ot.find(con, s.baseDato, guia.getId_ot());
	    			
	    			if(ot == null || ot.getId_cotizacion() == null) {
	    				con.close();
		       			return redirect("/home/");
		       		}
	    			Cotizacion coti = Cotizacion.find(con, s.baseDato, ot.getId_cotizacion());
	    			String cabezeraOt = Ot.vistaCabezeraOt(con, s.baseDato, ot, coti, mapeoDiccionario);
	    			BodegaEmpresa bodegaDestino = BodegaEmpresa.findXIdBodega(con, s.baseDato, coti.getId_bodegaEmpresa());
	    			
	    			
	    			BodegaEmpresa bodegaOrigen = BodegaEmpresa.findXIdBodega(con, s.baseDato, guia.getId_bodegaOrigen());
	    			
	    			
	    			if(bodegaOrigen!=null && bodegaOrigen.getVigente() == (long)0) {
	    				String msg = "No es posible modificar este movimiento, origen o destino esta no vigente, o ambos no vigentes";
    					con.close();
        				return ok(mensajes.render("/otListaDespachoModificarPeriodo/",msg));
	    			}
	    			
	    			if(bodegaDestino!=null && bodegaDestino.getVigente() == (long)0) {
	    				String msg = "No es posible modificar este movimiento, origen o destino esta no vigente, o ambos no vigentes";
    					con.close();
        				return ok(mensajes.render("/otListaDespachoModificarPeriodo/",msg));
	    			}
	    			
	    			
	    			Map<Long,Double> mapDespachado = OtDespachado.mapSumaDespachadoPorIdOtCantEquiv(con, s.baseDato, ot.getId());
	    			List<CotizaDetalle> detalleOrigen = CotizaDetalle.allPorIdCotizacion(con, s.baseDato, ot.getId_cotizacion());
	    			List<List<String>> detOrigen = new ArrayList<List<String>>();
	    			
	    			for(int i=0;i<detalleOrigen.size();i++){
	    				
	    				Double cant = Double.parseDouble(detalleOrigen.get(i).getCantidad().replaceAll(",", ""));
	    				Double saldoPorDespachar = cant;
	    				Double desp = mapDespachado.get(detalleOrigen.get(i).getId_equipo());
	    				if(desp != null) {
	    					saldoPorDespachar = cant - desp;
	    					if((double)saldoPorDespachar < (double)0) {
	    						saldoPorDespachar = (double)0;
	    					}
	    				}
	    				
	    				String concepto = mapeoDiccionario.get("Arriendo");
	    				if((long)detalleOrigen.get(i).getEsVenta() == (long)1) {
	    					concepto = "Venta";
	    				}
	    				
	    				List<String> aux = new ArrayList<String>();
	    				aux.add(detalleOrigen.get(i).getId_equipo().toString()); 	// 0 id_equipo origen
	    				aux.add(detalleOrigen.get(i).getEsVenta().toString()); 		// 1 esVenta = 1
	    				aux.add(detalleOrigen.get(i).getCodigo()); 					// 2 codigo equipo origen
	    				aux.add(detalleOrigen.get(i).getEquipo()); 					// 3 nombre equipo origen
	    				aux.add(detalleOrigen.get(i).getUnidad()); 					// 4 unidad equipo origen
	    				aux.add(detalleOrigen.get(i).getCantidad()); 				// 5 cantidad equipo origen
	    				aux.add(concepto); 											// 6 tipo arr o venta
	    				aux.add(myformatdouble2.format(saldoPorDespachar)); 		// 7 saldo por despachar
	    				aux.add(detalleOrigen.get(i).getId().toString());			// 8 id_cotizaDetalle
	    				aux.add(detalleOrigen.get(i).getGrupo()); 					// 9 grupo
	    				aux.add(detalleOrigen.get(i).totalKG); //dividir por cantidad
	    				aux.add(detalleOrigen.get(i).totalM2); //dividir por cantidad
	    				aux.add(detalleOrigen.get(i).id_cotizacion.toString()); 
	    				detOrigen.add(aux);
	    				
	    			}
	    			
	    			List<OtDespachado> allPorIdGuiaIdOt = OtDespachado.allPorIdGuiaIdOt(con, s.baseDato, guia.getId_ot(), guia.getId());
	    			Map<Long,Grupo> mapGrupo = Grupo.mapAll(con, s.baseDato);
	    			Map<Long,Equipo> mapEquipo = Equipo.mapAllVigentes(con, s.baseDato);
	    			Map<Long,Cotizacion> mapCotizacion = Cotizacion.mapAll(con, s.baseDato);
	    			Map<Long,Double> mapPeso = Atributo.mapAtributoPESO(con, s.baseDato);
	    			Map<Long,Double> mapM2 = Atributo.mapAtributoM2(con, s.baseDato);
	    			List<List<String>> listEquipoConStock = OtDespachado.listEquipoConStock(con, s.baseDato, mapeoPermiso, guia.getId_bodegaOrigen(), mapGrupo, mapEquipo, mapCotizacion, mapPeso, mapM2);

	    			Map<String,Double> mapCantDespAgrupPorEquip = new HashMap<String,Double>();
	    			List<List<String>> detalleDespachado = new ArrayList<List<String>>();
	    			
	    			for(OtDespachado lista: allPorIdGuiaIdOt) {
	    				Equipo equipo = mapEquipo.get(lista.getId_equipoDespacho());
	    				if(equipo!=null) {
	    					String codEquip = "error", nomEquipo = "error", unidEquip = "error";
		    				if(equipo != null) {
		    					codEquip = equipo.getCodigo();
		    					nomEquipo = equipo.getNombre();
		    					unidEquip = equipo.getUnidad();
		    				}
		    				Double auxStock = (double) 0;
		    				for(List<String> stock: listEquipoConStock) {
		    					if(stock.get(0).equals(lista.getId_equipoDespacho().toString())) {
		    						auxStock += Double.parseDouble(stock.get(9).replaceAll(",", ""));
		    					}
		    				}
		    				Double peso = mapPeso.get(lista.getId_equipoDespacho()); 
		    				if(peso == null){
		    					peso = (double)0;
		    				}
		    				Double m2 = mapM2.get(lista.getId_equipoDespacho()); 
		    				if(m2 == null){
		    					m2 = (double)0;
		    				}
		    				Double kgTot = lista.getCantidadDespacho() * peso;
		    				Double m2Tot = lista.getCantidadDespacho() * m2;
		    				
		    				List<String> aux = new ArrayList<String>();
		    				aux.add(codEquip);													// 0 codigo equipo despachado
		    				aux.add(lista.getId_equipoDespacho().toString());					// 1 id_equipoDespachado
		    				aux.add(lista.getId_cotizaDetalle().toString());					// 2 id_cotizaDetalle
		    				aux.add(lista.esVenta.toString());									// 3 esVenta despachado
		    				aux.add(lista.getId_equipoOrigen().toString());						// 4 id_equipoOrigen
		    				aux.add(nomEquipo);													// 5 nombre del equipo despachado
		    				aux.add(unidEquip);													// 6 unidad despachado
		    				
		    				aux.add(myformatdouble2.format(auxStock));				// 7 stock
		    				aux.add(myformatdouble2.format(lista.getCantidadDespacho()));		// 8 cantidadDespacho
		    				aux.add(myformatdouble2.format(lista.getCantidadRebajaOt()));		// 9 cantidadRebajaOt
		    				aux.add(myformatdouble2.format(kgTot));								// 10 kgTot
		    				aux.add(myformatdouble2.format(m2Tot));								// 11 m2Tot
		    				aux.add(peso.toString());											// 12 kgUni
		    				aux.add(m2.toString());												// 13 m2Uni
		    				aux.add(lista.getId_cotizacion().toString());						// 14 id_cotizacion
		    				detalleDespachado.add(aux);
		    				
		    				@SuppressWarnings("unlikely-arg-type")
							Double acumCantDesp = mapCantDespAgrupPorEquip.get(lista.getId_equipoDespacho());
		    				if(acumCantDesp == null) {
		    					acumCantDesp = lista.getCantidadDespacho();
		    				} else {
		    					acumCantDesp = acumCantDesp + lista.getCantidadDespacho();
		    				}
		    				mapCantDespAgrupPorEquip.put(lista.getId_equipoDespacho().toString(), acumCantDesp);
	    				}
	    				
	    				
	    			}
	    			
	    			String vistaDetOrigen = 
		    			"<table id='detalle' class='table table-sm table-bordered table-condensed table-fluid'>"+
		    	        "<thead style='background-color: #eeeeee'>"+
		    				"<TR>"+
		    				        "<TH width='10%' rowspan='2' style='text-align:center;'>GRUPO</TH>"+
		    				        "<TH width='10%' rowspan='2' style='text-align:center;'>COD</TH>"+
		    						"<TH width='20%' rowspan='2' style='text-align:center;'>EQUIPO</TH>"+
		    						"<TH colspan='4' style='text-align:center;'>ORDEN ORIGINAL</TH>"+
		    						"<TH colspan='8' style='text-align:center;'>POR DESPACHAR</TH>"+
		    				"</TR>"+
		    				"<TR> "+
		    					"<TH width='3%' style= 'text-align: center;'>UN</TH>"+
		    					"<TH width='4%' style= 'text-align: center;'>CANT</TH>"+
		    					"<TH width='4%' style= 'text-align: center;'>TIPO</TH>"+
		    					"<th width='4%' style='text-align:center; font-size:10px;'>SALDO</th>"+
		    					
		    					"<TH width='7%' style= 'text-align: center;'>COD</TH>"+
		    					"<TH width='14%' style= 'text-align: center;'>EQUIPO</TH>"+
		    					"<TH width='2%' style= 'text-align: center;'>UN</TH>"+
		    					"<TH width='3%' style= 'text-align: center;'>STOCK</TH>"+
		    					"<TH width='3%' style= 'text-align: center;'>CANT</TH>"+
		    					"<TH width='3%' style='text-align: center; font-size:10px;'>EQUIV</TH>"+
		    					"<TH width='3%' style= 'text-align: center;'>KG</TH>"+
		    					"<TH width='3%' style= 'text-align: center;'>M2</TH>"+
		    				"</TR>"+
		    			"</thead>"+
		    			"<tbody>";
	    			int cont = 0;
	    				for(int i=0; i<detOrigen.size(); i++){
	    					vistaDetOrigen += 
	    					"<tr>"+
	    						"<td style='text-align:left'>"+detOrigen.get(i).get(9)+"</td>"+
	    						"<td style='text-align:left'>"+detOrigen.get(i).get(2)+"</td>"+
	    						"<td style='text-align:left'>"+detOrigen.get(i).get(3)+"</td>"+
	    						"<td style='text-align:center'>"+detOrigen.get(i).get(4)+"</td>"+
	    						"<td style='text-align:right'>"+detOrigen.get(i).get(5)+"</td>"+
	    						"<td style='text-align:center'>"+detOrigen.get(i).get(6)+"</td>"+
	    						"<td style='text-align:right'>"+detOrigen.get(i).get(7)+"</td>"+
	    						"<td colspan='8'>"+
	    							"<table id='tabla_"+i+"'class='table table-sm table-bordered table-condensed table-fluid'>";
	    					
	    					
	    					boolean existeDespacho = false;
	    					for(int j=0; j<detalleDespachado.size(); j++) {
	    						
	    						if(detOrigen.get(i).get(0).equals(detalleDespachado.get(j).get(4))) {
	    							
	    							existeDespacho = true;
	    							
	    							Double cantDespAgrup = mapCantDespAgrupPorEquip.get(detalleDespachado.get(j).get(1));
	    							if(cantDespAgrup == null) {
	    								cantDespAgrup = (double)0;
	    							}
	    							
	    							Double stockInicial = Double.parseDouble(detalleDespachado.get(j).get(7).replaceAll(",", ""));
	    							Double cantInicial = Double.parseDouble(detalleDespachado.get(j).get(8).replaceAll(",", ""));
	    							
	    							
	    							vistaDetOrigen +=
		    								"<tr>"+
		    									"<td style='text-align:left'>"+detalleDespachado.get(j).get(0)+"</td>"+
		    									"<td style='text-align:left'>"+
		    										"<input type='hidden' name='id_cotizaDetalle[]' value='"+detalleDespachado.get(j).get(2)+"'>"+
		    										"<input type='hidden' name='id_equipoDespacho[]' value='"+detalleDespachado.get(j).get(1)+"'>"+
		    										"<input type='hidden' name='esVenta[]' value='"+detalleDespachado.get(j).get(3)+"'>"+
		    										"<input type='hidden' name='esNuevo[]' value='0'>"+
		    										"<input type='hidden' name='id_equipoOrigen[]' value='"+detalleDespachado.get(j).get(4)+"'>"+
		    										detalleDespachado.get(j).get(5)+
		    									"</td>"+
		    									"<td style='text-align:center'>"+detalleDespachado.get(j).get(6)+"</td>"+
		    									"<td style='text-align:right'>"+
		    										"<div id='"+cont+"_cantStock'>"+detalleDespachado.get(j).get(7)+"</div>"+
		    									"</td>"+
		    									"<td style='text-align:right'>"+
			    									"<input type='text' class='cantDespacho form-control right' "+
			    									" value='"+detalleDespachado.get(j).get(8)+"' "+
			    									" id='"+cont+"_cantDespacho_"+detalleDespachado.get(j).get(1)+"_"+detalleDespachado.get(j).get(14)+"' "+ 
			    									" name='cantDespacho[]' " +
			    									" onfocus=\"value=value.replace(/,/g,'')\" "+
			    									" onkeypress='return ingresoDouble(event,value)' "+
			    									" onfocusout='value = formatStandar2(value);' "+
			    									" onchange='validaCantDespacho("+cont+",value,"+detalleDespachado.get(j).get(1)+","+detalleDespachado.get(j).get(14)+","+stockInicial+","+cantInicial+","+cantDespAgrup+"); value=formatStandar2(value);'>"+
		    									"</td>"+
		    									"<td style='text-align:right'>"+
			    									"<input type='text' class='cantEquivalente form-control right' "+
			    									" value='"+detalleDespachado.get(j).get(9)+"' "+
			    									" id='"+cont+"_cantEquivalente' "+
			    									" name='cantEquivalente[]' " +
			    									" onfocus=\"value=value.replace(/,/g,'')\" "+
			    									" onkeypress='return ingresoDouble(event,value)' "+
			    									" onfocusout='value = formatStandar2(value);' "+
			    									" onchange='value=formatStandar2(value); $(\"#seModifico\").val(1)'>"+
		    									"</td>"+
		    									"<td style='text-align:right'>"+
		    										"<div class='kg' id='"+cont+"_kgTot'>"+detalleDespachado.get(j).get(10)+"</div>"+
		    									"</td>"+
		    									"<td style='text-align:right'>"+
		    										"<div class='m2' id='"+cont+"_m2Tot'>"+detalleDespachado.get(j).get(11)+"</div>"+
		    									"</td>"+
		    									"<td style='display:none'>"+
		    										"<div id='"+cont+"_kgUn'>"+detalleDespachado.get(j).get(12)+"</div>"+
		    									"</td>"+
		    									"<td style='display:none'>"+
													"<div id='"+cont+"_m2Un'>"+detalleDespachado.get(j).get(13)+"</div>"+
												"</td>"+
		    								"</tr>";
		    									
		    								cont++;
	    						}
	    						
	    					}
	    					
	    					if(!existeDespacho) {
	    						Double auxStock  = (double)0;
	    						for(List<String> stock: listEquipoConStock) {
			    					if(stock.get(0).equals(detOrigen.get(i).get(0).toString())) {
			    						auxStock += Double.parseDouble(stock.get(9).replaceAll(",", ""));
			    					}
			    				}
	    						
	    						vistaDetOrigen +=
	    								"<tr>"+
	    									"<td style='text-align:left'>"+detOrigen.get(i).get(2)+"</td>"+
	    									"<td style='text-align:left'>"+
	    										"<input type='hidden' name='id_cotizaDetalle[]' value='"+detOrigen.get(i).get(12)+"'>"+
	    										"<input type='hidden' name='id_equipoDespacho[]' value='"+detOrigen.get(i).get(0)+"'>"+
	    										"<input type='hidden' name='esVenta[]' value='"+detOrigen.get(i).get(1)+"'>"+
	    										"<input type='hidden' name='esNuevo[]' value='0'>"+
	    										"<input type='hidden' name='id_equipoOrigen[]' value='"+detOrigen.get(i).get(0)+"'>"+
	    										detOrigen.get(i).get(3)+
	    									"</td>"+
	    									"<td style='text-align:center'>"+detOrigen.get(i).get(4)+"</td>"+
	    									"<td style='text-align:right'>"+
	    										"<div id='1_cantStock'>"+myformatdouble2.format(auxStock)+"</div>"+
	    									"</td>"+
	    									"<td style='text-align:right'>"+
		    									"<input type='text' class='cantDespacho form-control right' "+
		    									" value='0.00' "+
		    									" id='1_cantDespacho_"+detOrigen.get(i).get(0)+"_0' "+ 
		    									" name='cantDespacho[]' " +
		    									" onfocus=\"value=value.replace(/,/g,'')\" "+
		    									" onkeypress='return ingresoDouble(event,value)' "+
		    									" onfocusout='value = formatStandar2(value);' "+
		    									" onchange='validaCantDespacho(1,value,"+detOrigen.get(i).get(0)+",0,"+auxStock+",0,0); value=formatStandar2(value);'>"+
	    									"</td>"+
	    									"<td style='text-align:right'>"+
		    									"<input type='text' class='cantEquivalente form-control right' "+
		    									" value='0.00' "+
		    									" id='1_cantEquivalente' "+
		    									" name='cantEquivalente[]' " +
		    									" onfocus=\"value=value.replace(/,/g,'')\" "+
		    									" onkeypress='return ingresoDouble(event,value)' "+
		    									" onfocusout='value = formatStandar2(value);' "+
		    									" onchange='value=formatStandar2(value); $(\"#seModifico\").val(1)'>"+
	    									"</td>"+
	    									"<td style='text-align:right'>"+
	    										"<div class='kg' id='1_kgTot'>0.00</div>"+
	    									"</td>"+
	    									"<td style='text-align:right'>"+
	    										"<div class='m2' id='1_m2Tot'>0.00</div>"+
	    									"</td>"+
	    									"<td style='display:none'>"+
	    										"<div id='1_kgUn'>"+detOrigen.get(i).get(10)+"</div>"+
	    									"</td>"+
	    									"<td style='display:none'>"+
												"<div id='1_m2Un'>"+detOrigen.get(i).get(11)+"</div>"+
											"</td>"+
	    								"</tr>";
	    					}
	    					
	    					
	    					vistaDetOrigen +=
	    								"<tr>"+
	    									"<td width='18%' style='text-align:left'>"+
	    										"<input type='button' class='btnBuscar btn btn-info btn-sm rounded-pill btn-block' value='buscar' "
	    										+ " onclick='index="+i+";"
	    												+ " idCotizaDetalle="+detOrigen.get(i).get(8)+";"
	    												+ " esVenta="+detOrigen.get(i).get(1)+";"
	    												+ " idEquipOrigen="+detOrigen.get(i).get(0)+";"
	    												+ " muestraEquipos();'>"+
	    									"</td>"+
	    									"<td width='37%' style='text-align:left'></td>"+
	    									"<td width='5%' style='text-align:center'></td>"+
	    									"<td width='7.5%' style='text-align:right'></td>"+
	    									"<td width='9.5%' style='text-align:right'></td>"+
	    									"<td width='9%' style='text-align:right'></td>"+
	    									"<td width='7%' style='text-align:right'></td>"+
	    									"<td width='7%' style='text-align:right'></td>"+
	    								"</tr>"+
	    							"</table>"+
	    						"</td>"+
	    					"</tr>";
	    				}
	    			vistaDetOrigen += "</tbody>"
	    					+ "<tfoot>"+
		    					"<TR> "+
			    					"<TH style='text-align:left;'>TOTALES</TH>"+
			    					"<TH></TH>"+
			    					"<TH></TH>"+
			    					"<TH></TH>"+
			    					"<TH></TH>"+
			    					"<th></th>"+
			    					"<TH></TH>"+
			    					"<TH></TH>"+
			    					"<TH></TH>"+
			    					"<TH></TH>"+
			    					"<TH style='text-align:right;'><div id='totalCant'>0.00</div></TH>"+
			    					"<TH></TH>"+
			    					"<TH style='text-align:right;'><div id='totalKG'>0.00</div></TH>"+
			    					"<TH style='text-align:right;'><div id='totalM2'>0.00</div></TH>"+
			    				"</TR>"+
			    				"</tfoot>"+
	    					"</table>";
	    			
	    			String fechaGuia = Fechas.AAMMDD(guia.getFecha());
	    			
	    			
	    			
	    			List<Transportista> listaTransporte = Transportista.listaTransportista(con, s.baseDato);
	    	
	    			con.close();
	    			return ok(otDespachoModificar.render(mapeoDiccionario,mapeoPermiso,userMnu, guia, cabezeraOt, bodegaDestino, 
	    					fechaGuia, vistaDetOrigen, coti.getId(), (long) cont, bodegaOrigen, listaTransporte, coti.getId_comercial()));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		return ok(mensajes.render("/",msgError));
    		}
		}else {
			return ok(mensajes.render("/",msgError));
		}
	}
	
	public Result modificaDespacho(Http.Request request) {
		Sessiones s = new Sessiones(request);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		FormDespacho form = formFactory.form(FormDespacho.class).withDirectFieldAccess(true).bindFromRequest(request).get();
    		if (form.id_bodegaDestino==null || form.id_ot==null) {
    			return ok(mensajes.render("/",msgErrorFormulario));
    		}else {
    			if(form.seModifico == (long) 0 ) {
    				return redirect("/otListaDespachoModificarPeriodo/");
    			}else {
    				Archivos archivos = formFactory.form(Archivos.class).withDirectFieldAccess(true).bindFromRequest(request).get();
	    			try {
		    			Connection con = db.getConnection();
		    			
		    			Guia auxGuia = Guia.find(con, s.baseDato, form.id_guia);
		    			Long id_userCrea = auxGuia.getId_userCrea();
		    			
	    				if(Movimiento.delete(con, s.baseDato, form.id_guia)) {
	    					if(Guia.existeNumero(con, s.baseDato, form.numeroGuia)) {
	    	    				Long nuevoNumero = Guia.findNuevoNumero(con, s.baseDato);
	    	    				form.numeroGuia = nuevoNumero;
	    	    			}
	    					String nombreDocAdjunto = "0";
			    			String nombreArchivoSinExtencion = "";
			    			if (archivos != null) {
			    				if(archivos.docAdjunto != null) {
			    					nombreArchivoSinExtencion = "Doc_Mov_" + form.numeroGuia;
			    					if(archivos.docAdjunto.size() == 1) {
			    						String fileName = archivos.docAdjunto.get(0).getFilename();
			    			         	fileName = fileName.substring(fileName.indexOf("."));
			    						while(fileName.substring(1).indexOf(".")>0) {
			    							fileName=fileName.substring(1);
			    							fileName=fileName.substring(fileName.indexOf("."));
			    						}
			    						String ext = fileName;
			    						nombreDocAdjunto = nombreArchivoSinExtencion + ext;
			    					}else {
			    						nombreDocAdjunto = nombreArchivoSinExtencion + ".zip";
			    					}
			    				}
			    			}
			    			form.docAnexo = nombreDocAdjunto;
			    			form.fotos = auxGuia.fotos;
	    					
	    					if(OtDespachado.eliminarDespacho(con, s.baseDato, form.id_guia)) {
	    						
	    						
								if(FormDespacho.create(con, s.baseDato, form, mapeoDiccionario, id_userCrea.toString(), s.id_usuario)) {
				    				FormMovimiento formMov = new FormMovimiento();
				    				formMov.numeroGuia = form.numeroGuia;
				    				formMov.numGuiaCliente = form.numGuiaCliente;
				    				formMov.fechaGuia = form.fechaGuia;
				    				formMov.observaciones = form.observaciones;
				    				formMov.id_transportista = form.id_transportista;
				    				
				    				if (archivos != null) {
				    					MnuCotizar.grabarFilesThread grabarFile = new MnuCotizar.grabarFilesThread(s.baseDato, archivos, nombreArchivoSinExtencion);
					    				grabarFile.run();
				    				}
				    				
				    				
				    				
				    				String fileNamePdf = FormMovimiento.generaGuiaPDF(con, s.baseDato, formMov, mapeoDiccionario, mapeoPermiso, Long.parseLong(s.id_usuario));
				    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "otDespacho", (long)0, "update", "modifica despacho movimiento nro "+form.numeroGuia);
				    				String titulo = "DESPACHO ENTRE "+mapeoDiccionario.get("BODEGA")+" O PROYECTOS";
				    				//String url = "%2FotListaDespachoModificar%2F0";
				    				String url = "%2Fhome%2F";
				    				con.close();
				    				
				    				return redirect("/routes2/redirShowPDF/"+fileNamePdf+","+titulo+","+url);
				    				
				    			}
			    			}
	    				}else {
	    					con.close();
		    				return ok(mensajes.render("/",msgError));
	    				}
	    			} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
    			}
    			return ok(mensajes.render("/",msgError));
    		}
    	}
    	return ok(mensajes.render("/",msgError));
    }
    
	
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
	
	
	//======================================================================
    // MNU otListarDespachos   Cotizar/Despachos/Listar Despachos
    //======================================================================
	
	public Result otListarDespachosPeriodo(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("otListarDespachos")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			
    			Fechas hoy = Fechas.hoy();
    			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
    			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			con.close();
    			return ok(otListarDespachosPeriodo.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
	
	public Result otListarDespachos(Http.Request request) {
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
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("otListarDespachos")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			
	    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
	    			permisoPorBodega = permisoPorBodega.replaceAll("`movimiento`.`id_bodegaEmpresa`", "`guia`.`id_bodegaDestino`");
	    			List<Guia> listaGuias = Guia.allDespachosConOtDesdeHasta(con, s.baseDato, desdeAAMMDD, hastaAAMMDD, permisoPorBodega, s.aplicaPorSucursal, s.id_sucursal);
	    			List<Guia> listaGuiasSinNroNegativo = new ArrayList<Guia>();
	    			listaGuias.forEach(x->{
	    				if((long)x.getNumero()>(long)0) {
	    					listaGuiasSinNroNegativo.add(x);
	    				}
	    			});
	    			
	    			con.close();
	    			return ok(otListarDespachos.render(mapeoDiccionario,mapeoPermiso,userMnu,listaGuiasSinNroNegativo));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
	   		
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
	
	public Result otDetalleDespacho(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_guia = Long.parseLong(form.get("id_guia").trim());
	       		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			
	    			Guia guia = Guia.find(con, s.baseDato, id_guia);
	    			BodegaEmpresa bodegaOrigen = BodegaEmpresa.findXIdBodega(con, s.baseDato, guia.getId_bodegaOrigen());
	    			
	    			List<List<String>> detalleGuia = new ArrayList<List<String>>();
					if((long) bodegaOrigen.esInterna == (long) 1) {
						detalleGuia = Guia.findDetalleGuiaOrigenDestinoYPrecios(con, s.baseDato, guia.getId(), guia.getId_bodegaDestino(), mapeoDiccionario.get("pais"), guia.getId_bodegaOrigen());
					}else {
						detalleGuia = Guia.findDetalleGuiaOrigenDestinoYPrecios(con, s.baseDato, guia.getId(), guia.getId_bodegaOrigen(), mapeoDiccionario.get("pais"), guia.getId_bodegaOrigen());
					}

					List<TipoEstado> listTipoEstado = TipoEstado.all(con, s.baseDato);
					List<TipoReparacion> listTipoReparacion = TipoReparacion.all(con, s.baseDato);
					Cotizacion coti = Cotizacion.find(con, s.baseDato, guia.getId_cotizacion());
					
					BodegaEmpresa bodegaDestino = BodegaEmpresa.findXIdBodega(con, s.baseDato, guia.getId_bodegaDestino());
					
					String nickCliente = "No Aplica";
	    			if((long)bodegaOrigen.getEsInterna() == (long)2 ){
	    				nickCliente = bodegaOrigen.getNickCliente();
	    			}else if((long)bodegaDestino.getEsInterna() == (long)2 ){
	    				nickCliente = bodegaDestino.getNickCliente();
	    			}
	    			
					con.close();
					return ok(otDetalleDespacho.render(mapeoDiccionario,mapeoPermiso,userMnu,guia, detalleGuia, bodegaOrigen, listTipoEstado, listTipoReparacion, coti, nickCliente));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		return ok(mensajes.render("/",msgError));
    		}
		}else {
			return ok(mensajes.render("/",msgError));
		}
	}
	
	
	public Result otListarDespachosImgPeriodo(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("otListarDespachos")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			
    			Fechas hoy = Fechas.hoy();
    			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
    			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			con.close();
    			return ok(otListarDespachosImgPeriodo.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
	
	public Result otListarDespachosImg(Http.Request request) {
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
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("otListarDespachos")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			

	    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
	    			permisoPorBodega = permisoPorBodega.replaceAll("`movimiento`.`id_bodegaEmpresa`", "`guia`.`id_bodegaDestino`");
	    			
	    			List<Guia> listaGuias = Guia.allDespachosConOtDesdeHasta(con, s.baseDato, desdeAAMMDD, hastaAAMMDD, permisoPorBodega, s.aplicaPorSucursal, s.id_sucursal);
	    			List<Guia> listaGuiasSinNroNegativo = new ArrayList<Guia>();
	    			listaGuias.forEach(x->{
	    				if((long)x.getNumero()>(long)0) {
	    					listaGuiasSinNroNegativo.add(x);
	    				}
	    			});
	    			
	    			con.close();
	    			return ok(otListarDespachosImg.render(mapeoDiccionario,mapeoPermiso,userMnu,listaGuiasSinNroNegativo));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
	   		
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
	
	
	
	public Result otDetalleDespachoImg(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_guia = Long.parseLong(form.get("id_guia").trim());
	       		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			
	    			Guia guia = Guia.find(con, s.baseDato, id_guia);
	    			BodegaEmpresa bodegaOrigen = BodegaEmpresa.findXIdBodega(con, s.baseDato, guia.getId_bodegaOrigen());
	    			
	    			List<List<String>> detalleGuia = new ArrayList<List<String>>();
					if((long) bodegaOrigen.esInterna == (long) 1) {
						detalleGuia = Guia.findDetalleGuiaOrigenDestinoYPrecios(con, s.baseDato, guia.getId(), guia.getId_bodegaDestino(), mapeoDiccionario.get("pais"), guia.getId_bodegaOrigen());
					}else {
						detalleGuia = Guia.findDetalleGuiaOrigenDestinoYPrecios(con, s.baseDato, guia.getId(), guia.getId_bodegaOrigen(), mapeoDiccionario.get("pais"), guia.getId_bodegaOrigen());
					}
					
	    			List<TipoEstado> listTipoEstado = TipoEstado.all(con, s.baseDato);
	    			List<TipoReparacion> listTipoReparacion = TipoReparacion.all(con, s.baseDato);
	    			Cotizacion coti = Cotizacion.find(con, s.baseDato, guia.getId_cotizacion());
	    			
	    			BodegaEmpresa bodegaDestino = BodegaEmpresa.findXIdBodega(con, s.baseDato, guia.getId_bodegaDestino());
					
					String nickCliente = "No Aplica";
	    			if((long)bodegaOrigen.getEsInterna() == (long)2 ){
	    				nickCliente = bodegaOrigen.getNickCliente();
	    			}else if((long)bodegaDestino.getEsInterna() == (long)2 ){
	    				nickCliente = bodegaDestino.getNickCliente();
	    			}
	    			
	    			con.close();
	    			return ok(otDetalleDespachoImg.render(mapeoDiccionario,mapeoPermiso,userMnu,guia, detalleGuia, bodegaOrigen, listTipoEstado, listTipoReparacion, coti, nickCliente));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		return ok(mensajes.render("/",msgError));
    		}
		}else {
			return ok(mensajes.render("/",msgError));
		}
	}
	
	//======================================================================
    // MNU otAgregaOC   Cotizar/Agregar OC del Cliente
    //======================================================================
    
	public Result otListaAgregarOCPeriodo(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("otAgregaOC")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Fechas hoy = Fechas.hoy();
    			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
    			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			con.close();
    			return ok(otListaAgregarOCPeriodo.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result otListaAgregarOC(Http.Request request) {
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
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("otAgregaOC")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			List<List<String>> listOt = Ot.listOtAgregarOC(con,s.baseDato, s.aplicaPorSucursal, s.id_sucursal, desdeAAMMDD, hastaAAMMDD);
	    			con.close();
	    			return ok(otListaAgregarOC.render(mapeoDiccionario,mapeoPermiso,userMnu,listOt,desdeAAMMDD,hastaAAMMDD));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result grabarOcPdf(Http.Request request, Long id_coti, String desdeAAMMDD, String hastaAAMMDD) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		
    		Http.MultipartFormData<TemporaryFile> body = request.body().asMultipartFormData();
			Http.MultipartFormData.FilePart<TemporaryFile> docAdjunto = body.getFile("docAdjunto");
			try {
    			Connection con = db.getConnection();
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			
    			Cotizacion coti= Cotizacion.find(con, s.baseDato, id_coti);
    			String path = "0";
				if (docAdjunto != null && coti !=null) {
					String nombreArchivoSinExtencion = "ocCliente_Coti_Nro_" + coti.getNumero();
					path = Archivos.grabarArchivos(docAdjunto, s.baseDato, nombreArchivoSinExtencion);
					Cotizacion.modifyXCampo(con, s.baseDato, "ocClientePDF", path, id_coti);
					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "cotizacion", id_coti, "update", "agrega orden de compra a coti nro: "+coti.getNumero());
				}
				
    			if(mapeoPermiso.get("otAgregaOC")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<List<String>> listOt = Ot.listOtAgregarOC(con,s.baseDato, s.aplicaPorSucursal, s.id_sucursal, desdeAAMMDD, hastaAAMMDD);
    			con.close();
    			return ok(otListaAgregarOC.render(mapeoDiccionario,mapeoPermiso,userMnu,listOt,desdeAAMMDD,hastaAAMMDD));
			} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}
    	return ok(mensajes.render("/",msgError));
    }
    
    public Result oc_cotiClienteAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	Long id_cotizacion = Long.parseLong(form.get("id_cotizacion").trim());
	    	  	String numeroOC = form.get("numeroOC").trim();
				try {
	    			Connection con = db.getConnection();
	    			Cotizacion.modifyXCampo(con, s.baseDato, "numeroOC", numeroOC, id_cotizacion);
	    			Cotizacion coti = Cotizacion.find(con, s.baseDato, id_cotizacion);
	    			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "cotizacion", id_cotizacion, "update", "agrega numero de orden de compra a coti nro: "+coti.getNumero());
					con.close();
	    			return ok("");
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
				return ok("error");
	       	}
    	}else {
    		return ok("error");
    	}
    }
    
    //======================================================================
    // MNU cotizaGeneraContrato   Cotizar/Generar Contrato
    //======================================================================
    
    public Result otGenerarContratoPeriodo(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("cotizaImprime")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Fechas hoy = Fechas.hoy();
    			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
    			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			con.close();
    			return ok(otGenerarContratoPeriodo.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result otGenerarContrato(Http.Request request) {
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
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("cotizaImprime")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}

	    			List<List<String>> listCoti = Cotizacion.listCotiGenerarContrato(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal, desdeAAMMDD, hastaAAMMDD);

	    			con.close();
	    			return ok(otGenerarContrato.render(mapeoDiccionario,mapeoPermiso,userMnu,listCoti));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result grabarContratoPdf(Http.Request request, Long id_cotizacion) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		Http.MultipartFormData<TemporaryFile> body = request.body().asMultipartFormData();
			Http.MultipartFormData.FilePart<TemporaryFile> docAdjunto = body.getFile("docAdjunto");
			try {
    			Connection con = db.getConnection();
    			String path = "0";
				if (docAdjunto != null) {
					String nombreArchivoSinExtencion = "contrato_ClienteCotiz_ID_" + id_cotizacion;
					path = Archivos.grabarArchivos(docAdjunto, s.baseDato, nombreArchivoSinExtencion);
					Cotizacion.modifyXCampo(con, s.baseDato, "contratoPDF", path, id_cotizacion);
				}
				Cotizacion coti = Cotizacion.find(con, s.baseDato, id_cotizacion);
				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "cotizacion", id_cotizacion, "update", "agrega contrato pdf a cotizacion nro: "+coti.getNumero());
				con.close();
				return redirect("/routes2/otGenerarContratoPeriodo/");
			} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}
    	return ok(mensajes.render("/",msgError));
    }
    
    public Result datosContrato(Http.Request request) {
		Sessiones s = new Sessiones(request);
		
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	Long id_cotizacion = Long.parseLong(form.get("id_cotizacion").trim());
				try {
	    			Connection con = db.getConnection();
	    			FormContrato datos = FormContrato.find(con, s.baseDato, id_cotizacion);
	    			Regiones region = Regiones.findPorNombre(con, s.baseDato, datos.clienteRegion);	
	    			Comunas comuna = Comunas.findPorNombre(con, s.baseDato, datos.clienteComuna);
	    			String codRegion = "0";
	    			if(region!=null) {
	    				codRegion = region.codigo;
	    			}
	    			if(comuna == null) {
	    				comuna = new Comunas();
	    			}
	    			
	    			
	    			
	    			List<Regiones> listRegiones = Regiones.all(con, s.baseDato);
	    			List<Comunas> listComunas = Comunas.allPorRegion(con, s.baseDato, codRegion);
	    			List<List<String>> listBodegas = BodegaEmpresa.listaAllBodegasVigentesExternas(con, s.baseDato, mapeoPermiso, s.aplicaPorSucursal, s.id_sucursal);
	    			List<Proyecto> listProyectos = Proyecto.all(con, s.baseDato);
	    			
		       		con.close();
		       		return ok(datosContrato.render(mapeoDiccionario,mapeoPermiso,userMnu,datos,region,comuna,listRegiones, listComunas, listBodegas, listProyectos));
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    	return ok(mensajes.render("/",msgError));
	}
    
    
    
    public Result hacerContrato(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		FormContrato form = formFactory.form(FormContrato.class).withDirectFieldAccess(true).bindFromRequest(request).get();
    		if (form.id_cotizacion==null || form.id_cliente==null) {
    			return ok(mensajes.render("/",msgErrorFormulario));
    		}else {
				try {
	    			Connection con = db.getConnection();
	    			
	    			String fileNamePdf = "0";
	    			String titulo = "";
	    			
	    			if(FormContrato.update(con, s.baseDato, form)) {
	    				
	    				Cotizacion cotizacion = Cotizacion.find(con, s.baseDato, form.id_cotizacion);
	    				
	    				if(s.baseDato.equals("madaPeruTeca")) {
	    					fileNamePdf = GeneraPDF_ContratoPeruTeca.generaPdfContratoTeca1(con, s.baseDato, form, cotizacion);
	    				}else if(s.baseDato.equals("madaMontax")){
	    					fileNamePdf = GeneraPDF_ContratoMontax.generaPdfContratoMontax(con, s.baseDato, cotizacion);
	    				}else {
	    					fileNamePdf = GeneraPDF_Contrato.generaPdfContratoTipoCotiza(con, s.baseDato, cotizacion, form);
	    				}
	    				
	    				titulo = "CONTRATO_"+ cotizacion.getNumeroContrato();
	    			}
	    			//String url = "%2FotGenerarContrato%2F0";
	    			String url = "%2Fhome%2F";
		       		con.close();
		       		
		       		return redirect("/routes2/redirShowPDF/"+fileNamePdf+","+titulo+","+url);
		       		
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    	return ok(mensajes.render("/",msgError));
	}
    
    public Result cotiPlantilla(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
        	if (form.hasErrors()) {
    			return ok(mensajes.render("/",msgErrorFormulario));
    		}else {
    			try {
	    			Connection con = db.getConnection();
	    			File file = Cotizacion.plantillaCotizacion(con, s.baseDato);
	    			if(file != null) {
						con.close();
	    				return ok(file, Optional.of("plantillaCargaCotizacion.xlsx"));
					}else {
						con.close();
						return ok(mensajes.render("/",msgError));
					}
    			} catch (Exception e) {
    				e.printStackTrace();
    	        }
	       	}
    	}
    	return ok(mensajes.render("/",msgError));
	}
    
    public Result cotiValidarPlantilla(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
        	if (form.hasErrors()) {
    			return ok(mensajes.render("/",msgErrorFormulario));
    		}else {
    			List<String> mensaje = new ArrayList<String>();
    			Archivos aux = formFactory.form(Archivos.class).withDirectFieldAccess(true).bindFromRequest(request).get();
        		Http.MultipartFormData.FilePart<TemporaryFile> archivo = aux.file;
        		
    			if (archivo != null) {
    				File file = Archivos.parseMultipartFormDatatoFile(archivo);
					try {
		    			Connection con = db.getConnection();
		    			mensaje = FormCotiza.cotiValidarPlantillaExcel(con, s.baseDato, file);
		    			String jsonMsg = Json.toJson(mensaje).toString();
		    			con.close();
		    			return ok(jsonMsg.toString()).as("application/json");
					} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
    			}
	       	}
    	}
    	return ok("error");
    }
    
    
    public Result cotiCargaPlantilla(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
        	if (form.hasErrors()) {
    			return ok(mensajes.render("/",msgErrorFormulario));
    		}else {
    			Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
    			String id_sucursal = form.get("id_sucursal").trim();

        		Http.MultipartFormData<TemporaryFile> body = request.body().asMultipartFormData();
    			Http.MultipartFormData.FilePart<TemporaryFile> archivo = body.getFile("archivoXLSX");
    			if (archivo != null) {
    				File file = Archivos.parseMultipartFormDatatoFile(archivo);
					try {
		    			Connection con = db.getConnection();

		    				List<List<String>> listaExcel = FormCotiza.llenaListaDesdeExcel(file);
		    				Map<String,List<String>> mapListaExcel = new HashMap<String,List<String>>();
		    				for(List<String> l: listaExcel){
		    					if(l.get(2).trim().equals("")) {
		    						l.set(2, "1");
		    					}
		    					mapListaExcel.put(l.get(1).toUpperCase().trim(), l);
		    				}
		    				
		    				
		    				if(mapListaExcel.size() != listaExcel.size()) {
		    					con.close();
								return ok(mensajes.render("/cotizaIngreso2/"+id_bodegaEmpresa, "Existen codigos duplicados en el archivo"));
		    				}
		    	
		    					Map<String,Equipo> mapEquipos = Equipo.mapAllAllPorCodigo(con, s.baseDato);
		    					String newEquipos = "";
		    					String selEquipos = "";
		    					List<Long> idsMonedas = new ArrayList<Long>();
		    					List<Long> idsUnTiempo = new ArrayList<Long>();
		    					
		    					List<Double> valPVta = new ArrayList<Double>();
		    					List<Double> valPArr = new ArrayList<Double>();
		    					
		    					
		    	    			List<String> codigos = new ArrayList<String>();
		    	    			Map<String,Grupo> mapGrupo = Grupo.mapAllPorNombre(con, s.baseDato);
		    	    			Map<String,Unidad> mapUnidad = Unidad.mapPorNombre(con, s.baseDato);
		    	    			Map<String,UnidadTiempo> mapUnidadTiempo = UnidadTiempo.mapUnidadTiempoPorNombre(con, s.baseDato);
		    	    			
		    	    			
		    	    			Map<String,Moneda> mapMoneda = Moneda.mapNickMonedas(con, s.baseDato);
		    	    			
		    	    			Long id_moneda2 = (long) 1;
	    	    				Long auxCont = (long)0;
	    	    				PreparedStatement smt01 = con
	    	    						.prepareStatement("select id_moneda, count(id_moneda) from `"+s.baseDato+"`.precio "
	    	    								+ " group by id_moneda;");
	    	    				ResultSet rs01 = smt01.executeQuery();
	    	    				while(rs01.next()) {
	    	    					if(auxCont < rs01.getLong(2)) {
	    	    						auxCont = rs01.getLong(2);
	    	    						id_moneda2 = rs01.getLong(1);
	    	    					}
	    	    				}
	    	    				smt01.close();
	    	    				rs01.close();
	    	    				
	    	    				Long idAuxUnTiempo = (long) 2;
		    					PreparedStatement smtAux = con
	    	    						.prepareStatement("select count(id_unidadTiempo), id_unidadTiempo from `"+s.baseDato+"`.precio group by id_unidadTiempo;");
	    	    				ResultSet rsAux = smtAux.executeQuery();
	    	    				Long aux = (long)0;
	    	    				while(rsAux.next()) {
	    	    					if(rsAux.getLong(1) > aux) {
	    	    						aux = rsAux.getLong(1);
	    	    						idAuxUnTiempo = rsAux.getLong(2);
	    	    					}
	    	    				}
	    	    				smtAux.close();
	    	    				rsAux.close();
	    	    				
		    	    			
		    					for(List<String> l: listaExcel) {
		    						String codEquipo = l.get(1);
		    						if(codEquipo!=null) {
		    							codEquipo = codEquipo.trim().toUpperCase();
		    						}
		    						
									Equipo equipo = mapEquipos.get(codEquipo);
		    	    				if(equipo == null) {
		    	    					Long id_grupo = (long)0;
		    	    					Grupo grupo = mapGrupo.get(l.get(0));
		    	    					if(grupo != null) {
		    	    						id_grupo = grupo.getId();
		    	    					}
		    	    					Long id_unidad = (long)1;
		    	    					String nomUnidad = l.get(3);
		    	    					if(nomUnidad!=null) {
		    	    						nomUnidad = nomUnidad.toUpperCase().trim();
		    	    					}
		    	    					Unidad unidad = mapUnidad.get(nomUnidad);
		    	    					if(unidad != null) {
		    	    						id_unidad = unidad.getId();
		    	    					}
		    	    					
		    	    					String mon = l.get(6);
		    	    					if(mon!=null) {
		    	    						mon = l.get(6).trim().toUpperCase();
		    	    					}
		    	    					
	    	    						Moneda moneda = mapMoneda.get(mon);
	    	    						if(moneda == null) {
	    	    							moneda = Moneda.find(con, s.baseDato, id_moneda2);
	    	    						}
	    	    						
	    	    						String unTiempo = l.get(8);
		    	    					if(unTiempo!=null) {
		    	    						unTiempo = l.get(8).trim().toUpperCase();
		    	    					}
		    	    					
	    	    						UnidadTiempo unidadTiempo = mapUnidadTiempo.get(unTiempo);
	    	    						if(unidadTiempo == null) {
	    	    							unidadTiempo = UnidadTiempo.find(con, s.baseDato, idAuxUnTiempo);
	    	    						}
	    	    						
	    		    					Double puVentaDbl = (double)0;
	    	    						String puVentaStr = l.get(7);
	    	    						if(puVentaStr!=null) {
	    	    							try {
	    	    								puVentaDbl = Double.parseDouble(puVentaStr);
	    	    							}catch(Exception e){}
	    	    						}
	    	    						valPVta.add(puVentaDbl);
	    	    						
	    	    						Double puArrDbl = (double)0;
	    	    						String puArrStr = l.get(7);
	    	    						if(puArrStr!=null) {
	    	    							try {
	    	    								puArrDbl = Double.parseDouble(puArrStr);
	    	    							}catch(Exception e){}
	    	    						}
	    	    						valPArr.add(puArrDbl);
	    	    						
	    	    						
		    	    					newEquipos += "('"+codEquipo+"','"+l.get(2)+"','"+id_unidad+"','"+id_grupo+"'),";
		    	    					codigos.add(l.get(1));
		    	    					selEquipos += "'"+l.get(1)+"',";
		    	    					idsMonedas.add(moneda.getId());
		    	    					idsUnTiempo.add(unidadTiempo.getId());
		    	    					
		    	    				}
		    					}
		    					
		    					
		    					
	    	    				
		    					
	    	    				if(newEquipos.length()>1) {
		    	    				newEquipos = newEquipos.substring(0,newEquipos.length()-1);
		    	    				PreparedStatement smt = con
		    	    						.prepareStatement("INSERT INTO `"+s.baseDato+"`.equipo (codigo,nombre,id_unidad,id_grupo) VALUES "+newEquipos+";");
		    	    				smt.executeUpdate();
		    	    				smt.close();
		    	    				if(selEquipos.length()>1) {
		    	    					selEquipos = "("+selEquipos.substring(0,selEquipos.length()-1)+")";
		    	    				}
		    	    				
		    	    				PreparedStatement smt2 = con
		    	    						.prepareStatement("Select id from `"+s.baseDato+"`.equipo where codigo in "+selEquipos+";");
		    	    				ResultSet rs2 = smt2.executeQuery();
		    	    				List<String> idsEquipo = new ArrayList<String>();
		    	    				while(rs2.next()) {
		    	    					idsEquipo.add(rs2.getString(1));
		    	    				}
		    	    				smt2.close();
		    	    				rs2.close();
		    	    				String datos = "";
		    	    				List<Sucursal> listSucursal = Sucursal.all(con, s.baseDato);
		    	    				for(int i=0; i<idsEquipo.size(); i++){
		    	    					
		    	    					Long id_moneda = idsMonedas.get(i);
	    	    						Long  id_unidadTiempo = idsUnTiempo.get(i);
		    	    					Double precioVenta = valPVta.get(i);
		    	    					Double precioArriendo = valPArr.get(i);
		    	    					Double tasaArriendo = (double)0;
		    	    					if(precioVenta>0) {
		    	    						tasaArriendo = precioArriendo/precioVenta;
		    	    					}
		    	    					
		    	    					for(Sucursal sucursal: listSucursal) {
		    	    						datos += "("+idsEquipo.get(i)+",'"+id_moneda+"','"+Fechas.hoy().getFechaStrAAMMDD()+"','"+precioVenta+"','"+precioVenta+"','"+tasaArriendo+"',"+id_unidadTiempo+",'"+precioArriendo+"','0','0',"+sucursal.getId()+"),";
		    	    					}
		    	    					
		    	    				}
		    	    				if(datos.length()>1) {
		    	    					datos = datos.substring(0,datos.length()-1);
		    	    				}
		    	    				
		    	    				PreparedStatement smt3 = con
		    	    						.prepareStatement("insert into `"+s.baseDato+"`.precio "
		    	    								+ " (id_equipo,id_moneda,fecha, precioVenta, precioReposicion, tasaArriendo, id_unidadTiempo, precioArriendo, precioMinimo, permanenciaMinima, id_sucursal) "
		    	    								+ " values "+datos+";");
		    	    				smt3.executeUpdate();
		    	    				smt3.close();
		    	    			}
	    	    				
	    	    				
	    	    				
	    	    				
	    	    				
		    					Long id_cotizacion = (long) 0;
		    					
		    	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		    	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		    	    			
		    	    			Long numeroCoti = Cotizacion.findNuevoNumero(con, s.baseDato);
		    	    			String fecha = Fechas.hoy().getFechaStrAAMMDD();
		    	    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
		    	    			FormCotiza formCotiza = new FormCotiza();
		    	    			if(bodega != null) {
		    	    				Cliente cliente = Cliente.find(con, s.baseDato, bodega.getId_cliente());
		    	    				String rutCliente = "";
		    	    				if(cliente!=null) {
		    	    					rutCliente = cliente.getRut();
		    	    				}
		    	    				formCotiza = new FormCotiza(numeroCoti, fecha, bodega.getId_cliente(), bodega.getId_proyecto(), bodega.getId(), 
		    	    						rutCliente, bodega.nickCliente, bodega.nickProyecto, bodega.nombre);
		    	    			} else {
		    	    				formCotiza = new FormCotiza(numeroCoti, fecha);
		    	    			}
		    	    			
		    	    			List<List<String>> listaConPrecio = FormCotiza.allConSuPrecio(con, s.baseDato, mapeoDiccionario.get("nEmpresa"), id_bodegaEmpresa, mapeoPermiso, id_sucursal, "0","0",(long)-1);
		    	    		
		    	    			List<Cliente> listClientes = Cliente.all(con, s.baseDato);
		    	    			List<Proyecto> listProyectos = Proyecto.all(con, s.baseDato);
		    	    			List<List<String>> listaDetCoti = FormCotiza.detalleCotizacion(con, s.baseDato, mapeoPermiso, mapeoDiccionario, id_bodegaEmpresa, id_cotizacion, s.id_sucursal,"0","0",
		    	    					listaConPrecio);
		    	    			String numDecParaTot = "0";
		    	    			if(listaDetCoti.size()>0) {
		    	    				numDecParaTot = listaDetCoti.get(listaDetCoti.size()-1).get(20);
		    	    			}
		    	    			formCotiza.id_cotizacion = id_cotizacion;
		    	    			
		    	    			List<Regiones> listRegiones = Regiones.all(con, s.baseDato);
		    	    			
		    	    			
		    	    			for(int i=0;i<listaConPrecio.size();i++){
		    	    				
		    	    				Long decimal = Long.parseLong(listaConPrecio.get(i).get(20));
		    	    					
		    	    					List<String> lexcel = mapListaExcel.get(listaConPrecio.get(i).get(1).toUpperCase());
		    	    					
		    	    					if(lexcel!=null) {
		    	    						//CANTIDAD
		    	    						Double cantDbl = (double) 1;
		    	    						String cantStr = "1.00";
		    	    						String auxStr = lexcel.get(4).trim();
		    	    						
		    	    						if(!auxStr.equals("")) {
		    	    							try {
		    	    								cantDbl =  Double.parseDouble(auxStr);
		    	    								if((double) cantDbl > (double) 0) {
		    	    									cantStr =  DecimalFormato.formato(cantDbl, (long)2);
		    	    								}
		    	    							}catch(Exception e){}
		    	    						}
		    	    						listaConPrecio.get(i).set(10, cantStr);
		    	    						
		    	    						
		    	    						//ESVENTA
		    	    						String esVenta = "0";
		    	    						auxStr = lexcel.get(5).toUpperCase().trim();
		    	    						if(auxStr.equals("SI")) {
		    	    							esVenta = "1";
		    	    						}
		    	    						listaConPrecio.get(i).set(11, esVenta);
		    	    						
		    	    						
		    	    						//PRECIO VENTA Y TOTAL
		    	    						String puVentaStr = listaConPrecio.get(i).get(5);
		    	    						Double puVentaDbl = Double.parseDouble(puVentaStr.replaceAll(",", ""));
		    	    						
		    	    						auxStr = lexcel.get(7).trim();
		    	    						if(!auxStr.equals("")) {
		    	    							try {
		    	    								puVentaDbl = Double.parseDouble(auxStr);
		    	    								if((double)puVentaDbl > (double)0) {
		    	    									puVentaStr = DecimalFormato.formato(puVentaDbl, decimal);
		    	    								}
		    	    							}catch(Exception e){}
		    	    						}
		    	    						listaConPrecio.get(i).set(5, puVentaStr);
		    	    						
		    	    						Double totVenta = cantDbl * puVentaDbl;
		    	    						listaConPrecio.get(i).set(13, DecimalFormato.formato(totVenta, decimal));
		    	    						
		    	    						if(esVenta.equals("1")) {
		    	    							listaConPrecio.get(i).set(15, DecimalFormato.formato(totVenta, decimal));
		    	    						}
		    	    						
		    	    						
		    	    						//PRECIO ARRIENDO, TASA, PERMANENCIA Y TOTAL
		    	    						String puArrStr = listaConPrecio.get(i).get(7);
		    	    						Double puArrDbl = Double.parseDouble(puArrStr.replaceAll(",", ""));
		    	    						auxStr = lexcel.get(10).trim();
		    	    						if(!auxStr.equals("")) {
		    	    							try {
		    	    								puArrDbl = Double.parseDouble(auxStr);
		    	    								if((double)puArrDbl > (double)0) {
		    	    									puArrStr = DecimalFormato.formato(puArrDbl, decimal);
		    	    								}
		    	    							}catch(Exception e){}
		    	    						}
		    	    						listaConPrecio.get(i).set(7, puArrStr);
		    	    						
		    	    						Double permDbl = (double)1;
		    	    						String permStr = "1.00";
		    	    						auxStr = lexcel.get(11).trim();
		    	    						if(!auxStr.equals("")) {
		    	    							try {
		    	    								permDbl = Double.parseDouble(auxStr);
		    	    								if((double)permDbl > (double)0) {
		    	    									permStr = DecimalFormato.formato(permDbl, (long)2);
		    	    								}
		    	    							}catch(Exception e){}
		    	    						}
		    	    						listaConPrecio.get(i).set(12, permStr);
		    	    						
		    	    						if(esVenta.equals("0")) {
			    	    						Double totArr = cantDbl * puArrDbl * permDbl;
			    	    						listaConPrecio.get(i).set(14, DecimalFormato.formato(totArr, decimal));
		    	    						}
		    	    						
		    	    						Double tasaDbl = (double) 0;
		    	    						if((double)puVentaDbl > (double)0) {
		    	    							tasaDbl = puArrDbl/puVentaDbl;
		    	    						}
		    	    						
		    	    						String tasaArriendo = myformatdouble2.format(tasaDbl*100)+" %";
			    	    					listaConPrecio.get(i).set(24,tasaArriendo); 									//tasa arr
		    	    						
			    	    					//KG Y M2 TOTALES
			    	    					Double kg = Double.parseDouble(listaConPrecio.get(i).get(16)) * cantDbl;
			    	    					Double m2 = Double.parseDouble(listaConPrecio.get(i).get(17)) * cantDbl;
			    	    					
			    	    					listaConPrecio.get(i).set(18,DecimalFormato.formato(kg, (long)2)); 
			    	    					listaConPrecio.get(i).set(19,DecimalFormato.formato(m2, (long)2));
			    	    					
			    	    					String mon = lexcel.get(6);
			    	    					if(mon!=null) {
			    	    						mon = lexcel.get(6).trim().toUpperCase();
			    	    					}
			    	    					Moneda moneda = mapMoneda.get(mon);
			    	    					if(moneda == null) {
		    	    							moneda = Moneda.find(con, s.baseDato, id_moneda2);
		    	    							id_moneda2 = moneda.getId();
		    	    						}
			    	    					
			    	    					String unTiempo = lexcel.get(8);
			    	    					if(unTiempo!=null) {
			    	    						unTiempo = lexcel.get(8).trim().toUpperCase();
			    	    					}
		    	    						UnidadTiempo unidadTiempo = mapUnidadTiempo.get(unTiempo);
		    	    						if(unidadTiempo == null) {
		    	    							unidadTiempo = UnidadTiempo.find(con, s.baseDato, idAuxUnTiempo);
		    	    						}
		    	    						
		    	    						listaConPrecio.get(i).set(8, moneda.getId().toString());
		    	    						listaConPrecio.get(i).set(4, moneda.getNickName());
		    	    						listaConPrecio.get(i).set(20, moneda.getNumeroDecimales().toString());
		    	    						listaConPrecio.get(i).set(9, unidadTiempo.getId().toString());
		    	    						listaConPrecio.get(i).set(6, unidadTiempo.getNombre());
			    	    					
		    	    						
		    	    					}
		    	    					
		    	    			}
		    	    			
		    	    			
		    	    			for(List<String> l: listaConPrecio) {
		    	    				l.add(l.get(10).replaceAll(",", ""));
		    	    				l.add(l.get(5).replaceAll(",", ""));
		    	    				l.add(l.get(24).replaceAll(",", "").replaceAll("%", ""));
		    	    				l.add(l.get(7).replaceAll(",", ""));
		    	    				l.add(l.get(12).replaceAll(",", ""));
		    	    				l.add(l.get(13).replaceAll(",", ""));
		    	    				l.add(l.get(14).replaceAll(",", ""));
		    	    				l.add(l.get(15).replaceAll(",", ""));
		    	    				l.add(l.get(18).replaceAll(",", ""));
		    	    				l.add(l.get(19).replaceAll(",", ""));
		    	    			}
		    	    			
		    	    			List<UnidadTiempo> listUnTiempo = UnidadTiempo.all(con, s.baseDato);
		    	    			
		    	    			Comercial comercial = new Comercial();
		    	    			Comercial auxComercial = Comercial.findPorIdUsuario(con, s.baseDato, s.id_usuario);
		    	    			if(auxComercial==null) {
		    	    				comercial.setId((long)0);
		    	    				comercial.setNameUsuario("");
		    	    				if(mapeoPermiso.get("cambiarComercial")!=null) {
		    	    					comercial.setNameUsuario("-- select comercial --");
		    	    				}
		    	    			}else {
		    	    				comercial = auxComercial;
		    	    			}
		    	    			
		    	    			
		    	    			List<Comercial> listComercial = new ArrayList<Comercial>();
		    	    			if(mapeoPermiso.get("cambiarComercial")!=null) {
		    	    				listComercial = Comercial.allPorIdSucursalVig(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
		    	    			}
		    	    			
		    	    			List<Sucursal> listSucursal = new ArrayList<Sucursal>();
		    	    			if(mapeoPermiso.get("cambiarSucursal")!=null) {
		    	    				listSucursal = Sucursal.all(con, s.baseDato);
		    	    			}
		    	    			
		    	    			Sucursal sucursal = Sucursal.find(con, s.baseDato, s.id_sucursal);
		    	    			
		    	    			List<CotizaSolucion> listSoluciones = CotizaSolucion.all(con, s.baseDato);
		    	    			
		    	    			String importDesdeExcel = "1";
		    	    			
		    	    			String jsonDetalle = Json.toJson(listaConPrecio).toString();
		    	    			String jsonListUnTiempo = Json.toJson(listUnTiempo).toString();
		    	    			
		    	    			EmisorTributario emisor = EmisorTributario.find(con, s.baseDato);
		    	    			
		    	    			con.close();
		    	    			return ok(cotizaIngreso2.render(mapeoDiccionario,mapeoPermiso,userMnu,formCotiza,listClientes,listProyectos,
		    	    					numDecParaTot,listRegiones, jsonListUnTiempo, sucursal, comercial, listSucursal,listComercial, importDesdeExcel,
		    	    					jsonDetalle, listSoluciones, emisor.tasaIva/100));
		    	    			
		    					
					} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
    			}
	       	}
    	}
    	return ok(mensajes.render("/",msgError));
	}
    
    
    public Result coti8columnas(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("");
	       	}else {
	       		try {
		       		Connection con = db.getConnection();
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			
	    			List<Equipo> listEquipo = Equipo.allVigentes(con, s.baseDato);
	    			Map<Long,Precio> mapPrecio = Precio.mapVigPorSucursal(con, s.baseDato, mapeoDiccionario, (long)1);
	    			Map<Long,Double> mapPeso = Atributo.mapAtributoPESO(con, s.baseDato);
	    			
	    			Fechas hoy = Fechas.hoy();
	    			Map<Long,Double> mapTasas = TasasCambio.mapTasasPorFecha(con, s.baseDato, hoy.getFechaStrAAMMDD(), mapeoDiccionario.get("pais"));
	    			Map<Long,Double> equivTiempo = UnidadTiempo.equivalencia(con, s.baseDato);
	    			
	    			File file = Coti8columnas.downloadPlantilla(s.baseDato, mapeoDiccionario, listEquipo, mapPrecio, mapPeso, mapTasas,equivTiempo);
	    			con.close();
	    			return ok(file,false,Optional.of("cotizacion8columnas.xlsx"));
	       		} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok("");
	       	}
		}else {
			return ok("");
		}
	}
    
    public Result coti8columnasValida(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
        	if (form.hasErrors()) {
    			return ok(mensajes.render("/",msgErrorFormulario));
    		}else {
    			
    			Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
    			String id_sucursal = form.get("id_sucursal").trim();
    			
        		Http.MultipartFormData<TemporaryFile> body = request.body().asMultipartFormData();
    			Http.MultipartFormData.FilePart<TemporaryFile> archivo = body.getFile("archivoXLSX");
    			if (archivo != null) {
    				File file = Archivos.parseMultipartFormDatatoFile(archivo);
					try {
		    			Connection con = db.getConnection();
		    			
		    			List<String> mensaje = Coti8columnas.validaPlantilla(con, s.baseDato, file);
		    			
		    			
		    			if(mensaje.get(0).equals("true")) {
		    				List<List<String>> listaExcel = Coti8columnas.llenaListaDesdeExcel(file);
		    				
		    				Map<String,List<String>> mapListaExcel = new HashMap<String,List<String>>();
		    				for(List<String> l: listaExcel){
		    					if(l.get(2).trim().equals("")) { l.set(2, "0"); }
		    					if(l.get(3).trim().equals("")) { l.set(3, "0"); }
		    					if(l.get(4).trim().equals("")) { l.set(4, "0"); }
		    					if(l.get(5).trim().equals("")) { l.set(5, "0"); }
		    					mapListaExcel.put(l.get(0), l);
		    				}
		    				
		    				if(mapListaExcel.size() == listaExcel.size()) {
		    					
		    					Long id_cotizacion = (long) 0;
		    					
		    	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		    	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		    	    			
		    	    			Long numeroCoti = Cotizacion.findNuevoNumero(con, s.baseDato);
		    	    			String fecha = Fechas.hoy().getFechaStrAAMMDD();
		    	    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
		    	    			FormCotiza formCotiza = new FormCotiza();
		    	    			if(bodega != null) {
		    	    				Cliente cliente = Cliente.find(con, s.baseDato, bodega.getId_cliente());
		    	    				String rutCliente = "";
		    	    				if(cliente!=null) {
		    	    					rutCliente = cliente.getRut();
		    	    				}
		    	    				formCotiza = new FormCotiza(numeroCoti, fecha, bodega.getId_cliente(), bodega.getId_proyecto(), bodega.getId(), 
		    	    						rutCliente, bodega.nickCliente, bodega.nickProyecto, bodega.nombre);
		    	    			} else {
		    	    				formCotiza = new FormCotiza(numeroCoti, fecha);
		    	    			}
		    	    			
		    	    			List<List<String>> listaConPrecio = FormCotiza.allConSuPrecio(con, s.baseDato, mapeoDiccionario.get("nEmpresa"), id_bodegaEmpresa, mapeoPermiso, id_sucursal, "0","0",(long)-1);
		    	    		
		    	    			List<Cliente> listClientes = Cliente.all(con, s.baseDato);
		    	    			List<Proyecto> listProyectos = Proyecto.all(con, s.baseDato);
		    	    			List<List<String>> listaDetCoti = FormCotiza.detalleCotizacion(con, s.baseDato, mapeoPermiso, mapeoDiccionario, id_bodegaEmpresa, id_cotizacion, s.id_sucursal,"0","0",
		    	    					listaConPrecio);
		    	    			String numDecParaTot = "0";
		    	    			if(listaDetCoti.size()>0) {
		    	    				numDecParaTot = listaDetCoti.get(listaDetCoti.size()-1).get(20);
		    	    			}
		    	    			formCotiza.id_cotizacion = id_cotizacion;
		    	    			
		    	    			List<Regiones> listRegiones = Regiones.all(con, s.baseDato);
		    	    			
		    	    			
		    	    			
		    	    			for(int i=0;i<listaConPrecio.size();i++){
		    	    				
		    	    				Long decimal = Long.parseLong(listaConPrecio.get(i).get(20));
		    	    					
		    	    					List<String> lexcel = mapListaExcel.get(listaConPrecio.get(i).get(1).toUpperCase());
		    	    					
		    	    					if(lexcel!=null) {
		    	    						
		    	    						//CANTIDAD
		    	    						Double cantDbl = (double) 0;
		    	    						String cantStr = "0.00";
		    	    						String auxStr = lexcel.get(1).trim();
		    	    						
		    	    						if(!auxStr.equals("")) {
		    	    							try {
		    	    								cantDbl =  Double.parseDouble(auxStr);
		    	    								if((double) cantDbl > (double) 0) {
		    	    									cantStr =  DecimalFormato.formato(cantDbl, (long)2);
		    	    								}else {
		    	    									cantDbl = (double) 1;
		    	    								}
		    	    							}catch(Exception e){}
		    	    						}
		    	    						listaConPrecio.get(i).set(10, cantStr);
		    	    						
		    	    						
		    	    						//ESVENTA
		    	    						String esVenta = "0";
		    	    						auxStr = lexcel.get(2).trim();
		    	    						if(auxStr.equals("1")) {
		    	    							esVenta = "1";
		    	    						}
		    	    						listaConPrecio.get(i).set(11, esVenta);
		    	    						
		    	    						
		    	    						//PRECIO VENTA Y TOTAL
		    	    						String puVentaStr = listaConPrecio.get(i).get(5);
		    	    						Double puVentaDbl = Double.parseDouble(puVentaStr.replaceAll(",", ""));
		    	    						
		    	    						auxStr = lexcel.get(3).trim();
		    	    						if(!auxStr.equals("")) {
		    	    							try {
		    	    								puVentaDbl = Double.parseDouble(auxStr);
		    	    								if((double)puVentaDbl > (double)0) {
		    	    									puVentaStr = DecimalFormato.formato(puVentaDbl, decimal);
		    	    								}
		    	    							}catch(Exception e){}
		    	    						}
		    	    						listaConPrecio.get(i).set(5, puVentaStr);
		    	    						
		    	    						Double totVenta = cantDbl * puVentaDbl;
		    	    						listaConPrecio.get(i).set(13, DecimalFormato.formato(totVenta, decimal));
		    	    						
		    	    						if(esVenta.equals("1")) {
		    	    							listaConPrecio.get(i).set(15, DecimalFormato.formato(totVenta, decimal));
		    	    						}
		    	    						
		    	    						
		    	    						//PRECIO ARRIENDO, TASA, PERMANENCIA Y TOTAL
		    	    						String puArrStr = listaConPrecio.get(i).get(7);
		    	    						Double puArrDbl = Double.parseDouble(puArrStr.replaceAll(",", ""));
		    	    						auxStr = lexcel.get(4).trim();
		    	    						if(!auxStr.equals("")) {
		    	    							try {
		    	    								puArrDbl = Double.parseDouble(auxStr);
		    	    								if((double)puArrDbl > (double)0) {
		    	    									puArrStr = DecimalFormato.formato(puArrDbl, decimal);
		    	    								}
		    	    							}catch(Exception e){}
		    	    						}
		    	    						listaConPrecio.get(i).set(7, puArrStr);
		    	    						
		    	    						Double permDbl = (double)1;
		    	    						String permStr = "1.00";
		    	    						auxStr = lexcel.get(5).trim();
		    	    						if(!auxStr.equals("")) {
		    	    							try {
		    	    								permDbl = Double.parseDouble(auxStr);
		    	    								if((double)permDbl > (double)0) {
		    	    									permStr = DecimalFormato.formato(permDbl, (long)2);
		    	    								}
		    	    							}catch(Exception e){}
		    	    						}
		    	    						listaConPrecio.get(i).set(12, permStr);
		    	    						
		    	    						if(esVenta.equals("0")) {
			    	    						Double totArr = cantDbl * puArrDbl * permDbl;
			    	    						listaConPrecio.get(i).set(14, DecimalFormato.formato(totArr, decimal));
		    	    						}
		    	    						
		    	    						Double tasaDbl = (double) 0;
		    	    						if((double)puVentaDbl > (double)0) {
		    	    							tasaDbl = puArrDbl/puVentaDbl;
		    	    						}
		    	    						
		    	    						String tasaArriendo = myformatdouble2.format(tasaDbl*100)+" %";
			    	    					listaConPrecio.get(i).set(24,tasaArriendo); 									//tasa arr
		    	    						
			    	    					//KG Y M2 TOTALES
			    	    					Double kg = Double.parseDouble(listaConPrecio.get(i).get(16)) * cantDbl;
			    	    					Double m2 = Double.parseDouble(listaConPrecio.get(i).get(17)) * cantDbl;
			    	    					
			    	    					listaConPrecio.get(i).set(18,DecimalFormato.formato(kg, (long)2)); 
			    	    					listaConPrecio.get(i).set(19,DecimalFormato.formato(m2, (long)2)); 
		    	    						
		    	    					}
		    	    					
		    	    			}
		    	    			
		    	    			for(List<String> l: listaConPrecio) {
		    	    				l.add(l.get(10).replaceAll(",", ""));
		    	    				l.add(l.get(5).replaceAll(",", ""));
		    	    				l.add(l.get(24).replaceAll(",", "").replaceAll("%", ""));
		    	    				l.add(l.get(7).replaceAll(",", ""));
		    	    				l.add(l.get(12).replaceAll(",", ""));
		    	    				
		    	    				l.add(l.get(13).replaceAll(",", ""));
		    	    				l.add(l.get(14).replaceAll(",", ""));
		    	    				l.add(l.get(15).replaceAll(",", ""));
		    	    				l.add(l.get(18).replaceAll(",", ""));
		    	    				l.add(l.get(19).replaceAll(",", ""));
		    	    			}
		    	    		
		    	    			List<UnidadTiempo> listUnTiempo = UnidadTiempo.all(con, s.baseDato);
		    	    			
		    	    			
		    	    			Comercial comercial = new Comercial();
		    	    			Comercial auxComercial = Comercial.findPorIdUsuario(con, s.baseDato, s.id_usuario);
		    	    			if(auxComercial==null) {
		    	    				comercial.setId((long)0);
		    	    				comercial.setNameUsuario("");
		    	    				if(mapeoPermiso.get("cambiarComercial")!=null) {
		    	    					comercial.setNameUsuario("-- select comercial --");
		    	    				}
		    	    			}else {
		    	    				comercial = auxComercial;
		    	    			}
		    	    			
		    	    			
		    	    			List<Comercial> listComercial = new ArrayList<Comercial>();
		    	    			if(mapeoPermiso.get("cambiarComercial")!=null) {
		    	    				listComercial = Comercial.allPorIdSucursalVig(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
		    	    			}
		    	    			
		    	    			List<Sucursal> listSucursal = new ArrayList<Sucursal>();
		    	    			if(mapeoPermiso.get("cambiarSucursal")!=null) {
		    	    				listSucursal = Sucursal.all(con, s.baseDato);
		    	    			}
		    	    			
		    	    			Sucursal sucursal = Sucursal.find(con, s.baseDato, s.id_sucursal);
		    	    			
		    	    			List<CotizaSolucion> listSoluciones = CotizaSolucion.all(con, s.baseDato);
		    	    			
		    	    			String importDesdeExcel = "1";
		    	    			
		    	    			String jsonDetalle = Json.toJson(listaConPrecio).toString();
		    	    			String jsonListUnTiempo = Json.toJson(listUnTiempo).toString();
		    	    			
		    	    			EmisorTributario emisor = EmisorTributario.find(con, s.baseDato);
		    	    			
		    	    			con.close();
		    	    			return ok(cotizaIngreso2.render(mapeoDiccionario,mapeoPermiso,userMnu,formCotiza,listClientes,listProyectos,
		    	    					numDecParaTot,listRegiones, jsonListUnTiempo, sucursal, comercial, listSucursal,listComercial, importDesdeExcel,
		    	    					jsonDetalle, listSoluciones, emisor.tasaIva/100));
		    	    			
		    				}else {
		    					con.close();
								return ok(mensajes.render("/cotizaIngreso2/"+id_bodegaEmpresa, "Existen codigos duplicados en el archivo"));
		    				}
		    				
		    			}else {
		    				String msg = "";
							for(String m: mensaje) {
								msg += m + "<br>";
							}
							con.close();
							return ok(mensajes.render("/cotizaIngreso2/"+id_bodegaEmpresa, msg));
		    			}
					} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
    			}
	       	}
    	}
    	return ok(mensajes.render("/",msgError));
	}
    

    
    

}






