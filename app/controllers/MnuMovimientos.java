package controllers;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;

import controllers.HomeController.Sessiones;
import models.api.ApiManagerDocDoc;
import models.api.ApiNuboxDocDoc;
import models.api.WebFacturacion;
import models.api.WebMaximise;
import models.calculo.Inventarios;
import models.forms.FormMovimiento;
import models.tables.Atributo;
import models.tables.BodegaEmpresa;
import models.tables.Comercial;
import models.tables.ContactoBodegaEmpresa;
import models.tables.Cotizacion;
import models.tables.EmisorTributario;
import models.tables.Equipo;
import models.tables.Grupo;
import models.tables.Guia;
import models.tables.Movimiento;
import models.tables.Ot;
import models.tables.Proyecto;
import models.tables.Sucursal;
import models.tables.TipoEstado;
import models.tables.TipoReparacion;
import models.tables.Transportista;
import models.tables.UsuarioPermiso;
import models.utilities.Archivos;
import models.utilities.Fechas;
import models.utilities.Registro;
import models.utilities.UserMnu;
import models.xlsx.MovimHojaChequeo;
import models.xlsx.XLSX_GuiaEntrada;
import models.xlsx.XLSX_GuiaSalida;
import models.xml.WebFacturacionGuiaSalidaXml;
import models.xml.XmlGuiaSalida;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.Database;
import play.libs.Json;
import play.libs.Files.TemporaryFile;
import play.libs.mailer.Email;
import play.libs.mailer.MailerClient;
import play.libs.ws.WSBodyReadables;
import play.libs.ws.WSBodyWritables;
import play.libs.ws.WSClient;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.*;
import viewsMnuMovimientos.html.*;

public class MnuMovimientos extends Controller implements WSBodyReadables, WSBodyWritables{
	
	
	
	public static Database db = HomeController.dbWrite;
	public static FormFactory formFactory = HomeController.formFactory;
	public String msgError = HomeController.msgError;
	public static String msgErrorFormulario = HomeController.msgErrorFormulario;
	public static String msgSinPermiso = HomeController.msgSinPermiso;
	
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	
	private final WSClient ws;
	public final MailerClient mailerClient;
	
	@Inject
	  public MnuMovimientos(WSClient ws, MailerClient mailerClient) {
	    this.ws = ws;
	    this.mailerClient = mailerClient;
	  }
	
	

	
	//============================================================
    // MNU movimientoSelectBodegaOrigen   Movimientos/Ingresar
    //============================================================
	
	public Result movimientoSelectBodegaOrigen(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("movimientoSelectBodegaOrigen")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			
    			List<List<String>> listBodegas = BodegaEmpresa.listaAllBodegasVigInterExterConStock(con, s.baseDato, mapeoPermiso, s.aplicaPorSucursal, s.id_sucursal);
    			con.close();
    			return ok(movimientoSelectBodegaOrigen.render(mapeoDiccionario,mapeoPermiso,userMnu,listBodegas));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
	
	public Result movimientoOrigenDestinoMultiple(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	    	  	Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("movimientoSelectBodegaOrigen")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			BodegaEmpresa bodegaOrigen = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
	    			List<List<String>> listBodegasDestino =  new ArrayList<List<String>>();
	    			List<List<String>> listEquipBodOrigen = new ArrayList<List<String>>();
	    			Long soloArriendo = (long) 1;
	    			if(mapeoPermiso.get("parametro.permiteDevolverVentas").equals("1")) {
	    				soloArriendo = (long) 0;
	    			}
	    			Map<String,Movimiento> mapStock = Inventarios.invPorIdBodega(con, s.baseDato, id_bodegaEmpresa, soloArriendo);
	    			Map<Long,Grupo> mapGrupo = Grupo.mapAll(con, s.baseDato);
	    			Map<Long,Equipo> mapEquipo = Equipo.mapAllVigentes(con, s.baseDato);
	    			Map<Long,Cotizacion> mapCotizacion = Cotizacion.mapAll(con, s.baseDato);
	    			Map<Long,Double> mapPeso = Atributo.mapAtributoPESO(con, s.baseDato);
	    			Map<Long,Double> mapM2 = Atributo.mapAtributoM2(con, s.baseDato);
	    			
	    			Map<Long,Long> mapIdCotiVsIdOt = Ot.mapAll_idCoti_vs_idOt(con, s.baseDato);
	    			Map<Long,Ot> mapOt = Ot.mapAll(con, s.baseDato);
	    			
	    			Map<Long,List<String>> mapEquipSinSaldo = new HashMap<Long,List<String>>();
	    			
	    			mapStock.forEach((k,v)->{
	    				Equipo equipo = mapEquipo.get(v.getId_equipo());
	    				if(equipo!=null) {
	    					Grupo grupo = mapGrupo.get(equipo.getId_grupo());
	        				Cotizacion coti = mapCotizacion.get(v.getId_cotizacion());
	        				Long numCoti = (long) 0;
	        				Long numOt  = (long) 0;
	        				Long id_ot  = (long) 0;
	        				String fechaOt = "";
        					if(coti!=null){
        						numCoti = coti.getNumero();
        						
        						id_ot = mapIdCotiVsIdOt.get(coti.getId());
        						if(id_ot!=null) {
        							Ot ot = mapOt.get(id_ot);
        							if(ot != null) {
	        							numOt = ot.getNumero();
	        							id_ot = ot.getId();
	        							fechaOt = Fechas.DDMMAA(ot.getFecha());
	        						}
        						}else{
        							id_ot  = (long) 0;
        						}
        					}
	        				Double peso = mapPeso.get(v.getId_equipo()); 
	        				if(peso==null){
	        					peso = (double)0;
	        				}
	        				Double m2 = mapM2.get(v.getId_equipo()); 
	        				if(m2==null){
	        					m2 = (double)0;
	        				}
	        				
		    				if(v.getCantidad()>0) {
		        				List<String> aux = new ArrayList<String>();
		        				aux.add(v.getId_equipo().toString()); 				// 0 id_equipo
		        				aux.add(v.getId_cotizacion().toString()); 			// 1 id_cotizacion
		        				aux.add(grupo.getNombre()); 						// 2 nombre de grupo
		        				aux.add(numCoti.toString()); 						// 3 numero cotizacion
		        				aux.add(equipo.getCodigo()); 						// 4 codigo de equipo
		        				aux.add(equipo.getNombre()); 						// 5 nombre de equipo
		        				aux.add(myformatdouble2.format(peso)); 				// 6 KG por equipo
		        				aux.add(myformatdouble2.format(m2)); 				// 7 M2 por equipo
		        				aux.add(equipo.getUnidad());						// 8 unidad
		        				aux.add(myformatdouble2.format(v.getCantidad()));	// 9 stock disponible
		        				aux.add(id_ot.toString()); 							// 10 id_ot
		        				aux.add(numOt.toString()); 							// 11 numero OT
		        				aux.add(fechaOt); 									// 12 fecha ot
		        				listEquipBodOrigen.add(aux);
		    				}else {
		    					List<String> aux = new ArrayList<String>();
		    					aux.add(v.getId_equipo().toString()); 				// 0 id_equipo
		        				aux.add("0"); 										// 1 id_cotizacion
		        				aux.add(grupo.getNombre()); 						// 2 nombre de grupo
		        				aux.add(""); 										// 3 numero cotizacion
		        				aux.add(equipo.getCodigo()); 						// 4 codigo de equipo
		        				aux.add(equipo.getNombre()); 						// 5 nombre de equipo
		        				aux.add(myformatdouble2.format(peso)); 				// 6 KG por equipo
		        				aux.add(myformatdouble2.format(m2)); 				// 7 M2 por equipo
		        				aux.add(equipo.getUnidad());						// 8 unidad
		        				aux.add(myformatdouble2.format(v.getCantidad()));	// 9 stock disponible
		        				aux.add("0"); 										// 10 id_ot
		        				aux.add("0"); 										// 11 numero OT
		        				aux.add(""); 										// 12 fecha ot
		        				mapEquipSinSaldo.put(v.getId_equipo(), aux);
		    				}
	    				}
	    			});
	    			
	    			
	    			Map<Long,Double> mapAux = new HashMap<Long,Double>();
	    			if(listEquipBodOrigen.size()==0) {
	    				String mensaje = mapeoDiccionario.get("Bodega")+"/Proyecto: "+bodegaOrigen.nombre+" no posee equipos para trasladar (no hay existencia)";
	    				con.close();
	    				return ok(mensajes.render("/movimientoSelectBodegaOrigen/",mensaje));
	    			}else{
	    				for(List<String> l: listEquipBodOrigen){
	    					String cantStr = l.get(9);
	    					Double cantDbl = Double.parseDouble(cantStr.replaceAll(",",""));
	    					Double aux = mapAux.get(Long.parseLong(l.get(0)));
	    					if(aux == null) {
	    						mapAux.put(Long.parseLong(l.get(0)), cantDbl);
	    					}else {
	    						mapAux.put(Long.parseLong(l.get(0)), cantDbl + aux);
	    					}
		    				
		    			}
	    			}
	    			
	    			
	    			List<List<String>> listBodegas = new ArrayList<List<String>>();
	    			
	    			if((long)bodegaOrigen.getEsInterna() == (long)1) {
	    				listBodegas = BodegaEmpresa.listaAllBodegasVigentesInternasExternas(con, s.baseDato, mapeoPermiso, s.aplicaPorSucursal, s.id_sucursal);
	    			}else {
	    				listBodegas = BodegaEmpresa.listaAllBodegasVigentesInternas(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
	    			}
	    			
	    			listBodegas.forEach(l->{
	    				if(!l.get(1).equals(id_bodegaEmpresa.toString())) {
	    					listBodegasDestino.add(l);
	    				}
	    			});
	    			
	    			Long nuevoNumeroGuia = Guia.findNuevoNumero(con, s.baseDato);
	    			Fechas hoy = Fechas.hoy();
	    			List<List<String>> listEquipNoEnBodOrigen = new ArrayList<List<String>>();
	    			
	    			
	    			
	    			
	    			Map<Long,List<String>> mapAuxiliar = new HashMap<Long,List<String>>();
	    			
	    			mapEquipo.forEach((k,v)->{
	    				Double aux = mapAux.get(k);
	    				if(aux == null) {
	    					List<String> aux2 = new ArrayList<String>();
	    					aux2.add(v.getId().toString()); 				// 0 id_equipo
	        				aux2.add("0"); 									// 1 id_cotizacion
	        				aux2.add(v.getGrupo()); 						// 2 nombre de grupo
	        				aux2.add(""); 									// 3 numero cotizacion
	        				aux2.add(v.getCodigo()); 						// 4 codigo de equipo
	        				aux2.add(v.getNombre()); 						// 5 nombre de equipo
	        				aux2.add(myformatdouble2.format(v.getKG())); 	// 6 KG por equipo
	        				aux2.add(myformatdouble2.format(v.getM2())); 	// 7 M2 por equipo
	        				aux2.add(v.getUnidad());						// 8 unidad
	        				aux2.add(myformatdouble2.format((double)0));	// stock disponible
	        				mapAuxiliar.put(v.getId(), aux2);
	    				}
	    			});
	    			
	    			mapAuxiliar.forEach((k,v)->{
	    				listEquipNoEnBodOrigen.add(v);
	    			});
	    			
	    			List<TipoEstado> listTipoEstado = TipoEstado.all(con, s.baseDato);
	    			List<TipoReparacion> listTipoReparacion = TipoReparacion.all(con, s.baseDato);
	    			
	    			List<Transportista> listaTransporte = Transportista.listaTransportista(con, s.baseDato);
	    			
	    			Sucursal sucursal = Sucursal.find(con, s.baseDato, bodegaOrigen.getId_sucursal().toString());
	    			
	    			Comercial comercial = new Comercial();
	    			if((long)bodegaOrigen.getEsInterna() == (long)2) {
	    				if(bodegaOrigen.getId_comercial().toString().equals("0")) {
	    					comercial.setId((long)-1);
		    				comercial.setNameUsuario("");
	    				}else {
	    					Comercial auxComercial = Comercial.findPorIdUsuario(con, s.baseDato, bodegaOrigen.getId_comercial().toString());
		    				if(auxComercial.getId()!=null) {
		    					comercial = auxComercial;
		    				}
	    				}
	    			}else {
	    				comercial.setId((long)-1);
	    				comercial.setNameUsuario("");
	    			}
	    			
	    			String sinVenta="0";
	    			if(soloArriendo == (long)1 && bodegaOrigen.getEsInterna() == (long)2) {
	    				sinVenta = "1";
	    			}
	    			
	    			con.close();
	    			return ok(movimientoOrigenDestinoMultiple.render(mapeoDiccionario,mapeoPermiso,userMnu,bodegaOrigen,listEquipBodOrigen,listBodegasDestino,
	    						nuevoNumeroGuia,hoy.getFechaStrAAMMDD(),listEquipNoEnBodOrigen,listTipoEstado,listTipoReparacion,listaTransporte,
	    						sucursal, comercial, sinVenta));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
	
	public Result movValySubePlantilla(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		Archivos aux = formFactory.form(Archivos.class).withDirectFieldAccess(true).bindFromRequest(request).get();
    		Http.MultipartFormData.FilePart<TemporaryFile> archivo = aux.file;
    		Long id_bodegaOrigen = aux.id_bodegaOrigen;
    		if (archivo != null && id_bodegaOrigen > 0) {
    			File file = Archivos.parseMultipartFormDatatoFile(archivo);
	    		try {
	    			Connection con = db.getConnection();
	    			List<String> mensaje = Movimiento.validaPlantillaMovimiento(con, s.baseDato, file);
	    			if(mensaje.get(0).equals("true")) {
	    				List<List<String>> listaExcel = Movimiento.llenaListaDesdePlantillaExcel(file);
	    				Map<String,List<String>> map = new HashMap<String,List<String>>();
	    				listaExcel.forEach(x->{
	    					map.put(x.get(0), x);
	    				});
	    				String mapeo = Json.toJson(map).toString();
	    				con.close();
	    				return ok("{\"status\":true,\"map\":"+mapeo+"}").as("application/json");
	    			}else {
	    				String msg = "";
						for(String m: mensaje) {
							msg += m + "<br>";
						}
						con.close();
						return ok("{\"status\":false,\"msg\":\""+msg+"\"}").as("application/json");
	    			}
	    			
	    		} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
    			
			}
    	}
    	return ok("{\"status\":\"false\",\"msg\":\"ERROR\"}").as("application/json");
	}
	
	public Result movPlantilla(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
        	if (form.hasErrors()) {
    			return ok(mensajes.render("/",msgErrorFormulario));
    		}else {
    			Long id_bodegaOrigen = Long.parseLong(form.get("id_bodegaOrigen").trim());
    			try {
    				Connection con = db.getConnection();
    				File file = Movimiento.plantillaMovimiento(con, s.baseDato, s.id_tipoUsuario, id_bodegaOrigen);
    				if(file != null) {
    					con.close();
        				return ok(file, Optional.of("plantillaCargaMovimientos.xlsx"));
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
	
	public Result nameComercialAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_bodegaOrigen = Long.parseLong(form.get("id_bodegaOrigen").trim());
	    	  	Long id_bodegaDestino = Long.parseLong(form.get("id_bodegaDestino").trim());
				try {
	    			Connection con = db.getConnection();
	    			BodegaEmpresa bodegaOrigen = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaOrigen);
	    			BodegaEmpresa bodegaDestino = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaDestino);
	    			String nameComercial = "";
	    			if(bodegaOrigen!=null && bodegaDestino!=null) {
	    				if((long) bodegaOrigen.getEsInterna() == (long)2) {
	    					nameComercial = bodegaOrigen.getNameComercial();
	    				}else if((long) bodegaDestino.getEsInterna() == (long)2) {
	    					nameComercial = bodegaDestino.getNameComercial();
	    				}
	    			}
	    			con.close();
    				return ok(nameComercial);
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
				return ok("error");
	       	}
    	}else {
    		return ok("error");
    	}
	}
	
	public Result nickNameClienteAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	    	  	Long id_bodegaOrigen = Long.parseLong(form.get("id_bodegaOrigen").trim());
	    	  	Long id_bodegaDestino = Long.parseLong(form.get("id_bodegaDestino").trim());
				
				try {
	    			Connection con = db.getConnection();
	    			BodegaEmpresa bodegaOrigen = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaOrigen);
	    			BodegaEmpresa bodegaDestino = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaDestino);
	    			
	    			String nickCliente = "No Aplica";
	    			if(bodegaOrigen!=null && bodegaDestino!=null) {
	    				if((long)bodegaOrigen.getEsInterna() == (long)2 ){
		    				nickCliente = bodegaOrigen.getNickCliente();
		    			}else if((long)bodegaDestino.getEsInterna() == (long)2 ){
		    				nickCliente = bodegaDestino.getNickCliente();
		    			}
	    			}
	    			con.close();
    				return ok(nickCliente);
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
				return ok("error");
	       	}
    	}else {
    		return ok("error");
    	}
	}
	
	public Result verificaNumeroGuiaAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	    	  	Long numeroGuia = Long.parseLong(form.get("numeroGuia").trim());
				try {
	    			Connection con = db.getConnection();
	    				if(Guia.existeNumero(con, s.baseDato, numeroGuia)) {
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
	
	public Result movimientoAplicar(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		
    		FormMovimiento form = formFactory.form(FormMovimiento.class).withDirectFieldAccess(true).bindFromRequest(request).get();
    		if (form.id_bodegaDestino==null || form.numeroGuia==null) {
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
	    			
	    			Long soloArriendo = (long) 1;
	    			if(mapeoPermiso.get("parametro.permiteDevolverVentas").equals("1")) {
	    				soloArriendo = (long) 0;
	    			}
	    			Map<String,Movimiento> mapStock = Inventarios.invPorIdBodega(con, s.baseDato, form.id_bodegaOrigen, soloArriendo);
	    			
	    			List<List<Double>> listaIdMovIdTipEstCant = FormMovimiento.create(con, s.baseDato, form, s.id_usuario, "0", mapStock);
	    			
	    			FormMovimiento.insertPreciosNuevos(con, s.baseDato, form, mapeoDiccionario);
	    			
	    			if(listaIdMovIdTipEstCant.size()>0) {
	    				FormMovimiento.moveSegunTipoEstado (con, s.baseDato, listaIdMovIdTipEstCant, s.id_usuario, "0");
	    			}
	    			
	    			if (archivos != null) {
	    				MnuMovimientos.grabarFilesThread grabarFile = new MnuMovimientos.grabarFilesThread(s.baseDato, archivos, nombreArchivoSinExtencion);
		    			grabarFile.run();
	    			}
	    			
	    			
	    			String fileNamePdf = FormMovimiento.generaGuiaPDF(con, s.baseDato, form, mapeoDiccionario, mapeoPermiso, Long.parseLong(s.id_usuario));
	    			
	    			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "movimiento", (long)0, "create", "ingresa un nuevo movimiento nro: "+form.numeroGuia);
	    			String titulo = "MOVIMIENTOS ENTRE "+mapeoDiccionario.get("BODEGA")+" O PROYECTOS";
	    			//String url = "%2FmovimientoSelectModificar%2F0";
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
	
	
	//======================================================================
    // MNU movimientoSelectModificar   Movimientos/Modificar
    //======================================================================
	
	
	
	public Result movimientoSelectModificarPeriodo(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("movimientoSelectModificar")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			
//    			Fechas hoy = Fechas.hoy();
//    			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
//    			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
//    			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			
    			Fechas hoy = Fechas.hoy();
    			String hasta = Fechas.addDias(hoy.getFechaCal(), 5).getFechaStrAAMMDD();
    			String desde = Fechas.addDias(hoy.getFechaCal(), -26).getFechaStrAAMMDD();
    			
    			con.close();
    			return ok(movimientoSelectModificarPeriodo.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
	
	public Result movimientoSelectModificar(Http.Request request) {
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
	    			if(mapeoPermiso.get("movimientoSelectModificar")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			
	    			con.close();
	    			return ok(movimientoSelectModificar.render(mapeoDiccionario,mapeoPermiso,userMnu, desdeAAMMDD, hastaAAMMDD));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
	
	public Result movimientoSelectModificar2(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
    		if (form.hasErrors()) {
    			return ok("error");
	       	}else {
	       		String desdeAAMMDD = form.get("fechaDesde").trim();
	       		String hastaAAMMDD = form.get("fechaHasta").trim();
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			if(mapeoPermiso.get("movimientoSelectModificar")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			
	    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
	    			if(permisoPorBodega.trim().length() > 5) {
	    				permisoPorBodega = permisoPorBodega.replaceAll("`movimiento`.`id_bodegaEmpresa`", "`guia`.`id_bodegaDestino`");
		    			permisoPorBodega = permisoPorBodega.replaceAll("and ", " ");
		    			permisoPorBodega = " and (" + permisoPorBodega + " or " + permisoPorBodega.replaceAll("`guia`.`id_bodegaDestino`", "`guia`.`id_bodegaOrigen`") + ") ";
	    			}
	    			
	    			List<Guia> listaGuias = Guia.allDesdeHastaSinNumNeg(con, s.baseDato, permisoPorBodega, desdeAAMMDD, hastaAAMMDD, s.aplicaPorSucursal, s.id_sucursal, false);
	    		
	
	    			String jsonGuias = Json.toJson(listaGuias).toString();
	    			
	    			con.close();
	    			return ok("{\"jsonGuias\":"+jsonGuias+"}").as("application/json");
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok("error");
    	}
    }
	
	
	public Result verCotizacionAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	    	  	Long id_cotizacion = Long.parseLong(form.get("id_cotizacion").trim());
				try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    				Cotizacion coti = Cotizacion.find(con, s.baseDato, id_cotizacion);
        			String tabla = Cotizacion.vistaModalVerCotizacion(con, s.baseDato, coti, mapeoDiccionario, mapeoPermiso);
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
	
	public Result movimientoOrigenDestinoModifica(Http.Request request) {
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
	    			
	    			if(mapeoPermiso.get("movimientoSelectModificar")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			
	    			Guia guia = Guia.find(con, s.baseDato, id_guia);
	    			
	    			if(guia == null || guia.getId_bodegaOrigen() == null) {
	    				con.close();
		       			return redirect("/home/");
		       		}
	    			
	    			
	    			BodegaEmpresa bodegaOrigen = BodegaEmpresa.findXIdBodega(con, s.baseDato, guia.getId_bodegaOrigen());
	    			BodegaEmpresa bodegaDestino = BodegaEmpresa.findXIdBodega(con, s.baseDato, guia.getId_bodegaDestino());
	    			
	    			
	    			if(bodegaOrigen!=null && bodegaOrigen.getVigente() == (long)0) {
	    				String msg = "No es posible modificar este movimiento, origen o destino esta no vigente, o ambos no vigentes";
    					con.close();
        				return ok(mensajes.render("/movimientoSelectModificarPeriodo/",msg));
	    			}
	    			
	    			if(bodegaDestino!=null && bodegaDestino.getVigente() == (long)0) {
	    				String msg = "No es posible modificar este movimiento, origen o destino esta no vigente, o ambos no vigentes";
    					con.close();
        				return ok(mensajes.render("/movimientoSelectModificarPeriodo/",msg));
	    			}
	    			
	    			
	    			
	    			List<List<String>> detalleGuia = new ArrayList<List<String>>();
					if((long) bodegaOrigen.esInterna == (long) 1) {
						detalleGuia = Guia.findDetalleGuiaOrigenDestinoYPrecios(con, s.baseDato, guia.getId(), guia.getId_bodegaDestino(), mapeoDiccionario.get("pais"), guia.getId_bodegaOrigen());
					}else {
						detalleGuia = Guia.findDetalleGuiaOrigenDestinoYPrecios(con, s.baseDato, guia.getId(), guia.getId_bodegaOrigen(), mapeoDiccionario.get("pais"), guia.getId_bodegaOrigen());
					}
	    			
	    			List<List<String>> listEquipBodOrigen = new ArrayList<List<String>>();
	    			List<List<String>> listEquipBodOrigenConStock = new ArrayList<List<String>>();
	    			
	    			Long soloArriendo = (long) 1;
	    			if(mapeoPermiso.get("parametro.permiteDevolverVentas").equals("1")) {
	    				soloArriendo = (long) 0;
	    			}
	    			
	    			
	    			Map<String,Movimiento> mapBodegaOrigen = Inventarios.invPorIdBodega(con, s.baseDato, guia.getId_bodegaOrigen(), soloArriendo);
	    			
	    			
	    			Map<Long,Equipo> mapEquipo = Equipo.mapAllVigentes(con, s.baseDato);
	    			Map<Long,Cotizacion> mapCotizacion = Cotizacion.mapAll(con, s.baseDato);
	    			Map<Long,Double> mapPeso = Atributo.mapAtributoPESO(con, s.baseDato);
	    			Map<Long,Double> mapM2 = Atributo.mapAtributoM2(con, s.baseDato);
	    			Map<Long,Ot> mapOt = Ot.mapAll(con, s.baseDato);
	    			
	    			mapBodegaOrigen.forEach((k,v)->{
	    				//if(v.getCantidad()>0) {
	    					Equipo equipo = mapEquipo.get(v.getId_equipo());
	    					
	    					if(equipo!=null) {
	    						String nomGrupo = equipo.grupo;
	    						
		        				Cotizacion coti = mapCotizacion.get(v.getId_cotizacion());
		        				Long numCoti = (long) 0;
		        				Long id_ot = (long)0;
		        				String numOt = "";
		        				String fechOt = "";
		        				if(coti!=null){
		        					numCoti = coti.getNumero();
		        					id_ot = coti.getId_ot();
		        					Ot ot = mapOt.get(id_ot);
		        					if(ot!=null) {
		        						numOt = ot.getNumero().toString();
		        						fechOt = ot.getFecha();
		        					}
		        				}
		        				
		        				Double peso = mapPeso.get(v.getId_equipo()); 
	        					if(peso==null){
	        						peso = (double)0;
	        					}
	        					
		        				Double m2 = mapM2.get(v.getId_equipo()); 
		        				if(m2==null){
		        					m2 = (double)0;
		        				}
		        				
		        				List<String> aux = new ArrayList<String>();
		        				aux.add(v.getId_equipo().toString()); 				// 0 id_equipo
		        				aux.add(v.getId_cotizacion().toString()); 			// 1 id_cotizacion
		        				aux.add(nomGrupo); 						// 2 nombre de grupo
		        				aux.add(numCoti.toString()); 						// 3 numero cotizacion
		        				aux.add(equipo.getCodigo()); 						// 4 codigo de equipo
		        				aux.add(equipo.getNombre()); 						// 5 nombre de equipo
		        				aux.add(myformatdouble2.format(peso)); 				// 6 KG por equipo
		        				aux.add(myformatdouble2.format(m2)); 				// 7 M2 por equipo
		        				aux.add(equipo.getUnidad());						// 8 unidad
		        				aux.add(myformatdouble2.format(v.getCantidad()));	// 9 stock disponible
		        				aux.add(id_ot.toString());							// 10 id_ot
		        				aux.add(numOt);										// 11 numero ot
		        				aux.add(fechOt);									// 12 fecha ot
		        				listEquipBodOrigen.add(aux);
		        				
		        				if(v.getCantidad()>0) {
		        					listEquipBodOrigenConStock.add(aux);
		        				}
		        				
	    					}
	    				//}
	    			});
	    			
	    			List<List<String>> listEquipNoEnBodOrigen = new ArrayList<List<String>>();
	    			Map<Long,Long> mapAux = new HashMap<Long,Long>();
	    			listEquipBodOrigenConStock.forEach(l->{
	    				mapAux.put(Long.parseLong(l.get(0)), Long.parseLong(l.get(0)));
	    			});
	    			
	    			Map<Long,List<String>> mapAuxiliar = new HashMap<Long,List<String>>();
	    			
	    			mapEquipo.forEach((k,v)->{
	    				Long aux = mapAux.get(k);
	    				if(aux==null) {
	    					List<String> aux2 = new ArrayList<String>();
	    					aux2.add(v.getId().toString()); 				// 0 id_equipo
	        				aux2.add("0"); 									// 1 id_cotizacion
	        				aux2.add(v.getGrupo()); 						// 2 nombre de grupo
	        				aux2.add(""); 									// 3 numero cotizacion
	        				aux2.add(v.getCodigo()); 						// 4 codigo de equipo
	        				aux2.add(v.getNombre()); 						// 5 nombre de equipo
	        				aux2.add(myformatdouble2.format(v.getKG())); 	// 6 KG por equipo
	        				aux2.add(myformatdouble2.format(v.getM2())); 	// 7 M2 por equipo
	        				aux2.add(v.getUnidad());						// 8 unidad
	        				aux2.add(myformatdouble2.format((double)0));	// 9 stock disponible
	        				mapAuxiliar.put(v.getId(), aux2);
	    				}
	    			});
	    			
	    			mapAuxiliar.forEach((k,v)->{
	    				listEquipNoEnBodOrigen.add(v);
	    			});
	    			
	    			List<TipoEstado> listTipoEstado = TipoEstado.all(con, s.baseDato);
	    			List<TipoReparacion> listTipoReparacion = TipoReparacion.all(con, s.baseDato);
	    			
	    			
	    			List<List<List<String>>> detalleMovimientoMasSinSaldo = Movimiento.detalleMovimientoParaModificar(con, s.baseDato, id_guia, detalleGuia, listEquipBodOrigen);
	    			
	    			List<List<String>> detalleMovimiento = detalleMovimientoMasSinSaldo.get(0);
	    			
	    			Long permiteExcedentes = (long) 0;
	    			if(guia.tipoGuia.equals(mapeoDiccionario.get("Despacho"))) {
	    				permiteExcedentes = (long) 0;
	    			}else if(guia.tipoGuia.equals(mapeoDiccionario.get("Traslado"))) {
	    				permiteExcedentes = (long) 0;
	    			}else {
	    				permiteExcedentes = (long) 1;
	    			}
	    			
	    			
	    			List<Transportista> listaTransporte = Transportista.listaTransportista(con, s.baseDato);
	    			
	    			Comercial comercial = new Comercial();
	    			if((long)bodegaOrigen.getEsInterna() == (long)2) {
	    				if(bodegaOrigen.getId_comercial().toString().equals("0")) {
	    					comercial.setId((long)-1);
		    				comercial.setNameUsuario("");
	    				}else {
	    					Comercial auxComercial = Comercial.findPorIdUsuario(con, s.baseDato, bodegaOrigen.getId_comercial().toString());
		    				if(auxComercial!=null) {
		    					comercial = auxComercial;
		    				}
	    				}
	    			}else if((long)bodegaDestino.getEsInterna() == (long)2){
	    				if(bodegaDestino.getId_comercial().toString().equals("0")) {
	    					comercial.setId((long)-1);
		    				comercial.setNameUsuario("");
	    				}else {
	    					Comercial auxComercial = Comercial.findPorIdUsuario(con, s.baseDato, bodegaDestino.getId_comercial().toString());
		    				if(auxComercial!=null) {
		    					comercial = auxComercial;
		    				}
	    				}
	    			}else {
	    				comercial.setId((long)0);
	    				comercial.setNameUsuario("");
	    			}
	    			
	    			Sucursal sucursalOrigen = Sucursal.find(con, s.baseDato, bodegaOrigen.getId_sucursal().toString());
	    			Sucursal sucursalDestino = Sucursal.find(con, s.baseDato, bodegaDestino.getId_sucursal().toString());
	    			
	    			String sinVenta="0";
	    			if(soloArriendo == (long)1 && bodegaOrigen.getEsInterna() == (long)2) {
	    				sinVenta = "1";
	    			}
	    			
	    			con.close();
	    			return ok(movimientoOrigenDestinoModifica.render(mapeoDiccionario,mapeoPermiso,userMnu,bodegaOrigen,
							listEquipNoEnBodOrigen,listTipoEstado,listTipoReparacion, guia, detalleMovimiento, permiteExcedentes, listaTransporte,
							sucursalOrigen, comercial,sucursalDestino, sinVenta));
	    			
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
	
	public Result movimientoOrigenDestinoElimina(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	    	  	Long id_guia = Long.parseLong(form.get("id_guia").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			if(mapeoPermiso.get("movimientoSelectModificar")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			if(Movimiento.esPosibleEliminar(con, s.baseDato, id_guia)) {
	    				Guia guia = Guia.find(con, s.baseDato, id_guia);
	    				String listaIdEliminados = Movimiento.listaIdEliminados(con, s.baseDato, id_guia);
	    				if(Movimiento.delete(con, s.baseDato, id_guia)) {
	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "movimiento", id_guia, "delete", "elimina movimiento guia nro "+guia.numero+" de fecha "+guia.fecha+" y sus movimientos asociados id: "+listaIdEliminados);
	        				con.close();
	            			return redirect("/movimientoSelectModificarPeriodo/");
	    				}else {
	    					String msg = "No es posible eliminar este movimiento, o su eliminacion provocaría inventarios negativos, o el origen y/o destino esta no vigente";
	    					con.close();
	        				return ok(mensajes.render("/movimientoSelectModificarPeriodo/",msg));
	    				}
	    			}else {
	    				String msg = "No es posible eliminar este movimiento, o su eliminacion provocaría inventarios negativos, o el origen y/o destino esta no vigente";
	    				con.close();
	    				return ok(mensajes.render("/movimientoSelectModificarPeriodo/",msg));
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
	
	public Result movimientoAplicarCambios(Http.Request request) {
		Sessiones s = new Sessiones(request);
		
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		
    		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    		
    		FormMovimiento form = formFactory.form(FormMovimiento.class).withDirectFieldAccess(true).bindFromRequest(request).get();
    		String titulo = "MOVIMIENTOS ENTRE "+mapeoDiccionario.get("BODEGA")+" O PROYECTOS";
    		if (form.id_bodegaDestino==null || form.numeroGuia==null) {
    			return ok(mensajes.render("/",msgErrorFormulario));
    		}else {
    			if(form.seModifico == (long) 0 ) {
    				return redirect("/movimientoSelectModificarPeriodo/");
    			}else {
    				Archivos archivos = formFactory.form(Archivos.class).withDirectFieldAccess(true).bindFromRequest(request).get();
	    			try {
		    			Connection con = db.getConnection();
		    			
		    			Guia auxGuia = Guia.find(con, s.baseDato, form.id_guia);
		    			Long id_userCrea = auxGuia.getId_userCrea();
		    			
		    			if(auxGuia != null && Movimiento.delete(con, s.baseDato, form.id_guia)) {
		    				
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
			    			
			    			Long soloArriendo = (long) 1;
			    			if(mapeoPermiso.get("parametro.permiteDevolverVentas").equals("1")) {
			    				soloArriendo = (long) 0;
			    			}
			    			Map<String,Movimiento> mapStock = Inventarios.invPorIdBodega(con, s.baseDato, form.id_bodegaOrigen, soloArriendo);
			    			
		    				List<List<Double>> listaIdMovIdTipEstCant = FormMovimiento.create(con, s.baseDato, form, id_userCrea.toString(), s.id_usuario, mapStock);
		    				
			    			FormMovimiento.insertPreciosNuevos(con, s.baseDato, form, mapeoDiccionario);
			    			
			    			if(listaIdMovIdTipEstCant.size()>0) {
			    				FormMovimiento.moveSegunTipoEstado (con, s.baseDato, listaIdMovIdTipEstCant, id_userCrea.toString(), s.id_usuario);
			    			}
			    			
			    			if (archivos != null) {
			    				MnuMovimientos.grabarFilesThread grabarFile = new MnuMovimientos.grabarFilesThread(s.baseDato, archivos, nombreArchivoSinExtencion);
				    			grabarFile.start();
			    			}
			    			
			    			
			    			String fileNamePdf = FormMovimiento.generaGuiaPDF(con, s.baseDato, form, mapeoDiccionario, mapeoPermiso, Long.parseLong(s.id_usuario));
			    			
			    			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "movimiento", (long)0, "update", "modifica movimiento nro: "+form.numeroGuia);
			    			
			    			//String url = "%2FmovimientoSelectModificar%2F0";
			    			String url = "%2Fhome%2F";
			    			con.close();
			    		
			    			return redirect("/routes2/redirShowPDF/"+fileNamePdf+","+titulo+","+url);
			    			
		    			}else {
		    				con.close();
		    				return ok(mensajes.render("/",msgError));
		    			}
	    			} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
    			}
    		}
    	}
    	return ok(mensajes.render("/",msgError));
    }
	
	
	public Result grabaAlbumFotosAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		
    		Archivos aux = formFactory.form(Archivos.class).withDirectFieldAccess(true).bindFromRequest(request).get();
    		
    		List<Http.MultipartFormData.FilePart<TemporaryFile>> archivos = aux.fotosAdjunto;
    		Long id_guia = aux.id_guia;
    		String nombre = aux.iniCarpeta;
    		
    		if (archivos != null) {
    			
	    		try {
	    			Connection con = db.getConnection();
	    			Guia auxGuia = Guia.find(con, s.baseDato, id_guia);
	    			String nombreCarpetaFotos = auxGuia.getFotos();
					if(auxGuia.getFotos().equals("0")) {
						nombreCarpetaFotos = nombre + auxGuia.numero +"_" + auxGuia.id;
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
					
					Guia.modificaPorCampo(con, s.baseDato, "fotos", id_guia, nombreCarpetaFotos);
					
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
	
	
	
	
	
	//======================================================================
    // MNU movimientoListar   Movimientos/Listar Movimientos/Sin Imagene
    //======================================================================
	
	public Result movimientoListarPeriodo(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("movimientoListar")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			
    			Fechas hoy = Fechas.hoy();
    			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
    			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			con.close();
    			return ok(movimientoListarPeriodo.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
	
	public Result movimientoListar(Http.Request request) {
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
	    			if(mapeoPermiso.get("movimientoListar")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			
	    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
	    			if(permisoPorBodega.trim().length() > 5) {
	    				permisoPorBodega = permisoPorBodega.replaceAll("`movimiento`.`id_bodegaEmpresa`", "`guia`.`id_bodegaDestino`");
		    			permisoPorBodega = permisoPorBodega.replaceAll("and ", " ");
		    			permisoPorBodega = " and (" + permisoPorBodega + " or " + permisoPorBodega.replaceAll("`guia`.`id_bodegaDestino`", "`guia`.`id_bodegaOrigen`") + ") ";
	    			}
	    			
	    			
	    			List<Guia> listaGuias = Guia.allDesdeHastaSinNumNeg(con, s.baseDato, permisoPorBodega, desdeAAMMDD, hastaAAMMDD, s.aplicaPorSucursal, s.id_sucursal, false);
	    			List<Transportista> listaTransporte = Transportista.listaTransportista(con, s.baseDato);
	    			
	    			con.close();
	    			return ok(movimientoListar.render(mapeoDiccionario,mapeoPermiso,userMnu,listaGuias, listaTransporte, desdeAAMMDD, hastaAAMMDD));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
	
	public Result movimientoListarExcel(Http.Request request) {
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
	    			if(mapeoPermiso.get("movimientoListar")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			
	    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
	    			if(permisoPorBodega.trim().length() > 5) {
	    				permisoPorBodega = permisoPorBodega.replaceAll("`movimiento`.`id_bodegaEmpresa`", "`guia`.`id_bodegaDestino`");
		    			permisoPorBodega = permisoPorBodega.replaceAll("and ", " ");
		    			permisoPorBodega = " and (" + permisoPorBodega + " or " + permisoPorBodega.replaceAll("`guia`.`id_bodegaDestino`", "`guia`.`id_bodegaOrigen`") + ") ";
	    			}
	    			
	    			List<Guia> listaGuias = Guia.allDesdeHastaSinNumNeg(con, s.baseDato, permisoPorBodega, desdeAAMMDD, hastaAAMMDD, s.aplicaPorSucursal, s.id_sucursal, false);
	    			File file = Guia.movimientoListarExcel(s.baseDato, listaGuias, mapeoDiccionario);
	   
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
	
	public Result movimientoDetalleGuia(Long id_guia, Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("movimientoListar")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			
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
    			
    			BodegaEmpresa bodegaDestino = BodegaEmpresa.findXIdBodega(con, s.baseDato, guia.getId_bodegaDestino());
    			
    			String nickCliente = "No Aplica";
    			if((long)bodegaOrigen.getEsInterna() == (long)2 ){
    				nickCliente = bodegaOrigen.getNickCliente();
    			}else if((long)bodegaDestino.getEsInterna() == (long)2 ){
    				nickCliente = bodegaDestino.getNickCliente();
    			}
    			
    			con.close();
    			return ok(movimientoDetalleGuia.render(mapeoDiccionario,mapeoPermiso,userMnu,guia, detalleGuia, bodegaOrigen, listTipoEstado, listTipoReparacion, nickCliente));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
	
	public Result generaGuiaXLS(Http.Request request){
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		
			DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("");
	       	}else {
	       		Long id_guia = Long.parseLong(form.get("id_guia").trim());
	       		Long id_transportista = Long.parseLong(form.get("id_transportista").trim());
	       		try {
		       		Connection con = db.getConnection();
		       		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		       		
		       		Guia guia = Guia.find(con, s.baseDato, id_guia);
	    			BodegaEmpresa bodegaOrigen = BodegaEmpresa.findXIdBodega(con, s.baseDato, guia.getId_bodegaOrigen());
	    			
	    			List<List<String>> detalleGuia = new ArrayList<List<String>>();
					if((long) bodegaOrigen.esInterna == (long) 1) {
						detalleGuia = Guia.findDetalleGuiaOrigenDestinoYPrecios(con, s.baseDato, guia.getId(), guia.getId_bodegaDestino(), mapeoDiccionario.get("pais"), guia.getId_bodegaOrigen());
					}else {
						detalleGuia = Guia.findDetalleGuiaOrigenDestinoYPrecios(con, s.baseDato, guia.getId(), guia.getId_bodegaOrigen(), mapeoDiccionario.get("pais"), guia.getId_bodegaOrigen());
					}
		       		Transportista transporte = Transportista.find(con, s.baseDato, id_transportista);
		       		Guia.modificaPorCampo(con, s.baseDato, "id_transportista", id_guia, id_transportista.toString());
		       		
		       		File file = null;
					if(bodegaOrigen.esInterna > (long)1){
						// devolucion
						file = XLSX_GuiaEntrada.generaGuiaEntradaXLS_TECA(con, s.baseDato, guia, detalleGuia, transporte);
					}else if( bodegaOrigen.esInterna == (long)1) {
						//despacho
						file = XLSX_GuiaSalida.generaGuiaXLS_TECA(con, s.baseDato, guia, detalleGuia, transporte);
					}
		       		
		       		if(file!=null) {
		       			con.close();
		       			return ok(file,false,Optional.of("guia_nro_"+guia.numero+".xlsx"));
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
	
	public Result generaGuiaExcel(Http.Request request){
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		
			DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("");
	       	}else {
	       		Long id_guia = Long.parseLong(form.get("id_guia").trim());
	       		try {
		       		Connection con = db.getConnection();
		       		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		       		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		       		
		       		Guia guia = Guia.find(con, s.baseDato, id_guia);
	    			BodegaEmpresa bodegaOrigen = BodegaEmpresa.findXIdBodega(con, s.baseDato, guia.getId_bodegaOrigen());
	    			
	    			
	    			List<List<String>> detalleGuia = new ArrayList<List<String>>();
					if((long) bodegaOrigen.esInterna == (long) 1) {
						detalleGuia = Guia.findDetalleGuiaOrigenDestinoYPrecios(con, s.baseDato, guia.getId(), guia.getId_bodegaDestino(), mapeoDiccionario.get("pais"), guia.getId_bodegaOrigen());
					}else {
						detalleGuia = Guia.findDetalleGuiaOrigenDestinoYPrecios(con, s.baseDato, guia.getId(), guia.getId_bodegaOrigen(), mapeoDiccionario.get("pais"), guia.getId_bodegaOrigen());
					}
					
					List<TipoEstado> listTipoEstado = TipoEstado.all(con, s.baseDato);
					
					File file = Guia.detalleGuiaExcel(s.baseDato, mapeoDiccionario, mapeoPermiso, guia, detalleGuia, listTipoEstado, bodegaOrigen);
					
					con.close();
					return ok(file,false,Optional.of("guia_nro_"+guia.numero+".xlsx"));
					
	       		} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok("");
	       	}
		}else {
			return ok("");
		}
   	}
	
	public Result guiaCambiarNroRef(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		
			DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("");
	       	}else {
	      		Long id_guia = Long.parseLong(form.get("id_guia"));
	      		String nroRef = form.get("nroRef").trim();
	      		try{
	      			Connection con = db.getConnection();
		      		
	      			Guia.modificaPorCampo(con, s.baseDato, "numGuiaCliente", id_guia, nroRef);
	      			
	      			con.close();
	      			return ok("ok");
	      		} catch (SQLException e) {
					e.printStackTrace();
				}
	      	}
	      	return ok("error");
	   	}
    	return ok("error");
	}
	
	public Result editaTransportistaAjax(Http.Request request){
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		
			DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("");
	       	}else {
	       		Long id_transportista = Long.parseLong(form.get("id_transportista").trim());
	       		try {
		       		Connection con = db.getConnection();
		       		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		       		String vista = Transportista.vistaEditaTransportista(con, s.baseDato, id_transportista, mapeoDiccionario);
		       		con.close();
		       		return ok(vista);
	       		} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok("");
	       	}
		}else {
			return ok("");
		}
   	}
	
	public Result nuevoTransportistaAjax(Http.Request request){
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		
			Transportista form = formFactory.form(Transportista.class).withDirectFieldAccess(true).bindFromRequest(request).get();
    		if (form.conductor==null || form.empresa==null) {
    			return ok("");
    		}else {
    			try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(Transportista.create(con, s.baseDato, form)){
	    				String vista = Transportista.vistaListado(con, s.baseDato, mapeoDiccionario);
	    				con.close();
	    				return ok(vista);
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
	
	public Result modificaTransportistaAjax(Http.Request request){
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		
			Transportista form = formFactory.form(Transportista.class).withDirectFieldAccess(true).bindFromRequest(request).get();
    		if (form.conductor==null || form.empresa==null) {
    			return ok("");
    		}else {
    			try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(Transportista.update(con, s.baseDato, form)){
	    				String vista = Transportista.vistaListado(con, s.baseDato, mapeoDiccionario);
	    				con.close();
	    				return ok(vista);
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
	
	public Result eliminaTransportistaAjax(Http.Request request){
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		
			DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("");
	       	}else {
	       		Long id_transportista = Long.parseLong(form.get("id_transportista").trim());
	       		try {
		       		Connection con = db.getConnection();
		       		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		       		if(Transportista.delete(con, s.baseDato, id_transportista)){
	    				String vista = Transportista.vistaListado(con, s.baseDato, mapeoDiccionario);
	    				con.close();
	    				return ok(vista);
	    			}else {
	    				con.close();
	    				return ok("FALSO");
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
	
	
	
	public Result generaGuiaXML(Http.Request request){
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		
			DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_guia = Long.parseLong(form.get("id_guia").trim());
	       		Long id_transportista = Long.parseLong(form.get("id_transportista").trim());
	       		try {
		       		Connection con = db.getConnection();
		       		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		       		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		       		
		       		Guia guia = Guia.find(con, s.baseDato, id_guia);
	    			BodegaEmpresa bodegaOrigen = BodegaEmpresa.findXIdBodega(con, s.baseDato, guia.getId_bodegaOrigen());
	    			
	    			List<List<String>> detalleGuia = new ArrayList<List<String>>();
					if((long) bodegaOrigen.esInterna == (long) 1) {
						detalleGuia = Guia.findDetalleGuiaOrigenDestinoYPrecios(con, s.baseDato, guia.getId(), guia.getId_bodegaDestino(), mapeoDiccionario.get("pais"), guia.getId_bodegaOrigen());
					}else {
						detalleGuia = Guia.findDetalleGuiaOrigenDestinoYPrecios(con, s.baseDato, guia.getId(), guia.getId_bodegaOrigen(), mapeoDiccionario.get("pais"), guia.getId_bodegaOrigen());
					}
					
		       		Transportista transporte = Transportista.find(con, s.baseDato, id_transportista);
		       		EmisorTributario emisorTributario = EmisorTributario.find(con, s.baseDato);
		       		
		       		Guia.modificaPorCampo(con, s.baseDato, "id_transportista", id_guia, id_transportista.toString());
		       		
		       		String archivoXml = XmlGuiaSalida.generaXmlGuiaSalida(con, s.baseDato, guia, detalleGuia, transporte, emisorTributario, mapeoPermiso);
		       		InputStream auxfile = Archivos.leerArchivo(s.baseDato+"/"+archivoXml);
		       		File file = Archivos.parseInputStreamToFile(auxfile);
		       		if(!file.canRead()){
		       			con.close();
		       			return ok("NO LEO");
		       		}else {
		       			Email email = new Email();
		       			String asunto = "Envio de XML desde MADA ";
		       			String desde = "desde MADA <informaciones@inqsol.cl>";
		       		    email.setSubject(asunto);
		       			email.setFrom(desde);
		       		    String correo=emisorTributario.emailEnvioXML;
		       		    email.setBodyHtml("<html>Archivo adjunto</html>");
		       		    email.addTo(correo);
		       		    email.addAttachment("guia.xml", file);
		       		    mailerClient.send(email);
		       			//MailerPlugin.send(email);
		       		    con.close();
		       			return ok(mensajes.render("/movimientoListarPeriodo/","DTE enviado enviado al correo: "+correo ));
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
	
	public Result generaGuiaApiManager(Http.Request request){
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		
			DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_guia = Long.parseLong(form.get("id_guia").trim());
	       		Long id_transportista = Long.parseLong(form.get("id_transportista").trim());
	       		try {
		       		Connection con = db.getConnection();
		       		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		       		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		       		
		       		Guia guiaFinal = Guia.find(con, s.baseDato, id_guia);
		       		Transportista transp = Transportista.find(con, s.baseDato, id_transportista);
		       		Guia.modificaPorCampo(con, s.baseDato, "id_transportista", id_guia, id_transportista.toString());
		       		
		       		ApiManagerDocDoc api = ApiManagerDocDoc.generaGuiaSalida(con, s.baseDato, guiaFinal, transp, mapeoDiccionario, mapeoPermiso);
		       		String jsonApi = Json.toJson(api).toString();
		       		Long id_proforma = (long)0;
		       		String rs = ApiManagerDocDoc.genera(con, s.baseDato, jsonApi, ws, id_proforma);
		       		Guia.modificaPorCampo(con, s.baseDato, "jsonGenerado", id_guia, jsonApi);
		       		Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "guia", id_guia, "update", "hace envio de guia API MANAGER nro: "+guiaFinal.getNumero());
		       		con.close();
		       		return ok(mensajes.render("/movimientoListarPeriodo/",rs));
	       		} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
		}
    	return ok(mensajes.render("/",msgError));
   	}
	
	public Result generaGuiaApiNubox(Http.Request request){
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		
			DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	   		}else {
	   			Long id_guia = Long.parseLong(form.get("id_guia").trim());
	       		Long id_transportista = Long.parseLong(form.get("id_transportista").trim());
	       		try {
		       		Connection con = db.getConnection();
		       		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		       		Guia guiaFinal = Guia.find(con, s.baseDato, id_guia);
		       		Transportista transp = Transportista.find(con, s.baseDato, id_transportista);
		       		ApiNuboxDocDoc api = ApiNuboxDocDoc.generaGuiaSalida(con, s.baseDato, guiaFinal, transp, mapeoDiccionario);
		       		Guia.modificaPorCampo(con, s.baseDato, "id_transportista", id_guia, id_transportista.toString());
		       		
		       		String jsonApi = Json.toJson(api).toString();
		       		Long id_proforma = (long)0;
		       		String rs = ApiNuboxDocDoc.genera(con, s.baseDato, jsonApi, ws, id_proforma, id_guia);
		       		Guia.modificaPorCampo(con, s.baseDato, "jsonGenerado", id_guia, jsonApi);
		       		Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "guia", id_guia, "update", "hace envio de guia API NUBOX nro: "+guiaFinal.getNumero());
		       		con.close();
		       		return ok(mensajes.render("/movimientoListarPeriodo/",rs));
	       		} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
		}
    	return ok(mensajes.render("/",msgError));
   	}

	public Result generaGuiaWebFacturacion(Http.Request request){
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		
			DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_guia = Long.parseLong(form.get("id_guia").trim());
	       		Long id_transportista = Long.parseLong(form.get("id_transportista").trim());
	       		try {
		       		Connection con = db.getConnection();
		       		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		       		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		       		
		       		Guia guia = Guia.find(con, s.baseDato, id_guia);
	    			BodegaEmpresa bodegaOrigen = BodegaEmpresa.findXIdBodega(con, s.baseDato, guia.getId_bodegaOrigen());
	    			
	    			List<List<String>> detalleGuia = new ArrayList<List<String>>();
					if((long) bodegaOrigen.esInterna == (long) 1) {
						detalleGuia = Guia.findDetalleGuiaOrigenDestinoYPrecios(con, s.baseDato, guia.getId(), guia.getId_bodegaDestino(), mapeoDiccionario.get("pais"), guia.getId_bodegaOrigen());
					}else {
						detalleGuia = Guia.findDetalleGuiaOrigenDestinoYPrecios(con, s.baseDato, guia.getId(), guia.getId_bodegaOrigen(), mapeoDiccionario.get("pais"), guia.getId_bodegaOrigen());
					}
					
		       		Transportista transporte = Transportista.find(con, s.baseDato, id_transportista);
		       		Guia.modificaPorCampo(con, s.baseDato, "id_transportista", id_guia, id_transportista.toString());
		       		
		       		EmisorTributario emisorTributario = EmisorTributario.find(con, s.baseDato);
		       		
		       		String archivoXml = WebFacturacionGuiaSalidaXml.generaXmlGuiaSalida(con, s.baseDato, guia, detalleGuia, transporte, emisorTributario, mapeoPermiso);
		       		
		       		
		       		String rs = WebFacturacion.genera(con, s.baseDato, archivoXml, ws, id_guia);
		       		Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "guia", id_guia, "update", "hace envio de guia API FACTURACION.COM nro: "+guia.getNumero());
		       		
		       		
		       		
		       		if(rs.equals("False")) {
		       			con.close();
		       			return ok(mensajes.render("/movimientoListarPeriodo/","SE PRESENTARON ERRORES"));
		       		}else {
		       			
		       			
		       			int ini = rs.indexOf("<Folio>");
			       		int fin = rs.indexOf("</Folio>");
			       		String folio = rs.substring(ini+7,fin);
		       			
		       			String link = WebFacturacion.obtieneLinkDte(con, s.baseDato, ws, folio);
		       			
		       			link = link.replace("http:", "https:");
		       			
		       			Guia.modificaPorCampo(con, s.baseDato, "linkFolio", id_guia, link);
		       			Guia.modificaPorCampo(con, s.baseDato, "numGuiaCliente", id_guia, folio);
		       			
		       			
		       			
		       			con.close();
		       			return ok(mensajes.render("/movimientoListarPeriodo/","GUIA ENVIADA"));
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
	
	
	
	
	
	public Result generaGuiaWebMaximise(Http.Request request){
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		
			DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_guia = Long.parseLong(form.get("id_guia").trim());
	       		Long id_transportista = Long.parseLong(form.get("id_transportista").trim());
	       		String sucurMaximise = form.get("sucurMaximise").trim();
	       		try {
		       		Connection con = db.getConnection();
		       		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		       		Guia guiaFinal = Guia.find(con, s.baseDato, id_guia);
		       		Transportista transp = Transportista.find(con, s.baseDato, id_transportista);
		       		EmisorTributario emisorTributario = EmisorTributario.find(con, s.baseDato);
		       		String xml = WebMaximise.generaGuiaSalida(con, s.baseDato, guiaFinal, transp, mapeoDiccionario, emisorTributario, sucurMaximise);
		       		Guia.modificaPorCampo(con, s.baseDato, "jsonGenerado", id_guia, xml);
		       		Guia.modificaPorCampo(con, s.baseDato, "id_transportista", id_guia, id_transportista.toString());
		       		String rs = WebMaximise.generaGuia(con, s.baseDato, xml, ws, emisorTributario, id_guia);
		       		try {
		       			Long nroInterno = Long.parseLong(rs);
		       			Guia.modificaPorCampo(con, s.baseDato, "linkFolio", id_guia, nroInterno.toString());
		       			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "guia", id_guia, "update", "hace envio de guia WS MAXIMISE nro interno: "+rs);
		       			rs = "Guía enviada a Maximise";
		       		}catch(Exception e) {
		       			rs = "ERROR: "+ rs;
		       		};
		       		con.close();
		       		return ok(mensajes.render("/movimientoListarPeriodo/",rs));
	       		} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    	return ok(mensajes.render("/movimientoListarPeriodo/","SE PRESENTARON ERRORES"));
	}
	
	public Result downGuiaMaximiseAjax(String nroIntGuia, Http.Request request){
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		
			DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		try {
	       			Connection con = db.getConnection();
		       		
		       		EmisorTributario emisorTributario = EmisorTributario.find(con, s.baseDato);
		       		File file = WebMaximise.downGuiaMaximise(con, s.baseDato, nroIntGuia, ws, emisorTributario);
		       		
		       		con.close();
		       		if(file!=null) {
		       			return ok(file,false,Optional.of(nroIntGuia+"_GuiaInterna.pdf"));
		       		}else {
		       			return ok(mensajes.render("/proformaLista/0","Documento aun no ha sido enviado al portal o inexistente."));
		       		}
	       		} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    	return ok(mensajes.render("/movimientoListarPeriodo/","SE PRESENTARON ERRORES"));
	}
	
	//======================================================================
    // MNU movimientoListarImg   Movimientos/Listar Movimientos/Con Imagenes
    //======================================================================
	
	public Result movimientoListarImgPeriodo(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("movimientoListar")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			
    			Fechas hoy = Fechas.hoy();
    			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
    			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			con.close();
    			return ok(movimientoListarImgPeriodo.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
	
	public Result movimientoListarImg(Http.Request request) {
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
	    			if(mapeoPermiso.get("movimientoListar")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			
	    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
	    			if(permisoPorBodega.trim().length() > 5) {
	    				permisoPorBodega = permisoPorBodega.replaceAll("`movimiento`.`id_bodegaEmpresa`", "`guia`.`id_bodegaDestino`");
		    			permisoPorBodega = permisoPorBodega.replaceAll("and ", " ");
		    			permisoPorBodega = " and (" + permisoPorBodega + " or " + permisoPorBodega.replaceAll("`guia`.`id_bodegaDestino`", "`guia`.`id_bodegaOrigen`") + ") ";
	    			}
	    			
	    			List<Guia> listaGuias = Guia.allDesdeHastaSinNumNeg(con, s.baseDato, permisoPorBodega, desdeAAMMDD, hastaAAMMDD, s.aplicaPorSucursal, s.id_sucursal, false);
	    			List<Transportista> listaTransporte = Transportista.listaTransportista(con, s.baseDato);
	   
	    			con.close();
	    			return ok(movimientoListarImg.render(mapeoDiccionario,mapeoPermiso,userMnu,listaGuias, listaTransporte));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
	
	public Result movimientoDetalleGuiaConImg(Long id_guia, Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("movimientoListar")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
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
    			
    			BodegaEmpresa bodegaDestino = BodegaEmpresa.findXIdBodega(con, s.baseDato, guia.getId_bodegaDestino());
    			
    			String nickCliente = "No Aplica";
    			if((long)bodegaOrigen.getEsInterna() == (long)2 ){
    				nickCliente = bodegaOrigen.getNickCliente();
    			}else if((long)bodegaDestino.getEsInterna() == (long)2 ){
    				nickCliente = bodegaDestino.getNickCliente();
    			}
    			
    			con.close();
    			return ok(movimientoDetalleGuiaConImg.render(mapeoDiccionario,mapeoPermiso,userMnu,guia, detalleGuia, bodegaOrigen, listTipoEstado, listTipoReparacion, nickCliente));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
	
	
	//======================================================================
    // MNU hojaChequeoSelectBodegaAgrupado   Movimientos/Hoja Chequeo (Agrupado)
    //======================================================================
	
	
	public Result hojaChequeoSelectBodegaAgrupado(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("hojaChequeoSelectBodega")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
    			List<List<String>> listBodegasConStock = Inventarios.listaBodegasConStock(con, s.baseDato, "3000-01-01", 
    					permisoPorBodega, s.aplicaPorSucursal, s.id_sucursal, mapeoDiccionario.get("ARRIENDO"));
    			
    			List<List<String>> listBodegas = BodegaEmpresa.listaAllBodegasVigentesExternasConStock(con, s.baseDato, mapeoPermiso, s.aplicaPorSucursal, s.id_sucursal,listBodegasConStock);
    			con.close();
    			return ok(hojaChequeoSelectBodegaAgrupado.render(mapeoDiccionario,mapeoPermiso,userMnu,listBodegas));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
	
	public Result hojaChequeoDetalleAgrupado(Long id_bodegaEmpresa, Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("hojaChequeoSelectBodega")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			BodegaEmpresa bodegaOrigen = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
    			List<List<String>> listEquipBodOrigen = new ArrayList<List<String>>();
    			Long soloArriendo = (long) 1;
    			if(mapeoPermiso.get("parametro.permiteDevolverVentas").equals("1")) {
    				soloArriendo = (long) 0;
    			}
    			Map<String,Movimiento> mapStock = Inventarios.invPorIdBodegaAgrupado(con, s.baseDato, id_bodegaEmpresa, soloArriendo);
    			Map<Long,Grupo> mapGrupo = Grupo.mapAll(con, s.baseDato);
    			Map<Long,Equipo> mapEquipo = Equipo.mapAllVigentes(con, s.baseDato);
    			Map<Long,Double> mapPeso = Atributo.mapAtributoPESO(con, s.baseDato);
    			Map<Long,Double> mapM2 = Atributo.mapAtributoM2(con, s.baseDato);
    			mapStock.forEach((k,v)->{
    				if(v.getCantidad()>0) {
    					Equipo equipo = mapEquipo.get(v.getId_equipo());
    					if(equipo!=null) {
    						Grupo grupo = mapGrupo.get(equipo.getId_grupo());
            				Double peso = mapPeso.get(v.getId_equipo()); if(peso==null){peso = (double)0;};
            				Double m2 = mapM2.get(v.getId_equipo()); if(m2==null){m2 = (double)0;};
            				List<String> aux = new ArrayList<String>();
            				aux.add(v.getId_equipo().toString()); 				// 0 id_equipo
            				aux.add(grupo.getNombre()); 						// nombre de grupo
            				aux.add(equipo.getCodigo()); 						// codigo de equipo
            				aux.add(equipo.getNombre()); 						// nombre de equipo
            				aux.add(myformatdouble2.format(peso)); 				// KG por equipo
            				aux.add(myformatdouble2.format(m2)); 				// M2 por equipo
            				aux.add(equipo.getUnidad());						// unidad
            				aux.add(myformatdouble2.format(v.getCantidad()));	// stock disponible
            				listEquipBodOrigen.add(aux);
    					}
    				}
    			});
    			if(listEquipBodOrigen.size()==0) {
    				String mensaje = mapeoDiccionario.get("Bodega")+"/Proyecto: "+bodegaOrigen.nombre+" no posee equipos (no hay existencia)";
    				con.close();
    				return ok(mensajes.render("/hojaChequeoSelectBodegaAgrupado/",mensaje));
    			}
    			List<TipoEstado> listTipoEstado = TipoEstado.all(con, s.baseDato);
    			
    			List<ContactoBodegaEmpresa> listContactos = ContactoBodegaEmpresa.allxBodega(con, s.baseDato, bodegaOrigen.getId());
    			String contactos = "";
           	   	if(listContactos.size() == 0) {
           		   contactos="";
           	   	}
           	   	for(ContactoBodegaEmpresa c: listContactos) {
           		 String nombre = c.getNombre();
           		   contactos += "\n-"+nombre+", Tel:"+c.getMovil().toLowerCase()+", "+c.getMail().toLowerCase();
           	   	}
           	   	Proyecto proyecto = Proyecto.find(con, s.baseDato, bodegaOrigen.getId_proyecto());
           	   	String direccion = proyecto.getDireccion()+", "+proyecto.getComuna()+", "+proyecto.getRegion();
           	   	
    			con.close();
    			return ok(hojaChequeoDetalleAgrupado.render(mapeoDiccionario,mapeoPermiso,userMnu,bodegaOrigen,listEquipBodOrigen,listTipoEstado,contactos, direccion));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }

	public Result hojaChequeoAgrupadoExcel(Http.Request request){
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		
			DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
	       		try {
		       		Connection con = db.getConnection();
		       		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		       		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		       		BodegaEmpresa bodegaOrigen = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
	    			List<List<String>> listEquipBodOrigen = new ArrayList<List<String>>();
	    			Long soloArriendo = (long) 1;
	    			if(mapeoPermiso.get("parametro.permiteDevolverVentas").equals("1")) {
	    				soloArriendo = (long) 0;
	    			}
	    			Map<String,Movimiento> mapStock = Inventarios.invPorIdBodegaAgrupado(con, s.baseDato, id_bodegaEmpresa, soloArriendo);
	    			Map<Long,Grupo> mapGrupo = Grupo.mapAll(con, s.baseDato);
	    			Map<Long,Equipo> mapEquipo = Equipo.mapAllVigentes(con, s.baseDato);
	    			Map<Long,Double> mapPeso = Atributo.mapAtributoPESO(con, s.baseDato);
	    			Map<Long,Double> mapM2 = Atributo.mapAtributoM2(con, s.baseDato);
	    			mapStock.forEach((k,v)->{
	    				if(v.getCantidad()>0) {
	    					Equipo equipo = mapEquipo.get(v.getId_equipo());
	    					if(equipo!=null) {
	    						Grupo grupo = mapGrupo.get(equipo.getId_grupo());
		        				Double peso = mapPeso.get(v.getId_equipo()); if(peso==null){peso = (double)0;};
		        				Double m2 = mapM2.get(v.getId_equipo()); if(m2==null){m2 = (double)0;};
		        				List<String> aux = new ArrayList<String>();
		        				aux.add(v.getId_equipo().toString()); 				// 0 id_equipo
		        				aux.add(grupo.getNombre()); 						// nombre de grupo
		        				aux.add(equipo.getCodigo()); 						// codigo de equipo
		        				aux.add(equipo.getNombre()); 						// nombre de equipo
		        				aux.add(myformatdouble2.format(peso)); 				// KG por equipo
		        				aux.add(myformatdouble2.format(m2)); 				// M2 por equipo
		        				aux.add(equipo.getUnidad());						// unidad
		        				aux.add(myformatdouble2.format(v.getCantidad()));	// stock disponible
		        				listEquipBodOrigen.add(aux);
	    					}
	    				}
	    			});
	    			List<TipoEstado> listTipoEstado = TipoEstado.all(con, s.baseDato);
	    			List<ContactoBodegaEmpresa> listContactos = ContactoBodegaEmpresa.allxBodega(con, s.baseDato, bodegaOrigen.getId());
	    			String contactos = "CONTACTOS: ";
	           	   	if(listContactos.size() == 0) {
	           		   contactos="";
	           	   	}
	           	   	for(ContactoBodegaEmpresa c: listContactos) {
	           		 String nombre = c.getNombre();
	           		   contactos += "\n-"+nombre+", Tel:"+c.getMovil().toLowerCase()+", "+c.getMail().toLowerCase();
	           	   	}
	           	   	
	           	   	Proyecto proyecto = Proyecto.find(con, s.baseDato, bodegaOrigen.getId_proyecto());
	        	   	String direccion = proyecto.getDireccion()+", "+proyecto.getComuna()+", "+proyecto.getRegion();
	        	   	
	           	   	String sinCant = mapeoPermiso.get("parametro.ocultar-cant-hoja-chequeo");
	           	   	if(sinCant == null) {
	           	   		sinCant = "0";
	           	   	}
	    			File file = MovimHojaChequeo.hojaChequeoAgrupadoXlsx(s.baseDato, mapeoDiccionario, bodegaOrigen, listEquipBodOrigen, listTipoEstado, contactos, direccion, sinCant);
		       		if(file!=null) {
		       			con.close();
		       			return ok(file,false,Optional.of("HojaChequeoPorCodigo.xlsx"));
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
	
	public Result hojaChequeoSelectBodega(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("hojaChequeoSelectBodega")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
    			List<List<String>> listBodegasConStock = Inventarios.listaBodegasConStock(con, s.baseDato, "3000-01-01", 
    					permisoPorBodega, s.aplicaPorSucursal, s.id_sucursal, mapeoDiccionario.get("ARRIENDO"));
    			
    			List<List<String>> listBodegas = BodegaEmpresa.listaAllBodegasVigentesExternasConStock(con, s.baseDato, mapeoPermiso, s.aplicaPorSucursal, s.id_sucursal,listBodegasConStock);
    			
    			con.close();
    			return ok(hojaChequeoSelectBodega.render(mapeoDiccionario,mapeoPermiso,userMnu,listBodegas));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
	
	public Result hojaChequeoDetalle(Long id_bodegaEmpresa, Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("hojaChequeoSelectBodega")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			BodegaEmpresa bodegaOrigen = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
    			List<List<String>> listEquipBodOrigen = new ArrayList<List<String>>();
    			Long soloArriendo = (long) 1;
    			if(mapeoPermiso.get("parametro.permiteDevolverVentas").equals("1")) {
    				soloArriendo = (long) 0;
    			}
    			Map<String,Movimiento> mapStock = Inventarios.invPorIdBodega(con, s.baseDato, id_bodegaEmpresa, soloArriendo);
    			Map<Long,Grupo> mapGrupo = Grupo.mapAll(con, s.baseDato);
    			Map<Long,Equipo> mapEquipo = Equipo.mapAllVigentes(con, s.baseDato);
    			Map<Long,Cotizacion> mapCotizacion = Cotizacion.mapAll(con, s.baseDato);
    			Map<Long,Double> mapPeso = Atributo.mapAtributoPESO(con, s.baseDato);
    			Map<Long,Double> mapM2 = Atributo.mapAtributoM2(con, s.baseDato);
    			mapStock.forEach((k,v)->{
    				if(v.getCantidad()>0) {
    					Equipo equipo = mapEquipo.get(v.getId_equipo());
    					if(equipo!=null) {
    						Grupo grupo = mapGrupo.get(equipo.getId_grupo());
            				Cotizacion coti = mapCotizacion.get(v.getId_cotizacion());
            				Long numCoti = (long) 0; if(coti!=null){numCoti = coti.getNumero();};
            				Double peso = mapPeso.get(v.getId_equipo()); if(peso==null){peso = (double)0;};
            				Double m2 = mapM2.get(v.getId_equipo()); if(m2==null){m2 = (double)0;};
            				List<String> aux = new ArrayList<String>();
            				aux.add(v.getId_equipo().toString()); 				// 0 id_equipo
            				aux.add(v.getId_cotizacion().toString()); 			// 1 id_cotizacion
            				aux.add(grupo.getNombre()); 						// 2 nombre de grupo
            				aux.add(numCoti.toString()); 						// 3 numero cotizacion
            				aux.add(equipo.getCodigo()); 						// 4 codigo de equipo
            				aux.add(equipo.getNombre()); 						// 5 nombre de equipo
            				aux.add(myformatdouble2.format(peso)); 				// 6 KG por equipo
            				aux.add(myformatdouble2.format(m2)); 				// 7 M2 por equipo
            				aux.add(equipo.getUnidad());						// 8 unidad
            				aux.add(myformatdouble2.format(v.getCantidad()));	// 9 stock disponible
            				listEquipBodOrigen.add(aux);
    					}
    				}
    			});
    			if(listEquipBodOrigen.size()==0) {
    				String mensaje = mapeoDiccionario.get("Bodega")+"/Proyecto: "+bodegaOrigen.nombre+" no posee equipos (no hay existencia)";
    				con.close();
    				return ok(mensajes.render("/hojaChequeoSelectBodega/",mensaje));
    			}
    			List<TipoEstado> listTipoEstado = TipoEstado.all(con, s.baseDato);
    			List<ContactoBodegaEmpresa> listContactos = ContactoBodegaEmpresa.allxBodega(con, s.baseDato, bodegaOrigen.getId());
    			String contactos = "CONTACTOS: ";
           	   	if(listContactos.size() == 0) {
           		   contactos="";
           	   	}
           	   	for(ContactoBodegaEmpresa c: listContactos) {
           		 String nombre = c.getNombre();
           		   contactos += "\n-"+nombre+", Tel:"+c.getMovil().toLowerCase()+", "+c.getMail().toLowerCase();
           	   	}
           	   	
           	   	Proyecto proyecto = Proyecto.find(con, s.baseDato, bodegaOrigen.getId_proyecto());
        	   	String direccion = proyecto.getDireccion()+", "+proyecto.getComuna()+", "+proyecto.getRegion();
        	   	
    			con.close();
    			return ok(hojaChequeoDetalle.render(mapeoDiccionario,mapeoPermiso,userMnu,bodegaOrigen,listEquipBodOrigen,listTipoEstado,contactos, direccion));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
	
	public Result hojaChequeoExcel(Http.Request request){
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		
			DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
	       		try {
		       		Connection con = db.getConnection();
		       		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		       		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		       		BodegaEmpresa bodegaOrigen = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
	    			List<List<String>> listEquipBodOrigen = new ArrayList<List<String>>();
	    			Long soloArriendo = (long) 1;
	    			if(mapeoPermiso.get("parametro.permiteDevolverVentas").equals("1")) {
	    				soloArriendo = (long) 0;
	    			}
	    			Map<String,Movimiento> mapStock = Inventarios.invPorIdBodega(con, s.baseDato, id_bodegaEmpresa, soloArriendo);
	    			Map<Long,Grupo> mapGrupo = Grupo.mapAll(con, s.baseDato);
	    			Map<Long,Equipo> mapEquipo = Equipo.mapAllVigentes(con, s.baseDato);
	    			Map<Long,Cotizacion> mapCotizacion = Cotizacion.mapAll(con, s.baseDato);
	    			Map<Long,Double> mapPeso = Atributo.mapAtributoPESO(con, s.baseDato);
	    			Map<Long,Double> mapM2 = Atributo.mapAtributoM2(con, s.baseDato);
	    			mapStock.forEach((k,v)->{
	    				if(v.getCantidad()>0) {
	    					Equipo equipo = mapEquipo.get(v.getId_equipo());
	    					if(equipo!=null) {
	    						Grupo grupo = mapGrupo.get(equipo.getId_grupo());
		        				Cotizacion coti = mapCotizacion.get(v.getId_cotizacion());
		        				Long numCoti = (long) 0; if(coti!=null){numCoti = coti.getNumero();};
		        				Double peso = mapPeso.get(v.getId_equipo()); if(peso==null){peso = (double)0;};
		        				Double m2 = mapM2.get(v.getId_equipo()); if(m2==null){m2 = (double)0;};
		        				List<String> aux = new ArrayList<String>();
		        				aux.add(v.getId_equipo().toString()); 				// 0 id_equipo
		        				aux.add(v.getId_cotizacion().toString()); 			// 1 id_cotizacion
		        				aux.add(grupo.getNombre()); 						// 2 nombre de grupo
		        				aux.add(numCoti.toString()); 						// 3 numero cotizacion
		        				aux.add(equipo.getCodigo()); 						// 4 codigo de equipo
		        				aux.add(equipo.getNombre()); 						// 5 nombre de equipo
		        				aux.add(myformatdouble2.format(peso)); 				// 6 KG por equipo
		        				aux.add(myformatdouble2.format(m2)); 				// 7 M2 por equipo
		        				aux.add(equipo.getUnidad());						// 8 unidad
		        				aux.add(myformatdouble2.format(v.getCantidad()));	// 9 stock disponible
		        				listEquipBodOrigen.add(aux);
	    					}
	    				}
	    			});
	    			if(listEquipBodOrigen.size()==0) {
	    				String mensaje = mapeoDiccionario.get("Bodega")+"/Proyecto: "+bodegaOrigen.nombre+" no posee equipos (no hay existencia)";
	    				con.close();
	    				return ok(mensajes.render("/hojaChequeoSelectBodega/",mensaje));
	    			}
	    			List<TipoEstado> listTipoEstado = TipoEstado.all(con, s.baseDato);
	    			List<ContactoBodegaEmpresa> listContactos = ContactoBodegaEmpresa.allxBodega(con, s.baseDato, bodegaOrigen.getId());
	    			String contactos = "CONTACTOS: ";
	           	   	if(listContactos.size() == 0) {
	           		   contactos="";
	           	   	}
	           	   	for(ContactoBodegaEmpresa c: listContactos) {
	           		 String nombre = c.getNombre();
	           		   contactos += "\n-"+nombre+", Tel:"+c.getMovil().toLowerCase()+", "+c.getMail().toLowerCase();
	           	   	}
	           	   	
	           	   	Proyecto proyecto = Proyecto.find(con, s.baseDato, bodegaOrigen.getId_proyecto());
	        	   	String direccion = proyecto.getDireccion()+", "+proyecto.getComuna()+", "+proyecto.getRegion();
	        	   	
	        	   	
	           	   	String sinCant = mapeoPermiso.get("parametro.ocultar-cant-hoja-chequeo");
	           	   	if(sinCant == null) {
	           	   		sinCant = "0";
	           	   	}
	    			File file = MovimHojaChequeo.hojaChequeoXlsx(s.baseDato, mapeoDiccionario, bodegaOrigen, listEquipBodOrigen, listTipoEstado, contactos, direccion, sinCant);
		       		if(file!=null) {
		       			con.close();
		       			return ok(file,false,Optional.of("HojaChequeoPorCodigoYCoti.xlsx"));
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
    // MNU hojaChequeoDetallePorGrupo   Movimientos/Hoja Chequeo (Agrupado por grupo)
    //====================================================================================
	
	
	public Result hojaChequeoSelectPorGrupo(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("hojaChequeoSelectBodega")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
    			List<List<String>> listBodegasConStock = Inventarios.listaBodegasConStock(con, s.baseDato, "3000-01-01", 
    					permisoPorBodega, s.aplicaPorSucursal, s.id_sucursal, mapeoDiccionario.get("ARRIENDO"));
    			
    			List<List<String>> listBodegas = BodegaEmpresa.listaAllBodegasVigentesExternasConStock(con, s.baseDato, mapeoPermiso, s.aplicaPorSucursal, s.id_sucursal,listBodegasConStock);
    			List<Grupo> listGrupos = Grupo.all(con, s.baseDato);
    			con.close();
    			return ok(hojaChequeoSelectPorGrupo.render(mapeoDiccionario,mapeoPermiso,userMnu,listBodegas,listGrupos));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
	
	public Result hojaChequeoDetallePorGrupo(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		FormMovimiento form = formFactory.form(FormMovimiento.class).withDirectFieldAccess(true).bindFromRequest(request).get();
    		if (form.id_bodegaEmpresa==null) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_bodegaEmpresa = form.id_bodegaEmpresa;
	       		List<Long> idGrupos = form.idGrupos;
	       		Map<Long,Long> mapIdGrupos = new HashMap<Long,Long>();
	       		for(Long x: idGrupos) {
	       			mapIdGrupos.put(x, x);
	       		}
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("hojaChequeoSelectBodega")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			BodegaEmpresa bodegaOrigen = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
	    			List<List<String>> listEquipBodOrigen = new ArrayList<List<String>>();
	    			Long soloArriendo = (long) 1;
	    			if(mapeoPermiso.get("parametro.permiteDevolverVentas").equals("1")) {
	    				soloArriendo = (long) 0;
	    			}
	    			Map<String,Movimiento> mapStock = Inventarios.invPorIdBodegaAgrupado(con, s.baseDato, id_bodegaEmpresa, soloArriendo);
	    			Map<Long,Grupo> mapGrupo = Grupo.mapAll(con, s.baseDato);
	    			Map<Long,Equipo> mapEquipo = Equipo.mapAllVigentes(con, s.baseDato);
	    			Map<Long,Double> mapPeso = Atributo.mapAtributoPESO(con, s.baseDato);
	    			Map<Long,Double> mapM2 = Atributo.mapAtributoM2(con, s.baseDato);
	    			
	    			mapStock.forEach((k,v)->{
	    				if(v.getCantidad()>0) {
	    					Equipo equipo = mapEquipo.get(v.getId_equipo());
	    					if(equipo!=null) {
	    						Grupo grupo = mapGrupo.get(equipo.getId_grupo());
		        				Double peso = mapPeso.get(v.getId_equipo()); if(peso==null){peso = (double)0;};
		        				Double m2 = mapM2.get(v.getId_equipo()); if(m2==null){m2 = (double)0;};
		        				
		        				Long id_grupo = mapIdGrupos.get(grupo.id);
		        				
		        				if(id_grupo != null && id_grupo > 0) {
		        					List<String> aux = new ArrayList<String>();
			        				aux.add(v.getId_equipo().toString()); 				// 0 id_equipo
			        				aux.add(grupo.getNombre()); 						// nombre de grupo
			        				aux.add(equipo.getCodigo()); 						// codigo de equipo
			        				aux.add(equipo.getNombre()); 						// nombre de equipo
			        				aux.add(myformatdouble2.format(peso)); 				// KG por equipo
			        				aux.add(myformatdouble2.format(m2)); 				// M2 por equipo
			        				aux.add(equipo.getUnidad());						// unidad
			        				aux.add(myformatdouble2.format(v.getCantidad()));	// stock disponible
			        				listEquipBodOrigen.add(aux);
		        				}
	    					}
	    				}
	    			});
	    			if(listEquipBodOrigen.size()==0) {
	    				String mensaje = mapeoDiccionario.get("Bodega")+"/Proyecto: "+bodegaOrigen.nombre+" no posee equipos (no hay existencia) con el o los grupos seleccionados";
	    				con.close();
	    				return ok(mensajes.render("/hojaChequeoSelectPorGrupo/",mensaje));
	    			}
	    			List<TipoEstado> listTipoEstado = TipoEstado.all(con, s.baseDato);
	    			List<ContactoBodegaEmpresa> listContactos = ContactoBodegaEmpresa.allxBodega(con, s.baseDato, bodegaOrigen.getId());
	    			String contactos = "CONTACTOS: ";
	           	   	if(listContactos.size() == 0) {
	           		   contactos="";
	           	   	}
	           	   	for(ContactoBodegaEmpresa c: listContactos) {
	           		 String nombre = c.getNombre();
	           		   contactos += "\n-"+nombre+", Tel:"+c.getMovil().toLowerCase()+", "+c.getMail().toLowerCase();
	           	   	}
	           	   	
	           	   	Proyecto proyecto = Proyecto.find(con, s.baseDato, bodegaOrigen.getId_proyecto());
	        	   	String direccion = proyecto.getDireccion()+", "+proyecto.getComuna()+", "+proyecto.getRegion();
	        	   	
	    			con.close();
	    			return ok(hojaChequeoDetallePorGrupo.render(mapeoDiccionario,mapeoPermiso,userMnu,bodegaOrigen,listEquipBodOrigen,listTipoEstado, idGrupos, contactos, direccion));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    	return ok(mensajes.render("/",msgError));
    }

	public Result hojaChequeoDetallePorGrupoExcel(Http.Request request){
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		
    		FormMovimiento form = formFactory.form(FormMovimiento.class).withDirectFieldAccess(true).bindFromRequest(request).get();
    		if (form.id_bodegaEmpresa==null) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_bodegaEmpresa = form.id_bodegaEmpresa;
	       		List<Long> idGrupos = form.idGrupos;
	       		Map<Long,Long> mapIdGrupos = new HashMap<Long,Long>();
	       		for(Long x: idGrupos) {
	       			mapIdGrupos.put(x, x);
	       		}
	    		try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("hojaChequeoSelectBodega")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			BodegaEmpresa bodegaOrigen = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
	    			List<List<String>> listEquipBodOrigen = new ArrayList<List<String>>();
	    			Long soloArriendo = (long) 1;
	    			if(mapeoPermiso.get("parametro.permiteDevolverVentas").equals("1")) {
	    				soloArriendo = (long) 0;
	    			}
	    			Map<String,Movimiento> mapStock = Inventarios.invPorIdBodegaAgrupado(con, s.baseDato, id_bodegaEmpresa, soloArriendo);
	    			Map<Long,Grupo> mapGrupo = Grupo.mapAll(con, s.baseDato);
	    			Map<Long,Equipo> mapEquipo = Equipo.mapAllVigentes(con, s.baseDato);
	    			Map<Long,Double> mapPeso = Atributo.mapAtributoPESO(con, s.baseDato);
	    			Map<Long,Double> mapM2 = Atributo.mapAtributoM2(con, s.baseDato);
	    			mapStock.forEach((k,v)->{
	    				if(v.getCantidad()>0) {
	    					Equipo equipo = mapEquipo.get(v.getId_equipo());
	    					if(equipo!=null) {
	    						Grupo grupo = mapGrupo.get(equipo.getId_grupo());
		        				Double peso = mapPeso.get(v.getId_equipo()); if(peso==null){peso = (double)0;};
		        				Double m2 = mapM2.get(v.getId_equipo()); if(m2==null){m2 = (double)0;};
		        				
		        				Long id_grupo = mapIdGrupos.get(grupo.id);
		        				if(id_grupo != null && id_grupo > 0) {
		        					List<String> aux = new ArrayList<String>();
			        				aux.add(v.getId_equipo().toString()); 				// 0 id_equipo
			        				aux.add(grupo.getNombre()); 						// nombre de grupo
			        				aux.add(equipo.getCodigo()); 						// codigo de equipo
			        				aux.add(equipo.getNombre()); 						// nombre de equipo
			        				aux.add(myformatdouble2.format(peso)); 				// KG por equipo
			        				aux.add(myformatdouble2.format(m2)); 				// M2 por equipo
			        				aux.add(equipo.getUnidad());						// unidad
			        				aux.add(myformatdouble2.format(v.getCantidad()));	// stock disponible
			        				listEquipBodOrigen.add(aux);
		        				}
	    					}
	    				}
	    			});
	    			List<TipoEstado> listTipoEstado = TipoEstado.all(con, s.baseDato);
	    			
	    			File file = null;
	    			if(mapeoDiccionario.get("nEmpresa").equals("HOHE")) {
	    				file = MovimHojaChequeo.hojaChequeoAgrupadoXlsxSoloHOHE(s.baseDato, mapeoDiccionario, bodegaOrigen, listEquipBodOrigen, listTipoEstado);
	    			}else {
	    				List<ContactoBodegaEmpresa> listContactos = ContactoBodegaEmpresa.allxBodega(con, s.baseDato, bodegaOrigen.getId());
		    			String contactos = "CONTACTOS: ";
		           	   	if(listContactos.size() == 0) {
		           		   contactos="";
		           	   	}
		           	   	for(ContactoBodegaEmpresa c: listContactos) {
		           		 String nombre = c.getNombre();
		           		   contactos += "\n-"+nombre+", Tel:"+c.getMovil().toLowerCase()+", "+c.getMail().toLowerCase();
		           	   	}
		           	   	
		           	   	Proyecto proyecto = Proyecto.find(con, s.baseDato, bodegaOrigen.getId_proyecto());
		        	   	String direccion = proyecto.getDireccion()+", "+proyecto.getComuna()+", "+proyecto.getRegion();
		        	   	
		           	   	String sinCant = mapeoPermiso.get("parametro.ocultar-cant-hoja-chequeo");
		           	   	if(sinCant == null) {
		           	   		sinCant = "0";
		           	   	}
	    				file = MovimHojaChequeo.hojaChequeoAgrupadoXlsx(s.baseDato, mapeoDiccionario, bodegaOrigen, listEquipBodOrigen, listTipoEstado,contactos,direccion,sinCant);
	    			}
		       		if(file!=null) {
		       			con.close();
		       			return ok(file,false,Optional.of("HojaChequeoPorGrupo.xlsx"));
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







