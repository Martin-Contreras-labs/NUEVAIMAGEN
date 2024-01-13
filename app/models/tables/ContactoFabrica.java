package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.forms.FormContactoFabricaGraba;



public class ContactoFabrica {
	public Long id;
	public Long id_fabrica;
	public String nombre;
	public String cargo;
	public String movil;
	public String fijo;
	public String mail;
	
	public ContactoFabrica(Long id, Long id_fabrica, String nombre,
			String cargo, String movil, String fijo, String mail) {
		super();
		this.id = id;
		this.id_fabrica = id_fabrica;
		this.nombre = nombre;
		this.cargo = cargo;
		this.movil = movil;
		this.fijo = fijo;
		this.mail = mail;
	}

	public ContactoFabrica() {
		super();
	}

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	public String getCargo() {return cargo;}
	public void setCargo(String cargo) {this.cargo = cargo;}
	public String getMovil() {return movil;}
	public void setMovil(String movil) {this.movil = movil;}
	public String getFijo() {return fijo;}
	public void setFijo(String fijo) {this.fijo = fijo;}
	public String getMail() {return mail;}
	public void setMail(String mail) {this.mail = mail;}
	public Long getId_fabrica() {return id_fabrica;}
	public void setId_fabrica(Long id_fabrica) {this.id_fabrica = id_fabrica;}
	
	public static List<ContactoFabrica> allxFabrica(Connection con, String db, Long idFabrica) {
		List<ContactoFabrica> lista = new ArrayList<ContactoFabrica>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" contactoFabrica.id, " +
							" contactoFabrica.id_fabrica, " +
							" contactoFabrica.nombre, " +
							" contactoFabrica.cargo, " +
							" contactoFabrica.movil, " +
							" contactoFabrica.fijo, " +
							" contactoFabrica.mail " +
							" from `"+db+"`.contactoFabrica " +
							" where contactoFabrica.id_fabrica = ?;");
			smt.setLong(1, idFabrica);
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				lista.add(new ContactoFabrica(rs.getLong(1),rs.getLong(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    return (lista);
	}
	
	public static ContactoFabrica find(Connection con, String db, Long id_contacto) {
		ContactoFabrica aux = new ContactoFabrica();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" contactoFabrica.id, " +
							" contactoFabrica.id_fabrica, " +
							" contactoFabrica.nombre, " +
							" contactoFabrica.cargo, " +
							" contactoFabrica.movil, " +
							" contactoFabrica.fijo, " +
							" contactoFabrica.mail " +
							" from `"+db+"`.contactoFabrica " +
							" where contactoFabrica.id = ?;");
			smt.setLong(1, id_contacto);
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				aux = new ContactoFabrica(rs.getLong(1),rs.getLong(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}
	
	public static boolean create(Connection con, String db, FormContactoFabricaGraba aux) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("INSERT INTO `"+db+"`.contactoFabrica (id_fabrica,nombre,cargo,fijo,movil,mail) " +
							" VALUES (?,?,?,?,?,?)");
			smt.setLong(1, aux.id_fabrica);
			smt.setString(2, aux.nombre.trim());
			smt.setString(3, aux.cargo.trim());
			smt.setString(4, aux.fijo.trim());
			smt.setString(5, aux.movil.trim());
			smt.setString(6, aux.mail.trim());
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean delete(Connection con, String db, Long id_fabrica) {
		boolean flag = false;
		try {
			PreparedStatement smt1 = con
						.prepareStatement("DELETE FROM `"+db+"`.contactoFabrica WHERE id = ?");
				smt1.setLong(1, id_fabrica);
				smt1.executeUpdate();
				smt1.close();
				flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean modificaPorCampo(Connection con, String db,String campo,Long id_contacto,String valor) {
		boolean flag = false;
		try {
			PreparedStatement smt = con.prepareStatement("UPDATE `"+db+"`.contactoFabrica set " + campo + " = ? WHERE id = ?;");		
			smt.setString(1, valor.trim());
			smt.setLong(2, id_contacto);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}





}
