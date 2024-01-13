package models.xlsx;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.TempFile;

import models.tables.BodegaEmpresa;
import models.tables.Cliente;
import models.tables.EstadoEquipo;
import models.tables.Guia;
import models.tables.Proyecto;
import models.tables.Transportista;
import models.utilities.Archivos;
import models.utilities.Fechas;




public class XLSX_GuiaEntrada {
	static DecimalFormat myformatint = new DecimalFormat("#,##0");
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble4 = new DecimalFormat("#,##0.0000");
	static DecimalFormat myformatdouble6 = new DecimalFormat("#,##0.000000");
	
	
	
	public static File generaGuiaEntradaXLS_TECA(Connection con, String db, Guia guia, List<List<String>> detalleGuia, Transportista transporte) {
		
		if(detalleGuia.size() > 20) return null;
		
		BodegaEmpresa bodegaOrigen = BodegaEmpresa.findXIdBodega(con, db, guia.getId_bodegaOrigen());
		Cliente clienteOrigen = Cliente.find(con, db, bodegaOrigen.getId_cliente());
		Proyecto proyectoOrigen = Proyecto.find(con, db, bodegaOrigen.getId_proyecto());
		
		Fechas fechaGuia = Fechas.obtenerFechaDesdeStrAAMMDD(Fechas.AAMMDD(guia.getFecha()));
		
		
		String MES[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
		Calendar c1 = fechaGuia.getFechaCal();
		String anio = "" + (c1.get(Calendar.YEAR)-2000);
		String mes = MES[c1.get(Calendar.MONTH)];
		String dia = "" + c1.get(Calendar.DATE);
		
		File tmp = TempFile.createTempFile("tmp","null");
		
		try {
			String path = db + "/formatos/guiaDeRecepcionExcel.xlsx";
			InputStream formato = Archivos.leerArchivo(path);
            Workbook libro = WorkbookFactory.create(formato);
            formato.close();
            
            Sheet hoja1 = libro.getSheetAt(0);
            Row row = null;
            Cell cell = null;
            
            
            row = hoja1.getRow(9);
            cell = row.getCell(7);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(dia);
			
			row = hoja1.getRow(9);
            cell = row.getCell(8);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(mes.toUpperCase());
			
			
			row = hoja1.getRow(9);
            cell = row.getCell(11);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(anio);
			
			String cliente = "";
			if(clienteOrigen != null) cliente = clienteOrigen.getNombre();
			row = hoja1.getRow(12);
            cell = row.getCell(3);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(cliente);
			
			row = hoja1.getRow(13);
            cell = row.getCell(3);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(guia.getNumero());
			
			
			String proyecto = "";
			if(proyectoOrigen != null) proyecto = proyectoOrigen.getNombre();
			row = hoja1.getRow(13);
            cell = row.getCell(9);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(proyecto);
			
			int inicio = 17;
			
			for(int i=inicio;i<40;i++) {
				
				row = hoja1.getRow(i);
				cell = row.getCell(1);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("");
				cell = row.getCell(2);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("");
				cell = row.getCell(4);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("");
				cell = row.getCell(5);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("");
				
				cell = row.getCell(9);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("");
				cell = row.getCell(10);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("");
				cell = row.getCell(11);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("");
			}
			
			for(int i=0;i<detalleGuia.size();i++) {
				
				row = hoja1.getRow(i+inicio);
				cell = row.getCell(1);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(i+1);
				
				cell = row.getCell(2);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(detalleGuia.get(i).get(5).trim());
				
				String cantidad = detalleGuia.get(i).get(8).replaceAll(",", "").trim();
   				Double cant = Double.parseDouble(cantidad);
				cell = row.getCell(4);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(myformatdouble.format(cant));
				
				cell = row.getCell(5);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(detalleGuia.get(i).get(6).trim());
				
				String idMovimiento = detalleGuia.get(i).get(0).trim();
				Map<String,Double> mapEstEquipo = EstadoEquipo.mapEstadoEquipos(con, db, idMovimiento);
				Double lim = (double)0;
		        Double rep = (double)0;
		        Double irr = (double)0;
		        Double est4 = (double)0;
		         
		        try {lim = mapEstEquipo.get(""+detalleGuia.get(i).get(2)+"3");}catch(Exception e) {}
		        try {rep = mapEstEquipo.get(""+detalleGuia.get(i).get(2)+"1");}catch(Exception e) {}
		        try {irr = mapEstEquipo.get(""+detalleGuia.get(i).get(2)+"2");}catch(Exception e) {}
		        try {est4 = mapEstEquipo.get(""+detalleGuia.get(i).get(2)+"4");}catch(Exception e) {}
		         
		        if(lim==null) lim = (double)0;
		        if(rep==null) rep = (double)0;
		        if(irr==null) irr = (double)0;
		        if(est4==null) est4 = (double)0;
				
				
				cell = row.getCell(9);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(myformatint.format(lim));
				
				cell = row.getCell(10);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(myformatint.format(rep));
				
				cell = row.getCell(11);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(myformatint.format(irr));
				
				
			}
			
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
	
	
	
