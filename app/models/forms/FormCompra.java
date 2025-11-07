package models.forms;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import controllers.HomeController;
import models.tables.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import models.utilities.Archivos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	static DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0",symbols);
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00",symbols);

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	public static boolean create (Connection con, String db, FormCompra form, Http.MultipartFormData.FilePart<TemporaryFile> docAdjunto, String numOcIConstruye, String id_userCrea) {
		boolean flag = false;
		try {
			Factura factura = new Factura(form.id_proveedor, form.numeroFactura, form.fechaFactura, form.observaciones);
			Long id_factura = Factura.create(con, db, factura, numOcIConstruye, id_userCrea);
			if(id_factura > (long)0) {
				String detalle = "";
				for(int i=0; form.id_equipo!=null && i<form.id_equipo.size(); i++) {
					detalle += "('"+id_factura+"','"+form.id_equipo.get(i)+"','"+form.cantidad.get(i).replaceAll(",", "")+"','"+form.id_monedaCompra.get(i)+"','"+
							form.puCompra.get(i).replaceAll(",", "")+"','"+form.id_bodegaDestino.get(i)+"'),";
				}

				if(detalle.length()>10) {
					detalle = detalle.substring(0,detalle.length()-1);
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
		} catch (Exception e) {
			String className = FormCompra.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return(flag);
	}
	
	public static boolean update (Connection con, String db, FormCompra form, Http.MultipartFormData.FilePart<TemporaryFile> docAdjunto) {
		boolean flag = false;
		try {
			Long id_factura = form.id_factura;
			if(id_factura > (long)0) {
				String detalle = "";
				for(int i=0; form.id_equipo!=null && i<form.id_equipo.size(); i++) {
					detalle += "('"+id_factura+"','"+form.id_equipo.get(i)+"','"+form.cantidad.get(i).replaceAll(",", "")+"','"+form.id_monedaCompra.get(i)+"','"+
							form.puCompra.get(i).replaceAll(",", "")+"','"+form.id_bodegaDestino.get(i)+"'),";
				}
				if(detalle.length()>10) {
					detalle = detalle.substring(0,detalle.length()-1);
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
		} catch (Exception e) {
			String className = FormCompra.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
		}
		return(flag);
	}
	
	public static Long validaProveedor (Connection con, String db, File file) {
		try {
            Workbook libro = WorkbookFactory.create(file);
            Sheet hoja1 = libro.getSheetAt(0);
            Row row = null;
            Cell cell = null;
            row = hoja1.getRow(1);
            if(row != null) {
            	cell = row.getCell(2);
            }
            if(row!=null && cell !=null ) {
            	Proveedor proveedor = Proveedor.findPorNickName(con, db, cell.getStringCellValue());
            	if(proveedor == null) {
            		return(null);
            	}else {
            		return(proveedor.getId());
            	}
            }else {
            	return(null);
            }
		} catch (InvalidFormatException | IOException e1) {
			String className = FormCompra.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e1);
			return(null);
		}
	}
	
	public static List<String> compraValidarPlantillaExcel (Connection con, String db, File file) {
		List<String> mensaje = new ArrayList<String>();
        try {
			DecimalFormat df = new DecimalFormat("#",symbols);
			df.setMaximumFractionDigits(8);
			mensaje.add("EQUIPOS QUE NO EXISTEN EN MADA: <br>");
            Workbook libro = WorkbookFactory.create(file);
            Sheet hoja1 = libro.getSheetAt(0);
            Row row = null;
            Cell cell = null;
            boolean archivoNoCorresponde = false;
            row = hoja1.getRow(3);
            if(row==null || archivoNoCorresponde) {
            	archivoNoCorresponde = true;
            }else {
            	for(int i=1; i<11; i++) {
            		cell = row.getCell(i);
                	if(cell==null) {
                		archivoNoCorresponde = true;
                	}
            	}
            	cell = row.getCell(2);
            	if(!cell.getStringCellValue().equals("CODIGO")) {
            		archivoNoCorresponde = true;
            	}
            }
            if(archivoNoCorresponde) {
            	mensaje.set(0,"ARCHIVO NO CORRESPONDE A LA PLANTILLA");
            	return (mensaje);
            }
            Map<String,Equipo> mapEquipos = Equipo.mapAllAllPorCodigo(con, db);
            Map<String,Grupo> mapGrupos = Grupo.mapAllPorNombre(con, db);
            Map<String,Unidad> mapUnidades = Unidad.mapPorNombre(con, db);
            Map<String,Moneda> mapMonedas = Moneda.mapNickMonedas(con, db);
            boolean flag = true;
            int fila = 4;
            int x = 4;
            while (row!=null && cell !=null ) {
            	fila++;
            	row = hoja1.getRow(x++);
            	if(row!=null) {
            		//valida grupo
            		cell = row.getCell(1);
            		String nomGrupo = "";
        			try {
        				nomGrupo = cell.getStringCellValue().trim();
        			}catch(Exception e) {
        				mensaje.set(0,"ERR1: EL GRUPO: "+nomGrupo+" NO EXISTE EN MADA");
        			}
            		if(cell!=null) {
            			if( ! nomGrupo.equals("")) {
            				Grupo grupo = mapGrupos.get(nomGrupo.toUpperCase());
                			if(grupo == null) {
                				mensaje.set(0,"ERR1: EL GRUPO: "+nomGrupo+" NO EXISTE EN MADA");
                    			return (mensaje);
                			}
            			}
            		}
            		//valida unidad
            		cell = row.getCell(6);
            		if(cell!=null) {
            			String nomUnidad = "";
            			try {
            				nomUnidad = cell.getStringCellValue().trim();
            			}catch(Exception e) {
            				mensaje.set(0,"ERR2: LA UNIDAD: "+nomUnidad+" NO EXISTE EN MADA");
            			}
            			if( ! nomUnidad.equals("")) {
            				Unidad unidad = mapUnidades.get(nomUnidad.toUpperCase());
                			if(unidad == null) {
                				mensaje.set(0,"ERR2: LA UNIDAD: "+nomUnidad+" NO EXISTE EN MADA");
                    			return (mensaje);
                			}
            			}
            		}
            		//valida moneda
            		cell = row.getCell(8);
            		if(cell!=null) {
            			String nickMoneda = "";
            			try {
            				nickMoneda = cell.getStringCellValue().trim();
            			}catch(Exception e) {
            				mensaje.set(0,"ERR3: LA MONEDA: "+nickMoneda+" NO EXISTE EN MADA");
            			}
            			if( ! nickMoneda.equals("")) {
            				Moneda moneda = mapMonedas.get(nickMoneda.toUpperCase());
                			if(moneda == null) {
                				mensaje.set(0,"ERR3: LA MONEDA: "+nickMoneda+" NO EXISTE EN MADA");
                    			return (mensaje);
                			}
            			}
            		}
            		//valida equipo
            		cell = row.getCell(2);
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
                			String dato = "codigo";
                			cell = row.getCell(2);
                			try {
                        		dato = cell.getStringCellValue().trim();
                        	}catch(Exception e){
                        		Double aux = cell.getNumericCellValue();
                        		Long aux2 = aux.longValue();
                        		dato = df.format(aux2);
                        	}
                			if(dato!=null) {
                				dato = dato.toUpperCase().trim();
                			}
                			Equipo equipo = mapEquipos.get(dato);
                			if(equipo == null) {
                				mensaje.add("En fila "+fila+": Codigo ["+dato+"] no existe en mada.<br>");
                        		flag = false;
                			}
                		}
                	}
                	cell = row.getCell(2);
            	}
            }
            if(flag) {
            	mensaje.set(0,"true");
            }
        } catch (Exception e) {
			String className = FormCompra.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, db, e);
			mensaje.set(0,"ARCHIVO NO CORRESPONDE A LA PLANTILLA");
        	return (mensaje);
        }
		return(mensaje);
	}
	
	public static List<List<String>> llenaListaDesdeExcel (File file) {
		List<List<String>> lista = new ArrayList<List<String>>();
		try {
			DecimalFormat df = new DecimalFormat("#",symbols);
			df.setMaximumFractionDigits(8);
            Workbook libro = WorkbookFactory.create(file);
            Sheet hoja1 = libro.getSheetAt(0);
            Row row = null;
            Cell cell = null;
            row = hoja1.getRow(1);
            if(row != null) {
            	cell = row.getCell(2);
            }
            int x = 4;
            while (row!=null && cell !=null ) {
            	row = hoja1.getRow(x++);
            	if(row!=null) {
            		cell = row.getCell(2);
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
                			for(int i=1; i<11; i++) {
                    			String dato = "";
                    			cell = row.getCell(i);
                                if(cell!=null) {
                                	try {
                                		Double aux = cell.getNumericCellValue();
                                		dato = df.format(aux);
                                	}catch(Exception e){
                                		dato = cell.getStringCellValue().trim();
                                		dato = dato.replaceAll("'", "\"");
                                	}
                                }
                                auxList.add(dato);
                            }
                			auxList.set(0, auxList.get(0).toUpperCase());
                    		lista.add(auxList);
                		}
                	}
                	cell = row.getCell(2);
            	}
            }
		} catch (InvalidFormatException | IOException e1) {
			String className = FormCompra.class.getSimpleName();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.error("ERROR. [CLASS: {}. METHOD: {}. DB: {}.]", className, methodName, "", e1);
		}
		return(lista);
	}

	
	
	
}














