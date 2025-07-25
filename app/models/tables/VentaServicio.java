package models.tables;

import java.io.ByteArrayInputStream;
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
import java.text.DecimalFormatSymbols;
import java.util.*;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.TempFile;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.poi.xwpf.usermodel.XWPFTableCell.XWPFVertAlign;

import models.utilities.Archivos;
import models.utilities.Fechas;

public class VentaServicio {
	public Long id;
	public Long id_userMada;
	public Long id_operador;
	public Long id_bodegaEmpresa;
	public Long id_equipo;
	public Long id_servicio;
	public String fecha;
	public String detalle;
	public String horaIni;
	public String horaTer;
	public String lecturaIni;
	public String lecturaTer;
	public String cantidad;
	public String observaciones;
	public String docAnexo;
	public String reportPDF;
	public String firmaPDFoperador;
	public String firmaPDFautorizador;
	
	
	public String nomUserAdam;
	public String nomOperador;
	public String nomBodegaEmpresa;
	public String codEquipo;
	public String nomEquipo;
	public String codServicio;
	public String nomServicio;
	public Long id_unidadServicio;
	public String unidadServicio;
	public Long aux_huella;
	public Long id_claseServicio;
	public String nomClaseServicio;
	
	public Long id_cotiOdo;
	public Long nroCotiOdo;
	
	public String albumFotos;
	
	public String nameSucursal;
	
	
	public VentaServicio(Long id, Long id_userMada, Long id_operador, Long id_bodegaEmpresa, Long id_equipo,
			Long id_servicio, String fecha, String detalle, String horaIni, String horaTer, String lecturaIni,
			String lecturaTer, String cantidad, String observaciones, String docAnexo, String reportPDF,
			String firmaPDFoperador, String firmaPDFautorizador, String nomUserAdam, String nomOperador,
			String nomBodegaEmpresa, String codEquipo, String nomEquipo, String codServicio, String nomServicio,
			Long id_unidadServicio, String unidadServicio, Long aux_huella, Long id_claseServicio, String nomClaseServicio, Long id_cotiOdo, Long nroCotiOdo,
			String albumFotos, String nameSucursal) {
		super();
		this.id = id;
		this.id_userMada = id_userMada;
		this.id_operador = id_operador;
		this.id_bodegaEmpresa = id_bodegaEmpresa;
		this.id_equipo = id_equipo;
		this.id_servicio = id_servicio;
		this.fecha = fecha;
		this.detalle = detalle;
		this.horaIni = horaIni;
		this.horaTer = horaTer;
		this.lecturaIni = lecturaIni;
		this.lecturaTer = lecturaTer;
		this.cantidad = cantidad;
		this.observaciones = observaciones;
		this.docAnexo = docAnexo;
		this.reportPDF = reportPDF;
		this.firmaPDFoperador = firmaPDFoperador;
		this.firmaPDFautorizador = firmaPDFautorizador;
		this.nomUserAdam = nomUserAdam;
		this.nomOperador = nomOperador;
		this.nomBodegaEmpresa = nomBodegaEmpresa;
		this.codEquipo = codEquipo;
		this.nomEquipo = nomEquipo;
		this.codServicio = codServicio;
		this.nomServicio = nomServicio;
		this.id_unidadServicio = id_unidadServicio;
		this.unidadServicio = unidadServicio;
		this.aux_huella = aux_huella;
		this.id_claseServicio = id_claseServicio;
		this.nomClaseServicio = nomClaseServicio;
		this.id_cotiOdo = id_cotiOdo;
		this.nroCotiOdo = nroCotiOdo;
		this.albumFotos = albumFotos;
		this.nameSucursal = nameSucursal;
	}

	public VentaServicio(Long id_bodegaEmpresa, Long id_servicio, String cantidad) {
		super();
		this.id_bodegaEmpresa = id_bodegaEmpresa;
		this.id_servicio = id_servicio;
		this.cantidad = cantidad;
	}

	public VentaServicio() {
		super();
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId_userMada() {
		return id_userMada;
	}
	public void setId_userMada(Long id_userMada) {
		this.id_userMada = id_userMada;
	}
	public Long getId_operador() {
		return id_operador;
	}
	public void setId_operador(Long id_operador) {
		this.id_operador = id_operador;
	}
	public Long getId_bodegaEmpresa() {
		return id_bodegaEmpresa;
	}
	public void setId_bodegaEmpresa(Long id_bodegaEmpresa) {
		this.id_bodegaEmpresa = id_bodegaEmpresa;
	}
	public Long getId_equipo() {
		return id_equipo;
	}
	public void setId_equipo(Long id_equipo) {
		this.id_equipo = id_equipo;
	}
	public Long getId_servicio() {
		return id_servicio;
	}
	public void setId_servicio(Long id_servicio) {
		this.id_servicio = id_servicio;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public String getHoraIni() {
		return horaIni;
	}
	public void setHoraIni(String horaIni) {
		this.horaIni = horaIni;
	}
	public String getHoraTer() {
		return horaTer;
	}
	public void setHoraTer(String horaTer) {
		this.horaTer = horaTer;
	}
	public String getLecturaIni() {
		return lecturaIni;
	}
	public void setLecturaIni(String lecturaIni) {
		this.lecturaIni = lecturaIni;
	}
	public String getLecturaTer() {
		return lecturaTer;
	}
	public void setLecturaTer(String lecturaTer) {
		this.lecturaTer = lecturaTer;
	}
	public String getCantidad() {
		return cantidad;
	}
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getDocAnexo() {
		return docAnexo;
	}
	public void setDocAnexo(String docAnexo) {
		this.docAnexo = docAnexo;
	}
	public String getReportPDF() {
		return reportPDF;
	}
	public void setReportPDF(String reportPDF) {
		this.reportPDF = reportPDF;
	}
	public String getNomUserAdam() {
		return nomUserAdam;
	}
	public void setNomUserAdam(String nomUserAdam) {
		this.nomUserAdam = nomUserAdam;
	}
	public String getNomOperador() {
		return nomOperador;
	}
	public void setNomOperador(String nomOperador) {
		this.nomOperador = nomOperador;
	}
	public String getNomBodegaEmpresa() {
		return nomBodegaEmpresa;
	}
	public void setNomBodegaEmpresa(String nomBodegaEmpresa) {
		this.nomBodegaEmpresa = nomBodegaEmpresa;
	}
	public String getCodEquipo() {
		return codEquipo;
	}
	public void setCodEquipo(String codEquipo) {
		this.codEquipo = codEquipo;
	}
	public String getNomEquipo() {
		return nomEquipo;
	}
	public void setNomEquipo(String nomEquipo) {
		this.nomEquipo = nomEquipo;
	}
	public String getCodServicio() {
		return codServicio;
	}
	public void setCodServicio(String codServicio) {
		this.codServicio = codServicio;
	}
	public String getNomServicio() {
		return nomServicio;
	}
	public void setNomServicio(String nomServicio) {
		this.nomServicio = nomServicio;
	}
	public Long getId_unidadServicio() {
		return id_unidadServicio;
	}
	public void setId_unidadServicio(Long id_unidadServicio) {
		this.id_unidadServicio = id_unidadServicio;
	}
	public String getUnidadServicio() {
		return unidadServicio;
	}
	public void setUnidadServicio(String unidadServicio) {
		this.unidadServicio = unidadServicio;
	}
	public Long getAux_huella() {
		return aux_huella;
	}
	public void setAux_huella(Long aux_huella) {
		this.aux_huella = aux_huella;
	}
	public String getFirmaPDFoperador() {
		return firmaPDFoperador;
	}
	public void setFirmaPDFoperador(String firmaPDFoperador) {
		this.firmaPDFoperador = firmaPDFoperador;
	}
	public String getFirmaPDFautorizador() {
		return firmaPDFautorizador;
	}
	public void setFirmaPDFautorizador(String firmaPDFautorizador) {
		this.firmaPDFautorizador = firmaPDFautorizador;
	}
	public Long getId_claseServicio() {
		return id_claseServicio;
	}
	public void setId_claseServicio(Long id_claseServicio) {
		this.id_claseServicio = id_claseServicio;
	}
	public String getNomClaseServicio() {
		return nomClaseServicio;
	}
	public void setNomClaseServicio(String nomClaseServicio) {
		this.nomClaseServicio = nomClaseServicio;
	}
	public Long getId_cotiOdo() {
		return id_cotiOdo;
	}
	public void setId_cotiOdo(Long id_cotiOdo) {
		this.id_cotiOdo = id_cotiOdo;
	}
	public Long getNroCotiOdo() {
		return nroCotiOdo;
	}
	public void setNroCotiOdo(Long nroCotiOdo) {
		this.nroCotiOdo = nroCotiOdo;
	}
	public String getAlbumFotos() {
		return albumFotos;
	}
	public void setAlbumFotos(String albumFotos) {
		this.albumFotos = albumFotos;
	}
	public String getNameSucursal() {
		return nameSucursal;
	}
	public void setNameSucursal(String nameSucursal) {
		this.nameSucursal = nameSucursal;
	}

	static DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00",symbols);
	
	
	public static boolean existenVentaAsociado(Connection con, String db, Long id_cotiOdo) {
		try {
			PreparedStatement smt = con
					.prepareStatement("select id from `"+db+"`.ventaServicio where id_cotiOdo=?;");
			smt.setLong(1, id_cotiOdo);
			ResultSet rs = smt.executeQuery();
			if(rs.next()) {
				rs.close();
				smt.close();
				return(true);
			}else {
				rs.close();
				smt.close();
				return(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(false);
	}
	
	public static Long anioPrimeraVenta(Connection con, String db) {
		Fechas hoy = Fechas.hoy();
		String[] aux = hoy.getFechaStrAAMMDD().split("-");
		Long minAnio = Long.parseLong(aux[0]);
		try {
			PreparedStatement smt2 = con
					.prepareStatement("select ifnull(min(year(fecha)),?) from `"+db+"`.ventaServicio where id>0;");
			smt2.setLong(1, minAnio);
			ResultSet rs2 = smt2.executeQuery();
			if(rs2.next()) {
				minAnio = rs2.getLong(1);
			}
			rs2.close();
			smt2.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return(minAnio);
	}
	
	public static Map<Long,Double> mapTotalCantPorServ(Connection con, String db, Long id_cotiOdo){
		Map<Long,Double> map = new HashMap<Long,Double>();
		
		try {
			PreparedStatement smt2 = con
					.prepareStatement("select id_servicio, sum(cantidad) from `"+db+"`.ventaServicio where id_cotiOdo = ? group by id_servicio;");
			smt2.setLong(1, id_cotiOdo);
			ResultSet rs2 = smt2.executeQuery();
			while(rs2.next()) {
				map.put(rs2.getLong(1), rs2.getDouble(2));
			}
			rs2.close();
			smt2.close();
		
		} catch (SQLException e) {
				e.printStackTrace();
		}
		
		return(map);
	}
	
	public static String modalTrazaPorIdOtOdoIdServicio(Connection con, String db, Long id_otOdo, Long id_servicio) {
		
		OtOdo otOdo = OtOdo.find(con, db, id_otOdo);
		List<VentaServicio> allPorIdOtOdo = VentaServicio.allPorIdOtOdoAndIdServicio(con, db, otOdo.getId_cotiOdo(), id_servicio);
		
		String vista =
				"<table class='table table-sm table-hover table-bordered table-condensed table-fluid'>"+
					"<thead style='background-color: #eeeeee'>"+
						"<TR>"
							+ "<th>FAMILIA</th>"
							+ "<th>CODIGO</th>"
							+ "<th>SERVICIO</th>"
							+ "<th>UN</th>"
							+ "<th>CANT</th>"
							+ "<th>REPORT</th>"
							+ "<th>FECHA</th>"
						+ "</TR>"
					+ "</thead>"
					+ "<tbody>";
				for(int i=0;i<allPorIdOtOdo.size();i++){
					vista += 
							"<TR>"
								+ "<td style='text-align:left;'>"+allPorIdOtOdo.get(i).getNomClaseServicio()+"</td>"
								+ "<td style='text-align:left;'>"+allPorIdOtOdo.get(i).getCodServicio()+"</td>"
								+ "<td style='text-align:left;'>"+allPorIdOtOdo.get(i).getNomServicio()+"</td>"
								+ "<td style='text-align:center;'>"+allPorIdOtOdo.get(i).getUnidadServicio()+"</td>"
								+ "<td style='text-align:right;'>"+allPorIdOtOdo.get(i).getCantidad()+"</td>"
								+ "<td style='text-align:center;'>"+allPorIdOtOdo.get(i).getId()+"</td>"
								+ "<td style='text-align:center;'>"+allPorIdOtOdo.get(i).getFecha()+"</td>"
							+"</TR>";
				}
				vista += "</tbody></table>";
		return (vista);
	}
	
	public static List<VentaServicio> allPorIdOtOdoAndIdServicio(Connection con, String db, Long id_cotiOdo, Long id_servicio) {
		List<VentaServicio> lista = new ArrayList<VentaServicio>();
		try {
			PreparedStatement smt2 = con
					.prepareStatement("select "
							+ " id,"
							+ "	id_userMada, "
							+ "	id_operador,"
							+ "	id_bodegaEmpresa,"
							+ "	id_equipo,"
							+ "	id_servicio,"
							+ " fecha,"
							+ "	detalle,"
							+ "	horaIni,"
							+ "	horaTer,"
							+ "	lecturaIni,"
							+ "	lecturaTer,"
							+ "	cantidad,"
							+ "	observaciones,"
							+ "	docAnexo,"
							+ "	reportPDF,"
							+ "	firmaPDFoperador,"
							+ "	firmaPDFautorizador,"
							+ "	id_cotiOdo, "
							+ " albumFotos "
							+ " from `"+db+"`.ventaServicio"
							+ " where id_cotiOdo = ?  and id_servicio = ? "
							+ " order by fecha desc, id desc;");
			smt2.setLong(1, id_cotiOdo);
			smt2.setLong(2, id_servicio);
			ResultSet rs2 = smt2.executeQuery();
			
			Map<Long,Usuario> mapUsuario = Usuario.mapAll(con, db);
			Map<Long,OperadorServicio> mapOperador = OperadorServicio.mapAll(con, db);
			Map<Long,BodegaEmpresa> mapBodega = BodegaEmpresa.mapAll(con, db);
			Map<Long,Equipo> mapEquipo = Equipo.mapAllAll(con, db);
			Map<Long,Servicio> mapServicio = Servicio.mapAll(con, db);
			Map<Long,ClaseServicio> mapClases = ClaseServicio.mapAll(con, db);
			Map<Long,Long> mapNroCotiOdo = CotiOdo.mapIdvsNroCoti(con, db);
			
			while(rs2.next()) {
				BodegaEmpresa bodega = mapBodega.get(rs2.getLong(4));
				
				if(bodega!=null) {
					String fecha = Fechas.DDMMAA(rs2.getString(7));
					String horaIni = rs2.getString(9).substring(0,5);
					String horaTer = rs2.getString(10).substring(0,5);
					String lecturaIni = myformatdouble2.format(rs2.getDouble(11));
					String lecturaTer = myformatdouble2.format(rs2.getDouble(12));
					String cantidad = myformatdouble2.format(rs2.getDouble(13));
					Usuario usuario = mapUsuario.get(rs2.getLong(2));
					String nomUserAdam = "";
					if(usuario!=null) {
						nomUserAdam = usuario.getUserName();
					}
					OperadorServicio operador = mapOperador.get(rs2.getLong(3));
					String nomOperador = "Sin operador";
					if(operador!=null) {
						nomOperador = operador.getNombre();
					}
					
					String nomBodegaEmpresa = "";
					String nameSucursal = "";
					if(bodega!=null) {
						nomBodegaEmpresa = bodega.getNombre();
						nameSucursal = bodega.getNameSucursal();
					}
					
					Equipo equipo = mapEquipo.get(rs2.getLong(5));
					String codEquipo = "";
					String nomEquipo = "";
					if(equipo!=null) {
						codEquipo = equipo.getCodigo();
						nomEquipo = equipo.getNombre();
					}
					Servicio servicio = mapServicio.get(rs2.getLong(6));
					String codServicio = "";
					String nomServicio = "";
					Long id_unidadServicio = (long)0;
					String unidadServicio = "S/U";
					Long id_claseServicio = (long)0;
					String nomClaseServicio = "Sin Clase";
					if(servicio!=null) {
						codServicio = servicio.getCodigo();
						nomServicio = servicio.getNombre();
						id_unidadServicio = servicio.getId_unidadServicio();
						unidadServicio = servicio.getNombreUnidad();
						
						ClaseServicio clase = mapClases.get(servicio.getId_claseServicio());
						if(clase!=null) {
							id_claseServicio = clase.getId();
							nomClaseServicio = clase.getNombre();
						}
						
					}
					Long nroCoti = mapNroCotiOdo.get(rs2.getLong(19));
					if(nroCoti==null) {
						nroCoti=(long)0;
					}
					
					lista.add(new VentaServicio(
					rs2.getLong(1),rs2.getLong(2),rs2.getLong(3),rs2.getLong(4),rs2.getLong(5),rs2.getLong(6),
					fecha,rs2.getString(8),horaIni,horaTer,lecturaIni,lecturaTer,cantidad,
					rs2.getString(14),rs2.getString(15),rs2.getString(16),rs2.getString(17),rs2.getString(18),
					nomUserAdam,nomOperador,nomBodegaEmpresa,codEquipo,nomEquipo,codServicio,nomServicio,id_unidadServicio,unidadServicio,(long)0,id_claseServicio,
					nomClaseServicio,rs2.getLong(19),nroCoti, rs2.getString(20),nameSucursal));
				}
			}
				
			rs2.close();
			smt2.close();
		
		} catch (SQLException e) {
				e.printStackTrace();
		}
	
		return(lista);
	}

	public static List<VentaServicio> allEntreFechas(Connection con, String db, String desdeAAMMDD, String hastaAAMMDD, String esPorSucursal, String id_sucursal) {
		List<VentaServicio> lista = new ArrayList<VentaServicio>();
		try {
			PreparedStatement smt2 = con
					.prepareStatement("select "
							+ " id,"
							+ "	id_userMada, "
							+ "	id_operador,"
							+ "	id_bodegaEmpresa,"
							+ "	id_equipo,"
							+ "	id_servicio,"
							+ " fecha,"
							+ "	detalle,"
							+ "	horaIni,"
							+ "	horaTer,"
							+ "	lecturaIni,"
							+ "	lecturaTer,"
							+ "	cantidad,"
							+ "	observaciones,"
							+ "	docAnexo,"
							+ "	reportPDF,"
							+ "	firmaPDFoperador,"
							+ "	firmaPDFautorizador,"
							+ "	id_cotiOdo, "
							+ " albumFotos "
							+ " from `"+db+"`.ventaServicio"
							+ " where fecha between ? and ? "
							+ " order by fecha desc, id desc;");
			smt2.setString(1, desdeAAMMDD);
			smt2.setString(2, hastaAAMMDD);
			ResultSet rs2 = smt2.executeQuery();
			
			Map<Long,Usuario> mapUsuario = Usuario.mapAll(con, db);
			Map<Long,OperadorServicio> mapOperador = OperadorServicio.mapAll(con, db);
			Map<Long,BodegaEmpresa> mapBodega = BodegaEmpresa.mapAll(con, db);
			Map<Long,Equipo> mapEquipo = Equipo.mapAllAll(con, db);
			Map<Long,Servicio> mapServicio = Servicio.mapAll(con, db);
			Map<Long,ClaseServicio> mapClases = ClaseServicio.mapAll(con, db);
			Map<Long,Long> mapNroCotiOdo = CotiOdo.mapIdvsNroCoti(con, db);
			
			while(rs2.next()) {
				BodegaEmpresa bodega = mapBodega.get(rs2.getLong(4));
				
				if(bodega!=null) {
					String fecha = Fechas.DDMMAA(rs2.getString(7));
					String horaIni = rs2.getString(9).substring(0,5);
					String horaTer = rs2.getString(10).substring(0,5);
					String lecturaIni = myformatdouble2.format(rs2.getDouble(11));
					String lecturaTer = myformatdouble2.format(rs2.getDouble(12));
					String cantidad = myformatdouble2.format(rs2.getDouble(13));
					Usuario usuario = mapUsuario.get(rs2.getLong(2));
					String nomUserAdam = "";
					if(usuario!=null) {
						nomUserAdam = usuario.getUserName();
					}
					OperadorServicio operador = mapOperador.get(rs2.getLong(3));
					String nomOperador = "Sin operador";
					if(operador!=null) {
						nomOperador = operador.getNombre();
					}
					String nomBodegaEmpresa = "";
					String nameSucursal = "";
					String auxId_sucursal = "0";
					if(bodega!=null) {
						nomBodegaEmpresa = bodega.getNombre();
						nameSucursal = bodega.getNameSucursal();
						auxId_sucursal = bodega.getId_sucursal().toString();
					}
					
					Equipo equipo = mapEquipo.get(rs2.getLong(5));
					String codEquipo = "";
					String nomEquipo = "";
					if(equipo!=null) {
						codEquipo = equipo.getCodigo();
						nomEquipo = equipo.getNombre();
					}
					Servicio servicio = mapServicio.get(rs2.getLong(6));
					String codServicio = "";
					String nomServicio = "";
					Long id_unidadServicio = (long)0;
					String unidadServicio = "S/U";
					Long id_claseServicio = (long)0;
					String nomClaseServicio = "Sin Clase";
					if(servicio!=null) {
						codServicio = servicio.getCodigo();
						nomServicio = servicio.getNombre();
						id_unidadServicio = servicio.getId_unidadServicio();
						unidadServicio = servicio.getNombreUnidad();
						
						ClaseServicio clase = mapClases.get(servicio.getId_claseServicio());
						if(clase!=null) {
							id_claseServicio = clase.getId();
							nomClaseServicio = clase.getNombre();
						}
						
					}
					Long nroCoti = mapNroCotiOdo.get(rs2.getLong(19));
					if(nroCoti==null) {
						nroCoti=(long)0;
					}
					
					if(esPorSucursal.equals("1")) {
						if(auxId_sucursal.equals(id_sucursal)) {
							lista.add(new VentaServicio(
									rs2.getLong(1),rs2.getLong(2),rs2.getLong(3),rs2.getLong(4),rs2.getLong(5),rs2.getLong(6),
									fecha,rs2.getString(8),horaIni,horaTer,lecturaIni,lecturaTer,cantidad,
									rs2.getString(14),rs2.getString(15),rs2.getString(16),rs2.getString(17),rs2.getString(18),
									nomUserAdam,nomOperador,nomBodegaEmpresa,codEquipo,nomEquipo,codServicio,nomServicio,id_unidadServicio,unidadServicio,(long)0,id_claseServicio,
									nomClaseServicio,rs2.getLong(19),nroCoti,rs2.getString(20),nameSucursal));
						}
					}else {
						lista.add(new VentaServicio(
								rs2.getLong(1),rs2.getLong(2),rs2.getLong(3),rs2.getLong(4),rs2.getLong(5),rs2.getLong(6),
								fecha,rs2.getString(8),horaIni,horaTer,lecturaIni,lecturaTer,cantidad,
								rs2.getString(14),rs2.getString(15),rs2.getString(16),rs2.getString(17),rs2.getString(18),
								nomUserAdam,nomOperador,nomBodegaEmpresa,codEquipo,nomEquipo,codServicio,nomServicio,id_unidadServicio,unidadServicio,(long)0,id_claseServicio,
								nomClaseServicio,rs2.getLong(19),nroCoti,rs2.getString(20),nameSucursal));
					}
					
				}
			}
				
			rs2.close();
			smt2.close();
		
		} catch (SQLException e) {
				e.printStackTrace();
		}
	
		return(lista);
	}
	
	public static Map<Long,List<VentaServicio>> mapAllEntreFechasPorBodega(Connection con, String db, String desdeAAMMDD, String hastaAAMMDD) {
		Map<Long, List<VentaServicio>> map = new HashMap<Long, List<VentaServicio>>();
		try {
			PreparedStatement smt2 = con
					.prepareStatement("select "
							+ " id,"
							+ "	id_userMada, "
							+ "	id_operador,"
							+ "	id_bodegaEmpresa,"
							+ "	id_equipo,"
							+ "	id_servicio,"
							+ " fecha,"
							+ "	detalle,"
							+ "	horaIni,"
							+ "	horaTer,"
							+ "	lecturaIni,"
							+ "	lecturaTer,"
							+ "	cantidad,"
							+ "	observaciones,"
							+ "	docAnexo,"
							+ "	reportPDF,"
							+ "	firmaPDFoperador,"
							+ "	firmaPDFautorizador,"
							+ "	id_cotiOdo, "
							+ " albumFotos "
							+ " from `"+db+"`.ventaServicio"
							+ " where fecha between ? and ? "
							+ " order by fecha desc, id desc;");
			smt2.setString(1, desdeAAMMDD);
			smt2.setString(2, hastaAAMMDD);
			ResultSet rs2 = smt2.executeQuery();
			
			Map<Long,Usuario> mapUsuario = Usuario.mapAll(con, db);
			Map<Long,OperadorServicio> mapOperador = OperadorServicio.mapAll(con, db);
			Map<Long,BodegaEmpresa> mapBodega = BodegaEmpresa.mapAll(con, db);
			Map<Long,Equipo> mapEquipo = Equipo.mapAllAll(con, db);
			Map<Long,Servicio> mapServicio = Servicio.mapAll(con, db);
			Map<Long,ClaseServicio> mapClases = ClaseServicio.mapAll(con, db);
			Map<Long,Long> mapNroCotiOdo = CotiOdo.mapIdvsNroCoti(con, db);
			
			while(rs2.next()) {
				BodegaEmpresa bodega = mapBodega.get(rs2.getLong(4));
				
				if(bodega!=null) {
					Long id_bodegaEmpresa = rs2.getLong(4);
					String fecha = Fechas.DDMMAA(rs2.getString(7));
					String horaIni = rs2.getString(9).substring(0,5);
					String horaTer = rs2.getString(10).substring(0,5);
					String lecturaIni = myformatdouble2.format(rs2.getDouble(11));
					String lecturaTer = myformatdouble2.format(rs2.getDouble(12));
					String cantidad = myformatdouble2.format(rs2.getDouble(13));
					Usuario usuario = mapUsuario.get(rs2.getLong(2));
					String nomUserAdam = "";
					if(usuario!=null) {
						nomUserAdam = usuario.getUserName();
					}
					OperadorServicio operador = mapOperador.get(rs2.getLong(3));
					String nomOperador = "Sin operador";
					if(operador!=null) {
						nomOperador = operador.getNombre();
					}
					String nomBodegaEmpresa = "";
					String nameSucursal = "";
					if(bodega!=null) {
						nomBodegaEmpresa = bodega.getNombre();
						nameSucursal = bodega.getNameSucursal();
					}
					
					Equipo equipo = mapEquipo.get(rs2.getLong(5));
					String codEquipo = "";
					String nomEquipo = "";
					if(equipo!=null) {
						codEquipo = equipo.getCodigo();
						nomEquipo = equipo.getNombre();
					}
					Servicio servicio = mapServicio.get(rs2.getLong(6));
					String codServicio = "";
					String nomServicio = "";
					Long id_unidadServicio = (long)0;
					String unidadServicio = "S/U";
					Long id_claseServicio = (long)0;
					String nomClaseServicio = "Sin Familia";
					if(servicio!=null) {
						codServicio = servicio.getCodigo();
						nomServicio = servicio.getNombre();
						id_unidadServicio = servicio.getId_unidadServicio();
						unidadServicio = servicio.getNombreUnidad();
						
						ClaseServicio clase = mapClases.get(servicio.getId_claseServicio());
						if(clase!=null) {
							id_claseServicio = clase.getId();
							nomClaseServicio = clase.getNombre();
						}
					}
					Long nroCoti = mapNroCotiOdo.get(rs2.getLong(19));
					if(nroCoti==null) {
						nroCoti=(long)0;
					}
					
					VentaServicio aux = new VentaServicio(
							rs2.getLong(1),rs2.getLong(2),rs2.getLong(3),rs2.getLong(4),rs2.getLong(5),rs2.getLong(6),
							fecha,rs2.getString(8),horaIni,horaTer,lecturaIni,lecturaTer,cantidad,
							rs2.getString(14),rs2.getString(15),rs2.getString(16),rs2.getString(17),rs2.getString(18),
							nomUserAdam,nomOperador,nomBodegaEmpresa,codEquipo,nomEquipo,codServicio,nomServicio,id_unidadServicio,unidadServicio,(long)0,id_claseServicio,
							nomClaseServicio,rs2.getLong(19),nroCoti,rs2.getString(20),nameSucursal);
					
					
					
					List<VentaServicio> listVentaServicio = map.get(id_bodegaEmpresa);
					if(listVentaServicio==null) {
						List<VentaServicio> iniciaLista = new ArrayList<VentaServicio>();
						iniciaLista.add(aux);
						map.put(id_bodegaEmpresa, iniciaLista);
					}else {
						listVentaServicio.add(aux);
						map.put(id_bodegaEmpresa, listVentaServicio);
					}
				}
			}
				
			rs2.close();
			smt2.close();
		
		} catch (SQLException e) {
				e.printStackTrace();
		}
	
		return(map);
	}
	
	public static List<VentaServicio> allEntreFechasPorBodega(Connection con, String db, String desdeAAMMDD, String hastaAAMMDD, Long id_bodegaEmpresa) {
		List<VentaServicio> lista = new ArrayList<VentaServicio>();
		try {
			PreparedStatement smt2 = con
					.prepareStatement("select "
							+ " id,"
							+ "	id_userMada, "
							+ "	id_operador,"
							+ "	id_bodegaEmpresa,"
							+ "	id_equipo,"
							+ "	id_servicio,"
							+ " fecha,"
							+ "	detalle,"
							+ "	horaIni,"
							+ "	horaTer,"
							+ "	lecturaIni,"
							+ "	lecturaTer,"
							+ "	cantidad,"
							+ "	observaciones,"
							+ "	docAnexo,"
							+ "	reportPDF,"
							+ "	firmaPDFoperador,"
							+ "	firmaPDFautorizador,"
							+ "	id_cotiOdo, "
							+ " albumFotos "
							+ " from `"+db+"`.ventaServicio"
							+ " where id_bodegaEmpresa = ? and fecha between ? and ? "
							+ " order by fecha desc, id desc;");
			smt2.setLong(1, id_bodegaEmpresa);
			smt2.setString(2, desdeAAMMDD);
			smt2.setString(3, hastaAAMMDD);
			
			ResultSet rs2 = smt2.executeQuery();
			
			Map<Long,Usuario> mapUsuario = Usuario.mapAll(con, db);
			Map<Long,OperadorServicio> mapOperador = OperadorServicio.mapAll(con, db);
			Map<Long,BodegaEmpresa> mapBodega = BodegaEmpresa.mapAll(con, db);
			Map<Long,Equipo> mapEquipo = Equipo.mapAllAll(con, db);
			Map<Long,Servicio> mapServicio = Servicio.mapAll(con, db);
			Map<Long,ClaseServicio> mapClases = ClaseServicio.mapAll(con, db);
			Map<Long,Long> mapNroCotiOdo = CotiOdo.mapIdvsNroCoti(con, db);
			
			while(rs2.next()) {
				BodegaEmpresa bodega = mapBodega.get(rs2.getLong(4));
				
				if(bodega!=null) {
					String fecha = Fechas.DDMMAA(rs2.getString(7));
					String horaIni = rs2.getString(9).substring(0,5);
					String horaTer = rs2.getString(10).substring(0,5);
					String lecturaIni = myformatdouble2.format(rs2.getDouble(11));
					String lecturaTer = myformatdouble2.format(rs2.getDouble(12));
					String cantidad = myformatdouble2.format(rs2.getDouble(13));
					Usuario usuario = mapUsuario.get(rs2.getLong(2));
					String nomUserAdam = "";
					if(usuario!=null) {
						nomUserAdam = usuario.getUserName();
					}
					OperadorServicio operador = mapOperador.get(rs2.getLong(3));
					String nomOperador = "Sin operador";
					if(operador!=null) {
						nomOperador = operador.getNombre();
					}
					String nomBodegaEmpresa = "";
					String nameSucursal = "";
					if(bodega!=null) {
						nomBodegaEmpresa = bodega.getNombre();
						nameSucursal = bodega.getNameSucursal();
					}
					
					Equipo equipo = mapEquipo.get(rs2.getLong(5));
					String codEquipo = "";
					String nomEquipo = "";
					if(equipo!=null) {
						codEquipo = equipo.getCodigo();
						nomEquipo = equipo.getNombre();
					}
					Servicio servicio = mapServicio.get(rs2.getLong(6));
					String codServicio = "";
					String nomServicio = "";
					Long id_unidadServicio = (long)0;
					String unidadServicio = "S/U";
					Long id_claseServicio = (long)0;
					String nomClaseServicio = "Sin Familia";
					if(servicio!=null) {
						codServicio = servicio.getCodigo();
						nomServicio = servicio.getNombre();
						id_unidadServicio = servicio.getId_unidadServicio();
						unidadServicio = servicio.getNombreUnidad();
						
						ClaseServicio clase = mapClases.get(servicio.getId_claseServicio());
						if(clase!=null) {
							id_claseServicio = clase.getId();
							nomClaseServicio = clase.getNombre();
						}
					}
					Long nroCoti = mapNroCotiOdo.get(rs2.getLong(19));
					if(nroCoti==null) {
						nroCoti=(long)0;
					}
					
					lista.add(new VentaServicio(
					rs2.getLong(1),rs2.getLong(2),rs2.getLong(3),rs2.getLong(4),rs2.getLong(5),rs2.getLong(6),
					fecha,rs2.getString(8),horaIni,horaTer,lecturaIni,lecturaTer,cantidad,
					rs2.getString(14),rs2.getString(15),rs2.getString(16),rs2.getString(17),rs2.getString(18),
					nomUserAdam,nomOperador,nomBodegaEmpresa,codEquipo,nomEquipo,codServicio,nomServicio,id_unidadServicio,unidadServicio,(long)0,id_claseServicio,
					nomClaseServicio, rs2.getLong(19),nroCoti,rs2.getString(20),nameSucursal));
				}
				
			}
			rs2.close();
			smt2.close();
		
		} catch (SQLException e) {
				e.printStackTrace();
		}
	
		return(lista);
	}
	
	public static Map<Long,String> mapUltimaLecturaEquipo(Connection con, String db) {
		Map<Long,String> map = new HashMap<Long,String>();
		try {
			PreparedStatement smt2 = con
					.prepareStatement("select "
							+ " dateCreate, "
							+ " fecha,"
							+ "	id_equipo,"
							+ "	lecturaTer"
							+ " from `"+db+"`.ventaServicio"
							+ " order by fecha asc, dateCreate asc;");
			ResultSet rs2 = smt2.executeQuery();
			while(rs2.next()) {
				String lecturaTer = myformatdouble2.format(rs2.getDouble(4));
				map.put(rs2.getLong(3), lecturaTer);
			}
			rs2.close();
			smt2.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
	
		return(map);
	}

	public static List<VentaServicio> all(Connection con, String db, Map<String,String> mapeoPermiso, String esPorSucursal, String id_sucursal, Long year) {
		List<VentaServicio> lista = new ArrayList<VentaServicio>();
		try {
			PreparedStatement smt2 = con
					.prepareStatement("select "
							+ " id,"
							+ "	id_userMada, "
							+ "	id_operador,"
							+ "	id_bodegaEmpresa,"
							+ "	id_equipo,"
							+ "	id_servicio,"
							+ " fecha,"
							+ "	detalle,"
							+ "	horaIni,"
							+ "	horaTer,"
							+ "	lecturaIni,"
							+ "	lecturaTer,"
							+ "	cantidad,"
							+ "	observaciones,"
							+ "	docAnexo,"
							+ "	reportPDF,"
							+ "	firmaPDFoperador,"
							+ "	firmaPDFautorizador,"
							+ "	id_cotiOdo, "
							+ " albumFotos "
							+ " from `"+db+"`.ventaServicio"
							+ " where year(fecha) = ? "
							+ " order by fecha desc, id desc;");
			smt2.setLong(1, year);
			ResultSet rs2 = smt2.executeQuery();
			
			Map<Long,Usuario> mapUsuario = Usuario.mapAll(con, db);
			Map<Long,OperadorServicio> mapOperador = OperadorServicio.mapAll(con, db);
			Map<Long,List<String>> mapBodega = BodegaEmpresa.mapAllVigentesExternas(con, db, esPorSucursal, id_sucursal);
			Map<Long,Equipo> mapEquipo = Equipo.mapAllAll(con, db);
			Map<Long,Servicio> mapServicio = Servicio.mapAll(con, db);
			Map<Long,ClaseServicio> mapClases = ClaseServicio.mapAll(con, db);
			Map<Long,Long> mapNroCotiOdo = CotiOdo.mapIdvsNroCoti(con, db);
			
			while(rs2.next()) {
				
				
				List<String> listBodegas = mapBodega.get(rs2.getLong(4));
				
				if(listBodegas!=null) {
					String fecha = Fechas.DDMMAA(rs2.getString(7));
					String horaIni = rs2.getString(9).substring(0,5);
					String horaTer = rs2.getString(10).substring(0,5);
					String lecturaIni = myformatdouble2.format(rs2.getDouble(11));
					String lecturaTer = myformatdouble2.format(rs2.getDouble(12));
					String cantidad = myformatdouble2.format(rs2.getDouble(13));
					Usuario usuario = mapUsuario.get(rs2.getLong(2));
					String nomUserAdam = "";
					if(usuario!=null) {
						nomUserAdam = usuario.getUserName();
					}
					OperadorServicio operador = mapOperador.get(rs2.getLong(3));
					String nomOperador = "Sin operador";
					if(operador!=null) {
						nomOperador = operador.getNombre();
					}
					
					String nomBodegaEmpresa = listBodegas.get(5);
					String nameSucursal = listBodegas.get(16);
					
					Equipo equipo = mapEquipo.get(rs2.getLong(5));
					String codEquipo = "";
					String nomEquipo = "";
					if(equipo!=null) {
						codEquipo = equipo.getCodigo();
						nomEquipo = equipo.getNombre();
					}
					Servicio servicio = mapServicio.get(rs2.getLong(6));
					String codServicio = "";
					String nomServicio = "";
					Long id_unidadServicio = (long)0;
					String unidadServicio = "S/U";
					Long id_claseServicio = (long)0;
					String nomClaseServicio = "Sin Familia";
					if(servicio!=null) {
						codServicio = servicio.getCodigo();
						nomServicio = servicio.getNombre();
						id_unidadServicio = servicio.getId_unidadServicio();
						unidadServicio = servicio.getNombreUnidad();
						
						ClaseServicio clase = mapClases.get(servicio.getId_claseServicio());
						if(clase!=null) {
							id_claseServicio = clase.getId();
							nomClaseServicio = clase.getNombre();
						}
					}
					Long nroCoti = mapNroCotiOdo.get(rs2.getLong(19));
					if(nroCoti==null) {
						nroCoti=(long)0;
					}
					
					lista.add(new VentaServicio(
					rs2.getLong(1),rs2.getLong(2),rs2.getLong(3),rs2.getLong(4),rs2.getLong(5),rs2.getLong(6),
					fecha,rs2.getString(8),horaIni,horaTer,lecturaIni,lecturaTer,cantidad,
					rs2.getString(14),rs2.getString(15),rs2.getString(16),rs2.getString(17),rs2.getString(18),
					nomUserAdam,nomOperador,nomBodegaEmpresa,codEquipo,nomEquipo,codServicio,nomServicio,id_unidadServicio,unidadServicio,(long)0,id_claseServicio,
					nomClaseServicio, rs2.getLong(19),nroCoti,rs2.getString(20),nameSucursal));
				}
			}
			rs2.close();
			smt2.close();
		
		} catch (SQLException e) {
				e.printStackTrace();
		}
	
		return(lista);
	}
	
	public static List<VentaServicio> allentreFechas(Connection con, String db, Map<String,String> mapeoPermiso, String esPorSucursal, String id_sucursal, String desde, String hasta) {
		List<VentaServicio> lista = new ArrayList<VentaServicio>();
		try {
			PreparedStatement smt2 = con
					.prepareStatement("select "
							+ " id,"
							+ "	id_userMada, "
							+ "	id_operador,"
							+ "	id_bodegaEmpresa,"
							+ "	id_equipo,"
							+ "	id_servicio,"
							+ " fecha,"
							+ "	detalle,"
							+ "	horaIni,"
							+ "	horaTer,"
							+ "	lecturaIni,"
							+ "	lecturaTer,"
							+ "	cantidad,"
							+ "	observaciones,"
							+ "	docAnexo,"
							+ "	reportPDF,"
							+ "	firmaPDFoperador,"
							+ "	firmaPDFautorizador,"
							+ "	id_cotiOdo, "
							+ " albumFotos "
							+ " from `"+db+"`.ventaServicio"
							+ " where fecha between ? and ? "
							+ " order by fecha desc, id desc;");
			smt2.setString(1, desde);
			smt2.setString(2, hasta);
			
			ResultSet rs2 = smt2.executeQuery();
			
			Map<Long,Usuario> mapUsuario = Usuario.mapAll(con, db);
			Map<Long,OperadorServicio> mapOperador = OperadorServicio.mapAll(con, db);
			Map<Long,List<String>> mapBodega = BodegaEmpresa.mapAllVigentesExternas(con, db, esPorSucursal, id_sucursal);
			Map<Long,Equipo> mapEquipo = Equipo.mapAllAll(con, db);
			Map<Long,Servicio> mapServicio = Servicio.mapAll(con, db);
			Map<Long,ClaseServicio> mapClases = ClaseServicio.mapAll(con, db);
			Map<Long,Long> mapNroCotiOdo = CotiOdo.mapIdvsNroCoti(con, db);
			
			while(rs2.next()) {
				
				List<String> listBodegas = mapBodega.get(rs2.getLong(4));
				
				if(listBodegas!=null ) {
					String fecha = Fechas.DDMMAA(rs2.getString(7));
					String horaIni = rs2.getString(9).substring(0,5);
					String horaTer = rs2.getString(10).substring(0,5);
					String lecturaIni = myformatdouble2.format(rs2.getDouble(11));
					String lecturaTer = myformatdouble2.format(rs2.getDouble(12));
					String cantidad = myformatdouble2.format(rs2.getDouble(13));
					Usuario usuario = mapUsuario.get(rs2.getLong(2));
					String nomUserAdam = "";
					if(usuario!=null) {
						nomUserAdam = usuario.getUserName();
					}
					OperadorServicio operador = mapOperador.get(rs2.getLong(3));
					String nomOperador = "Sin operador";
					if(operador!=null) {
						nomOperador = operador.getNombre();
					}
					
					String nomBodegaEmpresa = listBodegas.get(5);
					String nameSucursal = listBodegas.get(16);
					
					Equipo equipo = mapEquipo.get(rs2.getLong(5));
					String codEquipo = "";
					String nomEquipo = "";
					if(equipo!=null) {
						codEquipo = equipo.getCodigo();
						nomEquipo = equipo.getNombre();
					}
					Servicio servicio = mapServicio.get(rs2.getLong(6));
					String codServicio = "";
					String nomServicio = "";
					Long id_unidadServicio = (long)0;
					String unidadServicio = "S/U";
					Long id_claseServicio = (long)0;
					String nomClaseServicio = "Sin Familia";
					if(servicio!=null) {
						codServicio = servicio.getCodigo();
						nomServicio = servicio.getNombre();
						id_unidadServicio = servicio.getId_unidadServicio();
						unidadServicio = servicio.getNombreUnidad();
						
						ClaseServicio clase = mapClases.get(servicio.getId_claseServicio());
						if(clase!=null) {
							id_claseServicio = clase.getId();
							nomClaseServicio = clase.getNombre();
						}
					}
					Long nroCoti = mapNroCotiOdo.get(rs2.getLong(19));
					if(nroCoti==null) {
						nroCoti=(long)0;
					}
					
					lista.add(new VentaServicio(
					rs2.getLong(1),rs2.getLong(2),rs2.getLong(3),rs2.getLong(4),rs2.getLong(5),rs2.getLong(6),
					fecha,rs2.getString(8),horaIni,horaTer,lecturaIni,lecturaTer,cantidad,
					rs2.getString(14),rs2.getString(15),rs2.getString(16),rs2.getString(17),rs2.getString(18),
					nomUserAdam,nomOperador,nomBodegaEmpresa,codEquipo,nomEquipo,codServicio,nomServicio,id_unidadServicio,unidadServicio,(long)0,id_claseServicio,
					nomClaseServicio, rs2.getLong(19),nroCoti,rs2.getString(20),nameSucursal));
				}
			}
			rs2.close();
			smt2.close();
		
		} catch (SQLException e) {
				e.printStackTrace();
		}
	
		return(lista);
	}
	
	public static List<VentaServicio> allPorUserMada(Connection con, String db, Long year, Long id_userMada) {
		List<VentaServicio> lista = new ArrayList<VentaServicio>();
		try {
			PreparedStatement smt2 = con
					.prepareStatement("select "
							+ " id,"
							+ "	id_userMada, "
							+ "	id_operador,"
							+ "	id_bodegaEmpresa,"
							+ "	id_equipo,"
							+ "	id_servicio,"
							+ " fecha,"
							+ "	detalle,"
							+ "	horaIni,"
							+ "	horaTer,"
							+ "	lecturaIni,"
							+ "	lecturaTer,"
							+ "	cantidad,"
							+ "	observaciones,"
							+ "	docAnexo,"
							+ "	reportPDF,"
							+ "	firmaPDFoperador,"
							+ "	firmaPDFautorizador,"
							+ "	id_cotiOdo, "
							+ " albumFotos "
							+ " from `"+db+"`.ventaServicio"
							+ " where year(fecha) = ? and id_userMada = ?"
							+ " order by fecha desc, id desc;");
			smt2.setLong(1, year);
			smt2.setLong(2, id_userMada);
			ResultSet rs2 = smt2.executeQuery();
			
			Map<Long,Usuario> mapUsuario = Usuario.mapAll(con, db);
			Map<Long,OperadorServicio> mapOperador = OperadorServicio.mapAll(con, db);
			Map<Long,BodegaEmpresa> mapBodega = BodegaEmpresa.mapAll(con, db);
			Map<Long,Equipo> mapEquipo = Equipo.mapAllAll(con, db);
			Map<Long,Servicio> mapServicio = Servicio.mapAll(con, db);
			Map<Long,ClaseServicio> mapClases = ClaseServicio.mapAll(con, db);
			Map<Long,Long> mapNroCotiOdo = CotiOdo.mapIdvsNroCoti(con, db);
			
			while(rs2.next()) {
				BodegaEmpresa bodega = mapBodega.get(rs2.getLong(4));
				Equipo equipo = mapEquipo.get(rs2.getLong(5));
				if(bodega!=null && equipo!=null) {
					String fecha = Fechas.DDMMAA(rs2.getString(7));
					String horaIni = rs2.getString(9).substring(0,5);
					String horaTer = rs2.getString(10).substring(0,5);
					String lecturaIni = myformatdouble2.format(rs2.getDouble(11));
					String lecturaTer = myformatdouble2.format(rs2.getDouble(12));
					String cantidad = myformatdouble2.format(rs2.getDouble(13));
					Usuario usuario = mapUsuario.get(rs2.getLong(2));
					String nomUserAdam = "";
					if(usuario!=null) {
						nomUserAdam = usuario.getUserName();
					}
					OperadorServicio operador = mapOperador.get(rs2.getLong(3));
					String nomOperador = "Sin operador";
					if(operador!=null) {
						nomOperador = operador.getNombre();
					}
					String nomBodegaEmpresa = "";
					String nameSucursal = "";
					if(bodega!=null) {
						nomBodegaEmpresa = bodega.getNombre();
						nameSucursal = bodega.getNameSucursal();
					}
					
					String codEquipo = "";
					String nomEquipo = "";
					if(equipo!=null) {
						codEquipo = equipo.getCodigo();
						nomEquipo = equipo.getNombre();
					}
					Servicio servicio = mapServicio.get(rs2.getLong(6));
					String codServicio = "";
					String nomServicio = "";
					Long id_unidadServicio = (long)0;
					String unidadServicio = "S/U";
					Long id_claseServicio = (long)0;
					String nomClaseServicio = "Sin Familia";
					if(servicio!=null) {
						codServicio = servicio.getCodigo();
						nomServicio = servicio.getNombre();
						id_unidadServicio = servicio.getId_unidadServicio();
						unidadServicio = servicio.getNombreUnidad();
						
						ClaseServicio clase = mapClases.get(servicio.getId_claseServicio());
						if(clase!=null) {
							id_claseServicio = clase.getId();
							nomClaseServicio = clase.getNombre();
						}
					}
					Long nroCoti = mapNroCotiOdo.get(rs2.getLong(19));
					if(nroCoti==null) {
						nroCoti=(long)0;
					}
					
					lista.add(new VentaServicio(
					rs2.getLong(1),rs2.getLong(2),rs2.getLong(3),rs2.getLong(4),rs2.getLong(5),rs2.getLong(6),
					fecha,rs2.getString(8),horaIni,horaTer,lecturaIni,lecturaTer,cantidad,
					rs2.getString(14),rs2.getString(15),rs2.getString(16),rs2.getString(17),rs2.getString(18),
					nomUserAdam,nomOperador,nomBodegaEmpresa,codEquipo,nomEquipo,codServicio,nomServicio,id_unidadServicio,unidadServicio,(long)0,id_claseServicio,
					nomClaseServicio, rs2.getLong(19),nroCoti,rs2.getString(20),nameSucursal));
				}
				
			}
			rs2.close();
			smt2.close();
		
		} catch (SQLException e) {
				e.printStackTrace();
		}
	
		return(lista);
	}
	
	public static List<VentaServicio> allPorBodegas(Connection con, String db, Map<String,String> mapeoPermiso, String esPorSucursal, String id_sucursal, Long id_usuario) {
		String permisoPorBodega = UsuarioPermiso.permisoBodegaEmpresa(con, db, id_usuario);
		List<VentaServicio> lista = new ArrayList<VentaServicio>();
		permisoPorBodega = permisoPorBodega.replaceAll("movimiento", "ventaServicio");
		try {
			PreparedStatement smt2 = con
					.prepareStatement("select "
							+ " id,"
							+ "	id_userMada, "
							+ "	id_operador,"
							+ "	id_bodegaEmpresa,"
							+ "	id_equipo,"
							+ "	id_servicio,"
							+ " fecha,"
							+ "	detalle,"
							+ "	horaIni,"
							+ "	horaTer,"
							+ "	lecturaIni,"
							+ "	lecturaTer,"
							+ "	cantidad,"
							+ "	observaciones,"
							+ "	docAnexo,"
							+ "	reportPDF,"
							+ "	firmaPDFoperador,"
							+ "	firmaPDFautorizador,"
							+ "	id_cotiOdo, "
							+ " albumFotos "
							+ " from `"+db+"`.ventaServicio"
							+ " where id>0 "+ permisoPorBodega
							+ " order by fecha desc, id desc;");
			ResultSet rs2 = smt2.executeQuery();
			
			Map<Long,Usuario> mapUsuario = Usuario.mapAll(con, db);
			Map<Long,OperadorServicio> mapOperador = OperadorServicio.mapAll(con, db);
			Map<Long,List<String>> mapBodega = BodegaEmpresa.mapAllVigentesExternas(con, db, esPorSucursal, id_sucursal);
			Map<Long,Equipo> mapEquipo = Equipo.mapAllAll(con, db);
			Map<Long,Servicio> mapServicio = Servicio.mapAll(con, db);
			Map<Long,ClaseServicio> mapClases = ClaseServicio.mapAll(con, db);
			Map<Long,Long> mapNroCotiOdo = CotiOdo.mapIdvsNroCoti(con, db);
			
			while(rs2.next()) {
				
				
				List<String> listBodegas = mapBodega.get(rs2.getLong(4));
				
				if(listBodegas!=null) {
					String fecha = Fechas.DDMMAA(rs2.getString(7));
					String horaIni = rs2.getString(9).substring(0,5);
					String horaTer = rs2.getString(10).substring(0,5);
					String lecturaIni = myformatdouble2.format(rs2.getDouble(11));
					String lecturaTer = myformatdouble2.format(rs2.getDouble(12));
					String cantidad = myformatdouble2.format(rs2.getDouble(13));
					Usuario usuario = mapUsuario.get(rs2.getLong(2));
					String nomUserAdam = "";
					if(usuario!=null) {
						nomUserAdam = usuario.getUserName();
					}
					OperadorServicio operador = mapOperador.get(rs2.getLong(3));
					String nomOperador = "Sin operador";
					if(operador!=null) {
						nomOperador = operador.getNombre();
					}
					
					String nomBodegaEmpresa = listBodegas.get(5);
					String nameSucursal = listBodegas.get(16);
					
					Equipo equipo = mapEquipo.get(rs2.getLong(5));
					String codEquipo = "";
					String nomEquipo = "";
					if(equipo!=null) {
						codEquipo = equipo.getCodigo();
						nomEquipo = equipo.getNombre();
					}
					Servicio servicio = mapServicio.get(rs2.getLong(6));
					String codServicio = "";
					String nomServicio = "";
					Long id_unidadServicio = (long)0;
					String unidadServicio = "S/U";
					Long id_claseServicio = (long)0;
					String nomClaseServicio = "Sin Familia";
					if(servicio!=null) {
						codServicio = servicio.getCodigo();
						nomServicio = servicio.getNombre();
						id_unidadServicio = servicio.getId_unidadServicio();
						unidadServicio = servicio.getNombreUnidad();
						
						ClaseServicio clase = mapClases.get(servicio.getId_claseServicio());
						if(clase!=null) {
							id_claseServicio = clase.getId();
							nomClaseServicio = clase.getNombre();
						}
					}
					Long nroCoti = mapNroCotiOdo.get(rs2.getLong(19));
					if(nroCoti==null) {
						nroCoti=(long)0;
					}
					
					lista.add(new VentaServicio(
					rs2.getLong(1),rs2.getLong(2),rs2.getLong(3),rs2.getLong(4),rs2.getLong(5),rs2.getLong(6),
					fecha,rs2.getString(8),horaIni,horaTer,lecturaIni,lecturaTer,cantidad,
					rs2.getString(14),rs2.getString(15),rs2.getString(16),rs2.getString(17),rs2.getString(18),
					nomUserAdam,nomOperador,nomBodegaEmpresa,codEquipo,nomEquipo,codServicio,nomServicio,id_unidadServicio,unidadServicio,(long)0,id_claseServicio,
					nomClaseServicio, rs2.getLong(19),nroCoti,rs2.getString(20), nameSucursal));
				}
			}
			rs2.close();
			smt2.close();
		
		} catch (SQLException e) {
				e.printStackTrace();
		}
	
		return(lista);
	}
	
	public static boolean modificaPorCampo(Connection con, String db, String campo, String value, Long id_ventaServicio) {
		boolean flag = false;
		try {
			PreparedStatement smt2 = con
					.prepareStatement("update `"+db+"`.ventaServicio set `"+campo+"` = ? where id = ?;");
			smt2.setString(1, value);
			smt2.setLong(2, id_ventaServicio);
			smt2.executeUpdate();
			smt2.close();
			flag = true;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return(flag);
	}
	
	public static VentaServicio find(Connection con, String db, Long id_ventaServicio) {
		VentaServicio aux = null;
		try {
			PreparedStatement smt2 = con
					.prepareStatement("select "
							+ " id,"
							+ "	id_userMada, "
							+ "	id_operador,"
							+ "	id_bodegaEmpresa,"
							+ "	id_equipo,"
							+ "	id_servicio,"
							+ " fecha,"
							+ "	detalle,"
							+ "	horaIni,"
							+ "	horaTer,"
							+ "	lecturaIni,"
							+ "	lecturaTer,"
							+ "	cantidad,"
							+ "	observaciones,"
							+ "	docAnexo,"
							+ "	reportPDF,"
							+ "	firmaPDFoperador,"
							+ "	firmaPDFautorizador,"
							+ "	id_cotiOdo, "
							+ " albumFotos "
							+ " from `"+db+"`.ventaServicio"
							+ " where id = ?;");
			smt2.setLong(1, id_ventaServicio);
			ResultSet rs2 = smt2.executeQuery();
			if(rs2.next()) {
				String fecha = Fechas.DDMMAA(rs2.getString(7));
				String horaIni = rs2.getString(9).substring(0,5);
				String horaTer = rs2.getString(10).substring(0,5);
				String lecturaIni = myformatdouble2.format(rs2.getDouble(11));
				String lecturaTer = myformatdouble2.format(rs2.getDouble(12));
				String cantidad = myformatdouble2.format(rs2.getDouble(13));
				Usuario usuario = Usuario.findXIdUser(con, db, rs2.getLong(2));
				String nomUserAdam = "";
				if(usuario!=null) {
					nomUserAdam = usuario.getUserName();
				}
				OperadorServicio operador = OperadorServicio.find(con, db, rs2.getLong(3));
				String nomOperador = "Sin operador";
				if(operador!=null) {
					nomOperador = operador.getNombre();
				}
				BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, db, rs2.getLong(4));
				String nomBodegaEmpresa = "";
				String nameSucursal = "";
				if(bodega!=null) {
					nomBodegaEmpresa = bodega.getNombre();
					nameSucursal = bodega.getNameSucursal();
				}
				Equipo equipo = Equipo.find(con, db, rs2.getLong(5));
				String codEquipo = "";
				String nomEquipo = "";
				if(equipo!=null) {
					codEquipo = equipo.getCodigo();
					nomEquipo = equipo.getNombre();
				}
				Servicio servicio = Servicio.find(con, db, rs2.getLong(6));
				String codServicio = "";
				String nomServicio = "";
				Long id_unidadServicio = (long)0;
				String unidadServicio = "S/U";
				Long id_claseServicio = (long)0;
				String nomClaseServicio = "Sin Familia";
				if(servicio!=null) {
					codServicio = servicio.getCodigo();
					nomServicio = servicio.getNombre();
					id_unidadServicio = servicio.getId_unidadServicio();
					unidadServicio = servicio.getNombreUnidad();
					
					Map<Long,ClaseServicio> mapClases = ClaseServicio.mapAll(con, db);
					ClaseServicio clase = mapClases.get(servicio.getId_claseServicio());
					if(clase!=null) {
						id_claseServicio = clase.getId();
						nomClaseServicio = clase.getNombre();
					}
				}
				CotiOdo cotiOdo = CotiOdo.find(con, db, rs2.getLong(19));
				Long nroCoti = (long)0;
				if(cotiOdo!=null) {
					nroCoti=cotiOdo.getNumero();
				}
				
				aux = new VentaServicio(
				rs2.getLong(1),rs2.getLong(2),rs2.getLong(3),rs2.getLong(4),rs2.getLong(5),rs2.getLong(6),
				fecha,rs2.getString(8),horaIni,horaTer,lecturaIni,lecturaTer,cantidad,
				rs2.getString(14),rs2.getString(15),rs2.getString(16),rs2.getString(17),rs2.getString(18),
				nomUserAdam,nomOperador,nomBodegaEmpresa,codEquipo,nomEquipo,codServicio,nomServicio,id_unidadServicio,unidadServicio,(long)0,id_claseServicio,
				nomClaseServicio, rs2.getLong(19),nroCoti,rs2.getString(20),nameSucursal);
			}
			rs2.close();
			smt2.close();
		
		} catch (SQLException e) {
				e.printStackTrace();
		}
	
		return(aux);
	}
	
	public static VentaServicio findPorAlbum(Connection con, String db, String albumFotos) {
		VentaServicio aux = null;
		try {
			PreparedStatement smt2 = con
					.prepareStatement("select "
							+ " id,"
							+ "	id_userMada, "
							+ "	id_operador,"
							+ "	id_bodegaEmpresa,"
							+ "	id_equipo,"
							+ "	id_servicio,"
							+ " fecha,"
							+ "	detalle,"
							+ "	horaIni,"
							+ "	horaTer,"
							+ "	lecturaIni,"
							+ "	lecturaTer,"
							+ "	cantidad,"
							+ "	observaciones,"
							+ "	docAnexo,"
							+ "	reportPDF,"
							+ "	firmaPDFoperador,"
							+ "	firmaPDFautorizador,"
							+ "	id_cotiOdo, "
							+ " albumFotos "
							+ " from `"+db+"`.ventaServicio"
							+ " where albumFotos = ?;");
			smt2.setString(1, albumFotos);
			ResultSet rs2 = smt2.executeQuery();
			if(rs2.next()) {
				String fecha = Fechas.DDMMAA(rs2.getString(7));
				String horaIni = rs2.getString(9).substring(0,5);
				String horaTer = rs2.getString(10).substring(0,5);
				String lecturaIni = myformatdouble2.format(rs2.getDouble(11));
				String lecturaTer = myformatdouble2.format(rs2.getDouble(12));
				String cantidad = myformatdouble2.format(rs2.getDouble(13));
				Usuario usuario = Usuario.findXIdUser(con, db, rs2.getLong(2));
				String nomUserAdam = "";
				if(usuario!=null) {
					nomUserAdam = usuario.getUserName();
				}
				OperadorServicio operador = OperadorServicio.find(con, db, rs2.getLong(3));
				String nomOperador = "Sin operador";
				if(operador!=null) {
					nomOperador = operador.getNombre();
				}
				BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, db, rs2.getLong(4));
				String nomBodegaEmpresa = "";
				String nameSucursal = "";
				if(bodega!=null) {
					nomBodegaEmpresa = bodega.getNombre();
					nameSucursal = bodega.getNameSucursal();
				}
				Equipo equipo = Equipo.find(con, db, rs2.getLong(5));
				String codEquipo = "";
				String nomEquipo = "";
				if(equipo!=null) {
					codEquipo = equipo.getCodigo();
					nomEquipo = equipo.getNombre();
				}
				Servicio servicio = Servicio.find(con, db, rs2.getLong(6));
				String codServicio = "";
				String nomServicio = "";
				Long id_unidadServicio = (long)0;
				String unidadServicio = "S/U";
				Long id_claseServicio = (long)0;
				String nomClaseServicio = "Sin Familia";
				if(servicio!=null) {
					codServicio = servicio.getCodigo();
					nomServicio = servicio.getNombre();
					id_unidadServicio = servicio.getId_unidadServicio();
					unidadServicio = servicio.getNombreUnidad();
					
					Map<Long,ClaseServicio> mapClases = ClaseServicio.mapAll(con, db);
					ClaseServicio clase = mapClases.get(servicio.getId_claseServicio());
					if(clase!=null) {
						id_claseServicio = clase.getId();
						nomClaseServicio = clase.getNombre();
					}
				}
				CotiOdo cotiOdo = CotiOdo.find(con, db, rs2.getLong(19));
				Long nroCoti = (long)0;
				if(cotiOdo!=null) {
					nroCoti=cotiOdo.getNumero();
				}
				
				aux = new VentaServicio(
				rs2.getLong(1),rs2.getLong(2),rs2.getLong(3),rs2.getLong(4),rs2.getLong(5),rs2.getLong(6),
				fecha,rs2.getString(8),horaIni,horaTer,lecturaIni,lecturaTer,cantidad,
				rs2.getString(14),rs2.getString(15),rs2.getString(16),rs2.getString(17),rs2.getString(18),
				nomUserAdam,nomOperador,nomBodegaEmpresa,codEquipo,nomEquipo,codServicio,nomServicio,id_unidadServicio,unidadServicio,(long)0,id_claseServicio,
				nomClaseServicio, rs2.getLong(19),nroCoti,rs2.getString(20),nameSucursal);
			}
			rs2.close();
			smt2.close();
		
		} catch (SQLException e) {
				e.printStackTrace();
		}
	
		return(aux);
	}
	
	public static Long create(Connection con, String db, VentaServicio ventaServicio) {
		Long id_ventaServicio = (long)0;
		try {
			PreparedStatement smt2 = con
					.prepareStatement("insert into `"+db+"`.ventaServicio ("
							+ " fecha,"
							+ "	id_userMada,"
							+ "	id_operador,"
							+ "	id_bodegaEmpresa,"
							+ "	id_servicio,"
							+ "	id_equipo,"
							+ "	horaIni,"
							+ "	horaTer,"
							+ "	lecturaIni,"
							+ "	lecturaTer,"
							+ "	cantidad,"
							+ "	detalle,"
							+ "	observaciones,"
							+ "	firmaPDFoperador,"
							+ "	firmaPDFautorizador, "
							+ "	id_cotiOdo) "
							+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
			smt2.setString(1, ventaServicio.getFecha());
			smt2.setLong(2, ventaServicio.getId_userMada());
			smt2.setLong(3, ventaServicio.getId_operador());
			smt2.setLong(4, ventaServicio.getId_bodegaEmpresa());
			smt2.setLong(5, ventaServicio.getId_servicio());
			smt2.setLong(6, ventaServicio.getId_equipo());
			smt2.setString(7, ventaServicio.getHoraIni());
			smt2.setString(8, ventaServicio.getHoraTer());
			smt2.setString(9, ventaServicio.getLecturaIni().replaceAll(",", ""));
			smt2.setString(10, ventaServicio.getLecturaTer().replaceAll(",", ""));
			smt2.setString(11, ventaServicio.getCantidad().replaceAll(",", ""));
			smt2.setString(12, ventaServicio.getDetalle());
			smt2.setString(13, ventaServicio.getObservaciones());
			smt2.setString(14, ventaServicio.getFirmaPDFoperador());
			smt2.setString(15, ventaServicio.getFirmaPDFautorizador());
			smt2.setLong(16, ventaServicio.getId_cotiOdo());
			smt2.executeUpdate();
			smt2.close();
			
			
			PreparedStatement smt3 = con
					.prepareStatement("select max(id) from `"+db+"`.ventaServicio where "
							+ " fecha = ? and"
							+ "	id_userMada = ? and"
							+ "	id_operador = ? and"
							+ "	id_bodegaEmpresa = ? and"
							+ "	id_servicio = ? and"
							+ "	id_equipo = ? and"
							+ "	cantidad = ? and"
							+ "	id_cotiOdo = ? "
							+ " group by fecha,id_userMada,id_operador,id_bodegaEmpresa,id_servicio,id_equipo,cantidad;");
			smt3.setString(1, ventaServicio.getFecha());
			smt3.setLong(2, ventaServicio.getId_userMada());
			smt3.setLong(3, ventaServicio.getId_operador());
			smt3.setLong(4, ventaServicio.getId_bodegaEmpresa());
			smt3.setLong(5, ventaServicio.getId_servicio());
			smt3.setLong(6, ventaServicio.getId_equipo());
			smt3.setString(7, ventaServicio.getCantidad().replaceAll(",", ""));
			smt3.setLong(8, ventaServicio.getId_cotiOdo());
			ResultSet rs3 = smt3.executeQuery();
			if(rs3.next()) {
				id_ventaServicio = rs3.getLong(1);
			}
			rs3.close();
			smt3.close();
			
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (id_ventaServicio);
	}
	
	public static boolean delete(Connection con, String db, Long id_ventaServicio) {
		boolean flag = false;
		try {
			PreparedStatement smt2 = con
					.prepareStatement("delete from `"+db+"`.ventaServicio where id=?;");
			smt2.setLong(1, id_ventaServicio);
			smt2.executeUpdate();
			smt2.close();
			flag=true;
			
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (flag);
	}
	
	public static List<List<String>> listaBodegasConServ_equip(Connection con, String db, String esPorSucursal, String id_sucursal){
		List<List<String>> listBodegasConEquipos = EquipoServicio.listaBodegasConEquipos(con, db, esPorSucursal, id_sucursal);
		List<List<String>> listBodegasConServicios = ListaPrecioServicio.listaBodegasConServicios(con, db, esPorSucursal, id_sucursal);
		
		Map<String,List<String>> mapLista = new HashMap<String,List<String>>();
		listBodegasConEquipos.forEach(x->{
			if(x.get(3).equals("1")) {
				mapLista.put(x.get(0), x);
			}
		});
		listBodegasConServicios.forEach(x->{
			if(x.get(3).equals("1")) {
				mapLista.put(x.get(0), x);
			}
		});
		
		List<List<String>> lista = new ArrayList<List<String>>();
		mapLista.forEach((k,v)->{
			lista.add(v);
		});
		
		//ORDENA LA LISTA
		for(int j=0;j<lista.size();j++) {
	        for(int i=0;i<lista.size()-j;i++) {
	            if (i+1!=lista.size()&&lista.get(i).get(1).compareTo(lista.get(i+1).get(1))>0) {
	                List<String> aux = new ArrayList<String>();
	                aux=lista.get(i);
	                lista.set(i,lista.get(i+1));
	                lista.set(i+1, aux);
	            }
	        }
	    }
		return(lista);
	}
	
	public static String generaPdfVentaReport(Connection con, String db, VentaServicio ventaServicio, byte[] decodedStrOper, byte[] decodedStrAut) {
		String nameFile = "";
		File tmp = TempFile.createTempFile("tmp","null");
		try {
			String path = db+"/formatos/odoVentaReport.docx";
			InputStream formato = Archivos.leerArchivo(path);
			XWPFDocument doc = new XWPFDocument(formato);
			formato.close();
			
			XWPFTable table = null;
			XWPFTableRow row = null;
			XWPFTableCell cell = null;
			String texto = "";

			table = doc.getTables().get(0);
			
			row=table.getRow(1);
			cell=row.getCell(2);
			texto = Fechas.DDMMAA(ventaServicio.getFecha());
			setCelda(cell,"Arial",10,2,"000000",texto,false);
			
			row=table.getRow(2);
			cell=row.getCell(2);
			texto = "ID: "+ventaServicio.getId().toString();
			setCelda(cell,"Arial",10,2,"000000",texto,false);
			
			table = doc.getTables().get(1);
			
			row=table.getRow(0);
			cell=row.getCell(1);
			texto = Fechas.DDMMAA(ventaServicio.getFecha());
			setCelda(cell,"Arial",10,2,"000000",texto,false);
			
			row=table.getRow(1);
			cell=row.getCell(1);
			texto = ventaServicio.getNomUserAdam();
			setCelda(cell,"Arial",10,2,"000000",texto,false);
			
			row=table.getRow(2);
			cell=row.getCell(1);
			texto = ventaServicio.getNomOperador();
			setCelda(cell,"Arial",10,2,"000000",texto,false);
			
			row=table.getRow(3);
			cell=row.getCell(1);
			texto = ventaServicio.getNomBodegaEmpresa();
			setCelda(cell,"Arial",10,2,"000000",texto,false);
			
			
			CotiOdo cotiOdo = CotiOdo.find(con, db, ventaServicio.getId_cotiOdo());
			Long nroCoti = (long)0;
			if(cotiOdo!=null) {
				nroCoti=cotiOdo.getNumero();
			}
			
			
			row=table.getRow(4);
			cell=row.getCell(1);
			texto = nroCoti + " - " + ventaServicio.getCodServicio() + " - " + ventaServicio.getNomServicio();
			setCelda(cell,"Arial",10,2,"000000",texto,false);
			
			row=table.getRow(5);
			cell=row.getCell(1);
			texto = ventaServicio.getCodEquipo() + " - " + ventaServicio.getNomEquipo();
			setCelda(cell,"Arial",10,2,"000000",texto,false);
			
			row=table.getRow(6);
			cell=row.getCell(1);
			texto = ventaServicio.getHoraIni();
			setCelda(cell,"Arial",10,2,"000000",texto,false);
			
			row=table.getRow(7);
			cell=row.getCell(1);
			texto = ventaServicio.getHoraTer();
			setCelda(cell,"Arial",10,2,"000000",texto,false);
			
			row=table.getRow(8);
			cell=row.getCell(1);
			texto = ventaServicio.getLecturaIni();
			setCelda(cell,"Arial",10,2,"000000",texto,false);
			
			row=table.getRow(9);
			cell=row.getCell(1);
			texto = ventaServicio.getLecturaTer();
			setCelda(cell,"Arial",10,2,"000000",texto,false);
			
			row=table.getRow(10);
			cell=row.getCell(1);
			texto = ventaServicio.getCantidad();
			setCelda(cell,"Arial",10,2,"000000",texto,false);
			
			row=table.getRow(11);
			cell=row.getCell(1);
			texto = ventaServicio.getDetalle();
			setCelda(cell,"Arial",10,2,"000000",texto,false);
			
			row=table.getRow(12);
			cell=row.getCell(1);
			texto = ventaServicio.getObservaciones();
			setCelda(cell,"Arial",10,2,"000000",texto,false);
			
			//AGREGAR FIRMA
			row=table.getRow(13);
			cell=row.getCell(1);
			XWPFParagraph paragraph = cell.addParagraph();
			XWPFRun run2 = paragraph.createRun();
			run2.addPicture(new ByteArrayInputStream(decodedStrOper), XWPFDocument.PICTURE_TYPE_PNG, "firma", Units.toEMU(120), Units.toEMU(60));
			
			row=table.getRow(14);
			cell=row.getCell(1);
			XWPFParagraph paragraph3 = cell.addParagraph();
			XWPFRun run3 = paragraph3.createRun();
			run3.addPicture(new ByteArrayInputStream(decodedStrAut), XWPFDocument.PICTURE_TYPE_PNG, "firma", Units.toEMU(120), Units.toEMU(60));
			//FIN AGREGA FIRMA
			
			
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

				nameFile = "ValeReport_" + ventaServicio.getId() + ".pdf";
				path = db+"/"+nameFile;
				Archivos.grabarArchivo(tmp,path);
			
			PreparedStatement smt2 = con
					.prepareStatement("update `"+db+"`.ventaServicio set reportPDF = ? where id=?;");
			smt2.setString(1, nameFile);
			smt2.setLong(2, ventaServicio.getId());
			smt2.executeUpdate();
			smt2.close();
			
		
		} catch ( IOException | SQLException | InvalidFormatException e) {
			e.printStackTrace();
		}
		
		
		return (nameFile);
	}
	
	private static void setCelda (XWPFTableCell cell,String fontFamily,int fontSize,int alingH,String colorRGB,String text,boolean bold) {
		cell.removeParagraph(0);
		XWPFParagraph paragraph =null;
		if(text==null) text="--"; else if(text.trim().length()==0) text="--";
		paragraph = cell.addParagraph();
		if(alingH==3) {
			paragraph.setAlignment(ParagraphAlignment.RIGHT);
		}else if (alingH==2) {
			paragraph.setAlignment(ParagraphAlignment.CENTER);
		}else if (alingH==1) {
			paragraph.setAlignment(ParagraphAlignment.LEFT);
		}else {
			paragraph.setAlignment(ParagraphAlignment.BOTH);
		}
		cell.setVerticalAlignment(XWPFVertAlign.CENTER);
		
		
		
		
		XWPFRun celda = paragraph.createRun();
		celda.setFontFamily(fontFamily);
		celda.setFontSize(fontSize);
		celda.setColor(colorRGB);
		celda.setText(text);
		celda.setBold(bold);
    }
	
	
	
	
}
