package models.api;

public class ApiNuboxDocDet {
	public String rutContraparte;
	public String razonSocialContraparte;
	public String giroContraparte;
	public String tipo;
	public String folio;
	public String secuencia;
	public String fecha;
	public String afecto;
	public String producto;			// largo maximo 80 caracteres
//	public String descripcion;
	public String cantidad;
	public String comunaContraparte;
	public String direccionContraparte;
	public String precio;
	public String valor;
//	public String ponderacionDescuento;
//	public String emailContraparte;
//	public String tipoDeServicio;
//	public String fechaPeriodoDesde;
//	public String fechaPeriodoHasta;
//	public String fechaVencimiento;
	public String codigoSucursal;
//	public String vendedor;
	public String codigoItem;
	public String unidadMedida;
//	public String codigoIMP;
//	public String montoIMP;
	public String indicadorDeTraslado;
//	public String formaDePago;
//	public String medioDePago;
//	public String terminosDePagoDias;
//	public String terminosDePagoCodigo;
	public String comunaDestino;
//	public String rutSolicitanteFactura;
//	public String productoCambioSujeto;
//	public String cantidadMontoCambioSujeto;
//	public String tipoGlobalAfecto;
//	public String valorGlobalAfecto;
//	public String tipoGlobalExento;
//	public String valorGlobalExento;
//	public String precioCambioSujeto;
//	public String descuentoMonto;
	public String rutTransportista;
	public String rutChofer;
	public String patente;
	public String nombreChofer;
	public String direccionDestino;
	public String ciudadDestino;
//	public String tipoDeDespacho;
//	public String nombreDeContacto;
	public String observacion;

	

	public ApiNuboxDocDet(String rutContraparte, String razonSocialContraparte, String giroContraparte, String tipo,
		String folio, String secuencia, String fecha, String afecto, String producto, String cantidad,
		String comunaContraparte, String direccionContraparte, String precio, String valor, String codigoSucursal,
		String codigoItem, String unidadMedida, String indicadorDeTraslado, String comunaDestino,
		String rutTransportista, String rutChofer, String patente, String nombreChofer, String direccionDestino,
		String ciudadDestino, String observacion) {
	super();
	this.rutContraparte = rutContraparte;
	this.razonSocialContraparte = razonSocialContraparte;
	this.giroContraparte = giroContraparte;
	this.tipo = tipo;
	this.folio = folio;
	this.secuencia = secuencia;
	this.fecha = fecha;
	this.afecto = afecto;
	this.producto = producto;
	this.cantidad = cantidad;
	this.comunaContraparte = comunaContraparte;
	this.direccionContraparte = direccionContraparte;
	this.precio = precio;
	this.valor = valor;
	this.codigoSucursal = codigoSucursal;
	this.codigoItem = codigoItem;
	this.unidadMedida = unidadMedida;
	this.indicadorDeTraslado = indicadorDeTraslado;
	this.comunaDestino = comunaDestino;
	this.rutTransportista = rutTransportista;
	this.rutChofer = rutChofer;
	this.patente = patente;
	this.nombreChofer = nombreChofer;
	this.direccionDestino = direccionDestino;
	this.ciudadDestino = ciudadDestino;
	this.observacion = observacion;
}



	public ApiNuboxDocDet() {
		super();
		this.rutContraparte = "";
		this.razonSocialContraparte = "";
		this.giroContraparte = "";
		this.tipo = "";
		this.folio = "";
		this.secuencia = "";
		this.fecha = "";
		this.afecto = "";
		this.producto = "";
//		this.descripcion = "";
		this.cantidad = "";
		this.comunaContraparte = "Las Condes";
		this.direccionContraparte = "";
		this.precio = "0";
		this.valor = "0";
//		this.ponderacionDescuento = "";
//		this.emailContraparte = "";
//		this.tipoDeServicio = "";
//		this.fechaPeriodoDesde = "";
//		this.fechaPeriodoHasta = "";
//		this.fechaVencimiento = "";
		this.codigoSucursal = "1"; //"82006948";
//		this.vendedor = "";
		this.codigoItem = "";
		this.unidadMedida = "UNID";
//		this.codigoIMP = "";
//		this.montoIMP = "";
		this.indicadorDeTraslado = "";
//		this.formaDePago = "";
//		this.medioDePago = "";
//		this.terminosDePagoDias = "";
//		this.terminosDePagoCodigo = "";
		this.comunaDestino = "";
//		this.rutSolicitanteFactura = "";
//		this.productoCambioSujeto = "";
//		this.cantidadMontoCambioSujeto = "";
//		this.tipoGlobalAfecto = "";
//		this.valorGlobalAfecto = "";
//		this.tipoGlobalExento = "";
//		this.valorGlobalExento = "";
//		this.precioCambioSujeto = "";
//		this.descuentoMonto = "";
		this.rutTransportista = "";
		this.rutChofer = "";
		this.patente = "";
		this.nombreChofer = "";
		this.direccionDestino = "";
		this.ciudadDestino = "";
//		this.tipoDeDespacho = "";
//		this.nombreDeContacto = "";
		this.observacion = "";
	}
	
	
	
	
	
	
	
	
}
