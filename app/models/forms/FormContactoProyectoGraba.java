package models.forms;

public class FormContactoProyectoGraba {
	public Long id_proyecto;
	public String nombre;
	public String cargo;
	public String fijo;
	public String movil;
	public String mail;

	public FormContactoProyectoGraba(Long id_proyecto, String nombre, String cargo, String fijo, String movil, String mail) {
		super();
		this.id_proyecto = id_proyecto;
		this.nombre = nombre;
		this.cargo = cargo;
		this.fijo = fijo;
		this.movil = movil;
		this.mail = mail;
	}

	public FormContactoProyectoGraba() {
		super();
	}
}
