package models.tables;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.util.TempFile;

import models.utilities.Archivos;
import models.utilities.Fechas;





public class HojaVida {
	public Long id;
	public Long id_equipo;
	public Long id_tipoMantencion;
	public String fechaInicio;
	public Long id_tipoTrabajo;
	public Long id_moneda;
	public String costoNeto;
	public Long id_proveedor;
	public String numeroDocumento;
	public String fechaDocumento;
	public String documentoPDF;
	public String fechaTermino;
	public String trabajoHecho;
	
	public Long contador;
	public String tipoMantencionNombre;
	public String tipoTrabajoNombre;
	public String monedaNickName;
	public String proveedorNickName;
	
	public Long id_tipoPlan;
	public String tipoPlanNombre;
	
	

	public HojaVida(Long id, Long id_equipo, Long id_tipoMantencion, String fechaInicio, Long id_tipoTrabajo,
			Long id_moneda, String costoNeto, Long id_proveedor, String numeroDocumento, String fechaDocumento,
			String documentoPDF, String fechaTermino, String trabajoHecho, Long contador, String tipoMantencionNombre,
			String tipoTrabajoNombre, String monedaNickName, String proveedorNickName, Long id_tipoPlan,
			String tipoPlanNombre) {
		super();
		this.id = id;
		this.id_equipo = id_equipo;
		this.id_tipoMantencion = id_tipoMantencion;
		this.fechaInicio = fechaInicio;
		this.id_tipoTrabajo = id_tipoTrabajo;
		this.id_moneda = id_moneda;
		this.costoNeto = costoNeto;
		this.id_proveedor = id_proveedor;
		this.numeroDocumento = numeroDocumento;
		this.fechaDocumento = fechaDocumento;
		this.documentoPDF = documentoPDF;
		this.fechaTermino = fechaTermino;
		this.trabajoHecho = trabajoHecho;
		this.contador = contador;
		this.tipoMantencionNombre = tipoMantencionNombre;
		this.tipoTrabajoNombre = tipoTrabajoNombre;
		this.monedaNickName = monedaNickName;
		this.proveedorNickName = proveedorNickName;
		this.id_tipoPlan = id_tipoPlan;
		this.tipoPlanNombre = tipoPlanNombre;
	}

	public HojaVida() {super();}

	static DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
	static SimpleDateFormat myformatfecha = new SimpleDateFormat("dd-MM-yyyy");
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00",symbols);
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00",symbols);
	
	public static List<HojaVida> allPorEquipo(Connection con, String db, Long idEquipo) {
		List<HojaVida> lista = new ArrayList<HojaVida>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" hojaVida.id,id_equipo,id_tipoMantencion,fechaInicio,id_tipoTrabajo,id_moneda,costoNeto," +
							" id_proveedor,numeroDocumento,fechaDocumento,documentoPDF,fechaTermino,ifnull(trabajoHecho,''), " +
							" tipoMantencion.nombre,tipoTrabajo.nombre,moneda.nickName,proveedor.nickName, hojaVida.id_tipoPlan, tipoPlan.nombre" +
							" from `"+db+"`.hojaVida " +
							" left join `"+db+"`.tipoPlan on tipoPlan.id = hojaVida.id_tipoPlan  " +
							" left join `"+db+"`.tipoMantencion on tipoMantencion.id = id_tipoMantencion " +
							" left join `"+db+"`.tipoTrabajo on tipoTrabajo.id = id_tipoTrabajo " +
							" left join `"+db+"`.proveedor on proveedor.id = id_proveedor " +
							" left join `"+db+"`.moneda on moneda.id = id_moneda  " +
							" where id_equipo = ? " +
							" order by fechaInicio desc, hojaVida.id desc;" );
			smt.setLong(1, idEquipo);
			ResultSet rs = smt.executeQuery();
			Long contador = (long) 3;
			while (rs.next()) {
				String fechaInicio = null;		
				if (rs.getDate(4) != null) {fechaInicio = myformatfecha.format(rs.getDate(4));}
				String fechaDocumento = null;		
				if (rs.getDate(10) != null) {fechaDocumento = myformatfecha.format(rs.getDate(10));}
				String fechaTermino = null;		
				if (rs.getDate(12) != null) {fechaTermino = myformatfecha.format(rs.getDate(12));}
				lista.add((new HojaVida(rs.getLong(1),rs.getLong(2),rs.getLong(3),fechaInicio,rs.getLong(5),rs.getLong(6),
						myformatdouble2.format(rs.getDouble(7)),rs.getLong(8),rs.getString(9),fechaDocumento,
						rs.getString(11),fechaTermino,rs.getString(13),contador,rs.getString(14),rs.getString(15),
						rs.getString(16),rs.getString(17),rs.getLong(18),rs.getString(19))));
				contador=contador+2;
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<List<String>> tiposPlanPorEquipo(Connection con, String db, Long id_equipo) {
		List<List<String>> lista = new ArrayList<List<String>>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select id, nombre from `"+db+"`.tipoPlan where id in (select id_tipoPlan from `"+db+"`.planMantencion where id_equipo=?);");
			smt.setLong(1, id_equipo);
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {	
				List<String> aux = new ArrayList<String>();
				aux.add(rs.getString(1));
				aux.add(rs.getString(2));
				lista.add(aux);
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (lista);
	}
	
	public static Map<Long, Long> mapId_iConstruyeProdDet(Connection con, String db) {
		 Map<Long, Long> map = new HashMap<Long, Long>();
		try {
			PreparedStatement smt = con
					.prepareStatement("select id_iConstruyeProdDet from `"+db+"`.hojaVida;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {	
				map.put(rs.getLong(1), rs.getLong(1));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (map);
	}
	
	public static boolean agregaLinea(Connection con, String db, Long id_equipo) {
		boolean flag = false;
		Fechas hoy = Fechas.hoy();
		try {
			PreparedStatement smt = con
					.prepareStatement("insert into `"+db+"`.hojaVida (id_equipo, fechaInicio, fechaTermino) values (?,?,?);");
			smt.setLong(1, id_equipo);
			smt.setString(2, hoy.getFechaStrAAMMDD());
			smt.setString(3, hoy.getFechaStrAAMMDD());
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return(flag);
	}
	
	public static boolean insert(Connection con, String db, String insert) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("insert into `"+db+"`.hojaVida (id_tipoPlan, id_equipo, id_tipoMantencion, fechaInicio, id_tipoTrabajo, id_moneda, costoNeto, id_proveedor, numeroDocumento, fechaDocumento, trabajoHecho, fechaTermino) "
							+ " values "+insert+";");
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return(flag);
	}
	
	public static boolean eliminaLinea(Connection con, String db, Long id_hojaVida) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("delete from `"+db+"`.hojaVida where id = ?;");
			smt.setLong(1, id_hojaVida);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return(flag);
	}
	
	
	public static List<String> atributosEquipo(Connection con, String db, Long id_equipo) {
		List<String> lista = new ArrayList<String>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" concat(atributo.atributo,': ',if(numAtributo=0,strAtributo,numAtributo)) " +
							" from `"+db+"`.atributoEquipo " +
							" left join `"+db+"`.atributo on atributo.id = atributoEquipo.id_atributo " +
							" where id_equipo = ? order by atributo.atributo;" );
			smt.setLong(1, id_equipo);
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {		
				lista.add(rs.getString(1));
			}
			rs.close();
			smt.close();
			
			if(lista.size()==0) {
				List<String> aux = new ArrayList<String>();
				aux.add("");
				lista = aux;
			}
			
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<String> ultimaFacturaCompra(Connection con, String db, Long idEquipo) {
		List<String> lista = new ArrayList<String>();
		try {
			PreparedStatement smt = con
					.prepareStatement(" select " +
							" proveedor.rut,proveedor.nickname, " +
							" factura.numero,factura.fecha,factura.facturaPDF, " +
							" moneda.nickName, " +
							" precioUnidad,moneda.id " +
							" from `"+db+"`.compra " +
							" left join `"+db+"`.factura on factura.id = id_factura " +
							" left join `"+db+"`.proveedor on proveedor.id = factura.id_proveedor " +
							" left join `"+db+"`.moneda on moneda.id = compra.id_moneda " +
							" where id_equipo = ?;" );
			smt.setLong(1, idEquipo);
			Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				List<String> aux = new ArrayList<String>();
				String fecha = null;		
				if (rs.getDate(4) != null) {fecha = myformatfecha.format(rs.getDate(4));}
				switch(dec.get(rs.getLong(8)).toString()) {
				 case "0": myformatdouble = new DecimalFormat("#,##0",symbols); break;
				 case "2": myformatdouble = new DecimalFormat("#,##0.00",symbols); break;
				 case "4": myformatdouble = new DecimalFormat("#,##0.0000",symbols); break;
				 case "6": myformatdouble = new DecimalFormat("#,##0.000000",symbols); break;
				 default:  break;
				}
				aux.add(rs.getString(1));
				aux.add(rs.getString(2));
				aux.add(rs.getString(3));
				aux.add(fecha);
				aux.add(rs.getString(5));
				aux.add(rs.getString(6));
				aux.add(myformatdouble.format(rs.getDouble(7)));
				
				if(lista.size()>0) {
					Calendar auxLista = Fechas.obtenerFechaDesdeStrDDMMAA(lista.get(3)).getFechaCal();
					Calendar auxAux = Fechas.obtenerFechaDesdeStrDDMMAA(aux.get(3)).getFechaCal();
					if(auxLista.before(auxAux)) {
						lista = aux;
					}
				}else {
					lista = aux;
				}
			}
			rs.close();
			smt.close();
			
			if(lista.size()==0) {
				List<String> aux = new ArrayList<String>();
				aux.add("");
				aux.add("");
				aux.add("");
				aux.add("");
				aux.add("");
				aux.add("");
				aux.add("");
				lista = aux;
			}
			
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (lista);
	}
	
	public static Boolean actualizaPorCampo(Connection con, String db, Long id_hojaVida, String campo, String valor) {
		Boolean flag = false;
		try {	
			PreparedStatement smt = con
					.prepareStatement("update `"+db+"`.hojaVida set `" + campo + "` = ? where id = ?;");
			smt.setString(1, valor.trim());
			smt.setLong(2, id_hojaVida);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	
	public static File hojaVidaReportDetalleExcel(String db, Map<String,String> mapDiccionario, List<PlanMantencion> allPlan, List<HojaVida> lista) {
		
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
            encabezado.setAlignment(CellStyle.ALIGN_CENTER);
            
            CellStyle detalle = libro.createCellStyle();
            detalle.setBorderBottom(CellStyle.BORDER_THIN);
            detalle.setBorderTop(CellStyle.BORDER_THIN);
            detalle.setBorderRight(CellStyle.BORDER_THIN);
            detalle.setBorderLeft(CellStyle.BORDER_THIN);
            
            
            Sheet hoja1 = libro.getSheetAt(0);
            Row row = null;
            Cell cell = null;
            
            row = hoja1.createRow(1);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("HOJA DE VIDA POR EQUIPO");
		
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
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("CODIGO: "+allPlan.get(0).equipoCodigo);
			
			row = hoja1.createRow(6);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("EQUIPO: "+allPlan.get(0).equipoNombre);
			
			
			// encabezado de la tabla
			
			int posCell = 0;
			int posColl = 0;
			row = hoja1.createRow(8);
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TIPO MANTENCION");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TIPO PLAN");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FECHA INICIO");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TIPO TRABAJO");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("MONEDA");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("COSTO NETO");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("PRESTADOR SERVICIO");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("Nro DOCUMENTO");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FECHA DOCUMENTO");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FECHA TERMINO");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 15*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TRABAJO HECHO");
		
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
			
			//DETALLE DE LA TABLA
			int posRow = 9;
			for(int i=0;i<lista.size();i++){
				row = hoja1.createRow(posRow);
				posCell = 0;
				posColl = 0;
				Double aux = (double)0;
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).tipoMantencionNombre);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).tipoPlanNombre);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(Fechas.DDMMAA(lista.get(i).fechaInicio));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).tipoTrabajoNombre);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).monedaNickName);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            aux = Double.parseDouble(lista.get(i).costoNeto.replaceAll(",", ""));
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(aux);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).proveedorNickName);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).numeroDocumento);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String fecha = "";
				if(lista.get(i).fechaDocumento != null ){
					fecha = Fechas.DDMMAA(lista.get(i).fechaDocumento);
				}
				cell.setCellValue(fecha);
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(Fechas.DDMMAA(lista.get(i).fechaTermino));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).trabajoHecho);
				
				posRow++;
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
	
	public static File hojaVidaImportIConstruye(List<List<String>> lista, List<Equipo> listEquipo, List<TipoMantencion> listTipoMantencion, List<TipoPlan> listTipoPlan, List<TipoTrabajo> listTipoTrabajo) {
		
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
            encabezado.setAlignment(CellStyle.ALIGN_CENTER);
            
            CellStyle detalle = libro.createCellStyle();
            detalle.setBorderBottom(CellStyle.BORDER_THIN);
            detalle.setBorderTop(CellStyle.BORDER_THIN);
            detalle.setBorderRight(CellStyle.BORDER_THIN);
            detalle.setBorderLeft(CellStyle.BORDER_THIN);
            
            
            
            //Datos de las listas a restringir
            
            Sheet hoja2 = libro.getSheetAt(1);
            Row row = null;
            Cell cell = null;
            
            for(int i=0; i<listEquipo.size(); i++) {
            	row = hoja2.createRow(i);
            	cell = row.createCell(0);
            	cell.setCellType(Cell.CELL_TYPE_STRING);
    			cell.setCellValue(listEquipo.get(i).getCodigo());
            }
            
            for(int i=0; i<listTipoMantencion.size(); i++) {
            	row = hoja2.getRow(i);
            	if(row==null) {
            		row = hoja2.createRow(i);
            	}
            	cell = row.createCell(1);
            	cell.setCellType(Cell.CELL_TYPE_STRING);
    			cell.setCellValue(listTipoMantencion.get(i).getNombre());
            }
            
            for(int i=0; i<listTipoPlan.size(); i++) {
            	row = hoja2.getRow(i);
            	if(row==null) {
            		row = hoja2.createRow(i);
            	}
            	cell = row.createCell(2);
            	cell.setCellType(Cell.CELL_TYPE_STRING);
    			cell.setCellValue(listTipoPlan.get(i).getNombre());
            }
            
            for(int i=0; i<listTipoTrabajo.size(); i++) {
            	row = hoja2.getRow(i);
            	if(row==null) {
            		row = hoja2.createRow(i);
            	}
            	cell = row.createCell(3);
            	cell.setCellType(Cell.CELL_TYPE_STRING);
    			cell.setCellValue(listTipoTrabajo.get(i).getNombre());
            }
            
            DataValidationHelper helper = hoja2.getDataValidationHelper();
            DataValidationConstraint constraintCodigo = helper.createFormulaListConstraint("'Hoja2'!$A$1:$A$"+listEquipo.size());
            DataValidationConstraint constraintTipoMant = helper.createFormulaListConstraint("'Hoja2'!$B$1:$B$"+listTipoMantencion.size());
            DataValidationConstraint constraintTipoPlan = helper.createFormulaListConstraint("'Hoja2'!$C$1:$C$"+listTipoPlan.size());
            DataValidationConstraint constraintTipoTrab = helper.createFormulaListConstraint("'Hoja2'!$D$1:$D$"+listTipoTrabajo.size());
            
            
            // encabezado de la tabla
            
            Sheet hoja1 = libro.getSheetAt(0);
   
			int posCell = 0;
			int posColl = 0;
			row = hoja1.createRow(0);
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("CODIGO_EQUIPO");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TIPO_MANTENCION");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TIPO_PLAN");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TIPO_TRABAJO");
						
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("ID_DOC");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("NRO_OC");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 10*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("NOMBRE_OC");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FECHA_OC");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FECHA_ENVIO");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 3*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("MONEDA");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("COSTO_NETO");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 5*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("RUT_PROVEEDOR");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 10*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("PROVEEDOR");
			
			posCell++; posColl++;
			hoja1.setColumnWidth(posColl, 20*1000);
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TRABAJO_HECHO");
			
			
			
			//DETALLE DE LA TABLA
			int posRow = 1;
			for(int i=0;i<lista.size();i++){
				row = hoja1.createRow(posRow);
				posCell = 0;
				posColl = 0;
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("");
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("");
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("");
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("");
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(0));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(1));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(18));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(19));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(2));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(11));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
	            cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(13));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(3));
				
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(lista.get(i).get(4));
				
				String trabajoHecho = lista.get(i).get(18)+"_-_"+lista.get(i).get(6)+"_-_"+lista.get(i).get(7)+"_-_"+lista.get(i).get(8);
				posCell++; posColl++;
	            cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(trabajoHecho);
				
				
				posRow++;
			}
			
			if(lista.size()>1) {
				 
				 CellRangeAddressList addressList = new CellRangeAddressList(1, lista.size(), 1, 1);
				 DataValidation dataValidation = helper.createValidation(constraintCodigo, addressList);
				 dataValidation.setSuppressDropDownArrow(true);
				 dataValidation.setShowErrorBox(true);
				 hoja1.addValidationData(dataValidation);
				 
				 addressList = new CellRangeAddressList(1, lista.size(), 2, 2);
				 dataValidation = helper.createValidation(constraintTipoMant, addressList);
				 dataValidation.setSuppressDropDownArrow(true);
				 dataValidation.setShowErrorBox(true);
				 hoja1.addValidationData(dataValidation);
				 
				 addressList = new CellRangeAddressList(1, lista.size(), 3, 3);
				 dataValidation = helper.createValidation(constraintTipoPlan, addressList);
				 dataValidation.setSuppressDropDownArrow(true);
				 dataValidation.setShowErrorBox(true);
				 hoja1.addValidationData(dataValidation);
				 
				 addressList = new CellRangeAddressList(1, lista.size(), 4, 4);
				 dataValidation = helper.createValidation(constraintTipoTrab, addressList);
				 dataValidation.setSuppressDropDownArrow(true);
				 dataValidation.setShowErrorBox(true);
				 hoja1.addValidationData(dataValidation);
			}
			
			
			CreationHelper helper2 = libro.getCreationHelper();
			
			posRow = posRow + 5;
			row = hoja1.createRow(posRow);
			cell = row.createCell(1);
			Hyperlink hiper = helper2.createHyperlink(0);
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
	
	public static List<List<String>> hojaVidaLEEimportIConstruye(File file) {
		 List<List<String>> lista = new ArrayList<List<String>>();
		 DecimalFormat df = new DecimalFormat("#",symbols);
 	    df.setMaximumFractionDigits(8);
		try {
            Workbook libro = WorkbookFactory.create(file);
            Sheet hoja1 = libro.getSheetAt(0);
            Row row = null;
            Cell cell = null;
            int x = 0;
            row = hoja1.getRow(0);
            if(row!=null) {
            	cell = row.getCell(5);
            }
            while (row!=null && cell !=null ) {
            	row = hoja1.getRow(x++);
            	if(row!=null) {
            		cell = row.getCell(5);
                	if(cell!=null) {
                		boolean noEsBlanco = true;
                    	try {
                    		String dato = cell.getStringCellValue().trim();
                    		if(dato.trim().equals("")) {
                    			noEsBlanco = false;
                    		}
                    	}catch(Exception e){
                    		Double aux = cell.getNumericCellValue();
                    		if(aux.toString().trim().equals("")) {
                    			noEsBlanco = false;
                    		}
                    	}
                		if(noEsBlanco) {
                			List<String> auxList = new ArrayList<String>();
                    		for(int i=1; i<15; i++) {
                    			String dato = "";
                    			cell = row.getCell(i);
                                if(cell!=null) {
                                	try {
                                		dato = cell.getStringCellValue().trim();
                                		dato = dato.replaceAll("'", "\"");
                                	}catch(Exception e){
                                		Double aux = cell.getNumericCellValue();
                                		dato = df.format(aux);
                                	}
                                }
                                auxList.add(dato);
                            }
                    		lista.add(auxList);
                		}
                	}
                	cell = row.getCell(4);
            	}
            }
		} catch (Exception e) {
			e.printStackTrace();
        }
		
		/* estructura de la lista (la primera linea contiene los encabezados)
		 	0 CODIGO_EQUIPO	
		 	1 TIPO_MANTENCION
		 	2 TIPO_PLAN
		 	3 TIPO_TRABAJO	
		 	4 ID_DOC	
		 	5 NRO_OC	
		 	6 NOMBRE_OC	
		 	7 FECHA_OC	
		 	8 FECHA_ENVIO	
		 	9 MONEDA	
		 	10 COSTO_NETO	
		 	11 RUT_PROVEEDOR	
		 	12 PROVEEDOR	
		 	13 TRABAJO_HECHO
		 */
		return(lista);
	}
	
	
}
