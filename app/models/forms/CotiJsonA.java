package models.forms;

public class CotiJsonA {
	public String id_equipo;
	public String cantidad;
	public String esVenta;
	public String puReposicion;
	public String puArriendo;
	public String permanencia;
	public String nroDecimal;
	
	
	public String id_servicio;
	public String precio;
	public String aplicaMinimo;
	public String cantidadMinimo;
	public String precioAdicional;

	public CotiJsonA(String id_equipo, String cantidad, String esVenta, String puReposicion, String puArriendo,
			String permanencia, String nroDecimal, String id_servicio, String precio, String aplicaMinimo,
			String cantidadMinimo, String precioAdicional) {
		super();
		this.id_equipo = id_equipo;
		this.cantidad = cantidad;
		this.esVenta = esVenta;
		this.puReposicion = puReposicion;
		this.puArriendo = puArriendo;
		this.permanencia = permanencia;
		this.nroDecimal = nroDecimal;
		this.id_servicio = id_servicio;
		this.precio = precio;
		this.aplicaMinimo = aplicaMinimo;
		this.cantidadMinimo = cantidadMinimo;
		this.precioAdicional = precioAdicional;
	}


	public CotiJsonA() {
		super();
		this.id_equipo = "0";
		this.cantidad = "0";
		this.esVenta = "0";
		this.puReposicion = "0";
		this.puArriendo = "0";
		this.permanencia = "0";
		this.nroDecimal = "0";
		
		this.id_servicio = "0";
		this.precio = "0";
		this.aplicaMinimo = "0";
		this.cantidadMinimo = "0";
		this.precioAdicional = "0";
	}

}



