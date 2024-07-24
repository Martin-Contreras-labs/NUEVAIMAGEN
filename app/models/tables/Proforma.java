package models.tables;

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

import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.TempFile;

import models.utilities.Archivos;
import models.utilities.Fechas;


public class Proforma {
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
	public String tipo; // arriendo o venta
	public String proformaXml;
	public Long xmlEnviado;
	public String jsonGenerado;
	
	public Double netoSinAjustes;
	public Double netoSoloAjustes;
	
	public String response;
	
	public String nroFiscal;
	

	public Proforma(Long id, String fecha, String desde, String hasta, Long id_cliente, Long id_bodegaEmpresa,
			Long id_proyecto, String docRef, String epExcelMov, String epExcelEp, String proformaPdf, String docAnexo,
			Double descuento, Double neto, Double iva, Double total, Long esEliminable, String tipo, String proformaXml,
			Long xmlEnviado, String jsonGenerado, Double netoSinAjustes, Double netoSoloAjustes, String response, String nroFiscal) {
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
		this.tipo = tipo;
		this.proformaXml = proformaXml;
		this.xmlEnviado = xmlEnviado;
		this.jsonGenerado = jsonGenerado;
		this.netoSinAjustes = netoSinAjustes;
		this.netoSoloAjustes = netoSoloAjustes;
		this.response = response;
		this.nroFiscal = nroFiscal;
	}

	public Proforma() {
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
	public String getTipo() {return tipo;}
	public void setTipo(String tipo) {this.tipo = tipo;}
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

	public Long getXmlEnviado() {
		return xmlEnviado;
	}

	public void setXmlEnviado(Long xmlEnviado) {
		this.xmlEnviado = xmlEnviado;
	}

	public String getJsonGenerado() {
		return jsonGenerado;
	}

	public void setJsonGenerado(String jsonGenerado) {
		this.jsonGenerado = jsonGenerado;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getNroFiscal() {
		return nroFiscal;
	}

	public void setNroFiscal(String nroFiscal) {
		this.nroFiscal = nroFiscal;
	}



	static SimpleDateFormat myformatfecha = new SimpleDateFormat("dd-MM-yyyy");
	static DecimalFormat myformatdouble0 = new DecimalFormat("#,##0");
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble4 = new DecimalFormat("#,##0.0000");
	static DecimalFormat myformatdouble6 = new DecimalFormat("#,##0.0000");
	
	
	public static List<Proforma> all(Connection con,String db) {
		List<Proforma> lista = new ArrayList<Proforma>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select  id, fecha, desde, hasta, id_cliente, id_bodegaEmpresa,"
							+ " id_proyecto, docRef, epExcelMov, epExcelEp, proformaPdf, docAnexo, descuento, neto, iva, total, esEliminable, tipo, proformaXml, xmlEnviado, jsonGenerado, ifnull(response,0), ifnull(nroFiscal,0) "
							+ " from `"+db+"`.proforma"
							+ " order by fecha desc,id desc");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				String fecha = null;	if (rs.getString(2) != null) {fecha = myformatfecha.format(rs.getDate(2));}
				String desde = null;	if (rs.getString(3) != null) {desde = myformatfecha.format(rs.getDate(3));}
				String hasta = null;	if (rs.getString(4) != null) {hasta = myformatfecha.format(rs.getDate(4));}
				lista.add(new Proforma(rs.getLong(1),fecha,desde,hasta,rs.getLong(5),rs.getLong(6),rs.getLong(7),rs.getString(8),rs.getString(9),
						rs.getString(10),rs.getString(11),rs.getString(12),rs.getDouble(13),rs.getDouble(14),rs.getDouble(15),rs.getDouble(16),
						rs.getLong(17),rs.getString(18),rs.getString(19),rs.getLong(20),rs.getString(21),(double)0,(double)0,rs.getString(22),rs.getString(23)));
			}
			rs.close();smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (lista);
	}
	
	public static Long anioPrimeraProforma(Connection con, String db) {
		Fechas hoy = Fechas.hoy();
		String[] aux = hoy.getFechaStrAAMMDD().split("-");
		Long year = Long.parseLong(aux[0]);
		try {
			PreparedStatement smt = con
					.prepareStatement(" select year(min(fecha)) from `"+db+"`.proforma;" );
			ResultSet resultado = smt.executeQuery();
			if (resultado.next()) {	
				year = resultado.getLong(1);	
			}
			if(year < (long)2000) {
				year = Long.parseLong(aux[0]);
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (year);
	}
	
	public static List<List<String>> listadoPorAnio(Connection con, String db, String permisoPorBodega, Long year, String esPorSucursal, String id_sucursal) {
		List<List<String>> lista = new ArrayList<List<String>>();
		
		try {
			permisoPorBodega = permisoPorBodega.replaceAll("movimiento", "proforma");
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
							+ " tipo,"
							+ " proformaXml,"
							+ " xmlEnviado,"
							+ " jsonGenerado, "
							+ " ifnull(response,0), "
							+ " ifnull(nroFiscal,0)"
							+ " from `"+db+"`.proforma"
							+ " where id_bodegaEmpresa>0 "+permisoPorBodega
							+ " and year(fecha) = ? "
							+ " order by fecha desc,id desc");
			smt.setLong(1, year);
			ResultSet rs = smt.executeQuery();
			int numDec = Moneda.numeroDecimalxId(con, db, "1");
			Map<Long,Cliente> mapCliente = Cliente.mapAllClientes(con, db);
			Map<Long,BodegaEmpresa> mapBodega = BodegaEmpresa.mapAll(con, db);
			Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
		
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
				String nameSucursal = "";
				String auxIdSucursal = "0";
				String nameComercial = "";
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
	   			 aux.add(rs.getString(18));		//16 arriendo o venta
	   			 aux.add(rs.getString(19));		//17 xml factura
	   			 aux.add(rs.getString(20));		//18 xml enviado
	   			 aux.add(rs.getString(21));		//19 api manager
	   			 aux.add(rs.getString(22));		//20 response si es 0 no enviado y distinto enviado
	   			 aux.add(rs.getString(23));		//21 nro fiscal
	   			 aux.add(nameSucursal);			//22 nameSucursal
	   			 aux.add(nameComercial);		//23 nameComercial
	   			 
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
	
	public static Proforma find(Connection con, String db, Long id) {
		Proforma aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement("select  id,fecha,desde,hasta,id_cliente,id_bodegaEmpresa," + 
							" id_proyecto,docRef,epExcelMov,epExcelEp,proformaPdf,docAnexo,descuento,neto,iva,total,"+
							" esEliminable,tipo,proformaXml,xmlEnviado,jsonGenerado, ifnull(response,0), ifnull(nroFiscal,0) from `"+db+"`.proforma WHERE id = ?" );
			smt.setLong(1, id);
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				String fecha = null;	if (rs.getString(2) != null) {fecha = myformatfecha.format(rs.getDate(2));}
				String desde = null;	if (rs.getString(3) != null) {desde = myformatfecha.format(rs.getDate(3));}
				String hasta = null;	if (rs.getString(4) != null) {hasta = myformatfecha.format(rs.getDate(4));}
				aux = new Proforma(rs.getLong(1),fecha,desde,hasta,rs.getLong(5),rs.getLong(6),rs.getLong(7),rs.getString(8),rs.getString(9),
						rs.getString(10),rs.getString(11),rs.getString(12),rs.getDouble(13),rs.getDouble(14),rs.getDouble(15),rs.getDouble(16),
						rs.getLong(17), rs.getString(18), rs.getString(19),rs.getLong(20), rs.getString(21),(double)0,(double)0,rs.getString(22),rs.getString(23));
			}
			rs.close();smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (aux);
	}

	
	public static Proforma createSinNada(Connection con, String db, String hoy) {
		Long idProforma = (long)0;
		Double index = Math.random();
		try {
			PreparedStatement smt1 = con
					.prepareStatement("insert into `"+db+"`.proforma (fecha,random) values (?,?);");
			smt1.setString(1,hoy.trim());
			smt1.setDouble(2, index);
			smt1.executeUpdate();
			smt1.close();
			
			PreparedStatement smt2 = con.prepareStatement("Select max(id) from `"+db+"`.proforma where random=?;");
			smt2.setDouble(1, index);
			ResultSet rs2 = smt2.executeQuery();
			if(rs2.next()) {
				idProforma=rs2.getLong(1);
			}
			smt2.close();rs2.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		
		return (Proforma.find(con, db, idProforma));
	}
	
	public static boolean modify(Connection con,String db, Proforma aux) {
		Boolean flag = true;
		try {
			PreparedStatement smt = con
					.prepareStatement("update `"+db+"`.proforma set fecha=?,desde=?,hasta=?,id_cliente=?,id_bodegaEmpresa=?, " +
							" id_proyecto=?,docRef=?,epExcelMov=?,epExcelEp=?,proformaPdf=?,docAnexo=?,descuento=?,neto=?,iva=?,total=?,tipo=?,proformaXml=? " +
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
			smt.setString(16, aux.tipo.trim());
			smt.setString(17, aux.proformaXml.trim());
			smt.setLong(18, aux.id);
			smt.executeUpdate();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
			flag=false;
		}
		return (flag);
	}
	
	public static boolean updateXmlEnviado(Connection con, String db, Long idProforma) {
		Boolean flag = true;
		try {
			PreparedStatement smt = con
					.prepareStatement("update `"+db+"`.proforma set xmlEnviado=1 WHERE id = ?");
			smt.setLong(1, idProforma);
			smt.executeUpdate();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
			flag=false;
		}
		return (flag);
	}
	
	public static boolean updateJsonApi(Connection con, String db, Long id_proforma, String jsonApi) {
		Boolean flag = true;
		try {
			PreparedStatement smt = con
					.prepareStatement("update `"+db+"`.proforma set jsonGenerado=? WHERE id = ?");
			smt.setString(1, jsonApi);
			smt.setLong(2, id_proforma);
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
				.prepareStatement("delete from `"+db+"`.proforma where esEliminable = 1 and id = ?;");
			smt.setLong(1, id_proforma);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
    			e.printStackTrace();flag=false;
		}
		return (flag);
	}
	
	public static boolean updateResponse(Connection con, String db, Long id_proforma, String response) {
		Boolean flag = true;
		try {
			PreparedStatement smt = con
					.prepareStatement("update `"+db+"`.proforma set response=? WHERE id = ?");
			smt.setString(1, response);
			smt.setLong(2, id_proforma);
			smt.executeUpdate();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
			flag=false;
		}
		return (flag);
	}
	
	public static boolean updateNroFiscal(Connection con, String db, Long id_proforma, String nroFiscal) {
		Boolean flag = true;
		try {
			PreparedStatement smt = con
					.prepareStatement("update `"+db+"`.proforma set nroFiscal=? WHERE id = ?");
			smt.setString(1, nroFiscal);
			smt.setLong(2, id_proforma);
			smt.executeUpdate();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
			flag=false;
		}
		return (flag);
	}
	
	public static File listadoPorAnioExcel(String db, Map<String,String> mapDiccionario, List<List<String>> lista) {
		
		File tmp = TempFile.createTempFile("tmp","null");
		
		try {
			String path = "formatos/excel.xlsx";
			InputStream formato = Archivos.leerArchivo(path);
            Workbook libro = WorkbookFactory.create(formato);
            formato.close();
            
            // 0 negro 1 blanco 2 rojo 3 verde 4 azul 5 amarillo 19 celeste
            CellStyle titulo = libro.createCellStyle();
            Font font = libro.createFont();
            font.setBoldweight(Font.BOLDWEIGHT_BOLD);
            font.setColor((short)4);
            font.setFontHeight((short)(14*20));
            titulo.setFont(font);
            
            CellStyle subtitulo = libro.createCellStyle();
            Font font2 = libro.createFont();
            font2.setBoldweight(Font.BOLDWEIGHT_BOLD);
            font2.setColor((short)0);
            font2.setFontHeight((short)(12*20));
            subtitulo.setFont(font2);
            
            CellStyle encabezado = libro.createCellStyle();
            encabezado.setBorderBottom(CellStyle.BORDER_THIN);
            encabezado.setBorderTop(CellStyle.BORDER_THIN);
            encabezado.setBorderRight(CellStyle.BORDER_THIN);
            encabezado.setBorderLeft(CellStyle.BORDER_THIN);
            encabezado.setFillPattern(CellStyle.SOLID_FOREGROUND);
            encabezado.setFillForegroundColor((short)19);
            encabezado.setAlignment(CellStyle.ALIGN_LEFT);
            
            CellStyle detalle = libro.createCellStyle();
            detalle.setBorderBottom(CellStyle.BORDER_THIN);
            detalle.setBorderTop(CellStyle.BORDER_THIN);
            detalle.setBorderRight(CellStyle.BORDER_THIN);
            detalle.setBorderLeft(CellStyle.BORDER_THIN);
            
            DataFormat format = libro.createDataFormat();
            CellStyle formatFecha = libro.createCellStyle();
            formatFecha.setBorderBottom(CellStyle.BORDER_THIN);
            formatFecha.setBorderTop(CellStyle.BORDER_THIN);
            formatFecha.setBorderRight(CellStyle.BORDER_THIN);
            formatFecha.setBorderLeft(CellStyle.BORDER_THIN);
            formatFecha.setDataFormat(format.getFormat("dd-mm-yyyy"));
            
            
            
            //titulos del archivo
            
            libro.setSheetName(0, "COMPRAS PR EQUIPO");
            Sheet hoja1 = libro.getSheetAt(0);
            
            Row row = null;
            Cell cell = null;
            
            row = hoja1.createRow(1);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("LISTADO DE PROFORMAS");
			
			row = hoja1.createRow(2);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("EMPRESA: "+mapDiccionario.get("nEmpresa"));
			
			row = hoja1.createRow(3);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FECHA: "+Fechas.hoy().getFechaStrDDMMAA());
			
			
			
			//anchos de columnas
			for(int i=1; i<17; i++) {
				hoja1.setColumnWidth(i, 4*1000);
			}
			
			hoja1.setColumnWidth(7, 8*1000);
			hoja1.setColumnWidth(8, 8*1000);
			hoja1.setColumnWidth(9, 8*1000);
			hoja1.setColumnWidth(10, 10*1000);
			
			//INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
			InputStream x = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
            byte[] bytes = IOUtils.toByteArray(x);
            x.close();
            int pngIndex = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			Drawing draw = hoja1.createDrawingPatriarch();
			CreationHelper helper = libro.getCreationHelper();
			ClientAnchor anchor = helper.createClientAnchor();
	        //set top-left corner for the image
	        anchor.setCol1(10);
	        anchor.setRow1(1);
			Picture img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);
			
			
			// encabezado de la tabla
			
			int posRow = 8;
			
			row = hoja1.createRow(posRow);
			int posCell = 0;
			
			posCell++;
            cell = row.createCell(posCell);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("LISTADO:");
			
			posRow += 2;
			posCell = 0;
			
			row = hoja1.createRow(posRow);
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TIPO");
		
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("NUMERO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FECHA");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("DESDE");
		        
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("HASTA");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("RUT");

			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("CLIENTE");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("SUCURSAL");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("COMERCIAL");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(mapDiccionario.get("BODEGA")+"/PROYECTO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("NETO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("IVA");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TOTAL");
				
	        
			for(int i=0;i<lista.size();i++){
							
				posRow++;
				posCell = 0;
		        row = hoja1.createRow(posRow);
						
		        posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(16));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(0));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(formatFecha);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				Fechas fecha = Fechas.obtenerFechaDesdeStrDDMMAA(lista.get(i).get(2));
				cell.setCellValue(fecha.getFechaUtil());
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(formatFecha);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				fecha = Fechas.obtenerFechaDesdeStrDDMMAA(lista.get(i).get(3));
				cell.setCellValue(fecha.getFechaUtil());
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(formatFecha);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				fecha = Fechas.obtenerFechaDesdeStrDDMMAA(lista.get(i).get(4));
				cell.setCellValue(fecha.getFechaUtil());
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(5));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(6));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(22));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(23));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(7));
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            Double aux = Double.parseDouble(lista.get(i).get(8).replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(lista.get(i).get(9).replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(lista.get(i).get(10).replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);
				
			}
			
			posRow = posRow + 5;
			row = hoja1.createRow(posRow);
			cell = row.createCell(1);
			Hyperlink hiper = helper.createHyperlink(0);
			hiper.setAddress("https://www.inqsol.cl");
			cell.setHyperlink(hiper);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Documento generado desde MADA propiedad de INQSOL");

			// Write the output to a file tmp
			FileOutputStream fileOut = new FileOutputStream(tmp);
			libro.write(fileOut);
			fileOut.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
        }
		
		return tmp;
		
	}
	
	
}
