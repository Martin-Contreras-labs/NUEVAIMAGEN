package models.utilities;

import models.tables.MantOperadorMecanico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;





public class Inicio {
	public Long id_empresa;
	public String empresa;
	public String baseDato;
	public String logoEmpresa;
	
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
	public Long id_sucursal;

	public Inicio(Long id_empresa, String empresa, String baseDato,
			String logoEmpresa, Long id, String userName, String userKey,
			String nombre, String cargo, Long id_tipoUsuario, Long porProyecto,String tipoUsuario,String paisIngreso,String email,Long id_sucursal) {
		super();
		this.id_empresa = id_empresa;
		this.empresa = empresa;
		this.baseDato = baseDato;
		this.logoEmpresa = logoEmpresa;
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
		this.id_sucursal = id_sucursal;
	}

	public Inicio() {super();}

	public String getEmpresa() {return empresa;}
	public void setEmpresa(String empresa) {this.empresa = empresa;}
	public String getBaseDato() {return baseDato;}
	public void setBaseDato(String baseDato) {this.baseDato = baseDato;}
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
	public String getLogoEmpresa() {return logoEmpresa;}
	public void setLogoEmpresa(String logoEmpresa) {this.logoEmpresa = logoEmpresa;}
	public Long getId_empresa() {return id_empresa;}
	public void setId_empresa(Long id_empresa) {this.id_empresa = id_empresa;}
	public Long getPorProyecto() {return porProyecto;}
	public void setPorProyecto(Long porProyecto) {this.porProyecto = porProyecto;}
	public String getPaisIngreso() {return paisIngreso;}
	public void setPaisIngreso(String paisIngreso) {this.paisIngreso = paisIngreso;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	public Long getId_sucursal() {return id_sucursal;}
	public void setId_sucursal(Long id_sucursal) {this.id_sucursal = id_sucursal;}

	public static Inicio findXempresa(Connection con, String usuario, String empresa, String pais) {
		Inicio aux = new Inicio();
		if(usuario==null||empresa==null) {
			aux.id = (long) -2; 
			return (aux);
		}
		try {
			PreparedStatement smt1 = con
					.prepareStatement("select  id, empresa, baseDato, vigente, logoEmpresa " +
							" from empresa where UPPER(empresa) = ? and UPPER(pais) = ?" );
			smt1.setString(1, empresa.toUpperCase().trim());
			smt1.setString(2, pais.toUpperCase().trim());
			ResultSet rs1 = smt1.executeQuery();
			if (rs1.next()) {
				if(rs1.getLong(4)==0){
					aux.id = (long) -1;
				}else{
					String db = rs1.getString(3);
					PreparedStatement smt = con
							.prepareStatement("select  usuario.id, userName, userKey, nombre, cargo, " +
									"id_tipoUsuario,porProyecto, tipo, email, id_sucursal from `"+db+"`.usuario " +
									"left join `"+db+"`.tipoUsuario on tipoUsuario.id = id_tipoUsuario " +
									" where UPPER(userName) = ?" );
					smt.setString(1, usuario.toUpperCase().trim());
					ResultSet rs2 = smt.executeQuery();
					if (rs2.next()) {
						aux = new Inicio(rs1.getLong(1),rs1.getString(2),rs1.getString(3),rs1.getString(5),rs2.getLong(1),
								rs2.getString(2),rs2.getString(3),rs2.getString(4),rs2.getString(5),
								rs2.getLong(6),rs2.getLong(7),rs2.getString(8),"",rs2.getString(9),rs2.getLong(10));
					}else{
						aux.id = (long) -3;
					}
					rs2.close();
					smt.close();
				}
			} else{
				aux.id = (long) -2;
			}
			rs1.close();
			smt1.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (aux);
	}
	
	public static Inicio findXempresaVigente(Connection con, String usuario, String empresa, String pais) {
		Inicio aux = new Inicio();
		if(usuario==null||empresa==null) {
			aux.id = (long) -2; 
			return (aux);
		}
		
		try {
			PreparedStatement smt1 = con
					.prepareStatement("select  id, empresa, baseDato, vigente, logoEmpresa " +
							" from mada.empresa where UPPER(empresa) = ? and UPPER(pais) = ?" );
			smt1.setString(1, empresa.toUpperCase().trim());
			smt1.setString(2, pais.toUpperCase().trim());
			ResultSet rs1 = smt1.executeQuery();
			if (rs1.next()) {
				if(rs1.getLong(4)==0){
					aux.id = (long) -1;
				}else{
					String db = rs1.getString(3);
					PreparedStatement smt = con
							.prepareStatement("select  usuario.id, userName, userKey, nombre, cargo, " +
									"id_tipoUsuario,porProyecto, tipo, email, id_sucursal from `"+db+"`.usuario " +
									"left join `"+db+"`.tipoUsuario on tipoUsuario.id = id_tipoUsuario " +
									" where UPPER(userName) = ? and vigente=1;" );
					smt.setString(1, usuario.toUpperCase().trim());
					ResultSet rs2 = smt.executeQuery();
					if (rs2.next()) {
						aux = new Inicio(rs1.getLong(1),rs1.getString(2),rs1.getString(3),rs1.getString(5),rs2.getLong(1),
								rs2.getString(2),rs2.getString(3),rs2.getString(4),rs2.getString(5),
								rs2.getLong(6),rs2.getLong(7),rs2.getString(8),"",rs2.getString(9),rs2.getLong(10));
					}else{
						aux.id = (long) -3;
					}
					rs2.close();
					smt.close();
				}
			} else{
				aux.id = (long) -2;
			}
			rs1.close();
			smt1.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (aux);
	}

	public static String findBaseDatoVigente(Connection con, String empresa, String pais) {
		String aux = "sinBd";
		try {
			PreparedStatement smt1 = con
					.prepareStatement("select  id, empresa, baseDato, vigente, logoEmpresa " +
							" from mada.empresa where UPPER(empresa) = ? and UPPER(pais) = ?" );
			smt1.setString(1, empresa.toUpperCase().trim());
			smt1.setString(2, pais.toUpperCase().trim());
			ResultSet rs1 = smt1.executeQuery();
			if (rs1.next()) {
				aux = rs1.getString(3);
			}
			rs1.close();
			smt1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}

	
	
	
}