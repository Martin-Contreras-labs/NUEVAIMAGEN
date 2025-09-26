package models.cpanel;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import controllers.HomeController;
import models.reports.ReportFacturaConsolidado;
import models.reports.ReportGerenciales;
import models.tables.ActaBaja;
import models.tables.Compra;
import models.tables.Moneda;
import models.utilities.Fechas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CPanel {
	
	//usuario:
	public Long id_user;
	public String userName;
	public String userKey;
	public String nombre;
	public String cargo;
	public Long id_tipoUsuario;
	public String fono;
	public String email;
	public String nEmpresa;
	
	//cPanel:
	public Long id_cPanel;
	public String empresa;
	public String pais;
	public String baseDato;
	public String baseRaiz;
	public Long vigente;
	public String logo;

	public CPanel(Long id_user, String userName, String userKey, String nombre, String cargo, Long id_tipoUsuario,
			String fono, String email, String nEmpresa, Long id_cPanel, String empresa, String pais, String baseDato,
			String baseRaiz, Long vigente, String logo) {
		super();
		this.id_user = id_user;
		this.userName = userName;
		this.userKey = userKey;
		this.nombre = nombre;
		this.cargo = cargo;
		this.id_tipoUsuario = id_tipoUsuario;
		this.fono = fono;
		this.email = email;
		this.nEmpresa = nEmpresa;
		this.id_cPanel = id_cPanel;
		this.empresa = empresa;
		this.pais = pais;
		this.baseDato = baseDato;
		this.baseRaiz = baseRaiz;
		this.vigente = vigente;
		this.logo = logo;
	}


	public CPanel() {super();}

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	public static CPanel findUsuario(Connection con, String userName, String empresa, String userKey) {
		CPanel aux = new CPanel();
		String query = String.format("select  id, userName, userKey, nombre, cargo, id_tipoUsuario, fono, email, empresa " +
				" from madaCPanel.usuario where UPPER(userName) = ? and UPPER(empresa) = ? and userKey = BINARY ?;");
		try {
			if(userName==null||empresa==null) {
				aux.id_user = null; return (aux);
			}
			try (PreparedStatement smt = con.prepareStatement(query)) {
				smt.setString(1, userName.trim());
				smt.setString(2, empresa.trim());
				smt.setString(3, userKey.trim());
				try (ResultSet rs = smt.executeQuery()) {
					if (rs.next()) {
						aux = new CPanel(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
								rs.getLong(6), rs.getString(7), rs.getString(8), rs.getString(9),
								(long) 0, "0", "0", "0", "0", (long) 0, "0");
					} else {
						aux.id_user = null;
					}
				}
			}
		} catch (SQLException e) {
			String className = ActaBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, "madaCPanel", e);
		}
		return (aux);
	}
	
	public static List<CPanel> allEmpresasVigentes(Connection con, CPanel user) {
		List<CPanel> lista = new ArrayList<CPanel>();
		String query = String.format("select id, empresa, pais, baseDato, baseRaiz, vigente, logo from madaCPanel.cPanel where UPPER(empresa) = ? and vigente = 1 order by pais;");
		try (PreparedStatement smt = con.prepareStatement(query)) {
			smt.setString(1, user.nEmpresa.toUpperCase().trim());
			try (ResultSet rs = smt.executeQuery()) {
				while (rs.next()) {
					CPanel aux = new CPanel();
					aux = new CPanel(user.id_user, user.userName, user.userKey, user.nombre, user.cargo,
							user.id_tipoUsuario, user.fono, user.email, user.empresa,
							rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getLong(6), rs.getString(7));
					lista.add(aux);
				}
			}
		} catch (SQLException e) {
			String className = ActaBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, "madaCPanel", e);
		}
		return (lista);
	}
	
	public static List<List<String>> vistaPrincipal(Connection con, List<CPanel> emprVig){
		List<List<String>> vista = new ArrayList<List<String>>();
		try {
			ResultSet rs = null;
			for(int i=0;i<emprVig.size();i++) {
				List<String> aux = new ArrayList<String>();
				String bd = emprVig.get(i).baseDato;
				String bdraiz = emprVig.get(i).baseRaiz;
				String condUserInqsol = "";

				PreparedStatement smt0 = con.prepareStatement("select usuariosInqsol from madaCPanel.cPanel where baseDato = '"+bd+"'");
				rs = smt0.executeQuery();
				if(rs.next()) condUserInqsol = " where usuario not in ("+rs.getString(1)+")"; else condUserInqsol="";
				rs.close();smt0.close();
				aux.add((i+1)+"");
				aux.add(emprVig.get(i).pais);

				PreparedStatement smt1 = con.prepareStatement("select date_format(dateCreate,'%d/%m/%Y') as fechCreacion from "+bdraiz+".empresa where baseDato = '"+bd+"';");
				rs = smt1.executeQuery();
				if(rs.next()) {if(rs.getString(1)==null) aux.add(""); else aux.add(rs.getString(1));} else {aux.add("");}
				rs.close();smt1.close();

				PreparedStatement smt2 = con.prepareStatement("select date_format(max(dateCreate),'%d/%m/%Y') as fechUltimoMovimiento from "+bd+".movimiento;");
				rs = smt2.executeQuery();
				if(rs.next()) {if(rs.getString(1)==null) aux.add(""); else aux.add(rs.getString(1));} else {aux.add("");}
				rs.close();smt2.close();
				
				PreparedStatement smt3 = con.prepareStatement("select date_format(max(fecha),'%d/%m/%Y') as fechUltimaGuia from "+bd+".guia;");
				rs = smt3.executeQuery();
				if(rs.next()) {if(rs.getString(1)==null) aux.add(""); else aux.add(rs.getString(1));} else {aux.add("");}
				rs.close();smt3.close();
				
				PreparedStatement smt4 = con.prepareStatement("select date_format(max(fecha),'%d/%m/%Y') as fechUltimaCotizacion from "+bd+".cotizacion;");
				rs = smt4.executeQuery();
				if(rs.next()) {if(rs.getString(1)==null) aux.add(""); else aux.add(rs.getString(1));} else {aux.add("");}
				rs.close();smt4.close();
				
				PreparedStatement smt5 = con.prepareStatement("select date_format(max(dateCreate),'%d/%m/%Y') as fechUltimoIngUsuario from "+bd+".registroAccesos "+condUserInqsol+";");
				rs = smt5.executeQuery();
				if(rs.next()) {if(rs.getString(1)==null) aux.add(""); else aux.add(rs.getString(1));} else {aux.add("");}
				rs.close();smt5.close();
				
				PreparedStatement smt6 = con.prepareStatement("select count(id_equipo) as cantItemControlados from "+bd+".movimiento group by id_equipo;");
				rs = smt6.executeQuery();
				Long contador = (long)0;
				while(rs.next()) contador++;
				aux.add(contador.toString());
				rs.close();smt6.close();
				
				PreparedStatement smt7 = con.prepareStatement("select count(nombre) as cantProyVigente from "+bd+".bodegaEmpresa where vigente=1 and esInterna=2;");
				rs = smt7.executeQuery();
				if(rs.next()) {if(rs.getString(1)==null) aux.add(""); else aux.add(rs.getString(1));} else {aux.add("");}
				rs.close();smt7.close();
				
				PreparedStatement smt8 = con.prepareStatement("select count(nombre) as cantProyNoVigente from "+bd+".bodegaEmpresa where vigente=0 and esInterna=2;");
				rs = smt8.executeQuery();
				if(rs.next()) {if(rs.getString(1)==null) aux.add(""); else aux.add(rs.getString(1));} else {aux.add("");}
				rs.close();smt8.close();
				
				aux.add(emprVig.get(i).id_cPanel.toString());
				
				vista.add(aux);
			}
		} catch (SQLException e) {
			String className = ActaBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, "", e);
		}
		return(vista);
	}
	
	public static List<String> graficoPRvsPLporPais (Connection con, List<CPanel> emprVig, Map<String,String> mapDiccionario) {
		List<String> resultado = new ArrayList<String>();
		try{
			List<String> aux = new ArrayList<String>();
			String categorias = "";
			for(int i=0;i<emprVig.size();i++) {
				List<String> series = ReportGerenciales.graficoCategoriasAnio(con, emprVig.get(i).baseDato, (long)0, mapDiccionario, "0", "1");
				String[] arrFecha = series.get(6).replace("[", "").replace("]", "").split(",");
				String[] valPR = series.get(7).replace("[", "").replace("]", "").split(",");
				String[] valPL = series.get(8).replace("[", "").replace("]", "").split(",");
				String listPRvsPL = "[";
				for(int j=0; j<arrFecha.length; j++) {
					Double aux1 = Double.parseDouble(valPR[j]);
					Double aux2 = Double.parseDouble(valPL[j]);
					Double aux3 = (double)0;
					if(aux2>0) aux3 = (double) Math.round(aux1/aux2*100)/100;
					listPRvsPL += aux3.toString() + ",";
				}
				if(listPRvsPL.length()>1) {
					listPRvsPL = listPRvsPL.substring(0,listPRvsPL.length()-1) + "]";
					String aux3 = "{ name: '"+emprVig.get(i).pais+"', type: 'spline', yAxis: 0, data: "+listPRvsPL+", tooltip: {valueSuffix: ' '}},";
					aux.add(aux3);
					categorias = series.get(6);
				}

			}
			resultado.add(categorias);
			for(int i=0; i< aux.size(); i++) {
				resultado.add(aux.get(i));
			}
		} catch (Exception e) {
			String className = ActaBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, "", e);
		}
		return(resultado);
	}
	
	public static List<String> graficoOcupacionPorPais(Connection con, List<CPanel> emprVig) {
		List<String> resultado = new ArrayList<String>();
		try{
			List<java.sql.Date> listFechas = new ArrayList<java.sql.Date>();
			Calendar fechaCal = Calendar.getInstance();
			fechaCal.set(fechaCal.get(Calendar.YEAR),fechaCal.get(Calendar.MONTH),fechaCal.getActualMinimum(Calendar.DAY_OF_MONTH));
			fechaCal.add(Calendar.DAY_OF_YEAR, -1);
			fechaCal.add(Calendar.MONTH, -11);
			String categoriaFechas ="[";
			SimpleDateFormat myformatfecha = new SimpleDateFormat("MMM-yy");
			for (int i=1;i<=12;i++){
				String aux=",";	if(i==12) aux="";
				java.util.Date date = fechaCal.getTime();
				categoriaFechas=categoriaFechas+"'"+myformatfecha.format(date)+"'"+aux;
				listFechas.add(new java.sql.Date(fechaCal.getTimeInMillis()));
				fechaCal.add(Calendar.MONTH, 1);
				fechaCal.set(fechaCal.get(Calendar.YEAR),fechaCal.get(Calendar.MONTH),fechaCal.getActualMaximum(Calendar.DAY_OF_MONTH));
			}
			categoriaFechas=categoriaFechas+"]";
			resultado.add(categoriaFechas);
			for(int i=0;i<emprVig.size();i++){
				String aux4 = ",";if(i==emprVig.size()-1) aux4="";
				String aux3 = "{"+
					" name: ";
				String aux1 = "[";
				for(int j=0;j<listFechas.size();j++){
					String x=",";if(j==listFechas.size()-1) x="";
					Double aux = CPanel.ocupacionPaisMes(con, emprVig.get(i).baseDato, listFechas.get(j));
					aux1 = aux1 + aux + x;
				}
				aux1 = aux1+ "]";

				aux3 = aux3+"'"+emprVig.get(i).pais +"',"+
					" type: 'spline',"+
					" yAxis: 0,"+
					" data: "+ aux1 +","+
					" tooltip: {"+
						"valueSuffix: ' '"+
					"}"+
				"}" + aux4;
				resultado.add(aux3);
			}
		} catch (Exception e) {
			String className = ActaBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, "", e);
		}
		return (resultado);
	}
	
	public static Double ocupacionPaisMes(Connection con, String db,java.sql.Date fecha) {
		Double total = (double)0;
		try {
			Double parcial = (double)0;
			Map<Long,List<Double>> ultimaCompra = Compra.ultimoPrecio(con, db);
			String query = String.format(" select movimiento.id_equipo, sum(if(id_tipoMovimiento=1,1,-1)*movimiento.cantidad) " +
					" from `"+db+"`.movimiento " +
					" left join `%s`.guia on guia.id = movimiento.id_guia" +
					" left join `%s`.compra on compra.id = movimiento.id_compra " +
					" left join `%s`.factura on factura.id = compra.id_factura " +
					" left join `%s`.baja on baja.id = movimiento.id_baja " +
					" left join `%s`.actaBaja on actaBaja.id = baja.id_actaBaja " +
					" left join `%s`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa" +
					" left join `%s`.tipoBodega on tipoBodega.id = bodegaEmpresa.esInterna" +
					" where bodegaEmpresa.vigente = 1 and ifnull(ifnull(guia.fecha,factura.fecha),actaBaja.fecha) <= ?;",db,db,db,db,db,db,db);
			try (PreparedStatement smt6 = con.prepareStatement(query)) {
				smt6.setDate(1, fecha);
				try (ResultSet rsTotal = smt6.executeQuery()) {
					while (rsTotal.next()) {
						List<Double> aux = ultimaCompra.get(rsTotal.getLong(1));
						if (aux != null) {
							total += rsTotal.getDouble(2) * aux.get(0);
						}
					}
				}
			} catch (SQLException e) {
				String className = ActaBaja.class.getSimpleName();
				String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
			}
			query = String.format(" select movimiento.id_equipo, sum(if(id_tipoMovimiento=1,1,-1)*movimiento.cantidad) " +
							" from `%s`.movimiento " +
							" left join `%s`.guia on guia.id = movimiento.id_guia" +
							" left join `%s`.compra on compra.id = movimiento.id_compra " +
							" left join `%s`.factura on factura.id = compra.id_factura " +
							" left join `%s`.baja on baja.id = movimiento.id_baja " +
							" left join `%s`.actaBaja on actaBaja.id = baja.id_actaBaja " +
							" left join `%s`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa" +
							" left join `%s`.tipoBodega on tipoBodega.id = bodegaEmpresa.esInterna" +
							" where bodegaEmpresa.esInterna<>1 and bodegaEmpresa.vigente = 1 and ifnull(ifnull(guia.fecha,factura.fecha),actaBaja.fecha) <= ?;",db,db,db,db,db,db,db,db);
			try (PreparedStatement smt7 = con.prepareStatement(query)) {
				smt7.setDate(1, fecha);
				try (ResultSet rsParcial = smt7.executeQuery()) {
					while (rsParcial.next()) {
						List<Double> aux = ultimaCompra.get(rsParcial.getLong(1));
						if (aux != null) {
							parcial += rsParcial.getDouble(2) * aux.get(0);
						}
					}
				}
			} catch (SQLException e) {
				String className = ActaBaja.class.getSimpleName();
				String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
			}
			if(total==0){
				total = (double) 0;
			}else{
				total =  (double) Math.round(parcial/total*10000);
				total = total/100;
			}
		} catch (Exception e) {
			String className = ActaBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, "", e);
		}
		return(total);
	}
	
	public static List<List<String>> totalVentas(Connection con, List<CPanel> emprVig){
		List<List<String>> tabla = new ArrayList<List<String>>();
		try {
			Long meses = (long) 12;
			String permisoPorBodega = "";
			List<String> titulos = new ArrayList<String>();
			List<List<String>> datos = new ArrayList<List<String>>();
			for(int i=0;i<emprVig.size();i++) {
				List<String> aux = new ArrayList<String>();
				String bd = emprVig.get(i).baseDato;
				Fechas fecha = Fechas.hoy();
				List<List<String>> auxDato = ReportFacturaConsolidado.reportFacturaConsolidadoRtp(con, bd, fecha, meses, permisoPorBodega, "sinPais", "0", "1");
				
				// TITULOS
				if(i==0) {
					titulos.add("Nro");
					titulos.add("PAIS");
					titulos.add("MONEDA");
					for(int j=1; j<auxDato.get(0).size(); j++) {
						titulos.add(auxDato.get(0).get(j));
					}
				}
				Moneda moneda = Moneda.find(con, bd, (long)1);
				String nickMoneda = "";
				if(moneda!=null) {
					nickMoneda = moneda.nickName;
				}
				//DATOS
				aux.add((i+1)+"");
				aux.add(emprVig.get(i).pais);
				aux.add(nickMoneda);
				for(int j=1; j<auxDato.get(auxDato.size()-1).size(); j++) {
					aux.add(auxDato.get(auxDato.size()-1).get(j));
				}
				datos.add(aux);
			}
			tabla.add(titulos);
			for(int i=0;i<datos.size();i++) {
				tabla.add(datos.get(i));
			}
		} catch (Exception e) {
			String className = ActaBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, "", e);
		}
		return(tabla);
	}
	
	public static CPanel findEmpresa(Connection con, Long id_cPanel) {
		CPanel aux = new CPanel();
		String query = String.format("select id, empresa, pais, baseDato, baseRaiz, vigente, logo from madaCPanel.cPanel where id=?;");
		try (PreparedStatement smt = con.prepareStatement(query)) {
			smt.setLong(1, id_cPanel);
			try (ResultSet rs = smt.executeQuery()) {
				if (rs.next()) {
					aux = new CPanel((long) 0, "0", "0", "0", "0", (long) 0, "0", "0", "0",
							rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getLong(6), rs.getString(7));
				} else {
					aux.id_cPanel = null;
				}
			}
		} catch (SQLException e) {
			String className = ActaBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, "madaCPanel", e);
		}
		return (aux);
	}
	
	public static String modalItemsControlados(Connection con, CPanel empresa) {
		List<List<String>> lista = new ArrayList<List<String>>();
		String vista = "";
		try {
			String bd = empresa.baseDato;
			String query = String.format("select codigo, nombre from `%s`.movimiento left join `%s`.equipo on equipo.id = id_equipo group by id_equipo;",bd,bd);
			try (PreparedStatement smt = con.prepareStatement(query)) {
				try (ResultSet rs = smt.executeQuery()) {
					int i = 1;
					while (rs.next()) {
						List<String> aux = new ArrayList<String>();
						aux.add((i++) + "");
						aux.add(rs.getString(1));
						aux.add(rs.getString(2));
						lista.add(aux);
					}
				}
			} catch (SQLException e) {
				String className = ActaBaja.class.getSimpleName();
				String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, bd, e);
			}
			vista = ""+
					"<div class='modal-header'>" +
						"<h4 class='modal-title' id='myModalLabel'>" + "LISTA DE EQUIPOS CONTROLADOS ("+empresa.pais+")" + "</h4>" +
						"<div align='center'><button type='button' class='btn btn-mini' data-dismiss='modal' tabindex='-1'>cerrar</button></div>" +
					"</div>" +
					"<div class='modal-body'>" +
						"<table id='tableItemsControlados' class='table table-sm table-hover table-bordered table-condensed table-fluid'>" +
							"<thead style='background-color: #eeeeee'>" +
								"<TR><th>ITEM</th><th>CODIGO</th><th>EQUIPO</th></TR>" +
							"</thead>" +
							"<tbody>";
					for(int i=0;i<lista.size();i++){
						vista += ""+
							"<TR>" +
								"<td style='text-align:center;'>"+lista.get(i).get(0)+"</td>"+
								"<td style='text-align:center;'>"+lista.get(i).get(1)+"</td>"+
								"<td style='text-align:left;'>"+lista.get(i).get(2)+"</td>"+
							"</TR>";
					}
					vista += ""+
						"</tbody></table>" +
						"<div align='center'><button type='button' class='btn btn-mini' data-dismiss='modal' tabindex='-1'>cerrar</button></div>" +
						"</div>";
		} catch (Exception e) {
			String className = ActaBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, "", e);
		}
		return (vista);
	}
	
	public static String modalVigentes(Connection con, CPanel empresa) {
		List<List<String>> lista = new ArrayList<List<String>>();
		String vista = "";
		try {
			String bd = empresa.baseDato;
			String query = String.format("select date_format(dateCreate,'%d/%m/%Y'), nombre from `%s`.bodegaEmpresa where vigente=1 and esInterna=2 order by nombre;",bd);
			try (PreparedStatement smt = con.prepareStatement(query)) {
				try (ResultSet rs = smt.executeQuery()) {
					int i = 1;
					while (rs.next()) {
						List<String> aux = new ArrayList<String>();
						aux.add((i++) + "");
						aux.add(rs.getString(1));
						aux.add(rs.getString(2));
						lista.add(aux);
					}
				}
			} catch (SQLException e) {
				String className = ActaBaja.class.getSimpleName();
				String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, bd, e);
			}
			vista = ""+
					"<div class='modal-header'>" +
						"<h4 class='modal-title' id='myModalLabel'>" + "LISTA DE BODEGAS/PROYECTOS VIGENTES ("+empresa.pais+")" + "</h4>" +
						"<div align='center'><button type='button' class='btn btn-mini' data-dismiss='modal' tabindex='-1'>cerrar</button></div>" +
					"</div>" +
					"<div class='modal-body'>" +
						"<table id='tableVigentes' class='table table-sm table-hover table-bordered table-condensed table-fluid'>" +
							"<thead style='background-color: #eeeeee'>" +
								"<TR><th>ITEM</th><th>FECHA CREADO</th><th>NOMBRE</th></TR>" +
							"</thead>" +
							"<tbody>";
					for(int i=0;i<lista.size();i++){
						vista += ""+
							"<TR>" +
								"<td style='text-align:center;'>"+lista.get(i).get(0)+"</td>"+
								"<td style='text-align:center;'>"+lista.get(i).get(1)+"</td>"+
								"<td style='text-align:left;'>"+lista.get(i).get(2)+"</td>"+
							"</TR>";
					}
					vista += ""+
						"</tbody></table>" +
						"<div align='center'><button type='button' class='btn btn-mini' data-dismiss='modal' tabindex='-1'>cerrar</button></div>" +
						"</div>";
		} catch (Exception e) {
			String className = ActaBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, "", e);
		}
		return (vista);
	}
	
	public static String modalNoVigentes(Connection con, CPanel empresa) {
		List<List<String>> lista = new ArrayList<List<String>>();
		String vista = "";
		try {
			String bd = empresa.baseDato;
			String query = String.format("select date_format(dateCreate,'%d/%m/%Y'), nombre from `%s`.bodegaEmpresa where vigente=0 and esInterna=2 order by nombre;",bd);
			try (PreparedStatement smt = con.prepareStatement(query)) {
				try (ResultSet rs = smt.executeQuery()) {
					int i = 1;
					while (rs.next()) {
						List<String> aux = new ArrayList<String>();
						aux.add((i++) + "");
						aux.add(rs.getString(1));
						aux.add(rs.getString(2));
						lista.add(aux);
					}
				}
			} catch (SQLException e) {
				String className = ActaBaja.class.getSimpleName();
				String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
				logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, bd, e);
			}
			vista = ""+
					"<div class='modal-header'>" +
						"<h4 class='modal-title' id='myModalLabel'>" + "LISTA DE PROYECTOS/CLIENTES NO VIGENTES ("+empresa.pais+")" + "</h4>" +
						"<div align='center'><button type='button' class='btn btn-mini' data-dismiss='modal' tabindex='-1'>cerrar</button></div>" +
					"</div>" +
					"<div class='modal-body'>" +
						"<table id='tableNoVigentes' class='table table-sm table-hover table-bordered table-condensed table-fluid'>" +
							"<thead style='background-color: #eeeeee'>" +
								"<TR><th>ITEM</th><th>FECHA CREADO</th><th>NOMBRE</th></TR>" +
							"</thead>" +
							"<tbody>";
					for(int i=0;i<lista.size();i++){
						vista += ""+
							"<TR>" +
								"<td style='text-align:center;'>"+lista.get(i).get(0)+"</td>"+
								"<td style='text-align:center;'>"+lista.get(i).get(1)+"</td>"+
								"<td style='text-align:left;'>"+lista.get(i).get(2)+"</td>"+
							"</TR>";
					}
					vista += ""+
						"</tbody></table>" +
						"<div align='center'><button type='button' class='btn btn-mini' data-dismiss='modal' tabindex='-1'>cerrar</button></div>" +
						"</div>";
		} catch (Exception e) {
			String className = ActaBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, "", e);
		}
		return (vista);
	}
	
	public static Long findIdEmpresaMada(Connection con, CPanel empresa) {
		Long aux = (long)0;
		String query = String.format("select id from "+empresa.baseRaiz+".empresa where baseDato = '"+empresa.baseDato+"';");
		try (PreparedStatement smt = con.prepareStatement(query)) {
			try (ResultSet rs = smt.executeQuery()) {
				if (rs.next()) {
					aux = rs.getLong(1);
				}
			}
		} catch (SQLException e) {
			String className = ActaBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, empresa.baseDato, e);
		}
		return (aux);
	}

	public static CPanel findIdUserCpanel(Connection con, String idUserCpanel) {
		CPanel aux = new CPanel();
		String query = String.format("select  id, userName, userKey, nombre, cargo, id_tipoUsuario, fono, email, empresa " +
				" from madaCPanel.usuario where id = ?;");
		try (PreparedStatement smt = con.prepareStatement(query)) {
			smt.setString(1, idUserCpanel);
			try (ResultSet rs = smt.executeQuery()) {
				if (rs.next()) {
					aux = new CPanel(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
							rs.getLong(6), rs.getString(7), rs.getString(8), rs.getString(9),
							(long) 0, "0", "0", "0", "0", (long) 0, "0");
				} else {
					aux.id_user = null;
				}
			}
		} catch (SQLException e) {
			String className = ActaBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, "madaCPanel", e);
		}
		return (aux);
	}
		

	
	
}





	
	
	
