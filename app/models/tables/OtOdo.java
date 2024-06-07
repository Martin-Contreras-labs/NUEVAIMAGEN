package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.forms.FormCotizaOdo;
import models.utilities.DecimalFormato;
import models.utilities.Fechas;


public class OtOdo {
	public Long id;
	public Long id_cotiOdo;
	public Long numero;
	public String fecha;
	public String otOdoPDF;
	public Long esEliminable;
	public Long id_otEstado;
	public String fechaConfirmada;
	public Long confirmada;
	public String observaciones;
	public Long id_operadorServicio;
	
	public String fechaActualizacion;
	public String fechaEnvio;
	public String nameOperadorServicio;
	
	public OtOdo(Long id, Long id_cotiOdo, Long numero, String fecha,
			String otOdoPDF, Long esEliminable, Long id_otEstado, String fechaConfirmada, Long confirmada,
			String observaciones, Long id_operadorServicio, String fechaActualizacion, String fechaEnvio, String nameOperadorServicio) {
		super();
		this.id = id;
		this.id_cotiOdo = id_cotiOdo;
		this.numero = numero;
		this.fecha = fecha;
		this.otOdoPDF = otOdoPDF;
		this.esEliminable = esEliminable;
		this.id_otEstado = id_otEstado;
		this.fechaConfirmada = fechaConfirmada;
		this.confirmada = confirmada;
		this.observaciones = observaciones;
		this.id_operadorServicio = id_operadorServicio;
		this.fechaActualizacion = fechaActualizacion;
		this.fechaEnvio = fechaEnvio;
		this.nameOperadorServicio = nameOperadorServicio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId_cotiOdo() {
		return id_cotiOdo;
	}

	public void setId_cotiOdo(Long id_cotiOdo) {
		this.id_cotiOdo = id_cotiOdo;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getOtOdoPDF() {
		return otOdoPDF;
	}

	public void setOtOdoPDF(String otOdoPDF) {
		this.otOdoPDF = otOdoPDF;
	}

	public Long getEsEliminable() {
		return esEliminable;
	}

	public void setEsEliminable(Long esEliminable) {
		this.esEliminable = esEliminable;
	}

	public Long getId_otEstado() {
		return id_otEstado;
	}

	public void setId_otEstado(Long id_otEstado) {
		this.id_otEstado = id_otEstado;
	}

	public String getFechaConfirmada() {
		return fechaConfirmada;
	}

	public void setFechaConfirmada(String fechaConfirmada) {
		this.fechaConfirmada = fechaConfirmada;
	}

	public Long getConfirmada() {
		return confirmada;
	}

	public void setConfirmada(Long confirmada) {
		this.confirmada = confirmada;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Long getId_operadorServicio() {
		return id_operadorServicio;
	}

	public void setId_operadorServicio(Long id_operadorServicio) {
		this.id_operadorServicio = id_operadorServicio;
	}

	public String getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(String fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public String getFechaEnvio() {
		return fechaEnvio;
	}

	public void setFechaEnvio(String fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public String getNameOperadorServicio() {
		return nameOperadorServicio;
	}

	public void setNameOperadorServicio(String nameOperadorServicio) {
		this.nameOperadorServicio = nameOperadorServicio;
	}

	public OtOdo(){super();}

	static SimpleDateFormat myformatfecha = new SimpleDateFormat("dd-MM-yyyy");
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	
	
	public static Long anioPrimeraOt(Connection con, String db) {
		Fechas hoy = Fechas.hoy();
		String[] aux = hoy.getFechaStrAAMMDD().split("-");
		Long year = Long.parseLong(aux[0]);
		try {
			PreparedStatement smt = con
					.prepareStatement(" SELECT year(min(fecha)) FROM `"+db+"`.otOdo;" );
			ResultSet resultado = smt.executeQuery();
			if (resultado.next()) {	
				year = resultado.getLong(1);	
			}
			if(year < (long)2000) {
				year = Long.parseLong(aux[0]);
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (year);
	}
	
	
	public static Map<Long,OtOdo> mapAll(Connection con, String db){
		Map<Long,OtOdo> map = new HashMap<Long,OtOdo>();
		List<OtOdo> lista = OtOdo.all(con, db);
		lista.forEach(l->{
			map.put(l.getId(), l);
		});
		return(map);
	}
	
	public static Map<Long,Long> mapAll_idCoti_vs_idOt(Connection con,String db) {
		Map<Long,Long> map = new HashMap<Long,Long>();
		List<OtOdo> lista = OtOdo.all(con, db);
		for(OtOdo x: lista) {
			map.put(x.id_cotiOdo, x.id);
		}
		return(map);
	}
	
	public static Long findNuevoNumero(Connection con, String db){
		Long numeroOt=(long) 1;
		try {
			PreparedStatement smt = con
					.prepareStatement(" SELECT max(numero)+1 from `"+db+"`.otOdo;");
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				if(rs.getLong(1)>1) {
					numeroOt = rs.getLong(1);
				}
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(numeroOt);
	}
	
	public static boolean existeNumero(Connection con, String db, Long numeroOt){
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement(" SELECT numero from `"+db+"`.otOdo where numero = ?;");
			smt.setLong(1, numeroOt);
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				flag = true;
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(flag);
	}
	
	public static boolean existeIdOdoCoti(Connection con, String db, Long id_cotiOdo){
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement(" SELECT id_cotiOdo from `"+db+"`.otOdo where id_cotiOdo = ?;");
			smt.setLong(1, id_cotiOdo);
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				flag = true;
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(flag);
	}
	
	public static OtOdo find(Connection con, String db, Long id_ot) {
		OtOdo aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement("select otOdo.id, otOdo.id_cotiOdo, otOdo.numero, otOdo.fecha, otOdo.otOdoPDF, "
							+ " otOdo.esEliminable, otOdo.id_otEstado, ifnull(otOdo.fechaConfirmada,''), otOdo.confirmada, "
							+ " otOdo.observaciones, otOdo.id_operadorServicio, "
							+ " ifnull(otOdo.fechaActualizacion,''), ifnull(otOdo.fechaEnvio,''), "
							+ " ifnull(operadorServicio.nombre,'') "
							+ " from `"+db+"`.otOdo "
							+ " left join `"+db+"`.operadorServicio on operadorServicio.id = otOdo.id_operadorServicio "
							+ " where otOdo.id = ?;" );
			smt.setLong(1, id_ot);
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				aux = new OtOdo(rs.getLong(1),rs.getLong(2),rs.getLong(3), rs.getString(4), rs.getString(5),
						rs.getLong(6), rs.getLong(7), rs.getString(8), rs.getLong(9),rs.getString(10), rs.getLong(11),rs.getString(12), 
						rs.getString(13),rs.getString(14));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (aux);
	}
	
	public static OtOdo findPorNumero(Connection con, String db, Long numeroOt) {
		OtOdo aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement("select otOdo.id, otOdo.id_cotiOdo, otOdo.numero, otOdo.fecha, otOdo.otOdoPDF, "
							+ " otOdo.esEliminable, otOdo.id_otEstado, ifnull(otOdo.fechaConfirmada,''), otOdo.confirmada, "
							+ " otOdo.observaciones, otOdo.id_operadorServicio, "
							+ " ifnull(otOdo.fechaActualizacion,''), ifnull(otOdo.fechaEnvio,''), "
							+ " ifnull(operadorServicio.nombre,'') "
							+ " from `"+db+"`.otOdo "
							+ " left join `"+db+"`.operadorServicio on operadorServicio.id = otOdo.id_operadorServicio "
							+ " where otOdo.numero = ?;" );
			smt.setLong(1, numeroOt);
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				aux = new OtOdo(rs.getLong(1),rs.getLong(2),rs.getLong(3), rs.getString(4), rs.getString(5),
						rs.getLong(6), rs.getLong(7), rs.getString(8), rs.getLong(9),rs.getString(10), rs.getLong(11),rs.getString(12), 
						rs.getString(13),rs.getString(14));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (aux);
	}
	
	public static boolean modifyXCampo(Connection con, String db, String campo, String valor, Long id_ot) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("UPDATE `"+db+"`.otOdo SET `"+campo+"` = ? WHERE id = ?;");
			smt.setString(1, valor.trim());
			smt.setLong(2, id_ot);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static List<OtOdo> allxConfirmar(Connection con,String db) {
		List<OtOdo> lista = new ArrayList<OtOdo>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select otOdo.id, otOdo.id_cotiOdo, otOdo.numero, otOdo.fecha, otOdo.otOdoPDF, "
							+ " otOdo.esEliminable, otOdo.id_otEstado, ifnull(otOdo.fechaConfirmada,''), otOdo.confirmada, "
							+ " otOdo.observaciones, otOdo.id_operadorServicio, "
							+ " ifnull(otOdo.fechaActualizacion,''), ifnull(otOdo.fechaEnvio,''), "
							+ " ifnull(operadorServicio.nombre,'') "
							+ " from `"+db+"`.otOdo "
							+ " left join `"+db+"`.operadorServicio on operadorServicio.id = otOdo.id_operadorServicio " +
							" where otOdo.esEliminable = 1 and otOdo.confirmada = 0"+
							" order by otOdo.fecha desc, otOdo.numero desc;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				lista.add(new OtOdo(rs.getLong(1),rs.getLong(2),rs.getLong(3), rs.getString(4), rs.getString(5),
						rs.getLong(6), rs.getLong(7), rs.getString(8), rs.getLong(9),rs.getString(10), rs.getLong(11),rs.getString(12), 
						rs.getString(13),rs.getString(14)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<OtOdo> allEliminables(Connection con, String db) {
		List<OtOdo> lista = new ArrayList<OtOdo>();
		try {
			List<Long> conVentas = new ArrayList<Long>();
			PreparedStatement smt2 = con
					.prepareStatement(" select " +
							" id_cotiOdo " +
							" from `"+db+"`.ventaServicio " +
							" group by id_cotiOdo;");
			ResultSet rs2 = smt2.executeQuery();
			while (rs2.next()) {
				conVentas.add(rs2.getLong(1));
			}
			
			String condicion = "";
			if(conVentas.size()>0) {
				condicion = conVentas.toString();
				condicion = condicion.substring(1,condicion.length()-1);
				condicion = " where otOdo.id_cotiOdo not in ("+condicion+") ";
			}
			
			PreparedStatement smt = con
					.prepareStatement("select otOdo.id, otOdo.id_cotiOdo, otOdo.numero, otOdo.fecha, otOdo.otOdoPDF, "
							+ " otOdo.esEliminable, otOdo.id_otEstado, ifnull(otOdo.fechaConfirmada,''), otOdo.confirmada, "
							+ " otOdo.observaciones, otOdo.id_operadorServicio, "
							+ " ifnull(otOdo.fechaActualizacion,''), ifnull(otOdo.fechaEnvio,''), "
							+ " ifnull(operadorServicio.nombre,'') "
							+ " from `"+db+"`.otOdo "
							+ " left join `"+db+"`.operadorServicio on operadorServicio.id = otOdo.id_operadorServicio " +
							 condicion +
							" order by otOdo.fecha desc, otOdo.numero desc;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				lista.add(new OtOdo(rs.getLong(1),rs.getLong(2),rs.getLong(3), rs.getString(4), rs.getString(5),
						rs.getLong(6), rs.getLong(7), rs.getString(8), rs.getLong(9),rs.getString(10), rs.getLong(11),rs.getString(12), 
						rs.getString(13),rs.getString(14)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<OtOdo> all(Connection con,String db) {
		List<OtOdo> lista = new ArrayList<OtOdo>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select otOdo.id, otOdo.id_cotiOdo, otOdo.numero, otOdo.fecha, otOdo.otOdoPDF, "
							+ " otOdo.esEliminable, otOdo.id_otEstado, ifnull(otOdo.fechaConfirmada,''), otOdo.confirmada, "
							+ " otOdo.observaciones, otOdo.id_operadorServicio, "
							+ " ifnull(otOdo.fechaActualizacion,''), ifnull(otOdo.fechaEnvio,''), "
							+ " ifnull(operadorServicio.nombre,'') "
							+ " from `"+db+"`.otOdo "
							+ " left join `"+db+"`.operadorServicio on operadorServicio.id = otOdo.id_operadorServicio " +
							" order by otOdo.fecha desc, otOdo.numero desc;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				lista.add(new OtOdo(rs.getLong(1),rs.getLong(2),rs.getLong(3), rs.getString(4), rs.getString(5),
						rs.getLong(6), rs.getLong(7), rs.getString(8), rs.getLong(9),rs.getString(10), rs.getLong(11),rs.getString(12), 
						rs.getString(13),rs.getString(14)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<OtOdo> allConfirmadas(Connection con,String db) {
		List<OtOdo> lista = new ArrayList<OtOdo>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select otOdo.id, otOdo.id_cotiOdo, otOdo.numero, otOdo.fecha, otOdo.otOdoPDF, "
							+ " otOdo.esEliminable, otOdo.id_otEstado, ifnull(otOdo.fechaConfirmada,''), otOdo.confirmada, "
							+ " otOdo.observaciones, otOdo.id_operadorServicio, "
							+ " ifnull(otOdo.fechaActualizacion,''), ifnull(otOdo.fechaEnvio,''), "
							+ " ifnull(operadorServicio.nombre,'') "
							+ " from `"+db+"`.otOdo "
							+ " left join `"+db+"`.operadorServicio on operadorServicio.id = otOdo.id_operadorServicio " +
							" where otOdo.esEliminable = 0 and otOdo.confirmada = 1"+
							" order by otOdo.fecha desc, otOdo.numero desc;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				lista.add(new OtOdo(rs.getLong(1),rs.getLong(2),rs.getLong(3), rs.getString(4), rs.getString(5),
						rs.getLong(6), rs.getLong(7), rs.getString(8), rs.getLong(9),rs.getString(10), rs.getLong(11),rs.getString(12), 
						rs.getString(13),rs.getString(14)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<OtOdo> allConfirmadasPorAnio(Connection con,String db, Long year) {
		List<OtOdo> lista = new ArrayList<OtOdo>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select otOdo.id, otOdo.id_cotiOdo, otOdo.numero, otOdo.fecha, otOdo.otOdoPDF, "
							+ " otOdo.esEliminable, otOdo.id_otEstado, ifnull(otOdo.fechaConfirmada,''), otOdo.confirmada, "
							+ " otOdo.observaciones, otOdo.id_operadorServicio, "
							+ " ifnull(otOdo.fechaActualizacion,''), ifnull(otOdo.fechaEnvio,''), "
							+ " ifnull(operadorServicio.nombre,'') "
							+ " from `"+db+"`.otOdo "
							+ " left join `"+db+"`.operadorServicio on operadorServicio.id = otOdo.id_operadorServicio " +
							" where otOdo.esEliminable = 0 and otOdo.confirmada = 1 and year(otOdo.fecha) = ?"+
							" order by otOdo.fecha desc, otOdo.numero desc;");
			smt.setLong(1, year);
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				lista.add(new OtOdo(rs.getLong(1),rs.getLong(2),rs.getLong(3), rs.getString(4), rs.getString(5),
						rs.getLong(6), rs.getLong(7), rs.getString(8), rs.getLong(9),rs.getString(10), rs.getLong(11),rs.getString(12), 
						rs.getString(13),rs.getString(14)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static String vistaModalVerOtOdo(Connection con, String db, Long id_otOdo, Map<String,String> mapDiccionario){
		String vista="";
		OtOdo otOdo = OtOdo.find(con, db, id_otOdo);
		CotiOdo cotiOdo = CotiOdo.find(con, db, otOdo.getId_cotiOdo());
		Long id_cotiOdo = otOdo.getId_cotiOdo();
		Map<Long,Double> mapVentas = VentaServicio.mapTotalCantPorServ(con, db, id_otOdo);
		
		String fecha = Fechas.AAMMDD(cotiOdo.getFecha());
		Cliente cliente = Cliente.find(con, db, cotiOdo.getId_cliente());
		String rutCliente = "";
		String nomCliente = "";
		if(cliente!=null) {
			rutCliente = cliente.getRut();
			nomCliente = cliente.getNickName();
		}
		Proyecto proyecto = Proyecto.find(con, db, cotiOdo.getId_proyecto());
		String nomProyecto = "";
		if(proyecto!=null) {
			nomProyecto = proyecto.getNickName();
		}
		
		FormCotizaOdo formCotizaOdo = new FormCotizaOdo(cotiOdo, fecha, rutCliente, nomCliente, nomProyecto);
		formCotizaOdo.id_cotiOdo = id_cotiOdo;
		formCotizaOdo.dctoOdo = DecimalFormato.formato(cotiOdo.getDctoOdo()*100, (long)2);
		
		List<List<String>> listadoServicios = FormCotizaOdo.listServiciosConValoresCoti(con, db, id_cotiOdo);
		
		Long numDec = (long) 0;
		if(listadoServicios.size()>0) {
			String auxNumDec = listadoServicios.get(listadoServicios.size()-1).get(12);
			numDec = Long.parseLong(auxNumDec);
		}
		
		BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, db, cotiOdo.getId_bodegaEmpresa());
		vista=vista+
		"<table class='table table-sm table-hover table-bordered table-condensed table-fluid'>"+
			"<tr>"+
				"<td><label>N° "+mapDiccionario.get("OT")+": "+otOdo.numero+"</label></td>"+
				"<td><label>Fecha "+mapDiccionario.get("OT")+": "+Fechas.DDMMAA(otOdo.fecha)+"</label></td>"+
				"<td><label>N° COTI: "+cotiOdo.numero+"</label></td>"+
				"<td><label>Fecha COTI: "+Fechas.DDMMAA(cotiOdo.fecha)+"</label></td>"+
				
				"<td><label>"+mapDiccionario.get("Bodega")+"/Proyecto: "+bodega.getNombre()+"</label></td>"+
				"<td><label>N° OC: "+cotiOdo.getNumeroOC()+"</label></td>"+
			"</tr>"+
			"<tr>"+
				"<td colspan='2'><label>"+mapDiccionario.get("RUT")+" Cliente: "+bodega.getRutCliente()+"</label></td>"+
				"<td colspan='2'><label>Nombre Cliente: "+bodega.getNickCliente()+"</label></td>"+
				"<td><label>Nombre Proyecto: "+bodega.getNickProyecto()+"</label></td>"+
				"<td>"+
				"</td>"+
			"</tr>"+
			"<tr>"+
				"<td colspan='4' style='text-align:left'>OBSERVACIONES "+mapDiccionario.get("OT")+": "+otOdo.getObservaciones()+"</td>"+
				"<td colspan='4' style='text-align:left'>OBSERVACIONES COTI: "+cotiOdo.getObservaciones()+"</td>"+
			"</tr>"+
			"<tr>"+
				"<td colspan='2' style='text-align:left'>ULTIMA ACTUALIZACION: "+Fechas.DDMMAA(otOdo.getFechaActualizacion())+"</td>"+
				"<td colspan='2' style='text-align:left'>FECHA DE SERVICIO: "+Fechas.DDMMAA(otOdo.getFechaEnvio())+"</td>"+
				"<td colspan='2' style='text-align:left'>OPERADOR: "+otOdo.getNameOperadorServicio()+"</td>"+
			"</tr>"+
			
		"</table>"+
		"<table class='table table-sm table-hover table-bordered table-condensed table-fluid'>"+
			"<thead style='background-color: #eeeeee'>"+
				"<TR> "+
					"<TH colspan='11' style= 'text-align: center;'>ORDEN ORIGINAL</TH>"+
					"<TH colspan='2' style= 'text-align: center;'>VENTAS</TH>"+
				"</TR>"+
				"<TR> "+
					"<TH style= 'text-align: center;'>FAMILIA</TH>"+
					"<TH style= 'text-align: center;'>CODIGO</TH>"+
					"<TH style= 'text-align: center;'>SERVICIO</TH>"+
					"<TH style= 'text-align: center;'>UN</TH>"+
					"<TH style= 'text-align: center;'>CANTIDAD</TH>"+
					"<TH style= 'text-align: center;'>MON</TH>"+
					"<TH style= 'text-align: center;'>PRECIO<br>UNITARIO</TH>"+
					"<TH style= 'text-align: center;'>TOTAL</TH>"+
					"<TH style= 'text-align: center;'>Aplica<br>Minimo</TH>"+
					"<TH style= 'text-align: center;'>Cantidad<br>Minimo</TH>"+
					"<TH style= 'text-align: center;'>Precio<br>Adicional</TH>"+
					"<TH style= 'text-align: center;'>CANT</TH>"+
				"</TR>"+
			"</thead>"+
		"<tbody>";
		
		Double total = (double)0;
		Double cant = (double)0;
		Double totalCantVendida = (double)0;
		
		for(int i=0;i<listadoServicios.size();i++){
			Double cantidad = Double.parseDouble(listadoServicios.get(i).get(5).replaceAll(",", ""));
			if(cantidad > 0) {
				cant += cantidad;
				total += Double.parseDouble(listadoServicios.get(i).get(8).replaceAll(",", ""));
				
				Long id_servicio = Long.parseLong(listadoServicios.get(i).get(0));
				Double cantVendida = mapVentas.get(id_servicio);
				
				if(cantVendida==null) {
					cantVendida = (double)0;
				}
				totalCantVendida += cantVendida;
				
				vista=vista+
				"<TR>"+
					"<td style='text-align:left;'>"+listadoServicios.get(i).get(1)+"</td>"+
					"<td style='text-align:left;'>"+listadoServicios.get(i).get(2)+"</td>"+
					"<td style='text-align:left;'>"+listadoServicios.get(i).get(3)+"</td>"+
					"<td style='text-align:center;'>"+listadoServicios.get(i).get(4)+"</td>"+
					"<td style='text-align:right;'>"+listadoServicios.get(i).get(5)+"</td>"+
					"<td style='text-align:center;'>"+listadoServicios.get(i).get(6)+"</td>"+
					"<td style='text-align:right;'>"+listadoServicios.get(i).get(7)+"</td>"+
					"<td style='text-align:right;'>"+listadoServicios.get(i).get(8)+"</td>"+
					"<td style='text-align:center;'>";
						if(listadoServicios.get(i).get(9).equals("0")){
							vista = vista + "NO";
						}else{
							vista = vista + "SI";
						}
					vista=vista+
					"</td>"+
					"<td style='text-align:right;'>"+listadoServicios.get(i).get(10)+"</td>"+
					"<td style='text-align:right;'>"+listadoServicios.get(i).get(11)+"</td>"+
					"<td style='text-align:right;'>"+DecimalFormato.formato(cantVendida, (long)2)+"</td>"+
				"</TR>";
			}
 		}
		vista=vista+
		"</tbody>"+
		"<tfoot style='background-color: #eeeeee'>";
		
		Double dcto = Double.parseDouble(formCotizaOdo.dctoOdo.replaceAll(",", "")) / 100;
		
		if((double) cotiOdo.getDctoOdo() > (double) 0 ){
			vista=vista+
					"<TR>"+
							
							"<TH colspan='4' style='text-align: right;'>SUB-TOTALES </TH>"+
							"<TH style='text-align: right;'>"+DecimalFormato.formato(cant, (long)2)+"</TH>"+
							"<TH colspan='2'></TH>"+
							"<TH style='text-align: right;'>"+DecimalFormato.formato(total, numDec)+"</TH>"+
							"<TH colspan='3'></TH>"+
							"<TH style='text-align: right;'>"+DecimalFormato.formato(totalCantVendida, (long)2)+"</TH>"+
					"</TR>"+
					"<TR>"+
							"<TH colspan='4' style='text-align: right;'>DESCUENTO</TH>"+
							"<TH colspan='3'></TH>"+
							"<TH style='text-align: right;'>"+formCotizaOdo.dctoOdo+" %</TH>"+
							"<TH colspan='3'></TH>"+
							"<TH></TH>"+
					"</TR>";
		}
		
		vista=vista+
				
				"<TR>"+
					"<TH colspan='4' style='text-align: right;'>TOTALES </TH>"+
					"<TH style='text-align: right;'>"+DecimalFormato.formato(cant, (long)2)+"</TH>"+
					"<TH colspan='2'></TH>"+
					"<TH style='text-align: right;'>"+DecimalFormato.formato(total*(1-dcto), numDec)+"</TH>"+
					"<TH colspan='3'></TH>"+
					"<TH style='text-align: right;'>"+DecimalFormato.formato(totalCantVendida, (long)2)+"</TH>"+
				"</TR>"+
			"</tfoot>"+
		"</table>";
		return(vista);
	}
	
	public static String vistaRevisarOtOdo(Connection con, String db, Long id_otOdo, Map<String,String> mapDiccionario){
		String vista="";
		OtOdo otOdo = OtOdo.find(con, db, id_otOdo);
		CotiOdo cotiOdo = CotiOdo.find(con, db, otOdo.getId_cotiOdo());
		Long id_cotiOdo = otOdo.getId_cotiOdo();
		Map<Long,Double> mapVentas = VentaServicio.mapTotalCantPorServ(con, db, otOdo.getId_cotiOdo());
		
		String fecha = Fechas.AAMMDD(cotiOdo.getFecha());
		Cliente cliente = Cliente.find(con, db, cotiOdo.getId_cliente());
		String rutCliente = "";
		String nomCliente = "";
		if(cliente!=null) {
			rutCliente = cliente.getRut();
			nomCliente = cliente.getNickName();
		}
		Proyecto proyecto = Proyecto.find(con, db, cotiOdo.getId_proyecto());
		String nomProyecto = "";
		if(proyecto!=null) {
			nomProyecto = proyecto.getNickName();
		}
		
		FormCotizaOdo formCotizaOdo = new FormCotizaOdo(cotiOdo, fecha, rutCliente, nomCliente, nomProyecto);
		formCotizaOdo.id_cotiOdo = id_cotiOdo;
		formCotizaOdo.dctoOdo = DecimalFormato.formato(cotiOdo.getDctoOdo()*100, (long)2);
		
		List<List<String>> listadoServicios = FormCotizaOdo.listServiciosConValoresCoti(con, db, id_cotiOdo);
		
		Long numDec = (long) 0;
		if(listadoServicios.size()>0) {
			String auxNumDec = listadoServicios.get(listadoServicios.size()-1).get(12);
			numDec = Long.parseLong(auxNumDec);
		}
		
		BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, db, cotiOdo.getId_bodegaEmpresa());
		vista=vista+
		"<table class='table table-sm table-hover table-bordered table-condensed table-fluid'>"+
			"<tr>"+
				"<td><label>N° "+mapDiccionario.get("OT")+": "+otOdo.numero+"</label></td>"+
				"<td><label>Fecha "+mapDiccionario.get("OT")+": "+Fechas.DDMMAA(otOdo.fecha)+"</label></td>"+
				"<td><label>N° COTI: "+cotiOdo.numero+"</label></td>"+
				"<td><label>Fecha COTI: "+Fechas.DDMMAA(cotiOdo.fecha)+"</label></td>"+
				
				"<td><label>"+mapDiccionario.get("Bodega")+"/Proyecto: "+bodega.getNombre()+"</label></td>"+
				"<td><label>N° OC: "+cotiOdo.getNumeroOC()+"</label></td>"+
			"</tr>"+
			"<tr>"+
				"<td colspan='2'><label>"+mapDiccionario.get("RUT")+" Cliente: "+bodega.getRutCliente()+"</label></td>"+
				"<td colspan='2'><label>Nombre Cliente: "+bodega.getNickCliente()+"</label></td>"+
				"<td><label>Nombre Proyecto: "+bodega.getNickProyecto()+"</label></td>"+
				"<td>"+
				"</td>"+
			"</tr>"+
			"<tr>"+
				"<td colspan='4' style='text-align:left'>OBSERVACIONES "+mapDiccionario.get("OT")+": "+otOdo.getObservaciones()+"</td>"+
				"<td colspan='4' style='text-align:left'>OBSERVACIONES COTI: "+cotiOdo.getObservaciones()+"</td>"+
			"</tr>"+
			"<tr>"+
				"<td colspan='2' style='text-align:left'>ULTIMA ACTUALIZACION: "+Fechas.DDMMAA(otOdo.getFechaActualizacion())+"</td>"+
				"<td colspan='2' style='text-align:left'>FECHA DE SERVICIO: "+Fechas.DDMMAA(otOdo.getFechaEnvio())+"</td>"+
				"<td colspan='2' style='text-align:left'>OPERADOR: "+otOdo.getNameOperadorServicio()+"</td>"+
			"</tr>"+
			
		"</table>"+
		"<table class='table table-sm table-hover table-bordered table-condensed table-fluid'>"+
			"<thead style='background-color: #eeeeee'>"+
				"<TR> "+
					"<TH colspan='11' style= 'text-align: center;'>ORDEN ORIGINAL</TH>"+
					"<TH colspan='2' style= 'text-align: center;'>VENTAS</TH>"+
				"</TR>"+
				"<TR> "+
					"<TH style= 'text-align: center;'>FAMILIA</TH>"+
					"<TH style= 'text-align: center;'>CODIGO</TH>"+
					"<TH style= 'text-align: center;'>SERVICIO</TH>"+
					"<TH style= 'text-align: center;'>UN</TH>"+
					"<TH style= 'text-align: center;'>CANTIDAD</TH>"+
					"<TH style= 'text-align: center;'>MON</TH>"+
					"<TH style= 'text-align: center;'>PRECIO<br>UNITARIO</TH>"+
					"<TH style= 'text-align: center;'>TOTAL</TH>"+
					"<TH style= 'text-align: center;'>Aplica<br>Minimo</TH>"+
					"<TH style= 'text-align: center;'>Cantidad<br>Minimo</TH>"+
					"<TH style= 'text-align: center;'>Precio<br>Adicional</TH>"+
					"<TH style= 'text-align: center;'>CANT</TH>"+
					"<TH style= 'text-align: center;'>HIST</TH>"+
				"</TR>"+
			"</thead>"+
		"<tbody>";
		
		Double total = (double)0;
		Double cant = (double)0;
		Double totalCantVendida = (double)0;
		
		for(int i=0;i<listadoServicios.size();i++){
			Double cantidad = Double.parseDouble(listadoServicios.get(i).get(5).replaceAll(",", ""));
			if(cantidad > 0) {
				cant += cantidad;
				total += Double.parseDouble(listadoServicios.get(i).get(8).replaceAll(",", ""));
				
				Long id_servicio = Long.parseLong(listadoServicios.get(i).get(0));
				Double cantVendida = mapVentas.get(id_servicio);
				
				if(cantVendida==null) {
					cantVendida = (double)0;
				}
				totalCantVendida += cantVendida;
				
				vista=vista+
				"<TR>"+
					"<td style='text-align:left;'>"+listadoServicios.get(i).get(1)+"</td>"+
					"<td style='text-align:left;'>"+listadoServicios.get(i).get(2)+"</td>"+
					"<td style='text-align:left;'>"+listadoServicios.get(i).get(3)+"</td>"+
					"<td style='text-align:center;'>"+listadoServicios.get(i).get(4)+"</td>"+
					"<td style='text-align:right;'>"+listadoServicios.get(i).get(5)+"</td>"+
					"<td style='text-align:center;'>"+listadoServicios.get(i).get(6)+"</td>"+
					"<td style='text-align:right;'>"+listadoServicios.get(i).get(7)+"</td>"+
					"<td style='text-align:right;'>"+listadoServicios.get(i).get(8)+"</td>"+
					"<td style='text-align:center;'>";
						if(listadoServicios.get(i).get(9).equals("0")){
							vista = vista + "NO";
						}else{
							vista = vista + "SI";
						}
					vista=vista+
					"</td>"+
					"<td style='text-align:right;'>"+listadoServicios.get(i).get(10)+"</td>"+
					"<td style='text-align:right;'>"+listadoServicios.get(i).get(11)+"</td>"+
					"<td style='text-align:right;'>"+DecimalFormato.formato(cantVendida, (long)2)+"</td>"+
					
					"<td style='text-align: center;' title='Trazabilidad de las vetas'>"+
						"<a href='#' onclick='trazaOt("+id_otOdo+","+id_servicio.toString()+")'>"+
							"<kbd style='background-color: rgb(90, 200, 245)'><font color='green'>H</font></kbd>"+
						"</a>"+
					"</td>"+
				"</TR>";
			}
 		}
		vista=vista+
		"</tbody>"+
		"<tfoot style='background-color: #eeeeee'>";
		
		Double dcto = Double.parseDouble(formCotizaOdo.dctoOdo.replaceAll(",", "")) / 100;
		
		if((double) cotiOdo.getDctoOdo() > (double) 0 ){
			vista=vista+
					"<TR>"+
							
							"<TH colspan='4' style='text-align: right;'>SUB-TOTALES </TH>"+
							"<TH style='text-align: right;'>"+DecimalFormato.formato(cant, (long)2)+"</TH>"+
							"<TH colspan='2'></TH>"+
							"<TH style='text-align: right;'>"+DecimalFormato.formato(total, numDec)+"</TH>"+
							"<TH colspan='3'></TH>"+
							"<TH style='text-align: right;'>"+DecimalFormato.formato(totalCantVendida, (long)2)+"</TH>"+
							"<TH></TH>"+
					"</TR>"+
					"<TR>"+
							"<TH colspan='4' style='text-align: right;'>DESCUENTO</TH>"+
							"<TH colspan='3'></TH>"+
							"<TH style='text-align: right;'>"+formCotizaOdo.dctoOdo+" %</TH>"+
							"<TH colspan='3'></TH>"+
							"<TH></TH>"+
							"<TH></TH>"+
					"</TR>";
		}
		
		vista=vista+
				
				"<TR>"+
					"<TH colspan='4' style='text-align: right;'>TOTALES </TH>"+
					"<TH style='text-align: right;'>"+DecimalFormato.formato(cant, (long)2)+"</TH>"+
					"<TH colspan='2'></TH>"+
					"<TH style='text-align: right;'>"+DecimalFormato.formato(total*(1-dcto), numDec)+"</TH>"+
					"<TH colspan='3'></TH>"+
					"<TH style='text-align: right;'>"+DecimalFormato.formato(totalCantVendida, (long)2)+"</TH>"+
					"<TH></TH>"+
				"</TR>"+
			"</tfoot>"+
		"</table>";
		return(vista);
	}
	
	public static String vistaCabezeraOt(Connection con, String db, OtOdo otOdo, CotiOdo cotiOdo, Map<String,String> mapeoDiccionario){
		String vista="";
		
		BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, db, cotiOdo.getId_bodegaEmpresa());
		vista=vista+
		"<table class='table table-sm table-hover table-bordered table-condensed table-fluid'>"+
			"<tr>"+
				"<td><input type='hidden' name='id_otOdo' value='"+otOdo.getId()+"'<label>N° "+mapeoDiccionario.get("OT")+": "+otOdo.numero+"</label></td>"+
				"<td><label>Fecha "+mapeoDiccionario.get("OT")+": "+Fechas.DDMMAA(otOdo.fecha)+"</label></td>"+
				"<td><label>N° COTI: "+cotiOdo.numero+"</label></td>"+
				"<td><label>Fecha COTI: "+Fechas.DDMMAA(cotiOdo.fecha)+"</label></td>"+
				
				"<td><label>"+mapeoDiccionario.get("Bodega")+"/Proyecto: "+bodega.getNombre()+"</label></td>"+
				"<td><label>N° OC: "+cotiOdo.getNumeroOC()+"</label></td>"+
			"</tr>"+
			"<tr>"+
				"<td colspan='2'><label>"+mapeoDiccionario.get("RUT")+" Cliente: "+bodega.getRutCliente()+"</label></td>"+
				"<td colspan='2'><label>Nombre Cliente: "+bodega.getNickCliente()+"</label></td>"+
				"<td><label>Nombre Proyecto: "+bodega.getNickProyecto()+"</label></td>"+
				"<td>"+
				"</td>"+
			"</tr>"+
			"<tr>"+
				"<td colspan='4' style='text-align:left'>OBSERVACIONES "+mapeoDiccionario.get("OT")+": "+otOdo.getObservaciones()+"</td>"+
				"<td colspan='4' style='text-align:left'>OBSERVACIONES COTI: "+cotiOdo.getObservaciones()+"</td>"+
			"</tr>"+
			
		"</table>";
		return(vista);
	}
	
	public static boolean delete(Connection con, String db, OtOdo aux) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("delete from `"+db+"`.listaPrecioServicio where id_cotiOdo = ?;");
			smt.setLong(1, aux.getId_cotiOdo());
			smt.executeUpdate();
			smt.close();
			
			PreparedStatement smt4 = con
					.prepareStatement("delete from `"+db+"`.precioVariableServicio where id_cotiOdo = ?;");
			smt4.setLong(1, aux.getId_cotiOdo());
			smt4.executeUpdate();
			smt4.close();
			
			PreparedStatement smt2 = con
					.prepareStatement("delete from `"+db+"`.otOdo where id = ?;");
			smt2.setLong(1, aux.id);
			smt2.executeUpdate();
			smt2.close();
			
			PreparedStatement smt3 = con
					.prepareStatement("UPDATE `"+db+"`.cotiOdo SET id_otOdo = 0, id_bodegaEmpresa = 0, esModificable = 1, confirmada = 0, fechaConfirmada = null WHERE id = ?;");
			smt3.setLong(1, aux.getId_cotiOdo());
			smt3.executeUpdate();
			smt3.close();
			flag = true;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean confirmar(Connection con, String db, String listadoIdOtConfirmar){
		boolean flag = false;
		try {
			PreparedStatement smt = con
				.prepareStatement("update `"+db+"`.otOdo set esEliminable = ?, confirmada = ?, fechaConfirmada = ? "
						+ " where id in "+listadoIdOtConfirmar+";");
			smt.setString(1, "0");
			smt.setString(2, "1");
			smt.setString(3, Fechas.hoy().getFechaStrAAMMDD());
			smt.executeUpdate();
			smt.close();
			
			PreparedStatement smt2 = con
					.prepareStatement(" select "
							+ " id_bodegaEmpresa, "
							+ " id_servicio, "
							+ " id_moneda, "
							+ " fecha, "
							+ " precio, "
							+ " aplicaMinimo, "
							+ " cantidadMinimo, "
							+ " precioAdicional, "
							+ " id_cotiOdo "
							+ " from `"+db+"`.cotiOdoDetalle "
							+ " left join `"+db+"`.cotiOdo on cotiOdo.id = cotiOdoDetalle.id_cotiOdo "
							+ " where id_otOdo in "+listadoIdOtConfirmar+";");
			ResultSet rs2 = smt2.executeQuery();
			
			List<List<String>> auxLista = new ArrayList<List<String>>();
			while (rs2.next()) {
				List<String> aux = new ArrayList<String>();
				aux.add("'"+rs2.getString(1)+"'");
				aux.add("'"+rs2.getString(2)+"'");
				aux.add("'"+rs2.getString(3)+"'");
				aux.add("'"+rs2.getString(4)+"'");
				aux.add("'"+rs2.getString(5)+"'");
				aux.add("'"+rs2.getString(6)+"'");
				aux.add("'"+rs2.getString(7)+"'");
				aux.add("'"+rs2.getString(8)+"'");
				aux.add("'"+rs2.getString(9)+"'");
				auxLista.add(aux);
			}
			smt2.close();
			rs2.close();
			
			String paraInsert = auxLista.toString();
			if(paraInsert.length()>1) {
				paraInsert = paraInsert.substring(1,paraInsert.length()-1);
				paraInsert = paraInsert.replaceAll("\\[", "\\(");
				paraInsert = paraInsert.replaceAll("\\]", ")");
				
				PreparedStatement smt3 = con
						.prepareStatement("insert into `"+db+"`.listaPrecioServicio "
								+ " (id_bodegaEmpresa, id_servicio, id_moneda, fecha, precio, aplicaMinimo, cantMinimo, precioAdicional, id_cotiOdo) "
								+ " values "+paraInsert+";");
				smt3.executeUpdate();
				smt3.close();
				
				flag = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	
	public static boolean modifyFechaEnvio(Connection con, String db, String fechaActualizacion, String fechaEnvio, Long id_otOdo) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("UPDATE `"+db+"`.otOdo SET  fechaActualizacion = ?, fechaEnvio = ? WHERE id = ?;");
			
			if (fechaEnvio != null && !fechaEnvio.equals("")) {
				smt.setString(1, fechaActualizacion);
				smt.setString(2, fechaEnvio);
			} else {
				smt.setNull(1, Types.INTEGER);
				smt.setNull(2, Types.INTEGER);
			}
			
			smt.setLong(3, id_otOdo);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean modifyOperadorServicio(Connection con, String db, String id_operadorServicio, Long id_otOdo) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("UPDATE `"+db+"`.otOdo SET  id_operadorServicio = ? WHERE id = ?;");
			smt.setString(1, id_operadorServicio);
			smt.setLong(2, id_otOdo);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	
	
	
	
	
	
}
