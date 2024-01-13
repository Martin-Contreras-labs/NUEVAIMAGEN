package models.utilities;

public class UserMnu {
	public String userName;
	public String id_usuario;
	public String id_tipoUsuario;
	public String baseDato;
	public String id_sucursal;
	public String porProyecto;
	public String aplicaPorSucursal;
	
	public UserMnu(String userName, String id_usuario, String id_tipoUsuario, String baseDato, String id_sucursal,
			String porProyecto, String aplicaPorSucursal) {
		super();
		this.userName = userName;
		this.id_usuario = id_usuario;
		this.id_tipoUsuario = id_tipoUsuario;
		this.baseDato = baseDato;
		this.id_sucursal = id_sucursal;
		this.porProyecto = porProyecto;
		this.aplicaPorSucursal = aplicaPorSucursal;

	}
	
	public UserMnu() {
		super();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getBaseDato() {
		return baseDato;
	}

	public void setBaseDato(String baseDato) {
		this.baseDato = baseDato;
	}

	public String getId_tipoUsuario() {
		return id_tipoUsuario;
	}

	public void setId_tipoUsuario(String id_tipoUsuario) {
		this.id_tipoUsuario = id_tipoUsuario;
	}

	public String getId_sucursal() {
		return id_sucursal;
	}

	public void setId_sucursal(String id_sucursal) {
		this.id_sucursal = id_sucursal;
	}
	
	public String getPorProyecto() {
		return porProyecto;
	}

	public void setPorProyecto(String porProyecto) {
		this.porProyecto = porProyecto;
	}

	public String getAplicaPorSucursal() {
		return aplicaPorSucursal;
	}

	public void setAplicaPorSucursal(String aplicaPorSucursal) {
		this.aplicaPorSucursal = aplicaPorSucursal;
	}
	
	
}
