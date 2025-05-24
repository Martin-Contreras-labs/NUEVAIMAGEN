package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Comercial {
	public Long id;
	public String observaciones;
	public Long vigente;
	
	public String nameSucursal;
	public String nameUsuario;
	public Long id_sucursal;

	public String email;
	public String fono;
	
	public Comercial(Long id, String observaciones, Long vigente, String nameSucursal, String nameUsuario, Long id_sucursal, String email, String fono) {
		super();
		this.id = id;
		this.observaciones = observaciones;
		this.vigente = vigente;
		this.nameSucursal = nameSucursal;
		this.nameUsuario = nameUsuario;
		this.id_sucursal = id_sucursal;
		this.email = email;
		this.fono = fono;
	}

	public Comercial() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Long getVigente() {
		return vigente;
	}

	public void setVigente(Long vigente) {
		this.vigente = vigente;
	}

	public String getNameSucursal() {
		return nameSucursal;
	}

	public void setNameSucursal(String nameSucursal) {
		this.nameSucursal = nameSucursal;
	}

	public String getNameUsuario() {
		return nameUsuario;
	}

	public void setNameUsuario(String nameUsuario) {
		this.nameUsuario = nameUsuario;
	}

	public Long getId_sucursal() {
		return id_sucursal;
	}

	public void setId_sucursal(Long id_sucursal) {
		this.id_sucursal = id_sucursal;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFono() {
		return fono;
	}

	public void setFono(String fono) {
		this.fono = fono;
	}

	public static Map<Long,Comercial> mapAllComerciales(Connection con, String db){
		Map<Long,Comercial> map = new HashMap<Long,Comercial>();
		List<Comercial> allComerciales = Comercial.all(con, db);
		allComerciales.forEach(x->{
			map.put(x.getId(), x);
		});
		return(map);
	}
	
	public static List<Comercial> all(Connection con, String db) {
		List<Comercial> lista = new ArrayList<Comercial>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select " + 
							" comercial.id, " + 
							" ifnull(comercial.observaciones,''), " +
							" comercial.vigente, " + 
							" ifnull(sucursal.nombre,''), " +
							" ifnull(usuario.nombre,''), " +
							" ifnull(usuario.id_sucursal,''), " +
							" ifnull(usuario.email,''), " +
							" ifnull(usuario.fono,'') " +
							" from `"+db+"`.comercial " +
							" left join `"+db+"`.usuario on usuario.id = comercial.id "+
							" left join `"+db+"`.sucursal on sucursal.id = usuario.id_sucursal "+
							" order by sucursal.nombre, usuario.nombre;");

			ResultSet rs = smt.executeQuery();
			while (rs.next()) {		
				lista.add(new Comercial(rs.getLong(1),rs.getString(2),rs.getLong(3),rs.getString(4),rs.getString(5),rs.getLong(6),rs.getString(7),rs.getString(8)));
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<Comercial> allPorIdSucursalVig(Connection con, String db, String esPorSucursal, String id_sucursal) {
		List<Comercial> lista = new ArrayList<Comercial>();
		
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and usuario.id_sucursal = " + id_sucursal;
		}
		
		try {
			PreparedStatement smt = con
					.prepareStatement("select " + 
							" comercial.id, " +
							" ifnull(comercial.observaciones,''), " +
							" comercial.vigente, " +
							" ifnull(sucursal.nombre,''), " +
							" ifnull(usuario.nombre,''), " +
							" ifnull(usuario.id_sucursal,''), " +
							" ifnull(usuario.email,''), " +
							" ifnull(usuario.fono,'') " +
							" from `"+db+"`.comercial " +
							" left join `"+db+"`.usuario on usuario.id = comercial.id "+
							" left join `"+db+"`.sucursal on sucursal.id = usuario.id_sucursal "+
							" where usuario.vigente = 1 and comercial.vigente = 1 " + condSucursal +
							" order by usuario.nombre;");
			ResultSet rs = smt.executeQuery();
			
			while (rs.next()) {		
				lista.add(new Comercial(rs.getLong(1),rs.getString(2),rs.getLong(3),rs.getString(4),rs.getString(5),rs.getLong(6),rs.getString(7),rs.getString(8)));
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<Comercial> allPorIdSucursalSoloUsuariosVigentes(Connection con, String db, String esPorSucursal, String id_sucursal) {
		List<Comercial> lista = new ArrayList<Comercial>();
		
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and usuario.id_sucursal = " + id_sucursal;
		}
		
		try {
			PreparedStatement smt = con
					.prepareStatement("select " + 
							" comercial.id, " +
							" ifnull(comercial.observaciones,''), " +
							" comercial.vigente, " +
							" ifnull(sucursal.nombre,''), " +
							" ifnull(usuario.nombre,''), " +
							" ifnull(usuario.id_sucursal,''), " +
							" ifnull(usuario.email,''), " +
							" ifnull(usuario.fono,'') " +
							" from `"+db+"`.comercial " +
							" left join `"+db+"`.usuario on usuario.id = comercial.id "+
							" left join `"+db+"`.sucursal on sucursal.id = usuario.id_sucursal "+
							" where usuario.vigente = 1 " + condSucursal +
							" order by usuario.nombre;");
			ResultSet rs = smt.executeQuery();
			
			while (rs.next()) {		
				lista.add(new Comercial(rs.getLong(1),rs.getString(2),rs.getLong(3),rs.getString(4),rs.getString(5),rs.getLong(6),rs.getString(7),rs.getString(8)));
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static Comercial find(Connection con, String db, String id_comercial) {
		Comercial aux = new Comercial();
		try {
			PreparedStatement smt = con
					.prepareStatement("select " + 
							" comercial.id, " +
							" ifnull(comercial.observaciones,''), " +
							" comercial.vigente, " +
							" ifnull(sucursal.nombre,''), " +
							" ifnull(usuario.nombre,''), " +
							" ifnull(usuario.id_sucursal,''), " +
							" ifnull(usuario.email,''), " +
							" ifnull(usuario.fono,'') " +
							" from `"+db+"`.comercial " +
							" left join `"+db+"`.usuario on usuario.id = comercial.id "+
							" left join `"+db+"`.sucursal on sucursal.id = usuario.id_sucursal "+
							" where comercial.id=?;");
			smt.setString(1, id_comercial);
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {		
				aux = new Comercial(rs.getLong(1),rs.getString(2),rs.getLong(3),rs.getString(4),rs.getString(5),rs.getLong(6),rs.getString(7),rs.getString(8));
			}else {
				aux = null;
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}
	
	public static Comercial findPorIdUsuario(Connection con, String db, String id_usuario) {
		Comercial aux = new Comercial();
		try {
			PreparedStatement smt = con
					.prepareStatement("select " + 
							" comercial.id, " +
							" ifnull(comercial.observaciones,''), " +
							" comercial.vigente, " +
							" ifnull(sucursal.nombre,''), " +
							" ifnull(usuario.nombre,''), " +
							" ifnull(usuario.id_sucursal,''), " +
							" ifnull(usuario.email,''), " +
							" ifnull(usuario.fono,'') " +
							" from `"+db+"`.comercial " +
							" left join `"+db+"`.usuario on usuario.id = comercial.id "+
							" left join `"+db+"`.sucursal on sucursal.id = usuario.id_sucursal "+
							" where comercial.id=?;");
			smt.setString(1, id_usuario);
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {		
				aux = new Comercial(rs.getLong(1),rs.getString(2),rs.getLong(3),rs.getString(4),rs.getString(5),rs.getLong(6),rs.getString(7),rs.getString(8));
			}else {
				aux = null;
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}
	
	public static boolean modivicaVigencia(Connection con,String db, Long id_comercial, Long vigencia) {
		if(id_comercial==1){
			return (false);
		}
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("update `"+db+"`.comercial set vigente=? WHERE id = ?;");
			smt.setLong(1, vigencia);
			smt.setLong(2, id_comercial);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return(flag);
	}
	
	public static boolean agregaComercial(Connection con,String db, Long id_usuario) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("insert into `"+db+"`.comercial (id) values(?);");
			smt.setLong(1, id_usuario);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return(flag);
	}
	
	
	
	
}
