package models.forms;


public class FormServicioGraba {
	public Long id;
	public String codigo;
	public String nombre;
	public Long id_unidadServicio;
	public Long id_claseServicio;
	public String descripcion;
	public Long id_moneda;
	public String fecha;
	public String precio;
	
	public String cambioElprecio;
	

	public FormServicioGraba(Long id, String codigo, String nombre, Long id_unidadServicio, Long id_claseServicio,
			String descripcion, Long id_moneda, String fecha, String precio) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
		this.id_unidadServicio = id_unidadServicio;
		this.id_claseServicio = id_claseServicio;
		this.descripcion = descripcion;
		this.id_moneda = id_moneda;
		this.fecha = fecha;
		this.precio = precio;
		this.cambioElprecio = "0";
	}




	public FormServicioGraba() {
		super();
	}
}
