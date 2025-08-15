package models.apiRest;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import controllers.HomeController;
import models.tables.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MovimientosEntreFechas {
	
	public String id_movimiento;
	
	public String id_bodegaDestino;
	public String tipo_bodegaDestino;
	public String nombre_bodegaDestino;
	
	public String id_bodegaOrigen;
	public String tipo_bodegaOrigen;
	public String nombre_bodegaOrigen;
	
	public String id_guiaMovimiento;
	public String nro_guiaMovimiento;
	public String nro_guiaReferencia;
	public String fecha_guiaMovimiento;
	
	public String id_facturaCompra;
	public String nro_facturaCompra;
	public String fecha_facturaCompra;
	
	public String id_grupo;
	public String grupo;
	
	public String id_equipo;
	public String codigo;
	public String equipo;
	
	public String tipo_operacion;
	
	public String cantidad;
	
	public String id_moneda;
	public String moneda;
	
	public String plista_venta;
	public String plista_arriendoDia;
	public String pFinal_venta;
	public String pFinal_arriendoDia;
	
	public String id_cotizacion;
	public String nro_cotizacion;
	
	public String id_sucursal;
	public String nombre_sucursal;

	public String fechaIniTerGuia;
	
	public MovimientosEntreFechas() {
		super();
	}

	static DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
	static DecimalFormat sinFormato = new DecimalFormat("0",symbols);

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	public static List<MovimientosEntreFechas> movimientosEntreFechas(Connection con, String db, String desde, String hasta, Map<String,String> mapeoDiccionario) {
		List<MovimientosEntreFechas> datos = new ArrayList<MovimientosEntreFechas>();
		String query = String.format("select "
				/*1*/	+ " movimiento.id, "
				/*2*/	+ " movimiento.id_bodegaEmpresa, "
				/*3*/	+ " movimiento.id_equipo, "
				/*4*/	+ " movimiento.id_tipoMovimiento, "
				/*5*/	+ " movimiento.id_guia, "
				/*6*/	+ " movimiento.cantidad, "
				/*7*/	+ " ifnull(compra.id_factura,'0'), "
				/*8*/	+ " movimiento.id_bodegaOrigen, "
				/*9*/	+ " movimiento.id_baja, "
				/*10*/	+ " movimiento.id_cotizacion, "
				/*11*/	+ " ifnull(guia.numero,''), "
				/*12*/	+ " ifnull(guia.fecha,''), "
				/*13*/	+ " ifnull(factura.numero,''), "
				/*14*/	+ " ifnull(factura.fecha,''), "
				/*15*/	+ " ifnull(guia.numGuiaCliente,''), "
				/*16*/	+ " movimiento.esVenta, "
				/*17*/	+ " ifnull(guia.fechaIniTerGuia,ifnull(guia.fecha,'')) "
				+ " from `%s`.movimiento "
				+ " left join `%s`.guia on guia.id = id_guia "
				+ " left join `%s`.compra on compra.id = movimiento.id_compra "
				+ " left join `%s`.factura on factura.id = compra.id_factura "
				+ " where "
				+ " guia.fecha between '"+desde+"' and '"+hasta+"' "
				+ " or "
				+ " factura.fecha between '"+desde+"' and '"+hasta+"' ",db,db,db,db);
		try (PreparedStatement smt = con.prepareStatement(query)) {
			try (ResultSet rs = smt.executeQuery()) {
				Map<Long, Equipo> mapEquipo = Equipo.mapAllAll(con, db);
				Map<Long, BodegaEmpresa> mapBodega = BodegaEmpresa.mapAll(con, db);
				Map<Long, Cotizacion> mapCotizacion = Cotizacion.mapAll(con, db);
				Map<String, List<Double>> mapPrecio = ListaPrecio.mapListaPreciosAll(con, db);
				Map<String, Precio> mapMaestroPrecio = Precio.mapAllSucursales(con, db, mapeoDiccionario);
				Map<Long, Moneda> mapMoneda = Moneda.mapMonedas(con, db);
				Map<Long, Double> equivTiempo = UnidadTiempo.equivalencia(con, db);
				Map<Long, TipoBodega> mapTipoBodega = TipoBodega.mapAll(con, db);
				while (rs.next()) {
					Equipo equipo = mapEquipo.get(rs.getLong(3));
					BodegaEmpresa bodega = mapBodega.get(rs.getLong(2));
					if (equipo != null && bodega != null) {
						String idBodegaEmpresa = bodega.getId().toString();
						String idCotizacion = rs.getString(10);
						String idEquipo = rs.getString(3);
						String tipo_bodegaDestino = "";
						TipoBodega tipoBodega = mapTipoBodega.get(bodega.getEsInterna());
						if (tipoBodega != null) {
							tipo_bodegaDestino = tipoBodega.nombre;
						}
						String tipo_operacion = "Arriendo";
						if ((long) bodega.getEsInterna() == (long) 1) {
							tipo_operacion = "Traslado";
						} else {
							if (rs.getLong(16) == (long) 1) {
								tipo_operacion = "Venta";
							}
						}
						Double cantidad = rs.getDouble(6);
						if (rs.getLong(4) == (long) 2) {
							cantidad = cantidad * -1;
						}
						String bodegaOrigen = "";
						String tipo_bodegaOrigen = "";
						BodegaEmpresa bodOrigen = mapBodega.get(rs.getLong(8));
						if (bodOrigen != null) {
							bodegaOrigen = bodOrigen.getNombre();
							TipoBodega tipoBodegaOrigen = mapTipoBodega.get(bodega.getEsInterna());
							if (tipoBodegaOrigen != null) {
								tipo_bodegaOrigen = tipoBodegaOrigen.nombre;
							}
						}
						String nroCotizacion = "";
						Cotizacion cotizacion = mapCotizacion.get(rs.getLong(10));
						if (cotizacion != null) {
							nroCotizacion = cotizacion.getNumero().toString();
						}
						String id_moneda = "";
						String moneda = "";
						String pbase_arriendoDia = "";
						String pbase_venta = "";
						String plista_arriendoDia = "";
						String plista_venta = "";
						List<Double> listPrecio = mapPrecio.get(idBodegaEmpresa + "_" + idCotizacion + "_" + idEquipo);
						if (listPrecio != null) {
							Moneda mon = mapMoneda.get(listPrecio.get(3).longValue());
							if (mon != null) {
								Double factor = equivTiempo.get(listPrecio.get(2).longValue());
								if (factor != null) {
									Double parrDia = listPrecio.get(1) / factor;
									pbase_arriendoDia = sinFormato.format(parrDia);
									pbase_venta = sinFormato.format(listPrecio.get(0));
									id_moneda = mon.getId().toString();
									moneda = mon.getNickName();
								}
							}
						}
						Precio maestroPrecio = mapMaestroPrecio.get(bodega.getId_sucursal() + "_" + idEquipo);
						if (maestroPrecio != null) {
							Moneda mon = mapMoneda.get(maestroPrecio.getId_moneda());
							if (mon != null) {
								Double factor = equivTiempo.get(maestroPrecio.getId_unidadTiempo());
								if (factor != null) {
									Double parrDia = Double.parseDouble(maestroPrecio.getPrecioArriendo().replaceAll(",", "")) / factor;
									plista_arriendoDia = sinFormato.format(parrDia);
									plista_venta = sinFormato.format(Double.parseDouble(maestroPrecio.getPrecioReposicion().replaceAll(",", "")));
									id_moneda = mon.getId().toString();
									moneda = mon.getNickName();
								}
							}
						}
						MovimientosEntreFechas mov = new MovimientosEntreFechas();
						mov.cantidad = sinFormato.format(cantidad);
						mov.codigo = equipo.getCodigo();
						mov.equipo = equipo.getNombre();
						mov.fecha_facturaCompra = rs.getString(14);
						mov.fecha_guiaMovimiento = rs.getString(12);
						mov.grupo = equipo.getGrupo();
						mov.id_bodegaDestino = idBodegaEmpresa;
						mov.id_bodegaOrigen = rs.getString(8);
						mov.id_facturaCompra = rs.getString(7);
						mov.id_cotizacion = idCotizacion;
						mov.id_equipo = idEquipo;
						mov.id_grupo = equipo.getId_grupo().toString();
						mov.id_guiaMovimiento = rs.getString(5);
						mov.id_movimiento = rs.getString(1);
						mov.nombre_bodegaDestino = bodega.getNombre();
						mov.nombre_bodegaOrigen = bodegaOrigen;
						mov.nro_facturaCompra = rs.getString(13);
						mov.nro_cotizacion = nroCotizacion;
						mov.nro_guiaReferencia = rs.getString(15);
						mov.nro_guiaMovimiento = rs.getString(11);
						mov.id_moneda = id_moneda;
						mov.moneda = moneda;
						mov.pFinal_arriendoDia = pbase_arriendoDia;
						mov.pFinal_venta = pbase_venta;
						mov.plista_arriendoDia = plista_arriendoDia;
						mov.plista_venta = plista_venta;
						mov.tipo_bodegaDestino = tipo_bodegaDestino;
						mov.tipo_bodegaOrigen = tipo_bodegaOrigen;
						mov.tipo_operacion = tipo_operacion;
						mov.id_sucursal = bodega.getId_sucursal().toString();
						mov.nombre_sucursal = bodega.getNameSucursal();
						mov.fechaIniTerGuia = rs.getString(17);
						datos.add(mov);
					}
				}
			}
		} catch (SQLException e) {
			String className = ActaBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (datos);
	}
	
	
			
}
	
	
	
