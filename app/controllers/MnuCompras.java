package controllers;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;

import controllers.HomeController.Sessiones;
import models.api.ApiIConstruyeOC;
import models.forms.FormCompra;
import models.forms.FormEquipoGraba;
import models.reports.ReportMovCompras;
import models.tables.Baja;
import models.tables.BodegaEmpresa;
import models.tables.Compra;
import models.tables.EmisorTributario;
import models.tables.Equipo;
import models.tables.EquivalenciasMonedas;
import models.tables.Fabrica;
import models.tables.Factura;
import models.tables.Grupo;
import models.tables.IConstruye;
import models.tables.Moneda;
import models.tables.Movimiento;
import models.tables.Precio;
import models.tables.Proveedor;
import models.tables.Regiones;
import models.tables.Sucursal;
import models.tables.Unidad;
import models.tables.UnidadTiempo;
import models.utilities.Fechas;
import models.utilities.Registro;
import models.utilities.UserMnu;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.Database;
import play.libs.Files.TemporaryFile;
import play.libs.mailer.MailerClient;
import play.libs.ws.WSClient;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.mensajes;
import viewsMnuCompras.html.*;



public class MnuCompras extends Controller {
	
	public static Database db = HomeController.db;
	public static FormFactory formFactory = HomeController.formFactory;
	public static String msgError = HomeController.msgError;
	public static String msgErrorFormulario = HomeController.msgErrorFormulario;
	public static String msgSinPermiso = HomeController.msgSinPermiso;
	
	private final WSClient ws;
	public final MailerClient mailerClient;
	
	@Inject
	  public MnuCompras(WSClient ws, MailerClient mailerClient) {
	    this.ws = ws;
	    this.mailerClient = mailerClient;
	  }
	
	
	//============================================================
    // MNU compraImportIconstruye   Compras/Import IConstruye
    //============================================================

    public Result compraImportIconstruye0(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("compraIngreso")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			
    			Fechas hoy = Fechas.hoy();
    			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			
    			EmisorTributario emisor = EmisorTributario.find(con, s.baseDato);
    			String token = ApiIConstruyeOC.obtieneToken(emisor, ws);
    			List<List<String>> listIdOrgc = ApiIConstruyeOC.obtieneCtrosGestion(emisor, ws, token, mapeoDiccionario.get("nEmpresa"));
    			
    			
    			con.close();
    			return ok(compraImportIconstruye0.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta, listIdOrgc));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
	public Result compraImportIconstruye1(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String desdeAAMMDD = form.get("fechaDesde").trim();
	       		String hastaAAMMDD = form.get("fechaHasta").trim();
	       		String idOrgc = form.get("idOrgc").trim();
	       		try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			EmisorTributario emisor = EmisorTributario.find(con, s.baseDato);
	    			String token = ApiIConstruyeOC.obtieneToken(emisor, ws);
	    			List<List<String>> listaOC = new ArrayList<List<String>>();
	    			List<List<String>> listaOCAux = ApiIConstruyeOC.obtieneListaOC(emisor, ws, token, desdeAAMMDD, hastaAAMMDD, idOrgc);
	    			Map<String,Long> mapIConstruye = IConstruye.mapNumOcVsIdDoc(con, s.baseDato);
	    			for(List<String> lista: listaOCAux) {
	    				Long idDoc = mapIConstruye.get(lista.get(1));
	    				if(idDoc==null) {
	    					listaOC.add(lista);
	    				}
	    			}
	    			con.close();
	    			return ok(compraImportIconstruye1.render(mapeoDiccionario,mapeoPermiso,userMnu,listaOC));
	       		} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    		return ok(mensajes.render("/",msgError));
    }
	
	public Result compraImportIconstruye2(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String idDocumento = form.get("idDocumento").trim();
	       		try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			
	    			
	    			EmisorTributario emisor = EmisorTributario.find(con, s.baseDato);
	    			String token = ApiIConstruyeOC.obtieneToken(emisor, ws);
	    			List<List<String>> detalleOC = ApiIConstruyeOC.obtieneDetalleOC(emisor, ws, token, idDocumento);
	    			
	    			Proveedor proveedor = new Proveedor();
	    			
	    			if(detalleOC.size()>0) {
	    				
	    				String rut = detalleOC.get(0).get(3).toUpperCase().trim();
	    				
	    				if(!Proveedor.existeRut(con, s.baseDato, rut)) {
	    					String nombre = detalleOC.get(0).get(4).trim();
	    					String nick = nombre;
	    					if (nick.length()>20) {
	    						nick = nick.substring(0,20);
	    					}
	    					if(Proveedor.existeNickName(con, s.baseDato, nick)) {
	    						Long idMax = Proveedor.maxId(con, s.baseDato);
	    						nick = idMax + nick;
	    						if (nick.length()>20) {
		    						nick = nick.substring(0,20);
		    					}
	    					}
	    					Proveedor aux = new Proveedor();
	    					aux.rut = rut;
	    					aux.nombre = nombre;
	    					aux.nickName = nick;
	    					Proveedor.createDesdeIConstruye(con, s.baseDato, aux);
	    					proveedor = Proveedor.findPorRut(con, s.baseDato, rut);
	    				}else {
	    					proveedor = Proveedor.findPorRut(con, s.baseDato, rut);
	    				}
	    				
	    				Map<String,Unidad> mapUnidad = Unidad.mapPorNombre(con, s.baseDato);
	    				Map<String,Moneda> mapMoneda = Moneda.mapNickMonedas(con, s.baseDato);
	    				Map<String,Equipo> mapEquipo = Equipo.mapAllVigentesPorCodigo(con, s.baseDato);
	    				Map<String,EquivalenciasMonedas> mapEquivalenciasMonedas = EquivalenciasMonedas.mapEquivNickMonedas(con, s.baseDato);
	    				
	    				Long minId_fabrica = Fabrica.minId_fabrica(con, s.baseDato);
	    				Long minId_grupo = Grupo.minId_grupo(con, s.baseDato);
	    				BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, (long)2);
	    				Long numeroDecimales = (long)0;
	    				
	    				
	    				for(List<String> detalle: detalleOC) {
	    					
	    					Unidad unidad = new Unidad();
	    					Moneda moneda = new Moneda();
	    					Equipo equipo = new Equipo();
	    					
	    					String nomUnidad = detalle.get(9).toUpperCase().trim();
	    					unidad = mapUnidad.get(nomUnidad);
	    					if(unidad == null) {
	    						unidad = Unidad.create(con, s.baseDato, nomUnidad);
	    					}
	    					
	    					String nickMoneda = detalle.get(11).toUpperCase().trim().toUpperCase();
	    					moneda = mapMoneda.get(nickMoneda);
	    					if(moneda == null) {
	    						EquivalenciasMonedas equivalenciasMonedas = mapEquivalenciasMonedas.get(nickMoneda);
	    						if(equivalenciasMonedas == null) {
	    							Moneda aux = Moneda.find(con, s.baseDato, (long)1);
	    							nickMoneda = aux.getNickName();
	    						}else {
	    							nickMoneda = equivalenciasMonedas.equivEnMada;
	    						}
	    						moneda = mapMoneda.get(nickMoneda);
	    					}
	    					
	    					String codEquipo = detalle.get(6).toUpperCase().trim().toUpperCase();
	    					equipo = mapEquipo.get(codEquipo.toUpperCase());
	    					if(equipo == null) {
	    						String nombEquipo = detalle.get(7).toUpperCase().trim();
	    						FormEquipoGraba aux = new FormEquipoGraba();
	    						aux.codigo = codEquipo;
	    						aux.id_fabrica = minId_fabrica;
	    						aux.id_grupo = minId_grupo;
	    						aux.id_unidad = unidad.id;
	    						aux.idAtributos = new ArrayList<Long>();
	    						aux.nombre = nombEquipo;
	    						aux.valorAtributos = new ArrayList<String>();
	    						Equipo.create(con, s.baseDato, aux);
	    						equipo = Equipo.findXCodigo(con, s.baseDato, codEquipo);
	    					}
	    					
	    					detalle.set(14, equipo.id.toString());
	    					detalle.set(15, moneda.id.toString());
	    					detalle.set(17, bodega.nombre);
	    					
	    					
	    					numeroDecimales = moneda.numeroDecimales;
	    					DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
	    					if((long)numeroDecimales == (long) 0) {
	    						decimalFormat = new DecimalFormat("#,##0");
	    					} else if((long)numeroDecimales == (long) 2) {
	    						decimalFormat = new DecimalFormat("#,##0.00");
	    					} else if((long)numeroDecimales == (long) 4) {
	    						decimalFormat = new DecimalFormat("#,##0.0000");
	    					}else {
	    						decimalFormat = new DecimalFormat("#,##0.000000");
	    					}
	    					
	    					detalle.set(16, decimalFormat.format(Double.parseDouble(detalle.get(16))));
	    					
	    				}
	    				List<Equipo> listEquipo = Equipo.allAll(con, s.baseDato);
	    				List<Moneda> listMoneda = Moneda.all(con, s.baseDato);
	    				List<List<String>> listBodegas = BodegaEmpresa.listaAllBodegasVigentesInternas(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
	    				List<Moneda> listMon = Moneda.all(con, s.baseDato);
		    			List<Grupo> listGrupos = Grupo.all(con, s.baseDato);
		    			List<Fabrica> listFabrica = Fabrica.all(con, s.baseDato);
		    			List<Unidad> listUnidades = Unidad.all(con, s.baseDato);
	    				
	    				con.close();
	    				return ok(compraImportIconstruye2.render(mapeoDiccionario,mapeoPermiso,userMnu,proveedor,detalleOC,
	    						listEquipo,listMoneda,listBodegas,listMon,listGrupos,listFabrica,listUnidades,numeroDecimales));
	    			}
	    			
	       		} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    		return ok(mensajes.render("/",msgError));
    }
	
	//============================================================
    // MNU compraIngreso   Compras/Ingresar Compras
    //============================================================

    public Result compraIngreso(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("compraIngreso")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<Proveedor> listProveedor = Proveedor.all(con, s.baseDato);
    			List<Equipo> listEquipo = Equipo.allVigentes(con, s.baseDato);
    			List<Moneda> listMoneda = Moneda.all(con, s.baseDato);
    			List<List<String>> listBodegas = BodegaEmpresa.listaAllBodegasVigentesInternas(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
    			List<Moneda> listMon = Moneda.all(con, s.baseDato);
    			List<Grupo> listGrupos = Grupo.all(con, s.baseDato);
    			List<Fabrica> listFabrica = Fabrica.all(con, s.baseDato);
    			List<Unidad> listUnidades = Unidad.all(con, s.baseDato);
    			List<Regiones> listRegiones = Regiones.all(con, s.baseDato);
    			List<Sucursal> listSucursales = Sucursal.all(con, s.baseDato);
    			con.close();
    			return ok(compraIngreso.render(mapeoDiccionario,mapeoPermiso,userMnu,listProveedor,listEquipo,listMoneda,listBodegas,listMon,listGrupos,listFabrica,listUnidades,
    					listRegiones, listSucursales));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result verificaNumeroFacturaAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	Long numeroFactura = Long.parseLong(form.get("numeroFactura").trim());
	    	  	Long id_proveedor = Long.parseLong(form.get("id_proveedor").trim());
				try {
	    			Connection con = db.getConnection();
	    				if(Factura.existeNumero(con, s.baseDato, numeroFactura, id_proveedor)) {
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
    
    public Result compraNuevo(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    			FormCompra form = formFactory.form(FormCompra.class).withDirectFieldAccess(true).bindFromRequest(request).get();
        		if (form.numeroFactura==null || form.id_factura==null) {
        			return ok(mensajes.render("/",msgErrorFormulario));
        		}else {
        			Http.MultipartFormData<TemporaryFile> body = request.body().asMultipartFormData();
    				Http.MultipartFormData.FilePart<TemporaryFile> docAdjunto = body.getFile("docAdjunto");
        			try {
    	    			Connection con = db.getConnection();
    	    			if(Factura.existeNumero(con, s.baseDato, form.numeroFactura, form.id_proveedor)) {
    	    				String msg = "El numero de factura para este proveedor ya existe, debe volver a ingresar la compra";
    	    				con.close();
    	    				return ok(mensajes.render("/home/",msg));
    	    			}
    	    			String numOcIConstruye = form.numOcIConstruye;
    	    			if(FormCompra.create(con, s.baseDato, form, docAdjunto, numOcIConstruye, s.id_usuario)) {
    	    				if(numOcIConstruye.equals("0")) {
    	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "compra", (long)0, "create", "ingresa nueva compra nro: "+form.numeroFactura);
    	    				}else {
    	    					IConstruye.create(con, s.baseDato, form.numeroFactura, numOcIConstruye);
    	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "compra", (long)0, "create", "ingresa nueva compra desde ICONSTRUYE nro: "+form.numeroFactura +" nroOC: "+form.numOcIConstruye);
    	    				}
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
    
    public Result modalPreciosAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	Long id_sucursal = Long.parseLong(form.get("id_sucursal").trim());
	    	  	Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
				try {
	    			Connection con = db.getConnection();
    				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    				Equipo equipo = Equipo.find(con, s.baseDato, id_equipo);
    				Precio precio = Precio.findXIdEquipo(con, s.baseDato, id_sucursal, id_equipo, mapeoDiccionario);
    				if(precio==null){
    					Fechas fecha = Fechas.hoy();
    					if(Precio.create(con, s.baseDato, id_sucursal, id_equipo, fecha.getFechaStrAAMMDD())) {
    						precio = Precio.findXIdEquipo(con, s.baseDato, id_sucursal, id_equipo, mapeoDiccionario);
    					}else {
    						con.close();
    						return ok("error");
    					}
    				}
    				String vista = "<b>"+equipo.getCodigo() + " - " + equipo.getNombre() + "</b><hr>";
    				List<Moneda> listMonedas = Moneda.all(con, s.baseDato);
    				List<UnidadTiempo> listUnidadTiempo = UnidadTiempo.all(con, s.baseDato);
    				vista += "<table id='precioEquipo' class='table table-sm table-bordered table-condensed table-hover table-fluid'>"+
    						"<TR>"+
								"<td  style='text-align:left;'>MONEDA:</td>"+
								"<td  style='text-align:left;'>"+
									"<select class='custom-select' id='precioMoneda' onchange='cambiarPrecio("+id_sucursal+","+precio.getId_equipo()+",\"id_moneda\",value)'>"+
									"<option value='"+precio.getId_moneda()+"'>"+precio.getNombreMoneda()+"</option>";
				    				for(int i=0; i<listMonedas.size(); i++) {
				    					vista += "<option value='"+listMonedas.get(i).getId()+"'>"+listMonedas.get(i).getNickName()+"</option>";
				    				};
				    			vista += "</td>"+
				    		"</TR>"+
				    		"<TR>"+
				    			"<td  style='text-align:left;'>PRECIO REPOS/VTA:</td>"+
				    			"<td  style='text-align:left;'>"+
				    				"<input type=\"text\" class=\"form-control left\" " + 
				    				"id=\"precioVenta\" " + 
				    				"value=\""+precio.getPrecioVenta()+"\" " + 
				    				"onfocus=\"value=value.replace(/,/g,'')\" " + 
				    				"onkeypress=\"return ingresoDouble(event,value)\" " + 
				    				"onchange='cambiarPrecio("+id_sucursal+","+precio.getId_equipo()+",\"precioVenta\",value)'>"+
				    			"</td>"+
			    			"</TR>"+
				    		"<TR>"+
				    			"<td  style='text-align:left;'>TASA "+mapeoDiccionario.get("Arriendo")+":</td>"+
				    			"<td  style='text-align:left;'>"+
					    			"<input type=\"text\" class=\"form-control left\" " + 
					    			"id=\"precioTasa\" value=\""+precio.getTasaArriendo()+"\" disabled>"+
				    			"</td>"+
			    			"</TR>"+
				    		"<TR>"+
				    			"<td  style='text-align:left;'>PRECIO "+mapeoDiccionario.get("Arriendo")+":</td>"+
				    			"<td  style='text-align:left;'>"+
				    				"<input type=\"text\" class=\"form-control left\" " + 
				    				"id=\"precioArriendo\" " + 
				    				"value=\""+precio.getPrecioArriendo()+"\" " + 
				    				"onfocus=\"value=value.replace(/,/g,'')\" " + 
				    				"onkeypress=\"return ingresoDouble(event,value)\" " + 
				    				"onchange='cambiarPrecio("+id_sucursal+","+precio.getId_equipo()+",\"precioArriendo\",value)'>"+
				    			"</td>"+
			    			"</TR>"+
				    		"<TR>"+
				    			"<td  style='text-align:left;'>UNIDAD "+mapeoDiccionario.get("Arriendo")+":</td>"+
				    			"<td  style='text-align:left;'>"+
									"<select class='custom-select' id='precioUnidadTiempo' onchange='cambiarPrecio("+id_sucursal+","+precio.getId_equipo()+",\"id_unidadTiempo\",value)'>"+
									"<option value='"+precio.getId_unidadTiempo()+"'>"+precio.getNombreUnidadTiempo()+"</option>";
				    				for(int i=0; i<listUnidadTiempo.size(); i++){
				    					vista += "<option value='"+listUnidadTiempo.get(i).getId()+"'>"+listUnidadTiempo.get(i).getNombre()+"</option>";
				    				};
				    			vista += "</td>"+
	    					"</TR>"+
				    		"<TR>"+
		    					"<td  style='text-align:left;'>VALOR MINIMO:</td>"+
				    			"<td  style='text-align:left;'>"+
			    					"<input type=\"text\" class=\"form-control left\" " + 
			    					"id=\"precioMinimo\" " + 
			    					"value=\""+precio.getPrecioMinimo()+"\" " + 
			    					"onfocus=\"value=value.replace(/,/g,'')\" " + 
			    					"onkeypress=\"return ingresoDouble(event,value)\" " + 
			    					"onchange='cambiarPrecio("+id_sucursal+","+precio.getId_equipo()+",\"precioMinimo\",value)'>"+
			    				"</td>"+
		    				"</TR>"+
				    		"<TR>"+
			    				"<td  style='text-align:left;'>MINIMO EN DIAS:</td>"+
				    			"<td  style='text-align:left;'>"+
			    					"<input type=\"text\" class=\"form-control left\" " + 
			    					"id=\"permanenciaMinima\" " + 
			    					"value=\""+precio.getPermanenciaMinima()+"\" " + 
			    					"onfocus=\"value=value.replace(/,/g,'')\" " + 
			    					"onkeypress=\"return ingresoInt(event)\" " + 
			    					"onchange='cambiarPrecio("+id_sucursal+","+precio.getId_equipo()+",\"permanenciaMinima\",value)'>"+
			    				"</td>"+
		    				"</TR>"+
				    	"</table>";
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
    
    public Result compraVistaSelBodDestAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	String id_sucursal = form.get("id_sucursal").trim();
				try {
	    			Connection con = db.getConnection();
	    			String esPorSucursal = "1";
	    			List<List<String>> lista = BodegaEmpresa.listaAllBodegasVigentesInternas(con, s.baseDato, esPorSucursal, id_sucursal);
    				String vista = 
    						"<select class='selBodDest form-control form-control-sm' name='id_bodegaDestino[]'>"+
    								"<option value='0'></option>";
    						for(List<String> l: lista) {
    							vista += "<option value='"+l.get(1)+"'>"+l.get(5)+"</option>";
    						}
    						vista += "</select>";
    				Sucursal sucursal = Sucursal.find(con, s.baseDato, id_sucursal);	
					con.close();
					return ok("{\"vista\":\""+vista+"\",\"nameSucursal\":\""+sucursal.nombre+"\"}").as("application/json");
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
				return ok("error");
	       	}
    	}else {
    		return ok("error");
    	}
	}
   
    public Result  compraVistaSelBodDestAjax2(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	Long id_bodega = Long.parseLong(form.get("id_bodega").trim());
				try {
	    			Connection con = db.getConnection();
	    			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodega);
	    			String esPorSucursal = "1";
	    			List<List<String>> lista = BodegaEmpresa.listaAllBodegasVigentesInternas(con, s.baseDato, esPorSucursal, bodega.getId_sucursal().toString());
    				String vista = 
    						"<select class=\"selBodDest form-control form-control-sm\" name=\"id_bodegaDestino[]\">"+
    								"<option value='0'></option>";
    						for(List<String> l: lista) {
    							vista += "<option value='"+l.get(1)+"'>"+l.get(5)+"</option>";
    						}
    						vista += "</select>";
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
    
    
    public Result nuevoEquipoAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		FormEquipoGraba form = formFactory.form(FormEquipoGraba.class).withDirectFieldAccess(true).bindFromRequest(request).get();
    		if (form.codigo==null) {
	   			return ok("error");
	       	}else {
				try {
	    			Connection con = db.getConnection();
	    			if(Equipo.create(con, s.baseDato, form)) {
	    				Equipo equipo = Equipo.findXCodigo(con, s.baseDato, form.codigo);
    	    			con.close();
    	    			return ok(equipo.getId()+"_"+equipo.getKG()+"_"+equipo.getM2());
    				}else {
    					con.close();
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
    
    //============================================================
    // MNU compraConfirma   Compras/Confirmar Compras
    //============================================================
    
    public Result compraConfirma(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("compraConfirma")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<List<String>> listaAconfirmar = Compra.listaConfirmaIngreso(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
    			con.close();
    			return ok(compraConfirma.render(mapeoDiccionario,mapeoPermiso,userMnu,listaAconfirmar));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result compraConfirmaIngreso(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
    			try {
	    			Connection con = db.getConnection();
	    			String arrIdCompras = form.get("cambiosDeEstados").trim();
	    			
	    			if(arrIdCompras.length()>0) {
	    				String listIdCompra = "";
	    				String[] arr = arrIdCompras.split(";");
	    				for(int i=0; i<arr.length; i++) {
	    					String[] detalle = arr[i].split(",");
	    					
	    					Movimiento auxMov = new Movimiento();
	    					
	    					auxMov.setId_bodegaEmpresa(Long.parseLong(detalle[4]));
	    					auxMov.setId_equipo(Long.parseLong(detalle[2]));
	    					auxMov.setId_tipoMovimiento((long)1);
	    					auxMov.setCantidad(Double.parseDouble(detalle[3]));
	    					auxMov.setId_compra(Long.parseLong(detalle[0]));
	    					
	    					
	    					if(Movimiento.createMovimientoCompra(con, s.baseDato, auxMov)) {
	    						Compra.actualizaPorCampo(con, s.baseDato, "esModificable", Long.parseLong(detalle[0]), "0");
	    						listIdCompra += detalle[0]+", ";
	    					}
	    				}
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "compra", (long)0, "confirma", "confirma compras id: "+listIdCompra);
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
    // MNU compraModifica   Compras/Modificar Compras
    //============================================================
    
    public Result compraListaModifica(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("compraModifica")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<Factura> listFacturas = Factura.all(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
    			con.close();
    			return ok(compraListaModifica.render(mapeoDiccionario,mapeoPermiso,userMnu,listFacturas));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result compraModifica(Long id_factura, Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("compraModifica")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<Proveedor> listProveedor = Proveedor.all(con, s.baseDato);
    			List<Equipo> listEquipo = Equipo.allVigentes(con, s.baseDato);
    			List<Moneda> listMoneda = Moneda.all(con, s.baseDato);
    			
    			
    			
    			List<List<String>> listBodegas = BodegaEmpresa.listaAllBodegasVigentesInternas(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
    			
    			List<Moneda> listMon = Moneda.all(con, s.baseDato);
    			List<Grupo> listGrupos = Grupo.all(con, s.baseDato);
    			List<Fabrica> listFabrica = Fabrica.all(con, s.baseDato);
    			List<Unidad> listUnidades = Unidad.all(con, s.baseDato);
    			
    			Factura factura = Factura.find(con, s.baseDato, id_factura);
    			
    			List<List<String>> detalleFactura = Factura.detalleFactura(con, s.baseDato, factura.getId_proveedor(), id_factura, s.aplicaPorSucursal, s.id_sucursal);
    			
    			List<Sucursal> listSucursales = Sucursal.all(con, s.baseDato);
    					
    			con.close();
    			return ok(compraModifica.render(mapeoDiccionario,mapeoPermiso,userMnu,listProveedor,listEquipo,listMoneda,listBodegas,listMon,listGrupos,listFabrica,listUnidades,
    					factura, detalleFactura, listSucursales));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result compraModificaGraba(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    			FormCompra form = formFactory.form(FormCompra.class).withDirectFieldAccess(true).bindFromRequest(request).get();
        		if (form.numeroFactura==null || form.id_factura==null) {
        			return ok(mensajes.render("/",msgErrorFormulario));
        		}else {
        			Http.MultipartFormData<TemporaryFile> body = request.body().asMultipartFormData();
    				Http.MultipartFormData.FilePart<TemporaryFile> docAdjunto = body.getFile("docAdjunto");
        			try {
    	    			Connection con = db.getConnection();
    	    			if(FormCompra.update(con, s.baseDato, form, docAdjunto)) {
    	    				Factura factura = Factura.find(con, s.baseDato, form.id_factura);
    	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "compra", form.id_factura, "update", "agrega lineas a la compra nro: "+factura.getNumero());
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
    
    public Result modificaFacturaPorCampoAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	Long id_factura = Long.parseLong(form.get("id_factura").trim());
	    	  	String campo = form.get("campo").trim();
	    	  	String valor = form.get("valor").trim();
				try {
	    			Connection con = db.getConnection();
	    			if(Factura.modifyXCampo(con, s.baseDato, campo, valor, id_factura)) {
	    				Factura factura = Factura.find(con, s.baseDato, id_factura);
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "factura", id_factura, "update", "modifica "+campo+" de nro: "+factura.getNumero());
	    				con.close();
    	    			return ok("ok");
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
    
    public Result modificaCompraPorCampoAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	Long id_compra = Long.parseLong(form.get("id_compra").trim());
	    	  	String campo = form.get("campo").trim();
	    	  	String valor = form.get("valor").trim();
				try {
	    			Connection con = db.getConnection();
	    			if(Compra.modifyXCampo(con, s.baseDato, campo, valor, id_compra)) {
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "compra", id_compra, "update", "modifica "+campo);
	    				con.close();
    	    			return ok("ok");
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
    
    public Result eliminaCompraAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok("error");
	       	}else {
	    	  	Long id_compra = Long.parseLong(form.get("id_compra").trim());
				try {
	    			Connection con = db.getConnection();
	    			if(Compra.delete(con, s.baseDato, id_compra)) {
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "compra", id_compra, "delete", "elimina linea de la compra");
	    				con.close();
    	    			return ok("ok");
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
    // MNU compraListado   Compras/Listar Compras
    //============================================================
    
    public Result listComprasPorCompra(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("compraListado")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			
    			List<Factura> listFacturas = Factura.all(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
    			con.close();
    			return ok(listComprasPorCompra.render(mapeoDiccionario,mapeoPermiso,userMnu,listFacturas));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result listComprasPorCompraExcel(Http.Request request) {
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
	    			if(mapeoPermiso.get("compraListado")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			
	    			List<Factura> listFacturas = Factura.all(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
	       		
	    			File file = Factura.compralistComprasPorCompraExcel(s.baseDato, mapeoDiccionario, listFacturas);
	       		
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
    
    public Result compraFacturaPrint(Long id_factura, Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("compraListado")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Factura factura = Factura.find(con, s.baseDato, id_factura);
    			
    			List<List<String>> detalleFactura = Factura.detalleFactura(con, s.baseDato, factura.getId_proveedor(), id_factura, s.aplicaPorSucursal, s.id_sucursal);
    			con.close();
    			return ok(compraFacturaPrint.render(mapeoDiccionario,mapeoPermiso,userMnu,factura, detalleFactura));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result compraFacturaPrintExcel(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_factura = Long.parseLong(form.get("id_factura").trim());
	       		
	       		try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			Factura factura = Factura.find(con, s.baseDato, id_factura);
	    			
	    			List<List<String>> detalleFactura = Factura.detalleFactura(con, s.baseDato, factura.getId_proveedor(), id_factura, s.aplicaPorSucursal, s.id_sucursal);
	       		
	    			File file = Factura.compraFacturaPrintExcel(s.baseDato, mapeoDiccionario, factura, detalleFactura);
	       		
	    			if(file!=null) {
		       			con.close();
		       			return ok(file,false,Optional.of("Detalle_Compra_"+factura.numero+".xlsx"));
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
    
    public Result listComprasPorEquipo(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("compraListado")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<List<String>> listaPorEquipo = Compra.listaCompraPorEquipo(con, s.baseDato);
    			con.close();
    			return ok(listComprasPorEquipo.render(mapeoDiccionario,mapeoPermiso,userMnu,listaPorEquipo));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result listComprasPorEquipoExcel(Http.Request request) {
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
	    			if(mapeoPermiso.get("compraListado")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			List<List<String>> listaPorEquipo = Compra.listaCompraPorEquipo(con, s.baseDato);
	       		
	    			File file = Factura.compralistComprasPorEquipoExcel(s.baseDato, mapeoDiccionario, listaPorEquipo);
	       		
	    			if(file!=null) {
		       			con.close();
		       			return ok(file,false,Optional.of("ListadoDeComprasPorEquipos.xlsx"));
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
    
    public Result compraEquipoPrint(Long id_equipo, Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("compraListado")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<List<String>> listaCompra = Compra.allPorEquipo(con, s.baseDato, id_equipo);
    			List<List<String>> listaBaja = Baja.allPorEquipo(con, s.baseDato, id_equipo);
    			con.close();
    			return ok(compraEquipoPrint.render(mapeoDiccionario,mapeoPermiso,userMnu,listaCompra,listaBaja));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    
    //============================================================
    // MNU compraElimina   Compras/Eliminar (No confirmadas)
    //============================================================
    
    public Result compraElimina(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("compraElimina")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			
    			List<Factura> listFacturas = Factura.allEliminables(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
    			con.close();
    			return ok(compraElimina.render(mapeoDiccionario,mapeoPermiso,userMnu,listFacturas));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result eliminaCompra(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
    			try {
	    			Connection con = db.getConnection();
	    			Long id_factura = Long.parseLong(form.get("id_factura").trim());
	    			Factura factura = Factura.find(con, s.baseDato, id_factura);
	    			if(s.aplicaPorSucursal.equals("1")) {
	    				if(Compra.deleteAllDetallePorSucursal(con, s.baseDato, id_factura, s.id_sucursal)) {
		    				if(!factura.numOcIConstruye.equals("0")) {
		    					IConstruye.delete(con, s.baseDato, factura.numOcIConstruye);
		    				}
		    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "compra", id_factura, "delete", "elimina compra nro: "+factura.getNumero());
		    				con.close();
			    			return redirect("/compraElimina/");
		    			};
	    				con.close();
		    			return redirect("/compraElimina/");
	    			}else {
	    				if(Compra.deleteAllPorFacturaYfactura(con, s.baseDato, id_factura)) {
		    				if(!factura.numOcIConstruye.equals("0")) {
		    					IConstruye.delete(con, s.baseDato, factura.numOcIConstruye);
		    				}
		    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "compra", id_factura, "delete", "elimina compra nro: "+factura.getNumero());
		    				con.close();
			    			return redirect("/compraElimina/");
		    			};
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
    // MNU compraElimina   Compras/Mov Compras
    //============================================================
    
    public Result movCompras0(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
    		try {
    			Connection con = db.getConnection();
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("compraListado")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			Fechas hoy = Fechas.hoy();
    			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
    			List<Proveedor> listProveedor = Proveedor.all(con, s.baseDato);
    			con.close();
    			return ok(movCompras0.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta, listProveedor));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
    
    public Result movCompras1(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String fechaDesde = form.get("fechaDesde").trim();
	       		String fechaHasta = form.get("fechaHasta").trim();
	       		Long id_proveedor = Long.parseLong(form.get("id_proveedor"));
	       		try {
	    			Connection con = db.getConnection();
	    			String filtroPorProveedor = "";
	    			if(id_proveedor > 0) {
	    				filtroPorProveedor = " and proveedor.id = " + id_proveedor;
	    			}
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			List<List<String>> datos = ReportMovCompras.movComprasPeriodo(con, s.baseDato, fechaDesde, fechaHasta, filtroPorProveedor);
	    			con.close();
	    			return ok(movCompras1.render(mapeoDiccionario,mapeoPermiso,userMnu,datos,fechaDesde,fechaHasta,id_proveedor));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
    
    public Result movCompras1Excel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String fechaDesde = form.get("fechaDesde").trim();
	       		String fechaHasta = form.get("fechaHasta").trim();
	       		Long id_proveedor = Long.parseLong(form.get("id_proveedor"));
	       		try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			String filtroPorProveedor = "";
	    			if(id_proveedor > 0) {
	    				filtroPorProveedor = " and proveedor.id = " + id_proveedor;
	    			}
		    			
	    			List<List<String>> datos = ReportMovCompras.movComprasPeriodo(con, s.baseDato, fechaDesde, fechaHasta, filtroPorProveedor);
	    			File file = ReportMovCompras.movComprasPeriodoExcel(con, s.baseDato, datos, mapeoDiccionario, fechaDesde, fechaHasta);
	    			if(file!=null) {
		       			con.close();
		       			return ok(file,false,Optional.of("MovimientosPorBodegaAgrupado.xlsx"));
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
