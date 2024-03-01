package controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import controllers.HomeController.Sessiones;
import models.tables.BodegaEmpresa;
import models.tables.Grupo;
import models.tables.Moneda;
import models.tables.Ppto;
import models.tables.UsuarioPermiso;
import models.utilities.Registro;
import models.utilities.UserMnu;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.Database;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.mensajes;
import viewsMnuPpto.html.*;

public class MnuPpto extends Controller {
	public static Database db = HomeController.dbWrite;
	public static FormFactory formFactory = HomeController.formFactory;
	public static String msgError = HomeController.msgError;
	public static String msgErrorFormulario = HomeController.msgErrorFormulario;
	public static String msgSinPermiso = HomeController.msgSinPermiso;
	
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	
	
	
	//============================================================
    // MNU pptoAdministrar   Ppto/Administrar Pptos
    //============================================================
	
	public Result pptoAdministrar(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("pptoAdministrar")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			
    			Map<Long,List<Double>> mapVtaArrTot = Ppto.mapTotalPptoPorAllBodega(con, s.baseDato);
    			Map<Long,Long> dec = Moneda.numeroDecimal(con, s.baseDato);
    			Long numDecimales = (long)0;
    			if(dec!=null) {
    				numDecimales = dec.get((long)1);
    			}
    			switch(numDecimales.toString()) {
    			 case "0": myformatdouble = new DecimalFormat("#,##0"); break;
    			 case "2": myformatdouble = new DecimalFormat("#,##0.00"); break;
    			 case "4": myformatdouble = new DecimalFormat("#,##0.0000"); break;
    			 case "6": myformatdouble = new DecimalFormat("#,##0.000000"); break;
    			 default:  break;
    			}
    			List<List<String>> listBodegas = BodegaEmpresa.listaAllBodegasVigentesExternas(con, s.baseDato, mapeoPermiso, s.aplicaPorSucursal, s.id_sucursal);
    			List<List<String>> lista = new ArrayList<List<String>>();
    			for(int i=0; i<listBodegas.size(); i++) {
    				List<String> aux = new ArrayList<String>();
    				Long id_bodegaEmpresa = Long.parseLong(listBodegas.get(i).get(1));
    				aux.add(listBodegas.get(i).get(1));			//0 id_bodegaEmpresa
    				aux.add(listBodegas.get(i).get(5));			//1 nombre bodega
    				aux.add(listBodegas.get(i).get(7));			//2 nick cliente
    				aux.add(listBodegas.get(i).get(8));			//3 proyecto
    				List<Double> vtaArrTot = mapVtaArrTot.get(id_bodegaEmpresa);
    				if(mapVtaArrTot!=null && vtaArrTot!=null){
    					aux.add(myformatdouble.format(vtaArrTot.get(0)));			//4 total venta
    					aux.add(myformatdouble.format(vtaArrTot.get(1)));			//5 total arriendo
    					aux.add(myformatdouble.format(vtaArrTot.get(2)));			//6 total total
    				}else {
    					aux.add(myformatdouble.format((double)0));			//4 total venta
    					aux.add(myformatdouble.format((double)0));			//5 total arriendo
    					aux.add(myformatdouble.format((double)0));			//6 total total
    				}
    				aux.add(listBodegas.get(i).get(16));			//7 nameSucursal
    				lista.add(aux);
    			}
    			con.close();
    			return ok(pptoAdministrar.render(mapeoDiccionario,mapeoPermiso,userMnu,lista, numDecimales));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result pptoEditar(Http.Request request, Long id_bodegaEmpresa) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("pptoAdministrar")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			
    			List<Ppto> listPpto = Ppto.allPorBodegaEmpresa(con, s.baseDato, id_bodegaEmpresa);
        		BodegaEmpresa bodegaEmpresa = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
        		
    			Map<Long,Long> dec = Moneda.numeroDecimal(con, s.baseDato);
    			Long numDecimales = (long)0;
    			if(dec!=null) {
    				numDecimales = dec.get((long)1);
    			}
    			con.close();
    			return ok(pptoEditar.render(mapeoDiccionario,mapeoPermiso,userMnu,bodegaEmpresa,listPpto,numDecimales));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result pptoAgregar(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	    	  	Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa").trim());
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			BodegaEmpresa bodegaEmpresa = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
	    			List<Grupo> listGrupo = Grupo.all(con, s.baseDato);
	    			Map<Long,Long> dec = Moneda.numeroDecimal(con, s.baseDato);
	    			Long numDecimales = (long)0;
	    			if(dec!=null) {
	    				numDecimales = dec.get((long)1);
	    			}
	    			con.close();
	    			return ok(pptoAgregar.render(mapeoDiccionario,mapeoPermiso,userMnu,bodegaEmpresa,listGrupo,numDecimales));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
	
	public Result pptoGrabar(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Ppto ppto = new Ppto();
	       		ppto.id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa"));
		    	ppto.anioMes = form.get("anioMes");
		    	ppto.id_grupo = Long.parseLong(form.get("id_grupo"));
		    	ppto.esVenta = Long.parseLong(form.get("esVenta"));
		    	ppto.total = Double.parseDouble(form.get("total").replaceAll(",", ""));
		    	ppto.concepto = form.get("concepto");
	    		try {
	    			Connection con = db.getConnection();
	    			if(Ppto.grabarLinea(con, s.baseDato, ppto)){
	    				BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, ppto.id_bodegaEmpresa);
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "ppto", (long)0, "insert", "agrega nueva linea presupuesto en bodega: "+bodega.getNombre());
	    				con.close();
		    			return redirect("/pptoEditar/"+ppto.id_bodegaEmpresa.toString());
		    		}
	    			con.close();
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
	
	public Result pptoModificar(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_ppto = Long.parseLong(form.get("id_ppto"));
	    		try {
	    			Connection con = db.getConnection();
	    			
	    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
	    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
	    			Ppto ppto = Ppto.findPorIdPpto(con, s.baseDato, id_ppto);
		    		BodegaEmpresa bodegaEmpresa = BodegaEmpresa.findXIdBodega(con, s.baseDato, ppto.id_bodegaEmpresa);
		    		List<Grupo> listGrupo = Grupo.all(con, s.baseDato);
		    		Map<Long,Long> dec = Moneda.numeroDecimal(con, s.baseDato);
		    		Long numDecimales = (long)0;
	    			if(dec!=null) {
	    				numDecimales = dec.get((long)1);
	    			}
	    			con.close();
	    			return ok(pptoModificar.render(mapeoDiccionario,mapeoPermiso,userMnu,ppto,bodegaEmpresa,listGrupo,numDecimales));
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
	
	public Result pptoUpdate(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Ppto ppto = new Ppto();
		    	ppto.id = Long.parseLong(form.get("id"));
		    	ppto.id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa"));
		    	ppto.anioMes = form.get("anioMes");
		    	ppto.id_grupo = Long.parseLong(form.get("id_grupo"));
		    	ppto.esVenta = Long.parseLong(form.get("esVenta"));
		    	ppto.total = Double.parseDouble(form.get("total").replaceAll(",", ""));
		    	ppto.concepto = form.get("concepto");
	    		try {
	    			Connection con = db.getConnection();
	    			if(Ppto.updateLinea(con, s.baseDato, ppto)){
	    				BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, ppto.id_bodegaEmpresa);
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "ppto", ppto.id, "update", "modifica linea presupuesto en bodega "+bodega.getNombre());
	    				con.close();
		    			return redirect("/pptoEditar/"+ppto.id_bodegaEmpresa.toString());
		    		}
	    			con.close();
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
	
	public Result pptoEliminar(Http.Request request) {
    	Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		 
    		DynamicForm form = formFactory.form().bindFromRequest(request);
	   		if (form.hasErrors()) {
	   			return ok(mensajes.render("/",msgErrorFormulario));
	       	}else {
	       		Long id_ppto = Long.parseLong(form.get("id_ppto"));
		    	Long id_bodegaEmpresa = Long.parseLong(form.get("id_bodegaEmpresa"));
	    		try {
	    			Connection con = db.getConnection();
	    			if(Ppto.deleteLinea(con, s.baseDato, id_ppto)){
	    				BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
	    				Registro.modificaciones(con, s.baseDato, s.id_usuario, s.userName, "ppto", id_ppto, "delete", "elimina linea presupuesto en bodega "+bodega.getNombre());
	    				con.close();
		    			return redirect("/pptoEditar/"+id_bodegaEmpresa.toString());
		    		}
	    			con.close();
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		return ok(mensajes.render("/",msgError));
	       	}
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
    }
	
	//============================================================
    // MNU pptoReportes   Ppto/Reporte Pptos
    //============================================================
	
	public Result pptoListadoPptos(Http.Request request) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("pptoReportes")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			
    			String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, s.baseDato, Long.parseLong(s.id_usuario));
    			permisoPorBodega = permisoPorBodega.replaceAll("movimiento", "bodegaEmpresa");
    			permisoPorBodega = permisoPorBodega.replaceAll("id_bodegaEmpresa", "id");;
    			
    			Map<Long,List<Double>> mapVtaArrTot = Ppto.mapTotalPptoPorAllBodega(con, s.baseDato);
    			Map<Long,Long> dec = Moneda.numeroDecimal(con, s.baseDato);
    			Long numDecimales = (long)0;
    			if(dec!=null) {
    				numDecimales = dec.get((long)1);
    			}
    			switch(numDecimales.toString()) {
    			 case "0": myformatdouble = new DecimalFormat("#,##0"); break;
    			 case "2": myformatdouble = new DecimalFormat("#,##0.00"); break;
    			 case "4": myformatdouble = new DecimalFormat("#,##0.0000"); break;
    			 case "6": myformatdouble = new DecimalFormat("#,##0.000000"); break;
    			 default:  break;
    			}
    			
    			List<List<String>> listBodegas = BodegaEmpresa.listaAllBodegasVigentesExternasFiltradas(con, s.baseDato, permisoPorBodega, s.aplicaPorSucursal, s.id_sucursal);
    			List<List<String>> lista = new ArrayList<List<String>>();
    			for(int i=0; i<listBodegas.size(); i++) {
    				List<String> aux = new ArrayList<String>();
    				Long id_bodegaEmpresa = Long.parseLong(listBodegas.get(i).get(1));
    				aux.add(listBodegas.get(i).get(1));			//0 id_bodegaEmpresa
    				aux.add(listBodegas.get(i).get(5));			//1 nombre bodega
    				aux.add(listBodegas.get(i).get(7));			//2 nick cliente
    				aux.add(listBodegas.get(i).get(8));			//3 proyecto
    				List<Double> vtaArrTot = mapVtaArrTot.get(id_bodegaEmpresa);
    				if(mapVtaArrTot!=null && vtaArrTot!=null){
    					aux.add(myformatdouble.format(vtaArrTot.get(0)));			//4 total venta
    					aux.add(myformatdouble.format(vtaArrTot.get(1)));			//5 total arriendo
    					aux.add(myformatdouble.format(vtaArrTot.get(2)));			//6 total total
    				}else {
    					aux.add(myformatdouble.format((double)0));			//4 total venta
    					aux.add(myformatdouble.format((double)0));			//5 total arriendo
    					aux.add(myformatdouble.format((double)0));			//6 total total
    				}
    				aux.add(listBodegas.get(i).get(15));			//7 namesucursal
    				lista.add(aux);
    			}
    			List<List<String>> lista2 = new ArrayList<List<String>>();
    			for(List<String> x: lista) {
    				Double aux = Double.parseDouble(x.get(6).replaceAll(",", ""));
    				if(aux > (double)0) {
    					lista2.add(x);
    				}
    			}
    			con.close();
    			return ok(pptoListadoPptos.render(mapeoDiccionario,mapeoPermiso,userMnu,lista2, numDecimales));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
	public Result pptoVsRealxBodega(Http.Request request, Long id_bodegaEmpresa) {
		Sessiones s = new Sessiones(request);
    	if(s.userName!=null && s.id_usuario!=null && s.id_tipoUsuario!=null && s.baseDato!=null && s.id_sucursal!=null && s.porProyecto!=null) {
    		UserMnu userMnu = new UserMnu(s.userName, s.id_usuario, s.id_tipoUsuario, s.baseDato, s.id_sucursal, s.porProyecto, s.aplicaPorSucursal); 
    		try {
    			Connection con = db.getConnection();
    			
    			Map<String,String> mapeoPermiso = HomeController.mapPermisos(s.baseDato, s.id_tipoUsuario);
    			Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(s.baseDato);
    			if(mapeoPermiso.get("pptoReportes")==null) {
    				con.close();
    				return ok(mensajes.render("/",msgSinPermiso));
    			}
    			
    			List<List<String>> listPpto = Ppto.pptoVsRealxBodega(con, s.baseDato, id_bodegaEmpresa, mapeoDiccionario.get("pais"));
        		List<String> categoria = new ArrayList<String>();
        		List<String> parcialPpto = new ArrayList<String>();
        		List<String> acumPpto = new ArrayList<String>();
        		List<String> parcialReal = new ArrayList<String>();
        		List<String> acumReal = new ArrayList<String>();
        		
        		for(int i=0; i<listPpto.size(); i++) {
        			categoria.add("'"+listPpto.get(i).get(0)+"'");
        			parcialPpto.add(listPpto.get(i).get(1).replaceAll(",", ""));
        			acumPpto.add(listPpto.get(i).get(2).replaceAll(",", ""));
        			parcialReal.add(listPpto.get(i).get(3).replaceAll(",", ""));
        			acumReal.add(listPpto.get(i).get(4).replaceAll(",", ""));
        			
        		}
        		
        		List<List<String>> listGrafico = new ArrayList<List<String>>();
        		listGrafico.add(categoria);
        		listGrafico.add(parcialPpto);
        		listGrafico.add(acumPpto);
        		listGrafico.add(parcialReal);
        		listGrafico.add(acumReal);
        		
        		BodegaEmpresa bodegaEmpresa = BodegaEmpresa.findXIdBodega(con, s.baseDato, id_bodegaEmpresa);
        		Map<Long,Long> dec = Moneda.numeroDecimal(con, s.baseDato);
        		Long numDecimales = (long)0;
        		if(dec!=null) {
        			numDecimales = dec.get((long)1);
        		}
        		String moneda = mapeoDiccionario.get("MONEDA PRINCIPAL");
    			con.close();
    			return ok(pptoVsRealxBodega.render(mapeoDiccionario,mapeoPermiso,userMnu,bodegaEmpresa,listPpto,numDecimales,listGrafico,moneda));
        	} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return ok(mensajes.render("/",msgError));
    	}else {
    		return ok(mensajes.render("/",msgError));
    	}
	}
	
}
