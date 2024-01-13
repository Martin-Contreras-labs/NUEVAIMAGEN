package models.forms;

public class FormProveedorGraba {
	public String rut;
	public String nombre;
	public String nickName;
	public String direccion;
	public String cod_region;
	public String cod_comuna;
	public String ciudad;
	public String formaDePago;
	public String especialidad;

	public FormProveedorGraba(String rut, String nombre, String nickName, String direccion, String cod_region, String cod_comuna,
			String ciudad, String formaDePago, String especialidad) {
		super();
		this.rut = rut;
		this.nombre = nombre;
		this.nickName = nickName;
		this.direccion = direccion;
		this.cod_region = cod_region;
		this.cod_comuna = cod_comuna;
		this.ciudad = ciudad;
		this.formaDePago = formaDePago;
		this.especialidad = especialidad;
	}

	public FormProveedorGraba() {
		super();
	}
}
