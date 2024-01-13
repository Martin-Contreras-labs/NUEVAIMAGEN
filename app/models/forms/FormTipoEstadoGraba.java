package models.forms;

public class FormTipoEstadoGraba {
	public String sigla;
	public String nombre;
	public Long id_bodegaAsociada;
	public Long reparable;

	public FormTipoEstadoGraba(String sigla, String nombre, Long id_bodegaAsociada, Long reparable) {
		super();
		this.sigla = sigla;
		this.nombre = nombre;
		this.id_bodegaAsociada = id_bodegaAsociada;
		this.reparable = reparable;
	}

	public FormTipoEstadoGraba() {
		super();
	}
}
