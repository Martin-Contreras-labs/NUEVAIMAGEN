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

import models.forms.FormCotiza;
import models.utilities.Fechas;


public class Cotizacion {
	public java.util.Date dateCreate;
	public Long id;
	public Long id_bodegaEmpresa;
	public Long id_cliente;
	public Long id_proyecto;
	public Long numero;
	public String fecha;
	public String cotizacionPDF;
	public Double dctoArriendo;
	public Double dctoVenta;
	public Long esModificable;
	public String fechaConfirmada;
	public Long confirmada;
	public String contratoPDF;
	public String ocClientePDF;
	public String numeroOC;
	public String fechaOC;
	public String fechaContrato;
	public String numeroContrato;
	public String usadosEn;
	public String garantiaDoc;
	public String garantiaDet;
	public String garantiaVenc;
	public String garantiaEquiv;
	public String observaciones;
	public String notasAlContrato;
	public Long id_cotizaEstado;
	public String pdfArriendo;
	public String pdfVenta;
	public String pdfArrVta;
	public Long id_ot;
	
	public String listadoPlanos; // SOLO SE OCUPO CON CONCONCRETO
	public String fechaPlanos; // SOLO SE OCUPO CON CONCONCRETO
	public String nomRepresEmpresa; // SOLO SE OCUPO CON CONCONCRETO
	public String rutRepresEmpresa; // SOLO SE OCUPO CON CONCONCRETO
	public String direccionObra; // SOLO SE OCUPO CON CONCONCRETO
	
	public Long id_userCrea;
	public Long id_userModifica;
	public String fechaUserModifica;
	public Long id_sucursal;
	public Long id_comercial;
	public String nameSucursal;
	public String nameComercial;
	
	public Long id_cotizaSolucion;
	public String nameCotizaSolucion;
	
	public String notaCotizaEstado;

	public Cotizacion(Date dateCreate, Long id, Long id_bodegaEmpresa, Long id_cliente, Long id_proyecto, Long numero,
			String fecha, String cotizacionPDF, Double dctoArriendo, Double dctoVenta, Long esModificable,
			String fechaConfirmada, Long confirmada, String contratoPDF, String ocClientePDF, String numeroOC,
			String fechaOC, String fechaContrato, String numeroContrato, String usadosEn, String garantiaDoc,
			String garantiaDet, String garantiaVenc, String garantiaEquiv, String observaciones, String notasAlContrato,
			Long id_cotizaEstado, String pdfArriendo, String pdfVenta, String pdfArrVta, Long id_ot,
			String listadoPlanos, String fechaPlanos, String nomRepresEmpresa, String rutRepresEmpresa,
			String direccionObra, Long id_userCrea, Long id_userModifica, String fechaUserModifica, Long id_sucursal,
			Long id_comercial, String nameSucursal, String nameComercial, Long id_cotizaSolucion, String nameCotizaSolucion,
			String notaCotizaEstado) {
		super();
		this.dateCreate = dateCreate;
		this.id = id;
		this.id_bodegaEmpresa = id_bodegaEmpresa;
		this.id_cliente = id_cliente;
		this.id_proyecto = id_proyecto;
		this.numero = numero;
		this.fecha = fecha;
		this.cotizacionPDF = cotizacionPDF;
		this.dctoArriendo = dctoArriendo;
		this.dctoVenta = dctoVenta;
		this.esModificable = esModificable;
		this.fechaConfirmada = fechaConfirmada;
		this.confirmada = confirmada;
		this.contratoPDF = contratoPDF;
		this.ocClientePDF = ocClientePDF;
		this.numeroOC = numeroOC;
		this.fechaOC = fechaOC;
		this.fechaContrato = fechaContrato;
		this.numeroContrato = numeroContrato;
		this.usadosEn = usadosEn;
		this.garantiaDoc = garantiaDoc;
		this.garantiaDet = garantiaDet;
		this.garantiaVenc = garantiaVenc;
		this.garantiaEquiv = garantiaEquiv;
		this.observaciones = observaciones;
		this.notasAlContrato = notasAlContrato;
		this.id_cotizaEstado = id_cotizaEstado;
		this.pdfArriendo = pdfArriendo;
		this.pdfVenta = pdfVenta;
		this.pdfArrVta = pdfArrVta;
		this.id_ot = id_ot;
		this.listadoPlanos = listadoPlanos;
		this.fechaPlanos = fechaPlanos;
		this.nomRepresEmpresa = nomRepresEmpresa;
		this.rutRepresEmpresa = rutRepresEmpresa;
		this.direccionObra = direccionObra;
		this.id_userCrea = id_userCrea;
		this.id_userModifica = id_userModifica;
		this.fechaUserModifica = fechaUserModifica;
		this.id_sucursal = id_sucursal;
		this.id_comercial = id_comercial;
		this.nameSucursal = nameSucursal;
		this.nameComercial = nameComercial;
		this.id_cotizaSolucion = id_cotizaSolucion;
		this.nameCotizaSolucion = nameCotizaSolucion;
		this.notaCotizaEstado = notaCotizaEstado;
		
	}

	public Cotizacion(FormCotiza form) {
		super();
		this.id = form.id_cotizacion;
		this.id_bodegaEmpresa = form.id_bodegaEmpresa;
		this.id_cliente = form.id_cliente;
		this.id_proyecto = form.id_proyecto;
		this.numero = form.numeroCoti;
		this.fecha = form.fechaCoti;
		this.dctoArriendo = Double.parseDouble(form.dctoArriendo.replaceAll(",", "").replaceAll("%", "").trim())/100;
		this.dctoVenta = Double.parseDouble(form.dctoVenta.replaceAll(",", "").replaceAll("%", "").trim())/100;
		this.observaciones = form.observaciones;
		this.id_sucursal = form.id_sucursal;
		this.id_comercial = form.id_comercial;
		this.id_cotizaSolucion = form.id_cotizaSolucion;
	}
	
	public Cotizacion() {
		super();
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

	public String getCotizacionPDF() {
		return cotizacionPDF;
	}

	public void setCotizacionPDF(String cotizacionPDF) {
		this.cotizacionPDF = cotizacionPDF;
	}

	public Double getDctoArriendo() {
		return dctoArriendo;
	}

	public void setDctoArriendo(Double dctoArriendo) {
		this.dctoArriendo = dctoArriendo;
	}

	public Double getDctoVenta() {
		return dctoVenta;
	}

	public void setDctoVenta(Double dctoVenta) {
		this.dctoVenta = dctoVenta;
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

	public String getUsadosEn() {
		return usadosEn;
	}

	public void setUsadosEn(String usadosEn) {
		this.usadosEn = usadosEn;
	}

	public String getGarantiaDoc() {
		return garantiaDoc;
	}

	public void setGarantiaDoc(String garantiaDoc) {
		this.garantiaDoc = garantiaDoc;
	}

	public String getGarantiaDet() {
		return garantiaDet;
	}

	public void setGarantiaDet(String garantiaDet) {
		this.garantiaDet = garantiaDet;
	}

	public String getGarantiaVenc() {
		return garantiaVenc;
	}

	public void setGarantiaVenc(String garantiaVenc) {
		this.garantiaVenc = garantiaVenc;
	}

	public String getGarantiaEquiv() {
		return garantiaEquiv;
	}

	public void setGarantiaEquiv(String garantiaEquiv) {
		this.garantiaEquiv = garantiaEquiv;
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

	public String getPdfArriendo() {
		return pdfArriendo;
	}

	public void setPdfArriendo(String pdfArriendo) {
		this.pdfArriendo = pdfArriendo;
	}

	public String getPdfVenta() {
		return pdfVenta;
	}

	public void setPdfVenta(String pdfVenta) {
		this.pdfVenta = pdfVenta;
	}
	
	public String getPdfArrVta() {
		return pdfArrVta;
	}

	public void setPdfArrVta(String pdfArrVta) {
		this.pdfArrVta = pdfArrVta;
	}

	public Long getId_ot() {
		return id_ot;
	}

	public void setId_ot(Long id_ot) {
		this.id_ot = id_ot;
	}

	public String getListadoPlanos() {
		return listadoPlanos;
	}

	public void setListadoPlanos(String listadoPlanos) {
		this.listadoPlanos = listadoPlanos;
	}

	public String getFechaPlanos() {
		return fechaPlanos;
	}

	public void setFechaPlanos(String fechaPlanos) {
		this.fechaPlanos = fechaPlanos;
	}

	public String getNomRepresEmpresa() {
		return nomRepresEmpresa;
	}

	public void setNomRepresEmpresa(String nomRepresEmpresa) {
		this.nomRepresEmpresa = nomRepresEmpresa;
	}

	public String getRutRepresEmpresa() {
		return rutRepresEmpresa;
	}

	public void setRutRepresEmpresa(String rutRepresEmpresa) {
		this.rutRepresEmpresa = rutRepresEmpresa;
	}

	public String getDireccionObra() {
		return direccionObra;
	}

	public void setDireccionObra(String direccionObra) {
		this.direccionObra = direccionObra;
	}

	public Long getId_userCrea() {
		return id_userCrea;
	}

	public void setId_userCrea(Long id_userCrea) {
		this.id_userCrea = id_userCrea;
	}

	public Long getId_userModifica() {
		return id_userModifica;
	}

	public void setId_userModifica(Long id_userModifica) {
		this.id_userModifica = id_userModifica;
	}

	public String getFechaUserModifica() {
		return fechaUserModifica;
	}

	public void setFechaUserModifica(String fechaUserModifica) {
		this.fechaUserModifica = fechaUserModifica;
	}

	public Long getId_sucursal() {
		return id_sucursal;
	}

	public void setId_sucursal(Long id_sucursal) {
		this.id_sucursal = id_sucursal;
	}
	
	public Long getId_comercial() {
		return id_comercial;
	}

	public void setId_comercial(Long id_comercial) {
		this.id_comercial = id_comercial;
	}

	public String getSucursal() {
		return nameSucursal;
	}

	public void setSucursal(String sucursal) {
		this.nameSucursal = sucursal;
	}

	public String getComercial() {
		return nameComercial;
	}

	public void setComercial(String comercial) {
		this.nameComercial = comercial;
	}

	public String getNameSucursal() {
		return nameSucursal;
	}

	public void setNameSucursal(String nameSucursal) {
		this.nameSucursal = nameSucursal;
	}

	public String getNameComercial() {
		return nameComercial;
	}

	public void setNameComercial(String nameComercial) {
		this.nameComercial = nameComercial;
	}

	public Long getId_cotizaSolucion() {
		return id_cotizaSolucion;
	}

	public void setId_cotizaSolucion(Long id_cotizaSolucion) {
		this.id_cotizaSolucion = id_cotizaSolucion;
	}

	public String getNameCotizaSolucion() {
		return nameCotizaSolucion;
	}

	public void setNameCotizaSolucion(String nameCotizaSolucion) {
		this.nameCotizaSolucion = nameCotizaSolucion;
	}

	public String getNotaCotizaEstado() {
		return notaCotizaEstado;
	}

	public void setNotaCotizaEstado(String notaCotizaEstado) {
		this.notaCotizaEstado = notaCotizaEstado;
	}

	static SimpleDateFormat myformatfecha = new SimpleDateFormat("dd-MM-yyyy");
	static DecimalFormat myformatdouble = new DecimalFormat("#,##0.00");
	static DecimalFormat myformatdouble2 = new DecimalFormat("#,##0.00");
	static DecimalFormat myformattasa = new DecimalFormat("#,##0.00 %");
	
	public static Long anioPrimeraCotizacion(Connection con, String db) {
		Fechas hoy = Fechas.hoy();
		String[] aux = hoy.getFechaStrAAMMDD().split("-");
		Long year = Long.parseLong(aux[0]);
		try {
			PreparedStatement smt = con
					.prepareStatement(" SELECT year(min(fecha)) FROM `"+db+"`.cotizacion;" );
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
	
	public static boolean modifyXCampo(Connection con, String db, String campo, String valor, Long id_cotizacion) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
					.prepareStatement("UPDATE `"+db+"`.cotizacion SET `"+campo+"` = ? WHERE id = ?;");
			smt.setString(1, valor.trim());
			smt.setLong(2, id_cotizacion);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static Map<Long,List<Long>> mapIdvsNumCotiSucur(Connection con, String db){
		Map<Long,List<Long>> map = new HashMap<Long,List<Long>>();
		try{
			PreparedStatement smt = con
					.prepareStatement(" select id, numero, id_sucursal from `"+db+"`.cotizacion  where id>0;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				List<Long> aux = new ArrayList<Long>();
				aux.add(rs.getLong(2));
				aux.add(rs.getLong(3));
				map.put(rs.getLong(1), aux);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(map);
	}
	
	
	public static Map<Long,Cotizacion> mapAll(Connection con, String db){
		Map<Long,Cotizacion> map = new HashMap<Long,Cotizacion>();
		List<Cotizacion> lista = Cotizacion.all(con, db);
		lista.forEach(l->{
			map.put(l.getId(), l);
		});
		return(map);
	}
	
	public static Map<Long,Cotizacion> mapAllConfirm(Connection con, String db){
		Map<Long,Cotizacion> map = new HashMap<Long,Cotizacion>();
		List<Cotizacion> lista = Cotizacion.allConfirmadasCon_o_SinOt(con, db);
		for(Cotizacion x: lista) {
			map.put(x.id, x);
		}
		
		
		return(map);
	}
	
	public static Map<Long,Cotizacion> mapAllConfirmadasUnaBodega(Connection con, String db, Long id_bodegaEmpresa){
		Map<Long,Cotizacion> map = new HashMap<Long,Cotizacion>();
		List<Cotizacion> lista = Cotizacion.all(con, db);
		lista.forEach(l->{
			if((long)l.getEsModificable()==(long)0 
					&& (long)l.getConfirmada()==(long)1 
					&& ((long)l.getId_cotizaEstado()==(long)1 || (long)l.getId_cotizaEstado()==(long)-1 || (long)l.getId_cotizaEstado()==(long)-2) 
					&& (long)l.getId_bodegaEmpresa()==id_bodegaEmpresa) {
				map.put(l.getId(), l);
			}
		});
		return(map);
	}
	
	public static Map<Long,Cotizacion> mapAllConOt(Connection con, String db){
		Map<Long,Cotizacion> map = new HashMap<Long,Cotizacion>();
		List<Cotizacion> lista = Cotizacion.all(con, db);
		lista.forEach(l->{
			if((long)l.getId_ot()>(long)0) {
				map.put(l.getId(), l);
			}
		});
		return(map);
	}
	
	public static Map<Long,Long> mapAllIdBodConOt(Connection con, String db){
		Map<Long,Long> map = new HashMap<Long,Long>();
		List<Cotizacion> lista = Cotizacion.all(con, db);
		lista.forEach(l->{
			if((long)l.getId_ot()>(long)0) {
				map.put(l.getId_bodegaEmpresa(), l.getId_bodegaEmpresa());
			}
		});
		return(map);
	}
	
	public static List<Cotizacion> all(Connection con, String db){
		List<Cotizacion> lista = new ArrayList<Cotizacion>();
		try{
			PreparedStatement smt = con
					.prepareStatement(" select "
							+ " id, id_bodegaEmpresa, id_cliente, id_proyecto, numero, fecha, cotizacionPDF, dctoArriendo, dctoVenta, esModificable, "
							+ " ifnull(fechaConfirmada,''), confirmada, contratoPDF, ocClientePDF, numeroOC, ifnull(fechaOC,''), ifnull(fechaContrato,''), "
							+ " numeroContrato, ifnull(usadosEn,''), ifnull(garantiaDoc,''), ifnull(garantiaDet,''), ifnull(garantiaVenc,''), "
							+ " ifnull(garantiaEquiv,''), ifnull(observaciones,''), ifnull(notasAlContrato,''), id_cotizaEstado, pdfArriendo, pdfVenta, dateCreate, id_ot, pdfArrVta, "
							+ " listadoPlanos, fechaPlanos, nomRepresEmpresa, rutRepresEmpresa, direccionObra, id_userCrea, id_userModifica, ifnull(fechaUserModifica,''), "
							+ " id_sucursal, id_comercial, id_cotizaSolucion, notaCotizaEstado "
							+ " from `"+db+"`.cotizacion  where id>0;");
			ResultSet rs = smt.executeQuery();
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
			Map<Long,CotizaSolucion> mapCotizaSolucion = CotizaSolucion.mapAll(con, db);
			while (rs.next()) {
				String nameSucursal = "Sin Sucursal";
				String nameComercial = "Sin Comercial";
				Sucursal sucursal = mapSucursal.get(rs.getLong(40));
				Comercial comercial = mapComercial.get(rs.getLong(41));
				if(sucursal!=null) {
					nameSucursal = sucursal.nombre;
				}
				if(comercial!=null) {
					nameComercial = comercial.nameUsuario;
				}
				String nameSolucion = "";
				CotizaSolucion cotizaSolucion = mapCotizaSolucion.get(rs.getLong(42));
				if(cotizaSolucion!=null) {
					nameSolucion = cotizaSolucion.getSolucion();
				}
				  
				lista.add(new Cotizacion(rs.getTimestamp(29), rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getLong(4),rs.getLong(5),rs.getString(6),rs.getString(7),
					rs.getDouble(8),rs.getDouble(9),rs.getLong(10),rs.getString(11),rs.getLong(12),rs.getString(13), rs.getString(14),rs.getString(15),
					rs.getString(16),rs.getString(17),rs.getString(18),rs.getString(19),rs.getString(20),rs.getString(21),rs.getString(22),rs.getString(23),
					rs.getString(24),rs.getString(25),rs.getLong(26),rs.getString(27),rs.getString(28), rs.getString(31), rs.getLong(30),
					rs.getString(32),rs.getString(33),rs.getString(34),rs.getString(35),rs.getString(36),rs.getLong(37),rs.getLong(38),rs.getString(39),
					rs.getLong(40), rs.getLong(41), nameSucursal, nameComercial, rs.getLong(42), nameSolucion, rs.getString(43)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(lista);
	}
	
	public static List<Cotizacion> allDesdeHasta(Connection con, String db, String desde, String hasta){
		List<Cotizacion> lista = new ArrayList<Cotizacion>();
		try{
			PreparedStatement smt = con
					.prepareStatement(" select "
							+ " id, id_bodegaEmpresa, id_cliente, id_proyecto, numero, fecha, cotizacionPDF, dctoArriendo, dctoVenta, esModificable, "
							+ " ifnull(fechaConfirmada,''), confirmada, contratoPDF, ocClientePDF, numeroOC, ifnull(fechaOC,''), ifnull(fechaContrato,''), "
							+ " numeroContrato, ifnull(usadosEn,''), ifnull(garantiaDoc,''), ifnull(garantiaDet,''), ifnull(garantiaVenc,''), "
							+ " ifnull(garantiaEquiv,''), ifnull(observaciones,''), ifnull(notasAlContrato,''), id_cotizaEstado, pdfArriendo, pdfVenta, dateCreate, id_ot, pdfArrVta, "
							+ " listadoPlanos, fechaPlanos, nomRepresEmpresa, rutRepresEmpresa, direccionObra, id_userCrea, id_userModifica, ifnull(fechaUserModifica,''), "
							+ " id_sucursal, id_comercial, id_cotizaSolucion, notaCotizaEstado "
							+ " from `"+db+"`.cotizacion  where id>0 "
							+ " and (cotizacion.fecha between ? and ?);");
					smt.setString(1, desde);
					smt.setString(2, hasta);
			ResultSet rs = smt.executeQuery();
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
			Map<Long,CotizaSolucion> mapCotizaSolucion = CotizaSolucion.mapAll(con, db);
			while (rs.next()) {
				String nameSucursal = "Sin Sucursal";
				String nameComercial = "Sin Comercial";
				Sucursal sucursal = mapSucursal.get(rs.getLong(40));
				Comercial comercial = mapComercial.get(rs.getLong(41));
				if(sucursal!=null) {
					nameSucursal = sucursal.nombre;
				}
				if(comercial!=null) {
					nameComercial = comercial.nameUsuario;
				}
				String nameSolucion = "";
				CotizaSolucion cotizaSolucion = mapCotizaSolucion.get(rs.getLong(42));
				if(cotizaSolucion!=null) {
					nameSolucion = cotizaSolucion.getSolucion();
				}
				lista.add(new Cotizacion(rs.getTimestamp(29), rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getLong(4),rs.getLong(5),rs.getString(6),rs.getString(7),
					rs.getDouble(8),rs.getDouble(9),rs.getLong(10),rs.getString(11),rs.getLong(12),rs.getString(13), rs.getString(14),rs.getString(15),
					rs.getString(16),rs.getString(17),rs.getString(18),rs.getString(19),rs.getString(20),rs.getString(21),rs.getString(22),rs.getString(23),
					rs.getString(24),rs.getString(25),rs.getLong(26),rs.getString(27),rs.getString(28), rs.getString(31), rs.getLong(30),
					rs.getString(32),rs.getString(33),rs.getString(34),rs.getString(35),rs.getString(36),rs.getLong(37),rs.getLong(38),rs.getString(39),
					rs.getLong(40), rs.getLong(41), nameSucursal, nameComercial, rs.getLong(42), nameSolucion, rs.getString(43)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(lista);
	}
	
	public static List<Cotizacion> allEnEstado1(Connection con, String db){
		List<Cotizacion> lista = new ArrayList<Cotizacion>();
		try{
			PreparedStatement smt = con
					.prepareStatement(" select "
							+ " id, id_bodegaEmpresa, id_cliente, id_proyecto, numero, fecha, cotizacionPDF, dctoArriendo, dctoVenta, esModificable, "
							+ " ifnull(fechaConfirmada,''), confirmada, contratoPDF, ocClientePDF, numeroOC, ifnull(fechaOC,''), ifnull(fechaContrato,''), "
							+ " numeroContrato, ifnull(usadosEn,''), ifnull(garantiaDoc,''), ifnull(garantiaDet,''), ifnull(garantiaVenc,''), "
							+ " ifnull(garantiaEquiv,''), ifnull(observaciones,''), ifnull(notasAlContrato,''), id_cotizaEstado, pdfArriendo, pdfVenta, dateCreate, id_ot, pdfArrVta, "
							+ " listadoPlanos, fechaPlanos, nomRepresEmpresa, rutRepresEmpresa, direccionObra, id_userCrea, id_userModifica, ifnull(fechaUserModifica,''), "
							+ " id_sucursal, id_comercial, id_cotizaSolucion, notaCotizaEstado "
							+ " from `"+db+"`.cotizacion  where id>0 and (cotizacion.id_cotizaEstado = 1 or cotizacion.id_cotizaEstado = -1) order by fecha desc, numero desc;");
			ResultSet rs = smt.executeQuery();
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
			Map<Long,CotizaSolucion> mapCotizaSolucion = CotizaSolucion.mapAll(con, db);
			while (rs.next()) {
				String nameSucursal = "Sin Sucursal";
				String nameComercial = "Sin Comercial";
				Sucursal sucursal = mapSucursal.get(rs.getLong(40));
				Comercial comercial = mapComercial.get(rs.getLong(41));
				if(sucursal!=null) {
					nameSucursal = sucursal.nombre;
				}
				if(comercial!=null) {
					nameComercial = comercial.nameUsuario;
				}
				String nameSolucion = "";
				CotizaSolucion cotizaSolucion = mapCotizaSolucion.get(rs.getLong(42));
				if(cotizaSolucion!=null) {
					nameSolucion = cotizaSolucion.getSolucion();
				}
				lista.add(new Cotizacion(rs.getTimestamp(29), rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getLong(4),rs.getLong(5),rs.getString(6),rs.getString(7),
					rs.getDouble(8),rs.getDouble(9),rs.getLong(10),rs.getString(11),rs.getLong(12),rs.getString(13), rs.getString(14),rs.getString(15),
					rs.getString(16),rs.getString(17),rs.getString(18),rs.getString(19),rs.getString(20),rs.getString(21),rs.getString(22),rs.getString(23),
					rs.getString(24),rs.getString(25),rs.getLong(26),rs.getString(27),rs.getString(28), rs.getString(31), rs.getLong(30),
					rs.getString(32),rs.getString(33),rs.getString(34),rs.getString(35),rs.getString(36),rs.getLong(37),rs.getLong(38),rs.getString(39),
					rs.getLong(40), rs.getLong(41), nameSucursal, nameComercial, rs.getLong(42), nameSolucion, rs.getString(43)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(lista);
	}
	
	public static List<Cotizacion> allSinConfirmar(Connection con, String db) {
		List<Cotizacion> lista = new ArrayList<Cotizacion>();
		try{
			PreparedStatement smt = con
					.prepareStatement(" select "
							+ " id, id_bodegaEmpresa, id_cliente, id_proyecto, numero, fecha, cotizacionPDF, dctoArriendo, dctoVenta, esModificable, "
							+ " ifnull(fechaConfirmada,''), confirmada, contratoPDF, ocClientePDF, numeroOC, ifnull(fechaOC,''), ifnull(fechaContrato,''), "
							+ " numeroContrato, ifnull(usadosEn,''), ifnull(garantiaDoc,''), ifnull(garantiaDet,''), ifnull(garantiaVenc,''), "
							+ " ifnull(garantiaEquiv,''), ifnull(observaciones,''), ifnull(notasAlContrato,''), id_cotizaEstado, pdfArriendo, pdfVenta, dateCreate, id_ot, pdfArrVta, "
							+ " listadoPlanos, fechaPlanos, nomRepresEmpresa, rutRepresEmpresa, direccionObra, id_userCrea, id_userModifica, ifnull(fechaUserModifica,''), "
							+ " id_sucursal, id_comercial, id_cotizaSolucion, notaCotizaEstado "
							+ " from `"+db+"`.cotizacion "
							+ " where id>0 and esModificable=1 and (cotizacion.id_cotizaEstado = 1 or cotizacion.id_cotizaEstado = -1);");
			ResultSet rs = smt.executeQuery();
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
			Map<Long,CotizaSolucion> mapCotizaSolucion = CotizaSolucion.mapAll(con, db);
			while (rs.next()) {
				String nameSucursal = "Sin Sucursal";
				String nameComercial = "Sin Comercial";
				Sucursal sucursal = mapSucursal.get(rs.getLong(40));
				Comercial comercial = mapComercial.get(rs.getLong(41));
				if(sucursal!=null) {
					nameSucursal = sucursal.nombre;
				}
				if(comercial!=null) {
					nameComercial = comercial.nameUsuario;
				}
				String nameSolucion = "";
				CotizaSolucion cotizaSolucion = mapCotizaSolucion.get(rs.getLong(42));
				if(cotizaSolucion!=null) {
					nameSolucion = cotizaSolucion.getSolucion();
				}
				lista.add(new Cotizacion(rs.getTimestamp(29), rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getLong(4),rs.getLong(5),rs.getString(6),rs.getString(7),
					rs.getDouble(8),rs.getDouble(9),rs.getLong(10),rs.getString(11),rs.getLong(12),rs.getString(13), rs.getString(14),rs.getString(15),
					rs.getString(16),rs.getString(17),rs.getString(18),rs.getString(19),rs.getString(20),rs.getString(21),rs.getString(22),rs.getString(23),
					rs.getString(24),rs.getString(25),rs.getLong(26),rs.getString(27),rs.getString(28), rs.getString(31), rs.getLong(30),
					rs.getString(32),rs.getString(33),rs.getString(34),rs.getString(35),rs.getString(36),rs.getLong(37),rs.getLong(38),rs.getString(39),
					rs.getLong(40), rs.getLong(41), nameSucursal, nameComercial, rs.getLong(42), nameSolucion, rs.getString(43)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<Cotizacion> allSinConfirmarDesdeHasta(Connection con, String db, String desde, String hasta) {
		List<Cotizacion> lista = new ArrayList<Cotizacion>();
		try{
			PreparedStatement smt = con
					.prepareStatement(" select "
							+ " id, id_bodegaEmpresa, id_cliente, id_proyecto, numero, fecha, cotizacionPDF, dctoArriendo, dctoVenta, esModificable, "
							+ " ifnull(fechaConfirmada,''), confirmada, contratoPDF, ocClientePDF, numeroOC, ifnull(fechaOC,''), ifnull(fechaContrato,''), "
							+ " numeroContrato, ifnull(usadosEn,''), ifnull(garantiaDoc,''), ifnull(garantiaDet,''), ifnull(garantiaVenc,''), "
							+ " ifnull(garantiaEquiv,''), ifnull(observaciones,''), ifnull(notasAlContrato,''), id_cotizaEstado, pdfArriendo, pdfVenta, dateCreate, id_ot, pdfArrVta, "
							+ " listadoPlanos, fechaPlanos, nomRepresEmpresa, rutRepresEmpresa, direccionObra, id_userCrea, id_userModifica, ifnull(fechaUserModifica,''), "
							+ " id_sucursal, id_comercial, id_cotizaSolucion, notaCotizaEstado "
							+ " from `"+db+"`.cotizacion "
							+ " where id>0 and esModificable=1 and (cotizacion.id_cotizaEstado = 1 or cotizacion.id_cotizaEstado = -1) "
							+ " and (cotizacion.fecha between ? and ?);");
			smt.setString(1, desde);
			smt.setString(2, hasta);
			ResultSet rs = smt.executeQuery();
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
			Map<Long,CotizaSolucion> mapCotizaSolucion = CotizaSolucion.mapAll(con, db);
			while (rs.next()) {
				String nameSucursal = "Sin Sucursal";
				String nameComercial = "Sin Comercial";
				Sucursal sucursal = mapSucursal.get(rs.getLong(40));
				Comercial comercial = mapComercial.get(rs.getLong(41));
				if(sucursal!=null) {
					nameSucursal = sucursal.nombre;
				}
				if(comercial!=null) {
					nameComercial = comercial.nameUsuario;
				}
				String nameSolucion = "";
				CotizaSolucion cotizaSolucion = mapCotizaSolucion.get(rs.getLong(42));
				if(cotizaSolucion!=null) {
					nameSolucion = cotizaSolucion.getSolucion();
				}
				lista.add(new Cotizacion(rs.getTimestamp(29), rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getLong(4),rs.getLong(5),rs.getString(6),rs.getString(7),
					rs.getDouble(8),rs.getDouble(9),rs.getLong(10),rs.getString(11),rs.getLong(12),rs.getString(13), rs.getString(14),rs.getString(15),
					rs.getString(16),rs.getString(17),rs.getString(18),rs.getString(19),rs.getString(20),rs.getString(21),rs.getString(22),rs.getString(23),
					rs.getString(24),rs.getString(25),rs.getLong(26),rs.getString(27),rs.getString(28), rs.getString(31), rs.getLong(30),
					rs.getString(32),rs.getString(33),rs.getString(34),rs.getString(35),rs.getString(36),rs.getLong(37),rs.getLong(38),rs.getString(39),
					rs.getLong(40), rs.getLong(41), nameSucursal, nameComercial, rs.getLong(42), nameSolucion, rs.getString(43)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<Cotizacion> allParaCambiarEstado(Connection con, String db) {
		List<Cotizacion> lista = new ArrayList<Cotizacion>();
		try{
			PreparedStatement smt = con
					.prepareStatement(" select "
							+ " id, id_bodegaEmpresa, id_cliente, id_proyecto, numero, fecha, cotizacionPDF, dctoArriendo, dctoVenta, esModificable, "
							+ " ifnull(fechaConfirmada,''), confirmada, contratoPDF, ocClientePDF, numeroOC, ifnull(fechaOC,''), ifnull(fechaContrato,''), "
							+ " numeroContrato, ifnull(usadosEn,''), ifnull(garantiaDoc,''), ifnull(garantiaDet,''), ifnull(garantiaVenc,''), "
							+ " ifnull(garantiaEquiv,''), ifnull(observaciones,''), ifnull(notasAlContrato,''), id_cotizaEstado, pdfArriendo, pdfVenta, dateCreate, id_ot, pdfArrVta, "
							+ " listadoPlanos, fechaPlanos, nomRepresEmpresa, rutRepresEmpresa, direccionObra, id_userCrea, id_userModifica, ifnull(fechaUserModifica,''), "
							+ " id_sucursal, id_comercial, id_cotizaSolucion, notaCotizaEstado "
							+ " from `"+db+"`.cotizacion "
							+ " where id>0 and esModificable=1 and id_cotizaEstado > 0;");
			ResultSet rs = smt.executeQuery();
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
			Map<Long,CotizaSolucion> mapCotizaSolucion = CotizaSolucion.mapAll(con, db);
			while (rs.next()) {
				String nameSucursal = "Sin Sucursal";
				String nameComercial = "Sin Comercial";
				Sucursal sucursal = mapSucursal.get(rs.getLong(40));
				Comercial comercial = mapComercial.get(rs.getLong(41));
				if(sucursal!=null) {
					nameSucursal = sucursal.nombre;
				}
				if(comercial!=null) {
					nameComercial = comercial.nameUsuario;
				}
				String nameSolucion = "";
				CotizaSolucion cotizaSolucion = mapCotizaSolucion.get(rs.getLong(42));
				if(cotizaSolucion!=null) {
					nameSolucion = cotizaSolucion.getSolucion();
				}
				lista.add(new Cotizacion(rs.getTimestamp(29), rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getLong(4),rs.getLong(5),rs.getString(6),rs.getString(7),
					rs.getDouble(8),rs.getDouble(9),rs.getLong(10),rs.getString(11),rs.getLong(12),rs.getString(13), rs.getString(14),rs.getString(15),
					rs.getString(16),rs.getString(17),rs.getString(18),rs.getString(19),rs.getString(20),rs.getString(21),rs.getString(22),rs.getString(23),
					rs.getString(24),rs.getString(25),rs.getLong(26),rs.getString(27),rs.getString(28), rs.getString(31), rs.getLong(30),
					rs.getString(32),rs.getString(33),rs.getString(34),rs.getString(35),rs.getString(36),rs.getLong(37),rs.getLong(38),rs.getString(39),
					rs.getLong(40), rs.getLong(41), nameSucursal, nameComercial, rs.getLong(42), nameSolucion, rs.getString(43)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<Cotizacion> allParaCambiarEstadoDesdeHasta(Connection con, String db, String desde, String hasta) {
		List<Cotizacion> lista = new ArrayList<Cotizacion>();
		try{
			PreparedStatement smt = con
					.prepareStatement(" select "
							+ " id, id_bodegaEmpresa, id_cliente, id_proyecto, numero, fecha, cotizacionPDF, dctoArriendo, dctoVenta, esModificable, "
							+ " ifnull(fechaConfirmada,''), confirmada, contratoPDF, ocClientePDF, numeroOC, ifnull(fechaOC,''), ifnull(fechaContrato,''), "
							+ " numeroContrato, ifnull(usadosEn,''), ifnull(garantiaDoc,''), ifnull(garantiaDet,''), ifnull(garantiaVenc,''), "
							+ " ifnull(garantiaEquiv,''), ifnull(observaciones,''), ifnull(notasAlContrato,''), id_cotizaEstado, pdfArriendo, pdfVenta, dateCreate, id_ot, pdfArrVta, "
							+ " listadoPlanos, fechaPlanos, nomRepresEmpresa, rutRepresEmpresa, direccionObra, id_userCrea, id_userModifica, ifnull(fechaUserModifica,''), "
							+ " id_sucursal, id_comercial, id_cotizaSolucion, notaCotizaEstado "
							+ " from `"+db+"`.cotizacion "
							+ " where id>0 and esModificable=1 and id_cotizaEstado > 0 "
							+ " and (cotizacion.fecha between ? and ?);");
			smt.setString(1, desde);
			smt.setString(2, hasta);
			ResultSet rs = smt.executeQuery();
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
			Map<Long,CotizaSolucion> mapCotizaSolucion = CotizaSolucion.mapAll(con, db);
			while (rs.next()) {
				String nameSucursal = "Sin Sucursal";
				String nameComercial = "Sin Comercial";
				Sucursal sucursal = mapSucursal.get(rs.getLong(40));
				Comercial comercial = mapComercial.get(rs.getLong(41));
				if(sucursal!=null) {
					nameSucursal = sucursal.nombre;
				}
				if(comercial!=null) {
					nameComercial = comercial.nameUsuario;
				}
				String nameSolucion = "";
				CotizaSolucion cotizaSolucion = mapCotizaSolucion.get(rs.getLong(42));
				if(cotizaSolucion!=null) {
					nameSolucion = cotizaSolucion.getSolucion();
				}
				lista.add(new Cotizacion(rs.getTimestamp(29), rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getLong(4),rs.getLong(5),rs.getString(6),rs.getString(7),
					rs.getDouble(8),rs.getDouble(9),rs.getLong(10),rs.getString(11),rs.getLong(12),rs.getString(13), rs.getString(14),rs.getString(15),
					rs.getString(16),rs.getString(17),rs.getString(18),rs.getString(19),rs.getString(20),rs.getString(21),rs.getString(22),rs.getString(23),
					rs.getString(24),rs.getString(25),rs.getLong(26),rs.getString(27),rs.getString(28), rs.getString(31), rs.getLong(30),
					rs.getString(32),rs.getString(33),rs.getString(34),rs.getString(35),rs.getString(36),rs.getLong(37),rs.getLong(38),rs.getString(39),
					rs.getLong(40), rs.getLong(41), nameSucursal, nameComercial, rs.getLong(42), nameSolucion, rs.getString(43)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<Cotizacion> allConfirmadasSinOt(Connection con, String db) {
		List<Cotizacion> lista = new ArrayList<Cotizacion>();
		try{
			PreparedStatement smt = con
					.prepareStatement(" select "
							+ " id, id_bodegaEmpresa, id_cliente, id_proyecto, numero, fecha, cotizacionPDF, dctoArriendo, dctoVenta, esModificable, "
							+ " ifnull(fechaConfirmada,''), confirmada, contratoPDF, ocClientePDF, numeroOC, ifnull(fechaOC,''), ifnull(fechaContrato,''), "
							+ " numeroContrato, ifnull(usadosEn,''), ifnull(garantiaDoc,''), ifnull(garantiaDet,''), ifnull(garantiaVenc,''), "
							+ " ifnull(garantiaEquiv,''), ifnull(observaciones,''), ifnull(notasAlContrato,''), id_cotizaEstado, pdfArriendo, pdfVenta, dateCreate, id_ot, pdfArrVta, "
							+ " listadoPlanos, fechaPlanos, nomRepresEmpresa, rutRepresEmpresa, direccionObra, id_userCrea, id_userModifica, ifnull(fechaUserModifica,''), "
							+ " id_sucursal, id_comercial, id_cotizaSolucion, notaCotizaEstado "
							+ " from `"+db+"`.cotizacion "
							+ " where id>0 and esModificable=0 and (cotizacion.id_cotizaEstado = 1 or cotizacion.id_cotizaEstado = -1) and id_ot=0;");
			ResultSet rs = smt.executeQuery();
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
			Map<Long,CotizaSolucion> mapCotizaSolucion = CotizaSolucion.mapAll(con, db);
			while (rs.next()) {
				String nameSucursal = "Sin Sucursal";
				String nameComercial = "Sin Comercial";
				Sucursal sucursal = mapSucursal.get(rs.getLong(40));
				Comercial comercial = mapComercial.get(rs.getLong(41));
				if(sucursal!=null) {
					nameSucursal = sucursal.nombre;
				}
				if(comercial!=null) {
					nameComercial = comercial.nameUsuario;
				}
				String nameSolucion = "";
				CotizaSolucion cotizaSolucion = mapCotizaSolucion.get(rs.getLong(42));
				if(cotizaSolucion!=null) {
					nameSolucion = cotizaSolucion.getSolucion();
				}
				lista.add(new Cotizacion(rs.getTimestamp(29), rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getLong(4),rs.getLong(5),rs.getString(6),rs.getString(7),
					rs.getDouble(8),rs.getDouble(9),rs.getLong(10),rs.getString(11),rs.getLong(12),rs.getString(13), rs.getString(14),rs.getString(15),
					rs.getString(16),rs.getString(17),rs.getString(18),rs.getString(19),rs.getString(20),rs.getString(21),rs.getString(22),rs.getString(23),
					rs.getString(24),rs.getString(25),rs.getLong(26),rs.getString(27),rs.getString(28), rs.getString(31), rs.getLong(30),
					rs.getString(32),rs.getString(33),rs.getString(34),rs.getString(35),rs.getString(36),rs.getLong(37),rs.getLong(38),rs.getString(39),
					rs.getLong(40), rs.getLong(41), nameSucursal, nameComercial, rs.getLong(42), nameSolucion, rs.getString(43)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<Cotizacion> allSinConfirmarYconfirmadasSinOt(Connection con, String db) {
		List<Cotizacion> lista = new ArrayList<Cotizacion>();
		try{
			PreparedStatement smt = con
					.prepareStatement(" select "
							+ " id, id_bodegaEmpresa, id_cliente, id_proyecto, numero, fecha, cotizacionPDF, dctoArriendo, dctoVenta, esModificable, "
							+ " ifnull(fechaConfirmada,''), confirmada, contratoPDF, ocClientePDF, numeroOC, ifnull(fechaOC,''), ifnull(fechaContrato,''), "
							+ " numeroContrato, ifnull(usadosEn,''), ifnull(garantiaDoc,''), ifnull(garantiaDet,''), ifnull(garantiaVenc,''), "
							+ " ifnull(garantiaEquiv,''), ifnull(observaciones,''), ifnull(notasAlContrato,''), id_cotizaEstado, pdfArriendo, pdfVenta, dateCreate, id_ot, pdfArrVta, "
							+ " listadoPlanos, fechaPlanos, nomRepresEmpresa, rutRepresEmpresa, direccionObra, id_userCrea, id_userModifica, ifnull(fechaUserModifica,''), "
							+ " id_sucursal, id_comercial, id_cotizaSolucion, notaCotizaEstado "
							+ " from `"+db+"`.cotizacion "
							+ " where id>0 and (cotizacion.id_cotizaEstado = 1 or cotizacion.id_cotizaEstado = -1) and id_ot=0;");
			ResultSet rs = smt.executeQuery();
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
			Map<Long,CotizaSolucion> mapCotizaSolucion = CotizaSolucion.mapAll(con, db);
			while (rs.next()) {
				String nameSucursal = "Sin Sucursal";
				String nameComercial = "Sin Comercial";
				Sucursal sucursal = mapSucursal.get(rs.getLong(40));
				Comercial comercial = mapComercial.get(rs.getLong(41));
				if(sucursal!=null) {
					nameSucursal = sucursal.nombre;
				}
				if(comercial!=null) {
					nameComercial = comercial.nameUsuario;
				}
				String nameSolucion = "";
				CotizaSolucion cotizaSolucion = mapCotizaSolucion.get(rs.getLong(42));
				if(cotizaSolucion!=null) {
					nameSolucion = cotizaSolucion.getSolucion();
				}
				lista.add(new Cotizacion(rs.getTimestamp(29), rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getLong(4),rs.getLong(5),rs.getString(6),rs.getString(7),
					rs.getDouble(8),rs.getDouble(9),rs.getLong(10),rs.getString(11),rs.getLong(12),rs.getString(13), rs.getString(14),rs.getString(15),
					rs.getString(16),rs.getString(17),rs.getString(18),rs.getString(19),rs.getString(20),rs.getString(21),rs.getString(22),rs.getString(23),
					rs.getString(24),rs.getString(25),rs.getLong(26),rs.getString(27),rs.getString(28), rs.getString(31), rs.getLong(30),
					rs.getString(32),rs.getString(33),rs.getString(34),rs.getString(35),rs.getString(36),rs.getLong(37),rs.getLong(38),rs.getString(39),
					rs.getLong(40), rs.getLong(41), nameSucursal, nameComercial, rs.getLong(42), nameSolucion, rs.getString(43)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<Cotizacion> allConfirmadasCon_o_SinOt(Connection con, String db) {
		List<Cotizacion> lista = new ArrayList<Cotizacion>();
		try{
			PreparedStatement smt = con
					.prepareStatement(" select "
							+ " id, id_bodegaEmpresa, id_cliente, id_proyecto, numero, fecha, cotizacionPDF, dctoArriendo, dctoVenta, esModificable, "
							+ " ifnull(fechaConfirmada,''), confirmada, contratoPDF, ocClientePDF, numeroOC, ifnull(fechaOC,''), ifnull(fechaContrato,''), "
							+ " numeroContrato, ifnull(usadosEn,''), ifnull(garantiaDoc,''), ifnull(garantiaDet,''), ifnull(garantiaVenc,''), "
							+ " ifnull(garantiaEquiv,''), ifnull(observaciones,''), ifnull(notasAlContrato,''), id_cotizaEstado, pdfArriendo, pdfVenta, dateCreate, id_ot, pdfArrVta, "
							+ " listadoPlanos, fechaPlanos, nomRepresEmpresa, rutRepresEmpresa, direccionObra, id_userCrea, id_userModifica, ifnull(fechaUserModifica,''), "
							+ " id_sucursal, id_comercial, id_cotizaSolucion, notaCotizaEstado "
							+ " from `"+db+"`.cotizacion "
							+ " where id>0 and esModificable=0;");
			ResultSet rs = smt.executeQuery();
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
			Map<Long,CotizaSolucion> mapCotizaSolucion = CotizaSolucion.mapAll(con, db);
			while (rs.next()) {
				String nameSucursal = "Sin Sucursal";
				String nameComercial = "Sin Comercial";
				Sucursal sucursal = mapSucursal.get(rs.getLong(40));
				Comercial comercial = mapComercial.get(rs.getLong(41));
				if(sucursal!=null) {
					nameSucursal = sucursal.nombre;
				}
				if(comercial!=null) {
					nameComercial = comercial.nameUsuario;
				}
				String nameSolucion = "";
				CotizaSolucion cotizaSolucion = mapCotizaSolucion.get(rs.getLong(42));
				if(cotizaSolucion!=null) {
					nameSolucion = cotizaSolucion.getSolucion();
				}
				lista.add(new Cotizacion(rs.getTimestamp(29), rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getLong(4),rs.getLong(5),rs.getString(6),rs.getString(7),
					rs.getDouble(8),rs.getDouble(9),rs.getLong(10),rs.getString(11),rs.getLong(12),rs.getString(13), rs.getString(14),rs.getString(15),
					rs.getString(16),rs.getString(17),rs.getString(18),rs.getString(19),rs.getString(20),rs.getString(21),rs.getString(22),rs.getString(23),
					rs.getString(24),rs.getString(25),rs.getLong(26),rs.getString(27),rs.getString(28), rs.getString(31), rs.getLong(30),
					rs.getString(32),rs.getString(33),rs.getString(34),rs.getString(35),rs.getString(36),rs.getLong(37),rs.getLong(38),rs.getString(39),
					rs.getLong(40), rs.getLong(41), nameSucursal, nameComercial, rs.getLong(42), nameSolucion, rs.getString(43)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static List<Cotizacion> allConfirmadasCon_o_SinOtDesdeHasta(Connection con, String db, String desde, String hasta) {
		List<Cotizacion> lista = new ArrayList<Cotizacion>();
		try{
			PreparedStatement smt = con
					.prepareStatement(" select "
							+ " id, id_bodegaEmpresa, id_cliente, id_proyecto, numero, fecha, cotizacionPDF, dctoArriendo, dctoVenta, esModificable, "
							+ " ifnull(fechaConfirmada,''), confirmada, contratoPDF, ocClientePDF, numeroOC, ifnull(fechaOC,''), ifnull(fechaContrato,''), "
							+ " numeroContrato, ifnull(usadosEn,''), ifnull(garantiaDoc,''), ifnull(garantiaDet,''), ifnull(garantiaVenc,''), "
							+ " ifnull(garantiaEquiv,''), ifnull(observaciones,''), ifnull(notasAlContrato,''), id_cotizaEstado, pdfArriendo, pdfVenta, dateCreate, id_ot, pdfArrVta, "
							+ " listadoPlanos, fechaPlanos, nomRepresEmpresa, rutRepresEmpresa, direccionObra, id_userCrea, id_userModifica, ifnull(fechaUserModifica,''), "
							+ " id_sucursal, id_comercial, id_cotizaSolucion, notaCotizaEstado "
							+ " from `"+db+"`.cotizacion "
							+ " where id>0 and esModificable=0 and (cotizacion.fecha between ? and ?);");
			smt.setString(1, desde);
			smt.setString(2, hasta);
			ResultSet rs = smt.executeQuery();
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
			Map<Long,CotizaSolucion> mapCotizaSolucion = CotizaSolucion.mapAll(con, db);
			while (rs.next()) {
				String nameSucursal = "Sin Sucursal";
				String nameComercial = "Sin Comercial";
				Sucursal sucursal = mapSucursal.get(rs.getLong(40));
				Comercial comercial = mapComercial.get(rs.getLong(41));
				if(sucursal!=null) {
					nameSucursal = sucursal.nombre;
				}
				if(comercial!=null) {
					nameComercial = comercial.nameUsuario;
				}
				String nameSolucion = "";
				CotizaSolucion cotizaSolucion = mapCotizaSolucion.get(rs.getLong(42));
				if(cotizaSolucion!=null) {
					nameSolucion = cotizaSolucion.getSolucion();
				}
				lista.add(new Cotizacion(rs.getTimestamp(29), rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getLong(4),rs.getLong(5),rs.getString(6),rs.getString(7),
					rs.getDouble(8),rs.getDouble(9),rs.getLong(10),rs.getString(11),rs.getLong(12),rs.getString(13), rs.getString(14),rs.getString(15),
					rs.getString(16),rs.getString(17),rs.getString(18),rs.getString(19),rs.getString(20),rs.getString(21),rs.getString(22),rs.getString(23),
					rs.getString(24),rs.getString(25),rs.getLong(26),rs.getString(27),rs.getString(28), rs.getString(31), rs.getLong(30),
					rs.getString(32),rs.getString(33),rs.getString(34),rs.getString(35),rs.getString(36),rs.getLong(37),rs.getLong(38),rs.getString(39),
					rs.getLong(40), rs.getLong(41), nameSucursal, nameComercial, rs.getLong(42), nameSolucion, rs.getString(43)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (lista);
	}
	
	public static boolean delete(Connection con, String db, Long id_cotizacion) {
		boolean flag = false;
		Cotizacion cotizacion = Cotizacion.find(con, db, id_cotizacion);
		if((long)cotizacion.getEsModificable() == (long)1 && !Ot.existeIdCoti(con, db, id_cotizacion) && !Movimiento.existeMovAsosiado_a_Coti(con, db, id_cotizacion)) {
			try {
				PreparedStatement smt1 = con
						.prepareStatement("DELETE FROM `"+db+"`.cotizaDetalle WHERE id_cotizacion = ?");
				smt1.setLong(1, id_cotizacion);
				smt1.executeUpdate();
				smt1.close();
				
				PreparedStatement smt3 = con
						.prepareStatement("DELETE FROM `"+db+"`.cotizacion WHERE id = ?");
				smt3.setLong(1, id_cotizacion);
				smt3.executeUpdate();
				smt3.close();
				
				PreparedStatement smt = con
						.prepareStatement("delete from `"+db+"`.listaPrecio where id_cotizacion = ?;");
				smt.setLong(1, id_cotizacion);
				smt.executeUpdate();
				smt.close();
				flag = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return (flag);
	}
	
	public static Cotizacion find(Connection con, String db, Long id_cotizacion) {
		Cotizacion aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement(" select "
							+ " id, id_bodegaEmpresa, id_cliente, id_proyecto, numero, fecha, cotizacionPDF, dctoArriendo, dctoVenta, esModificable, "
							+ " ifnull(fechaConfirmada,''), confirmada, contratoPDF, ocClientePDF, numeroOC, ifnull(fechaOC,''), ifnull(fechaContrato,''), "
							+ " numeroContrato, ifnull(usadosEn,''), ifnull(garantiaDoc,''), ifnull(garantiaDet,''), ifnull(garantiaVenc,''), "
							+ " ifnull(garantiaEquiv,''), ifnull(observaciones,''), ifnull(notasAlContrato,''), id_cotizaEstado, pdfArriendo, pdfVenta, dateCreate, id_ot, pdfArrVta, "
							+ " listadoPlanos, fechaPlanos, nomRepresEmpresa, rutRepresEmpresa, direccionObra, id_userCrea, id_userModifica, ifnull(fechaUserModifica,''), "
							+ " id_sucursal, id_comercial, id_cotizaSolucion, notaCotizaEstado "
							+ " from `"+db+"`.cotizacion "
							+ " where cotizacion.id=?;" );
			smt.setLong(1, id_cotizacion);
			ResultSet rs = smt.executeQuery();
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
			Map<Long,CotizaSolucion> mapCotizaSolucion = CotizaSolucion.mapAll(con, db);
			if (rs.next()) {
				String nameSucursal = "Sin Sucursal";
				String nameComercial = "Sin Comercial";
				Sucursal sucursal = mapSucursal.get(rs.getLong(40));
				Comercial comercial = mapComercial.get(rs.getLong(41));
				if(sucursal!=null) {
					nameSucursal = sucursal.nombre;
				}
				if(comercial!=null) {
					nameComercial = comercial.nameUsuario;
				}
				String nameSolucion = "";
				CotizaSolucion cotizaSolucion = mapCotizaSolucion.get(rs.getLong(42));
				if(cotizaSolucion!=null) {
					nameSolucion = cotizaSolucion.getSolucion();
				}
				aux = new Cotizacion(rs.getTimestamp(29), rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getLong(4),rs.getLong(5),rs.getString(6),rs.getString(7),
						rs.getDouble(8),rs.getDouble(9),rs.getLong(10),rs.getString(11),rs.getLong(12),rs.getString(13), rs.getString(14),rs.getString(15),
						rs.getString(16),rs.getString(17),rs.getString(18),rs.getString(19),rs.getString(20),rs.getString(21),rs.getString(22),rs.getString(23),
						rs.getString(24),rs.getString(25),rs.getLong(26),rs.getString(27),rs.getString(28), rs.getString(31), rs.getLong(30),
						rs.getString(32),rs.getString(33),rs.getString(34),rs.getString(35),rs.getString(36),rs.getLong(37),rs.getLong(38),rs.getString(39),
						rs.getLong(40), rs.getLong(41), nameSucursal, nameComercial, rs.getLong(42), nameSolucion, rs.getString(43));
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}
	
	public static Cotizacion findPorNumero(Connection con, String db, Long numero) {
		Cotizacion aux = null;
		try {
			PreparedStatement smt = con
					.prepareStatement(" select "
							+ " id, id_bodegaEmpresa, id_cliente, id_proyecto, numero, fecha, cotizacionPDF, dctoArriendo, dctoVenta, esModificable, "
							+ " ifnull(fechaConfirmada,''), confirmada, contratoPDF, ocClientePDF, numeroOC, ifnull(fechaOC,''), ifnull(fechaContrato,''), "
							+ " numeroContrato, ifnull(usadosEn,''), ifnull(garantiaDoc,''), ifnull(garantiaDet,''), ifnull(garantiaVenc,''), "
							+ " ifnull(garantiaEquiv,''), ifnull(observaciones,''), ifnull(notasAlContrato,''), id_cotizaEstado, pdfArriendo, pdfVenta, dateCreate, id_ot, pdfArrVta, "
							+ " listadoPlanos, fechaPlanos, nomRepresEmpresa, rutRepresEmpresa, direccionObra, id_userCrea, id_userModifica, ifnull(fechaUserModifica,''), "
							+ " id_sucursal, id_comercial, id_cotizaSolucion, notaCotizaEstado "
							+ " from `"+db+"`.cotizacion "
							+ " where cotizacion.numero=?;" );
			smt.setLong(1, numero);
			ResultSet rs = smt.executeQuery();
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
			Map<Long,CotizaSolucion> mapCotizaSolucion = CotizaSolucion.mapAll(con, db);
			if (rs.next()) {
				String nameSucursal = "Sin Sucursal";
				String nameComercial = "Sin Comercial";
				Sucursal sucursal = mapSucursal.get(rs.getLong(40));
				Comercial comercial = mapComercial.get(rs.getLong(41));
				if(sucursal!=null) {
					nameSucursal = sucursal.nombre;
				}
				if(comercial!=null) {
					nameComercial = comercial.nameUsuario;
				}
				String nameSolucion = "";
				CotizaSolucion cotizaSolucion = mapCotizaSolucion.get(rs.getLong(42));
				if(cotizaSolucion!=null) {
					nameSolucion = cotizaSolucion.getSolucion();
				}
				aux = new Cotizacion(rs.getTimestamp(29), rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getLong(4),rs.getLong(5),rs.getString(6),rs.getString(7),
						rs.getDouble(8),rs.getDouble(9),rs.getLong(10),rs.getString(11),rs.getLong(12),rs.getString(13), rs.getString(14),rs.getString(15),
						rs.getString(16),rs.getString(17),rs.getString(18),rs.getString(19),rs.getString(20),rs.getString(21),rs.getString(22),rs.getString(23),
						rs.getString(24),rs.getString(25),rs.getLong(26),rs.getString(27),rs.getString(28), rs.getString(31), rs.getLong(30),
						rs.getString(32),rs.getString(33),rs.getString(34),rs.getString(35),rs.getString(36),rs.getLong(37),rs.getLong(38),rs.getString(39),
						rs.getLong(40), rs.getLong(41), nameSucursal, nameComercial, rs.getLong(42), nameSolucion, rs.getString(43));
			}
			rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (aux);
	}
	
	public static String vistaModalVerCotizacion(Connection con, String db, Cotizacion cotizacion, Map<String,String> mapDiccionario, Map<String,String> mapeoPermiso){
		String vista="";
		List<CotizaDetalle> detalle = CotizaDetalle.allPorIdCotizacion(con, db,cotizacion.getId());
		Comercial comercial = new Comercial();
		Comercial auxComercial = Comercial.findPorIdUsuario(con, db, cotizacion.getId_comercial().toString());
		if(auxComercial==null) {
			comercial.setId((long)0);
			comercial.setNameUsuario("Sin Comercial");
		}else {
			comercial = auxComercial;
		}
		Sucursal sucursal = Sucursal.find(con, db, cotizacion.getId_sucursal().toString());
		
		
		
		Cliente cliente = Cliente.find(con, db, cotizacion.getId_cliente());
		String rutCliente = "", nickCliente = "";
		if(cliente!=null) {
			rutCliente = cliente.getRut();
			nickCliente = cliente.getNickName();
		}
		Proyecto proyecto = Proyecto.find(con, db, cotizacion.getId_proyecto());
		String nickProyecto = "";
		if(proyecto!=null) {
			nickProyecto = proyecto.getNickName();
		}
		
		vista=vista+
		"<table class='table table-sm table-hover table-bordered table-condensed table-fluid'>"+
			"<tr>"+
				"<td><label for='numeroCotizacion'>Nro. Cotización: "+cotizacion.numero+"</label></td>"+
				"<td><label for='fechaCotizacion'>Fecha: "+Fechas.DDMMAA(cotizacion.fecha)+"</label></td>"+
				"<td></td>"+
			"</tr>"+
			"<tr>"+
				"<td><label for='rutCliente'>"+mapDiccionario.get("RUT")+" Cliente: "+rutCliente+"</label></td>"+
				"<td><label for='nombreCliente'>Nombre Cliente: "+nickCliente+"</label></td>"+
				"<td><label for='nombreProyecto'>Nombre Proyecto: "+nickProyecto+"</label></td>"+
			"</tr>"+
			"<tr>"+
				"<td colspan='2'><label for='numeroCotizacion'>Sucursal: "+sucursal.nombre+"</label></td>"+
				"<td colspan='3' rowspan='3' style='text-align:left; vertical-align:top'><label>Observaciones: <br>"+cotizacion.observaciones+"</label></td>"+
			"</tr>"+
			"<tr>"+
				"<td colspan='2'><label for='numeroCotizacion'>Comercial: "+comercial.nameUsuario+"</label></td>"+
			"</tr>"+
			"<tr>"+
				"<td colspan='2'><label for='numeroCotizacion'>Tipo de Solucion: "+cotizacion.getNameCotizaSolucion()+"</label></td>"+
			"</tr>"+
		"</table>"+
		"<table class='table table-sm table-hover table-bordered table-condensed table-fluid'>"+
	        "<thead style='background-color: #eeeeee'>"+
				"<TR> "+
				        "<TH style= 'text-align: center;'>GRUPO</TH>"+
				        "<TH style= 'text-align: center;'>COD</TH>"+
						"<TH style= 'text-align: center;'>EQUIPO</TH>"+
						"<TH style= 'text-align: center;'>UN</TH>"+
						"<TH style= 'text-align: center;'>CANT</TH>"+
						"<TH style= 'text-align: center;'>CONCEPTO</TH>"+
						"<TH style= 'text-align: center;'>MON</TH>"+
						"<TH style= 'text-align: center;'>P.UNIT<br>VTA/REPOS</TH>"+
						"<TH style= 'text-align: center;'>UN<br>"+mapDiccionario.get("ARR")+"</TH>"+
						"<TH style= 'text-align: center;'>P.UNIT<br>"+mapDiccionario.get("ARR")+"</TH>"+
						"<TH style= 'text-align: center;'>PERM</TH>"+
						"<TH style= 'text-align: center;'>P.TOTAL<br>REPOS</TH>"+
						"<TH style= 'text-align: center;'>P.TOTAL<br>"+mapDiccionario.get("ARR")+"</TH>"+
						"<TH style= 'text-align: center;'>P.TOTAL<br>VENTA</TH>"+
						"<TH style= 'text-align: center;'>KG</TH>";
			if(mapeoPermiso.get("parametro.escondeLosM2")!=null && mapeoPermiso.get("parametro.escondeLosM2").equals("1")) {
				vista += "<TH style= 'text-align: center; display:none'>M2</TH>";
			}else {
				vista += "<TH style= 'text-align: center;'>M2</TH>";
			}
						
			vista += "</TR>"+
			"</thead>"+
			"<tbody>";
		Double totalReposicion=(double)0;
		Double totalArriendo=(double)0;
		Double totalVenta=(double)0;
		Double totalKG=(double)0;
		Double totalM2=(double)0;
		Double totaCantidad=(double)0;
		
		Long idMoneda = (long) 1;
				for(int i=0;i<detalle.size();i++){
					idMoneda = (long) 1;
					if(detalle.get(i).id_moneda!=null) idMoneda = detalle.get(i).id_moneda;
					
					Double totRep = (double)0;
					String auxNum = detalle.get(i).totalReposicion.trim();
		    		if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
					try {totRep = myformatdouble.parse(auxNum).doubleValue();}catch(Exception e) {}
					if((long)detalle.get(i).esVenta == (long)0) {
						totalReposicion = totalReposicion + totRep;
					}
					
					Double totArr = (double)0;
					auxNum = detalle.get(i).totalArriendo.trim();
		    		if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
					try {totArr = myformatdouble.parse(auxNum).doubleValue();}catch(Exception e) {}
					totalArriendo = totalArriendo + totArr;
					
					Double totVta = (double)0;
					auxNum = detalle.get(i).totalVenta.trim();
		    		if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
					try {totVta = myformatdouble.parse(auxNum).doubleValue();}catch(Exception e) {}
					totalVenta = totalVenta + totVta;
					
					Double totKg = (double)0;
					auxNum = detalle.get(i).totalKG.trim();
		    		if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
					try {totKg = myformatdouble.parse(auxNum).doubleValue();}catch(Exception e) {}
					totalKG = totalKG + totKg;
					
					Double totM2 = (double)0;
					auxNum = detalle.get(i).totalM2.trim();
		    		if(auxNum==null || auxNum.trim().length()<=0) auxNum = "0";
					try {totM2 = myformatdouble.parse(auxNum).doubleValue();}catch(Exception e) {}
					totalM2 = totalM2 + totM2;
					
					Double cantidad = (double)0;
					auxNum = detalle.get(i).cantidad.trim();
		    		if( auxNum == null || auxNum.trim().length()<=0) auxNum = "0";
					try { cantidad = myformatdouble.parse(auxNum).doubleValue(); }catch(Exception e) {}
					totaCantidad = totaCantidad + cantidad;
					
					
					vista=vista+
					"<TR>"+
						"<td style='text-align:left;'>"+detalle.get(i).grupo+"</td>"+
						"<td style='text-align:left;'>"+detalle.get(i).codigo+"</td>"+
						"<td style='text-align:left;'>"+detalle.get(i).equipo+"</td>"+
						"<td style='text-align:center;'>"+detalle.get(i).unidad+"</td>"+
						"<td style='text-align:right;'>"+detalle.get(i).cantidad+"</td>"+
						"<td style='text-align:center;'>";
							if(detalle.get(i).esVenta==0){
								vista=vista+mapDiccionario.get("Arriendo");
							}else{
								vista=vista+"Venta";
							}
						vista=vista+
						"</td>"+
						"<td style='text-align:center;'>"+detalle.get(i).moneda+"</td>"+
						"<td style='text-align:right;'>"+detalle.get(i).precioReposicion+
						"<td style='text-align:center;'>"+detalle.get(i).unidadTiempo+"</td>"+
						"<td style='text-align:right;'>"+detalle.get(i).precioArriendo+"</td>"+
						"<td style='text-align:right;'>"+detalle.get(i).permanencia+"</td>"+
						"<td style='text-align:right;'>";
							if(detalle.get(i).esVenta==0){
								vista=vista+detalle.get(i).totalReposicion;
							}else{
								vista=vista+"0.00";
							}
						vista=vista+
						"</td>"+
						"<td style='text-align:right;'>"+detalle.get(i).totalArriendo+"</td>"+
						"<td style='text-align:right;'>"+detalle.get(i).totalVenta+"</td>"+
						
						"<td style='text-align:right;'>"+detalle.get(i).totalKG+"</td>";
						if(mapeoPermiso.get("parametro.escondeLosM2")!=null && mapeoPermiso.get("parametro.escondeLosM2").equals("1")) {
							vista += "<td style='text-align:right; display:none'>"+detalle.get(i).totalM2+"</td>";
						}else {
							vista += "<td style= 'text-align: center;'>"+detalle.get(i).totalM2+"</td>";
						}
						vista += 
					"</TR>";
						
		 		}

			
			Double totalArriendoConDcto = totalArriendo*(1-cotizacion.getDctoArriendo());
			Double totalVentaConDcto = totalVenta*(1-cotizacion.getDctoVenta());
			
			DecimalFormat formatDecimal = new DecimalFormat("#,##0.00");
			
			Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
			Long numDec = dec.get(idMoneda);
			if(numDec==null) {
				numDec = (long)0;
			}
			switch(numDec.toString()) {
			 case "0": formatDecimal = new DecimalFormat("#,##0"); break;
			 case "2": formatDecimal = new DecimalFormat("#,##0.00"); break;
			 case "4": formatDecimal = new DecimalFormat("#,##0.0000"); break;
			 case "6": formatDecimal = new DecimalFormat("#,##0.000000"); break;
			 default:  break;
			}
			
			
			vista=vista+
			"</tbody>"+
			"<tfoot style='background-color: #eeeeee'>";
			
			if((double) cotizacion.getDctoArriendo() > (double) 0 || (double) cotizacion.getDctoVenta() > (double) 0){
				vista=vista+
						"<TR>"+
								
								"<TH colspan='11' style= 'text-align: right;'>SUB-TOTALES </TH>"+
								
								"<TH style='text-align: right;'>"+formatDecimal.format(totalReposicion)+"</TH>"+
								"<TH style='text-align: right;'>"+formatDecimal.format(totalArriendo)+"</TH>"+
								"<TH style='text-align: right;'>"+formatDecimal.format(totalVenta)+"</TH>"+
								"<TH style='text-align:right;'>"+myformatdouble2.format(totalKG)+"</TH>";
								if(mapeoPermiso.get("parametro.escondeLosM2")!=null && mapeoPermiso.get("parametro.escondeLosM2").equals("1")) {
									vista += "<TH style='text-align:right; display:none;'>"+myformatdouble2.format(totalM2)+"</TH>";
								}else {
									vista += "<TH style='text-align:right;'>"+myformatdouble2.format(totalM2)+"</TH>";
								}
								vista += 
						"</TR>"+
						"<TR>"+
								"<TH colspan='11' style= 'text-align: right;' title='DESCUENTOS'>DESCUENTOS</TH>"+
								"<TH style='text-align:right;'></TH>"+
								"<TH style='text-align:right;'>"+cotizacion.getDctoArriendo()*100+" %</TH>"+
					    		"<TH style='text-align:right;'>"+cotizacion.getDctoVenta()*100+" %</TH>"+
					    		"<TH></TH>";
								if(mapeoPermiso.get("parametro.escondeLosM2")!=null && mapeoPermiso.get("parametro.escondeLosM2").equals("1")) {
									vista += "<TH style = 'display:none'></TH>";
								}else {
									vista += "<TH></TH>";
								}
								vista += 
						"</TR>";
			}
			
			vista=vista+
				
				"<TR>"+

						"<TH colspan='4' style= 'text-align: right;'>Total cantidad</TH>"+
						"<TH colspan='1' style= 'text-align: right;'>"+myformatdouble2.format(totaCantidad)+"</TH>"+
						"<TH colspan='6' style= 'text-align: right;'>TOTALES </TH>"+
								
						"<TH style='text-align:right;'>"+formatDecimal.format(totalReposicion)+"</TH>"+
						"<TH style='text-align:right;'>"+formatDecimal.format(totalArriendoConDcto)+"</TH>"+
						"<TH style='text-align:right;'>"+formatDecimal.format(totalVentaConDcto)+"</div></TH>"+
						"<TH style='text-align:right;'>"+myformatdouble2.format(totalKG)+"</TH>";
						if(mapeoPermiso.get("parametro.escondeLosM2")!=null && mapeoPermiso.get("parametro.escondeLosM2").equals("1")) {
							vista += "<TH style='text-align:right; display:none;'>"+myformatdouble2.format(totalM2)+"</TH>";
						}else {
							vista += "<TH style='text-align:right;'>"+myformatdouble2.format(totalM2)+"</TH>";
						}
						vista += 
				"</TR>"+
			"</tfoot>"+
		"</table>";
		return(vista);
	}
	
	public static Long findNuevoNumero(Connection con, String db){
		Long numeroCoti=(long) 1;
		try {
			PreparedStatement smt = con
					.prepareStatement(" SELECT ifnull(max(numero)+1,1) from `"+db+"`.cotizacion;");
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
					.prepareStatement(" SELECT numero from `"+db+"`.cotizacion where numero = ?;");
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
	
	public static Map<Long,List<Double>> mapDetalleCotizacion(Connection con, String db,Long id_cotizacion){
		Map<Long,List<Double>> mapDetalle = new HashMap<Long,List<Double>>();
		try{
		PreparedStatement smt = con
				.prepareStatement("select id_equipo,cantidad,esVenta,permanencia,precioArriendo,precioVenta,id_unidadTiempo,id_moneda "+
						" from `"+db+"`.cotizaDetalle where id_cotizacion=?;");
		smt.setLong(1, id_cotizacion);
		ResultSet rs = smt.executeQuery();
		while (rs.next()) {
			List<Double> aux = new ArrayList<Double>();			
			aux.add(rs.getDouble(2)); 					//0 cantidad
			aux.add(rs.getDouble(3)); 					//1 esVenta
			aux.add(rs.getDouble(4)); 					//2 permanencia
			aux.add(rs.getDouble(5)); 					//3 precioArriendo
			aux.add(rs.getDouble(6)); 					//4 precioVenta
			aux.add(rs.getDouble(7)); 					//5 id_unidadTiempo
			aux.add(rs.getDouble(8)); 					//6 id_moneda
			mapDetalle.put(rs.getLong(1), aux);
		}
		rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(mapDetalle);
	}
	
	public static Map<String,Long> mapDetIdEquipVsIdEquip(Connection con, String db,Long id_cotizacion){
		Map<String,Long> mapDetalle = new HashMap<String,Long>();
		try{
		PreparedStatement smt = con
				.prepareStatement("select id_equipo from `"+db+"`.cotizaDetalle where id_cotizacion=?;");
		smt.setLong(1, id_cotizacion);
		ResultSet rs = smt.executeQuery();
		while (rs.next()) {	
			mapDetalle.put(rs.getString(1), rs.getLong(1));
		}
		rs.close();smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(mapDetalle);
	}
	
	public static Long create(Connection con, String db, Cotizacion cotizacion, String id_usuario) {
		Long id_cotizacion = (long)0;
		try {
			PreparedStatement smt = con
				.prepareStatement("INSERT INTO `"+db+"`.cotizacion (id_bodegaEmpresa,id_cliente,id_proyecto," +
								"numero,fecha, observaciones, dctoArriendo, dctoVenta, id_userCrea, id_comercial, id_sucursal, id_cotizaSolucion) " +
								" VALUES (?,?,?,?,?,?,?,?,?,?,?,?);");
			smt.setLong(1, cotizacion.getId_bodegaEmpresa());
			smt.setLong(2, cotizacion.getId_cliente());
			smt.setLong(3, cotizacion.getId_proyecto());
			smt.setLong(4, cotizacion.getNumero());
			smt.setString(5, cotizacion.getFecha());
			smt.setString(6, cotizacion.getObservaciones());
			smt.setDouble(7, cotizacion.getDctoArriendo());
			smt.setDouble(8, cotizacion.getDctoVenta());
			smt.setString(9, id_usuario);
			smt.setLong(10, cotizacion.getId_comercial());
			smt.setLong(11, cotizacion.getId_sucursal());
			smt.setLong(12, cotizacion.getId_cotizaSolucion());
			smt.executeUpdate();
			smt.close();
			
			PreparedStatement smt2 = con
					.prepareStatement("select id from `"+db+"`.cotizacion where numero = ?;");
			smt2.setLong(1, cotizacion.getNumero());
			ResultSet rs = smt2.executeQuery();
			if(rs.next()) {
				id_cotizacion = rs.getLong(1);
			}
			smt2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (id_cotizacion);
	}
	
	public static boolean update(Connection con, String db, Cotizacion cotizacion, String id_usuario) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
				.prepareStatement("update `"+db+"`.cotizacion set id_bodegaEmpresa=?, id_cliente=?, id_proyecto=?," +
								"numero=?,fecha=?, observaciones=?, dctoArriendo=?, dctoVenta=? , id_userModifica=?, fechaUserModifica=?, id_comercial=?, id_sucursal=?, id_cotizaSolucion=? where id=?;");
			smt.setLong(1, cotizacion.getId_bodegaEmpresa());
			smt.setLong(2, cotizacion.getId_cliente());
			smt.setLong(3, cotizacion.getId_proyecto());
			smt.setLong(4, cotizacion.getNumero());
			smt.setString(5, cotizacion.getFecha());
			smt.setString(6, cotizacion.getObservaciones());
			smt.setDouble(7, cotizacion.getDctoArriendo());
			smt.setDouble(8, cotizacion.getDctoVenta());
			smt.setString(9, id_usuario);
			smt.setString(10, Fechas.hoy().getFechaStrAAMMDD());
			smt.setLong(11, cotizacion.getId_comercial());
			smt.setLong(12, cotizacion.getId_sucursal());
			smt.setLong(13, cotizacion.getId_cotizaSolucion());
			smt.setLong(14, cotizacion.getId());
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
				.prepareStatement("update `"+db+"`.cotizacion set esModificable = ?, confirmada = ?, fechaConfirmada = ?, id_cotizaEstado = ? where id in "+listadoIdCotiConfirmar+";");
			smt.setString(1, "0");
			smt.setString(2, "1");
			smt.setString(3, Fechas.hoy().getFechaStrAAMMDD());
			smt.setString(4, "-1");
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (flag);
	}
	
	public static List<List<String>> listCotiAll(Connection con, String db, String esPorSucursal, String id_sucursal){
		List<List<String>> listCotizacion = new ArrayList<List<String>>();
		List<Cotizacion> listCoti = Cotizacion.all(con, db);
		Map<Long,Cliente> mapCliente = Cliente.mapAllClientes(con, db);
		Map<Long, Proyecto> mapProyecto = Proyecto.mapAllProyectos(con, db);
		if(esPorSucursal.equals("1")) {
			listCoti.forEach(x->{
				if(x.id_sucursal.toString().equals(id_sucursal)) {
					Cliente cliente = mapCliente.get(x.getId_cliente());
    				String nickCliente = "";
    				if(cliente!=null) {
    					nickCliente = cliente.getNickName();
    				}
    				Proyecto proyecto = mapProyecto.get(x.getId_proyecto());
    				String nickProyecto = "";
    				if(proyecto!=null) {
    					nickProyecto = proyecto.getNickName();
    				}
    				List<String> aux = new ArrayList<String>();
    				aux.add(x.getId().toString());
    				aux.add(x.nameSucursal);
    				aux.add(x.getNumero().toString());
    				aux.add(Fechas.DDMMAA(x.getFecha()));
    				aux.add(nickCliente);
    				aux.add(nickProyecto);
    				aux.add(x.getObservaciones());
    				listCotizacion.add(aux);
				}
				
				
			});
		}else {
			listCoti.forEach(x->{
				Cliente cliente = mapCliente.get(x.getId_cliente());
				String nickCliente = "";
				if(cliente!=null) {
					nickCliente = cliente.getNickName();
				}
				Proyecto proyecto = mapProyecto.get(x.getId_proyecto());
				String nickProyecto = "";
				if(proyecto!=null) {
					nickProyecto = proyecto.getNickName();
				}
				List<String> aux = new ArrayList<String>();
				aux.add(x.getId().toString());
				aux.add(x.nameSucursal);
				aux.add(x.getNumero().toString());
				aux.add(Fechas.DDMMAA(x.getFecha()));
				aux.add(nickCliente);
				aux.add(nickProyecto);
				aux.add(x.getObservaciones());
				listCotizacion.add(aux);
			});
		}
		return(listCotizacion);
	}
	
	public static List<List<String>> listCotiAllSinConfirmar(Connection con, String db, String esPorSucursal, String id_sucursal, String desde, String hasta){
		List<List<String>> listCotizacion = new ArrayList<List<String>>();
		List<Cotizacion> listCoti = Cotizacion.allSinConfirmar(con, db);
		Map<Long,Cliente> mapCliente = Cliente.mapAllClientes(con, db);
		Map<Long, Proyecto> mapProyecto = Proyecto.mapAllProyectos(con, db);
		if(esPorSucursal.equals("1")) {
			listCoti.forEach(x->{
				if(x.id_sucursal.toString().equals(id_sucursal)) {
					Cliente cliente = mapCliente.get(x.getId_cliente());
					String nomCliente = "";
					if(cliente!=null) {
						nomCliente = cliente.nickName;
					}
					Proyecto proyecto = mapProyecto.get(x.getId_proyecto());
					String nomProyecto = "";
					if(proyecto!=null) {
						nomProyecto = proyecto.nickName;
					}
					List<String> aux = new ArrayList<String>();
					aux.add(x.getId().toString());			// 0 id_cotizacion
					aux.add(x.getNumero().toString());		// 1 numero de cotizacion
					aux.add(Fechas.AAMMDD(x.getFecha()));	// 2 fecha
					aux.add(nomCliente);					// 3 nombre de cliente
					aux.add(x.getObservaciones());			// 4 observaciones
					aux.add(x.getCotizacionPDF());			// 5 doc adjunto
					aux.add(nomProyecto);					// 6 nombre de proyecto
					aux.add(x.nameSucursal);				// 7 sucursal
					aux.add(x.nameComercial);				// 8 comercial
					aux.add(x.nameCotizaSolucion);			// 9 solucion
					listCotizacion.add(aux);
				}
			});
		}else {
			listCoti.forEach(x->{
				Cliente cliente = mapCliente.get(x.getId_cliente());
				String nomCliente = "";
				if(cliente!=null) {
					nomCliente = cliente.nickName;
				}
				Proyecto proyecto = mapProyecto.get(x.getId_proyecto());
				String nomProyecto = "";
				if(proyecto!=null) {
					nomProyecto = proyecto.nickName;
				}
				List<String> aux = new ArrayList<String>();
				aux.add(x.getId().toString());			// 0 id_cotizacion
				aux.add(x.getNumero().toString());		// 1 numero de cotizacion
				aux.add(Fechas.AAMMDD(x.getFecha()));	// 2 fecha
				aux.add(nomCliente);					// 3 nombre de cliente
				aux.add(x.getObservaciones());			// 4 observaciones
				aux.add(x.getCotizacionPDF());			// 5 doc adjunto
				aux.add(nomProyecto);					// 6 nombre de proyecto
				aux.add(x.nameSucursal);				// 7 sucursal
				aux.add(x.nameComercial);				// 8 comercial
				aux.add(x.nameCotizaSolucion);			// 9 solucion
				listCotizacion.add(aux);
			});
		}
		return(listCotizacion);
	}
	
	public static List<List<String>> listCotiallParaCambiarEstado(Connection con, String db, String esPorSucursal, String id_sucursal, String desde, String hasta){
		List<List<String>> listCotizacion = new ArrayList<List<String>>();
		List<Cotizacion> listCoti = Cotizacion.allParaCambiarEstadoDesdeHasta(con, db, desde, hasta);
		Map<Long,Cliente> mapCliente = Cliente.mapAllClientes(con, db);
		Map<Long, Proyecto> mapProyecto = Proyecto.mapAllProyectos(con, db);
		Map<Long,CotizaEstado> mapEstado = CotizaEstado.mapAll(con, db);
		if(esPorSucursal.equals("1")) {
			listCoti.forEach(x->{
				if(x.id_sucursal.toString().equals(id_sucursal)) {
					Cliente cliente = mapCliente.get(x.getId_cliente());
					String nomCliente = "";
					if(cliente!=null) {
						nomCliente = cliente.nickName;
					}
					Proyecto proyecto = mapProyecto.get(x.getId_proyecto());
					String nomProyecto = "";
					if(proyecto!=null) {
						nomProyecto = proyecto.nickName;
					}
					CotizaEstado estado = mapEstado.get(x.getId_cotizaEstado());
					String nomEstado = "";
					if(estado!=null) {
						nomEstado = estado.getEstado();
					}
					List<String> aux = new ArrayList<String>();
					aux.add(x.getId().toString());				// 0 id_cotizacion
					aux.add(x.getNumero().toString());			// 1 numero de cotizacion
					aux.add(Fechas.AAMMDD(x.getFecha()));		// 2 fecha
					aux.add(nomCliente);						// 3 nombre de cliente
					aux.add(x.getObservaciones());				// 4 observaciones
					aux.add(x.getCotizacionPDF());				// 5 doc adjunto
					aux.add(nomProyecto);						// 6 nombre de proyecto
					aux.add(x.getId_cotizaEstado().toString());	// 7 id_cotizaEstado
					aux.add(nomEstado);							// 8 nombre de estado
					aux.add(x.nameSucursal);					// 9 sucursal
					aux.add(x.nameComercial);					// 10 comercial
					aux.add(x.nameCotizaSolucion);				// 11 solucion
					aux.add(x.getNotaCotizaEstado());			// 12 NotaCotizaEstado
					listCotizacion.add(aux);
				}
			});
		}else {
			listCoti.forEach(x->{
				Cliente cliente = mapCliente.get(x.getId_cliente());
				String nomCliente = "";
				if(cliente!=null) {
					nomCliente = cliente.nickName;
				}
				Proyecto proyecto = mapProyecto.get(x.getId_proyecto());
				String nomProyecto = "";
				if(proyecto!=null) {
					nomProyecto = proyecto.nickName;
				}
				CotizaEstado estado = mapEstado.get(x.getId_cotizaEstado());
				String nomEstado = "";
				if(estado!=null) {
					nomEstado = estado.getEstado();
				}
				List<String> aux = new ArrayList<String>();
				aux.add(x.getId().toString());				// 0 id_cotizacion
				aux.add(x.getNumero().toString());			// 1 numero de cotizacion
				aux.add(Fechas.AAMMDD(x.getFecha()));		// 2 fecha
				aux.add(nomCliente);						// 3 nombre de cliente
				aux.add(x.getObservaciones());				// 4 observaciones
				aux.add(x.getCotizacionPDF());				// 5 doc adjunto
				aux.add(nomProyecto);						// 6 nombre de proyecto
				aux.add(x.getId_cotizaEstado().toString());	// 7 id_cotizaEstado
				aux.add(nomEstado);							// 8 nombre de estado
				aux.add(x.nameSucursal);					// 9 sucursal
				aux.add(x.nameComercial);					// 10 comercial
				aux.add(x.nameCotizaSolucion);				// 11 solucion
				aux.add(x.getNotaCotizaEstado());			// 12 NotaCotizaEstado
				listCotizacion.add(aux);
			});
		}
		
		return(listCotizacion);
	}
		    			
	public static List<List<String>> listCotiAllParaImprimir(Connection con, String db, String esPorSucursal, String id_sucursal, String desde, String hasta){
		List<List<String>> listCotizacion = new ArrayList<List<String>>();
		List<Cotizacion> listCoti = Cotizacion.allDesdeHasta(con, db, desde, hasta);
		Map<Long,Cliente> mapCliente = Cliente.mapAllClientes(con, db);
		Map<Long, Proyecto> mapProyecto = Proyecto.mapAllProyectos(con, db);
		Map<Long,CotizaEstado> mapEstado = CotizaEstado.mapAll(con, db);
		if(esPorSucursal.equals("1")) {
			listCoti.forEach(x->{
				if(x.id_sucursal.toString().equals(id_sucursal)) {
					Cliente cliente = mapCliente.get(x.getId_cliente());
    				String nomCliente = "";
    				if(cliente!=null) {
    					nomCliente = cliente.nickName;
    				}
    				Proyecto proyecto = mapProyecto.get(x.getId_proyecto());
    				String nomProyecto = "";
    				if(proyecto!=null) {
    					nomProyecto = proyecto.nickName;
    				}
    				CotizaEstado estado = mapEstado.get(x.getId_cotizaEstado());
    				String nomEstado = "";
    				if(estado!=null) {
    					nomEstado = estado.getEstado();
    				}
    				List<String> aux = new ArrayList<String>();
    				aux.add(x.getId().toString());				// 0 id_cotizacion
    				aux.add(x.getNumero().toString());			// 1 numero de cotizacion
    				aux.add(Fechas.AAMMDD(x.getFecha()));		// 2 fecha
    				aux.add(nomCliente);						// 3 nombre de cliente
    				aux.add(x.getObservaciones());				// 4 observaciones
    				aux.add(x.getCotizacionPDF());				// 5 doc adjunto
    				aux.add(nomProyecto);						// 6 nombre de proyecto
    				aux.add(x.getId_cotizaEstado().toString());	// 7 id_cotizaEstado
    				aux.add(nomEstado);							// 8 nombre de estado
    				aux.add(x.getFechaConfirmada());			// 9 fecha de confirmada
    				aux.add(x.getPdfArriendo());				// 10 pdf arriendo generado
    				aux.add(x.getPdfVenta());					// 11 pdf venta generado
    				aux.add(x.getPdfArrVta());					// 12 pdf venta y arriendo generado
					aux.add(x.nameSucursal);					// 13 sucursal
					aux.add(x.nameComercial);					// 14 comercial
					aux.add(x.nameCotizaSolucion);				// 15 solucion
					listCotizacion.add(aux);
				}
			});
		}else {
			listCoti.forEach(x->{
				Cliente cliente = mapCliente.get(x.getId_cliente());
				String nomCliente = "";
				if(cliente!=null) {
					nomCliente = cliente.nickName;
				}
				Proyecto proyecto = mapProyecto.get(x.getId_proyecto());
				String nomProyecto = "";
				if(proyecto!=null) {
					nomProyecto = proyecto.nickName;
				}
				CotizaEstado estado = mapEstado.get(x.getId_cotizaEstado());
				String nomEstado = "";
				if(estado!=null) {
					nomEstado = estado.getEstado();
				}
				List<String> aux = new ArrayList<String>();
				aux.add(x.getId().toString());				// 0 id_cotizacion
				aux.add(x.getNumero().toString());			// 1 numero de cotizacion
				aux.add(Fechas.AAMMDD(x.getFecha()));		// 2 fecha
				aux.add(nomCliente);						// 3 nombre de cliente
				aux.add(x.getObservaciones());				// 4 observaciones
				aux.add(x.getCotizacionPDF());				// 5 doc adjunto
				aux.add(nomProyecto);						// 6 nombre de proyecto
				aux.add(x.getId_cotizaEstado().toString());	// 7 id_cotizaEstado
				aux.add(nomEstado);							// 8 nombre de estado
				aux.add(x.getFechaConfirmada());			// 9 fecha de confirmada
				aux.add(x.getPdfArriendo());				// 10 pdf arriendo generado
				aux.add(x.getPdfVenta());					// 11 pdf venta generado
				aux.add(x.getPdfArrVta());					// 12 pdf venta y arriendo generado
				aux.add(x.nameSucursal);					// 13 sucursal
				aux.add(x.nameComercial);					// 14 comercial
				aux.add(x.nameCotizaSolucion);				// 15 solucion
				listCotizacion.add(aux);
			});
		}
		
		return(listCotizacion);
	}
	
	public static List<List<String>> listCotiAllParaConfirmar(Connection con, String db, String esPorSucursal, String id_sucursal){
		List<List<String>> listCotizacion = new ArrayList<List<String>>();
		List<Cotizacion> listCoti = Cotizacion.allSinConfirmar(con, db);
		Map<Long,Cliente> mapCliente = Cliente.mapAllClientes(con, db);
		Map<Long, Proyecto> mapProyecto = Proyecto.mapAllProyectos(con, db);
		Map<Long,CotizaEstado> mapEstado = CotizaEstado.mapAll(con, db);
		if(esPorSucursal.equals("1")) {
			listCoti.forEach(x->{
				if(x.id_sucursal.toString().equals(id_sucursal)) {
					Cliente cliente = mapCliente.get(x.getId_cliente());
    				String nomCliente = "";
    				if(cliente!=null) {
    					nomCliente = cliente.nickName;
    				}
    				Proyecto proyecto = mapProyecto.get(x.getId_proyecto());
    				String nomProyecto = "";
    				if(proyecto!=null) {
    					nomProyecto = proyecto.nickName;
    				}
    				CotizaEstado estado = mapEstado.get(x.getId_cotizaEstado());
    				String nomEstado = "";
    				if(estado!=null) {
    					nomEstado = estado.getEstado();
    				}
    				List<String> aux = new ArrayList<String>();
    				aux.add(x.getId().toString());				// 0 id_cotizacion
    				aux.add(x.getNumero().toString());			// 1 numero de cotizacion
    				aux.add(Fechas.AAMMDD(x.getFecha()));		// 2 fecha
    				aux.add(nomCliente);						// 3 nombre de cliente
    				aux.add(x.getObservaciones());				// 4 observaciones
    				aux.add(x.getCotizacionPDF());				// 5 doc adjunto
    				aux.add(nomProyecto);						// 6 nombre de proyecto
    				aux.add(x.getId_cotizaEstado().toString());	// 7 id_cotizaEstado
    				aux.add(nomEstado);							// 8 nombre de estado
    				aux.add(x.getFechaConfirmada());			// 9 fecha de confirmada
    				aux.add(x.getPdfArriendo());				// 10 pdf arriendo generado
    				aux.add(x.getPdfVenta());					// 11 pdf venta generado
    				aux.add(x.dateCreate.toString());			// 12 pdf fecha y hora generado
					aux.add(x.nameSucursal);					// 13 sucursal
					aux.add(x.nameComercial);					// 14 comercial
					aux.add(x.nameCotizaSolucion);				// 15 solucion
					listCotizacion.add(aux);
				}
			});
		}else {
			listCoti.forEach(x->{
				Cliente cliente = mapCliente.get(x.getId_cliente());
				String nomCliente = "";
				if(cliente!=null) {
					nomCliente = cliente.nickName;
				}
				Proyecto proyecto = mapProyecto.get(x.getId_proyecto());
				String nomProyecto = "";
				if(proyecto!=null) {
					nomProyecto = proyecto.nickName;
				}
				CotizaEstado estado = mapEstado.get(x.getId_cotizaEstado());
				String nomEstado = "";
				if(estado!=null) {
					nomEstado = estado.getEstado();
				}
				List<String> aux = new ArrayList<String>();
				aux.add(x.getId().toString());				// 0 id_cotizacion
				aux.add(x.getNumero().toString());			// 1 numero de cotizacion
				aux.add(Fechas.AAMMDD(x.getFecha()));		// 2 fecha
				aux.add(nomCliente);						// 3 nombre de cliente
				aux.add(x.getObservaciones());				// 4 observaciones
				aux.add(x.getCotizacionPDF());				// 5 doc adjunto
				aux.add(nomProyecto);						// 6 nombre de proyecto
				aux.add(x.getId_cotizaEstado().toString());	// 7 id_cotizaEstado
				aux.add(nomEstado);							// 8 nombre de estado
				aux.add(x.getFechaConfirmada());			// 9 fecha de confirmada
				aux.add(x.getPdfArriendo());				// 10 pdf arriendo generado
				aux.add(x.getPdfVenta());					// 11 pdf venta generado
				aux.add(x.dateCreate.toString());			// 12 pdf fecha y hora generado
				aux.add(x.nameSucursal);					// 13 sucursal
				aux.add(x.nameComercial);					// 14 comercial
				aux.add(x.nameCotizaSolucion);				// 15 solucion
				listCotizacion.add(aux);
			});
		}
		
		return(listCotizacion);
	}
	
	public static List<List<String>> listCotiSinOt(Connection con, String db, String esPorSucursal, String id_sucursal){
		List<List<String>> listCotizacion = new ArrayList<List<String>>();
		List<Cotizacion> listCoti = Cotizacion.allConfirmadasSinOt(con, db);
		Map<Long,Cliente> mapCliente = Cliente.mapAllClientes(con, db);
		Map<Long,Proyecto> mapProyecto = Proyecto.mapAllProyectos(con, db);
		Map<Long,CotizaEstado> mapEstado = CotizaEstado.mapAll(con, db);
		if(esPorSucursal.equals("1")) {
			listCoti.forEach(x->{
				if(x.id_sucursal.toString().equals(id_sucursal)) {
					Cliente cliente = mapCliente.get(x.getId_cliente());
    				String nomCliente = "";
    				if(cliente!=null) {
    					nomCliente = cliente.nickName;
    				}
    				Proyecto proyecto = mapProyecto.get(x.getId_proyecto());
    				String nomProyecto = "";
    				if(proyecto!=null) {
    					nomProyecto = proyecto.nickName;
    				}
    				CotizaEstado estado = mapEstado.get(x.getId_cotizaEstado());
    				String nomEstado = "";
    				if(estado!=null) {
    					nomEstado = estado.getEstado();
    				}
    				List<String> aux = new ArrayList<String>();
    				aux.add(x.getId().toString());				// 0 id_cotizacion
    				aux.add(x.getNumero().toString());			// 1 numero de cotizacion
    				aux.add(Fechas.AAMMDD(x.getFecha()));		// 2 fecha
    				aux.add(nomCliente);						// 3 nombre de cliente
    				aux.add(x.getObservaciones());				// 4 observaciones
    				aux.add(x.getCotizacionPDF());				// 5 doc adjunto
    				aux.add(nomProyecto);						// 6 nombre de proyecto
    				aux.add(x.getId_cotizaEstado().toString());	// 7 id_cotizaEstado
    				aux.add(nomEstado);							// 8 nombre de estado
    				aux.add(x.getFechaConfirmada());			// 9 fecha de confirmada
    				aux.add(x.getPdfArriendo());				// 10 pdf arriendo generado
    				aux.add(x.getPdfVenta());					// 11 pdf venta generado
    				aux.add(x.dateCreate.toString());			// 12 pdf venta generado
    				aux.add(x.nameSucursal);					// 13 sucursal
    				aux.add(x.nameComercial);					// 14 comercial
    				aux.add(x.nameCotizaSolucion);				// 15 solucion
					listCotizacion.add(aux);
				}
			});
		}else {
			listCoti.forEach(x->{
				Cliente cliente = mapCliente.get(x.getId_cliente());
				String nomCliente = "";
				if(cliente!=null) {
					nomCliente = cliente.nickName;
				}
				Proyecto proyecto = mapProyecto.get(x.getId_proyecto());
				String nomProyecto = "";
				if(proyecto!=null) {
					nomProyecto = proyecto.nickName;
				}
				CotizaEstado estado = mapEstado.get(x.getId_cotizaEstado());
				String nomEstado = "";
				if(estado!=null) {
					nomEstado = estado.getEstado();
				}
				List<String> aux = new ArrayList<String>();
				aux.add(x.getId().toString());				// 0 id_cotizacion
				aux.add(x.getNumero().toString());			// 1 numero de cotizacion
				aux.add(Fechas.AAMMDD(x.getFecha()));		// 2 fecha
				aux.add(nomCliente);						// 3 nombre de cliente
				aux.add(x.getObservaciones());				// 4 observaciones
				aux.add(x.getCotizacionPDF());				// 5 doc adjunto
				aux.add(nomProyecto);						// 6 nombre de proyecto
				aux.add(x.getId_cotizaEstado().toString());	// 7 id_cotizaEstado
				aux.add(nomEstado);							// 8 nombre de estado
				aux.add(x.getFechaConfirmada());			// 9 fecha de confirmada
				aux.add(x.getPdfArriendo());				// 10 pdf arriendo generado
				aux.add(x.getPdfVenta());					// 11 pdf venta generado
				aux.add(x.dateCreate.toString());			// 12 pdf venta generado
				aux.add(x.nameSucursal);					// 13 sucursal
				aux.add(x.nameComercial);					// 14 comercial
				aux.add(x.nameCotizaSolucion);				// 15 solucion
				listCotizacion.add(aux);
			});
		}
		
		return(listCotizacion);
	}
	
	public static List<List<String>> listCotiGenerarContrato(Connection con, String db, String esPorSucursal, String id_sucursal, String desde, String hasta){
		List<List<String>> listCotizacion = new ArrayList<List<String>>();

		List<Cotizacion> listCoti = Cotizacion.allConfirmadasCon_o_SinOtDesdeHasta(con, db, desde, hasta);
		Map<Long,Cliente> mapCliente = Cliente.mapAllClientes(con, db);
		Map<Long,Proyecto> mapProyecto = Proyecto.mapAllProyectos(con, db);
		Map<Long,CotizaEstado> mapEstado = CotizaEstado.mapAll(con, db);
		if(esPorSucursal.equals("1")) {
			listCoti.forEach(x->{
				if(x.id_sucursal.toString().equals(id_sucursal)) {
					Cliente cliente = mapCliente.get(x.getId_cliente());
    				String nomCliente = "";
    				if(cliente!=null) {
    					nomCliente = cliente.nickName;
    				}
    				Proyecto proyecto = mapProyecto.get(x.getId_proyecto());
    				String nomProyecto = "";
    				if(proyecto!=null) {
    					nomProyecto = proyecto.nickName;
    				}
    				CotizaEstado estado = mapEstado.get(x.getId_cotizaEstado());
    				String nomEstado = "";
    				if(estado!=null) {
    					nomEstado = estado.getEstado();
    				}
    				List<String> aux = new ArrayList<String>();
    				aux.add(x.getId().toString());				// 0 id_cotizacion
    				aux.add(x.getNumero().toString());			// 1 numero de cotizacion
    				aux.add(Fechas.AAMMDD(x.getFecha()));		// 2 fecha
    				aux.add(nomCliente);						// 3 nombre de cliente
    				aux.add(x.getObservaciones());				// 4 observaciones
    				aux.add(x.getCotizacionPDF());				// 5 doc adjunto
    				aux.add(nomProyecto);						// 6 nombre de proyecto
    				aux.add(x.getId_cotizaEstado().toString());	// 7 id_cotizaEstado
    				aux.add(nomEstado);							// 8 nombre de estado
    				aux.add(x.getFechaConfirmada());			// 9 fecha de confirmada
    				aux.add(x.getPdfArriendo());				// 10 pdf arriendo generado
    				aux.add(x.getPdfVenta());					// 11 pdf venta generado
    				aux.add(x.getContratoPDF());				// 12 pdf contrato pdf anexado
    				aux.add(x.nameSucursal);					// 13 sucursal
    				aux.add(x.nameComercial);					// 14 comercial
    				aux.add(x.nameCotizaSolucion);				// 15 solucion
					listCotizacion.add(aux);
				}
			});
		}else {
			listCoti.forEach(x->{
				Cliente cliente = mapCliente.get(x.getId_cliente());
				String nomCliente = "";
				if(cliente!=null) {
					nomCliente = cliente.nickName;
				}
				Proyecto proyecto = mapProyecto.get(x.getId_proyecto());
				String nomProyecto = "";
				if(proyecto!=null) {
					nomProyecto = proyecto.nickName;
				}
				CotizaEstado estado = mapEstado.get(x.getId_cotizaEstado());
				String nomEstado = "";
				if(estado!=null) {
					nomEstado = estado.getEstado();
				}
				List<String> aux = new ArrayList<String>();
				aux.add(x.getId().toString());				// 0 id_cotizacion
				aux.add(x.getNumero().toString());			// 1 numero de cotizacion
				aux.add(Fechas.AAMMDD(x.getFecha()));		// 2 fecha
				aux.add(nomCliente);						// 3 nombre de cliente
				aux.add(x.getObservaciones());				// 4 observaciones
				aux.add(x.getCotizacionPDF());				// 5 doc adjunto
				aux.add(nomProyecto);						// 6 nombre de proyecto
				aux.add(x.getId_cotizaEstado().toString());	// 7 id_cotizaEstado
				aux.add(nomEstado);							// 8 nombre de estado
				aux.add(x.getFechaConfirmada());			// 9 fecha de confirmada
				aux.add(x.getPdfArriendo());				// 10 pdf arriendo generado
				aux.add(x.getPdfVenta());					// 11 pdf venta generado
				aux.add(x.getContratoPDF());				// 12 pdf contrato pdf anexado
				aux.add(x.nameSucursal);					// 13 sucursal
				aux.add(x.nameComercial);					// 14 comercial
				aux.add(x.nameCotizaSolucion);				// 15 solucion
				listCotizacion.add(aux);
			});
		}
		
		return(listCotizacion);
	}
	
	public static List<List<String>> listCotiFiltrado(Connection con, String db, String anioMes, Long id_cotizaEstado, 
			Long id_sucursal, Long id_cliente, Long id_proyecto, Long id_comercial, Long id_cotizaSolucion){
		List<List<String>> lista = new ArrayList<List<String>>();
		String anio = anioMes.substring(0,4);
		String mes = anioMes.substring(4);
		try{
			PreparedStatement smt = con
					.prepareStatement(" select "
							+ " id,"
							+ " id_bodegaEmpresa,"
							+ " id_cliente,"
							+ " id_proyecto,"
							+ " numero,"
							+ " fecha,"
							+ " cotizacionPDF,"
							+ " dctoArriendo,"
							+ " dctoVenta,"
					/*10*/		+ " esModificable, "
							+ " ifnull(fechaConfirmada,''),"
							+ " confirmada,"
							+ " contratoPDF,"
							+ " ocClientePDF,"
							+ " numeroOC,"
							+ " ifnull(fechaOC,''),"
							+ " ifnull(fechaContrato,''), "
							+ " numeroContrato,"
							+ " ifnull(usadosEn,''),"
					/*20*/		+ " ifnull(garantiaDoc,''),"
							+ " ifnull(garantiaDet,''),"
							+ " ifnull(garantiaVenc,''), "
							+ " ifnull(garantiaEquiv,''),"
							+ " ifnull(observaciones,''),"
							+ " ifnull(notasAlContrato,''),"
							+ " id_cotizaEstado,"
							+ " pdfArriendo,"
							+ " pdfVenta,"
							+ " dateCreate,"
					/*30*/		+ " id_ot,"
							+ " pdfArrVta, "
							+ " listadoPlanos,"
							+ " fechaPlanos,"
							+ " nomRepresEmpresa,"
							+ " rutRepresEmpresa,"
							+ " direccionObra,"
							+ " id_userCrea,"
							+ " id_userModifica,"
							+ " ifnull(fechaUserModifica,''),"
					/*40*/		+ " id_sucursal,"
							+ " id_comercial,"
							+ " concat(year(cotizacion.fecha), if(month(cotizacion.fecha)<10,concat('0',month(cotizacion.fecha)), month(cotizacion.fecha))) as anioMes, "
							+ " cotizacion.id_cotizaSolucion"
							+ " from `"+db+"`.cotizacion "
							+ " where id>0  and year(fecha)=? and month(fecha)=? and id_cotizaEstado=? "
							+ " and id_sucursal=? and id_cliente=? and id_proyecto=? "
							+ " and id_comercial=? and id_cotizaSolucion=?;");
			smt.setString(1, anio);
			smt.setString(2, mes);
			smt.setLong(3, id_cotizaEstado);
			smt.setLong(4, id_sucursal);
			smt.setLong(5, id_cliente);
			smt.setLong(6, id_proyecto);
			smt.setLong(7, id_comercial);
			smt.setLong(8, id_cotizaSolucion);

			ResultSet rs = smt.executeQuery();
			
			Map<Long,Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			Map<Long,Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
			Map<Long,Cliente> mapCliente = Cliente.mapAllClientes(con, db);
			Map<Long, Proyecto> mapProyecto = Proyecto.mapAllProyectos(con, db);
			Map<Long,CotizaEstado> mapEstado = CotizaEstado.mapAll(con, db);
			Map<Long,CotizaSolucion> mapCotizaSolucion = CotizaSolucion.mapAll(con, db);
			
			while (rs.next()) {
				String nameSucursal = "Sin Sucursal";
				String nameComercial = "Sin Comercial";
				Sucursal sucursal = mapSucursal.get(rs.getLong(40));
				Comercial comercial = mapComercial.get(rs.getLong(41));
				if(sucursal!=null) {
					nameSucursal = sucursal.nombre;
				}
				if(comercial!=null) {
					nameComercial = comercial.nameUsuario;
				}
				
				Cliente cliente = mapCliente.get(rs.getLong(3));
				String nomCliente = "";
				if(cliente!=null) {
					nomCliente = cliente.nickName;
				}
				Proyecto proyecto = mapProyecto.get(rs.getLong(4));
				String nomProyecto = "";
				if(proyecto!=null) {
					nomProyecto = proyecto.nickName;
				}
				CotizaEstado estado = mapEstado.get(rs.getLong(26));
				String nomEstado = "";
				if(estado!=null) {
					nomEstado = estado.getEstado();
				}
				String nameCotizaSolucion = "";
				CotizaSolucion cotizaSolucion = mapCotizaSolucion.get(rs.getLong(43));
				if(cotizaSolucion!=null) {
					nameCotizaSolucion = cotizaSolucion.getSolucion();
				}
				
				List<String> aux = new ArrayList<String>();
				aux.add(rs.getString(1));					// 0 id_cotizacion
				aux.add(rs.getString(5));					// 1 numero de cotizacion
				aux.add(Fechas.AAMMDD(rs.getString(6)));	// 2 fecha
				aux.add(nomCliente);						// 3 nombre de cliente
				aux.add(rs.getString(24));					// 4 observaciones
				aux.add(rs.getString(7));					// 5 doc adjunto
				aux.add(nomProyecto);						// 6 nombre de proyecto
				aux.add(rs.getString(26));					// 7 id_cotizaEstado
				aux.add(nomEstado);							// 8 nombre de estado
				aux.add(rs.getString(11));					// 9 fecha de confirmada
				aux.add(rs.getString(27));					// 10 pdf arriendo generado
				aux.add(rs.getString(28));					// 11 pdf venta generado
				aux.add(rs.getString(31));					// 12 pdf venta y arriendo generado
				aux.add(nameSucursal);						// 13 sucursal
				aux.add(nameComercial);						// 14 comercial
				aux.add(nameCotizaSolucion);				// 15 solucion
				lista.add(aux);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(lista);
	}
	
	
	public static List<List<String>> listCotiAllClienteSelect(Connection con, String db, String esPorSucursal, String id_sucursal){
		
		List<List<String>> lista = new ArrayList<List<String>>();
		
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and cotizacion.id_sucursal = " + id_sucursal;
		}
		
		try {
			PreparedStatement smt = con
				.prepareStatement("select "
						+ " id_cliente, "
						+ " rut, "
						+ " cliente.nombre, "
						+ " cliente.nickName "
						+ " from `"+db+"`.cotizacion "
						+ " left join `"+db+"`.cliente on cliente.id = cotizacion.id_cliente "
						+ " where nickName is not null " + condSucursal
						+ " group by cotizacion.id_cliente;");
			ResultSet rs = smt.executeQuery();
			
			while(rs.next()) {
				
				List<String> aux = new ArrayList<String>();
				aux.add(rs.getString(1));
				aux.add(rs.getString(2));
				aux.add(rs.getString(3));
				aux.add(rs.getString(4));
				lista.add(aux);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return(lista);
	}
	
	public static List<List<String>> listCotiAllProyectoSelect(Connection con, String db, String id_cliente, String esPorSucursal, String id_sucursal){
		
		List<List<String>> lista = new ArrayList<List<String>>();
		
		String condSucursal = "";
		if(esPorSucursal.equals("1")) {
			condSucursal = " and cotizacion.id_sucursal = " + id_sucursal;
		}
		
		try {
			PreparedStatement smt = con
				.prepareStatement("select "
						+ " cotizacion.id_proyecto, "
						+ " ifnull(proyecto.nickName,'sin proyecto') "
						+ " from `"+db+"`.cotizacion "
						+ " left join `"+db+"`.proyecto on proyecto.id = cotizacion.id_proyecto "
						+ " where cotizacion.id_cliente = ? " + condSucursal
						+ " group by cotizacion.id_proyecto "
						+ " order by ifnull(proyecto.nickName,'sin proyecto');");
			smt.setString(1, id_cliente);
			ResultSet rs = smt.executeQuery();
			
			while(rs.next()) {
				List<String> aux = new ArrayList<String>();
				aux.add(rs.getString(1));
				aux.add(rs.getString(2));
				lista.add(aux);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return(lista);
	}
	
	public static List<List<String>> listCotiAllCotiPorCliente(Connection con, String db, String esPorSucursal, String id_sucursal, String id_cliente, String id_proyecto){
		List<List<String>> lista = new ArrayList<List<String>>();
		try {
			PreparedStatement smt = con
				.prepareStatement("select "
						+ " cotizacion.id, "
						+ " cotizacion.id_cliente, "
						+ " cotizacion.id_sucursal, "
						+ " cotizacion.id_comercial, "
						+ " cotizacion.numero, "
						+ " cotizacion.fecha, "
						+ " cotizacion.observaciones "
						+ " from `"+db+"`.cotizacion "
						+ " where cotizacion.id_cliente = ? and cotizacion.id_proyecto = ? and (cotizacion.id_cotizaEstado = 1 or cotizacion.id_cotizaEstado = -1);");
			smt.setString(1, id_cliente);
			smt.setString(2, id_proyecto);
			ResultSet rs = smt.executeQuery();
			Map<Long, Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
			Map<Long, Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			while(rs.next()) {
				String nameComercial = "";
				String nameSucursal = "";
				Sucursal sucursal = mapSucursal.get(rs.getLong(3));
				if(sucursal!=null) {
					nameSucursal = sucursal.getNombre();
				}
				Comercial comercial = mapComercial.get(rs.getLong(4));
				if(comercial!=null) {
					nameComercial = comercial.getNameUsuario();
				}
				List<String> aux = new ArrayList<String>();
				aux.add(rs.getString(1));
				aux.add(nameSucursal);
				aux.add(nameComercial);
				aux.add(rs.getString(5));
				aux.add(rs.getString(6));
				aux.add(rs.getString(7));
				aux.add(rs.getString(3)); // 6 id_sucursal
				lista.add(aux);
			}
			
			if(esPorSucursal.equals("1")) {
				List<List<String>> listaAux = new ArrayList<List<String>>();
				lista.forEach(x->{
					if(x.get(6).equals(id_sucursal)) {
						listaAux.add(x);
					}
				});
				lista = listaAux;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(lista);
	}
	
	public static List<List<String>> listCotiDetallePorCotiSelect(Connection con, String db, Map<String,String> mapDiccionario, String listadoIdCoti){
		
		List<List<String>> lista = new ArrayList<List<String>>();
		
		try {
			PreparedStatement smt = con
				.prepareStatement("select "
						+ " cotizacion.id_sucursal, "
						+ " cotizacion.id_comercial, "
						+ " cotizacion.numero, "
						+ " cotizacion.fecha, "
						+ " ifnull(cotizacion.observaciones,''), "
						+ " equipo.codigo, "
						+ " equipo.nombre as equipo, "
						+ " unidad.nombre as unidad, "
						+ " cotizaDetalle.cantidad, "
						+ " cotizaDetalle.esVenta, "
						+ " moneda.nickName as moneda, "
						+ " cotizaDetalle.precioVenta * (1-cotizacion.dctoVenta) as puVenta, "
						+ " unidadTiempo.nombre as unidadTiempo, "
						+ " cotizaDetalle.precioArriendo * (1-cotizacion.dctoArriendo) as puArriendo, "
						+ " cotizaDetalle.permanencia, "
						+ " if(cotizaDetalle.esVenta=1,0,cotizaDetalle.precioVenta * (1-cotizacion.dctoVenta) * cantidad) as reposicion, "
						+ " if(cotizaDetalle.esVenta=1,0,cotizaDetalle.precioArriendo * (1-cotizacion.dctoArriendo) * cantidad * cotizaDetalle.permanencia) as arriendo, "
						+ " if(cotizaDetalle.esVenta=0,0,cotizaDetalle.precioVenta * (1-cotizacion.dctoVenta) * cantidad) as venta, "
						+ " equipo.id, "
						+ " cotizaDetalle.id_cotizacion, "
						+ " moneda.id, "
						+ " cotizacion.id_cotizaSolucion "
						+ " from `"+db+"`.cotizaDetalle "
						+ " left join `"+db+"`.cotizacion on cotizacion.id = cotizaDetalle.id_cotizacion "
						+ " left join `"+db+"`.equipo on equipo.id = cotizaDetalle.id_equipo "
						+ " left join `"+db+"`.unidad on unidad.id = equipo.id_unidad "
						+ " left join `"+db+"`.moneda on moneda.id = cotizaDetalle.id_moneda "
						+ " left join `"+db+"`.unidadTiempo on unidadTiempo.id = cotizaDetalle.id_unidadTiempo "
						+ " where cotizaDetalle.id_cotizacion in "+listadoIdCoti+";");
			ResultSet rs = smt.executeQuery();
			Map<Long, Comercial> mapComercial = Comercial.mapAllComerciales(con, db);
			Map<Long, Sucursal> mapSucursal = Sucursal.mapAllSucursales(con, db);
			Map<Long, CotizaSolucion> mapCotizaSolucion = CotizaSolucion.mapAll(con, db);
			
			Map<Long,Double> pesos = Atributo.mapAtributoPESO(con, db);
			Map<Long,Double> superficies = Atributo.mapAtributoM2(con, db);
			
			Map<Long,Long> dec = Moneda.numeroDecimal(con, db);
			Long numDec = dec.get((long)1);
			if(numDec == null) {
				numDec = (long)0;
			}
			DecimalFormat formatDecimal = new DecimalFormat("#,##0");
			
			while(rs.next()) {
				
				Double peso = pesos.get(rs.getLong(19));
				if(peso==null) peso=(double)0;
				Double sup = superficies.get(rs.getLong(19));
				if(sup==null) sup=(double)0;
				
				String totalKG = myformatdouble2.format(rs.getDouble(9) * peso);
				String totalM2 = myformatdouble2.format(rs.getDouble(9) * sup);
				
				numDec = dec.get((long)1);
				if(numDec==null) {
					numDec = (long)0;
				}
				
				switch(numDec.toString()) {
				 case "0": formatDecimal = new DecimalFormat("#,##0"); break;
				 case "2": formatDecimal = new DecimalFormat("#,##0.00"); break;
				 case "4": formatDecimal = new DecimalFormat("#,##0.0000"); break;
				 case "6": formatDecimal = new DecimalFormat("#,##0.000000"); break;
				 default:  break;
				}
				
				String nameComercial = "";
				String nameSucursal = "";
				Sucursal sucursal = mapSucursal.get(rs.getLong(1));
				if(sucursal!=null) {
					nameSucursal = sucursal.getNombre();
				}
				Comercial comercial = mapComercial.get(rs.getLong(2));
				if(comercial!=null) {
					nameComercial = comercial.getNameUsuario();
				}
				
				String cantidad = myformatdouble2.format(rs.getDouble(9));
				String permanencia = myformatdouble2.format(rs.getDouble(15));
				
				String reposicion = formatDecimal.format(rs.getDouble(16));
				if(rs.getLong(10)>0) {
					reposicion = formatDecimal.format((double)0);
				}
				
				String arriendo = formatDecimal.format(rs.getDouble(17));
				String venta = formatDecimal.format(rs.getDouble(18));
				
				String puVenta = formatDecimal.format(rs.getDouble(12));
				String puArriendo = formatDecimal.format(rs.getDouble(14));
				
				String esVenta = "Venta";
				if(rs.getLong(10) == (long) 0){
					esVenta = mapDiccionario.get("Arriendo");
				}
				
				String nameCotizaSolucion = "";
				CotizaSolucion cotizaSolucion = mapCotizaSolucion.get(rs.getLong(22));
				if(cotizaSolucion!=null) {
					nameCotizaSolucion = cotizaSolucion.getSolucion();
				}
				
				List<String> aux = new ArrayList<String>();
				aux.add(nameSucursal);		// 0 nameSucursal
				aux.add(nameComercial);		// 1 nameComercial
				aux.add(rs.getString(3));	// 2 numero
				aux.add(Fechas.DDMMAA(rs.getString(4)));	// 3 fecha
				aux.add(rs.getString(5));	// 4 observaciones
				aux.add(rs.getString(6));	// 5 codigo
				aux.add(rs.getString(7));	// 6 equipo
				aux.add(rs.getString(8));	// 7 unidad
				aux.add(cantidad);			// 8 cantidad
				aux.add(esVenta);			// 9 esVenta
				aux.add(rs.getString(11));	// 10 moneda
				aux.add(puVenta);			// 11 puVenta
				aux.add(rs.getString(13));	// 12 unidadTiempo
				aux.add(puArriendo);		// 13 puArriendo
				aux.add(permanencia);		// 14 permanencia
				aux.add(reposicion);		// 15 reposicion
				aux.add(arriendo);			// 16 arriendo
				aux.add(venta);				// 17 venta
				aux.add(totalKG);			// 18 KG
				aux.add(totalM2);			// 19 M2
				aux.add(numDec.toString());	// 20 numDec
				aux.add(rs.getString(20));	// 21 id_cotizacion
				aux.add(rs.getString(21));	// 22 id_moneda
				aux.add(nameCotizaSolucion);	// 23 solucion
				lista.add(aux);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return(lista);
	}
	
	public static String vistaModalVerCotiResumen( Map<String,String> mapDiccionario, List<List<String>> resumen, Cliente cliente, Proyecto proyecto, Map<String,String> mapeoPermiso){
		String vista="";
		
		
		vista = vista +
		"<table class='table table-sm table-hover table-bordered table-condensed table-fluid'>"+
			"<tr>"+
				"<td><label for='rutCliente'>"+mapDiccionario.get("RUT")+" Cliente: "+cliente.getRut()+"</label></td>"+
				"<td><label for='nombreCliente'>Nombre Cliente: "+cliente.getNickName()+"</label></td>"+
				"<td><label for='nombreCliente'>Proyecto: "+proyecto.getNickName()+"</label></td>"+
			"</tr>"+
		"</table>"+
		"<table class='table table-sm table-hover table-bordered table-condensed table-fluid'>"+
	        "<thead style='background-color: #eeeeee'>"+
				"<TR> "+
				        "<TH style= 'text-align: center;'>SUCURSAL</TH>"+
				        "<TH style= 'text-align: center;'>COMERCIAL</TH>"+
						"<TH style= 'text-align: center;'>NRO COTI</TH>"+
						"<TH style= 'text-align: center;'>FECHA COTI</TH>"+
						"<TH style= 'text-align: center;'>OBSERVACIONES</TH>"+
						"<TH style= 'text-align: center;'>CODIGO</TH>"+
						"<TH style= 'text-align: center;'>EQUIPO</TH>"+
						"<TH style= 'text-align: center;'>UNIDAD</TH>"+
						"<TH style= 'text-align: center;'>CANTIDAD</TH>"+
						"<TH style= 'text-align: center;'>CONCEPTO</TH>"+
						"<TH style= 'text-align: center;'>MON</TH>"+
						"<TH style= 'text-align: center;'>P.UNIT<br>VTA/REPOS</TH>"+
						"<TH style= 'text-align: center;'>UN<br>"+mapDiccionario.get("ARR")+"</TH>"+
						"<TH style= 'text-align: center;'>P.UNIT<br>"+mapDiccionario.get("ARR")+"</TH>"+
						"<TH style= 'text-align: center;'>PERM</TH>"+
						"<TH style= 'text-align: center;'>P.TOTAL<br>REPOS</TH>"+
						"<TH style= 'text-align: center;'>P.TOTAL<br>"+mapDiccionario.get("ARR")+"</TH>"+
						"<TH style= 'text-align: center;'>P.TOTAL<br>VENTA</TH>"+
						"<TH style= 'text-align: center;'>KG</TH>";
						if(mapeoPermiso.get("parametro.escondeLosM2")!=null && mapeoPermiso.get("parametro.escondeLosM2").equals("1")) {
							vista += "<TH style= 'text-align: center; display:none'>M2</TH>";
						}else {
							vista += "<TH style= 'text-align: center;'>M2</TH>";
						}
				vista +=
				"</TR>"+
			"</thead>"+
			"<tbody>";
		
				Double totalRepos = (double)0;
				Double totalArr = (double)0;
				Double totalVta = (double)0;
				String numDec = "0";
				
				Double totalCant = (double)0;
				Double totalKg = (double)0;
				Double totalM2 = (double)0;
				
				for(int i=0; i<resumen.size(); i++){
					vista=vista+
					"<TR>"+
						"<td style='text-align:left;'>"+resumen.get(i).get(0)+"</td>"+
						"<td style='text-align:left;'>"+resumen.get(i).get(1)+"</td>"+
						"<td style='text-align:center;'>"+resumen.get(i).get(2)+"</td>"+
						"<td style='text-align:center;'>"+resumen.get(i).get(3)+"</td>"+
						"<td style='text-align:left;'>"+resumen.get(i).get(4)+"</td>"+
						"<td style='text-align:left;'>"+resumen.get(i).get(5)+"</td>"+
						"<td style='text-align:left;'>"+resumen.get(i).get(6)+"</td>"+
						"<td style='text-align:center;'>"+resumen.get(i).get(7)+"</td>"+
						"<td style='text-align:right;'>"+resumen.get(i).get(8)+"</td>"+
						"<td style='text-align:center;'>"+resumen.get(i).get(9)+"</td>"+
						"<td style='text-align:center;'>"+resumen.get(i).get(10)+"</td>"+
						"<td style='text-align:right;'>"+resumen.get(i).get(11)+"</td>"+
						"<td style='text-align:center;'>"+resumen.get(i).get(12)+"</td>"+
						"<td style='text-align:right;'>"+resumen.get(i).get(13)+"</td>"+
						"<td style='text-align:right;'>"+resumen.get(i).get(14)+"</td>"+
						"<td style='text-align:right;'>"+resumen.get(i).get(15)+"</td>"+
						"<td style='text-align:right;'>"+resumen.get(i).get(16)+"</td>"+
						"<td style='text-align:right;'>"+resumen.get(i).get(17)+"</td>"+
						"<td style='text-align:right;'>"+resumen.get(i).get(18)+"</td>";
						if(mapeoPermiso.get("parametro.escondeLosM2")!=null && mapeoPermiso.get("parametro.escondeLosM2").equals("1")) {
							vista += "<td style='text-align:right; display:none'>"+resumen.get(i).get(19)+"</td>";
						}else {
							vista += "<td style='text-align:right;'>"+resumen.get(i).get(19)+"</td>";
						}
					vista +=
					"</TR>";
						
					Double aux = Double.parseDouble(resumen.get(i).get(15).replaceAll(",", ""));
					totalRepos += aux;
					aux = Double.parseDouble(resumen.get(i).get(16).replaceAll(",", ""));
					totalArr += aux;
					aux = Double.parseDouble(resumen.get(i).get(17).replaceAll(",", ""));
					totalVta += aux;
					numDec = resumen.get(i).get(20);
					
					aux = Double.parseDouble(resumen.get(i).get(8).replaceAll(",", ""));
					totalCant += aux;
					aux = Double.parseDouble(resumen.get(i).get(18).replaceAll(",", ""));
					totalKg += aux;
					aux = Double.parseDouble(resumen.get(i).get(19).replaceAll(",", ""));
					totalM2 += aux;
		 		}
				
				DecimalFormat formatDecimal = new DecimalFormat("#,##0");
				switch(numDec.toString()) {
				 case "0": formatDecimal = new DecimalFormat("#,##0"); break;
				 case "2": formatDecimal = new DecimalFormat("#,##0.00"); break;
				 case "4": formatDecimal = new DecimalFormat("#,##0.0000"); break;
				 case "6": formatDecimal = new DecimalFormat("#,##0.000000"); break;
				 default:  break;
				}
			
			vista = vista +
			"</tbody>"+
			"<tfoot style='background-color: #eeeeee'>";
			
			
			vista = vista +
				
				"<TR>"+
						"<TH colspan='8' style= 'text-align: right;'>Total cantidad</TH>"+
						"<TH colspan='1' style= 'text-align: right;'>"+myformatdouble2.format(totalCant)+"</TH>"+
						"<TH colspan='6' style= 'text-align: right;'>TOTALES </TH>"+
								
						"<TH style='text-align:right;'>"+formatDecimal.format(totalRepos)+"</TH>"+
						"<TH style='text-align:right;'>"+formatDecimal.format(totalArr)+"</TH>"+
						"<TH style='text-align:right;'>"+formatDecimal.format(totalVta)+"</div></TH>"+
						"<TH style='text-align:right;'>"+myformatdouble2.format(totalKg)+"</TH>";
						if(mapeoPermiso.get("parametro.escondeLosM2")!=null && mapeoPermiso.get("parametro.escondeLosM2").equals("1")) {
							vista += "<TH style='text-align:right; display:none'>"+myformatdouble2.format(totalM2)+"</TH>";
						}else {
							vista += "<TH style='text-align:right;'>"+myformatdouble2.format(totalM2)+"</TH>";
						}
				vista +=
				"</TR>"+
			"</tfoot>"+
		"</table>";
		return(vista);
	}
	
	
	
	
}









