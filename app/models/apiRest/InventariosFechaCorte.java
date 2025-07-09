package models.apiRest;


import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;
import java.util.concurrent.TimeoutException;

import controllers.HomeController;
import models.reports.ReportHohe;
import models.tables.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class InventariosFechaCorte {
	
	public String id_bodegaEmpresa;
	
	public String bodega;
	public String tipoBodega;
	public String grupo;
	public String id_equipo;
	public String codigo;
	public String equipo;
	public String kg;
	public String m2;
	public String cant_arriendo;
	public String cant_venta;
	public String cant_todo;
	public String fecha_corte;
	public String nro_cotizacion;
	public String id_cotizacion;
	
	
	public String id_moneda;
	public String moneda;
	
	public String plista_venta;
	public String plista_arriendoDia;
	
	public String pFinal_venta;
	public String pFinal_arriendoDia;
	
	
	public String id_sucursal;
	public String sucursal;
	
	
	
	public InventariosFechaCorte() {
		super();
	}

	static DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
	static DecimalFormat sinFormato = new DecimalFormat("0",symbols);

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);


	public static List<InventariosFechaCorte> inventariosFechaCorte(Connection con, String db, String fechaCorte, Map<String,String> mapeoDiccionario) {
		try{
			List<InventariosFechaCorte> datos = new ArrayList<InventariosFechaCorte>();
			// KEY = id_bodega _ id_equipo _ id_cotizacion _ tipo:
			Map<String,List<String>> mapArr = ReportHohe.listaMatrizEquiposHOHE2Coti(con, db, "ARRIENDO", fechaCorte);
			Map<String,List<String>> mapVta = ReportHohe.listaMatrizEquiposHOHE2Coti(con, db, "VENTA", fechaCorte);
			//UNE AMBOS RESULTADOS
			Map<String,List<String>> mapUne = new HashMap<String,List<String>>();
			for (Map.Entry<String,List<String>> map : mapArr.entrySet()) {
				mapUne.put(map.getKey(), map.getValue());
			}
			for (Map.Entry<String,List<String>> map : mapVta.entrySet()) {
				mapUne.put(map.getKey(), map.getValue());
			}
			Map<String, List<Double>> mapPrecio = ListaPrecio.mapListaPreciosAll(con, db);
			Map<String, Precio> mapMaestroPrecio = Precio.mapAllSucursales(con, db, mapeoDiccionario);
			Map<Long, Moneda> mapMoneda = Moneda.mapMonedas(con, db);
			Map<Long,Double> equivTiempo = UnidadTiempo.equivalencia(con, db);
			for (Map.Entry<String,List<String>> map : mapUne.entrySet()) {
				String auxKey[] = map.getKey().split("_");
				List<String> list1 = map.getValue();
				Double arr = (double)0;
				List<String> laux = mapArr.get(map.getKey());
				if(laux!=null) {
					arr = Double.parseDouble(laux.get(9));
				}
				Double vta = (double)0;
				laux = mapVta.get(map.getKey());
				if(laux!=null) {
					vta = Double.parseDouble(laux.get(9));
				}
				Double tot = arr + vta;
				String idBodegaEmpresa = list1.get(0);
				String idCotizacion = auxKey[2];
				String idEquipo = list1.get(4);
				String id_moneda = "";
				String moneda = "";
				String pbase_arriendoDia = "";
				String pbase_venta = "";
				String plista_arriendoDia = "";
				String plista_venta = "";
				List<Double> listPrecio = mapPrecio.get(idBodegaEmpresa+"_"+idCotizacion+"_"+idEquipo);
				if(listPrecio != null) {
					Moneda mon = mapMoneda.get(listPrecio.get(3).longValue());
					if(mon != null) {
						Double factor = equivTiempo.get(listPrecio.get(2).longValue());
						if(factor != null) {
							Double parrDia = listPrecio.get(1) / factor;
							pbase_arriendoDia = sinFormato.format(parrDia);
							pbase_venta = sinFormato.format(listPrecio.get(0));
							id_moneda = mon.getId().toString();
							moneda = mon.getNickName();
						}
					}
				}
				Precio maestroPrecio = mapMaestroPrecio.get(list1 .get(12) + "_" +idEquipo);
				if(maestroPrecio != null) {
					Moneda mon = mapMoneda.get(maestroPrecio.getId_moneda());
					if(mon != null) {
						Double factor = equivTiempo.get(maestroPrecio.getId_unidadTiempo());
						if(factor != null) {
							Double parrDia = Double.parseDouble(maestroPrecio.getPrecioArriendo().replaceAll(",", ""))  / factor;
							plista_arriendoDia = sinFormato.format(parrDia);
							plista_venta = sinFormato.format(Double.parseDouble(maestroPrecio.getPrecioReposicion().replaceAll(",", "")));
							id_moneda = mon.getId().toString();
							moneda = mon.getNickName();
						}
					}
				}
				InventariosFechaCorte inventario = new InventariosFechaCorte();
				inventario.id_bodegaEmpresa = idBodegaEmpresa;
				inventario.bodega = list1.get(1);
				inventario.tipoBodega = list1.get(2);
				inventario.grupo = list1.get(3);
				inventario.id_equipo = idEquipo;
				inventario.codigo = list1.get(5);
				inventario.equipo = list1.get(6);
				inventario.kg = list1.get(7);
				inventario.m2 = list1.get(8);
				inventario.cant_arriendo = arr.toString();
				inventario.cant_venta = vta.toString();
				inventario.cant_todo = tot.toString();
				inventario.fecha_corte = fechaCorte;
				inventario.nro_cotizacion = list1.get(11);
				inventario.id_cotizacion = idCotizacion;
				inventario.id_moneda = id_moneda;
				inventario.moneda = moneda;
				inventario.pFinal_arriendoDia = pbase_arriendoDia;
				inventario.pFinal_venta = pbase_venta;
				inventario.plista_arriendoDia =  plista_arriendoDia;
				inventario.plista_venta =  plista_venta;
				inventario.id_sucursal = list1.get(12);
				inventario.sucursal = list1.get(13);
				datos.add(inventario);
			}
			return (datos);
		} catch (Exception e) {
			String className = ActaBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
			return null;
		}
	}
	
	
			
}
	
	
	
