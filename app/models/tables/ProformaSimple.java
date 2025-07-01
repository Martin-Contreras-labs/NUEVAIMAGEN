package models.tables;

import models.utilities.Archivos;
import models.utilities.Fechas;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.TempFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ProformaSimple {
	public Long id;
	public String fecha;
	public String desde;
	public String hasta;
	public Long id_bodegaEmpresa;
	public String docRef;
	public String epPdf;
	public String neto;
	public String iva;
	public String total;
	public String tipo;

	public ProformaSimple(Long id, String fecha, String desde, String hasta, Long id_bodegaEmpresa, String docRef, String epPdf, String neto, String iva, String total, String tipo) {
		this.id = id;
		this.fecha = fecha;
		this.desde = desde;
		this.hasta = hasta;
		this.id_bodegaEmpresa = id_bodegaEmpresa;
		this.docRef = docRef;
		this.epPdf = epPdf;
		this.neto = neto;
		this.iva = iva;
		this.total = total;
		this.tipo = tipo;
	}

	public ProformaSimple() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getDesde() {
		return desde;
	}

	public void setDesde(String desde) {
		this.desde = desde;
	}

	public String getHasta() {
		return hasta;
	}

	public void setHasta(String hasta) {
		this.hasta = hasta;
	}

	public Long getId_bodegaEmpresa() {
		return id_bodegaEmpresa;
	}

	public void setId_bodegaEmpresa(Long id_bodegaEmpresa) {
		this.id_bodegaEmpresa = id_bodegaEmpresa;
	}

	public String getDocRef() {
		return docRef;
	}

	public void setDocRef(String docRef) {
		this.docRef = docRef;
	}

	public String getEpPdf() {
		return epPdf;
	}

	public void setEpPdf(String epPdf) {
		this.epPdf = epPdf;
	}

	public String getNeto() {
		return neto;
	}

	public void setNeto(String neto) {
		this.neto = neto;
	}

	public String getIva() {
		return iva;
	}

	public void setIva(String iva) {
		this.iva = iva;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	static SimpleDateFormat myformatfecha = new SimpleDateFormat("dd-MM-yyyy");
	
	
	public static List<ProformaSimple> all(Connection con, String db) {
		List<ProformaSimple> lista = new ArrayList<ProformaSimple>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select  id, fecha, desde, hasta, id_bodegaEmpresa,"
							+ " epExcel, epPdf, neto, iva, total, tipo "
							+ " from `"+db+"`.proformaSimple"
							+ " order by fecha desc,id desc");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				String fecha = null;	if (rs.getString(2) != null) {fecha = myformatfecha.format(rs.getDate(2));}
				String desde = null;	if (rs.getString(3) != null) {desde = myformatfecha.format(rs.getDate(3));}
				String hasta = null;	if (rs.getString(4) != null) {hasta = myformatfecha.format(rs.getDate(4));}
				lista.add(new ProformaSimple(rs.getLong(1),fecha,desde,hasta,rs.getLong(5),
						rs.getString(6),rs.getString(7),
						rs.getString(8),rs.getString(9),rs.getString(10),
						rs.getString(11)));

			}
			rs.close();smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (lista);
	}

	public static ProformaSimple find(Connection con, String db, Long id_proformaSimple) {
		ProformaSimple aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement(" select  id, fecha, desde, hasta, id_bodegaEmpresa,"
							+ " epExcel, epPdf, neto, iva, total, tipo "
							+ " from `"+db+"`.proformaSimple"
							+ " where id = ?;");
			smt.setLong(1, id_proformaSimple);
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				String fecha = null;	if (rs.getString(2) != null) {fecha = myformatfecha.format(rs.getDate(2));}
				String desde = null;	if (rs.getString(3) != null) {desde = myformatfecha.format(rs.getDate(3));}
				String hasta = null;	if (rs.getString(4) != null) {hasta = myformatfecha.format(rs.getDate(4));}
				aux = new ProformaSimple(rs.getLong(1),fecha,desde,hasta,rs.getLong(5),
						rs.getString(6),rs.getString(7),
						rs.getString(8),rs.getString(9),rs.getString(10),
						rs.getString(11));

			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}

	public static List<List<String>> listadoPorPeriodo(Connection con, String db, String permisoPorBodega, String desde, String hasta, String esPorSucursal, String id_sucursal) {
		List<List<String>> lista = new ArrayList<List<String>>();
		
		try {
			permisoPorBodega = permisoPorBodega.replaceAll("movimiento", "proforma");
			PreparedStatement smt = con
					.prepareStatement(" select "
							+ " proformaSimple.id,"
							+ " proformaSimple.fecha,"
							+ " proformaSimple.desde,"
							+ " proformaSimple.hasta,"
							+ " proformaSimple.id_bodegaEmpresa,"
							+ " proformaSimple.epExcel,"
							+ " proformaSimple.epPdf,"
							+ " proformaSimple.neto, "
							+ " proformaSimple.iva,"
							+ " proformaSimple.total,"
							+ " proformaSimple.tipo"
							+ " from `"+db+"`.proformaSimple"
							+ " where proformaSimple.desde >= ? and proformaSimple.hasta <=? " +permisoPorBodega
							+ " order by proformaSimple.fecha desc,proformaSimple.id desc");
			smt.setString(1, desde);
			smt.setString(2, hasta);

			ResultSet rs = smt.executeQuery();
			Map<Long,Cliente> mapCliente = Cliente.mapAllClientes(con, db);
			Map<Long,BodegaEmpresa> mapBodega = BodegaEmpresa.mapAll(con, db);
			Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
			Map<Long,Rubro> mapRubro = Rubro.mapAll(con, db);
		
			while (rs.next()) {
				String fecha = null;
				if (rs.getString(2) != null) {fecha = myformatfecha.format(rs.getDate(2));}
				if (rs.getString(3) != null) {desde = myformatfecha.format(rs.getDate(3));}
				if (rs.getString(4) != null) {hasta = myformatfecha.format(rs.getDate(4));}




				BodegaEmpresa bodegaEmpresa = mapBodega.get(rs.getLong(5));
				String nomBodega = "";
				String nameSucursal = "";
				String auxIdSucursal = "0";
				String nameComercial = "";
				String rutCliente = "";
				String nickCliente = "";
				String nombreRubro = "";
				if(bodegaEmpresa!=null) {
					nomBodega = bodegaEmpresa.getNombre();
					nameSucursal = bodegaEmpresa.getNameSucursal();
					auxIdSucursal = bodegaEmpresa.getId_sucursal().toString();

					Comercial comercial = mapComercial.get(bodegaEmpresa.getId_comercial());
					if(comercial!=null) {
						nameComercial = comercial.getNameUsuario();
					}else {
						nameComercial = bodegaEmpresa.getComercial();
					}

					Cliente cliente = mapCliente.get(bodegaEmpresa.getId_cliente());
					if(cliente!=null) {
						rutCliente = cliente.getRut();
						nickCliente = cliente.getNickName();
					}

					Rubro rubro = mapRubro.get(bodegaEmpresa.getId_rubro());
					if(rubro!=null) {
						nombreRubro = rubro.getNombre();
					}else {
						nombreRubro = "SIN RUBRO";
					}

					if(nomBodega.length()>20) {
						nomBodega = nomBodega.substring(0, 20)+"...";
					}
					
				}

				String neto = rs.getString(8);
				String iva = rs.getString(9);
				String total = rs.getString(10);
				
	   			 List<String> aux = new ArrayList<String>();
	   			 
	   			 aux.add(rs.getString(1)); 		//0 numeroID
	   			 aux.add(rs.getString(11));		//1 tipo
	   			 aux.add(fecha);				//2 fecha
	   			 aux.add(desde);				//3 desde
	   			 aux.add(hasta);				//4 hasta
	   			 aux.add(rutCliente);			//5 rut
	   			 aux.add(nickCliente);			//6 cliente
				 aux.add(nameSucursal);			//7 nameSucursal
				 aux.add(nameComercial);		//8 nameComercial
				 aux.add(nombreRubro);			//9 rubro
	   			 aux.add(nomBodega);			//10 bodega
	   			 aux.add(neto);					//11 neto
	   			 aux.add(iva);					//12 iva
	   			 aux.add(total);				//13 total
	   			 aux.add(rs.getString(6));		//14 ep pdf
	   			 aux.add(rs.getString(7));		//15 excel ep
	   			 
	   			 if(esPorSucursal.equals("1")) {
	   				 if(auxIdSucursal.equals(id_sucursal)) {
	   					lista.add(aux);
	   				 }
	   			 }else {
	   				lista.add(aux);
	   			 }
	   			 
			}
			rs.close();smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (lista);
	}

	public static boolean eliminaProforma(Connection con, String db, Long id_proforma) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("delete from `"+db+"`.proformaSimple where id = ?;");
			smt.setLong(1, id_proforma);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();flag=false;
		}
		return (flag);
	}
	

	
	
}
