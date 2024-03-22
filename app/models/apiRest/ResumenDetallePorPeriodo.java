package models.apiRest;


import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.calculo.Calc_BodegaEmpresa;
import models.calculo.Calc_Precio;
import models.calculo.ModCalc_GuiasPer;
import models.calculo.ModCalc_InvInicial;
import models.calculo.ModeloCalculo;
import models.reports.ReportFacturas;
import models.tables.AjustesEP;
import models.tables.BodegaEmpresa;
import models.tables.Guia;






public class ResumenDetallePorPeriodo {
	
	@SuppressWarnings("unused")
	private static class Resumen{
		public String id_bodegaCliente;
		public String bodegaProyecto;
		public String cliente;
		public String proyecto;
		public String subtotal_cfi;
		public String subtotal_arriendo;
		public String subtotal_venta;
		public String ajustes_arriendo;
		public String ajustes_venta;
		public String total;
		
		public Resumen() {
			super();
		}
	}
	
	@SuppressWarnings("unused")
	private static class InventarioInicial{
		public String id_bodegaCliente;
		public String nro_coti;
		public String codigo;
		public String equipo;
		public String un;
		public String cantidad;
		public String moneda;
		public String p_venta;
		public String arrXdia;
		public String dias;
		public String tasa_cambio;
		public String cfi;
		public String arriendo;
		public String venta;
		
		public InventarioInicial() {
			super();
		}
	}
	
	@SuppressWarnings("unused")
	private static class DetalleMovimientos{
		public String id_bodegaCliente;
		public String nro_movimiento;
		public String fecha;
		public String nro_coti;
		public String codigo;
		public String equipo;
		public String un;
		public String cantidad;
		public String moneda;
		public String p_venta;
		public String arrXdia;
		public String dias;
		public String tasa_cambio;
		public String cfi;
		public String arriendo;
		public String venta;
		
		public DetalleMovimientos() {
			super();
		}
	}
	
	@SuppressWarnings("unused")
	private static class Ajustes{
		public String id_bodegaCliente;
		public String concepto;
		public String ajusteArriendo;
		public String ajusteVenta;
		
		public Ajustes() {
			super();
		}
	}
		

	public List<Resumen> resumen = new ArrayList<Resumen>();
	public List<InventarioInicial> inventarioInicial = new ArrayList<InventarioInicial>();
	public List<DetalleMovimientos> detalleMovimientos = new ArrayList<DetalleMovimientos>();
	public List<Ajustes> ajustes = new ArrayList<Ajustes>();
	
	
	
	public ResumenDetallePorPeriodo() {
		super();
	}
	
	static DecimalFormat sinFormato = new DecimalFormat("0");
	
	public static ResumenDetallePorPeriodo inventariosFechaCorte(Connection con, String db, String desdeAAMMDD, String hastaAAMMDD, Double uf, Double usd, Double eur, String esPorSucursal, String id_sucursal) {
		
		ResumenDetallePorPeriodo datos = new ResumenDetallePorPeriodo();
		
   		Map<Long,Double> tasas = new HashMap<Long,Double>();
		tasas.put((long)1, (double) 1); 	// 'Peso Chileno', 'CLP', '0'
		tasas.put((long)2, usd); 			// 'Dólar', 'USD', '2'
		tasas.put((long)3, eur); 			// 'Euro', 'EUR', '3'
		tasas.put((long)4, uf); 			// 'Unidad Fomento', 'UF', '4'
		
   		try {
			List<Long> listIdBodegaEmpresa = ModCalc_InvInicial.listIdBodegaEmpresa(con, db, "");
			Map<Long,Calc_BodegaEmpresa> mapBodegaEmpresa = Calc_BodegaEmpresa.mapAllBodegasVigentes(con, db);
			Map<String,Calc_Precio> mapPrecios = Calc_Precio.mapPrecios(con, db, listIdBodegaEmpresa);
			Map<Long,Calc_Precio> mapMaestroPrecios = Calc_Precio.mapMaestroPrecios(con, db);
			Map<String, Double> mapFijaTasas = BodegaEmpresa.mapFijaTasasAll(con, db);
			
			List<ModCalc_InvInicial> inventarioInicial = ModCalc_InvInicial.resumenInvInicial(con, db, desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa, mapBodegaEmpresa, mapPrecios, mapMaestroPrecios);
			List<ModCalc_GuiasPer> guiasPeriodo = ModCalc_GuiasPer.resumenGuiasPer(con, db, desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa, mapBodegaEmpresa, mapPrecios, mapMaestroPrecios);
			List<ModeloCalculo> valorTotalPorBodega = ModeloCalculo.valorTotalporBodega(con, db, desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, inventarioInicial,guiasPeriodo);
			List<List<String>> resumenTotales = ReportFacturas.resumenTotalesPorProyecto(con, db, valorTotalPorBodega);  						// RESUMEN INICIAL DEL REPORTE
			List<List<String>> proyectos = ReportFacturas.reportFacturaProyecto(con, db, valorTotalPorBodega, esPorSucursal, id_sucursal); 
			Map<String, List<List<String>>> mapInicioPer = ReportFacturas.mapInicioPerAllBodegas(con, db, inventarioInicial);  					// RESUMEN POR ESTADOS DE PAGO
			Map<String, List<List<String>>> mapGuiasPer = ReportFacturas.mapGuiasPer(con, db, guiasPeriodo);  									// RESUMEN POR ESTADOS DE PAGO
			
			Map<Long,Guia> mapGuias = Guia.mapAll(con, db);
			
			for(int i=0;i<proyectos.size();i++){
				
				String id_bodegaCliente = proyectos.get(i).get(1).trim();
				
				for(int j=0;j<resumenTotales.size();j++){
					
					if(id_bodegaCliente.equals(resumenTotales.get(j).get(0))){
						
						//RESUMEN
						Resumen resumen = new Resumen();
						resumen.id_bodegaCliente = id_bodegaCliente;
						resumen.bodegaProyecto = proyectos.get(i).get(5);
						resumen.cliente = proyectos.get(i).get(7);
						resumen.proyecto = proyectos.get(i).get(8);
						resumen.subtotal_cfi = resumenTotales.get(j).get(3).replaceAll(",", "");
						resumen.subtotal_arriendo = resumenTotales.get(j).get(1).replaceAll(",", "");
						resumen.subtotal_venta = resumenTotales.get(j).get(2).replaceAll(",", "");
						resumen.ajustes_arriendo = resumenTotales.get(j).get(5).replaceAll(",", "");
						resumen.ajustes_venta = resumenTotales.get(j).get(6).replaceAll(",", "");
						resumen.total = resumenTotales.get(j).get(4).replaceAll(",", "");
						datos.resumen.add(resumen);
						
						
						//INV INICIAL
						List<List<String>> inicioPer = mapInicioPer.get(resumenTotales.get(j).get(0));
						if(inicioPer==null) {
							inicioPer = new ArrayList<List<String>>();
						}
						for(int k=0; k<inicioPer.size(); k++){
							InventarioInicial inventario = new InventarioInicial();
							inventario.id_bodegaCliente = id_bodegaCliente;
							inventario.nro_coti = inicioPer.get(k).get(8);
							inventario.codigo = inicioPer.get(k).get(9);
							inventario.equipo = inicioPer.get(k).get(10);
							inventario.un = inicioPer.get(k).get(11);
							inventario.cantidad = inicioPer.get(k).get(12).replaceAll(",", "");
							inventario.moneda = inicioPer.get(k).get(13);
							inventario.p_venta = inicioPer.get(k).get(14).replaceAll(",", "");
							inventario.arrXdia = inicioPer.get(k).get(15).replaceAll(",", "");
							inventario.dias = inicioPer.get(k).get(16).replaceAll(",", "");
							inventario.tasa_cambio = inicioPer.get(k).get(17).replaceAll(",", "");
							inventario.cfi = inicioPer.get(k).get(23).replaceAll(",", "");
							inventario.arriendo = inicioPer.get(k).get(18).replaceAll(",", "");
							inventario.venta = inicioPer.get(k).get(19).replaceAll(",", "");
		    				datos.inventarioInicial.add(inventario);
						}
						
						
						//DETALLE
						List<List<String>> guiasPer = mapGuiasPer.get(id_bodegaCliente);
						if(guiasPer==null) {
							guiasPer = new ArrayList<List<String>>();
						}
						for(int k=0; k<guiasPer.size(); k++){
							
							Long id_guia = Long.parseLong(guiasPer.get(k).get(3));
							Guia guia = mapGuias.get(id_guia);
							
							if(guia!=null) {
								DetalleMovimientos detalle = new DetalleMovimientos();
								detalle.nro_movimiento = sinFormato.format(guia.numero);
								detalle.id_bodegaCliente = id_bodegaCliente;
			    				detalle.fecha = guia.fecha;
			    				detalle.nro_coti = guiasPer.get(k).get(8);
			    				detalle.codigo = guiasPer.get(k).get(9);
			    				detalle.equipo = guiasPer.get(k).get(10);
			    				detalle.un = guiasPer.get(k).get(11);
			    				detalle.cantidad = guiasPer.get(k).get(12).replaceAll(",", "");
			    				detalle.moneda = guiasPer.get(k).get(13);
			    				detalle.p_venta = guiasPer.get(k).get(14).replaceAll(",", "");
			    				detalle.arrXdia = guiasPer.get(k).get(15).replaceAll(",", "");
			    				detalle.dias = guiasPer.get(k).get(16).replaceAll(",", "");
			    				detalle.tasa_cambio = guiasPer.get(k).get(17).replaceAll(",", "");
			    				detalle.cfi = guiasPer.get(k).get(23).replaceAll(",", "");
			    				detalle.arriendo = guiasPer.get(k).get(18).replaceAll(",", "");
			    				detalle.venta = guiasPer.get(k).get(19).replaceAll(",", "");
			    				datos.detalleMovimientos.add(detalle);
							}
							
						}
						
						//AJUSTES
						List<List<String>> detalleAjuste = AjustesEP.detalleAjuste(con, db, Long.parseLong(id_bodegaCliente), desdeAAMMDD, hastaAAMMDD);
						for(List<String> lista: detalleAjuste ) {
							
							Ajustes ajustes = new Ajustes();
							ajustes.id_bodegaCliente = id_bodegaCliente;
							ajustes.concepto = lista.get(0);
							ajustes.ajusteArriendo = lista.get(1).trim().replaceAll(",", "");
							ajustes.ajusteVenta = lista.get(2).trim().replaceAll(",", "");
							datos.ajustes.add(ajustes);
						}
					}
				}
			}
			
			
			con.close();
    	} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		return (datos);
	}
	
	
			
}
	
	
	
