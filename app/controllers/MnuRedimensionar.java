package controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import controllers.HomeController.Sessiones;
import models.forms.FormBaja;
import models.forms.FormRedimensionar;
import models.tables.ActaRedimensionar;
import models.tables.BodegaEmpresa;
import models.tables.Equipo;
import models.tables.Fabrica;
import models.tables.Grupo;
import models.tables.Moneda;
import models.tables.Precio;
import models.tables.Redimensionar;
import models.tables.Unidad;
import models.utilities.DecimalFormato;
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
import viewsMnuRedimensionar.html.*;

public class MnuRedimensionar extends Controller {
	
	public static Database db = HomeController.dbWrite;
	public static FormFactory formFactory = HomeController.formFactory;
	public static String msgError = HomeController.msgError;
	public static String msgErrorFormulario = HomeController.msgErrorFormulario;
	public static String msgSinPermiso = HomeController.msgSinPermiso;
	
	
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdoubleCompra = new DecimalFormat("#,##0.00");
	
    //============================================================
    // MNU redimensionarPrepara   Redimensionar/preparar -- redimensionar
    //============================================================

    public Result redimensionarPrepara(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("redimensionarIngreso")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Long numeroActaRedimensionar = ActaRedimensionar.findNuevoNumero(con, s.baseDato);
    			String fecha = Fechas.hoy().getFechaStrAAMMDD();
    			List<List<String>> listEquipBodOrige = FormBaja.listEquipEnBodBaja(con, s.baseDato, mapeoPermiso, mapeoDiccionario);
    			List<Equipo> listEquipos = Equipo.allAll(con, s.baseDato);
    			
    			List<Grupo> listGrupos = Grupo.all(con, s.baseDato);
    			List<Fabrica> listFabrica = Fabrica.all(con, s.baseDato);
    			List<Unidad> listUnidades = Unidad.all(con, s.baseDato);
    			
    			List<List<String>> listBodegas = BodegaEmpresa.listaAllBodegasVigentesInternas(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
    			String optBodegas = "";
    			for(List<String> x: listBodegas) {
    				if( ! x.get(1).equals("1")) {
    					optBodegas += "<option value='"+x.get(1)+"'>"+x.get(5)+"</option>";
    				}
    			}
    			con.close();
    			return ok(redimensionarPrepara.render(mapeoDiccionario,mapeoPermiso,userMnu,numeroActaRedimensionar,listEquipBodOrige, fecha,
    					listEquipos, listGrupos, listFabrica, listUnidades, optBodegas));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result redimensionarNuevo(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    			FormRedimensionar form = formFactory.form(FormRedimensionar.class).withDirectFieldAccess(true).bindFromRequest(request).get();
        		if (form.numero==null || form.id_actaRedimensionar==null) {
        			return ok(mensajes.render("/",msgErrorFormulario));
        		}else {
        			Http.MultipartFormData<TemporaryFile> body = request.body().asMultipartFormData();
    				Http.MultipartFormData.FilePart<TemporaryFile> docAdjunto = body.getFile("docAdjunto");
        			try {
    	    			Connection con = db.getConnection();
    	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    	    			if(ActaRedimensionar.existeNumero(con, s.baseDato, form.numero)) {
    	    				String msg = "El numero de acta redimensionar ya fue utilizado, debe volver a preparar una nueva acta redimensionar";
    	    				return ok(mensajes.render("/home/",msg));
    	    			}
    	    			if(FormRedimensionar.create(con, s.baseDato, mapeoPermiso, form, docAdjunto)) {
    	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "redimensionar", (long)0, "create", "ingresa nueva acta redimensionar nro: "+form.numero);
    	    			}else {
    	    				con.close();
    	    				return ok(mensajes.render("/home/","No se pudo grabar debido a que para redimensionar bajas debe al menos estar asociado en una linea el equipo de baja con el equipo o equipos a redimensionar, ambos con cantidades mayor que cero."));
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
    
    public Result redimensionarConfirmar0(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("redimensionarConfirma")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<ActaRedimensionar> listActas = ActaRedimensionar.allPorConfirmar(con, s.baseDato);
    			con.close();
    			return ok(redimensionarConfirmar0.render(mapeoDiccionario,mapeoPermiso,userMnu,listActas));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result redimensionarConfirmar1(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		DynamicForm form = formFactory.form().bindFromRequest(request);
    		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_actaRedimensionar = Long.parseLong(form.get("id_actaRedimensionar").trim());
	       		try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("redimensionarConfirma")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			ActaRedimensionar acta = ActaRedimensionar.find(con, s.baseDato, id_actaRedimensionar);
	    			List<Redimensionar> listRedimensionar = Redimensionar.allPorIdActa(con, s.baseDato, id_actaRedimensionar);
	    			List<List<String>> detalle =  new ArrayList<List<String>>();
	    			Map<Long,Equipo> mapEquipo = Equipo.mapAllAll(con, s.baseDato);
	    			Map<Long,List<String>> mapBodega = BodegaEmpresa.mapAllVigentesInternas(con, s.baseDato, "0", "0");
	    			
	    			Map<Long,Precio> mapPrecio = Precio.mapAll(con, s.baseDato, mapeoDiccionario, (long) 1);
	    			Map<String,Long> decCompra = Moneda.numeroDecimalxNombre(con, s.baseDato);
	    			
	    			String auxCod = "";
	    			for(Redimensionar x: listRedimensionar) {
	    				List<String> aux = new ArrayList<String>();
	    				Equipo eqOrigen = mapEquipo.get(x.getId_equipoOrigen());
	    				Equipo eqRedimensionar = mapEquipo.get(x.getId_equipoRedimensionar());
	    				List<String> destino = mapBodega.get(x.id_bodegaDestino);
	    				
	    				//if(eqOrigen!=null && eqRedimensionar!=null && destino !=null) {
	    				if(eqOrigen!=null) {
	    					String cantOrigen = DecimalFormato.formato(x.getCant_equipoOrigen(), (long)2);
	    					String cantRedimensionar = DecimalFormato.formato(x.getCantEquipoRedimensionar(), (long)2);
	    					
	    					Precio precio = mapPrecio.get(x.getId_equipoOrigen());
	    					String moneda = "", pcompra = "";
	    					Double auxPcompra = (double) 0;
	    					if(precio!=null) {
	    						moneda = precio.getMonedaCompra();
	    						pcompra = precio.getPrecioCompra();
	    						auxPcompra = Double.parseDouble(pcompra.replaceAll(",", ""));
	    					}
	    					Double total = x.getCant_equipoOrigen() * auxPcompra;
	    					Long numDec = decCompra.get(moneda.toUpperCase());
	    					if(numDec==null) {
	    						numDec = (long)0;
	    					}
	    					String tot = DecimalFormato.formato(total , numDec);
	    					
	    					aux.add(x.getId().toString());
	    					aux.add(x.getId_actaRedimensionar().toString());
	    					if(auxCod.equals(eqOrigen.getCodigo())) {
	    						aux.add("");
		    					aux.add("");
		    					aux.add("");
		    					aux.add("");
		    					aux.add("");
		    					aux.add("");
		    					aux.add("");
	    					}else {
	    						aux.add(eqOrigen.getCodigo());
		    					aux.add(eqOrigen.getNombre());
		    					aux.add(eqOrigen.getUnidad());
		    					aux.add(cantOrigen);
		    					aux.add(moneda);
		    					aux.add(pcompra);
		    					aux.add(tot);
		    					auxCod = eqOrigen.getCodigo();
	    					}
	    					
	    					if(eqRedimensionar != null && destino != null) {
	    						aux.add(eqRedimensionar.getCodigo());
		    					aux.add(eqRedimensionar.getNombre());
		    					aux.add(eqRedimensionar.getUnidad());
		    					aux.add(cantRedimensionar);
		    					aux.add(destino.get(5));
	    					}else {
	    						aux.add("");
	    						aux.add("");
	    						aux.add("");
	    						aux.add("");
	    						aux.add("");
	    					}
	    					
	    					
	    					
	    					detalle.add(aux);
	    				}
	    			}
	    			con.close();
	    			return ok(redimensionarConfirmar1.render(mapeoDiccionario,mapeoPermiso,userMnu,acta,detalle));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		return ok(mensajes.render("/",msgError));
	       	}
    		
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result redimensionarConfirmar2(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
    		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_actaRedimensionar = Long.parseLong(form.get("id_actaRedimensionar").trim());
	       		try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			FormRedimensionar.redimensionar (con, s.baseDato, mapeoPermiso, mapeoDiccionario, 
	    					id_actaRedimensionar, s.id_usuario, s.userName);
	    			con.close();
	    			return redirect("/routes2/redimensionarConfirmar0/");
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    	return ok(mensajes.render("/",msgError));
    }
    
    public Result redimensionarEliminar0(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("redimensionarEliminar")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<ActaRedimensionar> listActas = ActaRedimensionar.allPorConfirmar(con, s.baseDato);
    			con.close();
    			return ok(redimensionarEliminar0.render(mapeoDiccionario,mapeoPermiso,userMnu,listActas));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result redimensionarEliminar1(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		DynamicForm form = formFactory.form().bindFromRequest(request);
    		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_actaRedimensionar = Long.parseLong(form.get("id_actaRedimensionar").trim());
	       		try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("redimensionarEliminar")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			ActaRedimensionar acta = ActaRedimensionar.find(con, s.baseDato, id_actaRedimensionar);
	    			List<Redimensionar> listRedimensionar = Redimensionar.allPorIdActa(con, s.baseDato, id_actaRedimensionar);
	    			List<List<String>> detalle =  new ArrayList<List<String>>();
	    			Map<Long,Equipo> mapEquipo = Equipo.mapAllAll(con, s.baseDato);
	    			Map<Long,List<String>> mapBodega = BodegaEmpresa.mapAllVigentesInternas(con, s.baseDato, "0", "0");
	    			
	    			Map<Long,Precio> mapPrecio = Precio.mapAll(con, s.baseDato, mapeoDiccionario, (long) 1);
	    			Map<String,Long> decCompra = Moneda.numeroDecimalxNombre(con, s.baseDato);
	    			
	    			String auxCod = "";
	    			for(Redimensionar x: listRedimensionar) {
	    				List<String> aux = new ArrayList<String>();
	    				Equipo eqOrigen = mapEquipo.get(x.getId_equipoOrigen());
	    				Equipo eqRedimensionar = mapEquipo.get(x.getId_equipoRedimensionar());
	    				List<String> destino = mapBodega.get(x.id_bodegaDestino);
	    				
	    				if(eqOrigen!=null && eqRedimensionar!=null && destino !=null) {
	    					
	    					String cantOrigen = DecimalFormato.formato(x.getCant_equipoOrigen(), (long)2);
	    					String cantRedimensionar = DecimalFormato.formato(x.getCantEquipoRedimensionar(), (long)2);
	    					
	    					Precio precio = mapPrecio.get(x.getId_equipoOrigen());
	    					String moneda = "", pcompra = "";
	    					Double auxPcompra = (double) 0;
	    					if(precio!=null) {
	    						moneda = precio.getMonedaCompra();
	    						pcompra = precio.getPrecioCompra();
	    						auxPcompra = Double.parseDouble(pcompra.replaceAll(",", ""));
	    					}
	    					Double total = x.getCant_equipoOrigen() * auxPcompra;
	    					Long numDec = decCompra.get(moneda.toUpperCase());
	    					if(numDec==null) {
	    						numDec = (long)0;
	    					}
	    					String tot = DecimalFormato.formato(total , numDec);
	    					
	    					aux.add(x.getId().toString());
	    					aux.add(x.getId_actaRedimensionar().toString());
	    					if(auxCod.equals(eqOrigen.getCodigo())) {
	    						aux.add("");
		    					aux.add("");
		    					aux.add("");
		    					aux.add("");
		    					aux.add("");
		    					aux.add("");
		    					aux.add("");
	    					}else {
	    						aux.add(eqOrigen.getCodigo());
		    					aux.add(eqOrigen.getNombre());
		    					aux.add(eqOrigen.getUnidad());
		    					aux.add(cantOrigen);
		    					aux.add(moneda);
		    					aux.add(pcompra);
		    					aux.add(tot);
		    					auxCod = eqOrigen.getCodigo();
	    					}
	    					
	    					
	    					aux.add(eqRedimensionar.getCodigo());
	    					aux.add(eqRedimensionar.getNombre());
	    					aux.add(eqRedimensionar.getUnidad());
	    					aux.add(cantRedimensionar);
	    					
	    					aux.add(destino.get(5));
	    					
	    					detalle.add(aux);
	    				}
	    			}
	    			con.close();
	    			return ok(redimensionarEliminar1.render(mapeoDiccionario,mapeoPermiso,userMnu,acta,detalle));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		return ok(mensajes.render("/",msgError));
	       	}
    		
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result redimensionarEliminar2(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
    		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_actaRedimensionar = Long.parseLong(form.get("id_actaRedimensionar").trim());
	       		try {
	    			Connection con = db.getConnection();
	    			if(ActaRedimensionar.delete(con, s.baseDato, id_actaRedimensionar)){
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "redimensionar", (long)0, "delete", "elimina acta redimensionar id: "+id_actaRedimensionar);
	    			}
	    			con.close();
	    			return redirect("/routes2/redimensionarEliminar0/");
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    	return ok(mensajes.render("/",msgError));
    }
    
    public Result redimensionarListar0(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("redimensionarListado")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<ActaRedimensionar> listActas = ActaRedimensionar.allConfirmadas(con, s.baseDato);
    			con.close();
    			return ok(redimensionarListar0.render(mapeoDiccionario,mapeoPermiso,userMnu,listActas));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result redimensionarListar1(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		DynamicForm form = formFactory.form().bindFromRequest(request);
    		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_actaRedimensionar = Long.parseLong(form.get("id_actaRedimensionar").trim());
	       		try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("redimensionarListado")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			ActaRedimensionar acta = ActaRedimensionar.find(con, s.baseDato, id_actaRedimensionar);
	    			List<Redimensionar> listRedimensionar = Redimensionar.allPorIdActa(con, s.baseDato, id_actaRedimensionar);
	    			List<List<String>> detalle =  new ArrayList<List<String>>();
	    			Map<Long,Equipo> mapEquipo = Equipo.mapAllAll(con, s.baseDato);
	    			Map<Long,List<String>> mapBodega = BodegaEmpresa.mapAllVigentesInternas(con, s.baseDato, "0", "0");
	    			
	    			Map<Long,Precio> mapPrecio = Precio.mapAll(con, s.baseDato, mapeoDiccionario, (long) 1);
	    			Map<String,Long> decCompra = Moneda.numeroDecimalxNombre(con, s.baseDato);
	    			
	    			String auxCod = "";
	    			for(Redimensionar x: listRedimensionar) {
	    				List<String> aux = new ArrayList<String>();
	    				Equipo eqOrigen = mapEquipo.get(x.getId_equipoOrigen());
	    				Equipo eqRedimensionar = mapEquipo.get(x.getId_equipoRedimensionar());
	    				List<String> destino = mapBodega.get(x.id_bodegaDestino);
	    				
	    				if(eqOrigen!=null && eqRedimensionar!=null && destino !=null) {
	    					
	    					String cantOrigen = DecimalFormato.formato(x.getCant_equipoOrigen(), (long)2);
	    					String cantRedimensionar = DecimalFormato.formato(x.getCantEquipoRedimensionar(), (long)2);
	    					
	    					Precio precio = mapPrecio.get(x.getId_equipoOrigen());
	    					String moneda = "", pcompra = "";
	    					Double auxPcompra = (double) 0;
	    					if(precio!=null) {
	    						moneda = precio.getMonedaCompra();
	    						pcompra = precio.getPrecioCompra();
	    						auxPcompra = Double.parseDouble(pcompra.replaceAll(",", ""));
	    					}
	    					Double total = x.getCant_equipoOrigen() * auxPcompra;
	    					Long numDec = decCompra.get(moneda.toUpperCase());
	    					if(numDec==null) {
	    						numDec = (long)0;
	    					}
	    					String tot = DecimalFormato.formato(total , numDec);
	    					
	    					aux.add(x.getId().toString());
	    					aux.add(x.getId_actaRedimensionar().toString());
	    					if(auxCod.equals(eqOrigen.getCodigo())) {
	    						aux.add("");
		    					aux.add("");
		    					aux.add("");
		    					aux.add("");
		    					aux.add("");
		    					aux.add("");
		    					aux.add("");
	    					}else {
	    						aux.add(eqOrigen.getCodigo());
		    					aux.add(eqOrigen.getNombre());
		    					aux.add(eqOrigen.getUnidad());
		    					aux.add(cantOrigen);
		    					aux.add(moneda);
		    					aux.add(pcompra);
		    					aux.add(tot);
		    					auxCod = eqOrigen.getCodigo();
	    					}
	    					
	    					
	    					aux.add(eqRedimensionar.getCodigo());
	    					aux.add(eqRedimensionar.getNombre());
	    					aux.add(eqRedimensionar.getUnidad());
	    					aux.add(cantRedimensionar);
	    					
	    					aux.add(destino.get(5));
	    					
	    					detalle.add(aux);
	    				}
	    			}
	    			con.close();
	    			return ok(redimensionarListar1.render(mapeoDiccionario,mapeoPermiso,userMnu,acta,detalle));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		return ok(mensajes.render("/",msgError));
	       	}
    		
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }

}
