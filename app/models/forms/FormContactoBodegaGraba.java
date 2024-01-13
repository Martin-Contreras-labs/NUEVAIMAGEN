package models.forms;

public class FormContactoBodegaGraba {
	public Long id_bodega;
	public String nombre;
	public String cargo;
	public String fijo;
	public String movil;
	public String mail;

	public FormContactoBodegaGraba(Long id_bodega, String nombre, String cargo, String fijo, String movil, String mail) {
		super();
		this.id_bodega = id_bodega;
		this.nombre = nombre;
		this.cargo = cargo;
		this.fijo = fijo;
		this.movil = movil;
		this.mail = mail;
	}

	public FormContactoBodegaGraba() {
		super();
	}
}
