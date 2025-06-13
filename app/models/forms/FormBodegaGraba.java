package models.forms;

import java.util.List;

public class FormBodegaGraba {
	public Long id_bodegaEmpresa;
	public String nombre;
	public Long id_tipoBodega;
	public Long id_propietario;
	public Long id_cliente;
	public Long id_proyecto;
	public String comercial;
	public String cfi;
	public Long cobraDiaDespacho;
	public Long baseCalculo;
	public Long tratoDevoluciones;
	public Long nDiasEnvio;
	public Long nDiasRegreso;
	public String pep;			// un codigo que usa conconcreto de colombia
	public String ivaBodega;	// 0 no aplica distinto aplica a facturas y cotizaciones por bodega
	public Long id_sucursal;
	public Long id_comercial;

	public List<Long> idsMoneda;
	public List<Double> tasaCambio;

	public Long id_rubro;

	public FormBodegaGraba(Long id_bodegaEmpresa, String nombre, Long id_tipoBodega, Long id_propietario,
			Long id_cliente, Long id_proyecto, String comercial, String cfi, Long cobraDiaDespacho, Long baseCalculo,
			Long tratoDevoluciones, Long nDiasEnvio, Long nDiasRegreso, String pep, String ivaBodega, Long id_sucursal,
			Long id_comercial, List<Long> idsMoneda, List<Double> tasaCambio, Long id_rubro) {
		super();
		this.id_bodegaEmpresa = id_bodegaEmpresa;
		this.nombre = nombre;
		this.id_tipoBodega = id_tipoBodega;
		this.id_propietario = id_propietario;
		this.id_cliente = id_cliente;
		this.id_proyecto = id_proyecto;
		this.comercial = comercial;
		this.cfi = cfi;
		this.cobraDiaDespacho = cobraDiaDespacho;
		this.baseCalculo = baseCalculo;
		this.tratoDevoluciones = tratoDevoluciones;
		this.nDiasEnvio = nDiasEnvio;
		this.nDiasRegreso = nDiasRegreso;
		this.pep = pep;
		this.ivaBodega = ivaBodega;
		this.id_sucursal = id_sucursal;
		this.id_comercial = id_comercial;
		this.idsMoneda = idsMoneda;
		this.tasaCambio = tasaCambio;
		this.id_rubro = id_rubro;
	}





	public FormBodegaGraba() {
		super();
	}
}
