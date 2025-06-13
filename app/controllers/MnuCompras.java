package controllers;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
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
import models.utilities.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.Database;
import play.libs.Json;
import play.libs.Files.TemporaryFile;
import play.libs.mailer.MailerClient;
import play.libs.ws.WSClient;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.mensajes;
import viewsMnuCompras.html.*;



public class MnuCompras extends Controller {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private static final Database dbWrite = HomeController.dbWrite;
	private static final DatabaseRead dbRead = HomeController.dbRead;
	public static FormFactory formFactory = HomeController.formFactory;
	public static String msgError = HomeController.msgError;
	public static String msgErrorFormulario = HomeController.msgErrorFormulario;
	public static String msgSinPermiso = HomeController.msgSinPermiso;
	private static final String msgReport = HomeController.msgReport;
	
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
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("compraIngreso")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			Fechas hoy = Fechas.hoy();
			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			EmisorTributario emisor = EmisorTributario.find(con, s.baseDato);
			String token = ApiIConstruyeOC.obtieneToken(emisor, ws);
			List<List<String>> listIdOrgc = ApiIConstruyeOC.obtieneCtrosGestion(emisor, ws, token, mapeoDiccionario.get("nEmpresa"));
			return ok(compraImportIconstruye0.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta, listIdOrgc));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
	public Result compraImportIconstruye1(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
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
				String idOrgc = form.get("idOrgc").trim();
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
				return ok(compraImportIconstruye1.render(mapeoDiccionario,mapeoPermiso,userMnu,listaOC));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
    	}
    }
	
	public Result compraImportIconstruye2(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgErrorFormulario));
		}else {
			try (Connection con = dbWrite.getConnection()){
				String idDocumento = form.get("idDocumento").trim();
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
						String nomUnidad = detalle.get(9);
						if(nomUnidad!=null) {
							nomUnidad = nomUnidad.toUpperCase().trim();
						}
						unidad = mapUnidad.get(nomUnidad);
						if(unidad == null) {
							unidad = Unidad.create(con, s.baseDato, nomUnidad);
						}
						String nickMoneda = detalle.get(11);
						if(nickMoneda!=null) {
							nickMoneda = nickMoneda.trim().toUpperCase();
						}
						moneda = mapMoneda.get(nickMoneda);
						if(moneda == null) {
							EquivalenciasMonedas equivalenciasMonedas = mapEquivalenciasMonedas.get(nickMoneda);
							if(equivalenciasMonedas == null) {
								Moneda aux = Moneda.find(con, s.baseDato, (long)1);
								nickMoneda = aux.getNickName();
							}else {
								nickMoneda = equivalenciasMonedas.equivEnMada;
							}
							if(nickMoneda!=null) {
								nickMoneda = nickMoneda.trim().toUpperCase();
							}
							moneda = mapMoneda.get(nickMoneda);
						}
						String codEquipo = detalle.get(6);
						if(codEquipo!=null) {
							codEquipo = codEquipo.toUpperCase().trim().toUpperCase();
						}
						equipo = mapEquipo.get(codEquipo);
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
							aux.desdeMenu = "ICONSTRUYE";
							aux.kg = (double)0;
							aux.m2 = (double)0;
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
					return ok(compraImportIconstruye2.render(mapeoDiccionario,mapeoPermiso,userMnu,proveedor,detalleOC,
							listEquipo,listMoneda,listBodegas,listMon,listGrupos,listFabrica,listUnidades,numeroDecimales));
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
    // MNU compraIngreso   Compras/Ingresar Compras
    //============================================================

    public Result compraIngreso(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("compraIngreso")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
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
			return ok(compraIngreso.render(mapeoDiccionario,mapeoPermiso,userMnu,listProveedor,listEquipo,listMoneda,listBodegas,listMon,listGrupos,listFabrica,listUnidades,
					listRegiones, listSucursales));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result verificaNumeroFacturaAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok("error");
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok("error");
		}else {
			try (Connection con = dbRead.getConnection()){
				Long numeroFactura = Long.parseLong(form.get("numeroFactura").trim());
				Long id_proveedor = Long.parseLong(form.get("id_proveedor").trim());
				return ok(Factura.existeNumero(con, s.baseDato, numeroFactura, id_proveedor) ? "existe" : "");
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("error");
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("error");
			}
    	}
	}
    
    public Result compraNuevo(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		FormCompra form = formFactory.form(FormCompra.class).withDirectFieldAccess(true).bindFromRequest(request).get();
		if (form.numeroFactura==null || form.id_factura==null) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgErrorFormulario));
		}else {
			Http.MultipartFormData<TemporaryFile> body = request.body().asMultipartFormData();
			Http.MultipartFormData.FilePart<TemporaryFile> docAdjunto = body.getFile("docAdjunto");
			try (Connection con = dbWrite.getConnection()){
				if(Factura.existeNumero(con, s.baseDato, form.numeroFactura, form.id_proveedor)) {
					String msg = "El numero de factura para este proveedor ya existe, debe volver a ingresar la compra";
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
				return redirect("/home/");
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
    	}
    }
    
    public Result modalPreciosAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok("error");
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok("error");
		}else {
			try (Connection con = dbWrite.getConnection()){
				Long id_sucursal = Long.parseLong(form.get("id_sucursal").trim());
				Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				Equipo equipo = Equipo.find(con, s.baseDato, id_equipo);
				Precio precio = Precio.findXIdEquipo(con, s.baseDato, id_sucursal, id_equipo, mapeoDiccionario);
				if(precio==null){
					Fechas fecha = Fechas.hoy();
					if(Precio.create(con, s.baseDato, id_equipo, fecha.getFechaStrAAMMDD())) {
						precio = Precio.findXIdEquipo(con, s.baseDato, id_sucursal, id_equipo, mapeoDiccionario);
					}else {
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
				return ok(vista);
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("error");
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("error");
			}
    	}
	}
    
    public Result compraVistaSelBodDestAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok("error");
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok("error");
		}else {
			try (Connection con = dbRead.getConnection()){
				String id_sucursal = form.get("id_sucursal").trim();
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
				return ok("{\"vista\":\""+vista+"\",\"nameSucursal\":\""+sucursal.nombre+"\"}").as("application/json");
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("error");
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("error");
			}
    	}
	}
   
    public Result  compraVistaSelBodDestAjax2(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok("error");
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok("error");
		}else {
			try (Connection con = dbRead.getConnection()){
				Long id_bodega = Long.parseLong(form.get("id_bodega").trim());
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
				return ok(vista);
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("error");
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("error");
			}
    	}
	}
    
    
    public Result nuevoEquipoAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok("error");
		}
		FormEquipoGraba form = formFactory.form(FormEquipoGraba.class).withDirectFieldAccess(true).bindFromRequest(request).get();
		if (form.codigo==null) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok("error");
		}else {
			try (Connection con = dbWrite.getConnection()){
				if(Equipo.create(con, s.baseDato, form)) {
					Equipo equipo = Equipo.findXCodigo(con, s.baseDato, form.codigo);
					if(form.desdeMenu == null) {
						form.desdeMenu = "no tiene datos";
					}
					if(form.desdeMenu.equals("COMPRA")) {
						Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "equipo", (long)0, "create", "crea desde COMPRA nuevo equipo codigo: "+form.codigo);
					}else if(form.desdeMenu.equals("REDIMENSIONAR")){
						Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "equipo", (long)0, "create", "crea desde REDIMENSIONAR nuevo equipo codigo: "+form.codigo);
					}else if(form.desdeMenu.equals("COMPRA_ICONSTRUYE")){
						Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "equipo", (long)0, "create", "crea desde COMPRA_ICONSTRUYE nuevo equipo codigo: "+form.codigo);
					}else if(form.desdeMenu.equals("PLANES_ICONSTRUYE")){
						Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "equipo", (long)0, "create", "crea desde PLANES_ICONSTRUYE nuevo equipo codigo: "+form.codigo);
					}else{
						Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "equipo", (long)0, "create", "crea nuevo equipo codigo: "+form.codigo);
					}
					return ok(equipo.getId()+"_"+equipo.getKg()+"_"+equipo.getM2());
				}else {
					return ok("existe");
				}
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("error");
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("error");
			}
    	}
	}
    
    public Result compraPlantilla(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgErrorFormulario));
		}else {
			try (Connection con = dbRead.getConnection()) {
				File file = Compra.plantillaCompras(con, s.baseDato);
				return ok(file, Optional.of("plantillaCargaCompras.xlsx"));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}
    
    public Result compraValidarPlantilla(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgErrorFormulario));
		}else {
			List<String> mensaje = new ArrayList<String>();
			Archivos aux = formFactory.form(Archivos.class).withDirectFieldAccess(true).bindFromRequest(request).get();
			Http.MultipartFormData.FilePart<TemporaryFile> archivo = aux.file;
			if (archivo != null) {
				File file = Archivos.parseMultipartFormDatatoFile(archivo);
				try (Connection con = dbRead.getConnection()){
					mensaje = FormCompra.compraValidarPlantillaExcel(con, s.baseDato, file);
					String jsonMsg = Json.toJson(mensaje).toString();
					return ok(jsonMsg.toString()).as("application/json");
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				}
			}
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result compraCargaPlantilla(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgErrorFormulario));
		}else {
			Http.MultipartFormData<TemporaryFile> body = request.body().asMultipartFormData();
			Http.MultipartFormData.FilePart<TemporaryFile> archivo = body.getFile("archivoXLSX");
			if (archivo != null) {
				File file = Archivos.parseMultipartFormDatatoFile(archivo);
				try (Connection con = dbWrite.getConnection()) {
					Long id_proveedor = FormCompra.validaProveedor(con, s.baseDato, file);
					Proveedor proveedor = new Proveedor();
					if (id_proveedor == null) {
						return ok(mensajes.render("/compraIngreso/", "Falta o no existe el proveedor"));
					} else {
						proveedor = Proveedor.find(con, s.baseDato, id_proveedor);
					}
					Map<String, BodegaEmpresa> mapBodega = BodegaEmpresa.mapAllNombre(con, s.baseDato);
					Map<String, List<String>> mapListaExcel = new HashMap<String, List<String>>();
					List<List<String>> listaExcel = FormCompra.llenaListaDesdeExcel(file);
					for (List<String> l : listaExcel) {
						BodegaEmpresa bodega = mapBodega.get(l.get(9).toUpperCase());
						if (bodega == null) {
							if (l.get(9).length() < 1) {
								return ok(mensajes.render("/compraIngreso/", "Faltan bodegas destino"));
							}
							return ok(mensajes.render("/compraIngreso/", "No existe la bodega destino: " + l.get(9).toUpperCase()));
						}
						mapListaExcel.put(l.get(1), l);
					}
					if (mapListaExcel.size() != listaExcel.size()) {
						return ok(mensajes.render("/compraIngreso/", "Existen codigos duplicados en el archivo"));
					}

					Map<String, Equipo> mapEquipos = Equipo.mapAllAllPorCodigo(con, s.baseDato);
					String newEquipos = "";
					List<String> codigos = new ArrayList<String>();
					Map<String, Grupo> mapGrupo = Grupo.mapAllPorNombre(con, s.baseDato);
					Map<String, Unidad> mapUnidad = Unidad.mapPorNombre(con, s.baseDato);
					String selEquipos = "";
					for (List<String> l : listaExcel) {
						String codEquipo = l.get(1);
						if (codEquipo != null) {
							codEquipo = codEquipo.trim().toUpperCase();
						}
						Equipo equipo = mapEquipos.get(codEquipo);
						if (equipo == null) {
							String KG = l.get(3);
							String M2 = l.get(4);
							Double cantKG = (double) 0;
							Double cantM2 = (double) 0;
							try {
								cantKG = Double.parseDouble(KG);
							} catch (Exception e) {
							}
							try {
								cantM2 = Double.parseDouble(M2);
							} catch (Exception e) {
							}
							Long id_grupo = (long) 0;
							Grupo grupo = mapGrupo.get(l.get(0));
							if (grupo != null) {
								id_grupo = grupo.getId();
							}
							Long id_unidad = (long) 1;
							String nomUnidad = l.get(5);
							if (nomUnidad != null) {
								nomUnidad = nomUnidad.toUpperCase().trim();
							}
							Unidad unidad = mapUnidad.get(nomUnidad);
							if (unidad != null) {
								id_unidad = unidad.getId();
							}
							newEquipos += "('" + l.get(1) + "','" + l.get(2) + "','" + id_unidad + "','" + id_grupo + "','" + cantKG + "','" + cantM2 + "'),";
							codigos.add(l.get(1));
							selEquipos += "'" + l.get(1) + "',";
						}
					}
					if (newEquipos.length() > 1) {
						newEquipos = newEquipos.substring(0, newEquipos.length() - 1);
						PreparedStatement smt = con
								.prepareStatement("insert into  `" + s.baseDato + "`.equipo (codigo, nombre, id_unidad, id_grupo, kg, m2) VALUES " + newEquipos + ";");
						smt.executeUpdate();
						smt.close();
						if (selEquipos.length() > 1) {
							selEquipos = "(" + selEquipos.substring(0, selEquipos.length() - 1) + ")";
						}
						Map<String, Long> mapCodVsId = new HashMap<String, Long>();
						PreparedStatement smt3 = con
								.prepareStatement("select codigo, id from `" + s.baseDato + "`.equipo where codigo in " + selEquipos + ";");
						ResultSet rs3 = smt3.executeQuery();
						while (rs3.next()) {
							mapCodVsId.put(rs3.getString(1), rs3.getLong(2));
						}
						rs3.close();
						smt3.close();
						String datos = "";
						List<Sucursal> listSucursal = Sucursal.all(con, s.baseDato);
						for (Map.Entry<String, Long> entry : mapCodVsId.entrySet()) {
							Long valor = entry.getValue();
							for (Sucursal suc : listSucursal) {
								datos += "('" + valor + "','" + Fechas.hoy().getFechaStrAAMMDD() + "','" + suc.getId() + "'),";
							}
						}
						if (datos.length() > 1) {
							datos = datos.substring(0, datos.length() - 1);
							PreparedStatement smt4 = con
									.prepareStatement("insert into `" + s.baseDato + "`.precio "
											+ " (id_equipo,fecha,id_sucursal) "
											+ " values " + datos + ";");
							smt4.executeUpdate();
							smt4.close();
						}
					}
					Map<String, String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
					Map<String, String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
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
					mapEquipos = Equipo.mapAllAllPorCodigo(con, s.baseDato);
					Map<String, Moneda> mapMoneda = Moneda.mapNickMonedas(con, s.baseDato);
					List<List<String>> lista = new ArrayList<List<String>>();
					for (List<String> l : listaExcel) {
						String codEquipo = l.get(1);
						if (codEquipo != null) {
							codEquipo = codEquipo.trim().toUpperCase();
						}
						Equipo equipo = mapEquipos.get(codEquipo);
						BodegaEmpresa bodega = mapBodega.get(l.get(9).toUpperCase());
						if (equipo != null && bodega != null) {
							List<String> aux = new ArrayList<String>();
							String cant = l.get(6);
							if (cant.trim().length() == 0) {
								cant = "0.00";
							} else {
								try {
									cant = DecimalFormato.formato(Double.parseDouble(cant), (long) 2);
								} catch (Exception e) {
									cant = "0.00";
								}
							}
							String nickMoneda = l.get(7);
							if (nickMoneda != null) {
								nickMoneda = nickMoneda.trim().toUpperCase();
							}
							Moneda mon = mapMoneda.get(nickMoneda);
							if (mon == null) {
								mon = Moneda.find(con, s.baseDato, (long) 1);
							}
							String pu = l.get(8);
							if (pu.trim().length() == 0) {
								pu = DecimalFormato.formato((double) 0, mon.numeroDecimales);
							} else {
								try {
									pu = DecimalFormato.formato(Double.parseDouble(pu), (long) 2);
								} catch (Exception e) {
									pu = DecimalFormato.formato((double) 0, mon.numeroDecimales);
								}
							}
							/*0*/
							aux.add(equipo.getGrupo());
							/*1*/
							aux.add(equipo.getCodigo());
							/*2*/
							aux.add(equipo.getNombre());
							/*3*/
							aux.add(DecimalFormato.formato(equipo.getKg(), (long) 2));
							/*4*/
							aux.add(DecimalFormato.formato(equipo.getM2(), (long) 2));
							/*5*/
							aux.add(equipo.getUnidad());
							/*6*/
							aux.add(cant);
							/*7*/
							aux.add(mon.getNickName());
							/*8*/
							aux.add(pu);
							/*9*/
							aux.add(bodega.getNombre());
							/*10*/
							aux.add(equipo.getId().toString());
							/*11*/
							aux.add(mon.getId().toString());
							/*12*/
							aux.add(mon.getNumeroDecimales().toString());
							/*13*/
							aux.add(bodega.getId_sucursal().toString());
							/*14*/
							aux.add(bodega.getNameSucursal());
							/*15*/
							aux.add(bodega.getId().toString());
							lista.add(aux);
						}
					}
					return ok(compraIngreso2.render(mapeoDiccionario, mapeoPermiso, userMnu, listProveedor, listEquipo, listMoneda, listBodegas, listMon, listGrupos, listFabrica, listUnidades,
							listRegiones, listSucursales, lista, proveedor));
				} catch (SQLException e) {
					logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				} catch (Exception e) {
					logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
					return ok(mensajes.render("/home/", msgReport));
				}
			}
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgReport));
    	}
	}

    //============================================================
    // MNU compraConfirma   Compras/Confirmar Compras
    //============================================================
    
    public Result compraConfirma(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("compraConfirma")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<List<String>> listaAconfirmar = Compra.listaConfirmaIngreso(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal, mapeoDiccionario);
			return ok(compraConfirma.render(mapeoDiccionario,mapeoPermiso,userMnu,listaAconfirmar));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result compraConfirmaIngreso(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgErrorFormulario));
		}else {
			try (Connection con = dbWrite.getConnection()){
				String arrIdCompras = form.get("cambiosDeEstados").trim();
				if(arrIdCompras.length()>0) {
					String[] arr = arrIdCompras.split(";");
					String insertMovimiento = "";
					for(int i=0; i<arr.length; i++) {
						String[] detalle = arr[i].split(",");
						String id_bodegaEmpresa = detalle[4];
						String id_equipo = detalle[2];
						String id_tipoMovimiento = "1";
						String cantidad = detalle[3];
						String id_compra = detalle[0];
						String id_factura = detalle[5];
						String fecha_factura = detalle[6];
						insertMovimiento += "('"
								+ id_bodegaEmpresa + "','"
								+ id_equipo + "','"
								+ id_tipoMovimiento + "','"
								+ cantidad + "','"
								+ id_compra + "','"
								+ id_factura + "','"
								+ fecha_factura + "'),";
					}
					if(insertMovimiento.length() > 2) {
						insertMovimiento = insertMovimiento.substring(0,insertMovimiento.length()-1);
						if(Movimiento.createMovimientoCompra(con, s.baseDato, insertMovimiento)) {
							String listIdCompra = "";
							for(int i=0; i<arr.length; i++) {
								String[] detalle = arr[i].split(",");
								String id_compra = detalle[0];
								listIdCompra += id_compra + ",";
								Compra.actualizaPorCampo(con, s.baseDato, "esModificable", Long.parseLong(id_compra), "0");
							}
							Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "compra", (long)0, "confirma", "confirma compras id: "+listIdCompra);
						}
					}
				}
				return redirect("/home/");
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
    // MNU compraModifica   Compras/Modificar Compras
    //============================================================
    
    public Result compraListaModifica(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("compraModifica")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<Factura> listFacturas = Factura.all(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
			return ok(compraListaModifica.render(mapeoDiccionario,mapeoPermiso,userMnu,listFacturas));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result compraModifica(Long id_factura, Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("compraModifica")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
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
			return ok(compraModifica.render(mapeoDiccionario,mapeoPermiso,userMnu,listProveedor,listEquipo,listMoneda,listBodegas,listMon,listGrupos,listFabrica,listUnidades,
					factura, detalleFactura, listSucursales));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result compraModificaGraba(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		FormCompra form = formFactory.form(FormCompra.class).withDirectFieldAccess(true).bindFromRequest(request).get();
		if (form.numeroFactura==null || form.id_factura==null) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgErrorFormulario));
		}else {
			Http.MultipartFormData<TemporaryFile> body = request.body().asMultipartFormData();
			Http.MultipartFormData.FilePart<TemporaryFile> docAdjunto = body.getFile("docAdjunto");
			try (Connection con = dbWrite.getConnection()){
				if(FormCompra.update(con, s.baseDato, form, docAdjunto)) {
					Factura factura = Factura.find(con, s.baseDato, form.id_factura);
					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "compra", form.id_factura, "update", "agrega lineas a la compra nro: "+factura.getNumero());
				}
				return redirect("/home/");
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
    	}
    }
    
    public Result modificaFacturaPorCampoAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok("error");
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok("error");
		}else {
			try (Connection con = dbWrite.getConnection()){
				Long id_factura = Long.parseLong(form.get("id_factura").trim());
				String campo = form.get("campo").trim();
				String valor = form.get("valor").trim();
				if(Factura.modifyXCampo(con, s.baseDato, campo, valor, id_factura)) {
					Factura factura = Factura.find(con, s.baseDato, id_factura);
					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "factura", id_factura, "update", "modifica "+campo+" de nro: "+factura.getNumero());
					return ok("ok");
				}else {
					return ok("error");
				}
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("error");
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("error");
			}
    	}
	}
    
    public Result modificaCompraPorCampoAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok("error");
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok("error");
		}else {
			try (Connection con = dbWrite.getConnection()){
				Long id_compra = Long.parseLong(form.get("id_compra").trim());
				String campo = form.get("campo").trim();
				String valor = form.get("valor").trim();
				if(Compra.modifyXCampo(con, s.baseDato, campo, valor, id_compra)) {
					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "compra", id_compra, "update", "modifica "+campo);
					return ok("ok");
				}else {
					return ok("error");
				}
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("error");
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("error");
			}
    	}
	}
    
    public Result eliminaCompraAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok("error");
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok("error");
		}else {
			try (Connection con = dbWrite.getConnection()){
				Long id_compra = Long.parseLong(form.get("id_compra").trim());
				if(Compra.delete(con, s.baseDato, id_compra)) {
					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "compra", id_compra, "delete", "elimina linea de la compra");
					return ok("ok");
				}else {
					return ok("error");
				}
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("error");
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok("error");
			}
    	}
	}
    
    //============================================================
    // MNU compraListado   Compras/Listar Compras
    //============================================================
    
    public Result listComprasPorCompra(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("compraListado")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<Factura> listFacturas = Factura.all(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
			return ok(listComprasPorCompra.render(mapeoDiccionario,mapeoPermiso,userMnu,listFacturas));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result listComprasPorCompraExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgErrorFormulario));
		}else {
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
			if(mapeoPermiso.get("compraListado")==null) {
				logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
				return ok(mensajes.render("/",msgSinPermiso));
			}
			try (Connection con = dbRead.getConnection()){
				List<Factura> listFacturas = Factura.all(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
				File file = Factura.compralistComprasPorCompraExcel(s.baseDato, mapeoDiccionario, listFacturas);
				return ok(file,false,Optional.of("ListadoDeComprasPorCompra.xlsx"));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
    	}
    }
    
    public Result compraFacturaPrint(Long id_factura, Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("compraListado")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			Factura factura = Factura.find(con, s.baseDato, id_factura);
			List<List<String>> detalleFactura = Factura.detalleFactura(con, s.baseDato, factura.getId_proveedor(), id_factura, s.aplicaPorSucursal, s.id_sucursal);
			return ok(compraFacturaPrint.render(mapeoDiccionario,mapeoPermiso,userMnu,factura, detalleFactura));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result compraFacturaPrintExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgErrorFormulario));
		}else {
			try (Connection con = dbRead.getConnection()){
				Long id_factura = Long.parseLong(form.get("id_factura").trim());
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				Factura factura = Factura.find(con, s.baseDato, id_factura);
				List<List<String>> detalleFactura = Factura.detalleFactura(con, s.baseDato, factura.getId_proveedor(), id_factura, s.aplicaPorSucursal, s.id_sucursal);
				File file = Factura.compraFacturaPrintExcel(s.baseDato, mapeoDiccionario, factura, detalleFactura);
				return ok(file,false,Optional.of("Detalle_Compra_"+factura.numero+".xlsx"));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
    	}
    }
    
    public Result listComprasPorEquipo(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("compraListado")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<List<String>> listaPorEquipo = Compra.listaCompraPorEquipo(con, s.baseDato);
			return ok(listComprasPorEquipo.render(mapeoDiccionario,mapeoPermiso,userMnu,listaPorEquipo));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result listComprasPorEquipoExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgErrorFormulario));
		}else {
			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
			if(mapeoPermiso.get("compraListado")==null) {
				logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
				return ok(mensajes.render("/",msgSinPermiso));
			}
			try (Connection con = dbRead.getConnection()){
				List<List<String>> listaPorEquipo = Compra.listaCompraPorEquipo(con, s.baseDato);
				File file = Factura.compralistComprasPorEquipoExcel(s.baseDato, mapeoDiccionario, listaPorEquipo);
				return ok(file,false,Optional.of("ListadoDeComprasPorEquipos.xlsx"));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
    	}
    }
    
    public Result compraEquipoPrint(Long id_equipo, Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("compraListado")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<List<String>> listaCompra = Compra.allPorEquipo(con, s.baseDato, id_equipo);
			List<List<String>> listaBaja = Baja.allPorEquipo(con, s.baseDato, id_equipo);
			return ok(compraEquipoPrint.render(mapeoDiccionario,mapeoPermiso,userMnu,listaCompra,listaBaja));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    
    //============================================================
    // MNU compraElimina   Compras/Eliminar (No confirmadas)
    //============================================================
    
    public Result compraElimina(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("compraElimina")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<Factura> listFacturas = Factura.allEliminables(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal);
			return ok(compraElimina.render(mapeoDiccionario,mapeoPermiso,userMnu,listFacturas));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result eliminaCompra(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgErrorFormulario));
		}else {
			try (Connection con = dbWrite.getConnection()){
				Long id_factura = Long.parseLong(form.get("id_factura").trim());
				Factura factura = Factura.find(con, s.baseDato, id_factura);
				if(s.aplicaPorSucursal.equals("1")) {
					if(Compra.deleteAllDetallePorSucursal(con, s.baseDato, id_factura, s.id_sucursal)) {
						if(!factura.numOcIConstruye.equals("0")) {
							IConstruye.delete(con, s.baseDato, factura.numOcIConstruye);
						}
						Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "compra", id_factura, "delete", "elimina compra nro: "+factura.getNumero());
						return redirect("/compraElimina/");
					}
					return redirect("/compraElimina/");
				}else {
					if(Compra.deleteAllPorFacturaYfactura(con, s.baseDato, id_factura)) {
						if(!factura.numOcIConstruye.equals("0")) {
							IConstruye.delete(con, s.baseDato, factura.numOcIConstruye);
						}
						Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "compra", id_factura, "delete", "elimina compra nro: "+factura.getNumero());
						return redirect("/compraElimina/");
					}
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
    
    
    //============================================================
    // MNU compraElimina   Compras/Mov Compras
    //============================================================
    
    public Result movCompras0(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal);
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
		Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
		if(mapeoPermiso.get("compraListado")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			Fechas hoy = Fechas.hoy();
			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			List<Proveedor> listProveedor = Proveedor.all(con, s.baseDato);
			return ok(movCompras0.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta, listProveedor));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result movCompras1(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
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
				String fechaDesde = form.get("fechaDesde").trim();
				String fechaHasta = form.get("fechaHasta").trim();
				Long id_proveedor = Long.parseLong(form.get("id_proveedor"));
				String filtroPorProveedor = "";
				if(id_proveedor > 0) {
					filtroPorProveedor = " and proveedor.id = " + id_proveedor;
				}
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				List<List<String>> datos = ReportMovCompras.movComprasPeriodo(con, s.baseDato, fechaDesde, fechaHasta, filtroPorProveedor);
				return ok(movCompras1.render(mapeoDiccionario,mapeoPermiso,userMnu,datos,fechaDesde,fechaHasta,id_proveedor));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
    	}
	}
    
    public Result movCompras1Excel(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok(mensajes.render("/", msgError));
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgErrorFormulario));
		}else {
			try (Connection con = dbRead.getConnection()){
				String fechaDesde = form.get("fechaDesde").trim();
				String fechaHasta = form.get("fechaHasta").trim();
				Long id_proveedor = Long.parseLong(form.get("id_proveedor"));
				List<List<String>> datos = null;
				Map<String,String> mapeoDiccionario = null;
				mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				String filtroPorProveedor = "";
				if(id_proveedor > 0) {
					filtroPorProveedor = " and proveedor.id = " + id_proveedor;
				}
				datos = ReportMovCompras.movComprasPeriodo(con, s.baseDato, fechaDesde, fechaHasta, filtroPorProveedor);
				File file = ReportMovCompras.movComprasPeriodoExcel(s.baseDato, datos, mapeoDiccionario, fechaDesde, fechaHasta);
				return ok(file,false,Optional.of("MovimientosPorBodegaAgrupado.xlsx"));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}

		}
	}



}
