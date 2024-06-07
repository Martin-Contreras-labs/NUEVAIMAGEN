package models.forms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.tables.BodegaEmpresa;
import models.tables.Cotizacion;
import models.tables.Guia;
import models.tables.ListaPrecio;
import models.tables.Ot;
import models.tables.OtDespachado;
import models.tables.Precio;
import models.tables.TasaEquipo;
import models.tables.TasaGrupo;
import models.utilities.Fechas;

public class FormDespacho {
	
	public long id_guia;
	public Long id_ot;
	public Long id_cotizacion;
	
	public Long id_bodegaOrigen;
	public Long id_bodegaDestino;
	public Long numeroGuia;
	public String numGuiaCliente;
	public String fechaGuia;
	public String observaciones;
			
	//public List<Long> id_cotizacion;
	public List<Long> id_cotizaDetalle;
	public List<Long> id_equipoDespacho;
	public List<Long> esVenta;
	public List<Long> esNuevo;
	public List<Long> id_equipoOrigen;
	public List<String> cantDespacho;
	public List<String> cantEquivalente;
	
	public Long seModifico;
	
	public String docAnexo;
	
	public Long id_transportista;
	
	public String fotos;
	
	public FormDespacho(long id_guia, Long id_ot, Long id_cotizacion, Long id_bodegaOrigen, Long id_bodegaDestino,
			Long numeroGuia, String numGuiaCliente, String fechaGuia, String observaciones, List<Long> id_cotizaDetalle,
			List<Long> id_equipoDespacho, List<Long> esVenta, List<Long> esNuevo, List<Long> id_equipoOrigen,
			List<String> cantDespacho, List<String> cantEquivalente, Long seModifico, String docAnexo,
			Long id_transportista, String fotos, Long id_comercial) {
		super();
		this.id_guia = id_guia;
		this.id_ot = id_ot;
		this.id_cotizacion = id_cotizacion;
		this.id_bodegaOrigen = id_bodegaOrigen;
		this.id_bodegaDestino = id_bodegaDestino;
		this.numeroGuia = numeroGuia;
		this.numGuiaCliente = numGuiaCliente;
		this.fechaGuia = fechaGuia;
		this.observaciones = observaciones;
		this.id_cotizaDetalle = id_cotizaDetalle;
		this.id_equipoDespacho = id_equipoDespacho;
		this.esVenta = esVenta;
		this.esNuevo = esNuevo;
		this.id_equipoOrigen = id_equipoOrigen;
		this.cantDespacho = cantDespacho;
		this.cantEquivalente = cantEquivalente;
		this.seModifico = seModifico;
		this.docAnexo = docAnexo;
		this.id_transportista = id_transportista;
		this.fotos = fotos;
	}

	public FormDespacho() {
		super();
	}
	

	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	
	public static boolean create (Connection con, String db, FormDespacho form,  Map<String,String> mapeoDiccionario, String id_userCrea, String id_userModifica) {
		boolean flag = false;
		 
		Guia aux = new Guia();
		aux.setNumero(form.numeroGuia);
		aux.setFecha(form.fechaGuia);
		aux.setDocAnexo(form.docAnexo);
		aux.setObservaciones(form.observaciones);
		aux.setNumGuiaCliente(form.numGuiaCliente);
		aux.setId_bodegaOrigen(form.id_bodegaOrigen);
		aux.setId_bodegaDestino(form.id_bodegaDestino);
		aux.setId_cotizacion(form.id_cotizacion);       //NOTA: guias y movimientos generados desde MOVIMIENTO no tienen cotizacion de origen asignado a la guia
		aux.setId_ot(form.id_ot);     					//NOTA: guias y movimientos generados desde MOVIMIENTO no tienen ot de origen asignado a la guia
		aux.setId_transportista(form.id_transportista);
		aux.setFotos(form.fotos);
		
		
		Guia guia = Guia.create(con, db, aux, id_userCrea, id_userModifica);
		if(guia!=null) {
			
			String valores="";
			Long id_ot = form.id_ot;
			Long id_guia = guia.getId();
			Long id_bodegaOrigen = form.id_bodegaOrigen;
			Long id_bodegaDestino = form.id_bodegaDestino;
			for(int i=0; i<form.id_equipoDespacho.size();i++){
				Double cantidadDespacho = Double.parseDouble(form.cantDespacho.get(i).replaceAll(",",""));
				if(cantidadDespacho > 0) {
					Long id_cotizacion = form.id_cotizacion;
					Long id_cotizaDetalle = form.id_cotizaDetalle.get(i);
					Long id_equipoOrigen = form.id_equipoOrigen.get(i);
					Long id_equipoDespacho = form.id_equipoDespacho.get(i);
					Double cantidadRebajaOt = Double.parseDouble(form.cantEquivalente.get(i).replaceAll(",", ""));
					Long esVenta = form.esVenta.get(i);
					valores += "('"+id_ot+"','"+id_guia+"','"+id_cotizaDetalle+"','"+id_equipoOrigen+"','"+id_equipoDespacho+"','"+cantidadDespacho+"','"+cantidadRebajaOt+"','"+id_bodegaOrigen+"','"+esVenta+"','"+id_cotizacion+"'),";
				}
			}
			if(valores.length()>10){
				valores = valores.substring(0, valores.length()-1);
				try {
					PreparedStatement smt = con
							.prepareStatement("INSERT INTO `"+db+"`.otDespachado "
								+ "(id_ot, id_guia, id_cotizaDetalle, id_equipoOrigen, id_equipoDespacho, cantidadDespacho, cantidadRebajaOt, id_bodegaOrigen, esVenta, id_cotizacion) "
								+ " values "+valores+";");
					smt.executeUpdate();
					smt.close();
					List<OtDespachado> listOtDespachado = OtDespachado.allPorIdGuiaIdOt(con, db, id_ot, id_guia);
					
					valores = "";
					for(int i=0;i<listOtDespachado.size();i++){
						Long id_equipo = listOtDespachado.get(i).getId_equipoDespacho();
						Double cantidad = listOtDespachado.get(i).getCantidadDespacho();
						Long esVenta = listOtDespachado.get(i).getEsVenta();
						Long esNuevo = (long)0;
						Long id_otDespachado = listOtDespachado.get(i).getId();
						Long id_cotizacion = listOtDespachado.get(i).getId_cotizacion();
						valores +=
							"('"+id_bodegaOrigen+"','"+id_equipo+"','2','"+id_guia+"','"+cantidad+"','0','"+esVenta+"','"+esNuevo+"','"+id_otDespachado+"','0'),"+
							"('"+id_bodegaDestino+"','"+id_equipo+"','1','"+id_guia+"','"+cantidad+"','"+id_bodegaOrigen+"','"+esVenta+"','"+esNuevo+"','"+id_otDespachado+"','"+id_cotizacion+"'),";
					}
					
					if(valores.length()>10){
						valores = valores.substring(0, valores.length()-1);
						PreparedStatement smt2 = con
								.prepareStatement("INSERT INTO `"+db+"`.movimiento "
									+ "(id_bodegaEmpresa, id_equipo, id_tipoMovimiento, id_guia, cantidad, id_bodegaOrigen, esVenta, esNuevo, id_otDespachado, id_cotizacion) "
									+ " values "+valores+";");
						smt2.executeUpdate();
						smt2.close();
						Ot.modifyXCampo(con, db, "esEliminable", "0", id_ot);
						
						Ot ot = Ot.find(con, db, id_ot);
						Cotizacion coti = Cotizacion.find(con, db, ot.getId_cotizacion());
						
						Map<Long, List<Double>> mapDetalleCoti = Cotizacion.mapDetalleCotizacion(con, db, ot.getId_cotizacion());
						
						BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, db, id_bodegaDestino);
						
						Map<Long,Precio> mapPrecio = Precio.mapAll(con, db, mapeoDiccionario, bodega.getId_sucursal());
						
						Double dctoArr = coti.getDctoArriendo();
						Double dctoVta = coti.getDctoVenta();
						
						Map<String,ListaPrecio> mapListaPrecio = ListaPrecio.mapListaPrecio(con, db, id_bodegaDestino, mapeoDiccionario.get("pais"));
						
						
						Map<String,TasaGrupo> mapTasaArrGrupo = TasaGrupo.mapAllXBodegaEmpresa(con, db, id_bodegaDestino);
						Map<String,TasaEquipo> mapTasaArrEquipo = TasaEquipo.mapAllXBodegaEmpresa(con, db, id_bodegaDestino);
						
						BodegaEmpresa bodegaDestino = BodegaEmpresa.findXIdBodega(con, db, form.id_bodegaDestino);
						Double tasaArrBod = bodegaDestino.getTasaArriendo();
						
						
						Map<String,String> mapLprecioAux = new HashMap<String,String>();
						for(int i=0; i<listOtDespachado.size(); i++) {
							Double precioVenta = (double)0;
							Double precioReposicion = (double)0;
							Double precioArriendo = (double)0;
							Long id_moneda = (long)1;
							Long id_unidadTiempo = (long)4;
							
							ListaPrecio listaPrecio = mapListaPrecio.get(listOtDespachado.get(i).getId_equipoDespacho()+"_"+listOtDespachado.get(i).getId_cotizacion());
							
							if( listaPrecio == null ) {
								List<Double> precioOrigen = mapDetalleCoti.get(listOtDespachado.get(i).getId_equipoDespacho());
								
								Precio precioMaestro =  mapPrecio.get(listOtDespachado.get(i).getId_equipoDespacho());
								
								
								if(precioOrigen != null) {
									precioVenta = precioOrigen.get(4) * (1-dctoVta);
									precioArriendo = precioOrigen.get(3) * (1-dctoArr);
									precioReposicion = precioVenta;
									id_moneda = precioOrigen.get(6).longValue();
									id_unidadTiempo = precioOrigen.get(5).longValue();
									
								} else if(precioMaestro!=null) {
									precioVenta = Double.parseDouble(precioMaestro.getPrecioVenta().replaceAll(",", "")) * (1-dctoVta);
									precioArriendo =  Double.parseDouble(precioMaestro.getPrecioArriendo().replaceAll(",", "")) * (1-dctoVta);
									
									Double tasaArrGrupo = (double)0;
									String idBod_idGrupo = id_bodegaDestino+"_"+precioMaestro.getId_grupo();
									TasaGrupo tasaGrupo =  mapTasaArrGrupo.get(idBod_idGrupo);
									if(tasaGrupo != null) {
										tasaArrGrupo = tasaGrupo.getTasaArriendo();
									}
									
									Double tasaArrEquipo = (double)0;
									String idBod_idEquipo = id_bodegaDestino+"_"+precioMaestro.id_equipo;
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
									
									if(tasa>0) {
										precioArriendo = precioVenta * tasa;
									}
									
									precioReposicion = precioVenta;
									id_moneda = precioMaestro.getId_moneda();
									id_unidadTiempo = precioMaestro.getId_unidadTiempo();
								
								}else {
									Precio.create(con, db, listOtDespachado.get(i).getId_equipoDespacho(), Fechas.hoy().getFechaStrAAMMDD());
								}
								
								Long id_cotizacion = listOtDespachado.get(i).getId_cotizacion();
								
								String dePaso = "('"+id_bodegaDestino+"','"+listOtDespachado.get(i).getId_equipoDespacho()+"','"+id_moneda+"','"+Fechas.hoy().getFechaStrAAMMDD()
										+"','"+precioVenta+"','"+precioReposicion+"','"+precioArriendo+"','"+id_unidadTiempo+"','"+id_cotizacion+"'),";
								
								mapLprecioAux.put(dePaso, dePaso);
							}
						}
						
						valores = "";
						for(String k: mapLprecioAux.keySet()) {
							valores += k;
						}
						
						if(valores.length()>10){
							valores = valores.substring(0, valores.length()-1);
							PreparedStatement smt3 = con
									.prepareStatement("INSERT INTO `"+db+"`.listaPrecio "
										+ "(id_bodegaEmpresa, id_equipo, id_moneda, fecha, precioVenta, precioReposicion, precioArriendo, id_unidadTiempo, id_cotizacion) "
										+ " values "+valores+";");
							smt3.executeUpdate();
							smt3.close();
						}
					}
				} catch (Exception e) {
	    			e.printStackTrace();
				}
			}
			flag = true;
		}
		return(flag);
		
	}
	
	
	
}














