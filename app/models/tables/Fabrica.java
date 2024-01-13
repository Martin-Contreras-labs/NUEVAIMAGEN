package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.forms.FormFabricaGraba;




public class Fabrica {
	public Long id;
	public String nombre; //VARCHAR(100)
	public String nickName; //VARCHAR(20) not null
	public String direccion;  //VARCHAR(100)
	public String cod_region;
	public String cod_comuna;
	
	public String region;
	public String comuna;
	public String ciudad;
	

	public Fabrica(Long id, String nombre, String nickName, String direccion, String cod_region, String cod_comuna,
			String region, String comuna, String ciudad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.nickName = nickName;
		this.direccion = direccion;
		this.cod_region = cod_region;
		this.cod_comuna = cod_comuna;
		this.region = region;
		this.comuna = comuna;
		this.ciudad = ciudad;
	}

	public Fabrica() {
		super();
	}
	
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
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

	public static List<Fabrica> all(Connection con, String db) {
		List<Fabrica> lista = new ArrayList<Fabrica>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" fabrica.id, " +
							" fabrica.nombre, " +
							" fabrica.nickName, " +
							" fabrica.direccion, " +
							" fabrica.cod_region, " +
							" fabrica.cod_comuna, " +
							" ifnull(regiones.nombre,'--'), " +
							" ifnull(comunas.nombre,'--'), " +
							" fabrica.ciudad " +
							" from `"+db+"`.fabrica " +
							" left join `"+db+"`.regiones on regiones.codigo = fabrica.cod_region " +
							" left join `"+db+"`.comunas on comunas.codigo = fabrica.cod_comuna " +
							" order by fabrica.nombre;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {		
				lista.add(new Fabrica(rs.getLong(1),rs.getString(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static Fabrica find(Connection con, String db, Long id_fabricante) {
		Fabrica aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement(" select  " +
							" fabrica.id, " +
							" fabrica.nombre, " +
							" fabrica.nickName, " +
							" fabrica.direccion, " +
							" fabrica.cod_region, " +
							" fabrica.cod_comuna, " +
							" ifnull(regiones.nombre,'--'), " +
							" ifnull(comunas.nombre,'--'), " +
							" fabrica.ciudad " +
							" from `"+db+"`.fabrica " +
							" left join `"+db+"`.regiones on regiones.codigo = fabrica.cod_region " +
							" left join `"+db+"`.comunas on comunas.codigo = fabrica.cod_comuna " +
							" where fabrica.id = ?;" );
			smt.setLong(1, id_fabricante);
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {				
				aux = new Fabrica(rs.getLong(1),rs.getString(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}
	
	public static Long minId_fabrica(Connection con, String db) {
		Long aux = (long)1;
		try {
			PreparedStatement smt = con
					.prepareStatement(" select  " +
							" min(fabrica.id) " +
							" from `"+db+"`.fabrica;");
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
	
	public static boolean existeNickName(Connection con, String db, String nickName) {
		boolean flag = false;
		try {
			PreparedStatement smt2 = con
					.prepareStatement("select * from `"+db+"`.fabrica WHERE nickName = ?");
			smt2.setString(1, nickName);
			ResultSet resultado2 = smt2.executeQuery();
			if (resultado2.next()) {
				flag = true;
			}
			resultado2.close();
			smt2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean modificaPorCampo(Connection con, String db,String campo,Long id_fabrica,String valor) {
		boolean flag = false;
		try {
			PreparedStatement smt = con.prepareStatement("UPDATE `"+db+"`.fabrica set " + campo + " = ? WHERE id = ?;");		
			smt.setString(1, valor.trim());
			smt.setLong(2, id_fabrica);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean estaEnUso(Connection con, String db, Long id_fabrica) {
		boolean flag = false;
		try {
			PreparedStatement smt2 = con
					.prepareStatement("select * from `"+db+"`.equipo WHERE id_fabrica = ?");
			smt2.setLong(1, id_fabrica);
			ResultSet resultado2 = smt2.executeQuery();
			if (resultado2.next()) {
				flag = true;
			}
			resultado2.close();
			smt2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean delete(Connection con, String db, Long id_fabrica) {
		boolean flag = false;
		try {
			PreparedStatement smt1 = con
					.prepareStatement("DELETE FROM `"+db+"`.fabrica WHERE id = ?");
			smt1.setLong(1, id_fabrica);
			smt1.executeUpdate();
			smt1.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean create(Connection con, String db, FormFabricaGraba aux) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("INSERT INTO `"+db+"`.fabrica (nombre,nickName,direccion,cod_region,cod_comuna, ciudad) " +
							" VALUES (?,?,?,?,?,?)");
			smt.setString(1, aux.nombre.trim());
			smt.setString(2, aux.nickName.trim());
			smt.setString(3, aux.direccion.trim());
			smt.setString(4, aux.cod_region.trim());
			smt.setString(5, aux.cod_comuna.trim());
			smt.setString(6, aux.ciudad.trim());
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	
	
	
}
