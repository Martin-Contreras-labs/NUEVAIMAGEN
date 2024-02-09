package models.forms;

import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.calculo.Inventarios;
import models.tables.ActaBaja;
import models.tables.ActaRedimensionar;
import models.tables.Baja;
import models.tables.Compra;
import models.tables.Factura;
import models.tables.Movimiento;
import models.tables.Proveedor;
import models.tables.Redimensionar;
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

	public FormRedimensionar(Long id_actaRedimensionar, Long numero, String fecha, String observaciones,
			List<Long> id_equipoOrigen, List<String> cantEquipoOrigen, List<Long> id_a_redimensionar,
			List<Long> id_redimensionar, List<String> cantEquipoRedimensionar,  List<Long> id_bodegaDestino) {
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
	}

	public FormRedimensionar() {
		super();
	}
	

	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdoubleCompra = new DecimalFormat("#,##0.00");
	
	public static boolean create (Connection con, String db, Map<String,String> mapeoPermiso, 
			FormRedimensionar form, Http.MultipartFormData.FilePart<TemporaryFile> docAdjunto) {
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
								+"','"+form.id_redimensionar.get(i)+"','"+form.cantEquipoRedimensionar.get(i).replaceAll(",", "")+"','"+form.id_bodegaDestino.get(i)+"'),";
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
	
	public static void redimensionar (Connection con, String db, Map<String,String> mapeoPermiso, Map<String,String> mapeoDiccionario, 
			Long id_actaRedimensionar, String id_usuario, String userName) {

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
				listMotivo.add("Generado desde redimensionar Nro: "+ id_actaRedimensionar);
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
		formCompra.observaciones = "Compra generada desde redimensionar Nro: "+ id_actaRedimensionar;
		if(FormCompra.create(con, db, formCompra, null, "0", id_usuario)) {
			Long id_factura = Factura.findIdFactura(con, db, formCompra.numeroFactura, formCompra.id_proveedor);
			List<List<String>> listCompra = Compra.allPorFactura(con, db, id_factura);
			for(List<String> x: listCompra) {
				Movimiento auxMov = new Movimiento();
				auxMov.setId_bodegaEmpresa(Long.parseLong(x.get(1)));
				auxMov.setId_equipo(Long.parseLong(x.get(2)));
				auxMov.setId_tipoMovimiento((long)1);
				auxMov.setCantidad(Double.parseDouble(x.get(3)));
				auxMov.setId_compra(Long.parseLong(x.get(0)));
				if(Movimiento.createMovimientoCompra(con, db, auxMov)) {
					Compra.actualizaPorCampo(con, db, "esModificable", Long.parseLong(x.get(0)), "0");
				}
			}
			Registro.modificaciones(con, db, id_usuario, userName, "factura", id_factura, "create", 
					"ingresa nueva compra confirmada nro: "+formCompra.numeroFactura+" desde acta redimensionar id: "+id_actaRedimensionar);
		}
		
		
		// genero acta, las bajas y las confirmo
		Long numActaBaja = ActaBaja.findNuevoNumero(con, db);
		formBaja.numero = numActaBaja;
		formBaja.fecha = hoy.getFechaStrAAMMDD();
		formBaja.observaciones = "Acta generada desde redimensionar Nro: "+ id_actaRedimensionar;
		if(FormBaja.create(con, db, mapeoPermiso, formBaja, null)) {
			Long id_actaBaja = ActaBaja.findIdActaBaja(con, db, numActaBaja);
			List<Baja> listBaja = Baja.allPorIdActaBaja(con, db, id_actaBaja);
			for(Baja x: listBaja) {
				Long id_baja = x.getId();
				Long id_equipo = x.getId_equipo();
				Double cantidad = x.getCantidad();
				Movimiento auxMov = new Movimiento();
				auxMov.setId_bodegaEmpresa((long)1);
				auxMov.setId_equipo(id_equipo);
				auxMov.setId_tipoMovimiento((long)2);
				auxMov.setCantidad(cantidad);
				auxMov.setId_baja(id_baja);
				if(Movimiento.createMovimientoBaja(con, db, auxMov)) {
					FormBaja.cambiaAconfirmada(con, db, id_baja);
				}
			}
			Registro.modificaciones(con, db, id_usuario, userName, "actaBaja", id_actaBaja, "create", 
					"ingresa nueva actaBaja confirmada nro: "+numActaBaja+" desde acta redimensionar id: "+id_actaRedimensionar);
		}
		
		ActaRedimensionar.modifyXCampo(con, db, "fechaConfirma", hoy.getFechaStrAAMMDD(), id_actaRedimensionar);
	}
	
	
	
	
	
	
	
}














