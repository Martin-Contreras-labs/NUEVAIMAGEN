package controllers;

import java.io.File;
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
import models.api.ApiIConstruyeOC;
import models.reports.ReportHojaVida;
import models.tables.BodegaEmpresa;
import models.tables.EmisorTributario;
import models.tables.Equipo;
import models.tables.EquivalenciasMonedas;
import models.tables.HojaVida;
import models.tables.IConstruye;
import models.tables.IConstruyeProdDet;
import models.tables.Moneda;
import models.tables.PlanMantencion;
import models.tables.Proveedor;
import models.tables.TipoMantencion;
import models.tables.TipoPlan;
import models.tables.TipoTrabajo;
import models.tables.Unidad;
import models.tables.UnidadMantencion;
import models.utilities.Archivos;
import models.utilities.Fechas;
import models.utilities.Registro;
import models.utilities.UserMnu;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.Database;
import play.libs.Files.TemporaryFile;
import play.libs.mailer.Email;
import play.libs.mailer.MailerClient;
import play.libs.ws.WSClient;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.mensajes;
import viewsMnuPlanes.html.*;

public class MnuPlanes extends Controller {
	public static Database db = HomeController.dbWrite;
	public static FormFactory formFactory = HomeController.formFactory;
	public static String msgError = HomeController.msgError;
	public static String msgErrorFormulario = HomeController.msgErrorFormulario;
	public static String msgSinPermiso = HomeController.msgSinPermiso;
	
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	
	
	private final WSClient ws;
	public final MailerClient mailerClient;
	
	@Inject
	  public MnuPlanes(WSClient ws, MailerClient mailerClient) {
	    this.ws = ws;
	    this.mailerClient = mailerClient;
	  }
	
	//============================================================
    // -- INI SUSPENDIDO -- EXTRAE DESDE APIS ICONSTRUYE PLANES
	
	public class tarea extends Thread {
		public EmisorTributario emisor;
		public String token;
		public String desde;
		public String hasta;
		public List<List<String>> listIdOrgc;
		public String empresa;
		public String username;
		public String baseDato;
		public String sendToEmail;
		
		
		public tarea(EmisorTributario emisor, String token, String desde, String hasta, List<List<String>> listIdOrgc, String empresa, String username, String baseDato, String sendToEmail) {
			this.emisor = emisor;
			this.token = token;
			this.desde = desde;
			this.hasta = hasta;
			this.listIdOrgc = listIdOrgc;
			this.empresa = empresa;
			this.username = username;
			this.baseDato = baseDato;
			this.sendToEmail = sendToEmail;
		}
		
		
		public void run(){
			
			List<String> listaIdDocumento = new ArrayList<String>();
			Map<String,Long> mapIConstruye = new HashMap<String,Long>();
			
			List<TipoTrabajo> listTipoTrabajo = new ArrayList<TipoTrabajo>();
			List<TipoPlan> listTipoPlan = new ArrayList<TipoPlan>();
			List<TipoMantencion> listTipoMantencion = new ArrayList<TipoMantencion>();
			List<Equipo> listEquipo = new ArrayList<Equipo>();
			
			
			try {
				Connection con = db.getConnection();
				mapIConstruye = IConstruye.mapNumOcVsIdDoc(con, baseDato);
				listTipoTrabajo = TipoTrabajo.all(con, baseDato);
				listTipoPlan = TipoPlan.all(con, baseDato);
				listTipoMantencion = TipoMantencion.all(con, baseDato);
				listEquipo = Equipo.allAll(con, baseDato);
				con.close();
			} catch (SQLException e) {
    			e.printStackTrace();
    		}
			
			for(int i=0; i<listIdOrgc.size(); i++) {
				String idOrgc = listIdOrgc.get(i).get(0);
				List<List<String>> listaOCAux = ApiIConstruyeOC.obtieneListaOC(emisor, ws, token, desde, hasta, idOrgc);
				for(List<String> lista: listaOCAux) {
					Long idDoc = mapIConstruye.get(lista.get(1));
					if(idDoc==null) {
						listaIdDocumento.add(lista.get(0));
					}
				}
			}
			
			List<List<String>> detalleOCFinal = new ArrayList<List<String>>();
			for(String idDocumento: listaIdDocumento) {
				List<List<String>> detalleOC = ApiIConstruyeOC.obtieneDetalleOC(emisor, ws, token, idDocumento);
				for(List<String> aux: detalleOC) {
					detalleOCFinal.add(aux);
				}
			}
			
			File file = HojaVida.hojaVidaImportIConstruye(detalleOCFinal, listEquipo, listTipoMantencion, listTipoPlan, listTipoTrabajo);
			
			Email email = new Email();
    		String asunto = "IMPORT EXCEL OC desde IConstruye";
   			String desde = "desde MADA <informaciones@inqsol.cl>";
   			email.setSubject(asunto);
			email.setFrom(desde);
			email.setBodyHtml("<html><body>" +
					" <p>EMPRESA: <b>"+empresa+"</b></p>" +
   		    		" <p>USUARIO: <b>"+username+"</b></p>" +
   		    		" <p>CONCEPTO: <b>Importacion de OC desde IConstruye</b></p>" +
   		    		" <p>CONTENIDO: <b>ARCHIVO EXCEL ADJUNTO</b></p>" +
   		    		" </body></html>");
			email.addAttachment("archivo.xlsx", file);
   		    email.addTo(sendToEmail);
   		    mailerClient.send(email);
			
		}
	}
	
	public Result plantillaIConstruyeDownload(Http.Request request) {
		Sessiones s = new Sessiones(request);
		if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
			DynamicForm form = formFactory.form().bindFromRequest(request);
			if (form.hasErrors()) {
				return ok(mensajes.render("/",msgErrorFormulario));
		   	}else {
		   		String desde = form.get("fechaDesde").trim();
		   		String hasta = form.get("fechaHasta").trim();
		   		String sendToEmail = form.get("sendToEmail").trim();
	    		try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			EmisorTributario emisor = EmisorTributario.find(con, s.baseDato);
	    			String token = ApiIConstruyeOC.obtieneToken(emisor, ws);
	    			List<List<String>> listIdOrgc = ApiIConstruyeOC.obtieneCtrosGestion(emisor, ws, token, mapeoDiccionario.get("nEmpresa"));
	    			tarea x = new tarea(emisor,  token,  desde,  hasta, listIdOrgc, mapeoDiccionario.get("nEmpresa"), s.userName, s.baseDato, sendToEmail);
	    			x.start();
	    			con.close();
	    			return ok(mensajes.render("/hojaVidaMantencionLista/0","El proceso comenzo, en pocos minutos sera enviado archivo excel a eMail: "+sendToEmail+", mientras puede continuar operando mada."));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
		   	}
		}
    	return ok(mensajes.render("/",msgError));
	}
	
	public Result plantillaIConstruyeUpload(Http.Request request) {
		Sessiones s = new Sessiones(request);
		if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
		
			DynamicForm form = formFactory.form().bindFromRequest(request);
	    	if (form.hasErrors()) {
	    		return ok(mensajes.render("/","ERROR DE CAPTURA: "+msgError));
	    	}else {
	    		Http.MultipartFormData<TemporaryFile> body = request.body().asMultipartFormData();
				Http.MultipartFormData.FilePart<TemporaryFile> archivo = body.getFile("archivoXLSX");
				if (archivo != null) {
					File file = Archivos.parseMultipartFormDatatoFile(archivo);
					List<List<String>> listado = HojaVida.hojaVidaLEEimportIConstruye(file);
					
					if(listado.size()<2) {
	    				return ok(mensajes.render("/hojaVidaMantencionLista/0","ERROR: El Archivo excel esta vacio"));
					}else {
						try {
			    			Connection con = db.getConnection();
			    			Map<String,Equipo> mapEquipo = Equipo.mapAllVigentesPorCodigo(con, s.baseDato);
			    			
			    			//VALIDA EXCEL
			    			
			    			boolean sinCodigo = false;
			    			boolean codigoNoExiste = false;
			    			String listCodNoExiste = "";
			    			
			    			for(int i=1; i<listado.size(); i++) {
			    				
			    				String codigo_equipo = listado.get(i).get(0);
			    				if(codigo_equipo.length()==0) {
			    					sinCodigo = true;
			    					break;
			    				}
			    				
	    	    				if(codigo_equipo!=null) {
	    	    					codigo_equipo = codigo_equipo.trim().toUpperCase();
	    	    				}
			    				Equipo equipo = mapEquipo.get(codigo_equipo);
			    				if(equipo==null) {
			    					listCodNoExiste += codigo_equipo + ", ";
			    					codigoNoExiste = true;
			    				}
			    				
			    			}
			    			
			    			//FIN VALIDA
			    			
			    			if(sinCodigo) {
			    				con.close();
			    				return ok(mensajes.render("/hojaVidaMantencionLista/0","ERROR: EXISTEN LINEAS SIN CODIGO DE EQUIPO"));
			    			}else if(codigoNoExiste) {
			    				con.close();
			    				return ok(mensajes.render("/hojaVidaMantencionLista/0","ERROR: LOS CODIGOS DE EQUIPOS: "+listCodNoExiste+" NO EXISTEN EN MADA"));
			    			}else {
			    				
			    				Map<String,Moneda> mapMoneda = Moneda.mapNickMonedas(con, s.baseDato);
			    				Map<String,EquivalenciasMonedas> mapEquivalenciasMonedas = EquivalenciasMonedas.mapEquivNickMonedas(con, s.baseDato);
			    				Map<String,PlanMantencion> mapPlanMantencion = PlanMantencion.mapPorCodigoEquipo(con, s.baseDato);

				    			Map<String,TipoMantencion> mapTipoMantencion = TipoMantencion.mapPorNombre(con, s.baseDato);
				    			Map<String,TipoPlan> mapTipoPlan = TipoPlan.mapPorNombre(con, s.baseDato);
				    			Map<String,TipoTrabajo> mapTipoTrabajo = TipoTrabajo.mapPorNombre(con, s.baseDato);
				    			
				    			Map<String,Proveedor> mapRutProveedor = Proveedor.mapPorRut(con, s.baseDato);
				    			Map<String,Proveedor> mapNickProveedor = Proveedor.mapPorNick(con, s.baseDato);
				    			
				    			TipoMantencion auxTipoMantencion = TipoMantencion.find(con, s.baseDato, (long)1);
				    			TipoPlan auxTipoPlan = TipoPlan.find(con, s.baseDato, (long)1);
				    			TipoTrabajo auxTipoTrabajo = TipoTrabajo.find(con, s.baseDato, (long)1);
				    			
				    			String insertHojaVida = "";
				    			Map<String, List<String>> mapIconstruye = new HashMap<String, List<String>>();
				    			Map<String, Long> mapAuxIconstruye = IConstruye.mapNumOcVsIdDoc(con, s.baseDato);
			    				
			    				String ocYaImportadas = ": ";
			    				
			    				for(int i=1; i<listado.size(); i++) {
			    					String codigo_equipo = listado.get(i).get(0);
			    					String tipo_mantencion = listado.get(i).get(1);
			    					String tipo_plan = listado.get(i).get(2);
			    					String tipo_trabajo = listado.get(i).get(3);
			    					String id_doc = listado.get(i).get(4);
			    					String nro_oc = listado.get(i).get(5);
			    				//	String nombre_oc = listado.get(i).get(6);
			    					String fecha_oc = listado.get(i).get(7);
			    					String fecha_envio = listado.get(i).get(8);
			    					String nickMoneda = listado.get(i).get(9);
			    					String costo_neto = listado.get(i).get(10);
			    					String rut_proveedor = listado.get(i).get(11);
			    					String nombre_proveedor = listado.get(i).get(12);
			    					String trabajo_echo = listado.get(i).get(13);
			    					
			    					TipoMantencion tipoMantencion = mapTipoMantencion.get(tipo_mantencion);
			    					if(tipoMantencion==null) {
			    						tipoMantencion = auxTipoMantencion;
			    					}
			    					
			    					TipoPlan tipoPlan = mapTipoPlan.get(tipo_plan);
			    					if(tipoPlan==null) {
			    						tipoPlan = auxTipoPlan;
			    					}
			    					
			    					TipoTrabajo tipoTrabajo = mapTipoTrabajo.get(tipo_trabajo);
			    					if(tipoTrabajo==null) {
			    						tipoTrabajo = auxTipoTrabajo;
			    					}
			    					
			    					if(codigo_equipo!=null) {
		    	    					codigo_equipo = codigo_equipo.trim().toUpperCase();
		    	    				}
			    					
			    					PlanMantencion planMantencion = mapPlanMantencion.get(codigo_equipo);
			    					Equipo equipo = mapEquipo.get(codigo_equipo);
			    					if(planMantencion==null) {
			    						PlanMantencion.create(con, s.baseDato, equipo.getId());
			    					}
			    					
	    	    					if(nickMoneda!=null) {
	    	    						nickMoneda = nickMoneda.trim().toUpperCase();
	    	    					}
	    	    					
			    					Moneda moneda = mapMoneda.get(nickMoneda);
			    					if(moneda == null) {
			    						EquivalenciasMonedas equivalenciasMonedas = mapEquivalenciasMonedas.get(nickMoneda);
			    						if(equivalenciasMonedas == null) {
			    							Moneda aux = Moneda.find(con, s.baseDato, (long)1);
			    							nickMoneda = aux.getNickName();
			    						}else {
			    							nickMoneda = equivalenciasMonedas.equivEnMada;
			    						}
			    						moneda = mapMoneda.get(nickMoneda.toUpperCase());
			    					}
			    					
			    					Proveedor proveedor = mapRutProveedor.get(rut_proveedor);
			    					if(proveedor==null) {
				    					String nick = nombre_proveedor;
				    					if (nick.length()>20) {
				    						nick = nick.substring(0,20);
				    					}
				    					Proveedor aux = mapNickProveedor.get(nick);
				    					if(aux!=null) {
				    						nick = aux.getId().toString() + nick;
				    						if (nick.length()>20) {
					    						nick = nick.substring(0,20);
					    					}
				    					}
				    					Proveedor nuevoProveedor = new Proveedor();
				    					nuevoProveedor.rut = rut_proveedor;
				    					nuevoProveedor.nombre = nombre_proveedor;
				    					nuevoProveedor.nickName = nick;
			    						Proveedor.createDesdeIConstruye(con, s.baseDato, nuevoProveedor);
			    						proveedor = Proveedor.findPorRut(con, s.baseDato, rut_proveedor);
			    					}
			    					
			    					Long auxIdDoc = mapAuxIconstruye.get(nro_oc);
			    					if(auxIdDoc==null) {
			    						insertHojaVida += "('"+tipoPlan.getId().toString()+"','"+equipo.getId().toString()+"','"+tipoMantencion.getId().toString()+"','"
				    							+fecha_envio+"','"+tipoTrabajo.getId().toString()+"','"+moneda.getId().toString()+"','"+costo_neto+"','"
				    							+proveedor.getId().toString()+"','"+nro_oc+"','"+fecha_oc+"','"+trabajo_echo+"','"+fecha_envio+"'),";
				    					
				    					List<String> aux = new ArrayList<String>();
				    					aux.add(id_doc);
				    					aux.add(nro_oc);
				    					mapIconstruye.put(nro_oc, aux);
			    					}else {
			    						ocYaImportadas += nro_oc + ", ";
			    					}
			    					
			    				}
			    				
			    				if(insertHojaVida.length()>2) {
			    					insertHojaVida = insertHojaVida.substring(0,insertHojaVida.length()-1);
					    			HojaVida.insert(con, s.baseDato, insertHojaVida);
					    			
					    			String insertIConstruye = "";
					    			for(List<String> lista: mapIconstruye.values()) {
					    				Long aux = mapAuxIconstruye.get(lista.get(1));
					    				if(aux==null) {
					    					insertIConstruye += "('"+lista.get(0)+"','"+lista.get(1)+"'),";
					    				}
					    			}
					    			
					    			if(insertIConstruye.length()>2) {
					    				insertIConstruye = insertIConstruye.substring(0,insertIConstruye.length()-1);
						    			
					    				if(insertIConstruye.length()>2) {
					    					IConstruye.insert(con, s.baseDato, insertIConstruye);
					    				}
					    			}
			    				}
					    			
			    				if(!ocYaImportadas.equals(": ")){
			    					ocYaImportadas = "IMPORTACION EXITOSA No se importaron las siguientes OC debido a que ya fueron importadas"+ ocYaImportadas;
			    				}else {
			    					ocYaImportadas = "IMPORTACION EXITOSA";
			    				}
			    				
				    			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "hojaVida", (long)0, "insert", "ingresa nuevas lineas MASIVO desde ICONSTRUYE ");
				    			con.close();
			    				return ok(mensajes.render("/hojaVidaMantencionLista/0",ocYaImportadas));
			    				
			    			}
						} catch (SQLException e) {
			    			e.printStackTrace();
			    		}
					}
				}
	    	}
		}
		return ok(mensajes.render("/",msgError));
    }
	
    // -- FIN SUSPENDIDO -- EXTRAE DESDE APIS ICONSTRUYE PLANES
    //============================================================
	
	
	public Result detalleProductoUploadIConstruye(Http.Request request) {
		Sessiones s = new Sessiones(request);
		if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
			DynamicForm form = formFactory.form().bindFromRequest(request);
	    	if (form.hasErrors()) {
	    		return ok(mensajes.render("/","ERROR DE CAPTURA: "+msgError));
	    	}else {
	    		Http.MultipartFormData<TemporaryFile> body = request.body().asMultipartFormData();
				Http.MultipartFormData.FilePart<TemporaryFile> archivo = body.getFile("archivoXLSX");
				if (archivo != null) {
					File file = Archivos.parseMultipartFormDatatoFile(archivo);
					List<List<String>> listado = IConstruyeProdDet.importProductoDetalleExcel(file);
					
					if(listado.size()<14) {
	    				return ok(mensajes.render("/hojaVidaMantencionLista/0","ERROR: El Archivo excel esta vacio"));
					}else {
						List<List<String>> insert = new ArrayList<List<String>>();
						List<List<String>> update = new ArrayList<List<String>>();
						try {
							Connection con = db.getConnection();
							Map<String,Long> mapAux = IConstruyeProdDet.mapStringVsIdIConstruyeProdDet(con, s.baseDato);
							for(List<String> aux : listado) {
								String key = aux.get(0).trim()+"_"+aux.get(8).trim()+"_"+aux.get(10).trim()+"_"+aux.get(11).trim()+"_"+aux.get(19).trim();
								Long id_iConstruyeProdDet = mapAux.get(key);
								if(id_iConstruyeProdDet == null) {
									insert.add(aux);
								}else {
									aux.add(id_iConstruyeProdDet.toString());
									update.add(aux);
								}
							}
							IConstruyeProdDet.insert(con, s.baseDato, insert);
							IConstruyeProdDet.update(con, s.baseDato, update);
							con.close();
						} catch (SQLException e) {
			    			e.printStackTrace();
			    		}
					}
					return ok(mensajes.render("/hojaVidaMantencionLista/1","PASO 1: TERMINADO CON EXITO"));
				}
	    	}
		}
		return ok(mensajes.render("/",msgError));
    }
	
	public Result detalleProductoDownloadMada(Http.Request request) {
		Sessiones s = new Sessiones(request);
		if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
			DynamicForm form = formFactory.form().bindFromRequest(request);
	    	if (form.hasErrors()) {
	    		return ok(mensajes.render("/","ERROR DE DESCARGA: "+msgError));
	    	}else {
	    		try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
					Map<Long,Long> mapId_iConstruyeProdDet = HojaVida.mapId_iConstruyeProdDet(con, s.baseDato);
					List<Equipo> listEquipo = Equipo.allVigentes(con, s.baseDato);
					List<IConstruyeProdDet> listProdDet = IConstruyeProdDet.all(con, s.baseDato);
					List<IConstruyeProdDet> listProdDetDepurado = new ArrayList<IConstruyeProdDet>();
	    			List<List<String>> listBodegas = BodegaEmpresa.listaAllBodegasVigentesExternas(con, s.baseDato, mapeoPermiso, s.aplicaPorSucursal, s.id_sucursal);// nombre de bodega = get(5)
					for(IConstruyeProdDet l: listProdDet) {
						Long id_iConstruyeProdDet = l.getId();
						Long aux = mapId_iConstruyeProdDet.get(id_iConstruyeProdDet);
						if(aux==null) {
							listProdDetDepurado.add(l);
						}
					}
					File file = IConstruyeProdDet.detalleProductoDownloadMada(listProdDetDepurado, listEquipo, listBodegas);
					
					if(file!=null) {
		       			con.close();
		       			return ok(file,false,Optional.of("ProductoDetalladoMada.xlsx"));
		       		}else {
		       			con.close();
		       			return ok(mensajes.render("/hojaVidaMantencionLista/0","ERROR DE DESCARGA: "+msgError));
		       		}
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    	}
		}
		return ok(mensajes.render("/",msgError));
    }
	
	public Result detalleProductoUploadMada(Http.Request request) {
		Sessiones s = new Sessiones(request);
		if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
			DynamicForm form = formFactory.form().bindFromRequest(request);
	    	if (form.hasErrors()) {
	    		return ok(mensajes.render("/","ERROR DE CAPTURA: "+msgError));
	    	}else {
	    		Http.MultipartFormData<TemporaryFile> body = request.body().asMultipartFormData();
				Http.MultipartFormData.FilePart<TemporaryFile> archivo = body.getFile("archivoXLSX");
				if (archivo != null) {
					File file = Archivos.parseMultipartFormDatatoFile(archivo);
					List<List<String>> listado = IConstruyeProdDet.detalleProductoUploadMada(file);
					if(listado.size()<1) {
	    				return ok(mensajes.render("/hojaVidaMantencionLista/0","ERROR: El Archivo excel esta vacio"));
					}else {
						try {
			    			Connection con = db.getConnection();
			    			Map<String,Equipo> mapEquipo = Equipo.mapAllVigentesPorCodigo(con, s.baseDato);
			    			//VALIDA EXCEL:
				    			boolean sinCodigo = false;
				    			boolean codigoNoExiste = false;
				    			String listCodNoExiste = "";
				    			for(int i=1; i<listado.size(); i++) {
				    				String codigo_equipo = listado.get(i).get(0);
				    				if(codigo_equipo.length()==0) {
				    					sinCodigo = true;
				    					break;
				    				}
				    				
			    					if(codigo_equipo!=null) {
		    	    					codigo_equipo = codigo_equipo.trim().toUpperCase();
		    	    				}
			    					
				    				Equipo equipo = mapEquipo.get(codigo_equipo);
				    				if(equipo==null) {
				    					listCodNoExiste += codigo_equipo + ", ";
				    					codigoNoExiste = true;
				    				}
				    			}
			    			//FIN VALIDA
			    			if(sinCodigo) {
			    				con.close();
			    				return ok(mensajes.render("/hojaVidaMantencionLista/0","ERROR: EXISTEN LINEAS SIN CODIGO DE EQUIPO"));
			    			}else if(codigoNoExiste) {
			    				con.close();
			    				return ok(mensajes.render("/hojaVidaMantencionLista/0","ERROR: LOS CODIGOS DE EQUIPOS: "+listCodNoExiste+" NO EXISTEN EN MADA"));
			    			}else {
			    				Map<String,Moneda> mapMoneda = Moneda.mapNickMonedas(con, s.baseDato);
			    				Map<String,EquivalenciasMonedas> mapEquivalenciasMonedas = EquivalenciasMonedas.mapEquivNickMonedas(con, s.baseDato);
			    				Map<String,TipoTrabajo> mapTipoTrabajo = TipoTrabajo.mapPorCod_CCcost_iconstruye(con, s.baseDato);
			    				Map<String,Proveedor> mapRutProveedor = Proveedor.mapPorRut(con, s.baseDato);
				    			Map<String,Proveedor> mapNickProveedor = Proveedor.mapPorNick(con, s.baseDato);
				    			Map<String,Long> mapIdBodegas = BodegaEmpresa.mapNombreVsId(con, s.baseDato);
				    			
				    			String insertHojaVida = "";
				    			Map<String, List<String>> mapIconstruye = new HashMap<String, List<String>>();
				    			
				    			String insertAjusteEpp = "";
				    			
				    			Map<Long,Long> mapId_iConstruyeProdDet = HojaVida.mapId_iConstruyeProdDet(con, s.baseDato);
				    			
				    			
				    			String lineasYaImportadas = ": ";
				    			TipoPlan tipoPlan = TipoPlan.find(con, s.baseDato, (long)1);
				    			TipoMantencion tipoMantencion = TipoMantencion.find(con, s.baseDato, (long)1);
				    			Map<String, Long> mapAuxIconstruye = IConstruye.mapNumOcVsIdDoc(con, s.baseDato);
				    			Map<String,PlanMantencion> mapPlanMantencion = PlanMantencion.mapPorCodigoEquipo(con, s.baseDato);
			    				for(List<String> l: listado) {
			    					String codigo_equipo = l.get(0);
			    					String nomFamilia = l.get(1).trim();
			    					String nomBodega = l.get(2).trim();
			    					String cod_CCcost_iconstruye = l.get(11).trim();	//corresponde al cod centro costo de iconstruye
			    					String tipo_trabajo = l.get(11).trim()+"_"+l.get(12).trim();	//corresponde al COD + NOMBRE centro costo de iconstruye
			    					String id_doc = "0";
			    					String nro_oc = l.get(3).trim();
			    					String fecha_oc = Fechas.AAMMDD(l.get(5));
			    					String nickMoneda = l.get(13).trim();
			    					String costo_neto = l.get(14).trim();
			    					String rut_proveedor = l.get(7).trim();
			    					String nombre_proveedor = l.get(8).trim();
			    					String trabajo_echo = l.get(9).trim() + ". " + l.get(10).trim();
			    					TipoTrabajo tipoTrabajo = mapTipoTrabajo.get(cod_CCcost_iconstruye.trim());
			    					String id_iConstruyeProdDet = l.get(16);
			    					
			    					if(tipoTrabajo==null) {
			    						TipoTrabajo.createConCCOST(con, s.baseDato, cod_CCcost_iconstruye.trim(), tipo_trabajo.trim());
			    						tipoTrabajo = TipoTrabajo.findPorCCOST(con, s.baseDato, cod_CCcost_iconstruye.trim());
			    						mapTipoTrabajo.put(cod_CCcost_iconstruye.trim().trim(), tipoTrabajo);
			    					}
			    					
			    					if(codigo_equipo!=null) {
		    	    					codigo_equipo = codigo_equipo.trim().toUpperCase();
		    	    				}
			    				
			    					PlanMantencion planMantencion = mapPlanMantencion.get(codigo_equipo);
			    					Equipo equipo = mapEquipo.get(codigo_equipo);
			    					if(planMantencion==null) {
			    						PlanMantencion.create(con, s.baseDato, equipo.getId());
			    						PlanMantencion aux = PlanMantencion.findPorCodigo(con, s.baseDato, codigo_equipo);
			    						mapPlanMantencion.put(codigo_equipo, aux);
			    					}
			    					
			    					if(nickMoneda!=null) {
	    	    						nickMoneda = nickMoneda.trim().toUpperCase();
	    	    					}
			    					Moneda moneda = mapMoneda.get(nickMoneda);
			    					if(moneda == null) {
			    						EquivalenciasMonedas equivalenciasMonedas = mapEquivalenciasMonedas.get(nickMoneda.trim());
			    						if(equivalenciasMonedas == null) {
			    							Moneda aux = Moneda.find(con, s.baseDato, (long)1);
			    							nickMoneda = aux.getNickName().trim();
			    						}else {
			    							nickMoneda = equivalenciasMonedas.equivEnMada.trim();
			    						}
			    						moneda = mapMoneda.get(nickMoneda.trim().toUpperCase());
			    					}
			    					
			    					Proveedor proveedor = mapRutProveedor.get(rut_proveedor.trim());
			    					if(proveedor==null) {
				    					String nick = nombre_proveedor.trim();
				    					if (nick.length()>20) {
				    						nick = nick.substring(0,20);
				    					}
				    					Proveedor aux = mapNickProveedor.get(nick.trim());
				    					
				    					if(aux != null ) {
				    						nick = aux.getId().toString().trim() +"_"+ nick.trim();
				    						if (nick.length()>20) {
					    						nick = nick.substring(0,20);
					    					}
				    					}
				    					Proveedor nuevoProveedor = new Proveedor();
				    					nuevoProveedor.rut = rut_proveedor.trim();
				    					nuevoProveedor.nombre = nombre_proveedor.trim();
				    					nuevoProveedor.nickName = nick.trim();
				    					Proveedor.createDesdeIConstruye(con, s.baseDato, nuevoProveedor);
				    					proveedor = Proveedor.findPorRut(con, s.baseDato, rut_proveedor.trim());
				    					
				    					mapRutProveedor.put(rut_proveedor.trim(), proveedor);
				    					mapNickProveedor.put(proveedor.getNickName().trim(), proveedor);
			    					}
			    					
			    					Long id_bodegaEmpresa = mapIdBodegas.get(nomBodega);
			    					if(id_bodegaEmpresa==null) {
			    						id_bodegaEmpresa = (long) 0;
			    					}
			    					
			    					Long auxId_iConstruyeProdDet = mapId_iConstruyeProdDet.get((long) Double.parseDouble(id_iConstruyeProdDet.trim()));
			    					if(auxId_iConstruyeProdDet==null) {
			    						insertHojaVida += "('"+tipoPlan.getId().toString()+"','"+equipo.getId().toString()+"','"+tipoMantencion.getId().toString()+"','"
				    							+fecha_oc.trim()+"','"+tipoTrabajo.getId().toString()+"','"+moneda.getId().toString()+"','"+costo_neto+"','"
				    							+proveedor.getId().toString()+"','"+nro_oc.trim()+"','"+fecha_oc.trim()+"','"+trabajo_echo.trim()+"','"+fecha_oc.trim()
				    							+"','"+id_iConstruyeProdDet.trim()+"','"+id_bodegaEmpresa+"','"+nomFamilia+"'),";
				    					
				    					List<String> aux = new ArrayList<String>();
				    					aux.add(id_doc.trim());
				    					aux.add(nro_oc.trim());
				    					mapIconstruye.put(nro_oc.trim(), aux);
				    					
				    					String concepto = nro_oc.trim() + " - " + l.get(9).trim();
				    					if(concepto.length()>200) {
				    						concepto = concepto.substring(0,200);
				    					}
				    					if(id_bodegaEmpresa>0) {
				    						insertAjusteEpp += "('"+id_bodegaEmpresa.toString()+"','2','1','"+concepto+"','"+fecha_oc+"','"+moneda.getId().toString()+"','"+costo_neto+"','"+l.get(9).trim()+"'),";
				    					}
				    					
				    					
			    					}else {
			    						lineasYaImportadas += "OC:" + nro_oc.trim() + " con ID:" + auxId_iConstruyeProdDet+", ";
			    					}
			    				}
			    				if(insertHojaVida.length()>2) {
			    					insertHojaVida = insertHojaVida.substring(0,insertHojaVida.length()-1);
			    					IConstruyeProdDet.insert(con, s.baseDato, insertHojaVida);
			    					
			    					insertAjusteEpp = insertAjusteEpp.substring(0,insertAjusteEpp.length()-1);
			    					IConstruyeProdDet.insertAjusteEpp(con, s.baseDato, insertAjusteEpp);
					    			
					    			String insertIConstruye = "";
					    			for(List<String> lista: mapIconstruye.values()) {
					    				Long aux = mapAuxIconstruye.get(lista.get(1).trim());
					    				if(aux==null) {
					    					insertIConstruye += "('"+lista.get(0).trim()+"','"+lista.get(1).trim()+"'),";
					    				}
					    			}
					    			
					    			if(insertIConstruye.length()>2) {
					    				insertIConstruye = insertIConstruye.substring(0,insertIConstruye.length()-1);
						    			
					    				if(insertIConstruye.length()>2) {
					    					IConstruye.insert(con, s.baseDato, insertIConstruye);
					    				}
					    			}
			    				}
			    				if(!lineasYaImportadas.equals(": ")){
			    					lineasYaImportadas = "IMPORTACION CON OBSERVACIONES: No se importaron las siguientes lineas debido a que ya fueron importadas"+ lineasYaImportadas;
			    				}else {
			    					lineasYaImportadas = "IMPORTACION EXITOSA";
			    				}
				    			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "hojaVida", (long)0, "insert", "ingresa nuevas lineas MASIVO desde REPORTE PROD DET ICONSTRUYE");
				    			con.close();
			    				return ok(mensajes.render("/hojaVidaMantencionLista/0",lineasYaImportadas));
			    				
			    			}
						} catch (SQLException e) {
			    			e.printStackTrace();
			    		}
					}
				}
	    	}
		}
		return ok(mensajes.render("/",msgError));
    }
	
	
	
	
	
	
	//============================================================
    // MNU planTipoTrabajo   PLANES/Tipo de trabajo
    //============================================================
	
	public Result tipoTrabajoMantencion(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("planTipoTrabajo")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<TipoTrabajo> lista = TipoTrabajo.all(con, s.baseDato);
    			con.close();
    			return ok(tipoTrabajoMantencion.render(mapeoDiccionario,mapeoPermiso,userMnu,lista));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    	return ok(mensajes.render("/",msgError));
	}
	
	public Result tipoTrabajoModifica(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	    	  	Long id_tipo = Long.parseLong(form.get("id_tipo").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			TipoTrabajo tipoTrabajo = TipoTrabajo.find(con, s.baseDato, id_tipo);
	    			con.close();
	    			return ok(tipoTrabajoModifica.render(mapeoDiccionario,mapeoPermiso,userMnu,tipoTrabajo));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    	return ok(mensajes.render("/",msgError));
    }
	
	public Result modificaTipoPorCampoAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String campo = form.get("campo").trim();
	       		Long id_tipo = Long.parseLong(form.get("id_tipo").trim());
	       		String valor = form.get("valor").trim();
				try {
	    			Connection con = db.getConnection();
	    			if(TipoTrabajo.existeTipoTrabajo(con, s.baseDato, valor)) {
	    				con.close();
    	    			return ok("existe");
	    			}else {
	    				if(TipoTrabajo.modificaPorCampo(con, s.baseDato, campo, id_tipo, valor)) {
	    					TipoTrabajo tipo = TipoTrabajo.find(con, s.baseDato, id_tipo);
	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "tipoTrabajo", id_tipo, "update", "cambia nombre del tipo de trabajo "+tipo.getNombre());
	    					con.close();
	    					return ok("");
	    				}
	    				con.close();
	    				return ok("error");
	    			}
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    	return ok("error");
    }
	
	public Result tipoTrabajoAgrega(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("planTipoTrabajo")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			con.close();
    			return ok(tipoTrabajoAgrega.render(mapeoDiccionario,mapeoPermiso,userMnu));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    	return ok(mensajes.render("/",msgError));
	}
	
	public Result tipoTrabajoNuevo(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String nombreTipo = form.get("nombreTipo").trim();
				try {
	    			Connection con = db.getConnection();
	    			if(TipoTrabajo.create(con, s.baseDato, nombreTipo)) {
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "tipoTrabajo", (long)0, "create", "agrega nuevo tipo de trabajo "+nombreTipo);
	    				con.close();
	    				return (tipoTrabajoMantencion(request));
	    			}
            	} catch (SQLException e) {
        			e.printStackTrace();
        		}
	       	}
    	}
    	return ok(mensajes.render("/",msgError));
    }
	
	public Result tipoTrabajoElimina(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_tipo = Long.parseLong(form.get("id_tipo").trim());
				try {
	    			Connection con = db.getConnection();
	    			TipoTrabajo tipo = TipoTrabajo.find(con, s.baseDato, id_tipo);
	    			if(TipoTrabajo.delete(con, s.baseDato, id_tipo)) {
	    				String tipoTrabajo="";
	    				if(tipo!=null) {
	    					tipoTrabajo = tipo.getNombre();
	    				}
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "tipoTrabajo", id_tipo, "delete", "elimina tipo de trabajo "+tipoTrabajo);
	    				con.close();
	    				return (tipoTrabajoMantencion(request));
	    			}else {
	    				String mensaje = " No es posible eliminar este trabajo, esta en uso ";
	    				con.close();
	    				return ok(mensajes.render("/tipoTrabajoMantencion/",mensaje));
	    			}
            	} catch (SQLException e) {
        			e.printStackTrace();
        		}
	       	}
    	}
    	return ok(mensajes.render("/",msgError));
    }
	
	//============================================================
    // MNU planTipoPlan   PLANES/Tipo Plan de Mantencion
    //============================================================
	
	public Result tipoPlanMantencion(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("planTipoPlan")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<TipoPlan> lista = TipoPlan.all(con, s.baseDato);
    			con.close();
    			return ok(tipoPlanMantencion.render(mapeoDiccionario,mapeoPermiso,userMnu,lista));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    	return ok(mensajes.render("/",msgError));
	}
	
	public Result tipoPlanModifica(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	    	  	Long id_tipo = Long.parseLong(form.get("id_tipo").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			TipoPlan tipoPlan = TipoPlan.find(con, s.baseDato, id_tipo);
	    			con.close();
	    			return ok(tipoPlanModifica.render(mapeoDiccionario,mapeoPermiso,userMnu,tipoPlan));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    	return ok(mensajes.render("/",msgError));
    }
	
	public Result modificaPlanPorCampoAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String campo = form.get("campo").trim();
	       		Long id_tipo = Long.parseLong(form.get("id_tipo").trim());
	       		String valor = form.get("valor").trim();
				try {
	    			Connection con = db.getConnection();
	    			if(TipoPlan.existeTipoPlan(con, s.baseDato, valor)) {
	    				con.close();
    	    			return ok("existe");
	    			}else {
	    				if(TipoPlan.modificaPorCampo(con, s.baseDato, campo, id_tipo, valor)) {
	    					TipoPlan tipo = TipoPlan.find(con, s.baseDato, id_tipo);
	    					Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "tipoPlan", id_tipo, "update", "cambia nombre del tipo de plan de mantencion "+tipo.getNombre());
	    					con.close();
	    					return ok("");
	    				}
	    			}
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    	return ok("error");
    }
	
	public Result tipoPlanAgrega(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("planTipoPlan")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			con.close();
    			return ok(tipoPlanAgrega.render(mapeoDiccionario,mapeoPermiso,userMnu));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    	return ok(mensajes.render("/",msgError));
	}
	
	public Result tipoPlanNuevo(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String nombreTipo = form.get("nombreTipo").trim();
				try {
	    			Connection con = db.getConnection();
	    			if(TipoPlan.create(con, s.baseDato, nombreTipo)) {
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "tipoPlan", (long)0, "create", "agrega nuevo tipo plan de mantencion "+nombreTipo);
	    				con.close();
	    				return (tipoPlanMantencion(request));
	    			}
            	} catch (SQLException e) {
        			e.printStackTrace();
        		}
	       	}
    	}
    	return ok(mensajes.render("/",msgError));
    }
	
	public Result tipoPlanElimina(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_tipo = Long.parseLong(form.get("id_tipo").trim());
				try {
	    			Connection con = db.getConnection();
	    			TipoPlan tipo = TipoPlan.find(con, s.baseDato, id_tipo);
	    			if(TipoPlan.delete(con, s.baseDato, id_tipo)) {
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "tipoPlan", id_tipo, "delete", "elimina tipo plan de mantencion "+tipo.getNombre());
	    				con.close();
	    				return (tipoPlanMantencion(request));
	    			}else {
	    				String mensaje = " No es posible eliminar este plan de mantención, esta en uso ";
	    				return ok(mensajes.render("/tipoPlanMantencion/",mensaje));
	    			}
            	} catch (SQLException e) {
        			e.printStackTrace();
        		}
	       	}
    	}
    	return ok(mensajes.render("/",msgError));
    }
	
	//============================================================
    // MNU planMantencion   PLANES/Planes de Mantencion
    //============================================================
	
	public Result hojaVidaPlanMantencion(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("planMantencion")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<PlanMantencion> listaPlanes = PlanMantencion.all(con, s.baseDato);
    			con.close();
    			return ok(hojaVidaPlanMantencion.render(mapeoDiccionario,mapeoPermiso,userMnu,listaPlanes));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    	return ok(mensajes.render("/",msgError));
	}
	
	public static Boolean esMayor(String consumoEstimadoAHoy,String proximaMantencion) {
		Double a = Double.parseDouble(consumoEstimadoAHoy.replaceAll(",", "").trim());
		Double b = Double.parseDouble(proximaMantencion.replaceAll(",", "").trim());
		if(a > b) {
			return true;
		}else {
			return(false);
		}
	}
	
	public static String pintaColor(String consumoEstimadoAHoy,String proximaMantencion,String rotacion) {
		Double a = Double.parseDouble(consumoEstimadoAHoy.replaceAll(",", "").trim());
		Double b = Double.parseDouble(proximaMantencion.replaceAll(",", "").trim());
		
		Double rot = Double.parseDouble(rotacion.replaceAll(",", "").trim());
		Double rot25 = rot * 0.25;
		Double rot10 = rot * 0.10;
		
		Double dif = b - a;
		
		if(dif > rot25) {
			return "verde";
		}else if(dif > rot10){
			return "amarillo";
		}else {
			return "rojo";
		}
	}
	
	
	
	public Result hojaVidaPlanModifica(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	    	  	Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			List<PlanMantencion> allPlan = PlanMantencion.allTipoPlanPorId_equipo(con, s.baseDato, id_equipo);
	    			List<String> listAtributos = HojaVida.atributosEquipo(con, s.baseDato, id_equipo);
	    			List<String> listCompra = HojaVida.ultimaFacturaCompra(con, s.baseDato, id_equipo);
	    			List<UnidadMantencion> listUnidad = UnidadMantencion.all(con, s.baseDato);
	    			List<TipoPlan> listTipoPlanAux = TipoPlan.all(con, s.baseDato);
	    			List<TipoPlan> listTipoPlan = new ArrayList<TipoPlan>();
	    			for(TipoPlan tipo: listTipoPlanAux) {
	    				boolean flag = true;
	    				for(PlanMantencion plan: allPlan) {
	    					if((long) plan.id_tipoPlan == tipo.id) {
	    						flag = false;
	    					}
	    				}
	    				if(flag) {
	    					listTipoPlan.add(tipo);
	    				}
	    			}
	    			con.close();
	    			return ok(hojaVidaPlanModifica.render(mapeoDiccionario,mapeoPermiso,userMnu,allPlan,listAtributos,listCompra,listUnidad,listTipoPlan));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    	return ok(mensajes.render("/",msgError));
    }
	
	public Result hojaVidaAgregaPlan(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	    	  	Long id_tipoPlan = Long.parseLong(form.get("id_tipoPlan").trim());
	    	  	Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			PlanMantencion.addPlan(con, s.baseDato, id_tipoPlan, id_equipo);
	    			con.close();
	    			return (hojaVidaPlanModifica(request));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    	return ok(mensajes.render("/",msgError));
    }
	
	public Result hojaVidaPlanElimina(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	    	  	Long id_tipoPlan = Long.parseLong(form.get("id_tipoPlan").trim());
	    	  	Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			TipoPlan tipo = TipoPlan.find(con, s.baseDato, id_tipoPlan);
	    			if(PlanMantencion.delete(con, s.baseDato, id_tipoPlan, id_equipo)) {
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "planMantencion", id_tipoPlan, "delete", "elimina del planMantencion tipoPlan: "+tipo.getNombre()+" id_equipo: "+id_equipo);
	    				con.close();
	    				return (hojaVidaPlanMantencion(request));
	    			}else {
	    				String mensaje = " No es posible eliminar este plan de mantención, esta en uso ";
	    				con.close();
	    				return ok(mensajes.render("/hojaVidaPlanMantencion/",mensaje));
	    			}
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    	return ok(mensajes.render("/",msgError));
    }
	
	public Result hojaVidaPlanCrear(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			List<Equipo> listEquipos = PlanMantencion.allSinPlanMantencionConStock(con, s.baseDato);
    			con.close();
    			return ok(hojaVidaPlanCrear.render(mapeoDiccionario,mapeoPermiso,userMnu,listEquipos));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    	return ok(mensajes.render("/",msgError));
	}
	
	public Result hojaVidaPlanCrear2(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	    	  	Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			if(PlanMantencion.create(con, s.baseDato, id_equipo)) {
	    				Equipo equipo = Equipo.find(con, s.baseDato, id_equipo);
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "planMantencion", id_equipo, "create", "agrega equipo codigo "+equipo.getCodigo()+" al planMantencion id_equipo: "+id_equipo);
	    				con.close();
	    				return (hojaVidaPlanMantencion(request));
	    			}
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    	return ok(mensajes.render("/",msgError));
	}
	
	public Result actualCampoPlanMantencionAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String campo = form.get("campo").trim();
	       		Long id_tipoPlan = Long.parseLong(form.get("id_tipoPlan").trim());
	       		Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
	       		String valor = form.get("valor").trim();
				try {
	    			Connection con = db.getConnection();
	    			if(PlanMantencion.actualizaPorCampo(con, s.baseDato, id_tipoPlan, id_equipo, campo, valor)) {
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "planMantencion", (long)0, "update", "actualiza plan mantencion "+ campo +" id_tipoPlan "+id_tipoPlan+" id_equipo "+id_equipo);
    					con.close();
    					return ok("");
	    			};
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    	return ok("error");
    }
	
	
	//============================================================
    // MNU planHojaVida   PLANES/Actualizar Hojas de Vida
    //============================================================
	
	
	public Result importaHojaVidaIconstruye0(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	    	  	Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("planHojaVida")==null) {
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
	    			return ok(importaHojaVidaIconstruye0.render(mapeoDiccionario,mapeoPermiso,userMnu, desde, hasta, listIdOrgc, id_equipo));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    	return ok(mensajes.render("/",msgError));
    	
    }
	
	public Result importaHojaVidaIconstruye1(Http.Request request) {
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
	       		Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
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
	    			return ok(importaHojaVidaIconstruye1.render(mapeoDiccionario,mapeoPermiso,userMnu,listaOC, id_equipo));
	       		} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    		return ok(mensajes.render("/",msgError));
    }
	
	public Result importaHojaVidaIconstruye2(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String idDocumento = form.get("idDocumento").trim();
	       		Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
	       		try {
	    			Connection con = db.getConnection();
	    			
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
	    				Map<String,EquivalenciasMonedas> mapEquivalenciasMonedas = EquivalenciasMonedas.mapEquivNickMonedas(con, s.baseDato);
	    				
	    				BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, (long)2);
	    				Long numeroDecimales = (long)0;
	    				
	    				
	    				String insert = "";
	    				
	    				for(List<String> detalle: detalleOC) {
	    					
	    					Unidad unidad = new Unidad();
	    					Moneda moneda = new Moneda();
	    					
	    					String nomUnidad = detalle.get(9);
	    					if(nomUnidad!=null) {
	    						nomUnidad = nomUnidad.toUpperCase().trim();
	    					}
	    					unidad = mapUnidad.get(nomUnidad);
	    					if(unidad == null) {
	    						unidad = Unidad.create(con, s.baseDato, nomUnidad);
	    					}
	    					
	    					String nickMoneda = detalle.get(11).toUpperCase().trim().toUpperCase();
	    					moneda = mapMoneda.get(nickMoneda.toUpperCase());
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
	    					
	    					detalle.set(14, id_equipo.toString());
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
	    					
	    					String id_tipoPlan = "1";
	    					String idEquipo = id_equipo.toString();
	    					String id_tipoMantencion = "1";
	    					String fechaInicio =  detalle.get(2);
	    					String id_tipoTrabajo = "1";
	    					String id_moneda =  moneda.getId().toString();
	    					String costoNeto =  detalle.get(13);
	    					String id_proveedor =  proveedor.getId().toString().toString();
	    					String numeroDocumento =  detalle.get(1);
	    					String fechaDocumento =  detalle.get(19);
	    					String trabajoHecho =  detalle.get(18)+"\n"+detalle.get(6)+" - "+detalle.get(7)+"\n"+detalle.get(8)+"\n"+detalle.get(18);
	    					String fechaTermino =  detalle.get(2);
	    					
	    					insert += "('"+id_tipoPlan+"','"+idEquipo+"','"+id_tipoMantencion+"','"+fechaInicio+"','"+id_tipoTrabajo+"','"+id_moneda+"','"+costoNeto+"','"+id_proveedor+"','"+numeroDocumento+"','"+fechaDocumento+"','"+trabajoHecho+"','"+fechaTermino+"'),";
	    					
	    				}
	    				
	    				
		    			if(insert.length()>2) {
		    				insert = insert.substring(0,insert.length()-1);
		    				HojaVida.insert(con, s.baseDato, insert);
		    			}
		    			
		    			Long idDoc = Long.parseLong(detalleOC.get(0).get(0));
		    			String numOC = detalleOC.get(0).get(1);
		    			//GRABAR TODO
	    				
		    			IConstruye.create(con, s.baseDato, idDoc, numOC);
	    				
		    			Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "hojaVida", (long)0, "insert", "ingresa nuevas lineas desde ICONSTRUYE nro: "+numOC);
		    			
		    			
		    			con.close();
		    			
		    			return redirect("/hojaVidaMantencionHojaReturn/"+id_equipo);
		    			
		    			
	    			}
	    			
	       		} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    		return ok(mensajes.render("/",msgError));
    }
	
	
	
	
	public Result hojaVidaMantencionLista(Http.Request request, Long flagModal) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("planHojaVida")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<PlanMantencion> listaPlanes = PlanMantencion.all(con, s.baseDato);
    			Fechas hoy = Fechas.hoy();
    			con.close();
    			return ok(hojaVidaMantencionLista.render(mapeoDiccionario,mapeoPermiso,userMnu,listaPlanes,hoy.getFechaStrAAMMDD(),flagModal));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    	return ok(mensajes.render("/",msgError));
	}
	
	public Result hojaVidaMantencionPlan(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	    	  	Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			List<PlanMantencion> allPlan = PlanMantencion.allTipoPlanPorId_equipo(con, s.baseDato, id_equipo);
	    			List<String> listAtributos = HojaVida.atributosEquipo(con, s.baseDato, id_equipo);
	    			List<String> listCompra = HojaVida.ultimaFacturaCompra(con, s.baseDato, id_equipo);
	    			con.close();
	    			return ok(hojaVidaMantencionPlan.render(mapeoDiccionario,mapeoPermiso,userMnu,allPlan,listAtributos,listCompra));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    	return ok(mensajes.render("/",msgError));
    }
	
	public Result hojaVidaMantencionHojaReturn(Http.Request request, Long id_equipo) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			if(mapeoPermiso.get("planHojaVida")==null) {
	    				con.close();
	    				return ok(mensajes.render("/",msgSinPermiso));
	    			}
	    			List<PlanMantencion> allPlan = PlanMantencion.allTipoPlanPorId_equipo(con, s.baseDato, id_equipo);
	    			List<String> listAtributos = HojaVida.atributosEquipo(con, s.baseDato, id_equipo);
	    			List<String> listCompra = HojaVida.ultimaFacturaCompra(con, s.baseDato, id_equipo);
	    			List<TipoMantencion> listTipoMant = TipoMantencion.all(con, s.baseDato);
	    			List<TipoTrabajo> listTipoTrab = TipoTrabajo.all(con, s.baseDato);
	    			List<Moneda> listMoneda = Moneda.all(con, s.baseDato);
	    			List<Proveedor> listProveedor = Proveedor.all(con, s.baseDato);
	    			List<HojaVida> listDetalle = HojaVida.allPorEquipo(con, s.baseDato, id_equipo);
	    			List<List<String>> listTiposPlan = HojaVida.tiposPlanPorEquipo(con, s.baseDato, id_equipo);
	    			con.close();
	    			return ok(hojaVidaMantencionHoja.render(mapeoDiccionario,mapeoPermiso,userMnu,allPlan,listAtributos,listCompra,listTipoMant,listTipoTrab,listMoneda,listProveedor,listDetalle, listTiposPlan));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
    	}
    	return ok(mensajes.render("/",msgError));
    }
	
	public Result hojaVidaMantencionHoja(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	    	  	Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			List<PlanMantencion> allPlan = PlanMantencion.allTipoPlanPorId_equipo(con, s.baseDato, id_equipo);
	    			List<String> listAtributos = HojaVida.atributosEquipo(con, s.baseDato, id_equipo);
	    			List<String> listCompra = HojaVida.ultimaFacturaCompra(con, s.baseDato, id_equipo);
	    			List<TipoMantencion> listTipoMant = TipoMantencion.all(con, s.baseDato);
	    			List<TipoTrabajo> listTipoTrab = TipoTrabajo.all(con, s.baseDato);
	    			List<Moneda> listMoneda = Moneda.all(con, s.baseDato);
	    			List<Proveedor> listProveedor = Proveedor.all(con, s.baseDato);
	    			List<HojaVida> listDetalle = HojaVida.allPorEquipo(con, s.baseDato, id_equipo);
	    			List<List<String>> listTiposPlan = HojaVida.tiposPlanPorEquipo(con, s.baseDato, id_equipo);
	    			con.close();
	    			//hojaVidaMantencionPlan
	    			return ok(hojaVidaMantencionHoja.render(mapeoDiccionario,mapeoPermiso,userMnu,allPlan,listAtributos,listCompra,listTipoMant,listTipoTrab,listMoneda,listProveedor,listDetalle, listTiposPlan));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    	return ok(mensajes.render("/",msgError));
    }
	
	
	public Result importaHojaVidaIconstruye(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	    	  	Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			List<PlanMantencion> allPlan = PlanMantencion.allTipoPlanPorId_equipo(con, s.baseDato, id_equipo);
	    			List<String> listAtributos = HojaVida.atributosEquipo(con, s.baseDato, id_equipo);
	    			List<String> listCompra = HojaVida.ultimaFacturaCompra(con, s.baseDato, id_equipo);
	    			List<TipoMantencion> listTipoMant = TipoMantencion.all(con, s.baseDato);
	    			List<TipoTrabajo> listTipoTrab = TipoTrabajo.all(con, s.baseDato);
	    			List<Moneda> listMoneda = Moneda.all(con, s.baseDato);
	    			List<Proveedor> listProveedor = Proveedor.all(con, s.baseDato);
	    			List<HojaVida> listDetalle = HojaVida.allPorEquipo(con, s.baseDato, id_equipo);
	    			List<List<String>> listTiposPlan = HojaVida.tiposPlanPorEquipo(con, s.baseDato, id_equipo);
	    			con.close();
	    			//hojaVidaMantencionPlan
	    			return ok(hojaVidaMantencionHoja.render(mapeoDiccionario,mapeoPermiso,userMnu,allPlan,listAtributos,listCompra,listTipoMant,listTipoTrab,listMoneda,listProveedor,listDetalle, listTiposPlan));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    	return ok(mensajes.render("/",msgError));
    }
	
	public Result hojaVidaMantencionHojaAgrega(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	    	  	Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			if(HojaVida.agregaLinea(con, s.baseDato, id_equipo)) {
	    				Equipo equipo = Equipo.find(con, s.baseDato, id_equipo);
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "hojaVida", (long)0, "create", "agrega linea a hojaVida de equipo codigo "+equipo.getCodigo()+" con id_equipo: "+id_equipo);
	    				con.close();
	    				return (hojaVidaMantencionHoja(request));
	    			}
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    	return ok(mensajes.render("/",msgError));
    }
	
	public Result hojaVidaMantencionHojaElimina(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	    	  	Long id_hojaVida = Long.parseLong(form.get("id_hojaVida").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			if(HojaVida.eliminaLinea(con, s.baseDato, id_hojaVida)) {
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "hojaVida", id_hojaVida, "create", "elimina linea a hojaVida id_hojaVida: "+id_hojaVida);
	    				con.close();
	    				return (hojaVidaMantencionHoja(request));
	    			}else {
	    				return (hojaVidaMantencionHoja(request));
	    			}
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    	return ok(mensajes.render("/",msgError));
    }
	
	public Result grabarHojaVidaPdf(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
    		Http.MultipartFormData<TemporaryFile> body = request.body().asMultipartFormData();
			Http.MultipartFormData.FilePart<TemporaryFile> docAdjunto = body.getFile("docAdjunto");
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	    	  	Long id_hojaVida = Long.parseLong(form.get("id_hojaVida").trim());
	    	  	if (docAdjunto != null) {
		    		try {
		    			Connection con = db.getConnection();
						String nombreArchivoSinExtencion = "HojaVida-Id"+id_hojaVida;
						String path = Archivos.grabarArchivos(docAdjunto, s.baseDato, nombreArchivoSinExtencion);
						HojaVida.actualizaPorCampo(con, s.baseDato, id_hojaVida, "documentoPDF", path);
						Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "hojaVida", id_hojaVida, "update", "actualiza documento PDF");
						con.close();
						return (hojaVidaMantencionHoja(request));
		        	} catch (SQLException e) {
		    			e.printStackTrace();
		    		}
	    	  	}
	       	}
    	}
    	return ok(mensajes.render("/",msgError));
    }
	
	public Result actualCampoHojaVidaAjax(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		String campo = form.get("campo").trim();
	       		Long id_hojaVida = Long.parseLong(form.get("id_hojaVida").trim());
	       		String valor = form.get("valor").trim();
				try {
	    			Connection con = db.getConnection();
	    			if(HojaVida.actualizaPorCampo(con, s.baseDato, id_hojaVida, campo, valor)) {
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "hojaVida", id_hojaVida, "update", "actualiza hojaVida "+ campo +" id_hojaVida "+id_hojaVida);
    					con.close();
    					return ok("");
	    			};
				} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    	return ok("error");
    }
	
	//============================================================
    // MNU reportesHojaVida   PLANES/Hojas de Vida
    //============================================================
	
	public Result hojaVidaReportLista(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("reportesHojaVida")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<PlanMantencion> listaPlanes = PlanMantencion.all(con, s.baseDato);
    			con.close();
    			return ok(hojaVidaReportLista.render(mapeoDiccionario,mapeoPermiso,userMnu,listaPlanes));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    	return ok(mensajes.render("/",msgError));
	}
	
	public Result hojaVidaReportDetalle(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	    	  	Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			List<PlanMantencion> allPlan = PlanMantencion.allTipoPlanPorId_equipo(con, s.baseDato, id_equipo);
	    			List<String> listAtributos = HojaVida.atributosEquipo(con, s.baseDato, id_equipo);
	    			List<String> listCompra = HojaVida.ultimaFacturaCompra(con, s.baseDato, id_equipo);
	    			List<HojaVida> listDetalle = HojaVida.allPorEquipo(con, s.baseDato, id_equipo);
	    			con.close();
	    			return ok(hojaVidaReportDetalle.render(mapeoDiccionario,mapeoPermiso,userMnu,allPlan,listAtributos,listCompra,listDetalle));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	       	}
    	}
    	return ok(mensajes.render("/",msgError));
    }
	
	public Result hojaVidaReportDetalleExcel(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_equipo = Long.parseLong(form.get("id_equipo").trim());
	       		try {
	    			Connection con = db.getConnection();
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			List<PlanMantencion> allPlan = PlanMantencion.allTipoPlanPorId_equipo(con, s.baseDato, id_equipo);
	    			List<HojaVida> listDetalle = HojaVida.allPorEquipo(con, s.baseDato, id_equipo);
	    			File file = HojaVida.hojaVidaReportDetalleExcel(s.baseDato, mapeoDiccionario, allPlan, listDetalle);
	    			if(file!=null) {
		       			con.close();
		       			return ok(file,false,Optional.of("ReporteHojaVida.xlsx"));
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
    // MNU analisisHojaVida   PLANES/Reporte KPIs
    //============================================================
	
	public Result hojaVidaReportKpis(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("analisisHojaVida")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<List<String>> lista = ReportHojaVida.reportDiasNoOperativo(con, s.baseDato, mapeoDiccionario.get("pais"), Long.parseLong(s.id_sucursal));
    			Map<String,String> mapUbicacion = Equipo.mapUnaSolaUbicacion(con, s.baseDato);
    			con.close();
    			return ok(hojaVidaReportKpis.render(mapeoDiccionario,mapeoPermiso,userMnu,lista,mapUbicacion));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    	return ok(mensajes.render("/",msgError));
	}
	
	
	//============================================================
    // MNU planGraficos   PLANES/Graficos
    //============================================================
	
	public Result hojaVidaReportGrafico(Http.Request request, Long id_grupo, Long periodo) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("planGraficos")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			List<String> series = ReportHojaVida.reportGrafico(con, s.baseDato, id_grupo, periodo, mapeoDiccionario.get("pais"), Long.parseLong(s.id_sucursal));
    			List<List<String>> listaGrupos = ReportHojaVida.listaGruposConHojaVida(con, s.baseDato);
    			con.close();
    			return ok(hojaVidaReportGrafico.render(mapeoDiccionario,mapeoPermiso,userMnu,series,listaGrupos,id_grupo,periodo));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    	return ok(mensajes.render("/",msgError));
	}
	
	
	
	
}









