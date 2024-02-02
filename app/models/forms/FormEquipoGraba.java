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
	
	public String desdeMenu;

	public FormEquipoGraba(Long id_grupo, String codigo, String nombre, Long id_fabrica, Long id_unidad,
			List<Long> idAtributos, List<String> valorAtributos, String desdeMenu) {
		super();
		this.id_grupo = id_grupo;
		this.codigo = codigo;
		this.nombre = nombre;
		this.id_fabrica = id_fabrica;
		this.id_unidad = id_unidad;
		this.idAtributos = idAtributos;
		this.valorAtributos = valorAtributos;
		this.desdeMenu = desdeMenu;
	}

	public FormEquipoGraba() {
		super();
	}
}
