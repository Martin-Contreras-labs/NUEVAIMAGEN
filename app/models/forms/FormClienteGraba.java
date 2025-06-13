package models.forms;

public class FormClienteGraba {
	public String rut;
	public String nombre;
	public String nickName;
	public String direccion;
	public String cod_region;
	public String cod_comuna;
	public String ciudad;
	public String giro;
	public String mailFactura;
	public String fonoContacto;
	public String contactoFactura;
	public String rutRepresentante1;
	public String nombreRepresentante1;
	public String rutRepresentante2;
	public String nombreRepresentante2;
	public String formaDePago;
	public String especialidad;

	public FormClienteGraba(String rut, String nombre, String nickName, String direccion, String cod_region,
			String cod_comuna, String ciudad, String giro, String mailFactura, String fonoContacto,
			String contactoFactura, String rutRepresentante1, String nombreRepresentante1, String rutRepresentante2,
			String nombreRepresentante2, String formaDePago, String especialidad) {
		super();
		this.rut = rut;
		this.nombre = nombre;
		this.nickName = nickName;
		this.direccion = direccion;
		this.cod_region = cod_region;
		this.cod_comuna = cod_comuna;
		this.ciudad = ciudad;
		this.giro = giro;
		this.mailFactura = mailFactura;
		this.fonoContacto = fonoContacto;
		this.contactoFactura = contactoFactura;
		this.rutRepresentante1 = rutRepresentante1;
		this.nombreRepresentante1 = nombreRepresentante1;
		this.rutRepresentante2 = rutRepresentante2;
		this.nombreRepresentante2 = nombreRepresentante2;
		this.formaDePago = formaDePago;
		this.especialidad = especialidad;
	}


	public FormClienteGraba() {
		super();
	}
}
