package models.calculo;


import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ModeloCalculo {
	
	public Long id_bodegaEmpresa;
	public Long id_grupo;
	public Long id_equipo;
	
	public Double totalCfi;
	public Double totalArriendo;
	public Double totalVenta;
	public Double totalTotal;
	public Double ajusteArriendo;
	public Double ajusteVenta;
	public Double totalArriendoSinAjuste;
	public Double totalVentaSinAjuste;
	
	public Double maestroTotalCfi;
	public Double maestroTotalArriendo;
	public Double maestroTotalVenta;
	public Double maestroTotalTotal;

	

	public ModeloCalculo(Long id_bodegaEmpresa, Long id_grupo, Long id_equipo, Double totalCfi, Double totalArriendo, Double totalVenta,
			Double totalTotal, Double ajusteArriendo, Double ajusteVenta, Double totalArriendoSinAjuste,
			Double totalVentaSinAjuste, Double maestroTotalCfi, Double maestroTotalArriendo, Double maestroTotalVenta,
			Double maestroTotalTotal) {
		super();
		this.id_bodegaEmpresa = id_bodegaEmpresa;
		this.id_grupo = id_grupo;
		this.id_equipo = id_equipo;
		this.totalCfi = totalCfi;
		this.totalArriendo = totalArriendo;
		this.totalVenta = totalVenta;
		this.totalTotal = totalTotal;
		this.ajusteArriendo = ajusteArriendo;
		this.ajusteVenta = ajusteVenta;
		this.totalArriendoSinAjuste = totalArriendoSinAjuste;
		this.totalVentaSinAjuste = totalVentaSinAjuste;
		this.maestroTotalCfi = maestroTotalCfi;
		this.maestroTotalArriendo = maestroTotalArriendo;
		this.maestroTotalVenta = maestroTotalVenta;
		this.maestroTotalTotal = maestroTotalTotal;
		
	}


	public ModeloCalculo() {
		super();
	}

	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	
	public static List<ModeloCalculo> valorTotalporBodega(Connection con, String db, String desdeAAMMDD, String hastaAAMMDD, Map<Long,Double> tasas,
			List<ModCalc_InvInicial> inventarioInicial, List<ModCalc_GuiasPer> guiasPeriodo) {
		
		List<ModeloCalculo> listado = new ArrayList<ModeloCalculo>();
		Map<Long,Long> listaBodegas = new HashMap<Long,Long>();
		Map<Long,ModeloCalculo> invInicial = new HashMap<Long,ModeloCalculo>();
		Map<Long,ModeloCalculo> guias = new HashMap<Long,ModeloCalculo>();
		Long flagId_Bodega = (long) 0;
		Long id_bodegaEmpresa = (long) 0;
		
		Double totalVenta = (double) 0, totalCfi = (double) 0, totalArriendo = (double) 0, totalTotal = (double) 0;
		Double maestroTotalVenta = (double) 0, maestroTotalCfi = (double) 0, maestroTotalArriendo = (double) 0, maestroTotalTotal = (double) 0;
		
		
		Double auxiliar = (double)0;
		
		//INVENTARIO INICIAL:
		for(int i=0; i<inventarioInicial.size(); i++) {
			auxiliar += inventarioInicial.get(i).totalArriendo;
			if(((long) flagId_Bodega != (long) inventarioInicial.get(i).id_bodegaEmpresa)) {
				if(flagId_Bodega>0) {
					ModeloCalculo aux = new ModeloCalculo();
					aux.id_bodegaEmpresa = id_bodegaEmpresa;
					
					aux.totalVenta = totalVenta;
					aux.totalArriendo = totalArriendo;
					aux.totalCfi = totalCfi;
					aux.totalTotal = totalTotal;
					
					aux.maestroTotalVenta = maestroTotalVenta;
					aux.maestroTotalArriendo = maestroTotalArriendo;
					aux.maestroTotalCfi = maestroTotalCfi;
					aux.maestroTotalTotal = maestroTotalTotal;
					
					invInicial.put(id_bodegaEmpresa, aux);
					listaBodegas.put(id_bodegaEmpresa, aux.id_bodegaEmpresa);
				}
				
				totalVenta = totalCfi = totalArriendo = totalTotal = (double) 0;
				maestroTotalVenta = maestroTotalArriendo = maestroTotalCfi = maestroTotalTotal = (double) 0;
				flagId_Bodega = inventarioInicial.get(i).id_bodegaEmpresa;
			}
			
			id_bodegaEmpresa = inventarioInicial.get(i).id_bodegaEmpresa;
			
			totalArriendo += inventarioInicial.get(i).totalArriendo;
			totalTotal += inventarioInicial.get(i).totalTotal;
			
			maestroTotalArriendo += inventarioInicial.get(i).maestroTotalArriendo;
			maestroTotalTotal += inventarioInicial.get(i).maestroTotalTotal;
			
			if(i == inventarioInicial.size()-1) {
				
				ModeloCalculo aux = new ModeloCalculo();
				
				aux.id_bodegaEmpresa = id_bodegaEmpresa;
				
				aux.totalVenta = totalVenta;
				aux.totalArriendo = totalArriendo;
				aux.totalCfi = totalCfi;
				aux.totalTotal = totalTotal;
				
				aux.maestroTotalVenta = maestroTotalVenta;
				aux.maestroTotalArriendo = maestroTotalArriendo;
				aux.maestroTotalCfi = maestroTotalCfi;
				aux.maestroTotalTotal = maestroTotalTotal;
				
				invInicial.put(id_bodegaEmpresa, aux);
				listaBodegas.put(id_bodegaEmpresa, aux.id_bodegaEmpresa);
			}
		}
		
		flagId_Bodega = (long) 0;
		id_bodegaEmpresa = (long) 0;
		
		
		//GUIAS DEL PERIODO:
		for(int i=0; i<guiasPeriodo.size(); i++) {
			
			auxiliar += guiasPeriodo.get(i).totalTotal;
			if(((long) flagId_Bodega != (long) guiasPeriodo.get(i).id_bodegaEmpresa)) {
				if(flagId_Bodega>0) {
					ModeloCalculo aux = new ModeloCalculo();
					aux.id_bodegaEmpresa = id_bodegaEmpresa;
					
					aux.totalVenta = totalVenta;
					aux.totalArriendo = totalArriendo;
					aux.totalCfi = totalCfi;
					aux.totalTotal = totalTotal;
					
					aux.maestroTotalVenta = maestroTotalVenta;
					aux.maestroTotalArriendo = maestroTotalArriendo;
					aux.maestroTotalCfi = maestroTotalCfi;
					aux.maestroTotalTotal = maestroTotalTotal;
					
					guias.put(id_bodegaEmpresa, aux);
					listaBodegas.put(id_bodegaEmpresa, aux.id_bodegaEmpresa);
				}
				totalVenta = totalCfi = totalArriendo = totalTotal = (double) 0;
				maestroTotalVenta = maestroTotalArriendo = maestroTotalCfi = maestroTotalTotal = (double) 0;
				flagId_Bodega = guiasPeriodo.get(i).id_bodegaEmpresa;
			}
			
			id_bodegaEmpresa = guiasPeriodo.get(i).id_bodegaEmpresa;
			
			totalArriendo = totalArriendo + guiasPeriodo.get(i).totalArriendo;
			totalCfi = totalCfi +  guiasPeriodo.get(i).totalCfi;
			totalVenta = totalVenta +  guiasPeriodo.get(i).totalVenta;
			totalTotal += guiasPeriodo.get(i).totalTotal;
			
			maestroTotalArriendo += guiasPeriodo.get(i).maestroTotalArriendo;
			maestroTotalCfi +=  guiasPeriodo.get(i).maestroTotalCfi;
			maestroTotalVenta +=  guiasPeriodo.get(i).maestroTotalVenta;
			maestroTotalTotal += guiasPeriodo.get(i).maestroTotalTotal;
			
			
			if(i == guiasPeriodo.size()-1) {
				ModeloCalculo aux = new ModeloCalculo();
				aux.id_bodegaEmpresa = id_bodegaEmpresa;
				
				aux.totalVenta = totalVenta;
				aux.totalArriendo = totalArriendo;
				aux.totalCfi = totalCfi;
				aux.totalTotal = totalTotal;
				
				aux.maestroTotalVenta = maestroTotalVenta;
				aux.maestroTotalArriendo = maestroTotalArriendo;
				aux.maestroTotalCfi = maestroTotalCfi;
				aux.maestroTotalTotal = maestroTotalTotal;
				
				guias.put(id_bodegaEmpresa, aux);
				listaBodegas.put(id_bodegaEmpresa, aux.id_bodegaEmpresa);
			}
		}
		
		
		
		//SUMA INVENTARIO INICIAL MAS GUIAS Y APLICA LOS AJUSTES A ESTADOS DE PAGO
		Map<Long,Calc_AjustesEP> mapResumenAjustePorBodega = Calc_AjustesEP.mapResumenAjustePorBodega(con, db, desdeAAMMDD, hastaAAMMDD);
		
		for (Long v : listaBodegas.values()) {
			
			Double totVenta = (double) 0, totArriendo = (double) 0, totCfi = (double) 0, totTot = (double) 0;
			Double maestroTotVenta = (double) 0, maestroTotArriendo = (double) 0, maestroTotCfi = (double) 0, maestroTotTot = (double) 0;
			
			ModeloCalculo ini = invInicial.get(v);
			if(ini!=null) {
				totVenta += ini.totalVenta;
				totArriendo += ini.totalArriendo;
				totCfi += ini.totalCfi;
				totTot += ini.totalTotal;
				
				maestroTotVenta += ini.maestroTotalVenta;
				maestroTotArriendo += ini.maestroTotalArriendo;
				maestroTotCfi += ini.maestroTotalCfi;
				maestroTotTot += ini.maestroTotalTotal;
				
			}
			ModeloCalculo per = guias.get(v);
			if(per!=null) {
				totVenta += per.totalVenta;
				totArriendo += per.totalArriendo;
				totCfi += per.totalCfi;
				totTot += per.totalTotal;
				
				maestroTotVenta += per.maestroTotalVenta;
				maestroTotArriendo += per.maestroTotalArriendo;
				maestroTotCfi += per.maestroTotalCfi;
				maestroTotTot += per.maestroTotalTotal;
			}
			//OBTIENE Y APLICA LOS AJUSTES A ESTADOS DE PAGO
			Calc_AjustesEP ajustes = mapResumenAjustePorBodega.get(v);
			Double ajusteArriendo = (double) 0, ajusteVenta = (double) 0;
			if(ajustes!=null) {
				Double tasaCambio = tasas.get(ajustes.id_moneda); 
				if(tasaCambio==null) {
					tasaCambio = (double) 1;
				}
				ajusteArriendo = ajustes.ajusteSobreArriendo * tasaCambio;
				ajusteVenta = ajustes.ajusteSobreVenta * tasaCambio;
			}
			ModeloCalculo aux = new ModeloCalculo();
			aux.id_bodegaEmpresa = v;
			aux.totalVenta = totVenta + ajusteVenta;
			aux.totalArriendo = totArriendo + ajusteArriendo;
			aux.totalCfi = totCfi;
			aux.totalTotal =  aux.totalVenta + aux.totalArriendo + aux.totalCfi;
			
			aux.ajusteArriendo = ajusteArriendo;
			aux.ajusteVenta = ajusteVenta;
			aux.totalArriendoSinAjuste = totArriendo;
			aux.totalVentaSinAjuste = totVenta;
			
			aux.maestroTotalVenta = maestroTotVenta;
			aux.maestroTotalArriendo = maestroTotArriendo;
			aux.maestroTotalCfi = maestroTotCfi;
			aux.maestroTotalTotal = aux.maestroTotalVenta + aux.maestroTotalArriendo + aux.maestroTotalCfi;

			
			if(totArriendo +  totVenta > 0) {
				listado.add(aux);
			}
		}
		
		return(listado);
	}
	
	public static List<ModeloCalculo> valorTotalporBodegaSinAjustes(Connection con, String db, String desdeAAMMDD, String hastaAAMMDD, Map<Long,Double> tasas,
			List<ModCalc_InvInicial> inventarioInicial, List<ModCalc_GuiasPer> guiasPeriodo) {
		
		List<ModeloCalculo> listado = new ArrayList<ModeloCalculo>();
		Map<Long,Long> listaBodegas = new HashMap<Long,Long>();
		Map<Long,ModeloCalculo> invInicial = new HashMap<Long,ModeloCalculo>();
		Map<Long,ModeloCalculo> guias = new HashMap<Long,ModeloCalculo>();
		Long flagId_Bodega = (long) 0;
		Long id_bodegaEmpresa = (long) 0;
		
		Double totalVenta = (double) 0, totalCfi = (double) 0, totalArriendo = (double) 0, totalTotal = (double) 0;
		Double maestroTotalVenta = (double) 0, maestroTotalCfi = (double) 0, maestroTotalArriendo = (double) 0, maestroTotalTotal = (double) 0;
		
		
		Double auxiliar = (double)0;
		
		//INVENTARIO INICIAL:
		for(int i=0; i<inventarioInicial.size(); i++) {
			auxiliar += inventarioInicial.get(i).totalArriendo;
			if(((long) flagId_Bodega != (long) inventarioInicial.get(i).id_bodegaEmpresa)) {
				if(flagId_Bodega>0) {
					ModeloCalculo aux = new ModeloCalculo();
					aux.id_bodegaEmpresa = id_bodegaEmpresa;
					
					aux.totalVenta = totalVenta;
					aux.totalArriendo = totalArriendo;
					aux.totalCfi = totalCfi;
					aux.totalTotal = totalTotal;
					
					aux.maestroTotalVenta = maestroTotalVenta;
					aux.maestroTotalArriendo = maestroTotalArriendo;
					aux.maestroTotalCfi = maestroTotalCfi;
					aux.maestroTotalTotal = maestroTotalTotal;
					
					invInicial.put(id_bodegaEmpresa, aux);
					listaBodegas.put(id_bodegaEmpresa, aux.id_bodegaEmpresa);
				}
				
				totalVenta = totalCfi = totalArriendo = totalTotal = (double) 0;
				maestroTotalVenta = maestroTotalArriendo = maestroTotalCfi = maestroTotalTotal = (double) 0;
				flagId_Bodega = inventarioInicial.get(i).id_bodegaEmpresa;
			}
			
			id_bodegaEmpresa = inventarioInicial.get(i).id_bodegaEmpresa;
			
			totalArriendo += inventarioInicial.get(i).totalArriendo;
			totalTotal += inventarioInicial.get(i).totalTotal;
			
			maestroTotalArriendo += inventarioInicial.get(i).maestroTotalArriendo;
			maestroTotalTotal += inventarioInicial.get(i).maestroTotalTotal;
			
			if(i == inventarioInicial.size()-1) {
				
				ModeloCalculo aux = new ModeloCalculo();
				
				aux.id_bodegaEmpresa = id_bodegaEmpresa;
				
				aux.totalVenta = totalVenta;
				aux.totalArriendo = totalArriendo;
				aux.totalCfi = totalCfi;
				aux.totalTotal = totalTotal;
				
				aux.maestroTotalVenta = maestroTotalVenta;
				aux.maestroTotalArriendo = maestroTotalArriendo;
				aux.maestroTotalCfi = maestroTotalCfi;
				aux.maestroTotalTotal = maestroTotalTotal;
				
				invInicial.put(id_bodegaEmpresa, aux);
				listaBodegas.put(id_bodegaEmpresa, aux.id_bodegaEmpresa);
			}
		}
		
		flagId_Bodega = (long) 0;
		id_bodegaEmpresa = (long) 0;
		
		
		//GUIAS DEL PERIODO:
		for(int i=0; i<guiasPeriodo.size(); i++) {
			
			auxiliar += guiasPeriodo.get(i).totalTotal;
			if(((long) flagId_Bodega != (long) guiasPeriodo.get(i).id_bodegaEmpresa)) {
				if(flagId_Bodega>0) {
					ModeloCalculo aux = new ModeloCalculo();
					aux.id_bodegaEmpresa = id_bodegaEmpresa;
					
					aux.totalVenta = totalVenta;
					aux.totalArriendo = totalArriendo;
					aux.totalCfi = totalCfi;
					aux.totalTotal = totalTotal;
					
					aux.maestroTotalVenta = maestroTotalVenta;
					aux.maestroTotalArriendo = maestroTotalArriendo;
					aux.maestroTotalCfi = maestroTotalCfi;
					aux.maestroTotalTotal = maestroTotalTotal;
					
					guias.put(id_bodegaEmpresa, aux);
					listaBodegas.put(id_bodegaEmpresa, aux.id_bodegaEmpresa);
				}
				totalVenta = totalCfi = totalArriendo = totalTotal = (double) 0;
				maestroTotalVenta = maestroTotalArriendo = maestroTotalCfi = maestroTotalTotal = (double) 0;
				flagId_Bodega = guiasPeriodo.get(i).id_bodegaEmpresa;
			}
			
			id_bodegaEmpresa = guiasPeriodo.get(i).id_bodegaEmpresa;
			
			totalArriendo = totalArriendo + guiasPeriodo.get(i).totalArriendo;
			totalCfi = totalCfi +  guiasPeriodo.get(i).totalCfi;
			totalVenta = totalVenta +  guiasPeriodo.get(i).totalVenta;
			totalTotal += guiasPeriodo.get(i).totalTotal;
			
			maestroTotalArriendo += guiasPeriodo.get(i).maestroTotalArriendo;
			maestroTotalCfi +=  guiasPeriodo.get(i).maestroTotalCfi;
			maestroTotalVenta +=  guiasPeriodo.get(i).maestroTotalVenta;
			maestroTotalTotal += guiasPeriodo.get(i).maestroTotalTotal;
			
			
			if(i == guiasPeriodo.size()-1) {
				ModeloCalculo aux = new ModeloCalculo();
				aux.id_bodegaEmpresa = id_bodegaEmpresa;
				
				aux.totalVenta = totalVenta;
				aux.totalArriendo = totalArriendo;
				aux.totalCfi = totalCfi;
				aux.totalTotal = totalTotal;
				
				aux.maestroTotalVenta = maestroTotalVenta;
				aux.maestroTotalArriendo = maestroTotalArriendo;
				aux.maestroTotalCfi = maestroTotalCfi;
				aux.maestroTotalTotal = maestroTotalTotal;
				
				guias.put(id_bodegaEmpresa, aux);
				listaBodegas.put(id_bodegaEmpresa, aux.id_bodegaEmpresa);
			}
		}
		
		
		
		//SUMA INVENTARIO INICIAL MAS GUIAS Y APLICA LOS AJUSTES A ESTADOS DE PAGO
	//	Map<Long,Calc_AjustesEP> mapResumenAjustePorBodega = Calc_AjustesEP.mapResumenAjustePorBodega(con, db, desdeAAMMDD, hastaAAMMDD);
		for (Long v : listaBodegas.values()) {
			
			Double totVenta = (double) 0, totArriendo = (double) 0, totCfi = (double) 0, totTot = (double) 0;
			Double maestroTotVenta = (double) 0, maestroTotArriendo = (double) 0, maestroTotCfi = (double) 0, maestroTotTot = (double) 0;
			
			ModeloCalculo ini = invInicial.get(v);
			if(ini!=null) {
				totVenta += ini.totalVenta;
				totArriendo += ini.totalArriendo;
				totCfi += ini.totalCfi;
				totTot += ini.totalTotal;
				
				maestroTotVenta += ini.maestroTotalVenta;
				maestroTotArriendo += ini.maestroTotalArriendo;
				maestroTotCfi += ini.maestroTotalCfi;
				maestroTotTot += ini.maestroTotalTotal;
				
			}
			ModeloCalculo per = guias.get(v);
			if(per!=null) {
				totVenta += per.totalVenta;
				totArriendo += per.totalArriendo;
				totCfi += per.totalCfi;
				totTot += per.totalTotal;
				
				maestroTotVenta += per.maestroTotalVenta;
				maestroTotArriendo += per.maestroTotalArriendo;
				maestroTotCfi += per.maestroTotalCfi;
				maestroTotTot += per.maestroTotalTotal;
			}
			
			Double ajusteArriendo = (double) 0, ajusteVenta = (double) 0;
			
			ModeloCalculo aux = new ModeloCalculo();
			aux.id_bodegaEmpresa = v;
			aux.totalVenta = totVenta + ajusteVenta;
			aux.totalArriendo = totArriendo + ajusteArriendo;
			aux.totalCfi = totCfi;
			aux.totalTotal =  aux.totalVenta + aux.totalArriendo + aux.totalCfi;
			
			aux.ajusteArriendo = ajusteArriendo;
			aux.ajusteVenta = ajusteVenta;
			aux.totalArriendoSinAjuste = totArriendo;
			aux.totalVentaSinAjuste = totVenta;
			
			aux.maestroTotalVenta = maestroTotVenta;
			aux.maestroTotalArriendo = maestroTotArriendo;
			aux.maestroTotalCfi = maestroTotCfi;
			aux.maestroTotalTotal = aux.maestroTotalVenta + aux.maestroTotalArriendo + aux.maestroTotalCfi;
			
			if(totArriendo +  totVenta > 0) {
				listado.add(aux);
			}
		}
		
		
		return(listado);
	}
	
	public static List<ModeloCalculo> valorTotalporBodegaYGrupo(Connection con, String db, String desdeAAMMDD, String hastaAAMMDD, Map<Long,Double> tasas,
			List<ModCalc_InvInicial> inventarioInicial, List<ModCalc_GuiasPer> guiasPeriodo) {
		List<ModeloCalculo> listado = new ArrayList<ModeloCalculo>();
		Map<String,String> listaBodegasGrupo = new HashMap<String,String>();
		Map<String,ModeloCalculo> invInicial = new HashMap<String,ModeloCalculo>();
		Map<String,ModeloCalculo> guias = new HashMap<String,ModeloCalculo>();
		Long flagId_Bodega = (long) 0, flagId_Grupo = (long) 0;
		Long id_bodegaEmpresa = (long) 0, id_grupo = (long) 0;
		Double totalVenta = (double) 0, totalCfi = (double) 0, totalArriendo = (double) 0;
		//INVENTARIO INICIAL:
		for(int i=0; i<inventarioInicial.size(); i++) {
			if((flagId_Bodega - inventarioInicial.get(i).id_bodegaEmpresa) != 0) {
				flagId_Bodega = inventarioInicial.get(i).id_bodegaEmpresa;
				flagId_Grupo = (long) 0;
			}
			if((flagId_Grupo - inventarioInicial.get(i).id_grupo) != 0) {
				if(i>0) {
					ModeloCalculo aux = new ModeloCalculo();
					aux.id_bodegaEmpresa = id_bodegaEmpresa;
					aux.id_grupo = id_grupo;
					aux.totalVenta = totalVenta;
					aux.totalArriendo = totalArriendo;
					aux.totalCfi = totalCfi;
					invInicial.put(id_bodegaEmpresa+"_"+id_grupo+"_Ini", aux);
					listaBodegasGrupo.put(id_bodegaEmpresa+"_"+id_grupo, id_bodegaEmpresa+"_"+id_grupo);
				}
				totalVenta = totalCfi = totalArriendo = (double) 0;
				flagId_Grupo = inventarioInicial.get(i).id_grupo;
			}
				id_bodegaEmpresa = inventarioInicial.get(i).id_bodegaEmpresa;
				id_grupo = inventarioInicial.get(i).id_grupo;
				
				totalArriendo = totalArriendo + inventarioInicial.get(i).totalArriendo;
			if(i == inventarioInicial.size()-1) {
				ModeloCalculo aux = new ModeloCalculo();
				aux.id_bodegaEmpresa = id_bodegaEmpresa;
				aux.id_grupo = id_grupo;
				aux.totalVenta = totalVenta;
				aux.totalArriendo = totalArriendo;
				aux.totalCfi = totalCfi;
				invInicial.put(id_bodegaEmpresa+"_"+id_grupo+"_Ini", aux);
				listaBodegasGrupo.put(id_bodegaEmpresa+"_"+id_grupo, id_bodegaEmpresa+"_"+id_grupo);
			}
		}
		
		
		flagId_Bodega = flagId_Grupo = (long) 0;
		id_bodegaEmpresa = id_grupo = (long) 0;
		//GUIAS DEL PERIODO:
		for(int i=0; i<guiasPeriodo.size(); i++) {
			if((flagId_Bodega - guiasPeriodo.get(i).id_bodegaEmpresa) != 0) {
				flagId_Bodega = guiasPeriodo.get(i).id_bodegaEmpresa;
				flagId_Grupo = (long) 0;
			}
			if((flagId_Grupo - guiasPeriodo.get(i).id_grupo) != 0) {
				if(i>0) {
					ModeloCalculo aux = new ModeloCalculo();
					aux.id_bodegaEmpresa = id_bodegaEmpresa;
					aux.id_grupo = id_grupo;
					aux.totalVenta = totalVenta;
					aux.totalArriendo = totalArriendo;
					aux.totalCfi = totalCfi;
					guias.put(id_bodegaEmpresa+"_"+id_grupo+"_Guia", aux);
					listaBodegasGrupo.put(id_bodegaEmpresa+"_"+id_grupo, id_bodegaEmpresa+"_"+id_grupo);
				}
				totalVenta = totalCfi = totalArriendo = (double) 0;
				flagId_Grupo = guiasPeriodo.get(i).id_grupo;
			}
			id_bodegaEmpresa = guiasPeriodo.get(i).id_bodegaEmpresa;
			id_grupo = guiasPeriodo.get(i).id_grupo;
			
			totalArriendo = totalArriendo + guiasPeriodo.get(i).totalArriendo;
			totalCfi = totalCfi +  guiasPeriodo.get(i).totalCfi;
			totalVenta = totalVenta +  guiasPeriodo.get(i).totalVenta;
			
			if(i == guiasPeriodo.size()-1) {
				ModeloCalculo aux = new ModeloCalculo();
				aux.id_bodegaEmpresa = id_bodegaEmpresa;
				aux.id_grupo = id_grupo;
				aux.totalVenta = totalVenta;
				aux.totalArriendo = totalArriendo;
				aux.totalCfi = totalCfi;
				guias.put(id_bodegaEmpresa+"_"+id_grupo+"_Guia", aux);
				listaBodegasGrupo.put(id_bodegaEmpresa+"_"+id_grupo, id_bodegaEmpresa+"_"+id_grupo);
			}
		}
		//SUMA INVENTARIO INICIAL MAS GUIAS 
		for (String v : listaBodegasGrupo.values()) {
			Double totVenta = (double) 0, totArriendo = (double) 0, totCfi = (double) 0;
			ModeloCalculo ini = invInicial.get(v+"_Ini");
			if(ini!=null) {
				totVenta = totVenta + ini.totalVenta;
				totArriendo = totArriendo + ini.totalArriendo;
				totCfi = totCfi + ini.totalCfi;
			}
			
			ModeloCalculo per = guias.get(v+"_Guia");
			if(per!=null) {
				totVenta = totVenta + per.totalVenta;
				totArriendo = totArriendo + per.totalArriendo;
				totCfi = totCfi + per.totalCfi;
			}
			ModeloCalculo aux = new ModeloCalculo();
			String[] bodegaGrupo = v.split("_");
			aux.id_bodegaEmpresa = Long.parseLong(bodegaGrupo[0]);
			aux.id_grupo = Long.parseLong(bodegaGrupo[1]);
			aux.totalVenta = totVenta;
			aux.totalArriendo = totArriendo;
			aux.totalCfi = totCfi;
			aux.totalTotal =  aux.totalVenta + aux.totalArriendo + aux.totalCfi;
			if(aux.totalTotal > 0) {
				listado.add(aux);
			}
			
		}
		
		//ordena por empresa alfabeticamente ascendente
		for(int i=0;i<(listado.size()-1);i++){
            for(int j=i+1;j<listado.size();j++){
                if((listado.get(i).id_bodegaEmpresa.toString()).
                		compareToIgnoreCase(listado.get(j).id_bodegaEmpresa.toString())>0){
                    //Intercambiamos valores
                	ModeloCalculo listaAuxiliar=listado.get(i);
                	listado.set(i, listado.get(j));
                	listado.set(j, listaAuxiliar);
                }
            }
		}
		return(listado);
	}
	
	public static List<ModeloCalculo> valorTotalporBodegaYGrupoYEquipo(Connection con, String db, String desdeAAMMDD, String hastaAAMMDD, Map<Long,Double> tasas,
			List<ModCalc_InvInicial> inventarioInicial, List<ModCalc_GuiasPer> guiasPeriodo) {
		List<ModeloCalculo> listado = new ArrayList<ModeloCalculo>();
		Map<String,String> listaBodegasEquipo = new HashMap<String,String>();
		Map<String,ModeloCalculo> invInicial = new HashMap<String,ModeloCalculo>();
		Map<String,ModeloCalculo> guias = new HashMap<String,ModeloCalculo>();
		Long flagId_Bodega = (long) 0, flagId_Equipo = (long) 0;
		Long id_bodegaEmpresa = (long) 0, id_equipo = (long) 0;
		Double totalVenta = (double) 0, totalCfi = (double) 0, totalArriendo = (double) 0;
		//INVENTARIO INICIAL:
		for(int i=0; i<inventarioInicial.size(); i++) {
			if((flagId_Bodega - inventarioInicial.get(i).id_bodegaEmpresa) != 0) {
				flagId_Bodega = inventarioInicial.get(i).id_bodegaEmpresa;
				flagId_Equipo = (long) 0;
			}
			if((flagId_Equipo - inventarioInicial.get(i).id_equipo) != 0) {
				if(i>0) {
					ModeloCalculo aux = new ModeloCalculo();
					aux.id_bodegaEmpresa = id_bodegaEmpresa;
					aux.id_equipo = id_equipo;
					aux.totalVenta = totalVenta;
					aux.totalArriendo = totalArriendo;
					aux.totalCfi = totalCfi;
					invInicial.put(id_bodegaEmpresa+"_"+id_equipo+"_Ini", aux);
					listaBodegasEquipo.put(id_bodegaEmpresa+"_"+id_equipo, id_bodegaEmpresa+"_"+id_equipo);
				}
				totalVenta = totalCfi = totalArriendo = (double) 0;
				flagId_Equipo = inventarioInicial.get(i).id_equipo;
			}
				id_bodegaEmpresa = inventarioInicial.get(i).id_bodegaEmpresa;
				id_equipo = inventarioInicial.get(i).id_equipo;
				
				totalArriendo = totalArriendo + inventarioInicial.get(i).totalArriendo;
			if(i == inventarioInicial.size()-1) {
				ModeloCalculo aux = new ModeloCalculo();
				aux.id_bodegaEmpresa = id_bodegaEmpresa;
				aux.id_equipo = id_equipo;
				aux.totalVenta = totalVenta;
				aux.totalArriendo = totalArriendo;
				aux.totalCfi = totalCfi;
				invInicial.put(id_bodegaEmpresa+"_"+id_equipo+"_Ini", aux);
				listaBodegasEquipo.put(id_bodegaEmpresa+"_"+id_equipo, id_bodegaEmpresa+"_"+id_equipo);
			}
		}
		
		
		flagId_Bodega = flagId_Equipo = (long) 0;
		id_bodegaEmpresa = id_equipo = (long) 0;
		//GUIAS DEL PERIODO:
		for(int i=0; i<guiasPeriodo.size(); i++) {
			if((flagId_Bodega - guiasPeriodo.get(i).id_bodegaEmpresa) != 0) {
				flagId_Bodega = guiasPeriodo.get(i).id_bodegaEmpresa;
				flagId_Equipo = (long) 0;
			}
			if((flagId_Equipo - guiasPeriodo.get(i).id_equipo) != 0) {
				if(i>0) {
					ModeloCalculo aux = new ModeloCalculo();
					aux.id_bodegaEmpresa = id_bodegaEmpresa;
					aux.id_equipo = id_equipo;
					aux.totalVenta = totalVenta;
					aux.totalArriendo = totalArriendo;
					aux.totalCfi = totalCfi;
					guias.put(id_bodegaEmpresa+"_"+id_equipo+"_Guia", aux);
					listaBodegasEquipo.put(id_bodegaEmpresa+"_"+id_equipo, id_bodegaEmpresa+"_"+id_equipo);
				}
				totalVenta = totalCfi = totalArriendo = (double) 0;
				flagId_Equipo = guiasPeriodo.get(i).id_equipo;
			}
			id_bodegaEmpresa = guiasPeriodo.get(i).id_bodegaEmpresa;
			id_equipo = guiasPeriodo.get(i).id_equipo;
			
			totalArriendo = totalArriendo + guiasPeriodo.get(i).totalArriendo;
			totalCfi = totalCfi +  guiasPeriodo.get(i).totalCfi;
			totalVenta = totalVenta +  guiasPeriodo.get(i).totalVenta;
			
			if(i == guiasPeriodo.size()-1) {
				ModeloCalculo aux = new ModeloCalculo();
				aux.id_bodegaEmpresa = id_bodegaEmpresa;
				aux.id_equipo = id_equipo;
				aux.totalVenta = totalVenta;
				aux.totalArriendo = totalArriendo;
				aux.totalCfi = totalCfi;
				guias.put(id_bodegaEmpresa+"_"+id_equipo+"_Guia", aux);
				listaBodegasEquipo.put(id_bodegaEmpresa+"_"+id_equipo, id_bodegaEmpresa+"_"+id_equipo);
			}
		}
		//SUMA INVENTARIO INICIAL MAS GUIAS 
		for (String v : listaBodegasEquipo.values()) {
			Double totVenta = (double) 0, totArriendo = (double) 0, totCfi = (double) 0;
			ModeloCalculo ini = invInicial.get(v+"_Ini");
			if(ini!=null) {
				totVenta = totVenta + ini.totalVenta;
				totArriendo = totArriendo + ini.totalArriendo;
				totCfi = totCfi + ini.totalCfi;
			}
			
			ModeloCalculo per = guias.get(v+"_Guia");
			if(per!=null) {
				totVenta = totVenta + per.totalVenta;
				totArriendo = totArriendo + per.totalArriendo;
				totCfi = totCfi + per.totalCfi;
			}
			ModeloCalculo aux = new ModeloCalculo();
			String[] bodegaEquipo = v.split("_");
			aux.id_bodegaEmpresa = Long.parseLong(bodegaEquipo[0]);
			aux.id_equipo = Long.parseLong(bodegaEquipo[1]);
			aux.totalVenta = totVenta;
			aux.totalArriendo = totArriendo;
			aux.totalCfi = totCfi;
			aux.totalTotal =  aux.totalVenta + aux.totalArriendo + aux.totalCfi;
			if(aux.totalTotal > 0) {
				listado.add(aux);
			}
			
		}
		
		//ordena por empresa alfabeticamente ascendente
		for(int i=0;i<(listado.size()-1);i++){
            for(int j=i+1;j<listado.size();j++){
                if((listado.get(i).id_bodegaEmpresa.toString()).
                		compareToIgnoreCase(listado.get(j).id_bodegaEmpresa.toString())>0){
                    //Intercambiamos valores
                	ModeloCalculo listaAuxiliar=listado.get(i);
                	listado.set(i, listado.get(j));
                	listado.set(j, listaAuxiliar);
                }
            }
		}
		return(listado);
	}
	
	
	
}
	
	
	
