package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.calculo.Inventarios;



public class OtDespachado {
	public Long id;
	public Long id_ot;
	public Long id_guia;
	public Long id_cotizaDetalle;
	public Long id_equipoOrigen;
	public Long id_equipoDespacho;
	public Double cantidadDespacho;
	public Double cantidadRebajaOt;
	public Long id_bodegaOrigen;
	public Long esVenta;
	public Long id_cotizacion;
	
	public OtDespachado(Long id, Long id_ot,Long id_guia, Long id_cotizaDetalle,
			Long id_equipoOrigen, Long id_equipoDespacho,
			Double cantidadDespacho, Double cantidadRebajaOt,
			Long id_bodegaOrigen,Long esVenta, Long id_cotizacion) {
		super();
		this.id = id;
		this.id_ot = id_ot;
		this.id_guia = id_guia;
		this.id_cotizaDetalle = id_cotizaDetalle;
		this.id_equipoOrigen = id_equipoOrigen;
		this.id_equipoDespacho = id_equipoDespacho;
		this.cantidadDespacho = cantidadDespacho;
		this.cantidadRebajaOt = cantidadRebajaOt;
		this.id_bodegaOrigen = id_bodegaOrigen;
		this.esVenta = esVenta;
		this.id_cotizacion = id_cotizacion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId_ot() {
		return id_ot;
	}

	public void setId_ot(Long id_ot) {
		this.id_ot = id_ot;
	}

	public Long getId_guia() {
		return id_guia;
	}

	public void setId_guia(Long id_guia) {
		this.id_guia = id_guia;
	}

	public Long getId_cotizaDetalle() {
		return id_cotizaDetalle;
	}

	public void setId_cotizaDetalle(Long id_cotizaDetalle) {
		this.id_cotizaDetalle = id_cotizaDetalle;
	}

	public Long getId_equipoOrigen() {
		return id_equipoOrigen;
	}

	public void setId_equipoOrigen(Long id_equipoOrigen) {
		this.id_equipoOrigen = id_equipoOrigen;
	}

	public Long getId_equipoDespacho() {
		return id_equipoDespacho;
	}

	public void setId_equipoDespacho(Long id_equipoDespacho) {
		this.id_equipoDespacho = id_equipoDespacho;
	}

	public Double getCantidadDespacho() {
		return cantidadDespacho;
	}

	public void setCantidadDespacho(Double cantidadDespacho) {
		this.cantidadDespacho = cantidadDespacho;
	}

	public Double getCantidadRebajaOt() {
		return cantidadRebajaOt;
	}

	public void setCantidadRebajaOt(Double cantidadRebajaOt) {
		this.cantidadRebajaOt = cantidadRebajaOt;
	}

	public Long getId_bodegaOrigen() {
		return id_bodegaOrigen;
	}

	public void setId_bodegaOrigen(Long id_bodegaOrigen) {
		this.id_bodegaOrigen = id_bodegaOrigen;
	}

	public Long getEsVenta() {
		return esVenta;
	}

	public void setEsVenta(Long esVenta) {
		this.esVenta = esVenta;
	}

	public Long getId_cotizacion() {
		return id_cotizacion;
	}

	public void setId_cotizacion(Long id_cotizacion) {
		this.id_cotizacion = id_cotizacion;
	}

	public OtDespachado() {super();}
	

	static SimpleDateFormat myformatfecha = new SimpleDateFormat("dd-MM-yyyy");
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	static DecimalFormat myformattasa = new DecimalFormat("#,##0.00 %");
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	
	public static List<List<String>> listSumaDespachadoPorIdOtCantDesp(Connection con, String db, Long idOt) {
		List<List<String>> lista = new ArrayList<List<String>>();
		Map<Long,Double> pesos = Atributo.mapAtributoPESO(con, db);
		try {
			PreparedStatement smt = con
					.prepareStatement(" select otDespachado.id_ot, otDespachado.id_equipoOrigen, otDespachado.id_equipoDespacho, equipo.codigo, equipo.nombre, " +
							" unidad.nombre,sum(cantidadDespacho),sum(cantidadRebajaOt) " +
							" from `"+db+"`.otDespachado " +
							" left join `"+db+"`.equipo on equipo.id = otDespachado.id_equipoDespacho " +
							" left join `"+db+"`.unidad on unidad.id = equipo.id_unidad " +
							" where otDespachado.id_ot=? group by otDespachado.id_equipoOrigen, otDespachado.id_equipoDespacho" +
							" order by equipo.nombre;");
			smt.setLong(1, idOt);
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				
				Double peso = pesos.get(rs.getLong(3));
				if(peso==null) peso=(double)0;
				
				
				List<String> aux = new ArrayList<String>();
				aux.add(rs.getString(1)); 							// 0 idOt
				aux.add(rs.getString(2)); 							// 1 idEquipoOrigen
				aux.add(rs.getString(3)); 							// 2 idEquipoDespacho
				aux.add(rs.getString(4)); 							// 3 codigo
				aux.add(rs.getString(5)); 							// 4 nombre
				aux.add(rs.getString(6)); 							// 5 unidad
				aux.add(myformatdouble.format(rs.getDouble(7))); 	// 6 cantidadDespacho
				aux.add(myformatdouble.format(rs.getDouble(8))); 	// 7 cantidadRebajaOt
				
				aux.add(myformatdouble.format(rs.getDouble(7) * peso)); 	// 8 total kg despachados
				
				lista.add(aux);
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(lista);
	}
	
	public static Map<Long,Double> mapSumaDespachadoPorIdOtCantEquiv(Connection con, String db, Long id_ot) {  
		Map<Long,Double> map = new HashMap<Long,Double>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select id_ot, id_equipoOrigen, sum(cantidadRebajaOt)  " +
							" from `"+db+"`.otDespachado where id_ot=? group by id_equipoOrigen;");
			smt.setLong(1, id_ot);
			ResultSet resultado = smt.executeQuery();
			while (resultado.next()) {
				map.put(resultado.getLong(2), resultado.getDouble(3));
			}
			resultado.close();smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return(map);
	}
	
	public static String modalTrazaPorIdEquipOrigen(Connection con, String db, Long id_ot, Long id_equipoOrigen) {
		List<List<String>> lista = OtDespachado.listHistorialDespPorIdEquipOrigen(con, db, id_ot, id_equipoOrigen);
		String vista =
				"<table class='table table-sm table-hover table-bordered table-condensed table-fluid'>"+
					"<thead style='background-color: #eeeeee'>"+
						"<TR>"
							+ "<th>GRUPO</th>"
							+ "<th>CODIGO</th>"
							+ "<th>EQUIPO</th>"
							+ "<th>UN</th>"
							+ "<th>CANT</th>"
							+ "<th>EQUIV</th>"
							+ "<th>GUIA</th>"
							+ "<th>FECHA</th>"
						+ "</TR>"
					+ "</thead>"
					+ "<tbody>";
				for(int i=0;i<lista.size();i++){
					vista += 
							"<TR>"
								+ "<td style='text-align:left;'>"+lista.get(i).get(10)+"</td>"
								+ "<td style='text-align:left;'>"+lista.get(i).get(3)+"</td>"
								+ "<td style='text-align:left;'>"+lista.get(i).get(4)+"</td>"
								+ "<td style='text-align:center;'>"+lista.get(i).get(5)+"</td>"
								+ "<td style='text-align:right;'>"+lista.get(i).get(6)+"</td>"
								+ "<td style='text-align:right;'>"+lista.get(i).get(7)+"</td>"
								+ "<td style='text-align:center;'>"+lista.get(i).get(8)+"</td>"
								+ "<td style='text-align:center;'>"+lista.get(i).get(9)+"</td>"
							+"</TR>";
				}
				vista += "</tbody></table>";
		return (vista);
	}
	
	public static List<List<String>> listHistorialDespPorIdEquipOrigen(Connection con,String db, Long id_ot, Long id_equipoOrigen) {
		List<List<String>> lista = new ArrayList<List<String>>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select "
							+ " otDespachado.id_ot, "
							+ " otDespachado.id_equipoOrigen,"
							+ " otDespachado.id_equipoDespacho,"
							+ " equipo.codigo, "
							+ " equipo.nombre, "
							+ " unidad.nombre,"
							+ " sum(otDespachado.cantidadDespacho),"
							+ " sum(otDespachado.cantidadRebajaOt),"
							+ " guia.fecha,"
							+ " guia.numero,"
							+ " grupo.nombre "
							+ " from `"+db+"`.otDespachado "
							+ " left join `"+db+"`.equipo on equipo.id = otDespachado.id_equipoDespacho "
							+ " left join `"+db+"`.unidad on unidad.id = equipo.id_unidad "
							+ " left join `"+db+"`.guia on guia.id = otDespachado.id_guia "
							+ " left join `"+db+"`.grupo on grupo.id = equipo.id_grupo "
							+ " where otDespachado.id_ot = ? and otDespachado.id_equipoOrigen = ? "
							+ " group by otDespachado.id_equipoOrigen, otDespachado.id_equipoDespacho, otDespachado.id_guia"
							+ " order by guia.fecha desc, guia.numero desc;");
			smt.setLong(1, id_ot);
			smt.setLong(2, id_equipoOrigen);
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				List<String> aux = new ArrayList<String>();
				aux.add(rs.getString(1)); 							//0 idOt
				aux.add(rs.getString(2));						 	//1 idEquipoOrigen
				aux.add(rs.getString(3)); 							//2 idEquipoDespacho
				aux.add(rs.getString(4)); 							//3 codigo
				aux.add(rs.getString(5)); 							//4 nombre
				aux.add(rs.getString(6)); 							//5 unidad
				aux.add(myformatdouble.format(rs.getDouble(7))); 	//6 cantidadDespacho
				aux.add(myformatdouble.format(rs.getDouble(8))); 	//7 cantidadRebajaOt
				aux.add(rs.getString(10)); 						 	//8 numero de la guia
				aux.add(myformatfecha.format(rs.getDate(9))); 	 	//9 fecha de la guia
				aux.add(rs.getString(11));					 	 	//10 grupo
				lista.add(aux);
			}
			rs.close();smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return(lista);
	}
	
	public static List<OtDespachado> all(Connection con, String db) {
		List<OtDespachado> lista = new ArrayList<OtDespachado>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select id,id_ot,id_guia,id_cotizaDetalle, " +
							" id_equipoOrigen,id_equipoDespacho,cantidadDespacho,cantidadRebajaOt, " +
							" id_bodegaOrigen,esVenta, id_cotizacion" +
							" from `"+db+"`.otDespachado order by id_ot;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				lista.add(new OtDespachado(rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getLong(4),
						rs.getLong(5),rs.getLong(6),rs.getDouble(7),rs.getDouble(8),
						rs.getLong(9),rs.getLong(10), rs.getLong(11)));
			}
			rs.close();smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return(lista);
	}
	
	public static List<OtDespachado> allPorIdGuiaIdOt(Connection con, String db, Long id_ot,Long id_guia) {
		List<OtDespachado> lista = new ArrayList<OtDespachado>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select id,id_ot,id_guia,id_cotizaDetalle, " +
							" id_equipoOrigen,id_equipoDespacho,cantidadDespacho,cantidadRebajaOt, " +
							" id_bodegaOrigen,esVenta, id_cotizacion" +
							" from `"+db+"`.otDespachado" +
							" where id_ot=? and id_guia=?;");
			smt.setLong(1, id_ot);
			smt.setLong(2, id_guia);
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				lista.add(new OtDespachado(rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getLong(4),
						rs.getLong(5),rs.getLong(6),rs.getDouble(7),rs.getDouble(8),
						rs.getLong(9),rs.getLong(10), rs.getLong(11)));
			}
			rs.close();smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return(lista);
	}
	
	public static List<List<String>> listEquipoConStock (Connection con, String db, Map<String,String> mapeoPermiso, Long id_bodegaOrigen,
			Map<Long,Grupo> mapGrupo, Map<Long,Equipo> mapEquipo, Map<Long,Cotizacion> mapCotizacion, Map<Long,Double> mapPeso, Map<Long,Double> mapM2){
    	List<List<String>> lista = new ArrayList<List<String>>();
    	Long soloArriendo = (long) 1;
		if(mapeoPermiso.get("parametro.permiteDevolverVentas").equals("1")) {
			soloArriendo = (long) 0;
		}
    	Map<String,Movimiento> map = Inventarios.invPorIdBodegaAgrupado(con, db, id_bodegaOrigen, soloArriendo);

		map.forEach((k,v)->{
			if(v.getCantidad()>0) {
				Equipo equipo = mapEquipo.get(v.getId_equipo());
				if(equipo!=null) {
					String codEquip = "error", nomEquipo = "error", unidEquip = "error";
					Long id_grupo = (long)0;
					if(equipo != null) {
						codEquip = equipo.getCodigo();
						nomEquipo = equipo.getNombre();
						unidEquip = equipo.getUnidad();
						id_grupo = equipo.getId_grupo();
					}
					Grupo grupo = mapGrupo.get(id_grupo);
					String nomGrupo = "sin grupo";
					if(grupo != null) {
						nomGrupo = grupo.getNombre();
					}
					Cotizacion coti = mapCotizacion.get(v.getId_cotizacion());
					Long numCoti = (long) 0; 
					if(coti != null){
						numCoti = coti.getNumero();
					}
					Double peso = mapPeso.get(v.getId_equipo()); 
					if(peso == null){
						peso = (double)0;
					}
					Double m2 = mapM2.get(v.getId_equipo()); 
					if(m2 == null){
						m2 = (double)0;
					}
					List<String> aux = new ArrayList<String>();
					aux.add(v.getId_equipo().toString()); 				// 0 id_equipo
					aux.add(v.getId_cotizacion().toString()); 			// 1 id_cotizacion
					aux.add(nomGrupo); 									// 2 nombre de grupo
					aux.add(numCoti.toString()); 						// 3 numero cotizacion
					aux.add(codEquip); 									// 4 codigo de equipo
					aux.add(nomEquipo); 								// 5 nombre de equipo
					aux.add(myformatdouble2.format(peso)); 				// 6 KG por equipo
					aux.add(myformatdouble2.format(m2)); 				// 7 M2 por equipo
					aux.add(unidEquip);									// 8 unidad
					aux.add(myformatdouble2.format(v.getCantidad()));	// 9 stock disponible
					lista.add(aux);
				}
				
			}
		});
    	return(lista);
    }
	
	public static boolean eliminarDespacho(Connection con, String db, Long id_guia) {
		try {
			PreparedStatement smt = con
					.prepareStatement("select id from `"+db+"`.guia where id = ?;");
			smt.setLong(1, id_guia);
			ResultSet rs = smt.executeQuery();
			if(rs.next()) {
				rs.close();
				smt.close();
				return(false);
			}else {
				PreparedStatement smt2 = con
						.prepareStatement("delete from `"+db+"`.otDespachado where id_guia = ?;");
				smt2.setLong(1, id_guia);
				smt2.executeUpdate();
				smt2.close();
				rs.close();
				smt.close();
				return(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(false);
	}
	
	public static boolean existenDespachosAsociados(Connection con, String db, Long id_ot) {
		try {
			PreparedStatement smt = con
					.prepareStatement("select id from `"+db+"`.otDespachado where id_ot=?;");
			smt.setLong(1, id_ot);
			ResultSet rs = smt.executeQuery();
			if(rs.next()) {
				rs.close();
				smt.close();
				return(true);
			}else {
				rs.close();
				smt.close();
				return(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(false);
	}
	
	public static Map<String,Double> mapAllDespachado(Connection con, String db, String esPorSucursal, String id_sucursal) {
		Map<String,Double> map = new HashMap<String,Double>();
		
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and bodegaEmpresa.id_sucursal = " + id_sucursal;
		}
		
		try {
			PreparedStatement smt = con
					.prepareStatement("select cotizacion.id_bodegaEmpresa, otDespachado.id_equipoOrigen, sum(otDespachado.cantidadRebajaOt), ot.id_otEstado "
							+ " from `"+db+"`.otDespachado "
							+ " left join `"+db+"`.cotizacion on cotizacion.id = otDespachado.id_cotizacion "
							+ " left join `"+db+"`.ot on ot.id = cotizacion.id_ot "
							+ " left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = cotizacion.id_bodegaEmpresa "
							+ " where cotizacion.id_ot > 0 and cotizacion.id_bodegaEmpresa > 0 and ot.id_otEstado = 1 " + condSucursal
							+ " group by cotizacion.id_bodegaEmpresa, otDespachado.id_equipoOrigen;");
			ResultSet rs = smt.executeQuery();
			while(rs.next()) {
				map.put(rs.getString(1)+"_"+rs.getString(2), rs.getDouble(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(map);
	}
	
	public static Map<String,Double> mapAllDespachadoPorOt(Connection con, String db, String esPorSucursal, String id_sucursal) {
		Map<String,Double> map = new HashMap<String,Double>();
		
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and bodegaEmpresa.id_sucursal = " + id_sucursal;
		}
		
		try {
			PreparedStatement smt = con
					.prepareStatement("select cotizacion.id_bodegaEmpresa, otDespachado.id_equipoOrigen, cotizacion.id_ot, sum(otDespachado.cantidadRebajaOt), ot.id_otEstado "
							+ " from `"+db+"`.otDespachado "
							+ " left join `"+db+"`.cotizacion on cotizacion.id = otDespachado.id_cotizacion "
							+ " left join `"+db+"`.ot on ot.id = cotizacion.id_ot "
							+ " left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = cotizacion.id_bodegaEmpresa "
							+ " where cotizacion.id_ot > 0 and cotizacion.id_bodegaEmpresa > 0 and ot.id_otEstado = 1 " + condSucursal
							+ " group by cotizacion.id_bodegaEmpresa, otDespachado.id_equipoOrigen, cotizacion.id_ot;");
			ResultSet rs = smt.executeQuery();
			
			while(rs.next()) {
				map.put(rs.getString(1)+"_"+rs.getString(2)+"_"+rs.getString(3), rs.getDouble(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(map);
	}
	
	public static List<List<String>> listadoDeOT(Connection con, String db, Long id_bodegaOrigen, Long id_equipo) {
		List<List<String>> lista = new ArrayList<List<String>>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select cotizacion.numero, ot.numero, bodegaEmpresa.nombre, equipo.codigo, equipo.nombre "
							+ " from `"+db+"`.cotizaDetalle "
							+ " left join `"+db+"`.cotizacion on cotizacion.id = cotizaDetalle.id_cotizacion "
							+ " left join `"+db+"`.ot on ot.id = cotizacion.id_ot "
							+ " left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = cotizacion.id_bodegaEmpresa "
							+ " left join `"+db+"`.equipo on equipo.id = cotizaDetalle.id_equipo "
							+ " where cotizacion.id_bodegaEmpresa = ? and cotizaDetalle.id_equipo = ?  and ot.id_otEstado = 1 ;");
			smt.setLong(1, id_bodegaOrigen);
			smt.setLong(2, id_equipo);
			ResultSet rs = smt.executeQuery();
			while(rs.next()) {
				List<String> aux = new ArrayList<String>();
				aux.add(rs.getString(1));
				aux.add(rs.getString(2));
				aux.add(rs.getString(3));
				aux.add(rs.getString(4));
				aux.add(rs.getString(5));
				lista.add(aux);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(lista);
	}
	
	

	

}
