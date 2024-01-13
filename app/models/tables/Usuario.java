package models.tables;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.forms.FormUsuarioGraba;



public class Usuario {
	public Long id;
	public String userName;
	public String userKey;
	public String nombre;
	public String cargo;
	public Long id_tipoUsuario;
	
	public Long porProyecto;
	public String tipoUsuario;
	public String paisIngreso;
	
	public String email;
	public String fono;
	public String observaciones;
	
	public Long vigente;
	public Long id_sucursal;
	public String nameSucursal;


	public Usuario(Long id, String userName, String userKey, String nombre, String cargo, Long id_tipoUsuario,
			Long porProyecto, String tipoUsuario, String paisIngreso, String email, String auxBaseDato, String fono, String observaciones, Long vigente, Long id_sucursal, String nameSucursal) {
		super();
		this.id = id;
		this.userName = userName;
		this.userKey = userKey;
		this.nombre = nombre;
		this.cargo = cargo;
		this.id_tipoUsuario = id_tipoUsuario;
		this.porProyecto = porProyecto;
		this.tipoUsuario = tipoUsuario;
		this.paisIngreso = paisIngreso;
		this.email = email;
		this.fono = fono;
		this.observaciones = observaciones;
		this.vigente = vigente;
		this.id_sucursal = id_sucursal;
		this.nameSucursal = nameSucursal;
	}
	

	public Usuario() {super();}
	

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getUserName() {return userName;}
	public void setUserName(String userName) {this.userName = userName;}
	public String getUserKey() {return userKey;}
	public void setUserKey(String userKey) {this.userKey = userKey;}
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	public String getCargo() {return cargo;}
	public void setCargo(String cargo) {this.cargo = cargo;}
	public Long getId_tipoUsuario() {return id_tipoUsuario;}
	public void setId_tipoUsuario(Long id_tipoUsuario) {this.id_tipoUsuario = id_tipoUsuario;}
	public String getTipoUsuario() {return tipoUsuario;}
	public void setTipoUsuario(String tipoUsuario) {this.tipoUsuario = tipoUsuario;}
	public Long getPorProyecto() {return porProyecto;}
	public void setPorProyecto(Long porProyecto) {this.porProyecto = porProyecto;}
	public String getPaisIngreso() {return paisIngreso;}
	public void setPaisIngreso(String paisIngreso) {this.paisIngreso = paisIngreso;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	public String getFono() {return fono;}
	public void setFono(String fono) {this.fono = fono;}
	public String getObservaciones() {return observaciones;}
	public void setObservaciones(String observaciones) {this.observaciones = observaciones;}
	public Long getVigente() {return vigente;}
	public void setVigente(Long vigente) {this.vigente = vigente;}
	public Long getId_sucursal() {return id_sucursal;}
	public void setId_sucursal(Long id_sucursal) {this.id_sucursal = id_sucursal;}
	
	public String getNameSucursal() {
		return nameSucursal;
	}

	public void setNameSucursal(String nameSucursal) {
		this.nameSucursal = nameSucursal;
	}



	static SimpleDateFormat myformatfecha = new SimpleDateFormat("dd-MM-yyyy");
	
	
	public static Map<Long,Usuario> mapAll(Connection con, String db){
		Map<Long,Usuario> map = new HashMap<Long,Usuario>();
		List<Usuario> lista = Usuario.all(con, db);
		lista.forEach(x->{
			map.put(x.getId(), x);
		});
		return(map);
	}
	
	public static Usuario findXIdUser(Connection con, String db, Long idUser) {
		Usuario aux = null;
		try {
			PreparedStatement smt = con
						.prepareStatement("SELECT usuario.id, usuario.userName, usuario.userKey, ifnull(usuario.nombre,''), ifnull(usuario.cargo,''), " +
								" usuario.id_tipoUsuario, tipoUsuario.porProyecto, ifnull(tipoUsuario.tipo,''), ifnull(usuario.email,''), ifnull(usuario.fono,''), " +
								" ifnull(usuario.observaciones,''), usuario.vigente, usuario.id_sucursal, sucursal.nombre " +
								" FROM `"+db+"`.usuario " +
								" left join `"+db+"`.tipoUsuario on tipoUsuario.id = id_tipoUsuario " +
								" left join `"+db+"`.sucursal on sucursal.id = usuario.id_sucursal " +
								" where usuario.id = ?" );
				smt.setLong(1, idUser);
				ResultSet rs = smt.executeQuery();
				if (rs.next()) {
					aux = new Usuario(rs.getLong(1),rs.getString(2),
							rs.getString(3),rs.getString(4),rs.getString(5),
							rs.getLong(6),rs.getLong(7),rs.getString(8),"",rs.getString(9),db,rs.getString(10),rs.getString(11),rs.getLong(12), rs.getLong(13),rs.getString(14));
	
				}else {
					aux = new Usuario((long)0,"","","","",(long)1,(long)0,"","","",db,"","",(long)1,(long)1,"CASA MATRIZ");
				}
				
				rs.close();
				smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (aux);
	}
	
	public static boolean modify(Connection con,String db, Usuario user) {
		boolean flag = false;
		try {
			PreparedStatement smt = con.prepareStatement("UPDATE `"+db+"`.usuario "
					+ " SET	userName = ?, userKey = ?, nombre = ?, cargo = ?, id_tipoUsuario=?, email=?, fono=?, observaciones=? "
					+ " WHERE id = ?");
			smt.setString(1, user.getUserName().trim());
			smt.setString(2, user.getUserKey().trim());
			smt.setString(3, user.getNombre().trim());
			smt.setString(4, user.getCargo().trim());
			smt.setLong(5, user.getId_tipoUsuario());
			smt.setString(6, user.getEmail().trim());
			smt.setString(7, user.getFono().trim());
			smt.setString(8, user.getObservaciones().trim());
			smt.setLong(9, user.getId());
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return(flag);
	}
	
	public static boolean existe(Connection con, String db, String userName) {
		boolean flag = false;
		try {
			PreparedStatement smt2 = con
					.prepareStatement("SELECT usuario.id, userName " +
							" FROM `"+db+"`.usuario " +
							" where upper(usuario.userName) = ?" );
			smt2.setString(1, userName.toUpperCase());
			ResultSet rs2 = smt2.executeQuery();
			if (rs2.next()) {
				flag=true;
			}
			rs2.close();
			smt2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static String vistaUpdateUsuario2(Connection con, String db, Long id_usuario) {
		Usuario usuario = Usuario.findXIdUser(con, db, id_usuario);
		String vista=
		"<form id='formUpdateUsuario2' action='/usuarioUpdate2' method='POST'>"+
			"<div id='modalUpdateUsuario2' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>"+
			  "<div class='modal-dialog modal-dialog-scrollable' role='document'>"+
			    "<div class='modal-content'>"+
			      "<div class='modal-header'>"+
			        "<h5 class='modal-title'>Modificar Usuario</h5>"+
			        "<button type='button' class='close' data-dismiss='modal' aria-label='Close'>"+
			          "<span aria-hidden='true'>&times;</span>"+
			        "</button>"+
			      "</div>"+
			      "<div class='modal-body'>"+
			        "<table class='table table-sm table-bordered table-condensed table-hover table-fluid'>"+
						"<tbody>"+
							"<tr>"+
						        "<td style='text-align:left;'>USER NAME (*):</td>"+
						        "<td style='text-align:left'>"+
						        	"<input type='hidden' name='id' value='"+id_usuario+"'>"+
						        	"<input type='text' class='form-control form-control-sm' id='userNameUpdate2' name='userName' maxlength='100' "+
						        		" autocomplete='off' onchange='value=value.trim().toUpperCase(); validaUsuarioUpdate2(value,\""+usuario.userName+"\")' value='"+usuario.userName+"' required>"+ 
						        "</td>"+
							"</tr>"+
							"<tr>"+
						        "<td style='text-align:left;'>CLAVE (*):</td>"+
						        "<td style='text-align:left'>"+
						        	"<input type='password' class='form-control form-control-sm' name='userKey' maxlength='100' "+
						        		" autocomplete='off' onchange='value=value.trim();' value='"+usuario.userKey+"' required>"+ 
						        "</td>"+
							"</tr>"+
							"<tr>"+
						        "<td style='text-align:left;'>NOMBRE (*):</td>"+
						        "<td style='text-align:left'>"+
						        	"<input type='text' class='form-control form-control-sm' name='nombre' maxlength='100' "+
						        		" autocomplete='off' onchange='value=value.trim().toUpperCase();' value='"+usuario.nombre+"' required>"+ 
						        "</td>"+
							"</tr>"+
							"<tr>"+
						        "<td style='text-align:left;'>CARGO:</td>"+
						        "<td style='text-align:left'>"+
						        	"<input type='text' class='form-control form-control-sm' name='cargo' maxlength='100' "+
						        		" autocomplete='off' onchange='value=value.trim();' value='"+usuario.cargo+"'>"+ 
						        "</td>"+
							"</tr>"+
							"<tr>"+
						        "<td style='text-align:left;'>E-MAIL:</td>"+
						        "<td style='text-align:left'>"+
						        	"<input type='text' class='form-control form-control-sm' name='email' maxlength='100' "+
						        		" autocomplete='off' onchange='value=value.trim();' value='"+usuario.email+"'>"+ 
						        "</td>"+
							"</tr>"+
							"<tr>"+
						        "<td style='text-align:left;'>FONO:</td>"+
						        "<td style='text-align:left'>"+
						        	"<input type='text' class='form-control form-control-sm' name='fono' maxlength='100' "+
						        		" autocomplete='off' onchange='value=value.trim();' value='"+usuario.fono+"'>"+
						        "</td>"+
							"</tr>"+
							"<tr>"+
						        "<td style='text-align:left;'>OBSERVACIONES:</td>"+
						        "<td style='text-align:left'>"+
						        	"<textarea class='form-control form-control-sm' rows='3' name='observaciones' autocomplete='off'>"+usuario.observaciones+"</textarea>"+
						        "</td>"+
						    "</tr>"+
						"</tbody>"+
					"</table>"+
					"<div class='row'>"+
						"<div class='col-sm-12' style='text-align:center'>"+
							"<button type='submit' class='btn btn-sm  btn-success' style='font-size: 10px;'>GRABAR</button>&nbsp;&nbsp;&nbsp; "+
							"<button type='button' class='btn btn-sm  btn-warning' style='font-size: 10px;' data-dismiss='modal'>Cancelar</button>"+
						"</div>"+
					"</div>"+
					"<br>"+
					"<font style='font-size: 12px;'>(*) Campos Obligatorios</font>"+
			      "</div>"+
			    "</div>"+
			  "</div>"+
			"</div>"+
		"</form>";
		return(vista);
	}

	public static List<Usuario> all(Connection con,String db) {
		List<Usuario> lista = new ArrayList<Usuario>();
		try {
			PreparedStatement smt = con
					.prepareStatement("SELECT usuario.id, usuario.userName, usuario.userKey, ifnull(usuario.nombre,''), ifnull(usuario.cargo,''), " +
							 	" usuario.id_tipoUsuario, tipoUsuario.porProyecto, ifnull(tipoUsuario.tipo,''), ifnull(usuario.email,''), ifnull(usuario.fono,''), " +
							 	" ifnull(usuario.observaciones,''), usuario.vigente, usuario.id_sucursal, sucursal.nombre " +
								" FROM `"+db+"`.usuario " +
								" left join `"+db+"`.tipoUsuario on tipoUsuario.id = id_tipoUsuario " +
								" left join `"+db+"`.sucursal on sucursal.id = usuario.id_sucursal " +
								" where usuario.id>1 order by userName");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				lista.add(new Usuario(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
						rs.getLong(6),rs.getLong(7),rs.getString(8),"",rs.getString(9),db,rs.getString(10),rs.getString(11), rs.getLong(12), rs.getLong(13),rs.getString(14)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<Usuario> allPorIdSucursal(Connection con, String db, String id_sucursal, String porProyecto) {
		List<Usuario> lista = new ArrayList<Usuario>();
		
		String condSucursal = "";
		if(!id_sucursal.equals("1") && !porProyecto.equals("1")) {
			condSucursal = " and usuario.id_sucursal = " + id_sucursal;
		}
		
		try {
			PreparedStatement smt = con
					.prepareStatement("SELECT usuario.id, usuario.userName, usuario.userKey, ifnull(usuario.nombre,''), ifnull(usuario.cargo,''), " +
						 		" usuario.id_tipoUsuario, tipoUsuario.porProyecto, ifnull(tipoUsuario.tipo,''), ifnull(usuario.email,''), ifnull(usuario.fono,''), " +
						 		" ifnull(usuario.observaciones,''), usuario.vigente, usuario.id_sucursal, sucursal.nombre " +
								" FROM `"+db+"`.usuario " +
								" left join `"+db+"`.tipoUsuario on tipoUsuario.id = usuario.id_tipoUsuario " +
								" left join `"+db+"`.sucursal on sucursal.id = usuario.id_sucursal " +
								" where usuario.id>1 and usuario.vigente=1 " + condSucursal +
								" order by usuario.userName");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				lista.add(new Usuario(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
						rs.getLong(6),rs.getLong(7),rs.getString(8),"",rs.getString(9),db,rs.getString(10),rs.getString(11), rs.getLong(12), rs.getLong(13),rs.getString(14)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (lista);
	}
	
	public static boolean modificaPorCampo(Connection con,String db,String campo,Long id_usuario,String valor) {
		boolean flag = false;
		try {
			PreparedStatement smt = con.prepareStatement("UPDATE `"+db+"`.usuario set `" + campo + "` = ? WHERE id = ?;");		
			smt.setString(1, valor.trim());
			smt.setLong(2, id_usuario);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean delete(Connection con,String db, Long id_usuario) {
		if(id_usuario==1){
			return (false);
		}
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("DELETE FROM `"+db+"`.usuario WHERE id = ?");
			smt.setLong(1, id_usuario);
			smt.executeUpdate();
			smt.close();
			
			PreparedStatement smt2 = con
					.prepareStatement("DELETE FROM `"+db+"`.permisoPorBodegaEmpresa WHERE id_usuario = ?");
			smt2.setLong(1, id_usuario);
			smt2.executeUpdate();
			smt2.close();
			flag = true;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return(flag);
	}
	
	public static boolean modivicaVigencia(Connection con,String db, Long id_usuario, Long vigencia) {
		if(id_usuario==1){
			return (false);
		}
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("update `"+db+"`.usuario set vigente=? WHERE id = ?;");
			smt.setLong(1, vigencia);
			smt.setLong(2, id_usuario);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return(flag);
	}
	
	public static boolean create(Connection con,String db, FormUsuarioGraba aux) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("INSERT INTO `"+db+"`.usuario (userName, userKey, nombre, cargo, id_tipoUsuario, email, fono, observaciones)"
							+ " VALUES (?,?,?,?,?,?,?,?)");
			smt.setString(1, aux.userName.trim());
			smt.setString(2, aux.userKey.trim());
			smt.setString(3, aux.nombre.trim());
			smt.setString(4, aux.cargo.trim());
			smt.setLong(5, aux.id_tipoUsuario);
			smt.setString(6, aux.email.trim());
			smt.setString(7, aux.fono.trim());
			smt.setString(8, aux.observaciones.trim());
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	
	public static List<List<Long>> listParOfIdTipoUsuarioVsEsPorProyecto(Connection con,String db){
		List<List<Long>> lista = new ArrayList<List<Long>>();
		try {
			PreparedStatement smt = con
					.prepareStatement("SELECT id, porProyecto FROM `"+db+"`.tipoUsuario;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				List<Long> aux = new ArrayList<Long>();
				aux.add(rs.getLong(1));
				aux.add(rs.getLong(2));
				lista.add(aux);
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return(lista);
	}
	
	public static List<Usuario> allUsuariosNoEnComercial(Connection con, String db, String esPorSucursal, String id_sucursal){
		List<Usuario> lista = new ArrayList<Usuario>();
		
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and usuario.id_sucursal = " + id_sucursal;
		}
		
		try {
			PreparedStatement smt = con
					.prepareStatement("SELECT usuario.id, usuario.userName, usuario.userKey, ifnull(usuario.nombre,''), ifnull(usuario.cargo,''), " +
							 	" usuario.id_tipoUsuario, tipoUsuario.porProyecto, ifnull(tipoUsuario.tipo,''), ifnull(usuario.email,''), ifnull(usuario.fono,''), " +
							 	" ifnull(usuario.observaciones,''), usuario.vigente, usuario.id_sucursal, sucursal.nombre " +
								" FROM `"+db+"`.usuario " +
								" left join `"+db+"`.tipoUsuario on tipoUsuario.id = id_tipoUsuario " +
								" left join `"+db+"`.sucursal on sucursal.id = usuario.id_sucursal " +
								" left join `"+db+"`.comercial on comercial.id = usuario.id " +
								" where usuario.id > 1 and usuario.vigente = 1 and comercial.id is null " + condSucursal
								+ " order by userName");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				lista.add(new Usuario(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
						rs.getLong(6),rs.getLong(7),rs.getString(8),"",rs.getString(9),db,rs.getString(10),rs.getString(11), rs.getLong(12), rs.getLong(13),rs.getString(14)));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (lista);
		
	}

	
	
}
	
	
	
