package models.forms;

import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.List;

import models.tables.Compra;
import models.tables.Factura;
import models.utilities.Archivos;
import play.libs.Files.TemporaryFile;
import play.mvc.Http;

public class FormCompra {
	public Long id_factura;
	public Long id_proveedor;
	public Long numeroFactura;
	public String fechaFactura;
	public String observaciones;
	public List<Long> id_equipo;
	public List<String> cantidad;
	public List<Long> id_monedaCompra;
	public List<String> puCompra;
	public List<Long> id_bodegaDestino;
	public String numOcIConstruye;

	public FormCompra(Long id_factura, Long id_proveedor, Long numeroFactura, String fechaFactura, String observaciones,
			List<Long> id_equipo, List<String> cantidad, List<Long> id_monedaCompra, List<String> puCompra,
			List<Long> id_bodegaDestino, String numOcIConstruye) {
		super();
		this.id_factura = id_factura;
		this.id_proveedor = id_proveedor;
		this.numeroFactura = numeroFactura;
		this.fechaFactura = fechaFactura;
		this.observaciones = observaciones;
		this.id_equipo = id_equipo;
		this.cantidad = cantidad;
		this.id_monedaCompra = id_monedaCompra;
		this.puCompra = puCompra;
		this.id_bodegaDestino = id_bodegaDestino;
		this.numOcIConstruye = numOcIConstruye;
	}

	public FormCompra() {
		super();
	}
	
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0");
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	
	public static boolean create (Connection con, String db, FormCompra form, Http.MultipartFormData.FilePart<TemporaryFile> docAdjunto, String numOcIConstruye, String id_userCrea) {
		boolean flag = false;
		Factura factura = new Factura(form.id_proveedor, form.numeroFactura, form.fechaFactura, form.observaciones);
		Long id_factura = Factura.create(con, db, factura, numOcIConstruye, id_userCrea);
		if(id_factura > (long)0) {
			String detalle = "";
			for(int i=0; form.id_equipo!=null && i<form.id_equipo.size(); i++) {
				detalle += "('"+id_factura+"','"+form.id_equipo.get(i)+"','"+form.cantidad.get(i).replaceAll(",", "")+"','"+form.id_monedaCompra.get(i)+"','"+
						form.puCompra.get(i).replaceAll(",", "")+"','"+form.id_bodegaDestino.get(i)+"'),";
			}
			if(form.id_equipo!=null) {
				detalle = detalle.substring(0,detalle.length()-1);
			}
			
			if(detalle.length()>2) {
				if(!Compra.create(con, db, detalle)) {
					Factura.delete(con, db, id_factura);
				}else {
					String path = "0";
					if (docAdjunto != null) {
						String nombreArchivoSinExtencion = "Doc_Compra_" + id_factura;
						path = Archivos.grabarArchivos(docAdjunto, db, nombreArchivoSinExtencion);
						Factura.modifyXCampo(con, db, "facturaPDF", path, id_factura);
					}
					flag = true;
				}
			}
		}
		return(flag);
	}
	
	public static boolean update (Connection con, String db, FormCompra form, Http.MultipartFormData.FilePart<TemporaryFile> docAdjunto) {
		boolean flag = false;
		Long id_factura = form.id_factura;
		if(id_factura > (long)0) {
			String detalle = "";
			
			for(int i=0; form.id_equipo!=null && i<form.id_equipo.size(); i++) {
				detalle += "('"+id_factura+"','"+form.id_equipo.get(i)+"','"+form.cantidad.get(i).replaceAll(",", "")+"','"+form.id_monedaCompra.get(i)+"','"+
						form.puCompra.get(i).replaceAll(",", "")+"','"+form.id_bodegaDestino.get(i)+"'),";
			}
			if(form.id_equipo!=null) {
				detalle = detalle.substring(0,detalle.length()-1);
			}
			if(detalle.length()>2) {
				Compra.create(con, db, detalle);
			}
			String path = "0";
			if (docAdjunto != null) {
				String nombreArchivoSinExtencion = "Doc_Compra_" + id_factura;
				path = Archivos.grabarArchivos(docAdjunto, db, nombreArchivoSinExtencion);
				Factura.modifyXCampo(con, db, "facturaPDF", path, id_factura);
			}
			flag = true;
		}
		return(flag);
	}
	
	
	
	
	
	
}














