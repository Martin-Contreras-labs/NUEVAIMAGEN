package models.forms;

public class FormFabricaGraba {
	public String nombre;
	public String nickName;
	public String direccion;
	public String cod_region;
	public String cod_comuna;
	public String ciudad;

	public FormFabricaGraba(String nombre, String nickName, String direccion, String cod_region, String cod_comuna,
			String ciudad) {
		super();
		this.nombre = nombre;
		this.nickName = nickName;
		this.direccion = direccion;
		this.cod_region = cod_region;
		this.cod_comuna = cod_comuna;
		this.ciudad = ciudad;
	}

	public FormFabricaGraba() {
		super();
	}
}
