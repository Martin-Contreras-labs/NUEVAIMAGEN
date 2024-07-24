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





public class Factura {
	public Long id;
	public Long id_proveedor;
	public Long numero;
	public String fecha;
	public String facturaPDF;
	public String rut;
	public String nombreProveedor;
	public String nickNameProveedor;
	public String observaciones;
	public String numOcIConstruye;
	public Long id_userCrea;
	public Long id_userModifica;
	public String fechaUserModifica;
	
	public String nameSucursal;
	


	public Factura(Long id, Long id_proveedor, Long numero, String fecha, String facturaPDF, String rut,
			String nombreProveedor, String nickNameProveedor, String observaciones, String numOcIConstruye,
			Long id_userCrea, Long id_userModifica, String fechaUserModifica, String nameSucursal) {
		super();
		this.id = id;
		this.id_proveedor = id_proveedor;
		this.numero = numero;
		this.fecha = fecha;
		this.facturaPDF = facturaPDF;
		this.rut = rut;
		this.nombreProveedor = nombreProveedor;
		this.nickNameProveedor = nickNameProveedor;
		this.observaciones = observaciones;
		this.numOcIConstruye = numOcIConstruye;
		this.id_userCrea = id_userCrea;
		this.id_userModifica = id_userModifica;
		this.fechaUserModifica = fechaUserModifica;
		this.nameSucursal = nameSucursal;
	}

	public Factura(Long id_proveedor, Long numero, String fecha, String observaciones) {
		super();
		this.id_proveedor = id_proveedor;
		this.numero = numero;
		this.fecha = fecha;
		this.observaciones = observaciones;
	}
	
	public Factura() {
		super();
	}
	
	
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public Long getId_proveedor() {return id_proveedor;}
	public void setId_proveedor(Long id_proveedor) {this.id_proveedor = id_proveedor;}
	public Long getNumero() {return numero;}
	public void setNumero(Long numero) {this.numero = numero;}
	public String getFecha() {return fecha;}
	public void setFecha(String fecha) {this.fecha = fecha;}
	public String getRut() {return rut;}
	public void setRut(String rut) {this.rut = rut;}
	public String getNombreProveedor() {return nombreProveedor;}
	public void setNombreProveedor(String nombreProveedor) {this.nombreProveedor = nombreProveedor;}
	public String getNickNameProveedor() {return nickNameProveedor;}
	public void setNickNameProveedor(String nickNameProveedor) {this.nickNameProveedor = nickNameProveedor;}
	public String getFacturaPDF() {return facturaPDF;}
	public void setFacturaPDF(String facturaPDF) {this.facturaPDF = facturaPDF;}
	public String getObservaciones() { return observaciones; }
	public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

	public String getNumOcIConstruye() {
		return numOcIConstruye;
	}

	public void setNumOcIConstruye(String numOcIConstruye) {
		this.numOcIConstruye = numOcIConstruye;
	}

	public Long getId_userCrea() {
		return id_userCrea;
	}

	public void setId_userCrea(Long id_userCrea) {
		this.id_userCrea = id_userCrea;
	}

	public Long getId_userModifica() {
		return id_userModifica;
	}

	public void setId_userModifica(Long id_userModifica) {
		this.id_userModifica = id_userModifica;
	}

	public String getFechaUserModifica() {
		return fechaUserModifica;
	}

	public void setFechaUserModifica(String fechaUserModifica) {
		this.fechaUserModifica = fechaUserModifica;
	}

	public String getNameSucursal() {
		return nameSucursal;
	}

	public void setNameSucursal(String nameSucursal) {
		this.nameSucursal = nameSucursal;
	}


	static SimpleDateFormat myformatfecha = new SimpleDateFormat("dd-MM-yyyy");
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	
	
	public static Boolean existeNumero(Connection con,String db, Long numeroFactura, Long id_proveedor) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement(" select id from `"+db+"`.factura where numero = ? and id_proveedor = ?;" );
			smt.setLong(1, numeroFactura);
			smt.setLong(2, id_proveedor);
			ResultSet resultado = smt.executeQuery();
			if (resultado.next()) {	
				flag = true;	
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	
	public static Long findIdFactura(Connection con, String db, Long numeroFactura, Long id_proveedor) {
		Long aux = (long) 0;
		try {
			PreparedStatement smt = con
					.prepareStatement(" select id from `"+db+"`.factura where numero = ? and id_proveedor = ?;" );
			smt.setLong(1, numeroFactura);
			smt.setLong(2, id_proveedor);
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {	
				aux = rs.getLong(1);	
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (aux);
	}
	
	public static Long nuevoNumero(Connection con, String db, Long id_proveedor) {
		try {
			PreparedStatement smt = con
					.prepareStatement(" select max(numero) from `"+db+"`.factura where id_proveedor = ?;" );
			smt.setLong(1, id_proveedor);
			ResultSet rs = smt.executeQuery();
			Long aux = (long) 0;
			if (rs.next()) {	
				aux = rs.getLong(1) + (long) 1;	
			}
			rs.close();
			smt.close();
			return (aux);
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return ((long) 1);
	}
	
	public static Long create(Connection con, String db, Factura factura, String numOcIConstruye, String id_userCrea) {
		Long id_factura = (long) 0;
		try {
			PreparedStatement smt = con
					.prepareStatement("insert into `"+db+"`.factura (id_proveedor,numero,fecha, observaciones, numOcIConstruye, id_userCrea) " +
							" values (?,?,?,?,?,?)");
			smt.setLong(1, factura.getId_proveedor());
			smt.setLong(2, factura.getNumero());
			smt.setString(3, factura.getFecha());
			smt.setString(4, factura.getObservaciones());
			smt.setString(5, numOcIConstruye);
			smt.setString(6, id_userCrea);
			smt.executeUpdate();
			smt.close();
			PreparedStatement smt2 = con.prepareStatement("select id from `"+db+"`.factura where id_proveedor=? and numero=?;");
			smt2.setLong(1, factura.getId_proveedor());
			smt2.setLong(2, factura.getNumero());
			ResultSet rs2 =  smt2.executeQuery();
			if(rs2.next()) {
				id_factura = rs2.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (id_factura);
	}
	
	public static boolean delete(Connection con, String db, Long id_factura) {
		boolean flag = false;		
		try {
			PreparedStatement smt2 = con
					.prepareStatement("delete from `"+db+"`.compra WHERE id_factura = ?");
			smt2.setLong(1, id_factura);
			smt2.executeUpdate();
			smt2.close();
			PreparedStatement smt1 = con
					.prepareStatement("delete from `"+db+"`.factura WHERE id = ?");
			smt1.setLong(1, id_factura);
			smt1.executeUpdate();
			smt1.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean modifyXCampo(Connection con, String db, String campo, String valor, Long id_factura) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("update `"+db+"`.factura set `"+campo+"` = ? WHERE id = ?;");
			smt.setString(1, valor.trim());
			smt.setLong(2, id_factura);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static List<Factura> all(Connection con, String db, String esPorSucursal, String id_sucursal) {
		List<Factura> lista = new ArrayList<Factura>();
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and bodegaEmpresa.id_sucursal = " + id_sucursal;
		}
		try {
			PreparedStatement smt = con
					.prepareStatement(" select   " +
							" factura.id,   " +
							" factura.id_proveedor,   " +
							" factura.numero,   " +
							" factura.fecha, " +
							" factura.facturaPDF, " +
							" proveedor.rut, " +
							" proveedor.nombre, " +
							" proveedor.nickname, " +
							" ifnull(factura.observaciones,''),  " +
							" factura.numOcIConstruye, " +
							" factura.id_userCrea,   " +
							" factura.id_userModifica,   " +
							" ifnull(factura.fechaUserModifica,''), " +
							" bodegaEmpresa.id_sucursal " +
							" from `"+db+"`.factura " +
							" left join `"+db+"`.proveedor on proveedor.id = id_proveedor " +
							" left join `"+db+"`.compra on compra.id_factura = factura.id " +
							" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = compra.id_bodegaEmpresa " +
							" where proveedor.rut is not null " + condSucursal +
							" group by bodegaEmpresa.id_sucursal, factura.id " +
							" order by factura.fecha desc;");
			ResultSet rs = smt.executeQuery();
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			while (rs.next()) {	
				String nameSucursal = "";
				Sucursal sucursal = mapSucursal.get(rs.getLong(14));
				if(sucursal!=null) {
					nameSucursal = sucursal.nombre;
				}
				String fecha = null;		
				if (rs.getString(4) != null) {fecha = myformatfecha.format(rs.getDate(4));}
				lista.add(new Factura(rs.getLong(1),rs.getLong(2),rs.getLong(3),fecha,
						rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),
						rs.getLong(11),rs.getLong(12),rs.getString(13),nameSucursal));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static Factura find(Connection con, String db, Long id_factura) {
		Factura aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement(" select  " +
							" factura.id, " +
							" factura.id_proveedor, " +
							" factura.numero, " +
							" factura.fecha, " +
							" factura.facturaPDF, " +
							" proveedor.rut, " +
							" proveedor.nombre, " +
							" proveedor.nickname, " +
							" ifnull(factura.observaciones,''),  " +
							" factura.numOcIConstruye, " +
							" factura.id_userCrea, " +
							" factura.id_userModifica, " +
							" ifnull(factura.fechaUserModifica,'') " +
							" from `"+db+"`.factura " +
							" left join `"+db+"`.proveedor on proveedor.id = id_proveedor " +
							" left join `"+db+"`.compra on compra.id_factura = factura.id " +
							" where factura.id = ?;" );
			smt.setLong(1, id_factura);
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				String fecha = null;		
				if (rs.getString(4) != null) {fecha = myformatfecha.format(rs.getDate(4));}
				aux = new Factura(rs.getLong(1),rs.getLong(2),rs.getLong(3),fecha,
						rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),
						rs.getLong(11),rs.getLong(12),rs.getString(13),"noAplicaSucursal");
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}
	
	public static List<List<String>> detalleFactura(Connection con, String db, Long id_proveedor, Long id_factura, String esPorSucursal, String id_sucursal) {
		List<List<String>> detalle = new ArrayList<List<String>>();
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and bodegaEmpresa.id_sucursal = " + id_sucursal;
		}
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" equipo.codigo, " +
							" equipo.nombre as equipo, " +
							" unidad.nombre as unidad, " +
							" compra.cantidad as cantidad, " +
							" moneda.nickName as moneda, " +
							" compra.precioUnidad as punitario, " +
							" compra.precioUnidad*compra.cantidad as total, " +
							" ifnull(bodegaEmpresa.nombre,' ') as destino, " +
							" moneda.id, " +
							" ifnull(bodegaEmpresa.id, 0), " +
							" esModificable, " +
							" compra.id, " +
							" equipo.id, " +
							" bodegaEmpresa.id_sucursal " +
							" from `"+db+"`.factura " +
							" left join `"+db+"`.proveedor on proveedor.id = factura.id_proveedor " +
							" left join `"+db+"`.compra on compra.id_factura = factura.id " +
							" left join `"+db+"`.equipo on equipo.id = compra.id_equipo " +
							" left join `"+db+"`.unidad on unidad.id = equipo.id_unidad " +
							" left join `"+db+"`.moneda on moneda.id = compra.id_moneda " +
							" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = compra.id_bodegaEmpresa " +
							" where proveedor.id = ? and factura.id = ? "+ condSucursal +
							" and codigo is not null;");
			smt.setLong(1, id_proveedor);
			smt.setLong(2, id_factura);
			ResultSet rs = smt.executeQuery();
			int i=0;
			Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
			Map<Long,Double> pesos = Atributo.mapAtributoPESO(con, db);
			Map<Long,Double> superficies = Atributo.mapAtributoM2(con, db);
			
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			
			while (rs.next()) {
				List<String> aux = new ArrayList<String>();
				i++;
				switch(dec.get(rs.getLong(9)).toString()) {
				 case "0": myformatdouble = new DecimalFormat("#,##0"); break;
				 case "2": myformatdouble = new DecimalFormat("#,##0.00"); break;
				 case "4": myformatdouble = new DecimalFormat("#,##0.0000"); break;
				 case "6": myformatdouble = new DecimalFormat("#,##0.000000"); break;
				 default:  break;
				}
				aux.add(i + ""); 									// 0 contador de linea
				aux.add(rs.getString(1));  							// 1 codigo
				aux.add(rs.getString(2));  							// 2 equipo
				aux.add(rs.getString(3));  							// 3 unidad
				aux.add(myformatdouble2.format(rs.getDouble(4)));  	// 4 cantidad
				aux.add(rs.getString(9));  							// 5 moneda.id
				aux.add(rs.getString(5));  							// 6 moneda.nickName
				aux.add(myformatdouble.format(rs.getDouble(6)));  	// 7 compra.precioUnidad
				aux.add(myformatdouble.format(rs.getDouble(7)));  	// 8 compra.precioUnidad*compra.cantidad as total
				aux.add(rs.getString(12));  						// 9 compra.id
				aux.add(rs.getString(11));  						// 10 esModificable
				aux.add(rs.getString(10));  						// 11 bodegaEmpresa.id
				aux.add(rs.getString(8));  							// 12 bodegaEmpresa.nombre as destino
				aux.add(rs.getString(13));  						// 13 equipo.id
				
				Double pesoUnit = pesos.get(rs.getLong(13));
				if(pesoUnit==null) pesoUnit=(double)0;
				Double supUnit = superficies.get(rs.getLong(13));
				if(supUnit==null) supUnit=(double)0;
				
				aux.add(myformatdouble2.format(pesoUnit));  					// 14 kg pesoUnit
				aux.add(myformatdouble2.format(supUnit));  						// 15 m2 supUnit
				
				aux.add(myformatdouble2.format(pesoUnit*rs.getDouble(4)));  	// 16 kg total
				aux.add(myformatdouble2.format(supUnit*rs.getDouble(4)));  		// 17 m2 total
				
				Sucursal sucursal = mapSucursal.get(rs.getLong(14));
				if(sucursal != null) {
					aux.add(sucursal.getNombre());  						// 18 nombre sucursal
					aux.add(sucursal.getId().toString());  					// 19 id sucursal
				}else {
					aux.add("");
					aux.add("0");
				}
				
				
				
				detalle.add(aux);
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (detalle);
	}
	
	public static List<Factura> allEliminables(Connection con, String db, String esPorSucursal, String id_sucursal) {
		List<Factura> lista = new ArrayList<Factura>();
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " where bodegaEmpresa.id_sucursal = " + id_sucursal;
		}
		try {
			PreparedStatement smt = con
					.prepareStatement("select compra.id_factura, factura.id_proveedor, factura.numero, factura.fecha, "
							+ " factura.facturaPDF, proveedor.rut, proveedor.nombre, proveedor.nickname, ifnull(factura.observaciones,''), "
							+ " factura.numOcIConstruye, "
							+ " avg(compra.esModificable), "
							+ " factura.id_userCrea, " 
							+ " factura.id_userModifica, " 
							+ " ifnull(factura.fechaUserModifica,''), "
							+ " bodegaEmpresa.id_sucursal "
							+ " from `"+db+"`.compra "
							+ " left join `"+db+"`.factura on factura.id = compra.id_factura "
							+ " left join `"+db+"`.proveedor on proveedor.id = factura.id_proveedor "
							+ " left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = compra.id_bodegaEmpresa "
							+ condSucursal
							+ " group by compra.id_factura "
							+ " having avg(compra.esModificable)=1 order by factura.fecha desc;");
			ResultSet rs = smt.executeQuery();
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			while (rs.next()) {	
				String nameSucursal = "";
				Sucursal sucursal = mapSucursal.get(rs.getLong(14));
				if(sucursal!=null) {
					nameSucursal = sucursal.nombre;
				}
				String fecha = null;		
				if (rs.getString(4) != null) {
					fecha = myformatfecha.format(rs.getDate(4));
				}
				lista.add(new Factura(rs.getLong(1),rs.getLong(2),rs.getLong(3),fecha,
						rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),
						rs.getLong(11),rs.getLong(12),rs.getString(13),nameSucursal));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	
	public static File compraFacturaPrintExcel(String db, Map<String,String> mapDiccionario, Factura factura, List<List<String>> detalleFactura) {
		
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
            
            
            
            //titulos del archivo
            
            libro.setSheetName(0, "COMPRA");
            Sheet hoja1 = libro.getSheetAt(0);
            
            Row row = null;
            Cell cell = null;
            
            row = hoja1.createRow(1);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("DETALLE DE COMPRA");
			
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
			
			
			
			row = hoja1.createRow(5);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(mapDiccionario.get("RUT")+" "+"Proveedor");
			
            cell = row.createCell(2);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(factura.getRut());
			
			row = hoja1.createRow(6);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Nombre Proveedor");
			
            cell = row.createCell(2);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(factura.getNickNameProveedor());
			
			row = hoja1.createRow(7);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Nro.Documento");
			
            cell = row.createCell(2);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(factura.getNumero().toString());
			
			row = hoja1.createRow(8);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Fecha");
			
            cell = row.createCell(2);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(Fechas.DDMMAA(factura.getFecha()));
			
			row = hoja1.createRow(9);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Observaciones");
			
            cell = row.createCell(2);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(Fechas.DDMMAA(factura.getObservaciones()));
			
			
			
			//INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
			InputStream x = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
            byte[] bytes = IOUtils.toByteArray(x);
            x.close();
            int pngIndex = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			Drawing draw = hoja1.createDrawingPatriarch();
			CreationHelper helper = libro.getCreationHelper();
			ClientAnchor anchor = helper.createClientAnchor();
	        //set top-left corner for the image
	        anchor.setCol1(11);
	        anchor.setRow1(1);
			Picture img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);
			
			
			// encabezado de la tabla
			
			int posRow = 11;
			
			row = hoja1.createRow(posRow);
			int posCell = 0;
			
			
			
			posCell++;
			hoja1.setColumnWidth(posCell, 6*1000);
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("CODIGO");
		
			posCell++;
			hoja1.setColumnWidth(posCell, 12*1000);
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("EQUIPO");
			
			posCell++;
			hoja1.setColumnWidth(posCell, 3*1000);
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("KG");
			
			posCell++;
			hoja1.setColumnWidth(posCell, 3*1000);
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("M2");
		        
			posCell++;
			hoja1.setColumnWidth(posCell, 3*1000);
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("UN");
			
			posCell++;
			hoja1.setColumnWidth(posCell, 3*1000);
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("CANT");

			posCell++;
			hoja1.setColumnWidth(posCell, 3*1000);
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("MON");
		        
			posCell++;
			hoja1.setColumnWidth(posCell, 3*1000);
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("P.U.COMPRA");
			
			posCell++;
			hoja1.setColumnWidth(posCell, 3*1000);
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TOTAL");
			
			posCell++;
			hoja1.setColumnWidth(posCell, 6*1000);
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("DESTINO");
			
			posCell++;
			hoja1.setColumnWidth(posCell, 3*1000);
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TOTAL KG");
			
			posCell++;
			hoja1.setColumnWidth(posCell, 3*1000);
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TOTAL M2");
				
		       
	        Double totCant = (double)0;
	        Double totTot = (double)0;
	        Double totKg = (double)0;
	        Double totM2 = (double)0;
	        
	        
	        
			for(int i=0;i<detalleFactura.size();i++){
				
				posRow++;
				posCell = 0;
		        row = hoja1.createRow(posRow);
				
		        posCell++;
				cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(detalleFactura.get(i).get(1));
				
				posCell++;
				cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(detalleFactura.get(i).get(2));
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            Double aux = Double.parseDouble(detalleFactura.get(i).get(14).replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(detalleFactura.get(i).get(15).replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);
				
				posCell++;
				cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(detalleFactura.get(i).get(3));
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(detalleFactura.get(i).get(4).replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);
				totCant += aux;
				
				posCell++;
				cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(detalleFactura.get(i).get(6));
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(detalleFactura.get(i).get(7).replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(detalleFactura.get(i).get(8).replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);
				totTot += aux;
				
				posCell++;
				cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(detalleFactura.get(i).get(12));
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(detalleFactura.get(i).get(16).replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);
				totKg += aux;
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(detalleFactura.get(i).get(17).replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);
				totM2 += aux;
			}
			
			posRow++;
			posCell = 0;
	        row = hoja1.createRow(posRow);
			
	        
	        
	        posCell++;
			cell = row.createCell(posCell);
			cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TOTAL");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			cell.setCellValue(totCant);
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			cell.setCellValue(totTot);
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			cell.setCellValue(totKg);
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			cell.setCellValue(totM2);
			
			
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
	
	public static File compralistComprasPorEquipoExcel(String db, Map<String,String> mapDiccionario, List<List<String>> listaPorEquipo) {
		
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
			cell.setCellValue("LISTADO DE COMPRAS POR EQUIPOS");
			
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
				hoja1.setColumnWidth(i, 6*1000);
			}
			//INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
			InputStream x = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
            byte[] bytes = IOUtils.toByteArray(x);
            x.close();
            int pngIndex = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			Drawing draw = hoja1.createDrawingPatriarch();
			CreationHelper helper = libro.getCreationHelper();
			ClientAnchor anchor = helper.createClientAnchor();
	        //set top-left corner for the image
	        anchor.setCol1(8);
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
			cell.setCellValue("GRUPO");
		
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("CODIGO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			hoja1.setColumnWidth(3, 10*1000);
			cell.setCellValue("EQUIPO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("UNIDAD");
		        
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("CANTIDAD");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("MONEDA");

			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("PRECIO (ULTIMO)");
		        
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FECHA");
				
	        
			for(int i=0;i<listaPorEquipo.size();i++){
							
				posRow++;
				posCell = 0;
		        row = hoja1.createRow(posRow);
						
		        posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(listaPorEquipo.get(i).get(1));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(listaPorEquipo.get(i).get(2));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(listaPorEquipo.get(i).get(3));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(listaPorEquipo.get(i).get(4));
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            Double aux = Double.parseDouble(listaPorEquipo.get(i).get(5).replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(listaPorEquipo.get(i).get(6));
				
				posCell++; 
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(listaPorEquipo.get(i).get(7).replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(formatFecha);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				Fechas fecha = Fechas.obtenerFechaDesdeStrDDMMAA(listaPorEquipo.get(i).get(8));
				cell.setCellValue(fecha.getFechaUtil());
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
	
	public static File compralistComprasPorCompraExcel(String db, Map<String,String> mapDiccionario, List<Factura> listFacturas) {
		
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
			cell.setCellValue("LISTADO DE COMPRAS POR COMPRA");
			
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
				hoja1.setColumnWidth(i, 6*1000);
			}
			//INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
			InputStream x = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
            byte[] bytes = IOUtils.toByteArray(x);
            x.close();
            int pngIndex = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			Drawing draw = hoja1.createDrawingPatriarch();
			CreationHelper helper = libro.getCreationHelper();
			ClientAnchor anchor = helper.createClientAnchor();
	        //set top-left corner for the image
	        anchor.setCol1(4);
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
			cell.setCellValue(mapDiccionario.get("RUT"));
		
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			hoja1.setColumnWidth(2, 10*1000);
			cell.setCellValue("PROVEEDOR");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FECHA");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("NUMERO");
		        
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			hoja1.setColumnWidth(5, 14*1000);
			cell.setCellValue("OBSERVACIONES");
			
				
	        
			for(int i=0;i<listFacturas.size();i++){
							
				posRow++;
				posCell = 0;
		        row = hoja1.createRow(posRow);
						
		        posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(listFacturas.get(i).getRut());
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(listFacturas.get(i).getNickNameProveedor());
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(formatFecha);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				Fechas fecha = Fechas.obtenerFechaDesdeStrDDMMAA(listFacturas.get(i).getFecha());
				cell.setCellValue(fecha.getFechaUtil());
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(listFacturas.get(i).getNumero());
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(listFacturas.get(i).getObservaciones());
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
