package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.sql.*;

import models.utilities.Fechas;




public class Sucursal {
	public Long id;
	public String nombre;
	public String observaciones;
	public String direccion;
	public Double ivaSucursal;
	public String ccost;
	
	public Sucursal(Long id, String nombre, String observaciones, String direccion, Double ivaSucursal, String ccost) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.observaciones = observaciones;
		this.direccion = direccion;
		this.ivaSucursal = ivaSucursal;
		this.ccost = ccost;
	}

	public Sucursal() {
		super();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Double getIvaSucursal() {
		return ivaSucursal;
	}

	public void setIvaSucursal(Double ivaSucursal) {
		this.ivaSucursal = ivaSucursal;
	}

	public String getCcost() {
		return ccost;
	}

	public void setCcost(String ccost) {
		this.ccost = ccost;
	}

	public static Map<Long,Sucursal> mapAllSucursales(Connection con, String db){
		Map<Long,Sucursal> map = new HashMap<Long,Sucursal>();
		List<Sucursal> listSucursal = Sucursal.all(con, db);
		listSucursal.forEach(x->{
			map.put(x.getId(), x);
		});
		return(map);
	}
	
	public static List<Sucursal> all(Connection con, String db) {
		List<Sucursal> lista = new ArrayList<Sucursal>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select " + 
							" sucursal.id, " + 
							" sucursal.nombre, " + 
							" sucursal.observaciones, " +
							" sucursal.direccion, " +
							" sucursal.ivaSucursal, " +
							" sucursal.ccost " +
							" from `"+db+"`.sucursal " +
							" order by sucursal.nombre;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {		
				lista.add(new Sucursal(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDouble(5),rs.getString(6)));
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static Sucursal find(Connection con, String db, String id_sucursal) {
		Sucursal aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement("select " + 
							" sucursal.id, " + 
							" sucursal.nombre, " +
							" sucursal.observaciones, " +
							" sucursal.direccion, " +
							" sucursal.ivaSucursal, " +
							" sucursal.ccost " +
							" from `"+db+"`.sucursal " +
							" where sucursal.id=?;");
			smt.setString(1, id_sucursal);
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {		
				aux = new Sucursal(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDouble(5),rs.getString(6));
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}
	
	public static Sucursal findPorNombre(Connection con, String db, String nameSucursal) {
		Sucursal aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement("select " + 
							" sucursal.id, " + 
							" sucursal.nombre, " +
							" sucursal.observaciones, " +
							" sucursal.direccion, " +
							" sucursal.ivaSucursal, " +
							" sucursal.ccost " +
							" from `"+db+"`.sucursal " +
							" where upper(sucursal.nombre) = ?;");
			smt.setString(1, nameSucursal.toUpperCase());
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {		
				aux = new Sucursal(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDouble(5),rs.getString(6));
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}
	
	public static boolean esPorSucursal(Map<String,String> mapeoPermiso, String id_tipoUsuario) {
		boolean esPorSucursal = true;
		if(mapeoPermiso.get("cambiarSucursal")!=null) {
			esPorSucursal = false;
		}
		if(mapeoPermiso.get("parametro.aplicaRestriccionesPorSucursal")==null || mapeoPermiso.get("parametro.aplicaRestriccionesPorSucursal").equals("0")) {
			esPorSucursal = false;
		}
		
		
		return(esPorSucursal);
	}
	
	
	public static boolean estaEnUso(Connection con, String db, Long id_sucursal) {
		boolean flag = false;
		try {
			PreparedStatement smt1 = con
					.prepareStatement("Select * from `"+db+"`.cotizacion WHERE id_sucursal = ?");
			smt1.setLong(1, id_sucursal);
			ResultSet rs1 = smt1.executeQuery();
			if (rs1.next()) {
				flag = true;
			}else {
				
				PreparedStatement smt2 = con
						.prepareStatement("Select * from `"+db+"`.usuario WHERE id_sucursal = ?");
				smt2.setLong(1, id_sucursal);
				ResultSet rs2 = smt2.executeQuery();
				if (rs2.next()) {
					flag = true;
				}else {
					PreparedStatement smt3 = con
							.prepareStatement("Select * from `"+db+"`.bodegaEmpresa WHERE id_sucursal = ?");
					smt3.setLong(1, id_sucursal);
					ResultSet rs3 = smt3.executeQuery();
					if (rs3.next()) {
						flag = true;
					}
					rs3.close();
					smt3.close();
				}
				rs2.close();
				smt2.close();
			}
			rs1.close();
			smt1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean delete(Connection con, String db, Long id_sucursal) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("delete from `"+db+"`.sucursal WHERE id = ?");
			smt.setLong(1, id_sucursal);
			smt.executeUpdate();
			smt.close();
			
			PreparedStatement smt2 = con
					.prepareStatement("delete from `"+db+"`.precio WHERE id_sucursal = ?");
			smt2.setLong(1, id_sucursal);
			smt2.executeUpdate();
			smt2.close();
			
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean existeSucursal(Connection con, String db, String nombre_sucursal) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("select id,nombre from `"+db+"`.sucursal WHERE upper(nombre) = ?" );
			smt.setString(1, nombre_sucursal.toUpperCase());
			ResultSet resultado = smt.executeQuery();
			if (resultado.next()) {
				flag = true;
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean create(Connection con,String db, String nombreSucursal, Double ivaSucursal, String ccost) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("insert into `"+db+"`.sucursal (nombre,ivaSucursal,ccost) values (?,?,?);", Statement.RETURN_GENERATED_KEYS);
			smt.setString(1, nombreSucursal.trim().toUpperCase());
			smt.setDouble(2, ivaSucursal);
			smt.setString(3, ccost);
			smt.executeUpdate();
			
			Long id_sucursal = (long)0;
			ResultSet rs1 = smt.getGeneratedKeys();
            if (rs1.next()) {
            	id_sucursal = rs1.getLong(1);
            }
            smt.close();
            rs1.close();
            
            PreparedStatement smt2 = con
					.prepareStatement("insert into `"+db+"`.precio"
							+ " (id_equipo,id_moneda,fecha,precioVenta,precioReposicion,tasaArriendo,precioArriendo,id_unidadTiempo,precioMinimo,permanenciaMinima,id_sucursal)"
							+ " (select id_equipo,id_moneda,'"+Fechas.hoy().getFechaStrAAMMDD()+"',precioVenta,precioReposicion,tasaArriendo,precioArriendo,id_unidadTiempo,precioMinimo,permanenciaMinima,'"+id_sucursal+"'"
									+ " from `"+db+"`.precio where id_sucursal=1);");
			smt2.executeUpdate();
			smt2.close();
			flag = true;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean modificaPorCampo(Connection con,String db, String campo, Long id_sucursal, String valor) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("update `"+db+"`.sucursal set `"+campo+"` = ? WHERE id = ?");
			smt.setString(1, valor.trim().toUpperCase());
			smt.setLong(2, id_sucursal);
			smt.executeUpdate();
			smt.close();
			flag=true;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	

	
}
