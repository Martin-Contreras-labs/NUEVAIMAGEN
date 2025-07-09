package controllers;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import controllers.HomeController.Sessiones;
import models.forms.CotiJsonA;
import models.forms.FormCotiza;
import models.tables.BodegaEmpresa;
import models.tables.Cliente;
import models.tables.Comercial;
import models.tables.CotizaDetalle;
import models.tables.Cotizacion;
import models.tables.Equipo;
import models.tables.Moneda;
import models.tables.Proyecto;
import models.tables.Regiones;
import models.tables.Sucursal;
import models.tables.UnidadTiempo;
import models.utilities.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.Database;
import play.libs.Files.TemporaryFile;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.mensajes;
import viewsMnuCotizarSelect.html.*;

public class MnuCotizarSelect extends Controller {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private static final Database dbWrite = HomeController.dbWrite;
	private static final DatabaseRead dbRead = HomeController.dbRead;
	public static FormFactory formFactory = HomeController.formFactory;
	public static String msgError = HomeController.msgError;
	public static String msgErrorFormulario = HomeController.msgErrorFormulario;
	public static String msgSinPermiso = HomeController.msgSinPermiso;
	private static final String msgReport = HomeController.msgReport;

	static DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
	static DecimalFormat myformatdouble0 = new DecimalFormat("#,##0",symbols);
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00",symbols);
	static DecimalFormat myformatdoubleCompra = new DecimalFormat("#,##0.00",symbols);
	
	
	
	
	//============================================================
    // MNU cotizaIngreso   Cotizar/Cotizar/Hacer Cotizacion
    //============================================================

	
	public Result cotizaIngresoSelect(Http.Request request) {
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
		if(mapeoPermiso.get("cotizaIngreso")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try (Connection con = dbRead.getConnection()){
			List<Equipo> listEquipo = Equipo.allVigentes(con, s.baseDato);
			return ok(cotizaIngresoSelect.render(mapeoDiccionario,mapeoPermiso,userMnu,listEquipo));
		} catch (SQLException e) {
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
	}
	
	public Result cotizaSeleccionados(Http.Request request) {
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
		if(mapeoPermiso.get("cotizaIngreso")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgErrorFormulario));
		}else {
			try (Connection con = dbRead.getConnection()){
				String listSeleccionados = form.get("seleccionados").trim();
				if(listSeleccionados.length()>1) {
					listSeleccionados = listSeleccionados.substring(0,listSeleccionados.length()-1);
				}
				String[] aux = listSeleccionados.split(",");
				Map<String,String> mapSeleccionados = new HashMap<String,String>();
				for(String x: aux) {
					mapSeleccionados.put(x, x);
				}
				Long id_cotizacion = (long) 0;
				Long numeroCoti = Cotizacion.findNuevoNumero(con, s.baseDato);
				String fecha = Fechas.hoy().getFechaStrAAMMDD();
				FormCotiza formCotiza = new FormCotiza();
				formCotiza = new FormCotiza(numeroCoti, fecha);
				List<Cliente> listClientes = Cliente.all(con, s.baseDato);
				List<Proyecto> listProyectos = Proyecto.all(con, s.baseDato);
				List<List<String>> detalleAux = FormCotiza.detalleCotizacion(con, s.baseDato, mapeoPermiso, mapeoDiccionario, (long)0, id_cotizacion, s.id_sucursal, "0", "1",
						null);
				String numDecParaTot = "0";
				if(detalleAux.size()>0) {
					numDecParaTot = detalleAux.get(detalleAux.size()-1).get(20);
				}
				formCotiza.id_cotizacion = id_cotizacion;
				List<Regiones> listRegiones = Regiones.all(con, s.baseDato);
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
				String importDesdeExcel = "0";
				List<List<String>> detalle = new ArrayList<List<String>>();
				for(List<String> d: detalleAux) {
					if(mapSeleccionados.get(d.get(0)) !=null){
						detalle.add(d);
					}
				}
				String[][] array = new String[detalleAux.size()][];
				for (int i = 0; i < detalleAux.size(); i++) {
					List<String> innerList = detalleAux.get(i);
					array[i] = innerList.toArray(new String[0]);
				}
				String json ="";
				ObjectMapper objectMapper = new ObjectMapper();
				JsonNode jsonNode = objectMapper.valueToTree(array);
				try {
					json = objectMapper.writeValueAsString(jsonNode);
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
				return ok(cotizaIngreso3.render(mapeoDiccionario,mapeoPermiso,userMnu,formCotiza,listClientes,listProyectos,detalle,numDecParaTot,listRegiones, listUnTiempo,
								sucursal, comercial, listSucursal,listComercial,importDesdeExcel, json));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
		}
	}
	
	
	public Result cotiValidarPlantillaSel(Http.Request request) {
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
			Http.MultipartFormData<TemporaryFile> body = request.body().asMultipartFormData();
			Http.MultipartFormData.FilePart<TemporaryFile> archivo = body.getFile("archivoXLSX");
			if (archivo != null) {
				try (Connection con = dbRead.getConnection()){
					File file = Archivos.parseMultipartFormDatatoFile(archivo);
					Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
					String id_sucursal = form.get("id_sucursal").trim();
					List<String> mensaje = new ArrayList<String>();
					mensaje = FormCotiza.cotiValidarPlantillaExcel(con, s.baseDato, file);
					if(mensaje.get(0).equals("true")) {
						List<List<String>> listaExcel = FormCotiza.llenaListaDesdeExcel(file);
						Map<String,List<String>> mapListaExcel = new HashMap<String,List<String>>();
						for(List<String> l: listaExcel){
							if(l.get(2).trim().equals("")) {
								l.set(2, "1");
							}
							mapListaExcel.put(l.get(0), l);
						}
						if(mapListaExcel.size() == listaExcel.size()) {
							Long id_cotizacion = (long) 0;
							Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
							Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
							Long numeroCoti = Cotizacion.findNuevoNumero(con, s.baseDato);
							String fecha = Fechas.hoy().getFechaStrAAMMDD();
							FormCotiza formCotiza = new FormCotiza();
							formCotiza = new FormCotiza(numeroCoti, fecha);
							List<List<String>> listaConPrecioAux = FormCotiza.allConSuPrecio(con, s.baseDato, mapeoDiccionario.get("nEmpresa"), id_bodegaEmpresa, mapeoPermiso, id_sucursal, "0","0",(long)-1);
							List<Cliente> listClientes = Cliente.all(con, s.baseDato);
							List<Proyecto> listProyectos = Proyecto.all(con, s.baseDato);
							List<List<String>> listaDetCoti = FormCotiza.detalleCotizacion(con, s.baseDato, mapeoPermiso, mapeoDiccionario, id_bodegaEmpresa, id_cotizacion, s.id_sucursal,"0","0",
									listaConPrecioAux);
							String numDecParaTot = "0";
							if(listaDetCoti.size()>0) {
								numDecParaTot = listaDetCoti.get(listaDetCoti.size()-1).get(20);
							}
							formCotiza.id_cotizacion = id_cotizacion;
							List<Regiones> listRegiones = Regiones.all(con, s.baseDato);
							List<List<String>> listaConPrecio = new ArrayList<List<String>>();
							Map<String,String> mapSeleccionados = new HashMap<String,String>();
							for(int i=0;i<listaConPrecioAux.size();i++){
								List<String> lexcel = mapListaExcel.get(listaConPrecioAux.get(i).get(1).toUpperCase());
								if(lexcel!=null) {
									mapSeleccionados.put(listaConPrecioAux.get(i).get(0), listaConPrecioAux.get(i).get(0));
									listaConPrecio.add(listaConPrecioAux.get(i));
								}
							}
							for(int i=0;i<listaConPrecio.size();i++){
								Long decimal = Long.parseLong(listaConPrecio.get(i).get(20));
								List<String> lexcel = mapListaExcel.get(listaConPrecio.get(i).get(1).toUpperCase());
								if(lexcel!=null) {
									//CANTIDAD
									Double cantDbl = (double) 1;
									String cantStr = "1.00";
									String auxStr = lexcel.get(2).trim();
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
									auxStr = lexcel.get(3).toUpperCase().trim();
									if(auxStr.equals("SI")) {
										esVenta = "1";
									}
									listaConPrecio.get(i).set(11, esVenta);
									//PRECIO VENTA Y TOTAL
									String puVentaStr = listaConPrecio.get(i).get(5);
									Double puVentaDbl = Double.parseDouble(puVentaStr.replaceAll(",", ""));
									auxStr = lexcel.get(4).trim();
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
									auxStr = lexcel.get(6).trim();
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
									auxStr = lexcel.get(7).trim();
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
							String importDesdeExcel = "1";
							List<List<String>> detalleAux = new ArrayList<List<String>>();
							for(List<String> d: listaConPrecioAux) {
								if(mapSeleccionados.get(d.get(0)) ==null){
									detalleAux.add(d);
								}
							}
							String[][] array = new String[detalleAux.size()][];
							for (int i = 0; i < detalleAux.size(); i++) {
								List<String> innerList = detalleAux.get(i);
								array[i] = innerList.toArray(new String[0]);
							}
							String json ="";
							ObjectMapper objectMapper = new ObjectMapper();
							JsonNode jsonNode = objectMapper.valueToTree(array);
							try {
								json = objectMapper.writeValueAsString(jsonNode);
							} catch (JsonProcessingException e) {
								e.printStackTrace();
							}
							return ok(cotizaIngreso3.render(mapeoDiccionario,mapeoPermiso,userMnu,formCotiza,listClientes,listProyectos,
									listaConPrecio,numDecParaTot,listRegiones, listUnTiempo, sucursal, comercial, listSucursal,listComercial,
									importDesdeExcel, json));
						}else {
							return ok(mensajes.render("/routes2/cotizaIngreso3/"+id_bodegaEmpresa, "Existen codigos duplicados en el archivo"));
						}
					}else {
						String msg = "";
						for(String m: mensaje) {
							msg += m + " ";
						}
						return ok(mensajes.render("/routes2/cotizaIngreso3/"+id_bodegaEmpresa, msg));
					}
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
	
	public Result cotizaModificaJsonSel(Http.Request request) {
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
				Long id_cotizacion = Long.parseLong(form.get("id_cotizacion").trim());
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
				List<CotizaDetalle> detalleCoti = CotizaDetalle.allPorIdCotizacion(con, s.baseDato,id_cotizacion);
				Map<String,CotiJsonA> mapListaCoti = new HashMap<String,CotiJsonA>();
				Long id_bodegaEmpresa = (long)0;
				String id_sucursal = "1";
				List<List<String>> listaConPrecioAux = FormCotiza.allConSuPrecio(con, s.baseDato, mapeoDiccionario.get("nEmpresa"), id_bodegaEmpresa, mapeoPermiso, id_sucursal, "0","0",(long)-1);
				List<List<String>> listaConPrecio = new ArrayList<List<String>>();
				Map<String,String> mapSeleccionados = new HashMap<String,String>();
				for(int k=0; k<detalleCoti.size(); k++) {
					String auxCant = detalleCoti.get(k).getCantidad();
					auxCant = auxCant.replaceAll(",", "");
					if(auxCant.trim().equals("")) {
						auxCant = "0";
					}
					Double cant = Double.parseDouble(auxCant.trim());
					if(cant>0) {
						int numeroDecimale = Moneda.numeroDecimalxId(con, s.baseDato, detalleCoti.get(k).getId_moneda().toString());
						CotiJsonA aux = new CotiJsonA();
						aux.id_equipo = detalleCoti.get(k).getId_equipo().toString();
						aux.cantidad = detalleCoti.get(k).getCantidad();
						aux.esVenta = detalleCoti.get(k).getEsVenta().toString();
						aux.puReposicion = detalleCoti.get(k).getPrecioReposicion();
						aux.puArriendo = detalleCoti.get(k).getPrecioArriendo();
						aux.permanencia = detalleCoti.get(k).getPermanencia().toString();
						aux.nroDecimal = ""+numeroDecimale;
						mapListaCoti.put(aux.id_equipo, aux);
					}
				}
				for(int i=0;i<listaConPrecioAux.size();i++){
					CotiJsonA json = mapListaCoti.get(listaConPrecioAux.get(i).get(0));
					if(json!=null) {
						listaConPrecioAux.get(i).set(5, json.puReposicion);
						listaConPrecioAux.get(i).set(7, json.puArriendo);
						listaConPrecioAux.get(i).set(10, json.cantidad);
						listaConPrecioAux.get(i).set(11, json.esVenta);
						listaConPrecioAux.get(i).set(12, json.permanencia);
						listaConPrecioAux.get(i).set(20, json.nroDecimal);
						listaConPrecio.add(listaConPrecioAux.get(i));
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
				Long numeroCoti = Cotizacion.findNuevoNumero(con, s.baseDato);
				String fecha = Fechas.hoy().getFechaStrAAMMDD();
				FormCotiza formCotiza = new FormCotiza();
				formCotiza = new FormCotiza(numeroCoti, fecha);
				List<Cliente> listClientes = Cliente.all(con, s.baseDato);
				List<Proyecto> listProyectos = Proyecto.all(con, s.baseDato);
				List<List<String>> listaDetCoti = FormCotiza.detalleCotizacion(con, s.baseDato, mapeoPermiso, mapeoDiccionario, id_bodegaEmpresa, id_cotizacion, s.id_sucursal,"0","0",
						listaConPrecioAux);
				String numDecParaTot = "0";
				if(listaDetCoti.size()>0) {
					numDecParaTot = listaDetCoti.get(listaDetCoti.size()-1).get(20);
				}
				formCotiza.id_cotizacion = id_cotizacion;
				List<Regiones> listRegiones = Regiones.all(con, s.baseDato);
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
				String importDesdeExcel = "1";
				List<List<String>> detalleAux = new ArrayList<List<String>>();
				for(List<String> d: listaConPrecioAux) {
					if(mapSeleccionados.get(d.get(0)) ==null){
						detalleAux.add(d);
					}
				}
				String[][] array = new String[detalleAux.size()][];
				for (int i = 0; i < detalleAux.size(); i++) {
					List<String> innerList = detalleAux.get(i);
					array[i] = innerList.toArray(new String[0]);
				}
				String json ="";
				ObjectMapper objectMapper = new ObjectMapper();
				JsonNode jsonNode = objectMapper.valueToTree(array);
				try {
					json = objectMapper.writeValueAsString(jsonNode);
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
				return ok(cotizaIngreso3.render(mapeoDiccionario,mapeoPermiso,userMnu,formCotiza,listClientes,listProyectos,
						listaConPrecio,numDecParaTot,listRegiones, listUnTiempo, sucursal, comercial, listSucursal,listComercial,
						importDesdeExcel, json));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
    	}
    	
	}
	
    public Result actualizaListaCotiSelAjax(Http.Request request) {
		Sessiones s = new Sessiones(request);
		String className = this.getClass().getSimpleName();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (!s.isValid()) {
			// logger.error("SESSION INVALIDA. [CLASS: {}. METHOD: {}.]", className, methodName);
			return ok("error");
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok("error");
		}else {
			try (Connection con = dbRead.getConnection()){
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
									"</TR>"+
								"</thead>"+
								"<tbody>";
							for(List<String> x: listCotizacion) {
								vistaHtml += "<TR onClick=\"seleccionaCotizacion('"+x.get(0)+"')\" data-dismiss=\"modal\">"+
										"<td  style=\"text-align:center;\">"+x.get(1)+"</td>"+
										"<td  style=\"text-align:center;\">"+x.get(2)+"</td>"+
										"<td  style=\"text-align:center;\">"+
											"<div style=\"display:none\">"+Fechas.AAMMDD(x.get(3))+"</div>"+
											Fechas.DDMMAA(x.get(3))+
										"</td>"+
										"<td  style=\"text-align:left;\">"+x.get(4)+"</td>"+
										"<td  style=\"text-align:left;\">"+x.get(5)+"</td>"+
									"</TR>";
							}
							vistaHtml += "</tbody>"+
									"</table>";
				return ok(vistaHtml);
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
    // MNU cotizaModifica   Cotizar/Cotizar/Modificar Cotizacion
    //============================================================
    
    public Result cotizaListaModificaSelPeriodo(Http.Request request) {
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
		if(mapeoPermiso.get("cotizaModifica")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		try {
			Fechas hoy = Fechas.hoy();
			String hasta = Fechas.obtenerFinMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			hoy = Fechas.addMeses(hoy.getFechaCal(),-1);
			String desde = Fechas.obtenerInicioMes(hoy.getFechaCal()).getFechaStrAAMMDD();
			return ok(cotizaListaModificaSelPeriodo.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta));
		} catch (Exception e) {
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
			return ok(mensajes.render("/home/", msgReport));
		}
    }
    
    public Result cotizaListaModificaSel(Http.Request request) {
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
		if(mapeoPermiso.get("cotizaModifica")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgErrorFormulario));
		}else {
			try (Connection con = dbRead.getConnection()){
				String desdeAAMMDD = form.get("fechaDesde").trim();
				String hastaAAMMDD = form.get("fechaHasta").trim();
				List<List<String>> listCotizacion = Cotizacion.listCotiAllSinConfirmar(con, s.baseDato, s.aplicaPorSucursal, s.id_sucursal, desdeAAMMDD, hastaAAMMDD);
				return ok(cotizaListaModificaSel.render(mapeoDiccionario,mapeoPermiso,userMnu,listCotizacion));
			} catch (SQLException e) {
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			} catch (Exception e) {
				logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName, e);
				return ok(mensajes.render("/home/", msgReport));
			}
    	}
    }
    
    
    public Result cotizaModificaSel(Http.Request request) {
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
		if(mapeoPermiso.get("cotizaModifica")==null) {
			logger.error("PERMISO DENEGADO. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/",msgSinPermiso));
		}
		DynamicForm form = formFactory.form().bindFromRequest(request);
		form.get("dummy");
		if (form.hasErrors()) {
			logger.error("FORM ERROR. [CLASS: {}. METHOD: {}. DB: {}. USER: {}.]", className, methodName, s.baseDato, s.userName);
			return ok(mensajes.render("/home/", msgErrorFormulario));
		}else {
			try (Connection con = dbRead.getConnection()){
				Long id_cotizacion = Long.parseLong(form.get("id_cotizacion").trim());
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
				List<List<String>> listaAux = FormCotiza.detalleCotizacion(con, s.baseDato, mapeoPermiso, mapeoDiccionario, id_bodegaEmpresa, id_cotizacion, s.id_sucursal, "1", "0", null);
				String numDecParaTot = "0";
				List<List<String>> lista = new ArrayList<List<String>>();
				for(List<String> l: listaAux){
					Double aux = Double.parseDouble(l.get(10).replaceAll(",", ""));
					if(aux > 0) {
						lista.add(l);
					}
				}
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
				Comercial comercial = new Comercial();
				Comercial auxComercial = Comercial.findPorIdUsuario(con, s.baseDato, cotizacion.getId_comercial().toString());
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
				Sucursal sucursal = Sucursal.find(con, s.baseDato, cotizacion.getId_sucursal().toString());
				String[][] array = new String[listaAux.size()][];
				for (int i = 0; i < listaAux.size(); i++) {
					List<String> innerList = listaAux.get(i);
					array[i] = innerList.toArray(new String[0]);
				}
				String json ="";
				ObjectMapper objectMapper = new ObjectMapper();
				JsonNode jsonNode = objectMapper.valueToTree(array);
				try {
					json = objectMapper.writeValueAsString(jsonNode);
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
				return ok(cotizaModificaSel.render(mapeoDiccionario,mapeoPermiso,userMnu,formCotiza,listClientes,listProyectos,lista, numDecParaTot, listRegiones, listUnTiempo,
						sucursal, comercial, listSucursal, listComercial, json));
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






