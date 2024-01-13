package models.api;

public class ApiManagerDocDet {
	public String cod_producto;
	public String cantidad;
	public String unidad;
	public String precio_unit;
	public String moneda_det;
	public String tasa_cambio_det;
	public String nro_serie;
	public String num_lote;
	public String fecha_vec;
	public String cen_cos;
	public String tipo_desc;
	public String descuento;
	public String ubicacion;
	public String bodega;
	public String concepto1;
	public String concepto2;
	public String concepto3;
	public String concepto4;
	public String descrip;
	public String desc_adic;
	public String stock;
	public String cod_impesp1;
	public String mon_impesp1;
	public String cod_impesp2;
	public String mon_impesp2;



	public ApiManagerDocDet(String cod_producto, String cantidad, String unidad, String precio_unit, String moneda_det,
			String tasa_cambio_det, String nro_serie, String num_lote, String fecha_vec, String cen_cos,
			String tipo_desc, String descuento, String ubicacion, String bodega, String concepto1, String concepto2,
			String concepto3, String concepto4, String descrip, String desc_adic, String stock, String cod_impesp1,
			String mon_impesp1, String cod_impesp2, String mon_impesp2) {
		super();
		this.cod_producto = cod_producto;
		this.cantidad = cantidad;
		this.unidad = unidad;
		this.precio_unit = precio_unit;
		this.moneda_det = moneda_det;
		this.tasa_cambio_det = tasa_cambio_det;
		this.nro_serie = nro_serie;
		this.num_lote = num_lote;
		this.fecha_vec = fecha_vec;
		this.cen_cos = cen_cos;
		this.tipo_desc = tipo_desc;
		this.descuento = descuento;
		this.ubicacion = ubicacion;
		this.bodega = bodega;
		this.concepto1 = concepto1;
		this.concepto2 = concepto2;
		this.concepto3 = concepto3;
		this.concepto4 = concepto4;
		this.descrip = descrip;
		this.desc_adic = desc_adic;
		this.stock = stock;
		this.cod_impesp1 = cod_impesp1;
		this.mon_impesp1 = mon_impesp1;
		this.cod_impesp2 = cod_impesp2;
		this.mon_impesp2 = mon_impesp2;
	}



	public ApiManagerDocDet() {
		super();
		this.cod_producto = "";
		this.cantidad = "0";
		this.unidad = "UNS";
		this.precio_unit = "0";
		this.moneda_det = "";
		this.tasa_cambio_det = "1";
		this.nro_serie = "";
		this.num_lote = "";
		this.fecha_vec = "";
		this.cen_cos = "";
		this.tipo_desc = "";
		this.descuento = "0";
		this.ubicacion = "U1";
		this.bodega = "B1";
		this.concepto1 = "";
		this.concepto2 = "";
		this.concepto3 = "";
		this.concepto4 = "";
		this.descrip = "";
		this.desc_adic = "";
		this.stock = "1";
		this.cod_impesp1 = "";
		this.mon_impesp1 = "";
		this.cod_impesp2 = "";
		this.mon_impesp2 = "";
		
	}
	
	
	
	
	
	
	
	
}
