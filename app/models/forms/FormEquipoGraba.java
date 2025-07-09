package models.forms;

import java.util.List;

public class FormEquipoGraba {
	public Long id_grupo;
	public String codigo;
	public String nombre;
	public Long id_fabrica;
	public Long id_unidad;
	public List<Long> idAtributos;
	public List<String> valorAtributos;
	public Double kg;
	public Double m2;
	
	public String desdeMenu;
	public Long id_propiedad;


	public FormEquipoGraba(Long id_grupo, String codigo, String nombre, Long id_fabrica, Long id_unidad,
			List<Long> idAtributos, List<String> valorAtributos, Double kg, Double m2, String desdeMenu, Long id_propiedad) {
		super();
		this.id_grupo = id_grupo;
		this.codigo = codigo;
		this.nombre = nombre;
		this.id_fabrica = id_fabrica;
		this.id_unidad = id_unidad;
		this.idAtributos = idAtributos;
		this.valorAtributos = valorAtributos;
		this.kg = kg;
		this.m2 = m2;
		this.id_propiedad = id_propiedad;
		this.desdeMenu = desdeMenu;
	}



	public FormEquipoGraba() {
		super();
	}
}
