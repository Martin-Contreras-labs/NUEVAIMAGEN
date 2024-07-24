package models.reports;


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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.TempFile;

import models.tables.Atributo;
import models.tables.BodegaEmpresa;
import models.tables.Equipo;
import models.tables.Sucursal;
import models.utilities.Archivos;
import models.utilities.Fechas;


public class ReportExcedentes {
	
	static SimpleDateFormat myformatfecha = new SimpleDateFormat("dd-MM-yyyy");
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdoubleV = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatMonedaOrigen = new DecimalFormat("#,##0");
	
	
	
	public static Map<String,Double> totalExcedentesPorCodyBod(Connection con, String db, Long id_bodegaEmpresa){
		Map<String,Double> map = new HashMap<String,Double>();
		try {
			PreparedStatement smt4 = con
					.prepareStatement(" select " +
							" equipo.codigo, " +
							" sum(if(movimiento.id_tipoMovimiento=2,1,-1)*movimiento.exceso) "+
							" from `"+db+"`.movimiento " +
							" left join `"+db+"`.equipo on equipo.id = movimiento.id_equipo " +
							" where movimiento.id_bodegaEmpresa =  ? "+  
							" group by equipo.codigo " + 
							" having  sum(movimiento.exceso)>0;");
			smt4.setLong(1, id_bodegaEmpresa);
			
			ResultSet rs4 = smt4.executeQuery();
			while (rs4.next()) {
				map.put(rs4.getString(1).trim(), rs4.getDouble(2));
			}
			rs4.close();
			smt4.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (map);
		
		
	}
	
	public static Map<String,Double> totalExcedentesPorNumCotiCodyBod(Connection con, String db, Long id_bodegaEmpresa){
		Map<String,Double> map = new HashMap<String,Double>();
		try {
			PreparedStatement smt4 = con
					.prepareStatement(" select " +
							" equipo.codigo, " +
							" sum(if(movimiento.id_tipoMovimiento=2,1,-1)*movimiento.exceso), "+
							" ifnull(cotizacion.numero,0) " +
							" from `"+db+"`.movimiento " +
							" left join `"+db+"`.equipo on equipo.id = movimiento.id_equipo " +
							" left join `"+db+"`.cotizacion on cotizacion.id = movimiento.id_cotizacion "+
							" where movimiento.id_bodegaEmpresa =  ? "+  
							" group by equipo.codigo " + 
							" having  sum(movimiento.exceso)>0;");
			smt4.setLong(1, id_bodegaEmpresa);
			ResultSet rs4 = smt4.executeQuery();
			while (rs4.next()) {
				map.put(rs4.getString(1).trim()+"_"+rs4.getString(3).trim(), rs4.getDouble(2));
			}
			rs4.close();
			smt4.close();
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (map);
		
		
	}
	
	public static List<List<String>> reportExcedentesSoloProyectosSelectivo(Connection con, String db, String permisoPorBodega, String esPorSucursal, String id_sucursal) {
		List<List<String>> lista = new ArrayList<List<String>>();
		
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and bodegaEmpresa.id_sucursal = " + id_sucursal;
		}
		
		try {
			PreparedStatement smt5 = con
					.prepareStatement(" select " +
							" bodegaEmpresa.esInterna, " +
							" movimiento.id_bodegaEmpresa, " +
							" ifnull(cliente.id,0), " +
							" ifnull(proyecto.id,0), " +
							" bodegaEmpresa.nombre,   " +
							" ifnull(cliente.rut,''), " +
							" ifnull(cliente.nickName,''), " +
							" ifnull(proyecto.nickName,''), " +
							" ifnull(comunas.nombre,''), " +
							" tipoBodega.nombre, " +
							" bodegaEmpresa.factorM2Viga, " +
							" movimiento.id_cotizacion, "+
							" sum(movimiento.exceso), " +
							" bodegaEmpresa.id_sucursal " +
							" from `"+db+"`.movimiento   " +
							" left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa  " +
							" left join `"+db+"`.cliente on cliente.id = bodegaEmpresa.id_cliente " +
							" left join `"+db+"`.proyecto on proyecto.id = bodegaEmpresa.id_proyecto " +
							" left join `"+db+"`.comunas on comunas.codigo = proyecto.cod_comuna " +
							" left join `"+db+"`.tipoBodega on tipoBodega.id = bodegaEmpresa.esInterna " +
							" where bodegaEmpresa.vigente = 1 and tipoBodega.id = 2 " + permisoPorBodega + condSucursal +
							" group by movimiento.id_bodegaEmpresa  " +
							" having sum(movimiento.exceso) >0 " +
							" order by bodegaEmpresa.esInterna,bodegaEmpresa.nombre;");
			
			ResultSet rs5 = smt5.executeQuery();
			Map<Long, Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			while (rs5.next()) {
				String nameSucursal = "";
				Sucursal sucursal = mapSucursal.get(rs5.getLong(14));
				if(sucursal!=null) {
					nameSucursal = sucursal.getNombre();
				}
				List<String> aux = new ArrayList<String>();
				aux.add(rs5.getString(1));  // 0 es cliente interno
				aux.add(rs5.getString(2));  // 1 idbodega empresa
				aux.add(rs5.getString(3));  // 2 id de cliente
				aux.add(rs5.getString(4));  // 3 id del proyecto
				aux.add(rs5.getString(10));  // 4 tipo de cliente interno o externo
				aux.add(rs5.getString(5));  // 5 nombre bodega o empresa
				aux.add(rs5.getString(6));  // 6 rut del cliente
				aux.add(rs5.getString(7));  // 7 nombre del cliente
				aux.add(rs5.getString(8));  // 8 nombre del proyecto
				aux.add(rs5.getString(9));  // 9 comuna
				aux.add(rs5.getString(11)); // 10 factorViga
				aux.add(nameSucursal); // 11 nameSucursal
				lista.add(aux);
			}
			rs5.close();
			smt5.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<List<String>> reportExcedentesDetallePorProyecto(Connection con, String db, Long id_bodegaEmpresa) {
		List<List<String>> lista = new ArrayList<List<String>>();
		try {
			PreparedStatement smt1 = con
					.prepareStatement(" select  distinct " +
							" guia.numero, " +
							" concat(day(guia.fecha),'/',month(guia.fecha),'/',year(guia.fecha)),  " +   
							" tipoMovimiento.nombre " +
							" from `"+db+"`.movimiento " +
							" left join `"+db+"`.guia on guia.id = movimiento.id_guia " +
							" left join `"+db+"`.tipoMovimiento on tipoMovimiento.id = id_tipoMovimiento  " +
							" where id_bodegaEmpresa =  ? and numero is not null " +
							"  and movimiento.exceso>0 " +
							" and movimiento.esVenta = 0 order by guia.fecha,guia.numero;");
			smt1.setLong(1, id_bodegaEmpresa);
			ResultSet rs1 = smt1.executeQuery();
				List<String> numGuia = new ArrayList<String>();
				List<String> fechGuia = new ArrayList<String>();
				List<String> blanco = new ArrayList<String>();
				numGuia.add(" ");
				fechGuia.add(" ");
				blanco.add("Grupo");
				numGuia.add(" ");
				fechGuia.add(" ");
				blanco.add("Nro.Coti");
				numGuia.add(" ");
				fechGuia.add(" ");
				blanco.add("Código");
				numGuia.add(" ");
				fechGuia.add(" ");
				blanco.add("Descripción");
				numGuia.add(" ");
				fechGuia.add(" ");
				blanco.add("kg");
				numGuia.add(" ");
				fechGuia.add(" ");
				blanco.add("m2");
				
				numGuia.add("Nro. Mov:");
				fechGuia.add("Fecha:");
				blanco.add(" ");
				
				while (rs1.next()) {
					numGuia.add(rs1.getString(1));
					fechGuia.add(rs1.getString(2));
					blanco.add(" ");
				}
				
				rs1.close();
				smt1.close();
				
				numGuia.add("TOTAL DE");
				fechGuia.add("EXCEDEN");
				blanco.add("");
				
				PreparedStatement smt2 = con
						.prepareStatement(" select distinct  guia.numero " +
								" from `"+db+"`.guia " +
								" left join `"+db+"`.movimiento on movimiento.id_guia = guia.id " +
								" where id_bodegaEmpresa = ?  "+
								" and movimiento.esVenta = 0 and movimiento.exceso>0  " +
								" order by guia.fecha,guia.numero; "); 
				smt2.setLong(1, id_bodegaEmpresa);
				ResultSet rs2 = smt2.executeQuery();
				List<String> listaGuias = new ArrayList<String>();
				while (rs2.next()) {
					listaGuias.add(rs2.getString(1));
				}
				rs2.close();
				smt2.close();
				
				PreparedStatement smt3 = con		// 1-2-3-12-13
						.prepareStatement(" select distinct " +
								" grupo.nombre, " +
								" equipo.codigo, " +
								" equipo.nombre, " +
								" movimiento.id_cotizacion, " +
								" ifnull(cotizacion.numero,0), " +
								" equipo.id " +
								" from `"+db+"`.movimiento " + 
								" left join `"+db+"`.cotizacion on cotizacion.id = movimiento.id_cotizacion " +
								" left join `"+db+"`.equipo on equipo.id=movimiento.id_equipo " + 
								" left join `"+db+"`.grupo on grupo.id = equipo.id_grupo  " + 
								" where " + 
								" movimiento.id_bodegaEmpresa=? "+
								" and equipo.nombre is not null and movimiento.esVenta = 0 "+ 
								" order by grupo.nombre,equipo.nombre;"); 
				smt3.setLong(1, id_bodegaEmpresa);
				ResultSet rs3 = smt3.executeQuery();
				List<List<String>> listaCodigos = new ArrayList<List<String>>();
				while (rs3.next()) {
					List<String> aux = new ArrayList<String>();
				
					aux.add(rs3.getString(1));
					aux.add(rs3.getString(2));
					aux.add(rs3.getString(3));
					aux.add(rs3.getString(4)); //  8	3 id cotizacion
					aux.add(rs3.getString(5)); //  9	4 numero coti
					aux.add(rs3.getString(6)); //  10	5 idequipo
					listaCodigos.add(aux);
				}
				rs3.close();
				smt3.close();
				
				PreparedStatement smt4 = con
						.prepareStatement(" select " +
								" equipo.codigo,    " +
								" guia.numero, " +
								" sum(if(movimiento.id_tipoMovimiento=2,1,-1)*movimiento.exceso),   " +
								" movimiento.id_cotizacion "+
								" from `"+db+"`.movimiento " +
								" left join `"+db+"`.equipo on equipo.id = movimiento.id_equipo " +
								" left join `"+db+"`.guia on guia.id = movimiento.id_guia " +
								" where movimiento.id_bodegaEmpresa =  ? "+  
								" and guia.fecha is not null   " +
								" and movimiento.esVenta = 0    " +
								" group by equipo.codigo, guia.numero, movimiento.id_cotizacion  " + 
								" having  sum(movimiento.exceso)>0 " +
								" order by guia.fecha,guia.numero,equipo.codigo;");
				smt4.setLong(1, id_bodegaEmpresa);
				ResultSet rs4 = smt4.executeQuery();
				Map<String,String> codGuiaCant = new HashMap<String,String>();
				while (rs4.next()) {
					codGuiaCant.put(rs4.getString(1).trim()+rs4.getString(2).trim()+"-"+rs4.getString(4).trim(), rs4.getString(3));
				}
				rs4.close();
				smt4.close();
				
				lista.add(numGuia);
				lista.add(fechGuia);
				lista.add(blanco);
				
				
				Map<Long,Double> pesos = Atributo.mapAtributoPESO(con, db);
				Map<Long,Double> m2 = Atributo.mapAtributoM2(con, db);
				
				String auxiliarDeReparacion="";
				for(int i=0;i<listaCodigos.size();i++){
					String dePaso = listaCodigos.get(i).get(0)+listaCodigos.get(i).get(4)+listaCodigos.get(i).get(1)+listaCodigos.get(i).get(2);
					if(!auxiliarDeReparacion.equals(dePaso)) {
						auxiliarDeReparacion=dePaso;
						
						
						List<String> aux = new ArrayList<String>();
						aux.add(listaCodigos.get(i).get(0)); //grupo
						aux.add(listaCodigos.get(i).get(4)); //numero cotizacion
						aux.add(listaCodigos.get(i).get(1)); //codigo
						aux.add(listaCodigos.get(i).get(2)); //equipo
						
						// kg por unidad
						Double kg = pesos.get(Long.parseLong(listaCodigos.get(i).get(5).trim()));
						if(kg==null) {
							aux.add(""); 
						}else {
							aux.add(myformatdouble2.format(kg)); 
						}
						
						// m2 por unidad
						Double supM2 = m2.get(Long.parseLong(listaCodigos.get(i).get(5).trim()));
						if(supM2==null) {
							aux.add("");
						}else {
							aux.add(myformatdouble2.format(supM2));
						}
						aux.add("");
						
						Double subtotal=(double)0;
						Double subtotalAbsoluto=(double)0;
						
						for(int k=0;k<listaGuias.size();k++){
							String keyAux=listaCodigos.get(i).get(1).trim()+listaGuias.get(k).trim()+"-"+listaCodigos.get(i).get(3);
							String cantidad = codGuiaCant.get(keyAux);
							
							if(cantidad==null){
								aux.add(" ");
							
							}else{
								Double auxSubTot = Double.parseDouble(cantidad.trim());
								subtotal =  auxSubTot + subtotal;
								aux.add(cantidad);
								
								if(auxSubTot<0) auxSubTot = auxSubTot *-1;
								subtotalAbsoluto = auxSubTot + subtotalAbsoluto;
							}
						}
						Long sub = subtotal.longValue();
						aux.add(sub.toString());
						
						if(subtotalAbsoluto>0) {
							lista.add(aux);
						}
					}
				}
		} catch (SQLException e) {
    			e.printStackTrace();
		}
		return (lista);
	}
	
	

	
	public static File excedentesExcel(Connection con, String db, List<List<String>> datos, Map<String,String> mapDiccionario, BodegaEmpresa bodega) {
		
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
			cell.setCellValue("REPORTE DE EXCEDENTES POR "+mapDiccionario.get("BODEGA")+"/PROYECTO");
		
            row = hoja1.createRow(2);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(mapDiccionario.get("BODEGA")+"/PROYECTO: "+bodega.getNombre().toUpperCase());
			
			row = hoja1.createRow(3);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("EMPRESA: "+mapDiccionario.get("nEmpresa"));
			
			row = hoja1.createRow(4);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FECHA: "+Fechas.hoy().getFechaStrDDMMAA());
			
			
			// encabezado de la tabla
			
			hoja1.setColumnWidth(1, 7*1000);
			hoja1.setColumnWidth(2, 3*1000);
			hoja1.setColumnWidth(3, 5*1000);
			hoja1.setColumnWidth(4, 10*1000);
			for(int i=5;i<datos.size()+5;i++){
				hoja1.setColumnWidth(i, 4*1000);
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
	        anchor.setCol1(6);
	        anchor.setRow1(1);
			Picture img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);
			
			//DETALLE DE LA TABLA
			int posRow = 8;
			for(int i=0;i<datos.size();i++){
				row = hoja1.createRow(posRow);
				int posCell = 0;
				Double aux = (double)0;
				for(int j=0;j<datos.get(i).size();j++){
					String dato = datos.get(i).get(j);
					if(i<3){
						posCell++; 
			            cell = row.createCell(posCell);
			            cell.setCellStyle(encabezado);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(dato);
					}else{
						if(j<4||j==6){
							posCell++; 
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(dato);
						}else {
							if(dato.equals("")||dato.equals(" ")) {
								dato = "0";
							}
							posCell++; 
				            cell = row.createCell(posCell);
				            cell.setCellStyle(detalle);
				            aux = Double.parseDouble(dato.replaceAll(",", ""));
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue(aux);
						}
					}
				}
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
	
	
	
	public static List<List<String>> reportExcedentesEquiposSelectivo(Connection con, String db, String permisoPorBodega, String esPorSucursal, String id_sucursal) {
		List<List<String>> lista = new ArrayList<List<String>>();
		
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and bodegaEmpresa.id_sucursal = " + id_sucursal;
		}
		
		try {
			PreparedStatement smt5 = con
					.prepareStatement(" select "
							+ " equipo.id, "
							+ " equipo.codigo, "
							+ " equipo.nombre, "
							+ " sum(movimiento.exceso) "
							+ " from `"+db+"`.movimiento "
							+ " left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa "
							+ " left join `"+db+"`.equipo on equipo.id = movimiento.id_equipo "
							+ " where bodegaEmpresa.vigente = 1 and bodegaEmpresa.esInterna = 2 " + permisoPorBodega + condSucursal 
							+ " group by movimiento.id_equipo "
							+ " having sum(movimiento.exceso) >0 "
							+ " order by equipo.nombre;");
			
			ResultSet rs5 = smt5.executeQuery();
			while (rs5.next()) {
				List<String> aux = new ArrayList<String>();
				aux.add(rs5.getString(1));  // 0 equipo.id
				aux.add(rs5.getString(2));  // 1 codigo
				aux.add(rs5.getString(3));  // 2 nombre
				aux.add(rs5.getString(4));  // 4 movimiento.exceso
				lista.add(aux);
			}
			rs5.close();
			smt5.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<List<String>> reportExcedentesEquiposDetalle(Connection con, String db, String permisoPorBodega, String esPorSucursal, String id_sucursal, String id_equipo) {
		List<List<String>> lista = new ArrayList<List<String>>();
		
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and bodegaEmpresa.id_sucursal = " + id_sucursal;
		}
		
		try {
			PreparedStatement smt5 = con
					.prepareStatement(" select "
							+ " sucursal.nombre, "
							+ " bodegaEmpresa.nombre, "
							+ " sum(movimiento.exceso) "
							+ " from `"+db+"`.movimiento "
							+ " left join `"+db+"`.bodegaEmpresa on bodegaEmpresa.id = movimiento.id_bodegaEmpresa "
							+ " left join `"+db+"`.equipo on equipo.id = movimiento.id_equipo "
							+ " left join `"+db+"`.sucursal on sucursal.id = bodegaEmpresa.id_sucursal "
							+ " where bodegaEmpresa.vigente = 1 and bodegaEmpresa.esInterna = 2  and equipo.id = ? " + permisoPorBodega + condSucursal
							+ " group by movimiento.id_equipo, movimiento.id_bodegaEmpresa "
							+ " having sum(movimiento.exceso) >0 "
							+ " order by bodegaEmpresa.esInterna,bodegaEmpresa.nombre;");
			smt5.setString(1, id_equipo);
			ResultSet rs5 = smt5.executeQuery();
			while (rs5.next()) {
				List<String> aux = new ArrayList<String>();
				aux.add(rs5.getString(1));  // 0 sucursal.nombre
				aux.add(rs5.getString(2));  // 1 bodegaEmpresa.nombre
				aux.add(rs5.getString(3));  // 2 movimiento.exceso
				lista.add(aux);
			}
			rs5.close();
			smt5.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (lista);
	}
	
	public static File excedentesExcelEquipo(String db, List<List<String>> datos, Map<String,String> mapDiccionario, Equipo equipo) {
		
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
			cell.setCellValue("REPORTE DE EXCEDENTES POR EQUIPO");
		
            row = hoja1.createRow(2);
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("EQUIPO: "+equipo.getCodigo().toUpperCase()+" - "+equipo.getNombre().toUpperCase());
			
			row = hoja1.createRow(3);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("EMPRESA: "+mapDiccionario.get("nEmpresa"));
			
			row = hoja1.createRow(4);
            cell = row.createCell(1);
            cell.setCellStyle(subtitulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FECHA: "+Fechas.hoy().getFechaStrDDMMAA());
			
			
			// encabezado de la tabla
			
			hoja1.setColumnWidth(1, 7*1000);
			hoja1.setColumnWidth(2, 12*1000);
			hoja1.setColumnWidth(3, 5*1000);
		
			//INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
			InputStream x = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
            byte[] bytes = IOUtils.toByteArray(x);
            x.close();
            int pngIndex = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			Drawing draw = hoja1.createDrawingPatriarch();
			CreationHelper helper = libro.getCreationHelper();
			ClientAnchor anchor = helper.createClientAnchor();
	        //set top-left corner for the image
	        anchor.setCol1(3);
	        anchor.setRow1(1);
			Picture img = draw.createPicture(anchor, pngIndex);
			img.resize(0.4);
			hoja1.createFreezePane(0, 0, 0,0);
			
			//DETALLE DE LA TABLA
			int posRow = 8;
			row = hoja1.createRow(posRow);
			int posCell = 0;
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("SUCURSAL");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(mapDiccionario.get("BODEGA")+"/PROYECTO");
			
			posCell++; 
            cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("EXCEDENTES");
			
			posRow++;
			
			for(int i=0;i<datos.size();i++){
				row = hoja1.createRow(posRow);
				posCell = 0;
				Double aux = (double)0;
				for(int j=0; j<datos.get(i).size(); j++){
					String dato = datos.get(i).get(j);
					if(j<2){
						posCell++; 
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(dato);
					}else{
						posCell++; 
			            cell = row.createCell(posCell);
			            cell.setCellStyle(detalle);
			            aux = Double.parseDouble(dato.replaceAll(",", ""));
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(aux);
					}
				}
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
	
}
	
	
	
