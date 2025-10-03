package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.forms.FormCotizaOdo;
import models.utilities.DecimalFormato;
import models.utilities.Fechas;


public class CotiOdo {
	public java.util.Date dateCreate;
	public Long id;
	public Long id_bodegaEmpresa;
	public Long id_cliente;
	public Long id_proyecto;
	public Long numero;
	public String fecha;
	public String cotiOdoPDF;
	public Double dctoOdo;

	public Long esModificable;
	public String fechaConfirmada;
	public Long confirmada;
	public String contratoPDF;
	public String ocClientePDF;
	public String numeroOC;
	public String fechaOC;
	public String fechaContrato;
	public String numeroContrato;
	
	public String observaciones;
	public String notasAlContrato;
	public Long id_cotizaEstado;
	public String pdfCotiVtaOdo;
	public Long id_otOdo;
	
	
	
	public CotiOdo(Date dateCreate, Long id, Long id_bodegaEmpresa, Long id_cliente, Long id_proyecto, Long numero,
			String fecha, String cotiOdoPDF, Double dctoOdo, Long esModificable, String fechaConfirmada,
			Long confirmada, String contratoPDF, String ocClientePDF, String numeroOC, String fechaOC,
			String fechaContrato, String numeroContrato, String observaciones, String notasAlContrato,
			Long id_cotizaEstado, String pdfCotiVtaOdo, Long id_otOdo) {
		super();
		this.dateCreate = dateCreate;
		this.id = id;
		this.id_bodegaEmpresa = id_bodegaEmpresa;
		this.id_cliente = id_cliente;
		this.id_proyecto = id_proyecto;
		this.numero = numero;
		this.fecha = fecha;
		this.cotiOdoPDF = cotiOdoPDF;
		this.dctoOdo = dctoOdo;
		this.esModificable = esModificable;
		this.fechaConfirmada = fechaConfirmada;
		this.confirmada = confirmada;
		this.contratoPDF = contratoPDF;
		this.ocClientePDF = ocClientePDF;
		this.numeroOC = numeroOC;
		this.fechaOC = fechaOC;
		this.fechaContrato = fechaContrato;
		this.numeroContrato = numeroContrato;
		this.observaciones = observaciones;
		this.notasAlContrato = notasAlContrato;
		this.id_cotizaEstado = id_cotizaEstado;
		this.pdfCotiVtaOdo = pdfCotiVtaOdo;
		this.id_otOdo = id_otOdo;
	}

	public CotiOdo(FormCotizaOdo form) {
		super();
		this.id = form.id_cotiOdo;
		this.id_bodegaEmpresa = form.id_bodegaEmpresa;
		this.id_cliente = form.id_cliente;
		this.id_proyecto = form.id_proyecto;
		this.numero = form.numeroCoti;
		this.fecha = form.fechaCoti;
		this.dctoOdo = Double.parseDouble(form.dctoOdo.replaceAll(",", "").replaceAll("%", "").trim())/100;
		this.observaciones = form.observaciones;
	}

	public java.util.Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(java.util.Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId_bodegaEmpresa() {
		return id_bodegaEmpresa;
	}

	public void setId_bodegaEmpresa(Long id_bodegaEmpresa) {
		this.id_bodegaEmpresa = id_bodegaEmpresa;
	}

	public Long getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Long id_cliente) {
		this.id_cliente = id_cliente;
	}

	public Long getId_proyecto() {
		return id_proyecto;
	}

	public void setId_proyecto(Long id_proyecto) {
		this.id_proyecto = id_proyecto;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getCotiOdoPDF() {
		return cotiOdoPDF;
	}

	public void setCotiOdoPDF(String cotiOdoPDF) {
		this.cotiOdoPDF = cotiOdoPDF;
	}

	public Double getDctoOdo() {
		return dctoOdo;
	}

	public void setDctoOdo(Double dctoOdo) {
		this.dctoOdo = dctoOdo;
	}

	public Long getEsModificable() {
		return esModificable;
	}

	public void setEsModificable(Long esModificable) {
		this.esModificable = esModificable;
	}

	public String getFechaConfirmada() {
		return fechaConfirmada;
	}

	public void setFechaConfirmada(String fechaConfirmada) {
		this.fechaConfirmada = fechaConfirmada;
	}

	public Long getConfirmada() {
		return confirmada;
	}

	public void setConfirmada(Long confirmada) {
		this.confirmada = confirmada;
	}

	public String getContratoPDF() {
		return contratoPDF;
	}

	public void setContratoPDF(String contratoPDF) {
		this.contratoPDF = contratoPDF;
	}

	public String getOcClientePDF() {
		return ocClientePDF;
	}

	public void setOcClientePDF(String ocClientePDF) {
		this.ocClientePDF = ocClientePDF;
	}

	public String getNumeroOC() {
		return numeroOC;
	}

	public void setNumeroOC(String numeroOC) {
		this.numeroOC = numeroOC;
	}

	public String getFechaOC() {
		return fechaOC;
	}

	public void setFechaOC(String fechaOC) {
		this.fechaOC = fechaOC;
	}

	public String getFechaContrato() {
		return fechaContrato;
	}

	public void setFechaContrato(String fechaContrato) {
		this.fechaContrato = fechaContrato;
	}

	public String getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getNotasAlContrato() {
		return notasAlContrato;
	}

	public void setNotasAlContrato(String notasAlContrato) {
		this.notasAlContrato = notasAlContrato;
	}

	public Long getId_cotizaEstado() {
		return id_cotizaEstado;
	}

	public void setId_cotizaEstado(Long id_cotizaEstado) {
		this.id_cotizaEstado = id_cotizaEstado;
	}

	public String getPdfCotiVtaOdo() {
		return pdfCotiVtaOdo;
	}

	public void setPdfCotiVtaOdo(String pdfCotiVtaOdo) {
		this.pdfCotiVtaOdo = pdfCotiVtaOdo;
	}

	public Long getId_otOdo() {
		return id_otOdo;
	}

	public void setId_otOdo(Long id_otOdo) {
		this.id_otOdo = id_otOdo;
	}

	public CotiOdo() {
		super();
	}

	
	public static Long anioPrimeraCotiOdo(Connection con, String db) {
		Fechas hoy = Fechas.hoy();
		String[] aux = hoy.getFechaStrAAMMDD().split("-");
		Long year = Long.parseLong(aux[0]);
		try {
			PreparedStatement smt = con
					.prepareStatement(" select year(min(fecha)) from `"+db+"`.cotiOdo;" );
			ResultSet resultado = smt.executeQuery();
			if (resultado.next()) {	
				year = resultado.getLong(1);	
			}
			if(year < (long)2000) {
				year = Long.parseLong(aux[0]);
			}
			resultado.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return (year);
	}
	
	public static boolean modifyXCampo(Connection con, String db, String campo, String valor, Long id_cotiOdo) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("update `"+db+"`.cotiOdo set `"+campo+"` = ? WHERE id = ?;");
			smt.setString(1, valor.trim());
			smt.setLong(2, id_cotiOdo);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	
	public static Map<Long,CotiOdo> mapAll(Connection con, String db){
		Map<Long,CotiOdo> map = new HashMap<Long,CotiOdo>();
		List<CotiOdo> lista = CotiOdo.all(con, db);
		lista.forEach(l->{
			map.put(l.getId(), l);
		});
		return(map);
	}
	
	public static Map<Long,Long> mapIdvsNroCoti(Connection con, String db){
		Map<Long,Long> map = new HashMap<Long,Long>();
		List<CotiOdo> lista = CotiOdo.all(con, db);
		lista.forEach(l->{
			map.put(l.getId(), l.numero);
		});
		return(map);
	}
	
	public static Map<Long,CotiOdo> mapAllConfirm(Connection con, String db){
		Map<Long,CotiOdo> map = new HashMap<Long,CotiOdo>();
		List<CotiOdo> lista = CotiOdo.allConfirmadasCon_o_SinOt(con, db);
		for(CotiOdo x: lista) {
			map.put(x.id, x);
		}
		return(map);
	}
	
	public static Map<Long,CotiOdo> mapAllConfirmadasUnaBodega(Connection con, String db, Long id_bodegaEmpresa){
		Map<Long,CotiOdo> map = new HashMap<Long,CotiOdo>();
		List<CotiOdo> lista = CotiOdo.all(con, db);
		lista.forEach(l->{
			if((long)l.getEsModificable()==(long)0 && (long)l.getConfirmada()==(long)1 
						&& ((long)l.getId_cotizaEstado()==(long)1 || (long)l.getId_cotizaEstado()==(long)-1 ||(long)l.getId_cotizaEstado()==(long)-2) 
						&& (long)l.getId_bodegaEmpresa()==id_bodegaEmpresa) {
				map.put(l.getId(), l);
			}
		});
		return(map);
	}
	
	public static Map<Long,CotiOdo> mapAllConOt(Connection con, String db){
		Map<Long,CotiOdo> map = new HashMap<Long,CotiOdo>();
		List<CotiOdo> lista = CotiOdo.all(con, db);
		lista.forEach(l->{
			if((long)l.getId_otOdo()>(long)0) {
				map.put(l.getId(), l);
			}
		});
		return(map);
	}
	
	public static List<CotiOdo> all(Connection con, String db){
		List<CotiOdo> lista = new ArrayList<CotiOdo>();
		try{
			PreparedStatement smt = con
					.prepareStatement(" select "
							+ " dateCreate, "
							+ " id, "
							+ " id_bodegaEmpresa, "
							+ " id_cliente, "
							+ " id_proyecto, "
							+ " numero, "
							+ " fecha, "
							+ " cotiOdoPDF, "
							+ " dctoOdo, "
							+ " esModificable, "
							+ " ifnull(fechaConfirmada,''), "
							+ " confirmada, "
							+ " contratoPDF, "
							+ " ocClientePDF, "
							+ " numeroOC, "
							+ " ifnull(fechaOC,''), "
							+ " ifnull(fechaContrato,''), "
							+ " numeroContrato, "
							+ " ifnull(observaciones,''), "
							+ " ifnull(notasAlContrato,''), "
							+ " id_cotizaEstado, "
							+ " pdfCotiVtaOdo, "
							+ " id_otOdo   "
							+ " from `"+db+"`.cotiOdo  where id>0;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				
				java.util.Date dateCreacion = null;
				  try {
					  dateCreacion = rs.getTimestamp(1);
				  }catch(Exception e){
					  dateCreacion = rs.getDate(1);
				  }
				  
				lista.add(new CotiOdo(dateCreacion, rs.getLong(2),rs.getLong(3),rs.getLong(4),rs.getLong(5),rs.getLong(6),rs.getString(7),rs.getString(8),
					rs.getDouble(9),rs.getLong(10),rs.getString(11),rs.getLong(12),rs.getString(13),rs.getString(14), rs.getString(15),rs.getString(16),
					rs.getString(17),rs.getString(18),rs.getString(19),rs.getString(20),rs.getLong(21),rs.getString(22),rs.getLong(23)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(lista);
	}
	
	
	public static List<CotiOdo> allEnEstado1(Connection con, String db){
		List<CotiOdo> lista = new ArrayList<CotiOdo>();
		try{
			PreparedStatement smt = con
					.prepareStatement(" select "
							+ " dateCreate, "
							+ " id, "
							+ " id_bodegaEmpresa, "
							+ " id_cliente, "
							+ " id_proyecto, "
							+ " numero, "
							+ " fecha, "
							+ " cotiOdoPDF, "
							+ " dctoOdo, "
							+ " esModificable, "
							+ " ifnull(fechaConfirmada,''), "
							+ " confirmada, "
							+ " contratoPDF, "
							+ " ocClientePDF, "
							+ " numeroOC, "
							+ " ifnull(fechaOC,''), "
							+ " ifnull(fechaContrato,''), "
							+ " numeroContrato, "
							+ " ifnull(observaciones,''), "
							+ " ifnull(notasAlContrato,''), "
							+ " id_cotizaEstado, "
							+ " pdfCotiVtaOdo, "
							+ " id_otOdo   "
							+ " from `"+db+"`.cotiOdo  where id>0 and id_cotizaEstado=1 order by fecha desc, numero desc;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				
				java.util.Date dateCreacion = null;
				  try {
					  dateCreacion = rs.getTimestamp(1);
				  }catch(Exception e){
					  dateCreacion = rs.getDate(1);
				  }
				  
				lista.add(new CotiOdo(dateCreacion, rs.getLong(2),rs.getLong(3),rs.getLong(4),rs.getLong(5),rs.getLong(6),rs.getString(7),rs.getString(8),
						rs.getDouble(9),rs.getLong(10),rs.getString(11),rs.getLong(12),rs.getString(13),rs.getString(14), rs.getString(15),rs.getString(16),
						rs.getString(17),rs.getString(18),rs.getString(19),rs.getString(20),rs.getLong(21),rs.getString(22),rs.getLong(23)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(lista);
	}
	
	public static List<CotiOdo> allSinConfirmar(Connection con, String db) {
		List<CotiOdo> lista = new ArrayList<CotiOdo>();
		try{
			PreparedStatement smt = con
					.prepareStatement(" select "
							+ " dateCreate, "
							+ " id, "
							+ " id_bodegaEmpresa, "
							+ " id_cliente, "
							+ " id_proyecto, "
							+ " numero, "
							+ " fecha, "
							+ " cotiOdoPDF, "
							+ " dctoOdo, "
							+ " esModificable, "
							+ " ifnull(fechaConfirmada,''), "
							+ " confirmada, "
							+ " contratoPDF, "
							+ " ocClientePDF, "
							+ " numeroOC, "
							+ " ifnull(fechaOC,''), "
							+ " ifnull(fechaContrato,''), "
							+ " numeroContrato, "
							+ " ifnull(observaciones,''), "
							+ " ifnull(notasAlContrato,''), "
							+ " id_cotizaEstado, "
							+ " pdfCotiVtaOdo, "
							+ " id_otOdo   "
							+ " from `"+db+"`.cotiOdo "
							+ " where id>0 and esModificable=1 and id_cotizaEstado=1;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				
				java.util.Date dateCreacion = null;
				  try {
					  dateCreacion = rs.getTimestamp(1);
				  }catch(Exception e){
					  dateCreacion = rs.getDate(1);
				  }
				  
				lista.add(new CotiOdo(dateCreacion, rs.getLong(2),rs.getLong(3),rs.getLong(4),rs.getLong(5),rs.getLong(6),rs.getString(7),rs.getString(8),
						rs.getDouble(9),rs.getLong(10),rs.getString(11),rs.getLong(12),rs.getString(13),rs.getString(14), rs.getString(15),rs.getString(16),
						rs.getString(17),rs.getString(18),rs.getString(19),rs.getString(20),rs.getLong(21),rs.getString(22),rs.getLong(23)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<CotiOdo> listCotiAllParaImprimir(Connection con, String db) {
		List<CotiOdo> lista = new ArrayList<CotiOdo>();
		try{
			PreparedStatement smt = con
					.prepareStatement(" select "
							+ " dateCreate, "
							+ " id, "
							+ " id_bodegaEmpresa, "
							+ " id_cliente, "
							+ " id_proyecto, "
							+ " numero, "
							+ " fecha, "
							+ " cotiOdoPDF, "
							+ " dctoOdo, "
							+ " esModificable, "
							+ " ifnull(fechaConfirmada,''), "
							+ " confirmada, "
							+ " contratoPDF, "
							+ " ocClientePDF, "
							+ " numeroOC, "
							+ " ifnull(fechaOC,''), "
							+ " ifnull(fechaContrato,''), "
							+ " numeroContrato, "
							+ " ifnull(observaciones,''), "
							+ " ifnull(notasAlContrato,''), "
							+ " id_cotizaEstado, "
							+ " pdfCotiVtaOdo, "
							+ " id_otOdo   "
							+ " from `"+db+"`.cotiOdo "
							+ " where id>0;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				
				java.util.Date dateCreacion = null;
				  try {
					  dateCreacion = rs.getTimestamp(1);
				  }catch(Exception e){
					  dateCreacion = rs.getDate(1);
				  }
				  
				lista.add(new CotiOdo(dateCreacion, rs.getLong(2),rs.getLong(3),rs.getLong(4),rs.getLong(5),rs.getLong(6),rs.getString(7),rs.getString(8),
						rs.getDouble(9),rs.getLong(10),rs.getString(11),rs.getLong(12),rs.getString(13),rs.getString(14), rs.getString(15),rs.getString(16),
						rs.getString(17),rs.getString(18),rs.getString(19),rs.getString(20),rs.getLong(21),rs.getString(22),rs.getLong(23)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<CotiOdo> allParaCambiarEstado(Connection con, String db) {
		List<CotiOdo> lista = new ArrayList<CotiOdo>();
		try{
			PreparedStatement smt = con
					.prepareStatement(" select "
							+ " dateCreate, "
							+ " id, "
							+ " id_bodegaEmpresa, "
							+ " id_cliente, "
							+ " id_proyecto, "
							+ " numero, "
							+ " fecha, "
							+ " cotiOdoPDF, "
							+ " dctoOdo, "
							+ " esModificable, "
							+ " ifnull(fechaConfirmada,''), "
							+ " confirmada, "
							+ " contratoPDF, "
							+ " ocClientePDF, "
							+ " numeroOC, "
							+ " ifnull(fechaOC,''), "
							+ " ifnull(fechaContrato,''), "
							+ " numeroContrato, "
							+ " ifnull(observaciones,''), "
							+ " ifnull(notasAlContrato,''), "
							+ " id_cotizaEstado, "
							+ " pdfCotiVtaOdo, "
							+ " id_otOdo   "
							+ " from `"+db+"`.cotiOdo "
							+ " where id>0 and esModificable=1;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {

				java.util.Date dateCreacion = null;
				  try {
					  dateCreacion = rs.getTimestamp(1);
				  }catch(Exception e){
					  dateCreacion = rs.getDate(1);
				  }
				  
				lista.add(new CotiOdo(dateCreacion, rs.getLong(2),rs.getLong(3),rs.getLong(4),rs.getLong(5),rs.getLong(6),rs.getString(7),rs.getString(8),
						rs.getDouble(9),rs.getLong(10),rs.getString(11),rs.getLong(12),rs.getString(13),rs.getString(14), rs.getString(15),rs.getString(16),
						rs.getString(17),rs.getString(18),rs.getString(19),rs.getString(20),rs.getLong(21),rs.getString(22),rs.getLong(23)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<CotiOdo> allConfirmadasSinOt(Connection con, String db) {
		List<CotiOdo> lista = new ArrayList<CotiOdo>();
		try{
			PreparedStatement smt = con
					.prepareStatement(" select "
							+ " dateCreate, "
							+ " id, "
							+ " id_bodegaEmpresa, "
							+ " id_cliente, "
							+ " id_proyecto, "
							+ " numero, "
							+ " fecha, "
							+ " cotiOdoPDF, "
							+ " dctoOdo, "
							+ " esModificable, "
							+ " ifnull(fechaConfirmada,''), "
							+ " confirmada, "
							+ " contratoPDF, "
							+ " ocClientePDF, "
							+ " numeroOC, "
							+ " ifnull(fechaOC,''), "
							+ " ifnull(fechaContrato,''), "
							+ " numeroContrato, "
							+ " ifnull(observaciones,''), "
							+ " ifnull(notasAlContrato,''), "
							+ " id_cotizaEstado, "
							+ " pdfCotiVtaOdo, "
							+ " id_otOdo   "
							+ " from `"+db+"`.cotiOdo "
							+ " where id>0 and esModificable=0 and id_cotizaEstado=1 and id_otOdo=0;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {

				java.util.Date dateCreacion = null;
				  try {
					  dateCreacion = rs.getTimestamp(1);
				  }catch(Exception e){
					  dateCreacion = rs.getDate(1);
				  }
				  
				lista.add(new CotiOdo(dateCreacion, rs.getLong(2),rs.getLong(3),rs.getLong(4),rs.getLong(5),rs.getLong(6),rs.getString(7),rs.getString(8),
						rs.getDouble(9),rs.getLong(10),rs.getString(11),rs.getLong(12),rs.getString(13),rs.getString(14), rs.getString(15),rs.getString(16),
						rs.getString(17),rs.getString(18),rs.getString(19),rs.getString(20),rs.getLong(21),rs.getString(22),rs.getLong(23)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<CotiOdo> allSinConfirmarYconfirmadasSinOt(Connection con, String db) {
		List<CotiOdo> lista = new ArrayList<CotiOdo>();
		try{
			PreparedStatement smt = con
					.prepareStatement(" select "
							+ " dateCreate, "
							+ " id, "
							+ " id_bodegaEmpresa, "
							+ " id_cliente, "
							+ " id_proyecto, "
							+ " numero, "
							+ " fecha, "
							+ " cotiOdoPDF, "
							+ " dctoOdo, "
							+ " esModificable, "
							+ " ifnull(fechaConfirmada,''), "
							+ " confirmada, "
							+ " contratoPDF, "
							+ " ocClientePDF, "
							+ " numeroOC, "
							+ " ifnull(fechaOC,''), "
							+ " ifnull(fechaContrato,''), "
							+ " numeroContrato, "
							+ " ifnull(observaciones,''), "
							+ " ifnull(notasAlContrato,''), "
							+ " id_cotizaEstado, "
							+ " pdfCotiVtaOdo, "
							+ " id_otOdo   "
							+ " from `"+db+"`.cotiOdo "
							+ " where id>0 and id_cotizaEstado=1 and id_otOdo=0;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {

				java.util.Date dateCreacion = null;
				  try {
					  dateCreacion = rs.getTimestamp(1);
				  }catch(Exception e){
					  dateCreacion = rs.getDate(1);
				  }
				  
				lista.add(new CotiOdo(dateCreacion, rs.getLong(2),rs.getLong(3),rs.getLong(4),rs.getLong(5),rs.getLong(6),rs.getString(7),rs.getString(8),
						rs.getDouble(9),rs.getLong(10),rs.getString(11),rs.getLong(12),rs.getString(13),rs.getString(14), rs.getString(15),rs.getString(16),
						rs.getString(17),rs.getString(18),rs.getString(19),rs.getString(20),rs.getLong(21),rs.getString(22),rs.getLong(23)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<CotiOdo> allConfirmadasCon_o_SinOt(Connection con, String db) {
		List<CotiOdo> lista = new ArrayList<CotiOdo>();
		try{
			PreparedStatement smt = con
					.prepareStatement(" select "
							+ " dateCreate, "
							+ " id, "
							+ " id_bodegaEmpresa, "
							+ " id_cliente, "
							+ " id_proyecto, "
							+ " numero, "
							+ " fecha, "
							+ " cotiOdoPDF, "
							+ " dctoOdo, "
							+ " esModificable, "
							+ " ifnull(fechaConfirmada,''), "
							+ " confirmada, "
							+ " contratoPDF, "
							+ " ocClientePDF, "
							+ " numeroOC, "
							+ " ifnull(fechaOC,''), "
							+ " ifnull(fechaContrato,''), "
							+ " numeroContrato, "
							+ " ifnull(observaciones,''), "
							+ " ifnull(notasAlContrato,''), "
							+ " id_cotizaEstado, "
							+ " pdfCotiVtaOdo, "
							+ " id_otOdo   "
							+ " from `"+db+"`.cotiOdo "
							+ " where id>0 and esModificable=0;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {

				java.util.Date dateCreacion = null;
				  try {
					  dateCreacion = rs.getTimestamp(1);
				  }catch(Exception e){
					  dateCreacion = rs.getDate(1);
				  }
				  
				lista.add(new CotiOdo(dateCreacion, rs.getLong(2),rs.getLong(3),rs.getLong(4),rs.getLong(5),rs.getLong(6),rs.getString(7),rs.getString(8),
						rs.getDouble(9),rs.getLong(10),rs.getString(11),rs.getLong(12),rs.getString(13),rs.getString(14), rs.getString(15),rs.getString(16),
						rs.getString(17),rs.getString(18),rs.getString(19),rs.getString(20),rs.getLong(21),rs.getString(22),rs.getLong(23)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<CotiOdo> allConfirmadasCon_o_SinOtPorAnio(Connection con, String db, Long year) {
		List<CotiOdo> lista = new ArrayList<CotiOdo>();
		try{
			PreparedStatement smt = con
					.prepareStatement(" select "
							+ " dateCreate, "
							+ " id, "
							+ " id_bodegaEmpresa, "
							+ " id_cliente, "
							+ " id_proyecto, "
							+ " numero, "
							+ " fecha, "
							+ " cotiOdoPDF, "
							+ " dctoOdo, "
							+ " esModificable, "
							+ " ifnull(fechaConfirmada,''), "
							+ " confirmada, "
							+ " contratoPDF, "
							+ " ocClientePDF, "
							+ " numeroOC, "
							+ " ifnull(fechaOC,''), "
							+ " ifnull(fechaContrato,''), "
							+ " numeroContrato, "
							+ " ifnull(observaciones,''), "
							+ " ifnull(notasAlContrato,''), "
							+ " id_cotizaEstado, "
							+ " pdfCotiVtaOdo, "
							+ " id_otOdo   "
							+ " from `"+db+"`.cotiOdo "
							+ " where id>0 and esModificable=0 and year(fecha) = ?;");
			smt.setLong(1, year);
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {

				java.util.Date dateCreacion = null;
				  try {
					  dateCreacion = rs.getTimestamp(1);
				  }catch(Exception e){
					  dateCreacion = rs.getDate(1);
				  }
				  
				lista.add(new CotiOdo(dateCreacion, rs.getLong(2),rs.getLong(3),rs.getLong(4),rs.getLong(5),rs.getLong(6),rs.getString(7),rs.getString(8),
						rs.getDouble(9),rs.getLong(10),rs.getString(11),rs.getLong(12),rs.getString(13),rs.getString(14), rs.getString(15),rs.getString(16),
						rs.getString(17),rs.getString(18),rs.getString(19),rs.getString(20),rs.getLong(21),rs.getString(22),rs.getLong(23)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static boolean delete(Connection con, String db, Long id_cotiOdo) {
		boolean flag = false;
		CotiOdo cotiOdo = CotiOdo.find(con, db, id_cotiOdo);
		if((long)cotiOdo.getEsModificable() == (long)1 && !OtOdo.existeIdOdoCoti(con, db, id_cotiOdo) && !VentaServicio.existenVentaAsociado(con, db, id_cotiOdo)) {
			try {
				PreparedStatement smt1 = con
						.prepareStatement("delete from `"+db+"`.cotiOdoDetalle WHERE id_cotiOdo = ?");
				smt1.setLong(1, id_cotiOdo);
				smt1.executeUpdate();
				smt1.close();
				
				PreparedStatement smt3 = con
						.prepareStatement("delete from `"+db+"`.cotiOdo WHERE id = ?");
				smt3.setLong(1, id_cotiOdo);
				smt3.executeUpdate();
				smt3.close();
				
				PreparedStatement smt = con
						.prepareStatement("delete from `"+db+"`.listaPrecioServicio where id_cotiOdo = ?;");
				smt.setLong(1, id_cotiOdo);
				smt.executeUpdate();
				smt.close();
				flag = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return (flag);
	}
	
	public static CotiOdo find(Connection con, String db, Long id_cotiOdo) {
		CotiOdo aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement(" select "
							+ " dateCreate, "
							+ " id, "
							+ " id_bodegaEmpresa, "
							+ " id_cliente, "
							+ " id_proyecto, "
							+ " numero, "
							+ " fecha, "
							+ " cotiOdoPDF, "
							+ " dctoOdo, "
							+ " esModificable, "
							+ " ifnull(fechaConfirmada,''), "
							+ " confirmada, "
							+ " contratoPDF, "
							+ " ocClientePDF, "
							+ " numeroOC, "
							+ " ifnull(fechaOC,''), "
							+ " ifnull(fechaContrato,''), "
							+ " numeroContrato, "
							+ " ifnull(observaciones,''), "
							+ " ifnull(notasAlContrato,''), "
							+ " id_cotizaEstado, "
							+ " pdfCotiVtaOdo, "
							+ " id_otOdo   "
							+ " from `"+db+"`.cotiOdo "
							+ " where cotiOdo.id=?;" );
			smt.setLong(1, id_cotiOdo);
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {

				java.util.Date dateCreacion = null;
				  try {
					  dateCreacion = rs.getTimestamp(1);
				  }catch(Exception e){
					  dateCreacion = rs.getDate(1);
				  }
				  
				aux = new CotiOdo(dateCreacion, rs.getLong(2),rs.getLong(3),rs.getLong(4),rs.getLong(5),rs.getLong(6),rs.getString(7),rs.getString(8),
						rs.getDouble(9),rs.getLong(10),rs.getString(11),rs.getLong(12),rs.getString(13),rs.getString(14), rs.getString(15),rs.getString(16),
						rs.getString(17),rs.getString(18),rs.getString(19),rs.getString(20),rs.getLong(21),rs.getString(22),rs.getLong(23));
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}
	
	public static CotiOdo findPorNumero(Connection con, String db, Long numero) {
		CotiOdo aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement(" select "
							+ " dateCreate, "
							+ " id, "
							+ " id_bodegaEmpresa, "
							+ " id_cliente, "
							+ " id_proyecto, "
							+ " numero, "
							+ " fecha, "
							+ " cotiOdoPDF, "
							+ " dctoOdo, "
							+ " esModificable, "
							+ " ifnull(fechaConfirmada,''), "
							+ " confirmada, "
							+ " contratoPDF, "
							+ " ocClientePDF, "
							+ " numeroOC, "
							+ " ifnull(fechaOC,''), "
							+ " ifnull(fechaContrato,''), "
							+ " numeroContrato, "
							+ " ifnull(observaciones,''), "
							+ " ifnull(notasAlContrato,''), "
							+ " id_cotizaEstado, "
							+ " pdfCotiVtaOdo, "
							+ " id_otOdo   "
							+ " from `"+db+"`.cotiOdo "
							+ " where cotiOdo.numero=?;" );
			smt.setLong(1, numero);
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {

				java.util.Date dateCreacion = null;
				  try {
					  dateCreacion = rs.getTimestamp(1);
				  }catch(Exception e){
					  dateCreacion = rs.getDate(1);
				  }
				  
				aux = new CotiOdo(dateCreacion, rs.getLong(2),rs.getLong(3),rs.getLong(4),rs.getLong(5),rs.getLong(6),rs.getString(7),rs.getString(8),
						rs.getDouble(9),rs.getLong(10),rs.getString(11),rs.getLong(12),rs.getString(13),rs.getString(14), rs.getString(15),rs.getString(16),
						rs.getString(17),rs.getString(18),rs.getString(19),rs.getString(20),rs.getLong(21),rs.getString(22),rs.getLong(23));
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}
	
	public static String vistaModalVerCotiOdo(Connection con, String db, Long id_cotiOdo, Map<String,String> mapDiccionario){
		String vista="";
		CotiOdo cotiOdo = CotiOdo.find(con, db,id_cotiOdo);
		String fecha = Fechas.AAMMDD(cotiOdo.getFecha());
		Cliente cliente = Cliente.find(con, db, cotiOdo.getId_cliente());
		String rutCliente = "";
		String nomCliente = "";
		if(cliente!=null) {
			rutCliente = cliente.getRut();
			nomCliente = cliente.getNickName();
		}
		Proyecto proyecto = Proyecto.find(con, db, cotiOdo.getId_proyecto());
		String nomProyecto = "";
		if(proyecto!=null) {
			nomProyecto = proyecto.getNickName();
		}
		
		FormCotizaOdo formCotizaOdo = new FormCotizaOdo(cotiOdo, fecha, rutCliente, nomCliente, nomProyecto);
		formCotizaOdo.id_cotiOdo = id_cotiOdo;
		formCotizaOdo.dctoOdo = DecimalFormato.formato(cotiOdo.getDctoOdo()*100, (long)2);
		
		List<List<String>> listadoServicios = FormCotizaOdo.listServiciosConValoresCoti(con, db, id_cotiOdo);
		
		
		Long numDec = (long) 0;
		if(listadoServicios.size()>0) {
			String auxNumDec = listadoServicios.get(listadoServicios.size()-1).get(12);
			numDec = Long.parseLong(auxNumDec);
		}
		
		vista=vista+
		"<table class='table table-sm table-hover table-bordered table-condensed table-fluid'>"+
			"<tr>"+
				"<td><label for='numeroCotizacion'>Nro. Cotización: "+cotiOdo.numero+"</label></td>"+
				"<td colspan='2'><label for='fechaCotizacion'>Fecha: "+Fechas.DDMMAA(cotiOdo.fecha)+"</label></td>"+
			"</tr>"+
			"<tr>"+
				"<td><label for='rutCliente'>"+mapDiccionario.get("RUT")+" Cliente: "+rutCliente+"</label></td>"+
				"<td><label for='nombreCliente'>Nombre Cliente: "+nomCliente+"</label></td>"+
				"<td><label for='nombreProyecto'>Nombre Proyecto: "+nomProyecto+"</label></td>"+
			"</tr>"+
			"<tr>"+
				"<td colspan='3'style='text-align:left;'><label>Observaciones: "+cotiOdo.observaciones+"</label></td>"+
			"</tr>"+
		"</table>"+
		"<table class='table table-sm table-hover table-bordered table-condensed table-fluid'>"+
	        "<thead style='background-color: #eeeeee'>"+
				"<TR> "+
				        "<TH style= 'text-align: center;'>FAMILIA</TH>"+
						"<TH style= 'text-align: center;'>CODIGO</TH>"+
						"<TH style= 'text-align: center;'>SERVICIO</TH>"+
						"<TH style= 'text-align: center;'>UN</TH>"+
						"<TH style= 'text-align: center;'>CANTIDAD</TH>"+
						"<TH style= 'text-align: center;'>MON</TH>"+
						"<TH style= 'text-align: center;'>PRECIO<br>UNITARIO</TH>"+
						"<TH style= 'text-align: center;'>TOTAL</TH>"+
						"<TH style= 'text-align: center;'>Aplica<br>Minimo</TH>"+
						"<TH style= 'text-align: center;'>Cantidad<br>Minimo</TH>"+
						"<TH style= 'text-align: center;'>Precio<br>Adicional</TH>"+
						"<TH style= 'text-align: center;'>EQUIPO ASOCIADO</TH>"+
				"</TR>"+
			"</thead>"+
			"<tbody>";
		
		Double total = (double)0;
		Double cant = (double)0;
		
		for(int i=0;i<listadoServicios.size();i++){
			Double cantidad = Double.parseDouble(listadoServicios.get(i).get(5).replaceAll(",", ""));
			if(cantidad > 0) {
				cant += cantidad;
				total += Double.parseDouble(listadoServicios.get(i).get(8).replaceAll(",", ""));
				
				vista=vista+
				"<TR>"+
					"<td style='text-align:left;'>"+listadoServicios.get(i).get(1)+"</td>"+
					"<td style='text-align:left;'>"+listadoServicios.get(i).get(2)+"</td>"+
					"<td style='text-align:left;'>"+listadoServicios.get(i).get(3)+"</td>"+
					"<td style='text-align:center;'>"+listadoServicios.get(i).get(4)+"</td>"+
					"<td style='text-align:right;'>"+listadoServicios.get(i).get(5)+"</td>"+
					"<td style='text-align:center;'>"+listadoServicios.get(i).get(6)+"</td>"+
					"<td style='text-align:right;'>"+listadoServicios.get(i).get(7)+"</td>"+
					"<td style='text-align:right;'>"+listadoServicios.get(i).get(8)+"</td>"+
					"<td style='text-align:center;'>";
						if(listadoServicios.get(i).get(9).equals("0")){
							vista = vista + "NO";
						}else{
							vista = vista + "SI";
						}
					vista=vista+
					"</td>"+
					"<td style='text-align:right;'>"+listadoServicios.get(i).get(10)+"</td>"+
					"<td style='text-align:right;'>"+listadoServicios.get(i).get(11)+"</td>"+
					"<td style='text-align:left;'>"+listadoServicios.get(i).get(15)+" - "+listadoServicios.get(i).get(16)+"</td>"+
				"</TR>";
			}
 		}
		vista=vista+
		"</tbody>"+
		"<tfoot style='background-color: #eeeeee'>";
		
		Double dcto = Double.parseDouble(formCotizaOdo.dctoOdo.replaceAll(",", "")) / 100;
		
		if((double) cotiOdo.getDctoOdo() > (double) 0 ){
			vista=vista+
					"<TR>"+
							
							"<TH colspan='4' style='text-align: right;'>SUB-TOTALES </TH>"+
							"<TH style='text-align: right;'>"+DecimalFormato.formato(cant, (long)2)+"</TH>"+
							"<TH colspan='2'></TH>"+
							"<TH style='text-align: right;'>"+DecimalFormato.formato(total, numDec)+"</TH>"+
							"<TH colspan='4'></TH>"+
					"</TR>"+
					"<TR>"+
							"<TH colspan='4' style='text-align: right;'>DESCUENTO</TH>"+
							"<TH colspan='3'></TH>"+
							"<TH style='text-align: right;'>"+formCotizaOdo.dctoOdo+" %</TH>"+
							"<TH colspan='4'></TH>"+
					"</TR>";
		}
		
		vista=vista+
				
				"<TR>"+
					"<TH colspan='4' style='text-align: right;'>TOTALES </TH>"+
					"<TH style='text-align: right;'>"+DecimalFormato.formato(cant, (long)2)+"</TH>"+
					"<TH colspan='2'></TH>"+
					"<TH style='text-align: right;'>"+DecimalFormato.formato(total*(1-dcto), numDec)+"</TH>"+
					"<TH colspan='4'></TH>"+
				"</TR>"+
			"</tfoot>"+
		"</table>";
		return(vista);
	}
	
	public static Long findNuevoNumeroOdo(Connection con, String db){
		Long numeroCoti=(long) 1;
		try {
			PreparedStatement smt = con
					.prepareStatement(" select ifnull(max(numero)+1,1) from `"+db+"`.cotiOdo;");
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				numeroCoti = rs.getLong(1);
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(numeroCoti == (long) 0) {
			numeroCoti = (long) 1;
		}
		return(numeroCoti);
	}
	
	public static boolean existeNumero(Connection con, String db, Long numeroCoti){
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement(" select numero from `"+db+"`.cotiOdo where numero = ?;");
			smt.setLong(1, numeroCoti);
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				flag = true;
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(flag);
	}

	
	public static Long create(Connection con, String db, CotiOdo cotiOdo) {
		Long id_cotiOdo = (long)0;
		try {
			PreparedStatement smt = con
				.prepareStatement("insert into `"+db+"`.cotiOdo (id_bodegaEmpresa,id_cliente,id_proyecto," +
								"numero,fecha, observaciones, dctoOdo) " +
								" values (?,?,?,?,?,?,?);");
			smt.setLong(1, cotiOdo.getId_bodegaEmpresa());
			smt.setLong(2, cotiOdo.getId_cliente());
			smt.setLong(3, cotiOdo.getId_proyecto());
			smt.setLong(4, cotiOdo.getNumero());
			smt.setString(5, cotiOdo.getFecha());
			smt.setString(6, cotiOdo.getObservaciones());
			smt.setDouble(7, cotiOdo.getDctoOdo());
			smt.executeUpdate();
			smt.close();
			
			PreparedStatement smt2 = con
					.prepareStatement("select id from `"+db+"`.cotiOdo where numero = ?;");
			smt2.setLong(1, cotiOdo.getNumero());
			ResultSet rs = smt2.executeQuery();
			if(rs.next()) {
				id_cotiOdo = rs.getLong(1);
			}
			smt2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (id_cotiOdo);
	}
	
	public static boolean update(Connection con, String db, CotiOdo cotiOdo) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
				.prepareStatement("update `"+db+"`.cotiOdo set id_bodegaEmpresa=?, id_cliente=?, id_proyecto=?," +
								"numero=?,fecha=?, observaciones=?, dctoOdo=? where id=?;");
			smt.setLong(1, cotiOdo.getId_bodegaEmpresa());
			smt.setLong(2, cotiOdo.getId_cliente());
			smt.setLong(3, cotiOdo.getId_proyecto());
			smt.setLong(4, cotiOdo.getNumero());
			smt.setString(5, cotiOdo.getFecha());
			smt.setString(6, cotiOdo.getObservaciones());
			smt.setDouble(7, cotiOdo.getDctoOdo());
			smt.setLong(8, cotiOdo.getId());
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static boolean confirmar(Connection con, String db, String listadoIdCotiConfirmar){
		boolean flag = false;
		try {
			PreparedStatement smt = con
				.prepareStatement("update `"+db+"`.cotiOdo set esModificable = ?, confirmada = ?, fechaConfirmada = ? where id in "+listadoIdCotiConfirmar+";");
			smt.setString(1, "0");
			smt.setString(2, "1");
			smt.setString(3, Fechas.hoy().getFechaStrAAMMDD());
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	
	
	
}









