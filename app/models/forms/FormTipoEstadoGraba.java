package models.forms;

public class FormTipoEstadoGraba {
	public String sigla;
	public String nombre;
	public Long id_bodegaAsociada;
	public Long reparable;
	public Long cobraArriendo;

	public FormTipoEstadoGraba(String sigla, String nombre, Long id_bodegaAsociada, Long reparable, Long cobraArriendo) {
		this.sigla = sigla;
		this.nombre = nombre;
		this.id_bodegaAsociada = id_bodegaAsociada;
		this.reparable = reparable;
		this.cobraArriendo = cobraArriendo;
	}

	public FormTipoEstadoGraba() {
		super();
	}
}
