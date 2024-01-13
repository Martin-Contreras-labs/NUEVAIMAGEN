package models.forms;

public class FormTipoReparacionGraba {
	public Long id_tipoEstado;
	public String sigla;
	public String nombre;
	public Long id_moneda;
	public Double precio;
	public String descripcion;

	public FormTipoReparacionGraba(Long id_tipoEstado, String sigla, String nombre, Long id_moneda, Double precio,
			String descripcion) {
		super();
		this.id_tipoEstado = id_tipoEstado;
		this.sigla = sigla;
		this.nombre = nombre;
		this.id_moneda = id_moneda;
		this.precio = precio;
		this.descripcion = descripcion;
	}

	public FormTipoReparacionGraba() {
		super();
	}
}
