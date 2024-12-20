package models.forms;

public class FormUsuarioGraba {
	public String userName;
	public String userKey;
	public String nombre;
	public String cargo;
	public String email;
	public String fono;
	public Long id_tipoUsuario;
	public String observaciones;
	public Long id_sucursal;

	public FormUsuarioGraba(String userName, String userKey, String nombre, String cargo, String email, String fono,
			Long id_tipoUsuario, String observaciones, Long id_sucursal) {
		super();
		this.userName = userName;
		this.userKey = userKey;
		this.nombre = nombre;
		this.cargo = cargo;
		this.email = email;
		this.fono = fono;
		this.id_tipoUsuario = id_tipoUsuario;
		this.observaciones = observaciones;
		this.id_sucursal = id_sucursal;
	}

	public FormUsuarioGraba() {
		super();
	}
}
