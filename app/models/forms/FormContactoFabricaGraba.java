package models.forms;

public class FormContactoFabricaGraba {
	public Long id_fabrica;
	public String nombre;
	public String cargo;
	public String fijo;
	public String movil;
	public String mail;

	public FormContactoFabricaGraba(Long id_fabrica, String nombre, String cargo, String fijo, String movil, String mail) {
		super();
		this.id_fabrica = id_fabrica;
		this.nombre = nombre;
		this.cargo = cargo;
		this.fijo = fijo;
		this.movil = movil;
		this.mail = mail;
	}

	public FormContactoFabricaGraba() {
		super();
	}
}
