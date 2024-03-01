package controllers;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import controllers.HomeController.Sessiones;
import models.forms.FormBaja;
import models.tables.ActaBaja;
import models.tables.Baja;
import models.tables.Compra;
import models.tables.Movimiento;
import models.utilities.Fechas;
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
import viewsMnuBajas.html.*;

public class MnuBajas extends Controller {
	
	public static Database db = HomeController.dbWrite;
	public static FormFactory formFactory = HomeController.formFactory;
	public static String msgError = HomeController.msgError;
	public static String msgErrorFormulario = HomeController.msgErrorFormulario;
	public static String msgSinPermiso = HomeController.msgSinPermiso;
	
	
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdoubleCompra = new DecimalFormat("#,##0.00");
	
	
	//============================================================
    // MNU bajaIngreso   Bajas/Ingresar Actas
    //============================================================

    public Result bajaIngreso(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("bajaIngreso")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Long numeroActaBaja = ActaBaja.findNuevoNumero(con, s.baseDato);
    			String fecha = Fechas.hoy().getFechaStrAAMMDD();
    			List<List<String>> listEquipBodOrige = FormBaja.listEquipEnBodBaja(con, s.baseDato, mapeoPermiso, mapeoDiccionario);
    			con.close();
    			return ok(bajaIngreso.render(mapeoDiccionario,mapeoPermiso,userMnu,numeroActaBaja,listEquipBodOrige, fecha));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result bajaNuevo(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    			FormBaja form = formFactory.form(FormBaja.class).withDirectFieldAccess(true).bindFromRequest(request).get();
        		if (form.numero==null || form.id_actaBaja==null) {
        			return ok(mensajes.render("/",msgErrorFormulario));
        		}else {
        			Http.MultipartFormData<TemporaryFile> body = request.body().asMultipartFormData();
    				Http.MultipartFormData.FilePart<TemporaryFile> docAdjunto = body.getFile("docAdjunto");
        			try {
    	    			Connection con = db.getConnection();
    	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    	    			if(ActaBaja.existeNumero(con, s.baseDato, form.numero)) {
    	    				String msg = "El numero de acta de baja ya fue utilizado, debe volver a ingresar el acta de baja";
    	    				return ok(mensajes.render("/home/",msg));
    	    			}
    	    			
    	    			if(FormBaja.create(con, s.baseDato, mapeoPermiso, form, docAdjunto)) {
    	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "baja", (long)0, "create", "ingresa nueva acta de baja nro: "+form.numero);
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
    // MNU bajaConfirma   Bajas/Confirmar Bajas 
    //============================================================

    public Result confirmaBaja(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("bajaConfirma")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<List<String>> listado = Baja.listParaConfirmar(con, s.baseDato);
    			con.close();
    			return ok(confirmaBaja.render(mapeoDiccionario,mapeoPermiso,userMnu,listado));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result bajaConfirmaIngreso(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
    			try {
	    			Connection con = db.getConnection();
	    			String arrIdBajas = form.get("cambiosDeEstados").trim();
	    			if(arrIdBajas.length()>0) {
	    				String listIdBaja = "";
	    				String[] arr = arrIdBajas.split(";");
	    				for(int i=0; i<arr.length; i++) {
	    					String[] detalle = arr[i].split(",");
	    					Long id_baja = Long.parseLong(detalle[0]);
	    					Long id_equipo = Long.parseLong(detalle[1]);
	    					Double cantidad = Double.parseDouble(detalle[2]);
	    					Movimiento auxMov = new Movimiento();
	    					auxMov.setId_bodegaEmpresa((long)1);
	    					auxMov.setId_equipo(id_equipo);
	    					auxMov.setId_tipoMovimiento((long)2);
	    					auxMov.setCantidad(cantidad);
	    					auxMov.setId_baja(id_baja);
	    					if(Movimiento.createMovimientoBaja(con, s.baseDato, auxMov)) {
	    						FormBaja.cambiaAconfirmada(con, s.baseDato, id_baja);
	    						listIdBaja += id_baja+", ";
	    					}
	    				}
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "baja", (long)0, "confirma", "confirma bajas id: "+listIdBaja);
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
    // MNU bajaModifica2   Bajas/Modificar Actas 
    //============================================================

    public Result bajaListaModifica(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("bajaModifica2")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<ActaBaja> listActas = ActaBaja.allModificables(con, s.baseDato);
    			con.close();
    			return ok(bajaListaModifica.render(mapeoDiccionario,mapeoPermiso,userMnu,listActas));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result bajaModifica(Long id_actaBaja, Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("bajaModifica2")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			ActaBaja actaBaja = ActaBaja.find(con, s.baseDato, id_actaBaja);
    			Map<Long, Baja> mapDetalleBajaModifica = Baja.mapDetalleBajaModifica(con, s.baseDato, id_actaBaja);
    			List<List<String>> detalleBaja = FormBaja.detalleDeLaBaja(con, s.baseDato, mapeoPermiso, mapeoDiccionario, id_actaBaja, mapDetalleBajaModifica);
    			con.close();
    			return ok(bajaModifica.render(mapeoDiccionario,mapeoPermiso,userMnu,actaBaja,detalleBaja));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result bajaModificaGraba(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    			FormBaja form = formFactory.form(FormBaja.class).withDirectFieldAccess(true).bindFromRequest(request).get();
        		if (form.numero==null || form.id_actaBaja==null) {
        			return ok(mensajes.render("/",msgErrorFormulario));
        		}else {
        			Http.MultipartFormData<TemporaryFile> body = request.body().asMultipartFormData();
    				Http.MultipartFormData.FilePart<TemporaryFile> docAdjunto = body.getFile("docAdjunto");
        			try {
    	    			Connection con = db.getConnection();
    	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    	    			if(Baja.deleteNoConfirmadas(con, s.baseDato, form.id_actaBaja)) {
    	    				FormBaja.update(con, s.baseDato, mapeoPermiso, form, docAdjunto);
    	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "baja", form.id_actaBaja, "update", "modifica acta de baja id: "+form.id_actaBaja);
    	    				con.close();
        	    			return redirect("/home/");
    	    			}
    	    			con.close();
    	    			return ok(mensajes.render("/",msgError));
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
    // MNU bajaListado   Bajas/Listar Bajas
    //============================================================
    
    public Result listBajasPorActa(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("bajaListado")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<ActaBaja> listActas = ActaBaja.all(con, s.baseDato);
    			con.close();
    			return ok(listBajasPorActa.render(mapeoDiccionario,mapeoPermiso,userMnu,listActas));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result bajaActaPrint(Long id_actaBaja, Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("bajaListado")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			ActaBaja actaBaja = ActaBaja.find(con, s.baseDato, id_actaBaja);
    			Map<Long, Baja> mapDetalleBaja = Baja.mapDetalleBaja(con, s.baseDato, id_actaBaja);
    			List<List<String>> detalleBaja = FormBaja.detalleDeLaBaja(con, s.baseDato, mapeoPermiso, mapeoDiccionario, id_actaBaja, mapDetalleBaja);
    			List<List<String>> detSinCantCero = new ArrayList<List<String>>();
    			detalleBaja.forEach(x->{
    				Double cantidad = Double.parseDouble(x.get(13).replaceAll(",", ""));
    				if((double)cantidad > (double)0) {
    					detSinCantCero.add(x);
    				}
    			});
    			con.close();
    			return ok(bajaActaPrint.render(mapeoDiccionario,mapeoPermiso,userMnu,actaBaja,detSinCantCero));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result listBajasPorEquipo(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("bajaListado")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<List<String>> listaPorEquipo = Baja.listaBajaPorEquipo(con, s.baseDato);
    			con.close();
    			return ok(listBajasPorEquipo.render(mapeoDiccionario,mapeoPermiso,userMnu,listaPorEquipo));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result listBajasPorEquipoExcel(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("bajaListado")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			List<List<String>> listaPorEquipo = Baja.listaBajaPorEquipo(con, s.baseDato);
	       		
	    			File file = Baja.listBajasPorEquipoExcel(s.baseDato, mapeoDiccionario, listaPorEquipo);
	       		
	    			if(file!=null) {
		       			con.close();
		       			return ok(file,false,Optional.of("ListadoDeComprasPorCompra.xlsx"));
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
    
    public Result bajaEquipoPrint(Long id_equipo, Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("bajaListado")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<List<String>> listaCompra = Compra.allPorEquipo(con, s.baseDato, id_equipo);
    			List<List<String>> listaBaja = Baja.allPorEquipo(con, s.baseDato, id_equipo);
    			con.close();
    			return ok(bajaEquipoPrint.render(mapeoDiccionario,mapeoPermiso,userMnu,listaCompra,listaBaja));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    
    

}
