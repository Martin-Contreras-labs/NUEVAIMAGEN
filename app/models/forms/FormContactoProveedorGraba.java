package models.forms;

public class FormContactoProveedorGraba {
	public Long id_proveedor;
	public String nombre;
	public String cargo;
	public String fijo;
	public String movil;
	public String mail;

	public FormContactoProveedorGraba(Long id_proveedor, String nombre, String cargo, String fijo, String movil, String mail) {
		super();
		this.id_proveedor = id_proveedor;
		this.nombre = nombre;
		this.cargo = cargo;
		this.fijo = fijo;
		this.movil = movil;
		this.mail = mail;
	}

	public FormContactoProveedorGraba() {
		super();
	}
}
