package models.forms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.calculo.Inventarios;
import models.tables.ActaRedimensionar;
import models.tables.Atributo;
import models.tables.Baja;
import models.tables.Cotizacion;
import models.tables.Equipo;
import models.tables.Grupo;
import models.tables.Moneda;
import models.tables.Movimiento;
import models.tables.Precio;
import models.tables.Redimensionar;
import models.utilities.Archivos;
import models.utilities.Fechas;
import play.libs.Files.TemporaryFile;
import play.mvc.Http;

public class FormBajaRedimensionar {
	public Long id_actaRedimensionar;
	public Long numero;
	public String fecha;
	public String observaciones;
	
	public List<Long> id_equipoOrigen;
	public List<String> cantEquipoOrigen;
	
	public List<Long> id_a_redimensionar;
	public List<Long> id_redimensionar;
	public List<String> cantEquipoRedimensionar;

	public FormBajaRedimensionar(Long id_actaRedimensionar, Long numero, String fecha, String observaciones,
			List<Long> id_equipoOrigen, List<String> cantEquipoOrigen, List<Long> id_a_redimensionar,
			List<Long> id_redimensionar, List<String> cantEquipoRedimensionar) {
		super();
		this.id_actaRedimensionar = id_actaRedimensionar;
		this.numero = numero;
		this.fecha = fecha;
		this.observaciones = observaciones;
		this.id_equipoOrigen = id_equipoOrigen;
		this.cantEquipoOrigen = cantEquipoOrigen;
		this.id_a_redimensionar = id_a_redimensionar;
		this.id_redimensionar = id_redimensionar;
		this.cantEquipoRedimensionar = cantEquipoRedimensionar;
	}

	public FormBajaRedimensionar() {
		super();
	}
	

	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdoubleCompra = new DecimalFormat("#,##0.00");
	
	public static boolean create (Connection con, String db, Map<String,String> mapeoPermiso, 
			FormBajaRedimensionar form, Http.MultipartFormData.FilePart<TemporaryFile> docAdjunto) {
		boolean flag = false;
		
		Long soloArriendo = (long) 1;
		if(mapeoPermiso.get("parametro.permiteDevolverVentas").equals("1")) {
			soloArriendo = (long) 0;
		}
		Map<String,Movimiento> map = Inventarios.invPorIdBodega(con, db, (long)1, soloArriendo);
		Map<Long,Double> mapCantBaja = new HashMap<Long,Double>();
		
		for(int i=0; i<form.id_equipoOrigen.size(); i++) {
			Movimiento mov = map.get(form.id_equipoOrigen.get(i).toString()+"_0");
			Double cant = (double)0;
			if(mov!=null) {
				cant = mov.getCantidad();
				Double cantBaja = Double.parseDouble(form.cantEquipoOrigen.get(i).replaceAll(",", ""));
				if((double) cantBaja <= (double) cant && cantBaja > 0) {
					form.cantEquipoOrigen.set(i,cant.toString());
					Double aux = mapCantBaja.get(form.id_equipoOrigen.get(i));
					if(aux == null) {
						mapCantBaja.put(form.id_equipoOrigen.get(i), cantBaja);
					}else {
						mapCantBaja.put(form.id_equipoOrigen.get(i), cantBaja + aux);
					}
					
				}
			}
		}
		
		ActaRedimensionar actaRedimensionar = new ActaRedimensionar(form);
		Long id_actaRedimensionar = ActaRedimensionar.create(con, db, actaRedimensionar);
		if(id_actaRedimensionar > 0) {
			String detalle = "";
			for(int i=0; form.id_a_redimensionar!=null && i<form.id_a_redimensionar.size(); i++) {
				Double cantBaja = mapCantBaja.get(form.id_a_redimensionar.get(i));
				if(cantBaja != null && (double) cantBaja > (double) 0) {
					Double aux = Double.parseDouble(form.cantEquipoRedimensionar.get(i).replaceAll(",", ""));
					if(aux > 0) {
						detalle += "('"+id_actaRedimensionar+"','"+form.id_a_redimensionar.get(i)+"','"+cantBaja
								+"','"+form.id_redimensionar.get(i)+"','"+form.cantEquipoRedimensionar.get(i).replaceAll(",", "")+"'),";
					}
				}
			}
			if(form.id_a_redimensionar!=null && detalle.length() > 2) {
				detalle = detalle.substring(0,detalle.length()-1);
				if(detalle.length()>2) {
					if(!Redimensionar.create(con, db, detalle)) {
						ActaRedimensionar.delete(con, db, id_actaRedimensionar);
					}else {
						String path = "0";
						if (docAdjunto != null) {
							String nombreArchivoSinExtencion = "Doc_Redimensionar_" + id_actaRedimensionar;
							path = Archivos.grabarArchivos(docAdjunto, db, nombreArchivoSinExtencion);
							ActaRedimensionar.modifyXCampo(con, db, "actaPDF", path, id_actaRedimensionar);
						}
						flag = true;
					}
				}
			}
		}
		if( ! flag) {
			ActaRedimensionar.delete(con, db, id_actaRedimensionar);
		}
		return(flag);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//********************************************
	
	
	
	public static List<List<String>> listEquipEnBodBaja (Connection con, String db, Map<String,String> mapeoPermiso, Map<String,String> mapeoDiccionario){
		Long soloArriendo = (long) 1;
		if(mapeoPermiso.get("parametro.permiteDevolverVentas").equals("1")) {
			soloArriendo = (long) 0;
		}
		Map<Long,Precio> mapPrecio = Precio.mapAll(con, db, mapeoDiccionario, (long) 1);
		Map<String,Long> decCompra = Moneda.numeroDecimalxNombre(con, db);
		Map<String,Movimiento> map = Inventarios.invPorIdBodega(con, db, (long)1, soloArriendo);
		Map<Long,Grupo> mapGrupo = Grupo.mapAll(con, db);
		Map<Long,Equipo> mapEquipo = Equipo.mapAllAll(con, db);
		Map<Long,Cotizacion> mapCotizacion = Cotizacion.mapAll(con, db);
		Map<Long,Double> mapPeso = Atributo.mapAtributoPESO(con, db);
		Map<Long,Double> mapM2 = Atributo.mapAtributoM2(con, db);
		List<List<String>> listEquipEnBodBaja = new ArrayList<List<String>>();
		map.forEach((k,v)->{
			if(v.getCantidad()>0) {
				Equipo equipo = mapEquipo.get(v.getId_equipo());
				if(equipo!=null) {
					Grupo grupo = mapGrupo.get(equipo.getId_grupo());
					Cotizacion coti = mapCotizacion.get(v.getId_cotizacion());
					Long numCoti = (long) 0; if(coti!=null){numCoti = coti.getNumero();};
					Double peso = mapPeso.get(v.getId_equipo()); if(peso==null){peso = (double)0;};
					Double m2 = mapM2.get(v.getId_equipo()); if(m2==null){m2 = (double)0;};
					Precio precio = mapPrecio.get(v.getId_equipo());
					String moneda = "", pcompra = "";
					Double auxPcompra = (double) 0;
					if(precio!=null) {
						moneda = precio.getMonedaCompra();
						pcompra = precio.getPrecioCompra();
						auxPcompra = Double.parseDouble(pcompra.replaceAll(",", ""));
					}
					Double total = v.getCantidad() * auxPcompra;
					Long auxDecimal = decCompra.get(moneda);
					if(auxDecimal==null) auxDecimal = (long)2;
					switch(auxDecimal.toString()) {
	   				 case "0": myformatdoubleCompra = new DecimalFormat("#,##0"); break;
	   				 default : myformatdoubleCompra = new DecimalFormat("#,##0.00"); break;
	   				}
					
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
		return (listEquipEnBodBaja);
	}
	
	
	
	public static List<List<String>> detalleDeLaBaja (Connection con, String db, Map<String,String> mapeoPermiso, Map<String,String> mapeoDiccionario, Long id_actaBaja, Map<Long, Baja> mapDetalleBajaModifica){
		List<List<String>> listEquipBodOrigen = FormBajaRedimensionar.listEquipEnBodBaja(con, db, mapeoPermiso, mapeoDiccionario);
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
		
		
		List<List<String>> detalle = new ArrayList<List<String>>();
		mapId_equipos.forEach((k,v)->{
			Baja baja = mapDetalleBajaModifica.get(k);
			List<String> bodOrigen = mapEquipBodOrigen.get(k);
			Map<String,Long> decCompra = Moneda.numeroDecimalxNombre(con, db);
			
			if(baja == null && bodOrigen != null) {
				
				Long auxDecimal = decCompra.get(bodOrigen.get(10));
				if(auxDecimal==null) auxDecimal = (long)2;
				switch(auxDecimal.toString()) {
   				 case "0": myformatdoubleCompra = new DecimalFormat("#,##0"); break;
   				 default : myformatdoubleCompra = new DecimalFormat("#,##0.00"); break;
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
				
				Long auxDecimal = decCompra.get(bodOrigen.get(10));
				if(auxDecimal==null) auxDecimal = (long)2;
				switch(auxDecimal.toString()) {
   				 case "0": myformatdoubleCompra = new DecimalFormat("#,##0"); break;
   				 default : myformatdoubleCompra = new DecimalFormat("#,##0.00"); break;
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
					Double kg = equipo.getKG();
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
					
					Long auxDecimal = decCompra.get(mon);
					if(auxDecimal==null) auxDecimal = (long)2;
					switch(auxDecimal.toString()) {
	   				 case "0": myformatdoubleCompra = new DecimalFormat("#,##0"); break;
	   				 default : myformatdoubleCompra = new DecimalFormat("#,##0.00"); break;
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
		return(detalle);
	}
	
	public static boolean update (Connection con, String db, Map<String,String> mapeoPermiso, FormBajaRedimensionar form, Http.MultipartFormData.FilePart<TemporaryFile> docAdjunto) {
		boolean flag = false;
		
//		Long soloArriendo = (long) 1;
//		if(mapeoPermiso.get("parametro.permiteDevolverVentas").equals("1")) {
//			soloArriendo = (long) 0;
//		}
//		Map<String,Movimiento> map = Inventarios.invPorIdBodega(con, db, (long)1, soloArriendo);
//		
//		for(int i=0; i<form.id_equipoOrigen.size(); i++) {
//			Movimiento mov = map.get(form.id_equipoOrigen.get(i).toString()+"_0");
//			Double cant = (double)0;
//			if(mov!=null) {
//				cant = mov.getCantidad();
//				Double aux = Double.parseDouble(form.cantEquipoOrigen.get(i).replaceAll(",", ""));
//				if((double) aux > (double) cant && (double) cant > (double) 0) {
//					form.cantEquipoOrigen.set(i,cant.toString());
//				}
//			}
//		}
//		
//		
//		Long id_actaBaja = form.id_actaBaja;
//		if(id_actaBaja>0) {
//			String detalle = "";
//			for(int i=0; form.id_equipoOrigen!=null && i<form.id_equipoOrigen.size(); i++) {
//				Double aux = Double.parseDouble(form.cantEquipoOrigen.get(i).replaceAll(",", ""));
//				if((double) aux > (double)0) {
//					detalle += "('"+id_actaBaja+"','"+form.id_equipoOrigen.get(i)+"','"+form.cantEquipoOrigen.get(i).replaceAll(",", "")+"','"+form.motivo.get(i)+"'),";
//				}
//			}
//			if(form.id_equipoOrigen!=null) {
//				detalle = detalle.substring(0,detalle.length()-1);
//				Baja.create(con, db, detalle);
//				ActaBaja.modifyXCampo(con, db, "fecha", form.fecha, id_actaBaja);
//				ActaBaja.modifyXCampo(con, db, "observaciones", form.observaciones, id_actaBaja);
//				String path = "0";
//				if (docAdjunto != null) {
//					String nombreArchivoSinExtencion = "Doc_Baja_" + id_actaBaja;
//					path = Archivos.grabarArchivos(docAdjunto, db, nombreArchivoSinExtencion);
//					ActaBaja.modifyXCampo(con, db, "actaBajaPDF", path, id_actaBaja);
//				}
//				flag = true;
//			}
//		}
		return(flag);
	}
	
	public static boolean cambiaAconfirmada(Connection con, String db, Long id_baja) {
		boolean flag = false;
		Fechas fecha = Fechas.hoy();
		try {
			PreparedStatement smt = con
					.prepareStatement("update `"+db+"`.baja set esModificable = ?, fechaConfirma = ? where id = ?;");
			smt.setLong(1, (long)0);
			smt.setString(2, fecha.getFechaStrAAMMDD());
			smt.setLong(3, id_baja);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(flag);
	}
	
	
	
	
	
	
	
}














