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

import models.utilities.DecimalFormato;
import models.utilities.Fechas;


public class Ot {
	public Long id;
	public Long id_cotizacion;
	public Long numero;
	public String fecha;
	public String otPDF;
	public Long esEliminable;
	public Long id_otEstado;
	public String fechaConfirmada;
	public Long confirmada;
	public String observaciones;
	
	public Long id_sucursal;
	public Long id_comercial;
	public String nameSucursal;
	public String nameComercial;
	
	public String fechaActualizacion;
	public String fechaEnvio;
	
	public String notaOtEstado;
	
	public Ot(Long id, Long id_cotizacion, Long numero, String fecha,
			String otPDF, Long esEliminable, Long id_otEstado, String fechaConfirmada, Long confirmada,
			String observaciones, Long id_sucursal, Long id_comercial, String nameSucursal, String nameComercial,
			String fechaActualizacion, String fechaEnvio, String notaOtEstado) {
		super();
		this.id = id;
		this.id_cotizacion = id_cotizacion;
		this.numero = numero;
		this.fecha = fecha;
		this.otPDF = otPDF;
		this.esEliminable = esEliminable;
		this.id_otEstado = id_otEstado;
		this.fechaConfirmada = fechaConfirmada;
		this.confirmada = confirmada;
		this.observaciones = observaciones;
		this.id_sucursal = id_sucursal;
		this.id_comercial = id_comercial;
		this.nameSucursal = nameSucursal;
		this.nameComercial = nameComercial;
		this.fechaActualizacion = fechaActualizacion;
		this.fechaEnvio = fechaEnvio;
		this.notaOtEstado = notaOtEstado;
	}
	
	public Ot(){super();}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId_cotizacion() {
		return id_cotizacion;
	}

	public void setId_cotizacion(Long id_cotizacion) {
		this.id_cotizacion = id_cotizacion;
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

	public String getOtPDF() {
		return otPDF;
	}

	public void setOtPDF(String otPDF) {
		this.otPDF = otPDF;
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

	public Long getId_sucursal() {
		return id_sucursal;
	}

	public void setId_sucursal(Long id_sucursal) {
		this.id_sucursal = id_sucursal;
	}

	public String getNameSucursal() {
		return nameSucursal;
	}

	public void setNameSucursal(String nameSucursal) {
		this.nameSucursal = nameSucursal;
	}

	public Long getId_comercial() {
		return id_comercial;
	}

	public void setId_comercial(Long id_comercial) {
		this.id_comercial = id_comercial;
	}

	public String getNameComercial() {
		return nameComercial;
	}

	public void setNameComercial(String nameComercial) {
		this.nameComercial = nameComercial;
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

	public String getNotaOtEstado() {
		return notaOtEstado;
	}

	public void setNotaOtEstado(String notaOtEstado) {
		this.notaOtEstado = notaOtEstado;
	}

	static SimpleDateFormat myformatfecha = new SimpleDateFormat("dd-MM-yyyy");
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	
	
	public static Long anioPrimeraOt(Connection con, String db) {
		Fechas hoy = Fechas.hoy();
		String[] aux = hoy.getFechaStrAAMMDD().split("-");
		Long year = Long.parseLong(aux[0]);
		try {
			PreparedStatement smt = con
					.prepareStatement(" SELECT year(min(fecha)) FROM `"+db+"`.ot;" );
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
	
	
	public static Map<Long,Ot> mapAll(Connection con, String db){
		Map<Long,Ot> map = new HashMap<Long,Ot>();
		List<Ot> lista = Ot.all(con, db);
		lista.forEach(l->{
			map.put(l.getId(), l);
		});
		return(map);
	}
	
	public static List<Long> listOtPorBodega(Connection con, String db, Long id_bodegaEmpresa){
		List<Long> lista = new ArrayList<Long>();
		try {
			PreparedStatement smt = con
				.prepareStatement("select "
						+ " cotizacion.id_ot "
						+ " from `"+db+"`.cotizacion "
						+ " where cotizacion.id_bodegaEmpresa = ? "
						+ " and cotizacion.id_ot > 0 "
						+ " order by id_ot;");
			smt.setLong(1, id_bodegaEmpresa);
			ResultSet rs = smt.executeQuery();
			while(rs.next()) {
				lista.add(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(lista);
	}
	
	public static Map<Long,Long> mapAll_idCoti_vs_idOt(Connection con,String db) {
		Map<Long,Long> map = new HashMap<Long,Long>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select ot.id, ot.id_cotizacion from `"+db+"`.ot;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				map.put(rs.getLong(2), rs.getLong(1));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return(map);
	}
	
	public static Map<Long,List<String>> mapIdvsFechActEnvio(Connection con,String db) {
		Map<Long,List<String>> map = new HashMap<Long,List<String>>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select ot.id, ifnull(ot.fechaActualizacion,''), ifnull(ot.fechaEnvio,'') from `"+db+"`.ot;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				List<String> aux = new ArrayList<String>();
				aux.add(rs.getString(2));
				aux.add(rs.getString(3));
				map.put(rs.getLong(1), aux);
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return(map);
	}
	
	public static Long findNuevoNumero(Connection con, String db){
		Long numeroOt=(long) 1;
		try {
			PreparedStatement smt = con
					.prepareStatement(" SELECT max(numero)+1 from `"+db+"`.ot;");
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
					.prepareStatement(" SELECT numero from `"+db+"`.ot where numero = ?;");
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
	
	public static boolean existeIdCoti(Connection con, String db, Long id_coti){
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement(" SELECT id_cotizacion from `"+db+"`.ot where id_cotizacion = ?;");
			smt.setLong(1, id_coti);
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
	
	public static Ot find(Connection con, String db, Long id_ot) {
		Ot aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" ot.id, ot.id_cotizacion, ot.numero, ot.fecha, ot.otPDF, ot.esEliminable, " +
							" ot.id_otEstado, ifnull(ot.fechaConfirmada,''), ot.confirmada, ifnull(ot.observaciones,''), cotizacion.id_sucursal, cotizacion.id_comercial, " +
							" ifnull(ot.fechaActualizacion,''), ifnull(ot.fechaEnvio,''), ot.notaOtEstado " +
							" from `"+db+"`.ot " +
							" left join `"+db+"`.cotizacion on cotizacion.id = ot.id_cotizacion " +
							" where ot.id = ?;" );
			smt.setLong(1, id_ot);
			ResultSet rs = smt.executeQuery();
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
			if (rs.next()) {
				String nameSucursal = "Sin Sucursal";
				String nameComercial = "Sin Comercial";
				Sucursal sucursal = mapSucursal.get(rs.getLong(11));
				Comercial comercial = mapComercial.get(rs.getLong(12));
				if(sucursal!=null) {
					nameSucursal = sucursal.nombre;
				}
				if(comercial!=null) {
					nameComercial = comercial.nameUsuario;
				}
				aux = new Ot(rs.getLong(1),rs.getLong(2),rs.getLong(3), rs.getString(4), rs.getString(5),
						rs.getLong(6), rs.getLong(7), rs.getString(8), rs.getLong(9),rs.getString(10),rs.getLong(11),rs.getLong(12),
						nameSucursal,nameComercial,rs.getString(13),rs.getString(14),rs.getString(15));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (aux);
	}
	
	public static Ot findPorNumero(Connection con, String db, Long numeroOt) {
		Ot aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" ot.id, ot.id_cotizacion, ot.numero, ot.fecha, ot.otPDF, ot.esEliminable, " +
							" ot.id_otEstado, ifnull(ot.fechaConfirmada,''), ot.confirmada, ifnull(ot.observaciones,''), cotizacion.id_sucursal, cotizacion.id_comercial,  " +
							" ifnull(ot.fechaActualizacion,''), ifnull(ot.fechaEnvio,''), ot.notaOtEstado " +
							" from `"+db+"`.ot " +
							" left join `"+db+"`.cotizacion on cotizacion.id = ot.id_cotizacion " +
							" where ot.numero = ?;" );
			smt.setLong(1, numeroOt);
			ResultSet rs = smt.executeQuery();
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
			if (rs.next()) {
				String nameSucursal = "Sin Sucursal";
				String nameComercial = "Sin Comercial";
				Sucursal sucursal = mapSucursal.get(rs.getLong(11));
				Comercial comercial = mapComercial.get(rs.getLong(12));
				if(sucursal!=null) {
					nameSucursal = sucursal.nombre;
				}
				if(comercial!=null) {
					nameComercial = comercial.nameUsuario;
				}
				aux = new Ot(rs.getLong(1),rs.getLong(2),rs.getLong(3), rs.getString(4), rs.getString(5),
						rs.getLong(6), rs.getLong(7), rs.getString(8), rs.getLong(9),rs.getString(10),rs.getLong(11),rs.getLong(12),
						nameSucursal,nameComercial,rs.getString(13),rs.getString(14),rs.getString(15));
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
					.prepareStatement("UPDATE `"+db+"`.ot SET `"+campo+"` = ? WHERE id = ?;");
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
	
	public static boolean modifyFechaEnvio(Connection con, String db, String fechaActualizacion, String fechaEnvio, Long id_ot) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("UPDATE `"+db+"`.ot SET  fechaActualizacion = ?, fechaEnvio = ? WHERE id = ?;");
			
			if (fechaEnvio != null && !fechaEnvio.equals("")) {
				smt.setString(1, fechaActualizacion);
				smt.setString(2, fechaEnvio);
			} else {
				smt.setNull(1, Types.INTEGER);
				smt.setNull(2, Types.INTEGER);
			}
			
			smt.setLong(3, id_ot);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static List<Ot> allxConfirmar(Connection con,String db) {
		List<Ot> lista = new ArrayList<Ot>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" ot.id, ot.id_cotizacion, ot.numero, ot.fecha, ot.otPDF, ot.esEliminable, " +
							" ot.id_otEstado, ifnull(ot.fechaConfirmada,''), ot.confirmada, ifnull(ot.observaciones,''), cotizacion.id_sucursal, cotizacion.id_comercial,  " +
							" ifnull(ot.fechaActualizacion,''), ifnull(ot.fechaEnvio,''), ot.notaOtEstado " +
							" from `"+db+"`.ot  " +
							" left join `"+db+"`.cotizacion on cotizacion.id = ot.id_cotizacion" +
							" where ot.esEliminable = 1 and ot.confirmada = 0"+
							" order by ot.fecha desc, ot.numero desc;");
			ResultSet rs = smt.executeQuery();
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
			while (rs.next()) {
				String nameSucursal = "Sin Sucursal";
				String nameComercial = "Sin Comercial";
				Sucursal sucursal = mapSucursal.get(rs.getLong(11));
				Comercial comercial = mapComercial.get(rs.getLong(12));
				if(sucursal!=null) {
					nameSucursal = sucursal.nombre;
				}
				if(comercial!=null) {
					nameComercial = comercial.nameUsuario;
				}
				lista.add(new Ot(rs.getLong(1),rs.getLong(2),rs.getLong(3), rs.getString(4), rs.getString(5),
						rs.getLong(6), rs.getLong(7), rs.getString(8), rs.getLong(9),rs.getString(10),rs.getLong(11),rs.getLong(12),
						nameSucursal,nameComercial,rs.getString(13),rs.getString(14),rs.getString(15)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<Ot> allEliminables(Connection con,String db) {
		List<Ot> lista = new ArrayList<Ot>();
		try {
			List<Long> conDespachos = new ArrayList<Long>();
			PreparedStatement smt2 = con
					.prepareStatement(" select " +
							" id_ot " +
							" from `"+db+"`.otDespachado " +
							" group by id_ot;");
			ResultSet rs2 = smt2.executeQuery();
			while (rs2.next()) {
				conDespachos.add(rs2.getLong(1));
			}
			
			String condicion = "";
			if(conDespachos.size()>0) {
				condicion = conDespachos.toString().replace("[", "");
				condicion = condicion.toString().replace("]", "");
				condicion = " where ot.id not in ("+condicion+") ";
			}
			
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" ot.id, ot.id_cotizacion, ot.numero, ot.fecha, ot.otPDF, ot.esEliminable, " +
							" ot.id_otEstado, ifnull(ot.fechaConfirmada,''), ot.confirmada, ifnull(ot.observaciones,''), cotizacion.id_sucursal, cotizacion.id_comercial,  " +
							" ifnull(ot.fechaActualizacion,''), ifnull(ot.fechaEnvio,''), ot.notaOtEstado " +
							" from `"+db+"`.ot  " +
							" left join `"+db+"`.cotizacion on cotizacion.id = ot.id_cotizacion " + condicion +
							" order by ot.fecha desc, ot.numero desc;");
			ResultSet rs = smt.executeQuery();
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
			while (rs.next()) {
				String nameSucursal = "Sin Sucursal";
				String nameComercial = "Sin Comercial";
				Sucursal sucursal = mapSucursal.get(rs.getLong(11));
				Comercial comercial = mapComercial.get(rs.getLong(12));
				if(sucursal!=null) {
					nameSucursal = sucursal.nombre;
				}
				if(comercial!=null) {
					nameComercial = comercial.nameUsuario;
				}
				lista.add(new Ot(rs.getLong(1),rs.getLong(2),rs.getLong(3), rs.getString(4), rs.getString(5),
						rs.getLong(6), rs.getLong(7), rs.getString(8), rs.getLong(9),rs.getString(10),rs.getLong(11),rs.getLong(12),
						nameSucursal,nameComercial,rs.getString(13),rs.getString(14),rs.getString(15)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<Ot> all(Connection con,String db) {
		List<Ot> lista = new ArrayList<Ot>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" ot.id, ot.id_cotizacion, ot.numero, ot.fecha, ot.otPDF, ot.esEliminable, " +
							" ot.id_otEstado, ifnull(ot.fechaConfirmada,''), ot.confirmada, ifnull(ot.observaciones,''), cotizacion.id_sucursal, cotizacion.id_comercial,  " +
							" ifnull(ot.fechaActualizacion,''), ifnull(ot.fechaEnvio,''), ot.notaOtEstado " +
							" from `"+db+"`.ot  " +
							" left join `"+db+"`.cotizacion on cotizacion.id = ot.id_cotizacion " +
							" order by ot.fecha desc, ot.numero desc;");
			ResultSet rs = smt.executeQuery();
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
			while (rs.next()) {
				String nameSucursal = "Sin Sucursal";
				String nameComercial = "Sin Comercial";
				Sucursal sucursal = mapSucursal.get(rs.getLong(11));
				Comercial comercial = mapComercial.get(rs.getLong(12));
				if(sucursal!=null) {
					nameSucursal = sucursal.nombre;
				}
				if(comercial!=null) {
					nameComercial = comercial.nameUsuario;
				}
				lista.add(new Ot(rs.getLong(1),rs.getLong(2),rs.getLong(3), rs.getString(4), rs.getString(5),
						rs.getLong(6), rs.getLong(7), rs.getString(8), rs.getLong(9),rs.getString(10),rs.getLong(11),rs.getLong(12),
						nameSucursal,nameComercial,rs.getString(13),rs.getString(14),rs.getString(15)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<Ot> allDesdeHasta(Connection con,String db, String desde, String hasta) {
		List<Ot> lista = new ArrayList<Ot>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" ot.id, ot.id_cotizacion, ot.numero, ot.fecha, ot.otPDF, ot.esEliminable, " +
							" ot.id_otEstado, ifnull(ot.fechaConfirmada,''), ot.confirmada, ifnull(ot.observaciones,''), cotizacion.id_sucursal, cotizacion.id_comercial,  " +
							" ifnull(ot.fechaActualizacion,''), ifnull(ot.fechaEnvio,''), ot.notaOtEstado " +
							" from `"+db+"`.ot  " +
							" left join `"+db+"`.cotizacion on cotizacion.id = ot.id_cotizacion " +
							" where (ot.fecha between ? and ?) " +
							" order by ot.fecha desc, ot.numero desc;");
			smt.setString(1, desde);
			smt.setString(2, hasta);
			ResultSet rs = smt.executeQuery();
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
			while (rs.next()) {
				String nameSucursal = "Sin Sucursal";
				String nameComercial = "Sin Comercial";
				Sucursal sucursal = mapSucursal.get(rs.getLong(11));
				Comercial comercial = mapComercial.get(rs.getLong(12));
				if(sucursal!=null) {
					nameSucursal = sucursal.nombre;
				}
				if(comercial!=null) {
					nameComercial = comercial.nameUsuario;
				}
				lista.add(new Ot(rs.getLong(1),rs.getLong(2),rs.getLong(3), rs.getString(4), rs.getString(5),
						rs.getLong(6), rs.getLong(7), rs.getString(8), rs.getLong(9),rs.getString(10),rs.getLong(11),rs.getLong(12),
						nameSucursal,nameComercial,rs.getString(13),rs.getString(14),rs.getString(15)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<Ot> allConfirmadas(Connection con,String db) {
		List<Ot> lista = new ArrayList<Ot>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" ot.id, ot.id_cotizacion, ot.numero, ot.fecha, ot.otPDF, ot.esEliminable, " +
							" ot.id_otEstado, ifnull(ot.fechaConfirmada,''), ot.confirmada, ifnull(ot.observaciones,''), cotizacion.id_sucursal, cotizacion.id_comercial,  " +
							" ifnull(ot.fechaActualizacion,''), ifnull(ot.fechaEnvio,''), ot.notaOtEstado " +
							" from `"+db+"`.ot  " +
							" left join `"+db+"`.cotizacion on cotizacion.id = ot.id_cotizacion " +
							" where ot.esEliminable = 0 and ot.confirmada = 1"+
							" order by ot.fecha desc, ot.numero desc;");
			ResultSet rs = smt.executeQuery();
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
			while (rs.next()) {
				String nameSucursal = "Sin Sucursal";
				String nameComercial = "Sin Comercial";
				Sucursal sucursal = mapSucursal.get(rs.getLong(11));
				Comercial comercial = mapComercial.get(rs.getLong(12));
				if(sucursal!=null) {
					nameSucursal = sucursal.nombre;
				}
				if(comercial!=null) {
					nameComercial = comercial.nameUsuario;
				}
				lista.add(new Ot(rs.getLong(1),rs.getLong(2),rs.getLong(3), rs.getString(4), rs.getString(5),
						rs.getLong(6), rs.getLong(7), rs.getString(8), rs.getLong(9),rs.getString(10),rs.getLong(11),rs.getLong(12),
						nameSucursal,nameComercial,rs.getString(13),rs.getString(14),rs.getString(15)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<Ot> allConfirmadasDesdeHasta(Connection con,String db,String desde,String hasta) {
		List<Ot> lista = new ArrayList<Ot>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" ot.id, ot.id_cotizacion, ot.numero, ot.fecha, ot.otPDF, ot.esEliminable, " +
							" ot.id_otEstado, ifnull(ot.fechaConfirmada,''), ot.confirmada, ifnull(ot.observaciones,''), cotizacion.id_sucursal, cotizacion.id_comercial,  " +
							" ifnull(ot.fechaActualizacion,''), ifnull(ot.fechaEnvio,''), ot.notaOtEstado " +
							" from `"+db+"`.ot  " +
							" left join `"+db+"`.cotizacion on cotizacion.id = ot.id_cotizacion " +
							" where ot.esEliminable = 0 and ot.confirmada = 1 "+
							" and (ot.fecha between ? and ?) " +
							" order by ot.fecha desc, ot.numero desc;");
			smt.setString(1, desde);
			smt.setString(2, hasta);
			ResultSet rs = smt.executeQuery();
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
			while (rs.next()) {
				String nameSucursal = "Sin Sucursal";
				String nameComercial = "Sin Comercial";
				Sucursal sucursal = mapSucursal.get(rs.getLong(11));
				Comercial comercial = mapComercial.get(rs.getLong(12));
				if(sucursal!=null) {
					nameSucursal = sucursal.nombre;
				}
				if(comercial!=null) {
					nameComercial = comercial.nameUsuario;
				}
				lista.add(new Ot(rs.getLong(1),rs.getLong(2),rs.getLong(3), rs.getString(4), rs.getString(5),
						rs.getLong(6), rs.getLong(7), rs.getString(8), rs.getLong(9),rs.getString(10),rs.getLong(11),rs.getLong(12),
						nameSucursal,nameComercial,rs.getString(13),rs.getString(14),rs.getString(15)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<Ot> allConfirmadasPorAnio(Connection con,String db, Long year) {
		List<Ot> lista = new ArrayList<Ot>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" ot.id, ot.id_cotizacion, ot.numero, ot.fecha, ot.otPDF, ot.esEliminable, " +
							" ot.id_otEstado, ifnull(ot.fechaConfirmada,''), ot.confirmada, ifnull(ot.observaciones,''), cotizacion.id_sucursal, cotizacion.id_comercial,  " +
							" ifnull(ot.fechaActualizacion,''), ifnull(ot.fechaEnvio,''), ot.notaOtEstado " +
							" from `"+db+"`.ot  " +
							" left join `"+db+"`.cotizacion on cotizacion.id = ot.id_cotizacion " +
							" where ot.esEliminable = 0 and ot.confirmada = 1 and year(ot.fecha) = ?"+
							" order by ot.fecha desc, ot.numero desc;");
			smt.setLong(1, year);
			ResultSet rs = smt.executeQuery();
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
			while (rs.next()) {
				String nameSucursal = "Sin Sucursal";
				String nameComercial = "Sin Comercial";
				Sucursal sucursal = mapSucursal.get(rs.getLong(11));
				Comercial comercial = mapComercial.get(rs.getLong(12));
				if(sucursal!=null) {
					nameSucursal = sucursal.nombre;
				}
				if(comercial!=null) {
					nameComercial = comercial.nameUsuario;
				}
				lista.add(new Ot(rs.getLong(1),rs.getLong(2),rs.getLong(3), rs.getString(4), rs.getString(5),
						rs.getLong(6), rs.getLong(7), rs.getString(8), rs.getLong(9),rs.getString(10),rs.getLong(11),rs.getLong(12),
						nameSucursal,nameComercial,rs.getString(13),rs.getString(14),rs.getString(15)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static String vistaModalVerOt(Connection con, String db, Long id_ot, Map<String,String> mapeoDiccionario){
		String vista="";
		Ot ot = Ot.find(con, db, id_ot);
		Cotizacion coti = Cotizacion.find(con, db, ot.getId_cotizacion());
		List<CotizaDetalle> detalleOrigen = CotizaDetalle.allPorIdCotizacion(con, db, ot.getId_cotizacion());
		List<List<String>> detalleDespacho = OtDespachado.listSumaDespachadoPorIdOtCantDesp(con, db, ot.getId());
		BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, db, coti.getId_bodegaEmpresa());
		vista=vista+
		"<table class='table table-sm table-hover table-bordered table-condensed table-fluid'>"+
			"<tr>"+
				"<td><label>N° "+mapeoDiccionario.get("OT")+": "+ot.numero+"</label></td>"+
				"<td><label>Fecha "+mapeoDiccionario.get("OT")+": "+Fechas.DDMMAA(ot.fecha)+"</label></td>"+
				"<td><label>N° COTI: "+coti.numero+"</label></td>"+
				"<td><label>Fecha COTI: "+Fechas.DDMMAA(coti.fecha)+"</label></td>"+
				
				"<td><label>"+mapeoDiccionario.get("Bodega")+"/Proyecto: "+bodega.getNombre()+"</label></td>"+
				"<td><label>N° OC: "+coti.getNumeroOC()+"</label></td>"+
			"</tr>"+
			"<tr>"+
				"<td colspan='2'><label>"+mapeoDiccionario.get("RUT")+" Cliente: "+bodega.getRutCliente()+"</label></td>"+
				"<td colspan='2'><label>Nombre Cliente: "+bodega.getNickCliente()+"</label></td>"+
				"<td><label>Nombre Proyecto: "+bodega.getNickProyecto()+"</label></td>"+
				"<td>"+
				"</td>"+
			"</tr>"+
			"<tr>"+
				"<td colspan='2'><label>SUCURSAL: "+bodega.getNameSucursal()+"</label></td>"+
				"<td colspan='10' style='text-align:left'>OBS COTI: "+coti.getObservaciones()+"</td>"+
			"</tr>"+
			"<tr>"+
				"<td colspan='2'><label>COMERCIAL: "+bodega.getNameComercial()+"</label></td>"+
				"<td colspan='10' style='text-align:left'>OBS "+mapeoDiccionario.get("OT")+": "+ot.getObservaciones()+"</td>"+
			"</tr>"+
			"<tr>"+
				"<td colspan='2'><label>ULTIMA ACTUALIZACION: "+Fechas.DDMMAA(ot.getFechaActualizacion())+"</label></td>"+
				"<td colspan='10' style='text-align:left'>ESTIMACION DE ENTREGA: "+Fechas.DDMMAA(ot.getFechaEnvio())+"</td>"+
			"</tr>"+
		"</table>"+
		"<table class='table table-sm table-hover table-bordered table-condensed table-fluid'>"+
	        "<thead style='background-color: #eeeeee'>"+
				"<TR> "+
				        "<TH rowspan='2' style= 'text-align: center;' title='Codigo del Equipo'>GRUPO</TH>"+
				        "<TH rowspan='2' style= 'text-align: center;' title='Codigo del Equipo'>COD</TH>"+
						"<TH rowspan='2' style= 'text-align: center;' title='Equipo/Descripcion'>EQUIPO</TH>"+
						"<TH colspan='4' style= 'text-align: center;'>ORDEN ORIGINAL</TH>"+
						"<TH colspan='6' style= 'text-align: center;'>DESPACHADO A LA FECHA</TH>"+
				"</TR>"+
				"<TR> "+
					"<TH style= 'text-align: center;'>UN</TH>"+
					"<TH style= 'text-align: center;'>CANT</TH>"+
					"<TH style= 'text-align: center;'>CONCEPTO</TH>"+
					"<th width='5%' style= 'text-align: center;font-size:10px;'>SALDO</th>"+
					"<TH style= 'text-align: center;'>COD</TH>"+
					"<TH style= 'text-align: center;'>EQUIPO</TH>"+
					"<TH style= 'text-align: center;'>UN</TH>"+
					"<TH style= 'text-align: center;'>CANT</TH>"+
					"<TH width='5%' style= 'text-align: center;font-size:10px;'>EQUIV</TH>"+
				"</TR>"+
			"</thead>"+
			"<tbody>";
		
		Map<Long,Double> mapDespachado = OtDespachado.mapSumaDespachadoPorIdOtCantEquiv(con, db, ot.getId());
		
		Double cantOrigen = (double)0;
		Double cantSaldo = (double)0;
		Double cantDesp = (double)0;
		Double cantEquiv = (double)0;
					
		for(int i=0;i<detalleOrigen.size();i++){
			Double cant = Double.parseDouble(detalleOrigen.get(i).getCantidad().replaceAll(",", ""));
			Double saldoPorDespachar = cant;
			Double desp = mapDespachado.get(detalleOrigen.get(i).getId_equipo());
			if(desp != null) {
				saldoPorDespachar = cant - desp;
				if((double)saldoPorDespachar < (double)0) {
					saldoPorDespachar = (double)0;
				}
			}
			String concepto = mapeoDiccionario.get("Arriendo");
			if((long) detalleOrigen.get(i).esVenta == (long) 1){
				concepto = "Venta";
			}
			
			cantOrigen += cant;
			cantSaldo += saldoPorDespachar;
			
			
			vista += 
			"<TR>"+
				"<td style= 'text-align: left;'>"+detalleOrigen.get(i).getGrupo()+"</td>"+
				"<td style= 'text-align: left;'>"+detalleOrigen.get(i).getCodigo()+"</td>"+
				"<td style= 'text-align: left;'>"+detalleOrigen.get(i).getEquipo()+"</td>"+
				"<td style= 'text-align: center;'>"+detalleOrigen.get(i).getUnidad()+"</td>"+
				"<td style= 'text-align: right;'>"+detalleOrigen.get(i).getCantidad()+"</td>"+
				"<td style= 'text-align: center;'>"+concepto+"</td>"+
				"<td style= 'text-align: right;'>"+myformatdouble2.format(saldoPorDespachar)+"</td>"+
				"<td style= 'text-align: left;' title='Codigo del Equipo'>";
			
				for(int k=0;k<detalleDespacho.size();k++){
					if(detalleOrigen.get(i).getId_equipo().toString().equals(detalleDespacho.get(k).get(1))) {
						vista += detalleDespacho.get(k).get(3)+"<br>";
					}
				}
				vista += "</td>"+
				"<td style= 'text-align: left;' title='Equipo/Descripcion'>";
				for(int k=0;k<detalleDespacho.size();k++){
					if(detalleOrigen.get(i).getId_equipo().toString().equals(detalleDespacho.get(k).get(1))) {
							vista += detalleDespacho.get(k).get(4)+"<br>";
					}
				}
				vista += "</td>"+
				"<td style= 'text-align: center;' title='Unidad'>";
				for(int k=0;k<detalleDespacho.size();k++){
					if(detalleOrigen.get(i).getId_equipo().toString().equals(detalleDespacho.get(k).get(1))) {
						vista += detalleDespacho.get(k).get(5)+"<br>";
					}
				}
				vista += "</td>"+
				"<td style= 'text-align: right;'  title='Cantidad despachada'>";
				for(int k=0;k<detalleDespacho.size();k++){
					if(detalleOrigen.get(i).getId_equipo().toString().equals(detalleDespacho.get(k).get(1))) {
						vista += detalleDespacho.get(k).get(6)+"<br>";
						cantDesp += Double.parseDouble(detalleDespacho.get(k).get(6).replaceAll(",", ""));
					}
				}
				vista += "</td>"+
				"<td style= 'text-align: right;' title='Cantidad equivalente que rebaja la Orden de Servicio'>";
				for(int k=0;k<detalleDespacho.size();k++){
					if(detalleOrigen.get(i).getId_equipo().toString().equals(detalleDespacho.get(k).get(1))) {
						vista += detalleDespacho.get(k).get(7)+"<br>";
						cantEquiv += Double.parseDouble(detalleDespacho.get(k).get(7).replaceAll(",", ""));
					}
				}
				vista += "</td>"+
			  "</TR>";
 			}
			vista=vista+
				"</tbody>"+
				"<tfoot>"+
					"<td></td>"+
					"<td></td>"+
					"<td></td>"+
					"<td></td>"+
					"<td style= 'text-align: right;'>"+myformatdouble2.format(cantOrigen)+"</td>"+
					"<td></td>"+
					"<td style= 'text-align: right;'>"+myformatdouble2.format(cantSaldo)+"</td>"+
					"<td></td>"+
					"<td></td>"+
					"<td></td>"+
					"<td style= 'text-align: right;'>"+myformatdouble2.format(cantDesp)+"</td>"+
					"<td style= 'text-align: right;'>"+myformatdouble2.format(cantEquiv)+"</td>"+
				"</tfoot>"+
				"</table>"+
				"</div>";
		return(vista);
	}
	
	public static String vistaRevisarOt(Connection con, String db, Long id_ot, Map<String,String> mapeoDiccionario){
		String vista="";
		Ot ot = Ot.find(con, db, id_ot);
		Cotizacion coti = Cotizacion.find(con, db, ot.getId_cotizacion());
		List<CotizaDetalle> detalleOrigen = CotizaDetalle.allPorIdCotizacion(con, db, ot.getId_cotizacion());
		List<List<String>> detalleDespacho = OtDespachado.listSumaDespachadoPorIdOtCantDesp(con, db, ot.getId());
		BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, db, coti.getId_bodegaEmpresa());
		vista=vista+
		"<table class='table table-sm table-hover table-bordered table-condensed table-fluid'>"+
			"<tr>"+
				"<td><label>N° "+mapeoDiccionario.get("OT")+": "+ot.numero+"</label></td>"+
				"<td><label>Fecha "+mapeoDiccionario.get("OT")+": "+Fechas.DDMMAA(ot.fecha)+"</label></td>"+
				"<td><label>N° COTI: "+coti.numero+"</label></td>"+
				"<td><label>Fecha COTI: "+Fechas.DDMMAA(coti.fecha)+"</label></td>"+
				
				"<td><label>"+mapeoDiccionario.get("Bodega")+"/Proyecto: "+bodega.getNombre()+"</label></td>"+
				"<td><label>N° OC: "+coti.getNumeroOC()+"</label></td>"+
			"</tr>"+
			"<tr>"+
				"<td colspan='2'><label>"+mapeoDiccionario.get("RUT")+" Cliente: "+bodega.getRutCliente()+"</label></td>"+
				"<td colspan='2'><label>Nombre Cliente: "+bodega.getNickCliente()+"</label></td>"+
				"<td><label>Nombre Proyecto: "+bodega.getNickProyecto()+"</label></td>"+
				"<td>"+
				"</td>"+
			"</tr>"+
			"<tr>"+
				"<td colspan='2'><label>SUCURSAL: "+bodega.getNameSucursal()+"</label></td>"+
				"<td colspan='10' style='text-align:left'>OBS COTI: "+coti.getObservaciones()+"</td>"+
			"</tr>"+
			"<tr>"+
				"<td colspan='2'><label>COMERCIAL: "+bodega.getNameComercial()+"</label></td>"+
				"<td colspan='10' style='text-align:left'>OBS "+mapeoDiccionario.get("OT")+": "+ot.getObservaciones()+"</td>"+
			"</tr>"+
			"<tr>"+
				"<td colspan='2'><label>ULTIMA ACTUALIZACION: "+Fechas.DDMMAA(ot.getFechaActualizacion())+"</label></td>"+
				"<td colspan='10' style='text-align:left'>FECHA DE ENTREGA: "+Fechas.DDMMAA(ot.getFechaEnvio())+"</td>"+
			"</tr>"+
		"</table>"+
		"<table class='table table-sm table-hover table-bordered table-condensed table-fluid'>"+
	        "<thead style='background-color: #eeeeee'>"+
				"<TR> "+
				        "<TH rowspan='2' style= 'text-align: center;' title='Codigo del Equipo'>GRUPO</TH>"+
				        "<TH rowspan='2' style= 'text-align: center;' title='Codigo del Equipo'>COD</TH>"+
						"<TH rowspan='2' style= 'text-align: center;' title='Equipo/Descripcion'>EQUIPO</TH>"+
						"<TH rowspan='2' style= 'text-align: center;' title='Stock disponible'>STOCK</TH>"+
						"<TH colspan='6' style= 'text-align: center;'>ORDEN ORIGINAL</TH>"+
						"<TH colspan='7' style= 'text-align: center;'>DESPACHADO A LA FECHA</TH>"+
				"</TR>"+
				"<TR> "+
					"<TH style= 'text-align: center;' title='Unidad'>UN</TH>"+
					"<TH style= 'text-align: center;' title='Cantidad original'>CANT</TH>"+
					
					"<TH style= 'text-align: center; font-size:10px;' title='Cantidad original'>TOTAL KG</TH>"+
					
					"<TH style= 'text-align: center;' title='Es venta o "+mapeoDiccionario.get("Arriendo")+"'>TIPO</TH>"+
					"<th width='5%'  style= 'text-align: center;font-size:10px;' title='Saldo pendiente de la Orden de Servicio'>SALDO</th>"+
					
					"<th width='5%'  style= 'text-align: center;font-size:10px;' title='Saldo pendiente de la Orden de Servicio'>SALDO KG</th>"+
					
					"<TH style= 'text-align: center;' title='Codigo del Equipo'>COD</TH>"+
					"<TH style= 'text-align: center;' title='Equipo/Descripcion'>EQUIPO</TH>"+
					"<TH style= 'text-align: center;' title='Unidad'>UN</TH>"+
					"<TH style= 'text-align: center;' title='Cantidad despachada'>CANT</TH>"+
					"<TH width='5%'  style= 'text-align: center;font-size:10px;' title='Cantidad equivalente que rebaja la Orden de Servicio'>EQUIV</TH>"+
					"<TH width='5%'  style= 'text-align: center;font-size:10px;'>EQUIV KG</TH>"+
					"<TH style= 'text-align: center;' title='Trazabilidad de los despachos'>HIST</TH>"+
				"</TR>"+
			"</thead>"+
		"<tbody>";
		
		Map<Long,Double> mapDespachado = OtDespachado.mapSumaDespachadoPorIdOtCantEquiv(con, db, ot.getId());
		
		Double sumCantOrigen = (double)0;
		Double sumKgOrigen = (double)0;
		Double sumSalCantOrigen = (double)0;
		Double sumSalKgOrigen = (double)0;
		
		Double sumCantDesp = (double)0;
		Double sumKgDesp = (double)0;
		
		Double sumCantEquiv = (double)0;
					
		for(int i=0;i<detalleOrigen.size();i++){
			
			Double cant = Double.parseDouble(detalleOrigen.get(i).getCantidad().replaceAll(",", ""));
			Double saldoPorDespachar = cant;
			Double desp = mapDespachado.get(detalleOrigen.get(i).getId_equipo());
			if(desp != null) {
				saldoPorDespachar = cant - desp;
				if((double)saldoPorDespachar < (double)0) {
					saldoPorDespachar = (double)0;
				}
			}
			
			String concepto = mapeoDiccionario.get("Arriendo");
			if((long)detalleOrigen.get(i).getEsVenta() == (long)1) {
				concepto = "Venta";
			}
			
			Double auxKg = Double.parseDouble(detalleOrigen.get(i).getTotalKG().trim().replaceAll(",", ""));
			Double auxKgSaldo = auxKg/cant * saldoPorDespachar;
			
			sumCantOrigen += cant;
			sumKgOrigen += auxKg;
			sumSalCantOrigen += saldoPorDespachar;
			sumSalKgOrigen += auxKgSaldo;
			
			vista += 
			"<TR>"+
				"<td style= 'text-align: left;'><div id='codEq_"+detalleOrigen.get(i).getId_equipo()+"'>"+detalleOrigen.get(i).getGrupo()+"</div></td>"+
				"<td style= 'text-align: left;'><div id='codEq_"+detalleOrigen.get(i).getId_equipo()+"'>"+detalleOrigen.get(i).getCodigo()+"</div></td>"+
				"<td style= 'text-align: left;'><div id='nomEq_"+detalleOrigen.get(i).getId_equipo()+"'>"+detalleOrigen.get(i).getEquipo()+"</div></td>"+
				"<td style='text-align:center;'><div class='noprint'>"+
					"<a href='#' onclick='vistaStockPorEquipo("+detalleOrigen.get(i).getId_equipo()+");'>"+
						"<kbd style='background-color: #73C6B6'>stock</kbd>"+
				"</a></div></td>"+
				"<td style= 'text-align: center;'>"+detalleOrigen.get(i).getUnidad()+"</td>"+
				"<td style= 'text-align: right;'>"+detalleOrigen.get(i).getCantidad()+"</td>"+
				"<td style= 'text-align: right;'>"+detalleOrigen.get(i).getTotalKG()+"</td>"+
				"<td style= 'text-align: center;'>"+concepto+"</td>"+
				"<td style= 'text-align: right;'>"+myformatdouble2.format(saldoPorDespachar)+"</td>"+
				"<td style= 'text-align: right;'>"+myformatdouble2.format(auxKgSaldo)+"</td>"+
				"<td style= 'text-align: left;' title='Codigo del Equipo'>";
				
				for(int k=0;k<detalleDespacho.size();k++){
					if(detalleOrigen.get(i).getId_equipo().toString().equals(detalleDespacho.get(k).get(1))) {
						vista += detalleDespacho.get(k).get(3)+"<br>";
					}
				}
				vista += "</td>"+
				"<td style= 'text-align: left;' title='Equipo/Descripcion'>";
				for(int k=0;k<detalleDespacho.size();k++){
					if(detalleOrigen.get(i).getId_equipo().toString().equals(detalleDespacho.get(k).get(1))) {
							vista += detalleDespacho.get(k).get(4)+"<br>";
						}
					}
				vista += "</td>"+
				"<td style= 'text-align: center;' style= 'text-align: center;' title='Unidad'>";
					for(int k=0;k<detalleDespacho.size();k++){
						if(detalleOrigen.get(i).getId_equipo().toString().equals(detalleDespacho.get(k).get(1))) {
							vista += detalleDespacho.get(k).get(5)+"<br>";
						}
					}
				vista += "</td>"+
				"<td style= 'text-align: right;' style= 'text-align: center;' title='Cantidad despachada'>";
					for(int k=0;k<detalleDespacho.size();k++){
						if(detalleOrigen.get(i).getId_equipo().toString().equals(detalleDespacho.get(k).get(1))) {
							Double cantDespachado = Double.parseDouble(detalleDespacho.get(k).get(6).trim().replaceAll(",", ""));
							sumCantDesp += cantDespachado;
							vista += detalleDespacho.get(k).get(6)+"<br>";
						}
					}
				vista += "</td>"+
				"<td style= 'text-align: right;' title='Cantidad equivalente que rebaja la Orden de Servicio'>";
					for(int k=0;k<detalleDespacho.size();k++){
						if(detalleOrigen.get(i).getId_equipo().toString().equals(detalleDespacho.get(k).get(1))) {
							Double cantEquiv = Double.parseDouble(detalleDespacho.get(k).get(7).trim().replaceAll(",", ""));
							sumCantEquiv += cantEquiv;
							vista += detalleDespacho.get(k).get(7)+"<br>";
						}
					}
				vista += "</td>"+
						"<td style= 'text-align: right;'>";
							for(int k=0;k<detalleDespacho.size();k++){
								if(detalleOrigen.get(i).getId_equipo().toString().equals(detalleDespacho.get(k).get(1))) {
									Double kgDespachado = Double.parseDouble(detalleDespacho.get(k).get(8).trim().replaceAll(",", ""));
									sumKgDesp += kgDespachado;
									vista += detalleDespacho.get(k).get(8)+"<br>";
								}
							}
				vista += "</td>"+
				"<td style= 'text-align: center;' title='Trazabilidad de los despachos'>"+
					"<a href='#' onclick='trazaOt("+ot.id+","+detalleOrigen.get(i).getId_equipo()+")'>"+
						"<kbd style='background-color: rgb(90, 200, 245)'>"+
							"<font color='green'>H</font>"+
						"</kbd>"+
					"</a>"+
				"</td>"+
			  "</TR>";
 			}
			vista += "</tbody><tfoot>";
		
			vista += 
		
					"<TR> "+
							"<TH></TH>"+
							"<TH></TH>"+
							"<TH style= 'text-align: left;'>TOTALES</TH>"+
							"<TH></TH>"+
							"<TH></TH>"+
							"<TH style= 'text-align: right;'>"+myformatdouble2.format(sumCantOrigen)+"</TH>"+
							"<TH style= 'text-align: right;'>"+myformatdouble2.format(sumKgOrigen)+"</TH>"+
							
							"<TH></TH>"+
							"<TH style= 'text-align: right;'>"+myformatdouble2.format(sumSalCantOrigen)+"</TH>"+
							"<TH style= 'text-align: right;'>"+myformatdouble2.format(sumSalKgOrigen)+"</TH>"+
												
							"<TH></TH>"+
							"<TH></TH>"+
							"<TH></TH>"+

							"<TH style= 'text-align: right;'>"+myformatdouble2.format(sumCantDesp)+"</TH>"+
							"<TH style= 'text-align: right;'>"+myformatdouble2.format(sumCantEquiv)+"</TH>"+
							"<TH style= 'text-align: right;'>"+myformatdouble2.format(sumKgDesp)+"</TH>"+
							
							"<TH></TH>"+
						"</TR>";
		
		
		
			vista=vista+
				"</tfoot>"+
				"</table>";
		return(vista);
	}
	
	public static String vistaCambiarPreciosOt(Connection con, String db, Long id_ot, Map<String,String> mapeoDiccionario){
		String vista="";
		Ot ot = Ot.find(con, db, id_ot);
		Cotizacion coti = Cotizacion.find(con, db, ot.getId_cotizacion());
		List<CotizaDetalle> detalleOrigen = CotizaDetalle.allPorIdCotizacion(con, db, ot.getId_cotizacion());
		List<List<String>> detalleDespacho = OtDespachado.listSumaDespachadoPorIdOtCantDesp(con, db, ot.getId());
		BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, db, coti.getId_bodegaEmpresa());
		
		Map<String,List<Double>> mapPrecios = ListaPrecio.mapListaPreciosEquiposPorBodega(con, db, bodega.getId());
		Map<Long,Moneda> mapMoneda = Moneda.mapMonedas(con, db);
		Map<Long,UnidadTiempo> mapUnTiempo = UnidadTiempo.mapUnidadTiempo(con, db);
		
		List<Moneda> listMoneda = Moneda.all(con, db);
		List<UnidadTiempo> listUnidadTiempo = UnidadTiempo.all(con, db);
		
		vista=vista+
		"<table class='table table-sm table-hover table-bordered table-condensed table-fluid'>"+
			"<tr>"+
				"<td><label>N° "+mapeoDiccionario.get("OT")+": "+ot.numero+"</label></td>"+
				"<td><label>Fecha "+mapeoDiccionario.get("OT")+": "+Fechas.DDMMAA(ot.fecha)+"</label></td>"+
				"<td><label>N° COTI: "+coti.numero+"</label></td>"+
				"<td><label>Fecha COTI: "+Fechas.DDMMAA(coti.fecha)+"</label></td>"+
				
				"<td><label>"+mapeoDiccionario.get("Bodega")+"/Proyecto: "+bodega.getNombre()+"</label></td>"+
				"<td><label>N° OC: "+coti.getNumeroOC()+"</label></td>"+
			"</tr>"+
			"<tr>"+
				"<td colspan='2'><label>"+mapeoDiccionario.get("RUT")+" Cliente: "+bodega.getRutCliente()+"</label></td>"+
				"<td colspan='2'><label>Nombre Cliente: "+bodega.getNickCliente()+"</label></td>"+
				"<td><label>Nombre Proyecto: "+bodega.getNickProyecto()+"</label></td>"+
				"<td>"+
				"</td>"+
			"</tr>"+
			"<tr>"+
				"<td colspan='2'><label>SUCURSAL: "+bodega.getNameSucursal()+"</label></td>"+
				"<td colspan='10' style='text-align:left'>OBS COTI: "+coti.getObservaciones()+"</td>"+
			"</tr>"+
			"<tr>"+
				"<td colspan='2'><label>COMERCIAL: "+bodega.getNameComercial()+"</label></td>"+
				"<td colspan='10' style='text-align:left'>OBS "+mapeoDiccionario.get("OT")+": "+ot.getObservaciones()+"</td>"+
			"</tr>"+
			"<tr>"+
				"<td colspan='2'><label>ULTIMA ACTUALIZACION: "+Fechas.DDMMAA(ot.getFechaActualizacion())+"</label></td>"+
				"<td colspan='10' style='text-align:left'>FECHA DE ENTREGA: "+Fechas.DDMMAA(ot.getFechaEnvio())+"</td>"+
			"</tr>"+
		"</table>"+
		"<table class='table table-sm table-hover table-bordered table-condensed table-fluid'>"+
	        "<thead style='background-color: #eeeeee'>"+
				"<TR> "+
				        "<TH rowspan='2' style= 'text-align: center;'>GRUPO</TH>"+
				        "<TH rowspan='2' style= 'text-align: center;'>COD</TH>"+
						"<TH rowspan='2' style= 'text-align: center;'>EQUIPO</TH>"+
						"<TH colspan='6' style= 'text-align: center;'>ORDEN ORIGINAL</TH>"+
						"<TH colspan='7' style= 'text-align: center;'>DESPACHADO A LA FECHA</TH>"+
				"</TR>"+
				"<TR> "+
					"<TH style= 'text-align: center;'>UN</TH>"+
					"<TH style= 'text-align: center;'>CANT</TH>"+
					"<TH style= 'text-align: center;'>TIPO</TH>"+
					"<th width='5%'  style= 'text-align: center;font-size:10px;'>SALDO</th>"+
					
					"<TH style= 'text-align: center;'>MON</TH>"+
					"<TH style= 'text-align: center;'>P.UNITARIO<br>REPOSICION</TH>"+
					"<TH style= 'text-align: center;'>UN<br>"+mapeoDiccionario.get("ARR")+"</TH>"+
					"<TH style= 'text-align: center;'>P.UNITARIO<br>"+mapeoDiccionario.get("ARRIENDO")+"</TH>"+
					
					"<TH style= 'text-align: center;'>COD</TH>"+
					"<TH style= 'text-align: center;'>EQUIPO</TH>"+
					"<TH style= 'text-align: center;'>UN</TH>"+
					"<TH style= 'text-align: center;'>CANT</TH>"+
					"<TH width='5%'  style= 'text-align: center;font-size:10px;'>EQUIV</TH>"+
					
					"<TH style= 'text-align: center;'>MON</TH>"+
					"<TH style= 'text-align: center;'>P.UNITARIO<br>REPOSICION</TH>"+
					"<TH style= 'text-align: center;'>UN<br>"+mapeoDiccionario.get("ARR")+"</TH>"+
					"<TH style= 'text-align: center;'>P.UNITARIO<br>"+mapeoDiccionario.get("ARRIENDO")+"</TH>"+
					
					"<TH style= 'text-align: center;'>HIST</TH>"+
				"</TR>"+
			"</thead>"+
		"<tbody>";
		
		Map<Long,Double> mapDespachado = OtDespachado.mapSumaDespachadoPorIdOtCantEquiv(con, db, ot.getId());
		
		Double sumCantOrigen = (double)0;
		Double sumKgOrigen = (double)0;
		Double sumSalCantOrigen = (double)0;
		Double sumSalKgOrigen = (double)0;
		
		Double sumCantDesp = (double)0;
		
		Double sumCantEquiv = (double)0;
					
		for(int i=0;i<detalleOrigen.size();i++){
			
			Double cant = Double.parseDouble(detalleOrigen.get(i).getCantidad().replaceAll(",", ""));
			Double saldoPorDespachar = cant;
			Double desp = mapDespachado.get(detalleOrigen.get(i).getId_equipo());
			if(desp != null) {
				saldoPorDespachar = cant - desp;
				if((double)saldoPorDespachar < (double)0) {
					saldoPorDespachar = (double)0;
				}
			}
			
			String concepto = mapeoDiccionario.get("Arriendo");
			if((long)detalleOrigen.get(i).getEsVenta() == (long)1) {
				concepto = "Venta";
			}
			
			Double auxKg = Double.parseDouble(detalleOrigen.get(i).getTotalKG().trim().replaceAll(",", ""));
			Double auxKgSaldo = auxKg/cant * saldoPorDespachar;
			
			sumCantOrigen += cant;
			sumKgOrigen += auxKg;
			sumSalCantOrigen += saldoPorDespachar;
			sumSalKgOrigen += auxKgSaldo;
			
			vista += 
			"<TR>"+
				"<td style= 'text-align: left;'><div id='codEq_"+detalleOrigen.get(i).getId_equipo()+"'>"+detalleOrigen.get(i).getGrupo()+"</div></td>"+
				"<td style= 'text-align: left;'><div id='codEq_"+detalleOrigen.get(i).getId_equipo()+"'>"+detalleOrigen.get(i).getCodigo()+"</div></td>"+
				"<td style= 'text-align: left;'><div id='nomEq_"+detalleOrigen.get(i).getId_equipo()+"'>"+detalleOrigen.get(i).getEquipo()+"</div></td>"+

				"<td style= 'text-align: center;'>"+detalleOrigen.get(i).getUnidad()+"</td>"+
				"<td style= 'text-align: right;'>"+detalleOrigen.get(i).getCantidad()+"</td>"+
				
				"<td style= 'text-align: center;'>"+concepto+"</td>"+
				"<td style= 'text-align: right;'>"+myformatdouble2.format(saldoPorDespachar)+"</td>"+
				
				
				"<td style= 'text-align: center;'>"+detalleOrigen.get(i).getMoneda()+"</td>"+
				"<td style= 'text-align: right;'>"+detalleOrigen.get(i).getPrecioReposicion()+"</td>"+
				"<td style= 'text-align: center;'>"+detalleOrigen.get(i).getUnidadTiempo()+"</td>"+
				"<td style= 'text-align: right;'>"+detalleOrigen.get(i).getPrecioArriendo()+"</td>"+
				
				
				"<td style= 'text-align: left;'>";
				
				for(int k=0;k<detalleDespacho.size();k++){
					if(detalleOrigen.get(i).getId_equipo().toString().equals(detalleDespacho.get(k).get(1))) {
						vista += detalleDespacho.get(k).get(3)+"<br>";
					}
				}
				vista += "</td>"+
				"<td style= 'text-align: left;'>";
				for(int k=0;k<detalleDespacho.size();k++){
					if(detalleOrigen.get(i).getId_equipo().toString().equals(detalleDespacho.get(k).get(1))) {
							vista += detalleDespacho.get(k).get(4)+"<br>";
						}
					}
				vista += "</td>"+
				"<td style= 'text-align: center;' style= 'text-align: center;'>";
					for(int k=0;k<detalleDespacho.size();k++){
						if(detalleOrigen.get(i).getId_equipo().toString().equals(detalleDespacho.get(k).get(1))) {
							vista += detalleDespacho.get(k).get(5)+"<br>";
						}
					}
				vista += "</td>"+
				"<td style= 'text-align: right;' style= 'text-align: center;'>";
					for(int k=0;k<detalleDespacho.size();k++){
						if(detalleOrigen.get(i).getId_equipo().toString().equals(detalleDespacho.get(k).get(1))) {
							Double cantDespachado = Double.parseDouble(detalleDespacho.get(k).get(6).trim().replaceAll(",", ""));
							sumCantDesp += cantDespachado;
							vista += detalleDespacho.get(k).get(6)+"<br>";
						}
					}
				vista += "</td>"+
				"<td style= 'text-align: right;'>";
					for(int k=0;k<detalleDespacho.size();k++){
						if(detalleOrigen.get(i).getId_equipo().toString().equals(detalleDespacho.get(k).get(1))) {
							Double cantEquiv = Double.parseDouble(detalleDespacho.get(k).get(7).trim().replaceAll(",", ""));
							sumCantEquiv += cantEquiv;
							vista += detalleDespacho.get(k).get(7)+"<br>";
						}
					}
				vista += "</td>"+
					
						
"<td style= 'text-align: right;'>";
				for(int k=0;k<detalleDespacho.size();k++){
					if(detalleOrigen.get(i).getId_equipo().toString().equals(detalleDespacho.get(k).get(1))) {
						String key = detalleDespacho.get(k).get(2) + "_"+coti.getId();
						List<Double> list = mapPrecios.get(key);
						if(list != null) {
							Moneda moneda = mapMoneda.get(list.get(2).longValue());
							String mon = "";
							Long id_moneda = (long) 0;
							if(moneda != null) {
								mon = moneda.getNickName();
								id_moneda = moneda.getId();
							}
							vista += 
								"<select class=\"custom-select\""
								+ " id=\"NM_"+detalleDespacho.get(k).get(2)+""+coti.getId()+"\"" 
								+ " onchange=\"grabar(id,value,'"+detalleDespacho.get(k).get(2)+"','"+coti.getId()+"')\">"
									+ "<option value=\""+id_moneda+"\">"+mon+"</option>";
									for(Moneda x: listMoneda){
										vista += "<option value='"+x.getId()+"'>"+x.getNickName()+"</option>";
									}
								vista += "</select><br>";
						}else {
							vista += "<br>";
						}
					}
				}
			vista += "</td>"+
				
		
	"<td style= 'text-align: right;'>";
			for(int k=0;k<detalleDespacho.size();k++){
				if(detalleOrigen.get(i).getId_equipo().toString().equals(detalleDespacho.get(k).get(1))) {
					String key = detalleDespacho.get(k).get(2) + "_"+coti.getId();
					List<Double> list = mapPrecios.get(key);
					if(list != null) {
						Moneda mon = mapMoneda.get(list.get(2).longValue());
						Long nroDec = (long)0;
						if(mon != null) {
							nroDec = mon.getNumeroDecimales();
						}
						vista +=
							"<input type=\"text\" class=\"form-control right\""
							+ " id=\"PV_"+detalleDespacho.get(k).get(2)+""+coti.getId()+"\"" 
							+ " value='"+DecimalFormato.formato(list.get(0),nroDec)+"'"
							+ "	onfocus=\"value=value.replace(/,/g,'')\""
							+ "	onkeypress=\"return ingresoDouble(event,value)\""
							+ " onchange=\"grabar(id,value,'"+detalleDespacho.get(k).get(2)+"','"+coti.getId()+"')\">";
					}else {
						vista += "<br>";
					}
				}
			}
		vista += "</td>"+
			
	"<td style= 'text-align: center;'>";
		for(int k=0;k<detalleDespacho.size();k++){
			if(detalleOrigen.get(i).getId_equipo().toString().equals(detalleDespacho.get(k).get(1))) {
				String key = detalleDespacho.get(k).get(2) + "_"+coti.getId();
				List<Double> list = mapPrecios.get(key);
				if(list != null) {
					UnidadTiempo unidadTiempo = mapUnTiempo.get(list.get(3).longValue());
					String unTiempo = "";
					Long id_unidadTiepo = (long) 0;
					if(unidadTiempo != null) {
						unTiempo = unidadTiempo.getNombre();
						id_unidadTiepo = unidadTiempo.getId();
					}
					vista += 
							"<select class=\"custom-select\""
							+ " id=\"UT_"+detalleDespacho.get(k).get(2)+""+coti.getId()+"\"" 
							+ " onchange=\"grabar(id,value,'"+detalleDespacho.get(k).get(2)+"','"+coti.getId()+"')\">"
								+ "<option value=\""+id_unidadTiepo+"\">"+unTiempo+"</option>";
								for(UnidadTiempo x: listUnidadTiempo){
									vista += "<option value='"+x.getId()+"'>"+x.getNombre()+"</option>";
								}
							vista += "</select><br>";
				}else {
					vista += "<br>";
				}
			}
		}
	vista += "</td>"+
			
	"<td style= 'text-align: right;'>";
		for(int k=0;k<detalleDespacho.size();k++){
			if(detalleOrigen.get(i).getId_equipo().toString().equals(detalleDespacho.get(k).get(1))) {
				String key = detalleDespacho.get(k).get(2) + "_"+coti.getId();
				List<Double> list = mapPrecios.get(key);
				if(list != null) {
					Moneda mon = mapMoneda.get(list.get(2).longValue());
					Long nroDec = (long)0;
					if(mon != null) {
						nroDec = mon.getNumeroDecimales();
					}
					vista +=
						"<input type=\"text\" class=\"form-control right\""
						+ " id=\"PA_"+detalleDespacho.get(k).get(2)+""+coti.getId()+"\"" 
						+ " value='"+DecimalFormato.formato(list.get(1),nroDec)+"'"
						+ "	onfocus=\"value=value.replace(/,/g,'')\""
						+ "	onkeypress=\"return ingresoDouble(event,value)\""
						+ " onchange=\"grabar(id,value,'"+detalleDespacho.get(k).get(2)+"','"+coti.getId()+"')\">";
				}else {
					vista += "<br>";
				}
			}
		}
	vista += "</td>"+
						
				
				
				
				
				
				
				
				"<td style= 'text-align: center;'>"+
					"<a href='#' onclick='trazaOt("+ot.id+","+detalleOrigen.get(i).getId_equipo()+")'>"+
						"<kbd style='background-color: rgb(90, 200, 245)'>"+
							"<font color='green'>H</font>"+
						"</kbd>"+
					"</a>"+
				"</td>"+
			  "</TR>";
 			}
			vista += "</tbody><tfoot>";
		
			vista += 
		
					"<TR> "+
							"<TH></TH>"+
							"<TH></TH>"+
							"<TH style= 'text-align: left;'>TOTALES</TH>"+
							"<TH></TH>"+
							"<TH style= 'text-align: right;'>"+myformatdouble2.format(sumCantOrigen)+"</TH>"+
							
							"<TH></TH>"+
							"<TH style= 'text-align: right;'>"+myformatdouble2.format(sumSalCantOrigen)+"</TH>"+
												
							"<TH></TH>"+
							"<TH></TH>"+
							"<TH></TH>"+

							"<TH style= 'text-align: right;'>"+myformatdouble2.format(sumCantDesp)+"</TH>"+
							"<TH style= 'text-align: right;'>"+myformatdouble2.format(sumCantEquiv)+"</TH>"+
							
							"<TH></TH>"+
						"</TR>";
		
		
		
			vista=vista+
				"</tfoot>"+
				"</table>";
		return(vista);
	}
	
	public static String vistaReportPreciosOt(Connection con, String db, Long id_ot, Map<String,String> mapeoDiccionario){
		String vista="";
		Ot ot = Ot.find(con, db, id_ot);
		Cotizacion coti = Cotizacion.find(con, db, ot.getId_cotizacion());
		List<CotizaDetalle> detalleOrigen = CotizaDetalle.allPorIdCotizacion(con, db, ot.getId_cotizacion());
		List<List<String>> detalleDespacho = OtDespachado.listSumaDespachadoPorIdOtCantDesp(con, db, ot.getId());
		BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, db, coti.getId_bodegaEmpresa());
		
		Map<String,List<Double>> mapPrecios = ListaPrecio.mapListaPreciosEquiposPorBodega(con, db, bodega.getId());
		Map<Long,Moneda> mapMoneda = Moneda.mapMonedas(con, db);
		Map<Long,UnidadTiempo> mapUnTiempo = UnidadTiempo.mapUnidadTiempo(con, db);
		
		vista=vista+
		"<table class='table table-sm table-hover table-bordered table-condensed table-fluid'>"+
			"<tr>"+
				"<td><label>N° "+mapeoDiccionario.get("OT")+": "+ot.numero+"</label></td>"+
				"<td><label>Fecha "+mapeoDiccionario.get("OT")+": "+Fechas.DDMMAA(ot.fecha)+"</label></td>"+
				"<td><label>N° COTI: "+coti.numero+"</label></td>"+
				"<td><label>Fecha COTI: "+Fechas.DDMMAA(coti.fecha)+"</label></td>"+
				
				"<td><label>"+mapeoDiccionario.get("Bodega")+"/Proyecto: "+bodega.getNombre()+"</label></td>"+
				"<td><label>N° OC: "+coti.getNumeroOC()+"</label></td>"+
			"</tr>"+
			"<tr>"+
				"<td colspan='2'><label>"+mapeoDiccionario.get("RUT")+" Cliente: "+bodega.getRutCliente()+"</label></td>"+
				"<td colspan='2'><label>Nombre Cliente: "+bodega.getNickCliente()+"</label></td>"+
				"<td><label>Nombre Proyecto: "+bodega.getNickProyecto()+"</label></td>"+
				"<td>"+
				"</td>"+
			"</tr>"+
			"<tr>"+
				"<td colspan='2'><label>SUCURSAL: "+bodega.getNameSucursal()+"</label></td>"+
				"<td colspan='10' style='text-align:left'>OBS COTI: "+coti.getObservaciones()+"</td>"+
			"</tr>"+
			"<tr>"+
				"<td colspan='2'><label>COMERCIAL: "+bodega.getNameComercial()+"</label></td>"+
				"<td colspan='10' style='text-align:left'>OBS "+mapeoDiccionario.get("OT")+": "+ot.getObservaciones()+"</td>"+
			"</tr>"+
			"<tr>"+
				"<td colspan='2'><label>ULTIMA ACTUALIZACION: "+Fechas.DDMMAA(ot.getFechaActualizacion())+"</label></td>"+
				"<td colspan='10' style='text-align:left'>FECHA DE ENTREGA: "+Fechas.DDMMAA(ot.getFechaEnvio())+"</td>"+
			"</tr>"+
		"</table>"+
		"<table class='table table-sm table-hover table-bordered table-condensed table-fluid'>"+
	        "<thead style='background-color: #eeeeee'>"+
				"<TR> "+
				        "<TH rowspan='2' style= 'text-align: center;'>GRUPO</TH>"+
				        "<TH rowspan='2' style= 'text-align: center;'>COD</TH>"+
						"<TH rowspan='2' style= 'text-align: center;'>EQUIPO</TH>"+
						"<TH colspan='6' style= 'text-align: center;'>ORDEN ORIGINAL</TH>"+
						"<TH colspan='7' style= 'text-align: center;'>DESPACHADO A LA FECHA</TH>"+
				"</TR>"+
				"<TR> "+
					"<TH style= 'text-align: center;'>UN</TH>"+
					"<TH style= 'text-align: center;'>CANT</TH>"+
					"<TH style= 'text-align: center;'>TIPO</TH>"+
					"<th width='5%'  style= 'text-align: center;font-size:10px;'>SALDO</th>"+
					
					"<TH style= 'text-align: center;'>MON</TH>"+
					"<TH style= 'text-align: center;'>P.UNITARIO<br>REPOSICION</TH>"+
					"<TH style= 'text-align: center;'>UN<br>"+mapeoDiccionario.get("ARR")+"</TH>"+
					"<TH style= 'text-align: center;'>P.UNITARIO<br>"+mapeoDiccionario.get("ARRIENDO")+"</TH>"+
					
					"<TH style= 'text-align: center;'>COD</TH>"+
					"<TH style= 'text-align: center;'>EQUIPO</TH>"+
					"<TH style= 'text-align: center;'>UN</TH>"+
					"<TH style= 'text-align: center;'>CANT</TH>"+
					"<TH width='5%'  style= 'text-align: center;font-size:10px;'>EQUIV</TH>"+
					
					"<TH style= 'text-align: center;'>MON</TH>"+
					"<TH style= 'text-align: center;'>P.UNITARIO<br>REPOSICION</TH>"+
					"<TH style= 'text-align: center;'>UN<br>"+mapeoDiccionario.get("ARR")+"</TH>"+
					"<TH style= 'text-align: center;'>P.UNITARIO<br>"+mapeoDiccionario.get("ARRIENDO")+"</TH>"+
					
					"<TH style= 'text-align: center;'>HIST</TH>"+
				"</TR>"+
			"</thead>"+
		"<tbody>";
		
		Map<Long,Double> mapDespachado = OtDespachado.mapSumaDespachadoPorIdOtCantEquiv(con, db, ot.getId());
		
		Double sumCantOrigen = (double)0;
		Double sumKgOrigen = (double)0;
		Double sumSalCantOrigen = (double)0;
		Double sumSalKgOrigen = (double)0;
		
		Double sumCantDesp = (double)0;
		
		Double sumCantEquiv = (double)0;
					
		for(int i=0;i<detalleOrigen.size();i++){
			
			Double cant = Double.parseDouble(detalleOrigen.get(i).getCantidad().replaceAll(",", ""));
			Double saldoPorDespachar = cant;
			Double desp = mapDespachado.get(detalleOrigen.get(i).getId_equipo());
			if(desp != null) {
				saldoPorDespachar = cant - desp;
				if((double)saldoPorDespachar < (double)0) {
					saldoPorDespachar = (double)0;
				}
			}
			
			String concepto = mapeoDiccionario.get("Arriendo");
			if((long)detalleOrigen.get(i).getEsVenta() == (long)1) {
				concepto = "Venta";
			}
			
			Double auxKg = Double.parseDouble(detalleOrigen.get(i).getTotalKG().trim().replaceAll(",", ""));
			Double auxKgSaldo = auxKg/cant * saldoPorDespachar;
			
			sumCantOrigen += cant;
			sumKgOrigen += auxKg;
			sumSalCantOrigen += saldoPorDespachar;
			sumSalKgOrigen += auxKgSaldo;
			
			vista += 
			"<TR>"+
				"<td style= 'text-align: left;'><div id='codEq_"+detalleOrigen.get(i).getId_equipo()+"'>"+detalleOrigen.get(i).getGrupo()+"</div></td>"+
				"<td style= 'text-align: left;'><div id='codEq_"+detalleOrigen.get(i).getId_equipo()+"'>"+detalleOrigen.get(i).getCodigo()+"</div></td>"+
				"<td style= 'text-align: left;'><div id='nomEq_"+detalleOrigen.get(i).getId_equipo()+"'>"+detalleOrigen.get(i).getEquipo()+"</div></td>"+

				"<td style= 'text-align: center;'>"+detalleOrigen.get(i).getUnidad()+"</td>"+
				"<td style= 'text-align: right;'>"+detalleOrigen.get(i).getCantidad()+"</td>"+
				
				"<td style= 'text-align: center;'>"+concepto+"</td>"+
				"<td style= 'text-align: right;'>"+myformatdouble2.format(saldoPorDespachar)+"</td>"+
				
				
				"<td style= 'text-align: center;'>"+detalleOrigen.get(i).getMoneda()+"</td>"+
				"<td style= 'text-align: right;'>"+detalleOrigen.get(i).getPrecioReposicion()+"</td>"+
				"<td style= 'text-align: center;'>"+detalleOrigen.get(i).getUnidadTiempo()+"</td>"+
				"<td style= 'text-align: right;'>"+detalleOrigen.get(i).getPrecioArriendo()+"</td>"+
				
				
				"<td style= 'text-align: left;'>";
				
				for(int k=0;k<detalleDespacho.size();k++){
					if(detalleOrigen.get(i).getId_equipo().toString().equals(detalleDespacho.get(k).get(1))) {
						vista += detalleDespacho.get(k).get(3)+"<br>";
					}
				}
				vista += "</td>"+
				"<td style= 'text-align: left;'>";
				for(int k=0;k<detalleDespacho.size();k++){
					if(detalleOrigen.get(i).getId_equipo().toString().equals(detalleDespacho.get(k).get(1))) {
							vista += detalleDespacho.get(k).get(4)+"<br>";
						}
					}
				vista += "</td>"+
				"<td style= 'text-align: center;' style= 'text-align: center;'>";
					for(int k=0;k<detalleDespacho.size();k++){
						if(detalleOrigen.get(i).getId_equipo().toString().equals(detalleDespacho.get(k).get(1))) {
							vista += detalleDespacho.get(k).get(5)+"<br>";
						}
					}
				vista += "</td>"+
				"<td style= 'text-align: right;' style= 'text-align: center;'>";
					for(int k=0;k<detalleDespacho.size();k++){
						if(detalleOrigen.get(i).getId_equipo().toString().equals(detalleDespacho.get(k).get(1))) {
							Double cantDespachado = Double.parseDouble(detalleDespacho.get(k).get(6).trim().replaceAll(",", ""));
							sumCantDesp += cantDespachado;
							vista += detalleDespacho.get(k).get(6)+"<br>";
						}
					}
				vista += "</td>"+
				"<td style= 'text-align: right;'>";
					for(int k=0;k<detalleDespacho.size();k++){
						if(detalleOrigen.get(i).getId_equipo().toString().equals(detalleDespacho.get(k).get(1))) {
							Double cantEquiv = Double.parseDouble(detalleDespacho.get(k).get(7).trim().replaceAll(",", ""));
							sumCantEquiv += cantEquiv;
							vista += detalleDespacho.get(k).get(7)+"<br>";
						}
					}
				vista += "</td>"+
					
						
		"<td style= 'text-align: right;'>";
				for(int k=0;k<detalleDespacho.size();k++){
					if(detalleOrigen.get(i).getId_equipo().toString().equals(detalleDespacho.get(k).get(1))) {
						String key = detalleDespacho.get(k).get(2) + "_"+coti.getId();
						List<Double> list = mapPrecios.get(key);
						if(list != null) {
							Moneda moneda = mapMoneda.get(list.get(2).longValue());
							String mon = "";
							if(moneda != null) {
								mon = moneda.getNickName();
							}
							vista += mon+"<br>";
						}else {
							vista += "<br>";
						}
					}
				}
			vista += "</td>"+
				
		
	"<td style= 'text-align: right;'>";
			for(int k=0;k<detalleDespacho.size();k++){
				if(detalleOrigen.get(i).getId_equipo().toString().equals(detalleDespacho.get(k).get(1))) {
					String key = detalleDespacho.get(k).get(2) + "_"+coti.getId();
					List<Double> list = mapPrecios.get(key);
					if(list != null) {
						Moneda mon = mapMoneda.get(list.get(2).longValue());
						Long nroDec = (long)0;
						if(mon != null) {
							nroDec = mon.getNumeroDecimales();
						}
						vista += DecimalFormato.formato(list.get(0),nroDec)+"<br>";
					}else {
						vista += "<br>";
					}
				}
			}
		vista += "</td>"+
			
	"<td style= 'text-align: center;'>";
		for(int k=0;k<detalleDespacho.size();k++){
			if(detalleOrigen.get(i).getId_equipo().toString().equals(detalleDespacho.get(k).get(1))) {
				String key = detalleDespacho.get(k).get(2) + "_"+coti.getId();
				List<Double> list = mapPrecios.get(key);
				if(list != null) {
					UnidadTiempo unidadTiempo = mapUnTiempo.get(list.get(3).longValue());
					String unTiempo = "";
					if(unidadTiempo != null) {
						unTiempo = unidadTiempo.getNombre();
					}
					vista += unTiempo+"<br>";
				}else {
					vista += "<br>";
				}
			}
		}
	vista += "</td>"+
			
	"<td style= 'text-align: right;'>";
		for(int k=0;k<detalleDespacho.size();k++){
			if(detalleOrigen.get(i).getId_equipo().toString().equals(detalleDespacho.get(k).get(1))) {
				String key = detalleDespacho.get(k).get(2) + "_"+coti.getId();
				List<Double> list = mapPrecios.get(key);
				if(list != null) {
					Moneda mon = mapMoneda.get(list.get(2).longValue());
					Long nroDec = (long)0;
					if(mon != null) {
						nroDec = mon.getNumeroDecimales();
					}
					vista += DecimalFormato.formato(list.get(1),nroDec)+"<br>";
				}else {
					vista += "<br>";
				}
			}
		}
	vista += "</td>"+
						
				
				
				
				
				
				
				
				"<td style= 'text-align: center;'>"+
					"<a href='#' onclick='trazaOt("+ot.id+","+detalleOrigen.get(i).getId_equipo()+")'>"+
						"<kbd style='background-color: rgb(90, 200, 245)'>"+
							"<font color='green'>H</font>"+
						"</kbd>"+
					"</a>"+
				"</td>"+
			  "</TR>";
 			}
			vista += "</tbody><tfoot>";
		
			vista += 
		
					"<TR> "+
							"<TH></TH>"+
							"<TH></TH>"+
							"<TH style= 'text-align: left;'>TOTALES</TH>"+
							"<TH></TH>"+
							"<TH style= 'text-align: right;'>"+myformatdouble2.format(sumCantOrigen)+"</TH>"+
							
							"<TH></TH>"+
							"<TH style= 'text-align: right;'>"+myformatdouble2.format(sumSalCantOrigen)+"</TH>"+
												
							"<TH></TH>"+
							"<TH></TH>"+
							"<TH></TH>"+

							"<TH style= 'text-align: right;'>"+myformatdouble2.format(sumCantDesp)+"</TH>"+
							"<TH style= 'text-align: right;'>"+myformatdouble2.format(sumCantEquiv)+"</TH>"+
							
							"<TH></TH>"+
						"</TR>";
		
		
		
			vista=vista+
				"</tfoot>"+
				"</table>";
		return(vista);
	}
	
	public static String vistaCabezeraOt(Connection con, String db, Ot ot, Cotizacion coti, Map<String,String> mapeoDiccionario){
		String vista="";
		
		BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, db, coti.getId_bodegaEmpresa());
		if(bodega != null) {
			vista=vista+
					"<table class='table table-sm table-hover table-bordered table-condensed table-fluid'>"+
						"<tr>"+
							"<td><input type='hidden' name='id_ot' value='"+ot.getId()+"'<label>N° "+mapeoDiccionario.get("OT")+": "+ot.numero+"</label></td>"+
							"<td><label>Fecha "+mapeoDiccionario.get("OT")+": "+Fechas.DDMMAA(ot.fecha)+"</label></td>"+
							"<td><label>N° COTI: "+coti.numero+"</label></td>"+
							"<td><label>Fecha COTI: "+Fechas.DDMMAA(coti.fecha)+"</label></td>"+
							
							"<td><label>"+mapeoDiccionario.get("Bodega")+"/Proyecto: "+bodega.getNombre()+"</label></td>"+
							"<td><label>N° OC: "+coti.getNumeroOC()+"</label></td>"+
						"</tr>"+
						"<tr>"+
							"<td colspan='2'><label>"+mapeoDiccionario.get("RUT")+" Cliente: "+bodega.getRutCliente()+"</label></td>"+
							"<td colspan='2'><label>Nombre Cliente: "+bodega.getNickCliente()+"</label></td>"+
							"<td><label>Nombre Proyecto: "+bodega.getNickProyecto()+"</label></td>"+
							"<td>"+
							"</td>"+
						"</tr>"+
						"<tr>"+
							"<td><label>SUCURSAL: "+coti.getNameSucursal()+"</label></td>"+
							"<td><label>COMERCIAL: "+coti.getNameComercial()+"</label></td>"+
							"<td colspan='2' style='text-align:left'>OBS "+mapeoDiccionario.get("OT")+": "+ot.getObservaciones()+"</td>"+
							"<td colspan='4' style='text-align:left'>OBS COTI: "+coti.getObservaciones()+"</td>"+
						"</tr>"+
						
					"</table>";
		}else {
			vista=vista+
					"<table class='table table-sm table-hover table-bordered table-condensed table-fluid'>"+
						"<tr>"+
							"<td><input type='hidden' name='id_ot' value='"+ot.getId()+"'<label>N° "+mapeoDiccionario.get("OT")+": "+ot.numero+"</label></td>"+
							"<td><label>Fecha "+mapeoDiccionario.get("OT")+": "+Fechas.DDMMAA(ot.fecha)+"</label></td>"+
							"<td><label>N° COTI: "+coti.numero+"</label></td>"+
							"<td><label>Fecha COTI: "+Fechas.DDMMAA(coti.fecha)+"</label></td>"+
							
							"<td><label>"+mapeoDiccionario.get("Bodega")+"/Proyecto: </label></td>"+
							"<td><label>N° OC: "+coti.getNumeroOC()+"</label></td>"+
						"</tr>"+
						"<tr>"+
							"<td colspan='2'><label>"+mapeoDiccionario.get("RUT")+" Cliente: </label></td>"+
							"<td colspan='2'><label>Nombre Cliente: </label></td>"+
							"<td><label>Nombre Proyecto: </label></td>"+
							"<td>"+
							"</td>"+
						"</tr>"+
						"<tr>"+
							"<td><label>SUCURSAL: "+coti.getNameSucursal()+"</label></td>"+
							"<td><label>COMERCIAL: "+coti.getNameComercial()+"</label></td>"+
							"<td colspan='2' style='text-align:left'>OBS "+mapeoDiccionario.get("OT")+": "+ot.getObservaciones()+"</td>"+
							"<td colspan='4' style='text-align:left'>OBS COTI: "+coti.getObservaciones()+"</td>"+
						"</tr>"+
						
					"</table>";
		}
		
		return(vista);
	}
	
	public static boolean delete(Connection con, String db, Ot aux, Map<String,String> mapPermiso) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("delete from `"+db+"`.listaPrecio where id_cotizacion = ?;");
			smt.setLong(1, aux.getId_cotizacion());
			smt.executeUpdate();
			smt.close();
			
			PreparedStatement smt2 = con
					.prepareStatement("delete from `"+db+"`.ot where id = ?;");
			smt2.setLong(1, aux.id);
			smt2.executeUpdate();
			smt2.close();
			
			Long id_bodegaEmpresa = (long)0;
			if(mapPermiso.get("parametro.cotizaPorTasa").equals("1")) {
				Cotizacion cotizacion = Cotizacion.find(con, db, aux.getId_cotizacion());
				if(cotizacion!=null && cotizacion.id_bodegaEmpresa!=null) {
					id_bodegaEmpresa = cotizacion.id_bodegaEmpresa;
				}
			}
			
			PreparedStatement smt3 = con
					.prepareStatement("UPDATE `"+db+"`.cotizacion SET id_ot = 0, id_bodegaEmpresa = ?, esModificable = 1, confirmada = 0, fechaConfirmada = null, id_cotizaEstado=1 WHERE id = ?;");
			smt3.setLong(1, id_bodegaEmpresa);
			smt3.setLong(2, aux.getId_cotizacion());
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
				.prepareStatement("update `"+db+"`.ot set esEliminable = ?, confirmada = ?, fechaConfirmada = ? where id in "+listadoIdOtConfirmar+";");
			smt.setString(1, "0");
			smt.setString(2, "1");
			smt.setString(3, Fechas.hoy().getFechaStrAAMMDD());
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean cambiar_a_NoConfirmada(Connection con, String db, Long id_ot){
		try {
			PreparedStatement smt = con
					.prepareStatement("select id from `"+db+"`.otDespachado where id_ot=?;");
			smt.setLong(1, id_ot);
			ResultSet rs = smt.executeQuery();
			if(rs.next()) {
				rs.close();
				smt.close();
				return(false);
			}else {
				PreparedStatement smt2 = con
						.prepareStatement("update `"+db+"`.ot set esEliminable = 1, fechaConfirmada = null, confirmada = 0 where id = ?;");
				smt2.setLong(1, id_ot);
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
	
	public static boolean eliminarListaPrecio(Connection con, String db, Long id_ot){
		try {
			PreparedStatement smt = con
					.prepareStatement("select id from `"+db+"`.cotizacion where id_ot=?;");
			smt.setLong(1, id_ot);
			ResultSet rs = smt.executeQuery();
			if(rs.next()) {
				PreparedStatement smt2 = con
						.prepareStatement("delete from `"+db+"`.listaPrecio where id_cotizacion = ?;");
				smt2.setLong(1, rs.getLong(1));
				smt2.executeUpdate();
				smt2.close();
				rs.close();
				smt.close();
				return(true);
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(false);
	}
	
	public static String cantidadComprometidaPorEquip (Connection con, String db, Long id_equipo){
		try {
			PreparedStatement smt = con
					.prepareStatement(" select otDespachado.id_equipoOrigen, sum(cotizaDetalle.cantidad - otDespachado.cantidadRebajaOt) "
							+ " from `"+db+"`.otDespachado "
							+ " left join `"+db+"`.cotizaDetalle on cotizaDetalle.id = otDespachado.id_cotizaDetalle "
							+ " left join `"+db+"`.ot on ot.id = otDespachado.id_ot "
							+ " where ot.id_otEstado = 1 and confirmada = 1 and id_equipo = ? "
							+ " group by otDespachado.id_equipoOrigen;");
			smt.setLong(1, id_equipo);
			ResultSet rs = smt.executeQuery();
			if(rs.next()) {
				String coprometido = myformatdouble2.format(rs.getDouble(2));
				rs.close();
				smt.close();
				return(coprometido);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return("0.00");
	}
	
	public static List<List<String>> listOtEliminar(Connection con, String db, String esPorSucursal, String id_sucursal){
		List<List<String>> listOt = new ArrayList<List<String>>();
		List<Ot> listadoOt = Ot.allEliminables(con, db);
		Map<Long,BodegaEmpresa> mapBodegas = BodegaEmpresa.mapAll(con, db);
		Map<Long,Cotizacion> mapCotizacion = Cotizacion.mapAllConOt(con, db);
		if(esPorSucursal.equals("1")) {
			listadoOt.forEach(x->{
				if(x.id_sucursal.toString().equals(id_sucursal)) {
					Cotizacion coti = mapCotizacion.get(x.getId_cotizacion());
    				String numCoti = "";
    				String fechaCoti = "";
    				Long id_bodegaEmpresa = (long)0;
    				String obsCoti = "";
    				String cotiPDF = "";
    				if(coti!=null) {
    					numCoti = coti.getNumero().toString();
    					fechaCoti = Fechas.AAMMDD(coti.getFecha());
    					id_bodegaEmpresa = coti.getId_bodegaEmpresa();
    					obsCoti = coti.getObservaciones();
    					cotiPDF = coti.getCotizacionPDF();
    				}
    				
    				BodegaEmpresa bodega = mapBodegas.get(id_bodegaEmpresa);
    				String nomCliente = "";
    				String nomProyecto = "";
    				String nomBodega = "";
    				if(bodega!=null) {
    					nomCliente = bodega.getNickCliente();
    					nomProyecto = bodega.getNickProyecto();
    					nomBodega = bodega.getNombre();
    				}
    				List<String> aux = new ArrayList<String>();
    				aux.add(x.getId().toString());				// 0 id_Ot
    				aux.add(x.getId_cotizacion().toString());	// 1 id_Cotizacion
    				aux.add(x.getNumero().toString());			// 2 numero de ot
    				aux.add(Fechas.AAMMDD(x.getFecha()));		// 3 fecha de ot
    				aux.add(numCoti);							// 4 numero de cotizacion
    				aux.add(fechaCoti);							// 5 fecha de cotizacion
    				aux.add(nomCliente);						// 6 nombre de cliente desde bodegaempresa
    				aux.add(nomProyecto);						// 7 nombre de proyecto desde bodegaempresa
    				aux.add(x.getObservaciones());				// 8 observaciones de ot
    				aux.add(obsCoti);							// 9 observaciones de cotizacion
    				aux.add(x.getOtPDF());						// 10 doc adjunto de ot
    				aux.add(cotiPDF);							// 11 doc adjunto de cotizacion
    				aux.add(nomBodega);							// 12 nombre bodega empresa
    				aux.add(x.nameSucursal);					// 13 sucursal
    				aux.add(x.nameComercial);					// 14 comercial
    				aux.add(x.getFechaConfirmada());			// 15 fecha confirmada OT
					listOt.add(aux);
				}
			});
		}else {
			listadoOt.forEach(x->{
				Cotizacion coti = mapCotizacion.get(x.getId_cotizacion());
				String numCoti = "";
				String fechaCoti = "";
				Long id_bodegaEmpresa = (long)0;
				String obsCoti = "";
				String cotiPDF = "";
				if(coti!=null) {
					numCoti = coti.getNumero().toString();
					fechaCoti = Fechas.AAMMDD(coti.getFecha());
					id_bodegaEmpresa = coti.getId_bodegaEmpresa();
					obsCoti = coti.getObservaciones();
					cotiPDF = coti.getCotizacionPDF();
				}
				
				BodegaEmpresa bodega = mapBodegas.get(id_bodegaEmpresa);
				String nomCliente = "";
				String nomProyecto = "";
				String nomBodega = "";
				if(bodega!=null) {
					nomCliente = bodega.getNickCliente();
					nomProyecto = bodega.getNickProyecto();
					nomBodega = bodega.getNombre();
				}
				List<String> aux = new ArrayList<String>();
				aux.add(x.getId().toString());				// 0 id_Ot
				aux.add(x.getId_cotizacion().toString());	// 1 id_Cotizacion
				aux.add(x.getNumero().toString());			// 2 numero de ot
				aux.add(Fechas.AAMMDD(x.getFecha()));		// 3 fecha de ot
				aux.add(numCoti);							// 4 numero de cotizacion
				aux.add(fechaCoti);							// 5 fecha de cotizacion
				aux.add(nomCliente);						// 6 nombre de cliente desde bodegaempresa
				aux.add(nomProyecto);						// 7 nombre de proyecto desde bodegaempresa
				aux.add(x.getObservaciones());				// 8 observaciones de ot
				aux.add(obsCoti);							// 9 observaciones de cotizacion
				aux.add(x.getOtPDF());						// 10 doc adjunto de ot
				aux.add(cotiPDF);							// 11 doc adjunto de cotizacion
				aux.add(nomBodega);							// 12 nombre bodega empresa
				aux.add(x.nameSucursal);					// 13 sucursal
				aux.add(x.nameComercial);					// 14 comercial
				aux.add(x.getFechaConfirmada());			// 15 fecha confirmada OT
				listOt.add(aux);
			});
		}
		
		return(listOt);
	}
	
	public static List<List<String>> listOtCambiarEstado(Connection con, String db, String esPorSucursal, String id_sucursal, String desde, String hasta){
		List<List<String>> listOt = new ArrayList<List<String>>();
		List<Ot> listadoOt = Ot.allDesdeHasta(con, db, desde, hasta);
		Map<Long,BodegaEmpresa> mapBodegas = BodegaEmpresa.mapAll(con, db);
		Map<Long,Cotizacion> mapCotizacion = Cotizacion.mapAllConOt(con, db);
		Map<Long,OtEstado> mapOtEstado = OtEstado.mapAll(con, db);
		
		Map<Long, Double> mapSaldos = CotizaDetalle.mapIdCotiVsSaldo(con, db);
		
		if(esPorSucursal.equals("1")) {
			listadoOt.forEach(x->{
				if(x.id_sucursal.toString().equals(id_sucursal)) {
					Cotizacion coti = mapCotizacion.get(x.getId_cotizacion());
    				String numCoti = "";
    				String fechaCoti = "";
    				Long id_bodegaEmpresa = (long)0;
    				String obsCoti = "";
    				String cotiPDF = "";
    				if(coti!=null) {
    					numCoti = coti.getNumero().toString();
    					fechaCoti = Fechas.AAMMDD(coti.getFecha());
    					id_bodegaEmpresa = coti.getId_bodegaEmpresa();
    					obsCoti = coti.getObservaciones();
    					cotiPDF = coti.getCotizacionPDF();
    				}
    				
    				BodegaEmpresa bodega = mapBodegas.get(id_bodegaEmpresa);
    				String nomCliente = "";
    				String nomProyecto = "";
    				String nomBodega = "";
    				if(bodega!=null) {
    					nomCliente = bodega.getNickCliente();
    					nomProyecto = bodega.getNickProyecto();
    					nomBodega = bodega.getNombre();
    				}
    				OtEstado estado = mapOtEstado.get(x.getId_otEstado());
    				String id_estado = "0";
    				String nomEstado = "";
    				
    				if(estado!=null) {
    					id_estado = estado.getId().toString();
    					nomEstado = estado.getEstado();
    				}
    				List<String> aux = new ArrayList<String>();
    				aux.add(x.getId().toString());				// 0 id_Ot
    				aux.add(x.getId_cotizacion().toString());	// 1 id_Cotizacion
    				aux.add(x.getNumero().toString());			// 2 numero de ot
    				aux.add(Fechas.AAMMDD(x.getFecha()));		// 3 fecha de ot
    				aux.add(numCoti);							// 4 numero de cotizacion
    				aux.add(fechaCoti);							// 5 fecha de cotizacion
    				aux.add(nomCliente);						// 6 nombre de cliente desde bodegaempresa
    				aux.add(nomProyecto);						// 7 nombre de proyecto desde bodegaempresa
    				aux.add(x.getObservaciones());				// 8 observaciones de ot
    				aux.add(obsCoti);							// 9 observaciones de cotizacion
    				aux.add(x.getOtPDF());						// 10 doc adjunto de ot
    				aux.add(cotiPDF);							// 11 doc adjunto de cotizacion
    				aux.add(id_estado);							// 12 id_estado
    				aux.add(nomEstado);							// 13 nombre estado
    				aux.add(nomBodega);							// 14 nombre bodega empresa
    				aux.add(x.nameSucursal);					// 15 sucursal
    				aux.add(x.nameComercial);					// 16 comercial
    				aux.add(x.getFechaActualizacion());			// 17 getFechaActualizacion
    				aux.add(x.getFechaEnvio());					// 18 getFechaEnvio
    				aux.add(x.getFechaConfirmada());			// 19 fecha confirmada OT
    				
    				Double saldo = mapSaldos.get(x.getId_cotizacion());
    				if(saldo!=null) {
    					aux.add(saldo.toString());		// 20 saldos por despachar
    				}else {
    					aux.add("---");		// 20 saldos por despachar
    				}
    				aux.add(x.getNotaOtEstado());			//21 NotaOtEstado
    				
					listOt.add(aux);
				}
			});
		}else {
			listadoOt.forEach(x->{
				Cotizacion coti = mapCotizacion.get(x.getId_cotizacion());
				String numCoti = "";
				String fechaCoti = "";
				Long id_bodegaEmpresa = (long)0;
				String obsCoti = "";
				String cotiPDF = "";
				if(coti!=null) {
					numCoti = coti.getNumero().toString();
					fechaCoti = Fechas.AAMMDD(coti.getFecha());
					id_bodegaEmpresa = coti.getId_bodegaEmpresa();
					obsCoti = coti.getObservaciones();
					cotiPDF = coti.getCotizacionPDF();
				}
				
				BodegaEmpresa bodega = mapBodegas.get(id_bodegaEmpresa);
				String nomCliente = "";
				String nomProyecto = "";
				String nomBodega = "";
				if(bodega!=null) {
					nomCliente = bodega.getNickCliente();
					nomProyecto = bodega.getNickProyecto();
					nomBodega = bodega.getNombre();
				}
				OtEstado estado = mapOtEstado.get(x.getId_otEstado());
				String id_estado = "0";
				String nomEstado = "";
				
				if(estado!=null) {
					id_estado = estado.getId().toString();
					nomEstado = estado.getEstado();
				}
				List<String> aux = new ArrayList<String>();
				aux.add(x.getId().toString());				// 0 id_Ot
				aux.add(x.getId_cotizacion().toString());	// 1 id_Cotizacion
				aux.add(x.getNumero().toString());			// 2 numero de ot
				aux.add(Fechas.AAMMDD(x.getFecha()));		// 3 fecha de ot
				aux.add(numCoti);							// 4 numero de cotizacion
				aux.add(fechaCoti);							// 5 fecha de cotizacion
				aux.add(nomCliente);						// 6 nombre de cliente desde bodegaempresa
				aux.add(nomProyecto);						// 7 nombre de proyecto desde bodegaempresa
				aux.add(x.getObservaciones());				// 8 observaciones de ot
				aux.add(obsCoti);							// 9 observaciones de cotizacion
				aux.add(x.getOtPDF());						// 10 doc adjunto de ot
				aux.add(cotiPDF);							// 11 doc adjunto de cotizacion
				aux.add(id_estado);							// 12 id_estado
				aux.add(nomEstado);							// 13 nombre estado
				aux.add(nomBodega);							// 14 nombre bodega empresa
				aux.add(x.nameSucursal);					// 15 sucursal
				aux.add(x.nameComercial);					// 16 comercial
				aux.add(x.getFechaActualizacion());			// 17 getFechaActualizacion
				aux.add(x.getFechaEnvio());					// 18 getFechaEnvio
				aux.add(x.getFechaConfirmada());			// 19 fecha confirmada OT
				
				Double saldo = mapSaldos.get(x.getId_cotizacion());
				if(saldo!=null) {
					aux.add(myformatdouble2.format(saldo));		// 20 saldos por despachar
				}else {
					aux.add("---");		// 20 saldos por despachar
				}
				
				aux.add(x.getNotaOtEstado());			//21 NotaOtEstado
				
				listOt.add(aux);
			});
		}
		
		return(listOt);
	}
	
	public static List<List<String>> listOtRevisar(Connection con, String db, String esPorSucursal, String id_sucursal, String desde, String hasta){
		List<List<String>> listOt = new ArrayList<List<String>>();
		List<Ot> listadoOt = Ot.allDesdeHasta(con, db, desde, hasta);
		Map<Long,BodegaEmpresa> mapBodegas = BodegaEmpresa.mapAll(con, db);
		Map<Long,Cotizacion> mapCotizacion = Cotizacion.mapAllConOt(con, db);
		Map<Long,OtEstado> mapEstado = OtEstado.mapAll(con, db);
		Map<Long, Double> mapSaldos = CotizaDetalle.mapIdCotiVsSaldo(con, db);
		if(esPorSucursal.equals("1")) {
			listadoOt.forEach(x->{
				if(x.id_sucursal.toString().equals(id_sucursal)) {
					Cotizacion coti = mapCotizacion.get(x.getId_cotizacion());
    				String numCoti = "";
    				String fechaCoti = "";
    				Long id_bodegaEmpresa = (long)0;
    				String obsCoti = "";
    				String cotiPDF = "";
    				if(coti!=null) {
    					numCoti = coti.getNumero().toString();
    					fechaCoti = Fechas.AAMMDD(coti.getFecha());
    					id_bodegaEmpresa = coti.getId_bodegaEmpresa();
    					obsCoti = coti.getObservaciones();
    					cotiPDF = coti.getCotizacionPDF();
    				}
    				
    				BodegaEmpresa bodega = mapBodegas.get(id_bodegaEmpresa);
    				String nomCliente = "";
    				String nomProyecto = "";
    				String nomBodega = "";
    				if(bodega!=null) {
    					nomCliente = bodega.getNickCliente();
    					nomProyecto = bodega.getNickProyecto();
    					nomBodega = bodega.getNombre();
    				}
    				
    				OtEstado estado = mapEstado.get(x.getId_otEstado());
    				String nomEstado = "";
    				if(estado!=null) {
    					nomEstado = estado.getEstado();
    				}
    				
    				List<String> aux = new ArrayList<String>();
    				aux.add(x.getId().toString());				// 0 id_Ot
    				aux.add(x.getId_cotizacion().toString());	// 1 id_Cotizacion
    				aux.add(x.getNumero().toString());			// 2 numero de ot
    				aux.add(Fechas.AAMMDD(x.getFecha()));		// 3 fecha de ot
    				aux.add(numCoti);							// 4 numero de cotizacion
    				aux.add(fechaCoti);							// 5 fecha de cotizacion
    				aux.add(nomCliente);						// 6 nombre de cliente desde bodegaempresa
    				aux.add(nomProyecto);						// 7 nombre de proyecto desde bodegaempresa
    				aux.add(x.getObservaciones());				// 8 observaciones de ot
    				aux.add(obsCoti);							// 9 observaciones de cotizacion
    				aux.add(x.getOtPDF());						// 10 doc adjunto de ot
    				aux.add(cotiPDF);							// 11 doc adjunto de cotizacion
    				aux.add(nomEstado);							// 12 estado de la ot
    				aux.add(x.getFechaConfirmada());			// 13 fecha confirmado
    				aux.add(nomBodega);							// 14 nombre bodega empresa
    				aux.add(x.nameSucursal);					// 15 sucursal
    				aux.add(x.nameComercial);					// 16 comercial
    				aux.add(x.getFechaActualizacion());			// 17 getFechaActualizacion
    				aux.add(x.getFechaEnvio());					// 18 getFechaEnvio
    				aux.add(x.getFechaConfirmada());			// 19 fecha confirmada OT
    				Double saldo = mapSaldos.get(x.getId_cotizacion());
    				if(saldo!=null) {
    					aux.add(saldo.toString());		// 20 saldos por despachar
    				}else {
    					aux.add("---");		// 20 saldos por despachar
    				}
    				aux.add(x.getNotaOtEstado());			//21 NotaOtEstado
					listOt.add(aux);
				}
			});
		}else {
			listadoOt.forEach(x->{
				Cotizacion coti = mapCotizacion.get(x.getId_cotizacion());
				String numCoti = "";
				String fechaCoti = "";
				Long id_bodegaEmpresa = (long)0;
				String obsCoti = "";
				String cotiPDF = "";
				if(coti!=null) {
					numCoti = coti.getNumero().toString();
					fechaCoti = Fechas.AAMMDD(coti.getFecha());
					id_bodegaEmpresa = coti.getId_bodegaEmpresa();
					obsCoti = coti.getObservaciones();
					cotiPDF = coti.getCotizacionPDF();
				}
				
				BodegaEmpresa bodega = mapBodegas.get(id_bodegaEmpresa);
				String nomCliente = "";
				String nomProyecto = "";
				String nomBodega = "";
				if(bodega!=null) {
					nomCliente = bodega.getNickCliente();
					nomProyecto = bodega.getNickProyecto();
					nomBodega = bodega.getNombre();
				}
				
				OtEstado estado = mapEstado.get(x.getId_otEstado());
				String nomEstado = "";
				if(estado!=null) {
					nomEstado = estado.getEstado();
				}
				
				List<String> aux = new ArrayList<String>();
				aux.add(x.getId().toString());				// 0 id_Ot
				aux.add(x.getId_cotizacion().toString());	// 1 id_Cotizacion
				aux.add(x.getNumero().toString());			// 2 numero de ot
				aux.add(Fechas.AAMMDD(x.getFecha()));		// 3 fecha de ot
				aux.add(numCoti);							// 4 numero de cotizacion
				aux.add(fechaCoti);							// 5 fecha de cotizacion
				aux.add(nomCliente);						// 6 nombre de cliente desde bodegaempresa
				aux.add(nomProyecto);						// 7 nombre de proyecto desde bodegaempresa
				aux.add(x.getObservaciones());				// 8 observaciones de ot
				aux.add(obsCoti);							// 9 observaciones de cotizacion
				aux.add(x.getOtPDF());						// 10 doc adjunto de ot
				aux.add(cotiPDF);							// 11 doc adjunto de cotizacion
				aux.add(nomEstado);							// 12 estado de la ot
				aux.add(x.getFechaConfirmada());			// 13 fecha confirmado
				aux.add(nomBodega);							// 14 nombre bodega empresa
				aux.add(x.nameSucursal);					// 15 sucursal
				aux.add(x.nameComercial);					// 16 comercial
				aux.add(x.getFechaActualizacion());			// 17 getFechaActualizacion
				aux.add(x.getFechaEnvio());					// 18 getFechaEnvio
				aux.add(x.getFechaConfirmada());			// 19 fecha confirmada OT
				Double saldo = mapSaldos.get(x.getId_cotizacion());
				if(saldo!=null) {
					aux.add(saldo.toString());		// 20 saldos por despachar
				}else {
					aux.add("---");		// 20 saldos por despachar
				}
				aux.add(x.getNotaOtEstado());			//21 NotaOtEstado
				listOt.add(aux);
			});
		}
		
		return(listOt);
	}
	
	public static List<List<String>> listOtConfirma(Connection con, String db, String esPorSucursal, String id_sucursal){
		List<List<String>> listOt = new ArrayList<List<String>>();
		List<Ot> listadoOt = Ot.allxConfirmar(con, db);
		Map<Long,BodegaEmpresa> mapBodegas = BodegaEmpresa.mapAll(con, db);
		Map<Long,Cotizacion> mapCotizacion = Cotizacion.mapAllConOt(con, db);
		if(esPorSucursal.equals("1")) {
			listadoOt.forEach(x->{
				if(x.id_sucursal.toString().equals(id_sucursal)) {
					Cotizacion coti = mapCotizacion.get(x.getId_cotizacion());
    				String numCoti = "";
    				String fechaCoti = "";
    				Long id_bodegaEmpresa = (long)0;
    				String obsCoti = "";
    				String cotiPDF = "";
    				if(coti!=null) {
    					numCoti = coti.getNumero().toString();
    					fechaCoti = Fechas.AAMMDD(coti.getFecha());
    					id_bodegaEmpresa = coti.getId_bodegaEmpresa();
    					obsCoti = coti.getObservaciones();
    					cotiPDF = coti.getCotizacionPDF();
    				}
    				
    				BodegaEmpresa bodega = mapBodegas.get(id_bodegaEmpresa);
    				String nomCliente = "";
    				String nomProyecto = "";
    				String nomBodega = "";
    				if(bodega!=null) {
    					nomCliente = bodega.getNickCliente();
    					nomProyecto = bodega.getNickProyecto();
    					nomBodega = bodega.getNombre();
    				}
    				List<String> aux = new ArrayList<String>();
    				aux.add(x.getId().toString());				// 0 id_Ot
    				aux.add(x.getId_cotizacion().toString());	// 1 id_Cotizacion
    				aux.add(x.getNumero().toString());			// 2 numero de ot
    				aux.add(Fechas.AAMMDD(x.getFecha()));		// 3 fecha de ot
    				aux.add(numCoti);							// 4 numero de cotizacion
    				aux.add(fechaCoti);							// 5 fecha de cotizacion
    				aux.add(nomCliente);						// 6 nombre de cliente desde bodegaempresa
    				aux.add(nomProyecto);						// 7 nombre de proyecto desde bodegaempresa
    				aux.add(x.getObservaciones());				// 8 observaciones de ot
    				aux.add(obsCoti);							// 9 observaciones de cotizacion
    				aux.add(x.getOtPDF());						// 10 doc adjunto de ot
    				aux.add(cotiPDF);							// 11 doc adjunto de cotizacion
    				aux.add(nomBodega);							// 12 nombre bodega empresa
    				aux.add(x.nameSucursal);					// 13 sucursal
    				aux.add(x.nameComercial);					// 14 comercial
    				aux.add(x.getFechaActualizacion());			// 15 getFechaActualizacion
    				aux.add(x.getFechaEnvio());					// 16 getFechaEnvio
					listOt.add(aux);
				}
			});
		}else {
			listadoOt.forEach(x->{
				Cotizacion coti = mapCotizacion.get(x.getId_cotizacion());
				String numCoti = "";
				String fechaCoti = "";
				Long id_bodegaEmpresa = (long)0;
				String obsCoti = "";
				String cotiPDF = "";
				if(coti!=null) {
					numCoti = coti.getNumero().toString();
					fechaCoti = Fechas.AAMMDD(coti.getFecha());
					id_bodegaEmpresa = coti.getId_bodegaEmpresa();
					obsCoti = coti.getObservaciones();
					cotiPDF = coti.getCotizacionPDF();
				}
				
				BodegaEmpresa bodega = mapBodegas.get(id_bodegaEmpresa);
				String nomCliente = "";
				String nomProyecto = "";
				String nomBodega = "";
				if(bodega!=null) {
					nomCliente = bodega.getNickCliente();
					nomProyecto = bodega.getNickProyecto();
					nomBodega = bodega.getNombre();
				}
				List<String> aux = new ArrayList<String>();
				aux.add(x.getId().toString());				// 0 id_Ot
				aux.add(x.getId_cotizacion().toString());	// 1 id_Cotizacion
				aux.add(x.getNumero().toString());			// 2 numero de ot
				aux.add(Fechas.AAMMDD(x.getFecha()));		// 3 fecha de ot
				aux.add(numCoti);							// 4 numero de cotizacion
				aux.add(fechaCoti);							// 5 fecha de cotizacion
				aux.add(nomCliente);						// 6 nombre de cliente desde bodegaempresa
				aux.add(nomProyecto);						// 7 nombre de proyecto desde bodegaempresa
				aux.add(x.getObservaciones());				// 8 observaciones de ot
				aux.add(obsCoti);							// 9 observaciones de cotizacion
				aux.add(x.getOtPDF());						// 10 doc adjunto de ot
				aux.add(cotiPDF);							// 11 doc adjunto de cotizacion
				aux.add(nomBodega);							// 12 nombre bodega empresa
				aux.add(x.nameSucursal);					// 13 sucursal
				aux.add(x.nameComercial);					// 14 comercial
				aux.add(x.getFechaActualizacion());			// 15 getFechaActualizacion
				aux.add(x.getFechaEnvio());					// 16 getFechaEnvio
				listOt.add(aux);
			});
		}
		
		return(listOt);
	}
	
	public static List<List<String>> listOtAgregarOC(Connection con, String db, String esPorSucursal, String id_sucursal, String desde, String hasta){
		List<List<String>> listOt = new ArrayList<List<String>>();
		
		List<Cotizacion> listadoCoti = Cotizacion.allConfirmadasCon_o_SinOtDesdeHasta(con, db, desde, hasta);
		
		Map<Long,BodegaEmpresa> mapBodegas = BodegaEmpresa.mapAll(con, db);
		Map<Long,Ot> mapOt = Ot.mapAll(con, db);
		Map<Long,OtEstado> mapEstado = OtEstado.mapAll(con, db);
		if(esPorSucursal.equals("1")) {
			listadoCoti.forEach(x->{
				if(x.id_sucursal.toString().equals(id_sucursal)) {

    				String numCoti = x.getNumero().toString();
    				String fechaCoti = Fechas.AAMMDD(x.getFecha());
    				Long id_bodegaEmpresa = x.getId_bodegaEmpresa();
    				String obsCoti = x.getObservaciones();
    				String cotiPDF = x.getCotizacionPDF();
    				String cotiNumeroOC = x.getNumeroOC();
    				
    				BodegaEmpresa bodega = mapBodegas.get(id_bodegaEmpresa);
    				String nomCliente = "";
    				String nomProyecto = "";
    				String nomBodega = "";
    				if(bodega!=null) {
    					nomCliente = bodega.getNickCliente();
    					nomProyecto = bodega.getNickProyecto();
    					nomBodega = bodega.getNombre();
    				}
    				
    				Ot ot = mapOt.get(x.getId_ot());
    				String nomEstado = "";
    				String otPDF = "0";
    				if(ot!=null) {
    					OtEstado estado = mapEstado.get(ot.getId_otEstado());
        				if(estado!=null) {
        					nomEstado = estado.getEstado();
        				}
        				otPDF = ot.getOtPDF();
    				}
    				
    				
    				
    				List<String> aux = new ArrayList<String>();
    				aux.add(x.getId().toString());				// 0 id_Ot
    				aux.add(x.getId().toString());				// 1 id_Cotizacion
    				aux.add(x.getNumero().toString());			// 2 numero de ot
    				aux.add(Fechas.AAMMDD(x.getFecha()));		// 3 fecha de ot
    				aux.add(numCoti);							// 4 numero de cotizacion
    				aux.add(fechaCoti);							// 5 fecha de cotizacion
    				aux.add(nomCliente);						// 6 nombre de cliente desde bodegaempresa
    				aux.add(nomProyecto);						// 7 nombre de proyecto desde bodegaempresa
    				aux.add(x.getObservaciones());				// 8 observaciones de ot
    				aux.add(obsCoti);							// 9 observaciones de cotizacion
    				aux.add(otPDF);								// 10 doc adjunto de ot
    				aux.add(cotiPDF);							// 11 doc adjunto de cotizacion
    				aux.add(nomEstado);							// 12 estado de la ot
    				aux.add(x.getFechaConfirmada());			// 13 fecha confirmado
    				aux.add(nomBodega);							// 14 nombre bodega empresa
    				aux.add(x.getOcClientePDF());				// 15 orden de compra adjunta
    				aux.add(cotiNumeroOC);						// 16 numero de orden de compra del cliente
    				aux.add(x.nameSucursal);					// 17 sucursal
    				aux.add(x.nameComercial);					// 18 comercial
    				aux.add(x.getFechaConfirmada());			// 19 fecha confirmada OT
					listOt.add(aux);
				}
			});
		}else {
			listadoCoti.forEach(x->{

				String numCoti = x.getNumero().toString();
				String fechaCoti = Fechas.AAMMDD(x.getFecha());
				Long id_bodegaEmpresa = x.getId_bodegaEmpresa();
				String obsCoti = x.getObservaciones();
				String cotiPDF = x.getCotizacionPDF();
				String cotiNumeroOC = x.getNumeroOC();
				
				BodegaEmpresa bodega = mapBodegas.get(id_bodegaEmpresa);
				String nomCliente = "";
				String nomProyecto = "";
				String nomBodega = "";
				if(bodega!=null) {
					nomCliente = bodega.getNickCliente();
					nomProyecto = bodega.getNickProyecto();
					nomBodega = bodega.getNombre();
				}
				
				Ot ot = mapOt.get(x.getId_ot());
				String nomEstado = "";
				String otPDF = "0";
				if(ot!=null) {
					OtEstado estado = mapEstado.get(ot.getId_otEstado());
    				if(estado!=null) {
    					nomEstado = estado.getEstado();
    				}
    				otPDF = ot.getOtPDF();
				}
				
				List<String> aux = new ArrayList<String>();
				aux.add(x.getId().toString());				// 0 id_Ot
				aux.add(x.getId().toString());				// 1 id_Cotizacion
				aux.add(x.getNumero().toString());			// 2 numero de ot
				aux.add(Fechas.AAMMDD(x.getFecha()));		// 3 fecha de ot
				aux.add(numCoti);							// 4 numero de cotizacion
				aux.add(fechaCoti);							// 5 fecha de cotizacion
				aux.add(nomCliente);						// 6 nombre de cliente desde bodegaempresa
				aux.add(nomProyecto);						// 7 nombre de proyecto desde bodegaempresa
				aux.add(x.getObservaciones());				// 8 observaciones de ot
				aux.add(obsCoti);							// 9 observaciones de cotizacion
				aux.add(otPDF);								// 10 doc adjunto de ot
				aux.add(cotiPDF);							// 11 doc adjunto de cotizacion
				aux.add(nomEstado);							// 12 estado de la ot
				aux.add(x.getFechaConfirmada());			// 13 fecha confirmado
				aux.add(nomBodega);							// 14 nombre bodega empresa
				aux.add(x.getOcClientePDF());				// 15 orden de compra adjunta
				aux.add(cotiNumeroOC);						// 16 numero de orden de compra del cliente
				aux.add(x.nameSucursal);					// 17 sucursal
				aux.add(x.nameComercial);					// 18 comercial
				aux.add(x.getFechaConfirmada());			// 19 fecha confirmada OT
				listOt.add(aux);
			});
		}
		
		return(listOt);
	}
	
	public static List<List<String>> listOtDespachar(Connection con, String db, String esPorSucursal, String id_sucursal, String desde, String hasta){
		List<List<String>> listOt = new ArrayList<List<String>>();
		List<Ot> listadoOt = Ot.allConfirmadasDesdeHasta(con, db, desde, hasta);
		Map<Long,BodegaEmpresa> mapBodegas = BodegaEmpresa.mapAll(con, db);
		Map<Long,Cotizacion> mapCotizacion = Cotizacion.mapAllConOt(con, db);
		Map<Long,OtEstado> mapEstado = OtEstado.mapAll(con, db);
		
		if(esPorSucursal.equals("1")) {
			listadoOt.forEach(x->{
				if(x.id_sucursal.toString().equals(id_sucursal)) {
					if(x.id_otEstado == 1) {
    					Cotizacion coti = mapCotizacion.get(x.getId_cotizacion());
        				String numCoti = "";
        				String fechaCoti = "";
        				Long id_bodegaEmpresa = (long)0;
        				String obsCoti = "";
        				String cotiPDF = "";
        				if(coti!=null) {
        					numCoti = coti.getNumero().toString();
        					fechaCoti = Fechas.AAMMDD(coti.getFecha());
        					id_bodegaEmpresa = coti.getId_bodegaEmpresa();
        					obsCoti = coti.getObservaciones();
        					cotiPDF = coti.getCotizacionPDF();
        				}
        				
        				BodegaEmpresa bodega = mapBodegas.get(id_bodegaEmpresa);
        				String nomCliente = "";
        				String nomProyecto = "";
        				String nomBodega = "";
        				if(bodega!=null) {
        					nomCliente = bodega.getNickCliente();
        					nomProyecto = bodega.getNickProyecto();
        					nomBodega = bodega.getNombre();
        				}
        				
        				OtEstado estado = mapEstado.get(x.getId_otEstado());
        				String nomEstado = "";
        				if(estado!=null) {
        					nomEstado = estado.getEstado();
        				}
        				
        				List<String> aux = new ArrayList<String>();
        				aux.add(x.getId().toString());				// 0 id_Ot
        				aux.add(x.getId_cotizacion().toString());	// 1 id_Cotizacion
        				aux.add(x.getNumero().toString());			// 2 numero de ot
        				aux.add(Fechas.AAMMDD(x.getFecha()));		// 3 fecha de ot
        				aux.add(numCoti);							// 4 numero de cotizacion
        				aux.add(fechaCoti);							// 5 fecha de cotizacion
        				aux.add(nomCliente);						// 6 nombre de cliente desde bodegaempresa
        				aux.add(nomProyecto);						// 7 nombre de proyecto desde bodegaempresa
        				aux.add(x.getObservaciones());				// 8 observaciones de ot
        				aux.add(obsCoti);							// 9 observaciones de cotizacion
        				aux.add(x.getOtPDF());						// 10 doc adjunto de ot
        				aux.add(cotiPDF);							// 11 doc adjunto de cotizacion
        				aux.add(nomEstado);							// 12 estado de la ot
        				aux.add(x.getFechaConfirmada());			// 13 fecha confirmado
        				aux.add(nomBodega);							// 14 nombre bodega empresa
        				aux.add(x.nameSucursal);					// 15 sucursal
        				aux.add(x.nameComercial);					// 16 comercial
        				aux.add(x.getFechaActualizacion());			// 17 getFechaActualizacion
        				aux.add(x.getFechaEnvio());					// 18 getFechaEnvio
        				aux.add(x.getFechaConfirmada());			// 19 fecha confirmada OT
    					listOt.add(aux);
    				}
				}
			});
		}else {
			listadoOt.forEach(x->{
				if(x.id_otEstado == 1) {
					Cotizacion coti = mapCotizacion.get(x.getId_cotizacion());
    				String numCoti = "";
    				String fechaCoti = "";
    				Long id_bodegaEmpresa = (long)0;
    				String obsCoti = "";
    				String cotiPDF = "";
    				if(coti!=null) {
    					numCoti = coti.getNumero().toString();
    					fechaCoti = Fechas.AAMMDD(coti.getFecha());
    					id_bodegaEmpresa = coti.getId_bodegaEmpresa();
    					obsCoti = coti.getObservaciones();
    					cotiPDF = coti.getCotizacionPDF();
    				}
    				
    				BodegaEmpresa bodega = mapBodegas.get(id_bodegaEmpresa);
    				String nomCliente = "";
    				String nomProyecto = "";
    				String nomBodega = "";
    				if(bodega!=null) {
    					nomCliente = bodega.getNickCliente();
    					nomProyecto = bodega.getNickProyecto();
    					nomBodega = bodega.getNombre();
    				}
    				
    				OtEstado estado = mapEstado.get(x.getId_otEstado());
    				String nomEstado = "";
    				if(estado!=null) {
    					nomEstado = estado.getEstado();
    				}
    				
    				List<String> aux = new ArrayList<String>();
    				aux.add(x.getId().toString());				// 0 id_Ot
    				aux.add(x.getId_cotizacion().toString());	// 1 id_Cotizacion
    				aux.add(x.getNumero().toString());			// 2 numero de ot
    				aux.add(Fechas.AAMMDD(x.getFecha()));		// 3 fecha de ot
    				aux.add(numCoti);							// 4 numero de cotizacion
    				aux.add(fechaCoti);							// 5 fecha de cotizacion
    				aux.add(nomCliente);						// 6 nombre de cliente desde bodegaempresa
    				aux.add(nomProyecto);						// 7 nombre de proyecto desde bodegaempresa
    				aux.add(x.getObservaciones());				// 8 observaciones de ot
    				aux.add(obsCoti);							// 9 observaciones de cotizacion
    				aux.add(x.getOtPDF());						// 10 doc adjunto de ot
    				aux.add(cotiPDF);							// 11 doc adjunto de cotizacion
    				aux.add(nomEstado);							// 12 estado de la ot
    				aux.add(x.getFechaConfirmada());			// 13 fecha confirmado
    				aux.add(nomBodega);							// 14 nombre bodega empresa
    				aux.add(x.nameSucursal);					// 15 sucursal
    				aux.add(x.nameComercial);					// 16 comercial
    				aux.add(x.getFechaActualizacion());			// 17 getFechaActualizacion
    				aux.add(x.getFechaEnvio());					// 18 getFechaEnvio
    				aux.add(x.getFechaConfirmada());			// 19 fecha confirmada OT
					listOt.add(aux);
				}
			});
		}
		
		return(listOt);
	}
	
	
	
	
	
	
	
	
	
}
