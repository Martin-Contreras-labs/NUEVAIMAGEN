package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.cpanel.CPanel;
import models.utilities.UserMnu;
import models.utilities.VerificarCaptcha;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.Database;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import views.html.mensajes;
import views.html.vistaPrincipal;
import viewsCPanel.html.*;


public class AppCPanel extends Controller {
	public static Database db = HomeController.dbWrite;
	public static FormFactory formFactory = HomeController.formFactory;
	public static String msgError = HomeController.msgError;
	public static String msgErrorFormulario = HomeController.msgErrorFormulario;
	public static String msgSinPermiso = HomeController.msgSinPermiso;
	
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	
	
	//=============================================
    // INICIO USUARIOS CPANEL
    //=============================================

    public Result vistaCPanel() {
    	return ok(indexCPanel.render("0")).withNewSession();
    }
    
    public Result inicioCPanelHome(Http.Request request) {
    	String idUserCpanel = request.session().get("idUserCpanel").get();
    	if(idUserCpanel==null) {
    		return ok(mensajes.render("/cpanel",msgErrorFormulario));
    	}else {
    		try {
				Connection con = db.getConnection();
	    		CPanel usuario = CPanel.findIdUserCpanel(con, idUserCpanel);
	    		Map<String,String> map = new HashMap<String,String>();
				map.put("pais", "sinPais");
				List<CPanel> listEmpresas = CPanel.allEmpresasVigentes(con, usuario);
				List<List<String>> datos = CPanel.vistaPrincipal(con, listEmpresas);
				List<String> graficoPRvsPL = CPanel.graficoPRvsPLporPais(con, listEmpresas, map);
				List<String> graficoOcupacion = CPanel.graficoOcupacionPorPais(con, listEmpresas);
				List<List<String>> ventas = CPanel.totalVentas(con, listEmpresas);
				con.close();
				return ok(inicioCPanel.render(datos, graficoOcupacion, graficoPRvsPL,ventas))
						.addingToSession(request, "idUserCpanel", usuario.id_user.toString())
						.addingToSession(request, "userNameAdmin", "1")
						.addingToSession(request, "usuario", usuario.userName)
						.addingToSession(request, "permiso", "1")
						.addingToSession(request, "porProyecto", "0")
						.addingToSession(request, "id_sucursal", "1")
						.addingToSession(request, "aplicaPorSucursal", "0");
    		} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
   		return ok(mensajes.render("/cpanel",msgError));
    }
    
    public Result inicioCPanel(Http.Request request) {
    	DynamicForm form = formFactory.form().bindFromRequest(request);
    	
   		if (form.hasErrors()) {
   			return ok(mensajes.render("/cpanel",msgErrorFormulario));
       	}else {
       		String userName = form.get("userName");
       		String empresa = form.get("empresa");
       		String userKey = form.get("userKey");
       		
       		String gRecaptchaResponse = form.get("gRecaptchaResponse");
       		
       		if(!empresa.equals("ALTRAD RMDK CHILE")) {
       			boolean verificado = false;
                try {
        			verificado = VerificarCaptcha.verificar(gRecaptchaResponse);
        		} catch (IOException e1) {
        			e1.printStackTrace();
        		}
                if(!verificado) {
                	String mensaje = "CAPTCHA NO VALIDO";
        			return ok(mensajes.render("/",mensaje));
                }
       		}
       		
            
       		try {
				Connection con = db.getConnection();
				CPanel usuario = CPanel.findUsuario(con, userName, empresa, userKey);
				if(usuario.id_user==null) {
					con.close();
					String msg = "Los datos ingresados de usuario, empresa o clave, no corresponden, vuelva a ingresarlos. En caso de continuar " +
							"este problema, por favor contactar con soporte pbarros@inqsol.com";
					con.close();
					return ok(mensajes.render("/cpanel",msg));
				}else {
					Map<String,String> map = new HashMap<String,String>();
					map.put("pais", "sinPais");
					List<CPanel> listEmpresas = CPanel.allEmpresasVigentes(con, usuario);
					List<List<String>> datos = CPanel.vistaPrincipal(con, listEmpresas);
					List<String> graficoPRvsPL = CPanel.graficoPRvsPLporPais(con, listEmpresas, map);
					List<String> graficoOcupacion = CPanel.graficoOcupacionPorPais(con, listEmpresas);
					List<List<String>> ventas = CPanel.totalVentas(con, listEmpresas);
					con.close();
					return ok(inicioCPanel.render(datos, graficoOcupacion, graficoPRvsPL,ventas))
							.addingToSession(request, "idUserCpanel", usuario.id_user.toString())
							.addingToSession(request, "userNameAdmin", "1")
							.addingToSession(request, "usuario", usuario.userName)
							.addingToSession(request, "permiso", "1")
							.addingToSession(request, "porProyecto", "0")
							.addingToSession(request, "id_sucursal", "1")
							.addingToSession(request, "aplicaPorSucursal", "0");
				}
       		} catch (SQLException e) {
				e.printStackTrace();
			}
       	}
   		return ok(mensajes.render("/cpanel",msgError));
    }
    
    public Result cPanelModalItemsControlados(Http.Request request) {
    	DynamicForm form = formFactory.form().bindFromRequest(request);
   		if (form.hasErrors()) { 
   			return ok("");
       	}else {
       		Long id_cPanel = Long.parseLong(form.get("id_cPanel"));
       		try {
				Connection con = db.getConnection();
				CPanel empresa = CPanel.findEmpresa(con, id_cPanel);
				String modal = CPanel.modalItemsControlados(con, empresa);
				con.close();
				return ok(modal);
       		} catch (SQLException e) {
				e.printStackTrace();
			}
       	}
   		return ok("");
    }
    
    public Result cPanelModalVigentes(Http.Request request) {
    	DynamicForm form = formFactory.form().bindFromRequest(request);
   		if (form.hasErrors()) { 
   			return ok("");
       	}else {
       		Long id_cPanel = Long.parseLong(form.get("id_cPanel"));
       		try {
				Connection con = db.getConnection();
				CPanel empresa = CPanel.findEmpresa(con, id_cPanel);
				String modal = CPanel.modalVigentes(con, empresa);
				con.close();
				return ok(modal);
       		} catch (SQLException e) {
				e.printStackTrace();
			}
       	}
   		return ok("");
    }
    
    public Result cPanelModalNoVigentes(Http.Request request) {
    	DynamicForm form = formFactory.form().bindFromRequest(request);
   		if (form.hasErrors()) { 
   			return ok("");
       	}else {
       		Long id_cPanel = Long.parseLong(form.get("id_cPanel"));
       		try {
				Connection con = db.getConnection();
				CPanel empresa = CPanel.findEmpresa(con, id_cPanel);
				String modal = CPanel.modalNoVigentes(con, empresa);
				con.close();
				return ok(modal);
       		} catch (SQLException e) {
				e.printStackTrace();
			}
       	}
   		return ok("");
    }
    
    public Result inicioCPanel2(Http.Request request) {
    	DynamicForm form = formFactory.form().bindFromRequest(request);
   		if (form.hasErrors()) {
   			return ok(mensajes.render("/cpanel",msgErrorFormulario));
       	}else {
       		Long id_cPanel = Long.parseLong(form.get("id_cPanel"));
       		try {
				Connection con = db.getConnection();
				CPanel empresa = CPanel.findEmpresa(con, id_cPanel);
				if(request.session().get("userNameAdmin").get()==null || empresa.id_cPanel==null) {
					con.close();
					return ok(mensajes.render("/cpanel",msgError));
				}
				Map<String,String> mapeoDiccionario = HomeController.mapDiccionario(empresa.baseDato);
				Map<String,String> mapeoPermiso = HomeController.mapPermisos(empresa.baseDato, "1");
				String userName = request.session().get("usuario").get();
				UserMnu userMnu = new UserMnu(userName, "0", "1", empresa.baseDato,"1","0","0");
				Long esMoroso = (long)0;
				try {
					PreparedStatement smt2 = con
							.prepareStatement("select moroso from empresa where baseDato=?;");
					smt2.setString(1,empresa.baseDato);
					ResultSet rs2 = smt2.executeQuery();
					if(rs2.next()) {
						esMoroso = rs2.getLong(1);
					}
					rs2.close();
					smt2.close();
				} catch (SQLException e) {}
				
				con.close();
				return ok(vistaPrincipal.render(
						mapeoDiccionario,
						mapeoPermiso,
						userMnu,
						esMoroso))
						.addingToSession(request, "userName", userName)
						.addingToSession(request, "id_usuario", "0")
						.addingToSession(request, "baseDato", empresa.baseDato)
						.addingToSession(request, "id_tipoUsuario", "1")
						.addingToSession(request, "porProyecto", "0")
						.addingToSession(request, "id_sucursal", "1")
						.addingToSession(request, "aplicaPorSucursal", "0");
       		} catch (SQLException e) {
				e.printStackTrace();
			}
       	}
   		return ok(mensajes.render("/cpanel",msgError));
    }
    
	    
	
	
}
