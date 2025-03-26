package models.tables;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
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

import models.forms.FormMantencion;
import models.utilities.Archivos;
import models.utilities.DecimalFormato;
import models.utilities.Fechas;
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
	public Long id_tipoMantencion; //-- preventivo o correctivo
	public Long id_planMantencion; // corresponde a tipo de plan
	
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
	
	
	
	public String userNameAdam;
	public String nameActorPersonal;
	public String nameActividad;
	public String nameBodega;
	public String codigoEquipo;
	public String nameEquipo;
	public String nameEstadoEnObra;
	public String nameEstadoOperacional;
	public String nameEstadoEnTaller;
	public String nameItemIntervenido;
	public String fullNameMecanico;
	public String fullNameOperador;
	public String nameTipoPersonal;
	public String nameTipoActividad;
	public String nameTipoMantencion;
	public String namePlanMantencion;
	public String nameTipoBodega;
	
	
	public MantTransacReport(Long id, Long id_userMada, Long id_mantActorPersonal, Long id_mantActividad,
			Long id_bodega, Long id_equipo, Long id_mantEstadoEnObra, Long id_mantEstadoOperacional,
			Long id_mantEstadoEnTaller, Long id_mantItemIntervenido, Long id_mantMecanico, Long id_mantOperador,
			Long id_mantTipoActividad, Long id_tipoMantencion, Long id_planMantencion, String comentario,
			String descTrabajo, String estadoFinal, String fecha, String horaIni, String horaFin, Double horaDif,
			String fechaIni, String fechaFin, Double fechaDif, Double lectAnterior, Double lectActual, Double lectDif,
			String observaciones, String docAnexo, String reportPDF, String firmaPDFoperador,
			String firmaPDFautorizador, String albumFotos, String userNameAdam, String nameActorPersonal,
			String nameActividad, String nameBodega, String codigoEquipo, String nameEquipo, String nameEstadoEnObra,
			String nameEstadoOperacional, String nameEstadoEnTaller, String nameItemIntervenido, String fullNameMecanico,
			String fullNameOperador, String nameTipoPersonal, String nameTipoActividad, String nameTipoMantencion, String namePlanMantencion,
			String nameTipoBodega) {
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
		
		this.userNameAdam = userNameAdam;
		this.nameActorPersonal = nameActorPersonal;
		this.nameActividad = nameActividad;
		this.nameBodega = nameBodega;
		this.codigoEquipo = codigoEquipo;
		this.nameEquipo = nameEquipo;
		this.nameEstadoEnObra = nameEstadoEnObra;
		this.nameEstadoOperacional = nameEstadoOperacional;
		this.nameEstadoEnTaller = nameEstadoEnTaller;
		this.nameItemIntervenido = nameItemIntervenido;
		this.fullNameMecanico = fullNameMecanico;
		this.fullNameOperador = fullNameOperador;
		this.nameTipoPersonal = nameTipoPersonal;
		this.nameTipoActividad = nameTipoActividad;
		this.nameTipoMantencion = nameTipoMantencion;
		this.namePlanMantencion = namePlanMantencion;
		this.nameTipoBodega = nameTipoBodega;
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

	public void setId_userMada(Long id_userMada) {
		this.id_userMada = id_userMada;
	}

	public Long getId_mantActorPersonal() {
		return id_mantActorPersonal;
	}

	public void setId_mantActorPersonal(Long id_mantActorPersonal) {
		this.id_mantActorPersonal = id_mantActorPersonal;
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
	
	
	/*******************************************/
	

	public String getUserNameAdam() {
		return userNameAdam;
	}

	public void setUserNameAdam(String userNameAdam) {
		this.userNameAdam = userNameAdam;
	}

	public String getNameActorPersonal() {
		return nameActorPersonal;
	}

	public void setNameActorPersonal(String nameActorPersonal) {
		this.nameActorPersonal = nameActorPersonal;
	}

	public String getNameActividad() {
		return nameActividad;
	}

	public void setNameActividad(String nameActividad) {
		this.nameActividad = nameActividad;
	}

	public String getNameBodega() {
		return nameBodega;
	}

	public void setNameBodega(String nameBodega) {
		this.nameBodega = nameBodega;
	}

	public String getCodigoEquipo() {
		return codigoEquipo;
	}

	public void setCodigoEquipo(String codigoEquipo) {
		this.codigoEquipo = codigoEquipo;
	}
	
	public String getNameEquipo() {
		return nameEquipo;
	}

	public void setNameEquipo(String nameEquipo) {
		this.nameEquipo = nameEquipo;
	}

	public String getNameEstadoEnObra() {
		return nameEstadoEnObra;
	}

	public void setNameEstadoEnObra(String nameEstadoEnObra) {
		this.nameEstadoEnObra = nameEstadoEnObra;
	}

	public String getNameEstadoOperacional() {
		return nameEstadoOperacional;
	}

	public void setNameEstadoOperacional(String nameEstadoOperacional) {
		this.nameEstadoOperacional = nameEstadoOperacional;
	}

	public String getNameEstadoEnTaller() {
		return nameEstadoEnTaller;
	}

	public void setNameEstadoEnTaller(String nameEstadoEnTaller) {
		this.nameEstadoEnTaller = nameEstadoEnTaller;
	}

	public String getNameItemIntervenido() {
		return nameItemIntervenido;
	}

	public void setNameItemIntervenido(String nameItemIntervenido) {
		this.nameItemIntervenido = nameItemIntervenido;
	}

	public String getFullNameMecanico() {
		return fullNameMecanico;
	}

	public void setFullNameMecanico(String fullNameMecanico) {
		this.fullNameMecanico = fullNameMecanico;
	}

	public String getFullNameOperador() {
		return fullNameOperador;
	}

	public void setFullNameOperador(String fullNameOperador) {
		this.fullNameOperador = fullNameOperador;
	}
	
	public String getNameTipoPersonal() {
		return nameTipoPersonal;
	}

	public void setNameTipoPersonal(String nameTipoPersonal) {
		this.nameTipoPersonal = nameTipoPersonal;
	}

	public String getNameTipoActividad() {
		return nameTipoActividad;
	}

	public void setNameTipoActividad(String nameTipoActividad) {
		this.nameTipoActividad = nameTipoActividad;
	}

	public String getNameTipoMantencion() {
		return nameTipoMantencion;
	}

	public void setNameTipoMantencion(String nameTipoMantencion) {
		this.nameTipoMantencion = nameTipoMantencion;
	}

	public String getNamePlanMantencion() {
		return namePlanMantencion;
	}

	public void setNamePlanMantencion(String namePlanMantencion) {
		this.namePlanMantencion = namePlanMantencion;
	}

	public String getNameTipoBodega() {
		return nameTipoBodega;
	}

	public void setNameTipoBodega(String nameTipoBodega) {
		this.nameTipoBodega = nameTipoBodega;
	}

	public static boolean delete(Connection con, String db, Long id_mantTransacReport) {
		try {
			PreparedStatement smt = con.prepareStatement("delete "
					+ " from `"+db+"`.mantTransacReport "
					+ " where id = ? ;");
			smt.setLong(1, id_mantTransacReport);
			smt.executeUpdate();
			return(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(false);
	}
	
	public static List<MantTransacReport> allEntreFechas(Connection con, String db, String desde, String hasta) {
		List<MantTransacReport> lista = new ArrayList<MantTransacReport>();
		Map<Long,Usuario> mapUserMada = Usuario.mapAll(con, db);
		Map<Long, MantOperadorMecanico> mapOper = MantOperadorMecanico.mapAll(con, db);
		Map<Long,Equipo> mapEquipo = Equipo.mapAllAll(con, db);
		Map<Long,String> mapActorPersonal = MantActorPersonal.mapAll(con, db);
		Map<Long,String> mapActividad = MantActividad.mapAll(con, db);
		Map<Long,BodegaEmpresa> mapBodega = BodegaEmpresa.mapAll(con, db);
		
		Map<Long,String> mapEstadoEnObra = MantEstadoEnObra.mapAll(con, db);
		Map<Long,String> mapEstadoOperacional = MantEstadoOperacional.mapAll(con, db);
		Map<Long,String> mapEstadoEnTaller = MantEstadoEnTaller.mapAll(con, db);
		
		Map<Long,String> mapItemIntervenido = MantItemIntervenido.mapAll(con, db);
		Map<Long,String> mapTipoActividad = MantTipoActividad.mapAll(con, db);
		Map<Long,TipoMantencion> mapTipoMantencion = TipoMantencion.mapAll(con, db);
		Map<Long,String> mapTipoPlan = TipoPlan.mapAll(con, db);
		
		
		try {
			PreparedStatement smt = con.prepareStatement("select "
					/* 1*/  + " id,"
					/* 2*/  + " id_userMada,"
					/* 3*/  + " id_mantActorPersonal,"
					/* 4*/  + " id_mantActividad,"
					/* 5*/  + " id_bodegaEmpresa,"
					/* 6*/  + " id_equipo,"
					/* 7*/  + " id_mantEstadoEnObra,"
					/* 8*/  + " id_mantEstadoOperacional,"
					/* 9*/  + " id_mantEstadoEnTaller,"
					/* 10*/ + " id_mantItemIntervenido,"
					/* 11*/ + " id_mantMecanico,"
					/* 12*/ + " id_mantOperador,"
					/* 13*/ + " id_mantTipoActividad,"
					/* 14*/ + " id_tipoMantencion,"
					/* 15*/ + " id_tipoPlan,"
					/* 16*/ + " comentario,"
					/* 17*/ + " descTrabajo,"
					/* 18*/ + " estadoFinal,"
					/* 19*/ + " fecha,"
					/* 20*/ + " horaIni,"
					/* 21*/ + " horaFin,"
					/* 22*/ + " horaDif,"
					/* 23*/ + " fechaIni,"
					/* 24*/ + " fechaFin,"
					/* 25*/ + " fechaDif,"
					/* 26*/ + " lectAnterior,"
					/* 27*/ + " lectActual,"
					/* 28*/ + " lectDif,"
					/* 29*/ + " observaciones,"
					/* 30*/ + " docAnexo,"
					/* 31*/ + " reportPDF,"
					/* 32*/ + " firmaPDFoperador,"
					/* 33*/ + " firmaPDFautorizador,"
					/* 34*/ + " albumFotos"
							+ " from `"+db+"`.mantTransacReport "
							+ " where fecha between ? and ? ;");
			smt.setString(1, desde);
			smt.setString(2, hasta);
			ResultSet rs = smt.executeQuery();
			while(rs.next()) {
				String userNameAdam = "";
				if(rs.getLong(2) > 0 ) {
					Usuario user = mapUserMada.get(rs.getLong(2));
					if(user != null) {
						userNameAdam = user.getUserName();
					}
				}
				String nameActorPersonal = mapActorPersonal.get(rs.getLong(3));
				if(nameActorPersonal == null) {
					nameActorPersonal = "";
				}
				String fullNameMecanico = "";
				String fullNameOperador = "";
				String nameTipoPersonal = "";
				if(rs.getLong(12) > 0) {
					MantOperadorMecanico oper = mapOper.get(rs.getLong(12));
					if(oper != null) {
						fullNameOperador = oper.getNombre();
						nameTipoPersonal = oper.getNameTipoPersonal();
					}
				}else if(rs.getLong(11) > 0){
					MantOperadorMecanico mec = mapOper.get(rs.getLong(11));
					if(mec != null) {
						fullNameMecanico = mec.getNombre();
						nameTipoPersonal = mec.getNameTipoPersonal();
					}
				}
				String codigoEquipo = "";
				String nameEquipo = "";
				Equipo equipo = mapEquipo.get(rs.getLong(6));
				if(equipo != null) {
					codigoEquipo = equipo.getCodigo();
					nameEquipo = equipo.getNombre();
				}
				String nameActividad = mapActividad.get(rs.getLong(4));
				if(nameActividad == null) {
					nameActividad = "";
				}
				BodegaEmpresa bodega = mapBodega.get(rs.getLong(5));
				String nameBodega = "";
				String nameTipoBodega = "";
				if(bodega != null) {
					nameBodega = bodega.getNombre();
					nameTipoBodega = bodega.getNombreTipoBodega();
				}
				String nameEstadoEnObra = mapEstadoEnObra.get(rs.getLong(7));
				if(nameEstadoEnObra == null) {
					nameEstadoEnObra = "";
				}
				String nameEstadoOperacional = mapEstadoOperacional.get(rs.getLong(8));
				if(nameEstadoOperacional == null) {
					nameEstadoOperacional = "";
				}
				String nameEstadoEnTaller = mapEstadoEnTaller.get(rs.getLong(9));
				if(nameEstadoEnTaller == null) {
					nameEstadoEnTaller = "";
				}
				String nameItemIntervenido = mapItemIntervenido.get(rs.getLong(10));
				if(nameItemIntervenido == null) {
					nameItemIntervenido = "";
				}
				String nameTipoActividad = mapTipoActividad.get(rs.getLong(13));
				if(nameTipoActividad == null) {
					nameTipoActividad = "";
				}
				
				
				String nameTipoMantencion = "";
				String namePlanMantencion = "no Aplica";
				if(rs.getLong(11) > 0){
					TipoMantencion aux = mapTipoMantencion.get(rs.getLong(14));
					if(aux != null) {
						nameTipoMantencion = aux.getNombre();
					}
					namePlanMantencion = mapTipoPlan.get(rs.getLong(15));
					if(namePlanMantencion == null) {
						namePlanMantencion = "no Aplica";
					}
				}
				
				
				lista.add(new MantTransacReport(
					rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getLong(4),rs.getLong(5),rs.getLong(6),rs.getLong(7),rs.getLong(8),
					rs.getLong(9),rs.getLong(10),rs.getLong(11),rs.getLong(12),rs.getLong(13),rs.getLong(14),rs.getLong(15),
					rs.getString(16),rs.getString(17),rs.getString(18),rs.getString(19),rs.getString(20),rs.getString(21),
					rs.getDouble(22),rs.getString(23),rs.getString(24),rs.getDouble(25),rs.getDouble(26),rs.getDouble(27),rs.getDouble(28),
					rs.getString(29),rs.getString(30),rs.getString(31),rs.getString(32),rs.getString(33),rs.getString(34),
					userNameAdam, nameActorPersonal, nameActividad, nameBodega, codigoEquipo, nameEquipo,
					nameEstadoEnObra, nameEstadoOperacional, nameEstadoEnTaller, nameItemIntervenido,
					fullNameMecanico, fullNameOperador, nameTipoPersonal, nameTipoActividad,
					nameTipoMantencion, namePlanMantencion, nameTipoBodega));
			}
			
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(lista);
	}
	
	public static List<List<String>> listaDeReports(Connection con, String db, List<MantTransacReport> listReport){
		List<List<String>> listado = new ArrayList<List<String>>();
		for(MantTransacReport x: listReport) {
			String userMada = x.getUserNameAdam();
			String operMec = x.getNameActorPersonal();
			String userOperMec = "";
			if(x.getId_mantOperador() > 0) {
				userOperMec = x.getFullNameOperador();
			}else if(x.getId_mantMecanico() > 0){
				userOperMec = x.getFullNameMecanico();
			}
			String tipoMantencion = x.getNameTipoMantencion();
			String codigo = x.getCodigoEquipo();
			String equip = x.getNameEquipo();
			List<String> a = new ArrayList<String>();
			a.add(x.getId().toString());
			a.add(x.getFecha());
			a.add(userMada);
			a.add(operMec);
			a.add(userOperMec);
			a.add(tipoMantencion);
			a.add(codigo);
			a.add(equip);
			a.add(x.getReportPDF());
			a.add(x.getDocAnexo());
			a.add(x.getFirmaPDFoperador());
			a.add(x.getFirmaPDFautorizador());
			listado.add(a);
		}
		return(listado);
	}
	
	public static List<List<String>> historialDeReports(Connection con, String db, List<MantTransacReport> listReport,
			Long id_mantActorPersonal, Long id_operMec, Long id_tipoMantencion, Long id_equipo){
		List<List<String>> listado = new ArrayList<List<String>>();
		
		
		
		for(MantTransacReport x: listReport) {
			
			boolean flagA = true;
			boolean flagO = true;
			boolean flagT = true;
			boolean flagE = true;
			if(id_mantActorPersonal > 0) {
				flagA = (id_mantActorPersonal - x.getId_mantActorPersonal()) == (long)0;
			}
			if(id_operMec > 0 && id_mantActorPersonal > 0) {
				if(id_mantActorPersonal == (long)1) {
					flagO = (id_operMec - x.getId_mantOperador()) == (long)0;
				}else {
					flagO = (id_operMec - x.getId_mantMecanico()) == (long)0;
				}
			}
			if(id_tipoMantencion > 0) {
				flagT = (id_tipoMantencion - x.getId_tipoMantencion()) == (long)0;
			}
			if(id_equipo > 0) {
				flagE = (id_equipo - x.getId_equipo()) == (long)0;
			}
			
			if(flagA && flagO && flagT && flagE) {
				String userMada = x.getUserNameAdam();
				String operMec = x.getNameActorPersonal();
				String userOperMec = "";
				if(x.getId_mantOperador() > 0) {
					userOperMec = x.getFullNameOperador();
				}else if(x.getId_mantMecanico() > 0){
					userOperMec = x.getFullNameMecanico();
				}
				String tipoMantencion = x.getNameTipoMantencion();
				String codigo = x.getCodigoEquipo();
				String equip = x.getNameEquipo();
				List<String> a = new ArrayList<String>();
				a.add(x.getId().toString());
				a.add(x.getFecha());
				a.add(userMada);
				a.add(operMec);
				a.add(userOperMec);
				a.add(tipoMantencion);
				a.add(codigo);
				a.add(equip);
				
				a.add(DecimalFormato.formato(x.getLectAnterior(),(long)2)); // anterior
				a.add(DecimalFormato.formato(x.getLectActual(),(long)2)); // actual
				a.add(DecimalFormato.formato(x.getLectDif(),(long)2)); // uso
				a.add(x.getNameBodega()); 				// ubicacion bodega
				a.add(x.getNameEstadoEnObra()); 		// ESTADO EN SITIO
				a.add(x.getNameEstadoOperacional()); 	// ESTADO OPERACIONAL
				a.add(x.getNameEstadoEnTaller()); 		// ESTADO EN TALLER
				
				a.add(x.getComentario());  		// comentarios operador
				a.add(x.getEstadoFinal());  	// estado final mecanico
				a.add(x.getObservaciones());	// observaciones
				
				a.add(x.getReportPDF());
				a.add(x.getDocAnexo());
				
				listado.add(a);
			}
			
			
			
		}
		return(listado);
	}
	
	public static File exportaListaDeReportsExcel(String db, Map<String,String> mapDiccionario, List<List<String>> listado,
			String desdeAAMMDD, String hastaAAMMDD) {
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
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("LISTA DE REPORT INGRESADOS");
			
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
			cell.setCellValue("PERIODO: desde " + Fechas.DDMMAA(desdeAAMMDD)  + " hasta " + Fechas.DDMMAA(hastaAAMMDD));
			
			
			//anchos de columnas
			hoja1.setColumnWidth(1, 2*1000);
			hoja1.setColumnWidth(2, 3*1000);
			hoja1.setColumnWidth(3, 5*1000);
			hoja1.setColumnWidth(4, 3*1000);
			hoja1.setColumnWidth(5, 7*1000);
			hoja1.setColumnWidth(6, 3*1000);
			hoja1.setColumnWidth(7, 5*1000);
			hoja1.setColumnWidth(8, 10*1000);
			hoja1.setColumnWidth(9, 5*1000);
			hoja1.setColumnWidth(10, 5*1000);
			
			
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
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("ID");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FECHA");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("USER_MADA");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("PERFIL");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("OPERADOR/MECANICO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TIPO MANTENCION");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("CODIGO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("EQUIPO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FIRMA OPER/MEC");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FIRMA SUPERVISOR");
			
			
			for(List<String> list: listado){
				posRow++;
				posCell = 0;
		        row = hoja1.createRow(posRow);
				
		        posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(list.get(0));
				
				posCell++;
				cell = row.createCell(posCell);
				Fechas fechax = Fechas.obtenerFechaDesdeStrAAMMDD(Fechas.AAMMDD(list.get(1)));
				cell.setCellValue(fechax.fechaUtil);
				cell.setCellStyle(fecha);
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(list.get(2));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(list.get(3));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(list.get(4));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(list.get(5));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(list.get(6));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(list.get(7));
			
				posCell++; 
				cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				byte[] firmaOperador = Base64.getDecoder().decode(list.get(10));
				int indexfo = libro.addPicture(firmaOperador, Workbook.PICTURE_TYPE_PNG);
				Drawing drawfo = hoja1.createDrawingPatriarch();
				CreationHelper helperfo = libro.getCreationHelper();
				ClientAnchor anchorfo = helperfo.createClientAnchor();
				anchorfo.setCol1(posCell);
				anchorfo.setRow1(posRow);
				anchorfo.setCol2(posCell+11);
				anchorfo.setRow2(posRow+1);
				Picture imgfo = drawfo.createPicture(anchorfo, indexfo);
				imgfo.resize(0.2);
				
				posCell++; 
				cell = row.createCell(posCell);
	            cell.setCellStyle(detalle);
				byte[] firmaAurorizador = Base64.getDecoder().decode(list.get(11));
				int indexfa = libro.addPicture(firmaAurorizador, Workbook.PICTURE_TYPE_PNG);
				Drawing drawfa = hoja1.createDrawingPatriarch();
				CreationHelper helperfa = libro.getCreationHelper();
				ClientAnchor anchorfa = helperfa.createClientAnchor();
				anchorfa.setCol1(posCell);
				anchorfa.setRow1(posRow);
				anchorfa.setCol2(posCell+11);
				anchorfa.setRow2(posRow+1);
				Picture imgfa = drawfa.createPicture(anchorfa, indexfa);
				imgfa.resize(0.2);
				
			
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
		return(tmp);
	}
	
	public static File exportaHistorialDeReportsExcel(String db, Map<String,String> mapDiccionario, List<List<String>> listado,
			String desdeAAMMDD, String hastaAAMMDD) {
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
            cell = row.createCell(1);
            cell.setCellStyle(titulo);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("HISTORIAL DE REPORT INGRESADOS");
			
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
			cell.setCellValue("PERIODO: desde " + Fechas.DDMMAA(desdeAAMMDD)  + " hasta " + Fechas.DDMMAA(hastaAAMMDD));
			
			
			//anchos de columnas
			hoja1.setColumnWidth(1, 2*1000);
			hoja1.setColumnWidth(2, 3*1000);
			hoja1.setColumnWidth(3, 5*1000);
			hoja1.setColumnWidth(4, 3*1000);
			hoja1.setColumnWidth(5, 7*1000);
			hoja1.setColumnWidth(6, 4*1000);
			hoja1.setColumnWidth(7, 5*1000);
			hoja1.setColumnWidth(8, 10*1000);
			hoja1.setColumnWidth(9, 3*1000);
			hoja1.setColumnWidth(10, 3*1000);
			hoja1.setColumnWidth(11, 3*1000);
			hoja1.setColumnWidth(12, 10*1000);
			hoja1.setColumnWidth(13, 7*1000);
			hoja1.setColumnWidth(14, 7*1000);
			hoja1.setColumnWidth(15, 7*1000);
			hoja1.setColumnWidth(16, 10*1000);
			hoja1.setColumnWidth(17, 10*1000);
			hoja1.setColumnWidth(18, 10*1000);
			
			
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
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("ID");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("FECHA");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("USER_MADA");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("PERFIL");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("OPERADOR/MECANICO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("TIPO MANTENCION");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("CODIGO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("EQUIPO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("LECT_ANTERIOR");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("LECT_ACTUAL");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("USO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("UBICACION");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("ESTADO EN SITIO");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("ESTADO OPERACIONAL");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("ESTADO EN TALLER");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("COMENTARIOS");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("ESTADO FINAL");
			
			posCell++;
			cell = row.createCell(posCell);
            cell.setCellStyle(encabezado);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("OBSERVACIONES");
			
			
			for(List<String> list: listado){
				posRow++;
				posCell = 0;
		        row = hoja1.createRow(posRow);
				
		        posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(list.get(0));
				
				posCell++;
				cell = row.createCell(posCell);
				Fechas fechax = Fechas.obtenerFechaDesdeStrAAMMDD(Fechas.AAMMDD(list.get(1)));
				cell.setCellValue(fechax.fechaUtil);
				cell.setCellStyle(fecha);
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(list.get(2));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(list.get(3));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(list.get(4));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(list.get(5));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(list.get(6));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(list.get(7));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(list.get(8));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(list.get(9));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(list.get(10));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(list.get(11));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(list.get(12));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(list.get(13));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(list.get(14));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(list.get(15));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(list.get(16));
				
				posCell++;
				cell = row.createCell(posCell);
				cell.setCellStyle(detalle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(list.get(17));
			
				
				
			
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
		return(tmp);
	}
	
	public static MantTransacReport find(Connection con, String db, Long id_mantTransacReport) {
		MantTransacReport aux = null;
		try {
			PreparedStatement smt = con.prepareStatement("select "
					/* 1*/  + " id,"
					/* 2*/  + " id_userMada,"
					/* 3*/  + " id_mantActorPersonal,"
					/* 4*/  + " id_mantActividad,"
					/* 5*/  + " id_bodegaEmpresa,"
					/* 6*/  + " id_equipo,"
					/* 7*/  + " id_mantEstadoEnObra,"
					/* 8*/  + " id_mantEstadoOperacional,"
					/* 9*/  + " id_mantEstadoEnTaller,"
					/* 10*/ + " id_mantItemIntervenido,"
					/* 11*/ + " id_mantMecanico,"
					/* 12*/ + " id_mantOperador,"
					/* 13*/ + " id_mantTipoActividad,"
					/* 14*/ + " id_tipoMantencion,"
					/* 15*/ + " id_tipoPlan,"
					/* 16*/ + " comentario,"
					/* 17*/ + " descTrabajo,"
					/* 18*/ + " estadoFinal,"
					/* 19*/ + " fecha,"
					/* 20*/ + " horaIni,"
					/* 21*/ + " horaFin,"
					/* 22*/ + " horaDif,"
					/* 23*/ + " fechaIni,"
					/* 24*/ + " fechaFin,"
					/* 25*/ + " fechaDif,"
					/* 26*/ + " lectAnterior,"
					/* 27*/ + " lectActual,"
					/* 28*/ + " lectDif,"
					/* 29*/ + " observaciones,"
					/* 30*/ + " docAnexo,"
					/* 31*/ + " reportPDF,"
					/* 32*/ + " firmaPDFoperador,"
					/* 33*/ + " firmaPDFautorizador,"
					/* 34*/ + " albumFotos"
							+ " from `"+db+"`.mantTransacReport "
							+ " where id = ?;");
			smt.setLong(1, id_mantTransacReport);
			ResultSet rs = smt.executeQuery();
			if(rs.next()) {
				
				String userNameAdam = "";
				if(rs.getLong(2) > 0 ) {
					Usuario user = Usuario.findXIdUser(con, db, rs.getLong(2));
					if(user != null) {
						userNameAdam = user.getUserName();
					}
				}
				String nameActorPersonal = "";
				MantActorPersonal mantActorPersonal = MantActorPersonal.find(con, db, rs.getLong(3));
				if(mantActorPersonal != null) {
					nameActorPersonal = mantActorPersonal.nombre;
				}
				String fullNameMecanico = "";
				String fullNameOperador = "";
				String nameTipoPersonal = "";
				if(rs.getLong(12) > 0) {
					MantOperadorMecanico oper = MantOperadorMecanico.findXIdUser(con, db, rs.getLong(12));
					if(oper != null) {
						fullNameOperador = oper.getNombre();
						nameTipoPersonal = oper.getNameTipoPersonal();
					}
				}else if(rs.getLong(11) > 0){
					MantOperadorMecanico mec = MantOperadorMecanico.findXIdUser(con, db, rs.getLong(11));
					if(mec != null) {
						fullNameMecanico = mec.getNombre();
						nameTipoPersonal = mec.getNameTipoPersonal();
					}
				}
				String codigoEquipo = "";
				String nameEquipo = "";
				Equipo equipo = Equipo.find(con, db, rs.getLong(6));
				if(equipo != null) {
					codigoEquipo = equipo.getCodigo();
					nameEquipo = equipo.getNombre();
				}
				MantActividad mantActividad = MantActividad.find(con, db, rs.getLong(4));
				String nameActividad = "";
				if(mantActividad != null) {
					nameActividad = mantActividad.getNombre();
				}
				BodegaEmpresa bodega = BodegaEmpresa.findXIdBodega(con, db, rs.getLong(5));
				String nameBodega = "";
				String nameTipoBodega = "";
				if(bodega != null) {
					nameBodega = bodega.getNombre();
					nameTipoBodega = bodega.getNombreTipoBodega();
				}
				MantEstadoEnObra mantEstadoEnObra = MantEstadoEnObra.find(con, db, rs.getLong(7));
				String nameEstadoEnObra = "";
				if(mantEstadoEnObra != null) {
					nameEstadoEnObra = mantEstadoEnObra.getNombre();
				}
				MantEstadoOperacional mantEstadoOperacional = MantEstadoOperacional.find(con, db, rs.getLong(8));
				String nameEstadoOperacional = "";
				if(mantEstadoOperacional != null) {
					nameEstadoOperacional = mantEstadoOperacional.getNombre();
				}
				MantEstadoEnTaller mantEstadoEnTaller = MantEstadoEnTaller.find(con, db, rs.getLong(9));
				String nameEstadoEnTaller = "";
				if(mantEstadoEnTaller != null) {
					nameEstadoEnTaller = mantEstadoEnTaller.getNombre();
				}
				MantItemIntervenido mantItemIntervenido = MantItemIntervenido.find(con, db, rs.getLong(10));
				String nameItemIntervenido = "";
				if(mantItemIntervenido != null) {
					nameItemIntervenido = mantItemIntervenido.getNombre();
				}
				MantTipoActividad mantTipoActividad = MantTipoActividad.find(con, db, rs.getLong(13));
				String nameTipoActividad = "";
				if(mantTipoActividad != null) {
					nameTipoActividad = mantTipoActividad.getNombre();
				}
				
				String nameTipoMantencion = "";
				String namePlanMantencion = "no Aplica";
				if(rs.getLong(11) > 0){
					TipoMantencion tipoMantencion = TipoMantencion.find(con, db, rs.getLong(14));
					if(tipoMantencion != null) {
						nameTipoMantencion = tipoMantencion.getNombre();
					}
					TipoPlan tipoPlan = TipoPlan.find(con, db, rs.getLong(15));
					if(tipoPlan != null) {
						namePlanMantencion = tipoPlan.getNombre();
					}
				}
				
				aux = new MantTransacReport(
					rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getLong(4),rs.getLong(5),rs.getLong(6),rs.getLong(7),rs.getLong(8),
					rs.getLong(9),rs.getLong(10),rs.getLong(11),rs.getLong(12),rs.getLong(13),rs.getLong(14),rs.getLong(15),
					rs.getString(16),rs.getString(17),rs.getString(18),rs.getString(19),rs.getString(20),rs.getString(21),
					rs.getDouble(22),rs.getString(23),rs.getString(24),rs.getDouble(25),rs.getDouble(26),rs.getDouble(27),rs.getDouble(28),
					rs.getString(29),rs.getString(30),rs.getString(31),rs.getString(32),rs.getString(33),rs.getString(34),
					userNameAdam, nameActorPersonal, nameActividad, nameBodega, codigoEquipo, nameEquipo,
					nameEstadoEnObra, nameEstadoOperacional, nameEstadoEnTaller, nameItemIntervenido,
					fullNameMecanico, fullNameOperador, nameTipoPersonal, nameTipoActividad,
					nameTipoMantencion, namePlanMantencion, nameTipoBodega);
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(aux);
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
			smt.setString(16, form.firmaEjecutor);
			smt.setString(17, form.firmaAprobador);
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
	
	public static Long newReportMecanico(Connection con, String db, FormMantencion form, Map<String,String> mapeoPermiso) {
		Long id_mantTransacReport = (long)0;
		try {
			// ****** PREVENTIVO aqui se debe actualizar futura mantencion *******
			if(form.id_tipoMantencion == (long)1) { 
				Double lecturaIni = Double.parseDouble(form.lecturaIni_mecP.replaceAll(",", ""));
				Double lecturaFin = Double.parseDouble(form.lecturaTer_mecP.replaceAll(",", ""));
				Double lectDif = lecturaFin - lecturaIni;
				Double cantMecP = Double.parseDouble(form.cantidad_mecP.replaceAll(",", ""));
				if(cantMecP < lectDif || cantMecP > lectDif) {
					lectDif = cantMecP;
				}
				Double horaDif = Horas.difHoras(form.horaIni_mecP, form.horaTer_mecP);  
				Fechas ini = Fechas.obtenerFechaDesdeStrAAMMDD(form.fechaIni_mecP);
				Fechas ter = Fechas.obtenerFechaDesdeStrAAMMDD(form.fechaTer_mecP);
				int fechaDif = Fechas.diasEntreFechas(ini.getFechaCal(), ter.getFechaCal()) + 1;
				
				PreparedStatement smt = con
						.prepareStatement("insert into `"+db+"`.mantTransacReport "
								+ " (fecha, id_userMada, id_mantActorPersonal, id_tipoMantencion, "
								+ " id_mantMecanico, id_equipo, id_bodegaEmpresa, "
								+ " id_mantEstadoOperacional, id_mantEstadoEnTaller, id_mantActividad, id_mantTipoActividad, "
								+ " fechaIni, fechaFin, fechaDif, "
								+ " horaIni, horaFin, horaDif, lectAnterior, lectActual, lectDif, "
								+ " descTrabajo, estadoFinal, observaciones, firmaPDFoperador, firmaPDFautorizador, "
								+ " id_tipoPlan, id_mantItemIntervenido) values "
								+ " (?,?,?,?,"
								+ " ?,?,?,"
								+ " ?,?,?,?,"
								+ " ?,?,?,"
								+ " ?,?,?,?,?,?,"
								+ " ?,?,?,?,?,"
								+ " ?,?);",Statement.RETURN_GENERATED_KEYS);
				smt.setString(1, form.fecha);
				smt.setLong(2, form.id_userMada);
				smt.setLong(3, form.id_mantActorPersonal);
				smt.setLong(4, form.id_tipoMantencion);
				smt.setLong(5, form.id_mecanico);
				smt.setLong(6, form.id_equipo_mecP);
				smt.setLong(7, form.id_bodega_mecP);
				
				smt.setLong(8, form.id_mantEstadoOperacionalP);
				smt.setLong(9, form.id_mantEstadoEnTallerP);
				smt.setLong(10, form.id_mantActividadP);
				smt.setLong(11, form.id_mantTipoActividadP);
				
				smt.setString(12, ini.getFechaStrAAMMDD());
				smt.setString(13, ter.getFechaStrAAMMDD());
				smt.setDouble(14, fechaDif);
				
				smt.setString(15, form.horaIni_mecP);
				smt.setString(16, form.horaTer_mecP);
				smt.setDouble(17, horaDif);
				smt.setDouble(18, lecturaIni);
				smt.setDouble(19, lecturaFin);
				smt.setDouble(20, lectDif);
				smt.setString(21, form.descTrabajoP);
				smt.setString(22, form.estadoFinalP);
				smt.setString(23, form.observaciones_mecP);
				smt.setString(24, form.firmaAprobador);
				smt.setString(25, form.firmaEjecutor);
				
				smt.setLong(26, form.id_tipoPlan_mecP); // corresponde al id_tipoPlan en modulo planes
				smt.setLong(27, form.id_mantItemIntervenidoP); 
				
				smt.executeUpdate();
				ResultSet rs = smt.getGeneratedKeys();
				if (rs.next()) {
					id_mantTransacReport = rs.getLong(1);
				}
				rs.close();
				smt.close();
				
				
				List<Long> listIdComp = form.id_mantComponenteP;
				List<String> listCantComp = form.cantidadP;
				
				if(listIdComp.size() > 0) {
					String query = "";
					for(int i=0; i<listIdComp.size(); i++) {
						if(listIdComp.get(i) > 0) {
							String auxCant = listCantComp.get(i);
							Double dblCant = Double.parseDouble(auxCant.replaceAll(",", "").trim());
							query += "("+id_mantTransacReport+","+listIdComp.get(i)+","+dblCant+"),";
						}
					}
					if(query.length()>3) {
						query = query.substring(0,query.length()-1);
						PreparedStatement smt2 = con
								.prepareStatement("insert into `"+db+"`.mantTransacComponentes "
										+ " (id_mantTransacReport,  id_mantComponente, cantidad) values "
										+ query + ";");
						smt2.executeUpdate();
						smt2.close();
					}
				}
				
				
				DecimalFormat myformat = new DecimalFormat("##0.0");
				PlanMantencion planMantencion = PlanMantencion.find(con, db, form.id_equipo_mecP, form.id_tipoPlan_mecP);
				
				List<PlanMantencion> allPlan = PlanMantencion.allTipoPlanPorId_equipo(con, db, form.id_equipo_mecP);
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
					for(PlanMantencion p: allPlan) {
						if(p.getId_unidadMantencion() == (long) aux) {
							PlanMantencion.actualizaPorCampo(con, db, p.getId_tipoPlan(), form.id_equipo_mec, "estadoActual", myformat.format(lecturaFin));
						}
					}
				}
				
				if(mapeoPermiso.get("parametro.planes_calculaProximaMant") != null && mapeoPermiso.get("parametro.planes_calculaProximaMant").equals("0")) {
					//manual
					String nEstimado = planMantencion.getCadaNEstimado();
					Double frecuencia = Double.parseDouble(nEstimado.replaceAll(",", ""));
					Double proximaMant = frecuencia + lecturaFin;
					
					List<PlanMantencion> allPorEquipo = PlanMantencion.allTipoPlanPorId_equipo(con, db, form.id_equipo_mecP);
					for(PlanMantencion x: allPorEquipo) {
						if((long) x.getId_unidadMantencion() == (long) planMantencion.getId_unidadMantencion()) {
							PlanMantencion.actualizaPorCampo(con, db, form.id_tipoPlan_mecP, form.id_equipo_oper, "proximaMantencion", myformat.format(proximaMant));
							PlanMantencion.actualizaPorCampo(con, db, form.id_tipoPlan_mecP, form.id_equipo_oper, "fechaReset", form.fecha);
						}
					}
				}else {
					//automatico
					String nEstimado = planMantencion.getCadaNEstimado();
					Double frecuencia = Double.parseDouble(nEstimado.replaceAll(",", ""));
					Double lectActual = lecturaFin;
					if(frecuencia > 0) {
						Double proximaMant = (double)0;
						if (lectActual % frecuencia == 0) {
							proximaMant = lectActual + frecuencia;
						}else {
							Double resto = lectActual % frecuencia;
							proximaMant = lectActual - resto + frecuencia;
						}
						List<PlanMantencion> allPorEquipo = PlanMantencion.allTipoPlanPorId_equipo(con, db, form.id_equipo_mecP);
						for(PlanMantencion x: allPorEquipo) {
							if((long) x.getId_unidadMantencion() == (long) planMantencion.getId_unidadMantencion()) {
								PlanMantencion.actualizaPorCampo(con, db, form.id_tipoPlan_mecP, form.id_equipo_mecP, "proximaMantencion", myformat.format(proximaMant));
								PlanMantencion.actualizaPorCampo(con, db, form.id_tipoPlan_mecP, form.id_equipo_mecP, "fechaReset", form.fecha);
							}
						}
					}
				}
			}
			
			
			
			
			
			
			// ****** CORRECTIVO  *******
			if(form.id_tipoMantencion == (long)2) { // CORRECTIVO
				
				Double lecturaIni = Double.parseDouble(form.lecturaIni_mec.replaceAll(",", ""));
				Double lecturaFin = Double.parseDouble(form.lecturaTer_mec.replaceAll(",", ""));
				Double lectDif = lecturaFin - lecturaIni;
				Double cantMec = Double.parseDouble(form.cantidad_mec.replaceAll(",", ""));
				if(cantMec < lectDif || cantMec > lectDif) {
					lectDif = cantMec;
				}
				Double horaDif = Horas.difHoras(form.horaIni_mec, form.horaTer_mec);
				Fechas ini = Fechas.obtenerFechaDesdeStrAAMMDD(form.fechaIni_mec);
				Fechas ter = Fechas.obtenerFechaDesdeStrAAMMDD(form.fechaTer_mec);
				int fechaDif = Fechas.diasEntreFechas(ini.getFechaCal(), ter.getFechaCal()) + 1;
				
				PreparedStatement smt = con
						.prepareStatement("insert into `"+db+"`.mantTransacReport "
								+ " (fecha, id_userMada, id_mantActorPersonal, id_tipoMantencion, "
								+ " id_mantMecanico, id_equipo, id_bodegaEmpresa, "
								+ " id_mantEstadoOperacional, id_mantEstadoEnTaller, id_mantActividad, id_mantTipoActividad, "
								+ " fechaIni, fechaFin, fechaDif, "
								+ " horaIni, horaFin, horaDif, lectAnterior, lectActual, lectDif, "
								+ " descTrabajo, estadoFinal, observaciones, firmaPDFoperador, firmaPDFautorizador, "
								+ " id_mantItemIntervenido) values "
								+ " (?,?,?,?,"
								+ " ?,?,?,"
								+ " ?,?,?,?,"
								+ " ?,?,?,"
								+ " ?,?,?,?,?,?,"
								+ " ?,?,?,?,?,"
								+ " ?);",Statement.RETURN_GENERATED_KEYS);
				smt.setString(1, form.fecha);
				smt.setLong(2, form.id_userMada);
				smt.setLong(3, form.id_mantActorPersonal);
				smt.setLong(4, form.id_tipoMantencion);
				smt.setLong(5, form.id_mecanico);
				smt.setLong(6, form.id_equipo_mec);
				smt.setLong(7, form.id_bodega_mec);
				
				smt.setLong(8, form.id_mantEstadoOperacional);
				smt.setLong(9, form.id_mantEstadoEnTaller);
				smt.setLong(10, form.id_mantActividad);
				smt.setLong(11, form.id_mantTipoActividad);
				
				smt.setString(12, ini.getFechaStrAAMMDD());
				smt.setString(13, ter.getFechaStrAAMMDD());
				smt.setDouble(14, fechaDif);
				
				smt.setString(15, form.horaIni_mec);
				smt.setString(16, form.horaTer_mec);
				smt.setDouble(17, horaDif);
				smt.setDouble(18, lecturaIni);
				smt.setDouble(19, lecturaFin);
				smt.setDouble(20, lectDif);
				smt.setString(21, form.descTrabajo);
				smt.setString(22, form.estadoFinal);
				smt.setString(23, form.observaciones_mec);
				smt.setString(24, form.firmaAprobador);
				smt.setString(25, form.firmaEjecutor);
				
				smt.setLong(26, form.id_mantItemIntervenido);
				
				smt.executeUpdate();
				ResultSet rs = smt.getGeneratedKeys();
				if (rs.next()) {
					id_mantTransacReport = rs.getLong(1);
				}
				rs.close();
				smt.close();
				
				
				List<Long> listIdComp = form.id_mantComponenteC;
				List<String> listCantComp = form.cantidadC;
				
				if(listIdComp.size() > 0) {
					String query = "";
					for(int i=0; i<listIdComp.size(); i++) {
						if(listIdComp.get(i) > 0) {
							String auxCant = listCantComp.get(i);
							Double dblCant = Double.parseDouble(auxCant.replaceAll(",", "").trim());
							query += "("+id_mantTransacReport+","+listIdComp.get(i)+","+dblCant+"),";
						}
					}
					if(query.length()>3) {
						query = query.substring(0,query.length()-1);
						PreparedStatement smt2 = con
								.prepareStatement("insert into `"+db+"`.mantTransacComponentes "
										+ " (id_mantTransacReport,  id_mantComponente, cantidad) values "
										+ query + ";");
						smt2.executeUpdate();
						smt2.close();
					}
				}
				
				
				
				List<PlanMantencion> allPlan = PlanMantencion.allTipoPlanPorId_equipo(con, db, form.id_equipo_mec);
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
							PlanMantencion.actualizaPorCampo(con, db, p.getId_tipoPlan(), form.id_equipo_mec, "estadoActual", myformat.format(lecturaFin));
						}
					}
				}
				
				
			}
			
		} catch (SQLException e) {
    		e.printStackTrace();
		}
		return (id_mantTransacReport);
	}
	
	
	
	

}
