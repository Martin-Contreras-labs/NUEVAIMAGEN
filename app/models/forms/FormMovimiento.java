package models.forms;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.util.TempFile;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableCell.XWPFVertAlign;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import models.reports.ReportInventarios;
import models.tables.BodegaEmpresa;
import models.tables.Cliente;
import models.tables.Compra;
import models.tables.ContactoBodegaEmpresa;
import models.tables.Guia;
import models.tables.ListaPrecio;
import models.tables.Moneda;
import models.tables.Movimiento;
import models.tables.Precio;
import models.tables.Proyecto;
import models.tables.TasaEquipo;
import models.tables.TasaGrupo;
import models.tables.TipoEstado;
import models.tables.Transportista;
import models.tables.UsuarioPermiso;
import models.utilities.Archivos;
import models.utilities.DecimalFormato;
import models.utilities.Fechas;
import play.libs.Files.TemporaryFile;
import play.mvc.Http;

public class FormMovimiento {
	public Long id_bodegaOrigen;
	public Long id_bodegaDestino;
	public Long numeroGuia;
	public String numGuiaCliente;
	public String fechaGuia;
	public String observaciones;
	public List<Long> id_equipo;
	public List<Long> id_cotizacion;
	public List<String> cantidad;
	public List<Long> esVenta;
	public List<Long> esNuevo;
	public List<String> exceso;
	public List<String> estados;
	public List<String> reparaciones;
	public Long id_guia;
	public Long seModifico;  // 0 no se modifico, 1 fue modificado
	
	public String docAnexo;
	public Long id_transportista;
	
	public List<String> cantCliente;
	
	public String fotos;
	
	public Long id_bodegaEmpresa;
	public List<Long> idGrupos;
	

	public FormMovimiento(Long id_bodegaOrigen, Long id_bodegaDestino, Long numeroGuia, String numGuiaCliente,
			String fechaGuia, String observaciones, List<Long> id_equipo, List<Long> id_cotizacion,
			List<String> cantidad, List<Long> esVenta, List<Long> esNuevo, List<String> exceso, List<String> estados,
			List<String> reparaciones, Long id_guia, Long seModifico, String docAnexo, Long id_transportista,
			List<String> cantCliente, String fotos, Long id_bodegaEmpresa, List<Long> idGrupos) {
		super();
		this.id_bodegaOrigen = id_bodegaOrigen;
		this.id_bodegaDestino = id_bodegaDestino;
		this.numeroGuia = numeroGuia;
		this.numGuiaCliente = numGuiaCliente;
		this.fechaGuia = fechaGuia;
		this.observaciones = observaciones;
		this.id_equipo = id_equipo;
		this.id_cotizacion = id_cotizacion;
		this.cantidad = cantidad;
		this.esVenta = esVenta;
		this.esNuevo = esNuevo;
		this.exceso = exceso;
		this.estados = estados;
		this.reparaciones = reparaciones;
		this.id_guia = id_guia;
		this.seModifico = seModifico;
		this.docAnexo = docAnexo;
		this.id_transportista = id_transportista;
		this.cantCliente = cantCliente;
		this.fotos = fotos;
		this.id_bodegaEmpresa = id_bodegaEmpresa;
		this.idGrupos = idGrupos;
	}

	public FormMovimiento() {
		super();
	}
	
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0");
	static DecimalFormat myformatdouble0 = new DecimalFormat("#,##0");
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	
	
	
	public class grabarFilesThread extends Thread {
		String db;
		Archivos archivos;
		String nombreArchivoSinExtencion;
		String nombreCarpetaFotos;
		
		public grabarFilesThread(String db, Archivos archivos, String nombreArchivoSinExtencion, String nombreCarpetaFotos) {
			super();
			this.db = db;
			this.archivos = archivos;
			this.nombreArchivoSinExtencion = nombreArchivoSinExtencion;
			this.nombreCarpetaFotos = nombreCarpetaFotos;
		}
		
		public void runGrabarFiles() {
			if(archivos.docAdjunto != null) {
				if(archivos.docAdjunto.size() == 1) {
					Archivos.grabarArchivos(archivos.docAdjunto.get(0), db, nombreArchivoSinExtencion);
				}else {
					try {
						Archivos.comprimirYgrabar(archivos.docAdjunto, db, nombreArchivoSinExtencion);
					} catch (Exception e) {}
				}
			}
			
			if(archivos.fotosAdjunto != null) {
				List<Http.MultipartFormData.FilePart<TemporaryFile>> fotos = archivos.fotosAdjunto;
	    		for(Http.MultipartFormData.FilePart<TemporaryFile> file: fotos) {
	    			String subCarpeta = db+"/"+nombreCarpetaFotos;  
	    			String nombreFile = file.getFilename();
	    			String nombreFileSinExtencion = nombreFile.substring(0,nombreFile.indexOf("."));
	    			Archivos.grabarArchivos(file, subCarpeta, nombreFileSinExtencion);
	    		}
			}
			
		}
		
	}
	
	
	
	
	public static List<List<Double>> create (Connection con, String db, FormMovimiento form, String id_userCrea, String id_userMoficica,
			Map<String,Movimiento> mapStock) {
		
		BodegaEmpresa bodegaOrigen = BodegaEmpresa.findXIdBodega(con, db, form.id_bodegaOrigen);
		
		List<List<Double>> listaIdMovIdTipEstCant = new ArrayList<List<Double>>();
		
		Guia aux = new Guia();
		aux.setNumero(form.numeroGuia);
		aux.setFecha(form.fechaGuia);
		aux.setDocAnexo(form.docAnexo);
		aux.setFotos(form.fotos);
		aux.setObservaciones(form.observaciones);
		aux.setNumGuiaCliente(form.numGuiaCliente);
		aux.setId_bodegaOrigen(form.id_bodegaOrigen);
		aux.setId_bodegaDestino(form.id_bodegaDestino);
		aux.setId_cotizacion((long)0);      //NOTA: guias y movimientos generados desde MOVIMIENTO no tienen cotizacion de origen asignado a la guia
		aux.setId_ot((long)0);     			//NOTA: guias y movimientos generados desde MOVIMIENTO no tienen ot de origen asignado a la guia
		aux.setId_transportista(form.id_transportista);
		aux.setFotos(form.fotos);
		
		Guia guia = Guia.create(con, db, aux, id_userCrea, id_userMoficica);
		
		if(guia!=null) {
			
			List<Movimiento> listMov = new ArrayList<Movimiento>();
			List<String> listEstad = new ArrayList<String>();
			List<String> listRepar = new ArrayList<String>();
			
			for(int i=0; i<form.id_equipo.size(); i++) {
				Double cantidad = (double)0;
				Double exceso = (double)0;
				Double cantCliente = (double)0;
				
				if(form.cantidad!=null) {
					cantidad = Double.parseDouble(form.cantidad.get(i).replaceAll(",", ""));
				}
				
				if(form.exceso!=null) {
					exceso =Double.parseDouble(form.exceso.get(i).replaceAll(",", ""));
				}
				
				// verifica y corrige cantidades en caso de devoluciones
				if(bodegaOrigen.getEsInterna() == (long)2) {
					Movimiento mov = mapStock.get(form.id_equipo.get(i)+"_"+form.id_cotizacion.get(i));
					if(mov != null) {
						if(mov.getCantidad() < cantidad) {
							Double dif = cantidad - mov.getCantidad();
							cantidad = mov.getCantidad();
							exceso += dif;
						}
					}
				}
				// fin verifica
				
				if(form.cantCliente!=null) {
					cantCliente =Double.parseDouble(form.cantCliente.get(i).replaceAll(",", ""));
				}
				
				Movimiento auxMov = new Movimiento();
				
				//salida
				Long id_cotizacion = form.id_cotizacion.get(i);
				if((long)bodegaOrigen.getEsInterna()==(long)1) {
					id_cotizacion = (long)0;
				}
				auxMov.setId_bodegaEmpresa(form.id_bodegaOrigen);
				auxMov.setId_equipo(form.id_equipo.get(i));
				auxMov.setId_tipoMovimiento((long)2);
				auxMov.setId_guia(guia.getId());
				auxMov.setCantidad(cantidad);
				auxMov.setExceso(exceso);
				auxMov.setId_bodegaOrigen((long)0);
				auxMov.setEsVenta(form.esVenta.get(i));
				auxMov.setEsNuevo(form.esNuevo.get(i));
				auxMov.setId_cotizacion(id_cotizacion);
				auxMov.setNroGuia(guia.getNumero());
				//agrega a la lista
				listMov.add(auxMov);
				//agrega estados y reparaciones en impares
				if((long)bodegaOrigen.getEsInterna()>(long)1) {
					listEstad.add(form.estados.get(i)+"&"+form.id_equipo.get(i)+"_"+form.id_cotizacion.get(i));
					listRepar.add(form.reparaciones.get(i)+"&"+form.id_equipo.get(i)+"_"+form.id_cotizacion.get(i));
				}
				auxMov.setCantCliente(cantCliente);
				
				auxMov = new Movimiento();
				//ingreso
				auxMov.setId_bodegaEmpresa(form.id_bodegaDestino);
				auxMov.setId_equipo(form.id_equipo.get(i));
				auxMov.setId_tipoMovimiento((long)1);
				auxMov.setId_guia(guia.getId());
				auxMov.setCantidad(cantidad);
				auxMov.setExceso((double)0);
				auxMov.setId_bodegaOrigen(form.id_bodegaOrigen);
				auxMov.setEsVenta(form.esVenta.get(i));
				auxMov.setEsNuevo(form.esNuevo.get(i));
				auxMov.setId_cotizacion((long)0);
				auxMov.setCantCliente(cantCliente);
				auxMov.setNroGuia(guia.getNumero());
				//agrega a la lista
				listMov.add(auxMov);
			}
			listaIdMovIdTipEstCant = FormMovimiento.insertMovimientos(con, db, listMov, listEstad, listRepar, bodegaOrigen, guia);
		}
		return(listaIdMovIdTipEstCant);
	}
	
	public static List<List<Double>> insertMovimientos(Connection con, String db, List<Movimiento> listMov, List<String> listEstad, List<String> listRepar, BodegaEmpresa bodegaOrigen, Guia guia) {
		
		
		List<List<Double>> listaIdMovIdTipEstCant = new ArrayList<List<Double>>();
		
		String insertMovimiento = "";
		for(int i=0; i<listMov.size(); i++) {
			Double random = Math.random();
			insertMovimiento += "('"
					+listMov.get(i).getId_bodegaEmpresa()+"','"
					+listMov.get(i).getId_equipo()+"','"
					+listMov.get(i).getId_tipoMovimiento()+"','"
					+listMov.get(i).getId_guia()+"','"
					+listMov.get(i).getCantidad()+"','"
					+listMov.get(i).getExceso()+"','"
					+listMov.get(i).getId_bodegaOrigen()+"','"
					+listMov.get(i).getEsVenta()+"','"
					+listMov.get(i).getEsNuevo()+"','"
					+listMov.get(i).getId_cotizacion()+"','"
					+random+"','"
					+listMov.get(i).getCantCliente()+"','"
					+listMov.get(i).getNroGuia()+"'),";
			
		}
		if(insertMovimiento.length()>10) {
			insertMovimiento = insertMovimiento.substring(0,insertMovimiento.length()-1);
		}else {
			insertMovimiento = null;
		}
		
		if(insertMovimiento!=null && insertMovimiento.length()>2) {
			try {
				PreparedStatement smt = con
						.prepareStatement("insert into  `"+db+"`.movimiento (id_bodegaEmpresa, id_equipo, id_tipoMovimiento, id_guia, cantidad, exceso,"
								+ " id_bodegaOrigen, esVenta, esNuevo, id_cotizacion, random, cantCliente, nroGuia ) "
								+ " VALUES "+insertMovimiento+";");
				
				smt.executeUpdate();
				smt.close();
				
				
				//prepara insertEstadoEquipo
				Map<String,Long> mapIdEqVsIdMov = new HashMap<String,Long>();
				String insertEstadoEquipo = "";
				if(insertMovimiento != null  && insertMovimiento.length()>2 && (long)bodegaOrigen.getEsInterna() > (long)1) {
					try {
						PreparedStatement smt2 = con.prepareStatement("select id_equipo, id_cotizacion, id from `"+db+"`.movimiento where id_bodegaEmpresa=? and id_guia=?;");	
						smt2.setLong(1, bodegaOrigen.getId());
						smt2.setLong(2, guia.getId());
						ResultSet rs = smt2.executeQuery();
						while(rs.next()) {
							mapIdEqVsIdMov.put(rs.getLong(1)+"_"+rs.getLong(2), rs.getLong(3));
						}
						smt2.close();
						rs.close();
						
						String auxId_equipo_id_coti = "";
						Map<String,Long> mapAux = new HashMap<String,Long>();
						for(Movimiento x: listMov) {
							mapAux.put(x.getId_equipo()+"_"+x.getId_cotizacion(), x.getId_equipo());
						}
						
				        for (Map.Entry<String, Long> entry : mapAux.entrySet()) {
				        	String clave = entry.getKey();
				        	Long id_equipo = entry.getValue();
							
							
							
							for(int j=0; j<listEstad.size(); j++) {
								if(listEstad.get(j).length() > 0) {
									
									String[] auxEstado = listEstad.get(j).split("&");
									
									String auxId_EquipoId_coti = auxEstado[1];
									
									String[] estados = auxEstado[0].split(";");
									
									String id_equipo_id_coti = clave;
									
									Long id_movimiento = mapIdEqVsIdMov.get(id_equipo_id_coti);
									
									if(id_movimiento!=null && ! id_equipo_id_coti.equals(auxId_equipo_id_coti) && id_equipo_id_coti.equals(auxId_EquipoId_coti)) {
										
										auxId_EquipoId_coti = id_equipo_id_coti;
										
										for(int k=0; k<estados.length; k++) {
											if(estados[k].length()>2) {
												String[] auxEst = estados[k].split(":");
												insertEstadoEquipo += "('"
														+id_movimiento+"','"
														+auxEst[0]+"','"
														+auxEst[1]+"','"
														+guia.getId()+"'),";
												
												List<Double> auxMov = new ArrayList<Double>();
												auxMov.add(Double.parseDouble(id_movimiento.toString()));					//0 id_movimiento
												auxMov.add(Double.parseDouble(auxEst[0]));									//1 id_tipoEstado
												auxMov.add(Double.parseDouble(auxEst[1]));									//2 cantidad
												auxMov.add(Double.parseDouble(id_equipo.toString()));						//3 id_equipo
												listaIdMovIdTipEstCant.add(auxMov);
											}
										}
									}
									
								}
							}
						}
					} catch (SQLException e) {
		    			e.printStackTrace();
					}
				}
				if(insertEstadoEquipo.length()>10) {
					insertEstadoEquipo = insertEstadoEquipo.substring(0,insertEstadoEquipo.length()-1);
				}else {
					insertEstadoEquipo = null;
				}
				
				
				
				
				
				if(insertEstadoEquipo!=null && insertEstadoEquipo.length()>2) {
					PreparedStatement smt3 = con.prepareStatement("insert into `"+db+"`.estadoEquipo (id_movimiento, id_tipoEstado, cantidad, id_guia) "
							+ " values "+insertEstadoEquipo+";");
					smt3.executeUpdate();
					smt3.close();
					
					//prepara insertReparacionEquipo
					String insertReparacionEquipo = "";
					Map<String,String> mapIsertReparaciones = new HashMap<String,String>();
					if(insertMovimiento != null  && insertMovimiento.length()>2 && insertEstadoEquipo != null && insertEstadoEquipo.length()>2 && (long)bodegaOrigen.getEsInterna() > (long)1) {
						try {
							Map<String,Long> mapIdMovIdTipEstEqVsIdEst = new HashMap<String,Long>();
							PreparedStatement smt2 = con.prepareStatement("select id_movimiento, id_tipoEstado, id  from `"+db+"`.estadoEquipo where id_guia=?;");
							smt2.setLong(1, guia.getId());
							ResultSet rs = smt2.executeQuery();
							while(rs.next()) {
								mapIdMovIdTipEstEqVsIdEst.put(rs.getString(1)+"_"+rs.getString(2), rs.getLong(3));
							}
							smt2.close();
							rs.close();
							for(int i=0; i<listMov.size(); i++) {
								for(int j=0; j<listRepar.size(); j++) {
									if(listRepar.get(j).length() > 0) {
										
										String[] auxiliarRepar = listRepar.get(j).split("&");
										
										String id_equipo_id_coti = auxiliarRepar[1];
										Long id_movimiento = mapIdEqVsIdMov.get(id_equipo_id_coti);
										
										if(id_movimiento!=null ) {
											String[] reparaciones = auxiliarRepar[0].split(";");
											
											for(int k=0; k<reparaciones.length; k++) {
												String[] auxRepar = reparaciones[k].split(":");
												
												Long id_estadoEquipo = mapIdMovIdTipEstEqVsIdEst.get(""+id_movimiento+"_"+auxRepar[0]);
												
												if(id_estadoEquipo!=null && Double.parseDouble(auxRepar[2]) > (double)0) {
													String auxValores = "('"
															+id_movimiento+"','"
															+id_estadoEquipo+"','"
															+auxRepar[0]+"','"
															+auxRepar[1]+"','"
															+auxRepar[2]+"','"
															+guia.getId()+"'),";
													mapIsertReparaciones.put(auxValores, auxValores);
												}
											}
										}
										
									}
								}
							}
						} catch (SQLException e) {
			    			e.printStackTrace();
						}
					}
					
					for(String x: mapIsertReparaciones.values()) {
						insertReparacionEquipo += x;
					}
					if(insertReparacionEquipo.length()>10) {
						insertReparacionEquipo = insertReparacionEquipo.substring(0,insertReparacionEquipo.length()-1);
					}else {
						insertReparacionEquipo = null;
					}
					
					if(insertReparacionEquipo!=null) {
						PreparedStatement smt5 = con.
								prepareStatement("insert into `"+db+"`.reparacionEquipo (id_movimiento, id_estadoEquipo, id_tipoEstado, id_tipoReparacion, cantidad, id_guia) "
								+ " values "+insertReparacionEquipo+";");
						smt5.executeUpdate();
						smt5.close();
					}
					
					
				}
			} catch (SQLException e) {
    			e.printStackTrace();
			}
		}else {
			Movimiento.delete(con, db, guia.getId());
		}
		
		
		return (listaIdMovIdTipEstCant);
	}
	
	public static void insertPreciosNuevos(Connection con, String db, FormMovimiento form, Map<String,String> mapeoDiccionario) {
		
		BodegaEmpresa bodegaDestino = BodegaEmpresa.findXIdBodega(con, db, form.id_bodegaDestino);
		
		if(bodegaDestino.getEsInterna()>1) {
			Map<String,ListaPrecio> mapListaPrecio = ListaPrecio.mapListaPrecio(con, db, form.id_bodegaDestino, mapeoDiccionario.get("pais"));
			Map<Long,Precio> mapPrecio = Precio.mapAll(con, db, mapeoDiccionario, bodegaDestino.getId_sucursal());
			Map<String,TasaGrupo> mapTasaArrGrupo = TasaGrupo.mapAllXBodegaEmpresa(con, db, bodegaDestino.getId());
			Map<String,TasaEquipo> mapTasaArrEquipo = TasaEquipo.mapAllXBodegaEmpresa(con, db, bodegaDestino.getId());
			
			Map<Long,List<Double>> MapUltimoPrecio = Compra.ultimoPrecio(con, db);
			
			Double tasaArrBod = bodegaDestino.getTasaArriendo();
			
			List<List<String>> listPrecios = new ArrayList<List<String>>();
			
			for(int i=0; i<form.id_equipo.size(); i++) {
				String idEq_idCoti = form.id_equipo.get(i)+"_"+form.id_cotizacion.get(i);
				
				ListaPrecio listaPrecio = mapListaPrecio.get(idEq_idCoti);
				
				if( listaPrecio == null) {
					
					Precio precio = mapPrecio.get(form.id_equipo.get(i));
					if(precio==null) {
						List<Double> ultimoPrecio = MapUltimoPrecio.get(form.id_equipo.get(i));
						if(ultimoPrecio!=null) {
							Precio.createAuxiliar(con, db, bodegaDestino.getId_sucursal(), form.id_equipo.get(i), ultimoPrecio.get(1).longValue(), ultimoPrecio.get(0));
						}else {
							Precio.createAuxiliar(con, db, bodegaDestino.getId_sucursal(), form.id_equipo.get(i), (long)1, (double)1);
						}
						precio = Precio.findXIdEquipo(con, db, bodegaDestino.getId_sucursal(), form.id_equipo.get(i), mapeoDiccionario);
					}
					
					
					Double tasaArrGrupo = (double)0;
					String idBod_idGrupo = bodegaDestino.getId()+"_"+precio.getId_grupo();
					TasaGrupo tasaGrupo =  mapTasaArrGrupo.get(idBod_idGrupo);
					if(tasaGrupo != null) {
						tasaArrGrupo = tasaGrupo.getTasaArriendo();
					}
					
					Double tasaArrEquipo = (double)0;
					String idBod_idEquipo = bodegaDestino.getId()+"_"+precio.id_equipo;
					TasaEquipo tasaEquipo =  mapTasaArrEquipo.get(idBod_idEquipo);
					if(tasaEquipo != null) {
						tasaArrEquipo = tasaEquipo.getTasaArriendo();
					}
					
					Double tasa = (double) 0;
					if((double)tasaArrEquipo > (double)0) {
						tasa = tasaArrEquipo;
					}else if((double)tasaArrGrupo > (double)0) {
						tasa = tasaArrGrupo;
					}else if((double)tasaArrBod > (double)0) {
						tasa = tasaArrBod;
					}
					
					String id_bodegaEmpresa = bodegaDestino.getId().toString();
					String id_equipo = precio.getId_equipo().toString();
					String id_moneda = precio.getId_moneda().toString();
					String fecha = Fechas.hoy().getFechaStrAAMMDD();
					
					String precioVenta = precio.getPrecioVenta().replaceAll(",", "");
					String precioReposicion = precio.getPrecioReposicion().replaceAll(",", "");
					String precioArriendo = precio.getPrecioArriendo().replaceAll(",", "");
					String id_unidad = precio.getId_unidadTiempo().toString();
					String precioMinimo = precio.getPrecioMinimo().replaceAll(",", "");
					String permanenciaMinima = precio.getPermanenciaMinima().replaceAll(",", "");
					String id_cotizacion = form.id_cotizacion.get(i).toString();
					
					Double precioVentaDbl = Double.parseDouble(precioVenta);
					Double precioReposicionDbl = Double.parseDouble(precioReposicion);
					Double precioArriendoDbl = Double.parseDouble(precioArriendo);
					
					if(tasa>0) {
						precioArriendoDbl = Double.parseDouble(precioReposicion) * tasa;
					}
					
					DecimalFormat formato = new DecimalFormat("#.########");
					precioVenta = formato.format(precioVentaDbl);
					precioReposicion = formato.format(precioReposicionDbl);
					precioArriendo = formato.format(precioArriendoDbl);
					
					List<String> auxList = new ArrayList<String>();
					auxList.add(id_bodegaEmpresa);
					auxList.add(id_equipo);
					auxList.add(id_moneda);
					auxList.add(fecha);
					auxList.add(precioVenta);
					auxList.add(precioReposicion);
					auxList.add(precioArriendo);
					auxList.add(id_unidad);
					auxList.add(precioMinimo);
					auxList.add(permanenciaMinima);
					auxList.add(id_cotizacion);
					listPrecios.add(auxList);
				}
			}
			
			if(listPrecios.size()>0) {
				String insertar = "";
				for(int i=0; i<listPrecios.size(); i++) {
					insertar += "(";
					for(int j=0; j<listPrecios.get(i).size(); j++) {
						insertar += "'"+listPrecios.get(i).get(j)+"',";
					}
					insertar = insertar.substring(0, insertar.length()-1);
					insertar += "),";
				}
				if(insertar.length() > 5) {
					insertar = insertar.substring(0, insertar.length()-1);
					PreparedStatement smt;
					try {
						smt = con
								.prepareStatement(" insert into `"+db+"`.listaPrecio " +
										" (id_bodegaEmpresa,id_equipo,id_moneda,fecha,precioVenta,precioReposicion, " +
										" precioArriendo,id_unidadTiempo,precioMinimo,permanenciaMinima,id_cotizacion) " +
										" values " + insertar +";");
						smt.executeUpdate();
						smt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public static void moveSegunTipoEstado(Connection con, String db, List<List<Double>> listaIdMovIdTipEstCant, String id_userCrea, String id_userMoficica) {
		List<TipoEstado> listTipoEstado = TipoEstado.all(con, db);
		Long id_movimiento = listaIdMovIdTipEstCant.get(0).get(0).longValue();
		Movimiento movimiento = Movimiento.find(con, db, id_movimiento);
		Guia guia = Guia.find(con, db, movimiento.getId_guia());
		String guiaRef = "from_"+guia.numero;
		Long auxBodegaAsociada = guia.getId_bodegaDestino();
		List<Movimiento> listMov = new ArrayList<Movimiento>();
		
		
		for(int i=0; i<listTipoEstado.size(); i++) {
			boolean flag = true;
			for(int j=0; j<listaIdMovIdTipEstCant.size(); j++) {
				Long id_tipoEstado = listaIdMovIdTipEstCant.get(j).get(1).longValue();
				
				
				if( (long)id_tipoEstado == (long)listTipoEstado.get(i).getId() 
						&& (long)listTipoEstado.get(i).id_bodegaAsociada != (long)auxBodegaAsociada
						&& (long)listTipoEstado.get(i).id_bodegaAsociada > 0) {
					
					if(flag) {
						Guia aux = new Guia();
						aux.setNumero(guia.getId()*-1);
						aux.setFecha(guia.getFecha());
						aux.setDocAnexo("0");
						aux.setObservaciones("Movimiento generado automáticamente según clasificación de estado equipo.");
						aux.setNumGuiaCliente(guiaRef);
						aux.setId_bodegaOrigen(auxBodegaAsociada);
						aux.setId_bodegaDestino(listTipoEstado.get(i).id_bodegaAsociada);
						aux.setId_cotizacion((long)0);      //NOTA: guias y movimientos generados desde MOVIMIENTO no tienen cotizacion de origen asignado a la guia
						aux.setId_ot((long)0);     			//NOTA: guias y movimientos generados desde MOVIMIENTO no tienen ot de origen asignado a la guia
						aux.setId_transportista(guia.getId_transportista());
						aux.setFotos(guia.getFotos());
						guia = Guia.create(con, db, aux, id_userCrea, id_userMoficica);
						flag =false;
					}
					Movimiento auxMov = new Movimiento();
					Long id_equipo = listaIdMovIdTipEstCant.get(j).get(3).longValue();
					//salida
					auxMov.setId_bodegaEmpresa(auxBodegaAsociada);
					auxMov.setId_equipo(id_equipo);
					auxMov.setId_tipoMovimiento((long)2);
					auxMov.setId_guia(guia.id);
					auxMov.setCantidad((double)listaIdMovIdTipEstCant.get(j).get(2));
					auxMov.setExceso((double)0);
					auxMov.setId_bodegaOrigen((long)0);
					auxMov.setEsVenta((long)0);
					auxMov.setEsNuevo((long)0);
					auxMov.setId_cotizacion((long)0);
					//agrega a la lista
					listMov.add(auxMov);
					auxMov = new Movimiento();
					//ingreso
					auxMov.setId_bodegaEmpresa(listTipoEstado.get(i).id_bodegaAsociada);
					auxMov.setId_equipo(id_equipo);
					auxMov.setId_tipoMovimiento((long)1);
					auxMov.setId_guia(guia.id);
					auxMov.setCantidad((double)listaIdMovIdTipEstCant.get(j).get(2));
					auxMov.setExceso((double)0);
					auxMov.setId_bodegaOrigen(auxBodegaAsociada);
					auxMov.setEsVenta((long)0);
					auxMov.setEsNuevo((long)0);
					auxMov.setId_cotizacion((long)0);
					//agrega a la lista
					listMov.add(auxMov);
				}
			}
			
		}
		
		Double random = Math.random();
		for(int j=0; j<listMov.size(); j++) {
			try {
				PreparedStatement smt = con
						.prepareStatement("insert into  `"+db+"`.movimiento (id_bodegaEmpresa, id_equipo, id_tipoMovimiento, id_guia, cantidad, exceso,"
								+ " id_bodegaOrigen, esVenta, esNuevo, id_cotizacion, random) "
								+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				smt.setLong(1, listMov.get(j).getId_bodegaEmpresa());
				smt.setLong(2, listMov.get(j).getId_equipo());
				smt.setLong(3, listMov.get(j).getId_tipoMovimiento());
				smt.setLong(4, listMov.get(j).getId_guia());
				smt.setDouble(5, listMov.get(j).getCantidad());
				smt.setDouble(6, listMov.get(j).getExceso());
				smt.setLong(7, listMov.get(j).getId_bodegaOrigen());
				smt.setLong(8, listMov.get(j).getEsVenta());
				smt.setLong(9, listMov.get(j).getEsNuevo());
				smt.setLong(10, listMov.get(j).getId_cotizacion());
				smt.setDouble(11, random);
				smt.executeUpdate();
				smt.close();
			} catch (SQLException e) {
    			e.printStackTrace();
			}
		}

	}
	
	public static String generaGuiaPDF(Connection con, String db, FormMovimiento form, Map<String,String> mapDiccionario, Map<String,String> mapeoPermiso, Long id_usuario) {
		
		Guia guia = Guia.findXNumeroGuia(con, db, form.numeroGuia);
		
		BodegaEmpresa bodegaOrigen = BodegaEmpresa.findXIdBodega(con, db, guia.getId_bodegaOrigen());
		Cliente clienteOrigen = Cliente.find(con, db, bodegaOrigen.getId_cliente());
		Proyecto proyectoOrigen = Proyecto.find(con, db, bodegaOrigen.getId_proyecto());
		
		BodegaEmpresa bodegaDestino = BodegaEmpresa.findXIdBodega(con, db, guia.getId_bodegaDestino());
		Cliente clienteDestino = Cliente.find(con, db, bodegaDestino.getId_cliente());
		Proyecto proyectoDestino = Proyecto.find(con, db, bodegaDestino.getId_proyecto());
		
		List<ContactoBodegaEmpresa> listContactos = ContactoBodegaEmpresa.allxBodega(con, db, bodegaDestino.getId());
		
		File tmp = TempFile.createTempFile("tmp","null");
		try {
			
			String path = db;
			if((long)bodegaOrigen.getEsInterna()==(long)1) {
				//GUIA DE SALIDA (DESPACHO DE EQUIPOS A CLIENTE)
				path += "/formatos/guiaSalida.docx";
			}else {
				//GUIA DE ENTRADA (DEVOLUCION DE EQUIPOS)
				if(mapeoPermiso.get("parametro.movimiento-devolucion-cantCliente")!=null && mapeoPermiso.get("parametro.movimiento-devolucion-cantCliente").equals("1")){
					path += "/formatos/guiaEntradaConCliente.docx";
				}else if(mapDiccionario.get("nEmpresa").equals("ALZATEC")) {
					path += "/formatos/guiaEntradaConCliente.docx";
				}else {
					path += "/formatos/guiaEntrada.docx";
				}
			}
			
			InputStream formato = Archivos.leerArchivo(path);
			XWPFDocument doc = new XWPFDocument(formato);
			formato.close();
			
			for (XWPFParagraph p : doc.getParagraphs()) {
	             for (XWPFRun r : p.getRuns()) {
	                String text = r.getText(0);
	 	            if(text!=null){
	 	               if (text.contains("contactosBodega"))   {
	 	            	   String aux = "CONTACTO EN "+mapDiccionario.get("BODEGA")+" "+bodegaDestino.getNombre().toUpperCase()+":";
	 	            	   if(listContactos.size() == 0) {
	 	            		   aux="";
	 	            	   }
	 	            	   for(ContactoBodegaEmpresa c: listContactos) {
	 	            		 String nombre = c.getNombre().toLowerCase();
	 	            		   aux += "\n-"+nombre+", Tel:"+c.getMovil().toLowerCase()+", "+c.getMail().toLowerCase();
	 	            	   }
	 	                    text = text.replace("contactosBodega", aux);
	 	                    r.setText(text, 0); 
	 	               }
	 	            }
	             }
	        }
			
			XWPFTable table = null;
			XWPFTableRow row = null;
			XWPFTableCell cell = null;
			String texto = "";
			
			table = doc.getTables().get(0);
				texto = "";
				cell = table.getRow(1).getCell(2);
				texto = form.numeroGuia.toString();
				setCelda(cell,"Arial",12,2,"2b5079",texto,false);
				cell = table.getRow(2).getCell(2);
				texto = "NroRef: "+ form.numGuiaCliente;
				setCelda(cell,"Arial",10,2,"2b5079",texto,false);
				cell = table.getRow(3).getCell(2);
				texto = Fechas.DDMMAA(form.fechaGuia);
				setCelda(cell,"Arial",12,2,"2b5079",texto,false);
			
			table= doc.getTables().get(1);
				if((long)bodegaOrigen.getEsInterna()>(long)1) {
					//ES BODEGA CLIENTE EXTERNA
					//GUIA DE ENTRADA (DEVOLUCION DE EQUIPOS)
					texto = "";
					cell=table.getRow(1).getCell(1);
					if(clienteOrigen != null) texto = clienteOrigen.nickName;
					setCelda(cell,"Arial",10,1,"2b5079",texto,false);
					cell=table.getRow(2).getCell(1);
					if(clienteOrigen != null) texto = clienteOrigen.direccion;
					setCelda(cell,"Arial",10,1,"2b5079",texto,false);
					cell=table.getRow(3).getCell(1);
					if(clienteOrigen != null) texto = clienteOrigen.region + " - " +clienteOrigen.comuna;
					setCelda(cell,"Arial",10,1,"2b5079",texto,false);
					cell=table.getRow(2).getCell(2);
					if(clienteOrigen != null) texto = clienteOrigen.rut;
					setCelda(cell,"Arial",10,2,"2b5079",texto,false);
				}else {
					//ES BODEGA INTERNA
					//GUIA DE SALIDA (DESPACHO DE EQUIPOS A CLIENTE)
					texto = "";
					cell=table.getRow(1).getCell(1);
					if(clienteDestino != null) texto = clienteDestino.nickName;
					setCelda(cell,"Arial",10,1,"2b5079",texto,false);
					cell=table.getRow(2).getCell(1);
					if(clienteDestino != null) texto = clienteDestino.direccion;
					setCelda(cell,"Arial",10,1,"2b5079",texto,false);
					cell=table.getRow(3).getCell(1);
					if(clienteDestino != null) texto = clienteDestino.region + " - " +clienteDestino.comuna;
					setCelda(cell,"Arial",10,1,"2b5079",texto,false);
					cell=table.getRow(2).getCell(2);
					if(clienteDestino != null) texto = clienteDestino.rut;
					setCelda(cell,"Arial",10,2,"2b5079",texto,false);
				}
			
			table = doc.getTables().get(2);
				if((long)bodegaOrigen.getEsInterna()==(long)1) {
					//ES BODEGA INTERNA
					texto = "";
					cell=table.getRow(1).getCell(1);
					texto = bodegaOrigen.nombre;
					setCelda(cell,"Arial",10,1,"2b5079",texto,false);
					cell=table.getRow(2).getCell(1);
					if(clienteOrigen != null) texto = clienteOrigen.direccion;
					setCelda(cell,"Arial",10,1,"2b5079",texto,false);
					cell=table.getRow(3).getCell(1);
					if(clienteOrigen != null) texto = clienteOrigen.region + " - " +clienteOrigen.comuna;
					setCelda(cell,"Arial",10,1,"2b5079",texto,false);
				}else {
					//ES BODEGA CLIENTE EXTERNA
					texto = "";
					cell=table.getRow(1).getCell(1);
					texto = bodegaOrigen.nombre;
					setCelda(cell,"Arial",10,1,"2b5079",texto,false);
					cell=table.getRow(2).getCell(1);
					if(proyectoOrigen != null) texto = proyectoOrigen.direccion;
					setCelda(cell,"Arial",10,1,"2b5079",texto,false);
					cell=table.getRow(3).getCell(1);
					if(proyectoOrigen != null) texto = proyectoOrigen.region + " - " +proyectoOrigen.comuna;
					setCelda(cell,"Arial",10,1,"2b5079",texto,false);
				}
				
				if((long)bodegaDestino.getEsInterna()==(long)1) {
					//ES BODEGA INTERNA
					texto = "";
					cell=table.getRow(1).getCell(3);
					texto = bodegaDestino.nombre;
					setCelda(cell,"Arial",10,1,"2b5079",texto,false);
					cell=table.getRow(2).getCell(3);
					if(clienteDestino != null) texto = clienteDestino.direccion;
					setCelda(cell,"Arial",10,1,"2b5079",texto,false);
					cell=table.getRow(3).getCell(3);
					if(clienteDestino != null) texto = clienteDestino.region + " - " +clienteDestino.comuna;
					setCelda(cell,"Arial",10,1,"2b5079",texto,false);
				}else {
					//ES BODEGA CLIENTE EXTERNA
					texto = "";
					cell=table.getRow(1).getCell(3);
					texto = bodegaDestino.nombre;
					setCelda(cell,"Arial",10,1,"2b5079",texto,false);
					cell=table.getRow(2).getCell(3);
					if(proyectoDestino != null) texto = proyectoDestino.direccion;
					setCelda(cell,"Arial",10,1,"2b5079",texto,false);
					cell=table.getRow(3).getCell(3);
					if(proyectoDestino != null) texto = proyectoDestino.region + " - " +proyectoDestino.comuna;
					setCelda(cell,"Arial",10,1,"2b5079",texto,false);
				}
			
			table = doc.getTables().get(3);
			
			List<List<String>> detalleGuia = new ArrayList<List<String>>();
			
				if(bodegaOrigen.getEsInterna() == (long)1) {
					detalleGuia = Guia.findDetalleGuiaOrigenDestinoYPrecios(con, db, guia.getId(), guia.getId_bodegaDestino(), mapDiccionario.get("pais"), guia.getId_bodegaOrigen());
				}else {
					detalleGuia = Guia.findDetalleGuiaOrigenDestinoYPrecios(con, db, guia.getId(), guia.getId_bodegaOrigen(), mapDiccionario.get("pais"), guia.getId_bodegaOrigen());
				}
				
				Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
				
				Map<String,String> mapEstadoLinea = new HashMap<String,String>();
				for(int i=0; form.estados!=null && i<form.id_equipo.size(); i++) {
					//id_equip_id_coti = estados
					mapEstadoLinea.put(form.id_equipo.get(i)+"_"+form.id_cotizacion.get(i), form.estados.get(i));
				}
			
				Double granTotalUnidades=(double)0;
    			Double granTotalPrecio=(double)0;
    			Double granTotalPeso=(double)0;
    			Double granTotalM2=(double)0;
    			
    			Double granTotalBueno=(double)0;
    			Double granTotalEst1=(double)0;
    			Double granTotalEst2=(double)0;
    			Double granTotalEst3=(double)0;
    			Double granTotalEst4=(double)0;
    			Double granTotalEst5=(double)0;
    			Double granTotalEst6=(double)0;
    			
    			Map<String,Double> mapPrecioRepos = new HashMap<String,Double>();
    			
    			int contLinea = 0;
    			
    			if(mapeoPermiso.get("parametro.notaSalidaConArriendo")!=null && mapeoPermiso.get("parametro.notaSalidaConArriendo").equals("1") && (long)bodegaOrigen.getEsInterna()==(long)1 ){
					row = table.getRow(0);
					texto = mapDiccionario.get("ARRIENDO");
					setCelda(row.getCell(4),"Arial",8,2,"000000",texto,false);
					setCelda(row.getCell(5),"Arial",8,2,"000000",texto,false);
				}
    			
				for(int i=0; i<detalleGuia.size(); i++) {
					
					// DETERMINA CANTIDAD DE DECIMALES SEGUN MONEDA
	    			Long id_moneda = Long.parseLong(detalleGuia.get(i).get(30).trim());
					if(id_moneda == 0) {id_moneda=(long)1;}
					Long decimal = dec.get(id_moneda);
					if(decimal == null) {decimal=(long)0;}
					switch(decimal.toString()) {
					 case "0": myformatdouble = new DecimalFormat("#,##0"); break;
					 case "2": myformatdouble = new DecimalFormat("#,##0.00"); break;
					 case "4": myformatdouble = new DecimalFormat("#,##0.0000"); break;
					 case "6": myformatdouble = new DecimalFormat("#,##0.000000"); break;
					 default:  break;
					}
					// FIN DETERMINA CANTIDAD DE DECIMALES SEGUN MONEDA
					
					String m2 = detalleGuia.get(i).get(28).trim();
					if( mapeoPermiso.get("parametro.escondeLosM2")!=null && mapeoPermiso.get("parametro.escondeLosM2").equals("1")) {
						m2 = "";
					}
					
					texto = "";
					String cantStr = detalleGuia.get(i).get(8).trim(); 
					if(cantStr.trim().equals("")) {
						cantStr = "0";
					}
					Double cantDbl = Double.parseDouble(cantStr.replaceAll(",", ""));
					String puStr = detalleGuia.get(i).get(9).trim(); 
					
					if(puStr.trim().equals("")) {
						puStr = "0";
					}
					
					Double puDbl = Double.parseDouble(puStr.replaceAll(",", ""));
					mapPrecioRepos.put(detalleGuia.get(i).get(5), puDbl);
					
    				Long esVenta = Long.parseLong(detalleGuia.get(i).get(20).trim());
    				if(mapeoPermiso.get("parametro.notaSalidaConArriendo")!=null && mapeoPermiso.get("parametro.notaSalidaConArriendo").equals("1") && (long)bodegaOrigen.getEsInterna()==(long)1 && esVenta==0) {
    					puStr = detalleGuia.get(i).get(16); 
    					if(puStr.trim().equals("")) {
    						puStr = "0";
    						}
    					puDbl = Double.parseDouble(puStr.replaceAll(",", ""));
    				}
    				
    				if(mapeoPermiso.get("parametro.notaSalidaSinPrecios")!=null && mapeoPermiso.get("parametro.notaSalidaSinPrecios").equals("1") && (long)bodegaOrigen.getEsInterna()==(long)1) {
    					puDbl = (double)0;
    					puStr = myformatdouble.format(puDbl);
    				}
    				
	    			Double totalPrecio = puDbl * cantDbl;
	    			
					//ESTADOS
					Double est1 = (double)  0; // id_tipoEstado = 3
					Double est2 = (double)  0; // id_tipoEstado = 1
					Double est3 = (double)  0; // id_tipoEstado = 2
					Double est4 = (double)  0; // id_tipoEstado = 4
					Double est5 = (double)  0; // id_tipoEstado = 5
					Double est6 = (double)  0; // id_tipoEstado = 6
					
	    			String estado = mapEstadoLinea.get(detalleGuia.get(i).get(2).trim()+"_"+detalleGuia.get(i).get(23).trim());
	    			
	    			if(estado==null) { 
	    				estado = "";
	    			}
	    			
					String[] estNiv1 = estado.split(";");
					
					for(int j=0; j<estNiv1.length; j++) {
						String[] estNiv2 = estNiv1[j].split(":");
						if(estNiv2.length>0) {
							if(estNiv2[0].equals("3")) { est1 = Double.parseDouble(estNiv2[1].replaceAll(",", "").trim()); }
							if(estNiv2[0].equals("1")) { est2 = Double.parseDouble(estNiv2[1].replaceAll(",", "").trim()); }
							if(estNiv2[0].equals("2")) { est3 = Double.parseDouble(estNiv2[1].replaceAll(",", "").trim()); }
							if(estNiv2[0].equals("4")) { est4 = Double.parseDouble(estNiv2[1].replaceAll(",", "").trim()); }
							if(estNiv2[0].equals("5")) { est5 = Double.parseDouble(estNiv2[1].replaceAll(",", "").trim()); }
							if(estNiv2[0].equals("6")) { est6 = Double.parseDouble(estNiv2[1].replaceAll(",", "").trim()); }
						}
					}
					
					Double bueno = cantDbl - est1 - est2 - est3;
					
						//AJUSTE ESPECIAL A EMPRESAS:
		    				if(mapDiccionario.get("nEmpresa").equals("GFS")) {
		    					bueno = cantDbl - est1 - est2 - est3 - est4 - est5;
		    				}
		    				if(mapDiccionario.get("nEmpresa").equals("ALZATEC")) {
		    					bueno = cantDbl - est1 - est2 - est3 - est4 - est5 - est6;
		    					Double auxlim = est1;
		    					Double auxrep = est2;
		    					
		    					est1 = est3;
		    					est2 = auxlim;
		    					est3 = auxrep;
		    					
		    				}
		    				if(mapDiccionario.get("nEmpresa").equals("HOHE")) {
		    					bueno = est1;
		    					est1 = est3;
		    					est3 = est4;
		    				}
		    				
	    				//FIN AJUSTE ESPECIAL A EMPRESAS
						
	    			//FIN ESTADOS
					//********************************
	    				
					if((long)bodegaOrigen.getEsInterna()==(long)1) {
						//GUIA DE SALIDA (DESPACHO DE EQUIPOS A CLIENTE)
						if((double) cantDbl > (double) 0) {
							contLinea++;
							row = table.getRow(contLinea);
							cell = row.getCell(0);
							texto = detalleGuia.get(i).get(5).trim();
							setCelda(cell,"Arial",8,2,"2b5079",texto,false);
							
		    				cell = row.getCell(1);
		    				texto = detalleGuia.get(i).get(6).trim();
		    				
		    				if(esVenta==0) {
		    					if(texto.length()>35) {
			    					texto = texto.substring(0,35);
			    				}
		    				}else {
		    					if(texto.length()>28) {
			    					texto = texto.substring(0,28);
			    				}
		    					texto += " (VENTA)";
		    				}
		    				
		    				setCelda(cell,"Arial",8,1,"2b5079",texto,false);
		    				
		    				
		    				
			   				cell = row.getCell(2);
			   				texto = detalleGuia.get(i).get(7);
			   				setCelda(cell,"Arial",8,2,"2b5079",texto,false);
			   				cell = row.getCell(3);
		    				texto = myformatdouble0.format(cantDbl);
		    				setCelda(cell,"Arial",8,3,"2b5079",texto,false);
		    				
		    				
		    				if(mapDiccionario.get("nEmpresa").equals("SM8 DE MEXICO")) {
		    					cell = row.getCell(4);
			    				texto = detalleGuia.get(i).get(27).trim();
			    				setCelda(cell,"Arial",8,3,"2b5079",texto,false);
			    				
			    				cell = row.getCell(5);
			    				texto = m2;
			    				setCelda(cell,"Arial",8,3,"2b5079",texto,false);
		    				}else {
		    					cell = row.getCell(4);
			    				texto = puStr;
			    				setCelda(cell,"Arial",8,3,"2b5079",texto,false);
			    				
			    				cell = row.getCell(5);
			    				texto = myformatdouble.format(totalPrecio);
			    				setCelda(cell,"Arial",8,3,"2b5079",texto,false);
			    				
			    				cell = row.getCell(6);
			    				texto = detalleGuia.get(i).get(27).trim();
			    				setCelda(cell,"Arial",8,3,"2b5079",texto,false);
			    				
			    				cell = row.getCell(7);
			    				texto = m2;
			    				setCelda(cell,"Arial",8,3,"2b5079",texto,false);
		    				}
		    				
		    				granTotalUnidades += cantDbl;
		    				granTotalPrecio += totalPrecio;
		    				granTotalPeso += Double.parseDouble(detalleGuia.get(i).get(27).replaceAll(",", "").trim());
		    				if( ! m2.equals("")) {
		    					granTotalM2 += Double.parseDouble(m2.replaceAll(",", "").trim());
		    				}
		    				
		    				
		    				table.createRow();
						}
					}else {
						//GUIA DE ENTRADA (DEVOLUCION DE EQUIPOS)
						if((double) cantDbl > (double) 0) {
							contLinea++;
							row = table.getRow(contLinea);
							cell = row.getCell(0);
							texto = detalleGuia.get(i).get(5).trim();
							setCelda(cell,"Arial",8,2,"2b5079",texto,false);
		    				cell = row.getCell(1);
		    				texto = detalleGuia.get(i).get(6).trim();
		    				
		    				if(texto.length()>35) {
		    					texto = texto.substring(0,35);
		    				}
		    				
		    				setCelda(cell,"Arial",8,1,"2b5079",texto,false);
		    				
		    				cell = row.getCell(2);
		    				texto = myformatdouble0.format(bueno);
		    				setCelda(cell,"Arial",8,3,"2b5079",texto,false);
			   				cell = row.getCell(3);
			   				texto = myformatdouble0.format(est1);
			   				setCelda(cell,"Arial",8,3,"2b5079",texto,false);
			   				cell = row.getCell(4);
			   				texto = myformatdouble0.format(est2);
			   				setCelda(cell,"Arial",8,3,"2b5079",texto,false);
			   				cell = row.getCell(5);
			   				texto = myformatdouble0.format(est3);
			   				if(mapDiccionario.get("nEmpresa").equals("MDP ANDAMIOS")) {
			   					texto = "";
			   				}
			   				setCelda(cell,"Arial",8,3,"2b5079",texto,false);
			   				
			   				cell = row.getCell(6);
			   				texto = myformatdouble0.format(cantDbl);
			   				setCelda(cell,"Arial",8,3,"2b5079",texto,false);
		    				
			   				cell = row.getCell(7);
		    				texto = detalleGuia.get(i).get(27).trim();
		    				setCelda(cell,"Arial",8,3,"2b5079",texto,false);
		    				cell = row.getCell(8);
		    				texto = m2;
		    				setCelda(cell,"Arial",8,3,"2b5079",texto,false);
		    				
		    				if(mapeoPermiso.get("parametro.movimiento-devolucion-cantCliente")!=null && mapeoPermiso.get("parametro.movimiento-devolucion-cantCliente").equals("1")){
		    					cell = row.getCell(9);
			    				texto = detalleGuia.get(i).get(34).trim();
			    				setCelda(cell,"Arial",8,3,"2b5079",texto,false);
			    				cell = row.getCell(10);
			    				texto = detalleGuia.get(i).get(35).trim();
			    				setCelda(cell,"Arial",8,3,"2b5079",texto,false);
		    				}
		    				
		    				//AJUSTE ESPECIAL A EMPRESAS:
		    				if(mapDiccionario.get("nEmpresa").equals("GFS")) {
		    					cell = row.getCell(6);
		    					texto = myformatdouble0.format(est4);
		    					setCelda(cell,"Arial",8,3,"2b5079",texto,false);
		    					cell = row.getCell(7);
		    					texto = myformatdouble0.format(est5);
		    					setCelda(cell,"Arial",8,3,"2b5079",texto,false);
		    					
		    					cell = row.getCell(8);
		    					texto = myformatdouble0.format(cantDbl);
		    					setCelda(cell,"Arial",8,3,"2b5079",texto,false);
		    					cell = row.getCell(9);
		    					texto = detalleGuia.get(i).get(27).trim();
		    					setCelda(cell,"Arial",8,3,"2b5079",texto,false);
			    				cell = row.getCell(10);
			    				texto = m2;
			    				setCelda(cell,"Arial",8,3,"2b5079",texto,false);
			    				
			    				granTotalEst4 += est4;
			        			granTotalEst5 += est5;
		    				}
		    				if(mapDiccionario.get("nEmpresa").equals("ALZATEC")) {
		    					
		    					cell = row.getCell(4);
				   				texto = myformatdouble0.format(est3);
				   				setCelda(cell,"Arial",8,3,"2b5079",texto,false);
				   				
				   				cell = row.getCell(5);
				   				texto = myformatdouble0.format(est2);
				   				setCelda(cell,"Arial",8,3,"2b5079",texto,false);
				   				
				   				
		    					cell = row.getCell(6);
		    					texto = myformatdouble0.format(est4);
		    					setCelda(cell,"Arial",8,3,"2b5079",texto,false);
		    					
		    					cell = row.getCell(7);
		    					texto = myformatdouble0.format(est5);
		    					setCelda(cell,"Arial",8,3,"2b5079",texto,false);
		    					
		    					cell = row.getCell(8);
		    					texto = myformatdouble0.format(est6);
		    					setCelda(cell,"Arial",8,3,"2b5079",texto,false);
		    					
		    					cell = row.getCell(9);
		    					texto = myformatdouble0.format(cantDbl);
		    					setCelda(cell,"Arial",8,3,"2b5079",texto,false);
		    					
		    					cell = row.getCell(10);
		    					texto = detalleGuia.get(i).get(27).trim();
		    					setCelda(cell,"Arial",8,3,"2b5079",texto,false);
		    					
			    				cell = row.getCell(11);
			    				texto =m2;
			    				setCelda(cell,"Arial",8,3,"2b5079",texto,false);
			    				
			    				granTotalEst4 += est4;
			    				granTotalEst5 += est5;
			        			granTotalEst6 += est6;
		    				}
		    				//FIN AJUSTE
		    				
		    				granTotalUnidades += cantDbl;
		    				granTotalPrecio += granTotalPrecio;
		    				granTotalPeso += Double.parseDouble(detalleGuia.get(i).get(27).replaceAll(",", "").trim());
		    				
		    				if( ! m2.equals("")) {
		    					granTotalM2 += Double.parseDouble(m2.replaceAll(",", "").trim());
		    				}
		    				
		    				
		    				granTotalBueno += bueno;
		    				granTotalEst1 += est1;
		    				granTotalEst2 += est2;
		    				granTotalEst3 += est3;
		    				
		        			
		    				table.createRow();
						}
					}
				} //end for
				
				if((long)bodegaOrigen.getEsInterna()==(long)1) {
					//GUIA DE SALIDA (DESPACHO DE EQUIPOS A CLIENTE)
					row = table.getRow(table.getNumberOfRows()-1);
					cell = row.getCell(1);
	    			texto = "TOTALES";
	    			setCelda(cell,"Arial",8,1,"2b5079",texto,false);
	    			cell = row.getCell(3);
	    			texto = myformatdouble0.format(granTotalUnidades);
	    			setCelda(cell,"Arial",8,3,"2b5079",texto,false);
	    			
	    			
	    			if(mapDiccionario.get("nEmpresa").equals("SM8 DE MEXICO")) {
	    				cell=row.getCell(4);
		    			texto = myformatdouble0.format(granTotalPeso);
		    			setCelda(cell,"Arial",8,3,"2b5079",texto,false);
		    			
		    			cell=row.getCell(5);
		    			texto = myformatdouble0.format(granTotalM2);
		    			if( mapeoPermiso.get("parametro.escondeLosM2")!=null && mapeoPermiso.get("parametro.escondeLosM2").equals("1")) {
		    				texto = "";
		    			}
		    			setCelda(cell,"Arial",8,3,"2b5079",texto,false);
		    			
	    			}else {
	    				cell = row.getCell(5);
		    			texto = myformatdouble.format(granTotalPrecio);
		    			setCelda(cell,"Arial",8,3,"2b5079",texto,false);
		    			
		    			cell=row.getCell(6);
		    			texto = myformatdouble0.format(granTotalPeso);
		    			setCelda(cell,"Arial",8,3,"2b5079",texto,false);
		    			
		    			cell=row.getCell(7);
		    			texto = myformatdouble0.format(granTotalM2);
		    			if( mapeoPermiso.get("parametro.escondeLosM2")!=null && mapeoPermiso.get("parametro.escondeLosM2").equals("1")) {
		    				texto = "";
		    			}
		    			setCelda(cell,"Arial",8,3,"2b5079",texto,false);
	    			}
	    			
	    			
					
					
				}else {
					//GUIA DE ENTRADA (DEVOLUCION DE EQUIPOS)
					row = table.getRow(table.getNumberOfRows()-1);
	    			cell = row.getCell(1);
	    			texto = "TOTALES";
	    			setCelda(cell,"Arial",8,1,"2b5079",texto,false);
	    			
	    			
	    			cell = row.getCell(2);
	    			texto = myformatdouble0.format(granTotalBueno);
	    			setCelda(cell,"Arial",8,3,"2b5079",texto,false);
	    			cell = row.getCell(3);
	    			texto = myformatdouble0.format(granTotalEst1);
	    			setCelda(cell,"Arial",8,3,"2b5079",texto,false);
	    			
	    			cell = row.getCell(4);
	    			texto = myformatdouble0.format(granTotalEst2);
	    			setCelda(cell,"Arial",8,3,"2b5079",texto,false);
	    			
	    			cell = row.getCell(5);
	    			texto = myformatdouble0.format(granTotalEst3);
	    			if(mapDiccionario.get("nEmpresa").equals("MDP ANDAMIOS")) {
	   					texto = "";
	   				}
	    			setCelda(cell,"Arial",8,3,"2b5079",texto,false);
	    			
	    			
	    			
	    			
	    			
	    			cell = row.getCell(6);
	    			texto = myformatdouble0.format(granTotalUnidades);
	    			setCelda(cell,"Arial",8,3,"2b5079",texto,false);
	    			cell = row.getCell(7);
	    			texto = myformatdouble0.format(granTotalPeso);
	    			setCelda(cell,"Arial",8,3,"2b5079",texto,false);
	    			cell = row.getCell(8);
	    			texto = myformatdouble0.format(granTotalM2);
	    			if( mapeoPermiso.get("parametro.escondeLosM2")!=null && mapeoPermiso.get("parametro.escondeLosM2").equals("1")) {
	    				texto = "";
	    			}
	    			setCelda(cell,"Arial",8,3,"2b5079",texto,false);
	    			
	    			//AJUSTE ESPECIAL A EMPRESAS:
    				if(mapDiccionario.get("nEmpresa").equals("GFS")) {
    					cell = row.getCell(6);
    					texto = myformatdouble0.format(granTotalEst4);
    	    			setCelda(cell,"Arial",8,3,"2b5079",texto,false);
    	    			cell = row.getCell(7);
    	    			texto = myformatdouble0.format(granTotalEst5);
    	    			setCelda(cell,"Arial",8,3,"2b5079",texto,false);
    	    			
    	    			setCelda(cell,"Arial",8,3,"2b5079",texto,false);
    	    			cell = row.getCell(8);
    	    			texto = myformatdouble0.format(granTotalUnidades);
    	    			setCelda(cell,"Arial",8,3,"2b5079",texto,false);
    	    			cell = row.getCell(9);
    	    			texto = myformatdouble0.format(granTotalPeso);
    	    			setCelda(cell,"Arial",8,3,"2b5079",texto,false);
    	    			cell = row.getCell(10);
    	    			texto = myformatdouble0.format(granTotalM2);
    	    			if( mapeoPermiso.get("parametro.escondeLosM2")!=null && mapeoPermiso.get("parametro.escondeLosM2").equals("1")) {
		    				texto = "";
		    			}
    	    			setCelda(cell,"Arial",8,3,"2b5079",texto,false);
    				}
    				
    				if(mapDiccionario.get("nEmpresa").equals("ALZATEC")) {
    					
    					cell = row.getCell(4);
    	    			texto = myformatdouble0.format(granTotalEst3);
    	    			setCelda(cell,"Arial",8,3,"2b5079",texto,false);
    	    			
    	    			cell = row.getCell(5);
    	    			texto = myformatdouble0.format(granTotalEst2);
    	    			setCelda(cell,"Arial",8,3,"2b5079",texto,false);
    	    			
    					cell = row.getCell(6);
    					texto = myformatdouble0.format(granTotalEst4);
    	    			setCelda(cell,"Arial",8,3,"2b5079",texto,false);
    	    			
    	    			cell = row.getCell(7);
    					texto = myformatdouble0.format(granTotalEst5);
    	    			setCelda(cell,"Arial",8,3,"2b5079",texto,false);
    	    			
    	    			cell = row.getCell(8);
    					texto = myformatdouble0.format(granTotalEst6);
    	    			setCelda(cell,"Arial",8,3,"2b5079",texto,false);
    	    			
    	    			setCelda(cell,"Arial",8,3,"2b5079",texto,false);
    	    			cell = row.getCell(9);
    	    			texto = myformatdouble0.format(granTotalUnidades);
    	    			setCelda(cell,"Arial",8,3,"2b5079",texto,false);
    	    			cell = row.getCell(10);
    	    			texto = myformatdouble0.format(granTotalPeso);
    	    			setCelda(cell,"Arial",8,3,"2b5079",texto,false);
    	    			cell = row.getCell(11);
    	    			texto = myformatdouble0.format(granTotalM2);
    	    			if( mapeoPermiso.get("parametro.escondeLosM2")!=null && mapeoPermiso.get("parametro.escondeLosM2").equals("1")) {
		    				texto = "";
		    			}
    	    			setCelda(cell,"Arial",8,3,"2b5079",texto,false);
    				}
    				
    				
    				//FIN AJUSTE
	    			
				}
				
				table = doc.getTables().get(4);
	            row=table.getRow(0);
    			cell = row.getCell(0);setCelda(cell,"Arial",10,1,"2b5079",form.observaciones,false);
    			
    			Transportista transp = Transportista.find(con, db, form.id_transportista);
    			
    			if(transp!=null) {
    				table = doc.getTables().get(5);
    				
    				
    				row=table.getRow(1);
    				cell = row.getCell(1);
    				setCelda(cell,"Arial",8,1,"2b5079",transp.rutConductor,false);
    				row=table.getRow(2);
    				cell = row.getCell(1);
    				setCelda(cell,"Arial",8,1,"2b5079",transp.conductor,false);
    				row=table.getRow(3);
    				cell = row.getCell(1);
    				setCelda(cell,"Arial",8,1,"2b5079",transp.patente,false);
    				row=table.getRow(4);
    				cell = row.getCell(1);
    				setCelda(cell,"Arial",8,1,"2b5079",transp.fonoContacto,false);
    				
    				
    			}
    			
    			
    			
    			if(mapeoPermiso.get("parametro.devoluciones-con-estados")!=null && mapeoPermiso.get("parametro.devoluciones-con-estados").equals("1")){
    				
    				if((long)bodegaOrigen.getEsInterna()==(long)2) {
    					
    					String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, db, id_usuario);
    					List<List<String>> reportInventarioPorEstadosPorNroGuia = ReportInventarios.reportInventarioPorEstadosPorNroGuia(con, db, permisoPorBodega, guia.getId());
    					
    					Map<String,Long> mapDecimal = Moneda.numeroDecimalxNombre(con, db);
    					Map<Long,TipoEstado> mapTipoEstado = TipoEstado.mapAll(con, db);
    					
    					List<List<String>> lista = new ArrayList<List<String>>();
    						
    					for(List<String> x : reportInventarioPorEstadosPorNroGuia) {
    						
    						TipoEstado tipoEstado = mapTipoEstado.get(Long.parseLong(x.get(15)));
    						
    						if(tipoEstado!=null) {
    							if((long)tipoEstado.getReparable() == (long)1 && (long)tipoEstado.getValoriza() == (long)1) {
    								
    								Double precio = Double.parseDouble(x.get(14).replaceAll(",", ""));
        							String moneda = x.get(13);
        							Double cant = Double.parseDouble(x.get(12).replaceAll(",", ""));
    								Double total = cant * precio;
    								if(moneda != null) {
    									moneda = moneda.toUpperCase();
    								}
    								Long nroDec = mapDecimal.get(moneda);
    								if(nroDec==null) {
    									nroDec = (long)0;
    								}
        							x.add(DecimalFormato.formato(total, nroDec));  // 16 total
        							lista.add(x);
        							
    							}else if((long)tipoEstado.getReparable() == (long)0 && (long)tipoEstado.getValoriza() == (long)1) {
    								Double precio = Double.parseDouble(x.get(7).replaceAll(",", ""));
    								String moneda = x.get(6);
    								Double cant = Double.parseDouble(x.get(9).replaceAll(",", ""));
    								Double total = cant * precio;
    								if(moneda != null) {
    									moneda = moneda.toUpperCase();
    								}
    								Long nroDec = mapDecimal.get(moneda);
    								if(nroDec==null) {
    									nroDec = (long)0;
    								}
    								
    								x.set(12, x.get(9));
    								x.set(13, x.get(6));
    								x.set(14, x.get(7));
    								
    								
    								
        							x.add(DecimalFormato.formato(total, nroDec));  // 16 total
        							lista.add(x);
    							}
    						}
    					}
    					
    					if(lista.size()>0) {
    						
    						table = doc.getTables().get(7);
    						
    						contLinea = 1;
    						for(List<String> x : lista) {
    							
    							row = table.getRow(contLinea);
    							cell = row.getCell(0);
    							texto = x.get(3).trim();
    							setCelda(cell,"Arial",8,2,"2b5079",texto,false);
    							cell = row.getCell(1);
    							texto = x.get(8).trim();
    							setCelda(cell,"Arial",8,2,"2b5079",texto,false);
    							cell = row.getCell(2);
    							texto = x.get(10).trim();
    							setCelda(cell,"Arial",8,2,"2b5079",texto,false);
    							cell = row.getCell(3);
    							texto = x.get(11).trim();
    							setCelda(cell,"Arial",8,2,"2b5079",texto,false);
    							cell = row.getCell(4);
    							texto = x.get(12).trim();
    							setCelda(cell,"Arial",8,2,"2b5079",texto,false);
    							cell = row.getCell(5);
    							texto = x.get(13).trim();
    							setCelda(cell,"Arial",8,3,"2b5079",texto,false);
    							cell = row.getCell(6);
    							texto = x.get(14).trim();
    							setCelda(cell,"Arial",8,3,"2b5079",texto,false);
    							cell = row.getCell(7);
    							texto = x.get(16).trim();
    							setCelda(cell,"Arial",8,3,"2b5079",texto,false);
    							
    							table.createRow();
    							contLinea++;
    						}
    					}else {
    						try {
    							table = doc.getTables().get(7);
        	        			int posTabla = doc.getPosOfTable(table);
        	        			doc.removeBodyElement(posTabla);
    	    				} catch (Exception e) {
    	    					
    	    				}
    						
    					}
    					
    				}
    			} else {
    				try {
	    				table = doc.getTables().get(7);
	        			int posTabla = doc.getPosOfTable(table);
	        			doc.removeBodyElement(posTabla);
    				} catch (Exception e) {
    					
    				}
    			}
				
				Guia.modificaPorCampo(con, db, "totalKg", guia.getId(), granTotalPeso.toString());
				
				
				
				// Write the output to a file word
				FileOutputStream fileOut = new FileOutputStream(tmp);
				doc.write(fileOut);
				fileOut.close();

					// 1) Load DOCX into XWPFDocument
					InputStream is = new FileInputStream(tmp);
					XWPFDocument document = new XWPFDocument(is);
					is.close();
					// 2) Prepare Pdf options
					PdfOptions options = PdfOptions.create().fontEncoding("iso-8859-15");
					// 3) Convert XWPFDocument to Pdf
					OutputStream out = new FileOutputStream(tmp);
					PdfConverter.getInstance().convert(document, out, options);
					out.close();

					String archivoPdf = "";
					if((long)bodegaOrigen.getEsInterna()==(long)1) {
						//GUIA DE SALIDA (DESPACHO DE EQUIPOS A CLIENTE)
						archivoPdf = "G_" + form.numeroGuia + "_guiaSalida.pdf";
					}else {
						//GUIA DE ENTRADA (DEVOLUCION DE EQUIPOS)
						archivoPdf = "G_" + guia.numero + "_guiaEntrada.pdf";
					}
					
					path = db+"/"+archivoPdf;
					Archivos.grabarArchivo(tmp,path);
					Guia.modificaPorCampo(con, db, "guiaPDF", guia.getId(), archivoPdf);
					return(archivoPdf);
			
		} catch ( IOException e) {
			e.printStackTrace();
		}
		return("0");
		
		
	}
	
	
	private static void setCelda (XWPFTableCell cell,String fontFamily,int fontSize,int alingH,String colorRGB,String text,boolean bold) {
		cell.removeParagraph(0);
		XWPFParagraph paragraph =null;
		if(text==null) text="-"; else if(text.trim().length()==0) text="-";
		paragraph = cell.addParagraph();
		if(alingH==3) {
			paragraph.setAlignment(ParagraphAlignment.RIGHT);
		}else if (alingH==2) {
			paragraph.setAlignment(ParagraphAlignment.CENTER);
		}else {
			paragraph.setAlignment(ParagraphAlignment.LEFT);
		}
		cell.setVerticalAlignment(XWPFVertAlign.CENTER);
		
		XWPFRun celda = paragraph.createRun();
		celda.setFontFamily(fontFamily);
		celda.setFontSize(fontSize);
		celda.setColor(colorRGB);
		celda.setText(text);
		celda.setBold(bold);
    }
	
	
	
	
}














