package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ProformaOdo {
	public Long id;
	public String fecha;
	public String desde;
	public String hasta;
	public Long id_cliente;
	public Long id_bodegaEmpresa;
	public Long id_proyecto;
	public String docRef;
	public String epExcelMov;
	public String epExcelEp;
	public String proformaPdf;
	public String docAnexo;
	public Double descuento;
	public Double neto;
	public Double iva;
	public Double total;
	public Long esEliminable;
	public String proformaXml;
	public Long xmlEnviado;
	public String jsonGenerado;
	
	public Double netoSinAjustes;
	public Double netoSoloAjustes;
	

	public ProformaOdo(Long id, String fecha, String desde, String hasta, Long id_cliente, Long id_bodegaEmpresa,
			Long id_proyecto, String docRef, String epExcelMov, String epExcelEp, String proformaPdf, String docAnexo,
			Double descuento, Double neto, Double iva, Double total, Long esEliminable, String proformaXml,
			Long xmlEnviado, String jsonGenerado, Double netoSinAjustes, Double netoSoloAjustes) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.desde = desde;
		this.hasta = hasta;
		this.id_cliente = id_cliente;
		this.id_bodegaEmpresa = id_bodegaEmpresa;
		this.id_proyecto = id_proyecto;
		this.docRef = docRef;
		this.epExcelMov = epExcelMov;
		this.epExcelEp = epExcelEp;
		this.proformaPdf = proformaPdf;
		this.docAnexo = docAnexo;
		this.descuento = descuento;
		this.neto = neto;
		this.iva = iva;
		this.total = total;
		this.esEliminable = esEliminable;
		this.proformaXml = proformaXml;
		this.xmlEnviado = xmlEnviado;
		this.jsonGenerado = jsonGenerado;
		this.netoSinAjustes = netoSinAjustes;
		this.netoSoloAjustes = netoSoloAjustes;
	}

	public ProformaOdo() {
		super();
	}
	
	public Long getId() {return id;}
	public void setId(Long id){this.id = id;}
	public String getFecha() {return fecha;}
	public void setFecha(String fecha){this.fecha = fecha;}
	public String getDesde() {return desde;}
	public void setDesde(String desde){this.desde = desde;}
	public String getHasta() {return hasta;}
	public void setHasta(String hasta){this.hasta = hasta;}
	public Long getId_cliente() {return id_cliente;}
	public void setId_cliente(Long id_cliente){this.id_cliente = id_cliente;}
	public Long getId_bodegaEmpresa() {return id_bodegaEmpresa;}
	public void setId_bodegaEmpresa(Long id_bodegaEmpresa){this.id_bodegaEmpresa = id_bodegaEmpresa;}
	public Long getId_proyecto() {return id_proyecto;}
	public void setId_proyecto(Long id_proyecto){this.id_proyecto = id_proyecto;}
	public String getDocRef() {return docRef;}
	public void setDocRef(String docRef){this.docRef = docRef;}
	public String getEpExcelMov() {return epExcelMov;}
	public void setEpExcelMov(String epExcelMov){this.epExcelMov = epExcelMov;}
	public String getEpExcelEp() {return epExcelEp;}
	public void setEpExcelEp(String epExcelEp){this.epExcelEp = epExcelEp;}
	public String getProformaPdf() {return proformaPdf;}
	public void setProformaPdf(String proformaPdf){this.proformaPdf = proformaPdf;}
	public String getDocAnexo() {return docAnexo;}
	public void setDocAnexo(String docAnexo){this.docAnexo = docAnexo;}
	public Double getDescuento() {return descuento;}
	public void setDescuento(Double descuento){this.descuento = descuento;}
	public Double getNeto() {return neto;}
	public void setNeto(Double neto) {this.neto = neto;}
	public Double getIva() {return iva;}
	public void setIva(Double iva) {this.iva = iva;}
	public Double getTotal() {return total;}
	public void setTotal(Double total) {this.total = total;}
	public Long getEsEliminable() {return esEliminable;}
	public void setEsEliminable(Long esEliminable) {this.esEliminable = esEliminable;}
	public String getProformaXml() {return proformaXml;}
	public void setProformaXml(String proformaXml) {this.proformaXml = proformaXml;}
	
	

	public Double getNetoSinAjustes() {
		return netoSinAjustes;
	}

	public void setNetoSinAjustes(Double netoSinAjustes) {
		this.netoSinAjustes = netoSinAjustes;
	}

	public Double getNetoSoloAjustes() {
		return netoSoloAjustes;
	}

	public void setNetoSoloAjustes(Double netoSoloAjustes) {
		this.netoSoloAjustes = netoSoloAjustes;
	}



	static SimpleDateFormat myformatfecha = new SimpleDateFormat("dd-MM-yyyy");
	static DecimalFormat myformatdouble0 = new DecimalFormat("#,##0");
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble4 = new DecimalFormat("#,##0.0000");
	static DecimalFormat myformatdouble6 = new DecimalFormat("#,##0.0000");
	
	

	public static List<List<String>> listadoProformas(Connection con, String db, String esPorSucursal, String id_sucursal) {
		List<List<String>> lista = new ArrayList<List<String>>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select "
							+ " id,"
							+ " fecha,"
							+ " desde,"
							+ " hasta,"
							+ " id_cliente,"
							+ " id_bodegaEmpresa,"
							+ " id_proyecto,"
							+ " docRef,"
							+ " epExcelMov,"
							+ " epExcelEp,"
							+ " proformaPdf,"
							+ " docAnexo,"
							+ " descuento,"
							+ " neto, "
							+ " iva,"
							+ " total,"
							+ " esEliminable,"
							+ " proformaXml,"
							+ " xmlEnviado,"
							+ " jsonGenerado "
							+ " from `"+db+"`.proformaOdo"
							+ " where id_bodegaEmpresa>0 order by fecha desc,id desc");
			ResultSet rs = smt.executeQuery();
			int numDec = Moneda.numeroDecimalxId(con, db, "1");
			Map<Long,Cliente> mapCliente = Cliente.mapAllClientes(con, db);
			Map<Long,BodegaEmpresa> mapBodega = BodegaEmpresa.mapAll(con, db);
			
			while (rs.next()) {
				String fecha = null;	if (rs.getString(2) != null) {fecha = myformatfecha.format(rs.getDate(2));}
				String desde = null;	if (rs.getString(3) != null) {desde = myformatfecha.format(rs.getDate(3));}
				String hasta = null;	if (rs.getString(4) != null) {hasta = myformatfecha.format(rs.getDate(4));}
				
				Cliente cliente = mapCliente.get(rs.getLong(5));
				String rutCliente = "";
				String nickCliente = "";
				if(cliente!=null) {
					rutCliente = cliente.getRut();
					nickCliente = cliente.getNickName();
				}
				BodegaEmpresa bodegaEmpresa = mapBodega.get(rs.getLong(6));
				String nomBodega = "";
				String auxId_sucursal = "";
				String nameSucursal = "";
				if(bodegaEmpresa!=null) {
					nomBodega = bodegaEmpresa.getNombre();
					auxId_sucursal = bodegaEmpresa.getId_sucursal().toString();
					nameSucursal = bodegaEmpresa.getNameSucursal();
				}
				
				String neto,iva,total = "0";
				
				if(numDec==0) {
	   			 neto = myformatdouble0.format(rs.getDouble(14));
	   			 iva = myformatdouble0.format(rs.getDouble(15));
	   			 total = myformatdouble0.format(rs.getDouble(16));
				}else {
	   			 neto = myformatdouble2.format(rs.getDouble(14));
	   			 iva = myformatdouble2.format(rs.getDouble(15));
	   			 total = myformatdouble2.format(rs.getDouble(16));
				}
				
				if(esPorSucursal.equals("1")) {
					if(auxId_sucursal.equals(id_sucursal)) {
						List<String> aux = new ArrayList<String>();
			   			 aux.add(rs.getString(1)); 		//0 numeroID
			   			 aux.add(rs.getString(8));		//1 documento de referencia
			   			 aux.add(fecha);				//2 fecha
			   			 aux.add(desde);				//3 desde
			   			 aux.add(hasta);				//4 hasta
			   			 aux.add(rutCliente);			//5 rut
			   			 aux.add(nickCliente);			//6 cliente
			   			 aux.add(nomBodega);			//7 bodega
			   			 aux.add(neto);					//8 neto
			   			 aux.add(iva);					//9 iva
			   			 aux.add(total);				//10 total
			   			 aux.add(rs.getString(11));		//11 proforma pdf
			   			 aux.add(rs.getString(10));		//12 excel ep
			   			 aux.add(rs.getString(9));		//13 exel mov
			   			 aux.add(rs.getString(12));		//14 doc anexo
			   			 aux.add(rs.getString(17));		//15 si es o no eliminable (1=se puede eliminar)
			   			 aux.add(rs.getString(18));		//16 xml factura
			   			 aux.add(rs.getString(19));		//17 xml enviado
			   			 aux.add(rs.getString(20));		//18 api manager
			   			 aux.add(nameSucursal);			//19 nameSucursal
			   			 lista.add(aux);
					}
				}else {
					List<String> aux = new ArrayList<String>();
		   			 aux.add(rs.getString(1)); 		//0 numeroID
		   			 aux.add(rs.getString(8));		//1 documento de referencia
		   			 aux.add(fecha);				//2 fecha
		   			 aux.add(desde);				//3 desde
		   			 aux.add(hasta);				//4 hasta
		   			 aux.add(rutCliente);			//5 rut
		   			 aux.add(nickCliente);			//6 cliente
		   			 aux.add(nomBodega);			//7 bodega
		   			 aux.add(neto);					//8 neto
		   			 aux.add(iva);					//9 iva
		   			 aux.add(total);				//10 total
		   			 aux.add(rs.getString(11));		//11 proforma pdf
		   			 aux.add(rs.getString(10));		//12 excel ep
		   			 aux.add(rs.getString(9));		//13 exel mov
		   			 aux.add(rs.getString(12));		//14 doc anexo
		   			 aux.add(rs.getString(17));		//15 si es o no eliminable (1=se puede eliminar)
		   			 aux.add(rs.getString(18));		//16 xml factura
		   			 aux.add(rs.getString(19));		//17 xml enviado
		   			 aux.add(rs.getString(20));		//18 api manager
		   			 aux.add(nameSucursal);			//19 nameSucursal
		   			 lista.add(aux);
				}
				
	   			 
			}
			rs.close();smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (lista);
	}
	public static ProformaOdo find(Connection con, String db, Long id) {
		ProformaOdo aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement("select  id,fecha,desde,hasta,id_cliente,id_bodegaEmpresa," + 
							" id_proyecto,docRef,epExcelMov,epExcelEp,proformaPdf,docAnexo,descuento,neto,iva,total,"+
							" esEliminable,proformaXml,xmlEnviado,jsonGenerado from `"+db+"`.proformaOdo WHERE id = ?" );
			smt.setLong(1, id);
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				String fecha = null;	if (rs.getString(2) != null) {fecha = myformatfecha.format(rs.getDate(2));}
				String desde = null;	if (rs.getString(3) != null) {desde = myformatfecha.format(rs.getDate(3));}
				String hasta = null;	if (rs.getString(4) != null) {hasta = myformatfecha.format(rs.getDate(4));}
				aux = new ProformaOdo(rs.getLong(1),fecha,desde,hasta,rs.getLong(5),rs.getLong(6),rs.getLong(7),rs.getString(8),rs.getString(9),
						rs.getString(10),rs.getString(11),rs.getString(12),rs.getDouble(13),rs.getDouble(14),rs.getDouble(15),rs.getDouble(16),
						rs.getLong(17), rs.getString(18), rs.getLong(19), rs.getString(20),(double)0,(double)0);
			}
			rs.close();smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (aux);
	}

	
	public static ProformaOdo createSinNada(Connection con, String db,String hoy) {
		Long idProforma = (long)0;
		Double index = Math.random();
		try {
			PreparedStatement smt1 = con
					.prepareStatement("INSERT INTO `"+db+"`.proformaOdo (fecha,random) values (?,?);");
			smt1.setString(1,hoy.trim());
			smt1.setDouble(2, index);
			smt1.executeUpdate();
			smt1.close();
			
			PreparedStatement smt2 = con.prepareStatement("Select max(id) from `"+db+"`.proformaOdo where random=?;");
			smt2.setDouble(1, index);
			ResultSet rs2 = smt2.executeQuery();
			if(rs2.next()) {
				idProforma=rs2.getLong(1);
			}
			smt2.close();rs2.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		
		return (ProformaOdo.find(con, db, idProforma));
	}
	
	public static boolean modify(Connection con,String db, ProformaOdo aux) {
		Boolean flag = true;
		try {
			PreparedStatement smt = con
					.prepareStatement("UPDATE `"+db+"`.proformaOdo SET fecha=?,desde=?,hasta=?,id_cliente=?,id_bodegaEmpresa=?, " +
							" id_proyecto=?,docRef=?,epExcelMov=?,epExcelEp=?,proformaPdf=?,docAnexo=?,descuento=?,neto=?,iva=?,total=?,proformaXml=? " +
							" WHERE id = ?");
			smt.setString(1, aux.fecha.trim());
			smt.setString(2, aux.desde.trim());
			smt.setString(3, aux.hasta.trim());
			smt.setLong(4, aux.id_cliente);
			smt.setLong(5, aux.id_bodegaEmpresa);
			smt.setLong(6, aux.id_proyecto);
			smt.setString(7, aux.docRef.trim());
			smt.setString(8, aux.epExcelMov.trim());
			smt.setString(9, aux.epExcelEp.trim());
			smt.setString(10, aux.proformaPdf.trim());
			smt.setString(11, aux.docAnexo.trim());
			smt.setDouble(12, aux.descuento);
			smt.setDouble(13, aux.neto);
			smt.setDouble(14, aux.iva);
			smt.setDouble(15, aux.total);
			smt.setString(16, aux.proformaXml.trim());
			smt.setLong(17, aux.id);
			smt.executeUpdate();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
			flag=false;
		}
		return (flag);
	}
	
	public static boolean eliminaProforma(Connection con, String db, Long id_proforma) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
				.prepareStatement("delete from `"+db+"`.proformaOdo where esEliminable = 1 and id = ?;");
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
