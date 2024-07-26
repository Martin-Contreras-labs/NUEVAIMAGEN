package controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.TempFile;

import models.tables.Equipo;
import models.tables.Precio;
import models.utilities.Archivos;
import models.utilities.Fechas;
import play.data.FormFactory;
import play.db.Database;
import play.mvc.Controller;

public class Coti8columnas extends Controller {
		public static Database db = HomeController.dbWrite;
		public static FormFactory formFactory = HomeController.formFactory;
		public static String msgError = HomeController.msgError;
		public static String msgErrorFormulario = HomeController.msgErrorFormulario;
		public static String msgSinPermiso = HomeController.msgSinPermiso;
		
		static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
		
		public static List<String> validaPlantilla(Connection con, String db, File file) {

			Map<String,Equipo> mapEquipos = Equipo.mapAllVigentesPorCodigo(con, db);
			List<String> mensaje = new ArrayList<String>();
			DecimalFormat df = new DecimalFormat("#");
		    df.setMaximumFractionDigits(8);
		    
	        mensaje.add("ERROR cod:001");
			
			try {
	            Workbook libro = WorkbookFactory.create(file);
	            Sheet hoja1 = libro.getSheetAt(0);
	            Row row = null;
	            Cell cell = null;
	            boolean archivoNoCorresponde = false;
	            
	            //valido titulos y archivo
	            if(hoja1 != null) {
	            	row = hoja1.getRow(13);
	            	if(row != null) {
	            		for(int i=1; i<7; i++) {
		            		cell = row.getCell(i);
		                	if( ! (cell != null &&  cell.getStringCellValue().equals("MADA_"+i)) ) {
		                		archivoNoCorresponde = true;
		                	}
		            	}
	            	}else {
	            		archivoNoCorresponde = true;
	            	}
	            }else {
	            	archivoNoCorresponde = true;
	            }
	            if(archivoNoCorresponde) {
	            	mensaje.set(0,"ARCHIVO NO CORRESPONDE A LA PLANTILLA");
	            	return (mensaje);
	            }
	            // fin
	            
	            
	            // valido datos
	            boolean flag = true;
	            int fila = 14;
	            row = hoja1.getRow(fila);
	            if(row != null) {
	            	cell = row.getCell(1);
	            	if(cell != null) {
	            		Long nroFilasExcel = (long) 0;
	            		Map<Long,Long> mapValidaRepetido = new HashMap<Long,Long>();
	            		while (row != null && cell != null ) {
	            			row = hoja1.getRow(fila);
	            			if(row != null) {
	            				
	            				//valido codigos
	            				cell = row.getCell(1);
	            				if(cell != null) {
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
	    	                			cell = row.getCell(1);
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
	    	                				mensaje.add("error en fila "+(fila+1)+": Codigo ["+dato+"] no existe en mada o el equipo esta no vigente.");
	    	                        		flag = false;
	    	                			}else{
	    	                				mapValidaRepetido.put(equipo.getId(), equipo.getId());
	    	                			}
	    	                		}
	            				}
	            				
	            				//valido numeros
	            				for(int i=2; i<7; i++) {
	            					cell = row.getCell(i);
	            					String celda = "";
	            					switch(i) {
	            					  case 2: celda  = "C" + (fila+1); break;
	            					  case 3: celda  = "D" + (fila+1); break;
	            					  case 4: celda  = "E" + (fila+1); break;
	            					  case 5: celda  = "F" + (fila+1); break;
	            					  case 6: celda  = "G" + (fila+1); break;
	            					  case 7: celda  = "H" + (fila+1); break;
	            					  default:
	            					}
	            					
	            					if(cell != null) {
	            						try {
	            							Double aux = cell.getNumericCellValue();
	            							if(aux < (double)0) {
	            								mensaje.add("error en fila "+(fila+1)+": El dato en la celda "+ celda +" no puede ser menor que cero.");
	            								flag = false;
	            							}else {
	            								if(i == 3 && aux > 1) {
	            									mensaje.add("error en fila "+(fila+1)+": El dato en la celda "+ celda +" solo puede ser cero o uno.");
		            								flag = false;
	            								}
	            							}
		    	                    	}catch(Exception e){
		    	                    		mensaje.add("error en fila "+(fila+1)+": El dato en la celda "+ celda +" no es numero.");
	    	                        		flag = false;
		    	                    	}
	            						
	            					}
	            				}
	            				nroFilasExcel++;
	            				fila++;
	            			}
	            		}
	            		if(nroFilasExcel - mapValidaRepetido.size() != (long)0) {
	            			mensaje.add("Existen codigos duplicados en el archivo.");
                    		flag = false;
	            		}
	            	}
	            }
	            if(flag) {
	            	mensaje.set(0,"true");
	            }
	        } catch (Exception e) {
				mensaje.set(0,"ARCHIVO NO CORRESPONDE A LA PLANTILLA");
	        	return (mensaje);
	        }
			return(mensaje);
		}

	    public static File downloadPlantilla(String db, Map<String,String> mapDiccionario, List<Equipo> listEquipo, Map<Long,Precio> mapPrecio,  
	    		Map<Long,Double> mapTasas, Map<Long,Double> equivTiempo) {
			
			File tmp = TempFile.createTempFile("tmp","null");
			
			try {
				String path = "formatos/alzatecPlantillaCoti.xlsx";
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
	            
	            CellStyle pie = libro.createCellStyle();
	            pie.setBorderBottom(CellStyle.BORDER_THIN);
	            pie.setBorderTop(CellStyle.BORDER_THIN);
	            pie.setBorderRight(CellStyle.BORDER_THIN);
	            pie.setBorderLeft(CellStyle.BORDER_THIN);
	            pie.setFillPattern(CellStyle.SOLID_FOREGROUND);
	            pie.setFillForegroundColor((short)19);
	            pie.setAlignment(CellStyle.ALIGN_RIGHT);
	            
	            CreationHelper creationHelper = libro.getCreationHelper();
	            CellStyle hora = libro.createCellStyle();
	            hora.setDataFormat(creationHelper.createDataFormat().getFormat("hh:mm"));
	            hora.setBorderBottom(CellStyle.BORDER_THIN);
	            hora.setBorderTop(CellStyle.BORDER_THIN);
	            hora.setBorderRight(CellStyle.BORDER_THIN);
	            hora.setBorderLeft(CellStyle.BORDER_THIN);
	            
	            CellStyle fecha = libro.createCellStyle();
	            fecha.setDataFormat(creationHelper.createDataFormat().getFormat("dd/MM/yyyy"));
	            fecha.setBorderBottom(CellStyle.BORDER_THIN);
	            fecha.setBorderTop(CellStyle.BORDER_THIN);
	            fecha.setBorderRight(CellStyle.BORDER_THIN);
	            fecha.setBorderLeft(CellStyle.BORDER_THIN);
	            
	           
	            
	            
	            
	            
	            //titulos del archivo
	            
	            libro.setSheetName(0, "report");
	            Sheet hoja1 = libro.getSheetAt(0);
	            
	            
	            
	            
	            Row row = null;
	            Cell cell = null;
	         
	            
	            row = hoja1.createRow(1);
	            cell = row.createCell(8);
	            cell.setCellStyle(titulo);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("PLANTILLA ALZATEC IMPORTAR COTIZACIONES");
				
				row = hoja1.createRow(2);
	            cell = row.createCell(8);
	            cell.setCellStyle(subtitulo);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("EMPRESA: "+mapDiccionario.get("nEmpresa"));
				
				row = hoja1.createRow(3);
	            cell = row.createCell(8);
	            cell.setCellStyle(subtitulo);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("FECHA: "+Fechas.hoy().getFechaStrDDMMAA());
				
				
				

//				//INSERTA LOGO DESPUES DE ANCHOS DE COLUMNAS
//				InputStream x = Archivos.leerArchivo(db+"/"+mapDiccionario.get("logoEmpresa"));
//	            byte[] bytes = IOUtils.toByteArray(x);
//	            x.close();
//	            int pngIndex = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
//				Drawing draw = hoja1.createDrawingPatriarch();
//				CreationHelper helper = libro.getCreationHelper();
//				ClientAnchor anchor = helper.createClientAnchor();
//		        //set top-left corner for the image
//		        anchor.setCol1(9);
//		        anchor.setRow1(1);
//				Picture img = draw.createPicture(anchor, pngIndex);
//				img.resize(0.4);
//				hoja1.createFreezePane(0, 0, 0,0);
				
				
				
				
				//Conenido archivo
				
				int posRow = 14;
				FormulaEvaluator evaluator = libro.getCreationHelper().createFormulaEvaluator();
				 
				 
				for(int i = 0; i < listEquipo.size(); i++) {	 
					
		            row = hoja1.createRow(posRow+i);
		            int linea = posRow+i+1;
		            
		            Double kg = listEquipo.get(i).getKg();
					if(kg == null) {
						kg = (double)0;
					}
		            Precio precio = mapPrecio.get(listEquipo.get(i).getId());
					String moneda = "";
					Double tasa = (double)1;
					Double reposicion =  (double)0;
					Double arriendoMes =  (double)0;
					if(precio != null) {
						moneda = precio.getNombreMoneda();
						tasa = mapTasas.get(precio.getId_moneda());
						if(tasa == null) {
							tasa = (double)1;
						}
						reposicion = Double.parseDouble(precio.getPrecioReposicion().replaceAll(",", ""));
						
						arriendoMes = Double.parseDouble(precio.getPrecioArriendo().replaceAll(",", ""));
						Double factor = equivTiempo.get(precio.getId_unidadTiempo());
						if(factor == null) {
							factor = (double)1;
						}
						Double precioArriendo_dia = arriendoMes / factor;
						arriendoMes = precioArriendo_dia * 30;
					}
					
		          
		            // CODIGO
		            cell = row.createCell(8);
		            cell.setCellStyle(detalle);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(listEquipo.get(i).getCodigo());
					
					// DESCRIPCION EQUIPOS
					cell = row.createCell(9);
		            cell.setCellStyle(detalle);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(listEquipo.get(i).getNombre());
					
					// PESO en KG
					cell = row.createCell(10);
		            cell.setCellStyle(detalle);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue(kg);
					
					// MONEDA
					
		            cell = row.createCell(11);
		            cell.setCellStyle(detalle);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(moneda);
					
					// TASA CAMBIO
					cell = row.createCell(12);
		            cell.setCellStyle(detalle);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue(tasa);
					
					// VALOR REPOSICION
					cell = row.createCell(13);
		            cell.setCellStyle(detalle);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue(reposicion);
					
					// VALOR ARRIENDO MES/UNIT
					cell = row.createCell(14);
		            cell.setCellStyle(detalle);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue(arriendoMes);
					
					// AJUSTE P.ARR MES/UNIT
					cell = row.createCell(15);
		            cell.setCellStyle(detalle);
		            cell.setCellFormula("O"+linea+"*(1-P$11)");
		            evaluator.evaluateFormulaCell(cell);
		            
		            // unidad
		            cell = row.createCell(16);
		            cell.setCellStyle(detalle);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(listEquipo.get(i).getUnidad());
					
					//cantidades
					cell = row.createCell(17);
		            cell.setCellStyle(detalle);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue((double)0);
					
					cell = row.createCell(18);
		            cell.setCellStyle(detalle);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue((double)0);
					
					cell = row.createCell(19);
		            cell.setCellStyle(detalle);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue((double)0);
					
					cell = row.createCell(20);
		            cell.setCellStyle(detalle);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue((double)0);
					
					cell = row.createCell(21);
		            cell.setCellStyle(detalle);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue((double)0);
					
					cell = row.createCell(22);
		            cell.setCellStyle(detalle);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue((double)0);
					
					cell = row.createCell(23);
		            cell.setCellStyle(detalle);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue((double)0);
					
					cell = row.createCell(24);
		            cell.setCellStyle(detalle);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue((double)0);
		            // fin cantidades
		            
					// SUMA cantidades
					cell = row.createCell(25);
		            cell.setCellStyle(detalle);
		            cell.setCellFormula("SUM(R"+linea+":Y"+linea+")");
		            evaluator.evaluateFormulaCell(cell);
		            
		            // MAX cantidades
					cell = row.createCell(26);
		            cell.setCellStyle(detalle);
		            cell.setCellFormula("MAX(R"+linea+":Y"+linea+")");
		            evaluator.evaluateFormulaCell(cell);
		            
		            // select  cantidades
					cell = row.createCell(27);
		            cell.setCellStyle(detalle);
		            cell.setCellFormula("SUMPRODUCT(R$9:Y$9,R"+linea+":Y"+linea+")");
		            evaluator.evaluateFormulaCell(cell);
		            
		            // PERMANENCIA MESES
					cell = row.createCell(28);
		            cell.setCellStyle(detalle);
		            cell.setCellFormula("SUMPRODUCT(Z$9:AB$9,Z$12:AB$12)");
		            evaluator.evaluateFormulaCell(cell);
					
		            // ES VENTA?
		            cell = row.createCell(30);
		            cell.setCellStyle(detalle);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue((double)0);
					
					//calculo totales
					cell = row.createCell(32);
		            cell.setCellStyle(detalle);
		            cell.setCellFormula("IF($AE"+linea+"=1,$N"+linea+"*R"+linea+"*$M"+linea+",R"+linea+"*$P"+linea+"*$M"+linea+"*$AC"+linea+")");
		            evaluator.evaluateFormulaCell(cell);
		            
		            cell = row.createCell(33);
		            cell.setCellStyle(detalle);
		            cell.setCellFormula("IF($AE"+linea+"=1,$N"+linea+"*S"+linea+"*$M"+linea+",S"+linea+"*$P"+linea+"*$M"+linea+"*$AC"+linea+")");
		            evaluator.evaluateFormulaCell(cell);
		            
		            cell = row.createCell(34);
		            cell.setCellStyle(detalle);
		            cell.setCellFormula("IF($AE"+linea+"=1,$N"+linea+"*T"+linea+"*$M"+linea+",T"+linea+"*$P"+linea+"*$M"+linea+"*$AC"+linea+")");
		            evaluator.evaluateFormulaCell(cell);
		            
		            cell = row.createCell(35);
		            cell.setCellStyle(detalle);
		            cell.setCellFormula("IF($AE"+linea+"=1,$N"+linea+"*U"+linea+"*$M"+linea+",U"+linea+"*$P"+linea+"*$M"+linea+"*$AC"+linea+")");
		            evaluator.evaluateFormulaCell(cell);
		            
		            cell = row.createCell(36);
		            cell.setCellStyle(detalle);
		            cell.setCellFormula("IF($AE"+linea+"=1,$N"+linea+"*V"+linea+"*$M"+linea+",V"+linea+"*$P"+linea+"*$M"+linea+"*$AC"+linea+")");
		            evaluator.evaluateFormulaCell(cell);
		            
		            cell = row.createCell(37);
		            cell.setCellStyle(detalle);
		            cell.setCellFormula("IF($AE"+linea+"=1,$N"+linea+"*W"+linea+"*$M"+linea+",W"+linea+"*$P"+linea+"*$M"+linea+"*$AC"+linea+")");
		            evaluator.evaluateFormulaCell(cell);
		            
		            cell = row.createCell(38);
		            cell.setCellStyle(detalle);
		            cell.setCellFormula("IF($AE"+linea+"=1,$N"+linea+"*X"+linea+"*$M"+linea+",X"+linea+"*$P"+linea+"*$M"+linea+"*$AC"+linea+")");
		            evaluator.evaluateFormulaCell(cell);
		            
		            cell = row.createCell(39);
		            cell.setCellStyle(detalle);
		            cell.setCellFormula("IF($AE"+linea+"=1,$N"+linea+"*Y"+linea+"*$M"+linea+",Y"+linea+"*$P"+linea+"*$M"+linea+"*$AC"+linea+")");
		            evaluator.evaluateFormulaCell(cell);
		            // fin calculos totales
					
					
		            // SUMA totales
					cell = row.createCell(40);
		            cell.setCellStyle(detalle);
		            cell.setCellFormula("SUM(AG"+linea+":AN"+linea+")");
		            evaluator.evaluateFormulaCell(cell);
		            
		            // MAX totales
					cell = row.createCell(41);
		            cell.setCellStyle(detalle);
		            cell.setCellFormula("IF(AE"+linea+"=1,N"+linea+"*AA"+linea+"*M"+linea+",AA"+linea+"*P"+linea+"*AC"+linea+"*M"+linea+")");
		            evaluator.evaluateFormulaCell(cell);
		            
		            // select  totales
					cell = row.createCell(42);
		            cell.setCellStyle(detalle);
		            cell.setCellFormula("IF(AE"+linea+"=1,N"+linea+"*AB"+linea+"*M"+linea+",AB"+linea+"*P"+linea+"*AC"+linea+"*M"+linea+")");
		            evaluator.evaluateFormulaCell(cell);
		            
		            
		            
		              //***********************************************************************************
			          // se valida ESTA ES LA INFO A IMPORTAR
			          //***********************************************************************************
			            cell = row.createCell(1);
			            cell.setCellStyle(detalle);
			            cell.setCellFormula("TRIM(I"+linea+")");
			            evaluator.evaluateFormulaCell(cell);
			            
			            cell = row.createCell(2);
			            cell.setCellStyle(detalle);
			            cell.setCellFormula("SUMPRODUCT(Z$9:AB$9"+",Z"+linea+":AB"+linea+")");
			            evaluator.evaluateFormulaCell(cell);
			            
			            cell = row.createCell(3);
			            cell.setCellStyle(detalle);
			            cell.setCellFormula("AE"+linea);
			            evaluator.evaluateFormulaCell(cell);
			            
			            cell = row.createCell(4);
			            cell.setCellStyle(detalle);
			            cell.setCellFormula("N"+linea);
			            evaluator.evaluateFormulaCell(cell);
			            
			            cell = row.createCell(5);
			            cell.setCellStyle(detalle);
			            cell.setCellFormula("P"+linea);
			            evaluator.evaluateFormulaCell(cell);
			            
			            cell = row.createCell(6);
			            cell.setCellStyle(detalle);
			            cell.setCellFormula("AC"+linea);
			            evaluator.evaluateFormulaCell(cell);
			          //fin se valida
			          //***********************************************************************************
			            
					
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
	    
	    public static List<List<String>> llenaListaDesdeExcel (File file) {
			List<List<String>> lista = new ArrayList<List<String>>();
			DecimalFormat df = new DecimalFormat("#");
		    df.setMaximumFractionDigits(8);
			try {
	            Workbook libro = WorkbookFactory.create(file);
	            Sheet hoja1 = libro.getSheetAt(0);
	            Row row = null;
	            Cell cell = null;
	            int x = 14;
	            row = hoja1.getRow(x);
	            cell = row.getCell(1);
	            while (row!=null && cell !=null ) {
	            	row = hoja1.getRow(x++);
	            	if(row!=null) {
	            		cell = row.getCell(1);
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
	                			for(int i=1; i<7; i++) {
	                    			String dato = "";
	                    			cell = row.getCell(i);
	                                if(cell!=null) {
	                                	try {
	                                		dato = cell.getStringCellValue().trim();
	                                		dato = dato.replaceAll("'", "\"");
	                                	}catch(Exception e){
	                                		if(i==1) {
	                                			Double aux = cell.getNumericCellValue();
	                                    		Long aux2 = aux.longValue();
	                                    		dato = df.format(aux2);
	                                		}else {
	                                			Double aux = cell.getNumericCellValue();
	                                    		dato = df.format(aux);
	                                		}
	                                	}
	                                }
	                                auxList.add(dato);
	                            }
	                			auxList.set(0, auxList.get(0).toUpperCase());
	                    		lista.add(auxList);
	                		}
	                	}
	                	cell = row.getCell(1);
	            	}
	            }
			} catch (InvalidFormatException | IOException e1) {
			}
			return(lista);
		}

		
}
