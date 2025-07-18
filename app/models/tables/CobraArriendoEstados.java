package models.tables;

import controllers.HomeController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CobraArriendoEstados {
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


	public CobraArriendoEstados(Long id_guia, Long id_bodegaEmpresa, Long id_equipo, Long id_unidad, Long id_cotizacion,
								Long id_sucursal, Long id_grupo, String nombreBodega, Long nroCoti, String codigo, String nombreEquipo, Long numGuia,
								String numGuiaCliente, String fecha, Double cantidad, String nombreSucursal, String nombreGrupo, Long id_moneda,
								String nickMoneda, Double puArriendoDia, Double puReposicion) {
		super();
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
	}

	public CobraArriendoEstados() {
			super();
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

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	public static List<CobraArriendoEstados> all (Connection con, String db) {
		List<CobraArriendoEstados> lista = new ArrayList<CobraArriendoEstados>();
		String query = String.format("select" +
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
						" sum(estadoEquipo.cantidad)" +
						" from `%s`.estadoEquipo" +
						" left join `%s`.movimiento on movimiento.id = estadoEquipo.id_movimiento" +
						" left join `%s`.tipoEstado on tipoEstado.id = estadoEquipo.id_tipoEstado" +
						" left join `%s`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa" +
						" left join `%s`.guia on guia.id = movimiento.id_guia" +
						" left join `%s`.equipo on equipo.id = movimiento.id_equipo" +
						" left join `%s`.cotizacion on cotizacion.id = movimiento.id_cotizacion" +
						" where (tipoEstado.cobraArriendo = 1 and estadoEquipo.cobraArriendo = 1) and bodegaEmpresa.vigente = 1 and bodegaEmpresa.esInterna = 2 and movimiento.id_guia > 0" +
						" group by estadoEquipo.id_guia, movimiento.id_equipo, movimiento.id_cotizacion, movimiento.id_bodegaEmpresa" +
						" having sum(estadoEquipo.cantidad)>0;",db,db,db,db,db,db,db);
		try (PreparedStatement smt = con.prepareStatement(query);
			 ResultSet rs = smt.executeQuery()){
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con,db);
			Map<Long,Grupo> mapGrupo = Grupo.mapAll(con,db);
			Map<String,List<Double>> mapPrecio = ListaPrecio.mapListaPreciosAll(con,db);
			while (rs.next()) {
				Long id_guia = rs.getLong(1);
				Long id_bodegaEmpresa = rs.getLong(2);
				Long id_equipo = rs.getLong(3);
				Long id_unidad = rs.getLong(4);
				Long id_cotizacion = rs.getLong(5);
				Long id_sucursal = rs.getLong(6);
				Long id_grupo = rs.getLong(7);
				String nombreBodega = rs.getString(8);
				Long nroCoti = rs.getLong(9);
				String codigo = rs.getString(10);
				String nombreEquipo = rs.getString(11);
				Long numGuia = rs.getLong(12);
				String numGuiaCliente = rs.getString(13);
				String fecha = rs.getString(14);
				Double cantidad = rs.getDouble(15);

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


				lista.add(new CobraArriendoEstados(id_guia,id_bodegaEmpresa,id_equipo,id_unidad,id_cotizacion,id_sucursal,id_grupo,nombreBodega,nroCoti,codigo,nombreEquipo,
						numGuia,numGuiaCliente,fecha,cantidad,nombreSucursal,nombreGrupo,id_moneda,nickMoneda,puArriendoDia,puReposicion));
			}
		} catch (SQLException e) {
			String className = ActaBaja.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("DB ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return (lista);
	}

	
	
}
