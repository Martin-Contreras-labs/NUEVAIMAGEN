package models.forms;

public class FormContactoClienteGraba {
	public Long id_cliente;
	public String nombre;
	public String cargo;
	public String fijo;
	public String movil;
	public String mail;

	public FormContactoClienteGraba(Long id_cliente, String nombre, String cargo, String fijo, String movil, String mail) {
		super();
		this.id_cliente = id_cliente;
		this.nombre = nombre;
		this.cargo = cargo;
		this.fijo = fijo;
		this.movil = movil;
		this.mail = mail;
	}

	public FormContactoClienteGraba() {
		super();
	}
}
