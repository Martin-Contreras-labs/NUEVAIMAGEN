package controllers;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import controllers.HomeController.Sessiones;
import models.calculo.Calc_AjustesEpOdo;
import models.forms.FormFactura;
import models.forms.FormServicioGraba;
import models.reports.ReportOdo;
import models.reports.ReportOdoConsolidado;
import models.reports.ReportOdoMovimientos;
import models.reports.ReportOdoOperadores;
import models.tables.AjustesEpOdo;
import models.tables.AuxHuella;
import models.tables.BodegaEmpresa;
import models.tables.ClaseServicio;
import models.tables.Cliente;
import models.tables.Equipo;
import models.tables.EquipoServicio;
import models.tables.ListaPrecioServicio;
import models.tables.Moneda;
import models.tables.OperadorServicio;
import models.tables.PrecioVariableServicio;
import models.tables.ProformaOdo;
import models.tables.Proyecto;
import models.tables.Servicio;
import models.tables.TasasCambio;
import models.tables.TipoAjustes;
import models.tables.TipoReferencia;
import models.tables.UnidadServicio;
import models.tables.Usuario;
import models.tables.UsuarioPermiso;
import models.tables.VentaServicio;
import models.utilities.Archivos;
import models.utilities.DecimalFormato;
import models.utilities.Fechas;
import models.utilities.Registro;
import models.utilities.UserMnu;
import models.xml.XmlFacturaReferencias;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.Database;
import play.libs.Files.TemporaryFile;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.mensajes;
import viewsMnuOdo.html.*;

public class MnuOdo extends Controller {
	public static Database db = HomeController.dbWrite;
	public static FormFactory formFactory = HomeController.formFactory;
	public static String msgError = HomeController.msgError;
	public static String msgErrorFormulario = HomeController.msgErrorFormulario;
	public static String msgSinPermiso = HomeController.msgSinPermiso;
	
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	
	
	//============================================================
    // MNU odoAdminClases   Odo/Clases
    //============================================================
	
	 public Result claseMantencion(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("odoAdminClases")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<ClaseServicio> listClases = ClaseServicio.all(con, s.baseDato);
    			con.close();
    			return ok(claseMantencion.render(mapeoDiccionario,mapeoPermiso,userMnu,listClases));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    	return ok(mensajes.render("/",msgError));
    }
	
	 public Result claseElimina(Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
	    		DynamicForm form = formFactory.form().bindFromRequest(request);
		   		if (form.hasErrors()) {
		   			return ok(mensajes.render("/",msgErrorFormulario));
		       	}else {
		    	  	Long id_clase = Long.parseLong(form.get("id_clase").trim());
		    		try {
		    			Connection con = db.getConnection();
		    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		    			if(mapeoPermiso.get("odoAdminClases")==null) {
		    				con.close();
		    				return ok(mensajes.render("/",msgSinPermiso));
		    			}
		    			if(ClaseServicio.estaEnUso(con, s.baseDato, id_clase)) {
		    				String msg = "No es posible eliminar esta clase, esta en uso con servicios vigentes ";
		    				con.close();
		    				return ok(mensajes.render("/claseMantencion/",msg));
		    			}else {
		    				ClaseServicio clase = ClaseServicio.find(con, s.baseDato, id_clase);
		    				if(ClaseServicio.delete(con, s.baseDato, id_clase)) {
		    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "claseServicio", id_clase, "delete", "elimina familia servicio: "+clase.getNombre());
		    					con.close();
		        				return redirect("/claseMantencion/");
		    				}else {
		    					con.close();
		    					return ok(mensajes.render("/",msgError));
		    				}
		    			}
		        	} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
		       	}
	    	}
	    	return ok(mensajes.render("/",msgError));
	    }
	    
	    public Result claseModifica(Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
	    		DynamicForm form = formFactory.form().bindFromRequest(request);
		   		if (form.hasErrors()) {
		   			return ok(mensajes.render("/",msgErrorFormulario));
		       	}else {
		    	  	Long id_clase = Long.parseLong(form.get("id_clase").trim());
		    		try {
		    			Connection con = db.getConnection();
		    			
		    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		    			if(mapeoPermiso.get("odoAdminClases")==null) {
		    				con.close();
		    				return ok(mensajes.render("/",msgSinPermiso));
		    			}
		    			ClaseServicio clase = ClaseServicio.find(con, s.baseDato, id_clase);
		    			con.close();
		    			return ok(claseModifica.render(mapeoDiccionario,mapeoPermiso,userMnu,clase));
		        	} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
		       	}
	    	}
	    	return ok(mensajes.render("/",msgError));
	    }
	    
	    public Result claseAgrega(Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("odoAdminClases")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			con.close();
	    			return ok(claseAgrega.render(mapeoDiccionario,mapeoPermiso,userMnu));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    	}
	    	return ok(mensajes.render("/",msgError));
	    }
	    
	    public Result claseNuevo(Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
	    		DynamicForm form = formFactory.form().bindFromRequest(request);
		   		if (form.hasErrors()) {
		   			return ok(mensajes.render("/",msgErrorFormulario));
		       	}else {
		       		String nombreClase = form.get("nombreClase").trim();
					try {
		    			Connection con = db.getConnection();
		    			if(ClaseServicio.existeClase(con, s.baseDato, nombreClase)) {
		    				String msg = "No es posible crear esta clase, el nombre de familia ya existe.";
		    				con.close();
		    				return ok(mensajes.render("/claseMantencion/",msg));
		    			}else {
		    				if(ClaseServicio.create(con, s.baseDato, nombreClase)) {
		    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "claseServicio", (long)0, "create", "agrega nueva familia servicio: "+nombreClase);
		    					con.close();
		        				return redirect("/claseMantencion/");
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
	    
	    public Result modificaClasePorCampoAjax(Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
	    		DynamicForm form = formFactory.form().bindFromRequest(request);
		   		if (form.hasErrors()) {
		   			return ok(mensajes.render("/",msgErrorFormulario));
		       	}else {
		       		String campo = form.get("campo").trim();
		       		Long id_clase = Long.parseLong(form.get("id_clase").trim());
		       		String valor = form.get("valor").trim();
					try {
		    			Connection con = db.getConnection();
		    			if(ClaseServicio.existeClase(con, s.baseDato, valor)) {
		    				con.close();
	    	    			return ok("existe");
		    			}else {
		    				if(ClaseServicio.modificaPorCampo(con, s.baseDato, campo, id_clase, valor)) {
		    					ClaseServicio clase = ClaseServicio.find(con, s.baseDato, id_clase);
		    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "claseServicio", id_clase, "update", "cambia nombre de la familia servicio a: "+clase.getNombre());
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
	    
	    //============================================================
	    // MNU odoAdminServicios   Odo/Servicios
	    //============================================================
	    
	    public Result servicioMantencion(Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("odoAdminServicios")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			List<Servicio> listServicios = Servicio.all(con, s.baseDato);
	    			
	    			List<List<String>> listado = new ArrayList<List<String>>();
	    			
	    			listServicios.forEach(x->{
	    				String precio = DecimalFormato.formato(x.getPrecio(), x.getNroDecimal());
	    				String cantMinimo = DecimalFormato.formato(x.getCantMinimo(), (long)2);
	    				String precioAdicional = DecimalFormato.formato(x.getPrecioAdicional(), x.getNroDecimal());
	    				
	    				List<String> aux = new ArrayList<String>();
	    				/*0*/ aux.add(x.getNombreClase());
	    				/*1*/ aux.add(x.getCodigo());
	    				/*2*/ aux.add(x.getNombre());
	    				/*3*/ aux.add(x.getNombreUnidad());
	    				/*4*/ aux.add(x.getNickMoneda());
	    				/*5*/ aux.add(precio);
	    				/*6*/ aux.add(x.getFecha());
	    				/*7*/ aux.add(x.getNroDecimal().toString());
	    				/*8*/ aux.add(x.getId().toString());
	    				/*9*/ aux.add(cantMinimo);
	    				/*10*/ aux.add(precioAdicional);
	    				listado.add(aux);
	    			});
	    			
	    			Fechas hoy = Fechas.hoy();
	    			con.close();
	    			return ok(servicioMantencion.render(mapeoDiccionario,mapeoPermiso,userMnu,listado,hoy));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    	}
	    	return ok(mensajes.render("/",msgError));
	    }
	    
	    public Result servicioNuevo(Long id_clase, Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("odoAdminServicios")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			List<ClaseServicio> listClases = ClaseServicio.all(con, s.baseDato);
	    			List<UnidadServicio> listUnidades = UnidadServicio.all(con, s.baseDato);
	    			List<Moneda> listMonedas = Moneda.all(con, s.baseDato);
	    			con.close();
	    			return ok(servicioNuevo.render(mapeoDiccionario,mapeoPermiso,userMnu,listClases,listUnidades,listMonedas));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    	}
	    	return ok(mensajes.render("/",msgError));
	    }
	    
	    public Result verificaCodigoServicioAjax(Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
	    		DynamicForm form = formFactory.form().bindFromRequest(request);
		   		if (form.hasErrors()) {
		   			return ok("error");
		       	}else {
		    	  	String codigo = form.get("codigo").trim();
					try {
		    			Connection con = db.getConnection();
		    				if(Servicio.existeCodigo(con, s.baseDato, codigo)) {
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
	    
	    public Result servicioGraba(Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
	    		FormServicioGraba form = formFactory.form(FormServicioGraba.class).withDirectFieldAccess(true).bindFromRequest(request).get();
	    		if (form.codigo==null) {
	    			return ok(mensajes.render("/",msgErrorFormulario));
	    		}else {
	    			try {
		    			Connection con = db.getConnection();
		    			if(Servicio.create(con, s.baseDato, form)) {
		    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "servicio", (long)0, "create", "crea nuevo servicio: "+form.codigo);
		    				con.close();
		    				return redirect("/servicioMantencion/");
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
	    
	    public Result servicioModifica(Long id_servicio, Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("odoAdminServicios")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			List<ClaseServicio> listClases = ClaseServicio.all(con, s.baseDato);
	    			List<UnidadServicio> listUnidades = UnidadServicio.all(con, s.baseDato);
	    			List<Moneda> listMonedas = Moneda.all(con, s.baseDato);
	    			Servicio servicio = Servicio.find(con, s.baseDato, id_servicio);
	    			con.close();
	    			return ok(servicioModifica.render(mapeoDiccionario,mapeoPermiso,userMnu,listClases,listUnidades,listMonedas,servicio));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    	}
	    	return ok(mensajes.render("/",msgError));
	    }
	    
	    public Result servicioUpdate(Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
	    		FormServicioGraba form = formFactory.form(FormServicioGraba.class).withDirectFieldAccess(true).bindFromRequest(request).get();
	    		if (form.codigo==null) {
	    			return ok(mensajes.render("/",msgErrorFormulario));
	    		}else {
	    			try {
		    			Connection con = db.getConnection();
		    			if(Servicio.update(con, s.baseDato, form)) {
		    				if(form.cambioElprecio.equals("1")) {
		    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "servicio", form.id, "update", "modifica el precio del servicio: "+form.codigo);
		    				}else {
		    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "servicio", form.id, "update", "modifica servicio: "+form.codigo);
		    				}
		    				con.close();
		    				return redirect("/servicioMantencion/");
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
	    
	    public Result servicioElimina(Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
	    		DynamicForm form = formFactory.form().bindFromRequest(request);
		   		if (form.hasErrors()) {
		   			return ok(mensajes.render("/",msgErrorFormulario));
		       	}else {
		       		Long id_servicio = Long.parseLong(form.get("id_servicio").trim());
		    		try {
		    			Connection con = db.getConnection();
		    			Servicio servicio = Servicio.find(con, s.baseDato, id_servicio);
		    			if(!Servicio.estaEnUso(con, s.baseDato, id_servicio)) {
		    				if(Servicio.delete(con, s.baseDato, id_servicio)) {
		    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "servicio", id_servicio, "delete", "elimina el servicio codigo: "+servicio.getCodigo());
		    					con.close();
		    					return redirect("/servicioMantencion/");
		    				}else {
		    					con.close();
			    				return ok(mensajes.render("/",msgError));
		    				}
		    			}else {
		    				String msg = "No es posible eliminar este servicio, esta en uso en el sistema";
		    				con.close();
		    				return ok(mensajes.render("/servicioMantencion/",msg));
		    			}
		        	} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
		       	}
	    	}
	    	return ok(mensajes.render("/",msgError));
	    }
	    
	    public Result modPrecioServicioAjax(Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
	    		DynamicForm form = formFactory.form().bindFromRequest(request);
		   		if (form.hasErrors()) {
		   			return ok("error");
		       	}else {
		       		Long id_servicio = Long.parseLong(form.get("id_servicio").trim());
		       		String valor = form.get("valor").replaceAll(",", "").trim();
		       		String fecha = form.get("fecha").trim();
		       		String concepto = form.get("concepto").trim();
		       		
					try {
		    			Connection con = db.getConnection();
		    			if(concepto.equals("precio")) {
		    				if(Servicio.modifyPrecio(con, s.baseDato, id_servicio, valor, fecha)) {
		    					Servicio servicio = Servicio.find(con, s.baseDato, id_servicio);
		    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "servicio", id_servicio, "update", "modifica precio servicio codigo: "+servicio.getCodigo());
				    			con.close();
		    	    			return ok("OK");
		    				}
		    			}
		    			if(concepto.equals("cantMinimo")) {
		    				if(Servicio.modifyCantMinimo(con, s.baseDato, id_servicio, valor, fecha)) {
		    					Servicio servicio = Servicio.find(con, s.baseDato, id_servicio);
		    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "servicio", id_servicio, "update", "modifica precio servicio codigo: "+servicio.getCodigo());
				    			con.close();
		    	    			return ok("OK");
		    				}
		    			}
		    			if(concepto.equals("precioAdicional")) {
		    				if(Servicio.modifyPrecioAdicional(con, s.baseDato, id_servicio, valor, fecha)) {
		    					Servicio servicio = Servicio.find(con, s.baseDato, id_servicio);
		    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "servicio", id_servicio, "update", "modifica precio servicio codigo: "+servicio.getCodigo());
				    			con.close();
		    	    			return ok("OK");
		    				}
		    			}
		    			
		    			con.close();
    					return ok("error");
		    				
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
	    // MNU odoAdminPrecios   Odo/Precios por bodega
	    //============================================================
	    
	    public Result servicioPrecios(Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("odoAdminPrecios")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			
	    			List<List<String>> listBodegas = BodegaEmpresa.listaAllBodegasVigentesExternas(con, s.baseDato, mapeoPermiso, s.aplicaPorSucursal, s.id_sucursal);
	    			con.close();
	    			return ok(servicioPrecios.render(mapeoDiccionario,mapeoPermiso,userMnu,listBodegas));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    	}
	    	return ok(mensajes.render("/",msgError));
	    }
	    
	    public Result servicioListaPrecios(Long id_bodegaEmpresa, Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("odoAdminPrecios")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
	    			List<ListaPrecioServicio> listPrecios = ListaPrecioServicio.allXBodega(con, s.baseDato, bodega);
	    			List<Moneda> listMoneda = Moneda.all(con, s.baseDato);
	    			List<Servicio> listServicioNoBod = Servicio.allNoEnBodega(con, s.baseDato, bodega, listPrecios);
	    			con.close();
	    			return ok(servicioListaPrecios.render(mapeoDiccionario,mapeoPermiso,userMnu,bodega,listPrecios,listMoneda,listServicioNoBod));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    	}
	    	return ok(mensajes.render("/",msgError));
	    }
	    
	    public Result servicioAgregaPrecio(Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
	    		DynamicForm form = formFactory.form().bindFromRequest(request);
		   		if (form.hasErrors()) {
		   			return ok(mensajes.render("/",msgErrorFormulario));
		       	}else {
		       		Long id_servicio = Long.parseLong(form.get("id_servicio").trim());
		       		Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
		       		Long id_cotiOdo = Long.parseLong(form.get("id_cotiOdo").trim());
		    		try {
		    			Connection con = db.getConnection();
		    			ListaPrecioServicio.insert(con, s.baseDato, id_servicio, id_bodegaEmpresa, id_cotiOdo);
		    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
		    			Servicio servicio = Servicio.find(con, s.baseDato, id_servicio);
		    			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "listaPrecioServicio", id_servicio, "insert", "agrega precio a bodega: "+bodega.nombre+" con id_bodega: "+id_bodegaEmpresa+" y servicio: "+servicio.getNombre());
		    			con.close();
		    			return redirect("/servicioListaPrecios/"+id_bodegaEmpresa);
		        	} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
		       	}
	    	}
	    	return ok(mensajes.render("/",msgError));
	    }
	    
	    public Result modPorCampoListaPServicioAjax(Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
	    		DynamicForm form = formFactory.form().bindFromRequest(request);
		   		if (form.hasErrors()) {
		   			return ok("error");
		       	}else {
		       		Long id_servicio = Long.parseLong(form.get("id_servicio").trim());
		       		Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
		       		Long id_cotiOdo = Long.parseLong(form.get("id_cotiOdo").trim());
		       		String campo = form.get("campo").trim();
		       		String valor = form.get("valor").replaceAll(",", "").trim();
					try {
		    			Connection con = db.getConnection();
		    				if(ListaPrecioServicio.modifyPorCampo(con, s.baseDato, campo, valor, id_bodegaEmpresa, id_servicio, id_cotiOdo)) {
		    					BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
		    					ListaPrecioServicio precio = ListaPrecioServicio.find(con, s.baseDato, bodega, id_servicio);
		    					int dec = Moneda.numeroDecimalxId(con, s.baseDato, precio.getId_moneda().toString());
		    					String rs = precio.getFecha()+"-"+dec;
		    					Servicio servicio = Servicio.find(con, s.baseDato, id_servicio);
		    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "listaPrecioServicio", id_servicio, "update", "actualiza: "+campo+" en bodega: "+bodega.nombre+" con "+id_bodegaEmpresa+" y servicio: "+servicio.getNombre());
		    	    			con.close();
		    	    			return ok(rs);
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
	    
	    //============================================================
	    // MNU odoAdminPrecios   Odo/Precios por bodega
	    //============================================================
	    
	    public Result servicioPreciosServicio(Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("odoAdminPrecios")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			List<Servicio> listServicios = Servicio.all(con, s.baseDato);
	    			con.close();
	    			return ok(servicioPreciosServicio.render(mapeoDiccionario,mapeoPermiso,userMnu,listServicios));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    	}
	    	return ok(mensajes.render("/",msgError));
	    }
	    
	    public Result servicioListaPreciosServicio(Long id_servicio, Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("odoAdminPrecios")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			List<List<String>> listBodegas = BodegaEmpresa.listaAllBodegasVigentesExternas(con, s.baseDato, mapeoPermiso, s.aplicaPorSucursal, s.id_sucursal);
	    			Servicio servicio = Servicio.find(con, s.baseDato, id_servicio);
	    			List<ListaPrecioServicio> listPrecios = ListaPrecioServicio.allXServicio(con, s.baseDato, id_servicio);
	    			List<List<String>> listBodegasNoServ = Servicio.allBodegaNoEnSevicio(con, s.baseDato, listBodegas, id_servicio);
	    			List<Moneda> listMoneda = Moneda.all(con, s.baseDato);
	    			con.close();
	    			return ok(servicioListaPreciosServicio.render(mapeoDiccionario,mapeoPermiso,userMnu,servicio,listPrecios,listMoneda,listBodegasNoServ));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    	}
	    	return ok(mensajes.render("/",msgError));
	    }
	    
	    public Result servicioAgregaPrecioServicio(Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
	    		DynamicForm form = formFactory.form().bindFromRequest(request);
		   		if (form.hasErrors()) {
		   			return ok(mensajes.render("/",msgErrorFormulario));
		       	}else {
		       		Long id_servicio = Long.parseLong(form.get("id_servicio").trim());
		       		Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
		       		Long id_cotiOdo = Long.parseLong(form.get("id_cotiOdo").trim());
		    		try {
		    			Connection con = db.getConnection();
		    			ListaPrecioServicio.insert(con, s.baseDato, id_servicio, id_bodegaEmpresa, id_cotiOdo);
		    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
		    			Servicio servicio = Servicio.find(con, s.baseDato, id_servicio);
		    			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "listaPrecioServicio", id_servicio, "insert", "agrega precio a bodega: "+bodega.nombre+" con id_bodega: "+id_bodegaEmpresa+" y servicio: "+servicio.getNombre());
		    			con.close();
		    			return redirect("/routes2/servicioListaPreciosServicio/"+id_servicio);
		        	} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
		       	}
	    	}
	    	return ok(mensajes.render("/",msgError));
	    }
	    
	    //============================================================
	    // MNU odoAdminPrecios   Odo/Precios variables
	    //============================================================
	    
	    public Result servicioPreciosVariable0(Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("odoAdminPrecios")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			
	    			List<List<String>> listBodegas = BodegaEmpresa.listaAllBodegasVigentesExternas(con, s.baseDato, mapeoPermiso, s.aplicaPorSucursal, s.id_sucursal);
	    			con.close();
	    			return ok(servicioPreciosVariable0.render(mapeoDiccionario,mapeoPermiso,userMnu,listBodegas));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    	}
	    	return ok(mensajes.render("/",msgError));
	    }
	    
	    public Result servicioPreciosVariable1(Long id_bodegaEmpresa, Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("odoAdminPrecios")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
	    			List<ListaPrecioServicio> listLPServicios = ListaPrecioServicio.listaServiciosVariablesXBodega(con, s.baseDato, bodega);
	    			con.close();
	    			return ok(servicioPreciosVariable1.render(mapeoDiccionario,mapeoPermiso,userMnu,bodega,listLPServicios));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    	}
	    	return ok(mensajes.render("/",msgError));
	    }
	    
	    public Result servicioPreciosVariable2(Long id_bodegaEmpresa, Long id_servicio, Long id_cotiOdo, Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("odoAdminPrecios")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
	    			ListaPrecioServicio listaPrecioServicio = ListaPrecioServicio.find(con, s.baseDato, bodega, id_servicio);
	    			Map<Long,Moneda> mapMoneda = Moneda.mapMonedas(con, s.baseDato);
	    			Moneda moneda = mapMoneda.get(listaPrecioServicio.getId_moneda());
	    			Long decMon = (long) 0;
	    			if(moneda!=null) {
	    				decMon = moneda.getNumeroDecimales();
	    			}
	    			List<PrecioVariableServicio> listPVariable = PrecioVariableServicio.allXBodXServ(con, s.baseDato, id_bodegaEmpresa, id_servicio, decMon, id_cotiOdo);
	    			Fechas hoy = Fechas.hoy();
	    			con.close();
	    			return ok(servicioPreciosVariable2.render(mapeoDiccionario,mapeoPermiso,userMnu,bodega,listaPrecioServicio,listPVariable,decMon,hoy));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    	}
	    	return ok(mensajes.render("/",msgError));
	    }
	    
	    public Result servicioAgregaVariable(Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
	    		DynamicForm form = formFactory.form().bindFromRequest(request);
		   		if (form.hasErrors()) {
		   			return ok(mensajes.render("/",msgErrorFormulario));
		       	}else {
		       		Long id_servicio = Long.parseLong(form.get("id_servicio").trim());
		       		Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodega").trim());
		       		String fecha = form.get("fecha").trim();
		       		Double precio = Double.parseDouble(form.get("precio").trim().replaceAll(",", ""));
		       		Long id_cotiOdo = Long.parseLong(form.get("id_cotiOdo").trim());
		    		try {
		    			Connection con = db.getConnection();
		    			PrecioVariableServicio.insert(con, s.baseDato, id_bodegaEmpresa, id_servicio, fecha, precio, id_cotiOdo);
		    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
		    			Servicio servicio = Servicio.find(con, s.baseDato, id_servicio);
		    			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "precioVariableServicio", id_servicio, "insert", "agrega precio variable en bodega: "+bodega.nombre+" con "+id_bodegaEmpresa+", id_cotiOdo: "+id_cotiOdo+" y servicio: "+servicio.getNombre());
		    			con.close();
		    			return redirect("/servicioPreciosVariable2/"+id_bodegaEmpresa+","+id_servicio+","+id_cotiOdo);
		        	} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
		       	}
	    	}
	    	return ok(mensajes.render("/",msgError));
	    	
	    }
	    
	    public Result modPrecioVariableServicioAjax(Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
	    		DynamicForm form = formFactory.form().bindFromRequest(request);
		   		if (form.hasErrors()) {
		   			return ok("error");
		       	}else {
		       		Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodega").trim());
		       		Long id_servicio = Long.parseLong(form.get("id_servicio").trim());
		       		String valor = form.get("valor").replaceAll(",", "").trim();
		       		String fecha = form.get("fecha").trim();
		       		Long id_cotiOdo = Long.parseLong(form.get("id_cotiOdo").trim());
					try {
		    			Connection con = db.getConnection();
		    				if(PrecioVariableServicio.modifyPrecio(con, s.baseDato, id_bodegaEmpresa, id_servicio, valor, fecha, id_cotiOdo)) {
		    					BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
				    			Servicio servicio = Servicio.find(con, s.baseDato, id_servicio);
		    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "precioVariableServicio", id_servicio, "update", "modifica precio variable en bodega: "+bodega.nombre+" con "+id_bodegaEmpresa+", id_cotiOdo: "+id_cotiOdo+" y servicio: "+servicio.getNombre());
				    			con.close();
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
	    
	    public Result servicioEliminaVariable(Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
	    		DynamicForm form = formFactory.form().bindFromRequest(request);
		   		if (form.hasErrors()) {
		   			return ok(mensajes.render("/",msgErrorFormulario));
		       	}else {
		       		Long id_servicio = Long.parseLong(form.get("id_servicio").trim());
		       		Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodega").trim());
		       		String fecha = form.get("fecha").trim();
		       		Long id_cotiOdo = Long.parseLong(form.get("id_cotiOdo").trim());
		    		try {
		    			Connection con = db.getConnection();
		    			PrecioVariableServicio.delete(con, s.baseDato, id_bodegaEmpresa, id_servicio, fecha, id_cotiOdo);
		    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
		    			Servicio servicio = Servicio.find(con, s.baseDato, id_servicio);
		    			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "precioVariableServicio", id_servicio, "insert", "agrega precio variable en bodega: "+bodega.nombre+" con "+id_bodegaEmpresa+", id_cotiOdo: "+id_cotiOdo+" y servicio: "+servicio.getNombre());
		    			con.close();
		    			return redirect("/servicioPreciosVariable2/"+id_bodegaEmpresa+","+id_servicio+","+id_cotiOdo);
		        	} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
		       	}
	    	}
	    	return ok(mensajes.render("/",msgError));
	    }
	    
	    //============================================================
	    // MNU odoAdminPrecios   Odo/Inscribir Equipos
	    //============================================================
	    
	    public Result servicioEquipos0(Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("odoAdminPrecios")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			List<EquipoServicio> listEquipos = EquipoServicio.allInscritos(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
	    			con.close();
	    			return ok(servicioEquipos0.render(mapeoDiccionario,mapeoPermiso,userMnu,listEquipos));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    	}
	    	return ok(mensajes.render("/",msgError));
	    }
	    
	    
	    public Result modVigEquipoServicioAjax(Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
	    		DynamicForm form = formFactory.form().bindFromRequest(request);
		   		if (form.hasErrors()) {
		   			return ok("error");
		       	}else {
		       		Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodega").trim());
		       		Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
		       		Long valor = Long.parseLong(form.get("valor").trim());
					try {
		    			Connection con = db.getConnection();
		    				if(EquipoServicio.modifyVigencia(con, s.baseDato, id_bodegaEmpresa, id_equipo, valor)) {
		    					BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
				    			Equipo equipo = Equipo.find(con, s.baseDato, id_equipo);
		    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "equipoServicio", id_equipo, "update", "modifica vigencia equipo codigo: "+equipo.getCodigo()+" a bodega: "+bodega.getNombre()+" con id_bodega: "+id_bodegaEmpresa);
				    			con.close();
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
	    
	    public Result servicioEquipos1(Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("odoAdminPrecios")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			
	    			List<List<String>> listBodegas = BodegaEmpresa.listaAllBodegasVigentesExternas(con, s.baseDato, mapeoPermiso, s.aplicaPorSucursal, s.id_sucursal);
	    			con.close();
	    			return ok(servicioEquipos1.render(mapeoDiccionario,mapeoPermiso,userMnu,listBodegas));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    	}
	    	return ok(mensajes.render("/",msgError));
	    }
	    
	    public Result servicioEquipos2(Long id_bodegaEmpresa, Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("odoAdminPrecios")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
	    			List<EquipoServicio> listEqInscritos = EquipoServicio.allInscritosPorBod(con, s.baseDato, id_bodegaEmpresa);
	    			List<EquipoServicio> listEqNoInscritos = EquipoServicio.allNoInscritosPorBod(con, s.baseDato, bodega);
	    			con.close();
	    			return ok(servicioEquipos2.render(mapeoDiccionario,mapeoPermiso,userMnu,bodega,listEqInscritos,listEqNoInscritos));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    	}
	    	return ok(mensajes.render("/",msgError));
	    }
	    
	    public Result servicioEquipo3(Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
	    		DynamicForm form = formFactory.form().bindFromRequest(request);
		   		if (form.hasErrors()) {
		   			return ok(mensajes.render("/",msgErrorFormulario));
		       	}else {
		       		Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
		       		Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
		    		try {
		    			Connection con = db.getConnection();
		    			EquipoServicio.insert(con, s.baseDato, id_bodegaEmpresa, id_equipo);
		    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
		    			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "equipoServicio", id_equipo, "insert", "agrega equipo servicio a bodega: "+bodega.getNombre()+" con id_bodega: "+id_bodegaEmpresa);
		    			con.close();
		    			return redirect("/servicioEquipos2/"+id_bodegaEmpresa);
		        	} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
		       	}
	    	}
	    	return ok(mensajes.render("/",msgError));
	    }
	    
	    //============================================================
	    // MNU odoAdminOperadores   Odo/Operadores
	    //============================================================
	    
	    public Result operadorMantencion(Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("odoAdminOperadores")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			List<OperadorServicio> listOperadores = OperadorServicio.all(con, s.baseDato);
	    			con.close();
	    			return ok(operadorMantencion.render(mapeoDiccionario,mapeoPermiso,userMnu,listOperadores));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    	}
	    	return ok(mensajes.render("/",msgError));
	    }
	    
	    public Result operadorNuevo(Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("odoAdminOperadores")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			List<Usuario> listUsuario = Usuario.all(con, s.baseDato);
	    			con.close();
	    			return ok(operadorNuevo.render(mapeoDiccionario,mapeoPermiso,userMnu,listUsuario));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    	}
	    	return ok(mensajes.render("/",msgError));
	    }
	    
	    public Result verificaRutOperadorAjax(Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
	    		DynamicForm form = formFactory.form().bindFromRequest(request);
		   		if (form.hasErrors()) {
		   			return ok("error");
		       	}else {
		       		String rut = form.get("rut").trim();
					try {
		    			Connection con = db.getConnection();
		    				if(OperadorServicio.existeRut(con, s.baseDato, rut)) {
				    			con.close();
		    	    			return ok("existe");
		    				}else {
		    					con.close();
		    					return ok("ok");
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
	    
	    public Result operadorGraba(Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
	    		OperadorServicio form = formFactory.form(OperadorServicio.class).withDirectFieldAccess(true).bindFromRequest(request).get();
	    		if (form.rut==null) {
	    			return ok(mensajes.render("/",msgErrorFormulario));
	    		}else {
	    			try {
		    			Connection con = db.getConnection();
		    			OperadorServicio.create(con, s.baseDato, form);
		    			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "operadorServicio", (long)0, "insert", "agrega operador: "+form.getNombre());
	    				con.close();
	    				return redirect("/operadorMantencion/");
	    			} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
	    		}
	    	}
	    	return ok(mensajes.render("/",msgError));
	    }
	    
	    public Result modVigOperadorServicioAjax(Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
	    		DynamicForm form = formFactory.form().bindFromRequest(request);
		   		if (form.hasErrors()) {
		   			return ok("error");
		       	}else {
		       		Long id_operador = Long.parseLong(form.get("id_operador").trim());
		       		Long valor = Long.parseLong(form.get("valor").trim());
					try {
		    			Connection con = db.getConnection();
		    			OperadorServicio.cambiaActivo(con, s.baseDato, id_operador, valor);
		    			OperadorServicio operador = OperadorServicio.find(con, s.baseDato, id_operador);
		    			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "operadorServicio", id_operador, "update", "modifica vigencia de operador: "+operador.getNombre());
		    			con.close();
	    				return redirect("/operadorMantencion/");
					} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
					return ok("error");
		       	}
	    	}else {
	    		return ok("error");
	    	}
	    }
	    
	    public Result operadorModifica(Long id_operador, Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("odoAdminOperadores")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			OperadorServicio operador = OperadorServicio.find(con, s.baseDato, id_operador);
	    			List<Usuario> listUsuario = Usuario.all(con, s.baseDato);
	    			con.close();
	    			return ok(operadorModifica.render(mapeoDiccionario,mapeoPermiso,userMnu,listUsuario,operador));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    	}
	    	return ok(mensajes.render("/",msgError));
	    }
	    
	    public Result operadorUpdate(Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
	    		OperadorServicio form = formFactory.form(OperadorServicio.class).withDirectFieldAccess(true).bindFromRequest(request).get();
	    		if (form.rut==null) {
	    			return ok(mensajes.render("/",msgErrorFormulario));
	    		}else {
	    			try {
		    			Connection con = db.getConnection();
		    			OperadorServicio.update(con, s.baseDato, form);
		    			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "operadorServicio", form.getId(), "update", "modifica datos del operador: "+form.getNombre());
	    				con.close();
	    				return redirect("/operadorMantencion/");
	    			} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
	    		}
	    	}
	    	return ok(mensajes.render("/",msgError));
	    }
	   
	    //============================================================
	    // MNU odoVentas   Odo/Ingreso Report
	    //============================================================
	    
	    public Result odoVentas(Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("odoVentas")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			String fechaAAMMDD = Fechas.hoy().getFechaStrAAMMDD();
	    			List<OperadorServicio> listOperadores = OperadorServicio.allActivos(con, s.baseDato);
	    			List<List<String>> lista = VentaServicio.listaBodegasConServ_equip(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
	    			Long aux_huella = AuxHuella.findHuella(con, s.baseDato, userMnu.getId_usuario());
	    			con.close();
	    			return ok(odoVentas.render(mapeoDiccionario,mapeoPermiso,userMnu,fechaAAMMDD,listOperadores,lista,aux_huella));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    	}
	    	return ok(mensajes.render("/",msgError));
	    }
	    
	    public Result odoListaServiciosAjax(Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
	    		DynamicForm form = formFactory.form().bindFromRequest(request);
		   		if (form.hasErrors()) {
		   			return ok("error");
		       	}else {
		       		Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
					try {
		    			Connection con = db.getConnection();
		    			List<List<String>> listServ = ListaPrecioServicio.allServiciosVigXBod(con, s.baseDato, id_bodegaEmpresa);
		    			String vistaTabla = 
					    		"<table id=\"tablaServicios\" class=\"table table-sm table-bordered table-condensed table-hover table-fluid\">"
					    			+ "<thead style=\"background-color: #eeeeee\">"
					    				+ "<TR>"
					    					+ "<th>Nro.Coti</th>"
					    					+ "<th>Codigo</th>"
					    					+ "<th>Servicio</th>"
					    				+ "</TR>"
					    			+ "</thead>"
					    			+ "<tbody>";
		    			
		    			for(List<String> x: listServ){
		    				String servicio = x.get(5)+" - "+x.get(1)+" - "+x.get(2);
		    				String encoded = new String(Base64.getEncoder().encode(servicio.getBytes()));
		    				//String decoded = new String(Base64.getDecoder().decode(encoded));
		    				vistaTabla +=
		    						"<TR onclick=\"selectServicio('"+x.get(0)+"', '"+encoded+"', '"+x.get(3)+"', '"+x.get(4)+"')\">"
		    								+ "<td style=\"text-align:center\"><a href=\"#\">"+x.get(5)+"</a></td>"
		    								+ "<td style=\"text-align:center\"><a href=\"#\">"+x.get(1)+"</a></td>"
		    								+ "<td style=\"text-align:left\"><a href=\"#\">"+x.get(2)+"</a></td>"
		    						+ "</TR>";
		    			}
		    			vistaTabla +=
					    			"</tbody>"
						    	+ "</table>";
		    			con.close();
	    				return ok(vistaTabla);
					} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
					return ok("error");
		       	}
	    	}else {
	    		return ok("error");
	    	}
	    }
	    
	    public Result odoListaEquiposAjax(Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
	    		DynamicForm form = formFactory.form().bindFromRequest(request);
		   		if (form.hasErrors()) {
		   			return ok("error");
		       	}else {
		       		Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
					try {
		    			Connection con = db.getConnection();
		    			List<List<String>> listEquip = EquipoServicio.allEquiposVigXBod(con, s.baseDato, id_bodegaEmpresa);
		    			String vistaTabla = 
					    		"<table id=\"tablaEquipos\" class=\"table table-sm table-bordered table-condensed table-hover table-fluid\">"
					    			+ "<thead style=\"background-color: #eeeeee\">"
					    				+ "<TR>"
						    				+ "<th>Codigo</th>"
					    					+ "<th>Equipo</th>"
					    				//	+ "<th>Lectura</th>"
					    				+ "</TR>"
					    			+ "</thead>"
					    			+ "<tbody>";
		    			
		    			for(List<String> x: listEquip){
		    				String equipo = x.get(1)+" - "+x.get(2);
		    				String encoded = new String(Base64.getEncoder().encode(equipo.getBytes()));
		    				//String decoded = new String(Base64.getDecoder().decode(encoded));
		    				String lectura = x.get(3);
		    				vistaTabla +=
		    						
		    						"<TR onclick=\"selectEquipo('"+x.get(0)+"', '"+encoded+"','"+lectura+"')\">"
		    								+ "<td style=\"text-align:center\"><a href=\"#\">"+x.get(1)+"</a></td>"
		    								+ "<td style=\"text-align:left\"><a href=\"#\">"+x.get(2)+"</a></td>"
		    							//	+ "<td style=\"text-align:right\"><a href=\"#\">"+x.get(3)+"</a></td>"
		    						+ "</TR>";
		    			}
		    			vistaTabla +=
					    			"</tbody>"
						    	+ "</table>";
		    			con.close();
	    				return ok(vistaTabla);
					} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
					return ok("error");
		       	}
	    	}else {
	    		return ok("error");
	    	}
	    }
	    
	    public Result odoVentasGrabar(Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
	    		VentaServicio form = formFactory.form(VentaServicio.class).withDirectFieldAccess(true).bindFromRequest(request).get();
	    		if (form.getId_servicio()==null || form.getId_userMada()==null) {
	    			return ok(mensajes.render("/",msgErrorFormulario));
	    		}else {
	    			String fileNamePdf = "";
	    			try {
		    			Connection con = db.getConnection();
		    			if(AuxHuella.existeHuella(con, s.baseDato, form.getAux_huella(), form.getId_userMada().toString())) {
		    				
		    				AuxHuella.aumentaHuella(con, s.baseDato, form.getId_userMada().toString());
		    				Long id_ventaServicio = VentaServicio.create(con, s.baseDato, form);
		    				VentaServicio ventaServicio = VentaServicio.find(con, s.baseDato, id_ventaServicio);
		    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "ventaServicio", ventaServicio.getId(), "insert", "agrega report diario nro: "+ventaServicio.getId());
		    				
		    				//EXTRAE FIRMAS
							byte[] decodedStrOper = Base64.getDecoder().decode(ventaServicio.getFirmaPDFoperador());
							byte[] decodedStrAut = Base64.getDecoder().decode(ventaServicio.getFirmaPDFautorizador());
							//FIN DE FIRMA
							
							fileNamePdf = VentaServicio.generaPdfVentaReport(con, s.baseDato, ventaServicio, decodedStrOper, decodedStrAut);
							
							
							
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
			    					}else {
			    						nombreDocAdjunto = nombreArchivoSinExtencion + ".zip";
			    					}
			    					VentaServicio.modificaPorCampo(con, s.baseDato, "docAnexo", nombreDocAdjunto, id_ventaServicio);
					    			MnuOdo.grabarFilesThread grabarFile = new MnuOdo.grabarFilesThread(s.baseDato, archivos, nombreArchivoSinExtencion);
					    			grabarFile.start();
			    				}
			    			}
			    			String url = "%2FodoVentas%2F";
		    				con.close();
		    				return redirect("/routes2/redirOdoShowPDF/"+fileNamePdf+","+"desde"+","+"hasta"+","+url);
		    			}
		    			
	    			} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
	    		}
	    	}
	    	return ok(mensajes.render("/",msgError));
	    }
	    
	    public Result odoVentasGrabaDocAnexo(Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 

	    		Archivos archivos = formFactory.form(Archivos.class).withDirectFieldAccess(true).bindFromRequest(request).get();
				String nombreDocAdjunto = "0";
    			String nombreArchivoSinExtencion = "";
    			if (archivos != null) {
    				if(archivos.docAdjunto != null) {
    					DynamicForm form = formFactory.form().bindFromRequest(request);
    			   		if (form.hasErrors()) {
    			   			return ok(mensajes.render("/",msgErrorFormulario));
    			       	}else {
    			       		try {
    			    			Connection con = db.getConnection();
	    			       		Long id_ventaServicio = Long.parseLong(form.get("id_ventaServicio").trim());
	    			       		String desdeAAMMDD = form.get("desdeAAMMDD").trim();
	    			       		String hastaAAMMDD = form.get("hastaAAMMDD").trim();
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
				    			con.close();
				    			return redirect("/odoListarVentas1aux/"+desdeAAMMDD+","+hastaAAMMDD);
			    				
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
	    
	    public Result odoGrabaAlbumFotosAjax(Http.Request request) {
			Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
	    		Archivos aux = formFactory.form(Archivos.class).withDirectFieldAccess(true).bindFromRequest(request).get();
	    		
	    		List<Http.MultipartFormData.FilePart<TemporaryFile>> archivos = aux.fotosAdjunto;
	    		Long id_ventaServicio = aux.id_ventaServicio;
	    		String nombre = aux.iniCarpeta;
	    		
	    		if (archivos != null) {
	    			
		    		try {
		    			Connection con = db.getConnection();
		    			VentaServicio ventaServicio = VentaServicio.find(con, s.baseDato, id_ventaServicio);
		    			String nombreCarpetaFotos = ventaServicio.getAlbumFotos();
						if(ventaServicio.getAlbumFotos().equals("0")) {
							nombreCarpetaFotos = nombre + "_" + id_ventaServicio;
						}
						
						for(Http.MultipartFormData.FilePart<TemporaryFile> archivo: archivos) {
			    			String subCarpeta = s.baseDato+"/"+nombreCarpetaFotos; 
			    			String nombreArchivo = archivo.getFilename();
			    			
			    			String ext = archivo.getFilename();
			             	ext = ext.substring(ext.indexOf("."));
			    			while(ext.substring(1).indexOf(".")>0) {
			    				ext=ext.substring(1);
			    				ext=ext.substring(ext.indexOf("."));
			    			}
			    			
			    			String nombreArchivoSinExtencion = nombreArchivo.replaceAll(ext, "");
			    			
			    			Archivos.grabarArchivos(archivo, subCarpeta, nombreArchivoSinExtencion);
			    		}
						
						VentaServicio.modificaPorCampo(con, s.baseDato, "albumFotos", nombreCarpetaFotos, id_ventaServicio);
						
						con.close();
						return ok(nombreCarpetaFotos);
		    		} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
	    			
				}else {
					return ok("error");
				}
	    	}
	    	return ok("error");
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
	   
	    //============================================================
	    // MNU odoVentas   Odo/Listar Report
	    //============================================================
	    
	    public Result odoListarVentas0(Http.Request request) {
			Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("odoVentas")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			Fechas hoy = Fechas.hoy();
	    			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
	    			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
	    			TasasCambio tasas = TasasCambio.allDeUnaFecha(con, s.baseDato, mapeoDiccionario.get("pais"),hasta);
	    			con.close();
	    			return ok(odoListarVentas0.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta, tasas));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    	}
	    	return ok(mensajes.render("/",msgError));
		}
	    
	    public Result odoListarVentas1(Http.Request request) {
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
		    			if(mapeoPermiso.get("odoVentas")==null) {
		    				con.close();
		    				return ok(mensajes.render("/",msgSinPermiso));
		    			}
		    			
		    			List<VentaServicio> listVentas = VentaServicio.allentreFechas(con, s.baseDato, mapeoPermiso, s.aplicaPorSucursal, s.id_sucursal, desdeAAMMDD, hastaAAMMDD);
	    			
	    			//map id_bodega vs idequip,cod,nombre
	    			Map<Long,List<List<String>>> mapEquiposVigentes = EquipoServicio.mapEquiposVigentes(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
	    			
	    			con.close();
	    			return ok(odoListarVentas1.render(mapeoDiccionario,mapeoPermiso,userMnu,listVentas,mapEquiposVigentes,desdeAAMMDD,hastaAAMMDD));
		        	} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
		    	}
	    	}
	    	return ok(mensajes.render("/",msgError));
	    }
	    
	    public Result odoListarVentas1Excel(Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
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
		    			if(mapeoPermiso.get("odoVentas")==null) {
		    				con.close();
		    				return ok(mensajes.render("/",msgSinPermiso));
		    			}
		    			
		    			List<VentaServicio> listVentas = VentaServicio.allentreFechas(con, s.baseDato, mapeoPermiso, s.aplicaPorSucursal, s.id_sucursal, desdeAAMMDD, hastaAAMMDD);
	    			
		    			File file = ReportOdo.exportaExcelOdoListarVentas1(s.baseDato, mapeoDiccionario, listVentas, desdeAAMMDD, hastaAAMMDD);
		    			
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
	    
	    
	    
	    
	    public Result odoListarVentas1aux(Http.Request request, String desdeAAMMDD, String hastaAAMMDD) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("odoVentas")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			
	    			List<VentaServicio> listVentas = VentaServicio.allentreFechas(con, s.baseDato, mapeoPermiso, s.aplicaPorSucursal, s.id_sucursal, desdeAAMMDD, hastaAAMMDD);
    			
    			//map id_bodega vs idequip,cod,nombre
    			Map<Long,List<List<String>>> mapEquiposVigentes = EquipoServicio.mapEquiposVigentes(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
    			
    			con.close();
    			return ok(odoListarVentas1.render(mapeoDiccionario,mapeoPermiso,userMnu,listVentas,mapEquiposVigentes,desdeAAMMDD,hastaAAMMDD));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    	}
	    	return ok(mensajes.render("/",msgError));
	    }
	    
	    
	    public Result odoCambiaEquipoAjax(Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
	    		DynamicForm form = formFactory.form().bindFromRequest(request);
		   		if (form.hasErrors()) {
		   			return ok(mensajes.render("/",msgErrorFormulario));
		       	}else {
		       		String id_equipo = form.get("id_equipo").trim();
		       		Long id_ventaServicio = Long.parseLong(form.get("id_ventaServicio").trim());
					try {
		    			Connection con = db.getConnection();
		    			VentaServicio.modificaPorCampo(con, s.baseDato, "id_equipo", id_equipo, id_ventaServicio);
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
	    
	    public Result odoDetalleVenta(Http.Request request, Long id_ventaServicio, String desdeAAMMDD, String hastaAAMMDD) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("odoVentas")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			VentaServicio ventaServicio = VentaServicio.find(con, s.baseDato, id_ventaServicio);
	    			con.close();
	    			return ok(odoDetalleVenta.render(mapeoDiccionario,mapeoPermiso,userMnu,ventaServicio, desdeAAMMDD, hastaAAMMDD));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    	}
	    	return ok(mensajes.render("/",msgError));
	    }
	    
	    public Result odoFirmaOperador(Http.Request request, Long id_ventaServicio, String desdeAAMMDD, String hastaAAMMDD) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("odoVentas")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			VentaServicio ventaServicio = VentaServicio.find(con, s.baseDato, id_ventaServicio);
	    			con.close();
	    			return ok(odoFirmaOperador.render(mapeoDiccionario,mapeoPermiso,userMnu,ventaServicio, desdeAAMMDD, hastaAAMMDD));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    	}
	    	return ok(mensajes.render("/",msgError));
	    }
	    
	    public Result grabarFirmaOperador(Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
	    		DynamicForm form = formFactory.form().bindFromRequest(request);
		   		if (form.hasErrors()) {
		   			return ok(mensajes.render("/",msgErrorFormulario));
	    		}else {
	    			Long id_ventaServicio = Long.parseLong(form.get("id_ventaServicio").trim());
	    			String firmaPDFoperador = form.get("firmaPDFoperador").trim();
	    			String desdeAAMMDD = form.get("fechaDesde").trim();
		       		String hastaAAMMDD = form.get("fechaHasta").trim();
	    			String fileNamePdf = "";
	    			try {
		    			Connection con = db.getConnection();
		    			VentaServicio ventaServicio = VentaServicio.find(con, s.baseDato, id_ventaServicio);
		    			VentaServicio.modificaPorCampo(con, s.baseDato, "firmaPDFoperador", firmaPDFoperador, id_ventaServicio);
		    			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "ventaServicio", ventaServicio.getId(), "insert", "agrega o modifica firma operador report diario nro: "+ventaServicio.getId());
		    			
		    			//EXTRAE FIRMAS
						byte[] decodedStrOper = Base64.getDecoder().decode(firmaPDFoperador);
						byte[] decodedStrAut = Base64.getDecoder().decode(ventaServicio.getFirmaPDFautorizador());
						//FIN DE FIRMA
						
						fileNamePdf = VentaServicio.generaPdfVentaReport(con, s.baseDato, ventaServicio, decodedStrOper, decodedStrAut);
						
						String url = "%2FodoListarVentas1aux%2F";
	    				con.close();
	    				
	    				return redirect("/routes2/redirOdoShowPDF/"+fileNamePdf+","+desdeAAMMDD+","+hastaAAMMDD+","+url);
	    				
	    			} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
	    		}
	    	}
	    	return ok(mensajes.render("/",msgError));
	    }
	    
	    public Result odoFirmaAutorizador(Http.Request request, Long id_ventaServicio, String desdeAAMMDD, String hastaAAMMDD) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("odoVentas")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			VentaServicio ventaServicio = VentaServicio.find(con, s.baseDato, id_ventaServicio);
	    			con.close();
	    			return ok(odoFirmaAutorizador.render(mapeoDiccionario,mapeoPermiso,userMnu,ventaServicio, desdeAAMMDD, hastaAAMMDD));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    	}
	    	return ok(mensajes.render("/",msgError));
	    }
	    
	    public Result grabarFirmaAutorizador(Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
	    		DynamicForm form = formFactory.form().bindFromRequest(request);
		   		if (form.hasErrors()) {
		   			return ok(mensajes.render("/",msgErrorFormulario));
	    		}else {
	    			Long id_ventaServicio = Long.parseLong(form.get("id_ventaServicio").trim());
	    			String firmaPDFaurorizador = form.get("firmaPDFautorizador").trim();
	    			String desdeAAMMDD = form.get("fechaDesde").trim();
		       		String hastaAAMMDD = form.get("fechaHasta").trim();
	    			String fileNamePdf = "";
	    			try {
		    			Connection con = db.getConnection();
		    			VentaServicio ventaServicio = VentaServicio.find(con, s.baseDato, id_ventaServicio);
		    			VentaServicio.modificaPorCampo(con, s.baseDato, "firmaPDFautorizador", firmaPDFaurorizador, id_ventaServicio);
		    			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "ventaServicio", ventaServicio.getId(), "insert", "agrega o modifica firma autorizador report diario nro: "+ventaServicio.getId());
		    			
		    			//EXTRAE FIRMAS
						byte[] decodedStrOper = Base64.getDecoder().decode(ventaServicio.getFirmaPDFoperador());
						byte[] decodedStrAut = Base64.getDecoder().decode(firmaPDFaurorizador);
						//FIN DE FIRMA
						
						fileNamePdf = VentaServicio.generaPdfVentaReport(con, s.baseDato, ventaServicio, decodedStrOper, decodedStrAut);
					
						String url = "%2FodoListarVentas1aux%2F";
	    				con.close();
	    				
	    				return redirect("/routes2/redirOdoShowPDF/"+fileNamePdf+","+desdeAAMMDD+","+hastaAAMMDD+","+url);
	    				
	    			} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
	    		}
	    	}
	    	return ok(mensajes.render("/",msgError));
	    }
	    
	    public Result odoVentaServicioElimina(Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
	    		DynamicForm form = formFactory.form().bindFromRequest(request);
		   		if (form.hasErrors()) {
		   			return ok(mensajes.render("/",msgErrorFormulario));
		       	}else {
		       		Long id_ventaServicio = Long.parseLong(form.get("id_ventaServicio").trim());
		       		String desdeAAMMDD = form.get("fechaDesde").trim();
		       		String hastaAAMMDD = form.get("fechaHasta").trim();
		    		try {
		    			Connection con = db.getConnection();
		    			VentaServicio.delete(con, s.baseDato, id_ventaServicio);
		    			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "ventaServicio", id_ventaServicio, "delete", "elimina report diario nro: "+id_ventaServicio);
		    			con.close();
		    			return redirect("/odoListarVentas1aux/"+desdeAAMMDD+","+hastaAAMMDD);
		        	} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
		       	}
	    	}
	    	return ok(mensajes.render("/",msgError));
	    }
	    
	    
	    //============================================================
	    // MNU odoProformas   Odo/Proforma/cliente_proyecto
	    //============================================================
	    
	    public Result reportProfPerOdo(Http.Request request) {
			Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("odoProformas")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			Fechas hoy = Fechas.hoy();
	    			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
	    			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
	    			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
	    			TasasCambio tasas = TasasCambio.allDeUnaFecha(con, s.baseDato, mapeoDiccionario.get("pais"),hasta);
	    			con.close();
	    			return ok(reportProfPerOdo.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta, tasas));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    	}
	    	return ok(mensajes.render("/",msgError));
		}
	    
	    public Result reportProfProyOdo(Http.Request request, String archivoPDF) {
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
		       		try {
		    			Connection con = db.getConnection();
		    			
		    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		    			Map<Long,Long> mapDec = Moneda.numeroDecimal(con, s.baseDato);
		    			List<VentaServicio> listVentaServicio = VentaServicio.allEntreFechas(con, s.baseDato, desdeAAMMDD, hastaAAMMDD, s.aplicaPorSucursal, s.id_sucursal);
		    			Map<Long,Double> mapTotalAjustePorBodega = Calc_AjustesEpOdo.mapTotalAjustePorBodega(con, s.baseDato, desdeAAMMDD, hastaAAMMDD, s.aplicaPorSucursal, s.id_sucursal);
		    			
		    			Map<Long,BodegaEmpresa> mapBodegas = BodegaEmpresa.mapAll(con, s.baseDato);
		    			Map<String,ListaPrecioServicio> mapPreciosOdo = ListaPrecioServicio.mapAllListaPrecioServicio(con, s.baseDato);
		    			Long id_grupo = (long)0; // 0 es todos los grupos de equipos
		    			Map<Long,Long> mapIdEquipoVsIdGrupo = Equipo.mapIdEquipoVsIdGrupo(con, s.baseDato);
		    			
		    			
		    			List<List<String>> resumenTotalesPorProyecto = ReportOdo.resumenTotalesPorProyecto(con, s.baseDato, listVentaServicio, tasas, mapDec, mapTotalAjustePorBodega, mapBodegas, mapPreciosOdo, id_grupo, mapIdEquipoVsIdGrupo);
		    			con.close();
		    			return ok(reportProfProyOdo.render(mapeoDiccionario,mapeoPermiso,userMnu,resumenTotalesPorProyecto,desdeAAMMDD,hastaAAMMDD, usd, eur, uf, Fechas.DDMMAA(desdeAAMMDD), Fechas.DDMMAA(hastaAAMMDD),archivoPDF));
		        	} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
		       	}
	    	}
	    	return ok(mensajes.render("/",msgError));
		}
	    
	    public Result reportProfProyOdoExcel(Http.Request request) {
	    	Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
	    		DynamicForm form = formFactory.form().bindFromRequest(request);
		   		if (form.hasErrors()) {
		   			return ok(mensajes.render("/",msgErrorFormulario));
		       	}else {
		       		String desdeAAMMDD = form.get("desdeAAMMDD").trim();
		       		String hastaAAMMDD = form.get("hastaAAMMDD").trim();
		       		Double uf = Double.parseDouble(form.get("uf").replaceAll(",", "").trim());
		       		Double usd = Double.parseDouble(form.get("usd").replaceAll(",", "").trim());
		       		Double eur = Double.parseDouble(form.get("eur").replaceAll(",", "").trim());
		       		Map<Long,Double> tasas = new HashMap<Long,Double>();
		    		tasas.put((long)1, (double) 1); 	// 'Peso Chileno', 'CLP', '0'
		    		tasas.put((long)2, usd); 			// 'Dólar', 'USD', '2'
		    		tasas.put((long)3, eur); 			// 'Euro', 'EUR', '3'
		    		tasas.put((long)4, uf); 			// 'Unidad Fomento', 'UF', '4'
		    		
		       		try {
		       			Connection con = db.getConnection();
		    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		    			Map<Long,Long> mapDec = Moneda.numeroDecimal(con, s.baseDato);
		    			List<VentaServicio> listVentaServicio = VentaServicio.allEntreFechas(con, s.baseDato, desdeAAMMDD, hastaAAMMDD, s.aplicaPorSucursal, s.id_sucursal);
		    			Map<Long,Double> mapTotalAjustePorBodega = Calc_AjustesEpOdo.mapTotalAjustePorBodega(con, s.baseDato, desdeAAMMDD, hastaAAMMDD, s.aplicaPorSucursal, s.id_sucursal);
		    			
		    			Map<Long,BodegaEmpresa> mapBodegas = BodegaEmpresa.mapAll(con, s.baseDato);
		    			Map<String,ListaPrecioServicio> mapPreciosOdo = ListaPrecioServicio.mapAllListaPrecioServicio(con, s.baseDato);
		    			Long id_grupo = (long)0; // 0 es todos los grupos de equipos
		    			Map<Long,Long> mapIdEquipoVsIdGrupo = Equipo.mapIdEquipoVsIdGrupo(con, s.baseDato);
		    			
		    			List<List<String>> resumenTotalesPorProyecto = ReportOdo.resumenTotalesPorProyecto(con, s.baseDato, listVentaServicio, tasas, mapDec, mapTotalAjustePorBodega, mapBodegas, mapPreciosOdo, id_grupo, mapIdEquipoVsIdGrupo);
		    			
		    			File file = ReportOdo.exportaProformaExcelProyectos(s.baseDato, mapeoDiccionario, resumenTotalesPorProyecto, desdeAAMMDD, hastaAAMMDD, uf, usd, eur);
		    			
		    			if(file!=null) {
			       			con.close();
			       			return ok(file,false,Optional.of("EPodo_Proforma_Listado.xlsx"));
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
	    
	    public Result reportProfProyDetalleOdo(Http.Request request) {
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
		    			
		    		try {
		        		Connection con = db.getConnection();
		        		
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
		    			List<List<String>> agrupadoPorServicio = ReportOdo.proformaAgrupadoPorServicio(groupPorClaseServicioEquipo, detalleProformaPorServicio, mapDec);
		    			List<List<String>> listaAjustes = Calc_AjustesEpOdo.listaEntreFechasPorBodega(con, s.baseDato, desdeAAMMDD, hastaAAMMDD, id_bodegaEmpresa);
		    			
		    			
		    			
		    			con.close();
			    		return ok(reportProfProyDetalleOdo.render(mapeoDiccionario,mapeoPermiso,userMnu,
			    				detalleProformaPorServicio, fechas, tasaCambio, bodega, proyecto, cliente, cantDec, listReferencias, agrupadoPorServicio, listaAjustes, groupPorClaseServicioEquipo));
		    		} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
		       	}
	    	}
	    	return ok(mensajes.render("/",msgError));
		}
	    
	    public Result reportProfProyDetalleOdoExcel(Http.Request request) {
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
		    		
		       		try {
		       			Connection con = db.getConnection();
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
		    			
		    			List<List<String>> groupPorClaseServicioEquipo = ReportOdo.proformaGroupPorClaseServicioEquipo(detalleProformaPorServicio, mapDec);
		    			List<List<String>> listaAjustes = Calc_AjustesEpOdo.listaEntreFechasPorBodega(con, s.baseDato, desdeAAMMDD, hastaAAMMDD, id_bodegaEmpresa);
		    			
		    			File file = ReportOdo.reportProfProyDetalleOdoExcel(s.baseDato, mapeoDiccionario, mapeoPermiso,
		    					detalleProformaPorServicio,fechas,tasaCambio,bodega,proyecto,cliente, groupPorClaseServicioEquipo, listaAjustes);
		    			if(file!=null) {
			       			con.close();
			       			return ok(file,false,Optional.of("EP_Proforma_"+bodega.nombre+".xlsx"));
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
	    
	    public Result generarProfPdfXlsxXmlJsonOdo(Http.Request request) {
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
		       		
		       		Map<Long,Double> tasas = new HashMap<Long,Double>();
		    		tasas.put((long)1, (double) 1); 	// 'Peso Chileno', 'CLP', '0'
		    		tasas.put((long)2, usd); 			// 'Dólar', 'USD', '2'
		    		tasas.put((long)3, eur); 			// 'Euro', 'EUR', '3'
		    		tasas.put((long)4, uf); 			// 'Unidad Fomento', 'UF', '4'
		    			
		    		try {
		    			Connection con = db.getConnection();
		    			
		    			
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
		    			
		    			Fechas hoy = Fechas.hoy();
		    			
		    			ProformaOdo proformaOdo = ProformaOdo.createSinNada(con, s.baseDato, hoy.getFechaStrAAMMDD());
		    			proformaOdo.setFecha(hoy.getFechaStrAAMMDD());
		    			proformaOdo.setDesde(desdeAAMMDD);
		    			proformaOdo.setHasta(hastaAAMMDD);
		    			proformaOdo.setId_cliente(cliente.id);
		    			proformaOdo.setId_bodegaEmpresa(bodega.id);
		    			proformaOdo.setId_proyecto(proyecto.id);
		    			proformaOdo.setDocRef("--");
		    			//proformaOdo.setEpExcelMov("ODOmov_" + proformaOdo.id + "_proformaArriendo.xlsx");
		    			proformaOdo.setEpExcelEp("ODOep_" + proformaOdo.id + "_proformaArriendo.xlsx");
		    			proformaOdo.setProformaPdf("ODOpdf" + proformaOdo.id + "_proformaArriendo.pdf");
		    			//proformaOdo.setProformaXml("ODOxml" + proformaOdo.id + "_proformaArriendo.xml");
		    			proformaOdo.setDocAnexo("0");
		    			proformaOdo.setDescuento((double)0);
		    			proformaOdo.setNeto((double)0);
		    			proformaOdo.setIva((double)0);
		    			proformaOdo.setTotal((double)0);
		    			
		   
		    			List<List<String>> groupPorClaseServicioEquipo = ReportOdo.proformaGroupPorClaseServicioEquipo(detalleProformaPorServicio, mapDec);
		    			List<List<String>> listaAjustes = Calc_AjustesEpOdo.listaEntreFechasPorBodega(con, s.baseDato, desdeAAMMDD, hastaAAMMDD, id_bodegaEmpresa);
		    			
		    			File file = ReportOdo.reportProfProyDetalleOdoExcel(s.baseDato, mapeoDiccionario, mapeoPermiso,
		    					detalleProformaPorServicio,fechas,tasaCambio,bodega,proyecto,cliente, groupPorClaseServicioEquipo, listaAjustes);

		    			String fileOutNameDetalle = s.baseDato+"/"+proformaOdo.getEpExcelEp();
		    			Archivos.grabarArchivo(file, fileOutNameDetalle);
		    			
		    			XmlFacturaReferencias referencias = new XmlFacturaReferencias();
		    			if(form.tpoDocRef!=null) {
							  referencias.tpoDocRef = form.tpoDocRef;
							  referencias.folioRef = form.folioRef;
							  referencias.fchRef = form.fchRef;
							  referencias.razonRef = form.razonRef;
							  referencias.obs = "";
						}
		    			
		    			Long cantDec = (long) Moneda.numeroDecimalxId(con, s.baseDato, "1");
		    			
		    			List<List<String>> agrupadoPorServicio = ReportOdo.proformaAgrupadoPorServicio(groupPorClaseServicioEquipo, detalleProformaPorServicio, mapDec);
		    			
		    			
		    			String archivoPDF = FormFactura.generaProformaOdo(con, s.baseDato, mapeoDiccionario,mapeoPermiso, proformaOdo, referencias,
				    				detalleProformaPorServicio, fechas, tasaCambio, bodega, proyecto, cliente, cantDec, groupPorClaseServicioEquipo, listaAjustes, agrupadoPorServicio );
		    		
		 	           con.close();
		 	           return (reportProfProyOdo(request, archivoPDF));
		 	          
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
	    // MNU proformaListaOdo   Odo/Proforma/Listado de Proformas
	    //====================================================================================
		
		public Result proformaListaOdo(Http.Request request) {
			Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("proformaListado")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			List<List<String>> lista = ProformaOdo.listadoProformas(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
	    			con.close();
	    			return ok(proformaListaOdo.render(mapeoDiccionario,mapeoPermiso,userMnu,lista));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		return ok(mensajes.render("/",msgError));
	    	}else {
	    		return ok(mensajes.render("/",msgError));
	    	}
		}
		
		public Result proformaEliminaOdo(Http.Request request) {
			Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
	    		DynamicForm form = formFactory.form().bindFromRequest(request);
		   		if (form.hasErrors()) {
		   			return ok(mensajes.render("/",msgErrorFormulario));
		       	}else {
		       		Long id_proforma = Long.parseLong(form.get("id_proforma"));
		       		try {
		    			Connection con = db.getConnection();
		    			ProformaOdo.eliminaProforma(con, s.baseDato, id_proforma);
	    				con.close();
		       			return (proformaListaOdo(request));
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
	    // MNU odoHaceAjustes   Odo/Proforma/Hacer Ajustes EP-Proforma
	    //====================================================================================
		
		public Result ajustesEpOdo(Http.Request request) {
			Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("odoHaceAjustes")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			List<List<String>> lista = VentaServicio.listaBodegasConServ_equip(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
	    			con.close();
	    			return ok(ajustesEpOdo.render(mapeoDiccionario,mapeoPermiso,userMnu,lista));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    	}
	    	return ok(mensajes.render("/",msgError));
		}
		
		public Result ajustesEpListadoOdo(Http.Request request) {
			Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
	    		DynamicForm form = formFactory.form().bindFromRequest(request);
		   		if (form.hasErrors()) {
		   			return ok(mensajes.render("/",msgErrorFormulario));
		       	}else {
		       		Long id_bodega = Long.parseLong(form.get("id_bodega"));
		       		try {
		    			Connection con = db.getConnection();
		    			
		    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		    			
		    			List<AjustesEpOdo> lista = AjustesEpOdo.allPorBodega(con, s.baseDato, id_bodega, s.aplicaPorSucursal, s.id_sucursal);
		    			BodegaEmpresa bodegaEmpresa = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodega);
		    			con.close();
		    			return ok(ajustesEpListadoOdo.render(mapeoDiccionario,mapeoPermiso,userMnu,lista,bodegaEmpresa));
		        	} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
		       	}
	    	}
	    	return ok(mensajes.render("/",msgError));
		}
		
		public Result ajustesEpNuevoOdo(Http.Request request) {
			Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
	    		DynamicForm form = formFactory.form().bindFromRequest(request);
		   		if (form.hasErrors()) {
		   			return ok(mensajes.render("/",msgErrorFormulario));
		       	}else {
		       		Long id_bodega = Long.parseLong(form.get("id_bodega"));
		       		try {
		    			Connection con = db.getConnection();
		    			
		    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodega);
		    			Fechas hoy = Fechas.hoy();
		    			List<TipoAjustes> listAjustes = TipoAjustes.all(con, s.baseDato);
		    			Long numDec = (long) Moneda.numeroDecimalxId(con, s.baseDato, "1");
		    			Moneda moneda = Moneda.find(con, s.baseDato, (long)1);
		    			con.close();
		    			return ok(ajustesEpNuevoOdo.render(mapeoDiccionario,mapeoPermiso,userMnu,bodega,hoy,listAjustes,numDec,moneda));
					} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
		       	}
	    	}
	    	return ok(mensajes.render("/",msgError));
		}
		
		public Result ajustesEpGrabarOdo(Http.Request request) {
			Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
	    		AjustesEpOdo form = formFactory.form(AjustesEpOdo.class).withDirectFieldAccess(true).bindFromRequest(request).get();
		   		if (form.id_bodegaEmpresa == null) {
		   			return ok(mensajes.render("/",msgErrorFormulario));
		       	}else {
		       		try {
		    			Connection con = db.getConnection();
		    			if(AjustesEpOdo.create(con, s.baseDato, form)) {
		    				BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, form.id_bodegaEmpresa);
		    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "ajustesEpOdo", (long)0, "create", "agrega nuevo ajuste de ep en bodega: "+bodega.getNombre());
		    				con.close();
		    				return (ajustesEpListadoOdo(request));
		    			}else {
		    				con.close();
		    				return (ajustesEpListadoOdo(request));
		    			}
					} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
		       	}
	    	}
	    	return ok(mensajes.render("/",msgError));
		}
		
		public Result ajustesEpEliminarOdo(Http.Request request) {
			Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
	    		DynamicForm form = formFactory.form().bindFromRequest(request);
		   		if (form.hasErrors()) {
		   			return ok(mensajes.render("/",msgErrorFormulario));
		       	}else {
		       		Long id_ajuste = Long.parseLong(form.get("id_ajuste"));
		       		try {
		    			Connection con = db.getConnection();
		    			if(AjustesEpOdo.delete(con, s.baseDato, id_ajuste)) {
		    				AjustesEpOdo ajuste = AjustesEpOdo.find(con, s.baseDato, id_ajuste, s.aplicaPorSucursal, s.id_sucursal);
		    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "ajustesEpOdo", id_ajuste, "delete", "elimina ajuste de ep en bodega: "+ajuste.getBodegaEmpresa());
		    				con.close();
		    				return (ajustesEpListadoOdo(request));
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
		
		public Result ajustesEpModificarOdo(Http.Request request) {
			Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
	    		DynamicForm form = formFactory.form().bindFromRequest(request);
		   		if (form.hasErrors()) {
		   			return ok(mensajes.render("/",msgErrorFormulario));
		       	}else {
		       		Long id_ajuste = Long.parseLong(form.get("id_ajuste"));
		       		try {
		    			Connection con = db.getConnection();
		    			
		    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		    			AjustesEpOdo ajusteEp = AjustesEpOdo.find(con, s.baseDato, id_ajuste, s.aplicaPorSucursal, s.id_sucursal);
		    			List<TipoAjustes> listAjustes = TipoAjustes.all(con, s.baseDato);
		    			Long numDec = (long) Moneda.numeroDecimalxId(con, s.baseDato, "1");
						con.close();
						return ok(ajustesEpModificarOdo.render(mapeoDiccionario,mapeoPermiso,userMnu,ajusteEp,listAjustes,numDec));
					} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
		       	}
	    	}
	    	return ok(mensajes.render("/",msgError));
		}
		
		public Result ajustesEpUpdateOdo(Http.Request request) {
			Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
	    		AjustesEpOdo form = formFactory.form(AjustesEpOdo.class).withDirectFieldAccess(true).bindFromRequest(request).get();
		   		if (form.id == null) {
		   			return ok(mensajes.render("/",msgErrorFormulario));
		       	}else {
		       		try {
		    			Connection con = db.getConnection();
		    			if(AjustesEpOdo.update(con, s.baseDato, form)) {
		    				AjustesEpOdo ajuste = AjustesEpOdo.find(con, s.baseDato, form.id, s.aplicaPorSucursal, s.id_sucursal);
		    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "ajustesEpOdo", form.id, "update", "modifica ajuste de ep en bodega: "+ajuste.getBodegaEmpresa());
		    				con.close();
		    				return (ajustesEpListadoOdo(request));
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
		
		public Result ajusteGrabaPDFodo(Http.Request request) {
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
		    			Connection con = db.getConnection();
		    			String path = "0";
						if (docAdjunto != null) {
							String nombreArchivoSinExtencion = "Ajuste-ODO-ID-" + id_ajuste;
							path = Archivos.grabarArchivos(docAdjunto, s.baseDato, nombreArchivoSinExtencion);
							AjustesEpOdo.modifyPDF(con, s.baseDato, path, id_ajuste);
						}
						AjustesEpOdo ajuste = AjustesEpOdo.find(con, s.baseDato, id_ajuste, s.aplicaPorSucursal, s.id_sucursal);
						Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "ajustesEpOdo", id_ajuste, "update", "agrega documento a ajusteEP en bodega: "+ajuste.getBodegaEmpresa());
						con.close();
						return (ajustesEpListadoOdo(request));
					} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
		       	}
	    	}
	    	return ok(mensajes.render("/",msgError));
		}
		
		//====================================================================================
	    // MNU odoListaAjustes   Odo/Proforma/Reporte Ajustes X bodega
	    //====================================================================================
		
		public Result ajustesEpRptOdo(Http.Request request) {
			Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("odoListaAjustes")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			List<List<String>> lista = BodegaEmpresa.listaAllBodegasVigentesExternasConAjustesOdo(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
	    			con.close();
	    			return ok(ajustesEpRptOdo.render(mapeoDiccionario,mapeoPermiso,userMnu,lista));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    	}
	    	return ok(mensajes.render("/",msgError));
		}
		
		public Result ajustesEpRptDetalleOdo(Http.Request request) {
			Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
	    		DynamicForm form = formFactory.form().bindFromRequest(request);
		   		if (form.hasErrors()) {
		   			return ok(mensajes.render("/",msgErrorFormulario));
		       	}else {
		       		Long id_bodega = Long.parseLong(form.get("id_bodega"));
		       		try {
		    			Connection con = db.getConnection();
		    			
		    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		    			List<AjustesEpOdo> lista = AjustesEpOdo.allPorBodega(con, s.baseDato, id_bodega, s.aplicaPorSucursal, s.id_sucursal);
		    			BodegaEmpresa bodegaEmpresa = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodega);
		    			con.close();
		    			return ok(ajustesEpRptDetalleOdo.render(mapeoDiccionario,mapeoPermiso,userMnu,lista,bodegaEmpresa));
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
	    // MNU odoAjustesPorPeriodo   Odo/Proforma/Reporte Ajustes X Periodo
	    //====================================================================================
		
		public Result ajustesPeriodoEpRpt1Odo(Http.Request request) {
			Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("odoAjustesPorPeriodo")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			Fechas hoy = Fechas.hoy();
	    			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
	    			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
	    			con.close();
	    			return ok(ajustesPeriodoEpRpt1Odo.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		return ok(mensajes.render("/",msgError));
	    	}else {
	    		return ok(mensajes.render("/",msgError));
	    	}
		}
		
		public Result ajustesPeriodoEpRpt2Odo(Http.Request request) {
			Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
	    		DynamicForm form = formFactory.form().bindFromRequest(request);
		   		if (form.hasErrors()) {
		   			return ok(mensajes.render("/",msgErrorFormulario));
		       	}else {
		       		String fechaDesde = form.get("fechaDesde").trim();
		       		String fechaHasta = form.get("fechaHasta").trim();
		       		try {
		    			Connection con = db.getConnection();
		    			
		    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		    			List<AjustesEpOdo> lista = AjustesEpOdo.allPorPeriodos(con, s.baseDato, fechaDesde, fechaHasta, s.aplicaPorSucursal, s.id_sucursal);
		    			con.close();
		    			return ok(ajustesPeriodoEpRpt2Odo.render(mapeoDiccionario,mapeoPermiso,userMnu,lista, Fechas.DDMMAA(fechaDesde), Fechas.DDMMAA(fechaHasta)));
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
	    // MNU odoResumen   Odo/Proforma/Resumen y Detalle (por mes)
	    //====================================================================================
		
		public Result reportOdoPeriodoResumen(Http.Request request) {
			Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("odoResumen")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			Fechas hoy = Fechas.hoy();
	    			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
	    			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
	    			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
	    			TasasCambio tasas = TasasCambio.allDeUnaFecha(con, s.baseDato, mapeoDiccionario.get("pais"),hasta);
	    			con.close();
	    			return ok(reportOdoPeriodoResumen.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta, tasas));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		return ok(mensajes.render("/",msgError));
	    	}else {
	    		return ok(mensajes.render("/",msgError));
	    	}
		}
		
		public Result reportOdoResumen(Http.Request request) {
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
		    		
		    		
		    		
		       		try {
		    			Connection con = db.getConnection();
		    			
		    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		    			
		    			
		    			//RESUMEN
		    			Map<Long,Long> mapDec = Moneda.numeroDecimal(con, s.baseDato);
		    			List<VentaServicio> listVentaServicio = VentaServicio.allEntreFechas(con, s.baseDato, desdeAAMMDD, hastaAAMMDD, s.aplicaPorSucursal, s.id_sucursal);
		    			Map<Long,Double> mapTotalAjustePorBodega = Calc_AjustesEpOdo.mapTotalAjustePorBodega(con, s.baseDato, desdeAAMMDD, hastaAAMMDD, s.aplicaPorSucursal, s.id_sucursal);
		    			
		    			Map<Long,BodegaEmpresa> mapBodegas = BodegaEmpresa.mapAll(con, s.baseDato);
		    			Map<String,ListaPrecioServicio> mapPreciosOdo = ListaPrecioServicio.mapAllListaPrecioServicio(con, s.baseDato);
		    			Long id_grupo = (long)0; // 0 es todos los grupos de equipos
		    			Map<Long,Long> mapIdEquipoVsIdGrupo = Equipo.mapIdEquipoVsIdGrupo(con, s.baseDato);
		    			
		    			List<List<String>> resumenTotalesPorProyecto = ReportOdo.resumenTotalesPorProyecto(con, s.baseDato, listVentaServicio, tasas, mapDec, mapTotalAjustePorBodega, mapBodegas, mapPreciosOdo, id_grupo, mapIdEquipoVsIdGrupo);
		    			
		    			Map<String, List<List<String>>> mapAgrupado =  new HashMap<String, List<List<String>>>();
		    			Map<String, List<List<String>>> mapDetallado =  new HashMap<String, List<List<String>>>();
		    			
		    			Map<Long,List<VentaServicio>> mapListVentaServicioXBodega = VentaServicio.mapAllEntreFechasPorBodega(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
		    			if(mapListVentaServicioXBodega!=null) {
		    				for(List<String> x: resumenTotalesPorProyecto) {
			    				Long id_bodegaEmpresa = Long.parseLong(x.get(0));
			    				List<VentaServicio> listVentaServicioXbodega = VentaServicio.allEntreFechasPorBodega(con, s.baseDato, desdeAAMMDD, hastaAAMMDD, id_bodegaEmpresa);
			    				//DETALLE POR SERVICIO SIN AGRUPAR
			    				List<List<String>> detalleProformaPorServicio = ReportOdo.detalleProformaPorServicio(con, s.baseDato, listVentaServicioXbodega, tasas, mapDec);
			    				mapDetallado.put(x.get(0), detalleProformaPorServicio);
			    				//AGRUPADO POR CLASE-SERVICIO-EQUIPO
				    			List<List<String>> groupPorClaseServicioEquipo = ReportOdo.proformaGroupPorClaseServicioEquipo(detalleProformaPorServicio, mapDec);
				    			mapAgrupado.put(x.get(0), groupPorClaseServicioEquipo);
			    			}
		    			}
		    			
		    			Long cantDec = (long) Moneda.numeroDecimalxId(con, s.baseDato, "1");
		    			
		    			con.close();
		    			return ok(reportOdoResumen.render(mapeoDiccionario,mapeoPermiso,userMnu, desde,hasta,uf,usd,eur,resumenTotalesPorProyecto,mapAgrupado,mapDetallado, cantDec));
		        	} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
		       		return ok(mensajes.render("/",msgError));
		       	}
	    	}else {
	    		return ok(mensajes.render("/",msgError));
	    	}
		}
		
		public Result reportOdoResumenExcel(Http.Request request) {
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
		    		
		       		try {
		       			Connection con = db.getConnection();
		       			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		       			//RESUMEN
		    			Map<Long,Long> mapDec = Moneda.numeroDecimal(con, s.baseDato);
		    			List<VentaServicio> listVentaServicio = VentaServicio.allEntreFechas(con, s.baseDato, desdeAAMMDD, hastaAAMMDD, s.aplicaPorSucursal, s.id_sucursal);
		    			Map<Long,Double> mapTotalAjustePorBodega = Calc_AjustesEpOdo.mapTotalAjustePorBodega(con, s.baseDato, desdeAAMMDD, hastaAAMMDD, s.aplicaPorSucursal, s.id_sucursal);
		    			
		    			Map<Long,BodegaEmpresa> mapBodegas = BodegaEmpresa.mapAll(con, s.baseDato);
		    			Map<String,ListaPrecioServicio> mapPreciosOdo = ListaPrecioServicio.mapAllListaPrecioServicio(con, s.baseDato);
		    			Long id_grupo = (long)0; // 0 es todos los grupos de equipos
		    			Map<Long,Long> mapIdEquipoVsIdGrupo = Equipo.mapIdEquipoVsIdGrupo(con, s.baseDato);
		    			
		    			List<List<String>> resumenTotalesPorProyecto = ReportOdo.resumenTotalesPorProyecto(con, s.baseDato, listVentaServicio, tasas, mapDec, mapTotalAjustePorBodega, mapBodegas, mapPreciosOdo, id_grupo, mapIdEquipoVsIdGrupo);
		    			
		    			Map<String, List<List<String>>> mapAgrupado =  new HashMap<String, List<List<String>>>();
		    			Map<String, List<List<String>>> mapDetallado =  new HashMap<String, List<List<String>>>();
		    			
		    			Map<Long,List<VentaServicio>> mapListVentaServicioXBodega = VentaServicio.mapAllEntreFechasPorBodega(con, s.baseDato, desdeAAMMDD, hastaAAMMDD);
		    			if(mapListVentaServicioXBodega!=null) {
		    				for(List<String> x: resumenTotalesPorProyecto) {
			    				Long id_bodegaEmpresa = Long.parseLong(x.get(0));
			    				List<VentaServicio> listVentaServicioXbodega = VentaServicio.allEntreFechasPorBodega(con, s.baseDato, desdeAAMMDD, hastaAAMMDD, id_bodegaEmpresa);
			    				//DETALLE POR SERVICIO SIN AGRUPAR
			    				List<List<String>> detalleProformaPorServicio = ReportOdo.detalleProformaPorServicio(con, s.baseDato, listVentaServicioXbodega, tasas, mapDec);
			    				mapDetallado.put(x.get(0), detalleProformaPorServicio);
			    				//AGRUPADO POR CLASE - FAMILIA - EQUIPO 
				    			List<List<String>> groupPorClaseServicioEquipo = ReportOdo.proformaGroupPorClaseServicioEquipo(detalleProformaPorServicio, mapDec);
				    			mapAgrupado.put(x.get(0), groupPorClaseServicioEquipo);
			    			}
		    			}
		    			File file = ReportOdo.exportaProformaExcelResumen(s.baseDato, mapeoDiccionario, desde, hasta, resumenTotalesPorProyecto, mapAgrupado, mapDetallado);
		    			if(file!=null) {
			       			con.close();
			       			return ok(file,false,Optional.of("EP_ODO_Proforma_Resumen.xlsx"));
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
	    // MNU odoConsolidado   Odo/Proforma//Proforma/Consolidado (varios Meses)
	    //====================================================================================
		
		public Result reportOdoConsolidado(Http.Request request) {
			Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("proformaConsolidado")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			Fechas hoy = Fechas.hoy();
	    			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
	    			String fecha = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
	    			con.close();
	    			return ok(reportOdoConsolidado.render(mapeoDiccionario,mapeoPermiso,userMnu, fecha));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		return ok(mensajes.render("/",msgError));
	    	}else {
	    		return ok(mensajes.render("/",msgError));
	    	}
		}
		
		public Result reportOdoConsolidadoRtp(Http.Request request) {
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
		       		try {
		    			Connection con = db.getConnection();
		    			
		    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
		    			List<List<String>> datos = ReportOdoConsolidado.reportOdoConsolidadoRtp(con, s.baseDato, fecha, meses, permisoPorBodega, mapeoDiccionario.get("pais"), s.aplicaPorSucursal, s.id_sucursal);
		    			
		    			List<String> categorias = new ArrayList<String>();
		    	    	for(int i=2; i<datos.get(0).size()-1; i++) {
		    	    		categorias.add("'" + datos.get(0).get(i) + "'");
		    	    	}
		    	    	
		    	    	List<String> nameSerie = new ArrayList<String>();
		    	    	Map<String,List<String>> mapDataSerie = new HashMap<String,List<String>>();
		    	    	for(int i=1; i<datos.size()-1; i++) {
		    	    		nameSerie.add("'" + datos.get(i).get(1) + "'");
		    	    		List<String> dataSerie = new ArrayList<String>();
		    	    		for(int j=2; j<datos.get(i).size()-1; j++) {
		    	    			
		    	    			String numeroStr = datos.get(i).get(j).replaceAll(",", "").trim();
		    	    			if(numeroStr.equals("")) {
		    	    				numeroStr = "0";
		    	    			}
		    	    			
		    	    			
		    	    			dataSerie.add(numeroStr);
		    	    		}
		    	    		mapDataSerie.put("'" + datos.get(i).get(1) + "'",dataSerie);
		    	    	}
		    			
		    			con.close();
		    			return ok(reportOdoConsolidadoRtp.render(mapeoDiccionario,mapeoPermiso,userMnu, datos, form.get("fecha"), form.get("cantMeses"), categorias, nameSerie, mapDataSerie));
		        	} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
		       		return ok(mensajes.render("/",msgError));
		       	}
	    	}else {
	    		return ok(mensajes.render("/",msgError));
	    	}
		}
		
		public Result reportOdoConsolidadoRtpExcel(Http.Request request) {
			Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
	    		DynamicForm form = formFactory.form().bindFromRequest(request);
		   		if (form.hasErrors()) {
		   			return ok(mensajes.render("/",msgErrorFormulario));
		       	}else {
		       		Fechas fecha = Fechas.obtenerFechaDesdeStrAAMMDD(form.get("fecha"));
			    	Long meses = Long.parseLong(form.get("cantMeses"));
		       		try {
		       			Connection con = db.getConnection();
		    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
		    			List<List<String>> datos = ReportOdoConsolidado.reportOdoConsolidadoRtp(con, s.baseDato, fecha, meses, permisoPorBodega, mapeoDiccionario.get("pais"), s.aplicaPorSucursal, s.id_sucursal);
		    			File file = ReportOdoConsolidado.reportOdoConsolidadoRtpExcel(s.baseDato, mapeoDiccionario, datos);
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
	    // MNU reporteMovOdo0   ODO/Movimientos/Por Fecha (Cantidades)
	    //====================================================================================
		
		public Result reporteMovOdoCantidades(Http.Request request) {
			Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("reporteMovOdo0")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			Fechas hoy = Fechas.hoy();
	    			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
	    			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
	    			con.close();
	    			return ok(reporteMovOdoCantidades.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		return ok(mensajes.render("/",msgError));
	    	}else {
	    		return ok(mensajes.render("/",msgError));
	    	}
		}
		
		public Result reporteMovOdoCantLista(Http.Request request) {
			Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
	    		DynamicForm form = formFactory.form().bindFromRequest(request);
		   		if (form.hasErrors()) {
		   			return ok(mensajes.render("/",msgErrorFormulario));
		       	}else {
		       		String fechaDesde = form.get("fechaDesde").trim();
		       		String fechaHasta = form.get("fechaHasta").trim();
		       		Double uf = (double)1;
		       		Double usd = (double)1;
		       		Double eur = (double)1;
		       		Map<Long,Double> tasas = new HashMap<Long,Double>();
		    		tasas.put((long)1, (double) 1); 	// 'Peso Chileno', 'CLP', '0'
		    		tasas.put((long)2, usd); 			// 'Dólar', 'USD', '2'
		    		tasas.put((long)3, eur); 			// 'Euro', 'EUR', '3'
		    		tasas.put((long)4, uf); 			// 'Unidad Fomento', 'UF', '4'
		       		try {
		    			Connection con = db.getConnection();
		    			
		    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		    			
		    			Map<Long,Long> mapDec = Moneda.numeroDecimal(con, s.baseDato);
		    			List<VentaServicio> listVentaServicio = VentaServicio.allEntreFechas(con, s.baseDato, fechaDesde, fechaHasta, s.aplicaPorSucursal, s.id_sucursal);
		    			Map<Long,Double> mapTotalAjustePorBodega = Calc_AjustesEpOdo.mapTotalAjustePorBodega(con, s.baseDato, fechaDesde, fechaHasta, s.aplicaPorSucursal, s.id_sucursal);
		    			
		    			Map<Long,BodegaEmpresa> mapBodegas = BodegaEmpresa.mapAll(con, s.baseDato);
		    			Map<String,ListaPrecioServicio> mapPreciosOdo = ListaPrecioServicio.mapAllListaPrecioServicio(con, s.baseDato);
		    			Long id_grupo = (long)0; // 0 es todos los grupos de equipos
		    			Map<Long,Long> mapIdEquipoVsIdGrupo = Equipo.mapIdEquipoVsIdGrupo(con, s.baseDato);
		    			
		    			List<List<String>> resumenTotalesPorProyecto = ReportOdo.resumenTotalesPorProyecto(con, s.baseDato, listVentaServicio, tasas, mapDec, mapTotalAjustePorBodega, mapBodegas, 
		    					mapPreciosOdo, id_grupo, mapIdEquipoVsIdGrupo);
		    			con.close();
		    			return ok(reporteMovOdoCantLista.render(mapeoDiccionario,mapeoPermiso,userMnu,resumenTotalesPorProyecto,fechaDesde,fechaHasta));
		        	} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
		       		return ok(mensajes.render("/",msgError));
		       	}
	    	}else {
	    		return ok(mensajes.render("/",msgError));
	    	}
		}
		
		public Result reporteMovOdoCantDetalle(Http.Request request) {
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
		       		try {
		    			Connection con = db.getConnection();
		    			
		    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		    			List<List<String>> datos = ReportOdoMovimientos.movimientoOdoCantidad(con, s.baseDato, id_bodegaEmpresa, fechaDesde, fechaHasta);
		    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
		    			con.close();
		    			return ok(reporteMovOdoCantDetalle.render(mapeoDiccionario,mapeoPermiso,userMnu,datos,bodega,fechaDesde,fechaHasta));
		        	} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
		       		return ok(mensajes.render("/",msgError));
		       	}
	    	}else {
	    		return ok(mensajes.render("/",msgError));
	    	}
		}
		
		public Result reporteMovOdoCantDetalleExcel(Http.Request request) {
			Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
	    		DynamicForm form = formFactory.form().bindFromRequest(request);
		   		if (form.hasErrors()) {
		   			return ok(mensajes.render("/",msgErrorFormulario));
		       	}else {
		       		Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
		       		String fechaDesde = form.get("fechaDesde").trim();
		       		String fechaHasta = form.get("fechaHasta").trim();
		       		try {
		    			Connection con = db.getConnection();
		    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		    			List<List<String>> datos = ReportOdoMovimientos.movimientoOdoCantidad(con, s.baseDato, id_bodegaEmpresa, fechaDesde, fechaHasta);
		    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
		    			File file = ReportOdoMovimientos.movimientosOdoCantidadExcel(con, s.baseDato, datos, mapeoDiccionario, bodega, fechaDesde, fechaHasta);
		    			if(file!=null) {
			       			con.close();
			       			return ok(file,false,Optional.of("MovimientoOdoPorBodegaCantidad.xlsx"));
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
	    // MNU reporteMovOdo1   Odo/Movimientos/Por Fecha (Valorizado)
	    //====================================================================================
		
		public Result reporteMovOdoValorizado(Http.Request request) {
			Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("reporteMovOdo1")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			Fechas hoy = Fechas.hoy();
	    			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
	    			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
	    			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
	    			TasasCambio tasas = TasasCambio.allDeUnaFecha(con, s.baseDato, mapeoDiccionario.get("pais"),hasta);
	    			con.close();
	    			return ok(reporteMovOdoValorizado.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta, tasas));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		return ok(mensajes.render("/",msgError));
	    	}else {
	    		return ok(mensajes.render("/",msgError));
	    	}
		}
		
		public Result reporteMovOdoValorizadoLista(Http.Request request) {
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
		       		try {
		    			Connection con = db.getConnection();
		    			
		    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		    			
		    			
		    			Map<Long,Long> mapDec = Moneda.numeroDecimal(con, s.baseDato);
		    			List<VentaServicio> listVentaServicio = VentaServicio.allEntreFechas(con, s.baseDato, desdeAAMMDD, hastaAAMMDD, s.aplicaPorSucursal, s.id_sucursal);
		    			Map<Long,Double> mapTotalAjustePorBodega = Calc_AjustesEpOdo.mapTotalAjustePorBodega(con, s.baseDato, desdeAAMMDD, hastaAAMMDD, s.aplicaPorSucursal, s.id_sucursal);
		    			
		    			Map<Long,BodegaEmpresa> mapBodegas = BodegaEmpresa.mapAll(con, s.baseDato);
		    			Map<String,ListaPrecioServicio> mapPreciosOdo = ListaPrecioServicio.mapAllListaPrecioServicio(con, s.baseDato);
		    			Long id_grupo = (long)0; // 0 es todos los grupos de equipos
		    			Map<Long,Long> mapIdEquipoVsIdGrupo = Equipo.mapIdEquipoVsIdGrupo(con, s.baseDato);
		    			
		    			List<List<String>> resumenTotalesPorProyecto = ReportOdo.resumenTotalesPorProyecto(con, s.baseDato, listVentaServicio, tasas, mapDec, mapTotalAjustePorBodega, mapBodegas, mapPreciosOdo, id_grupo, mapIdEquipoVsIdGrupo);
		    			
		    			
		    			con.close();
		    			return ok(reporteMovOdoValorizadoLista.render(mapeoDiccionario,mapeoPermiso,userMnu,resumenTotalesPorProyecto,desdeAAMMDD,hastaAAMMDD, usd, eur, uf, Fechas.DDMMAA(desdeAAMMDD), Fechas.DDMMAA(hastaAAMMDD)));
		        	} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
		       		return ok(mensajes.render("/",msgError));
		       	}
	    	}else {
	    		return ok(mensajes.render("/",msgError));
	    	}
		}
		
		public Result reporteMovOdoValorizadoDetalle(Http.Request request) {
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
		    		
		       		try {
		    			Connection con = db.getConnection();
		    			
		    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		    			List<List<String>> datos = ReportOdoMovimientos.movimientoOdoCantidad(con, s.baseDato, id_bodegaEmpresa, desdeAAMMDD, hastaAAMMDD);
		    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
		    			
		    			List<List<String>> listaAjustes = Calc_AjustesEpOdo.listaEntreFechasPorBodega(con, s.baseDato, desdeAAMMDD, hastaAAMMDD, id_bodegaEmpresa);
		    			
		    			List<VentaServicio> listVentaServicio = VentaServicio.allEntreFechasPorBodega(con, s.baseDato, desdeAAMMDD, hastaAAMMDD, id_bodegaEmpresa);
		    			Map<Long,Long> mapDec = Moneda.numeroDecimal(con, s.baseDato);
		    			List<List<String>> detalleProformaPorServicio = ReportOdo.detalleProformaPorServicio(con, s.baseDato, listVentaServicio, tasas, mapDec);
		    			
		    			Map<String,List<String>> mapPuYtasas = new HashMap<String,List<String>>();
		    			for(List<String> x: detalleProformaPorServicio) {
		    				List<String> aux = new ArrayList<String>();
		    				aux.add(x.get(15));	// 0 mon
		    				aux.add(x.get(16));	// 1 PU
		    				aux.add(x.get(18));	// 2 tasa
		    				mapPuYtasas.put(x.get(2), aux);
		    			}
		    			
		    			DecimalFormat myformatMonedaOrigen = new DecimalFormat("#,##0");
		    			DecimalFormat myformatMoneda = new DecimalFormat("#,##0");
		    			
		    			switch(mapDec.get((long) 1).toString()) {
		    			 case "0": myformatMonedaOrigen = new DecimalFormat("#,##0"); break;
		    			 case "2": myformatMonedaOrigen = new DecimalFormat("#,##0.00"); break;
		    			 case "4": myformatMonedaOrigen = new DecimalFormat("#,##0.0000"); break;
		    			 case "6": myformatMonedaOrigen = new DecimalFormat("#,##0.000000"); break;
		    			 default:  break;
		    			}
		    			
		    			Map<String,Long> mapDecXnick = Moneda.numeroDecimalxNombre(con, s.baseDato);
		    			
		    			for(int i=0; i< 2; i++) {
		    				List<String> aux = datos.get(i);
		    				if(i==0) {
		    					aux.add("MON");
		    					aux.add("PU");
		    					aux.add("TOTAL");
		    					aux.add("TASA");
		    					aux.add("TOTAL");
		    				}else {
		    					aux.add("");
		    					aux.add("");
		    					aux.add("");
		    					aux.add("");
		    					aux.add("");
		    				}
		    				datos.set(i, aux);
		    			}
		    			
		    			
		    			for(int i=2; i< datos.size(); i++) {
		    				List<String> aux = datos.get(i);
		    				
		    				Double cant = Double.parseDouble(aux.get(aux.size()-1));
		    				String codigo = aux.get(1);
		    				
		    				List<String> listPu = mapPuYtasas.get(codigo);
		    				String mon = "";
		    				String pu = "0";
		    				String total1 = "0";
		    				String tasa = "1";
		    				String total2 = "0";
		    				if(listPu!=null) {
		    					mon = listPu.get(0);
		    					pu = listPu.get(1);
		    					tasa = listPu.get(2);
		    					
		    					Long dec = mapDecXnick.get(mon);
		    					if(dec==null) {
		    						dec = (long)1;
		    					}
		    					switch(dec.toString()) {
		    					 case "0": myformatMoneda = new DecimalFormat("#,##0"); break;
		    					 case "2": myformatMoneda = new DecimalFormat("#,##0.00"); break;
		    					 case "4": myformatMoneda = new DecimalFormat("#,##0.0000"); break;
		    					 case "6": myformatMoneda = new DecimalFormat("#,##0.000000"); break;
		    					 default:  break;
		    					}
		    					Double dePaso = Double.parseDouble(pu.replaceAll(",", ""));
		    					dePaso = dePaso * cant;
		    					total1 = myformatMoneda.format(dePaso);
		    					dePaso = dePaso * Double.parseDouble(tasa.replaceAll(",", ""));
		    					total2 = myformatMonedaOrigen.format(dePaso);
		    				}
		    				
		    				aux.add(mon);
		    				aux.add(pu);
		    				aux.add(total1);
		    				aux.add(tasa);
		    				aux.add(total2);
		    				datos.set(i, aux);
		    			}
		    			con.close();
		    			return ok(reporteMovOdoValorizadoDetalle.render(mapeoDiccionario,mapeoPermiso,userMnu,datos,bodega,desdeAAMMDD,hastaAAMMDD, uf, usd, eur, listaAjustes));
		        	} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
		       		return ok(mensajes.render("/",msgError));
		       	}
	    	}else {
	    		return ok(mensajes.render("/",msgError));
	    	}
		}
		
		public Result reporteMovOdoValorizadoDetalleExcel(Http.Request request) {
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
		       		Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
		       		
		       		Map<Long,Double> tasas = new HashMap<Long,Double>();
		    		tasas.put((long)1, (double) 1); 	// 'Peso Chileno', 'CLP', '0'
		    		tasas.put((long)2, usd); 			// 'Dólar', 'USD', '2'
		    		tasas.put((long)3, eur); 			// 'Euro', 'EUR', '3'
		    		tasas.put((long)4, uf); 			// 'Unidad Fomento', 'UF', '4'
		    		
		       		try {
		    			Connection con = db.getConnection();
		    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		    			List<List<String>> datos = ReportOdoMovimientos.movimientoOdoCantidad(con, s.baseDato, id_bodegaEmpresa, desdeAAMMDD, hastaAAMMDD);
		    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
		    			
		    			List<List<String>> listaAjustes = Calc_AjustesEpOdo.listaEntreFechasPorBodega(con, s.baseDato, desdeAAMMDD, hastaAAMMDD, id_bodegaEmpresa);
		    			
		    			List<VentaServicio> listVentaServicio = VentaServicio.allEntreFechasPorBodega(con, s.baseDato, desdeAAMMDD, hastaAAMMDD, id_bodegaEmpresa);
		    			Map<Long,Long> mapDec = Moneda.numeroDecimal(con, s.baseDato);
		    			List<List<String>> detalleProformaPorServicio = ReportOdo.detalleProformaPorServicio(con, s.baseDato, listVentaServicio, tasas, mapDec);
		    			
		    			Map<String,List<String>> mapPuYtasas = new HashMap<String,List<String>>();
		    			for(List<String> x: detalleProformaPorServicio) {
		    				List<String> aux = new ArrayList<String>();
		    				aux.add(x.get(15));	// 0 mon
		    				aux.add(x.get(16));	// 1 PU
		    				aux.add(x.get(18));	// 2 tasa
		    				mapPuYtasas.put(x.get(2), aux);
		    			}
		    			
		    			DecimalFormat myformatMonedaOrigen = new DecimalFormat("#,##0");
		    			DecimalFormat myformatMoneda = new DecimalFormat("#,##0");
		    			
		    			switch(mapDec.get((long) 1).toString()) {
		    			 case "0": myformatMonedaOrigen = new DecimalFormat("#,##0"); break;
		    			 case "2": myformatMonedaOrigen = new DecimalFormat("#,##0.00"); break;
		    			 case "4": myformatMonedaOrigen = new DecimalFormat("#,##0.0000"); break;
		    			 case "6": myformatMonedaOrigen = new DecimalFormat("#,##0.000000"); break;
		    			 default:  break;
		    			}
		    			
		    			Map<String,Long> mapDecXnick = Moneda.numeroDecimalxNombre(con, s.baseDato);
		    			
		    			for(int i=0; i< 2; i++) {
		    				List<String> aux = datos.get(i);
		    				if(i==0) {
		    					aux.add("MON");
		    					aux.add("PU");
		    					aux.add("TOTAL");
		    					aux.add("TASA");
		    					aux.add("TOTAL");
		    				}else {
		    					aux.add("");
		    					aux.add("");
		    					aux.add("");
		    					aux.add("");
		    					aux.add("");
		    				}
		    				datos.set(i, aux);
		    			}
		    			
		    			
		    			for(int i=2; i< datos.size(); i++) {
		    				List<String> aux = datos.get(i);
		    				
		    				Double cant = Double.parseDouble(aux.get(aux.size()-1));
		    				String codigo = aux.get(1);
		    				
		    				List<String> listPu = mapPuYtasas.get(codigo);
		    				String mon = "";
		    				String pu = "0";
		    				String total1 = "0";
		    				String tasa = "1";
		    				String total2 = "0";
		    				if(listPu!=null) {
		    					mon = listPu.get(0);
		    					pu = listPu.get(1);
		    					tasa = listPu.get(2);
		    					
		    					Long dec = mapDecXnick.get(mon);
		    					if(dec==null) {
		    						dec = (long)1;
		    					}
		    					switch(dec.toString()) {
		    					 case "0": myformatMoneda = new DecimalFormat("#,##0"); break;
		    					 case "2": myformatMoneda = new DecimalFormat("#,##0.00"); break;
		    					 case "4": myformatMoneda = new DecimalFormat("#,##0.0000"); break;
		    					 case "6": myformatMoneda = new DecimalFormat("#,##0.000000"); break;
		    					 default:  break;
		    					}
		    					Double dePaso = Double.parseDouble(pu.replaceAll(",", ""));
		    					dePaso = dePaso * cant;
		    					total1 = myformatMoneda.format(dePaso);
		    					dePaso = dePaso * Double.parseDouble(tasa.replaceAll(",", ""));
		    					total2 = myformatMonedaOrigen.format(dePaso);
		    				}
		    				
		    				aux.add(mon);
		    				aux.add(pu);
		    				aux.add(total1);
		    				aux.add(tasa);
		    				aux.add(total2);
		    				datos.set(i, aux);
		    			}

		    			
		    			File file = ReportOdoMovimientos.movimientosOdoValorizadoExcel(con, s.baseDato, datos, mapeoDiccionario, bodega, desdeAAMMDD, hastaAAMMDD, listaAjustes);
		    			if(file!=null) {
			       			con.close();
			       			return ok(file,false,Optional.of("MovimientoOdoPorBodegaValorizado.xlsx"));
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
		
		//============================================================
	    // MNU odoProformas   Odo/Proforma/Consolidado por Operador
	    //============================================================
	    
	    public Result reportOperadorConsol0(Http.Request request) {
			Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("odoProformas")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			Fechas hoy = Fechas.hoy();
	    			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
	    			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
	    			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
	    			TasasCambio tasas = TasasCambio.allDeUnaFecha(con, s.baseDato, mapeoDiccionario.get("pais"),hasta);
	    			con.close();
	    			return ok(reportOperadorConsol0.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta, tasas));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    	}
	    	return ok(mensajes.render("/",msgError));
		}
		
	    public Result reportOperadorConsol1(Http.Request request) {
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
		    			List<VentaServicio> vtas = VentaServicio.allEntreFechas(con, s.baseDato, desdeAAMMDD, hastaAAMMDD, s.aplicaPorSucursal, s.id_sucursal);
		    			List<List<String>> plantilla2 = ReportOdoOperadores.reportOperadorConsol1(desdeAAMMDD, hastaAAMMDD, vtas);
		    			con.close();
		    			return ok(reportOperadorConsol1.render(mapeoDiccionario,mapeoPermiso,userMnu,desdeAAMMDD,hastaAAMMDD, plantilla2));
		        	} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
		       	}
	    	}
	    	return ok(mensajes.render("/",msgError));
		}
	    
	    public Result reportOperadorConsol1Excel(Http.Request request) {
			Sessiones s = new Sessiones(request);
	    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
	    		DynamicForm form = formFactory.form().bindFromRequest(request);
		   		if (form.hasErrors()) {
		   			return ok(mensajes.render("/",msgErrorFormulario));
		       	}else {
		       		String desdeAAMMDD = form.get("fechaDesde").trim();
		       		String hastaAAMMDD = form.get("fechaHasta").trim();
		       		try {
		    			Connection con = db.getConnection();
		    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		    			List<VentaServicio> vtas = VentaServicio.allEntreFechas(con, s.baseDato, desdeAAMMDD, hastaAAMMDD, s.aplicaPorSucursal, s.id_sucursal);
		    			List<List<String>> plantilla2 = ReportOdoOperadores.reportOperadorConsol1(desdeAAMMDD, hastaAAMMDD, vtas);
		    			
		    			File file = ReportOdoOperadores.reportOperadorConsol1Excel(s.baseDato, mapeoDiccionario, desdeAAMMDD, hastaAAMMDD, plantilla2);
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
		
	
}
