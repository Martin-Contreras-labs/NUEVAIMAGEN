package controllers;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;
import models.utilities.DatabaseExecutionContext;
import models.utilities.Fechas;

import com.fasterxml.jackson.databind.JsonNode;

import models.apiRest.InventariosFechaCorte;
import models.apiRest.MovimientosEntreFechas;
import models.apiRest.ResumenDetallePorPeriodo;
import models.calculo.Calc_BodegaEmpresa;
import models.calculo.Calc_Precio;
import models.calculo.ModCalc_GuiasPer;
import models.calculo.ModCalc_InvInicial;
import models.calculo.ModeloCalculo;
import models.reports.ReportBodegas;
import models.reports.ReportFacturaConsolidado;
import models.reports.ReportFacturas;
import models.reports.ReportHohe;
import models.tables.AjustesEP;
import models.tables.BodegaEmpresa;
import models.tables.Cliente;
import models.tables.Diccionario;
import models.tables.Proyecto;
import play.db.Database;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import views.html.madaApis;

public class Apis extends Controller {
	
	public static Database db;
	public static DatabaseExecutionContext executionContext;
	
    @SuppressWarnings("static-access")
	@Inject
    public Apis(Database db, DatabaseExecutionContext executionContext) {
	    this.db = db;
	    this.executionContext = executionContext;
	}
    
    static DecimalFormat sinFormato = new DecimalFormat("0.00");
	
	
    public static Map<String,String> mapeoDicc(String baseDato){
    	Map<String,String> map = new HashMap<String,String>();
    	try {
    		Connection con = db.getConnection();
			map = Diccionario.mapDiccionario(con, baseDato);
			con.close();
			return(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return(map);
	}
    
    
    public Result Mada_APIs(Http.Request request){
    	
    	return ok(madaApis.render());
    }
	
	private String generarToken() {
		SecureRandom random1 = new SecureRandom();
		SecureRandom random2 = new SecureRandom();
		SecureRandom random3 = new SecureRandom();
		byte bytes1[] = new byte[20];
		random1.nextBytes(bytes1);
		String token1 = bytes1.toString();
		byte bytes2[] = new byte[20];
		random2.nextBytes(bytes2);
		String token2 = bytes2.toString();
		byte bytes3[] = new byte[20];
		random3.nextBytes(bytes3);
		String token3 = bytes3.toString();
		String token = token1.substring(1)+token2.substring(1)+token3.substring(1);
		return (token);
	}
	
	private String validaToken(Http.Request request) {
		
		Optional<String> rsToken = request.headers().get("Authorization");
   		if(!rsToken.isPresent()) {
   			return ("error");
   		}
   		
		String bearer[] = rsToken.get().split(" ");
		if(bearer.length !=2 || !bearer[0].equals("Bearer")) {
			return ("error");
		}
		String token = bearer[1];
		
		String baseDato = "0";
		try {
   			Connection con = db.getConnection();
   			
   			Calendar fechaToken = Calendar.getInstance();
   			java.sql.Timestamp auxFecha = null;
   			
   			PreparedStatement smt = con.
					prepareStatement("select dateCreate, baseDato from token where BINARY token = BINARY ?;");
   			smt.setString(1, token);
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				auxFecha = rs.getTimestamp(1);
				baseDato = rs.getString(2);
			}else {
				smt.close();
				rs.close();
				con.close();
				return (baseDato);
			}
			smt.close();
			rs.close();
			
			long timestampLong = auxFecha.getTime();
			
			fechaToken.setTimeInMillis(timestampLong);
   			Calendar fechaActual = Calendar.getInstance();
   			
   			Long diffHoras = Math.abs(fechaActual.getTimeInMillis() - fechaToken.getTimeInMillis());
   			diffHoras = diffHoras/(1000*60*60);
   			
   			if((long)diffHoras > (long)4) {
   				PreparedStatement smt1 = con.
   						prepareStatement("delete from token where BINARY token = BINARY ?;");
   	   			smt1.setString(1, token);
   				smt1.executeUpdate();
   				smt1.close();
   				baseDato = "0";
   			}
   			
   			con.close();
   		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return (baseDato);
	}


	public Result authenticar(Http.Request request) {
		
		//LEER HEADER:
		Optional<String> rsAuth = request.headers().get("Authorization");
   		if(!rsAuth.isPresent()) {
   			String error = "{\"invalid_request\": \"the syntax is not correct\"}";
			return ok(error).as("application/json");
   		}
   		
		String basic[] = rsAuth.get().split(" ");
		if(basic.length !=2 || !basic[0].equals("Basic")) {
			String error = "{\"invalid_request\": \"the syntax is not correct\"}";
			return ok(error).as("application/json");
		}
		
		String decodedString = "";
		try {
			byte[] decodedBytes = Base64.getDecoder().decode(basic[1]);
			decodedString = new String(decodedBytes);
		}catch (Exception e){
			String error = "{\"error\":\"invalid_request\",\"invalid_client\": \"the credentials supplied were invalid\"}";
			return ok(error).as("application/json");
		}
		
		String[] aux = decodedString.split(":");
		String apiUser = aux[0];
		String apiKey = aux[1];
	   		
   		if(apiUser!=null && apiKey!=null) {
   			try {
	   			Connection con = db.getConnection();
	   			
	   			// VALIDA CREDENCIAL:
	   			String baseDato = null;
	   			PreparedStatement smt = con.
						prepareStatement("select baseDato from credenciales where apiUser = ? and BINARY apiKey = BINARY ?;");
	   			smt.setString(1, apiUser);
	   			smt.setString(2, apiKey);
				ResultSet rs = smt.executeQuery();
				if (rs.next()) {
					baseDato = rs.getString(1);
				}
				smt.close();
				rs.close();
				if(baseDato == null) {
	   				con.close();
	   				String error = "{\"error\":\"invalid_request\",\"unauthorized_client\": \"this apiUser is not authorized to request an access token or apiKey does not match\"}";
	   				return ok(error).as("application/json");
	   			}
	   			
				//VERIFICA EMPRESA ESTE VIGENTE:
				Long estaVigente = (long)1;
				PreparedStatement smt1 = con.
						prepareStatement("select vigente from empresa where baseDato = ?;");
	   			smt1.setString(1, baseDato);
				ResultSet rs1 = smt1.executeQuery();
				if (rs1.next()) {
					estaVigente = rs1.getLong(1);
				}
				smt1.close();
				rs1.close();
				if((long) estaVigente == (long)0) {
					String error = "{\"error\":\"invalid_request\",\"unauthorized_client\": \"this company is not authorized to request an access token\"}";
					con.close();
	   				return ok(error).as("application/json");
	   			}
				
				//GENERA TOKEN Y LO INSERTA:
	   			String token = generarToken();
	   			PreparedStatement smt2 = con.
						prepareStatement("insert into token (token,baseDato) values (?,?);");
	   			smt2.setString(1, token);
	   			smt2.setString(2, baseDato);
				smt2.executeUpdate();
	   			smt2.close();
	   			
	   			String response = "{\"access_token\": \""+token+"\",\"token_type\": \"Bearer\",\"expires_in\": 14400,\"example_parameter\": \"\"}";
	   			
	   			con.close();
	   			return ok(response).as("application/json");
	   		} catch (SQLException e) {
				e.printStackTrace();
			}
   		}else {
   			String error = "{\"error\":\"invalid_request\",\"invalid_client\": \"the client_id or client_secret field is missing\"}";
			return ok(error).as("application/json");
   		}
   		
   		String error = "{\"500 Internal Server Error\": \"500 Internal Server Error: an unexpected error occurred on the server\"}";
		return ok(error).as("application/json");
    }
	
	public Result pingToken(Http.Request request) {
		/* curl -X POST -H 'Content-type:application/json' -H 'Authorization: Bearer xxxxxxx' 'http://localhost:9001/pingToken/' */
		String baseDato = validaToken(request);
		if(baseDato.equals("error")) {
			String error = "{\"error\":\"invalid_token\": \"the syntax is not correct\"}";
			return ok(error).as("application/json");
		}
		if(!baseDato.equals("0")) {
			return ok("{\"good\":\"token validado\""+",\"empresa\":\""+baseDato.substring(4)+"\"}").as("application/json");
		}else {
			return ok("{\"error\":\"token no validado o tiempo cumplido\"}").as("application/json");
		}
	}
	
	public Result listadoDeBodegas(Http.Request request) {
		/* curl -X POST -H 'Content-type:application/json' -H 'Authorization: Bearer xxxxxxx' 'http://localhost:9001/listadoDeBodegas/' */
		String baseDato = validaToken(request);
		if(baseDato.equals("error")) {
			String error = "{\"error\":\"invalid_token\": \"the syntax is not correct\"}";
			return ok(error).as("application/json");
		}
		if(!baseDato.equals("0")) {
			try {
	   			Connection con = db.getConnection();
	   			List<BodegaEmpresa> lista = BodegaEmpresa.all(con, baseDato);
	   			JsonNode rsJson =  Json.toJson(lista);
	   			con.close();
	   			return ok("{\"listaBodegas\":"+rsJson.toString()+",\"empresa\":\""+baseDato.substring(4)+"\"}").as("application/json");
	   		} catch (SQLException e) {
				e.printStackTrace();
				return ok("{\"ERROR\":\"contactar a INQSOL\"}").as("application/json");
			}
			
   		}else {
   			return ok("{\"ERROR\":\"token no validado o tiempo cumplido\"}").as("application/json");
   		}
	}
	/* {"listaBodegas":[{"id":1,"esInterna":1,"nombre":"BAJA","id_cliente":1,"id_proyecto":0,"tasaDescto":0.0,"tasaArriendo":0.0,"tasaCfi":0.0,"cobraDiaDespacho":1,"nDiaGraciaEnvio":0,"nDiaGraciaRegreso":0,"factorM2Viga":0.17857143,"baseCalculo":1,"tratoDevoluciones":1,"nombreTipoBodega":"BODEGA/INTERNA","nickCliente":"CDEMO","nickProyecto":"","comercial":".","rutCliente":"75000000-2","pep":"","ivaBodega":0.19}*/
	

	
	public Result movimientosEntreFechas(Http.Request request, String desdeAAMMDD, String hastaAAMMDD) {
		/* curl -X POST -H 'Content-type:application/json' -H 'Authorization: Bearer xxxxxxx' 'http://localhost:9001/movimientosEntreFechas/?desdeAAMMDD=2021-01-01&hastaAAMMDD=2021-12-31' */
		String baseDato = validaToken(request);
		if(baseDato.equals("error")) {
			String error = "{\"error\":\"invalid_token\": \"the syntax is not correct\"}";
			return ok(error).as("application/json");
		}
		if(!baseDato.equals("0")) {
			Map<String,String> mapeoDiccionario = mapeoDicc(baseDato);
			try {
	   			Connection con = db.getConnection();
	   			List<MovimientosEntreFechas> lista = MovimientosEntreFechas.movimientosEntreFechas(con, baseDato, desdeAAMMDD, hastaAAMMDD, mapeoDiccionario);
	   			JsonNode rsJson =  Json.toJson(lista);
	   			con.close();
	   			return ok("{\"movimientosEntreFechas\":"+rsJson.toString()+",\"desde\":\""+desdeAAMMDD+"\",\"hasta\":\""+hastaAAMMDD+",\"empresa\":\""+baseDato.substring(4)+"\"}").as("application/json");
	   		} catch (SQLException e) {
				e.printStackTrace();
				return ok("{\"ERROR\":\"contactar a INQSOL\"}").as("application/json");
			}
			
   		}else {
   			return ok("{\"ERROR\":\"token no validado o tiempo cumplido\"}").as("application/json");
   		}
	}
	
	public Result findBodega(Http.Request request, Long id_bodegaEmpresa) {
		/* curl -X POST -H 'Content-type:application/json' -H 'Authorization: Bearer xxxxxxx' 'http://localhost:9001/findBodega/?id_bodega=2' */
		String baseDato = validaToken(request);
		if(baseDato.equals("error")) {
			String error = "{\"error\":\"invalid_token\": \"the syntax is not correct\"}";
			return ok(error).as("application/json");
		}
		if(!baseDato.equals("0")) {
			try {
	   			Connection con = db.getConnection();
	   			BodegaEmpresa aux = BodegaEmpresa.findXIdBodega(con, baseDato, id_bodegaEmpresa);
	   			JsonNode rsJson =  Json.toJson(aux);
	   			con.close();
	   			return ok("{\"findBodega\":"+rsJson.toString()+",\"empresa\":\""+baseDato.substring(4)+"\"}").as("application/json");
	   		} catch (SQLException e) {
				e.printStackTrace();
				return ok("{\"ERROR\":\"contactar a INQSOL\"}").as("application/json");
			}
			
   		}else {
   			return ok("{\"ERROR\":\"token no validado o tiempo cumplido\"}").as("application/json");
   		}
	}
	
	public Result findCliente(Http.Request request, Long id_cliente) {
		/* curl -X POST -H 'Content-type:application/json' -H 'Authorization: Bearer xxxxxxx' 'http://localhost:9001/findCliente/?id_cliente=1' */
		String baseDato = validaToken(request);
		if(baseDato.equals("error")) {
			String error = "{\"error\":\"invalid_token\": \"the syntax is not correct\"}";
			return ok(error).as("application/json");
		}
		if(!baseDato.equals("0")) {
			try {
	   			Connection con = db.getConnection();
	   			Cliente aux = Cliente.find(con, baseDato, id_cliente);
	   			JsonNode rsJson =  Json.toJson(aux);
	   			con.close();
	   			return ok("{\"findCliente\":"+rsJson.toString()+",\"empresa\":\""+baseDato.substring(4)+"\"}").as("application/json");
	   		} catch (SQLException e) {
				e.printStackTrace();
				return ok("{\"ERROR\":\"contactar a INQSOL\"}").as("application/json");
			}
			
   		}else {
   			return ok("{\"ERROR\":\"token no validado o tiempo cumplido\"}").as("application/json");
   		}
	}
	
	public Result findProyecto(Http.Request request, Long id_proyecto) {
		/* curl -X POST -H 'Content-type:application/json' -H 'Authorization: Bearer xxxxxxx' 'http://localhost:9001/findProyecto/?id_proyecto=1' */
		String baseDato = validaToken(request);
		if(baseDato.equals("error")) {
			String error = "{\"error\":\"invalid_token\": \"the syntax is not correct\"}";
			return ok(error).as("application/json");
		}
		if(!baseDato.equals("0")) {
			try {
	   			Connection con = db.getConnection();
	   			Proyecto aux = Proyecto.find(con, baseDato, id_proyecto);
	   			JsonNode rsJson =  Json.toJson(aux);
	   			con.close();
	   			return ok("{\"findProyecto\":"+rsJson.toString()+",\"empresa\":\""+baseDato.substring(4)+"\"}").as("application/json");
	   		} catch (SQLException e) {
				e.printStackTrace();
				return ok("{\"ERROR\":\"contactar a INQSOL\"}").as("application/json");
			}
			
   		}else {
   			return ok("{\"ERROR\":\"token no validado o tiempo cumplido\"}").as("application/json");
   		}
	}
	
	public Result inventarios_al_dia(Http.Request request, String fechaCorte) {
		/* curl -X POST -H 'Content-type:application/json' -H 'Authorization: Bearer xxxxxxx' 'http://localhost:9001/inventarios_al_dia/?fechaCorte=2021-12-31' */
		String baseDato = validaToken(request);
		if(baseDato.equals("error")) {
			String error = "{\"error\":\"invalid_token\": \"the syntax is not correct\"}";
			return ok(error).as("application/json");
		}
		if(!baseDato.equals("0")) {
			Map<String,String> mapeoDiccionario = mapeoDicc(baseDato);
			try {
	   			Connection con = db.getConnection();
	   			List<InventariosFechaCorte> lista = InventariosFechaCorte.inventariosFechaCorte(con, baseDato, fechaCorte, mapeoDiccionario);
	   			JsonNode rsJson =  Json.toJson(lista);
	   			con.close();
	   			return ok("{\"inventarios\":"+rsJson.toString()+",\"desde\":\""+fechaCorte+",\"empresa\":\""+baseDato.substring(4)+"\"}").as("application/json");
	   		} catch (SQLException e) {
				e.printStackTrace();
				return ok("{\"ERROR\":\"contactar a INQSOL\"}").as("application/json");
			}
			
   		}else {
   			return ok("{\"ERROR\":\"token no validado o tiempo cumplido\"}").as("application/json");
   		}
	}
	
	//menu: Reportes/Proformas/Resumen y Detalle (por periodo) 
	public Result estadoDePagoPorPeriodo(Http.Request request, String desdeAAMMDD, String hastaAAMMDD, Double uf, Double usd, Double eur) {
		/* curl -X POST -H 'Content-type:application/json' -H 'Authorization: Bearer B@10f1fa52B@6ef329B@b9526f8' 'http://localhost:9001/estadoDePagoPorPeriodo/?desdeAAMMDD=2021-12-01&hastaAAMMDD=2021-12-31&uf=31200&usd=810&eur=930' */
		String baseDato = validaToken(request);
		if(baseDato.equals("error")) {
			String error = "{\"error\":\"invalid_token\": \"the syntax is not correct\"}";
			return ok(error).as("application/json");
		}
		if(!baseDato.equals("0")) {
			try {
	   			Connection con = db.getConnection();
	   			ResumenDetallePorPeriodo resumen = ResumenDetallePorPeriodo.inventariosFechaCorte(con, baseDato, desdeAAMMDD, hastaAAMMDD, uf, usd, eur,"0","1");
	   			JsonNode rsJson =  Json.toJson(resumen);
	   			con.close();
	   			return ok("{\"estadosDePago\":"+rsJson.toString()+",\"desde\":\""+desdeAAMMDD+"\",\"hasta\":\""+hastaAAMMDD+"\",\"empresa\":\""+baseDato.substring(4)+"\"}").as("application/json");
	   		} catch (SQLException e) {
				e.printStackTrace();
				return ok("{\"ERROR\":\"contactar a INQSOL\"}").as("application/json");
			}
			
   		}else {
   			return ok("{\"ERROR\":\"token no validado o tiempo cumplido\"}").as("application/json");
   		}
	}
	
	//menu: Reportes/Proformas/Resumen y Detalle (por periodo) SOLO TOTALES
	public Result totalesEPporPeriodo(Http.Request request, String desdeAAMMDD, String hastaAAMMDD, Double uf, Double usd, Double eur) {
		/* curl -X POST -H 'Content-type:application/json' -H 'Authorization: Bearer B@10f1fa52B@6ef329B@b9526f8' 'http://localhost:9001/totalesEPporPeriodo/?desdeAAMMDD=2021-12-01&hastaAAMMDD=2021-12-31&uf=31200&usd=810&eur=930' */
		String baseDato = validaToken(request);
		if(baseDato.equals("error")) {
			String error = "{\"error\":\"invalid_token\": \"the syntax is not correct\"}";
			return ok(error).as("application/json");
		}
		if(!baseDato.equals("0")) {
			try {
	   			Connection con = db.getConnection();
	   			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(baseDato);
	   			Map<Long,Double> tasas = new HashMap<Long,Double>();
	    		tasas.put((long)1, (double) 1); 	// 'Peso Chileno', 'CLP', '0'
	    		tasas.put((long)2, usd); 			// 'Dólar', 'USD', '2'
	    		tasas.put((long)3, eur); 			// 'Euro', 'EUR', '3'
	    		tasas.put((long)4, uf); 			// 'Unidad Fomento', 'UF', '4'
	    		String permisoPorBodega = "";
    			List<Long> listIdBodegaEmpresa = ModCalc_InvInicial.listIdBodegaEmpresa(con, baseDato, permisoPorBodega);
    			Map<Long,Calc_BodegaEmpresa> mapBodegaEmpresa = Calc_BodegaEmpresa.mapAllBodegasVigentes(con, baseDato);
    			Map<String,Calc_Precio> mapPrecios = Calc_Precio.mapPrecios(con, baseDato, listIdBodegaEmpresa);
    			Map<Long,Calc_Precio> mapMaestroPrecios = Calc_Precio.mapMaestroPrecios(con, baseDato);
    			Map<String, Double> mapFijaTasas = BodegaEmpresa.mapFijaTasasAll(con, baseDato);
    			
    			List<ModCalc_InvInicial> inventarioInicial = ModCalc_InvInicial.resumenInvInicial(con, baseDato, desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa, mapBodegaEmpresa, mapPrecios, mapMaestroPrecios);
    			List<ModCalc_GuiasPer> guiasPeriodo = ModCalc_GuiasPer.resumenGuiasPer(con, baseDato, desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, listIdBodegaEmpresa, mapBodegaEmpresa, mapPrecios, mapMaestroPrecios);
    			List<ModeloCalculo> valorTotalPorBodega = ModeloCalculo.valorTotalporBodega(con, baseDato, desdeAAMMDD, hastaAAMMDD, mapFijaTasas, tasas, inventarioInicial,guiasPeriodo);
    			
    			List<List<String>> resumenTotales = ReportFacturas.resumenTotalesPorProyecto(con, baseDato, valorTotalPorBodega);
	   			List<List<String>> proyectos = ReportFacturas.reportFacturaProyecto(con, baseDato, valorTotalPorBodega, "0", "0");
	   			
	   			List<List<String>> rs = new ArrayList<List<String>>();
    			
    			List<String> aux = new ArrayList<String>();
    			aux.add("SUCURSAL");
    			aux.add("NOMBRE "+ mapeoDiccionario.get("BODEGA") + "/PROYECTO");
    			aux.add(mapeoDiccionario.get("RUT") + " CLIENTE");
    			aux.add("NOMBRE CLIENTE");
    			aux.add("NOMBRE PROYECTO");
    			aux.add("CFI");
    			aux.add("SubTotal (" + mapeoDiccionario.get("ARRIENDO") + ")");
    			aux.add("SubTotal (VENTA)");
    			aux.add("Ajustes (" + mapeoDiccionario.get("ARRIENDO") + ")");
    			aux.add("Ajustes (VENTA)");
    			aux.add("TOTAL (en " + mapeoDiccionario.get("PESOS") + ")");
    			rs.add(aux);
    			
    			for(List<String> p: proyectos) {
    				for(List<String> t: resumenTotales) {
    					if(p.get(1).equals(t.get(0))) {
    						aux = new ArrayList<String>();
    						
    						aux.add(p.get(14));
    						aux.add(p.get(5));
    						aux.add(p.get(6));
    						aux.add(p.get(7));
    						aux.add(p.get(8));
    						
    						aux.add(t.get(3).replaceAll(",", ""));
    						aux.add(t.get(1).replaceAll(",", ""));
    						aux.add(t.get(2).replaceAll(",", ""));
    						aux.add(t.get(5).replaceAll(",", ""));
    						aux.add(t.get(6).replaceAll(",", ""));
    						aux.add(t.get(4).replaceAll(",", ""));
    						rs.add(aux);
    					}
    				}
    			}
    			String rsJson = Json.toJson(rs).toString();
    			
	   			con.close();
	   			return ok("{\"datos\":"+rsJson.toString()+",\"desde\":\""+desdeAAMMDD+"\",\"hasta\":\""+hastaAAMMDD+"\",\"empresa\":\""+baseDato.substring(4)+"\"}").as("application/json");
	   		} catch (SQLException e) {
				e.printStackTrace();
				return ok("{\"ERROR\":\"contactar a INQSOL\"}").as("application/json");
			}
			
   		}else {
   			return ok("{\"ERROR\":\"token no validado o tiempo cumplido\"}").as("application/json");
   		}
		
	}
	
	
	//menu: HOHE Reportes/Tabla Todos/Matriz Inventario (por_coti)
	public Result matrizInventarioPorCoti(Http.Request request, String fechaCorte) {
		/* curl -X POST -H 'Content-type:application/json' -H 'Authorization: Bearer B@10f1fa52B@6ef329B@b9526f8' 'http://localhost:9001/matrizInventarioPorCoti/?fechaCorte=2021-12-01' */
		String baseDato = validaToken(request);
		if(baseDato.equals("error")) {
			String error = "{\"error\":\"invalid_token\": \"the syntax is not correct\"}";
			return ok(error).as("application/json");
		}
		if(!baseDato.equals("0")) {
			try {
	   			Connection con = db.getConnection();
	   			// KEY = id_bodega _ id_equipo _ id_cotizacion _ tipo:
	   			Map<String,List<String>> mapArr = ReportHohe.listaMatrizEquiposHOHE2Coti(con, baseDato, "ARRIENDO", fechaCorte);
	   			Map<String,List<String>> mapVta = ReportHohe.listaMatrizEquiposHOHE2Coti(con, baseDato, "VENTA", fechaCorte);
	   			
	   			//UNE AMBOS RESULTADOS
	   			Map<String,List<String>> mapUne = new HashMap<String,List<String>>();
	   			for (Map.Entry<String,List<String>> map : mapArr.entrySet()) {
	   				mapUne.put(map.getKey(), map.getValue());
	   			}
	   			for (Map.Entry<String,List<String>> map : mapVta.entrySet()) {
	   				mapUne.put(map.getKey(), map.getValue());
	   			}
	   			
	   			List<List<String>> rs = new ArrayList<List<String>>();
    			
    			List<String> aux = new ArrayList<String>();
    			aux.add("fecha_corte");
    			aux.add("id_bodega");
    			aux.add("bodega");
    			aux.add("tipo_bodega");
    			aux.add("grupo");
    			aux.add("id_equipo");
    			aux.add("cod_equipo");
    			aux.add("equipo");
    			aux.add("m2");
    			aux.add("kg");
    			aux.add("cant_arriendo");
    			aux.add("cant_venta");
    			aux.add("cant_total");
    			aux.add("p_repos");
    			aux.add("p_arriendo");
    			aux.add("num_coti");
    			rs.add(aux);
    			
    			List<List<String>> listaHoheAux = new ArrayList<List<String>>();
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
    				aux = new ArrayList<String>();
    				aux.add(list1.get(0));	//0 id_bodega
    				aux.add(list1.get(1));	//1 bodega
    				aux.add(list1.get(2));	//2 tipoBodega (interna externa)
    				aux.add(list1.get(3));	//3 grupo
    				aux.add(list1.get(4));	//4 id_equipo
    				aux.add(list1.get(5));	//5 codigo
    				aux.add(list1.get(6));	//6 equipo
    				aux.add(list1.get(7));	//7 kg
    				aux.add(list1.get(8));	//8 m2
    				aux.add(arr.toString());	//9 cantidad arriendo
    				aux.add(vta.toString());	//10 cantidad venta
    				aux.add(tot.toString());	//11 cantidad todo
    				aux.add(fechaCorte);		//12 fecha corte
    				aux.add(list1.get(11));		//13 numeroCotizacion
    				aux.add(auxKey[2]);			//14 id_cotizacion
    				listaHoheAux.add(aux);
    			}
    				
				Map<String,List<Double>> mapPrecios = ReportHohe.mapListaPrecioCoti(con, baseDato);
				for (int i=0; i<listaHoheAux.size(); i++) {
					aux = listaHoheAux.get(i);
					List<Double> precios = mapPrecios.get(aux.get(0)+"_"+aux.get(4)+"_"+aux.get(14));
					if(precios!=null) {
						aux.add(precios.get(0).toString());	//15 precio de reposicion 
						aux.add(precios.get(1).toString()); //16 precio de arriendo 
					}else {
						aux.add("0");
						aux.add("0");
					}
				}
    			
				for(List<String> l: listaHoheAux) {
					aux = new ArrayList<String>();
					aux.add(l.get(12));
					aux.add(l.get(0));
					aux.add(l.get(1));
					aux.add(l.get(2));
					aux.add(l.get(3));
					aux.add(l.get(4));
					aux.add(l.get(5));
					aux.add(l.get(6));
					aux.add(l.get(7));
					aux.add(l.get(8));
					aux.add(l.get(9));
					aux.add(l.get(10));
					aux.add(l.get(11));
					aux.add(l.get(15));
					aux.add(l.get(16));
					aux.add(l.get(13));
					rs.add(aux);
				}
    			
    			String rsJson = Json.toJson(rs).toString();
    			
	   			con.close();
	   			return ok("{\"datos\":"+rsJson.toString()+",\"fechaCorte\":\""+fechaCorte+"\",\"empresa\":\""+baseDato.substring(4)+"\"}").as("application/json");
	   		} catch (SQLException e) {
				e.printStackTrace();
				return ok("{\"ERROR\":\"contactar a INQSOL\"}").as("application/json");
			}
			
   		}else {
   			return ok("{\"ERROR\":\"token no validado o tiempo cumplido\"}").as("application/json");
   		}
		
	}
	
	//menu: Reportes/Proformas/Consolidado (detalle por grupos)
	public Result consolidadoPorGrupoMeses(Http.Request request, String fechaCorte, Long cantMeses) {
		/* curl -X POST -H 'Content-type:application/json' -H 'Authorization: Bearer B@10f1fa52B@6ef329B@b9526f8' 'http://localhost:9001/consolidadoPorGrupo3meses/?fechaCorte=2021-12-01' */
		String baseDato = validaToken(request);
		if(baseDato.equals("error")) {
			String error = "{\"error\":\"invalid_token\": \"the syntax is not correct\"}";
			return ok(error).as("application/json");
		}
		if(!baseDato.equals("0")) {
			try {
	   			Connection con = db.getConnection();
	   			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(baseDato);
	   			String permisoPorBodega = "";
	   			Fechas finMesFecha = Fechas.obtenerFechaDesdeStrAAMMDD(fechaCorte);
	   			finMesFecha = Fechas.obtenerFinMes(finMesFecha.getFechaCal());
	   			List<List<String>> datos = ReportFacturaConsolidado.reportConsDetalladoRtp(con, baseDato, finMesFecha, cantMeses, permisoPorBodega, mapeoDiccionario.get("pais"), "0", "0");
    			
	   			String aux2 ="";
	   			List<String> listSeleccion2 = new ArrayList<String>();
    	    	List<String> listEmpresa = new ArrayList<String>();
    	    	Map<String,List<String>> mapEmpresas = new HashMap<String,List<String>>();
    	    	Map<String,List<String>> mapDatosGrupo = new HashMap<String,List<String>>();
	   			
	   			for(int i=2; i<datos.size()-1; i++) {
    	    		if(!aux2.equals("'"+datos.get(i).get(1)+"'")) {
    	    			if(i>2) {
    	    				mapEmpresas.put(aux2, listEmpresa);
    	    				listEmpresa = new ArrayList<String>();
    	    			}
    	    			listSeleccion2.add("'"+datos.get(i).get(1)+"'");
    	    			aux2 = "'"+datos.get(i).get(1)+"'";
    	    		}
    	    		
    	    		if(aux2.equals("'"+datos.get(i).get(1)+"'")) {
    	    			listEmpresa.add("'"+datos.get(i).get(0)+"'");
    	    			mapEmpresas.put(aux2, listEmpresa);
    	    		}
    	    		
    	    		List<String> listDatos = new ArrayList<String>();
    	    		for(int j=4; j<datos.get(i).size()-3; j = j+3) {
    	    			listDatos.add(datos.get(i).get(j).replaceAll(",", ""));
    	    		}
    	    		mapDatosGrupo.put("'"+datos.get(i).get(1)+"'"+"_"+"'"+datos.get(i).get(0)+"'", listDatos);
    	    	}
	   			
	   			List<List<String>> rs = new ArrayList<List<String>>();
    	    	List<String> auxL = new ArrayList<String>();
    	    	List<String> t1 = datos.get(0);
    	    	List<String> t2 = datos.get(1);
    	    	for(int i=0; i<t1.size(); i++) {
    	    		auxL.add(t1.get(i)+" "+t2.get(i));
    	    	}
    	    	rs.add(auxL);
    	    	int index = 0;
    	    	for(List<String> l: datos) {
    	    		auxL = new ArrayList<String>();
    	    		if(index > 1 && index < datos.size()-1) {
    	    			auxL.add(l.get(0));
    	    			auxL.add(l.get(1));
    	    			for(int i=2; i<l.size(); i++) {
    	    				auxL.add(l.get(i).replaceAll(",", ""));
    	    			}
    	    			rs.add(auxL);
    	    		}
    	    		index++;
    	    	}
    			String rsJson = Json.toJson(rs).toString();
    			
    			Fechas inicioFecha = Fechas.addDias(finMesFecha.getFechaCal(), 1);
    			Fechas finFecha = Fechas.addMeses(finMesFecha.getFechaCal(), 3);
    			finFecha = Fechas.addDias(finFecha.getFechaCal(), -1);
    			
	   			con.close();
	   			return ok("{\"datos\":"+rsJson.toString()+",\"desde\":\""+inicioFecha.getFechaStrAAMMDD()+"\",\"hasta\":\""+finFecha.getFechaStrAAMMDD()+"\",\"empresa\":\""+baseDato.substring(4)+"\"}").as("application/json");
	   		} catch (SQLException e) {
				e.printStackTrace();
				return ok("{\"ERROR\":\"contactar a INQSOL\"}").as("application/json");
			}
			
   		}else {
   			return ok("{\"ERROR\":\"token no validado o tiempo cumplido\"}").as("application/json");
   		}
		
	}
	
	//menu: Reportes/Proformas/Reporte Ajustes X Período
	public Result ajustesPorPeriodo(Http.Request request, String desdeAAMMDD, String hastaAAMMDD) {
		/* curl -X POST -H 'Content-type:application/json' -H 'Authorization: Bearer B@10f1fa52B@6ef329B@b9526f8' 'http://localhost:9001/ajustesPorPeriodo/?desdeAAMMDD=2021-12-01&hastaAAMMDD=2021-12-31' */
		String baseDato = validaToken(request);
		if(baseDato.equals("error")) {
			String error = "{\"error\":\"invalid_token\": \"the syntax is not correct\"}";
			return ok(error).as("application/json");
		}
		if(!baseDato.equals("0")) {
			try {
	   			Connection con = db.getConnection();
	   			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(baseDato);
	   			String permisoPorBodega = "";
    			List<AjustesEP> lista = AjustesEP.allPorPeriodos(con, baseDato, desdeAAMMDD, hastaAAMMDD, permisoPorBodega,  "0", "0");
	   			List<List<String>> rs = new ArrayList<List<String>>();
    			
    			List<String> aux = new ArrayList<String>();
    			aux.add("SUCURSAL");
    			aux.add("NOMBRE "+ mapeoDiccionario.get("BODEGA") + "/PROYECTO");
    			aux.add("FECHA");
    			aux.add("TIPO AJUSTE");
    			aux.add("APLICAR SOBRE");
    			aux.add("MONEDA");
    			aux.add("VALOR");
    			aux.add("CONCEPTO");
    			aux.add("NOTAS");
    			rs.add(aux);
    			
    			for(AjustesEP a: lista) {
    				aux = new ArrayList<String>();
    				aux.add(a.nameSucursal);
					aux.add(a.bodegaEmpresa);
					aux.add(Fechas.AAMMDD(a.fechaAjuste));
					aux.add(a.tipoAjuste);
					aux.add(a.tipoAjusteVenta);
					aux.add(a.moneda);
					aux.add(a.totalAjuste.replaceAll(",", ""));
					aux.add(a.concepto);
					aux.add(a.observaciones);
					rs.add(aux);
    			}
    			String rsJson = Json.toJson(rs).toString();
	   			con.close();
	   			return ok("{\"datos\":"+rsJson.toString()+",\"desde\":\""+desdeAAMMDD+"\",\"hasta\":\""+hastaAAMMDD+"\",\"empresa\":\""+baseDato.substring(4)+"\"}").as("application/json");
	   		} catch (SQLException e) {
				e.printStackTrace();
				return ok("{\"ERROR\":\"contactar a INQSOL\"}").as("application/json");
			}
			
   		}else {
   			return ok("{\"ERROR\":\"token no validado o tiempo cumplido\"}").as("application/json");
   		}
		
	}
	
	//menu: Reportes/Inventarios/Estado Bodegas
	public Result estadoBodegasJson(Http.Request request) {
		/* curl -X POST -H 'Content-type:application/json' -H 'Authorization: Bearer B@10f1fa52B@6ef329B@b9526f8' 'http://localhost:9001/estadoBodegasJson/' */
		String baseDato = validaToken(request);
		if(baseDato.equals("error")) {
			String error = "{\"error\":\"invalid_token\": \"the syntax is not correct\"}";
			return ok(error).as("application/json");
		}
		if(!baseDato.equals("0")) {
			try {
	   			Connection con = db.getConnection();
	   			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(baseDato);
	   			List<List<String>> datos = ReportBodegas.estadoBodegas(con, baseDato);
	   			List<List<String>> rs = new ArrayList<List<String>>();
    			
    			List<String> aux = new ArrayList<String>();
    			aux.add("SUCURSAL");
    			aux.add("NOMBRE "+ mapeoDiccionario.get("BODEGA") + "/PROYECTO");
    			aux.add("VIGENTE");
    			aux.add("NOMBRE CLIENTE");
    			aux.add("RUT CLIENTE");
    			aux.add("NOMBRE PROYECTO");
    			aux.add("COMERCIAL");
    			aux.add("NRO GUIA (PRIMER DESPACHO)");
    			aux.add("REF GUIA (PRIMER DESPACHO)");
    			aux.add("FECHA GUIA (PRIMER DESPACHO)");
    			aux.add("NRO GUIA (ULTIMA DEVOLUCION)");
    			aux.add("REF GUIA (ULTIMA DEVOLUCION)");
    			aux.add("FECHA GUIA (ULTIMA DEVOLUCION)");
    			aux.add("CANTIDAD EQUIPOS");
    			rs.add(aux);
    			
    			for(List<String> a: datos) {
    				aux = new ArrayList<String>();
    				for(String d: a) {
    					aux.add(d);
    				}
					rs.add(aux);
    			}
    			String rsJson = Json.toJson(rs).toString();
	   			con.close();
	   			return ok("{\"datos\":"+rsJson.toString()+",\"empresa\":\""+baseDato.substring(4)+"\"}").as("application/json");
	   		} catch (SQLException e) {
				e.printStackTrace();
				return ok("{\"ERROR\":\"contactar a INQSOL\"}").as("application/json");
			}
			
   		}else {
   			return ok("{\"ERROR\":\"token no validado o tiempo cumplido\"}").as("application/json");
   		}
		
	}
	
	//menu HOHE: Reportes/Todo/Resumen por Período
		public Result hoheTodoResumenJson(Http.Request request, String desdeAAMMDD, String hastaAAMMDD) {
			/* curl -X POST -H 'Content-type:application/json' -H 'Authorization: Bearer B@10f1fa52B@6ef329B@b9526f8' 'http://localhost:9001/hoheTodoResumenJson/?desdeAAMMDD=2021-12-01&hastaAAMMDD=2021-12-31' */
			String baseDato = validaToken(request);
			if(baseDato.equals("error")) {
				String error = "{\"error\":\"invalid_token\": \"the syntax is not correct\"}";
				return ok(error).as("application/json");
			}
			if(!baseDato.equals("0")) {
				try {
		   			Connection con = db.getConnection();
		   			List<List<String>> lista = ReportHohe.datosResumen(con, baseDato, desdeAAMMDD, hastaAAMMDD);
		   			List<List<String>> l = ReportHohe.reporteHoheResumenJson(desdeAAMMDD, hastaAAMMDD, lista);
	       			JsonNode rsJson =  Json.toJson(l);
		   			con.close();
		   			return ok("{\"datos\":"+rsJson.toString()+",\"desde\":\""+desdeAAMMDD+"\",\"hasta\":\""+hastaAAMMDD+"\",\"empresa\":\""+baseDato.substring(4)+"\"}").as("application/json");
		   		} catch (SQLException e) {
					e.printStackTrace();
					return ok("{\"ERROR\":\"contactar a INQSOL\"}").as("application/json");
				}
				
	   		}else {
	   			return ok("{\"ERROR\":\"token no validado o tiempo cumplido\"}").as("application/json");
	   		}
			
		}
	

}
