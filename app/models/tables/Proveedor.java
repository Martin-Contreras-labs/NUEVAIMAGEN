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

import models.forms.FormProveedorGraba;




public class Proveedor {
	public Long id;
	public String rut;
	public String nombre; //VARCHAR(100)
	public String nickName; //VARCHAR(20) not null
	public String direccion;  //VARCHAR(100)
	public String cod_region;
	public String cod_comuna;
	
	public String region;
	public String comuna;
	
	public String ciudad;
	
	public String formaDePago;
	public String especialidad;
	

	public Proveedor(Long id, String rut, String nombre, String nickName, String direccion, String cod_region,
			String cod_comuna, String region, String comuna, String ciudad, String formaDePago, String especialidad) {
		super();
		this.id = id;
		this.rut = rut;
		this.nombre = nombre;
		this.nickName = nickName;
		this.direccion = direccion;
		this.cod_region = cod_region;
		this.cod_comuna = cod_comuna;
		this.region = region;
		this.comuna = comuna;
		this.ciudad = ciudad;
		this.formaDePago = formaDePago;
		this.especialidad = especialidad;
	}

	public Proveedor() {
		super();
	}
	
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getRut() {return rut;}
	public void setRut(String rut) {this.rut = rut;}
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	public String getNickName() {return nickName;}
	public void setNickName(String nickName) {this.nickName = nickName;}
	public String getDireccion() {return direccion;}
	public void setDireccion(String direccion) {this.direccion = direccion;}
	public String getCod_comuna() {return cod_comuna;}
	public void setCod_comuna(String cod_comuna) {this.cod_comuna = cod_comuna;}
	public String getComuna() {return comuna;}
	public void setComuna(String comuna) {this.comuna = comuna;}
	public String getCod_region() {return cod_region;}
	public void setCod_region(String cod_region) {this.cod_region = cod_region;}
	public String getRegion() {return region;}
	public void setRegion(String region) {this.region = region;}
	public String getCiudad() {return ciudad;}
	public void setCiudad(String ciudad) {this.ciudad = ciudad;}
	
	public String getFormaDePago() {
		return formaDePago;
	}

	public void setFormaDePago(String formaDePago) {
		this.formaDePago = formaDePago;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public static Long maxId(Connection con, String db) {
		Long aux = (long)0;
		try {
			PreparedStatement smt = con
					.prepareStatement(" select  " +
							" max(proveedor.id) " +
							" from `"+db+"`.proveedor;" );
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {				
				aux = rs.getLong(1);
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (aux);
	}

	public static List<Proveedor> all(Connection con, String db) {
		List<Proveedor> lista = new ArrayList<Proveedor>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" proveedor.id, " +
							" proveedor.rut, " +
							" proveedor.nombre, " +
							" proveedor.nickName, " +
							" proveedor.direccion, " +
							" proveedor.cod_region, " +
							" proveedor.cod_comuna, " +
							" ifnull(regiones.nombre,'--'), " +
							" ifnull(comunas.nombre,'--'), " +
							" proveedor.ciudad, " +
							" proveedor.formaDePago, " +
							" proveedor.especialidad " +
							" from `"+db+"`.proveedor " +
							" left join `"+db+"`.regiones on regiones.codigo = proveedor.cod_region " +
							" left join `"+db+"`.comunas on comunas.codigo = proveedor.cod_comuna " +
							" order by proveedor.nickName;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {		
				lista.add(new Proveedor(rs.getLong(1),rs.getString(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (lista);
	}
	
	public static Map<String, Proveedor> mapPorRut(Connection con, String db) {
		Map<String, Proveedor> map = new HashMap<String, Proveedor>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" proveedor.id, " +
							" proveedor.rut, " +
							" proveedor.nombre, " +
							" proveedor.nickName, " +
							" proveedor.direccion, " +
							" proveedor.cod_region, " +
							" proveedor.cod_comuna, " +
							" ifnull(regiones.nombre,'--'), " +
							" ifnull(comunas.nombre,'--'), " +
							" proveedor.ciudad, " +
							" proveedor.formaDePago, " +
							" proveedor.especialidad " +
							" from `"+db+"`.proveedor " +
							" left join `"+db+"`.regiones on regiones.codigo = proveedor.cod_region " +
							" left join `"+db+"`.comunas on comunas.codigo = proveedor.cod_comuna " +
							" order by proveedor.nickName;");
			ResultSet rs = smt.executeQuery();
			
			while (rs.next()) {	
				map.put(rs.getString(2), new Proveedor(rs.getLong(1),rs.getString(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (map);
	}
	
	public static Map<String, Proveedor> mapPorNick(Connection con, String db) {
		Map<String, Proveedor> map = new HashMap<String, Proveedor>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" proveedor.id, " +
							" proveedor.rut, " +
							" proveedor.nombre, " +
							" proveedor.nickName, " +
							" proveedor.direccion, " +
							" proveedor.cod_region, " +
							" proveedor.cod_comuna, " +
							" ifnull(regiones.nombre,'--'), " +
							" ifnull(comunas.nombre,'--'), " +
							" proveedor.ciudad, " +
							" proveedor.formaDePago, " +
							" proveedor.especialidad " +
							" from `"+db+"`.proveedor " +
							" left join `"+db+"`.regiones on regiones.codigo = proveedor.cod_region " +
							" left join `"+db+"`.comunas on comunas.codigo = proveedor.cod_comuna " +
							" order by proveedor.nickName;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {	
				map.put(rs.getString(4), new Proveedor(rs.getLong(1),rs.getString(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (map);
	}
	
	public static Proveedor find(Connection con, String db, Long id_proveedor) {
		Proveedor aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement(" select  " +
							" proveedor.id, " +
							" proveedor.rut, " +
							" proveedor.nombre, " +
							" proveedor.nickName, " +
							" proveedor.direccion, " +
							" proveedor.cod_region, " +
							" proveedor.cod_comuna, " +
							" ifnull(regiones.nombre,'--'), " +
							" ifnull(comunas.nombre,'--'), " +
							" proveedor.ciudad, " +
							" proveedor.formaDePago, " +
							" proveedor.especialidad " +
							" from `"+db+"`.proveedor " +
							" left join `"+db+"`.regiones on regiones.codigo = proveedor.cod_region " +
							" left join `"+db+"`.comunas on comunas.codigo = proveedor.cod_comuna " +
							" where proveedor.id = ?;" );
			smt.setLong(1, id_proveedor);
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {				
				aux = new Proveedor(rs.getLong(1),rs.getString(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (aux);
	}
	
	public static Proveedor findPorRut(Connection con, String db, String rut) {
		Proveedor aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement(" select  " +
							" proveedor.id, " +
							" proveedor.rut, " +
							" proveedor.nombre, " +
							" proveedor.nickName, " +
							" proveedor.direccion, " +
							" proveedor.cod_region, " +
							" proveedor.cod_comuna, " +
							" ifnull(regiones.nombre,'--'), " +
							" ifnull(comunas.nombre,'--'), " +
							" proveedor.ciudad, " +
							" proveedor.formaDePago, " +
							" proveedor.especialidad " +
							" from `"+db+"`.proveedor " +
							" left join `"+db+"`.regiones on regiones.codigo = proveedor.cod_region " +
							" left join `"+db+"`.comunas on comunas.codigo = proveedor.cod_comuna " +
							" where proveedor.rut = ?;" );
			smt.setString(1, rut);
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {				
				aux = new Proveedor(rs.getLong(1),rs.getString(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (aux);
	}
	
	public static Proveedor findPorNickName(Connection con, String db, String nickName) {
		Proveedor aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement(" select  " +
							" proveedor.id, " +
							" proveedor.rut, " +
							" proveedor.nombre, " +
							" proveedor.nickName, " +
							" proveedor.direccion, " +
							" proveedor.cod_region, " +
							" proveedor.cod_comuna, " +
							" ifnull(regiones.nombre,'--'), " +
							" ifnull(comunas.nombre,'--'), " +
							" proveedor.ciudad, " +
							" proveedor.formaDePago, " +
							" proveedor.especialidad " +
							" from `"+db+"`.proveedor " +
							" left join `"+db+"`.regiones on regiones.codigo = proveedor.cod_region " +
							" left join `"+db+"`.comunas on comunas.codigo = proveedor.cod_comuna " +
							" where proveedor.nickName = ?;" );
			smt.setString(1, nickName);
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {				
				aux = new Proveedor(rs.getLong(1),rs.getString(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (aux);
	}
	
	public static boolean existeNickName(Connection con,String db, String nickName) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement(" select id from `"+db+"`.proveedor where upper(nickName) = ?;" );
			smt.setString(1, nickName.toUpperCase().trim());
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				flag = true;
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean existeRut(Connection con,String db, String rut) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement(" select id from `"+db+"`.proveedor where upper(rut) = ?;" );
			smt.setString(1, rut.toUpperCase().trim());
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				flag = true;
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean modificaPorCampo(Connection con,String db,String campo,Long id_proveedor,String valor) {
		boolean flag = false;
		try {
			if(campo.equals("rut")) valor = valor.trim().replaceAll("[\\,\\.]","").trim();
			PreparedStatement smt = con.prepareStatement("UPDATE `"+db+"`.proveedor set " + campo + " = ? WHERE id = ?;");		
			smt.setString(1, valor.trim());
			smt.setLong(2, id_proveedor);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
				e.printStackTrace();
			flag=false;
		}
		return (flag);
	}
	
	public static boolean create(Connection con, String db, FormProveedorGraba aux) {
		boolean flag=true;
		try {
			PreparedStatement smt = con
					.prepareStatement("INSERT INTO `"+db+"`.proveedor (rut,nombre,nickName,direccion,cod_region,cod_comuna,ciudad,formaDePago,especialidad) " +
							" VALUES (?,?,?,?,?,?,?,?,?)");
			String rut = aux.rut.trim().replaceAll("[\\,\\.]","").trim();
			smt.setString(1, rut);
			smt.setString(2, aux.nombre.trim());
			smt.setString(3, aux.nickName.trim());
			smt.setString(4, aux.direccion.trim());
			smt.setString(5, aux.cod_region.trim());
			smt.setString(6, aux.cod_comuna.trim());
			smt.setString(7, aux.ciudad.trim());
			smt.setString(8, aux.formaDePago.trim());
			smt.setString(9, aux.especialidad.trim());
			smt.executeUpdate();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
			flag=false;
		}
		return (flag);
	}
	
	public static Long insertNew(Connection con, String db, String nickName) {
		Long aux = (long)0;
		try {
			PreparedStatement smt = con
					.prepareStatement("INSERT INTO `"+db+"`.proveedor (nickName) VALUES (?);",Statement.RETURN_GENERATED_KEYS);
			smt.setString(1, nickName);
			smt.executeUpdate();
			
			ResultSet rs = smt.getGeneratedKeys();
            if (rs.next()) {
            	aux = rs.getLong(1);
            }
            smt.close();
            rs.close();
            
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (aux);
	}
	
	public static boolean createDesdeIConstruye(Connection con, String db, Proveedor aux) {
		boolean flag=true;
		try {
			PreparedStatement smt = con
					.prepareStatement("INSERT INTO `"+db+"`.proveedor (rut, nombre, nickName) " +
							" VALUES (?,?,?)");
			String rut = aux.rut.trim().replaceAll("[\\,\\.]","").trim();
			smt.setString(1, rut);
			smt.setString(2, aux.nombre.trim());
			smt.setString(3, aux.nickName.trim());
			smt.executeUpdate();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
			flag=false;
		}
		return (flag);
	}
	
	public static boolean estaEnUso(Connection con, String db, Long id_proveedor) {
		boolean flag = false;
		try {
			PreparedStatement smt1 = con
					.prepareStatement("select * from `"+db+"`.factura WHERE id_proveedor = ?");
			smt1.setLong(1, id_proveedor);
			ResultSet resultado1 = smt1.executeQuery();
			if (resultado1.next()) {
				flag = true;
			}
			resultado1.close();
			smt1.close();
		} catch (SQLException e) {
				e.printStackTrace();
			flag=false;
		}
		return (flag);
	}
	
	public static boolean delete(Connection con, String db, Long id_proveedor) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("DELETE FROM `"+db+"`.proveedor WHERE id = ?");
			smt.setLong(1, id_proveedor);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
				e.printStackTrace();
			flag=false;
		}
		return (flag);
	}
	
	
	
	
}
