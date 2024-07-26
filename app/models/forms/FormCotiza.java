package models.forms;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.TempFile;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableCell.XWPFVertAlign;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import models.calculo.Inventarios;
import models.tables.BodegaEmpresa;
import models.tables.Cliente;
import models.tables.CotiBiblioteca;
import models.tables.CotizaDetalle;
import models.tables.Cotizacion;
import models.tables.EmisorTributario;
import models.tables.Equipo;
import models.tables.Moneda;
import models.tables.Movimiento;
import models.tables.Ot;
import models.tables.Precio;
import models.tables.Proyecto;
import models.tables.TasaEquipo;
import models.tables.TasaGrupo;
import models.tables.UnidadTiempo;
import models.tables.Usuario;
import models.utilities.Archivos;
import models.utilities.DecimalFormato;
import models.utilities.Fechas;
import play.libs.Files.TemporaryFile;
import play.mvc.Http;

public class FormCotiza {
	public Long id_cotizacion;
	public Long numeroCoti;
	public String fechaCoti;
	public Long id_cliente;
	public Long id_proyecto;
	public Long id_bodegaEmpresa;
	public String rutCliente;
	public String nickCliente;
	public String nickProyecto;
	public String nombreBodega;
	public String observaciones;
	
	public List<Long> id_equipo;
	public List<Long> id_moneda;
	public List<Long> id_unidadTiempo;
	public List<Long> esVenta;
	
	public List<String> cantidad;
	public List<String> puVentaRepos;
	public List<String> puArriendo;
	public List<String> permanencia;
	
	public String dctoArriendo;
	public String dctoVenta;
	
	public String cotizacionPDF;
	
	public String estadoCotizacion;
	public String fechaConfirmada;
	
	public Long id_sucursal;
	public Long id_comercial;
	
	public Long id_cotizaSolucion;
	

	public FormCotiza(Long id_cotizacion, Long numeroCoti, String fechaCoti, Long id_cliente, Long id_proyecto,
			Long id_bodegaEmpresa, String rutCliente, String nickCliente, String nickProyecto, String nombreBodega,
			String observaciones, List<Long> id_equipo, List<Long> id_moneda, List<Long> id_unidadTiempo,
			List<Long> esVenta, List<String> cantidad, List<String> puVentaRepos, List<String> puArriendo,
			List<String> permanencia, String dctoArriendo, String dctoVenta, String cotizacionPDF,
			String estadoCotizacion, String fechaConfirmada, Long id_sucursal, Long id_comercial, Long id_cotizaSolucion) {
		super();
		this.id_cotizacion = id_cotizacion;
		this.numeroCoti = numeroCoti;
		this.fechaCoti = fechaCoti;
		this.id_cliente = id_cliente;
		this.id_proyecto = id_proyecto;
		this.id_bodegaEmpresa = id_bodegaEmpresa;
		this.rutCliente = rutCliente;
		this.nickCliente = nickCliente;
		this.nickProyecto = nickProyecto;
		this.nombreBodega = nombreBodega;
		this.observaciones = observaciones;
		this.id_equipo = id_equipo;
		this.id_moneda = id_moneda;
		this.id_unidadTiempo = id_unidadTiempo;
		this.esVenta = esVenta;
		this.cantidad = cantidad;
		this.puVentaRepos = puVentaRepos;
		this.puArriendo = puArriendo;
		this.permanencia = permanencia;
		this.dctoArriendo = dctoArriendo;
		this.dctoVenta = dctoVenta;
		this.cotizacionPDF = cotizacionPDF;
		this.estadoCotizacion = estadoCotizacion;
		this.fechaConfirmada = fechaConfirmada;
		this.id_sucursal = id_sucursal;
		this.id_comercial = id_comercial;
		this.id_cotizaSolucion = id_cotizaSolucion;
	}

	public FormCotiza(Long numeroCoti, String fecha) {
		super();
		this.id_cotizacion = (long)0;
		this.numeroCoti = numeroCoti;
		this.fechaCoti = fecha;
		this.id_cliente = (long)0;
		this.id_proyecto = (long)0;
		this.id_bodegaEmpresa = (long)0;
		this.rutCliente = "";
		this.nickCliente = "";
		this.nickProyecto = "";
		this.nombreBodega = "";
		this.observaciones = "";
		this.dctoArriendo = "0";
		this.dctoVenta =  "0";
	}
	
	public FormCotiza(Long numeroCoti, String fecha, Long id_cliente, String rutCliente, String nomCliente, Long id_proyecto,String nomProyecto) {
		super();
		this.id_cotizacion = (long)0;
		this.numeroCoti = numeroCoti;
		this.fechaCoti = fecha;
		this.id_cliente = id_cliente;
		this.id_proyecto = id_proyecto;
		this.id_bodegaEmpresa = (long)0;
		this.rutCliente = rutCliente;
		this.nickCliente = nomCliente;
		this.nickProyecto = nomProyecto;
		this.nombreBodega = "";
		this.observaciones = "";
		this.dctoArriendo = "0";
		this.dctoVenta =  "0";
	}
	
	public FormCotiza(Long numeroCoti, String fecha, Long id_cliente, Long id_proyecto, Long id_bodegaEmpresa, String rutCliente, String nickCliente, String nickProyecto,
			String nombreBodega) {
		super();
		this.id_cotizacion = (long)0;
		this.numeroCoti = numeroCoti;
		this.fechaCoti = fecha;
		this.id_cliente = id_cliente;
		this.id_proyecto = id_proyecto;
		this.id_bodegaEmpresa = id_bodegaEmpresa;
		this.rutCliente = rutCliente;
		this.nickCliente = nickCliente;
		this.nickProyecto = nickProyecto;
		this.nombreBodega = nombreBodega;
		this.observaciones = "";
		this.dctoArriendo = "0";
		this.dctoVenta =  "0";
	}

	public FormCotiza() {
		super();
	}
	

	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	
	public static List<List<String>> allConSuPrecio(Connection con, String db, String nEmpresa, Long id_bodegaEmpresa, Map<String,String> mapeoPermiso, String id_sucursal, 
			String modificaCoti, String nuevaCoti, Long id_cotizacion) {
		List<List<String>> lista = new ArrayList<List<String>>();
		
		
		try {
			
			PreparedStatement smt = con
					.prepareStatement(" select " + 
							" equipo.id," + 
							" equipo.codigo," + 
							" equipo.nombre," + 
							" unidad.nombre," + 
							" ifnull(grupo.id,0), " +
							" ifnull(grupo.nombre,''), " +
							" equipo.vigente, " +
							" equipo.kg, " +
							" equipo.m2 " +
							" from `"+db+"`.equipo  "+
							" left join `"+db+"`.grupo on grupo.id = equipo.id_grupo "+
							" left join `"+db+"`.unidad on unidad.id = equipo.id_unidad "+
							" order by equipo.nombre; ");
			ResultSet rs = smt.executeQuery();
			
			Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
			
			Map<String,TasaGrupo> mapTasaGrupo = TasaGrupo.mapTasaPorBodegaGrupo(con, db);
			Map<String,TasaEquipo> mapTasaEquipo = TasaEquipo.mapTasaPorBodegaEquipo(con, db);
			BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, db, id_bodegaEmpresa);
			
			Map<Long,Precio> mapPrecio = Precio.mapAll(con, db, mapeoPermiso, Long.parseLong(id_sucursal));
			Map<Long,UnidadTiempo> mapUnidadTiempo = UnidadTiempo.mapUnidadTiempo(con, db);
			Map<Long,Moneda> mapMoneda = Moneda.mapMonedas(con, db);
				
			while (rs.next()) {
				
				String nickMoneda = "";
				Double precioReposicion = (double)0;
				String nUnidadT = "";
				Double precioArriendo = (double)0;
				Long idMoneda = (long)1;
				Long idUnidadT = (long)1;
				Long nroDecimal = (long)2;
				
				Precio precio = mapPrecio.get(rs.getLong(1));
				UnidadTiempo unidadTiempo = null;
				Moneda moneda = null;
						
				if(precio!=null) {
					idMoneda = precio.getId_moneda();
					precioReposicion = Double.parseDouble(precio.getPrecioReposicion().replaceAll(",", ""));
					precioArriendo = Double.parseDouble(precio.getPrecioArriendo().replaceAll(",", ""));
					idMoneda = precio.getId_moneda();
					idUnidadT = precio.getId_unidadTiempo();
					unidadTiempo = mapUnidadTiempo.get(precio.getId_unidadTiempo());
					moneda = mapMoneda.get(precio.getId_moneda());
				}
				
				if(moneda!=null) {
					nickMoneda = moneda.getNickName();
					nroDecimal = moneda.getNumeroDecimales();
				}
				
				if(unidadTiempo!=null) {
					nUnidadT = unidadTiempo.getNombre();
				}
				
				
				Long numDec = dec.get(idMoneda);
				if(numDec==null) {
					numDec = (long)0;
				}
				switch(numDec.toString()) {
				 case "0": myformatdouble = new DecimalFormat("#,##0"); break;
				 case "2": myformatdouble = new DecimalFormat("#,##0.00"); break;
				 case "4": myformatdouble = new DecimalFormat("#,##0.0000"); break;
				 case "6": myformatdouble = new DecimalFormat("#,##0.000000"); break;
				 default:  break;
				}
				
				List<String> aux = new ArrayList<String>();
				
				aux.add(rs.getString(1));  							//0 idEquipo
				aux.add(rs.getString(2));  							//1 codigo
				aux.add(rs.getString(3));  							//2 equipo
				aux.add(rs.getString(4));  							//3 unidad
				aux.add(nickMoneda);  								//4 moneda
				aux.add(myformatdouble.format(precioReposicion));  	//5 precio reposicion
				aux.add(nUnidadT);									//6 unidad tiempo
				
				
				
				//DETERMINA EL PRECIO UNITARIO DE ARRIENDO A APLICAR EN LA COTIZACION:
				
				if(mapeoPermiso.get("parametro.cotizaPorTasa").equals("1") && id_bodegaEmpresa != 0 && id_bodegaEmpresa != null) {
					
					if(nEmpresa.equals("BI")) {
						if(rs.getLong(5)==1) {
							TasaGrupo tasaGrupo = mapTasaGrupo.get(id_bodegaEmpresa+"_1");
							Double tasaBi = (double)0;
							if(tasaGrupo!=null) {
								tasaBi = tasaGrupo.tasaArriendo;
							}
							aux.add(myformatdouble.format(precioReposicion * tasaBi));		//7 precioArriendo unitario
						} else if(rs.getLong(5)==2) {
							TasaGrupo tasaGrupo = mapTasaGrupo.get(id_bodegaEmpresa+"_2");
							Double tasaBi = (double)0;
							if(tasaGrupo!=null) {
								tasaBi = tasaGrupo.tasaArriendo;
							}
							aux.add(myformatdouble.format(precioReposicion * tasaBi));		//7 precioArriendo unitario
						} else {
							aux.add(myformatdouble.format(precioArriendo));		    //7 precioArriendo unitario
						}
					} else {
							TasaEquipo tasaEquipo = mapTasaEquipo.get(id_bodegaEmpresa+"_"+rs.getString(1));
							TasaGrupo tasaGrupo = mapTasaGrupo.get(id_bodegaEmpresa+"_"+rs.getString(5));
							
							Double tasaEquip = (double)0;
							if(tasaEquipo!=null) {
								tasaEquip = tasaEquipo.tasaArriendo;
							}
							Double tasaGrup = (double)0;
							if(tasaGrupo!=null) {
								tasaGrup = tasaGrupo.tasaArriendo;
							}
							Double tasaBod = bodega.tasaArriendo;
							
							Double tasa = (double)0;
							if((double)tasaEquip > (double) 0) {
								tasa = tasaEquip;
							}else if((double) tasaGrup > (double) 0) {
								tasa = tasaGrup;
							}else {
								tasa = tasaBod;
							}
							
							aux.add(myformatdouble.format(precioReposicion * tasa));		//7 precioArriendo unitario
							
						}
				}else {
					aux.add(myformatdouble.format(precioArriendo));   					//7 precio arriendo
				}
				// FIN DETERMINA PRECIO ARRIENDO
					

				aux.add(idMoneda.toString());  												//8 idMoneda
				aux.add(idUnidadT.toString());  											//9 idUnidadTiempo
				aux.add("0.00");  														//10 cantidad
				aux.add("0");  															//11 esVenta
				aux.add("1.00");  														//12 permanencia
				aux.add("0.00");  														//13 totalReposicion
				aux.add("0.00");  														//14 totalArriendo
				aux.add("0.00");  														//15 totalVenta
				
				Double kg = rs.getDouble(8);
				Double m2 = rs.getDouble(9);
				
				aux.add(kg.toString());  												//16 kg unitario
				aux.add(m2.toString());  												//17 m2 unitario
				
				aux.add("0.00");  														//18 total kg
				aux.add("0.00");  														//19 total m2
				
				aux.add(nroDecimal.toString());  											//20 numero de decimales
				aux.add(numDec.toString()); 										//21 maximo numero de decimales
				aux.add(rs.getString(5)); 												//22 id grupo
				aux.add(rs.getString(6)); 												//23 nombre grupo
				
				Double puArriendo = Double.parseDouble(aux.get(7).replaceAll(",", ""));
				Double pVtaRepos = Double.parseDouble(aux.get(5).replaceAll(",", ""));
				
				Double auxTasaArr = (double)0;
				if((double)pVtaRepos > (double)0 && (double)puArriendo > (double)0) {
					auxTasaArr = puArriendo/pVtaRepos;
				}
				
				
				String tasaArriendo = myformatdouble2.format(auxTasaArr*100)+" %";
				aux.add(tasaArriendo); 													//24 tasaArriendo
				
				aux.add(rs.getString(7)); 													//25 vigente
				
				lista.add(aux);
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		List<List<String>> lista2 = new ArrayList<List<String>>();
		
		if(modificaCoti.equals("1")) {
			Map<String,Long> map = Cotizacion.mapDetIdEquipVsIdEquip(con, db, id_cotizacion);
			for(List<String> l: lista) {
				Long id = map.get(l.get(0));
				if(id!=null) {
					lista2.add(l);
				}else {
					if(l.get(25).equals("1")) {
						lista2.add(l);
					}
				}
			}
		} else if(nuevaCoti.equals("1")){
			for(List<String> l: lista) {
				if(l.get(25).equals("1")) {
					lista2.add(l);
				}
			}
		}else {
			lista2 = lista;
		}
		return (lista2);
	}
	

	
	
	public static String vistaInvPorEquipoAjax(Connection con, String db, Long id_equipo, Map<String,String> mapeoPermiso, Map<String,String> mapeoDiccionario) {
		Long soloArriendo = (long) 1;
		if(mapeoPermiso.get("parametro.permiteDevolverVentas").equals("1")) {
			soloArriendo = (long) 0;
		}
		Map<String ,Movimiento> invPorIdEquipoAgrupadoPorBodega = Inventarios.invPorIdEquipoAgrupadoPorBodega(con, db, id_equipo, soloArriendo);
		Map<Long ,BodegaEmpresa> mapBodega = BodegaEmpresa.mapAll(con, db);
		Equipo equipo = Equipo.find(con, db, id_equipo);
		
		
		List<List<String>> lista = new ArrayList<List<String>>();
		invPorIdEquipoAgrupadoPorBodega.forEach((k,v)->{
			if(v.cantidad>0) {
				BodegaEmpresa bodega = mapBodega.get(v.id_bodegaEmpresa);
				String nomBodega = "";
				String tipoBodega = "";
				if(bodega!=null) {
					nomBodega = bodega.getNombre();
					tipoBodega = bodega.getNombreTipoBodega();
				}
				String grupo = "";
				String codigo = "";
				String nomEquipo = "";
				if(equipo!=null) {
					grupo = equipo.getGrupo();
					codigo = equipo.getCodigo();
					nomEquipo = equipo.getNombre();
				}
				List<String> aux = new ArrayList<String>();
				aux.add(codigo);
				aux.add(nomEquipo);
				aux.add(myformatdouble2.format(v.cantidad));
				aux.add(nomBodega);
				aux.add(tipoBodega);
				aux.add(grupo);
				lista.add(aux);
			}
		});
		Double total = (double)0;
		for(List<String> x: lista) {
			total += Double.parseDouble(x.get(2).replaceAll(",", ""));
		};
		
		//ORDENA LA LISTA
		for(int j=0;j<lista.size();j++) {
	        for(int i=0;i<lista.size()-j;i++) {
	            if (i+1!=lista.size()&&lista.get(i).get(3).compareTo(lista.get(i+1).get(3))>0) {
	                List<String> aux;
	                aux=lista.get(i);
	                lista.set(i,lista.get(i+1));
	                lista.set(i+1, aux);
	            }
	        }
	    }
		
		String comprometido = Ot.cantidadComprometidaPorEquip(con, db, id_equipo);
		
		
		
		String vistaHTML = 
				"<h5>Cantidad comprometida por despachar: "+comprometido+"</h5>"+
				"<table class='table table-sm table-bordered table-condensed table-hover table-fluid'>" +
				     	"<thead style='background-color: #eeeeee'>" +
				     		"<TR><th>Grupo</th><th>Codigo</th><th>Equipo</th><th>Cantidad</th><th>"+mapeoDiccionario.get("Bodega")+"/Proyecto</th><th>Tipo</th></TR>"+
						"</thead><tbody>";
				for(int i=0;i<lista.size();i++){
					vistaHTML +=
							"<TR>"+
								"<td style='text-align:left;'>"+lista.get(i).get(5)+"</td>"+
								"<td style='text-align:left;'>"+lista.get(i).get(0)+"</td>"+
								"<td style='text-align:left;'>"+lista.get(i).get(1)+"</td>"+
								"<td style='text-align:right;'>"+lista.get(i).get(2)+"</td>"+
								"<td style='text-align:left;'>"+lista.get(i).get(3)+"</td>"+
								"<td style='text-align:left;'>"+lista.get(i).get(4)+"</td>"+
							"</TR>";
				}
				vistaHTML += "</tbody>" +
						"<td colspan='3' style='text-align:right;'>TOTAL</td>"+
						"<td style='text-align:right;'>"+myformatdouble2.format(total)+"</td>"+
						"<td colspan='2' style='text-align:right;'></td>"+
						"</table>";
		return(vistaHTML);
		
	}
	
	public static List<List<String>> detalleCotizacion(Connection con, String db, Map<String,String> mapeoPermiso, Map<String,String> mapeoDiccionario, 
			Long id_bodegaEmpresa, Long id_cotizacion, String id_sucursal, String modificaCoti, String nuevaCoti,
			List<List<String>> lista) {
		if(lista == null) {
			lista = FormCotiza.allConSuPrecio(con, db, mapeoDiccionario.get("nEmpresa"), id_bodegaEmpresa, mapeoPermiso, id_sucursal, modificaCoti, nuevaCoti, id_cotizacion);
		}
		
		Map<Long,List<Double>> mapCotiDetalle = Cotizacion.mapDetalleCotizacion(con, db, id_cotizacion);
		Map<Long,UnidadTiempo> mapUnTiempo = UnidadTiempo.mapUnidadTiempo(con, db);
		Map<Long,Moneda> mapMoneda = Moneda.mapMonedas(con, db);
		
		for(int i=0;i<lista.size();i++){
			Long idEquipo = Long.parseLong(lista.get(i).get(0).trim());
			List<Double> aux = mapCotiDetalle.get(idEquipo);
			
			
			if(aux!=null){
				Long decimal = Long.parseLong(lista.get(i).get(20));
				lista.get(i).set(5, DecimalFormato.formato(aux.get(4), decimal)); 	//prep
				lista.get(i).set(7, DecimalFormato.formato(aux.get(3), decimal)); 	//parr
				
				Double auxTasaArr = (double)0;
				if((double)aux.get(4) > (double)0) {
					auxTasaArr = aux.get(3) / aux.get(4);
				}
				String tasaArriendo = myformatdouble2.format(auxTasaArr*100)+" %";
				lista.get(i).set(24,tasaArriendo); 									//parr
				
				lista.get(i).set(10, DecimalFormato.formato(aux.get(0), (long)2));
				lista.get(i).set(11, DecimalFormato.formato(aux.get(1), (long)0));
				lista.get(i).set(12, DecimalFormato.formato(aux.get(2), (long)2));
				if((double)aux.get(1) == (double)0) { // si no es venta:
					lista.get(i).set(13, DecimalFormato.formato((aux.get(0) * aux.get(4)), decimal));
				}
				if((double)aux.get(1) == (double)0) { // si no es venta:
					lista.get(i).set(14, DecimalFormato.formato((aux.get(0) * aux.get(2) * aux.get(3)), decimal));
				}
				if((double)aux.get(1) == (double)1) { // si es venta:
					lista.get(i).set(15, DecimalFormato.formato((aux.get(0) * aux.get(4)), decimal));
				}
				lista.get(i).set(18, DecimalFormato.formato((aux.get(0) * Double.parseDouble(lista.get(i).get(16))), (long)2)); 
				lista.get(i).set(19, DecimalFormato.formato((aux.get(0) * Double.parseDouble(lista.get(i).get(17))), (long)2));
				
				
				Long id_unidadTiempo = aux.get(5).longValue();
				UnidadTiempo unTiempo = mapUnTiempo.get(id_unidadTiempo);
				if(unTiempo!=null) {
					lista.get(i).set(6, unTiempo.getNombre());
					lista.get(i).set(9, unTiempo.getId().toString());
				}
				
				Long id_moneda = aux.get(6).longValue();
				Moneda moneda = mapMoneda.get(id_moneda);
				if(unTiempo!=null) {
					lista.get(i).set(4, moneda.getNickName());
					lista.get(i).set(8, moneda.getId().toString());
				}
				
				
				
			}
		}
		
		for(List<String> l: lista) {
			l.add(l.get(10).replaceAll(",", ""));
			l.add(l.get(5).replaceAll(",", ""));
			l.add(l.get(24).replaceAll(",", "").replaceAll("%", ""));
			l.add(l.get(7).replaceAll(",", ""));
			l.add(l.get(12).replaceAll(",", ""));
			
			l.add(l.get(13).replaceAll(",", ""));
			l.add(l.get(14).replaceAll(",", ""));
			l.add(l.get(15).replaceAll(",", ""));
			l.add(l.get(18).replaceAll(",", ""));
			l.add(l.get(19).replaceAll(",", ""));
		}
		
		
		return(lista);
	}
	
	public static boolean create (Connection con, String db, FormCotiza form, Http.MultipartFormData.FilePart<TemporaryFile> docAdjunto, String id_usuario) {
		boolean flag = false;
		Cotizacion cotizacion = new Cotizacion(form);
		Long id_cotizacion = Cotizacion.create(con, db, cotizacion, id_usuario);
		if((long)id_cotizacion > (long)0) {
			String detalle = "";
			for(int i=0; form.id_equipo!=null && i<form.id_equipo.size(); i++) {
				Double cantidad = Double.parseDouble(form.cantidad.get(i).replaceAll(",", ""));
				if((double) cantidad > (double) 0) {
					detalle += "('"+id_cotizacion+"','"+
							form.id_equipo.get(i)+"','"+
							form.id_moneda.get(i)+"','"+
							form.puVentaRepos.get(i).replaceAll(",", "")+"','"+
							form.puVentaRepos.get(i).replaceAll(",", "")+"','"+
							form.puArriendo.get(i).replaceAll(",", "")+"','"+
							form.id_unidadTiempo.get(i)+"','"+
							form.cantidad.get(i).replaceAll(",", "")+"','"+
							form.permanencia.get(i).replaceAll(",", "")+"','"+
							form.esVenta.get(i)+"'),";
				}
			}
			
			if(form.id_equipo!=null && detalle.length()>10) {
				detalle = detalle.substring(0,detalle.length()-1);
				if(!CotizaDetalle.create(con, db, detalle)) {
					Cotizacion.delete(con, db, id_cotizacion);
				}else {
					if (docAdjunto != null) {
						String nombreArchivoSinExtencion = "Doc_Cotiza_" + id_cotizacion;
						String nombreArchivoConExtencion = Archivos.grabarArchivos(docAdjunto, db, nombreArchivoSinExtencion);
						Cotizacion.modifyXCampo(con, db, "cotizacionPDF", nombreArchivoConExtencion, id_cotizacion);
					}
					flag = true;
				}
			}
		}
		return(flag);
	}
	
	public static boolean update (Connection con, String db, FormCotiza form, Http.MultipartFormData.FilePart<TemporaryFile> docAdjunto, String id_usuario) {
		boolean flag = false;
		Cotizacion cotizacion = new Cotizacion(form);
		cotizacion.setId(form.id_cotizacion);
		if(Cotizacion.update(con, db, cotizacion, id_usuario)) {
			String detalle = "";
			for(int i=0; form.id_equipo!=null && i<form.id_equipo.size(); i++) {
				Double cantidad = Double.parseDouble(form.cantidad.get(i).replaceAll(",", ""));
				if((double) cantidad > (double) 0) {
					detalle += "('"+form.id_cotizacion+"','"+
							form.id_equipo.get(i)+"','"+
							form.id_moneda.get(i)+"','"+
							form.puVentaRepos.get(i).replaceAll(",", "")+"','"+
							form.puVentaRepos.get(i).replaceAll(",", "")+"','"+
							form.puArriendo.get(i).replaceAll(",", "")+"','"+
							form.id_unidadTiempo.get(i)+"','"+
							form.cantidad.get(i).replaceAll(",", "")+"','"+
							form.permanencia.get(i).replaceAll(",", "")+"','"+
							form.esVenta.get(i)+"'),";
				}
			}

			if(form.id_equipo!=null && detalle.length()>10) {
				detalle = detalle.substring(0,detalle.length()-1);
				if(CotizaDetalle.delete(con, db, form.id_cotizacion)) {
					CotizaDetalle.create(con, db, detalle);
					String path = "0";
					if (docAdjunto != null) {
						String nombreArchivoSinExtencion = "Doc_Cotiza_" + form.id_cotizacion;
						path = Archivos.grabarArchivos(docAdjunto, db, nombreArchivoSinExtencion);
						Cotizacion.modifyXCampo(con, db, "cotizacionPDF", path, form.id_cotizacion);
					}
					flag = true;
				};
			}
		}
		return(flag);
	}
	
	public static String generaPdfCotizaArriendo(Connection con, String db, Long id_cotizacion, Map<String,String> mapDiccionario, String cfi, Cotizacion cotizacion, 
			Map<String,String> mapPermiso, String sinDetalle, String selectGrupos, String tasaIva){
		
		
		List<CotizaDetalle> detalleCoti = CotizaDetalle.allPorIdCotizacion(con, db,id_cotizacion);
		Cliente cliente = Cliente.find(con, db, cotizacion.getId_cliente());
		String nickCliente = "";
		String nombreCliente = "";
		String rutCliente = "";
		if(cliente!=null) {
			nickCliente = cliente.getNickName();
			nombreCliente = cliente.getNombre();
			rutCliente = cliente.getRut();
		}
		if(nombreCliente.length()>2) {
			nickCliente = nombreCliente + " ("+nickCliente+")";
		}
		Proyecto proyecto = Proyecto.find(con, db, cotizacion.getId_proyecto());
		String nickProyecto = "";
		if(proyecto!=null) {
			nickProyecto = proyecto.getNickName();
		}
		
		EmisorTributario emisorTributario = models.tables.EmisorTributario.find(con, db);
		 
		
		File tmp = TempFile.createTempFile("tmp","null");
		
		
		
		
		
		try {
			String path = db + "/formatos/cotizaArriendo.docx";
			InputStream formato = Archivos.leerArchivo(path);
			XWPFDocument doc = new XWPFDocument(formato);
			formato.close();
		
			XWPFTable table = null;
			XWPFTableRow row = null;
			XWPFTableCell cell = null;
			
			table = doc.getTables().get(0);
			
			row=table.getRow(1);
			cell=row.getCell(2);
			setCelda(cell,"Arial",10,2,"2b5079","Fecha: "+Fechas.DDMMAA(cotizacion.fecha),false);
			
			row=table.getRow(2);
			cell=row.getCell(2);
			setCelda(cell,"Arial",10,2,"2b5079","Numero: "+cotizacion.numero.toString(),false);
			
			table= doc.getTables().get(1);
			
			cell=table.getRow(0).getCell(1);
			setCelda(cell,"Arial",10,1,"2b5079",nickCliente,false);
			
			cell=table.getRow(1).getCell(1);
			setCelda(cell,"Arial",10,1,"2b5079",rutCliente,false);
			
			cell=table.getRow(1).getCell(3);
			setCelda(cell,"Arial",10,1,"2b5079",nickProyecto,false);
			
			if(table.getNumberOfRows()<5) {
				cell=table.getRow(2).getCell(0);
				setCelda(cell,"Arial",10,1,"2b5079","",false);
			}else {
				cell=table.getRow(2).getCell(1);
				setCelda(cell,"Arial",10,1,"2b5079",cliente.giro,false);
				
				cell=table.getRow(3).getCell(1);
				setCelda(cell,"Arial",10,1,"2b5079",cliente.direccion,false);
				
				cell=table.getRow(4).getCell(1);
				setCelda(cell,"Arial",10,1,"2b5079",cliente.contactoFactura + "   TELEFONO: " + cliente.fonoContacto + "   EMAIL: " + cliente.mailFactura,false);
			}
			
			
			
			Double totalPrecio=(double)0;
			Double sumKG=(double)0;
			Double sumM2=(double)0;
			Double sumCant=(double)0;
			Long idMoneda = (long) 1;
			Double totalPrecioVenta = (double)0;
			Double totalReposicion = (double)0;
			
			String moneda = Moneda.find(con, db, (long) 1).getNickName();
			
			int contFilasTabla = 0;
			table = doc.getTables().get(2);
			
			if( mapPermiso.get("parametro.escondeLosM2")!=null && mapPermiso.get("parametro.escondeLosM2").equals("1")) {
				cell=table.getRow(0).getCell(12);setCelda(cell,"Arial",8,3,"2b5079","",false);
			}
			
			
			Double auxRestar = (double)0;
			
			if(sinDetalle.equals("2")) {
				
				String[] aux = selectGrupos.split(";");
				Map<String,String> mapIdGrupo = new HashMap<String,String>();
				for(int i=0; i<aux.length; i++) {
					mapIdGrupo.put(aux[i], aux[i]);
				}
				Map<String,Long> mapDecimal = Moneda.numeroDecimalxNombre(con, db);
				
				
				table.createRow();
				contFilasTabla++;
				contFilasTabla++;
				row = table.getRow(contFilasTabla);
				cell=row.getCell(0);setCelda(cell,"Arial",8,1,"2b5079","Solucion",false);
				cell=row.getCell(1);setCelda(cell,"Arial",8,1,"2b5079",cotizacion.getNameCotizaSolucion(),false);
				cell=row.getCell(2);setCelda(cell,"Arial",8,2,"2b5079","GL",false);
				cell=row.getCell(3);setCelda(cell,"Arial",8,3,"2b5079","1.00",false);
				cell=row.getCell(4);setCelda(cell,"Arial",7,2,"2b5079",moneda,false);
				
				if(moneda != null) {
					moneda = moneda.toUpperCase();
				}
				Long nroDecimal = mapDecimal.get(moneda);
				
				table.createRow();
				for(int i=0; i<detalleCoti.size(); i++) {
					
						idMoneda = detalleCoti.get(i).getId_moneda();
						String codigo = detalleCoti.get(i).getCodigo();
						String equipo = detalleCoti.get(i).getEquipo();
						String unidad = detalleCoti.get(i).getUnidad();
						String cantidad = detalleCoti.get(i).getCantidad();
						moneda = detalleCoti.get(i).getMoneda();
						String puVenta = detalleCoti.get(i).getPrecioReposicion();
						String totalArriendo = detalleCoti.get(i).getTotalArriendo();
					//	String totalVenta = detalleCoti.get(i).getTotalVenta();
						String totalKg = detalleCoti.get(i).getTotalKG();
						String totalM2 = detalleCoti.get(i).getTotalM2();
						
		    			if(totalArriendo == null || totalArriendo.trim().length()<=0) {
		    				totalArriendo = "0";
		    			}
		    			
		    			Double auxTotalArriendo = Double.parseDouble(totalArriendo.replaceAll(",", "").trim());
		    			totalPrecio += auxTotalArriendo;
		    			
		    			String idGrupo = mapIdGrupo.get(detalleCoti.get(i).id_grupo.toString());
		    			if(idGrupo != null) {
		    				if(puVenta != null && detalleCoti.get(i).getEsVenta() == (long)1) {
		    					Double pu = Double.parseDouble(puVenta.replaceAll(",", "").trim());
		    					Double cant = Double.parseDouble(cantidad.replaceAll(",", "").trim());
		    					if(pu > 0 && cant > 0) {
		    						totalPrecio += pu * cant;
		    						auxTotalArriendo += pu * cant;
		    					}
			    				
			    			}
		    			}
		    			
		    			
		    			if(totalKg == null || totalKg.trim().length()<=0) {
		    				totalKg = "0";
		    			}
		    			if(totalM2 == null || totalM2.trim().length()<=0) {
		    				totalM2 = "0";
		    			}
		    			if(cantidad == null || cantidad.trim().length()<=0) {
		    				cantidad = "0";
		    			}
		    			
		    			if(puVenta == null || cantidad.trim().length()<=0) {
		    				puVenta = "0";
		    			}
		    			
		   				sumKG += Double.parseDouble(totalKg.replaceAll(",", "").trim());
		   				sumM2 += Double.parseDouble(totalM2.replaceAll(",", "").trim());
		   				sumCant += Double.parseDouble(cantidad.replaceAll(",", "").trim());
		   				
		   				Double puVentaAux = Double.parseDouble(puVenta.replaceAll(",", "").trim());
		   				Double cantidadAux = Double.parseDouble(cantidad.replaceAll(",", "").trim());
		   				totalPrecioVenta +=  puVentaAux * cantidadAux;
		   				
		   				totalReposicion += Double.parseDouble(puVenta.replaceAll(",", "").trim()) * cantidadAux;
		   				
		   				if(idGrupo != null) {
		   					contFilasTabla++;
							row = table.getRow(contFilasTabla);
							cell=row.getCell(0);setCelda(cell,"Arial",8,1,"2b5079",codigo,false);
							cell=row.getCell(1);setCelda(cell,"Arial",8,1,"2b5079",equipo,false);
							cell=row.getCell(2);setCelda(cell,"Arial",8,2,"2b5079",unidad,false);
							cell=row.getCell(3);setCelda(cell,"Arial",8,3,"2b5079",cantidad,false);
							cell=row.getCell(4);setCelda(cell,"Arial",7,2,"2b5079",moneda,false);
							
							if(moneda != null) {
								moneda = moneda.toUpperCase();
	    					}
							nroDecimal = mapDecimal.get(moneda);
							if(nroDecimal != null) {
								totalArriendo = DecimalFormato.formato(auxTotalArriendo, nroDecimal);
							}else {
								totalArriendo = DecimalFormato.formato(auxTotalArriendo, (long)0);
							}
							auxRestar += auxTotalArriendo;
							cell=row.getCell(10);setCelda(cell,"Arial",8,3,"2b5079",totalArriendo,false);
							
							table.createRow();
		   				}
		   				

				}
				
				
			} else {
				table.createRow();
				for(int i=0; i<detalleCoti.size(); i++) {
					if((long)detalleCoti.get(i).esVenta == (long)0) {
						
						contFilasTabla++;
						
						idMoneda = detalleCoti.get(i).getId_moneda();
						String codigo = detalleCoti.get(i).getCodigo();
						String equipo = detalleCoti.get(i).getEquipo();
						String unidad = detalleCoti.get(i).getUnidad();
						String cantidad = detalleCoti.get(i).getCantidad();
						moneda = detalleCoti.get(i).getMoneda();
						String puVenta = detalleCoti.get(i).getPrecioReposicion();
						String unidadArriendo = detalleCoti.get(i).getUnidadTiempo();
						String puArriendo = detalleCoti.get(i).getPrecioArriendo();
						String permanencia = detalleCoti.get(i).getPermanencia().toString();
						String totalArriendo = detalleCoti.get(i).getTotalArriendo();
						String parcialRepos = detalleCoti.get(i).getTotalReposicion();
						String totalKg = detalleCoti.get(i).getTotalKG();
						String totalM2 = detalleCoti.get(i).getTotalM2();
						
		    			if(totalArriendo == null || totalArriendo.trim().length()<=0) {
		    				totalArriendo = "0";
		    			}
		    			if(totalKg == null || totalKg.trim().length()<=0) {
		    				totalKg = "0";
		    			}
		    			if(totalM2 == null || totalM2.trim().length()<=0) {
		    				totalM2 = "0";
		    			}
		    			if(cantidad == null || cantidad.trim().length()<=0) {
		    				cantidad = "0";
		    			}
		    			
		    			if(puVenta == null || cantidad.trim().length()<=0) {
		    				puVenta = "0";
		    			}
		    			
		    			
		    			
		   				totalPrecio += Double.parseDouble(totalArriendo.replaceAll(",", "").trim());
		   				sumKG += Double.parseDouble(totalKg.replaceAll(",", "").trim());
		   				sumM2 += Double.parseDouble(totalM2.replaceAll(",", "").trim());
		   				sumCant += Double.parseDouble(cantidad.replaceAll(",", "").trim());
		   				
		   				Double puVentaAux = Double.parseDouble(puVenta.replaceAll(",", "").trim());
		   				Double cantidadAux = Double.parseDouble(cantidad.replaceAll(",", "").trim());
		   				totalPrecioVenta +=  puVentaAux * cantidadAux;
		   				
		   				totalReposicion += Double.parseDouble(puVenta.replaceAll(",", "").trim()) * cantidadAux;
		   				
						row = table.getRow(contFilasTabla);
						cell=row.getCell(0);setCelda(cell,"Arial",8,1,"2b5079",codigo,false);
						cell=row.getCell(1);setCelda(cell,"Arial",8,1,"2b5079",equipo,false);
						cell=row.getCell(2);setCelda(cell,"Arial",8,2,"2b5079",unidad,false);
						cell=row.getCell(3);setCelda(cell,"Arial",8,3,"2b5079",cantidad,false);
						cell=row.getCell(4);setCelda(cell,"Arial",7,2,"2b5079",moneda,false);
						cell=row.getCell(5);setCelda(cell,"Arial",8,3,"2b5079",puVenta,false);
						cell=row.getCell(6);setCelda(cell,"Arial",8,2,"2b5079",unidadArriendo,false);
						cell=row.getCell(7);setCelda(cell,"Arial",8,3,"2b5079",puArriendo,false);
						cell=row.getCell(8);setCelda(cell,"Arial",8,3,"2b5079",permanencia,false);
						cell=row.getCell(9);setCelda(cell,"Arial",8,3,"2b5079",parcialRepos,false);
						cell=row.getCell(10);setCelda(cell,"Arial",8,3,"2b5079",totalArriendo,false);
						cell=row.getCell(11);setCelda(cell,"Arial",8,3,"2b5079",totalKg,false);
						if( !(mapPermiso.get("parametro.escondeLosM2")!=null && mapPermiso.get("parametro.escondeLosM2").equals("1")) ) {
							cell=row.getCell(12);setCelda(cell,"Arial",8,3,"2b5079",totalM2,false);
						}
						table.createRow();
					}
				}
				
				Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
				Long numDec = dec.get(idMoneda);
				if(numDec==null) {
					numDec = (long)1;
				}
				switch(numDec.toString()) {
				 case "0": myformatdouble = new DecimalFormat("#,##0"); break;
				 case "2": myformatdouble = new DecimalFormat("#,##0.00"); break;
				 case "4": myformatdouble = new DecimalFormat("#,##0.0000"); break;
				 case "6": myformatdouble = new DecimalFormat("#,##0.000000"); break;
				 default:  break;
				}
				
				
		        
				//table.createRow();
				row = table.getRow(contFilasTabla+2);
				
				cell=row.getCell(1);setCelda(cell,"Arial",8,1,"2b5079","TOTALES",true);
				cell=row.getCell(3);setCelda(cell,"Arial",8,3,"2b5079",myformatdouble.format(sumCant),true);
				cell=row.getCell(9);setCelda(cell,"Arial",8,3,"2b5079",myformatdouble.format(totalReposicion),true);
				cell=row.getCell(10);setCelda(cell,"Arial",8,3,"2b5079",myformatdouble.format(totalPrecio),true);
				cell=row.getCell(11);setCelda(cell,"Arial",8,3,"2b5079",myformatdouble.format(sumKG),true);
				if( !(mapPermiso.get("parametro.escondeLosM2")!=null && mapPermiso.get("parametro.escondeLosM2").equals("1")) ) {
					cell=row.getCell(12);setCelda(cell,"Arial",8,3,"2b5079",myformatdouble.format(sumM2),true);
				}
			}

			
			Double subTotal = totalPrecio;
	        
	        if(cotizacion.getDctoArriendo() == null) {
	        	cotizacion.setDctoArriendo((double)0);
	        }
	        
	        Double totalDcto = subTotal * cotizacion.getDctoArriendo();
	        Double totalNeto = subTotal - totalDcto;
	        
	        Double tasaIvaDbl = emisorTributario.getTasaIva()/100;
	        
	        
	        if(mapPermiso.get("parametro.ivaPorBodega")!=null && mapPermiso.get("parametro.ivaPorBodega").equals("1") && cotizacion.getId_bodegaEmpresa()>0) {
	        	BodegaEmpresa bodegaEmpresa = BodegaEmpresa.findXIdBodega(con, db, cotizacion.getId_bodegaEmpresa());
	        	if(bodegaEmpresa!=null) {
	        		Double aux = bodegaEmpresa.getIvaBodega();
	        		if(aux > 0) {
	        			tasaIvaDbl = bodegaEmpresa.getIvaBodega();
	        		} else {
	        			tasaIvaDbl = Double.parseDouble(tasaIva.replaceAll("%", "").trim().replaceAll(",",""))/100;
	        		}
	        	} else {
	        		tasaIvaDbl = Double.parseDouble(tasaIva.replaceAll("%", "").trim().replaceAll(",",""))/100;
	        	}
	        }
	        
	        String auxTasaIva = DecimalFormato.formato(tasaIvaDbl * 100, (long)2) + " %";
	        
	        
	        
	        Double totalIva = totalNeto * tasaIvaDbl;
	        Double total = totalNeto * (1+tasaIvaDbl);

	        table = doc.getTables().get(3);
	        
			cell=table.getRow(0).getCell(2);
			setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(subTotal),false);
			
			if(totalDcto!=0) {
				cell=table.getRow(1).getCell(1);
				setCelda(cell,"Arial",10,1,"000000","DESCUENTO "+myformatdouble2.format(cotizacion.dctoArriendo*100)+" %",false);
				
    			cell=table.getRow(1).getCell(2);
    			setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(totalDcto),false);
			}else {
				cell=table.getRow(1).getCell(1);
				setCelda(cell,"Arial",10,1,"000000"," ",false);
			}
			
			cell=table.getRow(2).getCell(2);
			setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(totalNeto),false);
			
			if(!db.equals("madaAndinaMontajes")) {
				
				cell = table.getRow(3).getCell(1);
				String aux = cell.getText() +" "+auxTasaIva;
				setCelda(cell,"Arial",10,1,"2b5079",aux,false);
				
				cell=table.getRow(3).getCell(2);
				setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(totalIva),false);
				
				cell=table.getRow(4).getCell(2);
				setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(total),false);
				
			}else {
				cell=table.getRow(3).getCell(1);
				setCelda(cell,"Arial",10,3,"2b5079","",false);
				
				cell=table.getRow(4).getCell(1);
				setCelda(cell,"Arial",10,3,"2b5079","",false);
				
				cell=table.getRow(3).getCell(0);
				setCelda(cell,"Arial",10,3,"2b5079","",false);
				
				cell=table.getRow(4).getCell(0);
				setCelda(cell,"Arial",10,3,"2b5079","",false);
			}
			
			
			
			
			
			cell=table.getRow(0).getCell(0);
			setCelda(cell,"Arial",10,1,"2b5079","NOTAS:\n"+cotizacion.getObservaciones(),false);
			
			Double dblCFI = Double.parseDouble(cfi.replaceAll("%", "").trim().replaceAll(",",""))/100;
    		Double totalCFI = dblCFI * totalPrecioVenta;
    		
    		
    		Usuario comercial = Usuario.findXIdUser(con, db, cotizacion.getId_comercial());
    		String nombreComercial = comercial.getNombre();
    		String telefonosComercial = comercial.getFono();
    		String correoComercial = comercial.getEmail();
    		String valorTotalReposicion = myformatdouble.format(totalReposicion);
    		String nameSucursal = cotizacion.getNameSucursal(); 
    		String formaDePago = cliente.getFormaDePago();
			
			for (XWPFParagraph p : doc.getParagraphs()) {
	             for (XWPFRun r : p.getRuns()) {
	                 String text = r.getText(0);
	 	            if(text!=null){
	 	               if (text.contains("XXXX"))   {
	 	                    text = text.replace("XXXX", " " + cfi);
	 	                    r.setText(text, 0); 
	 	               }
	 	               if (text.contains("CCOSTOFFIJO"))   {
		   	 	        	if((double) dblCFI > (double)0) {
		                		text = text.replace("CCOSTOFFIJO", " Al total se debe agregar el costo fijo inicial de "+cfi+
		                				" calculado en "+myformatdouble.format(totalCFI)+" "+moneda+" más IVA." );
		                	} else {
		                		text = text.replace("CCOSTOFFIJO", " " );
		                	}
			                r.setText(text, 0); 
			           }
		 	           if (text.contains("nombreComercial"))   {
		 	                    text = text.replace("nombreComercial", " " + nombreComercial);
		 	                    r.setText(text, 0); 
		 	           }
		 	           if (text.contains("telefonosComercial"))   {
		 	                    text = text.replace("telefonosComercial", " " + telefonosComercial);
		 	                    r.setText(text, 0); 
		 	           }
		 	           if (text.contains("correoComercial"))   {
	 	                    text = text.replace("correoComercial", " " + correoComercial);
	 	                    r.setText(text, 0); 
		 	           }
		 	           if (text.contains("valorTotalReposicion"))   {
	 	                    text = text.replace("valorTotalReposicion", " " + valorTotalReposicion);
	 	                    r.setText(text, 0); 
		 	           }
		 	           if (text.contains("nameSucursal"))   {
	 	                    text = text.replace("nameSucursal", " " + nameSucursal);
	 	                    r.setText(text, 0); 
		 	           }
		 	           if (text.contains("formaDePago"))   {
	 	                    text = text.replace("formaDePago", " " + formaDePago);
	 	                    r.setText(text, 0); 
		 	           }
	 	             
	 	            }
	             }
	        }
			
			
			if(sinDetalle.equals("2")) {
				table = doc.getTables().get(2);
				row = table.getRow(0);
				cell=row.getCell(5);setCelda(cell,"Arial",8,3,"2b5079"," ",false);
				cell=row.getCell(6);setCelda(cell,"Arial",8,2,"2b5079"," ",false);
				cell=row.getCell(7);setCelda(cell,"Arial",8,3,"2b5079"," ",false);
				cell=row.getCell(8);setCelda(cell,"Arial",8,3,"2b5079"," ",false);
				cell=row.getCell(9);setCelda(cell,"Arial",8,3,"2b5079"," ",false);
				cell=row.getCell(10);setCelda(cell,"Arial",8,2,"2b5079","TOTAL",false);
				cell=row.getCell(11);setCelda(cell,"Arial",8,3,"2b5079"," ",false);
				
				
				Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
				Long numDec = dec.get(idMoneda);
				if(numDec==null) {
					numDec = (long)1;
				}
				switch(numDec.toString()) {
				 case "0": myformatdouble = new DecimalFormat("#,##0"); break;
				 case "2": myformatdouble = new DecimalFormat("#,##0.00"); break;
				 case "4": myformatdouble = new DecimalFormat("#,##0.0000"); break;
				 case "6": myformatdouble = new DecimalFormat("#,##0.000000"); break;
				 default:  break;
				}
				
		        
				//table.createRow();
				row = table.getRow(2);
				cell=row.getCell(10);setCelda(cell,"Arial",8,3,"2b5079",myformatdouble.format(totalPrecio-auxRestar),true);
			
			}
			
			
			if(sinDetalle.equals("1")) {
				table = doc.getTables().get(2);
				doc.removeBodyElement(doc.getPosOfTable(table));
			}
			
			
			
			
			
			// Write the output to a file word
			FileOutputStream fileOut = new FileOutputStream(tmp);
			doc.write(fileOut);
			fileOut.close();

				// 1) Load DOCX into XWPFDocument
				InputStream is = new FileInputStream(tmp);
				XWPFDocument document = new XWPFDocument(is);
				is.close();
				// 2) Prepare Pdf options
				PdfOptions options = PdfOptions.create().fontEncoding("iso-8859-15");
				// 3) Convert XWPFDocument to Pdf
				OutputStream out = new FileOutputStream(tmp);
				PdfConverter.getInstance().convert(document, out, options);
				out.close();

				String archivoPdf = "CotizaArriendo_"+cotizacion.numero+".pdf";
				
				if(selectGrupos.length()>0) {
					archivoPdf = "CotizaArrVta_"+cotizacion.numero+".pdf";
					path = db+"/"+archivoPdf;
					Archivos.grabarArchivo(tmp, path);
					Cotizacion.modifyXCampo(con, db, "pdfArrVta", archivoPdf, id_cotizacion);
					
				}else {
					path = db+"/"+archivoPdf;
					Archivos.grabarArchivo(tmp, path);
					Cotizacion.modifyXCampo(con, db, "pdfArriendo", archivoPdf, id_cotizacion);
				}
				
				
				
				return(archivoPdf);
				
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return("0");
	}
	
	public static String generaPdfCotizaVenta(Connection con, String db, Long id_cotizacion, Map<String,String> mapDiccionario, Double tasaCambio, 
			Cotizacion cotizacion, Map<String,String> mapPermiso, String sinDetalle, String tasaIva){
		
		
		List<CotizaDetalle> detalleCoti = CotizaDetalle.allPorIdCotizacion(con, db,id_cotizacion);
		Cliente cliente = Cliente.find(con, db, cotizacion.getId_cliente());
		String nickCliente = "";
		String nombreCliente = "";
		String rutCliente = "";
		if(cliente!=null) {
			nickCliente = cliente.getNickName();
			nombreCliente = cliente.getNombre();
			rutCliente = cliente.getRut();
		}
		if(nombreCliente.length()>2) {
			nickCliente = nombreCliente + " ("+nickCliente+")";
		}
		Proyecto proyecto = Proyecto.find(con, db, cotizacion.getId_proyecto());
		String nickProyecto = "";
		if(proyecto!=null) {
			nickProyecto = proyecto.getNickName();
		}
		
		EmisorTributario emisorTributario = models.tables.EmisorTributario.find(con, db);
		 
		
		File tmp = TempFile.createTempFile("tmp","null");
		
		
		try {
			String path = db + "/formatos/cotizaVenta.docx";
			InputStream formato = Archivos.leerArchivo(path);
			XWPFDocument doc = new XWPFDocument(formato);
			formato.close();
		
			XWPFTable table = null;
			XWPFTableRow row = null;
			XWPFTableCell cell = null;
			
			table = doc.getTables().get(0);
			
			row=table.getRow(1);cell=row.getCell(2);
			setCelda(cell,"Arial",10,2,"2b5079","Fecha: "+Fechas.DDMMAA(cotizacion.fecha),false);
			
			row=table.getRow(2);cell=row.getCell(2);
			setCelda(cell,"Arial",10,2,"2b5079","Numero: "+cotizacion.numero.toString(),false);
			
			table= doc.getTables().get(1);
			
			cell=table.getRow(0).getCell(1);
			setCelda(cell,"Arial",10,1,"2b5079",nickCliente,false);
			
			cell=table.getRow(1).getCell(1);
			setCelda(cell,"Arial",10,1,"2b5079",rutCliente,false);
			
			cell=table.getRow(1).getCell(3);
			setCelda(cell,"Arial",10,1,"2b5079",nickProyecto,false);
			
			
			if(table.getNumberOfRows()<5) {
				cell=table.getRow(2).getCell(0);
				setCelda(cell,"Arial",10,1,"2b5079","",false);
			}else {
				cell=table.getRow(2).getCell(1);
				setCelda(cell,"Arial",10,1,"2b5079",cliente.giro,false);
				
				cell=table.getRow(3).getCell(1);
				setCelda(cell,"Arial",10,1,"2b5079",cliente.direccion,false);
				
				cell=table.getRow(4).getCell(1);
				setCelda(cell,"Arial",10,1,"2b5079",cliente.contactoFactura + "   TELEFONO: " + cliente.fonoContacto + "   EMAIL: " + cliente.mailFactura,false);
			}
			
			Double totalPrecio=(double)0;
			Double sumKG=(double)0;
			Double sumM2=(double)0;
			Double sumCant=(double)0;
			Long idMoneda = (long) 1;
			
			int contFilasTabla = 0;
			table = doc.getTables().get(2);
			
			if(mapPermiso.get("parametro.escondeLosM2")!=null && mapPermiso.get("parametro.escondeLosM2").equals("1")) {
				cell=table.getRow(0).getCell(8);setCelda(cell,"Arial",8,3,"2b5079","",false);
			}
			
			table.createRow();
			for(int i=0; i<detalleCoti.size(); i++) {
				if((long)detalleCoti.get(i).esVenta == (long)1) {
					
					contFilasTabla++;
					
					idMoneda = detalleCoti.get(i).getId_moneda();
					String codigo = detalleCoti.get(i).getCodigo();
					String equipo = detalleCoti.get(i).getEquipo();
					String unidad = detalleCoti.get(i).getUnidad();
					String cantidad = detalleCoti.get(i).getCantidad();
					String moneda = detalleCoti.get(i).getMoneda();
					String puVenta = detalleCoti.get(i).getPrecioReposicion();
					String totalVenta = detalleCoti.get(i).getTotalVenta();
					String totalKg = detalleCoti.get(i).getTotalKG();
					String totalM2 = detalleCoti.get(i).getTotalM2();
					
	    			if(totalVenta == null || totalVenta.trim().length()<=0) {
	    				totalVenta = "0";
	    			}
	    			if(totalKg == null || totalKg.trim().length()<=0) {
	    				totalKg = "0";
	    			}
	    			if(totalM2 == null || totalM2.trim().length()<=0) {
	    				totalM2 = "0";
	    			}
	    			if(cantidad == null || cantidad.trim().length()<=0) {
	    				cantidad = "0";
	    			}
	    			
	   				totalPrecio += Double.parseDouble(totalVenta.replaceAll(",", "").trim());
	   				sumKG += Double.parseDouble(totalKg.replaceAll(",", "").trim());
	   				sumM2 += Double.parseDouble(totalM2.replaceAll(",", "").trim());
	   				sumCant += Double.parseDouble(cantidad.replaceAll(",", "").trim());
	   				
					row = table.getRow(contFilasTabla);
					cell=row.getCell(0);setCelda(cell,"Arial",8,1,"2b5079",codigo,false);
    				cell=row.getCell(1);setCelda(cell,"Arial",8,1,"2b5079",equipo,false);
    				cell=row.getCell(2);setCelda(cell,"Arial",8,2,"2b5079",unidad,false);
    				cell=row.getCell(3);setCelda(cell,"Arial",8,3,"2b5079",cantidad,false);
    				cell=row.getCell(4);setCelda(cell,"Arial",8,2,"2b5079",moneda,false);
    				cell=row.getCell(5);setCelda(cell,"Arial",8,3,"2b5079",puVenta,false);
    				cell=row.getCell(6);setCelda(cell,"Arial",8,3,"2b5079",totalVenta,false);
    				cell=row.getCell(7);setCelda(cell,"Arial",8,3,"2b5079",totalKg,false);
    				if( !(mapPermiso.get("parametro.escondeLosM2")!=null && mapPermiso.get("parametro.escondeLosM2").equals("1")) ) {
    					cell=row.getCell(8);setCelda(cell,"Arial",8,3,"2b5079",totalM2,false);
    				}
					table.createRow();
				}
			}
			
			
			Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
			Long numDec = dec.get(idMoneda);
			if(numDec==null) {
				numDec = (long)1;
			}
			switch(numDec.toString()) {
			 case "0": myformatdouble = new DecimalFormat("#,##0"); break;
			 case "2": myformatdouble = new DecimalFormat("#,##0.00"); break;
			 case "4": myformatdouble = new DecimalFormat("#,##0.0000"); break;
			 case "6": myformatdouble = new DecimalFormat("#,##0.000000"); break;
			 default:  break;
			}
			
			
			
			row = table.getRow(contFilasTabla+2);
			
			cell=row.getCell(1);setCelda(cell,"Arial",8,1,"2b5079","TOTALES",true);
			cell=row.getCell(3);setCelda(cell,"Arial",8,3,"2b5079",myformatdouble.format(sumCant),true);
			cell=row.getCell(6);setCelda(cell,"Arial",8,3,"2b5079",myformatdouble.format(totalPrecio),true);
			cell=row.getCell(7);setCelda(cell,"Arial",8,3,"2b5079",myformatdouble.format(sumKG),true);
			if( !(mapPermiso.get("parametro.escondeLosM2")!=null && mapPermiso.get("parametro.escondeLosM2").equals("1")) ) {
				cell=row.getCell(8);setCelda(cell,"Arial",8,3,"2b5079",myformatdouble.format(sumM2),true);
			}
			
			Double subTotal = totalPrecio;
	        
	        if(cotizacion.getDctoVenta() == null) {
	        	cotizacion.setDctoVenta((double)0);
	        }
	        
	       
	        Double totalDcto = subTotal * cotizacion.getDctoVenta();
	        Double totalNeto = subTotal - totalDcto;
	        
	        Double tasaIvaDbl = emisorTributario.getTasaIva()/100;
	        
	        if(mapPermiso.get("parametro.ivaPorBodega")!=null && mapPermiso.get("parametro.ivaPorBodega").equals("1") && cotizacion.getId_bodegaEmpresa()>0) {
	        	BodegaEmpresa bodegaEmpresa = BodegaEmpresa.findXIdBodega(con, db, cotizacion.getId_bodegaEmpresa());
	        	if(bodegaEmpresa!=null) {
	        		Double aux = bodegaEmpresa.getIvaBodega();
	        		if(aux > 0) {
	        			tasaIvaDbl = bodegaEmpresa.getIvaBodega();
	        		} else {
	        			tasaIvaDbl = Double.parseDouble(tasaIva.replaceAll("%", "").trim().replaceAll(",",""))/100;
	        		}
	        	} else {
	        		tasaIvaDbl = Double.parseDouble(tasaIva.replaceAll("%", "").trim().replaceAll(",",""))/100;
	        	}
	        }
	        
	        String auxTasaIva = DecimalFormato.formato(tasaIvaDbl * 100, (long)2) + " %";
	        
	        
	        Double totalIva = totalNeto * tasaIvaDbl;
	        Double total = totalNeto * (1+tasaIvaDbl);
	        
	        table = doc.getTables().get(3);
	        
			cell=table.getRow(0).getCell(2);
			setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(subTotal),false);
			
			if(totalDcto!=0) {
				cell=table.getRow(1).getCell(1);
				setCelda(cell,"Arial",10,1,"000000","DESCUENTO "+myformatdouble2.format(cotizacion.getDctoVenta()*100)+" %",false);
				
    			cell=table.getRow(1).getCell(2);
    			setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(totalDcto),false);
    			
			}else {
				cell=table.getRow(1).getCell(1);
				setCelda(cell,"Arial",10,1,"000000"," ",false);
			}
			cell=table.getRow(2).getCell(2);
			setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(totalNeto),false);
			
			
			if(!db.equals("madaMontax")) {
				
				if(!db.equals("madaAndinaMontajes")) {
					
					cell=table.getRow(3).getCell(1);
					String aux = cell.getText()+" "+auxTasaIva;
					setCelda(cell,"Arial",10,1,"2b5079",aux,false);
					
					cell=table.getRow(3).getCell(2);
					setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(totalIva),false);
					
					cell=table.getRow(4).getCell(2);
					setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(total),false);
				}else {
					cell=table.getRow(3).getCell(2);
					setCelda(cell,"Arial",10,3,"2b5079","",false);
					
					cell=table.getRow(4).getCell(2);
					setCelda(cell,"Arial",10,3,"2b5079","",false);
					
					cell=table.getRow(3).getCell(1);
					setCelda(cell,"Arial",10,3,"2b5079","",false);
					
					cell=table.getRow(4).getCell(1);
					setCelda(cell,"Arial",10,3,"2b5079","",false);
				}
				
				cell=table.getRow(0).getCell(0);
    			setCelda(cell,"Arial",10,1,"2b5079","NOTAS:\n"+cotizacion.getObservaciones(),false);
    			
			}else {
				
				cell=table.getRow(0).getCell(0);
    			setCelda(cell,"Arial",10,1,"2b5079","NOTAS:\n"+cotizacion.getObservaciones(),false);
    			
				myformatdouble = new DecimalFormat("#,##0");
				table = doc.getTables().get(4);
				
    			cell=table.getRow(0).getCell(2);
    			setCelda(cell,"Arial",10,3,"2b5079",myformatdouble2.format(tasaCambio),false);
    			
    			cell=table.getRow(1).getCell(2);
    			setCelda(cell,"Arial",10,3,"2b5079","$ "+myformatdouble.format(totalNeto * tasaCambio),false);
    			
    			cell=table.getRow(2).getCell(2);
    			setCelda(cell,"Arial",10,3,"2b5079","$ "+myformatdouble.format(totalNeto * tasaCambio * tasaIvaDbl),false);
    			
    			cell=table.getRow(3).getCell(2);
    			setCelda(cell,"Arial",10,3,"2b5079","$ "+myformatdouble.format(totalNeto * tasaCambio * (1+tasaIvaDbl)),false);
    			
    			
			}
			
			Usuario comercial = Usuario.findXIdUser(con, db, cotizacion.getId_comercial());
    		String nombreComercial = comercial.getNombre();
    		String telefonosComercial = comercial.getFono();
    		String correoComercial = comercial.getEmail();
    		String nameSucursal = cotizacion.getNameSucursal();
    		String formaDePago = cliente.getFormaDePago();
    		
    		for (XWPFParagraph p : doc.getParagraphs()) {
	             for (XWPFRun r : p.getRuns()) {
	                 String text = r.getText(0);
	 	            if(text!=null){
		 	            if (text.contains("nombreComercial"))   {
		 	                    text = text.replace("nombreComercial", " " + nombreComercial);
		 	                    r.setText(text, 0); 
		 	            }
		 	           if (text.contains("telefonosComercial"))   {
		 	                    text = text.replace("telefonosComercial", " " + telefonosComercial);
		 	                    r.setText(text, 0); 
		 	            }
		 	            if (text.contains("correoComercial"))   {
	 	                    text = text.replace("correoComercial", " " + correoComercial);
	 	                    r.setText(text, 0); 
		 	            }
		 	           if (text.contains("nameSucursal"))   {
	 	                    text = text.replace("nameSucursal", " " + nameSucursal);
	 	                    r.setText(text, 0); 
		 	           }
		 	           if (text.contains("formaDePago"))   {
	 	                    text = text.replace("formaDePago", " " + formaDePago);
	 	                    r.setText(text, 0); 
		 	           }
	 	              
	 	             
	 	            }
	             }
	        }
			
			if(sinDetalle.equals("1")) {
				table = doc.getTables().get(2);
				doc.removeBodyElement(doc.getPosOfTable(table));
			}
			
			// Write the output to a file word
			FileOutputStream fileOut = new FileOutputStream(tmp);
			doc.write(fileOut);
			fileOut.close();

				// 1) Load DOCX into XWPFDocument
				InputStream is = new FileInputStream(tmp);
				XWPFDocument document = new XWPFDocument(is);
				is.close();
				// 2) Prepare Pdf options
				PdfOptions options = PdfOptions.create().fontEncoding("iso-8859-15");
				// 3) Convert XWPFDocument to Pdf
				OutputStream out = new FileOutputStream(tmp);
				PdfConverter.getInstance().convert(document, out, options);
				out.close();

				String archivoPdf = "CotizaVenta_"+cotizacion.numero+".pdf";
				path = db+"/"+archivoPdf;
				Archivos.grabarArchivo(tmp, path);
				Cotizacion.modifyXCampo(con, db, "pdfVenta", archivoPdf, id_cotizacion);
				
				return(archivoPdf);
				
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return("0");
	}
	
	public static String generaPdfCotizaArrVta(Connection con, String db, Long id_cotizacion, Map<String,String> mapDiccionario, Cotizacion cotizacion, 
			Map<String,String> mapPermiso, String sinDetalle, String tasaIva){
		
		
		List<CotizaDetalle> detalleCoti = CotizaDetalle.allPorIdCotizacion(con, db,id_cotizacion);
		Cliente cliente = Cliente.find(con, db, cotizacion.getId_cliente());
		String nickCliente = "";
		String nombreCliente = "";
		String rutCliente = "";
		if(cliente!=null) {
			nickCliente = cliente.getNickName();
			nombreCliente = cliente.getNombre();
			rutCliente = cliente.getRut();
		}
		if(nombreCliente.length()>2) {
			nickCliente = nombreCliente + " ("+nickCliente+")";
		}
		Proyecto proyecto = Proyecto.find(con, db, cotizacion.getId_proyecto());
		String nickProyecto = "";
		if(proyecto!=null) {
			nickProyecto = proyecto.getNickName();
		}
		
		EmisorTributario emisorTributario = models.tables.EmisorTributario.find(con, db);
		 
		
		File tmp = TempFile.createTempFile("tmp","null");
		
		
		try {
			String path = db + "/formatos/cotizaArrVta.docx";
			InputStream formato = Archivos.leerArchivo(path);
			XWPFDocument doc = new XWPFDocument(formato);
			formato.close();
		
			XWPFTable table = null;
			XWPFTableRow row = null;
			XWPFTableCell cell = null;
			
			table = doc.getTables().get(0);
			
			row=table.getRow(1);cell=row.getCell(2);
			setCelda(cell,"Arial",10,2,"2b5079","Fecha: "+Fechas.DDMMAA(cotizacion.fecha),false);
			
			row=table.getRow(2);cell=row.getCell(2);
			setCelda(cell,"Arial",10,2,"2b5079","Numero: "+cotizacion.numero.toString(),false);
			
			table= doc.getTables().get(1);
			
			cell=table.getRow(0).getCell(1);
			setCelda(cell,"Arial",10,1,"2b5079",nickCliente,false);
			
			cell=table.getRow(1).getCell(1);
			setCelda(cell,"Arial",10,1,"2b5079",rutCliente,false);
			
			cell=table.getRow(1).getCell(3);
			setCelda(cell,"Arial",10,1,"2b5079",nickProyecto,false);
			
			if(table.getNumberOfRows()<5) {
				cell=table.getRow(2).getCell(0);
				setCelda(cell,"Arial",10,1,"2b5079","",false);
			}else {
				cell=table.getRow(2).getCell(1);
				setCelda(cell,"Arial",10,1,"2b5079",cliente.giro,false);
				
				cell=table.getRow(3).getCell(1);
				setCelda(cell,"Arial",10,1,"2b5079",cliente.direccion,false);
				
				cell=table.getRow(4).getCell(1);
				setCelda(cell,"Arial",10,1,"2b5079",cliente.contactoFactura + "   TELEFONO: " + cliente.fonoContacto + "   EMAIL: " + cliente.mailFactura,false);
			}
			
			Double totalPrecioArr = (double)0;
			Double totalPrecioVta = (double)0;
			Double sumKG=(double)0;
			Double sumM2=(double)0;
			Double sumCant=(double)0;
			Long idMoneda = (long) 1;
			Double totalPrecioVenta = (double)0;
			Double totalReposicion = (double)0;
			
			String moneda = "";
			
			int contFilasTabla = 0;
			table = doc.getTables().get(2);
			
			if(mapPermiso.get("parametro.escondeLosM2")!=null && mapPermiso.get("parametro.escondeLosM2").equals("1")) {
				cell=table.getRow(0).getCell(12);setCelda(cell,"Arial",8,3,"2b5079","",false);
			}
			
			table.createRow();
			for(int i=0; i<detalleCoti.size(); i++) {
				
					
					contFilasTabla++;
					
					idMoneda = detalleCoti.get(i).getId_moneda();
					String codigo = detalleCoti.get(i).getCodigo();
					String equipo = detalleCoti.get(i).getEquipo();
					String unidad = detalleCoti.get(i).getUnidad();
					String cantidad = detalleCoti.get(i).getCantidad();
					moneda = detalleCoti.get(i).getMoneda();
					String puVenta = detalleCoti.get(i).getPrecioReposicion();
					String unidadArriendo = detalleCoti.get(i).getUnidadTiempo();
					String puArriendo = detalleCoti.get(i).getPrecioArriendo();
					String permanencia = detalleCoti.get(i).getPermanencia().toString();
					String totalArriendo = detalleCoti.get(i).getTotalArriendo();
					String parcialVenta = detalleCoti.get(i).getTotalVenta();
					String totalKg = detalleCoti.get(i).getTotalKG();
					String totalM2 = detalleCoti.get(i).getTotalM2();
					
	    			if(totalArriendo == null || totalArriendo.trim().length()<=0) {
	    				totalArriendo = "0";
	    			}
	    			if(totalKg == null || totalKg.trim().length()<=0) {
	    				totalKg = "0";
	    			}
	    			if(totalM2 == null || totalM2.trim().length()<=0) {
	    				totalM2 = "0";
	    			}
	    			if(cantidad == null || cantidad.trim().length()<=0) {
	    				cantidad = "0";
	    			}
	    			
	    			if(puVenta == null || cantidad.trim().length()<=0) {
	    				puVenta = "0";
	    			}
	    			
	   				totalPrecioArr += Double.parseDouble(totalArriendo.replaceAll(",", "").trim());
	   				totalPrecioVta += Double.parseDouble(parcialVenta.replaceAll(",", "").trim());
	   				sumKG += Double.parseDouble(totalKg.replaceAll(",", "").trim());
	   				sumM2 += Double.parseDouble(totalM2.replaceAll(",", "").trim());
	   				sumCant += Double.parseDouble(cantidad.replaceAll(",", "").trim());
	   				
	   				Double puVentaAux = Double.parseDouble(puVenta.replaceAll(",", "").trim());
	   				Double cantidadAux = Double.parseDouble(cantidad.replaceAll(",", "").trim());
	   				totalPrecioVenta +=  puVentaAux * cantidadAux;
	   				
	   				if(Double.parseDouble(totalArriendo.replaceAll(",", "").trim()) > 0) {
	   					totalReposicion += Double.parseDouble(puVenta.replaceAll(",", "").trim()) * cantidadAux;
	   				}
	   				
	   				
					row = table.getRow(contFilasTabla);
					cell=row.getCell(0);setCelda(cell,"Arial",8,1,"2b5079",codigo,false);
					cell=row.getCell(1);setCelda(cell,"Arial",8,1,"2b5079",equipo,false);
					cell=row.getCell(2);setCelda(cell,"Arial",8,2,"2b5079",unidad,false);
					cell=row.getCell(3);setCelda(cell,"Arial",8,3,"2b5079",cantidad,false);
					cell=row.getCell(4);setCelda(cell,"Arial",8,2,"2b5079",moneda,false);
					cell=row.getCell(5);setCelda(cell,"Arial",8,3,"2b5079",puVenta,false);
					cell=row.getCell(6);setCelda(cell,"Arial",8,2,"2b5079",unidadArriendo,false);
					cell=row.getCell(7);setCelda(cell,"Arial",8,3,"2b5079",puArriendo,false);
					cell=row.getCell(8);setCelda(cell,"Arial",8,3,"2b5079",permanencia,false);
					cell=row.getCell(9);setCelda(cell,"Arial",8,3,"2b5079",parcialVenta,false);
					cell=row.getCell(10);setCelda(cell,"Arial",8,3,"2b5079",totalArriendo,false);
					cell=row.getCell(11);setCelda(cell,"Arial",8,3,"2b5079",totalKg,false);
					if( !(mapPermiso.get("parametro.escondeLosM2")!=null && mapPermiso.get("parametro.escondeLosM2").equals("1")) ) {
						cell=row.getCell(12);setCelda(cell,"Arial",8,3,"2b5079",totalM2,false);
					}
					table.createRow();
				}
			
			
			Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
			Long numDec = dec.get(idMoneda);
			if(numDec==null) {
				numDec = (long)1;
			}
			switch(numDec.toString()) {
			 case "0": myformatdouble = new DecimalFormat("#,##0"); break;
			 case "2": myformatdouble = new DecimalFormat("#,##0.00"); break;
			 case "4": myformatdouble = new DecimalFormat("#,##0.0000"); break;
			 case "6": myformatdouble = new DecimalFormat("#,##0.000000"); break;
			 default:  break;
			}
			

	        
			//table.createRow();
			row = table.getRow(contFilasTabla+2);
			
			cell=row.getCell(1);setCelda(cell,"Arial",8,1,"2b5079","TOTALES",true);
			cell=row.getCell(3);setCelda(cell,"Arial",8,3,"2b5079",myformatdouble.format(sumCant),true);
			cell=row.getCell(9);setCelda(cell,"Arial",8,3,"2b5079",myformatdouble.format(totalPrecioVta),true);
			cell=row.getCell(10);setCelda(cell,"Arial",8,3,"2b5079",myformatdouble.format(totalPrecioArr),true);
			cell=row.getCell(11);setCelda(cell,"Arial",8,3,"2b5079",myformatdouble.format(sumKG),true);
			if( !(mapPermiso.get("parametro.escondeLosM2")!=null && mapPermiso.get("parametro.escondeLosM2").equals("1")) ) {
				cell=row.getCell(12);setCelda(cell,"Arial",8,3,"2b5079",myformatdouble.format(sumM2),true);
			}
			
			Double subTotalArr = totalPrecioArr;
			Double subTotalVta = totalPrecioVta;
	        
	        if(cotizacion.getDctoVenta() == null) {
	        	cotizacion.setDctoVenta((double)0);
	        }
	        
	        if(cotizacion.getDctoArriendo() == null) {
	        	cotizacion.setDctoArriendo((double)0);
	        }
	        
	        Double totalDctoVta = subTotalVta * cotizacion.getDctoVenta();
	        Double totalNetoVta = subTotalVta - totalDctoVta;
	        
	        Double tasaIvaVta = emisorTributario.getTasaIva()/100;
	        if(mapPermiso.get("parametro.ivaPorBodega")!=null && mapPermiso.get("parametro.ivaPorBodega").equals("1") && cotizacion.getId_bodegaEmpresa()>0) {
	        	BodegaEmpresa bodegaEmpresa = BodegaEmpresa.findXIdBodega(con, db, cotizacion.getId_bodegaEmpresa());
	        	if(bodegaEmpresa!=null) {
	        		Double aux = bodegaEmpresa.getIvaBodega();
	        		if(aux > 0) {
	        			tasaIvaVta = bodegaEmpresa.getIvaBodega();
	        		} else {
	        			tasaIvaVta = Double.parseDouble(tasaIva.replaceAll("%", "").trim().replaceAll(",",""))/100;
	        		}
	        	} else {
	        		tasaIvaVta = Double.parseDouble(tasaIva.replaceAll("%", "").trim().replaceAll(",",""))/100;
	        	}
	        }
	        
	        String auxTasaIva = DecimalFormato.formato(tasaIvaVta * 100, (long)2) + " %";
	        
	        Double totalIvaVta = totalNetoVta * tasaIvaVta;
	        Double totalVta = totalNetoVta * (1+tasaIvaVta);
	        
	        Double totalDctoArr = subTotalArr * cotizacion.getDctoArriendo();
	        Double totalNetoArr = subTotalArr - totalDctoArr;
	        
	        Double tasaIvaArr = emisorTributario.getTasaIva()/100;
	        
	        if(mapPermiso.get("parametro.ivaPorBodega")!=null && mapPermiso.get("parametro.ivaPorBodega").equals("1") && cotizacion.getId_bodegaEmpresa()>0) {
	        	BodegaEmpresa bodegaEmpresa = BodegaEmpresa.findXIdBodega(con, db, cotizacion.getId_bodegaEmpresa());
	        	if(bodegaEmpresa!=null) {
	        		Double aux = bodegaEmpresa.getIvaBodega();
	        		if(aux > 0) {
	        			tasaIvaArr = bodegaEmpresa.getIvaBodega();
	        		} else {
	        			tasaIvaArr = Double.parseDouble(tasaIva.replaceAll("%", "").trim().replaceAll(",",""))/100;
	        		}
	        	} else {
	        		tasaIvaArr = Double.parseDouble(tasaIva.replaceAll("%", "").trim().replaceAll(",",""))/100;
	        	}
	        }
	        
	        auxTasaIva = DecimalFormato.formato(tasaIvaArr * 100, (long)2) + " %";
	        
	        Double totalIvaArr = totalNetoArr * tasaIvaArr;
	        Double totalArr = totalNetoArr * (1+tasaIvaArr);
	        
	        table = doc.getTables().get(3);
			cell=table.getRow(0).getCell(2);setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(subTotalVta),false);
			
			if(totalDctoVta!=0 || totalDctoArr!=0) {
				String dctoVta = "---";
				String dctoArr = "---";
				if(totalDctoVta>0) {
					dctoVta = myformatdouble2.format(cotizacion.dctoVenta*100)+"%";
				}
				if(totalDctoArr>0) {
					dctoArr = myformatdouble2.format(cotizacion.dctoArriendo*100)+"%";
				}
				
				
				cell=table.getRow(1).getCell(1);setCelda(cell,"Arial",10,1,"000000","DESCUENTOS: "+dctoVta+" "+dctoArr,false);
    			cell=table.getRow(1).getCell(2);setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(totalDctoVta),false);
			}else {
				cell=table.getRow(1).getCell(1);setCelda(cell,"Arial",10,1,"000000"," ",false);
			}
			
			cell=table.getRow(2).getCell(2);setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(totalNetoVta),false);
			
			if(!db.equals("madaAndinaMontajes")) {
				
				
				cell=table.getRow(3).getCell(1);
				String aux = cell.getText()+" "+auxTasaIva;
				setCelda(cell,"Arial",10,1,"2b5079",aux,false);
				
				cell=table.getRow(3).getCell(2);setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(totalIvaVta),false);
				cell=table.getRow(4).getCell(2);setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(totalVta),false);
			}else {
				cell=table.getRow(3).getCell(2);setCelda(cell,"Arial",10,3,"2b5079","",false);
				cell=table.getRow(4).getCell(2);setCelda(cell,"Arial",10,3,"2b5079","",false);
				cell=table.getRow(3).getCell(1);setCelda(cell,"Arial",10,3,"2b5079","",false);
				cell=table.getRow(4).getCell(1);setCelda(cell,"Arial",10,3,"2b5079","",false);
			}
			
			
			
	   
			cell=table.getRow(0).getCell(3);setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(subTotalArr),false);
			
			if(totalDctoArr!=0) {
    			cell=table.getRow(1).getCell(3);setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(totalDctoArr),false);
			}
			
			cell=table.getRow(2).getCell(3);setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(totalNetoArr),false);
			
			if(!db.equals("madaAndinaMontajes")) {
				cell=table.getRow(3).getCell(3);setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(totalIvaArr),false);
				cell=table.getRow(4).getCell(3);setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(totalArr),false);
			}else {
				cell=table.getRow(3).getCell(3);setCelda(cell,"Arial",10,3,"2b5079","",false);
				cell=table.getRow(4).getCell(3);setCelda(cell,"Arial",10,3,"2b5079","",false);
				cell=table.getRow(3).getCell(2);setCelda(cell,"Arial",10,3,"2b5079","",false);
				cell=table.getRow(4).getCell(2);setCelda(cell,"Arial",10,3,"2b5079","",false);
			}
			
			
			cell=table.getRow(0).getCell(0);
			setCelda(cell,"Arial",10,1,"2b5079","NOTAS:\n"+cotizacion.getObservaciones(),false);
			
			
			
			table = doc.getTables().get(4);
			
			if(!db.equals("madaAndinaMontajes")) {
				cell=table.getRow(0).getCell(1);setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(totalVta+totalArr),false);
			}else {
				cell=table.getRow(0).getCell(1);setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(totalNetoVta+totalNetoArr),false);
			}
			
			
			
			Usuario comercial = Usuario.findXIdUser(con, db, cotizacion.getId_comercial());
    		String nombreComercial = comercial.getNombre();
    		String telefonosComercial = comercial.getFono();
    		String correoComercial = comercial.getEmail();
    		String valorTotalReposicion = myformatdouble.format(totalReposicion);
    		String nameSucursal = cotizacion.getNameSucursal();
    		String formaDePago = cliente.getFormaDePago();
    		
    		for (XWPFParagraph p : doc.getParagraphs()) {
	             for (XWPFRun r : p.getRuns()) {
	                 String text = r.getText(0);
	 	            if(text!=null){
		 	            if (text.contains("nombreComercial"))   {
		 	                    text = text.replace("nombreComercial", " " + nombreComercial);
		 	                    r.setText(text, 0); 
		 	            }
		 	           if (text.contains("telefonosComercial"))   {
		 	                    text = text.replace("telefonosComercial", " " + telefonosComercial);
		 	                    r.setText(text, 0); 
		 	            }
		 	            if (text.contains("correoComercial"))   {
	 	                    text = text.replace("correoComercial", " " + correoComercial);
	 	                    r.setText(text, 0); 
		 	            }
		 	           if (text.contains("valorTotalReposicion"))   {
	 	                    text = text.replace("valorTotalReposicion", " " + valorTotalReposicion);
	 	                    r.setText(text, 0); 
		 	            }
		 	          if (text.contains("nameSucursal"))   {
	 	                    text = text.replace("nameSucursal", " " + nameSucursal);
	 	                    r.setText(text, 0); 
		 	           }
		 	           if (text.contains("formaDePago"))   {
	 	                    text = text.replace("formaDePago", " " + formaDePago);
	 	                    r.setText(text, 0); 
		 	           }
	 	              
	 	             
	 	            }
	             }
	        }
			
			if(sinDetalle.equals("1")) {
				table = doc.getTables().get(2);
				doc.removeBodyElement(doc.getPosOfTable(table));
			}
			
			
			// Write the output to a file word
			FileOutputStream fileOut = new FileOutputStream(tmp);
			doc.write(fileOut);
			fileOut.close();

				// 1) Load DOCX into XWPFDocument
				InputStream is = new FileInputStream(tmp);
				XWPFDocument document = new XWPFDocument(is);
				is.close();
				// 2) Prepare Pdf options
				PdfOptions options = PdfOptions.create().fontEncoding("iso-8859-15");
				// 3) Convert XWPFDocument to Pdf
				OutputStream out = new FileOutputStream(tmp);
				PdfConverter.getInstance().convert(document, out, options);
				out.close();

				String archivoPdf = "CotizaArrVta_"+cotizacion.numero+".pdf";
				path = db+"/"+archivoPdf;
				Archivos.grabarArchivo(tmp, path);
				Cotizacion.modifyXCampo(con, db, "pdfArrVta", archivoPdf, id_cotizacion);
				
				return(archivoPdf);
				
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return("0");
	}
	
	public static Long generaPdfCotizaArrVtaResumenConDetalle(Connection con, String db, Map<String,String> mapPermiso, Map<String,String> mapDiccionario, List<List<String>> resumen, 
			Cliente cliente, String listadoIdCoti, Proyecto proyecto, String esPorSucursal, String id_sucursal, Usuario usuario){
		
		String nickCliente = "";
		String nombreCliente = "";
		String rutCliente = "";
		if(cliente!=null) {
			nickCliente = cliente.getNickName();
			nombreCliente = cliente.getNombre();
			rutCliente = cliente.getRut();
		}
		if(nombreCliente.length()>2) {
			nickCliente = nombreCliente + " ("+nickCliente+")";
		}
		
		//String nickProyecto = "";
		
		EmisorTributario emisorTributario = models.tables.EmisorTributario.find(con, db);
		 
		
		File tmp = TempFile.createTempFile("tmp","null");
		
		
		try {
			String path = db + "/formatos/cotizaArrVta.docx";
			InputStream formato = Archivos.leerArchivo(path);
			XWPFDocument doc = new XWPFDocument(formato);
			formato.close();
		
			XWPFTable table = null;
			XWPFTableRow row = null;
			XWPFTableCell cell = null;
			
			table = doc.getTables().get(0);
			
			row=table.getRow(1);cell=row.getCell(2);
			setCelda(cell,"Arial",10,2,"2b5079","Fecha: "+Fechas.hoy().getFechaStrDDMMAA(),false);
			
			row=table.getRow(2);cell=row.getCell(2);
			setCelda(cell,"Arial",10,2,"2b5079","Numeros: "+listadoIdCoti,false);
			
			table= doc.getTables().get(1);
			
			cell=table.getRow(0).getCell(1);
			setCelda(cell,"Arial",10,1,"2b5079",nickCliente,false);
			
			cell=table.getRow(1).getCell(1);
			setCelda(cell,"Arial",10,1,"2b5079",rutCliente,false);
			
			cell=table.getRow(1).getCell(3);
			setCelda(cell,"Arial",10,1,"2b5079",proyecto.getNickName(),false);
			
			if(table.getNumberOfRows()<5) {
				cell=table.getRow(2).getCell(0);
				setCelda(cell,"Arial",10,1,"2b5079","",false);
			}else {
				cell=table.getRow(2).getCell(1);
				setCelda(cell,"Arial",10,1,"2b5079",cliente.giro,false);
				
				cell=table.getRow(3).getCell(1);
				setCelda(cell,"Arial",10,1,"2b5079",cliente.direccion,false);
				
				cell=table.getRow(4).getCell(1);
				setCelda(cell,"Arial",10,1,"2b5079",cliente.contactoFactura + "   TELEFONO: " + cliente.fonoContacto + "   EMAIL: " + cliente.mailFactura,false);
			}
			
			Double totalPrecioArr = (double)0;
			Double totalPrecioVta = (double)0;
			Double sumKG=(double)0;
			Double sumM2=(double)0;
			Double sumCant=(double)0;
			Long idMoneda = (long) 1;
			Double totalReposicion = (double)0;
			
			String moneda = "";
			
			int contFilasTabla = -1;
			
			
			table = doc.getTables().get(2);
			
			if(mapPermiso.get("parametro.escondeLosM2")!=null && mapPermiso.get("parametro.escondeLosM2").equals("1")) {
				cell=table.getRow(0).getCell(12);setCelda(cell,"Arial",8,3,"2b5079","",false);
			}
			
			
			String id_coti = "";
			String listNumerosCoti = "";
			
			for(int i=0; i<resumen.size(); i++) {
				
				if(!resumen.get(i).get(21).equals(id_coti)) {
					table.createRow();
					id_coti = resumen.get(i).get(21);
					listNumerosCoti += resumen.get(i).get(2)+", ";
					
					
					contFilasTabla += 2;
					//contFilasTabla++;
					row = table.getRow(contFilasTabla);
					cell=row.getCell(0);setCelda(cell,"Arial",8,1,"0E5222","Coti: "+resumen.get(i).get(2),true);
					cell=row.getCell(1);setCelda(cell,"Arial",8,1,"0E5222",resumen.get(i).get(4),true);
					cell=row.getCell(2);setCelda(cell,"Arial",8,2,"2b5079","",false);
					cell=row.getCell(3);setCelda(cell,"Arial",8,3,"2b5079","",false);
					cell=row.getCell(4);setCelda(cell,"Arial",8,2,"2b5079","",false);
					cell=row.getCell(5);setCelda(cell,"Arial",8,3,"2b5079","",false);
					cell=row.getCell(6);setCelda(cell,"Arial",8,2,"2b5079","",false);
					cell=row.getCell(7);setCelda(cell,"Arial",8,3,"2b5079","",false);
					cell=row.getCell(8);setCelda(cell,"Arial",8,3,"2b5079","",false);
					cell=row.getCell(9);setCelda(cell,"Arial",8,3,"2b5079","",false);
					cell=row.getCell(10);setCelda(cell,"Arial",8,3,"2b5079","",false);
					cell=row.getCell(11);setCelda(cell,"Arial",8,3,"2b5079","",false);
					cell=row.getCell(12);setCelda(cell,"Arial",8,3,"2b5079","",false);
					table.createRow();
				}
					
					contFilasTabla++;
					
					idMoneda = Long.parseLong(resumen.get(i).get(22));
					
					String codigo = resumen.get(i).get(5);
					String equipo = resumen.get(i).get(6);
					String unidad = resumen.get(i).get(7);
					String cantidad = resumen.get(i).get(8);
					moneda = resumen.get(i).get(10);
					String puVenta = resumen.get(i).get(11);
					String unidadArriendo = resumen.get(i).get(12);
					String puArriendo = resumen.get(i).get(13);
					String permanencia = resumen.get(i).get(14);
					String totalArriendo = resumen.get(i).get(16);
					String totalVenta = resumen.get(i).get(17);
					String totalKg = resumen.get(i).get(18);
					String totalM2 = resumen.get(i).get(19);
					String totRepos = resumen.get(i).get(15);
					
	    			if(totalArriendo == null || totalArriendo.trim().length()<=0) {
	    				totalArriendo = "0";
	    			}
	    			if(totalKg == null || totalKg.trim().length()<=0) {
	    				totalKg = "0";
	    			}
	    			if(totalM2 == null || totalM2.trim().length()<=0) {
	    				totalM2 = "0";
	    			}
	    			if(cantidad == null || cantidad.trim().length()<=0) {
	    				cantidad = "0";
	    			}
	    			
	    			if(puVenta == null || cantidad.trim().length()<=0) {
	    				puVenta = "0";
	    			}
	    			
	   				totalPrecioArr += Double.parseDouble(totalArriendo.replaceAll(",", "").trim());
	   				totalPrecioVta += Double.parseDouble(totalVenta.replaceAll(",", "").trim());
	   				sumKG += Double.parseDouble(totalKg.replaceAll(",", "").trim());
	   				sumM2 += Double.parseDouble(totalM2.replaceAll(",", "").trim());
	   				sumCant += Double.parseDouble(cantidad.replaceAll(",", "").trim());
	   				
	   				totalReposicion += Double.parseDouble(totRepos.replaceAll(",", "").trim());
	   				
	   				
					row = table.getRow(contFilasTabla);
					cell=row.getCell(0);setCelda(cell,"Arial",8,1,"2b5079",codigo,false);
					cell=row.getCell(1);setCelda(cell,"Arial",8,1,"2b5079",equipo,false);
					cell=row.getCell(2);setCelda(cell,"Arial",8,2,"2b5079",unidad,false);
					cell=row.getCell(3);setCelda(cell,"Arial",8,3,"2b5079",cantidad,false);
					cell=row.getCell(4);setCelda(cell,"Arial",8,2,"2b5079",moneda,false);
					cell=row.getCell(5);setCelda(cell,"Arial",8,3,"2b5079",puVenta,false);
					cell=row.getCell(6);setCelda(cell,"Arial",8,2,"2b5079",unidadArriendo,false);
					cell=row.getCell(7);setCelda(cell,"Arial",8,3,"2b5079",puArriendo,false);
					cell=row.getCell(8);setCelda(cell,"Arial",8,3,"2b5079",permanencia,false);
					cell=row.getCell(9);setCelda(cell,"Arial",8,3,"2b5079",totalVenta,false);
					cell=row.getCell(10);setCelda(cell,"Arial",8,3,"2b5079",totalArriendo,false);
					cell=row.getCell(11);setCelda(cell,"Arial",8,3,"2b5079",totalKg,false);
					if( !(mapPermiso.get("parametro.escondeLosM2")!=null && mapPermiso.get("parametro.escondeLosM2").equals("1")) ) {
						cell=row.getCell(12);setCelda(cell,"Arial",8,3,"2b5079",totalM2,false);
					}
					table.createRow();
				}
			
			listNumerosCoti = listNumerosCoti.substring(0,listNumerosCoti.length()-2);
			
			Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
			Long numDec = dec.get(idMoneda);
			if(numDec==null) {
				numDec = (long)1;
			}
			switch(numDec.toString()) {
			 case "0": myformatdouble = new DecimalFormat("#,##0"); break;
			 case "2": myformatdouble = new DecimalFormat("#,##0.00"); break;
			 case "4": myformatdouble = new DecimalFormat("#,##0.0000"); break;
			 case "6": myformatdouble = new DecimalFormat("#,##0.000000"); break;
			 default:  break;
			}
			

	        
			//table.createRow();
			row = table.getRow(contFilasTabla+2);
			
			cell=row.getCell(1);setCelda(cell,"Arial",8,1,"2b5079","TOTALES",true);
			cell=row.getCell(3);setCelda(cell,"Arial",8,3,"2b5079",myformatdouble.format(sumCant),true);
			cell=row.getCell(9);setCelda(cell,"Arial",8,3,"2b5079",myformatdouble.format(totalPrecioVta),true);
			cell=row.getCell(10);setCelda(cell,"Arial",8,3,"2b5079",myformatdouble.format(totalPrecioArr),true);
			cell=row.getCell(11);setCelda(cell,"Arial",8,3,"2b5079",myformatdouble.format(sumKG),true);
			if( !(mapPermiso.get("parametro.escondeLosM2")!=null && mapPermiso.get("parametro.escondeLosM2").equals("1")) ) {
				cell=row.getCell(12);setCelda(cell,"Arial",8,3,"2b5079",myformatdouble.format(sumM2),true);
			}
			
			Double subTotalArr = totalPrecioArr;
			Double subTotalVta = totalPrecioVta;
	        
	        
	        Double totalNetoVta = subTotalVta;
	        Double tasaIvaVta = emisorTributario.getTasaIva()/100;
	        Double totalIvaVta = totalNetoVta * tasaIvaVta;
	        Double totalVta = totalNetoVta * (1+tasaIvaVta);
	        
	        Double totalNetoArr = subTotalArr;
	        Double tasaIvaArr = emisorTributario.getTasaIva()/100;
	        Double totalIvaArr = totalNetoArr * tasaIvaArr;
	        Double totalArr = totalNetoArr * (1+tasaIvaArr);
	        
	        table = doc.getTables().get(3);
			cell=table.getRow(0).getCell(2);setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(subTotalVta),false);
			
			cell=table.getRow(1).getCell(1);setCelda(cell,"Arial",10,1,"000000"," ",false);
			
			cell=table.getRow(2).getCell(2);setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(totalNetoVta),false);
			
			if(!db.equals("madaAndinaMontajes")) {
				cell=table.getRow(3).getCell(2);setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(totalIvaVta),false);
				cell=table.getRow(4).getCell(2);setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(totalVta),false);
			}else {
				cell=table.getRow(3).getCell(2);setCelda(cell,"Arial",10,3,"2b5079","",false);
				cell=table.getRow(4).getCell(2);setCelda(cell,"Arial",10,3,"2b5079","",false);
				cell=table.getRow(3).getCell(1);setCelda(cell,"Arial",10,3,"2b5079","",false);
				cell=table.getRow(4).getCell(1);setCelda(cell,"Arial",10,3,"2b5079","",false);
			}
			
			
			
	   
			cell=table.getRow(0).getCell(3);setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(subTotalArr),false);
			cell=table.getRow(2).getCell(3);setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(totalNetoArr),false);
			
			if(!db.equals("madaAndinaMontajes")) {
				cell=table.getRow(3).getCell(3);setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(totalIvaArr),false);
				cell=table.getRow(4).getCell(3);setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(totalArr),false);
			}else {
				cell=table.getRow(3).getCell(3);setCelda(cell,"Arial",10,3,"2b5079","",false);
				cell=table.getRow(4).getCell(3);setCelda(cell,"Arial",10,3,"2b5079","",false);
				cell=table.getRow(3).getCell(2);setCelda(cell,"Arial",10,3,"2b5079","",false);
				cell=table.getRow(4).getCell(2);setCelda(cell,"Arial",10,3,"2b5079","",false);
			}
			
			cell=table.getRow(0).getCell(0);
			setCelda(cell,"Arial",10,1,"2b5079","",false);
			
			table = doc.getTables().get(4);
			
			if(!db.equals("madaAndinaMontajes")) {
				cell=table.getRow(0).getCell(1);setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(totalVta+totalArr),false);
			}else {
				cell=table.getRow(0).getCell(1);setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(totalNetoVta+totalNetoArr),false);
			}
			
			
			//Usuario comercial = Usuario.findXIdUser(con, db, cotizacion.getId_comercial());
    		String nombreComercial = usuario.getNombre();
    		String telefonosComercial = usuario.getFono();
    		String correoComercial = usuario.getEmail();
    		String valorTotalReposicion = myformatdouble.format(totalReposicion);
    		String nameSucursal = "";
    		String formaDePago = cliente.getFormaDePago();
    		
    		for (XWPFParagraph p : doc.getParagraphs()) {
	             for (XWPFRun r : p.getRuns()) {
	                 String text = r.getText(0);
	 	            if(text!=null){
		 	            if (text.contains("nombreComercial"))   {
		 	                    text = text.replace("nombreComercial", " " + nombreComercial);
		 	                    r.setText(text, 0); 
		 	            }
		 	           if (text.contains("telefonosComercial"))   {
		 	                    text = text.replace("telefonosComercial", " " + telefonosComercial);
		 	                    r.setText(text, 0); 
		 	            }
		 	            if (text.contains("correoComercial"))   {
	 	                    text = text.replace("correoComercial", " " + correoComercial);
	 	                    r.setText(text, 0); 
		 	            }
		 	           if (text.contains("valorTotalReposicion"))   {
	 	                    text = text.replace("valorTotalReposicion", " " + valorTotalReposicion);
	 	                    r.setText(text, 0); 
		 	            }
		 	          if (text.contains("nameSucursal"))   {
	 	                    text = text.replace("nameSucursal", " " + nameSucursal);
	 	                    r.setText(text, 0); 
		 	           }
		 	           if (text.contains("formaDePago"))   {
	 	                    text = text.replace("formaDePago", " " + formaDePago);
	 	                    r.setText(text, 0); 
		 	           }
	 	             
	 	            }
	             }
	        }
			
    		table = doc.getTables().get(0);
			row=table.getRow(2);cell=row.getCell(2);
			setCelda(cell,"Arial",10,2,"2b5079","Numeros: "+listNumerosCoti,false);
			
			
			// Write the output to a file word
			FileOutputStream fileOut = new FileOutputStream(tmp);
			doc.write(fileOut);
			fileOut.close();

				// 1) Load DOCX into XWPFDocument
				InputStream is = new FileInputStream(tmp);
				XWPFDocument document = new XWPFDocument(is);
				is.close();
				// 2) Prepare Pdf options
				PdfOptions options = PdfOptions.create().fontEncoding("iso-8859-15");
				// 3) Convert XWPFDocument to Pdf
				OutputStream out = new FileOutputStream(tmp);
				PdfConverter.getInstance().convert(document, out, options);
				out.close();
				
				String neto = myformatdouble.format(totalNetoVta+totalNetoArr);
				String total = myformatdouble.format(totalVta+totalArr);
				String iva = myformatdouble.format(totalVta+totalArr - totalNetoVta-totalNetoArr);
				
				CotiBiblioteca cotiBiblioteca = new CotiBiblioteca(cliente.nickName, proyecto.nickName, listNumerosCoti,
						listadoIdCoti, id_sucursal, neto, iva, total);
				Long id_cotiBiblioteca = CotiBiblioteca.create(con, db, cotiBiblioteca);
				
				String archivoPdf = "CotizaResumenConDet"+id_cotiBiblioteca+".pdf";
				
				CotiBiblioteca.updatePdfConDetalle(con, db, archivoPdf, id_cotiBiblioteca);
				
				path = db+"/"+archivoPdf;
				Archivos.grabarArchivo(tmp, path);
				
				
				return(id_cotiBiblioteca);
				
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return((long)0);
	}
	
	public static File generaPdfCotizaArrVtaResumenSinDet(Connection con, String db, Map<String,String> mapPermiso, Map<String,String> mapDiccionario, List<List<String>> resumen, 
			Cliente cliente, String listadoIdCoti, Proyecto proyecto, String esPorSucursal, String id_sucursal, Usuario usuario){
		
		
		Map<String,List<Double>> mapSinDetalle = new HashMap<String,List<Double>>();
		for(List<String> l: resumen) {
			String key = l.get(2)+"_&&_"+l.get(4)+"_&&_"+l.get(10)+"_&&_"+l.get(20)+"_&&_"+l.get(22);
			
			List<Double> x = mapSinDetalle.get(key);
			if(x == null ) {
				List<Double> aux = new ArrayList<Double>();
				aux.add(Double.parseDouble(l.get(17).replaceAll(",", ""))); // 0 venta
				aux.add(Double.parseDouble(l.get(16).replaceAll(",", ""))); // 1 arriendo
				aux.add(Double.parseDouble(l.get(18).replaceAll(",", ""))); // 2 KG
				aux.add(Double.parseDouble(l.get(19).replaceAll(",", ""))); // 3 M2
				aux.add(Double.parseDouble(l.get(15).replaceAll(",", ""))); // 4 reposicion
				mapSinDetalle.put(key, aux);
			}else {
				List<Double> aux = new ArrayList<Double>();
				aux.add(x.get(0) + Double.parseDouble(l.get(17).replaceAll(",", "")));	// 0 venta
				aux.add(x.get(1) + Double.parseDouble(l.get(16).replaceAll(",", "")));	// 1 arriendo
				aux.add(x.get(2) + Double.parseDouble(l.get(18).replaceAll(",", "")));	// 2 KG
				aux.add(x.get(3) + Double.parseDouble(l.get(19).replaceAll(",", "")));	// 3 M2
				aux.add(x.get(4) + Double.parseDouble(l.get(15).replaceAll(",", "")));	// 4 reposicion
				mapSinDetalle.put(key, aux);
			}
		}
		
		List<List<String>> listSinDet = new ArrayList<List<String>>();
		mapSinDetalle.forEach((k,v)->{
			String x[] = k.split("_&&_");
			List<String> aux = new ArrayList<String>();
			aux.add(x[0]);														// 0 Nro coti
			aux.add(x[1]);														// 1 observacion
			aux.add(x[2]);														// 2 moneda
			aux.add(DecimalFormato.formato(v.get(0), Long.parseLong(x[3])));	// 3 total venta
			aux.add(DecimalFormato.formato(v.get(1), Long.parseLong(x[3])));	// 4 total arriendo
			aux.add(DecimalFormato.formato(v.get(2), (long)2));					// 5 total kg
			aux.add(DecimalFormato.formato(v.get(3), (long)2));					// 6 total m2
			aux.add(x[3]);														// 7 nro decimales
			aux.add(x[4]);														// 8 id_moneda
			aux.add(DecimalFormato.formato(v.get(4), Long.parseLong(x[3])));	// 9 total reposicion
			listSinDet.add(aux);
		});
		
		String nickCliente = "";
		String nombreCliente = "";
		String rutCliente = "";
		if(cliente!=null) {
			nickCliente = cliente.getNickName();
			nombreCliente = cliente.getNombre();
			rutCliente = cliente.getRut();
		}
		if(nombreCliente.length()>2) {
			nickCliente = nombreCliente + " ("+nickCliente+")";
		}
		
		//String nickProyecto = "";
		
		EmisorTributario emisorTributario = models.tables.EmisorTributario.find(con, db);
		 
		
		File tmp = TempFile.createTempFile("tmp","null");
		
		
		try {
			String path = db + "/formatos/cotizaArrVtaResumen.docx";
			InputStream formato = Archivos.leerArchivo(path);
			XWPFDocument doc = new XWPFDocument(formato);
			formato.close();
		
			XWPFTable table = null;
			XWPFTableRow row = null;
			XWPFTableCell cell = null;
			
			table = doc.getTables().get(0);
			
			row=table.getRow(1);cell=row.getCell(2);
			setCelda(cell,"Arial",10,2,"2b5079","Fecha: "+Fechas.hoy().getFechaStrDDMMAA(),false);
			
			row=table.getRow(2);cell=row.getCell(2);
			setCelda(cell,"Arial",10,2,"2b5079","Numeros: "+listadoIdCoti,false);
			
			table= doc.getTables().get(1);
			
			cell=table.getRow(0).getCell(1);
			setCelda(cell,"Arial",10,1,"2b5079",nickCliente,false);
			
			cell=table.getRow(1).getCell(1);
			setCelda(cell,"Arial",10,1,"2b5079",rutCliente,false);
			
			cell=table.getRow(1).getCell(3);
			setCelda(cell,"Arial",10,1,"2b5079",proyecto.getNickName(),false);
			
			if(table.getNumberOfRows()<5) {
				cell=table.getRow(2).getCell(0);
				setCelda(cell,"Arial",10,1,"2b5079","",false);
			}else {
				cell=table.getRow(2).getCell(1);
				setCelda(cell,"Arial",10,1,"2b5079",cliente.giro,false);
				
				cell=table.getRow(3).getCell(1);
				setCelda(cell,"Arial",10,1,"2b5079",cliente.direccion,false);
				
				cell=table.getRow(4).getCell(1);
				setCelda(cell,"Arial",10,1,"2b5079",cliente.contactoFactura + "   TELEFONO: " + cliente.fonoContacto + "   EMAIL: " + cliente.mailFactura,false);
			}
			
			Double totalPrecioArr = (double)0;
			Double totalPrecioVta = (double)0;
			Double totalReposicion = (double)0;
			Double sumKG=(double)0;
			Double sumM2=(double)0;
			Long idMoneda = (long) 1;
			String listNumerosCoti = "";
			
			int contFilasTabla = 0;
			
			table = doc.getTables().get(2);
			if(mapPermiso.get("parametro.escondeLosM2")!=null && mapPermiso.get("parametro.escondeLosM2").equals("1")) {
				cell=table.getRow(0).getCell(6);
				setCelda(cell,"Arial",8,3,"2b5079","",false);
			}
			for(int i=0; i<listSinDet.size(); i++) {
				
					contFilasTabla++;
					
					idMoneda = Long.parseLong(listSinDet.get(i).get(7));
					listNumerosCoti += " " + listSinDet.get(i).get(0);
					
					String nroCoti = listSinDet.get(i).get(0);
					String observacion = listSinDet.get(i).get(1);
					String moneda = listSinDet.get(i).get(2);
					String totalArriendo = listSinDet.get(i).get(4);
					String totalVenta = listSinDet.get(i).get(3);
					String reposicion = listSinDet.get(i).get(9);
					
					String totalKg = listSinDet.get(i).get(5);
					String totalM2 = listSinDet.get(i).get(6);
					
	    			
	   				totalPrecioArr += Double.parseDouble(totalArriendo.replaceAll(",", "").trim());
	   				totalPrecioVta += Double.parseDouble(totalVenta.replaceAll(",", "").trim());
	   				totalReposicion += Double.parseDouble(reposicion.replaceAll(",", "").trim());
	   				
	   				sumKG += Double.parseDouble(totalKg.replaceAll(",", "").trim());
	   				sumM2 += Double.parseDouble(totalM2.replaceAll(",", "").trim());
	   				
	   				
					row = table.getRow(contFilasTabla);
					cell=row.getCell(0);setCelda(cell,"Arial",8,1,"2b5079",nroCoti,false);
					cell=row.getCell(1);setCelda(cell,"Arial",8,1,"2b5079",observacion,false);
					cell=row.getCell(2);setCelda(cell,"Arial",8,2,"2b5079",moneda,false);
					cell=row.getCell(3);setCelda(cell,"Arial",8,3,"2b5079",totalVenta,false);
					cell=row.getCell(4);setCelda(cell,"Arial",8,3,"2b5079",totalArriendo,false);
					cell=row.getCell(5);setCelda(cell,"Arial",8,3,"2b5079",totalKg,false);
					if( !(mapPermiso.get("parametro.escondeLosM2")!=null && mapPermiso.get("parametro.escondeLosM2").equals("1")) ) {
						cell=row.getCell(6);setCelda(cell,"Arial",8,3,"2b5079",totalM2,false);
					}
					table.createRow();
				}
			
			
			Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
			Long numDec = dec.get(idMoneda);
			if(numDec==null) {
				numDec = (long)1;
			}
			switch(numDec.toString()) {
			 case "0": myformatdouble = new DecimalFormat("#,##0"); break;
			 case "2": myformatdouble = new DecimalFormat("#,##0.00"); break;
			 case "4": myformatdouble = new DecimalFormat("#,##0.0000"); break;
			 case "6": myformatdouble = new DecimalFormat("#,##0.000000"); break;
			 default:  break;
			}
			

	        
			//table.createRow();
			row = table.getRow(contFilasTabla+1);
			
			cell=row.getCell(1);setCelda(cell,"Arial",8,1,"2b5079","TOTALES",true);
			cell=row.getCell(2);setCelda(cell,"Arial",8,3,"2b5079","",true);
			cell=row.getCell(3);setCelda(cell,"Arial",8,3,"2b5079",myformatdouble.format(totalPrecioVta),true);
			cell=row.getCell(4);setCelda(cell,"Arial",8,3,"2b5079",myformatdouble.format(totalPrecioArr),true);
			cell=row.getCell(5);setCelda(cell,"Arial",8,3,"2b5079",myformatdouble.format(sumKG),true);
			if( !(mapPermiso.get("parametro.escondeLosM2")!=null && mapPermiso.get("parametro.escondeLosM2").equals("1")) ) {
				cell=row.getCell(6);setCelda(cell,"Arial",8,3,"2b5079",myformatdouble.format(sumM2),true);
			}
			
			Double subTotalArr = totalPrecioArr;
			Double subTotalVta = totalPrecioVta;
	        
	        
	        Double totalNetoVta = subTotalVta;
	        Double tasaIvaVta = emisorTributario.getTasaIva()/100;
	        Double totalIvaVta = totalNetoVta * tasaIvaVta;
	        Double totalVta = totalNetoVta * (1+tasaIvaVta);
	        
	        Double totalNetoArr = subTotalArr;
	        Double tasaIvaArr = emisorTributario.getTasaIva()/100;
	        Double totalIvaArr = totalNetoArr * tasaIvaArr;
	        Double totalArr = totalNetoArr * (1+tasaIvaArr);
	        
	        table = doc.getTables().get(3);
			cell=table.getRow(0).getCell(2);setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(subTotalVta),false);
			
			cell=table.getRow(1).getCell(1);setCelda(cell,"Arial",10,1,"000000"," ",false);
			
			cell=table.getRow(2).getCell(2);setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(totalNetoVta),false);
			
			if(!db.equals("madaAndinaMontajes")) {
				cell=table.getRow(3).getCell(2);setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(totalIvaVta),false);
				cell=table.getRow(4).getCell(2);setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(totalVta),false);
			}else {
				cell=table.getRow(3).getCell(2);setCelda(cell,"Arial",10,3,"2b5079","",false);
				cell=table.getRow(4).getCell(2);setCelda(cell,"Arial",10,3,"2b5079","",false);
				cell=table.getRow(3).getCell(1);setCelda(cell,"Arial",10,3,"2b5079","",false);
				cell=table.getRow(4).getCell(1);setCelda(cell,"Arial",10,3,"2b5079","",false);
			}
			
			
			
	   
			cell=table.getRow(0).getCell(3);setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(subTotalArr),false);
			cell=table.getRow(2).getCell(3);setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(totalNetoArr),false);
			
			if(!db.equals("madaAndinaMontajes")) {
				cell=table.getRow(3).getCell(3);setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(totalIvaArr),false);
				cell=table.getRow(4).getCell(3);setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(totalArr),false);
			}else {
				cell=table.getRow(3).getCell(3);setCelda(cell,"Arial",10,3,"2b5079","",false);
				cell=table.getRow(4).getCell(3);setCelda(cell,"Arial",10,3,"2b5079","",false);
				cell=table.getRow(3).getCell(2);setCelda(cell,"Arial",10,3,"2b5079","",false);
				cell=table.getRow(4).getCell(2);setCelda(cell,"Arial",10,3,"2b5079","",false);
			}
			
			cell=table.getRow(0).getCell(0);
			setCelda(cell,"Arial",10,1,"2b5079","",false);
			
			table = doc.getTables().get(4);
			
			if(!db.equals("madaAndinaMontajes")) {
				cell=table.getRow(0).getCell(1);setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(totalVta+totalArr),false);
			}else {
				cell=table.getRow(0).getCell(1);setCelda(cell,"Arial",10,3,"2b5079",myformatdouble.format(totalNetoVta+totalNetoArr),false);
			}
			
			
			//Usuario comercial = Usuario.findXIdUser(con, db, cotizacion.getId_comercial());
    		String nombreComercial = usuario.getNombre();
    		String telefonosComercial = usuario.getFono();
    		String correoComercial = usuario.getEmail();
    		String valorTotalReposicion = myformatdouble.format(totalReposicion);
    		String nameSucursal = "";
    		String formaDePago = cliente.getFormaDePago();
    		
    		for (XWPFParagraph p : doc.getParagraphs()) {
	             for (XWPFRun r : p.getRuns()) {
	                 String text = r.getText(0);
	 	            if(text!=null){
		 	            if (text.contains("nombreComercial"))   {
		 	                    text = text.replace("nombreComercial", " " + nombreComercial);
		 	                    r.setText(text, 0); 
		 	            }
		 	           if (text.contains("telefonosComercial"))   {
		 	                    text = text.replace("telefonosComercial", " " + telefonosComercial);
		 	                    r.setText(text, 0); 
		 	            }
		 	            if (text.contains("correoComercial"))   {
	 	                    text = text.replace("correoComercial", " " + correoComercial);
	 	                    r.setText(text, 0); 
		 	            }
		 	           if (text.contains("valorTotalReposicion"))   {
	 	                    text = text.replace("valorTotalReposicion", " " + valorTotalReposicion);
	 	                    r.setText(text, 0); 
		 	            }
		 	          if (text.contains("nameSucursal"))   {
	 	                    text = text.replace("nameSucursal", " " + nameSucursal);
	 	                    r.setText(text, 0); 
		 	           }
		 	           if (text.contains("formaDePago"))   {
	 	                    text = text.replace("formaDePago", " " + formaDePago);
	 	                    r.setText(text, 0); 
		 	           }
	 	             
	 	            }
	             }
	        }
			
    		table = doc.getTables().get(0);
			row=table.getRow(2);cell=row.getCell(2);
			setCelda(cell,"Arial",10,2,"2b5079","Numeros: "+listNumerosCoti,false);
			
			
			
			// Write the output to a file word
			FileOutputStream fileOut = new FileOutputStream(tmp);
			doc.write(fileOut);
			fileOut.close();

				// 1) Load DOCX into XWPFDocument
				InputStream is = new FileInputStream(tmp);
				XWPFDocument document = new XWPFDocument(is);
				is.close();
				// 2) Prepare Pdf options
				PdfOptions options = PdfOptions.create().fontEncoding("iso-8859-15");
				// 3) Convert XWPFDocument to Pdf
				OutputStream out = new FileOutputStream(tmp);
				PdfConverter.getInstance().convert(document, out, options);
				out.close();
				
				return(tmp);
				
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return(null);
	}
	
	private static void setCelda (XWPFTableCell cell,String fontFamily,int fontSize,int alingH,String colorRGB,String text,boolean bold) {
		cell.removeParagraph(0);
		XWPFParagraph paragraph =null;
		if(text==null) text=""; else if(text.trim().length()==0) text="";
		paragraph = cell.addParagraph();
		if(alingH==3) {
			paragraph.setAlignment(ParagraphAlignment.RIGHT);
		}else if (alingH==2) {
			paragraph.setAlignment(ParagraphAlignment.CENTER);
		}else {
			paragraph.setAlignment(ParagraphAlignment.LEFT);
		}
		cell.setVerticalAlignment(XWPFVertAlign.CENTER);
		
		XWPFRun celda = paragraph.createRun();
		celda.setFontFamily(fontFamily);
		celda.setFontSize(fontSize);
		celda.setColor(colorRGB);
		celda.setText(text);
		celda.setBold(bold);
    }
	
	
	public static List<String> cotiValidarPlantillaExcel (Connection con, String db, File file) {
		List<String> mensaje = new ArrayList<String>();
		DecimalFormat df = new DecimalFormat("#");
	    df.setMaximumFractionDigits(8);
	    
        mensaje.add("EQUIPOS QUE NO EXISTEN EN MADA: <br>");
        
        try {
            Workbook libro = WorkbookFactory.create(file);
            Sheet hoja1 = libro.getSheetAt(0);
            Row row = null;
            Cell cell = null;
            boolean archivoNoCorresponde = false;
            row = hoja1.getRow(1);
            if(row==null || archivoNoCorresponde) {
            	archivoNoCorresponde = true;
            }else {
            	for(int i=1; i<13; i++) {
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
            boolean flag = true;
            int fila = 2;
            int x = 2;
            while (row!=null && cell !=null ) {
            	fila++;
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
			mensaje.set(0,"ARCHIVO NO CORRESPONDE A LA PLANTILLA");
        	return (mensaje);
        }
		return(mensaje);
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
            
            row = hoja1.getRow(1);
            if(row != null) {
            	cell = row.getCell(2);
            }
            
            int x = 2;
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
                			for(int i=1; i<13; i++) {
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
		}
		return(lista);
	}
	
	//  0 GRUPO
	//  1 CODIGO
	//  2 EQUIPO
	//  3 UNIDAD
	//  4 CANT
	//  5 SOLO VENTA
	//  6 MONEDA
	//  7 P.UNITARIO VTA/REPOS
	//  8 UNIDAD ALQUILER
	//  9 TASA
	// 10 ARR/VTA P.UNITARIO ARR
	// 11 PERMANENCIA
	
	
}














