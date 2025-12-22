package models.tables;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.util.TempFile;

import models.forms.FormServicioGraba;
import models.utilities.Archivos;
import models.utilities.Fechas;




public class Servicio {
	public Long id;
	public String codigo;
	public String nombre;
	public Long id_unidadServicio;
	public Long id_claseServicio;
	public String descripcion;
	public Long id_moneda;
	public String fecha;
	public Double precio;
	
	public String nombreClase;
	public String nombreUnidad;
	public String nickMoneda; 
	public Long nroDecimal;
	
	public Double cantMinimo;				// es la cantidad de hr minimas u otra unidad
	public Double precioAdicional;			// es el valor a aplicar sobre el excedente
	

	public Servicio(Long id, String codigo, String nombre, Long id_unidadServicio, Long id_claseServicio,
			String descripcion, Long id_moneda, String fecha, Double precio, String nombreClase,
			String nombreUnidad, String nickMoneda, Long nroDecimal, Double cantMinimo, Double precioAdicional) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
		this.id_unidadServicio = id_unidadServicio;
		this.id_claseServicio = id_claseServicio;
		this.descripcion = descripcion;
		this.id_moneda = id_moneda;
		this.fecha = fecha;
		this.precio = precio;
		this.nombreClase = nombreClase;
		this.nombreUnidad = nombreUnidad;
		this.nickMoneda = nickMoneda;
		this.nroDecimal = nroDecimal;
		this.cantMinimo = cantMinimo;
		this.precioAdicional = precioAdicional;
	}



	public Servicio() {
		super();
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getId_unidadServicio() {
		return id_unidadServicio;
	}

	public void setId_unidadServicio(Long id_unidadServicio) {
		this.id_unidadServicio = id_unidadServicio;
	}

	public Long getId_claseServicio() {
		return id_claseServicio;
	}

	public void setId_claseServicio(Long id_claseServicio) {
		this.id_claseServicio = id_claseServicio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getId_moneda() {
		return id_moneda;
	}

	public void setId_moneda(Long id_moneda) {
		this.id_moneda = id_moneda;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getNombreClase() {
		return nombreClase;
	}

	public void setNombreClase(String nombreClase) {
		this.nombreClase = nombreClase;
	}

	public String getNombreUnidad() {
		return nombreUnidad;
	}

	public void setNombreUnidad(String nombreUnidad) {
		this.nombreUnidad = nombreUnidad;
	}

	public String getNickMoneda() {
		return nickMoneda;
	}

	public void setNickMoneda(String nickMoneda) {
		this.nickMoneda = nickMoneda;
	}

	public Long getNroDecimal() {
		return nroDecimal;
	}

	public void setNroDecimal(Long nroDecimal) {
		this.nroDecimal = nroDecimal;
	}

	public Double getCantMinimo() {
		return cantMinimo;
	}

	public void setCantMinimo(Double cantMinimo) {
		this.cantMinimo = cantMinimo;
	}

	public Double getPrecioAdicional() {
		return precioAdicional;
	}

	public void setPrecioAdicional(Double precioAdicional) {
		this.precioAdicional = precioAdicional;
	}



	public static Map<Long,Servicio> mapAll(Connection con, String db) {
		Map<Long,Servicio> map = new HashMap<Long,Servicio>();
		List<Servicio> lista = Servicio.all(con, db);
		lista.forEach(x->{
			map.put(x.getId(), x);
		});
		return(map);
	}
	
	public static Map<String,Servicio> mapAllPorCodigo(Connection con, String db) {
		Map<String,Servicio> map = new HashMap<String,Servicio>();
		List<Servicio> lista = Servicio.all(con, db);
		lista.forEach(x->{
			map.put(x.getCodigo(), x);
		});
		return(map);
	}
	
	public static List<Servicio> all(Connection con, String db) {
		List<Servicio> lista = new ArrayList<Servicio>();
		
		Map<Long,ClaseServicio> mapClase = ClaseServicio.mapAll(con, db);
		Map<Long,UnidadServicio> mapUnidad = UnidadServicio.mapAll(con, db);
		Map<Long,Moneda> mapMoneda = Moneda.mapMonedas(con, db);
		
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" servicio.id, " +
							" servicio.codigo, " +
							" servicio.nombre, " +
							" servicio.id_unidadServicio, " +
							" servicio.id_claseServicio, " +
							" servicio.descripcion, " +
							" servicio.id_moneda, " +
							" servicio.fecha, " +
							" servicio.precio, " +
							" servicio.cantMinimo, " +
							" servicio.precioAdicional " +
							" from `"+db+"`.servicio " +
							" order by servicio.nombre;");
			ResultSet rs = smt.executeQuery();
			
			while (rs.next()) {
				
				
				ClaseServicio clase = mapClase.get(rs.getLong(5));
				String nombreClase = "Sin Familia";
				if(clase !=null) nombreClase = clase.getNombre();
				
				UnidadServicio unidad = mapUnidad.get(rs.getLong(4));
				String nombreUnidad = "";
				if(unidad !=null) nombreUnidad = unidad.getNombre();
				
				Moneda moneda = mapMoneda.get(rs.getLong(7));
				String nickMoneda = "";
				Long decimal = (long)0;
				if(moneda !=null) {
					nickMoneda = moneda.getNickName();
					decimal = moneda.getNumeroDecimales();
				}

				lista.add(new Servicio(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getLong(4),rs.getLong(5),rs.getString(6),
						rs.getLong(7),rs.getString(8),rs.getDouble(9),nombreClase,nombreUnidad,nickMoneda,decimal,rs.getDouble(10),rs.getDouble(11)));
			}
			
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static File allExcel(String db, Map<String,String> mapDiccionario, List<List<String>> listado) {
			
		File tmp = null;
try{
	tmp = TempFile.createTempFile("tmp","null");
}catch(Exception e){}
		
		try {
			String path = "formatos/excel.xlsx";
			InputStream formato = Archivos.leerArchivo(path);
            Workbook libro = WorkbookFactory.create(formato);
            formato.close();
            
            // 0 negro 1 blanco 2 rojo 3 verde 4 azul 5 amarillo 19 celeste
            CellStyle titulo = libro.createCellStyle();
            Font font = libro.createFont();
            font.setBold(true);
            font.setColor((short)4);
            font.setFontHeight((short)(14*20));
            titulo.setFont(font);
            
            CellStyle subtitulo = libro.createCellStyle();
            Font font2 = libro.createFont();
            font2.setBold(true);
            font2.setColor((short)0);
            font2.setFontHeight((short)(12*20));
            subtitulo.setFont(font2);
            
            CellStyle encabezado = libro.createCellStyle();
            encabezado.setBorderBottom(BorderStyle.THIN);
            encabezado.setBorderTop(BorderStyle.THIN);
            encabezado.setBorderRight(BorderStyle.THIN);
            encabezado.setBorderLeft(BorderStyle.THIN);
            encabezado.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            encabezado.setFillForegroundColor((short)19);
            encabezado.setAlignment(HorizontalAlignment.LEFT);
            
            CellStyle detalle = libro.createCellStyle();
            detalle.setBorderBottom(BorderStyle.THIN);
            detalle.setBorderTop(BorderStyle.THIN);
            detalle.setBorderRight(BorderStyle.THIN);
            detalle.setBorderLeft(BorderStyle.THIN);
            
            CellStyle pie = libro.createCellStyle();
            pie.setBorderBottom(BorderStyle.THIN);
            pie.setBorderTop(BorderStyle.THIN);
            pie.setBorderRight(BorderStyle.THIN);
            pie.setBorderLeft(BorderStyle.THIN);
            pie.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            pie.setFillForegroundColor((short)19);
            pie.setAlignment(HorizontalAlignment.RIGHT);
            
            
            
            CreationHelper creationHelper = libro.getCreationHelper();
            CellStyle hora = libro.createCellStyle();
            hora.setDataFormat(creationHelper.createDataFormat().getFormat("hh:mm"));
            hora.setBorderBottom(BorderStyle.THIN);
            hora.setBorderTop(BorderStyle.THIN);
            hora.setBorderRight(BorderStyle.THIN);
            hora.setBorderLeft(BorderStyle.THIN);
            
            CellStyle fecha = libro.createCellStyle();
            fecha.setDataFormat(creationHelper.createDataFormat().getFormat("dd/MM/yyyy"));
            fecha.setBorderBottom(BorderStyle.THIN);
            fecha.setBorderTop(BorderStyle.THIN);
            fecha.setBorderRight(BorderStyle.THIN);
            fecha.setBorderLeft(BorderStyle.THIN);


            
            
            //titulos del archivo
            
            libro.setSheetName(0, "report");
            Sheet hoja1 = libro.getSheetAt(0);
            
            Row row = null;
            Cell cell = null;
            
            row = hoja1.createRow(1);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellValue("LISTA DE SERVICIOS");
			
			row = hoja1.createRow(2);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("EMPRESA: "+mapDiccionario.get("nEmpresa"));
			
			row = hoja1.createRow(3);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellValue("FECHA: "+Fechas.hoy().getFechaStrDDMMAA());
			
			
			
			//anchos de columnas
			hoja1.setColumnWidth(1, 4*1000);
			hoja1.setColumnWidth(2, 6*1000);
			hoja1.setColumnWidth(3, 10*1000);
			hoja1.setColumnWidth(4, 2*1000);
			hoja1.setColumnWidth(5, 2*1000);
			hoja1.setColumnWidth(6, 4*1000);
			hoja1.setColumnWidth(7, 4*1000);
			hoja1.setColumnWidth(8, 4*1000);
			hoja1.setColumnWidth(9, 4*1000);
			
			//INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
			InputStream x = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
            byte[] bytes = IOUtils.toByteArray(x);
            x.close();
            int pngIndex = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			Drawing draw = hoja1.createDrawingPatriarch();
			CreationHelper helper = libro.getCreationHelper();
			ClientAnchor anchor = helper.createClientAnchor();
	        //set top-left corner for the image
	        anchor.setCol1(9);
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
            cell.setCellStyle(encabezado);
			cell.setCellValue("FAMILIA");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("CODIGO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("SERVICIO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("UNIDAD");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("MONEDA");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("PRECIO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("FECHA_PRECIO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("CANT_MINIMO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellValue("PRECIO_ADICIONAL");
			
			
			for(List<String> lista1: listado){
				posRow++;
				posCell = 0;
		        row = hoja1.createRow(posRow);
				
		        posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(lista1.get(0));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(lista1.get(1));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(lista1.get(2));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(lista1.get(3));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellValue(lista1.get(4));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				try {
				Double aux = Double.parseDouble(lista1.get(5).replaceAll(",", ""));
				cell.setCellValue(aux);
				}catch(Exception e){
					cell.setCellValue((double)0);
				}
				
				posCell++;
				cell = row.createCell(posCell);
				Fechas fechax = Fechas.obtenerFechaDesdeStrAAMMDD(Fechas.AAMMDD(lista1.get(6)));
				cell.setCellValue(fechax.fechaUtil);
				cell.setCellStyle(fecha);
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				try {
				Double aux = Double.parseDouble(lista1.get(9).replaceAll(",", ""));
				cell.setCellValue(aux);
				}catch(Exception e){
					cell.setCellValue((double)0);
				}
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				try {
				Double aux = Double.parseDouble(lista1.get(10).replaceAll(",", ""));
				cell.setCellValue(aux);
				}catch(Exception e){
					cell.setCellValue((double)0);
				}
			}
			
			
			
			posRow = posRow + 5;
			row = hoja1.createRow(posRow);
			cell = row.createCell(1);
			Hyperlink hiper = helper.createHyperlink(HyperlinkType.URL);
			hiper.setAddress("https://www.inqsol.cl");
			cell.setHyperlink(hiper);
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
	
	public static List<Servicio> allNoEnBodega(Connection con, String db, BodegaEmpresa bodega, List<ListaPrecioServicio> listPrecios) {
		List<Servicio> lista = new ArrayList<Servicio>();
		List<Servicio> listServicios = Servicio.all(con, db);
		Map<String,ListaPrecioServicio> mapListaPrecio = ListaPrecioServicio.mapListaPrecioServicioXBodega(con, db, bodega);
		
		listServicios.forEach(s->{
			ListaPrecioServicio precio = mapListaPrecio.get(bodega.getId()+"_"+s.getId());
			if(precio==null) {
				lista.add(s);
			}
		});
		return (lista);
	}
	
	public static List<List<String>> allBodegaNoEnSevicio(Connection con, String db, List<List<String>> listBodegas, Long id_servicio) {
		List<List<String>> lista = new ArrayList<List<String>>();
		Map<Long,ListaPrecioServicio> mapListaPrecio = ListaPrecioServicio.mapListaPrecioServicioXServicio(con, db, id_servicio);
		
		listBodegas.forEach(s->{
			Long id_bodegaEmpresa = Long.parseLong(s.get(1));
			ListaPrecioServicio precio = mapListaPrecio.get(id_bodegaEmpresa);
			if(precio == null) {
				lista.add(s);
			}
		});
		return (lista);
	}
	
	public static boolean existeCodigo(Connection con, String db, String codigo) {
		boolean flag = false;
		try {
			PreparedStatement smt1 = con.prepareStatement("select * from `"+db+"`.servicio WHERE upper(codigo) = ?;");
			smt1.setString(1, codigo.toUpperCase());
			ResultSet rs = smt1.executeQuery();
			if (rs.next()) {
				flag = true;
			}
			smt1.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean create(Connection con, String db, FormServicioGraba aux) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("insert into `"+db+"`.servicio (codigo, nombre, id_unidadServicio, id_claseServicio, descripcion, id_moneda, fecha, precio) " +
							" values (?,?,?,?,?,?,?,?)");
			smt.setString(1, aux.codigo.replaceAll("\\,","").trim());
			smt.setString(2, aux.nombre.trim());
			smt.setLong(3, aux.id_unidadServicio);
			smt.setLong(4, aux.id_claseServicio);
			smt.setString(5, aux.descripcion);
			smt.setLong(6, aux.id_moneda);
			smt.setString(7, Fechas.hoy().getFechaStrAAMMDD());
			smt.setDouble(8, Double.parseDouble(aux.precio.replaceAll(",", "").trim()));
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static Servicio find(Connection con, String db, Long id_servicio) {
		Servicio aux = null;
		
		Map<Long,ClaseServicio> mapClase = ClaseServicio.mapAll(con, db);
		Map<Long,UnidadServicio> mapUnidad = UnidadServicio.mapAll(con, db);
		Map<Long,Moneda> mapMoneda = Moneda.mapMonedas(con, db);
		
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" servicio.id, " +
							" servicio.codigo, " +
							" servicio.nombre, " +
							" servicio.id_unidadServicio, " +
							" servicio.id_claseServicio, " +
							" servicio.descripcion, " +
							" servicio.id_moneda, " +
							" servicio.fecha, " +
							" servicio.precio, " +
							" servicio.cantMinimo, " +
							" servicio.precioAdicional " +
							" from `"+db+"`.servicio " +
							" where id = ?;");
			smt.setLong(1, id_servicio);

			ResultSet rs = smt.executeQuery();
			
			if (rs.next()) {
				ClaseServicio clase = mapClase.get(rs.getLong(5));
				String nombreClase = "Sin Familia";
				if(clase !=null) nombreClase = clase.getNombre();
				
				UnidadServicio unidad = mapUnidad.get(rs.getLong(4));
				String nombreUnidad = "";
				if(unidad !=null) nombreUnidad = unidad.getNombre();
				
				Moneda moneda = mapMoneda.get(rs.getLong(7));
				String nickMoneda = "";
				Long decimal = (long)0;
				if(moneda !=null) {
					nickMoneda = moneda.getNickName();
					decimal = moneda.getNumeroDecimales();
				}

				aux = new Servicio(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getLong(4),rs.getLong(5),rs.getString(6),
						rs.getLong(7),rs.getString(8),rs.getDouble(9),nombreClase,nombreUnidad,nickMoneda,decimal,rs.getDouble(10),rs.getDouble(11));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}
	
	public static boolean update(Connection con, String db, FormServicioGraba aux) {
		boolean flag = false;
		try {
			PreparedStatement smt2 = con
					.prepareStatement("update `"+db+"`.servicio set codigo=?, nombre=?, id_unidadServicio=?, id_claseServicio=?, descripcion=?, id_moneda=?, precio=? where id=?;");
			smt2.setString(1, aux.codigo.replaceAll("\\,","").trim());
			smt2.setString(2, aux.nombre.trim());
			smt2.setLong(3, aux.id_unidadServicio);
			smt2.setLong(4, aux.id_claseServicio);
			smt2.setString(5, aux.descripcion);
			smt2.setLong(6, aux.id_moneda);
			String auxprecio = aux.precio;
			Double precio = Double.parseDouble(auxprecio.replaceAll(",", ""));
			smt2.setDouble(7, precio);
			smt2.setLong(8, aux.id);
			smt2.executeUpdate();
			smt2.close();
			
			if(aux.cambioElprecio.equals("1")){
				PreparedStatement smt = con
						.prepareStatement("update `"+db+"`.servicio set fecha=?, precio=? where id=?;");
				smt.setString(1, Fechas.hoy().getFechaStrAAMMDD());
				smt.setDouble(2, Double.parseDouble(aux.precio.replaceAll(",", "").trim()));
				smt.setLong(3, aux.id);
				smt.executeUpdate();
				smt.close();
			}
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean modifyPrecio(Connection con, String db, Long id_servicio, String valor, String fecha) {
		boolean flag = false;
		try {
			PreparedStatement smt2 = con
					.prepareStatement("update `"+db+"`.servicio set precio=?, fecha=? where id=?;");
			smt2.setString(1, valor);
			smt2.setString(2, fecha);
			smt2.setLong(3, id_servicio);
			smt2.executeUpdate();
			smt2.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean modifyCantMinimo(Connection con, String db, Long id_servicio, String valor, String fecha) {
		boolean flag = false;
		try {
			PreparedStatement smt2 = con
					.prepareStatement("update `"+db+"`.servicio set cantMinimo=?, fecha=? where id=?;");
			smt2.setString(1, valor);
			smt2.setString(2, fecha);
			smt2.setLong(3, id_servicio);
			smt2.executeUpdate();
			smt2.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean modifyPrecioAdicional(Connection con, String db, Long id_servicio, String valor, String fecha) {
		boolean flag = false;
		try {
			PreparedStatement smt2 = con
					.prepareStatement("update `"+db+"`.servicio set precioAdicional=?, fecha=? where id=?;");
			smt2.setString(1, valor);
			smt2.setString(2, fecha);
			smt2.setLong(3, id_servicio);
			smt2.executeUpdate();
			smt2.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean estaEnUso(Connection con, String db, Long id_servicio) {
		boolean flag = false;
		try {
			PreparedStatement smt1 = con
					.prepareStatement("Select * from `"+db+"`.ventaServicio WHERE id_servicio = ?");
			smt1.setLong(1, id_servicio);
			ResultSet rs1 = smt1.executeQuery();
			if (rs1.next()) {
				flag = true;
			}
			rs1.close();
			smt1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean delete(Connection con, String db, Long id_servicio) {
		boolean flag = false;
		try {
			PreparedStatement smt1 = con.prepareStatement("delete from `"+db+"`.servicio WHERE id = ?;");
			smt1.setLong(1, id_servicio);
			smt1.executeUpdate();
			smt1.close();
	
			PreparedStatement smt = con.prepareStatement("delete from `"+db+"`.listaPrecioServicio WHERE id_servicio = ?;");
			smt.setLong(1, id_servicio);
			smt.executeUpdate();
			smt.close();
			
			PreparedStatement smt2 = con.prepareStatement("delete from `"+db+"`.precioVariableServicio WHERE id_servicio = ?;");
			smt2.setLong(1, id_servicio);
			smt2.executeUpdate();
			smt2.close();
			
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	
	
	
	
}
