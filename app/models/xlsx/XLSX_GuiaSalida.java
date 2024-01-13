package models.xlsx;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.TempFile;

import models.tables.Atributo;
import models.tables.BodegaEmpresa;
import models.tables.Cliente;
import models.tables.Guia;
import models.tables.Proyecto;
import models.tables.Transportista;
import models.utilities.Archivos;




public class XLSX_GuiaSalida {
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble4 = new DecimalFormat("#,##0.0000");
	static DecimalFormat myformatdouble6 = new DecimalFormat("#,##0.000000");
	
	
	
	public static File generaGuiaXLS_TECA(Connection con, String db, Guia guia, List<List<String>> detalleGuia, Transportista transporte) {
		
		if(detalleGuia.size() > 20) return null;
		
		BodegaEmpresa bodegaOrigen = BodegaEmpresa.findXIdBodega(con, db, guia.getId_bodegaOrigen());
		Cliente clienteOrigen = Cliente.find(con, db, bodegaOrigen.getId_cliente());
		
		BodegaEmpresa bodegaDestino = BodegaEmpresa.findXIdBodega(con, db, guia.getId_bodegaDestino());
		Cliente clienteDestino = Cliente.find(con, db, bodegaDestino.getId_cliente());
		Proyecto proyectoDestino = Proyecto.find(con, db, bodegaDestino.getId_proyecto());
		
		Map<Long,Double> mapPesos = Atributo.mapAtributoPESO(con, db);
		
		File tmp = TempFile.createTempFile("tmp","null");
		
		try {
			String path = db + "/formatos/guiaDeRemisionExcel.xlsx";
			InputStream formato = Archivos.leerArchivo(path);
            Workbook libro = WorkbookFactory.create(formato);
            formato.close();
            
            Sheet hoja1 = libro.getSheetAt(0);
            Row row = null;
            Cell cell = null;
            row = hoja1.getRow(7);
            cell = row.getCell(3);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(guia.getFecha());
			
			row = hoja1.getRow(7);
            cell = row.getCell(7);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(guia.getFecha());
			
			String dirOrigen = "";
			if(clienteOrigen != null) dirOrigen = clienteOrigen.direccion;
			row = hoja1.getRow(10);
            cell = row.getCell(0);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(dirOrigen);
			
			String dirDestino = "";
			if(proyectoDestino != null) dirDestino = proyectoDestino.direccion;
			row = hoja1.getRow(10);
            cell = row.getCell(6);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(dirDestino);
			
			String nombre = "", rut = "";
			if(clienteDestino != null) nombre = clienteDestino.nombre;
			if(clienteDestino != null) rut = clienteDestino.rut;
			
			row = hoja1.getRow(14);
            cell = row.getCell(0);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(nombre);
			
			row = hoja1.getRow(16);
            cell = row.getCell(0);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(rut);
			
			row = hoja1.getRow(13);
            cell = row.getCell(8);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(transporte.conductor);
			
			row = hoja1.getRow(14);
            cell = row.getCell(8);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(transporte.empresa);
			
			row = hoja1.getRow(15);
            cell = row.getCell(8);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(transporte.patente);
			
			row = hoja1.getRow(16);
            cell = row.getCell(8);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(transporte.licencia);
			
			for(int i=20;i<40;i++) {
				row = hoja1.getRow(i);
				cell = row.getCell(0);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("");
				cell = row.getCell(1);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("");
				cell = row.getCell(4);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("");
				cell = row.getCell(8);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("");
				cell = row.getCell(9);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("");
			}
			
			for(int i=0;i<detalleGuia.size();i++) {
				row = hoja1.getRow(i+20);
				cell = row.getCell(0);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(i+1);
				
				cell = row.getCell(1);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(detalleGuia.get(i).get(5).trim());
				
				cell = row.getCell(4);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(detalleGuia.get(i).get(6).trim());
				
				String cantidad = detalleGuia.get(i).get(8).replaceAll(",", "").trim();
   				Double cant = Double.parseDouble(cantidad);

				cell = row.getCell(8);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(myformatdouble.format(cant));
				
				Long id_equipo = Long.parseLong(detalleGuia.get(i).get(2).trim());
				Double peso = (double)0;
				if(mapPesos!=null) { peso = mapPesos.get(id_equipo); }
				if(peso==null) peso = (double)0;
				Double totPeso = peso * cant;
				
				cell = row.getCell(9);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(myformatdouble.format(totPeso));
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
	
	
	
