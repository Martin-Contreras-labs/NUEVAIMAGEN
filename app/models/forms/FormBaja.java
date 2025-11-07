package models.forms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

import controllers.HomeController;
import models.calculo.Inventarios;
import models.tables.ActaBaja;
import models.tables.Baja;
import models.tables.Cotizacion;
import models.tables.Equipo;
import models.tables.Grupo;
import models.tables.Moneda;
import models.tables.Movimiento;
import models.tables.Precio;
import models.utilities.Archivos;
import models.utilities.Fechas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Files.TemporaryFile;
import play.mvc.Http;

public class FormBaja {
	public Long id_actaBaja;
	public Long numero;
	public String fecha;
	public String observaciones;
	public List<Long> id_equipo;
	public List<String> cantidad;
	public List<String> motivo;

	public FormBaja(Long id_actaBaja, Long numero, String fecha, String observaciones, List<Long> id_equipo,
			List<String> cantidad, List<String> motivo) {
		super();
		this.id_actaBaja = id_actaBaja;
		this.numero = numero;
		this.fecha = fecha;
		this.observaciones = observaciones;
		this.id_equipo = id_equipo;
		this.cantidad = cantidad;
		this.motivo = motivo;
	}

	public FormBaja() {
		super();
	}

	static DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00",symbols);
	static DecimalFormat myformatdoubleCompra = new DecimalFormat("#,##0.00",symbols);

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	public static List<List<String>> listEquipEnBodBaja (Connection con, String db, Map<String,String> mapeoPermiso, Map<String,String> mapeoDiccionario){
		List<List<String>> listEquipEnBodBaja = new ArrayList<List<String>>();
		try {
			Long soloArriendo = (long) 1;
			if(mapeoPermiso.get("parametro.permiteDevolverVentas").equals("1")) {
				soloArriendo = (long) 0;
			}
			Long id_suscursal = 1L;
			Map<Long,Precio> mapPrecio = Precio.mapAll(con, db, mapeoDiccionario, id_suscursal);
			Map<String,Long> decCompra = Moneda.numeroDecimalxNombre(con, db);
			Long id_bodegaEmpresa = 1L;
			Map<String,Movimiento> map = Inventarios.invPorIdBodega(con, db, id_bodegaEmpresa, soloArriendo);
			Map<Long,Grupo> mapGrupo = Grupo.mapAll(con, db);
			Map<Long,Equipo> mapEquipo = Equipo.mapAllAll(con, db);
			Map<Long,Cotizacion> mapCotizacion = Cotizacion.mapAll(con, db);
			map.forEach((k,v)->{
				if(v.getCantidad()>0) {
					Equipo equipo = mapEquipo.get(v.getId_equipo());
					if(equipo!=null) {
						Grupo grupo = mapGrupo.get(equipo.getId_grupo());
						Cotizacion coti = mapCotizacion.get(v.getId_cotizacion());
						Long numCoti = (long) 0; if(coti!=null){numCoti = coti.getNumero();};
						Double kg = equipo.getKg();
						Double m2 = equipo.getM2();
						Precio precio = mapPrecio.get(v.getId_equipo());
						String moneda = "", pcompra = "";
						Double auxPcompra = (double) 0;
						if(precio!=null) {
							moneda = precio.getMonedaCompra();
							pcompra = precio.getPrecioCompra();
							auxPcompra = Double.parseDouble(pcompra.replaceAll(",", ""));
						}
						Double total = v.getCantidad() * auxPcompra;
						if(moneda != null) {
							moneda = moneda.toUpperCase();
						}
						Long auxDecimal = decCompra.get(moneda);
						if(auxDecimal==null) auxDecimal = (long)2;
						switch(auxDecimal.toString()) {
						 case "0": myformatdoubleCompra = new DecimalFormat("#,##0",symbols); break;
						 default : myformatdoubleCompra = new DecimalFormat("#,##0.00",symbols); break;
						}
						List<String> aux = new ArrayList<String>();
						aux.add(v.getId_equipo().toString()); 				// 0 id_equipo
						aux.add(v.getId_cotizacion().toString()); 			// 1 id_cotizacion
						aux.add(grupo.getNombre()); 						// 2 nombre de grupo
						aux.add(numCoti.toString()); 						// 3 numero cotizacion
						aux.add(equipo.getCodigo()); 						// 4 codigo de equipo
						aux.add(equipo.getNombre()); 						// 5 nombre de equipo
						aux.add(myformatdouble2.format(kg)); 				// 6 KG por equipo
						aux.add(myformatdouble2.format(m2)); 				// 7 M2 por equipo
						aux.add(equipo.getUnidad());						// 8 unidad
						aux.add(myformatdouble2.format(v.getCantidad()));	// 9 stock disponible
						aux.add(moneda);									// 10 moneda de compra
						aux.add(pcompra);									// 11 precio de compra
						aux.add(myformatdoubleCompra.format(total));		// 12 total a pcompra
						listEquipEnBodBaja.add(aux);
					}
				}
			});
			//ORDENA LA LISTA
			for(int j=0;j<listEquipEnBodBaja.size();j++) {
				for(int i=0;i<listEquipEnBodBaja.size()-j;i++) {
					if (i+1!=listEquipEnBodBaja.size()&&listEquipEnBodBaja.get(i).get(5).compareTo(listEquipEnBodBaja.get(i+1).get(5))>0) {
						List<String> aux;
						aux=listEquipEnBodBaja.get(i);
						listEquipEnBodBaja.set(i,listEquipEnBodBaja.get(i+1));
						listEquipEnBodBaja.set(i+1, aux);
					}
				}
			}
		} catch (Exception e) {
			String className = FormBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (listEquipEnBodBaja);
	}
	
	public static boolean create (Connection con, String db, Map<String,String> mapeoPermiso, FormBaja form, Http.MultipartFormData.FilePart<TemporaryFile> docAdjunto) {
		boolean flag = false;
		try {
			Long soloArriendo = (long) 1;
			if(mapeoPermiso.get("parametro.permiteDevolverVentas").equals("1")) {
				soloArriendo = (long) 0;
			}
			Map<String,Movimiento> map = Inventarios.invPorIdBodega(con, db, (long)1, soloArriendo);
			for(int i=0; i<form.id_equipo.size(); i++) {
				Movimiento mov = map.get(form.id_equipo.get(i).toString()+"_0");
				Double cant = (double)0;
				if(mov!=null) {
					cant = mov.getCantidad();
					Double aux = Double.parseDouble(form.cantidad.get(i).replaceAll(",", ""));
					if((double) aux > (double) cant && (double) cant > (double) 0) {
						form.cantidad.set(i,cant.toString());
					}
				}
			}
			ActaBaja actaBaja = new ActaBaja(form);
			Long id_actaBaja = ActaBaja.create(con, db, actaBaja);
			if(id_actaBaja>0) {
				String detalle = "";
				for(int i=0; form.id_equipo!=null && i<form.id_equipo.size(); i++) {
					Double aux = Double.parseDouble(form.cantidad.get(i).replaceAll(",", ""));
					if((double) aux > (double)0) {
						detalle += "('"+id_actaBaja+"','"+form.id_equipo.get(i)+"','"+form.cantidad.get(i).replaceAll(",", "")+"','"+form.motivo.get(i)+"'),";
					}
				}
				if(form.id_equipo!=null) {
					if(detalle.length()>10) {
						detalle = detalle.substring(0,detalle.length()-1);
						if(!Baja.create(con, db, detalle)) {
							ActaBaja.delete(con, db, id_actaBaja);
						}else {
							String path = "0";
							if (docAdjunto != null) {
								String nombreArchivoSinExtencion = "Doc_Baja_" + id_actaBaja;
								path = Archivos.grabarArchivos(docAdjunto, db, nombreArchivoSinExtencion);
								ActaBaja.modifyXCampo(con, db, "actaBajaPDF", path, id_actaBaja);
							}
							flag = true;
						}
					}else {
						ActaBaja.delete(con, db, id_actaBaja);
					}
				}
			}
		} catch (Exception e) {
			String className = FormBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return(flag);
	}
	
	public static List<List<String>> detalleDeLaBaja (Connection con, String db, Map<String,String> mapeoPermiso, Map<String,String> mapeoDiccionario, Long id_actaBaja, Map<Long, Baja> mapDetalleBajaModifica){
		List<List<String>> detalle = new ArrayList<List<String>>();
		try {
			List<List<String>> listEquipBodOrigen = FormBaja.listEquipEnBodBaja(con, db, mapeoPermiso, mapeoDiccionario);
			Map<Long, List<String>> mapEquipBodOrigen = new HashMap<Long,List<String>>();
			Map<Long,Long> mapId_equipos = new HashMap<Long,Long>();
			mapDetalleBajaModifica.forEach((k,v)->{
				mapId_equipos.put(k, k);
			});
			listEquipBodOrigen.forEach(x->{
				mapEquipBodOrigen.put(Long.parseLong(x.get(0)), x);
				mapId_equipos.put(Long.parseLong(x.get(0)), Long.parseLong(x.get(0)));
			});
			Map<Long,Equipo> mapEquipo = Equipo.mapAllAll(con, db);
			Map<Long,Precio> mapPrecio = Precio.mapAll(con, db, mapeoDiccionario, (long)1);
			mapId_equipos.forEach((k,v)->{
				Baja baja = mapDetalleBajaModifica.get(k);
				List<String> bodOrigen = mapEquipBodOrigen.get(k);
				Map<String,Long> decCompra = Moneda.numeroDecimalxNombre(con, db);
				if(baja == null && bodOrigen != null) {
					String moneda = bodOrigen.get(10);
					if(moneda != null) {
						moneda = moneda.toUpperCase();
					}
					Long auxDecimal = decCompra.get(moneda);
					if(auxDecimal==null) auxDecimal = (long)2;
					switch(auxDecimal.toString()) {
					 case "0": myformatdoubleCompra = new DecimalFormat("#,##0",symbols); break;
					 default : myformatdoubleCompra = new DecimalFormat("#,##0.00",symbols); break;
					}
					String totalPcXcant = myformatdoubleCompra.format((double)0);
					List<String> aux = new ArrayList<String>();
					aux.add(bodOrigen.get(0)); 		// 0 id_equipo
					aux.add(bodOrigen.get(1)); 		// 1 id_cotizacion
					aux.add(bodOrigen.get(2)); 		// 2 nombre de grupo
					aux.add(bodOrigen.get(3)); 		// 3 numero cotizacion
					aux.add(bodOrigen.get(4)); 		// 4 codigo de equipo
					aux.add(bodOrigen.get(5)); 		// 5 nombre de equipo
					aux.add(bodOrigen.get(6)); 		// 6 KG por equipo
					aux.add(bodOrigen.get(7)); 		// 7 M2 por equipo
					aux.add(bodOrigen.get(8));		// 8 unidad
					aux.add(bodOrigen.get(9));		// 9 stock disponible
					aux.add(bodOrigen.get(10));		// 10 moneda de compra
					aux.add(bodOrigen.get(11));		// 11 precio de compra
					aux.add(bodOrigen.get(12));		// 12 total a pcompra por stock
					aux.add("0.00");				// 13 cantidad
					aux.add("");					// 14 motivo
					aux.add("");					// 15 confirmada
					aux.add(totalPcXcant);			// 16 total a pcompra por cantidad
					detalle.add(aux);
				} else if(baja != null && bodOrigen != null) {
					Double auxStock = Double.parseDouble(bodOrigen.get(9).replaceAll(",", ""));
					Double auxPcompra = Double.parseDouble(bodOrigen.get(11).replaceAll(",", ""));
					Double cant = baja.getCantidad();
					Double stock = auxStock + cant;
					Double pcXstock = stock * auxPcompra;
					Double pcXcant = cant * auxPcompra;
					String moneda = bodOrigen.get(10);
					if(moneda != null) {
						moneda = moneda.toUpperCase();
					}
					Long auxDecimal = decCompra.get(moneda);
					if(auxDecimal==null) auxDecimal = (long)2;
					switch(auxDecimal.toString()) {
					 case "0": myformatdoubleCompra = new DecimalFormat("#,##0",symbols); break;
					 default : myformatdoubleCompra = new DecimalFormat("#,##0.00",symbols); break;
					}
					String totalPcXstock = myformatdoubleCompra.format(pcXstock);
					String totalPcXcant = myformatdoubleCompra.format(pcXcant);
					if(baja.getFechaConfirma() != null) {
						baja.setFechaConfirma(Fechas.DDMMAA(baja.getFechaConfirma()));
					}
					List<String> aux = new ArrayList<String>();
					aux.add(bodOrigen.get(0)); 					// 0 id_equipo
					aux.add(bodOrigen.get(1)); 					// 1 id_cotizacion
					aux.add(bodOrigen.get(2)); 					// 2 nombre de grupo
					aux.add(bodOrigen.get(3)); 					// 3 numero cotizacion
					aux.add(bodOrigen.get(4)); 					// 4 codigo de equipo
					aux.add(bodOrigen.get(5)); 					// 5 nombre de equipo
					aux.add(bodOrigen.get(6)); 					// 6 KG por equipo
					aux.add(bodOrigen.get(7)); 					// 7 M2 por equipo
					aux.add(bodOrigen.get(8));					// 8 unidad
					aux.add(myformatdouble2.format(stock));		// 9 stock disponible
					aux.add(bodOrigen.get(10));					// 10 moneda de compra
					aux.add(bodOrigen.get(11));					// 11 precio de compra
					aux.add(totalPcXstock);						// 12 total a pcompra por stock
					aux.add(myformatdouble2.format(cant));		// 13 cantidad
					aux.add(baja.getMotivo());					// 14 motivo
					aux.add(baja.getFechaConfirma());			// 15 confirmada
					aux.add(totalPcXcant);						// 16 total a pcompra por cantidad
					detalle.add(aux);
				} else if(baja != null && bodOrigen == null) {
					Equipo equipo = mapEquipo.get(k);
					if(equipo!=null) {
						Double kg = equipo.getKg();
						Double m2 = equipo.getM2();
						Double stock = baja.getCantidad();
						Double cant = stock;
						Precio precio = mapPrecio.get(k);
						String mon = "";
						String pcompra = "0.00";
						Double total = (double)0;
						if(precio!=null) {
							mon = precio.getMonedaCompra();
							pcompra = precio.getPrecioCompra();
							total = stock * Double.parseDouble(pcompra.replaceAll(",", ""));
						}
						if(mon != null) {
							mon = mon.toUpperCase();
						}
						Long auxDecimal = decCompra.get(mon);
						if(auxDecimal==null) auxDecimal = (long)2;
						switch(auxDecimal.toString()) {
						 case "0": myformatdoubleCompra = new DecimalFormat("#,##0",symbols); break;
						 default : myformatdoubleCompra = new DecimalFormat("#,##0.00",symbols); break;
						}
						String totalPcXstock = myformatdoubleCompra.format(total);
						String totalPcXcant = myformatdoubleCompra.format(total);
						if(baja.getFechaConfirma() != null) {
							baja.setFechaConfirma(Fechas.DDMMAA(baja.getFechaConfirma()));
						}
						List<String> aux = new ArrayList<String>();
						aux.add(baja.getId_equipo().toString()); 	// 0 id_equipo
						aux.add("0"); 								// 1 id_cotizacion
						aux.add(equipo.getGrupo()); 				// 2 nombre de grupo
						aux.add("0"); 								// 3 numero cotizacion
						aux.add(equipo.getCodigo()); 				// 4 codigo de equipo
						aux.add(equipo.getNombre()); 				// 5 nombre de equipo
						aux.add(myformatdouble2.format(kg)); 		// 6 KG por equipo
						aux.add(myformatdouble2.format(m2)); 		// 7 M2 por equipo
						aux.add(equipo.getUnidad());				// 8 unidad
						aux.add(myformatdouble2.format(stock));		// 9 stock disponible
						aux.add(mon);								// 10 moneda de compra
						aux.add(pcompra);							// 11 precio de compra
						aux.add(totalPcXstock);						// 12 total a pcompra por stock
						aux.add(myformatdouble2.format(cant));		// 13 cantidad
						aux.add(baja.getMotivo());					// 14 motivo
						aux.add(baja.getFechaConfirma());			// 15 confirmada
						aux.add(totalPcXcant);						// 16 total a pcompra por cantidad
						detalle.add(aux);
					}
				}
			});
			//ORDENA LA LISTA
			for(int j=0;j<detalle.size();j++) {
				for(int i=0;i<detalle.size()-j;i++) {
					if (i+1!=detalle.size()&&detalle.get(i).get(5).compareTo(detalle.get(i+1).get(5))>0) {
						List<String> aux;
						aux=detalle.get(i);
						detalle.set(i,detalle.get(i+1));
						detalle.set(i+1, aux);
					}
				}
			}
		} catch (Exception e) {
			String className = FormBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return(detalle);
	}
	
	public static boolean update (Connection con, String db, Map<String,String> mapeoPermiso, FormBaja form, Http.MultipartFormData.FilePart<TemporaryFile> docAdjunto) {
		boolean flag = false;
		try {
			Long soloArriendo = (long) 1;
			if(mapeoPermiso.get("parametro.permiteDevolverVentas").equals("1")) {
				soloArriendo = (long) 0;
			}
			Map<String,Movimiento> map = Inventarios.invPorIdBodega(con, db, (long)1, soloArriendo);
			for(int i=0; i<form.id_equipo.size(); i++) {
				Movimiento mov = map.get(form.id_equipo.get(i).toString()+"_0");
				Double cant = (double)0;
				if(mov!=null) {
					cant = mov.getCantidad();
					Double aux = Double.parseDouble(form.cantidad.get(i).replaceAll(",", ""));
					if((double) aux > (double) cant && (double) cant > (double) 0) {
						form.cantidad.set(i,cant.toString());
					}
				}
			}
			Long id_actaBaja = form.id_actaBaja;
			if(id_actaBaja>0) {
				String detalle = "";
				for(int i=0; form.id_equipo!=null && i<form.id_equipo.size(); i++) {
					Double aux = Double.parseDouble(form.cantidad.get(i).replaceAll(",", ""));
					if((double) aux > (double)0) {
						detalle += "('"+id_actaBaja+"','"+form.id_equipo.get(i)+"','"+form.cantidad.get(i).replaceAll(",", "")+"','"+form.motivo.get(i)+"'),";
					}
				}
				if(form.id_equipo!=null && detalle.length()>10) {
					detalle = detalle.substring(0,detalle.length()-1);
					Baja.create(con, db, detalle);
					ActaBaja.modifyXCampo(con, db, "fecha", form.fecha, id_actaBaja);
					ActaBaja.modifyXCampo(con, db, "observaciones", form.observaciones, id_actaBaja);
					String path = "0";
					if (docAdjunto != null) {
						String nombreArchivoSinExtencion = "Doc_Baja_" + id_actaBaja;
						path = Archivos.grabarArchivos(docAdjunto, db, nombreArchivoSinExtencion);
						ActaBaja.modifyXCampo(con, db, "actaBajaPDF", path, id_actaBaja);
					}
					flag = true;
				}
			}
		} catch (Exception e) {
			String className = FormBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return(flag);
	}
	
	public static boolean cambiaAconfirmada(Connection con, String db, Long id_baja) {
		boolean flag = false;
		String query = String.format("update `%s`.baja set esModificable = ?, fechaConfirma = ? where id = ?;",db);
		try (PreparedStatement smt = con.prepareStatement(query)) {
			Fechas fecha = Fechas.hoy();
			smt.setLong(1, (long)0);
			smt.setString(2, fecha.getFechaStrAAMMDD());
			smt.setLong(3, id_baja);
			smt.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			String className = FormBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR 1. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return(flag);
	}

	
	
}














