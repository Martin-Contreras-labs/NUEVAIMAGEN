package models.tables;

import controllers.HomeController;
import models.api.*;
import models.forms.FormFactura;
import models.utilities.Archivos;
import models.utilities.DecimalFormato;
import models.utilities.Fechas;
import models.xml.XmlFacturaReferencias;
import models.xml.XmlFacturaVenta;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.TempFile;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.ws.WSClient;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CobraArriendoEstados {
	Long id_movimiento;
	Long id_guia;
	Long id_bodegaEmpresa;
	Long id_equipo;
	Long id_unidad;
	Long id_cotizacion;
	Long id_sucursal;
	Long id_grupo;
	String nombreBodega;
	Long nroCoti;
	String codigo;
	String nombreEquipo;
	Long numGuia;
	String numGuiaCliente;
	String fecha;
	Double cantidad;

	String nombreSucursal;
	String nombreGrupo;
	Long id_moneda;
	String nickMoneda;
	Double puReposicion;
	Double puArriendoDia;

	Long cobraArriendo;
	String fechaIniTerGuia;


	public CobraArriendoEstados(Long id_movimiento, Long id_guia, Long id_bodegaEmpresa, Long id_equipo, Long id_unidad, Long id_cotizacion,
								Long id_sucursal, Long id_grupo, String nombreBodega, Long nroCoti, String codigo, String nombreEquipo, Long numGuia,
								String numGuiaCliente, String fecha, Double cantidad, String nombreSucursal, String nombreGrupo, Long id_moneda,
								String nickMoneda, Double puArriendoDia, Double puReposicion, Long cobraArriendo, String fechaIniTerGuia) {
		super();
		this.id_movimiento = id_movimiento;
		this.id_guia = id_guia;
		this.id_bodegaEmpresa = id_bodegaEmpresa;
		this.id_equipo = id_equipo;
		this.id_unidad = id_unidad;
		this.id_cotizacion = id_cotizacion;
		this.id_sucursal = id_sucursal;
		this.id_grupo = id_grupo;
		this.nombreBodega = nombreBodega;
		this.nroCoti = nroCoti;
		this.codigo = codigo;
		this.nombreEquipo = nombreEquipo;
		this.numGuia = numGuia;
		this.numGuiaCliente = numGuiaCliente;
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.nombreSucursal = nombreSucursal;
		this.nombreGrupo = nombreGrupo;
		this.id_moneda = id_moneda;
		this.nickMoneda = nickMoneda;
		this.puReposicion = puReposicion;
		this.puArriendoDia = puArriendoDia;
		this.cobraArriendo = cobraArriendo;
		this.fechaIniTerGuia = fechaIniTerGuia;
	}

	public CobraArriendoEstados() {
			super();
	}

	public Long getId_movimiento() {
		return id_movimiento;
	}

	public void setId_movimiento(Long id_movimiento) {
		this.id_movimiento = id_movimiento;
	}

	public Long getId_guia() {
		return id_guia;
	}

	public void setId_guia(Long id_guia) {
		this.id_guia = id_guia;
	}

	public Long getId_bodegaEmpresa() {
		return id_bodegaEmpresa;
	}

	public void setId_bodegaEmpresa(Long id_bodegaEmpresa) {
		this.id_bodegaEmpresa = id_bodegaEmpresa;
	}

	public Long getId_equipo() {
		return id_equipo;
	}

	public void setId_equipo(Long id_equipo) {
		this.id_equipo = id_equipo;
	}

	public Long getId_unidad() {
		return id_unidad;
	}

	public void setId_unidad(Long id_unidad) {
		this.id_unidad = id_unidad;
	}

	public Long getId_cotizacion() {
		return id_cotizacion;
	}

	public void setId_cotizacion(Long id_cotizacion) {
		this.id_cotizacion = id_cotizacion;
	}

	public Long getId_sucursal() {
		return id_sucursal;
	}

	public void setId_sucursal(Long id_sucursal) {
		this.id_sucursal = id_sucursal;
	}

	public Long getId_grupo() {
		return id_grupo;
	}

	public void setId_grupo(Long id_grupo) {
		this.id_grupo = id_grupo;
	}

	public String getNombreBodega() {
		return nombreBodega;
	}

	public void setNombreBodega(String nombreBodega) {
		this.nombreBodega = nombreBodega;
	}

	public Long getNroCoti() {
		return nroCoti;
	}

	public void setNroCoti(Long nroCoti) {
		this.nroCoti = nroCoti;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombreEquipo() {
		return nombreEquipo;
	}

	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}

	public Long getNumGuia() {
		return numGuia;
	}

	public void setNumGuia(Long numGuia) {
		this.numGuia = numGuia;
	}

	public String getNumGuiaCliente() {
		return numGuiaCliente;
	}

	public void setNumGuiaCliente(String numGuiaCliente) {
		this.numGuiaCliente = numGuiaCliente;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public String getNombreSucursal() {
		return nombreSucursal;
	}

	public void setNombreSucursal(String nombreSucursal) {
		this.nombreSucursal = nombreSucursal;
	}

	public String getNombreGrupo() {
		return nombreGrupo;
	}

	public void setNombreGrupo(String nombreGrupo) {
		this.nombreGrupo = nombreGrupo;
	}

	public Long getId_moneda() {
		return id_moneda;
	}

	public void setId_moneda(Long id_moneda) {
		this.id_moneda = id_moneda;
	}

	public String getNickMoneda() {
		return nickMoneda;
	}

	public void setNickMoneda(String nickMoneda) {
		this.nickMoneda = nickMoneda;
	}

	public Double getPuReposicion() {
		return puReposicion;
	}

	public void setPuReposicion(Double puReposicion) {
		this.puReposicion = puReposicion;
	}

	public Double getPuArriendoDia() {
		return puArriendoDia;
	}

	public void setPuArriendoDia(Double puArriendoDia) {
		this.puArriendoDia = puArriendoDia;
	}

	public Long getCobraArriendo() {
		return cobraArriendo;
	}

	public void setCobraArriendo(Long cobraArriendo) {
		this.cobraArriendo = cobraArriendo;
	}

	public String getFechaIniTerGuia() {
		return fechaIniTerGuia;
	}

	public void setFechaIniTerGuia(String fechaIniTerGuia) {
		this.fechaIniTerGuia = fechaIniTerGuia;
	}

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	public static List<CobraArriendoEstados> all (Connection con, String db) {
		List<CobraArriendoEstados> lista = new ArrayList<CobraArriendoEstados>();
		String query = String.format("select" +
						" estadoEquipo.id_movimiento," +
						" estadoEquipo.id_guia," +
						" movimiento.id_bodegaEmpresa," +
						" movimiento.id_equipo," +
						" equipo.id_unidad," +
						" movimiento.id_cotizacion," +
						" bodegaEmpresa.id_sucursal," +
						" equipo.id_grupo," +
						" bodegaEmpresa.nombre, " +
						" ifnull(cotizacion.numero,'') as nroCoti," +
						" equipo.codigo," +
						" equipo.nombre, " +
						" guia.numero," +
						" guia.numGuiaCliente," +
						" guia.fecha," +
						" sum(estadoEquipo.cantidad)," +
						" estadoEquipo.cobraArriendo," +
						" ifnull(guia.fechaIniTerGuia,guia.fecha) as fechaIniTerGuia" +
						" from `%s`.estadoEquipo" +
						" left join `%s`.movimiento on movimiento.id = estadoEquipo.id_movimiento" +
						" left join `%s`.tipoEstado on tipoEstado.id = estadoEquipo.id_tipoEstado" +
						" left join `%s`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa" +
						" left join `%s`.guia on guia.id = movimiento.id_guia" +
						" left join `%s`.equipo on equipo.id = movimiento.id_equipo" +
						" left join `%s`.cotizacion on cotizacion.id = movimiento.id_cotizacion" +
						" where tipoEstado.cobraArriendo = 1 and bodegaEmpresa.vigente = 1 and bodegaEmpresa.esInterna = 2 and movimiento.id_guia > 0" +
						" group by estadoEquipo.id_guia, movimiento.id_equipo, movimiento.id_cotizacion, movimiento.id_bodegaEmpresa" +
						" having sum(estadoEquipo.cantidad)>0;",db,db,db,db,db,db,db);
		try (PreparedStatement smt = con.prepareStatement(query);
			 ResultSet rs = smt.executeQuery()){
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con,db);
			Map<Long,Grupo> mapGrupo = Grupo.mapAll(con,db);
			Map<String,List<Double>> mapPrecio = ListaPrecio.mapListaPreciosAll(con,db);
			while (rs.next()) {
				Long id_movimiento = rs.getLong(1);
				Long id_guia = rs.getLong(2);
				Long id_bodegaEmpresa = rs.getLong(3);
				Long id_equipo = rs.getLong(4);
				Long id_unidad = rs.getLong(5);
				Long id_cotizacion = rs.getLong(6);
				Long id_sucursal = rs.getLong(7);
				Long id_grupo = rs.getLong(8);
				String nombreBodega = rs.getString(9);
				Long nroCoti = rs.getLong(10);
				String codigo = rs.getString(11);
				String nombreEquipo = rs.getString(12);
				Long numGuia = rs.getLong(13);
				String numGuiaCliente = rs.getString(14);
				String fecha = rs.getString(15);
				Double cantidad = rs.getDouble(16);
				Long cobraArriendo = rs.getLong(17);
				String fechaIniTerGuia = rs.getString(18);

				String nombreSucursal = mapSucursal.get(id_sucursal) != null ? mapSucursal.get(id_sucursal).nombre : "";
				String nombreGrupo = mapGrupo.get(id_grupo) != null ? mapGrupo.get(id_grupo).nombre : "";

				// key = id_bodegaEmpresa + id_cotizacion + id_equipo
				List<Double> listPrecio = mapPrecio.get(id_bodegaEmpresa+"_"+id_cotizacion+"_"+id_equipo);
				Long id_moneda = 1L;
				String nickMoneda = Moneda.find(con,db,id_moneda).getNickName();
				Double puReposicion = (double) 0;
				Double puArriendoDia = (double) 0;
				if (listPrecio != null && listPrecio.size() > 0) {
					puReposicion = listPrecio.get(0);
					puArriendoDia = listPrecio.get(1)/listPrecio.get(4).doubleValue();
					id_moneda = listPrecio.get(3).longValue();
				}


				lista.add(new CobraArriendoEstados(id_movimiento,id_guia,id_bodegaEmpresa,id_equipo,id_unidad,id_cotizacion,id_sucursal,id_grupo,nombreBodega,nroCoti,codigo,nombreEquipo,
						numGuia,numGuiaCliente,fecha,cantidad,nombreSucursal,nombreGrupo,id_moneda,nickMoneda,puArriendoDia,puReposicion, cobraArriendo, fechaIniTerGuia));
			}
		} catch (SQLException e) {
			String className = CobraArriendoEstados.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (lista);
	}

	public static List<CobraArriendoEstados> allAgrupadoPorBodega (Connection con, String db) {
		List<CobraArriendoEstados> lista = new ArrayList<CobraArriendoEstados>();
		String query = String.format("select" +
				" estadoEquipo.id_movimiento," +
				" estadoEquipo.id_guia," +
				" movimiento.id_bodegaEmpresa," +
				" movimiento.id_equipo," +
				" equipo.id_unidad," +
				" movimiento.id_cotizacion," +
				" bodegaEmpresa.id_sucursal," +
				" equipo.id_grupo," +
				" bodegaEmpresa.nombre, " +
				" ifnull(cotizacion.numero,'') as nroCoti," +
				" equipo.codigo," +
				" equipo.nombre, " +
				" guia.numero," +
				" guia.numGuiaCliente," +
				" guia.fecha," +
				" sum(estadoEquipo.cantidad)," +
				" estadoEquipo.cobraArriendo," +
				" ifnull(guia.fechaIniTerGuia,guia.fecha) as fechaIniTerGuia" +
				" from `%s`.estadoEquipo" +
				" left join `%s`.movimiento on movimiento.id = estadoEquipo.id_movimiento" +
				" left join `%s`.tipoEstado on tipoEstado.id = estadoEquipo.id_tipoEstado" +
				" left join `%s`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa" +
				" left join `%s`.guia on guia.id = movimiento.id_guia" +
				" left join `%s`.equipo on equipo.id = movimiento.id_equipo" +
				" left join `%s`.cotizacion on cotizacion.id = movimiento.id_cotizacion" +
				" where tipoEstado.cobraArriendo = 1 and bodegaEmpresa.vigente = 1 and bodegaEmpresa.esInterna = 2 and movimiento.id_guia > 0" +
				" group by movimiento.id_bodegaEmpresa" +
				" having sum(estadoEquipo.cantidad)>0;",db,db,db,db,db,db,db);
		try (PreparedStatement smt = con.prepareStatement(query);
			 ResultSet rs = smt.executeQuery()){
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con,db);
			Map<Long,Grupo> mapGrupo = Grupo.mapAll(con,db);
			Map<String,List<Double>> mapPrecio = ListaPrecio.mapListaPreciosAll(con,db);
			while (rs.next()) {
				Long id_movimiento = rs.getLong(1);
				Long id_guia = rs.getLong(2);
				Long id_bodegaEmpresa = rs.getLong(3);
				Long id_equipo = rs.getLong(4);
				Long id_unidad = rs.getLong(5);
				Long id_cotizacion = rs.getLong(6);
				Long id_sucursal = rs.getLong(7);
				Long id_grupo = rs.getLong(8);
				String nombreBodega = rs.getString(9);
				Long nroCoti = rs.getLong(10);
				String codigo = rs.getString(11);
				String nombreEquipo = rs.getString(12);
				Long numGuia = rs.getLong(13);
				String numGuiaCliente = rs.getString(14);
				String fecha = rs.getString(15);
				Double cantidad = rs.getDouble(16);
				Long cobraArriendo = rs.getLong(17);
				String fechaIniTerGuia = rs.getString(18);

				String nombreSucursal = mapSucursal.get(id_sucursal) != null ? mapSucursal.get(id_sucursal).nombre : "";
				String nombreGrupo = mapGrupo.get(id_grupo) != null ? mapGrupo.get(id_grupo).nombre : "";

				// key = id_bodegaEmpresa + id_cotizacion + id_equipo
				List<Double> listPrecio = mapPrecio.get(id_bodegaEmpresa+"_"+id_cotizacion+"_"+id_equipo);
				Long id_moneda = 1L;
				String nickMoneda = Moneda.find(con,db,id_moneda).getNickName();
				Double puReposicion = (double) 0;
				Double puArriendoDia = (double) 0;
				if (listPrecio != null && listPrecio.size() > 0) {
					puReposicion = listPrecio.get(0);
					puArriendoDia = listPrecio.get(1)/listPrecio.get(4).doubleValue();
					id_moneda = listPrecio.get(3).longValue();
				}


				lista.add(new CobraArriendoEstados(id_movimiento,id_guia,id_bodegaEmpresa,id_equipo,id_unidad,id_cotizacion,id_sucursal,id_grupo,nombreBodega,nroCoti,codigo,nombreEquipo,
						numGuia,numGuiaCliente,fecha,cantidad,nombreSucursal,nombreGrupo,id_moneda,nickMoneda,puArriendoDia,puReposicion, cobraArriendo, fechaIniTerGuia));
			}
		} catch (SQLException e) {
			String className = CobraArriendoEstados.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (lista);
	}

	public static List<CobraArriendoEstados> allPorIdBodega (Connection con, String db, Long id_bodegaEmpresa) {
		List<CobraArriendoEstados> lista = new ArrayList<CobraArriendoEstados>();
		String query = String.format("select" +
				" estadoEquipo.id_movimiento," +
				" estadoEquipo.id_guia," +
				" movimiento.id_bodegaEmpresa," +
				" movimiento.id_equipo," +
				" equipo.id_unidad," +
				" movimiento.id_cotizacion," +
				" bodegaEmpresa.id_sucursal," +
				" equipo.id_grupo," +
				" bodegaEmpresa.nombre, " +
				" ifnull(cotizacion.numero,'') as nroCoti," +
				" equipo.codigo," +
				" equipo.nombre, " +
				" guia.numero," +
				" guia.numGuiaCliente," +
				" guia.fecha," +
				" sum(estadoEquipo.cantidad)," +
				" estadoEquipo.cobraArriendo," +
				" ifnull(guia.fechaIniTerGuia,guia.fecha) as fechaIniTerGuia" +
				" from `%s`.estadoEquipo" +
				" left join `%s`.movimiento on movimiento.id = estadoEquipo.id_movimiento" +
				" left join `%s`.tipoEstado on tipoEstado.id = estadoEquipo.id_tipoEstado" +
				" left join `%s`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa" +
				" left join `%s`.guia on guia.id = movimiento.id_guia" +
				" left join `%s`.equipo on equipo.id = movimiento.id_equipo" +
				" left join `%s`.cotizacion on cotizacion.id = movimiento.id_cotizacion" +
				" where tipoEstado.cobraArriendo = 1 and bodegaEmpresa.vigente = 1 and bodegaEmpresa.esInterna = 2 and movimiento.id_guia > 0  and movimiento.id_bodegaEmpresa = "+ id_bodegaEmpresa +
				" group by estadoEquipo.id_guia, movimiento.id_equipo, movimiento.id_cotizacion, movimiento.id_bodegaEmpresa" +
				" having sum(estadoEquipo.cantidad)>0;",db,db,db,db,db,db,db);

		try (PreparedStatement smt = con.prepareStatement(query);
			 ResultSet rs = smt.executeQuery()){
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con,db);
			Map<Long,Grupo> mapGrupo = Grupo.mapAll(con,db);
			Map<String,List<Double>> mapPrecio = ListaPrecio.mapListaPreciosAll(con,db);
			while (rs.next()) {
				Long id_movimiento = rs.getLong(1);
				Long id_guia = rs.getLong(2);
				id_bodegaEmpresa = rs.getLong(3);
				Long id_equipo = rs.getLong(4);
				Long id_unidad = rs.getLong(5);
				Long id_cotizacion = rs.getLong(6);
				Long id_sucursal = rs.getLong(7);
				Long id_grupo = rs.getLong(8);
				String nombreBodega = rs.getString(9);
				Long nroCoti = rs.getLong(10);
				String codigo = rs.getString(11);
				String nombreEquipo = rs.getString(12);
				Long numGuia = rs.getLong(13);
				String numGuiaCliente = rs.getString(14);
				String fecha = rs.getString(15);
				Double cantidad = rs.getDouble(16);
				Long cobraArriendo = rs.getLong(17);
				String fechaIniTerGuia = rs.getString(18);

				String nombreSucursal = mapSucursal.get(id_sucursal) != null ? mapSucursal.get(id_sucursal).nombre : "";
				String nombreGrupo = mapGrupo.get(id_grupo) != null ? mapGrupo.get(id_grupo).nombre : "";

				// key = id_bodegaEmpresa + id_cotizacion + id_equipo
				List<Double> listPrecio = mapPrecio.get(id_bodegaEmpresa+"_"+id_cotizacion+"_"+id_equipo);
				Long id_moneda = 1L;
				String nickMoneda = Moneda.find(con,db,id_moneda).getNickName();
				Double puReposicion = (double) 0;
				Double puArriendoDia = (double) 0;
				if (listPrecio != null && listPrecio.size() > 0) {
					puReposicion = listPrecio.get(0);
					puArriendoDia = listPrecio.get(1)/listPrecio.get(4).doubleValue();
					id_moneda = listPrecio.get(3).longValue();
				}


				lista.add(new CobraArriendoEstados(id_movimiento,id_guia,id_bodegaEmpresa,id_equipo,id_unidad,id_cotizacion,id_sucursal,id_grupo,nombreBodega,nroCoti,codigo,nombreEquipo,
						numGuia,numGuiaCliente,fecha,cantidad,nombreSucursal,nombreGrupo,id_moneda,nickMoneda,puArriendoDia,puReposicion, cobraArriendo, fechaIniTerGuia));
			}
		} catch (SQLException e) {
			String className = CobraArriendoEstados.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (lista);
	}

	public static boolean modificaCobraArriendo (Connection con, String db, Long id_movimiento, Long valor) {
		String query = String.format("update `%s`.estadoEquipo set cobraArriendo = ? where id_movimiento = ?;",db);
		try (PreparedStatement smt = con.prepareStatement(query)){
			smt.setLong(1, valor);
			smt.setLong(2, id_movimiento);
			smt.executeUpdate();
			return (true);
		} catch (SQLException e) {
			String className = CobraArriendoEstados.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (false);
	}


	public static List<List<String>> calc_resumen (Connection con, String db, Fechas desde, Fechas hasta, List<CobraArriendoEstados> listCobraArriendoEstados, Map<Long,Double> tasas, Long nroDec) {
		List<List<String>> lista = new ArrayList<List<String>>();
		int diasPeriodo = Fechas.diasEntreFechas(desde.getFechaCal(), hasta.getFechaCal()) + 1;
		Map<Long,List<String>> map = new HashMap<Long,List<String>>();
		listCobraArriendoEstados.forEach(x -> {
			if(x.getCobraArriendo() > 0) {
				Double puArrDiaMO = x.getPuArriendoDia();
				Long id_moneda = x.getId_moneda();
				Double cantidad = x.getCantidad();
				Double tasa = tasas.get(id_moneda) != null ? tasas.get(id_moneda) : 1.0;
				Fechas fechaGuia = Fechas.obtenerFechaDesdeStrAAMMDD(x.getFechaIniTerGuia());
				Double total = puArrDiaMO * cantidad * tasa * diasPeriodo;
				if(fechaGuia.getFechaCal().after(desde.getFechaCal()) && fechaGuia.getFechaCal().before(hasta.getFechaCal())) {
					int auxDias = Fechas.diasEntreFechas(fechaGuia.getFechaCal(), hasta.getFechaCal());
					total = puArrDiaMO * cantidad * tasa * auxDias;
				}
				List<String> aux = map.get(x.getId_bodegaEmpresa());
				List<String> l = new ArrayList<String>();
				l.add(x.getNombreSucursal());			// 0
				l.add(x.getNombreBodega());				// 1
				if(aux == null) {
					l.add(DecimalFormato.formato(total, nroDec));	//2
					map.put(x.getId_bodegaEmpresa(), l);
				}else{
					Double valor = Double.parseDouble(aux.get(2).replaceAll(",", "").trim());
					l.add(DecimalFormato.formato(total + valor, nroDec));	//2
					map.put(x.getId_bodegaEmpresa(), l);
				}
			}
		});
		for(Map.Entry<Long,List<String>> entry : map.entrySet()) {
			List<String> l = new ArrayList<String>();
			l.add(entry.getKey().toString());	// 0
			l.add(entry.getValue().get(0));		// 1
			l.add(entry.getValue().get(1));		// 2
			l.add(entry.getValue().get(2));		// 3
			lista.add(l);
		}
		return (lista);
	}

	public static List<List<String>> calc_detalle (Connection con, String db, Fechas desde, Fechas hasta, List<CobraArriendoEstados> listCobraArriendoEstados, Map<Long,Double> tasas, Long nroDec, Long id_bodegaEmpresa) {
		List<List<String>> lista = new ArrayList<List<String>>();
		int diasPeriodo = Fechas.diasEntreFechas(desde.getFechaCal(), hasta.getFechaCal()) + 1;
		listCobraArriendoEstados.forEach(x -> {
			if(x.getId_bodegaEmpresa() - id_bodegaEmpresa == 0 && x.getCobraArriendo() > 0) {
				Double puArrDiaMO = x.getPuArriendoDia();
				Long id_moneda = x.getId_moneda();
				Double cantidad = x.getCantidad();
				Double tasa = tasas.get(id_moneda) != null ? tasas.get(id_moneda) : 1.0;
				Double puArrDiaMP = x.getPuArriendoDia() * tasa;
				Fechas fechaGuia = Fechas.obtenerFechaDesdeStrAAMMDD(x.getFechaIniTerGuia());
				Double total = puArrDiaMO * cantidad * tasa * diasPeriodo;
				int dias = diasPeriodo;
				if(fechaGuia.getFechaCal().after(desde.getFechaCal()) && fechaGuia.getFechaCal().before(hasta.getFechaCal())) {
					dias = Fechas.diasEntreFechas(fechaGuia.getFechaCal(), hasta.getFechaCal());
					total = puArrDiaMO * cantidad * tasa * dias;
				}
				List<String> aux = new ArrayList<String>();
				aux.add(x.getId_movimiento().toString());
				aux.add(x.getNombreGrupo());
				aux.add(x.getNroCoti().toString());
				aux.add(x.getCodigo());
				aux.add(x.getNombreEquipo());
				aux.add(x.getFecha());
				aux.add(x.getNumGuia().toString());
				aux.add(x.getNumGuiaCliente());
				aux.add(DecimalFormato.formato((double) dias, 0L));
				aux.add(DecimalFormato.formato(puArrDiaMP, nroDec));
				aux.add(DecimalFormato.formato(cantidad, 2L));
				aux.add(DecimalFormato.formato(total, nroDec));
				if(total > 0){
					lista.add(aux);
				}
			}
		});
		return (lista);
	}

	public static File estadosFacturaProyectoExcel(String db, Map<String,String> mapDiccionario, List<List<String>> lista, Fechas desde, Fechas hasta) {
		File tmp = TempFile.createTempFile("tmp","null");

		try {
			String path = "formatos/excel.xlsx";
			InputStream formato = Archivos.leerArchivo(path);
			Workbook libro = WorkbookFactory.create(formato);
			formato.close();

			// 0 negro 1 blanco 2 rojo 3 verde 4 azul 5 amarillo 19 celeste
			CellStyle titulo = libro.createCellStyle();
			Font font = libro.createFont();
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			font.setColor((short)4);
			font.setFontHeight((short)(14*20));
			titulo.setFont(font);

			CellStyle subtitulo = libro.createCellStyle();
			Font font2 = libro.createFont();
			font2.setBoldweight(Font.BOLDWEIGHT_BOLD);
			font2.setColor((short)0);
			font2.setFontHeight((short)(12*20));
			subtitulo.setFont(font2);

			CellStyle encabezado = libro.createCellStyle();
			encabezado.setBorderBottom(CellStyle.BORDER_THIN);
			encabezado.setBorderTop(CellStyle.BORDER_THIN);
			encabezado.setBorderRight(CellStyle.BORDER_THIN);
			encabezado.setBorderLeft(CellStyle.BORDER_THIN);
			encabezado.setFillPattern(CellStyle.SOLID_FOREGROUND);
			encabezado.setFillForegroundColor((short)19);
			encabezado.setAlignment(CellStyle.ALIGN_LEFT);

			CellStyle detalle = libro.createCellStyle();
			detalle.setBorderBottom(CellStyle.BORDER_THIN);
			detalle.setBorderTop(CellStyle.BORDER_THIN);
			detalle.setBorderRight(CellStyle.BORDER_THIN);
			detalle.setBorderLeft(CellStyle.BORDER_THIN);

			CellStyle pie = libro.createCellStyle();
			pie.setBorderBottom(CellStyle.BORDER_THIN);
			pie.setBorderTop(CellStyle.BORDER_THIN);
			pie.setBorderRight(CellStyle.BORDER_THIN);
			pie.setBorderLeft(CellStyle.BORDER_THIN);
			pie.setFillPattern(CellStyle.SOLID_FOREGROUND);
			pie.setFillForegroundColor((short)19);
			pie.setAlignment(CellStyle.ALIGN_RIGHT);


			//titulos del archivo

			libro.setSheetName(0, "LISTADO");
			Sheet hoja1 = libro.getSheetAt(0);

			Row row = null;
			Cell cell = null;

			row = hoja1.createRow(1);
			cell = row.createCell(1);
			cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("LISTADO DE EP ESTADO EQUIPO COBRAR "+mapDiccionario.get("ARRIENDO")+" POR DAÑOS");

			row = hoja1.createRow(2);
			cell = row.createCell(1);
			cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("EMPRESA: "+mapDiccionario.get("nEmpresa"));

			row = hoja1.createRow(3);
			cell = row.createCell(1);
			cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FECHA: "+Fechas.hoy().getFechaStrDDMMAA());

			row = hoja1.createRow(5);
			cell = row.createCell(1);
			cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("PERIODO: desde " + desde.getFechaStrDDMMAA()  + " hasta " + hasta.getFechaStrDDMMAA());


			//anchos de columnas
			hoja1.setColumnWidth(1, 6*1000);
			hoja1.setColumnWidth(2, 20*1000);
			hoja1.setColumnWidth(3, 4*1000);

			//INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
			InputStream x = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
			byte[] bytes = IOUtils.toByteArray(x);
			x.close();
			int pngIndex = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			Drawing draw = hoja1.createDrawingPatriarch();
			CreationHelper helper = libro.getCreationHelper();
			ClientAnchor anchor = helper.createClientAnchor();
			//set top-left corner for the image
			anchor.setCol1(9);
			anchor.setRow1(1);
			Picture img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);


			// encabezado de la tabla

			int posRow = 8;

			row = hoja1.createRow(posRow);
			int posCell = 0;

			row = hoja1.createRow(posRow);

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("SUCURSAL");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("NOMBRE "+mapDiccionario.get("BODEGA")+"/PROYECTO");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TOTAL (en "+mapDiccionario.get("Pesos")+")");

			Double totaltotal=(double)0;
			for(int i=0; i<lista.size(); i++){
				posRow++;
				posCell = 0;
				row = hoja1.createRow(posRow);

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(1));

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(2));

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				Double aux = Double.parseDouble(lista.get(i).get(3).replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);
				totaltotal += aux;
			}

			posRow++;
			posCell = 0;
			row = hoja1.createRow(posRow);

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TOTAL");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(pie);
			Double aux = totaltotal;
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			cell.setCellValue(aux);

			posRow = posRow + 5;
			row = hoja1.createRow(posRow);
			cell = row.createCell(1);
			Hyperlink hiper = helper.createHyperlink(0);
			hiper.setAddress("https://www.inqsol.cl");
			cell.setHyperlink(hiper);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Documento generado desde MADA propiedad de INQSOL");


			// Write the output to a file tmp
			FileOutputStream fileOut = new FileOutputStream(tmp);
			libro.write(fileOut);
			fileOut.close();


		} catch (Exception e) {
			e.printStackTrace();
		}

		return tmp;

	}

	public static File estadosFacturaProyectoDetExcel(String db, Map<String,String> mapDiccionario,
				  List<List<String>> lista, Fechas desde, Fechas hasta, BodegaEmpresa bodegaEmpresa, Fechas hoy) {
		File tmp = TempFile.createTempFile("tmp","null");
		try {
			String path = "formatos/excel.xlsx";
			InputStream formato = Archivos.leerArchivo(path);
			Workbook libro = WorkbookFactory.create(formato);
			formato.close();

			// 0 negro 1 blanco 2 rojo 3 verde 4 azul 5 amarillo 19 celeste
			CellStyle titulo = libro.createCellStyle();
			Font font = libro.createFont();
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			font.setColor((short)4);
			font.setFontHeight((short)(14*20));
			titulo.setFont(font);

			CellStyle subtitulo = libro.createCellStyle();
			Font font2 = libro.createFont();
			font2.setBoldweight(Font.BOLDWEIGHT_BOLD);
			font2.setColor((short)0);
			font2.setFontHeight((short)(12*20));
			subtitulo.setFont(font2);

			CellStyle encabezado = libro.createCellStyle();
			encabezado.setBorderBottom(CellStyle.BORDER_THIN);
			encabezado.setBorderTop(CellStyle.BORDER_THIN);
			encabezado.setBorderRight(CellStyle.BORDER_THIN);
			encabezado.setBorderLeft(CellStyle.BORDER_THIN);
			encabezado.setFillPattern(CellStyle.SOLID_FOREGROUND);
			encabezado.setFillForegroundColor((short)19);
			encabezado.setAlignment(CellStyle.ALIGN_LEFT);

			CellStyle detalle = libro.createCellStyle();
			detalle.setBorderBottom(CellStyle.BORDER_THIN);
			detalle.setBorderTop(CellStyle.BORDER_THIN);
			detalle.setBorderRight(CellStyle.BORDER_THIN);
			detalle.setBorderLeft(CellStyle.BORDER_THIN);

			CellStyle pie = libro.createCellStyle();
			pie.setBorderBottom(CellStyle.BORDER_THIN);
			pie.setBorderTop(CellStyle.BORDER_THIN);
			pie.setBorderRight(CellStyle.BORDER_THIN);
			pie.setBorderLeft(CellStyle.BORDER_THIN);
			pie.setFillPattern(CellStyle.SOLID_FOREGROUND);
			pie.setFillForegroundColor((short)19);
			pie.setAlignment(CellStyle.ALIGN_RIGHT);


			//titulos del archivo

			libro.setSheetName(0, "LISTADO");
			Sheet hoja1 = libro.getSheetAt(0);

			Row row = null;
			Cell cell = null;

			row = hoja1.createRow(1);
			cell = row.createCell(1);
			cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("DETALLE EP ESTADO EQUIPO COBRAR "+mapDiccionario.get("ARRIENDO")+" POR DAÑOS");

			row = hoja1.createRow(2);
			cell = row.createCell(1);
			cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("EMPRESA: "+mapDiccionario.get("nEmpresa"));

			row = hoja1.createRow(3);
			cell = row.createCell(1);
			cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FECHA: "+hoy.getFechaStrDDMMAA());

			row = hoja1.createRow(6);
			cell = row.createCell(1);
			cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(mapDiccionario.get("BODEGA")+"/PROYECTO: "+bodegaEmpresa.getNombre().toUpperCase());

			row = hoja1.createRow(7);
			cell = row.createCell(1);
			cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("PERIODO: desde " + desde.getFechaStrDDMMAA()  + " hasta " + hasta.getFechaStrDDMMAA());

			CreationHelper creationHelper = libro.getCreationHelper();

			CellStyle fecha = libro.createCellStyle();
			fecha.setDataFormat(creationHelper.createDataFormat().getFormat("dd/MM/yyyy"));
			fecha.setBorderBottom(CellStyle.BORDER_THIN);
			fecha.setBorderTop(CellStyle.BORDER_THIN);
			fecha.setBorderRight(CellStyle.BORDER_THIN);
			fecha.setBorderLeft(CellStyle.BORDER_THIN);

			for(int i=1; i<15; i++){
				hoja1.setColumnWidth(i, 4*1000);
			}
			//anchos de columnas
			hoja1.setColumnWidth(1, 6*1000);
			hoja1.setColumnWidth(4, 12*1000);

			//INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
			InputStream x = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
			byte[] bytes = IOUtils.toByteArray(x);
			x.close();
			int pngIndex = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			Drawing draw = hoja1.createDrawingPatriarch();
			CreationHelper helper = libro.getCreationHelper();
			ClientAnchor anchor = helper.createClientAnchor();
			//set top-left corner for the image
			anchor.setCol1(9);
			anchor.setRow1(1);
			Picture img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);

			// encabezado de la tabla

			int posRow = 9;
			int posCell = 0;
			row = hoja1.createRow(posRow);

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("GRUPO");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("COTI");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("CODIGO");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("EQUIPO");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FECHA MOVIM");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("NRO MOVIM");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("REF CLIENTE");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("DIAS");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("PU "+mapDiccionario.get("ARR")+" X DIA");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("CANT EQUIPOS");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TOTAL (en "+mapDiccionario.get("Pesos")+")");

			Double totaltotal=(double)0;
			Double totalCant=(double)0;
			for(int i=0; i<lista.size(); i++){
				posRow++;
				posCell = 0;
				row = hoja1.createRow(posRow);

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(1));

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				Double aux = Double.parseDouble(lista.get(i).get(2).replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(3));

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(4));

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(fecha);
				Fechas fechax = Fechas.obtenerFechaDesdeStrAAMMDD(Fechas.AAMMDD(lista.get(i).get(5)));
				cell.setCellValue(fechax.fechaUtil);

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				aux = Double.parseDouble(lista.get(i).get(6).replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(7));

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				aux = Double.parseDouble(lista.get(i).get(8).replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				aux = Double.parseDouble(lista.get(i).get(9).replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				aux = Double.parseDouble(lista.get(i).get(10).replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);
				totalCant += aux;

				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				aux = Double.parseDouble(lista.get(i).get(11).replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);
				totaltotal += aux;
			}

			posRow++;
			posCell = 0;
			row = hoja1.createRow(posRow);

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TOTALES");

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			cell.setCellValue(totalCant);

			posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(detalle);
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			cell.setCellValue(totaltotal);

			posRow = posRow + 5;
			row = hoja1.createRow(posRow);
			cell = row.createCell(1);
			Hyperlink hiper = helper.createHyperlink(0);
			hiper.setAddress("https://www.inqsol.cl");
			cell.setHyperlink(hiper);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Documento generado desde MADA propiedad de INQSOL");

			// Write the output to a file tmp
			FileOutputStream fileOut = new FileOutputStream(tmp);
			libro.write(fileOut);
			fileOut.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return tmp;
	}

	public static ProformaEstado agregaTotalesYcreaPDF (Connection con, String db, Map<String,String> mapPermiso, Map<String,String> mapDiccionario,
				   List<List<String>> lista, Fechas desde, Fechas hasta, BodegaEmpresa bodegaEmpresa, Fechas hoy, ProformaEstado proforma, Long nroDec) {
		File tmp = TempFile.createTempFile("tmp","null");
		try {
			String path = db + "/formatos/proformaVenta.docx";
			InputStream formato = Archivos.leerArchivo(path);
			XWPFDocument doc = new XWPFDocument(formato);
			formato.close();

			XWPFTable table = null;
			XWPFTableRow row = null;
			XWPFTableCell cell = null;

			table = doc.getTables().get(0);
			row=table.getRow(1);cell=row.getCell(2);setCelda(cell,"Arial",10,2,"2b5079",hoy.getFechaStrDDMMAA(),false);
			row=table.getRow(2);cell=row.getCell(2);setCelda(cell,"Arial",12,2,"2b5079",proforma.id.toString(),false);
			row=table.getRow(0);cell=row.getCell(2);setCelda(cell,"Arial",12,2,"2b5079","PROFORMA ESTADOS",false);

			Proyecto proyecto = Proyecto.find(con, db, bodegaEmpresa.id_proyecto);
			String dirProyecto = "";
			if(proyecto.id > 0) {
				dirProyecto = proyecto.getDireccion() + ", "+ proyecto.getComuna() + ", " + proyecto.getCiudad();
			}
			Cliente cliente = Cliente.find(con, db, bodegaEmpresa.id_cliente);

			table= doc.getTables().get(1);
			cell=table.getRow(0).getCell(1);setCelda(cell,"Arial",10,1,"2b5079",cliente.getNombre(),false);
			cell=table.getRow(1).getCell(1);setCelda(cell,"Arial",10,1,"2b5079",cliente.getRut(),false);
			cell=table.getRow(1).getCell(3);setCelda(cell,"Arial",10,1,"2b5079",bodegaEmpresa.getNombre(),false);
			cell=table.getRow(2).getCell(1);setCelda(cell,"Arial",10,1,"2b5079",cliente.getGiro(),false);
			cell=table.getRow(3).getCell(1);setCelda(cell,"Arial",10,1,"2b5079",cliente.getDireccion(),false);
			cell=table.getRow(4).getCell(1);setCelda(cell,"Arial",10,1,"2b5079",cliente.getRegion(),false);
			cell=table.getRow(5).getCell(1);setCelda(cell,"Arial",10,1,"2b5079",cliente.getComuna(),false);
			cell=table.getRow(3).getCell(3);setCelda(cell,"Arial",10,1,"2b5079","de "+desde.getFechaStrDDMMAA()+" a "+hasta.getFechaStrDDMMAA(),false);
			cell=table.getRow(4).getCell(3);setCelda(cell,"Arial",8,1,"2b5079",bodegaEmpresa.getComercial(),false);

			cell=table.getRow(5).getCell(2);setCelda(cell,"Arial",9,1,"000000","DIR_PROY:",false);
			cell=table.getRow(5).getCell(3);setCelda(cell,"Arial",8,1,"2b5079",dirProyecto,false);

			table = doc.getTables().get(2);
			cell = table.getRow(0).getCell(4);
			cell.removeParagraph(0); // Limpia el contenido actual
			setCelda(cell, "Arial", 9, 2, "000000", "DIAS", false);


			Double totalVenta = (double)0;
			int linea = 0;
			for(int i=0; i<lista.size(); i++){
				Double vta = Double.parseDouble(lista.get(i).get(11).replaceAll(",", ""));
				if( vta > 0) {
					int celda = 0;
					linea ++;
					row = table.getRow(linea);

					String dato = lista.get(i).get(3);
					cell = row.getCell(celda);
					setCelda(cell,"Arial",8,1,"2b5079",dato,false);
					celda++;

					dato = lista.get(i).get(4);
					cell = row.getCell(celda);
					setCelda(cell,"Arial",8,1,"2b5079",dato,false);
					celda++;

					dato = "UN";
					cell = row.getCell(celda);
					setCelda(cell,"Arial",10,2,"2b5079",dato,false);
					celda++;

					dato = lista.get(i).get(10);
					cell = row.getCell(celda);
					setCelda(cell,"Arial",10,3,"2b5079",dato,false);
					celda++;

					dato = lista.get(i).get(8);
					cell = row.getCell(celda);
					setCelda(cell,"Arial",10,3,"2b5079",dato,false);
					celda++;

					dato = lista.get(i).get(11);
					cell = row.getCell(celda);
					setCelda(cell,"Arial",10,3,"2b5079",dato,false);

					totalVenta += vta;
					table.createRow();
				}
			}

			EmisorTributario emisorTributario = EmisorTributario.find(con, db);
			proforma.setNeto(totalVenta);
			proforma.setIva(proforma.neto * emisorTributario.tasaIva / 100);
			if(mapPermiso.get("parametro.ivaPorBodega") != null && mapPermiso.get("parametro.ivaPorBodega").equals("1")) {
				if(bodegaEmpresa != null) {
					if(bodegaEmpresa.getIvaBodega() > 0) {
						proforma.setIva(proforma.neto * bodegaEmpresa.getIvaBodega());
					}else {
						Sucursal sucursal = Sucursal.find(con, db, bodegaEmpresa.getId_sucursal().toString());
						if(sucursal!=null && sucursal.getIvaSucursal() > 0) {
							proforma.setIva(proforma.neto * sucursal.getIvaSucursal());
						}
					}

				}
			}
			proforma.setTotal(proforma.neto + proforma.iva);

			ProformaEstado.updateTotales(con, db, proforma);

			linea++;
			row = table.getRow(linea);
			cell=row.getCell(1);
			setCelda(cell,"Arial",10,1,"2b5079","SUBTOTALES",false);
			cell=row.getCell(2);
			setCelda(cell,"Arial",10,2,"2b5079","",false);
			cell=row.getCell(3);
			setCelda(cell,"Arial",10,2,"2b5079","",false);
			cell=row.getCell(4);
			setCelda(cell,"Arial",10,3,"2b5079","",false);
			cell=row.getCell(5);
			setCelda(cell,"Arial",10,3,"2b5079", DecimalFormato.formato(totalVenta, nroDec),false);
			table.createRow();

			table = doc.getTables().get(3);
			cell=table.getRow(0).getCell(2);
			setCelda(cell,"Arial",10,3,"2b5079",DecimalFormato.formato(proforma.getNeto(), nroDec),false);

			cell=table.getRow(1).getCell(1);
			setCelda(cell,"Arial",10,3,"2b5079","",false);
			cell=table.getRow(2).getCell(2);
			setCelda(cell,"Arial",10,3,"2b5079",DecimalFormato.formato(proforma.getNeto(), nroDec),false);
			cell=table.getRow(3).getCell(2);
			setCelda(cell,"Arial",10,3,"2b5079",DecimalFormato.formato(proforma.getIva(), nroDec),false);
			cell=table.getRow(4).getCell(2);
			setCelda(cell,"Arial",10,3,"2b5079",DecimalFormato.formato(proforma.getTotal(), nroDec),false);


			// Write the output to a file word
			FileOutputStream fileOut = new FileOutputStream(tmp);
			doc.write(fileOut);
			fileOut.close();

			// 1) Load DOCX into XWPFDocument
			InputStream is = new FileInputStream(tmp);
			XWPFDocument document = new XWPFDocument(is);
			is.close();
			// 2) Prepare Pdf options
			PdfOptions options = PdfOptions.create().fontEncoding("iso-8859-15");
			// 3) Convert XWPFDocument to Pdf
			OutputStream out = new FileOutputStream(tmp);
			PdfConverter.getInstance().convert(document, out, options);
			out.close();

			String archivoPDF = db+"/"+proforma.getPdf();
			Archivos.grabarArchivo(tmp, archivoPDF);

			return(proforma);

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return(null);
	}

	private static void setCelda (XWPFTableCell cell,String fontFamily,int fontSize,int alingH,String colorRGB,String text,boolean bold) {
		cell.removeParagraph(0);
		XWPFParagraph paragraph =null;
		if(text==null) {
			text="--";
		}else if(text.trim().length()==0) {
			text="--";
		}
		paragraph = cell.addParagraph();
		if(alingH==3) {
			paragraph.setAlignment(ParagraphAlignment.RIGHT);
		}else if (alingH==2) {
			paragraph.setAlignment(ParagraphAlignment.CENTER);
		}else {
			paragraph.setAlignment(ParagraphAlignment.LEFT);
		}
		cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
		XWPFRun celda = paragraph.createRun();
		celda.setFontFamily(fontFamily);
		celda.setFontSize(fontSize);
		celda.setColor(colorRGB);
		celda.setText(text);
		celda.setBold(bold);
	}

	
	
}
