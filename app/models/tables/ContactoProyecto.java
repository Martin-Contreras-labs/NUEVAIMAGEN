package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.forms.FormContactoProyectoGraba;



public class ContactoProyecto {
	public Long id;
	public Long id_proyecto;
	public String nombre;
	public String cargo;
	public String movil;
	public String fijo;
	public String mail;
	
	public ContactoProyecto(Long id, Long id_proyecto, String nombre,
			String cargo, String movil, String fijo, String mail) {
		super();
		this.id = id;
		this.id_proyecto = id_proyecto;
		this.nombre = nombre;
		this.cargo = cargo;
		this.movil = movil;
		this.fijo = fijo;
		this.mail = mail;
	}

	public ContactoProyecto() {
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
	public Long getId_proyecto() {return id_proyecto;}
	public void setId_proyecto(Long id_proyecto) {this.id_proyecto = id_proyecto;}

	
	public static List<ContactoProyecto> allxProyecto(Connection con, String db, Long id_proyecto) {
			List<ContactoProyecto> lista = new ArrayList<ContactoProyecto>();
			try {
				PreparedStatement smt = con
						.prepareStatement(" select " +
								" contactoProyecto.id, " +
								" contactoProyecto.id_proyecto, " +
								" contactoProyecto.nombre, " +
								" contactoProyecto.cargo, " +
								" contactoProyecto.movil, " +
								" contactoProyecto.fijo, " +
								" contactoProyecto.mail " +
								" from `"+db+"`.contactoProyecto " +
								" where contactoProyecto.id_proyecto = ?;");
				smt.setLong(1, id_proyecto);
				ResultSet rs = smt.executeQuery();
				while (rs.next()) {
					lista.add(new ContactoProyecto(rs.getLong(1),rs.getLong(2),rs.getString(3),
							rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
				}
				rs.close();
				smt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        return (lista);
    } 
	
	public static boolean create(Connection con, String db, FormContactoProyectoGraba aux) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("INSERT INTO `"+db+"`.contactoProyecto (id_proyecto, nombre,cargo,fijo,movil,mail) " +
							" VALUES (?,?,?,?,?,?)");
			smt.setLong(1, aux.id_proyecto);
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
	
	public static boolean delete(Connection con, String db, Long id_contactoProyecto) {
		boolean flag = false;
		try {
			PreparedStatement smt1 = con
						.prepareStatement("DELETE FROM `"+db+"`.contactoProyecto WHERE id = ?");
				smt1.setLong(1, id_contactoProyecto);
				smt1.executeUpdate();
				smt1.close();
				flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static ContactoProyecto find(Connection con, String db, Long id_contactoProyecto) {
		ContactoProyecto aux = new ContactoProyecto();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" contactoProyecto.id, " +
							" contactoProyecto.id_proyecto, " +
							" contactoProyecto.nombre, " +
							" contactoProyecto.cargo, " +
							" contactoProyecto.movil, " +
							" contactoProyecto.fijo, " +
							" contactoProyecto.mail " +
							" from `"+db+"`.contactoProyecto " +
							" where contactoProyecto.id = ?;");
			smt.setLong(1, id_contactoProyecto);
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				aux = new ContactoProyecto(rs.getLong(1),rs.getLong(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}
	
	public static boolean modificaPorCampo(Connection con, String db,String campo,Long id_contacto,String valor) {
		boolean flag = false;
		try {
			PreparedStatement smt = con.prepareStatement("UPDATE `"+db+"`.contactoProyecto set `" + campo + "` = ? WHERE id = ?;");		
			smt.setString(1, valor.trim());
			smt.setLong(2, id_contacto);
			smt.executeUpdate();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			flag=false;
		}
		return (flag);
	}
	
	public static String contactos(Connection con, String db, Long id_proyecto) {
		String vistaHtml = "<table class='table table-sm table-hover table-bordered table-condensed table-fluid'>"+
				"<thead style='background-color: #eeeeee'><TR>" +
		        "<TH width='20%' style= 'text-align: center;'>PROYECTO</TH>" +
				"<TH width='20%' style= 'text-align: center;'>NOMBRE</TH>" +
				"<TH width='15%' style= 'text-align: center;'>CARGO</TH>" +
				"<TH width='15%' style= 'text-align: center;'>MOVIL<br>Compra</TH>" +
				"<TH width='15%' style= 'text-align: center;'>FIJO</TH>" +
				"<TH width='15%' style= 'text-align: center;'>eMAIL</TH>" +
				"</TR></thead><tbody>";
			try {
				PreparedStatement smt = con
						.prepareStatement(" select " +
								" contactoProyecto.id_proyecto, " +
								" proyecto.nickName, " +
								" contactoProyecto.nombre, " +
								" contactoProyecto.cargo, " +
								" contactoProyecto.movil, " +
								" contactoProyecto.fijo, " +
								" contactoProyecto.mail " +
								" from `"+db+"`.contactoProyecto " +
								" left join `"+db+"`.proyecto on proyecto.id = contactoProyecto.id_proyecto " +
								" where contactoProyecto.id_proyecto = ?;");
				smt.setLong(1, id_proyecto);
				ResultSet rs = smt.executeQuery();
				while (rs.next()) {
					vistaHtml = vistaHtml + "<TR>" +
							"<td style= 'text-align: left;'>"+rs.getString(2)+"</td>"+
							"<td style= 'text-align: left;'>"+rs.getString(3)+"</td>"+
							"<td style= 'text-align: left;'>"+rs.getString(4)+"</td>"+
							"<td style= 'text-align: left;'>"+rs.getString(5)+"</td>"+
							"<td style= 'text-align: left;'>"+rs.getString(6)+"</td>"+
							"<td style= 'text-align: left;'>"+rs.getString(7)+"</td></TR>";
				}
				vistaHtml = vistaHtml + "<TR>" + "</tbody></table>";
				rs.close();
				smt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        return (vistaHtml);
    } 
	


}
