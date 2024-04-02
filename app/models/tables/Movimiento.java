package models.tables;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.TempFile;

import controllers.HomeController;
import models.calculo.Inventarios;
import models.utilities.Archivos;
import models.utilities.Fechas;




public class Movimiento {
	public Long id;
	public Long id_compra;
	public Long id_bodegaOrigen;
	public Long id_bodegaEmpresa;
	public Long id_equipo;
	public Long id_tipoMovimiento;
	public Long id_guia;
	public Double cantidad;
	public Long id_baja;
	public Long bloqueoPorBaja;
	public Double exceso;
	public Long esNuevo;
	public Long esVenta;
	public Long id_otDespachado;
	public Long id_cotizacion;
	public Double cantCliente;
	
	public Long id_factura;
	public String fecha_factura;
	public Long id_actaBaja;
	public String fecha_actaBaja;
	
	public Movimiento(Long id, Long id_compra, Long id_bodegaOrigen, Long id_bodegaEmpresa, Long id_equipo,
			Long id_tipoMovimiento, Long id_guia, Double cantidad, Long id_baja, Long bloqueoPorBaja, Double exceso,
			Long esNuevo, Long esVenta, Long id_otDespachado, Long id_cotizacion, Double cantCliente, Long id_factura,
			String fecha_factura, Long id_actaBaja, String fecha_actaBaja) {
		super();
		this.id = id;
		this.id_compra = id_compra;
		this.id_bodegaOrigen = id_bodegaOrigen;
		this.id_bodegaEmpresa = id_bodegaEmpresa;
		this.id_equipo = id_equipo;
		this.id_tipoMovimiento = id_tipoMovimiento;
		this.id_guia = id_guia;
		this.cantidad = cantidad;
		this.id_baja = id_baja;
		this.bloqueoPorBaja = bloqueoPorBaja;
		this.exceso = exceso;
		this.esNuevo = esNuevo;
		this.esVenta = esVenta;
		this.id_otDespachado = id_otDespachado;
		this.id_cotizacion = id_cotizacion;
		this.cantCliente = cantCliente;
		this.id_factura = id_factura;
		this.fecha_factura = fecha_factura;
		this.id_actaBaja = id_actaBaja;
		this.fecha_actaBaja = fecha_actaBaja;
	}

	public Movimiento() {super();}

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public Long getId_compra() {return id_compra;}
	public void setId_compra(Long id_compra) {this.id_compra = id_compra;}
	public Long getId_bodegaOrigen() {return id_bodegaOrigen;}
	public void setId_bodegaOrigen(Long id_bodegaOrigen) {this.id_bodegaOrigen = id_bodegaOrigen;}
	public Long getId_bodegaEmpresa() {return id_bodegaEmpresa;}
	public void setId_bodegaEmpresa(Long id_bodegaEmpresa) {this.id_bodegaEmpresa = id_bodegaEmpresa;}
	public Long getId_equipo() {return id_equipo;}
	public void setId_equipo(Long id_equipo) {this.id_equipo = id_equipo;}
	public Long getId_tipoMovimiento() {return id_tipoMovimiento;}
	public void setId_tipoMovimiento(Long id_tipoMovimiento) {this.id_tipoMovimiento = id_tipoMovimiento;}
	public Long getId_guia() {return id_guia;}
	public void setId_guia(Long id_guia) {this.id_guia = id_guia;}
	public Double getCantidad() {return cantidad;}
	public void setCantidad(Double cantidad) {this.cantidad = cantidad;}
	public Long getId_baja() {return id_baja;}
	public void setId_baja(Long id_baja) {this.id_baja = id_baja;}
	public Long getBloqueoPorBaja() {return bloqueoPorBaja;}
	public void setBloqueoPorBaja(Long bloqueoPorBaja) {this.bloqueoPorBaja = bloqueoPorBaja;}
	public Double getExceso() {return exceso;}
	public void setExceso(Double exceso) {this.exceso = exceso;}
	public Long getEsNuevo() {return esNuevo;}
	public void setEsNuevo(Long esNuevo) {this.esNuevo = esNuevo;}
	public Long getEsVenta() {return esVenta;}
	public void setEsVenta(Long esVenta) {this.esVenta = esVenta;}
	public Long getId_otDespachado() {return id_otDespachado;}
	public void setId_otDespachado(Long id_otDespachado) {this.id_otDespachado = id_otDespachado;}
	public Long getId_cotizacion() {return id_cotizacion;}
	public void setId_cotizacion(Long id_cotizacion) {this.id_cotizacion = id_cotizacion;}
	public Double getCantCliente() {return cantCliente;}
	public void setCantCliente(Double cantCliente) {this.cantCliente = cantCliente;}
	public Long getId_factura() {return id_factura;}
	public void setId_factura(Long id_factura) {this.id_factura = id_factura;}
	public String getFecha_factura() {return fecha_factura;}
	public void setFecha_factura(String fecha_factura) {this.fecha_factura = fecha_factura;}
	public Long getId_actaBaja() {return id_actaBaja;}
	public void setId_actaBaja(Long id_actaBaja) {this.id_actaBaja = id_actaBaja;}
	public String getFecha_actaBaja() {return fecha_actaBaja;}
	public void setFecha_actaBaja(String fecha_actaBaja) {this.fecha_actaBaja = fecha_actaBaja;}


	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	
	
	public static List<Movimiento> all(Connection con, String db) {
		List<Movimiento> lista = new ArrayList<Movimiento>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" movimiento.id, " +
							" movimiento.id_compra, " +
							" movimiento.id_bodegaOrigen, " +
							" movimiento.id_bodegaEmpresa, " +
							" movimiento.id_equipo, " +
							" movimiento.id_tipoMovimiento, " +
							" movimiento.id_guia, " +
							" movimiento.cantidad, " +
							" movimiento.id_baja, " +
							" movimiento.bloqueoPorBaja, " +
							" movimiento.exceso, " +
							" movimiento.esNuevo, " +
							" movimiento.esVenta, " +
							" movimiento.id_otDespachado, " +
							" movimiento.id_cotizacion, " +
							" movimiento.cantCliente, " +
							" movimiento.id_factura, " +
							" movimiento.fecha_factura, " +
							" movimiento.id_actaBaja, " +
							" movimiento.fecha_actaBaja " +
							" from `"+db+"`.movimiento;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				lista.add(new Movimiento(rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getLong(4),
						rs.getLong(5),rs.getLong(6),rs.getLong(7),rs.getDouble(8),rs.getLong(9),
						rs.getLong(10),rs.getDouble(11),rs.getLong(12),rs.getLong(13),rs.getLong(14),rs.getLong(15),
						rs.getDouble(16),rs.getLong(17),rs.getString(18),rs.getLong(19),rs.getString(20)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<Movimiento> allPorIdBodega(Connection con, String db, Long id_bodegaEmpresa) {
		List<Movimiento> lista = new ArrayList<Movimiento>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" movimiento.id, " +
							" movimiento.id_compra, " +
							" movimiento.id_bodegaOrigen, " +
							" movimiento.id_bodegaEmpresa, " +
							" movimiento.id_equipo, " +
							" movimiento.id_tipoMovimiento, " +
							" movimiento.id_guia, " +
							" movimiento.cantidad, " +
							" movimiento.id_baja, " +
							" movimiento.bloqueoPorBaja, " +
							" movimiento.exceso, " +
							" movimiento.esNuevo, " +
							" movimiento.esVenta, " +
							" movimiento.id_otDespachado, " +
							" movimiento.id_cotizacion, " +
							" movimiento.cantCliente, " +
							" movimiento.id_factura, " +
							" movimiento.fecha_factura, " +
							" movimiento.id_actaBaja, " +
							" movimiento.fecha_actaBaja " +
							" from `"+db+"`.movimiento " +
							" where id_bodegaEmpresa = ?;");
			smt.setLong(1, id_bodegaEmpresa);

			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				lista.add(new Movimiento(rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getLong(4),
						rs.getLong(5),rs.getLong(6),rs.getLong(7),rs.getDouble(8),rs.getLong(9),
						rs.getLong(10),rs.getDouble(11),rs.getLong(12),rs.getLong(13),rs.getLong(14),rs.getLong(15),
						rs.getDouble(16),rs.getLong(17),rs.getString(18),rs.getLong(19),rs.getString(20)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<Movimiento> allPorIdEquipo(Connection con, String db, Long id_equipo) {
		List<Movimiento> lista = new ArrayList<Movimiento>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" movimiento.id, " +
							" movimiento.id_compra, " +
							" movimiento.id_bodegaOrigen, " +
							" movimiento.id_bodegaEmpresa, " +
							" movimiento.id_equipo, " +
							" movimiento.id_tipoMovimiento, " +
							" movimiento.id_guia, " +
							" movimiento.cantidad, " +
							" movimiento.id_baja, " +
							" movimiento.bloqueoPorBaja, " +
							" movimiento.exceso, " +
							" movimiento.esNuevo, " +
							" movimiento.esVenta, " +
							" movimiento.id_otDespachado, " +
							" movimiento.id_cotizacion, " +
							" movimiento.cantCliente, " +
							" movimiento.id_factura, " +
							" movimiento.fecha_factura, " +
							" movimiento.id_actaBaja, " +
							" movimiento.fecha_actaBaja " +
							" from `"+db+"`.movimiento " +
							" where id_equipo = ?;");
			smt.setLong(1, id_equipo);
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				lista.add(new Movimiento(rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getLong(4),
						rs.getLong(5),rs.getLong(6),rs.getLong(7),rs.getDouble(8),rs.getLong(9),
						rs.getLong(10),rs.getDouble(11),rs.getLong(12),rs.getLong(13),rs.getLong(14),rs.getLong(15),
						rs.getDouble(16),rs.getLong(17),rs.getString(18),rs.getLong(19),rs.getString(20)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static boolean existeMovAsosiado_a_Coti(Connection con, String db, Long id_cotizacion) {
		boolean flag =false;
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" movimiento.id " +
							" from `"+db+"`.movimiento " +
							" where id_cotizacion = ?;");
			smt.setLong(1, id_cotizacion);
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
	public static Movimiento find(Connection con, String db, Long id_movimiento) {
		Movimiento aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" movimiento.id, " +
							" movimiento.id_compra, " +
							" movimiento.id_bodegaOrigen, " +
							" movimiento.id_bodegaEmpresa, " +
							" movimiento.id_equipo, " +
							" movimiento.id_tipoMovimiento, " +
							" movimiento.id_guia, " +
							" movimiento.cantidad, " +
							" movimiento.id_baja, " +
							" movimiento.bloqueoPorBaja, " +
							" movimiento.exceso, " +
							" movimiento.esNuevo, " +
							" movimiento.esVenta, " +
							" movimiento.id_otDespachado, " +
							" movimiento.id_cotizacion, " +
							" movimiento.cantCliente, " +
							" movimiento.id_factura, " +
							" movimiento.fecha_factura, " +
							" movimiento.id_actaBaja, " +
							" movimiento.fecha_actaBaja " +
							" from `"+db+"`.movimiento " +
							" where id = ?;");
			smt.setLong(1, id_movimiento);
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				aux = new Movimiento(rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getLong(4),
						rs.getLong(5),rs.getLong(6),rs.getLong(7),rs.getDouble(8),rs.getLong(9),
						rs.getLong(10),rs.getDouble(11),rs.getLong(12),rs.getLong(13),rs.getLong(14),rs.getLong(15),
						rs.getDouble(16),rs.getLong(17),rs.getString(18),rs.getLong(19),rs.getString(20));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (aux);
	}
	
	public static List<List<List<String>>> detalleMovimientoParaModificar(Connection con, String db, Long id_guia, List<List<String>> detalleGuia, List<List<String>> listEquipBodOrigen){
		
		Map<Long,String> strEstados = EstadoEquipo.mapStrEstadoEquipo(con, db, id_guia);
		Map<Long,String> strReparaciones = ReparacionEquipo.mapStrReparacionEquipo(con, db, id_guia);
		
		List<List<String>> detalleGuiaFinal = new ArrayList<List<String>>();
		
		Map<String,List<String>> mapDetalleGuia = new HashMap<String,List<String>>();
		detalleGuia.forEach(x->{
			mapDetalleGuia.put(x.get(2)+"_"+x.get(23), x); // map idEquipo_idCotizacion = List<String> detalleGuia
		});
		
		List<List<String>> equiposSinSaldo = new ArrayList<List<String>>();
		
		for(int i=0; i<listEquipBodOrigen.size(); i++) {
			List<String> aux = new ArrayList<String>();
			aux.add(listEquipBodOrigen.get(i).get(0)); // 0 id_equipo
			aux.add(listEquipBodOrigen.get(i).get(1)); // 1 id_cotizacion
			aux.add(listEquipBodOrigen.get(i).get(2)); // 2 nombre de grupo
			aux.add(listEquipBodOrigen.get(i).get(3)); // 3 numero cotizacion
			aux.add(listEquipBodOrigen.get(i).get(4)); // 4 codigo de equipo
			aux.add(listEquipBodOrigen.get(i).get(5)); // 5 nombre de equipo
			aux.add(listEquipBodOrigen.get(i).get(6)); // 6 KG por equipo
			aux.add(listEquipBodOrigen.get(i).get(7)); // 7 M2 por equipo
			aux.add(listEquipBodOrigen.get(i).get(8)); // 8 unidad
			
			aux.add(listEquipBodOrigen.get(i).get(9)); // 9 stock disponible
			
			
			List<String> auxDet = mapDetalleGuia.get(listEquipBodOrigen.get(i).get(0)+"_"+listEquipBodOrigen.get(i).get(1));
			
			
			if(auxDet != null) {
				
				Long auxDetaux = Long.parseLong(auxDet.get(0).trim());
				String estadosDeEquipos = strEstados.get(auxDetaux);
				if(estadosDeEquipos==null) { 
					estadosDeEquipos = "";
				}
				String reparDeEquipos = strReparaciones.get(Long.parseLong(auxDetaux.toString()));
				if(reparDeEquipos==null) { 
					reparDeEquipos = ""; 
				}
				
				aux.add(auxDet.get(8)); 	// 10 CANTIDAD
				aux.add(auxDet.get(20)); 	// 11 esVenta
				aux.add(auxDet.get(21)); 	// 12 esNuevo
				aux.add(auxDet.get(18)); 	// 13 exceso
				aux.add(auxDet.get(27)); 	// 14 tot KG KG*(cant+exceso)
				aux.add(auxDet.get(28)); 	// 15 tot M2 M2*(cant+exceso)
				aux.add(estadosDeEquipos); 	// 16 estados   	*** string concatena estados
				aux.add(reparDeEquipos); 	// 17 reparaciones 	*** string concatena reparaciones
				
				aux.add(auxDet.get(34)); 	//18 cantidad que dice el cliente
				aux.add(auxDet.get(35)); 	//19 diferencia con cliente
				
				aux.add(listEquipBodOrigen.get(i).get(10)); 	//20 id_ot
				aux.add(listEquipBodOrigen.get(i).get(11)); 	//21 numero de ot 
				aux.add(listEquipBodOrigen.get(i).get(12)); 	//22 fecha de ot
				
			}else {
				aux.add("0.00"); 	// 10 CANTIDAD
				aux.add("0"); 		// 11 esVenta
				aux.add("0"); 		// 12 esNuevo
				aux.add("0.00"); 	// 13 exceso
				aux.add("0.00"); 	// 14 tot KG KG*(cant+exceso)
				aux.add("0.00"); 	// 15 tot M2 M2*(cant+exceso)
				aux.add(""); 		// 16 estados   
				aux.add(""); 		// 17 reparaciones 
				
				aux.add("0.00"); 	//18 cantidad que dice el cliente
				aux.add("0.00"); 	//19 diferencia con cliente
				
				aux.add(listEquipBodOrigen.get(i).get(10)); 	//20 id_ot
				aux.add(listEquipBodOrigen.get(i).get(11)); 	//21 numero de ot 
				aux.add(listEquipBodOrigen.get(i).get(12)); 	//22 fecha de ot
				
			}
			
			if(aux.get(10).equals("0.00") && aux.get(9).equals("0.00") && aux.get(13).equals("0.00")) {
				
				List<String> auxSinSaldo = new ArrayList<String>();
				auxSinSaldo.add(aux.get(0)); 	// 0 id_equipo
				auxSinSaldo.add("0"); 			// 1 id_cotizacion
				auxSinSaldo.add(aux.get(2)); 	// 2 nombre de grupo
				auxSinSaldo.add(""); 			// 3 numero cotizacion
				auxSinSaldo.add(aux.get(4)); 	// 4 codigo de equipo
				auxSinSaldo.add(aux.get(5)); 	// 5 nombre de equipo
				auxSinSaldo.add(aux.get(6)); 	// 6 KG por equipo
				auxSinSaldo.add(aux.get(7)); 	// 7 M2 por equipo
				auxSinSaldo.add(aux.get(8));	// 8 unidad
				auxSinSaldo.add("0.00");		// stock disponible
				
				equiposSinSaldo.add(auxSinSaldo);
				
			}else {
				detalleGuiaFinal.add(aux);
			}
			
			//detalleGuiaFinal.add(aux);
			
		}
		
		Map<String,List<String>> mapListEquipBodOrigen = new HashMap<String,List<String>>();
		listEquipBodOrigen.forEach(x->{
			mapListEquipBodOrigen.put(x.get(0)+"_"+x.get(1), x); // map idEquipo_idCotizacion = List<String> listEquipBodOrigen
		});
		for(int i=0; i<detalleGuia.size(); i++) {
			List<String> auxOrigen = mapListEquipBodOrigen.get(detalleGuia.get(i).get(2)+"_"+detalleGuia.get(i).get(23));
			if(auxOrigen == null) {
				String estadosDeEquipos = strEstados.get(Long.parseLong(detalleGuia.get(i).get(0).trim()));
				if(estadosDeEquipos==null) { estadosDeEquipos = ""; }
				String reparDeEquipos = strReparaciones.get(Long.parseLong(detalleGuia.get(i).get(0).trim()));
				if(reparDeEquipos==null) { reparDeEquipos = ""; }
				List<String> aux = new ArrayList<String>();
				aux.add(detalleGuia.get(i).get(2)); 	// 0 id_equipo
				aux.add(detalleGuia.get(i).get(23)); 	// 1 id_cotizacion
				aux.add(detalleGuia.get(i).get(4)); 	// 2 nombre de grupo
				aux.add(detalleGuia.get(i).get(24)); 	// 3 numero cotizacion
				aux.add(detalleGuia.get(i).get(5)); 	// 4 codigo de equipo
				aux.add(detalleGuia.get(i).get(6)); 	// 5 nombre de equipo
				aux.add(detalleGuia.get(i).get(25)); 	// 6 KG por equipo
				aux.add(detalleGuia.get(i).get(26)); 	// 7 M2 por equipo
				aux.add(detalleGuia.get(i).get(12)); 	// 8 unidad
				
				
				aux.add(detalleGuia.get(i).get(8)); 	// 9 stock disponible
				
				
				aux.add(detalleGuia.get(i).get(8)); 	// 10 CANTIDAD
				aux.add(detalleGuia.get(i).get(20)); 	// 11 esVenta
				aux.add(detalleGuia.get(i).get(21)); 	// 12 esNuevo
				aux.add(detalleGuia.get(i).get(18)); 	// 13 exceso
				aux.add(detalleGuia.get(i).get(27)); 	// 14 tot KG KG*(cant+exceso)
				aux.add(detalleGuia.get(i).get(28)); 	// 15 tot M2 M2*(cant+exceso)
				aux.add(estadosDeEquipos); 				// 16 estados   	*** string concatena estados
				aux.add(reparDeEquipos); 				// 17 reparaciones 	*** string concatena reparaciones
				
				aux.add(detalleGuia.get(i).get(34)); 	//18 cantidad que dice el cliente
				aux.add(detalleGuia.get(i).get(35)); 	//19 diferencia con cliente
				detalleGuiaFinal.add(aux);
			}
		}
		
		List<List<List<String>>> detalleMasSinSaldo = new ArrayList<List<List<String>>>();
		detalleMasSinSaldo.add(detalleGuiaFinal);
		detalleMasSinSaldo.add(equiposSinSaldo);
		return(detalleMasSinSaldo);
	}
	
	public static boolean esPosibleEliminar(Connection con, String db, Long id_guia) {
		boolean flag  = true;
		Guia guia = Guia.find(con, db, id_guia);
		BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, db , guia.id_bodegaOrigen);
		if(bodega.esInterna == 1) {
			try {
				
				PreparedStatement smt = con
						.prepareStatement(" select movimiento.id_equipo, movimiento.id_bodegaEmpresa, id_cotizacion, bodegaEmpresa.esInterna, sum(cantidad) " + 
								" from `"+db+"`.movimiento " + 
								" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = id_bodegaEmpresa " + 
								" where movimiento.id_guia = ? and movimiento.id_tipoMovimiento = 1 " + 
								" group by movimiento.id_equipo, id_cotizacion;");
				smt.setLong(1, id_guia);
				ResultSet rs = smt.executeQuery();
				List<List<String>> listaGuia = new ArrayList<List<String>>();
				Long id_bodegaEmpresa = (long)0;
				while (rs.next()) {
					List<String> aux = new ArrayList<String>();
					aux.add(rs.getString(1)+"_"+rs.getString(3));   // 0 id_equipo-id_cotizacion
					aux.add(rs.getString(5));						// 1 cantidad
					listaGuia.add(aux);
					id_bodegaEmpresa = rs.getLong(2);
				}
				rs.close();
				smt.close();
				
				PreparedStatement smt2 = con
						.prepareStatement(" select movimiento.id_equipo, movimiento.id_bodegaEmpresa, id_cotizacion,  bodegaEmpresa.esInterna, " + 
								" sum(cantidad*if(movimiento.id_tipoMovimiento = 1,1,-1)) " + 
								" from `"+db+"`.movimiento " + 
								" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = id_bodegaEmpresa " + 
								" where id_bodegaEmpresa = ? " + 
								" group by movimiento.id_equipo, id_cotizacion;");
				smt2.setLong(1, id_bodegaEmpresa);
				ResultSet rs2 = smt2.executeQuery();
				Map<String,Double> mapStock = new HashMap<String,Double>();
				while (rs2.next()) {
					//id_equipo-id_cotizacion = cantidad
					mapStock.put(rs2.getString(1)+"_"+rs2.getString(3), rs2.getDouble(5));
				}
				rs2.close();
				smt2.close();
				
				for(int i=0; i< listaGuia.size(); i++) {
					Double valor = Double.parseDouble(listaGuia.get(i).get(1).trim());
					Double dePaso = mapStock.get(listaGuia.get(i).get(0));
					if(dePaso != null && (double) valor > (double) dePaso) {
						flag = false;
					}
				}
			} catch (SQLException e) {
	    			e.printStackTrace();
			}
		}
		return (flag);
	}
	
	public static boolean delete(Connection con, String db, Long id_guia) {
		Boolean flag = false;
		Guia guia = Guia.find(con, db, id_guia);
		try {
			if(guia.numero!=null && (long)guia.numero>(long)0) {
				PreparedStatement smt5 = con
						.prepareStatement("delete from `"+db+"`.estadoEquipo where id_guia = ?;");
				smt5.setLong(1, id_guia);
				smt5.executeUpdate();
				smt5.close();
				
				PreparedStatement smt6 = con
						.prepareStatement("delete from `"+db+"`.reparacionEquipo where id_guia = ?;");
				smt6.setLong(1, id_guia);
				smt6.executeUpdate();
				smt6.close();
				
				PreparedStatement smt4 = con
						.prepareStatement(" delete from `"+db+"`.movimiento where id_guia = ? "
								+ " or id_guia in (select id from `"+db+"`.guia where numGuiaCliente = ?);");
				smt4.setLong(1, id_guia);
				smt4.setString(2, "from_"+guia.getNumero()); // elimina los movimientos autogenerados a traves de estados de equipo
				smt4.executeUpdate();
				smt4.close();
				
				PreparedStatement smt3 = con
						.prepareStatement(" delete from `"+db+"`.guia where id = ? or numGuiaCliente = ?;");
				smt3.setLong(1, id_guia);
				smt3.setString(2, "from_"+guia.getNumero()); // elimina guias autogeneradas a traves de estados de equipo
				smt3.executeUpdate();
				smt3.close();
			}else {
				PreparedStatement smt4 = con
						.prepareStatement(" delete from `"+db+"`.movimiento where id_guia = ?;");
				smt4.setLong(1, id_guia);
				smt4.executeUpdate();
				smt4.close();
				
				PreparedStatement smt3 = con
						.prepareStatement(" delete from `"+db+"`.guia where id = ?;");
				smt3.setLong(1, id_guia);
				smt3.executeUpdate();
				smt3.close();
			}
			
			flag = true;
		} catch (SQLException e) {
				e.printStackTrace();
			flag=false;
		}
		return (flag);
	}
	
	public static String listaIdEliminados(Connection con, String db, Long id_guia) {
		List<String> lista = new ArrayList<String>();
		Guia guia = Guia.find(con, db, id_guia);
		try {
			if((long)guia.numero>(long)0) {
				PreparedStatement smt4 = con
						.prepareStatement(" select id from `"+db+"`.movimiento where id_guia = ? "
								+ " or id_guia in (select id from `"+db+"`.guia where numGuiaCliente = ?);");
				smt4.setLong(1, id_guia);
				smt4.setString(2, "from_"+guia.getNumero()); // son los movimientos autogenerados a traves de estados de equipo
				ResultSet rs = smt4.executeQuery();
				while(rs.next()) {
					lista.add(rs.getString(1));
				}
				rs.close();
				smt4.close();
			}else {
				PreparedStatement smt4 = con
						.prepareStatement(" delete from `"+db+"`.movimiento where id_guia = ?;");
				smt4.setLong(1, id_guia);
				ResultSet rs = smt4.executeQuery();
				while(rs.next()) {
					lista.add(rs.getString(1));
				}
				rs.close();
				smt4.close();
			}
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista.toString());
	}
	
	
	public static boolean createMovimientoCompra(Connection con, String db, String insertMovimiento) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("INSERT INTO `"+db+"`.movimiento (id_bodegaEmpresa,id_equipo,id_tipoMovimiento,cantidad,id_compra,id_factura,fecha_factura) " +
							" VALUES " + insertMovimiento +";");
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean createMovimientoBaja(Connection con, String db, String insertMovimiento) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("INSERT INTO `"+db+"`.movimiento (id_bodegaEmpresa,id_equipo,id_tipoMovimiento,cantidad,id_baja,id_actaBaja,fecha_actaBaja) " +
							" VALUES "+insertMovimiento+";");
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static Fechas findMinFechaGuiaPorBod(Connection con,String db, Long id_bodegaEmpresa) {
		Fechas fecha = Fechas.hoy();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select "
							+ " min(guia.fecha) "
							+ " from `"+db+"`.movimiento "
							+ " left join `"+db+"`.guia on guia.id = movimiento.id_guia "
							+ " where id_guia > 0 and id_bodegaEmpresa=? "
							+ " group by movimiento.id_bodegaEmpresa;");
			smt.setLong(1, id_bodegaEmpresa);
			ResultSet rs = smt.executeQuery();
			if(rs.next()) {
				fecha = Fechas.obtenerFechaDesdeStrAAMMDD(rs.getString(1));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (fecha);
	}
	
	public static Map<Long,String> bloqueoPorBajaPorEmpresa(Connection con, String db, Long id_bodegaEmpresa) {
		Map<Long,String> aux = new HashMap<Long,String>();
		try {
			PreparedStatement smt2 = con
					.prepareStatement("select " +
							" movimiento.id, movimiento.bloqueoPorBaja " +
							" from `"+db+"`.movimiento WHERE id_bodegaEmpresa = ?;" );
			smt2.setLong(1, id_bodegaEmpresa);
			ResultSet rs2 = smt2.executeQuery();
			while (rs2.next()) {
				aux.put(rs2.getLong(1), rs2.getString(2));
			}
			rs2.close();
			smt2.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (aux);
	}
	
	public static Map<Long,Long> mapAllIdBodEnMov(Connection con, String db) {
		Map<Long,Long> map = new HashMap<Long,Long>();
		
		try {
			PreparedStatement smt = con
					.prepareStatement("select  "
							+ " movimiento.id_bodegaEmpresa"
							+ " from `"+db+"`.movimiento "
							+ " group by movimiento.id_bodegaEmpresa;");
			ResultSet rs = smt.executeQuery();
			while(rs.next()) {
				map.put(rs.getLong(1), rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(map);
	}
	
	public static Map<String,Double> mapAllStockBodInt(Connection con, String db, String esPorSucursal, String id_sucursal) {
		Map<String,Double> map = new HashMap<String,Double>();
		
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and bodegaEmpresa.id_sucursal = " + id_sucursal;
		}
		
		try {
			PreparedStatement smt = con
					.prepareStatement("select  "
							+ " movimiento.id_bodegaEmpresa, movimiento.id_equipo, "
							+ " if(sum(if(movimiento.id_tipoMovimiento=1,1,-1)*movimiento.cantidad) < 0.001,0,sum(if(movimiento.id_tipoMovimiento=1,1,-1)*movimiento.cantidad)) "
							+ " from `"+db+"`.movimiento "
							+ " where movimiento.id_bodegaEmpresa in (select id from `"+db+"`.bodegaEmpresa where vigente=1 and bodegaEmpresa.esInterna=1 "+condSucursal+") "
							+ " group by movimiento.id_bodegaEmpresa, movimiento.id_equipo "
							+ " having if(sum(if(movimiento.id_tipoMovimiento=2,movimiento.cantidad*-1,movimiento.cantidad))=-0,0,sum(if(movimiento.id_tipoMovimiento=2,movimiento.cantidad*-1,movimiento.cantidad)))>0;");
			ResultSet rs = smt.executeQuery();
			while(rs.next()) {
				map.put(rs.getString(1)+"_"+rs.getString(2), rs.getDouble(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(map);
	}
	
	public static File plantillaMovimiento(Connection con, String db, String id_tipoUsuario, Long id_bodegaOrigen) {
		File tmp = TempFile.createTempFile("tmp","null");
		Map<String,String> mapeoPermiso = HomeController.mapPermisos(db, id_tipoUsuario);
		BodegaEmpresa bodegaOrigen = BodegaEmpresa.findXIdBodega(con, db, id_bodegaOrigen);
		Long soloArriendo = (long) 1;
		if(mapeoPermiso.get("parametro.permiteDevolverVentas").equals("1")) {
			soloArriendo = (long) 0;
		}
		Map<String,Movimiento> map = Inventarios.invPorIdBodega(con, db, id_bodegaOrigen, soloArriendo);
		Map<Long,Equipo> mapEquipo = Equipo.mapAllVigentes(con, db);
		List<List<String>> listEquipBodOrigen = new ArrayList<List<String>>();
		map.forEach((k,v)->{
			Equipo equipo = mapEquipo.get(v.getId_equipo());
			if(equipo!=null) {
				if(v.getCantidad()>0) {
    				List<String> aux = new ArrayList<String>();
    				aux.add(v.getId_equipo().toString()); 				// 0 id_equipo
    				aux.add(equipo.getCodigo()); 						// 1 codigo de equipo
    				aux.add(equipo.getNombre()); 						// 2 nombre de equipo
    				aux.add(equipo.getUnidad());						// 3 unidad
    				aux.add(myformatdouble2.format(v.getCantidad()));	// 4 stock disponible
    				listEquipBodOrigen.add(aux);
				}
			}
		});
		try {
			InputStream formato = Archivos.leerArchivo("formatos/plantillaMovimiento.xlsx");
            Workbook libro = WorkbookFactory.create(formato);
            formato.close();
            
            CellStyle subtitulo = libro.createCellStyle();
            Font font2 = libro.createFont();
            font2.setBoldweight(Font.BOLDWEIGHT_BOLD);
            font2.setColor((short)0);
            font2.setFontHeight((short)(12*20));
            subtitulo.setFont(font2);
         
            CellStyle detalle = libro.createCellStyle();
            detalle.setBorderBottom(CellStyle.BORDER_THIN);
            detalle.setBorderTop(CellStyle.BORDER_THIN);
            detalle.setBorderRight(CellStyle.BORDER_THIN);
            detalle.setBorderLeft(CellStyle.BORDER_THIN);
            
            Sheet hoja1 = libro.getSheetAt(0);
            Row row = null;
            Cell cell = null;
		
            row = hoja1.getRow(1);
            cell = row.createCell(2);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(bodegaOrigen.getNombre().toUpperCase());
			
			
			//DETALLE DE LA TABLA
			int posRow = 4;
			for(int i=0; i<listEquipBodOrigen.size(); i++){
				row = hoja1.createRow(posRow);
				int posCell = 0;
				Double aux = (double)0;
				
				posCell++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(listEquipBodOrigen.get(i).get(1));
				
				posCell++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(listEquipBodOrigen.get(i).get(2));
				
				posCell++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(listEquipBodOrigen.get(i).get(3));
				
				posCell++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(listEquipBodOrigen.get(i).get(4).replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);
				
				posCell++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue((long)0);
				
				posCell++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue((long)0);
				
				posRow++;
			}
			
			// Write the output to a file tmp
			FileOutputStream fileOut = new FileOutputStream(tmp);
			libro.write(fileOut);
			fileOut.close();
			
		} catch (Exception e) {
			e.printStackTrace();
        }
		return(tmp);
	}
	
	public static List<String> validaPlantillaMovimiento(Connection con, String db, File file) {

		Map<String,Equipo> mapEquipos = Equipo.mapAllVigentesPorCodigo(con, db);
		List<String> mensaje = new ArrayList<String>();
		DecimalFormat df = new DecimalFormat("#");
	    df.setMaximumFractionDigits(8);
	    
       // mensaje.add("");
		
		try {
            Workbook libro = WorkbookFactory.create(file);
            Sheet hoja1 = libro.getSheetAt(0);
            Row row = null;
            Cell cell = null;
            boolean archivoNoCorresponde = false;
            
            //valido titulos y archivo
            if(hoja1 != null) {
            	row = hoja1.getRow(3);
            	if(row != null) {
            		for(int i=1; i<7; i++) {
	            		cell = row.getCell(i);
	                	if(cell == null) {
	                		archivoNoCorresponde = true;
	                	}
	            	}
            	}else {
            		archivoNoCorresponde = true;
            	}
            }else {
            	archivoNoCorresponde = true;
            }
            if(archivoNoCorresponde) {
            	mensaje.set(0,"ARCHIVO NO CORRESPONDE A LA PLANTILLA");
            	return (mensaje);
            }
            // fin
            
            
            // valido datos
            boolean flag = true;
            int fila = 4;
            row = hoja1.getRow(fila);
            if(row != null) {
            	cell = row.getCell(1);
            	if(cell != null) {
            		Long nroFilasExcel = (long) 0;
            		Map<Long,Long> mapValidaRepetido = new HashMap<Long,Long>();
            		while (row != null && cell != null ) {
            			row = hoja1.getRow(fila);
            			if(row != null) {
            				
            				//valido codigos
            				cell = row.getCell(1);
            				if(cell != null) {
            					boolean noEsBlanco = true;
    	                    	try {
    	                    		String dato = cell.getStringCellValue().trim();
    	                    		if(dato.trim().equals("")) {
    	                    			noEsBlanco = false;
    	                    		}
    	                    	}catch(Exception e){
    	                    		Double aux = cell.getNumericCellValue();
    	                    		if(aux.toString().trim().equals("")) {
    	                    			noEsBlanco = false;
    	                    		}
    	                    	}
    	                    	if(noEsBlanco) {
    	                			String dato = "codigo";
    	                			cell = row.getCell(1);
    	                			try {
    	                        		dato = cell.getStringCellValue().trim();
    	                        	}catch(Exception e){
    	                        		Double aux = cell.getNumericCellValue();
    	                        		Long aux2 = aux.longValue();
    	                        		dato = df.format(aux2);
    	                        	}
    	                			Equipo equipo = mapEquipos.get(dato);
    	                			if(equipo == null) {
    	                				mensaje.add("error en fila "+(fila+1)+": Codigo ["+dato+"] no existe en mada o el equipo esta no vigente.");
    	                        		flag = false;
    	                			}else{
    	                				mapValidaRepetido.put(equipo.getId(), equipo.getId());
    	                			}
    	                		}
            				}
            				
            				//valido numeros
            				for(int i=4; i<7; i++) {
            					cell = row.getCell(i);
            					String celda = "";
            					switch(i) {
            					  case 4: celda  = "E" + (fila+1); break;
            					  case 5: celda  = "F" + (fila+1); break;
            					  case 6: celda  = "G" + (fila+1); break;
            					  default:
            					}
            					
            					if(cell != null) {
            						try {
            							Double aux = cell.getNumericCellValue();
            							if(aux < (double)0) {
            								mensaje.add("error en fila "+(fila+1)+": El dato en la celda "+ celda +" no puede ser menor que cero.");
            								flag = false;
            							}else {
            								if(i == 6 && aux > 1) {
            									mensaje.add("error en fila "+(fila+1)+": El dato en la celda "+ celda +" solo puede ser cero o uno.");
	            								flag = false;
            								}
            							}
	    	                    	}catch(Exception e){
	    	                    		mensaje.add("error en fila "+(fila+1)+": El dato en la celda "+ celda +" no es numero.");
    	                        		flag = false;
	    	                    	}
            						
            					}
            				}
            				nroFilasExcel++;
            				fila++;
            			}
            		}
            		if(nroFilasExcel - mapValidaRepetido.size() != (long)0) {
            			mensaje.add("Existen codigos duplicados en el archivo.");
                		flag = false;
            		}
            	}
            }
            if(flag) {
            	mensaje.add("true");
            }
        } catch (Exception e) {
        	e.printStackTrace();
			mensaje.add("ARCHIVO NO CORRESPONDE A LA PLANTILLA");
        	return (mensaje);
        }
		return(mensaje);
	}
	
	public static List<List<String>> llenaListaDesdePlantillaExcel (File file) {
		List<List<String>> lista = new ArrayList<List<String>>();
		DecimalFormat df = new DecimalFormat("#");
	    df.setMaximumFractionDigits(8);
		try {
            Workbook libro = WorkbookFactory.create(file);
            Sheet hoja1 = libro.getSheetAt(0);
            Row row = null;
            Cell cell = null;
            int x = 4;
            row = hoja1.getRow(x);
            cell = row.getCell(1);
            while (row!=null && cell !=null ) {
            	row = hoja1.getRow(x++);
            	if(row!=null) {
            		cell = row.getCell(1);
                	if(cell!=null) {
                		boolean noEsBlanco = true;
                    	try {
                    		String dato = cell.getStringCellValue().trim();
                    		if(dato.trim().equals("")) {
                    			noEsBlanco = false;
                    		}
                    	}catch(Exception e){
                    		Double aux = cell.getNumericCellValue();
                    		if(aux.toString().trim().equals("")) {
                    			noEsBlanco = false;
                    		}
                    	}
                		if(noEsBlanco) {
                			List<String> auxList = new ArrayList<String>();
                			for(int i=1; i<7; i++) {
                    			String dato = "";
                    			cell = row.getCell(i);
                                if(cell!=null) {
                                	try {
                                		dato = cell.getStringCellValue().trim();
                                		dato = dato.replaceAll("'", "\"");
                                	}catch(Exception e){
                                		if(i==1) {
                                			Double aux = cell.getNumericCellValue();
                                    		Long aux2 = aux.longValue();
                                    		dato = df.format(aux2);
                                		}else {
                                			Double aux = cell.getNumericCellValue();
                                    		dato = df.format(aux);
                                		}
                                	}
                                }
                                if(i == 1 || i > 3) {
                                	auxList.add(dato);
                                }
                            }
                			auxList.set(0, auxList.get(0).toUpperCase());
                    		lista.add(auxList);
                		}
                	}
                	cell = row.getCell(1);
            	}
            }
		} catch (InvalidFormatException | IOException e1) {
		}
		return(lista);
	}

	
	
	
	
}
