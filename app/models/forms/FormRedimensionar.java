package models.forms;

import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

import models.calculo.Inventarios;
import models.tables.*;
import models.utilities.Archivos;
import models.utilities.Fechas;
import models.utilities.Registro;
import play.libs.Files.TemporaryFile;
import play.mvc.Http;

public class FormRedimensionar {
	public Long id_actaRedimensionar;
	public Long numero;
	public String fecha;
	public String observaciones;
	
	public List<Long> id_equipoOrigen;
	public List<String> cantEquipoOrigen;
	
	public List<Long> id_a_redimensionar;
	public List<Long> id_redimensionar;
	public List<String> cantEquipoRedimensionar;
	public List<Long> id_bodegaDestino;

	public Long id_bodegaOrigen;

	public FormRedimensionar(Long id_actaRedimensionar, Long numero, String fecha, String observaciones,
			List<Long> id_equipoOrigen, List<String> cantEquipoOrigen, List<Long> id_a_redimensionar,
			List<Long> id_redimensionar, List<String> cantEquipoRedimensionar,  List<Long> id_bodegaDestino, Long id_bodegaOrigen) {
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
		this.id_bodegaDestino = id_bodegaDestino;
		this.id_bodegaOrigen = id_bodegaOrigen;
	}

	public FormRedimensionar() {
		super();
	}

	static DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00",symbols);
	static DecimalFormat myformatdoubleCompra = new DecimalFormat("#,##0.00",symbols);
	
	public static boolean create (Connection con, String db, Map<String,String> mapeoPermiso, 
			FormRedimensionar form, Http.MultipartFormData.FilePart<TemporaryFile> docAdjunto) {
		boolean flag = false;
		
		Long soloArriendo = (long) 1;
		if(mapeoPermiso.get("parametro.permiteDevolverVentas").equals("1")) {
			soloArriendo = (long) 0;
		}
		Map<String,Movimiento> map = Inventarios.invPorIdBodega(con, db, form.id_bodegaOrigen, soloArriendo);
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
		
		Map<Long,Long> mapAux = new HashMap<Long,Long>();
		ActaRedimensionar actaRedimensionar = new ActaRedimensionar(form);
		Long id_actaRedimensionar = ActaRedimensionar.create(con, db, actaRedimensionar);
		if(id_actaRedimensionar > 0) {
			String detalle = "";
			// agrega todos los que indican equipo a redimensionar
			for(int i=0; form.id_a_redimensionar!=null && i<form.id_a_redimensionar.size(); i++) {
				Double cantBaja = mapCantBaja.get(form.id_a_redimensionar.get(i));
				if(cantBaja != null && (double) cantBaja > (double) 0) {
					Double aux = Double.parseDouble(form.cantEquipoRedimensionar.get(i).replaceAll(",", ""));
					aux += cantBaja;
					
					if(aux > 0) {
						mapAux.put(form.id_a_redimensionar.get(i), form.id_a_redimensionar.get(i));
						detalle += "('"+id_actaRedimensionar+"','"+form.id_a_redimensionar.get(i)+"','"+cantBaja
								+"','"+form.id_redimensionar.get(i)+"','"+form.cantEquipoRedimensionar.get(i).replaceAll(",", "")+"','"+form.id_bodegaDestino.get(i)+"'),";
					}
				}
			}
			//agrega solo para acta de baja los equipos con cant baja pero que no tienen equipo a redimensionar
			for (Map.Entry<Long, Double> entry : mapCantBaja.entrySet()) {
	            Long auxRedim = mapAux.get(entry.getKey());
	            if(auxRedim == null) {
	            	detalle += "('"+id_actaRedimensionar+"','"+entry.getKey()+"','"+entry.getValue()+"','0','0','0'),";
	            }
	        }
			if(form.id_a_redimensionar!=null) {
				if(detalle.length()>10) {
					detalle = detalle.substring(0,detalle.length()-1);
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
	
	public static void redimensionar (Connection con, String db, Map<String,String> mapeoPermiso, Map<String,String> mapeoDiccionario, 
			Long id_actaRedimensionar, Long nroActaRedimensionar, String id_usuario, String userName) {

		List<Redimensionar> listRedimensionar = Redimensionar.allPorIdActa(con, db, id_actaRedimensionar);
		Fechas hoy = Fechas.hoy();
		
		 
		FormBaja formBaja = new FormBaja();
		Long auxId = (long)0;
		FormCompra formCompra = new FormCompra();
		
		List<Long> listId_equipoOrigen = new ArrayList<Long>();
		List<String> listCant_equipoOrigen = new ArrayList<String>();
		List<String> listMotivo = new ArrayList<String>();
		List<Long> listId_equipoRedimensionar = new ArrayList<Long>();
		List<String> listCantEquipoRedimensionar = new ArrayList<String>();
		List<Long> listId_monedaCompra = new ArrayList<Long>();
		List<String> listPuCompra = new ArrayList<String>();
		List<Long> listId_bodegaDestino = new ArrayList<Long>();
		
		for(Redimensionar x: listRedimensionar) {
			if((long) auxId != (long) x.getId_equipoOrigen()) {
				listId_equipoOrigen.add(x.getId_equipoOrigen());
				listCant_equipoOrigen.add(x.getCant_equipoOrigen().toString());
				listMotivo.add("Generado desde redimensionar Nro: "+ nroActaRedimensionar);
			}
			listId_equipoRedimensionar.add(x.getId_equipoRedimensionar());
			listCantEquipoRedimensionar.add(x.getCantEquipoRedimensionar().toString());
			listId_monedaCompra.add((long) 1);
			listPuCompra.add("0");
			listId_bodegaDestino.add(x.getId_bodegaDestino());
			auxId = (long) x.getId_equipoOrigen();
		}
		
		formBaja.id_equipo = listId_equipoOrigen;
		formBaja.cantidad = listCant_equipoOrigen;
		formBaja.motivo = listMotivo;
		formCompra.id_equipo = listId_equipoRedimensionar;
		formCompra.cantidad = listCantEquipoRedimensionar;
		formCompra.id_monedaCompra = listId_monedaCompra;
		formCompra.puCompra = listPuCompra;
		formCompra.id_bodegaDestino = listId_bodegaDestino;
		
		
		// genero factura, las compras y las confirmo
		Proveedor proveedor = Proveedor.findPorNickName(con, db, mapeoDiccionario.get("nEmpresa"));
		if(proveedor == null) {
			formCompra.id_proveedor = Proveedor.insertNew(con, db, mapeoDiccionario.get("nEmpresa"));
		}else {
			formCompra.id_proveedor = proveedor.getId();
		}
		formCompra.numeroFactura = Factura.nuevoNumero(con, db, formCompra.id_proveedor);
		formCompra.fechaFactura = hoy.getFechaStrAAMMDD();
		formCompra.observaciones = "Compra generada desde redimensionar Nro: "+ nroActaRedimensionar;
		
		if(FormCompra.create(con, db, formCompra, null, "0", id_usuario)) {
			Long id_factura = Factura.findIdFactura(con, db, formCompra.numeroFactura, formCompra.id_proveedor);
			
			List<List<String>> listCompra = Compra.allPorFactura(con, db, id_factura);
			String insertMovimiento = "";
			
			for(List<String> x: listCompra) {
				
				String id_bodegaEmpresa = x.get(1);
				String id_equipo = x.get(2);
				String id_tipoMovimiento = "1";
				String cantidad = x.get(3);
				String id_compra = x.get(0);
				String fecha_factura = x.get(4);
				
				Double cant = Double.parseDouble(cantidad);
				if (cant>0) {
					insertMovimiento += "('"
							+ id_bodegaEmpresa + "','"
							+ id_equipo + "','"
							+ id_tipoMovimiento + "','"
							+ cantidad + "','"
							+ id_compra + "','"
							+ id_factura + "','"
							+ fecha_factura + "'),";
				}
				
			}
			
			if(insertMovimiento.length() > 10) {
				insertMovimiento = insertMovimiento.substring(0,insertMovimiento.length()-1);
				if(Movimiento.createMovimientoCompra(con, db, insertMovimiento)) {
					for(List<String> x: listCompra) {
						String id_compra = x.get(0);
						Compra.actualizaPorCampo(con, db, "esModificable", Long.parseLong(id_compra), "0");
					}
					Registro.modificaciones(con, db, id_usuario, userName, "factura", id_factura, "create", 
							"ingresa nueva compra confirmada nro: "+formCompra.numeroFactura+" desde acta redimensionar nro: "+nroActaRedimensionar+" con id: "+id_actaRedimensionar);
				}
			}
			
		}
		
		
		// genero acta, las bajas y las confirmo
		Long numActaBaja = ActaBaja.findNuevoNumero(con, db);
		formBaja.numero = numActaBaja;
		formBaja.fecha = hoy.getFechaStrAAMMDD();
		formBaja.observaciones = "Acta generada desde redimensionar Nro: "+ nroActaRedimensionar;
		if(FormBaja.create(con, db, mapeoPermiso, formBaja, null)) {
			Long id_actaBaja = ActaBaja.findIdActaBaja(con, db, numActaBaja);
			List<Baja> listBaja = Baja.allPorIdActaBaja(con, db, id_actaBaja);
			String insertMovimiento = "";
			for(Baja x: listBaja) {
				
				
				ActaRedimensionar actaRedimensionar = ActaRedimensionar.find(con,db, id_actaRedimensionar);

				
				
				//CAMBIAR
				Long id_bodegaEmpresa = actaRedimensionar.getId_bodegaOrigen();
				
				
				
				
				
				String id_equipo = x.getId_equipo().toString();
				String id_tipoMovimiento = "2";
				String cantidad = x.getCantidad().toString();
				String id_baja = x.getId().toString();
				String fecha_actaBaja = x.getFecha_actaBaja();
				
				Double cant = Double.parseDouble(cantidad);
				if (cant>0) {
					insertMovimiento += "('"
							+ id_bodegaEmpresa + "','"
							+ id_equipo + "','"
							+ id_tipoMovimiento + "','"
							+ cantidad + "','"
							+ id_baja + "','"
							+ id_actaBaja + "','"
							+ fecha_actaBaja + "'),";
				}
				
			}
			
			if(insertMovimiento.length() > 10) {
				insertMovimiento = insertMovimiento.substring(0,insertMovimiento.length()-1);
				if(Movimiento.createMovimientoBaja(con, db, insertMovimiento)) {
					for(Baja x: listBaja) {
						Long id_baja = x.getId();
						FormBaja.cambiaAconfirmada(con, db, id_baja);
					}
					Registro.modificaciones(con, db, id_usuario, userName, "actaBaja", id_actaBaja, "create", 
							"ingresa nueva actaBaja confirmada nro: "+numActaBaja+" desde acta redimensionar  nro: "+nroActaRedimensionar+" con id: "+id_actaRedimensionar);
				}
			}
			
			Registro.modificaciones(con, db, id_usuario, userName, "actaBaja", id_actaBaja, "create", 
					"ingresa nueva actaBaja confirmada nro: "+numActaBaja+" desde acta redimensionar  nro: "+nroActaRedimensionar+" con id: "+id_actaRedimensionar);
		}
		
		ActaRedimensionar.modifyXCampo(con, db, "fechaConfirma", hoy.getFechaStrAAMMDD(), id_actaRedimensionar);
	}


	public static List<List<String>> listEquipEnBodRedimensionar (Connection con, String db, Map<String,String> mapeoPermiso, Map<String,String> mapeoDiccionario, Long id_bodegaOrigen){
		Long soloArriendo = (long) 1;
		if(mapeoPermiso.get("parametro.permiteDevolverVentas").equals("1")) {
			soloArriendo = (long) 0;
		}
		Map<Long, Precio> mapPrecio = Precio.mapAll(con, db, mapeoDiccionario, (long) 1);
		Map<String,Long> decCompra = Moneda.numeroDecimalxNombre(con, db);


		//CAMBIAR
		Map<String,Movimiento> map = Inventarios.invPorIdBodega(con, db, id_bodegaOrigen, soloArriendo);




		Map<Long,Grupo> mapGrupo = Grupo.mapAll(con, db);
		Map<Long,Equipo> mapEquipo = Equipo.mapAllAll(con, db);
		Map<Long,Cotizacion> mapCotizacion = Cotizacion.mapAll(con, db);
		List<List<String>> listEquipEnBodBaja = new ArrayList<List<String>>();
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
		return (listEquipEnBodBaja);
	}
	
	
	
	
}














