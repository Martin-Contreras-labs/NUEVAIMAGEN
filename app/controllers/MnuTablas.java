package controllers;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import controllers.HomeController.Sessiones;
import models.calculo.Inventarios;
import models.forms.FormClienteGraba;
import models.forms.FormContactoClienteGraba;
import models.forms.FormContactoFabricaGraba;
import models.forms.FormContactoProveedorGraba;
import models.forms.FormContactoProyectoGraba;
import models.forms.FormEquipoGraba;
import models.forms.FormFabricaGraba;
import models.forms.FormProveedorGraba;
import models.forms.FormProyectoGraba;
import models.forms.FormTipoEstadoGraba;
import models.forms.FormTipoReparacionGraba;
import models.forms.FormUsuarioGraba;
import models.reports.ReportInventarios;
import models.tables.Atributo;
import models.tables.BodegaEmpresa;
import models.tables.Cliente;
import models.tables.Comercial;
import models.tables.Compra;
import models.tables.Comunas;
import models.tables.ContactoCliente;
import models.tables.ContactoFabrica;
import models.tables.ContactoProveedor;
import models.tables.ContactoProyecto;
import models.tables.Equipo;
import models.tables.Fabrica;
import models.tables.Grupo;
import models.tables.Moneda;
import models.tables.Precio;
import models.tables.Proveedor;
import models.tables.Proyecto;
import models.tables.Regiones;
import models.tables.Sucursal;
import models.tables.TipoEstado;
import models.tables.TipoReparacion;
import models.tables.Transportista;
import models.tables.Unidad;
import models.tables.UnidadTiempo;
import models.tables.Usuario;
import models.tables.UsuarioPermiso;
import models.tables.UsuarioTipo;
import models.utilities.Archivos;
import models.utilities.Fechas;
import models.utilities.Registro;
import models.utilities.UserMnu;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.Database;
import play.libs.Files.TemporaryFile;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.mensajes;
import viewsMnuTablas.html.*;


/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class MnuTablas extends Controller {
	
   
	public static Database db = HomeController.dbWrite;
	public static FormFactory formFactory = HomeController.formFactory;
	public static String msgError = HomeController.msgError;
	public static String msgErrorFormulario = HomeController.msgErrorFormulario;
	public static String msgSinPermiso = HomeController.msgSinPermiso;
	
    
    
    //============================================================
    // MNU precioMantencionListaGrupo   Tablas/Precios
    //============================================================
	
	
	

    public Result precioMantencion(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	       		Long id_sucursal = Long.parseLong(form.get("id_sucursal").trim());
	       		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			if(mapeoPermiso.get("precioMantencion")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			List<Precio> listPrecio = Precio.allSoloVigentes(con, s.baseDato, mapeoDiccionario, id_sucursal);
	    			List<Moneda> listMoneda = Moneda.all(con, s.baseDato);
	    			List<UnidadTiempo> listUnidadTiempo = UnidadTiempo.all(con, s.baseDato);
	    			Sucursal sucursal = Sucursal.find(con, s.baseDato, id_sucursal.toString());
	    			con.close();
	    			return ok(precioMantencion.render(mapeoDiccionario,mapeoPermiso,userMnu,listPrecio,listMoneda,listUnidadTiempo, sucursal));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result allContactosCliProvFabrExcel(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("bodegaContactos")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<List<String>> listContactos = ContactoCliente.allContactosCliProvFabr(con, s.baseDato);
    			
    			File file = ContactoCliente.contactoCliProvFabrsExcel(s.baseDato, listContactos, mapeoDiccionario);
    			if(file!=null) {
	       			con.close();
	       			return ok(file,false,Optional.of("ContactosClientesProveedFabricantes.xlsx"));
	       		}else {
	       			con.close();
	       			return ok("");
	       		}
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result actualizaPrecioAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
	    	DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	       		String id_sucursal = form.get("id_sucursal").trim();
	       		String id_equipo = form.get("id_equipo").trim();
	       		String id_moneda = form.get("id_moneda").trim();
	       		String campo = form.get("campo").trim();
	       		String valor = form.get("valor").replaceAll(",", "").trim();
	       		
	       		if(campo.equals("NM")) campo = "id_moneda";
				if(campo.equals("PV")) campo = "precioVenta";
				if(campo.equals("PA")) campo = "precioArriendo";
				if(campo.equals("UT")) campo = "id_unidadTiempo";
				if(campo.equals("PM")) campo = "precioMinimo";
				if(campo.equals("PE")) campo = "permanenciaMinima";
				try {
	    			Connection con = db.getConnection();
	    			String fecha = Precio.modifyPorCampo(con, s.baseDato, campo, valor, id_sucursal, id_equipo);
	    			Equipo equipo = Equipo.find(con, s.baseDato, Long.parseLong(id_equipo));
	    			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "precio", (long)0, "update", "cambia precio en campo:"+campo+" de idEq="+id_equipo+" con codigo: "+equipo.getCodigo());
	    			int numDec = Moneda.numeroDecimalxId(con, s.baseDato, id_moneda);
	    			con.close();
	    			return ok("{ \"status\": true, \"fecha\": \""+fecha+"\", \"numDec\": \""+numDec+"\"}").as("application/json");
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
				return ok("{ \"status\": false}").as("application/json");
	       	}
    	}
    	return ok("error");
    }
    
    public Result precioPlantilla(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
        	if (form.hasErrors()) {
    			return ok(mensajes.render("/",msgErrorFormulario));
    		}else {
    			Long id_sucursal = Long.parseLong(form.get("id_sucursal").trim());
    			try {
    				Connection con = db.getConnection();
    				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    				
    				File file = Precio.plantillaPrecios(con, s.baseDato, mapeoDiccionario, id_sucursal);
    				if(file != null) {
    					con.close();
        				return ok(file, Optional.of("plantillaMaestroPrecios.xlsx"));
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
    
    public Result precioCargaPlantilla(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
        	if (form.hasErrors()) {
    			return ok(mensajes.render("/",msgErrorFormulario));
    		}else {
        		Http.MultipartFormData<TemporaryFile> body = request.body().asMultipartFormData();
    			Http.MultipartFormData.FilePart<TemporaryFile> archivo = body.getFile("archivoXLSX");
    			if (archivo != null) {
    				File file = Archivos.parseMultipartFormDatatoFile(archivo);
    				Long id_sucursal = Long.parseLong(form.get("id_sucursal").trim());
					try {
		    			Connection con = db.getConnection();
	    				Long auxIdSucursal = Precio.validaSucursal(con, s.baseDato, file);
	    				if(auxIdSucursal == null) {
	    					con.close();
							return ok(mensajes.render("/home/", "Falta o no existe la sucursal en el archivo"));
	    				}else {
	    					if((long) id_sucursal != (long) auxIdSucursal) {
	    						con.close();
								return ok(mensajes.render("/home/", "La sucursal del archivo no corresponde a la sucursal seleccionada"));
	    					}
	    				}
		    				
	    				Map<String,Equipo> mapEquipos = Equipo.mapAllVigentesPorCodigo(con, s.baseDato);
	    				List<String> mensaje = Precio.validaPlantillaPrecio(con, s.baseDato, file, mapEquipos);
	    				if( ! mensaje.get(0).equals("true")) {
	    					String msg = "";
							for(String m: mensaje) {
								msg += m + " --- ";
							}
							con.close();
							return ok(mensajes.render("/home/",msg));
		    			}
		    				
		    			List<List<String>> listaExcel = Precio.llenaListaDesdeExcel(file);
		    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		    			Map<Long, Precio> mapPrecio = Precio.mapPreciosLista(con, s.baseDato, mapeoDiccionario, id_sucursal);
		    			Fechas hoy = Fechas.hoy();
    	    			for(List<String> l: listaExcel) {
    	    				String codEquipo = l.get(1);
    	    				if(codEquipo!=null) {
    	    					codEquipo = codEquipo.trim().toUpperCase();
    	    				}
							Equipo equipo = mapEquipos.get(codEquipo);
							
							if(equipo != null) {
								Precio precio = mapPrecio.get(equipo.getId());
								
								if(precio != null) {
									Double pVenta1 = Double.parseDouble(precio.getPrecioVenta().replaceAll(",", ""));
									Double pRepos1 = Double.parseDouble(precio.getPrecioReposicion().replaceAll(",", ""));
									Double pArr1 = Double.parseDouble(precio.getPrecioArriendo().replaceAll(",", ""));
									Double pMin1 = Double.parseDouble(precio.getPrecioMinimo().replaceAll(",", ""));
									Long perm1 = Long.parseLong(precio.getPermanenciaMinima().replaceAll(",", ""));
									
									Double pVenta2 = Double.parseDouble(l.get(4).replaceAll(",", ""));
									Double pRepos2 = Double.parseDouble(l.get(4).replaceAll(",", ""));
									Double pArr2 = Double.parseDouble(l.get(5).replaceAll(",", ""));
									Double pMin2 = Double.parseDouble(l.get(7).replaceAll(",", ""));
									Long perm2 = Long.parseLong(l.get(8).replaceAll(",", ""));
									
									Double rs = (pVenta1 + pRepos1 + pArr1 + pMin1 + perm1) - (pVenta2 + pRepos2 + pArr2 + pMin2 + perm2);
									
									if(rs < 0 || rs > 0) {
										Precio aux = new Precio();
										aux.setId_equipo(equipo.getId());
										aux.setId_sucursal(id_sucursal);
										aux.setPrecioVenta(l.get(4).replaceAll(",", ""));
										aux.setPrecioReposicion(l.get(4).replaceAll(",", ""));
										aux.setPrecioArriendo(l.get(5).replaceAll(",", ""));
										aux.setPrecioMinimo(l.get(7).replaceAll(",", ""));
										aux.setPermanenciaMinima(l.get(8).replaceAll(",", ""));
										Precio.updatePorSucursal(con, s.baseDato, aux, hoy.getFechaStrAAMMDD());
									}
								}
							}
    	    			}
    	    			con.close();
    	    			return ok(mensajes.render("/home/", "Actualizados los precios."));
		    	    			
					} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
    			}
	       	}
    	}
    	return ok(mensajes.render("/",msgError));
	}
    
    
    
    //============================================================
    // MNU grupoMantencion   Tablas/Grupos
    //============================================================
    
    public Result grupoMantencion(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("grupoMantencion")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<Grupo> listGrupos = Grupo.all(con, s.baseDato);
    			con.close();
    			return ok(grupoMantencion.render(mapeoDiccionario,mapeoPermiso,userMnu,listGrupos));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result grupoElimina(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	    	  	Long id_grupo = Long.parseLong(form.get("id_grupo").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			if(mapeoPermiso.get("grupoMantencion")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			if(Grupo.estaEnUso(con, s.baseDato, id_grupo)) {
	    				String msg = "No es posible eliminar este grupo, esta en uso con equipos vigentes ";
	    				con.close();
	    				return ok(mensajes.render("/grupoMantencion/",msg));
	    			}else {
	    				Grupo grupo = Grupo.find(con, s.baseDato, id_grupo);
	    				if(Grupo.delete(con, s.baseDato, id_grupo)) {
	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "grupo", id_grupo, "delete", "elimina grupo "+grupo.nombre);
	    					con.close();
	        				return redirect("/grupoMantencion/");
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
    
    public Result grupoModifica(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	    	  	Long id_grupo = Long.parseLong(form.get("id_grupo").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("grupoMantencion")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			Grupo grupo = Grupo.find(con, s.baseDato, id_grupo);
	    			List<Atributo> listAtributosGrupo = Atributo.allXGrupo(con, s.baseDato, id_grupo);
	    			con.close();
	    			return ok(grupoModifica.render(mapeoDiccionario,mapeoPermiso,userMnu,grupo,listAtributosGrupo));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result grupoAgrega(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("grupoMantencion")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			con.close();
    			return ok(grupoAgrega.render(mapeoDiccionario,mapeoPermiso,userMnu));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result grupoNuevo(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String nombreGrupo = form.get("nombreGrupo").trim();
				try {
	    			Connection con = db.getConnection();
	    			if(Grupo.existeGrupo(con, s.baseDato, nombreGrupo)) {
	    				String msg = "No es posible crear este grupo, el nombre de grupo ya existe.";
	    				con.close();
	    				return ok(mensajes.render("/grupoMantencion/",msg));
	    			}else {
	    				if(Grupo.create(con, s.baseDato, nombreGrupo)) {
	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "grupo", (long)0, "create", "agrega nuevo grupo: "+nombreGrupo);
	    					con.close();
	        				return redirect("/grupoMantencion/");
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
    
    public Result modificaGrupoPorCampoAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String campo = form.get("campo").trim();
	       		Long id_grupo = Long.parseLong(form.get("id_grupo").trim());
	       		String valor = form.get("valor").trim();
				try {
	    			Connection con = db.getConnection();
	    			if(Grupo.existeGrupo(con, s.baseDato, valor)) {
	    				con.close();
    	    			return ok("existe");
	    			}else {
	    				if(Grupo.modificaPorCampo(con, s.baseDato, campo, id_grupo, valor)) {
	    					Grupo grupo = Grupo.find(con, s.baseDato, id_grupo);
	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "grupo", id_grupo, "update", "cambia nombre del grupo a "+grupo.getNombre());
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
    
    public Result grupoNuevoAtributo(Long id_grupo, Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("grupoMantencion")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Grupo grupo = Grupo.find(con, s.baseDato, id_grupo);
    			List<Unidad> listUnidades = Unidad.all(con, s.baseDato);
    			con.close();
    			return ok(grupoNuevoAtributo.render(mapeoDiccionario,mapeoPermiso,userMnu,grupo,listUnidades));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result grupoGrabaAtributo(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_grupo = Long.parseLong(form.get("id_grupo").trim());
	       		String nombreAtributo = form.get("nombreAtributo").trim();
	       		Long id_unidad = Long.parseLong(form.get("id_unidad").trim());
	       		Long esNumerico = Long.parseLong(form.get("esNumerico").trim());
	       		Atributo atributo = new Atributo((long)0, id_grupo, nombreAtributo, id_unidad, esNumerico, "", "", "" );
				try {
	    			Connection con = db.getConnection();
	    			if(Atributo.existeAtributo(con, s.baseDato, id_grupo, nombreAtributo)) {
	    				String msg = "El nombre de atributo que intenta agregar ya existe para este grupo.";
	    				con.close();
	    				return ok(mensajes.render("/home/",msg));
	    			}else {
	    				if(Atributo.create(con, s.baseDato, atributo)) {
	    					Grupo grupo = Grupo.find(con, s.baseDato, id_grupo);
	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "atributo", (long)0, "create", "agrega nuevo atributo: "+atributo+" a grupo: "+grupo.getNombre());
	    					
	    	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    	    			List<Atributo> listAtributosGrupo = Atributo.allXGrupo(con, s.baseDato, id_grupo);
	    	    			return ok(grupoModifica.render(mapeoDiccionario,mapeoPermiso,userMnu,grupo,listAtributosGrupo));
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
    
    public Result grupoEliminaAtributo(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_grupo = Long.parseLong(form.get("id_grupo").trim());
	       		Long id_atributo = Long.parseLong(form.get("id_atributo").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			if(mapeoPermiso.get("grupoMantencion")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			if(Atributo.estaEnUso(con, s.baseDato, id_atributo)) {
	    				String msg = "No es posible eliminar este atributo, esta en uso con equipos vigentes ";
	    				con.close();
	    				return ok(mensajes.render("/home/",msg));
	    			}else {
	    				if(Atributo.delete(con, s.baseDato, id_atributo)) {
	    					Grupo grupo = Grupo.find(con, s.baseDato, id_grupo);
	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "atributo", id_atributo, "delete", "elimina atributo del grupo "+grupo.getNombre());
	    					
	    	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    	    			List<Atributo> listAtributosGrupo = Atributo.allXGrupo(con, s.baseDato, id_grupo);
	    	    			return ok(grupoModifica.render(mapeoDiccionario,mapeoPermiso,userMnu,grupo,listAtributosGrupo));
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
    
    
    //============================================================
    // MNU grupoMantencion   Tablas/Equipos
    //============================================================
    
    public Result equipoMantencion(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("equipoMantencion")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<Equipo> listEquipos = Equipo.allAll(con, s.baseDato);
    			con.close();
    			return ok(equipoMantencion.render(mapeoDiccionario,mapeoPermiso,userMnu,listEquipos));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result equipoMantencionExcel(Http.Request request) {
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
	    			if(mapeoPermiso.get("equipoMantencion")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			
	    			List<List<String>> listAtribGroup = Atributo.listAtributosGroup(con, s.baseDato);
	    			List<Equipo> listEquipos = Equipo.allAll(con, s.baseDato); 
	    			Map<Long,Double> mapStock = Inventarios.mapEquiposConStock(con, s.baseDato, "ARRIENDO", mapeoDiccionario);
	    			Map<Long,List<String>> mapUbicacion = ReportInventarios.mapIdEqVsEnCantBodegas(con, s.baseDato);
	    			Map<Long,List<String>> mapPCompra = Compra.ultimoPrecioMasFactura(con, s.baseDato); 
	    			Map<String,List<String>> mapAtributos = Atributo.mapAtributosAllEquipos(con, s.baseDato);
	    			
	    			File file = Equipo.allExcel(s.baseDato, mapeoDiccionario, listEquipos, mapStock, mapUbicacion, mapPCompra, listAtribGroup, mapAtributos);
	    			
	    			if(file!=null) {
		       			con.close();
		       			return ok(file,false,Optional.of("Listado_Equipos.xlsx"));
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
    
    public Result equipoCambiaEstado(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
			DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("{ \"status\": false}").as("application/json");
	       	}else {
				try{
					Connection con = db.getConnection();
					Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
					String estado = form.get("estado").trim();
					PreparedStatement smt20 = con
							.prepareStatement(" select "
									+ " bodegaEmpresa.esInterna, "
									+ " movimiento.id_equipo, "
									+ " if(sum(movimiento.cantidad*if(movimiento.id_tipoMovimiento=1,1,-1))=-0,0,sum(movimiento.cantidad*if(movimiento.id_tipoMovimiento=1,1,-1))) "
									+ " from `"+s.baseDato+"`.movimiento "
									+ " left join `"+s.baseDato+"`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa "
									+ " where bodegaEmpresa.id > 0 and movimiento.id_equipo = ? and bodegaEmpresa.vigente = 1 "
									+ " group by id_equipo "
									+ " having if(sum(movimiento.cantidad*if(movimiento.id_tipoMovimiento=1,1,-1))=-0,0,sum(movimiento.cantidad*if(movimiento.id_tipoMovimiento=1,1,-1)))>0;");
					smt20.setLong(1, id_equipo);
					
					ResultSet rs20 = smt20.executeQuery();
					if (rs20.next()) {
						rs20.close();
						smt20.close();
						con.close();
						return ok("{ \"status\": false, \"conInventario\": true}").as("application/json");
					}else {
						rs20.close();
						smt20.close();
						if(Equipo.modificaPorCampo(con, s.baseDato, "vigente", id_equipo, estado)) {
							con.close();
							return ok("{ \"status\": true}").as("application/json");
						}else {
							con.close();
							return ok("{ \"status\": false}").as("application/json");
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return ok("{ \"status\": false}").as("application/json");
	    	}
	    }
    	return ok("{ \"status\": false}").as("application/json");
    }
    
    public Result equipoModifica(Long id_equipo, Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("equipoMantencion")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Equipo equipo = Equipo.find(con, s.baseDato, id_equipo);
    			List<Grupo> listGrupos = Grupo.all(con, s.baseDato); 
    			List<Atributo> listAtributos = Atributo.allXEquipoConValor(con, s.baseDato, equipo);
    			List<Fabrica> listFabrica = Fabrica.all(con, s.baseDato);
    			List<Unidad> listUnidades = Unidad.all(con, s.baseDato);
    			con.close();
    			return ok(equipoModifica.render(mapeoDiccionario,mapeoPermiso,userMnu,equipo,listGrupos,listAtributos,listFabrica,listUnidades));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result equipoNuevo(Long id_grupo, Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("equipoMantencion")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<Grupo> listGrupos = Grupo.all(con, s.baseDato);
    			List<Fabrica> listFabrica = Fabrica.all(con, s.baseDato);
    			List<Unidad> listUnidades = Unidad.all(con, s.baseDato);
    			List<Atributo> listAtributos = Atributo.allXGrupo(con, s.baseDato, id_grupo);
    			Grupo grupo = Grupo.find(con, s.baseDato, id_grupo);
    			con.close();
    			return ok(equipoNuevo.render(mapeoDiccionario,mapeoPermiso,userMnu,listGrupos,listFabrica,listUnidades,listAtributos,grupo));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result cambiarGrupoEquipo(Long id_grupo, Long id_equipo, Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("equipoMantencion")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Equipo.cambiarGrupo(con, s.baseDato, id_grupo, id_equipo);
    			Equipo equipo = Equipo.find(con, s.baseDato, id_equipo);
    			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "equipo", id_equipo, "update", "cambia de grupo al equipo codigo "+equipo.getCodigo());
    			List<Grupo> listGrupos = Grupo.all(con, s.baseDato); 
    			List<Atributo> listAtributos = Atributo.allXEquipoConValor(con, s.baseDato, equipo);
    			List<Fabrica> listFabrica = Fabrica.all(con, s.baseDato);
    			List<Unidad> listUnidades = Unidad.all(con, s.baseDato);
    			con.close();
    			return ok(equipoModifica.render(mapeoDiccionario,mapeoPermiso,userMnu,equipo,listGrupos,listAtributos,listFabrica,listUnidades));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result modificaEquipoPorCampoAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	String campo = form.get("campo").trim();
	       		Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
	       		String valor = form.get("valor").trim();
				try {
	    			Connection con = db.getConnection();
	    			if(campo.equals("codigo")) {
	    				if(Equipo.existeCodigo(con, s.baseDato, valor)) {
	    	    			con.close();
	    	    			return ok("existe");
	    				}
	    			}
	    			if(!Equipo.modificaPorCampo(con, s.baseDato, campo, id_equipo, valor)){
	    				con.close();
	    				return ok("error");
	    			}else {
	    				Equipo equipo = Equipo.find(con, s.baseDato, id_equipo);
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "equipo", id_equipo, "update", "cambia el valor de: "+campo+" de equipo codigo "+equipo.getCodigo());
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
    
    public Result modificaAtributoEquipoAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	       		Long id_atributo = Long.parseLong(form.get("id_atributo").trim());;
	       		Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
	       		String valor = form.get("valor").trim();
	       		Long esNumerico = Long.parseLong(form.get("esNumerico").trim());
				try {
	    			Connection con = db.getConnection();
    				if(Atributo.modifyAtributoEquipo(con, s.baseDato, id_atributo, id_equipo, valor, esNumerico)) {
    					Equipo equipo = Equipo.find(con, s.baseDato, id_equipo);
    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "atributo", id_atributo, "update", "cambia el valor en equipo id: "+id_equipo+" con codigo: "+equipo.getCodigo()+", del atributo id: "+id_atributo);
    	    			con.close();
    	    			return ok("");
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
    
    public Result grabaImgEquipo(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
	       		Http.MultipartFormData<TemporaryFile> body = request.body().asMultipartFormData();
				Http.MultipartFormData.FilePart<TemporaryFile> archivo = body.getFile("img");
				if (archivo != null) {
					String nombreArchivoSinExtencion = "Img-Id" + id_equipo.toString();
					String path = Archivos.grabarArchivos(archivo, s.baseDato, nombreArchivoSinExtencion);
					try {
		    			Connection con = db.getConnection();
		    			if(!Equipo.modificaPorCampo(con, s.baseDato, "img", id_equipo, path)){
		    				con.close();
		    				return ok(mensajes.render("/",msgError));
		    			}else {
		    				Equipo equipo = Equipo.find(con, s.baseDato, id_equipo);
		    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "equipo", id_equipo, "update", "cambia o agrega imagen del equipo codigo "+equipo.getCodigo());
		    				con.close();
		    				return redirect("/equipoModifica/"+id_equipo);
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
    
    public Result equipoElimina(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			if(!Equipo.estaEnUso(con, s.baseDato, id_equipo)) {
	    				Equipo equipo = Equipo.find(con, s.baseDato, id_equipo);
	    				if(Equipo.delete(con, s.baseDato, id_equipo)) {
	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "equipo", id_equipo, "delete", "elimina el equipo codigo "+equipo.getCodigo());
	    					con.close();
	    					return redirect("/equipoMantencion/");
	    				}else {
	    					con.close();
		    				return ok(mensajes.render("/",msgError));
	    				}
	    			}else {
	    				String msg = "No es posible eliminar este equipo, esta en uso en el sistema";
	    				con.close();
	    				return ok(mensajes.render("/equipoMantencion/",msg));
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
    
    
    public Result equipoGraba(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		FormEquipoGraba form = formFactory.form(FormEquipoGraba.class).withDirectFieldAccess(true).bindFromRequest(request).get();
    		if (form.codigo==null) {
    			return ok(mensajes.render("/",msgErrorFormulario));
    		}else {
    			try {
	    			Connection con = db.getConnection();
	    			form.desdeMenu = "TABLAS";
	    			if(Equipo.create(con, s.baseDato, form)) {
	    				Equipo equipo = Equipo.findXCodigo(con, s.baseDato, form.codigo);
	    				if(form.idAtributos!=null){
							for(int i=0;i<form.idAtributos.size();i++){
								String valor = form.valorAtributos.get(i).trim();
								if(valor.length()>0){
									Long id_atributo = form.idAtributos.get(i);
									Atributo.grabaAtributoEquipo(con, s.baseDato, equipo.getId(), id_atributo, valor);
								}
							}
						}
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "equipo", (long)0, "create", "crea desde TABLAS nuevo equipo codigo: "+form.codigo);
	    				con.close();
	    				return redirect("/equipoMantencion/");
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
    
    public Result verificaCodigoEquipoAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	String codigo = form.get("codigo").trim();
				try {
	    			Connection con = db.getConnection();
	    				if(Equipo.existeCodigo(con, s.baseDato, codigo)) {
	    	    			con.close();
	    	    			return ok("existe");
	    				}else {
	    					con.close();
	    					return ok("");
	    				}
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    	return ok("error");
    }
    
    public Result equipoDescripcionAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
				try {
	    			Connection con = db.getConnection();
	    			String vista = Atributo.detalleEquipo(con, s.baseDato, id_equipo);
	    			con.close();
	    			return ok(vista);
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    	return ok("error");
    }
    
    public Result equipoFindIdForCodAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	String cod_equipo = form.get("cod_equipo").trim();
				try {
	    			Connection con = db.getConnection();
	    			Equipo equipo= Equipo.findXCodigo(con, s.baseDato, cod_equipo);
	    			if(equipo != null && (long) equipo.id != (long) 0) {
	    				con.close();
		    			return ok(equipo.id.toString());
	    			}
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    	return ok("error");
    }
    
    
    //============================================================
    // MNU proyectoMantencion   Tablas/Proyectos
    //============================================================
    
    public Result proyectoMantencion(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("proyectoMantencion")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<Proyecto> listProyectos = Proyecto.all(con, s.baseDato);
    			con.close();
    			return ok(proyectoMantencion.render(mapeoDiccionario,mapeoPermiso,userMnu,listProyectos));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result proyectoMantencionExcel(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			List<Proyecto> listProyectos = Proyecto.all(con, s.baseDato);
	    			File file = Proyecto.exportaAllProyectosExcel(s.baseDato, mapeoDiccionario,listProyectos);
	    			if(file!=null) {
		       			con.close();
		       			return ok(file,false,Optional.of("Listado_Proyectos.xlsx"));
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
    
    public Result proyectoModifica(Long id_proyecto, Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("proyectoMantencion")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Proyecto proyecto = Proyecto.find(con, s.baseDato, id_proyecto);
    			List<Comunas> listComunas = Comunas.allPorRegion(con, s.baseDato, proyecto.getCod_region());
    			List<Regiones> listRegiones = Regiones.all(con, s.baseDato);
    			List<ContactoProyecto> listContactos = ContactoProyecto.allxProyecto(con, s.baseDato, id_proyecto);
    			con.close();
    			return ok(proyectoModifica.render(mapeoDiccionario,mapeoPermiso,userMnu,proyecto,listComunas,listRegiones,listContactos));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result selComuna1Ajax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	String cod_region = form.get("cod_region").trim();
				try {
	    			Connection con = db.getConnection();
	    			String vista = Comunas.selectPorRegion1(con, s.baseDato, cod_region);
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
    
    public Result selComuna2Ajax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	String cod_region = form.get("cod_region").trim();
	    	  	Long id_proyecto = Long.parseLong(form.get("id_proyecto").trim());
				try {
	    			Connection con = db.getConnection();
	    			Proyecto.modificaPorCampo(con, s.baseDato, "cod_comuna", id_proyecto, "0");
	    			String vista = Comunas.selectPorRegion2(con, s.baseDato, cod_region);
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
    
    public Result modificaProyectoPorCampoAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	String campo = form.get("campo").trim();
	       		Long id_proyecto = Long.parseLong(form.get("id_proyecto").trim());
	       		String valor = form.get("valor").trim();
				try {
	    			Connection con = db.getConnection();
	    			if(campo.equals("nickName")) {
	    				if(Proyecto.existeNickName(con, s.baseDato, valor)) {
	    	    			con.close();
	    	    			return ok("existe");
	    				}
	    			}
	    			if(!Proyecto.modificaPorCampo(con, s.baseDato, campo, id_proyecto, valor)){
	    				con.close();
	    				return ok("error");
	    			}else {
	    				Proyecto proyecto = Proyecto.find(con, s.baseDato, id_proyecto);
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "proyecto", id_proyecto, "update", "cambia el valor de: "+campo+" en proyecto: "+proyecto.getNickName());
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
    
    public Result proyectoContactoModifica(Long id_contacto, Long id_proyecto, Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("proyectoMantencion")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			ContactoProyecto contacto = ContactoProyecto.find(con, s.baseDato, id_contacto);
    			Proyecto proyecto = Proyecto.find(con, s.baseDato, id_proyecto);
    			con.close();
    			return ok(proyectoContactoModifica.render(mapeoDiccionario,mapeoPermiso,userMnu,contacto,proyecto));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result proyectoContactoAgrega(Long id_proyecto, Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("proyectoMantencion")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Proyecto proyecto = Proyecto.find(con, s.baseDato, id_proyecto);
    			con.close();
    			return ok(proyectoContactoAgrega.render(mapeoDiccionario,mapeoPermiso,userMnu,proyecto));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result proyectoContactoGraba(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		FormContactoProyectoGraba form = formFactory.form(FormContactoProyectoGraba.class).withDirectFieldAccess(true).bindFromRequest(request).get();
    		if (form.id_proyecto==null) {
    			return ok(mensajes.render("/",msgErrorFormulario));
    		}else {
    			try {
	    			Connection con = db.getConnection();
	    			if(ContactoProyecto.create(con, s.baseDato, form)) {
	    				Proyecto proyecto = Proyecto.find(con, s.baseDato, form.id_proyecto);
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "contactoProyecto", (long)0, "create", "crea nuevo contacto proyecto: "+proyecto.getNickName());
	    				con.close();
	    				return redirect("/proyectoModifica/"+form.id_proyecto);
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
    
    public Result proyectoContactoElimina(Long id_contacto, Long id_proyecto, Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			if(mapeoPermiso.get("proyectoMantencion")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			ContactoProyecto.delete(con, s.baseDato, id_contacto);
    			Proyecto proyecto = Proyecto.find(con, s.baseDato, id_proyecto);
    			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "contactoProyecto", id_contacto, "delete", "elimina contacto de proyecto: "+proyecto.getNickName());
    			con.close();
    			return redirect("/proyectoModifica/"+id_proyecto);
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result modificaContactoProyectoPorCampoAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	String campo = form.get("campo").trim();
	       		Long id_contacto = Long.parseLong(form.get("id_contacto").trim());
	       		String valor = form.get("valor").trim();
				try {
	    			Connection con = db.getConnection();
	    			ContactoProyecto.modificaPorCampo(con, s.baseDato, campo, id_contacto, valor);
	    			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "contactoProyecto", id_contacto, "update", "cambia contacto en campo: "+campo);
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
    
    public Result proyectoElimina(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_proyecto = Long.parseLong(form.get("id_proyecto").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			if(mapeoPermiso.get("proyectoMantencion")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			if(Proyecto.estaEnUso(con, s.baseDato, id_proyecto)) {
	    				String msg = "No es posible eliminar este proyecto, esta en uso con bodegas/cliente";
	    				con.close();
	    				return ok(mensajes.render("/proyectoMantencion/",msg));
	    			}else {
	    				Proyecto proyecto = Proyecto.find(con, s.baseDato, id_proyecto);
	    				if(Proyecto.delete(con, s.baseDato, id_proyecto)) {
	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "proyecto", id_proyecto, "delete", "elimina proyecto "+proyecto.getNickName());
	    					con.close();
	        				return redirect("/proyectoMantencion/");
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
    
    public Result proyectoAgrega(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("proyectoMantencion")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<Regiones> listRegiones = Regiones.all(con, s.baseDato);
    			con.close();
    			return ok(proyectoAgrega.render(mapeoDiccionario,mapeoPermiso,userMnu,listRegiones));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result proyectoGraba(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		FormProyectoGraba form = formFactory.form(FormProyectoGraba.class).withDirectFieldAccess(true).bindFromRequest(request).get();
    		if (form.nickName==null) {
    			return ok(mensajes.render("/",msgErrorFormulario));
    		}else {
    			try {
	    			Connection con = db.getConnection();
	    			if(Proyecto.create(con, s.baseDato, form)) {
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "proyecto", (long)0, "create", "crea nuevo proyecto "+form.nickName);
	    				con.close();
	    				return redirect("/proyectoMantencion/");
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
    
    public Result proyectoGrabaAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		FormProyectoGraba form = formFactory.form(FormProyectoGraba.class).withDirectFieldAccess(true).bindFromRequest(request).get();
    		if (form.nickName==null) {
	   			return ok("error");
	       	}else {
				try {
	    			Connection con = db.getConnection();
	    			Proyecto proyecto = Proyecto.findPorNickName(con, s.baseDato, form.nickName);
	    			if(proyecto == null || (long) proyecto.id == (long)0) {
	    				if(Proyecto.create(con, s.baseDato, form)) {
	    					proyecto = Proyecto.findPorNickName(con, s.baseDato, form.nickName);
		    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "proyecto", (long)0, "create", "crea nuevo proyecto "+form.nickName);
		    				con.close();
		    				return ok(proyecto.getId().toString());
		    			}
	    			}else {
	    				return ok("existe");
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
    
    public Result verificaNickProyectoAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	String nickName = form.get("nickName").trim();
				try {
	    			Connection con = db.getConnection();
	    				if(Proyecto.existeNickName(con, s.baseDato, nickName)) {
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
    
    public Result proyectoContactosAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	       		Long id_proyecto = Long.parseLong(form.get("id_proyecto").trim());
				try {
	    			Connection con = db.getConnection();
	    			String vista = ContactoProyecto.contactos(con, s.baseDato, id_proyecto);
	    			con.close();
	    			return ok(vista);
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    	return ok("error");
    }
    
    public Result proyectoFindIdForNickNameAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	String nickName = form.get("nickName").trim();
				try {
	    			Connection con = db.getConnection();
	    			Proyecto proyecto = Proyecto.findPorNickName(con, s.baseDato, nickName);
	    			if(proyecto != null && (long) proyecto.id != (long) 0) {
	    				con.close();
		    			return ok(proyecto.id.toString());
	    			}
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    	return ok("error");
    }
    
    
    
    //============================================================
    // MNU clienteMantencion   Tablas/Clientes
    //============================================================
    
    public Result clienteMantencion(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("clienteMantencion")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			
    			con.close();
    			return ok(clienteMantencion.render(mapeoDiccionario,mapeoPermiso,userMnu));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result clienteMantencion2(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	       		try {
	    			Connection con = db.getConnection();
	    			List<Cliente> listClientes = Cliente.all(con, s.baseDato);
	    			String jsonClientes = Json.toJson(listClientes).toString();
	    			con.close();
	    			return ok(jsonClientes).as("application/json");
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok("error");
	       	}
    	}
    	return ok("error");
    }
    
    
    public Result clienteCambiaEstado(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
			DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("{ \"status\": false}").as("application/json");
	       	}else {
				try{
					Connection con = db.getConnection();
					Long id_cliente = Long.parseLong(form.get("id_cliente").trim());
					String estado = form.get("estado").trim();
					if(Cliente.modificaPorCampo(con, s.baseDato, "vigente", id_cliente, estado)) {
						con.close();
						return ok("{ \"status\": true}").as("application/json");
					}else {
						con.close();
						return ok("{ \"status\": false}").as("application/json");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return ok("{ \"status\": false}").as("application/json");
	    	}
	    }
    	return ok("{ \"status\": false}").as("application/json");
    }
    
    public Result clienteMantencionExcel(Http.Request request) {
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
	    			if(mapeoPermiso.get("clienteMantencion")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			List<Cliente> listClientes = Cliente.all(con, s.baseDato);
	    			
	    			File file = Cliente.allExcel(s.baseDato, mapeoDiccionario, listClientes);
	    			
	    			if(file!=null) {
		       			con.close();
		       			return ok(file,false,Optional.of("Listado_Clientes.xlsx"));
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
    
    
    public Result clienteModifica(Long id_cliente, Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("clienteMantencion")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Cliente cliente = Cliente.find(con, s.baseDato, id_cliente);
    			List<Comunas> listComunas = Comunas.allPorRegion(con, s.baseDato, cliente.getCod_region());
    			List<Regiones> listRegiones = Regiones.all(con, s.baseDato);
    			List<ContactoCliente> listContactos = ContactoCliente.allxCliente(con, s.baseDato, id_cliente);
    			con.close();
    			return ok(clienteModifica.render(mapeoDiccionario,mapeoPermiso,userMnu,cliente,listComunas,listRegiones,listContactos));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result modificaClientePorCampoAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	String campo = form.get("campo").trim();
	       		Long id_cliente = Long.parseLong(form.get("id_cliente").trim());
	       		String valor = form.get("valor").trim();
				try {
	    			Connection con = db.getConnection();
	    			if(campo.equals("nickName")) {
	    				if(Cliente.existeNickName(con, s.baseDato, valor)) {
	    	    			con.close();
	    	    			return ok("existe");
	    				}
	    			}
	    			if(!Cliente.modificaPorCampo(con, s.baseDato, campo, id_cliente, valor)){
	    				con.close();
	    				return ok("error");
	    			}else {
	    				Cliente cliente = Cliente.find(con, s.baseDato, id_cliente);
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "cliente", id_cliente, "update", "cambia el valor de: "+campo+" en el cliente "+cliente.getNickName());
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
    
    public Result selComuna3Ajax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	String cod_region = form.get("cod_region").trim();
	    	  	Long id_cliente = Long.parseLong(form.get("id_cliente").trim());
				try {
	    			Connection con = db.getConnection();
	    			Cliente.modificaPorCampo(con, s.baseDato, "cod_comuna", id_cliente, "0");
	    			String vista = Comunas.selectPorRegion3(con, s.baseDato, cod_region);
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
    
    public Result clienteContactoModifica(Long id_contacto, Long id_cliente, Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("clienteMantencion")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			ContactoCliente contacto = ContactoCliente.find(con, s.baseDato, id_contacto);
    			Cliente cliente = Cliente.find(con, s.baseDato, id_cliente);
    			con.close();
    			return ok(clienteContactoModifica.render(mapeoDiccionario,mapeoPermiso,userMnu,contacto,cliente));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result clienteContactoAgrega(Long id_cliente, Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("clienteMantencion")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Cliente cliente = Cliente.find(con, s.baseDato, id_cliente);
    			con.close();
    			return ok(clienteContactoAgrega.render(mapeoDiccionario,mapeoPermiso,userMnu,cliente));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result clienteContactoGraba(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		FormContactoClienteGraba form = formFactory.form(FormContactoClienteGraba.class).withDirectFieldAccess(true).bindFromRequest(request).get();
    		if (form.id_cliente==null) {
    			return ok(mensajes.render("/",msgErrorFormulario));
    		}else {
    			try {
	    			Connection con = db.getConnection();
	    			if(ContactoCliente.create(con, s.baseDato, form)) {
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "contactoCliente", (long)0, "create", "crea nuevo contacto id_cliente: "+form.id_cliente);
	    				con.close();
	    				return redirect("/clienteModifica/"+form.id_cliente);
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
    
    public Result clienteContactoElimina(Long id_contacto, Long id_cliente, Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			if(mapeoPermiso.get("clienteMantencion")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			ContactoCliente.delete(con, s.baseDato, id_contacto);
    			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "contactoCliente", id_contacto, "delete", "elimina contacto de id_cliente: "+id_cliente);
    			con.close();
    			return redirect("/clienteModifica/"+id_cliente);
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result modificaContactoClientePorCampoAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	String campo = form.get("campo").trim();
	       		Long id_contacto = Long.parseLong(form.get("id_contacto").trim());
	       		String valor = form.get("valor").trim();
				try {
	    			Connection con = db.getConnection();
	    			ContactoCliente.modificaPorCampo(con, s.baseDato, campo, id_contacto, valor);
	    			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "contactoCliente", id_contacto, "update", "cambia contacto en campo: "+campo);
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
    
    public Result clienteElimina(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
    		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_cliente = Long.parseLong(form.get("id_cliente").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			if(mapeoPermiso.get("clienteMantencion")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			if(Cliente.estaEnUso(con, s.baseDato, id_cliente)) {
	    				String msg = "No es posible eliminar este cliente, esta en uso con bodegas/cliente";
	    				con.close();
	    				return ok(mensajes.render("/clienteMantencion/",msg));
	    			}else {
	    				Cliente cliente = Cliente.find(con, s.baseDato, id_cliente);
	    				if(Cliente.delete(con, s.baseDato, id_cliente)) {
	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "cliente", id_cliente, "delete", "elimina cliente "+cliente.getNickName());
	    					con.close();
	        				return redirect("/clienteMantencion/");
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
    
    public Result clienteAgrega(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("clienteMantencion")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<Regiones> listRegiones = Regiones.all(con, s.baseDato);
    			con.close();
    			return ok(clienteAgrega.render(mapeoDiccionario,mapeoPermiso,userMnu,listRegiones));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result clienteGraba(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		FormClienteGraba form = formFactory.form(FormClienteGraba.class).withDirectFieldAccess(true).bindFromRequest(request).get();
    		if (form.nickName==null) {
    			return ok(mensajes.render("/",msgErrorFormulario));
    		}else {
    			try {
	    			Connection con = db.getConnection();
	    			if(Cliente.create(con, s.baseDato, form)) {
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "cliente", (long)0, "create", "crea nuevo cliente "+form.nickName);
	    				con.close();
	    				return redirect("/clienteMantencion/");
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
    
    public Result clienteGrabaAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		FormClienteGraba form = formFactory.form(FormClienteGraba.class).withDirectFieldAccess(true).bindFromRequest(request).get();
    		if (form.nickName==null) {
	   			return ok("error");
	       	}else {
				try {
	    			Connection con = db.getConnection();
	    			Cliente cliente = Cliente.findPorNickName(con, s.baseDato, form.nickName);
	    			if(cliente == null || (long) cliente.id == (long)0) {
	    				if(Cliente.create(con, s.baseDato, form)) {
		    				cliente = Cliente.findPorNickName(con, s.baseDato, form.nickName);
		    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "cliente", (long)0, "create", "crea nuevo cliente "+form.nickName);
		    				con.close();
		    				return ok(cliente.getId().toString());
		    			}
	    			}else {
	    				return ok("existe");
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
    
    public Result verificaNickClienteAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	String nickName = form.get("nickName").trim();
				try {
	    			Connection con = db.getConnection();
	    				if(Cliente.existeNickName(con, s.baseDato, nickName)) {
	    	    			con.close();
	    	    			return ok("existe");
	    				}else {
	    					con.close();
	    					return ok("");
	    				}
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    	return ok("error");
    }
    
    public Result clienteContactosAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	       		Long id_cliente = Long.parseLong(form.get("id_cliente").trim());
				try {
	    			Connection con = db.getConnection();
	    			String vista = ContactoCliente.contactos(con, s.baseDato, id_cliente);
	    			con.close();
	    			return ok(vista);
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    	return ok("error");
    }
    
    public Result clienteFindIdForNickNameAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	String nickName = form.get("nickName").trim();
				try {
	    			Connection con = db.getConnection();
	    			Cliente cliente = Cliente.findPorNickName(con, s.baseDato, nickName);
	    			if(cliente != null && (long) cliente.id != (long) 0) {
	    				con.close();
		    			return ok(cliente.id.toString());
	    			}
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    	return ok("error");
    }
    
    //============================================================
    // MNU fabricaMantencion   Tablas/Fabricantes
    //============================================================
    
    public Result fabricaMantencion(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("fabricaMantencion")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<Fabrica> listFabricantes = Fabrica.all(con, s.baseDato);
    			con.close();
    			return ok(fabricaMantencion.render(mapeoDiccionario,mapeoPermiso,userMnu,listFabricantes));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result fabricaModifica(Long id_fabrica, Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("fabricaMantencion")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Fabrica fabrica = Fabrica.find(con, s.baseDato, id_fabrica);
    			List<Comunas> listComunas = Comunas.allPorRegion(con, s.baseDato, fabrica.getCod_region());
    			List<Regiones> listRegiones = Regiones.all(con, s.baseDato);
    			List<ContactoFabrica> listContactos = ContactoFabrica.allxFabrica(con, s.baseDato, id_fabrica);
    			con.close();
    			return ok(fabricaModifica.render(mapeoDiccionario,mapeoPermiso,userMnu,fabrica,listComunas,listRegiones,listContactos));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result modificaFabricaPorCampoAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	String campo = form.get("campo").trim();
	       		Long id_fabrica = Long.parseLong(form.get("id_fabrica").trim());
	       		String valor = form.get("valor").trim();
				try {
	    			Connection con = db.getConnection();
	    			if(campo.equals("nickName")) {
	    				if(Fabrica.existeNickName(con, s.baseDato, valor)) {
	    	    			con.close();
	    	    			return ok("existe");
	    				}
	    			}
	    			if(!Fabrica.modificaPorCampo(con, s.baseDato, campo, id_fabrica, valor)){
	    				con.close();
	    				return ok("error");
	    			}else {
	    				Fabrica fabrica = Fabrica.find(con, s.baseDato, id_fabrica);
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "fabrica", id_fabrica, "update", "cambia el valor de: "+campo+" en fabricante: "+fabrica.getNickName());
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
    
    public Result selComuna4Ajax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	String cod_region = form.get("cod_region").trim();
	    	  	Long id_fabrica = Long.parseLong(form.get("id_fabrica").trim());
				try {
	    			Connection con = db.getConnection();
	    			Fabrica.modificaPorCampo(con, s.baseDato, "cod_comuna", id_fabrica, "0");
	    			String vista = Comunas.selectPorRegion4(con, s.baseDato, cod_region);
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
    
    public Result fabricaContactoModifica(Long id_contacto, Long id_fabrica, Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("fabricaMantencion")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			ContactoFabrica contacto = ContactoFabrica.find(con, s.baseDato, id_contacto);
    			Fabrica fabrica = Fabrica.find(con, s.baseDato, id_fabrica);
    			con.close();
    			return ok(fabricaContactoModifica.render(mapeoDiccionario,mapeoPermiso,userMnu,contacto,fabrica));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result fabricaContactoAgrega(Long id_fabrica, Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("fabricaMantencion")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Fabrica fabrica = Fabrica.find(con, s.baseDato, id_fabrica);
    			con.close();
    			return ok(fabricaContactoAgrega.render(mapeoDiccionario,mapeoPermiso,userMnu,fabrica));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result fabricaContactoGraba(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		FormContactoFabricaGraba form = formFactory.form(FormContactoFabricaGraba.class).withDirectFieldAccess(true).bindFromRequest(request).get();
    		if (form.id_fabrica==null) {
    			return ok(mensajes.render("/",msgErrorFormulario));
    		}else {
    			try {
	    			Connection con = db.getConnection();
	    			if(ContactoFabrica.create(con, s.baseDato, form)) {
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "contactoFabrica", (long)0, "create", "crea nuevo contacto id_fabrica: "+form.id_fabrica);
	    				con.close();
	    				return redirect("/fabricaModifica/"+form.id_fabrica);
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
    
    public Result fabricaContactoElimina(Long id_contacto, Long id_fabrica, Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			if(mapeoPermiso.get("fabricaMantencion")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			ContactoFabrica.delete(con, s.baseDato, id_contacto);
    			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "contactoFabrica", id_contacto, "delete", "elimina contacto de id_fabrica: "+id_fabrica);
    			con.close();
    			return redirect("/fabricaModifica/"+id_fabrica);
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result modificaContactoFabricaPorCampoAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	String campo = form.get("campo").trim();
	       		Long id_contacto = Long.parseLong(form.get("id_contacto").trim());
	       		String valor = form.get("valor").trim();
				try {
	    			Connection con = db.getConnection();
	    			ContactoFabrica.modificaPorCampo(con, s.baseDato, campo, id_contacto, valor);
	    			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "contactoFabrica", id_contacto, "update", "cambia contacto en campo: "+campo);
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
    
    public Result fabricaElimina(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
    		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_fabrica = Long.parseLong(form.get("id_fabrica").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			if(mapeoPermiso.get("fabricaMantencion")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			if(Fabrica.estaEnUso(con, s.baseDato, id_fabrica)) {
	    				String msg = "No es posible eliminar este fabricante, esta en uso con equipos";
	    				con.close();
	    				return ok(mensajes.render("/fabricaMantencion/",msg));
	    			}else {
	    				Fabrica fabrica = Fabrica.find(con, s.baseDato, id_fabrica);
	    				if(Fabrica.delete(con, s.baseDato, id_fabrica)) {
	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "fabrica", id_fabrica, "delete", "elimina fabricante "+fabrica.getNickName());
	    					con.close();
	        				return redirect("/fabricaMantencion/");
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
    
    public Result fabricaAgrega(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("fabricaMantencion")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<Regiones> listRegiones = Regiones.all(con, s.baseDato);
    			con.close();
    			return ok(fabricaAgrega.render(mapeoDiccionario,mapeoPermiso,userMnu,listRegiones));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result fabricaGraba(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		FormFabricaGraba form = formFactory.form(FormFabricaGraba.class).withDirectFieldAccess(true).bindFromRequest(request).get();
    		if (form.nickName==null) {
    			return ok(mensajes.render("/",msgErrorFormulario));
    		}else {
    			try {
	    			Connection con = db.getConnection();
	    			if(Fabrica.create(con, s.baseDato, form)) {
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "fabrica", (long)0, "create", "crea nuevo fabricante "+form.nickName);
	    				con.close();
	    				return redirect("/fabricaMantencion/");
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
    
    public Result verificaNickFabricaAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	String nickName = form.get("nickName").trim();
				try {
	    			Connection con = db.getConnection();
	    				if(Fabrica.existeNickName(con, s.baseDato, nickName)) {
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
    
    //============================================================
    // MNU TransportistaMantencion   Tablas/Transportistas
    //============================================================
    
    public Result transportistaMantencion(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("transportistaMantencion")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<Transportista> listaTransporte = Transportista.listaTransportista(con, s.baseDato);
    			con.close();
    			return ok(transportistaMantencion.render(mapeoDiccionario,mapeoPermiso,userMnu,listaTransporte));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    
    //============================================================
    // MNU proveedorMantencion   Tablas/Proveedores
    //============================================================
    
    public Result proveedorMantencion(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("proveedorMantencion")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<Proveedor> listProveedor = Proveedor.all(con, s.baseDato);
    			con.close();
    			return ok(proveedorMantencion.render(mapeoDiccionario,mapeoPermiso,userMnu,listProveedor));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result proveedorModifica(Long id_proveedor, Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("proveedorMantencion")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Proveedor proveedor = Proveedor.find(con, s.baseDato, id_proveedor);
    			List<Comunas> listComunas = Comunas.allPorRegion(con, s.baseDato, proveedor.getCod_region());
    			List<Regiones> listRegiones = Regiones.all(con, s.baseDato);
    			List<ContactoProveedor> listContactos = ContactoProveedor.allxProveedor(con, s.baseDato, id_proveedor);
    			con.close();
    			return ok(proveedorModifica.render(mapeoDiccionario,mapeoPermiso,userMnu,proveedor,listComunas,listRegiones,listContactos));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result modificaProveedorPorCampoAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	String campo = form.get("campo").trim();
	       		Long id_proveedor = Long.parseLong(form.get("id_proveedor").trim());
	       		String valor = form.get("valor").trim();
				try {
	    			Connection con = db.getConnection();
	    			if(campo.equals("nickName")) {
	    				if(Proveedor.existeNickName(con, s.baseDato, valor)) {
	    	    			con.close();
	    	    			return ok("existe");
	    				}
	    			}
	    			if(!Proveedor.modificaPorCampo(con, s.baseDato, campo, id_proveedor, valor)){
	    				con.close();
	    				return ok("error");
	    			}else {
	    				Proveedor proveedor = Proveedor.find(con, s.baseDato, id_proveedor);
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "proveedor", id_proveedor, "update", "cambia el valor de: "+campo+" en proveedor: "+proveedor.getNickName());
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
    
    public Result selComuna5Ajax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	String cod_region = form.get("cod_region").trim();
	    	  	Long id_proveedor = Long.parseLong(form.get("id_proveedor").trim());
				try {
	    			Connection con = db.getConnection();
	    			Proveedor.modificaPorCampo(con, s.baseDato, "cod_comuna", id_proveedor, "0");
	    			String vista = Comunas.selectPorRegion5(con, s.baseDato, cod_region);
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
    
    public Result proveedorContactoModifica(Long id_contacto, Long id_proveedor, Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("proveedorMantencion")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			ContactoProveedor contacto = ContactoProveedor.find(con, s.baseDato, id_contacto);
    			Proveedor proveedor = Proveedor.find(con, s.baseDato, id_proveedor);
    			con.close();
    			return ok(proveedorContactoModifica.render(mapeoDiccionario,mapeoPermiso,userMnu,contacto,proveedor));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result proveedorContactoAgrega(Long id_proveedor, Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("proveedorMantencion")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Proveedor proveedor = Proveedor.find(con, s.baseDato, id_proveedor);
    			con.close();
    			return ok(proveedorContactoAgrega.render(mapeoDiccionario,mapeoPermiso,userMnu,proveedor));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result proveedorContactoGraba(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		FormContactoProveedorGraba form = formFactory.form(FormContactoProveedorGraba.class).withDirectFieldAccess(true).bindFromRequest(request).get();
    		if (form.id_proveedor==null) {
    			return ok(mensajes.render("/",msgErrorFormulario));
    		}else {
    			try {
	    			Connection con = db.getConnection();
	    			if(ContactoProveedor.create(con, s.baseDato, form)) {
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "contactoProveedor", (long)0, "create", "crea nuevo contacto id_proveedor: "+form.id_proveedor);
	    				con.close();
	    				return redirect("/proveedorModifica/"+form.id_proveedor);
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
    
    public Result proveedorContactoElimina(Long id_contacto, Long id_proveedor, Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			if(mapeoPermiso.get("proveedorMantencion")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			ContactoProveedor.delete(con, s.baseDato, id_contacto);
    			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "contactoProveedor", id_contacto, "delete", "elimina contacto de id_proveedor: "+id_proveedor);
    			con.close();
    			return redirect("/proveedorModifica/"+id_proveedor);
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result modificaContactoProveedorPorCampoAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	String campo = form.get("campo").trim();
	       		Long id_contacto = Long.parseLong(form.get("id_contacto").trim());
	       		String valor = form.get("valor").trim();
				try {
	    			Connection con = db.getConnection();
	    			ContactoProveedor.modificaPorCampo(con, s.baseDato, campo, id_contacto, valor);
	    			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "contactoProveedor", id_contacto, "update", "cambia contacto en campo: "+campo);
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
    
    public Result proveedorElimina(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
    		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_proveedor = Long.parseLong(form.get("id_proveedor").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			if(mapeoPermiso.get("proveedorMantencion")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			if(Proveedor.estaEnUso(con, s.baseDato, id_proveedor)) {
	    				String msg = "No es posible eliminar este proveedor, esta en uso con equipos";
	    				con.close();
	    				return ok(mensajes.render("/proveedorMantencion/",msg));
	    			}else {
	    				Proveedor proveedor = Proveedor.find(con, s.baseDato, id_proveedor);
	    				if(Proveedor.delete(con, s.baseDato, id_proveedor)) {
	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "proveedor", id_proveedor, "delete", "elimina proveedor "+proveedor.getNickName());
	    					con.close();
	        				return redirect("/proveedorMantencion/");
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
    
    public Result proveedorAgrega(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("proveedorMantencion")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<Regiones> listRegiones = Regiones.all(con, s.baseDato);
    			con.close();
    			return ok(proveedorAgrega.render(mapeoDiccionario,mapeoPermiso,userMnu,listRegiones));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result proveedorGraba(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		FormProveedorGraba form = formFactory.form(FormProveedorGraba.class).withDirectFieldAccess(true).bindFromRequest(request).get();
    		if (form.nickName==null) {
    			return ok(mensajes.render("/",msgErrorFormulario));
    		}else {
    			try {
	    			Connection con = db.getConnection();
	    			if(Proveedor.create(con, s.baseDato, form)) {
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "proveedor", (long)0, "create", "crea nuevo proveedor "+form.nickName);
	    				con.close();
	    				return redirect("/proveedorMantencion/");
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
    
    
    public Result proveedorGrabaAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		FormProveedorGraba form = formFactory.form(FormProveedorGraba.class).withDirectFieldAccess(true).bindFromRequest(request).get();
    		if (form.nickName==null) {
	   			return ok("error");
	       	}else {
				try {
	    			Connection con = db.getConnection();
	    			Proveedor proveedor = Proveedor.findPorNickName(con, s.baseDato, form.nickName);
	    			if(proveedor == null || (long) proveedor.id == (long)0) {
	    				if(Proveedor.create(con, s.baseDato, form)) {
	    					proveedor = Proveedor.findPorNickName(con, s.baseDato, form.nickName);
	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "proveedor", (long)0, "create", "crea nuevo proveedor "+form.nickName);
		    				con.close();
		    				return ok(proveedor.getId().toString());
		    			}
	    			}else {
	    				return ok("existe");
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
    
    public Result verificaNickProveedorAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	String nickName = form.get("nickName").trim();
				try {
	    			Connection con = db.getConnection();
	    				if(Proveedor.existeNickName(con, s.baseDato, nickName)) {
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
    
    
    //============================================================
    // MNU tipoEstadoMantencion   Tablas/Tipo Estados Equipo
    //============================================================
    
    public Result tipoEstadoMantencion(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("tipoEstado")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<TipoEstado> listEstados = TipoEstado.all(con, s.baseDato);
    			con.close();
    			return ok(tipoEstadoMantencion.render(mapeoDiccionario,mapeoPermiso,userMnu,listEstados));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result tipoEstadoModifica(Long id_tipoEstado, Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("tipoEstado")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			
    			TipoEstado tipoEstado = TipoEstado.find(con, s.baseDato, id_tipoEstado);
    			List<TipoReparacion> listTipoReparacion = TipoReparacion.allxIdTipoEstado(con, s.baseDato, id_tipoEstado);
    			List<List<String>> listBodegas = BodegaEmpresa.listaAllBodegasVigentesInternas(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
    			con.close();
    			return ok(tipoEstadoModifica.render(mapeoDiccionario,mapeoPermiso,userMnu,tipoEstado,listTipoReparacion,listBodegas));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result modificaTipoEstadoPorCampoAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	String campo = form.get("campo").trim();
	       		Long id_tipoEstado = Long.parseLong(form.get("id_tipoEstado").trim());
	       		String valor = form.get("valor").trim();
				try {
	    			Connection con = db.getConnection();
	    			if(campo.equals("sigla")) {
	    				if(TipoEstado.existeSigla(con, s.baseDato, valor)) {
	    	    			con.close();
	    	    			return ok("existe");
	    				}
	    			}
	    			if(!TipoEstado.modificaPorCampo(con, s.baseDato, campo, id_tipoEstado, valor)){
	    				con.close();
	    				return ok("error");
	    			}else {
	    				TipoEstado tipo = TipoEstado.find(con, s.baseDato, id_tipoEstado);
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "tipoEstado", id_tipoEstado, "update", "cambia el valor de: "+campo+" en tipo estado "+tipo.getNombre());
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
    
    public Result tipoReparacionModifica(Long id_tipoReparacion, Long id_tipoEstado, Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("tipoEstado")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			TipoReparacion tipoReparacion = TipoReparacion.find(con, s.baseDato, id_tipoReparacion);
    			TipoEstado tipoEstado = TipoEstado.find(con, s.baseDato, id_tipoEstado);
    			List<Moneda> listMonedas = Moneda.all(con, s.baseDato);
    			con.close();
    			return ok(tipoReparacionModifica.render(mapeoDiccionario,mapeoPermiso,userMnu,tipoReparacion,tipoEstado,listMonedas));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result tipoReparacionAgrega(Long id_tipoEstado, Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("tipoEstado")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			TipoEstado tipoEstado = TipoEstado.find(con, s.baseDato, id_tipoEstado);
    			List<Moneda> listMonedas = Moneda.all(con, s.baseDato);
    			con.close();
    			return ok(tipoReparacionAgrega.render(mapeoDiccionario,mapeoPermiso,userMnu,tipoEstado,listMonedas));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result verificaSiglaTipoReparacionAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	String sigla = form.get("sigla").trim();
	    	  	Long id_tipoEstado = Long.parseLong(form.get("id_tipoEstado").trim());
				try {
	    			Connection con = db.getConnection();
	    				if(TipoReparacion.existeSigla(con, s.baseDato, sigla, id_tipoEstado)) {
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
    
    public Result tipoReparacionGraba(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		FormTipoReparacionGraba form = formFactory.form(FormTipoReparacionGraba.class).withDirectFieldAccess(true).bindFromRequest(request).get();
    		if (form.id_tipoEstado==null) {
    			return ok(mensajes.render("/",msgErrorFormulario));
    		}else {
    			try {
	    			Connection con = db.getConnection();
	    			if(TipoReparacion.create(con, s.baseDato, form)) {
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "tipoReparacion", (long)0, "create", "crea nuevo tipoReparacion id_tipoEstado: "+form.id_tipoEstado);
	    				con.close();
	    				return redirect("/tipoEstadoModifica/"+form.id_tipoEstado);
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
    
    public Result tipoReparacionElimina(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
    		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_tipoReparacion = Long.parseLong(form.get("id_tipoReparacion").trim());
	       		Long id_tipoEstado = Long.parseLong(form.get("id_tipoEstado").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			if(mapeoPermiso.get("tipoEstado")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			if(TipoReparacion.estaEnUso(con, s.baseDato, id_tipoReparacion)) {
	    				String msg = "No es posible eliminar este tipo de reparación, esta en uso.";
	    				con.close();
	    				return ok(mensajes.render("/tipoEstadoModifica/"+id_tipoEstado,msg));
	    			}else {
	    				if(TipoReparacion.delete(con, s.baseDato, id_tipoReparacion)) {
	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "tipoReparacion", id_tipoReparacion, "delete", "elimina tipo de reparación");
	    					con.close();
	        				return redirect("/tipoEstadoModifica/"+id_tipoEstado);
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
    
    public Result modificaTipoReparacionPorCampoAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	String campo = form.get("campo").trim();
	       		Long id_tipoReparacion = Long.parseLong(form.get("id_tipoReparacion").trim());
	       		String valor = form.get("valor").trim();
	       		Long id_tipoEstado = Long.parseLong(form.get("id_tipoEstado").trim());
				try {
	    			Connection con = db.getConnection();
	    			if(campo.equals("sigla")) {
	    				if(TipoReparacion.existeSigla(con, s.baseDato, valor, id_tipoEstado)) {
	    	    			con.close();
	    	    			return ok("existe");
	    				}
	    			}
	    			if(!TipoReparacion.modificaPorCampo(con, s.baseDato, campo, id_tipoReparacion, valor)){
	    				con.close();
	    				return ok("error");
	    			}else {
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "tipoReparacion", id_tipoReparacion, "update", "cambia tipo de reparación en campo: "+campo);
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
    
    public Result tipoEstadoElimina(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
    		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_tipoEstado = Long.parseLong(form.get("id_tipoEstado").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			if(mapeoPermiso.get("tipoEstado")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			if(TipoEstado.estaEnUso(con, s.baseDato, id_tipoEstado)) {
	    				String msg = "No es posible eliminar este tipo de estado, esta en uso.";
	    				con.close();
	    				return ok(mensajes.render("/tipoEstadoMantencion/",msg));
	    			}else {
	    				TipoEstado tipo = TipoEstado.find(con, s.baseDato, id_tipoEstado);
	    				if(TipoEstado.delete(con, s.baseDato, id_tipoEstado)) {
	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "tipoEstado", id_tipoEstado, "delete", "elimina tipo de estado "+tipo.getNombre());
	    					con.close();
	        				return redirect("/tipoEstadoMantencion/");
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
    
    public Result tipoEstadoAgrega(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("tipoEstado")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			
    			List<List<String>> listBodegas = BodegaEmpresa.listaAllBodegasVigentesInternas(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
    			con.close();
    			return ok(tipoEstadoAgrega.render(mapeoDiccionario,mapeoPermiso,userMnu,listBodegas));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result tipoEstadoGraba(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		FormTipoEstadoGraba form = formFactory.form(FormTipoEstadoGraba.class).withDirectFieldAccess(true).bindFromRequest(request).get();
    		if (form.sigla==null) {
    			return ok(mensajes.render("/",msgErrorFormulario));
    		}else {
    			try {
	    			Connection con = db.getConnection();
	    			if(TipoEstado.create(con, s.baseDato, form)) {
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "tipoEstado", (long)0, "create", "crea nuevo tipo de estado "+form.nombre);
	    				con.close();
	    				return redirect("/tipoEstadoMantencion/");
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
    
    public Result verificaSiglaTipoEstadoAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	String sigla = form.get("sigla").trim();
				try {
	    			Connection con = db.getConnection();
	    				if(TipoEstado.existeSigla(con, s.baseDato, sigla)) {
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
    
    //============================================================
    // MNU decimalNumero   Tablas/Cantidad Decimales
    //============================================================
    
    public Result decimalNumero(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("decimalNumero")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<Moneda> listMonedas = Moneda.all(con, s.baseDato);
    			con.close();
    			return ok(decimalNumero.render(mapeoDiccionario,mapeoPermiso,userMnu,listMonedas));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result decimalModificaAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	Long id_moneda = Long.parseLong(form.get("id_moneda").trim());
	    	  	Long numeroDecimales = Long.parseLong(form.get("numeroDecimales").trim());
				try {
	    			Connection con = db.getConnection();
	    				if(Moneda.modifyDecimales(con, s.baseDato, id_moneda, numeroDecimales)) {
	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "moneda", id_moneda, "update", "cambia el numero de decimales");
	    	    			con.close();
	    	    			return ok("");
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
    // MNU usuarioMantencion   Tablas/Usuarios/Perfiles
    //============================================================
    
    public Result usuarioMantencion(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("usuarioMantencion")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<Usuario> listUsuarios = Usuario.all(con, s.baseDato);
    			con.close();
    			return ok(usuarioMantencion.render(mapeoDiccionario,mapeoPermiso,userMnu,listUsuarios));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result usuarioModifica(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	Long id_usuario = Long.parseLong(form.get("id_usuario").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			Usuario usuario = Usuario.findXIdUser(con, s.baseDato, id_usuario);
	    			List<UsuarioTipo> listTipoUsuario = UsuarioTipo.all(con, s.baseDato);
	    			List<List<String>> listPermisoBodPorUsuario = UsuarioPermiso.listaPermisoBodPorUsuario(con, s.baseDato, id_usuario);
	    			Long esPorPorProyecto = (long) 0;
	    			if(UsuarioTipo.esPorProyecto(con, s.baseDato, usuario.id_tipoUsuario)) {
	    				esPorPorProyecto = (long) 1;
	    			}
	    			List<List<Long>> listEsPorProyecto = Usuario.listParOfIdTipoUsuarioVsEsPorProyecto(con, s.baseDato);
	    			List<Sucursal> listSucursal = Sucursal.all(con, s.baseDato);
	    			Sucursal sucursal = Sucursal.find(con, s.baseDato, usuario.getId_sucursal().toString());
	    			con.close();
	    			return ok(usuarioModifica.render(mapeoDiccionario,mapeoPermiso,userMnu,usuario,listTipoUsuario,listPermisoBodPorUsuario,esPorPorProyecto,listEsPorProyecto,listSucursal,sucursal));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result modificaUsuarioPorCampoAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	String campo = form.get("campo").trim();
	       		Long id_usuario = Long.parseLong(form.get("id_usuario").trim());
	       		String valor = form.get("valor").trim();
				try {
	    			Connection con = db.getConnection();
	    			if(campo.equals("userName")) {
	    				if(Usuario.existe(con, s.baseDato, valor)) {
	    	    			con.close();
	    	    			return ok("existe");
	    				}
	    			}
	    			if(!Usuario.modificaPorCampo(con, s.baseDato, campo, id_usuario, valor)){
	    				con.close();
	    				return ok("error");
	    			}else {
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "usuario", id_usuario, "update", "cambia el valor de: "+campo);
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
    
    public Result usuarioElimina(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	Long id_usuario = Long.parseLong(form.get("id_usuario").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			if(mapeoPermiso.get("usuarioMantencion")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
					if(Usuario.delete(con, s.baseDato, id_usuario)) {
						Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "usuario", id_usuario, "delete", "elimina usuario");
						con.close();
	    				return redirect("/usuarioMantencion/");
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
    
    public Result usuarioAgrega(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("usuarioMantencion")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<UsuarioTipo> listTipoUsuario = UsuarioTipo.all(con, s.baseDato);
    			List<List<Long>> listEsPorProyecto = Usuario.listParOfIdTipoUsuarioVsEsPorProyecto(con, s.baseDato);
    			con.close();
    			return ok(usuarioAgrega.render(mapeoDiccionario,mapeoPermiso,userMnu,listTipoUsuario,listEsPorProyecto));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result verificaUserNameUsuarioAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	String userName = form.get("userName").trim();
				try {
	    			Connection con = db.getConnection();
	    				if(Usuario.existe(con, s.baseDato, userName)) {
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
    
    public Result usuarioGraba(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		FormUsuarioGraba form = formFactory.form(FormUsuarioGraba.class).withDirectFieldAccess(true).bindFromRequest(request).get();
    		if (form.userName==null) {
    			return ok(mensajes.render("/",msgErrorFormulario));
    		}else {
    			try {
	    			Connection con = db.getConnection();
	    			if(Usuario.create(con, s.baseDato, form)) {
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "usuario", (long)0, "create", "crea nuevo usuario");
	    				con.close();
	    				return redirect("/usuarioMantencion/");
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
    
    public Result usuarioListBodegaEmpresa(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	Long id_usuario = Long.parseLong(form.get("id_usuario").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("usuarioMantencion")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			List<List<String>> listPermisoBodPorUsuario = UsuarioPermiso.listaPermisoBodPorUsuario(con, s.baseDato, id_usuario);
	    			
	    			List<List<String>> listBodegas = BodegaEmpresa.listaAllBodegasVigentesInternasExternas(con, s.baseDato, mapeoPermiso, s.aplicaPorSucursal, s.id_sucursal);
	    			List<List<String>> listadoBodegas = new ArrayList<List<String>>();
	    			listBodegas.forEach(lista -> {
	    				int flag = 0;
	    				for(int i=0; i<listPermisoBodPorUsuario.size();i++) {
	    					if(lista.get(1).equals(listPermisoBodPorUsuario.get(i).get(0))) {
	    						flag = 1;
	    						break;
	    					}
	    				}
						if(flag==0) {
							listadoBodegas.add(lista);
						}
	    			});
	    			con.close();
	    			return ok(usuarioListBodegaEmpresa.render(mapeoDiccionario,mapeoPermiso,userMnu,id_usuario,listadoBodegas));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result usuarioBodegaSelect(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	       		Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
	    	  	Long id_usuario = Long.parseLong(form.get("id_usuario").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			if(UsuarioPermiso.asignaPermisoPorBodega(con, s.baseDato, id_usuario, id_bodegaEmpresa)) {
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "permisoPorBodegaEmpresa", (long)0, "create", 
	    						"asigna permiso a id_bodega:"+id_bodegaEmpresa+" para id_usuario:"+id_usuario);
		    			
		    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		    			Usuario usuario = Usuario.findXIdUser(con, s.baseDato, id_usuario);
		    			List<UsuarioTipo> listTipoUsuario = UsuarioTipo.all(con, s.baseDato);
		    			List<List<String>> listPermisoBodPorUsuario = UsuarioPermiso.listaPermisoBodPorUsuario(con, s.baseDato, id_usuario);
		    			Long esPorPorProyecto = (long) 0;
		    			if(UsuarioTipo.esPorProyecto(con, s.baseDato, usuario.id_tipoUsuario)) {
		    				esPorPorProyecto = (long) 1;
		    			}
		    			List<List<Long>> listEsPorProyecto = Usuario.listParOfIdTipoUsuarioVsEsPorProyecto(con, s.baseDato);
		    			List<Sucursal> listSucursal = Sucursal.all(con, s.baseDato);
		    			Sucursal sucursal = Sucursal.find(con, s.baseDato, usuario.getId_sucursal().toString());
		    			con.close();
		    			return ok(usuarioModifica.render(mapeoDiccionario,mapeoPermiso,userMnu,usuario,listTipoUsuario,listPermisoBodPorUsuario,esPorPorProyecto,listEsPorProyecto,listSucursal, sucursal));
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
    
    public Result usuarioBodegaEmpresaElimina(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
	    	  	Long id_usuario = Long.parseLong(form.get("id_usuario").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			if(UsuarioPermiso.eliminaPermisoPorBodega(con, s.baseDato, id_usuario, id_bodegaEmpresa)) {
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "permisoPorBodegaEmpresa", (long)0, "delete", "elimina permiso a id_bodega:"+id_bodegaEmpresa+" para id_usuario:"+id_usuario);
	    				
		    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		    			Usuario usuario = Usuario.findXIdUser(con, s.baseDato, id_usuario);
		    			List<UsuarioTipo> listTipoUsuario = UsuarioTipo.all(con, s.baseDato);
		    			List<List<String>> listPermisoBodPorUsuario = UsuarioPermiso.listaPermisoBodPorUsuario(con, s.baseDato, id_usuario);
		    			Long esPorPorProyecto = (long) 0;
		    			if(UsuarioTipo.esPorProyecto(con, s.baseDato, usuario.id_tipoUsuario)) {
		    				esPorPorProyecto = (long) 1;
		    			}
		    			List<List<Long>> listEsPorProyecto = Usuario.listParOfIdTipoUsuarioVsEsPorProyecto(con, s.baseDato);
		    			List<Sucursal> listSucursal = Sucursal.all(con, s.baseDato);
		    			Sucursal sucursal = Sucursal.find(con, s.baseDato, usuario.getId_sucursal().toString());
		    			con.close();
		    			return ok(usuarioModifica.render(mapeoDiccionario,mapeoPermiso,userMnu,usuario,listTipoUsuario,listPermisoBodPorUsuario,esPorPorProyecto,listEsPorProyecto,listSucursal, sucursal));
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
    
    public Result modVigUsuarioAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	Long id_usuario = Long.parseLong(form.get("id_usuario").trim());
	    	  	Long valor = Long.parseLong(form.get("valor").trim());
	    	  	
	    		try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			if(mapeoPermiso.get("usuarioMantencion")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
					if(Usuario.modivicaVigencia(con, s.baseDato, id_usuario, valor)) {
						Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "usuario", id_usuario, "update", "cambia vigencia de usuario");
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
    
    public Result comercialMantencion(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("usuarioMantencion")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<Comercial> listComercial = Comercial.allPorIdSucursalSoloUsuariosVigentes(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
    			List<Usuario> listUsuario = Usuario.allUsuariosNoEnComercial(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
    			con.close();
    			return ok(comercialMantencion.render(mapeoDiccionario,mapeoPermiso,userMnu,listComercial,listUsuario));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result modVigComercialAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	Long id_comercial = Long.parseLong(form.get("id_comercial").trim());
	    	  	Long valor = Long.parseLong(form.get("valor").trim());
	    	  	
	    		try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			if(mapeoPermiso.get("usuarioMantencion")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
					if(Comercial.modivicaVigencia(con, s.baseDato, id_comercial, valor)) {
						Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "comercial", id_comercial, "update", "cambia vigencia de comercial");
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
    
    public Result comercialAgrega(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	Long id_usuario = Long.parseLong(form.get("id_usuario").trim());
	    	  	
	    		try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			if(mapeoPermiso.get("usuarioMantencion")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
					if(Comercial.agregaComercial(con, s.baseDato, id_usuario)) {
						Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "comercial", id_usuario, "insert", "agrega nuevo comercial");
						return redirect("/routes2/comercialMantencion/");
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
    
    
    public Result precioMantSelSucursal(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			if(mapeoPermiso.get("precioMantencion")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			List<Sucursal> listSucursal = Sucursal.all(con, s.baseDato);
    			con.close();
    			return ok(precioMantSelSucursal.render(mapeoDiccionario,mapeoPermiso,userMnu,listSucursal));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    
    
    //============================================================
    // MNU registroDeCambios   Tablas/Historial de Cambios
    //============================================================
    
    public Result registroDeCambios(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("historialCambios")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<List<String>> listHistorial = Registro.historialRegistro(con, s.baseDato);
    			con.close();
    			return ok(registroDeCambios.render(mapeoDiccionario,mapeoPermiso,userMnu,listHistorial));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    
    
    
    
    

}
