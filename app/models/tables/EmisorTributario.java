package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class EmisorTributario {
	public Long id;
	public String rut;
	public String razonSocial; 
	public String nombreFantasia; 
	public String giro;   // no codigo escribir en texto
	public String actividadEconomica;  //slo este campo con codigo del SII
	public String direccion;
	public String region; // no codigo escribir en texto
	public String comuna; // no codigo escribir en texto
	public String ciudad;
	public String pais;
	public Double tasaIva;  // tasa por 100
	public String email;
	public String fonoFijo;
	public String fonoMovil;
	public String fonoFax;
	public String rutRepresentante1;
	public String nombreRepresentante1;
	public String rutRepresentante2;
	public String nombreRepresentante2;
	public String emailEnvioXML;
	public String apiUser;
	public String apiKey;
	public String apiUrl;
	public String codigoSucursal;
	public String rutFuncionario;
	
	public String apiUserIConstruyeOC;
	public String apiKeyIConstruyeOC;
	public String apiUrlIConstruyeOC0;
	public String apiUrlIConstruyeOC1;
	public String apiUrlIConstruyeOC2;
	public String apiUrlIConstruyeOC3;
	
	

	public EmisorTributario(Long id, String rut, String razonSocial, String nombreFantasia, String giro,
			String actividadEconomica, String direccion, String region, String comuna, String ciudad, String pais,
			Double tasaIva, String email, String fonoFijo, String fonoMovil, String fonoFax, String rutRepresentante1,
			String nombreRepresentante1, String rutRepresentante2, String nombreRepresentante2, String emailEnvioXML,
			String apiUser, String apiKey, String apiUrl, String codigoSucursal, String rutFuncionario,
			String apiUserIConstruyeOC, String apiKeyIConstruyeOC, String apiUrlIConstruyeOC0, String apiUrlIConstruyeOC1,
			String apiUrlIConstruyeOC2, String apiUrlIConstruyeOC3) {
		super();
		this.id = id;
		this.rut = rut;
		this.razonSocial = razonSocial;
		this.nombreFantasia = nombreFantasia;
		this.giro = giro;
		this.actividadEconomica = actividadEconomica;
		this.direccion = direccion;
		this.region = region;
		this.comuna = comuna;
		this.ciudad = ciudad;
		this.pais = pais;
		this.tasaIva = tasaIva;
		this.email = email;
		this.fonoFijo = fonoFijo;
		this.fonoMovil = fonoMovil;
		this.fonoFax = fonoFax;
		this.rutRepresentante1 = rutRepresentante1;
		this.nombreRepresentante1 = nombreRepresentante1;
		this.rutRepresentante2 = rutRepresentante2;
		this.nombreRepresentante2 = nombreRepresentante2;
		this.emailEnvioXML = emailEnvioXML;
		this.apiUser = apiUser;
		this.apiKey = apiKey;
		this.apiUrl = apiUrl;
		this.codigoSucursal = codigoSucursal;
		this.rutFuncionario = rutFuncionario;
		this.apiUserIConstruyeOC = apiUserIConstruyeOC;
		this.apiKeyIConstruyeOC = apiKeyIConstruyeOC;
		this.apiUrlIConstruyeOC0 = apiUrlIConstruyeOC0;
		this.apiUrlIConstruyeOC1 = apiUrlIConstruyeOC1;
		this.apiUrlIConstruyeOC2 = apiUrlIConstruyeOC2;
		this.apiUrlIConstruyeOC3 = apiUrlIConstruyeOC3;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getNombreFantasia() {
		return nombreFantasia;
	}

	public void setNombreFantasia(String nombreFantasia) {
		this.nombreFantasia = nombreFantasia;
	}

	public String getGiro() {
		return giro;
	}

	public void setGiro(String giro) {
		this.giro = giro;
	}

	public String getActividadEconomica() {
		return actividadEconomica;
	}

	public void setActividadEconomica(String actividadEconomica) {
		this.actividadEconomica = actividadEconomica;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getComuna() {
		return comuna;
	}

	public void setComuna(String comuna) {
		this.comuna = comuna;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public Double getTasaIva() {
		return tasaIva;
	}

	public void setTasaIva(Double tasaIva) {
		this.tasaIva = tasaIva;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFonoFijo() {
		return fonoFijo;
	}

	public void setFonoFijo(String fonoFijo) {
		this.fonoFijo = fonoFijo;
	}

	public String getFonoMovil() {
		return fonoMovil;
	}

	public void setFonoMovil(String fonoMovil) {
		this.fonoMovil = fonoMovil;
	}

	public String getFonoFax() {
		return fonoFax;
	}

	public void setFonoFax(String fonoFax) {
		this.fonoFax = fonoFax;
	}

	public String getRutRepresentante1() {
		return rutRepresentante1;
	}

	public void setRutRepresentante1(String rutRepresentante1) {
		this.rutRepresentante1 = rutRepresentante1;
	}

	public String getNombreRepresentante1() {
		return nombreRepresentante1;
	}

	public void setNombreRepresentante1(String nombreRepresentante1) {
		this.nombreRepresentante1 = nombreRepresentante1;
	}

	public String getRutRepresentante2() {
		return rutRepresentante2;
	}

	public void setRutRepresentante2(String rutRepresentante2) {
		this.rutRepresentante2 = rutRepresentante2;
	}

	public String getNombreRepresentante2() {
		return nombreRepresentante2;
	}

	public void setNombreRepresentante2(String nombreRepresentante2) {
		this.nombreRepresentante2 = nombreRepresentante2;
	}

	public String getEmailEnvioXML() {
		return emailEnvioXML;
	}

	public void setEmailEnvioXML(String emailEnvioXML) {
		this.emailEnvioXML = emailEnvioXML;
	}

	public String getApiUser() {
		return apiUser;
	}

	public void setApiUser(String apiUser) {
		this.apiUser = apiUser;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public EmisorTributario() {
		super();
	}
	public String getCodigoSucursal() {
		return codigoSucursal;
	}
	
	public void setCodigoSucursal(String codigoSucursal) {
		this.codigoSucursal = codigoSucursal;
	}

	public String getRutFuncionario() {
		return rutFuncionario;
	}

	public void setRutFuncionario(String rutFuncionario) {
		this.rutFuncionario = rutFuncionario;
	}

	public String getApiUserIConstruyeOC() {
		return apiUserIConstruyeOC;
	}

	public void setApiUserIConstruyeOC(String apiUserIConstruyeOC) {
		this.apiUserIConstruyeOC = apiUserIConstruyeOC;
	}

	public String getApiKeyIConstruyeOC() {
		return apiKeyIConstruyeOC;
	}

	public void setApiKeyIConstruyeOC(String apiKeyIConstruyeOC) {
		this.apiKeyIConstruyeOC = apiKeyIConstruyeOC;
	}

	public String getApiUrlIConstruyeOC0() {
		return apiUrlIConstruyeOC0;
	}

	public void setApiUrlIConstruyeOC0(String apiUrlIConstruyeOC0) {
		this.apiUrlIConstruyeOC0 = apiUrlIConstruyeOC0;
	}

	public String getApiUrlIConstruyeOC1() {
		return apiUrlIConstruyeOC1;
	}

	public void setApiUrlIConstruyeOC1(String apiUrlIConstruyeOC1) {
		this.apiUrlIConstruyeOC1 = apiUrlIConstruyeOC1;
	}

	public String getApiUrlIConstruyeOC2() {
		return apiUrlIConstruyeOC2;
	}

	public void setApiUrlIConstruyeOC2(String apiUrlIConstruyeOC2) {
		this.apiUrlIConstruyeOC2 = apiUrlIConstruyeOC2;
	}

	public String getApiUrlIConstruyeOC3() {
		return apiUrlIConstruyeOC3;
	}

	public void setApiUrlIConstruyeOC3(String apiUrlIConstruyeOC3) {
		this.apiUrlIConstruyeOC3 = apiUrlIConstruyeOC3;
	}

	public static EmisorTributario find(Connection con, String db) {
		EmisorTributario aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement(" select id,rut,razonSocial,nombreFantasia,giro,actividadEconomica,direccion,region,"
							+ " comuna,ciudad,pais,tasaIva,email,fonoFijo,fonoMovil,fonoFax,rutRepresentante1,nombreRepresentante1,"
							+ " rutRepresentante2,nombreRepresentante2,emailEnvioXML,apiUser,apiKey,apiUrl,codigoSucursal,rutFuncionario, "
							+ " apiUrlIConstruyeOC0, apiUserIConstruyeOC, apiKeyIConstruyeOC, apiUrlIConstruyeOC1, apiUrlIConstruyeOC2, apiUrlIConstruyeOC3 "
							+ " from `"+db+"`.emisorTributario;" );
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {				
				aux = new EmisorTributario(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)
						,rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getDouble(12),
						rs.getString(13),rs.getString(14),rs.getString(15),rs.getString(16),rs.getString(17),rs.getString(18),
						rs.getString(19),rs.getString(20),rs.getString(21),rs.getString(22),rs.getString(23),rs.getString(24),rs.getString(25),rs.getString(26),
						rs.getString(27),rs.getString(28),rs.getString(29),rs.getString(30),rs.getString(31),rs.getString(32));
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}
	
	public static boolean update(Connection con, String db, EmisorTributario emisor) {
		try {
			PreparedStatement smt = con
					.prepareStatement(" update `"+db+"`.emisorTributario set rut=?,razonSocial=?, "
							+ " nombreFantasia=?,giro=?,actividadEconomica=?,direccion=?,region=?,comuna=?,"
							+ "	ciudad=?,pais=?,tasaIva=?,email=?,fonoFijo=?,fonoMovil=?,fonoFax=?,"
							+ "	rutRepresentante1=?,nombreRepresentante1=?,rutRepresentante2=?,nombreRepresentante2=?,emailEnvioXML=?, "
							+ " apiUser=?,apiKey=?,apiUrl=?,codigoSucursal=?, rutFuncionario=?,"
							+ " apiUrlIConstruyeOC0=?, apiUserIConstruyeOC=?, apiKeyIConstruyeOC=?, apiUrlIConstruyeOC1=?, apiUrlIConstruyeOC2=?, apiUrlIConstruyeOC3=? "
							+ " where id=?;" );
			smt.setString(1, emisor.rut);
			smt.setString(2, emisor.razonSocial);
			smt.setString(3, emisor.nombreFantasia);
			smt.setString(4, emisor.giro);
			smt.setString(5, emisor.actividadEconomica);
			smt.setString(6, emisor.direccion);
			smt.setString(7, emisor.region);
			smt.setString(8, emisor.comuna);
			smt.setString(9, emisor.ciudad);
			smt.setString(10, emisor.pais);
			smt.setDouble(11, emisor.tasaIva);
			smt.setString(12, emisor.email);
			smt.setString(13, emisor.fonoFijo);
			smt.setString(14, emisor.fonoMovil);
			smt.setString(15, emisor.fonoFax);
			smt.setString(16, emisor.rutRepresentante1);
			smt.setString(17, emisor.nombreRepresentante1);
			smt.setString(18, emisor.rutRepresentante2);
			smt.setString(19, emisor.nombreRepresentante2);
			smt.setString(20, emisor.emailEnvioXML);
			smt.setString(21, emisor.apiUser);
			smt.setString(22, emisor.apiKey);
			smt.setString(23, emisor.apiUrl);
			smt.setString(24, emisor.codigoSucursal);
			smt.setString(25, emisor.rutFuncionario);
			smt.setString(26, emisor.apiUserIConstruyeOC);
			smt.setString(27, emisor.apiKeyIConstruyeOC);
			smt.setString(28, emisor.apiUrlIConstruyeOC0);
			smt.setString(29, emisor.apiUrlIConstruyeOC1);
			smt.setString(30, emisor.apiUrlIConstruyeOC2);
			smt.setString(31, emisor.apiUrlIConstruyeOC3);
			smt.setLong(32, emisor.id);
			smt.executeUpdate();
			smt.close();
			return(true);
		} catch (SQLException e) {
			e.printStackTrace();
			return(false);
		}
	}

	
	
	
	
	
}
