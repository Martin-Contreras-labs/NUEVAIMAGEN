package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import models.forms.FormMantencion;
import models.utilities.Archivos;
import models.utilities.Horas;



public class MantTransacReport {
	public Long id;
	public Long id_userMada;  // si es cero significa que se ingreso por un operador o mecanico
	public Long id_mantActorPersonal; // 1 operador 2 mecanico (esto es FIJO)
	public Long id_mantActividad; 
	public Long id_bodega;
	public Long id_equipo;
	public Long id_mantEstadoEnObra;
	public Long id_mantEstadoOperacional;
	public Long id_mantEstadoEnTaller;
	public Long id_mantItemIntervenido;
	public Long id_mantMecanico; // -- viene de tabla mantOperadorMecanico
	public Long id_mantOperador;  //-- viene de tabla mantOperadorMecanico
	public Long id_mantTipoActividad;
	public Long id_tipoMantencion;
	public Long id_planMantencion;
	
	public String comentario;
	public String descTrabajo;
	public String estadoFinal;
	
	public String fecha;
	
	public String horaIni;
	public String horaFin;
	public Double horaDif;
	
	public String fechaIni;
	public String fechaFin;
	public Double fechaDif;
	
	public Double lectAnterior;
	public Double lectActual;
	public Double lectDif;
	
	public String observaciones;
	public String docAnexo;
	public String reportPDF;
	public String firmaPDFoperador;
	public String firmaPDFautorizador;
	public String albumFotos;

	public MantTransacReport(Long id, Long id_userMada, Long id_mantActorPersonal, Long id_mantActividad,
			Long id_bodega, Long id_equipo, Long id_mantEstadoEnObra, Long id_mantEstadoOperacional,
			Long id_mantEstadoEnTaller, Long id_mantItemIntervenido, Long id_mantMecanico, Long id_mantOperador,
			Long id_mantTipoActividad, Long id_tipoMantencion, Long id_planMantencion, String comentario,
			String descTrabajo, String estadoFinal, String fecha, String horaIni, String horaFin, Double horaDif,
			String fechaIni, String fechaFin, Double fechaDif, Double lectAnterior, Double lectActual, Double lectDif,
			String observaciones, String docAnexo, String reportPDF, String firmaPDFoperador,
			String firmaPDFautorizador, String albumFotos) {
		super();
		this.id = id;
		this.id_userMada = id_userMada;
		this.id_mantActorPersonal = id_mantActorPersonal;
		this.id_mantActividad = id_mantActividad;
		this.id_bodega = id_bodega;
		this.id_equipo = id_equipo;
		this.id_mantEstadoEnObra = id_mantEstadoEnObra;
		this.id_mantEstadoOperacional = id_mantEstadoOperacional;
		this.id_mantEstadoEnTaller = id_mantEstadoEnTaller;
		this.id_mantItemIntervenido = id_mantItemIntervenido;
		this.id_mantMecanico = id_mantMecanico;
		this.id_mantOperador = id_mantOperador;
		this.id_mantTipoActividad = id_mantTipoActividad;
		this.id_tipoMantencion = id_tipoMantencion;
		this.id_planMantencion = id_planMantencion;
		this.comentario = comentario;
		this.descTrabajo = descTrabajo;
		this.estadoFinal = estadoFinal;
		this.fecha = fecha;
		this.horaIni = horaIni;
		this.horaFin = horaFin;
		this.horaDif = horaDif;
		this.fechaIni = fechaIni;
		this.fechaFin = fechaFin;
		this.fechaDif = fechaDif;
		this.lectAnterior = lectAnterior;
		this.lectActual = lectActual;
		this.lectDif = lectDif;
		this.observaciones = observaciones;
		this.docAnexo = docAnexo;
		this.reportPDF = reportPDF;
		this.firmaPDFoperador = firmaPDFoperador;
		this.firmaPDFautorizador = firmaPDFautorizador;
		this.albumFotos = albumFotos;
	}

	public MantTransacReport() {super();}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId_userMada() {
		return id_userMada;
	}

	public Long getId_mantActorPersonal() {
		return id_mantActorPersonal;
	}

	public void setId_mantActorPersonal(Long id_mantActorPersonal) {
		this.id_mantActorPersonal = id_mantActorPersonal;
	}

	public void setId_userMada(Long id_userMada) {
		this.id_userMada = id_userMada;
	}

	public Long getId_mantActividad() {
		return id_mantActividad;
	}

	public void setId_mantActividad(Long id_mantActividad) {
		this.id_mantActividad = id_mantActividad;
	}

	public Long getId_bodega() {
		return id_bodega;
	}

	public void setId_bodega(Long id_bodega) {
		this.id_bodega = id_bodega;
	}

	public Long getId_equipo() {
		return id_equipo;
	}

	public void setId_equipo(Long id_equipo) {
		this.id_equipo = id_equipo;
	}

	public Long getId_mantEstadoEnObra() {
		return id_mantEstadoEnObra;
	}

	public void setId_mantEstadoEnObra(Long id_mantEstadoEnObra) {
		this.id_mantEstadoEnObra = id_mantEstadoEnObra;
	}

	public Long getId_mantEstadoOperacional() {
		return id_mantEstadoOperacional;
	}

	public void setId_mantEstadoOperacional(Long id_mantEstadoOperacional) {
		this.id_mantEstadoOperacional = id_mantEstadoOperacional;
	}

	public Long getId_mantEstadoEnTaller() {
		return id_mantEstadoEnTaller;
	}

	public void setId_mantEstadoEnTaller(Long id_mantEstadoEnTaller) {
		this.id_mantEstadoEnTaller = id_mantEstadoEnTaller;
	}

	public Long getId_mantItemIntervenido() {
		return id_mantItemIntervenido;
	}

	public void setId_mantItemIntervenido(Long id_mantItemIntervenido) {
		this.id_mantItemIntervenido = id_mantItemIntervenido;
	}

	public Long getId_mantMecanico() {
		return id_mantMecanico;
	}

	public void setId_mantMecanico(Long id_mantMecanico) {
		this.id_mantMecanico = id_mantMecanico;
	}

	public Long getId_mantOperador() {
		return id_mantOperador;
	}

	public void setId_mantOperador(Long id_mantOperador) {
		this.id_mantOperador = id_mantOperador;
	}

	public Long getId_mantTipoActividad() {
		return id_mantTipoActividad;
	}

	public void setId_mantTipoActividad(Long id_mantTipoActividad) {
		this.id_mantTipoActividad = id_mantTipoActividad;
	}

	public Long getId_tipoMantencion() {
		return id_tipoMantencion;
	}

	public void setId_tipoMantencion(Long id_tipoMantencion) {
		this.id_tipoMantencion = id_tipoMantencion;
	}

	public Long getId_planMantencion() {
		return id_planMantencion;
	}

	public void setId_planMantencion(Long id_planMantencion) {
		this.id_planMantencion = id_planMantencion;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getDescTrabajo() {
		return descTrabajo;
	}

	public void setDescTrabajo(String descTrabajo) {
		this.descTrabajo = descTrabajo;
	}

	public String getEstadoFinal() {
		return estadoFinal;
	}

	public void setEstadoFinal(String estadoFinal) {
		this.estadoFinal = estadoFinal;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHoraIni() {
		return horaIni;
	}

	public void setHoraIni(String horaIni) {
		this.horaIni = horaIni;
	}

	public String getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}

	public Double getHoraDif() {
		return horaDif;
	}

	public void setHoraDif(Double horaDif) {
		this.horaDif = horaDif;
	}

	public String getFechaIni() {
		return fechaIni;
	}

	public void setFechaIni(String fechaIni) {
		this.fechaIni = fechaIni;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Double getFechaDif() {
		return fechaDif;
	}

	public void setFechaDif(Double fechaDif) {
		this.fechaDif = fechaDif;
	}

	public Double getLectAnterior() {
		return lectAnterior;
	}

	public void setLectAnterior(Double lectAnterior) {
		this.lectAnterior = lectAnterior;
	}

	public Double getLectActual() {
		return lectActual;
	}

	public void setLectActual(Double lectActual) {
		this.lectActual = lectActual;
	}

	public Double getLectDif() {
		return lectDif;
	}

	public void setLectDif(Double lectDif) {
		this.lectDif = lectDif;
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

	public String getAlbumFotos() {
		return albumFotos;
	}

	public void setAlbumFotos(String albumFotos) {
		this.albumFotos = albumFotos;
	}
	
	public static boolean modificaPorCampo(Connection con, String db, String campo, Long id_mantTransacReport, String valor) {
		boolean flag = false;
		try {
			PreparedStatement smt = con.prepareStatement("update `"+db+"`.mantTransacReport set `" + campo + "` = ? WHERE id = ?;");
			smt.setString(1, valor.trim());
			smt.setLong(2, id_mantTransacReport);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static String nombreDocAnexo(Connection con, String db, Archivos archivos, Long id_mantTransacReport) {
		String nombreDocAdjunto = "0";
		String nombreArchivoSinExtencion = "Doc_ReportMant_" + id_mantTransacReport;
		if(archivos.docAdjunto.size() == 1) {
			String fileName = archivos.docAdjunto.get(0).getFilename();
         	fileName = fileName.substring(fileName.indexOf("."));
			while(fileName.substring(1).indexOf(".")>0) {
				fileName=fileName.substring(1);
				fileName=fileName.substring(fileName.indexOf("."));
			}
			String ext = fileName;
			nombreDocAdjunto = nombreArchivoSinExtencion + ext;
		}else {
			nombreDocAdjunto = nombreArchivoSinExtencion + ".zip";
		}
		MantTransacReport.modificaPorCampo(con, db, "docAnexo", id_mantTransacReport, nombreDocAdjunto);
		return (nombreArchivoSinExtencion);
	}

	public static Long newReportOperador(Connection con, String db, FormMantencion form) {
		Long id_mantTransacReport = (long)0;
		try {
			Double lecturaIni = Double.parseDouble(form.lecturaIni_oper.replaceAll(",", ""));
			Double lecturaFin = Double.parseDouble(form.lecturaTer_oper.replaceAll(",", ""));
			Double lectDif = lecturaFin - lecturaIni;
			Double cantOper = Double.parseDouble(form.cantidad_oper.replaceAll(",", ""));
			if(cantOper < lectDif || cantOper > lectDif) {
				lectDif = cantOper;
			}
			Double horaDif = Horas.difHoras(form.horaIni_oper, form.horaTer_oper);  
			PreparedStatement smt = con
					.prepareStatement("insert into `"+db+"`.mantTransacReport "
							+ " (fecha, id_userMada, id_mantActorPersonal, "
							+ " id_mantOperador, id_equipo, id_bodegaEmpresa, id_mantEstadoEnObra, "
							+ " horaIni, horaFin, horaDif, lectAnterior, lectActual, lectDif, "
							+ " comentario, observaciones, firmaPDFoperador, firmaPDFautorizador) values "
							+ " (?,?,?,"
							+ " ?,?,?,?,"
							+ " ?,?,?,?,?,?,"
							+ " ?,?,?,?);",Statement.RETURN_GENERATED_KEYS);
			smt.setString(1, form.fecha);
			smt.setLong(2, form.id_userMada);
			smt.setLong(3, form.id_mantActorPersonal);
			smt.setLong(4, form.id_operador);
			smt.setLong(5, form.id_equipo_oper);
			smt.setLong(6, form.id_bodega_oper);
			smt.setLong(7, form.id_mantEstadoEnObra);
			smt.setString(8, form.horaIni_oper);
			smt.setString(9, form.horaTer_oper);
			smt.setDouble(10, horaDif);
			smt.setDouble(11, lecturaIni);
			smt.setDouble(12, lecturaFin);
			smt.setDouble(13, lectDif);
			smt.setString(14, form.comentario);
			smt.setString(15, form.observaciones_oper);
			smt.setString(16, form.firmaAprobador);
			smt.setString(17, form.firmaEjecutor);
			smt.executeUpdate();
			ResultSet rs = smt.getGeneratedKeys();
			if (rs.next()) {
				id_mantTransacReport = rs.getLong(1);
			}
			
			List<PlanMantencion> allPlan = PlanMantencion.allTipoPlanPorId_equipo(con, db, form.id_equipo_oper);
			Long aux = (long)0;
			boolean flag = true;
			for(PlanMantencion p: allPlan) {
				if(p.getId_unidadMantencion() == (long)1 && flag) {
					aux = (long)1;
				}
				if(p.getId_unidadMantencion() == (long)2 && flag) {
					aux = (long)2;
					flag = false;
				}
			}
			
			if(aux > 0) {
				DecimalFormat myformat = new DecimalFormat("##0.0");
				for(PlanMantencion p: allPlan) {
					if(p.getId_unidadMantencion() == (long) aux) {
						PlanMantencion.actualizaPorCampo(con, db, p.getId_tipoPlan(), form.id_equipo_oper, "estadoActual", myformat.format(lecturaFin));
					}
				}
			}
			
			rs.close();
			smt.close();
		} catch (SQLException e) {
    		e.printStackTrace();
		}
		return (id_mantTransacReport);
	}
	
	
	
	
	

}
